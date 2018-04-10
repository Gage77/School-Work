#lang racket
;; Add to polynomials
(define (poly_add pa pb)
  (if (null? pa)
      pb
      (if (null? pb)
          pa
          (cons (+ (car pa) (car pb))
               (poly_add (cdr pa) (cdr pb))))))

;; Subtract pb from pa
(define (poly_sub pa pb)
  (if (null? pa)
      pb
      (if (null? pb)
          pa
          (cons (- (car pa) (car pb))
               (poly_sub (cdr pa) (cdr pb))))))

;; Multiply two polynomials
(define (poly_mul pa pb)
  (if (null? pa)
      pb
      (if (null? pb)
          pa
          (cons (* (car pa) (car pb))
                (poly_mul (cdr pa) (cdr pb))))))

;; Find the derivative of a given polynomail
(define (poly_der_full pa incr lng)
  (if (= lng incr)
      pa
      (cons (* (car pa) incr)
            (poly_der_full (cdr pa) (+ incr 1) lng)))) 

;; Check for nulls, then find call real der function
(define (poly_der pa)
  (if (null? pa)
      pa
      (poly_der_full pa 0 (length pa))))

;; Divide two polynomials (pa / pb)
(define (poly_div_full pa pb la lb)
  (if (< la lb)
      pa
      (cons (

;; Check for nulls, then run real div function
(define (poly_div pa pb)
  (if (null? pa)
      pb
      (if (null? pb)
          pb
          (poly_div_full pa pb (length pa) (length pb)))))

;; Find the remainder of the long division of pa by pb by
;; substracting pa by the result of the long division of pa by pb
(define (poly_mod_full pa pb la lb)
  (cons (poly_sub pa (poly_div pa pb))))

;; Check for nulls, then call real mod function
(define (poly_mod pa pb)
  (if (null? pa)
      pb
      (if (null? pb)
          pa
          (poly_mod_full pa pb (length pa) (length pb)))))



  