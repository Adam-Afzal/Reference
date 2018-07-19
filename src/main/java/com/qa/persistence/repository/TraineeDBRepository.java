package com.qa.persistence.repository;
import java.util.Collection;
import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;

import com.qa.util.JSONUtil;

import com.qa.persistence.domain.Trainee;

@Transactional(SUPPORTS)
@Default
public class TraineeDBRepository implements TraineeRepository {

	private static final Logger LOGGER  = Logger.getLogger(TraineeDBRepository.class);
	
	@PersistenceContext(unitName = "primary")
	private EntityManager manager;

	@Inject
	private JSONUtil util;

	public String getAllTrainees() {
		LOGGER.info("In TraineeDBRepository getAllAccounts ");
		Query query = manager.createQuery("Select a FROM Account a");
		Collection<Trainee> trainees = (Collection<Trainee>) query.getResultList();
		return util.getJSONForObject(trainees);
	}

	@Transactional(REQUIRED)
	public String createTrainee(String trainee) {
		LOGGER.info("In AccountDBRepository createAccount ");
		Trainee aTrainee = util.getObjectForJSON(trainee, Trainee.class);
		manager.persist(aTrainee);
		return "{\"message\": \"trainee has been sucessfully added\"}";
	}

	@Transactional(REQUIRED)
	public String updateTrainee(Long id, String traineeToUpdate) {
		LOGGER.info("In AccountDBRepository updateAccount ");
		Trainee updatedTrainee = util.getObjectForJSON(traineeToUpdate, Trainee.class);
		Trainee traineeFromDB = findTrainee(id);
		if (traineeToUpdate != null) {
			traineeFromDB = updatedTrainee;
			manager.merge(traineeFromDB);
			return "{\"message\": \"trainee sucessfully updated\"}";
		} else {
			LOGGER.info("Account is null!");
			return "{\"message\": \"trainee NOT updated\"}";
			
			
		}
	
	}

	@Transactional(REQUIRED)
	public String deleteTrainee(Long id) {
		LOGGER.info("In AccountDBRepository deleteAccount ");
		Trainee traineeInDB = findTrainee(id);
		if (traineeInDB != null) {
			manager.remove(traineeInDB);
		}
		return "{\"message\": \"trainee sucessfully deleted\"}";
	}

	private Trainee findTrainee(Long id) {
		LOGGER.info("In AccountDBRepository findAccount ");
		return manager.find(Trainee.class, id);
	}

	public void setManager(EntityManager manager) {
		this.manager = manager;
	}

	public void setUtil(JSONUtil util) {
		this.util = util;
	}
	
}
