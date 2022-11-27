package com.technikon.eagency;

import com.technikon.eagency.util.UseCasesUtil;

public class Technikon {

    public static void main(String[] args) {

        UseCasesUtil.dataPopulation();
        UseCasesUtil.ownerRegistrationWithTwoProperties();
        UseCasesUtil.repairRegistration();
        UseCasesUtil.repairAdministration();
        UseCasesUtil.reports();

    }
}
