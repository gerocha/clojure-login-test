(ns clojure-login-test.handler-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [clojure-login-test.handler :refer :all]))

(deftest test-app
  (testing "main route"
    (let [response (app (mock/request :get "/"))]
      (is (= (:status response) 200))
      (is (= (:body response) "Hello World"))))

  (testing "not-found route"
    (let [response (app (mock/request :get "/invalid"))]
      (is (= (:status response) 404)))))

  (testing "create user"
    (let [response (app (mock/request :post "/app/v1/user"))]
      (is (= (:status response) 404)))))
