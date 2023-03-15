/*
 * Copyright (c) 2021 Agile Content S.A. All rights reserved.
 */
package net.leidra.atsistemas.products.architecture;

import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.Architectures.onionArchitecture;

class CleanArchRules {

  @ArchTest
  static final ArchRule clean_architecture_definition =
      onionArchitecture()
      .domainModels("..domain..")
      .domainServices("..domain..")
      .applicationServices("..application..")
      .adapter("rest", "..infrastructure.controllers.http..")
      .adapter("repositories", "..infrastructure.repositories.r2dbc..");

  @ArchTest
  static final ArchRule application_should_not_depend_on_infrastructure =
    noClasses()
      .that().resideInAPackage("..application..")
      .should().dependOnClassesThat()
      .resideInAnyPackage("..infrastructure..");
  @ArchTest
  static final ArchRule domain_should_not_depend_on_application =
    noClasses()
      .that().resideInAPackage("..domain..")
      .should().dependOnClassesThat()
      .resideInAnyPackage("..application..");
}
