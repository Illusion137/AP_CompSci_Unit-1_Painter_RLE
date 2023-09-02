import org.code.neighborhood.*;

public class RunLengthEncodedPainter extends PainterPlus {
public final int gsize = 32;
//pallete
public final String[] p = {
    "#80A1D3",
    "#CA2368",
    "#C47360",
    "#218564",
    "#A5A1C9",
    "#865942",
    "#BF6F5D",
    "#E2D789",
    "#4D2B06",
    "#DEBE48",
    "#83BF9B",
    "#FFFFFF",
    "#E0E37F",
    "#E1618E",
    "#E0A5BF",
    "#E49FB5",
    "#DA7B9B",
    "#CF5E93",
    "#121212",
    "#895A43",
    "#4C2B06",
    "#D7427B",
  };
  // Would use inline class but it claims to not be supported by Java Lab :(
  public final RLE[] rlePixels = {
    new RLE(33,0),new RLE(2,1),new RLE(9,0),new RLE(3,2),new RLE(17,0),new RLE(4,1),new RLE(2,4),new RLE(5,0),new RLE(5,5),new RLE(1,2),new RLE(5,0),new RLE(2,1),new RLE(8,0),new RLE(4,1),new RLE(3,4),new RLE(3,0),new RLE(1,5),new RLE(1,2),new RLE(3,7),new RLE(1,9),new RLE(2,5),new RLE(3,0),new RLE(4,1),new RLE(2,4),new RLE(6,0),new RLE(2,1),new RLE(4,4),new RLE(2,0),new RLE(1,5),new RLE(1,2),new RLE(6,7),new RLE(1,9),new RLE(1,5),new RLE(2,0),new RLE(4,1),new RLE(3,4),new RLE(8,0),new RLE(2,4),new RLE(3,0),new RLE(1,5),new RLE(1,9),new RLE(7,7),new RLE(1,9),new RLE(1,5),new RLE(1,2),new RLE(1,0),new RLE(2,1),new RLE(4,4),new RLE(12,0),new RLE(2,2),new RLE(3,7),new RLE(5,9),new RLE(1,7),new RLE(1,9),new RLE(1,2),new RLE(6,5),new RLE(2,2),new RLE(11,0),new RLE(1,5),new RLE(1,9),new RLE(1,7),new RLE(2,9),new RLE(7,3),new RLE(1,9),new RLE(5,7),new RLE(1,9),new RLE(1,2),new RLE(2,5),new RLE(9,0),new RLE(2,5),new RLE(1,7),new RLE(1,3),new RLE(1,10),new RLE(1,11),new RLE(1,10),new RLE(4,3),new RLE(2,10),new RLE(2,3),new RLE(1,9),new RLE(5,7),new RLE(1,9),new RLE(1,2),new RLE(1,5),new RLE(6,0),new RLE(1,2),new RLE(1,5),new RLE(1,9),new RLE(1,7),new RLE(1,3),new RLE(1,10),new RLE(2,3),new RLE(4,9),new RLE(2,3),new RLE(2,11),new RLE(1,10),new RLE(1,3),new RLE(1,9),new RLE(4,7),new RLE(2,9),new RLE(1,5),new RLE(5,0),new RLE(1,5),new RLE(1,2),new RLE(1,9),new RLE(1,7),new RLE(2,3),new RLE(1,9),new RLE(1,12),new RLE(4,11),new RLE(1,7),new RLE(2,9),new RLE(1,3),new RLE(1,10),new RLE(1,11),new RLE(2,3),new RLE(1,9),new RLE(2,7),new RLE(3,9),new RLE(1,5),new RLE(4,0),new RLE(1,5),new RLE(1,9),new RLE(2,7),new RLE(2,9),new RLE(1,7),new RLE(6,11),new RLE(3,7),new RLE(2,9),new RLE(4,3),new RLE(4,9),new RLE(1,2),new RLE(1,5),new RLE(3,0),new RLE(1,5),new RLE(1,9),new RLE(2,7),new RLE(2,9),new RLE(7,11),new RLE(5,7),new RLE(1,9),new RLE(1,2),new RLE(4,3),new RLE(3,9),new RLE(1,5),new RLE(1,2),new RLE(2,0),new RLE(1,5),new RLE(1,9),new RLE(3,7),new RLE(1,2),new RLE(1,7),new RLE(4,11),new RLE(2,7),new RLE(8,9),new RLE(1,2),new RLE(2,10),new RLE(1,3),new RLE(1,2),new RLE(1,9),new RLE(1,2),new RLE(1,5),new RLE(2,0),new RLE(2,2),new RLE(3,7),new RLE(2,9),new RLE(4,7),new RLE(2,9),new RLE(3,13),new RLE(5,2),new RLE(3,5),new RLE(1,10),new RLE(2,3),new RLE(1,9),new RLE(1,5),new RLE(1,2),new RLE(2,0),new RLE(1,5),new RLE(1,9),new RLE(3,7),new RLE(1,2),new RLE(1,9),new RLE(2,7),new RLE(1,9),new RLE(1,2),new RLE(1,13),new RLE(9,15),new RLE(1,16),new RLE(1,13),new RLE(1,2),new RLE(1,5),new RLE(2,3),new RLE(2,5),new RLE(3,0),new RLE(1,5),new RLE(2,9),new RLE(2,7),new RLE(1,5),new RLE(1,9),new RLE(1,7),new RLE(1,2),new RLE(1,13),new RLE(7,15),new RLE(1,16),new RLE(1,6),new RLE(4,15),new RLE(1,16),new RLE(1,2),new RLE(1,3),new RLE(2,9),new RLE(1,5),new RLE(1,2),new RLE(2,0),new RLE(1,5),new RLE(4,9),new RLE(1,2),new RLE(1,9),new RLE(1,2),new RLE(1,13),new RLE(6,15),new RLE(2,17),new RLE(1,18),new RLE(1,17),new RLE(5,15),new RLE(1,16),new RLE(1,2),new RLE(2,11),new RLE(1,9),new RLE(1,5),new RLE(3,0),new RLE(1,5),new RLE(4,9),new RLE(1,2),new RLE(1,13),new RLE(6,15),new RLE(1,19),new RLE(2,18),new RLE(1,16),new RLE(7,15),new RLE(1,2),new RLE(1,7),new RLE(1,11),new RLE(1,9),new RLE(1,5),new RLE(4,0),new RLE(1,5),new RLE(3,9),new RLE(1,5),new RLE(2,16),new RLE(1,15),new RLE(1,13),new RLE(1,18),new RLE(9,15),new RLE(2,16),new RLE(1,15),new RLE(2,13),new RLE(1,7),new RLE(2,9),new RLE(1,5),new RLE(5,0),new RLE(1,5),new RLE(2,9),new RLE(1,5),new RLE(1,16),new RLE(1,19),new RLE(2,18),new RLE(1,13),new RLE(3,15),new RLE(1,14),new RLE(5,15),new RLE(1,13),new RLE(1,2),new RLE(2,13),new RLE(1,2),new RLE(1,9),new RLE(1,2),new RLE(1,5),new RLE(1,2),new RLE(6,0),new RLE(1,5),new RLE(1,9),new RLE(1,5),new RLE(2,16),new RLE(2,13),new RLE(9,15),new RLE(1,16),new RLE(1,13),new RLE(2,2),new RLE(2,8),new RLE(3,5),new RLE(7,0),new RLE(1,2),new RLE(1,5),new RLE(1,2),new RLE(2,16),new RLE(5,15),new RLE(1,20),new RLE(4,15),new RLE(1,16),new RLE(1,13),new RLE(1,2),new RLE(4,1),new RLE(2,9),new RLE(1,5),new RLE(8,0),new RLE(1,5),new RLE(1,13),new RLE(1,15),new RLE(1,13),new RLE(1,16),new RLE(8,15),new RLE(1,16),new RLE(1,13),new RLE(1,2),new RLE(1,1),new RLE(3,21),new RLE(1,1),new RLE(1,2),new RLE(1,7),new RLE(2,2),new RLE(7,0),new RLE(1,5),new RLE(1,13),new RLE(1,16),new RLE(1,2),new RLE(2,13),new RLE(6,16),new RLE(2,13),new RLE(1,2),new RLE(1,1),new RLE(2,21),new RLE(1,15),new RLE(1,21),new RLE(1,1),new RLE(1,2),new RLE(1,7),new RLE(1,9),new RLE(1,5),new RLE(7,0),new RLE(1,5),new RLE(1,2),new RLE(1,13),new RLE(3,2),new RLE(7,13),new RLE(1,2),new RLE(2,1),new RLE(4,21),new RLE(1,1),new RLE(1,2),new RLE(2,9),new RLE(1,5),new RLE(3,0),new RLE(2,1),new RLE(2,0),new RLE(1,5),new RLE(1,9),new RLE(1,2),new RLE(4,1),new RLE(5,2),new RLE(3,5),new RLE(5,1),new RLE(1,8),new RLE(2,9),new RLE(1,5),new RLE(1,2),new RLE(2,0),new RLE(4,1),new RLE(1,4),new RLE(1,5),new RLE(1,9),new RLE(1,1),new RLE(1,21),new RLE(1,15),new RLE(5,1),new RLE(1,5),new RLE(1,2),new RLE(1,9),new RLE(2,2),new RLE(2,5),new RLE(3,8),new RLE(1,2),new RLE(2,5),new RLE(1,2),new RLE(3,0),new RLE(4,1),new RLE(1,4),new RLE(1,5),new RLE(1,9),new RLE(1,1),new RLE(4,21),new RLE(2,1),new RLE(1,2),new RLE(6,9),new RLE(2,2),new RLE(3,5),new RLE(1,2),new RLE(3,0),new RLE(2,4),new RLE(1,0),new RLE(2,1),new RLE(2,4),new RLE(1,2),new RLE(1,9),new RLE(1,2),new RLE(4,1),new RLE(1,8),new RLE(1,2),new RLE(1,9),new RLE(1,2),new RLE(7,5),new RLE(1,2),new RLE(3,0),new RLE(2,1),new RLE(3,4),new RLE(3,0),new RLE(2,4),new RLE(1,0),new RLE(1,5),new RLE(1,9),new RLE(1,2),new RLE(3,8),new RLE(2,2),new RLE(2,5),new RLE(1,2),new RLE(9,0),new RLE(4,1),new RLE(2,4),new RLE(7,0),new RLE(7,5),new RLE(12,0),new RLE(4,1),new RLE(2,4),
  };
  public void paintRLEHorizontal(){
    int row = 0;
    //while we haven't traveled through all the RLE's(Run length Encoded) Data
    for(int i = 0; i < rlePixels.length; i++){
      //while we haven't traveled through the entire length of the RLE
      for(int traveled = 0; traveled < rlePixels[i].len; traveled++){
        //paint the RLE color
        this.paint(rlePixels[i].color);
        if(this.canMove()) 
          this.move();
        else{ //if can't move go to beginning of next row
          row++;
          if(row >= this.gsize)
            return;
          this.moveToPos(0, row, "east");
        }
      }
    }
  }
}