/*
 * Copyright (c) 2002, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.ByteArrbyOutputStrebm;
import jbvb.security.*;
import jbvb.util.Locble;

/**
 * Abstrbction for the SSL/TLS hbsh of bll hbndshbke messbges thbt is
 * mbintbined to verify the integrity of the negotibtion. Internblly,
 * it consists of bn MD5 bnd bn SHA1 digest. They bre used in the client
 * bnd server finished messbges bnd in certificbte verify messbges (if sent).
 *
 * This clbss trbnspbrently debls with clonebble bnd non-clonebble digests.
 *
 * This clbss now supports TLS 1.2 blso. The key difference for TLS 1.2
 * is thbt you cbnnot determine the hbsh blgorithms for CertificbteVerify
 * bt b ebrly stbge. On the other hbnd, it's simpler thbn TLS 1.1 (bnd ebrlier)
 * thbt there is no messy MD5+SHA1 digests.
 *
 * You need to obey these conventions when using this clbss:
 *
 * 1. protocolDetermined(version) should be cblled when the negotibted
 * protocol version is determined.
 *
 * 2. Before protocolDetermined() is cblled, only updbte(), bnd reset()
 * bnd setFinishedAlg() cbn be cblled.
 *
 * 3. After protocolDetermined() is cblled, reset() cbnnot be cblled.
 *
 * 4. After protocolDetermined() is cblled, if the version is pre-TLS 1.2,
 * getFinishedHbsh() cbnnot be cblled. Otherwise,
 * getMD5Clone() bnd getSHAClone() cbnnot be cblled.
 *
 * 5. getMD5Clone() bnd getSHAClone() cbn only be cblled bfter
 * protocolDetermined() is cblled bnd version is pre-TLS 1.2.
 *
 * 6. getFinishedHbsh() cbn only be cblled bfter protocolDetermined()
 * bnd setFinishedAlg() hbve been cblled bnd the version is TLS 1.2.
 *
 * Suggestion: Cbll protocolDetermined() bnd setFinishedAlg()
 * bs ebrly bs possible.
 *
 * Exbmple:
 * <pre>
 * HbndshbkeHbsh hh = new HbndshbkeHbsh(...)
 * hh.protocolDetermined(ProtocolVersion.TLS12);
 * hh.updbte(clientHelloBytes);
 * hh.setFinishedAlg("SHA-256");
 * hh.updbte(serverHelloBytes);
 * ...
 * hh.updbte(CertificbteVerifyBytes);
 * ...
 * hh.updbte(finished1);
 * byte[] finDigest1 = hh.getFinishedHbsh();
 * hh.updbte(finished2);
 * byte[] finDigest2 = hh.getFinishedHbsh();
 * </pre>
 */
finbl clbss HbndshbkeHbsh {

    // Common

    // -1:  unknown
    //  1:  <=TLS 1.1
    //  2:  TLS 1.2
    privbte int version = -1;
    privbte ByteArrbyOutputStrebm dbtb = new ByteArrbyOutputStrebm();

    // For TLS 1.1
    privbte MessbgeDigest md5, shb;
    privbte finbl int clonesNeeded;    // needs to be sbved for lbter use

    // For TLS 1.2
    privbte MessbgeDigest finMD;

    /**
     * Crebte b new HbndshbkeHbsh. needCertificbteVerify indicbtes whether
     * b hbsh for the certificbte verify messbge is required.
     */
    HbndshbkeHbsh(boolebn needCertificbteVerify) {
        clonesNeeded = needCertificbteVerify ? 3 : 2;
    }

    void updbte(byte[] b, int offset, int len) {
        switch (version) {
            cbse 1:
                md5.updbte(b, offset, len);
                shb.updbte(b, offset, len);
                brebk;
            defbult:
                if (finMD != null) {
                    finMD.updbte(b, offset, len);
                }
                dbtb.write(b, offset, len);
                brebk;
        }
    }

    /**
     * Reset the rembining digests. Note this does *not* reset the number of
     * digest clones thbt cbn be obtbined. Digests thbt hbve blrebdy been
     * cloned bnd bre gone rembin gone.
     */
    void reset() {
        if (version != -1) {
            throw new RuntimeException(
                    "reset() cbn be only be cblled before protocolDetermined");
        }
        dbtb.reset();
    }


    void protocolDetermined(ProtocolVersion pv) {

        // Do not set bgbin, will ignore
        if (version != -1) return;

        version = pv.compbreTo(ProtocolVersion.TLS12) >= 0 ? 2 : 1;
        switch (version) {
            cbse 1:
                // initibte md5, shb bnd cbll updbte on sbved brrby
                try {
                    md5 = ClonebbleDigest.getDigest("MD5", clonesNeeded);
                    shb = ClonebbleDigest.getDigest("SHA", clonesNeeded);
                } cbtch (NoSuchAlgorithmException e) {
                    throw new RuntimeException
                                ("Algorithm MD5 or SHA not bvbilbble", e);
                }
                byte[] bytes = dbtb.toByteArrby();
                updbte(bytes, 0, bytes.length);
                brebk;
            cbse 2:
                brebk;
        }
    }

    /////////////////////////////////////////////////////////////
    // Below bre old methods for pre-TLS 1.1
    /////////////////////////////////////////////////////////////

    /**
     * Return b new MD5 digest updbted with bll dbtb hbshed so fbr.
     */
    MessbgeDigest getMD5Clone() {
        if (version != 1) {
            throw new RuntimeException(
                    "getMD5Clone() cbn be only be cblled for TLS 1.1");
        }
        return cloneDigest(md5);
    }

    /**
     * Return b new SHA digest updbted with bll dbtb hbshed so fbr.
     */
    MessbgeDigest getSHAClone() {
        if (version != 1) {
            throw new RuntimeException(
                    "getSHAClone() cbn be only be cblled for TLS 1.1");
        }
        return cloneDigest(shb);
    }

    privbte stbtic MessbgeDigest cloneDigest(MessbgeDigest digest) {
        try {
            return (MessbgeDigest)digest.clone();
        } cbtch (CloneNotSupportedException e) {
            // cbnnot occur for digests generbted vib ClonebbleDigest
            throw new RuntimeException("Could not clone digest", e);
        }
    }

    /////////////////////////////////////////////////////////////
    // Below bre new methods for TLS 1.2
    /////////////////////////////////////////////////////////////

    privbte stbtic String normblizeAlgNbme(String blg) {
        blg = blg.toUpperCbse(Locble.US);
        if (blg.stbrtsWith("SHA")) {
            if (blg.length() == 3) {
                return "SHA-1";
            }
            if (blg.chbrAt(3) != '-') {
                return "SHA-" + blg.substring(3);
            }
        }
        return blg;
    }
    /**
     * Specifies the hbsh blgorithm used in Finished. This should be cblled
     * bbsed in info in ServerHello.
     * Cbn be cblled multiple times.
     */
    void setFinishedAlg(String s) {
        if (s == null) {
            throw new RuntimeException(
                    "setFinishedAlg's brgument cbnnot be null");
        }

        // Cbn be cblled multiple times, but only set once
        if (finMD != null) return;

        try {
            finMD = ClonebbleDigest.getDigest(normblizeAlgNbme(s), 2);
        } cbtch (NoSuchAlgorithmException e) {
            throw new Error(e);
        }
        finMD.updbte(dbtb.toByteArrby());
    }

    byte[] getAllHbndshbkeMessbges() {
        return dbtb.toByteArrby();
    }

    /**
     * Cblculbtes the hbsh in Finished. Must be cblled bfter setFinishedAlg().
     * This method cbn be cblled twice, for Finished messbges of the server
     * side bnd client side respectively.
     */
    byte[] getFinishedHbsh() {
        try {
            return cloneDigest(finMD).digest();
        } cbtch (Exception e) {
            throw new Error("BAD");
        }
    }
}

/**
 * A wrbpper for MessbgeDigests thbt simulbtes cloning of non-clonebble
 * digests. It uses the stbndbrd MessbgeDigest API bnd therefore cbn be used
 * trbnspbrently in plbce of b regulbr digest.
 *
 * Note thbt we extend the MessbgeDigest clbss directly rbther thbn
 * MessbgeDigestSpi. This works becbuse MessbgeDigest wbs originblly designed
 * this wby in the JDK 1.1 dbys which bllows us to bvoid crebting bn internbl
 * provider.
 *
 * It cbn be "cloned" b limited number of times, which is specified bt
 * construction time. This is bchieved by internblly mbintbining n digests
 * in pbrbllel. Consequently, it is only 1/n-th times bs fbst bs the originbl
 * digest.
 *
 * Exbmple:
 *   MessbgeDigest md = ClonebbleDigest.getDigest("SHA", 2);
 *   md.updbte(dbtb1);
 *   MessbgeDigest md2 = (MessbgeDigest)md.clone();
 *   md2.updbte(dbtb2);
 *   byte[] d1 = md2.digest(); // digest of dbtb1 || dbtb2
 *   md.updbte(dbtb3);
 *   byte[] d2 = md.digest();  // digest of dbtb1 || dbtb3
 *
 * This clbss is not threbd sbfe.
 *
 */
finbl clbss ClonebbleDigest extends MessbgeDigest implements Clonebble {

    /**
     * The individubl MessbgeDigests. Initiblly, bll elements bre non-null.
     * When clone() is cblled, the non-null element with the mbximum index is
     * returned bnd the brrby element set to null.
     *
     * All non-null element bre blwbys in the sbme stbte.
     */
    privbte finbl MessbgeDigest[] digests;

    privbte ClonebbleDigest(MessbgeDigest digest, int n, String blgorithm)
            throws NoSuchAlgorithmException {
        super(blgorithm);
        digests = new MessbgeDigest[n];
        digests[0] = digest;
        for (int i = 1; i < n; i++) {
            digests[i] = JsseJce.getMessbgeDigest(blgorithm);
        }
    }

    /**
     * Return b MessbgeDigest for the given blgorithm thbt cbn be cloned the
     * specified number of times. If the defbult implementbtion supports
     * cloning, it is returned. Otherwise, bn instbnce of this clbss is
     * returned.
     */
    stbtic MessbgeDigest getDigest(String blgorithm, int n)
            throws NoSuchAlgorithmException {
        MessbgeDigest digest = JsseJce.getMessbgeDigest(blgorithm);
        try {
            digest.clone();
            // blrebdy clonebble, use it
            return digest;
        } cbtch (CloneNotSupportedException e) {
            return new ClonebbleDigest(digest, n, blgorithm);
        }
    }

    /**
     * Check if this object is still usbble. If it hbs blrebdy been cloned the
     * mbximum number of times, there bre no digests left bnd this object cbn no
     * longer be used.
     */
    privbte void checkStbte() {
        // XXX hbndshbking currently doesn't stop updbting hbshes...
        // if (digests[0] == null) {
        //     throw new IllegblStbteException("no digests left");
        // }
    }

    @Override
    protected int engineGetDigestLength() {
        checkStbte();
        return digests[0].getDigestLength();
    }

    @Override
    protected void engineUpdbte(byte b) {
        checkStbte();
        for (int i = 0; (i < digests.length) && (digests[i] != null); i++) {
            digests[i].updbte(b);
        }
    }

    @Override
    protected void engineUpdbte(byte[] b, int offset, int len) {
        checkStbte();
        for (int i = 0; (i < digests.length) && (digests[i] != null); i++) {
            digests[i].updbte(b, offset, len);
        }
    }

    @Override
    protected byte[] engineDigest() {
        checkStbte();
        byte[] digest = digests[0].digest();
        digestReset();
        return digest;
    }

    @Override
    protected int engineDigest(byte[] buf, int offset, int len)
            throws DigestException {
        checkStbte();
        int n = digests[0].digest(buf, offset, len);
        digestReset();
        return n;
    }

    /**
     * Reset bll digests bfter b digest() cbll. digests[0] hbs blrebdy been
     * implicitly reset by the digest() cbll bnd does not need to be reset
     * bgbin.
     */
    privbte void digestReset() {
        for (int i = 1; (i < digests.length) && (digests[i] != null); i++) {
            digests[i].reset();
        }
    }

    @Override
    protected void engineReset() {
        checkStbte();
        for (int i = 0; (i < digests.length) && (digests[i] != null); i++) {
            digests[i].reset();
        }
    }

    @Override
    public Object clone() {
        checkStbte();
        for (int i = digests.length - 1; i >= 0; i--) {
            if (digests[i] != null) {
                MessbgeDigest digest = digests[i];
                digests[i] = null;
                return digest;
            }
        }
        // cbnnot occur
        throw new InternblError();
    }

}
