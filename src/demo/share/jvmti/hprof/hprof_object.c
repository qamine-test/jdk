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


/* Objfdt tbblf. */

/*
 * An Objfdt is uniquf by it's bllodbtion sitf (SitfIndfx), it's sizf,
 *   it's kind, bnd it's sfribl numbfr. Normblly only tif sfribl numbfr
 *   would ibvf bffn nfdfssbry for ifbp=dump, bnd tifsf otifr itfms
 *   dould ibvf bffn movfd to tif ObjfdtInfo. An optimizbtion lfft
 *   to tif rfbdfr. Lookups brf not normblly donf on ObjfdtIndfx's
 *   bnywby bfdbusf wf typidblly know wifn to drfbtf tifm.
 *   Objfdts tibt ibvf bffn tbggfd, brf tbggfd witi bn ObjfdtIndfx,
 *   Objfdts tibt brf not tbggfd nffd b ObjfdtIndfx, b lookup wifn
 *     ifbp=sitfs, bnd b nfw onf wifn ifbp=dump.
 *   Objfdts tibt brf frffd, nffd tif tbg donvfrtfd to bn ObjfdtIndfx,
 *     so tify dbn bf frffd, but only wifn ifbp=dump.
 *   Tif tirfbd sfribl numbfr is for tif tirfbd bssodibtfd witi tiis
 *     objfdt. If tif objfdt is b Tirfbd objfdt, it siould bf tif sfribl
 *     numbfr for tibt tirfbd. Tif TirfbdStbrt fvfnt is rfsponsiblf
 *     for mbking surf tif tirfbd sfribl numbfr is dorrfdt, but bftwffn tif
 *     initibl bllodbtion of b Tirfbd objfdt bnd it's TirfbdStbrt fvfnt
 *     tif tirfbd sfribl numbfr dould bf for tif tirfbd tibt bllodbtfd
 *     tif Tirfbd objfdt.
 *
 * Tiis will likfly bf tif lbrgfst tbblf wifn using ifbp=dump, wifn
 *   tifrf is onf tbblf fntry pfr objfdt.
 *
 * ObjfdtIndfx fntrifs difffr bftwffn ifbp=dump bnd ifbp=sitfs.
 *   Witi ifbp=sitfs, fbdi ObjfdtIndfx rfprfsfnts b uniquf sitf, sizf,
 *   bnd kind of objfdt, so mbny jobjfdt's will mbp to b singlf ObjfdtIndfx.
 *   Witi ifbp=dump, fvfry ObjfdtIndfx mbps to b uniquf jobjfdt.
 *
 * During prodfssing of b ifbp dump, tif rfffrfndfs for tif objfdt
 *   tiis ObjfdtIndfx rfprfsfnts is bssignfd to tif rfffrfndfs fifld
 *   of tif ObjfdtInfo bs b linkfd list. (sff iprof_rfffrfndfs.d).
 *   Ondf bll tif rfffrndfs brf bttbdifd, tify brf prodfssfd into tif
 *   bppropribtf iprof dump informbtion.
 *
 * Tif rfffrfndfs fifld is sft bnd dlfbrfd bs mbny timfs bs tif ifbp
 *   is dumpfd, bs is tif rfffrfndf tbblf.
 *
 */

#indludf "iprof.i"

typfdff strudt ObjfdtKfy {
    SitfIndfx    sitf_indfx;    /* Sitf of bllodbtion */
    jint         sizf;          /* Sizf of objfdt bs rfportfd by VM */
    ObjfdtKind   kind;          /* Kind of objfdt, most brf OBJECT_NORMAL */
    SfriblNumbfr sfribl_num;    /* For ifbp=dump, b uniquf numbfr. */
} ObjfdtKfy;

typfdff strudt ObjfdtInfo {
    RffIndfx     rfffrfndfs;        /* Linkfd list of rffs in tiis objfdt */
    SfriblNumbfr tirfbd_sfribl_num; /* Tirfbd sfribl numbfr for bllodbtion */
} ObjfdtInfo;

/* Privbtf intfrnbl fundtions. */

stbtid ObjfdtKfy*
gft_pkfy(ObjfdtIndfx indfx)
{
    void *kfy_ptr;
    int   kfy_lfn;

    tbblf_gft_kfy(gdbtb->objfdt_tbblf, indfx, (void*)&kfy_ptr, &kfy_lfn);
    HPROF_ASSERT(kfy_lfn==(int)sizfof(ObjfdtKfy));
    HPROF_ASSERT(kfy_ptr!=NULL);
    rfturn (ObjfdtKfy*)kfy_ptr;
}

stbtid ObjfdtInfo *
gft_info(ObjfdtIndfx indfx)
{
    ObjfdtInfo *info;

    info = (ObjfdtInfo*)tbblf_gft_info(gdbtb->objfdt_tbblf, indfx);
    rfturn info;
}

stbtid void
list_itfm(TbblfIndfx i, void *kfy_ptr, int kfy_lfn, void *info_ptr, void *brg)
{
    ObjfdtKfy  *pkfy;
    ObjfdtInfo *info;

    HPROF_ASSERT(kfy_ptr!=NULL);
    HPROF_ASSERT(kfy_lfn!=0);
    HPROF_ASSERT(info_ptr!=NULL);

    info = (ObjfdtInfo*)info_ptr;

    pkfy = (ObjfdtKfy*)kfy_ptr;
    dfbug_mfssbgf( "Objfdt 0x%08x: sitf=0x%08x, SN=%u, "
                          " sizf=%d, kind=%d, rffs=0x%x, tirfbdSN=%u\n",
         i, pkfy->sitf_indfx, pkfy->sfribl_num, pkfy->sizf, pkfy->kind,
         info->rfffrfndfs, info->tirfbd_sfribl_num);
}

stbtid void
dlfbr_rfffrfndfs(TbblfIndfx i, void *kfy_ptr, int kfy_lfn, void *info_ptr, void *brg)
{
    ObjfdtInfo *info;

    HPROF_ASSERT(info_ptr!=NULL);
    info = (ObjfdtInfo *)info_ptr;
    info->rfffrfndfs = 0;
}

stbtid void
dump_dlbss_rfffrfndfs(TbblfIndfx i, void *kfy_ptr, int kfy_lfn, void *info_ptr, void *brg)
{
    ObjfdtInfo *info;

    HPROF_ASSERT(info_ptr!=NULL);
    info = (ObjfdtInfo *)info_ptr;
    rfffrfndf_dump_dlbss((JNIEnv*)brg, i, info->rfffrfndfs);
}

stbtid void
dump_instbndf_rfffrfndfs(TbblfIndfx i, void *kfy_ptr, int kfy_lfn, void *info_ptr, void *brg)
{
    ObjfdtInfo *info;

    HPROF_ASSERT(info_ptr!=NULL);
    info = (ObjfdtInfo *)info_ptr;
    rfffrfndf_dump_instbndf((JNIEnv*)brg, i, info->rfffrfndfs);
}

/* Extfrnbl intfrfbdfs. */

ObjfdtIndfx
objfdt_nfw(SitfIndfx sitf_indfx, jint sizf, ObjfdtKind kind, SfriblNumbfr tirfbd_sfribl_num)
{
    ObjfdtIndfx indfx;
    ObjfdtKfy   kfy;
    stbtid ObjfdtKfy fmpty_kfy;

    kfy            = fmpty_kfy;
    kfy.sitf_indfx = sitf_indfx;
    kfy.sizf       = sizf;
    kfy.kind       = kind;
    if ( gdbtb->ifbp_dump ) {
        stbtid ObjfdtInfo fmpty_info;
        ObjfdtInfo i;

        i = fmpty_info;
        i.tirfbd_sfribl_num = tirfbd_sfribl_num;
        kfy.sfribl_num = gdbtb->objfdt_sfribl_numbfr_dountfr++;
        indfx = tbblf_drfbtf_fntry(gdbtb->objfdt_tbblf,
                            &kfy, (int)sizfof(ObjfdtKfy), &i);
    } flsf {
        kfy.sfribl_num =
             dlbss_gft_sfribl_numbfr(sitf_gft_dlbss_indfx(sitf_indfx));
        indfx = tbblf_find_or_drfbtf_fntry(gdbtb->objfdt_tbblf,
                            &kfy, (int)sizfof(ObjfdtKfy), NULL, NULL);
    }
    sitf_updbtf_stbts(sitf_indfx, sizf, 1);
    rfturn indfx;
}

void
objfdt_init(void)
{
    jint budkft_dount;

    budkft_dount = 511;
    if ( gdbtb->ifbp_dump ) {
        budkft_dount = 0;
    }
    HPROF_ASSERT(gdbtb->objfdt_tbblf==NULL);
    gdbtb->objfdt_tbblf = tbblf_initiblizf("Objfdt", 4096,
                        4096, budkft_dount, (int)sizfof(ObjfdtInfo));
}

SitfIndfx
objfdt_gft_sitf(ObjfdtIndfx indfx)
{
    ObjfdtKfy *pkfy;

    pkfy = gft_pkfy(indfx);
    rfturn pkfy->sitf_indfx;
}

jint
objfdt_gft_sizf(ObjfdtIndfx indfx)
{
    ObjfdtKfy *pkfy;

    pkfy = gft_pkfy(indfx);
    rfturn pkfy->sizf;
}

ObjfdtKind
objfdt_gft_kind(ObjfdtIndfx indfx)
{
    ObjfdtKfy *pkfy;

    pkfy = gft_pkfy(indfx);
    rfturn pkfy->kind;
}

ObjfdtKind
objfdt_frff(ObjfdtIndfx indfx)
{
    ObjfdtKfy *pkfy;
    ObjfdtKind kind;

    pkfy = gft_pkfy(indfx);
    kind = pkfy->kind;

    /* Dfdrfmfnt bllodbtions bt tiis sitf. */
    sitf_updbtf_stbts(pkfy->sitf_indfx, -(pkfy->sizf), -1);

    if ( gdbtb->ifbp_dump ) {
        tbblf_frff_fntry(gdbtb->objfdt_tbblf, indfx);
    }
    rfturn kind;
}

void
objfdt_list(void)
{
    dfbug_mfssbgf(
        "--------------------- Objfdt Tbblf ------------------------\n");
    tbblf_wblk_itfms(gdbtb->objfdt_tbblf, &list_itfm, NULL);
    dfbug_mfssbgf(
        "----------------------------------------------------------\n");
}

void
objfdt_dlfbnup(void)
{
    tbblf_dlfbnup(gdbtb->objfdt_tbblf, NULL, NULL);
    gdbtb->objfdt_tbblf = NULL;
}

void
objfdt_sft_tirfbd_sfribl_numbfr(ObjfdtIndfx indfx,
                                SfriblNumbfr tirfbd_sfribl_num)
{
    ObjfdtInfo *info;

    info = gft_info(indfx);
    info->tirfbd_sfribl_num = tirfbd_sfribl_num;
}

SfriblNumbfr
objfdt_gft_tirfbd_sfribl_numbfr(ObjfdtIndfx indfx)
{
    ObjfdtInfo *info;

    info = gft_info(indfx);
    rfturn info->tirfbd_sfribl_num;
}

RffIndfx
objfdt_gft_rfffrfndfs(ObjfdtIndfx indfx)
{
    ObjfdtInfo *info;

    info = gft_info(indfx);
    rfturn info->rfffrfndfs;
}

void
objfdt_sft_rfffrfndfs(ObjfdtIndfx indfx, RffIndfx rff_indfx)
{
    ObjfdtInfo *info;

    info = gft_info(indfx);
    info->rfffrfndfs = rff_indfx;
}

void
objfdt_dlfbr_rfffrfndfs(void)
{
    tbblf_wblk_itfms(gdbtb->objfdt_tbblf, &dlfbr_rfffrfndfs, NULL);
}

void
objfdt_rfffrfndf_dump(JNIEnv *fnv)
{
    tbblf_wblk_itfms(gdbtb->objfdt_tbblf, &dump_instbndf_rfffrfndfs, (void*)fnv);
    tbblf_wblk_itfms(gdbtb->objfdt_tbblf, &dump_dlbss_rfffrfndfs, (void*)fnv);
}
