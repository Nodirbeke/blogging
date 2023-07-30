
package nod.pro.blogging.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({Exception.class})
    public String errorHandler(Exception e,
                               Model model,
                               WebRequest request) {
//        String path =((ServletWebRequest)(request)).getRequest().getRequestURI();
        model.addAttribute("code", e.getLocalizedMessage());
        return "error/error";

    }


}
