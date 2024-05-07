package dev.jardel.catalog.domain.utils;

import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import lombok.*;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public abstract class EntityBase extends EntityId {

  // @JsonIgnore
  // @DateTimeFormat(iso = ISO.DATE_TIME)
  @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE default current_timestamp", insertable = false, updatable = false)
  private Date createdAt;

  // @JsonIgnore
  // @DateTimeFormat(iso = ISO.DATE_TIME)
  @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE default current_timestamp", insertable = false)
  private Date updatedAt;
}