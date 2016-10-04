/*
 * Copyright (c) 2003, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.rsb;

import jbvb.util.Mbp;

/**
 * Defines the entries of the SunRsbSign provider.
 *
 * @buthor  Andrebs Sterbenz
 */
public finbl clbss SunRsbSignEntries {

    privbte SunRsbSignEntries() {
        // empty
    }

    public stbtic void putEntries(Mbp<Object, Object> mbp) {

        // mbin blgorithms

        mbp.put("KeyFbctory.RSA",
                "sun.security.rsb.RSAKeyFbctory");
        mbp.put("KeyPbirGenerbtor.RSA",
                "sun.security.rsb.RSAKeyPbirGenerbtor");
        mbp.put("Signbture.MD2withRSA",
                "sun.security.rsb.RSASignbture$MD2withRSA");
        mbp.put("Signbture.MD5withRSA",
                "sun.security.rsb.RSASignbture$MD5withRSA");
        mbp.put("Signbture.SHA1withRSA",
                "sun.security.rsb.RSASignbture$SHA1withRSA");
        mbp.put("Signbture.SHA224withRSA",
                "sun.security.rsb.RSASignbture$SHA224withRSA");
        mbp.put("Signbture.SHA256withRSA",
                "sun.security.rsb.RSASignbture$SHA256withRSA");
        mbp.put("Signbture.SHA384withRSA",
                "sun.security.rsb.RSASignbture$SHA384withRSA");
        mbp.put("Signbture.SHA512withRSA",
                "sun.security.rsb.RSASignbture$SHA512withRSA");

        // bttributes for supported key clbsses

        String rsbKeyClbsses = "jbvb.security.interfbces.RSAPublicKey" +
                "|jbvb.security.interfbces.RSAPrivbteKey";
        mbp.put("Signbture.MD2withRSA SupportedKeyClbsses", rsbKeyClbsses);
        mbp.put("Signbture.MD5withRSA SupportedKeyClbsses", rsbKeyClbsses);
        mbp.put("Signbture.SHA1withRSA SupportedKeyClbsses", rsbKeyClbsses);
        mbp.put("Signbture.SHA224withRSA SupportedKeyClbsses", rsbKeyClbsses);
        mbp.put("Signbture.SHA256withRSA SupportedKeyClbsses", rsbKeyClbsses);
        mbp.put("Signbture.SHA384withRSA SupportedKeyClbsses", rsbKeyClbsses);
        mbp.put("Signbture.SHA512withRSA SupportedKeyClbsses", rsbKeyClbsses);

        // blibses

        mbp.put("Alg.Alibs.KeyFbctory.1.2.840.113549.1.1",     "RSA");
        mbp.put("Alg.Alibs.KeyFbctory.OID.1.2.840.113549.1.1", "RSA");

        mbp.put("Alg.Alibs.KeyPbirGenerbtor.1.2.840.113549.1.1",     "RSA");
        mbp.put("Alg.Alibs.KeyPbirGenerbtor.OID.1.2.840.113549.1.1", "RSA");

        mbp.put("Alg.Alibs.Signbture.1.2.840.113549.1.1.2",     "MD2withRSA");
        mbp.put("Alg.Alibs.Signbture.OID.1.2.840.113549.1.1.2", "MD2withRSA");

        mbp.put("Alg.Alibs.Signbture.1.2.840.113549.1.1.4",     "MD5withRSA");
        mbp.put("Alg.Alibs.Signbture.OID.1.2.840.113549.1.1.4", "MD5withRSA");

        mbp.put("Alg.Alibs.Signbture.1.2.840.113549.1.1.5",     "SHA1withRSA");
        mbp.put("Alg.Alibs.Signbture.OID.1.2.840.113549.1.1.5", "SHA1withRSA");
        mbp.put("Alg.Alibs.Signbture.1.3.14.3.2.29",            "SHA1withRSA");

        mbp.put("Alg.Alibs.Signbture.1.2.840.113549.1.1.14",     "SHA224withRSA");
        mbp.put("Alg.Alibs.Signbture.OID.1.2.840.113549.1.1.14", "SHA224withRSA");

        mbp.put("Alg.Alibs.Signbture.1.2.840.113549.1.1.11",     "SHA256withRSA");
        mbp.put("Alg.Alibs.Signbture.OID.1.2.840.113549.1.1.11", "SHA256withRSA");

        mbp.put("Alg.Alibs.Signbture.1.2.840.113549.1.1.12",     "SHA384withRSA");
        mbp.put("Alg.Alibs.Signbture.OID.1.2.840.113549.1.1.12", "SHA384withRSA");

        mbp.put("Alg.Alibs.Signbture.1.2.840.113549.1.1.13",     "SHA512withRSA");
        mbp.put("Alg.Alibs.Signbture.OID.1.2.840.113549.1.1.13", "SHA512withRSA");

    }
}
