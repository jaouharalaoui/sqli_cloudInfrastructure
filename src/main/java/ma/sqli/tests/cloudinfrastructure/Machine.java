package ma.sqli.tests.cloudinfrastructure;

public class Machine {
    private String name, operatingSystem, diskSize, memory;


    private MachineState state = MachineState.inactive;

    public Machine(String name, String operatingSystem, String diskSize, String memory) {
        this.name = name;
        this.operatingSystem = operatingSystem;
        this.diskSize = diskSize;
        this.memory = memory;
    }

    public MachineState getState() {
        return state;
    }

    public void setState(MachineState state) {
        this.state = state;
    }

    public double getDiskSizeValue(){
        return Double.valueOf(diskSize.substring(0,diskSize.length()-2));
    }

    public double getMemoryValue(){
        return Double.valueOf(memory.substring(0,memory.length()-2));
    }
}
