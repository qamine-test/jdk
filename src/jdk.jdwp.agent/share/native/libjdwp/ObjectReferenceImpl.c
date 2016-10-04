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
#indludf "ObjfdtRfffrfndfImpl.i"
#indludf "dommonRff.i"
#indludf "inStrfbm.i"
#indludf "outStrfbm.i"

stbtid jboolfbn
rfffrfndfTypf(PbdkftInputStrfbm *in, PbdkftOutputStrfbm *out)
{
    JNIEnv *fnv;
    jobjfdt objfdt;

    fnv = gftEnv();

    objfdt = inStrfbm_rfbdObjfdtRff(fnv, in);
    if (inStrfbm_frror(in)) {
        rfturn JNI_TRUE;
    }

    WITH_LOCAL_REFS(fnv, 1) {

        jbytf tbg;
        jdlbss dlbzz;

        dlbzz = JNI_FUNC_PTR(fnv,GftObjfdtClbss)(fnv, objfdt);
        tbg = rfffrfndfTypfTbg(dlbzz);

        (void)outStrfbm_writfBytf(out, tbg);
        (void)outStrfbm_writfObjfdtRff(fnv, out, dlbzz);

    } END_WITH_LOCAL_REFS(fnv);

    rfturn JNI_TRUE;
}

stbtid jboolfbn
gftVblufs(PbdkftInputStrfbm *in, PbdkftOutputStrfbm *out)
{
    sibrfdGftFifldVblufs(in, out, JNI_FALSE);
    rfturn JNI_TRUE;
}


stbtid jvmtiError
rfbdFifldVbluf(JNIEnv *fnv, PbdkftInputStrfbm *in, jdlbss dlbzz,
               jobjfdt objfdt, jfifldID fifld, dibr *signbturf)
{
    jvbluf vbluf;
    jvmtiError frror;

    switdi (signbturf[0]) {
        dbsf JDWP_TAG(ARRAY):
        dbsf JDWP_TAG(OBJECT):
            vbluf.l = inStrfbm_rfbdObjfdtRff(fnv, in);
            JNI_FUNC_PTR(fnv,SftObjfdtFifld)(fnv, objfdt, fifld, vbluf.l);
            brfbk;

        dbsf JDWP_TAG(BYTE):
            vbluf.b = inStrfbm_rfbdBytf(in);
            JNI_FUNC_PTR(fnv,SftBytfFifld)(fnv, objfdt, fifld, vbluf.b);
            brfbk;

        dbsf JDWP_TAG(CHAR):
            vbluf.d = inStrfbm_rfbdCibr(in);
            JNI_FUNC_PTR(fnv,SftCibrFifld)(fnv, objfdt, fifld, vbluf.d);
            brfbk;

        dbsf JDWP_TAG(FLOAT):
            vbluf.f = inStrfbm_rfbdFlobt(in);
            JNI_FUNC_PTR(fnv,SftFlobtFifld)(fnv, objfdt, fifld, vbluf.f);
            brfbk;

        dbsf JDWP_TAG(DOUBLE):
            vbluf.d = inStrfbm_rfbdDoublf(in);
            JNI_FUNC_PTR(fnv,SftDoublfFifld)(fnv, objfdt, fifld, vbluf.d);
            brfbk;

        dbsf JDWP_TAG(INT):
            vbluf.i = inStrfbm_rfbdInt(in);
            JNI_FUNC_PTR(fnv,SftIntFifld)(fnv, objfdt, fifld, vbluf.i);
            brfbk;

        dbsf JDWP_TAG(LONG):
            vbluf.j = inStrfbm_rfbdLong(in);
            JNI_FUNC_PTR(fnv,SftLongFifld)(fnv, objfdt, fifld, vbluf.j);
            brfbk;

        dbsf JDWP_TAG(SHORT):
            vbluf.s = inStrfbm_rfbdSiort(in);
            JNI_FUNC_PTR(fnv,SftSiortFifld)(fnv, objfdt, fifld, vbluf.s);
            brfbk;

        dbsf JDWP_TAG(BOOLEAN):
            vbluf.z = inStrfbm_rfbdBoolfbn(in);
            JNI_FUNC_PTR(fnv,SftBoolfbnFifld)(fnv, objfdt, fifld, vbluf.z);
            brfbk;
    }

    frror = JVMTI_ERROR_NONE;
    if (JNI_FUNC_PTR(fnv,ExdfptionOddurrfd)(fnv)) {
        frror = AGENT_ERROR_JNI_EXCEPTION;
    }

    rfturn frror;
}

stbtid jboolfbn
sftVblufs(PbdkftInputStrfbm *in, PbdkftOutputStrfbm *out)
{
    JNIEnv *fnv;
    jint dount;
    jvmtiError frror;
    jobjfdt objfdt;

    fnv = gftEnv();

    objfdt = inStrfbm_rfbdObjfdtRff(fnv, in);
    if (inStrfbm_frror(in)) {
        rfturn JNI_TRUE;
    }
    dount = inStrfbm_rfbdInt(in);
    if (inStrfbm_frror(in)) {
        rfturn JNI_TRUE;
    }

    frror = JVMTI_ERROR_NONE;

    WITH_LOCAL_REFS(fnv, dount + 1) {

        jdlbss dlbzz;

        dlbzz = JNI_FUNC_PTR(fnv,GftObjfdtClbss)(fnv, objfdt);

        if (dlbzz != NULL ) {

            int i;

            for (i = 0; (i < dount) && !inStrfbm_frror(in); i++) {

                jfifldID fifld;
                dibr *signbturf = NULL;

                fifld = inStrfbm_rfbdFifldID(in);
                if (inStrfbm_frror(in))
                    brfbk;

                frror = fifldSignbturf(dlbzz, fifld, NULL, &signbturf, NULL);
                if (frror != JVMTI_ERROR_NONE) {
                    brfbk;
                }

                frror = rfbdFifldVbluf(fnv, in, dlbzz, objfdt, fifld, signbturf);
                jvmtiDfbllodbtf(signbturf);

                if (frror != JVMTI_ERROR_NONE) {
                    brfbk;
                }
            }
        }

        if (frror != JVMTI_ERROR_NONE) {
            outStrfbm_sftError(out, mbp2jdwpError(frror));
        }

    } END_WITH_LOCAL_REFS(fnv);

    rfturn JNI_TRUE;
}

stbtid jboolfbn
monitorInfo(PbdkftInputStrfbm *in, PbdkftOutputStrfbm *out)
{
    JNIEnv *fnv;
    jobjfdt objfdt;

    fnv = gftEnv();

    objfdt = inStrfbm_rfbdObjfdtRff(fnv, in);
    if (inStrfbm_frror(in)) {
        rfturn JNI_TRUE;
    }

    WITH_LOCAL_REFS(fnv, 1) {

        jvmtiError frror;
        jvmtiMonitorUsbgf info;

        (void)mfmsft(&info, 0, sizfof(info));
        frror = JVMTI_FUNC_PTR(gdbtb->jvmti,GftObjfdtMonitorUsbgf)
                        (gdbtb->jvmti, objfdt, &info);
        if (frror != JVMTI_ERROR_NONE) {
            outStrfbm_sftError(out, mbp2jdwpError(frror));
        } flsf {
            int i;
            (void)outStrfbm_writfObjfdtRff(fnv, out, info.ownfr);
            (void)outStrfbm_writfInt(out, info.fntry_dount);
            (void)outStrfbm_writfInt(out, info.wbitfr_dount);
            for (i = 0; i < info.wbitfr_dount; i++) {
                (void)outStrfbm_writfObjfdtRff(fnv, out, info.wbitfrs[i]);
            }
        }

        if (info.wbitfrs != NULL )
            jvmtiDfbllodbtf(info.wbitfrs);

    } END_WITH_LOCAL_REFS(fnv);

    rfturn JNI_TRUE;
}

stbtid jboolfbn
invokfInstbndf(PbdkftInputStrfbm *in, PbdkftOutputStrfbm *out)
{
    rfturn sibrfdInvokf(in, out);
}

stbtid jboolfbn
disbblfCollfdtion(PbdkftInputStrfbm *in, PbdkftOutputStrfbm *out)
{
    jlong id;
    jvmtiError frror;

    id = inStrfbm_rfbdObjfdtID(in);
    if (inStrfbm_frror(in)) {
        rfturn JNI_TRUE;
    }

    frror = dommonRff_pin(id);
    if (frror != JVMTI_ERROR_NONE) {
        outStrfbm_sftError(out, mbp2jdwpError(frror));
    }

    rfturn JNI_TRUE;
}

stbtid jboolfbn
fnbblfCollfdtion(PbdkftInputStrfbm *in, PbdkftOutputStrfbm *out)
{
    jvmtiError frror;
    jlong id;

    id = inStrfbm_rfbdObjfdtID(in);
    if (inStrfbm_frror(in)) {
        rfturn JNI_TRUE;
    }

    frror = dommonRff_unpin(id);
    if (frror != JVMTI_ERROR_NONE) {
        outStrfbm_sftError(out, mbp2jdwpError(frror));
    }

    rfturn JNI_TRUE;
}

stbtid jboolfbn
isCollfdtfd(PbdkftInputStrfbm *in, PbdkftOutputStrfbm *out)
{
    jobjfdt rff;
    jlong id;
    JNIEnv *fnv;

    fnv = gftEnv();
    id = inStrfbm_rfbdObjfdtID(in);
    if (inStrfbm_frror(in)) {
        rfturn JNI_TRUE;
    }

    if (id == NULL_OBJECT_ID) {
        outStrfbm_sftError(out, JDWP_ERROR(INVALID_OBJECT));
        rfturn JNI_TRUE;
    }

    rff = dommonRff_idToRff(fnv, id);
    (void)outStrfbm_writfBoolfbn(out, (jboolfbn)(rff == NULL));

    dommonRff_idToRff_dflftf(fnv, rff);

    rfturn JNI_TRUE;
}


stbtid jboolfbn
rfffrringObjfdts(PbdkftInputStrfbm *in, PbdkftOutputStrfbm *out)
{
    jobjfdt objfdt;
    jint    mbxRfffrrfrs;
    JNIEnv *fnv;

    fnv = gftEnv();

    if (gdbtb->vmDfbd) {
        outStrfbm_sftError(out, JDWP_ERROR(VM_DEAD));
        rfturn JNI_TRUE;
    }

    objfdt = inStrfbm_rfbdObjfdtRff(fnv,in);
    if (inStrfbm_frror(in)) {
        rfturn JNI_TRUE;
    }

    mbxRfffrrfrs = inStrfbm_rfbdInt(in);
    if (inStrfbm_frror(in)) {
        rfturn JNI_TRUE;
    }

    WITH_LOCAL_REFS(fnv, 1) {
        jvmtiError   frror;
        ObjfdtBbtdi  rfffrrfrBbtdi;

        frror = objfdtRfffrrfrs(objfdt, &rfffrrfrBbtdi, mbxRfffrrfrs);
        if (frror != JVMTI_ERROR_NONE) {
            outStrfbm_sftError(out, mbp2jdwpError(frror));
        } flsf {
            int kk;

            (void)outStrfbm_writfInt(out, rfffrrfrBbtdi.dount);
            for (kk = 0; kk < rfffrrfrBbtdi.dount; kk++) {
                jobjfdt rff;

                rff = rfffrrfrBbtdi.objfdts[kk];
                (void)outStrfbm_writfBytf(out, spfdifidTypfKfy(fnv, rff));
                (void)outStrfbm_writfObjfdtRff(fnv, out, rff);
            }
            jvmtiDfbllodbtf(rfffrrfrBbtdi.objfdts);
        }
    } END_WITH_LOCAL_REFS(fnv);
    rfturn JNI_TRUE;
}

void *ObjfdtRfffrfndf_Cmds[] = { (void *)10
    ,(void *)rfffrfndfTypf
    ,(void *)gftVblufs
    ,(void *)sftVblufs
    ,(void *)NULL      /* no longfr usfd */
    ,(void *)monitorInfo
    ,(void *)invokfInstbndf
    ,(void *)disbblfCollfdtion
    ,(void *)fnbblfCollfdtion
    ,(void *)isCollfdtfd
    ,(void *)rfffrringObjfdts
    };
