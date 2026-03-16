package com.narxoz.rpg.decorator;

public interface AttackAction {
    String getActionName();
    int getDamage();
    String getEffectSummary();
}