(ns cthulhu.client
  "web client interfacing.
   WARNING: a WebClient instance is not thread safe."
  (:use [cthulhu.utils :only [*current-client*]])
  (:import (com.gargoylesoftware.htmlunit WebClient
                                          BrowserVersion)))

(def browsers
  {:ie7   BrowserVersion/INTERNET_EXPLORER_7
   :ie8   BrowserVersion/INTERNET_EXPLORER_8
   :ff3.6 BrowserVersion/FIREFOX_3_6})

(defn make-client
  "Creates a web client from one of the symbols in *browsers*"
  ([] (make-client nil))
  ([version]
     (let [browser-version (browsers version)]
       (if browser-version
         (WebClient. browser-version)
         (WebClient.)))))

(defn get-page
  "Access the page by an URL."
  ([url] (get-page (make-client) url))
  ([client url]
     (let [page (.getPage client url)]
       (.waitForBackgroundJavaScript client 10000)
       page)))

     ;; (.closeAllWindows ~client)

(defmacro with-client [version & body]
    "Binds *current-client* for the body context. It will close all
windows of the client afterwards to avoid memory leaks, according to
http://htmlunit.sourceforge.net/faq.html#MemoryLeak."
    `(binding [*current-client* (make-client ~version)]
       ~@body
       (.closeAllWindows *current-client*)))
