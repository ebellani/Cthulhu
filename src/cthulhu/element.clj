(ns cthulhu.element
  "Handles elements, aka nodes."
  (:use [cthulhu.utils]
        [cthulhu.page]
        [cthulhu.client])
  (:import [com.gargoylesoftware.htmlunit Page]
           [com.gargoylesoftware.htmlunit.html HtmlElement]))

(defmacro do-with-element [element & body]
  `(when ~element
      (binding [*current-element* ~element]
         ~@body)))

(defmacro with-element* [page option path & body]
  "Binds *current-element* with the return value of fetching the page at
the url with the client for the body context. The option parameter is mapped to diverse ways to obtain an element. For now we have:

:all-with-xpath -> get a list of elements by xpath. Executes the body equally
for every element

:xpath -> executes the body only on the first element of the list obtained by the xpath.

:id -> executes the body for an element fetched by an id.
"
  `(case ~option
     :all-with-xpath (doseq [~'element (.getByXPath ~page ~path)]
                       (do-with-element ~'element ~@body))
     :xpath (do-with-element (.getFirstByXPath ~page ~path) ~@body)
     :id    (do-with-element (.getElementById  ~page ~path) ~@body)))

(defmacro with-element [option path & body]
  "Like the with-element* macro, but uses the *current-element*
instead of using one provided by the user. Should be used with the
with-page macro."
  `(with-element*
     *current-page*
     ~option
     ~path
     ~@body))
