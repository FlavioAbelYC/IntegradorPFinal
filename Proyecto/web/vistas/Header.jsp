<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%>
<%
  HttpSession s = request.getSession();
  int id = Integer.parseInt(s.getAttribute("privilegio").toString());
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/all.min.css">
        <title>Marketplace</title>

    </head>

    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container-fluid">
                <a class="navbar-brand" href="${pageContext.request.contextPath}/Principal">MARKETPLACE</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                        aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/Tienda">Mi Tienda</a>
                        </li>
                        <% if (id == 1) { %>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/Usuario">Usuarios</a>
                        </li>
                        <% }
                          if (id == 1 || id == 2) {
                        %>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/Producto">Mis Productos </a>
                        </li>
                        <%}%>
                        
                    </ul>
                    <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/Carrito">Carrito </a>
                        </li>
                        <li class="nav-item dropdown">
                            <%
                              String clase = "";
                              if (id == 1) {
                                clase = "bg-primary";
                              }
                              if (id == 2) {
                                clase = "bg-danger";
                              }
                              if (id == 3) {
                                clase = "bg-success";
                              }
                            %>
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                               data-bs-toggle="dropdown" aria-expanded="false">
                                ${usuario} <span  class="badge <%=clase%>">${nombrepri}</span>
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li><a class="dropdown-item" href="#">${usuario} </a></li>
                                <li><a class="dropdown-item" href="#">Editar Perfil</a></li>
                                <li>
                                    <hr class="dropdown-divider">
                                </li>
                                <li><a class="dropdown-item" href="#">Something else here</a></li>
                            </ul>
                        </li>
                        <li class="nav-item">
                            <a href="${pageContext.request.contextPath}/Login?accion=cerrar" class="nav-link">Cerrar</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
