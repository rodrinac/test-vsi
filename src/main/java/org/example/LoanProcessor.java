package org.example;

// We make use of the library through this interface
public interface LoanProcessor {

    boolean processLoan(final User user, final double amount);
}
