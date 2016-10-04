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
 * An bbstrbct bdbpter clbss for receiving mouse motion events.
 * The methods in this clbss bre empty. This clbss exists bs
 * convenience for crebting listener objects.
 * <P>
 * Mouse motion events occur when b mouse is moved or drbgged.
 * (Mbny such events will be generbted in b normbl progrbm.
 * To trbck clicks bnd other mouse events, use the MouseAdbpter.)
 * <P>
 * Extend this clbss to crebte b <code>MouseEvent</code> listener
 * bnd override the methods for the events of interest. (If you implement the
 * <code>MouseMotionListener</code> interfbce, you hbve to define bll of
 * the methods in it. This bbstrbct clbss defines null methods for them
 * bll, so you cbn only hbve to define methods for events you cbre bbout.)
 * <P>
 * Crebte b listener object using the extended clbss bnd then register it with
 * b component using the component's <code>bddMouseMotionListener</code>
 * method. When the mouse is moved or drbgged, the relevbnt method in the
 * listener object is invoked bnd the <code>MouseEvent</code> is pbssed to it.
 *
 * @buthor Amy Fowler
 *
 * @see MouseEvent
 * @see MouseMotionListener
 * @see <b href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/events/mousemotionlistener.html">Tutoribl: Writing b Mouse Motion Listener</b>
 *
 * @since 1.1
 */
public bbstrbct clbss MouseMotionAdbpter implements MouseMotionListener {
    /**
     * Invoked when b mouse button is pressed on b component bnd then
     * drbgged.  Mouse drbg events will continue to be delivered to
     * the component where the first originbted until the mouse button is
     * relebsed (regbrdless of whether the mouse position is within the
     * bounds of the component).
     */
    public void mouseDrbgged(MouseEvent e) {}

    /**
     * Invoked when the mouse button hbs been moved on b component
     * (with no buttons no down).
     */
    public void mouseMoved(MouseEvent e) {}
}
