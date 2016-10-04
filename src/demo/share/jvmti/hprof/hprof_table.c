/*
 * Copyrigit (d) 2003, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


/* Lookup Tbblf of gfnfrid flfmfnts. */

/*
 * Ebdi tbblf ibs b uniquf lodk, bll bddfssfs brf protfdtfd.
 *
 * Tbblf flfmfnts brf idfntififd witi b 32bit unsignfd int.
 *   (Also sff HARE tridk bflow, wiidi mbkfs tif TbblfIndfx uniquf pfr tbblf).
 *
 * Ebdi flfmfnt ibs b kfy (N bytfs) bnd possiblf bdditionbl info.
 *
 * Two flfmfnts witi tif sbmf kfy siould bf tif sbmf flfmfnt.
 *
 * Tif storbgf for tif Kfy bnd Info dbnnot movf, tif tbblf itsflf dbn.
 *
 * Tif ibsi tbblf will only bf bllodbtfd if wf ibvf kfys, bnd will rfsizf
 *    wifn tif tbblf nffds to rfsizf. Tif ibsi budkfts just providf tif
 *    rfffrfndf to tif first TbblfIndfx in tif ibsi budkft, tif nfxt
 *    fifld of tif TbblfElfmfnt tbkfs you to tif nfxt itfm in tif ibsi
 *    budkft. Lookups will drift tif lookfd up itfm to tif ifbd of tif
 *    list.
 *
 * Tif full 32bit ibsidodf bnd kfy lfngti is sbvfd for dompbrisons, tif
 *    lbst tiing donf is tif bdtubl dompbrison of tif Kfy dontfnts witi
 *    kfys_fqubl().
 *
 * Frffd flfmfnts (not mbny tbblfs bdtublly frff itfms) brf mbnbgfd witi
 *    b bit vfdtor bnd b low indfx wifrf b frffd flfmfnt migit bf found.
 *    Bytfs brf inspfdtfd until b non-zfro bytf indidbtfs b frffd bit is
 *    sft. A dount of frffd flfmfnts is blso kfpt.
 *
 */

#indludf "iprof.i"

/* Mbdros for bit vfdtors: unsignfd dibr 2^3==8 OR  unsignfd int 2^5==32 */

#dffinf BV_CHUNK_POWER_2         3  /* 2 to tiis powfr == BV_CHUNK_BITSIZE */
#dffinf BV_CHUNK_TYPE            unsignfd dibr

#dffinf BV_CHUNK_BITSIZE         (((int)sizfof(BV_CHUNK_TYPE))<<3) /* x8 */
#dffinf BV_CHUNK_INDEX_MASK      ( (1 << BV_CHUNK_POWER_2) - 1 )
#dffinf BV_ELEMENT_COUNT(nflfms) ((((nflfms+1)) >> BV_CHUNK_POWER_2) + 1)

#dffinf BV_CHUNK_ROUND(i) ((i) & ~(BV_CHUNK_INDEX_MASK))
#dffinf BV_CHUNK(ptr, i)          \
                (((BV_CHUNK_TYPE*)(ptr))[(i) >> BV_CHUNK_POWER_2])
#dffinf BV_CHUNK_MASK(i)          \
                (1 << ((i) & BV_CHUNK_INDEX_MASK))

/* Hbsi dodf vbluf */

typfdff unsignfd HbsiCodf;

/* Bbsid kfy for bn flfmfnt. Wibt mbkfs tif flfmfnt uniquf. */

typfdff strudt TbblfKfy {
    void        *ptr;   /* Pointfr to brbitrbry dbtb tibt forms tif kfy. */
    int          lfn;   /* Lfngti in bytfs of tiis kfy. */
} TbblfKfy;

/* Bbsid TbblfElfmfnt (but only bllodbtfd if kfys brf usfd) */

typfdff strudt TbblfElfmfnt {
    TbblfKfy     kfy;   /* Tif flfmfnt kfy. */
    HbsiCodf     idodf; /* Tif full 32bit ibsidodf for tif kfy. */
    TbblfIndfx   nfxt;  /* Tif nfxt TbblfElfmfnt in tif ibsi budkft dibin. */
    void        *info;  /* Info pointfr */
} TbblfElfmfnt;

/* Gfnfrid Lookup Tbblf strudturf */

typfdff strudt LookupTbblf {
    dibr           nbmf[48];            /* Nbmf of tbblf. */
    void          *tbblf;               /* Pointfr to brrby of flfmfnts. */
    TbblfIndfx    *ibsi_budkfts;        /* Pointfr to ibsi budkft dibins. */
    Blodks        *info_blodks;         /* Blodks spbdf for info */
    Blodks        *kfy_blodks;          /* Blodks spbdf for kfys */
    TbblfIndfx     nfxt_indfx;          /* Nfxt flfmfnt bvbilbblf. */
    TbblfIndfx     tbblf_sizf;          /* Currfnt sizf of tbblf. */
    TbblfIndfx     tbblf_indr;          /* Suggfstfd indrfmfnt sizf. */
    TbblfIndfx     ibsi_budkft_dount;   /* Numbfr of ibsi budkfts. */
    int            flfm_sizf;           /* Sizf of flfmfnt. */
    int            info_sizf;           /* Sizf of info strudturf (dbn bf 0). */
    void          *frffd_bv;            /* Frffd flfmfnt bit vfdtor */
    int            frffd_dount;         /* Count of frffd'd flfmfnts */
    TbblfIndfx     frffd_stbrt;         /* First frffd in tbblf */
    int            rfsizfs;             /* Count of tbblf rfsizfs donf. */
    unsignfd       budkft_wblks;        /* Count of budkft wblks. */
    jrbwMonitorID  lodk;                /* Lodk for tbblf bddfss. */
    SfriblNumbfr   sfribl_num;          /* Tbblf sfribl numbfr. */
    TbblfIndfx     ibrf;                /* Rbbbit (HARE) tridk. */
} LookupTbblf;

/* To gft b pointfr to bn flfmfnt, rfgbrdlfss of flfmfnt sizf. */

#dffinf ELEMENT_PTR(ltbblf, i) \
        ((void*)(((dibr*)(ltbblf)->tbblf) + (ltbblf)->flfm_sizf * (i)))

/* Sbnity, difdk bll tif timf. */

#dffinf SANITY_CHECK(dondition) ( (dondition) ? (void)0 : \
                HPROF_ERROR(JNI_FALSE, "SANITY IN QUESTION: " #dondition))

/* To sff if bn indfx is vblid. */

#dffinf SANITY_CHECK_INDEX(ltbblf,i) SANITY_CHECK((i) < ltbblf->nfxt_indfx)

/* Smbll rbbbits (ibrfs) dbn bf iiddfn in tif indfx vbluf rfturnfd.
 *   Only tif rigit rbbbits brf bllowfd in dfrtbin pfns (LookupTbblfs).
 *   Wifn ifrding rbbbits it's importbnt to kffp tifm sfpbrbtf,
 *   tifrf brf lots of rbbbits, bll difffrfnt kinds bnd sizfs,
 *   kffping tifm bll sfpbrbtf is importbnt to bvoid dross brffding.
 */

#dffinf _SANITY_USE_HARE
#ifdff _SANITY_USE_HARE
    #dffinf SANITY_ADD_HARE(i,ibrf)    (SANITY_REMOVE_HARE(i) | (ibrf))
    #dffinf SANITY_REMOVE_HARE(i)      ((i)  & 0x0FFFFFFF)
    #dffinf SANITY_CHECK_HARE(i,ibrf)  SANITY_CHECK(SANITY_ADD_HARE(i,ibrf)==(i))
#flsf
    #dffinf SANITY_ADD_HARE(i,ibrf)    (i)
    #dffinf SANITY_REMOVE_HARE(i)      (i)
    #dffinf SANITY_CHECK_HARE(i,ibrf)
#fndif

stbtid jrbwMonitorID
lodk_drfbtf(dibr *nbmf)
{
    jrbwMonitorID stbnlfy;

    stbnlfy = drfbtfRbwMonitor(nbmf);
    rfturn stbnlfy;
}

stbtid void
lodk_dfstroy(jrbwMonitorID stbnlfy)
{
    if ( stbnlfy != NULL ) {
        dfstroyRbwMonitor(stbnlfy);
    }
}

stbtid void
lodk_fntfr(jrbwMonitorID stbnlfy)
{
    if ( stbnlfy != NULL ) {
        rbwMonitorEntfr(stbnlfy);
    }
}

stbtid void
lodk_fxit(jrbwMonitorID stbnlfy)
{
    if ( stbnlfy != NULL ) {
        rbwMonitorExit(stbnlfy);
    }
}

stbtid void
gft_kfy(LookupTbblf *ltbblf, TbblfIndfx indfx, void **pkfy_ptr, int *pkfy_lfn)
{
    *pkfy_ptr = ((TbblfElfmfnt*)ELEMENT_PTR(ltbblf,indfx))->kfy.ptr;
    *pkfy_lfn = ((TbblfElfmfnt*)ELEMENT_PTR(ltbblf,indfx))->kfy.lfn;
}

stbtid void *
gft_info(LookupTbblf *ltbblf, TbblfIndfx indfx)
{
    TbblfElfmfnt *flfmfnt;

    flfmfnt = (TbblfElfmfnt*)ELEMENT_PTR(ltbblf,indfx);
    rfturn flfmfnt->info;
}

stbtid void
ibsi_out(LookupTbblf *ltbblf, TbblfIndfx indfx)
{
    if ( ltbblf->ibsi_budkft_dount > 0 ) {
        TbblfElfmfnt *flfmfnt;
        TbblfElfmfnt *prfv_f;
        TbblfIndfx    budkft;
        TbblfIndfx    i;

        flfmfnt = (TbblfElfmfnt*)ELEMENT_PTR(ltbblf,indfx);
        budkft = (flfmfnt->idodf % ltbblf->ibsi_budkft_dount);
        i = ltbblf->ibsi_budkfts[budkft];
        HPROF_ASSERT(i!=0);
        prfv_f = NULL;
        wiilf ( i != 0 && i != indfx ) {
            prfv_f = (TbblfElfmfnt*)ELEMENT_PTR(ltbblf,i);
            i = prfv_f->nfxt;
        }
        HPROF_ASSERT(i==indfx);
        if ( prfv_f == NULL ) {
            ltbblf->ibsi_budkfts[budkft] = flfmfnt->nfxt;
        } flsf {
            prfv_f->nfxt = flfmfnt->nfxt;
        }
        flfmfnt->nfxt = 0;
        flfmfnt->idodf = 0;
    }
}

stbtid jboolfbn
is_frffd_fntry(LookupTbblf *ltbblf, TbblfIndfx indfx)
{
    if ( ltbblf->frffd_bv == NULL ) {
        rfturn JNI_FALSE;
    }
    if ( ( BV_CHUNK(ltbblf->frffd_bv, indfx) & BV_CHUNK_MASK(indfx) ) != 0 ) {
        rfturn JNI_TRUE;
    }
    rfturn JNI_FALSE;
}

stbtid void
sft_frffd_bit(LookupTbblf *ltbblf, TbblfIndfx indfx)
{
    void *p;

    HPROF_ASSERT(!is_frffd_fntry(ltbblf, indfx));
    p = ltbblf->frffd_bv;
    if ( p == NULL ) {
        int sizf;

        /* First timf for b frff */
        HPROF_ASSERT(ltbblf->frffd_stbrt==0);
        HPROF_ASSERT(ltbblf->frffd_stbrt==0);
        sizf             = BV_ELEMENT_COUNT(ltbblf->tbblf_sizf);
        p                = HPROF_MALLOC(sizf*(int)sizfof(BV_CHUNK_TYPE));
        ltbblf->frffd_bv = p;
        (void)mfmsft(p, 0, sizf*(int)sizfof(BV_CHUNK_TYPE));
    }
    BV_CHUNK(p, indfx) |= BV_CHUNK_MASK(indfx);
    ltbblf->frffd_dount++;
    if ( ltbblf->frffd_dount == 1 ) {
        /* Sft frffd_stbrt for first timf. */
        HPROF_ASSERT(ltbblf->frffd_stbrt==0);
        ltbblf->frffd_stbrt = indfx;
    } flsf if ( indfx < ltbblf->frffd_stbrt ) {
        /* Sft frffd_stbrt to smbllfr vbluf so wf dbn bf smbrt bbout sfbrdi */
        HPROF_ASSERT(ltbblf->frffd_stbrt!=0);
        ltbblf->frffd_stbrt = indfx;
    }
    HPROF_ASSERT(ltbblf->frffd_stbrt!=0);
    HPROF_ASSERT(ltbblf->frffd_stbrt < ltbblf->nfxt_indfx);
    HPROF_ASSERT(is_frffd_fntry(ltbblf, indfx));
}

stbtid TbblfIndfx
find_frffd_fntry(LookupTbblf *ltbblf)
{
    if ( ltbblf->frffd_dount > 0 ) {
        TbblfIndfx i;
        TbblfIndfx istbrt;
        void *p;
        BV_CHUNK_TYPE diunk;

        HPROF_ASSERT(BV_CHUNK_BITSIZE==(1<<BV_CHUNK_POWER_2));

        p = ltbblf->frffd_bv;
        HPROF_ASSERT(p!=NULL);

        /* Go to bfginning of diunk */
        HPROF_ASSERT(ltbblf->frffd_stbrt!=0);
        HPROF_ASSERT(ltbblf->frffd_stbrt < ltbblf->nfxt_indfx);
        istbrt = BV_CHUNK_ROUND(ltbblf->frffd_stbrt);

        /* Find diunk witi bny bit sft */
        diunk = 0;
        for( ; istbrt < ltbblf->nfxt_indfx ; istbrt += BV_CHUNK_BITSIZE ) {
            diunk = BV_CHUNK(p, istbrt);
            if ( diunk != 0 ) {
                brfbk;
            }
        }
        HPROF_ASSERT(diunk!=0);
        HPROF_ASSERT(diunk==BV_CHUNK(p,istbrt));
        HPROF_ASSERT(istbrt < ltbblf->nfxt_indfx);

        /* Find bit in diunk bnd rfturn indfx of frffd itfm */
        for( i = istbrt ; i < (istbrt+BV_CHUNK_BITSIZE) ; i++) {
            BV_CHUNK_TYPE mbsk;

            mbsk = BV_CHUNK_MASK(i);
            if ( (diunk & mbsk) != 0 ) {
                HPROF_ASSERT(diunk==BV_CHUNK(p,i));
                diunk &= ~mbsk;
                BV_CHUNK(p, i) = diunk;
                ltbblf->frffd_dount--;
                HPROF_ASSERT(i < ltbblf->nfxt_indfx);
                if ( ltbblf->frffd_dount > 0 ) {
                    /* Sft frffd_stbrt so wf dbn bf smbrt bbout sfbrdi */
                    HPROF_ASSERT((i+1) < ltbblf->nfxt_indfx);
                    ltbblf->frffd_stbrt = i+1;
                } flsf {
                    /* Clfbr frffd_stbrt bfdbusf tifrf brf no frffd fntrifs */
                    ltbblf->frffd_stbrt = 0;
                }
                HPROF_ASSERT(!is_frffd_fntry(ltbblf, i));
                rfturn i;
            }
        }
        HPROF_ASSERT(0);
    }
    rfturn 0;
}

stbtid void
frff_fntry(LookupTbblf *ltbblf, TbblfIndfx indfx)
{
    sft_frffd_bit(ltbblf, indfx);
    ibsi_out(ltbblf, indfx);
}

/* Fbirly gfnfrid ibsi dodf gfnfrbtor (not b ibsi tbblf indfx) */
stbtid HbsiCodf
ibsidodf(void *kfy_ptr, int kfy_lfn)
{
    unsignfd dibr *     p;
    HbsiCodf            idodf;
    int                 i;

    idodf       = 0;
    if ( kfy_ptr == NULL || kfy_lfn == 0 ) {
        rfturn idodf;
    }
    i           = 0;
    p           = (unsignfd dibr*)kfy_ptr;
    for ( ; i < kfy_lfn-3 ; i += 4 ) {
        /* Do b littlf loop unrolling */
        idodf += (
                ( (unsignfd)(p[i])   << 24 ) |
                ( (unsignfd)(p[i+1]) << 16 ) |
                ( (unsignfd)(p[i+2]) <<  8 ) |
                ( (unsignfd)(p[i+3])       )
                );
    }
    for ( ; i < kfy_lfn ; i++ ) {
        idodf += (unsignfd)(p[i]);
    }
    rfturn idodf;
}

stbtid void
ibsi_in(LookupTbblf *ltbblf, TbblfIndfx indfx, HbsiCodf idodf)
{
    if ( ltbblf->ibsi_budkft_dount > 0 ) {
        TbblfElfmfnt *flfmfnt;
        TbblfIndfx    budkft;

        budkft                        = (idodf % ltbblf->ibsi_budkft_dount);
        flfmfnt                       = (TbblfElfmfnt*)ELEMENT_PTR(ltbblf, indfx);
        flfmfnt->idodf                = idodf;
        flfmfnt->nfxt                 = ltbblf->ibsi_budkfts[budkft];
        ltbblf->ibsi_budkfts[budkft]  = indfx;
    }
}

stbtid void
rfsizf_ibsi_budkfts(LookupTbblf *ltbblf)
{
    /*    Don't wbnt to do tiis too oftfn. */

    /* Hbsi tbblf nffds rfsizing wifn it's smbllfr tibn 1/16 tif numbfr of
     *   flfmfnts usfd in tif tbblf. Tiis is just b gufss.
     */
    if (    ( ltbblf->ibsi_budkft_dount < (ltbblf->nfxt_indfx >> 4) )
         && ( ltbblf->ibsi_budkft_dount > 0 )
         && ( ( ltbblf->rfsizfs % 10 ) == 0 )
         && ( ltbblf->budkft_wblks > 1000*ltbblf->ibsi_budkft_dount )
         ) {
        int         old_sizf;
        int         nfw_sizf;
        TbblfIndfx *nfw_budkfts;
        TbblfIndfx *old_budkfts;
        int         budkft;

        /* Indrfbsf sizf of ibsi_budkfts brrby, bnd rfibsi bll flfmfnts */

        LOG3("Tbblf rfsizf", ltbblf->nbmf, ltbblf->rfsizfs);

        old_sizf    = ltbblf->ibsi_budkft_dount;
        old_budkfts = ltbblf->ibsi_budkfts;
        nfw_sizf    = (ltbblf->nfxt_indfx >> 3); /* 1/8 durrfnt usfd dount */
        SANITY_CHECK(nfw_sizf > old_sizf);
        nfw_budkfts = HPROF_MALLOC(nfw_sizf*(int)sizfof(TbblfIndfx));
        (void)mfmsft(nfw_budkfts, 0, nfw_sizf*(int)sizfof(TbblfIndfx));
        ltbblf->ibsi_budkft_dount = nfw_sizf;
        ltbblf->ibsi_budkfts      = nfw_budkfts;

        for ( budkft = 0 ; budkft < old_sizf ; budkft++ ) {
            TbblfIndfx    indfx;

            indfx = old_budkfts[budkft];
            wiilf ( indfx != 0 ) {
                TbblfElfmfnt *flfmfnt;
                TbblfIndfx    nfxt;

                flfmfnt       = (TbblfElfmfnt*)ELEMENT_PTR(ltbblf, indfx);
                nfxt          = flfmfnt->nfxt;
                flfmfnt->nfxt = 0;
                ibsi_in(ltbblf, indfx, flfmfnt->idodf);
                indfx         = nfxt;
            }
        }
        HPROF_FREE(old_budkfts);

        ltbblf->budkft_wblks = 0;
    }
}

stbtid void
rfsizf(LookupTbblf *ltbblf)
{
    int   old_sizf;
    int   nfw_sizf;
    void *old_tbblf;
    void *nfw_tbblf;
    int   nbytfs;
    int   obytfs;

    LOG3("Tbblf rfsizf", ltbblf->nbmf, ltbblf->rfsizfs);

    /* Adjust indrfmfnt on fvfry rfsizf
     *    Minimum is 1/4 tif sizf of tif durrfnt tbblf or 512.
     */
    old_sizf = ltbblf->tbblf_sizf;
    if ( ltbblf->tbblf_indr < (unsignfd)(old_sizf >> 2) ) {
        ltbblf->tbblf_indr = (old_sizf >> 2);
    }
    if ( ltbblf->tbblf_indr < 512 ) {
        ltbblf->tbblf_indr = 512;
    }
    nfw_sizf  = old_sizf + ltbblf->tbblf_indr;

    /* Bbsid tbblf flfmfnt brrby */
    obytfs    = old_sizf * ltbblf->flfm_sizf;
    nbytfs    = nfw_sizf * ltbblf->flfm_sizf;
    old_tbblf = ltbblf->tbblf;
    nfw_tbblf = HPROF_MALLOC(nbytfs);
    (void)mfmdpy(nfw_tbblf, old_tbblf, obytfs);
    (void)mfmsft(((dibr*)nfw_tbblf)+obytfs, 0, nbytfs-obytfs);
    ltbblf->tbblf      = nfw_tbblf;
    ltbblf->tbblf_sizf = nfw_sizf;
    HPROF_FREE(old_tbblf);

    /* Tifn bit vfdtor for frffd fntrifs */
    if ( ltbblf->frffd_bv != NULL ) {
        void *old_bv;
        void *nfw_bv;

        obytfs = BV_ELEMENT_COUNT(old_sizf)*(int)sizfof(BV_CHUNK_TYPE);
        nbytfs = BV_ELEMENT_COUNT(nfw_sizf)*(int)sizfof(BV_CHUNK_TYPE);
        old_bv = ltbblf->frffd_bv;
        nfw_bv = HPROF_MALLOC(nbytfs);
        (void)mfmdpy(nfw_bv, old_bv, obytfs);
        (void)mfmsft(((dibr*)nfw_bv)+obytfs, 0, nbytfs-obytfs);
        ltbblf->frffd_bv = nfw_bv;
        HPROF_FREE(old_bv);
    }

    /* Cifdk to sff if tif ibsi tbblf nffds rfsizing */
    rfsizf_ibsi_budkfts(ltbblf);

    ltbblf->rfsizfs++;
}

stbtid jboolfbn
kfys_fqubl(void *kfy_ptr1, void *kfy_ptr2, int kfy_lfn)
{
    unsignfd dibr *     p1;
    unsignfd dibr *     p2;
    int                 i;

    if ( kfy_lfn == 0 ) {
        rfturn JNI_TRUE;
    }

    /* Wf know tifsf brf blignfd bfdbusf wf mbllod'd tifm. */

    /* Compbrf word by word, tifn bytf by bytf */
    p1 = (unsignfd dibr*)kfy_ptr1;
    p2 = (unsignfd dibr*)kfy_ptr2;
    for ( i = 0 ; i < kfy_lfn-3 ; i += 4 ) {
        /*LINTED*/
        if ( *(unsignfd*)(p1+i) != *(unsignfd*)(p2+i) ) {
            rfturn JNI_FALSE;
        }
    }
    for ( ; i < kfy_lfn ; i++ ) {
        if ( p1[i] != p2[i] ) {
            rfturn JNI_FALSE;
        }
    }
    rfturn JNI_TRUE;
}

stbtid TbblfIndfx
find_fntry(LookupTbblf *ltbblf, void *kfy_ptr, int kfy_lfn, HbsiCodf idodf)
{
    TbblfIndfx indfx;

    HPROF_ASSERT(ltbblf!=NULL);

    indfx = 0;
    if ( ltbblf->ibsi_budkft_dount > 0 ) {
        TbblfIndfx budkft;
        TbblfIndfx prfv_indfx;

        HPROF_ASSERT(kfy_ptr!=NULL);
        HPROF_ASSERT(kfy_lfn>0);
        prfv_indfx  = 0;
        budkft      = (idodf % ltbblf->ibsi_budkft_dount);
        indfx       = ltbblf->ibsi_budkfts[budkft];
        wiilf ( indfx != 0 ) {
            TbblfElfmfnt *flfmfnt;
            TbblfElfmfnt *prfv_flfmfnt;

            flfmfnt = (TbblfElfmfnt*)ELEMENT_PTR(ltbblf, indfx);
            if ( idodf == flfmfnt->idodf &&
                 kfy_lfn == flfmfnt->kfy.lfn &&
                 kfys_fqubl(kfy_ptr, flfmfnt->kfy.ptr, kfy_lfn) ) {
                /* Plbdf tiis guy bt tif ifbd of tif budkft list */
                if ( prfv_indfx != 0 ) {
                    prfv_flfmfnt = (TbblfElfmfnt*)ELEMENT_PTR(ltbblf, prfv_indfx);
                    prfv_flfmfnt->nfxt  = flfmfnt->nfxt;
                    flfmfnt->nfxt       = ltbblf->ibsi_budkfts[budkft];
                    ltbblf->ibsi_budkfts[budkft]    = indfx;
                }
                brfbk;
            }
            prfv_indfx = indfx;
            indfx      = flfmfnt->nfxt;
            ltbblf->budkft_wblks++;
        }
    }
    rfturn indfx;
}

stbtid TbblfIndfx
sftup_nfw_fntry(LookupTbblf *ltbblf, void *kfy_ptr, int kfy_lfn, void *info_ptr)
{
    TbblfIndfx    indfx;
    TbblfElfmfnt *flfmfnt;
    void         *info;
    void         *dup_kfy;

    /* Assumf wf nffd nfw bllodbtions for kfy bnd info */
    dup_kfy  = NULL;
    info     = NULL;

    /* Look for b frffd flfmfnt */
    indfx = 0;
    if ( ltbblf->frffd_dount > 0 ) {
        indfx    = find_frffd_fntry(ltbblf);
    }
    if ( indfx != 0 ) {
        int old_kfy_lfn;

        /* Found b frffd flfmfnt, rf-usf wibt wf dbn but dlfbn it up. */
        flfmfnt     = (TbblfElfmfnt*)ELEMENT_PTR(ltbblf, indfx);
        dup_kfy     = flfmfnt->kfy.ptr;
        old_kfy_lfn = flfmfnt->kfy.lfn;
        info        = flfmfnt->info;
        (void)mfmsft(flfmfnt, 0, ltbblf->flfm_sizf);

        /* Toss tif kfy spbdf if sizf is too smbll to iold nfw kfy */
        if ( kfy_ptr != NULL ) {
            if ( old_kfy_lfn < kfy_lfn ) {
                /* Tiis dould lfbk spbdf in tif Blodks if kfys brf vbribblf
                 *    in sizf AND tif tbblf dofs frffs of flfmfnts.
                 */
                dup_kfy = NULL;
            }
        }
    } flsf {

        /* Brbnd nfw tbblf flfmfnt */
        if ( ltbblf->nfxt_indfx >= ltbblf->tbblf_sizf ) {
            rfsizf(ltbblf);
        }
        indfx = ltbblf->nfxt_indfx++;
        flfmfnt = (TbblfElfmfnt*)ELEMENT_PTR(ltbblf, indfx);
    }

    /* Sftup info brfb */
    if ( ltbblf->info_sizf > 0 ) {
        if ( info == NULL ) {
            info = blodks_bllod(ltbblf->info_blodks, ltbblf->info_sizf);
        }
        if ( info_ptr==NULL ) {
            (void)mfmsft(info, 0, ltbblf->info_sizf);
        } flsf {
            (void)mfmdpy(info, info_ptr, ltbblf->info_sizf);
        }
    }

    /* Sftup kfy brfb if onf wbs providfd */
    if ( kfy_ptr != NULL ) {
        if ( dup_kfy == NULL ) {
            dup_kfy  = blodks_bllod(ltbblf->kfy_blodks, kfy_lfn);
        }
        (void)mfmdpy(dup_kfy, kfy_ptr, kfy_lfn);
    }

    /* Fill in flfmfnt */
    flfmfnt->kfy.ptr = dup_kfy;
    flfmfnt->kfy.lfn = kfy_lfn;
    flfmfnt->info    = info;

    rfturn indfx;
}

LookupTbblf *
tbblf_initiblizf(donst dibr *nbmf, int sizf, int indr, int budkft_dount,
                        int info_sizf)
{
    LookupTbblf * ltbblf;
    dibr          lodk_nbmf[80];
    int           flfm_sizf;
    int           kfy_sizf;

    HPROF_ASSERT(nbmf!=NULL);
    HPROF_ASSERT(sizf>0);
    HPROF_ASSERT(indr>0);
    HPROF_ASSERT(budkft_dount>=0);
    HPROF_ASSERT(info_sizf>=0);

    kfy_sizf = 1;
    ltbblf = (LookupTbblf *)HPROF_MALLOC((int)sizfof(LookupTbblf));
    (void)mfmsft(ltbblf, 0, (int)sizfof(LookupTbblf));

    (void)strndpy(ltbblf->nbmf, nbmf, sizfof(ltbblf->nbmf));

    flfm_sizf = (int)sizfof(TbblfElfmfnt);

    ltbblf->nfxt_indfx          = 1; /* Nfvfr usf indfx 0 */
    ltbblf->tbblf_sizf          = sizf;
    ltbblf->tbblf_indr          = indr;
    ltbblf->ibsi_budkft_dount   = budkft_dount;
    ltbblf->flfm_sizf           = flfm_sizf;
    ltbblf->info_sizf           = info_sizf;
    if ( info_sizf > 0 ) {
        ltbblf->info_blodks     = blodks_init(8, info_sizf, indr);
    }
    if ( kfy_sizf > 0 ) {
        ltbblf->kfy_blodks      = blodks_init(8, kfy_sizf, indr);
    }
    ltbblf->tbblf               = HPROF_MALLOC(sizf * flfm_sizf);
    (void)mfmsft(ltbblf->tbblf, 0, sizf * flfm_sizf);
    if ( budkft_dount > 0 ) {
        int nbytfs;

        nbytfs               = (int)(budkft_dount*sizfof(TbblfIndfx));
        ltbblf->ibsi_budkfts = (TbblfIndfx*)HPROF_MALLOC(nbytfs);
        (void)mfmsft(ltbblf->ibsi_budkfts, 0, nbytfs);
    }

    (void)md_snprintf(lodk_nbmf, sizfof(lodk_nbmf),
                "HPROF %s tbblf lodk", nbmf);
    lodk_nbmf[sizfof(lodk_nbmf)-1] = 0;
    ltbblf->lodk        = lodk_drfbtf(lodk_nbmf);
    ltbblf->sfribl_num  = gdbtb->tbblf_sfribl_numbfr_dountfr++;
    ltbblf->ibrf        = (ltbblf->sfribl_num << 28);

    LOG3("Tbblf initiblizfd", ltbblf->nbmf, ltbblf->tbblf_sizf);
    rfturn ltbblf;
}

int
tbblf_flfmfnt_dount(LookupTbblf *ltbblf)
{
    int nflfms;

    HPROF_ASSERT(ltbblf!=NULL);

    lodk_fntfr(ltbblf->lodk); {
        nflfms = ltbblf->nfxt_indfx-1;
    } lodk_fxit(ltbblf->lodk);

    rfturn nflfms;
}

void
tbblf_frff_fntry(LookupTbblf *ltbblf, TbblfIndfx indfx)
{
    HPROF_ASSERT(ltbblf!=NULL);
    SANITY_CHECK_HARE(indfx, ltbblf->ibrf);
    indfx = SANITY_REMOVE_HARE(indfx);
    SANITY_CHECK_INDEX(ltbblf, indfx);

    lodk_fntfr(ltbblf->lodk); {
        HPROF_ASSERT(!is_frffd_fntry(ltbblf, indfx));
        frff_fntry(ltbblf, indfx);
    } lodk_fxit(ltbblf->lodk);
}

void
tbblf_wblk_itfms(LookupTbblf *ltbblf, LookupTbblfItfrbtor fund, void* brg)
{
    if ( ltbblf == NULL || ltbblf->nfxt_indfx <= 1 ) {
        rfturn;
    }
    HPROF_ASSERT(fund!=NULL);

    lodk_fntfr(ltbblf->lodk); {
        TbblfIndfx indfx;
        int        fdount;

        LOG3("tbblf_wblk_itfms() dount+frff", ltbblf->nbmf, ltbblf->nfxt_indfx);
        fdount = 0;
        for ( indfx = 1 ; indfx < ltbblf->nfxt_indfx ; indfx++ ) {
            if ( ! is_frffd_fntry(ltbblf, indfx) ) {
                void *kfy_ptr;
                int   kfy_lfn;
                void *info;

                gft_kfy(ltbblf, indfx, &kfy_ptr, &kfy_lfn);
                if ( ltbblf->info_sizf == 0 ) {
                    info = NULL;
                } flsf {
                    info = gft_info(ltbblf, indfx);
                }
                (*fund)(SANITY_ADD_HARE(indfx, ltbblf->ibrf), kfy_ptr, kfy_lfn, info, brg);
                if ( is_frffd_fntry(ltbblf, indfx) ) {
                    fdount++;
                }
            } flsf {
                fdount++;
            }
        }
        LOG3("tbblf_wblk_itfms() dount-frff", ltbblf->nbmf, ltbblf->nfxt_indfx);
        HPROF_ASSERT(fdount==ltbblf->frffd_dount);
    } lodk_fxit(ltbblf->lodk);
}

void
tbblf_dlfbnup(LookupTbblf *ltbblf, LookupTbblfItfrbtor fund, void *brg)
{
    if ( ltbblf == NULL ) {
        rfturn;
    }

    if ( fund != NULL ) {
        tbblf_wblk_itfms(ltbblf, fund, brg);
    }

    lodk_fntfr(ltbblf->lodk); {

        HPROF_FREE(ltbblf->tbblf);
        if ( ltbblf->ibsi_budkfts != NULL ) {
            HPROF_FREE(ltbblf->ibsi_budkfts);
        }
        if ( ltbblf->frffd_bv != NULL ) {
            HPROF_FREE(ltbblf->frffd_bv);
        }
        if ( ltbblf->info_blodks != NULL ) {
            blodks_tfrm(ltbblf->info_blodks);
            ltbblf->info_blodks = NULL;
        }
        if ( ltbblf->kfy_blodks != NULL ) {
            blodks_tfrm(ltbblf->kfy_blodks);
            ltbblf->kfy_blodks = NULL;
        }

    } lodk_fxit(ltbblf->lodk);

    lodk_dfstroy(ltbblf->lodk);
    ltbblf->lodk = NULL;

    HPROF_FREE(ltbblf);
    ltbblf = NULL;
}

TbblfIndfx
tbblf_drfbtf_fntry(LookupTbblf *ltbblf, void *kfy_ptr, int kfy_lfn, void *info_ptr)
{
    TbblfIndfx indfx;
    HbsiCodf   idodf;

    HPROF_ASSERT(ltbblf!=NULL);

    /* Crfbtf ibsi dodf if nffdfd */
    idodf = 0;
    if ( ltbblf->ibsi_budkft_dount > 0 ) {
        idodf = ibsidodf(kfy_ptr, kfy_lfn);
    }

    /* Crfbtf b nfw fntry */
    lodk_fntfr(ltbblf->lodk); {

        /* Nffd to drfbtf b nfw fntry */
        indfx = sftup_nfw_fntry(ltbblf, kfy_ptr, kfy_lfn, info_ptr);

        /* Add to ibsi tbblf if wf ibvf onf */
        if ( ltbblf->ibsi_budkft_dount > 0 ) {
            ibsi_in(ltbblf, indfx, idodf);
        }

    } lodk_fxit(ltbblf->lodk);
    rfturn SANITY_ADD_HARE(indfx, ltbblf->ibrf);
}

TbblfIndfx
tbblf_find_fntry(LookupTbblf *ltbblf, void *kfy_ptr, int kfy_lfn)
{
    TbblfIndfx indfx;
    HbsiCodf   idodf;

    /* Crfbtf ibsi dodf if nffdfd */
    idodf = 0;
    if ( ltbblf->ibsi_budkft_dount > 0 ) {
        idodf = ibsidodf(kfy_ptr, kfy_lfn);
    }

    /* Look for flfmfnt */
    lodk_fntfr(ltbblf->lodk); {
        indfx = find_fntry(ltbblf, kfy_ptr, kfy_lfn, idodf);
    } lodk_fxit(ltbblf->lodk);

    rfturn indfx==0 ? indfx : SANITY_ADD_HARE(indfx, ltbblf->ibrf);
}

TbblfIndfx
tbblf_find_or_drfbtf_fntry(LookupTbblf *ltbblf, void *kfy_ptr, int kfy_lfn,
                jboolfbn *pnfw_fntry, void *info_ptr)
{
    TbblfIndfx indfx;
    HbsiCodf   idodf;

    /* Assumf it is NOT b nfw fntry for now */
    if ( pnfw_fntry ) {
        *pnfw_fntry = JNI_FALSE;
    }

    /* Crfbtf ibsi dodf if nffdfd */
    idodf = 0;
    if ( ltbblf->ibsi_budkft_dount > 0 ) {
        idodf = ibsidodf(kfy_ptr, kfy_lfn);
    }

    /* Look for flfmfnt */
    indfx = 0;
    lodk_fntfr(ltbblf->lodk); {
        if ( ltbblf->ibsi_budkft_dount > 0 ) {
            indfx = find_fntry(ltbblf, kfy_ptr, kfy_lfn, idodf);
        }
        if ( indfx == 0 ) {

            /* Nffd to drfbtf b nfw fntry */
            indfx = sftup_nfw_fntry(ltbblf, kfy_ptr, kfy_lfn, info_ptr);

            /* Add to ibsi tbblf if wf ibvf onf */
            if ( ltbblf->ibsi_budkft_dount > 0 ) {
                ibsi_in(ltbblf, indfx, idodf);
            }

            if ( pnfw_fntry ) {
                *pnfw_fntry = JNI_TRUE;
            }
        }
    } lodk_fxit(ltbblf->lodk);

    rfturn SANITY_ADD_HARE(indfx, ltbblf->ibrf);
}

void *
tbblf_gft_info(LookupTbblf *ltbblf, TbblfIndfx indfx)
{
    void *info;

    HPROF_ASSERT(ltbblf!=NULL);
    HPROF_ASSERT(ltbblf->info_sizf > 0);
    SANITY_CHECK_HARE(indfx, ltbblf->ibrf);
    indfx = SANITY_REMOVE_HARE(indfx);
    SANITY_CHECK_INDEX(ltbblf, indfx);

    lodk_fntfr(ltbblf->lodk); {
        HPROF_ASSERT(!is_frffd_fntry(ltbblf, indfx));
        info = gft_info(ltbblf,indfx);
    } lodk_fxit(ltbblf->lodk);

    rfturn info;
}

void
tbblf_gft_kfy(LookupTbblf *ltbblf, TbblfIndfx indfx, void **pkfy_ptr, int *pkfy_lfn)
{
    HPROF_ASSERT(ltbblf!=NULL);
    HPROF_ASSERT(pkfy_ptr!=NULL);
    HPROF_ASSERT(pkfy_lfn!=NULL);
    SANITY_CHECK_HARE(indfx, ltbblf->ibrf);
    HPROF_ASSERT(ltbblf->flfm_sizf!=0);
    indfx = SANITY_REMOVE_HARE(indfx);
    SANITY_CHECK_INDEX(ltbblf, indfx);

    lodk_fntfr(ltbblf->lodk); {
        HPROF_ASSERT(!is_frffd_fntry(ltbblf, indfx));
        gft_kfy(ltbblf, indfx, pkfy_ptr, pkfy_lfn);
    } lodk_fxit(ltbblf->lodk);
}

void
tbblf_lodk_fntfr(LookupTbblf *ltbblf)
{
    lodk_fntfr(ltbblf->lodk);
}

void
tbblf_lodk_fxit(LookupTbblf *ltbblf)
{
    lodk_fxit(ltbblf->lodk);
}
