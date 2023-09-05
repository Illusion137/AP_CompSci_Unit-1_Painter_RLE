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

// #define RLE_HORIZONTAL
// #define DEBUG_IMAGE_PIXELS

#define BMP_HEADER_SIZE 54

#define IMAGE_SIZE 1024
#define ROW_SIZE 32

#define BMP_FILE "./eveePxlArt.bmp"

//Vertical RunLengthEncoder 
int main() {
    //image but the rows of pixels are reversed
    Pixel* rImage = (Pixel*)calloc(IMAGE_SIZE, sizeof(Pixel)); // allocate on heap incase of large image :3
    { // BMP to Pixels -> Reverse Row Order 
        FILE* bmpFile;
        bmpFile = fopen(BMP_FILE, "r");

        if (!bmpFile){ //If bmpFile fails to open
            printf("Failed to open file.\n");
            exit(1); //EXIT_FAILURE
        }

        // strip out BMP header
        fseek(bmpFile, BMP_HEADER_SIZE, NULL);
        { //scope to remove imageBeg at end of scope cuz we don't need it afterwards
            //the end of the image ptr but start of a row
            Pixel* imageEnd = rImage + IMAGE_SIZE - ROW_SIZE;
            for(int i = 0; i < ROW_SIZE; i++){ // foreach row in bmp
                // use BMP 24bit with no alpha channel as set in Krita
                //BITMAPS use BGR, must convert -> RGB
                // Read a row of Pixels into imageEnd
                fread(imageEnd, sizeof(Pixel), ROW_SIZE, bmpFile);
                // Travel back a row in the imageEnd
                imageEnd -= ROW_SIZE;
            }
        }
        fclose(bmpFile);

        #ifdef DEBUG_IMAGE_PIXELS
            for(int i = 0; i < IMAGE_SIZE; i++){
                printf("#%02X%02X%02X\n", rImage[i].r, rImage[i].g, rImage[i].b);
            }
            return 0;
        #endif //DEBUG_IMAGE_PIXELS
    }

    std::unordered_set<std::string> pallete = {};
    std::unordered_map<std::string, int> palleteMap = {};

    //Run length encoded image also allocated on heap
    RLE* rleImg = (RLE*)calloc(IMAGE_SIZE, sizeof(RLE));;
    
    //rle index
    int rleIdx = 0;
    //hex color code buffer 
    char* colbuf = (char*)calloc(8, sizeof(char));
    { // RUN LENGTH ENCODING :3
    #ifdef RLE_HORIZONTAL
        for (int i = 0; i < IMAGE_SIZE; i++) { // foreach pixel RUN LENGTH ENCODE PIXELS
            sprintf(colbuf,"#%02X%02X%02X", rImage[i].r, rImage[i].g, rImage[i].b);

            //insert into pallete set 
            pallete.insert((std::string)colbuf);

            rleImg[rleIdx].l = 1;
            rleImg[rleIdx].p = rImage[i];
            rleIdx++;
            //set RLE pixel color to current image color
            //while image color values are equal
            while ( pixels_equal(rImage[i], rImage[i + 1]) ) {
                //increase RLE length
                rleImg[rleIdx].l++;
                //dont count same value twice so just increment to next
                i++;
            }
        }
    #else
        #define RLE_VERTICAL
    #endif // RLE_HORIZONTAL

    #ifdef RLE_VERTICAL
        // nested for loop iterations count
        for (int i = 0; i < ROW_SIZE; i++) { // foreach Column in rImage RUN LENGTH ENCODE PIXELS
            for (int k = 0; k < ROW_SIZE; k++) { // foreach pixel in Column
                //current Pixel Index
                int cPI = (k * ROW_SIZE) + i;
                //current Pixel Index Next
                int cPIN = k == ROW_SIZE - 1 ? i + 1 : ((k + 1) * ROW_SIZE) + i;

                sprintf(colbuf,"#%02X%02X%02X", rImage[cPI].r, rImage[cPI].g, rImage[cPI].b);
                //insert into pallete set 
                pallete.insert((std::string)colbuf);

                rleImg[rleIdx].l = 1;
                rleImg[rleIdx].p = rImage[cPI];
                //set RLE pixel color to current image color
                //while image color values are equal
                while ( pixels_equal(rImage[cPI], rImage[cPIN]) ) {
                    //increase RLE length
                    rleImg[rleIdx].l++;
                    
                    k++;
                    if(k == ROW_SIZE){
                        k=0;
                        i++;
                        cPI = i;
                    } else {
                        cPI = (k * ROW_SIZE) + i;
                    }
                    cPIN = (k + 1 == ROW_SIZE) ? i + 1 : ((k + 1) * ROW_SIZE) + i;
                }
                rleIdx++;
            }
        }
    #endif // RLE_VERTICAL
        //dont forget to free heap memory :3
        free(rImage);
    }

    printf("public static final Image %s = new Image(new String[]{\n", "img");

    { // PALLETE MAPPING
        //index for palleteMap
        int palleteMapIndex = 0;
        for (std::string const& col : pallete)
        {
            printf("\"%s\",\n", col.c_str());
            palleteMap[col] = palleteMapIndex;
            palleteMapIndex++;
        }
        printf("}, new RLE[]{\n");
    }

    { // Output RLE 
        int i = 0;
        //length check to ensure RLE was functional
        unsigned lenChk = 0;
        //while pixel NOT NULL
        while (i < rleIdx) {
            lenChk += rleImg[i].l;

            sprintf(colbuf,"#%02X%02X%02X", rleImg[i].p.r, rleImg[i].p.g, rleImg[i].p.b);

            printf("new RLE(%d,%d),", rleImg[i].l,  palleteMap[colbuf]);
            
            i++;
        }
        free(colbuf); //free that damn heap memory
        free(rleImg); //FREE IT, FREE THE HEAP MEMORY
#ifdef RLE_HORIZONTAL
        printf("\n}, true);\n");
#else // RLE_VERTICAL
        printf("\n}, false);\n");
#endif // RLE_HORIZONTAL
        printf("lenChk: \n$%u\n", lenChk);
        assert(lenChk >= IMAGE_SIZE && "FAILED TO RLE BMP.");
    }
    return 0;
}