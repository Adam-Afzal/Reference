package com.qa.persistence.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

import org.apache.log4j.Logger;

import com.qa.persistence.domain.Trainee;


import com.qa.persistence.domain.Subject;
import com.qa.util.JSONUtil;
@ApplicationScoped
@Alternative
public class TraineeMapRepository implements TraineeRepository {
	private static final Logger LOGGER = Logger.getLogger(TraineeMapRepository.class);
	private final Long INITIAL_COUNT = 1L;
	private Map<Long, Trainee> traineeMap;
	private Long ID;

	@Inject
	private JSONUtil util;

	public TraineeMapRepository() {
		this.traineeMap = new HashMap<Long, Trainee>();
		ID = INITIAL_COUNT;
		initTraineeMap();
	}

	public String getAllTrainees() {
		LOGGER.info("In AccountMapRepository getAllAccounts");
		return util.getJSONForObject(traineeMap.values());
	}

	public String createTrainee(String trainee) {
		LOGGER.info("In AccountMapRepository createAccounts");
		ID++;
		Trainee newTrainee = util.getObjectForJSON(trainee, Trainee.class);
		traineeMap.put(ID, newTrainee);
		return trainee;
	}

	public String updateTrainee(Long id, String traineeToUpdate) {
		LOGGER.info("In AccountMapRepository updateAccount");
		Trainee newTrainee = util.getObjectForJSON(traineeToUpdate,Trainee.class);
		traineeMap.put(id, newTrainee);
		return traineeToUpdate;
	}

	public String deleteTrainee(Long id) {
		LOGGER.info("In AccountMapRepository deleteAccount");
		traineeMap.remove(id);
		return "{\"message\": \"trainee sucessfully removed\"}";
	}

	private void initTraineeMap() {
		Subject subject = new Subject("sample");
		subject.setId(1L);
		List<Subject> subjects = new ArrayList<>();
		subjects.add(subject);
		Trainee trainee = new Trainee("Joe", "Bloggs", "1234",subjects);
		traineeMap.put(1L, trainee);
	}
	

}
