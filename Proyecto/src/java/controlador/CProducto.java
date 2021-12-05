/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.Categoria;
import entidades.Producto;
import entidades.Tienda;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.ProductoDAO;
import modelo.TiendaDAO;
import org.json.simple.JSONObject;

/**
 *
 * @author Alizr
 */
@WebServlet(name = "CProducto", urlPatterns = {"/Producto"})
public class CProducto extends HttpServlet {

  ProductoDAO pdao = new ProductoDAO();

  TiendaDAO tdao = new TiendaDAO();

  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    HttpSession ses = request.getSession();
    try (PrintWriter out = response.getWriter()) {
      if (ses.getAttribute("usuario") == null) {
        response.sendRedirect("vistas/Login.jsp");
      } else {
        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");
        if (accion == null) {
          accion = "Listar";
        }

        switch (accion) {
          case "Listar":
            //mostrarDatos(request, response);
            request.getRequestDispatcher("vistas/Productos.jsp").forward(request, response);
            break;
          case "ListarID":
            mostrarDatos(request, response);
            break;
          case "listarCate":
            mostrarCategorias(request, response);
            break;
          case "Guardar":
            registrarProducto(request, response);
            break;
          case "Actualizar":
            actualizarProducto(request, response);
            break;
          case "TiendaID":
            IDTienda(request, response);
            break;
          case "ProductoID":
            EditarProducto(request, response);
            break;
          case "Eliminar":
            eliminarProducto(request, response);
            break;
          default:
            request.getRequestDispatcher("vistas/Principal.jsp").forward(request, response);
            break;
        }
      }
    } catch (Exception e) {
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

  private void mostrarDatos(HttpServletRequest request, HttpServletResponse response) throws ServletException {
    PrintWriter out = null;
    HttpSession ses = request.getSession();
    Tienda t = new Tienda();
    try {
      out = response.getWriter();

      int id = Integer.parseInt(ses.getAttribute("id").toString());
      t = tdao.TiendaID(id);
      List<Producto> lista = pdao.allProductosTienda(id);
      JSONObject li = new JSONObject();
      for (Producto p : lista) {
        JSONObject json = new JSONObject();

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
        li.put(p.getIdproducto(), json);
      }

      out.print(li.toString());
      out.close();
      request.setAttribute("idusu", t.getIdus());
      request.getRequestDispatcher("vistas/Productos.jsp").forward(request, response);
    } catch (IOException ex) {
      Logger.getLogger(CProducto.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      out.close();
    }
  }

  private void mostrarCategorias(HttpServletRequest request, HttpServletResponse response) throws ServletException {
    PrintWriter out = null;
    HttpSession ses = request.getSession();
    try {
      out = response.getWriter();
      List<Categoria> lista = pdao.listarCategorias();

      JSONObject li = new JSONObject();
      for (Categoria cat : lista) {
        JSONObject json = new JSONObject();

        json.put("idcate", cat.getIdcategoria());
        json.put("catego", cat.getCategoria());
        li.put(cat.getIdcategoria(), json);
      }
      out.print(li.toString());
      out.close();

      request.getRequestDispatcher("vistas/Productos.jsp").forward(request, response);
    } catch (IOException ex) {
      Logger.getLogger(CProducto.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      out.close();
    }
  }

  private void registrarProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException {
    PrintWriter out = null;
    try {
      out = response.getWriter();
      JSONObject json = new JSONObject();

      int idtienda = Integer.parseInt(request.getParameter("idtienda"));
      int cate = Integer.parseInt(request.getParameter("cbocategoria"));
      String nombre = request.getParameter("txtnombre");
      String descri = request.getParameter("txtdescripcion");
      String marca = request.getParameter("txtmarca");
      String presen = request.getParameter("txtpresentacion");
      Float precio = Float.parseFloat(request.getParameter("txtprecio"));
      int stock = Integer.parseInt(request.getParameter("txtstock"));
      int estado = Integer.parseInt(request.getParameter("cboestado"));
      Producto p = new Producto();
      p.setIdtienda(idtienda);
      p.setIdcate(cate);
      p.setNombreprod(nombre);
      p.setDescriprod(descri);
      p.setMarcaprod(marca);
      p.setPresenprod(presen);
      p.setPrecioprod(precio);
      p.setStockprod(stock);
      p.setEstadoprod(estado);
      if (pdao.addProducto(p) == 1) {
        json.put("estado", "OK");
        json.put("mensaje", "Producto Agregado Correctamente");
      } else {
        json.put("estado", "FAIL");
        json.put("mensaje", "Producto No se pudo Agregar");

      }
      out.print(json.toString());
      out.close();
      request.getRequestDispatcher("vistas/Productos.jsp").forward(request, response);
    } catch (IOException ex) {
      Logger.getLogger(CProducto.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      out.close();
    }
  }
  private void actualizarProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException {
    PrintWriter out = null;
    try {
      out = response.getWriter();
      JSONObject json = new JSONObject();

      int idprod = Integer.parseInt(request.getParameter("txtID"));
      int idtienda = Integer.parseInt(request.getParameter("idtienda"));
      int cate = Integer.parseInt(request.getParameter("cbocategoria"));
      String nombre = request.getParameter("txtnombre");
      String descri = request.getParameter("txtdescripcion");
      String marca = request.getParameter("txtmarca");
      String presen = request.getParameter("txtpresentacion");
      Float precio = Float.parseFloat(request.getParameter("txtprecio"));
      int stock = Integer.parseInt(request.getParameter("txtstock"));
      int estado = Integer.parseInt(request.getParameter("cboestado"));
      Producto p = new Producto();
      p.setIdproducto(idprod);
      p.setIdtienda(idtienda);
      p.setIdcate(cate);
      p.setNombreprod(nombre);
      p.setDescriprod(descri);
      p.setMarcaprod(marca);
      p.setPresenprod(presen);
      p.setPrecioprod(precio);
      p.setStockprod(stock);
      p.setEstadoprod(estado);
      if (pdao.updateProducto(p) == 1) {
        json.put("estado", "OK");
        json.put("mensaje", "Producto Actualizado Correctamente");
      } else {
        json.put("estado", "FAIL");
        json.put("mensaje", "Producto No se pudo Actualizar");

      }
      out.print(json.toString());
      out.close();
      request.getRequestDispatcher("vistas/Productos.jsp").forward(request, response);
    } catch (IOException ex) {
      Logger.getLogger(CProducto.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      out.close();
    }
  }
  private void eliminarProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException {
    PrintWriter out = null;
    try {
      out = response.getWriter();
      JSONObject json = new JSONObject();
      
      int idprod = Integer.parseInt(request.getParameter("idproducto"));
      if (pdao.deleteProducto(idprod)) {
        json.put("estado", "OK");
        json.put("mensaje", "Producto Eliminado Correctamente");
      } else {
        json.put("estado", "FAIL");
        json.put("mensaje", "Producto No se pudo Eliminar");

      }
      out.print(json.toString());
      out.close();
      request.getRequestDispatcher("vistas/Productos.jsp").forward(request, response);
    } catch (IOException ex) {
      Logger.getLogger(CProducto.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      out.close();
    }
  }

  private void EditarProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException {
    PrintWriter out = null;
    HttpSession ses = request.getSession();
    try {
      out = response.getWriter();
      JSONObject json = new JSONObject();
      int idusu = Integer.parseInt(ses.getAttribute("id").toString());
      int idpro = Integer.parseInt(request.getParameter("idproducto"));
      Producto pro = pdao.getProductoID(idpro);
      if (pro != null) {
        json.put("idpro", pro.getIdproducto());
        json.put("idtien", pro.getIdtienda());
        json.put("idcate", pro.getIdcate());
        json.put("nombre", pro.getNombreprod());
        json.put("descri", pro.getDescriprod());
        json.put("marca", pro.getMarcaprod());
        json.put("presen", pro.getPresenprod());
        json.put("precio", pro.getPrecioprod());
        json.put("stock", pro.getStockprod());
        json.put("estado", pro.getEstadoprod());
        json.put("cate", pro.getCategoria());
      }
      out.print(json.toString());
      out.close();
      request.getRequestDispatcher("vistas/Productos.jsp").forward(request, response);
    } catch (IOException ex) {
      Logger.getLogger(CProducto.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      out.close();
    }
  }

  private void IDTienda(HttpServletRequest request, HttpServletResponse response) throws ServletException {
    PrintWriter out = null;
    HttpSession ses = request.getSession();
    try {
      out = response.getWriter();
      JSONObject json = new JSONObject();
      Tienda t = new Tienda();
      int id = Integer.parseInt(ses.getAttribute("id").toString());
      t = tdao.TiendaID(id);
      int dato = 0;
      if (t != null) {
        dato = t.getId();
      }
      json.put("idusu", dato);
      out.print(json.toString());
      out.close();
      request.getRequestDispatcher("vistas/Productos.jsp").forward(request, response);
    } catch (IOException ex) {
      Logger.getLogger(CProducto.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      out.close();
    }
  }
}
