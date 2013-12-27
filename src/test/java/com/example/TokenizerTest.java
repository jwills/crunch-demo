package com.example;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import org.apache.crunch.Emitter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class TokenizerTest {
  @Mock
  private Emitter<String> emitter;

  @Test
  public void testProcess() {
    Tokenizer splitter = new Tokenizer();
    splitter.process("  foo  bar ", emitter);

    verify(emitter).emit("foo");
    verify(emitter).emit("bar");
    verifyNoMoreInteractions(emitter);
  }

}
