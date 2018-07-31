(ns clojure-login-test.handler
  (:require [clojurewerkz.neocons.rest :as nr]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(def conn (nr/connect "http://localhost:7474/db/data/"))

(defn create-node
  [node]
  (conn nn/create node))

(defroutes app-routes
  (GET "/" [] "Hello World")
  (POST "/api/v1/user" [username email password] (create-node {:username username
                                                               :email email
                                                               :password password}))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
