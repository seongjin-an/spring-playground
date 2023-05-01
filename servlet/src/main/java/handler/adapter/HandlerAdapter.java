package handler.adapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface HandlerAdapter {
    boolean supports(Object handler);

    void handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException;
}
