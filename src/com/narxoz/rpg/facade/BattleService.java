package com.narxoz.rpg.facade;

import com.narxoz.rpg.decorator.AttackAction;
import com.narxoz.rpg.enemy.BossEnemy;
import com.narxoz.rpg.hero.HeroProfile;

import java.util.Random;

public class BattleService {
    private Random random = new Random(1L);

    public BattleService setRandomSeed(long seed) {
        this.random = new Random(seed);
        return this;
    }

    public AdventureResult battle(HeroProfile hero, BossEnemy boss, AttackAction action) {
        AdventureResult result = new AdventureResult();

        if (hero == null || boss == null || action == null) {
            result.setWinner("None");
            result.setRounds(0);
            result.addLine("Battle could not start because one of the inputs is null.");
            return result;
        }

        int rounds = 0;
        final int maxRounds = 10;

        result.addLine("Battle started: " + hero.getName() + " vs " + boss.getName());

        while (hero.isAlive() && boss.isAlive() && rounds < maxRounds) {
            rounds++;
            result.addLine("Round " + rounds);

            int heroDamage = action.getDamage() + random.nextInt(6);
            boss.takeDamage(heroDamage);
            result.addLine(hero.getName() + " uses " + action.getActionName()
                    + " and deals " + heroDamage + " damage. "
                    + boss.getName() + " HP: " + boss.getHealth());

            if (!boss.isAlive()) {
                result.setWinner("Hero");
                result.setRounds(rounds);
                result.addLine(boss.getName() + " was defeated.");
                return result;
            }

            int bossDamage = boss.getAttackPower() + random.nextInt(5);
            hero.takeDamage(bossDamage);
            result.addLine(boss.getName() + " strikes back for " + bossDamage
                    + " damage. " + hero.getName() + " HP: " + hero.getHealth());

            if (!hero.isAlive()) {
                result.setWinner("Boss");
                result.setRounds(rounds);
                result.addLine(hero.getName() + " was defeated.");
                return result;
            }
        }

        result.setRounds(rounds);

        if (hero.isAlive() && !boss.isAlive()) {
            result.setWinner("Hero");
        } else if (!hero.isAlive() && boss.isAlive()) {
            result.setWinner("Boss");
        } else if (hero.getHealth() >= boss.getHealth()) {
            result.setWinner("Hero");
            result.addLine("Max rounds reached. Hero wins by remaining health.");
        } else {
            result.setWinner("Boss");
            result.addLine("Max rounds reached. Boss wins by remaining health.");
        }

        return result;
    }
}