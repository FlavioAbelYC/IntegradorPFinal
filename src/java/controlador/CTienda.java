/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.Tienda;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;
import modelo.TiendaDAO;

/**
 *
 * @author Alizr
 */
@WebServlet(name = "CTienda", urlPatterns = {"/Tienda"})
public class CTienda extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession ses = request.getSession();
        if(ses.getAttribute("usuario")==null){
            response.sendRedirect("vistas/Login.jsp");
        }
        else{
            String menu = request.getParameter("menu");
            String accion = request.getParameter("accion");
            String idtienda="";
            if(accion == null){
                accion = "Listar";
            }
            TiendaDAO tdao = new TiendaDAO();
            Tienda ti = new Tienda();
            switch (accion){
                case "Listar":
                    int id = Integer.parseInt(ses.getAttribute("id").toString());
                    List lista = tdao.listarTiendas(id);
                    request.setAttribute("tiendas", lista);
                    request.getRequestDispatcher("vistas/Tienda.jsp").forward(request, response);
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

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
