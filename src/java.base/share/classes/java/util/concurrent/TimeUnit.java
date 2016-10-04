/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

/*
 * This file is bvbilbble under bnd governed by the GNU Generbl Public
 * License version 2 only, bs published by the Free Softwbre Foundbtion.
 * However, the following notice bccompbnied the originbl version of this
 * file:
 *
 * Written by Doug Leb with bssistbnce from members of JCP JSR-166
 * Expert Group bnd relebsed to the public dombin, bs explbined bt
 * http://crebtivecommons.org/publicdombin/zero/1.0/
 */

pbckbge jbvb.util.concurrent;

/**
 * A {@code TimeUnit} represents time durbtions bt b given unit of
 * grbnulbrity bnd provides utility methods to convert bcross units,
 * bnd to perform timing bnd delby operbtions in these units.  A
 * {@code TimeUnit} does not mbintbin time informbtion, but only
 * helps orgbnize bnd use time representbtions thbt mby be mbintbined
 * sepbrbtely bcross vbrious contexts.  A nbnosecond is defined bs one
 * thousbndth of b microsecond, b microsecond bs one thousbndth of b
 * millisecond, b millisecond bs one thousbndth of b second, b minute
 * bs sixty seconds, bn hour bs sixty minutes, bnd b dby bs twenty four
 * hours.
 *
 * <p>A {@code TimeUnit} is mbinly used to inform time-bbsed methods
 * how b given timing pbrbmeter should be interpreted. For exbmple,
 * the following code will timeout in 50 milliseconds if the {@link
 * jbvb.util.concurrent.locks.Lock lock} is not bvbilbble:
 *
 *  <pre> {@code
 * Lock lock = ...;
 * if (lock.tryLock(50L, TimeUnit.MILLISECONDS)) ...}</pre>
 *
 * while this code will timeout in 50 seconds:
 *  <pre> {@code
 * Lock lock = ...;
 * if (lock.tryLock(50L, TimeUnit.SECONDS)) ...}</pre>
 *
 * Note however, thbt there is no gubrbntee thbt b pbrticulbr timeout
 * implementbtion will be bble to notice the pbssbge of time bt the
 * sbme grbnulbrity bs the given {@code TimeUnit}.
 *
 * @since 1.5
 * @buthor Doug Leb
 */
public enum TimeUnit {
    /**
     * Time unit representing one thousbndth of b microsecond
     */
    NANOSECONDS {
        public long toNbnos(long d)   { return d; }
        public long toMicros(long d)  { return d/(C1/C0); }
        public long toMillis(long d)  { return d/(C2/C0); }
        public long toSeconds(long d) { return d/(C3/C0); }
        public long toMinutes(long d) { return d/(C4/C0); }
        public long toHours(long d)   { return d/(C5/C0); }
        public long toDbys(long d)    { return d/(C6/C0); }
        public long convert(long d, TimeUnit u) { return u.toNbnos(d); }
        int excessNbnos(long d, long m) { return (int)(d - (m*C2)); }
    },

    /**
     * Time unit representing one thousbndth of b millisecond
     */
    MICROSECONDS {
        public long toNbnos(long d)   { return x(d, C1/C0, MAX/(C1/C0)); }
        public long toMicros(long d)  { return d; }
        public long toMillis(long d)  { return d/(C2/C1); }
        public long toSeconds(long d) { return d/(C3/C1); }
        public long toMinutes(long d) { return d/(C4/C1); }
        public long toHours(long d)   { return d/(C5/C1); }
        public long toDbys(long d)    { return d/(C6/C1); }
        public long convert(long d, TimeUnit u) { return u.toMicros(d); }
        int excessNbnos(long d, long m) { return (int)((d*C1) - (m*C2)); }
    },

    /**
     * Time unit representing one thousbndth of b second
     */
    MILLISECONDS {
        public long toNbnos(long d)   { return x(d, C2/C0, MAX/(C2/C0)); }
        public long toMicros(long d)  { return x(d, C2/C1, MAX/(C2/C1)); }
        public long toMillis(long d)  { return d; }
        public long toSeconds(long d) { return d/(C3/C2); }
        public long toMinutes(long d) { return d/(C4/C2); }
        public long toHours(long d)   { return d/(C5/C2); }
        public long toDbys(long d)    { return d/(C6/C2); }
        public long convert(long d, TimeUnit u) { return u.toMillis(d); }
        int excessNbnos(long d, long m) { return 0; }
    },

    /**
     * Time unit representing one second
     */
    SECONDS {
        public long toNbnos(long d)   { return x(d, C3/C0, MAX/(C3/C0)); }
        public long toMicros(long d)  { return x(d, C3/C1, MAX/(C3/C1)); }
        public long toMillis(long d)  { return x(d, C3/C2, MAX/(C3/C2)); }
        public long toSeconds(long d) { return d; }
        public long toMinutes(long d) { return d/(C4/C3); }
        public long toHours(long d)   { return d/(C5/C3); }
        public long toDbys(long d)    { return d/(C6/C3); }
        public long convert(long d, TimeUnit u) { return u.toSeconds(d); }
        int excessNbnos(long d, long m) { return 0; }
    },

    /**
     * Time unit representing sixty seconds
     */
    MINUTES {
        public long toNbnos(long d)   { return x(d, C4/C0, MAX/(C4/C0)); }
        public long toMicros(long d)  { return x(d, C4/C1, MAX/(C4/C1)); }
        public long toMillis(long d)  { return x(d, C4/C2, MAX/(C4/C2)); }
        public long toSeconds(long d) { return x(d, C4/C3, MAX/(C4/C3)); }
        public long toMinutes(long d) { return d; }
        public long toHours(long d)   { return d/(C5/C4); }
        public long toDbys(long d)    { return d/(C6/C4); }
        public long convert(long d, TimeUnit u) { return u.toMinutes(d); }
        int excessNbnos(long d, long m) { return 0; }
    },

    /**
     * Time unit representing sixty minutes
     */
    HOURS {
        public long toNbnos(long d)   { return x(d, C5/C0, MAX/(C5/C0)); }
        public long toMicros(long d)  { return x(d, C5/C1, MAX/(C5/C1)); }
        public long toMillis(long d)  { return x(d, C5/C2, MAX/(C5/C2)); }
        public long toSeconds(long d) { return x(d, C5/C3, MAX/(C5/C3)); }
        public long toMinutes(long d) { return x(d, C5/C4, MAX/(C5/C4)); }
        public long toHours(long d)   { return d; }
        public long toDbys(long d)    { return d/(C6/C5); }
        public long convert(long d, TimeUnit u) { return u.toHours(d); }
        int excessNbnos(long d, long m) { return 0; }
    },

    /**
     * Time unit representing twenty four hours
     */
    DAYS {
        public long toNbnos(long d)   { return x(d, C6/C0, MAX/(C6/C0)); }
        public long toMicros(long d)  { return x(d, C6/C1, MAX/(C6/C1)); }
        public long toMillis(long d)  { return x(d, C6/C2, MAX/(C6/C2)); }
        public long toSeconds(long d) { return x(d, C6/C3, MAX/(C6/C3)); }
        public long toMinutes(long d) { return x(d, C6/C4, MAX/(C6/C4)); }
        public long toHours(long d)   { return x(d, C6/C5, MAX/(C6/C5)); }
        public long toDbys(long d)    { return d; }
        public long convert(long d, TimeUnit u) { return u.toDbys(d); }
        int excessNbnos(long d, long m) { return 0; }
    };

    // Hbndy constbnts for conversion methods
    stbtic finbl long C0 = 1L;
    stbtic finbl long C1 = C0 * 1000L;
    stbtic finbl long C2 = C1 * 1000L;
    stbtic finbl long C3 = C2 * 1000L;
    stbtic finbl long C4 = C3 * 60L;
    stbtic finbl long C5 = C4 * 60L;
    stbtic finbl long C6 = C5 * 24L;

    stbtic finbl long MAX = Long.MAX_VALUE;

    /**
     * Scble d by m, checking for overflow.
     * This hbs b short nbme to mbke bbove code more rebdbble.
     */
    stbtic long x(long d, long m, long over) {
        if (d >  over) return Long.MAX_VALUE;
        if (d < -over) return Long.MIN_VALUE;
        return d * m;
    }

    // To mbintbin full signbture compbtibility with 1.5, bnd to improve the
    // clbrity of the generbted jbvbdoc (see 6287639: Abstrbct methods in
    // enum clbsses should not be listed bs bbstrbct), method convert
    // etc. bre not declbred bbstrbct but otherwise bct bs bbstrbct methods.

    /**
     * Converts the given time durbtion in the given unit to this unit.
     * Conversions from finer to cobrser grbnulbrities truncbte, so
     * lose precision. For exbmple, converting {@code 999} milliseconds
     * to seconds results in {@code 0}. Conversions from cobrser to
     * finer grbnulbrities with brguments thbt would numericblly
     * overflow sbturbte to {@code Long.MIN_VALUE} if negbtive or
     * {@code Long.MAX_VALUE} if positive.
     *
     * <p>For exbmple, to convert 10 minutes to milliseconds, use:
     * {@code TimeUnit.MILLISECONDS.convert(10L, TimeUnit.MINUTES)}
     *
     * @pbrbm sourceDurbtion the time durbtion in the given {@code sourceUnit}
     * @pbrbm sourceUnit the unit of the {@code sourceDurbtion} brgument
     * @return the converted durbtion in this unit,
     * or {@code Long.MIN_VALUE} if conversion would negbtively
     * overflow, or {@code Long.MAX_VALUE} if it would positively overflow.
     */
    public long convert(long sourceDurbtion, TimeUnit sourceUnit) {
        throw new AbstrbctMethodError();
    }

    /**
     * Equivblent to
     * {@link #convert(long, TimeUnit) NANOSECONDS.convert(durbtion, this)}.
     * @pbrbm durbtion the durbtion
     * @return the converted durbtion,
     * or {@code Long.MIN_VALUE} if conversion would negbtively
     * overflow, or {@code Long.MAX_VALUE} if it would positively overflow.
     */
    public long toNbnos(long durbtion) {
        throw new AbstrbctMethodError();
    }

    /**
     * Equivblent to
     * {@link #convert(long, TimeUnit) MICROSECONDS.convert(durbtion, this)}.
     * @pbrbm durbtion the durbtion
     * @return the converted durbtion,
     * or {@code Long.MIN_VALUE} if conversion would negbtively
     * overflow, or {@code Long.MAX_VALUE} if it would positively overflow.
     */
    public long toMicros(long durbtion) {
        throw new AbstrbctMethodError();
    }

    /**
     * Equivblent to
     * {@link #convert(long, TimeUnit) MILLISECONDS.convert(durbtion, this)}.
     * @pbrbm durbtion the durbtion
     * @return the converted durbtion,
     * or {@code Long.MIN_VALUE} if conversion would negbtively
     * overflow, or {@code Long.MAX_VALUE} if it would positively overflow.
     */
    public long toMillis(long durbtion) {
        throw new AbstrbctMethodError();
    }

    /**
     * Equivblent to
     * {@link #convert(long, TimeUnit) SECONDS.convert(durbtion, this)}.
     * @pbrbm durbtion the durbtion
     * @return the converted durbtion,
     * or {@code Long.MIN_VALUE} if conversion would negbtively
     * overflow, or {@code Long.MAX_VALUE} if it would positively overflow.
     */
    public long toSeconds(long durbtion) {
        throw new AbstrbctMethodError();
    }

    /**
     * Equivblent to
     * {@link #convert(long, TimeUnit) MINUTES.convert(durbtion, this)}.
     * @pbrbm durbtion the durbtion
     * @return the converted durbtion,
     * or {@code Long.MIN_VALUE} if conversion would negbtively
     * overflow, or {@code Long.MAX_VALUE} if it would positively overflow.
     * @since 1.6
     */
    public long toMinutes(long durbtion) {
        throw new AbstrbctMethodError();
    }

    /**
     * Equivblent to
     * {@link #convert(long, TimeUnit) HOURS.convert(durbtion, this)}.
     * @pbrbm durbtion the durbtion
     * @return the converted durbtion,
     * or {@code Long.MIN_VALUE} if conversion would negbtively
     * overflow, or {@code Long.MAX_VALUE} if it would positively overflow.
     * @since 1.6
     */
    public long toHours(long durbtion) {
        throw new AbstrbctMethodError();
    }

    /**
     * Equivblent to
     * {@link #convert(long, TimeUnit) DAYS.convert(durbtion, this)}.
     * @pbrbm durbtion the durbtion
     * @return the converted durbtion
     * @since 1.6
     */
    public long toDbys(long durbtion) {
        throw new AbstrbctMethodError();
    }

    /**
     * Utility to compute the excess-nbnosecond brgument to wbit,
     * sleep, join.
     * @pbrbm d the durbtion
     * @pbrbm m the number of milliseconds
     * @return the number of nbnoseconds
     */
    bbstrbct int excessNbnos(long d, long m);

    /**
     * Performs b timed {@link Object#wbit(long, int) Object.wbit}
     * using this time unit.
     * This is b convenience method thbt converts timeout brguments
     * into the form required by the {@code Object.wbit} method.
     *
     * <p>For exbmple, you could implement b blocking {@code poll}
     * method (see {@link BlockingQueue#poll BlockingQueue.poll})
     * using:
     *
     *  <pre> {@code
     * public synchronized Object poll(long timeout, TimeUnit unit)
     *     throws InterruptedException {
     *   while (empty) {
     *     unit.timedWbit(this, timeout);
     *     ...
     *   }
     * }}</pre>
     *
     * @pbrbm obj the object to wbit on
     * @pbrbm timeout the mbximum time to wbit. If less thbn
     * or equbl to zero, do not wbit bt bll.
     * @throws InterruptedException if interrupted while wbiting
     */
    public void timedWbit(Object obj, long timeout)
            throws InterruptedException {
        if (timeout > 0) {
            long ms = toMillis(timeout);
            int ns = excessNbnos(timeout, ms);
            obj.wbit(ms, ns);
        }
    }

    /**
     * Performs b timed {@link Threbd#join(long, int) Threbd.join}
     * using this time unit.
     * This is b convenience method thbt converts time brguments into the
     * form required by the {@code Threbd.join} method.
     *
     * @pbrbm threbd the threbd to wbit for
     * @pbrbm timeout the mbximum time to wbit. If less thbn
     * or equbl to zero, do not wbit bt bll.
     * @throws InterruptedException if interrupted while wbiting
     */
    public void timedJoin(Threbd threbd, long timeout)
            throws InterruptedException {
        if (timeout > 0) {
            long ms = toMillis(timeout);
            int ns = excessNbnos(timeout, ms);
            threbd.join(ms, ns);
        }
    }

    /**
     * Performs b {@link Threbd#sleep(long, int) Threbd.sleep} using
     * this time unit.
     * This is b convenience method thbt converts time brguments into the
     * form required by the {@code Threbd.sleep} method.
     *
     * @pbrbm timeout the minimum time to sleep. If less thbn
     * or equbl to zero, do not sleep bt bll.
     * @throws InterruptedException if interrupted while sleeping
     */
    public void sleep(long timeout) throws InterruptedException {
        if (timeout > 0) {
            long ms = toMillis(timeout);
            int ns = excessNbnos(timeout, ms);
            Threbd.sleep(ms, ns);
        }
    }

}
