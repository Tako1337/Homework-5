package com.narxoz.rpg.facade;

public class RewardService {
    public String determineReward(AdventureResult battleResult) {
        if (battleResult == null) {
            return "No reward";
        }

        if ("Hero".equalsIgnoreCase(battleResult.getWinner())) {
            if (battleResult.getRounds() <= 3) {
                return "Legendary chest, 500 gold, and a boss trophy";
            } else if (battleResult.getRounds() <= 5) {
                return "Epic chest and 300 gold";
            } else {
                return "Victory reward: 150 gold";
            }
        }

        return "No reward. The hero was defeated.";
    }
}