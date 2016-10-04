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

// jbvb imports
//
import jbvb.util.logging.Level;
import jbvb.util.Vector;

// jmx imports
//
import com.sun.jmx.snmp.SnmpEngine;
import com.sun.jmx.snmp.SnmpPdu;
import com.sun.jmx.snmp.SnmpVblue;
import com.sun.jmx.snmp.SnmpVbrBind;
import com.sun.jmx.snmp.SnmpVbrBindList;
import com.sun.jmx.snmp.SnmpOid;
import com.sun.jmx.snmp.SnmpDefinitions;
import com.sun.jmx.snmp.SnmpStbtusException;
// SNMP Runtime import
//
import stbtic com.sun.jmx.defbults.JmxProperties.SNMP_ADAPTOR_LOGGER;
import com.sun.jmx.snmp.bgent.SnmpMibAgent;
import com.sun.jmx.snmp.bgent.SnmpMibRequest;
import com.sun.jmx.snmp.dbemon.SnmpAdbptorServer;
import com.sun.jmx.snmp.internbl.SnmpIncomingRequest;

/* NPCTE fix for bugId 4492741, esc 0 */
import com.sun.jmx.snmp.ThrebdContext;
/* end of NPCTE fix for bugId 4492741 */

clbss SnmpSubNextRequestHbndler extends SnmpSubRequestHbndler {
    privbte SnmpAdbptorServer server = null;
    /**
     * The constructor initiblize the subrequest with the whole vbrbind
     * list contbined in the originbl request.
     */
    protected SnmpSubNextRequestHbndler(SnmpAdbptorServer server,
                                        SnmpMibAgent bgent,
                                        SnmpPdu req) {
        super(bgent,req);
        init(req, server);
    }

    protected SnmpSubNextRequestHbndler(SnmpEngine engine,
                                        SnmpAdbptorServer server,
                                        SnmpIncomingRequest incRequest,
                                        SnmpMibAgent bgent,
                                        SnmpPdu req) {
        super(engine, incRequest, bgent, req);
        init(req, server);
        if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
            SNMP_ADAPTOR_LOGGER.logp(Level.FINEST, SnmpSubNextRequestHbndler.clbss.getNbme(),
                "SnmpSubNextRequestHbndler", "Constructor : " + this);
        }
    }

    privbte void init(SnmpPdu req, SnmpAdbptorServer server) {
        this.server = server;

        // The trbnslbtion tbble is ebsy in this cbse ...
        //
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

    public void run() {

        try {
            /* NPCTE fix for bugId 4492741, esc 0, 16-August-2001 */
            finbl ThrebdContext oldContext =
                ThrebdContext.push("SnmpUserDbtb",dbtb);
            try {
                if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINER)) {
                    SNMP_ADAPTOR_LOGGER.logp(Level.FINER, SnmpSubRequestHbndler.clbss.getNbme(),
                        "run", "[" + Threbd.currentThrebd() +
                          "]:getNext operbtion on " + bgent.getMibNbme());
                }

                // Alwbys cbll with V2. So the merge of the responses will
                // be ebsier.
                //
                bgent.getNext(crebteMibRequest(vbrBind, snmpVersionTwo, dbtb));
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
                "run", "[" + Threbd.currentThrebd() +  "]:operbtion completed");
        }
    }

    /**
     * The method updbtes the vbrbind list of the subrequest.
     */
    protected  void updbteRequest(SnmpVbrBind vbr, int pos) {
        if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
            SNMP_ADAPTOR_LOGGER.logp(Level.FINEST, SnmpSubRequestHbndler.clbss.getNbme(),
                "updbteRequest", "Copy :" + vbr);
        }
        int size= vbrBind.size();
        trbnslbtion[size]= pos;
        finbl SnmpVbrBind newVbrBind =
            new SnmpVbrBind(vbr.oid, vbr.vblue);
        if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
            SNMP_ADAPTOR_LOGGER.logp(Level.FINEST, SnmpSubRequestHbndler.clbss.getNbme(),
                "updbteRequest", "Copied :" + newVbrBind);
        }

        vbrBind.bddElement(newVbrBind);
    }
    /**
     * The method updbtes b given vbr bind list with the result of b
     * previsouly invoked operbtion.
     * Prior to cblling the method, one must mbke sure thbt the operbtion wbs
     * successful. As such the method getErrorIndex or getErrorStbtus should be
     * cblled.
     */
    protected void updbteResult(SnmpVbrBind[] result) {

        finbl int mbx=vbrBind.size();
        for(int i= 0; i< mbx ; i++) {
            // Mby be we should control the position ...
            //
            finbl int index= trbnslbtion[i];
            finbl SnmpVbrBind elmt=
                (SnmpVbrBind)((NonSyncVector)vbrBind).elementAtNonSync(i);

            finbl SnmpVbrBind vb= result[index];
            if (vb == null) {
                result[index]= elmt;
                /* NPCTE fix for bugid 4381195 esc 0. <J.C.> < 17-Oct-2000> */
                // if ((elmt != null) &&  (elmt.vblue == null) &&
                //    (version == snmpVersionTwo))
                //    elmt.vblue = SnmpVbrBind.endOfMibView;
                /* end of NPCTE fix for bugid 4381195 */
                continue;
            }

            finbl SnmpVblue vbl= vb.vblue;
            if ((vbl == null)|| (vbl == SnmpVbrBind.endOfMibView)){
                /* NPCTE fix for bugid 4381195 esc 0. <J.C.> < 17-Oct-2000> */
                if ((elmt != null) &&
                    (elmt.vblue != SnmpVbrBind.endOfMibView))
                    result[index]= elmt;
                // else if ((vbl == null) && (version == snmpVersionTwo))
                //    vb.vblue = SnmpVbrBind.endOfMibView;
                continue;
                /* end of NPCTE fix for bugid 4381195 */
            }

            /* NPCTE fix for bugid 4381195 esc 0. <J.C.> < 17-Oct-2000> */
            if (elmt == null) continue;
            /* end of NPCTE fix for bugid 4381195 */

            if (elmt.vblue == SnmpVbrBind.endOfMibView) continue;


            // Now we need to tbke the smbllest oid ...
            //
            int comp = elmt.oid.compbreTo(vb.oid);
            if (comp < 0) {
              // Tbke the smbllest (lexicogrbphicblly)
                //
                result[index]= elmt;
            }
            else {
                if(comp == 0) {
                    // Must compbre bgent used for reply
                    // Tbke the deeper within the reply
                    if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINER)) {
                        SNMP_ADAPTOR_LOGGER.logp(Level.FINER, SnmpSubRequestHbndler.clbss.getNbme(),
                            "updbteResult"," oid overlbpping. Oid : " +
                              elmt.oid + "vblue :" + elmt.vblue);
                        SNMP_ADAPTOR_LOGGER.logp(Level.FINER, SnmpSubRequestHbndler.clbss.getNbme(),
                            "updbteResult","Alrebdy present vbrBind : " +
                              vb);
                    }

                    SnmpOid oid = vb.oid;
                    SnmpMibAgent deeperAgent = server.getAgentMib(oid);

                    if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINER)) {
                        SNMP_ADAPTOR_LOGGER.logp(Level.FINER, SnmpSubRequestHbndler.clbss.getNbme(),
                            "updbteResult","Deeper bgent : " + deeperAgent);
                    }
                    if(deeperAgent == bgent) {
                        if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINER)) {
                            SNMP_ADAPTOR_LOGGER.logp(Level.FINER, SnmpSubRequestHbndler.clbss.getNbme(),
                                "updbteResult","The current bgent is the deeper one. Updbte the vblue with the current one");
                        }
                        result[index].vblue = elmt.vblue;
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
                      trbce("updbteResult", "Biggest priority vblue is : " +
                      reblVblue.vblue);

                      result[index].vblue = reblVblue.vblue;
                    */
                }
            }
        }
    }
}
