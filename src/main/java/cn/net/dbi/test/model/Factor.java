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
	int x;// X坐标
	int y;// Y坐标
	int countRelation;// 统计所有的关系
	int fromRelation;// 来自关系
	int toRelation;// 去关系
	int fromMaxLength;// TODO 来自的最大深度
	int toMaxLength; // TODO 去最大深度
	int maxLength;

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

	public int getCountRelation() {
		return countRelation;
	}

	public void setCountRelation(int countRelation) {
		this.countRelation = countRelation;
	}

	public int getFromRelation() {
		return fromRelation;
	}

	public void setFromRelation(int fromRelation) {
		this.fromRelation = fromRelation;
	}

	public int getToRelation() {
		return toRelation;
	}

	public void setToRelation(int toRelation) {
		this.toRelation = toRelation;
	}

	public int getFromMaxLength() {
		return fromMaxLength;
	}

	public void setFromMaxLength(int fromMaxLength) {
		this.fromMaxLength = fromMaxLength;
	}

	public int getToMaxLength() {
		return toMaxLength;
	}

	public void setToMaxLength(int toMaxLength) {
		this.toMaxLength = toMaxLength;
	}

	public int getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}

}
