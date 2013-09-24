package tp2;

import javax.sound.sampled.*;
import java.io.*;

/**
 * User: Martin Gutierrez
 * Date: 01/01/12
 * Time: 14:23
 */
public class WAVPlayer {
    public static void main(String[] args) {
        try {
            SourceDataLine line;
            byte[] buffer;
            float[] signalToAnalyse;

            WAVAudioFile audioFile = new WAVAudioFile("uno.wav");

            AudioFormat format = new AudioFormat(11100, 16, 2, true, false);
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format); // format is an AudioFormat object
            if (!AudioSystem.isLineSupported(info)) {
                System.out.println("Line not supported!");
            }

            line = (SourceDataLine) AudioSystem.getLine(info);
            line.open(format);
            line.start();

            FFT2 fft = new FFT2(1024, 11100);

            buffer = audioFile.read2048Bytes();
            signalToAnalyse = new float[1024];


            while (buffer != null) {
                line.write(buffer, 0, 2048);

                for (int i = 0; i < signalToAnalyse.length; i++) {
                    signalToAnalyse[i] = buffer[i * 2];
                }
                fft.forward(signalToAnalyse);
                for (float f : signalToAnalyse) {
                    System.out.print(f + "\t");
                }
                System.out.println();

                buffer = audioFile.read2048Bytes();
            }
            line.stop();

            for (int i = 0; i < 1024; i++) {
                System.out.print(((float)i)/(1024.0 * 0.092)  + "\t");
            }
            System.out.println();
        } catch (LineUnavailableException ex) {
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
             //To change body of catch statement use File | Settings | File Templates.
        }
        // Obtain and open the line.


    }
}
