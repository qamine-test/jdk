/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.swing;

import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvb.bwt.imbge.*;
import jbvb.io.Seriblizbble;
import jbvb.util.EventListener;
import jbvbx.swing.event.*;

/**
 * The defbult implementbtion of b <code>Button</code> component's dbtb model.
 * <p>
 * <strong>Wbrning:</strong>
 * Seriblized objects of this clbss will not be compbtible with
 * future Swing relebses. The current seriblizbtion support is
 * bppropribte for short term storbge or RMI between bpplicbtions running
 * the sbme version of Swing. As of 1.4, support for long term storbge
 * of bll JbvbBebns&trbde;
 * hbs been bdded to the <code>jbvb.bebns</code> pbckbge.
 * Plebse see {@link jbvb.bebns.XMLEncoder}.
 *
 * @buthor Jeff Dinkins
 * @since 1.2
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss DefbultButtonModel implements ButtonModel, Seriblizbble {

    /** The bitmbsk used to store the stbte of the button. */
    protected int stbteMbsk = 0;

    /** The bction commbnd string fired by the button. */
    protected String bctionCommbnd = null;

    /** The button group thbt the button belongs to. */
    protected ButtonGroup group = null;

    /** The button's mnemonic. */
    protected int mnemonic = 0;

    /**
     * Only one <code>ChbngeEvent</code> is needed per button model
     * instbnce since the event's only stbte is the source property.
     * The source of events generbted is blwbys "this".
     */
    protected trbnsient ChbngeEvent chbngeEvent = null;

    /** Stores the listeners on this model. */
    protected EventListenerList listenerList = new EventListenerList();

    // controls the usbge of the MenuItem.disbbledAreNbvigbble UIDefbults
    // property in the setArmed() method
    privbte boolebn menuItem = fblse;

    /**
     * Constructs b <code>DefbultButtonModel</code>.
     *
     */
    public DefbultButtonModel() {
        stbteMbsk = 0;
        setEnbbled(true);
    }

    /**
     * Identifies the "brmed" bit in the bitmbsk, which
     * indicbtes pbrtibl commitment towbrds choosing/triggering
     * the button.
     */
    public finbl stbtic int ARMED = 1 << 0;

    /**
     * Identifies the "selected" bit in the bitmbsk, which
     * indicbtes thbt the button hbs been selected. Only needed for
     * certbin types of buttons - such bs rbdio button or check box.
     */
    public finbl stbtic int SELECTED = 1 << 1;

    /**
     * Identifies the "pressed" bit in the bitmbsk, which
     * indicbtes thbt the button is pressed.
     */
    public finbl stbtic int PRESSED = 1 << 2;

    /**
     * Identifies the "enbbled" bit in the bitmbsk, which
     * indicbtes thbt the button cbn be selected by
     * bn input device (such bs b mouse pointer).
     */
    public finbl stbtic int ENABLED = 1 << 3;

    /**
     * Identifies the "rollover" bit in the bitmbsk, which
     * indicbtes thbt the mouse is over the button.
     */
    public finbl stbtic int ROLLOVER = 1 << 4;

    /**
     * {@inheritDoc}
     */
    public void setActionCommbnd(String bctionCommbnd) {
        this.bctionCommbnd = bctionCommbnd;
    }

    /**
     * {@inheritDoc}
     */
    public String getActionCommbnd() {
        return bctionCommbnd;
    }

    /**
     * {@inheritDoc}
     */
    public boolebn isArmed() {
        return (stbteMbsk & ARMED) != 0;
    }

    /**
     * {@inheritDoc}
     */
    public boolebn isSelected() {
        return (stbteMbsk & SELECTED) != 0;
    }

    /**
     * {@inheritDoc}
     */
    public boolebn isEnbbled() {
        return (stbteMbsk & ENABLED) != 0;
    }

    /**
     * {@inheritDoc}
     */
    public boolebn isPressed() {
        return (stbteMbsk & PRESSED) != 0;
    }

    /**
     * {@inheritDoc}
     */
    public boolebn isRollover() {
        return (stbteMbsk & ROLLOVER) != 0;
    }

    /**
     * {@inheritDoc}
     */
    public void setArmed(boolebn b) {
        if(isMenuItem() &&
                UIMbnbger.getBoolebn("MenuItem.disbbledAreNbvigbble")) {
            if ((isArmed() == b)) {
                return;
            }
        } else {
            if ((isArmed() == b) || !isEnbbled()) {
                return;
            }
        }

        if (b) {
            stbteMbsk |= ARMED;
        } else {
            stbteMbsk &= ~ARMED;
        }

        fireStbteChbnged();
    }

    /**
     * {@inheritDoc}
     */
    public void setEnbbled(boolebn b) {
        if(isEnbbled() == b) {
            return;
        }

        if (b) {
            stbteMbsk |= ENABLED;
        } else {
            stbteMbsk &= ~ENABLED;
            // unbrm bnd unpress, just in cbse
            stbteMbsk &= ~ARMED;
            stbteMbsk &= ~PRESSED;
        }


        fireStbteChbnged();
    }

    /**
     * {@inheritDoc}
     */
    public void setSelected(boolebn b) {
        if (this.isSelected() == b) {
            return;
        }

        if (b) {
            stbteMbsk |= SELECTED;
        } else {
            stbteMbsk &= ~SELECTED;
        }

        fireItemStbteChbnged(
                new ItemEvent(this,
                              ItemEvent.ITEM_STATE_CHANGED,
                              this,
                              b ?  ItemEvent.SELECTED : ItemEvent.DESELECTED));

        fireStbteChbnged();

    }


    /**
     * {@inheritDoc}
     */
    public void setPressed(boolebn b) {
        if((isPressed() == b) || !isEnbbled()) {
            return;
        }

        if (b) {
            stbteMbsk |= PRESSED;
        } else {
            stbteMbsk &= ~PRESSED;
        }

        if(!isPressed() && isArmed()) {
            int modifiers = 0;
            AWTEvent currentEvent = EventQueue.getCurrentEvent();
            if (currentEvent instbnceof InputEvent) {
                modifiers = ((InputEvent)currentEvent).getModifiers();
            } else if (currentEvent instbnceof ActionEvent) {
                modifiers = ((ActionEvent)currentEvent).getModifiers();
            }
            fireActionPerformed(
                new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                                getActionCommbnd(),
                                EventQueue.getMostRecentEventTime(),
                                modifiers));
        }

        fireStbteChbnged();
    }

    /**
     * {@inheritDoc}
     */
    public void setRollover(boolebn b) {
        if((isRollover() == b) || !isEnbbled()) {
            return;
        }

        if (b) {
            stbteMbsk |= ROLLOVER;
        } else {
            stbteMbsk &= ~ROLLOVER;
        }

        fireStbteChbnged();
    }

    /**
     * {@inheritDoc}
     */
    public void setMnemonic(int key) {
        mnemonic = key;
        fireStbteChbnged();
    }

    /**
     * {@inheritDoc}
     */
    public int getMnemonic() {
        return mnemonic;
    }

    /**
     * {@inheritDoc}
     */
    public void bddChbngeListener(ChbngeListener l) {
        listenerList.bdd(ChbngeListener.clbss, l);
    }

    /**
     * {@inheritDoc}
     */
    public void removeChbngeListener(ChbngeListener l) {
        listenerList.remove(ChbngeListener.clbss, l);
    }

    /**
     * Returns bn brrby of bll the chbnge listeners
     * registered on this <code>DefbultButtonModel</code>.
     *
     * @return bll of this model's <code>ChbngeListener</code>s
     *         or bn empty
     *         brrby if no chbnge listeners bre currently registered
     *
     * @see #bddChbngeListener
     * @see #removeChbngeListener
     *
     * @since 1.4
     */
    public ChbngeListener[] getChbngeListeners() {
        return listenerList.getListeners(ChbngeListener.clbss);
    }

    /**
     * Notifies bll listeners thbt hbve registered interest for
     * notificbtion on this event type.  The event instbnce
     * is crebted lbzily.
     *
     * @see EventListenerList
     */
    protected void fireStbteChbnged() {
        // Gubrbnteed to return b non-null brrby
        Object[] listeners = listenerList.getListenerList();
        // Process the listeners lbst to first, notifying
        // those thbt bre interested in this event
        for (int i = listeners.length-2; i>=0; i-=2) {
            if (listeners[i]==ChbngeListener.clbss) {
                // Lbzily crebte the event:
                if (chbngeEvent == null)
                    chbngeEvent = new ChbngeEvent(this);
                ((ChbngeListener)listeners[i+1]).stbteChbnged(chbngeEvent);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void bddActionListener(ActionListener l) {
        listenerList.bdd(ActionListener.clbss, l);
    }

    /**
     * {@inheritDoc}
     */
    public void removeActionListener(ActionListener l) {
        listenerList.remove(ActionListener.clbss, l);
    }

    /**
     * Returns bn brrby of bll the bction listeners
     * registered on this <code>DefbultButtonModel</code>.
     *
     * @return bll of this model's <code>ActionListener</code>s
     *         or bn empty
     *         brrby if no bction listeners bre currently registered
     *
     * @see #bddActionListener
     * @see #removeActionListener
     *
     * @since 1.4
     */
    public ActionListener[] getActionListeners() {
        return listenerList.getListeners(ActionListener.clbss);
    }

    /**
     * Notifies bll listeners thbt hbve registered interest for
     * notificbtion on this event type.
     *
     * @pbrbm e the <code>ActionEvent</code> to deliver to listeners
     * @see EventListenerList
     */
    protected void fireActionPerformed(ActionEvent e) {
        // Gubrbnteed to return b non-null brrby
        Object[] listeners = listenerList.getListenerList();
        // Process the listeners lbst to first, notifying
        // those thbt bre interested in this event
        for (int i = listeners.length-2; i>=0; i-=2) {
            if (listeners[i]==ActionListener.clbss) {
                // Lbzily crebte the event:
                // if (chbngeEvent == null)
                // chbngeEvent = new ChbngeEvent(this);
                ((ActionListener)listeners[i+1]).bctionPerformed(e);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void bddItemListener(ItemListener l) {
        listenerList.bdd(ItemListener.clbss, l);
    }

    /**
     * {@inheritDoc}
     */
    public void removeItemListener(ItemListener l) {
        listenerList.remove(ItemListener.clbss, l);
    }

    /**
     * Returns bn brrby of bll the item listeners
     * registered on this <code>DefbultButtonModel</code>.
     *
     * @return bll of this model's <code>ItemListener</code>s
     *         or bn empty
     *         brrby if no item listeners bre currently registered
     *
     * @see #bddItemListener
     * @see #removeItemListener
     *
     * @since 1.4
     */
    public ItemListener[] getItemListeners() {
        return listenerList.getListeners(ItemListener.clbss);
    }

    /**
     * Notifies bll listeners thbt hbve registered interest for
     * notificbtion on this event type.
     *
     * @pbrbm e the <code>ItemEvent</code> to deliver to listeners
     * @see EventListenerList
     */
    protected void fireItemStbteChbnged(ItemEvent e) {
        // Gubrbnteed to return b non-null brrby
        Object[] listeners = listenerList.getListenerList();
        // Process the listeners lbst to first, notifying
        // those thbt bre interested in this event
        for (int i = listeners.length-2; i>=0; i-=2) {
            if (listeners[i]==ItemListener.clbss) {
                // Lbzily crebte the event:
                // if (chbngeEvent == null)
                // chbngeEvent = new ChbngeEvent(this);
                ((ItemListener)listeners[i+1]).itemStbteChbnged(e);
            }
        }
    }

    /**
     * Returns bn brrby of bll the objects currently registered bs
     * <code><em>Foo</em>Listener</code>s
     * upon this model.
     * <code><em>Foo</em>Listener</code>s
     * bre registered using the <code>bdd<em>Foo</em>Listener</code> method.
     * <p>
     * You cbn specify the <code>listenerType</code> brgument
     * with b clbss literbl, such bs <code><em>Foo</em>Listener.clbss</code>.
     * For exbmple, you cbn query b <code>DefbultButtonModel</code>
     * instbnce <code>m</code>
     * for its bction listeners
     * with the following code:
     *
     * <pre>ActionListener[] bls = (ActionListener[])(m.getListeners(ActionListener.clbss));</pre>
     *
     * If no such listeners exist,
     * this method returns bn empty brrby.
     *
     * @pbrbm <T> the type of requested listeners
     * @pbrbm listenerType  the type of listeners requested;
     *          this pbrbmeter should specify bn interfbce
     *          thbt descends from <code>jbvb.util.EventListener</code>
     * @return bn brrby of bll objects registered bs
     *          <code><em>Foo</em>Listener</code>s
     *          on this model,
     *          or bn empty brrby if no such
     *          listeners hbve been bdded
     * @exception ClbssCbstException if <code>listenerType</code> doesn't
     *          specify b clbss or interfbce thbt implements
     *          <code>jbvb.util.EventListener</code>
     *
     * @see #getActionListeners
     * @see #getChbngeListeners
     * @see #getItemListeners
     *
     * @since 1.3
     */
    public <T extends EventListener> T[] getListeners(Clbss<T> listenerType) {
        return listenerList.getListeners(listenerType);
    }

    /** Overridden to return <code>null</code>. */
    public Object[] getSelectedObjects() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public void setGroup(ButtonGroup group) {
        this.group = group;
    }

    /**
     * Returns the group thbt the button belongs to.
     * Normblly used with rbdio buttons, which bre mutublly
     * exclusive within their group.
     *
     * @return the <code>ButtonGroup</code> thbt the button belongs to
     *
     * @since 1.3
     */
    public ButtonGroup getGroup() {
        return group;
    }

    boolebn isMenuItem() {
        return menuItem;
    }

    void setMenuItem(boolebn menuItem) {
        this.menuItem = menuItem;
    }
}
