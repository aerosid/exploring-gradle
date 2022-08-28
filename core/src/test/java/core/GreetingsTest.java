package core;

import org.junit.Assert;
import org.junit.Test;

public class GreetingsTest {
  
  @Test
  public void testGreetigs() {
    Assert.assertEquals("Hello World!", Greetings.greet());
    return;
  }

}
