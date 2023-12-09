package nextstep.common.domain;

import java.time.LocalDateTime;

public class BaseDomain {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public BaseDomain() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = null;
    }

    public BaseDomain(Long id, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
