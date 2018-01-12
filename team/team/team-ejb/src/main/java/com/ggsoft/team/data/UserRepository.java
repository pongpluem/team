package com.ggsoft.team.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.logging.log4j.Logger;

import com.ggsoft.team.model.User;
import com.ggsoft.team.model.User_;


@Named
@ApplicationScoped
public class UserRepository extends Repository implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	@Named("produceLog")
	private Logger log;

	public User findById(Integer id) {
		return em.find(User.class, id);
	}
	
	public User Login(Integer id, String userName, String password) {
		
		log.debug("Begin");
				
		User user = new User();
		
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<User> q = cb.createQuery(User.class);
			Root<User> root = q.from(User.class);

			// Constructing list of parameters
			List<Predicate> predicates = new ArrayList<Predicate>();
						
			if (id != null) {
				if (id != 0) {
					predicates.add(cb
							.equal(root.get(User_.userID), id));
				}
			}
			else
			if (userName != null) {
				if (!(userName.isEmpty())) {
					predicates.add(cb.equal(root.get(User_.userName)
							, userName));
				}
			}
			
			
			// Order
			List<Order> orders = new ArrayList<>();			

			q.select(root).where(predicates.toArray(new Predicate[] {})).orderBy(orders).distinct(true);

			//user = 
			List<User> resultList = em.createQuery(q).getResultList();
			if(resultList != null && !resultList.isEmpty())
				user = Optional.ofNullable(resultList.get(0)).orElse(null);
			else
				user = null;

		} catch (Exception e) {
			throw e;
		}

		log.debug("End");
		return user;
	}
	
	public String md5EnCrypt(String password){		
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("select md5('");
		strBuilder.append(password);
		strBuilder.append("') from user");
		
		Object r = em.createNativeQuery(strBuilder.toString()).getSingleResult();
		
		return (String)r;
	}
		

}
