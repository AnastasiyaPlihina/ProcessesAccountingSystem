package by.tms.diploma.web;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorController {
    @ExceptionHandler(RuntimeException.class)
    public String error(RuntimeException e, Model model) {
        model.addAttribute("exceptionMessage", e.getMessage());
        return "error";
    }
}
