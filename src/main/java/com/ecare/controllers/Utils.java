package com.ecare.controllers;

import com.ecare.models.OptionPO;
import com.ecare.services.BaseService;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.List;

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
     * @param <POJOType> Type of data
     */
    public static <Service extends BaseService, POJOType> void pagination(Service service,
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

        List<POJOType> dataList = service.get((currentPage-1)*recordsPerPage, recordsPerPage);

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
    public static List<OptionPO> prepareOptions(List<OptionPO> existentOpts, List<OptionPO> allOpts) {
        List<OptionPO> options = new ArrayList<>(existentOpts);

        for(OptionPO opt : options)
            opt.setEnabled(true);

        for(final OptionPO opt : allOpts) {
            boolean found = false;
            for(OptionPO usrOpt : existentOpts) {
                if(opt.getId() == usrOpt.getId()) {
                    found = true;
                    break;
                }
            }
            if(!found) options.add(opt);
        }
        return options;
    }
}
