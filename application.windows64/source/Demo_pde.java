import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import processing.sound.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Demo_pde extends PApplet {


String page;
int t = 1;
int turn;
PImage slommImage;
PImage flammImage;
PImage bimImage;
PImage bolgImage;
PImage ipsaImage;
PImage imbyImage;
PImage leftImage;
PImage right1Image;
PImage right2Image;

int leftX = -700;
int right1X = 700;
int right2X = 700;
int leftC = 0;
int right1C = 0;
int right2C = 0;

PImage sglowImage;
PImage aglowImage;
PImage boardImage;
PImage menuImage;
PImage cancelB;
PImage endTB;
String state = "none";
button cancel;
button endT;

card slomm1;
card slomm2;

card flamm1;
card flamm2;

card bim1;
card bim2;

card bolg1;
card bolg2;

card ipsa1;
card ipsa2;

card imby1;
card imby2;

board gameBoard;
card selectedCard;
String selection = "self";
card noCard;
minion selectedMinion;
minion noMinion;
menu moveMenu;
player selectedPlayer;
player player1;
player player2;
player noPlayer;
ArrayList<button> buttons = new ArrayList<button>();
ArrayList<card> hand1a = new ArrayList<card>();
ArrayList<card> hand1b = new ArrayList<card>();
ArrayList<card> hand2a = new ArrayList<card>();
ArrayList<card> hand2b = new ArrayList<card>();
ArrayList<position> positions = new ArrayList<position>();
ArrayList<minion> minions = new ArrayList<minion>();
//SoundFile test;


public void setup()
{
  
  page = "game";
  turn = 1;
  slommImage = loadImage("slomm.png");
  flammImage = loadImage("flamm.png");
  bimImage = loadImage("bim.png");
  bolgImage = loadImage("bolg.png");
  ipsaImage = loadImage("ipsa.png");
  imbyImage = loadImage("imby.png");
  sglowImage = loadImage("selectGlow.png");
  aglowImage = loadImage("availableGlow.png");
  boardImage = loadImage("board.jpg");
  menuImage = loadImage("menuImage.png");
  cancelB = loadImage("cancel.png");
  endTB = loadImage("endT.png");
  leftImage = loadImage("left.png");
  right1Image = loadImage("right1.png");
  right2Image = loadImage("right2.png");
  slomm1 = new card("slomm", 10, slommImage, 110, 645, 60, 90, 180, 270, 1, 1);
  slomm2 = new card("slomm", 10, slommImage, 110, 65, 60, 90, 180, 270, 2, 1);
  flamm1 = new card("flamm", 8, flammImage, 183, 645, 60, 90, 180, 270, 1, 1);
  flamm2 = new card("flamm", 8, flammImage, 183, 65, 60, 90, 180, 270, 2, 1);
  bim1 = new card("bim", 16, bimImage, 256, 645, 60, 90, 180, 270, 1, 4);
  bim2 = new card("bim", 16, bimImage, 256, 65, 60, 90, 180, 270, 2, 4);
  bolg1 = new card("bolg", 20, bolgImage, 329, 645, 60, 90, 180, 270, 1, 4);
  bolg2 = new card("bolg", 20, bolgImage, 329, 65, 60, 90, 180, 270, 2, 4);
  ipsa1 = new card("ipsa", 30, ipsaImage, 402, 645, 60, 90, 180, 270, 1, 14);
  ipsa2 = new card("ipsa", 30, ipsaImage, 402, 65, 60, 90, 180, 270, 2, 14);
  imby1 = new card("imby", 45, imbyImage, 475, 645, 60, 90, 180, 270, 1, 14);
  imby2 = new card("imby", 45, imbyImage, 475, 65, 60, 90, 180, 270, 2, 14);
  player1 = new player();
  player2 = new player();
  noCard = new card("null", 0, boardImage, 0, 0, 0, 0, 0, 0, 0, 0);
  noMinion = new minion("null", 0, boardImage, 0, 0, 0, 0, 0, 0);
  noPlayer = new player();
  selectedCard = noCard;
  selectedPlayer = player1;
  for (int i = 1; i <= 6; i ++)
  {
    if (i <= 3)
    {
      positions.add(new position(150 + (i-1) * 110, 390, i, 1));
    } else
    {
      positions.add(new position(150 + (i-4) * 110, 220, i, 2));
    }
  }
  for (int i = 1; i <= 6; i ++)
  {
    if (i <= 3)
    {
      buttons.add(new button(75, 10 + (i - 1) * 40, 155, 30, i));
    } else
    {
      buttons.add(new button(245, 10 + (i - 4) * 40, 155, 30, i));
    }
  }
  gameBoard = new board();
  moveMenu = new menu(200, 0, 500, 120, buttons);
  hand1a.add(slomm1);
  hand2a.add(slomm2);
  hand1a.add(flamm1);
  hand2a.add(flamm2);
  hand1a.add(bim1);
  hand2a.add(bim2);
  hand1a.add(bolg1);
  hand2a.add(bolg2);
  hand1a.add(ipsa1);
  hand2a.add(ipsa2);
  hand1a.add(imby1);
  hand2a.add(imby2);
  
  hand1b.add(slomm1);
  hand2b.add(slomm2);
  hand1b.add(flamm1);
  hand2b.add(flamm2);
  hand1b.add(bim1);
  hand2b.add(bim2);
  hand1b.add(bolg1);
  hand2b.add(bolg2);
  hand1b.add(ipsa1);
  hand2b.add(ipsa2);
  hand1b.add(imby1);
  hand2b.add(imby2);
  //test = new SoundFile (this, "testSound.mp3");
}
public void draw() {
  if (page == "menu")
  {
    background(255, 255, 255);
    fill(0);
    text("Click to start", 200, 200);
    if (mousePressed)
    {
      page = "game";
    }
  }
  if (page == "game")
  {
    background(255, 255, 255);
    ///println(state, turn);
    gameBoard.display(boardImage);
    
    image(cancelB, 0, 0, 75, 30);
    image(endTB, 525, 670, 75, 30);
    //println(mouseX, mouseY);
    stroke(0);
    player1.displayEyes(500, 565);
    player2.displayEyes(80, 145);
    for (int i = 0; i < positions.size(); i ++)
    {
      position currentPosition = positions.get(i);
      currentPosition.display();
      //println(i, currentPosition.hasCard);
      if (state == "card selected")
      {
        if(currentPosition.hasCard == false && currentPosition.p == turn && selectedCard.c <= selectedPlayer.eyes)
        {
          currentPosition.isGlowing = true;
        }
        if (currentPosition.select() && selectedCard != noCard && selectedCard.placeable && currentPosition.hasCard == false && currentPosition.p == turn && selectedCard.c <= selectedPlayer.eyes)
        {
          if (currentPosition.p == selectedCard.p)
          {
            currentPosition.addCard(selectedCard, selectedCard.p);
            selectedCard.placeable = false;
            minions.get(minions.size() - 1).generateMoveset();
            minions.get(minions.size() - 1).selected = false;
            selectedMinion = noMinion;
            for (int j = 0; j < hand1a.size(); j ++)
            {
              card currentCard = hand1a.get(j);
              currentCard.selected = false;
            }
            for (int j = 0; j < hand2a.size(); j ++)
            {
              card currentCard = hand2a.get(j);
              currentCard.selected = false;
            }
            selectedPlayer.eyes -= selectedCard.c;
            selectedCard = noCard;
            currentPosition.hasCard = true;
            for (int k = 0; k < positions.size(); k ++)
            {
              position changePosition = positions.get(k);
              changePosition.isGlowing = false;
            }
            mousePressed = false;
            state = "none";
          }
        }
      }
    }
    int minionSelected = 0;
    for (int i = 0; i < minions.size(); i ++)
    {
      minion currentMinion = minions.get(i);
      currentMinion.display();
      if (state == "none")
      {
        currentMinion.select(true, moveMenu);
      }
      if (currentMinion.selected == true)
      {
        if (!(state == "targeting"))
        {
          state = "minion selected";
        }
        minionSelected += 1;
        selectedMinion = currentMinion;
        for (int j = 0; j < buttons.size(); j ++)
        {
          button currentButton = buttons.get(j);
          currentButton.name = currentMinion.actions.get(j);
          currentButton.action = currentMinion.actionNs.get(j);
          if(currentMinion.checkValid(currentButton.action))
          {
            currentButton.selectable = true;
          } else {
            currentButton.selectable = false;
          }
          if (currentButton.beClicked(moveMenu.x) && !currentMinion.hasMoved && currentMinion.checkValid(currentButton.action))
          {
            if (!currentMinion.seekTarget(currentButton.action))
            {
              state = "none";
              if (currentButton.action == 5 || currentButton.action == 6)
              {
                for (int k = 0; k < minions.size(); k ++)
                {
                  minion currentTMinion = minions.get(k); 
                  if (currentTMinion.p != currentMinion.p)
                  {
                    if (currentMinion.targetM1 == noMinion)
                    {
                      currentMinion.targetM1 = currentTMinion;
                    } else if (currentMinion.targetM2 == noMinion)
                    {
                      currentMinion.targetM2 = currentTMinion;
                    } else if (currentMinion.targetM3 == noMinion)
                    {
                      currentMinion.targetM3 = currentTMinion;
                    }
                  }
                }
              }
              println("Found Target");
              if (currentButton.action == 9 || currentButton.action == 10)
              {
                println("Found Action");
                for (int k = 0; k < minions.size(); k ++)
                {
                  minion currentTMinion = minions.get(k);
                  println(currentTMinion.p, currentMinion.p);
                  if (currentTMinion.p == currentMinion.p)
                  {
                    if (currentMinion.targetM1 == noMinion)
                    {
                      currentMinion.targetM1 = currentTMinion;
                    } else if (currentMinion.targetM2 == noMinion)
                    {
                      currentMinion.targetM2 = currentTMinion;
                    } else if (currentMinion.targetM3 == noMinion)
                    {
                      currentMinion.targetM3 = currentTMinion;
                    }
                  }
                }
              }
              currentMinion.move(currentButton.action);
              currentMinion.targetM1 = noMinion;
              currentMinion.targetM2 = noMinion;
              currentMinion.targetM3 = noMinion;
              currentMinion.selected = false;
              selectedMinion = noMinion;
            } else
            {
              state = "targeting";
            }
          }
        }
        if (state == "targeting")
        {
          currentMinion.target(positions, minions);
          if (currentMinion.targetM1 != noMinion)
          {
            currentMinion.move(currentMinion.moveN);
            currentMinion.targetM1 = noMinion;
            currentMinion.selected = false;
            selectedMinion = noMinion;
            state = "none";
            mousePressed = false;
          }
        }
      }
      if (currentMinion.hp <= 0)
      {
        //println(currentMinion.pos);
        positions.get(currentMinion.pos).hasCard = false;
        minions.remove(i);
      }
    }
    if (state == "minion selected")
    {
      moveMenu.open();
    }
    if (state == "none")
    {
      moveMenu.unopen();
    }
    if (minionSelected == 0)
    {
      selectedMinion = noMinion;
    }
    int cardSelected = 0;
    for (int i = 0; i < hand2a.size(); i ++)
    {
      card currentCard = hand2a.get(i);
      card replacementCard = hand2b.get(i);
      currentCard.display();
      if(state != "minion selected" && state != "targeting")
      {
        currentCard.zoom();
      }
      if (state == "none" && turn == 2)
      {
        currentCard.select();
      }
      if (currentCard.selected == true)
      {
        state = "card selected";
      }
      currentCard.action();
      if (currentCard.selected == true)
      {
        cardSelected += 1;
        selectedCard = currentCard;
      }
      if (currentCard.hover() && i < hand2a.size() - 1)
      {
        hand2a.remove(i);
        hand2a.add(replacementCard);
        hand2b.remove(i);
        hand2b.add(hand2a.get(hand2a.size() - 1));
      }
    }
    for (int i = 0; i < hand1a.size(); i ++)
    {
      card currentCard = hand1a.get(i);
      card replacementCard = hand1b.get(i);
      currentCard.display();
      if(state != "minion selected" && state != "targeting")
      {
        currentCard.zoom();
      }
      if (state == "none" && turn == 1)
      {
        currentCard.select();
      }
      if (currentCard.selected == true)
      {
        state = "card selected";
      }
      currentCard.action();
      if (currentCard.selected == true)
      {
        cardSelected += 1;
        selectedCard = currentCard;
      }
      if (currentCard.hover() && i < hand1a.size() - 1)
      {
        hand1a.remove(i);
        hand1a.add(replacementCard);
        hand1b.remove(i);
        hand1b.add(hand1a.get(hand1a.size() - 1));
      }
    }
    if (cardSelected == 0)
    {
      selectedCard = noCard;
    }
    if (mousePressed && mouseX < 75 && mouseY < 30)
    {
      state = "none";
      selectedCard = noCard;
      selectedMinion = noMinion;
      for (int i = 0; i < hand1a.size(); i ++)
      {
        card currentCard = hand1a.get(i);
        currentCard.selected = false;
      }
      for (int i = 0; i < hand2a.size(); i ++)
      {
        card currentCard = hand2a.get(i);
        currentCard.selected = false;
      }
      for (int i = 0; i < minions.size(); i ++)
      {
        minion currentMinion = minions.get(i);
        currentMinion.selected = false;
      }
      mousePressed = false;
    }
    if (mousePressed && mouseX > 525 && mouseY > 670)
    {
      state = "none";
      selectedCard = noCard;
      selectedMinion = noMinion;
      for (int i = 0; i < hand1a.size(); i ++)
      {
        card currentCard = hand1a.get(i);
        currentCard.selected = false;
      }
      for (int i = 0; i < hand2a.size(); i ++)
      {
        card currentCard = hand2a.get(i);
        currentCard.selected = false;
      }
      t += 1;
      turn = 2 - (t % 2);
      for (int i = 0; i < minions.size(); i ++)
      {
        minion currentMinion = minions.get(i);
        currentMinion.selected = false;
        if (currentMinion.p == turn)
        {
          currentMinion.hasMoved = false;
          currentMinion.defmult = 1;
          currentMinion.defadd = 0;
          currentMinion.atkadd = 0;
        } else
        {
          currentMinion.canAttack = true;
          currentMinion.canDefend = true;
        }
      }
      if (turn == 1)
      {
        selectedPlayer = player1;
        player1.gainEyes();
        leftC = 5;
        right1C = -5;
      } else if (turn == 2)
      {
        selectedPlayer = player2;
        player2.gainEyes();
        leftC = 5;
        right2C = -5;
      }  
      selectedPlayer.eyeAdded = false;
      mousePressed = false;
    }
    moveMenu.display(menuImage);
  }
}

class board
{
  board()
  {
    
  }
  public void display(PImage img)
  {
    imageMode(CORNER);
    image(img, 0, 0);
  }
}    
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
  public void display(int xTrans, boolean bordered)
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
  public boolean beClicked(int xTrans)
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
  public void display()
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
  public boolean hover()
  {
    if (mouseX > sx - mnw / 2 && mouseX < sx + mnw / 2 && mouseY > sy - mnh / 2 && mouseY < sy + mnh / 2)
    {
      return true;
    } else
    {
      return false;
    }
  }
  public void select()
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
  public void action()
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
  public void zoom()
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
  public void display(PImage img)
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
  public void open()
  {
    if(x > minX)
    {
      x -= 5;
    }
  }
  public void unopen()
  {
    if(x < maxX)
    {
      x += 5;
    }
  }
}
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
  public void display()
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
  public void select(boolean menuOpened, menu movelist)
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
  public boolean seekTarget(int moveNumber)
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
  public void target(ArrayList<position> targets, ArrayList<minion> targetMs)
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
  public boolean checkValid(int moveNumber)
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
  public void move(int moveNumber)
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
        targetM1.defmult = 0.5f;
        targetM2.defmult = 0.5f;
        targetM3.defmult = 0.5f;
      }
      if(ta1.spEffect == "hd")
      {
        targetM1.defmult = 0.5f;
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
  public void generateMoveset()
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

class move
{
  int cost1;
  String cost1T;
  int cost2;
  String cost2T;
  move(int Cost1, String Cost1T, int Cost2, String Cost2T)
  {
    cost1 = Cost1;
    cost1T = Cost1T;
    cost2 = Cost2;
    cost2T = Cost2T;
  }
  public boolean valid(int r1, int r2)
  {
    if(cost1T == "fire" && r1 < cost1)
    {
      return false;
    }
    else if(cost1T == "water" && r1 < cost1)
    {
      return false;
    }
    else if(cost1T == "earth" && r1 < cost1)
    {
      return false;
    }
    else if(cost1T == "air" && r1 < cost1)
    {
      return false;
    }
    else if(cost2T == "fire" && r2 < cost2)
    {
      return false;
    }
    else if(cost2T == "water" && r2 < cost2)
    {
      return false;
    }
    else if(cost2T == "earth" && r2 < cost2)
    {
      return false;
    }
    else if(cost2T == "air" && r2 < cost2)
    {
      return false;
    }
    else 
    {
      return true;
    }
  }
  public void cost(minion self)
  {
    if (cost1T == "fire")
    {
      self.fire -= cost1;
    } else if (cost1T == "water")
    {
      self.water -= cost1;
    } else if (cost1T == "earth")
    {
      self.earth -= cost1;
    } else if (cost1T == "air")
    {
      self.air -= cost1;
    }
    if(cost2T == "fire")
    {
      self.fire -= cost2;
    } else if (cost2T == "water")
    {
      self.water -= cost2;
    } else if (cost2T == "earth")
    {
      self.earth -= cost2;
    } else if (cost2T == "air")
    {
      self.air -= cost2;
    }
  }
}
class rgMove extends move
{
  int gain1;
  String gain1T;
  int gain2;
  String gain2T;
  int hpChange;
  String spEffect;
  rgMove(int Cost1, String Cost1T, int Cost2, String Cost2T, int Gain1, String Gain1T, int Gain2, String Gain2T, int healthChange, String Effect)
  {
    super(Cost1, Cost1T, Cost2, Cost2T);
    gain1 = Gain1;
    gain1T = Gain1T;
    gain2 = Gain2;
    gain2T = Gain2T;
    hpChange = healthChange;
    spEffect = Effect;
  }
  public void execute(minion self, minion target)
  {
    // HP Changes
    if (hpChange > 0 && self.maxhp > self.hp)
    {
      self.hp += hpChange;
      if (self.hp > self.maxhp)
      {
        self.hp = self.maxhp;
      }
    }
    if (hpChange < 0)
    {
      self.hp += hpChange;
    }
    // Gain types
    if (gain1T == "fire")
    {
      self.fire += gain1;
    } else if (gain1T == "water")
    {
      self.water += gain1;
    } else if (gain1T == "earth")
    {
      self.earth += gain1;
    } else if (gain1T == "air")
    {
      self.air += gain1;
    }
    if (gain2T == "fire")
    {
      self.fire += gain2;
    } else if (gain2T == "water")
    {
      self.water += gain2;
    } else if (gain2T == "earth")
    {
      self.earth += gain2;
    } else if (gain2T == "air")
    {
      self.air += gain2;
    }
  }
}

class tdMove extends move
{
  int dmg;
  String spEffect;
  tdMove(int Cost1, String Cost1T, int Cost2, String Cost2T, int damage, String Effect)
  {
    super(Cost1, Cost1T, Cost2, Cost2T);
    dmg = damage;
  }
  public void execute(minion self, minion target)
  {
    target.hp -= ceil((((dmg + self.atkadd) * self.atkmult) - target.defadd) * target.defmult);
  }
}

class trMove extends move
{
  int gain1;
  String gain1T;
  int gain2;
  String gain2T;
  int hpChange;
  String spEffect;
  trMove(int Cost1, String Cost1T, int Cost2, String Cost2T, int Gain1, String Gain1T, int Gain2, String Gain2T, int healthChange, String Effect)
  {
    super(Cost1, Cost1T, Cost2, Cost2T);
    gain1 = Gain1;
    gain1T = Gain1T;
    gain2 = Gain2;
    gain2T = Gain2T;
    hpChange = healthChange;
    spEffect = Effect;
  }
  public void execute(minion self, minion target)
  {
    // HP Changes
    if (hpChange > 0 && target.maxhp > target.hp)
    {
      target.hp += hpChange;
      if (target.hp > target.maxhp)
      {
        target.hp = target.maxhp;
      }
    }
    if (hpChange < 0)
    {
      target.hp += hpChange;
    }
    // Gain types
    if (gain1T == "fire")
    {
      target.fire += gain1;
    } else if (gain1T == "water")
    {
      target.water += gain1;
    } else if (gain1T == "earth")
    {
      target.earth += gain1;
    } else if (gain1T == "air")
    {
      target.air += gain1;
    }
    if (gain2T == "fire")
    {
      target.fire += gain2;
    } else if (gain2T == "water")
    {
      target.water += gain2;
    } else if (gain2T == "earth")
    {
      target.earth += gain2;
    } else if (gain2T == "air")
    {
      target.air += gain2;
    }
  }
}

class taMove extends move
{
  int gain1;
  String gain1T;
  int gain2;
  String gain2T;
  int hpChange;
  String spEffect;
  taMove(int Cost1, String Cost1T, int Cost2, String Cost2T, int Gain1, String Gain1T, int Gain2, String Gain2T, int healthChange, String Effect)
  {
    super(Cost1, Cost1T, Cost2, Cost2T);
    gain1 = Gain1;
    gain1T = Gain1T;
    gain2 = Gain2;
    gain2T = Gain2T;
    hpChange = healthChange;
    spEffect = Effect;
  }
  public void execute(minion self, minion target1, minion target2, minion target3)
  {
    println(target1.hp, target2.hp, target3.hp);
    if (hpChange > 0 && target1.maxhp > target1.hp)
    {
      target1.hp += hpChange;
      if (target1.hp > target1.maxhp)
      {
        target1.hp = target1.maxhp;
      }
    }
    if (hpChange > 0 && target2.maxhp > target2.hp)
    {
      target2.hp += hpChange;
      if (target2.hp > target2.maxhp)
      {
        target2.hp = target2.maxhp;
      }
    }
    if (hpChange > 0 && target3.maxhp > target3.hp)
    {
      target3.hp += hpChange;
      if (target3.hp > target3.maxhp)
      {
        target3.hp = target2.maxhp;
      }
    }
    if (gain1T == "fire")
    {
      target1.fire += gain1;
      target2.fire += gain1;
      target3.fire += gain1;
    } else if (gain1T == "water")
    {
      target1.water += gain1;
      target2.fire += gain1;
      target3.fire += gain1;
    } else if (gain1T == "earth")
    {
      target1.earth += gain1;
      target2.fire += gain1;
      target3.fire += gain1;
    } else if (gain1T == "air")
    {
      target1.air += gain1;
      target2.fire += gain1;
      target3.fire += gain1;
    }
    if (gain2T == "fire")
    {
      target1.fire += gain2;
      target2.fire += gain2;
      target3.fire += gain2;
    } else if (gain2T == "water")
    {
      target1.water += gain2;
      target2.fire += gain2;
      target3.fire += gain2;
    } else if (gain2T == "earth")
    {
      target1.earth += gain2;
      target2.fire += gain2;
      target3.fire += gain2;
    } else if (gain2T == "air")
    {
      target1.air += gain2;
      target2.fire += gain2;
      target3.fire += gain2;
    }
  }
}

class adMove extends move
{
  int dmg;
  int sh;
  String spEffect;
  adMove(int Cost1, String Cost1T, int Cost2, String Cost2T, int damage, int selfHarm, String Effect)
  {
    super(Cost1, Cost1T, Cost2, Cost2T);
    dmg = damage;
    sh = selfHarm;
    spEffect = Effect;
  }
  public void execute(minion self, minion target1, minion target2, minion target3)
  {
    self.hp -= sh;
    target1.hp -= ceil((((dmg + self.atkadd) * self.atkmult) - target1.defadd) * target1.defmult);
    target2.hp -= ceil((((dmg + self.atkadd) * self.atkmult) - target2.defadd) * target2.defmult);
    target3.hp -= ceil((((dmg + self.atkadd) * self.atkmult) - target3.defadd) * target3.defmult);
  }
}

class player
{
  int eyes;
  boolean eyeAdded;
  player()
  {
    eyes = 1;
    eyeAdded = false;
  }
  public void gainEyes()
  {
    if(!eyeAdded)
    {
      eyes += 1;
      eyeAdded = true;
    }
  }
  public void displayEyes(int x, int y)
  {
    fill(0);
    textSize(14);
    text(eyes, x, y);
  }
}
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
  public boolean select()
  {
    if(mousePressed && mouseX > x && mouseX < x + w && mouseY > y && mouseY < y + h)
    {
      return true;
    }
    else {
      return false;
    }
  }
  public void display()
  {
    if(isGlowing)
    {
      imageMode(CENTER);
      image(aglowImage, x + w / 2, y + h /2, abs(w) + 100, h + 75);
    }
  }
  public void addCard(card c, int playerTurn)
  {
    cardNum = minions.size();
    minions.add(new minion(c.name, c.hp, c.img, x + 11, y + 3, 60, 90, playerTurn, location - 1));
    hasCard = true;
  }
}
  public void settings() {  size(600, 700); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Demo_pde" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
