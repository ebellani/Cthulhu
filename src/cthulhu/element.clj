(ns cthulhu.element
  "Handles elements, aka nodes."
  (:use [cthulhu.utils]
        [cthulhu.page]
        [cthulhu.client]
        [clojure.reflect :as r])
  (:import [com.gargoylesoftware.htmlunit Page]
           [com.gargoylesoftware.htmlunit.html HtmlElement]))

(defmacro do-with-element [element & body]
  `(when ~element
      (binding [*current-element* ~element]
         ~@body)))

(defmacro with-element* [page option path & body]
  "Binds *current-element* with the return value of fetching the page at
the url with the client for the body context. The option parameter is mapped to diverse ways to obtain an element. For now we have:

:xpath -> get a list of elements by xpath. Executes the body equally
for every element

:first-xpath -> executes the body only on the first element of the list obtained by the xpath.

:by-id -> executes the body for an element fetched by an id.
"
  `(case ~option
     :xpath (doseq [el ~(.getByXPath page)]
              (do-with-element el ~@body))
     ;; :first-xpath ~(do-with-element (.getFirstByXPath page) body)
     ;; :by-id ~(do-with-element (.getElementById page) body)
     ))

(defmacro with-element [option path & body]
  "Like the with-element* macro, but uses the *current-element*
instead of using one provided by the user. Should be used with the
with-page macro."
  `(with-element*
     *current-page*
     ~option
     ~path
     ~@body))

;; (with-client :ff3.6
;;     (with-page "http://en.wikipedia.org/wiki/Cthulhu"
;;       ;; (with-element :xpath "Publication_history"
;;       ;;   (println *current-element*))
;;       (:members (r/reflect *current-element*))))