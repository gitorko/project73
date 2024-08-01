package com.demo.project73;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

@SpringBootTest
class MainTests {

    ApplicationModules modules = ApplicationModules.of(Main.class);

    @Test
    void createApplicationModuleModel() {
        modules.verify();
        modules.forEach(System.out::println);
    }

    @Test
    void createModuleDocumentation() {
        new Documenter(modules)
                .writeDocumentation()
                .writeIndividualModulesAsPlantUml();
    }

}
