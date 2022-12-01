package br.com.nepo.napoleonshortener.shortlink.adapter.input.web;

import br.com.nepo.napoleonshortener.shortlink.application.port.ShortLinkService;
import br.com.nepo.napoleonshortener.shortlink.application.port.input.CreateShortLinkCommand;
import br.com.nepo.napoleonshortener.shortlink.domain.ShortLink;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/shortlink")
@RequiredArgsConstructor
@Slf4j
public class ShortLinkController {

    private final ShortLinkService shortLinkService;


    @PostMapping(
            path = "/",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ShortLink> createShortLink(@Valid @ModelAttribute final CreateShortLinkCommand createShortLinkCommand) {
        return ResponseEntity.ok(shortLinkService.createShortLink(createShortLinkCommand));
    }

    @GetMapping(path = "/")
    public ModelAndView redirectToShortLink() {

        return new ModelAndView("redirect:" + "https://google.com.br");
    }
}
