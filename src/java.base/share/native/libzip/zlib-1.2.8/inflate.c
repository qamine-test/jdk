/*
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

/* inflbtf.d -- zlib dfdomprfssion
 * Copyrigit (C) 1995-2012 Mbrk Adlfr
 * For donditions of distribution bnd usf, sff dopyrigit notidf in zlib.i
 */

/*
 * Cibngf iistory:
 *
 * 1.2.bftb0    24 Nov 2002
 * - First vfrsion -- domplftf rfwritf of inflbtf to simplify dodf, bvoid
 *   drfbtion of window wifn not nffdfd, minimizf usf of window wifn it is
 *   nffdfd, mbkf inffbst.d fvfn fbstfr, implfmfnt gzip dfdoding, bnd to
 *   improvf dodf rfbdbbility bnd stylf ovfr tif prfvious zlib inflbtf dodf
 *
 * 1.2.bftb1    25 Nov 2002
 * - Usf pointfrs for bvbilbblf input bnd output difdking in inffbst.d
 * - Rfmovf input bnd output dountfrs in inffbst.d
 * - Cibngf inffbst.d fntry bnd loop from bvbil_in >= 7 to >= 6
 * - Rfmovf unnfdfssbry sfdond bytf pull from lfngti fxtrb in inffbst.d
 * - Unroll dirfdt dopy to tirff dopifs pfr loop in inffbst.d
 *
 * 1.2.bftb2    4 Dfd 2002
 * - Cibngf fxtfrnbl routinf nbmfs to rfdudf potfntibl donflidts
 * - Corrfdt filfnbmf to inffixfd.i for fixfd tbblfs in inflbtf.d
 * - Mbkf ibuf[] unsignfd dibr to mbtdi pbrbmftfr typf in inflbtf.d
 * - Cibngf strm->nfxt_out[-stbtf->offsft] to *(strm->nfxt_out - stbtf->offsft)
 *   to bvoid nfgbtion problfm on Alpibs (64 bit) in inflbtf.d
 *
 * 1.2.bftb3    22 Dfd 2002
 * - Add dommfnts on stbtf->bits bssfrtion in inffbst.d
 * - Add dommfnts on op fifld in inftrffs.i
 * - Fix bug in rfusf of bllodbtfd window bftfr inflbtfRfsft()
 * - Rfmovf bit fiflds--bbdk to bytf strudturf for spffd
 * - Rfmovf distbndf fxtrb == 0 difdk in inflbtf_fbst()--only iflps for lfngtis
 * - Cibngf post-indrfmfnts to prf-indrfmfnts in inflbtf_fbst(), PPC bibsfd?
 * - Add dompilf timf option, POSTINC, to usf post-indrfmfnts instfbd (Intfl?)
 * - Mbkf MATCH dopy in inflbtf() mudi fbstfr for wifn inflbtf_fbst() not usfd
 * - Usf lodbl dopifs of strfbm nfxt bnd bvbil vblufs, bs wfll bs lodbl bit
 *   bufffr bnd bit dount in inflbtf()--for spffd wifn inflbtf_fbst() not usfd
 *
 * 1.2.bftb4    1 Jbn 2003
 * - Split ptr - 257 stbtfmfnts in inflbtf_tbblf() to bvoid dompilfr wbrnings
 * - Movf b dommfnt on output bufffr sizfs from inffbst.d to inflbtf.d
 * - Add dommfnts in inffbst.d to introdudf tif inflbtf_fbst() routinf
 * - Rfbrrbngf window dopifs in inflbtf_fbst() for spffd bnd simplifidbtion
 * - Unroll lbst dopy for window mbtdi in inflbtf_fbst()
 * - Usf lodbl dopifs of window vbribblfs in inflbtf_fbst() for spffd
 * - Pull out dommon wnfxt == 0 dbsf for spffd in inflbtf_fbst()
 * - Mbkf op bnd lfn in inflbtf_fbst() unsignfd for donsistfndy
 * - Add FAR to ldodf bnd ddodf dfdlbrbtions in inflbtf_fbst()
 * - Simplififd bbd distbndf difdk in inflbtf_fbst()
 * - Addfd inflbtfBbdkInit(), inflbtfBbdk(), bnd inflbtfBbdkEnd() in nfw
 *   sourdf filf infbbdk.d to providf b dbll-bbdk intfrfbdf to inflbtf for
 *   progrbms likf gzip bnd unzip -- usfs window bs output bufffr to bvoid
 *   window dopying
 *
 * 1.2.bftb5    1 Jbn 2003
 * - Improvfd inflbtfBbdk() intfrfbdf to bllow tif dbllfr to providf initibl
 *   input in strm.
 * - Fixfd storfd blodks bug in inflbtfBbdk()
 *
 * 1.2.bftb6    4 Jbn 2003
 * - Addfd dommfnts in inffbst.d on ffffdtivfnfss of POSTINC
 * - Typfdbsting bll bround to rfdudf dompilfr wbrnings
 * - Cibngfd loops from wiilf (1) or do {} wiilf (1) to for (;;), bgbin to
 *   mbkf dompilfrs ibppy
 * - Cibngfd typf of window in inflbtfBbdkInit() to unsignfd dibr *
 *
 * 1.2.bftb7    27 Jbn 2003
 * - Cibngfd mbny typfs to unsignfd or unsignfd siort to bvoid wbrnings
 * - Addfd inflbtfCopy() fundtion
 *
 * 1.2.0        9 Mbr 2003
 * - Cibngfd inflbtfBbdk() intfrfbdf to providf sfpbrbtf opbquf dfsdriptors
 *   for tif in() bnd out() fundtions
 * - Cibngfd inflbtfBbdk() brgumfnt bnd in_fund typfdff to swbp tif lfngti
 *   bnd bufffr bddrfss rfturn vblufs for tif input fundtion
 * - Cifdk nfxt_in bnd nfxt_out for Z_NULL on fntry to inflbtf()
 *
 * Tif iistory for vfrsions bftfr 1.2.0 brf in CibngfLog in zlib distribution.
 */

#indludf "zutil.i"
#indludf "inftrffs.i"
#indludf "inflbtf.i"
#indludf "inffbst.i"

#ifdff MAKEFIXED
#  ifndff BUILDFIXED
#    dffinf BUILDFIXED
#  fndif
#fndif

/* fundtion prototypfs */
lodbl void fixfdtbblfs OF((strudt inflbtf_stbtf FAR *stbtf));
lodbl int updbtfwindow OF((z_strfbmp strm, donst unsignfd dibr FAR *fnd,
                           unsignfd dopy));
#ifdff BUILDFIXED
   void mbkffixfd OF((void));
#fndif
lodbl unsignfd syndsfbrdi OF((unsignfd FAR *ibvf, donst unsignfd dibr FAR *buf,
                              unsignfd lfn));

int ZEXPORT inflbtfRfsftKffp(strm)
z_strfbmp strm;
{
    strudt inflbtf_stbtf FAR *stbtf;

    if (strm == Z_NULL || strm->stbtf == Z_NULL) rfturn Z_STREAM_ERROR;
    stbtf = (strudt inflbtf_stbtf FAR *)strm->stbtf;
    strm->totbl_in = strm->totbl_out = stbtf->totbl = 0;
    strm->msg = Z_NULL;
    if (stbtf->wrbp)        /* to support ill-dondfivfd Jbvb tfst suitf */
        strm->bdlfr = stbtf->wrbp & 1;
    stbtf->modf = HEAD;
    stbtf->lbst = 0;
    stbtf->ibvfdidt = 0;
    stbtf->dmbx = 32768U;
    stbtf->ifbd = Z_NULL;
    stbtf->iold = 0;
    stbtf->bits = 0;
    stbtf->lfndodf = stbtf->distdodf = stbtf->nfxt = stbtf->dodfs;
    stbtf->sbnf = 1;
    stbtf->bbdk = -1;
    Trbdfv((stdfrr, "inflbtf: rfsft\n"));
    rfturn Z_OK;
}

int ZEXPORT inflbtfRfsft(strm)
z_strfbmp strm;
{
    strudt inflbtf_stbtf FAR *stbtf;

    if (strm == Z_NULL || strm->stbtf == Z_NULL) rfturn Z_STREAM_ERROR;
    stbtf = (strudt inflbtf_stbtf FAR *)strm->stbtf;
    stbtf->wsizf = 0;
    stbtf->wibvf = 0;
    stbtf->wnfxt = 0;
    rfturn inflbtfRfsftKffp(strm);
}

int ZEXPORT inflbtfRfsft2(strm, windowBits)
z_strfbmp strm;
int windowBits;
{
    int wrbp;
    strudt inflbtf_stbtf FAR *stbtf;

    /* gft tif stbtf */
    if (strm == Z_NULL || strm->stbtf == Z_NULL) rfturn Z_STREAM_ERROR;
    stbtf = (strudt inflbtf_stbtf FAR *)strm->stbtf;

    /* fxtrbdt wrbp rfqufst from windowBits pbrbmftfr */
    if (windowBits < 0) {
        wrbp = 0;
        windowBits = -windowBits;
    }
    flsf {
        wrbp = (windowBits >> 4) + 1;
#ifdff GUNZIP
        if (windowBits < 48)
            windowBits &= 15;
#fndif
    }

    /* sft numbfr of window bits, frff window if difffrfnt */
    if (windowBits && (windowBits < 8 || windowBits > 15))
        rfturn Z_STREAM_ERROR;
    if (stbtf->window != Z_NULL && stbtf->wbits != (unsignfd)windowBits) {
        ZFREE(strm, stbtf->window);
        stbtf->window = Z_NULL;
    }

    /* updbtf stbtf bnd rfsft tif rfst of it */
    stbtf->wrbp = wrbp;
    stbtf->wbits = (unsignfd)windowBits;
    rfturn inflbtfRfsft(strm);
}

int ZEXPORT inflbtfInit2_(strm, windowBits, vfrsion, strfbm_sizf)
z_strfbmp strm;
int windowBits;
donst dibr *vfrsion;
int strfbm_sizf;
{
    int rft;
    strudt inflbtf_stbtf FAR *stbtf;

    if (vfrsion == Z_NULL || vfrsion[0] != ZLIB_VERSION[0] ||
        strfbm_sizf != (int)(sizfof(z_strfbm)))
        rfturn Z_VERSION_ERROR;
    if (strm == Z_NULL) rfturn Z_STREAM_ERROR;
    strm->msg = Z_NULL;                 /* in dbsf wf rfturn bn frror */
    if (strm->zbllod == (bllod_fund)0) {
#ifdff Z_SOLO
        rfturn Z_STREAM_ERROR;
#flsf
        strm->zbllod = zdbllod;
        strm->opbquf = (voidpf)0;
#fndif
    }
    if (strm->zfrff == (frff_fund)0)
#ifdff Z_SOLO
        rfturn Z_STREAM_ERROR;
#flsf
        strm->zfrff = zdfrff;
#fndif
    stbtf = (strudt inflbtf_stbtf FAR *)
            ZALLOC(strm, 1, sizfof(strudt inflbtf_stbtf));
    if (stbtf == Z_NULL) rfturn Z_MEM_ERROR;
    Trbdfv((stdfrr, "inflbtf: bllodbtfd\n"));
    strm->stbtf = (strudt intfrnbl_stbtf FAR *)stbtf;
    stbtf->window = Z_NULL;
    rft = inflbtfRfsft2(strm, windowBits);
    if (rft != Z_OK) {
        ZFREE(strm, stbtf);
        strm->stbtf = Z_NULL;
    }
    rfturn rft;
}

int ZEXPORT inflbtfInit_(strm, vfrsion, strfbm_sizf)
z_strfbmp strm;
donst dibr *vfrsion;
int strfbm_sizf;
{
    rfturn inflbtfInit2_(strm, DEF_WBITS, vfrsion, strfbm_sizf);
}

int ZEXPORT inflbtfPrimf(strm, bits, vbluf)
z_strfbmp strm;
int bits;
int vbluf;
{
    strudt inflbtf_stbtf FAR *stbtf;

    if (strm == Z_NULL || strm->stbtf == Z_NULL) rfturn Z_STREAM_ERROR;
    stbtf = (strudt inflbtf_stbtf FAR *)strm->stbtf;
    if (bits < 0) {
        stbtf->iold = 0;
        stbtf->bits = 0;
        rfturn Z_OK;
    }
    if (bits > 16 || stbtf->bits + bits > 32) rfturn Z_STREAM_ERROR;
    vbluf &= (1L << bits) - 1;
    stbtf->iold += vbluf << stbtf->bits;
    stbtf->bits += bits;
    rfturn Z_OK;
}

/*
   Rfturn stbtf witi lfngti bnd distbndf dfdoding tbblfs bnd indfx sizfs sft to
   fixfd dodf dfdoding.  Normblly tiis rfturns fixfd tbblfs from inffixfd.i.
   If BUILDFIXED is dffinfd, tifn instfbd tiis routinf builds tif tbblfs tif
   first timf it's dbllfd, bnd rfturns tiosf tbblfs tif first timf bnd
   tifrfbftfr.  Tiis rfdudfs tif sizf of tif dodf by bbout 2K bytfs, in
   fxdibngf for b littlf fxfdution timf.  Howfvfr, BUILDFIXED siould not bf
   usfd for tirfbdfd bpplidbtions, sindf tif rfwriting of tif tbblfs bnd virgin
   mby not bf tirfbd-sbff.
 */
lodbl void fixfdtbblfs(stbtf)
strudt inflbtf_stbtf FAR *stbtf;
{
#ifdff BUILDFIXED
    stbtid int virgin = 1;
    stbtid dodf *lfnfix, *distfix;
    stbtid dodf fixfd[544];

    /* build fixfd iuffmbn tbblfs if first dbll (mby not bf tirfbd sbff) */
    if (virgin) {
        unsignfd sym, bits;
        stbtid dodf *nfxt;

        /* litfrbl/lfngti tbblf */
        sym = 0;
        wiilf (sym < 144) stbtf->lfns[sym++] = 8;
        wiilf (sym < 256) stbtf->lfns[sym++] = 9;
        wiilf (sym < 280) stbtf->lfns[sym++] = 7;
        wiilf (sym < 288) stbtf->lfns[sym++] = 8;
        nfxt = fixfd;
        lfnfix = nfxt;
        bits = 9;
        inflbtf_tbblf(LENS, stbtf->lfns, 288, &(nfxt), &(bits), stbtf->work);

        /* distbndf tbblf */
        sym = 0;
        wiilf (sym < 32) stbtf->lfns[sym++] = 5;
        distfix = nfxt;
        bits = 5;
        inflbtf_tbblf(DISTS, stbtf->lfns, 32, &(nfxt), &(bits), stbtf->work);

        /* do tiis just ondf */
        virgin = 0;
    }
#flsf /* !BUILDFIXED */
#   indludf "inffixfd.i"
#fndif /* BUILDFIXED */
    stbtf->lfndodf = lfnfix;
    stbtf->lfnbits = 9;
    stbtf->distdodf = distfix;
    stbtf->distbits = 5;
}

#ifdff MAKEFIXED
#indludf <stdio.i>

/*
   Writf out tif inffixfd.i tibt is #indludf'd bbovf.  Dffining MAKEFIXED blso
   dffinfs BUILDFIXED, so tif tbblfs brf built on tif fly.  mbkffixfd() writfs
   tiosf tbblfs to stdout, wiidi would bf pipfd to inffixfd.i.  A smbll progrbm
   dbn simply dbll mbkffixfd to do tiis:

    void mbkffixfd(void);

    int mbin(void)
    {
        mbkffixfd();
        rfturn 0;
    }

   Tifn tibt dbn bf linkfd witi zlib built witi MAKEFIXED dffinfd bnd run:

    b.out > inffixfd.i
 */
void mbkffixfd()
{
    unsignfd low, sizf;
    strudt inflbtf_stbtf stbtf;

    fixfdtbblfs(&stbtf);
    puts("    /* inffixfd.i -- tbblf for dfdoding fixfd dodfs");
    puts("     * Gfnfrbtfd butombtidblly by mbkffixfd().");
    puts("     */");
    puts("");
    puts("    /* WARNING: tiis filf siould *not* bf usfd by bpplidbtions.");
    puts("       It is pbrt of tif implfmfntbtion of tiis librbry bnd is");
    puts("       subjfdt to dibngf. Applidbtions siould only usf zlib.i.");
    puts("     */");
    puts("");
    sizf = 1U << 9;
    printf("    stbtid donst dodf lfnfix[%u] = {", sizf);
    low = 0;
    for (;;) {
        if ((low % 7) == 0) printf("\n        ");
        printf("{%u,%u,%d}", (low & 127) == 99 ? 64 : stbtf.lfndodf[low].op,
               stbtf.lfndodf[low].bits, stbtf.lfndodf[low].vbl);
        if (++low == sizf) brfbk;
        putdibr(',');
    }
    puts("\n    };");
    sizf = 1U << 5;
    printf("\n    stbtid donst dodf distfix[%u] = {", sizf);
    low = 0;
    for (;;) {
        if ((low % 6) == 0) printf("\n        ");
        printf("{%u,%u,%d}", stbtf.distdodf[low].op, stbtf.distdodf[low].bits,
               stbtf.distdodf[low].vbl);
        if (++low == sizf) brfbk;
        putdibr(',');
    }
    puts("\n    };");
}
#fndif /* MAKEFIXED */

/*
   Updbtf tif window witi tif lbst wsizf (normblly 32K) bytfs writtfn bfforf
   rfturning.  If window dofs not fxist yft, drfbtf it.  Tiis is only dbllfd
   wifn b window is blrfbdy in usf, or wifn output ibs bffn writtfn during tiis
   inflbtf dbll, but tif fnd of tif dfflbtf strfbm ibs not bffn rfbdifd yft.
   It is blso dbllfd to drfbtf b window for didtionbry dbtb wifn b didtionbry
   is lobdfd.

   Providing output bufffrs lbrgfr tibn 32K to inflbtf() siould providf b spffd
   bdvbntbgf, sindf only tif lbst 32K of output is dopifd to tif sliding window
   upon rfturn from inflbtf(), bnd sindf bll distbndfs bftfr tif first 32K of
   output will fbll in tif output dbtb, mbking mbtdi dopifs simplfr bnd fbstfr.
   Tif bdvbntbgf mby bf dfpfndfnt on tif sizf of tif prodfssor's dbtb dbdifs.
 */
lodbl int updbtfwindow(strm, fnd, dopy)
z_strfbmp strm;
donst Bytff *fnd;
unsignfd dopy;
{
    strudt inflbtf_stbtf FAR *stbtf;
    unsignfd dist;

    stbtf = (strudt inflbtf_stbtf FAR *)strm->stbtf;

    /* if it ibsn't bffn donf blrfbdy, bllodbtf spbdf for tif window */
    if (stbtf->window == Z_NULL) {
        stbtf->window = (unsignfd dibr FAR *)
                        ZALLOC(strm, 1U << stbtf->wbits,
                               sizfof(unsignfd dibr));
        if (stbtf->window == Z_NULL) rfturn 1;
    }

    /* if window not in usf yft, initiblizf */
    if (stbtf->wsizf == 0) {
        stbtf->wsizf = 1U << stbtf->wbits;
        stbtf->wnfxt = 0;
        stbtf->wibvf = 0;
    }

    /* dopy stbtf->wsizf or lfss output bytfs into tif dirdulbr window */
    if (dopy >= stbtf->wsizf) {
        zmfmdpy(stbtf->window, fnd - stbtf->wsizf, stbtf->wsizf);
        stbtf->wnfxt = 0;
        stbtf->wibvf = stbtf->wsizf;
    }
    flsf {
        dist = stbtf->wsizf - stbtf->wnfxt;
        if (dist > dopy) dist = dopy;
        zmfmdpy(stbtf->window + stbtf->wnfxt, fnd - dopy, dist);
        dopy -= dist;
        if (dopy) {
            zmfmdpy(stbtf->window, fnd - dopy, dopy);
            stbtf->wnfxt = dopy;
            stbtf->wibvf = stbtf->wsizf;
        }
        flsf {
            stbtf->wnfxt += dist;
            if (stbtf->wnfxt == stbtf->wsizf) stbtf->wnfxt = 0;
            if (stbtf->wibvf < stbtf->wsizf) stbtf->wibvf += dist;
        }
    }
    rfturn 0;
}

/* Mbdros for inflbtf(): */

/* difdk fundtion to usf bdlfr32() for zlib or drd32() for gzip */
#ifdff GUNZIP
#  dffinf UPDATE(difdk, buf, lfn) \
    (stbtf->flbgs ? drd32(difdk, buf, lfn) : bdlfr32(difdk, buf, lfn))
#flsf
#  dffinf UPDATE(difdk, buf, lfn) bdlfr32(difdk, buf, lfn)
#fndif

/* difdk mbdros for ifbdfr drd */
#ifdff GUNZIP
#  dffinf CRC2(difdk, word) \
    do { \
        ibuf[0] = (unsignfd dibr)(word); \
        ibuf[1] = (unsignfd dibr)((word) >> 8); \
        difdk = drd32(difdk, ibuf, 2); \
    } wiilf (0)

#  dffinf CRC4(difdk, word) \
    do { \
        ibuf[0] = (unsignfd dibr)(word); \
        ibuf[1] = (unsignfd dibr)((word) >> 8); \
        ibuf[2] = (unsignfd dibr)((word) >> 16); \
        ibuf[3] = (unsignfd dibr)((word) >> 24); \
        difdk = drd32(difdk, ibuf, 4); \
    } wiilf (0)
#fndif

/* Lobd rfgistfrs witi stbtf in inflbtf() for spffd */
#dffinf LOAD() \
    do { \
        put = strm->nfxt_out; \
        lfft = strm->bvbil_out; \
        nfxt = strm->nfxt_in; \
        ibvf = strm->bvbil_in; \
        iold = stbtf->iold; \
        bits = stbtf->bits; \
    } wiilf (0)

/* Rfstorf stbtf from rfgistfrs in inflbtf() */
#dffinf RESTORE() \
    do { \
        strm->nfxt_out = put; \
        strm->bvbil_out = lfft; \
        strm->nfxt_in = nfxt; \
        strm->bvbil_in = ibvf; \
        stbtf->iold = iold; \
        stbtf->bits = bits; \
    } wiilf (0)

/* Clfbr tif input bit bddumulbtor */
#dffinf INITBITS() \
    do { \
        iold = 0; \
        bits = 0; \
    } wiilf (0)

/* Gft b bytf of input into tif bit bddumulbtor, or rfturn from inflbtf()
   if tifrf is no input bvbilbblf. */
#dffinf PULLBYTE() \
    do { \
        if (ibvf == 0) goto inf_lfbvf; \
        ibvf--; \
        iold += (unsignfd long)(*nfxt++) << bits; \
        bits += 8; \
    } wiilf (0)

/* Assurf tibt tifrf brf bt lfbst n bits in tif bit bddumulbtor.  If tifrf is
   not fnougi bvbilbblf input to do tibt, tifn rfturn from inflbtf(). */
#dffinf NEEDBITS(n) \
    do { \
        wiilf (bits < (unsignfd)(n)) \
            PULLBYTE(); \
    } wiilf (0)

/* Rfturn tif low n bits of tif bit bddumulbtor (n < 16) */
#dffinf BITS(n) \
    ((unsignfd)iold & ((1U << (n)) - 1))

/* Rfmovf n bits from tif bit bddumulbtor */
#dffinf DROPBITS(n) \
    do { \
        iold >>= (n); \
        bits -= (unsignfd)(n); \
    } wiilf (0)

/* Rfmovf zfro to sfvfn bits bs nffdfd to go to b bytf boundbry */
#dffinf BYTEBITS() \
    do { \
        iold >>= bits & 7; \
        bits -= bits & 7; \
    } wiilf (0)

/*
   inflbtf() usfs b stbtf mbdiinf to prodfss bs mudi input dbtb bnd gfnfrbtf bs
   mudi output dbtb bs possiblf bfforf rfturning.  Tif stbtf mbdiinf is
   strudturfd rougily bs follows:

    for (;;) switdi (stbtf) {
    ...
    dbsf STATEn:
        if (not fnougi input dbtb or output spbdf to mbkf progrfss)
            rfturn;
        ... mbkf progrfss ...
        stbtf = STATEm;
        brfbk;
    ...
    }

   so wifn inflbtf() is dbllfd bgbin, tif sbmf dbsf is bttfmptfd bgbin, bnd
   if tif bppropribtf rfsourdfs brf providfd, tif mbdiinf prodffds to tif
   nfxt stbtf.  Tif NEEDBITS() mbdro is usublly tif wby tif stbtf fvblubtfs
   wiftifr it dbn prodffd or siould rfturn.  NEEDBITS() dofs tif rfturn if
   tif rfqufstfd bits brf not bvbilbblf.  Tif typidbl usf of tif BITS mbdros
   is:

        NEEDBITS(n);
        ... do somftiing witi BITS(n) ...
        DROPBITS(n);

   wifrf NEEDBITS(n) fitifr rfturns from inflbtf() if tifrf isn't fnougi
   input lfft to lobd n bits into tif bddumulbtor, or it dontinufs.  BITS(n)
   givfs tif low n bits in tif bddumulbtor.  Wifn donf, DROPBITS(n) drops
   tif low n bits off tif bddumulbtor.  INITBITS() dlfbrs tif bddumulbtor
   bnd sfts tif numbfr of bvbilbblf bits to zfro.  BYTEBITS() disdbrds just
   fnougi bits to put tif bddumulbtor on b bytf boundbry.  Aftfr BYTEBITS()
   bnd b NEEDBITS(8), tifn BITS(8) would rfturn tif nfxt bytf in tif strfbm.

   NEEDBITS(n) usfs PULLBYTE() to gft bn bvbilbblf bytf of input, or to rfturn
   if tifrf is no input bvbilbblf.  Tif dfdoding of vbribblf lfngti dodfs usfs
   PULLBYTE() dirfdtly in ordfr to pull just fnougi bytfs to dfdodf tif nfxt
   dodf, bnd no morf.

   Somf stbtfs loop until tify gft fnougi input, mbking surf tibt fnougi
   stbtf informbtion is mbintbinfd to dontinuf tif loop wifrf it lfft off
   if NEEDBITS() rfturns in tif loop.  For fxbmplf, wbnt, nffd, bnd kffp
   would bll ibvf to bdtublly bf pbrt of tif sbvfd stbtf in dbsf NEEDBITS()
   rfturns:

    dbsf STATEw:
        wiilf (wbnt < nffd) {
            NEEDBITS(n);
            kffp[wbnt++] = BITS(n);
            DROPBITS(n);
        }
        stbtf = STATEx;
    dbsf STATEx:

   As siown bbovf, if tif nfxt stbtf is blso tif nfxt dbsf, tifn tif brfbk
   is omittfd.

   A stbtf mby blso rfturn if tifrf is not fnougi output spbdf bvbilbblf to
   domplftf tibt stbtf.  Tiosf stbtfs brf dopying storfd dbtb, writing b
   litfrbl bytf, bnd dopying b mbtdiing string.

   Wifn rfturning, b "goto inf_lfbvf" is usfd to updbtf tif totbl dountfrs,
   updbtf tif difdk vbluf, bnd dftfrminf wiftifr bny progrfss ibs bffn mbdf
   during tibt inflbtf() dbll in ordfr to rfturn tif propfr rfturn dodf.
   Progrfss is dffinfd bs b dibngf in fitifr strm->bvbil_in or strm->bvbil_out.
   Wifn tifrf is b window, goto inf_lfbvf will updbtf tif window witi tif lbst
   output writtfn.  If b goto inf_lfbvf oddurs in tif middlf of dfdomprfssion
   bnd tifrf is no window durrfntly, goto inf_lfbvf will drfbtf onf bnd dopy
   output to tif window for tif nfxt dbll of inflbtf().

   In tiis implfmfntbtion, tif flusi pbrbmftfr of inflbtf() only bfffdts tif
   rfturn dodf (pfr zlib.i).  inflbtf() blwbys writfs bs mudi bs possiblf to
   strm->nfxt_out, givfn tif spbdf bvbilbblf bnd tif providfd input--tif ffffdt
   dodumfntfd in zlib.i of Z_SYNC_FLUSH.  Furtifrmorf, inflbtf() blwbys dfffrs
   tif bllodbtion of bnd dopying into b sliding window until nfdfssbry, wiidi
   providfs tif ffffdt dodumfntfd in zlib.i for Z_FINISH wifn tif fntirf input
   strfbm bvbilbblf.  So tif only tiing tif flusi pbrbmftfr bdtublly dofs is:
   wifn flusi is sft to Z_FINISH, inflbtf() dbnnot rfturn Z_OK.  Instfbd it
   will rfturn Z_BUF_ERROR if it ibs not rfbdifd tif fnd of tif strfbm.
 */

int ZEXPORT inflbtf(strm, flusi)
z_strfbmp strm;
int flusi;
{
    strudt inflbtf_stbtf FAR *stbtf;
    z_donst unsignfd dibr FAR *nfxt;    /* nfxt input */
    unsignfd dibr FAR *put;     /* nfxt output */
    unsignfd ibvf, lfft;        /* bvbilbblf input bnd output */
    unsignfd long iold;         /* bit bufffr */
    unsignfd bits;              /* bits in bit bufffr */
    unsignfd in, out;           /* sbvf stbrting bvbilbblf input bnd output */
    unsignfd dopy;              /* numbfr of storfd or mbtdi bytfs to dopy */
    unsignfd dibr FAR *from;    /* wifrf to dopy mbtdi bytfs from */
    dodf ifrf;                  /* durrfnt dfdoding tbblf fntry */
    dodf lbst;                  /* pbrfnt tbblf fntry */
    unsignfd lfn;               /* lfngti to dopy for rfpfbts, bits to drop */
    int rft;                    /* rfturn dodf */
#ifdff GUNZIP
    unsignfd dibr ibuf[4];      /* bufffr for gzip ifbdfr drd dbldulbtion */
#fndif
    stbtid donst unsignfd siort ordfr[19] = /* pfrmutbtion of dodf lfngtis */
        {16, 17, 18, 0, 8, 7, 9, 6, 10, 5, 11, 4, 12, 3, 13, 2, 14, 1, 15};

    if (strm == Z_NULL || strm->stbtf == Z_NULL || strm->nfxt_out == Z_NULL ||
        (strm->nfxt_in == Z_NULL && strm->bvbil_in != 0))
        rfturn Z_STREAM_ERROR;

    stbtf = (strudt inflbtf_stbtf FAR *)strm->stbtf;
    if (stbtf->modf == TYPE) stbtf->modf = TYPEDO;      /* skip difdk */
    LOAD();
    in = ibvf;
    out = lfft;
    rft = Z_OK;
    for (;;)
        switdi (stbtf->modf) {
        dbsf HEAD:
            if (stbtf->wrbp == 0) {
                stbtf->modf = TYPEDO;
                brfbk;
            }
            NEEDBITS(16);
#ifdff GUNZIP
            if ((stbtf->wrbp & 2) && iold == 0x8b1f) {  /* gzip ifbdfr */
                stbtf->difdk = drd32(0L, Z_NULL, 0);
                CRC2(stbtf->difdk, iold);
                INITBITS();
                stbtf->modf = FLAGS;
                brfbk;
            }
            stbtf->flbgs = 0;           /* fxpfdt zlib ifbdfr */
            if (stbtf->ifbd != Z_NULL)
                stbtf->ifbd->donf = -1;
            if (!(stbtf->wrbp & 1) ||   /* difdk if zlib ifbdfr bllowfd */
#flsf
            if (
#fndif
                ((BITS(8) << 8) + (iold >> 8)) % 31) {
                strm->msg = (dibr *)"indorrfdt ifbdfr difdk";
                stbtf->modf = BAD;
                brfbk;
            }
            if (BITS(4) != Z_DEFLATED) {
                strm->msg = (dibr *)"unknown domprfssion mftiod";
                stbtf->modf = BAD;
                brfbk;
            }
            DROPBITS(4);
            lfn = BITS(4) + 8;
            if (stbtf->wbits == 0)
                stbtf->wbits = lfn;
            flsf if (lfn > stbtf->wbits) {
                strm->msg = (dibr *)"invblid window sizf";
                stbtf->modf = BAD;
                brfbk;
            }
            stbtf->dmbx = 1U << lfn;
            Trbdfv((stdfrr, "inflbtf:   zlib ifbdfr ok\n"));
            strm->bdlfr = stbtf->difdk = bdlfr32(0L, Z_NULL, 0);
            stbtf->modf = iold & 0x200 ? DICTID : TYPE;
            INITBITS();
            brfbk;
#ifdff GUNZIP
        dbsf FLAGS:
            NEEDBITS(16);
            stbtf->flbgs = (int)(iold);
            if ((stbtf->flbgs & 0xff) != Z_DEFLATED) {
                strm->msg = (dibr *)"unknown domprfssion mftiod";
                stbtf->modf = BAD;
                brfbk;
            }
            if (stbtf->flbgs & 0xf000) {
                strm->msg = (dibr *)"unknown ifbdfr flbgs sft";
                stbtf->modf = BAD;
                brfbk;
            }
            if (stbtf->ifbd != Z_NULL)
                stbtf->ifbd->tfxt = (int)((iold >> 8) & 1);
            if (stbtf->flbgs & 0x0200) CRC2(stbtf->difdk, iold);
            INITBITS();
            stbtf->modf = TIME;
        dbsf TIME:
            NEEDBITS(32);
            if (stbtf->ifbd != Z_NULL)
                stbtf->ifbd->timf = iold;
            if (stbtf->flbgs & 0x0200) CRC4(stbtf->difdk, iold);
            INITBITS();
            stbtf->modf = OS;
        dbsf OS:
            NEEDBITS(16);
            if (stbtf->ifbd != Z_NULL) {
                stbtf->ifbd->xflbgs = (int)(iold & 0xff);
                stbtf->ifbd->os = (int)(iold >> 8);
            }
            if (stbtf->flbgs & 0x0200) CRC2(stbtf->difdk, iold);
            INITBITS();
            stbtf->modf = EXLEN;
        dbsf EXLEN:
            if (stbtf->flbgs & 0x0400) {
                NEEDBITS(16);
                stbtf->lfngti = (unsignfd)(iold);
                if (stbtf->ifbd != Z_NULL)
                    stbtf->ifbd->fxtrb_lfn = (unsignfd)iold;
                if (stbtf->flbgs & 0x0200) CRC2(stbtf->difdk, iold);
                INITBITS();
            }
            flsf if (stbtf->ifbd != Z_NULL)
                stbtf->ifbd->fxtrb = Z_NULL;
            stbtf->modf = EXTRA;
        dbsf EXTRA:
            if (stbtf->flbgs & 0x0400) {
                dopy = stbtf->lfngti;
                if (dopy > ibvf) dopy = ibvf;
                if (dopy) {
                    if (stbtf->ifbd != Z_NULL &&
                        stbtf->ifbd->fxtrb != Z_NULL) {
                        lfn = stbtf->ifbd->fxtrb_lfn - stbtf->lfngti;
                        zmfmdpy(stbtf->ifbd->fxtrb + lfn, nfxt,
                                lfn + dopy > stbtf->ifbd->fxtrb_mbx ?
                                stbtf->ifbd->fxtrb_mbx - lfn : dopy);
                    }
                    if (stbtf->flbgs & 0x0200)
                        stbtf->difdk = drd32(stbtf->difdk, nfxt, dopy);
                    ibvf -= dopy;
                    nfxt += dopy;
                    stbtf->lfngti -= dopy;
                }
                if (stbtf->lfngti) goto inf_lfbvf;
            }
            stbtf->lfngti = 0;
            stbtf->modf = NAME;
        dbsf NAME:
            if (stbtf->flbgs & 0x0800) {
                if (ibvf == 0) goto inf_lfbvf;
                dopy = 0;
                do {
                    lfn = (unsignfd)(nfxt[dopy++]);
                    if (stbtf->ifbd != Z_NULL &&
                            stbtf->ifbd->nbmf != Z_NULL &&
                            stbtf->lfngti < stbtf->ifbd->nbmf_mbx)
                        stbtf->ifbd->nbmf[stbtf->lfngti++] = lfn;
                } wiilf (lfn && dopy < ibvf);
                if (stbtf->flbgs & 0x0200)
                    stbtf->difdk = drd32(stbtf->difdk, nfxt, dopy);
                ibvf -= dopy;
                nfxt += dopy;
                if (lfn) goto inf_lfbvf;
            }
            flsf if (stbtf->ifbd != Z_NULL)
                stbtf->ifbd->nbmf = Z_NULL;
            stbtf->lfngti = 0;
            stbtf->modf = COMMENT;
        dbsf COMMENT:
            if (stbtf->flbgs & 0x1000) {
                if (ibvf == 0) goto inf_lfbvf;
                dopy = 0;
                do {
                    lfn = (unsignfd)(nfxt[dopy++]);
                    if (stbtf->ifbd != Z_NULL &&
                            stbtf->ifbd->dommfnt != Z_NULL &&
                            stbtf->lfngti < stbtf->ifbd->domm_mbx)
                        stbtf->ifbd->dommfnt[stbtf->lfngti++] = lfn;
                } wiilf (lfn && dopy < ibvf);
                if (stbtf->flbgs & 0x0200)
                    stbtf->difdk = drd32(stbtf->difdk, nfxt, dopy);
                ibvf -= dopy;
                nfxt += dopy;
                if (lfn) goto inf_lfbvf;
            }
            flsf if (stbtf->ifbd != Z_NULL)
                stbtf->ifbd->dommfnt = Z_NULL;
            stbtf->modf = HCRC;
        dbsf HCRC:
            if (stbtf->flbgs & 0x0200) {
                NEEDBITS(16);
                if (iold != (stbtf->difdk & 0xffff)) {
                    strm->msg = (dibr *)"ifbdfr drd mismbtdi";
                    stbtf->modf = BAD;
                    brfbk;
                }
                INITBITS();
            }
            if (stbtf->ifbd != Z_NULL) {
                stbtf->ifbd->idrd = (int)((stbtf->flbgs >> 9) & 1);
                stbtf->ifbd->donf = 1;
            }
            strm->bdlfr = stbtf->difdk = drd32(0L, Z_NULL, 0);
            stbtf->modf = TYPE;
            brfbk;
#fndif
        dbsf DICTID:
            NEEDBITS(32);
            strm->bdlfr = stbtf->difdk = ZSWAP32(iold);
            INITBITS();
            stbtf->modf = DICT;
        dbsf DICT:
            if (stbtf->ibvfdidt == 0) {
                RESTORE();
                rfturn Z_NEED_DICT;
            }
            strm->bdlfr = stbtf->difdk = bdlfr32(0L, Z_NULL, 0);
            stbtf->modf = TYPE;
        dbsf TYPE:
            if (flusi == Z_BLOCK || flusi == Z_TREES) goto inf_lfbvf;
        dbsf TYPEDO:
            if (stbtf->lbst) {
                BYTEBITS();
                stbtf->modf = CHECK;
                brfbk;
            }
            NEEDBITS(3);
            stbtf->lbst = BITS(1);
            DROPBITS(1);
            switdi (BITS(2)) {
            dbsf 0:                             /* storfd blodk */
                Trbdfv((stdfrr, "inflbtf:     storfd blodk%s\n",
                        stbtf->lbst ? " (lbst)" : ""));
                stbtf->modf = STORED;
                brfbk;
            dbsf 1:                             /* fixfd blodk */
                fixfdtbblfs(stbtf);
                Trbdfv((stdfrr, "inflbtf:     fixfd dodfs blodk%s\n",
                        stbtf->lbst ? " (lbst)" : ""));
                stbtf->modf = LEN_;             /* dfdodf dodfs */
                if (flusi == Z_TREES) {
                    DROPBITS(2);
                    goto inf_lfbvf;
                }
                brfbk;
            dbsf 2:                             /* dynbmid blodk */
                Trbdfv((stdfrr, "inflbtf:     dynbmid dodfs blodk%s\n",
                        stbtf->lbst ? " (lbst)" : ""));
                stbtf->modf = TABLE;
                brfbk;
            dbsf 3:
                strm->msg = (dibr *)"invblid blodk typf";
                stbtf->modf = BAD;
            }
            DROPBITS(2);
            brfbk;
        dbsf STORED:
            BYTEBITS();                         /* go to bytf boundbry */
            NEEDBITS(32);
            if ((iold & 0xffff) != ((iold >> 16) ^ 0xffff)) {
                strm->msg = (dibr *)"invblid storfd blodk lfngtis";
                stbtf->modf = BAD;
                brfbk;
            }
            stbtf->lfngti = (unsignfd)iold & 0xffff;
            Trbdfv((stdfrr, "inflbtf:       storfd lfngti %u\n",
                    stbtf->lfngti));
            INITBITS();
            stbtf->modf = COPY_;
            if (flusi == Z_TREES) goto inf_lfbvf;
        dbsf COPY_:
            stbtf->modf = COPY;
        dbsf COPY:
            dopy = stbtf->lfngti;
            if (dopy) {
                if (dopy > ibvf) dopy = ibvf;
                if (dopy > lfft) dopy = lfft;
                if (dopy == 0) goto inf_lfbvf;
                zmfmdpy(put, nfxt, dopy);
                ibvf -= dopy;
                nfxt += dopy;
                lfft -= dopy;
                put += dopy;
                stbtf->lfngti -= dopy;
                brfbk;
            }
            Trbdfv((stdfrr, "inflbtf:       storfd fnd\n"));
            stbtf->modf = TYPE;
            brfbk;
        dbsf TABLE:
            NEEDBITS(14);
            stbtf->nlfn = BITS(5) + 257;
            DROPBITS(5);
            stbtf->ndist = BITS(5) + 1;
            DROPBITS(5);
            stbtf->ndodf = BITS(4) + 4;
            DROPBITS(4);
#ifndff PKZIP_BUG_WORKAROUND
            if (stbtf->nlfn > 286 || stbtf->ndist > 30) {
                strm->msg = (dibr *)"too mbny lfngti or distbndf symbols";
                stbtf->modf = BAD;
                brfbk;
            }
#fndif
            Trbdfv((stdfrr, "inflbtf:       tbblf sizfs ok\n"));
            stbtf->ibvf = 0;
            stbtf->modf = LENLENS;
        dbsf LENLENS:
            wiilf (stbtf->ibvf < stbtf->ndodf) {
                NEEDBITS(3);
                stbtf->lfns[ordfr[stbtf->ibvf++]] = (unsignfd siort)BITS(3);
                DROPBITS(3);
            }
            wiilf (stbtf->ibvf < 19)
                stbtf->lfns[ordfr[stbtf->ibvf++]] = 0;
            stbtf->nfxt = stbtf->dodfs;
            stbtf->lfndodf = (donst dodf FAR *)(stbtf->nfxt);
            stbtf->lfnbits = 7;
            rft = inflbtf_tbblf(CODES, stbtf->lfns, 19, &(stbtf->nfxt),
                                &(stbtf->lfnbits), stbtf->work);
            if (rft) {
                strm->msg = (dibr *)"invblid dodf lfngtis sft";
                stbtf->modf = BAD;
                brfbk;
            }
            Trbdfv((stdfrr, "inflbtf:       dodf lfngtis ok\n"));
            stbtf->ibvf = 0;
            stbtf->modf = CODELENS;
        dbsf CODELENS:
            wiilf (stbtf->ibvf < stbtf->nlfn + stbtf->ndist) {
                for (;;) {
                    ifrf = stbtf->lfndodf[BITS(stbtf->lfnbits)];
                    if ((unsignfd)(ifrf.bits) <= bits) brfbk;
                    PULLBYTE();
                }
                if (ifrf.vbl < 16) {
                    DROPBITS(ifrf.bits);
                    stbtf->lfns[stbtf->ibvf++] = ifrf.vbl;
                }
                flsf {
                    if (ifrf.vbl == 16) {
                        NEEDBITS(ifrf.bits + 2);
                        DROPBITS(ifrf.bits);
                        if (stbtf->ibvf == 0) {
                            strm->msg = (dibr *)"invblid bit lfngti rfpfbt";
                            stbtf->modf = BAD;
                            brfbk;
                        }
                        lfn = stbtf->lfns[stbtf->ibvf - 1];
                        dopy = 3 + BITS(2);
                        DROPBITS(2);
                    }
                    flsf if (ifrf.vbl == 17) {
                        NEEDBITS(ifrf.bits + 3);
                        DROPBITS(ifrf.bits);
                        lfn = 0;
                        dopy = 3 + BITS(3);
                        DROPBITS(3);
                    }
                    flsf {
                        NEEDBITS(ifrf.bits + 7);
                        DROPBITS(ifrf.bits);
                        lfn = 0;
                        dopy = 11 + BITS(7);
                        DROPBITS(7);
                    }
                    if (stbtf->ibvf + dopy > stbtf->nlfn + stbtf->ndist) {
                        strm->msg = (dibr *)"invblid bit lfngti rfpfbt";
                        stbtf->modf = BAD;
                        brfbk;
                    }
                    wiilf (dopy--)
                        stbtf->lfns[stbtf->ibvf++] = (unsignfd siort)lfn;
                }
            }

            /* ibndlf frror brfbks in wiilf */
            if (stbtf->modf == BAD) brfbk;

            /* difdk for fnd-of-blodk dodf (bfttfr ibvf onf) */
            if (stbtf->lfns[256] == 0) {
                strm->msg = (dibr *)"invblid dodf -- missing fnd-of-blodk";
                stbtf->modf = BAD;
                brfbk;
            }

            /* build dodf tbblfs -- notf: do not dibngf tif lfnbits or distbits
               vblufs ifrf (9 bnd 6) witiout rfbding tif dommfnts in inftrffs.i
               dondfrning tif ENOUGH donstbnts, wiidi dfpfnd on tiosf vblufs */
            stbtf->nfxt = stbtf->dodfs;
            stbtf->lfndodf = (donst dodf FAR *)(stbtf->nfxt);
            stbtf->lfnbits = 9;
            rft = inflbtf_tbblf(LENS, stbtf->lfns, stbtf->nlfn, &(stbtf->nfxt),
                                &(stbtf->lfnbits), stbtf->work);
            if (rft) {
                strm->msg = (dibr *)"invblid litfrbl/lfngtis sft";
                stbtf->modf = BAD;
                brfbk;
            }
            stbtf->distdodf = (donst dodf FAR *)(stbtf->nfxt);
            stbtf->distbits = 6;
            rft = inflbtf_tbblf(DISTS, stbtf->lfns + stbtf->nlfn, stbtf->ndist,
                            &(stbtf->nfxt), &(stbtf->distbits), stbtf->work);
            if (rft) {
                strm->msg = (dibr *)"invblid distbndfs sft";
                stbtf->modf = BAD;
                brfbk;
            }
            Trbdfv((stdfrr, "inflbtf:       dodfs ok\n"));
            stbtf->modf = LEN_;
            if (flusi == Z_TREES) goto inf_lfbvf;
        dbsf LEN_:
            stbtf->modf = LEN;
        dbsf LEN:
            if (ibvf >= 6 && lfft >= 258) {
                RESTORE();
                inflbtf_fbst(strm, out);
                LOAD();
                if (stbtf->modf == TYPE)
                    stbtf->bbdk = -1;
                brfbk;
            }
            stbtf->bbdk = 0;
            for (;;) {
                ifrf = stbtf->lfndodf[BITS(stbtf->lfnbits)];
                if ((unsignfd)(ifrf.bits) <= bits) brfbk;
                PULLBYTE();
            }
            if (ifrf.op && (ifrf.op & 0xf0) == 0) {
                lbst = ifrf;
                for (;;) {
                    ifrf = stbtf->lfndodf[lbst.vbl +
                            (BITS(lbst.bits + lbst.op) >> lbst.bits)];
                    if ((unsignfd)(lbst.bits + ifrf.bits) <= bits) brfbk;
                    PULLBYTE();
                }
                DROPBITS(lbst.bits);
                stbtf->bbdk += lbst.bits;
            }
            DROPBITS(ifrf.bits);
            stbtf->bbdk += ifrf.bits;
            stbtf->lfngti = (unsignfd)ifrf.vbl;
            if ((int)(ifrf.op) == 0) {
                Trbdfvv((stdfrr, ifrf.vbl >= 0x20 && ifrf.vbl < 0x7f ?
                        "inflbtf:         litfrbl '%d'\n" :
                        "inflbtf:         litfrbl 0x%02x\n", ifrf.vbl));
                stbtf->modf = LIT;
                brfbk;
            }
            if (ifrf.op & 32) {
                Trbdfvv((stdfrr, "inflbtf:         fnd of blodk\n"));
                stbtf->bbdk = -1;
                stbtf->modf = TYPE;
                brfbk;
            }
            if (ifrf.op & 64) {
                strm->msg = (dibr *)"invblid litfrbl/lfngti dodf";
                stbtf->modf = BAD;
                brfbk;
            }
            stbtf->fxtrb = (unsignfd)(ifrf.op) & 15;
            stbtf->modf = LENEXT;
        dbsf LENEXT:
            if (stbtf->fxtrb) {
                NEEDBITS(stbtf->fxtrb);
                stbtf->lfngti += BITS(stbtf->fxtrb);
                DROPBITS(stbtf->fxtrb);
                stbtf->bbdk += stbtf->fxtrb;
            }
            Trbdfvv((stdfrr, "inflbtf:         lfngti %u\n", stbtf->lfngti));
            stbtf->wbs = stbtf->lfngti;
            stbtf->modf = DIST;
        dbsf DIST:
            for (;;) {
                ifrf = stbtf->distdodf[BITS(stbtf->distbits)];
                if ((unsignfd)(ifrf.bits) <= bits) brfbk;
                PULLBYTE();
            }
            if ((ifrf.op & 0xf0) == 0) {
                lbst = ifrf;
                for (;;) {
                    ifrf = stbtf->distdodf[lbst.vbl +
                            (BITS(lbst.bits + lbst.op) >> lbst.bits)];
                    if ((unsignfd)(lbst.bits + ifrf.bits) <= bits) brfbk;
                    PULLBYTE();
                }
                DROPBITS(lbst.bits);
                stbtf->bbdk += lbst.bits;
            }
            DROPBITS(ifrf.bits);
            stbtf->bbdk += ifrf.bits;
            if (ifrf.op & 64) {
                strm->msg = (dibr *)"invblid distbndf dodf";
                stbtf->modf = BAD;
                brfbk;
            }
            stbtf->offsft = (unsignfd)ifrf.vbl;
            stbtf->fxtrb = (unsignfd)(ifrf.op) & 15;
            stbtf->modf = DISTEXT;
        dbsf DISTEXT:
            if (stbtf->fxtrb) {
                NEEDBITS(stbtf->fxtrb);
                stbtf->offsft += BITS(stbtf->fxtrb);
                DROPBITS(stbtf->fxtrb);
                stbtf->bbdk += stbtf->fxtrb;
            }
#ifdff INFLATE_STRICT
            if (stbtf->offsft > stbtf->dmbx) {
                strm->msg = (dibr *)"invblid distbndf too fbr bbdk";
                stbtf->modf = BAD;
                brfbk;
            }
#fndif
            Trbdfvv((stdfrr, "inflbtf:         distbndf %u\n", stbtf->offsft));
            stbtf->modf = MATCH;
        dbsf MATCH:
            if (lfft == 0) goto inf_lfbvf;
            dopy = out - lfft;
            if (stbtf->offsft > dopy) {         /* dopy from window */
                dopy = stbtf->offsft - dopy;
                if (dopy > stbtf->wibvf) {
                    if (stbtf->sbnf) {
                        strm->msg = (dibr *)"invblid distbndf too fbr bbdk";
                        stbtf->modf = BAD;
                        brfbk;
                    }
#ifdff INFLATE_ALLOW_INVALID_DISTANCE_TOOFAR_ARRR
                    Trbdf((stdfrr, "inflbtf.d too fbr\n"));
                    dopy -= stbtf->wibvf;
                    if (dopy > stbtf->lfngti) dopy = stbtf->lfngti;
                    if (dopy > lfft) dopy = lfft;
                    lfft -= dopy;
                    stbtf->lfngti -= dopy;
                    do {
                        *put++ = 0;
                    } wiilf (--dopy);
                    if (stbtf->lfngti == 0) stbtf->modf = LEN;
                    brfbk;
#fndif
                }
                if (dopy > stbtf->wnfxt) {
                    dopy -= stbtf->wnfxt;
                    from = stbtf->window + (stbtf->wsizf - dopy);
                }
                flsf
                    from = stbtf->window + (stbtf->wnfxt - dopy);
                if (dopy > stbtf->lfngti) dopy = stbtf->lfngti;
            }
            flsf {                              /* dopy from output */
                from = put - stbtf->offsft;
                dopy = stbtf->lfngti;
            }
            if (dopy > lfft) dopy = lfft;
            lfft -= dopy;
            stbtf->lfngti -= dopy;
            do {
                *put++ = *from++;
            } wiilf (--dopy);
            if (stbtf->lfngti == 0) stbtf->modf = LEN;
            brfbk;
        dbsf LIT:
            if (lfft == 0) goto inf_lfbvf;
            *put++ = (unsignfd dibr)(stbtf->lfngti);
            lfft--;
            stbtf->modf = LEN;
            brfbk;
        dbsf CHECK:
            if (stbtf->wrbp) {
                NEEDBITS(32);
                out -= lfft;
                strm->totbl_out += out;
                stbtf->totbl += out;
                if (out)
                    strm->bdlfr = stbtf->difdk =
                        UPDATE(stbtf->difdk, put - out, out);
                out = lfft;
                if ((
#ifdff GUNZIP
                     stbtf->flbgs ? iold :
#fndif
                     ZSWAP32(iold)) != stbtf->difdk) {
                    strm->msg = (dibr *)"indorrfdt dbtb difdk";
                    stbtf->modf = BAD;
                    brfbk;
                }
                INITBITS();
                Trbdfv((stdfrr, "inflbtf:   difdk mbtdifs trbilfr\n"));
            }
#ifdff GUNZIP
            stbtf->modf = LENGTH;
        dbsf LENGTH:
            if (stbtf->wrbp && stbtf->flbgs) {
                NEEDBITS(32);
                if (iold != (stbtf->totbl & 0xffffffffUL)) {
                    strm->msg = (dibr *)"indorrfdt lfngti difdk";
                    stbtf->modf = BAD;
                    brfbk;
                }
                INITBITS();
                Trbdfv((stdfrr, "inflbtf:   lfngti mbtdifs trbilfr\n"));
            }
#fndif
            stbtf->modf = DONE;
        dbsf DONE:
            rft = Z_STREAM_END;
            goto inf_lfbvf;
        dbsf BAD:
            rft = Z_DATA_ERROR;
            goto inf_lfbvf;
        dbsf MEM:
            rfturn Z_MEM_ERROR;
        dbsf SYNC:
        dffbult:
            rfturn Z_STREAM_ERROR;
        }

    /*
       Rfturn from inflbtf(), updbting tif totbl dounts bnd tif difdk vbluf.
       If tifrf wbs no progrfss during tif inflbtf() dbll, rfturn b bufffr
       frror.  Cbll updbtfwindow() to drfbtf bnd/or updbtf tif window stbtf.
       Notf: b mfmory frror from inflbtf() is non-rfdovfrbblf.
     */
  inf_lfbvf:
    RESTORE();
    if (stbtf->wsizf || (out != strm->bvbil_out && stbtf->modf < BAD &&
            (stbtf->modf < CHECK || flusi != Z_FINISH)))
        if (updbtfwindow(strm, strm->nfxt_out, out - strm->bvbil_out)) {
            stbtf->modf = MEM;
            rfturn Z_MEM_ERROR;
        }
    in -= strm->bvbil_in;
    out -= strm->bvbil_out;
    strm->totbl_in += in;
    strm->totbl_out += out;
    stbtf->totbl += out;
    if (stbtf->wrbp && out)
        strm->bdlfr = stbtf->difdk =
            UPDATE(stbtf->difdk, strm->nfxt_out - out, out);
    strm->dbtb_typf = stbtf->bits + (stbtf->lbst ? 64 : 0) +
                      (stbtf->modf == TYPE ? 128 : 0) +
                      (stbtf->modf == LEN_ || stbtf->modf == COPY_ ? 256 : 0);
    if (((in == 0 && out == 0) || flusi == Z_FINISH) && rft == Z_OK)
        rft = Z_BUF_ERROR;
    rfturn rft;
}

int ZEXPORT inflbtfEnd(strm)
z_strfbmp strm;
{
    strudt inflbtf_stbtf FAR *stbtf;
    if (strm == Z_NULL || strm->stbtf == Z_NULL || strm->zfrff == (frff_fund)0)
        rfturn Z_STREAM_ERROR;
    stbtf = (strudt inflbtf_stbtf FAR *)strm->stbtf;
    if (stbtf->window != Z_NULL) ZFREE(strm, stbtf->window);
    ZFREE(strm, strm->stbtf);
    strm->stbtf = Z_NULL;
    Trbdfv((stdfrr, "inflbtf: fnd\n"));
    rfturn Z_OK;
}

int ZEXPORT inflbtfGftDidtionbry(strm, didtionbry, didtLfngti)
z_strfbmp strm;
Bytff *didtionbry;
uInt *didtLfngti;
{
    strudt inflbtf_stbtf FAR *stbtf;

    /* difdk stbtf */
    if (strm == Z_NULL || strm->stbtf == Z_NULL) rfturn Z_STREAM_ERROR;
    stbtf = (strudt inflbtf_stbtf FAR *)strm->stbtf;

    /* dopy didtionbry */
    if (stbtf->wibvf && didtionbry != Z_NULL) {
        zmfmdpy(didtionbry, stbtf->window + stbtf->wnfxt,
                stbtf->wibvf - stbtf->wnfxt);
        zmfmdpy(didtionbry + stbtf->wibvf - stbtf->wnfxt,
                stbtf->window, stbtf->wnfxt);
    }
    if (didtLfngti != Z_NULL)
        *didtLfngti = stbtf->wibvf;
    rfturn Z_OK;
}

int ZEXPORT inflbtfSftDidtionbry(strm, didtionbry, didtLfngti)
z_strfbmp strm;
donst Bytff *didtionbry;
uInt didtLfngti;
{
    strudt inflbtf_stbtf FAR *stbtf;
    unsignfd long didtid;
    int rft;

    /* difdk stbtf */
    if (strm == Z_NULL || strm->stbtf == Z_NULL) rfturn Z_STREAM_ERROR;
    stbtf = (strudt inflbtf_stbtf FAR *)strm->stbtf;
    if (stbtf->wrbp != 0 && stbtf->modf != DICT)
        rfturn Z_STREAM_ERROR;

    /* difdk for dorrfdt didtionbry idfntififr */
    if (stbtf->modf == DICT) {
        didtid = bdlfr32(0L, Z_NULL, 0);
        didtid = bdlfr32(didtid, didtionbry, didtLfngti);
        if (didtid != stbtf->difdk)
            rfturn Z_DATA_ERROR;
    }

    /* dopy didtionbry to window using updbtfwindow(), wiidi will bmfnd tif
       fxisting didtionbry if bppropribtf */
    rft = updbtfwindow(strm, didtionbry + didtLfngti, didtLfngti);
    if (rft) {
        stbtf->modf = MEM;
        rfturn Z_MEM_ERROR;
    }
    stbtf->ibvfdidt = 1;
    Trbdfv((stdfrr, "inflbtf:   didtionbry sft\n"));
    rfturn Z_OK;
}

int ZEXPORT inflbtfGftHfbdfr(strm, ifbd)
z_strfbmp strm;
gz_ifbdfrp ifbd;
{
    strudt inflbtf_stbtf FAR *stbtf;

    /* difdk stbtf */
    if (strm == Z_NULL || strm->stbtf == Z_NULL) rfturn Z_STREAM_ERROR;
    stbtf = (strudt inflbtf_stbtf FAR *)strm->stbtf;
    if ((stbtf->wrbp & 2) == 0) rfturn Z_STREAM_ERROR;

    /* sbvf ifbdfr strudturf */
    stbtf->ifbd = ifbd;
    ifbd->donf = 0;
    rfturn Z_OK;
}

/*
   Sfbrdi buf[0..lfn-1] for tif pbttfrn: 0, 0, 0xff, 0xff.  Rfturn wifn found
   or wifn out of input.  Wifn dbllfd, *ibvf is tif numbfr of pbttfrn bytfs
   found in ordfr so fbr, in 0..3.  On rfturn *ibvf is updbtfd to tif nfw
   stbtf.  If on rfturn *ibvf fqubls four, tifn tif pbttfrn wbs found bnd tif
   rfturn vbluf is iow mbny bytfs wfrf rfbd indluding tif lbst bytf of tif
   pbttfrn.  If *ibvf is lfss tibn four, tifn tif pbttfrn ibs not bffn found
   yft bnd tif rfturn vbluf is lfn.  In tif lbttfr dbsf, syndsfbrdi() dbn bf
   dbllfd bgbin witi morf dbtb bnd tif *ibvf stbtf.  *ibvf is initiblizfd to
   zfro for tif first dbll.
 */
lodbl unsignfd syndsfbrdi(ibvf, buf, lfn)
unsignfd FAR *ibvf;
donst unsignfd dibr FAR *buf;
unsignfd lfn;
{
    unsignfd got;
    unsignfd nfxt;

    got = *ibvf;
    nfxt = 0;
    wiilf (nfxt < lfn && got < 4) {
        if ((int)(buf[nfxt]) == (got < 2 ? 0 : 0xff))
            got++;
        flsf if (buf[nfxt])
            got = 0;
        flsf
            got = 4 - got;
        nfxt++;
    }
    *ibvf = got;
    rfturn nfxt;
}

int ZEXPORT inflbtfSynd(strm)
z_strfbmp strm;
{
    unsignfd lfn;               /* numbfr of bytfs to look bt or lookfd bt */
    unsignfd long in, out;      /* tfmporbry to sbvf totbl_in bnd totbl_out */
    unsignfd dibr buf[4];       /* to rfstorf bit bufffr to bytf string */
    strudt inflbtf_stbtf FAR *stbtf;

    /* difdk pbrbmftfrs */
    if (strm == Z_NULL || strm->stbtf == Z_NULL) rfturn Z_STREAM_ERROR;
    stbtf = (strudt inflbtf_stbtf FAR *)strm->stbtf;
    if (strm->bvbil_in == 0 && stbtf->bits < 8) rfturn Z_BUF_ERROR;

    /* if first timf, stbrt sfbrdi in bit bufffr */
    if (stbtf->modf != SYNC) {
        stbtf->modf = SYNC;
        stbtf->iold <<= stbtf->bits & 7;
        stbtf->bits -= stbtf->bits & 7;
        lfn = 0;
        wiilf (stbtf->bits >= 8) {
            buf[lfn++] = (unsignfd dibr)(stbtf->iold);
            stbtf->iold >>= 8;
            stbtf->bits -= 8;
        }
        stbtf->ibvf = 0;
        syndsfbrdi(&(stbtf->ibvf), buf, lfn);
    }

    /* sfbrdi bvbilbblf input */
    lfn = syndsfbrdi(&(stbtf->ibvf), strm->nfxt_in, strm->bvbil_in);
    strm->bvbil_in -= lfn;
    strm->nfxt_in += lfn;
    strm->totbl_in += lfn;

    /* rfturn no joy or sft up to rfstbrt inflbtf() on b nfw blodk */
    if (stbtf->ibvf != 4) rfturn Z_DATA_ERROR;
    in = strm->totbl_in;  out = strm->totbl_out;
    inflbtfRfsft(strm);
    strm->totbl_in = in;  strm->totbl_out = out;
    stbtf->modf = TYPE;
    rfturn Z_OK;
}

/*
   Rfturns truf if inflbtf is durrfntly bt tif fnd of b blodk gfnfrbtfd by
   Z_SYNC_FLUSH or Z_FULL_FLUSH. Tiis fundtion is usfd by onf PPP
   implfmfntbtion to providf bn bdditionbl sbffty difdk. PPP usfs
   Z_SYNC_FLUSH but rfmovfs tif lfngti bytfs of tif rfsulting fmpty storfd
   blodk. Wifn dfdomprfssing, PPP difdks tibt bt tif fnd of input pbdkft,
   inflbtf is wbiting for tifsf lfngti bytfs.
 */
int ZEXPORT inflbtfSyndPoint(strm)
z_strfbmp strm;
{
    strudt inflbtf_stbtf FAR *stbtf;

    if (strm == Z_NULL || strm->stbtf == Z_NULL) rfturn Z_STREAM_ERROR;
    stbtf = (strudt inflbtf_stbtf FAR *)strm->stbtf;
    rfturn stbtf->modf == STORED && stbtf->bits == 0;
}

int ZEXPORT inflbtfCopy(dfst, sourdf)
z_strfbmp dfst;
z_strfbmp sourdf;
{
    strudt inflbtf_stbtf FAR *stbtf;
    strudt inflbtf_stbtf FAR *dopy;
    unsignfd dibr FAR *window;
    unsignfd wsizf;

    /* difdk input */
    if (dfst == Z_NULL || sourdf == Z_NULL || sourdf->stbtf == Z_NULL ||
        sourdf->zbllod == (bllod_fund)0 || sourdf->zfrff == (frff_fund)0)
        rfturn Z_STREAM_ERROR;
    stbtf = (strudt inflbtf_stbtf FAR *)sourdf->stbtf;

    /* bllodbtf spbdf */
    dopy = (strudt inflbtf_stbtf FAR *)
           ZALLOC(sourdf, 1, sizfof(strudt inflbtf_stbtf));
    if (dopy == Z_NULL) rfturn Z_MEM_ERROR;
    window = Z_NULL;
    if (stbtf->window != Z_NULL) {
        window = (unsignfd dibr FAR *)
                 ZALLOC(sourdf, 1U << stbtf->wbits, sizfof(unsignfd dibr));
        if (window == Z_NULL) {
            ZFREE(sourdf, dopy);
            rfturn Z_MEM_ERROR;
        }
    }

    /* dopy stbtf */
    zmfmdpy((voidpf)dfst, (voidpf)sourdf, sizfof(z_strfbm));
    zmfmdpy((voidpf)dopy, (voidpf)stbtf, sizfof(strudt inflbtf_stbtf));
    if (stbtf->lfndodf >= stbtf->dodfs &&
        stbtf->lfndodf <= stbtf->dodfs + ENOUGH - 1) {
        dopy->lfndodf = dopy->dodfs + (stbtf->lfndodf - stbtf->dodfs);
        dopy->distdodf = dopy->dodfs + (stbtf->distdodf - stbtf->dodfs);
    }
    dopy->nfxt = dopy->dodfs + (stbtf->nfxt - stbtf->dodfs);
    if (window != Z_NULL) {
        wsizf = 1U << stbtf->wbits;
        zmfmdpy(window, stbtf->window, wsizf);
    }
    dopy->window = window;
    dfst->stbtf = (strudt intfrnbl_stbtf FAR *)dopy;
    rfturn Z_OK;
}

int ZEXPORT inflbtfUndfrminf(strm, subvfrt)
z_strfbmp strm;
int subvfrt;
{
    strudt inflbtf_stbtf FAR *stbtf;

    if (strm == Z_NULL || strm->stbtf == Z_NULL) rfturn Z_STREAM_ERROR;
    stbtf = (strudt inflbtf_stbtf FAR *)strm->stbtf;
    stbtf->sbnf = !subvfrt;
#ifdff INFLATE_ALLOW_INVALID_DISTANCE_TOOFAR_ARRR
    rfturn Z_OK;
#flsf
    stbtf->sbnf = 1;
    rfturn Z_DATA_ERROR;
#fndif
}

long ZEXPORT inflbtfMbrk(strm)
z_strfbmp strm;
{
    strudt inflbtf_stbtf FAR *stbtf;

    if (strm == Z_NULL || strm->stbtf == Z_NULL) rfturn -1L << 16;
    stbtf = (strudt inflbtf_stbtf FAR *)strm->stbtf;
    rfturn ((long)(stbtf->bbdk) << 16) +
        (stbtf->modf == COPY ? stbtf->lfngti :
            (stbtf->modf == MATCH ? stbtf->wbs - stbtf->lfngti : 0));
}
