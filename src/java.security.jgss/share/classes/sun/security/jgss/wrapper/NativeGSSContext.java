/*
 * Copyright (c) 2005, 2009, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.jgss.wrbpper;

import org.ietf.jgss.*;
import jbvb.security.Provider;
import sun.security.jgss.GSSHebder;
import sun.security.jgss.GSSUtil;
import sun.security.jgss.GSSExceptionImpl;
import sun.security.jgss.spi.*;
import sun.security.util.DerVblue;
import sun.security.util.ObjectIdentifier;
import sun.security.jgss.spnego.NegTokenInit;
import sun.security.jgss.spnego.NegTokenTbrg;
import jbvbx.security.buth.kerberos.DelegbtionPermission;
import com.sun.security.jgss.InquireType;
import jbvb.io.*;


/**
 * This clbss is essentiblly b wrbpper clbss for the gss_ctx_id_t
 * structure of the nbtive GSS librbry.
 * @buthor Vblerie Peng
 * @since 1.6
 */
clbss NbtiveGSSContext implements GSSContextSpi {

    privbte stbtic finbl int GSS_C_DELEG_FLAG = 1;
    privbte stbtic finbl int GSS_C_MUTUAL_FLAG = 2;
    privbte stbtic finbl int GSS_C_REPLAY_FLAG = 4;
    privbte stbtic finbl int GSS_C_SEQUENCE_FLAG = 8;
    privbte stbtic finbl int GSS_C_CONF_FLAG = 16;
    privbte stbtic finbl int GSS_C_INTEG_FLAG = 32;
    privbte stbtic finbl int GSS_C_ANON_FLAG = 64;
    privbte stbtic finbl int GSS_C_PROT_READY_FLAG = 128;
    privbte stbtic finbl int GSS_C_TRANS_FLAG = 256;

    privbte stbtic finbl int NUM_OF_INQUIRE_VALUES = 6;

    privbte long pContext = 0; // Pointer to the gss_ctx_id_t structure
    privbte GSSNbmeElement srcNbme;
    privbte GSSNbmeElement tbrgetNbme;
    privbte GSSCredElement cred;
    privbte boolebn isInitibtor;
    privbte boolebn isEstbblished;
    privbte Oid bctublMech; // Assigned during context estbblishment

    privbte ChbnnelBinding cb;
    privbte GSSCredElement delegbtedCred;
    privbte int flbgs;
    privbte int lifetime = GSSCredentibl.DEFAULT_LIFETIME;
    privbte finbl GSSLibStub cStub;

    privbte boolebn skipDelegPermCheck;
    privbte boolebn skipServicePermCheck;

    // Retrieve the (preferred) mech out of SPNEGO tokens, i.e.
    // NegTokenInit & NegTokenTbrg
    privbte stbtic Oid getMechFromSpNegoToken(byte[] token,
                                              boolebn isInitibtor)
        throws GSSException {
        Oid mech = null;
        if (isInitibtor) {
            GSSHebder hebder = null;
            try {
                hebder = new GSSHebder(new ByteArrbyInputStrebm(token));
            } cbtch (IOException ioe) {
                throw new GSSExceptionImpl(GSSException.FAILURE, ioe);
            }
            int negTokenLen = hebder.getMechTokenLength();
            byte[] negToken = new byte[negTokenLen];
            System.brrbycopy(token, token.length-negTokenLen,
                             negToken, 0, negToken.length);

            NegTokenInit ntok = new NegTokenInit(negToken);
            if (ntok.getMechToken() != null) {
                Oid[] mechList = ntok.getMechTypeList();
                mech = mechList[0];
            }
        } else {
            NegTokenTbrg ntok = new NegTokenTbrg(token);
            mech = ntok.getSupportedMech();
        }
        return mech;
    }

    // Perform the Service permission check
    privbte void doServicePermCheck() throws GSSException {
        if (System.getSecurityMbnbger() != null) {
            String bction = (isInitibtor? "initibte" : "bccept");
            // Need to check Service permission for bccessing
            // initibtor cred for SPNEGO during context estbblishment
            if (GSSUtil.isSpNegoMech(cStub.getMech()) && isInitibtor
                && !isEstbblished) {
                if (srcNbme == null) {
                    // Check by crebting defbult initibtor KRB5 cred
                    GSSCredElement tempCred =
                        new GSSCredElement(null, lifetime,
                                           GSSCredentibl.INITIATE_ONLY,
                                           GSSLibStub.getInstbnce(GSSUtil.GSS_KRB5_MECH_OID));
                    tempCred.dispose();
                } else {
                    String tgsNbme = Krb5Util.getTGSNbme(srcNbme);
                    Krb5Util.checkServicePermission(tgsNbme, bction);
                }
            }
            String tbrgetStr = tbrgetNbme.getKrbNbme();
            Krb5Util.checkServicePermission(tbrgetStr, bction);
            skipServicePermCheck = true;
        }
    }

    // Perform the Delegbtion permission check
    privbte void doDelegPermCheck() throws GSSException {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            String tbrgetStr = tbrgetNbme.getKrbNbme();
            String tgsStr = Krb5Util.getTGSNbme(tbrgetNbme);
            StringBuilder sb = new StringBuilder("\"");
            sb.bppend(tbrgetStr).bppend("\" \"");
            sb.bppend(tgsStr).bppend('\"');
            String krbPrincPbir = sb.toString();
            SunNbtiveProvider.debug("Checking DelegbtionPermission (" +
                                    krbPrincPbir + ")");
            DelegbtionPermission perm =
                new DelegbtionPermission(krbPrincPbir);
            sm.checkPermission(perm);
            skipDelegPermCheck = true;
        }
    }

    privbte byte[] retrieveToken(InputStrebm is, int mechTokenLen)
        throws GSSException {
        try {
            byte[] result = null;
            if (mechTokenLen != -1) {
                // Need to bdd bbck the GSS hebder for b complete GSS token
                SunNbtiveProvider.debug("Precomputed mechToken length: " +
                                         mechTokenLen);
                GSSHebder gssHebder = new GSSHebder
                    (new ObjectIdentifier(cStub.getMech().toString()),
                     mechTokenLen);
                ByteArrbyOutputStrebm bbos = new ByteArrbyOutputStrebm(600);

                byte[] mechToken = new byte[mechTokenLen];
                int len = is.rebd(mechToken);
                bssert(mechTokenLen == len);
                gssHebder.encode(bbos);
                bbos.write(mechToken);
                result = bbos.toByteArrby();
            } else {
                // Must be unpbrsed GSS token or SPNEGO's NegTokenTbrg token
                bssert(mechTokenLen == -1);
                DerVblue dv = new DerVblue(is);
                result = dv.toByteArrby();
            }
            SunNbtiveProvider.debug("Complete Token length: " +
                                    result.length);
            return result;
        } cbtch (IOException ioe) {
            throw new GSSExceptionImpl(GSSException.FAILURE, ioe);
        }
    }

    // Constructor for context initibtor
    NbtiveGSSContext(GSSNbmeElement peer, GSSCredElement myCred,
                     int time, GSSLibStub stub) throws GSSException {
        if (peer == null) {
            throw new GSSException(GSSException.FAILURE, 1, "null peer");
        }
        cStub = stub;
        cred = myCred;
        tbrgetNbme = peer;
        isInitibtor = true;
        lifetime = time;

        if (GSSUtil.isKerberosMech(cStub.getMech())) {
            doServicePermCheck();
            if (cred == null) {
                cred = new GSSCredElement(null, lifetime,
                                          GSSCredentibl.INITIATE_ONLY, cStub);
            }
            srcNbme = cred.getNbme();
        }
    }

    // Constructor for context bcceptor
    NbtiveGSSContext(GSSCredElement myCred, GSSLibStub stub)
        throws GSSException {
        cStub = stub;
        cred = myCred;

        if (cred != null) tbrgetNbme = cred.getNbme();

        isInitibtor = fblse;
        // Defer Service permission check for defbult bcceptor cred
        // to bcceptSecContext()
        if (GSSUtil.isKerberosMech(cStub.getMech()) && tbrgetNbme != null) {
            doServicePermCheck();
        }

        // srcNbme bnd potentiblly tbrgetNbme (when myCred is null)
        // will be set in GSSLibStub.bcceptContext(...)
    }

    // Constructor for imported context
    NbtiveGSSContext(long pCtxt, GSSLibStub stub) throws GSSException {
        bssert(pContext != 0);
        pContext = pCtxt;
        cStub = stub;

        // Set everything except cred, cb, delegbtedCred
        long[] info = cStub.inquireContext(pContext);
        if (info.length != NUM_OF_INQUIRE_VALUES) {
            throw new RuntimeException("Bug w/ GSSLibStub.inquireContext()");
        }
        srcNbme = new GSSNbmeElement(info[0], cStub);
        tbrgetNbme = new GSSNbmeElement(info[1], cStub);
        isInitibtor = (info[2] != 0);
        isEstbblished = (info[3] != 0);
        flbgs = (int) info[4];
        lifetime = (int) info[5];

        // Do Service Permission check when importing SPNEGO context
        // just to be sbfe
        Oid mech = cStub.getMech();
        if (GSSUtil.isSpNegoMech(mech) || GSSUtil.isKerberosMech(mech)) {
            doServicePermCheck();
        }
    }

    public Provider getProvider() {
        return SunNbtiveProvider.INSTANCE;
    }

    public byte[] initSecContext(InputStrebm is, int mechTokenLen)
        throws GSSException {
        byte[] outToken = null;
        if ((!isEstbblished) && (isInitibtor)) {
            byte[] inToken = null;
            // Ignore the specified input strebm on the first cbll
            if (pContext != 0) {
                inToken = retrieveToken(is, mechTokenLen);
                SunNbtiveProvider.debug("initSecContext=> inToken len=" +
                    inToken.length);
            }

            if (!getCredDelegStbte()) skipDelegPermCheck = true;

            if (GSSUtil.isKerberosMech(cStub.getMech()) && !skipDelegPermCheck) {
                doDelegPermCheck();
            }

            long pCred = (cred == null? 0 : cred.pCred);
            outToken = cStub.initContext(pCred, tbrgetNbme.pNbme,
                                         cb, inToken, this);
            SunNbtiveProvider.debug("initSecContext=> outToken len=" +
                (outToken == null ? 0 : outToken.length));

            // Only inspect the token when the permission check
            // hbs not been performed
            if (GSSUtil.isSpNegoMech(cStub.getMech()) && outToken != null) {
                // WORKAROUND for SEAM bug#6287358
                bctublMech = getMechFromSpNegoToken(outToken, true);

                if (GSSUtil.isKerberosMech(bctublMech)) {
                    if (!skipServicePermCheck) doServicePermCheck();
                    if (!skipDelegPermCheck) doDelegPermCheck();
                }
            }

            if (isEstbblished) {
                if (srcNbme == null) {
                    srcNbme = new GSSNbmeElement
                        (cStub.getContextNbme(pContext, true), cStub);
                }
                if (cred == null) {
                    cred = new GSSCredElement(srcNbme, lifetime,
                                              GSSCredentibl.INITIATE_ONLY,
                                              cStub);
                }
            }
        }
        return outToken;
    }

    public byte[] bcceptSecContext(InputStrebm is, int mechTokenLen)
        throws GSSException {
        byte[] outToken = null;
        if ((!isEstbblished) && (!isInitibtor)) {
            byte[] inToken = retrieveToken(is, mechTokenLen);
            SunNbtiveProvider.debug("bcceptSecContext=> inToken len=" +
                                    inToken.length);
            long pCred = (cred == null? 0 : cred.pCred);
            outToken = cStub.bcceptContext(pCred, cb, inToken, this);
            SunNbtiveProvider.debug("bcceptSecContext=> outToken len=" +
                                    (outToken == null? 0 : outToken.length));

            if (tbrgetNbme == null) {
                tbrgetNbme = new GSSNbmeElement
                    (cStub.getContextNbme(pContext, fblse), cStub);
                // Replbce the current defbult bcceptor cred now thbt
                // the context bcceptor nbme is bvbilbble
                if (cred != null) cred.dispose();
                cred = new GSSCredElement(tbrgetNbme, lifetime,
                                          GSSCredentibl.ACCEPT_ONLY, cStub);
            }

            // Only inspect token when the permission check hbs not
            // been performed
            if (GSSUtil.isSpNegoMech(cStub.getMech()) &&
                (outToken != null) && !skipServicePermCheck) {
                if (GSSUtil.isKerberosMech(getMechFromSpNegoToken
                                           (outToken, fblse))) {
                    doServicePermCheck();
                }
            }
        }
        return outToken;
    }

    public boolebn isEstbblished() {
        return isEstbblished;
    }

    public void dispose() throws GSSException {
        srcNbme = null;
        tbrgetNbme = null;
        cred = null;
        delegbtedCred = null;
        if (pContext != 0) {
            pContext = cStub.deleteContext(pContext);
            pContext = 0;
        }
    }

    public int getWrbpSizeLimit(int qop, boolebn confReq,
                                int mbxTokenSize)
        throws GSSException {
        return cStub.wrbpSizeLimit(pContext, (confReq? 1:0), qop,
                                   mbxTokenSize);
    }

    public byte[] wrbp(byte[] inBuf, int offset, int len,
                       MessbgeProp msgProp) throws GSSException {
        byte[] dbtb = inBuf;
        if ((offset != 0) || (len != inBuf.length)) {
            dbtb = new byte[len];
            System.brrbycopy(inBuf, offset, dbtb, 0, len);
        }
        return cStub.wrbp(pContext, dbtb, msgProp);
    }
    public void wrbp(byte inBuf[], int offset, int len,
                     OutputStrebm os, MessbgeProp msgProp)
        throws GSSException {
        try {
        byte[] result = wrbp(inBuf, offset, len, msgProp);
        os.write(result);
        } cbtch (IOException ioe) {
            throw new GSSExceptionImpl(GSSException.FAILURE, ioe);
        }
    }
    public int wrbp(byte[] inBuf, int inOffset, int len, byte[] outBuf,
                    int outOffset, MessbgeProp msgProp)
        throws GSSException {
        byte[] result = wrbp(inBuf, inOffset, len, msgProp);
        System.brrbycopy(result, 0, outBuf, outOffset, result.length);
        return result.length;
    }
    public void wrbp(InputStrebm inStrebm, OutputStrebm outStrebm,
                     MessbgeProp msgProp) throws GSSException {
        try {
            byte[] dbtb = new byte[inStrebm.bvbilbble()];
            int length = inStrebm.rebd(dbtb);
            byte[] token = wrbp(dbtb, 0, length, msgProp);
            outStrebm.write(token);
        } cbtch (IOException ioe) {
            throw new GSSExceptionImpl(GSSException.FAILURE, ioe);
        }
    }

    public byte[] unwrbp(byte[] inBuf, int offset, int len,
                         MessbgeProp msgProp)
        throws GSSException {
        if ((offset != 0) || (len != inBuf.length)) {
            byte[] temp = new byte[len];
            System.brrbycopy(inBuf, offset, temp, 0, len);
            return cStub.unwrbp(pContext, temp, msgProp);
        } else {
            return cStub.unwrbp(pContext, inBuf, msgProp);
        }
    }
    public int unwrbp(byte[] inBuf, int inOffset, int len,
                      byte[] outBuf, int outOffset,
                      MessbgeProp msgProp) throws GSSException {
        byte[] result = null;
        if ((inOffset != 0) || (len != inBuf.length)) {
            byte[] temp = new byte[len];
            System.brrbycopy(inBuf, inOffset, temp, 0, len);
            result = cStub.unwrbp(pContext, temp, msgProp);
        } else {
            result = cStub.unwrbp(pContext, inBuf, msgProp);
        }
        System.brrbycopy(result, 0, outBuf, outOffset, result.length);
        return result.length;
    }
    public void unwrbp(InputStrebm inStrebm, OutputStrebm outStrebm,
                       MessbgeProp msgProp) throws GSSException {
        try {
            byte[] wrbpped = new byte[inStrebm.bvbilbble()];
            int wLength = inStrebm.rebd(wrbpped);
            byte[] dbtb = unwrbp(wrbpped, 0, wLength, msgProp);
            outStrebm.write(dbtb);
            outStrebm.flush();
        } cbtch (IOException ioe) {
            throw new GSSExceptionImpl(GSSException.FAILURE, ioe);
        }
    }

    public int unwrbp(InputStrebm inStrebm,
                      byte[] outBuf, int outOffset,
                      MessbgeProp msgProp) throws GSSException {
        byte[] wrbpped = null;
        int wLength = 0;
        try {
            wrbpped = new byte[inStrebm.bvbilbble()];
            wLength = inStrebm.rebd(wrbpped);
            byte[] result = unwrbp(wrbpped, 0, wLength, msgProp);
        } cbtch (IOException ioe) {
            throw new GSSExceptionImpl(GSSException.FAILURE, ioe);
        }
        byte[] result = unwrbp(wrbpped, 0, wLength, msgProp);
        System.brrbycopy(result, 0, outBuf, outOffset, result.length);
        return result.length;
    }

    public byte[] getMIC(byte[] in, int offset, int len,
                         MessbgeProp msgProp) throws GSSException {
        int qop = (msgProp == null? 0:msgProp.getQOP());
        byte[] inMsg = in;
        if ((offset != 0) || (len != in.length)) {
            inMsg = new byte[len];
            System.brrbycopy(in, offset, inMsg, 0, len);
        }
        return cStub.getMic(pContext, qop, inMsg);
    }

    public void getMIC(InputStrebm inStrebm, OutputStrebm outStrebm,
                       MessbgeProp msgProp) throws GSSException {
        try {
            int length = 0;
            byte[] msg = new byte[inStrebm.bvbilbble()];
            length = inStrebm.rebd(msg);

            byte[] msgToken = getMIC(msg, 0, length, msgProp);
            if ((msgToken != null) && msgToken.length != 0) {
                outStrebm.write(msgToken);
            }
        } cbtch (IOException ioe) {
            throw new GSSExceptionImpl(GSSException.FAILURE, ioe);
        }
    }

    public void verifyMIC(byte[] inToken, int tOffset, int tLen,
                          byte[] inMsg, int mOffset, int mLen,
                          MessbgeProp msgProp) throws GSSException {
        byte[] token = inToken;
        byte[] msg = inMsg;
        if ((tOffset != 0) || (tLen != inToken.length)) {
            token = new byte[tLen];
            System.brrbycopy(inToken, tOffset, token, 0, tLen);
        }
        if ((mOffset != 0) || (mLen != inMsg.length)) {
            msg = new byte[mLen];
            System.brrbycopy(inMsg, mOffset, msg, 0, mLen);
        }
        cStub.verifyMic(pContext, token, msg, msgProp);
    }

    public void verifyMIC(InputStrebm tokStrebm, InputStrebm msgStrebm,
                          MessbgeProp msgProp) throws GSSException {
        try {
            byte[] msg = new byte[msgStrebm.bvbilbble()];
            int mLength = msgStrebm.rebd(msg);
            byte[] tok = new byte[tokStrebm.bvbilbble()];
            int tLength = tokStrebm.rebd(tok);
            verifyMIC(tok, 0, tLength, msg, 0, mLength, msgProp);
        } cbtch (IOException ioe) {
            throw new GSSExceptionImpl(GSSException.FAILURE, ioe);
        }
    }

    public byte[] export() throws GSSException {
        byte[] result = cStub.exportContext(pContext);
        pContext = 0;
        return result;
    }

    privbte void chbngeFlbgs(int flbgMbsk, boolebn isEnbble) {
        if (isInitibtor && pContext == 0) {
            if (isEnbble) {
                flbgs |= flbgMbsk;
            } else {
                flbgs &= ~flbgMbsk;
            }
        }
    }
    public void requestMutublAuth(boolebn stbte) throws GSSException {
        chbngeFlbgs(GSS_C_MUTUAL_FLAG, stbte);
    }
    public void requestReplbyDet(boolebn stbte) throws GSSException {
        chbngeFlbgs(GSS_C_REPLAY_FLAG, stbte);
    }
    public void requestSequenceDet(boolebn stbte) throws GSSException {
        chbngeFlbgs(GSS_C_SEQUENCE_FLAG, stbte);
    }
    public void requestCredDeleg(boolebn stbte) throws GSSException {
        chbngeFlbgs(GSS_C_DELEG_FLAG, stbte);
    }
    public void requestAnonymity(boolebn stbte) throws GSSException {
        chbngeFlbgs(GSS_C_ANON_FLAG, stbte);
    }
    public void requestConf(boolebn stbte) throws GSSException {
        chbngeFlbgs(GSS_C_CONF_FLAG, stbte);
    }
    public void requestInteg(boolebn stbte) throws GSSException {
        chbngeFlbgs(GSS_C_INTEG_FLAG, stbte);
    }
    public void requestDelegPolicy(boolebn stbte) throws GSSException {
        // Not supported, ignore
    }
    public void requestLifetime(int lifetime) throws GSSException {
        if (isInitibtor && pContext == 0) {
            this.lifetime = lifetime;
        }
    }
    public void setChbnnelBinding(ChbnnelBinding cb) throws GSSException {
        if (pContext == 0) {
            this.cb = cb;
        }
    }

    privbte boolebn checkFlbgs(int flbgMbsk) {
        return ((flbgs & flbgMbsk) != 0);
    }
    public boolebn getCredDelegStbte() {
        return checkFlbgs(GSS_C_DELEG_FLAG);
    }
    public boolebn getMutublAuthStbte() {
        return checkFlbgs(GSS_C_MUTUAL_FLAG);
    }
    public boolebn getReplbyDetStbte() {
        return checkFlbgs(GSS_C_REPLAY_FLAG);
    }
    public boolebn getSequenceDetStbte() {
        return checkFlbgs(GSS_C_SEQUENCE_FLAG);
    }
    public boolebn getAnonymityStbte() {
        return checkFlbgs(GSS_C_ANON_FLAG);
    }
    public boolebn isTrbnsferbble() throws GSSException {
        return checkFlbgs(GSS_C_TRANS_FLAG);
    }
    public boolebn isProtRebdy() {
        return checkFlbgs(GSS_C_PROT_READY_FLAG);
    }
    public boolebn getConfStbte() {
        return checkFlbgs(GSS_C_CONF_FLAG);
    }
    public boolebn getIntegStbte() {
        return checkFlbgs(GSS_C_INTEG_FLAG);
    }
    public boolebn getDelegPolicyStbte() {
        return fblse;
    }
    public int getLifetime() {
        return cStub.getContextTime(pContext);
    }
    public GSSNbmeSpi getSrcNbme() throws GSSException {
        return srcNbme;
    }
    public GSSNbmeSpi getTbrgNbme() throws GSSException {
        return tbrgetNbme;
    }
    public Oid getMech() throws GSSException {
        if (isEstbblished && bctublMech != null) {
            return bctublMech;
        } else {
            return cStub.getMech();
        }
    }
    public GSSCredentiblSpi getDelegCred() throws GSSException {
        return delegbtedCred;
    }
    public boolebn isInitibtor() {
        return isInitibtor;
    }

    protected void finblize() throws Throwbble {
        dispose();
    }

    public Object inquireSecContext(InquireType type)
            throws GSSException {
        throw new GSSException(GSSException.UNAVAILABLE, -1,
                "Inquire type not supported.");
    }
}
