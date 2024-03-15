package edu.daw.controller;

import edu.daw.entities.Articulos;
import edu.daw.entities.Usuarios;
import edu.daw.entities.Comentarios;
import java.io.File;
import java.io.FileOutputStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
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
import javax.servlet.http.Part;

/**
 *
 * @author ismae
 */
@WebServlet(name = "Controlador", urlPatterns = {"/home", "/login", "/register", "/juegos", "/validar", "/guardar", "/logout",
    "/favoritos", "/publicarJuego", "/crearJuego", "/filtro", "/detalles", "/publicarComentario",
    "/addFavorito", "/validacionCorreo"})

@MultipartConfig
public class Controlador extends HttpServlet {

    @PersistenceContext(unitName = "ProyectoDawPU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion;
        accion = request.getServletPath();
        String vista;
        HttpSession session = request.getSession();
        TypedQuery<Usuarios> queryUsers;
        TypedQuery<Articulos> queryArt;
        TypedQuery<Comentarios> queryCom;
        List<Comentarios> lc;
        List<Articulos> la, laUP;
        List<Articulos> lfav;

        switch (accion) {
            case "/home":
                //Pagina principal
                queryArt = em.createNamedQuery("Articulos.findLastPublished", Articulos.class);
                laUP = (List<Articulos>) queryArt.setFirstResult(0).setMaxResults(3).getResultList();
                request.setAttribute("ultimosPublicados", laUP);
                vista = "/WEB-INF/jsp/home.jsp";
                break;

            case "/login":
                //Mostrar el formulario de login
                vista = "/WEB-INF/jsp/login.jsp";
                break;

            case "/register":
                //Mostrar el formulario de login
                vista = "/WEB-INF/jsp/register.jsp";
                break;

            case "/juegos":
                //Muestra el listado de los juegos
                queryArt = em.createNamedQuery("Articulos.findAll", Articulos.class);
                la = queryArt.getResultList();
                request.setAttribute("articulos", la);

                vista = "/WEB-INF/jsp/juegos.jsp";
                break;

            case "/validar":
                // Validar el login del usuario
                try {
                String user = request.getParameter("usuario");
                String pass = request.getParameter("passw");

                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(pass.getBytes());
                byte[] digest = md.digest();
                StringBuilder sb = new StringBuilder();
                for (byte b : digest) {
                    sb.append(String.format("%02x", b & 0xff));
                }

                String pass_digest = sb.toString();
                TypedQuery<Usuarios> query = em.createNamedQuery("Usuarios.findByEmailAndPass", Usuarios.class);
                query.setParameter("name", user);
                query.setParameter("pwd", pass_digest);

                List<Usuarios> lu;
                lu = query.getResultList();

                if (!lu.isEmpty()) {
                    Usuarios u = lu.get(0);
                    session.setAttribute("id", u.getId());
                    session.setAttribute("user", u.getNombre());
                    session.setAttribute("email", u.getEmail());
                    request.setAttribute("msgOK", "Sesión iniciada correctamente");

                    queryArt = em.createNamedQuery("Articulos.findLastPublished", Articulos.class);
                    laUP = (List<Articulos>) queryArt.setFirstResult(0).setMaxResults(3).getResultList();
                    request.setAttribute("ultimosPublicados", laUP);

                    vista = "/WEB-INF/jsp/home.jsp";
                } else {
                    request.setAttribute("msgErrorLogin", "El usuario y/o la contraseña no son correctos");
                    vista = "/WEB-INF/jsp/login.jsp";
                }
            } catch (Exception e) {
                System.err.println(e);
                request.setAttribute("msgErrorLogin", "ERROR: imposible validar al usuario");
                vista = "/WEB-INF/jsp/login.jsp";
            }
            break;

            case "/guardar":
                // Guardar datos del usuario nuevo en la BD
                try {
                String user = request.getParameter("usuario");
                String pass = request.getParameter("passw");
                String email = request.getParameter("correo");

                queryUsers = em.createNamedQuery("Usuarios.findByEmail", Usuarios.class);
                queryUsers.setParameter("email", email);
                List<Usuarios> luCorreo = queryUsers.getResultList();

                if (luCorreo.size() > 0) {
                    request.setAttribute("msgErrorRegister", "ERROR: Usuario NO guardado");
                    vista = "/WEB-INF/jsp/register.jsp";
                } else {
                    String codPostal = request.getParameter("cp");
                    String telefono = request.getParameter("telf");
                    MessageDigest md = MessageDigest.getInstance("MD5");
                    md.update(pass.getBytes());
                    byte[] digest = md.digest();
                    StringBuilder sb = new StringBuilder();
                    for (byte b : digest) {
                        sb.append(String.format("%02x", b & 0xff));
                    }
                    String pass_digest = sb.toString();

                    Usuarios u = new Usuarios();
                    u.setNombre(user);
                    u.setPassword(pass_digest);
                    u.setEmail(email);
                    u.setCodpostal(codPostal);
                    u.setTelefono(telefono);
                    persist(u);
                    request.setAttribute("msgOKRegister", "Usuario creado correctamente");
                    vista = "/WEB-INF/jsp/login.jsp";
                }
            } catch (Exception e) {
                request.setAttribute("msgErrorRegister", "ERROR: Usuario NO guardado");
                vista = "/WEB-INF/jsp/register.jsp";
            }

            break;

            case "/validacionCorreo":
                String cB = request.getParameter("correoBuscar");

                System.out.println("El correo a comprobar es: " + cB);

                queryUsers = em.createNamedQuery("Usuarios.findByEmail", Usuarios.class);
                queryUsers.setParameter("email", cB);
                List<Usuarios> luCorreo = queryUsers.getResultList();

                if (luCorreo.size() > 0) {
                    request.setAttribute("correoNoAceptado", "No"); //Si existe manda un No al idvalido.jsp
                }

                vista = "/WEB-INF/jsp/correoValido.jsp";
                break;

            case "/logout":
                //Cerrar sesion, si no hay una sesión iniciada se denega el acceso
                if (session.getAttribute("user") != null) {
                    request.setAttribute("msgOK", "Sesión cerrada correctamente");
                    session.invalidate();
                } else {
                    request.setAttribute("msgDenegado", "Acceso denegado: No tienes permiso para acceder a esa página");
                }

                queryArt = em.createNamedQuery("Articulos.findLastPublished", Articulos.class);
                laUP = (List<Articulos>) queryArt.setFirstResult(0).setMaxResults(3).getResultList();
                request.setAttribute("ultimosPublicados", laUP);

                vista = "/WEB-INF/jsp/home.jsp";
                break;

            case "/favoritos":
                //Acceder a la pagina privada, si no hay una sesion iniciada se deniega el acceso
                if (session.getAttribute("user") != null) {
                    vista = "/WEB-INF/jsp/favoritos.jsp";
                } else {
                    request.setAttribute("msgDenegado", "Acceso denegado: No tienes permiso para acceder a esa página");

                    queryArt = em.createNamedQuery("Articulos.findLastPublished", Articulos.class);
                    laUP = (List<Articulos>) queryArt.setFirstResult(0).setMaxResults(3).getResultList();
                    request.setAttribute("ultimosPublicados", laUP);

                    vista = "/WEB-INF/jsp/home.jsp";
                }
                break;

            case "/publicarJuego":
                if (session.getAttribute("user") != null) {
                    vista = "/WEB-INF/jsp/publicarJuego.jsp";
                } else {
                    request.setAttribute("msgDenegado", "Acceso denegado: No tienes permiso para acceder a esa página");

                    queryArt = em.createNamedQuery("Articulos.findLastPublished", Articulos.class);
                    laUP = (List<Articulos>) queryArt.setFirstResult(0).setMaxResults(3).getResultList();
                    request.setAttribute("ultimosPublicados", laUP);

                    vista = "/WEB-INF/jsp/home.jsp";
                }
                break;

            case "/crearJuego":
                // Guardar datos del articulo nuevo en la BD
                String nombreArticulo = request.getParameter("nombre");
                int precio = Integer.parseInt(request.getParameter("precio"));
                String genero = request.getParameter("generoArticulo");
                int año = Integer.parseInt(request.getParameter("fecha"));
                String descripcion = request.getParameter("descripcion");
                try {
                    Articulos a = new Articulos();
                    a.setNombre(nombreArticulo);
                    a.setPrecio(precio);
                    a.setGenero(genero);
                    a.setAñoAdquisicion(año);
                    a.setDetalles(descripcion);

                    queryUsers = em.createNamedQuery("Usuarios.findByEmail", Usuarios.class);
                    queryUsers.setParameter("email", session.getAttribute("email"));
                    List<Usuarios> lu;
                    lu = queryUsers.getResultList();

                    a.setUsuario(lu.get(0));

                    persist(a);
                    request.setAttribute("msgOK", "Articulo publicado correctamente");

                    //Asignación de la imagen del articulo
                    final Part filePart = request.getPart("imgArticulo"); //Coge el archivo, si no se pone nada en el formulario este es NULL
                    if (filePart != null) {
                        String nombre = filePart.getName();
                        Long tamano = filePart.getSize();
                        String file = filePart.getSubmittedFileName();

                        String relativePathFolder = "img"; //Si no existe la carpeta img, la crea
                        String absolutePathFolder = getServletContext().getRealPath(relativePathFolder);

                        File folder = new File(absolutePathFolder);
                        if (folder.exists()) {
                            System.out.println("La carpeta existe... no se crea");
                        } else {
                            folder.mkdir();
                            System.out.println("La carpeta NO existe, la creamos");
                        }

                        System.out.println(absolutePathFolder + File.separator + nombreArticulo + año + ".jpg");
                        File f = new File(absolutePathFolder + File.separator + nombreArticulo + año + ".jpg"); //Guarda la imagen con el nombre del codigo del producto, da igual el nombre que tenia antes

                        String nfp = f.getAbsolutePath();
                        OutputStream p = new FileOutputStream(f);
                        InputStream filecontent;
                        filecontent = filePart.getInputStream();
                        //System.out.println("Tam: " + filePart.getSize());

                        int read = 0;
                        final byte[] bytes = new byte[1024];
                        while ((read = filecontent.read(bytes)) != -1) {
                            p.write(bytes, 0, read);
                        }

                        p.close();
                        filecontent.close();
                    }

                    queryArt = em.createNamedQuery("Articulos.findAll", Articulos.class);
                    la = queryArt.getResultList();
                    request.setAttribute("articulos", la);

                    vista = "/WEB-INF/jsp/juegos.jsp";
                } catch (Exception e) {
                    request.setAttribute("msgERRORpublicar", "ERROR: Articulo NO publicado");
                    vista = "/WEB-INF/jsp/publicarJuego.jsp";
                }
                break;

            case "/filtro":
                String where = "";
                String nB = request.getParameter("nombreBuscar");
                boolean nBrelleno = false;
                String gB = request.getParameter("generoBuscar");
                String pB = request.getParameter("precioBuscar");

                if (!"".equals(nB)) {
                    where = "WHERE a.nombre LIKE '" + nB + "%'";
                    nBrelleno = true;
                }

                if (!"".equals(gB)) {
                    if (nBrelleno) {
                        where += " AND a.genero LIKE '" + gB + "%'";
                    } else {
                        where = "WHERE a.genero LIKE '" + gB + "%'";
                    }
                }

                if (!"".equals(pB)) {
                    if (pB.equals("Menor_a_Mayor")) {
                        where += " ORDER BY a.precio";
                    } else {
                        where += " ORDER BY a.precio DESC";
                    }
                }

                System.out.println("El where vale: " + where);

                TypedQuery<Articulos> q1 = em.createQuery("SELECT a FROM Articulos a " + where, Articulos.class);
                System.out.println("La query es: " + "SELECT a FROM Articulos a " + where);
                List<Articulos> laf = (List<Articulos>) q1.getResultList();
                request.setAttribute("articulos", laf);

                vista = "/WEB-INF/jsp/datos.jsp";
                break;

            case "/detalles":
                String idArticulo;
                Articulos articulo;

                idArticulo = request.getParameter("id");
                queryArt = em.createNamedQuery("Articulos.findById", Articulos.class);
                queryArt.setParameter("id", Integer.parseInt(idArticulo));
                la = queryArt.getResultList();
                articulo = la.get(0);
                request.setAttribute("articulo", articulo);

                queryCom = em.createNamedQuery("Comentarios.findByArticulo", Comentarios.class);
                queryCom.setParameter("id", Integer.parseInt(idArticulo));
                lc = queryCom.getResultList();
                request.setAttribute("comentarios", lc);

                vista = "/WEB-INF/jsp/detalles.jsp";
                break;

            case "/publicarComentario":
                int idArticuloComentario = Integer.parseInt(request.getParameter("id-articulo-comentario"));
                String descripcionComentario = request.getParameter("publicar-comentario-descripcion");

                if (descripcionComentario.isEmpty()) {
                    request.setAttribute("msgDenegado", "ERROR: Comentario NO publicado");
                    vista = "detalles?id=" + idArticuloComentario;
                } else {
                    String privacidadComentario = request.getParameter("privaciad");

                    Comentarios comentario = new Comentarios();
                    try {
                        comentario.setTexto(descripcionComentario);
                        comentario.setPrivacidad(privacidadComentario);

                        queryUsers = em.createNamedQuery("Usuarios.findByEmail", Usuarios.class);
                        queryUsers.setParameter("email", session.getAttribute("email"));
                        List<Usuarios> lu;
                        lu = queryUsers.getResultList();
                        comentario.setUsuario(lu.get(0));

                        queryArt = em.createNamedQuery("Articulos.findById", Articulos.class);
                        queryArt.setParameter("id", idArticuloComentario);
                        la = queryArt.getResultList();
                        comentario.setArticulo(la.get(0));

                        persist(comentario);
                        request.setAttribute("msgOK", "Comentario publicado correctamente");

                        queryCom = em.createNamedQuery("Comentarios.findByArticulo", Comentarios.class);
                        queryCom.setParameter("id", idArticuloComentario);
                        lc = queryCom.getResultList();
                        request.setAttribute("comentarios", lc);

                        vista = "detalles?id=" + idArticuloComentario;

                    } catch (Exception e) {
                        request.setAttribute("msgDenegado", "ERROR: Comentario NO publicado");
                        vista = "detalles?id=" + idArticuloComentario;
                    }
                }
                break;

            case "/addFavorito":
                String idArtFav = request.getParameter("id");
                if (idArtFav != null) {
                    queryArt = em.createNamedQuery("Articulos.findById", Articulos.class);
                    queryArt.setParameter("id", Integer.parseInt(idArtFav));
                    la = queryArt.getResultList();
                    if (la != null) { // Esta el artículo
                        lfav = (List<Articulos>) session.getAttribute("lartFav"); //Vemos si ya existe la lista de favoritos en la sesion
                        if (lfav == null) {
                            lfav = new ArrayList<>(); //Si no existe la creamos
                        }
                        Articulos a = la.get(0);
                        lfav.add(a);
                        request.setAttribute("msgOK", "Juego guardado en favoritos");
                        session.setAttribute("lartFav", lfav);
                    } else { //No está el art
                        request.setAttribute("msgDenegado", "ERROR: no se ha encontrado en juego a agregar");
                    }
                } else { // Id no enviado
                    request.setAttribute("msgDenegado", "ERROR: id del juego no encontrado");
                }
                vista = "detalles?id=" + idArtFav;
                break;
                
            default:
                queryArt = em.createNamedQuery("Articulos.findLastPublished", Articulos.class);
                laUP = (List<Articulos>) queryArt.setFirstResult(0).setMaxResults(3).getResultList();
                request.setAttribute("ultimosPublicados", laUP);

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
}
