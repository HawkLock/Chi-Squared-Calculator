import java.io.*;

public class Serializer{

    public Serializer() {

    }

    public Table2D DeserializeTable2D(String fileName) throws IOException, ClassNotFoundException {
        fileName = "src/SavedData/Tables2D/"+ fileName + ".ser";
        try {
            System.out.println(fileName);
            FileInputStream fInStream = new FileInputStream(fileName);
            ObjectInputStream objStream = new ObjectInputStream(fInStream);
            Table2D obj = (Table2D) objStream.readObject();

            fInStream.close();
            return obj;

        } catch (Exception e) {
            return null;
        }

    }

    public void SerializeTable2D(Table2D obj, String fileName) throws IOException {
        try {
            fileName = "src/SavedData/Tables2D/"+ fileName + ".ser";
            FileOutputStream fOutStream = new FileOutputStream(fileName);
            ObjectOutputStream objStream = new ObjectOutputStream(fOutStream);
            objStream.writeObject(obj);

            fOutStream.close();
        } catch(Exception e) {
            return;
        }
    }

}
