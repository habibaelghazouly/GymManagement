package backend;

import java.io.*;
import java.util.*;

public abstract class Database<T extends Data> {

    protected ArrayList<T> records;
    protected String fileName;

    public Database(String fileName) {
        this.fileName = fileName;
        this.records = new ArrayList<>();
    }

    public void readFromFile() {
        File f = new File(fileName);
        try {
            if (f.createNewFile()) {
                System.out.println("File Created!");
            } else {
                System.out.println("File Exists!");
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    T record = createRecordFrom(line);
                    insertRecord(record);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<T> returnAllRecords() {
        return records;
    }

    public boolean contains(String key) {

        return getRecord(key) != null;
    }

    public T getRecord(String key) {
        for (T record : records) {
            if (key.equals(record.getSearchKey())) {
                return record;
            }
        }
        return null;
    }

    public void insertRecord(T record) {

        if (!contains(record.getSearchKey())) {

            records.add(record);
            System.out.println("Added successfully");

        } else {
            System.out.println("Already exists !!");
        }
    }

    public void deleteRecord(String key) {
        if (contains(key)) {
            records.remove(getRecord(key));
            System.out.println("Deleted successfully");
        } else {
            System.out.println("Not found !!");
        }
    }

    public void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (T record : records) {
                String line = record.lineRepresentation();
                writer.write(line);
                writer.write("\n");
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public abstract T createRecordFrom(String line);

}
