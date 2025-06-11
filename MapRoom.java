public class MapRoom {

    private Room room;
    private Vector2 position;
    private int rotation;

    private boolean doorL, doorF, doorR;

    private MapRoom parent;

    private MapRoom left;
    private MapRoom front;
    private MapRoom right;

    public MapRoom(Room room, Vector2 position, int rotation)
    {
        this.room = room;
        this.position = position;
        this.rotation = rotation;

        doorL = room.isDoorL();
        doorF = room.isDoorF();
        doorR = room.isDoorR();
    }

    public String ToString()
    {
        return room + ", L: " + doorL+ ", F: " + doorF+ ", R: " + doorR;
    }

    public Room getRoom() {
        return room;
    }

    public Vector2 getPosition(){
        return position;
    }

    public int getRotation() {
        return rotation;
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

    public MapRoom getParent() {
        return parent;
    }

    public MapRoom getLeft() {
        return left;
    }

    public MapRoom getFront() {
        return front;
    }

    public MapRoom getRight() {
        return right;
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

    public void setParent(MapRoom parent) {
        this.parent = parent;
    }

    public void setLeft(MapRoom left) {
        this.left = left;
    }

    public void setFront(MapRoom front) {
        this.front = front;
    }

    public void setRight(MapRoom right) {
        this.right = right;
    }
}