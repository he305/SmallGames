import java.awt.*;

public class Entity
{
    protected SnakeDirection direction;
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected EntityID id;
    protected Color color;

    public Entity(int x, int y)
    {
        this.x = x;
        this.y = y;
        width = 16;
        height = 16;
    }
    public Entity()
    {
        width = 16;
        height = 16;
    }

    public void paint(Graphics g)
    {
        g.setColor(color);
        g.fillOval(x, y, width, height);
    }

    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public EntityID getId()
    {
        return id;
    }

    public int getWidth()
    {
        return width;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public int getHeight()
    {
        return height;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    public SnakeDirection getDirection()
    {
        return direction;
    }

    public void setDirection(SnakeDirection direction)
    {
        this.direction = direction;
    }
}
