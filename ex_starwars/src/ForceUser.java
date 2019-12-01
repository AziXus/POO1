public class ForceUser {
    private String name;
    private int midiChloriens;
    private ForceUser master;

    public ForceUser(String name, int midiChloriens, ForceUser master) {
        this.name = name;
        this.midiChloriens = midiChloriens;
        this.master = master;
    }

    public ForceUser(String name, ForceUser forceUser, ForceUser master) {
        this(name, forceUser.midiChloriens, master);
    }

    String getName() {
        return name;
    }

    public int getMidiChloriens() {
        return midiChloriens;
    }

    public String saberColor() {
        return "n/a";
    }

    @Override
    public String toString() {
        if (master != null)
            return String.format("%s, master: %s, %d mc, saber: %s", this.name, this.master.getName(), this.midiChloriens, this.saberColor());
        else
            return String.format("%s, %d mc, saber: %s", this.name, this.midiChloriens, this.saberColor());
    }
}
