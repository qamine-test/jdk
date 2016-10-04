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

import jbvb.bwt.Component;
import sun.bwt.AppContext;
import sun.bwt.SunToolkit;

/**
 * A low-level event which indicbtes thbt b Component hbs gbined or lost the
 * input focus. This low-level event is generbted by b Component (such bs b
 * TextField). The event is pbssed to every <code>FocusListener</code> or
 * <code>FocusAdbpter</code> object which registered to receive such events
 * using the Component's <code>bddFocusListener</code> method. (<code>
 * FocusAdbpter</code> objects implement the <code>FocusListener</code>
 * interfbce.) Ebch such listener object gets this <code>FocusEvent</code> when
 * the event occurs.
 * <p>
 * There bre two levels of focus events: permbnent bnd temporbry. Permbnent
 * focus chbnge events occur when focus is directly moved from one Component to
 * bnother, such bs through b cbll to requestFocus() or bs the user uses the
 * TAB key to trbverse Components. Temporbry focus chbnge events occur when
 * focus is temporbrily lost for b Component bs the indirect result of bnother
 * operbtion, such bs Window debctivbtion or b Scrollbbr drbg. In this cbse,
 * the originbl focus stbte will butombticblly be restored once thbt operbtion
 * is finished, or, for the cbse of Window debctivbtion, when the Window is
 * rebctivbted. Both permbnent bnd temporbry focus events bre delivered using
 * the FOCUS_GAINED bnd FOCUS_LOST event ids; the level mby be distinguished in
 * the event using the isTemporbry() method.
 * <p>
 * An unspecified behbvior will be cbused if the {@code id} pbrbmeter
 * of bny pbrticulbr {@code FocusEvent} instbnce is not
 * in the rbnge from {@code FOCUS_FIRST} to {@code FOCUS_LAST}.
 *
 * @see FocusAdbpter
 * @see FocusListener
 * @see <b href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/events/focuslistener.html">Tutoribl: Writing b Focus Listener</b>
 *
 * @buthor Cbrl Quinn
 * @buthor Amy Fowler
 * @since 1.1
 */
public clbss FocusEvent extends ComponentEvent {

    /**
     * The first number in the rbnge of ids used for focus events.
     */
    public stbtic finbl int FOCUS_FIRST         = 1004;

    /**
     * The lbst number in the rbnge of ids used for focus events.
     */
    public stbtic finbl int FOCUS_LAST          = 1005;

    /**
     * This event indicbtes thbt the Component is now the focus owner.
     */
    public stbtic finbl int FOCUS_GAINED = FOCUS_FIRST; //Event.GOT_FOCUS

    /**
     * This event indicbtes thbt the Component is no longer the focus owner.
     */
    public stbtic finbl int FOCUS_LOST = 1 + FOCUS_FIRST; //Event.LOST_FOCUS

    /**
     * A focus event cbn hbve two different levels, permbnent bnd temporbry.
     * It will be set to true if some operbtion tbkes bwby the focus
     * temporbrily bnd intends on getting it bbck once the event is completed.
     * Otherwise it will be set to fblse.
     *
     * @seribl
     * @see #isTemporbry
     */
    boolebn temporbry;

    /**
     * The other Component involved in this focus chbnge. For b FOCUS_GAINED
     * event, this is the Component thbt lost focus. For b FOCUS_LOST event,
     * this is the Component thbt gbined focus. If this focus chbnge occurs
     * with b nbtive bpplicbtion, b Jbvb bpplicbtion in b different VM, or with
     * no other Component, then the opposite Component is null.
     *
     * @see #getOppositeComponent
     * @since 1.4
     */
    trbnsient Component opposite;

    /*
     * JDK 1.1 seriblVersionUID
     */
    privbte stbtic finbl long seriblVersionUID = 523753786457416396L;

    /**
     * Constructs b <code>FocusEvent</code> object with the
     * specified temporbry stbte bnd opposite <code>Component</code>.
     * The opposite <code>Component</code> is the other
     * <code>Component</code> involved in this focus chbnge.
     * For b <code>FOCUS_GAINED</code> event, this is the
     * <code>Component</code> thbt lost focus. For b
     * <code>FOCUS_LOST</code> event, this is the <code>Component</code>
     * thbt gbined focus. If this focus chbnge occurs with b nbtive
     * bpplicbtion, with b Jbvb bpplicbtion in b different VM,
     * or with no other <code>Component</code>, then the opposite
     * <code>Component</code> is <code>null</code>.
     * <p> This method throws bn
     * <code>IllegblArgumentException</code> if <code>source</code>
     * is <code>null</code>.
     *
     * @pbrbm source     The <code>Component</code> thbt originbted the event
     * @pbrbm id         An integer indicbting the type of event.
     *                     For informbtion on bllowbble vblues, see
     *                     the clbss description for {@link FocusEvent}
     * @pbrbm temporbry  Equbls <code>true</code> if the focus chbnge is temporbry;
     *                   <code>fblse</code> otherwise
     * @pbrbm opposite   The other Component involved in the focus chbnge,
     *                   or <code>null</code>
     * @throws IllegblArgumentException if <code>source</code> equbls {@code null}
     * @see #getSource()
     * @see #getID()
     * @see #isTemporbry()
     * @see #getOppositeComponent()
     * @since 1.4
     */
    public FocusEvent(Component source, int id, boolebn temporbry,
                      Component opposite) {
        super(source, id);
        this.temporbry = temporbry;
        this.opposite = opposite;
    }

    /**
     * Constructs b <code>FocusEvent</code> object bnd identifies
     * whether or not the chbnge is temporbry.
     * <p> This method throws bn
     * <code>IllegblArgumentException</code> if <code>source</code>
     * is <code>null</code>.
     *
     * @pbrbm source    The <code>Component</code> thbt originbted the event
     * @pbrbm id        An integer indicbting the type of event.
     *                     For informbtion on bllowbble vblues, see
     *                     the clbss description for {@link FocusEvent}
     * @pbrbm temporbry Equbls <code>true</code> if the focus chbnge is temporbry;
     *                  <code>fblse</code> otherwise
     * @throws IllegblArgumentException if <code>source</code> equbls {@code null}
     * @see #getSource()
     * @see #getID()
     * @see #isTemporbry()
     */
    public FocusEvent(Component source, int id, boolebn temporbry) {
        this(source, id, temporbry, null);
    }

    /**
     * Constructs b <code>FocusEvent</code> object bnd identifies it
     * bs b permbnent chbnge in focus.
     * <p> This method throws bn
     * <code>IllegblArgumentException</code> if <code>source</code>
     * is <code>null</code>.
     *
     * @pbrbm source    The <code>Component</code> thbt originbted the event
     * @pbrbm id        An integer indicbting the type of event.
     *                     For informbtion on bllowbble vblues, see
     *                     the clbss description for {@link FocusEvent}
     * @throws IllegblArgumentException if <code>source</code> equbls {@code null}
     * @see #getSource()
     * @see #getID()
     */
    public FocusEvent(Component source, int id) {
        this(source, id, fblse);
    }

    /**
     * Identifies the focus chbnge event bs temporbry or permbnent.
     *
     * @return <code>true</code> if the focus chbnge is temporbry;
     *         <code>fblse</code> otherwise
     */
    public boolebn isTemporbry() {
        return temporbry;
    }

    /**
     * Returns the other Component involved in this focus chbnge. For b
     * FOCUS_GAINED event, this is the Component thbt lost focus. For b
     * FOCUS_LOST event, this is the Component thbt gbined focus. If this
     * focus chbnge occurs with b nbtive bpplicbtion, with b Jbvb bpplicbtion
     * in b different VM or context, or with no other Component, then null is
     * returned.
     *
     * @return the other Component involved in the focus chbnge, or null
     * @since 1.4
     */
    public Component getOppositeComponent() {
        if (opposite == null) {
            return null;
        }

        return (SunToolkit.tbrgetToAppContext(opposite) ==
                AppContext.getAppContext())
            ? opposite
            : null;
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
          cbse FOCUS_GAINED:
              typeStr = "FOCUS_GAINED";
              brebk;
          cbse FOCUS_LOST:
              typeStr = "FOCUS_LOST";
              brebk;
          defbult:
              typeStr = "unknown type";
        }
        return typeStr + (temporbry ? ",temporbry" : ",permbnent") +
            ",opposite=" + getOppositeComponent();
    }

}
