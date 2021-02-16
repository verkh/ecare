package com.ecare.services;

import com.ecare.dao.IOptionDAO;
import com.ecare.dto.Option;
import com.ecare.models.OptionPO;
import com.ecare.models.OptionPO;
import com.ecare.network.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *  Describes handler of options
 */
@Service
public class OptionsService extends BaseService<IOptionDAO, OptionPO, Option> {

    @Autowired
    private Sender notifier;

    /**
     * Seeks object by id
     * @param id id of object
     * @return object or empty
     */
    public Optional<Option> get(long id) {
        Optional<OptionPO> OptionPO = dao.get(id);
        return OptionPO.isPresent() ? Optional.of(new Option(OptionPO.get())) : Optional.empty();
    }

    /**
     * Seeks N object in table. Used for pagination
     * @param from start id in table
     * @param number number of records to load
     * @return list of objects
     */
    public List<Option> get(int from, int number) {
        return dao.get(from, number).stream().map(value -> Option.of(value)).collect(Collectors.toList());
    }

    /**
     * @return all objects from table
     */
    public List<Option> getAll() {
        return dao.getAll().stream().map(value -> Option.of(value)).collect(Collectors.toList());
    }

    /**
     * Saves objects into database
     * @param value object for saving
     * @return updated object
     */
    public Option save(Option value) {
        Option o = new Option(dao.save(value.toEntity()));
        notifier.notifyClients();
        return o;
    }

    /**
     * Saves objects into database
     * @param value object for saving
     * @return updated object
     */
    public Option update(Option value) {
        Option o = new Option(dao.update(value.toEntity()));
        notifier.notifyClients();
        return o;
    }

    /**
     * Deletes object from database
     * @param value object to delete
     */
    public void delete(Option value) {
        dao.delete(value.toEntity());
        notifier.notifyClients();
    }


    /**
     * Set deprecated flag. It would be erased by task scheduler if no contract linked to it
     * @param deprecated deprecated or not
     * @param id id of target option
     */
    public void setDeprecated(Long id, boolean deprecated) {
        dao.setDeprecated(id, deprecated);
        notifier.notifyClients();
    }

    /**
     * Checks options in database that marked with "deprecated flag". If options is also not related to any contract
     * it would be deleted from database
     * @return number of deleted options
     */
    public int deleteUnusedDeprecatedOptions() {
        int deleted = dao.deleteUnusedDeprecatedOptions();
        if(deleted > 0) {
            // we want to notify only if something was actually changed
            notifier.notifyClients();
        }
        return deleted;
    }
}
