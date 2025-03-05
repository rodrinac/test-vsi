package org.example;

public class ThirdPartyBankAdapter implements LoanProcessor {
    private final ExternalBankService externalService;

    public ThirdPartyBankAdapter(final ExternalBankService externalService) {
        this.externalService = externalService;
    }

    @Override
    public boolean processLoan(final User user, final double amount) {
        return externalService.deposit(user.getId(), amount);
    }
}