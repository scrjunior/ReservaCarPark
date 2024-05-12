package consultas;

import consultas.dao.ConsultaDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/apagarConsulta")
public class ApagarConsultaServlet extends HttpServlet {
    private ConsultaDAO consultaDAO;

    public void init() {
        consultaDAO = new ConsultaDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get the placa parameter from the request
        String placa = request.getParameter("placa");
        
        if (placa != null && !placa.isEmpty()) {
            // Invoke the deleteConsulta method in ConsultaDAO with the placa
            consultaDAO.deleteConsultaByPlaca(placa);
        }
        
        // Redirect back to the consultas.jsp page
        response.sendRedirect("ConsultasServlet");
    }
}
