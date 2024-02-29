/**
 * Context class which holds the run command
 * Joseph Vretenar 101234613
 * Due: March 2nd, 2024
 */

package PELICAN;

import PELICAN.State.PedestrianStates;
import PELICAN.State.VehicleStates;
import PELICAN.States.Operational;
import PELICAN.States.Pedestrians.PedestrianEnabled;
import PELICAN.States.Pedestrians.Flash;
import PELICAN.States.Pedestrians.Walk;
import PELICAN.States.Vehicles.VehicleEnabled;
import PELICAN.States.Vehicles.Green;
import PELICAN.States.Vehicles.GreenInt;
import PELICAN.States.Vehicles.Yellow;

/**
 * The type Context.
 */
public class Context implements Runnable {

    private final Thread thread;
    private State currentState;
    private VehicleStates vehicleStates;
    private PedestrianStates pedestrianStates;

    /**
     * The Is pedestrian waiting.
     */
    public boolean isPedestrianWaiting;

    /**
     * Instantiates a new Context.
     */
    public Context() {
        this.thread = new Thread(this);
        thread.start();

    }

    @Override
    public void run() {
        new Operational(this);
    }

    /**
     * Pedestrian waiting.
     */
    public void pedestrianWaiting() {
        System.out.println("### PEDESTRIAN_WAITING ###");
        setIsPedestrianWaiting();
        // Check if pedestrianWaiting button is pressed during vehiclesGreen state then interrupt.
        if (getCurrentState().getClass().equals(Green.class) ||
                getCurrentState().getClass().equals(GreenInt.class)) {
            thread.interrupt();
        }
    }

    /**
     * Timeout.
     */
    public void timeout() {
        System.out.println("> TIMEOUT");
        // GREEN
        if (getVehicleActions().equals(VehicleStates.GREEN)) {
            // junction point
            if (getIsPedestrianWaiting()) {
                // vehiclesYellow
                System.out.println("-    [isPedestrianWaiting]");
                new Yellow(this);
            } else {
                // vehiclesGreenInt
                System.out.println("-    [else]");
                new GreenInt(this);
            }
        }
        // YELLOW
        else if (getVehicleActions().equals(VehicleStates.YELLOW)) {
            // pedestrianEnabled
            new PedestrianEnabled(this);
        }
        // RED
        else if (getVehicleActions().equals(VehicleStates.RED)) {
            // pedestrianFlash
            State pedestriansFlash;
            if (getCurrentState().getClass().equals(Walk.class)) {
                pedestriansFlash = new Flash(this);
            } else {
                // dynamic choice point
                pedestriansFlash = getCurrentState();
                if ((((Flash) pedestriansFlash).getWalkFlashCounter()) == 0) {
                    pedestriansFlash.signalPedestrians(PedestrianStates.DONT_WALK);
                    System.out.println("-    [pedestrianFlashCtr==0]");
                    new VehicleEnabled(this);
                }
                else if (((((Flash) pedestriansFlash).getWalkFlashCounter()) & 1) == 0) {
                    System.out.println("-    [(pedestrianFlashCtr&1)==0]");
                    pedestriansFlash.signalPedestrians(PedestrianStates.DONT_WALK);
                    ((Flash) pedestriansFlash).instance();
                }
                else {
                    System.out.println("-    [else]");
                    pedestriansFlash.signalPedestrians(PedestrianStates.IDLE);
                    ((Flash) pedestriansFlash).instance();
                }
            }
        }
    }

    /**
     * Gets current state.
     *
     * @return the current state
     */
    public State getCurrentState() {
        return this.currentState;
    }

    /**
     * Sets current state.
     *
     * @param currentState the current state
     */
    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    /**
     * Gets pedestrian actions.
     *
     * @return the pedestrian actions
     */
    public PedestrianStates getPedestrianActions() {
        return this.pedestrianStates;
    }

    /**
     * Sets pedestrian actions.
     *
     * @param pedestrianStates the pedestrian states
     */
    public void setPedestrianActions(PedestrianStates pedestrianStates) {
        this.pedestrianStates = pedestrianStates;
    }

    /**
     * Gets vehicle actions.
     *
     * @return the vehicle actions
     */
    public VehicleStates getVehicleActions() {
        return this.vehicleStates;
    }

    /**
     * Sets vehicle actions.
     *
     * @param vehicleStates the vehicle states
     */
    public void setVehicleActions(VehicleStates vehicleStates) {
        this.vehicleStates = vehicleStates;
    }

    /**
     * Gets is pedestrian waiting.
     *
     * @return the is pedestrian waiting
     */
    public boolean getIsPedestrianWaiting() {
        return this.isPedestrianWaiting;
    }

    /**
     * Sets is pedestrian waiting.
     */
    public void setIsPedestrianWaiting() {
        this.isPedestrianWaiting = true;
    }

    /**
     * Sets is not pedestrian waiting.
     */
    public void setIsNotPedestrianWaiting() {
        this.isPedestrianWaiting = false;
    }
}