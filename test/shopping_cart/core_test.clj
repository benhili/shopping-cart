(ns shopping-cart.core-test
  (:require [clojure.test :refer :all]
            [shopping-cart.core :refer :all]))

(deftest creating-cart
  (let [cart (create-cart)]
    (testing "cart is empty")
    (is (contains? cart :items))
    (is (= 0 (-> cart :items count)))))

(deftest adding-to-cart
  (let [item {:id 1 :name "Dove Soap" :price 39.99M}
        cart (add-to-cart (create-cart) item)]

    (testing "cart contains item"
      (is (contains? (cart :items) (item :id))))

    (testing "quantity was updated to 1"
      (is (= 1 (get-in cart [:items (item :id) :quantity]))))

    (testing "cart total was updated"
      (is (= (item :price) (cart :total))))))

(deftest adding-multiple-to-cart
  (let [item {:id 1 :name "Dove Soap" :price 39.99M}
        cart (add-multiple (create-cart) item 5)]

    (testing "cart contains item"
      (is (contains? (cart :items) (item :id))))

    (testing "quantity was updated to 5"
      (is (= 5 (get-in cart [:items (item :id) :quantity]))))

    (testing "cart total was updated and rounded"
      (is (= 199.95M (cart :total))))

    (testing "adding 3 more items updates total"
      (let [updated-cart (add-multiple cart item 3)]
        (is (= 8 (get-in updated-cart [:items (item :id) :quantity])))
        (is (= 319.92M (updated-cart :total)))))))

(deftest calculates-sales-tax
  (let [dove-soap {:id 1 :name "Dove Soap" :price 39.99M}
        axe-deo {:id 15 :name "Axe Deo" :price 99.99M}
        cart (-> (create-cart)
                 (add-multiple axe-deo 2)
                 (add-multiple dove-soap 2))]

    (testing "total includes sales tax for cart with multiple items"
      (is (= 279.96M (cart :total)))
      (is (= 35.00M (calculate-sales-tax cart)))
      (is (= 314.96M (total-inc-sales-tax cart))))))
