(ns clj-http-status.core)

(defmacro ^:private def-status-codes [& codes]
  `(do ~@(map (fn [[code value]] `(def ~code ~value)) (partition 2 codes))))

(def-status-codes
   HTTP_CONTINUE                        100
   HTTP_SWITCHING_PROTOCOLS             101
   HTTP_PROCESSING                      102

   HTTP_OK                              200
   HTTP_CREATED                         201
   HTTP_ACCEPTED                        202
   HTTP_NON_AUTHORITATIVE_INFORMATION   203
   HTTP_NO_CONTENT                      204
   HTTP_RESET_CONTENT                   205
   HTTP_PARTIAL_CONTENT                 206
   HTTP_MULTI_STATUS                    207
   HTTP_ALREADY_REPORTED                208

   HTTP_MULTIPLE_CHOICES                300
   HTTP_MOVED_PERMANENTLY               301
   HTTP_FOUND                           302
   HTTP_SEE_OTHER                       303
   HTTP_NOT_MODIFIED                    304
   HTTP_USE_PROXY                       305
   HTTP_TEMPORARY_REDIRECT              307

   HTTP_BAD_REQUEST                     400
   HTTP_UNAUTHORIZED                    401
   HTTP_PAYMENT_REQUIRED                402
   HTTP_FORBIDDEN                       403
   HTTP_NOT_FOUND                       404
   HTTP_METHOD_NOT_ALLOWED              405
   HTTP_NOT_ACCEPTABLE                  406
   HTTP_PROXY_AUTHENTICATION_REQUIRED   407
   HTTP_REQUEST_TIMEOUT                 408
   HTTP_CONFLICT                        409
   HTTP_GONE                            410
   HTTP_LENGTH_REQUIRED                 411
   HTTP_PRECONDITION_FAILED             412
   HTTP_REQUEST_ENTITY_TOO_LARGE        413
   HTTP_REQUEST_URI_TOO_LARGE           414
   HTTP_UNSUPPORTED_MEDIA_TYPE          415
   HTTP_REQUEST_RANGE_NOT_SATISFIABLE   416
   HTTP_EXPECTATION_FAILED              417
   HTTP_I_AM_A_TEAPOT                   418
   HTTP_UNPROCESSABLE_ENTITY            422
   HTTP_LOCKED                          423
   HTTP_FAILED_DEPENDENCY               424
   HTTP_NO_CODE                         425
   HTTP_UPGRADE_REQUIRED                426
   HTTP_PRECONDITION_REQUIRED           428
   HTTP_TOO_MANY_REQUESTS               429
   HTTP_REQUEST_HEADER_FIELDS_TOO_LARGE 431
   HTTP_RETRY_WITH                      449

   HTTP_INTERNAL_SERVER_ERROR           500
   HTTP_NOT_IMPLEMENTED                 501
   HTTP_BAD_GATEWAY                     502
   HTTP_SERVICE_UNAVAILABLE             503
   HTTP_GATEWAY_TIMEOUT                 504
   HTTP_HTTP_VERSION_NOT_SUPPORTED      505
   HTTP_VARIANT_ALSO_NEGOTIATES         506
   HTTP_INSUFFICIENT_STORAGE            507
   HTTP_BANDWIDTH_LIMIT_EXCEEDED        509
   HTTP_NOT_EXTENDED                    510
   HTTP_NETWORK_AUTHENTICATION_REQUIRED 511)

(defn info?
  "Return true if `status-code` is an informational status code (1xx). This class of
  code indicates a provisional response that can't have any content."
  [status-code]
  (<= 100 status-code 199))

(defn success?
  "Return true if `status-code` is a successful status code (2xx)."
  [status-code]
  (<= 200 status-code 299))

(defn redirect?
  "Return true if `status-code` is a redirection status code (3xx).
  This class of status code indicates that further action needs to be
  taken by the user agent in order to fulfill the request."
  [status-code]
  (<= 300 status-code 399))

(defn error?
  "Return true if `status-code` is an error status code (4xx or 5xx). The function
   returns true for both client and server error status codes."
  [status-code]
  (<= 400 status-code 599))

(defn client-error?
  "Return true if `status-code` is a Client Error status code (4xx). This class of
   status code is intended for cases in which the client seems to have erred."
  [status-code]
  (<= 400 status-code 499))

(defn server-error?
  "Return true if `status-code` is a Server Error status code (5xx). This class
  of status codes is intended for cases in which the server is aware that
  it has erred or is incapable of performing the request."
  [status-code]
  (<= 500 status-code 599))
