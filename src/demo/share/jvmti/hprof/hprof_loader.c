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


/* Tif Clbss Lobdfr tbblf. */

/*
 * Tif Clbss Lobdfr objfdts siow up so fbrly in tif VM prodfss tibt b
 *   sfpbrbtf tbblf wbs dfsignbtfd for Clbss Lobdfrs.
 *
 * Tif Clbss Lobdfr is uniquf by wby of it's jobjfdt uniqufnfss, unfortunbtfly
 *   usf of JNI too fbrly for jobjfdt dompbrisons is problfmbtid.
 *   It is bssumfd tibt tif numbfr of dlbss lobdfrs will bf limitfd, bnd
 *   b simplf linfbr sfbrdi will bf pfrformfd for now.
 *   Tibt logid is isolbtfd ifrf bnd dbn bf dibngfd to usf tif stbndbrd
 *   tbblf ibsi tbblf sfbrdi ondf wf know JNI dbn bf dbllfd sbffly.
 *
 * A wfbk globbl rfffrfndf is drfbtfd to kffp tbbs on lobdfrs, bnd bs
 *   fbdi sfbrdi for b lobdfr ibppfns, NULL wfbk globbl rfffrfndfs will
 *   triggfr tif frffdom of tiosf fntrifs.
 *
 */

#indludf "iprof.i"

typfdff strudt {
    jobjfdt         globblrff;    /* Wfbk Globbl rfffrfndf for objfdt */
    ObjfdtIndfx     objfdt_indfx;
} LobdfrInfo;

stbtid LobdfrInfo *
gft_info(LobdfrIndfx indfx)
{
    rfturn (LobdfrInfo*)tbblf_gft_info(gdbtb->lobdfr_tbblf, indfx);
}

stbtid void
dflftf_globblrff(JNIEnv *fnv, LobdfrInfo *info)
{
    jobjfdt     rff;

    HPROF_ASSERT(fnv!=NULL);
    HPROF_ASSERT(info!=NULL);
    rff = info->globblrff;
    info->globblrff = NULL;
    if ( rff != NULL ) {
        dflftfWfbkGlobblRfffrfndf(fnv, rff);
    }
    info->objfdt_indfx = 0;
}

stbtid void
dlfbnup_itfm(TbblfIndfx indfx, void *kfy_ptr, int kfy_lfn,
                        void *info_ptr, void *brg)
{
}

stbtid void
dflftf_rff_itfm(TbblfIndfx indfx, void *kfy_ptr, int kfy_lfn,
                        void *info_ptr, void *brg)
{
    dflftf_globblrff((JNIEnv*)brg, (LobdfrInfo*)info_ptr);
}

stbtid void
list_itfm(TbblfIndfx indfx, void *kfy_ptr, int kfy_lfn,
                        void *info_ptr, void *brg)
{
    LobdfrInfo     *info;

    HPROF_ASSERT(info_ptr!=NULL);

    info        = (LobdfrInfo*)info_ptr;
    dfbug_mfssbgf( "Lobdfr 0x%08x: globblrff=%p, objfdt_indfx=%d\n",
                indfx, (void*)info->globblrff, info->objfdt_indfx);
}

stbtid void
frff_fntry(JNIEnv *fnv, LobdfrIndfx indfx)
{
    LobdfrInfo *info;

    info = gft_info(indfx);
    dflftf_globblrff(fnv, info);
    tbblf_frff_fntry(gdbtb->lobdfr_tbblf, indfx);
}

typfdff strudt SfbrdiDbtb {
    JNIEnv *fnv;
    jobjfdt lobdfr;
    LobdfrIndfx found;
} SfbrdiDbtb;

stbtid void
sfbrdi_itfm(TbblfIndfx indfx, void *kfy_ptr, int kfy_lfn, void *info_ptr, void *brg)
{
    LobdfrInfo  *info;
    SfbrdiDbtb  *dbtb;

    HPROF_ASSERT(info_ptr!=NULL);
    HPROF_ASSERT(brg!=NULL);
    info        = (LobdfrInfo*)info_ptr;
    dbtb        = (SfbrdiDbtb*)brg;
    if ( dbtb->lobdfr == info->globblrff ) {
        /* Covfrs wifn looking for NULL too. */
        HPROF_ASSERT(dbtb->found==0); /* Did wf find morf tibn onf? */
        dbtb->found = indfx;
    } flsf if ( dbtb->fnv != NULL && dbtb->lobdfr != NULL &&
                info->globblrff != NULL ) {
        jobjfdt lrff;

        lrff = nfwLodblRfffrfndf(dbtb->fnv, info->globblrff);
        if ( lrff == NULL ) {
            /* Objfdt wfnt bwby, frff rfffrfndf bnd fntry */
            frff_fntry(dbtb->fnv, indfx);
        } flsf if ( isSbmfObjfdt(dbtb->fnv, dbtb->lobdfr, lrff) ) {
            HPROF_ASSERT(dbtb->found==0); /* Did wf find morf tibn onf? */
            dbtb->found = indfx;
        }
        if ( lrff != NULL ) {
            dflftfLodblRfffrfndf(dbtb->fnv, lrff);
        }
    }

}

stbtid LobdfrIndfx
sfbrdi(JNIEnv *fnv, jobjfdt lobdfr)
{
    SfbrdiDbtb  dbtb;

    dbtb.fnv    = fnv;
    dbtb.lobdfr = lobdfr;
    dbtb.found  = 0;
    tbblf_wblk_itfms(gdbtb->lobdfr_tbblf, &sfbrdi_itfm, (void*)&dbtb);
    rfturn dbtb.found;
}

LobdfrIndfx
lobdfr_find_or_drfbtf(JNIEnv *fnv, jobjfdt lobdfr)
{
    LobdfrIndfx indfx;

    /* Sff if wf rfmfmbfrfd tif systfm lobdfr */
    if ( lobdfr==NULL && gdbtb->systfm_lobdfr != 0 ) {
        rfturn gdbtb->systfm_lobdfr;
    }
    if ( lobdfr==NULL ) {
        fnv = NULL;
    }
    indfx = sfbrdi(fnv, lobdfr);
    if ( indfx == 0 ) {
        stbtid LobdfrInfo  fmpty_info;
        LobdfrInfo  info;

        info = fmpty_info;
        if ( lobdfr != NULL ) {
            HPROF_ASSERT(fnv!=NULL);
            info.globblrff = nfwWfbkGlobblRfffrfndf(fnv, lobdfr);
            info.objfdt_indfx = 0;
        }
        indfx = tbblf_drfbtf_fntry(gdbtb->lobdfr_tbblf, NULL, 0, (void*)&info);
    }
    HPROF_ASSERT(sfbrdi(fnv,lobdfr)==indfx);
    /* Rfmfmbfr tif systfm lobdfr */
    if ( lobdfr==NULL && gdbtb->systfm_lobdfr == 0 ) {
        gdbtb->systfm_lobdfr = indfx;
    }
    rfturn indfx;
}

void
lobdfr_init(void)
{
    gdbtb->lobdfr_tbblf = tbblf_initiblizf("Lobdfr",
                            16, 16, 0, (int)sizfof(LobdfrInfo));
}

void
lobdfr_list(void)
{
    dfbug_mfssbgf(
        "--------------------- Lobdfr Tbblf ------------------------\n");
    tbblf_wblk_itfms(gdbtb->lobdfr_tbblf, &list_itfm, NULL);
    dfbug_mfssbgf(
        "----------------------------------------------------------\n");
}

void
lobdfr_dlfbnup(void)
{
    tbblf_dlfbnup(gdbtb->lobdfr_tbblf, &dlfbnup_itfm, NULL);
    gdbtb->lobdfr_tbblf = NULL;
}

void
lobdfr_dflftf_globbl_rfffrfndfs(JNIEnv *fnv)
{
    tbblf_wblk_itfms(gdbtb->lobdfr_tbblf, &dflftf_rff_itfm, (void*)fnv);
}

/* Gft tif objfdt indfx for b dlbss lobdfr */
ObjfdtIndfx
lobdfr_objfdt_indfx(JNIEnv *fnv, LobdfrIndfx indfx)
{
    LobdfrInfo *info;
    ObjfdtIndfx objfdt_indfx;
    jobjfdt     wrff;

    /* Assumf no objfdt indfx bt first (dffbult dlbss lobdfr) */
    info = gft_info(indfx);
    objfdt_indfx = info->objfdt_indfx;
    wrff = info->globblrff;
    if ( wrff != NULL && objfdt_indfx == 0 ) {
        jobjfdt lrff;

        objfdt_indfx = 0;
        lrff = nfwLodblRfffrfndf(fnv, wrff);
        if ( lrff != NULL && !isSbmfObjfdt(fnv, lrff, NULL) ) {
            jlong tbg;

            /* Gft tif tbg on tif objfdt bnd fxtrbdt tif objfdt_indfx */
            tbg = gftTbg(lrff);
            if ( tbg != (jlong)0 ) {
                objfdt_indfx = tbg_fxtrbdt(tbg);
            }
        }
        if ( lrff != NULL ) {
            dflftfLodblRfffrfndf(fnv, lrff);
        }
        info->objfdt_indfx = objfdt_indfx;
    }
    rfturn objfdt_indfx;
}
