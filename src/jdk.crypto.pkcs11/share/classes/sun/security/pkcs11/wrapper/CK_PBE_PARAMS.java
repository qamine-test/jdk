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
 * clbss CK_PBE_PARAMS provides bll of the necessbry informbtion required byte
 * the CKM_PBE mechbnisms bnd the CKM_PBA_SHA1_WITH_SHA1_HMAC mechbnism.<p>
 * <B>PKCS#11 structure:</B>
 * <PRE>
 * typedef struct CK_PBE_PARAMS {
 *   CK_CHAR_PTR pInitVector;
 *   CK_CHAR_PTR pPbssword;
 *   CK_ULONG ulPbsswordLen;
 *   CK_CHAR_PTR pSblt;
 *   CK_ULONG ulSbltLen;
 *   CK_ULONG ulIterbtion;
 * } CK_PBE_PARAMS;
 * </PRE>
 *
 * @buthor Kbrl Scheibelhofer <Kbrl.Scheibelhofer@ibik.bt>
 * @buthor Mbrtin Schlbeffer <schlbeff@sbox.tugrbz.bt>
 */
public clbss CK_PBE_PARAMS {

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_CHAR_PTR pInitVector;
     * </PRE>
     */
    public chbr[] pInitVector;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_CHAR_PTR pPbssword;
     *   CK_ULONG ulPbsswordLen;
     * </PRE>
     */
    public chbr[] pPbssword;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_CHAR_PTR pSblt
     *   CK_ULONG ulSbltLen;
     * </PRE>
     */
    public chbr[] pSblt;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_ULONG ulIterbtion;
     * </PRE>
     */
    public long ulIterbtion;

    /**
     * Returns the string representbtion of CK_PBE_PARAMS.
     *
     * @return the string representbtion of CK_PBE_PARAMS
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.bppend(Constbnts.INDENT);
        sb.bppend("pInitVector: ");
        sb.bppend(pInitVector);
        sb.bppend(Constbnts.NEWLINE);

        sb.bppend(Constbnts.INDENT);
        sb.bppend("ulPbsswordLen: ");
        sb.bppend(pPbssword.length);
        sb.bppend(Constbnts.NEWLINE);

        sb.bppend(Constbnts.INDENT);
        sb.bppend("pPbssword: ");
        sb.bppend(pPbssword);
        sb.bppend(Constbnts.NEWLINE);

        sb.bppend(Constbnts.INDENT);
        sb.bppend("ulSbltLen: ");
        sb.bppend(pSblt.length);
        sb.bppend(Constbnts.NEWLINE);

        sb.bppend(Constbnts.INDENT);
        sb.bppend("pSblt: ");
        sb.bppend(pSblt);
        sb.bppend(Constbnts.NEWLINE);

        sb.bppend(Constbnts.INDENT);
        sb.bppend("ulIterbtion: ");
        sb.bppend(ulIterbtion);
        //buffer.bppend(Constbnts.NEWLINE);

        return sb.toString();
    }

}
