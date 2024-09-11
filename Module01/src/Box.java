import fruits.Fruits;
import java.util.ArrayList;
import java.util.List;

public  class Box <F extends Fruits> {
    private float quantity = 0;
    private final List<F> content = new ArrayList<>();

    public void add(F fruit){
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

    public void transferTo(Box<F> box){
      this.content.forEach(box::add);
      this.content.clear();
      quantity = 0;
      System.out.println("Пересыпали!");
   }
}
