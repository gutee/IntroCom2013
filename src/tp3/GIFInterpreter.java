package tp3;

import java.io.*;
import java.util.Scanner;

/**
 * User: Martin Gutierrez
 * Date: 28/08/13
 * Time: 23:50
 */
public class GIFInterpreter {

    public static void main(String[] args) {
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;

        System.out.println("1.RGB to BGR.\n2.Black and White.\n3.Negative\n\nMake your choice:");

        Scanner scanner = new Scanner(System.in);
        int result = scanner.nextInt();
        if(result == 1){
            BGRasRGB(fileInputStream, fileOutputStream);
        } else if (result == 2){
            blackAndWhite(fileInputStream, fileOutputStream);
        } else if (result == 3){
            negative(fileInputStream, fileOutputStream);
        }


        BGRasRGB(fileInputStream, fileOutputStream);
    }

    private static void BGRasRGB(FileInputStream fileInputStream, FileOutputStream fileOutputStream) {
        try {
            fileInputStream = new FileInputStream("test.gif");
            fileOutputStream = new FileOutputStream("resultBGR.gif");
            int read = fileInputStream.read();
            int i = 0;
            while (read != -1) {
                if (i >= 12 && i < 780) {
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

    private static void blackAndWhite(FileInputStream fileInputStream, FileOutputStream fileOutputStream) {

        try {
            fileInputStream = new FileInputStream("test.gif");
            fileOutputStream = new FileOutputStream("resultBW.gif");
            int read = fileInputStream.read();
            int i = 0;
            while (read != -1) {
                if (i >= 12 && i < 780) {
                    int read1 = fileInputStream.read();         //GREEN
                    i++;
                    int read2 = fileInputStream.read();              //BLUE
                    i++;
                    int result = (read + read1 + read2) / 3;
                    fileOutputStream.write(result);
                    fileOutputStream.write(result);
                    fileOutputStream.write(result);

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
    }private static void negative(FileInputStream fileInputStream, FileOutputStream fileOutputStream) {

        try {
            fileInputStream = new FileInputStream("test.gif");
            fileOutputStream = new FileOutputStream("resultNegative.gif");
            int read = fileInputStream.read();
            int i = 0;
            while (read != -1) {
                if (i >= 12 && i < 780) {

                    fileOutputStream.write(255 - read);
                    int read1 = fileInputStream.read();
                    i++;
                    fileOutputStream.write(255 - read1);
                    int read2 = fileInputStream.read();
                    i++;
                    fileOutputStream.write(255 - read2);

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
