import processing.sound.*;
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


void setup()
{
  size(600, 700);
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
void draw() {
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
