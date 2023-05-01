package handler.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public interface ViewController {
    String process(HttpServletRequest request, HttpServletResponse response, Map<String, Object> model) throws ServletException, IOException;
}
