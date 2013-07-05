package cn.net.dbi.test.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.springframework.data.jpa.domain.AbstractPersistable;

@SuppressWarnings("serial")
@Entity
public class FactorAttr extends AbstractPersistable<Long> {
	// public static enum Level {
	// father, sonson, aunt, orphan;
	// }
	//
	// public static enum AttrType {
	// data, describe, effect, transition;
	// }

	long schemeId;
	// String name;
	String label;

	// @Enumerated(EnumType.ORDINAL)
	// Level attrLevel;
	// @Enumerated(EnumType.ORDINAL)
	// AttrType attrType;
	// String val;

	// ...

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public long getSchemeId() {
		return schemeId;
	}

	public void setSchemeId(long schemeId) {
		this.schemeId = schemeId;
	}

}
