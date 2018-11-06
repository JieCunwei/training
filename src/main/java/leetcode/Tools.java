package leetcode;

import com.google.common.base.Stopwatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 工具集
 * Created by debug on 2018/11/06.
 */
public class Tools {
    private static final Logger logger = LoggerFactory.getLogger(Tools.class);
    private Appendable appendable;
    private Stopwatch stopwatch;
    private TimeUnit defaultTimeUnit;
    private String defaultTimeSuffix;
    private boolean logTimeCost;

    public Tools() {
        this.appendable = System.out;
        this.stopwatch = Stopwatch.createStarted();
//        this.defaultTimeUnit = TimeUnit.MILLISECONDS;
//        this.defaultTimeSuffix = "ms";
//        this.defaultTimeUnit = TimeUnit.MICROSECONDS;
//        this.defaultTimeSuffix = " micro seconds";
        this.defaultTimeUnit = TimeUnit.NANOSECONDS;
        this.defaultTimeSuffix = " nano seconds";
        this.logTimeCost = true;
    }

    public Tools(Appendable appendable) {
        this();
        if (appendable != null) {
            this.appendable = appendable;
        }
    }

    public Tools logTimeCost() {
        logger.info("Time cost {}{}.", stopwatch.elapsed(defaultTimeUnit), defaultTimeSuffix);
        return this;
    }

    public Tools logTimeCostAndReset() {
        logTimeCost();
        stopwatch.reset();
        return this;
    }

    public Tools logTimeCost(TimeUnit timeUnit) {
        logger.info("Time cost {}.", stopwatch.elapsed(timeUnit));
        return this;
    }

    public Tools logTimeCostAndReset(TimeUnit timeUnit) {
        logTimeCost(timeUnit);
        stopwatch.reset();
        return this;
    }

    public Tools print(int[] list) {
        append('[');
        for (int i = 0; i < list.length; i++) {
            print(list[i]);
            if (i + 1 < list.length) {
                append(',').append(' ');
            } else {
                append(']');
            }
        }
        return this;

    }

    public Tools println(int[] x) {
        return print(x).newline();
    }

    public Tools print(boolean b) {
        return append(String.valueOf(b));
    }

    public Tools print(char c) {
        return append(String.valueOf(c));
    }

    public Tools print(int i) {
        return append(String.valueOf(i));
    }

    public Tools print(long l) {
        return append(String.valueOf(l));
    }

    public Tools print(float f) {
        return append(String.valueOf(f));
    }

    public Tools print(double d) {
        return append(String.valueOf(d));
    }

    public Tools print(char[] s) {
        return append(String.valueOf(s));
    }

    public Tools print(String s) {
        return append(s);
    }

    public Tools print(Object obj) {
        return append(String.valueOf(obj));
    }

    public Tools println() {
        return newline();
    }

    public Tools println(boolean x) {
        return print(x).newline();
    }

    public Tools println(char x) {
        return print(x).newline();
    }

    public Tools println(int x) {
        return print(x).newline();
    }

    public Tools println(long x) {
        return print(x).newline();
    }

    public Tools println(float x) {
        return print(x).newline();
    }

    public Tools println(double x) {
        return print(x).newline();
    }

    public Tools println(char[] x) {
        return print(x).newline();
    }

    public Tools println(String x) {
        return print(x).newline();
    }

    public Tools println(Object x) {
        return print(x).newline();
    }

    private Tools newline() {
        if (logTimeCost) {
            return append(System.lineSeparator()).logTimeCostAndReset();
        }
        return append(System.lineSeparator());
    }

    private Tools append(CharSequence csq) {
        try {
            appendable.append(csq);
        } catch (IOException e) {
            logger.error("print error", e);
        }
        return this;
    }

    private Tools append(CharSequence csq, int start, int end) {
        try {
            appendable.append(csq, start, end);
        } catch (IOException e) {
            logger.error("print error", e);
        }
        return this;
    }

    private Tools append(char c) {
        try {
            appendable.append(c);
        } catch (IOException e) {
            logger.error("print error", e);
        }
        return this;
    }
}
