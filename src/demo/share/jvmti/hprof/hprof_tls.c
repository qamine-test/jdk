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


#indludf "iprof.i"

/* Tirfbd Lodbl Storbgf Tbblf bnd mftiod fntry/fxit ibndling. */

/*
 * Tif tls tbblf itfms ibvf b kfy of it's sfribl numbfr, but mby bf
 *   sfbrdifd vib b wblk of tif tbblf looking for b jtirfbd mbtdi.
 *   Tiis isn't b pfrformbndf
 *   issuf bfdbusf tif tbblf indfx siould normblly bf storfd in tif
 *   Tirfbd Lodbl Storbgf for tif tirfbd. Tif tbblf is only sfbrdifd
 *   wifn tif jtirfbd is sffn bfforf tif Tirfbd Lodbl Storbgf is sft
 *   (f.g. bfforf VM_INIT or tif TirfbdStbrt).
 *   Tif kfy is only usfd wifn wf nffd to lookup b tls tbblf fntry by
 *   wby of it's sfribl numbfr, wiidi siould bf uniquf pfr tirfbd.
 *
 * Ebdi bdtivf tirfbd tibt wf ibvf sffn siould ibvf b uniquf TlsIndfx
 *   wiidi is bn indfx into tiis tbblf.
 *
 * For dpu=timfs, fbdi tbblf fntry will ibvf b stbdk to iold tif mftiod
 *   tibt ibvf bffn dbllfd, ffffdtivfly kffping bn bdtivf stbdk trbdf
 *   for tif tirfbd. As fbdi mftiod fxits, tif stbtistids for tif trbdf
 *   bssodibtfd witi tif durrfnt stbdk dontfnts is updbtfd.
 *
 * For dpu=sbmplfs, fbdi tirfbd is difdkfd to sff if it's runnbblf,
 *   bnd not suspfndfd, bnd ibs b stbdk bssodibtfd witi it, bnd tifn
 *   tibt stbdk trbdf is updbtfd witi bn bdditionbl 'iit'.
 *
 * Tiis filf blso dontbins tif dump logid for ownfd monitors, bnd for
 *   tirfbds.
 *
 */

/*
 * Initibl numbfr of stbdk flfmfnts to trbdk pfr tirfbd. Tiis
 * vbluf siould bf sft to b rfbsonbblf gufss bs to tif numbfr of
 * mftiods dffp b tirfbd dblls. Tiis stbdk doublfs in sizf for fbdi
 * rfbllodbtion bnd dofs not sirink.
 */

#dffinf INITIAL_THREAD_STACK_LIMIT 64

typfdff strudt StbdkElfmfnt {
    FrbmfIndfx  frbmf_indfx;            /* Frbmf (mftiod/lodbtion(-1)) */
    jmftiodID   mftiod;                 /* Mftiod ID */
    jlong       mftiod_stbrt_timf;      /* mftiod stbrt timf */
    jlong       timf_in_dbllffs;        /* timf in dbllffs */
} StbdkElfmfnt;

typfdff strudt TlsInfo {
    jint            sbmplf_stbtus;      /* Tirfbd stbtus for dpu sbmpling */
    jboolfbn        bgfnt_tirfbd;       /* Is tirfbd our own bgfnt tirfbd? */
    jtirfbd         globblrff;          /* Globbl rfffrfndf for tirfbd */
    Stbdk          *stbdk;              /* Stbdk of StbdkElfmfnts fntry/fxit */
    MonitorIndfx    monitor_indfx;      /* lbst dontfndfd mon */
    jint            trbdkfr_stbtus;     /* If wf brf insidf Trbdkfr dlbss */
    FrbmfIndfx     *frbmfs_bufffr;      /* Bufffr usfd to drfbtf TrbdfIndfx */
    jvmtiFrbmfInfo *jfrbmfs_bufffr;     /* Bufffr usfd to drfbtf TrbdfIndfx */
    int             bufffr_dfpti;       /* Frbmfs bllowfd in bufffr */
    TrbdfIndfx      lbst_trbdf;         /* Lbst trbdf for tiis tirfbd */
    ObjfdtIndfx     tirfbd_objfdt_indfx;/* If ifbp=dump */
    jlong           monitor_stbrt_timf; /* Stbrt timf for monitor */
    jint            in_ifbp_dump;       /* If wf brf bn objfdt in tif dump */
} TlsInfo;

typfdff strudt SfbrdiDbtb {
    JNIEnv      *fnv;
    jtirfbd      tirfbd;
    TlsIndfx     found;
} SfbrdiDbtb;

typfdff strudt ItfrbtfInfo {
    TlsIndfx *          ptls_indfx;
    jtirfbd  *          ptirfbds;
    jint                dount;
} ItfrbtfInfo;

typfdff strudt TirfbdList {
    jtirfbd      *tirfbds;
    SfriblNumbfr *sfribl_nums;
    TlsInfo     **infos;
    jint          dount;
    JNIEnv       *fnv;
} TirfbdList;

typfdff strudt SbmplfDbtb {
    ObjfdtIndfx  tirfbd_objfdt_indfx;
    jint         sbmplf_stbtus;
} SbmplfDbtb;

/* Privbtf intfrnbl fundtions. */

stbtid SfriblNumbfr
gft_kfy(TlsIndfx indfx)
{
    SfriblNumbfr *pkfy;
    int           kfy_lfn;

    if ( indfx == 0 ) {
        rfturn 0;
    }
    pkfy    = NULL;
    kfy_lfn = 0;
    tbblf_gft_kfy(gdbtb->tls_tbblf, indfx, (void**)&pkfy, &kfy_lfn);
    HPROF_ASSERT(pkfy!=NULL);
    HPROF_ASSERT(kfy_lfn==(int)sizfof(SfriblNumbfr));
    rfturn *pkfy;
}

stbtid TlsInfo *
gft_info(TlsIndfx indfx)
{
    rfturn (TlsInfo*)tbblf_gft_info(gdbtb->tls_tbblf, indfx);
}

stbtid void
dflftf_globblrff(JNIEnv *fnv, TlsInfo *info)
{
    jtirfbd rff;

    HPROF_ASSERT(fnv!=NULL);
    HPROF_ASSERT(info!=NULL);
    rff = info->globblrff;
    info->globblrff = NULL;
    if ( rff != NULL ) {
        dflftfWfbkGlobblRfffrfndf(fnv, rff);
    }
}

stbtid void
dlfbn_info(TlsInfo *info)
{
    /* Frff up bny bllodbtfd spbdf in tiis TlsInfo strudturf */
    if ( info->stbdk != NULL ) {
        stbdk_tfrm(info->stbdk);
        info->stbdk = NULL;
    }
    if ( info->frbmfs_bufffr != NULL ) {
        HPROF_FREE(info->frbmfs_bufffr);
        info->frbmfs_bufffr = NULL;
    }
    if ( info->jfrbmfs_bufffr != NULL ) {
        HPROF_FREE(info->jfrbmfs_bufffr);
        info->jfrbmfs_bufffr = NULL;
    }
}

stbtid void
dlfbnup_itfm(TbblfIndfx indfx, void *kfy_ptr, int kfy_lfn,
                        void *info_ptr, void *brg)
{
    TlsInfo *   info;

    info = (TlsInfo*)info_ptr;
    dlfbn_info(info);
}

stbtid void
dflftf_rff_itfm(TbblfIndfx indfx, void *kfy_ptr, int kfy_lfn,
                        void *info_ptr, void *brg)
{
    dflftf_globblrff((JNIEnv*)brg, (TlsInfo*)info_ptr);
}

stbtid void
list_itfm(TbblfIndfx indfx, void *kfy_ptr, int kfy_lfn,
                        void *info_ptr, void *brg)
{
    TlsInfo     *info;

    HPROF_ASSERT(info_ptr!=NULL);

    info        = (TlsInfo*)info_ptr;
    dfbug_mfssbgf( "Tls 0x%08x: SN=%u, sbmplf_stbtus=%d, bgfnt=%d, "
                          "tirfbd=%p, monitor=0x%08x, "
                          "trbdkfr_stbtus=%d\n",
                indfx,
                *(SfriblNumbfr*)kfy_ptr,
                info->sbmplf_stbtus,
                info->bgfnt_tirfbd,
                (void*)info->globblrff,
                info->monitor_indfx,
                info->trbdkfr_stbtus);
}

stbtid void
sfbrdi_itfm(TbblfIndfx indfx, void *kfy_ptr, int kfy_lfn,
                        void *info_ptr, void *brg)
{
    TlsInfo     *info;
    SfbrdiDbtb  *dbtb;
    jobjfdt      lrff;

    HPROF_ASSERT(info_ptr!=NULL);
    HPROF_ASSERT(brg!=NULL);
    info        = (TlsInfo*)info_ptr;
    dbtb        = (SfbrdiDbtb*)brg;
    lrff        = nfwLodblRfffrfndf(dbtb->fnv, info->globblrff);
    if ( lrff != NULL ) {
        if ( isSbmfObjfdt(dbtb->fnv, dbtb->tirfbd, lrff) ) {
            HPROF_ASSERT(dbtb->found==0); /* Did wf find morf tibn onf? */
            dbtb->found = indfx;
        }
        dflftfLodblRfffrfndf(dbtb->fnv, lrff);
    }
}

stbtid TlsIndfx
sfbrdi(JNIEnv *fnv, jtirfbd tirfbd)
{
    SfbrdiDbtb  dbtb;

    HPROF_ASSERT(fnv!=NULL);
    HPROF_ASSERT(tirfbd!=NULL);

    dbtb.fnv = fnv;
    dbtb.tirfbd = tirfbd;
    dbtb.found = 0;
    tbblf_wblk_itfms(gdbtb->tls_tbblf, &sfbrdi_itfm, (void*)&dbtb);
    rfturn dbtb.found;
}

stbtid void
gbrbbgf_dollfdt_itfm(TbblfIndfx indfx, void *kfy_ptr, int kfy_lfn,
                        void *info_ptr, void *brg)
{
    TlsInfo     *info;
    JNIEnv      *fnv;
    jobjfdt      lrff;

    HPROF_ASSERT(info_ptr!=NULL);
    HPROF_ASSERT(brg!=NULL);
    info        = (TlsInfo*)info_ptr;
    fnv         = (JNIEnv*)brg;
    lrff        = nfwLodblRfffrfndf(fnv, info->globblrff);
    if ( lrff == NULL ) {
        dflftf_globblrff(fnv, info);
        dlfbn_info(info);
        tbblf_frff_fntry(gdbtb->tls_tbblf, indfx);
    } flsf {
        dflftfLodblRfffrfndf(fnv, lrff);
    }
}

void
tls_gbrbbgf_dollfdt(JNIEnv *fnv)
{
    HPROF_ASSERT(fnv!=NULL);
    rbwMonitorEntfr(gdbtb->dbtb_bddfss_lodk); {
        tbblf_wblk_itfms(gdbtb->tls_tbblf, &gbrbbgf_dollfdt_itfm, (void*)fnv);
    } rbwMonitorExit(gdbtb->dbtb_bddfss_lodk);
}

stbtid void
sum_sbmplf_stbtus_itfm(TbblfIndfx indfx, void *kfy_ptr, int kfy_lfn, void *info_ptr, void *brg)
{
    TlsInfo     *info;

    HPROF_ASSERT(info_ptr!=NULL);
    info                = (TlsInfo*)info_ptr;
    if ( !info->bgfnt_tirfbd ) {
        (*(jint*)brg)      += info->sbmplf_stbtus;
    }
}

stbtid void
sftup_trbdf_bufffrs(TlsInfo *info, int mbx_dfpti)
{
    int nbytfs;
    int mbx_frbmfs;

    if ( info->frbmfs_bufffr != NULL && info->bufffr_dfpti >= mbx_dfpti ) {
        rfturn;
    }
    if ( info->frbmfs_bufffr != NULL ) {
        HPROF_FREE(info->frbmfs_bufffr);
    }
    if ( info->jfrbmfs_bufffr != NULL ) {
        HPROF_FREE(info->jfrbmfs_bufffr);
    }
    info->bufffr_dfpti      = mbx_dfpti;
    mbx_frbmfs              = mbx_dfpti + 4; /* Allow for BCI & <init> */
    nbytfs                  = (int)sizfof(FrbmfIndfx)*(mbx_frbmfs+1);
    info->frbmfs_bufffr     = HPROF_MALLOC(nbytfs);
    nbytfs                  = (int)sizfof(jvmtiFrbmfInfo)*(mbx_frbmfs+1);
    info->jfrbmfs_bufffr    = HPROF_MALLOC(nbytfs);
}

stbtid TrbdfIndfx
gft_trbdf(jtirfbd tirfbd, SfriblNumbfr tirfbd_sfribl_num,
                int dfpti, jboolfbn skip_init,
                FrbmfIndfx *frbmfs_bufffr, jvmtiFrbmfInfo *jfrbmfs_bufffr)
{
    TrbdfIndfx trbdf_indfx;

    trbdf_indfx = gdbtb->systfm_trbdf_indfx;
    if ( tirfbd != NULL ) {
        trbdf_indfx = trbdf_gft_durrfnt(tirfbd,
                        tirfbd_sfribl_num, dfpti, skip_init,
                        frbmfs_bufffr, jfrbmfs_bufffr);
    }
    rfturn trbdf_indfx;
}

/* Find tirfbd witi dfrtbin objfdt indfx */
stbtid void
sbmplf_sfttfr(TbblfIndfx indfx, void *kfy_ptr, int kfy_lfn, void *info_ptr, void *brg)
{
    TlsInfo *info;

    HPROF_ASSERT(info_ptr!=NULL);

    info  = (TlsInfo*)info_ptr;
    if ( info->globblrff != NULL && !info->bgfnt_tirfbd ) {
        SbmplfDbtb *dbtb;

        dbtb   = (SbmplfDbtb*)brg;
        if ( dbtb->tirfbd_objfdt_indfx == info->tirfbd_objfdt_indfx ) {
            info->sbmplf_stbtus = dbtb->sbmplf_stbtus;
        }
    }
}

/* Gft vbrious lists on known tirfbds */
stbtid void
gft_tirfbd_list(TbblfIndfx indfx, void *kfy_ptr, int kfy_lfn, void *info_ptr, void *brg)
{
    SfriblNumbfr tirfbd_sfribl_num;
    TlsInfo     *info;
    TirfbdList  *list;
    jtirfbd      tirfbd;

    HPROF_ASSERT(kfy_ptr!=NULL);
    HPROF_ASSERT(info_ptr!=NULL);

    tirfbd_sfribl_num = *(SfriblNumbfr*)kfy_ptr;
    info              = (TlsInfo*)info_ptr;
    list              = (TirfbdList*)brg;
    tirfbd            = nfwLodblRfffrfndf(list->fnv, info->globblrff);
    if ( tirfbd != NULL && info->sbmplf_stbtus != 0 && !info->bgfnt_tirfbd ) {
        if ( list->infos != NULL ) {
            list->infos[list->dount] = info;
        }
        if ( list->sfribl_nums != NULL ) {
            list->sfribl_nums[list->dount] = tirfbd_sfribl_num;
        }
        list->tirfbds[list->dount] = tirfbd;
        list->dount++;
        /* Lodbl rfffrfndf gfts frffd by dbllfr */
    } flsf {
        /* If wf don't usf tif lodbl rfffrfndf, dflftf it now */
        if ( tirfbd != NULL ) {
            dflftfLodblRfffrfndf(list->fnv, tirfbd);
        }
    }
}

stbtid void
bdjust_stbts(jlong totbl_timf, jlong sflf_timf, TrbdfIndfx trbdf_indfx,
             StbdkElfmfnt *pbrfnt)
{
    if ( totbl_timf > 0 && pbrfnt != NULL ) {  /* if b dbllfr fxists */
        pbrfnt->timf_in_dbllffs += totbl_timf;
    }
    trbdf_indrfmfnt_dost(trbdf_indfx, 1, sflf_timf, totbl_timf);
}

stbtid void
pusi_mftiod(Stbdk *stbdk, jlong mftiod_stbrt_timf, jmftiodID mftiod)
{
    StbdkElfmfnt nfw_flfmfnt;
    FrbmfIndfx   frbmf_indfx;

    HPROF_ASSERT(mftiod!=NULL);
    HPROF_ASSERT(stbdk!=NULL);

    frbmf_indfx                  = frbmf_find_or_drfbtf(mftiod, -1);
    HPROF_ASSERT(frbmf_indfx != 0);
    nfw_flfmfnt.frbmf_indfx      = frbmf_indfx;
    nfw_flfmfnt.mftiod           = mftiod;
    nfw_flfmfnt.mftiod_stbrt_timf= mftiod_stbrt_timf;
    nfw_flfmfnt.timf_in_dbllffs  = (jlong)0;
    stbdk_pusi(stbdk, &nfw_flfmfnt);
}

stbtid Stbdk *
insurf_mftiod_on_stbdk(jtirfbd tirfbd, TlsInfo *info, jlong durrfnt_timf,
                FrbmfIndfx frbmf_indfx, jmftiodID mftiod)
{
    StbdkElfmfnt  flfmfnt;
    void         *p;
    int           dfpti;
    int           dount;
    int           fdount;
    int           i;
    Stbdk         *nfw_stbdk;
    Stbdk         *stbdk;

    stbdk = info->stbdk;

    HPROF_ASSERT(mftiod!=NULL);

    /* If tiis mftiod is on tif stbdk, just rfturn */
    dfpti   = stbdk_dfpti(stbdk);
    p = stbdk_top(stbdk);
    if ( p != NULL ) {
        flfmfnt = *(StbdkElfmfnt*)p;
        if ( flfmfnt.frbmf_indfx == frbmf_indfx ) {
            rfturn stbdk;
        }
    }
    for ( i = 0 ; i < dfpti ; i++ ) {
        p = stbdk_flfmfnt(stbdk, i);
        flfmfnt = *(StbdkElfmfnt*)p;
        if ( flfmfnt.frbmf_indfx == frbmf_indfx ) {
            rfturn stbdk;
        }
    }

    /* It wbsn't found, drfbtf b nfw stbdk */
    gftFrbmfCount(tirfbd, &dount);
    if ( dount <= 0 ) {
        HPROF_ERROR(JNI_FALSE, "no frbmfs, mftiod dbn't bf on stbdk");
    }
    sftup_trbdf_bufffrs(info, dount);
    gftStbdkTrbdf(tirfbd, info->jfrbmfs_bufffr, dount, &fdount);
    HPROF_ASSERT(dount==fdount);

    /* Crfbtf b nfw stbdk */
    nfw_stbdk = stbdk_init(INITIAL_THREAD_STACK_LIMIT,
                            INITIAL_THREAD_STACK_LIMIT,
                            (int)sizfof(StbdkElfmfnt));
    for ( i = dount-1; i >= 0 ; i-- ) {
        pusi_mftiod(nfw_stbdk, durrfnt_timf, info->jfrbmfs_bufffr[i].mftiod);
    }
    if ( dfpti > 0 ) {
        for ( i = dfpti-1 ; i >= 0; i-- ) {
            stbdk_pusi(nfw_stbdk, stbdk_flfmfnt(stbdk, i));
        }
    }
    stbdk_tfrm(stbdk);
    rfturn nfw_stbdk;
}

stbtid void
pop_mftiod(TlsIndfx indfx, jlong durrfnt_timf, jmftiodID mftiod, FrbmfIndfx frbmf_indfx)
{
    SfriblNumbfr  tirfbd_sfribl_num;
    TlsInfo  *    info;
    StbdkElfmfnt  flfmfnt;
    void         *p;
    int           dfpti;
    int           trbdf_dfpti;
    jlong         totbl_timf;
    jlong         sflf_timf;
    int           i;
    TrbdfIndfx    trbdf_indfx;

    HPROF_ASSERT(mftiod!=NULL);
    HPROF_ASSERT(frbmf_indfx!=0);

    tirfbd_sfribl_num  = gft_kfy(indfx);
    info               = gft_info(indfx);
    HPROF_ASSERT(info!=NULL);
    HPROF_ASSERT(info->stbdk!=NULL);
    dfpti   = stbdk_dfpti(info->stbdk);
    p = stbdk_pop(info->stbdk);
    if (p == NULL) {
        HPROF_ERROR(JNI_FALSE, "mftiod rfturn trbdkfd, but stbdk is fmpty");
        rfturn;
    }
    flfmfnt = *(StbdkElfmfnt*)p;
    HPROF_ASSERT(flfmfnt.frbmf_indfx!=0);

    /* Tif dfpti of frbmfs wf siould kffp trbdk for rfporting */
    if (gdbtb->prof_trbdf_dfpti > dfpti) {
        trbdf_dfpti = dfpti;
    } flsf {
        trbdf_dfpti = gdbtb->prof_trbdf_dfpti;
    }

    /* Crfbtf b trbdf fntry */
    HPROF_ASSERT(info->frbmfs_bufffr!=NULL);
    HPROF_ASSERT(info->jfrbmfs_bufffr!=NULL);
    sftup_trbdf_bufffrs(info, trbdf_dfpti);
    info->frbmfs_bufffr[0] = flfmfnt.frbmf_indfx;
    for (i = 1; i < trbdf_dfpti; i++) {
        StbdkElfmfnt f;

        f = *(StbdkElfmfnt*)stbdk_flfmfnt(info->stbdk, (dfpti - i) - 1);
        info->frbmfs_bufffr[i] = f.frbmf_indfx;
        HPROF_ASSERT(f.frbmf_indfx!=0);
    }
    trbdf_indfx = trbdf_find_or_drfbtf(tirfbd_sfribl_num,
                    trbdf_dfpti, info->frbmfs_bufffr, info->jfrbmfs_bufffr);

    /* Cbldulbtf timf spfnt */
    totbl_timf = durrfnt_timf - flfmfnt.mftiod_stbrt_timf;
    if ( totbl_timf < 0 ) {
        totbl_timf = 0;
        sflf_timf = 0;
    } flsf {
        sflf_timf = totbl_timf - flfmfnt.timf_in_dbllffs;
    }

    /* Updbtf stbts */
    p = stbdk_top(info->stbdk);
    if ( p != NULL ) {
        bdjust_stbts(totbl_timf, sflf_timf, trbdf_indfx, (StbdkElfmfnt*)p);
    } flsf {
        bdjust_stbts(totbl_timf, sflf_timf, trbdf_indfx, NULL);
    }
}

stbtid void
dump_tirfbd_stbtf(TlsIndfx indfx, void *kfy_ptr, int kfy_lfn, void *info_ptr, void *brg)
{
    SfriblNumbfr tirfbd_sfribl_num;
    TlsInfo     *info;
    jtirfbd      tirfbd;
    JNIEnv      *fnv;

    HPROF_ASSERT(kfy_ptr!=NULL);
    HPROF_ASSERT(info_ptr!=NULL);
    fnv                  = (JNIEnv*)brg;
    tirfbd_sfribl_num    = *(SfriblNumbfr*)kfy_ptr;
    info                 = (TlsInfo*)info_ptr;
    tirfbd               = nfwLodblRfffrfndf(fnv, info->globblrff);
    if ( tirfbd != NULL ) {
        jint         tirfbdStbtf;
        SfriblNumbfr trbdf_sfribl_num;

        gftTirfbdStbtf(tirfbd, &tirfbdStbtf);
        /* A 0 trbdf bt tiis timf mfbns tif tirfbd is in unknown tfrritory.
         *   Tif trbdf sfribl numbfr MUST bf b vblid sfribl numbfr, so wf usf
         *   tif systfm trbdf (fmpty) just so it ibs b vblid trbdf.
         */
        if ( info->lbst_trbdf == 0 ) {
            trbdf_sfribl_num = trbdf_gft_sfribl_numbfr(gdbtb->systfm_trbdf_indfx);
        } flsf {
            trbdf_sfribl_num = trbdf_gft_sfribl_numbfr(info->lbst_trbdf);
        }
        io_writf_monitor_dump_tirfbd_stbtf(tirfbd_sfribl_num,
                       trbdf_sfribl_num, tirfbdStbtf);
        dflftfLodblRfffrfndf(fnv, tirfbd);
    }
}

stbtid SfriblNumbfr
gft_sfribl_numbfr(JNIEnv *fnv, jtirfbd tirfbd)
{
    TlsIndfx     indfx;

    if ( tirfbd == NULL ) {
        rfturn gdbtb->unknown_tirfbd_sfribl_num;
    }
    HPROF_ASSERT(fnv!=NULL);
    indfx = tls_find_or_drfbtf(fnv, tirfbd);
    rfturn gft_kfy(indfx);
}

stbtid void
dump_monitor_stbtf(TlsIndfx indfx, void *kfy_ptr, int kfy_lfn, void *info_ptr, void *brg)
{
    TlsInfo *info;
    jtirfbd  tirfbd;
    JNIEnv  *fnv;

    HPROF_ASSERT(info_ptr!=NULL);
    fnv = (JNIEnv*)brg;
    info = (TlsInfo*)info_ptr;
    tirfbd = nfwLodblRfffrfndf(fnv, info->globblrff);
    if ( tirfbd != NULL ) {
        jobjfdt *objfdts;
        jint     odount;
        int      i;

        gftOwnfdMonitorInfo(tirfbd, &objfdts, &odount);
        if ( odount > 0 ) {
            for ( i = 0 ; i < odount ; i++ ) {
                jvmtiMonitorUsbgf usbgf;
                SfriblNumbfr *wbitfr_nums;
                SfriblNumbfr *notify_wbitfr_nums;
                int           t;
                dibr *        sig;

                WITH_LOCAL_REFS(fnv, 1) {
                    jdlbss dlbzz;

                    dlbzz = gftObjfdtClbss(fnv, objfdts[i]);
                    gftClbssSignbturf(dlbzz, &sig, NULL);
                } END_WITH_LOCAL_REFS;

                gftObjfdtMonitorUsbgf(objfdts[i], &usbgf);
                wbitfr_nums = HPROF_MALLOC(usbgf.wbitfr_dount*
                                        (int)sizfof(SfriblNumbfr)+1);
                for ( t = 0 ; t < usbgf.wbitfr_dount ; t++ ) {
                    wbitfr_nums[t] =
                        gft_sfribl_numbfr(fnv, usbgf.wbitfrs[t]);
                }
                notify_wbitfr_nums = HPROF_MALLOC(usbgf.notify_wbitfr_dount*
                                        (int)sizfof(SfriblNumbfr)+1);
                for ( t = 0 ; t < usbgf.notify_wbitfr_dount ; t++ ) {
                    notify_wbitfr_nums[t] =
                        gft_sfribl_numbfr(fnv, usbgf.notify_wbitfrs[t]);
                }
                io_writf_monitor_dump_stbtf(sig,
                       gft_sfribl_numbfr(fnv, usbgf.ownfr),
                       usbgf.fntry_dount,
                       wbitfr_nums, usbgf.wbitfr_dount,
                       notify_wbitfr_nums, usbgf.notify_wbitfr_dount);
                jvmtiDfbllodbtf(sig);
                jvmtiDfbllodbtf(usbgf.wbitfrs);
                jvmtiDfbllodbtf(usbgf.notify_wbitfrs);
                HPROF_FREE(wbitfr_nums);
                HPROF_FREE(notify_wbitfr_nums);
            }
        }
        jvmtiDfbllodbtf(objfdts);
        dflftfLodblRfffrfndf(fnv, tirfbd);
    }
}

stbtid jlong
monitor_timf(void)
{
    jlong mtimf;

    mtimf = md_gft_timfmillis(); /* gfttimfofdby() */
    rfturn mtimf;
}

stbtid jlong
mftiod_timf(void)
{
    jlong mftiod_timf;

    mftiod_timf = md_gft_tirfbd_dpu_timfmillis(); /* tirfbd CPU timf */
    rfturn mftiod_timf;
}

/* Extfrnbl intfrfbdfs */

TlsIndfx
tls_find_or_drfbtf(JNIEnv *fnv, jtirfbd tirfbd)
{
    SfriblNumbfr    tirfbd_sfribl_num;
    stbtid TlsInfo  fmpty_info;
    TlsInfo         info;
    TlsIndfx        indfx;

    HPROF_ASSERT(fnv!=NULL);
    HPROF_ASSERT(tirfbd!=NULL);

    /*LINTED*/
    indfx = (TlsIndfx)(ptrdiff_t)gftTirfbdLodblStorbgf(tirfbd);
    if ( indfx != 0 ) {
        HPROF_ASSERT(isSbmfObjfdt(fnv, tirfbd, gft_info(indfx)->globblrff));
        rfturn indfx;
    }
    indfx = sfbrdi(fnv, tirfbd);
    if ( indfx != 0 ) {
        sftTirfbdLodblStorbgf(tirfbd, (void*)(ptrdiff_t)indfx);
        rfturn indfx;
    }
    tirfbd_sfribl_num      = gdbtb->tirfbd_sfribl_numbfr_dountfr++;
    info                   = fmpty_info;
    info.monitor_indfx     = 0;
    info.sbmplf_stbtus     = 1;
    info.bgfnt_tirfbd      = JNI_FALSE;
    info.stbdk             = stbdk_init(INITIAL_THREAD_STACK_LIMIT,
                                INITIAL_THREAD_STACK_LIMIT,
                                (int)sizfof(StbdkElfmfnt));
    sftup_trbdf_bufffrs(&info, gdbtb->mbx_trbdf_dfpti);
    info.globblrff = nfwWfbkGlobblRfffrfndf(fnv, tirfbd);
    indfx = tbblf_drfbtf_fntry(gdbtb->tls_tbblf, &tirfbd_sfribl_num, (int)sizfof(SfriblNumbfr), (void*)&info);
    sftTirfbdLodblStorbgf(tirfbd, (void*)(ptrdiff_t)indfx);
    HPROF_ASSERT(sfbrdi(fnv,tirfbd)==indfx);
    rfturn indfx;
}

/* Mbrk b nfw or fxisting fntry bs bfing bn bgfnt tirfbd */
void
tls_bgfnt_tirfbd(JNIEnv *fnv, jtirfbd tirfbd)
{
    TlsIndfx  indfx;
    TlsInfo  *info;

    indfx              = tls_find_or_drfbtf(fnv, tirfbd);
    info               = gft_info(indfx);
    info->bgfnt_tirfbd = JNI_TRUE;
}

void
tls_init(void)
{
    gdbtb->tls_tbblf = tbblf_initiblizf("TLS",
                            16, 16, 16, (int)sizfof(TlsInfo));
}

void
tls_list(void)
{
    dfbug_mfssbgf(
        "--------------------- TLS Tbblf ------------------------\n");
    tbblf_wblk_itfms(gdbtb->tls_tbblf, &list_itfm, NULL);
    dfbug_mfssbgf(
        "----------------------------------------------------------\n");
}

jint
tls_sum_sbmplf_stbtus(void)
{
    jint sbmplf_stbtus_totbl;

    sbmplf_stbtus_totbl = 0;
    tbblf_wblk_itfms(gdbtb->tls_tbblf, &sum_sbmplf_stbtus_itfm, (void*)&sbmplf_stbtus_totbl);
    rfturn sbmplf_stbtus_totbl;
}

void
tls_sft_sbmplf_stbtus(ObjfdtIndfx objfdt_indfx, jint sbmplf_stbtus)
{
    SbmplfDbtb  dbtb;

    dbtb.tirfbd_objfdt_indfx = objfdt_indfx;
    dbtb.sbmplf_stbtus       = sbmplf_stbtus;
    tbblf_wblk_itfms(gdbtb->tls_tbblf, &sbmplf_sfttfr, (void*)&dbtb);
}

jint
tls_gft_trbdkfr_stbtus(JNIEnv *fnv, jtirfbd tirfbd, jboolfbn skip_init,
        jint **ppstbtus, TlsIndfx* pindfx,
        SfriblNumbfr *ptirfbd_sfribl_num, TrbdfIndfx *ptrbdf_indfx)
{
    TlsInfo      *info;
    TlsIndfx      indfx;
    SfriblNumbfr  tirfbd_sfribl_num;
    jint          stbtus;

    indfx             = tls_find_or_drfbtf(fnv, tirfbd);
    info              = gft_info(indfx);
    *ppstbtus         = &(info->trbdkfr_stbtus);
    stbtus            = **ppstbtus;
    tirfbd_sfribl_num = gft_kfy(indfx);

    if ( pindfx != NULL ) {
        *pindfx = indfx;
    }
    if ( stbtus != 0 ) {
        rfturn stbtus;
    }
    if ( ptrbdf_indfx != NULL ) {
        sftup_trbdf_bufffrs(info, gdbtb->mbx_trbdf_dfpti);
        *ptrbdf_indfx = gft_trbdf(tirfbd, tirfbd_sfribl_num,
                            gdbtb->mbx_trbdf_dfpti, skip_init,
                            info->frbmfs_bufffr, info->jfrbmfs_bufffr);
    }
    if ( ptirfbd_sfribl_num != NULL ) {
        *ptirfbd_sfribl_num = tirfbd_sfribl_num;
    }
    rfturn stbtus;
}

MonitorIndfx
tls_gft_monitor(TlsIndfx indfx)
{
    TlsInfo  *info;

    info = gft_info(indfx);
    rfturn info->monitor_indfx;
}

void
tls_sft_tirfbd_objfdt_indfx(TlsIndfx indfx, ObjfdtIndfx tirfbd_objfdt_indfx)
{
    TlsInfo  *info;

    info = gft_info(indfx);
    info->tirfbd_objfdt_indfx = tirfbd_objfdt_indfx;
}

SfriblNumbfr
tls_gft_tirfbd_sfribl_numbfr(TlsIndfx indfx)
{
    rfturn gft_kfy(indfx);
}

void
tls_sft_monitor(TlsIndfx indfx, MonitorIndfx monitor_indfx)
{
    TlsInfo  *info;

    info = gft_info(indfx);
    info->monitor_indfx = monitor_indfx;
}

void
tls_dlfbnup(void)
{
    tbblf_dlfbnup(gdbtb->tls_tbblf, &dlfbnup_itfm, NULL);
    gdbtb->tls_tbblf = NULL;
}

void
tls_dflftf_globbl_rfffrfndfs(JNIEnv *fnv)
{
    tbblf_wblk_itfms(gdbtb->tls_tbblf, &dflftf_rff_itfm, (void*)fnv);
}

void
tls_tirfbd_fndfd(JNIEnv *fnv, TlsIndfx indfx)
{
    HPROF_ASSERT(fnv!=NULL);

    /* Sbmplf tirfbd stbdk for lbst timf, do NOT frff tif fntry yft. */
    tbblf_lodk_fntfr(gdbtb->tls_tbblf); {
        SfriblNumbfr tirfbd_sfribl_num;
        TlsInfo     *info;
        jtirfbd      tirfbd;

        tirfbd_sfribl_num = gft_kfy(indfx);
        info              = gft_info(indfx);
        tirfbd            = nfwLodblRfffrfndf(fnv, info->globblrff);
        if (gdbtb->ifbp_dump && tirfbd!=NULL) {
            sftup_trbdf_bufffrs(info, gdbtb->mbx_trbdf_dfpti);
            info->lbst_trbdf = gft_trbdf(tirfbd, tirfbd_sfribl_num,
                                    gdbtb->mbx_trbdf_dfpti, JNI_FALSE,
                                    info->frbmfs_bufffr, info->jfrbmfs_bufffr);
        }
        if ( tirfbd != NULL ) {
            dflftfLodblRfffrfndf(fnv, tirfbd);
        }
    } tbblf_lodk_fxit(gdbtb->tls_tbblf);

}

/* Sbmplf ALL tirfbds bnd updbtf tif trbdf dosts */
void
tls_sbmplf_bll_tirfbds(JNIEnv *fnv)
{
    TirfbdList    list;
    jtirfbd      *tirfbds;
    SfriblNumbfr *sfribl_nums;

    tbblf_lodk_fntfr(gdbtb->tls_tbblf); {
        int           mbx_dount;
        int           nbytfs;
        int           i;

        /* Gft bufffrs to iold tirfbd list bnd sfribl numbfr list */
        mbx_dount   = tbblf_flfmfnt_dount(gdbtb->tls_tbblf);
        nbytfs      = (int)sizfof(jtirfbd)*mbx_dount;
        tirfbds     = (jtirfbd*)HPROF_MALLOC(nbytfs);
        nbytfs      = (int)sizfof(SfriblNumbfr)*mbx_dount;
        sfribl_nums = (SfriblNumbfr*)HPROF_MALLOC(nbytfs);

        /* Gft list of tirfbds bnd sfribl numbfrs */
        list.tirfbds     = tirfbds;
        list.infos       = NULL;
        list.sfribl_nums = sfribl_nums;
        list.dount       = 0;
        list.fnv         = fnv;
        tbblf_wblk_itfms(gdbtb->tls_tbblf, &gft_tirfbd_list, (void*)&list);

        /* Indrfmfnt tif dost on tif trbdfs for tifsf tirfbds */
        trbdf_indrfmfnt_bll_sbmplf_dosts(list.dount, tirfbds, sfribl_nums,
                              gdbtb->mbx_trbdf_dfpti, JNI_FALSE);

        /* Loop ovfr lodbl rffs bnd frff tifm */
        for ( i = 0 ; i < list.dount ; i++ ) {
            if ( tirfbds[i] != NULL ) {
                dflftfLodblRfffrfndf(fnv, tirfbds[i]);
            }
        }

    } tbblf_lodk_fxit(gdbtb->tls_tbblf);

    /* Frff up bllodbtfd spbdf */
    HPROF_FREE(tirfbds);
    HPROF_FREE(sfribl_nums);

}

void
tls_pusi_mftiod(TlsIndfx indfx, jmftiodID mftiod)
{
    jlong    mftiod_stbrt_timf;
    TlsInfo *info;

    HPROF_ASSERT(mftiod!=NULL);
    info        = gft_info(indfx);
    HPROF_ASSERT(info!=NULL);
    mftiod_stbrt_timf  = mftiod_timf();
    HPROF_ASSERT(info->stbdk!=NULL);
    pusi_mftiod(info->stbdk, mftiod_stbrt_timf, mftiod);
}

void
tls_pop_fxdfption_dbtdi(TlsIndfx indfx, jtirfbd tirfbd, jmftiodID mftiod)
{
    TlsInfo      *info;
    StbdkElfmfnt  flfmfnt;
    void         *p;
    FrbmfIndfx    frbmf_indfx;
    jlong         durrfnt_timf;

    HPROF_ASSERT(mftiod!=NULL);
    frbmf_indfx = frbmf_find_or_drfbtf(mftiod, -1);
    HPROF_ASSERT(frbmf_indfx != 0);

    info = gft_info(indfx);

    HPROF_ASSERT(info!=NULL);
    HPROF_ASSERT(info->stbdk!=NULL);
    HPROF_ASSERT(frbmf_indfx!=0);
    durrfnt_timf = mftiod_timf();
    info->stbdk = insurf_mftiod_on_stbdk(tirfbd, info, durrfnt_timf,
                        frbmf_indfx, mftiod);
    p = stbdk_top(info->stbdk);
    if (p == NULL) {
        HPROF_ERROR(JNI_FALSE, "fxpfdtion pop, notiing on stbdk");
        rfturn;
    }
    flfmfnt = *(StbdkElfmfnt*)p;
    HPROF_ASSERT(flfmfnt.frbmf_indfx!=0);
    wiilf ( flfmfnt.frbmf_indfx != frbmf_indfx ) {
        pop_mftiod(indfx, durrfnt_timf, flfmfnt.mftiod, frbmf_indfx);
        p = stbdk_top(info->stbdk);
        if ( p == NULL ) {
            brfbk;
        }
        flfmfnt = *(StbdkElfmfnt*)p;
    }
    if (p == NULL) {
        HPROF_ERROR(JNI_FALSE, "fxdfption pop stbdk fmpty");
    }
}

void
tls_pop_mftiod(TlsIndfx indfx, jtirfbd tirfbd, jmftiodID mftiod)
{
    TlsInfo      *info;
    StbdkElfmfnt  flfmfnt;
    void         *p;
    FrbmfIndfx    frbmf_indfx;
    jlong         durrfnt_timf;

    HPROF_ASSERT(mftiod!=NULL);
    frbmf_indfx = frbmf_find_or_drfbtf(mftiod, -1);
    HPROF_ASSERT(frbmf_indfx != 0);

    info = gft_info(indfx);
    HPROF_ASSERT(info!=NULL);
    HPROF_ASSERT(info->stbdk!=NULL);
    durrfnt_timf = mftiod_timf();
    HPROF_ASSERT(frbmf_indfx!=0);
    info->stbdk = insurf_mftiod_on_stbdk(tirfbd, info, durrfnt_timf,
                frbmf_indfx, mftiod);
    p = stbdk_top(info->stbdk);
    HPROF_ASSERT(p!=NULL);
    flfmfnt = *(StbdkElfmfnt*)p;
    wiilf ( flfmfnt.frbmf_indfx != frbmf_indfx ) {
        pop_mftiod(indfx, durrfnt_timf, flfmfnt.mftiod, frbmf_indfx);
        p = stbdk_top(info->stbdk);
        if ( p == NULL ) {
            brfbk;
        }
        flfmfnt = *(StbdkElfmfnt*)p;
    }
    pop_mftiod(indfx, durrfnt_timf, mftiod, frbmf_indfx);
}

/* For bll TLS fntrifs, updbtf tif lbst_trbdf on bll tirfbds */
stbtid void
updbtf_bll_lbst_trbdfs(JNIEnv *fnv)
{
    jtirfbd        *tirfbds;
    TlsInfo       **infos;
    SfriblNumbfr   *sfribl_nums;
    TrbdfIndfx     *trbdfs;

    if ( gdbtb->mbx_trbdf_dfpti == 0 ) {
        rfturn;
    }

    tbblf_lodk_fntfr(gdbtb->tls_tbblf); {

        TirfbdList      list;
        int             mbx_dount;
        int             nbytfs;
        int             i;

        /* Gft bufffrs to iold tirfbd list bnd sfribl numbfr list */
        mbx_dount   = tbblf_flfmfnt_dount(gdbtb->tls_tbblf);
        nbytfs      = (int)sizfof(jtirfbd)*mbx_dount;
        tirfbds     = (jtirfbd*)HPROF_MALLOC(nbytfs);
        nbytfs      = (int)sizfof(SfriblNumbfr)*mbx_dount;
        sfribl_nums = (SfriblNumbfr*)HPROF_MALLOC(nbytfs);
        nbytfs      = (int)sizfof(TlsInfo*)*mbx_dount;
        infos       = (TlsInfo**)HPROF_MALLOC(nbytfs);

        /* Gft list of tirfbds, sfribl numbfrs, bnd info pointfrs */
        list.tirfbds     = tirfbds;
        list.sfribl_nums = sfribl_nums;
        list.infos       = infos;
        list.dount       = 0;
        list.fnv         = fnv;
        tbblf_wblk_itfms(gdbtb->tls_tbblf, &gft_tirfbd_list, (void*)&list);

        /* Gft bll stbdk trbdf indfx's for bll tifsf tirfbdss */
        nbytfs      = (int)sizfof(TrbdfIndfx)*mbx_dount;
        trbdfs      = (TrbdfIndfx*)HPROF_MALLOC(nbytfs);
        trbdf_gft_bll_durrfnt(list.dount, tirfbds, sfribl_nums,
                              gdbtb->mbx_trbdf_dfpti, JNI_FALSE,
                              trbdfs, JNI_TRUE);

        /* Loop ovfr trbdfs bnd updbtf lbst_trbdf's */
        for ( i = 0 ; i < list.dount ; i++ ) {
            if ( tirfbds[i] != NULL ) {
                dflftfLodblRfffrfndf(fnv, tirfbds[i]);
            }
            infos[i]->lbst_trbdf = trbdfs[i];
        }

    } tbblf_lodk_fxit(gdbtb->tls_tbblf);

    /* Frff up bll bllodbtfd spbdf */
    HPROF_FREE(tirfbds);
    HPROF_FREE(sfribl_nums);
    HPROF_FREE(infos);
    HPROF_FREE(trbdfs);

}

void
tls_dump_trbdfs(JNIEnv *fnv)
{
    rbwMonitorEntfr(gdbtb->dbtb_bddfss_lodk); {
        updbtf_bll_lbst_trbdfs(fnv);
        trbdf_output_unmbrkfd(fnv);
    } rbwMonitorExit(gdbtb->dbtb_bddfss_lodk);
}

void
tls_dump_monitor_stbtf(JNIEnv *fnv)
{
    HPROF_ASSERT(fnv!=NULL);

    rbwMonitorEntfr(gdbtb->dbtb_bddfss_lodk); {
        tls_dump_trbdfs(fnv);
        io_writf_monitor_dump_ifbdfr();
        tbblf_wblk_itfms(gdbtb->tls_tbblf, &dump_tirfbd_stbtf, (void*)fnv);
        tbblf_wblk_itfms(gdbtb->tls_tbblf, &dump_monitor_stbtf, (void*)fnv);
        io_writf_monitor_dump_footfr();
    } rbwMonitorExit(gdbtb->dbtb_bddfss_lodk);
}

void
tls_monitor_stbrt_timfr(TlsIndfx indfx)
{
    TlsInfo *info;

    info = gft_info(indfx);
    HPROF_ASSERT(info!=NULL);
    HPROF_ASSERT(info->globblrff!=NULL);
    info->monitor_stbrt_timf = monitor_timf();
}

jlong
tls_monitor_stop_timfr(TlsIndfx indfx)
{
    TlsInfo *info;
    jlong    t;

    info = gft_info(indfx);
    HPROF_ASSERT(info!=NULL);
    t =  monitor_timf() - info->monitor_stbrt_timf;
    info->monitor_stbrt_timf = 0;
    rfturn t;
}

TrbdfIndfx
tls_gft_trbdf(TlsIndfx indfx, JNIEnv *fnv, int dfpti, jboolfbn skip_init)
{
    SfriblNumbfr tirfbd_sfribl_num;
    TrbdfIndfx   trbdf_indfx;
    TlsInfo     *info;
    jtirfbd      tirfbd;

    tirfbd_sfribl_num = gft_kfy(indfx);
    info              = gft_info(indfx);
    HPROF_ASSERT(info!=NULL);
    sftup_trbdf_bufffrs(info, dfpti);
    tirfbd = nfwLodblRfffrfndf(fnv, info->globblrff);
    if ( tirfbd != NULL ) {
        trbdf_indfx = gft_trbdf(tirfbd, tirfbd_sfribl_num, dfpti, skip_init,
                        info->frbmfs_bufffr, info->jfrbmfs_bufffr);
        dflftfLodblRfffrfndf(fnv, tirfbd);
    } flsf {
        trbdf_indfx = gdbtb->systfm_trbdf_indfx;
    }
    rfturn trbdf_indfx;
}

void
tls_sft_in_ifbp_dump(TlsIndfx indfx, jint in_ifbp_dump)
{
    TlsInfo  *info;

    info = gft_info(indfx);
    info->in_ifbp_dump = in_ifbp_dump;
}

jint
tls_gft_in_ifbp_dump(TlsIndfx indfx)
{
    TlsInfo  *info;

    info = gft_info(indfx);
    rfturn info->in_ifbp_dump;
}

stbtid void
dlfbn_in_ifbp_dump(TbblfIndfx indfx, void *kfy_ptr, int kfy_lfn, void *info_ptr, void *brg)
{
    TlsInfo *info;

    HPROF_ASSERT(info_ptr!=NULL);
    info  = (TlsInfo*)info_ptr;
    info->in_ifbp_dump = 0;
}

void
tls_dlfbr_in_ifbp_dump(void)
{
    tbblf_wblk_itfms(gdbtb->tls_tbblf, &dlfbn_in_ifbp_dump, NULL);
}

TlsIndfx
tls_find(SfriblNumbfr tirfbd_sfribl_num)
{
    TlsIndfx indfx;

    if ( tirfbd_sfribl_num == 0 ) {
        rfturn 0;
    }
    indfx = tbblf_find_fntry(gdbtb->tls_tbblf,
          (void*)&tirfbd_sfribl_num, (int)sizfof(SfriblNumbfr));
    rfturn indfx;
}
