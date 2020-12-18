package Day17;

import Day16.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Day17 {


    Utils utils = new Utils();
    Grid grid = new Grid();
    Grid4D grid4D = new Grid4D();

    public void go(){

        List<String> input = utils.readfile("input17.txt");

        int pointer = 0;
        long x = 0; long y=0; long z = 0;

        // Read in repo
        while (pointer < input.size()){
            for(int t=0; t < input.get(pointer).length(); t++){
                Character c = input.get(pointer).charAt(t);
                grid.enter(x,y,z,c);
                x++;
            }
            x=0;
            y++;
            pointer ++;
        };

        for (int t= 0 ;t <6;t++){
            grid.printGrid();
            grid.generation();
        }

        System.out.println("Aantal: " +  grid.countActive());



    }

    public void go2(){

        List<String> input = utils.readfile("input17.txt");

        int pointer = 0;
        long x = 0; long y=0; long z = 0; long w=0;

        // Read in repo
        while (pointer < input.size()){
            for(int t=0; t < input.get(pointer).length(); t++){
                Character c = input.get(pointer).charAt(t);
                grid4D.enter(x,y,z,w,c);
                x++;
            }
            x=0;
            y++;
            pointer ++;
        };

        for (int t= 0 ;t <6;t++){
            System.out.println("Nieuwe generation");
            grid4D.printGrid();
            grid4D.generation();

        }

        System.out.println("Aantal: " +  grid4D.countActive());



    }



}
