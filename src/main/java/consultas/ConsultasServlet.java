package consultas;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import consultas.dao.ConsultaDAO;
import consultas.model.Consulta;

@WebServlet("/ConsultasServlet")
public class ConsultasServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ConsultaDAO consultaDAO;

    public void init() {
        consultaDAO = new ConsultaDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Recuperar os parâmetros do formulário
        String nomePaciente = request.getParameter("nome");
        String apelidoPaciente = request.getParameter("apelido");
        String telefonePaciente = request.getParameter("telefone");
        String placaCarro = request.getParameter("placa");
        String especialista = request.getParameter("especialista");
        String dataConsultaStr = request.getParameter("dataConsulta");
        Date dataConsulta = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        
        
        if (nomePaciente == null || nomePaciente.isEmpty()) {
            // Tratar o caso em que o nome do paciente é nulo ou vazio
            // Por exemplo, você pode retornar uma mensagem de erro ou redirecionar para uma página de erro
        } else {
            // Criar um objeto Consulta com os dados do formulário
            Consulta consulta = new Consulta();
            consulta.setNomePaciente(nomePaciente);
            // Restante do código para criar o objeto Consulta e inserir no banco de dados...
        }


        // Verificar se a string da data de consulta não é nula ou vazia
        if (dataConsultaStr != null && !dataConsultaStr.isEmpty()) {
            try {
                dataConsulta = dateFormat.parse(dataConsultaStr);
            } catch (ParseException e) {
                e.printStackTrace(); // Tratamento adequado para o erro de parsing
            }
        } else {
            // Tratar o caso em que dataConsultaStr é nula ou vazia
        }

        // Continuação do código...

     // Replace this block of code in your Servlet
        String idMedicoStr = request.getParameter("idMedico");
        int idMedico = 0; // Valor padrão ou tratamento adequado em caso de parâmetro ausente

        if (idMedicoStr != null && !idMedicoStr.isEmpty()) {
            idMedico = Integer.parseInt(idMedicoStr);
        } else {
            // Tratar o caso em que o parâmetro "idMedico" está ausente
            // Por exemplo, você pode lançar uma exceção, retornar uma mensagem de erro, ou definir um valor padrão
        }

        // With this modified code
        String especialistaIdStr = request.getParameter("especialista");

        if (especialistaIdStr != null && !especialistaIdStr.isEmpty()) {
            idMedico = Integer.parseInt(especialistaIdStr);
        } else {
            // Handle the case where the "especialista" parameter is missing or empty
        }


        // Converter strings de data para objetos Date
        
        
        

        // Criar um objeto Consulta com os dados do formulário
        Consulta consulta = new Consulta();
        consulta.setNomePaciente(nomePaciente);
        consulta.setApelidoPaciente(apelidoPaciente);
        consulta.setTelefonePaciente(telefonePaciente);
        consulta.setPlaca(placaCarro);
        consulta.setVagas(especialista);
        consulta.setDataConsulta(dataConsulta);
        consulta.setIdMedico(idMedico);
        
     // Create a SimpleDateFormat object to parse both date and time
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // Parse the dataConsultaStr string to include both date and time
        if (dataConsultaStr != null && !dataConsultaStr.isEmpty()) {
            try {
                // Parse the date and time string
                dataConsulta = dateTimeFormat.parse(dataConsultaStr);
            } catch (ParseException e) {
                e.printStackTrace(); // Handle parsing error appropriately
            }
        }

        // Set the date and time in your Consulta object
        consulta.setDataConsulta(dataConsulta);


        // Inserir a consulta no banco de dados
        consultaDAO.inserirConsulta(consulta);

        // Redirecionar de volta para a página inicial ou outra página de sucesso
        response.sendRedirect("ConsultasServlet");
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	
    	
        ConsultaDAO consultaDAO = new ConsultaDAO();
        List<Consulta> consultas = consultaDAO.getAllConsultas();

        System.out.println("Consultas size: " + consultas.size()); // Debugging
        request.setAttribute("consultas", consultas);

        // Forward request to JSP for rendering
        request.getRequestDispatcher("consultas.jsp").forward(request, response);
    }


}


