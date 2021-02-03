package com.ecare.dao;

import com.ecare.base.BaseData;
import com.ecare.models.OptionPO;
import com.ecare.models.OptionRestrictionPO;

import java.util.List;

/**
 * Interface of DAO handles logic related to options table
 * @see BaseData
 */
public interface IOptionDAO extends BaseData<OptionPO> {

    /**
     * Checks options in database that marked with "deprecated flag". If options is also not related to any contract
     * it would be deleted from database
     * @return number of deleted options
     */
    public int deleteUnusedDeprecatedOptions();

    /**
     * Set deprecated flag. It would be erased by task scheduler if no contract linked to it
     * @param deprecated deprecated or not
     * @param id id of target option
     */
    public void setDeprecated(Long id, boolean deprecated);
}
