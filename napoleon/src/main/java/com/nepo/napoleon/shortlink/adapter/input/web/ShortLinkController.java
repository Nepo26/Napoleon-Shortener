package com.nepo.napoleon.shortlink.adapter.input.web;

import com.nepo.napoleon.shortlink.application.port.ShortLinkService;
import com.nepo.napoleon.shortlink.application.port.input.CreateShortLinkCommand;
import com.nepo.napoleon.shortlink.domain.ShortLink;
import com.nepo.napoleon.shortlink.errors.NonCompliantURIException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

@RestController
@RequestMapping(path = "/")
@RequiredArgsConstructor
@Slf4j
public class ShortLinkController {

    private final ShortLinkService shortLinkService;

    @Value("${server.hostname}")
    private String hostname;

    @PostMapping(
            path = "/shortlink",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ShortLink> createShortLink(@Valid @ModelAttribute final CreateShortLinkCommand createShortLinkCommand) throws NonCompliantURIException {


        try {
            final ShortLink shortLink = shortLinkService.createShortLink(createShortLinkCommand);
            return ResponseEntity.status(HttpStatus.CREATED).header("Location","http://"+hostname+"/shortlink/"+shortLink.getRandomId()).body(shortLink);

        } catch (MalformedURLException|URISyntaxException e) {
            throw new NonCompliantURIException(e.getMessage());
        }

    }

    @GetMapping(path = "/{shortLinkId}")
    public ModelAndView redirectToShortLink(@PathVariable final String shortLinkId) {

        final ShortLink shortLink = shortLinkService.retrieveShortLinkById(shortLinkId);

        return new ModelAndView("redirect:" + shortLink.getLink());
    }
}
