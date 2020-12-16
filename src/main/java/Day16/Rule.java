package Day16;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class Rule {

    String name;
    int from1;
    int to1;
    int from2;
    int to2;
    List<Integer> posPosition = new ArrayList<>();

    public boolean complies(int check){

        return (check >= from1 && check<=to1) || (check >= from2 && check<=to2);

    }

    public void removepos(int value){
        if (posPosition.contains(value) && posPosition.size()>1){
            posPosition.remove(posPosition.indexOf(value));
        }

    }

}
