package by.tms.diploma.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorController {
    private static final Logger logger = LoggerFactory.getLogger(ErrorController.class);
    @ExceptionHandler(RuntimeException.class)
    public String error(RuntimeException e, Model model) {
        logger.error(e.getMessage());
        model.addAttribute("exceptionMessage", e.getMessage());
        return "error";
    }
}
