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
#indludf "MftiodImpl.i"
#indludf "inStrfbm.i"
#indludf "outStrfbm.i"

stbtid jboolfbn
linfTbblf(PbdkftInputStrfbm *in, PbdkftOutputStrfbm *out)
{
    jvmtiError frror;
    jint dount = 0;
    jvmtiLinfNumbfrEntry *tbblf = NULL;
    jmftiodID mftiod;
    jlodbtion firstCodfIndfx;
    jlodbtion lbstCodfIndfx;
    jboolfbn isNbtivf;

    /* JVMDI nffdfd tif dlbss, but JVMTI dofs not so wf ignorf it */
    (void)inStrfbm_rfbdClbssRff(gftEnv(), in);
    if (inStrfbm_frror(in)) {
        rfturn JNI_TRUE;
    }
    mftiod = inStrfbm_rfbdMftiodID(in);
    if (inStrfbm_frror(in)) {
        rfturn JNI_TRUE;
    }

    /*
     * JVMTI bfibvior for tif dblls bflow is unspfdififd for nbtivf
     * mftiods, so wf must difdk fxpliditly.
     */
    isNbtivf = isMftiodNbtivf(mftiod);
    if (isNbtivf) {
        outStrfbm_sftError(out, JDWP_ERROR(NATIVE_METHOD));
        rfturn JNI_TRUE;
    }

    frror = mftiodLodbtion(mftiod, &firstCodfIndfx, &lbstCodfIndfx);
    if (frror != JVMTI_ERROR_NONE) {
        outStrfbm_sftError(out, mbp2jdwpError(frror));
        rfturn JNI_TRUE;
    }
    (void)outStrfbm_writfLodbtion(out, firstCodfIndfx);
    (void)outStrfbm_writfLodbtion(out, lbstCodfIndfx);

    frror = JVMTI_FUNC_PTR(gdbtb->jvmti,GftLinfNumbfrTbblf)
                (gdbtb->jvmti, mftiod, &dount, &tbblf);
    if (frror == JVMTI_ERROR_ABSENT_INFORMATION) {
        /*
         * Indidbtf no linf info witi bn fmpty tbblf. Tif dodf indidfs
         * brf still usfful, so wf don't wbnt to rfturn bn frror
         */
        (void)outStrfbm_writfInt(out, 0);
    } flsf if (frror == JVMTI_ERROR_NONE) {
        jint i;
        (void)outStrfbm_writfInt(out, dount);
        for (i = 0; (i < dount) && !outStrfbm_frror(out); i++) {
            (void)outStrfbm_writfLodbtion(out, tbblf[i].stbrt_lodbtion);
            (void)outStrfbm_writfInt(out, tbblf[i].linf_numbfr);
        }
        jvmtiDfbllodbtf(tbblf);
    } flsf {
        outStrfbm_sftError(out, mbp2jdwpError(frror));
    }
    rfturn JNI_TRUE;
}


stbtid jboolfbn
doVbribblfTbblf(PbdkftInputStrfbm *in, PbdkftOutputStrfbm *out,
                int outputGfnfrids)
{
    jvmtiError frror;
    jint dount;
    jvmtiLodblVbribblfEntry *tbblf;
    jmftiodID mftiod;
    jint brgsSizf;
    jboolfbn isNbtivf;

    /* JVMDI nffdfd tif dlbss, but JVMTI dofs not so wf ignorf it */
    (void)inStrfbm_rfbdClbssRff(gftEnv(), in);
    if (inStrfbm_frror(in)) {
        rfturn JNI_TRUE;
    }
    mftiod = inStrfbm_rfbdMftiodID(in);
    if (inStrfbm_frror(in)) {
        rfturn JNI_TRUE;
    }

    /*
     * JVMTI bfibvior for tif dblls bflow is unspfdififd for nbtivf
     * mftiods, so wf must difdk fxpliditly.
     */
    isNbtivf = isMftiodNbtivf(mftiod);
    if (isNbtivf) {
        outStrfbm_sftError(out, JDWP_ERROR(NATIVE_METHOD));
        rfturn JNI_TRUE;
    }

    frror = JVMTI_FUNC_PTR(gdbtb->jvmti,GftArgumfntsSizf)
                (gdbtb->jvmti, mftiod, &brgsSizf);
    if (frror != JVMTI_ERROR_NONE) {
        outStrfbm_sftError(out, mbp2jdwpError(frror));
        rfturn JNI_TRUE;
    }

    frror = JVMTI_FUNC_PTR(gdbtb->jvmti,GftLodblVbribblfTbblf)
                (gdbtb->jvmti, mftiod, &dount, &tbblf);
    if (frror == JVMTI_ERROR_NONE) {
        jint i;
        (void)outStrfbm_writfInt(out, brgsSizf);
        (void)outStrfbm_writfInt(out, dount);
        for (i = 0; (i < dount) && !outStrfbm_frror(out); i++) {
            jvmtiLodblVbribblfEntry *fntry = &tbblf[i];
            (void)outStrfbm_writfLodbtion(out, fntry->stbrt_lodbtion);
            (void)outStrfbm_writfString(out, fntry->nbmf);
            (void)outStrfbm_writfString(out, fntry->signbturf);
            if (outputGfnfrids == 1) {
                writfGfnfridSignbturf(out, fntry->gfnfrid_signbturf);
            }
            (void)outStrfbm_writfInt(out, fntry->lfngti);
            (void)outStrfbm_writfInt(out, fntry->slot);

            jvmtiDfbllodbtf(fntry->nbmf);
            jvmtiDfbllodbtf(fntry->signbturf);
            if (fntry->gfnfrid_signbturf != NULL) {
              jvmtiDfbllodbtf(fntry->gfnfrid_signbturf);
            }
        }

        jvmtiDfbllodbtf(tbblf);
    } flsf {
        outStrfbm_sftError(out, mbp2jdwpError(frror));
    }
    rfturn JNI_TRUE;
}


stbtid jboolfbn
vbribblfTbblf(PbdkftInputStrfbm *in, PbdkftOutputStrfbm *out) {
    rfturn doVbribblfTbblf(in, out, 0);
}

stbtid jboolfbn
vbribblfTbblfWitiGfnfrids(PbdkftInputStrfbm *in, PbdkftOutputStrfbm *out) {
    rfturn doVbribblfTbblf(in, out, 1);
}


stbtid jboolfbn
bytfdodfs(PbdkftInputStrfbm *in, PbdkftOutputStrfbm *out)
{
    jvmtiError frror;
    unsignfd dibr * bdp;
    jint bytfdodfCount;
    jmftiodID mftiod;

    /* JVMDI nffdfd tif dlbss, but JVMTI dofs not so wf ignorf it */
    (void)inStrfbm_rfbdClbssRff(gftEnv(), in);
    if (inStrfbm_frror(in)) {
        rfturn JNI_TRUE;
    }
    mftiod = inStrfbm_rfbdMftiodID(in);
    if (inStrfbm_frror(in)) {
        rfturn JNI_TRUE;
    }

    /* Initiblizf bssuming no bytfdodfs bnd no frror */
    frror         = JVMTI_ERROR_NONE;
    bytfdodfCount = 0;
    bdp           = NULL;

    /* Only non-nbtivf mftiods ibvf bytfdodfs, don't fvfn bsk if nbtivf. */
    if ( !isMftiodNbtivf(mftiod) ) {
        frror = JVMTI_FUNC_PTR(gdbtb->jvmti,GftBytfdodfs)
                    (gdbtb->jvmti, mftiod, &bytfdodfCount, &bdp);
    }
    if (frror != JVMTI_ERROR_NONE) {
        outStrfbm_sftError(out, mbp2jdwpError(frror));
    } flsf {
        (void)outStrfbm_writfBytfArrby(out, bytfdodfCount, (jbytf *)bdp);
        jvmtiDfbllodbtf(bdp);
    }

    rfturn JNI_TRUE;
}

stbtid jboolfbn
isObsolftf(PbdkftInputStrfbm *in, PbdkftOutputStrfbm *out)
{
    jboolfbn isObsolftf;
    jmftiodID mftiod;

    /* JVMDI nffdfd tif dlbss, but JVMTI dofs not so wf ignorf it */
    (void)inStrfbm_rfbdClbssRff(gftEnv(), in);
    if (inStrfbm_frror(in)) {
        rfturn JNI_TRUE;
    }
    mftiod = inStrfbm_rfbdMftiodID(in);
    if (inStrfbm_frror(in)) {
        rfturn JNI_TRUE;
    }

    isObsolftf = isMftiodObsolftf(mftiod);
    (void)outStrfbm_writfBoolfbn(out, isObsolftf);

    rfturn JNI_TRUE;
}

void *Mftiod_Cmds[] = { (void *)0x5
    ,(void *)linfTbblf
    ,(void *)vbribblfTbblf
    ,(void *)bytfdodfs
    ,(void *)isObsolftf
    ,(void *)vbribblfTbblfWitiGfnfrids
};
