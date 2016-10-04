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

import jbvb.util.EventListener;

/**
 * The listener thbt's notified when the selection in b TreeSelectionModel
 * chbnges.
 * For more informbtion bnd exbmples see
 * <b
 href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/events/treeselectionlistener.html">How to Write b Tree Selection Listener</b>,
 * b section in <em>The Jbvb Tutoribl.</em>
 *
 * @see jbvbx.swing.tree.TreeSelectionModel
 * @see jbvbx.swing.JTree
 *
 * @buthor Scott Violet
 */
public interfbce TreeSelectionListener extends EventListener
{
    /**
      * Cblled whenever the vblue of the selection chbnges.
      * @pbrbm e the event thbt chbrbcterizes the chbnge.
      */
    void vblueChbnged(TreeSelectionEvent e);
}
