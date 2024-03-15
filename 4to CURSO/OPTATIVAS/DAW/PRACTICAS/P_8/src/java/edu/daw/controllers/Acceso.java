package edu.daw.controllers;

import edu.daw.entities.Usuarios;
import java.io.IOException;
import java.security.MessageDigest;
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
@WebServlet(name = "Acceso", urlPatterns = {"/acceso/*"})
public class Acceso extends HttpServlet {

    @PersistenceContext(unitName = "userAuthAppPU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion;
        accion = request.getPathInfo();
        String vista;
        HttpSession session = request.getSession();

        switch (accion) {
            case "/home":
                //Pagina principal
                vista = "/home.jsp";
                break;

            case "/login":
                //Mostrar el formulario de login
                vista = "/WEB-INF/jsp/login.jsp";
                break;

            case "/validar":
                // Validar el usuario
                try {
                String user = request.getParameter("username");
                String pass = request.getParameter("password");

                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(pass.getBytes());
                byte[] digest = md.digest();
                StringBuilder sb = new StringBuilder();
                for (byte b : digest) {
                    sb.append(String.format("%02x", b & 0xff));
                }
                String pass_digest = sb.toString();
                TypedQuery<Usuarios> query = em.createNamedQuery("Usuarios.findByNameAndPass", Usuarios.class);
                query.setParameter("name", user);
                query.setParameter("pwd", pass_digest);

                List<Usuarios> lu;
                lu = query.getResultList();
                if (!lu.isEmpty()) {
                    Usuarios u = lu.get(0);
                    session.setAttribute("id", u.getId());
                    session.setAttribute("user", u.getNick());
                    request.setAttribute("msg", "Login correcto.");
                    vista = "/index.jsp";
                } else {
                    request.setAttribute("msg", "Usuario o Password incorrecto.");
                    vista = "/WEB-INF/jsp/login.jsp";
                }
            } catch (Exception e) {
                System.err.println(e);
                request.setAttribute("msg", "ERROR: imposible validar al usuario");
                vista = "/WEB-INF/jsp/login.jsp";
            }
            break;

            case "/alta":
                // Mostrar el formulario de alta
                vista = "/WEB-INF/jsp/alta.jsp";
                break;

            case "/guardar":
                // Guardar datos de usuario
                // UNDO: Verificación de los datos
                try {
                String user = request.getParameter("user");
                String pass = request.getParameter("pw1");
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(pass.getBytes());
                byte[] digest = md.digest();
                StringBuilder sb = new StringBuilder();
                for (byte b : digest) {
                    sb.append(String.format("%02x", b & 0xff));
                }
                String pass_digest = sb.toString();

                Usuarios u = new Usuarios();
                u.setNick(user);
                u.setPass(pass_digest);
                persist(u);
                request.setAttribute("msg", "Usuario guardado");
            } catch (Exception e) {
                request.setAttribute("msg", "ERROR: Usuario NO guardado");
            }
            vista = "/WEB-INF/jsp/alta.jsp";
            break;

            case "/logout":
                //Cerrar sesion
                session.invalidate();
                request.setAttribute("msg", "Usuario desconectado");
                vista = "/index.jsp";
                break;

            case "/privado":
                //Acceder a la pagina privada, solo disponible si la sesion de un usario está iniciada
                if (session.getAttribute("user") != null) {
                    vista = "/WEB-INF/jsp/privado.jsp";
                } else {
                    request.setAttribute("msg", "Acceso denegado");
                    vista = "/index.jsp";
                }
                break;

            default:
                vista = "/home.jsp";
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
