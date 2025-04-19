public class TrufflePizzaDecorator extends PizzaDecorator {
    public TrufflePizzaDecorator(Pizza pizza) {
        super(pizza);
    }


    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Truffle mushroom";
    }

    @Override
    public Double getCost() {
        return pizza.getCost() + 15.0;
    }
}
