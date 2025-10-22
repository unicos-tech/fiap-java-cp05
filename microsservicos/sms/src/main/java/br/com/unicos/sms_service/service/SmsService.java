package br.com.unicos.sms_service.service;

import br.com.unicos.sms_service.dto.SmsRequest;
import kong.unirest.core.HttpResponse;
import kong.unirest.core.Unirest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class SmsService {

    @Transactional
    public void apply(SmsRequest sms){
        System.out.println("Mensagem recebida da fila: " + sms.toString());

        String apiKey = System.getenv("SMSDEV_API_KEY");
        String phoneNumber = sms.getPhoneNumber();
        String message = sms.getMessage();

        String url = String.format(
            "https://api.smsdev.com.br/v1/send?key=%s&type=9&number=%s&msg=%s",
            apiKey, phoneNumber, message
        );

        HttpResponse<String> response = Unirest.get(url).asString();
        System.out.println(response.getBody());
    }
}
