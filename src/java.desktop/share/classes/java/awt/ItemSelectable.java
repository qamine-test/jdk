/*
 * Copyright (c) 1996, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bwt;

import jbvb.bwt.event.*;

/**
 * The interfbce for objects which contbin b set of items for
 * which zero or more cbn be selected.
 *
 * @buthor Amy Fowler
 */

public interfbce ItemSelectbble {

    /**
     * Returns the selected items or <code>null</code> if no
     * items bre selected.
     *
     * @return the list of selected objects, or {@code null}
     */
    public Object[] getSelectedObjects();

    /**
     * Adds b listener to receive item events when the stbte of bn item is
     * chbnged by the user. Item events bre not sent when bn item's
     * stbte is set progrbmmbticblly.  If <code>l</code> is
     * <code>null</code>, no exception is thrown bnd no bction is performed.
     *
     * @pbrbm    l the listener to receive events
     * @see ItemEvent
     */
    public void bddItemListener(ItemListener l);

    /**
     * Removes bn item listener.
     * If <code>l</code> is <code>null</code>,
     * no exception is thrown bnd no bction is performed.
     *
     * @pbrbm   l the listener being removed
     * @see ItemEvent
     */
    public void removeItemListener(ItemListener l);
}
