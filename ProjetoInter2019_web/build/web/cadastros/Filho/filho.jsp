<%-- 
    Document   : filho
    Created on : 27/11/2019, 17:33:25
    Author     : Luis Silva
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<jsp:include page="/header.jsp"/>
<jsp:include page="/menu.jsp"/>

<div class="container-fluid">
    <h1 class="h3 mb-2 text-gray-800">Filho</h1>
    <div class="card shadow mb-4">
         <div class="card-header py-3">Lista de Filho</div>
         <div class="card-body">
             <div class="table-responsive">
        <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
            <thead>                       
                <tr>
                    <th align="center">ID</th>                    
                    <th align="center">Nome</th>
                    <th align="center">Usuario</th>
                    <th align="center">Senha</th>
                    <th align="center">Descricao</th>
                    <th align="center" colspan="3">Editar</th>
                </tr>
            </thead>                                 
            <tbody>
                <c:forEach var="filho" items="${filhos}">                    
                    <tr>
                         <td align="center">${filho.idFilho}</td>                    
                        <td align="center">${filho.nome}</td>
                        <td align="center">${filho.usuario}</td>
                        <td align="center">${filho.senha}</td>
                        <td align="center">${filho.descricao}</td>
                        <td align="center">
                               <a href="${pageContext.request.contextPath}/FilhoExcluir?idFilho=${filho.idFilho}">
                    <button type="button" class="btn btn-danger"><c:out value="${filho.situacao == 'A' ? 'Inativar': 'Ativar'}"/>
                        </td>
                        
                        <td align="center">
                              <a href=
 "${pageContext.request.contextPath}/FilhoCarregar?idFilho=${filho.idFilho}">
                                  <button type="button" class="btn btn-primary">Alterar</button>
                             </a></td>
                    </tr>                
                </c:forEach>
            </tbody>
        </table>
        <div align="center">        
            <a href="${pageContext.request.contextPath}/FilhoNovo"><button type="button" class="btn btn-primary">Novo</button></a>
            <a href="index.jsp"><button type="button" class="btn btn-primary">Voltar à Página Inicial</button></a>
        </div>
            
        
             </div>
         </div>
       </div>
      </div>
</div>
<%@ include file="/footer.jsp" %>        
