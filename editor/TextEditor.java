package editor;

import javax.swing.*;

public class TextEditor extends JFrame {
    public TextEditor() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setVisible(true);
        setLayout(null);
        setTitle("The first stage");
        setName("Text Editor Frame");
        add(setTextArea());

    }

    private JTextArea setTextArea() {
        JTextArea textArea = new JTextArea();
        textArea.setName("TextArea");
        textArea.setBounds(25, 25, 250, 250);
        return textArea;
    }


}
