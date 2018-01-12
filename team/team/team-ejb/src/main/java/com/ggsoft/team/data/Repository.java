package com.ggsoft.team.data;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.ggsoft.team.model.AbstractEntity;



public class Repository {

	@Inject
	EntityManager em;

	public Repository() {

	}

	public <T extends AbstractEntity> List<T> retrieveAll(Class<T> entityClass) throws Exception {

		try {
			TypedQuery<T> query = em.createNamedQuery(entityClass.getSimpleName() + ".findAll", entityClass);
			List<T> list = query.getResultList();

			return list;
		} catch (Exception e) {

			throw e;
		}
	}

	public <T extends AbstractEntity> Long retrieveCountAll(Class<T> entityClass) throws Exception {

		try {
			Long count = em.createNamedQuery(entityClass.getSimpleName() + ".countAll", Long.class).getSingleResult();

			return count;
		} catch (Exception e) {

			throw e;
		}
	}

	public <T extends AbstractEntity> List<T> retrieveAll(Class<T> entityClass, int first, int max) throws Exception {

		try {
			TypedQuery<T> query = em.createNamedQuery(entityClass.getSimpleName() + ".findAll", entityClass);
			query.setFirstResult(first);
			query.setMaxResults(max);
			List<T> list = (List<T>) query.getResultList();

			return list;
		} catch (Exception e) {

			throw e;
		}
	}
	
	public <T extends AbstractEntity> List<T> filter (List<T> entityClass, Predicate<T> predicate) {
        return entityClass.stream().parallel().filter( predicate ).collect(Collectors.<T>toList());
    }

}
