/**
 * Yellow light state
 * Joseph Vretenar 101234613
 * Due: March 2nd, 2024
 */

package PELICAN.States.Vehicles;

import PELICAN.Context;
import PELICAN.State;

/**
 * The type Yellow.
 */
public class Yellow implements State {

    private final Context context;

    /**
     * Instantiates a new Yellow.
     *
     * @param context the context
     */
    public Yellow(Context context) {
        this.context = context;
        context.setCurrentState(this);
        System.out.print("STATE: ");
        System.out.println(this.getClass().getSimpleName());
        System.out.print("-    entry/");
        signalVehicles(VehicleStates.YELLOW);
        startTimer(3000);
        System.out.print("-    exit/");
        stopTimer();
    }

    @Override
    public void startTimer(int timer) {
        System.out.printf("-    setTimer(%d)%n", timer);
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
    public void signalPedestrians(PedestrianStates pedestrianStates) {}

    @Override
    public void signalVehicles(VehicleStates vehicleStates) {
        context.setVehicleActions(vehicleStates);
        System.out.printf("signalVehicles(%s)%n",
                context.getVehicleActions().toString());
    }
}