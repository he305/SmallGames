import java.awt.*;

public class SnakePart extends Entity
{
    public SnakePart(int x, int y)
    {
        super(x, y);
        id = EntityID.SnakePart;
        color = Color.green;
        direction = SnakeDirection.Right;
    }

    public SnakePart(SnakePart snakePart)
    {
        super();
        this.direction = snakePart.direction;
        switch (snakePart.getDirection())
        {
            case Left:
                this.x = snakePart.getX()+width;
                this.y = snakePart.getY();
                break;
            case Right:
                this.x = snakePart.getX()-width;
                this.y = snakePart.getY();
                break;
            case Top:
                this.x = snakePart.x;
                this.y = snakePart.y+height;
                break;
            case Down:
                this.x = snakePart.x;
                this.y = snakePart.y-height;
        }
        id = EntityID.SnakePart;
        color = Color.green;
    }

    public void move()
    {
        switch (direction)
        {
            case Left:
                x -= width;
                break;
            case Right:
                x += width;
                break;
            case Top:
                y -= height;
                break;
            case Down:
                y += height;
                break;
        }
    }
}
