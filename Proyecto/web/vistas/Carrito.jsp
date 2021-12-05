<%@page import="java.util.List"%>
<%@page import="javax.swing.JOptionPane"%>
<%@page import="entidades.Producto"%>
<%@page import="modelo.ProductoDAO"%>
<%@page import="entidades.Articulo" %>
<%@page import="java.util.ArrayList" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<jsp:include page="Header.jsp"></jsp:include>
<div class="container mt-3">

  <div class="table-responsive">
    <table class="table table-bordered">
      <thead>
        <tr>
          <th>Item</th>
          <th>Descripcion</th>
          <th>Precio</th>
          <th>Cantidad</th>
          <th>Total</th>
          <th>Opcion</th>
        </tr>
      </thead>
      <tbody id="contCarrito">
      </tbody>
      <tfoot>
        <tr>
          <td class="text-danger text-end" colspan="4">TOTAL de Pago</td>
          <td class="text-danger" colspan="2" id="txttotal">S/. 0.00</td>
        </tr>
      </tfoot>

    </table>
  </div>
</div>
<jsp:include page="Footer.jsp"></jsp:include>

<script>
  document.addEventListener("DOMContentLoaded",()=>{
    mostrarDatos();
  })
  const contCarrito = document.querySelector("#contCarrito");
  contCarrito.addEventListener("click",(e)=>{
    if(e.target.classList.contains("fa-trash")){
      e.preventDefault();
      let id =e.target.dataset.id;
      $.ajax({
        url:"Carrito",
        type:"POST",
        data:{opcion:"delcarrito",idproducto:id},
        success:function(res){
          console.log(res);
          mostrarDatos();
        }
      })
    }
    e.stopPropagation();
  })
  // $("tr #delProducto").click(function (e) {
  //   var elemento = $(this);
  //   var id = elemento.parent().find("#idproducto").text();

  //   console.log(contCarrito.length);
  //   $.ajax({
  //     url: "Carrito",
  //     type: "POST",
  //     data: {
  //       opcion: "delcarrito",
  //       idproducto: id
  //     },
  //     success: function (res) {
  //       elemento.parent().parent().remove();
  //     }
  //   })
  // })
  const mostrarDatos = () => {
    $.ajax({
      url: "Carrito",
      type: "POST",
      dataType: 'json',
      data: {
        opcion: "mostrarcarrito"
      },
      success: function (res) {
        contCarrito.innerHTML="";
        if (Object.keys(res).length === 0) {
          contCarrito.innerHTML="<tr><td colspan='6' class='text-danger text-center'>El carrito está vacio</td></tr>";
          document.querySelector("#txttotal").textContent = "S/. 0.00";
        }else{
          let contenido = "";
          cont = 1;
          t = 0;
          Object.values(res).forEach(c => {
            contenido+=`<tr>
              <td>`+cont+`</td>
              <td>`+c.nombre+`</td>
              <td>`+c.precio.toFixed(2)+`</td>
              <td>`+c.cantidad+`</td>
              <td>`+parseFloat(c.cantidad * c.precio).toFixed(2)+`</td>
              <td class="text-center"><i class="fa fa-trash btn btn-danger btn-sm" data-id="`+c.idpro+`"></i></td>
              </tr>`;
            t += parseFloat(c.precio * c.cantidad);
            cont+=1;
          });
          document.querySelector("#txttotal").textContent = "S/. "+parseFloat(t).toFixed(2);
          contCarrito.innerHTML= (contenido);
        }
      }
    })
  }
</script>