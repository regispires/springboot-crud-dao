<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<form:form method="post" modelAttribute="contato" action="${url_base}${acao}">
	<form:input path="id" type="hidden"  />
	
	<spring:bind path="nome">
	<div class="form-group ${status.error ? 'has-error' : ''}">
		<form:label path="nome">Nome</form:label>
		<form:input path="nome" type="text" cssClass="form-control" />
		<form:errors path="nome" />
	</div>
	</spring:bind>
	
	<spring:bind path="email">
	<div class="form-group ${status.error ? 'has-error' : ''}">
		<form:label path="email">Email</form:label>
		<form:input path="email" type="text" cssClass="form-control" />
		<form:errors path="email" />
	</div>
	</spring:bind>
	
	<spring:bind path="endereco">
	<div class="form-group ${status.error ? 'has-error' : ''}">
		<form:label path="endereco">Endereço</form:label>
		<form:input path="endereco" type="text" cssClass="form-control" />
		<form:errors path="endereco" />
	</div>
	</spring:bind>
	<button type="submit" class="btn btn-primary">Salvar</button>
</form:form>
