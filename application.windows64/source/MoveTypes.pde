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
  void execute(minion self, minion target)
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
  void execute(minion self, minion target)
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
  void execute(minion self, minion target)
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
  void execute(minion self, minion target1, minion target2, minion target3)
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
  void execute(minion self, minion target1, minion target2, minion target3)
  {
    self.hp -= sh;
    target1.hp -= ceil((((dmg + self.atkadd) * self.atkmult) - target1.defadd) * target1.defmult);
    target2.hp -= ceil((((dmg + self.atkadd) * self.atkmult) - target2.defadd) * target2.defmult);
    target3.hp -= ceil((((dmg + self.atkadd) * self.atkmult) - target3.defadd) * target3.defmult);
  }
}
