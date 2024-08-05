import fruits.Fruits;
import java.util.ArrayList;

public  class Box <T extends Fruits> {
    private float quantity = 0;
    private final ArrayList<T> content = new ArrayList<>();

    public void add(T fruit){
        content.add(fruit);
        quantity++;
    }

    public float getWeight(){
        float w = 0;
        if (quantity>0) {
            w = this.quantity * content.get(0).getWeightOfFruit();
        }
        return w;
    }

    public boolean compare (Box<? extends Fruits> box){
        return this.getWeight() == box.getWeight();
    }

    public void transferTo(Box<T> box){
        for ( T e : this.content) {
            box.add(e);
        }
        this.content.clear();
        quantity = 0;
        System.out.println("Пересыпали!");
   }
}
