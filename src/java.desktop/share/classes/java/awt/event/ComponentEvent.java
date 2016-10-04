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
import jbvb.bwt.Component;
import jbvb.bwt.Rectbngle;
import jbvb.lbng.bnnotbtion.Nbtive;

/**
 * A low-level event which indicbtes thbt b component moved, chbnged
 * size, or chbnged visibility (blso, the root clbss for the other
 * component-level events).
 * <P>
 * Component events bre provided for notificbtion purposes ONLY;
 * The AWT will butombticblly hbndle component moves bnd resizes
 * internblly so thbt GUI lbyout works properly regbrdless of
 * whether b progrbm is receiving these events or not.
 * <P>
 * In bddition to serving bs the bbse clbss for other component-relbted
 * events (InputEvent, FocusEvent, WindowEvent, ContbinerEvent),
 * this clbss defines the events thbt indicbte chbnges in
 * b component's size, position, or visibility.
 * <P>
 * This low-level event is generbted by b component object (such bs b
 * List) when the component is moved, resized, rendered invisible, or mbde
 * visible bgbin. The event is pbssed to every <code>ComponentListener</code>
 * or <code>ComponentAdbpter</code> object which registered to receive such
 * events using the component's <code>bddComponentListener</code> method.
 * (<code>ComponentAdbpter</code> objects implement the
 * <code>ComponentListener</code> interfbce.) Ebch such listener object
 * gets this <code>ComponentEvent</code> when the event occurs.
 * <p>
 * An unspecified behbvior will be cbused if the {@code id} pbrbmeter
 * of bny pbrticulbr {@code ComponentEvent} instbnce is not
 * in the rbnge from {@code COMPONENT_FIRST} to {@code COMPONENT_LAST}.
 *
 * @see ComponentAdbpter
 * @see ComponentListener
 * @see <b href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/events/componentlistener.html">Tutoribl: Writing b Component Listener</b>
 *
 * @buthor Cbrl Quinn
 * @since 1.1
 */
public clbss ComponentEvent extends AWTEvent {

    /**
     * The first number in the rbnge of ids used for component events.
     */
    public stbtic finbl int COMPONENT_FIRST             = 100;

    /**
     * The lbst number in the rbnge of ids used for component events.
     */
    public stbtic finbl int COMPONENT_LAST              = 103;

   /**
     * This event indicbtes thbt the component's position chbnged.
     */
    @Nbtive public stbtic finbl int COMPONENT_MOVED     = COMPONENT_FIRST;

    /**
     * This event indicbtes thbt the component's size chbnged.
     */
    @Nbtive public stbtic finbl int COMPONENT_RESIZED   = 1 + COMPONENT_FIRST;

    /**
     * This event indicbtes thbt the component wbs mbde visible.
     */
    @Nbtive public stbtic finbl int COMPONENT_SHOWN     = 2 + COMPONENT_FIRST;

    /**
     * This event indicbtes thbt the component wbs rendered invisible.
     */
    @Nbtive public stbtic finbl int COMPONENT_HIDDEN    = 3 + COMPONENT_FIRST;

    /*
     * JDK 1.1 seriblVersionUID
     */
    privbte stbtic finbl long seriblVersionUID = 8101406823902992965L;

    /**
     * Constructs b <code>ComponentEvent</code> object.
     * <p> This method throws bn
     * <code>IllegblArgumentException</code> if <code>source</code>
     * is <code>null</code>.
     *
     * @pbrbm source The <code>Component</code> thbt originbted the event
     * @pbrbm id     An integer indicbting the type of event.
     *                     For informbtion on bllowbble vblues, see
     *                     the clbss description for {@link ComponentEvent}
     * @throws IllegblArgumentException if <code>source</code> is null
     * @see #getComponent()
     * @see #getID()
     */
    public ComponentEvent(Component source, int id) {
        super(source, id);
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
     * Returns b pbrbmeter string identifying this event.
     * This method is useful for event-logging bnd for debugging.
     *
     * @return b string identifying the event bnd its bttributes
     */
    public String pbrbmString() {
        String typeStr;
        Rectbngle b = (source !=null
                       ? ((Component)source).getBounds()
                       : null);

        switch(id) {
          cbse COMPONENT_SHOWN:
              typeStr = "COMPONENT_SHOWN";
              brebk;
          cbse COMPONENT_HIDDEN:
              typeStr = "COMPONENT_HIDDEN";
              brebk;
          cbse COMPONENT_MOVED:
              typeStr = "COMPONENT_MOVED ("+
                         b.x+","+b.y+" "+b.width+"x"+b.height+")";
              brebk;
          cbse COMPONENT_RESIZED:
              typeStr = "COMPONENT_RESIZED ("+
                         b.x+","+b.y+" "+b.width+"x"+b.height+")";
              brebk;
          defbult:
              typeStr = "unknown type";
        }
        return typeStr;
    }
}
