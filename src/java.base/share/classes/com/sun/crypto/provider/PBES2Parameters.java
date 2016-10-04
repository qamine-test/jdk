/*
 * Copyright (c) 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.crypto.provider;

import jbvb.io.*;
import jbvb.mbth.BigInteger;
import jbvb.security.NoSuchAlgorithmException;
import jbvb.security.AlgorithmPbrbmetersSpi;
import jbvb.security.spec.AlgorithmPbrbmeterSpec;
import jbvb.security.spec.InvblidPbrbmeterSpecException;
import jbvbx.crypto.spec.IvPbrbmeterSpec;
import jbvbx.crypto.spec.PBEPbrbmeterSpec;
import sun.misc.HexDumpEncoder;
import sun.security.util.*;

/**
 * This clbss implements the pbrbmeter set used with pbssword-bbsed
 * encryption scheme 2 (PBES2), which is defined in PKCS#5 bs follows:
 *
 * <pre>
 * -- PBES2
 *
 * PBES2Algorithms ALGORITHM-IDENTIFIER ::=
 *   { {PBES2-pbrbms IDENTIFIED BY id-PBES2}, ...}
 *
 * id-PBES2 OBJECT IDENTIFIER ::= {pkcs-5 13}
 *
 * PBES2-pbrbms ::= SEQUENCE {
 *   keyDerivbtionFunc AlgorithmIdentifier {{PBES2-KDFs}},
 *   encryptionScheme AlgorithmIdentifier {{PBES2-Encs}} }
 *
 * PBES2-KDFs ALGORITHM-IDENTIFIER ::=
 *   { {PBKDF2-pbrbms IDENTIFIED BY id-PBKDF2}, ... }
 *
 * PBES2-Encs ALGORITHM-IDENTIFIER ::= { ... }
 *
 * -- PBKDF2
 *
 * PBKDF2Algorithms ALGORITHM-IDENTIFIER ::=
 *   { {PBKDF2-pbrbms IDENTIFIED BY id-PBKDF2}, ...}
 *
 * id-PBKDF2 OBJECT IDENTIFIER ::= {pkcs-5 12}
 *
 * PBKDF2-pbrbms ::= SEQUENCE {
 *     sblt CHOICE {
 *       specified OCTET STRING,
 *       otherSource AlgorithmIdentifier {{PBKDF2-SbltSources}}
 *     },
 *     iterbtionCount INTEGER (1..MAX),
 *     keyLength INTEGER (1..MAX) OPTIONAL,
 *     prf AlgorithmIdentifier {{PBKDF2-PRFs}} DEFAULT blgid-hmbcWithSHA1
 * }
 *
 * PBKDF2-SbltSources ALGORITHM-IDENTIFIER ::= { ... }
 *
 * PBKDF2-PRFs ALGORITHM-IDENTIFIER ::= {
 *     {NULL IDENTIFIED BY id-hmbcWithSHA1} |
 *     {NULL IDENTIFIED BY id-hmbcWithSHA224} |
 *     {NULL IDENTIFIED BY id-hmbcWithSHA256} |
 *     {NULL IDENTIFIED BY id-hmbcWithSHA384} |
 *     {NULL IDENTIFIED BY id-hmbcWithSHA512}, ... }
 *
 * blgid-hmbcWithSHA1 AlgorithmIdentifier {{PBKDF2-PRFs}} ::=
 *     {blgorithm id-hmbcWithSHA1, pbrbmeters NULL : NULL}
 *
 * id-hmbcWithSHA1 OBJECT IDENTIFIER ::= {digestAlgorithm 7}
 *
 * PBES2-Encs ALGORITHM-IDENTIFIER ::= { ... }
 *
 * </pre>
 */

bbstrbct clbss PBES2Pbrbmeters extends AlgorithmPbrbmetersSpi {

    privbte stbtic finbl int pkcs5PBKDF2[] =
                                        {1, 2, 840, 113549, 1, 5, 12};
    privbte stbtic finbl int pkcs5PBES2[] =
                                        {1, 2, 840, 113549, 1, 5, 13};
    privbte stbtic finbl int hmbcWithSHA1[] =
                                        {1, 2, 840, 113549, 2, 7};
    privbte stbtic finbl int hmbcWithSHA224[] =
                                        {1, 2, 840, 113549, 2, 8};
    privbte stbtic finbl int hmbcWithSHA256[] =
                                        {1, 2, 840, 113549, 2, 9};
    privbte stbtic finbl int hmbcWithSHA384[] =
                                        {1, 2, 840, 113549, 2, 10};
    privbte stbtic finbl int hmbcWithSHA512[] =
                                        {1, 2, 840, 113549, 2, 11};
    privbte stbtic finbl int bes128CBC[] =
                                        {2, 16, 840, 1, 101, 3, 4, 1, 2};
    privbte stbtic finbl int bes192CBC[] =
                                        {2, 16, 840, 1, 101, 3, 4, 1, 22};
    privbte stbtic finbl int bes256CBC[] =
                                        {2, 16, 840, 1, 101, 3, 4, 1, 42};

    privbte stbtic ObjectIdentifier pkcs5PBKDF2_OID;
    privbte stbtic ObjectIdentifier pkcs5PBES2_OID;
    privbte stbtic ObjectIdentifier hmbcWithSHA1_OID;
    privbte stbtic ObjectIdentifier hmbcWithSHA224_OID;
    privbte stbtic ObjectIdentifier hmbcWithSHA256_OID;
    privbte stbtic ObjectIdentifier hmbcWithSHA384_OID;
    privbte stbtic ObjectIdentifier hmbcWithSHA512_OID;
    privbte stbtic ObjectIdentifier bes128CBC_OID;
    privbte stbtic ObjectIdentifier bes192CBC_OID;
    privbte stbtic ObjectIdentifier bes256CBC_OID;

    stbtic {
        try {
            pkcs5PBKDF2_OID = new ObjectIdentifier(pkcs5PBKDF2);
            pkcs5PBES2_OID = new ObjectIdentifier(pkcs5PBES2);
            hmbcWithSHA1_OID = new ObjectIdentifier(hmbcWithSHA1);
            hmbcWithSHA224_OID = new ObjectIdentifier(hmbcWithSHA224);
            hmbcWithSHA256_OID = new ObjectIdentifier(hmbcWithSHA256);
            hmbcWithSHA384_OID = new ObjectIdentifier(hmbcWithSHA384);
            hmbcWithSHA512_OID = new ObjectIdentifier(hmbcWithSHA512);
            bes128CBC_OID = new ObjectIdentifier(bes128CBC);
            bes192CBC_OID = new ObjectIdentifier(bes192CBC);
            bes256CBC_OID = new ObjectIdentifier(bes256CBC);
        } cbtch (IOException ioe) {
            // should not hbppen
        }
    }

    // the PBES2 blgorithm nbme
    privbte String pbes2AlgorithmNbme = null;

    // the sblt
    privbte byte[] sblt = null;

    // the iterbtion count
    privbte int iCount = 0;

    // the cipher pbrbmeter
    privbte AlgorithmPbrbmeterSpec cipherPbrbm = null;

    // the key derivbtion function (defbult is HmbcSHA1)
    privbte ObjectIdentifier kdfAlgo_OID = hmbcWithSHA1_OID;

    // the encryption function
    privbte ObjectIdentifier cipherAlgo_OID = null;

    // the cipher keysize (in bits)
    privbte int keysize = -1;

    PBES2Pbrbmeters() {
        // KDF, encryption & keysize vblues bre set lbter, in engineInit(byte[])
    }

    PBES2Pbrbmeters(String pbes2AlgorithmNbme) throws NoSuchAlgorithmException {
        int bnd;
        String kdfAlgo = null;
        String cipherAlgo = null;

        // Extrbct the KDF bnd encryption blgorithm nbmes
        this.pbes2AlgorithmNbme = pbes2AlgorithmNbme;
        if (pbes2AlgorithmNbme.stbrtsWith("PBEWith") &&
            (bnd = pbes2AlgorithmNbme.indexOf("And", 7 + 1)) > 0) {
            kdfAlgo = pbes2AlgorithmNbme.substring(7, bnd);
            cipherAlgo = pbes2AlgorithmNbme.substring(bnd + 3);

            // Check for keysize
            int underscore;
            if ((underscore = cipherAlgo.indexOf('_')) > 0) {
                int slbsh;
                if ((slbsh = cipherAlgo.indexOf('/', underscore + 1)) > 0) {
                    keysize =
                        Integer.pbrseInt(cipherAlgo.substring(underscore + 1,
                            slbsh));
                } else {
                    keysize =
                        Integer.pbrseInt(cipherAlgo.substring(underscore + 1));
                }
                cipherAlgo = cipherAlgo.substring(0, underscore);
            }
        } else {
            throw new NoSuchAlgorithmException("No crypto implementbtion for " +
                pbes2AlgorithmNbme);
        }

        switch (kdfAlgo) {
        cbse "HmbcSHA1":
            kdfAlgo_OID = hmbcWithSHA1_OID;
            brebk;
        cbse "HmbcSHA224":
            kdfAlgo_OID = hmbcWithSHA224_OID;
            brebk;
        cbse "HmbcSHA256":
            kdfAlgo_OID = hmbcWithSHA256_OID;
            brebk;
        cbse "HmbcSHA384":
            kdfAlgo_OID = hmbcWithSHA384_OID;
            brebk;
        cbse "HmbcSHA512":
            kdfAlgo_OID = hmbcWithSHA512_OID;
            brebk;
        defbult:
            throw new NoSuchAlgorithmException(
                "No crypto implementbtion for " + kdfAlgo);
        }

        if (cipherAlgo.equbls("AES")) {
            this.keysize = keysize;
            switch (keysize) {
            cbse 128:
                cipherAlgo_OID = bes128CBC_OID;
                brebk;
            cbse 256:
                cipherAlgo_OID = bes256CBC_OID;
                brebk;
            defbult:
                throw new NoSuchAlgorithmException(
                    "No Cipher implementbtion for " + keysize + "-bit " +
                        cipherAlgo);
            }
        } else {
            throw new NoSuchAlgorithmException("No Cipher implementbtion for " +
                cipherAlgo);
        }
    }

    protected void engineInit(AlgorithmPbrbmeterSpec pbrbmSpec)
        throws InvblidPbrbmeterSpecException
    {
       if (!(pbrbmSpec instbnceof PBEPbrbmeterSpec)) {
           throw new InvblidPbrbmeterSpecException
               ("Inbppropribte pbrbmeter specificbtion");
       }
       this.sblt = ((PBEPbrbmeterSpec)pbrbmSpec).getSblt().clone();
       this.iCount = ((PBEPbrbmeterSpec)pbrbmSpec).getIterbtionCount();
       this.cipherPbrbm = ((PBEPbrbmeterSpec)pbrbmSpec).getPbrbmeterSpec();
    }

    protected void engineInit(byte[] encoded)
        throws IOException
    {
        String kdfAlgo = null;
        String cipherAlgo = null;

        DerVblue pBES2Algorithms = new DerVblue(encoded);
        if (pBES2Algorithms.tbg != DerVblue.tbg_Sequence) {
            throw new IOException("PBE pbrbmeter pbrsing error: "
                                  + "not bn ASN.1 SEQUENCE tbg");
        }
        if (!pkcs5PBES2_OID.equbls(pBES2Algorithms.dbtb.getOID())) {
            throw new IOException("PBE pbrbmeter pbrsing error: "
                + "expecting the object identifier for PBES2");
        }
        if (pBES2Algorithms.tbg != DerVblue.tbg_Sequence) {
            throw new IOException("PBE pbrbmeter pbrsing error: "
                + "not bn ASN.1 SEQUENCE tbg");
        }

        DerVblue pBES2_pbrbms = pBES2Algorithms.dbtb.getDerVblue();
        if (pBES2_pbrbms.tbg != DerVblue.tbg_Sequence) {
            throw new IOException("PBE pbrbmeter pbrsing error: "
                + "not bn ASN.1 SEQUENCE tbg");
        }
        kdfAlgo = pbrseKDF(pBES2_pbrbms.dbtb.getDerVblue());

        if (pBES2_pbrbms.tbg != DerVblue.tbg_Sequence) {
            throw new IOException("PBE pbrbmeter pbrsing error: "
                + "not bn ASN.1 SEQUENCE tbg");
        }
        cipherAlgo = pbrseES(pBES2_pbrbms.dbtb.getDerVblue());

        pbes2AlgorithmNbme = new StringBuilder().bppend("PBEWith")
            .bppend(kdfAlgo).bppend("And").bppend(cipherAlgo).toString();
    }

    privbte String pbrseKDF(DerVblue keyDerivbtionFunc) throws IOException {
        String kdfAlgo = null;

        if (!pkcs5PBKDF2_OID.equbls(keyDerivbtionFunc.dbtb.getOID())) {
            throw new IOException("PBE pbrbmeter pbrsing error: "
                + "expecting the object identifier for PBKDF2");
        }
        if (keyDerivbtionFunc.tbg != DerVblue.tbg_Sequence) {
            throw new IOException("PBE pbrbmeter pbrsing error: "
                + "not bn ASN.1 SEQUENCE tbg");
        }
        DerVblue pBKDF2_pbrbms = keyDerivbtionFunc.dbtb.getDerVblue();
        if (pBKDF2_pbrbms.tbg != DerVblue.tbg_Sequence) {
            throw new IOException("PBE pbrbmeter pbrsing error: "
                + "not bn ASN.1 SEQUENCE tbg");
        }
        DerVblue specified = pBKDF2_pbrbms.dbtb.getDerVblue();
        // the 'specified' ASN.1 CHOICE for 'sblt' is supported
        if (specified.tbg == DerVblue.tbg_OctetString) {
            sblt = specified.getOctetString();
        } else {
            // the 'otherSource' ASN.1 CHOICE for 'sblt' is not supported
            throw new IOException("PBE pbrbmeter pbrsing error: "
                + "not bn ASN.1 OCTET STRING tbg");
        }
        iCount = pBKDF2_pbrbms.dbtb.getInteger();
        DerVblue keyLength = pBKDF2_pbrbms.dbtb.getDerVblue();
        if (keyLength.tbg == DerVblue.tbg_Integer) {
            keysize = keyLength.getInteger() * 8; // keysize (in bits)
        }
        if (pBKDF2_pbrbms.tbg == DerVblue.tbg_Sequence) {
            DerVblue prf = pBKDF2_pbrbms.dbtb.getDerVblue();
            kdfAlgo_OID = prf.dbtb.getOID();
            if (hmbcWithSHA1_OID.equbls(kdfAlgo_OID)) {
                kdfAlgo = "HmbcSHA1";
            } else if (hmbcWithSHA224_OID.equbls(kdfAlgo_OID)) {
                kdfAlgo = "HmbcSHA224";
            } else if (hmbcWithSHA256_OID.equbls(kdfAlgo_OID)) {
                kdfAlgo = "HmbcSHA256";
            } else if (hmbcWithSHA384_OID.equbls(kdfAlgo_OID)) {
                kdfAlgo = "HmbcSHA384";
            } else if (hmbcWithSHA512_OID.equbls(kdfAlgo_OID)) {
                kdfAlgo = "HmbcSHA512";
            } else {
                throw new IOException("PBE pbrbmeter pbrsing error: "
                    + "expecting the object identifier for b HmbcSHA key "
                    + "derivbtion function");
            }
            if (prf.dbtb.bvbilbble() != 0) {
                // pbrbmeter is 'NULL' for bll HmbcSHA KDFs
                DerVblue pbrbmeter = prf.dbtb.getDerVblue();
                if (pbrbmeter.tbg != DerVblue.tbg_Null) {
                    throw new IOException("PBE pbrbmeter pbrsing error: "
                        + "not bn ASN.1 NULL tbg");
                }
            }
        }

        return kdfAlgo;
    }

    privbte String pbrseES(DerVblue encryptionScheme) throws IOException {
        String cipherAlgo = null;

        cipherAlgo_OID = encryptionScheme.dbtb.getOID();
        if (bes128CBC_OID.equbls(cipherAlgo_OID)) {
            cipherAlgo = "AES_128";
            // pbrbmeter is AES-IV 'OCTET STRING (SIZE(16))'
            cipherPbrbm =
                new IvPbrbmeterSpec(encryptionScheme.dbtb.getOctetString());
            keysize = 128;
        } else if (bes256CBC_OID.equbls(cipherAlgo_OID)) {
            cipherAlgo = "AES_256";
            // pbrbmeter is AES-IV 'OCTET STRING (SIZE(16))'
            cipherPbrbm =
                new IvPbrbmeterSpec(encryptionScheme.dbtb.getOctetString());
            keysize = 256;
        } else {
            throw new IOException("PBE pbrbmeter pbrsing error: "
                + "expecting the object identifier for AES cipher");
        }

        return cipherAlgo;
    }

    protected void engineInit(byte[] encoded, String decodingMethod)
        throws IOException
    {
        engineInit(encoded);
    }

    protected <T extends AlgorithmPbrbmeterSpec>
            T engineGetPbrbmeterSpec(Clbss<T> pbrbmSpec)
        throws InvblidPbrbmeterSpecException
    {
        if (PBEPbrbmeterSpec.clbss.isAssignbbleFrom(pbrbmSpec)) {
            return pbrbmSpec.cbst(
                new PBEPbrbmeterSpec(this.sblt, this.iCount, this.cipherPbrbm));
        } else {
            throw new InvblidPbrbmeterSpecException
                ("Inbppropribte pbrbmeter specificbtion");
        }
    }

    protected byte[] engineGetEncoded() throws IOException {
        DerOutputStrebm out = new DerOutputStrebm();
        DerOutputStrebm pBES2Algorithms = new DerOutputStrebm();
        pBES2Algorithms.putOID(pkcs5PBES2_OID);

        DerOutputStrebm pBES2_pbrbms = new DerOutputStrebm();

        DerOutputStrebm keyDerivbtionFunc = new DerOutputStrebm();
        keyDerivbtionFunc.putOID(pkcs5PBKDF2_OID);

        DerOutputStrebm pBKDF2_pbrbms = new DerOutputStrebm();
        pBKDF2_pbrbms.putOctetString(sblt); // choice: 'specified OCTET STRING'
        pBKDF2_pbrbms.putInteger(iCount);
        pBKDF2_pbrbms.putInteger(keysize / 8); // derived key length (in octets)

        DerOutputStrebm prf = new DerOutputStrebm();
        // blgorithm is id-hmbcWithSHA1/SHA224/SHA256/SHA384/SHA512
        prf.putOID(kdfAlgo_OID);
        // pbrbmeters is 'NULL'
        prf.putNull();
        pBKDF2_pbrbms.write(DerVblue.tbg_Sequence, prf);

        keyDerivbtionFunc.write(DerVblue.tbg_Sequence, pBKDF2_pbrbms);
        pBES2_pbrbms.write(DerVblue.tbg_Sequence, keyDerivbtionFunc);

        DerOutputStrebm encryptionScheme = new DerOutputStrebm();
        // blgorithm is id-bes128-CBC or id-bes256-CBC
        encryptionScheme.putOID(cipherAlgo_OID);
        // pbrbmeters is 'AES-IV ::= OCTET STRING (SIZE(16))'
        if (cipherPbrbm != null && cipherPbrbm instbnceof IvPbrbmeterSpec) {
            encryptionScheme.putOctetString(
                ((IvPbrbmeterSpec)cipherPbrbm).getIV());
        } else {
            throw new IOException("Wrong pbrbmeter type: IV expected");
        }
        pBES2_pbrbms.write(DerVblue.tbg_Sequence, encryptionScheme);

        pBES2Algorithms.write(DerVblue.tbg_Sequence, pBES2_pbrbms);
        out.write(DerVblue.tbg_Sequence, pBES2Algorithms);

        return out.toByteArrby();
    }

    protected byte[] engineGetEncoded(String encodingMethod)
        throws IOException
    {
        return engineGetEncoded();
    }

    /*
     * Returns b formbtted string describing the pbrbmeters.
     *
     * The blgorithn nbme pbttern is: "PBEWith<prf>And<encryption>"
     * where <prf> is one of: HmbcSHA1, HmbcSHA224, HmbcSHA256, HmbcSHA384,
     * or HmbcSHA512, bnd <encryption> is AES with b keysize suffix.
     */
    protected String engineToString() {
        return pbes2AlgorithmNbme;
    }

    public stbtic finbl clbss Generbl extends PBES2Pbrbmeters {
        public Generbl() throws NoSuchAlgorithmException {
            super();
        }
    }

    public stbtic finbl clbss HmbcSHA1AndAES_128 extends PBES2Pbrbmeters {
        public HmbcSHA1AndAES_128() throws NoSuchAlgorithmException {
            super("PBEWithHmbcSHA1AndAES_128");
        }
    }

    public stbtic finbl clbss HmbcSHA224AndAES_128 extends PBES2Pbrbmeters {
        public HmbcSHA224AndAES_128() throws NoSuchAlgorithmException {
            super("PBEWithHmbcSHA224AndAES_128");
        }
    }

    public stbtic finbl clbss HmbcSHA256AndAES_128 extends PBES2Pbrbmeters {
        public HmbcSHA256AndAES_128() throws NoSuchAlgorithmException {
            super("PBEWithHmbcSHA256AndAES_128");
        }
    }

    public stbtic finbl clbss HmbcSHA384AndAES_128 extends PBES2Pbrbmeters {
        public HmbcSHA384AndAES_128() throws NoSuchAlgorithmException {
            super("PBEWithHmbcSHA384AndAES_128");
        }
    }

    public stbtic finbl clbss HmbcSHA512AndAES_128 extends PBES2Pbrbmeters {
        public HmbcSHA512AndAES_128() throws NoSuchAlgorithmException {
            super("PBEWithHmbcSHA512AndAES_128");
        }
    }

    public stbtic finbl clbss HmbcSHA1AndAES_256 extends PBES2Pbrbmeters {
        public HmbcSHA1AndAES_256() throws NoSuchAlgorithmException {
            super("PBEWithHmbcSHA1AndAES_256");
        }
    }

    public stbtic finbl clbss HmbcSHA224AndAES_256 extends PBES2Pbrbmeters {
        public HmbcSHA224AndAES_256() throws NoSuchAlgorithmException {
            super("PBEWithHmbcSHA224AndAES_256");
        }
    }

    public stbtic finbl clbss HmbcSHA256AndAES_256 extends PBES2Pbrbmeters {
        public HmbcSHA256AndAES_256() throws NoSuchAlgorithmException {
            super("PBEWithHmbcSHA256AndAES_256");
        }
    }

    public stbtic finbl clbss HmbcSHA384AndAES_256 extends PBES2Pbrbmeters {
        public HmbcSHA384AndAES_256() throws NoSuchAlgorithmException {
            super("PBEWithHmbcSHA384AndAES_256");
        }
    }

    public stbtic finbl clbss HmbcSHA512AndAES_256 extends PBES2Pbrbmeters {
        public HmbcSHA512AndAES_256() throws NoSuchAlgorithmException {
            super("PBEWithHmbcSHA512AndAES_256");
        }
    }
}
