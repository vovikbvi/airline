package by.bogdevich.training.airline.service;

import java.util.List;

import javax.transaction.Transactional;

import by.bogdevich.training.airline.dataaccess.filtres.ModelPlaneFilter;
import by.bogdevich.training.airline.datamodel.ModelPlane;

public interface ModelPlaneService {

	@Transactional
	void insert(ModelPlane modelPlane);

	@Transactional
	void update(ModelPlane modelPlane);

	@Transactional
	void delete(Long id);

	ModelPlane get(Long id);

	List<ModelPlane> getAll();

	Long count(ModelPlaneFilter filter);

}