package Day7;

import Day16.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Day7 {


    Utils utils = new Utils();

    public void go(){


        List<String> input = utils.readfile("input7.txt");

        List<Bag> bagListRep = new ArrayList<>();

        // parsing bags into objectmodel

        initializeRepo(input, bagListRep);

        // find all bags that can contain gold bag

        int numberOfBags = 0;

        for (Bag bag : bagListRep){
            if (bag.canContainGoldBag()){
                System.out.println("Can contain: " + bag.getColor());
                numberOfBags++;
            } else {

                List<String> checkBags = bag.childBagsString();

//                if (recCheckBags(checkBags,bagListRep)){
//                    numberOfBags ++;
//                };

            }
        }
        System.out.println("TOTAL BAGS" + numberOfBags);

    }


    public void go2(){
        List<String> input = utils.readfile("input7.txt");

        List<Bag> bagListRep = new ArrayList<>();

        // parsing bags into objectmodel

        initializeRepo(input, bagListRep);
        int pointer = 0;
        while (!bagListRep.get(pointer).getColor().equals("shiny gold")){
            pointer ++;
        }
        Bag searchBag = bagListRep.get(pointer);
        long  count = recursiveCount(searchBag, bagListRep,0);

        System.out.println("Totoal bags" + count);
    }

    private long recursiveCount(Bag bag, List<Bag> bagListRep, long count) {
        List<Bag> childBags = new ArrayList<>();
        List<Integer> childNumber = new ArrayList<>();
        int pointer = 0;
        while (!bagListRep.get(pointer).getColor().equals(bag.getColor())){
            pointer ++;
        }
        childBags = bagListRep.get(pointer).childBags(bagListRep);
        childNumber = bagListRep.get(pointer).childBagsNum(bagListRep);

        if (childBags.size() == 0 ){
            return 1;
        } else {

            int index = 0;
            for (Bag childBag: childBags){
                count = count + childNumber.get(index) * recursiveCount(childBag, bagListRep,count);
                index ++;
            }
        }
        return count;


    }


    private void initializeRepo(List<String> input, List<Bag> bagListRep) {
        int pointer = 0;
        while (pointer < input.size()){
            String name = input.get(pointer).replaceAll(" bags.*","");
            List<String> containList = Arrays.asList(input.get(pointer).replaceAll(".*contain","").split(","));

            HashMap<String,Integer> containMap = new HashMap<>();
            for (String bag : containList){
                bag = bag.replaceAll(" bag.*","");


                int number;
                if (bag.contains("no")){
                    number = 0;
                } else {
                    number = Integer.parseInt(bag.substring(1,2));
                    bag = bag.substring(3);
                    containMap.put(bag,number);
                }
            }
            bagListRep.add(new Bag(name, containMap));
            pointer++;
        }
    }

    private boolean recCheckBags(List<Bag> checkBags, List<Bag> bagList) {

        boolean result = false;

        if (checkBags.size()==0){
            return false;
        }

        for (Bag checkBag: checkBags){
            for (Bag bag : bagList){
                String bagC = bag.getColor();
                if (bag.getColor().equals(checkBag.getColor())){
                    if (bag.canContainGoldBag()){
                        return true;
                    } else {
                        result = result ||recCheckBags(bag.childBags(bagList), bagList);
                    }
                }
            }


        }
        return result;
    }



}
