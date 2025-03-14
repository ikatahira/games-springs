<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Atualizar Jogo</title>
</head>
<body>
    <h1>Atualizar Jogo</h1>
    <form action="/jogo/update" method="post">
        <input type="hidden" name="id" value="${jogo.id}">
        
        <label for="titulo">Título:</label>
        <input type="text" id="titulo" name="titulo" value="${jogo.titulo}"><br><br>
        
        <label for="categoria">Categoria:</label>
        <select id="categoria" name="categoria">
            <c:forEach var="categoria" items="${categorias}">
                <option value="${categoria.id}" ${categoria.id == jogo.categoria.id ? 'selected' : ''}>
                    ${categoria.nome}
                </option>
            </c:forEach>
        </select><br><br>

        <label for="plataformas">Plataformas:</label><br>
        <c:forEach var="plataforma" items="${plataformas}">
            <input type="checkbox" id="plataforma${plataforma.id}" name="plataformas" value="${plataforma.id}"
                   <c:forEach var="jogoPlataforma" items="${jogo.plataformas}">
                       <c:if test="${jogoPlataforma.id == plataforma.id}">checked</c:if>
                   </c:forEach>>
            <label for="plataforma${plataforma.id}">${plataforma.nome}</label><br>
        </c:forEach><br>
        
        <button type="submit">Atualizar</button>
        <a href="/jogos">Cancelar</a>
    </form>
</body>
</html>