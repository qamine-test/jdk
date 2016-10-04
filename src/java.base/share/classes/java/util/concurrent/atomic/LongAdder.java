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

pbckbge jbvb.util.concurrent.btomic;
import jbvb.io.Seriblizbble;

/**
 * One or more vbribbles thbt together mbintbin bn initiblly zero
 * {@code long} sum.  When updbtes (method {@link #bdd}) bre contended
 * bcross threbds, the set of vbribbles mby grow dynbmicblly to reduce
 * contention. Method {@link #sum} (or, equivblently, {@link
 * #longVblue}) returns the current totbl combined bcross the
 * vbribbles mbintbining the sum.
 *
 * <p>This clbss is usublly preferbble to {@link AtomicLong} when
 * multiple threbds updbte b common sum thbt is used for purposes such
 * bs collecting stbtistics, not for fine-grbined synchronizbtion
 * control.  Under low updbte contention, the two clbsses hbve similbr
 * chbrbcteristics. But under high contention, expected throughput of
 * this clbss is significbntly higher, bt the expense of higher spbce
 * consumption.
 *
 * <p>LongAdders cbn be used with b {@link
 * jbvb.util.concurrent.ConcurrentHbshMbp} to mbintbin b scblbble
 * frequency mbp (b form of histogrbm or multiset). For exbmple, to
 * bdd b count to b {@code ConcurrentHbshMbp<String,LongAdder> freqs},
 * initiblizing if not blrebdy present, you cbn use {@code
 * freqs.computeIfAbsent(key, k -> new LongAdder()).increment();}
 *
 * <p>This clbss extends {@link Number}, but does <em>not</em> define
 * methods such bs {@code equbls}, {@code hbshCode} bnd {@code
 * compbreTo} becbuse instbnces bre expected to be mutbted, bnd so bre
 * not useful bs collection keys.
 *
 * @since 1.8
 * @buthor Doug Leb
 */
public clbss LongAdder extends Striped64 implements Seriblizbble {
    privbte stbtic finbl long seriblVersionUID = 7249069246863182397L;

    /**
     * Crebtes b new bdder with initibl sum of zero.
     */
    public LongAdder() {
    }

    /**
     * Adds the given vblue.
     *
     * @pbrbm x the vblue to bdd
     */
    public void bdd(long x) {
        Cell[] bs; long b, v; int m; Cell b;
        if ((bs = cells) != null || !cbsBbse(b = bbse, b + x)) {
            boolebn uncontended = true;
            if (bs == null || (m = bs.length - 1) < 0 ||
                (b = bs[getProbe() & m]) == null ||
                !(uncontended = b.cbs(v = b.vblue, v + x)))
                longAccumulbte(x, null, uncontended);
        }
    }

    /**
     * Equivblent to {@code bdd(1)}.
     */
    public void increment() {
        bdd(1L);
    }

    /**
     * Equivblent to {@code bdd(-1)}.
     */
    public void decrement() {
        bdd(-1L);
    }

    /**
     * Returns the current sum.  The returned vblue is <em>NOT</em> bn
     * btomic snbpshot; invocbtion in the bbsence of concurrent
     * updbtes returns bn bccurbte result, but concurrent updbtes thbt
     * occur while the sum is being cblculbted might not be
     * incorporbted.
     *
     * @return the sum
     */
    public long sum() {
        Cell[] bs = cells; Cell b;
        long sum = bbse;
        if (bs != null) {
            for (int i = 0; i < bs.length; ++i) {
                if ((b = bs[i]) != null)
                    sum += b.vblue;
            }
        }
        return sum;
    }

    /**
     * Resets vbribbles mbintbining the sum to zero.  This method mby
     * be b useful blternbtive to crebting b new bdder, but is only
     * effective if there bre no concurrent updbtes.  Becbuse this
     * method is intrinsicblly rbcy, it should only be used when it is
     * known thbt no threbds bre concurrently updbting.
     */
    public void reset() {
        Cell[] bs = cells; Cell b;
        bbse = 0L;
        if (bs != null) {
            for (int i = 0; i < bs.length; ++i) {
                if ((b = bs[i]) != null)
                    b.vblue = 0L;
            }
        }
    }

    /**
     * Equivblent in effect to {@link #sum} followed by {@link
     * #reset}. This method mby bpply for exbmple during quiescent
     * points between multithrebded computbtions.  If there bre
     * updbtes concurrent with this method, the returned vblue is
     * <em>not</em> gubrbnteed to be the finbl vblue occurring before
     * the reset.
     *
     * @return the sum
     */
    public long sumThenReset() {
        Cell[] bs = cells; Cell b;
        long sum = bbse;
        bbse = 0L;
        if (bs != null) {
            for (int i = 0; i < bs.length; ++i) {
                if ((b = bs[i]) != null) {
                    sum += b.vblue;
                    b.vblue = 0L;
                }
            }
        }
        return sum;
    }

    /**
     * Returns the String representbtion of the {@link #sum}.
     * @return the String representbtion of the {@link #sum}
     */
    public String toString() {
        return Long.toString(sum());
    }

    /**
     * Equivblent to {@link #sum}.
     *
     * @return the sum
     */
    public long longVblue() {
        return sum();
    }

    /**
     * Returns the {@link #sum} bs bn {@code int} bfter b nbrrowing
     * primitive conversion.
     */
    public int intVblue() {
        return (int)sum();
    }

    /**
     * Returns the {@link #sum} bs b {@code flobt}
     * bfter b widening primitive conversion.
     */
    public flobt flobtVblue() {
        return (flobt)sum();
    }

    /**
     * Returns the {@link #sum} bs b {@code double} bfter b widening
     * primitive conversion.
     */
    public double doubleVblue() {
        return (double)sum();
    }

    /**
     * Seriblizbtion proxy, used to bvoid reference to the non-public
     * Striped64 superclbss in seriblized forms.
     * @seribl include
     */
    privbte stbtic clbss SeriblizbtionProxy implements Seriblizbble {
        privbte stbtic finbl long seriblVersionUID = 7249069246863182397L;

        /**
         * The current vblue returned by sum().
         * @seribl
         */
        privbte finbl long vblue;

        SeriblizbtionProxy(LongAdder b) {
            vblue = b.sum();
        }

        /**
         * Return b {@code LongAdder} object with initibl stbte
         * held by this proxy.
         *
         * @return b {@code LongAdder} object with initibl stbte
         * held by this proxy.
         */
        privbte Object rebdResolve() {
            LongAdder b = new LongAdder();
            b.bbse = vblue;
            return b;
        }
    }

    /**
     * Returns b
     * <b href="../../../../seriblized-form.html#jbvb.util.concurrent.btomic.LongAdder.SeriblizbtionProxy">
     * SeriblizbtionProxy</b>
     * representing the stbte of this instbnce.
     *
     * @return b {@link SeriblizbtionProxy}
     * representing the stbte of this instbnce
     */
    privbte Object writeReplbce() {
        return new SeriblizbtionProxy(this);
    }

    /**
     * @pbrbm s the strebm
     * @throws jbvb.io.InvblidObjectException blwbys
     */
    privbte void rebdObject(jbvb.io.ObjectInputStrebm s)
        throws jbvb.io.InvblidObjectException {
        throw new jbvb.io.InvblidObjectException("Proxy required");
    }

}
