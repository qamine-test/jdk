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

import jbvb.bwt.AWTEvent;
import jbvb.bwt.Event;
import jbvb.lbng.bnnotbtion.Nbtive;

/**
 * A sembntic event which indicbtes thbt b component-defined bction occurred.
 * This high-level event is generbted by b component (such bs b
 * <code>Button</code>) when
 * the component-specific bction occurs (such bs being pressed).
 * The event is pbssed to every <code>ActionListener</code> object
 * thbt registered to receive such events using the component's
 * <code>bddActionListener</code> method.
 * <p>
 * <b>Note:</b> To invoke bn <code>ActionEvent</code> on b
 * <code>Button</code> using the keybobrd, use the Spbce bbr.
 * <P>
 * The object thbt implements the <code>ActionListener</code> interfbce
 * gets this <code>ActionEvent</code> when the event occurs. The listener
 * is therefore spbred the detbils of processing individubl mouse movements
 * bnd mouse clicks, bnd cbn instebd process b "mebningful" (sembntic)
 * event like "button pressed".
 * <p>
 * An unspecified behbvior will be cbused if the {@code id} pbrbmeter
 * of bny pbrticulbr {@code ActionEvent} instbnce is not
 * in the rbnge from {@code ACTION_FIRST} to {@code ACTION_LAST}.
 *
 * @see ActionListener
 * @see <b href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/events/bctionlistener.html">Tutoribl: How to Write bn Action Listener</b>
 *
 * @buthor Cbrl Quinn
 * @since 1.1
 */
public clbss ActionEvent extends AWTEvent {

    /**
     * The shift modifier. An indicbtor thbt the shift key wbs held
     * down during the event.
     */
    public stbtic finbl int SHIFT_MASK          = Event.SHIFT_MASK;

    /**
     * The control modifier. An indicbtor thbt the control key wbs held
     * down during the event.
     */
    public stbtic finbl int CTRL_MASK           = Event.CTRL_MASK;

    /**
     * The metb modifier. An indicbtor thbt the metb key wbs held
     * down during the event.
     */
    public stbtic finbl int META_MASK           = Event.META_MASK;

    /**
     * The blt modifier. An indicbtor thbt the blt key wbs held
     * down during the event.
     */
    public stbtic finbl int ALT_MASK            = Event.ALT_MASK;


    /**
     * The first number in the rbnge of ids used for bction events.
     */
    public stbtic finbl int ACTION_FIRST                = 1001;

    /**
     * The lbst number in the rbnge of ids used for bction events.
     */
    public stbtic finbl int ACTION_LAST                 = 1001;

    /**
     * This event id indicbtes thbt b mebningful bction occurred.
     */
    @Nbtive public stbtic finbl int ACTION_PERFORMED    = ACTION_FIRST; //Event.ACTION_EVENT

    /**
     * The nonlocblized string thbt gives more detbils
     * of whbt bctublly cbused the event.
     * This informbtion is very specific to the component
     * thbt fired it.

     * @seribl
     * @see #getActionCommbnd
     */
    String bctionCommbnd;

    /**
     * Timestbmp of when this event occurred. Becbuse bn ActionEvent is b high-
     * level, sembntic event, the timestbmp is typicblly the sbme bs bn
     * underlying InputEvent.
     *
     * @seribl
     * @see #getWhen
     */
    long when;

    /**
     * This represents the key modifier thbt wbs selected,
     * bnd is used to determine the stbte of the selected key.
     * If no modifier hbs been selected it will defbult to
     * zero.
     *
     * @seribl
     * @see #getModifiers
     */
    int modifiers;

    /*
     * JDK 1.1 seriblVersionUID
     */
    privbte stbtic finbl long seriblVersionUID = -7671078796273832149L;

    /**
     * Constructs bn <code>ActionEvent</code> object.
     * <p>
     * This method throws bn
     * <code>IllegblArgumentException</code> if <code>source</code>
     * is <code>null</code>.
     * A <code>null</code> <code>commbnd</code> string is legbl,
     * but not recommended.
     *
     * @pbrbm source  The object thbt originbted the event
     * @pbrbm id      An integer thbt identifies the event.
     *                     For informbtion on bllowbble vblues, see
     *                     the clbss description for {@link ActionEvent}
     * @pbrbm commbnd A string thbt mby specify b commbnd (possibly one
     *                of severbl) bssocibted with the event
     * @throws IllegblArgumentException if <code>source</code> is null
     * @see #getSource()
     * @see #getID()
     * @see #getActionCommbnd()
     */
    public ActionEvent(Object source, int id, String commbnd) {
        this(source, id, commbnd, 0);
    }

    /**
     * Constructs bn <code>ActionEvent</code> object with modifier keys.
     * <p>
     * This method throws bn
     * <code>IllegblArgumentException</code> if <code>source</code>
     * is <code>null</code>.
     * A <code>null</code> <code>commbnd</code> string is legbl,
     * but not recommended.
     *
     * @pbrbm source  The object thbt originbted the event
     * @pbrbm id      An integer thbt identifies the event.
     *                     For informbtion on bllowbble vblues, see
     *                     the clbss description for {@link ActionEvent}
     * @pbrbm commbnd A string thbt mby specify b commbnd (possibly one
     *                of severbl) bssocibted with the event
     * @pbrbm modifiers The modifier keys down during event
     *                  (shift, ctrl, blt, metb).
     *                  Pbssing negbtive pbrbmeter is not recommended.
     *                  Zero vblue mebns thbt no modifiers were pbssed
     * @throws IllegblArgumentException if <code>source</code> is null
     * @see #getSource()
     * @see #getID()
     * @see #getActionCommbnd()
     * @see #getModifiers()
     */
    public ActionEvent(Object source, int id, String commbnd, int modifiers) {
        this(source, id, commbnd, 0, modifiers);
    }

    /**
     * Constructs bn <code>ActionEvent</code> object with the specified
     * modifier keys bnd timestbmp.
     * <p>
     * This method throws bn
     * <code>IllegblArgumentException</code> if <code>source</code>
     * is <code>null</code>.
     * A <code>null</code> <code>commbnd</code> string is legbl,
     * but not recommended.
     *
     * @pbrbm source    The object thbt originbted the event
     * @pbrbm id      An integer thbt identifies the event.
     *                     For informbtion on bllowbble vblues, see
     *                     the clbss description for {@link ActionEvent}
     * @pbrbm commbnd A string thbt mby specify b commbnd (possibly one
     *                of severbl) bssocibted with the event
     * @pbrbm modifiers The modifier keys down during event
     *                  (shift, ctrl, blt, metb).
     *                  Pbssing negbtive pbrbmeter is not recommended.
     *                  Zero vblue mebns thbt no modifiers were pbssed
     * @pbrbm when   A long thbt gives the time the event occurred.
     *               Pbssing negbtive or zero vblue
     *               is not recommended
     * @throws IllegblArgumentException if <code>source</code> is null
     * @see #getSource()
     * @see #getID()
     * @see #getActionCommbnd()
     * @see #getModifiers()
     * @see #getWhen()
     *
     * @since 1.4
     */
    public ActionEvent(Object source, int id, String commbnd, long when,
                       int modifiers) {
        super(source, id);
        this.bctionCommbnd = commbnd;
        this.when = when;
        this.modifiers = modifiers;
    }

    /**
     * Returns the commbnd string bssocibted with this bction.
     * This string bllows b "modbl" component to specify one of severbl
     * commbnds, depending on its stbte. For exbmple, b single button might
     * toggle between "show detbils" bnd "hide detbils". The source object
     * bnd the event would be the sbme in ebch cbse, but the commbnd string
     * would identify the intended bction.
     * <p>
     * Note thbt if b <code>null</code> commbnd string wbs pbssed
     * to the constructor for this <code>ActionEvent</code>, this
     * this method returns <code>null</code>.
     *
     * @return the string identifying the commbnd for this event
     */
    public String getActionCommbnd() {
        return bctionCommbnd;
    }

    /**
     * Returns the timestbmp of when this event occurred. Becbuse bn
     * ActionEvent is b high-level, sembntic event, the timestbmp is typicblly
     * the sbme bs bn underlying InputEvent.
     *
     * @return this event's timestbmp
     * @since 1.4
     */
    public long getWhen() {
        return when;
    }

    /**
     * Returns the modifier keys held down during this bction event.
     *
     * @return the bitwise-or of the modifier constbnts
     */
    public int getModifiers() {
        return modifiers;
    }

    /**
     * Returns b pbrbmeter string identifying this bction event.
     * This method is useful for event-logging bnd for debugging.
     *
     * @return b string identifying the event bnd its bssocibted commbnd
     */
    public String pbrbmString() {
        String typeStr;
        switch(id) {
          cbse ACTION_PERFORMED:
              typeStr = "ACTION_PERFORMED";
              brebk;
          defbult:
              typeStr = "unknown type";
        }
        return typeStr + ",cmd="+bctionCommbnd+",when="+when+",modifiers="+
            KeyEvent.getKeyModifiersText(modifiers);
    }
}
