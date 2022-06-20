package com.inditex.pricing.model.base;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractPersistentObject implements PersistentObject, Serializable {

	private static final long serialVersionUID = -110807809682962623L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Version
	private long versionNumber;

	private LocalDateTime modificationTimestamp;
	private LocalDateTime creationTimestamp;

	@PrePersist
	public void onPrePersist() {
		setVersionNumber(0l);
		setCreationTimestamp(LocalDateTime.now());
		setModificationTimestamp(LocalDateTime.now());
	}

	@PreUpdate
	public void onPreUpdate() {
		setVersionNumber(versionNumber++);
		setModificationTimestamp(LocalDateTime.now());
	}

}
