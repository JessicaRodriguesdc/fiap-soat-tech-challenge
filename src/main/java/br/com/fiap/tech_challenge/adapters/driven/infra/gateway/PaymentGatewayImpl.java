package br.com.fiap.tech_challenge.adapters.driven.infra.gateway;

import br.com.fiap.tech_challenge.core.domain.ports.PaymentGateway;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class PaymentGatewayImpl implements PaymentGateway {
    @Override
    public String generatePixQrCode(BigDecimal value) {
        if(value.compareTo(new BigDecimal("10,00")) == 0) {
            return "mockedUnpaidPixQRCodeString";
        }
        return "mockedPaidPixQRCodeString";
    }

    @Override
    public Boolean verifyPaymentStatus(String qrCode) {
        return qrCode.equals("mockedPaidPixQRCodeString");
    }
}
