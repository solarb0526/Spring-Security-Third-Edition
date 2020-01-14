package com.packtpub.springsecurity.domain.acl;

import javax.persistence.*;

/*
create table acl_class (
  id bigint generated by default as identity(start with 100) not null primary key,
  class varchar_ignorecase(500) not null,
  constraint uk_acl_class unique(class) );
 */
@Entity
@Table(name = "acl_class")
public class AclClass {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "CLASS", unique = true)
    private String clazz;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }
}
