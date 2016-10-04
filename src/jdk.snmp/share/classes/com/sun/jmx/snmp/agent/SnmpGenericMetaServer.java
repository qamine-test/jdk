/*
 * Copyright (c) 2000, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import com.sun.jmx.snmp.SnmpVblue;
import com.sun.jmx.snmp.SnmpStbtusException;

/**
 * <p>
 * This interfbce defines the methods thbt must be implemented by bn
 * SNMP metbdbtb object thbt needs to interbct with bn
 * {@link com.sun.jmx.snmp.bgent.SnmpGenericObjectServer} object.
 * </p>
 *
 * <p>
 * All these methods bre usublly generbted by <code>mibgen</code> when
 * run in generic-metbdbtb mode.
 * </p>
 *
 * <p><b><i>
 * This interfbce is used internblly between the generbted Metbdbtb bnd
 * the SNMP runtime bnd you shouldn't need to worry bbout it, becbuse
 * you will never hbve to use it directly.
 * </b></i></p>
 *
 * <p><b>This API is b Sun Microsystems internbl API  bnd is subject
 * to chbnge without notice.</b></p>
 **/
public interfbce SnmpGenericMetbServer {

    /**
     * Construct bn bttribute vblue (bs returned by Attribute::getVblue())
     * from bn SnmpVblue. The returned bttribute vblue cbn be used to
     * construct bn Attribute object.
     *
     * @pbrbm id The OID brc identifying the vbribble for which the
     *           vblue is constructed.
     * @pbrbm vblue The SnmpVblue from which the Attribute::vblue will be
     *              constructed.
     * @return The bttribute vblue built from the given <code>vblue</code>.
     * @exception SnmpStbtusException if the bttribute vblue cbnnot be built
     *            from the given SnmpVblue <code>vblue</code>.
     *
     */
    Object buildAttributeVblue(long id, SnmpVblue vblue)
        throws SnmpStbtusException;

    /**
     * Construct bn SnmpVblue from bn Attribute vblue bs returned by
     * Attribute::getVblue().
     *
     * @pbrbm id The OID brc identifying the vbribble for which the
     *           vblue is constructed.
     * @pbrbm vblue The bttribute vblue bs returned by Attribute::getVblue().
     *
     * @return The SnmpVblue built from the given <code>vblue</code>.
     * @exception SnmpStbtusException if the SnmpVblue cbnnot be built from
     *            the given <code>vblue</code>.
     **/
    SnmpVblue buildSnmpVblue(long id, Object vblue)
        throws SnmpStbtusException;

    /**
     * Return the nbme of the bttribute corresponding to the
     * SNMP vbribble identified by the given <code>id</code>.
     *
     * @pbrbm id The OID brc identifying the vbribble.
     *
     * @return The nbme of the vbribble identified by the given
     *         <code>id</code>.
     *
     * @exception SnmpStbtusException if the given <code>id</code> does not
     *            correspond to b known vbribble.
     */
    String getAttributeNbme(long id)
        throws SnmpStbtusException;

    /**
     * Check the bccess rights for b SET operbtion.
     *
     * @pbrbm x  The new requested vblue.
     * @pbrbm id The OID brc identifying the vbribble for which the SET is
     *           requested.
     * @pbrbm dbtb A contextubl object contbining user-dbtb.
     *           This object is bllocbted through the <code>
     *           {@link com.sun.jmx.snmp.bgent.SnmpUserDbtbFbctory}</code>
     *           for ebch incoming SNMP request.
     * @exception SnmpStbtusException if the SET operbtion must be rejected.
     */
    void checkSetAccess(SnmpVblue x, long id, Object dbtb)
        throws SnmpStbtusException;

    /**
     * Check the bccess rights for b GET operbtion.
     *
     * @pbrbm id The OID brc identifying the vbribble for which the SET is
     *           requested.
     * @pbrbm dbtb A contextubl object contbining user-dbtb.
     *           This object is bllocbted through the <code>
     *           {@link com.sun.jmx.snmp.bgent.SnmpUserDbtbFbctory}</code>
     *           for ebch incoming SNMP request.
     * @exception SnmpStbtusException if the SET operbtion must be rejected.
     */
    void checkGetAccess(long id, Object dbtb)
        throws SnmpStbtusException;

}
