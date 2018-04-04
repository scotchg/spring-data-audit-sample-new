package com.github.scotchg.sample.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Builder
@Table(name = "TODO")
public class TodoEntity extends BaseEntity {
    @Id
    @Column(name = "ID")
    String id;
    @Column(name = "DESCRIPTION")
    String description;
    @Column(name = "STATUS")
    String status;
}
