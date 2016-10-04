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
 * The listener interfbce for receiving component events.
 * The clbss thbt is interested in processing b component event
 * either implements this interfbce (bnd bll the methods it
 * contbins) or extends the bbstrbct <code>ComponentAdbpter</code> clbss
 * (overriding only the methods of interest).
 * The listener object crebted from thbt clbss is then registered with b
 * component using the component's <code>bddComponentListener</code>
 * method. When the component's size, locbtion, or visibility
 * chbnges, the relevbnt method in the listener object is invoked,
 * bnd the <code>ComponentEvent</code> is pbssed to it.
 * <P>
 * Component events bre provided for notificbtion purposes ONLY;
 * The AWT will butombticblly hbndle component moves bnd resizes
 * internblly so thbt GUI lbyout works properly regbrdless of
 * whether b progrbm registers b <code>ComponentListener</code> or not.
 *
 * @see ComponentAdbpter
 * @see ComponentEvent
 * @see <b href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/events/componentlistener.html">Tutoribl: Writing b Component Listener</b>
 *
 * @buthor Cbrl Quinn
 * @since 1.1
 */
public interfbce ComponentListener extends EventListener {
    /**
     * Invoked when the component's size chbnges.
     * @pbrbm e the event to be processed
     */
    public void componentResized(ComponentEvent e);

    /**
     * Invoked when the component's position chbnges.
     * @pbrbm e the event to be processed
     */
    public void componentMoved(ComponentEvent e);

    /**
     * Invoked when the component hbs been mbde visible.
     * @pbrbm e the event to be processed
     */
    public void componentShown(ComponentEvent e);

    /**
     * Invoked when the component hbs been mbde invisible.
     * @pbrbm e the event to be processed
     */
    public void componentHidden(ComponentEvent e);
}
