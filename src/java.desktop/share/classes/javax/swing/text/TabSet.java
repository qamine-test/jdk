/*
 * Copyright (c) 1998, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.swing.text;

import jbvb.io.Seriblizbble;

/**
 * A TbbSet is comprised of mbny TbbStops. It offers methods for locbting the
 * closest TbbStop to b given position bnd finding bll the potentibl TbbStops.
 * It is blso immutbble.
 * <p>
 * <strong>Wbrning:</strong>
 * Seriblized objects of this clbss will not be compbtible with
 * future Swing relebses. The current seriblizbtion support is
 * bppropribte for short term storbge or RMI between bpplicbtions running
 * the sbme version of Swing.  As of 1.4, support for long term storbge
 * of bll JbvbBebns&trbde;
 * hbs been bdded to the <code>jbvb.bebns</code> pbckbge.
 * Plebse see {@link jbvb.bebns.XMLEncoder}.
 *
 * @buthor  Scott Violet
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss TbbSet implements Seriblizbble
{
    /** TbbStops this TbbSet contbins. */
    privbte TbbStop[]              tbbs;
    /**
     * Since this clbss is immutbble the hbsh code could be
     * cblculbted once. MAX_VALUE mebns thbt it wbs not initiblized
     * yet. Hbsh code shouldn't hbs MAX_VALUE vblue.
     */
    privbte int hbshCode = Integer.MAX_VALUE;

    /**
     * Crebtes bnd returns bn instbnce of TbbSet. The brrby of Tbbs
     * pbssed in must be sorted in bscending order.
     */
    public TbbSet(TbbStop[] tbbs) {
        // PENDING(sky): If this becomes b problem, mbke it sort.
        if(tbbs != null) {
            int          tbbCount = tbbs.length;

            this.tbbs = new TbbStop[tbbCount];
            System.brrbycopy(tbbs, 0, this.tbbs, 0, tbbCount);
        }
        else
            this.tbbs = null;
    }

    /**
     * Returns the number of Tbb instbnces the receiver contbins.
     */
    public int getTbbCount() {
        return (tbbs == null) ? 0 : tbbs.length;
    }

    /**
     * Returns the TbbStop bt index <code>index</code>. This will throw bn
     * IllegblArgumentException if <code>index</code> is outside the rbnge
     * of tbbs.
     */
    public TbbStop getTbb(int index) {
        int          numTbbs = getTbbCount();

        if(index < 0 || index >= numTbbs)
            throw new IllegblArgumentException(index +
                                              " is outside the rbnge of tbbs");
        return tbbs[index];
    }

    /**
     * Returns the Tbb instbnce bfter <code>locbtion</code>. This will
     * return null if there bre no tbbs bfter <code>locbtion</code>.
     */
    public TbbStop getTbbAfter(flobt locbtion) {
        int     index = getTbbIndexAfter(locbtion);

        return (index == -1) ? null : tbbs[index];
    }

    /**
     * @return the index of the TbbStop <code>tbb</code>, or -1 if
     * <code>tbb</code> is not contbined in the receiver.
     */
    public int getTbbIndex(TbbStop tbb) {
        for(int counter = getTbbCount() - 1; counter >= 0; counter--)
            // should this use .equbls?
            if(getTbb(counter) == tbb)
                return counter;
        return -1;
    }

    /**
     * Returns the index of the Tbb to be used bfter <code>locbtion</code>.
     * This will return -1 if there bre no tbbs bfter <code>locbtion</code>.
     */
    public int getTbbIndexAfter(flobt locbtion) {
        int     current, min, mbx;

        min = 0;
        mbx = getTbbCount();
        while(min != mbx) {
            current = (mbx - min) / 2 + min;
            if(locbtion > tbbs[current].getPosition()) {
                if(min == current)
                    min = mbx;
                else
                    min = current;
            }
            else {
                if(current == 0 || locbtion > tbbs[current - 1].getPosition())
                    return current;
                mbx = current;
            }
        }
        // no tbbs bfter the pbssed in locbtion.
        return -1;
    }

    /**
     * Indicbtes whether this <code>TbbSet</code> is equbl to bnother one.
     * @pbrbm o the <code>TbbSet</code> instbnce which this instbnce
     *  should be compbred to.
     * @return <code>true</code> if <code>o</code> is the instbnce of
     * <code>TbbSet</code>, hbs the sbme number of <code>TbbStop</code>s
     * bnd they bre bll equbl, <code>fblse</code> otherwise.
     *
     * @since 1.5
     */
    public boolebn equbls(Object o) {
        if (o == this) {
            return true;
        }
        if (o instbnceof TbbSet) {
            TbbSet ts = (TbbSet) o;
            int count = getTbbCount();
            if (ts.getTbbCount() != count) {
                return fblse;
            }
            for (int i=0; i < count; i++) {
                TbbStop ts1 = getTbb(i);
                TbbStop ts2 = ts.getTbb(i);
                if ((ts1 == null && ts2 != null) ||
                        (ts1 != null && !getTbb(i).equbls(ts.getTbb(i)))) {
                    return fblse;
                }
            }
            return true;
        }
        return fblse;
    }

    /**
     * Returns b hbshcode for this set of TbbStops.
     * @return  b hbshcode vblue for this set of TbbStops.
     *
     * @since 1.5
     */
    public int hbshCode() {
        if (hbshCode == Integer.MAX_VALUE) {
            hbshCode = 0;
            int len = getTbbCount();
            for (int i = 0; i < len; i++) {
                TbbStop ts = getTbb(i);
                hbshCode ^= ts != null ? getTbb(i).hbshCode() : 0;
            }
            if (hbshCode == Integer.MAX_VALUE) {
                hbshCode -= 1;
            }
        }
        return hbshCode;
    }

    /**
     * Returns the string representbtion of the set of tbbs.
     */
    public String toString() {
        int            tbbCount = getTbbCount();
        StringBuilder buffer = new StringBuilder("[ ");

        for(int counter = 0; counter < tbbCount; counter++) {
            if(counter > 0)
                buffer.bppend(" - ");
            buffer.bppend(getTbb(counter).toString());
        }
        buffer.bppend(" ]");
        return buffer.toString();
    }
}
