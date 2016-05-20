package by.bogdevich.training.airline.service;

import java.util.List;

import javax.transaction.Transactional;

import by.bogdevich.training.airline.dataaccess.filtres.ManufacturedPlaneFilter;
import by.bogdevich.training.airline.datamodel.ManufacturedPlane;

public interface ManufacturedPlainService {

	@Transactional
	void update(ManufacturedPlane manufacturedPlane);

	@Transactional
	void insert(ManufacturedPlane manufacturedPlane);

	@Transactional
	void delete(Long id);

	ManufacturedPlane get(Long id);

	List<ManufacturedPlane> getAll();

	Long count(ManufacturedPlaneFilter filter);

}