import org.code.neighborhood.*;

public class RunLengthEncodedPainter extends PainterPlus {
public final int gsize = 32;
//pallete
public final String[] p = {
    "#56E741",
    "#55CB0E",
    "#000000",
    "#8A399B",
    "#0F876C",
    "#6F6F6F",
    "#13A887",
    "#54E447",
    "#6AFF11",
    "#0FA689",
    "#14AD8B",
    "#6DFF00",
    "#B1C7C1",
    "#51C20D",
    "#0F8269",
    "#368009",
    "#14B08D",
    "#09A38B",
    "#2D6907",
    "#CBCBCB",
    "#00A08D",
    "#CCE8E0",
    "#00A17C",
    "#FFFFFF",
    "#0A5545",
    "#084437",
    "#FD469A",
    "#084C3D",
    "#CECECE",
    "#6EC2AC",
    "#00A682",
    "#08483A",
    "#83389B",
    "#F8459A",
    "#903CA2",
    "#FF479F",
    "#C03678",
    "#DC429A",
    "#6F2E7D",
  };
  // Would use inline class but it claims to not be supported by Java Lab :(
  public final RLE[] rlePixels = {
    new RLE(11,p[2]),new RLE(3,p[6]),new RLE(1,p[7]),new RLE(5,p[8]),new RLE(1,p[7]),new RLE(21,p[2]),new RLE(4,p[6]),new RLE(7,p[8]),new RLE(1,p[4]),new RLE(4,p[2]),new RLE(1,p[8]),new RLE(15,p[2]),new RLE(4,p[6]),new RLE(7,p[8]),new RLE(1,p[0]),new RLE(4,p[2]),new RLE(1,p[11]),new RLE(1,p[1]),new RLE(8,p[2]),new RLE(1,p[13]),new RLE(4,p[2]),new RLE(1,p[10]),new RLE(4,p[6]),new RLE(8,p[8]),new RLE(3,p[2]),new RLE(1,p[6]),new RLE(1,p[11]),new RLE(1,p[8]),new RLE(8,p[2]),new RLE(1,p[7]),new RLE(1,p[8]),new RLE(3,p[2]),new RLE(1,p[10]),new RLE(4,p[6]),new RLE(8,p[8]),new RLE(1,p[7]),new RLE(1,p[2]),new RLE(1,p[14]),new RLE(1,p[6]),new RLE(2,p[8]),new RLE(8,p[2]),new RLE(1,p[7]),new RLE(1,p[8]),new RLE(1,p[15]),new RLE(2,p[2]),new RLE(5,p[6]),new RLE(9,p[8]),new RLE(1,p[6]),new RLE(4,p[8]),new RLE(8,p[2]),new RLE(1,p[7]),new RLE(2,p[8]),new RLE(1,p[13]),new RLE(1,p[14]),new RLE(5,p[6]),new RLE(15,p[8]),new RLE(1,p[7]),new RLE(1,p[14]),new RLE(5,p[2]),new RLE(1,p[9]),new RLE(3,p[8]),new RLE(1,p[11]),new RLE(4,p[6]),new RLE(1,p[11]),new RLE(3,p[8]),new RLE(1,p[1]),new RLE(2,p[2]),new RLE(10,p[8]),new RLE(1,p[11]),new RLE(1,p[16]),new RLE(3,p[2]),new RLE(2,p[6]),new RLE(2,p[11]),new RLE(1,p[17]),new RLE(5,p[6]),new RLE(1,p[11]),new RLE(2,p[8]),new RLE(3,p[2]),new RLE(1,p[18]),new RLE(10,p[8]),new RLE(1,p[17]),new RLE(2,p[2]),new RLE(1,p[10]),new RLE(10,p[6]),new RLE(1,p[11]),new RLE(2,p[8]),new RLE(9,p[2]),new RLE(4,p[8]),new RLE(1,p[20]),new RLE(5,p[2]),new RLE(1,p[10]),new RLE(7,p[6]),new RLE(1,p[17]),new RLE(1,p[11]),new RLE(2,p[8]),new RLE(8,p[2]),new RLE(5,p[8]),new RLE(1,p[16]),new RLE(9,p[2]),new RLE(3,p[6]),new RLE(1,p[17]),new RLE(1,p[11]),new RLE(3,p[8]),new RLE(28,p[2]),new RLE(1,p[11]),new RLE(3,p[8]),new RLE(21,p[2]),new RLE(1,p[4]),new RLE(18,p[2]),new RLE(1,p[21]),new RLE(3,p[2]),new RLE(1,p[22]),new RLE(4,p[2]),new RLE(1,p[10]),new RLE(3,p[2]),new RLE(1,p[14]),new RLE(6,p[2]),new RLE(4,p[23]),new RLE(7,p[2]),new RLE(1,p[23]),new RLE(5,p[2]),new RLE(1,p[23]),new RLE(4,p[2]),new RLE(1,p[10]),new RLE(2,p[2]),new RLE(1,p[24]),new RLE(1,p[2]),new RLE(1,p[4]),new RLE(1,p[8]),new RLE(1,p[15]),new RLE(1,p[2]),new RLE(1,p[23]),new RLE(5,p[2]),new RLE(1,p[23]),new RLE(2,p[2]),new RLE(2,p[23]),new RLE(1,p[5]),new RLE(2,p[2]),new RLE(1,p[23]),new RLE(1,p[19]),new RLE(3,p[2]),new RLE(1,p[23]),new RLE(5,p[2]),new RLE(1,p[6]),new RLE(2,p[2]),new RLE(1,p[24]),new RLE(1,p[2]),new RLE(1,p[13]),new RLE(1,p[23]),new RLE(1,p[10]),new RLE(2,p[2]),new RLE(2,p[23]),new RLE(1,p[2]),new RLE(1,p[22]),new RLE(1,p[2]),new RLE(1,p[23]),new RLE(2,p[2]),new RLE(1,p[23]),new RLE(2,p[2]),new RLE(1,p[23]),new RLE(1,p[19]),new RLE(3,p[2]),new RLE(1,p[23]),new RLE(5,p[2]),new RLE(1,p[6]),new RLE(2,p[2]),new RLE(1,p[25]),new RLE(2,p[2]),new RLE(1,p[23]),new RLE(3,p[2]),new RLE(2,p[23]),new RLE(2,p[2]),new RLE(1,p[19]),new RLE(3,p[2]),new RLE(1,p[5]),new RLE(1,p[27]),new RLE(1,p[2]),new RLE(1,p[23]),new RLE(1,p[19]),new RLE(2,p[2]),new RLE(1,p[27]),new RLE(1,p[23]),new RLE(4,p[2]),new RLE(1,p[14]),new RLE(6,p[2]),new RLE(1,p[23]),new RLE(3,p[2]),new RLE(2,p[23]),new RLE(7,p[2]),new RLE(1,p[23]),new RLE(5,p[2]),new RLE(1,p[23]),new RLE(5,p[2]),new RLE(1,p[14]),new RLE(6,p[2]),new RLE(1,p[28]),new RLE(1,p[29]),new RLE(2,p[2]),new RLE(2,p[28]),new RLE(1,p[2]),new RLE(1,p[29]),new RLE(6,p[2]),new RLE(1,p[12]),new RLE(3,p[5]),new RLE(1,p[29]),new RLE(6,p[2]),new RLE(1,p[24]),new RLE(8,p[2]),new RLE(1,p[30]),new RLE(3,p[2]),new RLE(1,p[23]),new RLE(18,p[2]),new RLE(1,p[24]),new RLE(8,p[2]),new RLE(1,p[28]),new RLE(3,p[19]),new RLE(19,p[2]),new RLE(1,p[24]),new RLE(31,p[2]),new RLE(1,p[25]),new RLE(29,p[2]),new RLE(1,p[31]),new RLE(2,p[2]),new RLE(1,p[6]),new RLE(20,p[2]),new RLE(1,p[23]),new RLE(8,p[2]),new RLE(1,p[6]),new RLE(1,p[14]),new RLE(1,p[6]),new RLE(20,p[2]),new RLE(1,p[23]),new RLE(2,p[2]),new RLE(1,p[32]),new RLE(1,p[33]),new RLE(1,p[3]),new RLE(5,p[2]),new RLE(1,p[6]),new RLE(1,p[24]),new RLE(4,p[2]),new RLE(1,p[34]),new RLE(1,p[32]),new RLE(1,p[35]),new RLE(1,p[34]),new RLE(2,p[2]),new RLE(1,p[23]),new RLE(7,p[2]),new RLE(3,p[35]),new RLE(4,p[33]),new RLE(5,p[2]),new RLE(1,p[10]),new RLE(5,p[2]),new RLE(1,p[34]),new RLE(1,p[32]),new RLE(5,p[33]),new RLE(1,p[35]),new RLE(2,p[2]),new RLE(2,p[33]),new RLE(1,p[36]),new RLE(7,p[33]),new RLE(1,p[3]),new RLE(9,p[2]),new RLE(1,p[34]),new RLE(1,p[2]),new RLE(1,p[34]),new RLE(2,p[3]),new RLE(2,p[32]),new RLE(3,p[33]),new RLE(1,p[35]),new RLE(7,p[33]),new RLE(1,p[37]),new RLE(2,p[32]),new RLE(2,p[3]),new RLE(8,p[2]),new RLE(2,p[33]),new RLE(2,p[2]),new RLE(7,p[3]),new RLE(1,p[26]),new RLE(6,p[33]),new RLE(3,p[3]),new RLE(1,p[33]),new RLE(1,p[35]),new RLE(6,p[2]),new RLE(1,p[34]),new RLE(1,p[3]),new RLE(3,p[33]),new RLE(1,p[3]),new RLE(3,p[2]),new RLE(14,p[3]),new RLE(2,p[32]),new RLE(6,p[2]),new RLE(1,p[38]),new RLE(6,p[3]),new RLE(4,p[2]),new RLE(1,p[34]),new RLE(12,p[3]),new RLE(10,p[2]),
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