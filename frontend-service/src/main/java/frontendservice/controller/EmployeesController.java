package frontendservice.controller;

import frontendservice.employeegateway.CreateEmployeeRequest;
import frontendservice.service.EmployeesService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@AllArgsConstructor
@Slf4j
public class EmployeesController {

    private EmployeesService employeesService;

    @GetMapping("/employees")
    public ModelAndView listEmployees() {
        Map<String, Object> model = new HashMap<>();
        model.put("employees", employeesService.listEmployees());
        return new ModelAndView("employees", model);
    }

    @GetMapping("/create-employee")
    public ModelAndView createEmployee() {
        Map<String, Object> model = Map.of(
                "command", new CreateEmployeeRequest(),
                "roles", employeesService.listRoles()
        );
        return new ModelAndView("create-employee", model);
    }

    @PostMapping("/create-employee")
    public ModelAndView createEmployeePost(@ModelAttribute CreateEmployeeRequest command) {
        employeesService.createEmployee(command);
        return new ModelAndView("redirect:/employees");
    }

}