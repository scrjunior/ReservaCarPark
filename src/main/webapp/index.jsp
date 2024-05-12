<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Reservar | ReservaCarPark</title>
  <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
  <style>
    .container {
      max-width: 600px;
    }
  </style>
</head>
<body>

<div class="container mt-5">
  <h2 class="mb-4">Reserva CarPark</h2>
  <form method="post" action="ConsultasServlet" name="meuFormulario" id="meuFormulario">

    <div class="form-group">
    <label for="nome">Nome:</label>
    <div class="input-group">
        <input type="text" class="form-control" id="nome" name="nome" placeholder="Digite seu nome" required>
        <div class="input-group-append">
            <span class="input-group-text">Apelido:</span>
        </div>
        <input type="text" class="form-control" id="apelido" name="apelido" placeholder="Digite seu apelido">
    </div>
</div>


    <!-- 
    <div class="form-group">
      <label for="email">Email:</label>
      <input type="email" class="form-control" id="email" placeholder="Digite seu email" required>
    </div>
     -->
    <div class="form-group">
    <label for="telefone">Telefone:</label>
    <input type="tel" class="form-control" id="telefone" name="telefone" placeholder="Digite seu telefone" required>
</div>

    
    <div class="form-group">
  <label for="placaCarro">Número da placa:</label>
  <input type="number" class="form-control" id="placa" name="placa" placeholder="Digite a placa do seu carro" required>
</div>
    <div class="form-group">
    <label for="especialista">Vaga:</label>
    <div class="input-group">
        <select class="form-control" id="especialista" name="especialista" required>
            <option value="">Selecione uma vaga</option>
            <!-- Opções preenchidas dinamicamente -->
        </select>
        <div class="input-group-append">
            <span class="input-group-text" id="info-vaga-btn" style="cursor: pointer;">!</span>
        </div>
    </div>
</div>
<script>

</script>

    <div class="form-group">
      <label for="dataConsulta">Disponibilidade:</label>
      <select class="form-control" id="dataConsulta" name="dataConsulta" required>
        <option value="">Selecione uma data</option>
        <!-- Opções preenchidas dinamicamente -->
      </select>
    </div>
    
    <button type="submit" class="btn btn-primary">Reservar</button>
  </form>
  
  
</div>
<div class="modal" id="infoVagaModal">
  <div class="modal-dialog">
    <div class="modal-content">
      <!-- Cabeçalho do Modal -->
      <div class="modal-header">
        <h4 class="modal-title">Informações sobre as Vagas</h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>
      <!-- Corpo do Modal -->
      <div class="modal-body">
        <p>Vaga Azul: Cabe carro do tipo Sedan</p>
        <p>Vaga Vermelha: Cabe carro do tipo SUV</p>
        <p>Vaga Amarela: Cabe carro do tipo Hatchback</p>
        <p>Vaga Verde: Cabe carro do tipo Crossover</p>
      </div>
      <!-- Rodapé do Modal -->
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
      </div>
    </div>
  </div>



<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>


<script>
  // Função para carregar a lista de médicos
  function carregarMedicos() {
    var especialistaSelect = document.getElementById("especialista");

    // Limpar as opções atuais
    especialistaSelect.innerHTML = "";

    // Fazer requisição AJAX para o servlet para obter a lista de médicos
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "MedicoServlet", true);
    xhr.onreadystatechange = function () {
      if (xhr.readyState === 4 && xhr.status === 200) {
        var medicos = JSON.parse(xhr.responseText);

        // Adicionar as opções ao select
        medicos.forEach(function (medico) {
          var option = document.createElement("option");
          option.value = medico.idMedico;
          option.text = medico.especialidade;
          especialistaSelect.appendChild(option);
        });
      }
    };
    xhr.send();
  }

  // Função para carregar as datas de consulta com base no médico selecionado
  function carregarDatasConsulta() {
    var idMedicoSelecionado = document.getElementById("especialista").value;
    var dataConsultaSelect = document.getElementById("dataConsulta");

    // Limpar as opções atuais
    dataConsultaSelect.innerHTML = "";

    // Fazer requisição AJAX para o servlet para obter as datas de consulta
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "MedicoServlet?idMedico=" + idMedicoSelecionado, true);
    xhr.onreadystatechange = function () {
      if (xhr.readyState === 4 && xhr.status === 200) {
        var datasConsulta = JSON.parse(xhr.responseText);

        //console.log("Datas de Consulta:", datasConsulta);
        //console.log("Datas de Consulta:", especialista);
        
        // Adicionar as opções ao select
        datasConsulta.forEach(function (dataConsulta) {
          var option = document.createElement("option");
          option.value = dataConsulta;
          option.text = new Date(dataConsulta).toLocaleString();
          dataConsultaSelect.appendChild(option);
        });
      }
    };
    xhr.send();
  }

  // Carregar a lista de médicos ao carregar a página
  document.addEventListener("DOMContentLoaded", function () {
    carregarMedicos();
    
 // Adicionar evento de clique para abrir o modal com informações da vaga
    document.getElementById("info-vaga-btn").addEventListener("click", function () {
      $('#infoVagaModal').modal('show'); // Mostrar o modal ao clicar no ícone
    });
  });

  // Carregar as datas de consulta ao selecionar um médico
  document.getElementById("especialista").addEventListener("change", function () {
    carregarDatasConsulta();
  });
  
  
  


</script>

</body>
</html>




