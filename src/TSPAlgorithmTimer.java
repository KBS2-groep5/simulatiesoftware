class TSPAlgorithmTimer {
    private static long getAverageTime(TSPAlgorithm algorithm) {
        long totalTime = 0;
        for(int i = 0; i < 100; i++) {
            algorithm.solve();
            totalTime += algorithm.getSolveTime();
        }
        return totalTime / 100;
    }

    static String getHumanReadableAverageTime(TSPAlgorithm algorithm) {
        return "" + String.valueOf(getAverageTime(algorithm) / 1000) + " μs";
    }
}
