<%-- 
    Document   : Tienda
    Created on : 23 oct. 2021, 21:35:30
    Author     : Alizr
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="entidades.Tienda"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%>
<!DOCTYPE html>
<html>
<%
        HttpSession ses = request.getSession();
        if (ses.getAttribute("usuario") == null) {
            response.sendRedirect("vistas/Login.jsp");
        }
    %>

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <link rel="stylesheet" href="css/bootstrap.min.css">
  <link rel="stylesheet" href="css/all.min.css">
  <link rel="stylesheet" href="https://unpkg.com/leaflet@1.7.1/dist/leaflet.css"
    integrity="sha512-xodZBNTC5n17Xt2atTPuE1HxjVMSvLVW9ocqUKLsCC5CXdbqCmblAshOMAS6/keqq/sMZMZ19scR4PsZChSR7A=="
    crossorigin="" />
  <title>Tienda</title>
  <style>
    #map {
      height: 500px;
      width: 100%;
    }
  </style>
</head>

<body>
  <jsp:include page="Header.jsp"></jsp:include>
  <div class="container">
    <div class="row mt-3">
      <%
        List<Tienda> datos = new ArrayList<Tienda>();
        datos = (List<Tienda>) request.getAttribute("tiendas");
        String mensaje = "";
        if (datos.size() < 1) {
            mensaje = "No ha Tiendas";
      %>
      <div class="text-center">No hay Tiendas que Mostrar
        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
          Launch demo modal
        </button>
      </div>
      <%
        } else {
          for (Tienda ti : datos) {
        %>

      <div class="card mb-3 mx-auto">
        <div class="card-body">
          <div class="row g-0">
            <div class="col-md-6">
              <div class="text-center">
                <img src="images/tienda.jpg" alt="" class="w-75">
                <div class="mt-4">

                  <h5 class="card-title"><%=ti.getNombre()%> </h5>
                  <p class="lead">RUC : 
                    <span class="badge bg-primary"><%=ti.getRuc()%></span> 
                  </p>
                  <p class="card-text"><%=ti.getIddistrito()%></p>
                  <p class="card-text"><small class="text-muted">Dirección: <%=ti.getDireccion()%></small></p>
                  <a  href="Tienda?accion=Editar&idtienda=<%=ti.getId()%>" class="btn btn-dark" id="btnEditar"><i class="fas fa-edit"></i> Editar Tienda</a>
                </div>
              </div>
            </div>
            <div class="col-md-6">
              <div id="map">
              </div>
            </div>
          </div>
        </div>
      </div>

      <%
        }
      }
      %>

    </div>

    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog modal-xl">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
            <div class="row">
              <div class="col-md-4">
                <form action="" id="frmDireccion">
                  <div class="mb-2">
                    <label for="cbodistrito">Distrito</label>
                    <select id="cbodistrito" name="cbodistrito" class="form-select form-select-sm">
                      <option id="op1" value="0">Selecciona una opcion</option>
                    </select>
                  </div>
                  <div class="mb-2">
                    <label for="txtdireccion">Dirección</label>
                    <input type="text" id="txtdireccion" name="txtdireccion" class="form-control form-control-sm"
                      placeholder="Dirección">
                  </div>
                  <div class="mb-2">
                    <label for="txtlatitud">Latitud</label>
                    <input type="text" id="txtlatitud" name="txtlatitud" class="form-control form-control-sm"
                      placeholder="Latitud">
                  </div>
                  <div class="mb-2">
                    <label for="txtlongitud">Logitud</label>
                    <input type="text" id="txtlongitud" name="txtlongitud" class="form-control form-control-sm"
                      placeholder="Logitud">
                  </div>
                  <div class="mb-2">
                    <button name="Registrar" type="submit" class="btn btn-primary btn-sm">Guardar Tienda</button>
                  </div>
                </form>
              </div>
              <div class="col-md-8">
                <div id="map">
                </div>
              </div>
            </div>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            <button type="button" class="btn btn-primary">Save changes</button>
          </div>
        </div>
      </div>
    </div>
  </div>
  <script src="js/bootstrap.min.js"></script>
  <script src="js/leaflet.js"></script>
</body>

</html>

<script type="text/javascript">
  document.addEventListener("DOMContentLoaded", () => {
    cargarlista();
  })
  const ubicaciones = {
    1: {
      id: 1,
      nombre: "Selva Alegre",
      ubi: "-16.379814203774284,-71.52087539434434"
    },
    2: {
      id: 2,
      nombre: "Yanahuara",
      ubi: "-16.379814203774284,-71.52087539434434"
    },
    3: {
      id: 3,
      nombre: "Miraflores",
      ubi: "-16.394213, -71.522319"
    }
  }
  let map = L.map('map', {
    center: [-16.39881796103844, -71.53694182634355],
    zoom: 12,
    // doubleClickZoom:false,zoomControl:false
  });

  const cargarlista = () => {
    let lista = document.querySelector("#cbodistrito");
    let op = "";
    Object.values(ubicaciones).forEach((element) => {
      op = op + "<option value='" + element.id + "'>" + element.nombre + "</option>";
    });
    lista.innerHTML = op;
  }

  L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
  }).addTo(map);

  L.marker([-16.39881796103844, -71.53694182634355]).addTo(map);

  document.getElementById('cbodistrito').addEventListener('change', function (e) {
    let coords = ubicaciones[e.target.value].ubi.split(",");
    L.marker(coords).addTo(map)
    map.flyTo(coords, 15);
  });
  map.on('click', onMapClick);

  function onMapClick(click) {
    var coordenada = click.latlng;
    var latitud = coordenada.lat; // lat  es una propiedad de latlng
    var longitud = coordenada.lng; // lng también es una propiedad de latlng
    // alert("Acabas de hacer clic en: \n latitud: " + latitud + "\n longitud: " + longitud);
    document.querySelector("#txtlatitud").value = latitud;
    document.querySelector("#txtlongitud").value = longitud;
    L.marker([latitud, longitud]).addTo(map);
  };
  
  // const btnEditar = document.querySelector("#btnEditar");
  // btnEditar.addEventListener("click",(e)=>{
  //     e.preventDefault();
  //     fetch("Tienda",{
  //       method:"POST",
  //       body:JSON.stringify({accion:"Editar"})
  //     })
  //     .then(res=>res.text())
  //     .then(res => console.log(res))
  // });
</script>