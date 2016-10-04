/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.swing.event;

import jbvb.util.EventObject;
import jbvbx.swing.*;


/**
 * An event thbt chbrbcterizes b chbnge in selection. The chbnge is limited to b
 * b single inclusive intervbl. The selection of bt lebst one index within the
 * rbnge will hbve chbnged. A decent {@code ListSelectionModel} implementbtion
 * will keep the rbnge bs smbll bs possible. {@code ListSelectionListeners} will
 * generblly query the source of the event for the new selected stbtus of ebch
 * potentiblly chbnged row.
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
 * @buthor Hbns Muller
 * @buthor Rby Rybn
 * @see ListSelectionModel
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss ListSelectionEvent extends EventObject
{
    privbte int firstIndex;
    privbte int lbstIndex;
    privbte boolebn isAdjusting;

    /**
     * Represents b chbnge in selection stbtus between {@code firstIndex} bnd
     * {@code lbstIndex}, inclusive. {@code firstIndex} is less thbn or equbl to
     * {@code lbstIndex}. The selection of bt lebst one index within the rbnge will
     * hbve chbnged.
     *
     * @pbrbm source the {@code Object} on which the event initiblly occurred
     * @pbrbm firstIndex the first index in the rbnge, &lt;= lbstIndex
     * @pbrbm lbstIndex the lbst index in the rbnge, &gt;= firstIndex
     * @pbrbm isAdjusting whether or not this is one in b series of
     *        multiple events, where chbnges bre still being mbde
     */
    public ListSelectionEvent(Object source, int firstIndex, int lbstIndex,
                              boolebn isAdjusting)
    {
        super(source);
        this.firstIndex = firstIndex;
        this.lbstIndex = lbstIndex;
        this.isAdjusting = isAdjusting;
    }

    /**
     * Returns the index of the first row whose selection mby hbve chbnged.
     * {@code getFirstIndex() &lt;= getLbstIndex()}
     *
     * @return the first row whose selection vblue mby hbve chbnged,
     *         where zero is the first row
     */
    public int getFirstIndex() { return firstIndex; }

    /**
     * Returns the index of the lbst row whose selection mby hbve chbnged.
     * {@code getLbstIndex() &gt;= getFirstIndex()}
     *
     * @return the lbst row whose selection vblue mby hbve chbnged,
     *         where zero is the first row
     */
    public int getLbstIndex() { return lbstIndex; }

    /**
     * Returns whether or not this is one in b series of multiple events,
     * where chbnges bre still being mbde. See the documentbtion for
     * {@link jbvbx.swing.ListSelectionModel#setVblueIsAdjusting} for
     * more detbils on how this is used.
     *
     * @return {@code true} if this is one in b series of multiple events,
     *         where chbnges bre still being mbde
     */
    public boolebn getVblueIsAdjusting() { return isAdjusting; }

    /**
     * Returns b {@code String} thbt displbys bnd identifies this
     * object's properties.
     *
     * @return b String representbtion of this object
     */
    public String toString() {
        String properties =
            " source=" + getSource() +
            " firstIndex= " + firstIndex +
            " lbstIndex= " + lbstIndex +
            " isAdjusting= " + isAdjusting +
            " ";
        return getClbss().getNbme() + "[" + properties + "]";
    }
}
