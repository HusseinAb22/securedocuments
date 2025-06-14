package com.sain.securedocuments.event.listener;

import com.sain.securedocuments.event.UserEvent;
import com.sain.securedocuments.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserEventListener {
    private final EmailService emailService;

    @EventListener
    public void onUserEvent(UserEvent event) {
        switch (event.getType()) {
            case REGISTRATION ->
                emailService.sendNewAccountEmail(event.getUser().getFirstName(),event.getUser().getEmail(), (String) event.getData().get("key"));
            case RESETPASSWORD ->
                    emailService.sendNewPasswordResetEmail(event.getUser().getFirstName(),event.getUser().getEmail(), (String) event.getData().get("key"));
            default -> {}

        }
    }
}
