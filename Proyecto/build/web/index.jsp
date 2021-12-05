<%-- 
    Document   : index
    Created on : 16 oct. 2021, 12:05:27
    Author     : Alizr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%>
<!DOCTYPE html>
<html>
    <%
        HttpSession ses = request.getSession();
    if (ses.getAttribute("usuario") ==null) {
            response.sendRedirect("vistas/Login.jsp");
        }else{
            request.getRequestDispatcher("vistas/Principal.jsp").forward(request, response);
    }
    %>
   
</html>
