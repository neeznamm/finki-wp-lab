package mk.ukim.finki.wp.lab.web;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.service.CourseService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name="CoursesList", value="/listCourses")
public class CoursesListServlet extends HttpServlet
{
    private final CourseService courseService;

    public CoursesListServlet(CourseService courseService) {
        this.courseService = courseService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        List<Course> courseList = courseService.findAll();

        out.println("<html>\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "    <title>Welcome and Choose a Course</title>\n" +
                "    <style type=\"text/css\">\n" +
                "        body {\n" +
                "            width: 800px;\n" +
                "            margin: auto;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "<header>\n" +
                "    <h1>Courses List</h1>\n" +
                "</header>\n" +
                "<main>\n" +
                "    <h2>Choose course:</h2>");
        out.println("<form action=\"AddStudent\" method=\"post\">\n");
        for(int i=0;i<5;++i) {
            out.printf("<input type=\"radio\" name=\"courseChoice\" value=\"%d\"> %s<br/>\n", courseList.get(i).getCourseId(), courseList.get(i).getName());
        }
        out.println("    <br/>\n" +
                "    <input type='submit' value='Submit'/>\n" +
                "</main>\n" +
                "</body>\n" +
                "</html>");
    }
}
