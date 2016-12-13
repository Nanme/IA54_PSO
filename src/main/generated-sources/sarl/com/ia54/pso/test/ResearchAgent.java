package com.ia54.pso.test;

import com.ia54.pso.test.OptimumLocalFound;
import io.sarl.core.DefaultContextInteractions;
import io.sarl.core.Initialize;
import io.sarl.core.InnerContextAccess;
import io.sarl.core.Lifecycle;
import io.sarl.core.Logging;
import io.sarl.lang.annotation.ImportedCapacityFeature;
import io.sarl.lang.annotation.PerceptGuardEvaluator;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Address;
import io.sarl.lang.core.Agent;
import io.sarl.lang.core.BuiltinCapacitiesProvider;
import io.sarl.lang.core.Capacity;
import io.sarl.lang.core.EventSpace;
import io.sarl.lang.core.Scope;
import io.sarl.lang.core.Skill;
import io.sarl.util.Scopes;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import javax.inject.Inject;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Inline;
import org.eclipse.xtext.xbase.lib.Pure;

@SarlSpecification("0.4")
@SuppressWarnings("all")
public class ResearchAgent extends Agent {
  protected float x;
  
  protected float y;
  
  protected UUID adresseHolon;
  
  protected Double ValueFoundHere;
  
  @SyntheticMember
  private void $behaviorUnit$Initialize$0(final Initialize occurrence) {
    boolean _isEmpty = ((List<Object>)Conversions.doWrapArray(occurrence.parameters)).isEmpty();
    if (_isEmpty) {
      Logging _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER = this.$CAPACITY_USE$IO_SARL_CORE_LOGGING == null ? (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING = getSkill(Logging.class)) : this.$CAPACITY_USE$IO_SARL_CORE_LOGGING;
      _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER.info("Empty parameters");
    } else {
      Object _get = occurrence.parameters[0];
      float _parseFloat = Float.parseFloat(((String) _get));
      this.x = _parseFloat;
      Object _get_1 = occurrence.parameters[1];
      float _parseFloat_1 = Float.parseFloat(((String) _get_1));
      this.y = _parseFloat_1;
      Object _get_2 = occurrence.parameters[2];
      this.adresseHolon = ((UUID) _get_2);
    }
    DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER = this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null ? (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = getSkill(DefaultContextInteractions.class)) : this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS;
    OptimumLocalFound _optimumLocalFound = new OptimumLocalFound(this.x, this.y, (this.ValueFoundHere).doubleValue());
    DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER_1 = this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null ? (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = getSkill(DefaultContextInteractions.class)) : this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS;
    EventSpace _defaultSpace = _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER_1.getDefaultSpace();
    Address _address = _defaultSpace.getAddress(this.adresseHolon);
    Scope<Address> _addresses = Scopes.addresses(_address);
    _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER.emit(_optimumLocalFound, _addresses);
  }
  
  @Extension
  @ImportedCapacityFeature(DefaultContextInteractions.class)
  @SyntheticMember
  private transient DefaultContextInteractions $CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS;
  
  @Inline(value = "$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null ? (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = getSkill(DefaultContextInteractions.class)) : this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS")
  @SyntheticMember
  @Pure
  private DefaultContextInteractions $CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER() {
    if (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null) {
      this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = getSkill(DefaultContextInteractions.class);
    }
    return this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS;
  }
  
  @Extension
  @ImportedCapacityFeature(Logging.class)
  @SyntheticMember
  private transient Logging $CAPACITY_USE$IO_SARL_CORE_LOGGING;
  
  @Inline(value = "$CAPACITY_USE$IO_SARL_CORE_LOGGING == null ? (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING = getSkill(Logging.class)) : this.$CAPACITY_USE$IO_SARL_CORE_LOGGING")
  @SyntheticMember
  @Pure
  private Logging $CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER() {
    if (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING == null) {
      this.$CAPACITY_USE$IO_SARL_CORE_LOGGING = getSkill(Logging.class);
    }
    return this.$CAPACITY_USE$IO_SARL_CORE_LOGGING;
  }
  
  @Extension
  @ImportedCapacityFeature(InnerContextAccess.class)
  @SyntheticMember
  private transient InnerContextAccess $CAPACITY_USE$IO_SARL_CORE_INNERCONTEXTACCESS;
  
  @Inline(value = "$CAPACITY_USE$IO_SARL_CORE_INNERCONTEXTACCESS == null ? (this.$CAPACITY_USE$IO_SARL_CORE_INNERCONTEXTACCESS = getSkill(InnerContextAccess.class)) : this.$CAPACITY_USE$IO_SARL_CORE_INNERCONTEXTACCESS")
  @SyntheticMember
  @Pure
  private InnerContextAccess $CAPACITY_USE$IO_SARL_CORE_INNERCONTEXTACCESS$CALLER() {
    if (this.$CAPACITY_USE$IO_SARL_CORE_INNERCONTEXTACCESS == null) {
      this.$CAPACITY_USE$IO_SARL_CORE_INNERCONTEXTACCESS = getSkill(InnerContextAccess.class);
    }
    return this.$CAPACITY_USE$IO_SARL_CORE_INNERCONTEXTACCESS;
  }
  
  @Extension
  @ImportedCapacityFeature(Lifecycle.class)
  @SyntheticMember
  private transient Lifecycle $CAPACITY_USE$IO_SARL_CORE_LIFECYCLE;
  
  @Inline(value = "$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE == null ? (this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE = getSkill(Lifecycle.class)) : this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE")
  @SyntheticMember
  @Pure
  private Lifecycle $CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER() {
    if (this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE == null) {
      this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE = getSkill(Lifecycle.class);
    }
    return this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE;
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$Initialize(final Initialize occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$Initialize$0(occurrence));
  }
  
  /**
   * Construct an agent.
   * @param builtinCapacityProvider - provider of the built-in capacities.
   * @param parentID - identifier of the parent. It is the identifier of the parent agent and the enclosing contect, at the same time.
   * @param agentID - identifier of the agent. If <code>null</code> the agent identifier will be computed randomly.
   */
  @Inject
  @SyntheticMember
  public ResearchAgent(final BuiltinCapacitiesProvider builtinCapacityProvider, final UUID parentID, final UUID agentID) {
    super(builtinCapacityProvider, parentID, agentID);
  }
  
  @SyntheticMember
  @Override
  protected <S extends Skill> S $setSkill(final S skill, final Class<? extends Capacity>... capacities) {
    this.$CAPACITY_USE$IO_SARL_CORE_LOGGING = null;
    this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = null;
    this.$CAPACITY_USE$IO_SARL_CORE_INNERCONTEXTACCESS = null;
    this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE = null;
    return super.$setSkill(skill, capacities);
  }
  
  @SyntheticMember
  @Override
  protected <S extends Capacity> S clearSkill(final Class<S> capacity) {
    this.$CAPACITY_USE$IO_SARL_CORE_LOGGING = null;
    this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = null;
    this.$CAPACITY_USE$IO_SARL_CORE_INNERCONTEXTACCESS = null;
    this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE = null;
    return super.clearSkill(capacity);
  }
}
