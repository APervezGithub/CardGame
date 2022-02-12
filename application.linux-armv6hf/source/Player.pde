class player
{
  int eyes;
  boolean eyeAdded;
  player()
  {
    eyes = 1;
    eyeAdded = false;
  }
  void gainEyes()
  {
    if(!eyeAdded)
    {
      eyes += 1;
      eyeAdded = true;
    }
  }
  void displayEyes(int x, int y)
  {
    fill(0);
    textSize(14);
    text(eyes, x, y);
  }
}
