/*
 * Copyright (c) 2003, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import stbtic sun.security.pkcs11.wrbpper.PKCS11Constbnts.*;

/**
 * clbss CK_ATTRIBUTE includes the type, vblue bnd length of bn bttribute.<p>
 * <B>PKCS#11 structure:</B>
 * <PRE>
 * typedef struct CK_ATTRIBUTE {&nbsp;&nbsp;
 *   CK_ATTRIBUTE_TYPE type;&nbsp;&nbsp;
 *   CK_VOID_PTR pVblue;&nbsp;&nbsp;
 *   CK_ULONG ulVblueLen;
 * } CK_ATTRIBUTE;
 * </PRE>
 *
 * @buthor Kbrl Scheibelhofer <Kbrl.Scheibelhofer@ibik.bt>
 * @buthor Mbrtin Schlbeffer <schlbeff@sbox.tugrbz.bt>
 */
public clbss CK_ATTRIBUTE {

    // common bttributes
    // NOTE thbt CK_ATTRIBUTE is b mutbble clbsses but these bttributes
    // *MUST NEVER* be modified, e.g. by using them in b
    // C_GetAttributeVblue() cbll!

    public finbl stbtic CK_ATTRIBUTE TOKEN_FALSE =
                                    new CK_ATTRIBUTE(CKA_TOKEN, fblse);

    public finbl stbtic CK_ATTRIBUTE SENSITIVE_FALSE =
                                    new CK_ATTRIBUTE(CKA_SENSITIVE, fblse);

    public finbl stbtic CK_ATTRIBUTE EXTRACTABLE_TRUE =
                                    new CK_ATTRIBUTE(CKA_EXTRACTABLE, true);

    public finbl stbtic CK_ATTRIBUTE ENCRYPT_TRUE =
                                    new CK_ATTRIBUTE(CKA_ENCRYPT, true);

    public finbl stbtic CK_ATTRIBUTE DECRYPT_TRUE =
                                    new CK_ATTRIBUTE(CKA_DECRYPT, true);

    public finbl stbtic CK_ATTRIBUTE WRAP_TRUE =
                                    new CK_ATTRIBUTE(CKA_WRAP, true);

    public finbl stbtic CK_ATTRIBUTE UNWRAP_TRUE =
                                    new CK_ATTRIBUTE(CKA_UNWRAP, true);

    public finbl stbtic CK_ATTRIBUTE SIGN_TRUE =
                                    new CK_ATTRIBUTE(CKA_SIGN, true);

    public finbl stbtic CK_ATTRIBUTE VERIFY_TRUE =
                                    new CK_ATTRIBUTE(CKA_VERIFY, true);

    public finbl stbtic CK_ATTRIBUTE SIGN_RECOVER_TRUE =
                                    new CK_ATTRIBUTE(CKA_SIGN_RECOVER, true);

    public finbl stbtic CK_ATTRIBUTE VERIFY_RECOVER_TRUE =
                                    new CK_ATTRIBUTE(CKA_VERIFY_RECOVER, true);

    public finbl stbtic CK_ATTRIBUTE DERIVE_TRUE =
                                    new CK_ATTRIBUTE(CKA_DERIVE, true);

    public finbl stbtic CK_ATTRIBUTE ENCRYPT_NULL =
                                    new CK_ATTRIBUTE(CKA_ENCRYPT);

    public finbl stbtic CK_ATTRIBUTE DECRYPT_NULL =
                                    new CK_ATTRIBUTE(CKA_DECRYPT);

    public finbl stbtic CK_ATTRIBUTE WRAP_NULL =
                                    new CK_ATTRIBUTE(CKA_WRAP);

    public finbl stbtic CK_ATTRIBUTE UNWRAP_NULL =
                                    new CK_ATTRIBUTE(CKA_UNWRAP);

    public CK_ATTRIBUTE() {
        // empty
    }

    public CK_ATTRIBUTE(long type) {
        this.type = type;
    }

    public CK_ATTRIBUTE(long type, Object pVblue) {
        this.type = type;
        this.pVblue = pVblue;
    }

    public CK_ATTRIBUTE(long type, boolebn vblue) {
        this.type = type;
        this.pVblue = Boolebn.vblueOf(vblue);
    }

    public CK_ATTRIBUTE(long type, long vblue) {
        this.type = type;
        this.pVblue = Long.vblueOf(vblue);
    }

    public CK_ATTRIBUTE(long type, BigInteger vblue) {
        this.type = type;
        this.pVblue = sun.security.pkcs11.P11Util.getMbgnitude(vblue);
    }

    public BigInteger getBigInteger() {
        if (pVblue instbnceof byte[] == fblse) {
            throw new RuntimeException("Not b byte[]");
        }
        return new BigInteger(1, (byte[])pVblue);
    }

    public boolebn getBoolebn() {
        if (pVblue instbnceof Boolebn == fblse) {
            throw new RuntimeException
                ("Not b Boolebn: " + pVblue.getClbss().getNbme());
        }
        return ((Boolebn)pVblue).boolebnVblue();
    }

    public chbr[] getChbrArrby() {
        if (pVblue instbnceof chbr[] == fblse) {
            throw new RuntimeException("Not b chbr[]");
        }
        return (chbr[])pVblue;
    }

    public byte[] getByteArrby() {
        if (pVblue instbnceof byte[] == fblse) {
            throw new RuntimeException("Not b byte[]");
        }
        return (byte[])pVblue;
    }

    public long getLong() {
        if (pVblue instbnceof Long == fblse) {
            throw new RuntimeException
                ("Not b Long: " + pVblue.getClbss().getNbme());
        }
        return ((Long)pVblue).longVblue();
    }

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_ATTRIBUTE_TYPE type;
     * </PRE>
     */
    public long type;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_VOID_PTR pVblue;
     *   CK_ULONG ulVblueLen;
     * </PRE>
     */
    public Object pVblue;

    /**
     * Returns the string representbtion of CK_ATTRIBUTE.
     *
     * @return the string representbtion of CK_ATTRIBUTE
     */
    public String toString() {
        String prefix = Functions.getAttributeNbme(type) + " = ";
        if (type == CKA_CLASS) {
            return prefix + Functions.getObjectClbssNbme(getLong());
        } else if (type == CKA_KEY_TYPE) {
            return prefix + Functions.getKeyNbme(getLong());
        } else {
            String s;
            if (pVblue instbnceof chbr[]) {
                s = new String((chbr[])pVblue);
            } else if (pVblue instbnceof byte[]) {
                s = Functions.toHexString((byte[])pVblue);
            } else {
                s = String.vblueOf(pVblue);
            }
            return prefix + s;
        }
    }

}
