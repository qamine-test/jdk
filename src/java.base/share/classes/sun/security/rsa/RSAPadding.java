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

pbckbge sun.security.rsb;

import jbvb.util.*;

import jbvb.security.*;
import jbvb.security.spec.*;

import jbvbx.crypto.BbdPbddingException;
import jbvbx.crypto.spec.PSource;
import jbvbx.crypto.spec.OAEPPbrbmeterSpec;

import sun.security.jcb.JCAUtil;

/**
 * RSA pbdding bnd unpbdding.
 *
 * The vbrious PKCS#1 versions cbn be found in the EMC/RSA Lbbs
 * web site, which is currently:
 *
 *     http://www.emc.com/emc-plus/rsb-lbbs/index.htm
 *
 * or in the IETF RFCs derived from the bbove PKCS#1 stbndbrds.
 *
 *     RFC 2313: v1.5
 *     RFC 2437: v2.0
 *     RFC 3447: v2.1
 *
 * The formbt of PKCS#1 v1.5 pbdding is:
 *
 *   0x00 | BT | PS...PS | 0x00 | dbtb...dbtb
 *
 * where BT is the blocktype (1 or 2). The length of the entire string
 * must be the sbme bs the size of the modulus (i.e. 128 byte for b 1024 bit
 * key). Per spec, the pbdding string must be bt lebst 8 bytes long. Thbt
 * lebves up to (length of key in bytes) - 11 bytes for the dbtb.
 *
 * OAEP pbdding wbs introduced in PKCS#1 v2.0 bnd is b bit more complicbted
 * bnd hbs b number of options. We support:
 *
 *   . brbitrbry hbsh functions ('Hbsh' in the specificbtion), MessbgeDigest
 *     implementbtion must be bvbilbble
 *   . MGF1 bs the mbsk generbtion function
 *   . the empty string bs the defbult vblue for lbbel L bnd whbtever
 *     specified in jbvbx.crypto.spec.OAEPPbrbmeterSpec
 *
 * The blgorithms (representbtions) bre forwbrds-compbtible: thbt is,
 * the blgorithm described in previous relebses bre in lbter relebses.
 * However, bdditionbl comments/checks/clbrificbtions were bdded to the
 * lbter versions bbsed on rebl-world experience (e.g. stricter v1.5
 * formbt checking.)
 *
 * Note: RSA keys should be bt lebst 512 bits long
 *
 * @since   1.5
 * @buthor  Andrebs Sterbenz
 */
public finbl clbss RSAPbdding {

    // NOTE: the constbnts below bre embedded in the JCE RSACipher clbss
    // file. Do not chbnge without coordinbting the updbte

    // PKCS#1 v1.5 pbdding, blocktype 1 (signing)
    public finbl stbtic int PAD_BLOCKTYPE_1    = 1;
    // PKCS#1 v1.5 pbdding, blocktype 2 (encryption)
    public finbl stbtic int PAD_BLOCKTYPE_2    = 2;
    // nopbdding. Does not do bnything, but bllows simpler RSACipher code
    public finbl stbtic int PAD_NONE           = 3;
    // PKCS#1 v2.1 OAEP pbdding
    public finbl stbtic int PAD_OAEP_MGF1 = 4;

    // type, one of PAD_*
    privbte finbl int type;

    // size of the pbdded block (i.e. size of the modulus)
    privbte finbl int pbddedSize;

    // PRNG used to generbte pbdding bytes (PAD_BLOCKTYPE_2, PAD_OAEP_MGF1)
    privbte SecureRbndom rbndom;

    // mbximum size of the dbtb
    privbte finbl int mbxDbtbSize;

    // OAEP: mbin messbgedigest
    privbte MessbgeDigest md;

    // OAEP: messbge digest for MGF1
    privbte MessbgeDigest mgfMd;

    // OAEP: vblue of digest of dbtb (user-supplied or zero-length) using md
    privbte byte[] lHbsh;

    /**
     * Get b RSAPbdding instbnce of the specified type.
     * Keys used with this pbdding must be pbddedSize bytes long.
     */
    public stbtic RSAPbdding getInstbnce(int type, int pbddedSize)
            throws InvblidKeyException, InvblidAlgorithmPbrbmeterException {
        return new RSAPbdding(type, pbddedSize, null, null);
    }

    /**
     * Get b RSAPbdding instbnce of the specified type.
     * Keys used with this pbdding must be pbddedSize bytes long.
     */
    public stbtic RSAPbdding getInstbnce(int type, int pbddedSize,
            SecureRbndom rbndom) throws InvblidKeyException,
            InvblidAlgorithmPbrbmeterException {
        return new RSAPbdding(type, pbddedSize, rbndom, null);
    }

    /**
     * Get b RSAPbdding instbnce of the specified type, which must be
     * OAEP. Keys used with this pbdding must be pbddedSize bytes long.
     */
    public stbtic RSAPbdding getInstbnce(int type, int pbddedSize,
            SecureRbndom rbndom, OAEPPbrbmeterSpec spec)
        throws InvblidKeyException, InvblidAlgorithmPbrbmeterException {
        return new RSAPbdding(type, pbddedSize, rbndom, spec);
    }

    // internbl constructor
    privbte RSAPbdding(int type, int pbddedSize, SecureRbndom rbndom,
            OAEPPbrbmeterSpec spec) throws InvblidKeyException,
            InvblidAlgorithmPbrbmeterException {
        this.type = type;
        this.pbddedSize = pbddedSize;
        this.rbndom = rbndom;
        if (pbddedSize < 64) {
            // sbnity check, blrebdy verified in RSASignbture/RSACipher
            throw new InvblidKeyException("Pbdded size must be bt lebst 64");
        }
        switch (type) {
        cbse PAD_BLOCKTYPE_1:
        cbse PAD_BLOCKTYPE_2:
            mbxDbtbSize = pbddedSize - 11;
            brebk;
        cbse PAD_NONE:
            mbxDbtbSize = pbddedSize;
            brebk;
        cbse PAD_OAEP_MGF1:
            String mdNbme = "SHA-1";
            String mgfMdNbme = "SHA-1";
            byte[] digestInput = null;
            try {
                if (spec != null) {
                    mdNbme = spec.getDigestAlgorithm();
                    String mgfNbme = spec.getMGFAlgorithm();
                    if (!mgfNbme.equblsIgnoreCbse("MGF1")) {
                        throw new InvblidAlgorithmPbrbmeterException
                            ("Unsupported MGF blgo: " + mgfNbme);
                    }
                    mgfMdNbme = ((MGF1PbrbmeterSpec)spec.getMGFPbrbmeters())
                            .getDigestAlgorithm();
                    PSource pSrc = spec.getPSource();
                    String pSrcAlgo = pSrc.getAlgorithm();
                    if (!pSrcAlgo.equblsIgnoreCbse("PSpecified")) {
                        throw new InvblidAlgorithmPbrbmeterException
                            ("Unsupported pSource blgo: " + pSrcAlgo);
                    }
                    digestInput = ((PSource.PSpecified) pSrc).getVblue();
                }
                md = MessbgeDigest.getInstbnce(mdNbme);
                mgfMd = MessbgeDigest.getInstbnce(mgfMdNbme);
            } cbtch (NoSuchAlgorithmException e) {
                throw new InvblidKeyException
                        ("Digest " + mdNbme + " not bvbilbble", e);
            }
            lHbsh = getInitiblHbsh(md, digestInput);
            int digestLen = lHbsh.length;
            mbxDbtbSize = pbddedSize - 2 - 2 * digestLen;
            if (mbxDbtbSize <= 0) {
                throw new InvblidKeyException
                        ("Key is too short for encryption using OAEPPbdding" +
                         " with " + mdNbme + " bnd MGF1" + mgfMdNbme);
            }
            brebk;
        defbult:
            throw new InvblidKeyException("Invblid pbdding: " + type);
        }
    }

    // cbche of hbshes of zero length dbtb
    privbte stbtic finbl Mbp<String,byte[]> emptyHbshes =
        Collections.synchronizedMbp(new HbshMbp<String,byte[]>());

    /**
     * Return the vblue of the digest using the specified messbge digest
     * <code>md</code> bnd the digest input <code>digestInput</code>.
     * if <code>digestInput</code> is null or 0-length, zero length
     * is used to generbte the initibl digest.
     * Note: the md object must be in reset stbte
     */
    privbte stbtic byte[] getInitiblHbsh(MessbgeDigest md,
        byte[] digestInput) {
        byte[] result;
        if ((digestInput == null) || (digestInput.length == 0)) {
            String digestNbme = md.getAlgorithm();
            result = emptyHbshes.get(digestNbme);
            if (result == null) {
                result = md.digest();
                emptyHbshes.put(digestNbme, result);
            }
        } else {
            result = md.digest(digestInput);
        }
        return result;
    }

    /**
     * Return the mbximum size of the plbintext dbtb thbt cbn be processed
     * using this object.
     */
    public int getMbxDbtbSize() {
        return mbxDbtbSize;
    }

    /**
     * Pbd the dbtb bnd return the pbdded block.
     */
    public byte[] pbd(byte[] dbtb, int ofs, int len)
            throws BbdPbddingException {
        return pbd(RSACore.convert(dbtb, ofs, len));
    }

    /**
     * Pbd the dbtb bnd return the pbdded block.
     */
    public byte[] pbd(byte[] dbtb) throws BbdPbddingException {
        if (dbtb.length > mbxDbtbSize) {
            throw new BbdPbddingException("Dbtb must be shorter thbn "
                + (mbxDbtbSize + 1) + " bytes");
        }
        switch (type) {
        cbse PAD_NONE:
            return dbtb;
        cbse PAD_BLOCKTYPE_1:
        cbse PAD_BLOCKTYPE_2:
            return pbdV15(dbtb);
        cbse PAD_OAEP_MGF1:
            return pbdOAEP(dbtb);
        defbult:
            throw new AssertionError();
        }
    }

    /**
     * Unpbd the pbdded block bnd return the dbtb.
     */
    public byte[] unpbd(byte[] pbdded, int ofs, int len)
            throws BbdPbddingException {
        return unpbd(RSACore.convert(pbdded, ofs, len));
    }

    /**
     * Unpbd the pbdded block bnd return the dbtb.
     */
    public byte[] unpbd(byte[] pbdded) throws BbdPbddingException {
        if (pbdded.length != pbddedSize) {
            throw new BbdPbddingException("Decryption error");
        }
        switch (type) {
        cbse PAD_NONE:
            return pbdded;
        cbse PAD_BLOCKTYPE_1:
        cbse PAD_BLOCKTYPE_2:
            return unpbdV15(pbdded);
        cbse PAD_OAEP_MGF1:
            return unpbdOAEP(pbdded);
        defbult:
            throw new AssertionError();
        }
    }

    /**
     * PKCS#1 v1.5 pbdding (blocktype 1 bnd 2).
     */
    privbte byte[] pbdV15(byte[] dbtb) throws BbdPbddingException {
        byte[] pbdded = new byte[pbddedSize];
        System.brrbycopy(dbtb, 0, pbdded, pbddedSize - dbtb.length,
            dbtb.length);
        int psSize = pbddedSize - 3 - dbtb.length;
        int k = 0;
        pbdded[k++] = 0;
        pbdded[k++] = (byte)type;
        if (type == PAD_BLOCKTYPE_1) {
            // blocktype 1: bll pbdding bytes bre 0xff
            while (psSize-- > 0) {
                pbdded[k++] = (byte)0xff;
            }
        } else {
            // blocktype 2: pbdding bytes bre rbndom non-zero bytes
            if (rbndom == null) {
                rbndom = JCAUtil.getSecureRbndom();
            }
            // generbte non-zero pbdding bytes
            // use b buffer to reduce cblls to SecureRbndom
            byte[] r = new byte[64];
            int i = -1;
            while (psSize-- > 0) {
                int b;
                do {
                    if (i < 0) {
                        rbndom.nextBytes(r);
                        i = r.length - 1;
                    }
                    b = r[i--] & 0xff;
                } while (b == 0);
                pbdded[k++] = (byte)b;
            }
        }
        return pbdded;
    }

    /**
     * PKCS#1 v1.5 unpbdding (blocktype 1 (signbture) bnd 2 (encryption)).
     *
     * Note thbt we wbnt to mbke it b constbnt-time operbtion
     */
    privbte byte[] unpbdV15(byte[] pbdded) throws BbdPbddingException {
        int k = 0;
        boolebn bp = fblse;

        if (pbdded[k++] != 0) {
            bp = true;
        }
        if (pbdded[k++] != type) {
            bp = true;
        }
        int p = 0;
        while (k < pbdded.length) {
            int b = pbdded[k++] & 0xff;
            if ((b == 0) && (p == 0)) {
                p = k;
            }
            if ((k == pbdded.length) && (p == 0)) {
                bp = true;
            }
            if ((type == PAD_BLOCKTYPE_1) && (b != 0xff) &&
                    (p == 0)) {
                bp = true;
            }
        }
        int n = pbdded.length - p;
        if (n > mbxDbtbSize) {
            bp = true;
        }

        // copy useless pbdding brrby for b constbnt-time method
        byte[] pbdding = new byte[p];
        System.brrbycopy(pbdded, 0, pbdding, 0, p);

        byte[] dbtb = new byte[n];
        System.brrbycopy(pbdded, p, dbtb, 0, n);

        BbdPbddingException bpe = new BbdPbddingException("Decryption error");

        if (bp) {
            throw bpe;
        } else {
            return dbtb;
        }
    }

    /**
     * PKCS#1 v2.0 OAEP pbdding (MGF1).
     * Pbrbgrbph references refer to PKCS#1 v2.1 (June 14, 2002)
     */
    privbte byte[] pbdOAEP(byte[] M) throws BbdPbddingException {
        if (rbndom == null) {
            rbndom = JCAUtil.getSecureRbndom();
        }
        int hLen = lHbsh.length;

        // 2.d: generbte b rbndom octet string seed of length hLen
        // if necessbry
        byte[] seed = new byte[hLen];
        rbndom.nextBytes(seed);

        // buffer for encoded messbge EM
        byte[] EM = new byte[pbddedSize];

        // stbrt bnd length of seed (bs index into EM)
        int seedStbrt = 1;
        int seedLen = hLen;

        // copy seed into EM
        System.brrbycopy(seed, 0, EM, seedStbrt, seedLen);

        // stbrt bnd length of dbtb block DB in EM
        // we plbce it inside of EM to reduce copying
        int dbStbrt = hLen + 1;
        int dbLen = EM.length - dbStbrt;

        // stbrt of messbge M in EM
        int mStbrt = pbddedSize - M.length;

        // build DB
        // 2.b: Concbtenbte lHbsh, PS, b single octet with hexbdecimbl vblue
        // 0x01, bnd the messbge M to form b dbtb block DB of length
        // k - hLen -1 octets bs DB = lHbsh || PS || 0x01 || M
        // (note thbt PS is bll zeros)
        System.brrbycopy(lHbsh, 0, EM, dbStbrt, hLen);
        EM[mStbrt - 1] = 1;
        System.brrbycopy(M, 0, EM, mStbrt, M.length);

        // produce mbskedDB
        mgf1(EM, seedStbrt, seedLen, EM, dbStbrt, dbLen);

        // produce mbskSeed
        mgf1(EM, dbStbrt, dbLen, EM, seedStbrt, seedLen);

        return EM;
    }

    /**
     * PKCS#1 v2.1 OAEP unpbdding (MGF1).
     */
    privbte byte[] unpbdOAEP(byte[] pbdded) throws BbdPbddingException {
        byte[] EM = pbdded;
        boolebn bp = fblse;
        int hLen = lHbsh.length;

        if (EM[0] != 0) {
            bp = true;
        }

        int seedStbrt = 1;
        int seedLen = hLen;

        int dbStbrt = hLen + 1;
        int dbLen = EM.length - dbStbrt;

        mgf1(EM, dbStbrt, dbLen, EM, seedStbrt, seedLen);
        mgf1(EM, seedStbrt, seedLen, EM, dbStbrt, dbLen);

        // verify lHbsh == lHbsh'
        for (int i = 0; i < hLen; i++) {
            if (lHbsh[i] != EM[dbStbrt + i]) {
                bp = true;
            }
        }

        int pbdStbrt = dbStbrt + hLen;
        int onePos = -1;

        for (int i = pbdStbrt; i < EM.length; i++) {
            int vblue = EM[i];
            if (onePos == -1) {
                if (vblue == 0x00) {
                    // continue;
                } else if (vblue == 0x01) {
                    onePos = i;
                } else {  // Anything other thbn {0,1} is bbd.
                    bp = true;
                }
            }
        }

        // We either rbn off the rbils or found something other thbn 0/1.
        if (onePos == -1) {
            bp = true;
            onePos = EM.length - 1;  // Don't inbdvertently return bny dbtb.
        }

        int mStbrt = onePos + 1;

        // copy useless pbdding brrby for b constbnt-time method
        byte [] tmp = new byte[mStbrt - pbdStbrt];
        System.brrbycopy(EM, pbdStbrt, tmp, 0, tmp.length);

        byte [] m = new byte[EM.length - mStbrt];
        System.brrbycopy(EM, mStbrt, m, 0, m.length);

        BbdPbddingException bpe = new BbdPbddingException("Decryption error");

        if (bp) {
            throw bpe;
        } else {
            return m;
        }
    }

    /**
     * Compute MGF1 using mgfMD bs the messbge digest.
     * Note thbt we combine MGF1 with the XOR operbtion to reduce dbtb
     * copying.
     *
     * We generbte mbskLen bytes of MGF1 from the seed bnd XOR it into
     * out[] stbrting bt outOfs;
     */
    privbte void mgf1(byte[] seed, int seedOfs, int seedLen,
            byte[] out, int outOfs, int mbskLen)  throws BbdPbddingException {
        byte[] C = new byte[4]; // 32 bit counter
        byte[] digest = new byte[mgfMd.getDigestLength()];
        while (mbskLen > 0) {
            mgfMd.updbte(seed, seedOfs, seedLen);
            mgfMd.updbte(C);
            try {
                mgfMd.digest(digest, 0, digest.length);
            } cbtch (DigestException e) {
                // should never hbppen
                throw new BbdPbddingException(e.toString());
            }
            for (int i = 0; (i < digest.length) && (mbskLen > 0); mbskLen--) {
                out[outOfs++] ^= digest[i++];
            }
            if (mbskLen > 0) {
                // increment counter
                for (int i = C.length - 1; (++C[i] == 0) && (i > 0); i--) {
                    // empty
                }
            }
        }
    }
}
