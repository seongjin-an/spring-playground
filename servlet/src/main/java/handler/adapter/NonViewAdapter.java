package handler.adapter;

import handler.servlet.NonViewController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NonViewAdapter implements HandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof NonViewController);
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        NonViewController controller = (NonViewController) handler;
        String result = controller.process(request, response);
        response.getWriter().println("result: " + result);
    }
}
