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
        <form name="cadastrarprograma" action="ProgramaCadastrar" 
                method="POST">
            
            <table align="center" border="0">
                <thead>
                    <tr>
                        <th colspan="2" align="center">
                            Cadastro de Programa</th>
                    </tr>
                    <tr>
                        <th colspan="2" align="center">${mensagem}</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>ID: </td>
                        <td><input type="text" class="form-control form-control-user" name="idprograma" 
                          id="idprograma" value="${programa.idPrograma}"
                          readonly="readonly" /></td>
                    </tr>
                    <tr>
                        <td>Nome: </td>
                        <td><input type="text" class="form-control form-control-user" name="nomeprograma" id="nomeprograma" 
                         value="${programa.nomePrograma}" size="50" 
                         maxlength="50" /></td>
                    </tr>
                    <tr>
                        <td>Data: </td>
                        <td><input type="text" class="form-control form-control-user" name="dataacesso" id="dataacesso" 
                         value="${programa.dataAcesso}" /></td>
                    </tr><tr>
                        <td>Hora: </td>
                        <td><input type="text" class="form-control form-control-user" name="horaacesso" id="horaacesso" 
                         value="${programa.horaAcesso}" /></td>
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

