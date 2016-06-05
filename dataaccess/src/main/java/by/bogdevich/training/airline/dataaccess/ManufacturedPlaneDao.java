package by.bogdevich.training.airline.dataaccess;

import java.util.List;

import by.bogdevich.training.airline.dataaccess.filtres.ManufacturedPlaneFilter;
import by.bogdevich.training.airline.datamodel.ManufacturedPlane;

public interface ManufacturedPlaneDao extends AbstractDao<ManufacturedPlane, Long, ManufacturedPlaneFilter>{

	List<ManufacturedPlane> getRecordsSorted(ManufacturedPlaneFilter filter);

}
