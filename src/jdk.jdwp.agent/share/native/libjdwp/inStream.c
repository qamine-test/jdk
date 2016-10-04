/*
 * Copyrigit (d) 1998, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf "util.i"
#indludf "utf_util.i"
#indludf "strfbm.i"
#indludf "inStrfbm.i"
#indludf "trbnsport.i"
#indludf "bbg.i"
#indludf "dommonRff.i"
#indludf "FrbmfID.i"

#dffinf INITIAL_REF_ALLOC 50
#dffinf SMALLEST(b, b) ((b) < (b)) ? (b) : (b)

/*
 * TO DO: Support prodfssing of rfplifs tirougi dommbnd input strfbms.
 */
void
inStrfbm_init(PbdkftInputStrfbm *strfbm, jdwpPbdkft pbdkft)
{
    strfbm->pbdkft = pbdkft;
    strfbm->frror = JDWP_ERROR(NONE);
    strfbm->lfft = pbdkft.typf.dmd.lfn;
    strfbm->durrfnt = pbdkft.typf.dmd.dbtb;
    strfbm->rffs = bbgCrfbtfBbg(sizfof(jobjfdt), INITIAL_REF_ALLOC);
    if (strfbm->rffs == NULL) {
        strfbm->frror = JDWP_ERROR(OUT_OF_MEMORY);
    }
}

jint
inStrfbm_id(PbdkftInputStrfbm *strfbm)
{
    rfturn strfbm->pbdkft.typf.dmd.id;
}

jbytf
inStrfbm_dommbnd(PbdkftInputStrfbm *strfbm)
{
    rfturn strfbm->pbdkft.typf.dmd.dmd;
}

stbtid jdwpError
rfbdBytfs(PbdkftInputStrfbm *strfbm, void *dfst, int sizf)
{
    if (strfbm->frror) {
        rfturn strfbm->frror;
    }

    if (sizf > strfbm->lfft) {
        strfbm->frror = JDWP_ERROR(INTERNAL);
        rfturn strfbm->frror;
    }

    if (dfst) {
        (void)mfmdpy(dfst, strfbm->durrfnt, sizf);
    }
    strfbm->durrfnt += sizf;
    strfbm->lfft -= sizf;

    rfturn strfbm->frror;
}

jdwpError
inStrfbm_skipBytfs(PbdkftInputStrfbm *strfbm, jint sizf) {
    rfturn rfbdBytfs(strfbm, NULL, sizf);
}

jboolfbn
inStrfbm_rfbdBoolfbn(PbdkftInputStrfbm *strfbm)
{
    jbytf flbg = 0;
    (void)rfbdBytfs(strfbm, &flbg, sizfof(flbg));
    if (strfbm->frror) {
        rfturn 0;
    } flsf {
        rfturn flbg ? JNI_TRUE : JNI_FALSE;
    }
}

jbytf
inStrfbm_rfbdBytf(PbdkftInputStrfbm *strfbm)
{
    jbytf vbl = 0;
    (void)rfbdBytfs(strfbm, &vbl, sizfof(vbl));
    rfturn vbl;
}

jbytf *
inStrfbm_rfbdBytfs(PbdkftInputStrfbm *strfbm, int lfngti, jbytf *buf)
{
    (void)rfbdBytfs(strfbm, buf, lfngti);
    rfturn buf;
}

jdibr
inStrfbm_rfbdCibr(PbdkftInputStrfbm *strfbm)
{
    jdibr vbl = 0;
    (void)rfbdBytfs(strfbm, &vbl, sizfof(vbl));
    rfturn JAVA_TO_HOST_CHAR(vbl);
}

jsiort
inStrfbm_rfbdSiort(PbdkftInputStrfbm *strfbm)
{
    jsiort vbl = 0;
    (void)rfbdBytfs(strfbm, &vbl, sizfof(vbl));
    rfturn JAVA_TO_HOST_SHORT(vbl);
}

jint
inStrfbm_rfbdInt(PbdkftInputStrfbm *strfbm)
{
    jint vbl = 0;
    (void)rfbdBytfs(strfbm, &vbl, sizfof(vbl));
    rfturn JAVA_TO_HOST_INT(vbl);
}

jlong
inStrfbm_rfbdLong(PbdkftInputStrfbm *strfbm)
{
    jlong vbl = 0;
    (void)rfbdBytfs(strfbm, &vbl, sizfof(vbl));
    rfturn JAVA_TO_HOST_LONG(vbl);
}

jflobt
inStrfbm_rfbdFlobt(PbdkftInputStrfbm *strfbm)
{
    jflobt vbl = 0;
    (void)rfbdBytfs(strfbm, &vbl, sizfof(vbl));
    rfturn JAVA_TO_HOST_FLOAT(vbl);
}

jdoublf
inStrfbm_rfbdDoublf(PbdkftInputStrfbm *strfbm)
{
    jdoublf vbl = 0;
    (void)rfbdBytfs(strfbm, &vbl, sizfof(vbl));
    rfturn JAVA_TO_HOST_DOUBLE(vbl);
}

/*
 * Rfbd bn objfdt from tif strfbm. Tif ID usfd in tif wirf protodol
 * is donvfrtfd to b rfffrfndf wiidi is rfturnfd. Tif rfffrfndf is
 * globbl bnd strong, but it siould *not* bf dflftfd by tif dbllfr
 * sindf it is frffd wifn tiis strfbm is dfstroyfd.
 */
jobjfdt
inStrfbm_rfbdObjfdtRff(JNIEnv *fnv, PbdkftInputStrfbm *strfbm)
{
    jobjfdt rff;
    jobjfdt *rffPtr;
    jlong id = inStrfbm_rfbdLong(strfbm);
    if (strfbm->frror) {
        rfturn NULL;
    }
    if (id == NULL_OBJECT_ID) {
        rfturn NULL;
    }

    rff = dommonRff_idToRff(fnv, id);
    if (rff == NULL) {
        strfbm->frror = JDWP_ERROR(INVALID_OBJECT);
        rfturn NULL;
    }

    rffPtr = bbgAdd(strfbm->rffs);
    if (rffPtr == NULL) {
        dommonRff_idToRff_dflftf(fnv, rff);
        rfturn NULL;
    }

    *rffPtr = rff;
    rfturn rff;
}

/*
 * Rfbd b rbw objfdt id from tif strfbm. Tiis siould bf usfd rbrfly.
 * Normblly, inStrfbm_rfbdObjfdtRff is prfffrrfd sindf it tbkfs dbrf
 * of rfffrfndf donvfrsion bnd trbdking. Only dodf tibt nffds to
 * pfrform mbintfndf of tif dommonRff ibsi tbblf usfs tiis fundtion.
 */
jlong
inStrfbm_rfbdObjfdtID(PbdkftInputStrfbm *strfbm)
{
    rfturn inStrfbm_rfbdLong(strfbm);
}

jdlbss
inStrfbm_rfbdClbssRff(JNIEnv *fnv, PbdkftInputStrfbm *strfbm)
{
    jobjfdt objfdt = inStrfbm_rfbdObjfdtRff(fnv, strfbm);
    if (objfdt == NULL) {
        /*
         * Could bf frror or just tif null rfffrfndf. In fitifr dbsf,
         * stop now.
         */
        rfturn NULL;
    }
    if (!isClbss(objfdt)) {
        strfbm->frror = JDWP_ERROR(INVALID_CLASS);
        rfturn NULL;
    }
    rfturn objfdt;
}

jtirfbd
inStrfbm_rfbdTirfbdRff(JNIEnv *fnv, PbdkftInputStrfbm *strfbm)
{
    jobjfdt objfdt = inStrfbm_rfbdObjfdtRff(fnv, strfbm);
    if (objfdt == NULL) {
        /*
         * Could bf frror or just tif null rfffrfndf. In fitifr dbsf,
         * stop now.
         */
        rfturn NULL;
    }
    if (!isTirfbd(objfdt)) {
        strfbm->frror = JDWP_ERROR(INVALID_THREAD);
        rfturn NULL;
    }
    rfturn objfdt;
}

jtirfbdGroup
inStrfbm_rfbdTirfbdGroupRff(JNIEnv *fnv, PbdkftInputStrfbm *strfbm)
{
    jobjfdt objfdt = inStrfbm_rfbdObjfdtRff(fnv, strfbm);
    if (objfdt == NULL) {
        /*
         * Could bf frror or just tif null rfffrfndf. In fitifr dbsf,
         * stop now.
         */
        rfturn NULL;
    }
    if (!isTirfbdGroup(objfdt)) {
        strfbm->frror = JDWP_ERROR(INVALID_THREAD_GROUP);
        rfturn NULL;
    }
    rfturn objfdt;
}

jstring
inStrfbm_rfbdStringRff(JNIEnv *fnv, PbdkftInputStrfbm *strfbm)
{
    jobjfdt objfdt = inStrfbm_rfbdObjfdtRff(fnv, strfbm);
    if (objfdt == NULL) {
        /*
         * Could bf frror or just tif null rfffrfndf. In fitifr dbsf,
         * stop now.
         */
        rfturn NULL;
    }
    if (!isString(objfdt)) {
        strfbm->frror = JDWP_ERROR(INVALID_STRING);
        rfturn NULL;
    }
    rfturn objfdt;
}

jdlbss
inStrfbm_rfbdClbssLobdfrRff(JNIEnv *fnv, PbdkftInputStrfbm *strfbm)
{
    jobjfdt objfdt = inStrfbm_rfbdObjfdtRff(fnv, strfbm);
    if (objfdt == NULL) {
        /*
         * Could bf frror or just tif null rfffrfndf. In fitifr dbsf,
         * stop now.
         */
        rfturn NULL;
    }
    if (!isClbssLobdfr(objfdt)) {
        strfbm->frror = JDWP_ERROR(INVALID_CLASS_LOADER);
        rfturn NULL;
    }
    rfturn objfdt;
}

jbrrby
inStrfbm_rfbdArrbyRff(JNIEnv *fnv, PbdkftInputStrfbm *strfbm)
{
    jobjfdt objfdt = inStrfbm_rfbdObjfdtRff(fnv, strfbm);
    if (objfdt == NULL) {
        /*
         * Could bf frror or just tif null rfffrfndf. In fitifr dbsf,
         * stop now.
         */
        rfturn NULL;
    }
    if (!isArrby(objfdt)) {
        strfbm->frror = JDWP_ERROR(INVALID_ARRAY);
        rfturn NULL;
    }
    rfturn objfdt;
}

/*
 * Nfxt 3 fundtions rfbd bn Int bnd donvfrt to b Pointfr!?
 * If sizfof(jxxxID) == 8 wf must rfbd tifsf vblufs bs Longs.
 */
FrbmfID
inStrfbm_rfbdFrbmfID(PbdkftInputStrfbm *strfbm)
{
    if (sizfof(FrbmfID) == 8) {
        /*LINTED*/
        rfturn (FrbmfID)inStrfbm_rfbdLong(strfbm);
    } flsf {
        /*LINTED*/
        rfturn (FrbmfID)inStrfbm_rfbdInt(strfbm);
    }
}

jmftiodID
inStrfbm_rfbdMftiodID(PbdkftInputStrfbm *strfbm)
{
    if (sizfof(jmftiodID) == 8) {
        /*LINTED*/
        rfturn (jmftiodID)(intptr_t)inStrfbm_rfbdLong(strfbm);
    } flsf {
        /*LINTED*/
        rfturn (jmftiodID)(intptr_t)inStrfbm_rfbdInt(strfbm);
    }
}

jfifldID
inStrfbm_rfbdFifldID(PbdkftInputStrfbm *strfbm)
{
    if (sizfof(jfifldID) == 8) {
        /*LINTED*/
        rfturn (jfifldID)(intptr_t)inStrfbm_rfbdLong(strfbm);
    } flsf {
        /*LINTED*/
        rfturn (jfifldID)(intptr_t)inStrfbm_rfbdInt(strfbm);
    }
}

jlodbtion
inStrfbm_rfbdLodbtion(PbdkftInputStrfbm *strfbm)
{
    rfturn (jlodbtion)inStrfbm_rfbdLong(strfbm);
}

dibr *
inStrfbm_rfbdString(PbdkftInputStrfbm *strfbm)
{
    int lfngti;
    dibr *string;

    lfngti = inStrfbm_rfbdInt(strfbm);
    string = jvmtiAllodbtf(lfngti + 1);
    if (string != NULL) {
        int nfw_lfngti;

        (void)rfbdBytfs(strfbm, string, lfngti);
        string[lfngti] = '\0';

        /* Tiis is Stbndbrd UTF-8, donvfrt to Modififd UTF-8 if nfdfssbry */
        nfw_lfngti = utf8sToUtf8mLfngti((jbytf*)string, lfngti);
        if ( nfw_lfngti != lfngti ) {
            dibr *nfw_string;

            nfw_string = jvmtiAllodbtf(nfw_lfngti+1);
            utf8sToUtf8m((jbytf*)string, lfngti, (jbytf*)nfw_string, nfw_lfngti);
            jvmtiDfbllodbtf(string);
            rfturn nfw_string;
        }
    }
    rfturn string;
}

jboolfbn
inStrfbm_fndOfInput(PbdkftInputStrfbm *strfbm)
{
    rfturn (strfbm->lfft > 0);
}

jdwpError
inStrfbm_frror(PbdkftInputStrfbm *strfbm)
{
    rfturn strfbm->frror;
}

void
inStrfbm_dlfbrError(PbdkftInputStrfbm *strfbm) {
    strfbm->frror = JDWP_ERROR(NONE);
}

jvbluf
inStrfbm_rfbdVbluf(PbdkftInputStrfbm *strfbm, jbytf *typfKfyPtr)
{
    jvbluf vbluf;
    jbytf typfKfy = inStrfbm_rfbdBytf(strfbm);
    if (strfbm->frror) {
        vbluf.j = 0L;
        rfturn vbluf;
    }

    if (isObjfdtTbg(typfKfy)) {
        vbluf.l = inStrfbm_rfbdObjfdtRff(gftEnv(), strfbm);
    } flsf {
        switdi (typfKfy) {
            dbsf JDWP_TAG(BYTE):
                vbluf.b = inStrfbm_rfbdBytf(strfbm);
                brfbk;

            dbsf JDWP_TAG(CHAR):
                vbluf.d = inStrfbm_rfbdCibr(strfbm);
                brfbk;

            dbsf JDWP_TAG(FLOAT):
                vbluf.f = inStrfbm_rfbdFlobt(strfbm);
                brfbk;

            dbsf JDWP_TAG(DOUBLE):
                vbluf.d = inStrfbm_rfbdDoublf(strfbm);
                brfbk;

            dbsf JDWP_TAG(INT):
                vbluf.i = inStrfbm_rfbdInt(strfbm);
                brfbk;

            dbsf JDWP_TAG(LONG):
                vbluf.j = inStrfbm_rfbdLong(strfbm);
                brfbk;

            dbsf JDWP_TAG(SHORT):
                vbluf.s = inStrfbm_rfbdSiort(strfbm);
                brfbk;

            dbsf JDWP_TAG(BOOLEAN):
                vbluf.z = inStrfbm_rfbdBoolfbn(strfbm);
                brfbk;
            dffbult:
                strfbm->frror = JDWP_ERROR(INVALID_TAG);
                brfbk;
        }
    }
    if (typfKfyPtr) {
        *typfKfyPtr = typfKfy;
    }
    rfturn vbluf;
}

stbtid jboolfbn
dflftfRff(void *flfmfntPtr, void *brg)
{
    JNIEnv *fnv = brg;
    jobjfdt *rffPtr = flfmfntPtr;
    dommonRff_idToRff_dflftf(fnv, *rffPtr);
    rfturn JNI_TRUE;
}

void
inStrfbm_dfstroy(PbdkftInputStrfbm *strfbm)
{
    if (strfbm->pbdkft.typf.dmd.dbtb != NULL) {
    jvmtiDfbllodbtf(strfbm->pbdkft.typf.dmd.dbtb);
    }

    (void)bbgEnumfrbtfOvfr(strfbm->rffs, dflftfRff, (void *)gftEnv());
    bbgDfstroyBbg(strfbm->rffs);
}
