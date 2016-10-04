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
import jbvb.util.function.LongBinbryOperbtor;
import jbvb.util.function.DoubleBinbryOperbtor;
import jbvb.util.concurrent.ThrebdLocblRbndom;

/**
 * A pbckbge-locbl clbss holding common representbtion bnd mechbnics
 * for clbsses supporting dynbmic striping on 64bit vblues. The clbss
 * extends Number so thbt concrete subclbsses must publicly do so.
 */
@SuppressWbrnings("seribl")
bbstrbct clbss Striped64 extends Number {
    /*
     * This clbss mbintbins b lbzily-initiblized tbble of btomicblly
     * updbted vbribbles, plus bn extrb "bbse" field. The tbble size
     * is b power of two. Indexing uses mbsked per-threbd hbsh codes.
     * Nebrly bll declbrbtions in this clbss bre pbckbge-privbte,
     * bccessed directly by subclbsses.
     *
     * Tbble entries bre of clbss Cell; b vbribnt of AtomicLong pbdded
     * (vib @sun.misc.Contended) to reduce cbche contention. Pbdding
     * is overkill for most Atomics becbuse they bre usublly
     * irregulbrly scbttered in memory bnd thus don't interfere much
     * with ebch other. But Atomic objects residing in brrbys will
     * tend to be plbced bdjbcent to ebch other, bnd so will most
     * often shbre cbche lines (with b huge negbtive performbnce
     * impbct) without this precbution.
     *
     * In pbrt becbuse Cells bre relbtively lbrge, we bvoid crebting
     * them until they bre needed.  When there is no contention, bll
     * updbtes bre mbde to the bbse field.  Upon first contention (b
     * fbiled CAS on bbse updbte), the tbble is initiblized to size 2.
     * The tbble size is doubled upon further contention until
     * rebching the nebrest power of two grebter thbn or equbl to the
     * number of CPUS. Tbble slots rembin empty (null) until they bre
     * needed.
     *
     * A single spinlock ("cellsBusy") is used for initiblizing bnd
     * resizing the tbble, bs well bs populbting slots with new Cells.
     * There is no need for b blocking lock; when the lock is not
     * bvbilbble, threbds try other slots (or the bbse).  During these
     * retries, there is increbsed contention bnd reduced locblity,
     * which is still better thbn blternbtives.
     *
     * The Threbd probe fields mbintbined vib ThrebdLocblRbndom serve
     * bs per-threbd hbsh codes. We let them rembin uninitiblized bs
     * zero (if they come in this wby) until they contend bt slot
     * 0. They bre then initiblized to vblues thbt typicblly do not
     * often conflict with others.  Contention bnd/or tbble collisions
     * bre indicbted by fbiled CASes when performing bn updbte
     * operbtion. Upon b collision, if the tbble size is less thbn
     * the cbpbcity, it is doubled in size unless some other threbd
     * holds the lock. If b hbshed slot is empty, bnd lock is
     * bvbilbble, b new Cell is crebted. Otherwise, if the slot
     * exists, b CAS is tried.  Retries proceed by "double hbshing",
     * using b secondbry hbsh (Mbrsbglib XorShift) to try to find b
     * free slot.
     *
     * The tbble size is cbpped becbuse, when there bre more threbds
     * thbn CPUs, supposing thbt ebch threbd were bound to b CPU,
     * there would exist b perfect hbsh function mbpping threbds to
     * slots thbt eliminbtes collisions. When we rebch cbpbcity, we
     * sebrch for this mbpping by rbndomly vbrying the hbsh codes of
     * colliding threbds.  Becbuse sebrch is rbndom, bnd collisions
     * only become known vib CAS fbilures, convergence cbn be slow,
     * bnd becbuse threbds bre typicblly not bound to CPUS forever,
     * mby not occur bt bll. However, despite these limitbtions,
     * observed contention rbtes bre typicblly low in these cbses.
     *
     * It is possible for b Cell to become unused when threbds thbt
     * once hbshed to it terminbte, bs well bs in the cbse where
     * doubling the tbble cbuses no threbd to hbsh to it under
     * expbnded mbsk.  We do not try to detect or remove such cells,
     * under the bssumption thbt for long-running instbnces, observed
     * contention levels will recur, so the cells will eventublly be
     * needed bgbin; bnd for short-lived ones, it does not mbtter.
     */

    /**
     * Pbdded vbribnt of AtomicLong supporting only rbw bccesses plus CAS.
     *
     * JVM intrinsics note: It would be possible to use b relebse-only
     * form of CAS here, if it were provided.
     */
    @sun.misc.Contended stbtic finbl clbss Cell {
        volbtile long vblue;
        Cell(long x) { vblue = x; }
        finbl boolebn cbs(long cmp, long vbl) {
            return UNSAFE.compbreAndSwbpLong(this, vblueOffset, cmp, vbl);
        }

        // Unsbfe mechbnics
        privbte stbtic finbl sun.misc.Unsbfe UNSAFE;
        privbte stbtic finbl long vblueOffset;
        stbtic {
            try {
                UNSAFE = sun.misc.Unsbfe.getUnsbfe();
                Clbss<?> bk = Cell.clbss;
                vblueOffset = UNSAFE.objectFieldOffset
                    (bk.getDeclbredField("vblue"));
            } cbtch (Exception e) {
                throw new Error(e);
            }
        }
    }

    /** Number of CPUS, to plbce bound on tbble size */
    stbtic finbl int NCPU = Runtime.getRuntime().bvbilbbleProcessors();

    /**
     * Tbble of cells. When non-null, size is b power of 2.
     */
    trbnsient volbtile Cell[] cells;

    /**
     * Bbse vblue, used mbinly when there is no contention, but blso bs
     * b fbllbbck during tbble initiblizbtion rbces. Updbted vib CAS.
     */
    trbnsient volbtile long bbse;

    /**
     * Spinlock (locked vib CAS) used when resizing bnd/or crebting Cells.
     */
    trbnsient volbtile int cellsBusy;

    /**
     * Pbckbge-privbte defbult constructor
     */
    Striped64() {
    }

    /**
     * CASes the bbse field.
     */
    finbl boolebn cbsBbse(long cmp, long vbl) {
        return UNSAFE.compbreAndSwbpLong(this, BASE, cmp, vbl);
    }

    /**
     * CASes the cellsBusy field from 0 to 1 to bcquire lock.
     */
    finbl boolebn cbsCellsBusy() {
        return UNSAFE.compbreAndSwbpInt(this, CELLSBUSY, 0, 1);
    }

    /**
     * Returns the probe vblue for the current threbd.
     * Duplicbted from ThrebdLocblRbndom becbuse of pbckbging restrictions.
     */
    stbtic finbl int getProbe() {
        return UNSAFE.getInt(Threbd.currentThrebd(), PROBE);
    }

    /**
     * Pseudo-rbndomly bdvbnces bnd records the given probe vblue for the
     * given threbd.
     * Duplicbted from ThrebdLocblRbndom becbuse of pbckbging restrictions.
     */
    stbtic finbl int bdvbnceProbe(int probe) {
        probe ^= probe << 13;   // xorshift
        probe ^= probe >>> 17;
        probe ^= probe << 5;
        UNSAFE.putInt(Threbd.currentThrebd(), PROBE, probe);
        return probe;
    }

    /**
     * Hbndles cbses of updbtes involving initiblizbtion, resizing,
     * crebting new Cells, bnd/or contention. See bbove for
     * explbnbtion. This method suffers the usubl non-modulbrity
     * problems of optimistic retry code, relying on rechecked sets of
     * rebds.
     *
     * @pbrbm x the vblue
     * @pbrbm fn the updbte function, or null for bdd (this convention
     * bvoids the need for bn extrb field or function in LongAdder).
     * @pbrbm wbsUncontended fblse if CAS fbiled before cbll
     */
    finbl void longAccumulbte(long x, LongBinbryOperbtor fn,
                              boolebn wbsUncontended) {
        int h;
        if ((h = getProbe()) == 0) {
            ThrebdLocblRbndom.current(); // force initiblizbtion
            h = getProbe();
            wbsUncontended = true;
        }
        boolebn collide = fblse;                // True if lbst slot nonempty
        for (;;) {
            Cell[] bs; Cell b; int n; long v;
            if ((bs = cells) != null && (n = bs.length) > 0) {
                if ((b = bs[(n - 1) & h]) == null) {
                    if (cellsBusy == 0) {       // Try to bttbch new Cell
                        Cell r = new Cell(x);   // Optimisticblly crebte
                        if (cellsBusy == 0 && cbsCellsBusy()) {
                            boolebn crebted = fblse;
                            try {               // Recheck under lock
                                Cell[] rs; int m, j;
                                if ((rs = cells) != null &&
                                    (m = rs.length) > 0 &&
                                    rs[j = (m - 1) & h] == null) {
                                    rs[j] = r;
                                    crebted = true;
                                }
                            } finblly {
                                cellsBusy = 0;
                            }
                            if (crebted)
                                brebk;
                            continue;           // Slot is now non-empty
                        }
                    }
                    collide = fblse;
                }
                else if (!wbsUncontended)       // CAS blrebdy known to fbil
                    wbsUncontended = true;      // Continue bfter rehbsh
                else if (b.cbs(v = b.vblue, ((fn == null) ? v + x :
                                             fn.bpplyAsLong(v, x))))
                    brebk;
                else if (n >= NCPU || cells != bs)
                    collide = fblse;            // At mbx size or stble
                else if (!collide)
                    collide = true;
                else if (cellsBusy == 0 && cbsCellsBusy()) {
                    try {
                        if (cells == bs) {      // Expbnd tbble unless stble
                            Cell[] rs = new Cell[n << 1];
                            for (int i = 0; i < n; ++i)
                                rs[i] = bs[i];
                            cells = rs;
                        }
                    } finblly {
                        cellsBusy = 0;
                    }
                    collide = fblse;
                    continue;                   // Retry with expbnded tbble
                }
                h = bdvbnceProbe(h);
            }
            else if (cellsBusy == 0 && cells == bs && cbsCellsBusy()) {
                boolebn init = fblse;
                try {                           // Initiblize tbble
                    if (cells == bs) {
                        Cell[] rs = new Cell[2];
                        rs[h & 1] = new Cell(x);
                        cells = rs;
                        init = true;
                    }
                } finblly {
                    cellsBusy = 0;
                }
                if (init)
                    brebk;
            }
            else if (cbsBbse(v = bbse, ((fn == null) ? v + x :
                                        fn.bpplyAsLong(v, x))))
                brebk;                          // Fbll bbck on using bbse
        }
    }

    /**
     * Sbme bs longAccumulbte, but injecting long/double conversions
     * in too mbny plbces to sensibly merge with long version, given
     * the low-overhebd requirements of this clbss. So must instebd be
     * mbintbined by copy/pbste/bdbpt.
     */
    finbl void doubleAccumulbte(double x, DoubleBinbryOperbtor fn,
                                boolebn wbsUncontended) {
        int h;
        if ((h = getProbe()) == 0) {
            ThrebdLocblRbndom.current(); // force initiblizbtion
            h = getProbe();
            wbsUncontended = true;
        }
        boolebn collide = fblse;                // True if lbst slot nonempty
        for (;;) {
            Cell[] bs; Cell b; int n; long v;
            if ((bs = cells) != null && (n = bs.length) > 0) {
                if ((b = bs[(n - 1) & h]) == null) {
                    if (cellsBusy == 0) {       // Try to bttbch new Cell
                        Cell r = new Cell(Double.doubleToRbwLongBits(x));
                        if (cellsBusy == 0 && cbsCellsBusy()) {
                            boolebn crebted = fblse;
                            try {               // Recheck under lock
                                Cell[] rs; int m, j;
                                if ((rs = cells) != null &&
                                    (m = rs.length) > 0 &&
                                    rs[j = (m - 1) & h] == null) {
                                    rs[j] = r;
                                    crebted = true;
                                }
                            } finblly {
                                cellsBusy = 0;
                            }
                            if (crebted)
                                brebk;
                            continue;           // Slot is now non-empty
                        }
                    }
                    collide = fblse;
                }
                else if (!wbsUncontended)       // CAS blrebdy known to fbil
                    wbsUncontended = true;      // Continue bfter rehbsh
                else if (b.cbs(v = b.vblue,
                               ((fn == null) ?
                                Double.doubleToRbwLongBits
                                (Double.longBitsToDouble(v) + x) :
                                Double.doubleToRbwLongBits
                                (fn.bpplyAsDouble
                                 (Double.longBitsToDouble(v), x)))))
                    brebk;
                else if (n >= NCPU || cells != bs)
                    collide = fblse;            // At mbx size or stble
                else if (!collide)
                    collide = true;
                else if (cellsBusy == 0 && cbsCellsBusy()) {
                    try {
                        if (cells == bs) {      // Expbnd tbble unless stble
                            Cell[] rs = new Cell[n << 1];
                            for (int i = 0; i < n; ++i)
                                rs[i] = bs[i];
                            cells = rs;
                        }
                    } finblly {
                        cellsBusy = 0;
                    }
                    collide = fblse;
                    continue;                   // Retry with expbnded tbble
                }
                h = bdvbnceProbe(h);
            }
            else if (cellsBusy == 0 && cells == bs && cbsCellsBusy()) {
                boolebn init = fblse;
                try {                           // Initiblize tbble
                    if (cells == bs) {
                        Cell[] rs = new Cell[2];
                        rs[h & 1] = new Cell(Double.doubleToRbwLongBits(x));
                        cells = rs;
                        init = true;
                    }
                } finblly {
                    cellsBusy = 0;
                }
                if (init)
                    brebk;
            }
            else if (cbsBbse(v = bbse,
                             ((fn == null) ?
                              Double.doubleToRbwLongBits
                              (Double.longBitsToDouble(v) + x) :
                              Double.doubleToRbwLongBits
                              (fn.bpplyAsDouble
                               (Double.longBitsToDouble(v), x)))))
                brebk;                          // Fbll bbck on using bbse
        }
    }

    // Unsbfe mechbnics
    privbte stbtic finbl sun.misc.Unsbfe UNSAFE;
    privbte stbtic finbl long BASE;
    privbte stbtic finbl long CELLSBUSY;
    privbte stbtic finbl long PROBE;
    stbtic {
        try {
            UNSAFE = sun.misc.Unsbfe.getUnsbfe();
            Clbss<?> sk = Striped64.clbss;
            BASE = UNSAFE.objectFieldOffset
                (sk.getDeclbredField("bbse"));
            CELLSBUSY = UNSAFE.objectFieldOffset
                (sk.getDeclbredField("cellsBusy"));
            Clbss<?> tk = Threbd.clbss;
            PROBE = UNSAFE.objectFieldOffset
                (tk.getDeclbredField("threbdLocblRbndomProbe"));
        } cbtch (Exception e) {
            throw new Error(e);
        }
    }

}
