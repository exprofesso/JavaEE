package evilNerd.controller;

import evilNerd.controller.command.Commands;
import evilNerd.domain.Cars;
import evilNerd.repository.CarsRepository;
import evilNerd.repository.impl.CarsRepositorylmpl;
import evilNerd.domain.User;
import evilNerd.repository.UserRepository;
import evilNerd.repository.impl.UserRepositoryImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.io.IOException;
import java.nio.charset.Charset;
import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;

// public static final CarsRepository carsRepository = new CarsRepositorylmpl();
//   RequestDispatcher dispatcher = req.getRequestDispatcher("/tablecars");
//        if (dispatcher != null) {
//        System.out.println("Forward will be done!");
//        req.setAttribute("modelCars", carsRepository.findAll().stream().map(Cars::getModel).collect(Collectors.joining(" , ")));
//        dispatcher.forward(req, resp);
//    }


@Controller
public class FrontController extends HttpServlet {

    //public static final UserRepository userRepository = new UserRepositoryImpl();
    public static final CarsRepository carsRepository = new CarsRepositorylmpl();
    public FrontController() {
        super();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processGetRequests(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processPostRequests(req, resp);
    }
    //    private void doRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        RequestDispatcher dispatcher = req.getRequestDispatcher("/hello");
//        if (dispatcher != null) {
//            System.out.println("Forward will be done!");
//
//            req.setAttribute("userName", userRepository.findAll().stream().map(User::getName).collect(Collectors.joining(",")));
//
//            dispatcher.forward(req, resp);
//        }
//    }
//    private void processGetRequests(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Commands commandName = Commands.findByCommandName(req.getParameter("command"));
//        try {
//            RequestDispatcher dispatcher = req.getRequestDispatcher("/hello");
//            if (dispatcher != null) {
//                resolveGetRequestCommands(req, commandName);
//                dispatcher.forward(req, resp);
//            }
//        } catch (Exception e) {
//            RequestDispatcher dispatcher = req.getRequestDispatcher("/error");
//            if (dispatcher != null) {
//                req.setAttribute("trace", e.getMessage());
//                dispatcher.forward(req, resp);
//            }
//        }
//    }
//    private void resolveGetRequestCommands(HttpServletRequest req, Commands commandName) {
//        //http://localhost:8080/test/FrontController?command=findAll&page=0&limit=10 (add offset to query)
//        switch (commandName) {
//            //     http://localhost:8080/test/FrontController?command=findAll
//            case FIND_ALL:
//                String page = req.getParameter("page");
//                String limit = req.getParameter("limit");
//                req.setAttribute("users", userRepository.findAll());
//                break;
//            //     http://localhost:8080/test/FrontController?command=findById&id=10
//            case FIND_BY_ID:
//                String id = req.getParameter("id");
//                long userId = Long.parseLong(id);
//                req.setAttribute("users", Collections.singletonList(userRepository.findById(userId)));
//                req.setAttribute("singleUser", userRepository.findById(userId));
//                break;
//            default:
//                break;
//        }
//    }
//    private void processPostRequests(HttpServletRequest req, HttpServletResponse resp) {
//        Commands commandName = Commands.findByCommandName(req.getParameter("command"));
//        try {
//            switch (commandName) {
//                case CREATE:
//                    String body = IOUtils.toString(req.getInputStream(), Charset.defaultCharset());
//                    User user = new Gson().fromJson(body, User.class);
//                    req.setAttribute("users", Collections.singletonList(userRepository.save(user)));
//                    break;
//                case UPDATE:
//                    String updateBody = IOUtils.toString(req.getInputStream(), Charset.defaultCharset());
//                    User updateUser = new Gson().fromJson(updateBody, User.class);
//                    req.setAttribute("users", Collections.singletonList(userRepository.update(updateUser)));
//                    break;
//                case DELETE:
//                    String id = req.getParameter("id");
//                    long userId = Long.parseLong(id);
//                    userRepository.delete(userRepository.findById(userId));
//                    req.setAttribute("users", userRepository.findAll());
//                    break;
//                default:
//                    break;
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e.getMessage());
//        }







    private void processGetRequests(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Commands commandName = Commands.findByCommandName(req.getParameter("command"));
        try {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/tablecars");
            if (dispatcher != null) {
                resolveGetRequestCommands(req, commandName);
                dispatcher.forward(req, resp);
            }
        } catch (Exception e) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/error");
            if (dispatcher != null) {
                req.setAttribute("trace", e.getMessage());
                dispatcher.forward(req, resp);
            }
        }
    }
    private void resolveGetRequestCommands(HttpServletRequest req, Commands commandName) {
        //http://localhost:8080/test/FrontController?command=findAll&page=0&limit=10 (add offset to query)
        switch (commandName) {
            //     http://localhost:8080/test/FrontController?command=findAll
            case FIND_ALL:
                String page = req.getParameter("page");
                String limit = req.getParameter("limit");
                req.setAttribute("cars", carsRepository.findAll());
                break;
            //     http://localhost:8080/test/FrontController?command=findById&id=10
            case FIND_BY_ID:
                String id = req.getParameter("id");
                long carsId = Long.parseLong(id);
                req.setAttribute("cars", Collections.singletonList(carsRepository.findById(carsId)));
                req.setAttribute("singleCars", carsRepository.findById(carsId));
                break;
            default:
                break;
        }
    }
    private void processPostRequests(HttpServletRequest req, HttpServletResponse resp) {
        Commands commandName = Commands.findByCommandName(req.getParameter("command"));
        try {
            switch (commandName) {
                case CREATE:
                    String body = IOUtils.toString(req.getInputStream(), Charset.defaultCharset());
                    Cars cars = new Gson().fromJson(body, Cars.class);
                    req.setAttribute("cars", Collections.singletonList(carsRepository.save(cars)));
                    break;
                case UPDATE:
                    String updateBody = IOUtils.toString(req.getInputStream(), Charset.defaultCharset());
                    Cars updateCars = new Gson().fromJson(updateBody, Cars.class);
                    req.setAttribute("cars", Collections.singletonList(carsRepository.update(updateCars)));
                    break;
                case DELETE:
                    String id = req.getParameter("id");
                    long carsId = Long.parseLong(id);
                    carsRepository.delete(carsRepository.findById(carsId));
                    req.setAttribute("cars", carsRepository.findAll());
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }



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



