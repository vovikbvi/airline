package by.bogdevich.training.airline.dataaccess;

import java.util.List;

import by.bogdevich.training.airline.dataaccess.filtres.PlaneFilter;
import by.bogdevich.training.airline.datamodel.Plane;

public interface PlaneDao extends AbstractDao<Plane, Long, PlaneFilter>{

	List<Plane> getRecordsSorted(PlaneFilter filter);

	Plane getPlaneWithFetch(Plane plane);

}
