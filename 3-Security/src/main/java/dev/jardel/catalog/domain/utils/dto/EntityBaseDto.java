package dev.jardel.catalog.domain.utils.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class EntityBaseDto extends EntityIdDto {

    private Date createdAt;
    private Date updatedAt;
}
