package employeeservice.employee.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record UpdateEmployeeRequest(
        @Schema(description = "the name of the employee", example = "John Doe")
        String name,
        @Schema(description = "the id of the role of the employee", example = "1")
        long roleId
) {

}
