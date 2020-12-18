package Day17;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Grid4D {

    HashMap<Long, HashMap<Long, HashMap<Long, HashMap<Long, Character>>>> grid = new HashMap<>();

    long xf = 0;
    long xt = 0;
    long yf = 0;
    long wf = 0;
    long yt = 0;
    long zf = 0;
    long zt = 0;
    long wt = 0;


    public void enter(Long x, Long y, Long z, Long w, Character character) {

        xf = Long.min(xf, x);
        yf = Long.min(yf, y);
        zf = Long.min(zf, z);
        wf = Long.min(wf, z);

        xt = Long.max(xt, x);
        yt = Long.max(yt, y);
        zt = Long.max(zt, z);
        wt = Long.max(wt, z);

        if (grid.get(x) == null) {
            grid.put(x, new HashMap<Long, HashMap<Long, HashMap<Long, Character>>>());
        }
        if (grid.get(x).get(y) == null) {
            grid.get(x).put(y, new HashMap<Long, HashMap<Long, Character>>());
        }
        if (grid.get(x).get(y).get(z) == null) {
            grid.get(x).get(y).put(z, new HashMap<Long, Character>());
        }
        grid.get(x).get(y).get(z).put(w, character);

    }


    public void generation() {
        List<Action4d> actionList = new ArrayList<>();
        for (long wc = wf - 1; wc <= wt + 1; wc++) {
            for (long zc = zf - 1; zc <= zt + 1; zc++) {
                for (long yc = yf - 1; yc <= yt + 1; yc++) {
                    for (long xc = xf - 1; xc <= xt + 1; xc++) {
                        int activeNeighbours = activeNeighbours(xc, yc, zc, wc);
                        if (get(xc, yc, zc, wc).equals('#')) {   // Its 'alive
                            if (activeNeighbours == 2 || activeNeighbours == 3) {
                                actionList.add(new Action4d(xc, yc, zc, wc, '#'));
                            } else {
                                actionList.add(new Action4d(xc, yc, zc,wc, '.'));
                            }
                        } else {   // Is is not alivbe
                            if (activeNeighbours == 3) {
                                actionList.add(new Action4d(xc, yc, zc, wc,'#'));
                            }
                        }
                    }
                }
            }

        }
        actionList.forEach(action ->
                enter(action.getX(), action.getY(), action.getZ(), action.getW(), action.getCharacter())
        );
    }


    public int activeNeighbours(long x, long y, long z, long w) {
        int result = 0;
        for (long wc = w - 1; wc <= w + 1; wc++) {
            for (long zc = z - 1; zc <= z + 1; zc++) {
                for (long yc = y - 1; yc <= y + 1; yc++) {
                    for (long xc = x - 1; xc <= x + 1; xc++) {
                        if ((x == xc) && (y == yc) && (z == zc) && (w==wc)) {
                            continue;
                        }
                        if (get(xc, yc, zc, wc).equals('#')) {
                            result++;
                        }
                    }
                }
            }
        }
        return result;
    }


    public int countActive() {
        int result = 0;
        for (long wc = wf; wc <= wt; wc++) {
            for (long zc = zf; zc <= zt; zc++) {
                for (long yc = yf; yc <= yt; yc++) {
                    for (long xc = xf; xc <= xt; xc++) {
                        if (get(xc, yc, zc, wc).equals('#')) {
                            result++;
                        }
                    }
                }
            }
        }
        return result;
    }


    public Character get(Long x, Long y, Long z, Long w) {
        if (grid.get(x) == null) {
            return '.';
        }

        if (grid.get(x).get(y) == null) {
            return '.';
        }

        if (grid.get(x).get(y).get(z) == null) {
            return '.';
        }

        if (grid.get(x).get(y).get(z).get(w) == null) {
            return '.';
        }

        return grid.get(x).get(y).get(z).get(w);
    }


    public void printGrid() {
        for (long wc = wf; wc <= wt; wc++) {
            System.out.println("w: " + wc);
            for (long zc = zf; zc <= zt; zc++) {
                System.out.println("z: " + zc);
                for (long yc = yf; yc <= yt; yc++) {
                    for (long xc = xf; xc <= xt; xc++) {
                        System.out.print(get(xc, yc, zc,wc));
                    }
                    System.out.println();
                }
                System.out.println();
            }
        }
    }
}
