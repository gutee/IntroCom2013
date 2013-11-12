package tp4Mike;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * User: Martin Gutierrez
 * Date: 12/11/13
 * Time: 10:01
 */
public class SuperIVRSystem {
    private static final String WAVS_DIRECTORY = "tp4Wavs/";

    public static void main(String[] args) throws Exception {

        Integer result = 1;
        String text;
        String[] splitText;
        List<String> wavs;
        List<String> words;

        while(result != 0){

            words = new ArrayList<String>();

            System.out.println("1. Enter words (with A-E-I-O-U-P-T)");
            System.out.println("0. EXIT");
            Scanner scanner = new Scanner(System.in);
            result = scanner.nextInt();

            switch (result){
                case 0:
                    break;

                case 1:
                    text = JOptionPane.showInputDialog("Enter Text: ");

                    splitText = text.split("\\s+");


                    for (String s : splitText) {
                        wavs = new ArrayList<String>();


                        wavs.add("_" + s.charAt(0) + ".wav");

                        for(int i = 1; i < s.length(); i++){
                            wavs.add(s.charAt(i-1) + "" + s.charAt(i) + ".wav");
                        }

                        wavs.add(s.charAt(s.length()-1) + "_.wav");

                        concatenateFiles(wavs, s + ".wav");
                        words.add(s + ".wav");

                    }


                    System.out.println("Playing....");


                    play(words);

                    break;

                default:
                    System.out.println("Flasheaste!");
            }
        }
    }


    public static void play(List<String> files){
        byte[] buffer = new byte[4096];
        for (String filePath : files) {
            File file = new File(filePath);
            try {
                AudioInputStream is = AudioSystem.getAudioInputStream(file);
                AudioFormat format = is.getFormat();
                SourceDataLine line = AudioSystem.getSourceDataLine(format);
                line.open(format);
                line.start();
                while (is.available() > 0) {
                    int len = is.read(buffer);
                    line.write(buffer, 0, len);
                }
                line.drain();
                line.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static Boolean concatenateFiles(List<String> sourceFilesList, String destinationFileName) throws Exception {
        Boolean result = false;

        AudioInputStream audioInputStream = null;
        List<AudioInputStream> audioInputStreamList = null;
        AudioFormat audioFormat = null;
        Long frameLength = null;

        try {
            // loop through our files first and load them up
            for (String sourceFile : sourceFilesList) {
                audioInputStream = AudioSystem.getAudioInputStream(new File(WAVS_DIRECTORY + sourceFile));

                // get the format of first file
                if (audioFormat == null) {
                    audioFormat = audioInputStream.getFormat();
                }

                // add it to our stream list
                if (audioInputStreamList == null) {
                    audioInputStreamList = new ArrayList<AudioInputStream>();
                }
                audioInputStreamList.add(audioInputStream);

                // keep calculating frame length
                if(frameLength == null) {
                    frameLength = audioInputStream.getFrameLength();
                }
                else {
                    frameLength += audioInputStream.getFrameLength();
                }
            }

            // now write our concatenated file
            AudioSystem.write(new AudioInputStream(new SequenceInputStream(Collections.enumeration(audioInputStreamList)), audioFormat, frameLength), AudioFileFormat.Type.WAVE, new File(destinationFileName));

            // if all is good, return true
            result = true;
        }
        catch (Exception e) {
            throw e;
        }
        finally {
            if (audioInputStream != null) {
                audioInputStream.close();
            }
            if (audioInputStreamList != null) {
                audioInputStreamList = null;
            }
        }

        return result;
    }
}
