package com.ecare.services;

import com.ecare.dao.IOptionDAO;
import com.ecare.models.OptionPO;
import org.springframework.stereotype.Service;

/**
 *  Describes handler of options
 */
@Service
public class OptionsService extends BaseService<IOptionDAO, OptionPO> {
}
