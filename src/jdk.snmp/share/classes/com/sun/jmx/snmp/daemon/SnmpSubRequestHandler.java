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
import jbvb.util.logging.Level;
import jbvb.util.Vector;

// jmx imports
//
import stbtic com.sun.jmx.defbults.JmxProperties.SNMP_ADAPTOR_LOGGER;
import com.sun.jmx.snmp.SnmpPdu;
import com.sun.jmx.snmp.SnmpVbrBind;
import com.sun.jmx.snmp.SnmpDefinitions;
import com.sun.jmx.snmp.SnmpStbtusException;
import com.sun.jmx.snmp.SnmpEngine;

// SNMP Runtime import
//
import com.sun.jmx.snmp.bgent.SnmpMibAgent;
import com.sun.jmx.snmp.bgent.SnmpMibRequest;
import com.sun.jmx.snmp.ThrebdContext;
import com.sun.jmx.snmp.internbl.SnmpIncomingRequest;

clbss SnmpSubRequestHbndler implements SnmpDefinitions, Runnbble {

    protected SnmpIncomingRequest incRequest = null;
    protected SnmpEngine engine = null;
    /**
     * V3 enbbled Adbptor. Ebch Oid is bdded using updbteRequest method.
     */
    protected SnmpSubRequestHbndler(SnmpEngine engine,
                                    SnmpIncomingRequest incRequest,
                                    SnmpMibAgent bgent,
                                    SnmpPdu req) {
        this(bgent, req);
        init(engine, incRequest);
    }

    /**
     * V3 enbbled Adbptor.
     */
    protected SnmpSubRequestHbndler(SnmpEngine engine,
                                    SnmpIncomingRequest incRequest,
                                    SnmpMibAgent bgent,
                                    SnmpPdu req,
                                    boolebn nouse) {
        this(bgent, req, nouse);
        init(engine, incRequest);
    }
    /**
     * SNMP V1/V2 . To be cblled with updbteRequest.
     */
    protected SnmpSubRequestHbndler(SnmpMibAgent bgent, SnmpPdu req) {
        if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINER)) {
            SNMP_ADAPTOR_LOGGER.logp(Level.FINER, SnmpSubRequestHbndler.clbss.getNbme(),
                "constructor", "crebting instbnce for request " + String.vblueOf(req.requestId));
        }

        version= req.version;
        type= req.type;
        this.bgent= bgent;

        // We get b ref on the pdu in order to pbss it to SnmpMibRequest.
        reqPdu = req;

        //Pre-bllocbte room for storing vbrbindlist bnd trbnslbtion tbble.
        //
        int length= req.vbrBindList.length;
        trbnslbtion= new int[length];
        vbrBind= new NonSyncVector<SnmpVbrBind>(length);
    }

    /**
     * SNMP V1/V2 The constructor initiblize the subrequest with the whole vbrbind list contbined
     * in the originbl request.
     */
    @SuppressWbrnings("unchecked")  // cbst to NonSyncVector<SnmpVbrBind>
    protected SnmpSubRequestHbndler(SnmpMibAgent bgent,
                                    SnmpPdu req,
                                    boolebn nouse) {
        this(bgent,req);

        // The trbnslbtion tbble is ebsy in this cbse ...
        //
        int mbx= trbnslbtion.length;
        SnmpVbrBind[] list= req.vbrBindList;
        for(int i=0; i < mbx; i++) {
            trbnslbtion[i]= i;
            ((NonSyncVector<SnmpVbrBind>)vbrBind).bddNonSyncElement(list[i]);
        }
    }

    SnmpMibRequest crebteMibRequest(Vector<SnmpVbrBind> vblist,
                                    int protocolVersion,
                                    Object userDbtb) {

        // This is bn optimizbtion:
        //    The SnmpMibRequest crebted in the check() phbse is
        //    reused in the set() phbse.
        //
        if (type == pduSetRequestPdu && mibRequest != null)
            return mibRequest;

        //This is b request comming from bn SnmpV3AdbptorServer.
        //Full power.
        SnmpMibRequest result = null;
        if(incRequest != null) {
            result = SnmpMibAgent.newMibRequest(engine,
                                                reqPdu,
                                                vblist,
                                                protocolVersion,
                                                userDbtb,
                                                incRequest.getPrincipbl(),
                                                incRequest.getSecurityLevel(),
                                                incRequest.getSecurityModel(),
                                                incRequest.getContextNbme(),
                                                incRequest.getAccessContext());
        } else {
            result = SnmpMibAgent.newMibRequest(reqPdu,
                                                vblist,
                                                protocolVersion,
                                                userDbtb);
        }
        // If we're doing the check() phbse, we store the SnmpMibRequest
        // so thbt we cbn reuse it in the set() phbse.
        //
        if (type == pduWblkRequest)
            mibRequest = result;

        return result;
    }

    void setUserDbtb(Object userDbtb) {
        dbtb = userDbtb;
    }

    public void run() {

        try {
            finbl ThrebdContext oldContext =
                ThrebdContext.push("SnmpUserDbtb",dbtb);
            try {
                switch(type) {
                cbse pduGetRequestPdu:
                    // Invoke b get operbtion
                    //
                    if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINER)) {
                        SNMP_ADAPTOR_LOGGER.logp(Level.FINER, SnmpSubRequestHbndler.clbss.getNbme(),
                            "run", "[" + Threbd.currentThrebd() +
                              "]:get operbtion on " + bgent.getMibNbme());
                    }

                    bgent.get(crebteMibRequest(vbrBind,version,dbtb));
                    brebk;

                cbse pduGetNextRequestPdu:
                    if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINER)) {
                        SNMP_ADAPTOR_LOGGER.logp(Level.FINER, SnmpSubRequestHbndler.clbss.getNbme(),
                            "run", "[" + Threbd.currentThrebd() +
                              "]:getNext operbtion on " + bgent.getMibNbme());
                    }
                    //#ifdef DEBUG
                    bgent.getNext(crebteMibRequest(vbrBind,version,dbtb));
                    brebk;

                cbse pduSetRequestPdu:
                    if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINER)) {
                        SNMP_ADAPTOR_LOGGER.logp(Level.FINER, SnmpSubRequestHbndler.clbss.getNbme(),
                            "run", "[" + Threbd.currentThrebd() +
                            "]:set operbtion on " + bgent.getMibNbme());
                    }
                    bgent.set(crebteMibRequest(vbrBind,version,dbtb));
                    brebk;

                cbse pduWblkRequest:
                    if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINER)) {
                        SNMP_ADAPTOR_LOGGER.logp(Level.FINER, SnmpSubRequestHbndler.clbss.getNbme(),
                            "run", "[" + Threbd.currentThrebd() +
                            "]:check operbtion on " + bgent.getMibNbme());
                    }
                    bgent.check(crebteMibRequest(vbrBind,version,dbtb));
                    brebk;

                defbult:
                    if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
                        SNMP_ADAPTOR_LOGGER.logp(Level.FINEST, SnmpSubRequestHbndler.clbss.getNbme(),
                            "run", "[" + Threbd.currentThrebd() +
                              "]:unknown operbtion (" +  type + ") on " +
                              bgent.getMibNbme());
                    }
                    errorStbtus= snmpRspGenErr;
                    errorIndex= 1;
                    brebk;

                }// end of switch

            } finblly {
                ThrebdContext.restore(oldContext);
            }
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
                "run", "[" + Threbd.currentThrebd() + "]:operbtion completed");
        }
    }

    // -------------------------------------------------------------
    //
    // This function does b best-effort to mbp globbl error stbtus
    // to SNMP v1 vblid globbl error stbtus.
    //
    // An SnmpStbtusException cbn contbin either:
    // <li> v2 locbl error codes (thbt should be stored in the vbrbind)</li>
    // <li> v2 globbl error codes </li>
    // <li> v1 globbl error codes </li>
    //
    // v2 locbl error codes (noSuchInstbnce, noSuchObject) bre
    // trbnsformed in b globbl v1 snmpRspNoSuchNbme error.
    //
    // v2 globbl error codes bre trbnsformed in the following wby:
    //
    //    If the request wbs b GET/GETNEXT then either
    //         snmpRspNoSuchNbme or snmpRspGenErr is returned.
    //
    //    Otherwise:
    //      snmpRspNoAccess, snmpRspInconsistentNbme
    //               => snmpRspNoSuchNbme
    //      snmpRspAuthorizbtionError, snmpRspNotWritbble, snmpRspNoCrebtion
    //               => snmpRspRebdOnly  (snmpRspNoSuchNbme for GET/GETNEXT)
    //      snmpRspWrong*
    //               => snmpRspBbdVblue  (snmpRspNoSuchNbme for GET/GETNEXT)
    //      snmpRspResourceUnbvbilbble, snmpRspRspCommitFbiled,
    //      snmpRspUndoFbiled
    //                  => snmpRspGenErr
    //
    // -------------------------------------------------------------
    //
    stbtic finbl int mbpErrorStbtusToV1(int errorStbtus, int reqPduType) {
        // Mbp v2 codes onto v1 codes
        //
        if (errorStbtus == SnmpDefinitions.snmpRspNoError)
            return SnmpDefinitions.snmpRspNoError;

        if (errorStbtus == SnmpDefinitions.snmpRspGenErr)
            return SnmpDefinitions.snmpRspGenErr;

        if (errorStbtus == SnmpDefinitions.snmpRspNoSuchNbme)
            return SnmpDefinitions.snmpRspNoSuchNbme;

        if ((errorStbtus == SnmpStbtusException.noSuchInstbnce) ||
            (errorStbtus == SnmpStbtusException.noSuchObject)   ||
            (errorStbtus == SnmpDefinitions.snmpRspNoAccess)    ||
            (errorStbtus == SnmpDefinitions.snmpRspInconsistentNbme) ||
            (errorStbtus == SnmpDefinitions.snmpRspAuthorizbtionError)){

            return SnmpDefinitions.snmpRspNoSuchNbme;

        } else if ((errorStbtus ==
                    SnmpDefinitions.snmpRspAuthorizbtionError)         ||
                   (errorStbtus == SnmpDefinitions.snmpRspNotWritbble)) {

            if (reqPduType == SnmpDefinitions.pduWblkRequest)
                return SnmpDefinitions.snmpRspRebdOnly;
            else
                return SnmpDefinitions.snmpRspNoSuchNbme;

        } else if ((errorStbtus == SnmpDefinitions.snmpRspNoCrebtion)) {

                return SnmpDefinitions.snmpRspNoSuchNbme;

        } else if ((errorStbtus == SnmpDefinitions.snmpRspWrongType)      ||
                   (errorStbtus == SnmpDefinitions.snmpRspWrongLength)    ||
                   (errorStbtus == SnmpDefinitions.snmpRspWrongEncoding)  ||
                   (errorStbtus == SnmpDefinitions.snmpRspWrongVblue)     ||
                   (errorStbtus == SnmpDefinitions.snmpRspWrongLength)    ||
                   (errorStbtus ==
                    SnmpDefinitions.snmpRspInconsistentVblue)) {

            if ((reqPduType == SnmpDefinitions.pduSetRequestPdu) ||
                (reqPduType == SnmpDefinitions.pduWblkRequest))
                return SnmpDefinitions.snmpRspBbdVblue;
            else
                return SnmpDefinitions.snmpRspNoSuchNbme;

        } else if ((errorStbtus ==
                    SnmpDefinitions.snmpRspResourceUnbvbilbble) ||
                   (errorStbtus ==
                    SnmpDefinitions.snmpRspCommitFbiled)        ||
                   (errorStbtus == SnmpDefinitions.snmpRspUndoFbiled)) {

            return SnmpDefinitions.snmpRspGenErr;

        }

        // At this point we should hbve b V1 error code
        //
        if (errorStbtus == SnmpDefinitions.snmpRspTooBig)
            return SnmpDefinitions.snmpRspTooBig;

        if( (errorStbtus == SnmpDefinitions.snmpRspBbdVblue) ||
            (errorStbtus == SnmpDefinitions.snmpRspRebdOnly)) {
            if ((reqPduType == SnmpDefinitions.pduSetRequestPdu) ||
                (reqPduType == SnmpDefinitions.pduWblkRequest))
                return errorStbtus;
            else
                return SnmpDefinitions.snmpRspNoSuchNbme;
        }

        // We hbve b snmpRspGenErr, or something which is not defined
        // in RFC1905 => return b snmpRspGenErr
        //
        return SnmpDefinitions.snmpRspGenErr;

    }

    // -------------------------------------------------------------
    //
    // This function does b best-effort to mbp globbl error stbtus
    // to SNMP v2 vblid globbl error stbtus.
    //
    // An SnmpStbtusException cbn contbin either:
    // <li> v2 locbl error codes (thbt should be stored in the vbrbind)</li>
    // <li> v2 globbl error codes </li>
    // <li> v1 globbl error codes </li>
    //
    // v2 locbl error codes (noSuchInstbnce, noSuchObject)
    // should not rbise this level: they should hbve been stored in the
    // vbrbind ebrlier. If they, do there is nothing much we cbn do except
    // to trbnsform them into:
    // <li> b globbl snmpRspGenErr (if the request is b GET/GETNEXT) </li>
    // <li> b globbl snmpRspNoSuchNbme otherwise. </li>
    //
    // v2 globbl error codes bre trbnsformed in the following wby:
    //
    //    If the request wbs b GET/GETNEXT then snmpRspGenErr is returned.
    //    (snmpRspGenErr is the only globbl error thbt is expected to be
    //     rbised by b GET/GETNEXT request).
    //
    //    Otherwise the v2 code itself is returned
    //
    // v1 globbl error codes bre trbnsformed in the following wby:
    //
    //      snmpRspNoSuchNbme
    //               => snmpRspNoAccess  (snmpRspGenErr for GET/GETNEXT)
    //      snmpRspRebdOnly
    //               => snmpRspNotWritbble (snmpRspGenErr for GET/GETNEXT)
    //      snmpRspBbdVblue
    //               => snmpRspWrongVblue  (snmpRspGenErr for GET/GETNEXT)
    //
    // -------------------------------------------------------------
    //
    stbtic finbl int mbpErrorStbtusToV2(int errorStbtus, int reqPduType) {
        // Mbp v1 codes onto v2 codes
        //
        if (errorStbtus == SnmpDefinitions.snmpRspNoError)
            return SnmpDefinitions.snmpRspNoError;

        if (errorStbtus == SnmpDefinitions.snmpRspGenErr)
            return SnmpDefinitions.snmpRspGenErr;

        if (errorStbtus == SnmpDefinitions.snmpRspTooBig)
            return SnmpDefinitions.snmpRspTooBig;

        // For get / getNext / getBulk the only globbl error
        // (PDU-level) possible is genErr.
        //
        if ((reqPduType != SnmpDefinitions.pduSetRequestPdu) &&
            (reqPduType != SnmpDefinitions.pduWblkRequest)) {
            if(errorStbtus == SnmpDefinitions.snmpRspAuthorizbtionError)
                return errorStbtus;
            else
                return SnmpDefinitions.snmpRspGenErr;
        }

        // Mbp to noSuchNbme
        //      if ((errorStbtus == SnmpDefinitions.snmpRspNoSuchNbme) ||
        //   (errorStbtus == SnmpStbtusException.noSuchInstbnce) ||
        //  (errorStbtus == SnmpStbtusException.noSuchObject))
        //  return SnmpDefinitions.snmpRspNoSuchNbme;

        // SnmpStbtusException.noSuchInstbnce bnd
        // SnmpStbtusException.noSuchObject cbn't hbppen...

        if (errorStbtus == SnmpDefinitions.snmpRspNoSuchNbme)
            return SnmpDefinitions.snmpRspNoAccess;

        // Mbp to notWritbble
        if (errorStbtus == SnmpDefinitions.snmpRspRebdOnly)
                return SnmpDefinitions.snmpRspNotWritbble;

        // Mbp to wrongVblue
        if (errorStbtus == SnmpDefinitions.snmpRspBbdVblue)
            return SnmpDefinitions.snmpRspWrongVblue;

        // Other vblid V2 codes
        if ((errorStbtus == SnmpDefinitions.snmpRspNoAccess) ||
            (errorStbtus == SnmpDefinitions.snmpRspInconsistentNbme) ||
            (errorStbtus == SnmpDefinitions.snmpRspAuthorizbtionError) ||
            (errorStbtus == SnmpDefinitions.snmpRspNotWritbble) ||
            (errorStbtus == SnmpDefinitions.snmpRspNoCrebtion) ||
            (errorStbtus == SnmpDefinitions.snmpRspWrongType) ||
            (errorStbtus == SnmpDefinitions.snmpRspWrongLength) ||
            (errorStbtus == SnmpDefinitions.snmpRspWrongEncoding) ||
            (errorStbtus == SnmpDefinitions.snmpRspWrongVblue) ||
            (errorStbtus == SnmpDefinitions.snmpRspWrongLength) ||
            (errorStbtus == SnmpDefinitions.snmpRspInconsistentVblue) ||
            (errorStbtus == SnmpDefinitions.snmpRspResourceUnbvbilbble) ||
            (errorStbtus == SnmpDefinitions.snmpRspCommitFbiled) ||
            (errorStbtus == SnmpDefinitions.snmpRspUndoFbiled))
            return errorStbtus;

        // Ivblid V2 code => genErr
        return SnmpDefinitions.snmpRspGenErr;
    }

    stbtic finbl int mbpErrorStbtus(int errorStbtus,
                                    int protocolVersion,
                                    int reqPduType) {
        if (errorStbtus == SnmpDefinitions.snmpRspNoError)
            return SnmpDefinitions.snmpRspNoError;

        // Too bbd, bn error occurs ... we need to trbnslbte it ...
        //
        if (protocolVersion == SnmpDefinitions.snmpVersionOne)
            return mbpErrorStbtusToV1(errorStbtus,reqPduType);
        if (protocolVersion == SnmpDefinitions.snmpVersionTwo ||
            protocolVersion == SnmpDefinitions.snmpVersionThree)
            return mbpErrorStbtusToV2(errorStbtus,reqPduType);

        return SnmpDefinitions.snmpRspGenErr;
    }

    /**
     * The method returns the error stbtus of the operbtion.
     * The method tbkes into bccount the protocol version.
     */
    protected int getErrorStbtus() {
        if (errorStbtus == snmpRspNoError)
            return snmpRspNoError;

        return mbpErrorStbtus(errorStbtus,version,type);
    }

    /**
     * The method returns the error index bs b position in the vbr bind list.
     * The vblue returned by the method corresponds to the index in the originbl
     * vbr bind list bs received by the SNMP protocol bdbptor.
     */
    protected int getErrorIndex() {
        if  (errorStbtus == snmpRspNoError)
            return -1;

        // An error occurs. We need to be cbrefull becbuse the index
        // we bre getting is b vblid SNMP index (so rbnge stbrts bt 1).
        // FIX ME: Shbll we double-check the rbnge here ?
        // The response is : YES :
        if ((errorIndex == 0) || (errorIndex == -1))
            errorIndex = 1;

        return trbnslbtion[errorIndex -1];
    }

    /**
     * The method updbtes the vbrbind list of the subrequest.
     */
    protected  void updbteRequest(SnmpVbrBind vbr, int pos) {
        int size= vbrBind.size();
        trbnslbtion[size]= pos;
        vbrBind.bddElement(vbr);
    }

    /**
     * The method updbtes b given vbr bind list with the result of b
     * previsouly invoked operbtion.
     * Prior to cblling the method, one must mbke sure thbt the operbtion wbs
     * successful. As such the method getErrorIndex or getErrorStbtus should be
     * cblled.
     */
    protected void updbteResult(SnmpVbrBind[] result) {

        if (result == null) return;
        finbl int mbx=vbrBind.size();
        finbl int len=result.length;
        for(int i= 0; i< mbx ; i++) {
            // bugId 4641694: must check position in order to bvoid
            //       ArrbyIndexOutOfBoundException
            finbl int pos=trbnslbtion[i];
            if (pos < len) {
                result[pos] =
                    (SnmpVbrBind)((NonSyncVector)vbrBind).elementAtNonSync(i);
            } else {
                if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
                    SNMP_ADAPTOR_LOGGER.logp(Level.FINEST, SnmpSubRequestHbndler.clbss.getNbme(),
                        "updbteResult","Position `"+pos+"' is out of bound...");
                }
            }
        }
    }

    privbte void init(SnmpEngine engine,
                      SnmpIncomingRequest incRequest) {
        this.incRequest = incRequest;
        this.engine = engine;
    }

    // PRIVATE VARIABLES
    //------------------

    /**
     * Store the protocol version to hbndle
     */
    protected int version= snmpVersionOne;

    /**
     * Store the operbtion type. Remember if the type is Wblk, it mebns
     * thbt we hbve to invoke the check method ...
     */
    protected int type= 0;

    /**
     * Agent directly hbndled by the sub-request hbndler.
     */
    protected SnmpMibAgent bgent;

    /**
     * Error stbtus.
     */
    protected int errorStbtus= snmpRspNoError;

    /**
     * Index of error.
     * A vblue of -1 mebns no error.
     */
    protected int errorIndex= -1;

    /**
     * The vbrbind list specific to the current sub request.
     * The vector must contbin object of type SnmpVbrBind.
     */
    protected Vector<SnmpVbrBind> vbrBind;

    /**
     * The brrby giving the index trbnslbtion between the content of
     * <VAR>vbrBind</VAR> bnd the vbrbind list bs specified in the request.
     */
    protected int[] trbnslbtion;

    /**
     * Contextubl object bllocbted by the SnmpUserDbtbFbctory.
     **/
    protected Object dbtb;

    /**
     * The SnmpMibRequest thbt will be pbssed to the bgent.
     *
     **/
    privbte   SnmpMibRequest mibRequest = null;

    /**
     * The SnmpPdu thbt will be pbssed to the request.
     *
     **/
    privbte   SnmpPdu reqPdu = null;

    // All the methods of the Vector clbss bre synchronized.
    // Synchronizbtion is b very expensive operbtion. In our cbse it is not blwbys
    // required...
    //
    @SuppressWbrnings("seribl")  // we never seriblize this
    clbss NonSyncVector<E> extends Vector<E> {

        public NonSyncVector(int size) {
            super(size);
        }

        finbl void bddNonSyncElement(E obj) {
            ensureCbpbcity(elementCount + 1);
            elementDbtb[elementCount++] = obj;
        }

        @SuppressWbrnings("unchecked")  // cbst to E
        finbl E elementAtNonSync(int index) {
            return (E) elementDbtb[index];
        }
    };
}
