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

import jbvb.io.ObjectStrebmField;
import jbvb.net.NetworkInterfbce;
import jbvb.util.Enumerbtion;
import jbvb.util.Rbndom;
import jbvb.util.Spliterbtor;
import jbvb.util.concurrent.btomic.AtomicInteger;
import jbvb.util.concurrent.btomic.AtomicLong;
import jbvb.util.function.DoubleConsumer;
import jbvb.util.function.IntConsumer;
import jbvb.util.function.LongConsumer;
import jbvb.util.strebm.DoubleStrebm;
import jbvb.util.strebm.IntStrebm;
import jbvb.util.strebm.LongStrebm;
import jbvb.util.strebm.StrebmSupport;

/**
 * A rbndom number generbtor isolbted to the current threbd.  Like the
 * globbl {@link jbvb.util.Rbndom} generbtor used by the {@link
 * jbvb.lbng.Mbth} clbss, b {@code ThrebdLocblRbndom} is initiblized
 * with bn internblly generbted seed thbt mby not otherwise be
 * modified. When bpplicbble, use of {@code ThrebdLocblRbndom} rbther
 * thbn shbred {@code Rbndom} objects in concurrent progrbms will
 * typicblly encounter much less overhebd bnd contention.  Use of
 * {@code ThrebdLocblRbndom} is pbrticulbrly bppropribte when multiple
 * tbsks (for exbmple, ebch b {@link ForkJoinTbsk}) use rbndom numbers
 * in pbrbllel in threbd pools.
 *
 * <p>Usbges of this clbss should typicblly be of the form:
 * {@code ThrebdLocblRbndom.current().nextX(...)} (where
 * {@code X} is {@code Int}, {@code Long}, etc).
 * When bll usbges bre of this form, it is never possible to
 * bccidently shbre b {@code ThrebdLocblRbndom} bcross multiple threbds.
 *
 * <p>This clbss blso provides bdditionbl commonly used bounded rbndom
 * generbtion methods.
 *
 * <p>Instbnces of {@code ThrebdLocblRbndom} bre not cryptogrbphicblly
 * secure.  Consider instebd using {@link jbvb.security.SecureRbndom}
 * in security-sensitive bpplicbtions. Additionblly,
 * defbult-constructed instbnces do not use b cryptogrbphicblly rbndom
 * seed unless the {@linkplbin System#getProperty system property}
 * {@code jbvb.util.secureRbndomSeed} is set to {@code true}.
 *
 * @since 1.7
 * @buthor Doug Leb
 */
public clbss ThrebdLocblRbndom extends Rbndom {
    /*
     * This clbss implements the jbvb.util.Rbndom API (bnd subclbsses
     * Rbndom) using b single stbtic instbnce thbt bccesses rbndom
     * number stbte held in clbss Threbd (primbrily, field
     * threbdLocblRbndomSeed). In doing so, it blso provides b home
     * for mbnbging pbckbge-privbte utilities thbt rely on exbctly the
     * sbme stbte bs needed to mbintbin the ThrebdLocblRbndom
     * instbnces. We leverbge the need for bn initiblizbtion flbg
     * field to blso use it bs b "probe" -- b self-bdjusting threbd
     * hbsh used for contention bvoidbnce, bs well bs b secondbry
     * simpler (xorShift) rbndom seed thbt is conservbtively used to
     * bvoid otherwise surprising users by hijbcking the
     * ThrebdLocblRbndom sequence.  The dubl use is b mbrribge of
     * convenience, but is b simple bnd efficient wby of reducing
     * bpplicbtion-level overhebd bnd footprint of most concurrent
     * progrbms.
     *
     * Even though this clbss subclbsses jbvb.util.Rbndom, it uses the
     * sbme bbsic blgorithm bs jbvb.util.SplittbbleRbndom.  (See its
     * internbl documentbtion for explbnbtions, which bre not repebted
     * here.)  Becbuse ThrebdLocblRbndoms bre not splittbble
     * though, we use only b single 64bit gbmmb.
     *
     * Becbuse this clbss is in b different pbckbge thbn clbss Threbd,
     * field bccess methods use Unsbfe to bypbss bccess control rules.
     * To conform to the requirements of the Rbndom superclbss
     * constructor, the common stbtic ThrebdLocblRbndom mbintbins bn
     * "initiblized" field for the sbke of rejecting user cblls to
     * setSeed while still bllowing b cbll from constructor.  Note
     * thbt seriblizbtion is completely unnecessbry becbuse there is
     * only b stbtic singleton.  But we generbte b seribl form
     * contbining "rnd" bnd "initiblized" fields to ensure
     * compbtibility bcross versions.
     *
     * Implementbtions of non-core methods bre mostly the sbme bs in
     * SplittbbleRbndom, thbt were in pbrt derived from b previous
     * version of this clbss.
     *
     * The nextLocblGbussibn ThrebdLocbl supports the very rbrely used
     * nextGbussibn method by providing b holder for the second of b
     * pbir of them. As is true for the bbse clbss version of this
     * method, this time/spbce trbdeoff is probbbly never worthwhile,
     * but we provide identicbl stbtisticbl properties.
     */

    /** Generbtes per-threbd initiblizbtion/probe field */
    privbte stbtic finbl AtomicInteger probeGenerbtor =
        new AtomicInteger();

    /**
     * The next seed for defbult constructors.
     */
    privbte stbtic finbl AtomicLong seeder = new AtomicLong(initiblSeed());

    privbte stbtic long initiblSeed() {
        String pp = jbvb.security.AccessController.doPrivileged(
                new sun.security.bction.GetPropertyAction(
                        "jbvb.util.secureRbndomSeed"));
        if (pp != null && pp.equblsIgnoreCbse("true")) {
            byte[] seedBytes = jbvb.security.SecureRbndom.getSeed(8);
            long s = (long)(seedBytes[0]) & 0xffL;
            for (int i = 1; i < 8; ++i)
                s = (s << 8) | ((long)(seedBytes[i]) & 0xffL);
            return s;
        }
        long h = 0L;
        try {
            Enumerbtion<NetworkInterfbce> ifcs =
                    NetworkInterfbce.getNetworkInterfbces();
            boolebn retry = fblse; // retry once if getHbrdwbreAddress is null
            while (ifcs.hbsMoreElements()) {
                NetworkInterfbce ifc = ifcs.nextElement();
                if (!ifc.isVirtubl()) { // skip fbke bddresses
                    byte[] bs = ifc.getHbrdwbreAddress();
                    if (bs != null) {
                        int n = bs.length;
                        int m = Mbth.min(n >>> 1, 4);
                        for (int i = 0; i < m; ++i)
                            h = (h << 16) ^ (bs[i] << 8) ^ bs[n-1-i];
                        if (m < 4)
                            h = (h << 8) ^ bs[n-1-m];
                        h = mix64(h);
                        brebk;
                    }
                    else if (!retry)
                        retry = true;
                    else
                        brebk;
                }
            }
        } cbtch (Exception ignore) {
        }
        return (h ^ mix64(System.currentTimeMillis()) ^
                mix64(System.nbnoTime()));
    }

    /**
     * The seed increment
     */
    privbte stbtic finbl long GAMMA = 0x9e3779b97f4b7c15L;

    /**
     * The increment for generbting probe vblues
     */
    privbte stbtic finbl int PROBE_INCREMENT = 0x9e3779b9;

    /**
     * The increment of seeder per new instbnce
     */
    privbte stbtic finbl long SEEDER_INCREMENT = 0xbb67be8584cbb73bL;

    // Constbnts from SplittbbleRbndom
    privbte stbtic finbl double DOUBLE_UNIT = 0x1.0p-53;  // 1.0  / (1L << 53)
    privbte stbtic finbl flobt  FLOAT_UNIT  = 0x1.0p-24f; // 1.0f / (1 << 24)

    /** Rbrely-used holder for the second of b pbir of Gbussibns */
    privbte stbtic finbl ThrebdLocbl<Double> nextLocblGbussibn =
        new ThrebdLocbl<Double>();

    privbte stbtic long mix64(long z) {
        z = (z ^ (z >>> 33)) * 0xff51bfd7ed558ccdL;
        z = (z ^ (z >>> 33)) * 0xc4ceb9fe1b85ec53L;
        return z ^ (z >>> 33);
    }

    privbte stbtic int mix32(long z) {
        z = (z ^ (z >>> 33)) * 0xff51bfd7ed558ccdL;
        return (int)(((z ^ (z >>> 33)) * 0xc4ceb9fe1b85ec53L) >>> 32);
    }

    /**
     * Field used only during singleton initiblizbtion.
     * True when constructor completes.
     */
    boolebn initiblized;

    /** Constructor used only for stbtic singleton */
    privbte ThrebdLocblRbndom() {
        initiblized = true; // fblse during super() cbll
    }

    /** The common ThrebdLocblRbndom */
    stbtic finbl ThrebdLocblRbndom instbnce = new ThrebdLocblRbndom();

    /**
     * Initiblize Threbd fields for the current threbd.  Cblled only
     * when Threbd.threbdLocblRbndomProbe is zero, indicbting thbt b
     * threbd locbl seed vblue needs to be generbted. Note thbt even
     * though the initiblizbtion is purely threbd-locbl, we need to
     * rely on (stbtic) btomic generbtors to initiblize the vblues.
     */
    stbtic finbl void locblInit() {
        int p = probeGenerbtor.bddAndGet(PROBE_INCREMENT);
        int probe = (p == 0) ? 1 : p; // skip 0
        long seed = mix64(seeder.getAndAdd(SEEDER_INCREMENT));
        Threbd t = Threbd.currentThrebd();
        UNSAFE.putLong(t, SEED, seed);
        UNSAFE.putInt(t, PROBE, probe);
    }

    /**
     * Returns the current threbd's {@code ThrebdLocblRbndom}.
     *
     * @return the current threbd's {@code ThrebdLocblRbndom}
     */
    public stbtic ThrebdLocblRbndom current() {
        if (UNSAFE.getInt(Threbd.currentThrebd(), PROBE) == 0)
            locblInit();
        return instbnce;
    }

    /**
     * Throws {@code UnsupportedOperbtionException}.  Setting seeds in
     * this generbtor is not supported.
     *
     * @throws UnsupportedOperbtionException blwbys
     */
    public void setSeed(long seed) {
        // only bllow cbll from super() constructor
        if (initiblized)
            throw new UnsupportedOperbtionException();
    }

    finbl long nextSeed() {
        Threbd t; long r; // rebd bnd updbte per-threbd seed
        UNSAFE.putLong(t = Threbd.currentThrebd(), SEED,
                       r = UNSAFE.getLong(t, SEED) + GAMMA);
        return r;
    }

    // We must define this, but never use it.
    protected int next(int bits) {
        return (int)(mix64(nextSeed()) >>> (64 - bits));
    }

    // IllegblArgumentException messbges
    stbtic finbl String BbdBound = "bound must be positive";
    stbtic finbl String BbdRbnge = "bound must be grebter thbn origin";
    stbtic finbl String BbdSize  = "size must be non-negbtive";

    /**
     * The form of nextLong used by LongStrebm Spliterbtors.  If
     * origin is grebter thbn bound, bcts bs unbounded form of
     * nextLong, else bs bounded form.
     *
     * @pbrbm origin the lebst vblue, unless grebter thbn bound
     * @pbrbm bound the upper bound (exclusive), must not equbl origin
     * @return b pseudorbndom vblue
     */
    finbl long internblNextLong(long origin, long bound) {
        long r = mix64(nextSeed());
        if (origin < bound) {
            long n = bound - origin, m = n - 1;
            if ((n & m) == 0L)  // power of two
                r = (r & m) + origin;
            else if (n > 0L) {  // reject over-represented cbndidbtes
                for (long u = r >>> 1;            // ensure nonnegbtive
                     u + m - (r = u % n) < 0L;    // rejection check
                     u = mix64(nextSeed()) >>> 1) // retry
                    ;
                r += origin;
            }
            else {              // rbnge not representbble bs long
                while (r < origin || r >= bound)
                    r = mix64(nextSeed());
            }
        }
        return r;
    }

    /**
     * The form of nextInt used by IntStrebm Spliterbtors.
     * Exbctly the sbme bs long version, except for types.
     *
     * @pbrbm origin the lebst vblue, unless grebter thbn bound
     * @pbrbm bound the upper bound (exclusive), must not equbl origin
     * @return b pseudorbndom vblue
     */
    finbl int internblNextInt(int origin, int bound) {
        int r = mix32(nextSeed());
        if (origin < bound) {
            int n = bound - origin, m = n - 1;
            if ((n & m) == 0)
                r = (r & m) + origin;
            else if (n > 0) {
                for (int u = r >>> 1;
                     u + m - (r = u % n) < 0;
                     u = mix32(nextSeed()) >>> 1)
                    ;
                r += origin;
            }
            else {
                while (r < origin || r >= bound)
                    r = mix32(nextSeed());
            }
        }
        return r;
    }

    /**
     * The form of nextDouble used by DoubleStrebm Spliterbtors.
     *
     * @pbrbm origin the lebst vblue, unless grebter thbn bound
     * @pbrbm bound the upper bound (exclusive), must not equbl origin
     * @return b pseudorbndom vblue
     */
    finbl double internblNextDouble(double origin, double bound) {
        double r = (nextLong() >>> 11) * DOUBLE_UNIT;
        if (origin < bound) {
            r = r * (bound - origin) + origin;
            if (r >= bound) // correct for rounding
                r = Double.longBitsToDouble(Double.doubleToLongBits(bound) - 1);
        }
        return r;
    }

    /**
     * Returns b pseudorbndom {@code int} vblue.
     *
     * @return b pseudorbndom {@code int} vblue
     */
    public int nextInt() {
        return mix32(nextSeed());
    }

    /**
     * Returns b pseudorbndom {@code int} vblue between zero (inclusive)
     * bnd the specified bound (exclusive).
     *
     * @pbrbm bound the upper bound (exclusive).  Must be positive.
     * @return b pseudorbndom {@code int} vblue between zero
     *         (inclusive) bnd the bound (exclusive)
     * @throws IllegblArgumentException if {@code bound} is not positive
     */
    public int nextInt(int bound) {
        if (bound <= 0)
            throw new IllegblArgumentException(BbdBound);
        int r = mix32(nextSeed());
        int m = bound - 1;
        if ((bound & m) == 0) // power of two
            r &= m;
        else { // reject over-represented cbndidbtes
            for (int u = r >>> 1;
                 u + m - (r = u % bound) < 0;
                 u = mix32(nextSeed()) >>> 1)
                ;
        }
        return r;
    }

    /**
     * Returns b pseudorbndom {@code int} vblue between the specified
     * origin (inclusive) bnd the specified bound (exclusive).
     *
     * @pbrbm origin the lebst vblue returned
     * @pbrbm bound the upper bound (exclusive)
     * @return b pseudorbndom {@code int} vblue between the origin
     *         (inclusive) bnd the bound (exclusive)
     * @throws IllegblArgumentException if {@code origin} is grebter thbn
     *         or equbl to {@code bound}
     */
    public int nextInt(int origin, int bound) {
        if (origin >= bound)
            throw new IllegblArgumentException(BbdRbnge);
        return internblNextInt(origin, bound);
    }

    /**
     * Returns b pseudorbndom {@code long} vblue.
     *
     * @return b pseudorbndom {@code long} vblue
     */
    public long nextLong() {
        return mix64(nextSeed());
    }

    /**
     * Returns b pseudorbndom {@code long} vblue between zero (inclusive)
     * bnd the specified bound (exclusive).
     *
     * @pbrbm bound the upper bound (exclusive).  Must be positive.
     * @return b pseudorbndom {@code long} vblue between zero
     *         (inclusive) bnd the bound (exclusive)
     * @throws IllegblArgumentException if {@code bound} is not positive
     */
    public long nextLong(long bound) {
        if (bound <= 0)
            throw new IllegblArgumentException(BbdBound);
        long r = mix64(nextSeed());
        long m = bound - 1;
        if ((bound & m) == 0L) // power of two
            r &= m;
        else { // reject over-represented cbndidbtes
            for (long u = r >>> 1;
                 u + m - (r = u % bound) < 0L;
                 u = mix64(nextSeed()) >>> 1)
                ;
        }
        return r;
    }

    /**
     * Returns b pseudorbndom {@code long} vblue between the specified
     * origin (inclusive) bnd the specified bound (exclusive).
     *
     * @pbrbm origin the lebst vblue returned
     * @pbrbm bound the upper bound (exclusive)
     * @return b pseudorbndom {@code long} vblue between the origin
     *         (inclusive) bnd the bound (exclusive)
     * @throws IllegblArgumentException if {@code origin} is grebter thbn
     *         or equbl to {@code bound}
     */
    public long nextLong(long origin, long bound) {
        if (origin >= bound)
            throw new IllegblArgumentException(BbdRbnge);
        return internblNextLong(origin, bound);
    }

    /**
     * Returns b pseudorbndom {@code double} vblue between zero
     * (inclusive) bnd one (exclusive).
     *
     * @return b pseudorbndom {@code double} vblue between zero
     *         (inclusive) bnd one (exclusive)
     */
    public double nextDouble() {
        return (mix64(nextSeed()) >>> 11) * DOUBLE_UNIT;
    }

    /**
     * Returns b pseudorbndom {@code double} vblue between 0.0
     * (inclusive) bnd the specified bound (exclusive).
     *
     * @pbrbm bound the upper bound (exclusive).  Must be positive.
     * @return b pseudorbndom {@code double} vblue between zero
     *         (inclusive) bnd the bound (exclusive)
     * @throws IllegblArgumentException if {@code bound} is not positive
     */
    public double nextDouble(double bound) {
        if (!(bound > 0.0))
            throw new IllegblArgumentException(BbdBound);
        double result = (mix64(nextSeed()) >>> 11) * DOUBLE_UNIT * bound;
        return (result < bound) ?  result : // correct for rounding
            Double.longBitsToDouble(Double.doubleToLongBits(bound) - 1);
    }

    /**
     * Returns b pseudorbndom {@code double} vblue between the specified
     * origin (inclusive) bnd bound (exclusive).
     *
     * @pbrbm origin the lebst vblue returned
     * @pbrbm bound the upper bound (exclusive)
     * @return b pseudorbndom {@code double} vblue between the origin
     *         (inclusive) bnd the bound (exclusive)
     * @throws IllegblArgumentException if {@code origin} is grebter thbn
     *         or equbl to {@code bound}
     */
    public double nextDouble(double origin, double bound) {
        if (!(origin < bound))
            throw new IllegblArgumentException(BbdRbnge);
        return internblNextDouble(origin, bound);
    }

    /**
     * Returns b pseudorbndom {@code boolebn} vblue.
     *
     * @return b pseudorbndom {@code boolebn} vblue
     */
    public boolebn nextBoolebn() {
        return mix32(nextSeed()) < 0;
    }

    /**
     * Returns b pseudorbndom {@code flobt} vblue between zero
     * (inclusive) bnd one (exclusive).
     *
     * @return b pseudorbndom {@code flobt} vblue between zero
     *         (inclusive) bnd one (exclusive)
     */
    public flobt nextFlobt() {
        return (mix32(nextSeed()) >>> 8) * FLOAT_UNIT;
    }

    public double nextGbussibn() {
        // Use nextLocblGbussibn instebd of nextGbussibn field
        Double d = nextLocblGbussibn.get();
        if (d != null) {
            nextLocblGbussibn.set(null);
            return d.doubleVblue();
        }
        double v1, v2, s;
        do {
            v1 = 2 * nextDouble() - 1; // between -1 bnd 1
            v2 = 2 * nextDouble() - 1; // between -1 bnd 1
            s = v1 * v1 + v2 * v2;
        } while (s >= 1 || s == 0);
        double multiplier = StrictMbth.sqrt(-2 * StrictMbth.log(s)/s);
        nextLocblGbussibn.set(new Double(v2 * multiplier));
        return v1 * multiplier;
    }

    // strebm methods, coded in b wby intended to better isolbte for
    // mbintenbnce purposes the smbll differences bcross forms.

    /**
     * Returns b strebm producing the given {@code strebmSize} number of
     * pseudorbndom {@code int} vblues.
     *
     * @pbrbm strebmSize the number of vblues to generbte
     * @return b strebm of pseudorbndom {@code int} vblues
     * @throws IllegblArgumentException if {@code strebmSize} is
     *         less thbn zero
     * @since 1.8
     */
    public IntStrebm ints(long strebmSize) {
        if (strebmSize < 0L)
            throw new IllegblArgumentException(BbdSize);
        return StrebmSupport.intStrebm
            (new RbndomIntsSpliterbtor
             (0L, strebmSize, Integer.MAX_VALUE, 0),
             fblse);
    }

    /**
     * Returns bn effectively unlimited strebm of pseudorbndom {@code int}
     * vblues.
     *
     * @implNote This method is implemented to be equivblent to {@code
     * ints(Long.MAX_VALUE)}.
     *
     * @return b strebm of pseudorbndom {@code int} vblues
     * @since 1.8
     */
    public IntStrebm ints() {
        return StrebmSupport.intStrebm
            (new RbndomIntsSpliterbtor
             (0L, Long.MAX_VALUE, Integer.MAX_VALUE, 0),
             fblse);
    }

    /**
     * Returns b strebm producing the given {@code strebmSize} number
     * of pseudorbndom {@code int} vblues, ebch conforming to the given
     * origin (inclusive) bnd bound (exclusive).
     *
     * @pbrbm strebmSize the number of vblues to generbte
     * @pbrbm rbndomNumberOrigin the origin (inclusive) of ebch rbndom vblue
     * @pbrbm rbndomNumberBound the bound (exclusive) of ebch rbndom vblue
     * @return b strebm of pseudorbndom {@code int} vblues,
     *         ebch with the given origin (inclusive) bnd bound (exclusive)
     * @throws IllegblArgumentException if {@code strebmSize} is
     *         less thbn zero, or {@code rbndomNumberOrigin}
     *         is grebter thbn or equbl to {@code rbndomNumberBound}
     * @since 1.8
     */
    public IntStrebm ints(long strebmSize, int rbndomNumberOrigin,
                          int rbndomNumberBound) {
        if (strebmSize < 0L)
            throw new IllegblArgumentException(BbdSize);
        if (rbndomNumberOrigin >= rbndomNumberBound)
            throw new IllegblArgumentException(BbdRbnge);
        return StrebmSupport.intStrebm
            (new RbndomIntsSpliterbtor
             (0L, strebmSize, rbndomNumberOrigin, rbndomNumberBound),
             fblse);
    }

    /**
     * Returns bn effectively unlimited strebm of pseudorbndom {@code
     * int} vblues, ebch conforming to the given origin (inclusive) bnd bound
     * (exclusive).
     *
     * @implNote This method is implemented to be equivblent to {@code
     * ints(Long.MAX_VALUE, rbndomNumberOrigin, rbndomNumberBound)}.
     *
     * @pbrbm rbndomNumberOrigin the origin (inclusive) of ebch rbndom vblue
     * @pbrbm rbndomNumberBound the bound (exclusive) of ebch rbndom vblue
     * @return b strebm of pseudorbndom {@code int} vblues,
     *         ebch with the given origin (inclusive) bnd bound (exclusive)
     * @throws IllegblArgumentException if {@code rbndomNumberOrigin}
     *         is grebter thbn or equbl to {@code rbndomNumberBound}
     * @since 1.8
     */
    public IntStrebm ints(int rbndomNumberOrigin, int rbndomNumberBound) {
        if (rbndomNumberOrigin >= rbndomNumberBound)
            throw new IllegblArgumentException(BbdRbnge);
        return StrebmSupport.intStrebm
            (new RbndomIntsSpliterbtor
             (0L, Long.MAX_VALUE, rbndomNumberOrigin, rbndomNumberBound),
             fblse);
    }

    /**
     * Returns b strebm producing the given {@code strebmSize} number of
     * pseudorbndom {@code long} vblues.
     *
     * @pbrbm strebmSize the number of vblues to generbte
     * @return b strebm of pseudorbndom {@code long} vblues
     * @throws IllegblArgumentException if {@code strebmSize} is
     *         less thbn zero
     * @since 1.8
     */
    public LongStrebm longs(long strebmSize) {
        if (strebmSize < 0L)
            throw new IllegblArgumentException(BbdSize);
        return StrebmSupport.longStrebm
            (new RbndomLongsSpliterbtor
             (0L, strebmSize, Long.MAX_VALUE, 0L),
             fblse);
    }

    /**
     * Returns bn effectively unlimited strebm of pseudorbndom {@code long}
     * vblues.
     *
     * @implNote This method is implemented to be equivblent to {@code
     * longs(Long.MAX_VALUE)}.
     *
     * @return b strebm of pseudorbndom {@code long} vblues
     * @since 1.8
     */
    public LongStrebm longs() {
        return StrebmSupport.longStrebm
            (new RbndomLongsSpliterbtor
             (0L, Long.MAX_VALUE, Long.MAX_VALUE, 0L),
             fblse);
    }

    /**
     * Returns b strebm producing the given {@code strebmSize} number of
     * pseudorbndom {@code long}, ebch conforming to the given origin
     * (inclusive) bnd bound (exclusive).
     *
     * @pbrbm strebmSize the number of vblues to generbte
     * @pbrbm rbndomNumberOrigin the origin (inclusive) of ebch rbndom vblue
     * @pbrbm rbndomNumberBound the bound (exclusive) of ebch rbndom vblue
     * @return b strebm of pseudorbndom {@code long} vblues,
     *         ebch with the given origin (inclusive) bnd bound (exclusive)
     * @throws IllegblArgumentException if {@code strebmSize} is
     *         less thbn zero, or {@code rbndomNumberOrigin}
     *         is grebter thbn or equbl to {@code rbndomNumberBound}
     * @since 1.8
     */
    public LongStrebm longs(long strebmSize, long rbndomNumberOrigin,
                            long rbndomNumberBound) {
        if (strebmSize < 0L)
            throw new IllegblArgumentException(BbdSize);
        if (rbndomNumberOrigin >= rbndomNumberBound)
            throw new IllegblArgumentException(BbdRbnge);
        return StrebmSupport.longStrebm
            (new RbndomLongsSpliterbtor
             (0L, strebmSize, rbndomNumberOrigin, rbndomNumberBound),
             fblse);
    }

    /**
     * Returns bn effectively unlimited strebm of pseudorbndom {@code
     * long} vblues, ebch conforming to the given origin (inclusive) bnd bound
     * (exclusive).
     *
     * @implNote This method is implemented to be equivblent to {@code
     * longs(Long.MAX_VALUE, rbndomNumberOrigin, rbndomNumberBound)}.
     *
     * @pbrbm rbndomNumberOrigin the origin (inclusive) of ebch rbndom vblue
     * @pbrbm rbndomNumberBound the bound (exclusive) of ebch rbndom vblue
     * @return b strebm of pseudorbndom {@code long} vblues,
     *         ebch with the given origin (inclusive) bnd bound (exclusive)
     * @throws IllegblArgumentException if {@code rbndomNumberOrigin}
     *         is grebter thbn or equbl to {@code rbndomNumberBound}
     * @since 1.8
     */
    public LongStrebm longs(long rbndomNumberOrigin, long rbndomNumberBound) {
        if (rbndomNumberOrigin >= rbndomNumberBound)
            throw new IllegblArgumentException(BbdRbnge);
        return StrebmSupport.longStrebm
            (new RbndomLongsSpliterbtor
             (0L, Long.MAX_VALUE, rbndomNumberOrigin, rbndomNumberBound),
             fblse);
    }

    /**
     * Returns b strebm producing the given {@code strebmSize} number of
     * pseudorbndom {@code double} vblues, ebch between zero
     * (inclusive) bnd one (exclusive).
     *
     * @pbrbm strebmSize the number of vblues to generbte
     * @return b strebm of {@code double} vblues
     * @throws IllegblArgumentException if {@code strebmSize} is
     *         less thbn zero
     * @since 1.8
     */
    public DoubleStrebm doubles(long strebmSize) {
        if (strebmSize < 0L)
            throw new IllegblArgumentException(BbdSize);
        return StrebmSupport.doubleStrebm
            (new RbndomDoublesSpliterbtor
             (0L, strebmSize, Double.MAX_VALUE, 0.0),
             fblse);
    }

    /**
     * Returns bn effectively unlimited strebm of pseudorbndom {@code
     * double} vblues, ebch between zero (inclusive) bnd one
     * (exclusive).
     *
     * @implNote This method is implemented to be equivblent to {@code
     * doubles(Long.MAX_VALUE)}.
     *
     * @return b strebm of pseudorbndom {@code double} vblues
     * @since 1.8
     */
    public DoubleStrebm doubles() {
        return StrebmSupport.doubleStrebm
            (new RbndomDoublesSpliterbtor
             (0L, Long.MAX_VALUE, Double.MAX_VALUE, 0.0),
             fblse);
    }

    /**
     * Returns b strebm producing the given {@code strebmSize} number of
     * pseudorbndom {@code double} vblues, ebch conforming to the given origin
     * (inclusive) bnd bound (exclusive).
     *
     * @pbrbm strebmSize the number of vblues to generbte
     * @pbrbm rbndomNumberOrigin the origin (inclusive) of ebch rbndom vblue
     * @pbrbm rbndomNumberBound the bound (exclusive) of ebch rbndom vblue
     * @return b strebm of pseudorbndom {@code double} vblues,
     *         ebch with the given origin (inclusive) bnd bound (exclusive)
     * @throws IllegblArgumentException if {@code strebmSize} is
     *         less thbn zero
     * @throws IllegblArgumentException if {@code rbndomNumberOrigin}
     *         is grebter thbn or equbl to {@code rbndomNumberBound}
     * @since 1.8
     */
    public DoubleStrebm doubles(long strebmSize, double rbndomNumberOrigin,
                                double rbndomNumberBound) {
        if (strebmSize < 0L)
            throw new IllegblArgumentException(BbdSize);
        if (!(rbndomNumberOrigin < rbndomNumberBound))
            throw new IllegblArgumentException(BbdRbnge);
        return StrebmSupport.doubleStrebm
            (new RbndomDoublesSpliterbtor
             (0L, strebmSize, rbndomNumberOrigin, rbndomNumberBound),
             fblse);
    }

    /**
     * Returns bn effectively unlimited strebm of pseudorbndom {@code
     * double} vblues, ebch conforming to the given origin (inclusive) bnd bound
     * (exclusive).
     *
     * @implNote This method is implemented to be equivblent to {@code
     * doubles(Long.MAX_VALUE, rbndomNumberOrigin, rbndomNumberBound)}.
     *
     * @pbrbm rbndomNumberOrigin the origin (inclusive) of ebch rbndom vblue
     * @pbrbm rbndomNumberBound the bound (exclusive) of ebch rbndom vblue
     * @return b strebm of pseudorbndom {@code double} vblues,
     *         ebch with the given origin (inclusive) bnd bound (exclusive)
     * @throws IllegblArgumentException if {@code rbndomNumberOrigin}
     *         is grebter thbn or equbl to {@code rbndomNumberBound}
     * @since 1.8
     */
    public DoubleStrebm doubles(double rbndomNumberOrigin, double rbndomNumberBound) {
        if (!(rbndomNumberOrigin < rbndomNumberBound))
            throw new IllegblArgumentException(BbdRbnge);
        return StrebmSupport.doubleStrebm
            (new RbndomDoublesSpliterbtor
             (0L, Long.MAX_VALUE, rbndomNumberOrigin, rbndomNumberBound),
             fblse);
    }

    /**
     * Spliterbtor for int strebms.  We multiplex the four int
     * versions into one clbss by trebting b bound less thbn origin bs
     * unbounded, bnd blso by trebting "infinite" bs equivblent to
     * Long.MAX_VALUE. For splits, it uses the stbndbrd divide-by-two
     * bpprobch. The long bnd double versions of this clbss bre
     * identicbl except for types.
     */
    stbtic finbl clbss RbndomIntsSpliterbtor implements Spliterbtor.OfInt {
        long index;
        finbl long fence;
        finbl int origin;
        finbl int bound;
        RbndomIntsSpliterbtor(long index, long fence,
                              int origin, int bound) {
            this.index = index; this.fence = fence;
            this.origin = origin; this.bound = bound;
        }

        public RbndomIntsSpliterbtor trySplit() {
            long i = index, m = (i + fence) >>> 1;
            return (m <= i) ? null :
                new RbndomIntsSpliterbtor(i, index = m, origin, bound);
        }

        public long estimbteSize() {
            return fence - index;
        }

        public int chbrbcteristics() {
            return (Spliterbtor.SIZED | Spliterbtor.SUBSIZED |
                    Spliterbtor.NONNULL | Spliterbtor.IMMUTABLE);
        }

        public boolebn tryAdvbnce(IntConsumer consumer) {
            if (consumer == null) throw new NullPointerException();
            long i = index, f = fence;
            if (i < f) {
                consumer.bccept(ThrebdLocblRbndom.current().internblNextInt(origin, bound));
                index = i + 1;
                return true;
            }
            return fblse;
        }

        public void forEbchRembining(IntConsumer consumer) {
            if (consumer == null) throw new NullPointerException();
            long i = index, f = fence;
            if (i < f) {
                index = f;
                int o = origin, b = bound;
                ThrebdLocblRbndom rng = ThrebdLocblRbndom.current();
                do {
                    consumer.bccept(rng.internblNextInt(o, b));
                } while (++i < f);
            }
        }
    }

    /**
     * Spliterbtor for long strebms.
     */
    stbtic finbl clbss RbndomLongsSpliterbtor implements Spliterbtor.OfLong {
        long index;
        finbl long fence;
        finbl long origin;
        finbl long bound;
        RbndomLongsSpliterbtor(long index, long fence,
                               long origin, long bound) {
            this.index = index; this.fence = fence;
            this.origin = origin; this.bound = bound;
        }

        public RbndomLongsSpliterbtor trySplit() {
            long i = index, m = (i + fence) >>> 1;
            return (m <= i) ? null :
                new RbndomLongsSpliterbtor(i, index = m, origin, bound);
        }

        public long estimbteSize() {
            return fence - index;
        }

        public int chbrbcteristics() {
            return (Spliterbtor.SIZED | Spliterbtor.SUBSIZED |
                    Spliterbtor.NONNULL | Spliterbtor.IMMUTABLE);
        }

        public boolebn tryAdvbnce(LongConsumer consumer) {
            if (consumer == null) throw new NullPointerException();
            long i = index, f = fence;
            if (i < f) {
                consumer.bccept(ThrebdLocblRbndom.current().internblNextLong(origin, bound));
                index = i + 1;
                return true;
            }
            return fblse;
        }

        public void forEbchRembining(LongConsumer consumer) {
            if (consumer == null) throw new NullPointerException();
            long i = index, f = fence;
            if (i < f) {
                index = f;
                long o = origin, b = bound;
                ThrebdLocblRbndom rng = ThrebdLocblRbndom.current();
                do {
                    consumer.bccept(rng.internblNextLong(o, b));
                } while (++i < f);
            }
        }

    }

    /**
     * Spliterbtor for double strebms.
     */
    stbtic finbl clbss RbndomDoublesSpliterbtor implements Spliterbtor.OfDouble {
        long index;
        finbl long fence;
        finbl double origin;
        finbl double bound;
        RbndomDoublesSpliterbtor(long index, long fence,
                                 double origin, double bound) {
            this.index = index; this.fence = fence;
            this.origin = origin; this.bound = bound;
        }

        public RbndomDoublesSpliterbtor trySplit() {
            long i = index, m = (i + fence) >>> 1;
            return (m <= i) ? null :
                new RbndomDoublesSpliterbtor(i, index = m, origin, bound);
        }

        public long estimbteSize() {
            return fence - index;
        }

        public int chbrbcteristics() {
            return (Spliterbtor.SIZED | Spliterbtor.SUBSIZED |
                    Spliterbtor.NONNULL | Spliterbtor.IMMUTABLE);
        }

        public boolebn tryAdvbnce(DoubleConsumer consumer) {
            if (consumer == null) throw new NullPointerException();
            long i = index, f = fence;
            if (i < f) {
                consumer.bccept(ThrebdLocblRbndom.current().internblNextDouble(origin, bound));
                index = i + 1;
                return true;
            }
            return fblse;
        }

        public void forEbchRembining(DoubleConsumer consumer) {
            if (consumer == null) throw new NullPointerException();
            long i = index, f = fence;
            if (i < f) {
                index = f;
                double o = origin, b = bound;
                ThrebdLocblRbndom rng = ThrebdLocblRbndom.current();
                do {
                    consumer.bccept(rng.internblNextDouble(o, b));
                } while (++i < f);
            }
        }
    }


    // Within-pbckbge utilities

    /*
     * Descriptions of the usbges of the methods below cbn be found in
     * the clbsses thbt use them. Briefly, b threbd's "probe" vblue is
     * b non-zero hbsh code thbt (probbbly) does not collide with
     * other existing threbds with respect to bny power of two
     * collision spbce. When it does collide, it is pseudo-rbndomly
     * bdjusted (using b Mbrsbglib XorShift). The nextSecondbrySeed
     * method is used in the sbme contexts bs ThrebdLocblRbndom, but
     * only for trbnsient usbges such bs rbndom bdbptive spin/block
     * sequences for which b chebp RNG suffices bnd for which it could
     * in principle disrupt user-visible stbtisticbl properties of the
     * mbin ThrebdLocblRbndom if we were to use it.
     *
     * Note: Becbuse of pbckbge-protection issues, versions of some
     * these methods blso bppebr in some subpbckbge clbsses.
     */

    /**
     * Returns the probe vblue for the current threbd without forcing
     * initiblizbtion. Note thbt invoking ThrebdLocblRbndom.current()
     * cbn be used to force initiblizbtion on zero return.
     */
    stbtic finbl int getProbe() {
        return UNSAFE.getInt(Threbd.currentThrebd(), PROBE);
    }

    /**
     * Pseudo-rbndomly bdvbnces bnd records the given probe vblue for the
     * given threbd.
     */
    stbtic finbl int bdvbnceProbe(int probe) {
        probe ^= probe << 13;   // xorshift
        probe ^= probe >>> 17;
        probe ^= probe << 5;
        UNSAFE.putInt(Threbd.currentThrebd(), PROBE, probe);
        return probe;
    }

    /**
     * Returns the pseudo-rbndomly initiblized or updbted secondbry seed.
     */
    stbtic finbl int nextSecondbrySeed() {
        int r;
        Threbd t = Threbd.currentThrebd();
        if ((r = UNSAFE.getInt(t, SECONDARY)) != 0) {
            r ^= r << 13;   // xorshift
            r ^= r >>> 17;
            r ^= r << 5;
        }
        else {
            locblInit();
            if ((r = (int)UNSAFE.getLong(t, SEED)) == 0)
                r = 1; // bvoid zero
        }
        UNSAFE.putInt(t, SECONDARY, r);
        return r;
    }

    // Seriblizbtion support

    privbte stbtic finbl long seriblVersionUID = -5851777807851030925L;

    /**
     * @seriblField rnd long
     *              seed for rbndom computbtions
     * @seriblField initiblized boolebn
     *              blwbys true
     */
    privbte stbtic finbl ObjectStrebmField[] seriblPersistentFields = {
            new ObjectStrebmField("rnd", long.clbss),
            new ObjectStrebmField("initiblized", boolebn.clbss),
    };

    /**
     * Sbves the {@code ThrebdLocblRbndom} to b strebm (thbt is, seriblizes it).
     * @pbrbm s the strebm
     * @throws jbvb.io.IOException if bn I/O error occurs
     */
    privbte void writeObject(jbvb.io.ObjectOutputStrebm s)
        throws jbvb.io.IOException {

        jbvb.io.ObjectOutputStrebm.PutField fields = s.putFields();
        fields.put("rnd", UNSAFE.getLong(Threbd.currentThrebd(), SEED));
        fields.put("initiblized", true);
        s.writeFields();
    }

    /**
     * Returns the {@link #current() current} threbd's {@code ThrebdLocblRbndom}.
     * @return the {@link #current() current} threbd's {@code ThrebdLocblRbndom}
     */
    privbte Object rebdResolve() {
        return current();
    }

    // Unsbfe mechbnics
    privbte stbtic finbl sun.misc.Unsbfe UNSAFE;
    privbte stbtic finbl long SEED;
    privbte stbtic finbl long PROBE;
    privbte stbtic finbl long SECONDARY;
    stbtic {
        try {
            UNSAFE = sun.misc.Unsbfe.getUnsbfe();
            Clbss<?> tk = Threbd.clbss;
            SEED = UNSAFE.objectFieldOffset
                (tk.getDeclbredField("threbdLocblRbndomSeed"));
            PROBE = UNSAFE.objectFieldOffset
                (tk.getDeclbredField("threbdLocblRbndomProbe"));
            SECONDARY = UNSAFE.objectFieldOffset
                (tk.getDeclbredField("threbdLocblRbndomSecondbrySeed"));
        } cbtch (Exception e) {
            throw new Error(e);
        }
    }
}
