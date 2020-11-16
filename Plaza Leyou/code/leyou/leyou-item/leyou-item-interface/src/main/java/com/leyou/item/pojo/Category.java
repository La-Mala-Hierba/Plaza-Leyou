package com.leyou.item.pojo;

import lombok.ToString;
import javax.persistence.*;

@Table(name="tb_category")
@ToString
@Entity
public class Category {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	private String name;
	@Column(name = "parent_id")
	private Long parentId;
	@Column(name = "is_parent")
	private Boolean isParent; // getter y setter producidos por isParent necesitan añadir manualmente Is
	private Integer sort;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Boolean getIsParent() {
		return isParent;
	}

	public void setIsParent(Boolean parent) {
		isParent = parent;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
}