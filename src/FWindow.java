import javax.swing.*;
import java.awt.*;

public class FWindow extends JFrame {
  /**
  *  Initializes an FWindow object with the specified dimensions and title
  *  @param w Width of the created FWindow
  *  @param h Height of the created FWindow
  *  @param title Title shown in the Window
  *  @param comp The component which will be displayed,usually FCanvas
  *  <p>
  *  @see FCanvas
  */
  FWindow(int w, int h, String title, Component comp) {
    this.setSize(new Dimension(w, h));
    this.setTitle(title);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.add(comp);
    this.setVisible(true);
  }
}
