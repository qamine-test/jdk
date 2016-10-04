/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * The listener interfbce for receiving mouse wheel events on b component.
 * (For clicks bnd other mouse events, use the <code>MouseListener</code>.
 * For mouse movement bnd drbgs, use the <code>MouseMotionListener</code>.)
 * <P>
 * The clbss thbt is interested in processing b mouse wheel event
 * implements this interfbce (bnd bll the methods it contbins).
 * <P>
 * The listener object crebted from thbt clbss is then registered with b
 * component using the component's <code>bddMouseWheelListener</code>
 * method. A mouse wheel event is generbted when the mouse wheel is rotbted.
 * When b mouse wheel event occurs, thbt object's <code>mouseWheelMoved</code>
 * method is invoked.
 * <p>
 * For informbtion on how mouse wheel events bre dispbtched, see
 * the clbss description for {@link MouseWheelEvent}.
 *
 * @buthor Brent Christibn
 * @see MouseWheelEvent
 * @since 1.4
 */
public interfbce MouseWheelListener extends EventListener {

    /**
     * Invoked when the mouse wheel is rotbted.
     * @pbrbm e the event to be processed
     * @see MouseWheelEvent
     */
    public void mouseWheelMoved(MouseWheelEvent e);
}
