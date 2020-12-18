package Day17;

import com.sun.imageio.plugins.gif.GIFImageReader;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Getter
@Setter
public class Grid {


    HashMap<Long,HashMap<Long,HashMap<Long,Character>>> grid = new HashMap<>();

    long xf = 0;
    long xt = 0;
    long yf = 0;
    long yt = 0;
    long zf = 0;
    long zt = 0;


    public void enter(Long x,Long y ,Long z, Character character){
        xf = Long.min(xf,x);
        yf = Long.min(yf,y);
        zf = Long.min(zf,z);
        xt = Long.max(xt,x);
        yt = Long.max(yt,y);
        zt = Long.max(zt,z);

        if(grid.get(x) == null) {
                grid.put(x, new HashMap<Long, HashMap<Long, Character>>());
            }
            if(grid.get(x).get(y) == null){
                grid.get(x).put(y, new HashMap<Long, Character>());
            }
            grid.get(x).get(y).put(z, character);

    }



    public void generation(){

        List<Action> actionList= new ArrayList<>();

        for (long zc = zf-1; zc<= zt+1; zc++){
            for (long yc = yf-1; yc<= yt+1; yc++){
                for (long xc = xf-1; xc<= xt+1; xc++){
                    int activeNeighbours = activeNeighbours(xc,yc,zc);
                    if (get(xc,yc,zc).equals('#')){   // Its 'alive
                        if(activeNeighbours ==2 || activeNeighbours ==3){
                            actionList.add(new Action(xc,yc,zc,'#'));
                        } else {
                            actionList.add(new Action(xc,yc,zc,'.'));
                        }
                    }  else {   // Is is not alivbe
                        if(activeNeighbours ==3){
                            actionList.add(new Action(xc,yc,zc,'#'));
                        }
                    }
                }
            }

        }
        actionList.forEach( action ->
                enter(action.getX(),action.getY(), action.getZ(),action.getCharacter())
                );
    }



    public int activeNeighbours(long x, long y, long z){
        int result = 0;
        for (long zc = z-1; zc<= z+1; zc++){
            for (long yc = y-1; yc<= y+1 ; yc++){
                for (long xc = x-1; xc<= x+1; xc++){
                    if ((x == xc) && (y == yc) && (z==zc)){
                        continue;
                    }
                    if (get(xc,yc,zc).equals('#')){
                        result ++;
                    }
                }
            }
        }
        return result;
    }


    public int countActive(){
        int result = 0;
        for (long zc = zf; zc<= zt; zc++){
            for (long yc = yf; yc<= yt ; yc++){
                for (long xc = xf; xc<= xt; xc++){
                    if (get(xc,yc,zc).equals('#')){
                        result ++;
                    }
                }
            }
        }
        return result;
    }


    public Character get (Long x,Long y ,Long z){
        if (grid.get(x) == null){
            return '.';
        }

        if (grid.get(x).get(y) == null){
            return '.';
        }

         if (grid.get(x).get(y).get(z) == null){
            return '.';
         }

        return grid.get(x).get(y).get(z);
    }


    public void printGrid(){
        for (long zc = zf; zc<= zt; zc++){
            System.out.println("z " + zc );
            for (long yc = yf; yc<= yt; yc++){
                for (long xc = xf; xc<= xt; xc++){
                    System.out.print(get(xc,yc,zc));
                }
                System.out.println();
            }
            System.out.println();
        }
    }

}
