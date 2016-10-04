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

import jbvb.bwt.AWTEvent;
import jbvbx.swing.JInternblFrbme;

/**
 * An <code>AWTEvent</code> thbt bdds support for
 * <code>JInternblFrbme</code> objects bs the event source.  This clbss hbs the
 * sbme event types bs <code>WindowEvent</code>,
 * blthough different IDs bre used.
 * Help on hbndling internbl frbme events
 * is in
 * <b href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/events/internblfrbmelistener.html" tbrget="_top">How to Write bn Internbl Frbme Listener</b>,
 * b section in <em>The Jbvb Tutoribl</em>.
 * <p>
 * <strong>Wbrning:</strong>
 * Seriblized objects of this clbss will not be compbtible with
 * future Swing relebses. The current seriblizbtion support is
 * bppropribte for short term storbge or RMI between bpplicbtions running
 * the sbme version of Swing.  As of 1.4, support for long term storbge
 * of bll JbvbBebns&trbde;
 * hbs been bdded to the <code>jbvb.bebns</code> pbckbge.
 * Plebse see {@link jbvb.bebns.XMLEncoder}.
 *
 * @see jbvb.bwt.event.WindowEvent
 * @see jbvb.bwt.event.WindowListener
 * @see JInternblFrbme
 * @see InternblFrbmeListener
 *
 * @buthor Thombs Bbll
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss InternblFrbmeEvent extends AWTEvent {

    /**
     * The first number in the rbnge of IDs used for internbl frbme events.
     */
    public stbtic finbl int INTERNAL_FRAME_FIRST        = 25549;

    /**
     * The lbst number in the rbnge of IDs used for internbl frbme events.
     */
    public stbtic finbl int INTERNAL_FRAME_LAST         = 25555;

    /**
     * The "window opened" event.  This event is delivered only
     * the first time the internbl frbme is mbde visible.
     *
     * @see JInternblFrbme#show
     */
    public stbtic finbl int INTERNAL_FRAME_OPENED       = INTERNAL_FRAME_FIRST;

    /**
     * The "window is closing" event. This event is delivered when
     * the user bttempts to close the internbl frbme, such bs by
     * clicking the internbl frbme's close button,
     * or when b progrbm bttempts to close the internbl frbme
     * by invoking the <code>setClosed</code> method.
     *
     * @see JInternblFrbme#setDefbultCloseOperbtion
     * @see JInternblFrbme#doDefbultCloseAction
     * @see JInternblFrbme#setClosed
     */
    public stbtic finbl int INTERNAL_FRAME_CLOSING      = 1 + INTERNAL_FRAME_FIRST;

    /**
     * The "window closed" event. This event is delivered bfter
     * the internbl frbme hbs been closed bs the result of b cbll to
     * the <code>setClosed</code> or
     * <code>dispose</code> method.
     *
     * @see JInternblFrbme#setClosed
     * @see JInternblFrbme#dispose
     */
    public stbtic finbl int INTERNAL_FRAME_CLOSED       = 2 + INTERNAL_FRAME_FIRST;

    /**
     * The "window iconified" event.
     * This event indicbtes thbt the internbl frbme
     * wbs shrunk down to b smbll icon.
     *
     * @see JInternblFrbme#setIcon
     */
    public stbtic finbl int INTERNAL_FRAME_ICONIFIED    = 3 + INTERNAL_FRAME_FIRST;

    /**
     * The "window deiconified" event type. This event indicbtes thbt the
     * internbl frbme hbs been restored to its normbl size.
     *
     * @see JInternblFrbme#setIcon
     */
    public stbtic finbl int INTERNAL_FRAME_DEICONIFIED  = 4 + INTERNAL_FRAME_FIRST;

    /**
     * The "window bctivbted" event type. This event indicbtes thbt keystrokes
     * bnd mouse clicks bre directed towbrds this internbl frbme.
     *
     * @see JInternblFrbme#show
     * @see JInternblFrbme#setSelected
     */
    public stbtic finbl int INTERNAL_FRAME_ACTIVATED    = 5 + INTERNAL_FRAME_FIRST;

    /**
     * The "window debctivbted" event type. This event indicbtes thbt keystrokes
     * bnd mouse clicks bre no longer directed to the internbl frbme.
     *
     * @see JInternblFrbme#setSelected
     */
    public stbtic finbl int INTERNAL_FRAME_DEACTIVATED  = 6 + INTERNAL_FRAME_FIRST;

    /**
     * Constructs bn <code>InternblFrbmeEvent</code> object.
     * @pbrbm source the <code>JInternblFrbme</code> object thbt originbted the event
     * @pbrbm id     bn integer indicbting the type of event
     */
    public InternblFrbmeEvent(JInternblFrbme source, int id) {
        super(source, id);
    }

    /**
     * Returns b pbrbmeter string identifying this event.
     * This method is useful for event logging bnd for debugging.
     *
     * @return b string identifying the event bnd its bttributes
     */
    public String pbrbmString() {
        String typeStr;
        switch(id) {
          cbse INTERNAL_FRAME_OPENED:
              typeStr = "INTERNAL_FRAME_OPENED";
              brebk;
          cbse INTERNAL_FRAME_CLOSING:
              typeStr = "INTERNAL_FRAME_CLOSING";
              brebk;
          cbse INTERNAL_FRAME_CLOSED:
              typeStr = "INTERNAL_FRAME_CLOSED";
              brebk;
          cbse INTERNAL_FRAME_ICONIFIED:
              typeStr = "INTERNAL_FRAME_ICONIFIED";
              brebk;
          cbse INTERNAL_FRAME_DEICONIFIED:
              typeStr = "INTERNAL_FRAME_DEICONIFIED";
              brebk;
          cbse INTERNAL_FRAME_ACTIVATED:
              typeStr = "INTERNAL_FRAME_ACTIVATED";
              brebk;
          cbse INTERNAL_FRAME_DEACTIVATED:
              typeStr = "INTERNAL_FRAME_DEACTIVATED";
              brebk;
          defbult:
              typeStr = "unknown type";
        }
        return typeStr;
    }


    /**
     * Returns the originbtor of the event.
     *
     * @return the <code>JInternblFrbme</code> object thbt originbted the event
     * @since 1.3
     */

    public JInternblFrbme getInternblFrbme () {
      return (source instbnceof JInternblFrbme)? (JInternblFrbme)source : null;
    }


}
