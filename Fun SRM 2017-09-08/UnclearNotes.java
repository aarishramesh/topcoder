import java.io.*;
import java.util.*;

public class UnclearNotes {
	public String isMatch(String S, String T) {
		for (int i = 0; i < S.length(); i++) {
			char a = S.charAt(i);
			char b = T.charAt(i);
			if (a == b || ((a == '0' || b == '0') && (a == 'o' || b == 'o')) || 
					((a == 'm' || b == 'm') && (a == 'n' || b == 'n'))
					|| ((a == 'l' || b == 'l' ) && (a == '1' || b == '1'))) {
				continue;
			} else {
				return "Impossible";
			}
		}
		return "Possible";
	}

	// CUT begin
	public static void main(String[] args){
		System.err.println("UnclearNotes (250 Points)");
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

			String S = Reader.nextLine();
			String T = Reader.nextLine();
			Reader.nextLine();
			String __answer = Reader.nextLine();

			cases++;
			if (caseSet.size() > 0 && !caseSet.contains(cases - 1))
				continue;
			System.err.print(String.format("  Testcase #%d ... ", cases - 1));

			if (doTest(S, T, __answer))
				passed++;
		}
		if (caseSet.size() > 0) cases = caseSet.size();
		System.err.println(String.format("%nPassed : %d/%d cases", passed, cases));

		int T = (int)(System.currentTimeMillis() / 1000) - 1504897232;
		double PT = T / 60.0, TT = 75.0;
		System.err.println(String.format("Time   : %d minutes %d secs%nScore  : %.2f points", T / 60, T % 60, 250 * (0.3 + (0.7 * TT * TT) / (10.0 * PT * PT + TT * TT))));
	}

	static boolean doTest(String S, String T, String __expected) {
		S = new String(S);
		T = new String(T);
		long startTime = System.currentTimeMillis();
		Throwable exception = null;
		UnclearNotes instance = new UnclearNotes();
		String __result = "";
		try {
			__result = instance.isMatch(S, T);
		}
		catch (Throwable e) { exception = e; }
		double elapsed = (System.currentTimeMillis() - startTime) / 1000.0;

		if (exception != null) {
			System.err.println("RUNTIME ERROR!");
			exception.printStackTrace();
			return false;
		}
		else if (__expected.equals(__result)) {
			System.err.println("PASSED! " + String.format("(%.2f seconds)", elapsed));
			return true;
		}
		else {
			System.err.println("FAILED! " + String.format("(%.2f seconds)", elapsed));
			System.err.println("           Expected: " + "\"" + __expected + "\"");
			System.err.println("           Received: " + "\"" + __result + "\"");
			return false;
		}
	}

	static class Reader {
		private static final String dataFileName = "UnclearNotes.sample";
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
