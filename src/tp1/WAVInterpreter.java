package tp1;


import java.io.*;
import java.util.ArrayList;

/**
 * User: Martin Gutierrez
 * Date: 01/01/12
 * Time: 14:23
 */
public class WAVInterpreter {
    public static void main(String[] args) {
        FileInputStream fileInputStream = null;
        InputStreamReader inputStreamReader = null;
        FileOutputStream fileOutputStream = null;

        ArrayList<Integer> integers = new ArrayList<Integer>();

        try {
            fileInputStream = new FileInputStream("uno.wav");
            inputStreamReader = new InputStreamReader(fileInputStream);
            fileOutputStream = new FileOutputStream("dos.wav");
            int read = fileInputStream.read();
            int i = 0;
            while (read != -1) {
                if (i == 40){
                    fileOutputStream.write(32);
                }
                if (i == 41){
                    fileOutputStream.write(245);
                }
                if (i == 42){
                    fileOutputStream.write(13);
                }
                if (i >= 44 && i <= 457404){
                    integers.add(read);
                    fileOutputStream.write(read);
                    int read1 = fileInputStream.read();
                    i++;
                    fileOutputStream.write(read1);

                    fileOutputStream.write(read);
                    fileOutputStream.write(read1);
                } else {
                    if (i != 40 && i != 41 && i != 42){
                        fileOutputStream.write(read);
                    }
                }
                System.out.println(i++ +"\t"+(char)read +  "\t" + read);
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

        //JFrame jFrame = new JFrame("WAV");
        //Object o[][] = new Object[integers.size()][1];
        //for (int i = 0; i < integers.size(); i++){
        //    o[i][0] = integers.get(i);
        //}
        //Object on[] = {"Bytes"};
        //JTable table = new JTable(new DefaultTableModel(o,on));
        //table.getSelectionModel().setSelectionInterval(0,1000);
        //jFrame.add(new JScrollPane(table));
        //jFrame.setMinimumSize(new Dimension(1000, 500));

        //jFrame.setLocationRelativeTo(null);
        //jFrame.setVisible(true);



    }
}
