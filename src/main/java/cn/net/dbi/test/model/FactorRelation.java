package cn.net.dbi.test.model;

import javax.persistence.Entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

@SuppressWarnings("serial")
@Entity
public class FactorRelation extends AbstractPersistable<Long> {
	long fid1;
	long fid2;
	String label;
	long schemeId;

	public long getFid1() {
		return fid1;
	}

	public void setFid1(long fid1) {
		this.fid1 = fid1;
	}

	public long getFid2() {
		return fid2;
	}

	public void setFid2(long fid2) {
		this.fid2 = fid2;
	}

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
