package handler.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

public class ProductController implements ViewController {
    @Override
    public String process(HttpServletRequest request, HttpServletResponse response, Map<String, Object> model) throws ServletException, IOException {
        model.put("items", Arrays.asList(1,2,3,4));
        return "product";
    }
}
