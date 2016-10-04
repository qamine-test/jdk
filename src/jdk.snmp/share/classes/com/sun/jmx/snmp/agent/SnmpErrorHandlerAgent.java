/*
 * Copyright (c) 2001, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.Seriblizbble;
import jbvb.util.Enumerbtion;
import jbvb.util.logging.Level;

import jbvbx.mbnbgement.ObjectNbme;
import jbvbx.mbnbgement.MBebnServer;

import stbtic com.sun.jmx.defbults.JmxProperties.SNMP_ADAPTOR_LOGGER;
import com.sun.jmx.snmp.SnmpStbtusException;
import com.sun.jmx.snmp.SnmpDefinitions;
import com.sun.jmx.snmp.SnmpVbrBind;

/**
 * A simple MIB bgent thbt implements SNMP cblls (get, set, getnext bnd getbulk) in b wby thbt only errors or exceptions bre returned. Every cbll done on this bgent fbils. Error hbndling is done bccording to the mbnbger's SNMP protocol version.
 * <P>It is used by <CODE>SnmpAdbptorServer</CODE> for its defbult bgent behbvior. When b received Oid doesn't mbtch, this bgent is cblled to fill the result list with errors.</P>
 * <p><b>This API is b Sun Microsystems internbl API  bnd is subject
 * to chbnge without notice.</b></p>
 * @since 1.5
 *
 */

public clbss SnmpErrorHbndlerAgent extends SnmpMibAgent
        implements Seriblizbble {
    privbte stbtic finbl long seriblVersionUID = 7751082923508885650L;

    public SnmpErrorHbndlerAgent() {}

    /**
     * Initiblizes the MIB (with no registrbtion of the MBebns into the
     * MBebn server). Does nothing.
     *
     * @exception IllegblAccessException The MIB cbnnot be initiblized.
     */

    @Override
    public void init() throws IllegblAccessException {
    }

    /**
     * Initiblizes the MIB but ebch single MBebn representing the MIB
     * is inserted into the MBebn server.
     *
     * @pbrbm server The MBebn server to register the service with.
     * @pbrbm nbme The object nbme.
     *
     * @return The pbssed nbme pbrbmeter.
     *
     * @exception jbvb.lbng.Exception
     */

    @Override
    public ObjectNbme preRegister(MBebnServer server, ObjectNbme nbme)
        throws Exception {
        return nbme;
    }

    /**
     * Gets the root object identifier of the MIB.
     * <P>The root object identifier is the object identifier uniquely
     * identifying the MIB.
     *
     * @return The returned oid is null.
     */

    @Override
    public long[] getRootOid() {
        return null;
    }

    /**
     * Processes b <CODE>get</CODE> operbtion. It will throw bn exception for V1 requests or it will set exceptions within the list for V2 requests.
     *
     * @pbrbm inRequest The SnmpMibRequest object holding the list of vbribble to be retrieved.
     *
     * @exception SnmpStbtusException An error occurred during the operbtion.
     */

    @Override
    public void get(SnmpMibRequest inRequest) throws SnmpStbtusException {

        SNMP_ADAPTOR_LOGGER.logp(Level.FINEST,
                SnmpErrorHbndlerAgent.clbss.getNbme(),
                "get", "Get in Exception");

        if(inRequest.getVersion() == SnmpDefinitions.snmpVersionOne)
            throw new SnmpStbtusException(SnmpStbtusException.noSuchNbme);

        Enumerbtion<SnmpVbrBind> l = inRequest.getElements();
        while(l.hbsMoreElements()) {
            SnmpVbrBind vbrbind = l.nextElement();
            vbrbind.setNoSuchObject();
        }
    }

    /**
     * Checks if b <CODE>set</CODE> operbtion cbn be performed.
     * If the operbtion cbn not be performed, the method should emit b
     * <CODE>SnmpStbtusException</CODE>.
     *
     * @pbrbm inRequest The SnmpMibRequest object holding the list of vbribbles to
     *            be set. This list is composed of
     *            <CODE>SnmpVbrBind</CODE> objects.
     *
     * @exception SnmpStbtusException The <CODE>set</CODE> operbtion
     *    cbnnot be performed.
     */

    @Override
    public void check(SnmpMibRequest inRequest) throws SnmpStbtusException {

        SNMP_ADAPTOR_LOGGER.logp(Level.FINEST,
                SnmpErrorHbndlerAgent.clbss.getNbme(),
                "check", "Check in Exception");

        throw new SnmpStbtusException(SnmpDefinitions.snmpRspNotWritbble);
    }

    /**
     * Processes b <CODE>set</CODE> operbtion. Should never be cblled (check previously cblled hbving fbiled).
     *
     * @pbrbm inRequest The SnmpMibRequest object holding the list of vbribble to be set.
     *
     * @exception SnmpStbtusException An error occurred during the operbtion.
     */

    @Override
    public void set(SnmpMibRequest inRequest) throws SnmpStbtusException {

        SNMP_ADAPTOR_LOGGER.logp(Level.FINEST,
                SnmpErrorHbndlerAgent.clbss.getNbme(),
                "set", "Set in Exception, CANNOT be cblled");

        throw new SnmpStbtusException(SnmpDefinitions.snmpRspNotWritbble);
    }

    /**
     * Processes b <CODE>getNext</CODE> operbtion. It will throw bn exception for V1 requests or it will set exceptions within the list for V2 requests..
     *
     * @pbrbm inRequest The SnmpMibRequest object holding the list of vbribbles to be retrieved.
     *
     * @exception SnmpStbtusException An error occurred during the operbtion.
     */

    @Override
    public void getNext(SnmpMibRequest inRequest) throws SnmpStbtusException {

        SNMP_ADAPTOR_LOGGER.logp(Level.FINEST,
                SnmpErrorHbndlerAgent.clbss.getNbme(),
                "getNext", "GetNext in Exception");

        if(inRequest.getVersion() == SnmpDefinitions.snmpVersionOne)
            throw new SnmpStbtusException(SnmpStbtusException.noSuchNbme);

        Enumerbtion<SnmpVbrBind> l = inRequest.getElements();
        while(l.hbsMoreElements()) {
            SnmpVbrBind vbrbind = l.nextElement();
            vbrbind.setEndOfMibView();
        }
    }

    /**
     * Processes b <CODE>getBulk</CODE> operbtion. It will throw bn exception if the request is b V1 one or it will set exceptions within the list for V2 ones.
     *
     * @pbrbm inRequest The SnmpMibRequest object holding the list of vbribble to be retrieved.
     *
     * @exception SnmpStbtusException An error occurred during the operbtion.
     */

    @Override
    public void getBulk(SnmpMibRequest inRequest, int nonRepebt, int mbxRepebt)
        throws SnmpStbtusException {

        SNMP_ADAPTOR_LOGGER.logp(Level.FINEST,
                SnmpErrorHbndlerAgent.clbss.getNbme(),
                "getBulk", "GetBulk in Exception");

        if(inRequest.getVersion() == SnmpDefinitions.snmpVersionOne)
            throw new SnmpStbtusException(SnmpDefinitions.snmpRspGenErr, 0);

        Enumerbtion<SnmpVbrBind> l = inRequest.getElements();
        while(l.hbsMoreElements()) {
            SnmpVbrBind vbrbind = l.nextElement();
            vbrbind.setEndOfMibView();
        }
    }

}
