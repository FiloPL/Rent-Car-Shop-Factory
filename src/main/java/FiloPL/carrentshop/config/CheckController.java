package FiloPL.carrentshop.config;

import FiloPL.carrentshop.employee.Employee;
import FiloPL.carrentshop.employee.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping("/test")
public class CheckController {

    private final EmployeeService employeeService;
    public CheckController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ModelAndView getTest(){
        return new ModelAndView("check");
    }

    @GetMapping("/user")
    @ResponseBody
    public String getUserName(Principal principal){
        String userName = principal.getName();
        Employee employee = employeeService.getEmployeeByUsername(userName);
        return employee.getFullUserName();
    }
}
