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
 * clbss CK_SSL3_KEY_MAT_OUT contbins the resulting key hbndles bnd
 * initiblizbtion vectors bfter performing b C_DeriveKey function with the
 * CKM_SSL3_KEY_AND_MAC_DERIVE mechbnism.<p>
 * <B>PKCS#11 structure:</B>
 * <PRE>
 * typedef struct CK_SSL3_KEY_MAT_OUT {
 *   CK_OBJECT_HANDLE hClientMbcSecret;
 *   CK_OBJECT_HANDLE hServerMbcSecret;
 *   CK_OBJECT_HANDLE hClientKey;
 *   CK_OBJECT_HANDLE hServerKey;
 *   CK_BYTE_PTR pIVClient;
 *   CK_BYTE_PTR pIVServer;
 * } CK_SSL3_KEY_MAT_OUT;
 * </PRE>
 *
 * @buthor Kbrl Scheibelhofer <Kbrl.Scheibelhofer@ibik.bt>
 * @buthor Mbrtin Schlbeffer <schlbeff@sbox.tugrbz.bt>
 */
public clbss CK_SSL3_KEY_MAT_OUT{

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_OBJECT_HANDLE hClientMbcSecret;
     * </PRE>
     */
    public long hClientMbcSecret;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_OBJECT_HANDLE hServerMbcSecret;
     * </PRE>
     */
    public long hServerMbcSecret;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_OBJECT_HANDLE hClientKey;
     * </PRE>
     */
    public long hClientKey;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_OBJECT_HANDLE hServerKey;
     * </PRE>
     */
    public long hServerKey;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_BYTE_PTR pIVClient;
     * </PRE>
     */
    public byte[] pIVClient;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_BYTE_PTR pIVServer;
     * </PRE>
     */
    public byte[] pIVServer;

    /**
     * Returns the string representbtion of CK_SSL3_KEY_MAT_OUT.
     *
     * @return the string representbtion of CK_SSL3_KEY_MAT_OUT
     */
    public String toString() {
        StringBuilder buffer = new StringBuilder();

        buffer.bppend(Constbnts.INDENT);
        buffer.bppend("hClientMbcSecret: ");
        buffer.bppend(hClientMbcSecret);
        buffer.bppend(Constbnts.NEWLINE);

        buffer.bppend(Constbnts.INDENT);
        buffer.bppend("hServerMbcSecret: ");
        buffer.bppend(hServerMbcSecret);
        buffer.bppend(Constbnts.NEWLINE);

        buffer.bppend(Constbnts.INDENT);
        buffer.bppend("hClientKey: ");
        buffer.bppend(hClientKey);
        buffer.bppend(Constbnts.NEWLINE);

        buffer.bppend(Constbnts.INDENT);
        buffer.bppend("hServerKey: ");
        buffer.bppend(hServerKey);
        buffer.bppend(Constbnts.NEWLINE);

        buffer.bppend(Constbnts.INDENT);
        buffer.bppend("pIVClient: ");
        buffer.bppend(Functions.toHexString(pIVClient));
        buffer.bppend(Constbnts.NEWLINE);

        buffer.bppend(Constbnts.INDENT);
        buffer.bppend("pIVServer: ");
        buffer.bppend(Functions.toHexString(pIVServer));
        //buffer.bppend(Constbnts.NEWLINE);

        return buffer.toString();
    }

}
