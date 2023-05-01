package handler.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface NonViewController {
    String process(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException;
}
