package com.ecare.controllers;

import com.ecare.dto.Option;
import com.ecare.services.BaseService;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Basic class of utils for controllers
 */
public class Utils {

    /**
     * This methond implements pagination algorithm and configures model map for JSP page
     * @param service service that handles represented in JSP data
     * @param model model of JSP page
     * @param currentPage current viewed page
     * @param attributeName name of model attribute that should be set
     * @param <Service> Type of service
     * @param <DTOType> Type of data
     */
    public static <Service extends BaseService, DTOType> void pagination(Service service,
                                                                          ModelMap model,
                                                                          Integer currentPage,
                                                                          String attributeName)
    {
        final int recordsPerPage = 15;

        long rows = service.count();
        int nOfPages = (int)(rows / recordsPerPage);

        if (nOfPages % recordsPerPage > 0) {
            nOfPages++;
        }

        if(currentPage == null)
            currentPage = 1;

        List<DTOType> dataList = service.get((currentPage-1)*recordsPerPage, recordsPerPage);

        model.addAttribute(attributeName, dataList);

        model.addAttribute("noOfPages", nOfPages);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("recordsPerPage", recordsPerPage);
    }

    /**
     * Prepares list of options that could be displayed in contract or plan page
     * @param existentOpts options already related
     * @param allOpts all options that are available
     * @return the options list with set "true" flag for enabled options
     */
    public static List<Option> prepareOptions(List<Option> existentOpts, List<Option> allOpts) {
        List<Option> options = existentOpts.stream().map(opt -> opt.toBuilder().build()).collect(Collectors.toList());

        for(Option opt : options)
            opt.setEnabled(true);

        for(final Option opt : allOpts) {
            boolean found = false;
            for(Option usrOpt : existentOpts) {
                if(opt.getId() == usrOpt.getId()) {
                    found = true;
                    break;
                }
            }
            if(!found) options.add(opt.toBuilder().build());
        }
        return options;
    }

    public static void sortOptions(List<Option> options) {
        Collections.sort(options, (a, b) -> a.getId().compareTo(b.getId()));
    }
}
