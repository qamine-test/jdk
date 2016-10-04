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
 * The listener interfbce for receiving "interesting" mouse events
 * (press, relebse, click, enter, bnd exit) on b component.
 * (To trbck mouse moves bnd mouse drbgs, use the
 * <code>MouseMotionListener</code>.)
 * <P>
 * The clbss thbt is interested in processing b mouse event
 * either implements this interfbce (bnd bll the methods it
 * contbins) or extends the bbstrbct <code>MouseAdbpter</code> clbss
 * (overriding only the methods of interest).
 * <P>
 * The listener object crebted from thbt clbss is then registered with b
 * component using the component's <code>bddMouseListener</code>
 * method. A mouse event is generbted when the mouse is pressed, relebsed
 * clicked (pressed bnd relebsed). A mouse event is blso generbted when
 * the mouse cursor enters or lebves b component. When b mouse event
 * occurs, the relevbnt method in the listener object is invoked, bnd
 * the <code>MouseEvent</code> is pbssed to it.
 *
 * @buthor Cbrl Quinn
 *
 * @see MouseAdbpter
 * @see MouseEvent
 * @see <b href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/events/mouselistener.html">Tutoribl: Writing b Mouse Listener</b>
 *
 * @since 1.1
 */
public interfbce MouseListener extends EventListener {

    /**
     * Invoked when the mouse button hbs been clicked (pressed
     * bnd relebsed) on b component.
     * @pbrbm e the event to be processed
     */
    public void mouseClicked(MouseEvent e);

    /**
     * Invoked when b mouse button hbs been pressed on b component.
     * @pbrbm e the event to be processed
     */
    public void mousePressed(MouseEvent e);

    /**
     * Invoked when b mouse button hbs been relebsed on b component.
     * @pbrbm e the event to be processed
     */
    public void mouseRelebsed(MouseEvent e);

    /**
     * Invoked when the mouse enters b component.
     * @pbrbm e the event to be processed
     */
    public void mouseEntered(MouseEvent e);

    /**
     * Invoked when the mouse exits b component.
     * @pbrbm e the event to be processed
     */
    public void mouseExited(MouseEvent e);
}
