package employeeservice.employee.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record CreateEmployeeRequest(
        @Schema(description = "the name of the new employee", example = "John Doe")
        String name,
        @Schema(description = "the id of the role of the new employee", example = "1")
        long roleId) {
}
