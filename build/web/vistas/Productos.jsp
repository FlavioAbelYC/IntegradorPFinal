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
  <title>Productos</title>
</head>

<body>
  <jsp:include page="Header.jsp"></jsp:include>
  <div class="container mt-3">
    <button class="btn btn-primary" id="btnNuevoProducto"> <i class="fa fa-plus"></i>Nuevo Producto</button>
    <span id="mensajeTienda"></span>
    <div class="table-responsive mt-3">
      <table class="table table-bordered table-sm text-nowrap">
        <thead>
          <tr>
            <th>N°</th>
            <th>IdCate</th>
            <th>Nombres</th>
            <th>Descripcion</th>
            <th>Precio</th>
            <th>Stock</th>
            <th>Opciones</th>
          </tr>
        </thead>
        <tbody id="contProductos">
          <tr>
            <td colspan="7" id="mensaje" class="text-center text-danger"></td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>

  <!-- Modal -->
  <div class="modal fade" id="modalProductos" tabindex="-1" aria-labelledby="modalProductosLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="tituloModal"></h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <form id="frmProducto" autocomplete="off">
          <div class="modal-body">
            <div class="row">

              <div class="mb-2 col-6">
                <label for="txtID">ID</label>
                <input type="text" class="form-control form-control-sm" name="txtID" id="txtID" readonly>
              </div>
              <div class="mb-2 col-6">
                <label for="idtienda">IdTienda</label>
                <input type="text" class="form-control form-control-sm" name="idtienda" id="idtienda" readonly>
              </div>
              <div class="mb-2">
                <label for="txtnombre">Nombre</label>
                <input type="text" class="form-control form-control-sm" name="txtnombre" id="txtnombre"
                  placeholder="Nombre">
              </div>
              <div class="mb-2">
                <label for="txtpresentacion">Presentacion</label>
                <input type="text" class="form-control form-control-sm" name="txtpresentacion" id="txtpresentacion"
                  placeholder="Nombre">
              </div>
              <div class="mb-2 col-6">
                <label for="txtmarca">Marca</label>
                <input type="text" class="form-control form-control-sm" name="txtmarca" id="txtmarca"
                  placeholder="Marca">
              </div>
              <div class="mb-2 col-6">
                <label for="txtprecio">Precio</label>
                <input type="text" class="form-control form-control-sm" name="txtprecio" id="txtprecio"
                  placeholder="Precio">
              </div>
              <div class="mb-2 col-6">
                <label for="txtstock">Stock</label>
                <input type="text" class="form-control form-control-sm" name="txtstock" id="txtstock"
                  placeholder="Stock">
              </div>
              <div class="mb-2 col-6">
                <label for="cbocategoria">Categoría</label>
                <select type="combobox" class="form-select form-select-sm" name="cbocategoria" id="cbocategoria">
                </select>
              </div>
              <div class="mb-2 col-6">
                <label for="cboestado">Estado</label>
                <select type="combobox" class="form-select form-select-sm" name="cboestado" id="cboestado">
                  <option value="">Elige una Opcion</option>
                  <option value="1">Activo</option>
                  <option value="0">Inactivo</option>
                </select>
              </div>
              <div class="mb-2">
                <label for="txtdescripcion">Descripion</label>
                <textarea name="txtdescripcion" id="txtdescripcion" class="form-control form-control-sm"></textarea>
              </div>

            </div>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
            <button type="submit" class="btn btn-primary" id="btnGuardar"><span id="btnText">Guardar</span></button>
          </div>
        </form>
      </div>
    </div>
  </div>

  <template id="template-productos">
    <tr>
      <td></td>
      <td></td>
      <td></td>
      <td></td>
      <td></td>
      <td></td>
      <td>
        <i class="fa fa-edit text-primary" role="button"></i>
        <i class="fa fa-trash-alt text-danger" role="button"></i>
      </td>
    </tr>
  </template>
  <script src="js/jquery.js"></script>
  <script src="js/bootstrap.min.js"></script>
</body>

</html>
<script>
  document.addEventListener("DOMContentLoaded", () => {
    listarProductos();
  })
  const contProductos = document.querySelector("#contProductos");
  const frmProducto = document.querySelector("#frmProducto");
  const template = document.querySelector("#template-productos").content;
  const fragmento = new DocumentFragment();


  $("#btnNuevoProducto").click(function () {
    $("#tituloModal").text("Nuevo Producto");
    $("#btnText").text("Guardar");
    $("#modalProductos").modal("show");
    cargarCategorias();

  })
  frmProducto.addEventListener("submit", (e) => {
    e.preventDefault();
    let pro = new FormData(frmProducto);
    let producto = {};
    pro.forEach((p, index) => {
      producto[index] = p;
    });
    producto.accion = $("#btnText").text();
    console.log(producto);
    $.ajax({
      url: "Producto",
      type: 'POST',
      dataType: 'json',
      data: producto,
      success: function (res) {
        if (res.estado == "OK") {
          alert(res.mensaje);
        } else {
          alert(res.mensaje);
        }
        listarProductos();
      }
    });
  })

  contProductos.addEventListener("click", (e) => {
    if (e.target.classList.contains("fa-edit")) {
      const id = e.target.dataset.id;
      $("#tituloModal").text("Editar Producto");
      $("#btnText").text("Actualizar");
      cargarCategorias();
      datos = {
        accion: "ProductoID",
        idproducto: id
      };
      $.ajax({
        url: "Producto",
        type: 'POST',
        dataType: 'json',
        data: datos,
        success: function (res) {
          $("#txtID").val(res.idpro);
          $("#idtienda").val(res.idtien);
          $("#cbocategoria").val(res.idcate);
          $("#txtnombre").val(res.nombre);
          $("#txtdescripcion").val(res.descri);
          $("#txtmarca").val(res.marca);
          $("#txtpresentacion").val(res.presen);
          $("#txtprecio").val(res.precio);
          $("#txtstock").val(res.stock);
          $("#cboestado").val(res.estado);
        }
      });
      $("#modalProductos").modal("show");
    }
    if (e.target.classList.contains("fa-trash-alt")) {
      if (confirm("Estas seguro de eliminar?")) {
        const id = e.target.dataset.id;
        datos = {
          accion: "Eliminar",
          idproducto: id
        };
        $.ajax({
          url: "Producto",
          type: 'POST',
          dataType: 'json',
          data: datos,
          success: function (res) {
            console.log(res);
            if (res.estado == "OK") {
              alert(res.mensaje);
            } else {
              alert(res.mensaje);
            }
            listarProductos();
          }
        });
      }
    }
    e.stopPropagation();
  })

  function getTienda() {
    var bool = false;
    $.ajax({
      url: "Producto",
      type: 'POST',
      dataType: 'json',
      async: false,
      data: {
        accion: "TiendaID"
      },
      success: function (datos) {
        if (datos.idusu > 0) {
          $("#idtienda").val(datos.idusu);
          bool = true;
        }
      }
    });
    return bool;
  }
  const cargarCategorias = () => {
    $.ajax({
      url: "Producto",
      type: 'POST',
      dataType: 'json',
      data: {
        accion: "listarCate"
      },
      success: function (datos) {
        $("#cbocategoria").html("");
        Object.values(datos).forEach(c => {
          $('#cbocategoria').append($('<option>', {
            value: c.idcate,
            text: c.catego
          }));
        })
      }
    });
  }
  const listarProductos = () => {

    $.ajax({
      url: "Producto",
      type: 'POST',
      dataType: 'json',
      data: {
        accion: "ListarID"
      },
      success: function (datos) {
        console.log(getTienda());
        if (getTienda() == true) {

          if (Object.keys(datos).length === 0) {
            $("#mensaje").text("No tienes Productos");
          } else {
            contProductos.innerHTML = "";
            Object.values(datos).forEach(p => {
              const clonar = template.cloneNode(true);
              clonar.querySelectorAll("td")[0].textContent = p.idpro;
              clonar.querySelectorAll("td")[1].textContent = p.idcate;
              clonar.querySelectorAll("td")[2].textContent = p.nombre;
              clonar.querySelectorAll("td")[3].textContent = p.descrip;
              clonar.querySelectorAll("td")[4].textContent = p.precio;
              clonar.querySelectorAll("td")[5].textContent = p.stock;
              clonar.querySelector(".fa-edit").dataset.id = p.idpro;
              clonar.querySelector(".fa-trash-alt").dataset.id = p.idpro;


              fragmento.appendChild(clonar);
            });
            contProductos.appendChild(fragmento);
          }
        } else {
          $('#btnNuevoProducto').prop('disabled', true);
          $('#mensajeTienda').text("No puede agregar Productos - Primero crea su tienda").addClass(
            "text-danger");
        }
      }
    });
    // fetch("Producto?accion=ListarID")
    //   .then(res => JSON.parse(res))
    //   .then(res => console.log(res.nombre))
  }
</script>