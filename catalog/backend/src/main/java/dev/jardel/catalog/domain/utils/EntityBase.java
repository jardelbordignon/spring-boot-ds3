package dev.jardel.catalog.domain.utils;

import java.util.Date;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
//@SuppressWarnings("serial")
public abstract class EntityBase extends EntityId {

  // @JsonIgnore
  // @DateTimeFormat(iso = ISO.DATE_TIME)
  @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE default current_timestamp", insertable = false, updatable = false)
  private Date createdAt;

  // @JsonIgnore
  // @DateTimeFormat(iso = ISO.DATE_TIME)
  @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE default current_timestamp", insertable = false)
  private Date updatedAt;

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date at) {
    createdAt = at;
  }

  public Date getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Date at) {
    updatedAt = at;
  }

}