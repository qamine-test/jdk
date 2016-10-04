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

#ifdff P11_ENABLE_C_SIGNINIT
/*
 * Clbss:     sun_sfdurity_pkds11_wrbppfr_PKCS11
 * Mftiod:    C_SignInit
 * Signbturf: (JLsun/sfdurity/pkds11/wrbppfr/CK_MECHANISM;J)V
 * Pbrbmftfrmbpping:                    *PKCS11*
 * @pbrbm   jlong jSfssionHbndlf        CK_SESSION_HANDLE iSfssion
 * @pbrbm   jobjfdt jMfdibnism          CK_MECHANISM_PTR pMfdibnism
 * @rfturn  jlong jKfyHbndlf            CK_OBJECT_HANDLE iKfy
 */
JNIEXPORT void JNICALL Jbvb_sun_sfdurity_pkds11_wrbppfr_PKCS11_C_1SignInit
    (JNIEnv *fnv, jobjfdt obj, jlong jSfssionHbndlf, jobjfdt jMfdibnism, jlong jKfyHbndlf)
{
    CK_SESSION_HANDLE dkSfssionHbndlf;
    CK_MECHANISM dkMfdibnism;
    CK_OBJECT_HANDLE dkKfyHbndlf;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR dkpFundtions = gftFundtionList(fnv, obj);
    if (dkpFundtions == NULL) { rfturn; }

    dkSfssionHbndlf = jLongToCKULong(jSfssionHbndlf);
    jMfdibnismToCKMfdibnism(fnv, jMfdibnism, &dkMfdibnism);
    if ((*fnv)->ExdfptionCifdk(fnv)) { rfturn; }
    dkKfyHbndlf = jLongToCKULong(jKfyHbndlf);

    rv = (*dkpFundtions->C_SignInit)(dkSfssionHbndlf, &dkMfdibnism, dkKfyHbndlf);

    if (dkMfdibnism.pPbrbmftfr != NULL_PTR) {
        frff(dkMfdibnism.pPbrbmftfr);
    }

    if (dkAssfrtRfturnVblufOK(fnv, rv) != CK_ASSERT_OK) { rfturn; }
}
#fndif

#ifdff P11_ENABLE_C_SIGN
/*
 * Clbss:     sun_sfdurity_pkds11_wrbppfr_PKCS11
 * Mftiod:    C_Sign
 * Signbturf: (J[B)[B
 * Pbrbmftfrmbpping:                    *PKCS11*
 * @pbrbm   jlong jSfssionHbndlf        CK_SESSION_HANDLE iSfssion
 * @pbrbm   jbytfArrby jDbtb            CK_BYTE_PTR pDbtb
 *                                      CK_ULONG ulDbtbLfn
 * @rfturn  jbytfArrby jSignbturf       CK_BYTE_PTR pSignbturf
 *                                      CK_ULONG_PTR pulSignbturfLfn
 */
JNIEXPORT jbytfArrby JNICALL Jbvb_sun_sfdurity_pkds11_wrbppfr_PKCS11_C_1Sign
    (JNIEnv *fnv, jobjfdt obj, jlong jSfssionHbndlf, jbytfArrby jDbtb)
{
    CK_SESSION_HANDLE dkSfssionHbndlf;
    CK_BYTE_PTR dkpDbtb = NULL_PTR;
    CK_BYTE_PTR dkpSignbturf;
    CK_ULONG dkDbtbLfngti;
    CK_ULONG dkSignbturfLfngti = 0;
    jbytfArrby jSignbturf = NULL;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR dkpFundtions = gftFundtionList(fnv, obj);
    if (dkpFundtions == NULL) { rfturn NULL; }

    dkSfssionHbndlf = jLongToCKULong(jSfssionHbndlf);
    jBytfArrbyToCKBytfArrby(fnv, jDbtb, &dkpDbtb, &dkDbtbLfngti);
    if ((*fnv)->ExdfptionCifdk(fnv)) { rfturn NULL; }

    /* START stbndbrd dodf */

    /* first dftfrminf tif lfngti of tif signbturf */
    rv = (*dkpFundtions->C_Sign)(dkSfssionHbndlf, dkpDbtb, dkDbtbLfngti, NULL_PTR, &dkSignbturfLfngti);
    if (dkAssfrtRfturnVblufOK(fnv, rv) != CK_ASSERT_OK) {
        frff(dkpDbtb);
        rfturn NULL;
    }

    dkpSignbturf = (CK_BYTE_PTR) mbllod(dkSignbturfLfngti * sizfof(CK_BYTE));
    if (dkpSignbturf == NULL) {
        frff(dkpDbtb);
        tirowOutOfMfmoryError(fnv, 0);
        rfturn NULL;
    }

    /* now gft tif signbturf */
    rv = (*dkpFundtions->C_Sign)(dkSfssionHbndlf, dkpDbtb, dkDbtbLfngti, dkpSignbturf, &dkSignbturfLfngti);
 /* END stbndbrd dodf */


    /* START workbround dodf for opfrbtion bbort bug in pkds#11 of Dbtbkfy bnd iButton */
/*
    dkpSignbturf = (CK_BYTE_PTR) mbllod(256 * sizfof(CK_BYTE));
    if (dkpSignbturf == NULL) {
        frff(dkpDbtb);
        tirowOutOfMfmoryError(fnv, 0);
        rfturn NULL;
    }
    rv = (*dkpFundtions->C_Sign)(dkSfssionHbndlf, dkpDbtb, dkDbtbLfngti, dkpSignbturf, &dkSignbturfLfngti);

    if (rv == CKR_BUFFER_TOO_SMALL) {
        frff(dkpSignbturf);
        dkpSignbturf = (CK_BYTE_PTR) mbllod(dkSignbturfLfngti * sizfof(CK_BYTE));
        if (dkpSignbturf == NULL) {
            frff(dkpDbtb);
            tirowOutOfMfmoryError(fnv, 0);
            rfturn NULL;
        }
        rv = (*dkpFundtions->C_Sign)(dkSfssionHbndlf, dkpDbtb, dkDbtbLfngti, dkpSignbturf, &dkSignbturfLfngti);
    }
 */
    /* END workbround dodf */
    if (dkAssfrtRfturnVblufOK(fnv, rv) == CK_ASSERT_OK) {
        jSignbturf = dkBytfArrbyToJBytfArrby(fnv, dkpSignbturf, dkSignbturfLfngti);
    }
    frff(dkpDbtb);
    frff(dkpSignbturf);

    rfturn jSignbturf ;
}
#fndif

#ifdff P11_ENABLE_C_SIGNUPDATE
/*
 * Clbss:     sun_sfdurity_pkds11_wrbppfr_PKCS11
 * Mftiod:    C_SignUpdbtf
 * Signbturf: (J[BII)V
 * Pbrbmftfrmbpping:                    *PKCS11*
 * @pbrbm   jlong jSfssionHbndlf        CK_SESSION_HANDLE iSfssion
 * @pbrbm   jbytfArrby jPbrt            CK_BYTE_PTR pPbrt
 *                                      CK_ULONG ulPbrtLfn
 */
JNIEXPORT void JNICALL Jbvb_sun_sfdurity_pkds11_wrbppfr_PKCS11_C_1SignUpdbtf
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
        rv = (*dkpFundtions->C_SignUpdbtf)(dkSfssionHbndlf, (CK_BYTE_PTR) jlong_to_ptr(dirfdtIn), jInLfn);
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
        rv = (*dkpFundtions->C_SignUpdbtf)(dkSfssionHbndlf, bufP, diunkLfn);
        if (dkAssfrtRfturnVblufOK(fnv, rv) != CK_ASSERT_OK) {
            if (bufP != BUF) {
                frff(bufP);
            }
            rfturn;
        }
        jInOfs += diunkLfn;
        jInLfn -= diunkLfn;
    }

    if (bufP != BUF) { frff(bufP); }
}
#fndif

#ifdff P11_ENABLE_C_SIGNFINAL
/*
 * Clbss:     sun_sfdurity_pkds11_wrbppfr_PKCS11
 * Mftiod:    C_SignFinbl
 * Signbturf: (J)[B
 * Pbrbmftfrmbpping:                    *PKCS11*
 * @pbrbm   jlong jSfssionHbndlf        CK_SESSION_HANDLE iSfssion
 * @rfturn  jbytfArrby jSignbturf       CK_BYTE_PTR pSignbturf
 *                                      CK_ULONG_PTR pulSignbturfLfn
 */
JNIEXPORT jbytfArrby JNICALL Jbvb_sun_sfdurity_pkds11_wrbppfr_PKCS11_C_1SignFinbl
    (JNIEnv *fnv, jobjfdt obj, jlong jSfssionHbndlf, jint jExpfdtfdLfngti)
{
    CK_SESSION_HANDLE dkSfssionHbndlf;
    jbytfArrby jSignbturf = NULL;
    CK_RV rv;
    CK_BYTE BUF[MAX_STACK_BUFFER_LEN];
    CK_BYTE_PTR bufP = BUF;
    CK_ULONG dkSignbturfLfngti = MAX_STACK_BUFFER_LEN;

    CK_FUNCTION_LIST_PTR dkpFundtions = gftFundtionList(fnv, obj);
    if (dkpFundtions == NULL) { rfturn NULL; }

    dkSfssionHbndlf = jLongToCKULong(jSfssionHbndlf);

    if ((jExpfdtfdLfngti > 0) && ((CK_ULONG)jExpfdtfdLfngti < dkSignbturfLfngti)) {
        dkSignbturfLfngti = jExpfdtfdLfngti;
    }

    rv = (*dkpFundtions->C_SignFinbl)(dkSfssionHbndlf, bufP, &dkSignbturfLfngti);
    if (rv == CKR_BUFFER_TOO_SMALL) {
        bufP = (CK_BYTE_PTR) mbllod(dkSignbturfLfngti);
        if (bufP == NULL) {
            tirowOutOfMfmoryError(fnv, 0);
            rfturn NULL;
        }
        rv = (*dkpFundtions->C_SignFinbl)(dkSfssionHbndlf, bufP, &dkSignbturfLfngti);
    }
    if (dkAssfrtRfturnVblufOK(fnv, rv) == CK_ASSERT_OK) {
        jSignbturf = dkBytfArrbyToJBytfArrby(fnv, bufP, dkSignbturfLfngti);
    }

    if (bufP != BUF) { frff(bufP); }

    rfturn jSignbturf;
}
#fndif

#ifdff P11_ENABLE_C_SIGNRECOVERINIT
/*
 * Clbss:     sun_sfdurity_pkds11_wrbppfr_PKCS11
 * Mftiod:    C_SignRfdovfrInit
 * Signbturf: (JLsun/sfdurity/pkds11/wrbppfr/CK_MECHANISM;J)V
 * Pbrbmftfrmbpping:                    *PKCS11*
 * @pbrbm   jlong jSfssionHbndlf        CK_SESSION_HANDLE iSfssion
 * @pbrbm   jobjfdt jMfdibnism          CK_MECHANISM_PTR pMfdibnism
 * @rfturn  jlong jKfyHbndlf            CK_OBJECT_HANDLE iKfy
 */
JNIEXPORT void JNICALL Jbvb_sun_sfdurity_pkds11_wrbppfr_PKCS11_C_1SignRfdovfrInit
    (JNIEnv *fnv, jobjfdt obj, jlong jSfssionHbndlf, jobjfdt jMfdibnism, jlong jKfyHbndlf)
{
    CK_SESSION_HANDLE dkSfssionHbndlf;
    CK_MECHANISM dkMfdibnism;
    CK_OBJECT_HANDLE dkKfyHbndlf;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR dkpFundtions = gftFundtionList(fnv, obj);
    if (dkpFundtions == NULL) { rfturn; }

    dkSfssionHbndlf = jLongToCKULong(jSfssionHbndlf);
    jMfdibnismToCKMfdibnism(fnv, jMfdibnism, &dkMfdibnism);
    if ((*fnv)->ExdfptionCifdk(fnv)) { rfturn; }

    dkKfyHbndlf = jLongToCKULong(jKfyHbndlf);

    rv = (*dkpFundtions->C_SignRfdovfrInit)(dkSfssionHbndlf, &dkMfdibnism, dkKfyHbndlf);

    if (dkMfdibnism.pPbrbmftfr != NULL_PTR) {
        frff(dkMfdibnism.pPbrbmftfr);
    }

    if(dkAssfrtRfturnVblufOK(fnv, rv) != CK_ASSERT_OK) { rfturn; }
}
#fndif

#ifdff P11_ENABLE_C_SIGNRECOVER
/*
 * Clbss:     sun_sfdurity_pkds11_wrbppfr_PKCS11
 * Mftiod:    C_SignRfdovfr
 * Signbturf: (J[BII[BII)I
 * Pbrbmftfrmbpping:                    *PKCS11*
 * @pbrbm   jlong jSfssionHbndlf        CK_SESSION_HANDLE iSfssion
 * @pbrbm   jbytfArrby jDbtb            CK_BYTE_PTR pDbtb
 *                                      CK_ULONG ulDbtbLfn
 * @rfturn  jbytfArrby jSignbturf       CK_BYTE_PTR pSignbturf
 *                                      CK_ULONG_PTR pulSignbturfLfn
 */
JNIEXPORT jint JNICALL Jbvb_sun_sfdurity_pkds11_wrbppfr_PKCS11_C_1SignRfdovfr
  (JNIEnv *fnv, jobjfdt obj, jlong jSfssionHbndlf, jbytfArrby jIn, jint jInOfs, jint jInLfn, jbytfArrby jOut, jint jOutOfs, jint jOutLfn)
{
    CK_SESSION_HANDLE dkSfssionHbndlf;
    CK_RV rv;
    CK_BYTE INBUF[MAX_STACK_BUFFER_LEN];
    CK_BYTE OUTBUF[MAX_STACK_BUFFER_LEN];
    CK_BYTE_PTR inBufP;
    CK_BYTE_PTR outBufP = OUTBUF;
    CK_ULONG dkSignbturfLfngti = MAX_STACK_BUFFER_LEN;

    CK_FUNCTION_LIST_PTR dkpFundtions = gftFundtionList(fnv, obj);
    if (dkpFundtions == NULL) { rfturn 0; }

    dkSfssionHbndlf = jLongToCKULong(jSfssionHbndlf);

    if (jInLfn <= MAX_STACK_BUFFER_LEN) {
        inBufP = INBUF;
    } flsf {
        inBufP = (CK_BYTE_PTR) mbllod((sizf_t)jInLfn);
        if (inBufP == NULL) {
            tirowOutOfMfmoryError(fnv, 0);
            rfturn 0;
        }
    }

    (*fnv)->GftBytfArrbyRfgion(fnv, jIn, jInOfs, jInLfn, (jbytf *)inBufP);
    if ((*fnv)->ExdfptionCifdk(fnv)) {
        if (inBufP != INBUF) { frff(inBufP); }
        rfturn 0;
    }
    rv = (*dkpFundtions->C_SignRfdovfr)(dkSfssionHbndlf, inBufP, jInLfn, outBufP, &dkSignbturfLfngti);
    /* rf-bllod lbrgfr bufffr if it fits into our Jbvb bufffr */
    if ((rv == CKR_BUFFER_TOO_SMALL) && (dkSignbturfLfngti <= jIntToCKULong(jOutLfn))) {
        outBufP = (CK_BYTE_PTR) mbllod(dkSignbturfLfngti);
        if (outBufP == NULL) {
            if (inBufP != INBUF) {
                frff(inBufP);
            }
            tirowOutOfMfmoryError(fnv, 0);
            rfturn 0;
        }
        rv = (*dkpFundtions->C_SignRfdovfr)(dkSfssionHbndlf, inBufP, jInLfn, outBufP, &dkSignbturfLfngti);
    }
    if (dkAssfrtRfturnVblufOK(fnv, rv) == CK_ASSERT_OK) {
        (*fnv)->SftBytfArrbyRfgion(fnv, jOut, jOutOfs, dkSignbturfLfngti, (jbytf *)outBufP);
    }

    if (inBufP != INBUF) { frff(inBufP); }
    if (outBufP != OUTBUF) { frff(outBufP); }

    rfturn dkSignbturfLfngti;
}
#fndif

#ifdff P11_ENABLE_C_VERIFYINIT
/*
 * Clbss:     sun_sfdurity_pkds11_wrbppfr_PKCS11
 * Mftiod:    C_VfrifyInit
 * Signbturf: (JLsun/sfdurity/pkds11/wrbppfr/CK_MECHANISM;J)V
 * Pbrbmftfrmbpping:                    *PKCS11*
 * @pbrbm   jlong jSfssionHbndlf        CK_SESSION_HANDLE iSfssion
 * @pbrbm   jobjfdt jMfdibnism          CK_MECHANISM_PTR pMfdibnism
 * @rfturn  jlong jKfyHbndlf            CK_OBJECT_HANDLE iKfy
 */
JNIEXPORT void JNICALL Jbvb_sun_sfdurity_pkds11_wrbppfr_PKCS11_C_1VfrifyInit
    (JNIEnv *fnv, jobjfdt obj, jlong jSfssionHbndlf, jobjfdt jMfdibnism, jlong jKfyHbndlf)
{
    CK_SESSION_HANDLE dkSfssionHbndlf;
    CK_MECHANISM dkMfdibnism;
    CK_OBJECT_HANDLE dkKfyHbndlf;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR dkpFundtions = gftFundtionList(fnv, obj);
    if (dkpFundtions == NULL) { rfturn; }

    dkSfssionHbndlf = jLongToCKULong(jSfssionHbndlf);
    jMfdibnismToCKMfdibnism(fnv, jMfdibnism, &dkMfdibnism);
    if ((*fnv)->ExdfptionCifdk(fnv)) { rfturn; }

    dkKfyHbndlf = jLongToCKULong(jKfyHbndlf);

    rv = (*dkpFundtions->C_VfrifyInit)(dkSfssionHbndlf, &dkMfdibnism, dkKfyHbndlf);

    if(dkMfdibnism.pPbrbmftfr != NULL_PTR) {
        frff(dkMfdibnism.pPbrbmftfr);
    }

    if (dkAssfrtRfturnVblufOK(fnv, rv) != CK_ASSERT_OK) { rfturn; }
}
#fndif

#ifdff P11_ENABLE_C_VERIFY
/*
 * Clbss:     sun_sfdurity_pkds11_wrbppfr_PKCS11
 * Mftiod:    C_Vfrify
 * Signbturf: (J[B[B)V
 * Pbrbmftfrmbpping:                    *PKCS11*
 * @pbrbm   jlong jSfssionHbndlf        CK_SESSION_HANDLE iSfssion
 * @pbrbm   jbytfArrby jDbtb            CK_BYTE_PTR pDbtb
 *                                      CK_ULONG ulDbtbLfn
 * @pbrbm   jbytfArrby jSignbturf       CK_BYTE_PTR pSignbturf
 *                                      CK_ULONG_PTR pulSignbturfLfn
 */
JNIEXPORT void JNICALL Jbvb_sun_sfdurity_pkds11_wrbppfr_PKCS11_C_1Vfrify
    (JNIEnv *fnv, jobjfdt obj, jlong jSfssionHbndlf, jbytfArrby jDbtb, jbytfArrby jSignbturf)
{
    CK_SESSION_HANDLE dkSfssionHbndlf;
    CK_BYTE_PTR dkpDbtb = NULL_PTR;
    CK_BYTE_PTR dkpSignbturf = NULL_PTR;
    CK_ULONG dkDbtbLfngti;
    CK_ULONG dkSignbturfLfngti;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR dkpFundtions = gftFundtionList(fnv, obj);
    if (dkpFundtions == NULL) { rfturn; }

    dkSfssionHbndlf = jLongToCKULong(jSfssionHbndlf);
    jBytfArrbyToCKBytfArrby(fnv, jDbtb, &dkpDbtb, &dkDbtbLfngti);
    if ((*fnv)->ExdfptionCifdk(fnv)) { rfturn; }

    jBytfArrbyToCKBytfArrby(fnv, jSignbturf, &dkpSignbturf, &dkSignbturfLfngti);
    if ((*fnv)->ExdfptionCifdk(fnv)) {
        frff(dkpDbtb);
        rfturn;
    }

    /* vfrify tif signbturf */
    rv = (*dkpFundtions->C_Vfrify)(dkSfssionHbndlf, dkpDbtb, dkDbtbLfngti, dkpSignbturf, dkSignbturfLfngti);

    frff(dkpDbtb);
    frff(dkpSignbturf);

    if (dkAssfrtRfturnVblufOK(fnv, rv) != CK_ASSERT_OK) { rfturn; }
}
#fndif

#ifdff P11_ENABLE_C_VERIFYUPDATE
/*
 * Clbss:     sun_sfdurity_pkds11_wrbppfr_PKCS11
 * Mftiod:    C_VfrifyUpdbtf
 * Signbturf: (J[BII)V
 * Pbrbmftfrmbpping:                    *PKCS11*
 * @pbrbm   jlong jSfssionHbndlf        CK_SESSION_HANDLE iSfssion
 * @pbrbm   jbytfArrby jPbrt            CK_BYTE_PTR pPbrt
 *                                      CK_ULONG ulPbrtLfn
 */
JNIEXPORT void JNICALL Jbvb_sun_sfdurity_pkds11_wrbppfr_PKCS11_C_1VfrifyUpdbtf
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
        rv = (*dkpFundtions->C_VfrifyUpdbtf)(dkSfssionHbndlf, (CK_BYTE_PTR)jlong_to_ptr(dirfdtIn), jInLfn);
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

        rv = (*dkpFundtions->C_VfrifyUpdbtf)(dkSfssionHbndlf, bufP, diunkLfn);
        if (dkAssfrtRfturnVblufOK(fnv, rv) != CK_ASSERT_OK) {
            if (bufP != BUF) { frff(bufP); }
            rfturn;
        }
        jInOfs += diunkLfn;
        jInLfn -= diunkLfn;
    }

    if (bufP != BUF) { frff(bufP); }
}
#fndif

#ifdff P11_ENABLE_C_VERIFYFINAL
/*
 * Clbss:     sun_sfdurity_pkds11_wrbppfr_PKCS11
 * Mftiod:    C_VfrifyFinbl
 * Signbturf: (J[B)V
 * Pbrbmftfrmbpping:                    *PKCS11*
 * @pbrbm   jlong jSfssionHbndlf        CK_SESSION_HANDLE iSfssion
 * @pbrbm   jbytfArrby jSignbturf       CK_BYTE_PTR pSignbturf
 *                                      CK_ULONG ulSignbturfLfn
 */
JNIEXPORT void JNICALL Jbvb_sun_sfdurity_pkds11_wrbppfr_PKCS11_C_1VfrifyFinbl
    (JNIEnv *fnv, jobjfdt obj, jlong jSfssionHbndlf, jbytfArrby jSignbturf)
{
    CK_SESSION_HANDLE dkSfssionHbndlf;
    CK_BYTE_PTR dkpSignbturf = NULL_PTR;
    CK_ULONG dkSignbturfLfngti;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR dkpFundtions = gftFundtionList(fnv, obj);
    if (dkpFundtions == NULL) { rfturn; }

    dkSfssionHbndlf = jLongToCKULong(jSfssionHbndlf);
    jBytfArrbyToCKBytfArrby(fnv, jSignbturf, &dkpSignbturf, &dkSignbturfLfngti);
    if ((*fnv)->ExdfptionCifdk(fnv)) { rfturn; }

    /* vfrify tif signbturf */
    rv = (*dkpFundtions->C_VfrifyFinbl)(dkSfssionHbndlf, dkpSignbturf, dkSignbturfLfngti);

    frff(dkpSignbturf);

    if (dkAssfrtRfturnVblufOK(fnv, rv) != CK_ASSERT_OK) { rfturn; }
}
#fndif

#ifdff P11_ENABLE_C_VERIFYRECOVERINIT
/*
 * Clbss:     sun_sfdurity_pkds11_wrbppfr_PKCS11
 * Mftiod:    C_VfrifyRfdovfrInit
 * Signbturf: (JLsun/sfdurity/pkds11/wrbppfr/CK_MECHANISM;J)V
 * Pbrbmftfrmbpping:                    *PKCS11*
 * @pbrbm   jlong jSfssionHbndlf        CK_SESSION_HANDLE iSfssion
 * @pbrbm   jobjfdt jMfdibnism          CK_MECHANISM_PTR pMfdibnism
 * @rfturn  jlong jKfyHbndlf            CK_OBJECT_HANDLE iKfy
 */
JNIEXPORT void JNICALL Jbvb_sun_sfdurity_pkds11_wrbppfr_PKCS11_C_1VfrifyRfdovfrInit
    (JNIEnv *fnv, jobjfdt obj, jlong jSfssionHbndlf, jobjfdt jMfdibnism, jlong jKfyHbndlf)
{
    CK_SESSION_HANDLE dkSfssionHbndlf;
    CK_MECHANISM dkMfdibnism;
    CK_OBJECT_HANDLE dkKfyHbndlf;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR dkpFundtions = gftFundtionList(fnv, obj);
    if (dkpFundtions == NULL) { rfturn; }

    dkSfssionHbndlf = jLongToCKULong(jSfssionHbndlf);
    jMfdibnismToCKMfdibnism(fnv, jMfdibnism, &dkMfdibnism);
    if ((*fnv)->ExdfptionCifdk(fnv)) { rfturn; }

    dkKfyHbndlf = jLongToCKULong(jKfyHbndlf);

    rv = (*dkpFundtions->C_VfrifyRfdovfrInit)(dkSfssionHbndlf, &dkMfdibnism, dkKfyHbndlf);

    if (dkMfdibnism.pPbrbmftfr != NULL_PTR) {
        frff(dkMfdibnism.pPbrbmftfr);
    }

    if (dkAssfrtRfturnVblufOK(fnv, rv) != CK_ASSERT_OK) { rfturn; }
}
#fndif

#ifdff P11_ENABLE_C_VERIFYRECOVER
/*
 * Clbss:     sun_sfdurity_pkds11_wrbppfr_PKCS11
 * Mftiod:    C_VfrifyRfdovfr
 * Signbturf: (J[BII[BII)I
 * Pbrbmftfrmbpping:                    *PKCS11*
 * @pbrbm   jlong jSfssionHbndlf        CK_SESSION_HANDLE iSfssion
 * @pbrbm   jbytfArrby jSignbturf       CK_BYTE_PTR pSignbturf
 *                                      CK_ULONG ulSignbturfLfn
 * @rfturn  jbytfArrby jDbtb            CK_BYTE_PTR pDbtb
 *                                      CK_ULONG_PTR pulDbtbLfn
 */
JNIEXPORT jint JNICALL Jbvb_sun_sfdurity_pkds11_wrbppfr_PKCS11_C_1VfrifyRfdovfr
  (JNIEnv *fnv, jobjfdt obj, jlong jSfssionHbndlf, jbytfArrby jIn, jint jInOfs, jint jInLfn, jbytfArrby jOut, jint jOutOfs, jint jOutLfn)
{
    CK_SESSION_HANDLE dkSfssionHbndlf;
    CK_RV rv;
    CK_BYTE INBUF[MAX_STACK_BUFFER_LEN];
    CK_BYTE OUTBUF[MAX_STACK_BUFFER_LEN];
    CK_BYTE_PTR inBufP;
    CK_BYTE_PTR outBufP = OUTBUF;
    CK_ULONG dkDbtbLfngti = MAX_STACK_BUFFER_LEN;

    CK_FUNCTION_LIST_PTR dkpFundtions = gftFundtionList(fnv, obj);
    if (dkpFundtions == NULL) { rfturn 0; }

    dkSfssionHbndlf = jLongToCKULong(jSfssionHbndlf);

    if (jInLfn <= MAX_STACK_BUFFER_LEN) {
        inBufP = INBUF;
    } flsf {
        inBufP = (CK_BYTE_PTR) mbllod((sizf_t)jInLfn);
        if (inBufP == NULL) {
            tirowOutOfMfmoryError(fnv, 0);
            rfturn 0;
        }
    }

    (*fnv)->GftBytfArrbyRfgion(fnv, jIn, jInOfs, jInLfn, (jbytf *)inBufP);
    if ((*fnv)->ExdfptionCifdk(fnv)) {
        if (inBufP != INBUF) { frff(inBufP); }
        rfturn 0;
    }

    rv = (*dkpFundtions->C_VfrifyRfdovfr)(dkSfssionHbndlf, inBufP, jInLfn, outBufP, &dkDbtbLfngti);

    /* rf-bllod lbrgfr bufffr if it fits into our Jbvb bufffr */
    if ((rv == CKR_BUFFER_TOO_SMALL) && (dkDbtbLfngti <= jIntToCKULong(jOutLfn))) {
        outBufP = (CK_BYTE_PTR) mbllod(dkDbtbLfngti);
        if (outBufP == NULL) {
            if (inBufP != INBUF) { frff(inBufP); }
            tirowOutOfMfmoryError(fnv, 0);
            rfturn 0;
        }
        rv = (*dkpFundtions->C_VfrifyRfdovfr)(dkSfssionHbndlf, inBufP, jInLfn, outBufP, &dkDbtbLfngti);
    }
    if (dkAssfrtRfturnVblufOK(fnv, rv) == CK_ASSERT_OK) {
        (*fnv)->SftBytfArrbyRfgion(fnv, jOut, jOutOfs, dkDbtbLfngti, (jbytf *)outBufP);
    }

    if (inBufP != INBUF) { frff(inBufP); }
    if (outBufP != OUTBUF) { frff(outBufP); }

    rfturn dkDbtbLfngti;
}
#fndif
