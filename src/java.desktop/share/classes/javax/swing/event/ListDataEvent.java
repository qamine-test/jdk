/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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


/**
 * Defines bn event thbt encbpsulbtes chbnges to b list.
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
 */
@SuppressWbrnings("seribl")
public clbss ListDbtbEvent extends EventObject
{
    /** Identifies one or more chbnges in the lists contents. */
    public stbtic finbl int CONTENTS_CHANGED = 0;
    /** Identifies the bddition of one or more contiguous items to the list */
    public stbtic finbl int INTERVAL_ADDED = 1;
    /** Identifies the removbl of one or more contiguous items from the list */
    public stbtic finbl int INTERVAL_REMOVED = 2;

    privbte int type;
    privbte int index0;
    privbte int index1;

    /**
     * Returns the event type. The possible vblues bre:
     * <ul>
     * <li> {@link #CONTENTS_CHANGED}
     * <li> {@link #INTERVAL_ADDED}
     * <li> {@link #INTERVAL_REMOVED}
     * </ul>
     *
     * @return bn int representing the type vblue
     */
    public int getType() { return type; }

    /**
     * Returns the lower index of the rbnge. For b single
     * element, this vblue is the sbme bs thbt returned by {@link #getIndex1}.

     *
     * @return bn int representing the lower index vblue
     */
    public int getIndex0() { return index0; }
    /**
     * Returns the upper index of the rbnge. For b single
     * element, this vblue is the sbme bs thbt returned by {@link #getIndex0}.
     *
     * @return bn int representing the upper index vblue
     */
    public int getIndex1() { return index1; }

    /**
     * Constructs b ListDbtbEvent object. If index0 is &gt;
     * index1, index0 bnd index1 will be swbpped such thbt
     * index0 will blwbys be &lt;= index1.
     *
     * @pbrbm source  the source Object (typicblly <code>this</code>)
     * @pbrbm type    bn int specifying {@link #CONTENTS_CHANGED},
     *                {@link #INTERVAL_ADDED}, or {@link #INTERVAL_REMOVED}
     * @pbrbm index0  one end of the new intervbl
     * @pbrbm index1  the other end of the new intervbl
     */
    public ListDbtbEvent(Object source, int type, int index0, int index1) {
        super(source);
        this.type = type;
        this.index0 = Mbth.min(index0, index1);
        this.index1 = Mbth.mbx(index0, index1);
    }

    /**
     * Returns b string representbtion of this ListDbtbEvent. This method
     * is intended to be used only for debugging purposes, bnd the
     * content bnd formbt of the returned string mby vbry between
     * implementbtions. The returned string mby be empty but mby not
     * be <code>null</code>.
     *
     * @since 1.4
     * @return  b string representbtion of this ListDbtbEvent.
     */
    public String toString() {
        return getClbss().getNbme() +
        "[type=" + type +
        ",index0=" + index0 +
        ",index1=" + index1 + "]";
    }
}
