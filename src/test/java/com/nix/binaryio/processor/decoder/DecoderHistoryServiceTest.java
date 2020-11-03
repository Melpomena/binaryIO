package com.nix.binaryio.processor.decoder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class DecoderHistoryServiceTest {

  private DecoderHistoryService decoderHistoryService;

  @Test
  public void testWrite() {
    decoderHistoryService = new DecoderHistoryService(5);
    byte[] valuesIn = new byte[]{1, 2, 3};

    decoderHistoryService.write(valuesIn);
    assertEquals(3, decoderHistoryService.getCurrentIndex());
    assertEquals(valuesIn[0], decoderHistoryService.getHistory()[0]);
    assertEquals(valuesIn[1], decoderHistoryService.getHistory()[1]);
    assertEquals(valuesIn[2], decoderHistoryService.getHistory()[2]);
  }

  @Test
  public void testWriteMoreThanMaxSize() {
    decoderHistoryService = new DecoderHistoryService(5);
    byte[] valuesIn = new byte[]{1, 2, 3, 4, 5, 6, 7};

    decoderHistoryService.write(valuesIn);
    assertEquals(2, decoderHistoryService.getCurrentIndex());
    assertEquals(valuesIn[5], decoderHistoryService.getHistory()[0]);
    assertEquals(valuesIn[6], decoderHistoryService.getHistory()[1]);
  }

  @Test
  public void testRead() {
    decoderHistoryService = new DecoderHistoryService(5);
    byte[] valuesIn = new byte[]{1, 2, 3};

    decoderHistoryService.write(valuesIn);

    byte[] valuesOut = decoderHistoryService.read(2, 2);
    assertEquals(valuesIn[1], valuesOut[0]);
    assertEquals(valuesIn[2], valuesOut[1]);
  }

  @Test
  public void testIsValidRepetitionsZero() {
    decoderHistoryService = new DecoderHistoryService(5);
    byte[] valuesIn = new byte[]{1, 2, 3};

    decoderHistoryService.write(valuesIn);

    assertFalse(decoderHistoryService.isValid(7));
    assertTrue(decoderHistoryService.isValid(2));
  }


  @Test
  public void testIsValidRepetitionsGreaterThanZero() {
    decoderHistoryService = new DecoderHistoryService(5);
    byte[] valuesIn = new byte[]{1, 2, 3, 4, 5, 6, 7};

    decoderHistoryService.write(valuesIn);

    assertTrue(decoderHistoryService.isValid(7));
    assertTrue(decoderHistoryService.isValid(2));
  }

}
