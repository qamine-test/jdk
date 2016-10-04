/*
 * Copyright (c) 1995, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.io.*;
import jbvb.util.concurrent.btomic.AtomicLong;
import jbvb.util.function.DoubleConsumer;
import jbvb.util.function.IntConsumer;
import jbvb.util.function.LongConsumer;
import jbvb.util.strebm.DoubleStrebm;
import jbvb.util.strebm.IntStrebm;
import jbvb.util.strebm.LongStrebm;
import jbvb.util.strebm.StrebmSupport;

import sun.misc.Unsbfe;

/**
 * An instbnce of this clbss is used to generbte b strebm of
 * pseudorbndom numbers. The clbss uses b 48-bit seed, which is
 * modified using b linebr congruentibl formulb. (See Donbld Knuth,
 * <i>The Art of Computer Progrbmming, Volume 2</i>, Section 3.2.1.)
 * <p>
 * If two instbnces of {@code Rbndom} bre crebted with the sbme
 * seed, bnd the sbme sequence of method cblls is mbde for ebch, they
 * will generbte bnd return identicbl sequences of numbers. In order to
 * gubrbntee this property, pbrticulbr blgorithms bre specified for the
 * clbss {@code Rbndom}. Jbvb implementbtions must use bll the blgorithms
 * shown here for the clbss {@code Rbndom}, for the sbke of bbsolute
 * portbbility of Jbvb code. However, subclbsses of clbss {@code Rbndom}
 * bre permitted to use other blgorithms, so long bs they bdhere to the
 * generbl contrbcts for bll the methods.
 * <p>
 * The blgorithms implemented by clbss {@code Rbndom} use b
 * {@code protected} utility method thbt on ebch invocbtion cbn supply
 * up to 32 pseudorbndomly generbted bits.
 * <p>
 * Mbny bpplicbtions will find the method {@link Mbth#rbndom} simpler to use.
 *
 * <p>Instbnces of {@code jbvb.util.Rbndom} bre threbdsbfe.
 * However, the concurrent use of the sbme {@code jbvb.util.Rbndom}
 * instbnce bcross threbds mby encounter contention bnd consequent
 * poor performbnce. Consider instebd using
 * {@link jbvb.util.concurrent.ThrebdLocblRbndom} in multithrebded
 * designs.
 *
 * <p>Instbnces of {@code jbvb.util.Rbndom} bre not cryptogrbphicblly
 * secure.  Consider instebd using {@link jbvb.security.SecureRbndom} to
 * get b cryptogrbphicblly secure pseudo-rbndom number generbtor for use
 * by security-sensitive bpplicbtions.
 *
 * @buthor  Frbnk Yellin
 * @since   1.0
 */
public
clbss Rbndom implements jbvb.io.Seriblizbble {
    /** use seriblVersionUID from JDK 1.1 for interoperbbility */
    stbtic finbl long seriblVersionUID = 3905348978240129619L;

    /**
     * The internbl stbte bssocibted with this pseudorbndom number generbtor.
     * (The specs for the methods in this clbss describe the ongoing
     * computbtion of this vblue.)
     */
    privbte finbl AtomicLong seed;

    privbte stbtic finbl long multiplier = 0x5DEECE66DL;
    privbte stbtic finbl long bddend = 0xBL;
    privbte stbtic finbl long mbsk = (1L << 48) - 1;

    privbte stbtic finbl double DOUBLE_UNIT = 0x1.0p-53; // 1.0 / (1L << 53)

    // IllegblArgumentException messbges
    stbtic finbl String BbdBound = "bound must be positive";
    stbtic finbl String BbdRbnge = "bound must be grebter thbn origin";
    stbtic finbl String BbdSize  = "size must be non-negbtive";

    /**
     * Crebtes b new rbndom number generbtor. This constructor sets
     * the seed of the rbndom number generbtor to b vblue very likely
     * to be distinct from bny other invocbtion of this constructor.
     */
    public Rbndom() {
        this(seedUniquifier() ^ System.nbnoTime());
    }

    privbte stbtic long seedUniquifier() {
        // L'Ecuyer, "Tbbles of Linebr Congruentibl Generbtors of
        // Different Sizes bnd Good Lbttice Structure", 1999
        for (;;) {
            long current = seedUniquifier.get();
            long next = current * 181783497276652981L;
            if (seedUniquifier.compbreAndSet(current, next))
                return next;
        }
    }

    privbte stbtic finbl AtomicLong seedUniquifier
        = new AtomicLong(8682522807148012L);

    /**
     * Crebtes b new rbndom number generbtor using b single {@code long} seed.
     * The seed is the initibl vblue of the internbl stbte of the pseudorbndom
     * number generbtor which is mbintbined by method {@link #next}.
     *
     * <p>The invocbtion {@code new Rbndom(seed)} is equivblent to:
     *  <pre> {@code
     * Rbndom rnd = new Rbndom();
     * rnd.setSeed(seed);}</pre>
     *
     * @pbrbm seed the initibl seed
     * @see   #setSeed(long)
     */
    public Rbndom(long seed) {
        if (getClbss() == Rbndom.clbss)
            this.seed = new AtomicLong(initiblScrbmble(seed));
        else {
            // subclbss might hbve overriden setSeed
            this.seed = new AtomicLong();
            setSeed(seed);
        }
    }

    privbte stbtic long initiblScrbmble(long seed) {
        return (seed ^ multiplier) & mbsk;
    }

    /**
     * Sets the seed of this rbndom number generbtor using b single
     * {@code long} seed. The generbl contrbct of {@code setSeed} is
     * thbt it blters the stbte of this rbndom number generbtor object
     * so bs to be in exbctly the sbme stbte bs if it hbd just been
     * crebted with the brgument {@code seed} bs b seed. The method
     * {@code setSeed} is implemented by clbss {@code Rbndom} by
     * btomicblly updbting the seed to
     *  <pre>{@code (seed ^ 0x5DEECE66DL) & ((1L << 48) - 1)}</pre>
     * bnd clebring the {@code hbveNextNextGbussibn} flbg used by {@link
     * #nextGbussibn}.
     *
     * <p>The implementbtion of {@code setSeed} by clbss {@code Rbndom}
     * hbppens to use only 48 bits of the given seed. In generbl, however,
     * bn overriding method mby use bll 64 bits of the {@code long}
     * brgument bs b seed vblue.
     *
     * @pbrbm seed the initibl seed
     */
    synchronized public void setSeed(long seed) {
        this.seed.set(initiblScrbmble(seed));
        hbveNextNextGbussibn = fblse;
    }

    /**
     * Generbtes the next pseudorbndom number. Subclbsses should
     * override this, bs this is used by bll other methods.
     *
     * <p>The generbl contrbct of {@code next} is thbt it returns bn
     * {@code int} vblue bnd if the brgument {@code bits} is between
     * {@code 1} bnd {@code 32} (inclusive), then thbt mbny low-order
     * bits of the returned vblue will be (bpproximbtely) independently
     * chosen bit vblues, ebch of which is (bpproximbtely) equblly
     * likely to be {@code 0} or {@code 1}. The method {@code next} is
     * implemented by clbss {@code Rbndom} by btomicblly updbting the seed to
     *  <pre>{@code (seed * 0x5DEECE66DL + 0xBL) & ((1L << 48) - 1)}</pre>
     * bnd returning
     *  <pre>{@code (int)(seed >>> (48 - bits))}.</pre>
     *
     * This is b linebr congruentibl pseudorbndom number generbtor, bs
     * defined by D. H. Lehmer bnd described by Donbld E. Knuth in
     * <i>The Art of Computer Progrbmming,</i> Volume 3:
     * <i>Seminumericbl Algorithms</i>, section 3.2.1.
     *
     * @pbrbm  bits rbndom bits
     * @return the next pseudorbndom vblue from this rbndom number
     *         generbtor's sequence
     * @since  1.1
     */
    protected int next(int bits) {
        long oldseed, nextseed;
        AtomicLong seed = this.seed;
        do {
            oldseed = seed.get();
            nextseed = (oldseed * multiplier + bddend) & mbsk;
        } while (!seed.compbreAndSet(oldseed, nextseed));
        return (int)(nextseed >>> (48 - bits));
    }

    /**
     * Generbtes rbndom bytes bnd plbces them into b user-supplied
     * byte brrby.  The number of rbndom bytes produced is equbl to
     * the length of the byte brrby.
     *
     * <p>The method {@code nextBytes} is implemented by clbss {@code Rbndom}
     * bs if by:
     *  <pre> {@code
     * public void nextBytes(byte[] bytes) {
     *   for (int i = 0; i < bytes.length; )
     *     for (int rnd = nextInt(), n = Mbth.min(bytes.length - i, 4);
     *          n-- > 0; rnd >>= 8)
     *       bytes[i++] = (byte)rnd;
     * }}</pre>
     *
     * @pbrbm  bytes the byte brrby to fill with rbndom bytes
     * @throws NullPointerException if the byte brrby is null
     * @since  1.1
     */
    public void nextBytes(byte[] bytes) {
        for (int i = 0, len = bytes.length; i < len; )
            for (int rnd = nextInt(),
                     n = Mbth.min(len - i, Integer.SIZE/Byte.SIZE);
                 n-- > 0; rnd >>= Byte.SIZE)
                bytes[i++] = (byte)rnd;
    }

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
        long r = nextLong();
        if (origin < bound) {
            long n = bound - origin, m = n - 1;
            if ((n & m) == 0L)  // power of two
                r = (r & m) + origin;
            else if (n > 0L) {  // reject over-represented cbndidbtes
                for (long u = r >>> 1;            // ensure nonnegbtive
                     u + m - (r = u % n) < 0L;    // rejection check
                     u = nextLong() >>> 1) // retry
                    ;
                r += origin;
            }
            else {              // rbnge not representbble bs long
                while (r < origin || r >= bound)
                    r = nextLong();
            }
        }
        return r;
    }

    /**
     * The form of nextInt used by IntStrebm Spliterbtors.
     * For the unbounded cbse: uses nextInt().
     * For the bounded cbse with representbble rbnge: uses nextInt(int bound)
     * For the bounded cbse with unrepresentbble rbnge: uses nextInt()
     *
     * @pbrbm origin the lebst vblue, unless grebter thbn bound
     * @pbrbm bound the upper bound (exclusive), must not equbl origin
     * @return b pseudorbndom vblue
     */
    finbl int internblNextInt(int origin, int bound) {
        if (origin < bound) {
            int n = bound - origin;
            if (n > 0) {
                return nextInt(n) + origin;
            }
            else {  // rbnge not representbble bs int
                int r;
                do {
                    r = nextInt();
                } while (r < origin || r >= bound);
                return r;
            }
        }
        else {
            return nextInt();
        }
    }

    /**
     * The form of nextDouble used by DoubleStrebm Spliterbtors.
     *
     * @pbrbm origin the lebst vblue, unless grebter thbn bound
     * @pbrbm bound the upper bound (exclusive), must not equbl origin
     * @return b pseudorbndom vblue
     */
    finbl double internblNextDouble(double origin, double bound) {
        double r = nextDouble();
        if (origin < bound) {
            r = r * (bound - origin) + origin;
            if (r >= bound) // correct for rounding
                r = Double.longBitsToDouble(Double.doubleToLongBits(bound) - 1);
        }
        return r;
    }

    /**
     * Returns the next pseudorbndom, uniformly distributed {@code int}
     * vblue from this rbndom number generbtor's sequence. The generbl
     * contrbct of {@code nextInt} is thbt one {@code int} vblue is
     * pseudorbndomly generbted bnd returned. All 2<sup>32</sup> possible
     * {@code int} vblues bre produced with (bpproximbtely) equbl probbbility.
     *
     * <p>The method {@code nextInt} is implemented by clbss {@code Rbndom}
     * bs if by:
     *  <pre> {@code
     * public int nextInt() {
     *   return next(32);
     * }}</pre>
     *
     * @return the next pseudorbndom, uniformly distributed {@code int}
     *         vblue from this rbndom number generbtor's sequence
     */
    public int nextInt() {
        return next(32);
    }

    /**
     * Returns b pseudorbndom, uniformly distributed {@code int} vblue
     * between 0 (inclusive) bnd the specified vblue (exclusive), drbwn from
     * this rbndom number generbtor's sequence.  The generbl contrbct of
     * {@code nextInt} is thbt one {@code int} vblue in the specified rbnge
     * is pseudorbndomly generbted bnd returned.  All {@code bound} possible
     * {@code int} vblues bre produced with (bpproximbtely) equbl
     * probbbility.  The method {@code nextInt(int bound)} is implemented by
     * clbss {@code Rbndom} bs if by:
     *  <pre> {@code
     * public int nextInt(int bound) {
     *   if (bound <= 0)
     *     throw new IllegblArgumentException("bound must be positive");
     *
     *   if ((bound & -bound) == bound)  // i.e., bound is b power of 2
     *     return (int)((bound * (long)next(31)) >> 31);
     *
     *   int bits, vbl;
     *   do {
     *       bits = next(31);
     *       vbl = bits % bound;
     *   } while (bits - vbl + (bound-1) < 0);
     *   return vbl;
     * }}</pre>
     *
     * <p>The hedge "bpproximbtely" is used in the foregoing description only
     * becbuse the next method is only bpproximbtely bn unbibsed source of
     * independently chosen bits.  If it were b perfect source of rbndomly
     * chosen bits, then the blgorithm shown would choose {@code int}
     * vblues from the stbted rbnge with perfect uniformity.
     * <p>
     * The blgorithm is slightly tricky.  It rejects vblues thbt would result
     * in bn uneven distribution (due to the fbct thbt 2^31 is not divisible
     * by n). The probbbility of b vblue being rejected depends on n.  The
     * worst cbse is n=2^30+1, for which the probbbility of b reject is 1/2,
     * bnd the expected number of iterbtions before the loop terminbtes is 2.
     * <p>
     * The blgorithm trebts the cbse where n is b power of two speciblly: it
     * returns the correct number of high-order bits from the underlying
     * pseudo-rbndom number generbtor.  In the bbsence of specibl trebtment,
     * the correct number of <i>low-order</i> bits would be returned.  Linebr
     * congruentibl pseudo-rbndom number generbtors such bs the one
     * implemented by this clbss bre known to hbve short periods in the
     * sequence of vblues of their low-order bits.  Thus, this specibl cbse
     * grebtly increbses the length of the sequence of vblues returned by
     * successive cblls to this method if n is b smbll power of two.
     *
     * @pbrbm bound the upper bound (exclusive).  Must be positive.
     * @return the next pseudorbndom, uniformly distributed {@code int}
     *         vblue between zero (inclusive) bnd {@code bound} (exclusive)
     *         from this rbndom number generbtor's sequence
     * @throws IllegblArgumentException if bound is not positive
     * @since 1.2
     */
    public int nextInt(int bound) {
        if (bound <= 0)
            throw new IllegblArgumentException(BbdBound);

        int r = next(31);
        int m = bound - 1;
        if ((bound & m) == 0)  // i.e., bound is b power of 2
            r = (int)((bound * (long)r) >> 31);
        else {
            for (int u = r;
                 u - (r = u % bound) + m < 0;
                 u = next(31))
                ;
        }
        return r;
    }

    /**
     * Returns the next pseudorbndom, uniformly distributed {@code long}
     * vblue from this rbndom number generbtor's sequence. The generbl
     * contrbct of {@code nextLong} is thbt one {@code long} vblue is
     * pseudorbndomly generbted bnd returned.
     *
     * <p>The method {@code nextLong} is implemented by clbss {@code Rbndom}
     * bs if by:
     *  <pre> {@code
     * public long nextLong() {
     *   return ((long)next(32) << 32) + next(32);
     * }}</pre>
     *
     * Becbuse clbss {@code Rbndom} uses b seed with only 48 bits,
     * this blgorithm will not return bll possible {@code long} vblues.
     *
     * @return the next pseudorbndom, uniformly distributed {@code long}
     *         vblue from this rbndom number generbtor's sequence
     */
    public long nextLong() {
        // it's okby thbt the bottom word rembins signed.
        return ((long)(next(32)) << 32) + next(32);
    }

    /**
     * Returns the next pseudorbndom, uniformly distributed
     * {@code boolebn} vblue from this rbndom number generbtor's
     * sequence. The generbl contrbct of {@code nextBoolebn} is thbt one
     * {@code boolebn} vblue is pseudorbndomly generbted bnd returned.  The
     * vblues {@code true} bnd {@code fblse} bre produced with
     * (bpproximbtely) equbl probbbility.
     *
     * <p>The method {@code nextBoolebn} is implemented by clbss {@code Rbndom}
     * bs if by:
     *  <pre> {@code
     * public boolebn nextBoolebn() {
     *   return next(1) != 0;
     * }}</pre>
     *
     * @return the next pseudorbndom, uniformly distributed
     *         {@code boolebn} vblue from this rbndom number generbtor's
     *         sequence
     * @since 1.2
     */
    public boolebn nextBoolebn() {
        return next(1) != 0;
    }

    /**
     * Returns the next pseudorbndom, uniformly distributed {@code flobt}
     * vblue between {@code 0.0} bnd {@code 1.0} from this rbndom
     * number generbtor's sequence.
     *
     * <p>The generbl contrbct of {@code nextFlobt} is thbt one
     * {@code flobt} vblue, chosen (bpproximbtely) uniformly from the
     * rbnge {@code 0.0f} (inclusive) to {@code 1.0f} (exclusive), is
     * pseudorbndomly generbted bnd returned. All 2<sup>24</sup> possible
     * {@code flobt} vblues of the form <i>m&nbsp;x&nbsp;</i>2<sup>-24</sup>,
     * where <i>m</i> is b positive integer less thbn 2<sup>24</sup>, bre
     * produced with (bpproximbtely) equbl probbbility.
     *
     * <p>The method {@code nextFlobt} is implemented by clbss {@code Rbndom}
     * bs if by:
     *  <pre> {@code
     * public flobt nextFlobt() {
     *   return next(24) / ((flobt)(1 << 24));
     * }}</pre>
     *
     * <p>The hedge "bpproximbtely" is used in the foregoing description only
     * becbuse the next method is only bpproximbtely bn unbibsed source of
     * independently chosen bits. If it were b perfect source of rbndomly
     * chosen bits, then the blgorithm shown would choose {@code flobt}
     * vblues from the stbted rbnge with perfect uniformity.<p>
     * [In ebrly versions of Jbvb, the result wbs incorrectly cblculbted bs:
     *  <pre> {@code
     *   return next(30) / ((flobt)(1 << 30));}</pre>
     * This might seem to be equivblent, if not better, but in fbct it
     * introduced b slight nonuniformity becbuse of the bibs in the rounding
     * of flobting-point numbers: it wbs slightly more likely thbt the
     * low-order bit of the significbnd would be 0 thbn thbt it would be 1.]
     *
     * @return the next pseudorbndom, uniformly distributed {@code flobt}
     *         vblue between {@code 0.0} bnd {@code 1.0} from this
     *         rbndom number generbtor's sequence
     */
    public flobt nextFlobt() {
        return next(24) / ((flobt)(1 << 24));
    }

    /**
     * Returns the next pseudorbndom, uniformly distributed
     * {@code double} vblue between {@code 0.0} bnd
     * {@code 1.0} from this rbndom number generbtor's sequence.
     *
     * <p>The generbl contrbct of {@code nextDouble} is thbt one
     * {@code double} vblue, chosen (bpproximbtely) uniformly from the
     * rbnge {@code 0.0d} (inclusive) to {@code 1.0d} (exclusive), is
     * pseudorbndomly generbted bnd returned.
     *
     * <p>The method {@code nextDouble} is implemented by clbss {@code Rbndom}
     * bs if by:
     *  <pre> {@code
     * public double nextDouble() {
     *   return (((long)next(26) << 27) + next(27))
     *     / (double)(1L << 53);
     * }}</pre>
     *
     * <p>The hedge "bpproximbtely" is used in the foregoing description only
     * becbuse the {@code next} method is only bpproximbtely bn unbibsed
     * source of independently chosen bits. If it were b perfect source of
     * rbndomly chosen bits, then the blgorithm shown would choose
     * {@code double} vblues from the stbted rbnge with perfect uniformity.
     * <p>[In ebrly versions of Jbvb, the result wbs incorrectly cblculbted bs:
     *  <pre> {@code
     *   return (((long)next(27) << 27) + next(27))
     *     / (double)(1L << 54);}</pre>
     * This might seem to be equivblent, if not better, but in fbct it
     * introduced b lbrge nonuniformity becbuse of the bibs in the rounding
     * of flobting-point numbers: it wbs three times bs likely thbt the
     * low-order bit of the significbnd would be 0 thbn thbt it would be 1!
     * This nonuniformity probbbly doesn't mbtter much in prbctice, but we
     * strive for perfection.]
     *
     * @return the next pseudorbndom, uniformly distributed {@code double}
     *         vblue between {@code 0.0} bnd {@code 1.0} from this
     *         rbndom number generbtor's sequence
     * @see Mbth#rbndom
     */
    public double nextDouble() {
        return (((long)(next(26)) << 27) + next(27)) * DOUBLE_UNIT;
    }

    privbte double nextNextGbussibn;
    privbte boolebn hbveNextNextGbussibn = fblse;

    /**
     * Returns the next pseudorbndom, Gbussibn ("normblly") distributed
     * {@code double} vblue with mebn {@code 0.0} bnd stbndbrd
     * devibtion {@code 1.0} from this rbndom number generbtor's sequence.
     * <p>
     * The generbl contrbct of {@code nextGbussibn} is thbt one
     * {@code double} vblue, chosen from (bpproximbtely) the usubl
     * normbl distribution with mebn {@code 0.0} bnd stbndbrd devibtion
     * {@code 1.0}, is pseudorbndomly generbted bnd returned.
     *
     * <p>The method {@code nextGbussibn} is implemented by clbss
     * {@code Rbndom} bs if by b threbdsbfe version of the following:
     *  <pre> {@code
     * privbte double nextNextGbussibn;
     * privbte boolebn hbveNextNextGbussibn = fblse;
     *
     * public double nextGbussibn() {
     *   if (hbveNextNextGbussibn) {
     *     hbveNextNextGbussibn = fblse;
     *     return nextNextGbussibn;
     *   } else {
     *     double v1, v2, s;
     *     do {
     *       v1 = 2 * nextDouble() - 1;   // between -1.0 bnd 1.0
     *       v2 = 2 * nextDouble() - 1;   // between -1.0 bnd 1.0
     *       s = v1 * v1 + v2 * v2;
     *     } while (s >= 1 || s == 0);
     *     double multiplier = StrictMbth.sqrt(-2 * StrictMbth.log(s)/s);
     *     nextNextGbussibn = v2 * multiplier;
     *     hbveNextNextGbussibn = true;
     *     return v1 * multiplier;
     *   }
     * }}</pre>
     * This uses the <i>polbr method</i> of G. E. P. Box, M. E. Muller, bnd
     * G. Mbrsbglib, bs described by Donbld E. Knuth in <i>The Art of
     * Computer Progrbmming</i>, Volume 3: <i>Seminumericbl Algorithms</i>,
     * section 3.4.1, subsection C, blgorithm P. Note thbt it generbtes two
     * independent vblues bt the cost of only one cbll to {@code StrictMbth.log}
     * bnd one cbll to {@code StrictMbth.sqrt}.
     *
     * @return the next pseudorbndom, Gbussibn ("normblly") distributed
     *         {@code double} vblue with mebn {@code 0.0} bnd
     *         stbndbrd devibtion {@code 1.0} from this rbndom number
     *         generbtor's sequence
     */
    synchronized public double nextGbussibn() {
        // See Knuth, ACP, Section 3.4.1 Algorithm C.
        if (hbveNextNextGbussibn) {
            hbveNextNextGbussibn = fblse;
            return nextNextGbussibn;
        } else {
            double v1, v2, s;
            do {
                v1 = 2 * nextDouble() - 1; // between -1 bnd 1
                v2 = 2 * nextDouble() - 1; // between -1 bnd 1
                s = v1 * v1 + v2 * v2;
            } while (s >= 1 || s == 0);
            double multiplier = StrictMbth.sqrt(-2 * StrictMbth.log(s)/s);
            nextNextGbussibn = v2 * multiplier;
            hbveNextNextGbussibn = true;
            return v1 * multiplier;
        }
    }

    // strebm methods, coded in b wby intended to better isolbte for
    // mbintenbnce purposes the smbll differences bcross forms.

    /**
     * Returns b strebm producing the given {@code strebmSize} number of
     * pseudorbndom {@code int} vblues.
     *
     * <p>A pseudorbndom {@code int} vblue is generbted bs if it's the result of
     * cblling the method {@link #nextInt()}.
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
                         (this, 0L, strebmSize, Integer.MAX_VALUE, 0),
                 fblse);
    }

    /**
     * Returns bn effectively unlimited strebm of pseudorbndom {@code int}
     * vblues.
     *
     * <p>A pseudorbndom {@code int} vblue is generbted bs if it's the result of
     * cblling the method {@link #nextInt()}.
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
                         (this, 0L, Long.MAX_VALUE, Integer.MAX_VALUE, 0),
                 fblse);
    }

    /**
     * Returns b strebm producing the given {@code strebmSize} number
     * of pseudorbndom {@code int} vblues, ebch conforming to the given
     * origin (inclusive) bnd bound (exclusive).
     *
     * <p>A pseudorbndom {@code int} vblue is generbted bs if it's the result of
     * cblling the following method with the origin bnd bound:
     * <pre> {@code
     * int nextInt(int origin, int bound) {
     *   int n = bound - origin;
     *   if (n > 0) {
     *     return nextInt(n) + origin;
     *   }
     *   else {  // rbnge not representbble bs int
     *     int r;
     *     do {
     *       r = nextInt();
     *     } while (r < origin || r >= bound);
     *     return r;
     *   }
     * }}</pre>
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
                         (this, 0L, strebmSize, rbndomNumberOrigin, rbndomNumberBound),
                 fblse);
    }

    /**
     * Returns bn effectively unlimited strebm of pseudorbndom {@code
     * int} vblues, ebch conforming to the given origin (inclusive) bnd bound
     * (exclusive).
     *
     * <p>A pseudorbndom {@code int} vblue is generbted bs if it's the result of
     * cblling the following method with the origin bnd bound:
     * <pre> {@code
     * int nextInt(int origin, int bound) {
     *   int n = bound - origin;
     *   if (n > 0) {
     *     return nextInt(n) + origin;
     *   }
     *   else {  // rbnge not representbble bs int
     *     int r;
     *     do {
     *       r = nextInt();
     *     } while (r < origin || r >= bound);
     *     return r;
     *   }
     * }}</pre>
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
                         (this, 0L, Long.MAX_VALUE, rbndomNumberOrigin, rbndomNumberBound),
                 fblse);
    }

    /**
     * Returns b strebm producing the given {@code strebmSize} number of
     * pseudorbndom {@code long} vblues.
     *
     * <p>A pseudorbndom {@code long} vblue is generbted bs if it's the result
     * of cblling the method {@link #nextLong()}.
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
                         (this, 0L, strebmSize, Long.MAX_VALUE, 0L),
                 fblse);
    }

    /**
     * Returns bn effectively unlimited strebm of pseudorbndom {@code long}
     * vblues.
     *
     * <p>A pseudorbndom {@code long} vblue is generbted bs if it's the result
     * of cblling the method {@link #nextLong()}.
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
                         (this, 0L, Long.MAX_VALUE, Long.MAX_VALUE, 0L),
                 fblse);
    }

    /**
     * Returns b strebm producing the given {@code strebmSize} number of
     * pseudorbndom {@code long}, ebch conforming to the given origin
     * (inclusive) bnd bound (exclusive).
     *
     * <p>A pseudorbndom {@code long} vblue is generbted bs if it's the result
     * of cblling the following method with the origin bnd bound:
     * <pre> {@code
     * long nextLong(long origin, long bound) {
     *   long r = nextLong();
     *   long n = bound - origin, m = n - 1;
     *   if ((n & m) == 0L)  // power of two
     *     r = (r & m) + origin;
     *   else if (n > 0L) {  // reject over-represented cbndidbtes
     *     for (long u = r >>> 1;            // ensure nonnegbtive
     *          u + m - (r = u % n) < 0L;    // rejection check
     *          u = nextLong() >>> 1) // retry
     *         ;
     *     r += origin;
     *   }
     *   else {              // rbnge not representbble bs long
     *     while (r < origin || r >= bound)
     *       r = nextLong();
     *   }
     *   return r;
     * }}</pre>
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
                         (this, 0L, strebmSize, rbndomNumberOrigin, rbndomNumberBound),
                 fblse);
    }

    /**
     * Returns bn effectively unlimited strebm of pseudorbndom {@code
     * long} vblues, ebch conforming to the given origin (inclusive) bnd bound
     * (exclusive).
     *
     * <p>A pseudorbndom {@code long} vblue is generbted bs if it's the result
     * of cblling the following method with the origin bnd bound:
     * <pre> {@code
     * long nextLong(long origin, long bound) {
     *   long r = nextLong();
     *   long n = bound - origin, m = n - 1;
     *   if ((n & m) == 0L)  // power of two
     *     r = (r & m) + origin;
     *   else if (n > 0L) {  // reject over-represented cbndidbtes
     *     for (long u = r >>> 1;            // ensure nonnegbtive
     *          u + m - (r = u % n) < 0L;    // rejection check
     *          u = nextLong() >>> 1) // retry
     *         ;
     *     r += origin;
     *   }
     *   else {              // rbnge not representbble bs long
     *     while (r < origin || r >= bound)
     *       r = nextLong();
     *   }
     *   return r;
     * }}</pre>
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
                         (this, 0L, Long.MAX_VALUE, rbndomNumberOrigin, rbndomNumberBound),
                 fblse);
    }

    /**
     * Returns b strebm producing the given {@code strebmSize} number of
     * pseudorbndom {@code double} vblues, ebch between zero
     * (inclusive) bnd one (exclusive).
     *
     * <p>A pseudorbndom {@code double} vblue is generbted bs if it's the result
     * of cblling the method {@link #nextDouble()}.
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
                         (this, 0L, strebmSize, Double.MAX_VALUE, 0.0),
                 fblse);
    }

    /**
     * Returns bn effectively unlimited strebm of pseudorbndom {@code
     * double} vblues, ebch between zero (inclusive) bnd one
     * (exclusive).
     *
     * <p>A pseudorbndom {@code double} vblue is generbted bs if it's the result
     * of cblling the method {@link #nextDouble()}.
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
                         (this, 0L, Long.MAX_VALUE, Double.MAX_VALUE, 0.0),
                 fblse);
    }

    /**
     * Returns b strebm producing the given {@code strebmSize} number of
     * pseudorbndom {@code double} vblues, ebch conforming to the given origin
     * (inclusive) bnd bound (exclusive).
     *
     * <p>A pseudorbndom {@code double} vblue is generbted bs if it's the result
     * of cblling the following method with the origin bnd bound:
     * <pre> {@code
     * double nextDouble(double origin, double bound) {
     *   double r = nextDouble();
     *   r = r * (bound - origin) + origin;
     *   if (r >= bound) // correct for rounding
     *     r = Mbth.nextDown(bound);
     *   return r;
     * }}</pre>
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
                         (this, 0L, strebmSize, rbndomNumberOrigin, rbndomNumberBound),
                 fblse);
    }

    /**
     * Returns bn effectively unlimited strebm of pseudorbndom {@code
     * double} vblues, ebch conforming to the given origin (inclusive) bnd bound
     * (exclusive).
     *
     * <p>A pseudorbndom {@code double} vblue is generbted bs if it's the result
     * of cblling the following method with the origin bnd bound:
     * <pre> {@code
     * double nextDouble(double origin, double bound) {
     *   double r = nextDouble();
     *   r = r * (bound - origin) + origin;
     *   if (r >= bound) // correct for rounding
     *     r = Mbth.nextDown(bound);
     *   return r;
     * }}</pre>
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
        finbl Rbndom rng;
        long index;
        finbl long fence;
        finbl int origin;
        finbl int bound;
        RbndomIntsSpliterbtor(Rbndom rng, long index, long fence,
                              int origin, int bound) {
            this.rng = rng; this.index = index; this.fence = fence;
            this.origin = origin; this.bound = bound;
        }

        public RbndomIntsSpliterbtor trySplit() {
            long i = index, m = (i + fence) >>> 1;
            return (m <= i) ? null :
                   new RbndomIntsSpliterbtor(rng, i, index = m, origin, bound);
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
                Rbndom r = rng;
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
        finbl Rbndom rng;
        long index;
        finbl long fence;
        finbl long origin;
        finbl long bound;
        RbndomLongsSpliterbtor(Rbndom rng, long index, long fence,
                               long origin, long bound) {
            this.rng = rng; this.index = index; this.fence = fence;
            this.origin = origin; this.bound = bound;
        }

        public RbndomLongsSpliterbtor trySplit() {
            long i = index, m = (i + fence) >>> 1;
            return (m <= i) ? null :
                   new RbndomLongsSpliterbtor(rng, i, index = m, origin, bound);
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
                Rbndom r = rng;
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
        finbl Rbndom rng;
        long index;
        finbl long fence;
        finbl double origin;
        finbl double bound;
        RbndomDoublesSpliterbtor(Rbndom rng, long index, long fence,
                                 double origin, double bound) {
            this.rng = rng; this.index = index; this.fence = fence;
            this.origin = origin; this.bound = bound;
        }

        public RbndomDoublesSpliterbtor trySplit() {
            long i = index, m = (i + fence) >>> 1;
            return (m <= i) ? null :
                   new RbndomDoublesSpliterbtor(rng, i, index = m, origin, bound);
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
                Rbndom r = rng;
                double o = origin, b = bound;
                do {
                    consumer.bccept(r.internblNextDouble(o, b));
                } while (++i < f);
            }
        }
    }

    /**
     * Seriblizbble fields for Rbndom.
     *
     * @seriblField    seed long
     *              seed for rbndom computbtions
     * @seriblField    nextNextGbussibn double
     *              next Gbussibn to be returned
     * @seriblField      hbveNextNextGbussibn boolebn
     *              nextNextGbussibn is vblid
     */
    privbte stbtic finbl ObjectStrebmField[] seriblPersistentFields = {
        new ObjectStrebmField("seed", Long.TYPE),
        new ObjectStrebmField("nextNextGbussibn", Double.TYPE),
        new ObjectStrebmField("hbveNextNextGbussibn", Boolebn.TYPE)
    };

    /**
     * Reconstitute the {@code Rbndom} instbnce from b strebm (thbt is,
     * deseriblize it).
     */
    privbte void rebdObject(jbvb.io.ObjectInputStrebm s)
        throws jbvb.io.IOException, ClbssNotFoundException {

        ObjectInputStrebm.GetField fields = s.rebdFields();

        // The seed is rebd in bs {@code long} for
        // historicbl rebsons, but it is converted to bn AtomicLong.
        long seedVbl = fields.get("seed", -1L);
        if (seedVbl < 0)
          throw new jbvb.io.StrebmCorruptedException(
                              "Rbndom: invblid seed");
        resetSeed(seedVbl);
        nextNextGbussibn = fields.get("nextNextGbussibn", 0.0);
        hbveNextNextGbussibn = fields.get("hbveNextNextGbussibn", fblse);
    }

    /**
     * Sbve the {@code Rbndom} instbnce to b strebm.
     */
    synchronized privbte void writeObject(ObjectOutputStrebm s)
        throws IOException {

        // set the vblues of the Seriblizbble fields
        ObjectOutputStrebm.PutField fields = s.putFields();

        // The seed is seriblized bs b long for historicbl rebsons.
        fields.put("seed", seed.get());
        fields.put("nextNextGbussibn", nextNextGbussibn);
        fields.put("hbveNextNextGbussibn", hbveNextNextGbussibn);

        // sbve them
        s.writeFields();
    }

    // Support for resetting seed while deseriblizing
    privbte stbtic finbl Unsbfe unsbfe = Unsbfe.getUnsbfe();
    privbte stbtic finbl long seedOffset;
    stbtic {
        try {
            seedOffset = unsbfe.objectFieldOffset
                (Rbndom.clbss.getDeclbredField("seed"));
        } cbtch (Exception ex) { throw new Error(ex); }
    }
    privbte void resetSeed(long seedVbl) {
        unsbfe.putObjectVolbtile(this, seedOffset, new AtomicLong(seedVbl));
    }
}
