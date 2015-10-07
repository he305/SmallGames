import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

public class Game extends JPanel implements ActionListener
{
    private Timer timer;
    private int DELAY = 100;
    private int counter;
    private final int WIDTH;
    private final int HEIGHT;
    private boolean inGame;
    private JFrame jframe;

    private LinkedList<SnakePart> snake;
    private Apple apple;
    private Tile map;

    public Game()
    {
        WIDTH = 800;
        HEIGHT = 600;
        jframe = new JFrame("Snake");
        jframe.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        jframe.setResizable(false);
        jframe.add(this);
        jframe.pack();
        jframe.addKeyListener(new KeyList());
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setLocationRelativeTo(null);
        jframe.setVisible(true);
    }

    public void init()
    {
        map = new Tile(WIDTH, HEIGHT);
        counter = 0;
        apple = new Apple();
        locateApple();

        snake = new LinkedList<>();
        snake.add(new SnakeHead(5 * 16, 5 * 16));
        for (int i = 4; i >= 0; i--)
        {
            snake.add(new SnakePart(i*16, 5*16));
        }
        inGame = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        map.paint(g);
        for (int i = snake.size()-1; i >= 0; i--)
        {
            snake.get(i).paint(g);
        }

        apple.paint(g);
    }

    private void update()
    {
        for (SnakePart snakePart : snake)
        {
            snakePart.move();
        }

        for (int i = snake.size()-1; i > 0; i--)
        {
            snake.get(i).setDirection(snake.get(i-1).getDirection());
        }

        if (snake.get(0).getX() == apple.getX() && snake.get(0).getY() == apple.getY())
        {
            snake.add(new SnakePart(snake.get(snake.size()-1)));
            counter++;
            jframe.setTitle("Snake! Score: " + counter);
            timer.setDelay(DELAY-counter*10);
            locateApple();
        }
        checkCollision();
    }

    private void checkCollision()
    {
        SnakePart head = snake.get(0);
        if (head.getX() < 0 || head.getX() > WIDTH-head.getWidth()*2  || head.getY() < 0 || head.getY() > HEIGHT-head.getHeight()*3)
            inGame = false;

        for (int i = 1; i < snake.size(); i++)
        {
            if (head.getX() == snake.get(i).getX() && head.getY() == snake.get(i).getY())
                inGame = false;
        }

    }

    private void locateApple()
    {
        apple.setX((int) (Math.random() * map.getxCount()) * apple.getWidth());
        apple.setY((int) (Math.random() * map.getyCount()) * apple.getHeight());
        System.out.println(apple.getX() + " " + apple.getY());
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (inGame)
        {
            update();
        }

        else
        {
            if(JOptionPane.showConfirmDialog(null, "You lose!\nFinal score is " + counter) == JOptionPane.YES_OPTION)
            {
                timer.stop();
                init();
            }
            else System.exit(0);
        }

        repaint();
    }

    private class KeyList implements KeyListener
    {

        @Override
        public void keyTyped(KeyEvent e)
        {

        }

        @Override
        public void keyPressed(KeyEvent e)
        {
            if (e.getKeyCode() == KeyEvent.VK_UP)
            {
                snake.get(0).setDirection(SnakeDirection.Top);
            }
            else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            {
                snake.get(0).setDirection(SnakeDirection.Right);
            }
            else if (e.getKeyCode() == KeyEvent.VK_LEFT)
            {
                snake.get(0).setDirection(SnakeDirection.Left);
            }
            else if (e.getKeyCode() == KeyEvent.VK_DOWN)
            {
                snake.get(0).setDirection(SnakeDirection.Down);
            }
        }

        @Override
        public void keyReleased(KeyEvent e)
        {

        }
    }
}
