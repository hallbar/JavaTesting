package edu.osu.blackjack;

import java.util.*;

public class SubPlayer extends Player {
	// public enum ActionType {HIT , DOUBLE, STAND};

	@Override
	public ActionType getAction() {
		Random randomAction = new Random();

		int action = randomAction.nextInt(3);

		switch (action) {
			case 0:
				// System.out.println("Stand");
				return ActionType.STAND;
			case 1:
				// System.out.println("DOUBLE");
				return ActionType.DOUBLE;
			default:
				// System.out.println("HIT");
				return ActionType.HIT;
		}
	}
}