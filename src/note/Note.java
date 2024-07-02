package note;

import component.TitleBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Note extends JPanel {
    private final JTextArea textArea;
    private File file;

    public Note() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(600, 400));

        try{ UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel"); }
        catch(Exception e){ }

        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        TitleBar titleBar = new TitleBar("NoTitle", this::closeNoteApp);
        add(titleBar, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton newButton = new JButton("New");
        JButton loadButton = new JButton("Load");
        JButton saveButton = new JButton("Save");
        JButton saveAsButton = new JButton("Save As");
        buttonPanel.add(newButton);
        buttonPanel.add(loadButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(saveAsButton);
        add(buttonPanel, BorderLayout.SOUTH);

        newButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
                titleBar.setTitleLabel("NoTitle");
//                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(Note.this);
//                if (frame != null) {
//                    frame.setTitle("NoTitle");
//                }
            }
        });

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                if (fileChooser.showOpenDialog(Note.this) == JFileChooser.CANCEL_OPTION)
                    return;
                file = fileChooser.getSelectedFile();
                titleBar.setTitleLabel(file.getName());
//                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(Note.this);
//                if (frame != null) {
//                    frame.setTitle(file.getName());
//                }
                fileRead(file);
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (titleBar.getTitleLabel().getText().equals("NoTitle")) {
                    saveAsButton.doClick();
                }
                else{
                    fileSave(file);
                }
            }
        });

        saveAsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                if (fileChooser.showSaveDialog(Note.this) == JFileChooser.CANCEL_OPTION)
                    return;
                file = fileChooser.getSelectedFile();
                fileSave(file);
                titleBar.setTitleLabel(file.getName());
            }
        });
    }

    private void closeNoteApp() {
        this.setVisible(false);
    }

    private void fileRead(File file) {
        try {
            FileReader fileReader = new FileReader(file);
            StringWriter stringWriter = new StringWriter();
            while (true) {
                int oneChar = fileReader.read();
                if (oneChar == -1)
                    break;
                stringWriter.write(oneChar);
            }
            textArea.setText(stringWriter.toString());
            stringWriter.close();
            fileReader.close();
        } catch (FileNotFoundException fileNotFoundException) {
            JOptionPane.showMessageDialog(this, "File Not Found");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Something wrong with IO");
        }
    }

    private void fileSave(File file) {
        try {
            PrintStream printStream = new PrintStream(file);
            printStream.println(textArea.getText());
            printStream.close();
        } catch (FileNotFoundException fileNotFoundException) {
            JOptionPane.showMessageDialog(this, "File Not Found");
        }
    }
}
