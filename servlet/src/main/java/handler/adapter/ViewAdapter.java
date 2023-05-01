package handler.adapter;

import handler.servlet.ViewController;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ViewAdapter implements HandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ViewController);
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        ViewController controller = (ViewController) handler;
        Map<String, Object> model = new HashMap<>();
        String viewName = controller.process(request, response, model);
        givenModel(model, request);
        render(viewName, request, response);
    }

    private void render(String viewName, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/" + viewName + ".jsp");
        dispatcher.forward(request, response);
    }

    private void givenModel(Map<String, Object> model, HttpServletRequest request) {
        model.forEach((key, value) -> request.setAttribute(key, value));
    }
}
