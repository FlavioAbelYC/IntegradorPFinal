<%@page import="java.util.List"%>
<%@page import="entidades.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%>
<!DOCTYPE html>
<html>

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <link rel="stylesheet" href="css/bootstrap.min.css">
  <link rel="stylesheet" href="css/all.min.css">
  <link rel="stylesheet" href="https://unpkg.com/leaflet@1.7.1/dist/leaflet.css"
    integrity="sha512-xodZBNTC5n17Xt2atTPuE1HxjVMSvLVW9ocqUKLsCC5CXdbqCmblAshOMAS6/keqq/sMZMZ19scR4PsZChSR7A=="
    crossorigin="" />
  <title>Usuarios</title>

</head>

<body>
  <jsp:include page="Header.jsp"></jsp:include>
  <div class="container mt-3">
    <button class="btn btn-outline-primary">Agregar Nuevo</button>
    <div class="table-responsive">
      <table class="table table-bordered table-sm text-nowrap">
        <thead>
          <tr>
            <th>ID</th>
            <th>NOMBRES</th>
            <th>APELLIDOS</th>
            <th>DIRECCION</th>
            <th>TIPO</th>
            <th>OPCIONES</th>
          </tr>
        </thead>
        <tbody>
  
          <%
          List<Usuario> datos = new ArrayList<Usuario>();
              datos = (List<Usuario>)request.getAttribute("usuarios");
                  for (Usuario u : datos){
                      String clase = "";
                      if(u.getIdprivilegio() == 1){
                          clase = "bg-primary";
                      }
                      if(u.getIdprivilegio() == 2){
                          clase = "bg-danger";
                      }
                      if(u.getIdprivilegio() == 3){
                          clase = "bg-success";
                      }
          %>
            <tr>
              <td><%=u.getIdusuario()%></td>
              <td><%=u.getNombres()%></td>
              <td><%=u.getApellidos()%></td>
              <td><%=u.getDireccion()%></td>
              <td class="text-center"><span class="badge <%=clase%>"> <%=u.getNombreprivilegio()%></span></td>
              <td class="text-center">
                  <a href="#"><i class="fa fa-edit"></i></a>
                  <a href="#"><i class="fa fa-trash-alt text-danger"></i></a>
              </td>
            </tr>
          <%}%>
        </tbody>
      </table>
    </div>
  </div>
  <script src="js/bootstrap.min.js"></script>
  
</body>

</html>
