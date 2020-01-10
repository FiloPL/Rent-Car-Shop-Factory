package FiloPL.carrentshop.config;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionControllerAdvice {
    @ExceptionHandler
    public ModelAndView getExceptionForm(Exception exception){
        ModelAndView exceptionModel = new ModelAndView("exception");
        exceptionModel.addObject("message", exception.getMessage());
        exceptionModel.addObject("className", exception.getClass().getSimpleName());
        return exceptionModel;
    }
}
