package com.ggsoft.team.service;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.UserTransaction;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ggsoft.team.model.AbstractEntity;



@TransactionManagement(TransactionManagementType.BEAN)
public class BaseService {
	private static final Logger log = LogManager.getLogger(BaseService.class);

	protected static final int batchSize = 20;
	
	@Inject
	EntityManager em;

	@Resource
	UserTransaction utx;

	public BaseService(){

	}

	public <T extends AbstractEntity> void create(T entity) throws Exception {
    	log.debug("Begin...");
    	try {
    		if(entity != null){
				utx.begin();
				em.persist(entity);
		    	utx.commit();
//		    	log.info("create " + entity.toString() + " success.");
    		}
		} catch (Exception e) {
			utx.rollback();
			log.error("create entity failed.");
			throw e;
		}
    	log.debug("End...");
    }

	public <T extends AbstractEntity> void merge(T entity) throws Exception {
    	log.debug("Begin...");
    	try {
    		if(entity != null){
				utx.begin();
				em.merge(entity);
		    	utx.commit();
		    	log.debug("merge " + entity.toString() + " success.");
    		}
		} catch (Exception e) {
			utx.rollback();
			log.error("merge entity failed.");
			throw e;
		}
    	log.debug("End...");
    }

    public <T extends AbstractEntity> void create(Collection<T> entities) throws Exception {
    	log.debug("Begin...");
    	try {
			utx.begin();
			int count = 0;
	        for (T entity : entities) {
	        	if(entity != null){
	        		em.persist(entity);
	        		if(++count % batchSize == 0){
	        			em.flush();
	        			em.clear();
	        		}
	        	}
	        }
	        utx.commit();
    	} catch (Exception e) {
			utx.rollback();
			log.error("create entities failed.");
			throw e;
		}
    	log.debug("End...");
    }

    public <T extends AbstractEntity> void merge(Collection<T> entities) throws Exception {
    	try {
			utx.begin();
			int count = 0;
	        for (T entity : entities) {
	        	if(entity != null){
	        		em.merge(entity);
	        		if(++count % batchSize == 0){
	        			em.flush();
	        			em.clear();
	        		}
	        	}
	        }
	        utx.commit();
    	} catch (Exception e) {
			utx.rollback();
			log.error("merge entities failed.");
			throw e;
		}
    }

    public <T extends AbstractEntity> void merge(Collection<T> entities, boolean deleteAll, Class<T> entityClass) throws Exception {
    	log.debug("Begin...");
    	try {
			utx.begin();
			if(deleteAll){
				em.createNamedQuery(entityClass.getSimpleName() + ".deleteAll").executeUpdate();
			}
			int count = 0;
	        for (T entity : entities) {
	        	if(entity != null){
	        		em.merge(entity);
	        		if(++count % batchSize == 0){
	        			em.flush();
	        			em.clear();
	        		}
	        	}
	        }
	        utx.commit();
    	} catch (Exception e) {
			utx.rollback();
			log.error("merge entities failed.");
			throw e;
		}
    	log.debug("End...");
    }

    public <T extends AbstractEntity> List<T> getAll(Class<T> entityClass)throws Exception {
    	log.debug("Begin...");
    	try {
    		TypedQuery<T> query = em.createNamedQuery(entityClass.getSimpleName()+".findAll", entityClass);
    		List<T> list = query.getResultList();
    		log.debug("End...");
    		return list;
    	} catch (Exception e) {
			log.error("getAll entities failed.");
			throw e;
		}
    }

    public <T extends AbstractEntity> Long getCountAll(Class<T> entityClass)throws Exception{
		log.debug("Begin...");
    	try {
    		Long count = em.createNamedQuery(entityClass.getSimpleName()+".countAll", Long.class)
    					   .getSingleResult();
    		log.debug("End...");
    		return count;
    	} catch (Exception e) {
			log.error("getUsersByRole failed.");
			throw e;
		}
	}

    public <T extends AbstractEntity> T getByPk(Class<T> entityClass, Object pk)throws Exception {
//    	log.debug("Begin...");
    	try {
    		T e = em.find(entityClass, pk);
    		return e;
    	} catch (Exception e) {
			log.error("getByPk entities failed.");
			throw e;
		}
    }


	/**
     * This method is used for manual paginate <br/>
     * Ex. getRange(machineType, ((currentPage-1) * pageSize), pageSize) <br/>
     * If pageSize = 10 and want to select for page 2
     * should call getRange(machineType, ((2-1) * 10), 10) this will return result from index 10 to 19
	 * @param entityClass - class of entity
	 * @param first - int
	 * @param max - int
	 * @return List of Entity T
	 * @throws Exception
	 */
	public <T extends AbstractEntity> List<T> getAll(Class<T> entityClass, int first, int max)throws Exception {
    	log.debug("Begin...");
    	try {
    		TypedQuery<T> query = em.createNamedQuery(entityClass.getSimpleName()+".findAll", entityClass);
    		query.setFirstResult(first);
    		query.setMaxResults(max);
    		List<T> list = (List<T>) query.getResultList();
    		log.debug("End...");
    		return list;
    	} catch (Exception e) {
			log.error("getRange entities failed.");
			throw e;
		}
    }

	public <T extends AbstractEntity> void update(T entity, Object pk) throws Exception{
		log.debug("Begin...");
		try {
    		if(entity != null){
    			utx.begin();
    			if(em.find(entity.getClass(), pk)!= null){
    				em.merge(entity);
    			}else{
    				throw new IllegalArgumentException("not found entity where pk="+pk.toString());
    			}
    			utx.commit();
    		}
		} catch (Exception e) {
    		utx.rollback();
			log.error("update entity failed.", e);
			throw e;
		}
		log.debug("End...");
	}

	public <T extends AbstractEntity> void delete(Class<T> entityClass, Object pk) throws Exception{
		log.debug("Begin...");
		try {
    		if(pk != null){
    			utx.begin();
    			T e = em.find(entityClass, pk);
    			if(e != null){
    				em.remove(e);
    			}
    			utx.commit();
    		}
		} catch (Exception e) {
    		utx.rollback();
			log.error("delete entity failed.", e);
			throw e;
		}
		log.debug("End...");
	}
}
