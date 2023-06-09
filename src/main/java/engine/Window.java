package engine;

import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Window
{
    private int width , height;
    private String title;

    private long glfwWindow;
    private static Window window = null;
    private Window()
    {
        this.width = 1920;
        this.height = 1080;
        this.title = "Mario";
    }

    public static Window get() // getter function for the main class to use
                                // this ensures that window gets created for only one time
    {
        if (Window.window == null)
        {
            Window.window = new Window();
        }

        return Window.window;
    }

    public void run()
    {
        System.out.println("Hello LWJGL" + Version.getVersion() + "!");
        init();
        loop();

        // Free the memory when the loop exits
        glfwFreeCallbacks(glfwWindow);
        glfwDestroyWindow(glfwWindow);

        // terminate GLFW and then free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    public void init()
    {
        // setting up an error callback
        GLFWErrorCallback.createPrint(System.err).set();
        if(!glfwInit())
        {
            throw new IllegalStateException("Unable to initialize GLFW");

        }
        // Configure GLFW
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE , GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE , GLFW_TRUE);
        glfwWindowHint(GLFW_MAXIMIZED , GLFW_TRUE);

        // create the window
        glfwWindow = glfwCreateWindow(this.width, this.height, this.title, NULL, NULL);
        if (glfwWindow == NULL) // this means the window was not initialized
        {
            throw new IllegalStateException("Failed to create the GLFW window");
        }

        //Make the OpenGL the context current
        glfwMakeContextCurrent(glfwWindow);
        // ENable v - sync
        glfwSwapInterval(1);

        // Make the window visible
        glfwShowWindow(glfwWindow);

        GL.createCapabilities(); // impportant for OpenGL C bindings to work
    }
    public void loop()
    {
        while (!glfwWindowShouldClose(glfwWindow)) // glfwWindow should not get closed
        {
            // Poll events
            glfwPollEvents();

            glClearColor(1.0f , 1.0f , 1.0f , 1.0f);
            glClear(GL_COLOR_BUFFER_BIT);

            glfwSwapBuffers(glfwWindow);
        }
    }
}