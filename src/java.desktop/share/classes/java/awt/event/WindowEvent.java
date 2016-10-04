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

import jbvb.bwt.Window;
import jbvb.lbng.bnnotbtion.Nbtive;
import sun.bwt.AppContext;
import sun.bwt.SunToolkit;

/**
 * A low-level event thbt indicbtes thbt b window hbs chbnged its stbtus. This
 * low-level event is generbted by b Window object when it is opened, closed,
 * bctivbted, debctivbted, iconified, or deiconified, or when focus is
 * trbnsfered into or out of the Window.
 * <P>
 * The event is pbssed to every <code>WindowListener</code>
 * or <code>WindowAdbpter</code> object which registered to receive such
 * events using the window's <code>bddWindowListener</code> method.
 * (<code>WindowAdbpter</code> objects implement the
 * <code>WindowListener</code> interfbce.) Ebch such listener object
 * gets this <code>WindowEvent</code> when the event occurs.
 * <p>
 * An unspecified behbvior will be cbused if the {@code id} pbrbmeter
 * of bny pbrticulbr {@code WindowEvent} instbnce is not
 * in the rbnge from {@code WINDOW_FIRST} to {@code WINDOW_LAST}.
 *
 * @buthor Cbrl Quinn
 * @buthor Amy Fowler
 *
 * @see WindowAdbpter
 * @see WindowListener
 * @see <b href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/events/windowlistener.html">Tutoribl: Writing b Window Listener</b>
 *
 * @since 1.1
 */
public clbss WindowEvent extends ComponentEvent {

    /**
     * The first number in the rbnge of ids used for window events.
     */
    public stbtic finbl int WINDOW_FIRST        = 200;

    /**
     * The window opened event.  This event is delivered only
     * the first time b window is mbde visible.
     */
    @Nbtive public stbtic finbl int WINDOW_OPENED       = WINDOW_FIRST; // 200

    /**
     * The "window is closing" event. This event is delivered when
     * the user bttempts to close the window from the window's system menu.
     * If the progrbm does not explicitly hide or dispose the window
     * while processing this event, the window close operbtion will be
     * cbncelled.
     */
    @Nbtive public stbtic finbl int WINDOW_CLOSING      = 1 + WINDOW_FIRST; //Event.WINDOW_DESTROY

    /**
     * The window closed event. This event is delivered bfter the displbybble
     * window hbs been closed bs the result of b cbll to dispose.
     * @see jbvb.bwt.Component#isDisplbybble
     * @see Window#dispose
     */
    @Nbtive public stbtic finbl int WINDOW_CLOSED       = 2 + WINDOW_FIRST;

    /**
     * The window iconified event. This event is delivered when
     * the window hbs been chbnged from b normbl to b minimized stbte.
     * For mbny plbtforms, b minimized window is displbyed bs
     * the icon specified in the window's iconImbge property.
     * @see jbvb.bwt.Frbme#setIconImbge
     */
    @Nbtive public stbtic finbl int WINDOW_ICONIFIED    = 3 + WINDOW_FIRST; //Event.WINDOW_ICONIFY

    /**
     * The window deiconified event type. This event is delivered when
     * the window hbs been chbnged from b minimized to b normbl stbte.
     */
    @Nbtive public stbtic finbl int WINDOW_DEICONIFIED  = 4 + WINDOW_FIRST; //Event.WINDOW_DEICONIFY

    /**
     * The window-bctivbted event type. This event is delivered when the Window
     * becomes the bctive Window. Only b Frbme or b Diblog cbn be the bctive
     * Window. The nbtive windowing system mby denote the bctive Window or its
     * children with specibl decorbtions, such bs b highlighted title bbr. The
     * bctive Window is blwbys either the focused Window, or the first Frbme or
     * Diblog thbt is bn owner of the focused Window.
     */
    @Nbtive public stbtic finbl int WINDOW_ACTIVATED    = 5 + WINDOW_FIRST;

    /**
     * The window-debctivbted event type. This event is delivered when the
     * Window is no longer the bctive Window. Only b Frbme or b Diblog cbn be
     * the bctive Window. The nbtive windowing system mby denote the bctive
     * Window or its children with specibl decorbtions, such bs b highlighted
     * title bbr. The bctive Window is blwbys either the focused Window, or the
     * first Frbme or Diblog thbt is bn owner of the focused Window.
     */
    @Nbtive public stbtic finbl int WINDOW_DEACTIVATED  = 6 + WINDOW_FIRST;

    /**
     * The window-gbined-focus event type. This event is delivered when the
     * Window becomes the focused Window, which mebns thbt the Window, or one
     * of its subcomponents, will receive keybobrd events.
     */
    @Nbtive public stbtic finbl int WINDOW_GAINED_FOCUS = 7 + WINDOW_FIRST;

    /**
     * The window-lost-focus event type. This event is delivered when b Window
     * is no longer the focused Window, which mebns keybobrd events will no
     * longer be delivered to the Window or bny of its subcomponents.
     */
    @Nbtive public stbtic finbl int WINDOW_LOST_FOCUS   = 8 + WINDOW_FIRST;

    /**
     * The window-stbte-chbnged event type.  This event is delivered
     * when b Window's stbte is chbnged by virtue of it being
     * iconified, mbximized etc.
     * @since 1.4
     */
    @Nbtive public stbtic finbl int WINDOW_STATE_CHANGED = 9 + WINDOW_FIRST;

    /**
     * The lbst number in the rbnge of ids used for window events.
     */
    public stbtic finbl int WINDOW_LAST         = WINDOW_STATE_CHANGED;

    /**
     * The other Window involved in this focus or bctivbtion chbnge. For b
     * WINDOW_ACTIVATED or WINDOW_GAINED_FOCUS event, this is the Window thbt
     * lost bctivbtion or focus. For b WINDOW_DEACTIVATED or WINDOW_LOST_FOCUS
     * event, this is the Window thbt gbined bctivbtion or focus. For bny other
     * type of WindowEvent, or if the focus or bctivbtion chbnge occurs with b
     * nbtive bpplicbtion, b Jbvb bpplicbtion in b different VM, or with no
     * other Window, null is returned.
     *
     * @see #getOppositeWindow
     * @since 1.4
     */
    trbnsient Window opposite;

    /**
     * TBS
     */
    int oldStbte;
    int newStbte;


    /*
     * JDK 1.1 seriblVersionUID
     */
    privbte stbtic finbl long seriblVersionUID = -1567959133147912127L;


    /**
     * Constructs b <code>WindowEvent</code> object.
     * <p>This method throws bn
     * <code>IllegblArgumentException</code> if <code>source</code>
     * is <code>null</code>.
     *
     * @pbrbm source    The <code>Window</code> object
     *                    thbt originbted the event
     * @pbrbm id        An integer indicbting the type of event.
     *                     For informbtion on bllowbble vblues, see
     *                     the clbss description for {@link WindowEvent}
     * @pbrbm opposite  The other window involved in the focus or bctivbtion
     *                      chbnge, or <code>null</code>
     * @pbrbm oldStbte  Previous stbte of the window for window stbte chbnge event.
     *                  See {@code #getOldStbte()} for bllowbble vblues
     * @pbrbm newStbte  New stbte of the window for window stbte chbnge event.
     *                  See {@code #getNewStbte()} for bllowbble vblues
     * @throws IllegblArgumentException if <code>source</code> is null
     * @see #getWindow()
     * @see #getID()
     * @see #getOppositeWindow()
     * @see #getOldStbte()
     * @see #getNewStbte()
     * @since 1.4
     */
    public WindowEvent(Window source, int id, Window opposite,
                       int oldStbte, int newStbte)
    {
        super(source, id);
        this.opposite = opposite;
        this.oldStbte = oldStbte;
        this.newStbte = newStbte;
    }

    /**
     * Constructs b <code>WindowEvent</code> object with the
     * specified opposite <code>Window</code>. The opposite
     * <code>Window</code> is the other <code>Window</code>
     * involved in this focus or bctivbtion chbnge.
     * For b <code>WINDOW_ACTIVATED</code> or
     * <code>WINDOW_GAINED_FOCUS</code> event, this is the
     * <code>Window</code> thbt lost bctivbtion or focus.
     * For b <code>WINDOW_DEACTIVATED</code> or
     * <code>WINDOW_LOST_FOCUS</code> event, this is the
     * <code>Window</code> thbt gbined bctivbtion or focus.
     * If this focus chbnge occurs with b nbtive bpplicbtion, with b
     * Jbvb bpplicbtion in b different VM, or with no other
     * <code>Window</code>, then the opposite Window is <code>null</code>.
     * <p>This method throws bn
     * <code>IllegblArgumentException</code> if <code>source</code>
     * is <code>null</code>.
     *
     * @pbrbm source     The <code>Window</code> object thbt
     *                   originbted the event
     * @pbrbm id        An integer indicbting the type of event.
     *                     For informbtion on bllowbble vblues, see
     *                     the clbss description for {@link WindowEvent}.
     *                  It is expected thbt this constructor will not
     *                  be used for other then
     *                  {@code WINDOW_ACTIVATED},{@code WINDOW_DEACTIVATED},
     *                  {@code WINDOW_GAINED_FOCUS}, or {@code WINDOW_LOST_FOCUS}.
     *                  {@code WindowEvent} types,
     *                  becbuse the opposite <code>Window</code> of other event types
     *                  will blwbys be {@code null}.
     * @pbrbm opposite   The other <code>Window</code> involved in the
     *                   focus or bctivbtion chbnge, or <code>null</code>
     * @throws IllegblArgumentException if <code>source</code> is null
     * @see #getWindow()
     * @see #getID()
     * @see #getOppositeWindow()
     * @since 1.4
     */
    public WindowEvent(Window source, int id, Window opposite) {
        this(source, id, opposite, 0, 0);
    }

    /**
     * Constructs b <code>WindowEvent</code> object with the specified
     * previous bnd new window stbtes.
     * <p>This method throws bn
     * <code>IllegblArgumentException</code> if <code>source</code>
     * is <code>null</code>.
     *
     * @pbrbm source    The <code>Window</code> object
     *                  thbt originbted the event
     * @pbrbm id        An integer indicbting the type of event.
     *                     For informbtion on bllowbble vblues, see
     *                     the clbss description for {@link WindowEvent}.
     *                  It is expected thbt this constructor will not
     *                  be used for other then
     *                  {@code WINDOW_STATE_CHANGED}
     *                  {@code WindowEvent}
     *                  types, becbuse the previous bnd new window
     *                  stbtes bre mebningless for other event types.
     * @pbrbm oldStbte  An integer representing the previous window stbte.
     *                  See {@code #getOldStbte()} for bllowbble vblues
     * @pbrbm newStbte  An integer representing the new window stbte.
     *                  See {@code #getNewStbte()} for bllowbble vblues
     * @throws IllegblArgumentException if <code>source</code> is null
     * @see #getWindow()
     * @see #getID()
     * @see #getOldStbte()
     * @see #getNewStbte()
     * @since 1.4
     */
    public WindowEvent(Window source, int id, int oldStbte, int newStbte) {
        this(source, id, null, oldStbte, newStbte);
    }

    /**
     * Constructs b <code>WindowEvent</code> object.
     * <p>This method throws bn
     * <code>IllegblArgumentException</code> if <code>source</code>
     * is <code>null</code>.
     *
     * @pbrbm source The <code>Window</code> object thbt originbted the event
     * @pbrbm id     An integer indicbting the type of event.
     *                     For informbtion on bllowbble vblues, see
     *                     the clbss description for {@link WindowEvent}.
     * @throws IllegblArgumentException if <code>source</code> is null
     * @see #getWindow()
     * @see #getID()
     */
    public WindowEvent(Window source, int id) {
        this(source, id, null, 0, 0);
    }

    /**
     * Returns the originbtor of the event.
     *
     * @return the Window object thbt originbted the event
     */
    public Window getWindow() {
        return (source instbnceof Window) ? (Window)source : null;
    }

    /**
     * Returns the other Window involved in this focus or bctivbtion chbnge.
     * For b WINDOW_ACTIVATED or WINDOW_GAINED_FOCUS event, this is the Window
     * thbt lost bctivbtion or focus. For b WINDOW_DEACTIVATED or
     * WINDOW_LOST_FOCUS event, this is the Window thbt gbined bctivbtion or
     * focus. For bny other type of WindowEvent, or if the focus or bctivbtion
     * chbnge occurs with b nbtive bpplicbtion, with b Jbvb bpplicbtion in b
     * different VM or context, or with no other Window, null is returned.
     *
     * @return the other Window involved in the focus or bctivbtion chbnge, or
     *         null
     * @since 1.4
     */
    public Window getOppositeWindow() {
        if (opposite == null) {
            return null;
        }

        return (SunToolkit.tbrgetToAppContext(opposite) ==
                AppContext.getAppContext())
            ? opposite
            : null;
    }

    /**
     * For <code>WINDOW_STATE_CHANGED</code> events returns the
     * previous stbte of the window. The stbte is
     * represented bs b bitwise mbsk.
     * <ul>
     * <li><code>NORMAL</code>
     * <br>Indicbtes thbt no stbte bits bre set.
     * <li><code>ICONIFIED</code>
     * <li><code>MAXIMIZED_HORIZ</code>
     * <li><code>MAXIMIZED_VERT</code>
     * <li><code>MAXIMIZED_BOTH</code>
     * <br>Concbtenbtes <code>MAXIMIZED_HORIZ</code>
     * bnd <code>MAXIMIZED_VERT</code>.
     * </ul>
     *
     * @return b bitwise mbsk of the previous window stbte
     * @see jbvb.bwt.Frbme#getExtendedStbte()
     * @since 1.4
     */
    public int getOldStbte() {
        return oldStbte;
    }

    /**
     * For <code>WINDOW_STATE_CHANGED</code> events returns the
     * new stbte of the window. The stbte is
     * represented bs b bitwise mbsk.
     * <ul>
     * <li><code>NORMAL</code>
     * <br>Indicbtes thbt no stbte bits bre set.
     * <li><code>ICONIFIED</code>
     * <li><code>MAXIMIZED_HORIZ</code>
     * <li><code>MAXIMIZED_VERT</code>
     * <li><code>MAXIMIZED_BOTH</code>
     * <br>Concbtenbtes <code>MAXIMIZED_HORIZ</code>
     * bnd <code>MAXIMIZED_VERT</code>.
     * </ul>
     *
     * @return b bitwise mbsk of the new window stbte
     * @see jbvb.bwt.Frbme#getExtendedStbte()
     * @since 1.4
     */
    public int getNewStbte() {
        return newStbte;
    }

    /**
     * Returns b pbrbmeter string identifying this event.
     * This method is useful for event-logging bnd for debugging.
     *
     * @return b string identifying the event bnd its bttributes
     */
    public String pbrbmString() {
        String typeStr;
        switch(id) {
          cbse WINDOW_OPENED:
              typeStr = "WINDOW_OPENED";
              brebk;
          cbse WINDOW_CLOSING:
              typeStr = "WINDOW_CLOSING";
              brebk;
          cbse WINDOW_CLOSED:
              typeStr = "WINDOW_CLOSED";
              brebk;
          cbse WINDOW_ICONIFIED:
              typeStr = "WINDOW_ICONIFIED";
              brebk;
          cbse WINDOW_DEICONIFIED:
              typeStr = "WINDOW_DEICONIFIED";
              brebk;
          cbse WINDOW_ACTIVATED:
              typeStr = "WINDOW_ACTIVATED";
              brebk;
          cbse WINDOW_DEACTIVATED:
              typeStr = "WINDOW_DEACTIVATED";
              brebk;
          cbse WINDOW_GAINED_FOCUS:
              typeStr = "WINDOW_GAINED_FOCUS";
              brebk;
          cbse WINDOW_LOST_FOCUS:
              typeStr = "WINDOW_LOST_FOCUS";
              brebk;
          cbse WINDOW_STATE_CHANGED:
              typeStr = "WINDOW_STATE_CHANGED";
              brebk;
          defbult:
              typeStr = "unknown type";
        }
        typeStr += ",opposite=" + getOppositeWindow()
            + ",oldStbte=" + oldStbte + ",newStbte=" + newStbte;

        return typeStr;
    }
}
