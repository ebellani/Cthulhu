(ns cthulhu.utils
  "Global dynamic vars, and random utilities"
  (:use [clojure.reflect]
        [clojure.set]
        [clojure.pprint]))

(def
  #^{:dynamic true
     :doc "Used as the default page for a given body. See the
  with-page macro."}  *current-page* nil)

(def
  #^{:dynamic true
     :doc "Used as the default client for a given body. See the
  with-client macro."}  *current-client* nil)


(def
  #^{:dynamic true
     :doc "Used as the default element for a given body. See the
  with-element macro."}  *current-element* nil)


(defn print-methods-table [obj]
  "pprint all methods, parameter, and return types of a object,
sorted by method name. Recur on the bases up to Object"
  (let [columns [:name :parameter-types :return-type]
        reflection (reflect obj)]
    (print-table columns
                 (sort-by :name (project (:members reflection) columns)))
    (println (format "Bases --> %s%nAbove table for class --> %s"
                     (:bases reflection) obj))))
