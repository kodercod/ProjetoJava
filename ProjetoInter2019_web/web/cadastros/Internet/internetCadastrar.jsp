<%-- 
    Document   : internetCadastrar
    Created on : 15/11/2019, 10:15:05
    Author     : Luis Silva
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<jsp:include page="/header.jsp"/>
<jsp:include page="/menu.jsp"/>


<div class="container">
        <form name="internetcadastrar" action="InternetCadastrar" 
                method="POST">
            
            <table align="center" border="0">
                <thead>
                    <tr>
                <th colspan="2" align="center">
                            Cadastro de Sites</th>
                        
                    </tr>
                    <tr>
                        <th colspan="2" align="center">${mensagem}</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>ID: </td>
                        <td><input type="text" class="form-control form-control-user" name="idinternet" 
                          id="idinternet" value="${site.idInternet}"
                          readonly="readonly" /></td>
                    </tr>
                    <tr>
                        <td>Nome: </td>
                        <td><input type="text" class="form-control form-control-user" name="nomepagina" id="nomepagina" 
                         value="${site.nomePagina}" size="50" 
                         maxlength="50" /></td>
                    </tr>
                    <tr>
                        <td>URL: </td>
                        <td><input type="text" class="form-control form-control-user" name="enderecopagina" id="enderecopagina" 
                         value="${site.enderecoPagina}" size="100" 
                         maxlength="50" /></td>
                    </tr>
                    <tr>
                        <td>Data: </td>
                        <td><input type="text" class="form-control form-control-user" name="dataacesso" id="dataacesso" 
                         value="${site.dataAcesso}" /></td>
                    </tr><tr>
                        <td>Hora: </td>
                        <td><input type="text" class="form-control form-control-user" name="horaacesso" id="horaacesso" 
                         value="${site.horaAcesso}" /></td>
                    </tr>
                    <tr>                    
                        <td colspan="2" align="center">
                            <button type="submit" name="cadastrar" id="cadastrar" 
                                   value="Cadastrar" class="btn btn-primary">Cadastrar</button>
                            
                            
                            <button type="reset" name="limpar" id="limpar" 
                                    value="Limpar" class="btn btn-primary">Limpar</button>
                        </td>
                    </tr>
                <hr
                    <tr>                        
                        <td align="center" colspan="2"><h5><a href="index.jsp">
                                   <button type="button" class="btn btn-primary">Voltar à Página Inicial</button></a></h5></td>
                    </tr>
                </tbody>
            </table>
        </form>
    </div>
                         
<%@ include file="/footer.jsp" %>
