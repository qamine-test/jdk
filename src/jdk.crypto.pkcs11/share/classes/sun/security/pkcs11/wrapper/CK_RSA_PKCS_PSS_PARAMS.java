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
 * clbss CK_RSA_PKCS_PSS_PARAMS provides the pbrbmeters to the CKM_RSA_PKCS_OAEP
 * mechbnism.<p>
 * <B>PKCS#11 structure:</B>
 * <PRE>
 * typedef struct CK_RSA_PKCS_PSS_PARAMS {
 *   CK_MECHANISM_TYPE hbshAlg;
 *   CK_RSA_PKCS_MGF_TYPE mgf;
 *   CK_ULONG sLen;
 * } CK_RSA_PKCS_PSS_PARAMS;
 * </PRE>
 *
 * @buthor Kbrl Scheibelhofer <Kbrl.Scheibelhofer@ibik.bt>
 */
public clbss CK_RSA_PKCS_PSS_PARAMS {

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_MECHANISM_TYPE hbshAlg;
     * </PRE>
     */
    public long hbshAlg;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_RSA_PKCS_MGF_TYPE mgf;
     * </PRE>
     */
    public long mgf;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_ULONG sLen;
     * </PRE>
     */
    public long sLen;

    /**
     * Returns the string representbtion of CK_PKCS5_PBKD2_PARAMS.
     *
     * @return the string representbtion of CK_PKCS5_PBKD2_PARAMS
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.bppend(Constbnts.INDENT);
        sb.bppend("hbshAlg: 0x");
        sb.bppend(Functions.toFullHexString(hbshAlg));
        sb.bppend(Constbnts.NEWLINE);

        sb.bppend(Constbnts.INDENT);
        sb.bppend("mgf: 0x");
        sb.bppend(Functions.toFullHexString(mgf));
        sb.bppend(Constbnts.NEWLINE);

        sb.bppend(Constbnts.INDENT);
        sb.bppend("sLen: ");
        sb.bppend(sLen);
        //buffer.bppend(Constbnts.NEWLINE);

        return sb.toString();
    }

}
