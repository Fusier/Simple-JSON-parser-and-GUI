package fi.tuni.tamk.tiko.objectorientedprogramming;


 /**
 * Static Class holds products and amount of products.
 * <p>
 * Holds attributes for product and amount strings.
 * Also has methods for manipulating them.
 * </p>
 * @author Samu Willman
 * @version 4.0
 */

public class Item {

        private String product;
        private String amount;

         /**
         * Saves the given information in class Item.
         * @param product1 which is the name of the product.
          * @param amount1 which is the amount of the product.
         */
        public Item(String product, String amount) {
            this.product = product;
            this.amount = amount;
        }

        /**
        * Returns value of product.
        * @return product.
         */
        public String getProduct() {
            return product;
        }


         /**
         * Main class that creates the javafx program.
         * @return value of amount.
          */
        public String getAmount() {
        return amount;
        }

          /**
          *  Sets the given product
           * @param product1 is used to set product.
           */
        public void setProduct(String product) {
                this.product = product;

        }

         /**
         * Sets the given amount
         * @param amount1 is used to set amount.
          */
        public void setAmount(String amount) {
            this.amount = amount;
        }

        /**
        * ToString method which overrides default toString().
         * @return product and amount in one string
        */
        @Override
        public String toString() {

            return product +":"+ amount;
        }
}
