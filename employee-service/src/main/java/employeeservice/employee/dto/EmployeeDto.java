package employeeservice.employee.dto;

import employeeservice.role.dto.RoleDto;

public record EmployeeDto(
        Long id,
        String name,
        RoleDto role
) {

}
