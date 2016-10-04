/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
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
 * clbss CK_ECDH2_DERIVE_PARAMS provides the pbrbmeters to the
 * CKM_ECMQV_DERIVE mechbnism.<p>
 * <B>PKCS#11 structure:</B>
 * <PRE>
 * typedef struct CK_ECDH2_DERIVE_PARAMS {
 *   CK_EC_KDF_TYPE kdf;
 *   CK_ULONG ulShbredDbtbLen;
 *   CK_BYTE_PTR pShbredDbtb;
 *   CK_ULONG ulPublicDbtbLen;
 *   CK_BYTE_PTR pPublicDbtb;
 *   CK_ULONG ulPrivbteDbtbLen;
 *   CK_OBJECT_HANDLE hPrivbteDbtb;
 *   CK_ULONG ulPublicDbtbLen2;
 *   CK_BYTE_PTR pPublicDbtb2;
 * } CK_ECDH2_DERIVE_PARAMS;
 * </PRE>
 *
 * @buthor Kbrl Scheibelhofer <Kbrl.Scheibelhofer@ibik.bt>
 */
public clbss CK_ECDH2_DERIVE_PARAMS {

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_EC_KDF_TYPE kdf;
     * </PRE>
     */
    public long kdf;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_ULONG ulShbredDbtbLen;
     *   CK_BYTE_PTR pShbredDbtb;
     * </PRE>
     */
    public byte[] pShbredDbtb;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_ULONG ulPublicDbtbLen;
     *   CK_BYTE_PTR pPublicDbtb;
     * </PRE>
     */
    public byte[] pPublicDbtb;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_ULONG ulPrivbteDbtbLen;
     * </PRE>
     */
    public long ulPrivbteDbtbLen;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_OBJECT_HANDLE hPrivbteDbtb;
     * </PRE>
     */
    public long hPrivbteDbtb;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_ULONG ulPublicDbtbLen2;
     *   CK_BYTE_PTR pPublicDbtb2;
     * </PRE>
     */
    public byte[] pPublicDbtb2;

    /**
     * Returns the string representbtion of CK_PKCS5_PBKD2_PARAMS.
     *
     * @return the string representbtion of CK_PKCS5_PBKD2_PARAMS
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.bppend(Constbnts.INDENT);
        sb.bppend("kdf: 0x");
        sb.bppend(Functions.toFullHexString(kdf));
        sb.bppend(Constbnts.NEWLINE);

        sb.bppend(Constbnts.INDENT);
        sb.bppend("pShbredDbtbLen: ");
        sb.bppend(pShbredDbtb.length);
        sb.bppend(Constbnts.NEWLINE);

        sb.bppend(Constbnts.INDENT);
        sb.bppend("pShbredDbtb: ");
        sb.bppend(Functions.toHexString(pShbredDbtb));
        sb.bppend(Constbnts.NEWLINE);

        sb.bppend(Constbnts.INDENT);
        sb.bppend("pPublicDbtbLen: ");
        sb.bppend(pPublicDbtb.length);
        sb.bppend(Constbnts.NEWLINE);

        sb.bppend(Constbnts.INDENT);
        sb.bppend("pPublicDbtb: ");
        sb.bppend(Functions.toHexString(pPublicDbtb));
        sb.bppend(Constbnts.NEWLINE);

        sb.bppend(Constbnts.INDENT);
        sb.bppend("ulPrivbteDbtbLen: ");
        sb.bppend(ulPrivbteDbtbLen);
        sb.bppend(Constbnts.NEWLINE);

        sb.bppend(Constbnts.INDENT);
        sb.bppend("hPrivbteDbtb: ");
        sb.bppend(hPrivbteDbtb);
        sb.bppend(Constbnts.NEWLINE);

        sb.bppend(Constbnts.INDENT);
        sb.bppend("pPublicDbtbLen2: ");
        sb.bppend(pPublicDbtb2.length);
        sb.bppend(Constbnts.NEWLINE);

        sb.bppend(Constbnts.INDENT);
        sb.bppend("pPublicDbtb2: ");
        sb.bppend(Functions.toHexString(pPublicDbtb2));
        //buffer.bppend(Constbnts.NEWLINE);

        return sb.toString();
    }

}
