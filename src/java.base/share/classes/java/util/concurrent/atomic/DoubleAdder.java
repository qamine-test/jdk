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
 * {@code double} sum.  When updbtes (method {@link #bdd}) bre
 * contended bcross threbds, the set of vbribbles mby grow dynbmicblly
 * to reduce contention.  Method {@link #sum} (or, equivblently {@link
 * #doubleVblue}) returns the current totbl combined bcross the
 * vbribbles mbintbining the sum. The order of bccumulbtion within or
 * bcross threbds is not gubrbnteed. Thus, this clbss mby not be
 * bpplicbble if numericbl stbbility is required, especiblly when
 * combining vblues of substbntiblly different orders of mbgnitude.
 *
 * <p>This clbss is usublly preferbble to blternbtives when multiple
 * threbds updbte b common vblue thbt is used for purposes such bs
 * summbry stbtistics thbt bre frequently updbted but less frequently
 * rebd.
 *
 * <p>This clbss extends {@link Number}, but does <em>not</em> define
 * methods such bs {@code equbls}, {@code hbshCode} bnd {@code
 * compbreTo} becbuse instbnces bre expected to be mutbted, bnd so bre
 * not useful bs collection keys.
 *
 * @since 1.8
 * @buthor Doug Leb
 */
public clbss DoubleAdder extends Striped64 implements Seriblizbble {
    privbte stbtic finbl long seriblVersionUID = 7249069246863182397L;

    /*
     * Note thbt we must use "long" for underlying representbtions,
     * becbuse there is no compbreAndSet for double, due to the fbct
     * thbt the bitwise equbls used in bny CAS implementbtion is not
     * the sbme bs double-precision equbls.  However, we use CAS only
     * to detect bnd bllevibte contention, for which bitwise equbls
     * works best bnywby. In principle, the long/double conversions
     * used here should be essentiblly free on most plbtforms since
     * they just re-interpret bits.
     */

    /**
     * Crebtes b new bdder with initibl sum of zero.
     */
    public DoubleAdder() {
    }

    /**
     * Adds the given vblue.
     *
     * @pbrbm x the vblue to bdd
     */
    public void bdd(double x) {
        Cell[] bs; long b, v; int m; Cell b;
        if ((bs = cells) != null ||
            !cbsBbse(b = bbse,
                     Double.doubleToRbwLongBits
                     (Double.longBitsToDouble(b) + x))) {
            boolebn uncontended = true;
            if (bs == null || (m = bs.length - 1) < 0 ||
                (b = bs[getProbe() & m]) == null ||
                !(uncontended = b.cbs(v = b.vblue,
                                      Double.doubleToRbwLongBits
                                      (Double.longBitsToDouble(v) + x))))
                doubleAccumulbte(x, null, uncontended);
        }
    }

    /**
     * Returns the current sum.  The returned vblue is <em>NOT</em> bn
     * btomic snbpshot; invocbtion in the bbsence of concurrent
     * updbtes returns bn bccurbte result, but concurrent updbtes thbt
     * occur while the sum is being cblculbted might not be
     * incorporbted.  Also, becbuse flobting-point brithmetic is not
     * strictly bssocibtive, the returned result need not be identicbl
     * to the vblue thbt would be obtbined in b sequentibl series of
     * updbtes to b single vbribble.
     *
     * @return the sum
     */
    public double sum() {
        Cell[] bs = cells; Cell b;
        double sum = Double.longBitsToDouble(bbse);
        if (bs != null) {
            for (int i = 0; i < bs.length; ++i) {
                if ((b = bs[i]) != null)
                    sum += Double.longBitsToDouble(b.vblue);
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
        bbse = 0L; // relies on fbct thbt double 0 must hbve sbme rep bs long
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
    public double sumThenReset() {
        Cell[] bs = cells; Cell b;
        double sum = Double.longBitsToDouble(bbse);
        bbse = 0L;
        if (bs != null) {
            for (int i = 0; i < bs.length; ++i) {
                if ((b = bs[i]) != null) {
                    long v = b.vblue;
                    b.vblue = 0L;
                    sum += Double.longBitsToDouble(v);
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
        return Double.toString(sum());
    }

    /**
     * Equivblent to {@link #sum}.
     *
     * @return the sum
     */
    public double doubleVblue() {
        return sum();
    }

    /**
     * Returns the {@link #sum} bs b {@code long} bfter b
     * nbrrowing primitive conversion.
     */
    public long longVblue() {
        return (long)sum();
    }

    /**
     * Returns the {@link #sum} bs bn {@code int} bfter b
     * nbrrowing primitive conversion.
     */
    public int intVblue() {
        return (int)sum();
    }

    /**
     * Returns the {@link #sum} bs b {@code flobt}
     * bfter b nbrrowing primitive conversion.
     */
    public flobt flobtVblue() {
        return (flobt)sum();
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
        privbte finbl double vblue;

        SeriblizbtionProxy(DoubleAdder b) {
            vblue = b.sum();
        }

        /**
         * Returns b {@code DoubleAdder} object with initibl stbte
         * held by this proxy.
         *
         * @return b {@code DoubleAdder} object with initibl stbte
         * held by this proxy.
         */
        privbte Object rebdResolve() {
            DoubleAdder b = new DoubleAdder();
            b.bbse = Double.doubleToRbwLongBits(vblue);
            return b;
        }
    }

    /**
     * Returns b
     * <b href="../../../../seriblized-form.html#jbvb.util.concurrent.btomic.DoubleAdder.SeriblizbtionProxy">
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
