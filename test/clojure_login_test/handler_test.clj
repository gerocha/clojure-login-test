(ns clojure-login-test.handler-test
  (:require [cheshire.core :as cheshire]
            [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [clojure-login-test.handler :refer :all]))

(deftest test-app
  (testing "main route"
    (let [response (app (mock/request :get "/"))]
      (is (= (:status response) 200))
      (is (= (:body response) "Hello World"))))

  (testing "not-found route"
    (let [response (app (mock/request :get "/invalid"))]
      (is (= (:status response) 404))))

  (testing "should add user"
    (let [user {:email "teste@teste.com"
                :username "batman"
                :password "coringa"}

          response (app (-> (mock/request :post "/api/v1/user")
                            (mock/content-type "application/json")
                            (mock/body (cheshire/generate-string user))))]
      (is (= (:status response) 201)))))
