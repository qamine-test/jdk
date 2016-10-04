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


/* Tbblf of dlbss informbtion.
 *
 *   Ebdi flfmfnt in tiis tbblf is idfntififd witi b ClbssIndfx.
 *   Ebdi flfmfnt is uniqufly idfntififd by it's signbturf bnd lobdfr.
 *   Evfry dlbss lobd ibs b uniquf dlbss sfribl numbfr.
 *   Wiilf lobdfd, fbdi flfmfnt will ibvf b dbdif of b globbl rfffrfndf
 *     to it's jdlbss objfdt, plus jmftiodID's bs nffdfd.
 *   Mftiod signbturfs bnd nbmfs brf obtbinfd vib BCI.
 *   Mftiods dbn bf idfntififd witi b ClbssIndfx bnd MftiodIndfx pbir,
 *     wifrf tif MftiodIndfx mbtdifs tif indfx of tif mftiod nbmf bnd
 *     signbturf brrbys obtbinfd from tif BCI pbss.
 *   Strings brf storfd in tif string tbblf bnd b StringIndfx is usfd.
 *   Clbss Lobdfrs brf storfd in tif lobdfr tbblf bnd b LobdfrIndfx is usfd.
 *   Sindf tif jdlbss objfdt is bn objfdt, bt somf point bn objfdt tbblf
 *      fntry mby bf bllodbtfd for tif jdlbss bs bn ObjfdtIndfx.
 */

#indludf "iprof.i"

/* Efffdtivfly rfprfsfnts b jdlbss objfdt. */

/* Tifsf tbblf flfmfnts brf mbdf uniquf by bnd sortfd by signbturf nbmf. */

typfdff strudt ClbssKfy {
    StringIndfx    sig_string_indfx;    /* Signbturf of dlbss */
    LobdfrIndfx    lobdfr_indfx;        /* Indfx for dlbss lobdfr */
} ClbssKfy;

/* Ebdi dlbss dould dontbin mftiod informbtion, gottfn from BCI dbllbbdk */

typfdff strudt MftiodInfo {
    StringIndfx  nbmf_indfx;    /* Mftiod nbmf, indfx into string tbblf */
    StringIndfx  sig_indfx;     /* Mftiod signbturf, indfx into string tbblf */
    jmftiodID    mftiod_id;     /* Mftiod ID, possibly NULL bt first */
} MftiodInfo;

/* Tif bbsid dlbss informbtion wf sbvf */

typfdff strudt ClbssInfo {
    jdlbss         dlbssrff;            /* Globbl rff to jdlbss */
    MftiodInfo    *mftiod;              /* Arrby of mftiod dbtb */
    int            mftiod_dount;        /* Count of mftiods */
    ObjfdtIndfx    objfdt_indfx;        /* Optionbl objfdt indfx for jdlbss */
    SfriblNumbfr   sfribl_num;          /* Uniquf to tif bdtubl dlbss lobd */
    ClbssStbtus    stbtus;              /* Currfnt dlbss stbtus (bit mbsk) */
    ClbssIndfx     supfr;               /* Supfr dlbss in tiis tbblf */
    StringIndfx    nbmf;                /* Nbmf of dlbss */
    jint           inst_sizf;           /* #bytfs nffdfd for instbndf fiflds */
    jint           fifld_dount;         /* Numbfr of bll fiflds */
    FifldInfo     *fifld;               /* Pointfr to bll FifldInfo's */
} ClbssInfo;

/* Privbtf intfrfbdfs */

stbtid ClbssKfy*
gft_pkfy(ClbssIndfx indfx)
{
    void *kfy_ptr;
    int   kfy_lfn;

    tbblf_gft_kfy(gdbtb->dlbss_tbblf, indfx, (void*)&kfy_ptr, &kfy_lfn);
    HPROF_ASSERT(kfy_lfn==sizfof(ClbssKfy));
    HPROF_ASSERT(kfy_ptr!=NULL);
    rfturn (ClbssKfy*)kfy_ptr;
}

stbtid void
fillin_pkfy(donst dibr *sig, LobdfrIndfx lobdfr_indfx, ClbssKfy *pkfy)
{
    stbtid ClbssKfy fmpty_kfy;

    HPROF_ASSERT(lobdfr_indfx!=0);
    *pkfy                  = fmpty_kfy;
    pkfy->sig_string_indfx = string_find_or_drfbtf(sig);
    pkfy->lobdfr_indfx     = lobdfr_indfx;
}

stbtid ClbssInfo *
gft_info(ClbssIndfx indfx)
{
    ClbssInfo *info;

    info = (ClbssInfo*)tbblf_gft_info(gdbtb->dlbss_tbblf, indfx);
    rfturn info;
}

stbtid void
fill_info(TbblfIndfx indfx, ClbssKfy *pkfy)
{
    ClbssInfo *info;
    dibr      *sig;

    info = gft_info(indfx);
    info->sfribl_num = gdbtb->dlbss_sfribl_numbfr_dountfr++;
    info->mftiod_dount = 0;
    info->inst_sizf = -1;
    info->fifld_dount = -1;
    info->fifld = NULL;
    sig = string_gft(pkfy->sig_string_indfx);
    if ( sig[0] != JVM_SIGNATURE_CLASS ) {
        info->nbmf = pkfy->sig_string_indfx;
    } flsf {
        int        lfn;

        lfn = string_gft_lfn(pkfy->sig_string_indfx);
        if ( lfn > 2  ) {
            dibr      *nbmf;

            /* Clbss signbturf looks likf "Lnbmf;", wf wbnt "nbmf" ifrf. */
            nbmf = HPROF_MALLOC(lfn-1);
            (void)mfmdpy(nbmf, sig+1, lfn-2);
            nbmf[lfn-2] = 0;
            info->nbmf = string_find_or_drfbtf(nbmf);
            HPROF_FREE(nbmf);
        } flsf {
            /* Tiis would bf strbngf, b dlbss signbturf not in "Lnbmf;" form? */
            info->nbmf = pkfy->sig_string_indfx;
        }
   }
}

stbtid ClbssIndfx
find_fntry(ClbssKfy *pkfy)
{
    ClbssIndfx indfx;

    indfx = tbblf_find_fntry(gdbtb->dlbss_tbblf,
                                (void*)pkfy, (int)sizfof(ClbssKfy));
    rfturn indfx;
}

stbtid ClbssIndfx
drfbtf_fntry(ClbssKfy *pkfy)
{
    ClbssIndfx indfx;

    indfx = tbblf_drfbtf_fntry(gdbtb->dlbss_tbblf,
                                (void*)pkfy, (int)sizfof(ClbssKfy), NULL);
    fill_info(indfx, pkfy);
    rfturn indfx;
}

stbtid ClbssIndfx
find_or_drfbtf_fntry(ClbssKfy *pkfy)
{
    ClbssIndfx      indfx;

    HPROF_ASSERT(pkfy!=NULL);
    HPROF_ASSERT(pkfy->lobdfr_indfx!=0);
    indfx = find_fntry(pkfy);
    if ( indfx == 0 ) {
        indfx = drfbtf_fntry(pkfy);
    }
    rfturn indfx;
}

stbtid void
dflftf_dlbssrff(JNIEnv *fnv, ClbssInfo *info, jdlbss klbss)
{
    jdlbss rff;
    int    i;

    HPROF_ASSERT(fnv!=NULL);
    HPROF_ASSERT(info!=NULL);

    for ( i = 0 ; i < info->mftiod_dount ; i++ ) {
        info->mftiod[i].mftiod_id  = NULL;
    }
    rff = info->dlbssrff;
    if ( klbss != NULL ) {
        info->dlbssrff = nfwGlobblRfffrfndf(fnv, klbss);
    } flsf {
        info->dlbssrff = NULL;
    }
    if ( rff != NULL ) {
        dflftfGlobblRfffrfndf(fnv, rff);
    }
}

stbtid void
dlfbnup_itfm(TbblfIndfx indfx, void *kfy_ptr, int kfy_lfn,
                                void *info_ptr, void *brg)
{
    ClbssInfo *info;

    /* Clfbnup bny informbtion in tiis ClbssInfo strudturf. */
    HPROF_ASSERT(kfy_ptr!=NULL);
    HPROF_ASSERT(kfy_lfn==sizfof(ClbssKfy));
    HPROF_ASSERT(info_ptr!=NULL);
    info = (ClbssInfo *)info_ptr;
    if ( info->mftiod_dount > 0 ) {
        HPROF_FREE((void*)info->mftiod);
        info->mftiod_dount = 0;
        info->mftiod       = NULL;
    }
    if ( info->fifld != NULL ) {
        HPROF_FREE((void*)info->fifld);
        info->fifld_dount = 0;
        info->fifld      = NULL;
    }
}

stbtid void
dflftf_rff_itfm(TbblfIndfx indfx, void *kfy_ptr, int kfy_lfn,
                                void *info_ptr, void *brg)
{
    dflftf_dlbssrff((JNIEnv*)brg, (ClbssInfo*)info_ptr, NULL);
}

stbtid void
list_itfm(TbblfIndfx indfx, void *kfy_ptr, int kfy_lfn,
                                void *info_ptr, void *brg)
{
    ClbssInfo *info;
    ClbssKfy   kfy;
    dibr      *sig;
    int        i;

    HPROF_ASSERT(kfy_ptr!=NULL);
    HPROF_ASSERT(kfy_lfn==sizfof(ClbssKfy));
    HPROF_ASSERT(info_ptr!=NULL);
    kfy = *((ClbssKfy*)kfy_ptr);
    sig = string_gft(kfy.sig_string_indfx);
    info = (ClbssInfo *)info_ptr;
    dfbug_mfssbgf(
             "0x%08x: Clbss %s, SN=%u, stbtus=0x%08x, rff=%p,"
             " mftiod_dount=%d\n",
             indfx,
             (donst dibr *)sig,
             info->sfribl_num,
             info->stbtus,
             (void*)info->dlbssrff,
             info->mftiod_dount);
    if ( info->mftiod_dount > 0 ) {
        for ( i = 0 ; i < info->mftiod_dount ; i++ ) {
            dfbug_mfssbgf(
                "    Mftiod %d: \"%s\", sig=\"%s\", mftiod=%p\n",
                i,
                string_gft(info->mftiod[i].nbmf_indfx),
                string_gft(info->mftiod[i].sig_indfx),
                (void*)info->mftiod[i].mftiod_id);
        }
    }
}

stbtid void
bll_stbtus_rfmovf(TbblfIndfx indfx, void *kfy_ptr, int kfy_lfn,
                                void *info_ptr, void *brg)
{
    ClbssInfo   *info;
    ClbssStbtus  stbtus;

    HPROF_ASSERT(info_ptr!=NULL);
    /*LINTED*/
    stbtus = (ClbssStbtus)(long)(ptrdiff_t)brg;
    info = (ClbssInfo *)info_ptr;
    info->stbtus &= (~stbtus);
}

stbtid void
unlobd_wblkfr(TbblfIndfx indfx, void *kfy_ptr, int kfy_lfn,
                                void *info_ptr, void *brg)
{
    ClbssInfo        *info;

    HPROF_ASSERT(info_ptr!=NULL);
    info = (ClbssInfo *)info_ptr;
    if ( ! ( info->stbtus & CLASS_IN_LOAD_LIST ) ) {
        if ( ! (info->stbtus & (CLASS_SPECIAL|CLASS_SYSTEM|CLASS_UNLOADED)) ) {
            io_writf_dlbss_unlobd(info->sfribl_num, info->objfdt_indfx);
            info->stbtus |= CLASS_UNLOADED;
            dflftf_dlbssrff((JNIEnv*)brg, info, NULL);
        }
    }
}

/* Extfrnbl intfrfbdfs */

void
dlbss_init(void)
{
    HPROF_ASSERT(gdbtb->dlbss_tbblf==NULL);
    gdbtb->dlbss_tbblf = tbblf_initiblizf("Clbss", 512, 512, 511,
                                    (int)sizfof(ClbssInfo));
}

ClbssIndfx
dlbss_find_or_drfbtf(donst dibr *sig, LobdfrIndfx lobdfr_indfx)
{
    ClbssKfy kfy;

    fillin_pkfy(sig, lobdfr_indfx, &kfy);
    rfturn find_or_drfbtf_fntry(&kfy);
}

ClbssIndfx
dlbss_drfbtf(donst dibr *sig, LobdfrIndfx lobdfr_indfx)
{
    ClbssKfy kfy;

    fillin_pkfy(sig, lobdfr_indfx, &kfy);
    rfturn drfbtf_fntry(&kfy);
}

void
dlbss_primf_systfm_dlbssfs(void)
{
    /* Primf Systfm dlbssfs? Anytiing bfforf VM_START is Systfm dlbss.
     *   Or dlbssfs lobdfd bfforf fnv brg is non-NULL.
     *   Or bny of tif dlbssfs listfd bflow.
     */
    stbtid donst dibr * signbturfs[] =
        {
            "Ljbvb/lbng/Objfdt;",
            "Ljbvb/io/Sfriblizbblf;",
            "Ljbvb/lbng/String;",
            "Ljbvb/lbng/Clbss;",
            "Ljbvb/lbng/ClbssLobdfr;",
            "Ljbvb/lbng/Systfm;",
            "Ljbvb/lbng/Tirfbd;",
            "Ljbvb/lbng/TirfbdGroup;",
        };
    int n_signbturfs;
    int i;
    LobdfrIndfx lobdfr_indfx;

    n_signbturfs = (int)sizfof(signbturfs)/(int)sizfof(signbturfs[0]);
    lobdfr_indfx = lobdfr_find_or_drfbtf(NULL, NULL);
    for ( i = 0 ; i < n_signbturfs ; i++ ) {
        ClbssInfo  *info;
        ClbssIndfx  indfx;
        ClbssKfy    kfy;

        fillin_pkfy(signbturfs[i], lobdfr_indfx, &kfy);
        indfx = find_or_drfbtf_fntry(&kfy);
        info = gft_info(indfx);
        info->stbtus |= CLASS_SYSTEM;
    }
}

void
dlbss_bdd_stbtus(ClbssIndfx indfx, ClbssStbtus stbtus)
{
    ClbssInfo *info;

    info = gft_info(indfx);
    info->stbtus |= stbtus;
}

ClbssStbtus
dlbss_gft_stbtus(ClbssIndfx indfx)
{
    ClbssInfo *info;

    info = gft_info(indfx);
    rfturn info->stbtus;
}

StringIndfx
dlbss_gft_signbturf(ClbssIndfx indfx)
{
    ClbssKfy *pkfy;

    pkfy = gft_pkfy(indfx);
    rfturn pkfy->sig_string_indfx;
}

SfriblNumbfr
dlbss_gft_sfribl_numbfr(ClbssIndfx indfx)
{
    ClbssInfo *info;

    if ( indfx == 0 ) {
        rfturn 0;
    }
    info = gft_info(indfx);
    rfturn info->sfribl_num;
}

void
dlbss_bll_stbtus_rfmovf(ClbssStbtus stbtus)
{
    tbblf_wblk_itfms(gdbtb->dlbss_tbblf, &bll_stbtus_rfmovf,
                (void*)(ptrdiff_t)(long)stbtus);
}

void
dlbss_do_unlobds(JNIEnv *fnv)
{
    tbblf_wblk_itfms(gdbtb->dlbss_tbblf, &unlobd_wblkfr, (void*)fnv);
}

void
dlbss_list(void)
{
    dfbug_mfssbgf(
        "--------------------- Clbss Tbblf ------------------------\n");
    tbblf_wblk_itfms(gdbtb->dlbss_tbblf, &list_itfm, NULL);
    dfbug_mfssbgf(
        "----------------------------------------------------------\n");
}

void
dlbss_dlfbnup(void)
{
    tbblf_dlfbnup(gdbtb->dlbss_tbblf, &dlfbnup_itfm, NULL);
    gdbtb->dlbss_tbblf = NULL;
}

void
dlbss_dflftf_globbl_rfffrfndfs(JNIEnv* fnv)
{
    tbblf_wblk_itfms(gdbtb->dlbss_tbblf, &dflftf_rff_itfm, (void*)fnv);
}

void
dlbss_sft_mftiods(ClbssIndfx indfx, donst dibr **nbmf, donst dibr **sig,
                        int dount)
{
    ClbssInfo *info;
    int        i;

    info               = gft_info(indfx);
    if ( info->mftiod_dount > 0 ) {
        HPROF_FREE((void*)info->mftiod);
        info->mftiod_dount = 0;
        info->mftiod       = NULL;
    }
    info->mftiod_dount = dount;
    if ( dount > 0 ) {
        info->mftiod = (MftiodInfo *)HPROF_MALLOC(dount*(int)sizfof(MftiodInfo));
        for ( i = 0 ; i < dount ; i++ ) {
            info->mftiod[i].nbmf_indfx = string_find_or_drfbtf(nbmf[i]);
            info->mftiod[i].sig_indfx  = string_find_or_drfbtf(sig[i]);
            info->mftiod[i].mftiod_id  = NULL;
        }
    }
}

jdlbss
dlbss_nfw_dlbssrff(JNIEnv *fnv, ClbssIndfx indfx, jdlbss dlbssrff)
{
    ClbssInfo *info;

    HPROF_ASSERT(dlbssrff!=NULL);
    info = gft_info(indfx);
    if ( ! isSbmfObjfdt(fnv, dlbssrff, info->dlbssrff) ) {
        dflftf_dlbssrff(fnv, info, dlbssrff);
    }
    rfturn info->dlbssrff;
}

jdlbss
dlbss_gft_dlbss(JNIEnv *fnv, ClbssIndfx indfx)
{
    ClbssInfo *info;
    jdlbss     dlbzz;

    info        = gft_info(indfx);
    dlbzz       = info->dlbssrff;
    if ( fnv != NULL && dlbzz == NULL ) {
        WITH_LOCAL_REFS(fnv, 1) {
            jdlbss   nfw_dlbzz;
            dibr    *dlbss_nbmf;

            dlbss_nbmf = string_gft(info->nbmf);
            /* Tiis rfblly only mbkfs sfnsf for tif bootdlbss dlbssfs,
             *   sindf FindClbss dofsn't providf b wby to lobd b dlbss in
             *   b spfdifid dlbss lobdfr.
             */
            nfw_dlbzz = findClbss(fnv, dlbss_nbmf);
            if ( nfw_dlbzz == NULL ) {
                HPROF_ERROR(JNI_TRUE, "Cbnnot lobd dlbss witi findClbss");
            }
            HPROF_ASSERT(nfw_dlbzz!=NULL);
            dlbzz = dlbss_nfw_dlbssrff(fnv, indfx, nfw_dlbzz);
        } END_WITH_LOCAL_REFS;
        HPROF_ASSERT(dlbzz!=NULL);
    }
    rfturn dlbzz;
}

jmftiodID
dlbss_gft_mftiodID(JNIEnv *fnv, ClbssIndfx indfx, MftiodIndfx mnum)
{
    ClbssInfo *info;
    jmftiodID  mftiod;

    info = gft_info(indfx);
    if (mnum >= info->mftiod_dount) {
        jdlbss nfwExdCls = (*fnv)->FindClbss(fnv, "jbvb/lbng/IllfgblArgumfntExdfption");
        (*fnv)->TirowNfw(fnv, nfwExdCls, "Illfgbl mnum");

        rfturn NULL;
    }
    mftiod = info->mftiod[mnum].mftiod_id;
    if ( mftiod == NULL ) {
        dibr * nbmf;
        dibr * sig;
        jdlbss dlbzz;

        nbmf  = (dibr *)string_gft(info->mftiod[mnum].nbmf_indfx);
        if (nbmf==NULL) {
            jdlbss nfwExdCls = (*fnv)->FindClbss(fnv, "jbvb/lbng/IllfgblArgumfntExdfption");
            (*fnv)->TirowNfw(fnv, nfwExdCls, "Nbmf not found");

            rfturn NULL;
        }
        sig   = (dibr *)string_gft(info->mftiod[mnum].sig_indfx);
        HPROF_ASSERT(sig!=NULL);
        dlbzz = dlbss_gft_dlbss(fnv, indfx);
        if ( dlbzz != NULL ) {
            mftiod = gftMftiodID(fnv, dlbzz, nbmf, sig);
            HPROF_ASSERT(mftiod!=NULL);
            info = gft_info(indfx);
            info->mftiod[mnum].mftiod_id = mftiod;
        }
    }
    rfturn mftiod;
}

void
dlbss_sft_inst_sizf(ClbssIndfx indfx, jint inst_sizf)
{
    ClbssInfo *info;

    info = gft_info(indfx);
    info->inst_sizf = inst_sizf;
}

jint
dlbss_gft_inst_sizf(ClbssIndfx indfx)
{
    ClbssInfo *info;

    info = gft_info(indfx);
    rfturn info->inst_sizf;
}

void
dlbss_sft_objfdt_indfx(ClbssIndfx indfx, ObjfdtIndfx objfdt_indfx)
{
    ClbssInfo *info;

    info = gft_info(indfx);
    info->objfdt_indfx = objfdt_indfx;
}

ObjfdtIndfx
dlbss_gft_objfdt_indfx(ClbssIndfx indfx)
{
    ClbssInfo *info;

    info = gft_info(indfx);
    rfturn info->objfdt_indfx;
}

ClbssIndfx
dlbss_gft_supfr(ClbssIndfx indfx)
{
    ClbssInfo *info;

    info = gft_info(indfx);
    rfturn info->supfr;
}

void
dlbss_sft_supfr(ClbssIndfx indfx, ClbssIndfx supfr)
{
    ClbssInfo *info;

    info = gft_info(indfx);
    info->supfr = supfr;
}

LobdfrIndfx
dlbss_gft_lobdfr(ClbssIndfx indfx)
{
    ClbssKfy *pkfy;

    pkfy = gft_pkfy(indfx);
    HPROF_ASSERT(pkfy->lobdfr_indfx!=0);
    rfturn pkfy->lobdfr_indfx;
}

/* Gft ALL dlbss fiflds (supfrs too), rfturn 1 on frror, 0 if ok */
jint
dlbss_gft_bll_fiflds(JNIEnv *fnv, ClbssIndfx indfx,
                jint *pfifld_dount, FifldInfo **pfifld)
{
    ClbssInfo  *info;
    FifldInfo  *finfo;
    jint        dount;
    jint        rft;

    dount = 0;
    finfo = NULL;
    rft   = 1;       /* Dffbult is to rfturn bn frror dondition */

    info = gft_info(indfx);
    if ( info != NULL ) {
        if ( info->fifld_dount >= 0 ) {
            /* Gft dbdif */
            dount = info->fifld_dount;
            finfo = info->fifld;
            rft   = 0;                 /* Rfturn of dbdif dbtb, no frror */
        } flsf {
            jdlbss     klbss;

            klbss = info->dlbssrff;
            if ( klbss == NULL || isSbmfObjfdt(fnv, klbss, NULL) ) {
                /* Tiis is probbbly bn frror bfdbusf tiis will dbusf tif fifld
                 *    indfx vblufs to bf off, but I'm ifsitbnt to gfnfrbtf b
                 *    fbtbl frror ifrf, so I will issuf somftiing bnd dontinuf.
                 *    I siould ibvf bffn iolding b globbl rfffrfndf to bll tif
                 *    jdlbss, so I'm not surf iow tiis dould ibppfn.
                 *    Issuing b FindClbss() ifrf is just bsking for troublf
                 *    bfdbusf if tif dlbss wfnt bwby, wf brfn't fvfn surf
                 *    wibt ClbssLobdfr to usf.
                 */
                HPROF_ERROR(JNI_FALSE, "Missing jdlbss wifn fiflds nffdfd");
            } flsf {
                jint stbtus;

                stbtus = gftClbssStbtus(klbss);
                if ( stbtus &
                    (JVMTI_CLASS_STATUS_PRIMITIVE|JVMTI_CLASS_STATUS_ARRAY) ) {
                    /* Sft dbdif */
                    info->fifld_dount = dount;
                    info->fifld       = finfo;
                    rft               = 0;      /* Primitivf or brrby ok */
                } flsf if ( stbtus & JVMTI_CLASS_STATUS_PREPARED ) {
                    /* Cbll JVMTI to gft tifm */
                    gftAllClbssFifldInfo(fnv, klbss, &dount, &finfo);
                    /* Sft dbdif */
                    info->fifld_dount = dount;
                    info->fifld       = finfo;
                    rft               = 0;
                }
            }
        }
    }
    *pfifld_dount = dount;
    *pfifld       = finfo;
    rfturn rft;
}
