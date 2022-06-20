package com.inditex.pricing.model.base;

import java.time.LocalDateTime;

public interface PersistentObject {

	Long getId();

	void setId(Long id);

	long getVersionNumber();

	void setVersionNumber(long versionNumber);

	LocalDateTime getModificationTimestamp();

	void setModificationTimestamp(LocalDateTime modificationTimestamp);

	LocalDateTime getCreationTimestamp();

	void setCreationTimestamp(LocalDateTime creationTimestamp);

}
