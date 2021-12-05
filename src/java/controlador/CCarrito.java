package controlador;

import entidades.Articulo;
import entidades.Producto;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;
import modelo.ProductoDAO;
import org.json.simple.JSONObject;

@WebServlet(name = "Carrito", urlPatterns = {"/Carrito"})
public class CCarrito extends HttpServlet {

  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    HttpSession ses = request.getSession();
    if (ses.getAttribute("usuario") == null) {
      response.sendRedirect("vistas/Login.jsp");
    } else {
      String opcion = request.getParameter("opcion");
      if (opcion == null) {
        opcion = "vistacarrito";
      }

      switch (opcion) {
        case "vistacarrito":
          request.getRequestDispatcher("vistas/Carrito.jsp").forward(request, response);
          break;
        case "mostrarcarrito":
          mostrarCarrito(request, response);
          break;
        case "addcarrito":
          agregarCarrito(request, response);
          break;
        case "delcarrito":
          eliminarItem(request, response);
          break;
      }
      //response.sendRedirect("vistas/Carrito.jsp");
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

  private void mostrarCarrito(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    PrintWriter out = null;
    HttpSession sesion = request.getSession();
    out = response.getWriter();
    List<Articulo> articulos = sesion.getAttribute("carrito") == null ? new ArrayList<>() : (ArrayList) sesion.getAttribute("carrito");
    JSONObject jsonall = new JSONObject();
    if (articulos.size() > 0) {
      double total = 0;
      for (Articulo a : articulos) {
        JSONObject json = new JSONObject();
        Producto p = new ProductoDAO().getProductoID(a.getIdProducto());
        total = Math.round(a.getCantidad() * p.getPrecioprod());
        json.put("idpro", p.getIdproducto());
        json.put("idtienda", p.getIdtienda());
        json.put("idcate", p.getIdcate());
        json.put("nombre", p.getNombreprod());
        json.put("descrip", p.getDescriprod());
        json.put("marca", p.getMarcaprod());
        json.put("presen", p.getPresenprod());
        json.put("precio", p.getPrecioprod());
        json.put("stock", p.getStockprod());
        json.put("estado", p.getEstadoprod());
        json.put("cantidad", a.getCantidad());
        json.put("total", total);
        jsonall.put(p.getIdproducto(), json);
      }
    } else {
    }
    out.print(jsonall.toString());
    out.close();
    request.getRequestDispatcher("vistas/Carrito.jsp").forward(request, response);
  }

  private void eliminarItem(HttpServletRequest request, HttpServletResponse response) throws IOException {
    PrintWriter out = response.getWriter();
    int idproducto = Integer.parseInt(request.getParameter("idproducto"));
    HttpSession carsesion = request.getSession();
    //JOptionPane.showMessageDialog(null, carsesion.getAttribute("carrito"));
    List<Articulo> articulos = carsesion.getAttribute("carrito") == null ? null : (List) carsesion.getAttribute("carrito");
    if (articulos != null) {
      for (Articulo a : articulos) {
        if (a.getIdProducto() == idproducto) {
          articulos.remove(a);
          break;
        }
      }
    }
    JSONObject datojson = new JSONObject();
    datojson.put("estado", "OK");
    out.print(datojson.toString());
    out.close();
  }

  private void agregarCarrito(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession sesion = request.getSession();
    int cantidad = Integer.parseInt(request.getParameter("txtcantidad"));
    int idproducto = Integer.parseInt(request.getParameter("txtid"));

    ArrayList<Articulo> articulos = sesion.getAttribute("carrito") == null ? new ArrayList<>() : (ArrayList) sesion.getAttribute("carrito");
    boolean flag = false;
    if (articulos.size() > 0) {
      for (Articulo a : articulos) {
        if (idproducto == a.getIdProducto()) {
          a.setCantidad(a.getCantidad() + cantidad);
          flag = true;
          break;
        }
      }
    }
    if (!flag) {
      articulos.add(new Articulo(idproducto, cantidad));
    }
    sesion.setAttribute("carrito", articulos);
    request.getRequestDispatcher("vistas/Carrito.jsp").forward(request, response);
  }
}
