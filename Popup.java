import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.util.*;
import java.io.*;


public class Popup extends JPanel {

    //adding multiple document listeners: include the overriden methods in their creation calls
    JTextField input1;
    String titleText;
    String loginUsername;
    String loginPassword;
    String createUsername;
    String createPassword;
    
    public Popup(String type) {
        
        if (type.equals("title")) {
            JLabel label1 = new JLabel("Enter Title:");
            this.add(label1);
            input1 = new JTextField(16);
           
            DocumentListener doc1 = new DocumentListener() {
                
                public void changedUpdate(DocumentEvent e) {
                titleText = input1.getText();
                
                
            }
                
                public void removeUpdate(DocumentEvent e) {
                    titleText = input1.getText();
               
                }
                
                public void insertUpdate(DocumentEvent e) {
                    titleText = input1.getText();
                
                   
                }
                
            };
            input1.getDocument().addDocumentListener(doc1);
            input1.setEnabled(true);
            this.add(input1);
        }
        else if (type.equals("login")) {
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            JLabel label2 = new JLabel("Enter Username:");
            this.add(label2);
            JTextField input2 = new JTextField(8);
  
            DocumentListener doc2 = new DocumentListener() {
                
                public void changedUpdate(DocumentEvent e) {
                    loginUsername = input2.getText();
                    
                }
                
                public void removeUpdate(DocumentEvent e) {
                    loginUsername = input2.getText();
                }
                
                public void insertUpdate(DocumentEvent e) {
                    loginUsername = input2.getText();
                    
                }
                
            };
            input2.getDocument().addDocumentListener(doc2);
            input2.setEnabled(true);
            this.add(input2);
            JLabel label3 = new JLabel("Enter Password:");
            this.add(label3);
            JTextField input3 = new JTextField(8);
            DocumentListener doc3 = new DocumentListener() {
                
                public void changedUpdate(DocumentEvent e) {
                    loginPassword = input2.getText();
                    
                }
                
                public void removeUpdate(DocumentEvent e) {
                    loginPassword = input2.getText();
                }
                
                public void insertUpdate(DocumentEvent e) {
                    loginPassword = input2.getText();
                    
                }
            };
            
            input3.getDocument().addDocumentListener(doc3);
            input3.setEnabled(true);
            this.add(input3);
            
        }
        
    
        else if (type.equals("signup")) {
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            JLabel label4 = new JLabel("Enter Username:");
            this.add(label4);
            JTextField input4 = new JTextField(8);
            DocumentListener doc4 = new DocumentListener() {
                
                public void changedUpdate(DocumentEvent e) {
                    createUsername = input4.getText();
                    
                }
                
                public void removeUpdate(DocumentEvent e) {
                    createUsername = input4.getText();
                }
                
                public void insertUpdate(DocumentEvent e) {
                    createUsername = input4.getText();
                    
                }
                
            };
            input4.getDocument().addDocumentListener(doc4);
            input4.setEnabled(true);
            this.add(input4);
            
            
        
        
            JLabel label5 = new JLabel("Enter Password:");
            this.add(label5);
            JTextField input5 = new JTextField(8);
            DocumentListener doc5 = new DocumentListener() {
            
            public void changedUpdate(DocumentEvent e) {
                createPassword = input5.getText();
                
            }
            
            public void removeUpdate(DocumentEvent e) {
                createPassword = input5.getText();
            }
            
            public void insertUpdate(DocumentEvent e) {
                createPassword = input5.getText();
                
            }
            
            };
        input5.getDocument().addDocumentListener(doc5);
        input5.setEnabled(true);
        this.add(input5);
        }
    
    
    }

    
    public String getTitle() {
        return titleText;
        
        
        
    }
    
    public String getLoginUsername() {
        return loginUsername;
        
        
    }
    
    public String getLoginPassword() {
        return loginPassword;
        
    }
    
    public String getCreateUsername() {
        return createUsername;
        
        
    }
    
    public String getCreatePassword() {
        return createPassword;
        
    }
    

    }
    
 




