package Day7;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.*;


@Getter
@Setter
@AllArgsConstructor
public class Bag {

    String color;
    HashMap<String, Integer> containMap = new HashMap<String, Integer>();


    public boolean noOtherBags(){
        if (containMap.containsKey("nobag")){
            return true;
        }
        return false;
    }

    public List<String> childBagsString(){
        List<String> resultList = new ArrayList<>();

        Iterator it = containMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            String key = (String)pair.getKey();
            resultList.add(key);
        }
        return resultList;
    }

    public List<Bag> childBags(List<Bag> bagList){
        List<Bag> resultList = new ArrayList<>();

        Iterator it = containMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            String key = (String)pair.getKey();

            int pointer = 0;
            while(!bagList.get(pointer).getColor().equals(key) ){
                pointer ++;
            }

            resultList.add(bagList.get(pointer));
        }
        return resultList;
    }



    public List<Integer> childBagsNum(List<Bag> bagList){
        List<Integer> resultList = new ArrayList<>();
        for(int n : containMap.values()){
            resultList.add(n);
        }
        return resultList;
    }


    public boolean canContainGoldBag(){
        Iterator it = containMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            String key = (String)pair.getKey();
            if(key.contains("shiny gold")){
                return true;
            }
        }
        return false;
    }

}
