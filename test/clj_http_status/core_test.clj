(ns clj-http-status.core-test
  (:require [clojure.test :refer :all]
            [clj-http-status.core :as s]))

(def status-types
  [:info
   s/HTTP_CONTINUE
   s/HTTP_SWITCHING_PROTOCOLS
   s/HTTP_PROCESSING

   :success
   s/HTTP_OK
   s/HTTP_CREATED
   s/HTTP_ACCEPTED
   s/HTTP_NON_AUTHORITATIVE_INFORMATION
   s/HTTP_NO_CONTENT
   s/HTTP_RESET_CONTENT
   s/HTTP_PARTIAL_CONTENT
   s/HTTP_MULTI_STATUS
   s/HTTP_ALREADY_REPORTED

   :redirect
   s/HTTP_MULTIPLE_CHOICES
   s/HTTP_MOVED_PERMANENTLY
   s/HTTP_FOUND
   s/HTTP_SEE_OTHER
   s/HTTP_NOT_MODIFIED
   s/HTTP_USE_PROXY
   s/HTTP_TEMPORARY_REDIRECT

   :client-error
   s/HTTP_BAD_REQUEST
   s/HTTP_UNAUTHORIZED
   s/HTTP_PAYMENT_REQUIRED
   s/HTTP_FORBIDDEN
   s/HTTP_NOT_FOUND
   s/HTTP_METHOD_NOT_ALLOWED
   s/HTTP_NOT_ACCEPTABLE
   s/HTTP_PROXY_AUTHENTICATION_REQUIRED
   s/HTTP_REQUEST_TIMEOUT
   s/HTTP_CONFLICT
   s/HTTP_GONE
   s/HTTP_LENGTH_REQUIRED
   s/HTTP_PRECONDITION_FAILED
   s/HTTP_REQUEST_ENTITY_TOO_LARGE
   s/HTTP_REQUEST_URI_TOO_LARGE
   s/HTTP_UNSUPPORTED_MEDIA_TYPE
   s/HTTP_REQUEST_RANGE_NOT_SATISFIABLE
   s/HTTP_EXPECTATION_FAILED
   s/HTTP_I_AM_A_TEAPOT
   s/HTTP_UNPROCESSABLE_ENTITY
   s/HTTP_LOCKED
   s/HTTP_FAILED_DEPENDENCY
   s/HTTP_NO_CODE
   s/HTTP_UPGRADE_REQUIRED
   s/HTTP_PRECONDITION_REQUIRED
   s/HTTP_TOO_MANY_REQUESTS
   s/HTTP_REQUEST_HEADER_FIELDS_TOO_LARGE
   s/HTTP_RETRY_WITH

   :server-error
   s/HTTP_INTERNAL_SERVER_ERROR
   s/HTTP_NOT_IMPLEMENTED
   s/HTTP_BAD_GATEWAY
   s/HTTP_SERVICE_UNAVAILABLE
   s/HTTP_GATEWAY_TIMEOUT
   s/HTTP_HTTP_VERSION_NOT_SUPPORTED
   s/HTTP_VARIANT_ALSO_NEGOTIATES
   s/HTTP_INSUFFICIENT_STORAGE
   s/HTTP_BANDWIDTH_LIMIT_EXCEEDED
   s/HTTP_NOT_EXTENDED
   s/HTTP_NETWORK_AUTHENTICATION_REQUIRED])

(def status-types-map (into {} (map (juxt ffirst second)
                                    (partition 2 (partition-by keyword? status-types)))))

(deftest test-informational-codes
  (doseq [code (:info status-types-map)]
    (is (s/info? code))
    (is (not (s/success? code)))
    (is (not (s/redirect? code)))
    (is (not (s/error? code)))))

(deftest test-success-codes
  (doseq [code (:success status-types-map)]
    (is (s/success? code))
    (is (not (s/info? code)))
    (is (not (s/redirect? code)))
    (is (not (s/error? code)))))

(deftest test-redirect-codes
  (doseq [code (:redirect status-types-map)]
    (is (s/redirect? code))
    (is (not (s/info? code)))
    (is (not (s/success? code)))
    (is (not (s/error? code)))))

(deftest test-client-error-codes
  (doseq [code (:client-error status-types-map)]
    (is (s/client-error? code))
    (is (s/error? code))
    (is (not (s/info? code)))
    (is (not (s/redirect? code)))
    (is (not (s/server-error? code)))))

(deftest test-server-error-codes
  (doseq [code (:server-error status-types-map)]
    (is (s/server-error? code))
    (is (s/error? code))
    (is (not (s/info? code)))
    (is (not (s/redirect? code)))
    (is (not (s/client-error? code)))))
