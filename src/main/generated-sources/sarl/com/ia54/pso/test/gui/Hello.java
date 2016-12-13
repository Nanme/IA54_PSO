package com.ia54.pso.test.gui;

import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Address;
import io.sarl.lang.core.Event;

/**
 * @author renaud
 */
@SarlSpecification("0.4")
@SuppressWarnings("all")
public class Hello extends Event {
  /**
   * Construct an event. The source of the event is unknown.
   */
  @SyntheticMember
  public Hello() {
    super();
  }
  
  /**
   * Construct an event.
   * @param source - address of the agent that is emitting this event.
   */
  @SyntheticMember
  public Hello(final Address source) {
    super(source);
  }
  
  @SyntheticMember
  private final static long serialVersionUID = 588368462L;
}
