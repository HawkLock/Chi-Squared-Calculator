import java.io.Serializable;

public class Table2D implements Serializable {

    public String tableName;
    public float[][] values;
    public String[] rowNames;

    public Table2D(int columns, String objName) {
        values = new float[2][columns];
        rowNames = new String[]{"Observed", "Expected"};
        tableName = objName;
    }

    public void SetRowName(int nameIndex, String newName) {
        if (newName.equals("-1")) {
            return;
        }
        rowNames[nameIndex] = newName;
    }

    public void SetValue(int row, int column, float newValue) {
        values[row][column] = newValue;
    }

}
