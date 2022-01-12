(ns shopping-cart.core (:gen-class))

;; Version number:
;; 72cf4fe47f85c39779267d0ecee07655a354e623 

(defn -main [] (print "TODO"))

(defn create-cart
  "Create an empty cart"
  []
  {:items {} :total 0M})

(defn update-quantity
  [cart item]
  (let [id (item :id)]
    (if (contains? (cart :items) id)
      (update-in cart [:items id :quantity] inc)
      (assoc-in cart [:items id] (assoc item :quantity 1)))))

(defn update-total [cart item]
  (let
   [current-total (cart :total)
    item-price (item :price)]
    (assoc-in cart [:total] (bigdec (format "%.2f" (+ item-price current-total))))))


(defn add-to-cart
  "Add item to cart"
  [cart item]
  (-> cart
      (update-quantity item)
      (update-total item)))

(defn calculate-sales-tax
  [cart]
  (bigdec (format "%.2f" (* 0.125 (cart :total)))))

(defn add-multiple
  [cart item n]
  (nth (iterate #(add-to-cart % item) cart) n))

(defn total-inc-sales-tax
  "Add sales tax to cart total"
  [cart]
  (+ (cart :total)
     (calculate-sales-tax cart)))