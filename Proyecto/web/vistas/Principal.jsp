<%-- 
    Document   : Principal
    Created on : 22 oct. 2021, 06:07:25
    Author     : Alizr
--%>

<%@page import="java.util.List"%>
<%@page import="entidades.Producto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/all.min.css">
        <link rel="stylesheet" href="https://unpkg.com/leaflet@1.7.1/dist/leaflet.css"
              integrity="sha512-xodZBNTC5n17Xt2atTPuE1HxjVMSvLVW9ocqUKLsCC5CXdbqCmblAshOMAS6/keqq/sMZMZ19scR4PsZChSR7A==" crossorigin="" />
        <title>Marketplace</title>

    </head>

    <body>
        <jsp:include page="Header.jsp"></jsp:include>  
            <div class="container mt-3">
                <div class="row" id="">
                <%
                List<Producto> lista = (List<Producto>) request.getAttribute("productos");
                for (Producto pro : lista) {
                  
                %>
                <div class="col-md-3">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title"><%=pro.getNombreprod()%></h5>
                            <p class="card-text"><%=pro.getDescriprod()%></p>
                            <a href="Principal" class="btn btn-primary btn-sm"><i class="fa fa-shopping-bag"></i> Agregar</a>
                            <a href="vistas/DetalleProducto.jsp?id=<%=pro.getIdproducto()%>" class="btn btn-info btn-sm">Ver Detalle</a>
                        </div>
                    </div>
                </div>
                <%
                }
                %>
            </div>
        </div>
        <script src="js/bootstrap.min.js"></script>
        <template id="temProductos">
            <div class="col-md-3">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title"></h5>
                        <p class="card-text"></p>
                        <button class="btn btn-primary btn-sm">Agregar</button>
                    </div>
                </div>
            </div>
        </template>
    </body>

</html>
<script>
    const template = document.querySelector("#temProductos").content;
    const lista = document.querySelector("#listaProductos");
    const fragmento = new DocumentFragment();


    let datos = [
        {
            "titulo": "Agua Viva",
            "descripcion": "La Canción mas escuchada"
        },
        {
            "titulo": "Cielo Aviero",
            "descripcion": "Mi musica favorita para escuchar"
        },
        {
            "titulo": "Bajo la sombra",
            "descripcion": "Me hace recordar ltodo"
        },
        {
            "titulo": "So will I",
            "descripcion": "todo te entrego a ti"
        },
        {
            "titulo": "Con poder",
            "descripcion": "Milagros puend suceder"
        }

    ]
    lista.innerHTML = "";
    datos.forEach(dato => {
        const clone = template.cloneNode(true);
        clone.querySelector(".card-title").textContent = dato.titulo;
        clone.querySelector(".card-text").textContent = dato.descripcion;
        fragmento.appendChild(clone);
    });
    lista.appendChild(fragmento);
</script>

