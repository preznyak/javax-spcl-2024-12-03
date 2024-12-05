package frontendservice.service;

import frontendservice.employeegateway.CreateEmployeeRequest;
import frontendservice.employeegateway.EmployeeDto;
import frontendservice.employeegateway.EmployeeServiceClient;
import frontendservice.employeegateway.RoleDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeesService {

    private EmployeeServiceClient employeeServiceClient;

    public List<EmployeeDto> listEmployees() {
        return employeeServiceClient.employees();
    }

    public List<RoleDto> listRoles() {
        return employeeServiceClient.roles();
    }

    public void createEmployee(CreateEmployeeRequest command) {
        employeeServiceClient.createEmployee(command);
    }

}
