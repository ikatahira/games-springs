<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Inserir Jogo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">

</head>
<body>
    <h1>Inserir Jogo</h1>
    <form action="/jogo/insert" method="post">
        <label for="titulo">Título:</label>
        <input type="text" id="titulo" name="titulo"><br><br>
        
        <label for="categoria">Categoria:</label>
        <select id="categoria" name="categoria">
            <c:forEach var="categoria" items="${categorias}">
                <option value="${categoria.id}">${categoria.nome}</option>
            </c:forEach>
        </select><br><br>

        <label for="plataformas">Plataformas:</label><br>
        <c:forEach var="plataforma" items="${plataformas}">
            <input type="checkbox" id="plataforma${plataforma.id}" name="plataformas" value="${plataforma.id}">
            <label for="plataforma${plataforma.id}">${plataforma.nome}</label><br>
        </c:forEach><br>
        
        <button type="submit">Inserir</button>
        <a href="/jogos">Cancelar</a>
    </form>
</body>
</html>