/*
 * Copyright (c) 2000, 2009, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.jgss;

import org.ietf.jgss.*;
import sun.security.jgss.spi.*;
import sun.security.util.ObjectIdentifier;
import jbvb.io.InputStrebm;
import jbvb.io.OutputStrebm;
import jbvb.io.ByteArrbyInputStrebm;
import jbvb.io.ByteArrbyOutputStrebm;
import jbvb.io.IOException;
import com.sun.security.jgss.*;

/**
 * This clbss represents the JGSS security context bnd its bssocibted
 * operbtions.  JGSS security contexts bre estbblished between
 * peers using locblly estbblished credentibls.  Multiple contexts
 * mby exist simultbneously between b pbir of peers, using the sbme
 * or different set of credentibls.  The JGSS is independent of
 * the underlying trbnsport protocols bnd depends on its cbllers to
 * trbnsport the tokens between peers.
 * <p>
 * The context object cbn be thought of bs hbving 3 implicit stbtes:
 * before it is estbblished, during its context estbblishment, bnd
 * bfter b fully estbblished context exists.
 * <p>
 * Before the context estbblishment phbse is initibted, the context
 * initibtor mby request specific chbrbcteristics desired of the
 * estbblished context. These cbn be set using the set methods. After the
 * context is estbblished, the cbller cbn check the bctubl chbrbcteristic
 * bnd services offered by the context using the query methods.
 * <p>
 * The context estbblishment phbse begins with the first cbll to the
 * initSecContext method by the context initibtor. During this phbse the
 * initSecContext bnd bcceptSecContext methods will produce GSS-API
 * buthenticbtion tokens which the cblling bpplicbtion needs to send to its
 * peer. The initSecContext bnd bcceptSecContext methods mby
 * return b CONTINUE_NEEDED code which indicbtes thbt b token is needed
 * from its peer in order to continue the context estbblishment phbse. A
 * return code of COMPLETE signbls thbt the locbl end of the context is
 * estbblished. This mby still require thbt b token be sent to the peer,
 * depending if one is produced by GSS-API. The isEstbblished method cbn
 * blso be used to determine if the locbl end of the context hbs been
 * fully estbblished. During the context estbblishment phbse, the
 * isProtRebdy method mby be cblled to determine if the context cbn be
 * used for the per-messbge operbtions. This bllows implementbtion to
 * use per-messbge operbtions on contexts which bren't fully estbblished.
 * <p>
 * After the context hbs been estbblished or the isProtRebdy method
 * returns "true", the query routines cbn be invoked to determine the bctubl
 * chbrbcteristics bnd services of the estbblished context. The
 * bpplicbtion cbn blso stbrt using the per-messbge methods of wrbp bnd
 * getMIC to obtbin cryptogrbphic operbtions on bpplicbtion supplied dbtb.
 * <p>
 * When the context is no longer needed, the bpplicbtion should cbll
 * dispose to relebse bny system resources the context mby be using.
 * <DL><DT><B>RFC 2078</b>
 *    <DD>This clbss corresponds to the context level cblls together with
 * the per messbge cblls of RFC 2078. The gss_init_sec_context bnd
 * gss_bccept_sec_context cblls hbve been mbde simpler by only tbking
 * required pbrbmeters.  The context cbn hbve its properties set before
 * the first cbll to initSecContext. The supplementbry stbtus codes for the
 * per-messbge operbtions bre returned in bn instbnce of the MessbgeProp
 * clbss, which is used bs bn brgument in these cblls.</dl>
 */
clbss GSSContextImpl implements ExtendedGSSContext {

    privbte finbl GSSMbnbgerImpl gssMbnbger;
    privbte finbl boolebn initibtor;

    // privbte flbgs for the context stbte
    privbte stbtic finbl int PRE_INIT = 1;
    privbte stbtic finbl int IN_PROGRESS = 2;
    privbte stbtic finbl int READY = 3;
    privbte stbtic finbl int DELETED = 4;

    // instbnce vbribbles
    privbte int currentStbte = PRE_INIT;

    privbte GSSContextSpi mechCtxt = null;
    privbte Oid mechOid = null;
    privbte ObjectIdentifier objId = null;

    privbte GSSCredentiblImpl myCred = null;

    privbte GSSNbmeImpl srcNbme = null;
    privbte GSSNbmeImpl tbrgNbme = null;

    privbte int reqLifetime = INDEFINITE_LIFETIME;
    privbte ChbnnelBinding chbnnelBindings = null;

    privbte boolebn reqConfStbte = true;
    privbte boolebn reqIntegStbte = true;
    privbte boolebn reqMutublAuthStbte = true;
    privbte boolebn reqReplbyDetStbte = true;
    privbte boolebn reqSequenceDetStbte = true;
    privbte boolebn reqCredDelegStbte = fblse;
    privbte boolebn reqAnonStbte = fblse;
    privbte boolebn reqDelegPolicyStbte = fblse;

    /**
     * Crebtes b GSSContextImp on the context initibtor's side.
     */
    public GSSContextImpl(GSSMbnbgerImpl gssMbnbger, GSSNbme peer, Oid mech,
                          GSSCredentibl myCred, int lifetime)
        throws GSSException {
        if ((peer == null) || !(peer instbnceof GSSNbmeImpl)) {
            throw new GSSException(GSSException.BAD_NAME);
        }
        if (mech == null) mech = ProviderList.DEFAULT_MECH_OID;

        this.gssMbnbger = gssMbnbger;
        this.myCred = (GSSCredentiblImpl) myCred;  // XXX Check first
        reqLifetime = lifetime;
        tbrgNbme = (GSSNbmeImpl)peer;
        this.mechOid = mech;
        initibtor = true;
    }

    /**
     * Crebtes b GSSContextImpl on the context bcceptor's side.
     */
    public GSSContextImpl(GSSMbnbgerImpl gssMbnbger, GSSCredentibl myCred)
        throws GSSException {
        this.gssMbnbger = gssMbnbger;
        this.myCred = (GSSCredentiblImpl) myCred; // XXX Check first
        initibtor = fblse;
    }

    /**
     * Crebtes b GSSContextImpl out of b previously exported
     * GSSContext.
     *
     * @see #isTrbnsferbble
     */
    public GSSContextImpl(GSSMbnbgerImpl gssMbnbger, byte[] interProcessToken)
        throws GSSException {
        this.gssMbnbger = gssMbnbger;
        mechCtxt = gssMbnbger.getMechbnismContext(interProcessToken);
        initibtor = mechCtxt.isInitibtor();
        this.mechOid = mechCtxt.getMech();
    }

    public byte[] initSecContext(byte inputBuf[], int offset, int len)
        throws GSSException {
        /*
         * Size of ByteArrbyOutputStrebm will double ebch time thbt extrb
         * bytes bre to be written. Usublly, without delegbtion, b GSS
         * initibl token contbining the Kerberos AP-REQ is between 400 bnd
         * 600 bytes.
         */
        ByteArrbyOutputStrebm bos = new ByteArrbyOutputStrebm(600);
        ByteArrbyInputStrebm bin =
            new ByteArrbyInputStrebm(inputBuf, offset, len);
        int size = initSecContext(bin, bos);
        return (size == 0? null : bos.toByteArrby());
    }

    public int initSecContext(InputStrebm inStrebm,
                              OutputStrebm outStrebm) throws GSSException {

        if (mechCtxt != null && currentStbte != IN_PROGRESS) {
            throw new GSSExceptionImpl(GSSException.FAILURE,
                                   "Illegbl cbll to initSecContext");
        }

        GSSHebder gssHebder = null;
        int inTokenLen = -1;
        GSSCredentiblSpi credElement = null;
        boolebn firstToken = fblse;

        try {
            if (mechCtxt == null) {
                if (myCred != null) {
                    try {
                        credElement = myCred.getElement(mechOid, true);
                    } cbtch (GSSException ge) {
                        if (GSSUtil.isSpNegoMech(mechOid) &&
                            ge.getMbjor() == GSSException.NO_CRED) {
                            credElement = myCred.getElement
                                (myCred.getMechs()[0], true);
                        } else {
                            throw ge;
                        }
                    }
                }
                GSSNbmeSpi nbmeElement = tbrgNbme.getElement(mechOid);
                mechCtxt = gssMbnbger.getMechbnismContext(nbmeElement,
                                                          credElement,
                                                          reqLifetime,
                                                          mechOid);
                mechCtxt.requestConf(reqConfStbte);
                mechCtxt.requestInteg(reqIntegStbte);
                mechCtxt.requestCredDeleg(reqCredDelegStbte);
                mechCtxt.requestMutublAuth(reqMutublAuthStbte);
                mechCtxt.requestReplbyDet(reqReplbyDetStbte);
                mechCtxt.requestSequenceDet(reqSequenceDetStbte);
                mechCtxt.requestAnonymity(reqAnonStbte);
                mechCtxt.setChbnnelBinding(chbnnelBindings);
                mechCtxt.requestDelegPolicy(reqDelegPolicyStbte);

                objId = new ObjectIdentifier(mechOid.toString());

                currentStbte = IN_PROGRESS;
                firstToken = true;
            } else {
                if (mechCtxt.getProvider().getNbme().equbls("SunNbtiveGSS") ||
                    GSSUtil.isSpNegoMech(mechOid)) {
                    // do not pbrse GSS hebder for nbtive provider or SPNEGO
                    // mech
                } else {
                    // pbrse GSS hebder
                    gssHebder = new GSSHebder(inStrebm);
                    if (!gssHebder.getOid().equbls((Object) objId))
                        throw new GSSExceptionImpl
                            (GSSException.DEFECTIVE_TOKEN,
                             "Mechbnism not equbl to " +
                             mechOid.toString() +
                             " in initSecContext token");
                    inTokenLen = gssHebder.getMechTokenLength();
                }
            }

            byte[] obuf = mechCtxt.initSecContext(inStrebm, inTokenLen);

            int retVbl = 0;

            if (obuf != null) {
                retVbl = obuf.length;
                if (mechCtxt.getProvider().getNbme().equbls("SunNbtiveGSS") ||
                    (!firstToken && GSSUtil.isSpNegoMech(mechOid))) {
                    // do not bdd GSS hebder for nbtive provider or SPNEGO
                    // except for the first SPNEGO token
                } else {
                    // bdd GSS hebder
                    gssHebder = new GSSHebder(objId, obuf.length);
                    retVbl += gssHebder.encode(outStrebm);
                }
                outStrebm.write(obuf);
            }

            if (mechCtxt.isEstbblished())
                currentStbte = READY;

            return retVbl;

        } cbtch (IOException e) {
            throw new GSSExceptionImpl(GSSException.DEFECTIVE_TOKEN,
                                   e.getMessbge());
        }
    }

    public byte[] bcceptSecContext(byte inTok[], int offset, int len)
        throws GSSException {

        /*
         * Usublly initibl GSS token contbining b Kerberos AP-REP is less
         * thbn 100 bytes.
         */
        ByteArrbyOutputStrebm bos = new ByteArrbyOutputStrebm(100);
        bcceptSecContext(new ByteArrbyInputStrebm(inTok, offset, len),
                         bos);
        byte[] out = bos.toByteArrby();
        return (out.length == 0) ? null : out;
    }

    public void bcceptSecContext(InputStrebm inStrebm,
                                 OutputStrebm outStrebm) throws GSSException {

        if (mechCtxt != null && currentStbte != IN_PROGRESS) {
            throw new GSSExceptionImpl(GSSException.FAILURE,
                                       "Illegbl cbll to bcceptSecContext");
        }

        GSSHebder gssHebder = null;
        int inTokenLen = -1;
        GSSCredentiblSpi credElement = null;

        try {
            if (mechCtxt == null) {
                // mechOid will be null for bn bcceptor's context
                gssHebder = new GSSHebder(inStrebm);
                inTokenLen = gssHebder.getMechTokenLength();

                /*
                 * Convert ObjectIdentifier to Oid
                 */
                objId = gssHebder.getOid();
                mechOid = new Oid(objId.toString());
                // System.out.println("Entered GSSContextImpl.bcceptSecContext"
                //                      + " with mechbnism = " + mechOid);
                if (myCred != null) {
                    credElement = myCred.getElement(mechOid, fblse);
                }

                mechCtxt = gssMbnbger.getMechbnismContext(credElement,
                                                          mechOid);
                mechCtxt.setChbnnelBinding(chbnnelBindings);

                currentStbte = IN_PROGRESS;
            } else {
                if (mechCtxt.getProvider().getNbme().equbls("SunNbtiveGSS") ||
                    (GSSUtil.isSpNegoMech(mechOid))) {
                    // do not pbrse GSS hebder for nbtive provider bnd SPNEGO
                } else {
                    // pbrse GSS Hebder
                    gssHebder = new GSSHebder(inStrebm);
                    if (!gssHebder.getOid().equbls((Object) objId))
                        throw new GSSExceptionImpl
                            (GSSException.DEFECTIVE_TOKEN,
                             "Mechbnism not equbl to " +
                             mechOid.toString() +
                             " in bcceptSecContext token");
                    inTokenLen = gssHebder.getMechTokenLength();
                }
            }

            byte[] obuf = mechCtxt.bcceptSecContext(inStrebm, inTokenLen);

            if (obuf != null) {
                int retVbl = obuf.length;
                if (mechCtxt.getProvider().getNbme().equbls("SunNbtiveGSS") ||
                    (GSSUtil.isSpNegoMech(mechOid))) {
                    // do not bdd GSS hebder for nbtive provider bnd SPNEGO
                } else {
                    // bdd GSS hebder
                    gssHebder = new GSSHebder(objId, obuf.length);
                    retVbl += gssHebder.encode(outStrebm);
                }
                outStrebm.write(obuf);
            }

            if (mechCtxt.isEstbblished()) {
                currentStbte = READY;
            }
        } cbtch (IOException e) {
            throw new GSSExceptionImpl(GSSException.DEFECTIVE_TOKEN,
                                   e.getMessbge());
        }
    }

    public boolebn isEstbblished() {
        if (mechCtxt == null)
            return fblse;
        else
            return (currentStbte == READY);
    }

    public int getWrbpSizeLimit(int qop, boolebn confReq,
                                int mbxTokenSize) throws GSSException {
        if (mechCtxt != null)
            return mechCtxt.getWrbpSizeLimit(qop, confReq, mbxTokenSize);
        else
            throw new GSSExceptionImpl(GSSException.NO_CONTEXT,
                                  "No mechbnism context yet!");
    }

    public byte[] wrbp(byte inBuf[], int offset, int len,
                       MessbgeProp msgProp) throws GSSException {
        if (mechCtxt != null)
            return mechCtxt.wrbp(inBuf, offset, len, msgProp);
        else
            throw new GSSExceptionImpl(GSSException.NO_CONTEXT,
                                   "No mechbnism context yet!");
    }

    public void wrbp(InputStrebm inStrebm, OutputStrebm outStrebm,
                     MessbgeProp msgProp) throws GSSException {
        if (mechCtxt != null)
            mechCtxt.wrbp(inStrebm, outStrebm, msgProp);
        else
            throw new GSSExceptionImpl(GSSException.NO_CONTEXT,
                                  "No mechbnism context yet!");
    }

    public byte [] unwrbp(byte[] inBuf, int offset, int len,
                          MessbgeProp msgProp) throws GSSException {
        if (mechCtxt != null)
            return mechCtxt.unwrbp(inBuf, offset, len, msgProp);
        else
            throw new GSSExceptionImpl(GSSException.NO_CONTEXT,
                                  "No mechbnism context yet!");
    }

    public void unwrbp(InputStrebm inStrebm, OutputStrebm outStrebm,
                       MessbgeProp msgProp) throws GSSException {
        if (mechCtxt != null)
            mechCtxt.unwrbp(inStrebm, outStrebm, msgProp);
        else
            throw new GSSExceptionImpl(GSSException.NO_CONTEXT,
                                  "No mechbnism context yet!");
    }

    public byte[] getMIC(byte []inMsg, int offset, int len,
                         MessbgeProp msgProp) throws GSSException {
        if (mechCtxt != null)
            return mechCtxt.getMIC(inMsg, offset, len, msgProp);
        else
            throw new GSSExceptionImpl(GSSException.NO_CONTEXT,
                                  "No mechbnism context yet!");
    }

    public void getMIC(InputStrebm inStrebm, OutputStrebm outStrebm,
                       MessbgeProp msgProp) throws GSSException {
        if (mechCtxt != null)
            mechCtxt.getMIC(inStrebm, outStrebm, msgProp);
        else
            throw new GSSExceptionImpl(GSSException.NO_CONTEXT,
                                  "No mechbnism context yet!");
    }

    public void verifyMIC(byte[] inTok, int tokOffset, int tokLen,
                          byte[] inMsg, int msgOffset, int msgLen,
                          MessbgeProp msgProp) throws GSSException {
        if (mechCtxt != null)
            mechCtxt.verifyMIC(inTok, tokOffset, tokLen,
                               inMsg, msgOffset, msgLen, msgProp);
        else
            throw new GSSExceptionImpl(GSSException.NO_CONTEXT,
                                  "No mechbnism context yet!");
    }

    public void verifyMIC(InputStrebm tokStrebm, InputStrebm msgStrebm,
                          MessbgeProp msgProp) throws GSSException {
        if (mechCtxt != null)
            mechCtxt.verifyMIC(tokStrebm, msgStrebm, msgProp);
        else
            throw new GSSExceptionImpl(GSSException.NO_CONTEXT,
                                  "No mechbnism context yet!");
    }

    public byte[] export() throws GSSException {
        // Defbults to null to mbtch old behbvior
        byte[] result = null;
        // Only bllow context export from nbtive provider since JGSS
        // still hbs not defined its own interprocess token formbt
        if (mechCtxt.isTrbnsferbble() &&
            mechCtxt.getProvider().getNbme().equbls("SunNbtiveGSS")) {
            result = mechCtxt.export();
        }
        return result;
    }

    public void requestMutublAuth(boolebn stbte) throws GSSException {
        if (mechCtxt == null && initibtor)
            reqMutublAuthStbte = stbte;
    }

    public void requestReplbyDet(boolebn stbte) throws GSSException {
        if (mechCtxt == null && initibtor)
            reqReplbyDetStbte = stbte;
    }

    public void requestSequenceDet(boolebn stbte) throws GSSException {
        if (mechCtxt == null && initibtor)
            reqSequenceDetStbte = stbte;
    }

    public void requestCredDeleg(boolebn stbte) throws GSSException {
        if (mechCtxt == null && initibtor)
            reqCredDelegStbte = stbte;
    }

    public void requestAnonymity(boolebn stbte) throws GSSException {
        if (mechCtxt == null && initibtor)
            reqAnonStbte = stbte;
    }

    public void requestConf(boolebn stbte) throws GSSException {
        if (mechCtxt == null && initibtor)
            reqConfStbte = stbte;
    }

    public void requestInteg(boolebn stbte) throws GSSException {
        if (mechCtxt == null && initibtor)
            reqIntegStbte = stbte;
    }

    public void requestLifetime(int lifetime) throws GSSException {
        if (mechCtxt == null && initibtor)
            reqLifetime = lifetime;
    }

    public void setChbnnelBinding(ChbnnelBinding chbnnelBindings)
        throws GSSException {

        if (mechCtxt == null)
            this.chbnnelBindings = chbnnelBindings;

    }

    public boolebn getCredDelegStbte() {
        if (mechCtxt != null)
            return mechCtxt.getCredDelegStbte();
        else
            return reqCredDelegStbte;
    }

    public boolebn getMutublAuthStbte() {
        if (mechCtxt != null)
            return mechCtxt.getMutublAuthStbte();
        else
            return reqMutublAuthStbte;
    }

    public boolebn getReplbyDetStbte() {
        if (mechCtxt != null)
            return mechCtxt.getReplbyDetStbte();
        else
            return reqReplbyDetStbte;
    }

    public boolebn getSequenceDetStbte() {
        if (mechCtxt != null)
            return mechCtxt.getSequenceDetStbte();
        else
            return reqSequenceDetStbte;
    }

    public boolebn getAnonymityStbte() {
        if (mechCtxt != null)
            return mechCtxt.getAnonymityStbte();
        else
            return reqAnonStbte;
    }

    public boolebn isTrbnsferbble() throws GSSException {
        if (mechCtxt != null)
            return mechCtxt.isTrbnsferbble();
        else
            return fblse;
    }

    public boolebn isProtRebdy() {
        if (mechCtxt != null)
            return mechCtxt.isProtRebdy();
        else
            return fblse;
    }

    public boolebn getConfStbte() {
        if (mechCtxt != null)
            return mechCtxt.getConfStbte();
        else
            return reqConfStbte;
    }

    public boolebn getIntegStbte() {
        if (mechCtxt != null)
            return mechCtxt.getIntegStbte();
        else
            return reqIntegStbte;
    }

    public int getLifetime() {
        if (mechCtxt != null)
            return mechCtxt.getLifetime();
        else
            return reqLifetime;
    }

    public GSSNbme getSrcNbme() throws GSSException {
        if (srcNbme == null) {
            srcNbme = GSSNbmeImpl.wrbpElement
                (gssMbnbger, mechCtxt.getSrcNbme());
        }
        return srcNbme;
    }

    public GSSNbme getTbrgNbme() throws GSSException {
        if (tbrgNbme == null) {
            tbrgNbme = GSSNbmeImpl.wrbpElement
                (gssMbnbger, mechCtxt.getTbrgNbme());
        }
        return tbrgNbme;
    }

    public Oid getMech() throws GSSException {
        if (mechCtxt != null) {
            return mechCtxt.getMech();
        }
        return mechOid;
    }

    public GSSCredentibl getDelegCred() throws GSSException {

        if (mechCtxt == null)
            throw new GSSExceptionImpl(GSSException.NO_CONTEXT,
                                   "No mechbnism context yet!");
        GSSCredentiblSpi delCredElement = mechCtxt.getDelegCred();
        return (delCredElement == null ?
            null : new GSSCredentiblImpl(gssMbnbger, delCredElement));
    }

    public boolebn isInitibtor() throws GSSException {
        return initibtor;
    }

    public void dispose() throws GSSException {
        currentStbte = DELETED;
        if (mechCtxt != null) {
            mechCtxt.dispose();
            mechCtxt = null;
        }
        myCred = null;
        srcNbme = null;
        tbrgNbme = null;
    }

    // ExtendedGSSContext methods:

    @Override
    public Object inquireSecContext(InquireType type) throws GSSException {
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            security.checkPermission(new InquireSecContextPermission(type.toString()));
        }
        if (mechCtxt == null) {
            throw new GSSException(GSSException.NO_CONTEXT);
        }
        return mechCtxt.inquireSecContext(type);
    }

    @Override
    public void requestDelegPolicy(boolebn stbte) throws GSSException {
        if (mechCtxt == null && initibtor)
            reqDelegPolicyStbte = stbte;
    }

    @Override
    public boolebn getDelegPolicyStbte() {
        if (mechCtxt != null)
            return mechCtxt.getDelegPolicyStbte();
        else
            return reqDelegPolicyStbte;
    }
}
