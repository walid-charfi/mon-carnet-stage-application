package com.fm.mcs;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.fm.mcs");

        noClasses()
            .that()
            .resideInAnyPackage("com.fm.mcs.service..")
            .or()
            .resideInAnyPackage("com.fm.mcs.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.fm.mcs.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
