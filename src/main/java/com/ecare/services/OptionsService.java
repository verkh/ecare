package com.ecare.services;

import com.ecare.dao.IOptionDAO;
import com.ecare.models.OptionPO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OptionsService extends BaseService<IOptionDAO, OptionPO> {
}
