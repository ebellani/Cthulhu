(ns cthulhu.test.page
  (:use cthulhu.page :reload-all)
  (:use cthulhu.client :reload-all)
  (:use cthulhu.utils :reload-all)
  (:use cthulhu.test-utils :reload-all)
  (:use clojure.test)
  (:import [com.gargoylesoftware.htmlunit Page]
           [com.gargoylesoftware.htmlunit.html HtmlElement]))


(deftest page-macro-test
  (with-client :ff3.6
    (with-page test-url
      (is (= (class (.asText *current-page*))
             String))))
  (is (nil? *current-page*)))

