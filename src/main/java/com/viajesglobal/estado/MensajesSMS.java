package com.viajesglobal.estado;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

public class MensajesSMS {

    public static final String ACCOUNT_SID = "AC18fbe1985dfe6ec9335c3fbfba24d857";
    public static final String AUTH_TOKEN = "53ca067a1f3fd027685c7e0e77bf78f2";

    public String enviarMensaje(String mensaje, String numero) {
        if (numero != null && mensaje != null) {
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            Message message = Message.creator(
                    new com.twilio.type.PhoneNumber("+573124169511"),
                    new com.twilio.type.PhoneNumber("+12064296601"),mensaje).create();
            System.out.println(message.getSid());
        }

        return "mensaje enviado";
    }
}
