/**
 * The state to enable pedestrian
 * Joseph Vretenar 101234613
 * Due: March 2nd, 2024
 */

package PELICAN.States.Pedestrians;

import PELICAN.Context;
import PELICAN.State;

/**
 * The type Pedestrian enabled.
 */
public class PedestrianEnabled implements State {

    private final Context context;

    /**
     * Instantiates a new Pedestrian enabled.
     *
     * @param context the context
     */
    public PedestrianEnabled(Context context) {
        this.context = context;
        context.setCurrentState(this);
        System.out.print("STATE: ");
        System.out.println(this.getClass().getSimpleName());
        System.out.print("-    entry/");
        signalVehicles(VehicleStates.RED);
        context.setCurrentState(new Walk(context));
    }

    @Override
    public void startTimer(int timer) {}

    @Override
    public void stopTimer() {}

    @Override
    public void signalPedestrians(PedestrianStates pedestrianStates) {}

    @Override
    public void signalVehicles(VehicleStates vehicleStates) {
        this.context.setVehicleActions(vehicleStates);
        System.out.printf("signalVehicles(%s)%n", context.getVehicleActions().toString());
    }
}