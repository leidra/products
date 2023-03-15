/*
 * Copyright (c) 2021 Agile Content S.A. All rights reserved.
 */
package net.leidra.atsistemas.products.architecture;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchTests;

@AnalyzeClasses(packages = "net.leidra.atsistemas")
final class PricesArchTest {

  @ArchTest
  static final ArchTests codingRules = ArchTests.in(CodingRules.class);

  @ArchTest
  static final ArchTests cleanArchRules = ArchTests.in(CleanArchRules.class);
}
