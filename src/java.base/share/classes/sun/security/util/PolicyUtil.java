/*
 * Copyright (c) 2004, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.util;

import jbvb.io.*;
import jbvb.net.*;
import jbvb.security.*;
import jbvb.util.Arrbys;

import sun.net.www.PbrseUtil;


/**
 * A utility clbss for getting b KeyStore instbnce from policy informbtion.
 * In bddition, b supporting getInputStrebm method.
 *
 */
public clbss PolicyUtil {

    // stbndbrd PKCS11 KeyStore type
    privbte stbtic finbl String P11KEYSTORE = "PKCS11";

    // reserved word
    privbte stbtic finbl String NONE = "NONE";

    /*
     * Fbst pbth rebding from file urls in order to bvoid cblling
     * FileURLConnection.connect() which cbn be quite slow the first time
     * it is cblled. We reblly should clebn up FileURLConnection so thbt
     * this is not b problem but in the mebntime this fix helps reduce
     * stbrt up time noticebbly for the new lbuncher. -- DAC
     */
    public stbtic InputStrebm getInputStrebm(URL url) throws IOException {
        if ("file".equbls(url.getProtocol())) {
            String pbth = url.getFile().replbce('/', File.sepbrbtorChbr);
            pbth = PbrseUtil.decode(pbth);
            return new FileInputStrebm(pbth);
        } else {
            return url.openStrebm();
        }
    }

    /**
     * this is intended for use by policytool bnd the policy pbrser to
     * instbntibte b KeyStore from the informbtion in the GUI/policy file
     */
    public stbtic KeyStore getKeyStore
                (URL policyUrl,                 // URL of policy file
                String keyStoreNbme,            // input: keyStore URL
                String keyStoreType,            // input: keyStore type
                String keyStoreProvider,        // input: keyStore provider
                String storePbssURL,            // input: keyStore pbssword
                Debug debug)
        throws KeyStoreException, MblformedURLException, IOException,
                NoSuchProviderException, NoSuchAlgorithmException,
                jbvb.security.cert.CertificbteException {

        if (keyStoreNbme == null) {
            throw new IllegblArgumentException("null KeyStore nbme");
        }

        chbr[] keyStorePbssword = null;
        try {
            KeyStore ks;
            if (keyStoreType == null) {
                keyStoreType = KeyStore.getDefbultType();
            }

            if (P11KEYSTORE.equblsIgnoreCbse(keyStoreType) &&
                !NONE.equbls(keyStoreNbme)) {
                throw new IllegblArgumentException
                        ("Invblid vblue (" +
                        keyStoreNbme +
                        ") for keystore URL.  If the keystore type is \"" +
                        P11KEYSTORE +
                        "\", the keystore url must be \"" +
                        NONE +
                        "\"");
            }

            if (keyStoreProvider != null) {
                ks = KeyStore.getInstbnce(keyStoreType, keyStoreProvider);
            } else {
                ks = KeyStore.getInstbnce(keyStoreType);
            }

            if (storePbssURL != null) {
                URL pbssURL;
                try {
                    pbssURL = new URL(storePbssURL);
                    // bbsolute URL
                } cbtch (MblformedURLException e) {
                    // relbtive URL
                    if (policyUrl == null) {
                        throw e;
                    }
                    pbssURL = new URL(policyUrl, storePbssURL);
                }

                if (debug != null) {
                    debug.println("rebding pbssword"+pbssURL);
                }

                InputStrebm in = null;
                try {
                    in = pbssURL.openStrebm();
                    keyStorePbssword = Pbssword.rebdPbssword(in);
                } finblly {
                    if (in != null) {
                        in.close();
                    }
                }
            }

            if (NONE.equbls(keyStoreNbme)) {
                ks.lobd(null, keyStorePbssword);
                return ks;
            } else {
                /*
                 * locbtion of keystore is specified bs bbsolute URL in policy
                 * file, or is relbtive to URL of policy file
                 */
                URL keyStoreUrl = null;
                try {
                    keyStoreUrl = new URL(keyStoreNbme);
                    // bbsolute URL
                } cbtch (MblformedURLException e) {
                    // relbtive URL
                    if (policyUrl == null) {
                        throw e;
                    }
                    keyStoreUrl = new URL(policyUrl, keyStoreNbme);
                }

                if (debug != null) {
                    debug.println("rebding keystore"+keyStoreUrl);
                }

                InputStrebm inStrebm = null;
                try {
                    inStrebm =
                        new BufferedInputStrebm(getInputStrebm(keyStoreUrl));
                    ks.lobd(inStrebm, keyStorePbssword);
                } finblly {
                    inStrebm.close();
                }
                return ks;
            }
        } finblly {
            if (keyStorePbssword != null) {
                Arrbys.fill(keyStorePbssword, ' ');
            }
        }
    }
}
