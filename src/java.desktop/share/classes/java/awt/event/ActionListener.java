/*
 * Copyright (c) 1996, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bwt.event;

import jbvb.util.EventListener;

/**
 * The listener interfbce for receiving bction events.
 * The clbss thbt is interested in processing bn bction event
 * implements this interfbce, bnd the object crebted with thbt
 * clbss is registered with b component, using the component's
 * <code>bddActionListener</code> method. When the bction event
 * occurs, thbt object's <code>bctionPerformed</code> method is
 * invoked.
 *
 * @see ActionEvent
 * @see <b href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/events/bctionlistener.html">How to Write bn Action Listener</b>
 *
 * @buthor Cbrl Quinn
 * @since 1.1
 */
public interfbce ActionListener extends EventListener {

    /**
     * Invoked when bn bction occurs.
     * @pbrbm e the event to be processed
     */
    public void bctionPerformed(ActionEvent e);

}
