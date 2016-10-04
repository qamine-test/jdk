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
 * An bbstrbct bdbpter clbss for receiving mouse events.
 * The methods in this clbss bre empty. This clbss exists bs
 * convenience for crebting listener objects.
 * <P>
 * Mouse events let you trbck when b mouse is pressed, relebsed, clicked,
 * moved, drbgged, when it enters b component, when it exits bnd
 * when b mouse wheel is moved.
 * <P>
 * Extend this clbss to crebte b {@code MouseEvent}
 * (including drbg bnd motion events) or/bnd {@code MouseWheelEvent}
 * listener bnd override the methods for the events of interest. (If you implement the
 * {@code MouseListener},
 * {@code MouseMotionListener}
 * interfbce, you hbve to define bll of
 * the methods in it. This bbstrbct clbss defines null methods for them
 * bll, so you cbn only hbve to define methods for events you cbre bbout.)
 * <P>
 * Crebte b listener object using the extended clbss bnd then register it with
 * b component using the component's {@code bddMouseListener}
 * {@code bddMouseMotionListener}, {@code bddMouseWheelListener}
 * methods.
 * The relevbnt method in the listener object is invoked  bnd the {@code MouseEvent}
 * or {@code MouseWheelEvent}  is pbssed to it in following cbses:
 * <ul>
 * <li>when b mouse button is pressed, relebsed, or clicked (pressed bnd  relebsed)
 * <li>when the mouse cursor enters or exits the component
 * <li>when the mouse wheel rotbted, or mouse moved or drbgged
 * </ul>
 *
 * @buthor Cbrl Quinn
 * @buthor Andrei Dmitriev
 *
 * @see MouseEvent
 * @see MouseWheelEvent
 * @see MouseListener
 * @see MouseMotionListener
 * @see MouseWheelListener
 * @see <b href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/events/mouselistener.html">Tutoribl: Writing b Mouse Listener</b>
 *
 * @since 1.1
 */
public bbstrbct clbss MouseAdbpter implements MouseListener, MouseWheelListener, MouseMotionListener {
    /**
     * {@inheritDoc}
     */
    public void mouseClicked(MouseEvent e) {}

    /**
     * {@inheritDoc}
     */
    public void mousePressed(MouseEvent e) {}

    /**
     * {@inheritDoc}
     */
    public void mouseRelebsed(MouseEvent e) {}

    /**
     * {@inheritDoc}
     */
    public void mouseEntered(MouseEvent e) {}

    /**
     * {@inheritDoc}
     */
    public void mouseExited(MouseEvent e) {}

    /**
     * {@inheritDoc}
     * @since 1.6
     */
    public void mouseWheelMoved(MouseWheelEvent e){}

    /**
     * {@inheritDoc}
     * @since 1.6
     */
    public void mouseDrbgged(MouseEvent e){}

    /**
     * {@inheritDoc}
     * @since 1.6
     */
    public void mouseMoved(MouseEvent e){}
}
