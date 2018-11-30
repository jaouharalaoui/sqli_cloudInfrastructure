package ma.sqli.tests.cloudinfrastructure;

import java.util.LinkedList;
import java.util.stream.Collectors;

public class Store {
    private String name;
    private LinkedList<String> files;
    private double storeUsedDisk;

    public Store(String name){
        this.name = name;
        files = new LinkedList<>();
        this.storeUsedDisk =0;
    }

    public void addFile(String file){
        this.files.add(file);
        storeUsedDisk+=0.1;
    }

    public String listFiles(){
        return (files.isEmpty())?"empty":String.join(", ", files);
    }

    public double getStoreMemory() {
        return storeUsedDisk;
    }
}
