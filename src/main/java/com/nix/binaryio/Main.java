package com.nix.binaryio;

import com.nix.binaryio.processor.ByteProcessor;
import com.nix.binaryio.processor.decoder.DecoderByteProcessor;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

public class Main {

  public static void main(String[] args) throws IOException {
    InputStream inputStream = new BufferedInputStream(System.in);
    PrintStream ps = new PrintStream(System.out);

    byte[] inputBytes = new byte[2];
    int bytesRead = inputStream.read(inputBytes);

    ByteProcessor byteProcessor = new DecoderByteProcessor();
    while (bytesRead != -1) {
      ps.write(byteProcessor.process(inputBytes));
      bytesRead = inputStream.read(inputBytes);
    }

    /*
    PrintStream ps = new PrintStream(System.out);
    ByteProcessor byteProcessor = new DecoderByteProcessor(256);
    byte[] inputBytes2 = new byte[]{0, 61, 1, 1, 0, 62, 3, 2, 3, 3};
    byte[] two = new byte[2];

    for (int i = 0; i < inputBytes2.length; i = i + 2) {
      two[0] = inputBytes2[i];
      two[1] = inputBytes2[i + 1];
      ps.write(byteProcessor.process(two));
    }
    */
  }
}
