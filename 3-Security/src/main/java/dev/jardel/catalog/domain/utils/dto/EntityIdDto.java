package dev.jardel.catalog.domain.utils.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class EntityIdDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
}
