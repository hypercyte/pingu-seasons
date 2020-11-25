package game;

import city.cs.engine.*;

/**
 * Listener for level completion at the goal
 */
public class GoalListener implements CollisionListener {
    private Game game;

    public GoalListener(Game game) {
        this.game = game;
    }

    @Override
    public void collide(CollisionEvent e) {
        Pingu pingu = game.getPlayer1();
        Panda panda = game.getPlayer2();
        if ((e.getOtherBody() == pingu || e.getOtherBody() == panda) && game.completionCheck()) {
            System.out.println("Going to next level...");
            Sound.getDing().play();
            game.proceed();
        }
    }
}
