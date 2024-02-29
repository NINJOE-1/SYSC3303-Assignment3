/**
 * Class for the interrupt state of vehicles
 * Joseph Vretenar 101234613
 * Due: March 2nd, 2024
 */

package PELICAN.States.Vehicles;

import PELICAN.Context;
import PELICAN.State;

/**
 * The type Green int.
 */
public class GreenInt implements State {
    /**
     * The Context.
     */
    Context context;

    /**
     * Instantiates a new Green int.
     *
     * @param context the context
     */
    public GreenInt(Context context) {
        this.context = context;
        context.setCurrentState(this);
        System.out.print("STATE: ");
        System.out.println(this.getClass().getSimpleName());
        while (!context.getIsPedestrianWaiting()) {
            startTimer(100);
        }
        stopTimer();
    }

    @Override
    public void startTimer(int timer) {
        try {
            Thread.sleep(timer);
        } catch (InterruptedException e) {
            stopTimer();
        }
    }

    @Override
    public void stopTimer() {
        new Yellow(context);
    }

    @Override
    public void signalPedestrians(PedestrianStates pedestrianStates) {}

    @Override
    public void signalVehicles(VehicleStates vehicleStates) {}
}