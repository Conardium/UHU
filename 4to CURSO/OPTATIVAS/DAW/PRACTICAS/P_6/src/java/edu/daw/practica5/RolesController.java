/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.daw.practica5;

import java.io.IOException;
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
@WebServlet(name = "RolesController", urlPatterns = {"/roles/*"})
public class RolesController extends HttpServlet {

    @PersistenceContext(unitName = "javaWebApp2PU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion;
        accion = request.getPathInfo(); //Coge el tipo de accion que se va a realizar con la ultima parte del path
        String vista;
        System.out.println("action: "+ accion);
        TypedQuery<Roles> query;
        List<Roles> lr;
        Roles r;
        Long id;

        switch (accion) {
            case "/show": //Muestra los roles
                // Obtener todas las intancias de la Entidad Roles y mostrarlos en la Vista
                query = em.createNamedQuery("Roles.findAll", Roles.class);
                lr = query.getResultList();
                System.out.println("Mostrando");
                request.setAttribute("roles", lr);
                
                vista = "/WEB-INF/jsp/showRoles.jsp";
                break;
            case "/altaRol": //Crea un rol
                // funcionalidad para activar el formulario de alta  
                vista = "/WEB-INF/jsp/altaRoles.jsp";
                break;
            
            case "/persistRol": //Persiste(commit en la BD) un rol
                // funcionalidad para añadir los Roles
                String name = request.getParameter("nombre");
                if (name!="" && name != null) {
                    r = new Roles();
                    r.setRol(name);
                    try {
                        persist(r);
                        System.out.println("Rol: " + name + " creado");
                    }catch (Exception ex) {
                        System.out.println("Error: Imposible persistir  Rol: " + name);
                    } 
                } else {
                    System.out.println("Error: Nombre de Rol incorrecto");
                }
                query = em.createNamedQuery("Roles.findAll", Roles.class);
                lr = query.getResultList();
                
                request.setAttribute("roles", lr);
                vista = "/WEB-INF/jsp/showRoles.jsp";
                break;
               
            case "/editRol": //Edita un rol (se activa desde el formulario)
                r = new Roles();
                id = Long.parseLong(request.getParameter("id"));
                update(r);
                System.out.println("Rol con id " + id + " editado");
                
                query = em.createNamedQuery("Roles.findAll", Roles.class);
                lr = query.getResultList();
                request.setAttribute("roles", lr); 
                
                vista = "/WEB-INF/jsp/showRoles.jsp";
                break;
                
            case "/mergeRol": //Combina dos roles
                //
                vista = "/WEB-INF/jsp/showRoles.jsp";
                break;
                
            case "/delete": //Elimina un rol (se activa desde el formulario)
                // funcionaliad para eliminar un Rol
                id = Long.parseLong(request.getParameter("id"));
                r = em.find(Roles.class, id);
                delete(r);
                query = em.createNamedQuery("Roles.findAll", Roles.class);
                lr = query.getResultList();
                request.setAttribute("roles", lr); 
                
                vista = "/WEB-INF/jsp/showRoles.jsp";
                break;
                
            default:
                vista = "/index.html";
                break;
        }
        
        RequestDispatcher rd = request.getRequestDispatcher(vista);
        rd.forward(request, response);
    }

    private void delete(Object object) {
        try {
            utx.begin();
            object = (Object) em.merge(object);
            em.remove(object);
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }        
    }
    
    private void update(Object obj){
        try {
            utx.begin();
            obj = (Object) em.merge(obj);
            em.refresh(obj);
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
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
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caugth", e);
            throw new RuntimeException(e);
        }
    }

}
