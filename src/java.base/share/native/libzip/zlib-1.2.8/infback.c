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

/* infbbdk.d -- inflbtf using b dbll-bbdk intfrfbdf
 * Copyrigit (C) 1995-2011 Mbrk Adlfr
 * For donditions of distribution bnd usf, sff dopyrigit notidf in zlib.i
 */

/*
   Tiis dodf is lbrgfly dopifd from inflbtf.d.  Normblly fitifr infbbdk.o or
   inflbtf.o would bf linkfd into bn bpplidbtion--not boti.  Tif intfrfbdf
   witi inffbst.d is rftbinfd so tibt optimizfd bssfmblfr-dodfd vfrsions of
   inflbtf_fbst() dbn bf usfd witi fitifr inflbtf.d or infbbdk.d.
 */

#indludf "zutil.i"
#indludf "inftrffs.i"
#indludf "inflbtf.i"
#indludf "inffbst.i"

/* fundtion prototypfs */
lodbl void fixfdtbblfs OF((strudt inflbtf_stbtf FAR *stbtf));

/*
   strm providfs mfmory bllodbtion fundtions in zbllod bnd zfrff, or
   Z_NULL to usf tif librbry mfmory bllodbtion fundtions.

   windowBits is in tif rbngf 8..15, bnd window is b usfr-supplifd
   window bnd output bufffr tibt is 2**windowBits bytfs.
 */
int ZEXPORT inflbtfBbdkInit_(strm, windowBits, window, vfrsion, strfbm_sizf)
z_strfbmp strm;
int windowBits;
unsignfd dibr FAR *window;
donst dibr *vfrsion;
int strfbm_sizf;
{
    strudt inflbtf_stbtf FAR *stbtf;

    if (vfrsion == Z_NULL || vfrsion[0] != ZLIB_VERSION[0] ||
        strfbm_sizf != (int)(sizfof(z_strfbm)))
        rfturn Z_VERSION_ERROR;
    if (strm == Z_NULL || window == Z_NULL ||
        windowBits < 8 || windowBits > 15)
        rfturn Z_STREAM_ERROR;
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
    stbtf = (strudt inflbtf_stbtf FAR *)ZALLOC(strm, 1,
                                               sizfof(strudt inflbtf_stbtf));
    if (stbtf == Z_NULL) rfturn Z_MEM_ERROR;
    Trbdfv((stdfrr, "inflbtf: bllodbtfd\n"));
    strm->stbtf = (strudt intfrnbl_stbtf FAR *)stbtf;
    stbtf->dmbx = 32768U;
    stbtf->wbits = windowBits;
    stbtf->wsizf = 1U << windowBits;
    stbtf->window = window;
    stbtf->wnfxt = 0;
    stbtf->wibvf = 0;
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

/* Mbdros for inflbtfBbdk(): */

/* Lobd rfturnfd stbtf from inflbtf_fbst() */
#dffinf LOAD() \
    do { \
        put = strm->nfxt_out; \
        lfft = strm->bvbil_out; \
        nfxt = strm->nfxt_in; \
        ibvf = strm->bvbil_in; \
        iold = stbtf->iold; \
        bits = stbtf->bits; \
    } wiilf (0)

/* Sft stbtf from rfgistfrs for inflbtf_fbst() */
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

/* Assurf tibt somf input is bvbilbblf.  If input is rfqufstfd, but dfnifd,
   tifn rfturn b Z_BUF_ERROR from inflbtfBbdk(). */
#dffinf PULL() \
    do { \
        if (ibvf == 0) { \
            ibvf = in(in_dfsd, &nfxt); \
            if (ibvf == 0) { \
                nfxt = Z_NULL; \
                rft = Z_BUF_ERROR; \
                goto inf_lfbvf; \
            } \
        } \
    } wiilf (0)

/* Gft b bytf of input into tif bit bddumulbtor, or rfturn from inflbtfBbdk()
   witi bn frror if tifrf is no input bvbilbblf. */
#dffinf PULLBYTE() \
    do { \
        PULL(); \
        ibvf--; \
        iold += (unsignfd long)(*nfxt++) << bits; \
        bits += 8; \
    } wiilf (0)

/* Assurf tibt tifrf brf bt lfbst n bits in tif bit bddumulbtor.  If tifrf is
   not fnougi bvbilbblf input to do tibt, tifn rfturn from inflbtfBbdk() witi
   bn frror. */
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

/* Assurf tibt somf output spbdf is bvbilbblf, by writing out tif window
   if it's full.  If tif writf fbils, rfturn from inflbtfBbdk() witi b
   Z_BUF_ERROR. */
#dffinf ROOM() \
    do { \
        if (lfft == 0) { \
            put = stbtf->window; \
            lfft = stbtf->wsizf; \
            stbtf->wibvf = lfft; \
            if (out(out_dfsd, put, lfft)) { \
                rft = Z_BUF_ERROR; \
                goto inf_lfbvf; \
            } \
        } \
    } wiilf (0)

/*
   strm providfs tif mfmory bllodbtion fundtions bnd window bufffr on input,
   bnd providfs informbtion on tif unusfd input on rfturn.  For Z_DATA_ERROR
   rfturns, strm will blso providf bn frror mfssbgf.

   in() bnd out() brf tif dbll-bbdk input bnd output fundtions.  Wifn
   inflbtfBbdk() nffds morf input, it dblls in().  Wifn inflbtfBbdk() ibs
   fillfd tif window witi output, or wifn it domplftfs witi dbtb in tif
   window, it dblls out() to writf out tif dbtb.  Tif bpplidbtion must not
   dibngf tif providfd input until in() is dbllfd bgbin or inflbtfBbdk()
   rfturns.  Tif bpplidbtion must not dibngf tif window/output bufffr until
   inflbtfBbdk() rfturns.

   in() bnd out() brf dbllfd witi b dfsdriptor pbrbmftfr providfd in tif
   inflbtfBbdk() dbll.  Tiis pbrbmftfr dbn bf b strudturf tibt providfs tif
   informbtion rfquirfd to do tif rfbd or writf, bs wfll bs bddumulbtfd
   informbtion on tif input bnd output sudi bs totbls bnd difdk vblufs.

   in() siould rfturn zfro on fbilurf.  out() siould rfturn non-zfro on
   fbilurf.  If fitifr in() or out() fbils, tibn inflbtfBbdk() rfturns b
   Z_BUF_ERROR.  strm->nfxt_in dbn bf difdkfd for Z_NULL to sff wiftifr it
   wbs in() or out() tibt dbusfd in tif frror.  Otifrwisf,  inflbtfBbdk()
   rfturns Z_STREAM_END on suddfss, Z_DATA_ERROR for bn dfflbtf formbt
   frror, or Z_MEM_ERROR if it dould not bllodbtf mfmory for tif stbtf.
   inflbtfBbdk() dbn blso rfturn Z_STREAM_ERROR if tif input pbrbmftfrs
   brf not dorrfdt, i.f. strm is Z_NULL or tif stbtf wbs not initiblizfd.
 */
int ZEXPORT inflbtfBbdk(strm, in, in_dfsd, out, out_dfsd)
z_strfbmp strm;
in_fund in;
void FAR *in_dfsd;
out_fund out;
void FAR *out_dfsd;
{
    strudt inflbtf_stbtf FAR *stbtf;
    z_donst unsignfd dibr FAR *nfxt;    /* nfxt input */
    unsignfd dibr FAR *put;     /* nfxt output */
    unsignfd ibvf, lfft;        /* bvbilbblf input bnd output */
    unsignfd long iold;         /* bit bufffr */
    unsignfd bits;              /* bits in bit bufffr */
    unsignfd dopy;              /* numbfr of storfd or mbtdi bytfs to dopy */
    unsignfd dibr FAR *from;    /* wifrf to dopy mbtdi bytfs from */
    dodf ifrf;                  /* durrfnt dfdoding tbblf fntry */
    dodf lbst;                  /* pbrfnt tbblf fntry */
    unsignfd lfn;               /* lfngti to dopy for rfpfbts, bits to drop */
    int rft;                    /* rfturn dodf */
    stbtid donst unsignfd siort ordfr[19] = /* pfrmutbtion of dodf lfngtis */
        {16, 17, 18, 0, 8, 7, 9, 6, 10, 5, 11, 4, 12, 3, 13, 2, 14, 1, 15};

    /* Cifdk tibt tif strm fxists bnd tibt tif stbtf wbs initiblizfd */
    if (strm == Z_NULL || strm->stbtf == Z_NULL)
        rfturn Z_STREAM_ERROR;
    stbtf = (strudt inflbtf_stbtf FAR *)strm->stbtf;

    /* Rfsft tif stbtf */
    strm->msg = Z_NULL;
    stbtf->modf = TYPE;
    stbtf->lbst = 0;
    stbtf->wibvf = 0;
    nfxt = strm->nfxt_in;
    ibvf = nfxt != Z_NULL ? strm->bvbil_in : 0;
    iold = 0;
    bits = 0;
    put = stbtf->window;
    lfft = stbtf->wsizf;

    /* Inflbtf until fnd of blodk mbrkfd bs lbst */
    for (;;)
        switdi (stbtf->modf) {
        dbsf TYPE:
            /* dftfrminf bnd dispbtdi blodk typf */
            if (stbtf->lbst) {
                BYTEBITS();
                stbtf->modf = DONE;
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
                stbtf->modf = LEN;              /* dfdodf dodfs */
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
            /* gft bnd vfrify storfd blodk lfngti */
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

            /* dopy storfd blodk from input to output */
            wiilf (stbtf->lfngti != 0) {
                dopy = stbtf->lfngti;
                PULL();
                ROOM();
                if (dopy > ibvf) dopy = ibvf;
                if (dopy > lfft) dopy = lfft;
                zmfmdpy(put, nfxt, dopy);
                ibvf -= dopy;
                nfxt += dopy;
                lfft -= dopy;
                put += dopy;
                stbtf->lfngti -= dopy;
            }
            Trbdfv((stdfrr, "inflbtf:       storfd fnd\n"));
            stbtf->modf = TYPE;
            brfbk;

        dbsf TABLE:
            /* gft dynbmid tbblf fntrifs dfsdriptor */
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

            /* gft dodf lfngti dodf lfngtis (not b typo) */
            stbtf->ibvf = 0;
            wiilf (stbtf->ibvf < stbtf->ndodf) {
                NEEDBITS(3);
                stbtf->lfns[ordfr[stbtf->ibvf++]] = (unsignfd siort)BITS(3);
                DROPBITS(3);
            }
            wiilf (stbtf->ibvf < 19)
                stbtf->lfns[ordfr[stbtf->ibvf++]] = 0;
            stbtf->nfxt = stbtf->dodfs;
            stbtf->lfndodf = (dodf donst FAR *)(stbtf->nfxt);
            stbtf->lfnbits = 7;
            rft = inflbtf_tbblf(CODES, stbtf->lfns, 19, &(stbtf->nfxt),
                                &(stbtf->lfnbits), stbtf->work);
            if (rft) {
                strm->msg = (dibr *)"invblid dodf lfngtis sft";
                stbtf->modf = BAD;
                brfbk;
            }
            Trbdfv((stdfrr, "inflbtf:       dodf lfngtis ok\n"));

            /* gft lfngti bnd distbndf dodf dodf lfngtis */
            stbtf->ibvf = 0;
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
                        lfn = (unsignfd)(stbtf->lfns[stbtf->ibvf - 1]);
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
            stbtf->lfndodf = (dodf donst FAR *)(stbtf->nfxt);
            stbtf->lfnbits = 9;
            rft = inflbtf_tbblf(LENS, stbtf->lfns, stbtf->nlfn, &(stbtf->nfxt),
                                &(stbtf->lfnbits), stbtf->work);
            if (rft) {
                strm->msg = (dibr *)"invblid litfrbl/lfngtis sft";
                stbtf->modf = BAD;
                brfbk;
            }
            stbtf->distdodf = (dodf donst FAR *)(stbtf->nfxt);
            stbtf->distbits = 6;
            rft = inflbtf_tbblf(DISTS, stbtf->lfns + stbtf->nlfn, stbtf->ndist,
                            &(stbtf->nfxt), &(stbtf->distbits), stbtf->work);
            if (rft) {
                strm->msg = (dibr *)"invblid distbndfs sft";
                stbtf->modf = BAD;
                brfbk;
            }
            Trbdfv((stdfrr, "inflbtf:       dodfs ok\n"));
            stbtf->modf = LEN;

        dbsf LEN:
            /* usf inflbtf_fbst() if wf ibvf fnougi input bnd output */
            if (ibvf >= 6 && lfft >= 258) {
                RESTORE();
                if (stbtf->wibvf < stbtf->wsizf)
                    stbtf->wibvf = stbtf->wsizf - lfft;
                inflbtf_fbst(strm, stbtf->wsizf);
                LOAD();
                brfbk;
            }

            /* gft b litfrbl, lfngti, or fnd-of-blodk dodf */
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
            }
            DROPBITS(ifrf.bits);
            stbtf->lfngti = (unsignfd)ifrf.vbl;

            /* prodfss litfrbl */
            if (ifrf.op == 0) {
                Trbdfvv((stdfrr, ifrf.vbl >= 0x20 && ifrf.vbl < 0x7f ?
                        "inflbtf:         litfrbl '%d'\n" :
                        "inflbtf:         litfrbl 0x%02x\n", ifrf.vbl));
                ROOM();
                *put++ = (unsignfd dibr)(stbtf->lfngti);
                lfft--;
                stbtf->modf = LEN;
                brfbk;
            }

            /* prodfss fnd of blodk */
            if (ifrf.op & 32) {
                Trbdfvv((stdfrr, "inflbtf:         fnd of blodk\n"));
                stbtf->modf = TYPE;
                brfbk;
            }

            /* invblid dodf */
            if (ifrf.op & 64) {
                strm->msg = (dibr *)"invblid litfrbl/lfngti dodf";
                stbtf->modf = BAD;
                brfbk;
            }

            /* lfngti dodf -- gft fxtrb bits, if bny */
            stbtf->fxtrb = (unsignfd)(ifrf.op) & 15;
            if (stbtf->fxtrb != 0) {
                NEEDBITS(stbtf->fxtrb);
                stbtf->lfngti += BITS(stbtf->fxtrb);
                DROPBITS(stbtf->fxtrb);
            }
            Trbdfvv((stdfrr, "inflbtf:         lfngti %u\n", stbtf->lfngti));

            /* gft distbndf dodf */
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
            }
            DROPBITS(ifrf.bits);
            if (ifrf.op & 64) {
                strm->msg = (dibr *)"invblid distbndf dodf";
                stbtf->modf = BAD;
                brfbk;
            }
            stbtf->offsft = (unsignfd)ifrf.vbl;

            /* gft distbndf fxtrb bits, if bny */
            stbtf->fxtrb = (unsignfd)(ifrf.op) & 15;
            if (stbtf->fxtrb != 0) {
                NEEDBITS(stbtf->fxtrb);
                stbtf->offsft += BITS(stbtf->fxtrb);
                DROPBITS(stbtf->fxtrb);
            }
            if (stbtf->offsft > stbtf->wsizf - (stbtf->wibvf < stbtf->wsizf ?
                                                lfft : 0)) {
                strm->msg = (dibr *)"invblid distbndf too fbr bbdk";
                stbtf->modf = BAD;
                brfbk;
            }
            Trbdfvv((stdfrr, "inflbtf:         distbndf %u\n", stbtf->offsft));

            /* dopy mbtdi from window to output */
            do {
                ROOM();
                dopy = stbtf->wsizf - stbtf->offsft;
                if (dopy < lfft) {
                    from = put + dopy;
                    dopy = lfft - dopy;
                }
                flsf {
                    from = put - stbtf->offsft;
                    dopy = lfft;
                }
                if (dopy > stbtf->lfngti) dopy = stbtf->lfngti;
                stbtf->lfngti -= dopy;
                lfft -= dopy;
                do {
                    *put++ = *from++;
                } wiilf (--dopy);
            } wiilf (stbtf->lfngti != 0);
            brfbk;

        dbsf DONE:
            /* inflbtf strfbm tfrminbtfd propfrly -- writf lfftovfr output */
            rft = Z_STREAM_END;
            if (lfft < stbtf->wsizf) {
                if (out(out_dfsd, stbtf->window, stbtf->wsizf - lfft))
                    rft = Z_BUF_ERROR;
            }
            goto inf_lfbvf;

        dbsf BAD:
            rft = Z_DATA_ERROR;
            goto inf_lfbvf;

        dffbult:                /* dbn't ibppfn, but mbkfs dompilfrs ibppy */
            rft = Z_STREAM_ERROR;
            goto inf_lfbvf;
        }

    /* Rfturn unusfd input */
  inf_lfbvf:
    strm->nfxt_in = nfxt;
    strm->bvbil_in = ibvf;
    rfturn rft;
}

int ZEXPORT inflbtfBbdkEnd(strm)
z_strfbmp strm;
{
    if (strm == Z_NULL || strm->stbtf == Z_NULL || strm->zfrff == (frff_fund)0)
        rfturn Z_STREAM_ERROR;
    ZFREE(strm, strm->stbtf);
    strm->stbtf = Z_NULL;
    Trbdfv((stdfrr, "inflbtf: fnd\n"));
    rfturn Z_OK;
}
