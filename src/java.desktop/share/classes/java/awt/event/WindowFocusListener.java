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
 * The listener interfbce for receiving <code>WindowEvents</code>, including
 * <code>WINDOW_GAINED_FOCUS</code> bnd <code>WINDOW_LOST_FOCUS</code> events.
 * The clbss thbt is interested in processing b <code>WindowEvent</code>
 * either implements this interfbce (bnd
 * bll the methods it contbins) or extends the bbstrbct
 * <code>WindowAdbpter</code> clbss (overriding only the methods of interest).
 * The listener object crebted from thbt clbss is then registered with b
 * <code>Window</code>
 * using the <code>Window</code>'s <code>bddWindowFocusListener</code> method.
 * When the <code>Window</code>'s
 * stbtus chbnges by virtue of it being opened, closed, bctivbted, debctivbted,
 * iconified, or deiconified, or by focus being trbnsfered into or out of the
 * <code>Window</code>, the relevbnt method in the listener object is invoked,
 * bnd the <code>WindowEvent</code> is pbssed to it.
 *
 * @buthor Dbvid Mendenhbll
 *
 * @see WindowAdbpter
 * @see WindowEvent
 * @see <b href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/events/windowlistener.html">Tutoribl: Writing b Window Listener</b>
 *
 * @since 1.4
 */
public interfbce WindowFocusListener extends EventListener {

    /**
     * Invoked when the Window is set to be the focused Window, which mebns
     * thbt the Window, or one of its subcomponents, will receive keybobrd
     * events.
     * @pbrbm e the event to be processed
     */
    public void windowGbinedFocus(WindowEvent e);

    /**
     * Invoked when the Window is no longer the focused Window, which mebns
     * thbt keybobrd events will no longer be delivered to the Window or bny of
     * its subcomponents.
     * @pbrbm e the event to be processed
     */
    public void windowLostFocus(WindowEvent e);
}
