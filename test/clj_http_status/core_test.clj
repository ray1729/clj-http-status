(ns clj-http-status.core-test
  (:require [clojure.test :refer :all]
            [clj-http-status.constants :refer :all]
            [clj-http-status.core :as s]))

(def status-types
  [:info
   HTTP_CONTINUE
   HTTP_SWITCHING_PROTOCOLS
   HTTP_PROCESSING

   :success
   HTTP_OK
   HTTP_CREATED
   HTTP_ACCEPTED
   HTTP_NON_AUTHORITATIVE_INFORMATION
   HTTP_NO_CONTENT
   HTTP_RESET_CONTENT
   HTTP_PARTIAL_CONTENT
   HTTP_MULTI_STATUS
   HTTP_ALREADY_REPORTED

   :redirect
   HTTP_MULTIPLE_CHOICES
   HTTP_MOVED_PERMANENTLY
   HTTP_FOUND
   HTTP_SEE_OTHER
   HTTP_NOT_MODIFIED
   HTTP_USE_PROXY
   HTTP_TEMPORARY_REDIRECT

   :client-error
   HTTP_BAD_REQUEST
   HTTP_UNAUTHORIZED
   HTTP_PAYMENT_REQUIRED
   HTTP_FORBIDDEN
   HTTP_NOT_FOUND
   HTTP_METHOD_NOT_ALLOWED
   HTTP_NOT_ACCEPTABLE
   HTTP_PROXY_AUTHENTICATION_REQUIRED
   HTTP_REQUEST_TIMEOUT
   HTTP_CONFLICT
   HTTP_GONE
   HTTP_LENGTH_REQUIRED
   HTTP_PRECONDITION_FAILED
   HTTP_REQUEST_ENTITY_TOO_LARGE
   HTTP_REQUEST_URI_TOO_LARGE
   HTTP_UNSUPPORTED_MEDIA_TYPE
   HTTP_REQUEST_RANGE_NOT_SATISFIABLE
   HTTP_EXPECTATION_FAILED
   HTTP_I_AM_A_TEAPOT
   HTTP_UNPROCESSABLE_ENTITY
   HTTP_LOCKED
   HTTP_FAILED_DEPENDENCY
   HTTP_NO_CODE
   HTTP_UPGRADE_REQUIRED
   HTTP_PRECONDITION_REQUIRED
   HTTP_TOO_MANY_REQUESTS
   HTTP_REQUEST_HEADER_FIELDS_TOO_LARGE
   HTTP_RETRY_WITH

   :server-error
   HTTP_INTERNAL_SERVER_ERROR
   HTTP_NOT_IMPLEMENTED
   HTTP_BAD_GATEWAY
   HTTP_SERVICE_UNAVAILABLE
   HTTP_GATEWAY_TIMEOUT
   HTTP_HTTP_VERSION_NOT_SUPPORTED
   HTTP_VARIANT_ALSO_NEGOTIATES
   HTTP_INSUFFICIENT_STORAGE
   HTTP_BANDWIDTH_LIMIT_EXCEEDED
   HTTP_NOT_EXTENDED
   HTTP_NETWORK_AUTHENTICATION_REQUIRED])

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
