package editor;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class TextEditor extends JFrame {
    JTextArea textArea;

    public TextEditor() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setTitle("The first stage");
        setName("Text Editor Frame");
        textArea = setTextArea();
        add(setScroll(textArea), BorderLayout.CENTER);
        add(setFileArea(), BorderLayout.NORTH);
        setVisible(true);
    }

    private JTextArea setTextArea() {
        JTextArea textArea = new JTextArea();
        textArea.setName("TextArea");
        return textArea;
    }

    private JPanel setFileArea() {
        JPanel fileArea = new JPanel();
        JTextField fileInputField = new JTextField(15);
        fileInputField.setSize(30, 100);
        fileInputField.setVisible(true);
        fileInputField.setName("FilenameField");
        JButton saveButton = new JButton();
        saveButton.setName("SaveButton");
        saveButton.setText("Save");
        saveButton.addActionListener(e -> {
            String file = fileInputField.getText();
            String text = textArea.getText();
            try {
                saveFile(file, text);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        JButton loadButton = new JButton();
        loadButton.setName("LoadButton");
        loadButton.setText("Load");
        loadButton.addActionListener(e -> {
            String file = fileInputField.getText();
            try {
                loadFile(file);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        fileArea.add(fileInputField);
        fileArea.add(saveButton);
        fileArea.add(loadButton);
        return fileArea;
    }

    private void loadFile(String name) throws IOException {
        File file = new File("F:" + File.separator + "Text Editor" + File.separator + "Text Editor" + File.separator + "task" + File.separator + "src" + File.separator + name);
        String text = file.isFile() ? Files.readString(file.toPath()) : "";
        textArea.setText(text);
    }

    private void saveFile(String name, String text) throws IOException {
        File file = new File("F:" + File.separator + "Text Editor" + File.separator + "Text Editor" + File.separator + "task" + File.separator + "src" + File.separator + name);
        FileWriter writer = new FileWriter(file);
        writer.write(text);
        writer.close();
    }

    private JScrollPane setScroll(JTextArea textArea) {
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setName("ScrollPane");
        scrollPane.setVisible(true);
        return scrollPane;
    }


}
