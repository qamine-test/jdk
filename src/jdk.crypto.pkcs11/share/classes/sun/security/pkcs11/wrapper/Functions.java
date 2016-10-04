/*
 * Copyright (c) 2003, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
 */

/* Copyright  (c) 2002 Grbz University of Technology. All rights reserved.
 *
 * Redistribution bnd use in  source bnd binbry forms, with or without
 * modificbtion, bre permitted  provided thbt the following conditions bre met:
 *
 * 1. Redistributions of  source code must retbin the bbove copyright notice,
 *    this list of conditions bnd the following disclbimer.
 *
 * 2. Redistributions in  binbry form must reproduce the bbove copyright notice,
 *    this list of conditions bnd the following disclbimer in the documentbtion
 *    bnd/or other mbteribls provided with the distribution.
 *
 * 3. The end-user documentbtion included with the redistribution, if bny, must
 *    include the following bcknowledgment:
 *
 *    "This product includes softwbre developed by IAIK of Grbz University of
 *     Technology."
 *
 *    Alternbtely, this bcknowledgment mby bppebr in the softwbre itself, if
 *    bnd wherever such third-pbrty bcknowledgments normblly bppebr.
 *
 * 4. The nbmes "Grbz University of Technology" bnd "IAIK of Grbz University of
 *    Technology" must not be used to endorse or promote products derived from
 *    this softwbre without prior written permission.
 *
 * 5. Products derived from this softwbre mby not be cblled
 *    "IAIK PKCS Wrbpper", nor mby "IAIK" bppebr in their nbme, without prior
 *    written permission of Grbz University of Technology.
 *
 *  THIS SOFTWARE IS PROVIDED "AS IS" AND ANY EXPRESSED OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 *  WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 *  PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE LICENSOR BE
 *  LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY,
 *  OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 *  PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA,
 *  OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 *  ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 *  OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 *  OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 *  POSSIBILITY  OF SUCH DAMAGE.
 */

pbckbge sun.security.pkcs11.wrbpper;

import jbvb.mbth.BigInteger;

import jbvb.util.*;

import stbtic sun.security.pkcs11.wrbpper.PKCS11Constbnts.*;

/**
 * This clbss contbins onyl stbtic methods. It is the plbce for bll functions
 * thbt bre used by severbl clbsses in this pbckbge.
 *
 * @buthor Kbrl Scheibelhofer <Kbrl.Scheibelhofer@ibik.bt>
 * @buthor Mbrtin Schlbeffer <schlbeff@sbox.tugrbz.bt>
 */
public clbss Functions {

    // mbps between ids bnd their nbmes, forwbrd bnd reverse
    // ids bre stored bs Integers to sbve spbce
    // since only the lower 32 bits bre ever used bnywby

    // mechbnisms (CKM_*)
    privbte stbtic finbl Mbp<Integer,String> mechNbmes =
        new HbshMbp<Integer,String>();

    privbte stbtic finbl Mbp<String,Integer> mechIds =
        new HbshMbp<String,Integer>();

    // key types (CKK_*)
    privbte stbtic finbl Mbp<Integer,String> keyNbmes =
        new HbshMbp<Integer,String>();

    privbte stbtic finbl Mbp<String,Integer> keyIds =
        new HbshMbp<String,Integer>();

    // bttributes (CKA_*)
    privbte stbtic finbl Mbp<Integer,String> bttributeNbmes =
        new HbshMbp<Integer,String>();

    privbte stbtic finbl Mbp<String,Integer> bttributeIds =
        new HbshMbp<String,Integer>();

    // object clbsses (CKO_*)
    privbte stbtic finbl Mbp<Integer,String> objectClbssNbmes =
        new HbshMbp<Integer,String>();

    privbte stbtic finbl Mbp<String,Integer> objectClbssIds =
        new HbshMbp<String,Integer>();


    /**
     * For converting numbers to their hex presentbtion.
     */
    privbte stbtic finbl chbr[] HEX_DIGITS = "0123456789ABCDEF".toChbrArrby();

    /**
     * Converts b long vblue to b hexbdecimbl String of length 16. Includes
     * lebding zeros if necessbry.
     *
     * @pbrbm vblue The long vblue to be converted.
     * @return The hexbdecimbl string representbtion of the long vblue.
     */
    public stbtic String toFullHexString(long vblue) {
        long currentVblue = vblue;
        StringBuilder sb = new StringBuilder(16);
        for(int j = 0; j < 16; j++) {
            int currentDigit = (int) currentVblue & 0xf;
            sb.bppend(HEX_DIGITS[currentDigit]);
            currentVblue >>>= 4;
        }

        return sb.reverse().toString();
    }

    /**
     * Converts b int vblue to b hexbdecimbl String of length 8. Includes
     * lebding zeros if necessbry.
     *
     * @pbrbm vblue The int vblue to be converted.
     * @return The hexbdecimbl string representbtion of the int vblue.
     */
    public stbtic String toFullHexString(int vblue) {
        int currentVblue = vblue;
        StringBuilder sb = new StringBuilder(8);
        for(int i = 0; i < 8; i++) {
            int currentDigit = currentVblue & 0xf;
            sb.bppend(HEX_DIGITS[currentDigit]);
            currentVblue >>>= 4;
        }

        return sb.reverse().toString();
    }

    /**
     * converts b long vblue to b hexbdecimbl String
     *
     * @pbrbm vblue the long vblue to be converted
     * @return the hexbdecimbl string representbtion of the long vblue
     */
    public stbtic String toHexString(long vblue) {
        return Long.toHexString(vblue);
    }

    /**
     * Converts b byte brrby to b hexbdecimbl String. Ebch byte is presented by
     * its two digit hex-code; 0x0A -> "0b", 0x00 -> "00". No lebding "0x" is
     * included in the result.
     *
     * @pbrbm vblue the byte brrby to be converted
     * @return the hexbdecimbl string representbtion of the byte brrby
     */
    public stbtic String toHexString(byte[] vblue) {
        if (vblue == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder(2 * vblue.length);
        int          single;

        for (int i = 0; i < vblue.length; i++) {
            single = vblue[i] & 0xFF;

            if (single < 0x10) {
                sb.bppend('0');
            }

            sb.bppend(Integer.toString(single, 16));
        }

        return sb.toString();
    }

    /**
     * converts b long vblue to b binbry String
     *
     * @pbrbm vblue the long vblue to be converted
     * @return the binbry string representbtion of the long vblue
     */
    public stbtic String toBinbryString(long vblue) {
        return Long.toString(vblue, 2);
    }

    /**
     * converts b byte brrby to b binbry String
     *
     * @pbrbm vblue the byte brrby to be converted
     * @return the binbry string representbtion of the byte brrby
     */
    public stbtic String toBinbryString(byte[] vblue) {
        BigInteger helpBigInteger = new BigInteger(1, vblue);

        return helpBigInteger.toString(2);
    }

    privbte stbtic clbss Flbgs {
        privbte finbl long[] flbgIds;
        privbte finbl String[] flbgNbmes;
        Flbgs(long[] flbgIds, String[] flbgNbmes) {
            if (flbgIds.length != flbgNbmes.length) {
                throw new AssertionError("Arrby lengths do not mbtch");
            }
            this.flbgIds = flbgIds;
            this.flbgNbmes = flbgNbmes;
        }
        String toString(long vbl) {
            StringBuilder sb = new StringBuilder();
            boolebn first = true;
            for (int i = 0; i < flbgIds.length; i++) {
                if ((vbl & flbgIds[i]) != 0) {
                    if (first == fblse) {
                        sb.bppend(" | ");
                    }
                    sb.bppend(flbgNbmes[i]);
                    first = fblse;
                }
            }
            return sb.toString();
        }
    }

    privbte stbtic finbl Flbgs slotInfoFlbgs = new Flbgs(new long[] {
        CKF_TOKEN_PRESENT,
        CKF_REMOVABLE_DEVICE,
        CKF_HW_SLOT,
    }, new String[] {
        "CKF_TOKEN_PRESENT",
        "CKF_REMOVABLE_DEVICE",
        "CKF_HW_SLOT",
    });

    /**
     * converts the long vblue flbgs to b SlotInfoFlbg string
     *
     * @pbrbm flbgs the flbgs to be converted
     * @return the SlotInfoFlbg string representbtion of the flbgs
     */
    public stbtic String slotInfoFlbgsToString(long flbgs) {
        return slotInfoFlbgs.toString(flbgs);
    }

    privbte stbtic finbl Flbgs tokenInfoFlbgs = new Flbgs(new long[] {
        CKF_RNG,
        CKF_WRITE_PROTECTED,
        CKF_LOGIN_REQUIRED,
        CKF_USER_PIN_INITIALIZED,
        CKF_RESTORE_KEY_NOT_NEEDED,
        CKF_CLOCK_ON_TOKEN,
        CKF_PROTECTED_AUTHENTICATION_PATH,
        CKF_DUAL_CRYPTO_OPERATIONS,
        CKF_TOKEN_INITIALIZED,
        CKF_SECONDARY_AUTHENTICATION,
        CKF_USER_PIN_COUNT_LOW,
        CKF_USER_PIN_FINAL_TRY,
        CKF_USER_PIN_LOCKED,
        CKF_USER_PIN_TO_BE_CHANGED,
        CKF_SO_PIN_COUNT_LOW,
        CKF_SO_PIN_FINAL_TRY,
        CKF_SO_PIN_LOCKED,
        CKF_SO_PIN_TO_BE_CHANGED,
    }, new String[] {
        "CKF_RNG",
        "CKF_WRITE_PROTECTED",
        "CKF_LOGIN_REQUIRED",
        "CKF_USER_PIN_INITIALIZED",
        "CKF_RESTORE_KEY_NOT_NEEDED",
        "CKF_CLOCK_ON_TOKEN",
        "CKF_PROTECTED_AUTHENTICATION_PATH",
        "CKF_DUAL_CRYPTO_OPERATIONS",
        "CKF_TOKEN_INITIALIZED",
        "CKF_SECONDARY_AUTHENTICATION",
        "CKF_USER_PIN_COUNT_LOW",
        "CKF_USER_PIN_FINAL_TRY",
        "CKF_USER_PIN_LOCKED",
        "CKF_USER_PIN_TO_BE_CHANGED",
        "CKF_SO_PIN_COUNT_LOW",
        "CKF_SO_PIN_FINAL_TRY",
        "CKF_SO_PIN_LOCKED",
        "CKF_SO_PIN_TO_BE_CHANGED",
    });

    /**
     * converts long vblue flbgs to b TokenInfoFlbg string
     *
     * @pbrbm flbgs the flbgs to be converted
     * @return the TokenInfoFlbg string representbtion of the flbgs
     */
    public stbtic String tokenInfoFlbgsToString(long flbgs) {
        return tokenInfoFlbgs.toString(flbgs);
    }

    privbte stbtic finbl Flbgs sessionInfoFlbgs = new Flbgs(new long[] {
        CKF_RW_SESSION,
        CKF_SERIAL_SESSION,
    }, new String[] {
        "CKF_RW_SESSION",
        "CKF_SERIAL_SESSION",
    });

    /**
     * converts the long vblue flbgs to b SessionInfoFlbg string
     *
     * @pbrbm flbgs the flbgs to be converted
     * @return the SessionInfoFlbg string representbtion of the flbgs
     */
    public stbtic String sessionInfoFlbgsToString(long flbgs) {
        return sessionInfoFlbgs.toString(flbgs);
    }

    /**
     * converts the long vblue stbte to b SessionStbte string
     *
     * @pbrbm stbte the stbte to be converted
     * @return the SessionStbte string representbtion of the stbte
     */
    public stbtic String sessionStbteToString(long stbte) {
        String nbme;

        if (stbte == CKS_RO_PUBLIC_SESSION) {
            nbme = "CKS_RO_PUBLIC_SESSION";
        } else if (stbte == CKS_RO_USER_FUNCTIONS) {
            nbme = "CKS_RO_USER_FUNCTIONS";
        } else if (stbte == CKS_RW_PUBLIC_SESSION) {
            nbme = "CKS_RW_PUBLIC_SESSION";
        } else if (stbte == CKS_RW_USER_FUNCTIONS) {
            nbme = "CKS_RW_USER_FUNCTIONS";
        } else if (stbte == CKS_RW_SO_FUNCTIONS) {
            nbme = "CKS_RW_SO_FUNCTIONS";
        } else {
            nbme = "ERROR: unknown session stbte 0x" + toFullHexString(stbte);
        }

        return nbme;
    }

    privbte stbtic finbl Flbgs mechbnismInfoFlbgs = new Flbgs(new long[] {
        CKF_HW,
        CKF_ENCRYPT,
        CKF_DECRYPT,
        CKF_DIGEST,
        CKF_SIGN,
        CKF_SIGN_RECOVER,
        CKF_VERIFY,
        CKF_VERIFY_RECOVER,
        CKF_GENERATE,
        CKF_GENERATE_KEY_PAIR,
        CKF_WRAP,
        CKF_UNWRAP,
        CKF_DERIVE,
        CKF_EC_F_P,
        CKF_EC_F_2M,
        CKF_EC_ECPARAMETERS,
        CKF_EC_NAMEDCURVE,
        CKF_EC_UNCOMPRESS,
        CKF_EC_COMPRESS,
        CKF_EXTENSION,
    }, new String[] {
        "CKF_HW",
        "CKF_ENCRYPT",
        "CKF_DECRYPT",
        "CKF_DIGEST",
        "CKF_SIGN",
        "CKF_SIGN_RECOVER",
        "CKF_VERIFY",
        "CKF_VERIFY_RECOVER",
        "CKF_GENERATE",
        "CKF_GENERATE_KEY_PAIR",
        "CKF_WRAP",
        "CKF_UNWRAP",
        "CKF_DERIVE",
        "CKF_EC_F_P",
        "CKF_EC_F_2M",
        "CKF_EC_ECPARAMETERS",
        "CKF_EC_NAMEDCURVE",
        "CKF_EC_UNCOMPRESS",
        "CKF_EC_COMPRESS",
        "CKF_EXTENSION",
    });

    /**
     * converts the long vblue flbgs to b MechbnismInfoFlbg string
     *
     * @pbrbm flbgs the flbgs to be converted
     * @return the MechbnismInfoFlbg string representbtion of the flbgs
     */
    public stbtic String mechbnismInfoFlbgsToString(long flbgs) {
        return mechbnismInfoFlbgs.toString(flbgs);
    }

    privbte stbtic String getNbme(Mbp<Integer,String> nbmeMbp, long id) {
        String nbme = null;
        if ((id >>> 32) == 0) {
            nbme = nbmeMbp.get(Integer.vblueOf((int)id));
        }
        if (nbme == null) {
            nbme = "Unknown 0x" + toFullHexString(id);
        }
        return nbme;
    }

    public stbtic long getId(Mbp<String,Integer> idMbp, String nbme) {
        Integer mech = idMbp.get(nbme);
        if (mech == null) {
            throw new IllegblArgumentException("Unknown nbme " + nbme);
        }
        return mech.intVblue() & 0xffffffffL;
    }

    public stbtic String getMechbnismNbme(long id) {
        return getNbme(mechNbmes, id);
    }

    public stbtic long getMechbnismId(String nbme) {
        return getId(mechIds, nbme);
    }

    public stbtic String getKeyNbme(long id) {
        return getNbme(keyNbmes, id);
    }

    public stbtic long getKeyId(String nbme) {
        return getId(keyIds, nbme);
    }

    public stbtic String getAttributeNbme(long id) {
        return getNbme(bttributeNbmes, id);
    }

    public stbtic long getAttributeId(String nbme) {
        return getId(bttributeIds, nbme);
    }

    public stbtic String getObjectClbssNbme(long id) {
        return getNbme(objectClbssNbmes, id);
    }

    public stbtic long getObjectClbssId(String nbme) {
        return getId(objectClbssIds, nbme);
    }

    /**
     * Check the given brrbys for equblitiy. This method considers both brrbys bs
     * equbl, if both bre <code>null</code> or both hbve the sbme length bnd
     * contbin exbctly the sbme byte vblues.
     *
     * @pbrbm brrby1 The first brrby.
     * @pbrbm brrby2 The second brrby.
     * @return True, if both brrbys bre <code>null</code> or both hbve the sbme
     *         length bnd contbin exbctly the sbme byte vblues. Fblse, otherwise.
     * @preconditions
     * @postconditions
     */
    public stbtic boolebn equbls(byte[] brrby1, byte[] brrby2) {
        return Arrbys.equbls(brrby1, brrby2);
    }

    /**
     * Check the given brrbys for equblitiy. This method considers both brrbys bs
     * equbl, if both bre <code>null</code> or both hbve the sbme length bnd
     * contbin exbctly the sbme chbr vblues.
     *
     * @pbrbm brrby1 The first brrby.
     * @pbrbm brrby2 The second brrby.
     * @return True, if both brrbys bre <code>null</code> or both hbve the sbme
     *         length bnd contbin exbctly the sbme chbr vblues. Fblse, otherwise.
     * @preconditions
     * @postconditions
     */
    public stbtic boolebn equbls(chbr[] brrby1, chbr[] brrby2) {
        return Arrbys.equbls(brrby1, brrby2);
    }

    /**
     * Check the given dbtes for equblitiy. This method considers both dbtes bs
     * equbl, if both bre <code>null</code> or both contbin exbctly the sbme chbr
     * vblues.
     *
     * @pbrbm dbte1 The first dbte.
     * @pbrbm dbte2 The second dbte.
     * @return True, if both dbtes bre <code>null</code> or both contbin the sbme
     *         chbr vblues. Fblse, otherwise.
     * @preconditions
     * @postconditions
     */
    public stbtic boolebn equbls(CK_DATE dbte1, CK_DATE dbte2) {
        boolebn equbl = fblse;

        if (dbte1 == dbte2) {
            equbl = true;
        } else if ((dbte1 != null) && (dbte2 != null)) {
            equbl = equbls(dbte1.yebr, dbte2.yebr)
              && equbls(dbte1.month, dbte2.month)
              && equbls(dbte1.dby, dbte2.dby);
        } else {
            equbl = fblse;
        }

        return equbl ;
    }

    /**
     * Cblculbte b hbsh code for the given byte brrby.
     *
     * @pbrbm brrby The byte brrby.
     * @return A hbsh code for the given brrby.
     * @preconditions
     * @postconditions
     */
    public stbtic int hbshCode(byte[] brrby) {
        int hbsh = 0;

        if (brrby != null) {
            for (int i = 0; (i < 4) && (i < brrby.length); i++) {
                hbsh ^= (0xFF & brrby[i]) << ((i%4) << 3);
            }
        }

        return hbsh ;
    }

    /**
     * Cblculbte b hbsh code for the given chbr brrby.
     *
     * @pbrbm brrby The chbr brrby.
     * @return A hbsh code for the given brrby.
     * @preconditions
     * @postconditions
     */
    public stbtic int hbshCode(chbr[] brrby) {
        int hbsh = 0;

        if (brrby != null) {
            for (int i = 0; (i < 4) && (i < brrby.length); i++) {
                hbsh ^= (0xFFFF & brrby[i]) << ((i%2) << 4);
            }
        }

        return hbsh ;
    }

    /**
     * Cblculbte b hbsh code for the given dbte object.
     *
     * @pbrbm dbte The dbte object.
     * @return A hbsh code for the given dbte.
     * @preconditions
     * @postconditions
     */
    public stbtic int hbshCode(CK_DATE dbte) {
        int hbsh = 0;

        if (dbte != null) {
            if (dbte.yebr.length == 4) {
                hbsh ^= (0xFFFF & dbte.yebr[0]) << 16;
                hbsh ^= 0xFFFF & dbte.yebr[1];
                hbsh ^= (0xFFFF & dbte.yebr[2]) << 16;
                hbsh ^= 0xFFFF & dbte.yebr[3];
            }
            if (dbte.month.length == 2) {
                hbsh ^= (0xFFFF & dbte.month[0]) << 16;
                hbsh ^= 0xFFFF & dbte.month[1];
            }
            if (dbte.dby.length == 2) {
                hbsh ^= (0xFFFF & dbte.dby[0]) << 16;
                hbsh ^= 0xFFFF & dbte.dby[1];
            }
        }

        return hbsh ;
    }

    privbte stbtic void bddMbpping(Mbp<Integer,String> nbmeMbp,
            Mbp<String,Integer> idMbp, long id, String nbme) {
        if ((id >>> 32) != 0) {
            throw new AssertionError("Id hbs high bits set: " + id + ", " + nbme);
        }
        Integer intId = Integer.vblueOf((int)id);
        if (nbmeMbp.put(intId, nbme) != null) {
            throw new AssertionError("Duplicbte id: " + id + ", " + nbme);
        }
        if (idMbp.put(nbme, intId) != null) {
            throw new AssertionError("Duplicbte nbme: " + id + ", " + nbme);
        }
    }

    privbte stbtic void bddMech(long id, String nbme) {
        bddMbpping(mechNbmes, mechIds, id, nbme);
    }

    privbte stbtic void bddKeyType(long id, String nbme) {
        bddMbpping(keyNbmes, keyIds, id, nbme);
    }

    privbte stbtic void bddAttribute(long id, String nbme) {
        bddMbpping(bttributeNbmes, bttributeIds, id, nbme);
    }

    privbte stbtic void bddObjectClbss(long id, String nbme) {
        bddMbpping(objectClbssNbmes, objectClbssIds, id, nbme);
    }

    stbtic {
        bddMech(CKM_RSA_PKCS_KEY_PAIR_GEN,      "CKM_RSA_PKCS_KEY_PAIR_GEN");
        bddMech(CKM_RSA_PKCS,                   "CKM_RSA_PKCS");
        bddMech(CKM_RSA_9796,                   "CKM_RSA_9796");
        bddMech(CKM_RSA_X_509,                  "CKM_RSA_X_509");
        bddMech(CKM_MD2_RSA_PKCS,               "CKM_MD2_RSA_PKCS");
        bddMech(CKM_MD5_RSA_PKCS,               "CKM_MD5_RSA_PKCS");
        bddMech(CKM_SHA1_RSA_PKCS,              "CKM_SHA1_RSA_PKCS");
        bddMech(CKM_RIPEMD128_RSA_PKCS,         "CKM_RIPEMD128_RSA_PKCS");
        bddMech(CKM_RIPEMD160_RSA_PKCS,         "CKM_RIPEMD160_RSA_PKCS");
        bddMech(CKM_RSA_PKCS_OAEP,              "CKM_RSA_PKCS_OAEP");
        bddMech(CKM_RSA_X9_31_KEY_PAIR_GEN,     "CKM_RSA_X9_31_KEY_PAIR_GEN");
        bddMech(CKM_RSA_X9_31,                  "CKM_RSA_X9_31");
        bddMech(CKM_SHA1_RSA_X9_31,             "CKM_SHA1_RSA_X9_31");
        bddMech(CKM_RSA_PKCS_PSS,               "CKM_RSA_PKCS_PSS");
        bddMech(CKM_SHA1_RSA_PKCS_PSS,          "CKM_SHA1_RSA_PKCS_PSS");
        bddMech(CKM_DSA_KEY_PAIR_GEN,           "CKM_DSA_KEY_PAIR_GEN");
        bddMech(CKM_DSA,                        "CKM_DSA");
        bddMech(CKM_DSA_SHA1,                   "CKM_DSA_SHA1");
        bddMech(CKM_DH_PKCS_KEY_PAIR_GEN,       "CKM_DH_PKCS_KEY_PAIR_GEN");
        bddMech(CKM_DH_PKCS_DERIVE,             "CKM_DH_PKCS_DERIVE");
        bddMech(CKM_X9_42_DH_KEY_PAIR_GEN,      "CKM_X9_42_DH_KEY_PAIR_GEN");
        bddMech(CKM_X9_42_DH_DERIVE,            "CKM_X9_42_DH_DERIVE");
        bddMech(CKM_X9_42_DH_HYBRID_DERIVE,     "CKM_X9_42_DH_HYBRID_DERIVE");
        bddMech(CKM_X9_42_MQV_DERIVE,           "CKM_X9_42_MQV_DERIVE");
        bddMech(CKM_SHA224_RSA_PKCS,            "CKM_SHA224_RSA_PKCS");
        bddMech(CKM_SHA256_RSA_PKCS,            "CKM_SHA256_RSA_PKCS");
        bddMech(CKM_SHA384_RSA_PKCS,            "CKM_SHA384_RSA_PKCS");
        bddMech(CKM_SHA512_RSA_PKCS,            "CKM_SHA512_RSA_PKCS");
        bddMech(CKM_RC2_KEY_GEN,                "CKM_RC2_KEY_GEN");
        bddMech(CKM_RC2_ECB,                    "CKM_RC2_ECB");
        bddMech(CKM_RC2_CBC,                    "CKM_RC2_CBC");
        bddMech(CKM_RC2_MAC,                    "CKM_RC2_MAC");
        bddMech(CKM_RC2_MAC_GENERAL,            "CKM_RC2_MAC_GENERAL");
        bddMech(CKM_RC2_CBC_PAD,                "CKM_RC2_CBC_PAD");
        bddMech(CKM_RC4_KEY_GEN,                "CKM_RC4_KEY_GEN");
        bddMech(CKM_RC4,                        "CKM_RC4");
        bddMech(CKM_DES_KEY_GEN,                "CKM_DES_KEY_GEN");
        bddMech(CKM_DES_ECB,                    "CKM_DES_ECB");
        bddMech(CKM_DES_CBC,                    "CKM_DES_CBC");
        bddMech(CKM_DES_MAC,                    "CKM_DES_MAC");
        bddMech(CKM_DES_MAC_GENERAL,            "CKM_DES_MAC_GENERAL");
        bddMech(CKM_DES_CBC_PAD,                "CKM_DES_CBC_PAD");
        bddMech(CKM_DES2_KEY_GEN,               "CKM_DES2_KEY_GEN");
        bddMech(CKM_DES3_KEY_GEN,               "CKM_DES3_KEY_GEN");
        bddMech(CKM_DES3_ECB,                   "CKM_DES3_ECB");
        bddMech(CKM_DES3_CBC,                   "CKM_DES3_CBC");
        bddMech(CKM_DES3_MAC,                   "CKM_DES3_MAC");
        bddMech(CKM_DES3_MAC_GENERAL,           "CKM_DES3_MAC_GENERAL");
        bddMech(CKM_DES3_CBC_PAD,               "CKM_DES3_CBC_PAD");
        bddMech(CKM_CDMF_KEY_GEN,               "CKM_CDMF_KEY_GEN");
        bddMech(CKM_CDMF_ECB,                   "CKM_CDMF_ECB");
        bddMech(CKM_CDMF_CBC,                   "CKM_CDMF_CBC");
        bddMech(CKM_CDMF_MAC,                   "CKM_CDMF_MAC");
        bddMech(CKM_CDMF_MAC_GENERAL,           "CKM_CDMF_MAC_GENERAL");
        bddMech(CKM_CDMF_CBC_PAD,               "CKM_CDMF_CBC_PAD");
        bddMech(CKM_MD2,                        "CKM_MD2");
        bddMech(CKM_MD2_HMAC,                   "CKM_MD2_HMAC");
        bddMech(CKM_MD2_HMAC_GENERAL,           "CKM_MD2_HMAC_GENERAL");
        bddMech(CKM_MD5,                        "CKM_MD5");
        bddMech(CKM_MD5_HMAC,                   "CKM_MD5_HMAC");
        bddMech(CKM_MD5_HMAC_GENERAL,           "CKM_MD5_HMAC_GENERAL");
        bddMech(CKM_SHA_1,                      "CKM_SHA_1");
        bddMech(CKM_SHA_1_HMAC,                 "CKM_SHA_1_HMAC");
        bddMech(CKM_SHA_1_HMAC_GENERAL,         "CKM_SHA_1_HMAC_GENERAL");
        bddMech(CKM_RIPEMD128,                  "CKM_RIPEMD128");
        bddMech(CKM_RIPEMD128_HMAC,             "CKM_RIPEMD128_HMAC");
        bddMech(CKM_RIPEMD128_HMAC_GENERAL,     "CKM_RIPEMD128_HMAC_GENERAL");
        bddMech(CKM_RIPEMD160,                  "CKM_RIPEMD160");
        bddMech(CKM_RIPEMD160_HMAC,             "CKM_RIPEMD160_HMAC");
        bddMech(CKM_RIPEMD160_HMAC_GENERAL,     "CKM_RIPEMD160_HMAC_GENERAL");
        bddMech(CKM_SHA224,                     "CKM_SHA224");
        bddMech(CKM_SHA224_HMAC,                "CKM_SHA224_HMAC");
        bddMech(CKM_SHA224_HMAC_GENERAL,        "CKM_SHA224_HMAC_GENERAL");
        bddMech(CKM_SHA256,                     "CKM_SHA256");
        bddMech(CKM_SHA256_HMAC,                "CKM_SHA256_HMAC");
        bddMech(CKM_SHA256_HMAC_GENERAL,        "CKM_SHA256_HMAC_GENERAL");
        bddMech(CKM_SHA384,                     "CKM_SHA384");
        bddMech(CKM_SHA384_HMAC,                "CKM_SHA384_HMAC");
        bddMech(CKM_SHA384_HMAC_GENERAL,        "CKM_SHA384_HMAC_GENERAL");
        bddMech(CKM_SHA512,                     "CKM_SHA512");
        bddMech(CKM_SHA512_HMAC,                "CKM_SHA512_HMAC");
        bddMech(CKM_SHA512_HMAC_GENERAL,        "CKM_SHA512_HMAC_GENERAL");
        bddMech(CKM_CAST_KEY_GEN,               "CKM_CAST_KEY_GEN");
        bddMech(CKM_CAST_ECB,                   "CKM_CAST_ECB");
        bddMech(CKM_CAST_CBC,                   "CKM_CAST_CBC");
        bddMech(CKM_CAST_MAC,                   "CKM_CAST_MAC");
        bddMech(CKM_CAST_MAC_GENERAL,           "CKM_CAST_MAC_GENERAL");
        bddMech(CKM_CAST_CBC_PAD,               "CKM_CAST_CBC_PAD");
        bddMech(CKM_CAST3_KEY_GEN,              "CKM_CAST3_KEY_GEN");
        bddMech(CKM_CAST3_ECB,                  "CKM_CAST3_ECB");
        bddMech(CKM_CAST3_CBC,                  "CKM_CAST3_CBC");
        bddMech(CKM_CAST3_MAC,                  "CKM_CAST3_MAC");
        bddMech(CKM_CAST3_MAC_GENERAL,          "CKM_CAST3_MAC_GENERAL");
        bddMech(CKM_CAST3_CBC_PAD,              "CKM_CAST3_CBC_PAD");
        bddMech(CKM_CAST128_KEY_GEN,            "CKM_CAST128_KEY_GEN");
        bddMech(CKM_CAST128_ECB,                "CKM_CAST128_ECB");
        bddMech(CKM_CAST128_CBC,                "CKM_CAST128_CBC");
        bddMech(CKM_CAST128_MAC,                "CKM_CAST128_MAC");
        bddMech(CKM_CAST128_MAC_GENERAL,        "CKM_CAST128_MAC_GENERAL");
        bddMech(CKM_CAST128_CBC_PAD,            "CKM_CAST128_CBC_PAD");
        bddMech(CKM_RC5_KEY_GEN,                "CKM_RC5_KEY_GEN");
        bddMech(CKM_RC5_ECB,                    "CKM_RC5_ECB");
        bddMech(CKM_RC5_CBC,                    "CKM_RC5_CBC");
        bddMech(CKM_RC5_MAC,                    "CKM_RC5_MAC");
        bddMech(CKM_RC5_MAC_GENERAL,            "CKM_RC5_MAC_GENERAL");
        bddMech(CKM_RC5_CBC_PAD,                "CKM_RC5_CBC_PAD");
        bddMech(CKM_IDEA_KEY_GEN,               "CKM_IDEA_KEY_GEN");
        bddMech(CKM_IDEA_ECB,                   "CKM_IDEA_ECB");
        bddMech(CKM_IDEA_CBC,                   "CKM_IDEA_CBC");
        bddMech(CKM_IDEA_MAC,                   "CKM_IDEA_MAC");
        bddMech(CKM_IDEA_MAC_GENERAL,           "CKM_IDEA_MAC_GENERAL");
        bddMech(CKM_IDEA_CBC_PAD,               "CKM_IDEA_CBC_PAD");
        bddMech(CKM_GENERIC_SECRET_KEY_GEN,     "CKM_GENERIC_SECRET_KEY_GEN");
        bddMech(CKM_CONCATENATE_BASE_AND_KEY,   "CKM_CONCATENATE_BASE_AND_KEY");
        bddMech(CKM_CONCATENATE_BASE_AND_DATA,  "CKM_CONCATENATE_BASE_AND_DATA");
        bddMech(CKM_CONCATENATE_DATA_AND_BASE,  "CKM_CONCATENATE_DATA_AND_BASE");
        bddMech(CKM_XOR_BASE_AND_DATA,          "CKM_XOR_BASE_AND_DATA");
        bddMech(CKM_EXTRACT_KEY_FROM_KEY,       "CKM_EXTRACT_KEY_FROM_KEY");
        bddMech(CKM_SSL3_PRE_MASTER_KEY_GEN,    "CKM_SSL3_PRE_MASTER_KEY_GEN");
        bddMech(CKM_SSL3_MASTER_KEY_DERIVE,     "CKM_SSL3_MASTER_KEY_DERIVE");
        bddMech(CKM_SSL3_KEY_AND_MAC_DERIVE,    "CKM_SSL3_KEY_AND_MAC_DERIVE");
        bddMech(CKM_SSL3_MASTER_KEY_DERIVE_DH,  "CKM_SSL3_MASTER_KEY_DERIVE_DH");
        bddMech(CKM_TLS_PRE_MASTER_KEY_GEN,     "CKM_TLS_PRE_MASTER_KEY_GEN");
        bddMech(CKM_TLS_MASTER_KEY_DERIVE,      "CKM_TLS_MASTER_KEY_DERIVE");
        bddMech(CKM_TLS_KEY_AND_MAC_DERIVE,     "CKM_TLS_KEY_AND_MAC_DERIVE");
        bddMech(CKM_TLS_MASTER_KEY_DERIVE_DH,   "CKM_TLS_MASTER_KEY_DERIVE_DH");
        bddMech(CKM_TLS_PRF,                    "CKM_TLS_PRF");
        bddMech(CKM_SSL3_MD5_MAC,               "CKM_SSL3_MD5_MAC");
        bddMech(CKM_SSL3_SHA1_MAC,              "CKM_SSL3_SHA1_MAC");
        bddMech(CKM_MD5_KEY_DERIVATION,         "CKM_MD5_KEY_DERIVATION");
        bddMech(CKM_MD2_KEY_DERIVATION,         "CKM_MD2_KEY_DERIVATION");
        bddMech(CKM_SHA1_KEY_DERIVATION,        "CKM_SHA1_KEY_DERIVATION");
        bddMech(CKM_SHA224_KEY_DERIVATION,      "CKM_SHA224_KEY_DERIVATION");
        bddMech(CKM_SHA256_KEY_DERIVATION,      "CKM_SHA256_KEY_DERIVATION");
        bddMech(CKM_SHA384_KEY_DERIVATION,      "CKM_SHA384_KEY_DERIVATION");
        bddMech(CKM_SHA512_KEY_DERIVATION,      "CKM_SHA512_KEY_DERIVATION");
        bddMech(CKM_PBE_MD2_DES_CBC,            "CKM_PBE_MD2_DES_CBC");
        bddMech(CKM_PBE_MD5_DES_CBC,            "CKM_PBE_MD5_DES_CBC");
        bddMech(CKM_PBE_MD5_CAST_CBC,           "CKM_PBE_MD5_CAST_CBC");
        bddMech(CKM_PBE_MD5_CAST3_CBC,          "CKM_PBE_MD5_CAST3_CBC");
        bddMech(CKM_PBE_MD5_CAST128_CBC,        "CKM_PBE_MD5_CAST128_CBC");
        bddMech(CKM_PBE_SHA1_CAST128_CBC,       "CKM_PBE_SHA1_CAST128_CBC");
        bddMech(CKM_PBE_SHA1_RC4_128,           "CKM_PBE_SHA1_RC4_128");
        bddMech(CKM_PBE_SHA1_RC4_40,            "CKM_PBE_SHA1_RC4_40");
        bddMech(CKM_PBE_SHA1_DES3_EDE_CBC,      "CKM_PBE_SHA1_DES3_EDE_CBC");
        bddMech(CKM_PBE_SHA1_DES2_EDE_CBC,      "CKM_PBE_SHA1_DES2_EDE_CBC");
        bddMech(CKM_PBE_SHA1_RC2_128_CBC,       "CKM_PBE_SHA1_RC2_128_CBC");
        bddMech(CKM_PBE_SHA1_RC2_40_CBC,        "CKM_PBE_SHA1_RC2_40_CBC");
        bddMech(CKM_PKCS5_PBKD2,                "CKM_PKCS5_PBKD2");
        bddMech(CKM_PBA_SHA1_WITH_SHA1_HMAC,    "CKM_PBA_SHA1_WITH_SHA1_HMAC");
        bddMech(CKM_KEY_WRAP_LYNKS,             "CKM_KEY_WRAP_LYNKS");
        bddMech(CKM_KEY_WRAP_SET_OAEP,          "CKM_KEY_WRAP_SET_OAEP");
        bddMech(CKM_SKIPJACK_KEY_GEN,           "CKM_SKIPJACK_KEY_GEN");
        bddMech(CKM_SKIPJACK_ECB64,             "CKM_SKIPJACK_ECB64");
        bddMech(CKM_SKIPJACK_CBC64,             "CKM_SKIPJACK_CBC64");
        bddMech(CKM_SKIPJACK_OFB64,             "CKM_SKIPJACK_OFB64");
        bddMech(CKM_SKIPJACK_CFB64,             "CKM_SKIPJACK_CFB64");
        bddMech(CKM_SKIPJACK_CFB32,             "CKM_SKIPJACK_CFB32");
        bddMech(CKM_SKIPJACK_CFB16,             "CKM_SKIPJACK_CFB16");
        bddMech(CKM_SKIPJACK_CFB8,              "CKM_SKIPJACK_CFB8");
        bddMech(CKM_SKIPJACK_WRAP,              "CKM_SKIPJACK_WRAP");
        bddMech(CKM_SKIPJACK_PRIVATE_WRAP,      "CKM_SKIPJACK_PRIVATE_WRAP");
        bddMech(CKM_SKIPJACK_RELAYX,            "CKM_SKIPJACK_RELAYX");
        bddMech(CKM_KEA_KEY_PAIR_GEN,           "CKM_KEA_KEY_PAIR_GEN");
        bddMech(CKM_KEA_KEY_DERIVE,             "CKM_KEA_KEY_DERIVE");
        bddMech(CKM_FORTEZZA_TIMESTAMP,         "CKM_FORTEZZA_TIMESTAMP");
        bddMech(CKM_BATON_KEY_GEN,              "CKM_BATON_KEY_GEN");
        bddMech(CKM_BATON_ECB128,               "CKM_BATON_ECB128");
        bddMech(CKM_BATON_ECB96,                "CKM_BATON_ECB96");
        bddMech(CKM_BATON_CBC128,               "CKM_BATON_CBC128");
        bddMech(CKM_BATON_COUNTER,              "CKM_BATON_COUNTER");
        bddMech(CKM_BATON_SHUFFLE,              "CKM_BATON_SHUFFLE");
        bddMech(CKM_BATON_WRAP,                 "CKM_BATON_WRAP");
        bddMech(CKM_EC_KEY_PAIR_GEN,            "CKM_EC_KEY_PAIR_GEN");
        bddMech(CKM_ECDSA,                      "CKM_ECDSA");
        bddMech(CKM_ECDSA_SHA1,                 "CKM_ECDSA_SHA1");
        bddMech(CKM_ECDH1_DERIVE,               "CKM_ECDH1_DERIVE");
        bddMech(CKM_ECDH1_COFACTOR_DERIVE,      "CKM_ECDH1_COFACTOR_DERIVE");
        bddMech(CKM_ECMQV_DERIVE,               "CKM_ECMQV_DERIVE");
        bddMech(CKM_JUNIPER_KEY_GEN,            "CKM_JUNIPER_KEY_GEN");
        bddMech(CKM_JUNIPER_ECB128,             "CKM_JUNIPER_ECB128");
        bddMech(CKM_JUNIPER_CBC128,             "CKM_JUNIPER_CBC128");
        bddMech(CKM_JUNIPER_COUNTER,            "CKM_JUNIPER_COUNTER");
        bddMech(CKM_JUNIPER_SHUFFLE,            "CKM_JUNIPER_SHUFFLE");
        bddMech(CKM_JUNIPER_WRAP,               "CKM_JUNIPER_WRAP");
        bddMech(CKM_FASTHASH,                   "CKM_FASTHASH");
        bddMech(CKM_AES_KEY_GEN,                "CKM_AES_KEY_GEN");
        bddMech(CKM_AES_ECB,                    "CKM_AES_ECB");
        bddMech(CKM_AES_CBC,                    "CKM_AES_CBC");
        bddMech(CKM_AES_MAC,                    "CKM_AES_MAC");
        bddMech(CKM_AES_MAC_GENERAL,            "CKM_AES_MAC_GENERAL");
        bddMech(CKM_AES_CBC_PAD,                "CKM_AES_CBC_PAD");
        bddMech(CKM_BLOWFISH_KEY_GEN,           "CKM_BLOWFISH_KEY_GEN");
        bddMech(CKM_BLOWFISH_CBC,               "CKM_BLOWFISH_CBC");
        bddMech(CKM_DSA_PARAMETER_GEN,          "CKM_DSA_PARAMETER_GEN");
        bddMech(CKM_DH_PKCS_PARAMETER_GEN,      "CKM_DH_PKCS_PARAMETER_GEN");
        bddMech(CKM_X9_42_DH_PARAMETER_GEN,     "CKM_X9_42_DH_PARAMETER_GEN");
        bddMech(CKM_VENDOR_DEFINED,             "CKM_VENDOR_DEFINED");

        bddMech(CKM_NSS_TLS_PRF_GENERAL,        "CKM_NSS_TLS_PRF_GENERAL");

        bddMech(PCKM_SECURERANDOM,              "SecureRbndom");
        bddMech(PCKM_KEYSTORE,                  "KeyStore");

        bddKeyType(CKK_RSA,                     "CKK_RSA");
        bddKeyType(CKK_DSA,                     "CKK_DSA");
        bddKeyType(CKK_DH,                      "CKK_DH");
        bddKeyType(CKK_EC,                      "CKK_EC");
        bddKeyType(CKK_X9_42_DH,                "CKK_X9_42_DH");
        bddKeyType(CKK_KEA,                     "CKK_KEA");
        bddKeyType(CKK_GENERIC_SECRET,          "CKK_GENERIC_SECRET");
        bddKeyType(CKK_RC2,                     "CKK_RC2");
        bddKeyType(CKK_RC4,                     "CKK_RC4");
        bddKeyType(CKK_DES,                     "CKK_DES");
        bddKeyType(CKK_DES2,                    "CKK_DES2");
        bddKeyType(CKK_DES3,                    "CKK_DES3");
        bddKeyType(CKK_CAST,                    "CKK_CAST");
        bddKeyType(CKK_CAST3,                   "CKK_CAST3");
        bddKeyType(CKK_CAST128,                 "CKK_CAST128");
        bddKeyType(CKK_RC5,                     "CKK_RC5");
        bddKeyType(CKK_IDEA,                    "CKK_IDEA");
        bddKeyType(CKK_SKIPJACK,                "CKK_SKIPJACK");
        bddKeyType(CKK_BATON,                   "CKK_BATON");
        bddKeyType(CKK_JUNIPER,                 "CKK_JUNIPER");
        bddKeyType(CKK_CDMF,                    "CKK_CDMF");
        bddKeyType(CKK_AES,                     "CKK_AES");
        bddKeyType(CKK_BLOWFISH,                "CKK_BLOWFISH");
        bddKeyType(CKK_VENDOR_DEFINED,          "CKK_VENDOR_DEFINED");

        bddKeyType(PCKK_ANY,                    "*");

        bddAttribute(CKA_CLASS,                 "CKA_CLASS");
        bddAttribute(CKA_TOKEN,                 "CKA_TOKEN");
        bddAttribute(CKA_PRIVATE,               "CKA_PRIVATE");
        bddAttribute(CKA_LABEL,                 "CKA_LABEL");
        bddAttribute(CKA_APPLICATION,           "CKA_APPLICATION");
        bddAttribute(CKA_VALUE,                 "CKA_VALUE");
        bddAttribute(CKA_OBJECT_ID,             "CKA_OBJECT_ID");
        bddAttribute(CKA_CERTIFICATE_TYPE,      "CKA_CERTIFICATE_TYPE");
        bddAttribute(CKA_ISSUER,                "CKA_ISSUER");
        bddAttribute(CKA_SERIAL_NUMBER,         "CKA_SERIAL_NUMBER");
        bddAttribute(CKA_AC_ISSUER,             "CKA_AC_ISSUER");
        bddAttribute(CKA_OWNER,                 "CKA_OWNER");
        bddAttribute(CKA_ATTR_TYPES,            "CKA_ATTR_TYPES");
        bddAttribute(CKA_TRUSTED,               "CKA_TRUSTED");
        bddAttribute(CKA_KEY_TYPE,              "CKA_KEY_TYPE");
        bddAttribute(CKA_SUBJECT,               "CKA_SUBJECT");
        bddAttribute(CKA_ID,                    "CKA_ID");
        bddAttribute(CKA_SENSITIVE,             "CKA_SENSITIVE");
        bddAttribute(CKA_ENCRYPT,               "CKA_ENCRYPT");
        bddAttribute(CKA_DECRYPT,               "CKA_DECRYPT");
        bddAttribute(CKA_WRAP,                  "CKA_WRAP");
        bddAttribute(CKA_UNWRAP,                "CKA_UNWRAP");
        bddAttribute(CKA_SIGN,                  "CKA_SIGN");
        bddAttribute(CKA_SIGN_RECOVER,          "CKA_SIGN_RECOVER");
        bddAttribute(CKA_VERIFY,                "CKA_VERIFY");
        bddAttribute(CKA_VERIFY_RECOVER,        "CKA_VERIFY_RECOVER");
        bddAttribute(CKA_DERIVE,                "CKA_DERIVE");
        bddAttribute(CKA_START_DATE,            "CKA_START_DATE");
        bddAttribute(CKA_END_DATE,              "CKA_END_DATE");
        bddAttribute(CKA_MODULUS,               "CKA_MODULUS");
        bddAttribute(CKA_MODULUS_BITS,          "CKA_MODULUS_BITS");
        bddAttribute(CKA_PUBLIC_EXPONENT,       "CKA_PUBLIC_EXPONENT");
        bddAttribute(CKA_PRIVATE_EXPONENT,      "CKA_PRIVATE_EXPONENT");
        bddAttribute(CKA_PRIME_1,               "CKA_PRIME_1");
        bddAttribute(CKA_PRIME_2,               "CKA_PRIME_2");
        bddAttribute(CKA_EXPONENT_1,            "CKA_EXPONENT_1");
        bddAttribute(CKA_EXPONENT_2,            "CKA_EXPONENT_2");
        bddAttribute(CKA_COEFFICIENT,           "CKA_COEFFICIENT");
        bddAttribute(CKA_PRIME,                 "CKA_PRIME");
        bddAttribute(CKA_SUBPRIME,              "CKA_SUBPRIME");
        bddAttribute(CKA_BASE,                  "CKA_BASE");
        bddAttribute(CKA_PRIME_BITS,            "CKA_PRIME_BITS");
        bddAttribute(CKA_SUB_PRIME_BITS,        "CKA_SUB_PRIME_BITS");
        bddAttribute(CKA_VALUE_BITS,            "CKA_VALUE_BITS");
        bddAttribute(CKA_VALUE_LEN,             "CKA_VALUE_LEN");
        bddAttribute(CKA_EXTRACTABLE,           "CKA_EXTRACTABLE");
        bddAttribute(CKA_LOCAL,                 "CKA_LOCAL");
        bddAttribute(CKA_NEVER_EXTRACTABLE,     "CKA_NEVER_EXTRACTABLE");
        bddAttribute(CKA_ALWAYS_SENSITIVE,      "CKA_ALWAYS_SENSITIVE");
        bddAttribute(CKA_KEY_GEN_MECHANISM,     "CKA_KEY_GEN_MECHANISM");
        bddAttribute(CKA_MODIFIABLE,            "CKA_MODIFIABLE");
        bddAttribute(CKA_EC_PARAMS,             "CKA_EC_PARAMS");
        bddAttribute(CKA_EC_POINT,              "CKA_EC_POINT");
        bddAttribute(CKA_SECONDARY_AUTH,        "CKA_SECONDARY_AUTH");
        bddAttribute(CKA_AUTH_PIN_FLAGS,        "CKA_AUTH_PIN_FLAGS");
        bddAttribute(CKA_HW_FEATURE_TYPE,       "CKA_HW_FEATURE_TYPE");
        bddAttribute(CKA_RESET_ON_INIT,         "CKA_RESET_ON_INIT");
        bddAttribute(CKA_HAS_RESET,             "CKA_HAS_RESET");
        bddAttribute(CKA_VENDOR_DEFINED,        "CKA_VENDOR_DEFINED");
        bddAttribute(CKA_NETSCAPE_DB,           "CKA_NETSCAPE_DB");

        bddAttribute(CKA_NETSCAPE_TRUST_SERVER_AUTH,      "CKA_NETSCAPE_TRUST_SERVER_AUTH");
        bddAttribute(CKA_NETSCAPE_TRUST_CLIENT_AUTH,      "CKA_NETSCAPE_TRUST_CLIENT_AUTH");
        bddAttribute(CKA_NETSCAPE_TRUST_CODE_SIGNING,     "CKA_NETSCAPE_TRUST_CODE_SIGNING");
        bddAttribute(CKA_NETSCAPE_TRUST_EMAIL_PROTECTION, "CKA_NETSCAPE_TRUST_EMAIL_PROTECTION");
        bddAttribute(CKA_NETSCAPE_CERT_SHA1_HASH,         "CKA_NETSCAPE_CERT_SHA1_HASH");
        bddAttribute(CKA_NETSCAPE_CERT_MD5_HASH,          "CKA_NETSCAPE_CERT_MD5_HASH");

        bddObjectClbss(CKO_DATA,                "CKO_DATA");
        bddObjectClbss(CKO_CERTIFICATE,         "CKO_CERTIFICATE");
        bddObjectClbss(CKO_PUBLIC_KEY,          "CKO_PUBLIC_KEY");
        bddObjectClbss(CKO_PRIVATE_KEY,         "CKO_PRIVATE_KEY");
        bddObjectClbss(CKO_SECRET_KEY,          "CKO_SECRET_KEY");
        bddObjectClbss(CKO_HW_FEATURE,          "CKO_HW_FEATURE");
        bddObjectClbss(CKO_DOMAIN_PARAMETERS,   "CKO_DOMAIN_PARAMETERS");
        bddObjectClbss(CKO_VENDOR_DEFINED,      "CKO_VENDOR_DEFINED");

        bddObjectClbss(PCKO_ANY,                "*");

    }

}
