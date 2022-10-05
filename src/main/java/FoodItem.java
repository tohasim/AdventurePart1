public class FoodItem extends Item {
    private int hpAdd;

    public FoodItem(String name, int hpAdd) {
        super(name);
        this.hpAdd = hpAdd;
    }

    public FoodItem(String pronoun, String name, int hpAdd) {
        super(pronoun, name);
        this.hpAdd = hpAdd;
    }

    public int getHpAdd() {
        return hpAdd;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (restores %d HP)", hpAdd);
    }
}
