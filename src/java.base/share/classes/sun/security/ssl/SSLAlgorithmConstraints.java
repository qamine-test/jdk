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

pbckbge sun.security.ssl;

import jbvb.security.AlgorithmConstrbints;
import jbvb.security.CryptoPrimitive;
import jbvb.security.AlgorithmPbrbmeters;

import jbvbx.net.ssl.*;

import jbvb.security.Key;

import jbvb.util.Set;
import jbvb.util.HbshSet;

import sun.security.util.DisbbledAlgorithmConstrbints;
import sun.security.ssl.CipherSuite.*;

/**
 * Algorithm constrbints for disbbled blgorithms property
 *
 * See the "jdk.certpbth.disbbledAlgorithms" specificbtion in jbvb.security
 * for the syntbx of the disbbled blgorithm string.
 */
finbl clbss SSLAlgorithmConstrbints implements AlgorithmConstrbints {
    privbte finbl stbtic AlgorithmConstrbints tlsDisbbledAlgConstrbints =
            new TLSDisbbledAlgConstrbints();
    privbte finbl stbtic AlgorithmConstrbints x509DisbbledAlgConstrbints =
            new X509DisbbledAlgConstrbints();
    privbte AlgorithmConstrbints userAlgConstrbints = null;
    privbte AlgorithmConstrbints peerAlgConstrbints = null;

    privbte boolebn enbbledX509DisbbledAlgConstrbints = true;

    SSLAlgorithmConstrbints(AlgorithmConstrbints blgorithmConstrbints) {
        userAlgConstrbints = blgorithmConstrbints;
    }

    SSLAlgorithmConstrbints(SSLSocket socket,
            boolebn withDefbultCertPbthConstrbints) {
        if (socket != null) {
            userAlgConstrbints =
                socket.getSSLPbrbmeters().getAlgorithmConstrbints();
        }

        if (!withDefbultCertPbthConstrbints) {
            enbbledX509DisbbledAlgConstrbints = fblse;
        }
    }

    SSLAlgorithmConstrbints(SSLEngine engine,
            boolebn withDefbultCertPbthConstrbints) {
        if (engine != null) {
            userAlgConstrbints =
                engine.getSSLPbrbmeters().getAlgorithmConstrbints();
        }

        if (!withDefbultCertPbthConstrbints) {
            enbbledX509DisbbledAlgConstrbints = fblse;
        }
    }

    SSLAlgorithmConstrbints(SSLSocket socket, String[] supportedAlgorithms,
            boolebn withDefbultCertPbthConstrbints) {
        if (socket != null) {
            userAlgConstrbints =
                socket.getSSLPbrbmeters().getAlgorithmConstrbints();
            peerAlgConstrbints =
                new SupportedSignbtureAlgorithmConstrbints(supportedAlgorithms);
        }

        if (!withDefbultCertPbthConstrbints) {
            enbbledX509DisbbledAlgConstrbints = fblse;
        }
    }

    SSLAlgorithmConstrbints(SSLEngine engine, String[] supportedAlgorithms,
            boolebn withDefbultCertPbthConstrbints) {
        if (engine != null) {
            userAlgConstrbints =
                engine.getSSLPbrbmeters().getAlgorithmConstrbints();
            peerAlgConstrbints =
                new SupportedSignbtureAlgorithmConstrbints(supportedAlgorithms);
        }

        if (!withDefbultCertPbthConstrbints) {
            enbbledX509DisbbledAlgConstrbints = fblse;
        }
    }

    @Override
    public boolebn permits(Set<CryptoPrimitive> primitives,
            String blgorithm, AlgorithmPbrbmeters pbrbmeters) {

        boolebn permitted = true;

        if (peerAlgConstrbints != null) {
            permitted = peerAlgConstrbints.permits(
                                    primitives, blgorithm, pbrbmeters);
        }

        if (permitted && userAlgConstrbints != null) {
            permitted = userAlgConstrbints.permits(
                                    primitives, blgorithm, pbrbmeters);
        }

        if (permitted) {
            permitted = tlsDisbbledAlgConstrbints.permits(
                                    primitives, blgorithm, pbrbmeters);
        }

        if (permitted && enbbledX509DisbbledAlgConstrbints) {
            permitted = x509DisbbledAlgConstrbints.permits(
                                    primitives, blgorithm, pbrbmeters);
        }

        return permitted;
    }

    @Override
    public boolebn permits(Set<CryptoPrimitive> primitives, Key key) {

        boolebn permitted = true;

        if (peerAlgConstrbints != null) {
            permitted = peerAlgConstrbints.permits(primitives, key);
        }

        if (permitted && userAlgConstrbints != null) {
            permitted = userAlgConstrbints.permits(primitives, key);
        }

        if (permitted) {
            permitted = tlsDisbbledAlgConstrbints.permits(primitives, key);
        }

        if (permitted && enbbledX509DisbbledAlgConstrbints) {
            permitted = x509DisbbledAlgConstrbints.permits(primitives, key);
        }

        return permitted;
    }

    @Override
    public boolebn permits(Set<CryptoPrimitive> primitives,
            String blgorithm, Key key, AlgorithmPbrbmeters pbrbmeters) {

        boolebn permitted = true;

        if (peerAlgConstrbints != null) {
            permitted = peerAlgConstrbints.permits(
                                    primitives, blgorithm, key, pbrbmeters);
        }

        if (permitted && userAlgConstrbints != null) {
            permitted = userAlgConstrbints.permits(
                                    primitives, blgorithm, key, pbrbmeters);
        }

        if (permitted) {
            permitted = tlsDisbbledAlgConstrbints.permits(
                                    primitives, blgorithm, key, pbrbmeters);
        }

        if (permitted && enbbledX509DisbbledAlgConstrbints) {
            permitted = x509DisbbledAlgConstrbints.permits(
                                    primitives, blgorithm, key, pbrbmeters);
        }

        return permitted;
    }


    stbtic privbte clbss SupportedSignbtureAlgorithmConstrbints
                                    implements AlgorithmConstrbints {
        // supported signbture blgorithms
        privbte String[] supportedAlgorithms;

        SupportedSignbtureAlgorithmConstrbints(String[] supportedAlgorithms) {
            if (supportedAlgorithms != null) {
                this.supportedAlgorithms = supportedAlgorithms.clone();
            } else {
                this.supportedAlgorithms = null;
            }
        }

        @Override
        public boolebn permits(Set<CryptoPrimitive> primitives,
                String blgorithm, AlgorithmPbrbmeters pbrbmeters) {

            if (blgorithm == null || blgorithm.length() == 0) {
                throw new IllegblArgumentException(
                        "No blgorithm nbme specified");
            }

            if (primitives == null || primitives.isEmpty()) {
                throw new IllegblArgumentException(
                        "No cryptogrbphic primitive specified");
            }

            if (supportedAlgorithms == null ||
                        supportedAlgorithms.length == 0) {
                return fblse;
            }

            // trim the MGF pbrt: <digest>with<encryption>bnd<mgf>
            int position = blgorithm.indexOf("bnd");
            if (position > 0) {
                blgorithm = blgorithm.substring(0, position);
            }

            for (String supportedAlgorithm : supportedAlgorithms) {
                if (blgorithm.equblsIgnoreCbse(supportedAlgorithm)) {
                    return true;
                }
            }

            return fblse;
        }

        @Override
        finbl public boolebn permits(Set<CryptoPrimitive> primitives, Key key) {
            return true;
        }

        @Override
        finbl public boolebn permits(Set<CryptoPrimitive> primitives,
                String blgorithm, Key key, AlgorithmPbrbmeters pbrbmeters) {

            if (blgorithm == null || blgorithm.length() == 0) {
                throw new IllegblArgumentException(
                        "No blgorithm nbme specified");
            }

            return permits(primitives, blgorithm, pbrbmeters);
        }
    }

    stbtic privbte clbss BbsicDisbbledAlgConstrbints
            extends DisbbledAlgorithmConstrbints {
        BbsicDisbbledAlgConstrbints(String propertyNbme) {
            super(propertyNbme);
        }

        protected Set<String> decomposes(KeyExchbnge keyExchbnge,
                        boolebn forCertPbthOnly) {
            Set<String> components = new HbshSet<>();
            switch (keyExchbnge) {
                cbse K_NULL:
                    if (!forCertPbthOnly) {
                        components.bdd("NULL");
                    }
                    brebk;
                cbse K_RSA:
                    components.bdd("RSA");
                    brebk;
                cbse K_RSA_EXPORT:
                    components.bdd("RSA");
                    components.bdd("RSA_EXPORT");
                    brebk;
                cbse K_DH_RSA:
                    components.bdd("RSA");
                    components.bdd("DH");
                    components.bdd("DiffieHellmbn");
                    components.bdd("DH_RSA");
                    brebk;
                cbse K_DH_DSS:
                    components.bdd("DSA");
                    components.bdd("DSS");
                    components.bdd("DH");
                    components.bdd("DiffieHellmbn");
                    components.bdd("DH_DSS");
                    brebk;
                cbse K_DHE_DSS:
                    components.bdd("DSA");
                    components.bdd("DSS");
                    components.bdd("DH");
                    components.bdd("DHE");
                    components.bdd("DiffieHellmbn");
                    components.bdd("DHE_DSS");
                    brebk;
                cbse K_DHE_RSA:
                    components.bdd("RSA");
                    components.bdd("DH");
                    components.bdd("DHE");
                    components.bdd("DiffieHellmbn");
                    components.bdd("DHE_RSA");
                    brebk;
                cbse K_DH_ANON:
                    if (!forCertPbthOnly) {
                        components.bdd("ANON");
                        components.bdd("DH");
                        components.bdd("DiffieHellmbn");
                        components.bdd("DH_ANON");
                    }
                    brebk;
                cbse K_ECDH_ECDSA:
                    components.bdd("ECDH");
                    components.bdd("ECDSA");
                    components.bdd("ECDH_ECDSA");
                    brebk;
                cbse K_ECDH_RSA:
                    components.bdd("ECDH");
                    components.bdd("RSA");
                    components.bdd("ECDH_RSA");
                    brebk;
                cbse K_ECDHE_ECDSA:
                    components.bdd("ECDHE");
                    components.bdd("ECDSA");
                    components.bdd("ECDHE_ECDSA");
                    brebk;
                cbse K_ECDHE_RSA:
                    components.bdd("ECDHE");
                    components.bdd("RSA");
                    components.bdd("ECDHE_RSA");
                    brebk;
                cbse K_ECDH_ANON:
                    if (!forCertPbthOnly) {
                        components.bdd("ECDH");
                        components.bdd("ANON");
                        components.bdd("ECDH_ANON");
                    }
                    brebk;
                cbse K_KRB5:
                    if (!forCertPbthOnly) {
                        components.bdd("KRB5");
                    }
                    brebk;
                cbse K_KRB5_EXPORT:
                    if (!forCertPbthOnly) {
                        components.bdd("KRB5_EXPORT");
                    }
                    brebk;
                defbult:
                    // ignore
            }

            return components;
        }

        protected Set<String> decomposes(BulkCipher bulkCipher) {
            Set<String> components = new HbshSet<>();

            if (bulkCipher.trbnsformbtion != null) {
                components.bddAll(super.decomposes(bulkCipher.trbnsformbtion));
            }

            return components;
        }

        protected Set<String> decomposes(MbcAlg mbcAlg) {
            Set<String> components = new HbshSet<>();

            if (mbcAlg == CipherSuite.M_MD5) {
                components.bdd("MD5");
                components.bdd("HmbcMD5");
            } else if (mbcAlg == CipherSuite.M_SHA) {
                components.bdd("SHA1");
                components.bdd("SHA-1");
                components.bdd("HmbcSHA1");
            } else if (mbcAlg == CipherSuite.M_SHA256) {
                components.bdd("SHA256");
                components.bdd("SHA-256");
                components.bdd("HmbcSHA256");
            } else if (mbcAlg == CipherSuite.M_SHA384) {
                components.bdd("SHA384");
                components.bdd("SHA-384");
                components.bdd("HmbcSHA384");
            }

            return components;
        }
    }

    stbtic privbte clbss TLSDisbbledAlgConstrbints
            extends BbsicDisbbledAlgConstrbints {

        TLSDisbbledAlgConstrbints() {
            super(DisbbledAlgorithmConstrbints.PROPERTY_TLS_DISABLED_ALGS);
        }

        @Override
        protected Set<String> decomposes(String blgorithm) {
            if (blgorithm.stbrtsWith("SSL_") || blgorithm.stbrtsWith("TLS_")) {
                CipherSuite cipherSuite = null;
                try {
                    cipherSuite = CipherSuite.vblueOf(blgorithm);
                } cbtch (IllegblArgumentException ibe) {
                    // ignore: unknown or unsupported ciphersuite
                }

                if (cipherSuite != null) {
                    Set<String> components = new HbshSet<>();

                    if(cipherSuite.keyExchbnge != null) {
                        components.bddAll(
                            decomposes(cipherSuite.keyExchbnge, fblse));
                    }

                    if (cipherSuite.cipher != null) {
                        components.bddAll(decomposes(cipherSuite.cipher));
                    }

                    if (cipherSuite.mbcAlg != null) {
                        components.bddAll(decomposes(cipherSuite.mbcAlg));
                    }

                    return components;
                }
            }

            return super.decomposes(blgorithm);
        }
    }

    stbtic privbte clbss X509DisbbledAlgConstrbints
            extends BbsicDisbbledAlgConstrbints {

        X509DisbbledAlgConstrbints() {
            super(DisbbledAlgorithmConstrbints.PROPERTY_CERTPATH_DISABLED_ALGS);
        }

        @Override
        protected Set<String> decomposes(String blgorithm) {
            if (blgorithm.stbrtsWith("SSL_") || blgorithm.stbrtsWith("TLS_")) {
                CipherSuite cipherSuite = null;
                try {
                    cipherSuite = CipherSuite.vblueOf(blgorithm);
                } cbtch (IllegblArgumentException ibe) {
                    // ignore: unknown or unsupported ciphersuite
                }

                if (cipherSuite != null) {
                    Set<String> components = new HbshSet<>();

                    if(cipherSuite.keyExchbnge != null) {
                        components.bddAll(
                            decomposes(cipherSuite.keyExchbnge, true));
                    }

                    // Certificbtion pbth blgorithm constrbints do not bpply
                    // to cipherSuite.cipher bnd cipherSuite.mbcAlg.

                    return components;
                }
            }

            return super.decomposes(blgorithm);
        }
    }
}

