package MyFrame.Client.ClientLogics;

import javax.swing.*;
import java.io.*;

public final class IO {

    public static boolean openFile(JTextArea textArea) {
        JFileChooser fs = new JFileChooser();
        File filename;
        int choose = 0;
        fs.showDialog(null, "Открыть");
        filename = fs.getSelectedFile();
        if (filename == null) return false;
        if (!filename.exists()) {
            choose = JOptionPane.showConfirmDialog(null, "Нет такого файла.",
                    "Внимание!", JOptionPane.OK_OPTION);
            return false;
        }
        String s;
        BufferedReader buffReader = null;
        try {
            buffReader = new BufferedReader(new FileReader(filename));
            while ((s = buffReader.readLine()) != null) {
                textArea.append(s);
                textArea.append("\n");
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (buffReader != null) buffReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }
    }

    public static boolean saveFile(JTextArea textArea) {
        JFileChooser fs = new JFileChooser();
        File filename;
        int choose = 0;
        fs.showDialog(null, "Сохранить");
        filename = fs.getSelectedFile();
        if (filename == null) return false;
        if (filename.exists())
            choose = JOptionPane.showConfirmDialog(null, "Такой файл уже существует. Перезаписать его?",
                    "Внимание!", JOptionPane.YES_NO_OPTION);
        if (choose == 1) return false;
        String txt = textArea.getText();
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(filename));

            bufferedWriter.append(txt);

        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.flush();
                    bufferedWriter.close();
                }

            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return false;
        }
    }

}
