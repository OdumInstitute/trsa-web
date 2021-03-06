/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package us.cyberimpact.trsa.entities;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;

import javax.persistence.EntityManager;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.ws.rs.core.Response;
import us.cyberimpact.trsa.web.jsf.util.JsfUtil;

/**
 *
 * @author asone
 */
public abstract class AbstractFacade<T> {
    
    private static final Logger logger = Logger.getLogger(AbstractFacade.class.getName());
    
    
    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

//    public void create(T entity) {
      public Response create(T entity) {
          
logger.log(Level.INFO, "========== AbstractFacade#create : start ==========");

          
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(entity);
        StringBuilder sb = new StringBuilder();
        if (constraintViolations.size() > 0) {
            Iterator<ConstraintViolation<T>> iterator = constraintViolations.iterator();
            while (iterator.hasNext()) {
                ConstraintViolation<T> cv = iterator.next();
//                System.err.println(
//                        cv.getRootBeanClass().getName() + "." + cv.getPropertyPath() + " " + cv.getMessage());

                JsfUtil.addErrorMessage(
                        cv.getRootBeanClass().getSimpleName() + "." + cv.getPropertyPath() + " " + cv.getMessage());
                sb.append(cv.getRootBeanClass().getSimpleName()).append(".").append(cv.getPropertyPath()).append(" ").append(cv.getMessage());
            }
            logger.log(Level.INFO, "========== AbstractFacade#create : api-call: end ==========");
            return Response.status(400)
                .entity( Json.createObjectBuilder()
                .add("code", 400)
                .add("message", "Validation error during create request: "+ sb.toString())
                .build())
                .type("application/json").build();
        } else {
            getEntityManager().persist(entity);
            logger.log(Level.INFO, "========== AbstractFacade#create : web-gui call: end ==========");
            return Response.status(Response.Status.OK).entity(entity).build();
        }
    }

      
      
      
    public void save(T entity) {
        
        logger.log(Level.INFO, "========== AbstractFacade#create : start ==========");
        
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(entity);
        StringBuilder sb = new StringBuilder();
        if (constraintViolations.size() > 0) {
            Iterator<ConstraintViolation<T>> iterator = constraintViolations.iterator();
            while (iterator.hasNext()) {
                ConstraintViolation<T> cv = iterator.next();
//                System.err.println(
//                        cv.getRootBeanClass().getName() + "." + cv.getPropertyPath() + " " + cv.getMessage());

                JsfUtil.addErrorMessage(
                        cv.getRootBeanClass().getSimpleName() + "." + cv.getPropertyPath() + " " + cv.getMessage());
                sb.append(cv.getRootBeanClass().getSimpleName()).append(".").append(cv.getPropertyPath()).append(" ").append(cv.getMessage());
            }
            
        } else {
            getEntityManager().persist(entity);
        }
            logger.log(Level.INFO, "========== AbstractFacade#save : end ==========");
    }
      
      
      
    public void edit(T entity) {
        logger.log(Level.INFO, "========== AbstractFacade#edit : start ==========");
        getEntityManager().merge(entity);
            logger.log(Level.INFO, "========== AbstractFacade#edit : end ==========");
    }

    public void remove(T entity) {
        logger.log(Level.INFO, "========== AbstractFacade#remove : start ==========");
        getEntityManager().remove(getEntityManager().merge(entity));
        logger.log(Level.INFO, "========== AbstractFacade#remove : end ==========");
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        logger.log(Level.INFO, "========== AbstractFacade#findAll : start ==========");
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        logger.log(Level.INFO, "========== AbstractFacade#findAll : end ==========");
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
}
