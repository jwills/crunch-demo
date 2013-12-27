package com.example;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import org.apache.crunch.FilterFn;
import org.junit.Test;


public class StopWordFilterTest {

  @Test
  public void testFilter() {
    FilterFn<String> filter = new StopWordFilter();

    assertThat(filter.accept("foo"), is(true));
    assertThat(filter.accept("the"), is(false));
    assertThat(filter.accept("a"), is(false));
  }

}
