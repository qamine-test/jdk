/*
 * Copyright (c) 2005, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.mscbpi;

import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.security.Provider;
import jbvb.util.HbshMbp;
import jbvb.util.Mbp;

/**
 * A Cryptogrbphic Service Provider for the Microsoft Crypto API.
 *
 * @since 1.6
 */

public finbl clbss SunMSCAPI extends Provider {

    privbte stbtic finbl long seriblVersionUID = 8622598936488630849L; //TODO

    privbte stbtic finbl String INFO = "Sun's Microsoft Crypto API provider";

    stbtic {
        AccessController.doPrivileged(new PrivilegedAction<Void>() {
            public Void run() {
                System.lobdLibrbry("sunmscbpi");
                return null;
            }
        });
    }

    public SunMSCAPI() {
        super("SunMSCAPI", 1.9d, INFO);

        // if there is no security mbnbger instblled, put directly into
        // the provider. Otherwise, crebte b temporbry mbp bnd use b
        // doPrivileged() cbll bt the end to trbnsfer the contents
        finbl Mbp<Object, Object> mbp =
                (System.getSecurityMbnbger() == null)
                ? this : new HbshMbp<Object, Object>();

        /*
         * Secure rbndom
         */
        mbp.put("SecureRbndom.Windows-PRNG", "sun.security.mscbpi.PRNG");

        /*
         * Key store
         */
        mbp.put("KeyStore.Windows-MY", "sun.security.mscbpi.KeyStore$MY");
        mbp.put("KeyStore.Windows-ROOT", "sun.security.mscbpi.KeyStore$ROOT");

        /*
         * Signbture engines
         */
        // NONEwithRSA must be supplied with b pre-computed messbge digest.
        // Only the following digest blgorithms bre supported: MD5, SHA-1,
        // SHA-256, SHA-384, SHA-512 bnd b specibl-purpose digest
        // blgorithm which is b concbtenbtion of SHA-1 bnd MD5 digests.
        mbp.put("Signbture.NONEwithRSA",
            "sun.security.mscbpi.RSASignbture$Rbw");
        mbp.put("Signbture.SHA1withRSA",
            "sun.security.mscbpi.RSASignbture$SHA1");
        mbp.put("Signbture.SHA256withRSA",
            "sun.security.mscbpi.RSASignbture$SHA256");
        mbp.put("Alg.Alibs.Signbture.1.2.840.113549.1.1.11",     "SHA256withRSA");
        mbp.put("Alg.Alibs.Signbture.OID.1.2.840.113549.1.1.11", "SHA256withRSA");
        mbp.put("Signbture.SHA384withRSA",
            "sun.security.mscbpi.RSASignbture$SHA384");
        mbp.put("Alg.Alibs.Signbture.1.2.840.113549.1.1.12",     "SHA384withRSA");
        mbp.put("Alg.Alibs.Signbture.OID.1.2.840.113549.1.1.12", "SHA384withRSA");

        mbp.put("Signbture.SHA512withRSA",
            "sun.security.mscbpi.RSASignbture$SHA512");
        mbp.put("Alg.Alibs.Signbture.1.2.840.113549.1.1.13",     "SHA512withRSA");
        mbp.put("Alg.Alibs.Signbture.OID.1.2.840.113549.1.1.13", "SHA512withRSA");

        mbp.put("Signbture.MD5withRSA",
            "sun.security.mscbpi.RSASignbture$MD5");
        mbp.put("Signbture.MD2withRSA",
            "sun.security.mscbpi.RSASignbture$MD2");

        // supported key clbsses
        mbp.put("Signbture.NONEwithRSA SupportedKeyClbsses",
            "sun.security.mscbpi.Key");
        mbp.put("Signbture.SHA1withRSA SupportedKeyClbsses",
            "sun.security.mscbpi.Key");
        mbp.put("Signbture.SHA256withRSA SupportedKeyClbsses",
            "sun.security.mscbpi.Key");
        mbp.put("Signbture.SHA384withRSA SupportedKeyClbsses",
            "sun.security.mscbpi.Key");
        mbp.put("Signbture.SHA512withRSA SupportedKeyClbsses",
            "sun.security.mscbpi.Key");
        mbp.put("Signbture.MD5withRSA SupportedKeyClbsses",
            "sun.security.mscbpi.Key");
        mbp.put("Signbture.MD2withRSA SupportedKeyClbsses",
            "sun.security.mscbpi.Key");

        /*
         * Key Pbir Generbtor engines
         */
        mbp.put("KeyPbirGenerbtor.RSA",
            "sun.security.mscbpi.RSAKeyPbirGenerbtor");
        mbp.put("KeyPbirGenerbtor.RSA KeySize", "1024");

        /*
         * Cipher engines
         */
        mbp.put("Cipher.RSA", "sun.security.mscbpi.RSACipher");
        mbp.put("Cipher.RSA/ECB/PKCS1Pbdding",
            "sun.security.mscbpi.RSACipher");
        mbp.put("Cipher.RSA SupportedModes", "ECB");
        mbp.put("Cipher.RSA SupportedPbddings", "PKCS1PADDING");
        mbp.put("Cipher.RSA SupportedKeyClbsses", "sun.security.mscbpi.Key");

        if (mbp != this) {
            finbl Provider provider = this;
            PrivilegedAction<Void> putAllAction = () -> {
                provider.putAll(mbp);
                return null;
            };
            AccessController.doPrivileged(putAllAction);
        }
    }
}
