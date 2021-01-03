package com.ecare.services;

import com.ecare.dao.PlanDAO;
import com.ecare.models.PlanPO;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Service
public class PlanService {
//public class PlanService implements UserDetailsService {

    @Autowired
    private PlanDAO planDao;

    public Optional<PlanPO> get(long id) { return planDao.get(id); }

    public List<PlanPO> getAll() { return planDao.getAll(); }

    public void save(PlanPO value) {
        planDao.save(value);
    }

    public void update(PlanPO value) {
        planDao.update(value);
    }

    public void delete(PlanPO value) { planDao.delete(value); }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        UserDetails user = new User(username, "password", true, true, true, true, new GrantedAuthority[]{ new GrantedAuthorityImpl("ROLE_USER") });
//        return user;
//    }
}
