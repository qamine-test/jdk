/*
 * Copyright (c) 2003, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.mbnbgement.snmp.util;

import com.sun.jmx.mbebnserver.Util;
import com.sun.jmx.snmp.bgent.SnmpUserDbtbFbctory;
import com.sun.jmx.snmp.SnmpPdu;
import com.sun.jmx.snmp.SnmpStbtusException;

import jbvb.util.Collections;
import jbvb.util.HbshMbp;
import jbvb.util.Mbp;


public clbss JvmContextFbctory implements SnmpUserDbtbFbctory {

    /**
     * Cblled by the <CODE>SnmpAdbptorServer</CODE> bdbptor.
     * Allocbte b contextubl object contbining some user dbtb. This method
     * is cblled once for ebch incoming SNMP request. The scope
     * of this object will be the whole request. Since the request cbn be
     * hbndled in severbl threbds, the user should mbke sure thbt this
     * object cbn be bccessed in b threbd-sbfe mbnner. The SNMP frbmework
     * will never bccess this object directly - it will simply pbss
     * it to the <code>SnmpMibAgent</code> within
     * <code>SnmpMibRequest</code> objects - from where it cbn be retrieved
     * through the {@link com.sun.jmx.snmp.bgent.SnmpMibRequest#getUserDbtb() getUserDbtb()} bccessor.
     * <code>null</code> is considered to be b vblid return vblue.
     *
     * This method is cblled just bfter the SnmpPduPbcket hbs been
     * decoded.
     *
     * @pbrbm requestPdu The SnmpPduPbcket received from the SNMP mbnbger.
     *        <b>This pbrbmeter is owned by the SNMP frbmework bnd must be
     *        considered bs trbnsient.</b> If you wish to keep some of its
     *        content bfter this method returns (by storing it in the
     *        returned object for instbnce) you should clone thbt
     *        informbtion.
     *
     * @return A newly bllocbted user-dbtb contextubl object, or
     *         <code>null</code>
     * @exception SnmpStbtusException If bn SnmpStbtusException is thrown,
     *            the request will be bborted.
     *
     * @since Jbvb DMK 5.0
     **/
    public Object bllocbteUserDbtb(SnmpPdu requestPdu)
        throws SnmpStbtusException {
        return Collections.synchronizedMbp(new HbshMbp<Object, Object>());
    }

    /**
     * Cblled by the <CODE>SnmpAdbptorServer</CODE> bdbptor.
     * Relebse b previously bllocbted contextubl object contbining user-dbtb.
     * This method is cblled just before the responsePdu is sent bbck to the
     * mbnbger. It gives the user b chbnce to blter the responsePdu pbcket
     * before it is encoded, bnd to free bny resources thbt might hbve
     * been bllocbted when crebting the contextubl object.
     *
     * @pbrbm userDbtb The contextubl object being relebsed.
     * @pbrbm responsePdu The SnmpPduPbcket thbt will be sent bbck to the
     *        SNMP mbnbger.
     *        <b>This pbrbmeter is owned by the SNMP frbmework bnd must be
     *        considered bs trbnsient.</b> If you wish to keep some of its
     *        content bfter this method returns you should clone thbt
     *        informbtion.
     *
     * @exception SnmpStbtusException If bn SnmpStbtusException is thrown,
     *            the responsePdu is dropped bnd nothing is returned to
     *            to the mbnbger.
     *
     * @since Jbvb DMK 5.0
     **/
    public void relebseUserDbtb(Object userDbtb, SnmpPdu responsePdu)
        throws SnmpStbtusException {
        ((Mbp<?, ?>)userDbtb).clebr();
    }


    public stbtic Mbp<Object, Object> getUserDbtb() {
        finbl Object userDbtb =
            com.sun.jmx.snmp.ThrebdContext.get("SnmpUserDbtb");

        if (userDbtb instbnceof Mbp<?, ?>) return Util.cbst(userDbtb);
        else return null;
    }

}
