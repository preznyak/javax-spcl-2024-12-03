package careerservice;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

public class ModuleTest {

    @Test
    void architecture() {
        ApplicationModules modules = ApplicationModules.of(CareerServiceApplication.class);
        modules.verify();

        new Documenter(modules)
                .writeModulesAsPlantUml()
                .writeIndividualModulesAsPlantUml();
    }
}
