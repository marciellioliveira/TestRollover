package testrollover;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Marcielli
 */
public class TestRollover extends JPanel{

    private static final int PREF_W = 500;
    private static final int PREF_H =PREF_W;
    JButton button = new JButton(new ImageIcon(getClass().getResource("/imagens/btn.png")));
    
    
    public TestRollover() {
        
        button.setMargin(new Insets(0, 0, 0, 0));
        button.setBorder(null);
        button.setBackground(Color.white);
        add(button);
        button.getModel().addChangeListener(new ChangeListener() {
         
            private boolean rollover = false;
            
            @Override
            public void stateChanged(ChangeEvent e) {

                ButtonModel model = (ButtonModel) e.getSource();
                if(model.isRollover() !=rollover) {
                    System.out.println("Rollover: "+model.isRollover());
                    rollover = model.isRollover();
                }                
            }
        });
        
        button.addMouseListener(new MouseAdapter() {
            
            @Override
                   public void mouseEntered(MouseEvent e) {
                       
                       try {
                           Image img = ImageIO.read(getClass().getResource("/imagens/btnHover.png"));
                          
                           button.setIcon(new ImageIcon(img));
                           
                       } catch(IOException ex) {
                           
                       }
                       
                       System.out.println("Entered");
                   }
                   
            @Override
                    public void mouseExited(MouseEvent e) {
                        
                        try {
                            Image img = ImageIO.read(getClass().getResource("/imagens/btn.png"));
                            
                            button.setIcon(new ImageIcon(img));
                        } catch (IOException ex) {
                            
                        }
                        
                        System.out.println("Exited");
                    }
        });
        
    }
    
    @Override
    public Dimension getPreferredSize() {
        if(isPreferredSizeSet()) {
            return super.getPreferredSize();
        }
        return new Dimension(PREF_W, PREF_H);
    }
    
    private static void createAndShowGui() {
        TestRollover mainPanel = new TestRollover();
        JFrame frame = new JFrame("Campo Minado");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(mainPanel);
        mainPanel.setBackground(Color.white);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);        
    }
    
    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGui();
            }
        });        
    }
}
    
    

