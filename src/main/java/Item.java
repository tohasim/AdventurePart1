public class Item {
    private String name;
    private String pronoun = "";

    public Item(String name) {
        this.name = name;
    }

    public Item(String pronoun, String name) {
        this.pronoun = pronoun;
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public String getPronoun() {
        return pronoun;
    }

    @Override
    public String toString() {
        if (!pronoun.equals("")){
            return String.format("%s %s", pronoun, name);
        }
        return name;
    }
}
