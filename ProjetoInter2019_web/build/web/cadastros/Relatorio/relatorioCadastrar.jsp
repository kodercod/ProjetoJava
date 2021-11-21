<%-- 
    Document   : 
    Created on : 13/05/2019, 20:27:01
    Author     : jeffersonpasserini
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<jsp:include page="/header.jsp"/>
<jsp:include page="/menu.jsp"/>

<div class="container">
        <form name="cadastrarrelatorio" action="RelatorioCadastrar" 
                method="POST">
            
            <table align="center" border="0">
                <thead>
                    <tr>
                        <th colspan="2" align="center">
                            Cadastro de Relatorio</th>
                    </tr>
                    <tr>
                        <th colspan="2" align="center">${mensagem}</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>ID: </td>
                        <td><input type="text" class="form-control form-control-user" name="idrelatorio" 
                          id="idrelatorio" value="${relatorio.idRelatorio}"
                          readonly="readonly" /></td>
                    </tr>
                    <tr>
                        <td>Data: </td>
                        <td><input type="text" class="form-control form-control-user" name="datarelatorio" id="datarelatorio" 
                         value="${relatorio.dataRelatorio}" /></td>
                    </tr><tr>
                        <td>Hora: </td>
                        <td><input type="text" class="form-control form-control-user" name="horarelatorio" id="horarelatorio" 
                         value="${relatorio.horaRelatorio}" /></td>
                    </tr>
                   <tr>
                        <td>Programa: </td>
                        <td>
                        <select name="idprograma" id="idprograma">
                            <option value="">Selecione</option>
                            <c:forEach var="programa" items="${programas}">
                                <option value="${programa.idPrograma}" 
                                    ${relatorio.programas.idPrograma == programa.idPrograma ? "selected" : ""}>
                                    ${programa.nomePrograma}
                                </option>
                            </c:forEach>
                        </select>
                        </td>
                    </tr>
                   <tr>
                        <td>Site: </td>
                        <td>
                        <select name="idinternet" id="idinternet">
                            <option value="">Selecione</option>
                            <c:forEach var="site" items="${sites}">
                                <option value="${site.idInternet}" 
                                    ${relatorio.internet.idInternet == site.idInternet ? "selected" : ""}>
                                    ${site.nomePagina}
                                </option>
                            </c:forEach>
                        </select>
                        </td>
                    </tr>
                    <tr>                    
                        <td colspan="2" align="center">
                            <button type="submit" name="cadastrar" id="cadastrar" 
                                   value="Cadastrar" class="btn btn-primary">Cadastrar</button>
                            
                            
                            <button type="reset" name="limpar" id="limpar" 
                                    value="Limpar" class="btn btn-primary">Limpar</button>
                        </td>
                    </tr>

                    <tr>                        
                        <td align="center" colspan="2"><h5><a href="index.jsp">
                                   <button type="button" class="btn btn-primary">Voltar à Página Inicial</button></a></h5></td>
                    </tr>
                </tbody>
            </table>
        </form>  
</div>                         
<%@ include file="/footer.jsp" %>

