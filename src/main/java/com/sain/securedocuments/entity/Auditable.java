package com.sain.securedocuments.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sain.securedocuments.domain.RequestContext;
import com.sain.securedocuments.exception.ApiException;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.AlternativeJdkIdGenerator;

import java.time.LocalDate;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},allowGetters = true)
public abstract class Auditable {
    @Id
    @SequenceGenerator(name="primary_key_seq", sequenceName ="primary_key_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "primary_key_seq")
    @Column(name = "id", updatable = false)
    private Long id;

    private String referenceId= new AlternativeJdkIdGenerator().generateId().toString();
    @NotNull
    private Long createdBy;
    @NotNull
    private Long updatedBy;
    @NotNull
    @CreatedDate
    @Column(name = "created_at", nullable = false , updatable = false)
    private LocalDate createdAt;
    @CreatedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDate updatedAt;

    @PrePersist
    public void beforePersist() {
        var userId = RequestContext.getUserId();
        if(userId == null){
            throw new ApiException("Cannot persist entity without user ID in RequestContext for this thread");
        }
        setCreatedAt(LocalDate.now());
        setCreatedBy(userId);
        setUpdatedBy(userId);
        setUpdatedAt(LocalDate.now());
    }

    @PreUpdate
    public void beforeUpdate() {
        var userId = RequestContext.getUserId();
        if(userId == null){
            throw new ApiException("Cannot Update entity without user ID in RequestContext for this thread");
        }
        setUpdatedAt(LocalDate.now());
        setUpdatedBy(userId);
    }


}
