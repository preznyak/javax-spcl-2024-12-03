package frontendservice.employeegateway;

import lombok.Data;

@Data
public class CreateEmployeeRequest {

    private String name;

    private long roleId;
}
