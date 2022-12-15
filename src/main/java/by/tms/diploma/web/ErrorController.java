package by.tms.diploma.web;

import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorController {
    private static final Logger logger = LogManager.getLogger(ErrorController.class);
    @ExceptionHandler(RuntimeException.class)
    public String error(RuntimeException e, Model model) {
        logger.error(e.getMessage());
        model.addAttribute("exceptionMessage", e.getMessage());
        return "error";
    }
}
