package com.nix.binaryio.processor.decoder;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class DecoderByteProcessorTest {

  private final byte[] INVALID_BYTES = "3F".getBytes();
  private DecoderByteProcessor decoderByteProcessor;

  @Test
  public void testInvalidInputSize() {
    decoderByteProcessor = new DecoderByteProcessor();
    byte[] inputValues = new byte[]{1};

    assertArrayEquals(INVALID_BYTES, decoderByteProcessor.process(inputValues));
  }

  @Test
  public void testInvalidPiQi() {
    decoderByteProcessor = new DecoderByteProcessor();
    byte[] inputValues = new byte[]{1, 7};

    assertArrayEquals(INVALID_BYTES, decoderByteProcessor.process(inputValues));
  }

}
