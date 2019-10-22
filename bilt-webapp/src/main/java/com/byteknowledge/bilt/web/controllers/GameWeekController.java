package com.byteknowledge.bilt.web.controllers;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jboss.logging.Logger;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.byteknowledge.bilt.dto.Game2Dto;

@Controller
public class GameWeekController extends AbstractController {
    
    private static final Logger LOG = Logger.getLogger(GameWeekController.class);

    private static final String MENU_ITEM = "gameweek";
    
    private static final String URL_TEMPLATE = "http://{0}/teams";

    @GetMapping({ "/gameweek" })
    public String home(Model model) {
        // model.addAttribute(MENU_ITEM_PARAM, MENU_ITEM);
        return "gameweek";
    }

    @GetMapping({ "/gamepicks" })
    public ModelAndView gamepicks(Model model) {
        // model.addAttribute(MENU_ITEM_PARAM, MENU_ITEM);
        
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        final HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        
        
        final RestTemplate restTemplate = new RestTemplate();
        final ResponseEntity<List<Game2Dto>> responseEntity = restTemplate.exchange("http://localhost:9080/api/week/49de5eb9-9617-40fe-977e-0bb82a6073f9/games", 
                HttpMethod.GET, requestEntity, new ParameterizedTypeReference<List<Game2Dto>>() {
                });
        final Collection<Game2Dto> gameDtos = responseEntity.getBody();
        LOG.info(gameDtos);
        
        //model.addAttribute("gamesList", gameDtos);
        final Map<String,Object> params = new HashMap<String,Object>();
        params.put("gamesList", gameDtos);
        
        return new ModelAndView("gamepicks", params);
    }
    
    /*
    @GetMapping({ "/", "/hello" })
    public String hello(Model model,@RequestParam(value = "name", required = false, defaultValue = "World") String name) {
            model.addAttribute("name", name);
            return "hello";
    }
    
    @PostMapping(path = "/", consumes = "application/json", produces = "application/json")
    public Collection<OrgDto> list(@RequestBody final RequestDto requestDto) {
        Collection<OrgDto> orgDtos = new ArrayList<OrgDto>();
        try {
            final RestTemplate restTemplate = new RestTemplate();
            final HttpEntity<String> requestEntity = new HttpEntity<String>(getAuthorizedHeaders(requestDto));
            final ResponseEntity<List<OrgDto>> responseEntity = restTemplate.exchange(ORG_LIST_URL_TEMPLATE, HttpMethod.GET,
                    requestEntity, new ParameterizedTypeReference<List<OrgDto>>() {
                    });
            orgDtos = responseEntity.getBody();
            LOG.info("ORGS: "+ orgDtos);
        } catch (Exception ex) {
            LOG.error(ex);
        }
        return orgDtos;
    }   */     
}
