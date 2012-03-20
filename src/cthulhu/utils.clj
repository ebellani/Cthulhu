(ns cthulhu.utils
  "Global dynamic vars, and random utilities")

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
