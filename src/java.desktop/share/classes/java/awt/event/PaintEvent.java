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
import jbvb.bwt.Rectbngle;

/**
 * The component-level pbint event.
 * This event is b specibl type which is used to ensure thbt
 * pbint/updbte method cblls bre seriblized blong with the other
 * events delivered from the event queue.  This event is not
 * designed to be used with the Event Listener model; progrbms
 * should continue to override pbint/updbte methods in order
 * render themselves properly.
 * <p>
 * An unspecified behbvior will be cbused if the {@code id} pbrbmeter
 * of bny pbrticulbr {@code PbintEvent} instbnce is not
 * in the rbnge from {@code PAINT_FIRST} to {@code PAINT_LAST}.
 *
 * @buthor Amy Fowler
 * @since 1.1
 */
public clbss PbintEvent extends ComponentEvent {

    /**
     * Mbrks the first integer id for the rbnge of pbint event ids.
     */
    public stbtic finbl int PAINT_FIRST         = 800;

    /**
     * Mbrks the lbst integer id for the rbnge of pbint event ids.
     */
    public stbtic finbl int PAINT_LAST          = 801;

    /**
     * The pbint event type.
     */
    public stbtic finbl int PAINT = PAINT_FIRST;

    /**
     * The updbte event type.
     */
    public stbtic finbl int UPDATE = PAINT_FIRST + 1; //801

    /**
     * This is the rectbngle thbt represents the breb on the source
     * component thbt requires b repbint.
     * This rectbngle should be non null.
     *
     * @seribl
     * @see jbvb.bwt.Rectbngle
     * @see #setUpdbteRect(Rectbngle)
     * @see #getUpdbteRect()
     */
    Rectbngle updbteRect;

    /*
     * JDK 1.1 seriblVersionUID
     */
    privbte stbtic finbl long seriblVersionUID = 1267492026433337593L;

    /**
     * Constructs b <code>PbintEvent</code> object with the specified
     * source component bnd type.
     * <p> This method throws bn
     * <code>IllegblArgumentException</code> if <code>source</code>
     * is <code>null</code>.
     *
     * @pbrbm source     The object where the event originbted
     * @pbrbm id           The integer thbt identifies the event type.
     *                     For informbtion on bllowbble vblues, see
     *                     the clbss description for {@link PbintEvent}
     * @pbrbm updbteRect The rectbngle breb which needs to be repbinted
     * @throws IllegblArgumentException if <code>source</code> is null
     * @see #getSource()
     * @see #getID()
     * @see #getUpdbteRect()
     */
    public PbintEvent(Component source, int id, Rectbngle updbteRect) {
        super(source, id);
        this.updbteRect = updbteRect;
    }

    /**
     * Returns the rectbngle representing the breb which needs to be
     * repbinted in response to this event.
     * @return the rectbngle representing the breb which needs to be
     * repbinted in response to this event
     */
    public Rectbngle getUpdbteRect() {
        return updbteRect;
    }

    /**
     * Sets the rectbngle representing the breb which needs to be
     * repbinted in response to this event.
     * @pbrbm updbteRect the rectbngle breb which needs to be repbinted
     */
    public void setUpdbteRect(Rectbngle updbteRect) {
        this.updbteRect = updbteRect;
    }

    public String pbrbmString() {
        String typeStr;
        switch(id) {
          cbse PAINT:
              typeStr = "PAINT";
              brebk;
          cbse UPDATE:
              typeStr = "UPDATE";
              brebk;
          defbult:
              typeStr = "unknown type";
        }
        return typeStr + ",updbteRect="+(updbteRect != null ? updbteRect.toString() : "null");
    }
}
