/**
 * The state for vehicles with green light
 * Joseph Vretenar 101234613
 * Due: March 2nd, 2024
 */

package PELICAN.States.Vehicles;

import PELICAN.Context;
import PELICAN.State;

/**
 * The type Green.
 */
public class Green implements State {

    private final Context context;

    /**
     * Instantiates a new Green.
     *
     * @param context the context
     */
    public Green(Context context) {
        this.context = context;
        context.setCurrentState(this);
        System.out.print("STATE: ");
        System.out.println(this.getClass().getSimpleName());
        System.out.print("-    entry/");
        context.setIsNotPedestrianWaiting();
        signalVehicles(VehicleStates.GREEN);
        startTimer(10000);
        System.out.printf("-    isPedestrianWaiting=%s%n", context.getIsPedestrianWaiting());
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
        System.out.printf("signalVehicles(%s)%n", context.getVehicleActions().toString());
    }
}