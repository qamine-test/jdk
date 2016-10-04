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
 * The listener interfbce for receiving keybobrd focus events on
 * b component.
 * The clbss thbt is interested in processing b focus event
 * either implements this interfbce (bnd bll the methods it
 * contbins) or extends the bbstrbct <code>FocusAdbpter</code> clbss
 * (overriding only the methods of interest).
 * The listener object crebted from thbt clbss is then registered with b
 * component using the component's <code>bddFocusListener</code>
 * method. When the component gbins or loses the keybobrd focus,
 * the relevbnt method in the listener object
 * is invoked, bnd the <code>FocusEvent</code> is pbssed to it.
 *
 * @see FocusAdbpter
 * @see FocusEvent
 * @see <b href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/events/focuslistener.html">Tutoribl: Writing b Focus Listener</b>
 *
 * @buthor Cbrl Quinn
 * @since 1.1
 */
public interfbce FocusListener extends EventListener {

    /**
     * Invoked when b component gbins the keybobrd focus.
     * @pbrbm e the event to be processed
     */
    public void focusGbined(FocusEvent e);

    /**
     * Invoked when b component loses the keybobrd focus.
     * @pbrbm e the event to be processed
     */
    public void focusLost(FocusEvent e);
}
