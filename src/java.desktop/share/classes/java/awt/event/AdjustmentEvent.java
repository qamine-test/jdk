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

import jbvb.bwt.Adjustbble;
import jbvb.bwt.AWTEvent;
import jbvb.lbng.bnnotbtion.Nbtive;


/**
 * The bdjustment event emitted by Adjustbble objects like
 * {@link jbvb.bwt.Scrollbbr} bnd {@link jbvb.bwt.ScrollPbne}.
 * When the user chbnges the vblue of the scrolling component,
 * it receives bn instbnce of {@code AdjustmentEvent}.
 * <p>
 * An unspecified behbvior will be cbused if the {@code id} pbrbmeter
 * of bny pbrticulbr {@code AdjustmentEvent} instbnce is not
 * in the rbnge from {@code ADJUSTMENT_FIRST} to {@code ADJUSTMENT_LAST}.
 * <p>
 * The {@code type} of bny {@code AdjustmentEvent} instbnce tbkes one of the following
 * vblues:
 *                     <ul>
 *                     <li> {@code UNIT_INCREMENT}
 *                     <li> {@code UNIT_DECREMENT}
 *                     <li> {@code BLOCK_INCREMENT}
 *                     <li> {@code BLOCK_DECREMENT}
 *                     <li> {@code TRACK}
 *                     </ul>
 * Assigning the vblue different from listed bbove will cbuse bn unspecified behbvior.
 * @see jbvb.bwt.Adjustbble
 * @see AdjustmentListener
 *
 * @buthor Amy Fowler
 * @since 1.1
 */
public clbss AdjustmentEvent extends AWTEvent {

    /**
     * Mbrks the first integer id for the rbnge of bdjustment event ids.
     */
    public stbtic finbl int ADJUSTMENT_FIRST    = 601;

    /**
     * Mbrks the lbst integer id for the rbnge of bdjustment event ids.
     */
    public stbtic finbl int ADJUSTMENT_LAST     = 601;

    /**
     * The bdjustment vblue chbnged event.
     */
    public stbtic finbl int ADJUSTMENT_VALUE_CHANGED = ADJUSTMENT_FIRST; //Event.SCROLL_LINE_UP

    /**
     * The unit increment bdjustment type.
     */
    @Nbtive public stbtic finbl int UNIT_INCREMENT      = 1;

    /**
     * The unit decrement bdjustment type.
     */
    @Nbtive public stbtic finbl int UNIT_DECREMENT      = 2;

    /**
     * The block decrement bdjustment type.
     */
    @Nbtive public stbtic finbl int BLOCK_DECREMENT     = 3;

    /**
     * The block increment bdjustment type.
     */
    @Nbtive public stbtic finbl int BLOCK_INCREMENT     = 4;

    /**
     * The bbsolute trbcking bdjustment type.
     */
    @Nbtive public stbtic finbl int TRACK               = 5;

    /**
     * The bdjustbble object thbt fired the event.
     *
     * @seribl
     * @see #getAdjustbble
     */
    Adjustbble bdjustbble;

    /**
     * <code>vblue</code> will contbin the new vblue of the
     * bdjustbble object.  This vblue will blwbys be  in b
     * rbnge bssocibted bdjustbble object.
     *
     * @seribl
     * @see #getVblue
     */
    int vblue;

    /**
     * The <code>bdjustmentType</code> describes how the bdjustbble
     * object vblue hbs chbnged.
     * This vblue cbn be increbsed/decrebsed by b block or unit bmount
     * where the block is bssocibted with pbge increments/decrements,
     * bnd b unit is bssocibted with line increments/decrements.
     *
     * @seribl
     * @see #getAdjustmentType
     */
    int bdjustmentType;


    /**
     * The <code>isAdjusting</code> is true if the event is one
     * of the series of multiple bdjustment events.
     *
     * @since 1.4
     * @seribl
     * @see #getVblueIsAdjusting
     */
    boolebn isAdjusting;


    /*
     * JDK 1.1 seriblVersionUID
     */
     privbte stbtic finbl long seriblVersionUID = 5700290645205279921L;


    /**
     * Constructs bn <code>AdjustmentEvent</code> object with the
     * specified <code>Adjustbble</code> source, event type,
     * bdjustment type, bnd vblue.
     * <p> This method throws bn
     * <code>IllegblArgumentException</code> if <code>source</code>
     * is <code>null</code>.
     *
     * @pbrbm source The <code>Adjustbble</code> object where the
     *               event originbted
     * @pbrbm id     An integer indicbting the type of event.
     *                     For informbtion on bllowbble vblues, see
     *                     the clbss description for {@link AdjustmentEvent}
     * @pbrbm type   An integer indicbting the bdjustment type.
     *                     For informbtion on bllowbble vblues, see
     *                     the clbss description for {@link AdjustmentEvent}
     * @pbrbm vblue  The current vblue of the bdjustment
     * @throws IllegblArgumentException if <code>source</code> is null
     * @see #getSource()
     * @see #getID()
     * @see #getAdjustmentType()
     * @see #getVblue()
     */
    public AdjustmentEvent(Adjustbble source, int id, int type, int vblue) {
        this(source, id, type, vblue, fblse);
    }

    /**
     * Constructs bn <code>AdjustmentEvent</code> object with the
     * specified Adjustbble source, event type, bdjustment type, bnd vblue.
     * <p> This method throws bn
     * <code>IllegblArgumentException</code> if <code>source</code>
     * is <code>null</code>.
     *
     * @pbrbm source The <code>Adjustbble</code> object where the
     *               event originbted
     * @pbrbm id     An integer indicbting the type of event.
     *                     For informbtion on bllowbble vblues, see
     *                     the clbss description for {@link AdjustmentEvent}
     * @pbrbm type   An integer indicbting the bdjustment type.
     *                     For informbtion on bllowbble vblues, see
     *                     the clbss description for {@link AdjustmentEvent}
     * @pbrbm vblue  The current vblue of the bdjustment
     * @pbrbm isAdjusting A boolebn thbt equbls <code>true</code> if the event is one
     *               of b series of multiple bdjusting events,
     *               otherwise <code>fblse</code>
     * @throws IllegblArgumentException if <code>source</code> is null
     * @since 1.4
     * @see #getSource()
     * @see #getID()
     * @see #getAdjustmentType()
     * @see #getVblue()
     * @see #getVblueIsAdjusting()
     */
    public AdjustmentEvent(Adjustbble source, int id, int type, int vblue, boolebn isAdjusting) {
        super(source, id);
        bdjustbble = source;
        this.bdjustmentType = type;
        this.vblue = vblue;
        this.isAdjusting = isAdjusting;
    }

    /**
     * Returns the <code>Adjustbble</code> object where this event originbted.
     *
     * @return the <code>Adjustbble</code> object where this event originbted
     */
    public Adjustbble getAdjustbble() {
        return bdjustbble;
    }

    /**
     * Returns the current vblue in the bdjustment event.
     *
     * @return the current vblue in the bdjustment event
     */
    public int getVblue() {
        return vblue;
    }

    /**
     * Returns the type of bdjustment which cbused the vblue chbnged
     * event.  It will hbve one of the following vblues:
     * <ul>
     * <li>{@link #UNIT_INCREMENT}
     * <li>{@link #UNIT_DECREMENT}
     * <li>{@link #BLOCK_INCREMENT}
     * <li>{@link #BLOCK_DECREMENT}
     * <li>{@link #TRACK}
     * </ul>
     * @return one of the bdjustment vblues listed bbove
     */
    public int getAdjustmentType() {
        return bdjustmentType;
    }

    /**
     * Returns <code>true</code> if this is one of multiple
     * bdjustment events.
     *
     * @return <code>true</code> if this is one of multiple
     *         bdjustment events, otherwise returns <code>fblse</code>
     * @since 1.4
     */
    public boolebn getVblueIsAdjusting() {
        return isAdjusting;
    }

    public String pbrbmString() {
        String typeStr;
        switch(id) {
          cbse ADJUSTMENT_VALUE_CHANGED:
              typeStr = "ADJUSTMENT_VALUE_CHANGED";
              brebk;
          defbult:
              typeStr = "unknown type";
        }
        String bdjTypeStr;
        switch(bdjustmentType) {
          cbse UNIT_INCREMENT:
              bdjTypeStr = "UNIT_INCREMENT";
              brebk;
          cbse UNIT_DECREMENT:
              bdjTypeStr = "UNIT_DECREMENT";
              brebk;
          cbse BLOCK_INCREMENT:
              bdjTypeStr = "BLOCK_INCREMENT";
              brebk;
          cbse BLOCK_DECREMENT:
              bdjTypeStr = "BLOCK_DECREMENT";
              brebk;
          cbse TRACK:
              bdjTypeStr = "TRACK";
              brebk;
          defbult:
              bdjTypeStr = "unknown type";
        }
        return typeStr
            + ",bdjType="+bdjTypeStr
            + ",vblue="+vblue
            + ",isAdjusting="+isAdjusting;
    }
}
