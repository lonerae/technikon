package com.technikon.eagency.services;

public interface IOService {

    /**
     * Saves all owners of Owner database to a CSV file with the given name. If
     * file cannot be created, logs an appropriate message.
     *
     * @param filename
     */
    void saveOwnerToCsv(String filename);

    /**
     * Saves all properties of Property database to a CSV file with the given
     * name. If file cannot be created, logs an appropriate message.
     *
     * @param filename
     */
    void savePropertyToCsv(String filename);

    /**
     * Saves all repairs of Repair database to a CSV file with the given name.
     * If file cannot be created, logs an appropriate message.
     *
     * @param filename
     */
    void saveRepairToCsv(String filename);

    /**
     * Inserts all owners saved in the CSV file with the given name, to the
     * database. If the CSV file cannot be found or its content cannot be
     * parsed, logs an appropriate message.
     *
     * @param filename
     * @return number of rows that were successfully read
     */
    int readOwnerFromCsv(String filename);

    /**
     * Inserts all properties saved in the CSV file with the given name, to the
     * database. If the CSV file cannot be found or its content cannot be
     * parsed, logs an appropriate message.
     *
     * @param filename
     * @return number of rows that were successfully read
     */
    int readPropertyFromCsv(String filename);

    /**
     * Inserts all repairs saved in the CSV file with the given name, to the
     * database. If the CSV file cannot be found or its content cannot be
     * parsed, logs an appropriate message.
     *
     * @param filename
     * @return number of rows that were successfully read
     */
    int readRepairFromCsv(String filename);

}
