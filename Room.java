public class Room {
    private Vector2 position;
    private boolean doorL;
    private boolean doorF;
    private boolean doorR;

    public Room(Vector2 position, boolean doorL, boolean doorF, boolean doorR){
        this.position = position;
        this.doorL = doorL;
        this.doorF = doorF;
        this.doorR = doorR;
    }

    public Vector2 getPosition(){
        return position;
    }

    public boolean isDoorL() {
        return doorL;
    }

    public boolean isDoorF() {
        return doorF;
    }

    public boolean isDoorR() {
        return doorR;
    }

    public void setDoorL(boolean doorL) {
        this.doorL = doorL;
    }

    public void setDoorF(boolean doorF) {
        this.doorF = doorF;
    }

    public void setDoorR(boolean doorR) {
        this.doorR = doorR;
    }

}