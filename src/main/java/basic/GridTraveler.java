package basic;

import java.util.ArrayList;
import java.util.List;

public class GridTraveler {

    public static void main(String[] args) {
        System.err.println(gridTraveler(4, 4));
    }

    public static long gridTraveler(int x, int y) {
        List<List<Long>> table = initializeTable(x, y);
        table.get(1).set(1, 1L);
        for(int i = 1; i <= x; i++) {
            for(int j = 1; j <= y; j++) {
                Long toAdd = table.get(i).get(j);
                if(j + 1 <= y) {
                    Long toRight = table.get(i).get(j + 1);
                    table.get(i).set(j+1, toAdd+toRight);
                }
                if(i + 1 <= x) {
                    Long toBottom = table.get(i + 1).get(j);
                    table.get(i + 1).set(j, toAdd + toBottom);
                }
            }
        }
        return table.get(x).get(y);
    }

    private static List<List<Long>> initializeTable(int x, int y) {
        List<List<Long>> table = new ArrayList<>();
        for(int i = 0; i <= x; i++) {
            List<Long> row = new ArrayList<>();
            for(int j = 0; j<= y; j++) {
                row.add(0L);
            }
            table.add(row);
        }
        return table;
    }
}
