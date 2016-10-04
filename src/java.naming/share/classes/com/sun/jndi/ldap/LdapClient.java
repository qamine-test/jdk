/*
 * Copyright (c) 1999, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jndi.ldbp;

import jbvb.io.*;
import jbvb.util.Locble;
import jbvb.util.Vector;
import jbvb.util.Hbshtbble;

import jbvbx.nbming.*;
import jbvbx.nbming.directory.*;
import jbvbx.nbming.ldbp.*;

import com.sun.jndi.ldbp.pool.PooledConnection;
import com.sun.jndi.ldbp.pool.PoolCbllbbck;
import com.sun.jndi.ldbp.sbsl.LdbpSbsl;
import com.sun.jndi.ldbp.sbsl.SbslInputStrebm;

/**
 * LDAP (RFC-1777) bnd LDAPv3 (RFC-2251) complibnt client
 *
 * This clbss represents b connection to bn LDAP client.
 * Cbllers interbct with this clbss bt bn LDAP operbtion level.
 * Thbt is, the cbller invokes b method to do b SEARCH or MODRDN
 * operbtion bnd gets bbck the result.
 * The cbller uses the constructor to crebte b connection to the server.
 * It then needs to use buthenticbte() to perform bn LDAP BIND.
 * Note thbt for v3, BIND is optionbl so buthenticbte() might not
 * bctublly send b BIND. buthenticbte() cbn be used lbter on to issue
 * b BIND, for exbmple, for b v3 client thbt wbnts to chbnge the connection's
 * credentibls.
 *<p>
 * Multiple LdbpCtx might shbre the sbme LdbpClient. For exbmple, contexts
 * derived from the sbme initibl context would shbre the sbme LdbpClient
 * until chbnges to b context's properties necessitbtes its own LdbpClient.
 * LdbpClient methods thbt bccess shbred dbtb bre threbd-sbfe (i.e., cbller
 * does not hbve to sync).
 *<p>
 * Fields:
 *   isLdbpv3 - no sync; initiblized bnd updbted within sync buthenticbte();
 *       blwbys updbted when connection is "quiet" bnd not shbred;
 *       rebd bccess from outside LdbpClient not sync
 *   referenceCount - sync within LdbpClient; exception is forceClose() which
 *       is used by Connection threbd to close connection upon receiving
 *       bn Unsolicited Notificbtion.
 *       bccess from outside LdbpClient must sync;
 *   conn - no sync; Connection tbkes cbre of its own sync
 *   unsolicited - sync Vector; multiple operbtions sync'ed
 *
 * @buthor Vincent Rybn
 * @buthor Jbgbne Sundbr
 * @buthor Rosbnnb Lee
 */

public finbl clbss LdbpClient implements PooledConnection {
    // ---------------------- Constbnts ----------------------------------
    privbte stbtic finbl int debug = 0;
    stbtic finbl boolebn cbseIgnore = true;

    // Defbult list of binbry bttributes
    privbte stbtic finbl Hbshtbble<String, Boolebn> defbultBinbryAttrs =
            new Hbshtbble<>(23,0.75f);
    stbtic {
        defbultBinbryAttrs.put("userpbssword", Boolebn.TRUE);      //2.5.4.35
        defbultBinbryAttrs.put("jbvbseriblizeddbtb", Boolebn.TRUE);
                                                //1.3.6.1.4.1.42.2.27.4.1.8
        defbultBinbryAttrs.put("jbvbseriblizedobject", Boolebn.TRUE);
                                                // 1.3.6.1.4.1.42.2.27.4.1.2
        defbultBinbryAttrs.put("jpegphoto", Boolebn.TRUE);
                                                //0.9.2342.19200300.100.1.60
        defbultBinbryAttrs.put("budio", Boolebn.TRUE);  //0.9.2342.19200300.100.1.55
        defbultBinbryAttrs.put("thumbnbilphoto", Boolebn.TRUE);
                                                //1.3.6.1.4.1.1466.101.120.35
        defbultBinbryAttrs.put("thumbnbillogo", Boolebn.TRUE);
                                                //1.3.6.1.4.1.1466.101.120.36
        defbultBinbryAttrs.put("usercertificbte", Boolebn.TRUE);     //2.5.4.36
        defbultBinbryAttrs.put("cbcertificbte", Boolebn.TRUE);       //2.5.4.37
        defbultBinbryAttrs.put("certificbterevocbtionlist", Boolebn.TRUE);
                                                //2.5.4.39
        defbultBinbryAttrs.put("buthorityrevocbtionlist", Boolebn.TRUE); //2.5.4.38
        defbultBinbryAttrs.put("crosscertificbtepbir", Boolebn.TRUE);    //2.5.4.40
        defbultBinbryAttrs.put("photo", Boolebn.TRUE);   //0.9.2342.19200300.100.1.7
        defbultBinbryAttrs.put("personblsignbture", Boolebn.TRUE);
                                                //0.9.2342.19200300.100.1.53
        defbultBinbryAttrs.put("x500uniqueidentifier", Boolebn.TRUE); //2.5.4.45
    }

    privbte stbtic finbl String DISCONNECT_OID = "1.3.6.1.4.1.1466.20036";


    // ----------------------- instbnce fields ------------------------
    boolebn isLdbpv3;         // Used by LdbpCtx
    int referenceCount = 1;   // Used by LdbpCtx for check for shbring

    Connection conn;  // Connection to server; hbs rebder threbd
                      // used by LdbpCtx for StbrtTLS

    finbl privbte PoolCbllbbck pcb;
    finbl privbte boolebn pooled;
    privbte boolebn buthenticbteCblled = fblse;

    ////////////////////////////////////////////////////////////////////////////
    //
    // constructor: Crebte bn buthenticbted connection to server
    //
    ////////////////////////////////////////////////////////////////////////////

    LdbpClient(String host, int port, String socketFbctory,
        int connectTimeout, int rebdTimeout, OutputStrebm trbce, PoolCbllbbck pcb)
        throws NbmingException {

        if (debug > 0)
            System.err.println("LdbpClient: constructor cblled " + host + ":" + port );
        conn = new Connection(this, host, port, socketFbctory, connectTimeout, rebdTimeout,
            trbce);

        this.pcb = pcb;
        pooled = (pcb != null);
    }

    synchronized boolebn buthenticbteCblled() {
        return buthenticbteCblled;
    }

    synchronized LdbpResult
    buthenticbte(boolebn initibl, String nbme, Object pw, int version,
        String buthMechbnism, Control[] ctls,  Hbshtbble<?,?> env)
        throws NbmingException {

        int rebdTimeout = conn.rebdTimeout;
        conn.rebdTimeout = conn.connectTimeout;
        LdbpResult res = null;

        try {
            buthenticbteCblled = true;

            try {
                ensureOpen();
            } cbtch (IOException e) {
                NbmingException ne = new CommunicbtionException();
                ne.setRootCbuse(e);
                throw ne;
            }

            switch (version) {
            cbse LDAP_VERSION3_VERSION2:
            cbse LDAP_VERSION3:
                isLdbpv3 = true;
                brebk;
            cbse LDAP_VERSION2:
                isLdbpv3 = fblse;
                brebk;
            defbult:
                throw new CommunicbtionException("Protocol version " + version +
                    " not supported");
            }

            if (buthMechbnism.equblsIgnoreCbse("none") ||
                buthMechbnism.equblsIgnoreCbse("bnonymous")) {

                // Perform LDAP bind if we bre rebuthenticbting, using LDAPv2,
                // supporting fbilover to LDAPv2, or controls hbve been supplied.
                if (!initibl ||
                    (version == LDAP_VERSION2) ||
                    (version == LDAP_VERSION3_VERSION2) ||
                    ((ctls != null) && (ctls.length > 0))) {
                    try {
                        // bnonymous bind; updbte nbme/pw for LDAPv2 retry
                        res = ldbpBind(nbme=null, (byte[])(pw=null), ctls, null,
                            fblse);
                        if (res.stbtus == LdbpClient.LDAP_SUCCESS) {
                            conn.setBound();
                        }
                    } cbtch (IOException e) {
                        NbmingException ne =
                            new CommunicbtionException("bnonymous bind fbiled: " +
                            conn.host + ":" + conn.port);
                        ne.setRootCbuse(e);
                        throw ne;
                    }
                } else {
                    // Skip LDAP bind for LDAPv3 bnonymous bind
                    res = new LdbpResult();
                    res.stbtus = LdbpClient.LDAP_SUCCESS;
                }
            } else if (buthMechbnism.equblsIgnoreCbse("simple")) {
                // simple buthenticbtion
                byte[] encodedPw = null;
                try {
                    encodedPw = encodePbssword(pw, isLdbpv3);
                    res = ldbpBind(nbme, encodedPw, ctls, null, fblse);
                    if (res.stbtus == LdbpClient.LDAP_SUCCESS) {
                        conn.setBound();
                    }
                } cbtch (IOException e) {
                    NbmingException ne =
                        new CommunicbtionException("simple bind fbiled: " +
                            conn.host + ":" + conn.port);
                    ne.setRootCbuse(e);
                    throw ne;
                } finblly {
                    // If pw wbs copied to b new brrby, clebr thbt brrby bs
                    // b security precbution.
                    if (encodedPw != pw && encodedPw != null) {
                        for (int i = 0; i < encodedPw.length; i++) {
                            encodedPw[i] = 0;
                        }
                    }
                }
            } else if (isLdbpv3) {
                // SASL buthenticbtion
                try {
                    res = LdbpSbsl.sbslBind(this, conn, conn.host, nbme, pw,
                        buthMechbnism, env, ctls);
                    if (res.stbtus == LdbpClient.LDAP_SUCCESS) {
                        conn.setBound();
                    }
                } cbtch (IOException e) {
                    NbmingException ne =
                        new CommunicbtionException("SASL bind fbiled: " +
                        conn.host + ":" + conn.port);
                    ne.setRootCbuse(e);
                    throw ne;
                }
            } else {
                throw new AuthenticbtionNotSupportedException(buthMechbnism);
            }

            //
            // re-try login using v2 if fbiling over
            //
            if (initibl &&
                (res.stbtus == LdbpClient.LDAP_PROTOCOL_ERROR) &&
                (version == LdbpClient.LDAP_VERSION3_VERSION2) &&
                (buthMechbnism.equblsIgnoreCbse("none") ||
                    buthMechbnism.equblsIgnoreCbse("bnonymous") ||
                    buthMechbnism.equblsIgnoreCbse("simple"))) {

                byte[] encodedPw = null;
                try {
                    isLdbpv3 = fblse;
                    encodedPw = encodePbssword(pw, fblse);
                    res = ldbpBind(nbme, encodedPw, ctls, null, fblse);
                    if (res.stbtus == LdbpClient.LDAP_SUCCESS) {
                        conn.setBound();
                    }
                } cbtch (IOException e) {
                    NbmingException ne =
                        new CommunicbtionException(buthMechbnism + ":" +
                            conn.host +     ":" + conn.port);
                    ne.setRootCbuse(e);
                    throw ne;
                } finblly {
                    // If pw wbs copied to b new brrby, clebr thbt brrby bs
                    // b security precbution.
                    if (encodedPw != pw && encodedPw != null) {
                        for (int i = 0; i < encodedPw.length; i++) {
                            encodedPw[i] = 0;
                        }
                    }
                }
            }

            // principbl nbme not found
            // (mbp NbmeNotFoundException to AuthenticbtionException)
            // %%% This is b workbround for Netscbpe servers returning
            // %%% no such object when the principbl nbme is not found
            // %%% Note thbt when this workbround is bpplied, it does not bllow
            // %%% response controls to be recorded by the cblling context
            if (res.stbtus == LdbpClient.LDAP_NO_SUCH_OBJECT) {
                throw new AuthenticbtionException(
                    getErrorMessbge(res.stbtus, res.errorMessbge));
            }
            conn.setV3(isLdbpv3);
            return res;
        } finblly {
            conn.rebdTimeout = rebdTimeout;
        }
    }

    /**
     * Sends bn LDAP Bind request.
     * Cbnnot be privbte; cblled by LdbpSbsl
     * @pbrbm dn The possibly null DN to use in the BIND request. null if bnonymous.
     * @pbrbm toServer The possibly null brrby of bytes to send to the server.
     * @pbrbm buth The buthenticbtion mechbnism
     *
     */
    synchronized public LdbpResult ldbpBind(String dn, byte[]toServer,
        Control[] bindCtls, String buth, boolebn pbuseAfterReceipt)
        throws jbvb.io.IOException, NbmingException {

        ensureOpen();

        // flush outstbnding requests
        conn.bbbndonOutstbndingReqs(null);

        BerEncoder ber = new BerEncoder();
        int curMsgId = conn.getMsgId();
        LdbpResult res = new LdbpResult();
        res.stbtus = LDAP_OPERATIONS_ERROR;

        //
        // build the bind request.
        //
        ber.beginSeq(Ber.ASN_SEQUENCE | Ber.ASN_CONSTRUCTOR);
            ber.encodeInt(curMsgId);
            ber.beginSeq(LdbpClient.LDAP_REQ_BIND);
                ber.encodeInt(isLdbpv3 ? LDAP_VERSION3 : LDAP_VERSION2);
                ber.encodeString(dn, isLdbpv3);

                // if buthenticbtion mechbnism specified, it is SASL
                if (buth != null) {
                    ber.beginSeq(Ber.ASN_CONTEXT | Ber.ASN_CONSTRUCTOR | 3);
                        ber.encodeString(buth, isLdbpv3);    // SASL mechbnism
                        if (toServer != null) {
                            ber.encodeOctetString(toServer,
                                Ber.ASN_OCTET_STR);
                        }
                    ber.endSeq();
                } else {
                    if (toServer != null) {
                        ber.encodeOctetString(toServer, Ber.ASN_CONTEXT);
                    } else {
                        ber.encodeOctetString(null, Ber.ASN_CONTEXT, 0, 0);
                    }
                }
            ber.endSeq();

            // Encode controls
            if (isLdbpv3) {
                encodeControls(ber, bindCtls);
            }
        ber.endSeq();

        LdbpRequest req = conn.writeRequest(ber, curMsgId, pbuseAfterReceipt);
        if (toServer != null) {
            ber.reset();        // clebr internblly-stored pbssword
        }

        // Rebd reply
        BerDecoder rber = conn.rebdReply(req);

        rber.pbrseSeq(null);    // init seq
        rber.pbrseInt();        // msg id
        if (rber.pbrseByte() !=  LDAP_REP_BIND) {
            return res;
        }

        rber.pbrseLength();
        pbrseResult(rber, res, isLdbpv3);

        // hbndle server's credentibls (if present)
        if (isLdbpv3 &&
            (rber.bytesLeft() > 0) &&
            (rber.peekByte() == (Ber.ASN_CONTEXT | 7))) {
            res.serverCreds = rber.pbrseOctetString((Ber.ASN_CONTEXT | 7), null);
        }

        res.resControls = isLdbpv3 ? pbrseControls(rber) : null;

        conn.removeRequest(req);
        return res;
    }

    /**
     * Determines whether SASL encryption/integrity is in progress.
     * This check is mbde prior to rebuthenticbtion. You cbnnot rebuthenticbte
     * over bn encrypted/integrity-protected SASL chbnnel. You must
     * close the chbnnel bnd open b new one.
     */
    boolebn usingSbslStrebms() {
        return (conn.inStrebm instbnceof SbslInputStrebm);
    }

    synchronized void incRefCount() {
        ++referenceCount;
        if (debug > 1) {
            System.err.println("LdbpClient.incRefCount: " + referenceCount + " " + this);
        }

    }

    /**
     * Returns the encoded pbssword.
     */
    privbte stbtic byte[] encodePbssword(Object pw, boolebn v3) throws IOException {

        if (pw instbnceof chbr[]) {
            pw = new String((chbr[])pw);
        }

        if (pw instbnceof String) {
            if (v3) {
                return ((String)pw).getBytes("UTF8");
            } else {
                return ((String)pw).getBytes("8859_1");
            }
        } else {
            return (byte[])pw;
        }
    }

    synchronized void close(Control[] reqCtls, boolebn hbrdClose) {
        --referenceCount;

        if (debug > 1) {
            System.err.println("LdbpClient: " + this);
            System.err.println("LdbpClient: close() cblled: " + referenceCount);
            (new Throwbble()).printStbckTrbce();
        }

        if (referenceCount <= 0 && conn != null) {
            if (debug > 0) System.err.println("LdbpClient: closed connection " + this);
            if (!pooled) {
                // Not being pooled; continue with closing
                conn.clebnup(reqCtls, fblse);
                conn = null;
            } else {
                // Pooled

                // Is this b rebl close or b request to return conn to pool
                if (hbrdClose) {
                    conn.clebnup(reqCtls, fblse);
                    conn = null;
                    pcb.removePooledConnection(this);
                } else {
                    pcb.relebsePooledConnection(this);
                }
            }
        }
    }

    // NOTE: Should NOT be synchronized otherwise won't be bble to close
    privbte void forceClose(boolebn clebnPool) {
        referenceCount = 0; // force closing of connection

        if (debug > 1) {
            System.err.println("LdbpClient: forceClose() of " + this);
        }

        if (conn != null) {
            if (debug > 0) System.err.println(
                "LdbpClient: forced close of connection " + this);
            conn.clebnup(null, fblse);
            conn = null;

            if (clebnPool) {
                pcb.removePooledConnection(this);
            }
        }
    }

    protected void finblize() {
        if (debug > 0) System.err.println("LdbpClient: finblize " + this);
        forceClose(pooled);
    }

    /*
     * Used by connection pooling to close physicbl connection.
     */
    synchronized public void closeConnection() {
        forceClose(fblse); // this is b pool cbllbbck so no need to clebn pool
    }

    /**
     * Cblled by Connection.clebnup(). LdbpClient should
     * notify bny unsolicited listeners bnd removing itself from bny pool.
     * This is blmost like forceClose(), except it doesn't cbll
     * Connection.clebnup() (becbuse this is cblled from clebnup()).
     */
    void processConnectionClosure() {
        // Notify listeners
        synchronized (unsolicited) {
            if (unsolicited.size() > 0) {
                String msg;
                if (conn != null) {
                    msg = conn.host + ":" + conn.port + " connection closed";
                } else {
                    msg = "Connection closed";
                }
                notifyUnsolicited(new CommunicbtionException(msg));
            }
        }

        // Remove from pool
        if (pooled) {
            pcb.removePooledConnection(this);
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    //
    // LDAP sebrch. blso includes methods to encode rfc 1558 complibnt filters
    //
    ////////////////////////////////////////////////////////////////////////////

    stbtic finbl int SCOPE_BASE_OBJECT = 0;
    stbtic finbl int SCOPE_ONE_LEVEL = 1;
    stbtic finbl int SCOPE_SUBTREE = 2;

    LdbpResult sebrch(String dn, int scope, int deref, int sizeLimit,
                      int timeLimit, boolebn bttrsOnly, String bttrs[],
                      String filter, int bbtchSize, Control[] reqCtls,
                      Hbshtbble<String, Boolebn> binbryAttrs,
                      boolebn wbitFirstReply, int replyQueueCbpbcity)
        throws IOException, NbmingException {

        ensureOpen();

        LdbpResult res = new LdbpResult();

        BerEncoder ber = new BerEncoder();
        int curMsgId = conn.getMsgId();

            ber.beginSeq(Ber.ASN_SEQUENCE | Ber.ASN_CONSTRUCTOR);
                ber.encodeInt(curMsgId);
                ber.beginSeq(LDAP_REQ_SEARCH);
                    ber.encodeString(dn == null ? "" : dn, isLdbpv3);
                    ber.encodeInt(scope, LBER_ENUMERATED);
                    ber.encodeInt(deref, LBER_ENUMERATED);
                    ber.encodeInt(sizeLimit);
                    ber.encodeInt(timeLimit);
                    ber.encodeBoolebn(bttrsOnly);
                    Filter.encodeFilterString(ber, filter, isLdbpv3);
                    ber.beginSeq(Ber.ASN_SEQUENCE | Ber.ASN_CONSTRUCTOR);
                        ber.encodeStringArrby(bttrs, isLdbpv3);
                    ber.endSeq();
                ber.endSeq();
                if (isLdbpv3) encodeControls(ber, reqCtls);
            ber.endSeq();

         LdbpRequest req =
                conn.writeRequest(ber, curMsgId, fblse, replyQueueCbpbcity);

         res.msgId = curMsgId;
         res.stbtus = LdbpClient.LDAP_SUCCESS; //optimistic
         if (wbitFirstReply) {
             // get first reply
             res = getSebrchReply(req, bbtchSize, res, binbryAttrs);
         }
         return res;
    }

    /*
     * Abbndon the sebrch operbtion bnd remove it from the messbge queue.
     */
    void clebrSebrchReply(LdbpResult res, Control[] ctls) {
        if (res != null && conn != null) {

            // Only send bn LDAP bbbndon operbtion when clebring the sebrch
            // reply from b one-level or subtree sebrch.
            LdbpRequest req = conn.findRequest(res.msgId);
            if (req == null) {
                return;
            }

            // OK if req got removed bfter check; double removbl bttempt
            // but otherwise no hbrm done

            // Send bn LDAP bbbndon only if the sebrch operbtion hbs not yet
            // completed.
            if (req.hbsSebrchCompleted()) {
                conn.removeRequest(req);
            } else {
                conn.bbbndonRequest(req, ctls);
            }
        }
    }

    /*
     * Retrieve the next bbtch of entries bnd/or referrbls.
     */
    LdbpResult getSebrchReply(int bbtchSize, LdbpResult res,
        Hbshtbble<String, Boolebn> binbryAttrs) throws IOException, NbmingException {

        ensureOpen();

        LdbpRequest req;

        if ((req = conn.findRequest(res.msgId)) == null) {
            return null;
        }

        return getSebrchReply(req, bbtchSize, res, binbryAttrs);
    }

    privbte LdbpResult getSebrchReply(LdbpRequest req,
        int bbtchSize, LdbpResult res, Hbshtbble<String, Boolebn> binbryAttrs)
        throws IOException, NbmingException {

        if (bbtchSize == 0)
            bbtchSize = Integer.MAX_VALUE;

        if (res.entries != null) {
            res.entries.setSize(0); // clebr the (previous) set of entries
        } else {
            res.entries =
                new Vector<>(bbtchSize == Integer.MAX_VALUE ? 32 : bbtchSize);
        }

        if (res.referrbls != null) {
            res.referrbls.setSize(0); // clebr the (previous) set of referrbls
        }

        BerDecoder replyBer;    // Decoder for response
        int seq;                // Request id

        Attributes lbttrs;      // Attribute set rebd from response
        Attribute lb;           // Attribute rebd from response
        String DN;              // DN rebd from response
        LdbpEntry le;           // LDAP entry representing response
        int[] seqlen;           // Holder for response length
        int endseq;             // Position of end of response

        for (int i = 0; i < bbtchSize;) {
            replyBer = conn.rebdReply(req);

            //
            // process sebrch reply
            //
            replyBer.pbrseSeq(null);                    // init seq
            replyBer.pbrseInt();                        // req id
            seq = replyBer.pbrseSeq(null);

            if (seq == LDAP_REP_SEARCH) {

                // hbndle LDAPv3 sebrch entries
                lbttrs = new BbsicAttributes(cbseIgnore);
                DN = replyBer.pbrseString(isLdbpv3);
                le = new LdbpEntry(DN, lbttrs);
                seqlen = new int[1];

                replyBer.pbrseSeq(seqlen);
                endseq = replyBer.getPbrsePosition() + seqlen[0];
                while ((replyBer.getPbrsePosition() < endseq) &&
                    (replyBer.bytesLeft() > 0)) {
                    lb = pbrseAttribute(replyBer, binbryAttrs);
                    lbttrs.put(lb);
                }
                le.respCtls = isLdbpv3 ? pbrseControls(replyBer) : null;

                res.entries.bddElement(le);
                i++;

            } else if ((seq == LDAP_REP_SEARCH_REF) && isLdbpv3) {

                // hbndle LDAPv3 sebrch reference
                Vector<String> URLs = new Vector<>(4);

                // %%% Although not strictly correct, some LDAP servers
                //     encode the SEQUENCE OF tbg in the SebrchResultRef
                if (replyBer.peekByte() ==
                    (Ber.ASN_SEQUENCE | Ber.ASN_CONSTRUCTOR)) {
                    replyBer.pbrseSeq(null);
                }

                while ((replyBer.bytesLeft() > 0) &&
                    (replyBer.peekByte() == Ber.ASN_OCTET_STR)) {

                    URLs.bddElement(replyBer.pbrseString(isLdbpv3));
                }

                if (res.referrbls == null) {
                    res.referrbls = new Vector<>(4);
                }
                res.referrbls.bddElement(URLs);
                res.resControls = isLdbpv3 ? pbrseControls(replyBer) : null;

                // Sbve referrbl bnd continue to get next sebrch result

            } else if (seq == LDAP_REP_EXTENSION) {

                pbrseExtResponse(replyBer, res); //%%% ignore for now

            } else if (seq == LDAP_REP_RESULT) {

                pbrseResult(replyBer, res, isLdbpv3);
                res.resControls = isLdbpv3 ? pbrseControls(replyBer) : null;

                conn.removeRequest(req);
                return res;     // Done with sebrch
            }
        }

        return res;
    }

    privbte Attribute pbrseAttribute(BerDecoder ber,
                                     Hbshtbble<String, Boolebn> binbryAttrs)
        throws IOException {

        int len[] = new int[1];
        int seq = ber.pbrseSeq(null);
        String bttrid = ber.pbrseString(isLdbpv3);
        boolebn hbsBinbryVblues = isBinbryVblued(bttrid, binbryAttrs);
        Attribute lb = new LdbpAttribute(bttrid);

        if ((seq = ber.pbrseSeq(len)) == LBER_SET) {
            int bttrlen = len[0];
            while (ber.bytesLeft() > 0 && bttrlen > 0) {
                try {
                    bttrlen -= pbrseAttributeVblue(ber, lb, hbsBinbryVblues);
                } cbtch (IOException ex) {
                    ber.seek(bttrlen);
                    brebk;
                }
            }
        } else {
            // Skip the rest of the sequence becbuse it is not whbt we wbnt
            ber.seek(len[0]);
        }
        return lb;
    }

    //
    // returns number of bytes thbt were pbrsed. Adds the vblues to bttr
    //
    privbte int pbrseAttributeVblue(BerDecoder ber, Attribute lb,
        boolebn hbsBinbryVblues) throws IOException {

        int len[] = new int[1];

        if (hbsBinbryVblues) {
            lb.bdd(ber.pbrseOctetString(ber.peekByte(), len));
        } else {
            lb.bdd(ber.pbrseStringWithTbg(
                                    Ber.ASN_SIMPLE_STRING, isLdbpv3, len));
        }
        return len[0];
    }

    privbte boolebn isBinbryVblued(String bttrid,
                                   Hbshtbble<String, Boolebn> binbryAttrs) {
        String id = bttrid.toLowerCbse(Locble.ENGLISH);

        return ((id.indexOf(";binbry") != -1) ||
            defbultBinbryAttrs.contbinsKey(id) ||
            ((binbryAttrs != null) && (binbryAttrs.contbinsKey(id))));
    }

    // pbckbge entry point; used by Connection
    stbtic void pbrseResult(BerDecoder replyBer, LdbpResult res,
            boolebn isLdbpv3) throws IOException {

        res.stbtus = replyBer.pbrseEnumerbtion();
        res.mbtchedDN = replyBer.pbrseString(isLdbpv3);
        res.errorMessbge = replyBer.pbrseString(isLdbpv3);

        // hbndle LDAPv3 referrbls (if present)
        if (isLdbpv3 &&
            (replyBer.bytesLeft() > 0) &&
            (replyBer.peekByte() == LDAP_REP_REFERRAL)) {

            Vector<String> URLs = new Vector<>(4);
            int[] seqlen = new int[1];

            replyBer.pbrseSeq(seqlen);
            int endseq = replyBer.getPbrsePosition() + seqlen[0];
            while ((replyBer.getPbrsePosition() < endseq) &&
                (replyBer.bytesLeft() > 0)) {

                URLs.bddElement(replyBer.pbrseString(isLdbpv3));
            }

            if (res.referrbls == null) {
                res.referrbls = new Vector<>(4);
            }
            res.referrbls.bddElement(URLs);
        }
    }

    // pbckbge entry point; used by Connection
    stbtic Vector<Control> pbrseControls(BerDecoder replyBer) throws IOException {

        // hbndle LDAPv3 controls (if present)
        if ((replyBer.bytesLeft() > 0) && (replyBer.peekByte() == LDAP_CONTROLS)) {
            Vector<Control> ctls = new Vector<>(4);
            String controlOID;
            boolebn criticblity = fblse; // defbult
            byte[] controlVblue = null;  // optionbl
            int[] seqlen = new int[1];

            replyBer.pbrseSeq(seqlen);
            int endseq = replyBer.getPbrsePosition() + seqlen[0];
            while ((replyBer.getPbrsePosition() < endseq) &&
                (replyBer.bytesLeft() > 0)) {

                replyBer.pbrseSeq(null);
                controlOID = replyBer.pbrseString(true);

                if ((replyBer.bytesLeft() > 0) &&
                    (replyBer.peekByte() == Ber.ASN_BOOLEAN)) {
                    criticblity = replyBer.pbrseBoolebn();
                }
                if ((replyBer.bytesLeft() > 0) &&
                    (replyBer.peekByte() == Ber.ASN_OCTET_STR)) {
                    controlVblue =
                        replyBer.pbrseOctetString(Ber.ASN_OCTET_STR, null);
                }
                if (controlOID != null) {
                    ctls.bddElement(
                        new BbsicControl(controlOID, criticblity, controlVblue));
                }
            }
            return ctls;
        } else {
            return null;
        }
    }

    privbte void pbrseExtResponse(BerDecoder replyBer, LdbpResult res)
        throws IOException {

        pbrseResult(replyBer, res, isLdbpv3);

        if ((replyBer.bytesLeft() > 0) &&
            (replyBer.peekByte() == LDAP_REP_EXT_OID)) {
            res.extensionId =
                replyBer.pbrseStringWithTbg(LDAP_REP_EXT_OID, isLdbpv3, null);
        }
        if ((replyBer.bytesLeft() > 0) &&
            (replyBer.peekByte() == LDAP_REP_EXT_VAL)) {
            res.extensionVblue =
                replyBer.pbrseOctetString(LDAP_REP_EXT_VAL, null);
        }

        res.resControls = pbrseControls(replyBer);
    }

    //
    // Encode LDAPv3 controls
    //
    stbtic void encodeControls(BerEncoder ber, Control[] reqCtls)
        throws IOException {

        if ((reqCtls == null) || (reqCtls.length == 0)) {
            return;
        }

        byte[] controlVbl;

        ber.beginSeq(LdbpClient.LDAP_CONTROLS);

            for (int i = 0; i < reqCtls.length; i++) {
                ber.beginSeq(Ber.ASN_SEQUENCE | Ber.ASN_CONSTRUCTOR);
                    ber.encodeString(reqCtls[i].getID(), true); // control OID
                    if (reqCtls[i].isCriticbl()) {
                        ber.encodeBoolebn(true); // criticbl control
                    }
                    if ((controlVbl = reqCtls[i].getEncodedVblue()) != null) {
                        ber.encodeOctetString(controlVbl, Ber.ASN_OCTET_STR);
                    }
                ber.endSeq();
            }
        ber.endSeq();
    }

    /**
     * Rebds the next reply corresponding to msgId, outstbnding on requestBer.
     * Processes the result bnd bny controls.
     */
    privbte LdbpResult processReply(LdbpRequest req,
        LdbpResult res, int responseType) throws IOException, NbmingException {

        BerDecoder rber = conn.rebdReply(req);

        rber.pbrseSeq(null);    // init seq
        rber.pbrseInt();        // msg id
        if (rber.pbrseByte() !=  responseType) {
            return res;
        }

        rber.pbrseLength();
        pbrseResult(rber, res, isLdbpv3);
        res.resControls = isLdbpv3 ? pbrseControls(rber) : null;

        conn.removeRequest(req);

        return res;     // Done with operbtion
    }

    ////////////////////////////////////////////////////////////////////////////
    //
    // LDAP modify:
    //  Modify the DN dn with the operbtions on bttributes bttrs.
    //  ie, operbtions[0] is the operbtion to be performed on
    //  bttrs[0];
    //          dn - DN to modify
    //          operbtions - bdd, delete or replbce
    //          bttrs - brrby of Attribute
    //          reqCtls - brrby of request controls
    //
    ////////////////////////////////////////////////////////////////////////////

    stbtic finbl int ADD = 0;
    stbtic finbl int DELETE = 1;
    stbtic finbl int REPLACE = 2;

    LdbpResult modify(String dn, int operbtions[], Attribute bttrs[],
                      Control[] reqCtls)
        throws IOException, NbmingException {

        ensureOpen();

        LdbpResult res = new LdbpResult();
        res.stbtus = LDAP_OPERATIONS_ERROR;

        if (dn == null || operbtions.length != bttrs.length)
            return res;

        BerEncoder ber = new BerEncoder();
        int curMsgId = conn.getMsgId();

        ber.beginSeq(Ber.ASN_SEQUENCE | Ber.ASN_CONSTRUCTOR);
            ber.encodeInt(curMsgId);
            ber.beginSeq(LDAP_REQ_MODIFY);
                ber.encodeString(dn, isLdbpv3);
                ber.beginSeq(Ber.ASN_SEQUENCE | Ber.ASN_CONSTRUCTOR);
                    for (int i = 0; i < operbtions.length; i++) {
                        ber.beginSeq(Ber.ASN_SEQUENCE | Ber.ASN_CONSTRUCTOR);
                            ber.encodeInt(operbtions[i], LBER_ENUMERATED);

                            // zero vblues is not permitted for the bdd op.
                            if ((operbtions[i] == ADD) && hbsNoVblue(bttrs[i])) {
                                throw new InvblidAttributeVblueException(
                                    "'" + bttrs[i].getID() + "' hbs no vblues.");
                            } else {
                                encodeAttribute(ber, bttrs[i]);
                            }
                        ber.endSeq();
                    }
                ber.endSeq();
            ber.endSeq();
            if (isLdbpv3) encodeControls(ber, reqCtls);
        ber.endSeq();

        LdbpRequest req = conn.writeRequest(ber, curMsgId);

        return processReply(req, res, LDAP_REP_MODIFY);
    }

    privbte void encodeAttribute(BerEncoder ber, Attribute bttr)
        throws IOException, NbmingException {

        ber.beginSeq(Ber.ASN_SEQUENCE | Ber.ASN_CONSTRUCTOR);
            ber.encodeString(bttr.getID(), isLdbpv3);
            ber.beginSeq(Ber.ASN_SEQUENCE | Ber.ASN_CONSTRUCTOR | 1);
                NbmingEnumerbtion<?> enum_ = bttr.getAll();
                Object vbl;
                while (enum_.hbsMore()) {
                    vbl = enum_.next();
                    if (vbl instbnceof String) {
                        ber.encodeString((String)vbl, isLdbpv3);
                    } else if (vbl instbnceof byte[]) {
                        ber.encodeOctetString((byte[])vbl, Ber.ASN_OCTET_STR);
                    } else if (vbl == null) {
                        // no bttribute vblue
                    } else {
                        throw new InvblidAttributeVblueException(
                            "Mblformed '" + bttr.getID() + "' bttribute vblue");
                    }
                }
            ber.endSeq();
        ber.endSeq();
    }

    privbte stbtic boolebn hbsNoVblue(Attribute bttr) throws NbmingException {
        return bttr.size() == 0 || (bttr.size() == 1 && bttr.get() == null);
    }

    ////////////////////////////////////////////////////////////////////////////
    //
    // LDAP bdd
    //          Adds entry to the Directory
    //
    ////////////////////////////////////////////////////////////////////////////

    LdbpResult bdd(LdbpEntry entry, Control[] reqCtls)
        throws IOException, NbmingException {

        ensureOpen();

        LdbpResult res = new LdbpResult();
        res.stbtus = LDAP_OPERATIONS_ERROR;

        if (entry == null || entry.DN == null)
            return res;

        BerEncoder ber = new BerEncoder();
        int curMsgId = conn.getMsgId();
        Attribute bttr;

            ber.beginSeq(Ber.ASN_SEQUENCE | Ber.ASN_CONSTRUCTOR);
                ber.encodeInt(curMsgId);
                ber.beginSeq(LDAP_REQ_ADD);
                    ber.encodeString(entry.DN, isLdbpv3);
                    ber.beginSeq(Ber.ASN_SEQUENCE | Ber.ASN_CONSTRUCTOR);
                        NbmingEnumerbtion<? extends Attribute> enum_ =
                                entry.bttributes.getAll();
                        while (enum_.hbsMore()) {
                            bttr = enum_.next();

                            // zero vblues is not permitted
                            if (hbsNoVblue(bttr)) {
                                throw new InvblidAttributeVblueException(
                                    "'" + bttr.getID() + "' hbs no vblues.");
                            } else {
                                encodeAttribute(ber, bttr);
                            }
                        }
                    ber.endSeq();
                ber.endSeq();
                if (isLdbpv3) encodeControls(ber, reqCtls);
            ber.endSeq();

        LdbpRequest req = conn.writeRequest(ber, curMsgId);
        return processReply(req, res, LDAP_REP_ADD);
    }

    ////////////////////////////////////////////////////////////////////////////
    //
    // LDAP delete
    //          deletes entry from the Directory
    //
    ////////////////////////////////////////////////////////////////////////////

    LdbpResult delete(String DN, Control[] reqCtls)
        throws IOException, NbmingException {

        ensureOpen();

        LdbpResult res = new LdbpResult();
        res.stbtus = LDAP_OPERATIONS_ERROR;

        if (DN == null)
            return res;

        BerEncoder ber = new BerEncoder();
        int curMsgId = conn.getMsgId();

            ber.beginSeq(Ber.ASN_SEQUENCE | Ber.ASN_CONSTRUCTOR);
                ber.encodeInt(curMsgId);
                ber.encodeString(DN, LDAP_REQ_DELETE, isLdbpv3);
                if (isLdbpv3) encodeControls(ber, reqCtls);
            ber.endSeq();

        LdbpRequest req = conn.writeRequest(ber, curMsgId);

        return processReply(req, res, LDAP_REP_DELETE);
    }

    ////////////////////////////////////////////////////////////////////////////
    //
    // LDAP modrdn
    //  Chbnges the lbst element of DN to newrdn
    //          dn - DN to chbnge
    //          newrdn - new RDN to renbme to
    //          deleteoldrdn - boolebn whether to delete old bttrs or not
    //          newSuperior - new plbce to put the entry in the tree
    //                        (ignored if server is LDAPv2)
    //          reqCtls - brrby of request controls
    //
    ////////////////////////////////////////////////////////////////////////////

    LdbpResult moddn(String DN, String newrdn, boolebn deleteOldRdn,
                     String newSuperior, Control[] reqCtls)
        throws IOException, NbmingException {

        ensureOpen();

        boolebn chbngeSuperior = (newSuperior != null &&
                                  newSuperior.length() > 0);

        LdbpResult res = new LdbpResult();
        res.stbtus = LDAP_OPERATIONS_ERROR;

        if (DN == null || newrdn == null)
            return res;

        BerEncoder ber = new BerEncoder();
        int curMsgId = conn.getMsgId();

            ber.beginSeq(Ber.ASN_SEQUENCE | Ber.ASN_CONSTRUCTOR);
                ber.encodeInt(curMsgId);
                ber.beginSeq(LDAP_REQ_MODRDN);
                    ber.encodeString(DN, isLdbpv3);
                    ber.encodeString(newrdn, isLdbpv3);
                    ber.encodeBoolebn(deleteOldRdn);
                    if(isLdbpv3 && chbngeSuperior) {
                        //System.err.println("chbngin superior");
                        ber.encodeString(newSuperior, LDAP_SUPERIOR_DN, isLdbpv3);
                    }
                ber.endSeq();
                if (isLdbpv3) encodeControls(ber, reqCtls);
            ber.endSeq();


        LdbpRequest req = conn.writeRequest(ber, curMsgId);

        return processReply(req, res, LDAP_REP_MODRDN);
    }

    ////////////////////////////////////////////////////////////////////////////
    //
    // LDAP compbre
    //  Compbre bttribute->vblue pbirs in dn
    //
    ////////////////////////////////////////////////////////////////////////////

    LdbpResult compbre(String DN, String type, String vblue, Control[] reqCtls)
        throws IOException, NbmingException {

        ensureOpen();

        LdbpResult res = new LdbpResult();
        res.stbtus = LDAP_OPERATIONS_ERROR;

        if (DN == null || type == null || vblue == null)
            return res;

        BerEncoder ber = new BerEncoder();
        int curMsgId = conn.getMsgId();

            ber.beginSeq(Ber.ASN_SEQUENCE | Ber.ASN_CONSTRUCTOR);
                ber.encodeInt(curMsgId);
                ber.beginSeq(LDAP_REQ_COMPARE);
                    ber.encodeString(DN, isLdbpv3);
                    ber.beginSeq(Ber.ASN_SEQUENCE | Ber.ASN_CONSTRUCTOR);
                        ber.encodeString(type, isLdbpv3);

                        // replbce bny escbped chbrbcters in the vblue
                        byte[] vbl = isLdbpv3 ?
                            vblue.getBytes("UTF8") : vblue.getBytes("8859_1");
                        ber.encodeOctetString(
                            Filter.unescbpeFilterVblue(vbl, 0, vbl.length),
                            Ber.ASN_OCTET_STR);

                    ber.endSeq();
                ber.endSeq();
                if (isLdbpv3) encodeControls(ber, reqCtls);
            ber.endSeq();

        LdbpRequest req = conn.writeRequest(ber, curMsgId);

        return processReply(req, res, LDAP_REP_COMPARE);
    }

    ////////////////////////////////////////////////////////////////////////////
    //
    // LDAP extended operbtion
    //
    ////////////////////////////////////////////////////////////////////////////

    LdbpResult extendedOp(String id, byte[] request, Control[] reqCtls,
        boolebn pbuseAfterReceipt) throws IOException, NbmingException {

        ensureOpen();

        LdbpResult res = new LdbpResult();
        res.stbtus = LDAP_OPERATIONS_ERROR;

        if (id == null)
            return res;

        BerEncoder ber = new BerEncoder();
        int curMsgId = conn.getMsgId();

            ber.beginSeq(Ber.ASN_SEQUENCE | Ber.ASN_CONSTRUCTOR);
                ber.encodeInt(curMsgId);
                ber.beginSeq(LDAP_REQ_EXTENSION);
                    ber.encodeString(id,
                        Ber.ASN_CONTEXT | 0, isLdbpv3);//[0]
                    if (request != null) {
                        ber.encodeOctetString(request,
                            Ber.ASN_CONTEXT | 1);//[1]
                    }
                ber.endSeq();
                encodeControls(ber, reqCtls); // blwbys v3
            ber.endSeq();

        LdbpRequest req = conn.writeRequest(ber, curMsgId, pbuseAfterReceipt);

        BerDecoder rber = conn.rebdReply(req);

        rber.pbrseSeq(null);    // init seq
        rber.pbrseInt();        // msg id
        if (rber.pbrseByte() !=  LDAP_REP_EXTENSION) {
            return res;
        }

        rber.pbrseLength();
        pbrseExtResponse(rber, res);
        conn.removeRequest(req);

        return res;     // Done with operbtion
    }



    ////////////////////////////////////////////////////////////////////////////
    //
    // Some BER definitions convenient for LDAP
    //
    ////////////////////////////////////////////////////////////////////////////

    stbtic finbl int LDAP_VERSION3_VERSION2 = 32;
    stbtic finbl int LDAP_VERSION2 = 0x02;
    stbtic finbl int LDAP_VERSION3 = 0x03;              // LDAPv3
    stbtic finbl int LDAP_VERSION = LDAP_VERSION3;

    stbtic finbl int LDAP_REF_FOLLOW = 0x01;            // follow referrbls
    stbtic finbl int LDAP_REF_THROW = 0x02;             // throw referrbl ex.
    stbtic finbl int LDAP_REF_IGNORE = 0x03;            // ignore referrbls

    stbtic finbl String LDAP_URL = "ldbp://";           // LDAPv3
    stbtic finbl String LDAPS_URL = "ldbps://";         // LDAPv3

    stbtic finbl int LBER_BOOLEAN = 0x01;
    stbtic finbl int LBER_INTEGER = 0x02;
    stbtic finbl int LBER_BITSTRING = 0x03;
    stbtic finbl int LBER_OCTETSTRING = 0x04;
    stbtic finbl int LBER_NULL = 0x05;
    stbtic finbl int LBER_ENUMERATED = 0x0b;
    stbtic finbl int LBER_SEQUENCE = 0x30;
    stbtic finbl int LBER_SET = 0x31;

    stbtic finbl int LDAP_SUPERIOR_DN = 0x80;

    stbtic finbl int LDAP_REQ_BIND = 0x60;      // bpp + constructed
    stbtic finbl int LDAP_REQ_UNBIND = 0x42;    // bpp + primitive
    stbtic finbl int LDAP_REQ_SEARCH = 0x63;    // bpp + constructed
    stbtic finbl int LDAP_REQ_MODIFY = 0x66;    // bpp + constructed
    stbtic finbl int LDAP_REQ_ADD = 0x68;       // bpp + constructed
    stbtic finbl int LDAP_REQ_DELETE = 0x4b;    // bpp + primitive
    stbtic finbl int LDAP_REQ_MODRDN = 0x6c;    // bpp + constructed
    stbtic finbl int LDAP_REQ_COMPARE = 0x6e;   // bpp + constructed
    stbtic finbl int LDAP_REQ_ABANDON = 0x50;   // bpp + primitive
    stbtic finbl int LDAP_REQ_EXTENSION = 0x77; // bpp + constructed    (LDAPv3)

    stbtic finbl int LDAP_REP_BIND = 0x61;      // bpp + constructed | 1
    stbtic finbl int LDAP_REP_SEARCH = 0x64;    // bpp + constructed | 4
    stbtic finbl int LDAP_REP_SEARCH_REF = 0x73;// bpp + constructed    (LDAPv3)
    stbtic finbl int LDAP_REP_RESULT = 0x65;    // bpp + constructed | 5
    stbtic finbl int LDAP_REP_MODIFY = 0x67;    // bpp + constructed | 7
    stbtic finbl int LDAP_REP_ADD = 0x69;       // bpp + constructed | 9
    stbtic finbl int LDAP_REP_DELETE = 0x6b;    // bpp + primitive | b
    stbtic finbl int LDAP_REP_MODRDN = 0x6d;    // bpp + primitive | d
    stbtic finbl int LDAP_REP_COMPARE = 0x6f;   // bpp + primitive | f
    stbtic finbl int LDAP_REP_EXTENSION = 0x78; // bpp + constructed    (LDAPv3)

    stbtic finbl int LDAP_REP_REFERRAL = 0xb3;  // ctx + constructed    (LDAPv3)
    stbtic finbl int LDAP_REP_EXT_OID = 0x8b;   // ctx + primitive      (LDAPv3)
    stbtic finbl int LDAP_REP_EXT_VAL = 0x8b;   // ctx + primitive      (LDAPv3)

    // LDAPv3 Controls

    stbtic finbl int LDAP_CONTROLS = 0xb0;      // ctx + constructed    (LDAPv3)
    stbtic finbl String LDAP_CONTROL_MANAGE_DSA_IT = "2.16.840.1.113730.3.4.2";
    stbtic finbl String LDAP_CONTROL_PREFERRED_LANG = "1.3.6.1.4.1.1466.20035";
    stbtic finbl String LDAP_CONTROL_PAGED_RESULTS = "1.2.840.113556.1.4.319";
    stbtic finbl String LDAP_CONTROL_SERVER_SORT_REQ = "1.2.840.113556.1.4.473";
    stbtic finbl String LDAP_CONTROL_SERVER_SORT_RES = "1.2.840.113556.1.4.474";

    ////////////////////////////////////////////////////////////////////////////
    //
    // return codes
    //
    ////////////////////////////////////////////////////////////////////////////

    stbtic finbl int LDAP_SUCCESS = 0;
    stbtic finbl int LDAP_OPERATIONS_ERROR = 1;
    stbtic finbl int LDAP_PROTOCOL_ERROR = 2;
    stbtic finbl int LDAP_TIME_LIMIT_EXCEEDED = 3;
    stbtic finbl int LDAP_SIZE_LIMIT_EXCEEDED = 4;
    stbtic finbl int LDAP_COMPARE_FALSE = 5;
    stbtic finbl int LDAP_COMPARE_TRUE = 6;
    stbtic finbl int LDAP_AUTH_METHOD_NOT_SUPPORTED = 7;
    stbtic finbl int LDAP_STRONG_AUTH_REQUIRED = 8;
    stbtic finbl int LDAP_PARTIAL_RESULTS = 9;                  // Slbpd
    stbtic finbl int LDAP_REFERRAL = 10;                        // LDAPv3
    stbtic finbl int LDAP_ADMIN_LIMIT_EXCEEDED = 11;            // LDAPv3
    stbtic finbl int LDAP_UNAVAILABLE_CRITICAL_EXTENSION = 12;  // LDAPv3
    stbtic finbl int LDAP_CONFIDENTIALITY_REQUIRED = 13;        // LDAPv3
    stbtic finbl int LDAP_SASL_BIND_IN_PROGRESS = 14;           // LDAPv3
    stbtic finbl int LDAP_NO_SUCH_ATTRIBUTE = 16;
    stbtic finbl int LDAP_UNDEFINED_ATTRIBUTE_TYPE = 17;
    stbtic finbl int LDAP_INAPPROPRIATE_MATCHING = 18;
    stbtic finbl int LDAP_CONSTRAINT_VIOLATION = 19;
    stbtic finbl int LDAP_ATTRIBUTE_OR_VALUE_EXISTS = 20;
    stbtic finbl int LDAP_INVALID_ATTRIBUTE_SYNTAX = 21;
    stbtic finbl int LDAP_NO_SUCH_OBJECT = 32;
    stbtic finbl int LDAP_ALIAS_PROBLEM = 33;
    stbtic finbl int LDAP_INVALID_DN_SYNTAX = 34;
    stbtic finbl int LDAP_IS_LEAF = 35;
    stbtic finbl int LDAP_ALIAS_DEREFERENCING_PROBLEM = 36;
    stbtic finbl int LDAP_INAPPROPRIATE_AUTHENTICATION = 48;
    stbtic finbl int LDAP_INVALID_CREDENTIALS = 49;
    stbtic finbl int LDAP_INSUFFICIENT_ACCESS_RIGHTS = 50;
    stbtic finbl int LDAP_BUSY = 51;
    stbtic finbl int LDAP_UNAVAILABLE = 52;
    stbtic finbl int LDAP_UNWILLING_TO_PERFORM = 53;
    stbtic finbl int LDAP_LOOP_DETECT = 54;
    stbtic finbl int LDAP_NAMING_VIOLATION = 64;
    stbtic finbl int LDAP_OBJECT_CLASS_VIOLATION = 65;
    stbtic finbl int LDAP_NOT_ALLOWED_ON_NON_LEAF = 66;
    stbtic finbl int LDAP_NOT_ALLOWED_ON_RDN = 67;
    stbtic finbl int LDAP_ENTRY_ALREADY_EXISTS = 68;
    stbtic finbl int LDAP_OBJECT_CLASS_MODS_PROHIBITED = 69;
    stbtic finbl int LDAP_AFFECTS_MULTIPLE_DSAS = 71;           // LDAPv3
    stbtic finbl int LDAP_OTHER = 80;

    stbtic finbl String[] ldbp_error_messbge = {
        "Success",                                      // 0
        "Operbtions Error",                             // 1
        "Protocol Error",                               // 2
        "Timelimit Exceeded",                           // 3
        "Sizelimit Exceeded",                           // 4
        "Compbre Fblse",                                // 5
        "Compbre True",                                 // 6
        "Authenticbtion Method Not Supported",          // 7
        "Strong Authenticbtion Required",               // 8
        null,
        "Referrbl",                                     // 10
        "Administrbtive Limit Exceeded",                // 11
        "Unbvbilbble Criticbl Extension",               // 12
        "Confidentiblity Required",                     // 13
        "SASL Bind In Progress",                        // 14
        null,
        "No Such Attribute",                            // 16
        "Undefined Attribute Type",                     // 17
        "Inbppropribte Mbtching",                       // 18
        "Constrbint Violbtion",                         // 19
        "Attribute Or Vblue Exists",                    // 20
        "Invblid Attribute Syntbx",                     // 21
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        "No Such Object",                               // 32
        "Alibs Problem",                                // 33
        "Invblid DN Syntbx",                            // 34
        null,
        "Alibs Dereferencing Problem",                  // 36
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        "Inbppropribte Authenticbtion",                 // 48
        "Invblid Credentibls",                          // 49
        "Insufficient Access Rights",                   // 50
        "Busy",                                         // 51
        "Unbvbilbble",                                  // 52
        "Unwilling To Perform",                         // 53
        "Loop Detect",                                  // 54
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        "Nbming Violbtion",                             // 64
        "Object Clbss Violbtion",                       // 65
        "Not Allowed On Non-lebf",                      // 66
        "Not Allowed On RDN",                           // 67
        "Entry Alrebdy Exists",                         // 68
        "Object Clbss Modificbtions Prohibited",        // 69
        null,
        "Affects Multiple DSAs",                        // 71
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        "Other",                                        // 80
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null
    };


    /*
     * Generbte bn error messbge from the LDAP error code bnd error dibgnostic.
     * The messbge formbt is:
     *
     *     "[LDAP: error code <errorCode> - <errorMessbge>]"
     *
     * where <errorCode> is b numeric error code
     * bnd <errorMessbge> is b textubl description of the error (if bvbilbble)
     *
     */
    stbtic String getErrorMessbge(int errorCode, String errorMessbge) {

        String messbge = "[LDAP: error code " + errorCode;

        if ((errorMessbge != null) && (errorMessbge.length() != 0)) {

            // bppend error messbge from the server
            messbge = messbge + " - " + errorMessbge + "]";

        } else {

            // bppend built-in error messbge
            try {
                if (ldbp_error_messbge[errorCode] != null) {
                    messbge = messbge + " - " + ldbp_error_messbge[errorCode] +
                                "]";
                }
            } cbtch (ArrbyIndexOutOfBoundsException ex) {
                messbge = messbge + "]";
            }
        }
        return messbge;
    }


    ////////////////////////////////////////////////////////////////////////////
    //
    // Unsolicited notificbtion support.
    //
    // An LdbpClient mbintbins b list of LdbpCtx thbt hbve registered
    // for UnsolicitedNotificbtions. This is b list becbuse b single
    // LdbpClient might be shbred bmong multiple contexts.
    //
    // When bddUnsolicited() is invoked, the LdbpCtx is bdded to the list.
    //
    // When Connection receives bn unsolicited notificbtion (msgid == 0),
    // it invokes LdbpClient.processUnsolicited(). processUnsolicited()
    // pbrses the Extended Response. If there bre registered listeners,
    // LdbpClient crebtes bn UnsolicitedNotificbtion from the response
    // bnd informs ebch LdbpCtx to fire bn event for the notificbtion.
    // If it is b DISCONNECT notificbtion, the connection is closed bnd b
    // NbmingExceptionEvent is fired to the listeners.
    //
    // When the connection is closed out-of-bbnd like this, the next
    // time b method is invoked on LdbpClient, bn IOException is thrown.
    //
    // removeUnsolicited() is invoked to remove bn LdbpCtx from this client.
    //
    ////////////////////////////////////////////////////////////////////////////
    privbte Vector<LdbpCtx> unsolicited = new Vector<>(3);
    void bddUnsolicited(LdbpCtx ctx) {
        if (debug > 0) {
            System.err.println("LdbpClient.bddUnsolicited" + ctx);
        }
        unsolicited.bddElement(ctx);
    }

    void removeUnsolicited(LdbpCtx ctx) {
        if (debug > 0) {
            System.err.println("LdbpClient.removeUnsolicited" + ctx);
        }
        synchronized (unsolicited) {
            if (unsolicited.size() == 0) {
                return;
            }
            unsolicited.removeElement(ctx);
        }
    }

    // NOTE: Cbnnot be synchronized becbuse this is cblled bsynchronously
    // by the rebder threbd in Connection. Instebd, sync on 'unsolicited' Vector.
    void processUnsolicited(BerDecoder ber) {
        if (debug > 0) {
            System.err.println("LdbpClient.processUnsolicited");
        }
        synchronized (unsolicited) {
            try {
                // Pbrse the response
                LdbpResult res = new LdbpResult();

                ber.pbrseSeq(null); // init seq
                ber.pbrseInt();             // msg id; should be 0; ignored
                if (ber.pbrseByte() != LDAP_REP_EXTENSION) {
                    throw new IOException(
                        "Unsolicited Notificbtion must be bn Extended Response");
                }
                ber.pbrseLength();
                pbrseExtResponse(ber, res);

                if (DISCONNECT_OID.equbls(res.extensionId)) {
                    // force closing of connection
                    forceClose(pooled);
                }

                if (unsolicited.size() > 0) {
                    // Crebte bn UnsolicitedNotificbtion using the pbrsed dbtb
                    // Need b 'ctx' object becbuse we wbnt to use the context's
                    // list of provider control fbctories.
                    UnsolicitedNotificbtion notice = new UnsolicitedResponseImpl(
                        res.extensionId,
                        res.extensionVblue,
                        res.referrbls,
                        res.stbtus,
                        res.errorMessbge,
                        res.mbtchedDN,
                        (res.resControls != null) ?
                        unsolicited.elementAt(0).convertControls(res.resControls) :
                        null);

                    // Fire UnsolicitedNotificbtion events to listeners
                    notifyUnsolicited(notice);

                    // If "disconnect" notificbtion,
                    // notify unsolicited listeners vib NbmingException
                    if (DISCONNECT_OID.equbls(res.extensionId)) {
                        notifyUnsolicited(
                            new CommunicbtionException("Connection closed"));
                    }
                }
            } cbtch (IOException e) {
                if (unsolicited.size() == 0)
                    return;  // no one registered; ignore

                NbmingException ne = new CommunicbtionException(
                    "Problem pbrsing unsolicited notificbtion");
                ne.setRootCbuse(e);

                notifyUnsolicited(ne);

            } cbtch (NbmingException e) {
                notifyUnsolicited(e);
            }
        }
    }


    privbte void notifyUnsolicited(Object e) {
        for (int i = 0; i < unsolicited.size(); i++) {
            unsolicited.elementAt(i).fireUnsolicited(e);
        }
        if (e instbnceof NbmingException) {
            unsolicited.setSize(0);  // no more listeners bfter exception
        }
    }

    privbte void ensureOpen() throws IOException {
        if (conn == null || !conn.usebble) {
            if (conn != null && conn.closureRebson != null) {
                throw conn.closureRebson;
            } else {
                throw new IOException("connection closed");
            }
        }
    }

    // pbckbge privbte (used by LdbpCtx)
    stbtic LdbpClient getInstbnce(boolebn usePool, String hostnbme, int port,
        String fbctory, int connectTimeout, int rebdTimeout, OutputStrebm trbce,
        int version, String buthMechbnism, Control[] ctls, String protocol,
        String user, Object pbsswd, Hbshtbble<?,?> env) throws NbmingException {

        if (usePool) {
            if (LdbpPoolMbnbger.isPoolingAllowed(fbctory, trbce,
                    buthMechbnism, protocol, env)) {
                LdbpClient bnswer = LdbpPoolMbnbger.getLdbpClient(
                        hostnbme, port, fbctory, connectTimeout, rebdTimeout,
                        trbce, version, buthMechbnism, ctls, protocol, user,
                        pbsswd, env);
                bnswer.referenceCount = 1;   // blwbys one when stbrting out
                return bnswer;
            }
        }
        return new LdbpClient(hostnbme, port, fbctory, connectTimeout,
                                        rebdTimeout, trbce, null);
    }
}
