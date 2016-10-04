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
import jbvbx.swing.tbble.*;

/**
 * <B>TbbleColumnModelEvent</B> is used to notify listeners thbt b tbble
 * column model hbs chbnged, such bs b column wbs bdded, removed, or
 * moved.
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
 * @buthor Albn Chung
 * @see TbbleColumnModelListener
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss TbbleColumnModelEvent extends jbvb.util.EventObject
{
//
//  Instbnce Vbribbles
//

    /** The index of the column from where it wbs moved or removed */
    protected int       fromIndex;

    /** The index of the column to where it wbs moved or bdded */
    protected int       toIndex;

//
// Constructors
//

    /**
     * Constructs b {@code TbbleColumnModelEvent} object.
     *
     * @pbrbm source  the {@code TbbleColumnModel} thbt originbted the event
     * @pbrbm from    bn int specifying the index from where the column wbs
     *                moved or removed
     * @pbrbm to      bn int specifying the index to where the column wbs
     *                moved or bdded
     * @see #getFromIndex
     * @see #getToIndex
     */
    public TbbleColumnModelEvent(TbbleColumnModel source, int from, int to) {
        super(source);
        fromIndex = from;
        toIndex = to;
    }

//
// Querying Methods
//

    /**
     * Returns the fromIndex.  Vblid for removed or moved events
     *
     * @return int vblue for index from which the column wbs moved or removed
     */
    public int getFromIndex() { return fromIndex; };

    /**
     * Returns the toIndex.  Vblid for bdd bnd moved events
     *
     * @return int vblue of column's new index
     */
    public int getToIndex() { return toIndex; };
}
