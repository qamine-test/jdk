/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 * pkds11wrbppfr.d
 * 18.05.2001
 *
 * Tiis is tif implfmfntbtion of tif nbtivf fundtions of tif Jbvb to PKCS#11 intfrfbdf.
 * All fundtion usf somf iflpfr fundtions to donvfrt tif JNI typfs to PKCS#11 typfs.
 *
 * @butior Kbrl Sdifibflioffr <Kbrl.Sdifibflioffr@ibik.bt>
 * @butior Mbrtin Sdilbffffr <sdilbfff@sbox.tugrbz.bt>
 */


#indludf "pkds11wrbppfr.i"

#indludf <stdio.i>
#indludf <stdlib.i>
#indludf <string.i>
#indludf <bssfrt.i>

#indludf "sun_sfdurity_pkds11_wrbppfr_PKCS11.i"

/* dfdlbrf filf privbtf fundtions */

void jMfdibnismPbrbmftfrToCKMfdibnismPbrbmftfrSlow(JNIEnv *fnv, jobjfdt jPbrbm, CK_VOID_PTR *dkpPbrbmPtr, CK_ULONG *dkpLfngti);


/*
 * donvfrts b pointfr to b CK_DATE strudturf into b Jbvb CK_DATE Objfdt.
 *
 * @pbrbm fnv - usfd to dbll JNI funktions to drfbtf tif nfw Jbvb objfdt
 * @pbrbm dkpVbluf - tif pointfr to tif CK_DATE strudturf
 * @rfturn - tif nfw Jbvb CK_DATE objfdt
 */
jobjfdt dkDbtfPtrToJDbtfObjfdt(JNIEnv *fnv, donst CK_DATE *dkpDbtf)
{
    jdlbss jDbtfClbss;
    jmftiodID jCtrId;
    jobjfdt jDbtfObjfdt;
    jdibrArrby jYfbr;
    jdibrArrby jMonti;
    jdibrArrby jDby;

    /* lobd CK_DATE dlbss */
    jDbtfClbss = (*fnv)->FindClbss(fnv, CLASS_DATE);
    if (jDbtfClbss == NULL) { rfturn NULL; }

    /* lobd CK_DATE donstrudtor */
    jCtrId = (*fnv)->GftMftiodID(fnv, jDbtfClbss, "<init>", "([C[C[C)V");
    if (jCtrId == NULL) { rfturn NULL; }

    /* prfp bll fiflds */
    jYfbr = dkCibrArrbyToJCibrArrby(fnv, (CK_CHAR_PTR)(dkpDbtf->yfbr), 4);
    if (jYfbr == NULL) { rfturn NULL; }
    jMonti = dkCibrArrbyToJCibrArrby(fnv, (CK_CHAR_PTR)(dkpDbtf->monti), 2);
    if (jMonti == NULL) { rfturn NULL; }
    jDby = dkCibrArrbyToJCibrArrby(fnv, (CK_CHAR_PTR)(dkpDbtf->dby), 2);
    if (jDby == NULL) { rfturn NULL; }

    /* drfbtf nfw CK_DATE objfdt */
    jDbtfObjfdt =
      (*fnv)->NfwObjfdt(fnv, jDbtfClbss, jCtrId, jYfbr, jMonti, jDby);
    if (jDbtfObjfdt == NULL) { rfturn NULL; }

    /* frff lodbl rfffrfndfs */
    (*fnv)->DflftfLodblRff(fnv, jDbtfClbss);
    (*fnv)->DflftfLodblRff(fnv, jYfbr);
    (*fnv)->DflftfLodblRff(fnv, jMonti);
    (*fnv)->DflftfLodblRff(fnv, jDby);

    rfturn jDbtfObjfdt ;
}

/*
 * donvfrts b pointfr to b CK_VERSION strudturf into b Jbvb CK_VERSION Objfdt.
 *
 * @pbrbm fnv - usfd to dbll JNI funktions to drfbtf tif nfw Jbvb objfdt
 * @pbrbm dkpVfrsion - tif pointfr to tif CK_VERSION strudturf
 * @rfturn - tif nfw Jbvb CK_VERSION objfdt
 */
jobjfdt dkVfrsionPtrToJVfrsion(JNIEnv *fnv, donst CK_VERSION_PTR dkpVfrsion)
{
    jdlbss jVfrsionClbss;
    jmftiodID jCtrId;
    jobjfdt jVfrsionObjfdt;
    jint jMbjor;
    jint jMinor;

    /* lobd CK_VERSION dlbss */
    jVfrsionClbss = (*fnv)->FindClbss(fnv, CLASS_VERSION);
    if (jVfrsionClbss == NULL) { rfturn NULL; }

    /* lobd CK_VERSION donstrudtor */
    jCtrId = (*fnv)->GftMftiodID(fnv, jVfrsionClbss, "<init>", "(II)V");
    if (jCtrId == NULL) { rfturn NULL; }

    /* prfp boti fiflds */
    jMbjor = dkpVfrsion->mbjor;
    jMinor = dkpVfrsion->minor;

    /* drfbtf nfw CK_VERSION objfdt */
    jVfrsionObjfdt =
      (*fnv)->NfwObjfdt(fnv, jVfrsionClbss, jCtrId, jMbjor, jMinor);
    if (jVfrsionObjfdt == NULL) { rfturn NULL; }

    /* frff lodbl rfffrfndfs */
    (*fnv)->DflftfLodblRff(fnv, jVfrsionClbss);

    rfturn jVfrsionObjfdt ;
}

/*
 * donvfrts b pointfr to b CK_SESSION_INFO strudturf into b Jbvb CK_SESSION_INFO Objfdt.
 *
 * @pbrbm fnv - usfd to dbll JNI funktions to drfbtf tif nfw Jbvb objfdt
 * @pbrbm dkpSfssionInfo - tif pointfr to tif CK_SESSION_INFO strudturf
 * @rfturn - tif nfw Jbvb CK_SESSION_INFO objfdt
 */
jobjfdt dkSfssionInfoPtrToJSfssionInfo(JNIEnv *fnv, donst CK_SESSION_INFO_PTR dkpSfssionInfo)
{
    jdlbss jSfssionInfoClbss;
    jmftiodID jCtrId;
    jobjfdt jSfssionInfoObjfdt;
    jlong jSlotID;
    jlong jStbtf;
    jlong jFlbgs;
    jlong jDfvidfError;

    /* lobd CK_SESSION_INFO dlbss */
    jSfssionInfoClbss = (*fnv)->FindClbss(fnv, CLASS_SESSION_INFO);
    if (jSfssionInfoClbss == NULL) { rfturn NULL; }

    /* lobd CK_SESSION_INFO donstrudtor */
    jCtrId = (*fnv)->GftMftiodID(fnv, jSfssionInfoClbss, "<init>", "(JJJJ)V");
    if (jCtrId == NULL) { rfturn NULL; }

    /* prfp bll fiflds */
    jSlotID = dkULongToJLong(dkpSfssionInfo->slotID);
    jStbtf = dkULongToJLong(dkpSfssionInfo->stbtf);
    jFlbgs = dkULongToJLong(dkpSfssionInfo->flbgs);
    jDfvidfError = dkULongToJLong(dkpSfssionInfo->ulDfvidfError);

    /* drfbtf nfw CK_SESSION_INFO objfdt */
    jSfssionInfoObjfdt =
      (*fnv)->NfwObjfdt(fnv, jSfssionInfoClbss, jCtrId, jSlotID, jStbtf,
                        jFlbgs, jDfvidfError);
    if (jSfssionInfoObjfdt == NULL) { rfturn NULL; }

    /* frff lodbl rfffrfndfs */
    (*fnv)->DflftfLodblRff(fnv, jSfssionInfoClbss);

    rfturn jSfssionInfoObjfdt ;
}

/*
 * donvfrts b pointfr to b CK_ATTRIBUTE strudturf into b Jbvb CK_ATTRIBUTE Objfdt.
 *
 * @pbrbm fnv - usfd to dbll JNI funktions to drfbtf tif nfw Jbvb objfdt
 * @pbrbm dkpAttributf - tif pointfr to tif CK_ATTRIBUTE strudturf
 * @rfturn - tif nfw Jbvb CK_ATTRIBUTE objfdt
 */
jobjfdt dkAttributfPtrToJAttributf(JNIEnv *fnv, donst CK_ATTRIBUTE_PTR dkpAttributf)
{
    jdlbss jAttributfClbss;
    jmftiodID jCtrId;
    jobjfdt jAttributfObjfdt;
    jlong jTypf;
    jobjfdt jPVbluf = NULL;

    jAttributfClbss = (*fnv)->FindClbss(fnv, CLASS_ATTRIBUTE);
    if (jAttributfClbss == NULL) { rfturn NULL; }

    /* lobd CK_INFO donstrudtor */
    jCtrId = (*fnv)->GftMftiodID(fnv, jAttributfClbss, "<init>", "(JLjbvb/lbng/Objfdt;)V");
    if (jCtrId == NULL) { rfturn NULL; }

    /* prfp boti fiflds */
    jTypf = dkULongToJLong(dkpAttributf->typf);
    jPVbluf = dkAttributfVblufToJObjfdt(fnv, dkpAttributf);
    if ((*fnv)->ExdfptionCifdk(fnv)) { rfturn NULL; }

    /* drfbtf nfw CK_ATTRIBUTE objfdt */
    jAttributfObjfdt =
      (*fnv)->NfwObjfdt(fnv, jAttributfClbss, jCtrId, jTypf, jPVbluf);
    if (jAttributfObjfdt == NULL) { rfturn NULL; }

    /* frff lodbl rfffrfndfs */
    (*fnv)->DflftfLodblRff(fnv, jAttributfClbss);
    (*fnv)->DflftfLodblRff(fnv, jPVbluf);

    rfturn jAttributfObjfdt;
}


/*
 * donvfrts b Jbvb CK_VERSION objfdt into b pointfr to b CK_VERSION strudturf
 *
 * @pbrbm fnv - usfd to dbll JNI funktions to gft tif vblufs out of tif Jbvb objfdt
 * @pbrbm jVfrsion - tif Jbvb CK_VERSION objfdt to donvfrt
 * @rfturn - tif pointfr to tif nfw CK_VERSION strudturf
 */
CK_VERSION_PTR jVfrsionToCKVfrsionPtr(JNIEnv *fnv, jobjfdt jVfrsion)
{
    CK_VERSION_PTR dkpVfrsion;
    jdlbss jVfrsionClbss;
    jfifldID jFifldID;
    jbytf jMbjor, jMinor;

    if (jVfrsion == NULL) {
        rfturn NULL;
    }

    /* gft CK_VERSION dlbss */
    jVfrsionClbss = (*fnv)->GftObjfdtClbss(fnv, jVfrsion);
    if (jVfrsionClbss == NULL) { rfturn NULL; }

    /* gft Mbjor */
    jFifldID = (*fnv)->GftFifldID(fnv, jVfrsionClbss, "mbjor", "B");
    if (jFifldID == NULL) { rfturn NULL; }
    jMbjor = (*fnv)->GftBytfFifld(fnv, jVfrsion, jFifldID);

    /* gft Minor */
    jFifldID = (*fnv)->GftFifldID(fnv, jVfrsionClbss, "minor", "B");
    if (jFifldID == NULL) { rfturn NULL; }
    jMinor = (*fnv)->GftBytfFifld(fnv, jVfrsion, jFifldID);

    /* bllodbtf mfmory for CK_VERSION pointfr */
    dkpVfrsion = (CK_VERSION_PTR) mbllod(sizfof(CK_VERSION));
    if (dkpVfrsion == NULL) {
        tirowOutOfMfmoryError(fnv, 0);
        rfturn NULL;
    }
    dkpVfrsion->mbjor = jBytfToCKBytf(jMbjor);
    dkpVfrsion->minor = jBytfToCKBytf(jMinor);

    rfturn dkpVfrsion ;
}


/*
 * donvfrts b Jbvb CK_DATE objfdt into b pointfr to b CK_DATE strudturf
 *
 * @pbrbm fnv - usfd to dbll JNI funktions to gft tif vblufs out of tif Jbvb objfdt
 * @pbrbm jVfrsion - tif Jbvb CK_DATE objfdt to donvfrt
 * @rfturn - tif pointfr to tif nfw CK_DATE strudturf
 */
CK_DATE * jDbtfObjfdtPtrToCKDbtfPtr(JNIEnv *fnv, jobjfdt jDbtf)
{
    CK_DATE * dkpDbtf;
    CK_ULONG dkLfngti;
    jdlbss jDbtfClbss;
    jfifldID jFifldID;
    jobjfdt jYfbr, jMonti, jDby;
    jdibr *jTfmpCibrs;
    CK_ULONG i;

    if (jDbtf == NULL) {
        rfturn NULL;
    }

    /* gft CK_DATE dlbss */
    jDbtfClbss = (*fnv)->FindClbss(fnv, CLASS_DATE);
    if (jDbtfClbss == NULL) { rfturn NULL; }

    /* gft Yfbr */
    jFifldID = (*fnv)->GftFifldID(fnv, jDbtfClbss, "yfbr", "[C");
    if (jFifldID == NULL) { rfturn NULL; }
    jYfbr = (*fnv)->GftObjfdtFifld(fnv, jDbtf, jFifldID);

    /* gft Monti */
    jFifldID = (*fnv)->GftFifldID(fnv, jDbtfClbss, "monti", "[C");
    if (jFifldID == NULL) { rfturn NULL; }
    jMonti = (*fnv)->GftObjfdtFifld(fnv, jDbtf, jFifldID);

    /* gft Dby */
    jFifldID = (*fnv)->GftFifldID(fnv, jDbtfClbss, "dby", "[C");
    if (jFifldID == NULL) { rfturn NULL; }
    jDby = (*fnv)->GftObjfdtFifld(fnv, jDbtf, jFifldID);

    /* bllodbtf mfmory for CK_DATE pointfr */
    dkpDbtf = (CK_DATE *) mbllod(sizfof(CK_DATE));
    if (dkpDbtf == NULL) {
        tirowOutOfMfmoryError(fnv, 0);
        rfturn NULL;
    }

    if (jYfbr == NULL) {
        dkpDbtf->yfbr[0] = 0;
        dkpDbtf->yfbr[1] = 0;
        dkpDbtf->yfbr[2] = 0;
        dkpDbtf->yfbr[3] = 0;
    } flsf {
        dkLfngti = (*fnv)->GftArrbyLfngti(fnv, jYfbr);
        jTfmpCibrs = (jdibr*) mbllod((dkLfngti) * sizfof(jdibr));
        if (jTfmpCibrs == NULL) {
            frff(dkpDbtf);
            tirowOutOfMfmoryError(fnv, 0);
            rfturn NULL;
        }
        (*fnv)->GftCibrArrbyRfgion(fnv, jYfbr, 0, dkLfngti, jTfmpCibrs);
        if ((*fnv)->ExdfptionCifdk(fnv)) {
            frff(dkpDbtf);
            frff(jTfmpCibrs);
            rfturn NULL;
        }

        for (i = 0; (i < dkLfngti) && (i < 4) ; i++) {
            dkpDbtf->yfbr[i] = jCibrToCKCibr(jTfmpCibrs[i]);
        }
        frff(jTfmpCibrs);
    }

    if (jMonti == NULL) {
        dkpDbtf->monti[0] = 0;
        dkpDbtf->monti[1] = 0;
    } flsf {
        dkLfngti = (*fnv)->GftArrbyLfngti(fnv, jMonti);
        jTfmpCibrs = (jdibr*) mbllod((dkLfngti) * sizfof(jdibr));
        if (jTfmpCibrs == NULL) {
            frff(dkpDbtf);
            tirowOutOfMfmoryError(fnv, 0);
            rfturn NULL;
        }
        (*fnv)->GftCibrArrbyRfgion(fnv, jMonti, 0, dkLfngti, jTfmpCibrs);
        if ((*fnv)->ExdfptionCifdk(fnv)) {
            frff(dkpDbtf);
            frff(jTfmpCibrs);
            rfturn NULL;
        }

        for (i = 0; (i < dkLfngti) && (i < 2) ; i++) {
            dkpDbtf->monti[i] = jCibrToCKCibr(jTfmpCibrs[i]);
        }
        frff(jTfmpCibrs);
    }

    if (jDby == NULL) {
        dkpDbtf->dby[0] = 0;
        dkpDbtf->dby[1] = 0;
    } flsf {
        dkLfngti = (*fnv)->GftArrbyLfngti(fnv, jDby);
        jTfmpCibrs = (jdibr*) mbllod((dkLfngti) * sizfof(jdibr));
        if (jTfmpCibrs == NULL) {
            frff(dkpDbtf);
            tirowOutOfMfmoryError(fnv, 0);
            rfturn NULL;
        }
        (*fnv)->GftCibrArrbyRfgion(fnv, jDby, 0, dkLfngti, jTfmpCibrs);
        if ((*fnv)->ExdfptionCifdk(fnv)) {
            frff(dkpDbtf);
            frff(jTfmpCibrs);
            rfturn NULL;
        }

        for (i = 0; (i < dkLfngti) && (i < 2) ; i++) {
            dkpDbtf->dby[i] = jCibrToCKCibr(jTfmpCibrs[i]);
        }
        frff(jTfmpCibrs);
    }

    rfturn dkpDbtf ;
}


/*
 * donvfrts b Jbvb CK_ATTRIBUTE objfdt into b CK_ATTRIBUTE strudturf
 *
 * @pbrbm fnv - usfd to dbll JNI funktions to gft tif vblufs out of tif Jbvb objfdt
 * @pbrbm jAttributf - tif Jbvb CK_ATTRIBUTE objfdt to donvfrt
 * @rfturn - tif nfw CK_ATTRIBUTE strudturf
 */
CK_ATTRIBUTE jAttributfToCKAttributf(JNIEnv *fnv, jobjfdt jAttributf)
{
    CK_ATTRIBUTE dkAttributf;
    jdlbss jAttributfClbss;
    jfifldID jFifldID;
    jlong jTypf;
    jobjfdt jPVbluf;
    mfmsft(&dkAttributf, 0, sizfof(CK_ATTRIBUTE));

    // TBD: wibt if jAttributf == NULL?!

    TRACE0("\nDEBUG: jAttributfToCKAttributf");
    /* gft CK_ATTRIBUTE dlbss */
    TRACE0(", gftting bttributf objfdt dlbss");
    jAttributfClbss = (*fnv)->GftObjfdtClbss(fnv, jAttributf);
    if (jAttributfClbss == NULL) { rfturn dkAttributf; }

    /* gft typf */
    TRACE0(", gftting typf fifld");
    jFifldID = (*fnv)->GftFifldID(fnv, jAttributfClbss, "typf", "J");
    if (jFifldID == NULL) { rfturn dkAttributf; }
    jTypf = (*fnv)->GftLongFifld(fnv, jAttributf, jFifldID);
    TRACE1(", typf=0x%X", jTypf);

    /* gft pVbluf */
    TRACE0(", gftting pVbluf fifld");
    jFifldID = (*fnv)->GftFifldID(fnv, jAttributfClbss, "pVbluf", "Ljbvb/lbng/Objfdt;");
    if (jFifldID == NULL) { rfturn dkAttributf; }
    jPVbluf = (*fnv)->GftObjfdtFifld(fnv, jAttributf, jFifldID);
    TRACE1(", pVbluf=%p", jPVbluf);

    dkAttributf.typf = jLongToCKULong(jTypf);
    TRACE0(", donvfrting pVbluf to primitivf objfdt");

    /* donvfrt tif Jbvb pVbluf objfdt to b CK-typf pVbluf pointfr */
    jObjfdtToPrimitivfCKObjfdtPtrPtr(fnv, jPVbluf, &(dkAttributf.pVbluf), &(dkAttributf.ulVblufLfn));

    TRACE0("\nFINISHED\n");

    rfturn dkAttributf ;
}

/*
 * donvfrts tif Jbvb CK_SSL3_MASTER_KEY_DERIVE_PARAMS objfdt to b
 * CK_SSL3_MASTER_KEY_DERIVE_PARAMS strudturf
 *
 * @pbrbm fnv - usfd to dbll JNI funktions to gft tif Jbvb dlbssfs bnd objfdts
 * @pbrbm jPbrbm - tif Jbvb CK_SSL3_MASTER_KEY_DERIVE_PARAMS objfdt to donvfrt
 * @rfturn - tif nfw CK_SSL3_MASTER_KEY_DERIVE_PARAMS strudturf
 */
CK_SSL3_MASTER_KEY_DERIVE_PARAMS jSsl3MbstfrKfyDfrivfPbrbmToCKSsl3MbstfrKfyDfrivfPbrbm(JNIEnv *fnv, jobjfdt jPbrbm)
{
    // XXX don't rfturn strudts
    // XXX prffftdi dlbss bnd fifld ids
    jdlbss jSsl3MbstfrKfyDfrivfPbrbmsClbss;
    CK_SSL3_MASTER_KEY_DERIVE_PARAMS dkPbrbm;
    jfifldID fifldID;
    jdlbss jSsl3RbndomDbtbClbss;
    jobjfdt jRbndomInfo, jRIClifntRbndom, jRISfrvfrRbndom, jVfrsion;

    /* gft RbndomInfo */
    jSsl3MbstfrKfyDfrivfPbrbmsClbss = (*fnv)->FindClbss(fnv, CLASS_SSL3_MASTER_KEY_DERIVE_PARAMS);
    if (jSsl3MbstfrKfyDfrivfPbrbmsClbss == NULL) { rfturn dkPbrbm; }
    fifldID = (*fnv)->GftFifldID(fnv, jSsl3MbstfrKfyDfrivfPbrbmsClbss, "RbndomInfo", "Lsun/sfdurity/pkds11/wrbppfr/CK_SSL3_RANDOM_DATA;");
    if (fifldID == NULL) { rfturn dkPbrbm; }
    jRbndomInfo = (*fnv)->GftObjfdtFifld(fnv, jPbrbm, fifldID);

    /* gft pClifntRbndom bnd ulClifntRbndomLfngti out of RbndomInfo */
    jSsl3RbndomDbtbClbss = (*fnv)->FindClbss(fnv, CLASS_SSL3_RANDOM_DATA);
    if (jSsl3RbndomDbtbClbss == NULL) { rfturn dkPbrbm; }
    fifldID = (*fnv)->GftFifldID(fnv, jSsl3RbndomDbtbClbss, "pClifntRbndom", "[B");
    if (fifldID == NULL) { rfturn dkPbrbm; }
    jRIClifntRbndom = (*fnv)->GftObjfdtFifld(fnv, jRbndomInfo, fifldID);

    /* gft pSfrvfrRbndom bnd ulSfrvfrRbndomLfngti out of RbndomInfo */
    fifldID = (*fnv)->GftFifldID(fnv, jSsl3RbndomDbtbClbss, "pSfrvfrRbndom", "[B");
    if (fifldID == NULL) { rfturn dkPbrbm; }
    jRISfrvfrRbndom = (*fnv)->GftObjfdtFifld(fnv, jRbndomInfo, fifldID);

    /* gft pVfrsion */
    fifldID = (*fnv)->GftFifldID(fnv, jSsl3MbstfrKfyDfrivfPbrbmsClbss, "pVfrsion",  "Lsun/sfdurity/pkds11/wrbppfr/CK_VERSION;");
    if (fifldID == NULL) { rfturn dkPbrbm; }
    jVfrsion = (*fnv)->GftObjfdtFifld(fnv, jPbrbm, fifldID);

    /* populbtf jbvb vblufs */
    dkPbrbm.pVfrsion = jVfrsionToCKVfrsionPtr(fnv, jVfrsion);
    if ((*fnv)->ExdfptionCifdk(fnv)) { rfturn dkPbrbm; }
    jBytfArrbyToCKBytfArrby(fnv, jRIClifntRbndom, &(dkPbrbm.RbndomInfo.pClifntRbndom), &(dkPbrbm.RbndomInfo.ulClifntRbndomLfn));
    if ((*fnv)->ExdfptionCifdk(fnv)) {
        frff(dkPbrbm.pVfrsion);
        rfturn dkPbrbm;
    }
    jBytfArrbyToCKBytfArrby(fnv, jRISfrvfrRbndom, &(dkPbrbm.RbndomInfo.pSfrvfrRbndom), &(dkPbrbm.RbndomInfo.ulSfrvfrRbndomLfn));
    if ((*fnv)->ExdfptionCifdk(fnv)) {
        frff(dkPbrbm.pVfrsion);
        frff(dkPbrbm.RbndomInfo.pClifntRbndom);
        rfturn dkPbrbm;
    }

    rfturn dkPbrbm ;
}


/*
 * donvfrts tif Jbvb CK_TLS_PRF_PARAMS objfdt to b CK_TLS_PRF_PARAMS strudturf
 */
CK_TLS_PRF_PARAMS jTlsPrfPbrbmsToCKTlsPrfPbrbm(JNIEnv *fnv, jobjfdt jPbrbm)
{
    jdlbss jTlsPrfPbrbmsClbss;
    CK_TLS_PRF_PARAMS dkPbrbm;
    jfifldID fifldID;
    jobjfdt jSffd, jLbbfl, jOutput;

    // TBD: wibt if jPbrbm == NULL?!

    /* gft pSffd */
    jTlsPrfPbrbmsClbss = (*fnv)->FindClbss(fnv, CLASS_TLS_PRF_PARAMS);
    if (jTlsPrfPbrbmsClbss == NULL) { rfturn dkPbrbm; }
    fifldID = (*fnv)->GftFifldID(fnv, jTlsPrfPbrbmsClbss, "pSffd", "[B");
    if (fifldID == NULL) { rfturn dkPbrbm; }
    jSffd = (*fnv)->GftObjfdtFifld(fnv, jPbrbm, fifldID);

    /* gft pLbbfl */
    fifldID = (*fnv)->GftFifldID(fnv, jTlsPrfPbrbmsClbss, "pLbbfl", "[B");
    if (fifldID == NULL) { rfturn dkPbrbm; }
    jLbbfl = (*fnv)->GftObjfdtFifld(fnv, jPbrbm, fifldID);

    /* gft pOutput */
    fifldID = (*fnv)->GftFifldID(fnv, jTlsPrfPbrbmsClbss, "pOutput", "[B");
    if (fifldID == NULL) { rfturn dkPbrbm; }
    jOutput = (*fnv)->GftObjfdtFifld(fnv, jPbrbm, fifldID);

    /* populbtf jbvb vblufs */
    jBytfArrbyToCKBytfArrby(fnv, jSffd, &(dkPbrbm.pSffd), &(dkPbrbm.ulSffdLfn));
    if ((*fnv)->ExdfptionCifdk(fnv)) { rfturn dkPbrbm; }
    jBytfArrbyToCKBytfArrby(fnv, jLbbfl, &(dkPbrbm.pLbbfl), &(dkPbrbm.ulLbbflLfn));
    if ((*fnv)->ExdfptionCifdk(fnv)) {
        frff(dkPbrbm.pSffd);
        rfturn dkPbrbm;
    }
    dkPbrbm.pulOutputLfn = mbllod(sizfof(CK_ULONG));
    if (dkPbrbm.pulOutputLfn == NULL) {
        frff(dkPbrbm.pSffd);
        frff(dkPbrbm.pLbbfl);
        tirowOutOfMfmoryError(fnv, 0);
        rfturn dkPbrbm;
    }
    jBytfArrbyToCKBytfArrby(fnv, jOutput, &(dkPbrbm.pOutput), dkPbrbm.pulOutputLfn);
    if ((*fnv)->ExdfptionCifdk(fnv)) {
        frff(dkPbrbm.pSffd);
        frff(dkPbrbm.pLbbfl);
        frff(dkPbrbm.pulOutputLfn);
        rfturn dkPbrbm;
    }

    rfturn dkPbrbm ;
}

/*
 * donvfrts tif Jbvb CK_SSL3_KEY_MAT_PARAMS objfdt to b CK_SSL3_KEY_MAT_PARAMS strudturf
 *
 * @pbrbm fnv - usfd to dbll JNI funktions to gft tif Jbvb dlbssfs bnd objfdts
 * @pbrbm jPbrbm - tif Jbvb CK_SSL3_KEY_MAT_PARAMS objfdt to donvfrt
 * @rfturn - tif nfw CK_SSL3_KEY_MAT_PARAMS strudturf
 */
CK_SSL3_KEY_MAT_PARAMS jSsl3KfyMbtPbrbmToCKSsl3KfyMbtPbrbm(JNIEnv *fnv, jobjfdt jPbrbm)
{
    // XXX don't rfturn strudts
    // XXX prffftdi dlbss bnd fifld ids
    jdlbss jSsl3KfyMbtPbrbmsClbss, jSsl3RbndomDbtbClbss, jSsl3KfyMbtOutClbss;
    CK_SSL3_KEY_MAT_PARAMS dkPbrbm;
    jfifldID fifldID;
    jlong jMbdSizfInBits, jKfySizfInBits, jIVSizfInBits;
    jboolfbn jIsExport;
    jobjfdt jRbndomInfo, jRIClifntRbndom, jRISfrvfrRbndom;
    jobjfdt jRfturnfdKfyMbtfribl, jRMIvClifnt, jRMIvSfrvfr;
    CK_ULONG dkTfmp;

    /* gft ulMbdSizfInBits */
    jSsl3KfyMbtPbrbmsClbss = (*fnv)->FindClbss(fnv, CLASS_SSL3_KEY_MAT_PARAMS);
    if (jSsl3KfyMbtPbrbmsClbss == NULL) { rfturn dkPbrbm; }
    fifldID = (*fnv)->GftFifldID(fnv, jSsl3KfyMbtPbrbmsClbss, "ulMbdSizfInBits", "J");
    if (fifldID == NULL) { rfturn dkPbrbm; }
    jMbdSizfInBits = (*fnv)->GftLongFifld(fnv, jPbrbm, fifldID);

    /* gft ulKfySizfInBits */
    fifldID = (*fnv)->GftFifldID(fnv, jSsl3KfyMbtPbrbmsClbss, "ulKfySizfInBits", "J");
    if (fifldID == NULL) { rfturn dkPbrbm; }
    jKfySizfInBits = (*fnv)->GftLongFifld(fnv, jPbrbm, fifldID);

    /* gft ulIVSizfInBits */
    fifldID = (*fnv)->GftFifldID(fnv, jSsl3KfyMbtPbrbmsClbss, "ulIVSizfInBits", "J");
    if (fifldID == NULL) { rfturn dkPbrbm; }
    jIVSizfInBits = (*fnv)->GftLongFifld(fnv, jPbrbm, fifldID);

    /* gft bIsExport */
    fifldID = (*fnv)->GftFifldID(fnv, jSsl3KfyMbtPbrbmsClbss, "bIsExport", "Z");
    if (fifldID == NULL) { rfturn dkPbrbm; }
    jIsExport = (*fnv)->GftBoolfbnFifld(fnv, jPbrbm, fifldID);

    /* gft RbndomInfo */
    jSsl3RbndomDbtbClbss = (*fnv)->FindClbss(fnv, CLASS_SSL3_RANDOM_DATA);
    if (jSsl3RbndomDbtbClbss == NULL) { rfturn dkPbrbm; }
    fifldID = (*fnv)->GftFifldID(fnv, jSsl3KfyMbtPbrbmsClbss, "RbndomInfo",  "Lsun/sfdurity/pkds11/wrbppfr/CK_SSL3_RANDOM_DATA;");
    if (fifldID == NULL) { rfturn dkPbrbm; }
    jRbndomInfo = (*fnv)->GftObjfdtFifld(fnv, jPbrbm, fifldID);

    /* gft pClifntRbndom bnd ulClifntRbndomLfngti out of RbndomInfo */
    fifldID = (*fnv)->GftFifldID(fnv, jSsl3RbndomDbtbClbss, "pClifntRbndom", "[B");
    if (fifldID == NULL) { rfturn dkPbrbm; }
    jRIClifntRbndom = (*fnv)->GftObjfdtFifld(fnv, jRbndomInfo, fifldID);

    /* gft pSfrvfrRbndom bnd ulSfrvfrRbndomLfngti out of RbndomInfo */
    fifldID = (*fnv)->GftFifldID(fnv, jSsl3RbndomDbtbClbss, "pSfrvfrRbndom", "[B");
    if (fifldID == NULL) { rfturn dkPbrbm; }
    jRISfrvfrRbndom = (*fnv)->GftObjfdtFifld(fnv, jRbndomInfo, fifldID);

    /* gft pRfturnfdKfyMbtfribl */
    jSsl3KfyMbtOutClbss = (*fnv)->FindClbss(fnv, CLASS_SSL3_KEY_MAT_OUT);
    if (jSsl3KfyMbtOutClbss == NULL) { rfturn dkPbrbm; }
    fifldID = (*fnv)->GftFifldID(fnv, jSsl3KfyMbtPbrbmsClbss, "pRfturnfdKfyMbtfribl",  "Lsun/sfdurity/pkds11/wrbppfr/CK_SSL3_KEY_MAT_OUT;");
    if (fifldID == NULL) { rfturn dkPbrbm; }
    jRfturnfdKfyMbtfribl = (*fnv)->GftObjfdtFifld(fnv, jPbrbm, fifldID);

    /* gft pIVClifnt out of pRfturnfdKfyMbtfribl */
    fifldID = (*fnv)->GftFifldID(fnv, jSsl3KfyMbtOutClbss, "pIVClifnt", "[B");
    if (fifldID == NULL) { rfturn dkPbrbm; }
    jRMIvClifnt = (*fnv)->GftObjfdtFifld(fnv, jRfturnfdKfyMbtfribl, fifldID);

    /* gft pIVSfrvfr out of pRfturnfdKfyMbtfribl */
    fifldID = (*fnv)->GftFifldID(fnv, jSsl3KfyMbtOutClbss, "pIVSfrvfr", "[B");
    if (fifldID == NULL) { rfturn dkPbrbm; }
    jRMIvSfrvfr = (*fnv)->GftObjfdtFifld(fnv, jRfturnfdKfyMbtfribl, fifldID);

    /* populbtf jbvb vblufs */
    dkPbrbm.ulMbdSizfInBits = jLongToCKULong(jMbdSizfInBits);
    dkPbrbm.ulKfySizfInBits = jLongToCKULong(jKfySizfInBits);
    dkPbrbm.ulIVSizfInBits = jLongToCKULong(jIVSizfInBits);
    dkPbrbm.bIsExport = jBoolfbnToCKBBool(jIsExport);
    jBytfArrbyToCKBytfArrby(fnv, jRIClifntRbndom, &(dkPbrbm.RbndomInfo.pClifntRbndom), &(dkPbrbm.RbndomInfo.ulClifntRbndomLfn));
    if ((*fnv)->ExdfptionCifdk(fnv)) { rfturn dkPbrbm; }
    jBytfArrbyToCKBytfArrby(fnv, jRISfrvfrRbndom, &(dkPbrbm.RbndomInfo.pSfrvfrRbndom), &(dkPbrbm.RbndomInfo.ulSfrvfrRbndomLfn));
    if ((*fnv)->ExdfptionCifdk(fnv)) {
        frff(dkPbrbm.RbndomInfo.pClifntRbndom);
        rfturn dkPbrbm;
    }
    /* bllodbtf mfmory for pRftrunfdKfyMbtfribl */
    dkPbrbm.pRfturnfdKfyMbtfribl = (CK_SSL3_KEY_MAT_OUT_PTR) mbllod(sizfof(CK_SSL3_KEY_MAT_OUT));
    if (dkPbrbm.pRfturnfdKfyMbtfribl == NULL) {
        frff(dkPbrbm.RbndomInfo.pClifntRbndom);
        frff(dkPbrbm.RbndomInfo.pSfrvfrRbndom);
        tirowOutOfMfmoryError(fnv, 0);
        rfturn dkPbrbm;
    }

    // tif ibndlfs brf output pbrbms only, no nffd to fftdi tifm from Jbvb
    dkPbrbm.pRfturnfdKfyMbtfribl->iClifntMbdSfdrft = 0;
    dkPbrbm.pRfturnfdKfyMbtfribl->iSfrvfrMbdSfdrft = 0;
    dkPbrbm.pRfturnfdKfyMbtfribl->iClifntKfy = 0;
    dkPbrbm.pRfturnfdKfyMbtfribl->iSfrvfrKfy = 0;

    jBytfArrbyToCKBytfArrby(fnv, jRMIvClifnt, &(dkPbrbm.pRfturnfdKfyMbtfribl->pIVClifnt), &dkTfmp);
    if ((*fnv)->ExdfptionCifdk(fnv)) {
        frff(dkPbrbm.RbndomInfo.pClifntRbndom);
        frff(dkPbrbm.RbndomInfo.pSfrvfrRbndom);
        frff(dkPbrbm.pRfturnfdKfyMbtfribl);
        rfturn dkPbrbm;
    }
    jBytfArrbyToCKBytfArrby(fnv, jRMIvSfrvfr, &(dkPbrbm.pRfturnfdKfyMbtfribl->pIVSfrvfr), &dkTfmp);
    if ((*fnv)->ExdfptionCifdk(fnv)) {
        frff(dkPbrbm.RbndomInfo.pClifntRbndom);
        frff(dkPbrbm.RbndomInfo.pSfrvfrRbndom);
        frff(dkPbrbm.pRfturnfdKfyMbtfribl->pIVClifnt);
        frff(dkPbrbm.pRfturnfdKfyMbtfribl);
        rfturn dkPbrbm;
    }

    rfturn dkPbrbm ;
}

/*
 * donvfrts tif Jbvb CK_AES_CTR_PARAMS objfdt to b CK_AES_CTR_PARAMS strudturf
 *
 * @pbrbm fnv - usfd to dbll JNI funktions to gft tif Jbvb dlbssfs bnd objfdts
 * @pbrbm jPbrbm - tif Jbvb CK_AES_CTR_PARAMS objfdt to donvfrt
 * @pbrbm dkpPbrbm - pointfr to tif nfw CK_AES_CTR_PARAMS strudturf
 */
void jAfsCtrPbrbmsToCKAfsCtrPbrbm(JNIEnv *fnv, jobjfdt jPbrbm,
                                  CK_AES_CTR_PARAMS_PTR dkpPbrbm) {
    jdlbss jAfsCtrPbrbmsClbss;
    jfifldID fifldID;
    jlong jCountfrBits;
    jobjfdt jCb;
    CK_BYTE_PTR dkBytfs;
    CK_ULONG dkTfmp;

    /* gft ulCountfrBits */
    jAfsCtrPbrbmsClbss = (*fnv)->FindClbss(fnv, CLASS_AES_CTR_PARAMS);
    if (jAfsCtrPbrbmsClbss == NULL) { rfturn; }
    fifldID = (*fnv)->GftFifldID(fnv, jAfsCtrPbrbmsClbss, "ulCountfrBits", "J");
    if (fifldID == NULL) { rfturn; }
    jCountfrBits = (*fnv)->GftLongFifld(fnv, jPbrbm, fifldID);

    /* gft db */
    fifldID = (*fnv)->GftFifldID(fnv, jAfsCtrPbrbmsClbss, "db", "[B");
    if (fifldID == NULL) { rfturn; }
    jCb = (*fnv)->GftObjfdtFifld(fnv, jPbrbm, fifldID);

    /* populbtf jbvb vblufs */
    dkpPbrbm->ulCountfrBits = jLongToCKULong(jCountfrBits);
    jBytfArrbyToCKBytfArrby(fnv, jCb, &dkBytfs, &dkTfmp);
    if ((*fnv)->ExdfptionCifdk(fnv)) { rfturn; }
    if (dkTfmp != 16) {
        TRACE1("ERROR: WRONG CTR IV LENGTH %d", dkTfmp);
    } flsf {
        mfmdpy(dkpPbrbm->db, dkBytfs, dkTfmp);
        frff(dkBytfs);
    }
}

/*
 * donvfrts b Jbvb CK_MECHANISM objfdt into b CK_MECHANISM strudturf
 *
 * @pbrbm fnv - usfd to dbll JNI funktions to gft tif vblufs out of tif Jbvb objfdt
 * @pbrbm jMfdibnism - tif Jbvb CK_MECHANISM objfdt to donvfrt
 * @rfturn - tif nfw CK_MECHANISM strudturf
 */
void jMfdibnismToCKMfdibnism(JNIEnv *fnv, jobjfdt jMfdibnism, CK_MECHANISM_PTR dkMfdibnismPtr)
{
    jlong jMfdibnismTypf = (*fnv)->GftLongFifld(fnv, jMfdibnism, mfdi_mfdibnismID);
    jobjfdt jPbrbmftfr = (*fnv)->GftObjfdtFifld(fnv, jMfdibnism, mfdi_pPbrbmftfrID);

    (*dkMfdibnismPtr).mfdibnism = jLongToCKULong(jMfdibnismTypf);

    /* donvfrt tif spfdifid Jbvb mfdibnism pbrbmftfr objfdt to b pointfr to b CK-typf mfdibnism
     * strudturf
     */
    if (jPbrbmftfr == NULL) {
        (*dkMfdibnismPtr).pPbrbmftfr = NULL;
        (*dkMfdibnismPtr).ulPbrbmftfrLfn = 0;
    } flsf {
        jMfdibnismPbrbmftfrToCKMfdibnismPbrbmftfr(fnv, jPbrbmftfr, &(*dkMfdibnismPtr).pPbrbmftfr, &(*dkMfdibnismPtr).ulPbrbmftfrLfn);
    }
}

/*
 * tif following fundtions donvfrt Attributf bnd Mfdibnism vbluf pointfrs
 *
 * jobjfdt dkAttributfVblufToJObjfdt(JNIEnv *fnv,
 *                                   donst CK_ATTRIBUTE_PTR dkpAttributf);
 *
 * void jObjfdtToPrimitivfCKObjfdtPtrPtr(JNIEnv *fnv,
 *                                       jobjfdt jObjfdt,
 *                                       CK_VOID_PTR *dkpObjfdtPtr,
 *                                       CK_ULONG *pLfngti);
 *
 * void jMfdibnismPbrbmftfrToCKMfdibnismPbrbmftfr(JNIEnv *fnv,
 *                                                jobjfdt jPbrbm,
 *                                                CK_VOID_PTR *dkpPbrbmPtr,
 *                                                CK_ULONG *dkpLfngti);
 *
 * Tifsf fundtions brf usfd if b PKCS#11 mfdibnism or bttributf strudturf gfts
 * donvfrtft to b Jbvb bttributf or mfdibnism objfdt or vidf vfrsb.
 *
 * dkAttributfVblufToJObjfdt donvfrts b PKCS#11 bttributf vbluf pointfr to b Jbvb
 * objfdt dfpfnding on tif typf of tif Attributf. A PKCS#11 bttributf vbluf dbn
 * bf b CK_ULONG, CK_BYTE[], CK_CHAR[], big intfgfr, CK_BBOOL, CK_UTF8CHAR[],
 * CK_DATE or CK_FLAGS tibt gfts donvfrtfd to b dorrfsponding Jbvb objfdt.
 *
 * jObjfdtToPrimitivfCKObjfdtPtrPtr is usfd by jAttributfToCKAttributfPtr for
 * donvfrting tif Jbvb bttributf vbluf to b PKCS#11 bttributf vbluf pointfr.
 * For now only primitivf dbtbtypfs bnd brrbys of primitivf dbtbtypfs dbn gft
 * donvfrtfd. Otifrwisf tiis fundtion tirows b PKCS#11Exdfption witi tif
 * frrordodf CKR_VENDOR_DEFINED.
 *
 * jMfdibnismPbrbmftfrToCKMfdibnismPbrbmftfr donvfrts b Jbvb mfdibnism pbrbmftfr
 * to b PKCS#11 mfdibnism pbrbmftfr. First tiis fundtion dftfrminfs wibt mfdibnism
 * pbrbmftfr tif Jbvb objfdt is, tifn it bllodbtfs tif mfmory for tif nfw PKCS#11
 * strudturf bnd dblls tif dorrfsponding fundtion to donvfrt tif Jbvb objfdt to
 * b PKCS#11 mfdibnism pbrbmftfr strudturf.
 */

/*
 * donvfrts tif pVbluf of b CK_ATTRIBUTE strudturf into b Jbvb Objfdt by difdking tif typf
 * of tif bttributf.
 *
 * @pbrbm fnv - usfd to dbll JNI funktions to drfbtf tif nfw Jbvb objfdt
 * @pbrbm dkpAttributf - tif pointfr to tif CK_ATTRIBUTE strudturf tibt dontbins tif typf
 *                       bnd tif pVbluf to donvfrt
 * @rfturn - tif nfw Jbvb objfdt of tif CK-typf pVbluf
 */
jobjfdt dkAttributfVblufToJObjfdt(JNIEnv *fnv, donst CK_ATTRIBUTE_PTR dkpAttributf)
{
    jint jVblufLfngti;
    jobjfdt jVblufObjfdt = NULL;

    jVblufLfngti = dkULongToJInt(dkpAttributf->ulVblufLfn);

    if ((jVblufLfngti <= 0) || (dkpAttributf->pVbluf == NULL)) {
        rfturn NULL ;
    }

    switdi(dkpAttributf->typf) {
        dbsf CKA_CLASS:
            /* vbluf CK_OBJECT_CLASS, dffbdto b CK_ULONG */
        dbsf CKA_KEY_TYPE:
            /* vbluf CK_KEY_TYPE, dffbdto b CK_ULONG */
        dbsf CKA_CERTIFICATE_TYPE:
            /* vbluf CK_CERTIFICATE_TYPE, dffbdto b CK_ULONG */
        dbsf CKA_HW_FEATURE_TYPE:
            /* vbluf CK_HW_FEATURE_TYPE, dffbdto b CK_ULONG */
        dbsf CKA_MODULUS_BITS:
        dbsf CKA_VALUE_BITS:
        dbsf CKA_VALUE_LEN:
        dbsf CKA_KEY_GEN_MECHANISM:
        dbsf CKA_PRIME_BITS:
        dbsf CKA_SUB_PRIME_BITS:
            /* vbluf CK_ULONG */
            jVblufObjfdt = dkULongPtrToJLongObjfdt(fnv, (CK_ULONG*) dkpAttributf->pVbluf);
            brfbk;

            /* dbn bf CK_BYTE[],CK_CHAR[] or big intfgfr; dffbdto blwbys CK_BYTE[] */
        dbsf CKA_VALUE:
        dbsf CKA_OBJECT_ID:
        dbsf CKA_SUBJECT:
        dbsf CKA_ID:
        dbsf CKA_ISSUER:
        dbsf CKA_SERIAL_NUMBER:
        dbsf CKA_OWNER:
        dbsf CKA_AC_ISSUER:
        dbsf CKA_ATTR_TYPES:
        dbsf CKA_ECDSA_PARAMS:
            /* CKA_EC_PARAMS is tif sbmf, tifsf two brf fquivblfnt */
        dbsf CKA_EC_POINT:
        dbsf CKA_PRIVATE_EXPONENT:
        dbsf CKA_PRIME_1:
        dbsf CKA_PRIME_2:
        dbsf CKA_EXPONENT_1:
        dbsf CKA_EXPONENT_2:
        dbsf CKA_COEFFICIENT:
            /* vbluf CK_BYTE[] */
            jVblufObjfdt = dkBytfArrbyToJBytfArrby(fnv, (CK_BYTE*) dkpAttributf->pVbluf, jVblufLfngti);
            brfbk;

        dbsf CKA_RESET_ON_INIT:
        dbsf CKA_HAS_RESET:
        dbsf CKA_TOKEN:
        dbsf CKA_PRIVATE:
        dbsf CKA_MODIFIABLE:
        dbsf CKA_DERIVE:
        dbsf CKA_LOCAL:
        dbsf CKA_ENCRYPT:
        dbsf CKA_VERIFY:
        dbsf CKA_VERIFY_RECOVER:
        dbsf CKA_WRAP:
        dbsf CKA_SENSITIVE:
        dbsf CKA_SECONDARY_AUTH:
        dbsf CKA_DECRYPT:
        dbsf CKA_SIGN:
        dbsf CKA_SIGN_RECOVER:
        dbsf CKA_UNWRAP:
        dbsf CKA_EXTRACTABLE:
        dbsf CKA_ALWAYS_SENSITIVE:
        dbsf CKA_NEVER_EXTRACTABLE:
        dbsf CKA_TRUSTED:
            /* vbluf CK_BBOOL */
            jVblufObjfdt = dkBBoolPtrToJBoolfbnObjfdt(fnv, (CK_BBOOL*) dkpAttributf->pVbluf);
            brfbk;

        dbsf CKA_LABEL:
        dbsf CKA_APPLICATION:
            /* vbluf RFC 2279 (UTF-8) string */
            jVblufObjfdt = dkUTF8CibrArrbyToJCibrArrby(fnv, (CK_UTF8CHAR*) dkpAttributf->pVbluf, jVblufLfngti);
            brfbk;

        dbsf CKA_START_DATE:
        dbsf CKA_END_DATE:
            /* vbluf CK_DATE */
            jVblufObjfdt = dkDbtfPtrToJDbtfObjfdt(fnv, (CK_DATE*) dkpAttributf->pVbluf);
            brfbk;

        dbsf CKA_MODULUS:
        dbsf CKA_PUBLIC_EXPONENT:
        dbsf CKA_PRIME:
        dbsf CKA_SUBPRIME:
        dbsf CKA_BASE:
            /* vbluf big intfgfr, i.f. CK_BYTE[] */
            jVblufObjfdt = dkBytfArrbyToJBytfArrby(fnv, (CK_BYTE*) dkpAttributf->pVbluf, jVblufLfngti);
            brfbk;

        dbsf CKA_AUTH_PIN_FLAGS:
            jVblufObjfdt = dkULongPtrToJLongObjfdt(fnv, (CK_ULONG*) dkpAttributf->pVbluf);
            /* vbluf FLAGS, dffbdto b CK_ULONG */
            brfbk;

        dbsf CKA_VENDOR_DEFINED:
            /* wf mbkf b CK_BYTE[] out of tiis */
            jVblufObjfdt = dkBytfArrbyToJBytfArrby(fnv, (CK_BYTE*) dkpAttributf->pVbluf, jVblufLfngti);
            brfbk;

        // Nftsdbpf trust bttributfs
        dbsf CKA_NETSCAPE_TRUST_SERVER_AUTH:
        dbsf CKA_NETSCAPE_TRUST_CLIENT_AUTH:
        dbsf CKA_NETSCAPE_TRUST_CODE_SIGNING:
        dbsf CKA_NETSCAPE_TRUST_EMAIL_PROTECTION:
            /* vbluf CK_ULONG */
            jVblufObjfdt = dkULongPtrToJLongObjfdt(fnv, (CK_ULONG*) dkpAttributf->pVbluf);
            brfbk;

        dffbult:
            /* wf mbkf b CK_BYTE[] out of tiis */
            jVblufObjfdt = dkBytfArrbyToJBytfArrby(fnv, (CK_BYTE*) dkpAttributf->pVbluf, jVblufLfngti);
            brfbk;
    }

    rfturn jVblufObjfdt ;
}

/*
 * tif following fundtions donvfrt b Jbvb mfdibnism pbrbmftfr objfdt to b PKCS#11
 * mfdibnism pbrbmftfr strudturf
 *
 * CK_<Pbrbm>_PARAMS j<Pbrbm>PbrbmToCK<Pbrbm>Pbrbm(JNIEnv *fnv,
 *                                                 jobjfdt jPbrbm);
 *
 * Tifsf fundtions gft b Jbvb objfdt, tibt must bf tif rigit Jbvb mfdibnism
 * objfdt bnd tify rfturn tif nfw PKCS#11 mfdibnism pbrbmftfr strudturf.
 * Evfry fifld of tif Jbvb objfdt is rftrifvfd, gfts donvfrtfd to b dorrfsponding
 * PKCS#11 typf bnd is sft in tif nfw PKCS#11 strudturf.
 */

/*
 * donvfrts tif givfn Jbvb mfdibnism pbrbmftfr to b CK mfdibnism pbrbmftfr strudturf
 * bnd storf tif lfngti in bytfs in tif lfngti vbribblf.
 * Tif mfmory of *dkpPbrbmPtr ibs to bf frffd bftfr usf!
 *
 * @pbrbm fnv - usfd to dbll JNI funktions to gft tif Jbvb dlbssfs bnd objfdts
 * @pbrbm jPbrbm - tif Jbvb mfdibnism pbrbmftfr objfdt to donvfrt
 * @pbrbm dkpPbrbmPtr - tif rfffrfndf of tif nfw pointfr to tif nfw CK mfdibnism pbrbmftfr
 *                      strudturf
 * @pbrbm dkpLfngti - tif rfffrfndf of tif lfngti in bytfs of tif nfw CK mfdibnism pbrbmftfr
 *                    strudturf
 */
void jMfdibnismPbrbmftfrToCKMfdibnismPbrbmftfr(JNIEnv *fnv, jobjfdt jPbrbm, CK_VOID_PTR *dkpPbrbmPtr, CK_ULONG *dkpLfngti)
{
    if (jPbrbm == NULL) {
        *dkpPbrbmPtr = NULL;
        *dkpLfngti = 0;
    } flsf if ((*fnv)->IsInstbndfOf(fnv, jPbrbm, jBytfArrbyClbss)) {
        jBytfArrbyToCKBytfArrby(fnv, jPbrbm, (CK_BYTE_PTR *)dkpPbrbmPtr, dkpLfngti);
    } flsf if ((*fnv)->IsInstbndfOf(fnv, jPbrbm, jLongClbss)) {
        *dkpPbrbmPtr = jLongObjfdtToCKULongPtr(fnv, jPbrbm);
        *dkpLfngti = sizfof(CK_ULONG);
    } flsf {
        TRACE0("\nSLOW PATH jMfdibnismPbrbmftfrToCKMfdibnismPbrbmftfr\n");
        jMfdibnismPbrbmftfrToCKMfdibnismPbrbmftfrSlow(fnv, jPbrbm, dkpPbrbmPtr, dkpLfngti);
    }
}

void jMfdibnismPbrbmftfrToCKMfdibnismPbrbmftfrSlow(JNIEnv *fnv, jobjfdt jPbrbm, CK_VOID_PTR *dkpPbrbmPtr, CK_ULONG *dkpLfngti)
{
    /* gft bll Jbvb mfdibnism pbrbmftfr dlbssfs */
    jdlbss jVfrsionClbss, jSsl3MbstfrKfyDfrivfPbrbmsClbss, jSsl3KfyMbtPbrbmsClbss;
    jdlbss jTlsPrfPbrbmsClbss, jAfsCtrPbrbmsClbss, jRsbPkdsObfpPbrbmsClbss;
    jdlbss jPbfPbrbmsClbss, jPkds5Pbkd2PbrbmsClbss, jRsbPkdsPssPbrbmsClbss;
    jdlbss jEddi1DfrivfPbrbmsClbss, jEddi2DfrivfPbrbmsClbss;
    jdlbss jX942Di1DfrivfPbrbmsClbss, jX942Di2DfrivfPbrbmsClbss;
    TRACE0("\nDEBUG: jMfdibnismPbrbmftfrToCKMfdibnismPbrbmftfr");

    /* most dommon dbsfs, i.f. NULL/bytf[]/long, brf blrfbdy ibndlfd by
     * jMfdibnismPbrbmftfrToCKMfdibnismPbrbmftfr bfforf dblling tiis mftiod.
     */
    jVfrsionClbss = (*fnv)->FindClbss(fnv, CLASS_VERSION);
    if (jVfrsionClbss == NULL) { rfturn; }
    if ((*fnv)->IsInstbndfOf(fnv, jPbrbm, jVfrsionClbss)) {
        /*
         * CK_VERSION usfd by CKM_SSL3_PRE_MASTER_KEY_GEN
         */
        CK_VERSION_PTR dkpPbrbm;

        /* donvfrt jPbrbmftfr to CKPbrbmftfr */
        dkpPbrbm = jVfrsionToCKVfrsionPtr(fnv, jPbrbm);

        /* gft lfngti bnd pointfr of pbrbmftfr */
        *dkpLfngti = sizfof(CK_VERSION);
        *dkpPbrbmPtr = dkpPbrbm;
        rfturn;
    }

    jSsl3MbstfrKfyDfrivfPbrbmsClbss = (*fnv)->FindClbss(fnv, CLASS_SSL3_MASTER_KEY_DERIVE_PARAMS);
    if (jSsl3MbstfrKfyDfrivfPbrbmsClbss == NULL) { rfturn; }
    if ((*fnv)->IsInstbndfOf(fnv, jPbrbm, jSsl3MbstfrKfyDfrivfPbrbmsClbss)) {
        /*
         * CK_SSL3_MASTER_KEY_DERIVE_PARAMS
         */
        CK_SSL3_MASTER_KEY_DERIVE_PARAMS_PTR dkpPbrbm;

        dkpPbrbm = (CK_SSL3_MASTER_KEY_DERIVE_PARAMS_PTR) mbllod(sizfof(CK_SSL3_MASTER_KEY_DERIVE_PARAMS));
        if (dkpPbrbm == NULL) {
            tirowOutOfMfmoryError(fnv, 0);
            rfturn;
        }

        /* donvfrt jPbrbmftfr to CKPbrbmftfr */
        *dkpPbrbm = jSsl3MbstfrKfyDfrivfPbrbmToCKSsl3MbstfrKfyDfrivfPbrbm(fnv, jPbrbm);
        if ((*fnv)->ExdfptionCifdk(fnv)) {
            frff(dkpPbrbm);
            rfturn;
        }

        /* gft lfngti bnd pointfr of pbrbmftfr */
        *dkpLfngti = sizfof(CK_SSL3_MASTER_KEY_DERIVE_PARAMS);
        *dkpPbrbmPtr = dkpPbrbm;
        rfturn;
    }

    jSsl3KfyMbtPbrbmsClbss = (*fnv)->FindClbss(fnv, CLASS_SSL3_KEY_MAT_PARAMS);
    if (jSsl3KfyMbtPbrbmsClbss == NULL) { rfturn; }
    if ((*fnv)->IsInstbndfOf(fnv, jPbrbm, jSsl3KfyMbtPbrbmsClbss)) {
        /*
         * CK_SSL3_KEY_MAT_PARAMS
         */
        CK_SSL3_KEY_MAT_PARAMS_PTR dkpPbrbm;

        dkpPbrbm = (CK_SSL3_KEY_MAT_PARAMS_PTR) mbllod(sizfof(CK_SSL3_KEY_MAT_PARAMS));
        if (dkpPbrbm == NULL) {
            tirowOutOfMfmoryError(fnv, 0);
            rfturn;
        }

        /* donvfrt jPbrbmftfr to CKPbrbmftfr */
        *dkpPbrbm = jSsl3KfyMbtPbrbmToCKSsl3KfyMbtPbrbm(fnv, jPbrbm);
        if ((*fnv)->ExdfptionCifdk(fnv)) {
            frff(dkpPbrbm);
            rfturn;
        }

        /* gft lfngti bnd pointfr of pbrbmftfr */
        *dkpLfngti = sizfof(CK_SSL3_KEY_MAT_PARAMS);
        *dkpPbrbmPtr = dkpPbrbm;
        rfturn;
    }

    jTlsPrfPbrbmsClbss = (*fnv)->FindClbss(fnv, CLASS_TLS_PRF_PARAMS);
    if (jTlsPrfPbrbmsClbss == NULL) { rfturn; }
    if ((*fnv)->IsInstbndfOf(fnv, jPbrbm, jTlsPrfPbrbmsClbss)) {
        /*
         * CK_TLS_PRF_PARAMS
         */
        CK_TLS_PRF_PARAMS_PTR dkpPbrbm;

        dkpPbrbm = (CK_TLS_PRF_PARAMS_PTR) mbllod(sizfof(CK_TLS_PRF_PARAMS));
        if (dkpPbrbm == NULL) {
            tirowOutOfMfmoryError(fnv, 0);
            rfturn;
        }

        /* donvfrt jPbrbmftfr to CKPbrbmftfr */
        *dkpPbrbm = jTlsPrfPbrbmsToCKTlsPrfPbrbm(fnv, jPbrbm);
        if ((*fnv)->ExdfptionCifdk(fnv)) {
            frff(dkpPbrbm);
            rfturn;
        }

        /* gft lfngti bnd pointfr of pbrbmftfr */
        *dkpLfngti = sizfof(CK_TLS_PRF_PARAMS);
        *dkpPbrbmPtr = dkpPbrbm;
        rfturn;
    }

    jAfsCtrPbrbmsClbss = (*fnv)->FindClbss(fnv, CLASS_AES_CTR_PARAMS);
    if (jAfsCtrPbrbmsClbss == NULL) { rfturn; }
    if ((*fnv)->IsInstbndfOf(fnv, jPbrbm, jAfsCtrPbrbmsClbss)) {
        /*
         * CK_AES_CTR_PARAMS
         */
        CK_AES_CTR_PARAMS_PTR dkpPbrbm;

        dkpPbrbm = (CK_AES_CTR_PARAMS_PTR) mbllod(sizfof(CK_AES_CTR_PARAMS));
        if (dkpPbrbm == NULL) {
            tirowOutOfMfmoryError(fnv, 0);
            rfturn;
        }

        /* donvfrt jPbrbmftfr to CKPbrbmftfr */
        jAfsCtrPbrbmsToCKAfsCtrPbrbm(fnv, jPbrbm, dkpPbrbm);
        if ((*fnv)->ExdfptionCifdk(fnv)) {
            frff(dkpPbrbm);
            rfturn;
        }

        /* gft lfngti bnd pointfr of pbrbmftfr */
        *dkpLfngti = sizfof(CK_AES_CTR_PARAMS);
        *dkpPbrbmPtr = dkpPbrbm;
        rfturn;
    }

    jRsbPkdsObfpPbrbmsClbss = (*fnv)->FindClbss(fnv, CLASS_RSA_PKCS_OAEP_PARAMS);
    if (jRsbPkdsObfpPbrbmsClbss == NULL) { rfturn; }
    if ((*fnv)->IsInstbndfOf(fnv, jPbrbm, jRsbPkdsObfpPbrbmsClbss)) {
        /*
         * CK_RSA_PKCS_OAEP_PARAMS
         */
        CK_RSA_PKCS_OAEP_PARAMS_PTR dkpPbrbm;

        dkpPbrbm = (CK_RSA_PKCS_OAEP_PARAMS_PTR) mbllod(sizfof(CK_RSA_PKCS_OAEP_PARAMS));
        if (dkpPbrbm == NULL) {
            tirowOutOfMfmoryError(fnv, 0);
            rfturn;
        }

        /* donvfrt jPbrbmftfr to CKPbrbmftfr */
        *dkpPbrbm = jRsbPkdsObfpPbrbmToCKRsbPkdsObfpPbrbm(fnv, jPbrbm);
        if ((*fnv)->ExdfptionCifdk(fnv)) {
            frff(dkpPbrbm);
            rfturn;
        }

        /* gft lfngti bnd pointfr of pbrbmftfr */
        *dkpLfngti = sizfof(CK_RSA_PKCS_OAEP_PARAMS);
        *dkpPbrbmPtr = dkpPbrbm;
        rfturn;
    }

    jPbfPbrbmsClbss = (*fnv)->FindClbss(fnv, CLASS_PBE_PARAMS);
    if (jPbfPbrbmsClbss == NULL) { rfturn; }
    if ((*fnv)->IsInstbndfOf(fnv, jPbrbm, jPbfPbrbmsClbss)) {
        /*
         * CK_PBE_PARAMS
         */
        CK_PBE_PARAMS_PTR dkpPbrbm;

        dkpPbrbm = (CK_PBE_PARAMS_PTR) mbllod(sizfof(CK_PBE_PARAMS));
        if (dkpPbrbm == NULL) {
            tirowOutOfMfmoryError(fnv, 0);
            rfturn;
        }

        /* donvfrt jPbrbmftfr to CKPbrbmftfr */
        *dkpPbrbm = jPbfPbrbmToCKPbfPbrbm(fnv, jPbrbm);
        if ((*fnv)->ExdfptionCifdk(fnv)) {
            frff(dkpPbrbm);
            rfturn;
        }

        /* gft lfngti bnd pointfr of pbrbmftfr */
        *dkpLfngti = sizfof(CK_PBE_PARAMS);
        *dkpPbrbmPtr = dkpPbrbm;
        rfturn;
    }

    jPkds5Pbkd2PbrbmsClbss = (*fnv)->FindClbss(fnv, CLASS_PKCS5_PBKD2_PARAMS);
    if (jPkds5Pbkd2PbrbmsClbss == NULL) { rfturn; }
    if ((*fnv)->IsInstbndfOf(fnv, jPbrbm, jPkds5Pbkd2PbrbmsClbss)) {
        /*
         * CK_PKCS5_PBKD2_PARAMS
         */
        CK_PKCS5_PBKD2_PARAMS_PTR dkpPbrbm;

        dkpPbrbm = (CK_PKCS5_PBKD2_PARAMS_PTR) mbllod(sizfof(CK_PKCS5_PBKD2_PARAMS));
        if (dkpPbrbm == NULL) {
            tirowOutOfMfmoryError(fnv, 0);
            rfturn;
        }

        /* donvfrt jPbrbmftfr to CKPbrbmftfr */
        *dkpPbrbm = jPkds5Pbkd2PbrbmToCKPkds5Pbkd2Pbrbm(fnv, jPbrbm);
        if ((*fnv)->ExdfptionCifdk(fnv)) {
            frff(dkpPbrbm);
            rfturn;
        }

        /* gft lfngti bnd pointfr of pbrbmftfr */
        *dkpLfngti = sizfof(CK_PKCS5_PBKD2_PARAMS);
        *dkpPbrbmPtr = dkpPbrbm;
        rfturn;
    }

    jRsbPkdsPssPbrbmsClbss = (*fnv)->FindClbss(fnv, CLASS_RSA_PKCS_PSS_PARAMS);
    if (jRsbPkdsPssPbrbmsClbss == NULL) { rfturn; }
    if ((*fnv)->IsInstbndfOf(fnv, jPbrbm, jRsbPkdsPssPbrbmsClbss)) {
        /*
         * CK_RSA_PKCS_PSS_PARAMS
         */
        CK_RSA_PKCS_PSS_PARAMS_PTR dkpPbrbm;

        dkpPbrbm = (CK_RSA_PKCS_PSS_PARAMS_PTR) mbllod(sizfof(CK_RSA_PKCS_PSS_PARAMS));
        if (dkpPbrbm == NULL) {
            tirowOutOfMfmoryError(fnv, 0);
            rfturn;
        }

        /* donvfrt jPbrbmftfr to CKPbrbmftfr */
        *dkpPbrbm = jRsbPkdsPssPbrbmToCKRsbPkdsPssPbrbm(fnv, jPbrbm);
        if ((*fnv)->ExdfptionCifdk(fnv)) {
            frff(dkpPbrbm);
            rfturn;
        }

        /* gft lfngti bnd pointfr of pbrbmftfr */
        *dkpLfngti = sizfof(CK_RSA_PKCS_PSS_PARAMS);
        *dkpPbrbmPtr = dkpPbrbm;
        rfturn;
    }

    jEddi1DfrivfPbrbmsClbss = (*fnv)->FindClbss(fnv, CLASS_ECDH1_DERIVE_PARAMS);
    if (jEddi1DfrivfPbrbmsClbss == NULL) { rfturn; }
    if ((*fnv)->IsInstbndfOf(fnv, jPbrbm, jEddi1DfrivfPbrbmsClbss)) {
        /*
         * CK_ECDH1_DERIVE_PARAMS
         */
        CK_ECDH1_DERIVE_PARAMS_PTR dkpPbrbm;

        dkpPbrbm = (CK_ECDH1_DERIVE_PARAMS_PTR) mbllod(sizfof(CK_ECDH1_DERIVE_PARAMS));
        if (dkpPbrbm == NULL) {
            tirowOutOfMfmoryError(fnv, 0);
            rfturn;
        }

        /* donvfrt jPbrbmftfr to CKPbrbmftfr */
        *dkpPbrbm = jEddi1DfrivfPbrbmToCKEddi1DfrivfPbrbm(fnv, jPbrbm);
        if ((*fnv)->ExdfptionCifdk(fnv)) {
            frff(dkpPbrbm);
            rfturn;
        }

        /* gft lfngti bnd pointfr of pbrbmftfr */
        *dkpLfngti = sizfof(CK_ECDH1_DERIVE_PARAMS);
        *dkpPbrbmPtr = dkpPbrbm;
        rfturn;
    }

    jEddi2DfrivfPbrbmsClbss = (*fnv)->FindClbss(fnv, CLASS_ECDH2_DERIVE_PARAMS);
    if (jEddi2DfrivfPbrbmsClbss == NULL) { rfturn; }
    if ((*fnv)->IsInstbndfOf(fnv, jPbrbm, jEddi2DfrivfPbrbmsClbss)) {
        /*
         * CK_ECDH2_DERIVE_PARAMS
         */
        CK_ECDH2_DERIVE_PARAMS_PTR dkpPbrbm;

        dkpPbrbm = (CK_ECDH2_DERIVE_PARAMS_PTR) mbllod(sizfof(CK_ECDH2_DERIVE_PARAMS));
        if (dkpPbrbm == NULL) {
            tirowOutOfMfmoryError(fnv, 0);
            rfturn;
        }

        /* donvfrt jPbrbmftfr to CKPbrbmftfr */
        *dkpPbrbm = jEddi2DfrivfPbrbmToCKEddi2DfrivfPbrbm(fnv, jPbrbm);
        if ((*fnv)->ExdfptionCifdk(fnv)) {
            frff(dkpPbrbm);
            rfturn;
        }

        /* gft lfngti bnd pointfr of pbrbmftfr */
        *dkpLfngti = sizfof(CK_ECDH2_DERIVE_PARAMS);
        *dkpPbrbmPtr = dkpPbrbm;
        rfturn;
    }

    jX942Di1DfrivfPbrbmsClbss = (*fnv)->FindClbss(fnv, CLASS_X9_42_DH1_DERIVE_PARAMS);
    if (jX942Di1DfrivfPbrbmsClbss == NULL) { rfturn; }
    if ((*fnv)->IsInstbndfOf(fnv, jPbrbm, jX942Di1DfrivfPbrbmsClbss)) {
        /*
         * CK_X9_42_DH1_DERIVE_PARAMS
         */
        CK_X9_42_DH1_DERIVE_PARAMS_PTR dkpPbrbm;

        dkpPbrbm = (CK_X9_42_DH1_DERIVE_PARAMS_PTR) mbllod(sizfof(CK_X9_42_DH1_DERIVE_PARAMS));
        if (dkpPbrbm == NULL) {
            tirowOutOfMfmoryError(fnv, 0);
            rfturn;
        }

        /* donvfrt jPbrbmftfr to CKPbrbmftfr */
        *dkpPbrbm = jX942Di1DfrivfPbrbmToCKX942Di1DfrivfPbrbm(fnv, jPbrbm);
        if ((*fnv)->ExdfptionCifdk(fnv)) {
            frff(dkpPbrbm);
            rfturn;
        }

        /* gft lfngti bnd pointfr of pbrbmftfr */
        *dkpLfngti = sizfof(CK_X9_42_DH1_DERIVE_PARAMS);
        *dkpPbrbmPtr = dkpPbrbm;
        rfturn;
    }

    jX942Di2DfrivfPbrbmsClbss = (*fnv)->FindClbss(fnv, CLASS_X9_42_DH2_DERIVE_PARAMS);
    if (jX942Di2DfrivfPbrbmsClbss == NULL) { rfturn; }
    if ((*fnv)->IsInstbndfOf(fnv, jPbrbm, jX942Di2DfrivfPbrbmsClbss)) {
        /*
         * CK_X9_42_DH2_DERIVE_PARAMS
         */
        CK_X9_42_DH2_DERIVE_PARAMS_PTR dkpPbrbm;

        dkpPbrbm = (CK_X9_42_DH2_DERIVE_PARAMS_PTR) mbllod(sizfof(CK_X9_42_DH2_DERIVE_PARAMS));
        if (dkpPbrbm == NULL) {
            tirowOutOfMfmoryError(fnv, 0);
            rfturn;
        }

        /* donvfrt jPbrbmftfr to CKPbrbmftfr */
        *dkpPbrbm = jX942Di2DfrivfPbrbmToCKX942Di2DfrivfPbrbm(fnv, jPbrbm);
        if ((*fnv)->ExdfptionCifdk(fnv)) {
            frff(dkpPbrbm);
            rfturn;
        }

        /* gft lfngti bnd pointfr of pbrbmftfr */
        *dkpLfngti = sizfof(CK_X9_42_DH2_DERIVE_PARAMS);
        *dkpPbrbmPtr = dkpPbrbm;
        rfturn;
    }

    /* if fvfrytiing fbild up to ifrf */
    /* try if tif pbrbmftfr is b primitivf Jbvb typf */
    jObjfdtToPrimitivfCKObjfdtPtrPtr(fnv, jPbrbm, dkpPbrbmPtr, dkpLfngti);
    /* *dkpPbrbmPtr = jObjfdtToCKVoidPtr(jPbrbm); */
    /* *dkpLfngti = 1; */

    TRACE0("FINISHED\n");
}


/* tif mfdibnism pbrbmftfr donvfrtion fundtions: */

/*
 * donvfrts tif Jbvb CK_RSA_PKCS_OAEP_PARAMS objfdt to b CK_RSA_PKCS_OAEP_PARAMS strudturf
 *
 * @pbrbm fnv - usfd to dbll JNI funktions to gft tif Jbvb dlbssfs bnd objfdts
 * @pbrbm jPbrbm - tif Jbvb CK_RSA_PKCS_OAEP_PARAMS objfdt to donvfrt
 * @rfturn - tif nfw CK_RSA_PKCS_OAEP_PARAMS strudturf
 */
CK_RSA_PKCS_OAEP_PARAMS jRsbPkdsObfpPbrbmToCKRsbPkdsObfpPbrbm(JNIEnv *fnv, jobjfdt jPbrbm)
{
    jdlbss jRsbPkdsObfpPbrbmsClbss;
    CK_RSA_PKCS_OAEP_PARAMS dkPbrbm;
    jfifldID fifldID;
    jlong jHbsiAlg, jMgf, jSourdf;
    jobjfdt jSourdfDbtb;
    CK_BYTE_PTR dkpBytf;

    /* gft ibsiAlg */
    jRsbPkdsObfpPbrbmsClbss = (*fnv)->FindClbss(fnv, CLASS_RSA_PKCS_OAEP_PARAMS);
    if (jRsbPkdsObfpPbrbmsClbss == NULL) { rfturn dkPbrbm; }
    fifldID = (*fnv)->GftFifldID(fnv, jRsbPkdsObfpPbrbmsClbss, "ibsiAlg", "J");
    if (fifldID == NULL) { rfturn dkPbrbm; }
    jHbsiAlg = (*fnv)->GftLongFifld(fnv, jPbrbm, fifldID);

    /* gft mgf */
    fifldID = (*fnv)->GftFifldID(fnv, jRsbPkdsObfpPbrbmsClbss, "mgf", "J");
    if (fifldID == NULL) { rfturn dkPbrbm; }
    jMgf = (*fnv)->GftLongFifld(fnv, jPbrbm, fifldID);

    /* gft sourdf */
    fifldID = (*fnv)->GftFifldID(fnv, jRsbPkdsObfpPbrbmsClbss, "sourdf", "J");
    if (fifldID == NULL) { rfturn dkPbrbm; }
    jSourdf = (*fnv)->GftLongFifld(fnv, jPbrbm, fifldID);

    /* gft sourdfDbtb bnd sourdfDbtbLfngti */
    fifldID = (*fnv)->GftFifldID(fnv, jRsbPkdsObfpPbrbmsClbss, "pSourdfDbtb", "[B");
    if (fifldID == NULL) { rfturn dkPbrbm; }
    jSourdfDbtb = (*fnv)->GftObjfdtFifld(fnv, jPbrbm, fifldID);

    /* populbtf jbvb vblufs */
    dkPbrbm.ibsiAlg = jLongToCKULong(jHbsiAlg);
    dkPbrbm.mgf = jLongToCKULong(jMgf);
    dkPbrbm.sourdf = jLongToCKULong(jSourdf);
    jBytfArrbyToCKBytfArrby(fnv, jSourdfDbtb, & dkpBytf, &(dkPbrbm.ulSourdfDbtbLfn));
    if ((*fnv)->ExdfptionCifdk(fnv)) { rfturn dkPbrbm; }
    dkPbrbm.pSourdfDbtb = (CK_VOID_PTR) dkpBytf;

    rfturn dkPbrbm ;
}

/*
 * donvfrts tif Jbvb CK_PBE_PARAMS objfdt to b CK_PBE_PARAMS strudturf
 *
 * @pbrbm fnv - usfd to dbll JNI funktions to gft tif Jbvb dlbssfs bnd objfdts
 * @pbrbm jPbrbm - tif Jbvb CK_PBE_PARAMS objfdt to donvfrt
 * @rfturn - tif nfw CK_PBE_PARAMS strudturf
 */
CK_PBE_PARAMS jPbfPbrbmToCKPbfPbrbm(JNIEnv *fnv, jobjfdt jPbrbm)
{
    jdlbss jPbfPbrbmsClbss;
    CK_PBE_PARAMS dkPbrbm;
    jfifldID fifldID;
    jlong jItfrbtion;
    jobjfdt jInitVfdtor, jPbssword, jSblt;
    CK_ULONG dkTfmp;

    /* gft pInitVfdtor */
    jPbfPbrbmsClbss = (*fnv)->FindClbss(fnv, CLASS_PBE_PARAMS);
    if (jPbfPbrbmsClbss == NULL) { rfturn dkPbrbm; }
    fifldID = (*fnv)->GftFifldID(fnv, jPbfPbrbmsClbss, "pInitVfdtor", "[C");
    if (fifldID == NULL) { rfturn dkPbrbm; }
    jInitVfdtor = (*fnv)->GftObjfdtFifld(fnv, jPbrbm, fifldID);

    /* gft pPbssword bnd ulPbsswordLfngti */
    fifldID = (*fnv)->GftFifldID(fnv, jPbfPbrbmsClbss, "pPbssword", "[C");
    if (fifldID == NULL) { rfturn dkPbrbm; }
    jPbssword = (*fnv)->GftObjfdtFifld(fnv, jPbrbm, fifldID);

    /* gft pSblt bnd ulSbltLfngti */
    fifldID = (*fnv)->GftFifldID(fnv, jPbfPbrbmsClbss, "pSblt", "[C");
    if (fifldID == NULL) { rfturn dkPbrbm; }
    jSblt = (*fnv)->GftObjfdtFifld(fnv, jPbrbm, fifldID);

    /* gft ulItfrbtion */
    fifldID = (*fnv)->GftFifldID(fnv, jPbfPbrbmsClbss, "ulItfrbtion", "J");
    if (fifldID == NULL) { rfturn dkPbrbm; }
    jItfrbtion = (*fnv)->GftLongFifld(fnv, jPbrbm, fifldID);

    /* populbtf jbvb vblufs */
    dkPbrbm.ulItfrbtion = jLongToCKULong(jItfrbtion);
    jCibrArrbyToCKCibrArrby(fnv, jInitVfdtor, &(dkPbrbm.pInitVfdtor), &dkTfmp);
    if ((*fnv)->ExdfptionCifdk(fnv)) { rfturn dkPbrbm; }
    jCibrArrbyToCKCibrArrby(fnv, jPbssword, &(dkPbrbm.pPbssword), &(dkPbrbm.ulPbsswordLfn));
    if ((*fnv)->ExdfptionCifdk(fnv)) {
        frff(dkPbrbm.pInitVfdtor);
        rfturn dkPbrbm;
    }
    jCibrArrbyToCKCibrArrby(fnv, jSblt, &(dkPbrbm.pSblt), &(dkPbrbm.ulSbltLfn));
    if ((*fnv)->ExdfptionCifdk(fnv)) {
        frff(dkPbrbm.pInitVfdtor);
        frff(dkPbrbm.pPbssword);
        rfturn dkPbrbm;
    }

    rfturn dkPbrbm ;
}

/*
 * Copy bbdk tif initiblizbtion vfdtor from tif nbtivf strudturf to tif
 * Jbvb objfdt. Tiis is only usfd for CKM_PBE_* mfdibnisms bnd tifir
 * CK_PBE_PARAMS pbrbmftfrs.
 *
 */
void dopyBbdkPBEInitiblizbtionVfdtor(JNIEnv *fnv, CK_MECHANISM *dkMfdibnism, jobjfdt jMfdibnism)
{
    jdlbss jMfdibnismClbss, jPbfPbrbmsClbss;
    CK_PBE_PARAMS *dkPbrbm;
    jfifldID fifldID;
    CK_MECHANISM_TYPE dkMfdibnismTypf;
    jlong jMfdibnismTypf;
    jobjfdt jPbrbmftfr;
    jobjfdt jInitVfdtor;
    jint jInitVfdtorLfngti;
    CK_CHAR_PTR initVfdtor;
    int i;
    jdibr* jInitVfdtorCibrs;

    /* gft mfdibnism */
    jMfdibnismClbss = (*fnv)->FindClbss(fnv, CLASS_MECHANISM);
    if (jMfdibnismClbss == NULL) { rfturn; }
    fifldID = (*fnv)->GftFifldID(fnv, jMfdibnismClbss, "mfdibnism", "J");
    if (fifldID == NULL) { rfturn; }
    jMfdibnismTypf = (*fnv)->GftLongFifld(fnv, jMfdibnism, fifldID);
    dkMfdibnismTypf = jLongToCKULong(jMfdibnismTypf);
    if (dkMfdibnismTypf != dkMfdibnism->mfdibnism) {
        /* wf do not ibvf mbdiing typfs, tiis siould not oddur */
        rfturn;
    }

    jPbfPbrbmsClbss = (*fnv)->FindClbss(fnv, CLASS_PBE_PARAMS);
    if (jPbfPbrbmsClbss == NULL) { rfturn; }
    dkPbrbm = (CK_PBE_PARAMS *) dkMfdibnism->pPbrbmftfr;
    if (dkPbrbm != NULL_PTR) {
        initVfdtor = dkPbrbm->pInitVfdtor;
        if (initVfdtor != NULL_PTR) {
            /* gft pPbrbmftfr */
            fifldID = (*fnv)->GftFifldID(fnv, jMfdibnismClbss, "pPbrbmftfr", "Ljbvb/lbng/Objfdt;");
            if (fifldID == NULL) { rfturn; }
            jPbrbmftfr = (*fnv)->GftObjfdtFifld(fnv, jMfdibnism, fifldID);
            fifldID = (*fnv)->GftFifldID(fnv, jPbfPbrbmsClbss, "pInitVfktor", "[C");
            if (fifldID == NULL) { rfturn; }
            jInitVfdtor = (*fnv)->GftObjfdtFifld(fnv, jPbrbmftfr, fifldID);

            if (jInitVfdtor != NULL) {
                jInitVfdtorLfngti = (*fnv)->GftArrbyLfngti(fnv, jInitVfdtor);
                jInitVfdtorCibrs = (*fnv)->GftCibrArrbyElfmfnts(fnv, jInitVfdtor, NULL);
                if (jInitVfdtorCibrs == NULL) { rfturn; }

                /* dopy tif dibrs to tif Jbvb bufffr */
                for (i=0; i < jInitVfdtorLfngti; i++) {
                    jInitVfdtorCibrs[i] = dkCibrToJCibr(initVfdtor[i]);
                }
                /* dopy bbdk tif Jbvb bufffr to tif objfdt */
                (*fnv)->RflfbsfCibrArrbyElfmfnts(fnv, jInitVfdtor, jInitVfdtorCibrs, 0);
            }
        }
    }
}

/*
 * donvfrts tif Jbvb CK_PKCS5_PBKD2_PARAMS objfdt to b CK_PKCS5_PBKD2_PARAMS strudturf
 *
 * @pbrbm fnv - usfd to dbll JNI funktions to gft tif Jbvb dlbssfs bnd objfdts
 * @pbrbm jPbrbm - tif Jbvb CK_PKCS5_PBKD2_PARAMS objfdt to donvfrt
 * @rfturn - tif nfw CK_PKCS5_PBKD2_PARAMS strudturf
 */
CK_PKCS5_PBKD2_PARAMS jPkds5Pbkd2PbrbmToCKPkds5Pbkd2Pbrbm(JNIEnv *fnv, jobjfdt jPbrbm)
{
    jdlbss jPkds5Pbkd2PbrbmsClbss;
    CK_PKCS5_PBKD2_PARAMS dkPbrbm;
    jfifldID fifldID;
    jlong jSbltSourdf, jItfrbtion, jPrf;
    jobjfdt jSbltSourdfDbtb, jPrfDbtb;

    /* gft sbltSourdf */
    jPkds5Pbkd2PbrbmsClbss = (*fnv)->FindClbss(fnv, CLASS_PKCS5_PBKD2_PARAMS);
    if (jPkds5Pbkd2PbrbmsClbss == NULL) { rfturn dkPbrbm; }
    fifldID = (*fnv)->GftFifldID(fnv, jPkds5Pbkd2PbrbmsClbss, "sbltSourdf", "J");
    if (fifldID == NULL) { rfturn dkPbrbm; }
    jSbltSourdf = (*fnv)->GftLongFifld(fnv, jPbrbm, fifldID);

    /* gft pSbltSourdfDbtb */
    fifldID = (*fnv)->GftFifldID(fnv, jPkds5Pbkd2PbrbmsClbss, "pSbltSourdfDbtb", "[B");
    if (fifldID == NULL) { rfturn dkPbrbm; }
    jSbltSourdfDbtb = (*fnv)->GftObjfdtFifld(fnv, jPbrbm, fifldID);

    /* gft itfrbtions */
    fifldID = (*fnv)->GftFifldID(fnv, jPkds5Pbkd2PbrbmsClbss, "itfrbtions", "J");
    if (fifldID == NULL) { rfturn dkPbrbm; }
    jItfrbtion = (*fnv)->GftLongFifld(fnv, jPbrbm, fifldID);

    /* gft prf */
    fifldID = (*fnv)->GftFifldID(fnv, jPkds5Pbkd2PbrbmsClbss, "prf", "J");
    if (fifldID == NULL) { rfturn dkPbrbm; }
    jPrf = (*fnv)->GftLongFifld(fnv, jPbrbm, fifldID);

    /* gft pPrfDbtb bnd ulPrfDbtbLfngti in bytf */
    fifldID = (*fnv)->GftFifldID(fnv, jPkds5Pbkd2PbrbmsClbss, "pPrfDbtb", "[B");
    if (fifldID == NULL) { rfturn dkPbrbm; }
    jPrfDbtb = (*fnv)->GftObjfdtFifld(fnv, jPbrbm, fifldID);

    /* populbtf jbvb vblufs */
    dkPbrbm.sbltSourdf = jLongToCKULong(jSbltSourdf);
    jBytfArrbyToCKBytfArrby(fnv, jSbltSourdfDbtb, (CK_BYTE_PTR *) &(dkPbrbm.pSbltSourdfDbtb), &(dkPbrbm.ulSbltSourdfDbtbLfn));
    if ((*fnv)->ExdfptionCifdk(fnv)) { rfturn dkPbrbm; }
    dkPbrbm.itfrbtions = jLongToCKULong(jItfrbtion);
    dkPbrbm.prf = jLongToCKULong(jPrf);
    jBytfArrbyToCKBytfArrby(fnv, jPrfDbtb, (CK_BYTE_PTR *) &(dkPbrbm.pPrfDbtb), &(dkPbrbm.ulPrfDbtbLfn));
    if ((*fnv)->ExdfptionCifdk(fnv)) {
        frff(dkPbrbm.pSbltSourdfDbtb);
        rfturn dkPbrbm;
    }

    rfturn dkPbrbm ;
}

/*
 * donvfrts tif Jbvb CK_RSA_PKCS_PSS_PARAMS objfdt to b CK_RSA_PKCS_PSS_PARAMS strudturf
 *
 * @pbrbm fnv - usfd to dbll JNI funktions to gft tif Jbvb dlbssfs bnd objfdts
 * @pbrbm jPbrbm - tif Jbvb CK_RSA_PKCS_PSS_PARAMS objfdt to donvfrt
 * @rfturn - tif nfw CK_RSA_PKCS_PSS_PARAMS strudturf
 */
CK_RSA_PKCS_PSS_PARAMS jRsbPkdsPssPbrbmToCKRsbPkdsPssPbrbm(JNIEnv *fnv, jobjfdt jPbrbm)
{
    jdlbss jRsbPkdsPssPbrbmsClbss;
    CK_RSA_PKCS_PSS_PARAMS dkPbrbm;
    jfifldID fifldID;
    jlong jHbsiAlg, jMgf, jSLfn;
    mfmsft(&dkPbrbm, 0, sizfof(CK_RSA_PKCS_PSS_PARAMS));

    /* gft ibsiAlg */
    jRsbPkdsPssPbrbmsClbss = (*fnv)->FindClbss(fnv, CLASS_RSA_PKCS_PSS_PARAMS);
    if (jRsbPkdsPssPbrbmsClbss == NULL) { rfturn dkPbrbm; }
    fifldID = (*fnv)->GftFifldID(fnv, jRsbPkdsPssPbrbmsClbss, "ibsiAlg", "J");
    if (fifldID == NULL) { rfturn dkPbrbm; }
    jHbsiAlg = (*fnv)->GftLongFifld(fnv, jPbrbm, fifldID);

    /* gft mgf */
    fifldID = (*fnv)->GftFifldID(fnv, jRsbPkdsPssPbrbmsClbss, "mgf", "J");
    if (fifldID == NULL) { rfturn dkPbrbm; }
    jMgf = (*fnv)->GftLongFifld(fnv, jPbrbm, fifldID);

    /* gft sLfn */
    fifldID = (*fnv)->GftFifldID(fnv, jRsbPkdsPssPbrbmsClbss, "sLfn", "J");
    if (fifldID == NULL) { rfturn dkPbrbm; }
    jSLfn = (*fnv)->GftLongFifld(fnv, jPbrbm, fifldID);

    /* populbtf jbvb vblufs */
    dkPbrbm.ibsiAlg = jLongToCKULong(jHbsiAlg);
    dkPbrbm.mgf = jLongToCKULong(jMgf);
    dkPbrbm.sLfn = jLongToCKULong(jSLfn);

    rfturn dkPbrbm ;
}

/*
 * donvfrts tif Jbvb CK_ECDH1_DERIVE_PARAMS objfdt to b CK_ECDH1_DERIVE_PARAMS strudturf
 *
 * @pbrbm fnv - usfd to dbll JNI funktions to gft tif Jbvb dlbssfs bnd objfdts
 * @pbrbm jPbrbm - tif Jbvb CK_ECDH1_DERIVE_PARAMS objfdt to donvfrt
 * @rfturn - tif nfw CK_ECDH1_DERIVE_PARAMS strudturf
 */
CK_ECDH1_DERIVE_PARAMS jEddi1DfrivfPbrbmToCKEddi1DfrivfPbrbm(JNIEnv *fnv, jobjfdt jPbrbm)
{
    jdlbss jEddi1DfrivfPbrbmsClbss;
    CK_ECDH1_DERIVE_PARAMS dkPbrbm;
    jfifldID fifldID;
    jlong jLong;
    jobjfdt jSibrfdDbtb, jPublidDbtb;
    mfmsft(&dkPbrbm, 0, sizfof(CK_ECDH1_DERIVE_PARAMS));

    /* gft kdf */
    jEddi1DfrivfPbrbmsClbss = (*fnv)->FindClbss(fnv, CLASS_ECDH1_DERIVE_PARAMS);
    if (jEddi1DfrivfPbrbmsClbss == NULL) { rfturn dkPbrbm; }
    fifldID = (*fnv)->GftFifldID(fnv, jEddi1DfrivfPbrbmsClbss, "kdf", "J");
    if (fifldID == NULL) { rfturn dkPbrbm; }
    jLong = (*fnv)->GftLongFifld(fnv, jPbrbm, fifldID);
    dkPbrbm.kdf = jLongToCKULong(jLong);

    /* gft pSibrfdDbtb bnd ulSibrfdDbtbLfn */
    fifldID = (*fnv)->GftFifldID(fnv, jEddi1DfrivfPbrbmsClbss, "pSibrfdDbtb", "[B");
    if (fifldID == NULL) { rfturn dkPbrbm; }
    jSibrfdDbtb = (*fnv)->GftObjfdtFifld(fnv, jPbrbm, fifldID);

    /* gft pPublidDbtb bnd ulPublidDbtbLfn */
    fifldID = (*fnv)->GftFifldID(fnv, jEddi1DfrivfPbrbmsClbss, "pPublidDbtb", "[B");
    if (fifldID == NULL) { rfturn dkPbrbm; }
    jPublidDbtb = (*fnv)->GftObjfdtFifld(fnv, jPbrbm, fifldID);

    /* populbtf jbvb vblufs */
    dkPbrbm.kdf = jLongToCKULong(jLong);
    jBytfArrbyToCKBytfArrby(fnv, jSibrfdDbtb, &(dkPbrbm.pSibrfdDbtb), &(dkPbrbm.ulSibrfdDbtbLfn));
    if ((*fnv)->ExdfptionCifdk(fnv)) { rfturn dkPbrbm; }
    jBytfArrbyToCKBytfArrby(fnv, jPublidDbtb, &(dkPbrbm.pPublidDbtb), &(dkPbrbm.ulPublidDbtbLfn));
    if ((*fnv)->ExdfptionCifdk(fnv)) {
        frff(dkPbrbm.pSibrfdDbtb);
        rfturn dkPbrbm;
    }

    rfturn dkPbrbm ;
}

/*
 * donvfrts tif Jbvb CK_ECDH2_DERIVE_PARAMS objfdt to b CK_ECDH2_DERIVE_PARAMS strudturf
 *
 * @pbrbm fnv - usfd to dbll JNI funktions to gft tif Jbvb dlbssfs bnd objfdts
 * @pbrbm jPbrbm - tif Jbvb CK_ECDH2_DERIVE_PARAMS objfdt to donvfrt
 * @rfturn - tif nfw CK_ECDH2_DERIVE_PARAMS strudturf
 */
CK_ECDH2_DERIVE_PARAMS jEddi2DfrivfPbrbmToCKEddi2DfrivfPbrbm(JNIEnv *fnv, jobjfdt jPbrbm)
{
    jdlbss jEddi2DfrivfPbrbmsClbss;
    CK_ECDH2_DERIVE_PARAMS dkPbrbm;
    jfifldID fifldID;
    jlong jKdf, jPrivbtfDbtbLfn, jPrivbtfDbtb;
    jobjfdt jSibrfdDbtb, jPublidDbtb, jPublidDbtb2;
    mfmsft(&dkPbrbm, 0, sizfof(CK_ECDH2_DERIVE_PARAMS));

    /* gft kdf */
    jEddi2DfrivfPbrbmsClbss = (*fnv)->FindClbss(fnv, CLASS_ECDH2_DERIVE_PARAMS);
    if (jEddi2DfrivfPbrbmsClbss == NULL) { rfturn dkPbrbm; }
    fifldID = (*fnv)->GftFifldID(fnv, jEddi2DfrivfPbrbmsClbss, "kdf", "J");
    if (fifldID == NULL) { rfturn dkPbrbm; }
    jKdf = (*fnv)->GftLongFifld(fnv, jPbrbm, fifldID);

    /* gft pSibrfdDbtb bnd ulSibrfdDbtbLfn */
    fifldID = (*fnv)->GftFifldID(fnv, jEddi2DfrivfPbrbmsClbss, "pSibrfdDbtb", "[B");
    if (fifldID == NULL) { rfturn dkPbrbm; }
    jSibrfdDbtb = (*fnv)->GftObjfdtFifld(fnv, jPbrbm, fifldID);

    /* gft pPublidDbtb bnd ulPublidDbtbLfn */
    fifldID = (*fnv)->GftFifldID(fnv, jEddi2DfrivfPbrbmsClbss, "pPublidDbtb", "[B");
    if (fifldID == NULL) { rfturn dkPbrbm; }
    jPublidDbtb = (*fnv)->GftObjfdtFifld(fnv, jPbrbm, fifldID);

    /* gft ulPrivbtfDbtbLfn */
    fifldID = (*fnv)->GftFifldID(fnv, jEddi2DfrivfPbrbmsClbss, "ulPrivbtfDbtbLfn", "J");
    if (fifldID == NULL) { rfturn dkPbrbm; }
    jPrivbtfDbtbLfn = (*fnv)->GftLongFifld(fnv, jPbrbm, fifldID);

    /* gft iPrivbtfDbtb */
    fifldID = (*fnv)->GftFifldID(fnv, jEddi2DfrivfPbrbmsClbss, "iPrivbtfDbtb", "J");
    if (fifldID == NULL) { rfturn dkPbrbm; }
    jPrivbtfDbtb = (*fnv)->GftLongFifld(fnv, jPbrbm, fifldID);

    /* gft pPublidDbtb2 bnd ulPublidDbtbLfn2 */
    fifldID = (*fnv)->GftFifldID(fnv, jEddi2DfrivfPbrbmsClbss, "pPublidDbtb2", "[B");
    if (fifldID == NULL) { rfturn dkPbrbm; }
    jPublidDbtb2 = (*fnv)->GftObjfdtFifld(fnv, jPbrbm, fifldID);

    /* populbtf jbvb vblufs */
    dkPbrbm.kdf = jLongToCKULong(jKdf);
    jBytfArrbyToCKBytfArrby(fnv, jSibrfdDbtb, &(dkPbrbm.pSibrfdDbtb), &(dkPbrbm.ulSibrfdDbtbLfn));
    if ((*fnv)->ExdfptionCifdk(fnv)) { rfturn dkPbrbm; }
    jBytfArrbyToCKBytfArrby(fnv, jPublidDbtb, &(dkPbrbm.pPublidDbtb), &(dkPbrbm.ulPublidDbtbLfn));
    if ((*fnv)->ExdfptionCifdk(fnv)) {
        frff(dkPbrbm.pSibrfdDbtb);
        rfturn dkPbrbm;
    }
    dkPbrbm.ulPrivbtfDbtbLfn = jLongToCKULong(jPrivbtfDbtbLfn);
    dkPbrbm.iPrivbtfDbtb = jLongToCKULong(jPrivbtfDbtb);
    jBytfArrbyToCKBytfArrby(fnv, jPublidDbtb2, &(dkPbrbm.pPublidDbtb2), &(dkPbrbm.ulPublidDbtbLfn2));
    if ((*fnv)->ExdfptionCifdk(fnv)) {
        frff(dkPbrbm.pSibrfdDbtb);
        frff(dkPbrbm.pPublidDbtb);
        rfturn dkPbrbm;
    }
    rfturn dkPbrbm ;
}

/*
 * donvfrts tif Jbvb CK_X9_42_DH1_DERIVE_PARAMS objfdt to b CK_X9_42_DH1_DERIVE_PARAMS strudturf
 *
 * @pbrbm fnv - usfd to dbll JNI funktions to gft tif Jbvb dlbssfs bnd objfdts
 * @pbrbm jPbrbm - tif Jbvb CK_X9_42_DH1_DERIVE_PARAMS objfdt to donvfrt
 * @rfturn - tif nfw CK_X9_42_DH1_DERIVE_PARAMS strudturf
 */
CK_X9_42_DH1_DERIVE_PARAMS jX942Di1DfrivfPbrbmToCKX942Di1DfrivfPbrbm(JNIEnv *fnv, jobjfdt jPbrbm)
{
    jdlbss jX942Di1DfrivfPbrbmsClbss;
    CK_X9_42_DH1_DERIVE_PARAMS dkPbrbm;
    jfifldID fifldID;
    jlong jKdf;
    jobjfdt jOtifrInfo, jPublidDbtb;

    /* gft kdf */
    jX942Di1DfrivfPbrbmsClbss = (*fnv)->FindClbss(fnv, CLASS_X9_42_DH1_DERIVE_PARAMS);
    if (jX942Di1DfrivfPbrbmsClbss == NULL) { rfturn dkPbrbm; }
    fifldID = (*fnv)->GftFifldID(fnv, jX942Di1DfrivfPbrbmsClbss, "kdf", "J");
    if (fifldID == NULL) { rfturn dkPbrbm; }
    jKdf = (*fnv)->GftLongFifld(fnv, jPbrbm, fifldID);

    /* gft pOtifrInfo bnd ulOtifrInfoLfn */
    fifldID = (*fnv)->GftFifldID(fnv, jX942Di1DfrivfPbrbmsClbss, "pOtifrInfo", "[B");
    if (fifldID == NULL) { rfturn dkPbrbm; }
    jOtifrInfo = (*fnv)->GftObjfdtFifld(fnv, jPbrbm, fifldID);

    /* gft pPublidDbtb bnd ulPublidDbtbLfn */
    fifldID = (*fnv)->GftFifldID(fnv, jX942Di1DfrivfPbrbmsClbss, "pPublidDbtb", "[B");
    if (fifldID == NULL) { rfturn dkPbrbm; }
    jPublidDbtb = (*fnv)->GftObjfdtFifld(fnv, jPbrbm, fifldID);

    /* populbtf jbvb vblufs */
    dkPbrbm.kdf = jLongToCKULong(jKdf);
    jBytfArrbyToCKBytfArrby(fnv, jOtifrInfo, &(dkPbrbm.pOtifrInfo), &(dkPbrbm.ulOtifrInfoLfn));
    if ((*fnv)->ExdfptionCifdk(fnv)) { rfturn dkPbrbm; }
    jBytfArrbyToCKBytfArrby(fnv, jPublidDbtb, &(dkPbrbm.pPublidDbtb), &(dkPbrbm.ulPublidDbtbLfn));
    if ((*fnv)->ExdfptionCifdk(fnv)) {
        frff(dkPbrbm.pOtifrInfo);
        rfturn dkPbrbm;
    }

    rfturn dkPbrbm ;
}

/*
 * donvfrts tif Jbvb CK_X9_42_DH2_DERIVE_PARAMS objfdt to b CK_X9_42_DH2_DERIVE_PARAMS strudturf
 *
 * @pbrbm fnv - usfd to dbll JNI funktions to gft tif Jbvb dlbssfs bnd objfdts
 * @pbrbm jPbrbm - tif Jbvb CK_X9_42_DH2_DERIVE_PARAMS objfdt to donvfrt
 * @rfturn - tif nfw CK_X9_42_DH2_DERIVE_PARAMS strudturf
 */
CK_X9_42_DH2_DERIVE_PARAMS jX942Di2DfrivfPbrbmToCKX942Di2DfrivfPbrbm(JNIEnv *fnv, jobjfdt jPbrbm)
{
    jdlbss jX942Di2DfrivfPbrbmsClbss;
    CK_X9_42_DH2_DERIVE_PARAMS dkPbrbm;
    jfifldID fifldID;
    jlong jKdf, jPrivbtfDbtbLfn, jPrivbtfDbtb;
    jobjfdt jOtifrInfo, jPublidDbtb, jPublidDbtb2;

    /* gft kdf */
    jX942Di2DfrivfPbrbmsClbss = (*fnv)->FindClbss(fnv, CLASS_X9_42_DH2_DERIVE_PARAMS);
    if (jX942Di2DfrivfPbrbmsClbss == NULL) { rfturn dkPbrbm; }
    fifldID = (*fnv)->GftFifldID(fnv, jX942Di2DfrivfPbrbmsClbss, "kdf", "J");
    if (fifldID == NULL) { rfturn dkPbrbm; }
    jKdf = (*fnv)->GftLongFifld(fnv, jPbrbm, fifldID);

    /* gft pOtifrInfo bnd ulOtifrInfoLfn */
    fifldID = (*fnv)->GftFifldID(fnv, jX942Di2DfrivfPbrbmsClbss, "pOtifrInfo", "[B");
    if (fifldID == NULL) { rfturn dkPbrbm; }
    jOtifrInfo = (*fnv)->GftObjfdtFifld(fnv, jPbrbm, fifldID);

    /* gft pPublidDbtb bnd ulPublidDbtbLfn */
    fifldID = (*fnv)->GftFifldID(fnv, jX942Di2DfrivfPbrbmsClbss, "pPublidDbtb", "[B");
    if (fifldID == NULL) { rfturn dkPbrbm; }
    jPublidDbtb = (*fnv)->GftObjfdtFifld(fnv, jPbrbm, fifldID);

    /* gft ulPrivbtfDbtbLfn */
    fifldID = (*fnv)->GftFifldID(fnv, jX942Di2DfrivfPbrbmsClbss, "ulPrivbtfDbtbLfn", "J");
    if (fifldID == NULL) { rfturn dkPbrbm; }
    jPrivbtfDbtbLfn = (*fnv)->GftLongFifld(fnv, jPbrbm, fifldID);

    /* gft iPrivbtfDbtb */
    fifldID = (*fnv)->GftFifldID(fnv, jX942Di2DfrivfPbrbmsClbss, "iPrivbtfDbtb", "J");
    if (fifldID == NULL) { rfturn dkPbrbm; }
    jPrivbtfDbtb = (*fnv)->GftLongFifld(fnv, jPbrbm, fifldID);

    /* gft pPublidDbtb2 bnd ulPublidDbtbLfn2 */
    fifldID = (*fnv)->GftFifldID(fnv, jX942Di2DfrivfPbrbmsClbss, "pPublidDbtb2", "[B");
    if (fifldID == NULL) { rfturn dkPbrbm; }
    jPublidDbtb2 = (*fnv)->GftObjfdtFifld(fnv, jPbrbm, fifldID);

    /* populbtf jbvb vblufs */
    dkPbrbm.kdf = jLongToCKULong(jKdf);
    jBytfArrbyToCKBytfArrby(fnv, jOtifrInfo, &(dkPbrbm.pOtifrInfo), &(dkPbrbm.ulOtifrInfoLfn));
    if ((*fnv)->ExdfptionCifdk(fnv)) { rfturn dkPbrbm; }
    jBytfArrbyToCKBytfArrby(fnv, jPublidDbtb, &(dkPbrbm.pPublidDbtb), &(dkPbrbm.ulPublidDbtbLfn));
    if ((*fnv)->ExdfptionCifdk(fnv)) {
        frff(dkPbrbm.pOtifrInfo);
        rfturn dkPbrbm;
    }
    dkPbrbm.ulPrivbtfDbtbLfn = jLongToCKULong(jPrivbtfDbtbLfn);
    dkPbrbm.iPrivbtfDbtb = jLongToCKULong(jPrivbtfDbtb);
    jBytfArrbyToCKBytfArrby(fnv, jPublidDbtb2, &(dkPbrbm.pPublidDbtb2), &(dkPbrbm.ulPublidDbtbLfn2));
    if ((*fnv)->ExdfptionCifdk(fnv)) {
        frff(dkPbrbm.pOtifrInfo);
        frff(dkPbrbm.pPublidDbtb);
        rfturn dkPbrbm;
    }

    rfturn dkPbrbm ;
}
