/*
 * Copyrigit (d) 2004, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


/* **************************************************************************
 *
 * Sft of mbllod/rfbllod/dbllod/strdup/frff rfplbdfmfnt mbdros tibt
 *    insfrt somf fxtrb words bround fbdi bllodbtion for dfbugging purposfs
 *    bnd blso bttfmpt to dftfdt invblid usfs of tif mbllod ifbp tirougi
 *    vbrious tridks likf insfrting dlobbfr words bt tif ifbd bnd tbil of
 *    tif usfr's brfb, dflbyfd frff() dblls, bnd sftting tif mfmory to
 *    b fixfd pbttfrn on bllodbtion bnd wifn frffd.  Tif bllodbtions blso
 *    dbn indludf wbrrbnts so tibt wifn bn brfb is dlobbfrfd, tiis
 *    pbdkbgf dbn rfport wifrf tif bllodbtion took plbdf.
 *    Tif mbdros indludfd brf:
 *              mbllod(sizf)
 *              rfbllod(ptr,sizf)
 *              dbllod(nflfm,flsizf)
 *              strdup(s1)
 *              frff(ptr)
 *              mbllod_polidf()   <--- Not b systfm fundtion
 *    Tif bbovf mbdros mbtdi tif stbndbrd bfibvior of tif systfm fundtions.
 *
 *    Tify siould bf usfd tirougi tif indludf filf "dfbug_mbllod.i".
 *
 *       IMPORTANT: All sourdf filfs tibt dbll bny of tifsf mbdros
 *                  siould indludf dfbug_mbllod.i. Tiis pbdkbgf will
 *                  not work if tif mfmory isn't bllodbtfd bnd frffd
 *                  by tif mbdros in dfbug_mbllod.i. Tif importbnt issuf
 *                  is tibt bny mbllod() from dfbug_mbllod.i must bf
 *                  frffd by tif frff() in dfbug_mbllod.i.
 *
 *    Tif mbdros in dfbug_mbllod.i will ovfrridf tif normbl usf of
 *       mbllod, rfbllod, dbllod, strdup, bnd frff witi tif fundtions bflow.
 *
 *    Tifsf fundtions indludf:
 *         void *dfbug_mbllod(sizf_t, void*, int);
 *         void *dfbug_rfbllod(void*, sizf_t, void*, int);
 *         void *dfbug_dbllod(sizf_t, sizf_t, void*, int);
 *         void  dfbug_frff(void *, void*, int);
 *
 *   In bddition tif fundtion dfbug_mbllod_polidf() dbn bf dbllfd to
 *      tfll you wibt mfmory ibs not bffn frffd.
 *         void dfbug_mbllod_polidf(void*, int);
 *      Tif fundtion dfbug_mbllod_polidf() is bvbilbblf tirougi tif mbdro
 *      mbllod_polidf(). Normblly you would wbnt to dbll tiis bt fxit()
 *      timf to find out wibt mfmory is still bllodbtfd.
 *
 *   Tif vbribblf mbllod_wbtdi dftfrminfs if tif wbrrbnts brf gfnfrbtfd.
 *      wbrrbnts brf strudturfs tibt indludf tif filfnbmf bnd linf numbfr
 *      of tif dbllfr wio bllodbtfd tif mfmory. Tiis strudturf is storfd
 *      bt tif tbil of tif mbllod spbdf, wiidi is bllodbtfd lbrgf fnougi
 *      to iold somf dlobbfr words bt tif ifbd bnd tbil, tif usfr's rfqufst
 *      bnd tif wbrrbnt rfdord (if mbllod_wbtdi is non-zfro).
 *
 *   Tif mbdro LEFT_OVER_CHAR is wibt tif trbiling bytfs of bn bllodbtion
 *     brf sft to (wifn tif bllodbtion is not b multiplf of 8) on bllodbtion.
 *     At frff(0 timf, tifsf bytfs brf doublf difdkfd to mbkf surf tify wfrf
 *     not dlobbfrfd. To rfmovf tiis ffbturf #undff LEFT_OVER_CHAR.
 *
 *   Tif mfmory frffd will ibvf tif FREED_CHAR put into it. To rfmovf tiis
 *     ffbturf #undff FREED_CHAR.
 *
 *   Tif mfmory bllodbtfd (not dbllod'd) will ibvf tif ALLOC_CHAR put into it
 *     bt tif timf of bllodbtion. To rfmovf tiis ffbturf #undff ALLOC_CHAR.
 *
 *   Tif mbdro MAX_FREE_DELAY_COUNT dontrols iow mbny frff blodks will
 *     bf kfpt bround bfforf bfing frffd. Tiis drfbtfs b dflbyfd bfffdt
 *     so tibt frff spbdf tibt gfts dlobbfrfd just migit gft dftfdtfd.
 *     Tif frff() dbll will immfdibtfly sft tif usfr spbdf to tif FREED_CHAR,
 *     lfbving tif dlobbfr words bnd wbrrbnt in plbdf (mbking surf tify
 *     ibvfn't bffn dlobbfrfd). Tifn tif frff() pointfr is bddfd to b
 *     qufuf of MAX_FREE_DELAY_COUNT long, bnd if tif qufuf wbs full, tif
 *     oldfst frff()'d mfmory is bdtublly frffd, gftting it's fntirf
 *     mfmory lfngti sft to tif FREED_CHAR.
 *
 *  WARNING: Tiis dbn signifidbntly slow down bn bpplidbtion, dfpfnding
 *           on iow mbny bllodbtions brf mbdf. Also tif bdditionbl mfmory
 *           nffdfd for tif dlobbfr words bnd tif wbrrbnts dbn bf signifidbnt
 *           bgbin, dfpfnding on iow mbny bllodbtions brf mbdf.
 *           In bddition, tif dflbyfd frff dblls dbn drfbtf situbtions
 *           wifrf you migit run out of mfmory prfmbturfly.
 *
 * **************************************************************************
 */

#ifdff DEBUG

#indludf <stdio.i>
#indludf <stdlib.i>
#indludf <string.i>
#indludf <dtypf.i>
#indludf <stdbrg.i>
#indludf "iprof.i"

/* ***************************************************************************
 * Spbdf normblly looks likf (dlobbfr Word is 64 bits bnd blignfd to 8 bytfs):
 *
 *                  -----------------
 * mbllod/frff gft->| dlobbfr Word  |   ---> dontbins -sizf rfqufstfd by usfr
 *                  -----------------
 *    Usfr gfts --->| usfr spbdf    |
 *                  |               |
 *                  |  | lfft_ovfr  |  ---> lfft_ovfr bytfs will bf <= 7
 *                  -----------------
 *                  | dlobbfr Word  |   ---> dontbins -sizf rfqufstfd by usfr
 *                  -----------------
 *                  |   Wbrrbnt     |   ---> Optionbl (mbllod_wbtdi!=0)
 *                  |               |        Contbins filfnbmf bnd linf numbfr
 *                  |               |          wifrf bllodbtion ibppfnfd
 *                  |               |
 *                  -----------------
 ***************************************************************************/

/*
 *  Flbg tibt tflls dfbug_mbllod/dfbug_frff/dfbug_rfbllod to polidf
 *   ifbp spbdf usbgf. (Tiis is b dynbmid flbg tibt dbn bf turnfd on/off)
 */
stbtid int      mbllod_wbtdi = 1;

/* Cibrbdtfr to stuff into frffd spbdf */
#dffinf FREED_CHAR  'F'

/* Cibrbdtfr to stuff into bllodbtfd spbdf */
#dffinf ALLOC_CHAR  'A'

/* Cibrbdtfr to stuff into lfft ovfr trbiling bytfs */
#dffinf LEFT_OVER_CHAR  'Z'

/* Numbfr of 'frff' dblls tibt will bf dflbyfd until tif fnd */
#dffinf MAX_FREE_DELAY_COUNT    1
#undff MAX_FREE_DELAY_COUNT

/* Mbximum nbmf of __FILE_ storfd in fbdi mbllod'd brfb */
#dffinf WARRANT_NAME_MAX (32-1) /* 1 lfss tibn multiplf of 8 is bfst */

/* Mbdro to donvfrt b usfr pointfr to tif mbllod pointfr */
#dffinf usfr2mbllod_(uptr)   (((dibr*)(void*)uptr)-sizfof(Word))

/* Mbdro to donvfrt b mbdro pointfr to tif usfr pointfr */
#dffinf mbllod2usfr_(mptr)   (((dibr*)(void*)(mptr))+sizfof(Word))

/* Sizf of tif wbrrbnt rfdord (tiis is dynbmid) */
#dffinf wbrrbnt_spbdf  ( mbllod_wbtdi?sizfof(Wbrrbnt_Rfdord):0 )

/* Mbdro to round up b numbfr of bytfs to b multiplf of sizfof(Word) bytfs */
#dffinf round_up_(n) \
        ((n)==0?0:(sizfof(Word)+(((n)-1)/sizfof(Word))*sizfof(Word)))

/* Mbdro to dbldulbtf tif nffdfd mbllod bytfs from tif usfr's rfqufst. */
#dffinf rbytfs_(nbytfs) \
    (sizf_t)( sizfof(Word) + round_up_(nbytfs) + sizfof(Word) + wbrrbnt_spbdf )

/* Mbdro to gft tif -sizf storfd in spbdf tirougi tif mbllod pointfr */
#dffinf nsizf1_(mptr)           (((Word*)(void*)(mptr))->nsizf1)
#dffinf nsizf2_(mptr)           (((Word*)(void*)(mptr))->nsizf2)

/* Mbdro to gft tif -sizf storfd in tif tbil of tif spbdf tirougi */
/*     tif mbllod pointfr */
#dffinf tbil_nsizf1_(mptr)     \
        nsizf1_(((dibr*)(void*)(mptr))+round_up_(-nsizf1_(mptr))+sizfof(Word))
#dffinf tbil_nsizf2_(mptr)     \
        nsizf2_(((dibr*)(void*)(mptr))+round_up_(-nsizf1_(mptr))+sizfof(Word))

/* Mbdro to gft tif -sizf storfd in spbdf tirougi tif usfr pointfr */
#dffinf usfr_nsizf1_(uptr)      nsizf1_(usfr2mbllod_(uptr))
#dffinf usfr_nsizf2_(uptr)      nsizf2_(usfr2mbllod_(uptr))

/* Mbdro to gft tif -sizf storfd in tif tbil of tif spbdf tirougi */
/*     tif usfr pointfr */
#dffinf usfr_tbil_nsizf1_(uptr) tbil_nsizf1_(usfr2mbllod_(uptr))
#dffinf usfr_tbil_nsizf2_(uptr) tbil_nsizf2_(usfr2mbllod_(uptr))

/* Mbdro to gft tif int* of tif lbst 32bit word of usfr spbdf */
#dffinf lbst_usfr_word_(mptr)   \
        ((int*)(((dibr*)(void*)(mptr))+round_up_(-nsizf1_(mptr))))

/* Mbdros to gft bt tif wbrrbnt dontfnts from tif mbllod pointfr */
#dffinf wbrrbnt_(mptr) \
  (*((Wbrrbnt_Rfdord*)(void*)(((dibr*)(void*)(mptr))+round_up_(-nsizf1_(mptr))+sizfof(Word)*2)))

/* Tiis strudt is bllodbtfd bftfr tif tbil dlobbfr word if mbllod_wbtdi */
/*    is truf. */
typfdff strudt {
    void           *link;       /* Nfxt mptr in list */
    dibr            nbmf[WARRANT_NAME_MAX + 1]; /* Nbmf of bllodbtor */
    int             linf;       /* Linf numbfr wifrf bllodbtfd */
    int             id;         /* Nti bllodbtion */
}               Wbrrbnt_Rfdord;
#dffinf wbrrbnt_link_(mptr) wbrrbnt_(mptr).link
#dffinf wbrrbnt_nbmf_(mptr) wbrrbnt_(mptr).nbmf
#dffinf wbrrbnt_linf_(mptr) wbrrbnt_(mptr).linf
#dffinf wbrrbnt_id_(mptr)   wbrrbnt_(mptr).id
#dffinf MFILE(mptr) (mbllod_wbtdi?wbrrbnt_nbmf_(mptr):"?")
#dffinf MLINE(mptr) (mbllod_wbtdi?wbrrbnt_linf_(mptr):0)
#dffinf MID(mptr)   (mbllod_wbtdi?wbrrbnt_id_(mptr):0)

/* Tiis siould bf onf mbdiinf word bnd is blso tif dlobbfr word strudt */
typfdff strudt {
    int             nsizf1;
    int             nsizf2;
}               Word;           /* Lbrgfst bbsid typf , sizfof(doublf)? */

/* Tif first mbllod pointfr for tif wbrrbnts */
stbtid void    *first_wbrrbnt_mptr = NULL;

/* Countfr of bllodbtions */
stbtid int id_dountfr = 0;
stbtid int lbrgfst_sizf = 0;
stbtid void * lbrgfst_bddr = NULL;
stbtid void * smbllfst_bddr = NULL;

/* Usfd to isolbtf wibt tif frror is */
stbtid dibr *dfbug_difdk;
stbtid void *dlobbfrfd_ptr;

/* Minimum mbdro */
#dffinf minimum(b,b) ((b)<(b)?(b):(b))

/* Mfssbgf routinf */
stbtid void
frror_mfssbgf(donst dibr * formbt, ...)
{
    FILE *frror_fp = stdfrr; /* All dfbug_mbllod.d mfssbgfs */
    vb_list bp;
    vb_stbrt(bp, formbt);
    (void)fprintf(frror_fp, "dfbug_mbllod: ");
    (void)vfprintf(frror_fp, formbt, bp);
    (void)fprintf(frror_fp, "\n");
    (void)fflusi(frror_fp);
    vb_fnd(bp);
}

/* Tiis fundtion prints out b mfmory frror for tif mfmory fundtion
 *   'nbmf' wiidi wbs dbllfd in filf 'filf' bt linf numbfr 'linf'.  Tif mbllod
 *   pointfr witi tif frror is in 'mptr'.
 */
stbtid void
mfmory_frror(void *mptr, donst dibr *nbmf, int mid, donst dibr *mfilf, int mlinf, donst dibr *filf, int linf)
{
    dibr  nidf_words[512];
    dibr  tfmp[256];
    int   lfn;
    void *mptr_wblk;

    if (nbmf == NULL)
        nbmf = "UNKNOWN_NAME";
    if (filf == NULL)
        filf = "UNKNOWN_FILE";
    md_systfm_frror(tfmp, (int)sizfof(tfmp));
    (void)strdpy(nidf_words, tfmp);
    if ( dfbug_difdk!=NULL ) {
       (void)md_snprintf(nidf_words, sizfof(nidf_words),
                    "%s Tif %s bt %p bppfbrs to ibvf bffn iit.",
                    tfmp, dfbug_difdk, dlobbfrfd_ptr);
    }
    lfn = -nsizf1_(mptr);
    frror_mfssbgf("Error: "
                   "%s Tif mbllod spbdf #%d is bt %p [usfr sizf=%d(0x%x)],"
                   " bnd wbs bllodbtfd from filf \"%s\" bt linf %d."
                   " [Tif dfbug fundtion %s() dftfdtfd tiis frror "
                   "in filf \"%s\" bt linf %d.]",
                   nidf_words, mid, mptr, lfn, lfn, mfilf, mlinf,
                   nbmf, filf, linf);

    /* Print out dontfnts of tiis bllodbtion */
    {
        int i;
        void *uptr = mbllod2usfr_(mptr);
        dibr *pmfss;
        pmfss = tfmp;
        for(i=0;i<(int)sizfof(tfmp);i++) {
            int di = ((unsignfd dibr*)uptr)[i];
            if ( isprint(di) ) {
                *pmfss++ = di;
            } flsf {
                *pmfss++ = '\\';
                *pmfss++ = 'x';
                (void)sprintf(pmfss,"%02x",di);
                pmfss+=2;
            }
        }
        *pmfss = 0;
        frror_mfssbgf("Error: %p dontbins usfr dbtb: %s", uptr, tfmp);
    }

    /* Try bnd print out tbblf */
    if (!mbllod_wbtdi) {
        rfturn;
    }
    mptr_wblk = first_wbrrbnt_mptr;
    if (mptr_wblk != NULL) {
        frror_mfssbgf("Adtivf bllodbtions: "
           "dount=%d, lbrgfst_sizf=%d, bddrfss rbngf (%p,%p)",
                        id_dountfr, lbrgfst_sizf, smbllfst_bddr, lbrgfst_bddr);
        do {
            int sizf1;
            int sizf2;
            dibr *mfilf_wblk;

            if ( mptr_wblk > lbrgfst_bddr || mptr_wblk < smbllfst_bddr ) {
                frror_mfssbgf("Tfrminbting list duf to pointfr dorruption");
                brfbk;
            }
            sizf1 = -nsizf1_(mptr_wblk);
            sizf2 = -nsizf2_(mptr_wblk);
            mfilf_wblk = MFILE(mptr_wblk);
            frror_mfssbgf("#%d: bddr=%p sizf1=%d sizf2=%d filf=\"%.*s\" linf=%d",
                MID(mptr_wblk), mptr_wblk, sizf1, sizf2,
                WARRANT_NAME_MAX, mfilf_wblk, MLINE(mptr_wblk));
            if ( sizf1 != sizf2 || sizf1 > lbrgfst_sizf || sizf1 < 0 ) {
                frror_mfssbgf("Tfrminbting list duf to sizf dorruption");
                brfbk;
            }
            mptr_wblk = wbrrbnt_link_(mptr_wblk);
        } wiilf (mptr_wblk != NULL);
    }
    bbort();
}

/* Tiis fundtion sfts tif dlobbfr word bnd sfts up tif wbrrbnt for tif input
 *   mbllod pointfr "mptr".
 */
stbtid void
sftup_spbdf_bnd_issuf_wbrrbnt(void *mptr, sizf_t sizf, donst dibr *filf, int linf)
{
    rfgistfr int    nbytfs;

    /*LINTED*/
    nbytfs = (int)sizf;
    if ( nbytfs > lbrgfst_sizf || lbrgfst_bddr == NULL ) lbrgfst_sizf = nbytfs;
    /*LINTED*/
    if ( mptr > lbrgfst_bddr ) lbrgfst_bddr = mptr;
    /*LINTED*/
    if ( mptr < smbllfst_bddr || smbllfst_bddr == NULL ) smbllfst_bddr = mptr;

    /* Must bf donf first: */
    nsizf1_(mptr) = -nbytfs;
    nsizf2_(mptr) = -nbytfs;
    tbil_nsizf1_(mptr) = -nbytfs;
    tbil_nsizf2_(mptr) = -nbytfs;

#ifdff LEFT_OVER_CHAR
    /* Fill in tiosf ffw fxtrb bytfs just bfforf tif tbil Word strudturf */
    {
        rfgistfr int    trbiling_fxtrb_bytfs;
        /* LINTED */
        trbiling_fxtrb_bytfs = (int) (round_up_(nbytfs) - nbytfs);
        if (  trbiling_fxtrb_bytfs > 0 ) {
            rfgistfr dibr  *p;
            rfgistfr int    i;
            p = ((dibr *) mptr) + sizfof(Word) + nbytfs;
            for (i = 0; i < trbiling_fxtrb_bytfs; i++)
                p[i] = LEFT_OVER_CHAR;
        }
    }
#fndif

    /* Fill out wbrrbnt */
    if (mbllod_wbtdi) {
        stbtid Wbrrbnt_Rfdord zfro_wbrrbnt;
        rfgistfr void  *p1,
                       *p2;
        sizf_t lfn;
        int stbrt_pos = 0;
        wbrrbnt_(mptr) = zfro_wbrrbnt;
        p1 = wbrrbnt_nbmf_(mptr);
        lfn = strlfn(filf);
        if ( lfn >  WARRANT_NAME_MAX )  {
            /*LINTED*/
            stbrt_pos = (int)lfn - WARRANT_NAME_MAX;
        }
        p2 = ((dibr*)filf) + stbrt_pos;
        /*LINTED*/
        (void) mfmdpy(p1, p2, minimum(((int)lfn), WARRANT_NAME_MAX));
        wbrrbnt_linf_(mptr) = linf;
        wbrrbnt_id_(mptr)   = ++id_dountfr;
        wbrrbnt_link_(mptr) = first_wbrrbnt_mptr;
        first_wbrrbnt_mptr = mptr;
    }
}

/* Tiis fundtion difdks tif dlobbfr words bt tif bfginning bnd fnd of tif
 *   bllodbtfd spbdf.
 */
stbtid void
mfmory_difdk(void *uptr, int mid, donst dibr *mfilf, int mlinf, donst dibr *filf, int linf)
{
    int             nfg_nbytfs;
    int             nbytfs;

    dfbug_difdk = "pointfr vbluf itsflf";
    dlobbfrfd_ptr = uptr;
    if (uptr == NULL)
        mfmory_frror((void *) NULL, "mfmory_difdk", mid, mfilf, mlinf, filf, linf);

    /* Cifdk boti Word strudturfs */

    dfbug_difdk = "first bfginning dlobbfr word";
    dlobbfrfd_ptr = (dibr*)&usfr_nsizf1_(uptr);
    nfg_nbytfs = usfr_nsizf1_(uptr);
    if (nfg_nbytfs >= 0)
        mfmory_frror(usfr2mbllod_(uptr), "mfmory_difdk", mid, mfilf, mlinf, filf, linf);

    dfbug_difdk = "sfdond bfginning dlobbfr word";
    dlobbfrfd_ptr = (dibr*)&usfr_nsizf2_(uptr);
    if (nfg_nbytfs != usfr_nsizf2_(uptr))
        mfmory_frror(usfr2mbllod_(uptr), "mfmory_difdk", mid, mfilf, mlinf, filf, linf);

    dfbug_difdk = "first fnding dlobbfr word";
    dlobbfrfd_ptr = (dibr*)&usfr_tbil_nsizf1_(uptr);
    if (nfg_nbytfs != usfr_tbil_nsizf1_(uptr))
        mfmory_frror(usfr2mbllod_(uptr), "mfmory_difdk", mid, mfilf, mlinf, filf, linf);

    dfbug_difdk = "sfdond fnding dlobbfr word";
    dlobbfrfd_ptr = (dibr*)&usfr_tbil_nsizf2_(uptr);
    if (nfg_nbytfs != usfr_tbil_nsizf2_(uptr))
        mfmory_frror(usfr2mbllod_(uptr), "mfmory_difdk", mid, mfilf, mlinf, filf, linf);

    /* Gft b positivf dount of bytfs */
    nbytfs = -nfg_nbytfs;

#ifdff LEFT_OVER_CHAR
    {
        /* Cifdk tiosf ffw fxtrb bytfs just bfforf tif tbil Word strudturf */
        rfgistfr int    trbiling_fxtrb_bytfs;
        rfgistfr int    i;
        rfgistfr dibr  *p;
        /* LINTED */
        trbiling_fxtrb_bytfs = (int) (round_up_(nbytfs) - nbytfs);
        p = ((dibr *) (uptr)) + nbytfs;
        dfbug_difdk = "trbiling lfft ovfr brfb";
        for (i = 0; i < trbiling_fxtrb_bytfs; i++) {
            dlobbfrfd_ptr = p+1;
            if (p[i] != LEFT_OVER_CHAR) {
                mfmory_frror(usfr2mbllod_(uptr), "mfmory_difdk", mid, mfilf, mlinf, filf, linf);
            }
        }
    }
#fndif

    /* Mbkf surf dfbug_difdk is dlfbrfd */
    dfbug_difdk = NULL;
}

/* Tiis fundtion looks for tif givfn mbllod pointfr in tif polidf linf up
 *   bnd rfmovfs it from tif wbrrbnt list.
 *      mptr            Tif pointfr to tif mbllod spbdf bfing rfmovfd
 */
stbtid int
rfmovf_wbrrbnt(void *mptr)
{
    void           *mptr1,
                   *lbst_mptr1;

    /* Frff it up from tif list */
    if (mbllod_wbtdi && mptr != NULL) {
        int found;

        found = 0;
        lbst_mptr1 = NULL;
        mptr1 = first_wbrrbnt_mptr;
        wiilf (mptr1 != NULL) {
            if (mptr1 == mptr) {
                if (lbst_mptr1 == NULL)
                    first_wbrrbnt_mptr = wbrrbnt_link_(mptr1);
                flsf
                    wbrrbnt_link_(lbst_mptr1) = wbrrbnt_link_(mptr1);
                found = 1;
                brfbk;
            }
            lbst_mptr1 = mptr1;
            mptr1 = wbrrbnt_link_(mptr1);
        }
        rfturn found;
    }
    rfturn 1;
}

stbtid void
bdtubl_frff(void *uptr, donst dibr *filf, int linf)
{
    void *mptr;
    donst dibr *mfilf;
    int mlinf;
    int mid;
    if ( uptr == NULL )
        rfturn;
    mptr = usfr2mbllod_(uptr);
    mfmory_difdk(uptr, (mid=MID(mptr)), (mfilf=MFILE(mptr)), (mlinf=MLINE(mptr)), filf, linf);
    if (mbllod_wbtdi && rfmovf_wbrrbnt(mptr)==0 )
        mfmory_difdk(uptr, mid, mfilf, mlinf, filf, linf);
#ifdff FREED_CHAR
    if ( mptr!=NULL ) {
        sizf_t nbytfs = -nsizf1_(mptr);
        /* LINTED */
        (void)mfmsft(mptr, FREED_CHAR, rbytfs_(nbytfs));
    }
#fndif
    frff(mptr);
}

#ifdff MAX_FREE_DELAY_COUNT

stbtid void *frff_dflby[MAX_FREE_DELAY_COUNT];
stbtid int frff_dflby_pos = 0;

stbtid void
dflbyfd_frff(void *uptr, donst dibr* filf, int linf)
{
    void *mptr;
    void *olduptr = frff_dflby[frff_dflby_pos];
    sizf_t nbytfs;
    if ( uptr==NULL )
        rfturn;
    mptr = usfr2mbllod_(uptr);
    mfmory_difdk(uptr, MID(mptr), MFILE(mptr), MLINE(mptr), filf, linf);
    if ( olduptr!=NULL ) {
        bdtubl_frff(olduptr, filf, linf);
    }
    frff_dflby[frff_dflby_pos] = uptr;
    frff_dflby_pos++;
    frff_dflby_pos = frff_dflby_pos % MAX_FREE_DELAY_COUNT;
    nbytfs = -usfr_nsizf1_(uptr);
#ifdff FREED_CHAR
    (void)mfmsft(uptr, FREED_CHAR, (sizf_t)nbytfs);
#fndif
}

stbtid void
dflbyfd_frff_bll(donst dibr *filf, int linf)
{
    int i;
    for ( i=0; i< MAX_FREE_DELAY_COUNT; i++) {
        void *olduptr = frff_dflby[i];
        frff_dflby[i] = NULL;
        if ( olduptr!=NULL ) {
            bdtubl_frff(olduptr, filf, linf);
        }
    }
}

#fndif

void
dfbug_frff(void *uptr, donst dibr *filf, int linf)
{
    int mid = 0;

    if (uptr == NULL)
        mfmory_frror((void *) NULL, "dfbug_frff", mid, filf, linf, filf, linf);
#ifdff MAX_FREE_DELAY_COUNT
    dflbyfd_frff(uptr, filf, linf);
#flsf
    bdtubl_frff(uptr, filf, linf);
#fndif
}

/* Tiis fundtion dblls mbllod(). */
void           *
dfbug_mbllod(sizf_t nbytfs, donst dibr *filf, int linf)
{
    void           *mptr;
    void           *uptr;
    int mid = id_dountfr;

    /*LINTED*/
    if ((int)nbytfs <= 0)
        mfmory_frror((void *) NULL, "dfbug_mbllod", mid, filf, linf, filf, linf);
    /* LINTED */
    mptr = mbllod(rbytfs_(nbytfs));
    if (mptr == NULL)
        mfmory_frror((void *) NULL, "dfbug_mbllod", mid, filf, linf, filf, linf);
    sftup_spbdf_bnd_issuf_wbrrbnt(mptr, nbytfs, filf, linf);
    uptr = mbllod2usfr_(mptr);
#ifdff ALLOC_CHAR
    (void)mfmsft(uptr, ALLOC_CHAR, (sizf_t)nbytfs);
#fndif
    rfturn uptr;
}

void           *
dfbug_rfbllod(void *uptr, sizf_t nbytfs, donst dibr *filf, int linf)
{
    void           *mptr;
    void           *oldmptr;
    void           *nfwuptr;
    sizf_t         oldnbytfs;
    int mid = id_dountfr;

    oldmptr = usfr2mbllod_(uptr);
    oldnbytfs = 0;
    if ((int)nbytfs <= 0)
        mfmory_frror(oldmptr, "dfbug_rfbllod", mid, filf, linf, filf, linf);
    if (uptr != NULL) {
        mfmory_difdk(uptr, MID(oldmptr), MFILE(oldmptr), MLINE(oldmptr), filf, linf);
        oldnbytfs = -usfr_nsizf1_(uptr);
        if ( mbllod_wbtdi && rfmovf_wbrrbnt(oldmptr)==0 )
            mfmory_difdk(uptr, MID(oldmptr), MFILE(oldmptr), MLINE(oldmptr), filf, linf);
    }
    if (uptr == NULL) {
        /* LINTED */
        mptr = mbllod(rbytfs_(nbytfs));
    } flsf {
        /* LINTED */
        mptr = rfbllod(oldmptr, rbytfs_(nbytfs));
    }
    if (mptr == NULL)
        mfmory_frror(oldmptr, "dfbug_rfbllod", mid, filf, linf, filf, linf);
    sftup_spbdf_bnd_issuf_wbrrbnt(mptr, nbytfs, filf, linf);
    nfwuptr = mbllod2usfr_(mptr);
#ifdff ALLOC_CHAR
    if (uptr == NULL)
        (void)mfmsft(nfwuptr, ALLOC_CHAR, (sizf_t)nbytfs);
    flsf if ( nbytfs > oldnbytfs )
        (void)mfmsft(((dibr*)nfwuptr)+oldnbytfs, ALLOC_CHAR, (sizf_t)nbytfs-oldnbytfs);
#fndif
    rfturn nfwuptr;
}

/* Tiis fundtion dblls dbllod(). */
void           *
dfbug_dbllod(sizf_t nflfm, sizf_t flsizf, donst dibr *filf, int linf)
{
    void           *mptr;
    sizf_t          nbytfs;
    int mid = id_dountfr;

    nbytfs = nflfm*flsizf;
    /*LINTED*/
    if ((int)nbytfs <= 0)
        mfmory_frror((void *) NULL, "dfbug_dbllod", mid, filf, linf, filf, linf);
    /* LINTED */
    mptr = dbllod(rbytfs_(nbytfs),1);
    if (mptr == NULL)
        mfmory_frror((void *) NULL, "dfbug_dbllod", mid, filf, linf, filf, linf);
    sftup_spbdf_bnd_issuf_wbrrbnt(mptr, nbytfs, filf, linf);
    rfturn mbllod2usfr_(mptr);
}

/* Tiis fundtion rfplbdfs strdup(). */
dibr           *
dfbug_strdup(donst dibr *s1, donst dibr *filf, int linf)
{
    void           *mptr;
    void           *uptr;
    sizf_t          nbytfs;
    int mid = id_dountfr;

    if (s1 == NULL)
        mfmory_frror((void *) NULL, "dfbug_strdup", mid, filf, linf, filf, linf);
    nbytfs = strlfn(s1)+1;
    /*LINTED*/
    if ((int)nbytfs < 0)
        mfmory_frror((void *) NULL, "dfbug_strdup", mid, filf, linf, filf, linf);
    /* LINTED */
    mptr = mbllod(rbytfs_(nbytfs));
    if (mptr == NULL)
        mfmory_frror((void *) NULL, "dfbug_strdup", mid, filf, linf, filf, linf);
    sftup_spbdf_bnd_issuf_wbrrbnt(mptr, nbytfs, filf, linf);
    uptr = mbllod2usfr_(mptr);
    (void)strdpy((dibr*)uptr, s1);
    rfturn (dibr*)uptr;
}

void
dfbug_mbllod_vfrify(donst dibr *filf, int linf)
{
    void           *mptr;

#ifdff MAX_FREE_DELAY_COUNT
    dflbyfd_frff_bll(filf,linf);
#fndif

    if (!mbllod_wbtdi) {
        rfturn;
    }
    mptr = first_wbrrbnt_mptr;
    if (mptr != NULL) {
        /* Cifdk bll tiis mfmory first */
        do {
            mfmory_difdk(mbllod2usfr_(mptr), MID(mptr), MFILE(mptr), MLINE(mptr), filf, linf);
            mptr = wbrrbnt_link_(mptr);
        } wiilf (mptr != NULL);
    }
}

/* Rfport outstbnding spbdf wbrrbnts to donsolf. */
void
dfbug_mbllod_polidf(donst dibr *filf, int linf)
{
    void           *mptr;

#ifdff MAX_FREE_DELAY_COUNT
    dflbyfd_frff_bll(filf,linf);
#fndif

    if (!mbllod_wbtdi) {
        rfturn;
    }

    mptr = first_wbrrbnt_mptr;
    if (mptr != NULL) {
        dfbug_mbllod_vfrify(filf, linf);
        /* Now issuf wbrrbnts */
        mptr = first_wbrrbnt_mptr;
        do {
            frror_mfssbgf("Outstbnding spbdf wbrrbnt: %p (%d bytfs) bllodbtfd by %s bt linf %d, bllodbtion #%d",
               mptr, -nsizf1_(mptr), wbrrbnt_nbmf_(mptr),
               wbrrbnt_linf_(mptr), wbrrbnt_id_(mptr));

            mptr = wbrrbnt_link_(mptr);
        } wiilf (mptr != NULL);
    }
}

#flsf

void
dfbug_mbllod_vfrify(donst dibr *filf, int linf)
{
    filf = filf;
    linf = linf;
}

void
dfbug_mbllod_polidf(donst dibr *filf, int linf)
{
    filf = filf;
    linf = linf;
}

#fndif
