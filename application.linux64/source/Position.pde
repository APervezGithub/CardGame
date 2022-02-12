class position
{
  int x;
  int y;
  int w;
  int h;
  int location;
  int cardNum;
  boolean hasCard;
  boolean isGlowing;
  int p;
  position(int xPos, int yPos, int locationVar, int player)
  {
    x = xPos;
    y = yPos;
    w = 80;
    h = 110;
    location = locationVar;
    hasCard = false;
    isGlowing = false;
    cardNum = 1000;
    p = player;
  }
  boolean select()
  {
    if(mousePressed && mouseX > x && mouseX < x + w && mouseY > y && mouseY < y + h)
    {
      return true;
    }
    else {
      return false;
    }
  }
  void display()
  {
    if(isGlowing)
    {
      imageMode(CENTER);
      image(aglowImage, x + w / 2, y + h /2, abs(w) + 100, h + 75);
    }
  }
  void addCard(card c, int playerTurn)
  {
    cardNum = minions.size();
    minions.add(new minion(c.name, c.hp, c.img, x + 11, y + 3, 60, 90, playerTurn, location - 1));
    hasCard = true;
  }
}
