public class Item {
    protected String name;
    private String pronoun = "";

    public Item(String name) {
        this.name = name;
    }

    public Item(String pronoun, String name) {
        this.pronoun = pronoun;
        this.name = name;
    }

    @Override
    public String toString() {
        if (!pronoun.equals("")){
            return String.format("%s %s", pronoun, name);
        }
        return name;
    }
}
