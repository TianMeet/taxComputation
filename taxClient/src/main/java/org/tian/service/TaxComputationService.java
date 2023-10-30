package org.tian.service;

import javax.jws.WebService;

@WebService
public interface TaxComputationService {
    public double[] computationTax(double salary);
}
