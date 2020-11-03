package com.tutorial;

// ===========================================================================
// PLAYER CLASS :
class Player {
    // StrenghtPlayer have a good basehealth and defence
    // IntelPlayer have a good base attack
    // ======================================================
    private String name;
    private String playerType;
    private int baseHealth;
    private int baseLevel;
    private int baseAttack;
    private int baseDefence;
    // ======================================================

    // object relation
    Armor armor;
    Weapon weapon;

    Player(String name, String playerType) {
        this.name = name;

        if (playerType.equals("strength")) {
            this.playerType = "Strength Player";
            this.baseHealth = 300;
            this.baseLevel = 1;
            this.baseAttack = 5;
            this.baseDefence = 15;
        } else if (playerType.equals("intel")) {
            this.playerType = "Intelligence Player";
            this.baseHealth = 200;
            this.baseLevel = 1;
            this.baseAttack = 20;
            this.baseDefence = 5;
        }

        this.display();
    }

    public void display() {
        System.out.println("\n==========Player Status==========\n");
        System.out.println("Name    : " + name);
        System.out.println("Type    : " + playerType);
        System.out.println("HP      : " + baseHealth);
        System.out.println("Level   : " + baseLevel);
        System.out.println("Attack  : " + baseAttack);
        System.out.println("Defence : " + baseDefence);
        System.out.println("=================================\n");
    }

    public void equipArmor(Armor armor) {
        this.armor = armor;
        this.baseDefence += this.armor.getDefencePower();

        System.out.println("=================================");
        System.out.println(this.name + " Equipped an armor : " + this.armor.getArmorName());
        System.out.println("This armor increase defence : " + this.armor.getDefencePower());
        System.out.println("=================================\n");
    }

    public void equipWeapon(Weapon weapon) {
        this.weapon = weapon;
        this.baseAttack += this.weapon.getAttackPower();

        System.out.println("=================================");
        System.out.println(this.name + " Equipped a weapon : " + this.weapon.getWeaponName());
        System.out.println("This weapon increase attack : " + this.weapon.getAttackPower());
        System.out.println("=================================\n");
    }

    private void levelUp() {
        int hpStrength = 25;
        int hpIntel = 15;
        int attackStrength = 3;
        int attackIntel = 5;
        int hpIncreament = 0;
        int attackIncrement = 0;

        if (this.playerType.equals("Strength Player")) {
            this.baseLevel += 1;
            this.baseHealth += hpStrength;
            this.baseAttack += attackStrength;
            hpIncreament = hpStrength;
            attackIncrement = attackStrength;
        } else if (this.playerType.equals("Intelligence Player")) {
            this.baseLevel += 1;
            this.baseHealth += hpIntel;
            this.baseAttack += attackIntel;
            hpIncreament = hpIntel;
            attackIncrement = attackIntel;
        }

        System.out.println("=================================");
        System.out.println(this.name + " has level up to " + this.baseLevel);
        System.out.println("HP has been increased " + hpIncreament);
        System.out.println("Attack has been increased " + attackIncrement);
        System.out.println("=================================\n");
    }

    private void attack(Player opponent) {
        int attackPower;

        if ((this.baseAttack - opponent.baseDefence) >= 0) {
            attackPower = this.baseAttack - opponent.baseDefence;
        } else {
            attackPower = 0;
        }
        opponent.baseHealth -= attackPower;

        System.out.println("==========Battle Start===========");
        System.out.println(this.name + " has attack " + opponent.name);
        System.out.println(opponent.name + " gets damage " + attackPower);
        System.out.println(opponent.name + " health is decreased to " + opponent.baseHealth);
        System.out.println("===========Battle End============\n");

        this.levelUp();
    }

    public void attackUntillDie(Player opponent) {
        do {
            this.attack(opponent);
            opponent.attack(this);
        } while (this.baseHealth >= 0 && opponent.baseHealth >= 0);

        this.display();
        opponent.display();

        if (this.baseHealth < opponent.baseHealth) {
            System.out.println("=================================");
            System.out.println(opponent.name + " has won the Battle!");
            System.out.println("=================================\n");
        } else {
            System.out.println("=================================");
            System.out.println(this.name + " has won the Battle!");
            System.out.println("=================================\n");
        }

    }

}

// END PLAYER CLASS
// ===========================================================================
// ARMOR CLASS :
class Armor {
    private String name;
    private int defence;

    Armor(String name, int defence) {
        this.name = name;
        this.defence = defence;
    }

    public int getDefencePower() {
        return defence;
    }

    public String getArmorName() {
        return name;
    }
}

// END ARMOR CLASS
// ===========================================================================
// WEAPON CLASS :
class Weapon {
    private String name;
    private int attack;

    Weapon(String name, int attack) {
        this.name = name;
        this.attack = attack;
    }

    public int getAttackPower() {
        return attack;
    }

    public String getWeaponName() {
        return name;
    }
}
// END WEAPON CLASS
// ===========================================================================

public class Main {
    public static void main(String[] args) {
        // built player
        Player fadlil = new Player("fadlil", "strength");
        Player pipit = new Player("pipit", "intel");

        // built armor
        Armor queenArmor = new Armor("Queen Armor", 10);
        Armor princeArmor = new Armor("Prince Armor", 10);

        // built weapon
        Weapon sword = new Weapon("Sword", 15);
        Weapon hammer = new Weapon("Hammer", 25);

        // equip player fadlil
        fadlil.equipArmor(princeArmor);
        fadlil.equipWeapon(sword);

        // equip player pipit
        pipit.equipArmor(queenArmor);
        pipit.equipWeapon(hammer);

        // 2 player
        fadlil.attackUntillDie(pipit);

    }
}
