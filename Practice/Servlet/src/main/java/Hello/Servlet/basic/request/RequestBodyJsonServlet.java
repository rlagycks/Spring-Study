package Hello.Servlet.basic.request;

import Hello.Servlet.basic.Hellodata;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "RequestBodyJsonServlet", urlPatterns = "/request-body-json")
public class RequestBodyJsonServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messegeBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        System.out.println("messegeBody = " + messegeBody);

        Hellodata hellodata=objectMapper.readValue(messegeBody, Hellodata.class);

        System.out.println("hellodata.username = " + hellodata.getUsername());
        System.out.println("hellodata.age = " + hellodata.getAge());

        response.getWriter().write("ok");
    }
}
