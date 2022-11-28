package com.technikon.eagency.exceptions;

public class RepairExceptionCodes {

    public final static String REPAIR_IS_NULL = "The repair is null.";
    public final static String REPAIR_MISSING_DATA = "Not all data are given to create or update a repair.";
    public final static String REPAIR_INVALID_DATA = "The given data are not appropriate to create a repair.";
    public final static String REPAIR_INVALID_DATE_OF_START = "The given date of start is invalid (after submission date).";
    public final static String REPAIR_INVALID_DATE_OF_END = "The given date of end is invalid (after submission date).";
}
