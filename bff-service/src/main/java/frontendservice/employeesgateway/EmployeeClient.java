package frontendservice.employeesgateway;

import org.springframework.web.service.annotation.GetExchange;

import java.util.List;

public interface EmployeeClient {

    @GetExchange("/api/employees")
    List<Employee> listEmployees();

}
