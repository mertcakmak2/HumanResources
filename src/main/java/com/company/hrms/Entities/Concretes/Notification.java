package com.company.hrms.Entities.Concretes;

import com.company.hrms.Core.Entitites.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "notifications")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "from_user_id", nullable = false)
    @NotNull
    private User from;

    @ManyToOne
    @JoinColumn(name = "to_user_id", nullable = false)
    @NotNull
    private User to;

    @Column(name = "created_at", updatable = false, nullable = false)
    private Date createdAt;

    private boolean isSeen = false;
}
