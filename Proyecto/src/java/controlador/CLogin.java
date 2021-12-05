/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;
import modelo.ProductoDAO;
import modelo.UsuarioDAO;

/**
 *
 * @author Alizr
 */
@WebServlet(name = "CLogin", urlPatterns = {"/Login"})
public class CLogin extends HttpServlet {

  UsuarioDAO udao = new UsuarioDAO();
  Usuario u = new Usuario();

  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    HttpSession ses = request.getSession();
    String menu = request.getParameter("menu");
    String accion = request.getParameter("accion");
    if (ses.getAttribute("usuario") == null) {
      response.sendRedirect("vistas/Login.jsp");
    } else {
      if (accion == null) {
        accion = "Listar";
      }
      switch (accion) {
        case "Listar":
          request.setAttribute("productos", new ProductoDAO().allProductos());
          request.getRequestDispatcher("vistas/Principal.jsp").forward(request, response);
          break;
        case "Registrar":
          break;
        case "cerrar":
          HttpSession session = request.getSession();
          session.invalidate();
          response.sendRedirect("vistas/Login.jsp");
          break;
        default:
          break;
      }
    }
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    processRequest(request, response);

  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    response.setContentType("text/html");
    response.setCharacterEncoding("UTF-8");
    String accion = request.getParameter("accion");
    if (accion.equals("registrarse")) {
      String dni = request.getParameter("txtdni");
      String nomb = request.getParameter("txtnombres");
      String ape = request.getParameter("txtapellidos");
      String dire = request.getParameter("txtdireccion");
      String tel = request.getParameter("txttelefono");
      String ema = request.getParameter("txtemail");
      String pass = request.getParameter("txtpassword");
      int pri = Integer.parseInt(request.getParameter("cboprivilegio"));
      int estado = Integer.parseInt(request.getParameter("cboestado"));

      u.setDni(dni);
      u.setNombres(nomb);
      u.setApellidos(ape);
      u.setDireccion(dire);
      u.setTelefono(tel);
      u.setEmail(ema);
      u.setPassword(pass);
      u.setIdprivilegio(pri);
      u.setEstado(estado);
      udao.agregarUsuario(u);
      response.sendRedirect("vistas/Login.jsp");
    }
    if (accion.equals("login")) {
      String usuario = request.getParameter("txtemail");
      String pass = request.getParameter("txtpassword");

      HttpSession sesion = request.getSession();
      u = udao.Login(usuario, pass);
      if (u.getEmail() != null) {
        sesion.setAttribute("usuario", u.getNombres());
        sesion.setAttribute("id", u.getIdusuario());
        sesion.setAttribute("privilegio", u.getIdprivilegio());
        sesion.setAttribute("nombrepri", u.getNombreprivilegio());
        request.setAttribute("productos", new ProductoDAO().allProductos());
        request.getRequestDispatcher("vistas/Principal.jsp").forward(request, response);
      } else {
        request.setAttribute("fail", "Usuario invalido");
        response.sendRedirect("vistas/Login.jsp");
      }
    }

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

}
