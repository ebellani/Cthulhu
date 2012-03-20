(ns cthulhu.page
  "Handles pages. You can get the Page instance in the client
namespace to use it yourself or just use one of the auxiliary
functions here defined."
  (:use [cthulhu.utils]
        [cthulhu.client])  
  (:import [com.gargoylesoftware.htmlunit Page]))

(defmacro with-page* [client url & body]
  "Binds *current-page* with the return value of fetching the page at
the url with the client for the body context. Usually you would want
to use the with-page macro."
  `(binding [*current-page* (get-page ~client ~url)]
     ~@body))

(defmacro with-page [url & body]
  "Like the with-page* macro, but uses the *current-client*
instead of using one provided by the user. Should be used in
conjuction with the 'with-client' macro."
  `(with-page*
     *current-client*
     ~url
     ~@body))

