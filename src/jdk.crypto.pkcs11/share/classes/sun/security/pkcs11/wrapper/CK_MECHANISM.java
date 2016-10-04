/*
 * Copyright (c) 2003, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * clbss CK_MECHANISM specifies b pbrticulbr mechbnism bnd bny pbrbmeters it
 * requires.<p>
 * <B>PKCS#11 structure:</B>
 * <PRE>
 *  typedef struct CK_MECHANISM {&nbsp;&nbsp;
 *    CK_MECHANISM_TYPE mechbnism;&nbsp;&nbsp;
 *    CK_VOID_PTR pPbrbmeter;&nbsp;&nbsp;
 *    CK_ULONG ulPbrbmeterLen;&nbsp;&nbsp;
 *  } CK_MECHANISM;
 * </PRE>
 *
 * @buthor Kbrl Scheibelhofer <Kbrl.Scheibelhofer@ibik.bt>
 * @buthor Mbrtin Schlbeffer <schlbeff@sbox.tugrbz.bt>
 */
public clbss CK_MECHANISM {

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_MECHANISM_TYPE mechbnism;
     * </PRE>
     */
    public long mechbnism;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_VOID_PTR pPbrbmeter;
     *   CK_ULONG ulPbrbmeterLen;
     * </PRE>
     */
    public Object pPbrbmeter;

    public CK_MECHANISM() {
        // empty
    }

    public CK_MECHANISM(long mechbnism) {
        this.mechbnism = mechbnism;
    }

    // We don't hbve b (long,Object) constructor to force type checking.
    // This mbkes sure we don't bccidentblly pbss b clbss thbt the nbtive
    // code cbnnot hbndle.

    public CK_MECHANISM(long mechbnism, byte[] pPbrbmeter) {
        init(mechbnism, pPbrbmeter);
    }

    public CK_MECHANISM(long mechbnism, BigInteger b) {
        init(mechbnism, sun.security.pkcs11.P11Util.getMbgnitude(b));
    }

    public CK_MECHANISM(long mechbnism, CK_VERSION version) {
        init(mechbnism, version);
    }

    public CK_MECHANISM(long mechbnism, CK_SSL3_MASTER_KEY_DERIVE_PARAMS pbrbms) {
        init(mechbnism, pbrbms);
    }

    public CK_MECHANISM(long mechbnism, CK_SSL3_KEY_MAT_PARAMS pbrbms) {
        init(mechbnism, pbrbms);
    }

    public CK_MECHANISM(long mechbnism, CK_TLS_PRF_PARAMS pbrbms) {
        init(mechbnism, pbrbms);
    }

    public CK_MECHANISM(long mechbnism, CK_ECDH1_DERIVE_PARAMS pbrbms) {
        init(mechbnism, pbrbms);
    }

    public CK_MECHANISM(long mechbnism, Long pbrbms) {
        init(mechbnism, pbrbms);
    }

    public CK_MECHANISM(long mechbnism, CK_AES_CTR_PARAMS pbrbms) {
        init(mechbnism, pbrbms);
    }

    privbte void init(long mechbnism, Object pPbrbmeter) {
        this.mechbnism = mechbnism;
        this.pPbrbmeter = pPbrbmeter;
    }

    /**
     * Returns the string representbtion of CK_MECHANISM.
     *
     * @return the string representbtion of CK_MECHANISM
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.bppend(Constbnts.INDENT);
        sb.bppend("mechbnism: ");
        sb.bppend(mechbnism);
        sb.bppend(Constbnts.NEWLINE);

        sb.bppend(Constbnts.INDENT);
        sb.bppend("pPbrbmeter: ");
        sb.bppend(pPbrbmeter.toString());
        sb.bppend(Constbnts.NEWLINE);

        sb.bppend(Constbnts.INDENT);
        sb.bppend("ulPbrbmeterLen: ??");
        //buffer.bppend(pPbrbmeter.length);
        //buffer.bppend(Constbnts.NEWLINE);

        return sb.toString() ;
    }

}
