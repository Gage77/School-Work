#lang racket
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

;; Helper function for poly_mull
(define (poly_mul_full a pb)
  (if (null? pb)
      '()
      (cons (* a (car pb)) (poly_mul_full a (cdr pb)))))

;; Multiply two polynomials
(define (poly_mul pa pb)
  (if (null? pa)
      pa
      (poly_add (poly_mul_full (car pa) pb) (cons 0 (poly_mul (cdr pa) pb)))))
          
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

;; Function to reverse list
(define (rl pa)
  (if (null? pa)
      '()
      (append (rl (cdr pa)) (list (car pa)))))

;; poly_mul helper
(define (poly_mod_tr pa pb)
  (if (< (length pa) (length pb))
      (rl pa)
      (poly_mod_tr (poly_sub (cdr pa) (poly_mul (list (/ (car pa) (car pb))) (cdr pb))) pb)))

;; Find modulus of two polynomials
(define (poly_mod pa pb)
  (poly_mod_tr (rl pa) (rl pb)))
  