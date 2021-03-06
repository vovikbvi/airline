package by.bogdevich.training.airline.dataaccess;

import java.util.List;

import by.bogdevich.training.airline.dataaccess.filtres.ModelPlaneFilter;
import by.bogdevich.training.airline.datamodel.ModelPlane;

public interface ModelPlaneDao extends AbstractDao<ModelPlane, Long, ModelPlaneFilter>{

	List<ModelPlane> getRecordsSorted(ModelPlaneFilter filter);

}
