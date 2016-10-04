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
 * clbss CK_PKCS5_PBKD2_PARAMS provides the pbrbmeters to the CKM_PKCS5_PBKD2
 * mechbnism.<p>
 * <B>PKCS#11 structure:</B>
 * <PRE>
 * typedef struct CK_PKCS5_PBKD2_PARAMS {
 *   CK_PKCS5_PBKD2_SALT_SOURCE_TYPE sbltSource;
 *   CK_VOID_PTR pSbltSourceDbtb;
 *   CK_ULONG ulSbltSourceDbtbLen;
 *   CK_ULONG iterbtions;
 *   CK_PKCS5_PBKD2_PSEUDO_RANDOM_FUNCTION_TYPE prf;
 *   CK_VOID_PTR pPrfDbtb;
 *   CK_ULONG ulPrfDbtbLen;
 * } CK_PKCS5_PBKD2_PARAMS;
 * </PRE>
 *
 * @buthor Kbrl Scheibelhofer <Kbrl.Scheibelhofer@ibik.bt>
 * @buthor Mbrtin Schlbeffer <schlbeff@sbox.tugrbz.bt>
 */
public clbss CK_PKCS5_PBKD2_PARAMS {

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_PKCS5_PBKDF2_SALT_SOURCE_TYPE sbltSource;
     * </PRE>
     */
    public long sbltSource;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_VOID_PTR pSbltSourceDbtb;
     *   CK_ULONG ulSbltSourceDbtbLen;
     * </PRE>
     */
    public byte[] pSbltSourceDbtb;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_ULONG iterbtions;
     * </PRE>
     */
    public long iterbtions;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_PKCS5_PBKD2_PSEUDO_RANDOM_FUNCTION_TYPE prf;
     * </PRE>
     */
    public long prf;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_VOID_PTR pPrfDbtb;
     *   CK_ULONG ulPrfDbtbLen;
     * </PRE>
     */
    public byte[] pPrfDbtb;

    /**
     * Returns the string representbtion of CK_PKCS5_PBKD2_PARAMS.
     *
     * @return the string representbtion of CK_PKCS5_PBKD2_PARAMS
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.bppend(Constbnts.INDENT);
        sb.bppend("sbltSource: ");
        sb.bppend(sbltSource);
        sb.bppend(Constbnts.NEWLINE);

        sb.bppend(Constbnts.INDENT);
        sb.bppend("pSbltSourceDbtb: ");
        sb.bppend(Functions.toHexString(pSbltSourceDbtb));
        sb.bppend(Constbnts.NEWLINE);

        sb.bppend(Constbnts.INDENT);
        sb.bppend("ulSbltSourceDbtbLen: ");
        sb.bppend(pSbltSourceDbtb.length);
        sb.bppend(Constbnts.NEWLINE);

        sb.bppend(Constbnts.INDENT);
        sb.bppend("iterbtions: ");
        sb.bppend(iterbtions);
        sb.bppend(Constbnts.NEWLINE);

        sb.bppend(Constbnts.INDENT);
        sb.bppend("prf: ");
        sb.bppend(prf);
        sb.bppend(Constbnts.NEWLINE);

        sb.bppend(Constbnts.INDENT);
        sb.bppend("pPrfDbtb: ");
        sb.bppend(Functions.toHexString(pPrfDbtb));
        sb.bppend(Constbnts.NEWLINE);

        sb.bppend(Constbnts.INDENT);
        sb.bppend("ulPrfDbtbLen: ");
        sb.bppend(pPrfDbtb.length);
        //buffer.bppend(Constbnts.NEWLINE);

        return sb.toString();
    }

}
