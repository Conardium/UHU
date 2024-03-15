/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package edu.daw.practica5;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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

/**
 *
 * @author ismae
 */
@WebServlet(name = "UsuariosController", urlPatterns = {"/usuarios/*"})
public class UsuariosController extends HttpServlet {

    @PersistenceContext(unitName = "javaWebApp2PU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion;
        accion = request.getPathInfo();
        String vista;
        
        TypedQuery<Roles> query;
        List<Roles> lr= new ArrayList<>();
        Roles r;
        Usuarios u;
        Long id;

        switch (accion) {
            case "/alta":
                query = em.createNamedQuery("Roles.findAll", Roles.class);
                lr = query.getResultList();               
                request.setAttribute("roles", lr); 
                
                vista="/WEB-INF/jsp/altaUsuarios.jsp";
                break;
                
            case "/persistUser":
                String msg;
                String name = request.getParameter("nombre");
                String email = request.getParameter("correo");
                String[] roles = request.getParameterValues("roles");
                if (name!=null && email!=null && roles!=null) {
                    u = new Usuarios();
                    u.setNombre(name);
                    u.setCorreo(email);
                    for (String idRol : roles ) {
                        id = Long.parseLong(idRol);
                        r = em.find(Roles.class, id);
                        if (r!=null)
                            lr.add(r);
                    }
                    u.setRoles(lr);
                    try {
                        persist(u);
                         msg="<p style='background:green;color:white'>Usuario: " + name + " insertado con éxito</p>";
                    }catch (Exception ex) {
                         msg="<p style='background:red;color:white'>Error al insertar el usuario: " + name + " en la BD</p>";
                    } 
                } else {
                    msg="<p style='background:red;color:white'>Error al insertar el usuario: " + name + " en la BD</p>";
                }                
                request.setAttribute("msg", msg);
                
                vista="/WEB-INF/jsp/altaUsuarios.jsp";
                break; 
            // Otros case
                
            default:
                vista = "/index.html";
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

}
