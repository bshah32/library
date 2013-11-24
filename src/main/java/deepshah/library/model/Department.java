package deepshah.library.model;


public interface Department {

	public abstract Long getId();

	public abstract void setId(Long id);

	public abstract String getCode();

	public abstract void setCode(String code);

	public abstract String getName();

	public abstract void setName(String name);

	public abstract String getDescription();

	public abstract void setDescription(String description);

	public abstract Company getCompany();

	public abstract void setCompany(Company company);

}