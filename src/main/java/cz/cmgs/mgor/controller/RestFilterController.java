package cz.cmgs.mgor.controller;

import cz.cmgs.mgor.entity.RegistredPlayer;
import cz.cmgs.mgor.page.Column;
import cz.cmgs.mgor.page.Order;
import cz.cmgs.mgor.page.Page;
import cz.cmgs.mgor.page.Search;
import cz.cmgs.mgor.service.RegistredPlayerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class RestFilterController {

    private static final Comparator<RegistredPlayer> EMPTY_COMPARATOR = (e1, e2) -> 0;

    private static final String PARAM_DRAW= "draw";
    private static final String PARAM_ORDER_COLUMN= "order[0][column]";
    private static final String PARAM_ORDER_DIR= "order[0][dir]";

    @Autowired
    RegistredPlayerService registredPlayerService;

    @RequestMapping("playersFilterPageRest")
    public Page<RegistredPlayer> filterPageRegistredPlayers(@RequestParam Map<String, String> allRequestParams) {

        List<RegistredPlayer> registredPlayerList= registredPlayerService.getRegistredPlayers();

        log.debug("filterPageRegistredPlayers:registredPlayerList: {}", registredPlayerList);
        List<RegistredPlayer> filtered = registredPlayerList.stream()
                .sorted(sortRegistredPlayers(allRequestParams))
                .collect(Collectors.toList());

        log.debug("filtered: {}", filtered);

        Page<RegistredPlayer> page = new Page<>(filtered);
        page.setRecordsFiltered((int) filtered.size());
        page.setRecordsTotal((int) registredPlayerList.size());
        page.setDraw(Integer.valueOf(getParameterValue(PARAM_DRAW, allRequestParams)).intValue());

        log.debug("page: {}", page);
        //page.setDraw(pagingRequest.getDraw());

        return page;
    }

    private String getParameterValue(String paramName, Map<String, String> allRequestParams) {
        log.debug("allRequestParams.get(paramName): {}, {}",paramName, allRequestParams.get(paramName));
        return allRequestParams.get(paramName);
    }

    private Column getParameterValueColumn(int columnIndex, Map<String, String> allRequestParams) {
        Column column = new Column();

        column.setData(getParameterValue("columns["+columnIndex+"][data]", allRequestParams));
        column.setName(getParameterValue("columns["+columnIndex+"][name]", allRequestParams));
        column.setSearchable(getParameterValue("columns["+columnIndex+"][searchable]", allRequestParams).equalsIgnoreCase("true"));

        Search search = new Search();
        search.setValue(getParameterValue("columns["+columnIndex+"][search][value]", allRequestParams));
        search.setRegexp(getParameterValue("columns["+columnIndex+"][search][regex]", allRequestParams).equalsIgnoreCase("true"));

        column.setSearch(search);

        return column;
    }

    private Comparator<RegistredPlayer> sortRegistredPlayers(Map<String, String> allRequestParams) {
        if (getParameterValue(PARAM_ORDER_COLUMN, allRequestParams) == null) {
            return EMPTY_COMPARATOR;
        }

        try {
            Order order = new Order(Integer.valueOf(getParameterValue(PARAM_ORDER_COLUMN, allRequestParams)).intValue(), Sort.Direction.valueOf(getParameterValue(PARAM_ORDER_DIR, allRequestParams).toUpperCase()));

            int columnIndex = order.getColumn();
            Column column = getParameterValueColumn(columnIndex, allRequestParams);


            Comparator<RegistredPlayer> comparator = RegistredPlayer.getComparator(column.getData(), order.getDir().name());
            if (comparator == null) {
                return EMPTY_COMPARATOR;
            }

            return comparator;

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return EMPTY_COMPARATOR;
    }
}
