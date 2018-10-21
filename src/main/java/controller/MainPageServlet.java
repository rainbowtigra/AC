package controller;


import entity.Flight;
import repository.JDBCRepository.JDBCFlightRepository;
import repository.JDBCRepository.ds.pcLibraryProj.ConnectionPoolException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/lists")
public class MainPageServlet extends HttpServlet {

    //private static final Logger LOGGER = LogManager.getLogger(MainPageServlet.class);


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            List<Flight> listF = JDBCFlightRepository.getAll();
            request.setAttribute("listF", listF);
            request.getRequestDispatcher("/WEB-INF/pages/lists.jsp").forward(request, response);
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
    }

/*    public static void main(String[] args) throws ConnectionPoolException {
            System.out.println(JDBCFlightRepository.getAll());
    }*/
}