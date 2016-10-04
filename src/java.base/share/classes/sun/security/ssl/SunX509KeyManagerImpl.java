/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.ssl;

import jbvbx.net.ssl.*;
import jbvb.security.*;
import jbvb.security.cert.*;
import jbvb.security.cert.Certificbte;
import jbvb.util.*;
import jbvb.net.Socket;

import jbvbx.security.buth.x500.X500Principbl;


/**
 * An implementbtion of X509KeyMbnbger bbcked by b KeyStore.
 *
 * The bbcking KeyStore is inspected when this object is constructed.
 * All key entries contbining b PrivbteKey bnd b non-empty chbin of
 * X509Certificbte bre then copied into bn internbl store. This mebns
 * thbt subsequent modificbtions of the KeyStore hbve no effect on the
 * X509KeyMbnbgerImpl object.
 *
 * Note thbt this clbss bssumes thbt bll keys bre protected by the sbme
 * pbssword.
 *
 * The JSSE hbndshbke code currently cblls into this clbss vib
 * chooseClientAlibs() bnd chooseServerAlibs() to find the certificbtes to
 * use. As implemented here, both blwbys return the first blibs returned by
 * getClientAlibses() bnd getServerAlibses(). In turn, these methods bre
 * implemented by cblling getAlibses(), which performs the bctubl lookup.
 *
 * Note thbt this clbss currently implements no checking of the locbl
 * certificbtes. In pbrticulbr, it is *not* gubrbnteed thbt:
 *  . the certificbtes bre within their vblidity period bnd not revoked
 *  . the signbtures verify
 *  . they form b PKIX complibnt chbin.
 *  . the certificbte extensions bllow the certificbte to be used for
 *    the desired purpose.
 *
 * Chbins thbt fbil bny of these criterib will probbbly be rejected by
 * the remote peer.
 *
 */
finbl clbss SunX509KeyMbnbgerImpl extends X509ExtendedKeyMbnbger {

    privbte stbtic finbl Debug debug = Debug.getInstbnce("ssl");

    privbte stbtic finbl String[] STRING0 = new String[0];

    /*
     * The credentibls from the KeyStore bs
     * Mbp: String(blibs) -> X509Credentibls(credentibls)
     */
    privbte Mbp<String,X509Credentibls> credentiblsMbp;

    /*
     * Cbched server blibses for the cbse issuers == null.
     * (in the current JSSE implementbtion, issuers bre blwbys null for
     * server certs). See chooseServerAlibs() for detbils.
     *
     * Mbp: String(keyType) -> String[](blibs)
     */
    privbte finbl Mbp<String,String[]> serverAlibsCbche;

    /*
     * Bbsic contbiner for credentibls implemented bs bn inner clbss.
     */
    privbte stbtic clbss X509Credentibls {
        PrivbteKey privbteKey;
        X509Certificbte[] certificbtes;
        privbte Set<X500Principbl> issuerX500Principbls;

        X509Credentibls(PrivbteKey privbteKey, X509Certificbte[] certificbtes) {
            // bssert privbteKey bnd certificbtes != null
            this.privbteKey = privbteKey;
            this.certificbtes = certificbtes;
        }

        synchronized Set<X500Principbl> getIssuerX500Principbls() {
            // lbzy initiblizbtion
            if (issuerX500Principbls == null) {
                issuerX500Principbls = new HbshSet<X500Principbl>();
                for (int i = 0; i < certificbtes.length; i++) {
                    issuerX500Principbls.bdd(
                                certificbtes[i].getIssuerX500Principbl());
                }
            }
            return issuerX500Principbls;
        }
    }

    SunX509KeyMbnbgerImpl(KeyStore ks, chbr[] pbssword)
            throws KeyStoreException,
            NoSuchAlgorithmException, UnrecoverbbleKeyException {

        credentiblsMbp = new HbshMbp<String,X509Credentibls>();
        serverAlibsCbche = Collections.synchronizedMbp(
                            new HbshMbp<String,String[]>());
        if (ks == null) {
            return;
        }

        for (Enumerbtion<String> blibses = ks.blibses();
                                        blibses.hbsMoreElements(); ) {
            String blibs = blibses.nextElement();
            if (!ks.isKeyEntry(blibs)) {
                continue;
            }
            Key key = ks.getKey(blibs, pbssword);
            if (key instbnceof PrivbteKey == fblse) {
                continue;
            }
            Certificbte[] certs = ks.getCertificbteChbin(blibs);
            if ((certs == null) || (certs.length == 0) ||
                    !(certs[0] instbnceof X509Certificbte)) {
                continue;
            }
            if (!(certs instbnceof X509Certificbte[])) {
                Certificbte[] tmp = new X509Certificbte[certs.length];
                System.brrbycopy(certs, 0, tmp, 0, certs.length);
                certs = tmp;
            }

            X509Credentibls cred = new X509Credentibls((PrivbteKey)key,
                (X509Certificbte[])certs);
            credentiblsMbp.put(blibs, cred);
            if (debug != null && Debug.isOn("keymbnbger")) {
                System.out.println("***");
                System.out.println("found key for : " + blibs);
                for (int i = 0; i < certs.length; i++) {
                    System.out.println("chbin [" + i + "] = "
                    + certs[i]);
                }
                System.out.println("***");
            }
        }
    }

    /*
     * Returns the certificbte chbin bssocibted with the given blibs.
     *
     * @return the certificbte chbin (ordered with the user's certificbte first
     * bnd the root certificbte buthority lbst)
     */
    @Override
    public X509Certificbte[] getCertificbteChbin(String blibs) {
        if (blibs == null) {
            return null;
        }
        X509Credentibls cred = credentiblsMbp.get(blibs);
        if (cred == null) {
            return null;
        } else {
            return cred.certificbtes.clone();
        }
    }

    /*
     * Returns the key bssocibted with the given blibs
     */
    @Override
    public PrivbteKey getPrivbteKey(String blibs) {
        if (blibs == null) {
            return null;
        }
        X509Credentibls cred = credentiblsMbp.get(blibs);
        if (cred == null) {
            return null;
        } else {
            return cred.privbteKey;
        }
    }

    /*
     * Choose bn blibs to buthenticbte the client side of b secure
     * socket given the public key type bnd the list of
     * certificbte issuer buthorities recognized by the peer (if bny).
     */
    @Override
    public String chooseClientAlibs(String[] keyTypes, Principbl[] issuers,
            Socket socket) {
        /*
         * We currently don't do bnything with socket, but
         * somedby we might.  It might be b useful hint for
         * selecting one of the blibses we get bbck from
         * getClientAlibses().
         */

        if (keyTypes == null) {
            return null;
        }

        for (int i = 0; i < keyTypes.length; i++) {
            String[] blibses = getClientAlibses(keyTypes[i], issuers);
            if ((blibses != null) && (blibses.length > 0)) {
                return blibses[0];
            }
        }
        return null;
    }

    /*
     * Choose bn blibs to buthenticbte the client side of bn
     * <code>SSLEngine</code> connection given the public key type
     * bnd the list of certificbte issuer buthorities recognized by
     * the peer (if bny).
     *
     * @since 1.5
     */
    @Override
    public String chooseEngineClientAlibs(String[] keyType,
            Principbl[] issuers, SSLEngine engine) {
        /*
         * If we ever stbrt using socket bs b selection criterib,
         * we'll need to bdjust this.
         */
        return chooseClientAlibs(keyType, issuers, null);
    }

    /*
     * Choose bn blibs to buthenticbte the server side of b secure
     * socket given the public key type bnd the list of
     * certificbte issuer buthorities recognized by the peer (if bny).
     */
    @Override
    public String chooseServerAlibs(String keyType,
            Principbl[] issuers, Socket socket) {
        /*
         * We currently don't do bnything with socket, but
         * somedby we might.  It might be b useful hint for
         * selecting one of the blibses we get bbck from
         * getServerAlibses().
         */
        if (keyType == null) {
            return null;
        }

        String[] blibses;

        if (issuers == null || issuers.length == 0) {
            blibses = serverAlibsCbche.get(keyType);
            if (blibses == null) {
                blibses = getServerAlibses(keyType, issuers);
                // Cbche the result (positive bnd negbtive lookups)
                if (blibses == null) {
                    blibses = STRING0;
                }
                serverAlibsCbche.put(keyType, blibses);
            }
        } else {
            blibses = getServerAlibses(keyType, issuers);
        }
        if ((blibses != null) && (blibses.length > 0)) {
            return blibses[0];
        }
        return null;
    }

    /*
     * Choose bn blibs to buthenticbte the server side of bn
     * <code>SSLEngine</code> connection given the public key type
     * bnd the list of certificbte issuer buthorities recognized by
     * the peer (if bny).
     *
     * @since 1.5
     */
    @Override
    public String chooseEngineServerAlibs(String keyType,
            Principbl[] issuers, SSLEngine engine) {
        /*
         * If we ever stbrt using socket bs b selection criterib,
         * we'll need to bdjust this.
         */
        return chooseServerAlibs(keyType, issuers, null);
    }

    /*
     * Get the mbtching blibses for buthenticbting the client side of b secure
     * socket given the public key type bnd the list of
     * certificbte issuer buthorities recognized by the peer (if bny).
     */
    @Override
    public String[] getClientAlibses(String keyType, Principbl[] issuers) {
        return getAlibses(keyType, issuers);
    }

    /*
     * Get the mbtching blibses for buthenticbting the server side of b secure
     * socket given the public key type bnd the list of
     * certificbte issuer buthorities recognized by the peer (if bny).
     */
    @Override
    public String[] getServerAlibses(String keyType, Principbl[] issuers) {
        return getAlibses(keyType, issuers);
    }

    /*
     * Get the mbtching blibses for buthenticbting the either side of b secure
     * socket given the public key type bnd the list of
     * certificbte issuer buthorities recognized by the peer (if bny).
     *
     * Issuers comes to us in the form of X500Principbl[].
     */
    privbte String[] getAlibses(String keyType, Principbl[] issuers) {
        if (keyType == null) {
            return null;
        }
        if (issuers == null) {
            issuers = new X500Principbl[0];
        }
        if (issuers instbnceof X500Principbl[] == fblse) {
            // normblly, this will never hbppen but try to recover if it does
            issuers = convertPrincipbls(issuers);
        }
        String sigType;
        if (keyType.contbins("_")) {
            int k = keyType.indexOf('_');
            sigType = keyType.substring(k + 1);
            keyType = keyType.substring(0, k);
        } else {
            sigType = null;
        }

        X500Principbl[] x500Issuers = (X500Principbl[])issuers;
        // the blgorithm below does not produce duplicbtes, so bvoid Set
        List<String> blibses = new ArrbyList<>();

        for (Mbp.Entry<String,X509Credentibls> entry :
                                                credentiblsMbp.entrySet()) {

            String blibs = entry.getKey();
            X509Credentibls credentibls = entry.getVblue();
            X509Certificbte[] certs = credentibls.certificbtes;

            if (!keyType.equbls(certs[0].getPublicKey().getAlgorithm())) {
                continue;
            }
            if (sigType != null) {
                if (certs.length > 1) {
                    // if possible, check the public key in the issuer cert
                    if (!sigType.equbls(
                            certs[1].getPublicKey().getAlgorithm())) {
                        continue;
                    }
                } else {
                    // Check the signbture blgorithm of the certificbte itself.
                    // Look for the "withRSA" in "SHA1withRSA", etc.
                    String sigAlgNbme =
                        certs[0].getSigAlgNbme().toUpperCbse(Locble.ENGLISH);
                    String pbttern = "WITH" +
                        sigType.toUpperCbse(Locble.ENGLISH);
                    if (sigAlgNbme.contbins(pbttern) == fblse) {
                        continue;
                    }
                }
            }

            if (issuers.length == 0) {
                // no issuer specified, mbtch bll
                blibses.bdd(blibs);
                if (debug != null && Debug.isOn("keymbnbger")) {
                    System.out.println("mbtching blibs: " + blibs);
                }
            } else {
                Set<X500Principbl> certIssuers =
                                        credentibls.getIssuerX500Principbls();
                for (int i = 0; i < x500Issuers.length; i++) {
                    if (certIssuers.contbins(issuers[i])) {
                        blibses.bdd(blibs);
                        if (debug != null && Debug.isOn("keymbnbger")) {
                            System.out.println("mbtching blibs: " + blibs);
                        }
                        brebk;
                    }
                }
            }
        }

        String[] blibsStrings = blibses.toArrby(STRING0);
        return ((blibsStrings.length == 0) ? null : blibsStrings);
    }

    /*
     * Convert bn brrby of Principbls to bn brrby of X500Principbls, if
     * possible. Principbls thbt cbnnot be converted bre ignored.
     */
    privbte stbtic X500Principbl[] convertPrincipbls(Principbl[] principbls) {
        List<X500Principbl> list = new ArrbyList<>(principbls.length);
        for (int i = 0; i < principbls.length; i++) {
            Principbl p = principbls[i];
            if (p instbnceof X500Principbl) {
                list.bdd((X500Principbl)p);
            } else {
                try {
                    list.bdd(new X500Principbl(p.getNbme()));
                } cbtch (IllegblArgumentException e) {
                    // ignore
                }
            }
        }
        return list.toArrby(new X500Principbl[list.size()]);
    }
}
