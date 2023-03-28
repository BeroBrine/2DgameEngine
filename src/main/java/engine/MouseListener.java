package engine;
import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;
public class MouseListener {
    private static MouseListener instance;
    private double scrollX, scrollY;
    private double xPos, yPos , lastY , lastX;
    private boolean mouseButtonPressed[] = new boolean[3];
    private boolean isDragging;

    private MouseListener()
    {
        this.scrollX = 0.0;
        this.scrollY = 0.0;
        this.xPos = 0.0;
        this.yPos = 0.0;
        this.lastX = 0.0;
        this.lastY = 0.0;
    }
    public static MouseListener get()
    {
        if(MouseListener.instance == null)
        {
            MouseListener.instance = new MouseListener();
        }
        return MouseListener.instance;
    }
    public static void mousePosCallback(long window , double xpos , double ypos)
    {
        get().lastX = get().xPos; // gets the positon of the mouse and then sets it to lastx
        get().lastY = get().yPos;
        get().xPos = xpos;
        get().yPos = ypos;
    }

     // adding mouse button callback

    public static void mouseButtonCallback(long window , int button , int action , int mods)
    {
        if(action == GLFW_PRESS) //checks if the mouse button is pressed
        {
            get().mouseButtonPressed[button] = true; // sets the variable to true in arr
        } else if (action == GLFW_RELEASE) // checks if the mouse button is released
        {
            get().mouseButtonPressed[button] = false;
            get().isDragging = false;
        }
    }
}
