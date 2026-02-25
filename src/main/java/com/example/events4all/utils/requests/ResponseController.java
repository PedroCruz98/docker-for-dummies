package com.example.events4all.utils.requests;

import org.springframework.http.ResponseEntity;

public interface ResponseController <T, U extends Response<T>> {
    ResponseEntity<U> call();
}
