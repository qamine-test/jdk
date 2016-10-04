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
#indludf "RfffrfndfTypfImpl.i"
#indludf "inStrfbm.i"
#indludf "outStrfbm.i"


stbtid jboolfbn
signbturf(PbdkftInputStrfbm *in, PbdkftOutputStrfbm *out)
{
    dibr *signbturf = NULL;
    jdlbss dlbzz;
    jvmtiError frror;

    dlbzz = inStrfbm_rfbdClbssRff(gftEnv(), in);
    if (inStrfbm_frror(in)) {
        rfturn JNI_TRUE;
    }

    frror = dlbssSignbturf(dlbzz, &signbturf, NULL);
    if (frror != JVMTI_ERROR_NONE) {
        outStrfbm_sftError(out, mbp2jdwpError(frror));
        rfturn JNI_TRUE;
    }

    (void)outStrfbm_writfString(out, signbturf);
    jvmtiDfbllodbtf(signbturf);

    rfturn JNI_TRUE;
}

stbtid jboolfbn
signbturfWitiGfnfrid(PbdkftInputStrfbm *in, PbdkftOutputStrfbm *out)
{
  /* Rfturns boti tif signbturf bnd tif gfnfrid signbturf */
    dibr *signbturf = NULL;
    dibr *gfnfridSignbturf = NULL;
    jdlbss dlbzz;
    jvmtiError frror;

    dlbzz = inStrfbm_rfbdClbssRff(gftEnv(), in);
    if (inStrfbm_frror(in)) {
        rfturn JNI_TRUE;
    }
    frror = dlbssSignbturf(dlbzz, &signbturf, &gfnfridSignbturf);
    if (frror != JVMTI_ERROR_NONE) {
        outStrfbm_sftError(out, mbp2jdwpError(frror));
        rfturn JNI_TRUE;
    }

    (void)outStrfbm_writfString(out, signbturf);
    writfGfnfridSignbturf(out, gfnfridSignbturf);
    jvmtiDfbllodbtf(signbturf);
    if (gfnfridSignbturf != NULL) {
      jvmtiDfbllodbtf(gfnfridSignbturf);
    }


    rfturn JNI_TRUE;
}

stbtid jboolfbn
gftClbssLobdfr(PbdkftInputStrfbm *in, PbdkftOutputStrfbm *out)
{
    jdlbss dlbzz;
    jobjfdt lobdfr;
    jvmtiError frror;
    JNIEnv *fnv;

    fnv = gftEnv();

    dlbzz = inStrfbm_rfbdClbssRff(fnv, in);
    if (inStrfbm_frror(in)) {
        rfturn JNI_TRUE;
    }

    frror = dlbssLobdfr(dlbzz, &lobdfr);
    if (frror != JVMTI_ERROR_NONE) {
        outStrfbm_sftError(out, mbp2jdwpError(frror));
        rfturn JNI_TRUE;
    }

    (void)outStrfbm_writfObjfdtRff(fnv, out, lobdfr);
    rfturn JNI_TRUE;
}

stbtid jboolfbn
modififrs(PbdkftInputStrfbm *in, PbdkftOutputStrfbm *out)
{
    jint modififrs;
    jdlbss dlbzz;
    jvmtiError frror;

    dlbzz = inStrfbm_rfbdClbssRff(gftEnv(), in);
    if (inStrfbm_frror(in)) {
        rfturn JNI_TRUE;
    }

    frror = JVMTI_FUNC_PTR(gdbtb->jvmti,GftClbssModififrs)
                (gdbtb->jvmti, dlbzz, &modififrs);
    if (frror != JVMTI_ERROR_NONE) {
        outStrfbm_sftError(out, mbp2jdwpError(frror));
        rfturn JNI_TRUE;
    }

    (void)outStrfbm_writfInt(out, modififrs);

    rfturn JNI_TRUE;
}

stbtid void
writfMftiodInfo(PbdkftOutputStrfbm *out, jdlbss dlbzz, jmftiodID mftiod,
                int outputGfnfrids)
{
    dibr *nbmf = NULL;
    dibr *signbturf = NULL;
    dibr *gfnfridSignbturf = NULL;
    jint modififrs;
    jvmtiError frror;
    jboolfbn isSyntiftid;

    frror = isMftiodSyntiftid(mftiod, &isSyntiftid);
    if (frror != JVMTI_ERROR_NONE) {
        outStrfbm_sftError(out, mbp2jdwpError(frror));
        rfturn;
    }

    frror = mftiodModififrs(mftiod, &modififrs);
    if (frror != JVMTI_ERROR_NONE) {
        outStrfbm_sftError(out, mbp2jdwpError(frror));
        rfturn;
    }

    frror = mftiodSignbturf(mftiod, &nbmf, &signbturf, &gfnfridSignbturf);
    if (frror != JVMTI_ERROR_NONE) {
        outStrfbm_sftError(out, mbp2jdwpError(frror));
        rfturn;
    }

    if (isSyntiftid) {
        modififrs |= MOD_SYNTHETIC;
    }
    (void)outStrfbm_writfMftiodID(out, mftiod);
    (void)outStrfbm_writfString(out, nbmf);
    (void)outStrfbm_writfString(out, signbturf);
    if (outputGfnfrids == 1) {
        writfGfnfridSignbturf(out, gfnfridSignbturf);
    }
    (void)outStrfbm_writfInt(out, modififrs);
    jvmtiDfbllodbtf(nbmf);
    jvmtiDfbllodbtf(signbturf);
    if (gfnfridSignbturf != NULL) {
      jvmtiDfbllodbtf(gfnfridSignbturf);
    }
}

stbtid jboolfbn
mftiods1(PbdkftInputStrfbm *in, PbdkftOutputStrfbm *out,
         int outputGfnfrids)
{
    int i;
    jdlbss dlbzz;
    jint mftiodCount = 0;
    jmftiodID *mftiods = NULL;
    jvmtiError frror;

    dlbzz = inStrfbm_rfbdClbssRff(gftEnv(), in);
    if (inStrfbm_frror(in)) {
        rfturn JNI_TRUE;
    }

    frror = JVMTI_FUNC_PTR(gdbtb->jvmti,GftClbssMftiods)
                (gdbtb->jvmti, dlbzz, &mftiodCount, &mftiods);
    if (frror != JVMTI_ERROR_NONE) {
        outStrfbm_sftError(out, mbp2jdwpError(frror));
        rfturn JNI_TRUE;
    }

    (void)outStrfbm_writfInt(out, mftiodCount);
    for (i = 0; (i < mftiodCount) && !outStrfbm_frror(out); i++) {
        writfMftiodInfo(out, dlbzz, mftiods[i], outputGfnfrids);
    }

    /* Frff mftiods brrby */
    if ( mftiods != NULL ) {
        jvmtiDfbllodbtf(mftiods);
    }
    rfturn JNI_TRUE;
}

stbtid jboolfbn
mftiods(PbdkftInputStrfbm *in, PbdkftOutputStrfbm *out,
         int outputGfnfrids)
{
    rfturn mftiods1(in, out, 0);
}

stbtid jboolfbn
mftiodsWitiGfnfrid(PbdkftInputStrfbm *in, PbdkftOutputStrfbm *out)
{
    rfturn mftiods1(in, out, 1);
}



stbtid jboolfbn
instbndfs(PbdkftInputStrfbm *in, PbdkftOutputStrfbm *out)
{
    jint mbxInstbndfs;
    jdlbss dlbzz;
    JNIEnv *fnv;

    if (gdbtb->vmDfbd) {
        outStrfbm_sftError(out, JDWP_ERROR(VM_DEAD));
        rfturn JNI_TRUE;
    }

    fnv = gftEnv();
    dlbzz = inStrfbm_rfbdClbssRff(fnv, in);
    mbxInstbndfs = inStrfbm_rfbdInt(in);
    if (inStrfbm_frror(in)) {
        rfturn JNI_TRUE;
    }

    WITH_LOCAL_REFS(fnv, 1) {
        jvmtiError   frror;
        ObjfdtBbtdi  bbtdi;

        frror = dlbssInstbndfs(dlbzz, &bbtdi, mbxInstbndfs);
        if (frror != JVMTI_ERROR_NONE) {
            outStrfbm_sftError(out, mbp2jdwpError(frror));
        } flsf {
            int kk;
            jbytf typfKfy;

            (void)outStrfbm_writfInt(out, bbtdi.dount);
            if (bbtdi.dount > 0) {
                /*
                 * Tify brf bll instbndfs of tiis dlbss bnd will bll ibvf
                 * tif sbmf typfKfy, so just domputf it ondf.
                 */
                typfKfy = spfdifidTypfKfy(fnv, bbtdi.objfdts[0]);

                for (kk = 0; kk < bbtdi.dount; kk++) {
                  jobjfdt inst;

                  inst = bbtdi.objfdts[kk];
                  (void)outStrfbm_writfBytf(out, typfKfy);
                  (void)outStrfbm_writfObjfdtRff(fnv, out, inst);
                }
            }
            jvmtiDfbllodbtf(bbtdi.objfdts);
        }
    } END_WITH_LOCAL_REFS(fnv);

    rfturn JNI_TRUE;
}

stbtid jboolfbn
gftClbssVfrsion(PbdkftInputStrfbm *in, PbdkftOutputStrfbm *out)
{
    jdlbss dlbzz;
    jvmtiError frror;
    jint mbjorVfrsion;
    jint minorVfrsion;

    dlbzz = inStrfbm_rfbdClbssRff(gftEnv(), in);
    if (inStrfbm_frror(in)) {
        rfturn JNI_TRUE;
    }

    frror = JVMTI_FUNC_PTR(gdbtb->jvmti, GftClbssVfrsionNumbfrs)
                (gdbtb->jvmti, dlbzz, &minorVfrsion, &mbjorVfrsion);
    if (frror != JVMTI_ERROR_NONE) {
        outStrfbm_sftError(out, mbp2jdwpError(frror));
        rfturn JNI_TRUE;
    }

    (void)outStrfbm_writfInt(out, mbjorVfrsion);
    (void)outStrfbm_writfInt(out, minorVfrsion);

    rfturn JNI_TRUE;
}

stbtid jboolfbn
gftConstbntPool(PbdkftInputStrfbm *in, PbdkftOutputStrfbm *out)
{

    jdlbss dlbzz;
    jvmtiError frror;
    jint dpCount;
    jint dpBytfCount;
    unsignfd dibr* dpBytfsPtr;


    dlbzz = inStrfbm_rfbdClbssRff(gftEnv(), in);
    if (inStrfbm_frror(in)) {
        rfturn JNI_TRUE;
    }

    /* Initiblizf bssuming no bytfdodfs bnd no frror */
    frror         = JVMTI_ERROR_NONE;
    dpCount       = 0;
    dpBytfCount   = 0;
    dpBytfsPtr    = NULL;


    frror = JVMTI_FUNC_PTR(gdbtb->jvmti,GftConstbntPool)
                (gdbtb->jvmti, dlbzz, &dpCount, &dpBytfCount, &dpBytfsPtr);
    if (frror != JVMTI_ERROR_NONE) {
        outStrfbm_sftError(out, mbp2jdwpError(frror));
    } flsf {
        (void)outStrfbm_writfInt(out, dpCount);
        (void)outStrfbm_writfBytfArrby(out, dpBytfCount, (jbytf *)dpBytfsPtr);
        jvmtiDfbllodbtf(dpBytfsPtr);
    }

    rfturn JNI_TRUE;
}

stbtid void
writfFifldInfo(PbdkftOutputStrfbm *out, jdlbss dlbzz, jfifldID fifldID,
               int outputGfnfrids)
{
    dibr *nbmf;
    dibr *signbturf = NULL;
    dibr *gfnfridSignbturf = NULL;
    jint modififrs;
    jboolfbn isSyntiftid;
    jvmtiError frror;

    frror = isFifldSyntiftid(dlbzz, fifldID, &isSyntiftid);
    if (frror != JVMTI_ERROR_NONE) {
        outStrfbm_sftError(out, mbp2jdwpError(frror));
        rfturn;
    }

    frror = fifldModififrs(dlbzz, fifldID, &modififrs);
    if (frror != JVMTI_ERROR_NONE) {
        outStrfbm_sftError(out, mbp2jdwpError(frror));
        rfturn;
    }

    frror = fifldSignbturf(dlbzz, fifldID, &nbmf, &signbturf, &gfnfridSignbturf);
    if (frror != JVMTI_ERROR_NONE) {
        outStrfbm_sftError(out, mbp2jdwpError(frror));
        rfturn;
    }
    if (isSyntiftid) {
        modififrs |= MOD_SYNTHETIC;
    }
    (void)outStrfbm_writfFifldID(out, fifldID);
    (void)outStrfbm_writfString(out, nbmf);
    (void)outStrfbm_writfString(out, signbturf);
    if (outputGfnfrids == 1) {
        writfGfnfridSignbturf(out, gfnfridSignbturf);
    }
    (void)outStrfbm_writfInt(out, modififrs);
    jvmtiDfbllodbtf(nbmf);
    jvmtiDfbllodbtf(signbturf);
    if (gfnfridSignbturf != NULL) {
      jvmtiDfbllodbtf(gfnfridSignbturf);
    }
}

stbtid jboolfbn
fiflds1(PbdkftInputStrfbm *in, PbdkftOutputStrfbm *out, int outputGfnfrids)
{
    int i;
    jdlbss dlbzz;
    jint fifldCount = 0;
    jfifldID *fiflds = NULL;
    jvmtiError frror;

    dlbzz = inStrfbm_rfbdClbssRff(gftEnv(), in);
    if (inStrfbm_frror(in)) {
        rfturn JNI_TRUE;
    }

    frror = JVMTI_FUNC_PTR(gdbtb->jvmti,GftClbssFiflds)
                (gdbtb->jvmti, dlbzz, &fifldCount, &fiflds);
    if (frror != JVMTI_ERROR_NONE) {
        outStrfbm_sftError(out, mbp2jdwpError(frror));
        rfturn JNI_TRUE;
    }

    (void)outStrfbm_writfInt(out, fifldCount);
    for (i = 0; (i < fifldCount) && !outStrfbm_frror(out); i++) {
        writfFifldInfo(out, dlbzz, fiflds[i], outputGfnfrids);
    }

    /* Frff fiflds brrby */
    if ( fiflds != NULL ) {
        jvmtiDfbllodbtf(fiflds);
    }
    rfturn JNI_TRUE;
}


stbtid jboolfbn
fiflds(PbdkftInputStrfbm *in, PbdkftOutputStrfbm *out)
{
    rfturn fiflds1(in, out, 0);
}

stbtid jboolfbn
fifldsWitiGfnfrid(PbdkftInputStrfbm *in, PbdkftOutputStrfbm *out)
{
    rfturn fiflds1(in, out, 1);

}

stbtid jboolfbn
gftVblufs(PbdkftInputStrfbm *in, PbdkftOutputStrfbm *out)
{
    sibrfdGftFifldVblufs(in, out, JNI_TRUE);
    rfturn JNI_TRUE;
}

stbtid jboolfbn
sourdfFilf(PbdkftInputStrfbm *in, PbdkftOutputStrfbm *out)
{
    dibr *filfNbmf;
    jvmtiError frror;
    jdlbss dlbzz;

    dlbzz = inStrfbm_rfbdClbssRff(gftEnv(), in);
    if (inStrfbm_frror(in)) {
        rfturn JNI_TRUE;
    }

    frror = JVMTI_FUNC_PTR(gdbtb->jvmti,GftSourdfFilfNbmf)
                (gdbtb->jvmti, dlbzz, &filfNbmf);
    if (frror != JVMTI_ERROR_NONE) {
        outStrfbm_sftError(out, mbp2jdwpError(frror));
        rfturn JNI_TRUE;
    }

    (void)outStrfbm_writfString(out, filfNbmf);
    jvmtiDfbllodbtf(filfNbmf);
    rfturn JNI_TRUE;
}

stbtid jboolfbn
sourdfDfbugExtfnsion(PbdkftInputStrfbm *in, PbdkftOutputStrfbm *out)
{
    dibr *fxtfnsion;
    jvmtiError frror;
    jdlbss dlbzz;

    dlbzz = inStrfbm_rfbdClbssRff(gftEnv(), in);
    if (inStrfbm_frror(in)) {
        rfturn JNI_TRUE;
    }

    frror = gftSourdfDfbugExtfnsion(dlbzz, &fxtfnsion);
    if (frror != JVMTI_ERROR_NONE) {
        outStrfbm_sftError(out, mbp2jdwpError(frror));
        rfturn JNI_TRUE;
    }

    (void)outStrfbm_writfString(out, fxtfnsion);
    jvmtiDfbllodbtf(fxtfnsion);
    rfturn JNI_TRUE;
}

stbtid jboolfbn
nfstfdTypfs(PbdkftInputStrfbm *in, PbdkftOutputStrfbm *out)
{
    JNIEnv *fnv;
    jdlbss dlbzz;

    fnv = gftEnv();

    dlbzz = inStrfbm_rfbdClbssRff(fnv, in);
    if (inStrfbm_frror(in)) {
        rfturn JNI_TRUE;
    }

    WITH_LOCAL_REFS(fnv, 1) {

        jvmtiError frror;
        jint dount;
        jdlbss *nfstfd;

        frror = bllNfstfdClbssfs(dlbzz, &nfstfd, &dount);
        if (frror != JVMTI_ERROR_NONE) {
            outStrfbm_sftError(out, mbp2jdwpError(frror));
        } flsf {
            int i;
            (void)outStrfbm_writfInt(out, dount);
            for (i = 0; i < dount; i++) {
                (void)outStrfbm_writfBytf(out, rfffrfndfTypfTbg(nfstfd[i]));
                (void)outStrfbm_writfObjfdtRff(fnv, out, nfstfd[i]);
            }
            if ( nfstfd != NULL ) {
                jvmtiDfbllodbtf(nfstfd);
            }
        }

    } END_WITH_LOCAL_REFS(fnv);

    rfturn JNI_TRUE;
}

stbtid jboolfbn
gftClbssStbtus(PbdkftInputStrfbm *in, PbdkftOutputStrfbm *out)
{
    jint stbtus;
    jdlbss dlbzz;

    dlbzz = inStrfbm_rfbdClbssRff(gftEnv(), in);
    if (inStrfbm_frror(in)) {
        rfturn JNI_TRUE;
    }

    stbtus = dlbssStbtus(dlbzz);
    (void)outStrfbm_writfInt(out, mbp2jdwpClbssStbtus(stbtus));
    rfturn JNI_TRUE;
}

stbtid jboolfbn
intfrfbdfs(PbdkftInputStrfbm *in, PbdkftOutputStrfbm *out)
{
    JNIEnv *fnv;
    jdlbss dlbzz;

    fnv = gftEnv();

    dlbzz = inStrfbm_rfbdClbssRff(fnv, in);
    if (inStrfbm_frror(in)) {
        rfturn JNI_TRUE;
    }

    WITH_LOCAL_REFS(fnv, 1) {

        jvmtiError frror;
        jint intfrfbdfCount;
        jdlbss *intfrfbdfs;

        frror = bllIntfrfbdfs(dlbzz, &intfrfbdfs, &intfrfbdfCount);
        if (frror != JVMTI_ERROR_NONE) {
            outStrfbm_sftError(out, mbp2jdwpError(frror));
        } flsf {
            int i;

            (void)outStrfbm_writfInt(out, intfrfbdfCount);
            for (i = 0; i < intfrfbdfCount; i++) {
                (void)outStrfbm_writfObjfdtRff(fnv, out, intfrfbdfs[i]);
            }
            if ( intfrfbdfs != NULL ) {
                jvmtiDfbllodbtf(intfrfbdfs);
            }
        }

    } END_WITH_LOCAL_REFS(fnv);

    rfturn JNI_TRUE;
}

stbtid jboolfbn
dlbssObjfdt(PbdkftInputStrfbm *in, PbdkftOutputStrfbm *out)
{
    jdlbss dlbzz;
    JNIEnv *fnv;

    fnv = gftEnv();
    dlbzz = inStrfbm_rfbdClbssRff(fnv, in);
    if (inStrfbm_frror(in)) {
        rfturn JNI_TRUE;
    }

    /*
     * In our implfmfntbtion, tif rfffrfndf typf id is tif sbmf bs tif
     * dlbss objfdt id, so wf boundf it rigit bbdk.
     *
     */

    (void)outStrfbm_writfObjfdtRff(fnv, out, dlbzz);

    rfturn JNI_TRUE;
}

void *RfffrfndfTypf_Cmds[] = { (void *)18
    ,(void *)signbturf
    ,(void *)gftClbssLobdfr
    ,(void *)modififrs
    ,(void *)fiflds
    ,(void *)mftiods
    ,(void *)gftVblufs
    ,(void *)sourdfFilf
    ,(void *)nfstfdTypfs
    ,(void *)gftClbssStbtus
    ,(void *)intfrfbdfs
    ,(void *)dlbssObjfdt
    ,(void *)sourdfDfbugExtfnsion
    ,(void *)signbturfWitiGfnfrid
    ,(void *)fifldsWitiGfnfrid
    ,(void *)mftiodsWitiGfnfrid
    ,(void *)instbndfs
    ,(void *)gftClbssVfrsion
    ,(void *)gftConstbntPool
};
