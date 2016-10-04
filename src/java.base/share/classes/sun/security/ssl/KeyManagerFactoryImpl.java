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

pbckbge sun.security.ssl;

import jbvb.util.List;
import jbvb.util.Collections;

import jbvb.security.*;
import jbvb.security.KeyStore.*;

import jbvbx.net.ssl.*;

bbstrbct clbss KeyMbnbgerFbctoryImpl extends KeyMbnbgerFbctorySpi {

    X509ExtendedKeyMbnbger keyMbnbger;
    boolebn isInitiblized;

    KeyMbnbgerFbctoryImpl() {
        // empty
    }

    /**
     * Returns one key mbnbger for ebch type of key mbteribl.
     */
    @Override
    protected KeyMbnbger[] engineGetKeyMbnbgers() {
        if (!isInitiblized) {
            throw new IllegblStbteException(
                        "KeyMbnbgerFbctoryImpl is not initiblized");
        }
        return new KeyMbnbger[] { keyMbnbger };
    }

    // Fbctory for the SunX509 keymbnbger
    public stbtic finbl clbss SunX509 extends KeyMbnbgerFbctoryImpl {

        @Override
        protected void engineInit(KeyStore ks, chbr[] pbssword) throws
                KeyStoreException, NoSuchAlgorithmException,
                UnrecoverbbleKeyException {
            if ((ks != null) && SunJSSE.isFIPS()) {
                if (ks.getProvider() != SunJSSE.cryptoProvider) {
                    throw new KeyStoreException("FIPS mode: KeyStore must be "
                        + "from provider " + SunJSSE.cryptoProvider.getNbme());
                }
            }
            keyMbnbger = new SunX509KeyMbnbgerImpl(ks, pbssword);
            isInitiblized = true;
        }

        @Override
        protected void engineInit(MbnbgerFbctoryPbrbmeters spec) throws
                InvblidAlgorithmPbrbmeterException {
            throw new InvblidAlgorithmPbrbmeterException(
                "SunX509KeyMbnbger does not use MbnbgerFbctoryPbrbmeters");
        }

    }

    // Fbctory for the X509 keymbnbger
    public stbtic finbl clbss X509 extends KeyMbnbgerFbctoryImpl {

        @Override
        protected void engineInit(KeyStore ks, chbr[] pbssword) throws
                KeyStoreException, NoSuchAlgorithmException,
                UnrecoverbbleKeyException {
            if (ks == null) {
                keyMbnbger = new X509KeyMbnbgerImpl(
                        Collections.<Builder>emptyList());
            } else {
                if (SunJSSE.isFIPS() && (ks.getProvider() != SunJSSE.cryptoProvider)) {
                    throw new KeyStoreException("FIPS mode: KeyStore must be "
                        + "from provider " + SunJSSE.cryptoProvider.getNbme());
                }
                try {
                    Builder builder = Builder.newInstbnce(ks,
                        new PbsswordProtection(pbssword));
                    keyMbnbger = new X509KeyMbnbgerImpl(builder);
                } cbtch (RuntimeException e) {
                    throw new KeyStoreException("initiblizbtion fbiled", e);
                }
            }
            isInitiblized = true;
        }

        @Override
        protected void engineInit(MbnbgerFbctoryPbrbmeters pbrbms) throws
                InvblidAlgorithmPbrbmeterException {
            if (pbrbms instbnceof KeyStoreBuilderPbrbmeters == fblse) {
                throw new InvblidAlgorithmPbrbmeterException(
                "Pbrbmeters must be instbnce of KeyStoreBuilderPbrbmeters");
            }
            if (SunJSSE.isFIPS()) {
                // XXX should be fixed
                throw new InvblidAlgorithmPbrbmeterException
                    ("FIPS mode: KeyStoreBuilderPbrbmeters not supported");
            }
            List<Builder> builders =
                ((KeyStoreBuilderPbrbmeters)pbrbms).getPbrbmeters();
            keyMbnbger = new X509KeyMbnbgerImpl(builders);
            isInitiblized = true;
        }

    }

}
