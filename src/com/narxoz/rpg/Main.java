package com.narxoz.rpg;

import com.narxoz.rpg.decorator.AttackAction;
import com.narxoz.rpg.decorator.BasicAttack;
import com.narxoz.rpg.decorator.CriticalFocusDecorator;
import com.narxoz.rpg.decorator.FireRuneDecorator;
import com.narxoz.rpg.decorator.PoisonCoatingDecorator;
import com.narxoz.rpg.enemy.BossEnemy;
import com.narxoz.rpg.facade.AdventureResult;
import com.narxoz.rpg.facade.DungeonFacade;
import com.narxoz.rpg.hero.HeroProfile;

public class Main {
    public static void main(String[] args) {
        AttackAction baseAttack = new BasicAttack("Sword Slash", 20);
        AttackAction fireAttack = new FireRuneDecorator(baseAttack);
        AttackAction stackedAttack = new CriticalFocusDecorator(
                new PoisonCoatingDecorator(
                        new FireRuneDecorator(
                                new BasicAttack("Sword Slash", 20)
                        )
                )
        );

        System.out.println("=== DECORATOR DEMO ===");
        printAction(baseAttack);
        printAction(fireAttack);
        printAction(stackedAttack);

        System.out.println();
        System.out.println("=== FACADE DUNGEON RUN DEMO ===");

        HeroProfile hero = new HeroProfile("Arman", 120);
        BossEnemy boss = new BossEnemy("Ancient Dragon", 110, 18);

        DungeonFacade dungeonFacade = new DungeonFacade().setRandomSeed(7L);
        AdventureResult result = dungeonFacade.runAdventure(hero, boss, stackedAttack);

        for (String line : result.getLog()) {
            System.out.println(line);
        }

        System.out.println();
        System.out.println("=== FINAL SUMMARY ===");
        System.out.println("Winner: " + result.getWinner());
        System.out.println("Rounds: " + result.getRounds());
        System.out.println("Reward: " + result.getReward());
    }

    private static void printAction(AttackAction action) {
        System.out.println("Action: " + action.getActionName());
        System.out.println("Damage: " + action.getDamage());
        System.out.println("Effect: " + action.getEffectSummary());
        System.out.println();
    }
}