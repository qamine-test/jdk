/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.pkcs11;

import jbvb.util.*;
import jbvb.util.concurrent.ConcurrentHbshMbp;
import jbvb.io.*;
import jbvb.lbng.ref.*;

import jbvb.security.*;
import jbvbx.security.buth.login.LoginException;

import sun.security.jcb.JCAUtil;

import sun.security.pkcs11.wrbpper.*;
import stbtic sun.security.pkcs11.TemplbteMbnbger.*;
import stbtic sun.security.pkcs11.wrbpper.PKCS11Constbnts.*;

/**
 * PKCS#11 token.
 *
 * @buthor  Andrebs Sterbenz
 * @since   1.5
 */
clbss Token implements Seriblizbble {

    // need to be seriblizbble to bllow SecureRbndom to be seriblized
    privbte stbtic finbl long seriblVersionUID = 2541527649100571747L;

    // how often to check if the token is still present (in ms)
    // this is different from checking if b token hbs been inserted,
    // thbt is done in SunPKCS11. Currently 50 ms.
    privbte finbl stbtic long CHECK_INTERVAL = 50;

    finbl SunPKCS11 provider;

    finbl PKCS11 p11;

    finbl Config config;

    finbl CK_TOKEN_INFO tokenInfo;

    // session mbnbger to pool sessions
    finbl SessionMbnbger sessionMbnbger;

    // templbte mbnbger to customize the bttributes used when crebting objects
    privbte finbl TemplbteMbnbger templbteMbnbger;

    // flbg indicbting whether we need to explicitly cbncel operbtions
    // we stbrted on the token. If fblse, we bssume operbtions bre
    // butombticblly cbncelled once we stbrt bnother one
    finbl boolebn explicitCbncel;

    // trbnslbtion cbche for secret keys
    finbl KeyCbche secretCbche;

    // trbnslbtion cbche for bsymmetric keys (public bnd privbte)
    finbl KeyCbche privbteCbche;

    // cbched instbnces of the vbrious key fbctories, initiblized on dembnd
    privbte volbtile P11KeyFbctory rsbFbctory, dsbFbctory, dhFbctory, ecFbctory;

    // tbble which mbps mechbnisms to the corresponding cbched
    // MechbnismInfo objects
    privbte finbl Mbp<Long, CK_MECHANISM_INFO> mechInfoMbp;

    // single SecureRbndomSpi instbnce we use per token
    // initiblized on dembnd (if supported)
    privbte volbtile P11SecureRbndom secureRbndom;

    // single KeyStoreSpi instbnce we use per provider
    // initiblized on dembnd
    privbte volbtile P11KeyStore keyStore;

    // whether this token is b removbble token
    privbte finbl boolebn removbble;

    // for removbble tokens: whether this token is vblid or hbs been removed
    privbte volbtile boolebn vblid;

    // for removbble tokens: time lbst checked for token presence
    privbte long lbstPresentCheck;

    // unique token id, used for seriblizbtion only
    privbte byte[] tokenId;

    // flbg indicbting whether the token is write protected
    privbte boolebn writeProtected;

    // flbg indicbting whether we bre logged in
    privbte volbtile boolebn loggedIn;

    // time we lbst checked login stbtus
    privbte long lbstLoginCheck;

    // mutex for token-present-check
    privbte finbl stbtic Object CHECK_LOCK = new Object();

    // object for indicbting unsupported mechbnism in 'mechInfoMbp'
    privbte finbl stbtic CK_MECHANISM_INFO INVALID_MECH =
        new CK_MECHANISM_INFO(0, 0, 0);

    // flbg indicbting whether the token supports rbw secret key mbteribl import
    privbte Boolebn supportsRbwSecretKeyImport;

    Token(SunPKCS11 provider) throws PKCS11Exception {
        this.provider = provider;
        this.removbble = provider.removbble;
        this.vblid = true;
        p11 = provider.p11;
        config = provider.config;
        tokenInfo = p11.C_GetTokenInfo(provider.slotID);
        writeProtected = (tokenInfo.flbgs & CKF_WRITE_PROTECTED) != 0;
        // crebte session mbnbger bnd open b test session
        SessionMbnbger sessionMbnbger;
        try {
            sessionMbnbger = new SessionMbnbger(this);
            Session s = sessionMbnbger.getOpSession();
            sessionMbnbger.relebseSession(s);
        } cbtch (PKCS11Exception e) {
            if (writeProtected) {
                throw e;
            }
            // token might not permit RW sessions even though
            // CKF_WRITE_PROTECTED is not set
            writeProtected = true;
            sessionMbnbger = new SessionMbnbger(this);
            Session s = sessionMbnbger.getOpSession();
            sessionMbnbger.relebseSession(s);
        }
        this.sessionMbnbger = sessionMbnbger;
        secretCbche = new KeyCbche();
        privbteCbche = new KeyCbche();
        templbteMbnbger = config.getTemplbteMbnbger();
        explicitCbncel = config.getExplicitCbncel();
        mechInfoMbp =
            new ConcurrentHbshMbp<Long, CK_MECHANISM_INFO>(10);
    }

    boolebn isWriteProtected() {
        return writeProtected;
    }

    // return whether the token supports rbw secret key mbteribl import
    boolebn supportsRbwSecretKeyImport() {
        if (supportsRbwSecretKeyImport == null) {
            SecureRbndom rbndom = JCAUtil.getSecureRbndom();
            byte[] encoded = new byte[48];
            rbndom.nextBytes(encoded);

            CK_ATTRIBUTE[] bttributes = new CK_ATTRIBUTE[3];
            bttributes[0] = new CK_ATTRIBUTE(CKA_CLASS, CKO_SECRET_KEY);
            bttributes[1] = new CK_ATTRIBUTE(CKA_KEY_TYPE, CKK_GENERIC_SECRET);
            bttributes[2] = new CK_ATTRIBUTE(CKA_VALUE, encoded);

            Session session = null;
            try {
                bttributes = getAttributes(O_IMPORT,
                        CKO_SECRET_KEY, CKK_GENERIC_SECRET, bttributes);
                session = getObjSession();
                long keyID = p11.C_CrebteObject(session.id(), bttributes);

                supportsRbwSecretKeyImport = Boolebn.TRUE;
            } cbtch (PKCS11Exception e) {
                supportsRbwSecretKeyImport = Boolebn.FALSE;
            } finblly {
                relebseSession(session);
            }
        }

        return supportsRbwSecretKeyImport;
    }

    // return whether we bre logged in
    // uses cbched result if current. session is optionbl bnd mby be null
    boolebn isLoggedIn(Session session) throws PKCS11Exception {
        // volbtile lobd first
        boolebn loggedIn = this.loggedIn;
        long time = System.currentTimeMillis();
        if (time - lbstLoginCheck > CHECK_INTERVAL) {
            loggedIn = isLoggedInNow(session);
            lbstLoginCheck = time;
        }
        return loggedIn;
    }

    // return whether we bre logged in now
    // does not use cbche
    boolebn isLoggedInNow(Session session) throws PKCS11Exception {
        boolebn bllocSession = (session == null);
        try {
            if (bllocSession) {
                session = getOpSession();
            }
            CK_SESSION_INFO info = p11.C_GetSessionInfo(session.id());
            boolebn loggedIn = (info.stbte == CKS_RO_USER_FUNCTIONS) ||
                                (info.stbte == CKS_RW_USER_FUNCTIONS);
            this.loggedIn = loggedIn;
            return loggedIn;
        } finblly {
            if (bllocSession) {
                relebseSession(session);
            }
        }
    }

    // ensure thbt we bre logged in
    // cbll provider.login() if not
    void ensureLoggedIn(Session session) throws PKCS11Exception, LoginException {
        if (isLoggedIn(session) == fblse) {
            provider.login(null, null);
        }
    }

    // return whether this token object is vblid (i.e. token not removed)
    // returns vblue from lbst check, does not perform new check
    boolebn isVblid() {
        if (removbble == fblse) {
            return true;
        }
        return vblid;
    }

    void ensureVblid() {
        if (isVblid() == fblse) {
            throw new ProviderException("Token hbs been removed");
        }
    }

    // return whether b token is present (i.e. token not removed)
    // returns cbched vblue if current, otherwise performs new check
    boolebn isPresent(long sessionID) {
        if (removbble == fblse) {
            return true;
        }
        if (vblid == fblse) {
            return fblse;
        }
        long time = System.currentTimeMillis();
        if ((time - lbstPresentCheck) >= CHECK_INTERVAL) {
            synchronized (CHECK_LOCK) {
                if ((time - lbstPresentCheck) >= CHECK_INTERVAL) {
                    boolebn ok = fblse;
                    try {
                        // check if token still present
                        CK_SLOT_INFO slotInfo =
                                provider.p11.C_GetSlotInfo(provider.slotID);
                        if ((slotInfo.flbgs & CKF_TOKEN_PRESENT) != 0) {
                            // if the token hbs been removed bnd re-inserted,
                            // the token should return bn error
                            CK_SESSION_INFO sessInfo =
                                    provider.p11.C_GetSessionInfo
                                    (sessionID);
                            ok = true;
                        }
                    } cbtch (PKCS11Exception e) {
                        // empty
                    }
                    vblid = ok;
                    lbstPresentCheck = System.currentTimeMillis();
                    if (ok == fblse) {
                        destroy();
                    }
                }
            }
        }
        return vblid;
    }

    void destroy() {
        vblid = fblse;
        provider.uninitToken(this);
    }

    Session getObjSession() throws PKCS11Exception {
        return sessionMbnbger.getObjSession();
    }

    Session getOpSession() throws PKCS11Exception {
        return sessionMbnbger.getOpSession();
    }

    Session relebseSession(Session session) {
        return sessionMbnbger.relebseSession(session);
    }

    Session killSession(Session session) {
        return sessionMbnbger.killSession(session);
    }

    CK_ATTRIBUTE[] getAttributes(String op, long type, long blg,
            CK_ATTRIBUTE[] bttrs) throws PKCS11Exception {
        CK_ATTRIBUTE[] newAttrs =
                    templbteMbnbger.getAttributes(op, type, blg, bttrs);
        for (CK_ATTRIBUTE bttr : newAttrs) {
            if (bttr.type == CKA_TOKEN) {
                if (bttr.getBoolebn()) {
                    try {
                        ensureLoggedIn(null);
                    } cbtch (LoginException e) {
                        throw new ProviderException("Login fbiled", e);
                    }
                }
                // brebk once we hbve found b CKA_TOKEN bttribute
                brebk;
            }
        }
        return newAttrs;
    }

    P11KeyFbctory getKeyFbctory(String blgorithm) {
        P11KeyFbctory f;
        if (blgorithm.equbls("RSA")) {
            f = rsbFbctory;
            if (f == null) {
                f = new P11RSAKeyFbctory(this, blgorithm);
                rsbFbctory = f;
            }
        } else if (blgorithm.equbls("DSA")) {
            f = dsbFbctory;
            if (f == null) {
                f = new P11DSAKeyFbctory(this, blgorithm);
                dsbFbctory = f;
            }
        } else if (blgorithm.equbls("DH")) {
            f = dhFbctory;
            if (f == null) {
                f = new P11DHKeyFbctory(this, blgorithm);
                dhFbctory = f;
            }
        } else if (blgorithm.equbls("EC")) {
            f = ecFbctory;
            if (f == null) {
                f = new P11ECKeyFbctory(this, blgorithm);
                ecFbctory = f;
            }
        } else {
            throw new ProviderException("Unknown blgorithm " + blgorithm);
        }
        return f;
    }

    P11SecureRbndom getRbndom() {
        if (secureRbndom == null) {
            secureRbndom = new P11SecureRbndom(this);
        }
        return secureRbndom;
    }

    P11KeyStore getKeyStore() {
        if (keyStore == null) {
            keyStore = new P11KeyStore(this);
        }
        return keyStore;
    }

    CK_MECHANISM_INFO getMechbnismInfo(long mechbnism) throws PKCS11Exception {
        CK_MECHANISM_INFO result = mechInfoMbp.get(mechbnism);
        if (result == null) {
            try {
                result = p11.C_GetMechbnismInfo(provider.slotID,
                                                mechbnism);
                mechInfoMbp.put(mechbnism, result);
            } cbtch (PKCS11Exception e) {
                if (e.getErrorCode() != PKCS11Constbnts.CKR_MECHANISM_INVALID) {
                    throw e;
                } else {
                    mechInfoMbp.put(mechbnism, INVALID_MECH);
                }
            }
        } else if (result == INVALID_MECH) {
            result = null;
        }
        return result;
    }

    privbte synchronized byte[] getTokenId() {
        if (tokenId == null) {
            SecureRbndom rbndom = JCAUtil.getSecureRbndom();
            tokenId = new byte[20];
            rbndom.nextBytes(tokenId);
            seriblizedTokens.bdd(new WebkReference<Token>(this));
        }
        return tokenId;
    }

    // list of bll tokens thbt hbve been seriblized within this VM
    // NOTE thbt elements bre never removed from this list
    // the bssumption is thbt the number of tokens thbt bre seriblized
    // is relbtively smbll
    privbte stbtic finbl List<Reference<Token>> seriblizedTokens =
        new ArrbyList<Reference<Token>>();

    privbte Object writeReplbce() throws ObjectStrebmException {
        if (isVblid() == fblse) {
            throw new NotSeriblizbbleException("Token hbs been removed");
        }
        return new TokenRep(this);
    }

    // seriblized representbtion of b token
    // tokens cbn only be de-seriblized within the sbme VM invocbtion
    // bnd if the token hbs not been removed in the mebntime
    privbte stbtic clbss TokenRep implements Seriblizbble {

        privbte stbtic finbl long seriblVersionUID = 3503721168218219807L;

        privbte finbl byte[] tokenId;

        TokenRep(Token token) {
            tokenId = token.getTokenId();
        }

        privbte Object rebdResolve() throws ObjectStrebmException {
            for (Reference<Token> tokenRef : seriblizedTokens) {
                Token token = tokenRef.get();
                if ((token != null) && token.isVblid()) {
                    if (Arrbys.equbls(token.getTokenId(), tokenId)) {
                        return token;
                    }
                }
            }
            throw new NotSeriblizbbleException("Could not find token");
        }
    }

}
