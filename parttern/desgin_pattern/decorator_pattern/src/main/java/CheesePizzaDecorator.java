public class CheesePizzaDecorator extends PizzaDecorator{
    public CheesePizzaDecorator(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", cheese";
    }

    @Override
    public Double getCost() {
        return pizza.getCost() + 1.0;
    }
}
