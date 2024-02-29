/**
 * Class to hold the different states for the pedestrians and vehicles
 * Joseph Vretenar 101234613
 * Due: March 2nd, 2024
 */

package PELICAN;

/**
 * The interface State.
 */
public interface State {

    /**
     * The enum Pedestrian states.
     */
    enum PedestrianStates {
        DONT_WALK, WALK, IDLE}

    /**
     * The enum Vehicle states.
     */
    enum VehicleStates {
        GREEN, YELLOW, RED}

    /**
     * Start timer.
     *
     * @param timer the timer
     */
    void startTimer(int timer);

    /**
     * Stop timer.
     */
    void stopTimer();

    /**
     * Signal pedestrians.
     *
     * @param pedestrianStates the pedestrian states
     */
    void signalPedestrians(PedestrianStates pedestrianStates);

    /**
     * Signal vehicles.
     *
     * @param vehicleStates the vehicle states
     */
    void signalVehicles(VehicleStates vehicleStates);
}