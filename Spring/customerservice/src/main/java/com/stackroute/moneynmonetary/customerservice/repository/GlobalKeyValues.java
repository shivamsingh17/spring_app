package com.stackroute.moneynmonetary.customerservice.repository;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "globalKeyValues")
public class GlobalKeyValues {
	@Id
	@GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    @Type(type = "org.hibernate.type.UUIDCharType")
	private UUID id;
	private String keyString;
	private String valueString;
	public GlobalKeyValues(String keyString, String valueString) {
		this.keyString = keyString;
		this.valueString = valueString;
	}
	public GlobalKeyValues() {
	}
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getKey() {
		return keyString;
	}
	public void setKey(String keyString) {
		this.keyString = keyString;
	}
	public String getValue() {
		return valueString;
	}
	public void setValue(String valueString) {
		this.valueString = valueString;
	}
}
