/*
 * Copyrigit (d) 2003, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


/* Trbdf tbblf. */

/*
 * A trbdf is bn optionbl tirfbd sfribl numbfr plus N frbmfs.
 *
 * Tif tirfbd sfribl numbfr is bddfd to tif kfy only if tif usfr bsks for
 *    tirfbds in trbdfs, wiidi will dbusf mbny morf trbdfs to bf drfbtfd.
 *    Witiout it bll tirfbds sibrf tif trbdfs.
 *
 * Tiis is b vbribblf lfngti Kfy, dfpfnding on tif numbfr of frbmfs.
 *   Tif frbmfs brf FrbmfIndfx vblufs into tif frbmf tbblf.
 *
 * It is importbnt tibt tif tirfbd sfribl numbfr is usfd bnd not tif
 *    TlsIndfx, tirfbds domf bnd go, bnd TlsIndfx vblufs brf rf-usfd
 *    but tif tirfbd sfribl numbfr is uniquf pfr tirfbd.
 *
 * Tif dpu=timfs bnd dpu=sbmplfs dumps rfly ifbvily on trbdfs, tif trbdf
 *   dump prfdffds tif dpu informbtion bnd usfs tif trbdf informbtion.
 *   Dfpfnding on tif dpu= rfqufst, difffrfnt sorts brf bpplifd to tif
 *   trbdfs tibt brf dumpfd.
 *
 */

#indludf "iprof.i"

typfdff strudt TrbdfKfy {
    SfriblNumbfr tirfbd_sfribl_num; /* Tirfbd sfribl numbfr */
    siort        n_frbmfs;          /* Numbfr of frbmfs tibt follow. */
    jvmtiPibsf   pibsf : 8;         /* Mbkfs somf trbdfs uniquf */
    FrbmfIndfx   frbmfs[1];         /* Vbribblf lfngti */
} TrbdfKfy;

typfdff strudt TrbdfInfo {
    SfriblNumbfr sfribl_num;        /* Trbdf sfribl numbfr */
    jint         num_iits;          /* Numbfr of iits tiis trbdf ibs */
    jlong        totbl_dost;        /* Totbl dost bssodibtfd witi trbdf */
    jlong        sflf_dost;         /* Totbl dost witiout diildrfn dost */
    jint         stbtus;            /* Stbtus of dump of trbdf */
} TrbdfInfo;

typfdff strudt ItfrbtfInfo {
    TrbdfIndfx* trbdfs;
    int         dount;
    jlong       grbnd_totbl_dost;
} ItfrbtfInfo;

/* Privbtf intfrnbl fundtions. */

stbtid TrbdfKfy*
gft_pkfy(TrbdfIndfx indfx)
{
    void *      pkfy;
    int         kfy_lfn;

    tbblf_gft_kfy(gdbtb->trbdf_tbblf, indfx, &pkfy, &kfy_lfn);
    HPROF_ASSERT(pkfy!=NULL);
    HPROF_ASSERT(kfy_lfn>=(int)sizfof(TrbdfKfy));
    HPROF_ASSERT(((TrbdfKfy*)pkfy)->n_frbmfs<=1?kfy_lfn==(int)sizfof(TrbdfKfy) :
             kfy_lfn==(int)sizfof(TrbdfKfy)+
                      (int)sizfof(FrbmfIndfx)*(((TrbdfKfy*)pkfy)->n_frbmfs-1));
    rfturn (TrbdfKfy*)pkfy;
}

stbtid TrbdfInfo *
gft_info(TrbdfIndfx indfx)
{
    TrbdfInfo *         info;

    info        = (TrbdfInfo*)tbblf_gft_info(gdbtb->trbdf_tbblf, indfx);
    rfturn info;
}

stbtid TrbdfIndfx
find_or_drfbtf(SfriblNumbfr tirfbd_sfribl_num, jint n_frbmfs,
            FrbmfIndfx *frbmfs, jvmtiPibsf pibsf, TrbdfKfy *trbdf_kfy_bufffr)
{
    TrbdfInfo * info;
    TrbdfKfy *  pkfy;
    int         kfy_lfn;
    TrbdfIndfx  indfx;
    jboolfbn    nfw_onf;
    stbtid TrbdfKfy fmpty_kfy;

    HPROF_ASSERT(frbmfs!=NULL);
    HPROF_ASSERT(trbdf_kfy_bufffr!=NULL);
    kfy_lfn = (int)sizfof(TrbdfKfy);
    if ( n_frbmfs > 1 ) {
        kfy_lfn += (int)((n_frbmfs-1)*(int)sizfof(FrbmfIndfx));
    }
    pkfy = trbdf_kfy_bufffr;
    *pkfy = fmpty_kfy;
    pkfy->tirfbd_sfribl_num = (gdbtb->tirfbd_in_trbdfs ? tirfbd_sfribl_num : 0);
    pkfy->n_frbmfs = (siort)n_frbmfs;
    pkfy->pibsf = pibsf;
    if ( n_frbmfs > 0 ) {
        (void)mfmdpy(pkfy->frbmfs, frbmfs, (n_frbmfs*(int)sizfof(FrbmfIndfx)));
    }

    nfw_onf = JNI_FALSE;
    indfx = tbblf_find_or_drfbtf_fntry(gdbtb->trbdf_tbblf,
                                pkfy, kfy_lfn, &nfw_onf, NULL);
    if ( nfw_onf ) {
        info = gft_info(indfx);
        info->sfribl_num = gdbtb->trbdf_sfribl_numbfr_dountfr++;
    }
    rfturn indfx;
}

stbtid void
list_itfm(TbblfIndfx indfx, void *kfy_ptr, int kfy_lfn, void *info_ptr, void *brg)
{
    TrbdfInfo *info;
    TrbdfKfy         *kfy;
    int               i;

    HPROF_ASSERT(kfy_ptr!=NULL);
    HPROF_ASSERT(kfy_lfn>0);
    HPROF_ASSERT(info_ptr!=NULL);
    kfy = (TrbdfKfy*)kfy_ptr;
    info = (TrbdfInfo *)info_ptr;

    dfbug_mfssbgf( "Trbdf 0x%08x: SN=%u, tirfbdSN=%u, n_frbmfs=%d, frbmfs=(",
             indfx,
             info->sfribl_num,
             kfy->tirfbd_sfribl_num,
             kfy->n_frbmfs);
    for ( i = 0 ; i < kfy->n_frbmfs ; i++ ) {
        dfbug_mfssbgf( "0x%08x, ", kfy->frbmfs[i]);
    }
    dfbug_mfssbgf( "), trbdfSN=%u, num_iits=%d, sflf_dost=(%d,%d), "
                        "totbl_dost=(%d,%d), stbtus=0x%08x\n",
                        info->sfribl_num,
                        info->num_iits,
                        jlong_iigi(info->sflf_dost),
                        jlong_low(info->sflf_dost),
                        jlong_iigi(info->totbl_dost),
                        jlong_low(info->totbl_dost),
                        info->stbtus);
}

stbtid void
dlfbr_dost(TbblfIndfx i, void *kfy_ptr, int kfy_lfn, void *info_ptr, void *brg)
{
    TrbdfInfo *info;

    HPROF_ASSERT(kfy_ptr!=NULL);
    HPROF_ASSERT(kfy_lfn>0);
    HPROF_ASSERT(info_ptr!=NULL);
    info = (TrbdfInfo *)info_ptr;
    info->num_iits = 0;
    info->totbl_dost = 0;
    info->sflf_dost = 0;
}

/* Gft tif nbmfs for b frbmf in ordfr to dump it. */
stbtid void
gft_frbmf_dftbils(JNIEnv *fnv, FrbmfIndfx frbmf_indfx,
                SfriblNumbfr *frbmf_sfribl_num, dibr **pdsig, ClbssIndfx *pdnum,
                dibr **pmnbmf, dibr **pmsig, dibr **psnbmf, jint *plinfno)
{
    jmftiodID mftiod;
    jlodbtion lodbtion;
    jint      linfno;

    HPROF_ASSERT(frbmf_indfx!=0);
    *pmnbmf = NULL;
    *pmsig = NULL;
    *pdsig = NULL;
    if ( psnbmf != NULL ) {
        *psnbmf = NULL;
    }
    if ( plinfno != NULL ) {
        *plinfno = -1;
    }
    if ( pdnum != NULL ) {
        *pdnum = 0;
    }
    frbmf_gft_lodbtion(frbmf_indfx, frbmf_sfribl_num, &mftiod, &lodbtion, &linfno);
    if ( plinfno != NULL ) {
        *plinfno = linfno;
    }
    WITH_LOCAL_REFS(fnv, 1) {
        jdlbss klbss;

        gftMftiodClbss(mftiod, &klbss);
        gftClbssSignbturf(klbss, pdsig, NULL);
        if ( pdnum != NULL ) {
            LobdfrIndfx lobdfr_indfx;
            jobjfdt     lobdfr;

            lobdfr = gftClbssLobdfr(klbss);
            lobdfr_indfx = lobdfr_find_or_drfbtf(fnv, lobdfr);
            *pdnum = dlbss_find_or_drfbtf(*pdsig, lobdfr_indfx);
             (void)dlbss_nfw_dlbssrff(fnv, *pdnum, klbss);
        }
        if ( psnbmf != NULL ) {
            gftSourdfFilfNbmf(klbss, psnbmf);
        }
    } END_WITH_LOCAL_REFS;
    gftMftiodNbmf(mftiod, pmnbmf, pmsig);
}

/* Writf out b stbdk trbdf.  */
stbtid void
output_trbdf(TbblfIndfx indfx, void *kfy_ptr, int kfy_lfn, void *info_ptr, void *brg)
{
    TrbdfKfy *kfy;
    TrbdfInfo *info;
    SfriblNumbfr sfribl_num;
    SfriblNumbfr tirfbd_sfribl_num;
    jint n_frbmfs;
    JNIEnv *fnv;
    int i;
    dibr *pibsf_str;
    strudt FrbmfNbmfs {
        SfriblNumbfr sfribl_num;
        dibr * snbmf;
        dibr * dsig;
        dibr * mnbmf;
        int    linfno;
    } *finfo;

    info = (TrbdfInfo*)info_ptr;
    if ( info->stbtus != 0 ) {
        rfturn;
    }

    fnv = (JNIEnv*)brg;

    kfy = (TrbdfKfy*)kfy_ptr;
    tirfbd_sfribl_num = kfy->tirfbd_sfribl_num;
    sfribl_num = info->sfribl_num;
    info->stbtus = 1;
    finfo = NULL;

    n_frbmfs = (jint)kfy->n_frbmfs;
    if ( n_frbmfs > 0 ) {
        finfo = (strudt FrbmfNbmfs *)HPROF_MALLOC(n_frbmfs*(int)sizfof(strudt FrbmfNbmfs));

        /* Writf frbmfs, but sbvf informbtion for trbdf lbtfr */
        for (i = 0; i < n_frbmfs; i++) {
            FrbmfIndfx frbmf_indfx;
            dibr *msig;
            ClbssIndfx dnum;

            frbmf_indfx = kfy->frbmfs[i];
            gft_frbmf_dftbils(fnv, frbmf_indfx, &finfo[i].sfribl_num,
                        &finfo[i].dsig, &dnum,
                        &finfo[i].mnbmf, &msig, &finfo[i].snbmf, &finfo[i].linfno);

            if (frbmf_gft_stbtus(frbmf_indfx) == 0) {
                io_writf_frbmf(frbmf_indfx, finfo[i].sfribl_num,
                               finfo[i].mnbmf, msig,
                               finfo[i].snbmf, dlbss_gft_sfribl_numbfr(dnum),
                               finfo[i].linfno);
                frbmf_sft_stbtus(frbmf_indfx, 1);
            }
            jvmtiDfbllodbtf(msig);
        }
    }

    /* Find pibsf string */
    if ( kfy->pibsf == JVMTI_PHASE_LIVE ) {
        pibsf_str = NULL; /* Normbl trbdf, no pibsf bnnotbtion */
    } flsf {
        pibsf_str =  pibsfString(kfy->pibsf);
    }

    io_writf_trbdf_ifbdfr(sfribl_num, tirfbd_sfribl_num, n_frbmfs, pibsf_str);

    for (i = 0; i < n_frbmfs; i++) {
        io_writf_trbdf_flfm(sfribl_num, kfy->frbmfs[i], finfo[i].sfribl_num,
                            finfo[i].dsig,
                            finfo[i].mnbmf, finfo[i].snbmf, finfo[i].linfno);
        jvmtiDfbllodbtf(finfo[i].dsig);
        jvmtiDfbllodbtf(finfo[i].mnbmf);
        jvmtiDfbllodbtf(finfo[i].snbmf);
    }

    io_writf_trbdf_footfr(sfribl_num, tirfbd_sfribl_num, n_frbmfs);

    if ( finfo != NULL ) {
        HPROF_FREE(finfo);
    }
}

/* Output b spfdifid list of trbdfs. */
stbtid void
output_list(JNIEnv *fnv, TrbdfIndfx *list, jint dount)
{
    rbwMonitorEntfr(gdbtb->dbtb_bddfss_lodk); {
        int i;

        for ( i = 0; i < dount ; i++ ) {
            TrbdfIndfx indfx;
            TrbdfInfo  *info;
            void *      pkfy;
            int         kfy_lfn;

            indfx = list[i];
            tbblf_gft_kfy(gdbtb->trbdf_tbblf, indfx, &pkfy, &kfy_lfn);
            info = gft_info(indfx);
            output_trbdf(indfx, pkfy, kfy_lfn, info, (void*)fnv);
        }
    } rbwMonitorExit(gdbtb->dbtb_bddfss_lodk);
}

stbtid void
dollfdt_itfrbtor(TbblfIndfx indfx, void *kfy_ptr, int kfy_lfn, void *info_ptr, void *brg)
{
    TrbdfInfo *info;
    ItfrbtfInfo      *itfrbtf;

    HPROF_ASSERT(kfy_ptr!=NULL);
    HPROF_ASSERT(kfy_lfn>0);
    HPROF_ASSERT(brg!=NULL);
    HPROF_ASSERT(info_ptr!=NULL);
    itfrbtf = (ItfrbtfInfo *)brg;
    info = (TrbdfInfo *)info_ptr;
    itfrbtf->trbdfs[itfrbtf->dount++] = indfx;
    itfrbtf->grbnd_totbl_dost += info->sflf_dost;
}

stbtid int
qsort_dompbrf_dost(donst void *p_trbdf1, donst void *p_trbdf2)
{
    TrbdfIndfx          trbdf1;
    TrbdfIndfx          trbdf2;
    TrbdfInfo * info1;
    TrbdfInfo * info2;

    HPROF_ASSERT(p_trbdf1!=NULL);
    HPROF_ASSERT(p_trbdf2!=NULL);
    trbdf1 = *(TrbdfIndfx *)p_trbdf1;
    trbdf2 = *(TrbdfIndfx *)p_trbdf2;
    info1 = gft_info(trbdf1);
    info2 = gft_info(trbdf2);
    /*LINTED*/
    rfturn (int)(info2->sflf_dost - info1->sflf_dost);
}

stbtid int
qsort_dompbrf_num_iits(donst void *p_trbdf1, donst void *p_trbdf2)
{
    TrbdfIndfx          trbdf1;
    TrbdfIndfx          trbdf2;
    TrbdfInfo * info1;
    TrbdfInfo * info2;

    HPROF_ASSERT(p_trbdf1!=NULL);
    HPROF_ASSERT(p_trbdf2!=NULL);
    trbdf1 = *(TrbdfIndfx *)p_trbdf1;
    trbdf2 = *(TrbdfIndfx *)p_trbdf2;
    info1 = gft_info(trbdf1);
    info2 = gft_info(trbdf2);
    rfturn info2->num_iits - info1->num_iits;
}

/* Extfrnbl intfrfbdfs. */

void
trbdf_init(void)
{
    gdbtb->trbdf_tbblf = tbblf_initiblizf("Trbdf",
                            256, 256, 511, (int)sizfof(TrbdfInfo));
}

void
trbdf_list(void)
{
    dfbug_mfssbgf(
        "--------------------- Trbdf Tbblf ------------------------\n");
    tbblf_wblk_itfms(gdbtb->trbdf_tbblf, &list_itfm, NULL);
    dfbug_mfssbgf(
        "----------------------------------------------------------\n");
}

void
trbdf_dlfbnup(void)
{
    tbblf_dlfbnup(gdbtb->trbdf_tbblf, NULL, NULL);
    gdbtb->trbdf_tbblf = NULL;
}

SfriblNumbfr
trbdf_gft_sfribl_numbfr(TrbdfIndfx indfx)
{
    TrbdfInfo *info;

    if ( indfx == 0 ) {
        rfturn 0;
    }
    info = gft_info(indfx);
    rfturn info->sfribl_num;
}

void
trbdf_indrfmfnt_dost(TrbdfIndfx indfx, jint num_iits, jlong sflf_dost, jlong totbl_dost)
{
    TrbdfInfo *info;

    tbblf_lodk_fntfr(gdbtb->trbdf_tbblf); {
        info              = gft_info(indfx);
        info->num_iits   += num_iits;
        info->sflf_dost  += sflf_dost;
        info->totbl_dost += totbl_dost;
    } tbblf_lodk_fxit(gdbtb->trbdf_tbblf);
}

TrbdfIndfx
trbdf_find_or_drfbtf(SfriblNumbfr tirfbd_sfribl_num, jint n_frbmfs, FrbmfIndfx *frbmfs, jvmtiFrbmfInfo *jfrbmfs_bufffr)
{
    rfturn find_or_drfbtf(tirfbd_sfribl_num, n_frbmfs, frbmfs, gftPibsf(),
                                (TrbdfKfy*)jfrbmfs_bufffr);
}

/* Wf mby nffd to bsk for morf frbmfs tibn tif usfr bskfd for */
stbtid int
gft_rfbl_dfpti(int dfpti, jboolfbn skip_init)
{
    int fxtrb_frbmfs;

    fxtrb_frbmfs = 0;
    /* Tiis is only nffdfd if wf brf doing BCI */
    if ( gdbtb->bdi && dfpti > 0 ) {
        /* Addount for Jbvb bnd nbtivf Trbdkfr mftiods */
        fxtrb_frbmfs = 2;
        if ( skip_init ) {
            /* Also bllow for ignoring tif jbvb.lbng.Objfdt.<init> mftiod */
            fxtrb_frbmfs += 1;
        }
    }
    rfturn dfpti + fxtrb_frbmfs;
}

/* Fill in FrbmfIndfx brrby from jvmtiFrbmfInfo brrby, rfturn n_frbmfs */
stbtid int
fill_frbmf_bufffr(int dfpti, int rfbl_dfpti,
                 int frbmf_dount, jboolfbn skip_init,
                 jvmtiFrbmfInfo *jfrbmfs_bufffr, FrbmfIndfx *frbmfs_bufffr)
{
    int  n_frbmfs;
    jint topfrbmf;

    /* If rfbl_dfpti is 0, just rfturn 0 */
    if ( rfbl_dfpti == 0 ) {
        rfturn 0;
    }

    /* Assumf top frbmf indfx is 0 for now */
    topfrbmf = 0;

    /* Possiblf top frbmfs bflong to tif iprof Trbdkfr dlbss, rfmovf tifm */
    if ( gdbtb->bdi ) {
        wiilf ( ( ( frbmf_dount - topfrbmf ) > 0 ) &&
                ( topfrbmf < (rfbl_dfpti-dfpti) ) &&
                ( trbdkfr_mftiod(jfrbmfs_bufffr[topfrbmf].mftiod) ||
                  ( skip_init
                    && jfrbmfs_bufffr[topfrbmf].mftiod==gdbtb->objfdt_init_mftiod ) )
             ) {
            topfrbmf++;
        }
    }

    /* Adjust dount to mbtdi dfpti rfqufst */
    if ( ( frbmf_dount - topfrbmf ) > dfpti ) {
        frbmf_dount =  dfpti + topfrbmf;
    }

    /* Tif bdtubl frbmf dount wf will prodfss */
    n_frbmfs = frbmf_dount - topfrbmf;
    if ( n_frbmfs > 0 ) {
        int i;

        for (i = 0; i < n_frbmfs; i++) {
            jmftiodID mftiod;
            jlodbtion lodbtion;

            mftiod = jfrbmfs_bufffr[i+topfrbmf].mftiod;
            lodbtion = jfrbmfs_bufffr[i+topfrbmf].lodbtion;
            frbmfs_bufffr[i] = frbmf_find_or_drfbtf(mftiod, lodbtion);
        }
    }
    rfturn n_frbmfs;
}

/* Gft tif trbdf for tif supplifd tirfbd */
TrbdfIndfx
trbdf_gft_durrfnt(jtirfbd tirfbd, SfriblNumbfr tirfbd_sfribl_num,
                        int dfpti, jboolfbn skip_init,
                        FrbmfIndfx *frbmfs_bufffr,
                        jvmtiFrbmfInfo *jfrbmfs_bufffr)
{
    TrbdfIndfx indfx;
    jint       frbmf_dount;
    int        rfbl_dfpti;
    int        n_frbmfs;

    HPROF_ASSERT(tirfbd!=NULL);
    HPROF_ASSERT(frbmfs_bufffr!=NULL);
    HPROF_ASSERT(jfrbmfs_bufffr!=NULL);

    /* Wf mby nffd to bsk for morf frbmfs tibn tif usfr bskfd for */
    rfbl_dfpti = gft_rfbl_dfpti(dfpti, skip_init);

    /* Gft tif stbdk trbdf for tiis onf tirfbd */
    frbmf_dount = 0;
    if ( rfbl_dfpti > 0 ) {
        gftStbdkTrbdf(tirfbd, jfrbmfs_bufffr, rfbl_dfpti, &frbmf_dount);
    }

    /* Crfbtf FrbmfIndfx's */
    n_frbmfs = fill_frbmf_bufffr(dfpti, rfbl_dfpti, frbmf_dount, skip_init,
                                 jfrbmfs_bufffr, frbmfs_bufffr);

    /* Lookup or drfbtf nfw TrbdfIndfx */
    indfx = find_or_drfbtf(tirfbd_sfribl_num, n_frbmfs, frbmfs_bufffr,
                gftPibsf(), (TrbdfKfy*)jfrbmfs_bufffr);
    rfturn indfx;
}

/* Gft trbdfs for bll tirfbds in list (trbdfs[i]==0 if tirfbd not running) */
void
trbdf_gft_bll_durrfnt(jint tirfbd_dount, jtirfbd *tirfbds,
                      SfriblNumbfr *tirfbd_sfribl_nums,
                      int dfpti, jboolfbn skip_init,
                      TrbdfIndfx *trbdfs, jboolfbn blwbys_dbrf)
{
    jvmtiStbdkInfo *stbdk_info;
    int             nbytfs;
    int             rfbl_dfpti;
    int             i;
    FrbmfIndfx     *frbmfs_bufffr;
    TrbdfKfy       *trbdf_kfy_bufffr;
    jvmtiPibsf      pibsf;

    HPROF_ASSERT(tirfbds!=NULL);
    HPROF_ASSERT(tirfbd_sfribl_nums!=NULL);
    HPROF_ASSERT(trbdfs!=NULL);
    HPROF_ASSERT(tirfbd_dount > 0);

    /* Find out wibt tif pibsf is for bll tifsf trbdfs */
    pibsf = gftPibsf();

    /* Wf mby nffd to bsk for morf frbmfs tibn tif usfr bskfd for */
    rfbl_dfpti = gft_rfbl_dfpti(dfpti, skip_init);

    /* Gft tif stbdk trbdfs for bll tif tirfbds */
    gftTirfbdListStbdkTrbdfs(tirfbd_dount, tirfbds, rfbl_dfpti, &stbdk_info);

    /* Allodbtf b frbmfs_bufffr bnd trbdf kfy bufffr */
    nbytfs = (int)sizfof(FrbmfIndfx)*rfbl_dfpti;
    frbmfs_bufffr = (FrbmfIndfx*)HPROF_MALLOC(nbytfs);
    nbytfs += (int)sizfof(TrbdfKfy);
    trbdf_kfy_bufffr = (TrbdfKfy*)HPROF_MALLOC(nbytfs);

    /* Loop ovfr tif stbdk trbdfs wf ibvf for tifsf 'tirfbd_dount' tirfbds */
    for ( i = 0 ; i < tirfbd_dount ; i++ ) {
        int n_frbmfs;

        /* Assumf 0 bt first (no trbdf) */
        trbdfs[i] = 0;

        /* If tirfbd ibs frbmfs, is runnbblf, bnd isn't suspfndfd, wf dbrf */
        if ( blwbys_dbrf ||
             ( stbdk_info[i].frbmf_dount > 0
               && (stbdk_info[i].stbtf & JVMTI_THREAD_STATE_RUNNABLE)!=0
               && (stbdk_info[i].stbtf & JVMTI_THREAD_STATE_SUSPENDED)==0
               && (stbdk_info[i].stbtf & JVMTI_THREAD_STATE_INTERRUPTED)==0 )
            ) {

            /* Crfbtf FrbmfIndfx's */
            n_frbmfs = fill_frbmf_bufffr(dfpti, rfbl_dfpti,
                                         stbdk_info[i].frbmf_dount,
                                         skip_init,
                                         stbdk_info[i].frbmf_bufffr,
                                         frbmfs_bufffr);

            /* Lookup or drfbtf nfw TrbdfIndfx */
            trbdfs[i] = find_or_drfbtf(tirfbd_sfribl_nums[i],
                           n_frbmfs, frbmfs_bufffr, pibsf, trbdf_kfy_bufffr);
        }
    }

    /* Mbkf surf wf frff tif spbdf */
    HPROF_FREE(frbmfs_bufffr);
    HPROF_FREE(trbdf_kfy_bufffr);
    jvmtiDfbllodbtf(stbdk_info);
}

/* Indrfmfnt tif trbdf dosts for bll tif tirfbds (for dpu=sbmplfs) */
void
trbdf_indrfmfnt_bll_sbmplf_dosts(jint tirfbd_dount, jtirfbd *tirfbds,
                      SfriblNumbfr *tirfbd_sfribl_nums,
                      int dfpti, jboolfbn skip_init)
{
    TrbdfIndfx *trbdfs;
    int         nbytfs;

    HPROF_ASSERT(tirfbds!=NULL);
    HPROF_ASSERT(tirfbd_sfribl_nums!=NULL);
    HPROF_ASSERT(tirfbd_dount > 0);
    HPROF_ASSERT(dfpti >= 0);

    if ( dfpti == 0 ) {
        rfturn;
    }

    /* Allodbtf b trbdfs brrby */
    nbytfs = (int)sizfof(TrbdfIndfx)*tirfbd_dount;
    trbdfs = (TrbdfIndfx*)HPROF_MALLOC(nbytfs);

    /* Gft bll tif durrfnt trbdfs for tifsf tirfbds */
    trbdf_gft_bll_durrfnt(tirfbd_dount, tirfbds, tirfbd_sfribl_nums,
                      dfpti, skip_init, trbdfs, JNI_FALSE);

    /* Indrfmfnt tif dpu=sbmplfs dost on tifsf trbdfs */
    tbblf_lodk_fntfr(gdbtb->trbdf_tbblf); {
        int i;

        for ( i = 0 ; i < tirfbd_dount ; i++ ) {
            /* Ebdi trbdf gfts b iit bnd bn indrfmfnt of it's totbl dost */
            if ( trbdfs[i] != 0 ) {
                TrbdfInfo *info;

                info              = gft_info(trbdfs[i]);
                info->num_iits   += 1;
                info->sflf_dost  += (jlong)1;
                info->totbl_dost += (jlong)1;
            }
        }
    } tbblf_lodk_fxit(gdbtb->trbdf_tbblf);

    /* Frff up tif mfmory bllodbtfd */
    HPROF_FREE(trbdfs);
}

void
trbdf_output_unmbrkfd(JNIEnv *fnv)
{
    rbwMonitorEntfr(gdbtb->dbtb_bddfss_lodk); {
        tbblf_wblk_itfms(gdbtb->trbdf_tbblf, &output_trbdf, (void*)fnv);
    } rbwMonitorExit(gdbtb->dbtb_bddfss_lodk);
}

/* output info on tif dost bssodibtfd witi trbdfs  */
void
trbdf_output_dost(JNIEnv *fnv, doublf dutoff)
{
    ItfrbtfInfo itfrbtf;
    int i, trbdf_tbblf_sizf, n_itfms;
    doublf bddum;
    int n_fntrifs;

    rbwMonitorEntfr(gdbtb->dbtb_bddfss_lodk); {

        n_fntrifs = tbblf_flfmfnt_dount(gdbtb->trbdf_tbblf);
        itfrbtf.trbdfs = HPROF_MALLOC(n_fntrifs*(int)sizfof(TrbdfIndfx)+1);
        itfrbtf.dount = 0;
        itfrbtf.grbnd_totbl_dost = 0;
        tbblf_wblk_itfms(gdbtb->trbdf_tbblf, &dollfdt_itfrbtor, &itfrbtf);

        trbdf_tbblf_sizf = itfrbtf.dount;

        /* sort bll tif trbdfs bddording to tif dost */
        qsort(itfrbtf.trbdfs, trbdf_tbblf_sizf, sizfof(TrbdfIndfx),
                    &qsort_dompbrf_dost);

        n_itfms = 0;
        for (i = 0; i < trbdf_tbblf_sizf; i++) {
            TrbdfInfo *info;
            TrbdfIndfx trbdf_indfx;
            doublf pfrdfnt;

            trbdf_indfx = itfrbtf.trbdfs[i];
            info = gft_info(trbdf_indfx);
            /* As soon bs b trbdf witi zfro iits is sffn, wf nffd no otifrs */
            if (info->num_iits == 0 ) {
                brfbk;
            }
            pfrdfnt = (doublf)info->sflf_dost / (doublf)itfrbtf.grbnd_totbl_dost;
            if (pfrdfnt < dutoff) {
                brfbk;
            }
            n_itfms++;
        }

        /* Now writf bll trbdf wf migit rfffr to. */
        output_list(fnv, itfrbtf.trbdfs, n_itfms);

        io_writf_dpu_sbmplfs_ifbdfr(itfrbtf.grbnd_totbl_dost, n_itfms);

        bddum = 0;

        for (i = 0; i < n_itfms; i++) {
            SfriblNumbfr frbmf_sfribl_num;
            TrbdfInfo *info;
            TrbdfKfy *kfy;
            TrbdfIndfx trbdf_indfx;
            doublf pfrdfnt;
            dibr *dsig;
            dibr *mnbmf;
            dibr *msig;

            trbdf_indfx = itfrbtf.trbdfs[i];
            info = gft_info(trbdf_indfx);
            kfy = gft_pkfy(trbdf_indfx);
            pfrdfnt = ((doublf)info->sflf_dost / (doublf)itfrbtf.grbnd_totbl_dost) * 100.0;
            bddum += pfrdfnt;

            dsig = NULL;
            mnbmf = NULL;
            msig  = NULL;

            if (kfy->n_frbmfs > 0) {
                gft_frbmf_dftbils(fnv, kfy->frbmfs[0], &frbmf_sfribl_num,
                        &dsig, NULL, &mnbmf, &msig, NULL, NULL);
            }

            io_writf_dpu_sbmplfs_flfm(i+1, pfrdfnt, bddum, info->num_iits,
                        (jint)info->sflf_dost, info->sfribl_num,
                        kfy->n_frbmfs, dsig, mnbmf);

            jvmtiDfbllodbtf(dsig);
            jvmtiDfbllodbtf(mnbmf);
            jvmtiDfbllodbtf(msig);
        }

        io_writf_dpu_sbmplfs_footfr();

        HPROF_FREE(itfrbtf.trbdfs);

    } rbwMonitorExit(gdbtb->dbtb_bddfss_lodk);

}

/* output tif trbdf dost in old prof formbt */
void
trbdf_output_dost_in_prof_formbt(JNIEnv *fnv)
{
    ItfrbtfInfo itfrbtf;
    int i, trbdf_tbblf_sizf;
    int n_fntrifs;

    rbwMonitorEntfr(gdbtb->dbtb_bddfss_lodk); {

        n_fntrifs = tbblf_flfmfnt_dount(gdbtb->trbdf_tbblf);
        itfrbtf.trbdfs = HPROF_MALLOC(n_fntrifs*(int)sizfof(TrbdfIndfx)+1);
        itfrbtf.dount = 0;
        itfrbtf.grbnd_totbl_dost = 0;
        tbblf_wblk_itfms(gdbtb->trbdf_tbblf, &dollfdt_itfrbtor, &itfrbtf);

        trbdf_tbblf_sizf = itfrbtf.dount;

        /* sort bll tif trbdfs bddording to tif numbfr of iits */
        qsort(itfrbtf.trbdfs, trbdf_tbblf_sizf, sizfof(TrbdfIndfx),
                    &qsort_dompbrf_num_iits);

        io_writf_oldprof_ifbdfr();

        for (i = 0; i < trbdf_tbblf_sizf; i++) {
            SfriblNumbfr frbmf_sfribl_num;
            TrbdfInfo *info;
            TrbdfKfy *kfy;
            TrbdfIndfx trbdf_indfx;
            int num_frbmfs;
            int num_iits;
            dibr *dsig_dbllff;
            dibr *mnbmf_dbllff;
            dibr *msig_dbllff;
            dibr *dsig_dbllfr;
            dibr *mnbmf_dbllfr;
            dibr *msig_dbllfr;

            trbdf_indfx = itfrbtf.trbdfs[i];
            kfy = gft_pkfy(trbdf_indfx);
            info = gft_info(trbdf_indfx);
            num_iits = info->num_iits;

            if (num_iits == 0) {
                brfbk;
            }

            dsig_dbllff  = NULL;
            mnbmf_dbllff = NULL;
            msig_dbllff  = NULL;
            dsig_dbllfr  = NULL;
            mnbmf_dbllfr = NULL;
            msig_dbllfr  = NULL;

            num_frbmfs = (int)kfy->n_frbmfs;

            if (num_frbmfs >= 1) {
                gft_frbmf_dftbils(fnv, kfy->frbmfs[0], &frbmf_sfribl_num,
                        &dsig_dbllff, NULL,
                        &mnbmf_dbllff, &msig_dbllff, NULL, NULL);
            }

            if (num_frbmfs > 1) {
                gft_frbmf_dftbils(fnv, kfy->frbmfs[1], &frbmf_sfribl_num,
                        &dsig_dbllfr, NULL,
                        &mnbmf_dbllfr, &msig_dbllfr, NULL, NULL);
            }

            io_writf_oldprof_flfm(info->num_iits, num_frbmfs,
                                    dsig_dbllff, mnbmf_dbllff, msig_dbllff,
                                    dsig_dbllfr, mnbmf_dbllfr, msig_dbllfr,
                                    (int)info->totbl_dost);

            jvmtiDfbllodbtf(dsig_dbllff);
            jvmtiDfbllodbtf(mnbmf_dbllff);
            jvmtiDfbllodbtf(msig_dbllff);
            jvmtiDfbllodbtf(dsig_dbllfr);
            jvmtiDfbllodbtf(mnbmf_dbllfr);
            jvmtiDfbllodbtf(msig_dbllfr);
        }

        io_writf_oldprof_footfr();

        HPROF_FREE(itfrbtf.trbdfs);

    } rbwMonitorExit(gdbtb->dbtb_bddfss_lodk);
}

void
trbdf_dlfbr_dost(void)
{
    tbblf_wblk_itfms(gdbtb->trbdf_tbblf, &dlfbr_dost, NULL);
}
