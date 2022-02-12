class button
{
  int x;
  int y;
  int w;
  int h;
  int action;
  String name;
  boolean selectable;
  button(int xPos, int yPos, int width, int height, int Action)
  {
    x = xPos;
    y = yPos;
    w = width;
    h = height;
    action = Action;
    selectable = false;
    name = "";
  }
  void display(int xTrans, boolean bordered)
  {
    fill(0);
    textSize(13);
    text(name, x + xTrans, y + 20);
    if(selectable)
    {
      noFill();
      stroke(0, 255, 255);
      rect(x + xTrans, y, w, h);
    }
  }
  boolean beClicked(int xTrans)
  {
    if (mousePressed && mouseX > x + xTrans && mouseX < x + xTrans + w && mouseY > y - 20 && mouseY < y + h)
    {
      return true;
    } else
    {
      return false;
    }
  }
}
