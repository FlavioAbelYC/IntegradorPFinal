<%-- 
    Document   : DetalleProducto
    Created on : 5 dic. 2021, 07:39:20
    Author     : Alizr
--%>

<%@page import="entidades.Producto"%>
<%@page import="modelo.ProductoDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="Header.jsp"></jsp:include> 
    <h1>Hello Productos Detalle!</h1>
<%
  int id = Integer.parseInt(request.getParameter("id"));
  Producto p = new ProductoDAO().getProductoID(id);
  
%>
  <div class="conatiner">
    <div class="row">
      <div class="col-md-8 mx-auto">
        <div class="card">
            <div class="card-body">
                <div class="row">
                  <div class="col-md-6">
                    <img src="${pageContext.request.contextPath}/images/producto.png" alt="">
                  </div>
                  <div class="col-md-6">
                      <form action="${pageContext.request.contextPath}/Carrito" method="POST" id="frmAdd">

                      <h5 class="card-title"><%=p.getNombreprod()%></h5>
                      <p class="text-muted">WEB ID : <%=p.getIdproducto()%></p>
                      <p class="text-lead">CATEGORÍA : <%=p.getCategoria()%></p>
                      <p class="text-danger">Precio : S/. <%=p.getPrecioprod()%></p>
                      <p class="text-muted">STOCK : <%=p.getStockprod()%></p>
                      <div class="mb-2">
                        <input type="hidden" name="txtid" id="txtid" value="<%=p.getIdproducto()%>">
                        <input type="hidden" name="opcion" id="opcion" value="addcarrito">
                        <input type="number" name="txtcantidad" id="txtcantidad" class="form-control from-control-sm" value="1">
                      </div>
                      <button type="submit" class="btn btn-primary btn-sm"><i class="fa fa-shopping-bag"></i> Agregar</button>
                    </form>
                  </div>
                </div>
            </div>
        </div>
    </div>
    </div>
  </div>
<jsp:include page="Footer.jsp"></jsp:include> 
