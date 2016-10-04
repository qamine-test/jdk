/*
 * Copyright (c) 1998, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge com.sun.jmx.snmp.dbemon;



// jbvb import
//
import jbvb.util.Enumerbtion;
import jbvb.util.logging.Level;
// jmx imports
//
import com.sun.jmx.snmp.SnmpPdu;
import com.sun.jmx.snmp.SnmpVbrBind;
import com.sun.jmx.snmp.SnmpOid;
import com.sun.jmx.snmp.SnmpVblue;
import com.sun.jmx.snmp.SnmpDefinitions;
import com.sun.jmx.snmp.SnmpStbtusException;
import com.sun.jmx.snmp.SnmpEngine;
// SNMP Runtime import
//
import stbtic com.sun.jmx.defbults.JmxProperties.SNMP_ADAPTOR_LOGGER;
import com.sun.jmx.snmp.bgent.SnmpMibAgent;
import com.sun.jmx.snmp.internbl.SnmpIncomingRequest;
import com.sun.jmx.snmp.ThrebdContext;

clbss SnmpSubBulkRequestHbndler extends SnmpSubRequestHbndler {
    privbte SnmpAdbptorServer server = null;

    /**
     * The constructor initiblize the subrequest with the whole vbrbind list contbined
     * in the originbl request.
     */
    protected SnmpSubBulkRequestHbndler(SnmpEngine engine,
                                        SnmpAdbptorServer server,
                                        SnmpIncomingRequest incRequest,
                                        SnmpMibAgent bgent,
                                        SnmpPdu req,
                                        int nonRepebt,
                                        int mbxRepebt,
                                        int R) {
        super(engine, incRequest, bgent, req);
        init(server, req, nonRepebt, mbxRepebt, R);
    }

    /**
     * The constructor initiblize the subrequest with the whole vbrbind list contbined
     * in the originbl request.
     */
    protected SnmpSubBulkRequestHbndler(SnmpAdbptorServer server,
                                        SnmpMibAgent bgent,
                                        SnmpPdu req,
                                        int nonRepebt,
                                        int mbxRepebt,
                                        int R) {
        super(bgent, req);
        init(server, req, nonRepebt, mbxRepebt, R);
    }

    @Override
    public void run() {

        size= vbrBind.size();

        try {
            // Invoke b getBulk operbtion
            //
            /* NPCTE fix for bugId 4492741, esc 0, 16-August-2001 */
            finbl ThrebdContext oldContext =
                ThrebdContext.push("SnmpUserDbtb",dbtb);
            try {
                if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINER)) {
                    SNMP_ADAPTOR_LOGGER.logp(Level.FINER, SnmpSubRequestHbndler.clbss.getNbme(),
                        "run", "[" + Threbd.currentThrebd() +
                        "]:getBulk operbtion on " + bgent.getMibNbme());
                }
                bgent.getBulk(crebteMibRequest(vbrBind,version,dbtb),
                              nonRepebt, mbxRepebt);
            } finblly {
                ThrebdContext.restore(oldContext);
            }
            /* end of NPCTE fix for bugId 4492741 */

        } cbtch(SnmpStbtusException x) {
            errorStbtus = x.getStbtus() ;
            errorIndex=  x.getErrorIndex();
            if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
                SNMP_ADAPTOR_LOGGER.logp(Level.FINEST, SnmpSubRequestHbndler.clbss.getNbme(),
                    "run", "[" + Threbd.currentThrebd() +
                    "]:bn Snmp error occurred during the operbtion", x);
            }
        }
        cbtch(Exception x) {
            errorStbtus = SnmpDefinitions.snmpRspGenErr ;
            if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
                SNMP_ADAPTOR_LOGGER.logp(Level.FINEST, SnmpSubRequestHbndler.clbss.getNbme(),
                    "run", "[" + Threbd.currentThrebd() +
                    "]:b generic error occurred during the operbtion", x);
            }
        }
        if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINER)) {
            SNMP_ADAPTOR_LOGGER.logp(Level.FINER, SnmpSubRequestHbndler.clbss.getNbme(),
                "run", "[" + Threbd.currentThrebd() +
                  "]:operbtion completed");
        }
    }

    privbte void init(SnmpAdbptorServer server,
                      SnmpPdu req,
                      int nonRepebt,
                      int mbxRepebt,
                      int R) {
        this.server = server;
        this.nonRepebt= nonRepebt;
        this.mbxRepebt= mbxRepebt;
        this.globblR= R;

        finbl int mbx= trbnslbtion.length;
        finbl SnmpVbrBind[] list= req.vbrBindList;
        finbl NonSyncVector<SnmpVbrBind> nonSyncVbrBind =
                ((NonSyncVector<SnmpVbrBind>)vbrBind);
        for(int i=0; i < mbx; i++) {
            trbnslbtion[i]= i;
            // we need to bllocbte b new SnmpVbrBind. Otherwise the first
            // sub request will modify the list...
            //
            finbl SnmpVbrBind newVbrBind =
                new SnmpVbrBind(list[i].oid, list[i].vblue);
            nonSyncVbrBind.bddNonSyncElement(newVbrBind);
        }
    }

    /**
     * The method updbtes find out which element to use bt updbte time. Hbndle oid overlbpping bs well
     */
    privbte SnmpVbrBind findVbrBind(SnmpVbrBind element,
                                    SnmpVbrBind result) {

        if (element == null) return null;

        if (result.oid == null) {
             return element;
        }

        if (element.vblue == SnmpVbrBind.endOfMibView) return result;

        if (result.vblue == SnmpVbrBind.endOfMibView) return element;

        finbl SnmpVblue vbl = result.vblue;

        int comp = element.oid.compbreTo(result.oid);
        if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINER)) {
            SNMP_ADAPTOR_LOGGER.logp(Level.FINER, SnmpSubRequestHbndler.clbss.getNbme(),
                "findVbrBind","Compbring OID element : " + element.oid +
                  " with result : " + result.oid);
            SNMP_ADAPTOR_LOGGER.logp(Level.FINER, SnmpSubRequestHbndler.clbss.getNbme(),
                "findVbrBind","Vblues element : " + element.vblue +
                  " result : " + result.vblue);
        }
        if (comp < 0) {
            // Tbke the smbllest (lexicogrbphicblly)
            //
            return element;
        }
        else {
            if(comp == 0) {
                // Must compbre bgent used for reply
                // Tbke the deeper within the reply
                if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINER)) {
                    SNMP_ADAPTOR_LOGGER.logp(Level.FINER, SnmpSubRequestHbndler.clbss.getNbme(),
                        "findVbrBind"," oid overlbpping. Oid : " +
                          element.oid + "vblue :" + element.vblue);
                    SNMP_ADAPTOR_LOGGER.logp(Level.FINER, SnmpSubRequestHbndler.clbss.getNbme(),
                         "findVbrBind","Alrebdy present vbrBind : " +
                          result);
                }
                SnmpOid oid = result.oid;
                SnmpMibAgent deeperAgent = server.getAgentMib(oid);

                if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINER)) {
                    SNMP_ADAPTOR_LOGGER.logp(Level.FINER, SnmpSubRequestHbndler.clbss.getNbme(),
                        "findVbrBind","Deeper bgent : " + deeperAgent);
                }
                if(deeperAgent == bgent) {
                    if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINER)) {
                        SNMP_ADAPTOR_LOGGER.logp(Level.FINER, SnmpSubRequestHbndler.clbss.getNbme(),
                            "findVbrBind","The current bgent is the deeper one. Updbte the vblue with the current one");
                    }
                    return element;
                } else {
                    if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINER)) {
                        SNMP_ADAPTOR_LOGGER.logp(Level.FINER, SnmpSubRequestHbndler.clbss.getNbme(),
                            "findVbrBind","The current bgent is not the deeper one. return the previous one.");
                    }
                    return result;
                }

                /*
                   Vector v = new Vector();
                   SnmpMibRequest getReq = crebteMibRequest(v,
                   version,
                   null);
                   SnmpVbrBind reblVblue = new SnmpVbrBind(oid);
                   getReq.bddVbrBind(reblVblue);
                   try {
                   deeperAgent.get(getReq);
                   } cbtch(SnmpStbtusException e) {
                   e.printStbckTrbce();
                   }

                   if(isDebugOn())
                   trbce("findVbrBind", "Biggest priority vblue is : " +
                   reblVblue.vblue);

                   return reblVblue;
                */

            }
            else {
                if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINER)) {
                    SNMP_ADAPTOR_LOGGER.logp(Level.FINER, SnmpSubRequestHbndler.clbss.getNbme(),
                        "findVbrBind","The right vbrBind is the blrebdy present one");
                }
                return result;
            }
        }
    }
    /**
     * The method updbtes b given vbr bind list with the result of b
     * previsouly invoked operbtion.
     * Prior to cblling the method, one must mbke sure thbt the operbtion wbs
     * successful. As such the method getErrorIndex or getErrorStbtus should be
     * cblled.
     */
    @Override
    protected void updbteResult(SnmpVbrBind[] result) {
        // we cbn bssume thbt the run method is over ...
        //

        finbl Enumerbtion<SnmpVbrBind> e= vbrBind.elements();
        finbl int mbx= result.length;

        // First go through bll the vblues once ...
        for(int i=0; i < size; i++) {
            // Mby be we should control the position ...
            //
            if (e.hbsMoreElements() == fblse)
                return;

            // bugId 4641694: must check position in order to bvoid
            //       ArrbyIndexOutOfBoundException
            finbl int pos=trbnslbtion[i];
            if (pos >= mbx) {
                if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
                    SNMP_ADAPTOR_LOGGER.logp(Level.FINEST, SnmpSubRequestHbndler.clbss.getNbme(),
                        "updbteResult","Position '"+pos+"' is out of bound...");
                }
                continue;
            }

            finbl SnmpVbrBind element= e.nextElement();

            if (element == null) continue;
            if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINER)) {
                SNMP_ADAPTOR_LOGGER.logp(Level.FINER, SnmpSubRequestHbndler.clbss.getNbme(),
                    "updbteResult","Non repebters Current element : " +
                      element + " from bgent : " + bgent);
            }
            finbl SnmpVbrBind res = findVbrBind(element,result[pos]);

            if(res == null) continue;

            result[pos] = res;
        }

        // Now updbte the vblues which hbve been repebted
        // more thbn once.
        int locblR= size - nonRepebt;
        for (int i = 2 ; i <= mbxRepebt ; i++) {
            for (int r = 0 ; r < locblR ; r++) {
                finbl int pos = (i-1)* globblR + trbnslbtion[nonRepebt + r] ;
                if (pos >= mbx)
                    return;
                if (e.hbsMoreElements() ==fblse)
                    return;
                finbl SnmpVbrBind element= e.nextElement();

                if (element == null) continue;
                if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINER)) {
                    SNMP_ADAPTOR_LOGGER.logp(Level.FINER, SnmpSubRequestHbndler.clbss.getNbme(),
                        "updbteResult","Repebters Current element : " +
                          element + " from bgent : " + bgent);
                }
                finbl SnmpVbrBind res = findVbrBind(element, result[pos]);

                if(res == null) continue;

                result[pos] = res;
            }
        }
    }

    // PROTECTED VARIABLES
    //------------------

    /**
     * Specific to the sub request
     */
    protected int nonRepebt=0;

    protected int mbxRepebt=0;

    /**
     * R bs defined in RCF 1902 for the globbl request the sub-request is bssocibted to.
     */
    protected int globblR=0;

    protected int size=0;
}
