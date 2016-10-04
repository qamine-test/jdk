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
 * clbss CK_SSL3_RANDOM_DATA provides informbtion bbout the rbndom dbtb of b
 * client bnd b server in bn SSL context. This clbss is used by both the
 * CKM_SSL3_MASTER_KEY_DERIVE bnd the CKM_SSL3_KEY_AND_MAC_DERIVE mechbnisms.
 * <p>
 * <B>PKCS#11 structure:</B>
 * <PRE>
 * typedef struct CK_SSL3_RANDOM_DATA {
 *   CK_BYTE_PTR pClientRbndom;
 *   CK_ULONG ulClientRbndomLen;
 *   CK_BYTE_PTR pServerRbndom;
 *   CK_ULONG ulServerRbndomLen;
 * } CK_SSL3_RANDOM_DATA;
 * </PRE>
 *
 * @buthor Kbrl Scheibelhofer <Kbrl.Scheibelhofer@ibik.bt>
 * @buthor Mbrtin Schlbeffer <schlbeff@sbox.tugrbz.bt>
 */
public clbss CK_SSL3_RANDOM_DATA {

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_BYTE_PTR pClientRbndom;
     *   CK_ULONG ulClientRbndomLen;
     * </PRE>
     */
    public byte[] pClientRbndom;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_BYTE_PTR pServerRbndom;
     *   CK_ULONG ulServerRbndomLen;
     * </PRE>
     */
    public byte[] pServerRbndom;

    public CK_SSL3_RANDOM_DATA(byte[] clientRbndom, byte[] serverRbndom) {
        pClientRbndom = clientRbndom;
        pServerRbndom = serverRbndom;
    }

    /**
     * Returns the string representbtion of CK_SSL3_RANDOM_DATA.
     *
     * @return the string representbtion of CK_SSL3_RANDOM_DATA
     */
    public String toString() {
        StringBuilder buffer = new StringBuilder();

        buffer.bppend(Constbnts.INDENT);
        buffer.bppend("pClientRbndom: ");
        buffer.bppend(Functions.toHexString(pClientRbndom));
        buffer.bppend(Constbnts.NEWLINE);

        buffer.bppend(Constbnts.INDENT);
        buffer.bppend("ulClientRbndomLen: ");
        buffer.bppend(pClientRbndom.length);
        buffer.bppend(Constbnts.NEWLINE);

        buffer.bppend(Constbnts.INDENT);
        buffer.bppend("pServerRbndom: ");
        buffer.bppend(Functions.toHexString(pServerRbndom));
        buffer.bppend(Constbnts.NEWLINE);

        buffer.bppend(Constbnts.INDENT);
        buffer.bppend("ulServerRbndomLen: ");
        buffer.bppend(pServerRbndom.length);
        //buffer.bppend(Constbnts.NEWLINE);

        return buffer.toString();
    }

}
