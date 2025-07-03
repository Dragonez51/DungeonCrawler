import javax.swing.*;
import java.awt.*;

public class MapViewer extends JPanel {

    private int third;
    private Map map;
    private float sizeX, sizeY;
    private float maxX, minX, maxY, minY;
    private boolean isFirstRoom;

    public MapViewer(Map map){
        super();
        this.map = map;
        this.isFirstRoom = true;

//        third = Math.ceilDiv((int)MapGenerator.roomOffset, 3);
    }

    @Override
    protected void paintComponent(Graphics g){
        maxX = map.getMaxX();
        minX = map.getMinX();
        maxY = map.getMaxY();
        minY = map.getMinY();
        sizeX = maxX - minX;
        sizeY = maxY - minY;
        Component[] children = this.getComponents();
//        children[0].setLocation(0,0);
        if(isFirstRoom){
            System.out.println("minX: "+minX+"\nmaxX: "+maxX+"\nminY: "+minY+"\nmaxY: "+maxY+"\nsizeX: "+sizeX + "\nsizeY: "+sizeY);
        }
        this.setPreferredSize(new Dimension((int)sizeX, (int)sizeY));
        this.setMaximumSize(this.getPreferredSize());
        this.setBackground(new Color(0,0,0));
        this.setMaximumSize(this.getPreferredSize());
        super.paintComponent(g);
        paintMap(g);
        g.setColor(new Color(255, 0, 0));
//        g.drawString(("Room0: ["+map.getRoot().getPosition().x+", "+map.getRoot().getPosition().y+"] || knight position: ["+this.getComponents()[0].getLocation().x+", "+this.getComponents()[0].getLocation().y+"]"), 20, 20);
//        g.drawLine(this.getComponents()[0].getLocation().x, this.getComponents()[0].getLocation().y, this.getComponents()[0].getLocation().x+64, this.getComponents()[0].getLocation().y-64);
//        this.getComponents()[0].setLocation((int)Expedition.ocPosition.x, (int)Expedition.ocPosition.y);
//        children[0].setBounds((int)Expedition.ocPosition.x, (int)Expedition.ocPosition.y, 64, 64);
//        children[0].setLocation((int)Expedition.ocPosition.x, (int)Expedition.ocPosition.y);
    }

    private void paintMap(Graphics g){
        paintMap(g, map.getRoot());
    }

    private void paintMap(Graphics g, MapRoom node){

        if(node == null){
            return;
        }

        //  center is Vector2[sizeX/2, sizeY/2];
        //  we need to move the fucking center the distance between maxX and maxY

        if(isFirstRoom){

//            System.out.println("Before: (root) "+node.getPosition().x+", "+node.getPosition().y);
//            if(minX+sizeX/2 < 0){
//                System.out.println("Moving all X's by: "+(Math.abs(minX+sizeX/2)+MapGenerator.roomOffset));
//                map.moveAllX(Math.abs(minX+sizeX/2)+MapGenerator.roomOffset);
//            }else if(maxX+(sizeX/2) > sizeX){
//                System.out.println("Moving all X's by: "+(sizeX - (maxX+sizeX/2)+MapGenerator.roomOffset));
//                map.moveAllX(sizeX - (maxX+sizeX/2)+MapGenerator.roomOffset);
//            }
//            if(minY+sizeY/2 < 0){
//                System.out.println("Moving all Y's by: "+(Math.abs(minY+sizeY/2)+MapGenerator.roomOffset));
//                map.moveAllY(Math.abs(minY+sizeY/2)+MapGenerator.roomOffset);
//            } else if(maxY+sizeY/2 > sizeY){
//                System.out.println("Moving all Y's by: "+(sizeY - (maxY+sizeY/2)+MapGenerator.roomOffset));
//                map.moveAllY(sizeY - (maxY+sizeY/2)+MapGenerator.roomOffset);
//            }
            if(minX < 0){
//                System.out.println("Moving all X's by: "+(Math.abs(minX)+MapGenerator.roomOffset));
                map.moveAllX(Math.abs(minX));
            }else if(maxX > sizeX){
//                System.out.println("Moving all X's by: "+(sizeX - (maxX+sizeX/2)+MapGenerator.roomOffset));
//                map.moveAllX(sizeX - (maxX+sizeX/2)+MapGenerator.roomOffset);
                map.moveAllX(sizeX - maxX);
            }
            if(minY < 0){
//                System.out.println("Moving all Y's by: "+(Math.abs(minY)+MapGenerator.roomOffset));
                map.moveAllY(Math.abs(minY));
            } else if(maxY > sizeY){
//                System.out.println("Moving all Y's by: "+(sizeY - (maxY+sizeY/2)+MapGenerator.roomOffset));
                map.moveAllY(sizeY - maxY);
            }

//            this.setPreferredSize();

//            System.out.println("After: (root) "+node.getPosition().x+", "+node.getPosition().y+"\n");

//            Expedition.ocPosition.x = node.getPosition().x;
//            Expedition.ocPosition.y = node.getPosition().y;

//            System.out.println("Before: (knight) "+Expedition.ocPosition.x+", "+Expedition.ocPosition.y);
            Expedition.ocPosition = new Vector2(node.getPosition().x+16, node.getPosition().y+16);
//            System.out.println("After: (knight) "+Expedition.ocPosition.x+", "+Expedition.ocPosition.y);

            map.printAll();

            isFirstRoom = false;
        }
//        int nextX = (int) (node.getPosition().x + sizeX/2);
        int nextX = (int) (node.getPosition().x);
//        int nextY = (int) (node.getPosition().y + sizeY/2);
        int nextY = (int) (node.getPosition().y);

        if(!node.getRoom().isDoorL() && !node.getRoom().isDoorF() && !node.getRoom().isDoorR()){
            paintRoom0(g, nextX, nextY, node.getRotation());
        }else if(node.getRoom().isDoorL() && !node.getRoom().isDoorF() && !node.getRoom().isDoorR()){
            paintRoomL(g, nextX, nextY, node.getRotation());
        }else if(!node.getRoom().isDoorL() && node.getRoom().isDoorF() && !node.getRoom().isDoorR()){
            paintRoomF(g, nextX, nextY, node.getRotation());
        }else if(!node.getRoom().isDoorL() && !node.getRoom().isDoorF() && node.getRoom().isDoorR()){
            paintRoomR(g, nextX, nextY, node.getRotation());
        }else if(node.getRoom().isDoorL() && node.getRoom().isDoorF() && !node.getRoom().isDoorR()){
            paintRoomLF(g, nextX, nextY, node.getRotation());
        }else if(!node.getRoom().isDoorL() && node.getRoom().isDoorF() && node.getRoom().isDoorR()){
            paintRoomFR(g, nextX, nextY, node.getRotation());
        }else if(node.getRoom().isDoorL() && !node.getRoom().isDoorF() && node.getRoom().isDoorR()){
            paintRoomLR(g, nextX, nextY, node.getRotation());
        }else if(node.getRoom().isDoorL() && node.getRoom().isDoorF() && node.getRoom().isDoorR()){
            paintRoomLFR(g, nextX, nextY, node.getRotation());
        }

        g.setColor(new Color(255, 255, 255));
        g.drawString(String.valueOf(node.id), nextX+40, nextY+48);

//        System.out.println("Drawing knight at: ["+(int)Expedition.ocPosition.x+", "+(int)Expedition.ocPosition.y+"]");
        g.drawImage(Expedition.Knight, (int)Expedition.ocPosition.x, (int)Expedition.ocPosition.y, null);

        paintMap(g, node.getLeft());
        paintMap(g, node.getFront());
        paintMap(g, node.getRight());
    }

    private void paintRoomL(Graphics g, int x, int y, int rotation){
        if(rotation==0){
            g.drawImage(Expedition.RoomL_0, x, y, null);
//                g.drawImage(Expedition.Floor_Wood, x-third, y+third, null);
//                g.drawImage(Expedition.Wall_U, x-third, y, null);
//                g.drawImage(Expedition.Wall_R, x+third*2, y+third, null);
//                g.drawImage(Expedition.Wall_U, x, y, null);
//                g.drawImage(Expedition.Wall_U, x, y, null);
//                g.drawImage(Expedition.Wall_U, x, y, null);
//                g.drawImage(Expedition.Wall_U, x+third/2, y-third*2, null);
//                g.drawImage(Expedition.Wall_R, x+third*2, y, null);
//                g.drawImage(Expedition.Wall_Open_L, x-third, y, null);
//                g.drawImage(Expedition.Wall_Open_D, x+third/2, y+third, null);
        }else if(rotation == -1){
            g.drawImage(Expedition.RoomL_N1, x, y, null);
        }else if(rotation == -2 || rotation == 2){
            g.drawImage(Expedition.RoomL_2, x, y, null);
        }else if(rotation == 1){
            g.drawImage(Expedition.RoomL_1, x, y, null);
        }
//        if(rotation == 0){
//            y+=third;
//            g.drawLine(x, y, x-=third, y);
//            g.drawLine(x, y, x, y-=third);
//            y-=third;
//            g.drawLine(x, y, x, y-=third);
//            g.drawLine(x, y, x+=third*3, y);
//            g.drawLine(x, y, x, y+=third*3);
//            g.drawLine(x, y, x-=third, y);
//        }
//        else if(rotation == -1){
//            x+=third;
//            g.drawLine(x, y, x, y+=third);
//            g.drawLine(x, y, x-=third, y);
//            x-=third;
//            g.drawLine(x, y, x-=third, y);
//            g.drawLine(x, y, x, y-=third*3);
//            g.drawLine(x, y, x+=third*3, y);
//            g.drawLine(x, y, x, y+=third);
//        }
//        else if(rotation == -2 || rotation == 2){
//            y-=third;
//            g.drawLine(x, y, x+=third, y);
//            g.drawLine(x, y, x, y+=third);
//            y+=third;
//            g.drawLine(x, y, x, y+=third);
//            g.drawLine(x, y, x-=third*3, y);
//            g.drawLine(x, y, x, y-=third*3);
//            g.drawLine(x, y, x+=third, y);
//        }
//        else if(rotation == 1){
//            x-=third;
//            g.drawLine(x, y, x, y-=third);
//            g.drawLine(x, y, x+=third, y);
//            x+=third;
//            g.drawLine(x, y, x+=third, y);
//            g.drawLine(x, y, x, y+=third*3);
//            g.drawLine(x, y, x-=third*3, y);
//            g.drawLine(x, y, x, y-=third);
//        }
    }

    private void paintRoomF(Graphics g, int x, int y, int rotation){
        if(rotation == 0 || rotation == 2 || rotation == -2){
            g.drawImage(Expedition.RoomF_0, x, y, null);
        }else{
            g.drawImage(Expedition.RoomF_1, x, y, null);
        }
//        if(rotation == 0){
//            y+=third;
//            g.drawLine(x, y, x-=third, y);
//            g.drawLine(x, y, x, y-=third*3);
//            g.drawLine(x, y, x+=third, y);
//            x+=third;
//            g.drawLine(x, y, x+=third, y);
//            g.drawLine(x, y, x, y+=third*3);
//            g.drawLine(x, y, x-=third, y);
//        }else if(rotation == -1){
//            x+=third;
//           g.drawLine(x, y, x, y+=third);
//           g.drawLine(x, y, x-=third*3, y);
//           g.drawLine(x, y, x, y-=third);
//           y-=third;
//           g.drawLine(x, y, x, y-=third);
//           g.drawLine(x, y, x+=third*3, y);
//           g.drawLine(x, y, x, y+=third);
//        }else if(rotation == -2 || rotation == 2){
//            y-=third;
//            g.drawLine(x, y, x+=third, y);
//            g.drawLine(x, y, x, y+=third*3);
//            g.drawLine(x, y, x-=third, y);
//            x-=third;
//            g.drawLine(x, y, x-=third, y);
//            g.drawLine(x, y, x, y-=third*3);
//            g.drawLine(x, y, x+=third, y);
//        }else if(rotation == 1){
//            x-=third;
//            g.drawLine(x, y, x, y-=third);
//            g.drawLine(x, y, x+=third*3, y);
//            g.drawLine(x, y, x, y+=third);
//            y+=third;
//            g.drawLine(x, y, x, y+=third);
//            g.drawLine(x, y, x-=third*3, y);
//            g.drawLine(x, y, x, y-=third);
//        }
    }

    private void paintRoomR(Graphics g, int x, int y, int rotation){
        if(rotation == 0){
            g.drawImage(Expedition.RoomL_N1, x, y, null);
        }else if(rotation == 1){
            g.drawImage(Expedition.RoomL_0, x, y, null);
        }else if(rotation == 2 || rotation == -2){
            g.drawImage(Expedition.RoomL_1, x, y, null);
        }else if(rotation == -1){
            g.drawImage(Expedition.RoomL_2, x, y, null);
        }
//        if(rotation == 0){
//            y+=third;
//            g.drawLine(x, y, x-=third, y);
//            g.drawLine(x, y, x, y-=third*3);
//            g.drawLine(x, y, x+=third*3, y);
//            g.drawLine(x, y, x, y+=third);
//            y+=third;
//            g.drawLine(x, y, x, y+=third);
//            g.drawLine(x, y, x-=third, y);
//        }else if(rotation == -1){
//            x+=third;
//            g.drawLine(x, y, x, y+=third);
//            g.drawLine(x, y, x-=third*3, y);
//            g.drawLine(x, y, x, y-=third*3);
//            g.drawLine(x, y, x+=third, y);
//            x+=third;
//            g.drawLine(x, y, x+=third, y);
//            g.drawLine(x, y, x, y+=third);
//        }else if(rotation == -2 || rotation == 2){
//            y-=third;
//            g.drawLine(x, y, x+=third, y);
//            g.drawLine(x, y, x, y+=third*3);
//            g.drawLine(x, y, x-=third*3, y);
//            g.drawLine(x, y, x, y-=third);
//            y-=third;
//            g.drawLine(x, y, x, y-=third);
//            g.drawLine(x, y, x+=third, y);
//        }else if(rotation == 1){
//            x-=third;
//            g.drawLine(x, y, x, y-=third);
//            g.drawLine(x, y, x+=third*3, y);
//            g.drawLine(x, y, x, y+=third*3);
//            g.drawLine(x, y, x-=third, y);
//            x-=third;
//            g.drawLine(x, y, x-=third, y);
//            g.drawLine(x, y, x, y-=third);
//        }
    }

    private void paintRoomLF(Graphics g, int x, int y, int rotation){
        if(rotation == 0){
            g.drawImage(Expedition.RoomLR_1, x, y, null);
        }else if(rotation == 1){
            g.drawImage(Expedition.RoomLR_2, x, y, null);
        }else if(rotation == 2 || rotation == -2){
            g.drawImage(Expedition.RoomLR_N1, x, y, null);
        }else if(rotation == -1){
            g.drawImage(Expedition.RoomLR_0, x, y, null);
        }
//        if(rotation == 0){
//            y+=third;
//            g.drawLine(x, y, x-=third, y);
//            g.drawLine(x, y, x, y-=third);
//            y-=third;
//            g.drawLine(x, y, x, y-=third);
//            g.drawLine(x, y, x+=third, y);
//            x+=third;
//            g.drawLine(x, y, x+=third, y);
//            g.drawLine(x, y, x, y+=third*3);
//            g.drawLine(x, y, x-=third, y);
//        }else if(rotation == -1){
//            x+=third;
//            g.drawLine(x, y, x, y+=third);
//            g.drawLine(x, y, x-=third, y);
//            x-=third;
//            g.drawLine(x, y, x-=third, y);
//            g.drawLine(x, y, x, y-=third);
//            y-=third;
//            g.drawLine(x, y, x, y-=third);
//            g.drawLine(x, y, x+=third*3, y);
//            g.drawLine(x, y, x, y+=third);
//        }else if(rotation == -2 || rotation == 2){
//            y-=third;
//            g.drawLine(x, y, x+=third, y);
//            g.drawLine(x, y, x, y+=third);
//            y+=third;
//            g.drawLine(x, y, x, y+=third);
//            g.drawLine(x, y, x-=third, y);
//            x-=third;
//            g.drawLine(x, y, x-=third, y);
//            g.drawLine(x, y, x, y-=third*3);
//            g.drawLine(x, y, x+=third, y);
//        }else if(rotation == 1){
//            x-=third;
//            g.drawLine(x, y, x, y-=third);
//            g.drawLine(x, y, x+=third, y);
//            x+=third;
//            g.drawLine(x, y, x+=third, y);
//            g.drawLine(x, y, x, y+=third);
//            y+=third;
//            g.drawLine(x, y, x, y+=third);
//            g.drawLine(x, y, x-=third*3, y);
//            g.drawLine(x, y, x, y-=third);
//        }
    }

    private void paintRoomFR(Graphics g, int x, int y, int rotation){
        if(rotation == 0){
            g.drawImage(Expedition.RoomLR_N1, x, y, null);
        }else if(rotation == 1){
            g.drawImage(Expedition.RoomLR_0, x, y, null);
        }else if(rotation == 2 || rotation == -2){
            g.drawImage(Expedition.RoomLR_1, x, y, null);
        }else if(rotation == -1){
            g.drawImage(Expedition.RoomLR_2, x, y, null);
        }
//        if(rotation == 0){
//            y+=third;
//            g.drawLine(x, y, x-=third, y);
//            g.drawLine(x, y, x, y-=third*3);
//            g.drawLine(x, y, x+=third, y);
//            x+=third;
//            g.drawLine(x, y, x+=third, y);
//            g.drawLine(x, y, x, y+=third);
//            y+=third;
//            g.drawLine(x, y, x, y+=third);
//            g.drawLine(x, y, x-=third, y);
//        }else if(rotation == -1){
//            x+=third;
//            g.drawLine(x, y, x, y+=third);
//            g.drawLine(x, y, x-=third*3, y);
//            g.drawLine(x, y, x, y-=third);
//            y-=third;
//            g.drawLine(x, y, x, y-=third);
//            g.drawLine(x, y, x+=third, y);
//            x+=third;
//            g.drawLine(x, y, x+=third, y);
//            g.drawLine(x, y, x, y+=third);
//        }else if(rotation == -2 || rotation == 2){
//            y-=third;
//            g.drawLine(x, y, x+=third, y);
//            g.drawLine(x, y, x, y+=third*3);
//            g.drawLine(x, y, x-=third, y);
//            x-=third;
//            g.drawLine(x, y, x-=third, y);
//            g.drawLine(x, y, x, y-=third);
//            y-=third;
//            g.drawLine(x, y, x, y-=third);
//            g.drawLine(x, y, x+=third, y);
//        }else if(rotation == 1){
//            x-=third;
//            g.drawLine(x, y, x, y-=third);
//            g.drawLine(x, y, x+=third*3, y);
//            g.drawLine(x, y, x, y+=third);
//            y+=third;
//            g.drawLine(x, y, x, y+=third);
//            g.drawLine(x, y, x-=third, y);
//            x-=third;
//            g.drawLine(x, y, x-=third, y);
//            g.drawLine(x, y, x, y-=third);
//        }
    }

    private void paintRoomLR(Graphics g, int x, int y, int rotation){
        if(rotation == 0){
            g.drawImage(Expedition.RoomLR_0, x, y, null);
        }else if(rotation == 1){
            g.drawImage(Expedition.RoomLR_1, x, y, null);
        }else if(rotation == 2 || rotation == -2){
            g.drawImage(Expedition.RoomLR_2, x, y, null);
        }else if(rotation == -1){
            g.drawImage(Expedition.RoomLR_N1, x, y, null);
        }
//        if (rotation == 0) {
//            y+=third;
//            g.drawLine(x, y, x-=third, y);
//            g.drawLine(x, y, x, y-=third);
//            y-=third;
//            g.drawLine(x, y, x, y-=third);
//            g.drawLine(x, y, x+=third*3, y);
//            g.drawLine(x, y, x, y+=third);
//            y+=third;
//            g.drawLine(x, y, x, y+=third);
//            g.drawLine(x, y, x-=third, y);
//        }else if(rotation == -1){
//            x+=third;
//            g.drawLine(x, y, x, y+=third);
//            g.drawLine(x, y, x-=third, y);
//            x-=third;
//            g.drawLine(x, y, x-=third, y);
//            g.drawLine(x, y, x, y-=third*3);
//            g.drawLine(x, y, x+=third, y);
//            x+=third;
//            g.drawLine(x, y, x+=third, y);
//            g.drawLine(x, y, x, y+=third);
//        }else if(rotation == -2 || rotation == 2){
//            y-=third;
//            g.drawLine(x, y, x+=third, y);
//            g.drawLine(x, y, x, y+=third);
//            y+=third;
//            g.drawLine(x, y, x, y+=third);
//            g.drawLine(x, y, x-=third*3, y);
//            g.drawLine(x, y, x, y-=third);
//            y-=third;
//            g.drawLine(x, y, x, y-=third);
//            g.drawLine(x, y, x+=third, y);
//        }else if(rotation == 1){
//            x-=third;
//            g.drawLine(x, y, x, y-=third);
//            g.drawLine(x, y, x+=third, y);
//            x+=third;
//            g.drawLine(x, y, x+=third, y);
//            g.drawLine(x, y, x, y+=third*3);
//            g.drawLine(x, y, x-=third, y);
//            x-=third;
//            g.drawLine(x, y, x-=third, y);
//            g.drawLine(x, y, x, y-=third);
//        }
    }

    private void paintRoomLFR(Graphics g, int x, int y, int rotation){
        g.drawImage(Expedition.RoomLFR, x, y, null);
//        if(rotation == 0){
//            y+=third;
//            g.drawLine(x, y, x-=third, y);
//            g.drawLine(x, y, x, y-=third);
//            y-=third;
//            g.drawLine(x, y, x, y-=third);
//            g.drawLine(x, y, x+=third, y);
//            x+=third;
//            g.drawLine(x, y, x+=third, y);
//            g.drawLine(x, y, x, y+=third);
//            y+=third;
//            g.drawLine(x, y, x, y+=third);
//            g.drawLine(x, y, x-=third, y);
//        }else if(rotation == -1){
//            x+=third;
//            g.drawLine(x, y, x, y+=third);
//            g.drawLine(x, y, x-=third, y);
//            x-=third;
//            g.drawLine(x, y, x-=third, y);
//            g.drawLine(x, y, x, y-=third);
//            y-=third;
//            g.drawLine(x, y, x, y-=third);
//            g.drawLine(x, y, x+=third, y);
//            x+=third;
//            g.drawLine(x, y, x+=third, y);
//            g.drawLine(x, y, x, y+=third);
//        }else if(rotation == -2 || rotation == 2){
//            y-=third;
//            g.drawLine(x, y, x+=third, y);
//            g.drawLine(x, y, x, y+=third);
//            y+=third;
//            g.drawLine(x, y, x, y+=third);
//            g.drawLine(x, y, x-=third, y);
//            x-=third;
//            g.drawLine(x, y, x-=third, y);
//            g.drawLine(x, y, x, y-=third);
//            y-=third;
//            g.drawLine(x, y, x, y-=third);
//            g.drawLine(x, y, x+=third, y);
//        }else if(rotation == 1){
//            x+=third;
//            g.drawLine(x, y, x, y-=third);
//            g.drawLine(x, y, x+=third, y);
//            x+=third;
//            g.drawLine(x, y, x+=third, y);
//            g.drawLine(x, y, x, y+=third);
//            y+=third;
//            g.drawLine(x, y, x, y+=third);
//            g.drawLine(x, y, x-=third, y);
//            x-=third;
//            g.drawLine(x, y, x-=third, y);
//            g.drawLine(x, y, x, y-=third);
//        }
    }
    private void paintRoom0(Graphics g, int x, int y, int rotation){
        if(rotation == 0){
            g.drawImage(Expedition.Room0_0, x, y, null);
        }else if(rotation == 1){
            g.drawImage(Expedition.Room0_1, x, y, null);
        }else if(rotation == 2 || rotation == -2){
            g.drawImage(Expedition.Room0_2, x, y,null);
        }else if(rotation == -1){
            g.drawImage(Expedition.Room0_N1, x, y, null);
        }
//        if(rotation == 0){
//            g.drawLine(x, y, x-=third, y);
//            g.drawLine(x, y, x, y-=third*3);
//            g.drawLine(x, y, x+=third*3, y);
//            g.drawLine(x, y, x, y+=third*3);
//            g.drawLine(x, y, x-=third, y);
//        }else if(rotation == -1){
//            g.drawLine(x, y, x, y+=third);
//            g.drawLine(x, y, x-=third*3, y);
//            g.drawLine(x, y, x, y-=third*3);
//            g.drawLine(x, y, x+=third*3, y);
//            g.drawLine(x, y, x, y+=third);
//        } else if (rotation == -2 || rotation == 2){
//            g.drawLine(x, y, x+=third, y);
//            g.drawLine(x, y, x, y+=third*3);
//            g.drawLine(x, y, x-=third*3, y);
//            g.drawLine(x, y, x, y-=third*3);
//            g.drawLine(x, y, x+=third, y);
//        }else if(rotation == 1){
//            g.drawLine(x, y, x, y-=third);
//            g.drawLine(x, y, x+=third*3, y);
//            g.drawLine(x, y, x, y+=third*3);
//            g.drawLine(x, y, x-=third*3, y);
//            g.drawLine(x, y, x, y-=third);
//        }
    }
}
