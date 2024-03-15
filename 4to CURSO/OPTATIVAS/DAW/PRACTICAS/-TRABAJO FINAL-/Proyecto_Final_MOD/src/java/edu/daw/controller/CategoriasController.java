/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package edu.daw.controller;

import edu.daw.entities.Articulos;
import edu.daw.entities.Categoria;
import edu.daw.entities.Comentarios;
import edu.daw.entities.Usuarios;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ismae
 */
@WebServlet(name = "Categorias", urlPatterns = {"/Categorias/*"})
public class CategoriasController extends HttpServlet {

    @PersistenceContext(unitName = "ProyectoDawPU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion;
        accion = request.getPathInfo();
        String vista;
        HttpSession session = request.getSession();
        TypedQuery<Usuarios> queryUsers;
        TypedQuery<Categoria> queryCat;
        List<Categoria> lcat;
        List<Usuarios> lusers;

        System.out.println("La accion es: " + accion);

        switch (accion) {
            case "/cat":
                if (session.getAttribute("user").equals("admin")) {
                    queryCat = em.createNamedQuery("Categoria.findAll", Categoria.class);
                    lcat = queryCat.getResultList();
                    request.setAttribute("categorias", lcat);

                    vista = "/WEB-INF/jsp/categorias.jsp";
                } else {
                    request.setAttribute("msgDenegado", "Acceso denegado: Solo el admin puede acceder a las categorias");
                    vista = "/WEB-INF/jsp/errorAcceso.jsp";
                }
                break;

            case "/addCategoria":
                String nombre = request.getParameter("nombre");
                try {
                    Categoria c = new Categoria();
                    c.setName(nombre);
                    persist(c);
                    request.setAttribute("msgOKCat", "Categoría " + nombre + " creada correctamente");
                } catch (Exception e) {
                    request.setAttribute("msgDenegadoCat", "ERROR: Categoria NO guardado");
                }

                queryCat = em.createNamedQuery("Categoria.findAll", Categoria.class);
                lcat = queryCat.getResultList();
                request.setAttribute("categorias", lcat);
                vista = "/WEB-INF/jsp/categorias.jsp";
                break;
                
            case "/delCategoria":
                long id = Long.parseLong(request.getParameter("id"));
                System.out.println("El id ha borrar es: " + id);
                
                queryCat = em.createNamedQuery("Categoria.findById", Categoria.class);
                queryCat.setParameter("id", id);
                lcat = queryCat.getResultList();
                
                delete(lcat.get(0));
                
                queryCat = em.createNamedQuery("Categoria.findAll", Categoria.class);
                lcat = queryCat.getResultList();
                request.setAttribute("categorias", lcat);
                vista = "/WEB-INF/jsp/categorias.jsp";
                break;

            default:
                vista = "/WEB-INF/jsp/home.jsp";
                break;
        }

        RequestDispatcher rd = request.getRequestDispatcher(vista);
        rd.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public void persist(Object object) {
        try {
            utx.begin();
            em.persist(object);
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }
    
    public void delete(Object object) {
        try {
            utx.begin();
            object = em.merge(object);
            em.remove(object);
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }

}
