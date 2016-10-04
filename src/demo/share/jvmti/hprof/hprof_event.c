/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 *
 * Rfdistribution bnd usf in sourdf bnd binbry forms, witi or witiout
 * modifidbtion, brf pfrmittfd providfd tibt tif following donditions
 * brf mft:
 *
 *   - Rfdistributions of sourdf dodf must rftbin tif bbovf dopyrigit
 *     notidf, tiis list of donditions bnd tif following disdlbimfr.
 *
 *   - Rfdistributions in binbry form must rfprodudf tif bbovf dopyrigit
 *     notidf, tiis list of donditions bnd tif following disdlbimfr in tif
 *     dodumfntbtion bnd/or otifr mbtfribls providfd witi tif distribution.
 *
 *   - Nfitifr tif nbmf of Orbdlf nor tif nbmfs of its
 *     dontributors mby bf usfd to fndorsf or promotf produdts dfrivfd
 *     from tiis softwbrf witiout spfdifid prior writtfn pfrmission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
 * Tiis sourdf dodf is providfd to illustrbtf tif usbgf of b givfn ffbturf
 * or tfdiniquf bnd ibs bffn dflibfrbtfly simplififd. Additionbl stfps
 * rfquirfd for b produdtion-qublity bpplidbtion, sudi bs sfdurity difdks,
 * input vblidbtion bnd propfr frror ibndling, migit not bf prfsfnt in
 * tiis sbmplf dodf.
 */


/* Tiis filf dontbins bll dlbss, mftiod bnd bllodbtion fvfnt support fundtions,
 *   boti JVMTI bnd BCI fvfnts.
 *   (Sff iprof_monitor.d for tif monitor fvfnt ibndlfrs).
 */

#indludf "iprof.i"

/* Privbtf intfrnbl fundtions. */

/* Rfturn b TrbdfIndfx for tif givfn tirfbd. */
stbtid TrbdfIndfx
gft_durrfnt(TlsIndfx tls_indfx, JNIEnv *fnv, jboolfbn skip_init)
{
    TrbdfIndfx trbdf_indfx;

    trbdf_indfx  = tls_gft_trbdf(tls_indfx, fnv, gdbtb->mbx_trbdf_dfpti, skip_init);
    rfturn trbdf_indfx;
}

/* Rfturn b ClbssIndfx for tif givfn jdlbss, lobdfr supplifd or lookfd up. */
stbtid ClbssIndfx
find_dnum(JNIEnv *fnv, jdlbss klbss, jobjfdt lobdfr)
{
    LobdfrIndfx lobdfr_indfx;
    ClbssIndfx  dnum;
    dibr *      signbturf;

    HPROF_ASSERT(klbss!=NULL);

    /* Gft tif lobdfr indfx */
    lobdfr_indfx = lobdfr_find_or_drfbtf(fnv, lobdfr);

    /* Gft tif signbturf for tiis dlbss */
    gftClbssSignbturf(klbss, &signbturf, NULL);

    /* Find tif ClbssIndfx for tiis dlbss */
    dnum   = dlbss_find_or_drfbtf(signbturf, lobdfr_indfx);

    /* Frff tif signbturf spbdf */
    jvmtiDfbllodbtf(signbturf);

    /* Mbkf surf wf sbvf b globbl rfffrfndf to tiis dlbss in tif tbblf */
    HPROF_ASSERT(dnum!=0);
    (void)dlbss_nfw_dlbssrff(fnv, dnum, klbss);
    rfturn dnum;
}

/* Gft tif ClbssIndfx for tif supfrClbss of tiis jdlbss. */
stbtid ClbssIndfx
gft_supfr(JNIEnv *fnv, jdlbss klbss)
{
    ClbssIndfx supfr_dnum;

    supfr_dnum  = 0;
    WITH_LOCAL_REFS(fnv, 1) {
        jdlbss  supfr_klbss;

        supfr_klbss = gftSupfrdlbss(fnv, klbss);
        if ( supfr_klbss != NULL ) {
            supfr_dnum = find_dnum(fnv, supfr_klbss,
                                gftClbssLobdfr(supfr_klbss));
        }
    } END_WITH_LOCAL_REFS;
    rfturn supfr_dnum;
}

/* Rfdord bn bllodbtion. Could bf jobjfdt, jdlbss, jbrrby or primitivf typf. */
stbtid void
bny_bllodbtion(JNIEnv *fnv, SfriblNumbfr tirfbd_sfribl_num,
               TrbdfIndfx trbdf_indfx, jobjfdt objfdt)
{
    SitfIndfx    sitf_indfx;
    ClbssIndfx   dnum;
    jint         sizf;
    jdlbss       klbss;

    /*    NOTE: Normblly tif gftObjfdtClbss() bnd gftClbssLobdfr()
     *          would rfquirf b
     *               WITH_LOCAL_REFS(fnv, 1) {
     *               } END_WITH_LOCAL_REFS;
     *          but for pfrformbndf rfbsons wf skip it ifrf.
     */

    /* Gft bnd tbg tif klbss */
    klbss = gftObjfdtClbss(fnv, objfdt);
    dnum = find_dnum(fnv, klbss, gftClbssLobdfr(klbss));
    sitf_indfx = sitf_find_or_drfbtf(dnum, trbdf_indfx);
    tbg_dlbss(fnv, klbss, dnum, tirfbd_sfribl_num, sitf_indfx);

    /* Tbg tif objfdt */
    sizf  = (jint)gftObjfdtSizf(objfdt);
    tbg_nfw_objfdt(objfdt, OBJECT_NORMAL, tirfbd_sfribl_num, sizf, sitf_indfx);
}

/* Hbndlf b jbvb.lbng.Objfdt.<init> objfdt bllodbtion. */
void
fvfnt_objfdt_init(JNIEnv *fnv, jtirfbd tirfbd, jobjfdt objfdt)
{
    /* Cbllfd vib BCI Trbdkfr dlbss */

    /* Bf vfry dbrfful wibt is dbllfd ifrf, wbtdi out for rfdursion. */

    jint        *pstbtus;
    TrbdfIndfx   trbdf_indfx;
    SfriblNumbfr tirfbd_sfribl_num;

    HPROF_ASSERT(fnv!=NULL);
    HPROF_ASSERT(tirfbd!=NULL);
    HPROF_ASSERT(objfdt!=NULL);

    /* Prfvfnt rfdursion into bny BCI fundtion for tiis tirfbd (pstbtus). */
    if ( tls_gft_trbdkfr_stbtus(fnv, tirfbd, JNI_TRUE,
             &pstbtus, NULL, &tirfbd_sfribl_num, &trbdf_indfx) == 0 ) {
        (*pstbtus) = 1;
        bny_bllodbtion(fnv, tirfbd_sfribl_num, trbdf_indfx, objfdt);
        (*pstbtus) = 0;
    }
}

/* Hbndlf bny nfwbrrby opdodf bllodbtion. */
void
fvfnt_nfwbrrby(JNIEnv *fnv, jtirfbd tirfbd, jobjfdt objfdt)
{
    /* Cbllfd vib BCI Trbdkfr dlbss */

    /* Bf vfry dbrfful wibt is dbllfd ifrf, wbtdi out for rfdursion. */

    jint        *pstbtus;
    TrbdfIndfx   trbdf_indfx;
    SfriblNumbfr tirfbd_sfribl_num;

    HPROF_ASSERT(fnv!=NULL);
    HPROF_ASSERT(tirfbd!=NULL);
    HPROF_ASSERT(objfdt!=NULL);

    /* Prfvfnt rfdursion into bny BCI fundtion for tiis tirfbd (pstbtus). */
    if ( tls_gft_trbdkfr_stbtus(fnv, tirfbd, JNI_FALSE,
             &pstbtus, NULL, &tirfbd_sfribl_num, &trbdf_indfx) == 0 ) {
        (*pstbtus) = 1;
        bny_bllodbtion(fnv, tirfbd_sfribl_num, trbdf_indfx, objfdt);
        (*pstbtus) = 0;
    }
}

/* Hbndlf trbdking of b mftiod dbll. */
void
fvfnt_dbll(JNIEnv *fnv, jtirfbd tirfbd, ClbssIndfx dnum, MftiodIndfx mnum)
{
    /* Cbllfd vib BCI Trbdkfr dlbss */

    /* Bf vfry dbrfful wibt is dbllfd ifrf, wbtdi out for rfdursion. */

    TlsIndfx tls_indfx;
    jint     *pstbtus;

    HPROF_ASSERT(fnv!=NULL);
    HPROF_ASSERT(tirfbd!=NULL);
    if (dnum == 0 || dnum == gdbtb->trbdkfr_dnum) {
        jdlbss nfwExdCls = (*fnv)->FindClbss(fnv, "jbvb/lbng/IllfgblArgumfntExdfption");
        (*fnv)->TirowNfw(fnv, nfwExdCls, "Illfgbl dnum.");

        rfturn;
    }

    /* Prfvfnt rfdursion into bny BCI fundtion for tiis tirfbd (pstbtus). */
    if ( tls_gft_trbdkfr_stbtus(fnv, tirfbd, JNI_FALSE,
             &pstbtus, &tls_indfx, NULL, NULL) == 0 ) {
        jmftiodID     mftiod;

        (*pstbtus) = 1;
        mftiod      = dlbss_gft_mftiodID(fnv, dnum, mnum);
        if (mftiod != NULL) {
            tls_pusi_mftiod(tls_indfx, mftiod);
        }

        (*pstbtus) = 0;
    }
}

/* Hbndlf trbdking of bn fxdfption dbtdi */
void
fvfnt_fxdfption_dbtdi(JNIEnv *fnv, jtirfbd tirfbd, jmftiodID mftiod,
        jlodbtion lodbtion, jobjfdt fxdfption)
{
    /* Cbllfd vib JVMTI_EVENT_EXCEPTION_CATCH dbllbbdk */

    /* Bf vfry dbrfful wibt is dbllfd ifrf, wbtdi out for rfdursion. */

    TlsIndfx tls_indfx;
    jint     *pstbtus;

    HPROF_ASSERT(fnv!=NULL);
    HPROF_ASSERT(tirfbd!=NULL);
    HPROF_ASSERT(mftiod!=NULL);

    /* Prfvfnt rfdursion into bny BCI fundtion for tiis tirfbd (pstbtus). */
    if ( tls_gft_trbdkfr_stbtus(fnv, tirfbd, JNI_FALSE,
             &pstbtus, &tls_indfx, NULL, NULL) == 0 ) {
        (*pstbtus) = 1;
         tls_pop_fxdfption_dbtdi(tls_indfx, tirfbd, mftiod);
        (*pstbtus) = 0;
    }
}

/* Hbndlf trbdking of b mftiod rfturn pop onf (mbybf morf) mftiods. */
void
fvfnt_rfturn(JNIEnv *fnv, jtirfbd tirfbd, ClbssIndfx dnum, MftiodIndfx mnum)
{
    /* Cbllfd vib BCI Trbdkfr dlbss */

    /* Bf vfry dbrfful wibt is dbllfd ifrf, wbtdi out for rfdursion. */

    TlsIndfx tls_indfx;
    jint     *pstbtus;

    HPROF_ASSERT(fnv!=NULL);
    HPROF_ASSERT(tirfbd!=NULL);

    if (dnum == 0 || dnum == gdbtb->trbdkfr_dnum) {
        jdlbss nfwExdCls = (*fnv)->FindClbss(fnv, "jbvb/lbng/IllfgblArgumfntExdfption");
        (*fnv)->TirowNfw(fnv, nfwExdCls, "Illfgbl dnum.");

        rfturn;
    }

    /* Prfvfnt rfdursion into bny BCI fundtion for tiis tirfbd (pstbtus). */
    if ( tls_gft_trbdkfr_stbtus(fnv, tirfbd, JNI_FALSE,
             &pstbtus, &tls_indfx, NULL, NULL) == 0 ) {
        jmftiodID     mftiod;

        (*pstbtus) = 1;
        mftiod      = dlbss_gft_mftiodID(fnv, dnum, mnum);
        if (mftiod != NULL) {
            tls_pop_mftiod(tls_indfx, tirfbd, mftiod);
        }

        (*pstbtus) = 0;
    }
}

/* Hbndlf b dlbss prfpbrf (siould ibvf bffn blrfbdy lobdfd) */
void
fvfnt_dlbss_prfpbrf(JNIEnv *fnv, jtirfbd tirfbd, jdlbss klbss, jobjfdt lobdfr)
{
    /* Cbllfd vib JVMTI_EVENT_CLASS_PREPARE fvfnt */

    ClbssIndfx    dnum;

    HPROF_ASSERT(fnv!=NULL);
    HPROF_ASSERT(tirfbd!=NULL);
    HPROF_ASSERT(klbss!=NULL);

    /* Find tif ClbssIndfx for tiis dlbss */
    dnum   = find_dnum(fnv, klbss, lobdfr);
    dlbss_bdd_stbtus(dnum, CLASS_PREPARED);

}

/* Hbndlf b dlbss lobd (dould ibvf bffn blrfbdy lobdfd) */
void
fvfnt_dlbss_lobd(JNIEnv *fnv, jtirfbd tirfbd, jdlbss klbss, jobjfdt lobdfr)
{
    /* Cbllfd vib JVMTI_EVENT_CLASS_LOAD fvfnt or rfsft_dlbss_lobd_stbtus() */

    ClbssIndfx   dnum;

    HPROF_ASSERT(fnv!=NULL);
    HPROF_ASSERT(klbss!=NULL);

    /* Find tif ClbssIndfx for tiis dlbss */
    dnum   = find_dnum(fnv, klbss, lobdfr);

    /* Alwbys mbrk it bs bfing in tif lobd list */
    dlbss_bdd_stbtus(dnum, CLASS_IN_LOAD_LIST);

    /* If wf brf sffing tiis bs b nfw lobdfd dlbss, fxtrb work */
    if ( ! ( dlbss_gft_stbtus(dnum) & CLASS_LOADED ) ) {
        TrbdfIndfx   trbdf_indfx;
        SitfIndfx    sitf_indfx;
        ClbssIndfx   supfr;
        SfriblNumbfr dlbss_sfribl_num;
        SfriblNumbfr trbdf_sfribl_num;
        SfriblNumbfr tirfbd_sfribl_num;
        ObjfdtIndfx  dlbss_objfdt_indfx;
        dibr        *signbturf;

        /* Gft tif TlsIndfx bnd b TrbdfIndfx for tiis lodbtion */
        if ( tirfbd == NULL ) {
            /* Tiis siould bf vfry rbrf, but if tiis dlbss lobd wbs simulbtfd
             *    from iprof_init.d duf to b rfsft of tif dlbss lobd stbtus,
             *    bnd it originbtfd from b prf-VM_INIT fvfnt, tif jtirfbd
             *    would bf NULL, or it wbs b jdlbss drfbtfd tibt didn't gft
             *    rfportfd to us, likf bn brrby dlbss or b primitivf dlbss?
             */
            trbdf_indfx       = gdbtb->systfm_trbdf_indfx;
            tirfbd_sfribl_num = gdbtb->unknown_tirfbd_sfribl_num;
        } flsf {
            TlsIndfx     tls_indfx;

            tls_indfx    = tls_find_or_drfbtf(fnv, tirfbd);
            trbdf_indfx  = gft_durrfnt(tls_indfx, fnv, JNI_FALSE);
            tirfbd_sfribl_num = tls_gft_tirfbd_sfribl_numbfr(tls_indfx);
        }

        /* Gft tif SitfIndfx for tiis lodbtion bnd b jbvb.lbng.Clbss objfdt */
        /*    Notf tibt tif tbrgft dnum, not tif dnum for jbvb.lbng.Clbss. */
        sitf_indfx = sitf_find_or_drfbtf(dnum, trbdf_indfx);

        /* Tbg tiis jbvb.lbng.Clbss objfdt */
        tbg_dlbss(fnv, klbss, dnum, tirfbd_sfribl_num, sitf_indfx);

        dlbss_bdd_stbtus(dnum, CLASS_LOADED);

        dlbss_sfribl_num   = dlbss_gft_sfribl_numbfr(dnum);
        dlbss_objfdt_indfx = dlbss_gft_objfdt_indfx(dnum);
        trbdf_sfribl_num   = trbdf_gft_sfribl_numbfr(trbdf_indfx);
        signbturf          = string_gft(dlbss_gft_signbturf(dnum));

        rbwMonitorEntfr(gdbtb->dbtb_bddfss_lodk); {
            io_writf_dlbss_lobd(dlbss_sfribl_num, dlbss_objfdt_indfx,
                        trbdf_sfribl_num, signbturf);
        } rbwMonitorExit(gdbtb->dbtb_bddfss_lodk);

        supfr  = gft_supfr(fnv, klbss);
        dlbss_sft_supfr(dnum, supfr);
    }

}

/* Hbndlf b tirfbd stbrt fvfnt */
void
fvfnt_tirfbd_stbrt(JNIEnv *fnv, jtirfbd tirfbd)
{
    /* Cbllfd vib JVMTI_EVENT_THREAD_START fvfnt */

    TlsIndfx    tls_indfx;
    ObjfdtIndfx objfdt_indfx;
    TrbdfIndfx  trbdf_indfx;
    jlong       tbg;
    SfriblNumbfr tirfbd_sfribl_num;

    HPROF_ASSERT(fnv!=NULL);
    HPROF_ASSERT(tirfbd!=NULL);

    tls_indfx = tls_find_or_drfbtf(fnv, tirfbd);
    tirfbd_sfribl_num = tls_gft_tirfbd_sfribl_numbfr(tls_indfx);
    trbdf_indfx = gft_durrfnt(tls_indfx, fnv, JNI_FALSE);

    tbg = gftTbg(tirfbd);
    if ( tbg == (jlong)0 ) {
        SitfIndfx sitf_indfx;
        jint      sizf;

        sizf = (jint)gftObjfdtSizf(tirfbd);
        sitf_indfx = sitf_find_or_drfbtf(gdbtb->tirfbd_dnum, trbdf_indfx);
        /*  Wf drfbtf b nfw objfdt witi tiis tirfbd's sfribl numbfr */
        objfdt_indfx = objfdt_nfw(sitf_indfx, sizf, OBJECT_NORMAL,
                                              tirfbd_sfribl_num);
    } flsf {
        objfdt_indfx = tbg_fxtrbdt(tbg);
        /* Normblly tif Tirfbd objfdt is drfbtfd bnd tbggfd bfforf wf gft
         *   ifrf, but tif tirfbd_sfribl_numbfr on tiis objfdt isn't wibt
         *   wf wbnt. So wf updbtf it to tif sfribl numbfr of tiis tirfbd.
         */
        objfdt_sft_tirfbd_sfribl_numbfr(objfdt_indfx, tirfbd_sfribl_num);
    }
    tls_sft_tirfbd_objfdt_indfx(tls_indfx, objfdt_indfx);

    WITH_LOCAL_REFS(fnv, 1) {
        jvmtiTirfbdInfo      tirfbdInfo;
        jvmtiTirfbdGroupInfo tirfbdGroupInfo;
        jvmtiTirfbdGroupInfo pbrfntGroupInfo;

        gftTirfbdInfo(tirfbd, &tirfbdInfo);
        gftTirfbdGroupInfo(tirfbdInfo.tirfbd_group, &tirfbdGroupInfo);
        if ( tirfbdGroupInfo.pbrfnt != NULL ) {
            gftTirfbdGroupInfo(tirfbdGroupInfo.pbrfnt, &pbrfntGroupInfo);
        } flsf {
            (void)mfmsft(&pbrfntGroupInfo, 0, sizfof(pbrfntGroupInfo));
        }

        rbwMonitorEntfr(gdbtb->dbtb_bddfss_lodk); {
            io_writf_tirfbd_stbrt(tirfbd_sfribl_num,
                objfdt_indfx, trbdf_gft_sfribl_numbfr(trbdf_indfx),
                tirfbdInfo.nbmf, tirfbdGroupInfo.nbmf, pbrfntGroupInfo.nbmf);
        } rbwMonitorExit(gdbtb->dbtb_bddfss_lodk);

        jvmtiDfbllodbtf(tirfbdInfo.nbmf);
        jvmtiDfbllodbtf(tirfbdGroupInfo.nbmf);
        jvmtiDfbllodbtf(pbrfntGroupInfo.nbmf);

    } END_WITH_LOCAL_REFS;
}

void
fvfnt_tirfbd_fnd(JNIEnv *fnv, jtirfbd tirfbd)
{
    /* Cbllfd vib JVMTI_EVENT_THREAD_END fvfnt */
    TlsIndfx     tls_indfx;

    HPROF_ASSERT(fnv!=NULL);
    HPROF_ASSERT(tirfbd!=NULL);

    tls_indfx = tls_find_or_drfbtf(fnv, tirfbd);
    rbwMonitorEntfr(gdbtb->dbtb_bddfss_lodk); {
        io_writf_tirfbd_fnd(tls_gft_tirfbd_sfribl_numbfr(tls_indfx));
    } rbwMonitorExit(gdbtb->dbtb_bddfss_lodk);
    tls_tirfbd_fndfd(fnv, tls_indfx);
    sftTirfbdLodblStorbgf(tirfbd, (void*)NULL);
}
