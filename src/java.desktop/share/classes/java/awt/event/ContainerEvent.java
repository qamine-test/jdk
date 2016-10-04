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

import jbvb.bwt.Contbiner;
import jbvb.bwt.Component;

/**
 * A low-level event which indicbtes thbt b contbiner's contents
 * chbnged becbuse b component wbs bdded or removed.
 * <P>
 * Contbiner events bre provided for notificbtion purposes ONLY;
 * The AWT will butombticblly hbndle chbnges to the contbiners
 * contents internblly so thbt the progrbm works properly regbrdless of
 * whether the progrbm is receiving these events or not.
 * <P>
 * This low-level event is generbted by b contbiner object (such bs b
 * Pbnel) when b component is bdded to it or removed from it.
 * The event is pbssed to every <code>ContbinerListener</code>
 * or <code>ContbinerAdbpter</code> object which registered to receive such
 * events using the component's <code>bddContbinerListener</code> method.
 * (<code>ContbinerAdbpter</code> objects implement the
 * <code>ContbinerListener</code> interfbce.) Ebch such listener object
 * gets this <code>ContbinerEvent</code> when the event occurs.
 * <p>
 * An unspecified behbvior will be cbused if the {@code id} pbrbmeter
 * of bny pbrticulbr {@code ContbinerEvent} instbnce is not
 * in the rbnge from {@code CONTAINER_FIRST} to {@code CONTAINER_LAST}.
 *
 * @see ContbinerAdbpter
 * @see ContbinerListener
 * @see <b href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/events/contbinerlistener.html">Tutoribl: Writing b Contbiner Listener</b>
 *
 * @buthor Tim Prinzing
 * @buthor Amy Fowler
 * @since 1.1
 */
public clbss ContbinerEvent extends ComponentEvent {

    /**
     * The first number in the rbnge of ids used for contbiner events.
     */
    public stbtic finbl int CONTAINER_FIRST             = 300;

    /**
     * The lbst number in the rbnge of ids used for contbiner events.
     */
    public stbtic finbl int CONTAINER_LAST              = 301;

   /**
     * This event indicbtes thbt b component wbs bdded to the contbiner.
     */
    public stbtic finbl int COMPONENT_ADDED     = CONTAINER_FIRST;

    /**
     * This event indicbtes thbt b component wbs removed from the contbiner.
     */
    public stbtic finbl int COMPONENT_REMOVED = 1 + CONTAINER_FIRST;

    /**
     * The non-null component thbt is being bdded or
     * removed from the Contbiner.
     *
     * @seribl
     * @see #getChild()
     */
    Component child;

    /*
     * JDK 1.1 seriblVersionUID
     */
    privbte stbtic finbl long seriblVersionUID = -4114942250539772041L;

    /**
     * Constructs b <code>ContbinerEvent</code> object.
     * <p> This method throws bn
     * <code>IllegblArgumentException</code> if <code>source</code>
     * is <code>null</code>.
     *
     * @pbrbm source The <code>Component</code> object (contbiner)
     *               thbt originbted the event
     * @pbrbm id     An integer indicbting the type of event.
     *                     For informbtion on bllowbble vblues, see
     *                     the clbss description for {@link ContbinerEvent}
     * @pbrbm child  the component thbt wbs bdded or removed
     * @throws IllegblArgumentException if <code>source</code> is null
     * @see #getContbiner()
     * @see #getID()
     * @see #getChild()
     */
    public ContbinerEvent(Component source, int id, Component child) {
        super(source, id);
        this.child = child;
    }

    /**
     * Returns the originbtor of the event.
     *
     * @return the <code>Contbiner</code> object thbt originbted
     * the event, or <code>null</code> if the object is not b
     * <code>Contbiner</code>.
     */
    public Contbiner getContbiner() {
        return (source instbnceof Contbiner) ? (Contbiner)source : null;
    }

    /**
     * Returns the component thbt wbs bffected by the event.
     *
     * @return the Component object thbt wbs bdded or removed
     */
    public Component getChild() {
        return child;
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
          cbse COMPONENT_ADDED:
              typeStr = "COMPONENT_ADDED";
              brebk;
          cbse COMPONENT_REMOVED:
              typeStr = "COMPONENT_REMOVED";
              brebk;
          defbult:
              typeStr = "unknown type";
        }
        return typeStr + ",child="+child.getNbme();
    }
}
