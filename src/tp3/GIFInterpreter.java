package tp3;

import java.io.*;

/**
 * User: Martin Gutierrez
 * Date: 28/08/13
 * Time: 23:50
 */
public class GIFInterpreter {

    public static void main(String[] args) {
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;


        try {
            fileInputStream = new FileInputStream("test.gif");
            fileOutputStream = new FileOutputStream("result.gif");
            int read = fileInputStream.read();
            int i = 0;
            while (read != -1) {
                if (i >= 12 && i < 780) {

                    //BLANCO Y NEGRO

                    /*int read1 = fileInputStream.read();         //GREEN
                    i++;
                    int read2 = fileInputStream.read();              //BLUE
                    i++;
                    int result = (read + read1 + read2) / 3;
                    fileOutputStream.write(result);
                    fileOutputStream.write(result);
                    fileOutputStream.write(result);*/

                    //NEGATIVO

                    /*fileOutputStream.write(255 - read);
                    int read1 = fileInputStream.read();
                    i++;
                    fileOutputStream.write(255 - read1);
                    int read2 = fileInputStream.read();
                    i++;
                    fileOutputStream.write(255 - read2);*/

                    //FLASHERO - BGR grabado como RGB

                    int read1 = fileInputStream.read();
                    i++;
                    int read2 = fileInputStream.read();
                    i++;
                    fileOutputStream.write(read2);
                    fileOutputStream.write(read1);
                    fileOutputStream.write(read);

                } else {
                    fileOutputStream.write(read);
                }
                if (i <= 1000) System.out.println(i++ + "\t" + (char) read + "\t" + read);
                read = fileInputStream.read();
            }
            fileOutputStream.flush();
            fileInputStream.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
