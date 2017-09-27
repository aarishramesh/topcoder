import java.io.*;
import java.util.*;

public class FlightDataRecorder {
	public double getDistance(int[] heading, int[] distance) {
		double x = 0, y = 0;
		for (int i = 0; i < heading.length; i++) {
			if (heading[i] == 0)
				y += distance[i];
			else if (heading[i] == 90)
				x += distance[i];
			else if (heading[i] == 180)
				y += distance[i];
			else if (heading[i] == 240)
				x += distance[i];
			else if (heading[i] == 360)
				y += distance[i];
			else {
				double newX = distance[i] * Math.sin(heading[i]);
				double newY = distance[i] * Math.cos(heading[i]);
				x += newX;
				y += newY;
			}
		}
		double result = Math.sqrt((x * x) + (y * y));
		return result;
	}

// CUT begin
	public static void main(String[] args){
		System.err.println("FlightDataRecorder (250 Points)");
		System.err.println();
		HashSet<Integer> cases = new HashSet<Integer>();
        for (int i = 0; i < args.length; ++i) cases.add(Integer.parseInt(args[i]));
        runTest(cases);
	}

	static void runTest(HashSet<Integer> caseSet) {
	    int cases = 0, passed = 0;
	    while (true) {
	    	String label = Reader.nextLine();
	    	if (label == null || !label.startsWith("--"))
	    		break;

            int[] heading = new int[Integer.parseInt(Reader.nextLine())];
            for (int i = 0; i < heading.length; ++i)
                heading[i] = Integer.parseInt(Reader.nextLine());
            int[] distance = new int[Integer.parseInt(Reader.nextLine())];
            for (int i = 0; i < distance.length; ++i)
                distance[i] = Integer.parseInt(Reader.nextLine());
            Reader.nextLine();
            double __answer = Double.parseDouble(Reader.nextLine());

            cases++;
            if (caseSet.size() > 0 && !caseSet.contains(cases - 1))
                continue;
    		System.err.print(String.format("  Testcase #%d ... ", cases - 1));

            if (doTest(heading, distance, __answer))
                passed++;
	    }
	    if (caseSet.size() > 0) cases = caseSet.size();
        System.err.println(String.format("%nPassed : %d/%d cases", passed, cases));

        int T = (int)(System.currentTimeMillis() / 1000) - 1506438004;
        double PT = T / 60.0, TT = 75.0;
        System.err.println(String.format("Time   : %d minutes %d secs%nScore  : %.2f points", T / 60, T % 60, 250 * (0.3 + (0.7 * TT * TT) / (10.0 * PT * PT + TT * TT))));
	}

	static boolean doTest(int[] heading, int[] distance, double __expected) {
		long startTime = System.currentTimeMillis();
		Throwable exception = null;
		FlightDataRecorder instance = new FlightDataRecorder();
		double __result = 0.0;
		try {
			__result = instance.getDistance(heading, distance);
		}
		catch (Throwable e) { exception = e; }
		double elapsed = (System.currentTimeMillis() - startTime) / 1000.0;

		if (exception != null) {
			System.err.println("RUNTIME ERROR!");
			exception.printStackTrace();
			return false;
		}
		else if (doubleEquals(__expected, __result)) {
			System.err.println("PASSED! " + String.format("(%.2f seconds)", elapsed));
			return true;
		}
		else {
			System.err.println("FAILED! " + String.format("(%.2f seconds)", elapsed));
			System.err.println("           Expected: " + __expected);
			System.err.println("           Received: " + __result);
			return false;
		}
	}

	static boolean doubleEquals(double a, double b) {
	    return !Double.isNaN(a) && !Double.isNaN(b) && Math.abs(b - a) <= 1e-9 * Math.max(1.0, Math.abs(a) );
	}

	static class Reader {
        private static final String dataFileName = "FlightDataRecorder.sample";
	    private static BufferedReader reader;

	    public static String nextLine() {
	        try {
                if (reader == null) {
                    reader = new BufferedReader(new InputStreamReader(new FileInputStream(dataFileName)));
                }
                return reader.readLine();
	        }
	        catch (IOException e) {
	            System.err.println("FATAL!! IOException");
	            e.printStackTrace();
	            System.exit(1);
	        }
	        return "";
	    }
	}
// CUT end
}
