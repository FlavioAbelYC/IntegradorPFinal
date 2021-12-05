/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;
import modelo.ProductoDAO;
import org.json.simple.JSONObject;

/**
 *
 * @author Alizr
 */
@WebServlet(name = "Principal", urlPatterns = {"/Principal"})
public class CPrincipal extends HttpServlet {

  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    HttpSession ses = request.getSession();
    if (ses.getAttribute("usuario") == null) {
      response.sendRedirect("vistas/Login.jsp");
    } else {
      String menu = request.getParameter("menu");
      String accion = request.getParameter("accion");
      String idtienda = "";
      if (accion == null) {
        accion = "Listar";
      }
      switch (accion) {
        case "Listar":
          todosProducto(request, response);
          break;
        case "Registrar":
          break;
        case "Editar":
          idtienda = request.getParameter("idtienda");
          JOptionPane.showMessageDialog(null, idtienda);
          request.getRequestDispatcher("vistas/Tienda.jsp").forward(request, response);
          break;
        default:
          break;
      }
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

  private void todosProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.setAttribute("productos", new ProductoDAO().allProductos());
    request.getRequestDispatcher("vistas/Principal.jsp").forward(request, response);
  }
}
