/*
 * Copyright (c) 2021 Agile Content S.A. All rights reserved.
 */
package net.leidra.atsistemas.products.architecture;

import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.GeneralCodingRules;

import static com.tngtech.archunit.core.domain.JavaClass.Predicates.resideInAPackage;
import static com.tngtech.archunit.lang.conditions.ArchConditions.dependOnClassesThat;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

abstract class CodingRules {

  @ArchTest
  @SuppressWarnings("unused")
  static final ArchRule no_system_out_err = GeneralCodingRules.NO_CLASSES_SHOULD_ACCESS_STANDARD_STREAMS;

  @ArchTest
  @SuppressWarnings("unused")
  static final ArchRule no_generic_exceptions = GeneralCodingRules.NO_CLASSES_SHOULD_THROW_GENERIC_EXCEPTIONS;

  @ArchTest
  @SuppressWarnings("unused")
  static final ArchRule no_java_util_logging = GeneralCodingRules.NO_CLASSES_SHOULD_USE_JAVA_UTIL_LOGGING;

  @ArchTest
  @SuppressWarnings("unused")
  static final ArchRule no_slf4j =
      noClasses()
      .should(dependOnClassesThat(resideInAPackage("org.slf4j"))
        .as("use SLF4J"));
}
