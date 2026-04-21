package com.www.skeleton.util.exception;

/*
 * root of exception
 * application all exceptions must extend the exception
 */
public class BaseException extends RuntimeException{

	public BaseException() {
        super();
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
