import java.awt.*;

public class Tile
{
    private int tileWidth;
    private int tileHight;
    private int width;
    private int height;
    private int xCount;
    private int yCount;

    public Tile(int width, int height)
    {
        tileWidth = 16;
        tileHight = 16;
        this.width = width-tileWidth;
        this.height = height-tileHight*3;
        xCount = width/tileWidth;
        yCount = height/tileHight;
        System.out.println(xCount + " - " + yCount);
    }

    public void paint(Graphics g)
    {
        for (int i = 0; i < width; i+=tileWidth)
        {
            for (int j = 0; j < height; j+=tileHight)
            {
                g.drawOval(i, j, tileWidth, tileHight);
            }
        }
    }

    public int getxCount()
    {
        return xCount;
    }

    public int getyCount()
    {
        return yCount;
    }
}
