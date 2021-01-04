package com.ecare.controllers;

import com.ecare.services.BaseService;
import org.springframework.ui.ModelMap;

import java.util.List;

public class Utils {

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
}
