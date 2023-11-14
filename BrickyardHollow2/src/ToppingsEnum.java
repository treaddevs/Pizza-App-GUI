package src;
public class ToppingsEnum {
    // Toppings enum class for toppings with relative price based on selected size
    public enum Toppings {
        APPLE("Apples", 1.75, 3.00),
        BACON("Bacon", 1.75, 3.00),
        BANANA_PEPPERS("Banana Peppers", 1.75, 3.00),
        BASIL("Basil", 1.75, 3.00),
        BBQ_SAUCE("BBQ Sauce", 1.75, 3.00),
        BLUEBERRIES("Blueberries", 1.75, 3.00),
        BROCCOLI("Broccoli", 1.75, 3.00),
        BUFFALO_SAUCE("Buffalo Sauce", 1.75, 3.00),
        CAPICOLA("Capicola", 1.75, 3.00),
        CHERRY_TOMATO("Cherry Tomatoes", 1.75, 3.00),
        CHICKEN("Chicken", 1.75, 3.00),
        CORNBREAD("Cornbread", 1.75, 3.00),
        CREAM_SAUCE("Cream Sauce", 1.75, 3.00),
        DONAIR_SAUCE("Donair Sauce", 1.75, 3.00),
        DRIZZLE_SAUCE("Drizzle Sauce", 1.75, 3.00),
        EXTRA_CHEESE("Extra Cheese", 1.75, 3.00),
        FETA_CHEESE("Feta Cheese", 1.75, 3.00),
        GARLIC("Roasted Garlic", 1.75, 3.00),
        GOAT_CHEESE("Goat Cheese", 1.75, 3.00),
        GORGONZOLA_CHEESE("Gorgonzola Cheese", 1.75, 3.00),
        GROUND_BEEF("Ground Beef", 1.75, 3.00),
        HONEY("Honey", 1.75, 3.00),
        HOT_HONEY("Hot Honey", 1.75, 3.00),
        JALEPENO("Jalapenos", 1.75, 3.00),
        LEMON_ZEST("Lemon Zest", 1.75, 3.00),
        MAC_N_CHEESE("Mac 'n' Cheese", 1.75, 3.00),
        MASHED_POTATO("Mashed Potato", 1.75, 3.00),
        MUSHROOM("Mushroom", 1.75, 3.00),
        PARMESAN_CHEESE("Parmesan Cheese", 1.75, 3.00),
        PEACHES("Peaches", 1.75, 3.00),
        PEPPERONI("Pepperoni", 1.75, 3.00),
        PESTO("Pesto", 1.75, 3.00),
        PINEAPPLE("Pineapple", 1.75, 3.00),
        PULLED_PORK("Pulled Pork", 1.75, 3.00),
        RED_ONIONS("Red Onion", 1.75, 3.00),
        RED_PEPPERS("Red Pepper", 1.75, 3.00),
        RICOTTA_CHEESE("Ricotta Cheese", 1.75, 3.00),
        SAUSAGE("Sausage", 1.75, 3.00),
        SCA_SAUCE("Sweet Chili Sauce", 1.75, 3.00),
        SPINACH("Spinach", 1.75, 3.00),
        STRAWBERRIES("Strawberries", 1.75, 3.00),
        SWEET_POTATO("Sweet Potato", 1.75, 3.00),
        TOMATOES("Tomatoes", 1.75, 3.00);
        private final String name;
        private final double smallPrice;
        private final double largePrice;

        Toppings(String name, double smallPrice, double largePrice) {
            this.name = name;
            this.smallPrice = smallPrice;
            this.largePrice = largePrice;
        }

        public String getName() {
            return name;
        }
        public double getSmallPrice() {
            return smallPrice;
        }
        public double getLargePrice() {
            return largePrice;
        }
    }
}