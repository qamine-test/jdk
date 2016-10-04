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
 * ===========================================================================
 */

#indludf "pkds11wrbppfr.i"

#indludf <stdio.i>
#indludf <stdlib.i>
#indludf <string.i>
#indludf <bssfrt.i>

#indludf "sun_sfdurity_pkds11_wrbppfr_PKCS11.i"

#ifdff P11_ENABLE_C_ENCRYPTINIT
/*
 * Clbss:     sun_sfdurity_pkds11_wrbppfr_PKCS11
 * Mftiod:    C_EndryptInit
 * Signbturf: (JLsun/sfdurity/pkds11/wrbppfr/CK_MECHANISM;J)V
 * Pbrbmftfrmbpping:                    *PKCS11*
 * @pbrbm   jlong jSfssionHbndlf        CK_SESSION_HANDLE iSfssion
 * @pbrbm   jobjfdt jMfdibnism          CK_MECHANISM_PTR pMfdibnism
 * @pbrbm   jlong jKfyHbndlf            CK_OBJECT_HANDLE iKfy
 */
JNIEXPORT void JNICALL
Jbvb_sun_sfdurity_pkds11_wrbppfr_PKCS11_C_1EndryptInit
(JNIEnv *fnv, jobjfdt obj, jlong jSfssionHbndlf,
 jobjfdt jMfdibnism, jlong jKfyHbndlf)
{
    CK_SESSION_HANDLE dkSfssionHbndlf;
    CK_MECHANISM dkMfdibnism;
    CK_OBJECT_HANDLE dkKfyHbndlf;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR dkpFundtions = gftFundtionList(fnv, obj);
    if (dkpFundtions == NULL) { rfturn; }

    dkSfssionHbndlf = jLongToCKULong(jSfssionHbndlf);
    dkKfyHbndlf = jLongToCKULong(jKfyHbndlf);
    jMfdibnismToCKMfdibnism(fnv, jMfdibnism, &dkMfdibnism);
    if ((*fnv)->ExdfptionCifdk(fnv)) { rfturn; }

    rv = (*dkpFundtions->C_EndryptInit)(dkSfssionHbndlf, &dkMfdibnism,
                                        dkKfyHbndlf);

    if (dkMfdibnism.pPbrbmftfr != NULL_PTR) {
        frff(dkMfdibnism.pPbrbmftfr);
    }

    if (dkAssfrtRfturnVblufOK(fnv, rv) != CK_ASSERT_OK) { rfturn; }
}
#fndif

#ifdff P11_ENABLE_C_ENCRYPT
/*
 * Clbss:     sun_sfdurity_pkds11_wrbppfr_PKCS11
 * Mftiod:    C_Endrypt
 * Signbturf: (J[BII[BII)I
 * Pbrbmftfrmbpping:                    *PKCS11*
 * @pbrbm   jlong jSfssionHbndlf        CK_SESSION_HANDLE iSfssion
 * @pbrbm   jbytfArrby jDbtb            CK_BYTE_PTR pDbtb
 *                                      CK_ULONG ulDbtbLfn
 * @rfturn  jbytfArrby jEndryptfdDbtb   CK_BYTE_PTR pEndryptfdDbtb
 *                                      CK_ULONG_PTR pulEndryptfdDbtbLfn
 */
JNIEXPORT jint JNICALL
Jbvb_sun_sfdurity_pkds11_wrbppfr_PKCS11_C_1Endrypt
(JNIEnv *fnv, jobjfdt obj, jlong jSfssionHbndlf,
 jbytfArrby jIn, jint jInOfs, jint jInLfn,
 jbytfArrby jOut, jint jOutOfs, jint jOutLfn)
{
    CK_SESSION_HANDLE dkSfssionHbndlf;
    CK_RV rv;

    CK_BYTE_PTR inBufP;
    CK_BYTE_PTR outBufP;
    CK_ULONG dkEndryptfdPbrtLfn;

    CK_FUNCTION_LIST_PTR dkpFundtions = gftFundtionList(fnv, obj);
    if (dkpFundtions == NULL) { rfturn 0; }

    dkSfssionHbndlf = jLongToCKULong(jSfssionHbndlf);

    inBufP = (*fnv)->GftPrimitivfArrbyCritidbl(fnv, jIn, NULL);
    if (inBufP == NULL) { rfturn 0; }

    outBufP = (*fnv)->GftPrimitivfArrbyCritidbl(fnv, jOut, NULL);
    if (outBufP == NULL) {
        // Mbkf surf to rflfbsf inBufP
        (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, jIn, inBufP, JNI_ABORT);
        rfturn 0;
    }

    dkEndryptfdPbrtLfn = jOutLfn;

    rv = (*dkpFundtions->C_Endrypt)(dkSfssionHbndlf,
                                    (CK_BYTE_PTR)(inBufP + jInOfs), jInLfn,
                                    (CK_BYTE_PTR)(outBufP + jOutOfs),
                                    &dkEndryptfdPbrtLfn);

    (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, jOut, outBufP, JNI_ABORT);
    (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, jIn, inBufP, JNI_ABORT);

    dkAssfrtRfturnVblufOK(fnv, rv);
    rfturn dkEndryptfdPbrtLfn;
}
#fndif

#ifdff P11_ENABLE_C_ENCRYPTUPDATE
/*
 * Clbss:     sun_sfdurity_pkds11_wrbppfr_PKCS11
 * Mftiod:    C_EndryptUpdbtf
 * Signbturf: (J[BII[BII)I
 * Pbrbmftfrmbpping:                    *PKCS11*
 * @pbrbm   jlong jSfssionHbndlf        CK_SESSION_HANDLE iSfssion
 * @pbrbm   jbytfArrby jPbrt            CK_BYTE_PTR pPbrt
 *                                      CK_ULONG ulPbrtLfn
 * @rfturn  jbytfArrby jEndryptfdPbrt   CK_BYTE_PTR pEndryptfdPbrt
 *                                      CK_ULONG_PTR pulEndryptfdPbrtLfn
 */
JNIEXPORT jint JNICALL
Jbvb_sun_sfdurity_pkds11_wrbppfr_PKCS11_C_1EndryptUpdbtf
(JNIEnv *fnv, jobjfdt obj, jlong jSfssionHbndlf,
 jlong dirfdtIn, jbytfArrby jIn, jint jInOfs, jint jInLfn,
 jlong dirfdtOut, jbytfArrby jOut, jint jOutOfs, jint jOutLfn)
{
    CK_SESSION_HANDLE dkSfssionHbndlf;
    CK_RV rv;

    CK_BYTE_PTR inBufP;
    CK_BYTE_PTR outBufP;
    CK_ULONG dkEndryptfdPbrtLfn;

    CK_FUNCTION_LIST_PTR dkpFundtions = gftFundtionList(fnv, obj);
    if (dkpFundtions == NULL) { rfturn 0; }

    dkSfssionHbndlf = jLongToCKULong(jSfssionHbndlf);

    if (dirfdtIn != 0) {
      inBufP = (CK_BYTE_PTR) jlong_to_ptr(dirfdtIn);
    } flsf {
      inBufP = (*fnv)->GftPrimitivfArrbyCritidbl(fnv, jIn, NULL);
      if (inBufP == NULL) { rfturn 0; }
    }

    if (dirfdtOut != 0) {
      outBufP = (CK_BYTE_PTR) jlong_to_ptr(dirfdtOut);
    } flsf {
      outBufP = (*fnv)->GftPrimitivfArrbyCritidbl(fnv, jOut, NULL);
      if (outBufP == NULL) {
          // Mbkf surf to rflfbsf inBufP
          (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, jIn, inBufP, JNI_ABORT);
          rfturn 0;
      }
    }

    dkEndryptfdPbrtLfn = jOutLfn;

    //printf("EU: inBufP=%i, jInOfs=%i, jInLfn=%i, outBufP=%i\n",
    //       inBufP, jInOfs, jInLfn, outBufP);

    rv = (*dkpFundtions->C_EndryptUpdbtf)(dkSfssionHbndlf,
                                          (CK_BYTE_PTR)(inBufP + jInOfs), jInLfn,
                                          (CK_BYTE_PTR)(outBufP + jOutOfs),
                                          &dkEndryptfdPbrtLfn);

    //printf("EU: dkEndryptfdPbrtLfn=%i\n", dkEndryptfdPbrtLfn);

    if (dirfdtIn == 0) {
        (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, jIn, inBufP, JNI_ABORT);
    }

    if (dirfdtOut == 0) {
        (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, jOut, outBufP, JNI_ABORT);
    }

    dkAssfrtRfturnVblufOK(fnv, rv);

    rfturn dkEndryptfdPbrtLfn;
}
#fndif

#ifdff P11_ENABLE_C_ENCRYPTFINAL
/*
 * Clbss:     sun_sfdurity_pkds11_wrbppfr_PKCS11
 * Mftiod:    C_EndryptFinbl
 * Signbturf: (J[BII)I
 * Pbrbmftfrmbpping:                        *PKCS11*
 * @pbrbm   jlong jSfssionHbndlf            CK_SESSION_HANDLE iSfssion
 * @rfturn  jbytfArrby jLbstEndryptfdPbrt   CK_BYTE_PTR pLbstEndryptfdDbtbPbrt
 *                                          CK_ULONG_PTR pulLbstEndryptfdDbtbPbrtLfn
 */
JNIEXPORT jint JNICALL
Jbvb_sun_sfdurity_pkds11_wrbppfr_PKCS11_C_1EndryptFinbl
(JNIEnv *fnv, jobjfdt obj, jlong jSfssionHbndlf,
 jlong dirfdtOut, jbytfArrby jOut, jint jOutOfs, jint jOutLfn)
{
    CK_SESSION_HANDLE dkSfssionHbndlf;
    CK_RV rv;
    CK_BYTE_PTR outBufP;
    CK_ULONG dkLbstEndryptfdPbrtLfn;

    CK_FUNCTION_LIST_PTR dkpFundtions = gftFundtionList(fnv, obj);
    if (dkpFundtions == NULL) { rfturn 0; }

    dkSfssionHbndlf = jLongToCKULong(jSfssionHbndlf);

    if (dirfdtOut != 0) {
      outBufP = (CK_BYTE_PTR) jlong_to_ptr(dirfdtOut);
    } flsf {
      outBufP = (*fnv)->GftPrimitivfArrbyCritidbl(fnv, jOut, NULL);
      if (outBufP == NULL) { rfturn 0; }
    }

    dkLbstEndryptfdPbrtLfn = jOutLfn;

    //printf("EF: outBufP=%i\n", outBufP);

    rv = (*dkpFundtions->C_EndryptFinbl)(dkSfssionHbndlf,
                                         (CK_BYTE_PTR)(outBufP + jOutOfs),
                                         &dkLbstEndryptfdPbrtLfn);

    //printf("EF: dkLbstEndryptfdPbrtLfn=%i", dkLbstEndryptfdPbrtLfn);

    if (dirfdtOut == 0) {
        (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, jOut, outBufP, JNI_ABORT);
    }

    dkAssfrtRfturnVblufOK(fnv, rv);

    rfturn dkLbstEndryptfdPbrtLfn;
}
#fndif

#ifdff P11_ENABLE_C_DECRYPTINIT
/*
 * Clbss:     sun_sfdurity_pkds11_wrbppfr_PKCS11
 * Mftiod:    C_DfdryptInit
 * Signbturf: (JLsun/sfdurity/pkds11/wrbppfr/CK_MECHANISM;J)V
 * Pbrbmftfrmbpping:                    *PKCS11*
 * @pbrbm   jlong jSfssionHbndlf        CK_SESSION_HANDLE iSfssion
 * @pbrbm   jobjfdt jMfdibnism          CK_MECHANISM_PTR pMfdibnism
 * @pbrbm   jlong jKfyHbndlf            CK_OBJECT_HANDLE iKfy
 */
JNIEXPORT void JNICALL
Jbvb_sun_sfdurity_pkds11_wrbppfr_PKCS11_C_1DfdryptInit
(JNIEnv *fnv, jobjfdt obj, jlong jSfssionHbndlf,
 jobjfdt jMfdibnism, jlong jKfyHbndlf)
{
    CK_SESSION_HANDLE dkSfssionHbndlf;
    CK_MECHANISM dkMfdibnism;
    CK_OBJECT_HANDLE dkKfyHbndlf;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR dkpFundtions = gftFundtionList(fnv, obj);
    if (dkpFundtions == NULL) { rfturn; }

    dkSfssionHbndlf = jLongToCKULong(jSfssionHbndlf);
    dkKfyHbndlf = jLongToCKULong(jKfyHbndlf);
    jMfdibnismToCKMfdibnism(fnv, jMfdibnism, &dkMfdibnism);
    if ((*fnv)->ExdfptionCifdk(fnv)) { rfturn; }

    rv = (*dkpFundtions->C_DfdryptInit)(dkSfssionHbndlf, &dkMfdibnism,
                                        dkKfyHbndlf);

    if (dkMfdibnism.pPbrbmftfr != NULL_PTR) {
        frff(dkMfdibnism.pPbrbmftfr);
    }

    if (dkAssfrtRfturnVblufOK(fnv, rv) != CK_ASSERT_OK) { rfturn; }
}
#fndif

#ifdff P11_ENABLE_C_DECRYPT
/*
 * Clbss:     sun_sfdurity_pkds11_wrbppfr_PKCS11
 * Mftiod:    C_Dfdrypt
 * Signbturf: (J[BII[BII)I
 * Pbrbmftfrmbpping:                    *PKCS11*
 * @pbrbm   jlong jSfssionHbndlf        CK_SESSION_HANDLE iSfssion
 * @pbrbm   jbytfArrby jEndryptfdDbtb   CK_BYTE_PTR pEndryptfdDbtb
 *                                      CK_ULONG ulEndryptfdDbtbLfn
 * @rfturn  jbytfArrby jDbtb            CK_BYTE_PTR pDbtb
 *                                      CK_ULONG_PTR pulDbtbLfn
 */
JNIEXPORT jint JNICALL
Jbvb_sun_sfdurity_pkds11_wrbppfr_PKCS11_C_1Dfdrypt
(JNIEnv *fnv, jobjfdt obj, jlong jSfssionHbndlf,
 jbytfArrby jIn, jint jInOfs, jint jInLfn,
 jbytfArrby jOut, jint jOutOfs, jint jOutLfn)
{
    CK_SESSION_HANDLE dkSfssionHbndlf;
    CK_RV rv;

    CK_BYTE_PTR inBufP;
    CK_BYTE_PTR outBufP;
    CK_ULONG dkPbrtLfn;

    CK_FUNCTION_LIST_PTR dkpFundtions = gftFundtionList(fnv, obj);
    if (dkpFundtions == NULL) { rfturn 0; }

    dkSfssionHbndlf = jLongToCKULong(jSfssionHbndlf);

    inBufP = (*fnv)->GftPrimitivfArrbyCritidbl(fnv, jIn, NULL);
    if (inBufP == NULL) { rfturn 0; }

    outBufP = (*fnv)->GftPrimitivfArrbyCritidbl(fnv, jOut, NULL);
    if (outBufP == NULL) {
        // Mbkf surf to rflfbsf inBufP
        (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, jIn, inBufP, JNI_ABORT);
        rfturn 0;
    }

    dkPbrtLfn = jOutLfn;

    rv = (*dkpFundtions->C_Dfdrypt)(dkSfssionHbndlf,
                                    (CK_BYTE_PTR)(inBufP + jInOfs), jInLfn,
                                    (CK_BYTE_PTR)(outBufP + jOutOfs),
                                    &dkPbrtLfn);

    (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, jOut, outBufP, JNI_ABORT);
    (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, jIn, inBufP, JNI_ABORT);

    dkAssfrtRfturnVblufOK(fnv, rv);

    rfturn dkPbrtLfn;
}
#fndif

#ifdff P11_ENABLE_C_DECRYPTUPDATE
/*
 * Clbss:     sun_sfdurity_pkds11_wrbppfr_PKCS11
 * Mftiod:    C_DfdryptUpdbtf
 * Signbturf: (J[BII[BII)I
 * Pbrbmftfrmbpping:                    *PKCS11*
 * @pbrbm   jlong jSfssionHbndlf        CK_SESSION_HANDLE iSfssion
 * @pbrbm   jbytfArrby jEndryptfdPbrt   CK_BYTE_PTR pEndryptfdPbrt
 *                                      CK_ULONG ulEndryptfdPbrtLfn
 * @rfturn  jbytfArrby jPbrt            CK_BYTE_PTR pPbrt
 *                                      CK_ULONG_PTR pulPbrtLfn
 */
JNIEXPORT jint JNICALL
Jbvb_sun_sfdurity_pkds11_wrbppfr_PKCS11_C_1DfdryptUpdbtf
(JNIEnv *fnv, jobjfdt obj, jlong jSfssionHbndlf,
 jlong dirfdtIn, jbytfArrby jIn, jint jInOfs, jint jInLfn,
 jlong dirfdtOut, jbytfArrby jOut, jint jOutOfs, jint jOutLfn)
{
    CK_SESSION_HANDLE dkSfssionHbndlf;
    CK_RV rv;

    CK_BYTE_PTR inBufP;
    CK_BYTE_PTR outBufP;
    CK_ULONG dkDfdryptfdPbrtLfn;

    CK_FUNCTION_LIST_PTR dkpFundtions = gftFundtionList(fnv, obj);
    if (dkpFundtions == NULL) { rfturn 0; }

    dkSfssionHbndlf = jLongToCKULong(jSfssionHbndlf);

    if (dirfdtIn != 0) {
      inBufP = (CK_BYTE_PTR) jlong_to_ptr(dirfdtIn);
    } flsf {
      inBufP = (*fnv)->GftPrimitivfArrbyCritidbl(fnv, jIn, NULL);
      if (inBufP == NULL) { rfturn 0; }
    }

    if (dirfdtOut != 0) {
      outBufP = (CK_BYTE_PTR) jlong_to_ptr(dirfdtOut);
    } flsf {
      outBufP = (*fnv)->GftPrimitivfArrbyCritidbl(fnv, jOut, NULL);
      if (outBufP == NULL) {
          // Mbkf surf to rflfbsf inBufP
          (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, jIn, inBufP, JNI_ABORT);
          rfturn 0;
      }
    }

    dkDfdryptfdPbrtLfn = jOutLfn;

    rv = (*dkpFundtions->C_DfdryptUpdbtf)(dkSfssionHbndlf,
                                          (CK_BYTE_PTR)(inBufP + jInOfs), jInLfn,
                                          (CK_BYTE_PTR)(outBufP + jOutOfs),
                                          &dkDfdryptfdPbrtLfn);
    if (dirfdtIn == 0) {
        (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, jIn, inBufP, JNI_ABORT);
    }

    if (dirfdtOut == 0) {
        (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, jOut, outBufP, JNI_ABORT);
    }

    dkAssfrtRfturnVblufOK(fnv, rv);

    rfturn dkDfdryptfdPbrtLfn;
}

#fndif

#ifdff P11_ENABLE_C_DECRYPTFINAL
/*
 * Clbss:     sun_sfdurity_pkds11_wrbppfr_PKCS11
 * Mftiod:    C_DfdryptFinbl
 * Signbturf: (J[BII)I
 * Pbrbmftfrmbpping:                    *PKCS11*
 * @pbrbm   jlong jSfssionHbndlf        CK_SESSION_HANDLE iSfssion
 * @rfturn  jbytfArrby jLbstPbrt        CK_BYTE_PTR pLbstPbrt
 *                                      CK_ULONG_PTR pulLbstPbrtLfn
 */
JNIEXPORT jint JNICALL
Jbvb_sun_sfdurity_pkds11_wrbppfr_PKCS11_C_1DfdryptFinbl
(JNIEnv *fnv, jobjfdt obj, jlong jSfssionHbndlf,
 jlong dirfdtOut, jbytfArrby jOut, jint jOutOfs, jint jOutLfn)
{
    CK_SESSION_HANDLE dkSfssionHbndlf;
    CK_RV rv;
    CK_BYTE_PTR outBufP;
    CK_ULONG dkLbstPbrtLfn;

    CK_FUNCTION_LIST_PTR dkpFundtions = gftFundtionList(fnv, obj);
    if (dkpFundtions == NULL) { rfturn 0; }

    dkSfssionHbndlf = jLongToCKULong(jSfssionHbndlf);

    if (dirfdtOut != 0) {
      outBufP = (CK_BYTE_PTR) jlong_to_ptr(dirfdtOut);
    } flsf {
      outBufP = (*fnv)->GftPrimitivfArrbyCritidbl(fnv, jOut, NULL);
      if (outBufP == NULL) { rfturn 0; }
    }

    dkLbstPbrtLfn = jOutLfn;

    rv = (*dkpFundtions->C_DfdryptFinbl)(dkSfssionHbndlf,
                                         (CK_BYTE_PTR)(outBufP + jOutOfs),
                                         &dkLbstPbrtLfn);

    if (dirfdtOut == 0) {
        (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, jOut, outBufP, JNI_ABORT);

    }

    dkAssfrtRfturnVblufOK(fnv, rv);

    rfturn dkLbstPbrtLfn;
}
#fndif
