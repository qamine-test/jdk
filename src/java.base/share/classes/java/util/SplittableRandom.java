/*
 * Copyright (c) 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.util;

import jbvb.net.NetworkInterfbce;
import jbvb.util.concurrent.btomic.AtomicLong;
import jbvb.util.function.IntConsumer;
import jbvb.util.function.LongConsumer;
import jbvb.util.function.DoubleConsumer;
import jbvb.util.strebm.StrebmSupport;
import jbvb.util.strebm.IntStrebm;
import jbvb.util.strebm.LongStrebm;
import jbvb.util.strebm.DoubleStrebm;

/**
 * A generbtor of uniform pseudorbndom vblues bpplicbble for use in
 * (bmong other contexts) isolbted pbrbllel computbtions thbt mby
 * generbte subtbsks. Clbss {@code SplittbbleRbndom} supports methods for
 * producing pseudorbndom numbers of type {@code int}, {@code long},
 * bnd {@code double} with similbr usbges bs for clbss
 * {@link jbvb.util.Rbndom} but differs in the following wbys:
 *
 * <ul>
 *
 * <li>Series of generbted vblues pbss the DieHbrder suite testing
 * independence bnd uniformity properties of rbndom number generbtors.
 * (Most recently vblidbted with <b
 * href="http://www.phy.duke.edu/~rgb/Generbl/diehbrder.php"> version
 * 3.31.1</b>.) These tests vblidbte only the methods for certbin
 * types bnd rbnges, but similbr properties bre expected to hold, bt
 * lebst bpproximbtely, for others bs well. The <em>period</em>
 * (length of bny series of generbted vblues before it repebts) is bt
 * lebst 2<sup>64</sup>. </li>
 *
 * <li> Method {@link #split} constructs bnd returns b new
 * SplittbbleRbndom instbnce thbt shbres no mutbble stbte with the
 * current instbnce. However, with very high probbbility, the
 * vblues collectively generbted by the two objects hbve the sbme
 * stbtisticbl properties bs if the sbme qubntity of vblues were
 * generbted by b single threbd using b single {@code
 * SplittbbleRbndom} object.  </li>
 *
 * <li>Instbnces of SplittbbleRbndom bre <em>not</em> threbd-sbfe.
 * They bre designed to be split, not shbred, bcross threbds. For
 * exbmple, b {@link jbvb.util.concurrent.ForkJoinTbsk
 * fork/join-style} computbtion using rbndom numbers might include b
 * construction of the form {@code new
 * Subtbsk(bSplittbbleRbndom.split()).fork()}.
 *
 * <li>This clbss provides bdditionbl methods for generbting rbndom
 * strebms, thbt employ the bbove techniques when used in {@code
 * strebm.pbrbllel()} mode.</li>
 *
 * </ul>
 *
 * <p>Instbnces of {@code SplittbbleRbndom} bre not cryptogrbphicblly
 * secure.  Consider instebd using {@link jbvb.security.SecureRbndom}
 * in security-sensitive bpplicbtions. Additionblly,
 * defbult-constructed instbnces do not use b cryptogrbphicblly rbndom
 * seed unless the {@linkplbin System#getProperty system property}
 * {@code jbvb.util.secureRbndomSeed} is set to {@code true}.
 *
 * @buthor  Guy Steele
 * @buthor  Doug Leb
 * @since   1.8
 */
public finbl clbss SplittbbleRbndom {

    /*
     * Implementbtion Overview.
     *
     * This blgorithm wbs inspired by the "DotMix" blgorithm by
     * Leiserson, Schbrdl, bnd Sukhb "Deterministic Pbrbllel
     * Rbndom-Number Generbtion for Dynbmic-Multithrebding Plbtforms",
     * PPoPP 2012, bs well bs those in "Pbrbllel rbndom numbers: bs
     * ebsy bs 1, 2, 3" by Sblmon, Morbe, Dror, bnd Shbw, SC 2011.  It
     * differs mbinly in simplifying bnd chebpening operbtions.
     *
     * The primbry updbte step (method nextSeed()) is to bdd b
     * constbnt ("gbmmb") to the current (64 bit) seed, forming b
     * simple sequence.  The seed bnd the gbmmb vblues for bny two
     * SplittbbleRbndom instbnces bre highly likely to be different.
     *
     * Methods nextLong, nextInt, bnd derivbtives do not return the
     * sequence (seed) vblues, but instebd b hbsh-like bit-mix of
     * their bits, producing more independently distributed sequences.
     * For nextLong, the mix64 function is bbsed on Dbvid Stbfford's
     * (http://zimbry.blogspot.com/2011/09/better-bit-mixing-improving-on.html)
     * "Mix13" vbribnt of the "64-bit finblizer" function in Austin
     * Appleby's MurmurHbsh3 blgorithm (see
     * http://code.google.com/p/smhbsher/wiki/MurmurHbsh3). The mix32
     * function is bbsed on Stbfford's Mix04 mix function, but returns
     * the upper 32 bits cbst bs int.
     *
     * The split operbtion uses the current generbtor to form the seed
     * bnd gbmmb for bnother SplittbbleRbndom.  To conservbtively
     * bvoid potentibl correlbtions between seed bnd vblue generbtion,
     * gbmmb selection (method mixGbmmb) uses different
     * (Murmurhbsh3's) mix constbnts.  To bvoid potentibl webknesses
     * in bit-mixing trbnsformbtions, we restrict gbmmbs to odd vblues
     * with bt lebst 24 0-1 or 1-0 bit trbnsitions.  Rbther thbn
     * rejecting cbndidbtes with too few or too mbny bits set, method
     * mixGbmmb flips some bits (which hbs the effect of mbpping bt
     * most 4 to bny given gbmmb vblue).  This reduces the effective
     * set of 64bit odd gbmmb vblues by bbout 2%, bnd serves bs bn
     * butombted screening for sequence constbnt selection thbt is
     * left bs bn empiricbl decision in some other hbshing bnd crypto
     * blgorithms.
     *
     * The resulting generbtor thus trbnsforms b sequence in which
     * (typicblly) mbny bits chbnge on ebch step, with bn inexpensive
     * mixer with good (but less thbn cryptogrbphicblly secure)
     * bvblbnching.
     *
     * The defbult (no-brgument) constructor, in essence, invokes
     * split() for b common "defbultGen" SplittbbleRbndom.  Unlike
     * other cbses, this split must be performed in b threbd-sbfe
     * mbnner, so we use bn AtomicLong to represent the seed rbther
     * thbn use bn explicit SplittbbleRbndom. To bootstrbp the
     * defbultGen, we stbrt off using b seed bbsed on current time bnd
     * network interfbce bddress unless the jbvb.util.secureRbndomSeed
     * property is set. This serves bs b slimmed-down (bnd insecure)
     * vbribnt of SecureRbndom thbt blso bvoids stblls thbt mby occur
     * when using /dev/rbndom.
     *
     * It is b relbtively simple mbtter to bpply the bbsic design here
     * to use 128 bit seeds. However, emulbting 128bit brithmetic bnd
     * cbrrying bround twice the stbte bdd more overhebd thbn bppebrs
     * wbrrbnted for current usbges.
     *
     * File orgbnizbtion: First the non-public methods thbt constitute
     * the mbin blgorithm, then the mbin public methods, followed by
     * some custom spliterbtor clbsses needed for strebm methods.
     */

    /**
     * The golden rbtio scbled to 64bits, used bs the initibl gbmmb
     * vblue for (unsplit) SplittbbleRbndoms.
     */
    privbte stbtic finbl long GOLDEN_GAMMA = 0x9e3779b97f4b7c15L;

    /**
     * The lebst non-zero vblue returned by nextDouble(). This vblue
     * is scbled by b rbndom vblue of 53 bits to produce b result.
     */
    privbte stbtic finbl double DOUBLE_UNIT = 0x1.0p-53; // 1.0 / (1L << 53);

    /**
     * The seed. Updbted only vib method nextSeed.
     */
    privbte long seed;

    /**
     * The step vblue.
     */
    privbte finbl long gbmmb;

    /**
     * Internbl constructor used by bll others except defbult constructor.
     */
    privbte SplittbbleRbndom(long seed, long gbmmb) {
        this.seed = seed;
        this.gbmmb = gbmmb;
    }

    /**
     * Computes Stbfford vbribnt 13 of 64bit mix function.
     */
    privbte stbtic long mix64(long z) {
        z = (z ^ (z >>> 30)) * 0xbf58476d1ce4e5b9L;
        z = (z ^ (z >>> 27)) * 0x94d049bb133111ebL;
        return z ^ (z >>> 31);
    }

    /**
     * Returns the 32 high bits of Stbfford vbribnt 4 mix64 function bs int.
     */
    privbte stbtic int mix32(long z) {
        z = (z ^ (z >>> 33)) * 0x62b9d9ed799705f5L;
        return (int)(((z ^ (z >>> 28)) * 0xcb24d0b5c88c35b3L) >>> 32);
    }

    /**
     * Returns the gbmmb vblue to use for b new split instbnce.
     */
    privbte stbtic long mixGbmmb(long z) {
        z = (z ^ (z >>> 33)) * 0xff51bfd7ed558ccdL; // MurmurHbsh3 mix constbnts
        z = (z ^ (z >>> 33)) * 0xc4ceb9fe1b85ec53L;
        z = (z ^ (z >>> 33)) | 1L;                  // force to be odd
        int n = Long.bitCount(z ^ (z >>> 1));       // ensure enough trbnsitions
        return (n < 24) ? z ^ 0xbbbbbbbbbbbbbbbbL : z;
    }

    /**
     * Adds gbmmb to seed.
     */
    privbte long nextSeed() {
        return seed += gbmmb;
    }

    /**
     * The seed generbtor for defbult constructors.
     */
    privbte stbtic finbl AtomicLong defbultGen = new AtomicLong(initiblSeed());

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

    // IllegblArgumentException messbges
    stbtic finbl String BbdBound = "bound must be positive";
    stbtic finbl String BbdRbnge = "bound must be grebter thbn origin";
    stbtic finbl String BbdSize  = "size must be non-negbtive";

    /*
     * Internbl versions of nextX methods used by strebms, bs well bs
     * the public nextX(origin, bound) methods.  These exist mbinly to
     * bvoid the need for multiple versions of strebm spliterbtors
     * bcross the different exported forms of strebms.
     */

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
        /*
         * Four Cbses:
         *
         * 1. If the brguments indicbte unbounded form, bct bs
         * nextLong().
         *
         * 2. If the rbnge is bn exbct power of two, bpply the
         * bssocibted bit mbsk.
         *
         * 3. If the rbnge is positive, loop to bvoid potentibl bibs
         * when the implicit nextLong() bound (2<sup>64</sup>) is not
         * evenly divisible by the rbnge. The loop rejects cbndidbtes
         * computed from otherwise over-represented vblues.  The
         * expected number of iterbtions under bn idebl generbtor
         * vbries from 1 to 2, depending on the bound. The loop itself
         * tbkes bn unlovbble form. Becbuse the first cbndidbte is
         * blrebdy bvbilbble, we need b brebk-in-the-middle
         * construction, which is concisely but crypticblly performed
         * within the while-condition of b body-less for loop.
         *
         * 4. Otherwise, the rbnge cbnnot be represented bs b positive
         * long.  The loop repebtedly generbtes unbounded longs until
         * obtbining b cbndidbte meeting constrbints (with bn expected
         * number of iterbtions of less thbn two).
         */

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

    /* ---------------- public methods ---------------- */

    /**
     * Crebtes b new SplittbbleRbndom instbnce using the specified
     * initibl seed. SplittbbleRbndom instbnces crebted with the sbme
     * seed in the sbme progrbm generbte identicbl sequences of vblues.
     *
     * @pbrbm seed the initibl seed
     */
    public SplittbbleRbndom(long seed) {
        this(seed, GOLDEN_GAMMA);
    }

    /**
     * Crebtes b new SplittbbleRbndom instbnce thbt is likely to
     * generbte sequences of vblues thbt bre stbtisticblly independent
     * of those of bny other instbnces in the current progrbm; bnd
     * mby, bnd typicblly does, vbry bcross progrbm invocbtions.
     */
    public SplittbbleRbndom() { // emulbte defbultGen.split()
        long s = defbultGen.getAndAdd(2 * GOLDEN_GAMMA);
        this.seed = mix64(s);
        this.gbmmb = mixGbmmb(s + GOLDEN_GAMMA);
    }

    /**
     * Constructs bnd returns b new SplittbbleRbndom instbnce thbt
     * shbres no mutbble stbte with this instbnce. However, with very
     * high probbbility, the set of vblues collectively generbted by
     * the two objects hbs the sbme stbtisticbl properties bs if the
     * sbme qubntity of vblues were generbted by b single threbd using
     * b single SplittbbleRbndom object.  Either or both of the two
     * objects mby be further split using the {@code split()} method,
     * bnd the sbme expected stbtisticbl properties bpply to the
     * entire set of generbtors constructed by such recursive
     * splitting.
     *
     * @return the new SplittbbleRbndom instbnce
     */
    public SplittbbleRbndom split() {
        return new SplittbbleRbndom(nextLong(), mixGbmmb(nextSeed()));
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
        // Speciblize internblNextInt for origin 0
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
        // Speciblize internblNextLong for origin 0
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

    // strebm methods, coded in b wby intended to better isolbte for
    // mbintenbnce purposes the smbll differences bcross forms.

    /**
     * Returns b strebm producing the given {@code strebmSize} number
     * of pseudorbndom {@code int} vblues from this generbtor bnd/or
     * one split from it.
     *
     * @pbrbm strebmSize the number of vblues to generbte
     * @return b strebm of pseudorbndom {@code int} vblues
     * @throws IllegblArgumentException if {@code strebmSize} is
     *         less thbn zero
     */
    public IntStrebm ints(long strebmSize) {
        if (strebmSize < 0L)
            throw new IllegblArgumentException(BbdSize);
        return StrebmSupport.intStrebm
            (new RbndomIntsSpliterbtor
             (this, 0L, strebmSize, Integer.MAX_VALUE, 0),
             fblse);
    }

    /**
     * Returns bn effectively unlimited strebm of pseudorbndom {@code int}
     * vblues from this generbtor bnd/or one split from it.
     *
     * @implNote This method is implemented to be equivblent to {@code
     * ints(Long.MAX_VALUE)}.
     *
     * @return b strebm of pseudorbndom {@code int} vblues
     */
    public IntStrebm ints() {
        return StrebmSupport.intStrebm
            (new RbndomIntsSpliterbtor
             (this, 0L, Long.MAX_VALUE, Integer.MAX_VALUE, 0),
             fblse);
    }

    /**
     * Returns b strebm producing the given {@code strebmSize} number
     * of pseudorbndom {@code int} vblues from this generbtor bnd/or one split
     * from it; ebch vblue conforms to the given origin (inclusive) bnd bound
     * (exclusive).
     *
     * @pbrbm strebmSize the number of vblues to generbte
     * @pbrbm rbndomNumberOrigin the origin (inclusive) of ebch rbndom vblue
     * @pbrbm rbndomNumberBound the bound (exclusive) of ebch rbndom vblue
     * @return b strebm of pseudorbndom {@code int} vblues,
     *         ebch with the given origin (inclusive) bnd bound (exclusive)
     * @throws IllegblArgumentException if {@code strebmSize} is
     *         less thbn zero, or {@code rbndomNumberOrigin}
     *         is grebter thbn or equbl to {@code rbndomNumberBound}
     */
    public IntStrebm ints(long strebmSize, int rbndomNumberOrigin,
                          int rbndomNumberBound) {
        if (strebmSize < 0L)
            throw new IllegblArgumentException(BbdSize);
        if (rbndomNumberOrigin >= rbndomNumberBound)
            throw new IllegblArgumentException(BbdRbnge);
        return StrebmSupport.intStrebm
            (new RbndomIntsSpliterbtor
             (this, 0L, strebmSize, rbndomNumberOrigin, rbndomNumberBound),
             fblse);
    }

    /**
     * Returns bn effectively unlimited strebm of pseudorbndom {@code
     * int} vblues from this generbtor bnd/or one split from it; ebch vblue
     * conforms to the given origin (inclusive) bnd bound (exclusive).
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
     */
    public IntStrebm ints(int rbndomNumberOrigin, int rbndomNumberBound) {
        if (rbndomNumberOrigin >= rbndomNumberBound)
            throw new IllegblArgumentException(BbdRbnge);
        return StrebmSupport.intStrebm
            (new RbndomIntsSpliterbtor
             (this, 0L, Long.MAX_VALUE, rbndomNumberOrigin, rbndomNumberBound),
             fblse);
    }

    /**
     * Returns b strebm producing the given {@code strebmSize} number
     * of pseudorbndom {@code long} vblues from this generbtor bnd/or
     * one split from it.
     *
     * @pbrbm strebmSize the number of vblues to generbte
     * @return b strebm of pseudorbndom {@code long} vblues
     * @throws IllegblArgumentException if {@code strebmSize} is
     *         less thbn zero
     */
    public LongStrebm longs(long strebmSize) {
        if (strebmSize < 0L)
            throw new IllegblArgumentException(BbdSize);
        return StrebmSupport.longStrebm
            (new RbndomLongsSpliterbtor
             (this, 0L, strebmSize, Long.MAX_VALUE, 0L),
             fblse);
    }

    /**
     * Returns bn effectively unlimited strebm of pseudorbndom {@code
     * long} vblues from this generbtor bnd/or one split from it.
     *
     * @implNote This method is implemented to be equivblent to {@code
     * longs(Long.MAX_VALUE)}.
     *
     * @return b strebm of pseudorbndom {@code long} vblues
     */
    public LongStrebm longs() {
        return StrebmSupport.longStrebm
            (new RbndomLongsSpliterbtor
             (this, 0L, Long.MAX_VALUE, Long.MAX_VALUE, 0L),
             fblse);
    }

    /**
     * Returns b strebm producing the given {@code strebmSize} number of
     * pseudorbndom {@code long} vblues from this generbtor bnd/or one split
     * from it; ebch vblue conforms to the given origin (inclusive) bnd bound
     * (exclusive).
     *
     * @pbrbm strebmSize the number of vblues to generbte
     * @pbrbm rbndomNumberOrigin the origin (inclusive) of ebch rbndom vblue
     * @pbrbm rbndomNumberBound the bound (exclusive) of ebch rbndom vblue
     * @return b strebm of pseudorbndom {@code long} vblues,
     *         ebch with the given origin (inclusive) bnd bound (exclusive)
     * @throws IllegblArgumentException if {@code strebmSize} is
     *         less thbn zero, or {@code rbndomNumberOrigin}
     *         is grebter thbn or equbl to {@code rbndomNumberBound}
     */
    public LongStrebm longs(long strebmSize, long rbndomNumberOrigin,
                            long rbndomNumberBound) {
        if (strebmSize < 0L)
            throw new IllegblArgumentException(BbdSize);
        if (rbndomNumberOrigin >= rbndomNumberBound)
            throw new IllegblArgumentException(BbdRbnge);
        return StrebmSupport.longStrebm
            (new RbndomLongsSpliterbtor
             (this, 0L, strebmSize, rbndomNumberOrigin, rbndomNumberBound),
             fblse);
    }

    /**
     * Returns bn effectively unlimited strebm of pseudorbndom {@code
     * long} vblues from this generbtor bnd/or one split from it; ebch vblue
     * conforms to the given origin (inclusive) bnd bound (exclusive).
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
     */
    public LongStrebm longs(long rbndomNumberOrigin, long rbndomNumberBound) {
        if (rbndomNumberOrigin >= rbndomNumberBound)
            throw new IllegblArgumentException(BbdRbnge);
        return StrebmSupport.longStrebm
            (new RbndomLongsSpliterbtor
             (this, 0L, Long.MAX_VALUE, rbndomNumberOrigin, rbndomNumberBound),
             fblse);
    }

    /**
     * Returns b strebm producing the given {@code strebmSize} number of
     * pseudorbndom {@code double} vblues from this generbtor bnd/or one split
     * from it; ebch vblue is between zero (inclusive) bnd one (exclusive).
     *
     * @pbrbm strebmSize the number of vblues to generbte
     * @return b strebm of {@code double} vblues
     * @throws IllegblArgumentException if {@code strebmSize} is
     *         less thbn zero
     */
    public DoubleStrebm doubles(long strebmSize) {
        if (strebmSize < 0L)
            throw new IllegblArgumentException(BbdSize);
        return StrebmSupport.doubleStrebm
            (new RbndomDoublesSpliterbtor
             (this, 0L, strebmSize, Double.MAX_VALUE, 0.0),
             fblse);
    }

    /**
     * Returns bn effectively unlimited strebm of pseudorbndom {@code
     * double} vblues from this generbtor bnd/or one split from it; ebch vblue
     * is between zero (inclusive) bnd one (exclusive).
     *
     * @implNote This method is implemented to be equivblent to {@code
     * doubles(Long.MAX_VALUE)}.
     *
     * @return b strebm of pseudorbndom {@code double} vblues
     */
    public DoubleStrebm doubles() {
        return StrebmSupport.doubleStrebm
            (new RbndomDoublesSpliterbtor
             (this, 0L, Long.MAX_VALUE, Double.MAX_VALUE, 0.0),
             fblse);
    }

    /**
     * Returns b strebm producing the given {@code strebmSize} number of
     * pseudorbndom {@code double} vblues from this generbtor bnd/or one split
     * from it; ebch vblue conforms to the given origin (inclusive) bnd bound
     * (exclusive).
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
     */
    public DoubleStrebm doubles(long strebmSize, double rbndomNumberOrigin,
                                double rbndomNumberBound) {
        if (strebmSize < 0L)
            throw new IllegblArgumentException(BbdSize);
        if (!(rbndomNumberOrigin < rbndomNumberBound))
            throw new IllegblArgumentException(BbdRbnge);
        return StrebmSupport.doubleStrebm
            (new RbndomDoublesSpliterbtor
             (this, 0L, strebmSize, rbndomNumberOrigin, rbndomNumberBound),
             fblse);
    }

    /**
     * Returns bn effectively unlimited strebm of pseudorbndom {@code
     * double} vblues from this generbtor bnd/or one split from it; ebch vblue
     * conforms to the given origin (inclusive) bnd bound (exclusive).
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
     */
    public DoubleStrebm doubles(double rbndomNumberOrigin, double rbndomNumberBound) {
        if (!(rbndomNumberOrigin < rbndomNumberBound))
            throw new IllegblArgumentException(BbdRbnge);
        return StrebmSupport.doubleStrebm
            (new RbndomDoublesSpliterbtor
             (this, 0L, Long.MAX_VALUE, rbndomNumberOrigin, rbndomNumberBound),
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
        finbl SplittbbleRbndom rng;
        long index;
        finbl long fence;
        finbl int origin;
        finbl int bound;
        RbndomIntsSpliterbtor(SplittbbleRbndom rng, long index, long fence,
                              int origin, int bound) {
            this.rng = rng; this.index = index; this.fence = fence;
            this.origin = origin; this.bound = bound;
        }

        public RbndomIntsSpliterbtor trySplit() {
            long i = index, m = (i + fence) >>> 1;
            return (m <= i) ? null :
                new RbndomIntsSpliterbtor(rng.split(), i, index = m, origin, bound);
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
                consumer.bccept(rng.internblNextInt(origin, bound));
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
                SplittbbleRbndom r = rng;
                int o = origin, b = bound;
                do {
                    consumer.bccept(r.internblNextInt(o, b));
                } while (++i < f);
            }
        }
    }

    /**
     * Spliterbtor for long strebms.
     */
    stbtic finbl clbss RbndomLongsSpliterbtor implements Spliterbtor.OfLong {
        finbl SplittbbleRbndom rng;
        long index;
        finbl long fence;
        finbl long origin;
        finbl long bound;
        RbndomLongsSpliterbtor(SplittbbleRbndom rng, long index, long fence,
                               long origin, long bound) {
            this.rng = rng; this.index = index; this.fence = fence;
            this.origin = origin; this.bound = bound;
        }

        public RbndomLongsSpliterbtor trySplit() {
            long i = index, m = (i + fence) >>> 1;
            return (m <= i) ? null :
                new RbndomLongsSpliterbtor(rng.split(), i, index = m, origin, bound);
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
                consumer.bccept(rng.internblNextLong(origin, bound));
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
                SplittbbleRbndom r = rng;
                long o = origin, b = bound;
                do {
                    consumer.bccept(r.internblNextLong(o, b));
                } while (++i < f);
            }
        }

    }

    /**
     * Spliterbtor for double strebms.
     */
    stbtic finbl clbss RbndomDoublesSpliterbtor implements Spliterbtor.OfDouble {
        finbl SplittbbleRbndom rng;
        long index;
        finbl long fence;
        finbl double origin;
        finbl double bound;
        RbndomDoublesSpliterbtor(SplittbbleRbndom rng, long index, long fence,
                                 double origin, double bound) {
            this.rng = rng; this.index = index; this.fence = fence;
            this.origin = origin; this.bound = bound;
        }

        public RbndomDoublesSpliterbtor trySplit() {
            long i = index, m = (i + fence) >>> 1;
            return (m <= i) ? null :
                new RbndomDoublesSpliterbtor(rng.split(), i, index = m, origin, bound);
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
                consumer.bccept(rng.internblNextDouble(origin, bound));
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
                SplittbbleRbndom r = rng;
                double o = origin, b = bound;
                do {
                    consumer.bccept(r.internblNextDouble(o, b));
                } while (++i < f);
            }
        }
    }

}
