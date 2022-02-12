class minion
{
  String name;
  int hp;
  int maxhp;
  int fire;
  int water;
  int earth;
  int air;
  int res1 = 0;
  int res2 = 0;
  PImage img;
  int x;
  int y;
  int w;
  int h;
  int p;
  int pos;
  int atkadd;
  int defadd;
  float atkmult;
  float defmult;
  int moveN = 0;
  minion targetM1;
  minion targetM2;
  minion targetM3;
  boolean selected;
  boolean hasMoved;
  boolean canAttack;
  boolean canDefend;
  rgMove rg1; // 1
  rgMove rg2; // 2
  tdMove td1; // 3
  tdMove td2; // 4
  adMove ad1; // 5
  adMove ad2; // 6
  trMove tr1; // 7
  trMove tr2; // 8
  taMove ta1; // 9
  taMove ta2; // 10
  ArrayList<String> actions;
  IntList actionNs;
  //int[] actionNumbers;
  minion(String cardName, int startingHP, PImage cardImage, int xPos, int yPos, int width, int height, int player, int position)
  {
    name = cardName;
    hp = startingHP;
    maxhp = startingHP;
    img = cardImage;
    x = xPos;
    y = yPos;
    w = width;
    h = height;
    fire = 0;
    water = 0;
    earth = 0;
    air = 0;
    atkadd = 0;
    defadd = 0;
    atkmult = 1;
    defmult = 1;
    p = player;
    pos = position;
    targetM1 = noMinion;
    targetM2 = noMinion;
    targetM3 = noMinion;
    selected = false;
    hasMoved = false;
    canAttack = true;
    canDefend = true;
    actions = new ArrayList<String>();
    actionNs = new IntList();
  }
  void display()
  {
    if (selected)
    {
      imageMode(CENTER);
      image(sglowImage, x + w / 2, y + h / 2, w + 175, h + 100);
    }
    if (!hasMoved && turn == p && state == "none")
    {
      imageMode(CENTER);
      image(aglowImage, x + w / 2, y + h / 2, w + 100, h + 75);
    }
    if (state == "targeting")
    {
      imageMode(CENTER);
      image(aglowImage, x + w / 2, y + h / 2, w + 100, h + 75);
    }
    imageMode(CORNER);
    image(img, x, y, w, h);
    if (p == 1)
    {
      textSize(20);
      fill(255, 0, 0);
      text(hp, x, y - 2);
      fill(224, 89, 35);
      text(fire, x + 22, y + 117);
      fill(35, 142, 224);
      text(water, x + 22, y + 138);
      fill(1, 178, 13);
      text(earth, x + 22, y + 159);
      fill(220, 220, 220);
      text(air, x + 22, y + 180);
    } else
    {
      textSize(20);
      fill(255, 0, 0);
      text(hp, x + 20, y + 115);
      fill(224, 89, 35);
      text(fire, x + 22, y - 12);
      fill(35, 142, 224);
      text(water, x + 22, y - 33);
      fill(1, 178, 13);
      text(earth, x + 22, y - 54);
      fill(220, 220, 220);
      text(air, x + 22, y - 75);
    }
  }
  void select(boolean menuOpened, menu movelist)
  {
    if (mousePressed && !hasMoved)
    {
      if (mouseX > x && mouseX < x + w && mouseY > y && mouseY < y + h && selected == false)
      {
        selected = true;
        mousePressed = false;
      }
    }
  }
  boolean seekTarget(int moveNumber)
  {
    moveN = moveNumber;
    if (moveNumber == 3 || moveNumber == 4 || moveNumber == 7 || moveNumber == 8)
    {
      return true;
    } else
    {
      println("no target");
      return false;
    }
  }
  void target(ArrayList<position> targets, ArrayList<minion> targetMs)
  {
    for (int i = 0; i < targets.size(); i ++)
    {
      position currentTarget = targets.get(i);
      if (mousePressed && mouseX > currentTarget.x && mouseX < currentTarget.x + currentTarget.w && mouseY > currentTarget.y && mouseY < currentTarget.y + currentTarget.h && currentTarget.hasCard)
      {
        //println("targeting");
        //println(currentTarget.x, currentTarget.x + currentTarget.w, currentTarget.y, currentTarget.y + currentTarget.h, currentTarget.hasCard);
        targetM1 = targetMs.get(currentTarget.cardNum);
      }
    }
  }
  boolean checkValid(int moveNumber)
  {
    if(rg1.cost1T == "water")
    {
      res1 = water;
    }
    else if(rg1.cost1T == "fire")
    {
      res1 = fire;
    }
    else if(rg1.cost1T == "earth")
    {
      res1 = earth;
    }
    else if(rg1.cost1T == "air")
    {
      res1 = air;
    }
    if(rg1.cost2T == "water")
    {
      res2 = water;
    }
    else if(rg1.cost2T == "fire")
    {
      res2 = fire;
    }
    else if(rg1.cost2T == "earth")
    {
      res2 = earth;
    }
    else if(rg1.cost2T == "air")
    {
      res2 = air;
    }
    
    if (moveNumber == 1 && canDefend)
    {
      return rg1.valid(res1, res2);
    }
    else if (moveNumber == 2 && canDefend)
    {
      return rg2.valid(res1, res2);
    }
    else if (moveNumber == 3 && canAttack)
    {
      return td1.valid(res1, res2);
    }
    else if (moveNumber == 4 && canAttack)
    {
      return td2.valid(res1, res2);
    }
    else if (moveNumber == 5 && canAttack)
    {
      return ad1.valid(res1, res2);
    }
    else if (moveNumber == 6 && canAttack)
    {
      return ad2.valid(res1, res2);
    }
    else if (moveNumber == 7)
    {
      return tr1.valid(res1, res2);
    }
    else if (moveNumber == 8)
    {
      return tr2.valid(res1, res2);
    }
    else if (moveNumber == 9)
    {
      return ta1.valid(res1, res2);
    }
    else if (moveNumber == 10)
    {
      return ta2.valid(res1, res2);
    }
    else
    {
      return false;
    }
  }
  void move(int moveNumber)
  {
    if (moveNumber == 1)
    {
      if(rg1.spEffect == "im")
      {
        this.defmult = 0;
      }
      rg1.cost(this);
      rg1.execute(this, this);
    }
    else if (moveNumber == 2)
    {
      if(rg2.spEffect == "vu")
      {
        this.defmult = 2;
      }
      rg2.cost(this);
      rg2.execute(this, this);
    }
    else if (moveNumber == 3)
    {
      td1.cost(this);
      td1.execute(this, targetM1);
      atkmult = 1;
    }
    else if (moveNumber == 4)
    {
      td2.cost(this);
      td2.execute(this, targetM1);
      atkmult = 1;
    }
    else if (moveNumber == 5)
    {
      if(ad1.spEffect == "na")
      {
        targetM1.canAttack = false;
        targetM2.canAttack = false;
        targetM3.canAttack = false;
      }
      if(ad1.spEffect == "nd")
      {
        targetM1.canDefend = false;
        targetM2.canDefend = false;
        targetM3.canDefend = false;
      }
      ad1.cost(this);
      ad1.execute(this, targetM1, targetM2, targetM3);
      atkmult = 1;
    }
    else if (moveNumber == 6)
    {
      if(ad2.spEffect == "db")
      {
        atkmult = 2;
      }
      ad2.cost(this);
      ad2.execute(this, targetM1, targetM2, targetM3);
      if(ad2.spEffect != "db")
      {
        atkmult = 1;
      }
    }
    else if (moveNumber == 7)
    {
      if(tr1.spEffect == "da")
      {
        targetM1.atkmult = 2;
      }
      tr1.cost(this);
      tr1.execute(this, targetM1);
    }
    else if (moveNumber == 8)
    {
      if(tr2.spEffect == "pp")
      {
        tr2.cost2 = res2;
        tr2.hpChange = res2;
      }
      if(tr2.spEffect == "im")
      {
        targetM1.defmult = 0;
      }
      tr2.cost(this);
      tr2.execute(this, targetM1);
    }
    else if (moveNumber == 9)
    {
      ta1.cost(this);
      ta1.execute(this, targetM1, targetM2, targetM3);
      if(tr1.spEffect == "hd")
      {
        targetM1.defmult = 0.5;
        targetM2.defmult = 0.5;
        targetM3.defmult = 0.5;
      }
      if(ta1.spEffect == "hd")
      {
        targetM1.defmult = 0.5;
      }
      if(ta1.spEffect == "st")
      {
        if(targetM1 != this)
        {
          air += targetM1.air;
          targetM1.air = 0;
          earth += targetM1.earth;
          targetM1.earth = 0;
        }
        if(targetM2 != this)
        {
          air += targetM2.air;
          targetM2.air = 0;
          earth += targetM2.earth;
          targetM2.earth = 0;
        }
        if(targetM3 != this)
        {
          air += targetM3.air;
          targetM3.air = 0;
          earth += targetM3.earth;
          targetM3.earth = 0;
        }
      }
    }
    else if (moveNumber == 10)
    {
      ta2.cost(this);
      ta2.execute(this, targetM1, targetM2, targetM3);
      if(ta2.spEffect == "ar")
      {
        if(targetM1 != this)
        {
          targetM1.water += 10;
          targetM1.fire += 10;
          targetM1.earth += 10;
          targetM1.air += 10;
        }
        if(targetM2 != this)
        {
          targetM2.water += 10;
          targetM2.fire += 10;
          targetM2.earth += 10;
          targetM2.air += 10;
        }
        if(targetM3 != this)
        {
          targetM3.water += 10;
          targetM3.fire += 10;
          targetM3.earth += 10;
          targetM3.air += 10;
        }
      }
    }
    moveN = 0;
    hasMoved = true;
  }
  void generateMoveset()
  {
    // int Cost1, String Cost1T, int Cost2, String Cost2T, int Gain1, String Gain1T, int Gain2, String Gain2T, int healthChange, String Effect
    if (name == "slomm")
    {
      rg1 = new rgMove(0, "water", 0, "earth", 2, "water", 2, "earth", 0, "none");
      actions.add("Big Slurp");
      actionNs.append(1);

      ad1 = new adMove(2, "water", 0, "earth", 1, 0, "none");
      actions.add("Spit");
      actionNs.append(5);

      tr1 = new trMove(0, "water", 2, "earth", 0, "water", 2, "earth", 0, "none");
      actions.add("Nurturing Slap");
      actionNs.append(7);

      rg2 = new rgMove(0, "water", 1, "earth", 0, "water", 0, "earth", 2, "none");
      actions.add("Yum Yum");
      actionNs.append(2);

      td1 = new tdMove(0, "water", 3, "earth", 3, "none");
      actions.add("Slap");
      actionNs.append(3);

      tr2 = new trMove(2, "water", 0, "earth", 2, "water", 0, "earth", 0, "none");
      actions.add("Nurturing Wet");
      actionNs.append(8);
    }
    else if (name == "flamm")
    {
      rg1 = new rgMove(0, "air", 0, "fire", 4, "air", 0, "fire", 1, "none");
      actions.add("Foo");
      actionNs.append(1);

      ad1 = new adMove(0, "air", 4, "fire", 4, 0, "none");
      actions.add("Fire Lightning");
      actionNs.append(5);

      tr1 = new trMove(2, "air", 2, "fire", 2, "air", 2, "fire", -1, "none");
      actions.add("Warm Hug");
      actionNs.append(7);

      rg2 = new rgMove(2, "air", 0, "fire", 0, "air", 2, "fire", 0, "none");
      actions.add("Snote");
      actionNs.append(2);
      
      ad2 = new adMove(2, "air", 0, "fire", 1, 0, "none");
      actions.add("Air Thunder");
      actionNs.append(6);

      tr2 = new trMove(0, "air", 0, "fire", 0, "air", 0, "fire", 0, "none");
      actions.add("Long Look");
      actionNs.append(8);
    }
    else if (name == "bim")
    {
      rg1 = new rgMove(0, "fire", 0, "earth", 4, "fire", 0, "earth", -3, "none");
      actions.add("Spew");
      actionNs.append(1);

      ad1 = new adMove(0, "fire", 4, "earth", 4, 1, "none");
      actions.add("Rumbling Shake");
      actionNs.append(5);

      ta1 = new taMove(4, "fire", 0, "earth", 0, "fire", 0, "earth", 2, "none");
      actions.add("Generous poof");
      actionNs.append(9);

      rg2 = new rgMove(2, "fire", 0, "earth", 0, "fire", 2, "earth", 2, "none");
      actions.add("Chill");
      actionNs.append(2);
      
      ad2 = new adMove(8, "fire", 0, "earth", 5, 0, "none");
      actions.add("Plume");
      actionNs.append(6);

      tr2 = new trMove(0, "fire", 0, "earth", 0, "fire", 0, "earth", 0, "pp"); // Per earth, values change in execute
      actions.add("Rock coat");
      actionNs.append(8);
    }
    else if (name == "bolg")
    {
      rg1 = new rgMove(0, "water", 0, "air", 4, "water", 0, "air", 4, "none");
      actions.add("Spread");
      actionNs.append(1);

      ad1 = new adMove(2, "water", 0, "air", 1, 0, "none");
      actions.add("Curdle");
      actionNs.append(5);

      tr1 = new trMove(0, "water", 2, "air", 0, "water", 2, "air", 3, "none");
      actions.add("Inflate");
      actionNs.append(7);

      rg2 = new rgMove(2, "water", 0, "air", 0, "water", 2, "air", 0, "none");
      actions.add("Slosh");
      actionNs.append(2);
      
      td1 = new tdMove(0, "water", 4, "air", 6, "none");
      actions.add("Pop");
      actionNs.append(3);

      tr2 = new trMove(8, "water", 0, "air", 0, "water", 0, "air", 0, "im");
      actions.add("Coagulate");
      actionNs.append(8);
    }
    else if (name == "ipsa")
    {
      rg1 = new rgMove(0, "air", 0, "earth", 0, "air", 20, "earth", -4, "im");
      actions.add("Squirm");
      actionNs.append(1);

      ad1 = new adMove(30, "air", 0, "earth", 10, 0, "nd");
      actions.add("Heavy Breathing");
      actionNs.append(5);

      tr1 = new trMove(40, "air", 0, "earth", 0, "air", 0, "earth", 0, "da");
      actions.add("Rallying Roar");
      actionNs.append(7);

      rg2 = new rgMove(0, "air", 20, "earth", 20, "air", 0, "earth", 0, "vu");
      actions.add("Balloon");
      actionNs.append(2);
      
      ad2 = new adMove(0, "air", 30, "earth", 0, 0, "db");
      actions.add("Imbibe");
      actionNs.append(6);

      ta1 = new taMove(0, "air", 10, "earth", 0, "air", 0, "earth", 0, "st");
      actions.add("Fungal Leech");
      actionNs.append(9);
    }
    else if (name == "imby")
    {
      rg1 = new rgMove(0, "water", 0, "fire", 10, "water", 0, "fire", 5, "none");
      actions.add("Squelch");
      actionNs.append(1);

      td1 = new tdMove(30, "water", 0, "fire", 3, "ot");
      actions.add("Kebab");
      actionNs.append(3);

      ta1 = new taMove(10, "water", 0, "fire", 0, "water", 0, "fire", 0, "hd");
      actions.add("Rallying Gurgle");
      actionNs.append(9);

      rg2 = new rgMove(5, "water", 0, "fire", 0, "water", 10, "fire", 0, "none");
      actions.add("Steam");
      actionNs.append(2);
      
      ad1 = new adMove(0, "water", 30, "fire", 6, 0, "na");
      actions.add("Boil");
      actionNs.append(5);

      ta2 = new taMove(20, "water", 20, "fire", 0, "water", 0, "fire", 0, "ar");
      actions.add("Lamb Sauce");
      actionNs.append(10);
    }
  }
}
