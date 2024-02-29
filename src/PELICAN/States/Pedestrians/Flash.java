/**
 * The state for pedestrian flash which will happen before vehicles can go
 * Joseph Vretenar 101234613
 * Due: March 2nd, 2024
 */

package PELICAN.States.Pedestrians;

import PELICAN.Context;
import PELICAN.State;

/**
 * The type Flash.
 */
public class Flash implements State {

    private final Context context;
    private int walkFlashCounter;

    /**
     * Instantiates a new Flash.
     *
     * @param context the context
     */
    public Flash(Context context) {
        this.context = context;
        context.setCurrentState(this);
        System.out.print("STATE- ");
        System.out.println(this.getClass().getSimpleName());
        System.out.print("-    entry/");
        startTimer(1000);
        walkFlashCounter = 7;
        System.out.printf("-    walkFlashCounter=%d%n", walkFlashCounter);
        System.out.print("-    exit/");
        stopTimer();
    }

    /**
     * Gets walk flash counter.
     *
     * @return the walk flash counter
     */
    public int getWalkFlashCounter() {
        return this.walkFlashCounter;
    }

    /**
     * Instance.
     */
    public void instance() {
        context.setCurrentState(this);
        System.out.print("STATE: ");
        System.out.println(this.getClass().getSimpleName());
        System.out.print("-    entry/");
        startTimer(1000);
        System.out.printf("-    walkFlashCounter=%d%n", walkFlashCounter);
        System.out.print("-    exit/");
        stopTimer();
    }

    @Override
    public void startTimer(int timer) {
        System.out.printf("startTimer(%d)%n", timer);
        try {
            Thread.sleep(timer);
        } catch (InterruptedException e) {
            stopTimer();
        }
    }

    @Override
    public void stopTimer() {
        System.out.println("stopTimer()");
        walkFlashCounter--;
        context.timeout();
    }

    @Override
    public void signalPedestrians(PedestrianStates pedestrianStates) {
        this.context.setPedestrianActions(pedestrianStates);
        System.out.printf("-    signalPedestrians(%s)%n",
                context.getPedestrianActions().toString());
    }

    @Override
    public void signalVehicles(VehicleStates vehicleStates) {
        this.context.setVehicleActions(vehicleStates);
        System.out.printf("signalVehicles(%s)%n",
                context.getVehicleActions().toString());
    }
}