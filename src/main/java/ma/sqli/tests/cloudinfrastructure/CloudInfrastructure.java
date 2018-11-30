package ma.sqli.tests.cloudinfrastructure;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class CloudInfrastructure {
    //linked hash map to preserve the order
    private LinkedHashMap<String,Store> stores = new LinkedHashMap<>();
    private LinkedHashMap<String,Machine> machines = new LinkedHashMap<>();


    public void createStore(String storeName) throws CreateStoreException{
        if(!this.stores.keySet().contains(storeName))
            this.stores.put(storeName,new Store(storeName));
        else throw new CreateStoreException();
    }

    public void uploadDocument(String storeName,String... files){
        for(String file : files){
            this.stores.get(storeName).addFile(file);
        }
    }

    public String listStores(){
        String listStores;
        listStores = "";
        for (String key: this.stores.keySet()) {
            listStores +=key +":"+this.stores.get(key).listFiles()+"||";
        }
        return listStores.substring(0,listStores.length()-2);
    }

    public void deleteStore(String storeName){
        this.stores.remove(storeName);
    }

    public void emptyStore(String storeName){
        this.stores.replace(storeName,new Store(storeName));
    }

    public void createMachine(String name, String operatingSystem, String diskSize, String memory) {
        this.machines.put(name,new Machine(name,operatingSystem,diskSize,memory));
    }

    public String listMachines(){
        String mListMachines = "";
        for (String key:this.machines.keySet()){
            mListMachines+=key+":"+this.machines.get(key).getState()+"||";
        }
        return mListMachines.substring(0,mListMachines.length()-2);
    }

    public void startMachine(String machineName) throws MachineStateException{
        if(this.machines.get(machineName).getState() != MachineState.running)
            this.machines.get(machineName).setState(MachineState.running);
        else throw new MachineStateException();
    }

    public void stopMachine(String machineName){
        this.machines.get(machineName).setState(MachineState.stopped);
    }

    public double usedMemory(String machineName){
        Machine machine =this.machines.get(machineName);

        return (machine.getState().equals(MachineState.running)) ? this.machines.get(machineName).getMemoryValue() : 0;
    }

    public double machineUsedDisk(String machineName){
        return this.machines.get(machineName).getDiskSizeValue();
    }

    public double storeUsedDisk(String storeName){
        return this.stores.get(storeName).getStoreMemory();
    }

    public double usedDisk(String key){
        return (this.stores.keySet().contains(key))?storeUsedDisk(key):machineUsedDisk(key);
    }

    public double globalUsedDisk(){
        double usedDisk=0;
        for(String key:this.stores.keySet())
            usedDisk+=storeUsedDisk(key);
        for(String key:this.machines.keySet())
            usedDisk+=machineUsedDisk(key);
        return usedDisk;
    }

    public double globalUsedMemory(){
        double gUsedMemory=0;
        for (String key:this.machines.keySet())
            gUsedMemory+=usedMemory(key);
        return gUsedMemory;
    }
}
