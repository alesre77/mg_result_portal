package cz.cmgs.mgor.controller;

import cz.cmgs.mgor.entity.RPDto;
import cz.cmgs.mgor.entity.RegistredPlayer;
import cz.cmgs.mgor.page.PagingRequest;
import cz.cmgs.mgor.service.RegistredPlayerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("playersRest")
public class RegistredPlayerRestController {

    @Autowired
    RegistredPlayerService registredPlayerService;

    //@PostMapping("/playersRest")
    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    //public List<RPDto> showRegistredPlayersList(@RequestBody PagingRequest pagingRequest) {
    public List<RPDto> showRegistredPlayersList(@RequestParam Map<String, String> allRequestParams) {
        List<RegistredPlayer> registredPlayerList = new ArrayList<>();
        log.debug("allRequestParams: %", allRequestParams);
        /*
        if (pagingRequest != null && pagingRequest.getSearch().getValue()!=null && !pagingRequest.getSearch().getValue().isEmpty()) {
            registredPlayerList= registredPlayerService.findByKeyword(pagingRequest.getSearch().getValue());
        } else {
            registredPlayerList= registredPlayerService.getRegistredPlayers();
        }
*/
        List<RPDto> rpDtoList = new ArrayList<>();
        for (RegistredPlayer rp:registredPlayerList) {
            RPDto rpd = new RPDto();
            rpd.setBirthDate(rp.getFormattedBirthDate());
            rpd.setFirstName(rp.getFirstName());
            rpd.setLastName(rp.getLastName());
            rpd.setRegNr(rp.getRegNr());

            rpDtoList.add(rpd);
        }

        return rpDtoList;
    }

}
