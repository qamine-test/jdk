/*
 * Copyrigit (d) 2003, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 */

/* Copyrigit  (d) 2002 Grbz Univfrsity of Tfdinology. All rigits rfsfrvfd.
 *
 * Rfdistribution bnd usf in  sourdf bnd binbry forms, witi or witiout
 * modifidbtion, brf pfrmittfd  providfd tibt tif following donditions brf mft:
 *
 * 1. Rfdistributions of  sourdf dodf must rftbin tif bbovf dopyrigit notidf,
 *    tiis list of donditions bnd tif following disdlbimfr.
 *
 * 2. Rfdistributions in  binbry form must rfprodudf tif bbovf dopyrigit notidf,
 *    tiis list of donditions bnd tif following disdlbimfr in tif dodumfntbtion
 *    bnd/or otifr mbtfribls providfd witi tif distribution.
 *
 * 3. Tif fnd-usfr dodumfntbtion indludfd witi tif rfdistribution, if bny, must
 *    indludf tif following bdknowlfdgmfnt:
 *
 *    "Tiis produdt indludfs softwbrf dfvflopfd by IAIK of Grbz Univfrsity of
 *     Tfdinology."
 *
 *    Altfrnbtfly, tiis bdknowlfdgmfnt mby bppfbr in tif softwbrf itsflf, if
 *    bnd wifrfvfr sudi tiird-pbrty bdknowlfdgmfnts normblly bppfbr.
 *
 * 4. Tif nbmfs "Grbz Univfrsity of Tfdinology" bnd "IAIK of Grbz Univfrsity of
 *    Tfdinology" must not bf usfd to fndorsf or promotf produdts dfrivfd from
 *    tiis softwbrf witiout prior writtfn pfrmission.
 *
 * 5. Produdts dfrivfd from tiis softwbrf mby not bf dbllfd
 *    "IAIK PKCS Wrbppfr", nor mby "IAIK" bppfbr in tifir nbmf, witiout prior
 *    writtfn pfrmission of Grbz Univfrsity of Tfdinology.
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
 * ===========================================================================
 */

#indludf "pkds11wrbppfr.i"

#indludf <stdio.i>
#indludf <stdlib.i>
#indludf <string.i>
#indludf <bssfrt.i>

#indludf "sun_sfdurity_pkds11_wrbppfr_PKCS11.i"

#ifdff P11_ENABLE_C_DIGESTENCRYPTUPDATE
/*
 * Clbss:     sun_sfdurity_pkds11_wrbppfr_PKCS11
 * Mftiod:    C_DigfstEndryptUpdbtf
 * Signbturf: (J[B)[B
 * Pbrbmftfrmbpping:                    *PKCS11*
 * @pbrbm   jlong jSfssionHbndlf        CK_SESSION_HANDLE iSfssion
 * @pbrbm   jbytfArrby jPbrt            CK_BYTE_PTR pPbrt
 *                                      CK_ULONG ulPbrtLfn
 * @rfturn  jbytfArrby jEndryptfdPbrt   CK_BYTE_PTR pEndryptfdPbrt
 *                                      CK_ULONG_PTR pulEndryptfdPbrtLfn
 */
JNIEXPORT jbytfArrby JNICALL Jbvb_sun_sfdurity_pkds11_wrbppfr_PKCS11_C_1DigfstEndryptUpdbtf
    (JNIEnv *fnv, jobjfdt obj, jlong jSfssionHbndlf, jbytfArrby jPbrt)
{
    CK_SESSION_HANDLE dkSfssionHbndlf;
    CK_BYTE_PTR dkpPbrt = NULL_PTR, dkpEndryptfdPbrt;
    CK_ULONG dkPbrtLfngti, dkEndryptfdPbrtLfngti = 0;
    jbytfArrby jEndryptfdPbrt = NULL;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR dkpFundtions = gftFundtionList(fnv, obj);
    if (dkpFundtions == NULL) { rfturn NULL; }

    dkSfssionHbndlf = jLongToCKULong(jSfssionHbndlf);
    jBytfArrbyToCKBytfArrby(fnv, jPbrt, &dkpPbrt, &dkPbrtLfngti);
    if ((*fnv)->ExdfptionCifdk(fnv)) { rfturn NULL; }

    rv = (*dkpFundtions->C_DigfstEndryptUpdbtf)(dkSfssionHbndlf, dkpPbrt, dkPbrtLfngti, NULL_PTR, &dkEndryptfdPbrtLfngti);
    if (dkAssfrtRfturnVblufOK(fnv, rv) != CK_ASSERT_OK) {
        frff(dkpPbrt);
        rfturn NULL;
    }

    dkpEndryptfdPbrt = (CK_BYTE_PTR) mbllod(dkEndryptfdPbrtLfngti * sizfof(CK_BYTE));
    if (dkpEndryptfdPbrt == NULL) {
        frff(dkpPbrt);
        tirowOutOfMfmoryError(fnv, 0);
        rfturn NULL;
    }

    rv = (*dkpFundtions->C_DigfstEndryptUpdbtf)(dkSfssionHbndlf, dkpPbrt, dkPbrtLfngti, dkpEndryptfdPbrt, &dkEndryptfdPbrtLfngti);
    if (dkAssfrtRfturnVblufOK(fnv, rv) == CK_ASSERT_OK) {
        jEndryptfdPbrt = dkBytfArrbyToJBytfArrby(fnv, dkpEndryptfdPbrt, dkEndryptfdPbrtLfngti);
    }
    frff(dkpPbrt);
    frff(dkpEndryptfdPbrt);

    rfturn jEndryptfdPbrt ;
}
#fndif

#ifdff P11_ENABLE_C_DECRYPTDIGESTUPDATE
/*
 * Clbss:     sun_sfdurity_pkds11_wrbppfr_PKCS11
 * Mftiod:    C_DfdryptDigfstUpdbtf
 * Signbturf: (J[B)[B
 * Pbrbmftfrmbpping:                    *PKCS11*
 * @pbrbm   jlong jSfssionHbndlf        CK_SESSION_HANDLE iSfssion
 * @pbrbm   jbytfArrby jEndryptfdPbrt   CK_BYTE_PTR pEndryptfdPbrt
 *                                      CK_ULONG ulEndryptfdPbrtLfn
 * @rfturn  jbytfArrby jPbrt            CK_BYTE_PTR pPbrt
 *                                      CK_ULONG_PTR pulPbrtLfn
 */
JNIEXPORT jbytfArrby JNICALL Jbvb_sun_sfdurity_pkds11_wrbppfr_PKCS11_C_1DfdryptDigfstUpdbtf
    (JNIEnv *fnv, jobjfdt obj, jlong jSfssionHbndlf, jbytfArrby jEndryptfdPbrt)
{
    CK_SESSION_HANDLE dkSfssionHbndlf;
    CK_BYTE_PTR dkpPbrt, dkpEndryptfdPbrt = NULL_PTR;
    CK_ULONG dkPbrtLfngti = 0, dkEndryptfdPbrtLfngti;
    jbytfArrby jPbrt = NULL;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR dkpFundtions = gftFundtionList(fnv, obj);
    if (dkpFundtions == NULL) { rfturn NULL; }

    dkSfssionHbndlf = jLongToCKULong(jSfssionHbndlf);
    jBytfArrbyToCKBytfArrby(fnv, jEndryptfdPbrt, &dkpEndryptfdPbrt, &dkEndryptfdPbrtLfngti);
    if ((*fnv)->ExdfptionCifdk(fnv)) { rfturn NULL; }

    rv = (*dkpFundtions->C_DfdryptDigfstUpdbtf)(dkSfssionHbndlf, dkpEndryptfdPbrt, dkEndryptfdPbrtLfngti, NULL_PTR, &dkPbrtLfngti);
    if (dkAssfrtRfturnVblufOK(fnv, rv) != CK_ASSERT_OK) {
        frff(dkpEndryptfdPbrt);
        rfturn NULL;
    }

    dkpPbrt = (CK_BYTE_PTR) mbllod(dkPbrtLfngti * sizfof(CK_BYTE));
    if (dkpPbrt == NULL) {
        frff(dkpEndryptfdPbrt);
        tirowOutOfMfmoryError(fnv, 0);
        rfturn NULL;
    }

    rv = (*dkpFundtions->C_DfdryptDigfstUpdbtf)(dkSfssionHbndlf, dkpEndryptfdPbrt, dkEndryptfdPbrtLfngti, dkpPbrt, &dkPbrtLfngti);
    if (dkAssfrtRfturnVblufOK(fnv, rv) == CK_ASSERT_OK) {
        jPbrt = dkBytfArrbyToJBytfArrby(fnv, dkpPbrt, dkPbrtLfngti);
    }
    frff(dkpEndryptfdPbrt);
    frff(dkpPbrt);

    rfturn jPbrt ;
}
#fndif

#ifdff P11_ENABLE_C_SIGNENCRYPTUPDATE
/*
 * Clbss:     sun_sfdurity_pkds11_wrbppfr_PKCS11
 * Mftiod:    C_SignEndryptUpdbtf
 * Signbturf: (J[B)[B
 * Pbrbmftfrmbpping:                    *PKCS11*
 * @pbrbm   jlong jSfssionHbndlf        CK_SESSION_HANDLE iSfssion
 * @pbrbm   jbytfArrby jPbrt            CK_BYTE_PTR pPbrt
 *                                      CK_ULONG ulPbrtLfn
 * @rfturn  jbytfArrby jEndryptfdPbrt   CK_BYTE_PTR pEndryptfdPbrt
 *                                      CK_ULONG_PTR pulEndryptfdPbrtLfn
 */
JNIEXPORT jbytfArrby JNICALL Jbvb_sun_sfdurity_pkds11_wrbppfr_PKCS11_C_1SignEndryptUpdbtf
    (JNIEnv *fnv, jobjfdt obj, jlong jSfssionHbndlf, jbytfArrby jPbrt)
{
    CK_SESSION_HANDLE dkSfssionHbndlf;
    CK_BYTE_PTR dkpPbrt = NULL_PTR, dkpEndryptfdPbrt;
    CK_ULONG dkPbrtLfngti, dkEndryptfdPbrtLfngti = 0;
    jbytfArrby jEndryptfdPbrt = NULL;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR dkpFundtions = gftFundtionList(fnv, obj);
    if (dkpFundtions == NULL) { rfturn NULL; }

    dkSfssionHbndlf = jLongToCKULong(jSfssionHbndlf);
    jBytfArrbyToCKBytfArrby(fnv, jPbrt, &dkpPbrt, &dkPbrtLfngti);
    if ((*fnv)->ExdfptionCifdk(fnv)) { rfturn NULL; }

    rv = (*dkpFundtions->C_SignEndryptUpdbtf)(dkSfssionHbndlf, dkpPbrt, dkPbrtLfngti, NULL_PTR, &dkEndryptfdPbrtLfngti);
    if (dkAssfrtRfturnVblufOK(fnv, rv) != CK_ASSERT_OK) {
        frff(dkpPbrt);
        rfturn NULL;
    }

    dkpEndryptfdPbrt = (CK_BYTE_PTR) mbllod(dkEndryptfdPbrtLfngti * sizfof(CK_BYTE));
    if (dkpEndryptfdPbrt == NULL) {
        frff(dkpPbrt);
        tirowOutOfMfmoryError(fnv, 0);
        rfturn NULL;
    }

    rv = (*dkpFundtions->C_SignEndryptUpdbtf)(dkSfssionHbndlf, dkpPbrt, dkPbrtLfngti, dkpEndryptfdPbrt, &dkEndryptfdPbrtLfngti);
    if (dkAssfrtRfturnVblufOK(fnv, rv) == CK_ASSERT_OK) {
        jEndryptfdPbrt = dkBytfArrbyToJBytfArrby(fnv, dkpEndryptfdPbrt, dkEndryptfdPbrtLfngti);
    }
    frff(dkpPbrt);
    frff(dkpEndryptfdPbrt);

    rfturn jEndryptfdPbrt ;
}
#fndif

#ifdff P11_ENABLE_C_DECRYPTVERIFYUPDATE
/*
 * Clbss:     sun_sfdurity_pkds11_wrbppfr_PKCS11
 * Mftiod:    C_DfdryptVfrifyUpdbtf
 * Signbturf: (J[B)[B
 * Pbrbmftfrmbpping:                    *PKCS11*
 * @pbrbm   jlong jSfssionHbndlf        CK_SESSION_HANDLE iSfssion
 * @pbrbm   jbytfArrby jEndryptfdPbrt   CK_BYTE_PTR pEndryptfdPbrt
 *                                      CK_ULONG ulEndryptfdPbrtLfn
 * @rfturn  jbytfArrby jPbrt            CK_BYTE_PTR pPbrt
 *                                      CK_ULONG_PTR pulPbrtLfn
 */
JNIEXPORT jbytfArrby JNICALL Jbvb_sun_sfdurity_pkds11_wrbppfr_PKCS11_C_1DfdryptVfrifyUpdbtf
    (JNIEnv *fnv, jobjfdt obj, jlong jSfssionHbndlf, jbytfArrby jEndryptfdPbrt)
{
    CK_SESSION_HANDLE dkSfssionHbndlf;
    CK_BYTE_PTR dkpPbrt, dkpEndryptfdPbrt = NULL_PTR;
    CK_ULONG dkPbrtLfngti = 0, dkEndryptfdPbrtLfngti;
    jbytfArrby jPbrt = NULL;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR dkpFundtions = gftFundtionList(fnv, obj);
    if (dkpFundtions == NULL) { rfturn NULL; }

    dkSfssionHbndlf = jLongToCKULong(jSfssionHbndlf);
    jBytfArrbyToCKBytfArrby(fnv, jEndryptfdPbrt, &dkpEndryptfdPbrt, &dkEndryptfdPbrtLfngti);
    if ((*fnv)->ExdfptionCifdk(fnv)) { rfturn NULL; }

    rv = (*dkpFundtions->C_DfdryptVfrifyUpdbtf)(dkSfssionHbndlf, dkpEndryptfdPbrt, dkEndryptfdPbrtLfngti, NULL_PTR, &dkPbrtLfngti);
    if (dkAssfrtRfturnVblufOK(fnv, rv) != CK_ASSERT_OK) {
        frff(dkpEndryptfdPbrt);
        rfturn NULL;
    }

    dkpPbrt = (CK_BYTE_PTR) mbllod(dkPbrtLfngti * sizfof(CK_BYTE));
    if (dkpPbrt == NULL) {
        frff(dkpEndryptfdPbrt);
        tirowOutOfMfmoryError(fnv, 0);
        rfturn NULL;
    }

    rv = (*dkpFundtions->C_DfdryptVfrifyUpdbtf)(dkSfssionHbndlf, dkpEndryptfdPbrt, dkEndryptfdPbrtLfngti, dkpPbrt, &dkPbrtLfngti);

    if (dkAssfrtRfturnVblufOK(fnv, rv) == CK_ASSERT_OK) {
        jPbrt = dkBytfArrbyToJBytfArrby(fnv, dkpPbrt, dkPbrtLfngti);
    }
    frff(dkpEndryptfdPbrt);
    frff(dkpPbrt);

    rfturn jPbrt ;
}
#fndif

#ifdff P11_ENABLE_C_GETFUNCTIONSTATUS
/*
 * Clbss:     sun_sfdurity_pkds11_wrbppfr_PKCS11
 * Mftiod:    C_GftFundtionStbtus
 * Signbturf: (J)V
 * Pbrbmftfrmbpping:                    *PKCS11*
 * @pbrbm   jlong jSfssionHbndlf        CK_SESSION_HANDLE iSfssion
 */
JNIEXPORT void JNICALL Jbvb_sun_sfdurity_pkds11_wrbppfr_PKCS11_C_1GftFundtionStbtus
    (JNIEnv *fnv, jobjfdt obj, jlong jSfssionHbndlf)
{
    CK_SESSION_HANDLE dkSfssionHbndlf;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR dkpFundtions = gftFundtionList(fnv, obj);
    if (dkpFundtions == NULL) { rfturn; }

    dkSfssionHbndlf = jLongToCKULong(jSfssionHbndlf);

    /* C_GftFundtionStbtus siould blwbys rfturn CKR_FUNCTION_NOT_PARALLEL */
    rv = (*dkpFundtions->C_GftFundtionStbtus)(dkSfssionHbndlf);
    if (dkAssfrtRfturnVblufOK(fnv, rv) != CK_ASSERT_OK) { rfturn; }
}
#fndif

#ifdff P11_ENABLE_C_CANCELFUNCTION
/*
 * Clbss:     sun_sfdurity_pkds11_wrbppfr_PKCS11
 * Mftiod:    C_CbndflFundtion
 * Signbturf: (J)V
 * Pbrbmftfrmbpping:                    *PKCS11*
 * @pbrbm   jlong jSfssionHbndlf        CK_SESSION_HANDLE iSfssion
 */
JNIEXPORT void JNICALL Jbvb_sun_sfdurity_pkds11_wrbppfr_PKCS11_C_1CbndflFundtion
    (JNIEnv *fnv, jobjfdt obj, jlong jSfssionHbndlf)
{
    CK_SESSION_HANDLE dkSfssionHbndlf;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR dkpFundtions = gftFundtionList(fnv, obj);
    if (dkpFundtions == NULL) { rfturn; }

    dkSfssionHbndlf = jLongToCKULong(jSfssionHbndlf);

    /* C_GftFundtionStbtus siould blwbys rfturn CKR_FUNCTION_NOT_PARALLEL */
    rv = (*dkpFundtions->C_CbndflFundtion)(dkSfssionHbndlf);
    if (dkAssfrtRfturnVblufOK(fnv, rv) != CK_ASSERT_OK) { rfturn; }
}
#fndif
