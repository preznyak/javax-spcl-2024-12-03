package frontendservice.data;

import lombok.Data;

@Data
public class EmployeeData {

    private Long id;

    private String name;

    private RoleData role;
}
