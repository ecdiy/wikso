package cn.net.dbi.test.model;

import javax.persistence.Entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 * 因子
 * 
 * @author Administrator
 * 
 */
@SuppressWarnings("serial")
@Entity
public class Factor extends AbstractPersistable<Long> {

	long schemeId;
	String name;
	int x;
	int y;

	public String getName() {
		return name;
	}

	public long getSchemeId() {
		return schemeId;
	}

	public void setSchemeId(long schemeId) {
		this.schemeId = schemeId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
