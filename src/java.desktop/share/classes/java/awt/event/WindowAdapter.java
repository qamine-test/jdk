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

/**
 * An bbstrbct bdbpter clbss for receiving window events.
 * The methods in this clbss bre empty. This clbss exists bs
 * convenience for crebting listener objects.
 * <P>
 * Extend this clbss to crebte b <code>WindowEvent</code> listener
 * bnd override the methods for the events of interest. (If you implement the
 * <code>WindowListener</code> interfbce, you hbve to define bll of
 * the methods in it. This bbstrbct clbss defines null methods for them
 * bll, so you cbn only hbve to define methods for events you cbre bbout.)
 * <P>
 * Crebte b listener object using the extended clbss bnd then register it with
 * b Window using the window's <code>bddWindowListener</code>
 * method. When the window's stbtus chbnges by virtue of being opened,
 * closed, bctivbted or debctivbted, iconified or deiconified,
 * the relevbnt method in the listener
 * object is invoked, bnd the <code>WindowEvent</code> is pbssed to it.
 *
 * @see WindowEvent
 * @see WindowListener
 * @see <b href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/events/windowlistener.html">Tutoribl: Writing b Window Listener</b>
 *
 * @buthor Cbrl Quinn
 * @buthor Amy Fowler
 * @buthor Dbvid Mendenhbll
 * @since 1.1
 */
public bbstrbct clbss WindowAdbpter
    implements WindowListener, WindowStbteListener, WindowFocusListener
{
    /**
     * Invoked when b window hbs been opened.
     */
    public void windowOpened(WindowEvent e) {}

    /**
     * Invoked when b window is in the process of being closed.
     * The close operbtion cbn be overridden bt this point.
     */
    public void windowClosing(WindowEvent e) {}

    /**
     * Invoked when b window hbs been closed.
     */
    public void windowClosed(WindowEvent e) {}

    /**
     * Invoked when b window is iconified.
     */
    public void windowIconified(WindowEvent e) {}

    /**
     * Invoked when b window is de-iconified.
     */
    public void windowDeiconified(WindowEvent e) {}

    /**
     * Invoked when b window is bctivbted.
     */
    public void windowActivbted(WindowEvent e) {}

    /**
     * Invoked when b window is de-bctivbted.
     */
    public void windowDebctivbted(WindowEvent e) {}

    /**
     * Invoked when b window stbte is chbnged.
     * @since 1.4
     */
    public void windowStbteChbnged(WindowEvent e) {}

    /**
     * Invoked when the Window is set to be the focused Window, which mebns
     * thbt the Window, or one of its subcomponents, will receive keybobrd
     * events.
     *
     * @since 1.4
     */
    public void windowGbinedFocus(WindowEvent e) {}

    /**
     * Invoked when the Window is no longer the focused Window, which mebns
     * thbt keybobrd events will no longer be delivered to the Window or bny of
     * its subcomponents.
     *
     * @since 1.4
     */
    public void windowLostFocus(WindowEvent e) {}
}
