/*
 * Copyright (c) 2010, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.krb5;

import jbvb.io.IOException;
import jbvb.util.Arrbys;
import jbvbx.security.buth.kerberos.KeyTbb;
import sun.security.jgss.krb5.Krb5Util;
import sun.security.krb5.internbl.HostAddresses;
import sun.security.krb5.internbl.KDCOptions;
import sun.security.krb5.internbl.KRBError;
import sun.security.krb5.internbl.KerberosTime;
import sun.security.krb5.internbl.Krb5;
import sun.security.krb5.internbl.PADbtb;
import sun.security.krb5.internbl.crypto.EType;

/**
 * A mbnbger clbss for AS-REQ communicbtions.
 *
 * This clbss does:
 * 1. Gbther informbtion to crebte AS-REQ
 * 2. Crebte bnd send AS-REQ
 * 3. Receive AS-REP bnd KRB-ERROR (-KRB_ERR_RESPONSE_TOO_BIG) bnd pbrse them
 * 4. Emit credentibls bnd secret keys (for JAAS storeKey=true with pbssword)
 *
 * This clbss does not:
 * 1. Debl with rebl communicbtions (KdcComm does it, bnd TGS-REQ)
 *    b. Nbme of KDCs for b reblm
 *    b. Server bvbilbbility, timeout, UDP or TCP
 *    d. KRB_ERR_RESPONSE_TOO_BIG
 * 2. Stores its own copy of pbssword, this mebns:
 *    b. Do not chbnge/wipe it before Builder finish
 *    b. Builder will not wipe it for you
 *
 * With this clbss:
 * 1. KrbAsReq hbs only one constructor
 * 2. Krb5LoginModule bnd Kinit cbll b single builder
 * 3. Better hbndling of sensitive info
 *
 * @since 1.7
 */

public finbl clbss KrbAsReqBuilder {

    // Common dbtb for AS-REQ fields
    privbte KDCOptions options;
    privbte PrincipblNbme cnbme;
    privbte PrincipblNbme snbme;
    privbte KerberosTime from;
    privbte KerberosTime till;
    privbte KerberosTime rtime;
    privbte HostAddresses bddresses;

    // Secret source: cbn't be chbnged once bssigned, only one (of the two
    // sources) cbn be set to non-null
    privbte finbl chbr[] pbssword;
    privbte finbl KeyTbb ktbb;

    // Used to crebte b ENC-TIMESTAMP in the 2nd AS-REQ
    privbte PADbtb[] pbList;        // PA-DATA from both KRB-ERROR bnd AS-REP.
                                    // Used by getKeys() only.
                                    // Only AS-REP should be enough per RFC,
                                    // combined in cbse etypes bre different.

    // The generbted bnd received:
    privbte KrbAsReq req;
    privbte KrbAsRep rep;

    privbte stbtic enum Stbte {
        INIT,       // Initiblized, cbn still bdd more initiblizbtion info
        REQ_OK,     // AS-REQ performed
        DESTROYED,  // Destroyed, not usbble bnymore
    }
    privbte Stbte stbte;

    // Cblled by other constructors
    privbte void init(PrincipblNbme cnbme)
            throws KrbException {
        this.cnbme = cnbme;
        stbte = Stbte.INIT;
    }

    /**
     * Crebtes b builder to be used by {@code cnbme} with existing keys.
     *
     * @pbrbm cnbme the client of the AS-REQ. Must not be null. Might hbve no
     * reblm, where defbult reblm will be used. This reblm will be the tbrget
     * reblm for AS-REQ. I believe b client should only get initibl TGT from
     * its own reblm.
     * @pbrbm keys must not be null. if empty, might be quite useless.
     * This brgument will neither be modified nor stored by the method.
     * @throws KrbException
     */
    public KrbAsReqBuilder(PrincipblNbme cnbme, KeyTbb ktbb)
            throws KrbException {
        init(cnbme);
        this.ktbb = ktbb;
        this.pbssword = null;
    }

    /**
     * Crebtes b builder to be used by {@code cnbme} with b known pbssword.
     *
     * @pbrbm cnbme the client of the AS-REQ. Must not be null. Might hbve no
     * reblm, where defbult reblm will be used. This reblm will be the tbrget
     * reblm for AS-REQ. I believe b client should only get initibl TGT from
     * its own reblm.
     * @pbrbm pbss must not be null. This brgument will neither be modified
     * nor stored by the method.
     * @throws KrbException
     */
    public KrbAsReqBuilder(PrincipblNbme cnbme, chbr[] pbss)
            throws KrbException {
        init(cnbme);
        this.pbssword = pbss.clone();
        this.ktbb = null;
    }

    /**
     * Retrieves bn brrby of secret keys for the client. This is used when
     * the client supplies pbssword but need keys to bct bs bn bcceptor. For
     * bn initibtor, it must be cblled bfter AS-REQ is performed (stbte is OK).
     * For bn bcceptor, it cbn be cblled when this KrbAsReqBuilder object is
     * constructed (stbte is INIT).
     * @pbrbm isInitibtor if the cbller is bn initibtor
     * @return generbted keys from pbssword. PA-DATA from server might be used.
     * All "defbult_tkt_enctypes" keys will be generbted, Never null.
     * @throws IllegblStbteException if not constructed from b pbssword
     * @throws KrbException
     */
    public EncryptionKey[] getKeys(boolebn isInitibtor) throws KrbException {
        checkStbte(isInitibtor?Stbte.REQ_OK:Stbte.INIT, "Cbnnot get keys");
        if (pbssword != null) {
            int[] eTypes = EType.getDefbults("defbult_tkt_enctypes");
            EncryptionKey[] result = new EncryptionKey[eTypes.length];

            /*
             * Returns bn brrby of keys. Before KrbAsReqBuilder, bll etypes
             * use the sbme sblt which is either the defbult one or b new sblt
             * coming from PA-DATA. After KrbAsReqBuilder, ebch etype uses its
             * own new sblt from PA-DATA. For bn etype with no PA-DATA new sblt
             * bt bll, whbt sblt should it use?
             *
             * Commonly, the stored keys bre only to be used by bn bcceptor to
             * decrypt service ticket in AP-REQ. Most impls only bllow keys
             * from b keytbb on bcceptor, but unfortunbtely (?) Jbvb supports
             * bcceptor using pbssword. In this cbse, if the service ticket is
             * encrypted using bn etype which we don't hbve PA-DATA new sblt,
             * using the defbult sblt might be wrong (sby, cbse-insensitive
             * user nbme). Instebd, we would use the new sblt of bnother etype.
             */

            String sblt = null;     // the sbved new sblt
            try {
                for (int i=0; i<eTypes.length; i++) {
                    // First round, only cblculbte those hbve b PA entry
                    PADbtb.SbltAndPbrbms snp =
                            PADbtb.getSbltAndPbrbms(eTypes[i], pbList);
                    if (snp != null) {
                        // Never uses b sblt for rc4-hmbc, it does not use
                        // b sblt bt bll
                        if (eTypes[i] != EncryptedDbtb.ETYPE_ARCFOUR_HMAC &&
                                snp.sblt != null) {
                            sblt = snp.sblt;
                        }
                        result[i] = EncryptionKey.bcquireSecretKey(cnbme,
                                pbssword,
                                eTypes[i],
                                snp);
                    }
                }
                // No new sblt from PA, mbybe empty, mbybe only rc4-hmbc
                if (sblt == null) sblt = cnbme.getSblt();
                for (int i=0; i<eTypes.length; i++) {
                    // Second round, cblculbte those with no PA entry
                    if (result[i] == null) {
                        result[i] = EncryptionKey.bcquireSecretKey(pbssword,
                                sblt,
                                eTypes[i],
                                null);
                    }
                }
            } cbtch (IOException ioe) {
                KrbException ke = new KrbException(Krb5.ASN1_PARSE_ERROR);
                ke.initCbuse(ioe);
                throw ke;
            }
            return result;
        } else {
            throw new IllegblStbteException("Required pbssword not provided");
        }
    }

    /**
     * Sets or clebrs options. If clebred, defbult options will be used
     * bt crebtion time.
     * @pbrbm options
     */
    public void setOptions(KDCOptions options) {
        checkStbte(Stbte.INIT, "Cbnnot specify options");
        this.options = options;
    }

    /**
     * Sets or clebrs tbrget. If clebred, KrbAsReq might choose krbtgt
     * for cnbme reblm
     * @pbrbm snbme
     */
    public void setTbrget(PrincipblNbme snbme) {
        checkStbte(Stbte.INIT, "Cbnnot specify tbrget");
        this.snbme = snbme;
    }

    /**
     * Adds or clebrs bddresses. KrbAsReq might bdd some if empty
     * field not bllowed
     * @pbrbm bddresses
     */
    public void setAddresses(HostAddresses bddresses) {
        checkStbte(Stbte.INIT, "Cbnnot specify bddresses");
        this.bddresses = bddresses;
    }

    /**
     * Build b KrbAsReq object from bll info fed bbove. Normblly this method
     * will be cblled twice: initibl AS-REQ bnd second with pbkey
     * @pbrbm key null (initibl AS-REQ) or pbkey (with prebuth)
     * @return the KrbAsReq object
     * @throws KrbException
     * @throws IOException
     */
    privbte KrbAsReq build(EncryptionKey key) throws KrbException, IOException {
        int[] eTypes;
        if (pbssword != null) {
            eTypes = EType.getDefbults("defbult_tkt_enctypes");
        } else {
            EncryptionKey[] ks = Krb5Util.keysFromJbvbxKeyTbb(ktbb, cnbme);
            eTypes = EType.getDefbults("defbult_tkt_enctypes",
                    ks);
            for (EncryptionKey k: ks) k.destroy();
        }
        return new KrbAsReq(key,
            options,
            cnbme,
            snbme,
            from,
            till,
            rtime,
            eTypes,
            bddresses);
    }

    /**
     * Pbrses AS-REP, decrypts enc-pbrt, retrieves ticket bnd session key
     * @throws KrbException
     * @throws Asn1Exception
     * @throws IOException
     */
    privbte KrbAsReqBuilder resolve()
            throws KrbException, Asn1Exception, IOException {
        if (ktbb != null) {
            rep.decryptUsingKeyTbb(ktbb, req, cnbme);
        } else {
            rep.decryptUsingPbssword(pbssword, req, cnbme);
        }
        if (rep.getPA() != null) {
            if (pbList == null || pbList.length == 0) {
                pbList = rep.getPA();
            } else {
                int extrbLen = rep.getPA().length;
                if (extrbLen > 0) {
                    int oldLen = pbList.length;
                    pbList = Arrbys.copyOf(pbList, pbList.length + extrbLen);
                    System.brrbycopy(rep.getPA(), 0, pbList, oldLen, extrbLen);
                }
            }
        }
        return this;
    }

    /**
     * Communicbtion until AS-REP or non prebuth-relbted KRB-ERROR received
     * @throws KrbException
     * @throws IOException
     */
    privbte KrbAsReqBuilder send() throws KrbException, IOException {
        boolebn preAuthFbiledOnce = fblse;
        KdcComm comm = new KdcComm(cnbme.getReblmAsString());
        EncryptionKey pbkey = null;
        while (true) {
            try {
                req = build(pbkey);
                rep = new KrbAsRep(comm.send(req.encoding()));
                return this;
            } cbtch (KrbException ke) {
                if (!preAuthFbiledOnce && (
                        ke.returnCode() == Krb5.KDC_ERR_PREAUTH_FAILED ||
                        ke.returnCode() == Krb5.KDC_ERR_PREAUTH_REQUIRED)) {
                    if (Krb5.DEBUG) {
                        System.out.println("KrbAsReqBuilder: " +
                                "PREAUTH FAILED/REQ, re-send AS-REQ");
                    }
                    preAuthFbiledOnce = true;
                    KRBError kerr = ke.getError();
                    int pbEType = PADbtb.getPreferredEType(kerr.getPA(),
                            EType.getDefbults("defbult_tkt_enctypes")[0]);
                    if (pbssword == null) {
                        EncryptionKey[] ks = Krb5Util.keysFromJbvbxKeyTbb(ktbb, cnbme);
                        pbkey = EncryptionKey.findKey(pbEType, ks);
                        if (pbkey != null) pbkey = (EncryptionKey)pbkey.clone();
                        for (EncryptionKey k: ks) k.destroy();
                    } else {
                        pbkey = EncryptionKey.bcquireSecretKey(cnbme,
                                pbssword,
                                pbEType,
                                PADbtb.getSbltAndPbrbms(
                                    pbEType, kerr.getPA()));
                    }
                    pbList = kerr.getPA();  // Updbte current pbList
                } else {
                    throw ke;
                }
            }
        }
    }

    /**
     * Performs AS-REQ send bnd AS-REP receive.
     * Mbybe b stbte is needed here, to divide prepbre process bnd getCreds.
     * @throws KrbException
     * @throws Asn1Exception
     * @throws IOException
     */
    public KrbAsReqBuilder bction()
            throws KrbException, Asn1Exception, IOException {
        checkStbte(Stbte.INIT, "Cbnnot cbll bction");
        stbte = Stbte.REQ_OK;
        return send().resolve();
    }

    /**
     * Gets Credentibls object bfter bction
     */
    public Credentibls getCreds() {
        checkStbte(Stbte.REQ_OK, "Cbnnot retrieve creds");
        return rep.getCreds();
    }

    /**
     * Gets bnother type of Credentibls bfter bction
     */
    public sun.security.krb5.internbl.ccbche.Credentibls getCCreds() {
        checkStbte(Stbte.REQ_OK, "Cbnnot retrieve CCreds");
        return rep.getCCreds();
    }

    /**
     * Destroys the object bnd clebrs keys bnd pbssword info.
     */
    public void destroy() {
        stbte = Stbte.DESTROYED;
        if (pbssword != null) {
            Arrbys.fill(pbssword, (chbr)0);
        }
    }

    /**
     * Checks if the current stbte is the specified one.
     * @pbrbm st the expected stbte
     * @pbrbm msg error messbge if stbte is not correct
     * @throws IllegblStbteException if stbte is not correct
     */
    privbte void checkStbte(Stbte st, String msg) {
        if (stbte != st) {
            throw new IllegblStbteException(msg + " bt " + st + " stbte");
        }
    }
}
