package evilNerd.controller;

import evilNerd.domain.User;
import evilNerd.repository.UserRepository;
import evilNerd.repository.impl.UserRepositoryImpl;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.stream.Collectors;

public class FrontController extends  HttpServlet{
    public static final UserRepository userRepository = new UserRepositoryImpl();

    public FrontController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doRequest(req, resp);
    }

    private void doRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher dispatcher = req.getRequestDispatcher("/hello");
        if (dispatcher != null) {
            System.out.println("Forward will be done!");
            req.setAttribute("userName", userRepository.findAll().stream().map(User::getName).collect(Collectors.joining(" , ")));
            dispatcher.forward(req, resp);
        }


        // Третий вариант задание номер 6

    //    RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
     //   dispatcher.forward(req, resp);



        // Второй вариат задание номер 5
/*
        resp.setContentType("text/html");
        PrintWriter output = resp.getWriter();
        output.println("<html><head>");
        output.println("<meta http-equiv=\"Content-Type\" content=\"text/html;charset=utf-8\">");
        output.println("<title>Title</title>");
        output.println("</head><body>");
        output.println("<h1> Hello, world!!! </h1>");
        output.println("</body></html>");

*/

// Первоначальный вариант
/*
        RequestDispatcher dispatcher = req.getRequestDispatcher("/bye");
        if (dispatcher != null) {
            System.out.println("Forward will be done!");
            dispatcher.forward(req, resp);
        }
        */

    }
}
