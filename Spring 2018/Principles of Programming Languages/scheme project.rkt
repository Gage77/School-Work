#lang racket
(define (poly_add pa pb)
  (if (null? pa)
      pb
      (if (null? pb)
          pa
          (cons (+ (car pa) (car pb))
               (poly_add (cdr pa) (cdr pb))))))

(define (poly_sub pa pb)
  (if (null? pa)
      pb
      (if (null? pb)
          pa
          (cons (- (car pa) (car pb))
               (poly_sub (cdr pa) (cdr pb))))))

(define (poly_mul pa pb)
  (if (null? pa)
      pb
      (if (null? pb)
          pa
          (cons (* (car pa) (car pb))
                (poly_mul (cdr pa) (cdr pb))))))

(define (poly_der_full pa incr lng)
  (if (= lng incr)
      pa
      (cons (* (car pa) incr)
            (poly_der_full (cdr pa) (+ incr 1) lng)))) 

(define (poly_der pa)
  (if (null? pa)
      pa
      (poly_der_full pa 0 (length pa))))
