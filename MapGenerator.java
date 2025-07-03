import java.util.Random;

public class MapGenerator {

    public static final float roomOffset = 96f;

    private static Room Room0 = new Room(new Vector2(), false, false, false);
    private static Room RoomL = new Room(new Vector2(), true, false, false);
    private static Room RoomF = new Room(new Vector2(), false, true, false);
    private static Room RoomR = new Room(new Vector2(), false, false, true);
    private static Room RoomLF = new Room(new Vector2(), true, true, false);
    private static Room RoomFR = new Room(new Vector2(), false, true, true);
    private static Room RoomLR = new Room(new Vector2(), true, false, true);

    private static Room[] rooms = new Room[]{Room0, RoomL, RoomF, RoomR, RoomLF, RoomFR, RoomLR};

    private static int third = Math.ceilDiv((int)MapGenerator.roomOffset, 3);

    public static void generateMap(Map map, int roomAmount)
    {

        Random rand = new Random();

        int roomCount = 1;
        int forward = 0;
        int right = 0;
        int nextRotation = 0;

        boolean doorL = false;
        boolean doorF = false;
        boolean doorR = false;

        int randomIndex = rand.nextInt(1, 7);
        MapRoom generatedRoom = new MapRoom(rooms[randomIndex], new Vector2(), 0);

        map.add(generatedRoom);

        Room currentRoom;

        while (roomCount < roomAmount)
        {
            try{
                currentRoom = generatedRoom.getRoom();
            }catch(NullPointerException e){
                System.out.println("generatedRoom is null!");
                return;
            }
            int currentRotation = generatedRoom.getRotation();

            if (currentRoom == Room0)
            {
                break;
            }
            else if (currentRoom == RoomL)
            {
                if (currentRotation == 0) { right = -1; }
                else if (currentRotation == -1) { forward = 1; }
                else if (currentRotation == -2 || currentRotation == 2) { right = 1; }
                else if (currentRotation == 1) { forward = -1; }

                if (currentRotation <= -2) { nextRotation = 1; }
                else { nextRotation = currentRotation - 1; }

                doorL = true;
            }
            else if (currentRoom == RoomF)
            {
                if (currentRotation == 0) { forward = -1; }
                else if (currentRotation == -1) { right = -1; }
                else if (currentRotation == -2 || currentRotation == 2) { forward = 1; }
                else if (currentRotation == 1) { right = 1; }

                nextRotation = currentRotation;

                doorF = true;
            }
            else if (currentRoom == RoomR)
            {
                if (currentRotation == 0) { right = 1; }
                else if (currentRotation == -1) { forward = -1; }
                else if (currentRotation == -2 || currentRotation == 2) { right = -1; }
                else if (currentRotation == 1) { forward = 1; }

                if (currentRotation >= 2) { nextRotation = -1; }
                else { nextRotation = currentRotation + 1; }

                doorR = true;
            }
            else if (currentRoom == RoomLF)
            {
                if (generatedRoom.isDoorL())
                {
                    if (currentRotation == 0) { right = -1; }
                    else if (currentRotation == -1) { forward = 1; }
                    else if (currentRotation == -2 || currentRotation == 2) { right = 1; }
                    else if (currentRotation == 1) { forward = -1; }

                    if (currentRotation <= -2) { nextRotation = 1; }
                    else { nextRotation = currentRotation - 1; }

                    doorL = true;
                }
                else if (generatedRoom.isDoorF())
                {
                    if (currentRotation == 0) { forward = -1; }
                    else if (currentRotation == -1) { right = -1; }
                    else if (currentRotation == -2 || currentRotation == 2) { forward = 1; }
                    else if (currentRotation == 1) { right = 1; }

                    nextRotation = currentRotation;

                    doorF = true;
                }
            }
            else if (currentRoom == RoomFR)
            {
                if (generatedRoom.isDoorF())
                {
                    if (currentRotation == 0) { forward = -1; }
                    else if (currentRotation == -1) { right = -1; }
                    else if (currentRotation == -2 || currentRotation == 2) { forward = 1; }
                    else if (currentRotation == 1) { right = 1; }

                    nextRotation = currentRotation;

                    doorF = true;
                }
                else if(generatedRoom.isDoorR())
                {
                    if (currentRotation == 0) { right = 1; }
                    else if (currentRotation == -1) { forward = -1; }
                    else if (currentRotation == -2 || currentRotation == 2) { right = -1; }
                    else if (currentRotation == 1) { forward = 1; }

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
                    else if (currentRotation == -1) { forward = 1; }
                    else if (currentRotation == -2 || currentRotation == 2) { right = 1; }
                    else if (currentRotation == 1) { forward = -1; }

                    if (currentRotation <= -2) { nextRotation = 1; }
                    else { nextRotation = currentRotation - 1; }

                    doorL = true;
                }
                else if(generatedRoom.isDoorR())
                {
                    if (currentRotation == 0) { right = 1; }
                    else if (currentRotation == -1) { forward = -1; }
                    else if (currentRotation == -2 || currentRotation == 2) { right = -1; }
                    else if (currentRotation == 1) { forward = 1; }

                    if (currentRotation >= 2) { nextRotation = -1; }
                    else { nextRotation = currentRotation + 1; }

                    doorR = true;
                }
            }
            else //                RoomLFR
            {
                if (generatedRoom.isDoorL())
                {
                    if (currentRotation == 0) { right = -1; }
                    else if (currentRotation == -1) { forward = 1; }
                    else if (currentRotation == -2 || currentRotation == 2) { right = 1; }
                    else if (currentRotation == 1) { forward = -1; }

                    if (currentRotation <= -2) { nextRotation = 1; }
                    else { nextRotation = currentRotation - 1; }

                    doorL = true;
                }
                else if (generatedRoom.isDoorF())
                {
                    if (currentRotation == 0) { forward = -1; }
                    else if (currentRotation == -1) { right = -1; }
                    else if (currentRotation == -2 || currentRotation == 2) { forward = 1; }
                    else if (currentRotation == 1) { right = 1; }

                    nextRotation = currentRotation;

                    doorF = true;
                }
                else if(generatedRoom.isDoorR())
                {
                    if (currentRotation == 0) { right = 1; }
                    else if (currentRotation == -1) { forward = -1; }
                    else if (currentRotation == -2 || currentRotation == 2) { right = -1; }
                    else if (currentRotation == 1) { forward = 1; }

                    if (currentRotation >= 2) { nextRotation = -1; }
                    else { nextRotation = currentRotation + 1; }

                    doorL = true;
                }
            }

            Vector2 lastPosition = generatedRoom.getPosition();

//            Vector2 newPosition = new Vector2(lastPosition.x + (third * right), lastPosition.y + (third * forward));

            Vector2 newPosition = new Vector2(lastPosition.x + (roomOffset*right), lastPosition.y+(roomOffset*forward));

            forward = 0;
            right = 0;

            boolean comparison = map.comparePositionsRec(newPosition, roomOffset);
            if (comparison)
            {
                doorL = false;
                doorF = false;
                doorR = false;
                if (generatedRoom.isDoorL())
                {
                    generatedRoom.setDoorL(false);
                }
                else if (generatedRoom.isDoorF())
                {
                    generatedRoom.setDoorF(false);
                }
                else if (generatedRoom.isDoorR())
                {
                    generatedRoom.setDoorR(false);
                }
                else
                {
                    generatedRoom = generatedRoom.getParent();

                    if (generatedRoom == map.getRoot()) { return; }
                }
                continue;

            }

            MapRoom lastRoom = generatedRoom;

            randomIndex = rand.nextInt(1, 7);
            generatedRoom = new MapRoom(rooms[randomIndex], newPosition, nextRotation);

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