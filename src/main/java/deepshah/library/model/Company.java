package deepshah.library.model;

import java.util.Set;

public interface Company {

	public abstract Long getId();

	public abstract void setId(Long id);

	public abstract String getName();

	public abstract void setName(String name);

	public abstract String getDescription();

	public abstract void setDescription(String description);

	public abstract Set<Department> getDepartments();

	public abstract void setDepartments(Set<Department> department);

}
