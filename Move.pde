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
  boolean valid(int r1, int r2)
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
  void cost(minion self)
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
