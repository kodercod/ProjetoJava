<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<jsp:include page="menu.jsp"/>

<!-- Begin Page Content -->
        <div class="container-fluid">

          <!-- Page Heading -->
          <h1 class="h3 mb-2 text-gray-800">Tabelas</h1>

          <!-- DataTales Example -->
          <div class="card shadow mb-4">
            <div class="card-header py-3">
              <h6 class="m-0 font-weight-bold text-primary">DataTables</h6>
            </div>
            <div class="card-body">
              <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0" colspan="3">
                  <thead>
                    <tr>
                      <th>ID</th>
                      <th>Nome</th>
                      <th>Data</th>
                      <th>Hora</th>
                      <th>Editar</th>
                    </tr>
                  </thead>
                  <tbody>
                    <c:forEach var="programa" items="${programas}">
                    <tr>
                        <td align="center">${programa.idPrograma}</td>                    
                        <td align="center">${programa.nomePrograma}</td>
                        <td align="center">${programa.dataAcesso}</td>
                        <td align="center">${programa.horaAcesso}</td>
                        <td align="center">
                               <a href=
 "${pageContext.request.contextPath}/ProgramaExcluir?idPrograma=${programa.idPrograma}">
                            Excluir</a></td>
                        
                        <td align="center">
                              <a href=
 "${pageContext.request.contextPath}/ProgramaCarregar?idPrograma=${programa.idPrograma}">
                             Alterar</a></td>
                    </tr>
                  </c:forEach>
                  </tbody>
                </table>
              </div>
            </div>
          </div>

        </div>
        <!-- /.container-fluid -->
      </div>
      <!-- End of Main Content -->

    </div>
    <!-- End of Content Wrapper -->

  </div>
  <!-- End of Page Wrapper -->
    <!-- Scroll to Top Button-->
    
<jsp:include page="scroll.jsp"/>
  <!-- Bootstrap core JavaScript-->
  <script src="vendor/jquery/jquery.min.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Core plugin JavaScript-->
  <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

  <!-- Custom scripts for all pages-->
  <script src="js/sb-admin-2.min.js"></script>

  <!-- Page level plugins -->
  <script src="vendor/datatables/jquery.dataTables.min.js"></script>
  <script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>

  <!-- Page level custom scripts -->
  <script src="js/demo/datatables-demo.js"></script>
  <

  <jsp:include page="footer.jsp"/>