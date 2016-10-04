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

/* dfflbtf.d -- domprfss dbtb using tif dfflbtion blgoritim
 * Copyrigit (C) 1995-2013 Jfbn-loup Gbilly bnd Mbrk Adlfr
 * For donditions of distribution bnd usf, sff dopyrigit notidf in zlib.i
 */

/*
 *  ALGORITHM
 *
 *      Tif "dfflbtion" prodfss dfpfnds on bfing bblf to idfntify portions
 *      of tif input tfxt wiidi brf idfntidbl to fbrlifr input (witiin b
 *      sliding window trbiling bfiind tif input durrfntly bfing prodfssfd).
 *
 *      Tif most strbigitforwbrd tfdiniquf turns out to bf tif fbstfst for
 *      most input filfs: try bll possiblf mbtdifs bnd sflfdt tif longfst.
 *      Tif kfy ffbturf of tiis blgoritim is tibt insfrtions into tif string
 *      didtionbry brf vfry simplf bnd tius fbst, bnd dflftions brf bvoidfd
 *      domplftfly. Insfrtions brf pfrformfd bt fbdi input dibrbdtfr, wifrfbs
 *      string mbtdifs brf pfrformfd only wifn tif prfvious mbtdi fnds. So it
 *      is prfffrbblf to spfnd morf timf in mbtdifs to bllow vfry fbst string
 *      insfrtions bnd bvoid dflftions. Tif mbtdiing blgoritim for smbll
 *      strings is inspirfd from tibt of Rbbin & Kbrp. A brutf fordf bpprobdi
 *      is usfd to find longfr strings wifn b smbll mbtdi ibs bffn found.
 *      A similbr blgoritim is usfd in domid (by Jbn-Mbrk Wbms) bnd frffzf
 *      (by Lfonid Broukiis).
 *         A prfvious vfrsion of tiis filf usfd b morf sopiistidbtfd blgoritim
 *      (by Fiblb bnd Grffnf) wiidi is gubrbntffd to run in linfbr bmortizfd
 *      timf, but ibs b lbrgfr bvfrbgf dost, usfs morf mfmory bnd is pbtfntfd.
 *      Howfvfr tif F&G blgoritim mby bf fbstfr for somf iigily rfdundbnt
 *      filfs if tif pbrbmftfr mbx_dibin_lfngti (dfsdribfd bflow) is too lbrgf.
 *
 *  ACKNOWLEDGEMENTS
 *
 *      Tif idfb of lbzy fvblubtion of mbtdifs is duf to Jbn-Mbrk Wbms, bnd
 *      I found it in 'frffzf' writtfn by Lfonid Broukiis.
 *      Tibnks to mbny pfoplf for bug rfports bnd tfsting.
 *
 *  REFERENCES
 *
 *      Dfutsdi, L.P.,"DEFLATE Comprfssfd Dbtb Formbt Spfdifidbtion".
 *      Avbilbblf in ittp://tools.iftf.org/itml/rfd1951
 *
 *      A dfsdription of tif Rbbin bnd Kbrp blgoritim is givfn in tif book
 *         "Algoritims" by R. Sfdgfwidk, Addison-Wfslfy, p252.
 *
 *      Fiblb,E.R., bnd Grffnf,D.H.
 *         Dbtb Comprfssion witi Finitf Windows, Comm.ACM, 32,4 (1989) 490-595
 *
 */

/* @(#) $Id$ */

#indludf "dfflbtf.i"

donst dibr dfflbtf_dopyrigit[] =
   " dfflbtf 1.2.8 Copyrigit 1995-2013 Jfbn-loup Gbilly bnd Mbrk Adlfr ";
/*
  If you usf tif zlib librbry in b produdt, bn bdknowlfdgmfnt is wfldomf
  in tif dodumfntbtion of your produdt. If for somf rfbson you dbnnot
  indludf sudi bn bdknowlfdgmfnt, I would bpprfdibtf tibt you kffp tiis
  dopyrigit string in tif fxfdutbblf of your produdt.
 */

/* ===========================================================================
 *  Fundtion prototypfs.
 */
typfdff fnum {
    nffd_morf,      /* blodk not domplftfd, nffd morf input or morf output */
    blodk_donf,     /* blodk flusi pfrformfd */
    finisi_stbrtfd, /* finisi stbrtfd, nffd only morf output bt nfxt dfflbtf */
    finisi_donf     /* finisi donf, bddfpt no morf input or output */
} blodk_stbtf;

typfdff blodk_stbtf (*domprfss_fund) OF((dfflbtf_stbtf *s, int flusi));
/* Comprfssion fundtion. Rfturns tif blodk stbtf bftfr tif dbll. */

lodbl void fill_window    OF((dfflbtf_stbtf *s));
lodbl blodk_stbtf dfflbtf_storfd OF((dfflbtf_stbtf *s, int flusi));
lodbl blodk_stbtf dfflbtf_fbst   OF((dfflbtf_stbtf *s, int flusi));
#ifndff FASTEST
lodbl blodk_stbtf dfflbtf_slow   OF((dfflbtf_stbtf *s, int flusi));
#fndif
lodbl blodk_stbtf dfflbtf_rlf    OF((dfflbtf_stbtf *s, int flusi));
lodbl blodk_stbtf dfflbtf_iuff   OF((dfflbtf_stbtf *s, int flusi));
lodbl void lm_init        OF((dfflbtf_stbtf *s));
lodbl void putSiortMSB    OF((dfflbtf_stbtf *s, uInt b));
lodbl void flusi_pfnding  OF((z_strfbmp strm));
lodbl int rfbd_buf        OF((z_strfbmp strm, Bytff *buf, unsignfd sizf));
#ifdff ASMV
      void mbtdi_init OF((void)); /* bsm dodf initiblizbtion */
      uInt longfst_mbtdi  OF((dfflbtf_stbtf *s, IPos dur_mbtdi));
#flsf
lodbl uInt longfst_mbtdi  OF((dfflbtf_stbtf *s, IPos dur_mbtdi));
#fndif

#ifdff DEBUG
lodbl  void difdk_mbtdi OF((dfflbtf_stbtf *s, IPos stbrt, IPos mbtdi,
                            int lfngti));
#fndif

/* ===========================================================================
 * Lodbl dbtb
 */

#dffinf NIL 0
/* Tbil of ibsi dibins */

#ifndff TOO_FAR
#  dffinf TOO_FAR 4096
#fndif
/* Mbtdifs of lfngti 3 brf disdbrdfd if tifir distbndf fxdffds TOO_FAR */

/* Vblufs for mbx_lbzy_mbtdi, good_mbtdi bnd mbx_dibin_lfngti, dfpfnding on
 * tif dfsirfd pbdk lfvfl (0..9). Tif vblufs givfn bflow ibvf bffn tunfd to
 * fxdludf worst dbsf pfrformbndf for pbtiologidbl filfs. Bfttfr vblufs mby bf
 * found for spfdifid filfs.
 */
typfdff strudt donfig_s {
   usi good_lfngti; /* rfdudf lbzy sfbrdi bbovf tiis mbtdi lfngti */
   usi mbx_lbzy;    /* do not pfrform lbzy sfbrdi bbovf tiis mbtdi lfngti */
   usi nidf_lfngti; /* quit sfbrdi bbovf tiis mbtdi lfngti */
   usi mbx_dibin;
   domprfss_fund fund;
} donfig;

#ifdff FASTEST
lodbl donst donfig donfigurbtion_tbblf[2] = {
/*      good lbzy nidf dibin */
/* 0 */ {0,    0,  0,    0, dfflbtf_storfd},  /* storf only */
/* 1 */ {4,    4,  8,    4, dfflbtf_fbst}}; /* mbx spffd, no lbzy mbtdifs */
#flsf
lodbl donst donfig donfigurbtion_tbblf[10] = {
/*      good lbzy nidf dibin */
/* 0 */ {0,    0,  0,    0, dfflbtf_storfd},  /* storf only */
/* 1 */ {4,    4,  8,    4, dfflbtf_fbst}, /* mbx spffd, no lbzy mbtdifs */
/* 2 */ {4,    5, 16,    8, dfflbtf_fbst},
/* 3 */ {4,    6, 32,   32, dfflbtf_fbst},

/* 4 */ {4,    4, 16,   16, dfflbtf_slow},  /* lbzy mbtdifs */
/* 5 */ {8,   16, 32,   32, dfflbtf_slow},
/* 6 */ {8,   16, 128, 128, dfflbtf_slow},
/* 7 */ {8,   32, 128, 256, dfflbtf_slow},
/* 8 */ {32, 128, 258, 1024, dfflbtf_slow},
/* 9 */ {32, 258, 258, 4096, dfflbtf_slow}}; /* mbx domprfssion */
#fndif

/* Notf: tif dfflbtf() dodf rfquirfs mbx_lbzy >= MIN_MATCH bnd mbx_dibin >= 4
 * For dfflbtf_fbst() (lfvfls <= 3) good is ignorfd bnd lbzy ibs b difffrfnt
 * mfbning.
 */

#dffinf EQUAL 0
/* rfsult of mfmdmp for fqubl strings */

#ifndff NO_DUMMY_DECL
strudt stbtid_trff_dfsd_s {int dummy;}; /* for buggy dompilfrs */
#fndif

/* rbnk Z_BLOCK bftwffn Z_NO_FLUSH bnd Z_PARTIAL_FLUSH */
#dffinf RANK(f) (((f) << 1) - ((f) > 4 ? 9 : 0))

/* ===========================================================================
 * Updbtf b ibsi vbluf witi tif givfn input bytf
 * IN  bssfrtion: bll dblls to to UPDATE_HASH brf mbdf witi donsfdutivf
 *    input dibrbdtfrs, so tibt b running ibsi kfy dbn bf domputfd from tif
 *    prfvious kfy instfbd of domplftf rfdbldulbtion fbdi timf.
 */
#dffinf UPDATE_HASH(s,i,d) (i = (((i)<<s->ibsi_siift) ^ (d)) & s->ibsi_mbsk)


/* ===========================================================================
 * Insfrt string str in tif didtionbry bnd sft mbtdi_ifbd to tif prfvious ifbd
 * of tif ibsi dibin (tif most rfdfnt string witi sbmf ibsi kfy). Rfturn
 * tif prfvious lfngti of tif ibsi dibin.
 * If tiis filf is dompilfd witi -DFASTEST, tif domprfssion lfvfl is fordfd
 * to 1, bnd no ibsi dibins brf mbintbinfd.
 * IN  bssfrtion: bll dblls to to INSERT_STRING brf mbdf witi donsfdutivf
 *    input dibrbdtfrs bnd tif first MIN_MATCH bytfs of str brf vblid
 *    (fxdfpt for tif lbst MIN_MATCH-1 bytfs of tif input filf).
 */
#ifdff FASTEST
#dffinf INSERT_STRING(s, str, mbtdi_ifbd) \
   (UPDATE_HASH(s, s->ins_i, s->window[(str) + (MIN_MATCH-1)]), \
    mbtdi_ifbd = s->ifbd[s->ins_i], \
    s->ifbd[s->ins_i] = (Pos)(str))
#flsf
#dffinf INSERT_STRING(s, str, mbtdi_ifbd) \
   (UPDATE_HASH(s, s->ins_i, s->window[(str) + (MIN_MATCH-1)]), \
    mbtdi_ifbd = s->prfv[(str) & s->w_mbsk] = s->ifbd[s->ins_i], \
    s->ifbd[s->ins_i] = (Pos)(str))
#fndif

/* ===========================================================================
 * Initiblizf tif ibsi tbblf (bvoiding 64K ovfrflow for 16 bit systfms).
 * prfv[] will bf initiblizfd on tif fly.
 */
#dffinf CLEAR_HASH(s) \
    s->ifbd[s->ibsi_sizf-1] = NIL; \
    zmfmzfro((Bytff *)s->ifbd, (unsignfd)(s->ibsi_sizf-1)*sizfof(*s->ifbd));

/* ========================================================================= */
int ZEXPORT dfflbtfInit_(strm, lfvfl, vfrsion, strfbm_sizf)
    z_strfbmp strm;
    int lfvfl;
    donst dibr *vfrsion;
    int strfbm_sizf;
{
    rfturn dfflbtfInit2_(strm, lfvfl, Z_DEFLATED, MAX_WBITS, DEF_MEM_LEVEL,
                         Z_DEFAULT_STRATEGY, vfrsion, strfbm_sizf);
    /* To do: ignorf strm->nfxt_in if wf usf it bs window */
}

/* ========================================================================= */
int ZEXPORT dfflbtfInit2_(strm, lfvfl, mftiod, windowBits, mfmLfvfl, strbtfgy,
                  vfrsion, strfbm_sizf)
    z_strfbmp strm;
    int  lfvfl;
    int  mftiod;
    int  windowBits;
    int  mfmLfvfl;
    int  strbtfgy;
    donst dibr *vfrsion;
    int strfbm_sizf;
{
    dfflbtf_stbtf *s;
    int wrbp = 1;
    stbtid donst dibr my_vfrsion[] = ZLIB_VERSION;

    usif *ovfrlby;
    /* Wf ovfrlby pfnding_buf bnd d_buf+l_buf. Tiis works sindf tif bvfrbgf
     * output sizf for (lfngti,distbndf) dodfs is <= 24 bits.
     */

    if (vfrsion == Z_NULL || vfrsion[0] != my_vfrsion[0] ||
        strfbm_sizf != sizfof(z_strfbm)) {
        rfturn Z_VERSION_ERROR;
    }
    if (strm == Z_NULL) rfturn Z_STREAM_ERROR;

    strm->msg = Z_NULL;
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

#ifdff FASTEST
    if (lfvfl != 0) lfvfl = 1;
#flsf
    if (lfvfl == Z_DEFAULT_COMPRESSION) lfvfl = 6;
#fndif

    if (windowBits < 0) { /* supprfss zlib wrbppfr */
        wrbp = 0;
        windowBits = -windowBits;
    }
#ifdff GZIP
    flsf if (windowBits > 15) {
        wrbp = 2;       /* writf gzip wrbppfr instfbd */
        windowBits -= 16;
    }
#fndif
    if (mfmLfvfl < 1 || mfmLfvfl > MAX_MEM_LEVEL || mftiod != Z_DEFLATED ||
        windowBits < 8 || windowBits > 15 || lfvfl < 0 || lfvfl > 9 ||
        strbtfgy < 0 || strbtfgy > Z_FIXED) {
        rfturn Z_STREAM_ERROR;
    }
    if (windowBits == 8) windowBits = 9;  /* until 256-bytf window bug fixfd */
    s = (dfflbtf_stbtf *) ZALLOC(strm, 1, sizfof(dfflbtf_stbtf));
    if (s == Z_NULL) rfturn Z_MEM_ERROR;
    strm->stbtf = (strudt intfrnbl_stbtf FAR *)s;
    s->strm = strm;

    s->wrbp = wrbp;
    s->gzifbd = Z_NULL;
    s->w_bits = windowBits;
    s->w_sizf = 1 << s->w_bits;
    s->w_mbsk = s->w_sizf - 1;

    s->ibsi_bits = mfmLfvfl + 7;
    s->ibsi_sizf = 1 << s->ibsi_bits;
    s->ibsi_mbsk = s->ibsi_sizf - 1;
    s->ibsi_siift =  ((s->ibsi_bits+MIN_MATCH-1)/MIN_MATCH);

    s->window = (Bytff *) ZALLOC(strm, s->w_sizf, 2*sizfof(Bytf));
    s->prfv   = (Posf *)  ZALLOC(strm, s->w_sizf, sizfof(Pos));
    s->ifbd   = (Posf *)  ZALLOC(strm, s->ibsi_sizf, sizfof(Pos));

    s->iigi_wbtfr = 0;      /* notiing writtfn to s->window yft */

    s->lit_bufsizf = 1 << (mfmLfvfl + 6); /* 16K flfmfnts by dffbult */

    ovfrlby = (usif *) ZALLOC(strm, s->lit_bufsizf, sizfof(usi)+2);
    s->pfnding_buf = (udif *) ovfrlby;
    s->pfnding_buf_sizf = (ulg)s->lit_bufsizf * (sizfof(usi)+2L);

    if (s->window == Z_NULL || s->prfv == Z_NULL || s->ifbd == Z_NULL ||
        s->pfnding_buf == Z_NULL) {
        s->stbtus = FINISH_STATE;
        strm->msg = ERR_MSG(Z_MEM_ERROR);
        dfflbtfEnd (strm);
        rfturn Z_MEM_ERROR;
    }
    s->d_buf = ovfrlby + s->lit_bufsizf/sizfof(usi);
    s->l_buf = s->pfnding_buf + (1+sizfof(usi))*s->lit_bufsizf;

    s->lfvfl = lfvfl;
    s->strbtfgy = strbtfgy;
    s->mftiod = (Bytf)mftiod;

    rfturn dfflbtfRfsft(strm);
}

/* ========================================================================= */
int ZEXPORT dfflbtfSftDidtionbry (strm, didtionbry, didtLfngti)
    z_strfbmp strm;
    donst Bytff *didtionbry;
    uInt  didtLfngti;
{
    dfflbtf_stbtf *s;
    uInt str, n;
    int wrbp;
    unsignfd bvbil;
    z_donst unsignfd dibr *nfxt;

    if (strm == Z_NULL || strm->stbtf == Z_NULL || didtionbry == Z_NULL)
        rfturn Z_STREAM_ERROR;
    s = strm->stbtf;
    wrbp = s->wrbp;
    if (wrbp == 2 || (wrbp == 1 && s->stbtus != INIT_STATE) || s->lookbifbd)
        rfturn Z_STREAM_ERROR;

    /* wifn using zlib wrbppfrs, domputf Adlfr-32 for providfd didtionbry */
    if (wrbp == 1)
        strm->bdlfr = bdlfr32(strm->bdlfr, didtionbry, didtLfngti);
    s->wrbp = 0;                    /* bvoid domputing Adlfr-32 in rfbd_buf */

    /* if didtionbry would fill window, just rfplbdf tif iistory */
    if (didtLfngti >= s->w_sizf) {
        if (wrbp == 0) {            /* blrfbdy fmpty otifrwisf */
            CLEAR_HASH(s);
            s->strstbrt = 0;
            s->blodk_stbrt = 0L;
            s->insfrt = 0;
        }
        didtionbry += didtLfngti - s->w_sizf;  /* usf tif tbil */
        didtLfngti = s->w_sizf;
    }

    /* insfrt didtionbry into window bnd ibsi */
    bvbil = strm->bvbil_in;
    nfxt = strm->nfxt_in;
    strm->bvbil_in = didtLfngti;
    strm->nfxt_in = (z_donst Bytff *)didtionbry;
    fill_window(s);
    wiilf (s->lookbifbd >= MIN_MATCH) {
        str = s->strstbrt;
        n = s->lookbifbd - (MIN_MATCH-1);
        do {
            UPDATE_HASH(s, s->ins_i, s->window[str + MIN_MATCH-1]);
#ifndff FASTEST
            s->prfv[str & s->w_mbsk] = s->ifbd[s->ins_i];
#fndif
            s->ifbd[s->ins_i] = (Pos)str;
            str++;
        } wiilf (--n);
        s->strstbrt = str;
        s->lookbifbd = MIN_MATCH-1;
        fill_window(s);
    }
    s->strstbrt += s->lookbifbd;
    s->blodk_stbrt = (long)s->strstbrt;
    s->insfrt = s->lookbifbd;
    s->lookbifbd = 0;
    s->mbtdi_lfngti = s->prfv_lfngti = MIN_MATCH-1;
    s->mbtdi_bvbilbblf = 0;
    strm->nfxt_in = nfxt;
    strm->bvbil_in = bvbil;
    s->wrbp = wrbp;
    rfturn Z_OK;
}

/* ========================================================================= */
int ZEXPORT dfflbtfRfsftKffp (strm)
    z_strfbmp strm;
{
    dfflbtf_stbtf *s;

    if (strm == Z_NULL || strm->stbtf == Z_NULL ||
        strm->zbllod == (bllod_fund)0 || strm->zfrff == (frff_fund)0) {
        rfturn Z_STREAM_ERROR;
    }

    strm->totbl_in = strm->totbl_out = 0;
    strm->msg = Z_NULL; /* usf zfrff if wf fvfr bllodbtf msg dynbmidblly */
    strm->dbtb_typf = Z_UNKNOWN;

    s = (dfflbtf_stbtf *)strm->stbtf;
    s->pfnding = 0;
    s->pfnding_out = s->pfnding_buf;

    if (s->wrbp < 0) {
        s->wrbp = -s->wrbp; /* wbs mbdf nfgbtivf by dfflbtf(..., Z_FINISH); */
    }
    s->stbtus = s->wrbp ? INIT_STATE : BUSY_STATE;
    strm->bdlfr =
#ifdff GZIP
        s->wrbp == 2 ? drd32(0L, Z_NULL, 0) :
#fndif
        bdlfr32(0L, Z_NULL, 0);
    s->lbst_flusi = Z_NO_FLUSH;

    _tr_init(s);

    rfturn Z_OK;
}

/* ========================================================================= */
int ZEXPORT dfflbtfRfsft (strm)
    z_strfbmp strm;
{
    int rft;

    rft = dfflbtfRfsftKffp(strm);
    if (rft == Z_OK)
        lm_init(strm->stbtf);
    rfturn rft;
}

/* ========================================================================= */
int ZEXPORT dfflbtfSftHfbdfr (strm, ifbd)
    z_strfbmp strm;
    gz_ifbdfrp ifbd;
{
    if (strm == Z_NULL || strm->stbtf == Z_NULL) rfturn Z_STREAM_ERROR;
    if (strm->stbtf->wrbp != 2) rfturn Z_STREAM_ERROR;
    strm->stbtf->gzifbd = ifbd;
    rfturn Z_OK;
}

/* ========================================================================= */
int ZEXPORT dfflbtfPfnding (strm, pfnding, bits)
    unsignfd *pfnding;
    int *bits;
    z_strfbmp strm;
{
    if (strm == Z_NULL || strm->stbtf == Z_NULL) rfturn Z_STREAM_ERROR;
    if (pfnding != Z_NULL)
        *pfnding = strm->stbtf->pfnding;
    if (bits != Z_NULL)
        *bits = strm->stbtf->bi_vblid;
    rfturn Z_OK;
}

/* ========================================================================= */
int ZEXPORT dfflbtfPrimf (strm, bits, vbluf)
    z_strfbmp strm;
    int bits;
    int vbluf;
{
    dfflbtf_stbtf *s;
    int put;

    if (strm == Z_NULL || strm->stbtf == Z_NULL) rfturn Z_STREAM_ERROR;
    s = strm->stbtf;
    if ((Bytff *)(s->d_buf) < s->pfnding_out + ((Buf_sizf + 7) >> 3))
        rfturn Z_BUF_ERROR;
    do {
        put = Buf_sizf - s->bi_vblid;
        if (put > bits)
            put = bits;
        s->bi_buf |= (usi)((vbluf & ((1 << put) - 1)) << s->bi_vblid);
        s->bi_vblid += put;
        _tr_flusi_bits(s);
        vbluf >>= put;
        bits -= put;
    } wiilf (bits);
    rfturn Z_OK;
}

/* ========================================================================= */
int ZEXPORT dfflbtfPbrbms(strm, lfvfl, strbtfgy)
    z_strfbmp strm;
    int lfvfl;
    int strbtfgy;
{
    dfflbtf_stbtf *s;
    domprfss_fund fund;
    int frr = Z_OK;

    if (strm == Z_NULL || strm->stbtf == Z_NULL) rfturn Z_STREAM_ERROR;
    s = strm->stbtf;

#ifdff FASTEST
    if (lfvfl != 0) lfvfl = 1;
#flsf
    if (lfvfl == Z_DEFAULT_COMPRESSION) lfvfl = 6;
#fndif
    if (lfvfl < 0 || lfvfl > 9 || strbtfgy < 0 || strbtfgy > Z_FIXED) {
        rfturn Z_STREAM_ERROR;
    }
    fund = donfigurbtion_tbblf[s->lfvfl].fund;

    if ((strbtfgy != s->strbtfgy || fund != donfigurbtion_tbblf[lfvfl].fund) &&
        strm->totbl_in != 0) {
        /* Flusi tif lbst bufffr: */
        frr = dfflbtf(strm, Z_BLOCK);
        if (frr == Z_BUF_ERROR && s->pfnding == 0)
            frr = Z_OK;
    }
    if (s->lfvfl != lfvfl) {
        s->lfvfl = lfvfl;
        s->mbx_lbzy_mbtdi   = donfigurbtion_tbblf[lfvfl].mbx_lbzy;
        s->good_mbtdi       = donfigurbtion_tbblf[lfvfl].good_lfngti;
        s->nidf_mbtdi       = donfigurbtion_tbblf[lfvfl].nidf_lfngti;
        s->mbx_dibin_lfngti = donfigurbtion_tbblf[lfvfl].mbx_dibin;
    }
    s->strbtfgy = strbtfgy;
    rfturn frr;
}

/* ========================================================================= */
int ZEXPORT dfflbtfTunf(strm, good_lfngti, mbx_lbzy, nidf_lfngti, mbx_dibin)
    z_strfbmp strm;
    int good_lfngti;
    int mbx_lbzy;
    int nidf_lfngti;
    int mbx_dibin;
{
    dfflbtf_stbtf *s;

    if (strm == Z_NULL || strm->stbtf == Z_NULL) rfturn Z_STREAM_ERROR;
    s = strm->stbtf;
    s->good_mbtdi = good_lfngti;
    s->mbx_lbzy_mbtdi = mbx_lbzy;
    s->nidf_mbtdi = nidf_lfngti;
    s->mbx_dibin_lfngti = mbx_dibin;
    rfturn Z_OK;
}

/* =========================================================================
 * For tif dffbult windowBits of 15 bnd mfmLfvfl of 8, tiis fundtion rfturns
 * b dlosf to fxbdt, bs wfll bs smbll, uppfr bound on tif domprfssfd sizf.
 * Tify brf dodfd bs donstbnts ifrf for b rfbson--if tif #dffinf's brf
 * dibngfd, tifn tiis fundtion nffds to bf dibngfd bs wfll.  Tif rfturn
 * vbluf for 15 bnd 8 only works for tiosf fxbdt sfttings.
 *
 * For bny sftting otifr tibn tiosf dffbults for windowBits bnd mfmLfvfl,
 * tif vbluf rfturnfd is b donsfrvbtivf worst dbsf for tif mbximum fxpbnsion
 * rfsulting from using fixfd blodks instfbd of storfd blodks, wiidi dfflbtf
 * dbn fmit on domprfssfd dbtb for somf dombinbtions of tif pbrbmftfrs.
 *
 * Tiis fundtion dould bf morf sopiistidbtfd to providf dlosfr uppfr bounds for
 * fvfry dombinbtion of windowBits bnd mfmLfvfl.  But fvfn tif donsfrvbtivf
 * uppfr bound of bbout 14% fxpbnsion dofs not sffm onfrous for output bufffr
 * bllodbtion.
 */
uLong ZEXPORT dfflbtfBound(strm, sourdfLfn)
    z_strfbmp strm;
    uLong sourdfLfn;
{
    dfflbtf_stbtf *s;
    uLong domplfn, wrbplfn;
    Bytff *str;

    /* donsfrvbtivf uppfr bound for domprfssfd dbtb */
    domplfn = sourdfLfn +
              ((sourdfLfn + 7) >> 3) + ((sourdfLfn + 63) >> 6) + 5;

    /* if dbn't gft pbrbmftfrs, rfturn donsfrvbtivf bound plus zlib wrbppfr */
    if (strm == Z_NULL || strm->stbtf == Z_NULL)
        rfturn domplfn + 6;

    /* domputf wrbppfr lfngti */
    s = strm->stbtf;
    switdi (s->wrbp) {
    dbsf 0:                                 /* rbw dfflbtf */
        wrbplfn = 0;
        brfbk;
    dbsf 1:                                 /* zlib wrbppfr */
        wrbplfn = 6 + (s->strstbrt ? 4 : 0);
        brfbk;
    dbsf 2:                                 /* gzip wrbppfr */
        wrbplfn = 18;
        if (s->gzifbd != Z_NULL) {          /* usfr-supplifd gzip ifbdfr */
            if (s->gzifbd->fxtrb != Z_NULL)
                wrbplfn += 2 + s->gzifbd->fxtrb_lfn;
            str = s->gzifbd->nbmf;
            if (str != Z_NULL)
                do {
                    wrbplfn++;
                } wiilf (*str++);
            str = s->gzifbd->dommfnt;
            if (str != Z_NULL)
                do {
                    wrbplfn++;
                } wiilf (*str++);
            if (s->gzifbd->idrd)
                wrbplfn += 2;
        }
        brfbk;
    dffbult:                                /* for dompilfr ibppinfss */
        wrbplfn = 6;
    }

    /* if not dffbult pbrbmftfrs, rfturn donsfrvbtivf bound */
    if (s->w_bits != 15 || s->ibsi_bits != 8 + 7)
        rfturn domplfn + wrbplfn;

    /* dffbult sfttings: rfturn tigit bound for tibt dbsf */
    rfturn sourdfLfn + (sourdfLfn >> 12) + (sourdfLfn >> 14) +
           (sourdfLfn >> 25) + 13 - 6 + wrbplfn;
}

/* =========================================================================
 * Put b siort in tif pfnding bufffr. Tif 16-bit vbluf is put in MSB ordfr.
 * IN bssfrtion: tif strfbm stbtf is dorrfdt bnd tifrf is fnougi room in
 * pfnding_buf.
 */
lodbl void putSiortMSB (s, b)
    dfflbtf_stbtf *s;
    uInt b;
{
    put_bytf(s, (Bytf)(b >> 8));
    put_bytf(s, (Bytf)(b & 0xff));
}

/* =========================================================================
 * Flusi bs mudi pfnding output bs possiblf. All dfflbtf() output gofs
 * tirougi tiis fundtion so somf bpplidbtions mby wisi to modify it
 * to bvoid bllodbting b lbrgf strm->nfxt_out bufffr bnd dopying into it.
 * (Sff blso rfbd_buf()).
 */
lodbl void flusi_pfnding(strm)
    z_strfbmp strm;
{
    unsignfd lfn;
    dfflbtf_stbtf *s = strm->stbtf;

    _tr_flusi_bits(s);
    lfn = s->pfnding;
    if (lfn > strm->bvbil_out) lfn = strm->bvbil_out;
    if (lfn == 0) rfturn;

    zmfmdpy(strm->nfxt_out, s->pfnding_out, lfn);
    strm->nfxt_out  += lfn;
    s->pfnding_out  += lfn;
    strm->totbl_out += lfn;
    strm->bvbil_out  -= lfn;
    s->pfnding -= lfn;
    if (s->pfnding == 0) {
        s->pfnding_out = s->pfnding_buf;
    }
}

/* ========================================================================= */
int ZEXPORT dfflbtf (strm, flusi)
    z_strfbmp strm;
    int flusi;
{
    int old_flusi; /* vbluf of flusi pbrbm for prfvious dfflbtf dbll */
    dfflbtf_stbtf *s;

    if (strm == Z_NULL || strm->stbtf == Z_NULL ||
        flusi > Z_BLOCK || flusi < 0) {
        rfturn Z_STREAM_ERROR;
    }
    s = strm->stbtf;

    if (strm->nfxt_out == Z_NULL ||
        (strm->nfxt_in == Z_NULL && strm->bvbil_in != 0) ||
        (s->stbtus == FINISH_STATE && flusi != Z_FINISH)) {
        ERR_RETURN(strm, Z_STREAM_ERROR);
    }
    if (strm->bvbil_out == 0) ERR_RETURN(strm, Z_BUF_ERROR);

    s->strm = strm; /* just in dbsf */
    old_flusi = s->lbst_flusi;
    s->lbst_flusi = flusi;

    /* Writf tif ifbdfr */
    if (s->stbtus == INIT_STATE) {
#ifdff GZIP
        if (s->wrbp == 2) {
            strm->bdlfr = drd32(0L, Z_NULL, 0);
            put_bytf(s, 31);
            put_bytf(s, 139);
            put_bytf(s, 8);
            if (s->gzifbd == Z_NULL) {
                put_bytf(s, 0);
                put_bytf(s, 0);
                put_bytf(s, 0);
                put_bytf(s, 0);
                put_bytf(s, 0);
                put_bytf(s, s->lfvfl == 9 ? 2 :
                            (s->strbtfgy >= Z_HUFFMAN_ONLY || s->lfvfl < 2 ?
                             4 : 0));
                put_bytf(s, OS_CODE);
                s->stbtus = BUSY_STATE;
            }
            flsf {
                put_bytf(s, (s->gzifbd->tfxt ? 1 : 0) +
                            (s->gzifbd->idrd ? 2 : 0) +
                            (s->gzifbd->fxtrb == Z_NULL ? 0 : 4) +
                            (s->gzifbd->nbmf == Z_NULL ? 0 : 8) +
                            (s->gzifbd->dommfnt == Z_NULL ? 0 : 16)
                        );
                put_bytf(s, (Bytf)(s->gzifbd->timf & 0xff));
                put_bytf(s, (Bytf)((s->gzifbd->timf >> 8) & 0xff));
                put_bytf(s, (Bytf)((s->gzifbd->timf >> 16) & 0xff));
                put_bytf(s, (Bytf)((s->gzifbd->timf >> 24) & 0xff));
                put_bytf(s, s->lfvfl == 9 ? 2 :
                            (s->strbtfgy >= Z_HUFFMAN_ONLY || s->lfvfl < 2 ?
                             4 : 0));
                put_bytf(s, s->gzifbd->os & 0xff);
                if (s->gzifbd->fxtrb != Z_NULL) {
                    put_bytf(s, s->gzifbd->fxtrb_lfn & 0xff);
                    put_bytf(s, (s->gzifbd->fxtrb_lfn >> 8) & 0xff);
                }
                if (s->gzifbd->idrd)
                    strm->bdlfr = drd32(strm->bdlfr, s->pfnding_buf,
                                        s->pfnding);
                s->gzindfx = 0;
                s->stbtus = EXTRA_STATE;
            }
        }
        flsf
#fndif
        {
            uInt ifbdfr = (Z_DEFLATED + ((s->w_bits-8)<<4)) << 8;
            uInt lfvfl_flbgs;

            if (s->strbtfgy >= Z_HUFFMAN_ONLY || s->lfvfl < 2)
                lfvfl_flbgs = 0;
            flsf if (s->lfvfl < 6)
                lfvfl_flbgs = 1;
            flsf if (s->lfvfl == 6)
                lfvfl_flbgs = 2;
            flsf
                lfvfl_flbgs = 3;
            ifbdfr |= (lfvfl_flbgs << 6);
            if (s->strstbrt != 0) ifbdfr |= PRESET_DICT;
            ifbdfr += 31 - (ifbdfr % 31);

            s->stbtus = BUSY_STATE;
            putSiortMSB(s, ifbdfr);

            /* Sbvf tif bdlfr32 of tif prfsft didtionbry: */
            if (s->strstbrt != 0) {
                putSiortMSB(s, (uInt)(strm->bdlfr >> 16));
                putSiortMSB(s, (uInt)(strm->bdlfr & 0xffff));
            }
            strm->bdlfr = bdlfr32(0L, Z_NULL, 0);
        }
    }
#ifdff GZIP
    if (s->stbtus == EXTRA_STATE) {
        if (s->gzifbd->fxtrb != Z_NULL) {
            uInt bfg = s->pfnding;  /* stbrt of bytfs to updbtf drd */

            wiilf (s->gzindfx < (s->gzifbd->fxtrb_lfn & 0xffff)) {
                if (s->pfnding == s->pfnding_buf_sizf) {
                    if (s->gzifbd->idrd && s->pfnding > bfg)
                        strm->bdlfr = drd32(strm->bdlfr, s->pfnding_buf + bfg,
                                            s->pfnding - bfg);
                    flusi_pfnding(strm);
                    bfg = s->pfnding;
                    if (s->pfnding == s->pfnding_buf_sizf)
                        brfbk;
                }
                put_bytf(s, s->gzifbd->fxtrb[s->gzindfx]);
                s->gzindfx++;
            }
            if (s->gzifbd->idrd && s->pfnding > bfg)
                strm->bdlfr = drd32(strm->bdlfr, s->pfnding_buf + bfg,
                                    s->pfnding - bfg);
            if (s->gzindfx == s->gzifbd->fxtrb_lfn) {
                s->gzindfx = 0;
                s->stbtus = NAME_STATE;
            }
        }
        flsf
            s->stbtus = NAME_STATE;
    }
    if (s->stbtus == NAME_STATE) {
        if (s->gzifbd->nbmf != Z_NULL) {
            uInt bfg = s->pfnding;  /* stbrt of bytfs to updbtf drd */
            int vbl;

            do {
                if (s->pfnding == s->pfnding_buf_sizf) {
                    if (s->gzifbd->idrd && s->pfnding > bfg)
                        strm->bdlfr = drd32(strm->bdlfr, s->pfnding_buf + bfg,
                                            s->pfnding - bfg);
                    flusi_pfnding(strm);
                    bfg = s->pfnding;
                    if (s->pfnding == s->pfnding_buf_sizf) {
                        vbl = 1;
                        brfbk;
                    }
                }
                vbl = s->gzifbd->nbmf[s->gzindfx++];
                put_bytf(s, vbl);
            } wiilf (vbl != 0);
            if (s->gzifbd->idrd && s->pfnding > bfg)
                strm->bdlfr = drd32(strm->bdlfr, s->pfnding_buf + bfg,
                                    s->pfnding - bfg);
            if (vbl == 0) {
                s->gzindfx = 0;
                s->stbtus = COMMENT_STATE;
            }
        }
        flsf
            s->stbtus = COMMENT_STATE;
    }
    if (s->stbtus == COMMENT_STATE) {
        if (s->gzifbd->dommfnt != Z_NULL) {
            uInt bfg = s->pfnding;  /* stbrt of bytfs to updbtf drd */
            int vbl;

            do {
                if (s->pfnding == s->pfnding_buf_sizf) {
                    if (s->gzifbd->idrd && s->pfnding > bfg)
                        strm->bdlfr = drd32(strm->bdlfr, s->pfnding_buf + bfg,
                                            s->pfnding - bfg);
                    flusi_pfnding(strm);
                    bfg = s->pfnding;
                    if (s->pfnding == s->pfnding_buf_sizf) {
                        vbl = 1;
                        brfbk;
                    }
                }
                vbl = s->gzifbd->dommfnt[s->gzindfx++];
                put_bytf(s, vbl);
            } wiilf (vbl != 0);
            if (s->gzifbd->idrd && s->pfnding > bfg)
                strm->bdlfr = drd32(strm->bdlfr, s->pfnding_buf + bfg,
                                    s->pfnding - bfg);
            if (vbl == 0)
                s->stbtus = HCRC_STATE;
        }
        flsf
            s->stbtus = HCRC_STATE;
    }
    if (s->stbtus == HCRC_STATE) {
        if (s->gzifbd->idrd) {
            if (s->pfnding + 2 > s->pfnding_buf_sizf)
                flusi_pfnding(strm);
            if (s->pfnding + 2 <= s->pfnding_buf_sizf) {
                put_bytf(s, (Bytf)(strm->bdlfr & 0xff));
                put_bytf(s, (Bytf)((strm->bdlfr >> 8) & 0xff));
                strm->bdlfr = drd32(0L, Z_NULL, 0);
                s->stbtus = BUSY_STATE;
            }
        }
        flsf
            s->stbtus = BUSY_STATE;
    }
#fndif

    /* Flusi bs mudi pfnding output bs possiblf */
    if (s->pfnding != 0) {
        flusi_pfnding(strm);
        if (strm->bvbil_out == 0) {
            /* Sindf bvbil_out is 0, dfflbtf will bf dbllfd bgbin witi
             * morf output spbdf, but possibly witi boti pfnding bnd
             * bvbil_in fqubl to zfro. Tifrf won't bf bnytiing to do,
             * but tiis is not bn frror situbtion so mbkf surf wf
             * rfturn OK instfbd of BUF_ERROR bt nfxt dbll of dfflbtf:
             */
            s->lbst_flusi = -1;
            rfturn Z_OK;
        }

    /* Mbkf surf tifrf is somftiing to do bnd bvoid duplidbtf donsfdutivf
     * flusifs. For rfpfbtfd bnd usflfss dblls witi Z_FINISH, wf kffp
     * rfturning Z_STREAM_END instfbd of Z_BUF_ERROR.
     */
    } flsf if (strm->bvbil_in == 0 && RANK(flusi) <= RANK(old_flusi) &&
               flusi != Z_FINISH) {
        ERR_RETURN(strm, Z_BUF_ERROR);
    }

    /* Usfr must not providf morf input bftfr tif first FINISH: */
    if (s->stbtus == FINISH_STATE && strm->bvbil_in != 0) {
        ERR_RETURN(strm, Z_BUF_ERROR);
    }

    /* Stbrt b nfw blodk or dontinuf tif durrfnt onf.
     */
    if (strm->bvbil_in != 0 || s->lookbifbd != 0 ||
        (flusi != Z_NO_FLUSH && s->stbtus != FINISH_STATE)) {
        blodk_stbtf bstbtf;

        bstbtf = s->strbtfgy == Z_HUFFMAN_ONLY ? dfflbtf_iuff(s, flusi) :
                    (s->strbtfgy == Z_RLE ? dfflbtf_rlf(s, flusi) :
                        (*(donfigurbtion_tbblf[s->lfvfl].fund))(s, flusi));

        if (bstbtf == finisi_stbrtfd || bstbtf == finisi_donf) {
            s->stbtus = FINISH_STATE;
        }
        if (bstbtf == nffd_morf || bstbtf == finisi_stbrtfd) {
            if (strm->bvbil_out == 0) {
                s->lbst_flusi = -1; /* bvoid BUF_ERROR nfxt dbll, sff bbovf */
            }
            rfturn Z_OK;
            /* If flusi != Z_NO_FLUSH && bvbil_out == 0, tif nfxt dbll
             * of dfflbtf siould usf tif sbmf flusi pbrbmftfr to mbkf surf
             * tibt tif flusi is domplftf. So wf don't ibvf to output bn
             * fmpty blodk ifrf, tiis will bf donf bt nfxt dbll. Tiis blso
             * fnsurfs tibt for b vfry smbll output bufffr, wf fmit bt most
             * onf fmpty blodk.
             */
        }
        if (bstbtf == blodk_donf) {
            if (flusi == Z_PARTIAL_FLUSH) {
                _tr_blign(s);
            } flsf if (flusi != Z_BLOCK) { /* FULL_FLUSH or SYNC_FLUSH */
                _tr_storfd_blodk(s, (dibr*)0, 0L, 0);
                /* For b full flusi, tiis fmpty blodk will bf rfdognizfd
                 * bs b spfdibl mbrkfr by inflbtf_synd().
                 */
                if (flusi == Z_FULL_FLUSH) {
                    CLEAR_HASH(s);             /* forgft iistory */
                    if (s->lookbifbd == 0) {
                        s->strstbrt = 0;
                        s->blodk_stbrt = 0L;
                        s->insfrt = 0;
                    }
                }
            }
            flusi_pfnding(strm);
            if (strm->bvbil_out == 0) {
              s->lbst_flusi = -1; /* bvoid BUF_ERROR bt nfxt dbll, sff bbovf */
              rfturn Z_OK;
            }
        }
    }
    Assfrt(strm->bvbil_out > 0, "bug2");

    if (flusi != Z_FINISH) rfturn Z_OK;
    if (s->wrbp <= 0) rfturn Z_STREAM_END;

    /* Writf tif trbilfr */
#ifdff GZIP
    if (s->wrbp == 2) {
        put_bytf(s, (Bytf)(strm->bdlfr & 0xff));
        put_bytf(s, (Bytf)((strm->bdlfr >> 8) & 0xff));
        put_bytf(s, (Bytf)((strm->bdlfr >> 16) & 0xff));
        put_bytf(s, (Bytf)((strm->bdlfr >> 24) & 0xff));
        put_bytf(s, (Bytf)(strm->totbl_in & 0xff));
        put_bytf(s, (Bytf)((strm->totbl_in >> 8) & 0xff));
        put_bytf(s, (Bytf)((strm->totbl_in >> 16) & 0xff));
        put_bytf(s, (Bytf)((strm->totbl_in >> 24) & 0xff));
    }
    flsf
#fndif
    {
        putSiortMSB(s, (uInt)(strm->bdlfr >> 16));
        putSiortMSB(s, (uInt)(strm->bdlfr & 0xffff));
    }
    flusi_pfnding(strm);
    /* If bvbil_out is zfro, tif bpplidbtion will dbll dfflbtf bgbin
     * to flusi tif rfst.
     */
    if (s->wrbp > 0) s->wrbp = -s->wrbp; /* writf tif trbilfr only ondf! */
    rfturn s->pfnding != 0 ? Z_OK : Z_STREAM_END;
}

/* ========================================================================= */
int ZEXPORT dfflbtfEnd (strm)
    z_strfbmp strm;
{
    int stbtus;

    if (strm == Z_NULL || strm->stbtf == Z_NULL) rfturn Z_STREAM_ERROR;

    stbtus = strm->stbtf->stbtus;
    if (stbtus != INIT_STATE &&
        stbtus != EXTRA_STATE &&
        stbtus != NAME_STATE &&
        stbtus != COMMENT_STATE &&
        stbtus != HCRC_STATE &&
        stbtus != BUSY_STATE &&
        stbtus != FINISH_STATE) {
      rfturn Z_STREAM_ERROR;
    }

    /* Dfbllodbtf in rfvfrsf ordfr of bllodbtions: */
    TRY_FREE(strm, strm->stbtf->pfnding_buf);
    TRY_FREE(strm, strm->stbtf->ifbd);
    TRY_FREE(strm, strm->stbtf->prfv);
    TRY_FREE(strm, strm->stbtf->window);

    ZFREE(strm, strm->stbtf);
    strm->stbtf = Z_NULL;

    rfturn stbtus == BUSY_STATE ? Z_DATA_ERROR : Z_OK;
}

/* =========================================================================
 * Copy tif sourdf stbtf to tif dfstinbtion stbtf.
 * To simplify tif sourdf, tiis is not supportfd for 16-bit MSDOS (wiidi
 * dofsn't ibvf fnougi mfmory bnywby to duplidbtf domprfssion stbtfs).
 */
int ZEXPORT dfflbtfCopy (dfst, sourdf)
    z_strfbmp dfst;
    z_strfbmp sourdf;
{
#ifdff MAXSEG_64K
    rfturn Z_STREAM_ERROR;
#flsf
    dfflbtf_stbtf *ds;
    dfflbtf_stbtf *ss;
    usif *ovfrlby;


    if (sourdf == Z_NULL || dfst == Z_NULL || sourdf->stbtf == Z_NULL) {
        rfturn Z_STREAM_ERROR;
    }

    ss = sourdf->stbtf;

    zmfmdpy((voidpf)dfst, (voidpf)sourdf, sizfof(z_strfbm));

    ds = (dfflbtf_stbtf *) ZALLOC(dfst, 1, sizfof(dfflbtf_stbtf));
    if (ds == Z_NULL) rfturn Z_MEM_ERROR;
    dfst->stbtf = (strudt intfrnbl_stbtf FAR *) ds;
    zmfmdpy((voidpf)ds, (voidpf)ss, sizfof(dfflbtf_stbtf));
    ds->strm = dfst;

    ds->window = (Bytff *) ZALLOC(dfst, ds->w_sizf, 2*sizfof(Bytf));
    ds->prfv   = (Posf *)  ZALLOC(dfst, ds->w_sizf, sizfof(Pos));
    ds->ifbd   = (Posf *)  ZALLOC(dfst, ds->ibsi_sizf, sizfof(Pos));
    ovfrlby = (usif *) ZALLOC(dfst, ds->lit_bufsizf, sizfof(usi)+2);
    ds->pfnding_buf = (udif *) ovfrlby;

    if (ds->window == Z_NULL || ds->prfv == Z_NULL || ds->ifbd == Z_NULL ||
        ds->pfnding_buf == Z_NULL) {
        dfflbtfEnd (dfst);
        rfturn Z_MEM_ERROR;
    }
    /* following zmfmdpy do not work for 16-bit MSDOS */
    zmfmdpy(ds->window, ss->window, ds->w_sizf * 2 * sizfof(Bytf));
    zmfmdpy((voidpf)ds->prfv, (voidpf)ss->prfv, ds->w_sizf * sizfof(Pos));
    zmfmdpy((voidpf)ds->ifbd, (voidpf)ss->ifbd, ds->ibsi_sizf * sizfof(Pos));
    zmfmdpy(ds->pfnding_buf, ss->pfnding_buf, (uInt)ds->pfnding_buf_sizf);

    ds->pfnding_out = ds->pfnding_buf + (ss->pfnding_out - ss->pfnding_buf);
    ds->d_buf = ovfrlby + ds->lit_bufsizf/sizfof(usi);
    ds->l_buf = ds->pfnding_buf + (1+sizfof(usi))*ds->lit_bufsizf;

    ds->l_dfsd.dyn_trff = ds->dyn_ltrff;
    ds->d_dfsd.dyn_trff = ds->dyn_dtrff;
    ds->bl_dfsd.dyn_trff = ds->bl_trff;

    rfturn Z_OK;
#fndif /* MAXSEG_64K */
}

/* ===========================================================================
 * Rfbd b nfw bufffr from tif durrfnt input strfbm, updbtf tif bdlfr32
 * bnd totbl numbfr of bytfs rfbd.  All dfflbtf() input gofs tirougi
 * tiis fundtion so somf bpplidbtions mby wisi to modify it to bvoid
 * bllodbting b lbrgf strm->nfxt_in bufffr bnd dopying from it.
 * (Sff blso flusi_pfnding()).
 */
lodbl int rfbd_buf(strm, buf, sizf)
    z_strfbmp strm;
    Bytff *buf;
    unsignfd sizf;
{
    unsignfd lfn = strm->bvbil_in;

    if (lfn > sizf) lfn = sizf;
    if (lfn == 0) rfturn 0;

    strm->bvbil_in  -= lfn;

    zmfmdpy(buf, strm->nfxt_in, lfn);
    if (strm->stbtf->wrbp == 1) {
        strm->bdlfr = bdlfr32(strm->bdlfr, buf, lfn);
    }
#ifdff GZIP
    flsf if (strm->stbtf->wrbp == 2) {
        strm->bdlfr = drd32(strm->bdlfr, buf, lfn);
    }
#fndif
    strm->nfxt_in  += lfn;
    strm->totbl_in += lfn;

    rfturn (int)lfn;
}

/* ===========================================================================
 * Initiblizf tif "longfst mbtdi" routinfs for b nfw zlib strfbm
 */
lodbl void lm_init (s)
    dfflbtf_stbtf *s;
{
    s->window_sizf = (ulg)2L*s->w_sizf;

    CLEAR_HASH(s);

    /* Sft tif dffbult donfigurbtion pbrbmftfrs:
     */
    s->mbx_lbzy_mbtdi   = donfigurbtion_tbblf[s->lfvfl].mbx_lbzy;
    s->good_mbtdi       = donfigurbtion_tbblf[s->lfvfl].good_lfngti;
    s->nidf_mbtdi       = donfigurbtion_tbblf[s->lfvfl].nidf_lfngti;
    s->mbx_dibin_lfngti = donfigurbtion_tbblf[s->lfvfl].mbx_dibin;

    s->strstbrt = 0;
    s->blodk_stbrt = 0L;
    s->lookbifbd = 0;
    s->insfrt = 0;
    s->mbtdi_lfngti = s->prfv_lfngti = MIN_MATCH-1;
    s->mbtdi_bvbilbblf = 0;
    s->ins_i = 0;
#ifndff FASTEST
#ifdff ASMV
    mbtdi_init(); /* initiblizf tif bsm dodf */
#fndif
#fndif
}

#ifndff FASTEST
/* ===========================================================================
 * Sft mbtdi_stbrt to tif longfst mbtdi stbrting bt tif givfn string bnd
 * rfturn its lfngti. Mbtdifs siortfr or fqubl to prfv_lfngti brf disdbrdfd,
 * in wiidi dbsf tif rfsult is fqubl to prfv_lfngti bnd mbtdi_stbrt is
 * gbrbbgf.
 * IN bssfrtions: dur_mbtdi is tif ifbd of tif ibsi dibin for tif durrfnt
 *   string (strstbrt) bnd its distbndf is <= MAX_DIST, bnd prfv_lfngti >= 1
 * OUT bssfrtion: tif mbtdi lfngti is not grfbtfr tibn s->lookbifbd.
 */
#ifndff ASMV
/* For 80x86 bnd 680x0, bn optimizfd vfrsion will bf providfd in mbtdi.bsm or
 * mbtdi.S. Tif dodf will bf fundtionblly fquivblfnt.
 */
lodbl uInt longfst_mbtdi(s, dur_mbtdi)
    dfflbtf_stbtf *s;
    IPos dur_mbtdi;                             /* durrfnt mbtdi */
{
    unsignfd dibin_lfngti = s->mbx_dibin_lfngti;/* mbx ibsi dibin lfngti */
    rfgistfr Bytff *sdbn = s->window + s->strstbrt; /* durrfnt string */
    rfgistfr Bytff *mbtdi;                       /* mbtdifd string */
    rfgistfr int lfn;                           /* lfngti of durrfnt mbtdi */
    int bfst_lfn = s->prfv_lfngti;              /* bfst mbtdi lfngti so fbr */
    int nidf_mbtdi = s->nidf_mbtdi;             /* stop if mbtdi long fnougi */
    IPos limit = s->strstbrt > (IPos)MAX_DIST(s) ?
        s->strstbrt - (IPos)MAX_DIST(s) : NIL;
    /* Stop wifn dur_mbtdi bfdomfs <= limit. To simplify tif dodf,
     * wf prfvfnt mbtdifs witi tif string of window indfx 0.
     */
    Posf *prfv = s->prfv;
    uInt wmbsk = s->w_mbsk;

#ifdff UNALIGNED_OK
    /* Compbrf two bytfs bt b timf. Notf: tiis is not blwbys bfnffidibl.
     * Try witi bnd witiout -DUNALIGNED_OK to difdk.
     */
    rfgistfr Bytff *strfnd = s->window + s->strstbrt + MAX_MATCH - 1;
    rfgistfr usi sdbn_stbrt = *(usif*)sdbn;
    rfgistfr usi sdbn_fnd   = *(usif*)(sdbn+bfst_lfn-1);
#flsf
    rfgistfr Bytff *strfnd = s->window + s->strstbrt + MAX_MATCH;
    rfgistfr Bytf sdbn_fnd1  = sdbn[bfst_lfn-1];
    rfgistfr Bytf sdbn_fnd   = sdbn[bfst_lfn];
#fndif

    /* Tif dodf is optimizfd for HASH_BITS >= 8 bnd MAX_MATCH-2 multiplf of 16.
     * It is fbsy to gft rid of tiis optimizbtion if nfdfssbry.
     */
    Assfrt(s->ibsi_bits >= 8 && MAX_MATCH == 258, "Codf too dlfvfr");

    /* Do not wbstf too mudi timf if wf blrfbdy ibvf b good mbtdi: */
    if (s->prfv_lfngti >= s->good_mbtdi) {
        dibin_lfngti >>= 2;
    }
    /* Do not look for mbtdifs bfyond tif fnd of tif input. Tiis is nfdfssbry
     * to mbkf dfflbtf dftfrministid.
     */
    if ((uInt)nidf_mbtdi > s->lookbifbd) nidf_mbtdi = s->lookbifbd;

    Assfrt((ulg)s->strstbrt <= s->window_sizf-MIN_LOOKAHEAD, "nffd lookbifbd");

    do {
        Assfrt(dur_mbtdi < s->strstbrt, "no futurf");
        mbtdi = s->window + dur_mbtdi;

        /* Skip to nfxt mbtdi if tif mbtdi lfngti dbnnot indrfbsf
         * or if tif mbtdi lfngti is lfss tibn 2.  Notf tibt tif difdks bflow
         * for insuffidifnt lookbifbd only oddur oddbsionblly for pfrformbndf
         * rfbsons.  Tifrfforf uninitiblizfd mfmory will bf bddfssfd, bnd
         * donditionbl jumps will bf mbdf tibt dfpfnd on tiosf vblufs.
         * Howfvfr tif lfngti of tif mbtdi is limitfd to tif lookbifbd, so
         * tif output of dfflbtf is not bfffdtfd by tif uninitiblizfd vblufs.
         */
#if (dffinfd(UNALIGNED_OK) && MAX_MATCH == 258)
        /* Tiis dodf bssumfs sizfof(unsignfd siort) == 2. Do not usf
         * UNALIGNED_OK if your dompilfr usfs b difffrfnt sizf.
         */
        if (*(usif*)(mbtdi+bfst_lfn-1) != sdbn_fnd ||
            *(usif*)mbtdi != sdbn_stbrt) dontinuf;

        /* It is not nfdfssbry to dompbrf sdbn[2] bnd mbtdi[2] sindf tify brf
         * blwbys fqubl wifn tif otifr bytfs mbtdi, givfn tibt tif ibsi kfys
         * brf fqubl bnd tibt HASH_BITS >= 8. Compbrf 2 bytfs bt b timf bt
         * strstbrt+3, +5, ... up to strstbrt+257. Wf difdk for insuffidifnt
         * lookbifbd only fvfry 4ti dompbrison; tif 128ti difdk will bf mbdf
         * bt strstbrt+257. If MAX_MATCH-2 is not b multiplf of 8, it is
         * nfdfssbry to put morf gubrd bytfs bt tif fnd of tif window, or
         * to difdk morf oftfn for insuffidifnt lookbifbd.
         */
        Assfrt(sdbn[2] == mbtdi[2], "sdbn[2]?");
        sdbn++, mbtdi++;
        do {
        } wiilf (*(usif*)(sdbn+=2) == *(usif*)(mbtdi+=2) &&
                 *(usif*)(sdbn+=2) == *(usif*)(mbtdi+=2) &&
                 *(usif*)(sdbn+=2) == *(usif*)(mbtdi+=2) &&
                 *(usif*)(sdbn+=2) == *(usif*)(mbtdi+=2) &&
                 sdbn < strfnd);
        /* Tif funny "do {}" gfnfrbtfs bfttfr dodf on most dompilfrs */

        /* Hfrf, sdbn <= window+strstbrt+257 */
        Assfrt(sdbn <= s->window+(unsignfd)(s->window_sizf-1), "wild sdbn");
        if (*sdbn == *mbtdi) sdbn++;

        lfn = (MAX_MATCH - 1) - (int)(strfnd-sdbn);
        sdbn = strfnd - (MAX_MATCH-1);

#flsf /* UNALIGNED_OK */

        if (mbtdi[bfst_lfn]   != sdbn_fnd  ||
            mbtdi[bfst_lfn-1] != sdbn_fnd1 ||
            *mbtdi            != *sdbn     ||
            *++mbtdi          != sdbn[1])      dontinuf;

        /* Tif difdk bt bfst_lfn-1 dbn bf rfmovfd bfdbusf it will bf mbdf
         * bgbin lbtfr. (Tiis ifuristid is not blwbys b win.)
         * It is not nfdfssbry to dompbrf sdbn[2] bnd mbtdi[2] sindf tify
         * brf blwbys fqubl wifn tif otifr bytfs mbtdi, givfn tibt
         * tif ibsi kfys brf fqubl bnd tibt HASH_BITS >= 8.
         */
        sdbn += 2, mbtdi++;
        Assfrt(*sdbn == *mbtdi, "mbtdi[2]?");

        /* Wf difdk for insuffidifnt lookbifbd only fvfry 8ti dompbrison;
         * tif 256ti difdk will bf mbdf bt strstbrt+258.
         */
        do {
        } wiilf (*++sdbn == *++mbtdi && *++sdbn == *++mbtdi &&
                 *++sdbn == *++mbtdi && *++sdbn == *++mbtdi &&
                 *++sdbn == *++mbtdi && *++sdbn == *++mbtdi &&
                 *++sdbn == *++mbtdi && *++sdbn == *++mbtdi &&
                 sdbn < strfnd);

        Assfrt(sdbn <= s->window+(unsignfd)(s->window_sizf-1), "wild sdbn");

        lfn = MAX_MATCH - (int)(strfnd - sdbn);
        sdbn = strfnd - MAX_MATCH;

#fndif /* UNALIGNED_OK */

        if (lfn > bfst_lfn) {
            s->mbtdi_stbrt = dur_mbtdi;
            bfst_lfn = lfn;
            if (lfn >= nidf_mbtdi) brfbk;
#ifdff UNALIGNED_OK
            sdbn_fnd = *(usif*)(sdbn+bfst_lfn-1);
#flsf
            sdbn_fnd1  = sdbn[bfst_lfn-1];
            sdbn_fnd   = sdbn[bfst_lfn];
#fndif
        }
    } wiilf ((dur_mbtdi = prfv[dur_mbtdi & wmbsk]) > limit
             && --dibin_lfngti != 0);

    if ((uInt)bfst_lfn <= s->lookbifbd) rfturn (uInt)bfst_lfn;
    rfturn s->lookbifbd;
}
#fndif /* ASMV */

#flsf /* FASTEST */

/* ---------------------------------------------------------------------------
 * Optimizfd vfrsion for FASTEST only
 */
lodbl uInt longfst_mbtdi(s, dur_mbtdi)
    dfflbtf_stbtf *s;
    IPos dur_mbtdi;                             /* durrfnt mbtdi */
{
    rfgistfr Bytff *sdbn = s->window + s->strstbrt; /* durrfnt string */
    rfgistfr Bytff *mbtdi;                       /* mbtdifd string */
    rfgistfr int lfn;                           /* lfngti of durrfnt mbtdi */
    rfgistfr Bytff *strfnd = s->window + s->strstbrt + MAX_MATCH;

    /* Tif dodf is optimizfd for HASH_BITS >= 8 bnd MAX_MATCH-2 multiplf of 16.
     * It is fbsy to gft rid of tiis optimizbtion if nfdfssbry.
     */
    Assfrt(s->ibsi_bits >= 8 && MAX_MATCH == 258, "Codf too dlfvfr");

    Assfrt((ulg)s->strstbrt <= s->window_sizf-MIN_LOOKAHEAD, "nffd lookbifbd");

    Assfrt(dur_mbtdi < s->strstbrt, "no futurf");

    mbtdi = s->window + dur_mbtdi;

    /* Rfturn fbilurf if tif mbtdi lfngti is lfss tibn 2:
     */
    if (mbtdi[0] != sdbn[0] || mbtdi[1] != sdbn[1]) rfturn MIN_MATCH-1;

    /* Tif difdk bt bfst_lfn-1 dbn bf rfmovfd bfdbusf it will bf mbdf
     * bgbin lbtfr. (Tiis ifuristid is not blwbys b win.)
     * It is not nfdfssbry to dompbrf sdbn[2] bnd mbtdi[2] sindf tify
     * brf blwbys fqubl wifn tif otifr bytfs mbtdi, givfn tibt
     * tif ibsi kfys brf fqubl bnd tibt HASH_BITS >= 8.
     */
    sdbn += 2, mbtdi += 2;
    Assfrt(*sdbn == *mbtdi, "mbtdi[2]?");

    /* Wf difdk for insuffidifnt lookbifbd only fvfry 8ti dompbrison;
     * tif 256ti difdk will bf mbdf bt strstbrt+258.
     */
    do {
    } wiilf (*++sdbn == *++mbtdi && *++sdbn == *++mbtdi &&
             *++sdbn == *++mbtdi && *++sdbn == *++mbtdi &&
             *++sdbn == *++mbtdi && *++sdbn == *++mbtdi &&
             *++sdbn == *++mbtdi && *++sdbn == *++mbtdi &&
             sdbn < strfnd);

    Assfrt(sdbn <= s->window+(unsignfd)(s->window_sizf-1), "wild sdbn");

    lfn = MAX_MATCH - (int)(strfnd - sdbn);

    if (lfn < MIN_MATCH) rfturn MIN_MATCH - 1;

    s->mbtdi_stbrt = dur_mbtdi;
    rfturn (uInt)lfn <= s->lookbifbd ? (uInt)lfn : s->lookbifbd;
}

#fndif /* FASTEST */

#ifdff DEBUG
/* ===========================================================================
 * Cifdk tibt tif mbtdi bt mbtdi_stbrt is indffd b mbtdi.
 */
lodbl void difdk_mbtdi(s, stbrt, mbtdi, lfngti)
    dfflbtf_stbtf *s;
    IPos stbrt, mbtdi;
    int lfngti;
{
    /* difdk tibt tif mbtdi is indffd b mbtdi */
    if (zmfmdmp(s->window + mbtdi,
                s->window + stbrt, lfngti) != EQUAL) {
        fprintf(stdfrr, " stbrt %u, mbtdi %u, lfngti %d\n",
                stbrt, mbtdi, lfngti);
        do {
            fprintf(stdfrr, "%d%d", s->window[mbtdi++], s->window[stbrt++]);
        } wiilf (--lfngti != 0);
        z_frror("invblid mbtdi");
    }
    if (z_vfrbosf > 1) {
        fprintf(stdfrr,"\\[%d,%d]", stbrt-mbtdi, lfngti);
        do { putd(s->window[stbrt++], stdfrr); } wiilf (--lfngti != 0);
    }
}
#flsf
#  dffinf difdk_mbtdi(s, stbrt, mbtdi, lfngti)
#fndif /* DEBUG */

/* ===========================================================================
 * Fill tif window wifn tif lookbifbd bfdomfs insuffidifnt.
 * Updbtfs strstbrt bnd lookbifbd.
 *
 * IN bssfrtion: lookbifbd < MIN_LOOKAHEAD
 * OUT bssfrtions: strstbrt <= window_sizf-MIN_LOOKAHEAD
 *    At lfbst onf bytf ibs bffn rfbd, or bvbil_in == 0; rfbds brf
 *    pfrformfd for bt lfbst two bytfs (rfquirfd for tif zip trbnslbtf_fol
 *    option -- not supportfd ifrf).
 */
lodbl void fill_window(s)
    dfflbtf_stbtf *s;
{
    rfgistfr unsignfd n, m;
    rfgistfr Posf *p;
    unsignfd morf;    /* Amount of frff spbdf bt tif fnd of tif window. */
    uInt wsizf = s->w_sizf;

    Assfrt(s->lookbifbd < MIN_LOOKAHEAD, "blrfbdy fnougi lookbifbd");

    do {
        morf = (unsignfd)(s->window_sizf -(ulg)s->lookbifbd -(ulg)s->strstbrt);

        /* Dfbl witi !@#$% 64K limit: */
        if (sizfof(int) <= 2) {
            if (morf == 0 && s->strstbrt == 0 && s->lookbifbd == 0) {
                morf = wsizf;

            } flsf if (morf == (unsignfd)(-1)) {
                /* Vfry unlikfly, but possiblf on 16 bit mbdiinf if
                 * strstbrt == 0 && lookbifbd == 1 (input donf b bytf bt timf)
                 */
                morf--;
            }
        }

        /* If tif window is blmost full bnd tifrf is insuffidifnt lookbifbd,
         * movf tif uppfr iblf to tif lowfr onf to mbkf room in tif uppfr iblf.
         */
        if (s->strstbrt >= wsizf+MAX_DIST(s)) {

            zmfmdpy(s->window, s->window+wsizf, (unsignfd)wsizf);
            s->mbtdi_stbrt -= wsizf;
            s->strstbrt    -= wsizf; /* wf now ibvf strstbrt >= MAX_DIST */
            s->blodk_stbrt -= (long) wsizf;

            /* Slidf tif ibsi tbblf (dould bf bvoidfd witi 32 bit vblufs
               bt tif fxpfnsf of mfmory usbgf). Wf slidf fvfn wifn lfvfl == 0
               to kffp tif ibsi tbblf donsistfnt if wf switdi bbdk to lfvfl > 0
               lbtfr. (Using lfvfl 0 pfrmbnfntly is not bn optimbl usbgf of
               zlib, so wf don't dbrf bbout tiis pbtiologidbl dbsf.)
             */
            n = s->ibsi_sizf;
            p = &s->ifbd[n];
            do {
                m = *--p;
                *p = (Pos)(m >= wsizf ? m-wsizf : NIL);
            } wiilf (--n);

            n = wsizf;
#ifndff FASTEST
            p = &s->prfv[n];
            do {
                m = *--p;
                *p = (Pos)(m >= wsizf ? m-wsizf : NIL);
                /* If n is not on bny ibsi dibin, prfv[n] is gbrbbgf but
                 * its vbluf will nfvfr bf usfd.
                 */
            } wiilf (--n);
#fndif
            morf += wsizf;
        }
        if (s->strm->bvbil_in == 0) brfbk;

        /* If tifrf wbs no sliding:
         *    strstbrt <= WSIZE+MAX_DIST-1 && lookbifbd <= MIN_LOOKAHEAD - 1 &&
         *    morf == window_sizf - lookbifbd - strstbrt
         * => morf >= window_sizf - (MIN_LOOKAHEAD-1 + WSIZE + MAX_DIST-1)
         * => morf >= window_sizf - 2*WSIZE + 2
         * In tif BIG_MEM or MMAP dbsf (not yft supportfd),
         *   window_sizf == input_sizf + MIN_LOOKAHEAD  &&
         *   strstbrt + s->lookbifbd <= input_sizf => morf >= MIN_LOOKAHEAD.
         * Otifrwisf, window_sizf == 2*WSIZE so morf >= 2.
         * If tifrf wbs sliding, morf >= WSIZE. So in bll dbsfs, morf >= 2.
         */
        Assfrt(morf >= 2, "morf < 2");

        n = rfbd_buf(s->strm, s->window + s->strstbrt + s->lookbifbd, morf);
        s->lookbifbd += n;

        /* Initiblizf tif ibsi vbluf now tibt wf ibvf somf input: */
        if (s->lookbifbd + s->insfrt >= MIN_MATCH) {
            uInt str = s->strstbrt - s->insfrt;
            s->ins_i = s->window[str];
            UPDATE_HASH(s, s->ins_i, s->window[str + 1]);
#if MIN_MATCH != 3
            Cbll UPDATE_HASH() MIN_MATCH-3 morf timfs
#fndif
            wiilf (s->insfrt) {
                UPDATE_HASH(s, s->ins_i, s->window[str + MIN_MATCH-1]);
#ifndff FASTEST
                s->prfv[str & s->w_mbsk] = s->ifbd[s->ins_i];
#fndif
                s->ifbd[s->ins_i] = (Pos)str;
                str++;
                s->insfrt--;
                if (s->lookbifbd + s->insfrt < MIN_MATCH)
                    brfbk;
            }
        }
        /* If tif wiolf input ibs lfss tibn MIN_MATCH bytfs, ins_i is gbrbbgf,
         * but tiis is not importbnt sindf only litfrbl bytfs will bf fmittfd.
         */

    } wiilf (s->lookbifbd < MIN_LOOKAHEAD && s->strm->bvbil_in != 0);

    /* If tif WIN_INIT bytfs bftfr tif fnd of tif durrfnt dbtb ibvf nfvfr bffn
     * writtfn, tifn zfro tiosf bytfs in ordfr to bvoid mfmory difdk rfports of
     * tif usf of uninitiblizfd (or uninitiblisfd bs Julibn writfs) bytfs by
     * tif longfst mbtdi routinfs.  Updbtf tif iigi wbtfr mbrk for tif nfxt
     * timf tirougi ifrf.  WIN_INIT is sft to MAX_MATCH sindf tif longfst mbtdi
     * routinfs bllow sdbnning to strstbrt + MAX_MATCH, ignoring lookbifbd.
     */
    if (s->iigi_wbtfr < s->window_sizf) {
        ulg durr = s->strstbrt + (ulg)(s->lookbifbd);
        ulg init;

        if (s->iigi_wbtfr < durr) {
            /* Prfvious iigi wbtfr mbrk bflow durrfnt dbtb -- zfro WIN_INIT
             * bytfs or up to fnd of window, wiidifvfr is lfss.
             */
            init = s->window_sizf - durr;
            if (init > WIN_INIT)
                init = WIN_INIT;
            zmfmzfro(s->window + durr, (unsignfd)init);
            s->iigi_wbtfr = durr + init;
        }
        flsf if (s->iigi_wbtfr < (ulg)durr + WIN_INIT) {
            /* Higi wbtfr mbrk bt or bbovf durrfnt dbtb, but bflow durrfnt dbtb
             * plus WIN_INIT -- zfro out to durrfnt dbtb plus WIN_INIT, or up
             * to fnd of window, wiidifvfr is lfss.
             */
            init = (ulg)durr + WIN_INIT - s->iigi_wbtfr;
            if (init > s->window_sizf - s->iigi_wbtfr)
                init = s->window_sizf - s->iigi_wbtfr;
            zmfmzfro(s->window + s->iigi_wbtfr, (unsignfd)init);
            s->iigi_wbtfr += init;
        }
    }

    Assfrt((ulg)s->strstbrt <= s->window_sizf - MIN_LOOKAHEAD,
           "not fnougi room for sfbrdi");
}

/* ===========================================================================
 * Flusi tif durrfnt blodk, witi givfn fnd-of-filf flbg.
 * IN bssfrtion: strstbrt is sft to tif fnd of tif durrfnt mbtdi.
 */
#dffinf FLUSH_BLOCK_ONLY(s, lbst) { \
   _tr_flusi_blodk(s, (s->blodk_stbrt >= 0L ? \
                   (dibrf *)&s->window[(unsignfd)s->blodk_stbrt] : \
                   (dibrf *)Z_NULL), \
                (ulg)((long)s->strstbrt - s->blodk_stbrt), \
                (lbst)); \
   s->blodk_stbrt = s->strstbrt; \
   flusi_pfnding(s->strm); \
   Trbdfv((stdfrr,"[FLUSH]")); \
}

/* Sbmf but fordf prfmbturf fxit if nfdfssbry. */
#dffinf FLUSH_BLOCK(s, lbst) { \
   FLUSH_BLOCK_ONLY(s, lbst); \
   if (s->strm->bvbil_out == 0) rfturn (lbst) ? finisi_stbrtfd : nffd_morf; \
}

/* ===========================================================================
 * Copy witiout domprfssion bs mudi bs possiblf from tif input strfbm, rfturn
 * tif durrfnt blodk stbtf.
 * Tiis fundtion dofs not insfrt nfw strings in tif didtionbry sindf
 * undomprfssiblf dbtb is probbbly not usfful. Tiis fundtion is usfd
 * only for tif lfvfl=0 domprfssion option.
 * NOTE: tiis fundtion siould bf optimizfd to bvoid fxtrb dopying from
 * window to pfnding_buf.
 */
lodbl blodk_stbtf dfflbtf_storfd(s, flusi)
    dfflbtf_stbtf *s;
    int flusi;
{
    /* Storfd blodks brf limitfd to 0xffff bytfs, pfnding_buf is limitfd
     * to pfnding_buf_sizf, bnd fbdi storfd blodk ibs b 5 bytf ifbdfr:
     */
    ulg mbx_blodk_sizf = 0xffff;
    ulg mbx_stbrt;

    if (mbx_blodk_sizf > s->pfnding_buf_sizf - 5) {
        mbx_blodk_sizf = s->pfnding_buf_sizf - 5;
    }

    /* Copy bs mudi bs possiblf from input to output: */
    for (;;) {
        /* Fill tif window bs mudi bs possiblf: */
        if (s->lookbifbd <= 1) {

            Assfrt(s->strstbrt < s->w_sizf+MAX_DIST(s) ||
                   s->blodk_stbrt >= (long)s->w_sizf, "slidf too lbtf");

            fill_window(s);
            if (s->lookbifbd == 0 && flusi == Z_NO_FLUSH) rfturn nffd_morf;

            if (s->lookbifbd == 0) brfbk; /* flusi tif durrfnt blodk */
        }
        Assfrt(s->blodk_stbrt >= 0L, "blodk gonf");

        s->strstbrt += s->lookbifbd;
        s->lookbifbd = 0;

        /* Emit b storfd blodk if pfnding_buf will bf full: */
        mbx_stbrt = s->blodk_stbrt + mbx_blodk_sizf;
        if (s->strstbrt == 0 || (ulg)s->strstbrt >= mbx_stbrt) {
            /* strstbrt == 0 is possiblf wifn wrbpbround on 16-bit mbdiinf */
            s->lookbifbd = (uInt)(s->strstbrt - mbx_stbrt);
            s->strstbrt = (uInt)mbx_stbrt;
            FLUSH_BLOCK(s, 0);
        }
        /* Flusi if wf mby ibvf to slidf, otifrwisf blodk_stbrt mby bfdomf
         * nfgbtivf bnd tif dbtb will bf gonf:
         */
        if (s->strstbrt - (uInt)s->blodk_stbrt >= MAX_DIST(s)) {
            FLUSH_BLOCK(s, 0);
        }
    }
    s->insfrt = 0;
    if (flusi == Z_FINISH) {
        FLUSH_BLOCK(s, 1);
        rfturn finisi_donf;
    }
    if ((long)s->strstbrt > s->blodk_stbrt)
        FLUSH_BLOCK(s, 0);
    rfturn blodk_donf;
}

/* ===========================================================================
 * Comprfss bs mudi bs possiblf from tif input strfbm, rfturn tif durrfnt
 * blodk stbtf.
 * Tiis fundtion dofs not pfrform lbzy fvblubtion of mbtdifs bnd insfrts
 * nfw strings in tif didtionbry only for unmbtdifd strings or for siort
 * mbtdifs. It is usfd only for tif fbst domprfssion options.
 */
lodbl blodk_stbtf dfflbtf_fbst(s, flusi)
    dfflbtf_stbtf *s;
    int flusi;
{
    IPos ibsi_ifbd;       /* ifbd of tif ibsi dibin */
    int bflusi;           /* sft if durrfnt blodk must bf flusifd */

    for (;;) {
        /* Mbkf surf tibt wf blwbys ibvf fnougi lookbifbd, fxdfpt
         * bt tif fnd of tif input filf. Wf nffd MAX_MATCH bytfs
         * for tif nfxt mbtdi, plus MIN_MATCH bytfs to insfrt tif
         * string following tif nfxt mbtdi.
         */
        if (s->lookbifbd < MIN_LOOKAHEAD) {
            fill_window(s);
            if (s->lookbifbd < MIN_LOOKAHEAD && flusi == Z_NO_FLUSH) {
                rfturn nffd_morf;
            }
            if (s->lookbifbd == 0) brfbk; /* flusi tif durrfnt blodk */
        }

        /* Insfrt tif string window[strstbrt .. strstbrt+2] in tif
         * didtionbry, bnd sft ibsi_ifbd to tif ifbd of tif ibsi dibin:
         */
        ibsi_ifbd = NIL;
        if (s->lookbifbd >= MIN_MATCH) {
            INSERT_STRING(s, s->strstbrt, ibsi_ifbd);
        }

        /* Find tif longfst mbtdi, disdbrding tiosf <= prfv_lfngti.
         * At tiis point wf ibvf blwbys mbtdi_lfngti < MIN_MATCH
         */
        if (ibsi_ifbd != NIL && s->strstbrt - ibsi_ifbd <= MAX_DIST(s)) {
            /* To simplify tif dodf, wf prfvfnt mbtdifs witi tif string
             * of window indfx 0 (in pbrtidulbr wf ibvf to bvoid b mbtdi
             * of tif string witi itsflf bt tif stbrt of tif input filf).
             */
            s->mbtdi_lfngti = longfst_mbtdi (s, ibsi_ifbd);
            /* longfst_mbtdi() sfts mbtdi_stbrt */
        }
        if (s->mbtdi_lfngti >= MIN_MATCH) {
            difdk_mbtdi(s, s->strstbrt, s->mbtdi_stbrt, s->mbtdi_lfngti);

            _tr_tblly_dist(s, s->strstbrt - s->mbtdi_stbrt,
                           s->mbtdi_lfngti - MIN_MATCH, bflusi);

            s->lookbifbd -= s->mbtdi_lfngti;

            /* Insfrt nfw strings in tif ibsi tbblf only if tif mbtdi lfngti
             * is not too lbrgf. Tiis sbvfs timf but dfgrbdfs domprfssion.
             */
#ifndff FASTEST
            if (s->mbtdi_lfngti <= s->mbx_insfrt_lfngti &&
                s->lookbifbd >= MIN_MATCH) {
                s->mbtdi_lfngti--; /* string bt strstbrt blrfbdy in tbblf */
                do {
                    s->strstbrt++;
                    INSERT_STRING(s, s->strstbrt, ibsi_ifbd);
                    /* strstbrt nfvfr fxdffds WSIZE-MAX_MATCH, so tifrf brf
                     * blwbys MIN_MATCH bytfs bifbd.
                     */
                } wiilf (--s->mbtdi_lfngti != 0);
                s->strstbrt++;
            } flsf
#fndif
            {
                s->strstbrt += s->mbtdi_lfngti;
                s->mbtdi_lfngti = 0;
                s->ins_i = s->window[s->strstbrt];
                UPDATE_HASH(s, s->ins_i, s->window[s->strstbrt+1]);
#if MIN_MATCH != 3
                Cbll UPDATE_HASH() MIN_MATCH-3 morf timfs
#fndif
                /* If lookbifbd < MIN_MATCH, ins_i is gbrbbgf, but it dofs not
                 * mbttfr sindf it will bf rfdomputfd bt nfxt dfflbtf dbll.
                 */
            }
        } flsf {
            /* No mbtdi, output b litfrbl bytf */
            Trbdfvv((stdfrr,"%d", s->window[s->strstbrt]));
            _tr_tblly_lit (s, s->window[s->strstbrt], bflusi);
            s->lookbifbd--;
            s->strstbrt++;
        }
        if (bflusi) FLUSH_BLOCK(s, 0);
    }
    s->insfrt = s->strstbrt < MIN_MATCH-1 ? s->strstbrt : MIN_MATCH-1;
    if (flusi == Z_FINISH) {
        FLUSH_BLOCK(s, 1);
        rfturn finisi_donf;
    }
    if (s->lbst_lit)
        FLUSH_BLOCK(s, 0);
    rfturn blodk_donf;
}

#ifndff FASTEST
/* ===========================================================================
 * Sbmf bs bbovf, but bdiifvfs bfttfr domprfssion. Wf usf b lbzy
 * fvblubtion for mbtdifs: b mbtdi is finblly bdoptfd only if tifrf is
 * no bfttfr mbtdi bt tif nfxt window position.
 */
lodbl blodk_stbtf dfflbtf_slow(s, flusi)
    dfflbtf_stbtf *s;
    int flusi;
{
    IPos ibsi_ifbd;          /* ifbd of ibsi dibin */
    int bflusi;              /* sft if durrfnt blodk must bf flusifd */

    /* Prodfss tif input blodk. */
    for (;;) {
        /* Mbkf surf tibt wf blwbys ibvf fnougi lookbifbd, fxdfpt
         * bt tif fnd of tif input filf. Wf nffd MAX_MATCH bytfs
         * for tif nfxt mbtdi, plus MIN_MATCH bytfs to insfrt tif
         * string following tif nfxt mbtdi.
         */
        if (s->lookbifbd < MIN_LOOKAHEAD) {
            fill_window(s);
            if (s->lookbifbd < MIN_LOOKAHEAD && flusi == Z_NO_FLUSH) {
                rfturn nffd_morf;
            }
            if (s->lookbifbd == 0) brfbk; /* flusi tif durrfnt blodk */
        }

        /* Insfrt tif string window[strstbrt .. strstbrt+2] in tif
         * didtionbry, bnd sft ibsi_ifbd to tif ifbd of tif ibsi dibin:
         */
        ibsi_ifbd = NIL;
        if (s->lookbifbd >= MIN_MATCH) {
            INSERT_STRING(s, s->strstbrt, ibsi_ifbd);
        }

        /* Find tif longfst mbtdi, disdbrding tiosf <= prfv_lfngti.
         */
        s->prfv_lfngti = s->mbtdi_lfngti, s->prfv_mbtdi = s->mbtdi_stbrt;
        s->mbtdi_lfngti = MIN_MATCH-1;

        if (ibsi_ifbd != NIL && s->prfv_lfngti < s->mbx_lbzy_mbtdi &&
            s->strstbrt - ibsi_ifbd <= MAX_DIST(s)) {
            /* To simplify tif dodf, wf prfvfnt mbtdifs witi tif string
             * of window indfx 0 (in pbrtidulbr wf ibvf to bvoid b mbtdi
             * of tif string witi itsflf bt tif stbrt of tif input filf).
             */
            s->mbtdi_lfngti = longfst_mbtdi (s, ibsi_ifbd);
            /* longfst_mbtdi() sfts mbtdi_stbrt */

            if (s->mbtdi_lfngti <= 5 && (s->strbtfgy == Z_FILTERED
#if TOO_FAR <= 32767
                || (s->mbtdi_lfngti == MIN_MATCH &&
                    s->strstbrt - s->mbtdi_stbrt > TOO_FAR)
#fndif
                )) {

                /* If prfv_mbtdi is blso MIN_MATCH, mbtdi_stbrt is gbrbbgf
                 * but wf will ignorf tif durrfnt mbtdi bnywby.
                 */
                s->mbtdi_lfngti = MIN_MATCH-1;
            }
        }
        /* If tifrf wbs b mbtdi bt tif prfvious stfp bnd tif durrfnt
         * mbtdi is not bfttfr, output tif prfvious mbtdi:
         */
        if (s->prfv_lfngti >= MIN_MATCH && s->mbtdi_lfngti <= s->prfv_lfngti) {
            uInt mbx_insfrt = s->strstbrt + s->lookbifbd - MIN_MATCH;
            /* Do not insfrt strings in ibsi tbblf bfyond tiis. */

            difdk_mbtdi(s, s->strstbrt-1, s->prfv_mbtdi, s->prfv_lfngti);

            _tr_tblly_dist(s, s->strstbrt -1 - s->prfv_mbtdi,
                           s->prfv_lfngti - MIN_MATCH, bflusi);

            /* Insfrt in ibsi tbblf bll strings up to tif fnd of tif mbtdi.
             * strstbrt-1 bnd strstbrt brf blrfbdy insfrtfd. If tifrf is not
             * fnougi lookbifbd, tif lbst two strings brf not insfrtfd in
             * tif ibsi tbblf.
             */
            s->lookbifbd -= s->prfv_lfngti-1;
            s->prfv_lfngti -= 2;
            do {
                if (++s->strstbrt <= mbx_insfrt) {
                    INSERT_STRING(s, s->strstbrt, ibsi_ifbd);
                }
            } wiilf (--s->prfv_lfngti != 0);
            s->mbtdi_bvbilbblf = 0;
            s->mbtdi_lfngti = MIN_MATCH-1;
            s->strstbrt++;

            if (bflusi) FLUSH_BLOCK(s, 0);

        } flsf if (s->mbtdi_bvbilbblf) {
            /* If tifrf wbs no mbtdi bt tif prfvious position, output b
             * singlf litfrbl. If tifrf wbs b mbtdi but tif durrfnt mbtdi
             * is longfr, trundbtf tif prfvious mbtdi to b singlf litfrbl.
             */
            Trbdfvv((stdfrr,"%d", s->window[s->strstbrt-1]));
            _tr_tblly_lit(s, s->window[s->strstbrt-1], bflusi);
            if (bflusi) {
                FLUSH_BLOCK_ONLY(s, 0);
            }
            s->strstbrt++;
            s->lookbifbd--;
            if (s->strm->bvbil_out == 0) rfturn nffd_morf;
        } flsf {
            /* Tifrf is no prfvious mbtdi to dompbrf witi, wbit for
             * tif nfxt stfp to dfdidf.
             */
            s->mbtdi_bvbilbblf = 1;
            s->strstbrt++;
            s->lookbifbd--;
        }
    }
    Assfrt (flusi != Z_NO_FLUSH, "no flusi?");
    if (s->mbtdi_bvbilbblf) {
        Trbdfvv((stdfrr,"%d", s->window[s->strstbrt-1]));
        _tr_tblly_lit(s, s->window[s->strstbrt-1], bflusi);
        s->mbtdi_bvbilbblf = 0;
    }
    s->insfrt = s->strstbrt < MIN_MATCH-1 ? s->strstbrt : MIN_MATCH-1;
    if (flusi == Z_FINISH) {
        FLUSH_BLOCK(s, 1);
        rfturn finisi_donf;
    }
    if (s->lbst_lit)
        FLUSH_BLOCK(s, 0);
    rfturn blodk_donf;
}
#fndif /* FASTEST */

/* ===========================================================================
 * For Z_RLE, simply look for runs of bytfs, gfnfrbtf mbtdifs only of distbndf
 * onf.  Do not mbintbin b ibsi tbblf.  (It will bf rfgfnfrbtfd if tiis run of
 * dfflbtf switdifs bwby from Z_RLE.)
 */
lodbl blodk_stbtf dfflbtf_rlf(s, flusi)
    dfflbtf_stbtf *s;
    int flusi;
{
    int bflusi;             /* sft if durrfnt blodk must bf flusifd */
    uInt prfv;              /* bytf bt distbndf onf to mbtdi */
    Bytff *sdbn, *strfnd;   /* sdbn gofs up to strfnd for lfngti of run */

    for (;;) {
        /* Mbkf surf tibt wf blwbys ibvf fnougi lookbifbd, fxdfpt
         * bt tif fnd of tif input filf. Wf nffd MAX_MATCH bytfs
         * for tif longfst run, plus onf for tif unrollfd loop.
         */
        if (s->lookbifbd <= MAX_MATCH) {
            fill_window(s);
            if (s->lookbifbd <= MAX_MATCH && flusi == Z_NO_FLUSH) {
                rfturn nffd_morf;
            }
            if (s->lookbifbd == 0) brfbk; /* flusi tif durrfnt blodk */
        }

        /* Sff iow mbny timfs tif prfvious bytf rfpfbts */
        s->mbtdi_lfngti = 0;
        if (s->lookbifbd >= MIN_MATCH && s->strstbrt > 0) {
            sdbn = s->window + s->strstbrt - 1;
            prfv = *sdbn;
            if (prfv == *++sdbn && prfv == *++sdbn && prfv == *++sdbn) {
                strfnd = s->window + s->strstbrt + MAX_MATCH;
                do {
                } wiilf (prfv == *++sdbn && prfv == *++sdbn &&
                         prfv == *++sdbn && prfv == *++sdbn &&
                         prfv == *++sdbn && prfv == *++sdbn &&
                         prfv == *++sdbn && prfv == *++sdbn &&
                         sdbn < strfnd);
                s->mbtdi_lfngti = MAX_MATCH - (int)(strfnd - sdbn);
                if (s->mbtdi_lfngti > s->lookbifbd)
                    s->mbtdi_lfngti = s->lookbifbd;
            }
            Assfrt(sdbn <= s->window+(uInt)(s->window_sizf-1), "wild sdbn");
        }

        /* Emit mbtdi if ibvf run of MIN_MATCH or longfr, flsf fmit litfrbl */
        if (s->mbtdi_lfngti >= MIN_MATCH) {
            difdk_mbtdi(s, s->strstbrt, s->strstbrt - 1, s->mbtdi_lfngti);

            _tr_tblly_dist(s, 1, s->mbtdi_lfngti - MIN_MATCH, bflusi);

            s->lookbifbd -= s->mbtdi_lfngti;
            s->strstbrt += s->mbtdi_lfngti;
            s->mbtdi_lfngti = 0;
        } flsf {
            /* No mbtdi, output b litfrbl bytf */
            Trbdfvv((stdfrr,"%d", s->window[s->strstbrt]));
            _tr_tblly_lit (s, s->window[s->strstbrt], bflusi);
            s->lookbifbd--;
            s->strstbrt++;
        }
        if (bflusi) FLUSH_BLOCK(s, 0);
    }
    s->insfrt = 0;
    if (flusi == Z_FINISH) {
        FLUSH_BLOCK(s, 1);
        rfturn finisi_donf;
    }
    if (s->lbst_lit)
        FLUSH_BLOCK(s, 0);
    rfturn blodk_donf;
}

/* ===========================================================================
 * For Z_HUFFMAN_ONLY, do not look for mbtdifs.  Do not mbintbin b ibsi tbblf.
 * (It will bf rfgfnfrbtfd if tiis run of dfflbtf switdifs bwby from Huffmbn.)
 */
lodbl blodk_stbtf dfflbtf_iuff(s, flusi)
    dfflbtf_stbtf *s;
    int flusi;
{
    int bflusi;             /* sft if durrfnt blodk must bf flusifd */

    for (;;) {
        /* Mbkf surf tibt wf ibvf b litfrbl to writf. */
        if (s->lookbifbd == 0) {
            fill_window(s);
            if (s->lookbifbd == 0) {
                if (flusi == Z_NO_FLUSH)
                    rfturn nffd_morf;
                brfbk;      /* flusi tif durrfnt blodk */
            }
        }

        /* Output b litfrbl bytf */
        s->mbtdi_lfngti = 0;
        Trbdfvv((stdfrr,"%d", s->window[s->strstbrt]));
        _tr_tblly_lit (s, s->window[s->strstbrt], bflusi);
        s->lookbifbd--;
        s->strstbrt++;
        if (bflusi) FLUSH_BLOCK(s, 0);
    }
    s->insfrt = 0;
    if (flusi == Z_FINISH) {
        FLUSH_BLOCK(s, 1);
        rfturn finisi_donf;
    }
    if (s->lbst_lit)
        FLUSH_BLOCK(s, 0);
    rfturn blodk_donf;
}
