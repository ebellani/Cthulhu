(ns cthulhu.test.client
  (:use cthulhu.client :reload-all)
  (:use cthulhu.utils :reload-all)
  (:use clojure.test))


(deftest client-macro-test
  (with-client :ff3.6
    (is (= (class *current-client*)
           com.gargoylesoftware.htmlunit.WebClient)))
  (is (nil? *current-client*)))

