package com.ecare.dao;

import com.ecare.models.OptionPO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO handles logic related to options table
 * @see Dao
 */
@Transactional
public class OptionDAO extends Dao<OptionPO> implements IOptionDAO {

    private static Logger logger = LogManager.getLogger(OptionDAO.class);

    public OptionDAO() { super(OptionPO.class); }

    /**
     * Checks options in database that marked with "deprecated flag". If options is also not related to any contract
     * it would be deleted from database
     * @return number of deleted options
     */
    @Override
    public int deleteUnusedDeprecatedOptions() {
        final String query = "DELETE options FROM options LEFT JOIN contract_options coptions ON " +
                "coptions.option_id = options.id WHERE options.deprecated=1 and coptions.id is NULL;";

        logger.debug("Trying to delete unused deprecated options...");
        Query q = sessionFactory.getCurrentSession().createSQLQuery(query);
        return q.executeUpdate();
    }

    /**
     * Set deprecated flag. It would be erased by task scheduler if no contract linked to it
     * @param deprecated deprecated or not
     * @param id id of target option
     */
    @Override
    public void setDeprecated(Long id, boolean deprecated) {
        final String query = String.format("UPDATE options set deprecated=%d WHERE id=%d;", deprecated ? 1 : 0, id);
        logger.debug("Trying to delete unused deprecated options...");
        Query q = sessionFactory.getCurrentSession().createSQLQuery(query);
        q.executeUpdate();
    }
}
