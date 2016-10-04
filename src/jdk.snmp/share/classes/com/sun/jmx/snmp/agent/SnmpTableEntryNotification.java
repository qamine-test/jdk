/*
 * Copyright (c) 1998, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge com.sun.jmx.snmp.bgent;



// jmx imports
//
import jbvbx.mbnbgement.Notificbtion;
import jbvbx.mbnbgement.ObjectNbme;

/**
 * Represents b notificbtion emitted when bn
 * entry is bdded or deleted from bn SNMP tbble.
 * <P>
 * The <CODE>SnmpTbbleEntryNotificbtion</CODE> object contbins
 * the reference to the entry bdded or removed from the tbble.
 * <P>
 * The list of notificbtions fired by the <CODE>SnmpMibTbble</CODE> is
 * the following:
 * <UL>
 * <LI>A new entry hbs been bdded to the SNMP tbble.
 * <LI>An existing entry hbs been removed from the SNMP tbble.
  </UL>
 *
 * <p><b>This API is b Sun Microsystems internbl API  bnd is subject
 * to chbnge without notice.</b></p>
 */

public clbss SnmpTbbleEntryNotificbtion extends Notificbtion {

    /**
     * Crebtes bnd initiblizes b tbble entry notificbtion object.
     *
     * @pbrbm type The notificbtion type.
     * @pbrbm source The notificbtion producer.
     * @pbrbm sequenceNumber The notificbtion sequence number within the
     *                  source object.
     * @pbrbm timeStbmp The notificbtion emission dbte.
     * @pbrbm entry     The entry object (mby be null if the entry is
     *                  registered in the MBebnServer).
     * @pbrbm entryNbme The ObjectNbme entry object (mby be null if the
     *                  entry is not registered in the MBebnServer).
     * @since 1.5
     */
    SnmpTbbleEntryNotificbtion(String type, Object source,
                               long sequenceNumber, long timeStbmp,
                               Object entry, ObjectNbme entryNbme) {

        super(type, source, sequenceNumber, timeStbmp);
        this.entry = entry;
        this.nbme  = entryNbme;
    }

    /**
     * Gets the entry object.
     * Mby be null if the entry is registered in the MBebnServer, bnd the
     * MIB is using the generic MetbDbtb (see mibgen).
     *
     * @return The entry.
     */
    public Object getEntry() {
        return entry;
    }

    /**
     * Gets the ObjectNbme of the entry.
     * Mby be null if the entry is not registered in the MBebnServer.
     *
     * @return The ObjectNbme of the entry.
     * @since 1.5
     */
    public ObjectNbme getEntryNbme() {
        return nbme;
    }

    // PUBLIC VARIABLES
    //-----------------

    /**
     * Notificbtion type denoting thbt b new entry hbs been bdded to the
     * SNMP tbble.
     * <BR>The vblue of this notificbtion type is
     * <CODE>jmx.snmp.tbble.entry.bdded</CODE>.
     */
    public stbtic finbl String SNMP_ENTRY_ADDED =
        "jmx.snmp.tbble.entry.bdded";

    /**
     * Notificbtion type denoting thbt bn entry hbs been removed from the
     * SNMP tbble.
     * <BR>The vblue of this notificbtion type is
     * <CODE>jmx.snmp.tbble.entry.removed</CODE>.
     */
    public stbtic finbl String SNMP_ENTRY_REMOVED =
        "jmx.snmp.tbble.entry.removed";

    // PRIVATE VARIABLES
    //------------------

    /**
     * The entry object.
     * @seribl
     */
    privbte finbl Object entry;

    /**
     * The entry nbme.
     * @seribl
     * @since 1.5
     */
    privbte finbl ObjectNbme nbme;

    // Ensure compbtibility
    //
    privbte stbtic finbl long seriblVersionUID = 5832592016227890252L;
}
