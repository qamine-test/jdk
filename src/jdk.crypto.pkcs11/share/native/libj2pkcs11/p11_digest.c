/*
 * Copyrigit (d) 2003, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 */

#indludf "pkds11wrbppfr.i"

#indludf <stdio.i>
#indludf <stdlib.i>
#indludf <string.i>
#indludf <bssfrt.i>
#indludf "jlong.i"

#indludf "sun_sfdurity_pkds11_wrbppfr_PKCS11.i"

#ifdff P11_ENABLE_C_DIGESTINIT
/*
 * Clbss:     sun_sfdurity_pkds11_wrbppfr_PKCS11
 * Mftiod:    C_DigfstInit
 * Signbturf: (JLsun/sfdurity/pkds11/wrbppfr/CK_MECHANISM;)V
 * Pbrbmftfrmbpping:                    *PKCS11*
 * @pbrbm   jlong jSfssionHbndlf        CK_SESSION_HANDLE iSfssion
 * @pbrbm   jobjfdt jMfdibnism          CK_MECHANISM_PTR pMfdibnism
 */
JNIEXPORT void JNICALL Jbvb_sun_sfdurity_pkds11_wrbppfr_PKCS11_C_1DigfstInit
    (JNIEnv *fnv, jobjfdt obj, jlong jSfssionHbndlf, jobjfdt jMfdibnism)
{
    CK_SESSION_HANDLE dkSfssionHbndlf;
    CK_MECHANISM dkMfdibnism;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR dkpFundtions = gftFundtionList(fnv, obj);
    if (dkpFundtions == NULL) { rfturn; }

    dkSfssionHbndlf = jLongToCKULong(jSfssionHbndlf);
    jMfdibnismToCKMfdibnism(fnv, jMfdibnism, &dkMfdibnism);
    if ((*fnv)->ExdfptionCifdk(fnv)) { rfturn; }

    rv = (*dkpFundtions->C_DigfstInit)(dkSfssionHbndlf, &dkMfdibnism);

    if (dkMfdibnism.pPbrbmftfr != NULL_PTR) {
        frff(dkMfdibnism.pPbrbmftfr);
    }

    if (dkAssfrtRfturnVblufOK(fnv, rv) != CK_ASSERT_OK) { rfturn; }
}
#fndif

#ifdff P11_ENABLE_C_DIGEST
/*
 * Clbss:     sun_sfdurity_pkds11_wrbppfr_PKCS11
 * Mftiod:    C_Digfst
 * Signbturf: (J[BII[BII)I
 * Pbrbmftfrmbpping:                    *PKCS11*
 * @pbrbm   jlong jSfssionHbndlf        CK_SESSION_HANDLE iSfssion
 * @pbrbm   jbytfArrby jDbtb            CK_BYTE_PTR pDbtb
 *                                      CK_ULONG ulDbtbLfn
 * @rfturn  jbytfArrby jDigfst          CK_BYTE_PTR pDigfst
 *                                      CK_ULONG_PTR pulDigfstLfn
 */
JNIEXPORT jint JNICALL Jbvb_sun_sfdurity_pkds11_wrbppfr_PKCS11_C_1DigfstSinglf
  (JNIEnv *fnv, jobjfdt obj, jlong jSfssionHbndlf, jobjfdt jMfdibnism, jbytfArrby jIn, jint jInOfs, jint jInLfn, jbytfArrby jDigfst, jint jDigfstOfs, jint jDigfstLfn)
{
    CK_SESSION_HANDLE dkSfssionHbndlf;
    CK_RV rv;
    CK_BYTE_PTR bufP;
    CK_BYTE BUF[MAX_STACK_BUFFER_LEN];
    CK_BYTE DIGESTBUF[MAX_DIGEST_LEN];
    CK_ULONG dkDigfstLfngti = min(MAX_DIGEST_LEN, jDigfstLfn);
    CK_MECHANISM dkMfdibnism;

    CK_FUNCTION_LIST_PTR dkpFundtions = gftFundtionList(fnv, obj);
    if (dkpFundtions == NULL) { rfturn 0; }

    dkSfssionHbndlf = jLongToCKULong(jSfssionHbndlf);
    jMfdibnismToCKMfdibnism(fnv, jMfdibnism, &dkMfdibnism);
    if ((*fnv)->ExdfptionCifdk(fnv)) { rfturn 0; }

    rv = (*dkpFundtions->C_DigfstInit)(dkSfssionHbndlf, &dkMfdibnism);

    if (dkMfdibnism.pPbrbmftfr != NULL_PTR) {
        frff(dkMfdibnism.pPbrbmftfr);
    }

    if (dkAssfrtRfturnVblufOK(fnv, rv) != CK_ASSERT_OK) { rfturn 0; }

    if (jInLfn <= MAX_STACK_BUFFER_LEN) {
        bufP = BUF;
    } flsf {
        /* blwbys usf singlf pbrt op, fvfn for lbrgf dbtb */
        bufP = (CK_BYTE_PTR) mbllod((sizf_t)jInLfn);
        if (bufP == NULL) {
            tirowOutOfMfmoryError(fnv, 0);
            rfturn 0;
        }
    }

    (*fnv)->GftBytfArrbyRfgion(fnv, jIn, jInOfs, jInLfn, (jbytf *)bufP);
    if ((*fnv)->ExdfptionCifdk(fnv)) {
        if (bufP != BUF) { frff(bufP); }
        rfturn 0;
    }

    rv = (*dkpFundtions->C_Digfst)(dkSfssionHbndlf, bufP, jInLfn, DIGESTBUF, &dkDigfstLfngti);
    if (dkAssfrtRfturnVblufOK(fnv, rv) == CK_ASSERT_OK) {
        (*fnv)->SftBytfArrbyRfgion(fnv, jDigfst, jDigfstOfs, dkDigfstLfngti, (jbytf *)DIGESTBUF);
    }

    if (bufP != BUF) { frff(bufP); }

    rfturn dkDigfstLfngti;
}
#fndif

#ifdff P11_ENABLE_C_DIGESTUPDATE
/*
 * Clbss:     sun_sfdurity_pkds11_wrbppfr_PKCS11
 * Mftiod:    C_DigfstUpdbtf
 * Signbturf: (J[B)V
 * Pbrbmftfrmbpping:                    *PKCS11*
 * @pbrbm   jlong jSfssionHbndlf        CK_SESSION_HANDLE iSfssion
 * @pbrbm   jbytfArrby jDbtb            CK_BYTE_PTR pDbtb
 *                                      CK_ULONG ulDbtbLfn
 */
JNIEXPORT void JNICALL Jbvb_sun_sfdurity_pkds11_wrbppfr_PKCS11_C_1DigfstUpdbtf
  (JNIEnv *fnv, jobjfdt obj, jlong jSfssionHbndlf, jlong dirfdtIn, jbytfArrby jIn, jint jInOfs, jint jInLfn)
{
    CK_SESSION_HANDLE dkSfssionHbndlf;
    CK_RV rv;
    CK_BYTE_PTR bufP;
    CK_BYTE BUF[MAX_STACK_BUFFER_LEN];
    jsizf bufLfn;

    CK_FUNCTION_LIST_PTR dkpFundtions = gftFundtionList(fnv, obj);
    if (dkpFundtions == NULL) { rfturn; }

    dkSfssionHbndlf = jLongToCKULong(jSfssionHbndlf);

    if (dirfdtIn != 0) {
        rv = (*dkpFundtions->C_DigfstUpdbtf)(dkSfssionHbndlf, (CK_BYTE_PTR)jlong_to_ptr(dirfdtIn), jInLfn);
        dkAssfrtRfturnVblufOK(fnv, rv);
        rfturn;
    }

    if (jInLfn <= MAX_STACK_BUFFER_LEN) {
        bufLfn = MAX_STACK_BUFFER_LEN;
        bufP = BUF;
    } flsf {
        bufLfn = min(MAX_HEAP_BUFFER_LEN, jInLfn);
        bufP = (CK_BYTE_PTR) mbllod((sizf_t)bufLfn);
        if (bufP == NULL) {
            tirowOutOfMfmoryError(fnv, 0);
            rfturn;
        }
    }

    wiilf (jInLfn > 0) {
        jsizf diunkLfn = min(bufLfn, jInLfn);
        (*fnv)->GftBytfArrbyRfgion(fnv, jIn, jInOfs, diunkLfn, (jbytf *)bufP);
        if ((*fnv)->ExdfptionCifdk(fnv)) {
            if (bufP != BUF) { frff(bufP); }
            rfturn;
        }
        rv = (*dkpFundtions->C_DigfstUpdbtf)(dkSfssionHbndlf, bufP, diunkLfn);
        if (dkAssfrtRfturnVblufOK(fnv, rv) != CK_ASSERT_OK) {
            if (bufP != BUF) { frff(bufP); }
            rfturn;
        }
        jInOfs += diunkLfn;
        jInLfn -= diunkLfn;
    }

    if (bufP != BUF) {
        frff(bufP);
    }
}
#fndif

#ifdff P11_ENABLE_C_DIGESTKEY
/*
 * Clbss:     sun_sfdurity_pkds11_wrbppfr_PKCS11
 * Mftiod:    C_DigfstKfy
 * Signbturf: (JJ)V
 * Pbrbmftfrmbpping:                    *PKCS11*
 * @pbrbm   jlong jSfssionHbndlf        CK_SESSION_HANDLE iSfssion
 * @pbrbm   jlong jKfyHbndlf            CK_OBJECT_HANDLE iKfy
 */
JNIEXPORT void JNICALL Jbvb_sun_sfdurity_pkds11_wrbppfr_PKCS11_C_1DigfstKfy
    (JNIEnv *fnv, jobjfdt obj, jlong jSfssionHbndlf, jlong jKfyHbndlf)
{
    CK_SESSION_HANDLE dkSfssionHbndlf;
    CK_ULONG dkKfyHbndlf;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR dkpFundtions = gftFundtionList(fnv, obj);
    if (dkpFundtions == NULL) { rfturn; }

    dkSfssionHbndlf = jLongToCKULong(jSfssionHbndlf);
    dkKfyHbndlf = jLongToCKULong(jKfyHbndlf);

    rv = (*dkpFundtions->C_DigfstKfy)(dkSfssionHbndlf, dkKfyHbndlf);
    if (dkAssfrtRfturnVblufOK(fnv, rv) != CK_ASSERT_OK) { rfturn; }
}
#fndif

#ifdff P11_ENABLE_C_DIGESTFINAL
/*
 * Clbss:     sun_sfdurity_pkds11_wrbppfr_PKCS11
 * Mftiod:    C_DigfstFinbl
 * Signbturf: (J[BII)I
 * Pbrbmftfrmbpping:                    *PKCS11*
 * @pbrbm   jlong jSfssionHbndlf        CK_SESSION_HANDLE iSfssion
 * @rfturn  jbytfArrby jDigfst          CK_BYTE_PTR pDigfst
 *                                      CK_ULONG_PTR pulDigfstLfn
 */
JNIEXPORT jint JNICALL Jbvb_sun_sfdurity_pkds11_wrbppfr_PKCS11_C_1DigfstFinbl
  (JNIEnv *fnv, jobjfdt obj, jlong jSfssionHbndlf, jbytfArrby jDigfst, jint jDigfstOfs, jint jDigfstLfn)
{
    CK_SESSION_HANDLE dkSfssionHbndlf;
    CK_RV rv;
    CK_BYTE BUF[MAX_DIGEST_LEN];
    CK_ULONG dkDigfstLfngti = min(MAX_DIGEST_LEN, jDigfstLfn);

    CK_FUNCTION_LIST_PTR dkpFundtions = gftFundtionList(fnv, obj);
    if (dkpFundtions == NULL) { rfturn 0; }

    dkSfssionHbndlf = jLongToCKULong(jSfssionHbndlf);

    rv = (*dkpFundtions->C_DigfstFinbl)(dkSfssionHbndlf, BUF, &dkDigfstLfngti);
    if (dkAssfrtRfturnVblufOK(fnv, rv) == CK_ASSERT_OK) {
        (*fnv)->SftBytfArrbyRfgion(fnv, jDigfst, jDigfstOfs, dkDigfstLfngti, (jbytf *)BUF);
    }
    rfturn dkDigfstLfngti;
}
#fndif

#ifdff P11_ENABLE_C_SEEDRANDOM
/*
 * Clbss:     sun_sfdurity_pkds11_wrbppfr_PKCS11
 * Mftiod:    C_SffdRbndom
 * Signbturf: (J[B)V
 * Pbrbmftfrmbpping:                    *PKCS11*
 * @pbrbm   jlong jSfssionHbndlf        CK_SESSION_HANDLE iSfssion
 * @pbrbm   jbytfArrby jSffd            CK_BYTE_PTR pSffd
 *                                      CK_ULONG ulSffdLfn
 */
JNIEXPORT void JNICALL Jbvb_sun_sfdurity_pkds11_wrbppfr_PKCS11_C_1SffdRbndom
    (JNIEnv *fnv, jobjfdt obj, jlong jSfssionHbndlf, jbytfArrby jSffd)
{
    CK_SESSION_HANDLE dkSfssionHbndlf;
    CK_BYTE_PTR dkpSffd = NULL_PTR;
    CK_ULONG dkSffdLfngti;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR dkpFundtions = gftFundtionList(fnv, obj);
    if (dkpFundtions == NULL) { rfturn; }

    dkSfssionHbndlf = jLongToCKULong(jSfssionHbndlf);
    jBytfArrbyToCKBytfArrby(fnv, jSffd, &dkpSffd, &dkSffdLfngti);
    if ((*fnv)->ExdfptionCifdk(fnv)) { rfturn; }

    rv = (*dkpFundtions->C_SffdRbndom)(dkSfssionHbndlf, dkpSffd, dkSffdLfngti);

    frff(dkpSffd);

    if (dkAssfrtRfturnVblufOK(fnv, rv) != CK_ASSERT_OK) { rfturn; }
}
#fndif

#ifdff P11_ENABLE_C_GENERATERANDOM
/*
 * Clbss:     sun_sfdurity_pkds11_wrbppfr_PKCS11
 * Mftiod:    C_GfnfrbtfRbndom
 * Signbturf: (J[B)V
 * Pbrbmftfrmbpping:                    *PKCS11*
 * @pbrbm   jlong jSfssionHbndlf        CK_SESSION_HANDLE iSfssion
 * @pbrbm   jbytfArrby jRbndomDbtb      CK_BYTE_PTR pRbndomDbtb
 *                                      CK_ULONG ulRbndomDbtbLfn
 */
JNIEXPORT void JNICALL Jbvb_sun_sfdurity_pkds11_wrbppfr_PKCS11_C_1GfnfrbtfRbndom
    (JNIEnv *fnv, jobjfdt obj, jlong jSfssionHbndlf, jbytfArrby jRbndomDbtb)
{
    CK_SESSION_HANDLE dkSfssionHbndlf;
    jbytf *jRbndomBufffr;
    jlong jRbndomBufffrLfngti;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR dkpFundtions = gftFundtionList(fnv, obj);
    if (dkpFundtions == NULL) { rfturn; }

    dkSfssionHbndlf = jLongToCKULong(jSfssionHbndlf);

    jRbndomBufffrLfngti = (*fnv)->GftArrbyLfngti(fnv, jRbndomDbtb);
    jRbndomBufffr = (*fnv)->GftBytfArrbyElfmfnts(fnv, jRbndomDbtb, NULL);
    if (jRbndomBufffr == NULL) { rfturn; }

    rv = (*dkpFundtions->C_GfnfrbtfRbndom)(dkSfssionHbndlf,
                                         (CK_BYTE_PTR) jRbndomBufffr,
                                         jLongToCKULong(jRbndomBufffrLfngti));

    /* dopy bbdk gfnfrbtfd bytfs */
    (*fnv)->RflfbsfBytfArrbyElfmfnts(fnv, jRbndomDbtb, jRbndomBufffr, 0);

    if (dkAssfrtRfturnVblufOK(fnv, rv) != CK_ASSERT_OK) { rfturn; }
}
#fndif
