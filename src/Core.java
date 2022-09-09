import java.io.IOException;
import java.util.Scanner;

public class Core {

    static Serializer serializer = new Serializer();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        RequestUserChoice();

    }

    // Asks the user for what problem they want to save
    static void RequestUserChoice() throws IOException, ClassNotFoundException {
        /*
        Choices:
        1. Edit Table 1D (table id) Later implementation
        2. Edit Table 2D (table id)
        3. Calculate Chi Square (2D table)
        3. Calculate Standard Error of the Mean (2D table)

        // Commands are all 3 characters with a colon and input variables after
         */

        System.out.println("Options: ");
        System.out.println("1. Edit Table 2D (E2D:tableName) ");
        System.out.println("2. View Table 2D (V2D:tableName ");
        System.out.println("3. Calculate Chi Square (CCS:tableName) ");
        System.out.println("4. Calculate SEM (SEM:tableName) ");
        System.out.println("Enter Command:");
        String userChoice = scanner.nextLine();

        String tableChoice = userChoice.substring(FindColonIndex(userChoice)+1);
        if (userChoice.contains("E2D")) {
            EditTable2D(tableChoice);
        }
        else if (userChoice.contains("V2D")) {
            ViewTable(tableChoice);
        }
        else if (userChoice.contains("CCS")) {
            FindChiSquare(tableChoice);
        }
        else if (userChoice.contains("SEM")) {
            FindSEM(tableChoice);
        }

    }

    static int FindColonIndex(String str) {
        for (int i = 0; i < str.length()-1; i++) {
            if (str.charAt(i) == ':') {
                return i;
            }
        }
        return -1;
    }

    static void EditTable2D(String tableName) throws IOException, ClassNotFoundException {
        Table2D tab = serializer.DeserializeTable2D(tableName);
        if (tab == null) {
            System.out.println("Error: Table name invalid");
            System.out.println("Please enter width for a new table");
            int colCount = scanner.nextInt();
            tab = new Table2D(colCount, tableName);
        }

        // Edit table values
        for (int row = 0; row < tab.values.length; row++) {
            for (int col = 0; col < tab.values[0].length; col++) {
                System.out.printf("New Value for %s : %d\n", tab.rowNames[row], col);
                float newVal = scanner.nextFloat();
                tab.SetValue(row, col, newVal);
            }
            System.out.println("\n\n");
        }
        System.out.println("\n\n");

        serializer.SerializeTable2D(tab, tab.tableName);

        for (float[] row : tab.values) {
            for (float val : row) {
                System.out.printf("%f ", val);
            }
            System.out.println();
        }
    }

    static void FindChiSquare(String tableName) throws IOException, ClassNotFoundException {
        Table2D table = serializer.DeserializeTable2D(tableName);
        CalculateChiSquare(table);
    }

    static float CalculateChiSquare(Table2D table){
        float sum = 0;
        for (int col = 0; col < table.values[0].length; col++) {
            float observed = table.values[0][col];
            float expected = table.values[1][col];
            sum += Math.pow(observed-expected, 2) / expected;
        }
        System.out.printf("The Chi^2 is %f\n",sum);
        return sum;
    }

    static float FindSEM(String tableName) throws IOException, ClassNotFoundException {
        Table2D table = serializer.DeserializeTable2D(tableName);
        float SEM = (float) (CalculateChiSquare(table) / Math.sqrt(table.values[0].length));
        System.out.printf("The SEM is %f", SEM);
        return SEM;
    }

    static void ViewTable(String tableName) throws IOException, ClassNotFoundException {
        Table2D table = serializer.DeserializeTable2D(tableName);
        for (float[] row : table.values) {
            for (float val : row) {
                System.out.printf("%f ", val);
            }
            System.out.println();
        }
    }

}
