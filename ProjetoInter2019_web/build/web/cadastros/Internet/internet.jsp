<%-- 
    Document   : internet
    Created on : 15/11/2019, 10:09:17
    Author     : Luis Silva
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<jsp:include page="/header.jsp"/>
<jsp:include page="/menu.jsp"/>

<div class="container-fluid">
    <h1 class="h3 mb-2 text-gray-800">Sites</h1>
    <div class="card shadow mb-4">
         <div class="card-header py-3">Lista de Sites</div>
         <div class="card-body">
             <div class="table-responsive">
        <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
            <thead>                       
                <tr>
                    <th align="center">ID</th>                    
                    <th align="center">Nome</th>
                    <th align="center">Endere?o</th>
                    <th align="center">Data</th>
                    <th align="center">Hora</th>
                    <th align="center" colspan="3">Editar</th>
                </tr>
            </thead>                                 
            <tbody>
                <c:forEach var="site" items="${sites}">                    
                    <tr>
                         <td align="center">${site.idInternet}</td>                    
                        <td align="center">${site.nomePagina}</td>
                        <td align="center">${site.enderecoPagina}</td>
                        <td align="center">${site.dataAcesso}</td>
                        <td align="center">${site.horaAcesso}</td>
                        <td align="center">
                               <a href=
 "${pageContext.request.contextPath}/InternetExcluir?idInternet=${site.idInternet}">
                            <button type="button" class="btn btn-danger">Excluir</button></a></td>
                        
                        <td align="center">
                              <a href=
 "${pageContext.request.contextPath}/InternetCarregar?idInternet=${site.idInternet}">
                                  <button type="button" class="btn btn-primary">Alterar</button>
                             </a></td>
                    </tr>                
                </c:forEach>
            </tbody>
        </table>
        <div align="center">        
            <a href="${pageContext.request.contextPath}/InternetNovo"><button type="button" class="btn btn-primary">Novo</button></a>
            <a href="index.jsp"><button type="button" class="btn btn-primary">Voltar ? P?gina Inicial</button></a>
        </div>
            
        
             </div>
         </div>
       </div>
      </div>
</div>
<%@ include file="/footer.jsp" %>        

