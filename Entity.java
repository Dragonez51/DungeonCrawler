public class Entity {
    private int Level;
    private int maxHP;
    private int maxSP;
    private int HP;
    private int SP;
    private int Strength;
    private int Agility;
    private int Intelligence;
    private int Defense;

    public Entity(){
        Level = 1;

        maxHP = 100;
        maxSP = 100;
        HP = maxHP;
        SP = maxSP;

        Strength = 1;
        Agility = 1;
        Intelligence = 1;
        Defense = 1;
    }

    public Entity(int maxHP, int maxSP, int HP, int SP, int strength, int agility, int intelligence, int defense) {
        this.maxHP = maxHP;
        this.maxSP = maxSP;
        this.HP = HP;
        this.SP = SP;
        Strength = strength;
        Agility = agility;
        Intelligence = intelligence;
        Defense = defense;
    }

    public Entity(int Level, int maxHP, int maxSP, int HP, int SP, int strength, int agility, int intelligence, int defense) {
        this.Level = Level;
        this.maxHP = maxHP;
        this.maxSP = maxSP;
        this.HP = HP;
        this.SP = SP;
        Strength = strength;
        Agility = agility;
        Intelligence = intelligence;
        Defense = defense;
    }

    public int getLevel() {
        return Level;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public int getMaxSP() {
        return maxSP;
    }

    public int getHP() {
        return HP;
    }

    public int getSP() {
        return SP;
    }

    public int getStrength() {
        return Strength;
    }

    public int getAgility() {
        return Agility;
    }

    public int getIntelligence() {
        return Intelligence;
    }

    public int getDefense() {
        return Defense;
    }

    public void setLevel(int level) {
        Level = level;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public void setMaxSP(int maxSP) {
        this.maxSP = maxSP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public void setSP(int SP) {
        this.SP = SP;
    }

    public void setStrength(int strength) {
        Strength = strength;
    }

    public void setAgility(int agility) {
        Agility = agility;
    }

    public void setIntelligence(int intelligence) {
        Intelligence = intelligence;
    }

    public void setDefense(int defense) {
        Defense = defense;
    }
}