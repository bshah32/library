package deepshah.library.model.impl;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import deepshah.library.model.Company;
import deepshah.library.model.Department;

@Entity
@Table(name = "Company")
@NamedQuery(name = "findCompanyByName", query = "from JpaCompany c where c.name =:name")
public class JpaCompany implements Company {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Id")
	private Long id;

	@Column(name = "Name")
	private String name;

	@Column(name = "Description")
	private String description;

	@OneToMany(mappedBy = "company", targetEntity = JpaDepartment.class)
	private Set<Department> departments;

	public JpaCompany() {
	}

	public JpaCompany(Long id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see deepshah.library.model.impl.Company#getId()
	 */
	@Override
	public Long getId() {
		return id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see deepshah.library.model.impl.Company#setId(long)
	 */
	@Override
	public void setId(Long id) {
		this.id = id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see deepshah.library.model.impl.Company#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see deepshah.library.model.impl.Company#setName(java.lang.String)
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see deepshah.library.model.impl.Company#getDescription()
	 */
	@Override
	public String getDescription() {
		return description;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see deepshah.library.model.impl.Company#setDescription(java.lang.String)
	 */
	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see deepshah.library.model.impl.Company#getDepartment()
	 */
	@Override
	public Set<Department> getDepartments() {
		if (departments == null) {
			departments = new LinkedHashSet<Department>();
		}
		return departments;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see deepshah.library.model.impl.Company#setDepartment(java.util.Set)
	 */
	@Override
	public void setDepartments(Set<Department> department) {
		this.departments = department;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JpaCompany other = (JpaCompany) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
