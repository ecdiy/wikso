package cn.net.dbi.test.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.springframework.data.jpa.domain.AbstractPersistable;

@SuppressWarnings("serial")
@Entity
public class FactorAttr extends AbstractPersistable<Long> {
	public static enum Level {
		father, sonson, aunt, orphan;
	}

	public static enum AttrType {
		data, describe, effect, transition;
	}

	String name;
	String label;
	@Enumerated(EnumType.ORDINAL)
	Level attrLevel;
	@Enumerated(EnumType.ORDINAL)
	AttrType attrType;
	String val;

	// ...
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Level getAttrLevel() {
		return attrLevel;
	}

	public void setAttrLevel(Level attrLevel) {
		this.attrLevel = attrLevel;
	}

	public AttrType getAttrType() {
		return attrType;
	}

	public void setAttrType(AttrType attrType) {
		this.attrType = attrType;
	}

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}

}
