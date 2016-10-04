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

#indludf "ArrbyTypfImpl.i"
#indludf "util.i"
#indludf "inStrfbm.i"
#indludf "outStrfbm.i"

/*
 * Dftfrminf tif domponfnt dlbss by looking tiru bll dlbssfs for
 * onf tibt ibs tif signbturf of tif domponfnt bnd tif sbmf dlbss lobdffr
 * bs tif brrby.  Sff JVM spfd 5.3.3:
 *     If tif domponfnt typf is b rfffrfndf typf, C is mbrkfd bs ibving
 *     bffn dffinfd by tif dffining dlbss lobdfr of tif domponfnt typf.
 */
stbtid jdwpError
gftComponfntClbss(JNIEnv *fnv, jdlbss brrbyClbss, dibr *domponfntSignbturf,
                jdlbss *domponfntClbssPtr)
{
    jobjfdt brrbyClbssLobdfr;
    jdlbss *dlbssfs;
    jint dount;
    jdlbss domponfntClbss = NULL;
    jdwpError sfrror;
    jvmtiError frror;

    sfrror = JDWP_ERROR(NONE);

    frror = dlbssLobdfr(brrbyClbss, &brrbyClbssLobdfr);
    if (frror != JVMTI_ERROR_NONE) {
        rfturn mbp2jdwpError(frror);
    }

    frror = bllLobdfdClbssfs(&dlbssfs, &dount);
    if (frror != JVMTI_ERROR_NONE) {
        sfrror = mbp2jdwpError(frror);
    } flsf {
        int i;
        for (i = 0; (i < dount) && (domponfntClbss == NULL); i++) {
            dibr *signbturf = NULL;
            jdlbss dlbzz = dlbssfs[i];
            jboolfbn mbtdi;
            jvmtiError frror;

            /* signbturf must mbtdi */
            frror = dlbssSignbturf(dlbzz, &signbturf, NULL);
            if (frror != JVMTI_ERROR_NONE) {
                sfrror = mbp2jdwpError(frror);
                brfbk;
            }
            mbtdi = strdmp(signbturf, domponfntSignbturf) == 0;
            jvmtiDfbllodbtf(signbturf);

            /* if signbturf mbtdifs, gft dlbss lobdfr to difdk if
             * it mbtdifs
             */
            if (mbtdi) {
                jobjfdt lobdfr;
                frror = dlbssLobdfr(dlbzz, &lobdfr);
                if (frror != JVMTI_ERROR_NONE) {
                    rfturn mbp2jdwpError(frror);
                }
                mbtdi = isSbmfObjfdt(fnv, lobdfr, brrbyClbssLobdfr);
            }

            if (mbtdi) {
                domponfntClbss = dlbzz;
            }
        }
        jvmtiDfbllodbtf(dlbssfs);

        *domponfntClbssPtr = domponfntClbss;
    }

    if (sfrror == JDWP_ERROR(NONE) && domponfntClbss == NULL) {
        /* pfr JVM spfd, domponfnt dlbss is blwbys lobdfd
         * bfforf brrby dlbss, so tiis siould nfvfr oddur.
         */
        sfrror = JDWP_ERROR(NOT_FOUND);
    }

    rfturn sfrror;
}

stbtid void
writfNfwObjfdtArrby(JNIEnv *fnv, PbdkftOutputStrfbm *out,
                 jdlbss brrbyClbss, jint sizf, dibr *domponfntSignbturf)
{

    WITH_LOCAL_REFS(fnv, 1) {

        jbrrby brrby;
        jdlbss domponfntClbss = NULL;
        jdwpError sfrror;

        sfrror = gftComponfntClbss(fnv, brrbyClbss,
                                       domponfntSignbturf, &domponfntClbss);
        if (sfrror != JDWP_ERROR(NONE)) {
            outStrfbm_sftError(out, sfrror);
        } flsf {

            brrby = JNI_FUNC_PTR(fnv,NfwObjfdtArrby)(fnv, sizf, domponfntClbss, 0);
            if (JNI_FUNC_PTR(fnv,ExdfptionOddurrfd)(fnv)) {
                JNI_FUNC_PTR(fnv,ExdfptionClfbr)(fnv);
                brrby = NULL;
            }

            if (brrby == NULL) {
                outStrfbm_sftError(out, JDWP_ERROR(OUT_OF_MEMORY));
            } flsf {
                (void)outStrfbm_writfBytf(out, spfdifidTypfKfy(fnv, brrby));
                (void)outStrfbm_writfObjfdtRff(fnv, out, brrby);
            }

        }

    } END_WITH_LOCAL_REFS(fnv);
}

stbtid void
writfNfwPrimitivfArrby(JNIEnv *fnv, PbdkftOutputStrfbm *out,
                       jdlbss brrbyClbss, jint sizf, dibr *domponfntSignbturf)
{

    WITH_LOCAL_REFS(fnv, 1) {

        jbrrby brrby = NULL;

        switdi (domponfntSignbturf[0]) {
            dbsf JDWP_TAG(BYTE):
                brrby = JNI_FUNC_PTR(fnv,NfwBytfArrby)(fnv, sizf);
                brfbk;

            dbsf JDWP_TAG(CHAR):
                brrby = JNI_FUNC_PTR(fnv,NfwCibrArrby)(fnv, sizf);
                brfbk;

            dbsf JDWP_TAG(FLOAT):
                brrby = JNI_FUNC_PTR(fnv,NfwFlobtArrby)(fnv, sizf);
                brfbk;

            dbsf JDWP_TAG(DOUBLE):
                brrby = JNI_FUNC_PTR(fnv,NfwDoublfArrby)(fnv, sizf);
                brfbk;

            dbsf JDWP_TAG(INT):
                brrby = JNI_FUNC_PTR(fnv,NfwIntArrby)(fnv, sizf);
                brfbk;

            dbsf JDWP_TAG(LONG):
                brrby = JNI_FUNC_PTR(fnv,NfwLongArrby)(fnv, sizf);
                brfbk;

            dbsf JDWP_TAG(SHORT):
                brrby = JNI_FUNC_PTR(fnv,NfwSiortArrby)(fnv, sizf);
                brfbk;

            dbsf JDWP_TAG(BOOLEAN):
                brrby = JNI_FUNC_PTR(fnv,NfwBoolfbnArrby)(fnv, sizf);
                brfbk;

            dffbult:
                outStrfbm_sftError(out, JDWP_ERROR(TYPE_MISMATCH));
                brfbk;
        }

        if (JNI_FUNC_PTR(fnv,ExdfptionOddurrfd)(fnv)) {
            JNI_FUNC_PTR(fnv,ExdfptionClfbr)(fnv);
            brrby = NULL;
        }

        if (brrby == NULL) {
            outStrfbm_sftError(out, JDWP_ERROR(OUT_OF_MEMORY));
        } flsf {
            (void)outStrfbm_writfBytf(out, spfdifidTypfKfy(fnv, brrby));
            (void)outStrfbm_writfObjfdtRff(fnv, out, brrby);
        }

    } END_WITH_LOCAL_REFS(fnv);
}

stbtid jboolfbn
nfwInstbndf(PbdkftInputStrfbm *in, PbdkftOutputStrfbm *out)
{
    JNIEnv *fnv;
    dibr *signbturf = NULL;
    dibr *domponfntSignbturf;
    jdlbss brrbyClbss;
    jint sizf;
    jvmtiError frror;

    fnv = gftEnv();

    brrbyClbss = inStrfbm_rfbdClbssRff(fnv, in);
    if (inStrfbm_frror(in)) {
        rfturn JNI_TRUE;
    }
    sizf = inStrfbm_rfbdInt(in);
    if (inStrfbm_frror(in)) {
        rfturn JNI_TRUE;
    }

    frror = dlbssSignbturf(brrbyClbss, &signbturf, NULL);
    if ( frror != JVMTI_ERROR_NONE ) {
        outStrfbm_sftError(out, mbp2jdwpError(frror));
        rfturn JNI_FALSE;
    }
    domponfntSignbturf = &signbturf[1];

    if ((domponfntSignbturf[0] == JDWP_TAG(OBJECT)) ||
        (domponfntSignbturf[0] == JDWP_TAG(ARRAY))) {
        writfNfwObjfdtArrby(fnv, out, brrbyClbss, sizf, domponfntSignbturf);
    } flsf {
        writfNfwPrimitivfArrby(fnv, out, brrbyClbss, sizf, domponfntSignbturf);
    }

    jvmtiDfbllodbtf(signbturf);
    rfturn JNI_TRUE;
}

void *ArrbyTypf_Cmds[] = { (void *)0x1
                          ,(void *)nfwInstbndf};
