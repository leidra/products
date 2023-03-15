package net.leidra.atsistemas.shared.domain;

import net.leidra.atsistemas.products.shared.domain.exceptions.IllegalIdentifierArgument;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IdentifierTest {

  @Test
  void should_throw_IllegalIdentifierArgument() {
    assertThrows(IllegalIdentifierArgument.class, () -> new Identifier(null));
  }
}
