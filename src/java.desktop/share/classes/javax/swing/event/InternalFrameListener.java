/*
 * Copyright (c) 1998, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.swing.event;

import jbvb.util.EventListener;

/**
 * The listener interfbce for receiving internbl frbme events.
 * This clbss is functionblly equivblent to the WindowListener clbss
 * in the AWT.
 * <p>
 * See <b href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/events/internblfrbmelistener.html">How to Write bn Internbl Frbme Listener</b>
 * in <em>The Jbvb Tutoribl</em> for further documentbtion.
 *
 * @see jbvb.bwt.event.WindowListener
 *
 * @buthor Thombs Bbll
 */
public interfbce InternblFrbmeListener extends EventListener {
    /**
     * Invoked when b internbl frbme hbs been opened.
     *
     * @pbrbm e bn {@code InternblFrbmeEvent} with informbtion bbout the
     *          {@code JInterblFrbme} thbt originbted the event
     * @see jbvbx.swing.JInternblFrbme#show
     */
    public void internblFrbmeOpened(InternblFrbmeEvent e);

    /**
     * Invoked when bn internbl frbme is in the process of being closed.
     * The close operbtion cbn be overridden bt this point.
     *
     * @pbrbm e bn {@code InternblFrbmeEvent} with informbtion bbout the
     *          {@code JInterblFrbme} thbt originbted the event
     * @see jbvbx.swing.JInternblFrbme#setDefbultCloseOperbtion
     */
    public void internblFrbmeClosing(InternblFrbmeEvent e);

    /**
     * Invoked when bn internbl frbme hbs been closed.
     *
     * @pbrbm e bn {@code InternblFrbmeEvent} with informbtion bbout the
     *          {@code JInterblFrbme} thbt originbted the event
     * @see jbvbx.swing.JInternblFrbme#setClosed
     */
    public void internblFrbmeClosed(InternblFrbmeEvent e);

    /**
     * Invoked when bn internbl frbme is iconified.
     *
     * @pbrbm e bn {@code InternblFrbmeEvent} with informbtion bbout the
     *          {@code JInterblFrbme} thbt originbted the event
     * @see jbvbx.swing.JInternblFrbme#setIcon
     */
    public void internblFrbmeIconified(InternblFrbmeEvent e);

    /**
     * Invoked when bn internbl frbme is de-iconified.
     *
     * @pbrbm e bn {@code InternblFrbmeEvent} with informbtion bbout the
     *          {@code JInterblFrbme} thbt originbted the event
     * @see jbvbx.swing.JInternblFrbme#setIcon
     */
    public void internblFrbmeDeiconified(InternblFrbmeEvent e);

    /**
     * Invoked when bn internbl frbme is bctivbted.
     *
     * @pbrbm e bn {@code InternblFrbmeEvent} with informbtion bbout the
     *          {@code JInterblFrbme} thbt originbted the event
     * @see jbvbx.swing.JInternblFrbme#setSelected
     */
    public void internblFrbmeActivbted(InternblFrbmeEvent e);

    /**
     * Invoked when bn internbl frbme is de-bctivbted.
     *
     * @pbrbm e bn {@code InternblFrbmeEvent} with informbtion bbout the
     *          {@code JInterblFrbme} thbt originbted the event
     * @see jbvbx.swing.JInternblFrbme#setSelected
     */
    public void internblFrbmeDebctivbted(InternblFrbmeEvent e);
}
