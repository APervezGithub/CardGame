class menu
{
  int maxX;
  int x;
  int minX;
  int y;
  int w;
  int h;
  ArrayList<button> buttons;
  menu(int xPos, int yPos, int width, int height, ArrayList<button> list)
  {
    maxX = 600;
    x = 600;
    minX = xPos;
    y = yPos;
    w = width;
    h = height;
    buttons = list;
  }
  void display(PImage img)
  {
    imageMode(CORNER);
    image(img, x, y, w, h);
    stroke(155, 0, 0);
    strokeWeight(1);
    line(x + 235, y + 20, x + 235, h - 20);
    for(int i = 0; i < buttons.size(); i ++)
    {
      button currentButton = buttons.get(i);
      currentButton.display(x, false);
    }
  }
  void open()
  {
    if(x > minX)
    {
      x -= 5;
    }
  }
  void unopen()
  {
    if(x < maxX)
    {
      x += 5;
    }
  }
}
