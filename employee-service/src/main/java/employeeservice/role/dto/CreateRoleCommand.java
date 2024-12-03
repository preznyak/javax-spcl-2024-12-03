package employeeservice.role.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

public record CreateRoleCommand(
        @Schema(description = "the name of the new role", example = "John Doe")
        String name
) {

}
