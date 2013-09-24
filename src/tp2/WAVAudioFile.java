package tp2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Javi
 * Date: 9/9/13
 * Time: 10:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class WAVAudioFile {
    private FileInputStream fileInputStream = null;
    private int fileBitIndex = 0;
    private int audioBitIndex = 0;
    private byte[] buffer32 = new byte[32];
    private byte[] buffer2048 = new byte[2048];
    private byte[] audioData = new byte[457360];

    public WAVAudioFile(String filePath) throws IOException {
        fileInputStream = new FileInputStream(filePath);
        for (; fileBitIndex < 44; fileBitIndex++) {
            fileInputStream.read();
        }
        for (int i = 0; i < 457360; i++) {
            audioData[i] = (byte) fileInputStream.read();
            fileBitIndex++;
        }

    }

    public byte[] read32Bytes() throws IOException {
        for (int i = 0; i < 32; i++) {
            if (audioBitIndex >= audioData.length){
                return null;
            }
            buffer32[i] = audioData[audioBitIndex++];
        }
        return buffer32;
    }

    public byte[] read2048Bytes() {
        for (int i = 0; i < 2048; i++) {
            if (audioBitIndex >= audioData.length){
                return null;
            }
            buffer2048[i] = audioData[audioBitIndex++];
        }
        return buffer2048;
    }
}
