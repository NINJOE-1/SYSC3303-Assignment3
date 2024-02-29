/**
 * Class to enable vehicles
 * Joseph Vretenar 101234613
 * Due: March 2nd, 2024
 */

package PELICAN.States.Vehicles;

import PELICAN.Context;
import PELICAN.State;

/**
 * The type Vehicle enabled.
 */
public class VehicleEnabled implements State {

    private final Context context;

    /**
     * Instantiates a new Vehicle enabled.
     *
     * @param context the context
     */
    public VehicleEnabled(Context context) {
        this.context = context;
        context.setCurrentState(this);
        System.out.print("STATE: ");
        System.out.println(this.getClass().getSimpleName());
        System.out.print("-    entry/");
        signalPedestrians(PedestrianStates.DONT_WALK);
        context.setCurrentState(new Green(context));
    }

    @Override
    public void startTimer(int timer) {}

    @Override
    public void stopTimer() {}

    @Override
    public void signalPedestrians(PedestrianStates pedestrianStates) {
        this.context.setPedestrianActions(pedestrianStates);
        System.out.printf("signalPedestrians(%s)%n", context.getPedestrianActions().toString());
    }

    @Override
    public void signalVehicles(VehicleStates vehicleStates) {}
}