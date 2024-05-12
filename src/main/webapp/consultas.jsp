<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Consultas</title>
    <!-- Include Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <h1 class="mt-5">Consultas</h1>
        <table class="table table-striped mt-4">
            <thead class="thead-dark">
                <tr>
                    <th scope="col">Nome</th>
                    <th scope="col">Apelido</th>
                    <th scope="col">Telefone</th>
                    <th scope="col">Placa</th>
                    <th scope="col">Vaga</th>
                    <th scope="col">Hora</th>
                    <th scope="col">Ações</th> <!-- New column for buttons -->
                </tr>
            </thead>
            <tbody>
                <c:forEach var="consulta" items="${consultas}">
                    <tr>
                        <td>${consulta.nomePaciente}</td>
                        <td>${consulta.apelidoPaciente}</td>
                        <td>${consulta.telefonePaciente}</td>
                        <td>${consulta.placa}</td>
                        <td>${consulta.vagas}</td>
                        <td><fmt:formatDate value="${consulta.dataConsulta}" pattern="yyyy-MM-dd HH:mm"/></td>
                        <td>
                            <!-- Editar button -->
                            <a href="editarConsulta?id=${consulta.idConsulta}" class="btn btn-primary btn-sm">Editar</a>
                            
                            <!-- Apagar button -->
                            <button type="button" class="btn btn-danger btn-sm delete-btn"
                                data-id="${consulta.idConsulta}" data-placa="${consulta.placa}">Apagar</button>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <!-- Modal de Confirmação para Excluir Consulta -->
    <div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="deleteModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="deleteModalLabel">Confirmar Exclusão</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form id="confirmDeleteForm" action="apagarConsulta" method="GET">
                        <p>Por favor, insira a placa para confirmar a exclusão da consulta:</p>
                        <input type="hidden" id="consultaId" name="id" value="">
                        <input type="text" class="form-control" id="placaInput" name="placa" placeholder="Placa">
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                    <button type="submit" class="btn btn-danger" form="confirmDeleteForm">Confirmar Exclusão</button>
                </div>
            </div>
        </div>
    </div>

    <!-- Include jQuery -->
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <!-- Include Bootstrap JS and jQuery (Slim version is fine for basic functionality) -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>

    <script>
        $(document).ready(function () {
            $('.delete-btn').click(function () {
                var consultaId = $(this).data('id');
                var placa = $(this).data('placa'); // Obter a placa da data attribute
                
                // Preencher os campos do formulário no modal
                $('#consultaId').val(consultaId); // Preencher o campo id com o id da consulta
                $('#placaInput').val(placa); // Preencher o campo placa com a placa da consulta

                // Exibir o modal
                $('#deleteModal').modal('show');
            });
        });
    </script>
</body>
</html>
