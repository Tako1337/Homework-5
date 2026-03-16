package com.narxoz.rpg.decorator;

public class PoisonCoatingDecorator extends ActionDecorator {
    public PoisonCoatingDecorator(AttackAction wrappedAction) {
        super(wrappedAction);
    }

    @Override
    public String getActionName() {
        return super.getActionName() + " + Poison";
    }

    @Override
    public int getDamage() {
        return super.getDamage() + 8;
    }

    @Override
    public String getEffectSummary() {
        return super.getEffectSummary() + ", coated with poison (+8 damage)";
    }
}