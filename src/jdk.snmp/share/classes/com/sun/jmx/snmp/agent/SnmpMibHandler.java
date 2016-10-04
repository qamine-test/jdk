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



// jbvb imports
//
import jbvb.util.Vector;
import jbvb.io.IOException;

// jmx imports
//
import com.sun.jmx.snmp.SnmpOid;
import com.sun.jmx.snmp.SnmpStbtusException;

/**
 * The logicbl link between bn SNMP MIB bnd the SNMP communicbtion stbck.
 *
 * <p><b>This API is b Sun Microsystems internbl API  bnd is subject
 * to chbnge without notice.</b></p>
 */

public interfbce SnmpMibHbndler {

    /**
     * Adds b new MIB in the SNMP MIB hbndler.
     * This method is cblled butombticblly by {@link com.sun.jmx.snmp.bgent.SnmpMibAgent#setSnmpAdbptor(SnmpMibHbndler)} bnd
     * {@link com.sun.jmx.snmp.bgent.SnmpMibAgent#setSnmpAdbptorNbme(ObjectNbme)} bnd should not be cblled directly.
     *
     * @pbrbm mib The MIB to bdd.
     *
     * @return A reference on the SNMP MIB hbndler.
     *
     * @exception IllegblArgumentException If the pbrbmeter is null.
     */
    public SnmpMibHbndler bddMib(SnmpMibAgent mib) throws IllegblArgumentException;

/**
     * Adds b new MIB in the SNMP MIB hbndler.
     *
     * @pbrbm mib The MIB to bdd.
     * @pbrbm oids The brrby of oid used to bdd the mib. Ebch oid is b root oid for the mib.
     * @return A reference on the SNMP MIB hbndler.
     *
     * @exception IllegblArgumentException If the pbrbmeter is null.
     *
     * @since 1.5
     */
    public SnmpMibHbndler bddMib(SnmpMibAgent mib, SnmpOid[] oids) throws IllegblArgumentException;

    /**
     * Adds b new contextublized MIB in the SNMP MIB hbndler.
     *
     * @pbrbm mib The MIB to bdd.
     * @pbrbm contextNbme The MIB context nbme. If null is pbssed, will be registered in the defbult context.
     *
     * @return A reference to the SNMP MIB hbndler.
     *
     * @exception IllegblArgumentException If the pbrbmeter is null.
     *
     * @since 1.5
     */
    public SnmpMibHbndler bddMib(SnmpMibAgent mib, String contextNbme)
        throws IllegblArgumentException;

    /**
     * Adds b new contextublized MIB in the SNMP MIB hbndler.
     *
     * @pbrbm mib The MIB to bdd.
     * @pbrbm contextNbme The MIB context nbme. If null is pbssed, will be registered in the defbult context.
     * @pbrbm oids The brrby of oid used to bdd the mib. Ebch oid is b root oid for the mib.
     *
     * @return A reference to the SNMP MIB hbndler.
     *
     * @exception IllegblArgumentException If the pbrbmeter is null.
     *
     * @since 1.5
     */
    public SnmpMibHbndler bddMib(SnmpMibAgent mib, String contextNbme, SnmpOid[] oids)
        throws IllegblArgumentException;

    /**
     * Removes the specified MIB from the SNMP protocol bdbptor.
     * This method is cblled butombticblly by {@link com.sun.jmx.snmp.bgent.SnmpMibAgent#setSnmpAdbptor(SnmpMibHbndler)} bnd
     * {@link com.sun.jmx.snmp.bgent.SnmpMibAgent#setSnmpAdbptorNbme(ObjectNbme)} bnd should not be cblled directly.
     *
     * @pbrbm mib The MIB to be removed.
     *
     * @return <CODE>true</CODE> if the specified <CODE>mib</CODE> wbs b MIB included in the SNMP MIB hbndler,
     * <CODE>fblse</CODE> otherwise.
     */
    public boolebn removeMib(SnmpMibAgent mib);
  /**
     * Removes the specified MIB from the SNMP protocol bdbptor.
     * This method is cblled butombticblly by {@link com.sun.jmx.snmp.bgent.SnmpMibAgent#setSnmpAdbptor(SnmpMibHbndler)} bnd
     * {@link com.sun.jmx.snmp.bgent.SnmpMibAgent#setSnmpAdbptorNbme(ObjectNbme)} bnd should not be cblled directly.
     *
     * @pbrbm mib The MIB to be removed.
     * @pbrbm oids The oid the MIB wbs previously registered for.
     * @return <CODE>true</CODE> if the specified <CODE>mib</CODE> wbs b MIB included in the SNMP MIB hbndler,
     * <CODE>fblse</CODE> otherwise.
     *
     * @since 1.5
     */
    public boolebn removeMib(SnmpMibAgent mib, SnmpOid[] oids);
     /**
     * Removes the specified MIB from the SNMP protocol bdbptor.
     *
     * @pbrbm mib The MIB to be removed.
     * @pbrbm contextNbme The context nbme used bt registrbtion time.
     *
     * @return <CODE>true</CODE> if the specified <CODE>mib</CODE> wbs b MIB included in the SNMP MIB hbndler,
     * <CODE>fblse</CODE> otherwise.
     *
     * @since 1.5
     */
    public boolebn removeMib(SnmpMibAgent mib, String contextNbme);
     /**
     * Removes the specified MIB from the SNMP protocol bdbptor.
     *
     * @pbrbm mib The MIB to be removed.
     * @pbrbm contextNbme The context nbme used bt registrbtion time.
     * @pbrbm oids The oid the MIB wbs previously registered for.
     * @return <CODE>true</CODE> if the specified <CODE>mib</CODE> wbs b MIB included in the SNMP MIB hbndler,
     * <CODE>fblse</CODE> otherwise.
     *
     * @since 1.5
     */
    public boolebn removeMib(SnmpMibAgent mib, String contextNbme, SnmpOid[] oids);
}
