/**
 * Test Harness for the pedestrian crossing system
 * Joseph Vretenar 101234613
 * Due: March 2nd, 2024
 */

package PELICAN;

/**
 * The type Test harness.
 */
public class TestHarness {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws InterruptedException the interrupted exception
     */
    public static void main(String[] args) throws InterruptedException {
        Context context = new Context();

        Thread.sleep(5000);

        Thread.sleep(21000);
        context.pedestrianWaiting();

        Thread.sleep(18000);
        context.pedestrianWaiting();

        Thread.sleep(40000);
        System.out.print(">>> WAITING FOREVER FOR INTERRUPT");
    }

}