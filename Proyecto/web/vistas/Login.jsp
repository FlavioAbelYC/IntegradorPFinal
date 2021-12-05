<%-- 
    Document   : login
    Created on : 16 oct. 2021, 12:38:21
    Author     : Alizr
--%>

<%@page import="java.util.List"%>
<%@page import="entidades.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <link rel="stylesheet" href="../css/bootstrap.min.css">
  <link rel="stylesheet" href="../css/all.min.css">
  <title>Login</title>
</head>

<body>
  <div class="container mt-5">
    <div class="row">
      <div class="col-md-6 mx-auto">
        <div class="card">
          <div class="card-body">
            <ul class="nav nav-tabs nav-fill" id="myTab" role="tablist">
              <li class="nav-item" role="presentation">
                <button class="nav-link active" id="home-tab" data-bs-toggle="tab" data-bs-target="#home" type="button" role="tab" aria-controls="home" aria-selected="true">Iniciar Sesion</button>
              </li>
              <li class="nav-item" role="presentation">
                <button class="nav-link" id="profile-tab" data-bs-toggle="tab" data-bs-target="#profile" type="button" role="tab" aria-controls="profile" aria-selected="false">Registrarse</button>
              </li>
            </ul>
            <div class="tab-content" id="myTabContent">
              <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                <div class="row">
                  <div class="col-md-5">
                    <div class="w-100 h-100 d-flex justify-content-center align-items-center">
                      <i class="fa fa-user fa-5x text-dark"></i>
                    </div>
                  </div>
                  <div class="col-md-7">
                    <form action="../Login" method="post" id="frmLogin">
                      <div class="mb-2">
                        <label for="txtemail">Usuario</label>
                        <input type="text" name="txtemail" id="txtemail" class="form-control form-control-sm" placeholder="Usuario" required>
                      </div>
                      <div class="mb-2">
                        <label for="txtpassword">Password</label>
                        <input type="text" name="txtpassword" id="txtpassword" class="form-control form-control-sm" placeholder="Password" required>
                      </div>
                      <div class="mb-3">
                          <button class="btn btn-primary" type="submit" name="accion" value="login">Iniciar Sesion</button>
                      </div>
                    </form>
                      
                      <%
					List<Usuario> datos = new ArrayList<Usuario>();
                                        
					if (request.getAttribute("fail") != null) {
                                            out.println("<script>alert('datos no encontrados')</script>");
					}
				%>
                  </div>
                </div>
              </div>
              <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
                <form action="../Login" id="frmRegistrarse" method="POST">
                  <div class="my-2">
                    <label for="txtdni">DNI</label>
                    <input type="text" name="txtdni" id="txtdni" class="form-control form-control-sm" placeholder="DNI" required>
                  </div>
                  <div class="mb-2">
                    <label for="txtnombres">Nombres</label>
                    <input type="text" name="txtnombres" id="txtnombres" class="form-control form-control-sm" placeholder="Nombres" required>
                  </div>
                  <div class="mb-2">
                    <label for="txtapellidos">Apellidos</label>
                    <input type="text" name="txtapellidos" id="txtapellidos" class="form-control form-control-sm" placeholder="Apellidos" required>
                  </div>
                  <div class="mb-2">
                    <label for="txtdireccion">Direccion</label>
                    <input type="text" name="txtdireccion" id="txtdireccion" class="form-control form-control-sm" placeholder="Direccion" required>
                  </div>
                  <div class="mb-2">
                    <label for="txttelefono">Telefono</label>
                    <input type="text" name="txttelefono" id="txttelefono" class="form-control form-control-sm" placeholder="Telefono" required>
                  </div>
                  <div class="mb-2">
                    <label for="txtemail">Email</label>
                    <input type="text" name="txtemail" id="txtemail" class="form-control form-control-sm" placeholder="Email" required>
                  </div>
                  <div class="mb-2">
                    <label for="txtpassword">Contraseña</label>
                    <input type="text" name="txtpassword" id="txtpassword" class="form-control form-control-sm" placeholder="Contraseña" required>
                  </div>
                  <div class="row mb-2">
                    <div class="col-6">
                      <label for="cboprivilegio">Privilegio</label>
                      <select name="cboprivilegio" id="cboprivilegio" class="form-select" required>
                        <option value="">Elige Opcion</option>
                        <option value="2">Vendedor</option>
                        <option value="3">Comprador</option>
                      </select>
                    </div>
                    <div class="col-6">
                      <label for="cboestado">Estado</label>
                      <select name="cboestado" id="cboestado" class="form-select" required>
                        <option value="1">Activo</option>
                        <option value="0">Inactivo</option>
                      </select>
                    </div>
                  </div>
                  <div class="mb-2">
                    <button class="btn btn-outline-primary" type="submit" name="accion" value="registrarse">Registrarse</button>
                  </div>
                </form>
              </div>
            </div>
            
          </div>
        </div>
      </div>
    </div>
  </div>
  <script src="../js/bootstrap.min.js"></script>
</body>

</html>