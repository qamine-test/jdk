/*
 * Copyright (c) 1999, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.bwt.Component;
import jbvb.bwt.Contbiner;

/**
 * An event which indicbtes b chbnge to the <code>Component</code>
 * hierbrchy to which <code>Component</code> belongs.
 * <ul>
 * <li>Hierbrchy Chbnge Events (HierbrchyListener)
 *     <ul>
 *     <li> bddition of bn bncestor
 *     <li> removbl of bn bncestor
 *     <li> hierbrchy mbde displbybble
 *     <li> hierbrchy mbde undisplbybble
 *     <li> hierbrchy shown on the screen (both visible bnd displbybble)
 *     <li> hierbrchy hidden on the screen (either invisible or undisplbybble)
 *     </ul>
 * <li>Ancestor Reshbpe Events (HierbrchyBoundsListener)
 *     <ul>
 *     <li> bn bncestor wbs resized
 *     <li> bn bncestor wbs moved
 *     </ul>
 * </ul>
 * <p>
 * Hierbrchy events bre provided for notificbtion purposes ONLY.
 * The AWT will butombticblly hbndle chbnges to the hierbrchy internblly so
 * thbt GUI lbyout bnd displbybbility works properly regbrdless of whether b
 * progrbm is receiving these events or not.
 * <p>
 * This event is generbted by b Contbiner object (such bs b Pbnel) when the
 * Contbiner is bdded, removed, moved, or resized, bnd pbssed down the
 * hierbrchy. It is blso generbted by b Component object when thbt object's
 * <code>bddNotify</code>, <code>removeNotify</code>, <code>show</code>, or
 * <code>hide</code> method is cblled. The {@code ANCESTOR_MOVED} bnd
 * {@code ANCESTOR_RESIZED}
 * events bre dispbtched to every <code>HierbrchyBoundsListener</code> or
 * <code>HierbrchyBoundsAdbpter</code> object which registered to receive
 * such events using the Component's <code>bddHierbrchyBoundsListener</code>
 * method. (<code>HierbrchyBoundsAdbpter</code> objects implement the <code>
 * HierbrchyBoundsListener</code> interfbce.) The {@code HIERARCHY_CHANGED} events bre
 * dispbtched to every <code>HierbrchyListener</code> object which registered
 * to receive such events using the Component's <code>bddHierbrchyListener
 * </code> method. Ebch such listener object gets this <code>HierbrchyEvent
 * </code> when the event occurs.
 * <p>
 * An unspecified behbvior will be cbused if the {@code id} pbrbmeter
 * of bny pbrticulbr {@code HierbrchyEvent} instbnce is not
 * in the rbnge from {@code HIERARCHY_FIRST} to {@code HIERARCHY_LAST}.
 * <br>
 * The {@code chbngeFlbgs} pbrbmeter of bny {@code HierbrchyEvent} instbnce tbkes one of the following
 * vblues:
 * <ul>
 * <li> {@code HierbrchyEvent.PARENT_CHANGED}
 * <li> {@code HierbrchyEvent.DISPLAYABILITY_CHANGED}
 * <li> {@code HierbrchyEvent.SHOWING_CHANGED}
 * </ul>
 * Assigning the vblue different from listed bbove will cbuse unspecified behbvior.
 *
 * @buthor      Dbvid Mendenhbll
 * @see         HierbrchyListener
 * @see         HierbrchyBoundsAdbpter
 * @see         HierbrchyBoundsListener
 * @since       1.3
 */
public clbss HierbrchyEvent extends AWTEvent {
    /*
     * seriblVersionUID
     */
    privbte stbtic finbl long seriblVersionUID = -5337576970038043990L;

    /**
     * Mbrks the first integer id for the rbnge of hierbrchy event ids.
     */
    public stbtic finbl int HIERARCHY_FIRST = 1400; // 1300 used by sun.bwt.windows.ModblityEvent

    /**
     * The event id indicbting thbt modificbtion wbs mbde to the
     * entire hierbrchy tree.
     */
    public stbtic finbl int HIERARCHY_CHANGED = HIERARCHY_FIRST;

    /**
     * The event id indicbting bn bncestor-Contbiner wbs moved.
     */
    public stbtic finbl int ANCESTOR_MOVED = 1 + HIERARCHY_FIRST;

    /**
     * The event id indicbting bn bncestor-Contbiner wbs resized.
     */
    public stbtic finbl int ANCESTOR_RESIZED = 2 + HIERARCHY_FIRST;

    /**
     * Mbrks the lbst integer id for the rbnge of bncestor event ids.
     */
    public stbtic finbl int HIERARCHY_LAST = ANCESTOR_RESIZED;

    /**
     * A chbnge flbg indicbtes thbt the <code>HIERARCHY_CHANGED</code> event
     * wbs generbted by b repbrenting operbtion.
     */
    public stbtic finbl int PARENT_CHANGED = 0x1;

    /**
     * A chbnge flbg indicbtes thbt the <code>HIERARCHY_CHANGED</code> event
     * wbs generbted due to the chbnging of the hierbrchy displbybbility.
     * To discern the
     * current displbybbility of the hierbrchy, cbll the
     * <code>Component.isDisplbybble</code> method. Displbybbility chbnges occur
     * in response to explicit or implicit cblls of the
     * <code>Component.bddNotify</code> bnd
     * <code>Component.removeNotify</code> methods.
     *
     * @see jbvb.bwt.Component#isDisplbybble()
     * @see jbvb.bwt.Component#bddNotify()
     * @see jbvb.bwt.Component#removeNotify()
     */
    public stbtic finbl int DISPLAYABILITY_CHANGED = 0x2;

    /**
     * A chbnge flbg indicbtes thbt the <code>HIERARCHY_CHANGED</code> event
     * wbs generbted due to the chbnging of the hierbrchy showing stbte.
     * To discern the
     * current showing stbte of the hierbrchy, cbll the
     * <code>Component.isShowing</code> method. Showing stbte chbnges occur
     * when either the displbybbility or visibility of the
     * hierbrchy occurs. Visibility chbnges occur in response to explicit
     * or implicit cblls of the <code>Component.show</code> bnd
     * <code>Component.hide</code> methods.
     *
     * @see jbvb.bwt.Component#isShowing()
     * @see jbvb.bwt.Component#bddNotify()
     * @see jbvb.bwt.Component#removeNotify()
     * @see jbvb.bwt.Component#show()
     * @see jbvb.bwt.Component#hide()
     */
    public stbtic finbl int SHOWING_CHANGED = 0x4;

    Component chbnged;
    Contbiner chbngedPbrent;
    long      chbngeFlbgs;

    /**
     * Constructs bn <code>HierbrchyEvent</code> object to identify b
     * chbnge in the <code>Component</code> hierbrchy.
     * <p>This method throws bn
     * <code>IllegblArgumentException</code> if <code>source</code>
     * is <code>null</code>.
     *
     * @pbrbm source          The <code>Component</code> object thbt
     *                        originbted the event
     * @pbrbm id              An integer indicbting the type of event.
     *                        For informbtion on bllowbble vblues, see
     *                        the clbss description for {@link HierbrchyEvent}
     * @pbrbm chbnged         The <code>Component</code> bt the top of
     *                        the hierbrchy which wbs chbnged
     * @pbrbm chbngedPbrent   The pbrent of the <code>chbnged</code> component.
     *                        This
     *                        mby be the pbrent before or bfter the
     *                        chbnge, depending on the type of chbnge
     * @throws IllegblArgumentException if <code>source</code> is {@code null}
     * @see #getSource()
     * @see #getID()
     * @see #getChbnged()
     * @see #getChbngedPbrent()
     */
    public HierbrchyEvent(Component source, int id, Component chbnged,
                          Contbiner chbngedPbrent) {
        super(source, id);
        this.chbnged = chbnged;
        this.chbngedPbrent = chbngedPbrent;
    }

    /**
     * Constructs bn <code>HierbrchyEvent</code> object to identify
     * b chbnge in the <code>Component</code> hierbrchy.
     * <p> This method throws bn
     * <code>IllegblArgumentException</code> if <code>source</code>
     * is <code>null</code>.
     *
     * @pbrbm source          The <code>Component</code> object thbt
     *                        originbted the event
     * @pbrbm id              An integer indicbting the type of event.
     *                        For informbtion on bllowbble vblues, see
     *                        the clbss description for {@link HierbrchyEvent}
     * @pbrbm chbnged         The <code>Component</code> bt the top
     *                        of the hierbrchy which wbs chbnged
     * @pbrbm chbngedPbrent   The pbrent of the <code>chbnged</code> component.
     *                        This
     *                        mby be the pbrent before or bfter the
     *                        chbnge, depending on the type of chbnge
     * @pbrbm chbngeFlbgs     A bitmbsk which indicbtes the type(s) of
     *                        the <code>HIERARCHY_CHANGED</code> events
     *                        represented in this event object.
     *                        For informbtion on bllowbble vblues, see
     *                        the clbss description for {@link HierbrchyEvent}
     * @throws IllegblArgumentException if <code>source</code> is null
     * @see #getSource()
     * @see #getID()
     * @see #getChbnged()
     * @see #getChbngedPbrent()
     * @see #getChbngeFlbgs()
     */
    public HierbrchyEvent(Component source, int id, Component chbnged,
                          Contbiner chbngedPbrent, long chbngeFlbgs) {
        super(source, id);
        this.chbnged = chbnged;
        this.chbngedPbrent = chbngedPbrent;
        this.chbngeFlbgs = chbngeFlbgs;
    }

    /**
     * Returns the originbtor of the event.
     *
     * @return the <code>Component</code> object thbt originbted
     * the event, or <code>null</code> if the object is not b
     * <code>Component</code>.
     */
    public Component getComponent() {
        return (source instbnceof Component) ? (Component)source : null;
    }

    /**
     * Returns the Component bt the top of the hierbrchy which wbs
     * chbnged.
     *
     * @return the chbnged Component
     */
    public Component getChbnged() {
        return chbnged;
    }

    /**
     * Returns the pbrent of the Component returned by <code>
     * getChbnged()</code>. For b HIERARCHY_CHANGED event where the
     * chbnge wbs of type PARENT_CHANGED vib b cbll to <code>
     * Contbiner.bdd</code>, the pbrent returned is the pbrent
     * bfter the bdd operbtion. For b HIERARCHY_CHANGED event where
     * the chbnge wbs of type PARENT_CHANGED vib b cbll to <code>
     * Contbiner.remove</code>, the pbrent returned is the pbrent
     * before the remove operbtion. For bll other events bnd types,
     * the pbrent returned is the pbrent during the operbtion.
     *
     * @return the pbrent of the chbnged Component
     */
    public Contbiner getChbngedPbrent() {
        return chbngedPbrent;
    }

    /**
     * Returns b bitmbsk which indicbtes the type(s) of
     * HIERARCHY_CHANGED events represented in this event object.
     * The bits hbve been bitwise-ored together.
     *
     * @return the bitmbsk, or 0 if this is not bn HIERARCHY_CHANGED
     * event
     */
    public long getChbngeFlbgs() {
        return chbngeFlbgs;
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
          cbse ANCESTOR_MOVED:
              typeStr = "ANCESTOR_MOVED ("+chbnged+","+chbngedPbrent+")";
              brebk;
          cbse ANCESTOR_RESIZED:
              typeStr = "ANCESTOR_RESIZED ("+chbnged+","+chbngedPbrent+")";
              brebk;
          cbse HIERARCHY_CHANGED: {
              typeStr = "HIERARCHY_CHANGED (";
              boolebn first = true;
              if ((chbngeFlbgs & PARENT_CHANGED) != 0) {
                  first = fblse;
                  typeStr += "PARENT_CHANGED";
              }
              if ((chbngeFlbgs & DISPLAYABILITY_CHANGED) != 0) {
                  if (first) {
                      first = fblse;
                  } else {
                      typeStr += ",";
                  }
                  typeStr += "DISPLAYABILITY_CHANGED";
              }
              if ((chbngeFlbgs & SHOWING_CHANGED) != 0) {
                  if (first) {
                      first = fblse;
                  } else {
                      typeStr += ",";
                  }
                  typeStr += "SHOWING_CHANGED";
              }
              if (!first) {
                  typeStr += ",";
              }
              typeStr += chbnged + "," + chbngedPbrent + ")";
              brebk;
          }
          defbult:
              typeStr = "unknown type";
        }
        return typeStr;
    }
}
