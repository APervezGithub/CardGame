class card
{
  int p;
  int c;
  String name;
  int hp;
  PImage img;
  int sx;
  int sy;
  int x;
  int y;
  int w;
  int h;
  int mnw;
  int mnh;
  int mxw;
  int mxh;
  boolean selected;
  boolean placeable;
  card(String cardName, int startingHP, PImage cardImage, int xPos, int yPos, int width, int height, int maxWidth, int maxHeight, int player, int cost)
  {
    name = cardName;
    hp = startingHP;
    img = cardImage;
    sx = xPos;
    sy = yPos;
    x = xPos;
    y = yPos;
    w = width;
    h = height;
    mnw = width;
    mnh = height;
    mxw = maxWidth;
    mxh = maxHeight;
    selected = false;
    placeable = true;
    c = cost;
    p = player;
  }
  void display()
  {
    if (selected == true && placeable)
    {
      imageMode(CENTER);
      image(sglowImage, x, y, abs(w) + 175, h + 100);
    }
    if(p == 1 && p == turn && player1.eyes >= c && placeable)
    {
      imageMode(CENTER);
      image(aglowImage, x, y, abs(w) + 100, h + 75);
    }
    if(p == 2 && p == turn && player2.eyes >= c && placeable)
    {
      imageMode(CENTER);
      image(aglowImage, x, y, abs(w) + 100, h + 75);
    }
    imageMode(CENTER);
    image(img, x, y, w, h);
    if(!placeable)
    {
      noStroke();
      fill(0, 0, 0, 150);
      rect(x - w / 2, y - h / 2, w, h);
    }
  }
  boolean hover()
  {
    if (mouseX > sx - mnw / 2 && mouseX < sx + mnw / 2 && mouseY > sy - mnh / 2 && mouseY < sy + mnh / 2)
    {
      return true;
    } else
    {
      return false;
    }
  }
  void select()
  {
    if (mousePressed && placeable)
    {
      if (mouseX > sx - mnw / 2 && mouseX < sx + mnw / 2 && mouseY > sy - mnh / 2 && mouseY < sy + mnh / 2)
      {
        selected = true;
      }
      else
      {
        selected = false; 
      }
    }
  }
  void action()
  {
    int translation = 0;
    if(p == 1)
    {
      translation = 1;
    } else
    {
      translation = -1;
    }
    if (selected)
    {
      if (h > mnh)
      {
        w += (mnw + mxw) / ((mxh - mnh) / 3);
        h -= 3;
        y += 4 * translation;
      }
    }
  }
  void zoom()
  {
    int translation = 0;
    if(p == 1)
    {
      translation = 1;
    } else
    {
      translation = -1;
    }
    if (mouseX > sx - mnw / 2 && mouseX < sx + mnw / 2 && mouseY > sy - mnh / 2 && mouseY < sy + mnh / 2)
    {
      if (!selected && h < mxh)
      {
        w -= (mnw + mxw) / ((mxh - mnh) / 3);
        h += 3;
        y -= 4 * translation;
      }
    } else
    {
      if (!selected && h > mnh)
      {
        w += (mnw + mxw) / ((mxh - mnh) / 3);
        h -= 3;
        y += 4 * translation;
      }
    }
  }
}
