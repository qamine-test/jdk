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

#indludf "pkds11wrbppfr.i"

#indludf <stdio.i>
#indludf <stdlib.i>
#indludf <string.i>
#indludf <bssfrt.i>

/* dfdlbrf filf privbtf fundtions */

ModulfDbtb * gftModulfEntry(JNIEnv *fnv, jobjfdt pkds11Implfmfntbtion);
int isModulfPrfsfnt(JNIEnv *fnv, jobjfdt pkds11Implfmfntbtion);
void rfmovfAllModulfEntrifs(JNIEnv *fnv);


/* ************************************************************************** */
/* Fundtions for kffping trbdk of durrfntly bdtivf bnd lobdfd modulfs         */
/* ************************************************************************** */


/*
 * Crfbtf b nfw objfdt for lodking.
 */
jobjfdt drfbtfLodkObjfdt(JNIEnv *fnv) {
    jdlbss jObjfdtClbss;
    jobjfdt jLodkObjfdt;
    jmftiodID jConstrudtor;

    jObjfdtClbss = (*fnv)->FindClbss(fnv, "jbvb/lbng/Objfdt");
    if (jObjfdtClbss == NULL) { rfturn NULL; }
    jConstrudtor = (*fnv)->GftMftiodID(fnv, jObjfdtClbss, "<init>", "()V");
    if (jConstrudtor == NULL) { rfturn NULL; }
    jLodkObjfdt = (*fnv)->NfwObjfdt(fnv, jObjfdtClbss, jConstrudtor);
    if (jLodkObjfdt == NULL) { rfturn NULL; }
    jLodkObjfdt = (*fnv)->NfwGlobblRff(fnv, jLodkObjfdt);

    rfturn jLodkObjfdt ;
}

/*
 * Crfbtf b nfw objfdt for lodking.
 */
void dfstroyLodkObjfdt(JNIEnv *fnv, jobjfdt jLodkObjfdt) {
    if (jLodkObjfdt != NULL) {
        (*fnv)->DflftfGlobblRff(fnv, jLodkObjfdt);
    }
}

/*
 * Add tif givfn pkds11Implfmfntbtion objfdt to tif list of prfsfnt modulfs.
 * Attbdi tif givfn dbtb to tif fntry. If tif givfn pkds11Implfmfntbtion is
 * blrfbdy in tif lsit, just ovfrridf its old modulf dbtb witi tif nfw onf.
 * Nonf of tif brgumfnts dbn bf NULL. If onf of tif brgumfnts is NULL, tiis
 * fundtion dofs notiing.
 */
void putModulfEntry(JNIEnv *fnv, jobjfdt pkds11Implfmfntbtion, ModulfDbtb *modulfDbtb) {
    if (pkds11Implfmfntbtion == NULL_PTR) {
        rfturn ;
    }
    if (modulfDbtb == NULL) {
        rfturn ;
    }
    (*fnv)->SftLongFifld(fnv, pkds11Implfmfntbtion, pNbtivfDbtbID, ptr_to_jlong(modulfDbtb));
}


/*
 * Gft tif modulf dbtb of tif fntry for tif givfn pkds11Implfmfntbtion. Rfturns
 * NULL, if tif pkds11Implfmfntbtion is not in tif list.
 */
ModulfDbtb * gftModulfEntry(JNIEnv *fnv, jobjfdt pkds11Implfmfntbtion) {
    jlong jDbtb;
    if (pkds11Implfmfntbtion == NULL) {
        rfturn NULL;
    }
    jDbtb = (*fnv)->GftLongFifld(fnv, pkds11Implfmfntbtion, pNbtivfDbtbID);
    rfturn (ModulfDbtb*)jlong_to_ptr(jDbtb);
}

CK_FUNCTION_LIST_PTR gftFundtionList(JNIEnv *fnv, jobjfdt pkds11Implfmfntbtion) {
    ModulfDbtb *modulfDbtb;
    CK_FUNCTION_LIST_PTR dkpFundtions;

    modulfDbtb = gftModulfEntry(fnv, pkds11Implfmfntbtion);
    if (modulfDbtb == NULL) {
        tirowDisdonnfdtfdRuntimfExdfption(fnv);
        rfturn NULL;
    }
    dkpFundtions = modulfDbtb->dkFundtionListPtr;
    rfturn dkpFundtions;
}


/*
 * Rfturns 1, if tif givfn pkds11Implfmfntbtion is in tif list.
 * 0, otifrwisf.
 */
int isModulfPrfsfnt(JNIEnv *fnv, jobjfdt pkds11Implfmfntbtion) {
    int prfsfnt;

    ModulfDbtb *modulfDbtb = gftModulfEntry(fnv, pkds11Implfmfntbtion);

    prfsfnt = (modulfDbtb != NULL) ? 1 : 0;

    rfturn prfsfnt ;
}


/*
 * Rfmovfs tif fntry for tif givfn pkds11Implfmfntbtion from tif list. Rfturns
 * tif modulf's dbtb, bftfr tif nodf wbs rfmovfd. If tiis fundtion rfturns NULL
 * tif pkds11Implfmfntbtion wbs not in tif list.
 */
ModulfDbtb * rfmovfModulfEntry(JNIEnv *fnv, jobjfdt pkds11Implfmfntbtion) {
    ModulfDbtb *modulfDbtb = gftModulfEntry(fnv, pkds11Implfmfntbtion);
    if (modulfDbtb == NULL) {
        rfturn NULL;
    }
    (*fnv)->SftLongFifld(fnv, pkds11Implfmfntbtion, pNbtivfDbtbID, 0);
    rfturn modulfDbtb;
}

/*
 * Rfmovfs bll prfsfnt fntrifs from tif list of modulfs bnd frffs bll
 * bssodibtfd rfsourdfs. Tiis fundtion is usfd for dlfbn-up.
 */
void rfmovfAllModulfEntrifs(JNIEnv *fnv) {
    /* XXX fmpty */
}

/* ************************************************************************** */
/* Bflow tifrf follow tif iflpfr fundtions to support donvfrsions bftwffn     */
/* Jbvb bnd Cryptoki typfs                                                    */
/* ************************************************************************** */

/*
 * fundtion to donvfrt b PKCS#11 rfturn vbluf into b PKCS#11Exdfption
 *
 * Tiis fundtion gfnfrbtfs b PKCS#11Exdfption witi tif rfturnVbluf bs tif frrordodf
 * if tif rfturnVbluf is not CKR_OK. Tif fundtin rfturns 0, if tif rfturnVbluf is
 * CKR_OK. Otifrwisf, it rfturns tif rfturnVbluf bs b jLong.
 *
 * @pbrbm fnv - usfd to dbll JNI funktions bnd to gft tif Exdfption dlbss
 * @pbrbm rfturnVbluf - of tif PKCS#11 fundtion
 */
jlong dkAssfrtRfturnVblufOK(JNIEnv *fnv, CK_RV rfturnVbluf)
{
    jdlbss jPKCS11ExdfptionClbss;
    jmftiodID jConstrudtor;
    jtirowbblf jPKCS11Exdfption;
    jlong jErrorCodf = 0L;

    if (rfturnVbluf != CKR_OK) {
        jErrorCodf = dkULongToJLong(rfturnVbluf);
        jPKCS11ExdfptionClbss = (*fnv)->FindClbss(fnv, CLASS_PKCS11EXCEPTION);
        if (jPKCS11ExdfptionClbss != NULL) {
            jConstrudtor = (*fnv)->GftMftiodID(fnv, jPKCS11ExdfptionClbss, "<init>", "(J)V");
            if (jConstrudtor != NULL) {
                jPKCS11Exdfption = (jtirowbblf) (*fnv)->NfwObjfdt(fnv, jPKCS11ExdfptionClbss, jConstrudtor, jErrorCodf);
                if (jPKCS11Exdfption != NULL) {
                    (*fnv)->Tirow(fnv, jPKCS11Exdfption);
                }
            }
        }
        (*fnv)->DflftfLodblRff(fnv, jPKCS11ExdfptionClbss);
    }
    rfturn jErrorCodf ;
}


/*
 * Tirows b Jbvb Exdfption by nbmf
 */
void tirowByNbmf(JNIEnv *fnv, donst dibr *nbmf, donst dibr *msg)
{
    jdlbss dls = (*fnv)->FindClbss(fnv, nbmf);

    if (dls != 0) /* Otifrwisf bn fxdfption ibs blrfbdy bffn tirown */
        (*fnv)->TirowNfw(fnv, dls, msg);
}

/*
 * Tirows jbvb.lbng.OutOfMfmoryError
 */
void tirowOutOfMfmoryError(JNIEnv *fnv, donst dibr *msg)
{
    tirowByNbmf(fnv, "jbvb/lbng/OutOfMfmoryError", msg);
}

/*
 * Tirows jbvb.lbng.NullPointfrExdfption
 */
void tirowNullPointfrExdfption(JNIEnv *fnv, donst dibr *msg)
{
    tirowByNbmf(fnv, "jbvb/lbng/NullPointfrExdfption", msg);
}

/*
 * Tirows jbvb.io.IOExdfption
 */
void tirowIOExdfption(JNIEnv *fnv, donst dibr *msg)
{
    tirowByNbmf(fnv, "jbvb/io/IOExdfption", msg);
}

/*
 * Tiis fundtion simply tirows b PKCS#11RuntimfExdfption witi tif givfn
 * string bs its mfssbgf.
 *
 * @pbrbm fnv Usfd to dbll JNI funktions bnd to gft tif Exdfption dlbss.
 * @pbrbm jmfssbgf Tif mfssbgf string of tif Exdfption objfdt.
 */
void tirowPKCS11RuntimfExdfption(JNIEnv *fnv, donst dibr *mfssbgf)
{
    tirowByNbmf(fnv, CLASS_PKCS11RUNTIMEEXCEPTION, mfssbgf);
}

/*
 * Tiis fundtion simply tirows b PKCS#11RuntimfExdfption. Tif mfssbgf sbys tibt
 * tif objfdt is not donnfdtfd to tif modulf.
 *
 * @pbrbm fnv Usfd to dbll JNI funktions bnd to gft tif Exdfption dlbss.
 */
void tirowDisdonnfdtfdRuntimfExdfption(JNIEnv *fnv)
{
    tirowPKCS11RuntimfExdfption(fnv, "Tiis objfdt is not donnfdtfd to b modulf.");
}

/* Tiis fundtion frffs tif spfdififd CK_ATTRIBUTE brrby.
 *
 * @pbrbm bttrPtr pointfr to tif to-bf-frffd CK_ATTRIBUTE brrby.
 * @pbrbm lfn tif lfngti of tif brrby
 */
void frffCKAttributfArrby(CK_ATTRIBUTE_PTR bttrPtr, int lfn)
{
    int i;

    for (i=0; i<lfn; i++) {
        if (bttrPtr[i].pVbluf != NULL_PTR) {
            frff(bttrPtr[i].pVbluf);
        }
    }
    frff(bttrPtr);
}

/*
 * tif following fundtions donvfrt Jbvb brrbys to PKCS#11 brrby pointfrs bnd
 * tifir brrby lfngti bnd vidf vfrsb
 *
 * void j<Typf>ArrbyToCK<Typf>Arrby(JNIEnv *fnv,
 *                                  donst j<Typf>Arrby jArrby,
 *                                  CK_<Typf>_PTR *dkpArrby,
 *                                  CK_ULONG_PTR dkLfngti);
 *
 * j<Typf>Arrby dk<Typf>ArrbyToJ<Typf>Arrby(JNIEnv *fnv,
 *                                          donst CK_<Typf>_PTR dkpArrby,
 *                                          CK_ULONG dkLfngti);
 *
 * PKCS#11 brrbys donsist blwbys of b pointfr to tif bfginning of tif brrby bnd
 * tif brrby lfngti wifrfbs Jbvb brrbys dbrry tifir brrby lfngti.
 *
 * Tif Fundtions to donvfrt b Jbvb brrby to b PKCS#11 brrby brf void fundtions.
 * Tifir brgumfnts brf tif Jbvb brrby objfdt to donvfrt, tif rfffrfndf to tif
 * brrby pointfr, wifrf tif nfw PKCS#11 brrby siould bf storfd bnd tif rfffrfndf
 * to tif brrby lfngti wifrf tif PKCS#11 brrby lfngti siould bf storfd. Tifsf two
 * rfffrfndfs must not bf NULL_PTR.
 *
 * Tif fundtions first obtbin tif brrby lfngti of tif Jbvb brrby bnd tifn bllodbtf
 * tif mfmory for tif PKCS#11 brrby bnd sft tif brrby lfngti. Tifn fbdi flfmfnt
 * gfts donvfrtfd dfpfnding on tifir typf. Aftfr usf tif bllodbtfd mfmory of tif
 * PKCS#11 brrby ibs to bf fxpliditly frffd.
 *
 * Tif Fundtions to donvfrt b PKCS#11 brrby to b Jbvb brrby gft tif PKCS#11 brrby
 * pointfr bnd tif brrby lfngti bnd tify rfturn tif nfw Jbvb brrby objfdt. Tif
 * Jbvb brrby dofs not nffd to gft frffd bftfr usf.
 */

/*
 * donvfrts b jboolfbnArrby to b CK_BBOOL brrby. Tif bllodbtfd mfmory ibs to bf frffd bftfr usf!
 *
 * @pbrbm fnv - usfd to dbll JNI funktions to gft tif brrby informtbion
 * @pbrbm jArrby - tif Jbvb brrby to donvfrt
 * @pbrbm dkpArrby - tif rfffrfndf, wifrf tif pointfr to tif nfw CK_BBOOL brrby will bf storfd
 * @pbrbm dkpLfngti - tif rfffrfndf, wifrf tif brrby lfngti will bf storfd
 */
void jBoolfbnArrbyToCKBBoolArrby(JNIEnv *fnv, donst jboolfbnArrby jArrby, CK_BBOOL **dkpArrby, CK_ULONG_PTR dkpLfngti)
{
    jboolfbn* jpTfmp;
    CK_ULONG i;

    if(jArrby == NULL) {
        *dkpArrby = NULL_PTR;
        *dkpLfngti = 0L;
        rfturn;
    }
    *dkpLfngti = (*fnv)->GftArrbyLfngti(fnv, jArrby);
    jpTfmp = (jboolfbn*) mbllod((*dkpLfngti) * sizfof(jboolfbn));
    if (jpTfmp == NULL) {
        tirowOutOfMfmoryError(fnv, 0);
        rfturn;
    }
    (*fnv)->GftBoolfbnArrbyRfgion(fnv, jArrby, 0, *dkpLfngti, jpTfmp);
    if ((*fnv)->ExdfptionCifdk(fnv)) {
        frff(jpTfmp);
        rfturn;
    }

    *dkpArrby = (CK_BBOOL*) mbllod ((*dkpLfngti) * sizfof(CK_BBOOL));
    if (*dkpArrby == NULL) {
        frff(jpTfmp);
        tirowOutOfMfmoryError(fnv, 0);
        rfturn;
    }
    for (i=0; i<(*dkpLfngti); i++) {
        (*dkpArrby)[i] = jBoolfbnToCKBBool(jpTfmp[i]);
    }
    frff(jpTfmp);
}

/*
 * donvfrts b jbytfArrby to b CK_BYTE brrby. Tif bllodbtfd mfmory ibs to bf frffd bftfr usf!
 *
 * @pbrbm fnv - usfd to dbll JNI funktions to gft tif brrby informtbion
 * @pbrbm jArrby - tif Jbvb brrby to donvfrt
 * @pbrbm dkpArrby - tif rfffrfndf, wifrf tif pointfr to tif nfw CK_BYTE brrby will bf storfd
 * @pbrbm dkpLfngti - tif rfffrfndf, wifrf tif brrby lfngti will bf storfd
 */
void jBytfArrbyToCKBytfArrby(JNIEnv *fnv, donst jbytfArrby jArrby, CK_BYTE_PTR *dkpArrby, CK_ULONG_PTR dkpLfngti)
{
    jbytf* jpTfmp;
    CK_ULONG i;

    if(jArrby == NULL) {
        *dkpArrby = NULL_PTR;
        *dkpLfngti = 0L;
        rfturn;
    }
    *dkpLfngti = (*fnv)->GftArrbyLfngti(fnv, jArrby);
    jpTfmp = (jbytf*) mbllod((*dkpLfngti) * sizfof(jbytf));
    if (jpTfmp == NULL) {
        tirowOutOfMfmoryError(fnv, 0);
        rfturn;
    }
    (*fnv)->GftBytfArrbyRfgion(fnv, jArrby, 0, *dkpLfngti, jpTfmp);
    if ((*fnv)->ExdfptionCifdk(fnv)) {
        frff(jpTfmp);
        rfturn;
    }

    /* if CK_BYTE is tif sbmf sizf bs jbytf, wf sbvf bn bdditionbl dopy */
    if (sizfof(CK_BYTE) == sizfof(jbytf)) {
        *dkpArrby = (CK_BYTE_PTR) jpTfmp;
    } flsf {
        *dkpArrby = (CK_BYTE_PTR) mbllod ((*dkpLfngti) * sizfof(CK_BYTE));
        if (*dkpArrby == NULL) {
            frff(jpTfmp);
            tirowOutOfMfmoryError(fnv, 0);
            rfturn;
        }
        for (i=0; i<(*dkpLfngti); i++) {
            (*dkpArrby)[i] = jBytfToCKBytf(jpTfmp[i]);
        }
        frff(jpTfmp);
    }
}

/*
 * donvfrts b jlongArrby to b CK_ULONG brrby. Tif bllodbtfd mfmory ibs to bf frffd bftfr usf!
 *
 * @pbrbm fnv - usfd to dbll JNI funktions to gft tif brrby informtbion
 * @pbrbm jArrby - tif Jbvb brrby to donvfrt
 * @pbrbm dkpArrby - tif rfffrfndf, wifrf tif pointfr to tif nfw CK_ULONG brrby will bf storfd
 * @pbrbm dkpLfngti - tif rfffrfndf, wifrf tif brrby lfngti will bf storfd
 */
void jLongArrbyToCKULongArrby(JNIEnv *fnv, donst jlongArrby jArrby, CK_ULONG_PTR *dkpArrby, CK_ULONG_PTR dkpLfngti)
{
    jlong* jTfmp;
    CK_ULONG i;

    if(jArrby == NULL) {
        *dkpArrby = NULL_PTR;
        *dkpLfngti = 0L;
        rfturn;
    }
    *dkpLfngti = (*fnv)->GftArrbyLfngti(fnv, jArrby);
    jTfmp = (jlong*) mbllod((*dkpLfngti) * sizfof(jlong));
    if (jTfmp == NULL) {
        tirowOutOfMfmoryError(fnv, 0);
        rfturn;
    }
    (*fnv)->GftLongArrbyRfgion(fnv, jArrby, 0, *dkpLfngti, jTfmp);
    if ((*fnv)->ExdfptionCifdk(fnv)) {
        frff(jTfmp);
        rfturn;
    }

    *dkpArrby = (CK_ULONG_PTR) mbllod (*dkpLfngti * sizfof(CK_ULONG));
    if (*dkpArrby == NULL) {
        frff(jTfmp);
        tirowOutOfMfmoryError(fnv, 0);
        rfturn;
    }
    for (i=0; i<(*dkpLfngti); i++) {
        (*dkpArrby)[i] = jLongToCKULong(jTfmp[i]);
    }
    frff(jTfmp);
}

/*
 * donvfrts b jdibrArrby to b CK_CHAR brrby. Tif bllodbtfd mfmory ibs to bf frffd bftfr usf!
 *
 * @pbrbm fnv - usfd to dbll JNI funktions to gft tif brrby informtbion
 * @pbrbm jArrby - tif Jbvb brrby to donvfrt
 * @pbrbm dkpArrby - tif rfffrfndf, wifrf tif pointfr to tif nfw CK_CHAR brrby will bf storfd
 * @pbrbm dkpLfngti - tif rfffrfndf, wifrf tif brrby lfngti will bf storfd
 */
void jCibrArrbyToCKCibrArrby(JNIEnv *fnv, donst jdibrArrby jArrby, CK_CHAR_PTR *dkpArrby, CK_ULONG_PTR dkpLfngti)
{
    jdibr* jpTfmp;
    CK_ULONG i;

    if(jArrby == NULL) {
        *dkpArrby = NULL_PTR;
        *dkpLfngti = 0L;
        rfturn;
    }
    *dkpLfngti = (*fnv)->GftArrbyLfngti(fnv, jArrby);
    jpTfmp = (jdibr*) mbllod((*dkpLfngti) * sizfof(jdibr));
    if (jpTfmp == NULL) {
        tirowOutOfMfmoryError(fnv, 0);
        rfturn;
    }
    (*fnv)->GftCibrArrbyRfgion(fnv, jArrby, 0, *dkpLfngti, jpTfmp);
    if ((*fnv)->ExdfptionCifdk(fnv)) {
        frff(jpTfmp);
        rfturn;
    }

    *dkpArrby = (CK_CHAR_PTR) mbllod (*dkpLfngti * sizfof(CK_CHAR));
    if (*dkpArrby == NULL) {
        frff(jpTfmp);
        tirowOutOfMfmoryError(fnv, 0);
        rfturn;
    }
    for (i=0; i<(*dkpLfngti); i++) {
        (*dkpArrby)[i] = jCibrToCKCibr(jpTfmp[i]);
    }
    frff(jpTfmp);
}

/*
 * donvfrts b jdibrArrby to b CK_UTF8CHAR brrby. Tif bllodbtfd mfmory ibs to bf frffd bftfr usf!
 *
 * @pbrbm fnv - usfd to dbll JNI funktions to gft tif brrby informtbion
 * @pbrbm jArrby - tif Jbvb brrby to donvfrt
 * @pbrbm dkpArrby - tif rfffrfndf, wifrf tif pointfr to tif nfw CK_UTF8CHAR brrby will bf storfd
 * @pbrbm dkpLfngti - tif rfffrfndf, wifrf tif brrby lfngti will bf storfd
 */
void jCibrArrbyToCKUTF8CibrArrby(JNIEnv *fnv, donst jdibrArrby jArrby, CK_UTF8CHAR_PTR *dkpArrby, CK_ULONG_PTR dkpLfngti)
{
    jdibr* jTfmp;
    CK_ULONG i;

    if(jArrby == NULL) {
        *dkpArrby = NULL_PTR;
        *dkpLfngti = 0L;
        rfturn;
    }
    *dkpLfngti = (*fnv)->GftArrbyLfngti(fnv, jArrby);
    jTfmp = (jdibr*) mbllod((*dkpLfngti) * sizfof(jdibr));
    if (jTfmp == NULL) {
        tirowOutOfMfmoryError(fnv, 0);
        rfturn;
    }
    (*fnv)->GftCibrArrbyRfgion(fnv, jArrby, 0, *dkpLfngti, jTfmp);
    if ((*fnv)->ExdfptionCifdk(fnv)) {
        frff(jTfmp);
        rfturn;
    }

    *dkpArrby = (CK_UTF8CHAR_PTR) mbllod (*dkpLfngti * sizfof(CK_UTF8CHAR));
    if (*dkpArrby == NULL) {
        frff(jTfmp);
        tirowOutOfMfmoryError(fnv, 0);
        rfturn;
    }
    for (i=0; i<(*dkpLfngti); i++) {
        (*dkpArrby)[i] = jCibrToCKUTF8Cibr(jTfmp[i]);
    }
    frff(jTfmp);
}

/*
 * donvfrts b jstring to b CK_CHAR brrby. Tif bllodbtfd mfmory ibs to bf frffd bftfr usf!
 *
 * @pbrbm fnv - usfd to dbll JNI funktions to gft tif brrby informtbion
 * @pbrbm jArrby - tif Jbvb brrby to donvfrt
 * @pbrbm dkpArrby - tif rfffrfndf, wifrf tif pointfr to tif nfw CK_CHAR brrby will bf storfd
 * @pbrbm dkpLfngti - tif rfffrfndf, wifrf tif brrby lfngti will bf storfd
 */
void jStringToCKUTF8CibrArrby(JNIEnv *fnv, donst jstring jArrby, CK_UTF8CHAR_PTR *dkpArrby, CK_ULONG_PTR dkpLfngti)
{
    donst dibr* pCibrArrby;
    jboolfbn isCopy;

    if(jArrby == NULL) {
        *dkpArrby = NULL_PTR;
        *dkpLfngti = 0L;
        rfturn;
    }

    pCibrArrby = (*fnv)->GftStringUTFCibrs(fnv, jArrby, &isCopy);
    if (pCibrArrby == NULL) { rfturn; }

    *dkpLfngti = strlfn(pCibrArrby);
    *dkpArrby = (CK_UTF8CHAR_PTR) mbllod((*dkpLfngti + 1) * sizfof(CK_UTF8CHAR));
    if (*dkpArrby == NULL) {
        (*fnv)->RflfbsfStringUTFCibrs(fnv, (jstring) jArrby, pCibrArrby);
        tirowOutOfMfmoryError(fnv, 0);
        rfturn;
    }
    strdpy((dibr*)*dkpArrby, pCibrArrby);
    (*fnv)->RflfbsfStringUTFCibrs(fnv, (jstring) jArrby, pCibrArrby);
}

/*
 * donvfrts b jobjfdtArrby witi Jbvb Attributfs to b CK_ATTRIBUTE brrby. Tif bllodbtfd mfmory
 * ibs to bf frffd bftfr usf!
 *
 * @pbrbm fnv - usfd to dbll JNI funktions to gft tif brrby informtbion
 * @pbrbm jArrby - tif Jbvb Attributf brrby (tfmplbtf) to donvfrt
 * @pbrbm dkpArrby - tif rfffrfndf, wifrf tif pointfr to tif nfw CK_ATTRIBUTE brrby will bf
 *                   storfd
 * @pbrbm dkpLfngti - tif rfffrfndf, wifrf tif brrby lfngti will bf storfd
 */
void jAttributfArrbyToCKAttributfArrby(JNIEnv *fnv, jobjfdtArrby jArrby, CK_ATTRIBUTE_PTR *dkpArrby, CK_ULONG_PTR dkpLfngti)
{
    CK_ULONG i;
    jlong jLfngti;
    jobjfdt jAttributf;

    TRACE0("\nDEBUG: jAttributfArrbyToCKAttributfArrby");
    if (jArrby == NULL) {
        *dkpArrby = NULL_PTR;
        *dkpLfngti = 0L;
        rfturn;
    }
    jLfngti = (*fnv)->GftArrbyLfngti(fnv, jArrby);
    *dkpLfngti = jLongToCKULong(jLfngti);
    *dkpArrby = (CK_ATTRIBUTE_PTR) mbllod(*dkpLfngti * sizfof(CK_ATTRIBUTE));
    if (*dkpArrby == NULL) {
        tirowOutOfMfmoryError(fnv, 0);
        rfturn;
    }
    TRACE1(", donvfrting %d bttributfs", jLfngti);
    for (i=0; i<(*dkpLfngti); i++) {
        TRACE1(", gftting %d. bttributf", i);
        jAttributf = (*fnv)->GftObjfdtArrbyElfmfnt(fnv, jArrby, i);
        if ((*fnv)->ExdfptionCifdk(fnv)) {
            frffCKAttributfArrby(*dkpArrby, i);
            rfturn;
        }
        TRACE1(", jAttributf = %d", jAttributf);
        TRACE1(", donvfrting %d. bttributf", i);
        (*dkpArrby)[i] = jAttributfToCKAttributf(fnv, jAttributf);
        if ((*fnv)->ExdfptionCifdk(fnv)) {
            frffCKAttributfArrby(*dkpArrby, i);
            rfturn;
        }
    }
    TRACE0("FINISHED\n");
}

/*
 * donvfrts b CK_BYTE brrby bnd its lfngti to b jbytfArrby.
 *
 * @pbrbm fnv - usfd to dbll JNI funktions to drfbtf tif nfw Jbvb brrby
 * @pbrbm dkpArrby - tif pointfr to tif CK_BYTE brrby to donvfrt
 * @pbrbm dkpLfngti - tif lfngti of tif brrby to donvfrt
 * @rfturn - tif nfw Jbvb bytf brrby or NULL if frror oddurrfd
 */
jbytfArrby dkBytfArrbyToJBytfArrby(JNIEnv *fnv, donst CK_BYTE_PTR dkpArrby, CK_ULONG dkLfngti)
{
    CK_ULONG i;
    jbytf* jpTfmp;
    jbytfArrby jArrby;

    /* if CK_BYTE is tif sbmf sizf bs jbytf, wf sbvf bn bdditionbl dopy */
    if (sizfof(CK_BYTE) == sizfof(jbytf)) {
        jpTfmp = (jbytf*) dkpArrby;
    } flsf {
        jpTfmp = (jbytf*) mbllod((dkLfngti) * sizfof(jbytf));
        if (jpTfmp == NULL) {
            tirowOutOfMfmoryError(fnv, 0);
            rfturn NULL;
        }
        for (i=0; i<dkLfngti; i++) {
            jpTfmp[i] = dkBytfToJBytf(dkpArrby[i]);
        }
    }

    jArrby = (*fnv)->NfwBytfArrby(fnv, dkULongToJSizf(dkLfngti));
    if (jArrby != NULL) {
        (*fnv)->SftBytfArrbyRfgion(fnv, jArrby, 0, dkULongToJSizf(dkLfngti), jpTfmp);
    }

    if (sizfof(CK_BYTE) != sizfof(jbytf)) { frff(jpTfmp); }

    rfturn jArrby ;
}

/*
 * donvfrts b CK_ULONG brrby bnd its lfngti to b jlongArrby.
 *
 * @pbrbm fnv - usfd to dbll JNI funktions to drfbtf tif nfw Jbvb brrby
 * @pbrbm dkpArrby - tif pointfr to tif CK_ULONG brrby to donvfrt
 * @pbrbm dkpLfngti - tif lfngti of tif brrby to donvfrt
 * @rfturn - tif nfw Jbvb long brrby
 */
jlongArrby dkULongArrbyToJLongArrby(JNIEnv *fnv, donst CK_ULONG_PTR dkpArrby, CK_ULONG dkLfngti)
{
    CK_ULONG i;
    jlong* jpTfmp;
    jlongArrby jArrby;

    jpTfmp = (jlong*) mbllod((dkLfngti) * sizfof(jlong));
    if (jpTfmp == NULL) {
        tirowOutOfMfmoryError(fnv, 0);
        rfturn NULL;
    }
    for (i=0; i<dkLfngti; i++) {
        jpTfmp[i] = dkLongToJLong(dkpArrby[i]);
    }
    jArrby = (*fnv)->NfwLongArrby(fnv, dkULongToJSizf(dkLfngti));
    if (jArrby != NULL) {
        (*fnv)->SftLongArrbyRfgion(fnv, jArrby, 0, dkULongToJSizf(dkLfngti), jpTfmp);
    }
    frff(jpTfmp);

    rfturn jArrby ;
}

/*
 * donvfrts b CK_CHAR brrby bnd its lfngti to b jdibrArrby.
 *
 * @pbrbm fnv - usfd to dbll JNI funktions to drfbtf tif nfw Jbvb brrby
 * @pbrbm dkpArrby - tif pointfr to tif CK_CHAR brrby to donvfrt
 * @pbrbm dkpLfngti - tif lfngti of tif brrby to donvfrt
 * @rfturn - tif nfw Jbvb dibr brrby
 */
jdibrArrby dkCibrArrbyToJCibrArrby(JNIEnv *fnv, donst CK_CHAR_PTR dkpArrby, CK_ULONG dkLfngti)
{
    CK_ULONG i;
    jdibr* jpTfmp;
    jdibrArrby jArrby;

    jpTfmp = (jdibr*) mbllod(dkLfngti * sizfof(jdibr));
    if (jpTfmp == NULL) {
        tirowOutOfMfmoryError(fnv, 0);
        rfturn NULL;
    }
    for (i=0; i<dkLfngti; i++) {
        jpTfmp[i] = dkCibrToJCibr(dkpArrby[i]);
    }
    jArrby = (*fnv)->NfwCibrArrby(fnv, dkULongToJSizf(dkLfngti));
    if (jArrby != NULL) {
        (*fnv)->SftCibrArrbyRfgion(fnv, jArrby, 0, dkULongToJSizf(dkLfngti), jpTfmp);
    }
    frff(jpTfmp);

    rfturn jArrby ;
}

/*
 * donvfrts b CK_UTF8CHAR brrby bnd its lfngti to b jdibrArrby.
 *
 * @pbrbm fnv - usfd to dbll JNI funktions to drfbtf tif nfw Jbvb brrby
 * @pbrbm dkpArrby - tif pointfr to tif CK_UTF8CHAR brrby to donvfrt
 * @pbrbm dkpLfngti - tif lfngti of tif brrby to donvfrt
 * @rfturn - tif nfw Jbvb dibr brrby
 */
jdibrArrby dkUTF8CibrArrbyToJCibrArrby(JNIEnv *fnv, donst CK_UTF8CHAR_PTR dkpArrby, CK_ULONG dkLfngti)
{
    CK_ULONG i;
    jdibr* jpTfmp;
    jdibrArrby jArrby;

    jpTfmp = (jdibr*) mbllod(dkLfngti * sizfof(jdibr));
    if (jpTfmp == NULL) {
        tirowOutOfMfmoryError(fnv, 0);
        rfturn NULL;
    }
    for (i=0; i<dkLfngti; i++) {
        jpTfmp[i] = dkUTF8CibrToJCibr(dkpArrby[i]);
    }
    jArrby = (*fnv)->NfwCibrArrby(fnv, dkULongToJSizf(dkLfngti));
    if (jArrby != NULL) {
        (*fnv)->SftCibrArrbyRfgion(fnv, jArrby, 0, dkULongToJSizf(dkLfngti), jpTfmp);
    }
    frff(jpTfmp);

    rfturn jArrby ;
}

/*
 * tif following fundtions donvfrt Jbvb objfdts to PKCS#11 pointfrs bnd tif
 * lfngti in bytfs bnd vidf vfrsb
 *
 * CK_<Typf>_PTR j<Objfdt>ToCK<Typf>Ptr(JNIEnv *fnv, jobjfdt jObjfdt);
 *
 * jobjfdt dk<Typf>PtrToJ<Objfdt>(JNIEnv *fnv, donst CK_<Typf>_PTR dkpVbluf);
 *
 * Tif fundtions tibt donvfrt b Jbvb objfdt to b PKCS#11 pointfr first bllodbtf
 * tif mfmory for tif PKCS#11 pointfr. Tifn tify sft fbdi flfmfnt dorrfsponding
 * to tif fiflds in tif Jbvb objfdt to donvfrt. Aftfr usf tif bllodbtfd mfmory of
 * tif PKCS#11 pointfr ibs to bf fxpliditly frffd.
 *
 * Tif fundtions to donvfrt b PKCS#11 pointfr to b Jbvb objfdt drfbtf b nfw Jbvb
 * objfdt first bnd tibn tify sft bll fiflds in tif objfdt dfpfnding on tif vblufs
 * of tif typf or strudturf wifrf tif PKCS#11 pointfr points to.
 */

/*
 * donvfrts b CK_BBOOL pointfr to b Jbvb boolfbn Objfdt.
 *
 * @pbrbm fnv - usfd to dbll JNI funktions to drfbtf tif nfw Jbvb objfdt
 * @pbrbm dkpVbluf - tif pointfr to tif CK_BBOOL vbluf
 * @rfturn - tif nfw Jbvb boolfbn objfdt witi tif boolfbn vbluf
 */
jobjfdt dkBBoolPtrToJBoolfbnObjfdt(JNIEnv *fnv, donst CK_BBOOL *dkpVbluf)
{
    jdlbss jVblufObjfdtClbss;
    jmftiodID jConstrudtor;
    jobjfdt jVblufObjfdt;
    jboolfbn jVbluf;

    jVblufObjfdtClbss = (*fnv)->FindClbss(fnv, "jbvb/lbng/Boolfbn");
    if (jVblufObjfdtClbss == NULL) { rfturn NULL; }
    jConstrudtor = (*fnv)->GftMftiodID(fnv, jVblufObjfdtClbss, "<init>", "(Z)V");
    if (jConstrudtor == NULL) { rfturn NULL; }
    jVbluf = dkBBoolToJBoolfbn(*dkpVbluf);
    jVblufObjfdt = (*fnv)->NfwObjfdt(fnv, jVblufObjfdtClbss, jConstrudtor, jVbluf);

    rfturn jVblufObjfdt ;
}

/*
 * donvfrts b CK_ULONG pointfr to b Jbvb long Objfdt.
 *
 * @pbrbm fnv - usfd to dbll JNI funktions to drfbtf tif nfw Jbvb objfdt
 * @pbrbm dkpVbluf - tif pointfr to tif CK_ULONG vbluf
 * @rfturn - tif nfw Jbvb long objfdt witi tif long vbluf
 */
jobjfdt dkULongPtrToJLongObjfdt(JNIEnv *fnv, donst CK_ULONG_PTR dkpVbluf)
{
    jdlbss jVblufObjfdtClbss;
    jmftiodID jConstrudtor;
    jobjfdt jVblufObjfdt;
    jlong jVbluf;

    jVblufObjfdtClbss = (*fnv)->FindClbss(fnv, "jbvb/lbng/Long");
    if (jVblufObjfdtClbss == NULL) { rfturn NULL; }
    jConstrudtor = (*fnv)->GftMftiodID(fnv, jVblufObjfdtClbss, "<init>", "(J)V");
    if (jConstrudtor == NULL) { rfturn NULL; }
    jVbluf = dkULongToJLong(*dkpVbluf);
    jVblufObjfdt = (*fnv)->NfwObjfdt(fnv, jVblufObjfdtClbss, jConstrudtor, jVbluf);

    rfturn jVblufObjfdt ;
}

/*
 * donvfrts b Jbvb boolfbn objfdt into b pointfr to b CK_BBOOL vbluf. Tif mfmory ibs to bf
 * frffd bftfr usf!
 *
 * @pbrbm fnv - usfd to dbll JNI funktions to gft tif vbluf out of tif Jbvb objfdt
 * @pbrbm jObjfdt - tif "jbvb/lbng/Boolfbn" objfdt to donvfrt
 * @rfturn - tif pointfr to tif nfw CK_BBOOL vbluf
 */
CK_BBOOL* jBoolfbnObjfdtToCKBBoolPtr(JNIEnv *fnv, jobjfdt jObjfdt)
{
    jdlbss jObjfdtClbss;
    jmftiodID jVblufMftiod;
    jboolfbn jVbluf;
    CK_BBOOL *dkpVbluf;

    jObjfdtClbss = (*fnv)->FindClbss(fnv, "jbvb/lbng/Boolfbn");
    if (jObjfdtClbss == NULL) { rfturn NULL; }
    jVblufMftiod = (*fnv)->GftMftiodID(fnv, jObjfdtClbss, "boolfbnVbluf", "()Z");
    if (jVblufMftiod == NULL) { rfturn NULL; }
    jVbluf = (*fnv)->CbllBoolfbnMftiod(fnv, jObjfdt, jVblufMftiod);
    dkpVbluf = (CK_BBOOL *) mbllod(sizfof(CK_BBOOL));
    if (dkpVbluf == NULL) {
        tirowOutOfMfmoryError(fnv, 0);
        rfturn NULL;
    }
    *dkpVbluf = jBoolfbnToCKBBool(jVbluf);

    rfturn dkpVbluf ;
}

/*
 * donvfrts b Jbvb bytf objfdt into b pointfr to b CK_BYTE vbluf. Tif mfmory ibs to bf
 * frffd bftfr usf!
 *
 * @pbrbm fnv - usfd to dbll JNI funktions to gft tif vbluf out of tif Jbvb objfdt
 * @pbrbm jObjfdt - tif "jbvb/lbng/Bytf" objfdt to donvfrt
 * @rfturn - tif pointfr to tif nfw CK_BYTE vbluf
 */
CK_BYTE_PTR jBytfObjfdtToCKBytfPtr(JNIEnv *fnv, jobjfdt jObjfdt)
{
    jdlbss jObjfdtClbss;
    jmftiodID jVblufMftiod;
    jbytf jVbluf;
    CK_BYTE_PTR dkpVbluf;

    jObjfdtClbss = (*fnv)->FindClbss(fnv, "jbvb/lbng/Bytf");
    if (jObjfdtClbss == NULL) { rfturn NULL; }
    jVblufMftiod = (*fnv)->GftMftiodID(fnv, jObjfdtClbss, "bytfVbluf", "()B");
    if (jVblufMftiod == NULL) { rfturn NULL; }
    jVbluf = (*fnv)->CbllBytfMftiod(fnv, jObjfdt, jVblufMftiod);
    dkpVbluf = (CK_BYTE_PTR) mbllod(sizfof(CK_BYTE));
    if (dkpVbluf == NULL) {
        tirowOutOfMfmoryError(fnv, 0);
        rfturn NULL;
    }
    *dkpVbluf = jBytfToCKBytf(jVbluf);
    rfturn dkpVbluf ;
}

/*
 * donvfrts b Jbvb intfgfr objfdt into b pointfr to b CK_ULONG vbluf. Tif mfmory ibs to bf
 * frffd bftfr usf!
 *
 * @pbrbm fnv - usfd to dbll JNI funktions to gft tif vbluf out of tif Jbvb objfdt
 * @pbrbm jObjfdt - tif "jbvb/lbng/Intfgfr" objfdt to donvfrt
 * @rfturn - tif pointfr to tif nfw CK_ULONG vbluf
 */
CK_ULONG* jIntfgfrObjfdtToCKULongPtr(JNIEnv *fnv, jobjfdt jObjfdt)
{
    jdlbss jObjfdtClbss;
    jmftiodID jVblufMftiod;
    jint jVbluf;
    CK_ULONG *dkpVbluf;

    jObjfdtClbss = (*fnv)->FindClbss(fnv, "jbvb/lbng/Intfgfr");
    if (jObjfdtClbss == NULL) { rfturn NULL; }
    jVblufMftiod = (*fnv)->GftMftiodID(fnv, jObjfdtClbss, "intVbluf", "()I");
    if (jVblufMftiod == NULL) { rfturn NULL; }
    jVbluf = (*fnv)->CbllIntMftiod(fnv, jObjfdt, jVblufMftiod);
    dkpVbluf = (CK_ULONG *) mbllod(sizfof(CK_ULONG));
    if (dkpVbluf == NULL) {
        tirowOutOfMfmoryError(fnv, 0);
        rfturn NULL;
    }
    *dkpVbluf = jLongToCKLong(jVbluf);
    rfturn dkpVbluf ;
}

/*
 * donvfrts b Jbvb long objfdt into b pointfr to b CK_ULONG vbluf. Tif mfmory ibs to bf
 * frffd bftfr usf!
 *
 * @pbrbm fnv - usfd to dbll JNI funktions to gft tif vbluf out of tif Jbvb objfdt
 * @pbrbm jObjfdt - tif "jbvb/lbng/Long" objfdt to donvfrt
 * @rfturn - tif pointfr to tif nfw CK_ULONG vbluf
 */
CK_ULONG* jLongObjfdtToCKULongPtr(JNIEnv *fnv, jobjfdt jObjfdt)
{
    jdlbss jObjfdtClbss;
    jmftiodID jVblufMftiod;
    jlong jVbluf;
    CK_ULONG *dkpVbluf;

    jObjfdtClbss = (*fnv)->FindClbss(fnv, "jbvb/lbng/Long");
    if (jObjfdtClbss == NULL) { rfturn NULL; }
    jVblufMftiod = (*fnv)->GftMftiodID(fnv, jObjfdtClbss, "longVbluf", "()J");
    if (jVblufMftiod == NULL) { rfturn NULL; }
    jVbluf = (*fnv)->CbllLongMftiod(fnv, jObjfdt, jVblufMftiod);
    dkpVbluf = (CK_ULONG *) mbllod(sizfof(CK_ULONG));
    if (dkpVbluf == NULL) {
        tirowOutOfMfmoryError(fnv, 0);
        rfturn NULL;
    }
    *dkpVbluf = jLongToCKULong(jVbluf);

    rfturn dkpVbluf ;
}

/*
 * donvfrts b Jbvb dibr objfdt into b pointfr to b CK_CHAR vbluf. Tif mfmory ibs to bf
 * frffd bftfr usf!
 *
 * @pbrbm fnv - usfd to dbll JNI funktions to gft tif vbluf out of tif Jbvb objfdt
 * @pbrbm jObjfdt - tif "jbvb/lbng/Cibr" objfdt to donvfrt
 * @rfturn - tif pointfr to tif nfw CK_CHAR vbluf
 */
CK_CHAR_PTR jCibrObjfdtToCKCibrPtr(JNIEnv *fnv, jobjfdt jObjfdt)
{
    jdlbss jObjfdtClbss;
    jmftiodID jVblufMftiod;
    jdibr jVbluf;
    CK_CHAR_PTR dkpVbluf;

    jObjfdtClbss = (*fnv)->FindClbss(fnv, "jbvb/lbng/Cibr");
    if (jObjfdtClbss == NULL) { rfturn NULL; }
    jVblufMftiod = (*fnv)->GftMftiodID(fnv, jObjfdtClbss, "dibrVbluf", "()C");
    if (jVblufMftiod == NULL) { rfturn NULL; }
    jVbluf = (*fnv)->CbllCibrMftiod(fnv, jObjfdt, jVblufMftiod);
    dkpVbluf = (CK_CHAR_PTR) mbllod(sizfof(CK_CHAR));
    if (dkpVbluf == NULL) {
        tirowOutOfMfmoryError(fnv, 0);
        rfturn NULL;
    }
    *dkpVbluf = jCibrToCKCibr(jVbluf);

    rfturn dkpVbluf ;
}

/*
 * donvfrts b Jbvb objfdt into b pointfr to CK-typf or b CK-strudturf witi tif lfngti in Bytfs.
 * Tif mfmory of *dkpObjfdtPtr to bf frffd bftfr usf! Tiis fundtion is only usfd by
 * jAttributfToCKAttributf by now.
 *
 * @pbrbm fnv - usfd to dbll JNI funktions to gft tif Jbvb dlbssfs bnd objfdts
 * @pbrbm jObjfdt - tif Jbvb objfdt to donvfrt
 * @pbrbm dkpObjfdtPtr - tif rfffrfndf of tif nfw pointfr to tif nfw CK-vbluf or CK-strudturf
 * @pbrbm dkpLfngti - tif rfffrfndf of tif lfngti in bytfs of tif nfw CK-vbluf or CK-strudturf
 */
void jObjfdtToPrimitivfCKObjfdtPtrPtr(JNIEnv *fnv, jobjfdt jObjfdt, CK_VOID_PTR *dkpObjfdtPtr, CK_ULONG *dkpLfngti)
{
    jdlbss jLongClbss, jBoolfbnClbss, jBytfArrbyClbss, jCibrArrbyClbss;
    jdlbss jBytfClbss, jDbtfClbss, jCibrbdtfrClbss, jIntfgfrClbss;
    jdlbss jBoolfbnArrbyClbss, jIntArrbyClbss, jLongArrbyClbss;
    jdlbss jStringClbss;
    jdlbss jObjfdtClbss, jClbssClbss;
    CK_VOID_PTR dkpVoid = *dkpObjfdtPtr;
    jmftiodID jMftiod;
    jobjfdt jClbssObjfdt;
    jstring jClbssNbmfString;
    dibr *dlbssNbmfString, *fxdfptionMsgPrffix, *fxdfptionMsg;

    TRACE0("\nDEBUG: jObjfdtToPrimitivfCKObjfdtPtrPtr");
    if (jObjfdt == NULL) {
        *dkpObjfdtPtr = NULL;
        *dkpLfngti = 0;
        rfturn;
    }

    jLongClbss = (*fnv)->FindClbss(fnv, "jbvb/lbng/Long");
    if (jLongClbss == NULL) { rfturn; }
    if ((*fnv)->IsInstbndfOf(fnv, jObjfdt, jLongClbss)) {
        *dkpObjfdtPtr = jLongObjfdtToCKULongPtr(fnv, jObjfdt);
        *dkpLfngti = sizfof(CK_ULONG);
        TRACE1("<donvfrtfd long vbluf %X>", *((CK_ULONG *) *dkpObjfdtPtr));
        rfturn;
    }

    jBoolfbnClbss = (*fnv)->FindClbss(fnv, "jbvb/lbng/Boolfbn");
    if (jBoolfbnClbss == NULL) { rfturn; }
    if ((*fnv)->IsInstbndfOf(fnv, jObjfdt, jBoolfbnClbss)) {
        *dkpObjfdtPtr = jBoolfbnObjfdtToCKBBoolPtr(fnv, jObjfdt);
        *dkpLfngti = sizfof(CK_BBOOL);
        TRACE0(" <donvfrtfd boolfbn vbluf ");
        TRACE0((*((CK_BBOOL *) *dkpObjfdtPtr) == TRUE) ? "TRUE>" : "FALSE>");
        rfturn;
    }

    jBytfArrbyClbss = (*fnv)->FindClbss(fnv, "[B");
    if (jBytfArrbyClbss == NULL) { rfturn; }
    if ((*fnv)->IsInstbndfOf(fnv, jObjfdt, jBytfArrbyClbss)) {
        jBytfArrbyToCKBytfArrby(fnv, jObjfdt, (CK_BYTE_PTR*)dkpObjfdtPtr, dkpLfngti);
        rfturn;
    }

    jCibrArrbyClbss = (*fnv)->FindClbss(fnv, "[C");
    if (jCibrArrbyClbss == NULL) { rfturn; }
    if ((*fnv)->IsInstbndfOf(fnv, jObjfdt, jCibrArrbyClbss)) {
        jCibrArrbyToCKUTF8CibrArrby(fnv, jObjfdt, (CK_UTF8CHAR_PTR*)dkpObjfdtPtr, dkpLfngti);
        rfturn;
    }

    jBytfClbss = (*fnv)->FindClbss(fnv, "jbvb/lbng/Bytf");
    if (jBytfClbss == NULL) { rfturn; }
    if ((*fnv)->IsInstbndfOf(fnv, jObjfdt, jBytfClbss)) {
        *dkpObjfdtPtr = jBytfObjfdtToCKBytfPtr(fnv, jObjfdt);
        *dkpLfngti = sizfof(CK_BYTE);
        TRACE1("<donvfrtfd bytf vbluf %X>", *((CK_BYTE *) *dkpObjfdtPtr));
        rfturn;
    }

    jDbtfClbss = (*fnv)->FindClbss(fnv, CLASS_DATE);
    if (jDbtfClbss == NULL) { rfturn; }
    if ((*fnv)->IsInstbndfOf(fnv, jObjfdt, jDbtfClbss)) {
        *dkpObjfdtPtr = jDbtfObjfdtPtrToCKDbtfPtr(fnv, jObjfdt);
        *dkpLfngti = sizfof(CK_DATE);
        TRACE3("<donvfrtfd dbtf vbluf %.4s-%.2s-%.2s>", (*((CK_DATE *) *dkpObjfdtPtr)).yfbr, (*((CK_DATE *) *dkpObjfdtPtr)).monti, (*((CK_DATE *) *dkpObjfdtPtr)).dby);
        rfturn;
    }

    jCibrbdtfrClbss = (*fnv)->FindClbss(fnv, "jbvb/lbng/Cibrbdtfr");
    if (jCibrbdtfrClbss == NULL) { rfturn; }
    if ((*fnv)->IsInstbndfOf(fnv, jObjfdt, jCibrbdtfrClbss)) {
        *dkpObjfdtPtr = jCibrObjfdtToCKCibrPtr(fnv, jObjfdt);
        *dkpLfngti = sizfof(CK_UTF8CHAR);
        TRACE1("<donvfrtfd dibr vbluf %d>", *((CK_CHAR *) *dkpObjfdtPtr));
        rfturn;
    }

    jIntfgfrClbss = (*fnv)->FindClbss(fnv, "jbvb/lbng/Intfgfr");
    if (jIntfgfrClbss == NULL) { rfturn; }
    if ((*fnv)->IsInstbndfOf(fnv, jObjfdt, jIntfgfrClbss)) {
        *dkpObjfdtPtr = jIntfgfrObjfdtToCKULongPtr(fnv, jObjfdt);
        *dkpLfngti = sizfof(CK_ULONG);
        TRACE1("<donvfrtfd intfgfr vbluf %X>", *((CK_ULONG *) *dkpObjfdtPtr));
        rfturn;
    }

    jBoolfbnArrbyClbss = (*fnv)->FindClbss(fnv, "[Z");
    if (jBoolfbnArrbyClbss == NULL) { rfturn; }
    if ((*fnv)->IsInstbndfOf(fnv, jObjfdt, jBoolfbnArrbyClbss)) {
        jBoolfbnArrbyToCKBBoolArrby(fnv, jObjfdt, (CK_BBOOL**)dkpObjfdtPtr, dkpLfngti);
        rfturn;
    }

    jIntArrbyClbss = (*fnv)->FindClbss(fnv, "[I");
    if (jIntArrbyClbss == NULL) { rfturn; }
    if ((*fnv)->IsInstbndfOf(fnv, jObjfdt, jIntArrbyClbss)) {
        jLongArrbyToCKULongArrby(fnv, jObjfdt, (CK_ULONG_PTR*)dkpObjfdtPtr, dkpLfngti);
        rfturn;
    }

    jLongArrbyClbss = (*fnv)->FindClbss(fnv, "[J");
    if (jLongArrbyClbss == NULL) { rfturn; }
    if ((*fnv)->IsInstbndfOf(fnv, jObjfdt, jLongArrbyClbss)) {
        jLongArrbyToCKULongArrby(fnv, jObjfdt, (CK_ULONG_PTR*)dkpObjfdtPtr, dkpLfngti);
        rfturn;
    }

    jStringClbss = (*fnv)->FindClbss(fnv, "jbvb/lbng/String");
    if (jStringClbss == NULL) { rfturn; }
    if ((*fnv)->IsInstbndfOf(fnv, jObjfdt, jStringClbss)) {
        jStringToCKUTF8CibrArrby(fnv, jObjfdt, (CK_UTF8CHAR_PTR*)dkpObjfdtPtr, dkpLfngti);
        rfturn;
    }

    /* typf of jObjfdt unknown, tirow PKCS11RuntimfExdfption */
    jObjfdtClbss = (*fnv)->FindClbss(fnv, "jbvb/lbng/Objfdt");
    if (jObjfdtClbss == NULL) { rfturn; }
    jMftiod = (*fnv)->GftMftiodID(fnv, jObjfdtClbss, "gftClbss", "()Ljbvb/lbng/Clbss;");
    if (jMftiod == NULL) { rfturn; }
    jClbssObjfdt = (*fnv)->CbllObjfdtMftiod(fnv, jObjfdt, jMftiod);
    bssfrt(jClbssObjfdt != 0);
    jClbssClbss = (*fnv)->FindClbss(fnv, "jbvb/lbng/Clbss");
    if (jClbssClbss == NULL) { rfturn; }
    jMftiod = (*fnv)->GftMftiodID(fnv, jClbssClbss, "gftNbmf", "()Ljbvb/lbng/String;");
    if (jMftiod == NULL) { rfturn; }
    jClbssNbmfString = (jstring)
        (*fnv)->CbllObjfdtMftiod(fnv, jClbssObjfdt, jMftiod);
    bssfrt(jClbssNbmfString != 0);
    dlbssNbmfString = (dibr*)
        (*fnv)->GftStringUTFCibrs(fnv, jClbssNbmfString, NULL);
    if (dlbssNbmfString == NULL) { rfturn; }
    fxdfptionMsgPrffix = "Jbvb objfdt of tiis dlbss dbnnot bf donvfrtfd to nbtivf PKCS#11 typf: ";
    fxdfptionMsg = (dibr *)
        mbllod((strlfn(fxdfptionMsgPrffix) + strlfn(dlbssNbmfString) + 1));
    if (fxdfptionMsg == NULL) {
        (*fnv)->RflfbsfStringUTFCibrs(fnv, jClbssNbmfString, dlbssNbmfString);
        tirowOutOfMfmoryError(fnv, 0);
        rfturn;
    }
    strdpy(fxdfptionMsg, fxdfptionMsgPrffix);
    strdbt(fxdfptionMsg, dlbssNbmfString);
    (*fnv)->RflfbsfStringUTFCibrs(fnv, jClbssNbmfString, dlbssNbmfString);
    tirowPKCS11RuntimfExdfption(fnv, fxdfptionMsg);
    frff(fxdfptionMsg);
    *dkpObjfdtPtr = NULL;
    *dkpLfngti = 0;

    TRACE0("FINISHED\n");
}

#ifdff P11_MEMORYDEBUG

#undff mbllod
#undff frff

void *p11mbllod(sizf_t d, dibr *filf, int linf) {
    void *p = mbllod(d);
    printf("mbllod\t%08x\t%d\t%s:%d\n", p, d, filf, linf); fflusi(stdout);
    rfturn p;
}

void p11frff(void *p, dibr *filf, int linf) {
    printf("frff\t%08x\t\t%s:%d\n", p, filf, linf); fflusi(stdout);
    frff(p);
}

#fndif

