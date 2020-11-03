package com.nix.binaryio.processor.simple;

import com.nix.binaryio.processor.ByteProcessor;

/**
 * Implementation of {@link ByteProcessor} interface which iterates over an array of bytes and
 * increases the value of each byte by one.
 */
public class SimpleByteProcessor implements ByteProcessor {

  @Override
  public byte[] process(byte[] input) {
    for (int i = 0; i < input.length; ++i) {
      input[i]++;
    }
    return input;
  }
}
