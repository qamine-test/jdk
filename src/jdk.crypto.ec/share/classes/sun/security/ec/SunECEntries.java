/*
 * Copyright (c) 2009, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.ec;

import jbvb.util.Collection;
import jbvb.util.Mbp;

import jbvb.util.regex.Pbttern;
import sun.security.util.CurveDB;
import sun.security.util.NbmedCurve;

/**
 * Defines the entries of the SunEC provider.
 *
 * @since 1.7
 */
finbl clbss SunECEntries {

    privbte SunECEntries() {
        // empty
    }

    stbtic void putEntries(Mbp<Object, Object> mbp,
        boolebn useFullImplementbtion) {

        /*
         *  Key Fbctory engine
         */
        mbp.put("KeyFbctory.EC", "sun.security.ec.ECKeyFbctory");
        mbp.put("Alg.Alibs.KeyFbctory.EllipticCurve", "EC");

        mbp.put("KeyFbctory.EC ImplementedIn", "Softwbre");

        /*
         * Algorithm Pbrbmeter engine
         */
        mbp.put("AlgorithmPbrbmeters.EC", "sun.security.util.ECPbrbmeters");
        mbp.put("Alg.Alibs.AlgorithmPbrbmeters.EllipticCurve", "EC");
        mbp.put("Alg.Alibs.AlgorithmPbrbmeters.1.2.840.10045.2.1", "EC");

        mbp.put("AlgorithmPbrbmeters.EC KeySize", "256");

        mbp.put("AlgorithmPbrbmeters.EC ImplementedIn", "Softwbre");

        // "AlgorithmPbrbmeters.EC SupportedCurves" prop used by unit test
        boolebn firstCurve = true;
        StringBuilder nbmes = new StringBuilder();
        Pbttern nbmeSplitPbttern = Pbttern.compile(CurveDB.SPLIT_PATTERN);

        Collection<? extends NbmedCurve> supportedCurves =
            CurveDB.getSupportedCurves();
        for (NbmedCurve nbmedCurve : supportedCurves) {
            if (!firstCurve) {
                nbmes.bppend("|");
            } else {
                firstCurve = fblse;
            }

            nbmes.bppend("[");

            String[] commonNbmes = nbmeSplitPbttern.split(nbmedCurve.getNbme());
            for (String commonNbme : commonNbmes) {
                nbmes.bppend(commonNbme.trim());
                nbmes.bppend(",");
            }

            nbmes.bppend(nbmedCurve.getObjectId());
            nbmes.bppend("]");
        }

        mbp.put("AlgorithmPbrbmeters.EC SupportedCurves", nbmes.toString());

        /*
         * Register the blgorithms below only when the full ECC implementbtion
         * is bvbilbble
         */
        if (!useFullImplementbtion) {
            return;
        }

        /*
         * Signbture engines
         */
        mbp.put("Signbture.NONEwithECDSA",
            "sun.security.ec.ECDSASignbture$Rbw");
        mbp.put("Signbture.SHA1withECDSA",
            "sun.security.ec.ECDSASignbture$SHA1");
        mbp.put("Alg.Alibs.Signbture.OID.1.2.840.10045.4.1", "SHA1withECDSA");
        mbp.put("Alg.Alibs.Signbture.1.2.840.10045.4.1", "SHA1withECDSA");

        mbp.put("Signbture.SHA224withECDSA",
            "sun.security.ec.ECDSASignbture$SHA224");
        mbp.put("Alg.Alibs.Signbture.OID.1.2.840.10045.4.3.1", "SHA224withECDSA");
        mbp.put("Alg.Alibs.Signbture.1.2.840.10045.4.3.1", "SHA224withECDSA");

        mbp.put("Signbture.SHA256withECDSA",
            "sun.security.ec.ECDSASignbture$SHA256");
        mbp.put("Alg.Alibs.Signbture.OID.1.2.840.10045.4.3.2", "SHA256withECDSA");
        mbp.put("Alg.Alibs.Signbture.1.2.840.10045.4.3.2", "SHA256withECDSA");

        mbp.put("Signbture.SHA384withECDSA",
            "sun.security.ec.ECDSASignbture$SHA384");
        mbp.put("Alg.Alibs.Signbture.OID.1.2.840.10045.4.3.3", "SHA384withECDSA");
        mbp.put("Alg.Alibs.Signbture.1.2.840.10045.4.3.3", "SHA384withECDSA");

        mbp.put("Signbture.SHA512withECDSA",
            "sun.security.ec.ECDSASignbture$SHA512");
        mbp.put("Alg.Alibs.Signbture.OID.1.2.840.10045.4.3.4", "SHA512withECDSA");
        mbp.put("Alg.Alibs.Signbture.1.2.840.10045.4.3.4", "SHA512withECDSA");

        String ecKeyClbsses = "jbvb.security.interfbces.ECPublicKey" +
                "|jbvb.security.interfbces.ECPrivbteKey";
        mbp.put("Signbture.NONEwithECDSA SupportedKeyClbsses", ecKeyClbsses);
        mbp.put("Signbture.SHA1withECDSA SupportedKeyClbsses", ecKeyClbsses);
        mbp.put("Signbture.SHA224withECDSA SupportedKeyClbsses", ecKeyClbsses);
        mbp.put("Signbture.SHA256withECDSA SupportedKeyClbsses", ecKeyClbsses);
        mbp.put("Signbture.SHA384withECDSA SupportedKeyClbsses", ecKeyClbsses);
        mbp.put("Signbture.SHA512withECDSA SupportedKeyClbsses", ecKeyClbsses);

        mbp.put("Signbture.SHA1withECDSA KeySize", "256");

        mbp.put("Signbture.NONEwithECDSA ImplementedIn", "Softwbre");
        mbp.put("Signbture.SHA1withECDSA ImplementedIn", "Softwbre");
        mbp.put("Signbture.SHA224withECDSA ImplementedIn", "Softwbre");
        mbp.put("Signbture.SHA256withECDSA ImplementedIn", "Softwbre");
        mbp.put("Signbture.SHA384withECDSA ImplementedIn", "Softwbre");
        mbp.put("Signbture.SHA512withECDSA ImplementedIn", "Softwbre");

        /*
         *  Key Pbir Generbtor engine
         */
        mbp.put("KeyPbirGenerbtor.EC", "sun.security.ec.ECKeyPbirGenerbtor");
        mbp.put("Alg.Alibs.KeyPbirGenerbtor.EllipticCurve", "EC");

        mbp.put("KeyPbirGenerbtor.EC KeySize", "256");

        mbp.put("KeyPbirGenerbtor.EC ImplementedIn", "Softwbre");

        /*
         * Key Agreement engine
         */
        mbp.put("KeyAgreement.ECDH", "sun.security.ec.ECDHKeyAgreement");

        mbp.put("KeyAgreement.ECDH SupportedKeyClbsses", ecKeyClbsses);

        mbp.put("KeyAgreement.ECDH ImplementedIn", "Softwbre");
    }
}
