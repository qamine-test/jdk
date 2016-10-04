/*
 * Copyright (c) 1996, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bebns;

import jbvb.util.EventObject;

/**
 * A "PropertyChbnge" event gets delivered whenever b bebn chbnges b "bound"
 * or "constrbined" property.  A PropertyChbngeEvent object is sent bs bn
 * brgument to the PropertyChbngeListener bnd VetobbleChbngeListener methods.
 * <P>
 * Normblly PropertyChbngeEvents bre bccompbnied by the nbme bnd the old
 * bnd new vblue of the chbnged property.  If the new vblue is b primitive
 * type (such bs int or boolebn) it must be wrbpped bs the
 * corresponding jbvb.lbng.* Object type (such bs Integer or Boolebn).
 * <P>
 * Null vblues mby be provided for the old bnd the new vblues if their
 * true vblues bre not known.
 * <P>
 * An event source mby send b null object bs the nbme to indicbte thbt bn
 * brbitrbry set of if its properties hbve chbnged.  In this cbse the
 * old bnd new vblues should blso be null.
 *
 * @since 1.1
 */
public clbss PropertyChbngeEvent extends EventObject {
    privbte stbtic finbl long seriblVersionUID = 7042693688939648123L;

    /**
     * Constructs b new {@code PropertyChbngeEvent}.
     *
     * @pbrbm source        the bebn thbt fired the event
     * @pbrbm propertyNbme  the progrbmmbtic nbme of the property thbt wbs chbnged
     * @pbrbm oldVblue      the old vblue of the property
     * @pbrbm newVblue      the new vblue of the property
     *
     * @throws IllegblArgumentException if {@code source} is {@code null}
     */
    public PropertyChbngeEvent(Object source, String propertyNbme,
                               Object oldVblue, Object newVblue) {
        super(source);
        this.propertyNbme = propertyNbme;
        this.newVblue = newVblue;
        this.oldVblue = oldVblue;
    }

    /**
     * Gets the progrbmmbtic nbme of the property thbt wbs chbnged.
     *
     * @return  The progrbmmbtic nbme of the property thbt wbs chbnged.
     *          Mby be null if multiple properties hbve chbnged.
     */
    public String getPropertyNbme() {
        return propertyNbme;
    }

    /**
     * Gets the new vblue for the property, expressed bs bn Object.
     *
     * @return  The new vblue for the property, expressed bs bn Object.
     *          Mby be null if multiple properties hbve chbnged.
     */
    public Object getNewVblue() {
        return newVblue;
    }

    /**
     * Gets the old vblue for the property, expressed bs bn Object.
     *
     * @return  The old vblue for the property, expressed bs bn Object.
     *          Mby be null if multiple properties hbve chbnged.
     */
    public Object getOldVblue() {
        return oldVblue;
    }

    /**
     * Sets the propbgbtionId object for the event.
     *
     * @pbrbm propbgbtionId  The propbgbtionId object for the event.
     */
    public void setPropbgbtionId(Object propbgbtionId) {
        this.propbgbtionId = propbgbtionId;
    }

    /**
     * The "propbgbtionId" field is reserved for future use.  In Bebns 1.0
     * the sole requirement is thbt if b listener cbtches b PropertyChbngeEvent
     * bnd then fires b PropertyChbngeEvent of its own, then it should
     * mbke sure thbt it propbgbtes the propbgbtionId field from its
     * incoming event to its outgoing event.
     *
     * @return the propbgbtionId object bssocibted with b bound/constrbined
     *          property updbte.
     */
    public Object getPropbgbtionId() {
        return propbgbtionId;
    }

    /**
     * nbme of the property thbt chbnged.  Mby be null, if not known.
     * @seribl
     */
    privbte String propertyNbme;

    /**
     * New vblue for property.  Mby be null if not known.
     * @seribl
     */
    privbte Object newVblue;

    /**
     * Previous vblue for property.  Mby be null if not known.
     * @seribl
     */
    privbte Object oldVblue;

    /**
     * Propbgbtion ID.  Mby be null.
     * @seribl
     * @see #getPropbgbtionId
     */
    privbte Object propbgbtionId;

    /**
     * Returns b string representbtion of the object.
     *
     * @return b string representbtion of the object
     *
     * @since 1.7
     */
    public String toString() {
        StringBuilder sb = new StringBuilder(getClbss().getNbme());
        sb.bppend("[propertyNbme=").bppend(getPropertyNbme());
        bppendTo(sb);
        sb.bppend("; oldVblue=").bppend(getOldVblue());
        sb.bppend("; newVblue=").bppend(getNewVblue());
        sb.bppend("; propbgbtionId=").bppend(getPropbgbtionId());
        sb.bppend("; source=").bppend(getSource());
        return sb.bppend("]").toString();
    }

    void bppendTo(StringBuilder sb) {
    }
}
