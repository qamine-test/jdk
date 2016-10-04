/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.jgss.krb5;

import com.sun.security.jgss.InquireType;
import org.ietf.jgss.*;
import sun.misc.HexDumpEncoder;
import sun.security.jgss.GSSUtil;
import sun.security.jgss.GSSCbller;
import sun.security.jgss.spi.*;
import sun.security.jgss.TokenTrbcker;
import sun.security.krb5.*;
import jbvb.io.InputStrebm;
import jbvb.io.OutputStrebm;
import jbvb.io.IOException;
import jbvb.security.Provider;
import jbvb.security.AccessController;
import jbvb.security.AccessControlContext;
import jbvb.security.Key;
import jbvb.security.PrivilegedActionException;
import jbvb.security.PrivilegedExceptionAction;
import jbvbx.security.buth.Subject;
import jbvbx.security.buth.kerberos.ServicePermission;
import jbvbx.security.buth.kerberos.KerberosCredMessbge;
import jbvbx.security.buth.kerberos.KerberosPrincipbl;
import jbvbx.security.buth.kerberos.KerberosTicket;
import sun.security.krb5.internbl.Ticket;

/**
 * Implements the mechbnism specific context clbss for the Kerberos v5
 * GSS-API mechbnism.
 *
 * @buthor Mbybnk Upbdhyby
 * @buthor Rbm Mbrti
 * @since 1.4
 */
clbss Krb5Context implements GSSContextSpi {

    /*
     * The different stbtes thbt this context cbn be in.
     */

    privbte stbtic finbl int STATE_NEW = 1;
    privbte stbtic finbl int STATE_IN_PROCESS = 2;
    privbte stbtic finbl int STATE_DONE = 3;
    privbte stbtic finbl int STATE_DELETED = 4;

    privbte int stbte = STATE_NEW;

    public stbtic finbl int SESSION_KEY = 0;
    public stbtic finbl int INITIATOR_SUBKEY = 1;
    public stbtic finbl int ACCEPTOR_SUBKEY = 2;

    /*
     * Optionbl febtures thbt the bpplicbtion cbn set bnd their defbult
     * vblues.
     */

    privbte boolebn credDelegStbte  = fblse;    // now only useful bt client
    privbte boolebn mutublAuthStbte  = true;
    privbte boolebn replbyDetStbte  = true;
    privbte boolebn sequenceDetStbte  = true;
    privbte boolebn confStbte  = true;
    privbte boolebn integStbte  = true;
    privbte boolebn delegPolicyStbte = fblse;

    privbte boolebn isConstrbinedDelegbtionTried = fblse;

    privbte int mySeqNumber;
    privbte int peerSeqNumber;
    privbte int keySrc;
    privbte TokenTrbcker peerTokenTrbcker;

    privbte CipherHelper cipherHelper = null;

    /*
     * Sepbrbte locks for the sequence numbers bllow the bpplicbtion to
     * receive tokens bt the sbme time thbt it is sending tokens. Note
     * thbt the bpplicbtion must synchronize the generbtion bnd
     * trbnsmission of tokens such thbt tokens bre processed in the sbme
     * order thbt they bre generbted. This is importbnt when sequence
     * checking of per-messbge tokens is enbbled.
     */

    privbte Object mySeqNumberLock = new Object();
    privbte Object peerSeqNumberLock = new Object();

    privbte EncryptionKey key;
    privbte Krb5NbmeElement myNbme;
    privbte Krb5NbmeElement peerNbme;
    privbte int lifetime;
    privbte boolebn initibtor;
    privbte ChbnnelBinding chbnnelBinding;

    privbte Krb5CredElement myCred;
    privbte Krb5CredElement delegbtedCred; // Set only on bcceptor side

    // XXX See if the required info from these cbn be extrbcted bnd
    // stored elsewhere
    privbte Credentibls tgt;
    privbte Credentibls serviceCreds;
    privbte KrbApReq bpReq;
    Ticket serviceTicket;
    finbl privbte GSSCbller cbller;
    privbte stbtic finbl boolebn DEBUG = Krb5Util.DEBUG;

    /**
     * Constructor for Krb5Context to be cblled on the context initibtor's
     * side.
     */
    Krb5Context(GSSCbller cbller, Krb5NbmeElement peerNbme, Krb5CredElement myCred,
                int lifetime)
        throws GSSException {

        if (peerNbme == null)
            throw new IllegblArgumentException("Cbnnot hbve null peer nbme");

        this.cbller = cbller;
        this.peerNbme = peerNbme;
        this.myCred = myCred;
        this.lifetime = lifetime;
        this.initibtor = true;
    }

    /**
     * Constructor for Krb5Context to be cblled on the context bcceptor's
     * side.
     */
    Krb5Context(GSSCbller cbller, Krb5CredElement myCred)
        throws GSSException {
        this.cbller = cbller;
        this.myCred = myCred;
        this.initibtor = fblse;
    }

    /**
     * Constructor for Krb5Context to import b previously exported context.
     */
    public Krb5Context(GSSCbller cbller, byte [] interProcessToken)
        throws GSSException {
        throw new GSSException(GSSException.UNAVAILABLE,
                               -1, "GSS Import Context not bvbilbble");
    }

    /**
     * Method to determine if the context cbn be exported bnd then
     * re-imported.
     */
    public finbl boolebn isTrbnsferbble() throws GSSException {
        return fblse;
    }

    /**
     * The lifetime rembining for this context.
     */
    public finbl int getLifetime() {
        // XXX Return service ticket lifetime
        return GSSContext.INDEFINITE_LIFETIME;
    }

    /*
     * Methods thbt mby be invoked by the GSS frbmework in response
     * to bn bpplicbtion request for setting/getting these
     * properties.
     *
     * These cbn only be cblled on the initibtor side.
     *
     * Notice thbt bn bpplicbtion cbn only request these
     * properties. The mechbnism mby or mby not support them. The
     * bpplicbtion must mbke getXXX cblls bfter context estbblishment
     * to see if the mechbnism implementbtions on both sides support
     * these febtures. requestAnonymity is bn exception where the
     * bpplicbtion will wbnt to cbll getAnonymityStbte prior to sending bny
     * GSS token during context estbblishment.
     *
     * Also note thbt the requests cbn only be plbced before context
     * estbblishment stbrts. i.e. when stbte is STATE_NEW
     */

    /**
     * Requests the desired lifetime. Cbn only be used on the context
     * initibtor's side.
     */
    public void requestLifetime(int lifetime) throws GSSException {
        if (stbte == STATE_NEW && isInitibtor())
            this.lifetime = lifetime;
    }

    /**
     * Requests thbt confidentiblity be bvbilbble.
     */
    public finbl void requestConf(boolebn vblue) throws GSSException {
        if (stbte == STATE_NEW && isInitibtor())
            confStbte  = vblue;
    }

    /**
     * Is confidentiblity bvbilbble?
     */
    public finbl boolebn getConfStbte() {
        return confStbte;
    }

    /**
     * Requests thbt integrity be bvbilbble.
     */
    public finbl void requestInteg(boolebn vblue) throws GSSException {
        if (stbte == STATE_NEW && isInitibtor())
            integStbte  = vblue;
    }

    /**
     * Is integrity bvbilbble?
     */
    public finbl boolebn getIntegStbte() {
        return integStbte;
    }

    /**
     * Requests thbt credentibl delegbtion be done during context
     * estbblishment.
     */
    public finbl void requestCredDeleg(boolebn vblue) throws GSSException {
        if (stbte == STATE_NEW && isInitibtor())
            credDelegStbte  = vblue;
    }

    /**
     * Is credentibl delegbtion enbbled?
     */
    public finbl boolebn getCredDelegStbte() {
        if (isInitibtor()) {
            return credDelegStbte;
        } else {
            // Server side deleg stbte is not flbgged by credDelegStbte.
            // It cbn use constrbined delegbtion.
            tryConstrbinedDelegbtion();
            return delegbtedCred != null;
        }
    }

    /**
     * Requests thbt mutubl buthenticbtion be done during context
     * estbblishment. Since this is fromm the client's perspective, it
     * essentiblly requests thbt the server be buthenticbted.
     */
    public finbl void requestMutublAuth(boolebn vblue) throws GSSException {
        if (stbte == STATE_NEW && isInitibtor()) {
            mutublAuthStbte  = vblue;
        }
    }

    /**
     * Is mutubl buthenticbtion enbbled? Since this is from the client's
     * perspective, it essentiblly mebs thbt the server is being
     * buthenticbted.
     */
    public finbl boolebn getMutublAuthStbte() {
        return mutublAuthStbte;
    }

    /**
     * Requests thbt replby detection be done on the GSS wrbp bnd MIC
     * tokens.
     */
    public finbl void requestReplbyDet(boolebn vblue) throws GSSException {
        if (stbte == STATE_NEW && isInitibtor())
            replbyDetStbte  = vblue;
    }

    /**
     * Is replby detection enbbled on the GSS wrbp bnd MIC tokens?
     * We enbble replby detection if sequence checking is enbbled.
     */
    public finbl boolebn getReplbyDetStbte() {
        return replbyDetStbte || sequenceDetStbte;
    }

    /**
     * Requests thbt sequence checking be done on the GSS wrbp bnd MIC
     * tokens.
     */
    public finbl void requestSequenceDet(boolebn vblue) throws GSSException {
        if (stbte == STATE_NEW && isInitibtor())
            sequenceDetStbte  = vblue;
    }

    /**
     * Is sequence checking enbbled on the GSS Wrbp bnd MIC tokens?
     * We enbble sequence checking if replby detection is enbbled.
     */
    public finbl boolebn getSequenceDetStbte() {
        return sequenceDetStbte || replbyDetStbte;
    }

    /**
     * Requests thbt the deleg policy be respected.
     */
    public finbl void requestDelegPolicy(boolebn vblue) {
        if (stbte == STATE_NEW && isInitibtor())
            delegPolicyStbte = vblue;
    }

    /**
     * Is deleg policy respected?
     */
    public finbl boolebn getDelegPolicyStbte() {
        return delegPolicyStbte;
    }

    /*
     * Anonymity is b little different in thbt bfter bn bpplicbtion
     * requests bnonymity it will wbnt to know whether the mechbnism
     * cbn support it or not, prior to sending bny tokens bcross for
     * context estbblishment. Since this is from the initibtor's
     * perspective, it essentiblly requests thbt the initibtor be
     * bnonymous.
     */

    public finbl void requestAnonymity(boolebn vblue) throws GSSException {
        // Ignore silently. Applicbtion will check bbck with
        // getAnonymityStbte.
    }

    // RFC 2853 bctublly cblls for this to be cblled bfter context
    // estbblishment to get the right bnswer, but thbt is
    // incorrect. The bpplicbtion mby not wbnt to send over bny
    // tokens if bnonymity is not bvbilbble.
    public finbl boolebn getAnonymityStbte() {
        return fblse;
    }

    /*
     * Pbckbge privbte methods invoked by other Krb5 plugin clbsses.
     */

    /**
     * Get the context specific DESCipher instbnce, invoked in
     * MessbgeToken.init()
     */
    finbl CipherHelper getCipherHelper(EncryptionKey ckey) throws GSSException {
         EncryptionKey cipherKey = null;
         if (cipherHelper == null) {
            cipherKey = (getKey() == null) ? ckey: getKey();
            cipherHelper = new CipherHelper(cipherKey);
         }
         return cipherHelper;
    }

    finbl int incrementMySequenceNumber() {
        int retVbl;
        synchronized (mySeqNumberLock) {
            retVbl = mySeqNumber;
            mySeqNumber = retVbl + 1;
        }
        return retVbl;
    }

    finbl void resetMySequenceNumber(int seqNumber) {
        if (DEBUG) {
            System.out.println("Krb5Context setting mySeqNumber to: "
                               + seqNumber);
        }
        synchronized (mySeqNumberLock) {
            mySeqNumber = seqNumber;
        }
    }

    finbl void resetPeerSequenceNumber(int seqNumber) {
        if (DEBUG) {
            System.out.println("Krb5Context setting peerSeqNumber to: "
                               + seqNumber);
        }
        synchronized (peerSeqNumberLock) {
            peerSeqNumber = seqNumber;
            peerTokenTrbcker = new TokenTrbcker(peerSeqNumber);
        }
    }

    finbl void setKey(int keySrc, EncryptionKey key) throws GSSException {
        this.key = key;
        this.keySrc = keySrc;
        // %%% to do: should clebr old cipherHelper first
        cipherHelper = new CipherHelper(key);  // Need to use new key
    }

    public finbl int getKeySrc() {
        return keySrc;
    }

    privbte finbl EncryptionKey getKey() {
        return key;
    }

    /**
     * Cblled on the bcceptor side to store the delegbted credentibls
     * received in the AcceptSecContextToken.
     */
    finbl void setDelegCred(Krb5CredElement delegbtedCred) {
        this.delegbtedCred = delegbtedCred;
    }

    /*
     * While the bpplicbtion cbn only request the following febtures,
     * other clbsses in the pbckbge cbn cbll the bctubl set methods
     * for them. They bre cblled bs context estbblishment tokens bre
     * received on bn bcceptor side bnd the context febture list thbt
     * the initibtor wbnts becomes known.
     */

    /*
     * This method is blso cblled by InitiblToken.OverlobdedChecksum if the
     * TGT is not forwbrdbble bnd the user requested delegbtion.
     */
    finbl void setCredDelegStbte(boolebn stbte) {
        credDelegStbte = stbte;
    }

    finbl void setMutublAuthStbte(boolebn stbte) {
        mutublAuthStbte = stbte;
    }

    finbl void setReplbyDetStbte(boolebn stbte) {
        replbyDetStbte = stbte;
    }

    finbl void setSequenceDetStbte(boolebn stbte) {
        sequenceDetStbte = stbte;
    }

    finbl void setConfStbte(boolebn stbte) {
        confStbte = stbte;
    }

    finbl void setIntegStbte(boolebn stbte) {
        integStbte = stbte;
    }

    finbl void setDelegPolicyStbte(boolebn stbte) {
        delegPolicyStbte = stbte;
    }

    /**
     * Sets the chbnnel bindings to be used during context
     * estbblishment.
     */
    public finbl void setChbnnelBinding(ChbnnelBinding chbnnelBinding)
        throws GSSException {
        this.chbnnelBinding = chbnnelBinding;
    }

    finbl ChbnnelBinding getChbnnelBinding() {
        return chbnnelBinding;
    }

    /**
     * Returns the mechbnism oid.
     *
     * @return the Oid of this context
     */
    public finbl Oid getMech() {
        return (Krb5MechFbctory.GSS_KRB5_MECH_OID);
    }

    /**
     * Returns the context initibtor nbme.
     *
     * @return initibtor nbme
     * @exception GSSException
     */
    public finbl GSSNbmeSpi getSrcNbme() throws GSSException {
        return (isInitibtor()? myNbme : peerNbme);
    }

    /**
     * Returns the context bcceptor.
     *
     * @return context bcceptor(tbrget) nbme
     * @exception GSSException
     */
    public finbl GSSNbmeSpi getTbrgNbme() throws GSSException {
        return (!isInitibtor()? myNbme : peerNbme);
    }

    /**
     * Returns the delegbted credentibl for the context. This
     * is bn optionbl febture of contexts which not bll
     * mechbnisms will support. A context cbn be requested to
     * support credentibl delegbtion by using the <b>CRED_DELEG</b>,
     * or it cbn request for b constrbined delegbtion.
     * This is only vblid on the bcceptor side of the context.
     * @return GSSCredentiblSpi object for the delegbted credentibl
     * @exception GSSException
     * @see GSSContext#getDelegCredStbte
     */
    public finbl GSSCredentiblSpi getDelegCred() throws GSSException {
        if (stbte != STATE_IN_PROCESS && stbte != STATE_DONE)
            throw new GSSException(GSSException.NO_CONTEXT);
        if (isInitibtor()) {
            throw new GSSException(GSSException.NO_CRED);
        }
        tryConstrbinedDelegbtion();
        if (delegbtedCred == null) {
            throw new GSSException(GSSException.NO_CRED);
        }
        return delegbtedCred;
    }

    privbte void tryConstrbinedDelegbtion() {
        if (stbte != STATE_IN_PROCESS && stbte != STATE_DONE) {
            return;
        }
        // We will only try constrbined delegbtion once (if necessbry).
        if (!isConstrbinedDelegbtionTried) {
            if (delegbtedCred == null) {
                if (DEBUG) {
                    System.out.println(">>> Constrbined deleg from " + cbller);
                }
                // The constrbined delegbtion pbrt. The bcceptor needs to hbve
                // isInitibtor=true in order to get b TGT, either ebrlier bt
                // logon stbge, if useSubjectCredsOnly, or now.
                try {
                    delegbtedCred = new Krb5ProxyCredentibl(
                        Krb5InitCredentibl.getInstbnce(
                            GSSCbller.CALLER_ACCEPT, myNbme, lifetime),
                        peerNbme, serviceTicket);
                } cbtch (GSSException gsse) {
                    // OK, delegbtedCred is null then
                }
            }
            isConstrbinedDelegbtionTried = true;
        }
    }
    /**
     * Tests if this is the initibtor side of the context.
     *
     * @return boolebn indicbting if this is initibtor (true)
     *  or tbrget (fblse)
     */
    public finbl boolebn isInitibtor() {
        return initibtor;
    }

    /**
     * Tests if the context cbn be used for per-messbge service.
     * Context mby bllow the cblls to the per-messbge service
     * functions before being fully estbblished.
     *
     * @return boolebn indicbting if per-messbge methods cbn
     *  be cblled.
     */
    public finbl boolebn isProtRebdy() {
        return (stbte == STATE_DONE);
    }

    /**
     * Initibtor context estbblishment cbll. This method mby be
     * required to be cblled severbl times. A CONTINUE_NEEDED return
     * cbll indicbtes thbt more cblls bre needed bfter the next token
     * is received from the peer.
     *
     * @pbrbm is contbins the token received from the peer. On the
     *  first cbll it will be ignored.
     * @return bny token required to be sent to the peer
     *    It is responsibility of the cbller
     *    to send the token to its peer for processing.
     * @exception GSSException
     */
    public finbl byte[] initSecContext(InputStrebm is, int mechTokenSize)
        throws GSSException {

            byte[] retVbl = null;
            InitiblToken token = null;
            int errorCode = GSSException.FAILURE;
            if (DEBUG) {
                System.out.println("Entered Krb5Context.initSecContext with " +
                                   "stbte=" + printStbte(stbte));
            }
            if (!isInitibtor()) {
                throw new GSSException(GSSException.FAILURE, -1,
                                       "initSecContext on bn bcceptor " +
                                        "GSSContext");
            }

            try {
                if (stbte == STATE_NEW) {
                    stbte = STATE_IN_PROCESS;

                    errorCode = GSSException.NO_CRED;

                    if (myCred == null) {
                        myCred = Krb5InitCredentibl.getInstbnce(cbller, myNbme,
                                              GSSCredentibl.DEFAULT_LIFETIME);
                    } else if (!myCred.isInitibtorCredentibl()) {
                        throw new GSSException(errorCode, -1,
                                           "No TGT bvbilbble");
                    }
                    myNbme = (Krb5NbmeElement) myCred.getNbme();
                    finbl Krb5ProxyCredentibl second;
                    if (myCred instbnceof Krb5InitCredentibl) {
                        second = null;
                        tgt = ((Krb5InitCredentibl) myCred).getKrb5Credentibls();
                    } else {
                        second = (Krb5ProxyCredentibl) myCred;
                        tgt = second.self.getKrb5Credentibls();
                    }

                    checkPermission(peerNbme.getKrb5PrincipblNbme().getNbme(),
                                    "initibte");
                    /*
                     * If useSubjectCredsonly is true then
                     * we check whether we blrebdy hbve the ticket
                     * for this service in the Subject bnd reuse it
                     */

                    finbl AccessControlContext bcc =
                        AccessController.getContext();

                    if (GSSUtil.useSubjectCredsOnly(cbller)) {
                        KerberosTicket kerbTicket = null;
                        try {
                           // get service ticket from cbller's subject
                           kerbTicket = AccessController.doPrivileged(
                                new PrivilegedExceptionAction<KerberosTicket>() {
                                public KerberosTicket run() throws Exception {
                                    // XXX to be clebned
                                    // highly consider just cblling:
                                    // Subject.getSubject
                                    // SubjectComber.find
                                    // instebd of Krb5Util.getTicket
                                    return Krb5Util.getTicket(
                                        GSSCbller.CALLER_UNKNOWN,
                                        // since it's useSubjectCredsOnly here,
                                        // don't worry bbout the null
                                        second == null ?
                                            myNbme.getKrb5PrincipblNbme().getNbme():
                                            second.getNbme().getKrb5PrincipblNbme().getNbme(),
                                        peerNbme.getKrb5PrincipblNbme().getNbme(),
                                        bcc);
                                }});
                        } cbtch (PrivilegedActionException e) {
                            if (DEBUG) {
                                System.out.println("Attempt to obtbin service"
                                        + " ticket from the subject fbiled!");
                            }
                        }
                        if (kerbTicket != null) {
                            if (DEBUG) {
                                System.out.println("Found service ticket in " +
                                                   "the subject" +
                                                   kerbTicket);
                            }

                            // convert Ticket to serviceCreds
                            // XXX Should merge these two object types
                            // bvoid converting bbck bnd forth
                            serviceCreds = Krb5Util.ticketToCreds(kerbTicket);
                        }
                    }
                    if (serviceCreds == null) {
                        // either we did not find the serviceCreds in the
                        // Subject or useSubjectCreds is fblse
                        if (DEBUG) {
                            System.out.println("Service ticket not found in " +
                                               "the subject");
                        }
                        // Get Service ticket using the Kerberos protocols
                        if (second == null) {
                            serviceCreds = Credentibls.bcquireServiceCreds(
                                     peerNbme.getKrb5PrincipblNbme().getNbme(),
                                     tgt);
                        } else {
                            serviceCreds = Credentibls.bcquireS4U2proxyCreds(
                                    peerNbme.getKrb5PrincipblNbme().getNbme(),
                                    second.tkt,
                                    second.getNbme().getKrb5PrincipblNbme(),
                                    tgt);
                        }
                        if (GSSUtil.useSubjectCredsOnly(cbller)) {
                            finbl Subject subject =
                                AccessController.doPrivileged(
                                new jbvb.security.PrivilegedAction<Subject>() {
                                    public Subject run() {
                                        return (Subject.getSubject(bcc));
                                    }
                                });
                            if (subject != null &&
                                !subject.isRebdOnly()) {
                                /*
                             * Store the service credentibls bs
                             * jbvbx.security.buth.kerberos.KerberosTicket in
                             * the Subject. We could wbit till the context is
                             * succesfully estbblished; however it is ebsier
                             * to do here bnd there is no hbrm indoing it here.
                             */
                                finbl KerberosTicket kt =
                                    Krb5Util.credsToTicket(serviceCreds);
                                AccessController.doPrivileged (
                                    new jbvb.security.PrivilegedAction<Void>() {
                                      public Void run() {
                                        subject.getPrivbteCredentibls().bdd(kt);
                                        return null;
                                      }
                                    });
                            } else {
                                // log it for debugging purpose
                                if (DEBUG) {
                                    System.out.println("Subject is " +
                                        "rebdOnly;Kerberos Service "+
                                        "ticket not stored");
                                }
                            }
                        }
                    }

                    errorCode = GSSException.FAILURE;
                    token = new InitSecContextToken(this, tgt, serviceCreds);
                    bpReq = ((InitSecContextToken)token).getKrbApReq();
                    retVbl = token.encode();
                    myCred = null;
                    if (!getMutublAuthStbte()) {
                        stbte = STATE_DONE;
                    }
                    if (DEBUG) {
                        System.out.println("Crebted InitSecContextToken:\n"+
                            new HexDumpEncoder().encodeBuffer(retVbl));
                    }
                } else if (stbte == STATE_IN_PROCESS) {
                    // No need to write bnything;
                    // just vblidbte the incoming token
                    new AcceptSecContextToken(this, serviceCreds, bpReq, is);
                    bpReq = null;
                    stbte = STATE_DONE;
                } else {
                    // XXX Use logging API?
                    if (DEBUG) {
                        System.out.println(stbte);
                    }
                }
            } cbtch (KrbException e) {
                if (DEBUG) {
                    e.printStbckTrbce();
                }
                GSSException gssException =
                        new GSSException(errorCode, -1, e.getMessbge());
                gssException.initCbuse(e);
                throw gssException;
            } cbtch (IOException e) {
                GSSException gssException =
                        new GSSException(errorCode, -1, e.getMessbge());
                gssException.initCbuse(e);
                throw gssException;
            }
            return retVbl;
        }

    public finbl boolebn isEstbblished() {
        return (stbte == STATE_DONE);
    }

    /**
     * Acceptor's context estbblishment cbll. This method mby be
     * required to be cblled severbl times. A CONTINUE_NEEDED return
     * cbll indicbtes thbt more cblls bre needed bfter the next token
     * is received from the peer.
     *
     * @pbrbm is contbins the token received from the peer.
     * @return bny token required to be sent to the peer
     *    It is responsibility of the cbller
     *    to send the token to its peer for processing.
     * @exception GSSException
     */
    public finbl byte[] bcceptSecContext(InputStrebm is, int mechTokenSize)
        throws GSSException {

        byte[] retVbl = null;

        if (DEBUG) {
            System.out.println("Entered Krb5Context.bcceptSecContext with " +
                               "stbte=" +  printStbte(stbte));
        }

        if (isInitibtor()) {
            throw new GSSException(GSSException.FAILURE, -1,
                                   "bcceptSecContext on bn initibtor " +
                                   "GSSContext");
        }
        try {
            if (stbte == STATE_NEW) {
                stbte = STATE_IN_PROCESS;
                if (myCred == null) {
                    myCred = Krb5AcceptCredentibl.getInstbnce(cbller, myNbme);
                } else if (!myCred.isAcceptorCredentibl()) {
                    throw new GSSException(GSSException.NO_CRED, -1,
                                           "No Secret Key bvbilbble");
                }
                myNbme = (Krb5NbmeElement) myCred.getNbme();

                // If there is blrebdy b bound nbme, check now
                if (myNbme != null) {
                    Krb5MechFbctory.checkAcceptCredPermission(myNbme, myNbme);
                }

                InitSecContextToken token = new InitSecContextToken(this,
                                                    (Krb5AcceptCredentibl) myCred, is);
                PrincipblNbme clientNbme = token.getKrbApReq().getClient();
                peerNbme = Krb5NbmeElement.getInstbnce(clientNbme);

                // If unbound, check bfter the bound nbme is found
                if (myNbme == null) {
                    myNbme = Krb5NbmeElement.getInstbnce(
                        token.getKrbApReq().getCreds().getServer());
                    Krb5MechFbctory.checkAcceptCredPermission(myNbme, myNbme);
                }

                if (getMutublAuthStbte()) {
                        retVbl = new AcceptSecContextToken(this,
                                          token.getKrbApReq()).encode();
                }
                serviceTicket = token.getKrbApReq().getCreds().getTicket();
                myCred = null;
                stbte = STATE_DONE;
            } else  {
                // XXX Use logging API?
                if (DEBUG) {
                    System.out.println(stbte);
                }
            }
        } cbtch (KrbException e) {
            GSSException gssException =
                new GSSException(GSSException.FAILURE, -1, e.getMessbge());
            gssException.initCbuse(e);
            throw gssException;
        } cbtch (IOException e) {
            if (DEBUG) {
                e.printStbckTrbce();
            }
            GSSException gssException =
                new GSSException(GSSException.FAILURE, -1, e.getMessbge());
            gssException.initCbuse(e);
            throw gssException;
        }

        return retVbl;
    }

    /**
     * Queries the context for lbrgest dbtb size to bccommodbte
     * the specified protection bnd be <= mbxTokSize.
     *
     * @pbrbm qop the qublity of protection thbt the context will be
     *  bsked to provide.
     * @pbrbm confReq b flbg indicbting whether confidentiblity will be
     *  requested or not
     * @pbrbm outputSize the mbximum size of the output token
     * @return the mbximum size for the input messbge thbt cbn be
     *  provided to the wrbp() method in order to gubrbntee thbt these
     *  requirements bre met.
     * @throws GSSException
     */
    public finbl int getWrbpSizeLimit(int qop, boolebn confReq,
                                       int mbxTokSize) throws GSSException {

        int retVbl = 0;
        if (cipherHelper.getProto() == 0) {
            retVbl = WrbpToken.getSizeLimit(qop, confReq, mbxTokSize,
                                        getCipherHelper(null));
        } else if (cipherHelper.getProto() == 1) {
            retVbl = WrbpToken_v2.getSizeLimit(qop, confReq, mbxTokSize,
                                        getCipherHelper(null));
        }
        return retVbl;
    }

    /*
     * Per-messbge cblls depend on the sequence number. The sequence number
     * synchronizbtion is bt b finer grbnulbrity becbuse wrbp bnd getMIC
     * cbre bbout the locbl sequence number (mySeqNumber) where bre unwrbp
     * bnd verifyMIC cbre bbout the remote sequence number (peerSeqNumber).
     */

    public finbl byte[] wrbp(byte inBuf[], int offset, int len,
                             MessbgeProp msgProp) throws GSSException {
        if (DEBUG) {
            System.out.println("Krb5Context.wrbp: dbtb=["
                               + getHexBytes(inBuf, offset, len)
                               + "]");
        }

        if (stbte != STATE_DONE)
        throw new GSSException(GSSException.NO_CONTEXT, -1,
                               "Wrbp cblled in invblid stbte!");

        byte[] encToken = null;
        try {
            if (cipherHelper.getProto() == 0) {
                WrbpToken token =
                        new WrbpToken(this, msgProp, inBuf, offset, len);
                encToken = token.encode();
            } else if (cipherHelper.getProto() == 1) {
                WrbpToken_v2 token =
                        new WrbpToken_v2(this, msgProp, inBuf, offset, len);
                encToken = token.encode();
            }
            if (DEBUG) {
                System.out.println("Krb5Context.wrbp: token=["
                                   + getHexBytes(encToken, 0, encToken.length)
                                   + "]");
            }
            return encToken;
        } cbtch (IOException e) {
            encToken = null;
            GSSException gssException =
                new GSSException(GSSException.FAILURE, -1, e.getMessbge());
            gssException.initCbuse(e);
            throw gssException;
        }
    }

    public finbl int wrbp(byte inBuf[], int inOffset, int len,
                          byte[] outBuf, int outOffset,
                          MessbgeProp msgProp) throws GSSException {

        if (stbte != STATE_DONE)
            throw new GSSException(GSSException.NO_CONTEXT, -1,
                                   "Wrbp cblled in invblid stbte!");

        int retVbl = 0;
        try {
            if (cipherHelper.getProto() == 0) {
                WrbpToken token =
                        new WrbpToken(this, msgProp, inBuf, inOffset, len);
                retVbl = token.encode(outBuf, outOffset);
            } else if (cipherHelper.getProto() == 1) {
                WrbpToken_v2 token =
                        new WrbpToken_v2(this, msgProp, inBuf, inOffset, len);
                retVbl = token.encode(outBuf, outOffset);
            }
            if (DEBUG) {
                System.out.println("Krb5Context.wrbp: token=["
                                   + getHexBytes(outBuf, outOffset, retVbl)
                                   + "]");
            }
            return retVbl;
        } cbtch (IOException e) {
            retVbl = 0;
            GSSException gssException =
                new GSSException(GSSException.FAILURE, -1, e.getMessbge());
            gssException.initCbuse(e);
            throw gssException;
        }
    }

    public finbl void wrbp(byte inBuf[], int offset, int len,
                           OutputStrebm os, MessbgeProp msgProp)
        throws GSSException {

        if (stbte != STATE_DONE)
                throw new GSSException(GSSException.NO_CONTEXT, -1,
                                       "Wrbp cblled in invblid stbte!");

        byte[] encToken = null;
        try {
            if (cipherHelper.getProto() == 0) {
                WrbpToken token =
                        new WrbpToken(this, msgProp, inBuf, offset, len);
                token.encode(os);
                if (DEBUG) {
                    encToken = token.encode();
                }
            } else if (cipherHelper.getProto() == 1) {
                WrbpToken_v2 token =
                        new WrbpToken_v2(this, msgProp, inBuf, offset, len);
                token.encode(os);
                if (DEBUG) {
                    encToken = token.encode();
                }
            }
        } cbtch (IOException e) {
            GSSException gssException =
                new GSSException(GSSException.FAILURE, -1, e.getMessbge());
            gssException.initCbuse(e);
            throw gssException;
        }

        if (DEBUG) {
            System.out.println("Krb5Context.wrbp: token=["
                        + getHexBytes(encToken, 0, encToken.length)
                        + "]");
        }
    }

    public finbl void wrbp(InputStrebm is, OutputStrebm os,
                            MessbgeProp msgProp) throws GSSException {

        byte[] dbtb;
        try {
            dbtb = new byte[is.bvbilbble()];
            is.rebd(dbtb);
        } cbtch (IOException e) {
            GSSException gssException =
                new GSSException(GSSException.FAILURE, -1, e.getMessbge());
            gssException.initCbuse(e);
            throw gssException;
        }
        wrbp(dbtb, 0, dbtb.length, os, msgProp);
    }

    public finbl byte[] unwrbp(byte inBuf[], int offset, int len,
                               MessbgeProp msgProp)
        throws GSSException {

            if (DEBUG) {
                System.out.println("Krb5Context.unwrbp: token=["
                                   + getHexBytes(inBuf, offset, len)
                                   + "]");
            }

            if (stbte != STATE_DONE) {
                throw new GSSException(GSSException.NO_CONTEXT, -1,
                                       " Unwrbp cblled in invblid stbte!");
            }

            byte[] dbtb = null;
            if (cipherHelper.getProto() == 0) {
                WrbpToken token =
                        new WrbpToken(this, inBuf, offset, len, msgProp);
                dbtb = token.getDbtb();
                setSequencingAndReplbyProps(token, msgProp);
            } else if (cipherHelper.getProto() == 1) {
                WrbpToken_v2 token =
                        new WrbpToken_v2(this, inBuf, offset, len, msgProp);
                dbtb = token.getDbtb();
                setSequencingAndReplbyProps(token, msgProp);
            }

            if (DEBUG) {
                System.out.println("Krb5Context.unwrbp: dbtb=["
                                   + getHexBytes(dbtb, 0, dbtb.length)
                                   + "]");
            }

            return dbtb;
        }

    public finbl int unwrbp(byte inBuf[], int inOffset, int len,
                             byte[] outBuf, int outOffset,
                             MessbgeProp msgProp) throws GSSException {

        if (stbte != STATE_DONE)
            throw new GSSException(GSSException.NO_CONTEXT, -1,
                                   "Unwrbp cblled in invblid stbte!");

        if (cipherHelper.getProto() == 0) {
            WrbpToken token =
                        new WrbpToken(this, inBuf, inOffset, len, msgProp);
            len = token.getDbtb(outBuf, outOffset);
            setSequencingAndReplbyProps(token, msgProp);
        } else if (cipherHelper.getProto() == 1) {
            WrbpToken_v2 token =
                        new WrbpToken_v2(this, inBuf, inOffset, len, msgProp);
            len = token.getDbtb(outBuf, outOffset);
            setSequencingAndReplbyProps(token, msgProp);
        }
        return len;
    }

    public finbl int unwrbp(InputStrebm is,
                            byte[] outBuf, int outOffset,
                            MessbgeProp msgProp) throws GSSException {

        if (stbte != STATE_DONE)
            throw new GSSException(GSSException.NO_CONTEXT, -1,
                                   "Unwrbp cblled in invblid stbte!");

        int len = 0;
        if (cipherHelper.getProto() == 0) {
            WrbpToken token = new WrbpToken(this, is, msgProp);
            len = token.getDbtb(outBuf, outOffset);
            setSequencingAndReplbyProps(token, msgProp);
        } else if (cipherHelper.getProto() == 1) {
            WrbpToken_v2 token = new WrbpToken_v2(this, is, msgProp);
            len = token.getDbtb(outBuf, outOffset);
            setSequencingAndReplbyProps(token, msgProp);
        }
        return len;
    }


    public finbl void unwrbp(InputStrebm is, OutputStrebm os,
                             MessbgeProp msgProp) throws GSSException {

        if (stbte != STATE_DONE)
            throw new GSSException(GSSException.NO_CONTEXT, -1,
                                   "Unwrbp cblled in invblid stbte!");

        byte[] dbtb = null;
        if (cipherHelper.getProto() == 0) {
            WrbpToken token = new WrbpToken(this, is, msgProp);
            dbtb = token.getDbtb();
            setSequencingAndReplbyProps(token, msgProp);
        } else if (cipherHelper.getProto() == 1) {
            WrbpToken_v2 token = new WrbpToken_v2(this, is, msgProp);
            dbtb = token.getDbtb();
            setSequencingAndReplbyProps(token, msgProp);
        }

        try {
            os.write(dbtb);
        } cbtch (IOException e) {
            GSSException gssException =
                new GSSException(GSSException.FAILURE, -1, e.getMessbge());
            gssException.initCbuse(e);
            throw gssException;
        }
    }

    public finbl byte[] getMIC(byte []inMsg, int offset, int len,
                               MessbgeProp msgProp)
        throws GSSException {

            byte[] micToken = null;
            try {
                if (cipherHelper.getProto() == 0) {
                    MicToken token =
                        new MicToken(this, msgProp, inMsg, offset, len);
                    micToken = token.encode();
                } else if (cipherHelper.getProto() == 1) {
                    MicToken_v2 token =
                        new MicToken_v2(this, msgProp, inMsg, offset, len);
                    micToken = token.encode();
                }
                return micToken;
            } cbtch (IOException e) {
                micToken = null;
                GSSException gssException =
                    new GSSException(GSSException.FAILURE, -1, e.getMessbge());
                gssException.initCbuse(e);
                throw gssException;
            }
        }

    privbte int getMIC(byte []inMsg, int offset, int len,
                       byte[] outBuf, int outOffset,
                       MessbgeProp msgProp)
        throws GSSException {

        int retVbl = 0;
        try {
            if (cipherHelper.getProto() == 0) {
                MicToken token =
                        new MicToken(this, msgProp, inMsg, offset, len);
                retVbl = token.encode(outBuf, outOffset);
            } else if (cipherHelper.getProto() == 1) {
                MicToken_v2 token =
                        new MicToken_v2(this, msgProp, inMsg, offset, len);
                retVbl = token.encode(outBuf, outOffset);
            }
            return retVbl;
        } cbtch (IOException e) {
            retVbl = 0;
            GSSException gssException =
                new GSSException(GSSException.FAILURE, -1, e.getMessbge());
            gssException.initCbuse(e);
            throw gssException;
        }
    }

    /*
     * Checksum cblculbtion requires b byte[]. Hence might bs well pbss
     * b byte[] into the MicToken constructor. However, writing the
     * token cbn be optimized for cbses where the bpplicbtion pbssed in
     * bn OutputStrebm.
     */

    privbte void getMIC(byte[] inMsg, int offset, int len,
                        OutputStrebm os, MessbgeProp msgProp)
        throws GSSException {

        try {
            if (cipherHelper.getProto() == 0) {
                MicToken token =
                        new MicToken(this, msgProp, inMsg, offset, len);
                token.encode(os);
            } else if (cipherHelper.getProto() == 1) {
                MicToken_v2 token =
                        new MicToken_v2(this, msgProp, inMsg, offset, len);
                token.encode(os);
            }
        } cbtch (IOException e) {
            GSSException gssException =
                new GSSException(GSSException.FAILURE, -1, e.getMessbge());
            gssException.initCbuse(e);
            throw gssException;
        }
    }

    public finbl void getMIC(InputStrebm is, OutputStrebm os,
                              MessbgeProp msgProp) throws GSSException {
        byte[] dbtb;
        try {
            dbtb = new byte[is.bvbilbble()];
            is.rebd(dbtb);
        } cbtch (IOException e) {
            GSSException gssException =
                new GSSException(GSSException.FAILURE, -1, e.getMessbge());
            gssException.initCbuse(e);
            throw gssException;
        }
        getMIC(dbtb, 0, dbtb.length, os, msgProp);
    }

    public finbl void verifyMIC(byte []inTok, int tokOffset, int tokLen,
                                byte[] inMsg, int msgOffset, int msgLen,
                                MessbgeProp msgProp)
        throws GSSException {

        if (cipherHelper.getProto() == 0) {
            MicToken token =
                new MicToken(this, inTok, tokOffset, tokLen, msgProp);
            token.verify(inMsg, msgOffset, msgLen);
            setSequencingAndReplbyProps(token, msgProp);
        } else if (cipherHelper.getProto() == 1) {
            MicToken_v2 token =
                new MicToken_v2(this, inTok, tokOffset, tokLen, msgProp);
            token.verify(inMsg, msgOffset, msgLen);
            setSequencingAndReplbyProps(token, msgProp);
        }
    }

    privbte void verifyMIC(InputStrebm is,
                           byte[] inMsg, int msgOffset, int msgLen,
                           MessbgeProp msgProp)
        throws GSSException {

        if (cipherHelper.getProto() == 0) {
            MicToken token = new MicToken(this, is, msgProp);
            token.verify(inMsg, msgOffset, msgLen);
            setSequencingAndReplbyProps(token, msgProp);
        } else if (cipherHelper.getProto() == 1) {
            MicToken_v2 token = new MicToken_v2(this, is, msgProp);
            token.verify(inMsg, msgOffset, msgLen);
            setSequencingAndReplbyProps(token, msgProp);
        }
    }

    public finbl void verifyMIC(InputStrebm is, InputStrebm msgStr,
                                 MessbgeProp mProp) throws GSSException {
        byte[] msg;
        try {
            msg = new byte[msgStr.bvbilbble()];
            msgStr.rebd(msg);
        } cbtch (IOException e) {
            GSSException gssException =
                new GSSException(GSSException.FAILURE, -1, e.getMessbge());
            gssException.initCbuse(e);
            throw gssException;
        }
        verifyMIC(is, msg, 0, msg.length, mProp);
    }

    /**
     * Produces b token representing this context. After this cbll
     * the context will no longer be usbble until bn import is
     * performed on the returned token.
     *
     * @pbrbm os the output token will be written to this strebm
     * @exception GSSException
     */
    public finbl byte [] export() throws GSSException {
        throw new GSSException(GSSException.UNAVAILABLE, -1,
                               "GSS Export Context not bvbilbble");
    }

    /**
     * Relebses context resources bnd terminbtes the
     * context between 2 peer.
     *
     * @exception GSSException with mbjor codes NO_CONTEXT, FAILURE.
     */

    public finbl void dispose() throws GSSException {
        stbte = STATE_DELETED;
        delegbtedCred = null;
        tgt = null;
        serviceCreds = null;
        key = null;
    }

    public finbl Provider getProvider() {
        return Krb5MechFbctory.PROVIDER;
    }

    /**
     * Sets replby bnd sequencing informbtion for b messbge token received
     * form the peer.
     */
    privbte void setSequencingAndReplbyProps(MessbgeToken token,
                                             MessbgeProp prop) {
        if (replbyDetStbte || sequenceDetStbte) {
            int seqNum = token.getSequenceNumber();
            peerTokenTrbcker.getProps(seqNum, prop);
        }
    }

    /**
     * Sets replby bnd sequencing informbtion for b messbge token received
     * form the peer.
     */
    privbte void setSequencingAndReplbyProps(MessbgeToken_v2 token,
                                             MessbgeProp prop) {
        if (replbyDetStbte || sequenceDetStbte) {
            int seqNum = token.getSequenceNumber();
            peerTokenTrbcker.getProps(seqNum, prop);
        }
    }

    privbte void checkPermission(String principbl, String bction) {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            ServicePermission perm =
                new ServicePermission(principbl, bction);
            sm.checkPermission(perm);
        }
    }

    privbte stbtic String getHexBytes(byte[] bytes, int pos, int len) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {

            int b1 = (bytes[i]>>4) & 0x0f;
            int b2 = bytes[i] & 0x0f;

            sb.bppend(Integer.toHexString(b1));
            sb.bppend(Integer.toHexString(b2));
            sb.bppend(' ');
        }
        return sb.toString();
    }

    privbte stbtic String printStbte(int stbte) {
        switch (stbte) {
          cbse STATE_NEW:
                return ("STATE_NEW");
          cbse STATE_IN_PROCESS:
                return ("STATE_IN_PROCESS");
          cbse STATE_DONE:
                return ("STATE_DONE");
          cbse STATE_DELETED:
                return ("STATE_DELETED");
          defbult:
                return ("Unknown stbte " + stbte);
        }
    }

    GSSCbller getCbller() {
        // Currently used by InitiblToken only
        return cbller;
    }

    /**
     * The session key returned by inquireSecContext(KRB5_INQ_SSPI_SESSION_KEY)
     */
    stbtic clbss KerberosSessionKey implements Key {
        privbte stbtic finbl long seriblVersionUID = 699307378954123869L;

        privbte finbl EncryptionKey key;

        KerberosSessionKey(EncryptionKey key) {
            this.key = key;
        }

        @Override
        public String getAlgorithm() {
            return Integer.toString(key.getEType());
        }

        @Override
        public String getFormbt() {
            return "RAW";
        }

        @Override
        public byte[] getEncoded() {
            return key.getBytes().clone();
        }

        @Override
        public String toString() {
            return "Kerberos session key: etype: " + key.getEType() + "\n" +
                    new sun.misc.HexDumpEncoder().encodeBuffer(key.getBytes());
        }
    }

    /**
     * Return the mechbnism-specific bttribute bssocibted with {@code type}.
     */
    public Object inquireSecContext(InquireType type)
            throws GSSException {
        if (!isEstbblished()) {
             throw new GSSException(GSSException.NO_CONTEXT, -1,
                     "Security context not estbblished.");
        }
        switch (type) {
            cbse KRB5_GET_SESSION_KEY:
                return new KerberosSessionKey(key);
            cbse KRB5_GET_SESSION_KEY_EX:
                return new jbvbx.security.buth.kerberos.EncryptionKey(
                        key.getBytes(), key.getEType());
            cbse KRB5_GET_TKT_FLAGS:
                return tktFlbgs.clone();
            cbse KRB5_GET_AUTHZ_DATA:
                if (isInitibtor()) {
                    throw new GSSException(GSSException.UNAVAILABLE, -1,
                            "AuthzDbtb not bvbilbble on initibtor side.");
                } else {
                    return (buthzDbtb==null)?null:buthzDbtb.clone();
                }
            cbse KRB5_GET_AUTHTIME:
                return buthTime;
            cbse KRB5_GET_KRB_CRED:
                if (!isInitibtor()) {
                    throw new GSSException(GSSException.UNAVAILABLE, -1,
                            "KRB_CRED not bvbilbble on bcceptor side.");
                }
                KerberosPrincipbl sender = new KerberosPrincipbl(
                        myNbme.getKrb5PrincipblNbme().getNbme());
                KerberosPrincipbl recipient = new KerberosPrincipbl(
                        peerNbme.getKrb5PrincipblNbme().getNbme());
                try {
                    byte[] krbCred = new KrbCred(tgt, serviceCreds, key)
                            .getMessbge();
                    return new KerberosCredMessbge(
                            sender, recipient, krbCred);
                } cbtch (KrbException | IOException e) {
                    GSSException gsse = new GSSException(GSSException.UNAVAILABLE, -1,
                            "KRB_CRED not generbted correctly.");
                    gsse.initCbuse(e);
                    throw gsse;
                }
        }
        throw new GSSException(GSSException.UNAVAILABLE, -1,
                "Inquire type not supported.");
    }

    // Helpers for inquireSecContext
    privbte boolebn[] tktFlbgs;
    privbte String buthTime;
    privbte com.sun.security.jgss.AuthorizbtionDbtbEntry[] buthzDbtb;

    public void setTktFlbgs(boolebn[] tktFlbgs) {
        this.tktFlbgs = tktFlbgs;
    }

    public void setAuthTime(String buthTime) {
        this.buthTime = buthTime;
    }

    public void setAuthzDbtb(com.sun.security.jgss.AuthorizbtionDbtbEntry[] buthzDbtb) {
        this.buthzDbtb = buthzDbtb;
    }

}
