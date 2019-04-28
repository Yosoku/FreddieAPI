package app;

import java.awt.*;
import java.awt.image.BufferStrategy;

public abstract class FCanvas extends Canvas implements Runnable {
  public int width;
  public  int height;
  private boolean running = false;
  private Thread thread;

  private boolean FPSLOG = true;
  private Color background = Color.black;


  public synchronized void start() {
    if (running)
      return;
    running = true;
    thread = new Thread(this);
    thread.start();
  }

  private synchronized void stop() {
    if (!running)
      return;
    running = false;
    try {
      thread.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    }

  /**
  * Main program loop,it calls init once to initialize variables and runs the game loop
  * @see <a href="https://gameprogrammingpatterns.com/game-loop.html">Game Loops</a>
  */
  public void run() {

    init();
    this.requestFocus();
    long lastTime = System.nanoTime();
    double amountOfTicks = 60.0;
    double ns = 1000000000 / amountOfTicks;
    double delta = 0;
    long timer = System.currentTimeMillis();
    int updates = 0;
    int frames = 0;
    while (running) {
      long now = System.nanoTime();
      delta = delta + (now - lastTime) / ns;
      lastTime = now;
      while (delta >= 1) {
        update();
        updates++;
        delta--;
      }
      render();
      frames++;

      if (System.currentTimeMillis() - timer > 1000) {
        timer = timer + 1000;
        if (this.FPSLOG)
          System.out.println("FPS: " + frames + " " + "Ticks: " + updates);
        frames = 0;
        updates = 0;
      }

    }
    stop();
  }
  /**
  * Render method is called as much as possible in the main loop and it is used to draw on the Canvas
  * First time its called it creates a tripple Buffered strategy to get a Graphics context and uses
  * that graphics object to draw and clear the screen.
  */
  private void render() {
    BufferStrategy bs = this.getBufferStrategy();
    if (bs == null) {
      this.createBufferStrategy(3);
      return;
    }
    Graphics g = bs.getDrawGraphics();
    g.setColor(background);
    g.fillRect(0, 0, getWidth(), getHeight());
    draw(g);
    g.dispose();
    bs.show();
  }

  /**
  * Setter method for Logging Fps on the Console
  * @param status New status for the FPS logger
  */
  public void setFPSLOG(boolean status) {
    this.FPSLOG = status;
  }

  /**
   * Changes the background color of your canvas.Default is black
   * @param c The new background color for the FCanvas object
   */
  public void setBackground(Color c) {
    this.background = c;
  }

  /**
  * init method is called once after FCanvas.start() is called
  * Program variables and object initializations should be declared in init
  */
  public abstract void init();

  /**
  * draw method is called every frame and it is used to draw on the canvas
  * using a java.awt.Graphics Object
  */
  public abstract void draw(Graphics g);

  /**
  * Update is called 60 times per second and it used to update the properties of
  * objects of your Program
  */
  public abstract void update();
}
