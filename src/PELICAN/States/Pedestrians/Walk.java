/**
 * The state for pedestrians walking
 * Joseph Vretenar 101234613
 * Due: March 2nd, 2024
 */

package PELICAN.States.Pedestrians;

import PELICAN.Context;
import PELICAN.State;

/**
 * The type Walk.
 */
public class Walk implements State {

    private final Context context;

    /**
     * Instantiates a new Walk.
     *
     * @param context the context
     */
    public Walk(Context context) {
        this.context = context;
        context.setCurrentState(this);
        System.out.print("STATE- ");
        System.out.println(this.getClass().getSimpleName());
        System.out.print("-    entry/");
        signalPedestrians(PedestrianStates.WALK);
        startTimer(15000);
        System.out.print("-    exit/");
        stopTimer();
    }

    @Override
    public void startTimer(int timer) {
        System.out.printf("-    startTimer(%d)%n", timer);
        try {
            Thread.sleep(timer);
        } catch (InterruptedException e) {
            stopTimer();
        }
    }

    @Override
    public void stopTimer() {
        System.out.println("stopTimer()");
        context.timeout();
    }

    @Override
    public void signalPedestrians(PedestrianStates pedestrianStates) {
        this.context.setPedestrianActions(pedestrianStates);
        System.out.printf("signalPedestrians(%s)%n", context.getPedestrianActions().toString());
    }

    @Override
    public void signalVehicles(VehicleStates vehicleStates) {}
}