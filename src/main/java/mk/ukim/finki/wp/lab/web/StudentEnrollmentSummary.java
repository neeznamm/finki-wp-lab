package mk.ukim.finki.wp.lab.web;

import mk.ukim.finki.wp.lab.service.CourseService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="StudentEnrollmentSummary", value="/StudentEnrollmentSummary")
public class StudentEnrollmentSummary extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final CourseService courseService;

    public StudentEnrollmentSummary(SpringTemplateEngine springTemplateEngine, CourseService courseService) {
        this.springTemplateEngine = springTemplateEngine;
        this.courseService = courseService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String error = courseService.addStudentInCourse(req.getParameter("username"), Long.parseLong((String) req.getSession().getAttribute("courseChoice")));
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("studentsInCourse", courseService.listStudentsByCourse(Long.parseLong((String) req.getSession().getAttribute("courseChoice"))));
        context.setVariable("course", courseService.findCourseById(Long.parseLong((String) req.getSession().getAttribute("courseChoice"))));

        this.springTemplateEngine.process("studentsInCourse.html", context, resp.getWriter());
    }
}
