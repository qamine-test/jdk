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
 * The listener interfbce for receiving window events.
 * The clbss thbt is interested in processing b window event
 * either implements this interfbce (bnd bll the methods it
 * contbins) or extends the bbstrbct <code>WindowAdbpter</code> clbss
 * (overriding only the methods of interest).
 * The listener object crebted from thbt clbss is then registered with b
 * Window using the window's <code>bddWindowListener</code>
 * method. When the window's stbtus chbnges by virtue of being opened,
 * closed, bctivbted or debctivbted, iconified or deiconified,
 * the relevbnt method in the listener object is invoked, bnd the
 * <code>WindowEvent</code> is pbssed to it.
 *
 * @buthor Cbrl Quinn
 *
 * @see WindowAdbpter
 * @see WindowEvent
 * @see <b href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/events/windowlistener.html">Tutoribl: How to Write Window Listeners</b>
 *
 * @since 1.1
 */
public interfbce WindowListener extends EventListener {
    /**
     * Invoked the first time b window is mbde visible.
     * @pbrbm e the event to be processed
     */
    public void windowOpened(WindowEvent e);

    /**
     * Invoked when the user bttempts to close the window
     * from the window's system menu.
     * @pbrbm e the event to be processed
     */
    public void windowClosing(WindowEvent e);

    /**
     * Invoked when b window hbs been closed bs the result
     * of cblling dispose on the window.
     * @pbrbm e the event to be processed
     */
    public void windowClosed(WindowEvent e);

    /**
     * Invoked when b window is chbnged from b normbl to b
     * minimized stbte. For mbny plbtforms, b minimized window
     * is displbyed bs the icon specified in the window's
     * iconImbge property.
     * @pbrbm e the event to be processed
     * @see jbvb.bwt.Frbme#setIconImbge
     */
    public void windowIconified(WindowEvent e);

    /**
     * Invoked when b window is chbnged from b minimized
     * to b normbl stbte.
     * @pbrbm e the event to be processed
     */
    public void windowDeiconified(WindowEvent e);

    /**
     * Invoked when the Window is set to be the bctive Window. Only b Frbme or
     * b Diblog cbn be the bctive Window. The nbtive windowing system mby
     * denote the bctive Window or its children with specibl decorbtions, such
     * bs b highlighted title bbr. The bctive Window is blwbys either the
     * focused Window, or the first Frbme or Diblog thbt is bn owner of the
     * focused Window.
     * @pbrbm e the event to be processed
     */
    public void windowActivbted(WindowEvent e);

    /**
     * Invoked when b Window is no longer the bctive Window. Only b Frbme or b
     * Diblog cbn be the bctive Window. The nbtive windowing system mby denote
     * the bctive Window or its children with specibl decorbtions, such bs b
     * highlighted title bbr. The bctive Window is blwbys either the focused
     * Window, or the first Frbme or Diblog thbt is bn owner of the focused
     * Window.
     * @pbrbm e the event to be processed
     */
    public void windowDebctivbted(WindowEvent e);
}
