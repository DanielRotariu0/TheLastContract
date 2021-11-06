package Game.Input;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseManager extends MouseAdapter {
    private boolean leftPressed;
    private boolean rightPressed;
    private int mouseX;
    private int mouseY;

    public MouseManager() { }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton()==MouseEvent.BUTTON1)
        {
            leftPressed = true;
        }
        if(e.getButton()==MouseEvent.BUTTON3)
            rightPressed=true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getButton()==MouseEvent.BUTTON1) {
            leftPressed = false;
        }
        if(e.getButton()==MouseEvent.BUTTON3){
            rightPressed=false;
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX=e.getX();
        mouseY=e.getY();
    }

    public boolean leftClickPressed(){ return leftPressed; }

    public boolean rightClickPressed(){ return rightPressed; }

    public int getMouseX() { return mouseX; }

    public int getMouseY() { return mouseY; }
}