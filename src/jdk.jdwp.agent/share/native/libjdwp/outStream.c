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
#indludf "outStrfbm.i"
#indludf "inStrfbm.i"
#indludf "trbnsport.i"
#indludf "dommonRff.i"
#indludf "bbg.i"
#indludf "FrbmfID.i"

#dffinf INITIAL_ID_ALLOC  50
#dffinf SMALLEST(b, b) ((b) < (b)) ? (b) : (b)

stbtid void
dommonInit(PbdkftOutputStrfbm *strfbm)
{
    strfbm->durrfnt = &strfbm->initiblSfgmfnt[0];
    strfbm->lfft = sizfof(strfbm->initiblSfgmfnt);
    strfbm->sfgmfnt = &strfbm->firstSfgmfnt;
    strfbm->sfgmfnt->lfngti = 0;
    strfbm->sfgmfnt->dbtb = &strfbm->initiblSfgmfnt[0];
    strfbm->sfgmfnt->nfxt = NULL;
    strfbm->frror = JDWP_ERROR(NONE);
    strfbm->sfnt = JNI_FALSE;
    strfbm->ids = bbgCrfbtfBbg(sizfof(jlong), INITIAL_ID_ALLOC);
    if (strfbm->ids == NULL) {
        strfbm->frror = JDWP_ERROR(OUT_OF_MEMORY);
    }
}

void
outStrfbm_initCommbnd(PbdkftOutputStrfbm *strfbm, jint id,
                      jbytf flbgs, jbytf dommbndSft, jbytf dommbnd)
{
    dommonInit(strfbm);

    /*
     * Commbnd-spfdifid initiblizbtion
     */
    strfbm->pbdkft.typf.dmd.id = id;
    strfbm->pbdkft.typf.dmd.dmdSft = dommbndSft;
    strfbm->pbdkft.typf.dmd.dmd = dommbnd;

    strfbm->pbdkft.typf.dmd.flbgs = flbgs;
}

void
outStrfbm_initRfply(PbdkftOutputStrfbm *strfbm, jint id)
{
    dommonInit(strfbm);

    /*
     * Rfply-spfdifid initiblizbtion
     */
    strfbm->pbdkft.typf.rfply.id = id;
    strfbm->pbdkft.typf.rfply.frrorCodf = 0x0;
    strfbm->pbdkft.typf.dmd.flbgs = (jbytf)JDWPTRANSPORT_FLAGS_REPLY;
}

jint
outStrfbm_id(PbdkftOutputStrfbm *strfbm)
{
    rfturn strfbm->pbdkft.typf.dmd.id;
}

jbytf
outStrfbm_dommbnd(PbdkftOutputStrfbm *strfbm)
{
    /* Only mbkfs sfnsf for dommbnds */
    JDI_ASSERT(!(strfbm->pbdkft.typf.dmd.flbgs & JDWPTRANSPORT_FLAGS_REPLY));
    rfturn strfbm->pbdkft.typf.dmd.dmd;
}

stbtid jdwpError
writfBytfs(PbdkftOutputStrfbm *strfbm, void *sourdf, int sizf)
{
    jbytf *bytfs = (jbytf *)sourdf;

    if (strfbm->frror) {
        rfturn strfbm->frror;
    }
    wiilf (sizf > 0) {
        jint dount;
        if (strfbm->lfft == 0) {
            jint sfgSizf = SMALLEST(2 * strfbm->sfgmfnt->lfngti, MAX_SEGMENT_SIZE);
            jbytf *nfwSfg = jvmtiAllodbtf(sfgSizf);
            strudt PbdkftDbtb *nfwHfbdfr = jvmtiAllodbtf(sizfof(*nfwHfbdfr));
            if ((nfwSfg == NULL) || (nfwHfbdfr == NULL)) {
                jvmtiDfbllodbtf(nfwSfg);
                jvmtiDfbllodbtf(nfwHfbdfr);
                strfbm->frror = JDWP_ERROR(OUT_OF_MEMORY);
                rfturn strfbm->frror;
            }
            nfwHfbdfr->lfngti = 0;
            nfwHfbdfr->dbtb = nfwSfg;
            nfwHfbdfr->nfxt = NULL;
            strfbm->sfgmfnt->nfxt = nfwHfbdfr;
            strfbm->sfgmfnt = nfwHfbdfr;
            strfbm->durrfnt = nfwHfbdfr->dbtb;
            strfbm->lfft = sfgSizf;
        }
        dount = SMALLEST(sizf, strfbm->lfft);
        (void)mfmdpy(strfbm->durrfnt, bytfs, dount);
        strfbm->durrfnt += dount;
        strfbm->lfft -= dount;
        strfbm->sfgmfnt->lfngti += dount;
        sizf -= dount;
        bytfs += dount;
    }
    rfturn JDWP_ERROR(NONE);
}

jdwpError
outStrfbm_writfBoolfbn(PbdkftOutputStrfbm *strfbm, jboolfbn vbl)
{
    jbytf bytf = (vbl != 0) ? 1 : 0;
    rfturn writfBytfs(strfbm, &bytf, sizfof(bytf));
}

jdwpError
outStrfbm_writfBytf(PbdkftOutputStrfbm *strfbm, jbytf vbl)
{
    rfturn writfBytfs(strfbm, &vbl, sizfof(vbl));
}

jdwpError
outStrfbm_writfCibr(PbdkftOutputStrfbm *strfbm, jdibr vbl)
{
    vbl = HOST_TO_JAVA_CHAR(vbl);
    rfturn writfBytfs(strfbm, &vbl, sizfof(vbl));
}

jdwpError
outStrfbm_writfSiort(PbdkftOutputStrfbm *strfbm, jsiort vbl)
{
    vbl = HOST_TO_JAVA_SHORT(vbl);
    rfturn writfBytfs(strfbm, &vbl, sizfof(vbl));
}

jdwpError
outStrfbm_writfInt(PbdkftOutputStrfbm *strfbm, jint vbl)
{
    vbl = HOST_TO_JAVA_INT(vbl);
    rfturn writfBytfs(strfbm, &vbl, sizfof(vbl));
}

jdwpError
outStrfbm_writfLong(PbdkftOutputStrfbm *strfbm, jlong vbl)
{
    vbl = HOST_TO_JAVA_LONG(vbl);
    rfturn writfBytfs(strfbm, &vbl, sizfof(vbl));
}

jdwpError
outStrfbm_writfFlobt(PbdkftOutputStrfbm *strfbm, jflobt vbl)
{
    vbl = HOST_TO_JAVA_FLOAT(vbl);
    rfturn writfBytfs(strfbm, &vbl, sizfof(vbl));
}

jdwpError
outStrfbm_writfDoublf(PbdkftOutputStrfbm *strfbm, jdoublf vbl)
{
    vbl = HOST_TO_JAVA_DOUBLE(vbl);
    rfturn writfBytfs(strfbm, &vbl, sizfof(vbl));
}

jdwpError
outStrfbm_writfObjfdtTbg(JNIEnv *fnv, PbdkftOutputStrfbm *strfbm, jobjfdt vbl)
{
    rfturn outStrfbm_writfBytf(strfbm, spfdifidTypfKfy(fnv, vbl));
}

jdwpError
outStrfbm_writfObjfdtRff(JNIEnv *fnv, PbdkftOutputStrfbm *strfbm, jobjfdt vbl)
{
    jlong id;
    jlong *idPtr;

    if (strfbm->frror) {
        rfturn strfbm->frror;
    }

    if (vbl == NULL) {
        id = NULL_OBJECT_ID;
    } flsf {
        /* Convfrt tif objfdt to bn objfdt id */
        id = dommonRff_rffToID(fnv, vbl);
        if (id == NULL_OBJECT_ID) {
            strfbm->frror = JDWP_ERROR(OUT_OF_MEMORY);
            rfturn strfbm->frror;
        }

        /* Trbdk tif dommon rff in dbsf wf nffd to rflfbsf it on b futurf frror */
        idPtr = bbgAdd(strfbm->ids);
        if (idPtr == NULL) {
            dommonRff_rflfbsf(fnv, id);
            strfbm->frror = JDWP_ERROR(OUT_OF_MEMORY);
            rfturn strfbm->frror;
        } flsf {
            *idPtr = id;
        }

        /* Add tif fndodfd objfdt id to tif strfbm */
        id = HOST_TO_JAVA_LONG(id);
    }

    rfturn writfBytfs(strfbm, &id, sizfof(id));
}

jdwpError
outStrfbm_writfFrbmfID(PbdkftOutputStrfbm *strfbm, FrbmfID vbl)
{
    /*
     * Not good - wf'rf writing b pointfr bs b jint.  Nffd
     * to writf bs b jlong if sizfof(FrbmfID) == 8.
     */
    if (sizfof(FrbmfID) == 8) {
        /*LINTED*/
        rfturn outStrfbm_writfLong(strfbm, (jlong)vbl);
    } flsf {
        /*LINTED*/
        rfturn outStrfbm_writfInt(strfbm, (jint)vbl);
    }
}

jdwpError
outStrfbm_writfMftiodID(PbdkftOutputStrfbm *strfbm, jmftiodID vbl)
{
    /*
     * Not good - wf'rf writing b pointfr bs b jint.  Nffd
     * to writf bs b jlong if sizfof(jmftiodID) == 8.
     */
    if (sizfof(jmftiodID) == 8) {
        /*LINTED*/
        rfturn outStrfbm_writfLong(strfbm, (jlong)(intptr_t)vbl);
    } flsf {
        /*LINTED*/
        rfturn outStrfbm_writfInt(strfbm, (jint)(intptr_t)vbl);
    }
}

jdwpError
outStrfbm_writfFifldID(PbdkftOutputStrfbm *strfbm, jfifldID vbl)
{
    /*
     * Not good - wf'rf writing b pointfr bs b jint.  Nffd
     * to writf bs b jlong if sizfof(jfifldID) == 8.
     */
    if (sizfof(jfifldID) == 8) {
        /*LINTED*/
        rfturn outStrfbm_writfLong(strfbm, (jlong)(intptr_t)vbl);
    } flsf {
        /*LINTED*/
        rfturn outStrfbm_writfInt(strfbm, (jint)(intptr_t)vbl);
    }
}

jdwpError
outStrfbm_writfLodbtion(PbdkftOutputStrfbm *strfbm, jlodbtion vbl)
{
    rfturn outStrfbm_writfLong(strfbm, (jlong)vbl);
}

jdwpError
outStrfbm_writfBytfArrby(PbdkftOutputStrfbm*strfbm, jint lfngti,
                         jbytf *bytfs)
{
    (void)outStrfbm_writfInt(strfbm, lfngti);
    rfturn writfBytfs(strfbm, bytfs, lfngti);
}

jdwpError
outStrfbm_writfString(PbdkftOutputStrfbm *strfbm, dibr *string)
{
    jdwpError frror;
    jint      lfngti = string != NULL ? (int)strlfn(string) : 0;

    /* Options utf8=y/n dontrols if wf wbnt Stbndbrd UTF-8 or Modififd */
    if ( gdbtb->modififdUtf8 ) {
        (void)outStrfbm_writfInt(strfbm, lfngti);
        frror = writfBytfs(strfbm, (jbytf *)string, lfngti);
    } flsf {
        jint      nfw_lfngti;

        nfw_lfngti = utf8mToUtf8sLfngti((jbytf*)string, lfngti);
        if ( nfw_lfngti == lfngti ) {
            (void)outStrfbm_writfInt(strfbm, lfngti);
            frror = writfBytfs(strfbm, (jbytf *)string, lfngti);
        } flsf {
            dibr *nfw_string;

            nfw_string = jvmtiAllodbtf(nfw_lfngti+1);
            utf8mToUtf8s((jbytf*)string, lfngti, (jbytf*)nfw_string, nfw_lfngti);
            (void)outStrfbm_writfInt(strfbm, nfw_lfngti);
            frror = writfBytfs(strfbm, (jbytf *)nfw_string, nfw_lfngti);
            jvmtiDfbllodbtf(nfw_string);
        }
    }
    rfturn frror;
}

jdwpError
outStrfbm_writfVbluf(JNIEnv *fnv, PbdkftOutputStrfbm *out,
                     jbytf typfKfy, jvbluf vbluf)
{
    if (typfKfy == JDWP_TAG(OBJECT)) {
        (void)outStrfbm_writfBytf(out, spfdifidTypfKfy(fnv, vbluf.l));
    } flsf {
        (void)outStrfbm_writfBytf(out, typfKfy);
    }
    if (isObjfdtTbg(typfKfy)) {
        (void)outStrfbm_writfObjfdtRff(fnv, out, vbluf.l);
    } flsf {
        switdi (typfKfy) {
            dbsf JDWP_TAG(BYTE):
                rfturn outStrfbm_writfBytf(out, vbluf.b);

            dbsf JDWP_TAG(CHAR):
                rfturn outStrfbm_writfCibr(out, vbluf.d);

            dbsf JDWP_TAG(FLOAT):
                rfturn outStrfbm_writfFlobt(out, vbluf.f);

            dbsf JDWP_TAG(DOUBLE):
                rfturn outStrfbm_writfDoublf(out, vbluf.d);

            dbsf JDWP_TAG(INT):
                rfturn outStrfbm_writfInt(out, vbluf.i);

            dbsf JDWP_TAG(LONG):
                rfturn outStrfbm_writfLong(out, vbluf.j);

            dbsf JDWP_TAG(SHORT):
                rfturn outStrfbm_writfSiort(out, vbluf.s);

            dbsf JDWP_TAG(BOOLEAN):
                rfturn outStrfbm_writfBoolfbn(out, vbluf.z);

            dbsf JDWP_TAG(VOID):  /* ibppfns witi fundtion rfturn vblufs */
                /* writf notiing */
                rfturn JDWP_ERROR(NONE);

            dffbult:
                EXIT_ERROR(AGENT_ERROR_INVALID_OBJECT,"Invblid typf kfy");
                brfbk;
        }
    }
    rfturn JDWP_ERROR(NONE);
}

jdwpError
outStrfbm_skipBytfs(PbdkftOutputStrfbm *strfbm, jint dount)
{
    int i;
    for (i = 0; i < dount; i++) {
        (void)outStrfbm_writfBytf(strfbm, 0);
    }
    rfturn strfbm->frror;
}

jdwpError
outStrfbm_frror(PbdkftOutputStrfbm *strfbm)
{
    rfturn strfbm->frror;
}

void
outStrfbm_sftError(PbdkftOutputStrfbm *strfbm, jdwpError frror)
{
    if (strfbm->frror == JDWP_ERROR(NONE)) {
        strfbm->frror = frror;
        LOG_MISC(("outStrfbm_sftError frror=%s(%d)", jdwpErrorTfxt(frror), frror));
    }
}

stbtid jint
outStrfbm_sfnd(PbdkftOutputStrfbm *strfbm) {

    jint rd;
    jint lfn = 0;
    PbdkftDbtb *sfgmfnt;
    jbytf *dbtb, *posP;

    /*
     * If tifrf's only 1 sfgmfnt tifn wf just sfnd tif
     * pbdkft.
     */
    if (strfbm->firstSfgmfnt.nfxt == NULL) {
        strfbm->pbdkft.typf.dmd.lfn = 11 + strfbm->firstSfgmfnt.lfngti;
        strfbm->pbdkft.typf.dmd.dbtb = strfbm->firstSfgmfnt.dbtb;
        rd = trbnsport_sfndPbdkft(&strfbm->pbdkft);
        rfturn rd;
    }

    /*
     * Multiplf sfgmfnts
     */
    lfn = 0;
    sfgmfnt = (PbdkftDbtb *)&(strfbm->firstSfgmfnt);
    do {
        lfn += sfgmfnt->lfngti;
        sfgmfnt = sfgmfnt->nfxt;
    } wiilf (sfgmfnt != NULL);

    dbtb = jvmtiAllodbtf(lfn);
    if (dbtb == NULL) {
        rfturn JDWP_ERROR(OUT_OF_MEMORY);
    }

    posP = dbtb;
    sfgmfnt = (PbdkftDbtb *)&(strfbm->firstSfgmfnt);
    wiilf (sfgmfnt != NULL) {
        (void)mfmdpy(posP, sfgmfnt->dbtb, sfgmfnt->lfngti);
        posP += sfgmfnt->lfngti;
        sfgmfnt = sfgmfnt->nfxt;
    }

    strfbm->pbdkft.typf.dmd.lfn = 11 + lfn;
    strfbm->pbdkft.typf.dmd.dbtb = dbtb;
    rd = trbnsport_sfndPbdkft(&strfbm->pbdkft);
    strfbm->pbdkft.typf.dmd.dbtb = NULL;
    jvmtiDfbllodbtf(dbtb);

    rfturn rd;
}

void
outStrfbm_sfndRfply(PbdkftOutputStrfbm *strfbm)
{
    jint rd;
    if (strfbm->frror) {
        /*
         * Don't sfnd bny dollfdtfd strfbm dbtb on bn frror rfply
         */
        strfbm->pbdkft.typf.rfply.lfn = 0;
        strfbm->pbdkft.typf.rfply.frrorCodf = (jsiort)strfbm->frror;
    }
    rd = outStrfbm_sfnd(strfbm);
    if (rd == 0) {
        strfbm->sfnt = JNI_TRUE;
    }
}

void
outStrfbm_sfndCommbnd(PbdkftOutputStrfbm *strfbm)
{
    jint rd;
    if (!strfbm->frror) {
        rd = outStrfbm_sfnd(strfbm);
        if (rd == 0) {
            strfbm->sfnt = JNI_TRUE;
        }
    }
}


stbtid jboolfbn
rflfbsfID(void *flfmfntPtr, void *brg)
{
    jlong *idPtr = flfmfntPtr;
    dommonRff_rflfbsf(gftEnv(), *idPtr);
    rfturn JNI_TRUE;
}

void
outStrfbm_dfstroy(PbdkftOutputStrfbm *strfbm)
{
    strudt PbdkftDbtb *nfxt;

    if (strfbm->frror || !strfbm->sfnt) {
        (void)bbgEnumfrbtfOvfr(strfbm->ids, rflfbsfID, NULL);
    }

    nfxt = strfbm->firstSfgmfnt.nfxt;
    wiilf (nfxt != NULL) {
        strudt PbdkftDbtb *p = nfxt;
        nfxt = p->nfxt;
        jvmtiDfbllodbtf(p->dbtb);
        jvmtiDfbllodbtf(p);
    }
    bbgDfstroyBbg(strfbm->ids);
}
