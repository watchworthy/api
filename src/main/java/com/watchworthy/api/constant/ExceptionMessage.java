package com.watchworthy.api.constant;

import lombok.Getter;

@Getter
public class ExceptionMessage {
    public static final String EMPTY_VALUE_EXCEPTION_MESSAGE = "VALUE SHOULD NOT BE EMPTY.";
    public static final String PASSWORD_NOT_MATCH_MESSAGE = "PASSWORD DOES NOT MATCH.";
    public static final String USER_ALREADY_EXIST_MESSAGE = "USER ALREADY EXISTS.";
    public static final String USER_NOT_EXIST_MESSAGE = "USER DOES NOT EXIST.";
    public static final String LOG_IN_FAIL_MESSAGE = "LOG IN FAILED.";
    public static final String INVALID_REQUEST_ERROR_MESSAGE = "Invalid request.";
    public static final String INTERNAL_SERVER_ERROR_MESSAGE = "Unexpected internal server error.";
    public static final String COMPANY_NOT_FOUND_MESSAGE = "COMPANY DOES NOT EXIST.";
    public static final String UNAUTHORIZED_ERROR_MESSAGE = "UNAUTHORIZED.";
    public static final String MOVIE_NOT_FOUND_MESSAGE = "THE REQUESTED MOVIE WAS NOT FOUND.";

}
