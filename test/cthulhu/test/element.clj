(ns cthulhu.test.element
  (:use cthulhu.element :reload-all)
  (:use cthulhu.page :reload-all)
  (:use cthulhu.client :reload-all)
  (:use cthulhu.utils :reload-all)
  (:use cthulhu.test.utils :reload-all)
  (:use clojure.test clojure.walk)
  (:import [com.gargoylesoftware.htmlunit Page]
           [com.gargoylesoftware.htmlunit.html HtmlElement]))


(deftest with-element-test
  (with-client :ff3.6
    (with-page test-url
      (with-element :by-id "Publication_history"
        (println (.asText *current-element*))))))

;; (println (macroexpand '(with-client :ff3.6
;;                          (with-page test-url
;;                            (with-element :by-id "Publication_history"
;;                              (println (.asText *current-element*)))))))

(with-client :ff3.6
  (with-page test-url
    (with-element :by-id "Publication_history"
      (println (.asText *current-element*)))))
