package Team6.BWU5TEAM6.controllers;

import Team6.BWU5TEAM6.dto.NewEmailDTO;
import Team6.BWU5TEAM6.entities.Client;
import Team6.BWU5TEAM6.exceptions.BadRequestException;
import Team6.BWU5TEAM6.tools.MailgunSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {
    @Autowired
    private MailgunSender mailgunSender;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public void sendEmail(@RequestBody @Validated NewEmailDTO body, BindingResult validation) {
        if (validation.hasErrors()) throw new BadRequestException(validation.getAllErrors());
        Client recipient = new Client(body.email());
        this.mailgunSender.sendEmail(recipient, body.subject(), body.text());
    }
}
