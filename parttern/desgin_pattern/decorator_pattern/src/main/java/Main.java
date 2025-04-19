public class Main {
    public static void main(String[] args) {
        Pizza plainPizza = new PlainPizza();

        System.out.println( plainPizza.getDescription());
        System.out.println(plainPizza.getCost());

//        plainPizza = new TrufflePizzaDecorator(plainPizza);
        plainPizza = new CheesePizzaDecorator(plainPizza);

        System.out.println(plainPizza.getDescription());
        System.out.println(plainPizza.getCost());

    }
}
