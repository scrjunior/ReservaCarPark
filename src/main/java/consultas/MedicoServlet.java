package consultas;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import consultas.dao.MedicoDAO;
import consultas.model.Medico;
import consultas.LocalDateTimeAdapter;

@WebServlet("/MedicoServlet")
public class MedicoServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Check if the request parameter 'idMedico' is present
            if (request.getParameter("idMedico") != null) {
                // If 'idMedico' is present, retrieve consultation dates for the specified doctor
                int idMedico = Integer.parseInt(request.getParameter("idMedico"));

                // Initialize MedicoDAO
                MedicoDAO medicoDAO = new MedicoDAO();

                // Retrieve consultation dates for the specified doctor
                List<LocalDateTime> datasConsulta = medicoDAO.listarDatasConsulta(idMedico);

                // Serialize LocalDateTime objects using a custom adapter
                Gson gson = new GsonBuilder()
                        .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                        .create();
                String jsonDatasConsulta = gson.toJson(datasConsulta);

                // Set response type to JSON
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");

                // Send JSON response
                PrintWriter out = response.getWriter();
                out.print(jsonDatasConsulta);
                out.flush();
            } else {
                // If 'idMedico' is not present, retrieve the list of doctors
                MedicoDAO medicoDAO = new MedicoDAO();
                List<Medico> medicos = medicoDAO.listarMedicos();

                // Convert list of doctors to JSON format
                Gson gson = new Gson();
                String jsonMedicos = gson.toJson(medicos);

                // Set response type to JSON
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");

                // Send JSON response
                PrintWriter out = response.getWriter();
                out.print(jsonMedicos);
                out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Erro no servlet MedicoServlet", e);
        }
    }
}
