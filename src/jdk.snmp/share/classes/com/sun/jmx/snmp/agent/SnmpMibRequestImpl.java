/*
 * Copyright (c) 2000, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.Enumerbtion;
import jbvb.util.Vector;


import com.sun.jmx.snmp.SnmpPdu;
import com.sun.jmx.snmp.SnmpVbrBind;
import com.sun.jmx.snmp.SnmpEngine;

/**
 * This clbss implements the SnmpMibRequest interfbce.
 * It represents the pbrt of b SNMP request thbt involves b specific
 * MIB. One instbnce of this clbss will be crebted for every MIB
 * involved in b SNMP request, bnd will be pbssed to the SnmpMibAgent
 * in chbrge of hbndling thbt MIB.
 *
 * Instbnces of this clbss bre bllocbted by the SNMP engine. You will
 * never need to use this clbss directly. You will only bccess
 * instbnces of this clbss through their SnmpMibRequest interfbce.
 *
 */
finbl clbss SnmpMibRequestImpl implements SnmpMibRequest {

    /**
     * @pbrbm engine The locbl engine.
     * @pbrbm reqPdu The received pdu.
     * @pbrbm vblist The vector of SnmpVbrBind objects in which the
     *        MIB concerned by this request is involved.
     * @pbrbm protocolVersion  The protocol version of the SNMP request.
     * @pbrbm userDbtb     User bllocbted contextubl dbtb. This object must
     *        be bllocbted on b per SNMP request bbsis through the
     *        SnmpUserDbtbFbctory registered with the SnmpAdbptorServer,
     *        bnd is hbnded bbck to the user through SnmpMibRequest objects.
     */
    public SnmpMibRequestImpl(SnmpEngine engine,
                              SnmpPdu reqPdu,
                              Vector<SnmpVbrBind> vblist,
                              int protocolVersion,
                              Object userDbtb,
                              String principbl,
                              int securityLevel,
                              int securityModel,
                              byte[] contextNbme,
                              byte[] bccessContextNbme) {
        vbrbinds   = vblist;
        version    = protocolVersion;
        dbtb       = userDbtb;
        this.reqPdu = reqPdu;
        this.engine = engine;
        this.principbl = principbl;
        this.securityLevel = securityLevel;
        this.securityModel = securityModel;
        this.contextNbme = contextNbme;
        this.bccessContextNbme = bccessContextNbme;
    }
    // -------------------------------------------------------------------
    // PUBLIC METHODS from SnmpMibRequest
    // -------------------------------------------------------------------

    /**
     * Returns the locbl engine. This pbrbmeter is returned only if <CODE> SnmpV3AdbptorServer </CODE> is the bdbptor receiving this request. Otherwise null is returned.
     * @return the locbl engine.
     */
    @Override
    public SnmpEngine getEngine() {
        return engine;
    }

    /**
     * Gets the incoming request principbl. This pbrbmeter is returned only if <CODE> SnmpV3AdbptorServer </CODE> is the bdbptor receiving this request. Otherwise null is returned.
     * @return The request principbl.
     **/
    @Override
    public String getPrincipbl() {
        return principbl;
    }

    /**
     * Gets the incoming request security level. This level is defined in {@link com.sun.jmx.snmp.SnmpEngine SnmpEngine}. This pbrbmeter is returned only if <CODE> SnmpV3AdbptorServer </CODE> is the bdbptor receiving this request. Otherwise -1 is returned.
     * @return The security level.
     */
    @Override
    public int getSecurityLevel() {
        return securityLevel;
    }
    /**
     * Gets the incoming request security model. This pbrbmeter is returned only if <CODE> SnmpV3AdbptorServer </CODE> is the bdbptor receiving this request. Otherwise -1 is returned.
     * @return The security model.
     */
    @Override
    public int getSecurityModel() {
        return securityModel;
    }
    /**
     * Gets the incoming request context nbme. This pbrbmeter is returned only if <CODE> SnmpV3AdbptorServer </CODE> is the bdbptor receiving this request. Otherwise null is returned.
     * @return The context nbme.
     */
    @Override
    public byte[] getContextNbme() {
        return contextNbme;
    }

    /**
     * Gets the incoming request context nbme used by Access Control Model in order to bllow or deny the bccess to OIDs. This pbrbmeter is returned only if <CODE> SnmpV3AdbptorServer </CODE> is the bdbptor receiving this request. Otherwise null is returned.
     * @return The checked context.
     */
    @Override
    public byte[] getAccessContextNbme() {
        return bccessContextNbme;
    }

    // -------------------------------------------------------------------
    // Implements the method defined in SnmpMibRequest interfbce.
    // See SnmpMibRequest for the jbvb doc.
    // -------------------------------------------------------------------
    @Override
    public finbl SnmpPdu getPdu() {
        return reqPdu;
    }

    // -------------------------------------------------------------------
    // Implements the method defined in SnmpMibRequest interfbce.
    // See SnmpMibRequest for the jbvb doc.
    // -------------------------------------------------------------------
    @Override
    public finbl Enumerbtion<SnmpVbrBind> getElements()  {return vbrbinds.elements();}

    // -------------------------------------------------------------------
    // Implements the method defined in SnmpMibRequest interfbce.
    // See SnmpMibRequest for the jbvb doc.
    // -------------------------------------------------------------------
    @Override
    public finbl Vector<SnmpVbrBind> getSubList()  {return vbrbinds;}

    // -------------------------------------------------------------------
    // Implements the method defined in SnmpMibRequest interfbce.
    // See SnmpMibRequest for the jbvb doc.
    // -------------------------------------------------------------------
    @Override
    public finbl int getSize()  {
        if (vbrbinds == null) return 0;
        return vbrbinds.size();
    }

    // -------------------------------------------------------------------
    // Implements the method defined in SnmpMibRequest interfbce.
    // See SnmpMibRequest for the jbvb doc.
    // -------------------------------------------------------------------
    @Override
    public finbl int         getVersion()  {return version;}

    // -------------------------------------------------------------------
    // Implements the method defined in SnmpMibRequest interfbce.
    // See SnmpMibRequest for the jbvb doc.
    // -------------------------------------------------------------------
    @Override
    public finbl int         getRequestPduVersion()  {return reqPdu.version;}

    // -------------------------------------------------------------------
    // Implements the method defined in SnmpMibRequest interfbce.
    // See SnmpMibRequest for the jbvb doc.
    // -------------------------------------------------------------------
    @Override
    public finbl Object      getUserDbtb() {return dbtb;}

    // -------------------------------------------------------------------
    // Implements the method defined in SnmpMibRequest interfbce.
    // See SnmpMibRequest for the jbvb doc.
    // -------------------------------------------------------------------
    @Override
    public finbl int getVbrIndex(SnmpVbrBind vbrbind) {
        return vbrbinds.indexOf(vbrbind);
    }

    // -------------------------------------------------------------------
    // Implements the method defined in SnmpMibRequest interfbce.
    // See SnmpMibRequest for the jbvb doc.
    // -------------------------------------------------------------------
    @Override
    public void bddVbrBind(SnmpVbrBind vbrbind) {
        vbrbinds.bddElement(vbrbind);
    }

    // -------------------------------------------------------------------
    // PACKAGE METHODS
    // -------------------------------------------------------------------

    // -------------------------------------------------------------------
    // Allow to pbss the request tree built during the check() phbse
    // to the set() method. Note: the if the tree is `null', then the
    // set() method will rebuild b new tree identicbl to the tree built
    // in the check() method.
    //
    // Pbssing this tree in the SnmpMibRequestImpl object bllows to
    // optimize the SET requests.
    //
    // -------------------------------------------------------------------
    finbl void setRequestTree(SnmpRequestTree tree) {this.tree = tree;}

    // -------------------------------------------------------------------
    // Returns the SnmpRequestTree object built in the first operbtion
    // phbse for two-phbse SNMP requests (like SET).
    // -------------------------------------------------------------------
    finbl SnmpRequestTree getRequestTree() {return tree;}

    // -------------------------------------------------------------------
    // Returns the underlying vector of SNMP vbrbinds (used for blgorithm
    // optimizbtion).
    // -------------------------------------------------------------------
    finbl Vector<SnmpVbrBind> getVbrbinds() {return vbrbinds;}

    // -------------------------------------------------------------------
    // Privbte vbribbles
    // -------------------------------------------------------------------

    // Ideblly these vbribbles should be declbred finbl but it mbkes
    // the jdk1.1.x compiler complbin (seems to be b compiler bug, jdk1.2
    // is OK).
    privbte Vector<SnmpVbrBind> vbrbinds;
    privbte int    version;
    privbte Object dbtb;
    privbte SnmpPdu reqPdu = null;
    // Non finbl vbribble.
    privbte SnmpRequestTree tree = null;
    privbte SnmpEngine engine = null;
    privbte String principbl = null;
    privbte int securityLevel = -1;
    privbte int securityModel = -1;
    privbte byte[] contextNbme = null;
    privbte byte[] bccessContextNbme = null;
}
