import java.util.Random;

public class MapGenerator {

    private final float roomOffset = 1f;
    private int roomAmount;

    private Room Room0 = new Room(new Vector2(), false, false, false);
    private Room RoomL = new Room(new Vector2(), true, false, false);
    private Room RoomF = new Room(new Vector2(), false, true, false);
    private Room RoomR = new Room(new Vector2(), false, false, true);
    private Room RoomLF = new Room(new Vector2(), true, true, false);
    private Room RoomFR = new Room(new Vector2(), false, true, true);
    private Room RoomLR = new Room(new Vector2(), true, false, true);

    private Room[] rooms = new Room[]{Room0, RoomL, RoomF, RoomR, RoomLF, RoomFR, RoomLR};

    public MapGenerator(Map map, int roomAmount){
        this.roomAmount = roomAmount;
        generateMap(map);
    }

    private void generateMap(Map map)
    {

        Random rand = new Random();

        int roomCount = 2;
        int forward = 0;
        int right = 0;
        int nextRotation = 0;

        boolean doorL = false;
        boolean doorF = false;
        boolean doorR = false;

        MapRoom firstRoom = new MapRoom(rooms[rand.nextInt(2, 7)], new Vector2(), 2);
        MapRoom generatedRoom = new MapRoom(rooms[rand.nextInt(1, 7)], new Vector2(firstRoom.getPosition().x, firstRoom.getPosition().y + roomOffset), 0);

        map.addStartingRooms(firstRoom, generatedRoom);

        Room currentRoom;

        while (roomCount < roomAmount)
        {
            currentRoom = generatedRoom.getRoom();
            int currentRotation = generatedRoom.getRotation();

            if (currentRoom == Room0)
            {
                break;
            }
            else if (currentRoom == RoomL)
            {
                if (currentRotation == 0) { right = -1; }
                else if (currentRotation == -1) { forward = -1; }
                else if (currentRotation == -2 || currentRotation == 2) { right = 1; }
                else if (currentRotation == 1) { forward = 1; }

                if (currentRotation <= -2) { nextRotation = 1; }
                else { nextRotation = currentRotation - 1; }

                doorL = true;
            }
            else if (currentRoom == RoomF)
            {
                if (currentRotation == 0) { forward = 1; }
                else if (currentRotation == -1) { right = -1; }
                else if (currentRotation == -2 || currentRotation == 2) { forward = -1; }
                else if (currentRotation == 1) { right = 1; }

                nextRotation = currentRotation;

                doorF = true;
            }
            else if (currentRoom == RoomR)
            {
                if (currentRotation == 0) { right = 1; }
                else if (currentRotation == -1) { forward = 1; }
                else if (currentRotation == -2 || currentRotation == 2) { right = -1; }
                else if (currentRotation == 1) { forward = -1; }

                if (currentRotation >= 2) { nextRotation = -1; }
                else { nextRotation = currentRotation + 1; }

                doorR = true;
            }
            else if (currentRoom == RoomLF)
            {
                if (generatedRoom.isDoorL())
                {
                    if (currentRotation == 0) { right = -1; }
                    else if (currentRotation == -1) { forward = -1; }
                    else if (currentRotation == -2 || currentRotation == 2) { right = 1; }
                    else if (currentRotation == 1) { forward = 1; }

                    if (currentRotation <= -2) { nextRotation = 1; }
                    else { nextRotation = currentRotation - 1; }

                    doorL = true;
                }
                else if (generatedRoom.isDoorF())
                {
                    if (currentRotation == 0) { forward = 1; }
                    else if (currentRotation == -1) { right = -1; }
                    else if (currentRotation == -2 || currentRotation == 2) { forward = -1; }
                    else if (currentRotation == 1) { right = 1; }

                    nextRotation = currentRotation;

                    doorF = true;
                }
            }
            else if (currentRoom == RoomFR)
            {
                if (generatedRoom.isDoorF())
                {
                    if (currentRotation == 0) { forward = 1; }
                    else if (currentRotation == -1) { right = -1; }
                    else if (currentRotation == -2 || currentRotation == 2) { forward = -1; }
                    else if (currentRotation == 1) { right = 1; }

                    nextRotation = currentRotation;

                    doorF = true;
                }
                else if(generatedRoom.isDoorR())
                {
                    if (currentRotation == 0) { right = 1; }
                    else if (currentRotation == -1) { forward = 1; }
                    else if (currentRotation == -2 || currentRotation == 2) { right = -1; }
                    else if (currentRotation == 1) { forward = -1; }

                    if (currentRotation >= 2) { nextRotation = -1; }
                    else { nextRotation = currentRotation + 1; }

                    doorR = true;
                }
            }
            else if (currentRoom == RoomLR)
            {
                if (generatedRoom.isDoorL())
                {
                    if (currentRotation == 0) { right = -1; }
                    else if (currentRotation == -1) { forward = -1; }
                    else if (currentRotation == -2 || currentRotation == 2) { right = 1; }
                    else if (currentRotation == 1) { forward = 1; }

                    if (currentRotation <= -2) { nextRotation = 1; }
                    else { nextRotation = currentRotation - 1; }

                    doorL = true;
                }
                else if(generatedRoom.isDoorR())
                {
                    if (currentRotation == 0) { right = 1; }
                    else if (currentRotation == -1) { forward = 1; }
                    else if (currentRotation == -2 || currentRotation == 2) { right = -1; }
                    else if (currentRotation == 1) { forward = -1; }

                    if (currentRotation >= 2) { nextRotation = -1; }
                    else { nextRotation = currentRotation + 1; }

                    doorR = true;
                }
            }
            else
            {
                if (generatedRoom.isDoorL())
                {
                    if (currentRotation == 0) { right = -1; }
                    else if (currentRotation == -1) { forward = -1; }
                    else if (currentRotation == -2 || currentRotation == 2) { right = 1; }
                    else if (currentRotation == 1) { forward = 1; }

                    if (currentRotation <= -2) { nextRotation = 1; }
                    else { nextRotation = currentRotation - 1; }

                    doorL = true;
                }
                else if (generatedRoom.isDoorF())
                {
                    if (currentRotation == 0) { forward = 1; }
                    else if (currentRotation == -1) { right = -1; }
                    else if (currentRotation == -2 || currentRotation == 2) { forward = -1; }
                    else if (currentRotation == 1) { right = 1; }

                    nextRotation = currentRotation;

                    doorF = true;
                }
                else if(generatedRoom.isDoorR())
                {
                    if (currentRotation == 0) { right = 1; }
                    else if (currentRotation == -1) { forward = 1; }
                    else if (currentRotation == -2 || currentRotation == 2) { right = -1; }
                    else if (currentRotation == 1) { forward = -1; }

                    if (currentRotation >= 2) { nextRotation = -1; }
                    else { nextRotation = currentRotation + 1; }

                    doorL = true;
                }
            }

            Vector2 lastPosition = generatedRoom.getPosition();

            Vector2 newPosition = new Vector2(lastPosition.x + (roomOffset * right), lastPosition.y + (roomOffset * forward));

            forward = 0;
            right = 0;

            boolean comparison = map.comparePositionsRec(newPosition, 0.1f);
            if (comparison)
            {
//                Debug.Log("There's already a room here at: "+newPosition+"!");
                if (generatedRoom.isDoorL())
                {
                    generatedRoom.setDoorL(false);

                    doorL = false;
                    doorF = false;
                    doorR = false;

                    continue;
                }
                else if (generatedRoom.isDoorF())
                {
                    generatedRoom.setDoorF(false);

                    doorL = false;
                    doorF = false;
                    doorR = false;

                    continue;
                }
                else if (generatedRoom.isDoorR())
                {
                    generatedRoom.setDoorR(false);

                    doorL = false;
                    doorF = false;
                    doorR = false;

                    continue;
                }
                else
                {
                    generatedRoom = generatedRoom.getParent();

                    if (generatedRoom == firstRoom) { return; }

                    doorL = false;
                    doorF = false;
                    doorR = false;

                    continue;
                }

            }

            MapRoom lastRoom = generatedRoom;

            generatedRoom = new MapRoom(rooms[rand.nextInt(1, 7)], newPosition, nextRotation);

            if (doorL)
            {
                map.addAfter(lastRoom, generatedRoom, 'L');
            }
            else if (doorF)
            {
                map.addAfter(lastRoom, generatedRoom, 'F');
            }
            else if (doorR)
            {
                map.addAfter(lastRoom, generatedRoom, 'R');
            }
            else{
                new RuntimeException("No info where to spawn room!");
            }

            doorL = false;
            doorF = false;
            doorR = false;

            nextRotation = 0;

            roomCount++;

        }
    }

}