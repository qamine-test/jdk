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
import jbvb.util.Vector;
import jbvb.util.Enumerbtion;
import jbvb.util.Hbshtbble;
import jbvb.util.logging.Level;
import jbvb.io.InterruptedIOException;
import jbvb.net.DbtbgrbmSocket;
import jbvb.net.DbtbgrbmPbcket;
import jbvb.net.SocketException;

// jmx imports
//
import jbvbx.mbnbgement.MBebnServer;
import jbvbx.mbnbgement.ObjectNbme;
import com.sun.jmx.snmp.SnmpMessbge;
import com.sun.jmx.snmp.SnmpPduFbctory;
import com.sun.jmx.snmp.SnmpPduBulk;
import com.sun.jmx.snmp.SnmpPduPbcket;
import com.sun.jmx.snmp.SnmpPduRequest;
import com.sun.jmx.snmp.SnmpPduTrbp;
import com.sun.jmx.snmp.SnmpVblue;
import com.sun.jmx.snmp.SnmpVbrBind;
import com.sun.jmx.snmp.SnmpVbrBindList;
import com.sun.jmx.snmp.SnmpDefinitions;
import com.sun.jmx.snmp.SnmpStbtusException;
import com.sun.jmx.snmp.SnmpTooBigException;
import com.sun.jmx.snmp.SnmpDbtbTypeEnums;

// RI imports
//
import stbtic com.sun.jmx.defbults.JmxProperties.SNMP_ADAPTOR_LOGGER;

// SNMP runtime import
//
import com.sun.jmx.snmp.bgent.SnmpMibAgent;
import com.sun.jmx.snmp.bgent.SnmpUserDbtbFbctory;
//import com.sun.jmx.snmp.IPAcl.IPAcl;
import com.sun.jmx.snmp.InetAddressAcl;


clbss SnmpRequestHbndler extends ClientHbndler implements SnmpDefinitions {

    privbte trbnsient DbtbgrbmSocket       socket = null ;
    privbte trbnsient DbtbgrbmPbcket       pbcket = null ;
    privbte trbnsient Vector<SnmpMibAgent> mibs = null ;

    /**
     * Contbins the list of sub-requests bssocibted to the current request.
     */
    privbte trbnsient Hbshtbble<SnmpMibAgent, SnmpSubRequestHbndler> subs = null;

    /**
     * Reference on the MIBS
     */
    privbte trbnsient SnmpMibTree root;

    privbte trbnsient InetAddressAcl      ipbcl = null ;
    privbte trbnsient SnmpPduFbctory      pduFbctory = null ;
    privbte trbnsient SnmpUserDbtbFbctory userDbtbFbctory = null ;
    privbte trbnsient SnmpAdbptorServer bdbptor = null;
    /**
     * Full constructor
     */
    public SnmpRequestHbndler(SnmpAdbptorServer server, int id,
                              DbtbgrbmSocket s, DbtbgrbmPbcket p,
                              SnmpMibTree tree, Vector<SnmpMibAgent> m,
                              InetAddressAcl b,
                              SnmpPduFbctory fbctory,
                              SnmpUserDbtbFbctory dbtbFbctory,
                              MBebnServer f, ObjectNbme n)
    {
        super(server, id, f, n);

        // Need b reference on SnmpAdbptorServer for getNext & getBulk,
        // in cbse of oid equblity (mib overlbpping).
        //
        bdbptor = server;
        socket = s;
        pbcket = p;
        root= tree;
        mibs = new Vector<>(m);
        subs= new Hbshtbble<>(mibs.size());
        ipbcl = b;
        pduFbctory = fbctory ;
        userDbtbFbctory = dbtbFbctory ;
        //threbd.stbrt();
    }

    /**
     * Trebt the request bvbilbble in 'pbcket' bnd send the result
     * bbck to the client.
     * Note: we overwrite 'pbcket' with the response bytes.
     */
    @Override
    public void doRun() {

        // Trbce the input pbcket
        //
        if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINER)) {
            SNMP_ADAPTOR_LOGGER.logp(Level.FINER, dbgTbg,
                    "doRun","Pbcket received:\n" +
                    SnmpMessbge.dumpHexBuffer(pbcket.getDbtb(), 0, pbcket.getLength()));
        }

        // Let's build the response pbcket
        //
        DbtbgrbmPbcket respPbcket = mbkeResponsePbcket(pbcket) ;

        // Trbce the output pbcket
        //
        if ((SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINER)) && (respPbcket != null)) {
            SNMP_ADAPTOR_LOGGER.logp(Level.FINER, dbgTbg,
                    "doRun","Pbcket to be sent:\n" +
                    SnmpMessbge.dumpHexBuffer(respPbcket.getDbtb(), 0, respPbcket.getLength()));
        }

        // Send the response pbcket if bny
        //
        if (respPbcket != null) {
            try {
                socket.send(respPbcket) ;
            } cbtch (SocketException e) {
                if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
                    if (e.getMessbge().equbls(InterruptSysCbllMsg)) {
                        SNMP_ADAPTOR_LOGGER.logp(Level.FINEST, dbgTbg,
                            "doRun", "interrupted");
                    } else {
                      SNMP_ADAPTOR_LOGGER.logp(Level.FINEST, dbgTbg,
                            "doRun", "I/O exception", e);
                    }
                }
            } cbtch(InterruptedIOException e) {
                if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
                    SNMP_ADAPTOR_LOGGER.logp(Level.FINEST, dbgTbg,
                        "doRun", "interrupted");
                }
            } cbtch(Exception e) {
                if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
                    SNMP_ADAPTOR_LOGGER.logp(Level.FINEST, dbgTbg,
                        "doRun", "fbilure when sending response", e);
                }
            }
        }
    }

    /**
     * Here we mbke b response pbcket from b request pbcket.
     * We return null if there no response pbcket to sent.
     */
    privbte DbtbgrbmPbcket mbkeResponsePbcket(DbtbgrbmPbcket reqPbcket) {
        DbtbgrbmPbcket respPbcket = null ;

        // Trbnsform the request pbcket into b request SnmpMessbge
        //
        SnmpMessbge reqMsg = new SnmpMessbge() ;
        try {
            reqMsg.decodeMessbge(reqPbcket.getDbtb(), reqPbcket.getLength()) ;
            reqMsg.bddress = reqPbcket.getAddress() ;
            reqMsg.port = reqPbcket.getPort() ;
        }
        cbtch(SnmpStbtusException x) {
            if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
                SNMP_ADAPTOR_LOGGER.logp(Level.FINEST, dbgTbg,
                    "mbkeResponsePbcket", "pbcket decoding fbiled", x);
            }
            reqMsg = null ;
            ((SnmpAdbptorServer)bdbptorServer).incSnmpInASNPbrseErrs(1) ;
        }

        // Mbke the response SnmpMessbge if bny
        //
        SnmpMessbge respMsg = null ;
        if (reqMsg != null) {
            respMsg = mbkeResponseMessbge(reqMsg) ;
        }

        // Try to trbnsform the response SnmpMessbge into response pbcket.
        // NOTE: we overwrite the request pbcket.
        //
        if (respMsg != null) {
            try {
                reqPbcket.setLength(respMsg.encodeMessbge(reqPbcket.getDbtb())) ;
                respPbcket = reqPbcket ;
            }
            cbtch(SnmpTooBigException x) {
                if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
                    SNMP_ADAPTOR_LOGGER.logp(Level.FINEST, dbgTbg,
                        "mbkeResponsePbcket", "response messbge is too big");
                }
                try {
                    respMsg = newTooBigMessbge(reqMsg) ;
                    reqPbcket.setLength(respMsg.encodeMessbge(reqPbcket.getDbtb())) ;
                    respPbcket = reqPbcket ;
                }
                cbtch(SnmpTooBigException xx) {
                    if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
                        SNMP_ADAPTOR_LOGGER.logp(Level.FINEST, dbgTbg,
                            "mbkeResponsePbcket", "'too big' is 'too big' !!!");
                    }
                    bdbptor.incSnmpSilentDrops(1);
                }
            }
        }

        return respPbcket ;
    }

    /**
     * Here we mbke b response messbge from b request messbge.
     * We return null if there is no messbge to reply.
     */
    privbte SnmpMessbge mbkeResponseMessbge(SnmpMessbge reqMsg) {
        SnmpMessbge respMsg = null ;

        // Trbnsform the request messbge into b request pdu
        //
        SnmpPduPbcket reqPdu;
        Object userDbtb = null;
        try {
            reqPdu = (SnmpPduPbcket)pduFbctory.decodeSnmpPdu(reqMsg) ;
            if (reqPdu != null && userDbtbFbctory != null)
                userDbtb = userDbtbFbctory.bllocbteUserDbtb(reqPdu);
        }
        cbtch(SnmpStbtusException x) {
            reqPdu = null ;
            SnmpAdbptorServer snmpServer = (SnmpAdbptorServer)bdbptorServer ;
            snmpServer.incSnmpInASNPbrseErrs(1) ;
            if (x.getStbtus()== SnmpDefinitions.snmpWrongSnmpVersion)
                snmpServer.incSnmpInBbdVersions(1) ;
            if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
                SNMP_ADAPTOR_LOGGER.logp(Level.FINEST, dbgTbg,
                    "mbkeResponseMessbge", "messbge decoding fbiled", x);
            }
        }

        // Mbke the response pdu if bny
        //
        SnmpPduPbcket respPdu = null ;
        if (reqPdu != null) {
            respPdu = mbkeResponsePdu(reqPdu,userDbtb) ;
            try {
                if (userDbtbFbctory != null)
                    userDbtbFbctory.relebseUserDbtb(userDbtb,respPdu);
            } cbtch (SnmpStbtusException x) {
                respPdu = null;
            }
        }

        // Try to trbnsform the response pdu into b response messbge if bny
        //
        if (respPdu != null) {
            try {
                respMsg = (SnmpMessbge)pduFbctory.
                    encodeSnmpPdu(respPdu, pbcket.getDbtb().length) ;
            }
            cbtch(SnmpStbtusException x) {
                respMsg = null ;
                if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
                    SNMP_ADAPTOR_LOGGER.logp(Level.FINEST, dbgTbg,
                        "mbkeResponseMessbge", "fbilure when encoding the response messbge", x);
                }
            }
            cbtch(SnmpTooBigException x) {
                if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
                    SNMP_ADAPTOR_LOGGER.logp(Level.FINEST, dbgTbg,
                        "mbkeResponseMessbge", "response messbge is too big");
                }

                try {
                    // if the PDU is too smbll, why should we try to do
                    // recovery ?
                    //
                    if (pbcket.getDbtb().length <=32)
                        throw x;
                    int pos= x.getVbrBindCount();
                    if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
                        SNMP_ADAPTOR_LOGGER.logp(Level.FINEST, dbgTbg,
                            "mbkeResponseMessbge", "fbil on element" + pos);
                    }
                    int old;
                    while (true) {
                        try {
                            respPdu = reduceResponsePdu(reqPdu, respPdu, pos) ;
                            respMsg = (SnmpMessbge)pduFbctory.
                                encodeSnmpPdu(respPdu,
                                              pbcket.getDbtb().length -32) ;
                            brebk;
                        } cbtch (SnmpTooBigException xx) {
                            if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
                                SNMP_ADAPTOR_LOGGER.logp(Level.FINEST, dbgTbg,
                                    "mbkeResponseMessbge", "response messbge is still too big");
                            }
                            old= pos;
                            pos= xx.getVbrBindCount();
                            if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
                                SNMP_ADAPTOR_LOGGER.logp(Level.FINEST, dbgTbg,
                                    "mbkeResponseMessbge","fbil on element" + pos);
                            }
                            if (pos == old) {
                                // we cbn not go bny further in trying to
                                // reduce the messbge !
                                //
                                throw xx;
                            }
                        }
                    }// end of loop
                } cbtch(SnmpStbtusException xx) {
                    respMsg = null ;
                    if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
                        SNMP_ADAPTOR_LOGGER.logp(Level.FINEST, dbgTbg,
                           "mbkeResponseMessbge", "fbilure when encoding the response messbge", xx);
                    }
                }
                cbtch(SnmpTooBigException xx) {
                    try {
                        respPdu = newTooBigPdu(reqPdu) ;
                        respMsg = (SnmpMessbge)pduFbctory.
                            encodeSnmpPdu(respPdu, pbcket.getDbtb().length) ;
                    }
                    cbtch(SnmpTooBigException xxx) {
                        respMsg = null ;
                        if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
                            SNMP_ADAPTOR_LOGGER.logp(Level.FINEST, dbgTbg,
                               "mbkeResponseMessbge", "'too big' is 'too big' !!!");
                        }
                        bdbptor.incSnmpSilentDrops(1);
                    }
                    cbtch(Exception xxx) {
                        if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
                            SNMP_ADAPTOR_LOGGER.logp(Level.FINEST, dbgTbg,
                               "mbkeResponseMessbge", "Got unexpected exception", xxx);
                        }
                        respMsg = null ;
                    }
                }
                cbtch(Exception xx) {
                    if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
                        SNMP_ADAPTOR_LOGGER.logp(Level.FINEST, dbgTbg,
                           "mbkeResponseMessbge", "Got unexpected exception", xx);
                    }
                    respMsg = null ;
                }
            }
        }
        return respMsg ;
    }

    /**
     * Here we mbke b response pdu from b request pdu.
     * We return null if there is no pdu to reply.
     */
    privbte SnmpPduPbcket mbkeResponsePdu(SnmpPduPbcket reqPdu,
                                          Object userDbtb) {

        SnmpAdbptorServer snmpServer = (SnmpAdbptorServer)bdbptorServer ;
        SnmpPduPbcket respPdu = null ;

        snmpServer.updbteRequestCounters(reqPdu.type) ;
        if (reqPdu.vbrBindList != null)
            snmpServer.updbteVbrCounters(reqPdu.type,
                                         reqPdu.vbrBindList.length) ;

        if (checkPduType(reqPdu)) {
            respPdu = checkAcl(reqPdu) ;
            if (respPdu == null) { // reqPdu is bccepted by ACLs
                if (mibs.size() < 1) {
                    if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINER)) {
                        SNMP_ADAPTOR_LOGGER.logp(Level.FINER, dbgTbg,
                           "mbkeResponsePdu", "Request " + reqPdu.requestId +
                           " received but no MIB registered.");
                    }
                    return mbkeNoMibErrorPdu((SnmpPduRequest)reqPdu, userDbtb);
                }
                switch(reqPdu.type) {
                cbse SnmpPduPbcket.pduGetRequestPdu:
                cbse SnmpPduPbcket.pduGetNextRequestPdu:
                cbse SnmpPduPbcket.pduSetRequestPdu:
                    respPdu = mbkeGetSetResponsePdu((SnmpPduRequest)reqPdu,
                                                    userDbtb) ;
                    brebk ;

                cbse SnmpPduPbcket.pduGetBulkRequestPdu:
                    respPdu = mbkeGetBulkResponsePdu((SnmpPduBulk)reqPdu,
                                                     userDbtb) ;
                    brebk ;
                }
            }
            else { // reqPdu is rejected by ACLs
                // respPdu contbins the error response to be sent.
                // We send this response only if buthResEnbbled is true.
                if (!snmpServer.getAuthRespEnbbled()) { // No response should be sent
                    respPdu = null ;
                }
                if (snmpServer.getAuthTrbpEnbbled()) { // A trbp must be sent
                    try {
                        snmpServer.snmpV1Trbp(SnmpPduTrbp.
                                              trbpAuthenticbtionFbilure, 0,
                                              new SnmpVbrBindList()) ;
                    }
                    cbtch(Exception x) {
                        if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
                            SNMP_ADAPTOR_LOGGER.logp(Level.FINEST, dbgTbg,
                               "mbkeResponsePdu", "Fbilure when sending buthenticbtion trbp", x);
                        }
                    }
                }
            }
        }
        return respPdu ;
    }

    //
    // Generbtes b response pbcket, filling the vblues in the
    // vbrbindlist with one of endOfMibView, noSuchObject, noSuchInstbnce
    // bccording to the vblue of <code>stbtus</code>
    //
    // @pbrbm stbtusTbg should be one of:
    //        <li>SnmpDbtbTypeEnums.errEndOfMibViewTbg</li>
    //        <li>SnmpDbtbTypeEnums.errNoSuchObjectTbg</li>
    //        <li>SnmpDbtbTypeEnums.errNoSuchInstbnceTbg</li>
    //
    SnmpPduPbcket mbkeErrorVbrbindPdu(SnmpPduPbcket req, int stbtusTbg) {

        finbl SnmpVbrBind[] vblist = req.vbrBindList;
        finbl int length = vblist.length;

        switch (stbtusTbg) {
        cbse SnmpDbtbTypeEnums.errEndOfMibViewTbg:
            for (int i=0 ; i<length ; i++)
                vblist[i].vblue = SnmpVbrBind.endOfMibView;
            brebk;
        cbse SnmpDbtbTypeEnums.errNoSuchObjectTbg:
            for (int i=0 ; i<length ; i++)
                vblist[i].vblue = SnmpVbrBind.noSuchObject;
            brebk;
        cbse SnmpDbtbTypeEnums.errNoSuchInstbnceTbg:
            for (int i=0 ; i<length ; i++)
                vblist[i].vblue = SnmpVbrBind.noSuchInstbnce;
            brebk;
        defbult:
            return newErrorResponsePdu(req,snmpRspGenErr,1);
        }
        return newVblidResponsePdu(req,vblist);
    }

    // Generbtes bn bppropribte response when no mib is registered in
    // the bdbptor.
    //
    // <li>If the version is V1:</li>
    // <ul><li>Generbtes b NoSuchNbme error V1 response PDU</li></ul>
    // <li>If the version is V2:</li>
    // <ul><li>If the request is b GET, fills the vbrbind list with
    //         NoSuchObject's</li>
    //     <li>If the request is b GET-NEXT/GET-BULK, fills the vbrbind
    //         list with EndOfMibView's</li>
    //     <li>If the request is b SET, generbtes b NoAccess error V2
    //          response PDU</li>
    // </ul>
    //
    //
    SnmpPduPbcket mbkeNoMibErrorPdu(SnmpPduRequest req, Object userDbtb) {
        // There is no bgent registered
        //
        if (req.version == SnmpDefinitions.snmpVersionOne) {
            // Version 1: => NoSuchNbme
            return
                newErrorResponsePdu(req,snmpRspNoSuchNbme,1);
        } else if (req.version == SnmpDefinitions.snmpVersionTwo) {
            // Version 2: => depends on PDU type
            switch (req.type) {
            cbse pduSetRequestPdu :
            cbse pduWblkRequest :
                // SET request => NoAccess
                return
                    newErrorResponsePdu(req,snmpRspNoAccess,1);
            cbse pduGetRequestPdu :
                // GET request => NoSuchObject
                return
                    mbkeErrorVbrbindPdu(req,SnmpDbtbTypeEnums.
                                        errNoSuchObjectTbg);
            cbse pduGetNextRequestPdu :
            cbse pduGetBulkRequestPdu :
                // GET-NEXT or GET-BULK => EndOfMibView
                return
                    mbkeErrorVbrbindPdu(req,SnmpDbtbTypeEnums.
                                        errEndOfMibViewTbg);
            defbult:
            }
        }
        // Something wrong here: => snmpRspGenErr
        return newErrorResponsePdu(req,snmpRspGenErr,1);
    }

    /**
     * Here we mbke the response pdu from b get/set request pdu.
     * At this level, the result is never null.
     */
    privbte SnmpPduPbcket mbkeGetSetResponsePdu(SnmpPduRequest req,
                                                Object userDbtb) {

        // Crebte the trhebd group specific for hbndling sub-requests
        // bssocibted to the current request. Use the invoke id
        //
        // Nice ideb to use b threbd group on b request bbsis.
        // However the impbct on performbnce is terrible !
        // theGroup= new ThrebdGroup(threbd.getThrebdGroup(),
        //                "request " + String.vblueOf(req.requestId));

        // Let's build the vbrBindList for the response pdu
        //

        if (req.vbrBindList == null) {
            // Good ! Let's mbke b full response pdu.
            //
            return newVblidResponsePdu(req, null) ;
        }

        // First we need to split the request into subrequests
        //
        splitRequest(req);
        int nbSubRequest= subs.size();
        if (nbSubRequest == 1)
            return turboProcessingGetSet(req,userDbtb);


        // Execute bll the subrequests resulting from the split of the
        // vbrbind list.
        //
        SnmpPduPbcket result= executeSubRequest(req,userDbtb);
        if (result != null)
            // It mebns thbt bn error occurred. The error is blrebdy
            // formbtted by the executeSubRequest
            // method.
            return result;

        // So fbr so good. So we need to concbtenbte bll the bnswers.
        //
        if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINER)) {
            SNMP_ADAPTOR_LOGGER.logp(Level.FINER, dbgTbg,
               "mbkeGetSetResponsePdu",
               "Build the unified response for request " + req.requestId);
        }
        return mergeResponses(req);
    }

    /**
     * The method runs bll the sub-requests bssocibted to the current
     * instbnce of SnmpRequestHbndler.
     */
    privbte SnmpPduPbcket executeSubRequest(SnmpPduPbcket req,
                                            Object userDbtb) {

        int errorStbtus = SnmpDefinitions.snmpRspNoError ;

        int i;
        // If it's b set request, we must first check bny vbrBind
        //
        if (req.type == pduSetRequestPdu) {

            i=0;
            for(Enumerbtion<SnmpSubRequestHbndler> e= subs.elements(); e.hbsMoreElements() ; i++) {
                // Indicbte to the sub request thbt b check must be invoked ...
                // OK we should hbve defined out own tbg for thbt !
                //
                SnmpSubRequestHbndler sub= e.nextElement();
                sub.setUserDbtb(userDbtb);
                sub.type= pduWblkRequest;

                sub.run();

                sub.type= pduSetRequestPdu;

                if (sub.getErrorStbtus() != SnmpDefinitions.snmpRspNoError) {
                    // No point to go bny further.
                    //
                    if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
                        SNMP_ADAPTOR_LOGGER.logp(Level.FINEST, dbgTbg,
                           "executeSubRequest", "bn error occurs");
                    }

                    return newErrorResponsePdu(req, errorStbtus,
                                               sub.getErrorIndex() + 1) ;
                }
            }
        }// end processing check operbtion for b set PDU.

        // Let's stbrt the sub-requests.
        //
        i=0;
        for(Enumerbtion<SnmpSubRequestHbndler> e= subs.elements(); e.hbsMoreElements() ;i++) {
            SnmpSubRequestHbndler sub= e.nextElement();
        /* NPCTE fix for bugId 4492741, esc 0, 16-August 2001 */
            sub.setUserDbtb(userDbtb);
        /* end of NPCTE fix for bugId 4492741 */

            sub.run();

            if (sub.getErrorStbtus() != SnmpDefinitions.snmpRspNoError) {
                // No point to go bny further.
                //
                if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
                    SNMP_ADAPTOR_LOGGER.logp(Level.FINEST, dbgTbg,
                       "executeSubRequest", "bn error occurs");
                }

                return newErrorResponsePdu(req, errorStbtus,
                                           sub.getErrorIndex() + 1) ;
            }
        }

        // everything is ok
        //
        return null;
    }

    /**
     * Optimize when there is only one sub request
     */
    privbte SnmpPduPbcket turboProcessingGetSet(SnmpPduRequest req,
                                                Object userDbtb) {

        int errorStbtus;
        SnmpSubRequestHbndler sub = subs.elements().nextElement();
        sub.setUserDbtb(userDbtb);

        // Indicbte to the sub request thbt b check must be invoked ...
        // OK we should hbve defined out own tbg for thbt !
        //
        if (req.type == SnmpDefinitions.pduSetRequestPdu) {
            sub.type= pduWblkRequest;
            sub.run();
            sub.type= pduSetRequestPdu;

            // Check the error stbtus.
            //
            errorStbtus= sub.getErrorStbtus();
            if (errorStbtus != SnmpDefinitions.snmpRspNoError) {
                // No point to go bny further.
                //
                return newErrorResponsePdu(req, errorStbtus,
                                           sub.getErrorIndex() + 1) ;
            }
        }

        // process the operbtion
        //

        sub.run();
        errorStbtus= sub.getErrorStbtus();
        if (errorStbtus != SnmpDefinitions.snmpRspNoError) {
            // No point to go bny further.
            //
            if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
                SNMP_ADAPTOR_LOGGER.logp(Level.FINEST, dbgTbg,
                   "turboProcessingGetSet", "bn error occurs");
            }
            int reblIndex= sub.getErrorIndex() + 1;
            return newErrorResponsePdu(req, errorStbtus, reblIndex) ;
        }

        // So fbr so good. So we need to concbtenbte bll the bnswers.
        //

        if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINER)) {
            SNMP_ADAPTOR_LOGGER.logp(Level.FINER, dbgTbg,
               "turboProcessingGetSet",  "build the unified response for request "
                + req.requestId);
        }
        return mergeResponses(req);
    }

    /**
     * Here we mbke the response pdu for b bulk request.
     * At this level, the result is never null.
     */
    privbte SnmpPduPbcket mbkeGetBulkResponsePdu(SnmpPduBulk req,
                                                 Object userDbtb) {

        SnmpVbrBind[] respVbrBindList;

        // RFC 1905, Section 4.2.3, p14
        int L = req.vbrBindList.length ;
        int N = Mbth.mbx(Mbth.min(req.nonRepebters, L), 0) ;
        int M = Mbth.mbx(req.mbxRepetitions, 0) ;
        int R = L - N ;

        if (req.vbrBindList == null) {
            // Good ! Let's mbke b full response pdu.
            //
            return newVblidResponsePdu(req, null) ;
        }

        // Split the request into subrequests.
        //
        splitBulkRequest(req, N, M, R);
        SnmpPduPbcket result= executeSubRequest(req,userDbtb);
        if (result != null)
            return result;

        respVbrBindList= mergeBulkResponses(N + (M * R));

        // Now we remove useless trbiling endOfMibView.
        //
        int m2 ; // respVbrBindList[m2] item bnd next bre going to be removed
        int t = respVbrBindList.length ;
        while ((t > N) && (respVbrBindList[t-1].
                           vblue.equbls(SnmpVbrBind.endOfMibView))) {
            t-- ;
        }
        if (t == N)
            m2 = N + R ;
        else
            m2 = N + ((t -1 -N) / R + 2) * R ; // Trivibl, of course...
        if (m2 < respVbrBindList.length) {
            SnmpVbrBind[] truncbtedList = new SnmpVbrBind[m2] ;
            for (int i = 0 ; i < m2 ; i++) {
                truncbtedList[i] = respVbrBindList[i] ;
            }
            respVbrBindList = truncbtedList ;
        }

        // Good ! Let's mbke b full response pdu.
        //
        return newVblidResponsePdu(req, respVbrBindList) ;
    }

    /**
     * Check the type of the pdu: only the get/set/bulk request
     * bre bccepted.
     */
    privbte boolebn checkPduType(SnmpPduPbcket pdu) {

        boolebn result;

        switch(pdu.type) {

        cbse SnmpDefinitions.pduGetRequestPdu:
        cbse SnmpDefinitions.pduGetNextRequestPdu:
        cbse SnmpDefinitions.pduSetRequestPdu:
        cbse SnmpDefinitions.pduGetBulkRequestPdu:
            result = true ;
            brebk;

        defbult:
            if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
                SNMP_ADAPTOR_LOGGER.logp(Level.FINEST, dbgTbg,
                   "checkPduType", "cbnnot respond to this kind of PDU");
            }
            result = fblse ;
            brebk;
        }

        return result ;
    }

    /**
     * Check if the specified pdu is conform to the ACL.
     * This method returns null if the pdu is ok. If not, it returns
     * the response pdu to be replied.
     */
    privbte SnmpPduPbcket checkAcl(SnmpPduPbcket pdu) {
        SnmpPduPbcket response = null ;
        String community = new String(pdu.community) ;

        // We check the pdu type bnd crebte bn error response if
        // the check fbiled.
        //
        if (ipbcl != null) {
            if (pdu.type == SnmpDefinitions.pduSetRequestPdu) {
                if (!ipbcl.checkWritePermission(pdu.bddress, community)) {
                    if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINER)) {
                        SNMP_ADAPTOR_LOGGER.logp(Level.FINER, dbgTbg,
                           "checkAcl", "sender is " + pdu.bddress +
                              " with " + community +". Sender hbs no write permission");
                    }
                    int err = SnmpSubRequestHbndler.
                        mbpErrorStbtus(SnmpDefinitions.
                                       snmpRspAuthorizbtionError,
                                       pdu.version, pdu.type);
                    response = newErrorResponsePdu(pdu, err, 0) ;
                }
                else {
                    if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINER)) {
                        SNMP_ADAPTOR_LOGGER.logp(Level.FINER, dbgTbg,
                           "checkAcl", "sender is " + pdu.bddress +
                              " with " + community +". Sender hbs write permission");
                    }
                }
            }
            else {
                if (!ipbcl.checkRebdPermission(pdu.bddress, community)) {
                    if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINER)) {
                        SNMP_ADAPTOR_LOGGER.logp(Level.FINER, dbgTbg,
                           "checkAcl", "sender is " + pdu.bddress +
                              " with " + community +". Sender hbs no rebd permission");
                    }
                    int err = SnmpSubRequestHbndler.
                        mbpErrorStbtus(SnmpDefinitions.
                                       snmpRspAuthorizbtionError,
                                       pdu.version, pdu.type);
                    response = newErrorResponsePdu(pdu,
                                                   err,
                                                   0);
                    SnmpAdbptorServer snmpServer =
                        (SnmpAdbptorServer)bdbptorServer;
                    snmpServer.updbteErrorCounters(SnmpDefinitions.
                                                   snmpRspNoSuchNbme);
                }
                else {
                    if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINER)) {
                        SNMP_ADAPTOR_LOGGER.logp(Level.FINER, dbgTbg,
                           "checkAcl", "sender is " + pdu.bddress +
                              " with " + community +". Sender hbs rebd permission");
                    }
                }
            }
        }

        // If the response is not null, this mebns the pdu is rejected.
        // So let's updbte the stbtistics.
        //
        if (response != null) {
            SnmpAdbptorServer snmpServer = (SnmpAdbptorServer)bdbptorServer ;
            snmpServer.incSnmpInBbdCommunityUses(1) ;
            if (ipbcl.checkCommunity(community) == fblse)
                snmpServer.incSnmpInBbdCommunityNbmes(1) ;
        }

        return response ;
    }

    /**
     * Mbke b response pdu with the specified error stbtus bnd index.
     * NOTE: the response pdu shbre its vbrBindList with the request pdu.
     */
    privbte SnmpPduRequest newVblidResponsePdu(SnmpPduPbcket reqPdu,
                                               SnmpVbrBind[] vbrBindList) {
        SnmpPduRequest result = new SnmpPduRequest() ;

        result.bddress = reqPdu.bddress ;
        result.port = reqPdu.port ;
        result.version = reqPdu.version ;
        result.community = reqPdu.community ;
        result.type = SnmpPduRequest.pduGetResponsePdu ;
        result.requestId = reqPdu.requestId ;
        result.errorStbtus = SnmpDefinitions.snmpRspNoError ;
        result.errorIndex = 0 ;
        result.vbrBindList = vbrBindList ;

        ((SnmpAdbptorServer)bdbptorServer).
            updbteErrorCounters(result.errorStbtus) ;

        return result ;
    }

    /**
     * Mbke b response pdu with the specified error stbtus bnd index.
     * NOTE: the response pdu shbre its vbrBindList with the request pdu.
     */
    privbte SnmpPduRequest newErrorResponsePdu(SnmpPduPbcket req,int s,int i) {
        SnmpPduRequest result = newVblidResponsePdu(req, null) ;
        result.errorStbtus = s ;
        result.errorIndex = i ;
        result.vbrBindList = req.vbrBindList ;

        ((SnmpAdbptorServer)bdbptorServer).
            updbteErrorCounters(result.errorStbtus) ;

        return result ;
    }

    privbte SnmpMessbge newTooBigMessbge(SnmpMessbge reqMsg)
        throws SnmpTooBigException {
        SnmpMessbge result = null ;
        SnmpPduPbcket reqPdu;

        try {
            reqPdu = (SnmpPduPbcket)pduFbctory.decodeSnmpPdu(reqMsg) ;
            if (reqPdu != null) {
                SnmpPduPbcket respPdu = newTooBigPdu(reqPdu) ;
                result = (SnmpMessbge)pduFbctory.
                    encodeSnmpPdu(respPdu, pbcket.getDbtb().length) ;
            }
        }
        cbtch(SnmpStbtusException x) {
            // This should not occur becbuse decodeIncomingRequest hbs normblly
            // been successfully cblled before.
            if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
                SNMP_ADAPTOR_LOGGER.logp(Level.FINEST, dbgTbg,
                   "newTooBigMessbge", "Internbl error", x);
            }
            throw new InternblError(x) ;
        }

        return result ;
    }

    privbte SnmpPduPbcket newTooBigPdu(SnmpPduPbcket req) {
        SnmpPduRequest result =
            newErrorResponsePdu(req, SnmpDefinitions.snmpRspTooBig, 0) ;
        result.vbrBindList = null ;
        return result ;
    }

    privbte SnmpPduPbcket reduceResponsePdu(SnmpPduPbcket req,
                                            SnmpPduPbcket resp,
                                            int bcceptedVbCount)
        throws SnmpTooBigException {

        // Reduction cbn be bttempted only on bulk response
        //
        if (req.type != SnmpPduPbcket.pduGetBulkRequestPdu) {
            if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
                SNMP_ADAPTOR_LOGGER.logp(Level.FINEST, dbgTbg,
                   "reduceResponsePdu", "cbnnot remove bnything");
            }
            throw new SnmpTooBigException(bcceptedVbCount) ;
        }

        // We're going to reduce the vbrbind list.
        // First determine which items should be removed.
        // Next duplicbte bnd replbce the existing list by the reduced one.
        //
        // bcceptedVbCount is the number of vbrbind which hbve been
        // successfully encoded before rebching bufferSize:
        //   * when it is >= 2, we split the vbrbindlist bt this
        //     position (-1 to be sbfe),
        //   * when it is 1, we only put one (big?) item in the vbrbindlist
        //   * when it is 0 (in fbct, bcceptedVbCount is not bvbilbble),
        //     we split the vbrbindlist by 2.
        //
        int vbCount;
        if (bcceptedVbCount >= 3)
            vbCount = Mbth.min(bcceptedVbCount - 1, resp.vbrBindList.length) ;
        else if (bcceptedVbCount == 1)
            vbCount = 1 ;
        else // bcceptedCount == 0 ie it is unknown
            vbCount = resp.vbrBindList.length / 2 ;

        if (vbCount < 1) {
            if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
                SNMP_ADAPTOR_LOGGER.logp(Level.FINEST, dbgTbg,
                   "reduceResponsePdu", "cbnnot remove bnything");
            }
            throw new SnmpTooBigException(bcceptedVbCount) ;
        }
        else {
            SnmpVbrBind[] newVbList = new SnmpVbrBind[vbCount] ;
            for (int i = 0 ; i < vbCount ; i++) {
                newVbList[i] = resp.vbrBindList[i] ;
            }
            if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
                SNMP_ADAPTOR_LOGGER.logp(Level.FINEST, dbgTbg,
                   "reduceResponsePdu", (resp.vbrBindList.length - newVbList.length) +
                    " items hbve been removed");
            }
            resp.vbrBindList = newVbList ;
        }

        return resp ;
    }

    /**
     * The method tbkes the incoming requests bnd split it into subrequests.
     */
    privbte void splitRequest(SnmpPduRequest req) {

        int nbAgents= mibs.size();
        SnmpMibAgent bgent = mibs.firstElement();
        if (nbAgents == 1) {
            // Tbke bll the oids contbined in the request bnd
            //
            subs.put(bgent, new SnmpSubRequestHbndler(bgent, req, true));
            return;
        }

        // For the get next operbtion we bre going to send the vbrbind list
        // to bll bgents
        //
        if (req.type == pduGetNextRequestPdu) {
            for(Enumerbtion<SnmpMibAgent> e= mibs.elements(); e.hbsMoreElements(); ) {
                finbl SnmpMibAgent bg= e.nextElement();
                subs.put(bg, new SnmpSubNextRequestHbndler(bdbptor, bg, req));
            }
            return;
        }

        int nbReqs= req.vbrBindList.length;
        SnmpVbrBind[] vbrs= req.vbrBindList;
        SnmpSubRequestHbndler sub;
        for(int i=0; i < nbReqs; i++) {
            bgent= root.getAgentMib(vbrs[i].oid);
            sub= subs.get(bgent);
            if (sub == null) {
                // We need to crebte the sub request hbndler bnd updbte
                // the hbshtbble
                //
                sub= new SnmpSubRequestHbndler(bgent, req);
                subs.put(bgent, sub);
            }

            // Updbte the trbnslbtion tbble within the subrequest
            //
            sub.updbteRequest(vbrs[i], i);
        }
    }

    /**
     * The method tbkes the incoming get bulk requests bnd split it into
     * subrequests.
     */
    privbte void splitBulkRequest(SnmpPduBulk req,
                                  int nonRepebters,
                                  int mbxRepetitions,
                                  int R) {
        // Send the getBulk to bll bgents
        //
        for(Enumerbtion<SnmpMibAgent> e= mibs.elements(); e.hbsMoreElements(); ) {
            finbl SnmpMibAgent bgent = e.nextElement();

            if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINER)) {
                SNMP_ADAPTOR_LOGGER.logp(Level.FINER, dbgTbg,
                   "splitBulkRequest", "Crebte b sub with : " + bgent + " " + nonRepebters
                   + " " + mbxRepetitions + " " + R);
            }

            subs.put(bgent,
                     new SnmpSubBulkRequestHbndler(bdbptor,
                                                   bgent,
                                                   req,
                                                   nonRepebters,
                                                   mbxRepetitions,
                                                   R));
        }
    }

    privbte SnmpPduPbcket mergeResponses(SnmpPduRequest req) {

        if (req.type == pduGetNextRequestPdu) {
            return mergeNextResponses(req);
        }

        SnmpVbrBind[] result= req.vbrBindList;

        // Go through the list of subrequests bnd concbtenbte.
        // Hopefully, by now bll the sub-requests should be finished
        //
        for(Enumerbtion<SnmpSubRequestHbndler> e= subs.elements(); e.hbsMoreElements();) {
            SnmpSubRequestHbndler sub= e.nextElement();
            sub.updbteResult(result);
        }
        return newVblidResponsePdu(req,result);
    }

    privbte SnmpPduPbcket mergeNextResponses(SnmpPduRequest req) {
        int mbx= req.vbrBindList.length;
        SnmpVbrBind[] result= new SnmpVbrBind[mbx];

        // Go through the list of subrequests bnd concbtenbte.
        // Hopefully, by now bll the sub-requests should be finished
        //
        for(Enumerbtion<SnmpSubRequestHbndler> e= subs.elements(); e.hbsMoreElements();) {
            SnmpSubRequestHbndler sub= e.nextElement();
            sub.updbteResult(result);
        }

        if (req.version == snmpVersionTwo) {
            return newVblidResponsePdu(req,result);
        }

        // In v1 mbke sure there is no endOfMibView ...
        //
        for(int i=0; i < mbx; i++) {
            SnmpVblue vbl= result[i].vblue;
            if (vbl == SnmpVbrBind.endOfMibView)
                return newErrorResponsePdu(req,
                                   SnmpDefinitions.snmpRspNoSuchNbme, i+1);
        }

        // So fbr so good ...
        //
        return newVblidResponsePdu(req,result);
    }

    privbte SnmpVbrBind[] mergeBulkResponses(int size) {
        // Let's bllocbte the brrby for storing the result
        //
        SnmpVbrBind[] result= new SnmpVbrBind[size];
        for(int i= size-1; i >=0; --i) {
            result[i]= new SnmpVbrBind();
            result[i].vblue= SnmpVbrBind.endOfMibView;
        }

        // Go through the list of subrequests bnd concbtenbte.
        // Hopefully, by now bll the sub-requests should be finished
        //
        for(Enumerbtion<SnmpSubRequestHbndler> e= subs.elements(); e.hbsMoreElements();) {
            SnmpSubRequestHbndler sub= e.nextElement();
            sub.updbteResult(result);
        }

        return result;
    }

    @Override
    protected String mbkeDebugTbg() {
        return "SnmpRequestHbndler[" + bdbptorServer.getProtocol() + ":" +
            bdbptorServer.getPort() + "]";
    }

    @Override
    Threbd crebteThrebd(Runnbble r) {
        return null;
    }

    stbtic finbl privbte String InterruptSysCbllMsg =
        "Interrupted system cbll";
}
