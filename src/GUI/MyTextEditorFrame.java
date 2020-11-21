/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Service.WriteFile;
import java.awt.print.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.filechooser.*;
import javax.swing.text.*;
import javax.swing.undo.*;

/**
 *
 * @author harry
 */
public class MyTextEditorFrame extends javax.swing.JFrame {

    String temp;
    String findStr;
    boolean findDown;
    PrinterJob pj;
    boolean isNewFile;
    String filePath;
    String textoriginal;
    private UndoManager um;
    int count = 0;
    Date currentDate = new Date();
    boolean isSave = false;
    

    /**
     * Creates new form MyTextEditorFrame
     */
    public MyTextEditorFrame() {
        initComponents();
        um = new UndoManager();
        Document doc = this.jTextAreaNote.getDocument();
        doc.addUndoableEditListener(new UndoableEditListener() {
            @Override
            public void undoableEditHappened(UndoableEditEvent e) {
                um.addEdit(e.getEdit());
            }
        });
        setNotepad();
    }

    public void setNotepad() {
        this.setTitle("My Text Editor");
        temp = jTextAreaNote.getText();
        findDown = true;
        findStr = "";
        isNewFile = true;
        textoriginal = "";
        filePath = "";
    }

    @Override
    public void setTitle(String title) {
        super.setTitle(title + " (MTE)"); //To change body of generated methods, choose Tools | Templates.
    }

    public void setFindStr(String findStr) {
        this.findStr = findStr;
    }

    public void setFindDown(boolean findDown) {
        this.findDown = findDown;
    }

    public JTextArea getjTextAreaNote() {
        return jTextAreaNote;
    }

    public String getFindStr() {
        return findStr;
    }

    private boolean Verify_Save() {
        if (!textoriginal.equals(jTextAreaNote.getText())) {
            int result;
            Object[] options = {"Save", "Don't save", "Cancel"};
            if (!isNewFile) {
                result = JOptionPane.showOptionDialog(this,
                        "Do you want to save change to"
                        + filePath, "Notepad",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            } else {
                String file = this.getTitle().replaceAll("- Notepad", "");
                result = JOptionPane.showOptionDialog(this,
                        "Do you want to save change to"
                        + file, "Notepad",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            }
            if (result == JOptionPane.YES_OPTION) {
                if (!isNewFile) {
                    try {
                        WriteFile wf = new WriteFile(filePath, jTextAreaNote.getText());
                        wf.Write();
                    } catch (Exception e) {

                    }
                } else {
                    JFileChooser save = new JFileChooser();
                    FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
                    save.setFileFilter(filter);
                    int option = save.showSaveDialog(this);
                    if (option == JFileChooser.APPROVE_OPTION) {

                        try {
                            BufferedWriter out = new BufferedWriter(new FileWriter(save.getSelectedFile().getPath() + ".txt"));
                            out.write(this.jTextAreaNote.getText());
                            out.close();
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                    }
                }
            } else if (result == JOptionPane.CANCEL_OPTION) {
                return false;

            }
        }
        return true;
    }

    public void ReplaceAll(String content) {
        String t = jTextAreaNote.getText();
        t = t.replaceAll(findStr, content);
        jTextAreaNote.setText(t);
    }

    public void FindText() {
        String t = jTextAreaNote.getText();
        int posStart = jTextAreaNote.getCaretPosition();
        int pos;
        if (findDown) {
            pos = t.indexOf(findStr, posStart);
            if (pos == -1) {
                JOptionPane.showMessageDialog(this, "Not found!");
                jTextAreaNote.setCaretPosition(posStart);
            } else {
                jTextAreaNote.select(pos, pos + findStr.length());
            }
        } else {
            if (jTextAreaNote.getSelectedText() != null) {
                posStart -= jTextAreaNote.getSelectedText().length();
            }
            t = t.substring(0, posStart);
            pos = t.lastIndexOf(findStr);
            if (pos == -1) {
                JOptionPane.showMessageDialog(this, "Not found!");
                jTextAreaNote.setCaretPosition(posStart);
            } else {
                jTextAreaNote.select(pos, pos + findStr.length());
            }
        }
    }

    public void ReplaceText(String content) {
        if (jTextAreaNote.getSelectedText() != null) {
            jTextAreaNote.replaceSelection(content);
        }
        String t = jTextAreaNote.getText();
        int posStart = jTextAreaNote.getCaretPosition();
        int pos = t.indexOf(findStr, posStart);
        if (pos == -1) {
            posStart = 0;
            pos = t.indexOf(findStr, posStart);
            if (pos == -1) {
                JOptionPane.showMessageDialog(this, "Not found!");
                return;
            }
        }
        jTextAreaNote.select(pos, pos + findStr.length());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaNote = new javax.swing.JTextArea();
        jMenuBar = new javax.swing.JMenuBar();
        jMenuFile = new javax.swing.JMenu();
        jMenuItemNew = new javax.swing.JMenuItem();
        jMenuItemOpen = new javax.swing.JMenuItem();
        jMenuItemSave = new javax.swing.JMenuItem();
        jMenuItemSaveAs = new javax.swing.JMenuItem();
        jSeparatorFile1 = new javax.swing.JPopupMenu.Separator();
        jMenuItemPrint = new javax.swing.JMenuItem();
        jSeparatorFile2 = new javax.swing.JPopupMenu.Separator();
        jMenuItemExit = new javax.swing.JMenuItem();
        jMenuEdit = new javax.swing.JMenu();
        jMenuItemUndo = new javax.swing.JMenuItem();
        jMenuItemRedo = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItemCut = new javax.swing.JMenuItem();
        jMenuItemCopy = new javax.swing.JMenuItem();
        jMenuItemPaste = new javax.swing.JMenuItem();
        jMenuItemDel = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItemFind = new javax.swing.JMenuItem();
        jMenuItemReplace = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jMenuItemSelectAll = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuFormat = new javax.swing.JMenu();
        jMenuItemWordWrap = new javax.swing.JMenuItem();
        jMenuItemFont = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextAreaNote.setColumns(20);
        jTextAreaNote.setRows(5);
        jScrollPane1.setViewportView(jTextAreaNote);

        jMenuFile.setMnemonic('f');
        jMenuFile.setText("File");

        jMenuItemNew.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemNew.setMnemonic('n');
        jMenuItemNew.setText("New");
        jMenuItemNew.setToolTipText("");
        jMenuItemNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemNewActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemNew);

        jMenuItemOpen.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemOpen.setMnemonic('o');
        jMenuItemOpen.setText("Open");
        jMenuItemOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemOpenActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemOpen);

        jMenuItemSave.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemSave.setMnemonic('s');
        jMenuItemSave.setText("Save");
        jMenuItemSave.setToolTipText("");
        jMenuItemSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSaveActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemSave);

        jMenuItemSaveAs.setMnemonic('a');
        jMenuItemSaveAs.setText("Save As...");
        jMenuItemSaveAs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSaveAsActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemSaveAs);
        jMenuFile.add(jSeparatorFile1);

        jMenuItemPrint.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemPrint.setMnemonic('p');
        jMenuItemPrint.setText("Print");
        jMenuItemPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPrintActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemPrint);
        jMenuFile.add(jSeparatorFile2);

        jMenuItemExit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        jMenuItemExit.setMnemonic('x');
        jMenuItemExit.setText("Exit");
        jMenuItemExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemExitActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemExit);

        jMenuBar.add(jMenuFile);

        jMenuEdit.setMnemonic('e');
        jMenuEdit.setText("Edit");
        jMenuEdit.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuCanceled(javax.swing.event.MenuEvent evt) {
            }
            public void menuDeselected(javax.swing.event.MenuEvent evt) {
            }
            public void menuSelected(javax.swing.event.MenuEvent evt) {
                jMenuEditMenuSelected(evt);
            }
        });

        jMenuItemUndo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemUndo.setMnemonic('u');
        jMenuItemUndo.setText("Undo");
        jMenuItemUndo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemUndoActionPerformed(evt);
            }
        });
        jMenuEdit.add(jMenuItemUndo);

        jMenuItemRedo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemRedo.setMnemonic('r');
        jMenuItemRedo.setText("Redo");
        jMenuItemRedo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemRedoActionPerformed(evt);
            }
        });
        jMenuEdit.add(jMenuItemRedo);
        jMenuEdit.add(jSeparator1);

        jMenuItemCut.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemCut.setMnemonic('t');
        jMenuItemCut.setText("Cut");
        jMenuItemCut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCutActionPerformed(evt);
            }
        });
        jMenuEdit.add(jMenuItemCut);

        jMenuItemCopy.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemCopy.setMnemonic('c');
        jMenuItemCopy.setText("Copy");
        jMenuItemCopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCopyActionPerformed(evt);
            }
        });
        jMenuEdit.add(jMenuItemCopy);

        jMenuItemPaste.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemPaste.setMnemonic('p');
        jMenuItemPaste.setText("Paste");
        jMenuItemPaste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPasteActionPerformed(evt);
            }
        });
        jMenuEdit.add(jMenuItemPaste);

        jMenuItemDel.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DELETE, 0));
        jMenuItemDel.setMnemonic('l');
        jMenuItemDel.setText("Delete");
        jMenuItemDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemDelActionPerformed(evt);
            }
        });
        jMenuEdit.add(jMenuItemDel);
        jMenuEdit.add(jSeparator2);

        jMenuItemFind.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemFind.setMnemonic('f');
        jMenuItemFind.setText("Find...");
        jMenuItemFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemFindActionPerformed(evt);
            }
        });
        jMenuEdit.add(jMenuItemFind);

        jMenuItemReplace.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemReplace.setMnemonic('r');
        jMenuItemReplace.setText("Replace...");
        jMenuItemReplace.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemReplaceActionPerformed(evt);
            }
        });
        jMenuEdit.add(jMenuItemReplace);
        jMenuEdit.add(jSeparator3);

        jMenuItemSelectAll.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemSelectAll.setMnemonic('a');
        jMenuItemSelectAll.setText("Select All");
        jMenuItemSelectAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSelectAllActionPerformed(evt);
            }
        });
        jMenuEdit.add(jMenuItemSelectAll);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        jMenuItem1.setMnemonic('d');
        jMenuItem1.setLabel("Date");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenuEdit.add(jMenuItem1);

        jMenuBar.add(jMenuEdit);

        jMenuFormat.setMnemonic('o');
        jMenuFormat.setText("Format");

        jMenuItemWordWrap.setMnemonic('W');
        jMenuItemWordWrap.setText("Word Wrap");
        jMenuItemWordWrap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemWordWrapActionPerformed(evt);
            }
        });
        jMenuFormat.add(jMenuItemWordWrap);

        jMenuItemFont.setMnemonic('F');
        jMenuItemFont.setText("Font...");
        jMenuItemFont.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemFontActionPerformed(evt);
            }
        });
        jMenuFormat.add(jMenuItemFont);

        jMenuBar.add(jMenuFormat);

        setJMenuBar(jMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 611, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemNewActionPerformed
        // TODO add your handling code here:
        if (!Verify_Save()) {
            return;
        }
        isNewFile = true;
        jTextAreaNote.setText("");
    }//GEN-LAST:event_jMenuItemNewActionPerformed

    private void jMenuItemOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemOpenActionPerformed
        // TODO add your handling code here:
        if (!Verify_Save()) {
            return;
        }
        JFileChooser open = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
        open.setFileFilter(filter);
        int option = open.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            this.jTextAreaNote.setText("");
            try {
                Scanner scan = new Scanner(new FileReader(open.getSelectedFile().getPath()));
                while (scan.hasNext()) {
                    this.jTextAreaNote.append(scan.nextLine() + "\n");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Can't open file!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jMenuItemOpenActionPerformed

    private void jMenuItemSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSaveActionPerformed
        // TODO add your handling code here:
        if (!textoriginal.equals(jTextAreaNote.getText())) {
            if (!isNewFile) {
                try {
                    textoriginal = jTextAreaNote.getText();
                    WriteFile wf = new WriteFile(filePath, textoriginal);
                    wf.Write();
                    isSave = true;
                } catch (Exception e) {
                }
            } else {
                JFileChooser save = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
                save.setFileFilter(filter);
                int option = save.showSaveDialog(this);
                if (option == JFileChooser.APPROVE_OPTION) {
                
                    try {
                        textoriginal = jTextAreaNote.getText();
                        filePath = save.getSelectedFile().getPath() + ".txt";
                        WriteFile wf = new WriteFile(filePath, textoriginal);
                        wf.Write();
                        isNewFile = false;
                        isSave = true;
                        setTitle(save.getSelectedFile().getName());
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            }
        }
    }//GEN-LAST:event_jMenuItemSaveActionPerformed

    private void jMenuItemSaveAsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSaveAsActionPerformed
        // TODO add your handling code here:
        JFileChooser save = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
        save.setFileFilter(filter);
        int option = save.showSaveDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {

            try {
                textoriginal = jTextAreaNote.getText();
                filePath = save.getSelectedFile().getPath() + ".txt";
                WriteFile wf = new WriteFile(filePath, textoriginal);
                wf.Write();
                isNewFile = false;
                isSave = true;
                setTitle(save.getSelectedFile().getName());
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }//GEN-LAST:event_jMenuItemSaveAsActionPerformed

    private void jMenuItemPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPrintActionPerformed
        // TODO add your handling code here:
        try {
            jTextAreaNote.print();
        } catch (PrinterException ex) {
            Logger.getLogger(MyTextEditorFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItemPrintActionPerformed

    private void jMenuItemExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemExitActionPerformed
        // TODO add your handling code here:
        if (!Verify_Save()) {
            return;
        }
        System.exit(0);
    }//GEN-LAST:event_jMenuItemExitActionPerformed

    private void jMenuItemUndoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemUndoActionPerformed
        // TODO add your handling code here:
        if (um.canUndo()) {
            try {
                um.undo();
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_jMenuItemUndoActionPerformed

    private void jMenuItemRedoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemRedoActionPerformed
        // TODO add your handling code here:
        if (um.canRedo()) {
            try {
                um.redo();
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_jMenuItemRedoActionPerformed

    private void jMenuItemCutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCutActionPerformed
        // TODO add your handling code here:
        jTextAreaNote.cut();
    }//GEN-LAST:event_jMenuItemCutActionPerformed

    private void jMenuItemCopyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCopyActionPerformed
        // TODO add your handling code here:
        jTextAreaNote.copy();
    }//GEN-LAST:event_jMenuItemCopyActionPerformed

    private void jMenuItemPasteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPasteActionPerformed
        // TODO add your handling code here:
        jTextAreaNote.paste();
    }//GEN-LAST:event_jMenuItemPasteActionPerformed

    private void jMenuItemDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemDelActionPerformed
        // TODO add your handling code here:
        jTextAreaNote.replaceSelection("");
    }//GEN-LAST:event_jMenuItemDelActionPerformed

    private void jMenuItemFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemFindActionPerformed
        // TODO add your handling code here:
        FindDialog f = new FindDialog(this, true);
        f.setVisible(true);
    }//GEN-LAST:event_jMenuItemFindActionPerformed

    private void jMenuItemReplaceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemReplaceActionPerformed
        // TODO add your handling code here:
        ReplaceDialog r = new ReplaceDialog(this, true);
        r.setVisible(true);
    }//GEN-LAST:event_jMenuItemReplaceActionPerformed

    private void jMenuItemSelectAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSelectAllActionPerformed
        // TODO add your handling code here:
        jTextAreaNote.selectAll();
    }//GEN-LAST:event_jMenuItemSelectAllActionPerformed

    private void jMenuItemFontActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemFontActionPerformed
        // TODO add your handling code here:
        FontDialog f = new FontDialog(this, true);
        f.setVisible(true);
    }//GEN-LAST:event_jMenuItemFontActionPerformed

    private void jMenuEditMenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_jMenuEditMenuSelected
        // TODO add your handling code here:
        boolean setFind = !"".equals(jTextAreaNote.getText());
        jMenuItemFind.setEnabled(setFind);
        boolean setEdit = !(jTextAreaNote.getSelectedText() == null);
        jMenuItemCopy.setEnabled(setEdit);
        jMenuItemCut.setEnabled(setEdit);
        jMenuItemDel.setEnabled(setEdit);
    }//GEN-LAST:event_jMenuEditMenuSelected

    private void jMenuItemWordWrapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemWordWrapActionPerformed
        // TODO add your handling code here:
        count++;
        if (count % 2 == 1) {
            jTextAreaNote.setLineWrap(true);
            jTextAreaNote.setWrapStyleWord(true);
        } else {
            jTextAreaNote.setLineWrap(false);
            jTextAreaNote.setWrapStyleWord(false);
        }

    }//GEN-LAST:event_jMenuItemWordWrapActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss aa");
        Date date = cal.getTime();
        String timeString = formatter.format(date);
        SimpleDateFormat dat = new SimpleDateFormat("EEEE, MMMM d, yyyy");

        jTextAreaNote.setText(timeString + " " + dat.format(currentDate));

    }//GEN-LAST:event_jMenuItem1ActionPerformed

    
    
//    public void windowClosing(java.awt.event.WindowEvent evt) {
//        if (JOptionPane.showConfirmDialog(this, 
//            "Are you sure you want to close this window?", "Close Window?", 
//            JOptionPane.YES_NO_OPTION,
//            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
//            System.exit(0);
//        }
//    }
   
    public void formWindowClosing(java.awt.event.WindowEvent evt) {
        // TODO add your handling code here:
        if (!Verify_Save()) {
            return;
        }
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MyTextEditorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MyTextEditorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MyTextEditorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MyTextEditorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MyTextEditorFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JMenu jMenuEdit;
    private javax.swing.JMenu jMenuFile;
    private javax.swing.JMenu jMenuFormat;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItemCopy;
    private javax.swing.JMenuItem jMenuItemCut;
    private javax.swing.JMenuItem jMenuItemDel;
    private javax.swing.JMenuItem jMenuItemExit;
    private javax.swing.JMenuItem jMenuItemFind;
    private javax.swing.JMenuItem jMenuItemFont;
    private javax.swing.JMenuItem jMenuItemNew;
    private javax.swing.JMenuItem jMenuItemOpen;
    private javax.swing.JMenuItem jMenuItemPaste;
    private javax.swing.JMenuItem jMenuItemPrint;
    private javax.swing.JMenuItem jMenuItemRedo;
    private javax.swing.JMenuItem jMenuItemReplace;
    private javax.swing.JMenuItem jMenuItemSave;
    private javax.swing.JMenuItem jMenuItemSaveAs;
    private javax.swing.JMenuItem jMenuItemSelectAll;
    private javax.swing.JMenuItem jMenuItemUndo;
    private javax.swing.JMenuItem jMenuItemWordWrap;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparatorFile1;
    private javax.swing.JPopupMenu.Separator jSeparatorFile2;
    private javax.swing.JTextArea jTextAreaNote;
    // End of variables declaration//GEN-END:variables
}
