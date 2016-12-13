package com.ia54.pso.test;

import com.ia54.pso.test.Solution;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Event;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author Vianney M
 * Agent's behavior and associated functions
 */
@SarlSpecification("0.4")
@SuppressWarnings("all")
public class OptimumLocalFound extends Event {
  public Solution sol;
  
  public OptimumLocalFound(final float x, final float y, final double z) {
    this.sol.x = x;
    this.sol.y = y;
    this.sol.val = z;
  }
  
  @Override
  @Pure
  @SyntheticMember
  public boolean equals(final Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    OptimumLocalFound other = (OptimumLocalFound) obj;
    if (this.sol == null) {
      if (other.sol != null)
        return false;
    } else if (!this.sol.equals(other.sol))
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((this.sol== null) ? 0 : this.sol.hashCode());
    return result;
  }
  
  /**
   * Returns a String representation of the OptimumLocalFound event's attributes only.
   */
  @SyntheticMember
  @Pure
  protected String attributesToString() {
    StringBuilder result = new StringBuilder(super.attributesToString());
    result.append("sol  = ").append(this.sol);
    return result.toString();
  }
  
  @SyntheticMember
  private final static long serialVersionUID = -2662538195L;
}
