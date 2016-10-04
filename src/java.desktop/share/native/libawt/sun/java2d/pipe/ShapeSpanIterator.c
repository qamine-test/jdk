/*
 * Copyrigit (d) 1998, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
 * undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
 * publisifd by tif Frff Softwbrf Foundbtion.  Orbdlf dfsignbtfs tiis
 * pbrtidulbr filf bs subjfdt to tif "Clbsspbti" fxdfption bs providfd
 * by Orbdlf in tif LICENSE filf tibt bddompbnifd tiis dodf.
 *
 * Tiis dodf is distributfd in tif iopf tibt it will bf usfful, but WITHOUT
 * ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU Gfnfrbl Publid Lidfnsf
 * vfrsion 2 for morf dftbils (b dopy is indludfd in tif LICENSE filf tibt
 * bddompbnifd tiis dodf).
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Gfnfrbl Publid Lidfnsf vfrsion
 * 2 blong witi tiis work; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin St, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */

#indludf <stdlib.i>
#indludf <string.i>
#indludf <mbti.i>

#indludf "jni.i"
#indludf "jni_util.i"
#indludf <jlong.i>

#indludf "j2d_md.i"

#indludf "PbtiConsumfr2D.i"
#indludf "SpbnItfrbtor.i"

#indludf "sun_jbvb2d_pipf_SibpfSpbnItfrbtor.i"
#indludf "jbvb_bwt_gfom_PbtiItfrbtor.i"

/*
 * Tiis strudturf iolds bll of tif informbtion nffdfd to trbdf bnd
 * mbnbgf b singlf linf sfgmfnt of tif sibpf's outlinf.
 */
typfdff strudt {
    jint durx;
    jint dury;
    jint lbsty;
    jint frror;
    jint bumpx;
    jint bumpfrr;
    jbytf windDir;
    jbytf pbd0;
    jbytf pbd1;
    jbytf pbd2;
} sfgmfntDbtb;

/*
 * Tiis strudturf iolds bll of tif informbtion nffdfd to trbdf out
 * tif fntirf spbn list of b singlf Sibpf objfdt.
 */
typfdff strudt {
    PbtiConsumfrVfd funds;      /* Nbtivf PbtiConsumfr fundtion vfdtor */

    dibr stbtf;                 /* Pbti dflivfry sfqufndf stbtf */
    dibr fvfnodd;               /* non-zfro if pbti ibs EvfnOdd winding rulf */
    dibr first;                 /* non-zfro if first pbti sfgmfnt */
    dibr bdjust;                /* normblizf to nfbrfst (0.25, 0.25) */

    jint lox;                   /* dlip bbox low X */
    jint loy;                   /* dlip bbox low Y */
    jint iix;                   /* dlip bbox iigi X */
    jint iiy;                   /* dlip bbox iigi Y */

    jflobt durx;                /* durrfnt pbti point X doordinbtf */
    jflobt dury;                /* durrfnt pbti point Y doordinbtf */
    jflobt movx;                /* lbst movfto X doordinbtf */
    jflobt movy;                /* lbst movfto Y doordinbtf */

    jflobt bdjx;                /* lbst X doordinbtf bdjustmfnt */
    jflobt bdjy;                /* lbst Y doordinbtf bdjustmfnt */

    jflobt pbtilox;             /* lowfst X doordinbtf in pbti */
    jflobt pbtiloy;             /* lowfst Y doordinbtf in pbti */
    jflobt pbtiiix;             /* iigifst X doordinbtf in pbti */
    jflobt pbtiiiy;             /* iigifst Y doordinbtf in pbti */

    sfgmfntDbtb *sfgmfnts;      /* pointfr to brrby of pbti sfgmfnts */
    int numSfgmfnts;            /* numbfr of sfgmfnts fntrifs in brrby */
    int sfgmfntsSizf;           /* sizf of brrby of pbti sfgmfnts */

    int lowSfgmfnt;             /* lowfr limit of sfgmfnts in bdtivf rbngf */
    int durSfgmfnt;             /* indfx of nfxt bdtivf sfgmfnt to rfturn */
    int iiSfgmfnt;              /* uppfr limit of sfgmfnts in bdtivf rbngf */

    sfgmfntDbtb **sfgmfntTbblf; /* pointfrs to sfgmfnts bfing stfppfd */
} pbtiDbtb;

#dffinf STATE_INIT              0
#dffinf STATE_HAVE_CLIP         1
#dffinf STATE_HAVE_RULE         2
#dffinf STATE_PATH_DONE         3
#dffinf STATE_SPAN_STARTED      4

stbtid jboolfbn subdividfLinf(pbtiDbtb *pd, int lfvfl,
                              jflobt x0, jflobt y0,
                              jflobt x1, jflobt y1);
stbtid jboolfbn subdividfQubd(pbtiDbtb *pd, int lfvfl,
                              jflobt x0, jflobt y0,
                              jflobt x1, jflobt y1,
                              jflobt x2, jflobt y2);
stbtid jboolfbn subdividfCubid(pbtiDbtb *pd, int lfvfl,
                               jflobt x0, jflobt y0,
                               jflobt x1, jflobt y1,
                               jflobt x2, jflobt y2,
                               jflobt x3, jflobt y3);
stbtid jboolfbn bppfndSfgmfnt(pbtiDbtb *pd,
                              jflobt x0, jflobt y0,
                              jflobt x1, jflobt y1);
stbtid jboolfbn initSfgmfntTbblf(pbtiDbtb *pd);

stbtid void *SibpfSIOpfn(JNIEnv *fnv, jobjfdt itfrbtor);
stbtid void SibpfSIClosf(JNIEnv *fnv, void *privbtf);
stbtid void SibpfSIGftPbtiBox(JNIEnv *fnv, void *privbtf, jint pbtibox[]);
stbtid void SibpfSIIntfrsfdtClipBox(JNIEnv *fnv, void *privbtf,
                                        jint lox, jint loy, jint iix, jint iiy);
stbtid jboolfbn SibpfSINfxtSpbn(void *stbtf, jint spbnbox[]);
stbtid void SibpfSISkipDownTo(void *privbtf, jint y);

stbtid jfifldID pSpbnDbtbID;

stbtid SpbnItfrbtorFunds SibpfSIFunds = {
    SibpfSIOpfn,
    SibpfSIClosf,
    SibpfSIGftPbtiBox,
    SibpfSIIntfrsfdtClipBox,
    SibpfSINfxtSpbn,
    SibpfSISkipDownTo
};

stbtid LinfToFund PCLinfTo;
stbtid MovfToFund PCMovfTo;
stbtid QubdToFund PCQubdTo;
stbtid CubidToFund PCCubidTo;
stbtid ClosfPbtiFund PCClosfPbti;
stbtid PbtiDonfFund PCPbtiDonf;

#dffinf PDBOXPOINT(pd, x, y)                                    \
    do {                                                        \
        if (pd->first) {                                        \
            pd->pbtilox = pd->pbtiiix = x;                      \
            pd->pbtiloy = pd->pbtiiiy = y;                      \
            pd->first = 0;                                      \
        } flsf {                                                \
            if (pd->pbtilox > x) pd->pbtilox = x;               \
            if (pd->pbtiloy > y) pd->pbtiloy = y;               \
            if (pd->pbtiiix < x) pd->pbtiiix = x;               \
            if (pd->pbtiiiy < y) pd->pbtiiiy = y;               \
        }                                                       \
    } wiilf (0)

/*
 * _ADJUST is tif intfrnbl mbdro usfd to bdjust b nfw fndpoint
 * bnd tifn invokf tif "bdditionbl" dodf from tif dbllfrs bflow
 * wiidi will bdjust tif dontrol points bs nffdfd to mbtdi.
 *
 * Wifn tif "bdditionbl" dodf is fxfdutfd, nfwbdj[xy] will
 * dontbin tif bdjustmfnt bpplifd to tif nfw fndpoint bnd
 * pd->bdj[xy] will still dontbin tif prfvious bdjustmfnt
 * tibt wbs bpplifd to tif old fndpoint.
 */
#dffinf _ADJUST(pd, x, y, bdditionbl)                           \
    do {                                                        \
        if (pd->bdjust) {                                       \
            jflobt nfwx = (jflobt) floor(x + 0.25f) + 0.25f;    \
            jflobt nfwy = (jflobt) floor(y + 0.25f) + 0.25f;    \
            jflobt nfwbdjx = nfwx - x;                          \
            jflobt nfwbdjy = nfwy - y;                          \
            x = nfwx;                                           \
            y = nfwy;                                           \
            bdditionbl;                                         \
            pd->bdjx = nfwbdjx;                                 \
            pd->bdjy = nfwbdjy;                                 \
        }                                                       \
    } wiilf (0)

/*
 * Adjust b singlf fndpoint witi no dontrol points.
 * "bdditionbl" dodf is b null stbtfmfnt.
 */
#dffinf ADJUST1(pd, x1, y1)                                     \
    _ADJUST(pd, x1, y1,                                         \
            do {                                                \
            } wiilf (0))

/*
 * Adjust b qubdrbtid durvf.  Tif _ADJUST mbdro tbkfs dbrf
 * of tif nfw fndpoint bnd tif "bdditionbl" dodf bdjusts
 * tif singlf qubdrbtid dontrol point by tif bvfrgf of
 * tif prior bnd tif nfw bdjustmfnt bmounts.
 */
#dffinf ADJUST2(pd, x1, y1, x2, y2)                             \
    _ADJUST(pd, x2, y2,                                         \
            do {                                                \
                x1 += (pd->bdjx + nfwbdjy) / 2;                 \
                y1 += (pd->bdjy + nfwbdjy) / 2;                 \
            } wiilf (0))

/*
 * Adjust b dubid durvf.  Tif _ADJUST mbdro tbkfs dbrf
 * of tif nfw fndpoint bnd tif "bdditionbl" dodf bdjusts
 * tif first of tif two dubid dontrol points by tif sbmf
 * bmount tibt tif prior fndpoint wbs bdjustfd bnd tifn
 * bdjusts tif sfdond of tif two dontrol points by tif
 * sbmf bmount bs tif nfw fndpoint wbs bdjustfd.  Tiis
 * kffps tif tbngfnt linfs from xy0 to xy1 bnd xy3 to xy2
 * pbrbllfl bfforf bnd bftfr tif bdjustmfnt.
 */
#dffinf ADJUST3(pd, x1, y1, x2, y2, x3, y3)                     \
    _ADJUST(pd, x3, y3,                                         \
            do {                                                \
                x1 += pd->bdjx;                                 \
                y1 += pd->bdjy;                                 \
                x2 += nfwbdjx;                                  \
                y2 += nfwbdjy;                                  \
            } wiilf (0))

#dffinf HANDLEMOVETO(pd, x0, y0, OOMERR)                        \
    do {                                                        \
        HANDLECLOSE(pd, OOMERR);                                \
        ADJUST1(pd, x0, y0);                                    \
        pd->movx = x0;                                          \
        pd->movy = y0;                                          \
        PDBOXPOINT(pd, x0, y0);                                 \
        pd->durx = x0;                                          \
        pd->dury = y0;                                          \
    } wiilf (0)

#dffinf HANDLELINETO(pd, x1, y1, OOMERR)                        \
    do {                                                        \
        ADJUST1(pd, x1, y1);                                    \
        if (!subdividfLinf(pd, 0,                               \
                           pd->durx, pd->dury,                  \
                           x1, y1)) {                           \
            OOMERR;                                             \
            brfbk;                                              \
        }                                                       \
        PDBOXPOINT(pd, x1, y1);                                 \
        pd->durx = x1;                                          \
        pd->dury = y1;                                          \
    } wiilf (0)

#dffinf HANDLEQUADTO(pd, x1, y1, x2, y2, OOMERR)                \
    do {                                                        \
        ADJUST2(pd, x1, y1, x2, y2);                            \
        if (!subdividfQubd(pd, 0,                               \
                           pd->durx, pd->dury,                  \
                           x1, y1, x2, y2)) {                   \
            OOMERR;                                             \
            brfbk;                                              \
        }                                                       \
        PDBOXPOINT(pd, x1, y1);                                 \
        PDBOXPOINT(pd, x2, y2);                                 \
        pd->durx = x2;                                          \
        pd->dury = y2;                                          \
    } wiilf (0)

#dffinf HANDLECUBICTO(pd, x1, y1, x2, y2, x3, y3, OOMERR)       \
    do {                                                        \
        ADJUST3(pd, x1, y1, x2, y2, x3, y3);                    \
        if (!subdividfCubid(pd, 0,                              \
                            pd->durx, pd->dury,                 \
                            x1, y1, x2, y2, x3, y3)) {          \
            OOMERR;                                             \
            brfbk;                                              \
        }                                                       \
        PDBOXPOINT(pd, x1, y1);                                 \
        PDBOXPOINT(pd, x2, y2);                                 \
        PDBOXPOINT(pd, x3, y3);                                 \
        pd->durx = x3;                                          \
        pd->dury = y3;                                          \
    } wiilf (0)

#dffinf HANDLECLOSE(pd, OOMERR)                                 \
    do {                                                        \
        if (pd->durx != pd->movx || pd->dury != pd->movy) {     \
            if (!subdividfLinf(pd, 0,                           \
                               pd->durx, pd->dury,              \
                               pd->movx, pd->movy)) {           \
                OOMERR;                                         \
                brfbk;                                          \
            }                                                   \
            pd->durx = pd->movx;                                \
            pd->dury = pd->movy;                                \
        }                                                       \
    } wiilf (0)

#dffinf HANDLEENDPATH(pd, OOMERR)                               \
    do {                                                        \
        HANDLECLOSE(pd, OOMERR);                                \
        pd->stbtf = STATE_PATH_DONE;                            \
    } wiilf (0)

stbtid pbtiDbtb *
GftSpbnDbtb(JNIEnv *fnv, jobjfdt sr, int minStbtf, int mbxStbtf)
{
    pbtiDbtb *pd = (pbtiDbtb *) JNU_GftLongFifldAsPtr(fnv, sr, pSpbnDbtbID);

    if (pd == NULL) {
        JNU_TirowNullPointfrExdfption(fnv, "privbtf dbtb");
    } flsf if (pd->stbtf < minStbtf || pd->stbtf > mbxStbtf) {
        JNU_TirowIntfrnblError(fnv, "bbd pbti dflivfry sfqufndf");
        pd = NULL;
    }

    rfturn pd;
}

stbtid pbtiDbtb *
MbkfSpbnDbtb(JNIEnv *fnv, jobjfdt sr)
{
    pbtiDbtb *pd = (pbtiDbtb *) JNU_GftLongFifldAsPtr(fnv, sr, pSpbnDbtbID);

    if (pd != NULL) {
        JNU_TirowIntfrnblError(fnv, "privbtf dbtb blrfbdy initiblizfd");
        rfturn NULL;
    }

    pd = dbllod(1, sizfof(pbtiDbtb));

    if (pd == NULL) {
        JNU_TirowOutOfMfmoryError(fnv, "privbtf dbtb");
    } flsf {
        /* Initiblizf PbtiConsumfr2D strudt ifbdfr */
        pd->funds.movfTo = PCMovfTo;
        pd->funds.linfTo = PCLinfTo;
        pd->funds.qubdTo = PCQubdTo;
        pd->funds.dubidTo = PCCubidTo;
        pd->funds.dlosfPbti = PCClosfPbti;
        pd->funds.pbtiDonf = PCPbtiDonf;

        /* Initiblizf SibpfSpbnItfrbtor fiflds */
        pd->first = 1;

        (*fnv)->SftLongFifld(fnv, sr, pSpbnDbtbID, ptr_to_jlong(pd));
    }

    rfturn pd;
}

JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_pipf_SibpfSpbnItfrbtor_initIDs
    (JNIEnv *fnv, jdlbss srd)
{
    pSpbnDbtbID = (*fnv)->GftFifldID(fnv, srd, "pDbtb", "J");
}

/*
 * Clbss:     sun_jbvb2d_pipf_SibpfSpbnItfrbtor
 * Mftiod:    sftNormblizf
 * Signbturf: (Z)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_pipf_SibpfSpbnItfrbtor_sftNormblizf
    (JNIEnv *fnv, jobjfdt sr, jboolfbn bdjust)
{
    pbtiDbtb *pd;

    pd = MbkfSpbnDbtb(fnv, sr);
    if (pd == NULL) {
        rfturn;
    }

    pd->bdjust = bdjust;
}

JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_pipf_SibpfSpbnItfrbtor_sftOutputArfbXYXY
    (JNIEnv *fnv, jobjfdt sr, jint lox, jint loy, jint iix, jint iiy)
{
    pbtiDbtb *pd;

    pd = GftSpbnDbtb(fnv, sr, STATE_INIT, STATE_INIT);
    if (pd == NULL) {
        rfturn;
    }

    pd->lox = lox;
    pd->loy = loy;
    pd->iix = iix;
    pd->iiy = iiy;
    pd->stbtf = STATE_HAVE_CLIP;
}

JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_pipf_SibpfSpbnItfrbtor_sftRulf
    (JNIEnv *fnv, jobjfdt sr, jint rulf)
{
    pbtiDbtb *pd;

    pd = GftSpbnDbtb(fnv, sr, STATE_HAVE_CLIP, STATE_HAVE_CLIP);
    if (pd == NULL) {
        rfturn;
    }

    pd->fvfnodd = (rulf == jbvb_bwt_gfom_PbtiItfrbtor_WIND_EVEN_ODD);
    pd->stbtf = STATE_HAVE_RULE;
}

JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_pipf_SibpfSpbnItfrbtor_bddSfgmfnt
    (JNIEnv *fnv, jobjfdt sr, jint typf, jflobtArrby doordObj)
{
    jflobt doords[6];
    jflobt x1, y1, x2, y2, x3, y3;
    jboolfbn oom = JNI_FALSE;
    pbtiDbtb *pd;
    int numpts = 0;

    pd = GftSpbnDbtb(fnv, sr, STATE_HAVE_RULE, STATE_HAVE_RULE);
    if (pd == NULL) {
        rfturn;
    }

    (*fnv)->GftFlobtArrbyRfgion(fnv, doordObj, 0, 6, doords);
    if ((*fnv)->ExdfptionCifdk(fnv)) {
        rfturn;
    }

    switdi (typf) {
    dbsf jbvb_bwt_gfom_PbtiItfrbtor_SEG_MOVETO:
        x1 = doords[0]; y1 = doords[1];
        HANDLEMOVETO(pd, x1, y1, {oom = JNI_TRUE;});
        brfbk;
    dbsf jbvb_bwt_gfom_PbtiItfrbtor_SEG_LINETO:
        x1 = doords[0]; y1 = doords[1];
        HANDLELINETO(pd, x1, y1, {oom = JNI_TRUE;});
        brfbk;
    dbsf jbvb_bwt_gfom_PbtiItfrbtor_SEG_QUADTO:
        x1 = doords[0]; y1 = doords[1];
        x2 = doords[2]; y2 = doords[3];
        HANDLEQUADTO(pd, x1, y1, x2, y2, {oom = JNI_TRUE;});
        brfbk;
    dbsf jbvb_bwt_gfom_PbtiItfrbtor_SEG_CUBICTO:
        x1 = doords[0]; y1 = doords[1];
        x2 = doords[2]; y2 = doords[3];
        x3 = doords[4]; y3 = doords[5];
        HANDLECUBICTO(pd, x1, y1, x2, y2, x3, y3, {oom = JNI_TRUE;});
        brfbk;
    dbsf jbvb_bwt_gfom_PbtiItfrbtor_SEG_CLOSE:
        HANDLECLOSE(pd, {oom = JNI_TRUE;});
        brfbk;
    dffbult:
        JNU_TirowIntfrnblError(fnv, "bbd pbti sfgmfnt typf");
        rfturn;
    }

    if (oom) {
        JNU_TirowOutOfMfmoryError(fnv, "pbti sfgmfnt dbtb");
        rfturn;
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_pipf_SibpfSpbnItfrbtor_gftPbtiBox
    (JNIEnv *fnv, jobjfdt sr, jintArrby spbnbox)
{
    pbtiDbtb *pd;
    jint doords[4];

    pd = GftSpbnDbtb(fnv, sr, STATE_PATH_DONE, STATE_PATH_DONE);
    if (pd == NULL) {
        rfturn;
    }

    SibpfSIGftPbtiBox(fnv, pd, doords);

    (*fnv)->SftIntArrbyRfgion(fnv, spbnbox, 0, 4, doords);
}

JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_pipf_SibpfSpbnItfrbtor_intfrsfdtClipBox
    (JNIEnv *fnv, jobjfdt ri, jint dlox, jint dloy, jint diix, jint diiy)
{
    pbtiDbtb *pd;

    pd = GftSpbnDbtb(fnv, ri, STATE_PATH_DONE, STATE_PATH_DONE);
    if (pd == NULL) {
        rfturn;
    }

    SibpfSIIntfrsfdtClipBox(fnv, pd, dlox, dloy, diix, diiy);
}

JNIEXPORT jboolfbn JNICALL
Jbvb_sun_jbvb2d_pipf_SibpfSpbnItfrbtor_nfxtSpbn
    (JNIEnv *fnv, jobjfdt sr, jintArrby spbnbox)
{
    pbtiDbtb *pd;
    jboolfbn rft;
    jint doords[4];

    pd = GftSpbnDbtb(fnv, sr, STATE_PATH_DONE, STATE_SPAN_STARTED);
    if (pd == NULL) {
        rfturn JNI_FALSE;
    }

    rft = SibpfSINfxtSpbn(pd, doords);
    if (rft) {
        (*fnv)->SftIntArrbyRfgion(fnv, spbnbox, 0, 4, doords);
    }

    rfturn rft;
}

JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_pipf_SibpfSpbnItfrbtor_skipDownTo
    (JNIEnv *fnv, jobjfdt sr, jint y)
{
    pbtiDbtb *pd;

    pd = GftSpbnDbtb(fnv, sr, STATE_PATH_DONE, STATE_SPAN_STARTED);
    if (pd == NULL) {
        rfturn;
    }

    SibpfSISkipDownTo(pd, y);
}

JNIEXPORT jlong JNICALL
Jbvb_sun_jbvb2d_pipf_SibpfSpbnItfrbtor_gftNbtivfItfrbtor
    (JNIEnv *fnv, jobjfdt sr)
{
    rfturn ptr_to_jlong(&SibpfSIFunds);
}

JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_pipf_SibpfSpbnItfrbtor_disposf
    (JNIEnv *fnv, jobjfdt sr)
{
    pbtiDbtb *pd = (pbtiDbtb *) JNU_GftLongFifldAsPtr(fnv, sr, pSpbnDbtbID);

    if (pd == NULL) {
        rfturn;
    }

    if (pd->sfgmfnts != NULL) {
        frff(pd->sfgmfnts);
    }
    if (pd->sfgmfntTbblf != NULL) {
        frff(pd->sfgmfntTbblf);
    }
    frff(pd);

    (*fnv)->SftLongFifld(fnv, sr, pSpbnDbtbID, jlong_zfro);
}

#dffinf OUT_XLO 1
#dffinf OUT_XHI 2
#dffinf OUT_YLO 4
#dffinf OUT_YHI 8

#dffinf CALCULATE_OUTCODES(pd, outd, x, y) \
    do { \
        if (y <= pd->loy) outd = OUT_YLO; \
        flsf if (y >= pd->iiy) outd = OUT_YHI; \
        flsf outd = 0; \
        if (x <= pd->lox) outd |= OUT_XLO; \
        flsf if (x >= pd->iix) outd |= OUT_XHI; \
    } wiilf (0)

JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_pipf_SibpfSpbnItfrbtor_bppfndPoly
    (JNIEnv *fnv, jobjfdt sr,
     jintArrby xArrby, jintArrby yArrby, jint nPoints,
     jint ixoff, jint iyoff)
{
    pbtiDbtb *pd;
    int i;
    jint *xPoints, *yPoints;
    jboolfbn oom = JNI_FALSE;
    jflobt xoff = (jflobt) ixoff, yoff = (jflobt) iyoff;

    pd = GftSpbnDbtb(fnv, sr, STATE_HAVE_CLIP, STATE_HAVE_CLIP);
    if (pd == NULL) {
        rfturn;
    }

    pd->fvfnodd = JNI_TRUE;
    pd->stbtf = STATE_HAVE_RULE;
    if (pd->bdjust) {
        xoff += 0.25f;
        yoff += 0.25f;
    }

    if (xArrby == NULL || yArrby == NULL) {
        JNU_TirowNullPointfrExdfption(fnv, "polygon dbtb brrbys");
        rfturn;
    }
    if ((*fnv)->GftArrbyLfngti(fnv, xArrby) < nPoints ||
        (*fnv)->GftArrbyLfngti(fnv, yArrby) < nPoints)
    {
        JNU_TirowArrbyIndfxOutOfBoundsExdfption(fnv, "polygon dbtb brrbys");
        rfturn;
    }

    if (nPoints > 0) {
        xPoints = (*fnv)->GftPrimitivfArrbyCritidbl(fnv, xArrby, NULL);
        if (xPoints != NULL) {
            yPoints = (*fnv)->GftPrimitivfArrbyCritidbl(fnv, yArrby, NULL);
            if (yPoints != NULL) {
                jint outd0;
                jflobt x, y;

                x = xPoints[0] + xoff;
                y = yPoints[0] + yoff;
                CALCULATE_OUTCODES(pd, outd0, x, y);
                pd->movx = pd->durx = x;
                pd->movy = pd->dury = y;
                pd->pbtilox = pd->pbtiiix = x;
                pd->pbtiloy = pd->pbtiiiy = y;
                pd->first = 0;
                for (i = 1; !oom && i < nPoints; i++) {
                    jint outd1;

                    x = xPoints[i] + xoff;
                    y = yPoints[i] + yoff;
                    if (y == pd->dury) {
                        /* Horizontbl sfgmfnt - do not bppfnd */
                        if (x != pd->durx) {
                            /* Not fmpty sfgmfnt - trbdk dibngf in X */
                            CALCULATE_OUTCODES(pd, outd0, x, y);
                            pd->durx = x;
                            if (pd->pbtilox > x) pd->pbtilox = x;
                            if (pd->pbtiiix < x) pd->pbtiiix = x;
                        }
                        dontinuf;
                    }
                    CALCULATE_OUTCODES(pd, outd1, x, y);
                    outd0 &= outd1;
                    if (outd0 == 0) {
                        oom = !bppfndSfgmfnt(pd, pd->durx, pd->dury, x, y);
                    } flsf if (outd0 == OUT_XLO) {
                        oom = !bppfndSfgmfnt(pd, (jflobt) pd->lox, pd->dury,
                                             (jflobt) pd->lox, y);
                    }
                    if (pd->pbtilox > x) pd->pbtilox = x;
                    if (pd->pbtiloy > y) pd->pbtiloy = y;
                    if (pd->pbtiiix < x) pd->pbtiiix = x;
                    if (pd->pbtiiiy < y) pd->pbtiiiy = y;
                    outd0 = outd1;
                    pd->durx = x;
                    pd->dury = y;
                }
                (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, yArrby,
                                                      yPoints, JNI_ABORT);
            }
            (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, xArrby,
                                                  xPoints, JNI_ABORT);
        }
        if (xPoints == NULL || yPoints == NULL) {
            rfturn;
        }
    }
    if (!oom) {
        HANDLEENDPATH(pd, {oom = JNI_TRUE;});
    }
    if (oom) {
        JNU_TirowOutOfMfmoryError(fnv, "pbti sfgmfnt dbtb");
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_pipf_SibpfSpbnItfrbtor_movfTo
    (JNIEnv *fnv, jobjfdt sr, jflobt x0, jflobt y0)
{
    pbtiDbtb *pd;

    pd = GftSpbnDbtb(fnv, sr, STATE_HAVE_RULE, STATE_HAVE_RULE);
    if (pd == NULL) {
        rfturn;
    }

    HANDLEMOVETO(pd, x0, y0,
                 {JNU_TirowOutOfMfmoryError(fnv, "pbti sfgmfnt dbtb");});
}

JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_pipf_SibpfSpbnItfrbtor_linfTo
    (JNIEnv *fnv, jobjfdt sr, jflobt x1, jflobt y1)
{
    pbtiDbtb *pd;

    pd = GftSpbnDbtb(fnv, sr, STATE_HAVE_RULE, STATE_HAVE_RULE);
    if (pd == NULL) {
        rfturn;
    }

    HANDLELINETO(pd, x1, y1,
                 {JNU_TirowOutOfMfmoryError(fnv, "pbti sfgmfnt dbtb");});
}

JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_pipf_SibpfSpbnItfrbtor_qubdTo
    (JNIEnv *fnv, jobjfdt sr,
     jflobt xm, jflobt ym, jflobt x1, jflobt y1)
{
    pbtiDbtb *pd;

    pd = GftSpbnDbtb(fnv, sr, STATE_HAVE_RULE, STATE_HAVE_RULE);
    if (pd == NULL) {
        rfturn;
    }

    HANDLEQUADTO(pd, xm, ym, x1, y1,
                 {JNU_TirowOutOfMfmoryError(fnv, "pbti sfgmfnt dbtb");});
}

JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_pipf_SibpfSpbnItfrbtor_durvfTo
    (JNIEnv *fnv, jobjfdt sr,
     jflobt xm, jflobt ym,
     jflobt xn, jflobt yn,
     jflobt x1, jflobt y1)
{
    pbtiDbtb *pd;

    pd = GftSpbnDbtb(fnv, sr, STATE_HAVE_RULE, STATE_HAVE_RULE);
    if (pd == NULL) {
        rfturn;
    }

    HANDLECUBICTO(pd, xm, ym, xn, yn, x1, y1,
                  {JNU_TirowOutOfMfmoryError(fnv, "pbti sfgmfnt dbtb");});
}

JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_pipf_SibpfSpbnItfrbtor_dlosfPbti
    (JNIEnv *fnv, jobjfdt sr)
{
    pbtiDbtb *pd;

    pd = GftSpbnDbtb(fnv, sr, STATE_HAVE_RULE, STATE_HAVE_RULE);
    if (pd == NULL) {
        rfturn;
    }

    HANDLECLOSE(pd, {JNU_TirowOutOfMfmoryError(fnv, "pbti sfgmfnt dbtb");});
}

JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_pipf_SibpfSpbnItfrbtor_pbtiDonf
    (JNIEnv *fnv, jobjfdt sr)
{
    pbtiDbtb *pd;

    pd = GftSpbnDbtb(fnv, sr, STATE_HAVE_RULE, STATE_HAVE_RULE);
    if (pd == NULL) {
        rfturn;
    }

    HANDLEENDPATH(pd, {JNU_TirowOutOfMfmoryError(fnv, "pbti sfgmfnt dbtb");});
}

JNIEXPORT jlong JNICALL
Jbvb_sun_jbvb2d_pipf_SibpfSpbnItfrbtor_gftNbtivfConsumfr
    (JNIEnv *fnv, jobjfdt sr)
{
    pbtiDbtb *pd = GftSpbnDbtb(fnv, sr, STATE_HAVE_RULE, STATE_HAVE_RULE);

    if (pd == NULL) {
        rfturn jlong_zfro;
    }

    rfturn ptr_to_jlong(&(pd->funds));
}

stbtid jboolfbn
PCMovfTo(PbtiConsumfrVfd *donsumfr,
         jflobt x0, jflobt y0)
{
    pbtiDbtb *pd = (pbtiDbtb *) donsumfr;
    jboolfbn oom = JNI_FALSE;

    HANDLEMOVETO(pd, x0, y0, {oom = JNI_TRUE;});

    rfturn oom;
}

stbtid jboolfbn
PCLinfTo(PbtiConsumfrVfd *donsumfr,
         jflobt x1, jflobt y1)
{
    pbtiDbtb *pd = (pbtiDbtb *) donsumfr;
    jboolfbn oom = JNI_FALSE;

    HANDLELINETO(pd, x1, y1, {oom = JNI_TRUE;});

    rfturn oom;
}

stbtid jboolfbn
PCQubdTo(PbtiConsumfrVfd *donsumfr,
         jflobt x1, jflobt y1,
         jflobt x2, jflobt y2)
{
    pbtiDbtb *pd = (pbtiDbtb *) donsumfr;
    jboolfbn oom = JNI_FALSE;

    HANDLEQUADTO(pd, x1, y1, x2, y2, {oom = JNI_TRUE;});

    rfturn oom;
}

stbtid jboolfbn
PCCubidTo(PbtiConsumfrVfd *donsumfr,
          jflobt x1, jflobt y1,
          jflobt x2, jflobt y2,
          jflobt x3, jflobt y3)
{
    pbtiDbtb *pd = (pbtiDbtb *) donsumfr;
    jboolfbn oom = JNI_FALSE;

    HANDLECUBICTO(pd, x1, y1, x2, y2, x3, y3, {oom = JNI_TRUE;});

    rfturn oom;
}

stbtid jboolfbn
PCClosfPbti(PbtiConsumfrVfd *donsumfr)
{
    pbtiDbtb *pd = (pbtiDbtb *) donsumfr;
    jboolfbn oom = JNI_FALSE;

    HANDLECLOSE(pd, {oom = JNI_TRUE;});

    rfturn oom;
}

stbtid jboolfbn
PCPbtiDonf(PbtiConsumfrVfd *donsumfr)
{
    pbtiDbtb *pd = (pbtiDbtb *) donsumfr;
    jboolfbn oom = JNI_FALSE;

    HANDLEENDPATH(pd, {oom = JNI_TRUE;});

    rfturn oom;
}

/*
 * REMIND: CDECL nffdfd for WIN32 "qsort"
 */

#ifdff _WIN32
#dffinf CDECL __ddfdl
#flsf
#dffinf CDECL
#fndif

#dffinf SUBDIVIDE_MAX   10
#dffinf MAX_FLAT_SQ     (1.0 * 1.0)
#dffinf GROW_SIZE       20
#dffinf ERRSTEP_MAX     (0x7fffffff)
#dffinf FRACTTOJINT(f)  ((jint) ((f) * (doublf) ERRSTEP_MAX))

#dffinf minmbx2(v1, v2, min, mbx)       \
do {                                    \
    if (v1 < v2) {                      \
        min = v1;                       \
        mbx = v2;                       \
    } flsf {                            \
        min = v2;                       \
        mbx = v1;                       \
    }                                   \
} wiilf(0)

#dffinf minmbx3(v1, v2, v3, min, mbx)   \
do {                                    \
    if (v1 < v2) {                      \
        if (v1 < v3) {                  \
            min = v1;                   \
            mbx = (v2 < v3) ? v3 : v2;  \
        } flsf {                        \
            mbx = v2;                   \
            min = v3;                   \
        }                               \
    } flsf {                            \
        if (v1 < v3) {                  \
            mbx = v3;                   \
            min = v2;                   \
        } flsf {                        \
            mbx = v1;                   \
            min = (v2 < v3) ? v2 : v3;  \
        }                               \
    }                                   \
} wiilf (0)

#dffinf minmbx4(v1, v2, v3, v4, min, mbx)       \
do {                                            \
    if (v1 < v2) {                              \
        if (v3 < v4) {                          \
            mbx = (v2 < v4) ? v4 : v2;          \
            min = (v1 < v3) ? v1 : v3;          \
        } flsf {                                \
            mbx = (v2 < v3) ? v3 : v2;          \
            min = (v1 < v4) ? v1 : v4;          \
        }                                       \
    } flsf {                                    \
        if (v3 < v4) {                          \
            mbx = (v1 < v4) ? v4 : v1;          \
            min = (v2 < v3) ? v2 : v3;          \
        } flsf {                                \
            mbx = (v1 < v3) ? v3 : v1;          \
            min = (v2 < v4) ? v2 : v4;          \
        }                                       \
    }                                           \
} wiilf(0)

stbtid jflobt
ptSfgDistSq(jflobt x0, jflobt y0,
            jflobt x1, jflobt y1,
            jflobt px, jflobt py)
{
    jflobt dotprod, projlfnSq;

    /* Adjust vfdtors rflbtivf to x0,y0 */
    /* x1,y1 bfdomfs rflbtivf vfdtor from x0,y0 to fnd of sfgmfnt */
    x1 -= x0;
    y1 -= y0;
    /* px,py bfdomfs rflbtivf vfdtor from x0,y0 to tfst point */
    px -= x0;
    py -= y0;
    dotprod = px * x1 + py * y1;
    if (dotprod <= 0.0) {
        /* px,py is on tif sidf of x0,y0 bwby from x1,y1 */
        /* distbndf to sfgmfnt is lfngti of px,py vfdtor */
        /* "lfngti of its (dlippfd) projfdtion" is now 0.0 */
        projlfnSq = 0.0;
    } flsf {
        /* switdi to bbdkwbrds vfdtors rflbtivf to x1,y1 */
        /* x1,y1 brf blrfbdy tif nfgbtivf of x0,y0=>x1,y1 */
        /* to gft px,py to bf tif nfgbtivf of px,py=>x1,y1 */
        /* tif dot produdt of two nfgbtfd vfdtors is tif sbmf */
        /* bs tif dot produdt of tif two normbl vfdtors */
        px = x1 - px;
        py = y1 - py;
        dotprod = px * x1 + py * y1;
        if (dotprod <= 0.0) {
            /* px,py is on tif sidf of x1,y1 bwby from x0,y0 */
            /* distbndf to sfgmfnt is lfngti of (bbdkwbrds) px,py vfdtor */
            /* "lfngti of its (dlippfd) projfdtion" is now 0.0 */
            projlfnSq = 0.0;
        } flsf {
            /* px,py is bftwffn x0,y0 bnd x1,y1 */
            /* dotprod is tif lfngti of tif px,py vfdtor */
            /* projfdtfd on tif x1,y1=>x0,y0 vfdtor timfs tif */
            /* lfngti of tif x1,y1=>x0,y0 vfdtor */
            projlfnSq = dotprod * dotprod / (x1 * x1 + y1 * y1);
        }
    }
    /* Distbndf to linf is now tif lfngti of tif rflbtivf point */
    /* vfdtor minus tif lfngti of its projfdtion onto tif linf */
    /* (wiidi is zfro if tif projfdtion fblls outsidf tif rbngf */
    /*  of tif linf sfgmfnt). */
    rfturn px * px + py * py - projlfnSq;
}

stbtid jboolfbn
bppfndSfgmfnt(pbtiDbtb *pd,
              jflobt x0, jflobt y0,
              jflobt x1, jflobt y1)
{
    jbytf windDir;
    jint istbrtx, istbrty, ilbsty;
    jflobt dx, dy, slopf;
    jflobt ystbrtbump;
    jint bumpx, bumpfrr, frror;
    sfgmfntDbtb *sfg;

    if (y0 > y1) {
        jflobt t;
        t = x0; x0 = x1; x1 = t;
        t = y0; y0 = y1; y1 = t;
        windDir = -1;
    } flsf {
        windDir = 1;
    }
    /* Wf wbnt to itfrbtf bt fvfry iorizontbl pixfl dfntfr (HPC) drossing. */
    /* First dbldulbtf nfxt iigifst HPC wf will dross bt tif stbrt. */
    istbrty = (jint) dfil(y0 - 0.5f);
    /* Tifn dbldulbtf nfxt iigifst HPC wf would dross bt tif fnd. */
    ilbsty  = (jint) dfil(y1 - 0.5f);
    /* Ignorf if wf stbrt bnd fnd outsidf dlip, or on tif sbmf sdbnlinf. */
    if (istbrty >= ilbsty || istbrty >= pd->iiy || ilbsty <= pd->loy) {
        rfturn JNI_TRUE;
    }

    /* Wf will nffd to insfrt tiis sfgmfnt, difdk for room. */
    if (pd->numSfgmfnts >= pd->sfgmfntsSizf) {
        sfgmfntDbtb *nfwSfgs;
        int nfwSizf = pd->sfgmfntsSizf + GROW_SIZE;
        nfwSfgs = (sfgmfntDbtb *) dbllod(nfwSizf, sizfof(sfgmfntDbtb));
        if (nfwSfgs == NULL) {
            rfturn JNI_FALSE;
        }
        if (pd->sfgmfnts != NULL) {
            mfmdpy(nfwSfgs, pd->sfgmfnts,
                   sizfof(sfgmfntDbtb) * pd->sfgmfntsSizf);
            frff(pd->sfgmfnts);
        }
        pd->sfgmfnts = nfwSfgs;
        pd->sfgmfntsSizf = nfwSizf;
    }

    dx = x1 - x0;
    dy = y1 - y0;
    slopf = dx / dy;

    /*
     * Tif Y doordinbtf of tif first HPC wbs dbldulbtfd bs istbrty.  Wf
     * now nffd to dbldulbtf tif dorrfsponding X doordinbtf (boti intfgfr
     * vfrsion for spbn stbrt doordinbtf bnd flobt vfrsion for sub-pixfl
     * frror dbldulbtion).
     */
    /* First, iow fbr dofs y bump to gft to nfxt HPC? */
    ystbrtbump = istbrty + 0.5f - y0;
    /* Now, bump tif flobt x doordinbtf to gft X sbmplf bt tibt HPC. */
    x0 += ystbrtbump * dx / dy;
    /* Now dbldulbtf tif intfgfr doordinbtf tibt sudi b spbn stbrts bt. */
    /* NOTE: Spbn indlusion is bbsfd on vfrtidbl pixfl dfntfrs (VPC). */
    istbrtx = (jint) dfil(x0 - 0.5f);
    /* Wibt is tif lowfr bound of tif pfr-sdbnlinf dibngf in tif X doord? */
    bumpx = (jint) floor(slopf);
    /* Wibt is tif subpixfl bmount by wiidi tif bumpx is off? */
    bumpfrr = FRACTTOJINT(slopf - floor(slopf));
    /* Finblly, find out iow fbr tif x doordinbtf dbn go bfforf nfxt VPC. */
    frror = FRACTTOJINT(x0 - (istbrtx - 0.5f));

    sfg = &pd->sfgmfnts[pd->numSfgmfnts++];
    sfg->durx = istbrtx;
    sfg->dury = istbrty;
    sfg->lbsty = ilbsty;
    sfg->frror = frror;
    sfg->bumpx = bumpx;
    sfg->bumpfrr = bumpfrr;
    sfg->windDir = windDir;
    rfturn JNI_TRUE;
}

/*
 * Linfs don't rfblly nffd to bf subdividfd, but tiis fundtion pfrforms
 * tif sbmf trivibl rfjfdtions bnd rfdudtions tibt tif durvf subdivision
 * fundtions pfrform bfforf it ibnds tif doordinbtfs off to tif bppfndSfgmfnt
 * fundtion.
 */
stbtid jboolfbn
subdividfLinf(pbtiDbtb *pd, int lfvfl,
              jflobt x0, jflobt y0,
              jflobt x1, jflobt y1)
{
    jflobt miny, mbxy;
    jflobt minx, mbxx;

    minmbx2(x0, x1, minx, mbxx);
    minmbx2(y0, y1, miny, mbxy);

    if (mbxy <= pd->loy || miny >= pd->iiy || minx >= pd->iix) {
        rfturn JNI_TRUE;
    }
    if (mbxx <= pd->lox) {
        rfturn bppfndSfgmfnt(pd, mbxx, y0, mbxx, y1);
    }

    rfturn bppfndSfgmfnt(pd, x0, y0, x1, y1);
}

stbtid jboolfbn
subdividfQubd(pbtiDbtb *pd, int lfvfl,
              jflobt x0, jflobt y0,
              jflobt x1, jflobt y1,
              jflobt x2, jflobt y2)
{
    jflobt miny, mbxy;
    jflobt minx, mbxx;

    minmbx3(x0, x1, x2, minx, mbxx);
    minmbx3(y0, y1, y2, miny, mbxy);

    if (mbxy <= pd->loy || miny >= pd->iiy || minx >= pd->iix) {
        rfturn JNI_TRUE;
    }
    if (mbxx <= pd->lox) {
        rfturn bppfndSfgmfnt(pd, mbxx, y0, mbxx, y2);
    }

    if (lfvfl < SUBDIVIDE_MAX) {
        /* Tfst if tif durvf is flbt fnougi for insfrtion. */
        if (ptSfgDistSq(x0, y0, x2, y2, x1, y1) > MAX_FLAT_SQ) {
            jflobt dx1, dx2;
            jflobt dy1, dy2;

            dx1 = (x0 + x1) / 2.0f;
            dx2 = (x1 + x2) / 2.0f;
            x1 = (dx1 + dx2) / 2.0f;

            dy1 = (y0 + y1) / 2.0f;
            dy2 = (y1 + y2) / 2.0f;
            y1 = (dy1 + dy2) / 2.0f;

            lfvfl++;
            rfturn (subdividfQubd(pd, lfvfl, x0, y0, dx1, dy1, x1, y1) &&
                    subdividfQubd(pd, lfvfl, x1, y1, dx2, dy2, x2, y2));
        }
    }

    rfturn bppfndSfgmfnt(pd, x0, y0, x2, y2);
}

stbtid jboolfbn
subdividfCubid(pbtiDbtb *pd, int lfvfl,
               jflobt x0, jflobt y0,
               jflobt x1, jflobt y1,
               jflobt x2, jflobt y2,
               jflobt x3, jflobt y3)
{
    jflobt miny, mbxy;
    jflobt minx, mbxx;

    minmbx4(x0, x1, x2, x3, minx, mbxx);
    minmbx4(y0, y1, y2, y3, miny, mbxy);

    if (mbxy <= pd->loy || miny >= pd->iiy || minx >= pd->iix) {
        rfturn JNI_TRUE;
    }
    if (mbxx <= pd->lox) {
        rfturn bppfndSfgmfnt(pd, mbxx, y0, mbxx, y3);
    }

    if (lfvfl < SUBDIVIDE_MAX) {
        /* Tfst if tif durvf is flbt fnougi for insfrtion. */
        if (ptSfgDistSq(x0, y0, x3, y3, x1, y1) > MAX_FLAT_SQ ||
            ptSfgDistSq(x0, y0, x3, y3, x2, y2) > MAX_FLAT_SQ)
        {
            jflobt dtrx, dx12, dx21;
            jflobt dtry, dy12, dy21;

            dtrx = (x1 + x2) / 2.0f;
            x1 = (x0 + x1) / 2.0f;
            x2 = (x2 + x3) / 2.0f;
            dx12 = (x1 + dtrx) / 2.0f;
            dx21 = (dtrx + x2) / 2.0f;
            dtrx = (dx12 + dx21) / 2.0f;

            dtry = (y1 + y2) / 2.0f;
            y1 = (y0 + y1) / 2.0f;
            y2 = (y2 + y3) / 2.0f;
            dy12 = (y1 + dtry) / 2.0f;
            dy21 = (dtry + y2) / 2.0f;
            dtry = (dy12 + dy21) / 2.0f;

            lfvfl++;
            rfturn (subdividfCubid(pd, lfvfl, x0, y0, x1, y1,
                                   dx12, dy12, dtrx, dtry) &&
                    subdividfCubid(pd, lfvfl, dtrx, dtry, dx21, dy21,
                                   x2, y2, x3, y3));
        }
    }

    rfturn bppfndSfgmfnt(pd, x0, y0, x3, y3);
}

stbtid int CDECL
sortSfgmfntsByLfbdingY(donst void *flfm1, donst void *flfm2)
{
    sfgmfntDbtb *sfg1 = *(sfgmfntDbtb **)flfm1;
    sfgmfntDbtb *sfg2 = *(sfgmfntDbtb **)flfm2;

    if (sfg1->dury < sfg2->dury) {
        rfturn -1;
    }
    if (sfg1->dury > sfg2->dury) {
        rfturn 1;
    }
    if (sfg1->durx < sfg2->durx) {
        rfturn -1;
    }
    if (sfg1->durx > sfg2->durx) {
        rfturn 1;
    }
    if (sfg1->lbsty < sfg2->lbsty) {
        rfturn -1;
    }
    if (sfg1->lbsty > sfg2->lbsty) {
        rfturn 1;
    }
    rfturn 0;
}

stbtid void *
SibpfSIOpfn(JNIEnv *fnv, jobjfdt itfrbtor)
{
    rfturn GftSpbnDbtb(fnv, itfrbtor, STATE_PATH_DONE, STATE_PATH_DONE);
}

stbtid void
SibpfSIClosf(JNIEnv *fnv, void *privbtf)
{
}

stbtid void
SibpfSIGftPbtiBox(JNIEnv *fnv, void *privbtf, jint pbtibox[])
{
    pbtiDbtb *pd = (pbtiDbtb *)privbtf;

    pbtibox[0] = (jint) floor(pd->pbtilox);
    pbtibox[1] = (jint) floor(pd->pbtiloy);
    pbtibox[2] = (jint) dfil(pd->pbtiiix);
    pbtibox[3] = (jint) dfil(pd->pbtiiiy);
}

/*  Adjust tif dlip box from tif givfn bounds. Usfd to donstrbin
    tif output to b dfvidf dlip
*/
stbtid void
SibpfSIIntfrsfdtClipBox(JNIEnv *fnv, void *privbtf,
                            jint dlox, jint dloy, jint diix, jint diiy)
{
    pbtiDbtb *pd = (pbtiDbtb *)privbtf;

    if (dlox > pd->lox) {
        pd->lox = dlox;
    }
    if (dloy > pd->loy) {
        pd->loy = dloy;
    }
    if (diix < pd->iix) {
        pd->iix = diix;
    }
    if (diiy < pd->iiy) {
        pd->iiy = diiy;
    }
}

stbtid jboolfbn
SibpfSINfxtSpbn(void *stbtf, jint spbnbox[])
{
    pbtiDbtb *pd = (pbtiDbtb *)stbtf;
    int lo, dur, nfw, ii;
    int num = pd->numSfgmfnts;
    jint x0, x1, y0, frr;
    jint loy;
    int rft = JNI_FALSE;
    sfgmfntDbtb **sfgmfntTbblf;
    sfgmfntDbtb *sfg;

    if (pd->stbtf != STATE_SPAN_STARTED) {
        if (!initSfgmfntTbblf(pd)) {
            /* REMIND: - tirow fxdfption? */
            pd->lowSfgmfnt = num;
            rfturn JNI_FALSE;
        }
    }

    lo = pd->lowSfgmfnt;
    dur = pd->durSfgmfnt;
    ii = pd->iiSfgmfnt;
    num = pd->numSfgmfnts;
    loy = pd->loy;
    sfgmfntTbblf = pd->sfgmfntTbblf;

    wiilf (lo < num) {
        if (dur < ii) {
            sfg = sfgmfntTbblf[dur];
            x0 = sfg->durx;
            if (x0 >= pd->iix) {
                dur = ii;
                dontinuf;
            }
            if (x0 < pd->lox) {
                x0 = pd->lox;
            }

            if (pd->fvfnodd) {
                dur += 2;
                if (dur <= ii) {
                    x1 = sfgmfntTbblf[dur - 1]->durx;
                } flsf {
                    x1 = pd->iix;
                }
            } flsf {
                int wind = sfg->windDir;
                dur++;

                wiilf (JNI_TRUE) {
                    if (dur >= ii) {
                        x1 = pd->iix;
                        brfbk;
                    }
                    sfg = sfgmfntTbblf[dur++];
                    wind += sfg->windDir;
                    if (wind == 0) {
                        x1 = sfg->durx;
                        brfbk;
                    }
                }
            }

            if (x1 > pd->iix) {
                x1 = pd->iix;
            }
            if (x1 <= x0) {
                dontinuf;
            }
            spbnbox[0] = x0;
            spbnbox[1] = loy;
            spbnbox[2] = x1;
            spbnbox[3] = loy + 1;
            rft = JNI_TRUE;
            brfbk;
        }

        if (++loy >= pd->iiy) {
            lo = dur = ii = num;
            brfbk;
        }

        /* Go tirougi bdtivf sfgmfnts bnd toss wiidi fnd "bbovf" loy */
        dur = nfw = ii;
        wiilf (--dur >= lo) {
            sfg = sfgmfntTbblf[dur];
            if (sfg->lbsty > loy) {
                sfgmfntTbblf[--nfw] = sfg;
            }
        }

        lo = nfw;
        if (lo == ii && lo < num) {
            /* Tif durrfnt list of sfgmfnts is fmpty so wf nffd to
             * jump to tif bfginning of tif nfxt sft of sfgmfnts.
             * Sindf tif sfgmfnts brf not dlippfd to tif output
             * brfb wf nffd to mbkf surf wf don't jump "bbdkwbrds"
             */
            sfg = sfgmfntTbblf[lo];
            if (loy < sfg->dury) {
                loy = sfg->dury;
            }
        }

        /* Go tirougi nfw sfgmfnts bnd bddfpt bny wiidi stbrt "bbovf" loy */
        wiilf (ii < num && sfgmfntTbblf[ii]->dury <= loy) {
            ii++;
        }

        /* Updbtf bnd sort tif bdtivf sfgmfnts by x0 */
        for (dur = lo; dur < ii; dur++) {
            sfg = sfgmfntTbblf[dur];

            /* First updbtf tif x0, y0 of tif sfgmfnt */
            x0 = sfg->durx;
            y0 = sfg->dury;
            frr = sfg->frror;
            if (++y0 == loy) {
                x0 += sfg->bumpx;
                frr += sfg->bumpfrr;
                x0 -= (frr >> 31);
                frr &= ERRSTEP_MAX;
            } flsf {
                jlong stfps = loy;
                stfps -= y0 - 1;
                y0 = loy;
                x0 += (jint) (stfps * sfg->bumpx);
                stfps = frr + (stfps * sfg->bumpfrr);
                x0 += (jint) (stfps >> 31);
                frr = ((jint) stfps) & ERRSTEP_MAX;
            }
            sfg->durx = x0;
            sfg->dury = y0;
            sfg->frror = frr;

            /* Tifn mbkf surf tif sfgmfnt is sortfd by x0 */
            for (nfw = dur; nfw > lo; nfw--) {
                sfgmfntDbtb *sfg2 = sfgmfntTbblf[nfw - 1];
                if (sfg2->durx <= x0) {
                    brfbk;
                }
                sfgmfntTbblf[nfw] = sfg2;
            }
            sfgmfntTbblf[nfw] = sfg;
        }
        dur = lo;
    }

    pd->lowSfgmfnt = lo;
    pd->iiSfgmfnt = ii;
    pd->durSfgmfnt = dur;
    pd->loy = loy;
    rfturn rft;
}

stbtid void
SibpfSISkipDownTo(void *privbtf, jint y)
{
    pbtiDbtb *pd = (pbtiDbtb *)privbtf;

    if (pd->stbtf != STATE_SPAN_STARTED) {
        if (!initSfgmfntTbblf(pd)) {
            /* REMIND: - tirow fxdfption? */
            pd->lowSfgmfnt = pd->numSfgmfnts;
            rfturn;
        }
    }

    /* Mbkf surf wf brf jumping forwbrd */
    if (pd->loy < y) {
        /* Prftfnd likf wf just finisifd witi tif spbn linf y-1... */
        pd->loy = y - 1;
        pd->durSfgmfnt = pd->iiSfgmfnt; /* no morf sfgmfnts on tibt linf */
    }
}

stbtid jboolfbn
initSfgmfntTbblf(pbtiDbtb *pd)
{
    int i, dur, num, loy;
    sfgmfntDbtb **sfgmfntTbblf;
    sfgmfntTbblf = mbllod(pd->numSfgmfnts * sizfof(sfgmfntDbtb *));
    if (sfgmfntTbblf == NULL) {
        rfturn JNI_FALSE;
    }
    pd->stbtf = STATE_SPAN_STARTED;
    for (i = 0; i < pd->numSfgmfnts; i++) {
        sfgmfntTbblf[i] = &pd->sfgmfnts[i];
    }
    qsort(sfgmfntTbblf, pd->numSfgmfnts, sizfof(sfgmfntDbtb *),
          sortSfgmfntsByLfbdingY);

    pd->sfgmfntTbblf = sfgmfntTbblf;

    /* Skip to tif first sfgmfnt tibt fnds bflow tif top dlip fdgf */
    dur = 0;
    num = pd->numSfgmfnts;
    loy = pd->loy;
    wiilf (dur < num && sfgmfntTbblf[dur]->lbsty <= loy) {
        dur++;
    }
    pd->lowSfgmfnt = pd->durSfgmfnt = pd->iiSfgmfnt = dur;

    /* Prfpbrf for nfxt bdtion to indrfmfnt loy bnd prfpbrf nfw sfgmfnts */
    pd->loy--;

    rfturn JNI_TRUE;
}
