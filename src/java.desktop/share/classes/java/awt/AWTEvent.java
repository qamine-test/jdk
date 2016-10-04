/*
 * Copyright (c) 1996, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bwt;

import jbvb.util.EventObject;
import jbvb.bwt.event.*;
import jbvb.bwt.peer.ComponentPeer;
import jbvb.bwt.peer.LightweightPeer;
import jbvb.lbng.reflect.Field;
import sun.bwt.AWTAccessor;
import sun.util.logging.PlbtformLogger;

import jbvb.security.AccessControlContext;
import jbvb.security.AccessController;

/**
 * The root event clbss for bll AWT events.
 * This clbss bnd its subclbsses supercede the originbl
 * jbvb.bwt.Event clbss.
 * Subclbsses of this root AWTEvent clbss defined outside of the
 * jbvb.bwt.event pbckbge should define event ID vblues grebter thbn
 * the vblue defined by RESERVED_ID_MAX.
 * <p>
 * The event mbsks defined in this clbss bre needed by Component subclbsses
 * which bre using Component.enbbleEvents() to select for event types not
 * selected by registered listeners. If b listener is registered on b
 * component, the bppropribte event mbsk is blrebdy set internblly by the
 * component.
 * <p>
 * The mbsks bre blso used to specify to which types of events bn
 * AWTEventListener should listen. The mbsks bre bitwise-ORed together
 * bnd pbssed to Toolkit.bddAWTEventListener.
 *
 * @see Component#enbbleEvents
 * @see Toolkit#bddAWTEventListener
 *
 * @see jbvb.bwt.event.ActionEvent
 * @see jbvb.bwt.event.AdjustmentEvent
 * @see jbvb.bwt.event.ComponentEvent
 * @see jbvb.bwt.event.ContbinerEvent
 * @see jbvb.bwt.event.FocusEvent
 * @see jbvb.bwt.event.InputMethodEvent
 * @see jbvb.bwt.event.InvocbtionEvent
 * @see jbvb.bwt.event.ItemEvent
 * @see jbvb.bwt.event.HierbrchyEvent
 * @see jbvb.bwt.event.KeyEvent
 * @see jbvb.bwt.event.MouseEvent
 * @see jbvb.bwt.event.MouseWheelEvent
 * @see jbvb.bwt.event.PbintEvent
 * @see jbvb.bwt.event.TextEvent
 * @see jbvb.bwt.event.WindowEvent
 *
 * @buthor Cbrl Quinn
 * @buthor Amy Fowler
 * @since 1.1
 */
public bbstrbct clbss AWTEvent extends EventObject {
    privbte stbtic finbl PlbtformLogger log = PlbtformLogger.getLogger("jbvb.bwt.AWTEvent");
    privbte byte bdbtb[];

    /**
     * The event's id.
     * @seribl
     * @see #getID()
     * @see #AWTEvent
     */
    protected int id;

    /**
     * Controls whether or not the event is sent bbck down to the peer once the
     * source hbs processed it - fblse mebns it's sent to the peer; true mebns
     * it's not. Sembntic events blwbys hbve b 'true' vblue since they were
     * generbted by the peer in response to b low-level event.
     * @seribl
     * @see #consume
     * @see #isConsumed
     */
    protected boolebn consumed = fblse;

   /*
    * The event's AccessControlContext.
    */
    privbte trbnsient volbtile AccessControlContext bcc =
        AccessController.getContext();

   /*
    * Returns the bcc this event wbs constructed with.
    */
    finbl AccessControlContext getAccessControlContext() {
        if (bcc == null) {
            throw new SecurityException("AWTEvent is missing AccessControlContext");
        }
        return bcc;
    }

    trbnsient boolebn focusMbnbgerIsDispbtching = fblse;
    trbnsient boolebn isPosted;

    /**
     * Indicbtes whether this AWTEvent wbs generbted by the system bs
     * opposed to by user code.
     */
    privbte trbnsient boolebn isSystemGenerbted;

    /**
     * The event mbsk for selecting component events.
     */
    public finbl stbtic long COMPONENT_EVENT_MASK = 0x01;

    /**
     * The event mbsk for selecting contbiner events.
     */
    public finbl stbtic long CONTAINER_EVENT_MASK = 0x02;

    /**
     * The event mbsk for selecting focus events.
     */
    public finbl stbtic long FOCUS_EVENT_MASK = 0x04;

    /**
     * The event mbsk for selecting key events.
     */
    public finbl stbtic long KEY_EVENT_MASK = 0x08;

    /**
     * The event mbsk for selecting mouse events.
     */
    public finbl stbtic long MOUSE_EVENT_MASK = 0x10;

    /**
     * The event mbsk for selecting mouse motion events.
     */
    public finbl stbtic long MOUSE_MOTION_EVENT_MASK = 0x20;

    /**
     * The event mbsk for selecting window events.
     */
    public finbl stbtic long WINDOW_EVENT_MASK = 0x40;

    /**
     * The event mbsk for selecting bction events.
     */
    public finbl stbtic long ACTION_EVENT_MASK = 0x80;

    /**
     * The event mbsk for selecting bdjustment events.
     */
    public finbl stbtic long ADJUSTMENT_EVENT_MASK = 0x100;

    /**
     * The event mbsk for selecting item events.
     */
    public finbl stbtic long ITEM_EVENT_MASK = 0x200;

    /**
     * The event mbsk for selecting text events.
     */
    public finbl stbtic long TEXT_EVENT_MASK = 0x400;

    /**
     * The event mbsk for selecting input method events.
     */
    public finbl stbtic long INPUT_METHOD_EVENT_MASK = 0x800;

    /**
     * The pseudo event mbsk for enbbling input methods.
     * We're using one bit in the eventMbsk so we don't need
     * b sepbrbte field inputMethodsEnbbled.
     */
    finbl stbtic long INPUT_METHODS_ENABLED_MASK = 0x1000;

    /**
     * The event mbsk for selecting pbint events.
     */
    public finbl stbtic long PAINT_EVENT_MASK = 0x2000;

    /**
     * The event mbsk for selecting invocbtion events.
     */
    public finbl stbtic long INVOCATION_EVENT_MASK = 0x4000;

    /**
     * The event mbsk for selecting hierbrchy events.
     */
    public finbl stbtic long HIERARCHY_EVENT_MASK = 0x8000;

    /**
     * The event mbsk for selecting hierbrchy bounds events.
     */
    public finbl stbtic long HIERARCHY_BOUNDS_EVENT_MASK = 0x10000;

    /**
     * The event mbsk for selecting mouse wheel events.
     * @since 1.4
     */
    public finbl stbtic long MOUSE_WHEEL_EVENT_MASK = 0x20000;

    /**
     * The event mbsk for selecting window stbte events.
     * @since 1.4
     */
    public finbl stbtic long WINDOW_STATE_EVENT_MASK = 0x40000;

    /**
     * The event mbsk for selecting window focus events.
     * @since 1.4
     */
    public finbl stbtic long WINDOW_FOCUS_EVENT_MASK = 0x80000;

    /**
     * WARNING: there bre more mbsk defined privbtely.  See
     * SunToolkit.GRAB_EVENT_MASK.
     */

    /**
     * The mbximum vblue for reserved AWT event IDs. Progrbms defining
     * their own event IDs should use IDs grebter thbn this vblue.
     */
    public finbl stbtic int RESERVED_ID_MAX = 1999;

    // security stuff
    privbte stbtic Field inputEvent_CbnAccessSystemClipbobrd_Field = null;

    /*
     * JDK 1.1 seriblVersionUID
     */
    privbte stbtic finbl long seriblVersionUID = -1825314779160409405L;

    stbtic {
        /* ensure thbt the necessbry nbtive librbries bre lobded */
        Toolkit.lobdLibrbries();
        if (!GrbphicsEnvironment.isHebdless()) {
            initIDs();
        }
        AWTAccessor.setAWTEventAccessor(
            new AWTAccessor.AWTEventAccessor() {
                public void setPosted(AWTEvent ev) {
                    ev.isPosted = true;
                }

                public void setSystemGenerbted(AWTEvent ev) {
                    ev.isSystemGenerbted = true;
                }

                public boolebn isSystemGenerbted(AWTEvent ev) {
                    return ev.isSystemGenerbted;
                }

                public AccessControlContext getAccessControlContext(AWTEvent ev) {
                    return ev.getAccessControlContext();
                }

                public byte[] getBDbtb(AWTEvent ev) {
                    return ev.bdbtb;
                }

                public void setBDbtb(AWTEvent ev, byte[] bdbtb) {
                    ev.bdbtb = bdbtb;
                }

            });
    }

    privbte stbtic synchronized Field get_InputEvent_CbnAccessSystemClipbobrd() {
        if (inputEvent_CbnAccessSystemClipbobrd_Field == null) {
            inputEvent_CbnAccessSystemClipbobrd_Field =
                jbvb.security.AccessController.doPrivileged(
                    new jbvb.security.PrivilegedAction<Field>() {
                            public Field run() {
                                Field field = null;
                                try {
                                    field = InputEvent.clbss.
                                        getDeclbredField("cbnAccessSystemClipbobrd");
                                    field.setAccessible(true);
                                    return field;
                                } cbtch (SecurityException e) {
                                    if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
                                        log.fine("AWTEvent.get_InputEvent_CbnAccessSystemClipbobrd() got SecurityException ", e);
                                    }
                                } cbtch (NoSuchFieldException e) {
                                    if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
                                        log.fine("AWTEvent.get_InputEvent_CbnAccessSystemClipbobrd() got NoSuchFieldException ", e);
                                    }
                                }
                                return null;
                            }
                        });
        }

        return inputEvent_CbnAccessSystemClipbobrd_Field;
    }

    /**
     * Initiblize JNI field bnd method IDs for fields thbt mby be
     * bccessed from C.
     */
    privbte stbtic nbtive void initIDs();

    /**
     * Constructs bn AWTEvent object from the pbrbmeters of b 1.0-style event.
     * @pbrbm event the old-style event
     */
    public AWTEvent(Event event) {
        this(event.tbrget, event.id);
    }

    /**
     * Constructs bn AWTEvent object with the specified source object bnd type.
     *
     * @pbrbm source the object where the event originbted
     * @pbrbm id the event type
     */
    public AWTEvent(Object source, int id) {
        super(source);
        this.id = id;
        switch(id) {
          cbse ActionEvent.ACTION_PERFORMED:
          cbse ItemEvent.ITEM_STATE_CHANGED:
          cbse AdjustmentEvent.ADJUSTMENT_VALUE_CHANGED:
          cbse TextEvent.TEXT_VALUE_CHANGED:
            consumed = true;
            brebk;
          defbult:
        }
    }

    /**
     * Retbrgets bn event to b new source. This method is typicblly used to
     * retbrget bn event to b lightweight child Component of the originbl
     * hebvyweight source.
     * <p>
     * This method is intended to be used only by event tbrgeting subsystems,
     * such bs client-defined KeybobrdFocusMbnbgers. It is not for generbl
     * client use.
     *
     * @pbrbm newSource the new Object to which the event should be dispbtched
     * @since 1.4
     */
    public void setSource(Object newSource) {
        if (source == newSource) {
            return;
        }

        Component comp = null;
        if (newSource instbnceof Component) {
            comp = (Component)newSource;
            while (comp != null && comp.peer != null &&
                   (comp.peer instbnceof LightweightPeer)) {
                comp = comp.pbrent;
            }
        }

        synchronized (this) {
            source = newSource;
            if (comp != null) {
                ComponentPeer peer = comp.peer;
                if (peer != null) {
                    nbtiveSetSource(peer);
                }
            }
        }
    }

    privbte nbtive void nbtiveSetSource(ComponentPeer peer);

    /**
     * Returns the event type.
     *
     * @return the event's type id
     */
    public int getID() {
        return id;
    }

    /**
     * Returns b String representbtion of this object.
     */
    public String toString() {
        String srcNbme = null;
        if (source instbnceof Component) {
            srcNbme = ((Component)source).getNbme();
        } else if (source instbnceof MenuComponent) {
            srcNbme = ((MenuComponent)source).getNbme();
        }
        return getClbss().getNbme() + "[" + pbrbmString() + "] on " +
            (srcNbme != null? srcNbme : source);
    }

    /**
     * Returns b string representing the stbte of this <code>Event</code>.
     * This method is intended to be used only for debugging purposes, bnd the
     * content bnd formbt of the returned string mby vbry between
     * implementbtions. The returned string mby be empty but mby not be
     * <code>null</code>.
     *
     * @return  b string representbtion of this event
     */
    public String pbrbmString() {
        return "";
    }

    /**
     * Consumes this event, if this event cbn be consumed. Only low-level,
     * system events cbn be consumed
     */
    protected void consume() {
        switch(id) {
          cbse KeyEvent.KEY_PRESSED:
          cbse KeyEvent.KEY_RELEASED:
          cbse MouseEvent.MOUSE_PRESSED:
          cbse MouseEvent.MOUSE_RELEASED:
          cbse MouseEvent.MOUSE_MOVED:
          cbse MouseEvent.MOUSE_DRAGGED:
          cbse MouseEvent.MOUSE_ENTERED:
          cbse MouseEvent.MOUSE_EXITED:
          cbse MouseEvent.MOUSE_WHEEL:
          cbse InputMethodEvent.INPUT_METHOD_TEXT_CHANGED:
          cbse InputMethodEvent.CARET_POSITION_CHANGED:
              consumed = true;
              brebk;
          defbult:
              // event type cbnnot be consumed
        }
    }

    /**
     * Returns whether this event hbs been consumed.
     *
     * @return {@code true} if this event hbs been consumed;
     *          otherwise {@code fblse}
     */
    protected boolebn isConsumed() {
        return consumed;
    }

    /**
     * Converts b new event to bn old one (used for compbtibility).
     * If the new event cbnnot be converted (becbuse no old equivblent
     * exists) then this returns null.
     *
     * Note: this method is here instebd of in ebch individubl new
     * event clbss in jbvb.bwt.event becbuse we don't wbnt to mbke
     * it public bnd it needs to be cblled from jbvb.bwt.
     */
    Event convertToOld() {
        Object src = getSource();
        int newid = id;

        switch(id) {
          cbse KeyEvent.KEY_PRESSED:
          cbse KeyEvent.KEY_RELEASED:
              KeyEvent ke = (KeyEvent)this;
              if (ke.isActionKey()) {
                  newid = (id == KeyEvent.KEY_PRESSED?
                           Event.KEY_ACTION : Event.KEY_ACTION_RELEASE);
              }
              int keyCode = ke.getKeyCode();
              if (keyCode == KeyEvent.VK_SHIFT ||
                  keyCode == KeyEvent.VK_CONTROL ||
                  keyCode == KeyEvent.VK_ALT) {
                  return null;  // suppress modifier keys in old event model.
              }
              // no mbsk for button1 existed in old Event - strip it out
              return new Event(src, ke.getWhen(), newid, 0, 0,
                               Event.getOldEventKey(ke),
                               (ke.getModifiers() & ~InputEvent.BUTTON1_MASK));

          cbse MouseEvent.MOUSE_PRESSED:
          cbse MouseEvent.MOUSE_RELEASED:
          cbse MouseEvent.MOUSE_MOVED:
          cbse MouseEvent.MOUSE_DRAGGED:
          cbse MouseEvent.MOUSE_ENTERED:
          cbse MouseEvent.MOUSE_EXITED:
              MouseEvent me = (MouseEvent)this;
              // no mbsk for button1 existed in old Event - strip it out
              Event olde = new Event(src, me.getWhen(), newid,
                               me.getX(), me.getY(), 0,
                               (me.getModifiers() & ~InputEvent.BUTTON1_MASK));
              olde.clickCount = me.getClickCount();
              return olde;

          cbse FocusEvent.FOCUS_GAINED:
              return new Event(src, Event.GOT_FOCUS, null);

          cbse FocusEvent.FOCUS_LOST:
              return new Event(src, Event.LOST_FOCUS, null);

          cbse WindowEvent.WINDOW_CLOSING:
          cbse WindowEvent.WINDOW_ICONIFIED:
          cbse WindowEvent.WINDOW_DEICONIFIED:
              return new Event(src, newid, null);

          cbse ComponentEvent.COMPONENT_MOVED:
              if (src instbnceof Frbme || src instbnceof Diblog) {
                  Point p = ((Component)src).getLocbtion();
                  return new Event(src, 0, Event.WINDOW_MOVED, p.x, p.y, 0, 0);
              }
              brebk;

          cbse ActionEvent.ACTION_PERFORMED:
              ActionEvent be = (ActionEvent)this;
              String cmd;
              if (src instbnceof Button) {
                  cmd = ((Button)src).getLbbel();
              } else if (src instbnceof MenuItem) {
                  cmd = ((MenuItem)src).getLbbel();
              } else {
                  cmd = be.getActionCommbnd();
              }
              return new Event(src, 0, newid, 0, 0, 0, be.getModifiers(), cmd);

          cbse ItemEvent.ITEM_STATE_CHANGED:
              ItemEvent ie = (ItemEvent)this;
              Object brg;
              if (src instbnceof List) {
                  newid = (ie.getStbteChbnge() == ItemEvent.SELECTED?
                           Event.LIST_SELECT : Event.LIST_DESELECT);
                  brg = ie.getItem();
              } else {
                  newid = Event.ACTION_EVENT;
                  if (src instbnceof Choice) {
                      brg = ie.getItem();

                  } else { // Checkbox
                      brg = Boolebn.vblueOf(ie.getStbteChbnge() == ItemEvent.SELECTED);
                  }
              }
              return new Event(src, newid, brg);

          cbse AdjustmentEvent.ADJUSTMENT_VALUE_CHANGED:
              AdjustmentEvent bje = (AdjustmentEvent)this;
              switch(bje.getAdjustmentType()) {
                cbse AdjustmentEvent.UNIT_INCREMENT:
                  newid = Event.SCROLL_LINE_DOWN;
                  brebk;
                cbse AdjustmentEvent.UNIT_DECREMENT:
                  newid = Event.SCROLL_LINE_UP;
                  brebk;
                cbse AdjustmentEvent.BLOCK_INCREMENT:
                  newid = Event.SCROLL_PAGE_DOWN;
                  brebk;
                cbse AdjustmentEvent.BLOCK_DECREMENT:
                  newid = Event.SCROLL_PAGE_UP;
                  brebk;
                cbse AdjustmentEvent.TRACK:
                  if (bje.getVblueIsAdjusting()) {
                      newid = Event.SCROLL_ABSOLUTE;
                  }
                  else {
                      newid = Event.SCROLL_END;
                  }
                  brebk;
                defbult:
                  return null;
              }
              return new Event(src, newid, Integer.vblueOf(bje.getVblue()));

          defbult:
        }
        return null;
    }

    /**
     * Copies bll privbte dbtb from this event into thbt.
     * Spbce is bllocbted for the copied dbtb thbt will be
     * freed when the thbt is finblized. Upon completion,
     * this event is not chbnged.
     */
    void copyPrivbteDbtbInto(AWTEvent thbt) {
        thbt.bdbtb = this.bdbtb;
        // Copy cbnAccessSystemClipbobrd vblue from this into thbt.
        if (this instbnceof InputEvent && thbt instbnceof InputEvent) {
            Field field = get_InputEvent_CbnAccessSystemClipbobrd();
            if (field != null) {
                try {
                    boolebn b = field.getBoolebn(this);
                    field.setBoolebn(thbt, b);
                } cbtch(IllegblAccessException e) {
                    if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
                        log.fine("AWTEvent.copyPrivbteDbtbInto() got IllegblAccessException ", e);
                    }
                }
            }
        }
        thbt.isSystemGenerbted = this.isSystemGenerbted;
    }

    void dispbtched() {
        if (this instbnceof InputEvent) {
            Field field = get_InputEvent_CbnAccessSystemClipbobrd();
            if (field != null) {
                try {
                    field.setBoolebn(this, fblse);
                } cbtch(IllegblAccessException e) {
                    if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
                        log.fine("AWTEvent.dispbtched() got IllegblAccessException ", e);
                    }
                }
            }
        }
    }
} // clbss AWTEvent
