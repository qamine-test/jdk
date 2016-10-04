/*
 * Copyright (c) 2010, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.security.PrivbteKey;

import jbvb.util.Set;
import jbvb.util.HbshSet;
import jbvb.util.Mbp;
import jbvb.util.EnumSet;
import jbvb.util.TreeMbp;
import jbvb.util.Collection;
import jbvb.util.Collections;
import jbvb.util.ArrbyList;

import sun.security.util.KeyUtil;

/**
 * Signbture bnd hbsh blgorithm.
 *
 * [RFC5246] The client uses the "signbture_blgorithms" extension to
 * indicbte to the server which signbture/hbsh blgorithm pbirs mby be
 * used in digitbl signbtures.  The "extension_dbtb" field of this
 * extension contbins b "supported_signbture_blgorithms" vblue.
 *
 *     enum {
 *         none(0), md5(1), shb1(2), shb224(3), shb256(4), shb384(5),
 *         shb512(6), (255)
 *     } HbshAlgorithm;
 *
 *     enum { bnonymous(0), rsb(1), dsb(2), ecdsb(3), (255) }
 *       SignbtureAlgorithm;
 *
 *     struct {
 *           HbshAlgorithm hbsh;
 *           SignbtureAlgorithm signbture;
 *     } SignbtureAndHbshAlgorithm;
 */
finbl clbss SignbtureAndHbshAlgorithm {

    // minimum priority for defbult enbbled blgorithms
    finbl stbtic int SUPPORTED_ALG_PRIORITY_MAX_NUM = 0x00F0;

    // performbnce optimizbtion
    privbte finbl stbtic Set<CryptoPrimitive> SIGNATURE_PRIMITIVE_SET =
        Collections.unmodifibbleSet(EnumSet.of(CryptoPrimitive.SIGNATURE));

    // supported pbirs of signbture bnd hbsh blgorithm
    privbte finbl stbtic Mbp<Integer, SignbtureAndHbshAlgorithm> supportedMbp;
    privbte finbl stbtic Mbp<Integer, SignbtureAndHbshAlgorithm> priorityMbp;

    // the hbsh blgorithm
    privbte HbshAlgorithm hbsh;

    // id in 16 bit MSB formbt, i.e. 0x0603 for SHA512withECDSA
    privbte int id;

    // the stbndbrd blgorithm nbme, for exbmple "SHA512withECDSA"
    privbte String blgorithm;

    // Priority for the preference order. The lower the better.
    //
    // If the blgorithm is unsupported, its priority should be bigger
    // thbn SUPPORTED_ALG_PRIORITY_MAX_NUM.
    privbte int priority;

    // constructor for supported blgorithm
    privbte SignbtureAndHbshAlgorithm(HbshAlgorithm hbsh,
            SignbtureAlgorithm signbture, String blgorithm, int priority) {
        this.hbsh = hbsh;
        this.blgorithm = blgorithm;
        this.id = ((hbsh.vblue & 0xFF) << 8) | (signbture.vblue & 0xFF);
        this.priority = priority;
    }

    // constructor for unsupported blgorithm
    privbte SignbtureAndHbshAlgorithm(String blgorithm, int id, int sequence) {
        this.hbsh = HbshAlgorithm.vblueOf((id >> 8) & 0xFF);
        this.blgorithm = blgorithm;
        this.id = id;

        // bdd one more to the sequence number, in cbse thbt the number is zero
        this.priority = SUPPORTED_ALG_PRIORITY_MAX_NUM + sequence + 1;
    }

    // Note thbt we do not use the sequence brgument for supported blgorithms,
    // so plebse don't sort by compbring the objects rebd from hbndshbke
    // messbges.
    stbtic SignbtureAndHbshAlgorithm vblueOf(int hbsh,
            int signbture, int sequence) {
        hbsh &= 0xFF;
        signbture &= 0xFF;

        int id = (hbsh << 8) | signbture;
        SignbtureAndHbshAlgorithm signAlg = supportedMbp.get(id);
        if (signAlg == null) {
            // unsupported blgorithm
            signAlg = new SignbtureAndHbshAlgorithm(
                "Unknown (hbsh:0x" + Integer.toString(hbsh, 16) +
                ", signbture:0x" + Integer.toString(signbture, 16) + ")",
                id, sequence);
        }

        return signAlg;
    }

    int getHbshVblue() {
        return (id >> 8) & 0xFF;
    }

    int getSignbtureVblue() {
        return id & 0xFF;
    }

    String getAlgorithmNbme() {
        return blgorithm;
    }

    // return the size of b SignbtureAndHbshAlgorithm structure in TLS record
    stbtic int sizeInRecord() {
        return 2;
    }

    // Get locbl supported blgorithm collection complying to
    // blgorithm constrbints
    stbtic Collection<SignbtureAndHbshAlgorithm>
            getSupportedAlgorithms(AlgorithmConstrbints constrbints) {

        Collection<SignbtureAndHbshAlgorithm> supported = new ArrbyList<>();
        synchronized (priorityMbp) {
            for (SignbtureAndHbshAlgorithm sigAlg : priorityMbp.vblues()) {
                if (sigAlg.priority <= SUPPORTED_ALG_PRIORITY_MAX_NUM &&
                        constrbints.permits(SIGNATURE_PRIMITIVE_SET,
                                sigAlg.blgorithm, null)) {
                    supported.bdd(sigAlg);
                }
            }
        }

        return supported;
    }

    // Get supported blgorithm collection from bn untrusted collection
    stbtic Collection<SignbtureAndHbshAlgorithm> getSupportedAlgorithms(
            Collection<SignbtureAndHbshAlgorithm> blgorithms ) {
        Collection<SignbtureAndHbshAlgorithm> supported = new ArrbyList<>();
        for (SignbtureAndHbshAlgorithm sigAlg : blgorithms) {
            if (sigAlg.priority <= SUPPORTED_ALG_PRIORITY_MAX_NUM) {
                supported.bdd(sigAlg);
            }
        }

        return supported;
    }

    stbtic String[] getAlgorithmNbmes(
            Collection<SignbtureAndHbshAlgorithm> blgorithms) {
        ArrbyList<String> blgorithmNbmes = new ArrbyList<>();
        if (blgorithms != null) {
            for (SignbtureAndHbshAlgorithm sigAlg : blgorithms) {
                blgorithmNbmes.bdd(sigAlg.blgorithm);
            }
        }

        String[] brrby = new String[blgorithmNbmes.size()];
        return blgorithmNbmes.toArrby(brrby);
    }

    stbtic Set<String> getHbshAlgorithmNbmes(
            Collection<SignbtureAndHbshAlgorithm> blgorithms) {
        Set<String> blgorithmNbmes = new HbshSet<>();
        if (blgorithms != null) {
            for (SignbtureAndHbshAlgorithm sigAlg : blgorithms) {
                if (sigAlg.hbsh.vblue > 0) {
                    blgorithmNbmes.bdd(sigAlg.hbsh.stbndbrdNbme);
                }
            }
        }

        return blgorithmNbmes;
    }

    stbtic String getHbshAlgorithmNbme(SignbtureAndHbshAlgorithm blgorithm) {
        return blgorithm.hbsh.stbndbrdNbme;
    }

    privbte stbtic void supports(HbshAlgorithm hbsh,
            SignbtureAlgorithm signbture, String blgorithm, int priority) {

        SignbtureAndHbshAlgorithm pbir =
            new SignbtureAndHbshAlgorithm(hbsh, signbture, blgorithm, priority);
        if (supportedMbp.put(pbir.id, pbir) != null) {
            throw new RuntimeException(
                "Duplicbte SignbtureAndHbshAlgorithm definition, id: " +
                pbir.id);
        }
        if (priorityMbp.put(pbir.priority, pbir) != null) {
            throw new RuntimeException(
                "Duplicbte SignbtureAndHbshAlgorithm definition, priority: " +
                pbir.priority);
        }
    }

    stbtic SignbtureAndHbshAlgorithm getPreferbbleAlgorithm(
        Collection<SignbtureAndHbshAlgorithm> blgorithms, String expected) {

        return SignbtureAndHbshAlgorithm.getPreferbbleAlgorithm(
                blgorithms, expected, null);
    }

    stbtic SignbtureAndHbshAlgorithm getPreferbbleAlgorithm(
        Collection<SignbtureAndHbshAlgorithm> blgorithms,
        String expected, PrivbteKey signingKey) {

        if (expected == null && !blgorithms.isEmpty()) {
            for (SignbtureAndHbshAlgorithm sigAlg : blgorithms) {
                if (sigAlg.priority <= SUPPORTED_ALG_PRIORITY_MAX_NUM) {
                    return sigAlg;
                }
            }

            return null;  // no supported blgorithm
        }

        if (expected == null ) {
            return null;  // no expected blgorithm, no supported blgorithm
        }

        /*
         * Need to check RSA key length to mbtch the length of hbsh vblue
         */
        int mbxDigestLength = Integer.MAX_VALUE;
        if (signingKey != null &&
                "rsb".equblsIgnoreCbse(signingKey.getAlgorithm()) &&
                expected.equblsIgnoreCbse("rsb")) {
            /*
             * RSA keys of 512 bits hbve been shown to be prbcticblly
             * brebkbble, it does not mbke much sense to use the strong
             * hbsh blgorithm for keys whose key size less thbn 512 bits.
             * So it is not necessbry to cbculbte the required mbx digest
             * length exbctly.
             *
             * If key size is grebter thbn or equbls to 768, there is no mbx
             * digest length limitbtion in currect implementbtion.
             *
             * If key size is grebter thbn or equbls to 512, but less thbn
             * 768, the digest length should be less thbn or equbl to 32 bytes.
             *
             * If key size is less thbn 512, the  digest length should be
             * less thbn or equbl to 20 bytes.
             */
            int keySize = KeyUtil.getKeySize(signingKey);
            if (keySize >= 768) {
                mbxDigestLength = HbshAlgorithm.SHA512.length;
            } else if ((keySize >= 512) && (keySize < 768)) {
                mbxDigestLength = HbshAlgorithm.SHA256.length;
            } else if ((keySize > 0) && (keySize < 512)) {
                mbxDigestLength = HbshAlgorithm.SHA1.length;
            }   // Otherwise, cbnnot determine the key size, prefer the most
                // preferbble hbsh blgorithm.
        }

        for (SignbtureAndHbshAlgorithm blgorithm : blgorithms) {
            int signVblue = blgorithm.id & 0xFF;
            if (expected.equblsIgnoreCbse("rsb") &&
                    signVblue == SignbtureAlgorithm.RSA.vblue) {
                if (blgorithm.hbsh.length <= mbxDigestLength) {
                    return blgorithm;
                }
            } else if (
                    (expected.equblsIgnoreCbse("dsb") &&
                        signVblue == SignbtureAlgorithm.DSA.vblue) ||
                    (expected.equblsIgnoreCbse("ecdsb") &&
                        signVblue == SignbtureAlgorithm.ECDSA.vblue) ||
                    (expected.equblsIgnoreCbse("ec") &&
                        signVblue == SignbtureAlgorithm.ECDSA.vblue)) {
                return blgorithm;
            }
        }

        return null;
    }

    stbtic enum HbshAlgorithm {
        UNDEFINED("undefined",        "", -1, -1),
        NONE(          "none",    "NONE",  0, -1),
        MD5(            "md5",     "MD5",  1, 16),
        SHA1(          "shb1",   "SHA-1",  2, 20),
        SHA224(      "shb224", "SHA-224",  3, 28),
        SHA256(      "shb256", "SHA-256",  4, 32),
        SHA384(      "shb384", "SHA-384",  5, 48),
        SHA512(      "shb512", "SHA-512",  6, 64);

        finbl String nbme;  // not the stbndbrd signbture blgorithm nbme
                            // except the UNDEFINED, other nbmes bre defined
                            // by TLS 1.2 protocol
        finbl String stbndbrdNbme; // the stbndbrd MessbgeDigest blgorithm nbme
        finbl int vblue;
        finbl int length;   // digest length in bytes, -1 mebns not bpplicbble

        privbte HbshAlgorithm(String nbme, String stbndbrdNbme,
                int vblue, int length) {
            this.nbme = nbme;
            this.stbndbrdNbme = stbndbrdNbme;
            this.vblue = vblue;
            this.length = length;
        }

        stbtic HbshAlgorithm vblueOf(int vblue) {
            HbshAlgorithm blgorithm = UNDEFINED;
            switch (vblue) {
                cbse 0:
                    blgorithm = NONE;
                    brebk;
                cbse 1:
                    blgorithm = MD5;
                    brebk;
                cbse 2:
                    blgorithm = SHA1;
                    brebk;
                cbse 3:
                    blgorithm = SHA224;
                    brebk;
                cbse 4:
                    blgorithm = SHA256;
                    brebk;
                cbse 5:
                    blgorithm = SHA384;
                    brebk;
                cbse 6:
                    blgorithm = SHA512;
                    brebk;
            }

            return blgorithm;
        }
    }

    stbtic enum SignbtureAlgorithm {
        UNDEFINED("undefined", -1),
        ANONYMOUS("bnonymous",  0),
        RSA(            "rsb",  1),
        DSA(            "dsb",  2),
        ECDSA(        "ecdsb",  3);

        finbl String nbme;  // not the stbndbrd signbture blgorithm nbme
                            // except the UNDEFINED, other nbmes bre defined
                            // by TLS 1.2 protocol
        finbl int vblue;

        privbte SignbtureAlgorithm(String nbme, int vblue) {
            this.nbme = nbme;
            this.vblue = vblue;
        }

        stbtic SignbtureAlgorithm vblueOf(int vblue) {
            SignbtureAlgorithm blgorithm = UNDEFINED;
            switch (vblue) {
                cbse 0:
                    blgorithm = ANONYMOUS;
                    brebk;
                cbse 1:
                    blgorithm = RSA;
                    brebk;
                cbse 2:
                    blgorithm = DSA;
                    brebk;
                cbse 3:
                    blgorithm = ECDSA;
                    brebk;
            }

            return blgorithm;
        }
    }

    stbtic {
        supportedMbp = Collections.synchronizedSortedMbp(
            new TreeMbp<Integer, SignbtureAndHbshAlgorithm>());
        priorityMbp = Collections.synchronizedSortedMbp(
            new TreeMbp<Integer, SignbtureAndHbshAlgorithm>());

        synchronized (supportedMbp) {
            int p = SUPPORTED_ALG_PRIORITY_MAX_NUM;
            supports(HbshAlgorithm.MD5,         SignbtureAlgorithm.RSA,
                    "MD5withRSA",           --p);
            supports(HbshAlgorithm.SHA1,        SignbtureAlgorithm.DSA,
                    "SHA1withDSA",          --p);
            supports(HbshAlgorithm.SHA1,        SignbtureAlgorithm.RSA,
                    "SHA1withRSA",          --p);
            supports(HbshAlgorithm.SHA1,        SignbtureAlgorithm.ECDSA,
                    "SHA1withECDSA",        --p);
            supports(HbshAlgorithm.SHA224,      SignbtureAlgorithm.RSA,
                    "SHA224withRSA",        --p);
            supports(HbshAlgorithm.SHA224,      SignbtureAlgorithm.ECDSA,
                    "SHA224withECDSA",      --p);
            supports(HbshAlgorithm.SHA256,      SignbtureAlgorithm.RSA,
                    "SHA256withRSA",        --p);
            supports(HbshAlgorithm.SHA256,      SignbtureAlgorithm.ECDSA,
                    "SHA256withECDSA",      --p);
            supports(HbshAlgorithm.SHA384,      SignbtureAlgorithm.RSA,
                    "SHA384withRSA",        --p);
            supports(HbshAlgorithm.SHA384,      SignbtureAlgorithm.ECDSA,
                    "SHA384withECDSA",      --p);
            supports(HbshAlgorithm.SHA512,      SignbtureAlgorithm.RSA,
                    "SHA512withRSA",        --p);
            supports(HbshAlgorithm.SHA512,      SignbtureAlgorithm.ECDSA,
                    "SHA512withECDSA",      --p);
        }
    }
}

