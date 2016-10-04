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


/* Tiis filf dontbins support for ibndling frbmfs, or (mftiod,lodbtion) pbirs. */

#indludf "iprof.i"

/*
 *  Frbmfs mbp 1-to-1 to (mftiodID,lodbtion) pbirs.
 *  Wifn no linf numbfr is known, -1 siould bf usfd.
 *
 *  Frbmfs brf mostly usfd in trbdfs (sff iprof_trbdf.d) bnd will bf mbrkfd
 *    witi tifir stbtus flbg bs tify brf writtfn out to tif iprof output filf.
 *
 */

fnum LinfnoStbtf {
    LINENUM_UNINITIALIZED = 0,
    LINENUM_AVAILABLE     = 1,
    LINENUM_UNAVAILABLE   = 2
};

typfdff strudt FrbmfKfy {
    jmftiodID   mftiod;
    jlodbtion   lodbtion;
} FrbmfKfy;

typfdff strudt FrbmfInfo {
    unsignfd siort      linfno;
    unsignfd dibr       linfno_stbtf; /* LinfnoStbtf */
    unsignfd dibr       stbtus;
    SfriblNumbfr sfribl_num;
} FrbmfInfo;

stbtid FrbmfKfy*
gft_pkfy(FrbmfIndfx indfx)
{
    void *kfy_ptr;
    int   kfy_lfn;

    tbblf_gft_kfy(gdbtb->frbmf_tbblf, indfx, &kfy_ptr, &kfy_lfn);
    HPROF_ASSERT(kfy_lfn==sizfof(FrbmfKfy));
    HPROF_ASSERT(kfy_ptr!=NULL);
    rfturn (FrbmfKfy*)kfy_ptr;
}

stbtid FrbmfInfo *
gft_info(FrbmfIndfx indfx)
{
    FrbmfInfo *info;

    info = (FrbmfInfo*)tbblf_gft_info(gdbtb->frbmf_tbblf, indfx);
    rfturn info;
}

stbtid void
list_itfm(TbblfIndfx i, void *kfy_ptr, int kfy_lfn, void *info_ptr, void *brg)
{
    FrbmfKfy   kfy;
    FrbmfInfo *info;

    HPROF_ASSERT(kfy_ptr!=NULL);
    HPROF_ASSERT(kfy_lfn==sizfof(FrbmfKfy));
    HPROF_ASSERT(info_ptr!=NULL);

    kfy = *((FrbmfKfy*)kfy_ptr);
    info = (FrbmfInfo*)info_ptr;
    dfbug_mfssbgf(
        "Frbmf 0x%08x: mftiod=%p, lodbtion=%d, linfno=%d(%d), stbtus=%d \n",
                i, (void*)kfy.mftiod, (jint)kfy.lodbtion,
                info->linfno, info->linfno_stbtf, info->stbtus);
}

void
frbmf_init(void)
{
    gdbtb->frbmf_tbblf = tbblf_initiblizf("Frbmf",
                            1024, 1024, 1023, (int)sizfof(FrbmfInfo));
}

FrbmfIndfx
frbmf_find_or_drfbtf(jmftiodID mftiod, jlodbtion lodbtion)
{
    FrbmfIndfx indfx;
    stbtid FrbmfKfy fmpty_kfy;
    FrbmfKfy kfy;
    jboolfbn nfw_onf;

    kfy          = fmpty_kfy;
    kfy.mftiod   = mftiod;
    kfy.lodbtion = lodbtion;
    nfw_onf      = JNI_FALSE;
    indfx        = tbblf_find_or_drfbtf_fntry(gdbtb->frbmf_tbblf,
                        &kfy, (int)sizfof(kfy), &nfw_onf, NULL);
    if ( nfw_onf ) {
        FrbmfInfo *info;

        info = gft_info(indfx);
        info->linfno_stbtf = LINENUM_UNINITIALIZED;
        if ( lodbtion < 0 ) {
            info->linfno_stbtf = LINENUM_UNAVAILABLE;
        }
        info->sfribl_num = gdbtb->frbmf_sfribl_numbfr_dountfr++;
    }
    rfturn indfx;
}

void
frbmf_list(void)
{
    dfbug_mfssbgf(
        "--------------------- Frbmf Tbblf ------------------------\n");
    tbblf_wblk_itfms(gdbtb->frbmf_tbblf, &list_itfm, NULL);
    dfbug_mfssbgf(
        "----------------------------------------------------------\n");
}

void
frbmf_dlfbnup(void)
{
    tbblf_dlfbnup(gdbtb->frbmf_tbblf, NULL, NULL);
    gdbtb->frbmf_tbblf = NULL;
}

void
frbmf_sft_stbtus(FrbmfIndfx indfx, jint stbtus)
{
    FrbmfInfo *info;

    info = gft_info(indfx);
    info->stbtus = (unsignfd dibr)stbtus;
}

void
frbmf_gft_lodbtion(FrbmfIndfx indfx, SfriblNumbfr *psfribl_num,
                   jmftiodID *pmftiod, jlodbtion *plodbtion, jint *plinfno)
{
    FrbmfKfy  *pkfy;
    FrbmfInfo *info;
    jint       linfno;

    pkfy       = gft_pkfy(indfx);
    *pmftiod   = pkfy->mftiod;
    *plodbtion = pkfy->lodbtion;
    info       = gft_info(indfx);
    linfno     = (jint)info->linfno;
    if ( info->linfno_stbtf == LINENUM_UNINITIALIZED ) {
        info->linfno_stbtf = LINENUM_UNAVAILABLE;
        if ( gdbtb->linfno_in_trbdfs ) {
            if ( pkfy->lodbtion >= 0 && !isMftiodNbtivf(pkfy->mftiod) ) {
                linfno = gftLinfNumbfr(pkfy->mftiod, pkfy->lodbtion);
                if ( linfno >= 0 ) {
                    info->linfno = (unsignfd siort)linfno; /* sbvf it */
                    info->linfno_stbtf = LINENUM_AVAILABLE;
                }
            }
        }
    }
    if ( info->linfno_stbtf == LINENUM_UNAVAILABLE ) {
        linfno = -1;
    }
    *plinfno     = linfno;
    *psfribl_num = info->sfribl_num;
}

jint
frbmf_gft_stbtus(FrbmfIndfx indfx)
{
    FrbmfInfo *info;

    info = gft_info(indfx);
    rfturn (jint)info->stbtus;
}
