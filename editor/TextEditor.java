package editor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class TextEditor extends JFrame {
    JTextArea textArea;
    JMenuBar menuBarFile;
    JTextField searchField;

    public TextEditor() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 700);
        setTitle("The first stage");
        setName("Text Editor Frame");
        textArea = setTextArea();
        add(setScroll(textArea), BorderLayout.CENTER);
        add(setFileArea(), BorderLayout.NORTH);
        menuBarFile = setMenuBar();
        setJMenuBar(menuBarFile);
        setVisible(true);
    }

    private JMenuBar setMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = setFileMenu();
        JMenu searchMenu = setSearchMenu();

        menuBar.add(fileMenu);
        menuBar.add(searchMenu);

        return menuBar;
    }

    private JMenu setSearchMenu() {
        JMenu searchMenu = new JMenu("Search");
        searchMenu.setMnemonic(KeyEvent.VK_S);
        searchMenu.setName("MenuSearch");

        JMenuItem startSearch = new JMenuItem("Start search");
        searchMenu.setName("MenuStartSearch");
        JMenuItem previousSearch = new JMenuItem("Previous search");
        searchMenu.setName("MenuPreviousMatch");
        JMenuItem nextMatch = new JMenuItem("Next match");
        searchMenu.setName("MenuNextMatch");
        JMenuItem useRegEx = new JMenuItem("Use regular expressions");
        searchMenu.setName("MenuUseRegExp");

//TODO add action Listeners to menu items

        searchMenu.add(startSearch);
        searchMenu.add(previousSearch);
        searchMenu.add(nextMatch);
        searchMenu.add(useRegEx);
        return searchMenu;
    }

    private JMenu setFileMenu() {
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        fileMenu.setName("MenuFile");

        JMenuItem loadMenuItem = new JMenuItem("Open");
        loadMenuItem.setName("MenuOpen");
        JMenuItem saveMenuItem = new JMenuItem("Save");
        saveMenuItem.setName("MenuSave");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.setName("MenuExit");

        fileMenu.add(loadMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);

        loadMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectAndLoadFile();
            }
        });

        saveMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textArea.getText();
                try {
                    saveFile(text);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        exitMenuItem.addActionListener(e -> {
            this.dispose();
            System.exit(0);
        });
        return fileMenu;
    }

    private JTextArea setTextArea() {
        JTextArea textArea = new JTextArea();
        textArea.setName("TextArea");
        return textArea;
    }

    private JPanel setFileArea() {
        JPanel fileArea = new JPanel();
        searchField = new JTextField(15);
        searchField.setSize(30, 100);
        searchField.setVisible(true);
        searchField.setName("SearchField");

        JButton saveButton = setSaveButton();
        JButton loadButton = setLoadButton();
        JButton startSearchButton = setStartSearchButton();
        JButton previousMatchButton = setPreviousMatchButton();
        JButton nextMatchButton = setNextMatchButton();
        JCheckBox useRegEx = setUseRegExCheckBox();

        fileArea.add(loadButton);
        fileArea.add(saveButton);
        fileArea.add(searchField);
        fileArea.add(startSearchButton);
        fileArea.add(previousMatchButton);
        fileArea.add(nextMatchButton);
        fileArea.add(useRegEx);

        return fileArea;
    }

    private JCheckBox setUseRegExCheckBox() {
        JCheckBox useRegEx = new JCheckBox("Use regex");
        useRegEx.setName("UseRegExCheckbox");

        //todo add action listener
        return useRegEx;
    }

    private JButton setNextMatchButton() {
        ImageIcon icon = new ImageIcon(".\\Text Editor\\task\\src\\resources\\next-icon.png");
        JButton next = new JButton(icon);
        next.setName("NextMatchButton");
        //TODO add action listener

        return next;
    }

    private JButton setPreviousMatchButton() {
        ImageIcon icon = new ImageIcon(".\\Text Editor\\task\\src\\resources\\previous-icon.png");
        JButton previous = new JButton(icon);
        previous.setName("PreviousMatchButton");
        //TODO add Action Listener

        return previous;
    }

    private JButton setStartSearchButton() {
        ImageIcon icon = new ImageIcon(".\\Text Editor\\task\\src\\resources\\search-icon.png");
        JButton search = new JButton(icon);
        search.setName("StartSearchButton");

        //TODO add action Listener
        return search;
    }

    private JButton setLoadButton() {
        ImageIcon loadIcon = new ImageIcon(".\\Text Editor\\task\\src\\resources\\open-icon.png");
        JButton loadButton = new JButton(loadIcon);
        loadButton.setName("LoadButton");
        loadButton.addActionListener(e -> {
            selectAndLoadFile();
        });

        return loadButton;
    }

    private JButton setSaveButton() {
        ImageIcon saveIcon = new ImageIcon(".\\Text Editor\\task\\src\\resources\\Save-icon.png");
        JButton saveButton = new JButton(saveIcon);
        saveButton.setName("SaveButton");
        saveButton.addActionListener(e -> {
            String text = textArea.getText();
            try {
                saveFile(text);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        return saveButton;
    }

    private void selectAndLoadFile() {
        JFileChooser fileChooser = new JFileChooser(".\\Text Editor\\task\\src");
        fileChooser.showOpenDialog(null);
        File file = fileChooser.getSelectedFile();
        if (file != null) {
            try {
                loadFile(file);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void loadFile(File file) throws IOException {
        String text = file.isFile() ? Files.readString(file.toPath()) : "";
        textArea.setText(text);
    }

    private void saveFile(String text) throws IOException {
        JFileChooser fileChooser = new JFileChooser(".\\Text Editor\\task\\src");
        fileChooser.showSaveDialog(null);
        File file = fileChooser.getSelectedFile();
//        File file = new File("F:" + File.separator + "Text Editor" + File.separator + "Text Editor" + File.separator + "task" + File.separator + "src" + File.separator + name);
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
