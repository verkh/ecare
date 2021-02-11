package com.ecare.services;

import com.ecare.dao.IPlanDAO;
import com.ecare.dto.Plan;
import com.ecare.models.PlanPO;
import com.ecare.models.PlanPO;
import com.ecare.network.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *  Describes handler of plans
 */
@Service
public class PlanService extends BaseService<IPlanDAO, PlanPO, Plan> {

    @Autowired
    private Sender notifier;
    /**
     * Seeks object by id
     * @param id id of object
     * @return object or empty
     */
    public Optional<Plan> get(long id) {
        Optional<PlanPO> PlanPO = dao.get(id);
        return PlanPO.isPresent() ? Optional.of(new Plan(PlanPO.get())) : Optional.empty();
    }

    /**
     * Seeks N object in table. Used for pagination
     * @param from start id in table
     * @param number number of records to load
     * @return list of objects
     */
    public List<Plan> get(int from, int number) {
        return dao.get(from, number).stream().map(value -> Plan.of(value)).collect(Collectors.toList());
    }

    /**
     * @return all objects from table
     */
    public List<Plan> getAll() {
        return dao.getAll().stream().map(value -> Plan.of(value)).collect(Collectors.toList());
    }

    /**
     * Saves objects into database
     * @param value object for saving
     * @return updated object
     */
    public Plan save(Plan value) {
        Plan p = new Plan(dao.save(value.toEntity()));
        notifier.notifyClients();
        return p;
    }

    /**
     * Saves objects into database
     * @param value object for saving
     * @return updated object
     */
    public Plan update(Plan value) {
        Plan p = new Plan(dao.update(value.toEntity()));
        notifier.notifyClients();
        return p;
    }

    /**
     * Deletes object from database
     * @param value object to delete
     */
    public void delete(Plan value) {
        dao.delete(value.toEntity());
        notifier.notifyClients();
    }

    public Plan findByName(String planName) { return new Plan(dao.findByName(planName)); }
}
