package mk.ukim.finki.wp.lab.web;

import mk.ukim.finki.wp.lab.exception.NotFoundException;
import mk.ukim.finki.wp.lab.service.CourseService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String error = null;
        try {
            courseService.addStudentInCourse(req.getParameter("username"), Long.parseLong((String) req.getSession().getAttribute("courseChoice")));
        } catch (NotFoundException e) {
            error = e.getMessage();
        }
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("courseEnrollments", courseService.listStudentsByCourse(Long.parseLong((String) req.getSession().getAttribute("courseChoice"))));
        context.setVariable("course", courseService.findCourseById(Long.parseLong((String) req.getSession().getAttribute("courseChoice"))));

        this.springTemplateEngine.process("studentsInCourse.html", context, resp.getWriter());
    }
}
