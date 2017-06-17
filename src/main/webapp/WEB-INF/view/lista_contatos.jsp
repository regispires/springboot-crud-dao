<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:url var="url_base" value="/" />
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>Lista Contatos</title>
	<link rel="stylesheet" href="${url_base}css/bootstrap.min.css" />
</head>
<body>
<div class="container">
	<h1>Lista de Contatos</h1>
	<c:if test="${not empty msg}">
		<div class="alert alert-success alert-dismissible" role="alert">
		<button type="button" class="close" data-dismiss="alert" aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
		<p>${msg}</p>
		</div>
	</c:if>
	<a href="/contatos/add" class="btn btn-primary">Novo contato</a>
	<c:if test="${not empty contatos}">
	<table id="tabela_contatos" class="table table-hover">
		<thead>
			<tr><th>Nome</th><th>Email</th><th>Endereço</th><th>Ação</th></tr>
		</thead>
		<tbody>
		<c:forEach var="c" items="${contatos}">
			<tr><td>${c.nome}</td><td>${c.email}</td><td>${c.endereco}</td>
			<td><a href="${url_base}contatos/${c.id}/update" class="btn btn-primary">Altera</a></td>
			<td><a href="${url_base}contatos/${c.id}/delete" class="btn btn-danger" onclick="return confirm('Confirma remoção?')" >Deleta</a></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</c:if>
</div>
<script src="${url_base}js/jquery-3.2.1.min.js"></script>
<script src="${url_base}js/bootstrap.min.js"></script>
<script>
</script>
</body>
</html>