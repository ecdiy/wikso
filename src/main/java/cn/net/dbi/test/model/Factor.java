package cn.net.dbi.test.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 因子
 * 
 * @author Administrator
 * 
 */
@Entity
public class Factor {
	@Id
	long fid;

	String name;
	int x;
	int y;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getFid() {
		return fid;
	}

	public void setFid(long fid) {
		this.fid = fid;
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
