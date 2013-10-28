package webinar;

import org.junit.Assert;
import org.junit.Test;

public class WebinarTest {
  
  @Test public void normalizesDescription() {
    //when
    Demoable demoable = new Webinar();
    
    //then
    Assert.assertEquals("I'm happy today!", demoable.getDescription());
  }
}