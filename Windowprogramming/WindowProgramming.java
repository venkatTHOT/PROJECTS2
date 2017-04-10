
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;



class WindowProgramming extends JFrame implements MouseMotionListener,ActionListener  {
    JMenuBar jmenubar;
    JMenu menu;
    JMenuItem submenu;
    JLabel jl;
    JPanel jp,jp1;
    JTextArea ta = new JTextArea(33,110);
    JScrollPane scroll;
    static JTabbedPane jTabbedPane1 = new JTabbedPane();
    boolean b=true;
    
public WindowProgramming(String s) {
        super(s);
        jmenubar =new JMenuBar();
        
        this.setSize(500, 300);
        
        jp=new JPanel();
        jp1=new JPanel();
        
        jp=new JPanel();
        jp.setBorder(new TitledBorder(new EtchedBorder(), "Display Area"));
       
 // create the middle panel components

        
        ta.setEditable(true); // set textArea non-editable
        scroll = new JScrollPane(ta);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        //Add Textarea in to middle panel
        jp.add(scroll);
        
        ta.setBackground(new Color(204, 238, 241)); // light blue
        ta.setFont(new Font("Century Gothic", Font.ITALIC, 20));
       ta.addMouseMotionListener(this);
       
       jTabbedPane1.add("project1",jp);
               
       //layout
        setLayout(new BorderLayout());
        add(jTabbedPane1);
        
        
       
      
        //for font
        Font displayfont =new Font("serif",Font.BOLD,18);
        
        //for file
        menu=new JMenu("File");
        menu.setMnemonic(KeyEvent.VK_F);
        menu.setFont(displayfont);
        jmenubar.add(menu);
        submenu=new JMenuItem("New");
        submenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK));
        submenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
           //ta = new JTextArea();
           //ta.addMouseMotionListener(WindowProgramming.this);
           //jp1.add(ta);
           //jTabbedPane1.add("untitled",jp1);
            }
        });
        menu.add(submenu);
        submenu=new JMenuItem("Open");
        submenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.CTRL_MASK));
        submenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                JFileChooser open = new JFileChooser(); // open up a file chooser (a dialog for the user to browse files to open)
			int option = open.showOpenDialog(open); // get the option that the user selected (approve or cancel)
			// NOTE: because we are OPENing a file, we call showOpenDialog~
			// if the user clicked OK, we have "APPROVE_OPTION"
			// so we want to open the file
			if (option == JFileChooser.APPROVE_OPTION) {
				ta.setText("");
				try {
					// create a scanner to read the file (getSelectedFile().getPath() will get the path to the file)
					Scanner scan = new Scanner(new FileReader(open.getSelectedFile().getPath()));
					while (scan.hasNext()) // while there's still something to read
						ta.append(scan.nextLine() + "\n"); // append the line to the TextArea
                                        
				} catch (Exception ex) { // catch any exceptions, and...
					// ...write to the debug console
					System.out.println(ex.getMessage());
				}
			}
		}
           
            
        });
        menu.add(submenu);
        submenu=new JMenuItem("Save");
        submenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
        submenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
           
                
                JFileChooser save = new JFileChooser(); // again, open a file chooser
			int option = save.showSaveDialog(save); // similar to the open file, only this time we call
			// showSaveDialog instead of showOpenDialog
			// if the user clicked OK (and not cancel)
			if (option == JFileChooser.APPROVE_OPTION) {
				try {
					// create a buffered writer to write to a file
					BufferedWriter out = new BufferedWriter(new FileWriter(save.getSelectedFile().getPath()));
					out.write(ta.getText()); // write the contents of the TextArea to the file
					out.close(); // close the file stream
				} catch (Exception ex) { // again, catch any exceptions and...
					// ...write to the debug console
					System.out.println(ex.getMessage());
				}
			}
		}
                
            
        });
        menu.add(submenu);
        submenu=new JMenuItem("Save As...");
        submenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,ActionEvent.CTRL_MASK));
        submenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
           
            }
        });
        menu.add(submenu);
        menu.addSeparator();
        submenu=new JMenuItem("Page Setup..");
        submenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U,ActionEvent.CTRL_MASK));
        submenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
           
            }
        });
        menu.add(submenu);
        submenu=new JMenuItem("Print");
        submenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,ActionEvent.CTRL_MASK));
        menu.add(submenu);
        menu.addSeparator();
        submenu=new JMenuItem("Exit");
        submenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,ActionEvent.CTRL_MASK));
        submenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
           dispose();
            }
        });
        menu.add(submenu);
        
        
        //for edit 
        
        menu=new JMenu("Edit");
        menu.setMnemonic(KeyEvent.VK_E);
        menu.setFont(displayfont);
         jmenubar.add(menu);
        submenu=new JMenuItem("Undo"); 
        submenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,ActionEvent.CTRL_MASK));
        submenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
           
            }
        });
        menu.add(submenu);
        submenu=new JMenuItem("Cut");
        submenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,ActionEvent.CTRL_MASK));
        submenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
           ta.cut();
            }
        });
        menu.add(submenu);
        submenu=new JMenuItem("Copy");
        submenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
           ta.copy();
           
            }
        });
        submenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,ActionEvent.CTRL_MASK));
        menu.add(submenu);
        submenu=new JMenuItem("Paste");
        submenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,ActionEvent.CTRL_MASK));
        submenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
           ta.paste();
            }
        });
        menu.add(submenu);
        menu.addSeparator();
        submenu=new JMenuItem("Delete");
        submenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,ActionEvent.CTRL_MASK));
        submenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
           
            }
        });
        menu.add(submenu);
        submenu=new JMenuItem("Find..");
        submenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,ActionEvent.CTRL_MASK));
        submenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
           
            }
        });
        menu.add(submenu);
        menu.addSeparator();
        submenu=new JMenuItem("Find Next");
        submenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I,ActionEvent.CTRL_MASK));
        submenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
           
            }
        });
        menu.add(submenu);
        submenu=new JMenuItem("Replace..");
        submenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,ActionEvent.CTRL_MASK));
        submenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
           
            }
        });
        menu.add(submenu);
        submenu=new JMenuItem("Go To...");
        submenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G,ActionEvent.CTRL_MASK));
        submenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
           
            }
        });
        menu.add(submenu);
        
        
        //for format
          
       
        menu=new JMenu("Format");
        menu.setFont(displayfont);
        menu.setMnemonic(KeyEvent.VK_E);
         jmenubar.add(menu);
        submenu=new JMenuItem("WordWrap"); 
        submenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,ActionEvent.CTRL_MASK));
        submenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
           
            }
        });
        menu.add(submenu);
        submenu=new JMenuItem("Color..");
        submenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,ActionEvent.CTRL_MASK));
        submenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
           Color bgcolor=JColorChooser.showDialog(ta,"Choose Background Color",getBackground());
if(bgcolor!=null)
ta.setBackground(bgcolor);
            }
        });
        menu.add(submenu);
        
        //for view
         menu=new JMenu("View");
         menu.setFont(displayfont);
        menu.setMnemonic(KeyEvent.VK_V);
         jmenubar.add(menu);
        submenu=new JMenuItem("Status Bar"); 
        submenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,ActionEvent.CTRL_MASK));
        submenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             
                if(b==true)
                {
           jp.setVisible(false);
                b=false;
                }
                else
                    jp.setVisible(true);
            }
        });
        menu.add(submenu);
        
        //fr help
        
         menu=new JMenu("Help");
         menu.setFont(displayfont);
        menu.setMnemonic(KeyEvent.VK_H);
         jmenubar.add(menu);
        submenu=new JMenuItem("View Help"); 
        submenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_AT,ActionEvent.CTRL_MASK));
        submenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
           
            }
        });
        menu.add(submenu);
        menu.addSeparator();
         submenu=new JMenuItem("About Notepad"); 
        submenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y,ActionEvent.CTRL_MASK));
        submenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
           
            }
        });
        menu.add(submenu);
        
        //for status bar
        jp=new JPanel();
        jl=new JLabel("Status Bar  :");
        jp.add(jl);
        jl =new JLabel();
        jp.add(jl);
        this.addMouseMotionListener(this);
        add(jp,BorderLayout.SOUTH);
       
         this.setJMenuBar(jmenubar);
        this.setVisible(true);
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
}

    @Override
    public void mouseDragged(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        
        
         jl.setText("X:"+e.getX()+"  Y:"+e.getY()+" ");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
     public static void main(String[] args) {
       new WindowProgramming("Application");
    }


}
        