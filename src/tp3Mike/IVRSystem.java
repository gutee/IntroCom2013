package tp3Mike;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.SourceDataLine;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * User: Martin Gutierrez
 * Date: 13/10/13
 * Time: 13:38
 */
public class IVRSystem {
    private static final String WAVS_DIRECTORY = "tp3Wavs/";

    public static void main(String[] args) {
        List<String> wavs = new ArrayList<String>();
        String flightNumber;
        wavs.add("bienvenida.wav");
        play(wavs);
        wavs.clear();

        System.out.println("INGRESE EL NÚMERO DE CLIENTE:");
        Scanner scanner = new Scanner(System.in);
        String clientNumberString = scanner.next();

        wavs.add("elNumeroDeCliente.wav");
        addNumbers(wavs, clientNumberString);

        if(clientNumberString.equals("3107")){
            wavs.add("tieneViajes.wav");
            play(wavs);
            wavs.clear();

            System.out.println("INGRESE NÚMERO DE VUELO:");
            scanner = new Scanner(System.in);
            flightNumber = scanner.next();
            wavs.add("elVueloNumero.wav");
            addNumbers(wavs, flightNumber);

            if(flightNumber.equals("1015")){
                wavs.add("conDestinoA.wav");
                wavs.add("uruguay.wav");
                wavs.add("fueEntregadoEl.wav");
                wavs.add("7.wav");
                wavs.add("septiembre.wav");
                wavs.add("2013.wav");
                play(wavs);
                wavs.clear();
            } else if(flightNumber.equals("1034")){
                wavs.add("conDestinoA.wav");
                wavs.add("paraguay.wav");
                wavs.add("estaPendiente.wav");
                play(wavs);
                wavs.clear();
            } else {
                wavs.add("noExiste.wav");
                play(wavs);
                wavs.clear();
            }
        } else {
            wavs.add("noTieneViajes.wav");
            play(wavs);
            wavs.clear();
        }
    }

    public static void addNumbers(List<String> list, String numberString){
        for (int i = 0; i < numberString.length(); i++) {
            list.add(numberString.charAt(i) + ".wav");
        }
    }

    public static void play(List<String> files){
        byte[] buffer = new byte[4096];
        for (String filePath : files) {
            File file = new File(WAVS_DIRECTORY + filePath);
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
}
