<%-- 
    Document   : estado
    Created on : 02/09/2019, 08:49:15
    Author     : luissilva
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<jsp:include page="/header.jsp"/>
<jsp:include page="/menu.jsp"/>

<div class="container-fluid">
    <h1 class="h3 mb-2 text-gray-800">Relatorios</h1>
    <div class="card shadow mb-4">
         <div class="card-header py-3">Lista de Relatorios</div>
         <div class="card-body">
             <div class="table-responsive">
        <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
            <thead>                       
                <tr>
                    <th align="center">ID</th>                    
                    <th align="center">Data</th>
                    <th align="center">Hora</th>
                    <th align="center">Programas</th>
                    <th align="center">Sites</th> 
                    <th align="center" colspan="3">Editar</th>
                </tr>
            </thead>                                 
            <tbody>
                <c:forEach var="relatorio" items="${relatorios}">                    
                    <tr>
                        <td align="center">${relatorio.idRelatorio}</td>                   
                        <td align="center">${relatorio.dataRelatorio}</td>
                        <td align="center">${relatorio.horaRelatorio}</td>
                        <td align="center">${relatorio.programas.nomePrograma}</td>
                        <td align="center">${relatorio.internet.nomePagina}</td>
                        <td align="center">
                               <a href=
 "${pageContext.request.contextPath}/RelatorioExcluir?idRelatorio=${relatorio.idRelatorio}">
                            <button type="button" class="btn btn-danger">Excluir</button></a></td>
                        
                        <td align="center">
                              <a href=
 "${pageContext.request.contextPath}/RelatorioCarregar?idRelatorio=${relatorio.idRelatorio}">
                                  <button type="button" class="btn btn-primary">Alterar</button>
                             </a></td>
                    </tr>                
                </c:forEach>
            </tbody>
        </table>
        <div align="center">        
            <a href="${pageContext.request.contextPath}/RelatorioNovo"><button type="button" class="btn btn-primary">Novo</button></a>
            <a href="index.jsp"><button type="button" class="btn btn-primary">Voltar à Página Inicial</button></a>
        </div>
            
        
             </div>
         </div>
       </div>
      </div>
</div>
<%@ include file="/footer.jsp" %>        
