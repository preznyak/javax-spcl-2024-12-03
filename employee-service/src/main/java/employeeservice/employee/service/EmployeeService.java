package employeeservice.employee.service;

import employeeservice.NotFoundException;
import employeeservice.employee.dto.CreateEmployeeRequest;
import employeeservice.employee.dto.EmployeeDto;
import employeeservice.employee.dto.UpdateEmployeeRequest;
import employeeservice.employee.model.Employee;
import employeeservice.role.model.Role;
import employeeservice.role.service.RoleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Supplier;

@Service
@AllArgsConstructor
@Slf4j
public class EmployeeService {
    private EmployeeMapper employeeMapper;

    private EmployeeRepository employeeRepository;

    private RoleRepository roleRepository;

    public EmployeeDto createEmployee(CreateEmployeeRequest request) {
        Role role = roleRepository.findById(request.roleId())
                .orElseThrow(() -> new NotFoundException("Role not found with id: " + request.roleId()));
        Employee employee = new Employee(request.name(), role);
        employeeRepository.save(employee);
        return employeeMapper.toDto(employee);
    }

    public List<EmployeeDto> listEmployees() {
        log.debug("List employees");
        return employeeMapper.toDto(employeeRepository.findAll());
    }

    public EmployeeDto findEmployeeById(long id) {
        return employeeMapper.toDto(employeeRepository.findById(id)
                        .orElseThrow(notFountException(id)));
    }

    @Transactional
    public EmployeeDto updateEmployee(long id, UpdateEmployeeRequest request) {
        Employee employeeToModify = employeeRepository.findById(id)
                .orElseThrow(notFountException(id));
        employeeToModify.setName(request.name());
        Role role = roleRepository.findById(request.roleId())
                .orElseThrow(() -> new NotFoundException("Role not found with id: " + id));
        employeeToModify.setRole(role);
        return employeeMapper.toDto(employeeToModify);
    }

    public void deleteEmployee(long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Employee not found with id: " + id));
        employeeRepository.delete(employee);
    }

    private Supplier<NotFoundException> notFountException(long id) {
        return () -> new NotFoundException("Employee not found with id: %d".formatted(id));
    }

}
