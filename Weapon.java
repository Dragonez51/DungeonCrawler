public class Weapon {

    private String Name;
    private int DMG;
    private int Durability;
    private int StaminaConsumption;

    public Weapon(String name, int DMG, int durability, int staminaConsumption) {
        Name = name;
        this.DMG = DMG;
        Durability = durability;
        StaminaConsumption = staminaConsumption;
    }
}