/*
 * Copyright (c) 1997, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.util.Vector;

import jbvbx.mbnbgement.ObjectNbme;
import jbvbx.mbnbgement.MBebnServer;
import jbvbx.mbnbgement.MblformedObjectNbmeException;
import jbvbx.mbnbgement.InstbnceAlrebdyExistsException;
import jbvbx.mbnbgement.MBebnRegistrbtionException;
import jbvbx.mbnbgement.NotComplibntMBebnException;

import stbtic com.sun.jmx.defbults.JmxProperties.SNMP_ADAPTOR_LOGGER;
import com.sun.jmx.snmp.SnmpOid;
import com.sun.jmx.snmp.SnmpVbrBind;
import com.sun.jmx.snmp.SnmpDefinitions;
import com.sun.jmx.snmp.SnmpStbtusException;
import com.sun.jmx.snmp.SnmpEngine;
import com.sun.jmx.snmp.SnmpUnknownModelException;
import com.sun.jmx.snmp.internbl.SnmpAccessControlModel;
import com.sun.jmx.snmp.internbl.SnmpEngineImpl;

/**
 * Oid Checker mbkes use of ACM to check ebch OID during the getnext process.
 */
clbss AcmChecker {


    SnmpAccessControlModel model = null;
    String principbl = null;
    int securityLevel = -1;
    int version = -1;
    int pduType = -1;
    int securityModel = -1;
    byte[] contextNbme = null;
    SnmpEngineImpl engine = null;
    LongList l = null;
    AcmChecker(SnmpMibRequest req) {
        engine = (SnmpEngineImpl) req.getEngine();
        //We bre in V3 brchitecture, ACM is in the picture.
        if(engine != null) {
            if(engine.isCheckOidActivbted()) {
                try {
                    if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
                        SNMP_ADAPTOR_LOGGER.logp(Level.FINEST,
                                SnmpMib.clbss.getNbme(),
                                "AcmChecker(SnmpMibRequest)",
                                "SNMP V3 Access Control to be done");
                    }
                    model = (SnmpAccessControlModel)
                        engine.getAccessControlSubSystem().
                        getModel(SnmpDefinitions.snmpVersionThree);
                    principbl = req.getPrincipbl();
                    securityLevel = req.getSecurityLevel();
                    pduType = req.getPdu().type;
                    version = req.getRequestPduVersion();
                    securityModel = req.getSecurityModel();
                    contextNbme = req.getAccessContextNbme();
                    l = new LongList();
                    if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
                        finbl StringBuilder strb = new StringBuilder()
                        .bppend("Will check oid for : principbl : ")
                        .bppend(principbl)
                        .bppend("; securityLevel : ").bppend(securityLevel)
                        .bppend("; pduType : ").bppend(pduType)
                        .bppend("; version : ").bppend(version)
                        .bppend("; securityModel : ").bppend(securityModel)
                        .bppend("; contextNbme : ").bppend(contextNbme);
                        SNMP_ADAPTOR_LOGGER.logp(Level.FINEST,
                                SnmpMib.clbss.getNbme(),
                                "AcmChecker(SnmpMibRequest)", strb.toString());
                    }

                }cbtch(SnmpUnknownModelException e) {
                    if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
                        SNMP_ADAPTOR_LOGGER.logp(Level.FINEST,
                                SnmpMib.clbss.getNbme(),
                                "AcmChecker(SnmpMibRequest)",
                                "Unknown Model, no ACM check.");
                    }
                }
            }
        }
    }

    void bdd(int index, long brc) {
        if(model != null)
            l.bdd(index, brc);
    }

    void remove(int index) {
        if(model != null)
            l.remove(index);
    }

    void bdd(finbl int bt,finbl long[] src, finbl int from,
             finbl int count) {
        if(model != null)
            l.bdd(bt,src,from,count);
    }

    void remove(finbl int from, finbl int count) {
        if(model != null)
            l.remove(from,count);
    }

    void checkCurrentOid() throws SnmpStbtusException {
        if(model != null) {
            SnmpOid oid = new SnmpOid(l.toArrby());
            if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
                SNMP_ADAPTOR_LOGGER.logp(Level.FINEST, SnmpMib.clbss.getNbme(),
                        "checkCurrentOid", "Checking bccess for : " + oid);
            }
            model.checkAccess(version,
                              principbl,
                              securityLevel,
                              pduType,
                              securityModel,
                              contextNbme,
                              oid);
        }
    }

}
