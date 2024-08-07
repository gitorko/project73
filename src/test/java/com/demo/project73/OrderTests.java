package com.demo.project73;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.testcontainers.junit.jupiter.Testcontainers;

@ApplicationModuleTest(mode = ApplicationModuleTest.BootstrapMode.STANDALONE)
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class OrderTests extends BaseTest {
}
