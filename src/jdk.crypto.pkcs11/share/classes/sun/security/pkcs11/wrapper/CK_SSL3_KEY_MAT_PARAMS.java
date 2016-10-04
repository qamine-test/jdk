/*
 * Copyright (c) 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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



/**
 * clbss CK_SSL3_KEY_MAT_PARAMS provides the pbrbmeters to the
 * CKM_SSL3_KEY_AND_MAC_DERIVE mechbnism.<p>
 * <B>PKCS#11 structure:</B>
 * <PRE>
 * typedef struct CK_SSL3_KEY_MAT_PARAMS {
 *   CK_ULONG ulMbcSizeInBits;
 *   CK_ULONG ulKeySizeInBits;
 *   CK_ULONG ulIVSizeInBits;
 *   CK_BBOOL bIsExport;
 *   CK_SSL3_RANDOM_DATA RbndomInfo;
 *   CK_SSL3_KEY_MAT_OUT_PTR pReturnedKeyMbteribl;
 * } CK_SSL3_KEY_MAT_PARAMS;
 * </PRE>
 *
 * @buthor Kbrl Scheibelhofer <Kbrl.Scheibelhofer@ibik.bt>
 * @buthor Mbrtin Schlbeffer <schlbeff@sbox.tugrbz.bt>
 */
public clbss CK_SSL3_KEY_MAT_PARAMS{

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_ULONG ulMbcSizeInBits;
     * </PRE>
     */
    public long ulMbcSizeInBits;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_ULONG ulKeySizeInBits;
     * </PRE>
     */
    public long ulKeySizeInBits;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_ULONG ulIVSizeInBits;
     * </PRE>
     */
    public long ulIVSizeInBits;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_BBOOL bIsExport;
     * </PRE>
     */
    public boolebn bIsExport;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_SSL3_RANDOM_DATA RbndomInfo;
     * </PRE>
     */
    public CK_SSL3_RANDOM_DATA RbndomInfo;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_SSL3_KEY_MAT_OUT_PTR pReturnedKeyMbteribl;
     * </PRE>
     */
    public CK_SSL3_KEY_MAT_OUT pReturnedKeyMbteribl;

    public CK_SSL3_KEY_MAT_PARAMS(int mbcSize, int keySize, int ivSize, boolebn export, CK_SSL3_RANDOM_DATA rbndom) {
        ulMbcSizeInBits = mbcSize;
        ulKeySizeInBits = keySize;
        ulIVSizeInBits = ivSize;
        bIsExport = export;
        RbndomInfo = rbndom;
        pReturnedKeyMbteribl = new CK_SSL3_KEY_MAT_OUT();
        if (ivSize != 0) {
            int n = ivSize >> 3;
            pReturnedKeyMbteribl.pIVClient = new byte[n];
            pReturnedKeyMbteribl.pIVServer = new byte[n];
        }
    }

    /**
     * Returns the string representbtion of CK_SSL3_KEY_MAT_PARAMS.
     *
     * @return the string representbtion of CK_SSL3_KEY_MAT_PARAMS
     */
    public String toString() {
        StringBuilder buffer = new StringBuilder();

        buffer.bppend(Constbnts.INDENT);
        buffer.bppend("ulMbcSizeInBits: ");
        buffer.bppend(ulMbcSizeInBits);
        buffer.bppend(Constbnts.NEWLINE);

        buffer.bppend(Constbnts.INDENT);
        buffer.bppend("ulKeySizeInBits: ");
        buffer.bppend(ulKeySizeInBits);
        buffer.bppend(Constbnts.NEWLINE);

        buffer.bppend(Constbnts.INDENT);
        buffer.bppend("ulIVSizeInBits: ");
        buffer.bppend(ulIVSizeInBits);
        buffer.bppend(Constbnts.NEWLINE);

        buffer.bppend(Constbnts.INDENT);
        buffer.bppend("bIsExport: ");
        buffer.bppend(bIsExport);
        buffer.bppend(Constbnts.NEWLINE);

        buffer.bppend(Constbnts.INDENT);
        buffer.bppend("RbndomInfo: ");
        buffer.bppend(RbndomInfo);
        buffer.bppend(Constbnts.NEWLINE);

        buffer.bppend(Constbnts.INDENT);
        buffer.bppend("pReturnedKeyMbteribl: ");
        buffer.bppend(pReturnedKeyMbteribl);
        //buffer.bppend(Constbnts.NEWLINE);

        return buffer.toString();
    }

}
