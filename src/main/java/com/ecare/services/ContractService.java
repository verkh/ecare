package com.ecare.services;

import com.ecare.dao.IContractDAO;
import com.ecare.dao.IUserDAO;
import com.ecare.dto.Contract;
import com.ecare.models.ContractPO;
import com.ecare.models.UserPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *  Describes handler of contracts
 */
@Service
public class ContractService extends BaseService<IContractDAO, ContractPO, Contract> {

    @Autowired
    private IUserDAO userDAO;

    /**
     * Seeks object by id
     * @param id id of object
     * @return object or empty
     */
    public Optional<Contract> get(long id) {
        Optional<ContractPO> ContractPO = dao.get(id);
        return ContractPO.isPresent() ? Optional.of(new Contract(ContractPO.get())) : Optional.empty();
    }

    /**
     * Seeks N object in table. Used for pagination
     * @param from start id in table
     * @param number number of records to load
     * @return list of objects
     */
    public List<Contract> get(int from, int number) {
        return dao.get(from, number).stream().map(value -> Contract.of(value)).collect(Collectors.toList());
    }

    /**
     * @return all objects from table
     */
    public List<Contract> getAll() {
        return dao.getAll().stream().map(value -> Contract.of(value)).collect(Collectors.toList());
    }

    /**
     * Saves objects into database
     * @param value object for saving
     * @return updated object
     */
    public Contract save(Contract value) {
        return new Contract(dao.save(value.toEntity()));
    }

    /**
     * Saves objects into database
     * @param value object for saving
     * @return updated object
     */
    public Contract update(Contract value) {
        return new Contract(dao.update(value.toEntity()));
    }

    /**
     * Deletes object from database
     * @param value object to delete
     */
    public void delete(Contract value) { dao.delete(value.toEntity()); }
    
    /**
     * find contract by phone number
     * @param phoneNumber phone number string
     * @return Found contract or null if contract is not found
     */
    public Contract findByPhoneNumber(String phoneNumber) {
        return new Contract(dao.findByPhone(phoneNumber));
    }

    /**
     * Search contacts that have number alike
     * @param phoneNumber partial of full number
     * @return list of matched contracts
     */
    public List<Contract> findAlikeByPhoneNumber(String phoneNumber) {
        return dao.findAlikeByPhone(phoneNumber).stream().map(value -> Contract.of(value)).collect(Collectors.toList());
    }

    /**
     * find contract by email
     * @param email email of user
     * @return Found user or null if user is not found
     */
    public Contract findByEmail(String email) {
        UserPO user = userDAO.findByEmail(email);
        return user != null ? new Contract(user.getContract()) : null;
    }

    /**
     * Search contacts that have number alike
     * @param email partial of full email
     * @return list of matched users
     */
    public List<Contract> findAlikeByEmail(String email) {
        return userDAO.findAlikeByEmail(email).stream().map(value -> Contract.of(value.getContract())).collect(Collectors.toList());
    }
}
