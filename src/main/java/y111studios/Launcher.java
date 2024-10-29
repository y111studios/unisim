package y111studios;

public abstract class Launcher {

    public static void main(String[] args) {
        try {
            // Get the path to the java home directory
            String javaHome = System.getProperty("java.home");
            // Get the path to the java binary
            String javaBin = javaHome + "/bin/java";
            // Get the classpath
            String classpath = System.getProperty("java.class.path");
            // Get the main class entry point
            String className = "y111studios.App";

            // Build new process
            ProcessBuilder builder = new ProcessBuilder(javaBin, "-XstartOnFirstThread", "-cp",
                    classpath, className);
            builder.inheritIO();
            // Begin process
            Process process = builder.start();
            // Wait for process to finish
            process.waitFor();
            // Exit with the same status code
            System.exit(process.exitValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
