package com.ivan.softserve.ldm.constant;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ErrorMessage {
    public static final String LOG_FILES_NOT_FOUND = "No log files found";
    public static final String LOG_FILE_NOT_FOUND = "No file found with name: %s";
    public static final String CANNOT_READ_LOG_FILE = "Error reading log file: %s";
    public static final String BAD_SECRET_KEY = "The given secret key is incorrect";
    public static final String CANNOT_DELETE_DOTENV = "Failed to delete .env file";
    public static final String FUNCTIONALITY_NOT_AVAILABLE = "Functionality is not available";
}
