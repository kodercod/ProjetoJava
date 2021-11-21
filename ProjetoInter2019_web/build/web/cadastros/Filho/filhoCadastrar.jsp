<%-- 
    Document   : filhoCadastrar
    Created on : 27/11/2019, 17:33:43
    Author     : Luis Silva
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>

<jsp:include page="/header.jsp"/>
<jsp:include page="/menu.jsp"/>

<div class="container">
<form name="cadastrarfilho" action="FilhoCadastrar" 
              method="POST">
            
            <table align="center" border="0">
                <thead>
                    <tr>
                        <th colspan="2" align="center">
                            Cadastro de Filhos</th>
                    </tr>
                    <tr>
                        <th colspan="2" align="center">${mensagem}</th>
                    </tr>
                </thead>
                <tbody>
                    
                    <tr>
                        <td>Id Filho: </td>
                        <td><input type="text" class="form-control form-control-user" name="idfilho" 
                          id="idfilho" value="${filho.idFilho}"
                          readonly="readonly" /></td>
                        <td>Id Login: </td>
                        <td><input type="text" class="form-control form-control-user" name="idlogin" 
                          id="idlogin" value="${filho.idLogin}"
                          readonly="readonly" /></td>
                    </tr>
                    <tr>
                        <td>Nome: </td>
                        <td><input type="text" class="form-control form-control-user" name="nome" id="nome" 
                         value="${filho.nome}" /></td>
                    </tr>
                    <tr>
                        <td>Usuario: </td>
                        <td><input type="text" class="form-control form-control-user" name="usuario" id="usuario" 
                         value="${filho.usuario}" /></td>
                    </tr>
                    <tr>
                        <td>Senha: </td>
                        <td><input type="password" class="form-control form-control-user" name="senha" id="senha" 
                         value="${filho.senha}" /></td>
                    </tr>
                    <tr>
                        <td>Data Nascimento: </td>
                        <td><input type="date" class="form-control form-control-user" name="datanascimento" id="datanascimento" 
                         value="${filho.dataNascimento}" /></td>
                    </tr>
                    <tr>
                        <td>Descricao </td>
                        <td><input type="textarea" class="form-control form-control-user" name="descricao" id="descricao" 
                         value="${filho.descricao}" size="100" 
                         maxlength="50" /></td>
                    </tr>
                    <tr>
                        <td>Relatorio</td>
                        <td>
                            <select name="idrelatorio" id="idrelatorio">
                                <option value="">Selecione</option>
                                <c:forEach var="relatorio" items="${relatorios}">
                                    <option value="${relatorio.idRelatorio}" 
                                        ${filho.relatorio.idRelatorio == relatorio.idRelatorio ? "selected" : ""}>
                                        ${relatorio.idRelatorio}
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