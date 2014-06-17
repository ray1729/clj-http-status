(ns clj-http-status.core)

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
