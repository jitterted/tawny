package com.jitterted.tawny.adapter.out.jpa;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PositionDto {
  private Long id;

  public void setId(Long id) {
    this.id = id;
  }

  @Id
  public Long getId() {
    return id;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    PositionDto that = (PositionDto) o;

    return id.equals(that.id);
  }

  @Override
  public int hashCode() {
    return id.hashCode();
  }
}
