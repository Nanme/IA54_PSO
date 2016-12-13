package com.ia54.pso.test;

import com.ia54.pso.test.OptimumLocalFound;
import com.ia54.pso.test.Solution;
import io.sarl.core.DefaultContextInteractions;
import io.sarl.core.Initialize;
import io.sarl.core.Logging;
import io.sarl.lang.annotation.ImportedCapacityFeature;
import io.sarl.lang.annotation.PerceptGuardEvaluator;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Agent;
import io.sarl.lang.core.BuiltinCapacitiesProvider;
import io.sarl.lang.core.Capacity;
import io.sarl.lang.core.Skill;
import java.util.Collection;
import java.util.UUID;
import javax.inject.Inject;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Inline;
import org.eclipse.xtext.xbase.lib.Pure;

@SarlSpecification("0.4")
@SuppressWarnings("all")
public class HolonBoss extends Agent {
  protected Solution solutionHolon;
  
  @SyntheticMember
  private void $behaviorUnit$Initialize$0(final Initialize occurrence) {
    Logging _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER = this.$CAPACITY_USE$IO_SARL_CORE_LOGGING == null ? (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING = getSkill(Logging.class)) : this.$CAPACITY_USE$IO_SARL_CORE_LOGGING;
    _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER.info("The boss is awake");
  }
  
  @SyntheticMember
  private void $behaviorUnit$OptimumLocalFound$1(final OptimumLocalFound occurrence) {
    Logging _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER = this.$CAPACITY_USE$IO_SARL_CORE_LOGGING == null ? (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING = getSkill(Logging.class)) : this.$CAPACITY_USE$IO_SARL_CORE_LOGGING;
    float _x = occurrence.sol.getX();
    String _plus = ("Optimum Local Found at x:" + Float.valueOf(_x));
    String _plus_1 = (_plus + ", y:");
    float _y = occurrence.sol.getY();
    String _plus_2 = (_plus_1 + Float.valueOf(_y));
    String _plus_3 = (_plus_2 + " and the value is:");
    double _val = occurrence.sol.getVal();
    String _plus_4 = (_plus_3 + Double.valueOf(_val));
    _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER.info(_plus_4);
    double _val_1 = occurrence.sol.getVal();
    double _val_2 = this.solutionHolon.getVal();
    boolean _greaterThan = (_val_1 > _val_2);
    if (_greaterThan) {
    }
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
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$Initialize(final Initialize occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$Initialize$0(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$OptimumLocalFound(final OptimumLocalFound occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$OptimumLocalFound$1(occurrence));
  }
  
  /**
   * Construct an agent.
   * @param builtinCapacityProvider - provider of the built-in capacities.
   * @param parentID - identifier of the parent. It is the identifier of the parent agent and the enclosing contect, at the same time.
   * @param agentID - identifier of the agent. If <code>null</code> the agent identifier will be computed randomly.
   */
  @Inject
  @SyntheticMember
  public HolonBoss(final BuiltinCapacitiesProvider builtinCapacityProvider, final UUID parentID, final UUID agentID) {
    super(builtinCapacityProvider, parentID, agentID);
  }
  
  @SyntheticMember
  @Override
  protected <S extends Skill> S $setSkill(final S skill, final Class<? extends Capacity>... capacities) {
    this.$CAPACITY_USE$IO_SARL_CORE_LOGGING = null;
    this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = null;
    return super.$setSkill(skill, capacities);
  }
  
  @SyntheticMember
  @Override
  protected <S extends Capacity> S clearSkill(final Class<S> capacity) {
    this.$CAPACITY_USE$IO_SARL_CORE_LOGGING = null;
    this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = null;
    return super.clearSkill(capacity);
  }
}
