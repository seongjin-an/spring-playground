package handler.front;

import handler.adapter.HandlerAdapter;
import handler.adapter.NonViewAdapter;
import handler.adapter.ViewAdapter;
import handler.servlet.ViewController;
import handler.servlet.HelloController;
import handler.servlet.ProductController;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontHandler", urlPatterns = "/servlet/*")
public class FrontHandler extends HttpServlet {

    private final Map<String, Object> handlerMaps = new HashMap<>();
    private final List<HandlerAdapter> handlerAdapters = new ArrayList<>();

    public FrontHandler() {
        handlerMaps.put("/servlet/helloworld", new HelloController());
        handlerMaps.put("/servlet/product", new ProductController());
        handlerAdapters.add(new ViewAdapter());
        handlerAdapters.add(new NonViewAdapter());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        Object handler = handlerMaps.get(requestURI);
        HandlerAdapter adapter = givenHandler(handler);
        adapter.handle(req, resp, handler);
    }

    private HandlerAdapter givenHandler(Object handler) {
        for (HandlerAdapter adapter : handlerAdapters) {
            if (adapter.supports(handler)) {
                return adapter;
            }
        }
        throw new RuntimeException("not found handler adapter");
    }

}
