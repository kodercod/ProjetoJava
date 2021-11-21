<%-- 
    Document   : pai
    Created on : 27/11/2019, 10:52:35
    Author     : Luis Silva
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<jsp:include page="/header.jsp"/>
<jsp:include page="/menu.jsp"/>

<div class="container-fluid">
    <h1 class="h3 mb-2 text-gray-800">Pai</h1>
    <div class="card shadow mb-4">
         <div class="card-header py-3">Lista de Pai</div>
         <div class="card-body">
             <div class="table-responsive">
        <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
            <thead>                       
                <tr>
                    <th align="center">ID</th>                    
                    <th align="center">Nome</th>
                    <th align="center">Usuario</th>
                    <th align="center">Senha</th>
                    <th align="center">CPF</th>
                    <th align="center">Observacao</th>
                    <th align="center">Relatorio</th>
                    <th align="center" colspan="3">Editar</th>
                </tr>
            </thead>                                 
            <tbody>
                <c:forEach var="pai" items="${pais}">                    
                    <tr>
                         <td align="center">${pai.idPai}</td>                    
                        <td align="center">${pai.nome}</td>
                        <td align="center">${pai.usuario}</td>
                        <td align="center">${pai.senha}</td>
                        <td align="center">${pai.cpf}</td>
                        <td align="center">${pai.observacao}</td>
                        <td align="center">${pai.relatorio.idRelatorio}</td>
                        <td align="center">
                              <a href="${pageContext.request.contextPath}/PaiExcluir?idPai=${pai.idPai}">
                    <button type="button" class="btn btn-danger"><c:out value="${pai.situacao == 'A' ? 'Inativar': 'Ativar'}"/>
                    </button></a> 
                        </td>
                        
                        <td align="center">
                              <a href=
 "${pageContext.request.contextPath}/PaiCarregar?idPai=${pai.idPai}">
                                  <button type="button" class="btn btn-primary">Alterar</button>
                             </a></td>
                
                    </tr>                
                </c:forEach>
            </tbody>
        </table>
        <div align="center">        
            <a href="${pageContext.request.contextPath}/PaiNovo"><button type="button" class="btn btn-primary">Novo</button></a>
            
            <a href="index.jsp"><button type="button" class="btn btn-primary">Voltar à Página Inicial</button></a>
        </div>
            
        
             </div>
         </div>
       </div>
      </div>
</div>
<%@ include file="/footer.jsp" %>        

