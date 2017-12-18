<%-- 
    Document   : index
    Created on : 24/07/2017, 19:09:59
    Author     : Joao Pedro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>API LIfe Stories</title>
        
    </head>
    <body>
        <h1>Loading...</h1>
        
        <script>
            var url = window.location.href + 'swagger-ui.html';
            
            function redirect(){
               window.location.href = url; 
            };
            
            redirect();
        </script>
    </body>

</html>
