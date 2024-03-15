/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package edu.daw.controller;

import edu.daw.entities.Articulos;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ismae
 */
import javax.servlet.http.Part;

@WebServlet(name = "Controlador", urlPatterns = {"/home", "/creaArticulo", "/idValido",
    "/articulos",
    "/favoritos",
    "/addFavorito",
    "/delFavorito",
    "/invalidar",
    "/detalles",
    "/filtro"})
@MultipartConfig
public class Controlador extends HttpServlet {

    @PersistenceContext(unitName = "segundamanoPU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String accion;
        accion = request.getServletPath(); //Otra forma es con el PathInf
        //Con PathInf() -------> localhost:8080/contextpath/controlador/[*] 
        //Con ServletPath() ---> localhost:8080/contextpath/[Servlet] 
        String vista;

        HttpSession session = request.getSession();

        TypedQuery<Articulos> query;
        List<Articulos> lr;
        String msg = "";
        String idf;
        List<Articulos> lid;
        Articulos a;

        switch (accion) {
            case "/home":
                vista = "home";
                break;

            case "/creaArticulo":

                String id = request.getParameter("id");
                String cod = request.getParameter("cod");
                String name = request.getParameter("nombre");
                String pvp = request.getParameter("precio");
                System.out.println("id:" + id + " - cod:" + cod);
                if (id != null && cod != null && name != null && pvp != null) {
                    try {
                        a = new Articulos();
                        a.setId(Long.parseLong(id));
                        a.setCod(cod);
                        a.setNombre(name);
                        a.setPrecio(Double.parseDouble(pvp));
                        persist(a);
                        System.out.println("Artículo: " + name + " creado");
                        msg = "<p class='ok'>Artículo: " + name + " creado adecuadamente</p>";

                        final Part filePart = request.getPart("file"); //Coge el archivo, si no se pone nada en el formulario este es NULL
                        if (filePart != null) {

                            String nombre = filePart.getName();
                            Long tamano = filePart.getSize();
                            String file = filePart.getSubmittedFileName();

                            String relativePathFolder = "img"; //Si no existe la carpeta img, la crea
                            String absolutePathFolder = getServletContext().getRealPath(relativePathFolder);

                            File folder = new File(absolutePathFolder);
                            if (folder.exists()) {
                                //System.err.println("Error : " + absolutePathFolder + " existe");
                            } else {
                                folder.mkdir();
                            }

                            System.out.println(absolutePathFolder + File.separator + cod + ".jpg");
                            File f = new File(absolutePathFolder + File.separator + cod + ".jpg"); //Guarda la imagen con el nombre del codigo del producto, da igual el nombre que tenia antes

                            //String nfp = f.getAbsolutePath();
                            OutputStream p = new FileOutputStream(f);
                            InputStream filecontent;
                            filecontent = filePart.getInputStream();
                            System.out.println("Tam: " + filePart.getSize());

                            int read = 0;
                            final byte[] bytes = new byte[1024];
                            while ((read = filecontent.read(bytes)) != -1) {
                                p.write(bytes, 0, read);
                            }

                            p.close();
                            filecontent.close();

                        }

                    } catch (Exception ex) {
                        System.out.println(ex);
                        System.out.println("Error: Imposible persistir  articulo: " + name);
                        msg = "<p class='error'>Error: Artículo " + name + " no creado</p>";
                    }
                } else {
                    System.out.println("Error: datos incorrectos");
                    msg = "<p class=\"error\">Error: Faltan datos</p>";
                }

                request.setAttribute("msg", msg);
                query = em.createNamedQuery("Articulos.findAll", Articulos.class);
                lr = query.getResultList();
                request.setAttribute("articulos", lr);

                vista = "articulos";
                break;

            case "/idValido":
                idf = request.getParameter("id");
                query = em.createNamedQuery("Articulos.findById", Articulos.class);
                query.setParameter("id", Integer.parseInt(idf));
                lr = query.getResultList();
                if (lr.size() > 0) {
                    request.setAttribute("idvalido", "No"); //Si existe manda un No al idvalido.jsp
                } else {
                    request.setAttribute("idvalido", "Ok"); //En caso contrario manda OK
                }

                vista = "idvalido";
                break;

            case "/articulos":
                query = em.createNamedQuery("Articulos.findAll", Articulos.class);
                lr = query.getResultList();
                request.setAttribute("articulos", lr);

                vista = "articulos";
                break;

            case "/filtro":
                String where = null;
                boolean bn = false,
                 bn1 = false,
                 bn2 = false;
                String n = request.getParameter("nombre");
                String n1 = request.getParameter("pmenor");
                String n2 = request.getParameter("pmayor");

                if (!"".equals(n)) {
                    where = "WHERE a.nombre LIKE '" + n + "%'";
                    bn = true;
                }
                if (!"".equals(n1)) {
                    if (bn) {
                        where += " AND a.precio > " + n1;
                    } else {
                        where = "WHERE a.precio > " + n1;
                    }
                    bn1 = true;
                }
                if (!"".equals(n2)) {
                    if (bn || bn1) {
                        where += " AND a.precio < " + n2;
                    } else {
                        where = "WHERE a.precio < " + n2;
                    }
                    bn2 = true;
                }

                TypedQuery<Articulos> q1 = em.createQuery("SELECT a FROM Articulos a " + (where != null ? where : ""), Articulos.class);

                List<Articulos> la = (List<Articulos>) q1.getResultList();
                request.setAttribute("articulos", la);

                System.out.println("La query es: " + "SELECT a FROM Articulos a " + where);
                
                vista = "datos";
                break;

            case "/detalles":
                idf = request.getParameter("id");
                query = em.createNamedQuery("Articulos.findById", Articulos.class);
                query.setParameter("id", Integer.parseInt(idf));
                lr = query.getResultList();
                a = lr.get(0);
                request.setAttribute("articulo", a);
                vista = "detalles";
                break;

            case "/addFavorito":
                idf = request.getParameter("id");
                if (idf != null) {
                    query = em.createNamedQuery("Articulos.findById", Articulos.class);
                    query.setParameter("id", Integer.parseInt(idf));
                    lr = query.getResultList();
                    if (lr != null) { // Esta el artículo
                        lid = (List<Articulos>) session.getAttribute("lart");
                        if (lid == null) {
                            lid = new ArrayList<>();
                        }
                        a = lr.get(0);
                        lid.add(a);
                        msg = "<p class=\"ok\">Artículo registrado en favoritos.<p>";
                        session.setAttribute("lart", lid);
                    } else { //No está el art
                        msg = "<p class=\"ok\">ERROR: Artículo registrado en favoritos. No existía.<p>";
                    }
                } else { // Id no enviado
                    msg = "<p class=\"error\">Artículo No registrado en favoritos. Falta Id.<p>";
                }

                request.setAttribute("msg", msg);
                query = em.createNamedQuery("Articulos.findAll", Articulos.class);
                lr = query.getResultList();
                request.setAttribute("articulos", lr);

                vista = "articulos";
                break;

            case "/favoritos":

                vista = "favoritos";
                break;

            case "/delFavorito":
                idf = request.getParameter("id");
                int i = Integer.parseInt(idf);
                lid = (List<Articulos>) session.getAttribute("lart");
                lid.remove(i);
                session.setAttribute("lart", lid);
                vista = "favoritos";
                break;

            case "/invalidar": //Elimina la sesion actual, es como un LogOut
                session.invalidate();
                vista = "favoritos";
                break;

            default:
                vista = "home";
                break;
        }
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jspf/" + vista + ".jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

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
