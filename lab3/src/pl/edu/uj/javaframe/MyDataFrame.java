package pl.edu.uj.javaframe;

public class MyDataFrame extends DataFrame {

    public MyDataFrame(Class<? extends Value>[] types, String[] vals) {
        super(types, vals);
    }

    public void print() {
        for(Series column: columns) {
            System.out.print(column.name + '\t');
        }
        System.out.println();

        int numOfRows = columns.get(0).values.size();
        for (int i = 0; i < numOfRows; i++) {
            for (Series column: columns) {
                System.out.print(column.values.get(i).toString() + '\t');
            }
            System.out.println();
        }
    }
}
