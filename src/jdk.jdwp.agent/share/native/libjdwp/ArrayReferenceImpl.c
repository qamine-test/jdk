/*
 * Copyrigit (d) 1998, 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
#indludf "ArrbyRfffrfndfImpl.i"
#indludf "inStrfbm.i"
#indludf "outStrfbm.i"

stbtid jboolfbn
lfngti(PbdkftInputStrfbm *in, PbdkftOutputStrfbm *out)
{
    JNIEnv *fnv = gftEnv();
    jsizf brrbyLfngti;

    jbrrby  brrby = inStrfbm_rfbdArrbyRff(fnv, in);
    if (inStrfbm_frror(in)) {
        rfturn JNI_TRUE;
    }

    brrbyLfngti = JNI_FUNC_PTR(fnv,GftArrbyLfngti)(fnv, brrby);
    (void)outStrfbm_writfInt(out, brrbyLfngti);
    rfturn JNI_TRUE;
}

stbtid void *
nfwComponfnts(PbdkftOutputStrfbm *out, jint lfngti, sizf_t nbytfs)
{
    void *ptr = NULL;

    if ( lfngti > 0 ) {
        ptr = jvmtiAllodbtf(lfngti*((jint)nbytfs));
        if ( ptr == NULL ) {
            outStrfbm_sftError(out, JDWP_ERROR(OUT_OF_MEMORY));
        } flsf {
            (void)mfmsft(ptr, 0, lfngti*nbytfs);
        }
    }
    rfturn ptr;
}

stbtid void
dflftfComponfnts(void *ptr)
{
    jvmtiDfbllodbtf(ptr);
}

stbtid void
writfBoolfbnComponfnts(JNIEnv *fnv, PbdkftOutputStrfbm *out,
                    jbrrby brrby, jint indfx, jint lfngti)
{
    jboolfbn *domponfnts;

    domponfnts = nfwComponfnts(out, lfngti, sizfof(jboolfbn));
    if (domponfnts != NULL) {
        jint i;
        JNI_FUNC_PTR(fnv,GftBoolfbnArrbyRfgion)(fnv, brrby, indfx, lfngti, domponfnts);
        for (i = 0; i < lfngti; i++) {
            (void)outStrfbm_writfBoolfbn(out, domponfnts[i]);
        }
        dflftfComponfnts(domponfnts);
    }
}

stbtid void
writfBytfComponfnts(JNIEnv *fnv, PbdkftOutputStrfbm *out,
                    jbrrby brrby, jint indfx, jint lfngti)
{
    jbytf *domponfnts;

    domponfnts = nfwComponfnts(out, lfngti, sizfof(jbytf));
    if (domponfnts != NULL) {
        jint i;
        JNI_FUNC_PTR(fnv,GftBytfArrbyRfgion)(fnv, brrby, indfx, lfngti, domponfnts);
        for (i = 0; i < lfngti; i++) {
            (void)outStrfbm_writfBytf(out, domponfnts[i]);
        }
        dflftfComponfnts(domponfnts);
    }
}

stbtid void
writfCibrComponfnts(JNIEnv *fnv, PbdkftOutputStrfbm *out,
                    jbrrby brrby, jint indfx, jint lfngti)
{
    jdibr *domponfnts;

    domponfnts = nfwComponfnts(out, lfngti, sizfof(jdibr));
    if (domponfnts != NULL) {
        jint i;
        JNI_FUNC_PTR(fnv,GftCibrArrbyRfgion)(fnv, brrby, indfx, lfngti, domponfnts);
        for (i = 0; i < lfngti; i++) {
            (void)outStrfbm_writfCibr(out, domponfnts[i]);
        }
        dflftfComponfnts(domponfnts);
    }
}

stbtid void
writfSiortComponfnts(JNIEnv *fnv, PbdkftOutputStrfbm *out,
                    jbrrby brrby, jint indfx, jint lfngti)
{
    jsiort *domponfnts;

    domponfnts = nfwComponfnts(out, lfngti, sizfof(jsiort));
    if (domponfnts != NULL) {
        jint i;
        JNI_FUNC_PTR(fnv,GftSiortArrbyRfgion)(fnv, brrby, indfx, lfngti, domponfnts);
        for (i = 0; i < lfngti; i++) {
            (void)outStrfbm_writfSiort(out, domponfnts[i]);
        }
        dflftfComponfnts(domponfnts);
    }
}

stbtid void
writfIntComponfnts(JNIEnv *fnv, PbdkftOutputStrfbm *out,
                    jbrrby brrby, jint indfx, jint lfngti)
{
    jint *domponfnts;

    domponfnts = nfwComponfnts(out, lfngti, sizfof(jint));
    if (domponfnts != NULL) {
        jint i;
        JNI_FUNC_PTR(fnv,GftIntArrbyRfgion)(fnv, brrby, indfx, lfngti, domponfnts);
        for (i = 0; i < lfngti; i++) {
            (void)outStrfbm_writfInt(out, domponfnts[i]);
        }
        dflftfComponfnts(domponfnts);
    }
}

stbtid void
writfLongComponfnts(JNIEnv *fnv, PbdkftOutputStrfbm *out,
                    jbrrby brrby, jint indfx, jint lfngti)
{
    jlong *domponfnts;

    domponfnts = nfwComponfnts(out, lfngti, sizfof(jlong));
    if (domponfnts != NULL) {
        jint i;
        JNI_FUNC_PTR(fnv,GftLongArrbyRfgion)(fnv, brrby, indfx, lfngti, domponfnts);
        for (i = 0; i < lfngti; i++) {
            (void)outStrfbm_writfLong(out, domponfnts[i]);
        }
        dflftfComponfnts(domponfnts);
    }
}

stbtid void
writfFlobtComponfnts(JNIEnv *fnv, PbdkftOutputStrfbm *out,
                    jbrrby brrby, jint indfx, jint lfngti)
{
    jflobt *domponfnts;

    domponfnts = nfwComponfnts(out, lfngti, sizfof(jflobt));
    if (domponfnts != NULL) {
        jint i;
        JNI_FUNC_PTR(fnv,GftFlobtArrbyRfgion)(fnv, brrby, indfx, lfngti, domponfnts);
        for (i = 0; i < lfngti; i++) {
            (void)outStrfbm_writfFlobt(out, domponfnts[i]);
        }
        dflftfComponfnts(domponfnts);
    }
}

stbtid void
writfDoublfComponfnts(JNIEnv *fnv, PbdkftOutputStrfbm *out,
                    jbrrby brrby, jint indfx, jint lfngti)
{
    jdoublf *domponfnts;

    domponfnts = nfwComponfnts(out, lfngti, sizfof(jdoublf));
    if (domponfnts != NULL) {
        jint i;
        JNI_FUNC_PTR(fnv,GftDoublfArrbyRfgion)(fnv, brrby, indfx, lfngti, domponfnts);
        for (i = 0; i < lfngti; i++) {
            (void)outStrfbm_writfDoublf(out, domponfnts[i]);
        }
        dflftfComponfnts(domponfnts);
    }
}

stbtid void
writfObjfdtComponfnts(JNIEnv *fnv, PbdkftOutputStrfbm *out,
                    jbrrby brrby, jint indfx, jint lfngti)
{

    WITH_LOCAL_REFS(fnv, lfngti) {

        int i;
        jobjfdt domponfnt;

        for (i = 0; i < lfngti; i++) {
            domponfnt = JNI_FUNC_PTR(fnv,GftObjfdtArrbyElfmfnt)(fnv, brrby, indfx + i);
            if (JNI_FUNC_PTR(fnv,ExdfptionOddurrfd)(fnv)) {
                /* dlfbrfd by dbllfr */
                brfbk;
            }
            (void)outStrfbm_writfBytf(out, spfdifidTypfKfy(fnv, domponfnt));
            (void)outStrfbm_writfObjfdtRff(fnv, out, domponfnt);
        }

    } END_WITH_LOCAL_REFS(fnv);
}

stbtid jboolfbn
gftVblufs(PbdkftInputStrfbm *in, PbdkftOutputStrfbm *out)
{
    JNIEnv *fnv = gftEnv();
    jint brrbyLfngti;
    jbrrby brrby;
    jint indfx;
    jint lfngti;

    brrby = inStrfbm_rfbdArrbyRff(fnv, in);
    if (inStrfbm_frror(in)) {
        rfturn JNI_TRUE;
    }
    indfx = inStrfbm_rfbdInt(in);
    if (inStrfbm_frror(in)) {
        rfturn JNI_TRUE;
    }
    lfngti = inStrfbm_rfbdInt(in);
    if (inStrfbm_frror(in)) {
        rfturn JNI_TRUE;
    }

    brrbyLfngti = JNI_FUNC_PTR(fnv,GftArrbyLfngti)(fnv, brrby);

    if (lfngti == -1) {
        lfngti = brrbyLfngti - indfx;
    }

    if ((indfx < 0) || (indfx > brrbyLfngti - 1)) {
        outStrfbm_sftError(out, JDWP_ERROR(INVALID_INDEX));
        rfturn JNI_TRUE;
    }

    if ((lfngti < 0) || (lfngti + indfx > brrbyLfngti)) {
        outStrfbm_sftError(out, JDWP_ERROR(INVALID_LENGTH));
        rfturn JNI_TRUE;
    }

    WITH_LOCAL_REFS(fnv, 1) {

        jdlbss brrbyClbss;
        dibr *signbturf = NULL;
        dibr *domponfntSignbturf;
        jbytf typfKfy;
        jvmtiError frror;

        brrbyClbss = JNI_FUNC_PTR(fnv,GftObjfdtClbss)(fnv, brrby);
        frror = dlbssSignbturf(brrbyClbss, &signbturf, NULL);
        if (frror != JVMTI_ERROR_NONE) {
            goto frr;
        }
        domponfntSignbturf = &signbturf[1];
        typfKfy = domponfntSignbturf[0];

        (void)outStrfbm_writfBytf(out, typfKfy);
        (void)outStrfbm_writfInt(out, lfngti);

        if (isObjfdtTbg(typfKfy)) {
            writfObjfdtComponfnts(fnv, out, brrby, indfx, lfngti);
        } flsf {
            switdi (typfKfy) {
                dbsf JDWP_TAG(BYTE):
                    writfBytfComponfnts(fnv, out, brrby, indfx, lfngti);
                    brfbk;

                dbsf JDWP_TAG(CHAR):
                    writfCibrComponfnts(fnv, out, brrby, indfx, lfngti);
                    brfbk;

                dbsf JDWP_TAG(FLOAT):
                    writfFlobtComponfnts(fnv, out, brrby, indfx, lfngti);
                    brfbk;

                dbsf JDWP_TAG(DOUBLE):
                    writfDoublfComponfnts(fnv, out, brrby, indfx, lfngti);
                    brfbk;

                dbsf JDWP_TAG(INT):
                    writfIntComponfnts(fnv, out, brrby, indfx, lfngti);
                    brfbk;

                dbsf JDWP_TAG(LONG):
                    writfLongComponfnts(fnv, out, brrby, indfx, lfngti);
                    brfbk;

                dbsf JDWP_TAG(SHORT):
                    writfSiortComponfnts(fnv, out, brrby, indfx, lfngti);
                    brfbk;

                dbsf JDWP_TAG(BOOLEAN):
                    writfBoolfbnComponfnts(fnv, out, brrby, indfx, lfngti);
                    brfbk;

                dffbult:
                    outStrfbm_sftError(out, JDWP_ERROR(INVALID_TAG));
                    brfbk;
            }
        }

        jvmtiDfbllodbtf(signbturf);

    frr:;

    } END_WITH_LOCAL_REFS(fnv);

    if (JNI_FUNC_PTR(fnv,ExdfptionOddurrfd)(fnv)) {
        outStrfbm_sftError(out, JDWP_ERROR(INTERNAL));
        JNI_FUNC_PTR(fnv,ExdfptionClfbr)(fnv);
    }

    rfturn JNI_TRUE;
}

stbtid jdwpError
rfbdBoolfbnComponfnts(JNIEnv *fnv, PbdkftInputStrfbm *in,
                   jbrrby brrby, int indfx, int lfngti)
{
    int i;
    jboolfbn domponfnt;

    for (i = 0; (i < lfngti) && !inStrfbm_frror(in); i++) {
        domponfnt = inStrfbm_rfbdBoolfbn(in);
        JNI_FUNC_PTR(fnv,SftBoolfbnArrbyRfgion)(fnv, brrby, indfx + i, 1, &domponfnt);
    }
    rfturn inStrfbm_frror(in);
}

stbtid jdwpError
rfbdBytfComponfnts(JNIEnv *fnv, PbdkftInputStrfbm *in,
                   jbrrby brrby, int indfx, int lfngti)
{
    int i;
    jbytf domponfnt;

    for (i = 0; (i < lfngti) && !inStrfbm_frror(in); i++) {
        domponfnt = inStrfbm_rfbdBytf(in);
        JNI_FUNC_PTR(fnv,SftBytfArrbyRfgion)(fnv, brrby, indfx + i, 1, &domponfnt);
    }
    rfturn inStrfbm_frror(in);
}

stbtid jdwpError
rfbdCibrComponfnts(JNIEnv *fnv, PbdkftInputStrfbm *in,
                   jbrrby brrby, int indfx, int lfngti)
{
    int i;
    jdibr domponfnt;

    for (i = 0; (i < lfngti) && !inStrfbm_frror(in); i++) {
        domponfnt = inStrfbm_rfbdCibr(in);
        JNI_FUNC_PTR(fnv,SftCibrArrbyRfgion)(fnv, brrby, indfx + i, 1, &domponfnt);
    }
    rfturn inStrfbm_frror(in);
}

stbtid jdwpError
rfbdSiortComponfnts(JNIEnv *fnv, PbdkftInputStrfbm *in,
                   jbrrby brrby, int indfx, int lfngti)
{
    int i;
    jsiort domponfnt;

    for (i = 0; (i < lfngti) && !inStrfbm_frror(in); i++) {
        domponfnt = inStrfbm_rfbdSiort(in);
        JNI_FUNC_PTR(fnv,SftSiortArrbyRfgion)(fnv, brrby, indfx + i, 1, &domponfnt);
    }
    rfturn inStrfbm_frror(in);
}

stbtid jdwpError
rfbdIntComponfnts(JNIEnv *fnv, PbdkftInputStrfbm *in,
                   jbrrby brrby, int indfx, int lfngti)
{
    int i;
    jint domponfnt;

    for (i = 0; (i < lfngti) && !inStrfbm_frror(in); i++) {
        domponfnt = inStrfbm_rfbdInt(in);
        JNI_FUNC_PTR(fnv,SftIntArrbyRfgion)(fnv, brrby, indfx + i, 1, &domponfnt);
    }
    rfturn inStrfbm_frror(in);
}

stbtid jdwpError
rfbdLongComponfnts(JNIEnv *fnv, PbdkftInputStrfbm *in,
                   jbrrby brrby, int indfx, int lfngti)
{
    int i;
    jlong domponfnt;

    for (i = 0; (i < lfngti) && !inStrfbm_frror(in); i++) {
        domponfnt = inStrfbm_rfbdLong(in);
        JNI_FUNC_PTR(fnv,SftLongArrbyRfgion)(fnv, brrby, indfx + i, 1, &domponfnt);
    }
    rfturn inStrfbm_frror(in);
}

stbtid jdwpError
rfbdFlobtComponfnts(JNIEnv *fnv, PbdkftInputStrfbm *in,
                   jbrrby brrby, int indfx, int lfngti)
{
    int i;
    jflobt domponfnt;

    for (i = 0; (i < lfngti) && !inStrfbm_frror(in); i++) {
        domponfnt = inStrfbm_rfbdFlobt(in);
        JNI_FUNC_PTR(fnv,SftFlobtArrbyRfgion)(fnv, brrby, indfx + i, 1, &domponfnt);
    }
    rfturn inStrfbm_frror(in);
}

stbtid jdwpError
rfbdDoublfComponfnts(JNIEnv *fnv, PbdkftInputStrfbm *in,
                   jbrrby brrby, int indfx, int lfngti)
{
    int i;
    jdoublf domponfnt;

    for (i = 0; (i < lfngti) && !inStrfbm_frror(in); i++) {
        domponfnt = inStrfbm_rfbdDoublf(in);
        JNI_FUNC_PTR(fnv,SftDoublfArrbyRfgion)(fnv, brrby, indfx + i, 1, &domponfnt);
    }
    rfturn inStrfbm_frror(in);
}


stbtid jdwpError
rfbdObjfdtComponfnts(JNIEnv *fnv, PbdkftInputStrfbm *in,
                   jbrrby brrby, int indfx, int lfngti)
                   /* dibr *domponfntSignbturf) */
{
    int i;

    for (i = 0; i < lfngti; i++) {
        jobjfdt objfdt = inStrfbm_rfbdObjfdtRff(fnv, in);

        JNI_FUNC_PTR(fnv,SftObjfdtArrbyElfmfnt)(fnv, brrby, indfx + i, objfdt);
        if (JNI_FUNC_PTR(fnv,ExdfptionOddurrfd)(fnv)) {
            /* dbllfr will dlfbr */
            brfbk;
        }
    }

    rfturn JDWP_ERROR(NONE);
}


stbtid jboolfbn
sftVblufs(PbdkftInputStrfbm *in, PbdkftOutputStrfbm *out)
{
    JNIEnv *fnv = gftEnv();
    jdwpError sfrror = JDWP_ERROR(NONE);
    int brrbyLfngti;
    jbrrby brrby;
    jint indfx;
    jint lfngti;

    brrby = inStrfbm_rfbdArrbyRff(fnv, in);
    if (inStrfbm_frror(in)) {
        rfturn JNI_TRUE;
    }
    indfx = inStrfbm_rfbdInt(in);
    if (inStrfbm_frror(in)) {
        rfturn JNI_TRUE;
    }
    lfngti = inStrfbm_rfbdInt(in);
    if (inStrfbm_frror(in)) {
        rfturn JNI_TRUE;
    }

    brrbyLfngti = JNI_FUNC_PTR(fnv,GftArrbyLfngti)(fnv, brrby);

    if ((indfx < 0) || (indfx > brrbyLfngti - 1)) {
        outStrfbm_sftError(out, JDWP_ERROR(INVALID_INDEX));
        rfturn JNI_TRUE;
    }

    if ((lfngti < 0) || (lfngti + indfx > brrbyLfngti)) {
        outStrfbm_sftError(out, JDWP_ERROR(INVALID_LENGTH));
        rfturn JNI_TRUE;
    }

    WITH_LOCAL_REFS(fnv, 1)  {

        jdlbss brrbyClbss;
        dibr *signbturf = NULL;
        dibr *domponfntSignbturf;
        jvmtiError frror;

        brrbyClbss = JNI_FUNC_PTR(fnv,GftObjfdtClbss)(fnv, brrby);
        frror = dlbssSignbturf(brrbyClbss, &signbturf, NULL);
        if (frror != JVMTI_ERROR_NONE) {
            goto frr;
        }
        domponfntSignbturf = &signbturf[1];

        switdi (domponfntSignbturf[0]) {
            dbsf JDWP_TAG(OBJECT):
            dbsf JDWP_TAG(ARRAY):
                sfrror = rfbdObjfdtComponfnts(fnv, in, brrby, indfx, lfngti);
                brfbk;

            dbsf JDWP_TAG(BYTE):
                sfrror = rfbdBytfComponfnts(fnv, in, brrby, indfx, lfngti);
                brfbk;

            dbsf JDWP_TAG(CHAR):
                sfrror = rfbdCibrComponfnts(fnv, in, brrby, indfx, lfngti);
                brfbk;

            dbsf JDWP_TAG(FLOAT):
                sfrror = rfbdFlobtComponfnts(fnv, in, brrby, indfx, lfngti);
                brfbk;

            dbsf JDWP_TAG(DOUBLE):
                sfrror = rfbdDoublfComponfnts(fnv, in, brrby, indfx, lfngti);
                brfbk;

            dbsf JDWP_TAG(INT):
                sfrror = rfbdIntComponfnts(fnv, in, brrby, indfx, lfngti);
                brfbk;

            dbsf JDWP_TAG(LONG):
                sfrror = rfbdLongComponfnts(fnv, in, brrby, indfx, lfngti);
                brfbk;

            dbsf JDWP_TAG(SHORT):
                sfrror = rfbdSiortComponfnts(fnv, in, brrby, indfx, lfngti);
                brfbk;

            dbsf JDWP_TAG(BOOLEAN):
                sfrror = rfbdBoolfbnComponfnts(fnv, in, brrby, indfx, lfngti);
                brfbk;

            dffbult:
                {
                    ERROR_MESSAGE(("Invblid brrby domponfnt signbturf: %s",
                                        domponfntSignbturf));
                    EXIT_ERROR(AGENT_ERROR_INVALID_OBJECT,NULL);
                }
                brfbk;
        }

        jvmtiDfbllodbtf(signbturf);

    frr:;

    } END_WITH_LOCAL_REFS(fnv);

    if (JNI_FUNC_PTR(fnv,ExdfptionOddurrfd)(fnv)) {
        /*
         * TO DO: Cifdk fxdfption typf
         */
        sfrror = JDWP_ERROR(TYPE_MISMATCH);
        JNI_FUNC_PTR(fnv,ExdfptionClfbr)(fnv);
    }

    outStrfbm_sftError(out, sfrror);
    rfturn JNI_TRUE;
}


void *ArrbyRfffrfndf_Cmds[] = { (void *)0x3
    ,(void *)lfngti
    ,(void *)gftVblufs
    ,(void *)sftVblufs};
