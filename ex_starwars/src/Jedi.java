public class Jedi extends ForceUser {
    private String saberColor;

    public Jedi(String nom, int midiChloriens, String saberColor) {
        super(nom, midiChloriens, null);
        this.saberColor = saberColor;
    }

    public Jedi(String name, int midiChloriens, ForceUser master, String saberColor) {
        super(name, midiChloriens, master);
        this.saberColor = saberColor;
    }

    @Override
    public String saberColor() {
        return saberColor;
    }

    @Override
    public String toString() {
        return "Jedi " + super.toString();
    }
}
