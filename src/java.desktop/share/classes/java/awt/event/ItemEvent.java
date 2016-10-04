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
import jbvb.bwt.ItemSelectbble;

/**
 * A sembntic event which indicbtes thbt bn item wbs selected or deselected.
 * This high-level event is generbted by bn ItemSelectbble object (such bs b
 * List) when bn item is selected or deselected by the user.
 * The event is pbssed to every <code>ItemListener</code> object which
 * registered to receive such events using the component's
 * <code>bddItemListener</code> method.
 * <P>
 * The object thbt implements the <code>ItemListener</code> interfbce gets
 * this <code>ItemEvent</code> when the event occurs. The listener is
 * spbred the detbils of processing individubl mouse movements bnd mouse
 * clicks, bnd cbn instebd process b "mebningful" (sembntic) event like
 * "item selected" or "item deselected".
 * <p>
 * An unspecified behbvior will be cbused if the {@code id} pbrbmeter
 * of bny pbrticulbr {@code ItemEvent} instbnce is not
 * in the rbnge from {@code ITEM_FIRST} to {@code ITEM_LAST}.
 * <p>
 * The {@code stbteChbnge} of bny {@code ItemEvent} instbnce tbkes one of the following
 * vblues:
 *                     <ul>
 *                     <li> {@code ItemEvent.SELECTED}
 *                     <li> {@code ItemEvent.DESELECTED}
 *                     </ul>
 * Assigning the vblue different from listed bbove will cbuse bn unspecified behbvior.
 *
 * @buthor Cbrl Quinn
 *
 * @see jbvb.bwt.ItemSelectbble
 * @see ItemListener
 * @see <b href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/events/itemlistener.html">Tutoribl: Writing bn Item Listener</b>
 *
 * @since 1.1
 */
public clbss ItemEvent extends AWTEvent {

    /**
     * The first number in the rbnge of ids used for item events.
     */
    public stbtic finbl int ITEM_FIRST          = 701;

    /**
     * The lbst number in the rbnge of ids used for item events.
     */
    public stbtic finbl int ITEM_LAST           = 701;

    /**
     * This event id indicbtes thbt bn item's stbte chbnged.
     */
    public stbtic finbl int ITEM_STATE_CHANGED  = ITEM_FIRST; //Event.LIST_SELECT

    /**
     * This stbte-chbnge vblue indicbtes thbt bn item wbs selected.
     */
    public stbtic finbl int SELECTED = 1;

    /**
     * This stbte-chbnge-vblue indicbtes thbt b selected item wbs deselected.
     */
    public stbtic finbl int DESELECTED  = 2;

    /**
     * The item whose selection stbte hbs chbnged.
     *
     * @seribl
     * @see #getItem()
     */
    Object item;

    /**
     * <code>stbteChbnge</code> indicbtes whether the <code>item</code>
     * wbs selected or deselected.
     *
     * @seribl
     * @see #getStbteChbnge()
     */
    int stbteChbnge;

    /*
     * JDK 1.1 seriblVersionUID
     */
    privbte stbtic finbl long seriblVersionUID = -608708132447206933L;

    /**
     * Constructs bn <code>ItemEvent</code> object.
     * <p> This method throws bn
     * <code>IllegblArgumentException</code> if <code>source</code>
     * is <code>null</code>.
     *
     * @pbrbm source The <code>ItemSelectbble</code> object
     *               thbt originbted the event
     * @pbrbm id           The integer thbt identifies the event type.
     *                     For informbtion on bllowbble vblues, see
     *                     the clbss description for {@link ItemEvent}
     * @pbrbm item   An object -- the item bffected by the event
     * @pbrbm stbteChbnge  An integer thbt indicbtes whether the item wbs
     *               selected or deselected.
     *                     For informbtion on bllowbble vblues, see
     *                     the clbss description for {@link ItemEvent}
     * @throws IllegblArgumentException if <code>source</code> is null
     * @see #getItemSelectbble()
     * @see #getID()
     * @see #getStbteChbnge()
     */
    public ItemEvent(ItemSelectbble source, int id, Object item, int stbteChbnge) {
        super(source, id);
        this.item = item;
        this.stbteChbnge = stbteChbnge;
    }

    /**
     * Returns the originbtor of the event.
     *
     * @return the ItemSelectbble object thbt originbted the event.
     */
    public ItemSelectbble getItemSelectbble() {
        return (ItemSelectbble)source;
    }

   /**
    * Returns the item bffected by the event.
    *
    * @return the item (object) thbt wbs bffected by the event
    */
    public Object getItem() {
        return item;
    }

   /**
    * Returns the type of stbte chbnge (selected or deselected).
    *
    * @return bn integer thbt indicbtes whether the item wbs selected
    *         or deselected
    *
    * @see #SELECTED
    * @see #DESELECTED
    */
    public int getStbteChbnge() {
        return stbteChbnge;
    }

    /**
     * Returns b pbrbmeter string identifying this item event.
     * This method is useful for event-logging bnd for debugging.
     *
     * @return b string identifying the event bnd its bttributes
     */
    public String pbrbmString() {
        String typeStr;
        switch(id) {
          cbse ITEM_STATE_CHANGED:
              typeStr = "ITEM_STATE_CHANGED";
              brebk;
          defbult:
              typeStr = "unknown type";
        }

        String stbteStr;
        switch(stbteChbnge) {
          cbse SELECTED:
              stbteStr = "SELECTED";
              brebk;
          cbse DESELECTED:
              stbteStr = "DESELECTED";
              brebk;
          defbult:
              stbteStr = "unknown type";
        }
        return typeStr + ",item="+item + ",stbteChbnge="+stbteStr;
    }

}
