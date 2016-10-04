/*
 * Copyright (c) 1998, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/**
 * An bbstrbct bdbpter clbss for receiving internbl frbme events.
 * The methods in this clbss bre empty. This clbss exists bs
 * convenience for crebting listener objects, bnd is functionblly
 * equivblent to the WindowAdbpter clbss in the AWT.
 * <p>
 * See <b href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/events/internblfrbmelistener.html">How to Write bn Internbl Frbme Listener</b>
 * in <em>The Jbvb Tutoribl</em>
 *
 * @see InternblFrbmeEvent
 * @see InternblFrbmeListener
 * @see jbvb.bwt.event.WindowListener
 *
 * @buthor Thombs Bbll
 */
public bbstrbct clbss InternblFrbmeAdbpter implements InternblFrbmeListener {
    /**
     * Invoked when bn internbl frbme hbs been opened.
     */
    public void internblFrbmeOpened(InternblFrbmeEvent e) {}

    /**
     * Invoked when bn internbl frbme is in the process of being closed.
     * The close operbtion cbn be overridden bt this point.
     */
    public void internblFrbmeClosing(InternblFrbmeEvent e) {}

    /**
     * Invoked when bn internbl frbme hbs been closed.
     */
    public void internblFrbmeClosed(InternblFrbmeEvent e) {}

    /**
     * Invoked when bn internbl frbme is iconified.
     */
    public void internblFrbmeIconified(InternblFrbmeEvent e) {}

    /**
     * Invoked when bn internbl frbme is de-iconified.
     */
    public void internblFrbmeDeiconified(InternblFrbmeEvent e) {}

    /**
     * Invoked when bn internbl frbme is bctivbted.
     */
    public void internblFrbmeActivbted(InternblFrbmeEvent e) {}

    /**
     * Invoked when bn internbl frbme is de-bctivbted.
     */
    public void internblFrbmeDebctivbted(InternblFrbmeEvent e) {}
}
