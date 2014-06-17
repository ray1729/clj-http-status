# clj-http-status

This library provides constants and funcitons for defining and
classifying HTTP status codes, which are used to encode the overall
outcome of an HTTP response message. Codes correspond to those defined
in [RFC 2616](http://www.ietf.org/rfc/rfc2616.txt) and [RFC 2518](http://www.ietf.org/rfc/rfc2518.txt).

It is a port of Perl's [HTTP::Status](http://search.cpan.org/perldoc?HTTP%3A%3AStatus).

## Usage

Install from Clojars:

    [clj-http-status "0.1.0"]
    
To use in your programs:

    (require '[clj-http-status :as s])
    
    (s/HTTP_OK)
    ;;=> 200
    
    (s/success? 200)
    ;;=> true
    
    (s/error? 200)
    ;;=> false

## License

Copyright © 2014 Ray Miller <ray@1729.org.uk>.

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
