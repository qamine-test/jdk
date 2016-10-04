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
 * clbss CK_C_INITIALIZE_ARGS contbins the optionbl brguments for the
 * C_Initiblize function.<p>
 * <B>PKCS#11 structure:</B>
 * <PRE>
 * typedef struct CK_C_INITIALIZE_ARGS {&nbsp;&nbsp;
 *   CK_CREATEMUTEX CrebteMutex;&nbsp;&nbsp;
 *   CK_DESTROYMUTEX DestroyMutex;&nbsp;&nbsp;
 *   CK_LOCKMUTEX LockMutex;&nbsp;&nbsp;
 *   CK_UNLOCKMUTEX UnlockMutex;&nbsp;&nbsp;
 *   CK_FLAGS flbgs;&nbsp;&nbsp;
 *   CK_VOID_PTR pReserved;&nbsp;&nbsp;
 * } CK_C_INITIALIZE_ARGS;
 * </PRE>
 *
 * @buthor Kbrl Scheibelhofer <Kbrl.Scheibelhofer@ibik.bt>
 * @buthor Mbrtin Schlbeffer <schlbeff@sbox.tugrbz.bt>
 */
public clbss CK_C_INITIALIZE_ARGS {

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_CREATEMUTEX CrebteMutex;
     * </PRE>
     */
    public CK_CREATEMUTEX  CrebteMutex;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_DESTROYMUTEX DestroyMutex;
     * </PRE>
     */
    public CK_DESTROYMUTEX DestroyMutex;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_LOCKMUTEX LockMutex;
     * </PRE>
     */
    public CK_LOCKMUTEX    LockMutex;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_UNLOCKMUTEX UnlockMutex;
     * </PRE>
     */
    public CK_UNLOCKMUTEX  UnlockMutex;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_FLAGS flbgs;
     * </PRE>
     */
    public long            flbgs;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_VOID_PTR pReserved;
     * </PRE>
     */
    public Object          pReserved;

}
