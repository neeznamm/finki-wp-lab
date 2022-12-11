package mk.ukim.finki.wp.lab.web;

import mk.ukim.finki.wp.lab.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="CreateStudent", value="/CreateStudent" )
public class CreateStudentServlet extends HttpServlet {
    private final StudentService studentService;

    public CreateStudentServlet(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        studentService.save(req.getParameter("username").toString(), req.getParameter("password").toString(), req.getParameter("name").toString(), req.getParameter("surname").toString());
        resp.sendRedirect("/AddStudent");
    }
}
