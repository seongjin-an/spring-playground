package handler.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Map;

public class HelloController implements NonViewController {
    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return "helloworld";
    }
}
