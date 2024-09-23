package com.designpatterns.structural.adapter;

import com.designpatterns.util.LoggerUtil;

interface ModernPaymentGateway {
    void processPayment(double amount);
}

class LegacyPaymentSystem {
    public void makePayment(String amount) {
        LoggerUtil.log("Legacy system processing payment of $" + amount);
    }
}

class LegacyPaymentAdapter implements ModernPaymentGateway {
    private LegacyPaymentSystem legacySystem;

    public LegacyPaymentAdapter(LegacyPaymentSystem legacySystem) {
        this.legacySystem = legacySystem;
    }

    @Override
    public void processPayment(double amount) {
        String formattedAmount = String.format("%.2f", amount);
        legacySystem.makePayment(formattedAmount);
    }
}

class ModernPaymentProcessor {
    private ModernPaymentGateway paymentGateway;

    public ModernPaymentProcessor(ModernPaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public void processPayment(double amount) {
        paymentGateway.processPayment(amount);
    }
}

public class PaymentSystemIntegrationDemo {
    public static void main(String[] args) {
        LegacyPaymentSystem legacySystem = new LegacyPaymentSystem();
        ModernPaymentGateway adapter = new LegacyPaymentAdapter(legacySystem);
        ModernPaymentProcessor processor = new ModernPaymentProcessor(adapter);

        processor.processPayment(100.50);
    }
}