package com.nix.binaryio.processor.decoder;

import com.nix.binaryio.processor.ByteProcessor;

/**
 * Implementation of {@link ByteProcessor} which works as a decoder. The algorithm of the decoder
 * can be found in the assignment description. Relies on {@link DecoderHistoryService} for storing
 * the last maxIndex previously decoded values for later access.
 */
public class DecoderByteProcessor implements ByteProcessor {

  private final int maxIndex = 256;
  private final byte[] INVALID_BYTES = "3F".getBytes();
  private final DecoderHistoryService decoderHistoryService;

  public DecoderByteProcessor() {
    this.decoderHistoryService = new DecoderHistoryService(maxIndex);
  }

  @Override
  public byte[] process(byte[] input) {
    // check if two bytes
    if (input.length != 2) {
      return INVALID_BYTES;
    }

    int pi = Byte.toUnsignedInt(input[0]);
    int qi = Byte.toUnsignedInt(input[1]);

    byte[] decodedValues;

    if (pi == 0) {
      decodedValues = new byte[]{input[1]};
      decoderHistoryService.write(decodedValues);
    } else if (pi < qi) {
      return INVALID_BYTES;
    } else {

      if (!decoderHistoryService.isValid(pi)) {
        return INVALID_BYTES;
      }

      decodedValues = decoderHistoryService.read(pi, qi);

      decoderHistoryService.write(decodedValues);
    }

    return decodedValues;
  }
}
