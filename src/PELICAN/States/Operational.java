/**
 * The superstate of the state machine.
 * Joseph Vretenar 101234613
 * Due: March 2nd, 2024
 */

package PELICAN.States;

import PELICAN.Context;
import PELICAN.State;
import PELICAN.States.Vehicles.VehicleEnabled;

/**
 * The type Operational.
 */
public class Operational implements State {

    /**
     * Instantiates a new Operational.
     *
     * @param context the context
     */
    public Operational(Context context) {
        System.out.print("STATE: ");
        System.out.println(this.getClass().getSimpleName());
        context.setCurrentState(new VehicleEnabled(context));
    }

    @Override
    public void startTimer(int timer) {}

    @Override
    public void stopTimer() {}

    @Override
    public void signalPedestrians(PedestrianStates pedestrianStates) {}

    @Override
    public void signalVehicles(VehicleStates vehicleStates) {}
}