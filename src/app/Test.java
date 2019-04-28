package app;

import java.awt.*;


class Test extends FCanvas {
  private int rectW;
  private int rectH;

  public static void main(String[] args) {
    Test game = new Test();
    game.setFPSLOG(true);
    
    FWindow win = new FWindow(900, 900, "My Game", game);
    game.start();
  }

  @Override
  public void update() {

  }

  @Override
  public void init() {
    setBackground(Color.white);
    rectW = 100;
    rectH = 100;
  }

  @Override
  public void draw(Graphics g) {
    g.setColor(Color.red);
    g.fillRect(0, 0, rectW, rectH);
  }
}
