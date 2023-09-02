//FOR CLANG++
//https://stackoverflow.com/questions/22450423/how-to-use-crt-secure-no-warnings
#define _CRT_SECURE_NO_WARNINGS
#include <assert.h>

#include <iostream>
#include <string>
#include <fstream>
#include <unordered_map>
#include <unordered_set>

typedef unsigned char byte;

struct Pixel {
  byte b;
  byte g;
  byte r;
};

//compares 2 pixels
bool pixels_equal(Pixel p1, Pixel p2){
    return p1.r == p2.r && p1.g == p2.g && p1.b == p2.b;
}

struct RLE {
  unsigned l;
  Pixel p;
};

size_t get_file_size(FILE* file) {
    fseek(file, 0, SEEK_END);
    size_t fsize = ftell(file);
    rewind(file);
    return fsize;
}

#define RLE_HORIZONTAL
#define DEBUG_IMAGE_PIXELS

#define OUTPUT_FILE "./RunLengthEncodedPainter.java"

#define BMP_HEADER_SIZE 54

#define IMAGE_SIZE 1024
#define ROW_SIZE 32

//Vertical RunLengthEncoder 
int main() {
    //image but the rows of pixels are reversed
    Pixel* rImage = (Pixel*)calloc(IMAGE_SIZE, sizeof(Pixel)); // allocate on heap incase of large image :3
    { // BMP to Pixels -> Reverse Row Order 
        FILE* bmpFile;
        bmpFile = fopen("./ralsei.bmp", "r");

        if (!bmpFile){ //If bmpFile fails to open
            printf("Failed to open file.\n");
            exit(1); //EXIT_FAILURE
        }

        // strip out BMP header
        fseek(bmpFile, BMP_HEADER_SIZE, 0);

        { //scope to remove imageBeg at end of scope cuz we don't need it afterwards
            //the end of the image ptr but start of a row
            Pixel* imageEnd = rImage + IMAGE_SIZE - ROW_SIZE;
            for(int i = 0; i < ROW_SIZE; i++){ // foreach row in bmp
                // use BMP 24bit with no alpha channel as set in Krita
                //BITMAPS use BGR, must convert -> RGB
                for(int j = 0; j < ROW_SIZE; j++){ // foreach pixel in row in bmp
                    fread(imageEnd, sizeof(Pixel), 1, bmpFile);
                    imageEnd++;
                }
                imageEnd -= ROW_SIZE * 2;
            }
        }
        fclose(bmpFile);

        #ifdef DEBUG_IMAGE_PIXELS
            for(int i = 0; i < IMAGE_SIZE; i++){ // foreach pixel in row in bmp
                printf("#%02X%02X%02X\n", rImage[i].r, rImage[i].g, rImage[i].b);
            }
        #endif //DEBUG_IMAGE_PIXELS
    }

    std::unordered_set<std::string> pallete = {};
    std::unordered_map<std::string, int> palleteMap = {};

    //Run length encoded image also allocated on heap
    RLE* rleImg = (RLE*)calloc(IMAGE_SIZE, sizeof(RLE));;
    
    int j = 0;
    { // RUN LENGTH ENCODING :3
        //rle index
    #ifdef RLE_HORIZONTAL
        for (int i = 0; i < 1024; i++) { // foreach pixel RUN LENGTH ENCODE PIXELS
            //hex color code buffer
            char* colbuf = (char*)calloc(8, sizeof(char));
            sprintf(colbuf,"#%02X%02X%02X", rImage[i].r, rImage[i].g, rImage[i].b);

            //insert into pallete set 
            pallete.insert((std::string)colbuf);
            
            //dont forget to free heap memory :3
            free(colbuf);

            // if all image color values EQUAL the next image color values in array
            if ( pixels_equal(rImage[i], rImage[i + 1]) ) {
                //set RLE pixel color to current image color
                rleImg[j].l = 1;
                rleImg[j].p = rImage[i];
                //while image color values are equal
                while ( pixels_equal(rImage[i], rImage[i + 1]) ) {
                    //increase RLE length
                    rleImg[j].l++;
                    //dont count same value twice so just increment to next
                    i++;
                }
            j++;
            } else {
                rleImg[j].p = rImage[i];
                rleImg[j].l = 1;
                j++;
            }
        }
    #else
        #define RLE_VERTICAL
    #endif // RLE_HORIZONTAL

    #ifdef RLE_VERTICAL
        // nested for loop iterations count
        int c = 0;
        for (int i = 0; i < ROW_SIZE; i++) { // foreach Column in rImage RUN LENGTH ENCODE PIXELS
            for (int k = 0; k < ROW_SIZE; k++) { // foreach pixel in Column
                //hex color code buffer
                char* colbuf = (char*)calloc(8, sizeof(char));
                sprintf(colbuf,"#%02X%02X%02X", rImage[c].r, rImage[c].g, rImage[c].b);

                //insert into pallete set 
                pallete.insert((std::string)colbuf);
                
                c++;

                //dont forget to free heap memory :3
                free(colbuf);
                // if all image color values EQUAL the next image color values in array
                if ( pixels_equal( rImage[k * ROW_SIZE + i], rImage[(k + 1) * ROW_SIZE + i] ) ) {
                    //set RLE pixel color to current image color
                    rleImg[j].l = 1;
                    rleImg[j].p = rImage[k * ROW_SIZE + i];
                    //while image color values are equal
                    while ( pixels_equal(rImage[k * ROW_SIZE + i], rImage[(k + 1) * ROW_SIZE + i]) ) {
                        //increase RLE length
                        rleImg[j].l++;
                        //dont count same value twice so just increment to next
                        k++;
                        if(k == ROW_SIZE - 1){
                            rleImg[j].l++;
                            k = 0;
                            i++;
                        }
                    }
                    j++;
                } else {
                    rleImg[j].p = rImage[k * ROW_SIZE + i];
                    rleImg[j].l = 1;
                    j++;
                }
            }
        }
    #endif // RLE_VERTICAL

        //dont forget to free heap memory :3
        free(rImage);
    }

    FILE* outFile = fopen(OUTPUT_FILE, "w");

    { // Create file boilerplate
    fprintf(outFile, 
"import org.code.neighborhood.*;\n\n\
public class RunLengthEncodedPainter extends PainterPlus {\n\
public final int gsize = 32;\n\
//pallete\n\
public final String[] p = {\n"
    );
    }

    { // PALLETE MAPPING
        //index for palleteMap
        int palleteMapIndex = 0;
        for (std::string const& col : pallete)
        {
            fprintf(outFile, "    \"%s\",\n", col.c_str());
            palleteMap[col] = palleteMapIndex;
            palleteMapIndex++;
        }
        fprintf(outFile, "  };\n");
    }

    { // Output RLE to File for JavaLabs 
        fprintf(outFile, "  // Would use inline class but it claims to not be supported by Java Lab :(\n");
        int i = 0;
        fprintf(outFile, "  public final RLE[] rlePixels = {\n    ");
        //length check to ensure RLE was functional
        unsigned lenChk = 0;
        //while pixel NOT NULL
        while (i < j) {
            lenChk += rleImg[i].l;

            //hex color code buffer
            char* buffer = (char*)calloc(8, sizeof(char));
            sprintf(buffer,"#%02X%02X%02X", rleImg[i].p.r, rleImg[i].p.g, rleImg[i].p.b);

            fprintf(outFile, "new RLE(%d,%d),", rleImg[i].l,  palleteMap[buffer]);

            //free that damn heap memory
            free(buffer);
            i++;
        }
        free(rleImg); //FREE IT, FREE THE HEAP MEMORY
        fprintf(outFile, "\n  };\n");
        printf("$%u\n", lenChk);
        assert(lenChk > IMAGE_SIZE && "FAILED TO RLE BMP.");
    }

    { // Add Vert/Horz Method to file
#ifdef RLE_HORIZONTAL
        const char* templateFileName = "JavaTemplateHorizontal";
#else // RLE_VERTICAL
        const char* templateFileName = "JavaTemplateVertical";
#endif // RLE_HORIZONTAL

        FILE* paintTemplateFile = fopen(templateFileName, "r");
        size_t ptfSize = get_file_size(paintTemplateFile);
        // paintTemplateFile buffer
        char* ptfBuffer = (char*)calloc(ptfSize + 1, sizeof(char));
        fread(ptfBuffer, sizeof(char), ptfSize, paintTemplateFile);
        fclose(paintTemplateFile);

        fprintf(outFile, "%s", ptfBuffer);

        fprintf(outFile, "\n}");
    }

    return 0;
}