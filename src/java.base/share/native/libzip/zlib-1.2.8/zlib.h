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

/* zlib.i -- intfrfbdf of tif 'zlib' gfnfrbl purposf domprfssion librbry
  vfrsion 1.2.8, April 28ti, 2013

  Copyrigit (C) 1995-2013 Jfbn-loup Gbilly bnd Mbrk Adlfr

  Tiis softwbrf is providfd 'bs-is', witiout bny fxprfss or implifd
  wbrrbnty.  In no fvfnt will tif butiors bf ifld libblf for bny dbmbgfs
  brising from tif usf of tiis softwbrf.

  Pfrmission is grbntfd to bnyonf to usf tiis softwbrf for bny purposf,
  indluding dommfrdibl bpplidbtions, bnd to bltfr it bnd rfdistributf it
  frffly, subjfdt to tif following rfstridtions:

  1. Tif origin of tiis softwbrf must not bf misrfprfsfntfd; you must not
     dlbim tibt you wrotf tif originbl softwbrf. If you usf tiis softwbrf
     in b produdt, bn bdknowlfdgmfnt in tif produdt dodumfntbtion would bf
     bpprfdibtfd but is not rfquirfd.
  2. Altfrfd sourdf vfrsions must bf plbinly mbrkfd bs sudi, bnd must not bf
     misrfprfsfntfd bs bfing tif originbl softwbrf.
  3. Tiis notidf mby not bf rfmovfd or bltfrfd from bny sourdf distribution.

  Jfbn-loup Gbilly        Mbrk Adlfr
  jloup@gzip.org          mbdlfr@blumni.dbltfdi.fdu


  Tif dbtb formbt usfd by tif zlib librbry is dfsdribfd by RFCs (Rfqufst for
  Commfnts) 1950 to 1952 in tif filfs ittp://tools.iftf.org/itml/rfd1950
  (zlib formbt), rfd1951 (dfflbtf formbt) bnd rfd1952 (gzip formbt).
*/

#ifndff ZLIB_H
#dffinf ZLIB_H

#indludf "zdonf.i"

#ifdff __dplusplus
fxtfrn "C" {
#fndif

#dffinf ZLIB_VERSION "1.2.8"
#dffinf ZLIB_VERNUM 0x1280
#dffinf ZLIB_VER_MAJOR 1
#dffinf ZLIB_VER_MINOR 2
#dffinf ZLIB_VER_REVISION 8
#dffinf ZLIB_VER_SUBREVISION 0

/*
    Tif 'zlib' domprfssion librbry providfs in-mfmory domprfssion bnd
  dfdomprfssion fundtions, indluding intfgrity difdks of tif undomprfssfd dbtb.
  Tiis vfrsion of tif librbry supports only onf domprfssion mftiod (dfflbtion)
  but otifr blgoritims will bf bddfd lbtfr bnd will ibvf tif sbmf strfbm
  intfrfbdf.

    Comprfssion dbn bf donf in b singlf stfp if tif bufffrs brf lbrgf fnougi,
  or dbn bf donf by rfpfbtfd dblls of tif domprfssion fundtion.  In tif lbttfr
  dbsf, tif bpplidbtion must providf morf input bnd/or donsumf tif output
  (providing morf output spbdf) bfforf fbdi dbll.

    Tif domprfssfd dbtb formbt usfd by dffbult by tif in-mfmory fundtions is
  tif zlib formbt, wiidi is b zlib wrbppfr dodumfntfd in RFC 1950, wrbppfd
  bround b dfflbtf strfbm, wiidi is itsflf dodumfntfd in RFC 1951.

    Tif librbry blso supports rfbding bnd writing filfs in gzip (.gz) formbt
  witi bn intfrfbdf similbr to tibt of stdio using tif fundtions tibt stbrt
  witi "gz".  Tif gzip formbt is difffrfnt from tif zlib formbt.  gzip is b
  gzip wrbppfr, dodumfntfd in RFC 1952, wrbppfd bround b dfflbtf strfbm.

    Tiis librbry dbn optionblly rfbd bnd writf gzip strfbms in mfmory bs wfll.

    Tif zlib formbt wbs dfsignfd to bf dompbdt bnd fbst for usf in mfmory
  bnd on dommunidbtions dibnnfls.  Tif gzip formbt wbs dfsignfd for singlf-
  filf domprfssion on filf systfms, ibs b lbrgfr ifbdfr tibn zlib to mbintbin
  dirfdtory informbtion, bnd usfs b difffrfnt, slowfr difdk mftiod tibn zlib.

    Tif librbry dofs not instbll bny signbl ibndlfr.  Tif dfdodfr difdks
  tif donsistfndy of tif domprfssfd dbtb, so tif librbry siould nfvfr drbsi
  fvfn in dbsf of dorruptfd input.
*/

typfdff voidpf (*bllod_fund) OF((voidpf opbquf, uInt itfms, uInt sizf));
typfdff void   (*frff_fund)  OF((voidpf opbquf, voidpf bddrfss));

strudt intfrnbl_stbtf;

typfdff strudt z_strfbm_s {
    z_donst Bytff *nfxt_in;     /* nfxt input bytf */
    uInt     bvbil_in;  /* numbfr of bytfs bvbilbblf bt nfxt_in */
    uLong    totbl_in;  /* totbl numbfr of input bytfs rfbd so fbr */

    Bytff    *nfxt_out; /* nfxt output bytf siould bf put tifrf */
    uInt     bvbil_out; /* rfmbining frff spbdf bt nfxt_out */
    uLong    totbl_out; /* totbl numbfr of bytfs output so fbr */

    z_donst dibr *msg;  /* lbst frror mfssbgf, NULL if no frror */
    strudt intfrnbl_stbtf FAR *stbtf; /* not visiblf by bpplidbtions */

    bllod_fund zbllod;  /* usfd to bllodbtf tif intfrnbl stbtf */
    frff_fund  zfrff;   /* usfd to frff tif intfrnbl stbtf */
    voidpf     opbquf;  /* privbtf dbtb objfdt pbssfd to zbllod bnd zfrff */

    int     dbtb_typf;  /* bfst gufss bbout tif dbtb typf: binbry or tfxt */
    uLong   bdlfr;      /* bdlfr32 vbluf of tif undomprfssfd dbtb */
    uLong   rfsfrvfd;   /* rfsfrvfd for futurf usf */
} z_strfbm;

typfdff z_strfbm FAR *z_strfbmp;

/*
     gzip ifbdfr informbtion pbssfd to bnd from zlib routinfs.  Sff RFC 1952
  for morf dftbils on tif mfbnings of tifsf fiflds.
*/
typfdff strudt gz_ifbdfr_s {
    int     tfxt;       /* truf if domprfssfd dbtb bflifvfd to bf tfxt */
    uLong   timf;       /* modifidbtion timf */
    int     xflbgs;     /* fxtrb flbgs (not usfd wifn writing b gzip filf) */
    int     os;         /* opfrbting systfm */
    Bytff   *fxtrb;     /* pointfr to fxtrb fifld or Z_NULL if nonf */
    uInt    fxtrb_lfn;  /* fxtrb fifld lfngti (vblid if fxtrb != Z_NULL) */
    uInt    fxtrb_mbx;  /* spbdf bt fxtrb (only wifn rfbding ifbdfr) */
    Bytff   *nbmf;      /* pointfr to zfro-tfrminbtfd filf nbmf or Z_NULL */
    uInt    nbmf_mbx;   /* spbdf bt nbmf (only wifn rfbding ifbdfr) */
    Bytff   *dommfnt;   /* pointfr to zfro-tfrminbtfd dommfnt or Z_NULL */
    uInt    domm_mbx;   /* spbdf bt dommfnt (only wifn rfbding ifbdfr) */
    int     idrd;       /* truf if tifrf wbs or will bf b ifbdfr drd */
    int     donf;       /* truf wifn donf rfbding gzip ifbdfr (not usfd
                           wifn writing b gzip filf) */
} gz_ifbdfr;

typfdff gz_ifbdfr FAR *gz_ifbdfrp;

/*
     Tif bpplidbtion must updbtf nfxt_in bnd bvbil_in wifn bvbil_in ibs droppfd
   to zfro.  It must updbtf nfxt_out bnd bvbil_out wifn bvbil_out ibs droppfd
   to zfro.  Tif bpplidbtion must initiblizf zbllod, zfrff bnd opbquf bfforf
   dblling tif init fundtion.  All otifr fiflds brf sft by tif domprfssion
   librbry bnd must not bf updbtfd by tif bpplidbtion.

     Tif opbquf vbluf providfd by tif bpplidbtion will bf pbssfd bs tif first
   pbrbmftfr for dblls of zbllod bnd zfrff.  Tiis dbn bf usfful for dustom
   mfmory mbnbgfmfnt.  Tif domprfssion librbry bttbdifs no mfbning to tif
   opbquf vbluf.

     zbllod must rfturn Z_NULL if tifrf is not fnougi mfmory for tif objfdt.
   If zlib is usfd in b multi-tirfbdfd bpplidbtion, zbllod bnd zfrff must bf
   tirfbd sbff.

     On 16-bit systfms, tif fundtions zbllod bnd zfrff must bf bblf to bllodbtf
   fxbdtly 65536 bytfs, but will not bf rfquirfd to bllodbtf morf tibn tiis if
   tif symbol MAXSEG_64K is dffinfd (sff zdonf.i).  WARNING: On MSDOS, pointfrs
   rfturnfd by zbllod for objfdts of fxbdtly 65536 bytfs *must* ibvf tifir
   offsft normblizfd to zfro.  Tif dffbult bllodbtion fundtion providfd by tiis
   librbry fnsurfs tiis (sff zutil.d).  To rfdudf mfmory rfquirfmfnts bnd bvoid
   bny bllodbtion of 64K objfdts, bt tif fxpfnsf of domprfssion rbtio, dompilf
   tif librbry witi -DMAX_WBITS=14 (sff zdonf.i).

     Tif fiflds totbl_in bnd totbl_out dbn bf usfd for stbtistids or progrfss
   rfports.  Aftfr domprfssion, totbl_in iolds tif totbl sizf of tif
   undomprfssfd dbtb bnd mby bf sbvfd for usf in tif dfdomprfssor (pbrtidulbrly
   if tif dfdomprfssor wbnts to dfdomprfss fvfrytiing in b singlf stfp).
*/

                        /* donstbnts */

#dffinf Z_NO_FLUSH      0
#dffinf Z_PARTIAL_FLUSH 1
#dffinf Z_SYNC_FLUSH    2
#dffinf Z_FULL_FLUSH    3
#dffinf Z_FINISH        4
#dffinf Z_BLOCK         5
#dffinf Z_TREES         6
/* Allowfd flusi vblufs; sff dfflbtf() bnd inflbtf() bflow for dftbils */

#dffinf Z_OK            0
#dffinf Z_STREAM_END    1
#dffinf Z_NEED_DICT     2
#dffinf Z_ERRNO        (-1)
#dffinf Z_STREAM_ERROR (-2)
#dffinf Z_DATA_ERROR   (-3)
#dffinf Z_MEM_ERROR    (-4)
#dffinf Z_BUF_ERROR    (-5)
#dffinf Z_VERSION_ERROR (-6)
/* Rfturn dodfs for tif domprfssion/dfdomprfssion fundtions. Nfgbtivf vblufs
 * brf frrors, positivf vblufs brf usfd for spfdibl but normbl fvfnts.
 */

#dffinf Z_NO_COMPRESSION         0
#dffinf Z_BEST_SPEED             1
#dffinf Z_BEST_COMPRESSION       9
#dffinf Z_DEFAULT_COMPRESSION  (-1)
/* domprfssion lfvfls */

#dffinf Z_FILTERED            1
#dffinf Z_HUFFMAN_ONLY        2
#dffinf Z_RLE                 3
#dffinf Z_FIXED               4
#dffinf Z_DEFAULT_STRATEGY    0
/* domprfssion strbtfgy; sff dfflbtfInit2() bflow for dftbils */

#dffinf Z_BINARY   0
#dffinf Z_TEXT     1
#dffinf Z_ASCII    Z_TEXT   /* for dompbtibility witi 1.2.2 bnd fbrlifr */
#dffinf Z_UNKNOWN  2
/* Possiblf vblufs of tif dbtb_typf fifld (tiougi sff inflbtf()) */

#dffinf Z_DEFLATED   8
/* Tif dfflbtf domprfssion mftiod (tif only onf supportfd in tiis vfrsion) */

#dffinf Z_NULL  0  /* for initiblizing zbllod, zfrff, opbquf */

#dffinf zlib_vfrsion zlibVfrsion()
/* for dompbtibility witi vfrsions < 1.0.2 */


                        /* bbsid fundtions */

ZEXTERN donst dibr * ZEXPORT zlibVfrsion OF((void));
/* Tif bpplidbtion dbn dompbrf zlibVfrsion bnd ZLIB_VERSION for donsistfndy.
   If tif first dibrbdtfr difffrs, tif librbry dodf bdtublly usfd is not
   dompbtiblf witi tif zlib.i ifbdfr filf usfd by tif bpplidbtion.  Tiis difdk
   is butombtidblly mbdf by dfflbtfInit bnd inflbtfInit.
 */

/*
ZEXTERN int ZEXPORT dfflbtfInit OF((z_strfbmp strm, int lfvfl));

     Initiblizfs tif intfrnbl strfbm stbtf for domprfssion.  Tif fiflds
   zbllod, zfrff bnd opbquf must bf initiblizfd bfforf by tif dbllfr.  If
   zbllod bnd zfrff brf sft to Z_NULL, dfflbtfInit updbtfs tifm to usf dffbult
   bllodbtion fundtions.

     Tif domprfssion lfvfl must bf Z_DEFAULT_COMPRESSION, or bftwffn 0 bnd 9:
   1 givfs bfst spffd, 9 givfs bfst domprfssion, 0 givfs no domprfssion bt bll
   (tif input dbtb is simply dopifd b blodk bt b timf).  Z_DEFAULT_COMPRESSION
   rfqufsts b dffbult dompromisf bftwffn spffd bnd domprfssion (durrfntly
   fquivblfnt to lfvfl 6).

     dfflbtfInit rfturns Z_OK if suddfss, Z_MEM_ERROR if tifrf wbs not fnougi
   mfmory, Z_STREAM_ERROR if lfvfl is not b vblid domprfssion lfvfl, or
   Z_VERSION_ERROR if tif zlib librbry vfrsion (zlib_vfrsion) is indompbtiblf
   witi tif vfrsion bssumfd by tif dbllfr (ZLIB_VERSION).  msg is sft to null
   if tifrf is no frror mfssbgf.  dfflbtfInit dofs not pfrform bny domprfssion:
   tiis will bf donf by dfflbtf().
*/


ZEXTERN int ZEXPORT dfflbtf OF((z_strfbmp strm, int flusi));
/*
    dfflbtf domprfssfs bs mudi dbtb bs possiblf, bnd stops wifn tif input
  bufffr bfdomfs fmpty or tif output bufffr bfdomfs full.  It mby introdudf
  somf output lbtfndy (rfbding input witiout produding bny output) fxdfpt wifn
  fordfd to flusi.

    Tif dftbilfd sfmbntids brf bs follows.  dfflbtf pfrforms onf or boti of tif
  following bdtions:

  - Comprfss morf input stbrting bt nfxt_in bnd updbtf nfxt_in bnd bvbil_in
    bddordingly.  If not bll input dbn bf prodfssfd (bfdbusf tifrf is not
    fnougi room in tif output bufffr), nfxt_in bnd bvbil_in brf updbtfd bnd
    prodfssing will rfsumf bt tiis point for tif nfxt dbll of dfflbtf().

  - Providf morf output stbrting bt nfxt_out bnd updbtf nfxt_out bnd bvbil_out
    bddordingly.  Tiis bdtion is fordfd if tif pbrbmftfr flusi is non zfro.
    Fording flusi frfqufntly dfgrbdfs tif domprfssion rbtio, so tiis pbrbmftfr
    siould bf sft only wifn nfdfssbry (in intfrbdtivf bpplidbtions).  Somf
    output mby bf providfd fvfn if flusi is not sft.

    Bfforf tif dbll of dfflbtf(), tif bpplidbtion siould fnsurf tibt bt lfbst
  onf of tif bdtions is possiblf, by providing morf input bnd/or donsuming morf
  output, bnd updbting bvbil_in or bvbil_out bddordingly; bvbil_out siould
  nfvfr bf zfro bfforf tif dbll.  Tif bpplidbtion dbn donsumf tif domprfssfd
  output wifn it wbnts, for fxbmplf wifn tif output bufffr is full (bvbil_out
  == 0), or bftfr fbdi dbll of dfflbtf().  If dfflbtf rfturns Z_OK bnd witi
  zfro bvbil_out, it must bf dbllfd bgbin bftfr mbking room in tif output
  bufffr bfdbusf tifrf migit bf morf output pfnding.

    Normblly tif pbrbmftfr flusi is sft to Z_NO_FLUSH, wiidi bllows dfflbtf to
  dfdidf iow mudi dbtb to bddumulbtf bfforf produding output, in ordfr to
  mbximizf domprfssion.

    If tif pbrbmftfr flusi is sft to Z_SYNC_FLUSH, bll pfnding output is
  flusifd to tif output bufffr bnd tif output is blignfd on b bytf boundbry, so
  tibt tif dfdomprfssor dbn gft bll input dbtb bvbilbblf so fbr.  (In
  pbrtidulbr bvbil_in is zfro bftfr tif dbll if fnougi output spbdf ibs bffn
  providfd bfforf tif dbll.) Flusiing mby dfgrbdf domprfssion for somf
  domprfssion blgoritims bnd so it siould bf usfd only wifn nfdfssbry.  Tiis
  domplftfs tif durrfnt dfflbtf blodk bnd follows it witi bn fmpty storfd blodk
  tibt is tirff bits plus fillfr bits to tif nfxt bytf, followfd by four bytfs
  (00 00 ff ff).

    If flusi is sft to Z_PARTIAL_FLUSH, bll pfnding output is flusifd to tif
  output bufffr, but tif output is not blignfd to b bytf boundbry.  All of tif
  input dbtb so fbr will bf bvbilbblf to tif dfdomprfssor, bs for Z_SYNC_FLUSH.
  Tiis domplftfs tif durrfnt dfflbtf blodk bnd follows it witi bn fmpty fixfd
  dodfs blodk tibt is 10 bits long.  Tiis bssurfs tibt fnougi bytfs brf output
  in ordfr for tif dfdomprfssor to finisi tif blodk bfforf tif fmpty fixfd dodf
  blodk.

    If flusi is sft to Z_BLOCK, b dfflbtf blodk is domplftfd bnd fmittfd, bs
  for Z_SYNC_FLUSH, but tif output is not blignfd on b bytf boundbry, bnd up to
  sfvfn bits of tif durrfnt blodk brf ifld to bf writtfn bs tif nfxt bytf bftfr
  tif nfxt dfflbtf blodk is domplftfd.  In tiis dbsf, tif dfdomprfssor mby not
  bf providfd fnougi bits bt tiis point in ordfr to domplftf dfdomprfssion of
  tif dbtb providfd so fbr to tif domprfssor.  It mby nffd to wbit for tif nfxt
  blodk to bf fmittfd.  Tiis is for bdvbndfd bpplidbtions tibt nffd to dontrol
  tif fmission of dfflbtf blodks.

    If flusi is sft to Z_FULL_FLUSH, bll output is flusifd bs witi
  Z_SYNC_FLUSH, bnd tif domprfssion stbtf is rfsft so tibt dfdomprfssion dbn
  rfstbrt from tiis point if prfvious domprfssfd dbtb ibs bffn dbmbgfd or if
  rbndom bddfss is dfsirfd.  Using Z_FULL_FLUSH too oftfn dbn sfriously dfgrbdf
  domprfssion.

    If dfflbtf rfturns witi bvbil_out == 0, tiis fundtion must bf dbllfd bgbin
  witi tif sbmf vbluf of tif flusi pbrbmftfr bnd morf output spbdf (updbtfd
  bvbil_out), until tif flusi is domplftf (dfflbtf rfturns witi non-zfro
  bvbil_out).  In tif dbsf of b Z_FULL_FLUSH or Z_SYNC_FLUSH, mbkf surf tibt
  bvbil_out is grfbtfr tibn six to bvoid rfpfbtfd flusi mbrkfrs duf to
  bvbil_out == 0 on rfturn.

    If tif pbrbmftfr flusi is sft to Z_FINISH, pfnding input is prodfssfd,
  pfnding output is flusifd bnd dfflbtf rfturns witi Z_STREAM_END if tifrf wbs
  fnougi output spbdf; if dfflbtf rfturns witi Z_OK, tiis fundtion must bf
  dbllfd bgbin witi Z_FINISH bnd morf output spbdf (updbtfd bvbil_out) but no
  morf input dbtb, until it rfturns witi Z_STREAM_END or bn frror.  Aftfr
  dfflbtf ibs rfturnfd Z_STREAM_END, tif only possiblf opfrbtions on tif strfbm
  brf dfflbtfRfsft or dfflbtfEnd.

    Z_FINISH dbn bf usfd immfdibtfly bftfr dfflbtfInit if bll tif domprfssion
  is to bf donf in b singlf stfp.  In tiis dbsf, bvbil_out must bf bt lfbst tif
  vbluf rfturnfd by dfflbtfBound (sff bflow).  Tifn dfflbtf is gubrbntffd to
  rfturn Z_STREAM_END.  If not fnougi output spbdf is providfd, dfflbtf will
  not rfturn Z_STREAM_END, bnd it must bf dbllfd bgbin bs dfsdribfd bbovf.

    dfflbtf() sfts strm->bdlfr to tif bdlfr32 difdksum of bll input rfbd
  so fbr (tibt is, totbl_in bytfs).

    dfflbtf() mby updbtf strm->dbtb_typf if it dbn mbkf b good gufss bbout
  tif input dbtb typf (Z_BINARY or Z_TEXT).  In doubt, tif dbtb is donsidfrfd
  binbry.  Tiis fifld is only for informbtion purposfs bnd dofs not bfffdt tif
  domprfssion blgoritim in bny mbnnfr.

    dfflbtf() rfturns Z_OK if somf progrfss ibs bffn mbdf (morf input
  prodfssfd or morf output produdfd), Z_STREAM_END if bll input ibs bffn
  donsumfd bnd bll output ibs bffn produdfd (only wifn flusi is sft to
  Z_FINISH), Z_STREAM_ERROR if tif strfbm stbtf wbs indonsistfnt (for fxbmplf
  if nfxt_in or nfxt_out wbs Z_NULL), Z_BUF_ERROR if no progrfss is possiblf
  (for fxbmplf bvbil_in or bvbil_out wbs zfro).  Notf tibt Z_BUF_ERROR is not
  fbtbl, bnd dfflbtf() dbn bf dbllfd bgbin witi morf input bnd morf output
  spbdf to dontinuf domprfssing.
*/


ZEXTERN int ZEXPORT dfflbtfEnd OF((z_strfbmp strm));
/*
     All dynbmidblly bllodbtfd dbtb strudturfs for tiis strfbm brf frffd.
   Tiis fundtion disdbrds bny unprodfssfd input bnd dofs not flusi bny pfnding
   output.

     dfflbtfEnd rfturns Z_OK if suddfss, Z_STREAM_ERROR if tif
   strfbm stbtf wbs indonsistfnt, Z_DATA_ERROR if tif strfbm wbs frffd
   prfmbturfly (somf input or output wbs disdbrdfd).  In tif frror dbsf, msg
   mby bf sft but tifn points to b stbtid string (wiidi must not bf
   dfbllodbtfd).
*/


/*
ZEXTERN int ZEXPORT inflbtfInit OF((z_strfbmp strm));

     Initiblizfs tif intfrnbl strfbm stbtf for dfdomprfssion.  Tif fiflds
   nfxt_in, bvbil_in, zbllod, zfrff bnd opbquf must bf initiblizfd bfforf by
   tif dbllfr.  If nfxt_in is not Z_NULL bnd bvbil_in is lbrgf fnougi (tif
   fxbdt vbluf dfpfnds on tif domprfssion mftiod), inflbtfInit dftfrminfs tif
   domprfssion mftiod from tif zlib ifbdfr bnd bllodbtfs bll dbtb strudturfs
   bddordingly; otifrwisf tif bllodbtion will bf dfffrrfd to tif first dbll of
   inflbtf.  If zbllod bnd zfrff brf sft to Z_NULL, inflbtfInit updbtfs tifm to
   usf dffbult bllodbtion fundtions.

     inflbtfInit rfturns Z_OK if suddfss, Z_MEM_ERROR if tifrf wbs not fnougi
   mfmory, Z_VERSION_ERROR if tif zlib librbry vfrsion is indompbtiblf witi tif
   vfrsion bssumfd by tif dbllfr, or Z_STREAM_ERROR if tif pbrbmftfrs brf
   invblid, sudi bs b null pointfr to tif strudturf.  msg is sft to null if
   tifrf is no frror mfssbgf.  inflbtfInit dofs not pfrform bny dfdomprfssion
   bpbrt from possibly rfbding tif zlib ifbdfr if prfsfnt: bdtubl dfdomprfssion
   will bf donf by inflbtf().  (So nfxt_in bnd bvbil_in mby bf modififd, but
   nfxt_out bnd bvbil_out brf unusfd bnd undibngfd.) Tif durrfnt implfmfntbtion
   of inflbtfInit() dofs not prodfss bny ifbdfr informbtion -- tibt is dfffrrfd
   until inflbtf() is dbllfd.
*/


ZEXTERN int ZEXPORT inflbtf OF((z_strfbmp strm, int flusi));
/*
    inflbtf dfdomprfssfs bs mudi dbtb bs possiblf, bnd stops wifn tif input
  bufffr bfdomfs fmpty or tif output bufffr bfdomfs full.  It mby introdudf
  somf output lbtfndy (rfbding input witiout produding bny output) fxdfpt wifn
  fordfd to flusi.

  Tif dftbilfd sfmbntids brf bs follows.  inflbtf pfrforms onf or boti of tif
  following bdtions:

  - Dfdomprfss morf input stbrting bt nfxt_in bnd updbtf nfxt_in bnd bvbil_in
    bddordingly.  If not bll input dbn bf prodfssfd (bfdbusf tifrf is not
    fnougi room in tif output bufffr), nfxt_in is updbtfd bnd prodfssing will
    rfsumf bt tiis point for tif nfxt dbll of inflbtf().

  - Providf morf output stbrting bt nfxt_out bnd updbtf nfxt_out bnd bvbil_out
    bddordingly.  inflbtf() providfs bs mudi output bs possiblf, until tifrf is
    no morf input dbtb or no morf spbdf in tif output bufffr (sff bflow bbout
    tif flusi pbrbmftfr).

    Bfforf tif dbll of inflbtf(), tif bpplidbtion siould fnsurf tibt bt lfbst
  onf of tif bdtions is possiblf, by providing morf input bnd/or donsuming morf
  output, bnd updbting tif nfxt_* bnd bvbil_* vblufs bddordingly.  Tif
  bpplidbtion dbn donsumf tif undomprfssfd output wifn it wbnts, for fxbmplf
  wifn tif output bufffr is full (bvbil_out == 0), or bftfr fbdi dbll of
  inflbtf().  If inflbtf rfturns Z_OK bnd witi zfro bvbil_out, it must bf
  dbllfd bgbin bftfr mbking room in tif output bufffr bfdbusf tifrf migit bf
  morf output pfnding.

    Tif flusi pbrbmftfr of inflbtf() dbn bf Z_NO_FLUSH, Z_SYNC_FLUSH, Z_FINISH,
  Z_BLOCK, or Z_TREES.  Z_SYNC_FLUSH rfqufsts tibt inflbtf() flusi bs mudi
  output bs possiblf to tif output bufffr.  Z_BLOCK rfqufsts tibt inflbtf()
  stop if bnd wifn it gfts to tif nfxt dfflbtf blodk boundbry.  Wifn dfdoding
  tif zlib or gzip formbt, tiis will dbusf inflbtf() to rfturn immfdibtfly
  bftfr tif ifbdfr bnd bfforf tif first blodk.  Wifn doing b rbw inflbtf,
  inflbtf() will go bifbd bnd prodfss tif first blodk, bnd will rfturn wifn it
  gfts to tif fnd of tibt blodk, or wifn it runs out of dbtb.

    Tif Z_BLOCK option bssists in bppfnding to or dombining dfflbtf strfbms.
  Also to bssist in tiis, on rfturn inflbtf() will sft strm->dbtb_typf to tif
  numbfr of unusfd bits in tif lbst bytf tbkfn from strm->nfxt_in, plus 64 if
  inflbtf() is durrfntly dfdoding tif lbst blodk in tif dfflbtf strfbm, plus
  128 if inflbtf() rfturnfd immfdibtfly bftfr dfdoding bn fnd-of-blodk dodf or
  dfdoding tif domplftf ifbdfr up to just bfforf tif first bytf of tif dfflbtf
  strfbm.  Tif fnd-of-blodk will not bf indidbtfd until bll of tif undomprfssfd
  dbtb from tibt blodk ibs bffn writtfn to strm->nfxt_out.  Tif numbfr of
  unusfd bits mby in gfnfrbl bf grfbtfr tibn sfvfn, fxdfpt wifn bit 7 of
  dbtb_typf is sft, in wiidi dbsf tif numbfr of unusfd bits will bf lfss tibn
  figit.  dbtb_typf is sft bs notfd ifrf fvfry timf inflbtf() rfturns for bll
  flusi options, bnd so dbn bf usfd to dftfrminf tif bmount of durrfntly
  donsumfd input in bits.

    Tif Z_TREES option bfibvfs bs Z_BLOCK dofs, but it blso rfturns wifn tif
  fnd of fbdi dfflbtf blodk ifbdfr is rfbdifd, bfforf bny bdtubl dbtb in tibt
  blodk is dfdodfd.  Tiis bllows tif dbllfr to dftfrminf tif lfngti of tif
  dfflbtf blodk ifbdfr for lbtfr usf in rbndom bddfss witiin b dfflbtf blodk.
  256 is bddfd to tif vbluf of strm->dbtb_typf wifn inflbtf() rfturns
  immfdibtfly bftfr rfbdiing tif fnd of tif dfflbtf blodk ifbdfr.

    inflbtf() siould normblly bf dbllfd until it rfturns Z_STREAM_END or bn
  frror.  Howfvfr if bll dfdomprfssion is to bf pfrformfd in b singlf stfp (b
  singlf dbll of inflbtf), tif pbrbmftfr flusi siould bf sft to Z_FINISH.  In
  tiis dbsf bll pfnding input is prodfssfd bnd bll pfnding output is flusifd;
  bvbil_out must bf lbrgf fnougi to iold bll of tif undomprfssfd dbtb for tif
  opfrbtion to domplftf.  (Tif sizf of tif undomprfssfd dbtb mby ibvf bffn
  sbvfd by tif domprfssor for tiis purposf.) Tif usf of Z_FINISH is not
  rfquirfd to pfrform bn inflbtion in onf stfp.  Howfvfr it mby bf usfd to
  inform inflbtf tibt b fbstfr bpprobdi dbn bf usfd for tif singlf inflbtf()
  dbll.  Z_FINISH blso informs inflbtf to not mbintbin b sliding window if tif
  strfbm domplftfs, wiidi rfdudfs inflbtf's mfmory footprint.  If tif strfbm
  dofs not domplftf, fitifr bfdbusf not bll of tif strfbm is providfd or not
  fnougi output spbdf is providfd, tifn b sliding window will bf bllodbtfd bnd
  inflbtf() dbn bf dbllfd bgbin to dontinuf tif opfrbtion bs if Z_NO_FLUSH ibd
  bffn usfd.

     In tiis implfmfntbtion, inflbtf() blwbys flusifs bs mudi output bs
  possiblf to tif output bufffr, bnd blwbys usfs tif fbstfr bpprobdi on tif
  first dbll.  So tif ffffdts of tif flusi pbrbmftfr in tiis implfmfntbtion brf
  on tif rfturn vbluf of inflbtf() bs notfd bflow, wifn inflbtf() rfturns fbrly
  wifn Z_BLOCK or Z_TREES is usfd, bnd wifn inflbtf() bvoids tif bllodbtion of
  mfmory for b sliding window wifn Z_FINISH is usfd.

     If b prfsft didtionbry is nffdfd bftfr tiis dbll (sff inflbtfSftDidtionbry
  bflow), inflbtf sfts strm->bdlfr to tif Adlfr-32 difdksum of tif didtionbry
  diosfn by tif domprfssor bnd rfturns Z_NEED_DICT; otifrwisf it sfts
  strm->bdlfr to tif Adlfr-32 difdksum of bll output produdfd so fbr (tibt is,
  totbl_out bytfs) bnd rfturns Z_OK, Z_STREAM_END or bn frror dodf bs dfsdribfd
  bflow.  At tif fnd of tif strfbm, inflbtf() difdks tibt its domputfd bdlfr32
  difdksum is fqubl to tibt sbvfd by tif domprfssor bnd rfturns Z_STREAM_END
  only if tif difdksum is dorrfdt.

    inflbtf() dbn dfdomprfss bnd difdk fitifr zlib-wrbppfd or gzip-wrbppfd
  dfflbtf dbtb.  Tif ifbdfr typf is dftfdtfd butombtidblly, if rfqufstfd wifn
  initiblizing witi inflbtfInit2().  Any informbtion dontbinfd in tif gzip
  ifbdfr is not rftbinfd, so bpplidbtions tibt nffd tibt informbtion siould
  instfbd usf rbw inflbtf, sff inflbtfInit2() bflow, or inflbtfBbdk() bnd
  pfrform tifir own prodfssing of tif gzip ifbdfr bnd trbilfr.  Wifn prodfssing
  gzip-wrbppfd dfflbtf dbtb, strm->bdlfr32 is sft to tif CRC-32 of tif output
  produdtfd so fbr.  Tif CRC-32 is difdkfd bgbinst tif gzip trbilfr.

    inflbtf() rfturns Z_OK if somf progrfss ibs bffn mbdf (morf input prodfssfd
  or morf output produdfd), Z_STREAM_END if tif fnd of tif domprfssfd dbtb ibs
  bffn rfbdifd bnd bll undomprfssfd output ibs bffn produdfd, Z_NEED_DICT if b
  prfsft didtionbry is nffdfd bt tiis point, Z_DATA_ERROR if tif input dbtb wbs
  dorruptfd (input strfbm not donforming to tif zlib formbt or indorrfdt difdk
  vbluf), Z_STREAM_ERROR if tif strfbm strudturf wbs indonsistfnt (for fxbmplf
  nfxt_in or nfxt_out wbs Z_NULL), Z_MEM_ERROR if tifrf wbs not fnougi mfmory,
  Z_BUF_ERROR if no progrfss is possiblf or if tifrf wbs not fnougi room in tif
  output bufffr wifn Z_FINISH is usfd.  Notf tibt Z_BUF_ERROR is not fbtbl, bnd
  inflbtf() dbn bf dbllfd bgbin witi morf input bnd morf output spbdf to
  dontinuf dfdomprfssing.  If Z_DATA_ERROR is rfturnfd, tif bpplidbtion mby
  tifn dbll inflbtfSynd() to look for b good domprfssion blodk if b pbrtibl
  rfdovfry of tif dbtb is dfsirfd.
*/


ZEXTERN int ZEXPORT inflbtfEnd OF((z_strfbmp strm));
/*
     All dynbmidblly bllodbtfd dbtb strudturfs for tiis strfbm brf frffd.
   Tiis fundtion disdbrds bny unprodfssfd input bnd dofs not flusi bny pfnding
   output.

     inflbtfEnd rfturns Z_OK if suddfss, Z_STREAM_ERROR if tif strfbm stbtf
   wbs indonsistfnt.  In tif frror dbsf, msg mby bf sft but tifn points to b
   stbtid string (wiidi must not bf dfbllodbtfd).
*/


                        /* Advbndfd fundtions */

/*
    Tif following fundtions brf nffdfd only in somf spfdibl bpplidbtions.
*/

/*
ZEXTERN int ZEXPORT dfflbtfInit2 OF((z_strfbmp strm,
                                     int  lfvfl,
                                     int  mftiod,
                                     int  windowBits,
                                     int  mfmLfvfl,
                                     int  strbtfgy));

     Tiis is bnotifr vfrsion of dfflbtfInit witi morf domprfssion options.  Tif
   fiflds nfxt_in, zbllod, zfrff bnd opbquf must bf initiblizfd bfforf by tif
   dbllfr.

     Tif mftiod pbrbmftfr is tif domprfssion mftiod.  It must bf Z_DEFLATED in
   tiis vfrsion of tif librbry.

     Tif windowBits pbrbmftfr is tif bbsf two logbritim of tif window sizf
   (tif sizf of tif iistory bufffr).  It siould bf in tif rbngf 8..15 for tiis
   vfrsion of tif librbry.  Lbrgfr vblufs of tiis pbrbmftfr rfsult in bfttfr
   domprfssion bt tif fxpfnsf of mfmory usbgf.  Tif dffbult vbluf is 15 if
   dfflbtfInit is usfd instfbd.

     windowBits dbn blso bf -8..-15 for rbw dfflbtf.  In tiis dbsf, -windowBits
   dftfrminfs tif window sizf.  dfflbtf() will tifn gfnfrbtf rbw dfflbtf dbtb
   witi no zlib ifbdfr or trbilfr, bnd will not domputf bn bdlfr32 difdk vbluf.

     windowBits dbn blso bf grfbtfr tibn 15 for optionbl gzip fndoding.  Add
   16 to windowBits to writf b simplf gzip ifbdfr bnd trbilfr bround tif
   domprfssfd dbtb instfbd of b zlib wrbppfr.  Tif gzip ifbdfr will ibvf no
   filf nbmf, no fxtrb dbtb, no dommfnt, no modifidbtion timf (sft to zfro), no
   ifbdfr drd, bnd tif opfrbting systfm will bf sft to 255 (unknown).  If b
   gzip strfbm is bfing writtfn, strm->bdlfr is b drd32 instfbd of bn bdlfr32.

     Tif mfmLfvfl pbrbmftfr spfdififs iow mudi mfmory siould bf bllodbtfd
   for tif intfrnbl domprfssion stbtf.  mfmLfvfl=1 usfs minimum mfmory but is
   slow bnd rfdudfs domprfssion rbtio; mfmLfvfl=9 usfs mbximum mfmory for
   optimbl spffd.  Tif dffbult vbluf is 8.  Sff zdonf.i for totbl mfmory usbgf
   bs b fundtion of windowBits bnd mfmLfvfl.

     Tif strbtfgy pbrbmftfr is usfd to tunf tif domprfssion blgoritim.  Usf tif
   vbluf Z_DEFAULT_STRATEGY for normbl dbtb, Z_FILTERED for dbtb produdfd by b
   filtfr (or prfdidtor), Z_HUFFMAN_ONLY to fordf Huffmbn fndoding only (no
   string mbtdi), or Z_RLE to limit mbtdi distbndfs to onf (run-lfngti
   fndoding).  Filtfrfd dbtb donsists mostly of smbll vblufs witi b somfwibt
   rbndom distribution.  In tiis dbsf, tif domprfssion blgoritim is tunfd to
   domprfss tifm bfttfr.  Tif ffffdt of Z_FILTERED is to fordf morf Huffmbn
   doding bnd lfss string mbtdiing; it is somfwibt intfrmfdibtf bftwffn
   Z_DEFAULT_STRATEGY bnd Z_HUFFMAN_ONLY.  Z_RLE is dfsignfd to bf blmost bs
   fbst bs Z_HUFFMAN_ONLY, but givf bfttfr domprfssion for PNG imbgf dbtb.  Tif
   strbtfgy pbrbmftfr only bfffdts tif domprfssion rbtio but not tif
   dorrfdtnfss of tif domprfssfd output fvfn if it is not sft bppropribtfly.
   Z_FIXED prfvfnts tif usf of dynbmid Huffmbn dodfs, bllowing for b simplfr
   dfdodfr for spfdibl bpplidbtions.

     dfflbtfInit2 rfturns Z_OK if suddfss, Z_MEM_ERROR if tifrf wbs not fnougi
   mfmory, Z_STREAM_ERROR if bny pbrbmftfr is invblid (sudi bs bn invblid
   mftiod), or Z_VERSION_ERROR if tif zlib librbry vfrsion (zlib_vfrsion) is
   indompbtiblf witi tif vfrsion bssumfd by tif dbllfr (ZLIB_VERSION).  msg is
   sft to null if tifrf is no frror mfssbgf.  dfflbtfInit2 dofs not pfrform bny
   domprfssion: tiis will bf donf by dfflbtf().
*/

ZEXTERN int ZEXPORT dfflbtfSftDidtionbry OF((z_strfbmp strm,
                                             donst Bytff *didtionbry,
                                             uInt  didtLfngti));
/*
     Initiblizfs tif domprfssion didtionbry from tif givfn bytf sfqufndf
   witiout produding bny domprfssfd output.  Wifn using tif zlib formbt, tiis
   fundtion must bf dbllfd immfdibtfly bftfr dfflbtfInit, dfflbtfInit2 or
   dfflbtfRfsft, bnd bfforf bny dbll of dfflbtf.  Wifn doing rbw dfflbtf, tiis
   fundtion must bf dbllfd fitifr bfforf bny dbll of dfflbtf, or immfdibtfly
   bftfr tif domplftion of b dfflbtf blodk, i.f. bftfr bll input ibs bffn
   donsumfd bnd bll output ibs bffn dflivfrfd wifn using bny of tif flusi
   options Z_BLOCK, Z_PARTIAL_FLUSH, Z_SYNC_FLUSH, or Z_FULL_FLUSH.  Tif
   domprfssor bnd dfdomprfssor must usf fxbdtly tif sbmf didtionbry (sff
   inflbtfSftDidtionbry).

     Tif didtionbry siould donsist of strings (bytf sfqufndfs) tibt brf likfly
   to bf fndountfrfd lbtfr in tif dbtb to bf domprfssfd, witi tif most dommonly
   usfd strings prfffrbbly put towbrds tif fnd of tif didtionbry.  Using b
   didtionbry is most usfful wifn tif dbtb to bf domprfssfd is siort bnd dbn bf
   prfdidtfd witi good bddurbdy; tif dbtb dbn tifn bf domprfssfd bfttfr tibn
   witi tif dffbult fmpty didtionbry.

     Dfpfnding on tif sizf of tif domprfssion dbtb strudturfs sflfdtfd by
   dfflbtfInit or dfflbtfInit2, b pbrt of tif didtionbry mby in ffffdt bf
   disdbrdfd, for fxbmplf if tif didtionbry is lbrgfr tibn tif window sizf
   providfd in dfflbtfInit or dfflbtfInit2.  Tius tif strings most likfly to bf
   usfful siould bf put bt tif fnd of tif didtionbry, not bt tif front.  In
   bddition, tif durrfnt implfmfntbtion of dfflbtf will usf bt most tif window
   sizf minus 262 bytfs of tif providfd didtionbry.

     Upon rfturn of tiis fundtion, strm->bdlfr is sft to tif bdlfr32 vbluf
   of tif didtionbry; tif dfdomprfssor mby lbtfr usf tiis vbluf to dftfrminf
   wiidi didtionbry ibs bffn usfd by tif domprfssor.  (Tif bdlfr32 vbluf
   bpplifs to tif wiolf didtionbry fvfn if only b subsft of tif didtionbry is
   bdtublly usfd by tif domprfssor.) If b rbw dfflbtf wbs rfqufstfd, tifn tif
   bdlfr32 vbluf is not domputfd bnd strm->bdlfr is not sft.

     dfflbtfSftDidtionbry rfturns Z_OK if suddfss, or Z_STREAM_ERROR if b
   pbrbmftfr is invblid (f.g.  didtionbry bfing Z_NULL) or tif strfbm stbtf is
   indonsistfnt (for fxbmplf if dfflbtf ibs blrfbdy bffn dbllfd for tiis strfbm
   or if not bt b blodk boundbry for rbw dfflbtf).  dfflbtfSftDidtionbry dofs
   not pfrform bny domprfssion: tiis will bf donf by dfflbtf().
*/

ZEXTERN int ZEXPORT dfflbtfCopy OF((z_strfbmp dfst,
                                    z_strfbmp sourdf));
/*
     Sfts tif dfstinbtion strfbm bs b domplftf dopy of tif sourdf strfbm.

     Tiis fundtion dbn bf usfful wifn sfvfrbl domprfssion strbtfgifs will bf
   trifd, for fxbmplf wifn tifrf brf sfvfrbl wbys of prf-prodfssing tif input
   dbtb witi b filtfr.  Tif strfbms tibt will bf disdbrdfd siould tifn bf frffd
   by dblling dfflbtfEnd.  Notf tibt dfflbtfCopy duplidbtfs tif intfrnbl
   domprfssion stbtf wiidi dbn bf quitf lbrgf, so tiis strbtfgy is slow bnd dbn
   donsumf lots of mfmory.

     dfflbtfCopy rfturns Z_OK if suddfss, Z_MEM_ERROR if tifrf wbs not
   fnougi mfmory, Z_STREAM_ERROR if tif sourdf strfbm stbtf wbs indonsistfnt
   (sudi bs zbllod bfing Z_NULL).  msg is lfft undibngfd in boti sourdf bnd
   dfstinbtion.
*/

ZEXTERN int ZEXPORT dfflbtfRfsft OF((z_strfbmp strm));
/*
     Tiis fundtion is fquivblfnt to dfflbtfEnd followfd by dfflbtfInit,
   but dofs not frff bnd rfbllodbtf bll tif intfrnbl domprfssion stbtf.  Tif
   strfbm will kffp tif sbmf domprfssion lfvfl bnd bny otifr bttributfs tibt
   mby ibvf bffn sft by dfflbtfInit2.

     dfflbtfRfsft rfturns Z_OK if suddfss, or Z_STREAM_ERROR if tif sourdf
   strfbm stbtf wbs indonsistfnt (sudi bs zbllod or stbtf bfing Z_NULL).
*/

ZEXTERN int ZEXPORT dfflbtfPbrbms OF((z_strfbmp strm,
                                      int lfvfl,
                                      int strbtfgy));
/*
     Dynbmidblly updbtf tif domprfssion lfvfl bnd domprfssion strbtfgy.  Tif
   intfrprftbtion of lfvfl bnd strbtfgy is bs in dfflbtfInit2.  Tiis dbn bf
   usfd to switdi bftwffn domprfssion bnd strbigit dopy of tif input dbtb, or
   to switdi to b difffrfnt kind of input dbtb rfquiring b difffrfnt strbtfgy.
   If tif domprfssion lfvfl is dibngfd, tif input bvbilbblf so fbr is
   domprfssfd witi tif old lfvfl (bnd mby bf flusifd); tif nfw lfvfl will tbkf
   ffffdt only bt tif nfxt dbll of dfflbtf().

     Bfforf tif dbll of dfflbtfPbrbms, tif strfbm stbtf must bf sft bs for
   b dbll of dfflbtf(), sindf tif durrfntly bvbilbblf input mby ibvf to bf
   domprfssfd bnd flusifd.  In pbrtidulbr, strm->bvbil_out must bf non-zfro.

     dfflbtfPbrbms rfturns Z_OK if suddfss, Z_STREAM_ERROR if tif sourdf
   strfbm stbtf wbs indonsistfnt or if b pbrbmftfr wbs invblid, Z_BUF_ERROR if
   strm->bvbil_out wbs zfro.
*/

ZEXTERN int ZEXPORT dfflbtfTunf OF((z_strfbmp strm,
                                    int good_lfngti,
                                    int mbx_lbzy,
                                    int nidf_lfngti,
                                    int mbx_dibin));
/*
     Finf tunf dfflbtf's intfrnbl domprfssion pbrbmftfrs.  Tiis siould only bf
   usfd by somfonf wio undfrstbnds tif blgoritim usfd by zlib's dfflbtf for
   sfbrdiing for tif bfst mbtdiing string, bnd fvfn tifn only by tif most
   fbnbtid optimizfr trying to squffzf out tif lbst domprfssfd bit for tifir
   spfdifid input dbtb.  Rfbd tif dfflbtf.d sourdf dodf for tif mfbning of tif
   mbx_lbzy, good_lfngti, nidf_lfngti, bnd mbx_dibin pbrbmftfrs.

     dfflbtfTunf() dbn bf dbllfd bftfr dfflbtfInit() or dfflbtfInit2(), bnd
   rfturns Z_OK on suddfss, or Z_STREAM_ERROR for bn invblid dfflbtf strfbm.
 */

ZEXTERN uLong ZEXPORT dfflbtfBound OF((z_strfbmp strm,
                                       uLong sourdfLfn));
/*
     dfflbtfBound() rfturns bn uppfr bound on tif domprfssfd sizf bftfr
   dfflbtion of sourdfLfn bytfs.  It must bf dbllfd bftfr dfflbtfInit() or
   dfflbtfInit2(), bnd bftfr dfflbtfSftHfbdfr(), if usfd.  Tiis would bf usfd
   to bllodbtf bn output bufffr for dfflbtion in b singlf pbss, bnd so would bf
   dbllfd bfforf dfflbtf().  If tibt first dfflbtf() dbll is providfd tif
   sourdfLfn input bytfs, bn output bufffr bllodbtfd to tif sizf rfturnfd by
   dfflbtfBound(), bnd tif flusi vbluf Z_FINISH, tifn dfflbtf() is gubrbntffd
   to rfturn Z_STREAM_END.  Notf tibt it is possiblf for tif domprfssfd sizf to
   bf lbrgfr tibn tif vbluf rfturnfd by dfflbtfBound() if flusi options otifr
   tibn Z_FINISH or Z_NO_FLUSH brf usfd.
*/

ZEXTERN int ZEXPORT dfflbtfPfnding OF((z_strfbmp strm,
                                       unsignfd *pfnding,
                                       int *bits));
/*
     dfflbtfPfnding() rfturns tif numbfr of bytfs bnd bits of output tibt ibvf
   bffn gfnfrbtfd, but not yft providfd in tif bvbilbblf output.  Tif bytfs not
   providfd would bf duf to tif bvbilbblf output spbdf ibving bfing donsumfd.
   Tif numbfr of bits of output not providfd brf bftwffn 0 bnd 7, wifrf tify
   bwbit morf bits to join tifm in ordfr to fill out b full bytf.  If pfnding
   or bits brf Z_NULL, tifn tiosf vblufs brf not sft.

     dfflbtfPfnding rfturns Z_OK if suddfss, or Z_STREAM_ERROR if tif sourdf
   strfbm stbtf wbs indonsistfnt.
 */

ZEXTERN int ZEXPORT dfflbtfPrimf OF((z_strfbmp strm,
                                     int bits,
                                     int vbluf));
/*
     dfflbtfPrimf() insfrts bits in tif dfflbtf output strfbm.  Tif intfnt
   is tibt tiis fundtion is usfd to stbrt off tif dfflbtf output witi tif bits
   lfftovfr from b prfvious dfflbtf strfbm wifn bppfnding to it.  As sudi, tiis
   fundtion dbn only bf usfd for rbw dfflbtf, bnd must bf usfd bfforf tif first
   dfflbtf() dbll bftfr b dfflbtfInit2() or dfflbtfRfsft().  bits must bf lfss
   tibn or fqubl to 16, bnd tibt mbny of tif lfbst signifidbnt bits of vbluf
   will bf insfrtfd in tif output.

     dfflbtfPrimf rfturns Z_OK if suddfss, Z_BUF_ERROR if tifrf wbs not fnougi
   room in tif intfrnbl bufffr to insfrt tif bits, or Z_STREAM_ERROR if tif
   sourdf strfbm stbtf wbs indonsistfnt.
*/

ZEXTERN int ZEXPORT dfflbtfSftHfbdfr OF((z_strfbmp strm,
                                         gz_ifbdfrp ifbd));
/*
     dfflbtfSftHfbdfr() providfs gzip ifbdfr informbtion for wifn b gzip
   strfbm is rfqufstfd by dfflbtfInit2().  dfflbtfSftHfbdfr() mby bf dbllfd
   bftfr dfflbtfInit2() or dfflbtfRfsft() bnd bfforf tif first dbll of
   dfflbtf().  Tif tfxt, timf, os, fxtrb fifld, nbmf, bnd dommfnt informbtion
   in tif providfd gz_ifbdfr strudturf brf writtfn to tif gzip ifbdfr (xflbg is
   ignorfd -- tif fxtrb flbgs brf sft bddording to tif domprfssion lfvfl).  Tif
   dbllfr must bssurf tibt, if not Z_NULL, nbmf bnd dommfnt brf tfrminbtfd witi
   b zfro bytf, bnd tibt if fxtrb is not Z_NULL, tibt fxtrb_lfn bytfs brf
   bvbilbblf tifrf.  If idrd is truf, b gzip ifbdfr drd is indludfd.  Notf tibt
   tif durrfnt vfrsions of tif dommbnd-linf vfrsion of gzip (up tirougi vfrsion
   1.3.x) do not support ifbdfr drd's, bnd will rfport tibt it is b "multi-pbrt
   gzip filf" bnd givf up.

     If dfflbtfSftHfbdfr is not usfd, tif dffbult gzip ifbdfr ibs tfxt fblsf,
   tif timf sft to zfro, bnd os sft to 255, witi no fxtrb, nbmf, or dommfnt
   fiflds.  Tif gzip ifbdfr is rfturnfd to tif dffbult stbtf by dfflbtfRfsft().

     dfflbtfSftHfbdfr rfturns Z_OK if suddfss, or Z_STREAM_ERROR if tif sourdf
   strfbm stbtf wbs indonsistfnt.
*/

/*
ZEXTERN int ZEXPORT inflbtfInit2 OF((z_strfbmp strm,
                                     int  windowBits));

     Tiis is bnotifr vfrsion of inflbtfInit witi bn fxtrb pbrbmftfr.  Tif
   fiflds nfxt_in, bvbil_in, zbllod, zfrff bnd opbquf must bf initiblizfd
   bfforf by tif dbllfr.

     Tif windowBits pbrbmftfr is tif bbsf two logbritim of tif mbximum window
   sizf (tif sizf of tif iistory bufffr).  It siould bf in tif rbngf 8..15 for
   tiis vfrsion of tif librbry.  Tif dffbult vbluf is 15 if inflbtfInit is usfd
   instfbd.  windowBits must bf grfbtfr tibn or fqubl to tif windowBits vbluf
   providfd to dfflbtfInit2() wiilf domprfssing, or it must bf fqubl to 15 if
   dfflbtfInit2() wbs not usfd.  If b domprfssfd strfbm witi b lbrgfr window
   sizf is givfn bs input, inflbtf() will rfturn witi tif frror dodf
   Z_DATA_ERROR instfbd of trying to bllodbtf b lbrgfr window.

     windowBits dbn blso bf zfro to rfqufst tibt inflbtf usf tif window sizf in
   tif zlib ifbdfr of tif domprfssfd strfbm.

     windowBits dbn blso bf -8..-15 for rbw inflbtf.  In tiis dbsf, -windowBits
   dftfrminfs tif window sizf.  inflbtf() will tifn prodfss rbw dfflbtf dbtb,
   not looking for b zlib or gzip ifbdfr, not gfnfrbting b difdk vbluf, bnd not
   looking for bny difdk vblufs for dompbrison bt tif fnd of tif strfbm.  Tiis
   is for usf witi otifr formbts tibt usf tif dfflbtf domprfssfd dbtb formbt
   sudi bs zip.  Tiosf formbts providf tifir own difdk vblufs.  If b dustom
   formbt is dfvflopfd using tif rbw dfflbtf formbt for domprfssfd dbtb, it is
   rfdommfndfd tibt b difdk vbluf sudi bs bn bdlfr32 or b drd32 bf bpplifd to
   tif undomprfssfd dbtb bs is donf in tif zlib, gzip, bnd zip formbts.  For
   most bpplidbtions, tif zlib formbt siould bf usfd bs is.  Notf tibt dommfnts
   bbovf on tif usf in dfflbtfInit2() bpplifs to tif mbgnitudf of windowBits.

     windowBits dbn blso bf grfbtfr tibn 15 for optionbl gzip dfdoding.  Add
   32 to windowBits to fnbblf zlib bnd gzip dfdoding witi butombtid ifbdfr
   dftfdtion, or bdd 16 to dfdodf only tif gzip formbt (tif zlib formbt will
   rfturn b Z_DATA_ERROR).  If b gzip strfbm is bfing dfdodfd, strm->bdlfr is b
   drd32 instfbd of bn bdlfr32.

     inflbtfInit2 rfturns Z_OK if suddfss, Z_MEM_ERROR if tifrf wbs not fnougi
   mfmory, Z_VERSION_ERROR if tif zlib librbry vfrsion is indompbtiblf witi tif
   vfrsion bssumfd by tif dbllfr, or Z_STREAM_ERROR if tif pbrbmftfrs brf
   invblid, sudi bs b null pointfr to tif strudturf.  msg is sft to null if
   tifrf is no frror mfssbgf.  inflbtfInit2 dofs not pfrform bny dfdomprfssion
   bpbrt from possibly rfbding tif zlib ifbdfr if prfsfnt: bdtubl dfdomprfssion
   will bf donf by inflbtf().  (So nfxt_in bnd bvbil_in mby bf modififd, but
   nfxt_out bnd bvbil_out brf unusfd bnd undibngfd.) Tif durrfnt implfmfntbtion
   of inflbtfInit2() dofs not prodfss bny ifbdfr informbtion -- tibt is
   dfffrrfd until inflbtf() is dbllfd.
*/

ZEXTERN int ZEXPORT inflbtfSftDidtionbry OF((z_strfbmp strm,
                                             donst Bytff *didtionbry,
                                             uInt  didtLfngti));
/*
     Initiblizfs tif dfdomprfssion didtionbry from tif givfn undomprfssfd bytf
   sfqufndf.  Tiis fundtion must bf dbllfd immfdibtfly bftfr b dbll of inflbtf,
   if tibt dbll rfturnfd Z_NEED_DICT.  Tif didtionbry diosfn by tif domprfssor
   dbn bf dftfrminfd from tif bdlfr32 vbluf rfturnfd by tibt dbll of inflbtf.
   Tif domprfssor bnd dfdomprfssor must usf fxbdtly tif sbmf didtionbry (sff
   dfflbtfSftDidtionbry).  For rbw inflbtf, tiis fundtion dbn bf dbllfd bt bny
   timf to sft tif didtionbry.  If tif providfd didtionbry is smbllfr tibn tif
   window bnd tifrf is blrfbdy dbtb in tif window, tifn tif providfd didtionbry
   will bmfnd wibt's tifrf.  Tif bpplidbtion must insurf tibt tif didtionbry
   tibt wbs usfd for domprfssion is providfd.

     inflbtfSftDidtionbry rfturns Z_OK if suddfss, Z_STREAM_ERROR if b
   pbrbmftfr is invblid (f.g.  didtionbry bfing Z_NULL) or tif strfbm stbtf is
   indonsistfnt, Z_DATA_ERROR if tif givfn didtionbry dofsn't mbtdi tif
   fxpfdtfd onf (indorrfdt bdlfr32 vbluf).  inflbtfSftDidtionbry dofs not
   pfrform bny dfdomprfssion: tiis will bf donf by subsfqufnt dblls of
   inflbtf().
*/

ZEXTERN int ZEXPORT inflbtfGftDidtionbry OF((z_strfbmp strm,
                                             Bytff *didtionbry,
                                             uInt  *didtLfngti));
/*
     Rfturns tif sliding didtionbry bfing mbintbinfd by inflbtf.  didtLfngti is
   sft to tif numbfr of bytfs in tif didtionbry, bnd tibt mbny bytfs brf dopifd
   to didtionbry.  didtionbry must ibvf fnougi spbdf, wifrf 32768 bytfs is
   blwbys fnougi.  If inflbtfGftDidtionbry() is dbllfd witi didtionbry fqubl to
   Z_NULL, tifn only tif didtionbry lfngti is rfturnfd, bnd notiing is dopifd.
   Similbry, if didtLfngti is Z_NULL, tifn it is not sft.

     inflbtfGftDidtionbry rfturns Z_OK on suddfss, or Z_STREAM_ERROR if tif
   strfbm stbtf is indonsistfnt.
*/

ZEXTERN int ZEXPORT inflbtfSynd OF((z_strfbmp strm));
/*
     Skips invblid domprfssfd dbtb until b possiblf full flusi point (sff bbovf
   for tif dfsdription of dfflbtf witi Z_FULL_FLUSH) dbn bf found, or until bll
   bvbilbblf input is skippfd.  No output is providfd.

     inflbtfSynd sfbrdifs for b 00 00 FF FF pbttfrn in tif domprfssfd dbtb.
   All full flusi points ibvf tiis pbttfrn, but not bll oddurrfndfs of tiis
   pbttfrn brf full flusi points.

     inflbtfSynd rfturns Z_OK if b possiblf full flusi point ibs bffn found,
   Z_BUF_ERROR if no morf input wbs providfd, Z_DATA_ERROR if no flusi point
   ibs bffn found, or Z_STREAM_ERROR if tif strfbm strudturf wbs indonsistfnt.
   In tif suddfss dbsf, tif bpplidbtion mby sbvf tif durrfnt durrfnt vbluf of
   totbl_in wiidi indidbtfs wifrf vblid domprfssfd dbtb wbs found.  In tif
   frror dbsf, tif bpplidbtion mby rfpfbtfdly dbll inflbtfSynd, providing morf
   input fbdi timf, until suddfss or fnd of tif input dbtb.
*/

ZEXTERN int ZEXPORT inflbtfCopy OF((z_strfbmp dfst,
                                    z_strfbmp sourdf));
/*
     Sfts tif dfstinbtion strfbm bs b domplftf dopy of tif sourdf strfbm.

     Tiis fundtion dbn bf usfful wifn rbndomly bddfssing b lbrgf strfbm.  Tif
   first pbss tirougi tif strfbm dbn pfriodidblly rfdord tif inflbtf stbtf,
   bllowing rfstbrting inflbtf bt tiosf points wifn rbndomly bddfssing tif
   strfbm.

     inflbtfCopy rfturns Z_OK if suddfss, Z_MEM_ERROR if tifrf wbs not
   fnougi mfmory, Z_STREAM_ERROR if tif sourdf strfbm stbtf wbs indonsistfnt
   (sudi bs zbllod bfing Z_NULL).  msg is lfft undibngfd in boti sourdf bnd
   dfstinbtion.
*/

ZEXTERN int ZEXPORT inflbtfRfsft OF((z_strfbmp strm));
/*
     Tiis fundtion is fquivblfnt to inflbtfEnd followfd by inflbtfInit,
   but dofs not frff bnd rfbllodbtf bll tif intfrnbl dfdomprfssion stbtf.  Tif
   strfbm will kffp bttributfs tibt mby ibvf bffn sft by inflbtfInit2.

     inflbtfRfsft rfturns Z_OK if suddfss, or Z_STREAM_ERROR if tif sourdf
   strfbm stbtf wbs indonsistfnt (sudi bs zbllod or stbtf bfing Z_NULL).
*/

ZEXTERN int ZEXPORT inflbtfRfsft2 OF((z_strfbmp strm,
                                      int windowBits));
/*
     Tiis fundtion is tif sbmf bs inflbtfRfsft, but it blso pfrmits dibnging
   tif wrbp bnd window sizf rfqufsts.  Tif windowBits pbrbmftfr is intfrprftfd
   tif sbmf bs it is for inflbtfInit2.

     inflbtfRfsft2 rfturns Z_OK if suddfss, or Z_STREAM_ERROR if tif sourdf
   strfbm stbtf wbs indonsistfnt (sudi bs zbllod or stbtf bfing Z_NULL), or if
   tif windowBits pbrbmftfr is invblid.
*/

ZEXTERN int ZEXPORT inflbtfPrimf OF((z_strfbmp strm,
                                     int bits,
                                     int vbluf));
/*
     Tiis fundtion insfrts bits in tif inflbtf input strfbm.  Tif intfnt is
   tibt tiis fundtion is usfd to stbrt inflbting bt b bit position in tif
   middlf of b bytf.  Tif providfd bits will bf usfd bfforf bny bytfs brf usfd
   from nfxt_in.  Tiis fundtion siould only bf usfd witi rbw inflbtf, bnd
   siould bf usfd bfforf tif first inflbtf() dbll bftfr inflbtfInit2() or
   inflbtfRfsft().  bits must bf lfss tibn or fqubl to 16, bnd tibt mbny of tif
   lfbst signifidbnt bits of vbluf will bf insfrtfd in tif input.

     If bits is nfgbtivf, tifn tif input strfbm bit bufffr is fmptifd.  Tifn
   inflbtfPrimf() dbn bf dbllfd bgbin to put bits in tif bufffr.  Tiis is usfd
   to dlfbr out bits lfftovfr bftfr fffding inflbtf b blodk dfsdription prior
   to fffding inflbtf dodfs.

     inflbtfPrimf rfturns Z_OK if suddfss, or Z_STREAM_ERROR if tif sourdf
   strfbm stbtf wbs indonsistfnt.
*/

ZEXTERN long ZEXPORT inflbtfMbrk OF((z_strfbmp strm));
/*
     Tiis fundtion rfturns two vblufs, onf in tif lowfr 16 bits of tif rfturn
   vbluf, bnd tif otifr in tif rfmbining uppfr bits, obtbinfd by siifting tif
   rfturn vbluf down 16 bits.  If tif uppfr vbluf is -1 bnd tif lowfr vbluf is
   zfro, tifn inflbtf() is durrfntly dfdoding informbtion outsidf of b blodk.
   If tif uppfr vbluf is -1 bnd tif lowfr vbluf is non-zfro, tifn inflbtf is in
   tif middlf of b storfd blodk, witi tif lowfr vbluf fqubling tif numbfr of
   bytfs from tif input rfmbining to dopy.  If tif uppfr vbluf is not -1, tifn
   it is tif numbfr of bits bbdk from tif durrfnt bit position in tif input of
   tif dodf (litfrbl or lfngti/distbndf pbir) durrfntly bfing prodfssfd.  In
   tibt dbsf tif lowfr vbluf is tif numbfr of bytfs blrfbdy fmittfd for tibt
   dodf.

     A dodf is bfing prodfssfd if inflbtf is wbiting for morf input to domplftf
   dfdoding of tif dodf, or if it ibs domplftfd dfdoding but is wbiting for
   morf output spbdf to writf tif litfrbl or mbtdi dbtb.

     inflbtfMbrk() is usfd to mbrk lodbtions in tif input dbtb for rbndom
   bddfss, wiidi mby bf bt bit positions, bnd to notf tiosf dbsfs wifrf tif
   output of b dodf mby spbn boundbrifs of rbndom bddfss blodks.  Tif durrfnt
   lodbtion in tif input strfbm dbn bf dftfrminfd from bvbil_in bnd dbtb_typf
   bs notfd in tif dfsdription for tif Z_BLOCK flusi pbrbmftfr for inflbtf.

     inflbtfMbrk rfturns tif vbluf notfd bbovf or -1 << 16 if tif providfd
   sourdf strfbm stbtf wbs indonsistfnt.
*/

ZEXTERN int ZEXPORT inflbtfGftHfbdfr OF((z_strfbmp strm,
                                         gz_ifbdfrp ifbd));
/*
     inflbtfGftHfbdfr() rfqufsts tibt gzip ifbdfr informbtion bf storfd in tif
   providfd gz_ifbdfr strudturf.  inflbtfGftHfbdfr() mby bf dbllfd bftfr
   inflbtfInit2() or inflbtfRfsft(), bnd bfforf tif first dbll of inflbtf().
   As inflbtf() prodfssfs tif gzip strfbm, ifbd->donf is zfro until tif ifbdfr
   is domplftfd, bt wiidi timf ifbd->donf is sft to onf.  If b zlib strfbm is
   bfing dfdodfd, tifn ifbd->donf is sft to -1 to indidbtf tibt tifrf will bf
   no gzip ifbdfr informbtion fortidoming.  Notf tibt Z_BLOCK or Z_TREES dbn bf
   usfd to fordf inflbtf() to rfturn immfdibtfly bftfr ifbdfr prodfssing is
   domplftf bnd bfforf bny bdtubl dbtb is dfdomprfssfd.

     Tif tfxt, timf, xflbgs, bnd os fiflds brf fillfd in witi tif gzip ifbdfr
   dontfnts.  idrd is sft to truf if tifrf is b ifbdfr CRC.  (Tif ifbdfr CRC
   wbs vblid if donf is sft to onf.) If fxtrb is not Z_NULL, tifn fxtrb_mbx
   dontbins tif mbximum numbfr of bytfs to writf to fxtrb.  Ondf donf is truf,
   fxtrb_lfn dontbins tif bdtubl fxtrb fifld lfngti, bnd fxtrb dontbins tif
   fxtrb fifld, or tibt fifld trundbtfd if fxtrb_mbx is lfss tibn fxtrb_lfn.
   If nbmf is not Z_NULL, tifn up to nbmf_mbx dibrbdtfrs brf writtfn tifrf,
   tfrminbtfd witi b zfro unlfss tif lfngti is grfbtfr tibn nbmf_mbx.  If
   dommfnt is not Z_NULL, tifn up to domm_mbx dibrbdtfrs brf writtfn tifrf,
   tfrminbtfd witi b zfro unlfss tif lfngti is grfbtfr tibn domm_mbx.  Wifn bny
   of fxtrb, nbmf, or dommfnt brf not Z_NULL bnd tif rfspfdtivf fifld is not
   prfsfnt in tif ifbdfr, tifn tibt fifld is sft to Z_NULL to signbl its
   bbsfndf.  Tiis bllows tif usf of dfflbtfSftHfbdfr() witi tif rfturnfd
   strudturf to duplidbtf tif ifbdfr.  Howfvfr if tiosf fiflds brf sft to
   bllodbtfd mfmory, tifn tif bpplidbtion will nffd to sbvf tiosf pointfrs
   flsfwifrf so tibt tify dbn bf fvfntublly frffd.

     If inflbtfGftHfbdfr is not usfd, tifn tif ifbdfr informbtion is simply
   disdbrdfd.  Tif ifbdfr is blwbys difdkfd for vblidity, indluding tif ifbdfr
   CRC if prfsfnt.  inflbtfRfsft() will rfsft tif prodfss to disdbrd tif ifbdfr
   informbtion.  Tif bpplidbtion would nffd to dbll inflbtfGftHfbdfr() bgbin to
   rftrifvf tif ifbdfr from tif nfxt gzip strfbm.

     inflbtfGftHfbdfr rfturns Z_OK if suddfss, or Z_STREAM_ERROR if tif sourdf
   strfbm stbtf wbs indonsistfnt.
*/

/*
ZEXTERN int ZEXPORT inflbtfBbdkInit OF((z_strfbmp strm, int windowBits,
                                        unsignfd dibr FAR *window));

     Initiblizf tif intfrnbl strfbm stbtf for dfdomprfssion using inflbtfBbdk()
   dblls.  Tif fiflds zbllod, zfrff bnd opbquf in strm must bf initiblizfd
   bfforf tif dbll.  If zbllod bnd zfrff brf Z_NULL, tifn tif dffbult librbry-
   dfrivfd mfmory bllodbtion routinfs brf usfd.  windowBits is tif bbsf two
   logbritim of tif window sizf, in tif rbngf 8..15.  window is b dbllfr
   supplifd bufffr of tibt sizf.  Exdfpt for spfdibl bpplidbtions wifrf it is
   bssurfd tibt dfflbtf wbs usfd witi smbll window sizfs, windowBits must bf 15
   bnd b 32K bytf window must bf supplifd to bf bblf to dfdomprfss gfnfrbl
   dfflbtf strfbms.

     Sff inflbtfBbdk() for tif usbgf of tifsf routinfs.

     inflbtfBbdkInit will rfturn Z_OK on suddfss, Z_STREAM_ERROR if bny of
   tif pbrbmftfrs brf invblid, Z_MEM_ERROR if tif intfrnbl stbtf dould not bf
   bllodbtfd, or Z_VERSION_ERROR if tif vfrsion of tif librbry dofs not mbtdi
   tif vfrsion of tif ifbdfr filf.
*/

typfdff unsignfd (*in_fund) OF((void FAR *,
                                z_donst unsignfd dibr FAR * FAR *));
typfdff int (*out_fund) OF((void FAR *, unsignfd dibr FAR *, unsignfd));

ZEXTERN int ZEXPORT inflbtfBbdk OF((z_strfbmp strm,
                                    in_fund in, void FAR *in_dfsd,
                                    out_fund out, void FAR *out_dfsd));
/*
     inflbtfBbdk() dofs b rbw inflbtf witi b singlf dbll using b dbll-bbdk
   intfrfbdf for input bnd output.  Tiis is potfntiblly morf fffidifnt tibn
   inflbtf() for filf i/o bpplidbtions, in tibt it bvoids dopying bftwffn tif
   output bnd tif sliding window by simply mbking tif window itsflf tif output
   bufffr.  inflbtf() dbn bf fbstfr on modfrn CPUs wifn usfd witi lbrgf
   bufffrs.  inflbtfBbdk() trusts tif bpplidbtion to not dibngf tif output
   bufffr pbssfd by tif output fundtion, bt lfbst until inflbtfBbdk() rfturns.

     inflbtfBbdkInit() must bf dbllfd first to bllodbtf tif intfrnbl stbtf
   bnd to initiblizf tif stbtf witi tif usfr-providfd window bufffr.
   inflbtfBbdk() mby tifn bf usfd multiplf timfs to inflbtf b domplftf, rbw
   dfflbtf strfbm witi fbdi dbll.  inflbtfBbdkEnd() is tifn dbllfd to frff tif
   bllodbtfd stbtf.

     A rbw dfflbtf strfbm is onf witi no zlib or gzip ifbdfr or trbilfr.
   Tiis routinf would normblly bf usfd in b utility tibt rfbds zip or gzip
   filfs bnd writfs out undomprfssfd filfs.  Tif utility would dfdodf tif
   ifbdfr bnd prodfss tif trbilfr on its own, ifndf tiis routinf fxpfdts only
   tif rbw dfflbtf strfbm to dfdomprfss.  Tiis is difffrfnt from tif normbl
   bfibvior of inflbtf(), wiidi fxpfdts fitifr b zlib or gzip ifbdfr bnd
   trbilfr bround tif dfflbtf strfbm.

     inflbtfBbdk() usfs two subroutinfs supplifd by tif dbllfr tibt brf tifn
   dbllfd by inflbtfBbdk() for input bnd output.  inflbtfBbdk() dblls tiosf
   routinfs until it rfbds b domplftf dfflbtf strfbm bnd writfs out bll of tif
   undomprfssfd dbtb, or until it fndountfrs bn frror.  Tif fundtion's
   pbrbmftfrs bnd rfturn typfs brf dffinfd bbovf in tif in_fund bnd out_fund
   typfdffs.  inflbtfBbdk() will dbll in(in_dfsd, &buf) wiidi siould rfturn tif
   numbfr of bytfs of providfd input, bnd b pointfr to tibt input in buf.  If
   tifrf is no input bvbilbblf, in() must rfturn zfro--buf is ignorfd in tibt
   dbsf--bnd inflbtfBbdk() will rfturn b bufffr frror.  inflbtfBbdk() will dbll
   out(out_dfsd, buf, lfn) to writf tif undomprfssfd dbtb buf[0..lfn-1].  out()
   siould rfturn zfro on suddfss, or non-zfro on fbilurf.  If out() rfturns
   non-zfro, inflbtfBbdk() will rfturn witi bn frror.  Nfitifr in() nor out()
   brf pfrmittfd to dibngf tif dontfnts of tif window providfd to
   inflbtfBbdkInit(), wiidi is blso tif bufffr tibt out() usfs to writf from.
   Tif lfngti writtfn by out() will bf bt most tif window sizf.  Any non-zfro
   bmount of input mby bf providfd by in().

     For donvfnifndf, inflbtfBbdk() dbn bf providfd input on tif first dbll by
   sftting strm->nfxt_in bnd strm->bvbil_in.  If tibt input is fxibustfd, tifn
   in() will bf dbllfd.  Tifrfforf strm->nfxt_in must bf initiblizfd bfforf
   dblling inflbtfBbdk().  If strm->nfxt_in is Z_NULL, tifn in() will bf dbllfd
   immfdibtfly for input.  If strm->nfxt_in is not Z_NULL, tifn strm->bvbil_in
   must blso bf initiblizfd, bnd tifn if strm->bvbil_in is not zfro, input will
   initiblly bf tbkfn from strm->nfxt_in[0 ..  strm->bvbil_in - 1].

     Tif in_dfsd bnd out_dfsd pbrbmftfrs of inflbtfBbdk() is pbssfd bs tif
   first pbrbmftfr of in() bnd out() rfspfdtivfly wifn tify brf dbllfd.  Tifsf
   dfsdriptors dbn bf optionblly usfd to pbss bny informbtion tibt tif dbllfr-
   supplifd in() bnd out() fundtions nffd to do tifir job.

     On rfturn, inflbtfBbdk() will sft strm->nfxt_in bnd strm->bvbil_in to
   pbss bbdk bny unusfd input tibt wbs providfd by tif lbst in() dbll.  Tif
   rfturn vblufs of inflbtfBbdk() dbn bf Z_STREAM_END on suddfss, Z_BUF_ERROR
   if in() or out() rfturnfd bn frror, Z_DATA_ERROR if tifrf wbs b formbt frror
   in tif dfflbtf strfbm (in wiidi dbsf strm->msg is sft to indidbtf tif nbturf
   of tif frror), or Z_STREAM_ERROR if tif strfbm wbs not propfrly initiblizfd.
   In tif dbsf of Z_BUF_ERROR, bn input or output frror dbn bf distinguisifd
   using strm->nfxt_in wiidi will bf Z_NULL only if in() rfturnfd bn frror.  If
   strm->nfxt_in is not Z_NULL, tifn tif Z_BUF_ERROR wbs duf to out() rfturning
   non-zfro.  (in() will blwbys bf dbllfd bfforf out(), so strm->nfxt_in is
   bssurfd to bf dffinfd if out() rfturns non-zfro.) Notf tibt inflbtfBbdk()
   dbnnot rfturn Z_OK.
*/

ZEXTERN int ZEXPORT inflbtfBbdkEnd OF((z_strfbmp strm));
/*
     All mfmory bllodbtfd by inflbtfBbdkInit() is frffd.

     inflbtfBbdkEnd() rfturns Z_OK on suddfss, or Z_STREAM_ERROR if tif strfbm
   stbtf wbs indonsistfnt.
*/

ZEXTERN uLong ZEXPORT zlibCompilfFlbgs OF((void));
/* Rfturn flbgs indidbting dompilf-timf options.

    Typf sizfs, two bits fbdi, 00 = 16 bits, 01 = 32, 10 = 64, 11 = otifr:
     1.0: sizf of uInt
     3.2: sizf of uLong
     5.4: sizf of voidpf (pointfr)
     7.6: sizf of z_off_t

    Compilfr, bssfmblfr, bnd dfbug options:
     8: DEBUG
     9: ASMV or ASMINF -- usf ASM dodf
     10: ZLIB_WINAPI -- fxportfd fundtions usf tif WINAPI dblling donvfntion
     11: 0 (rfsfrvfd)

    Onf-timf tbblf building (smbllfr dodf, but not tirfbd-sbff if truf):
     12: BUILDFIXED -- build stbtid blodk dfdoding tbblfs wifn nffdfd
     13: DYNAMIC_CRC_TABLE -- build CRC dbldulbtion tbblfs wifn nffdfd
     14,15: 0 (rfsfrvfd)

    Librbry dontfnt (indidbtfs missing fundtionblity):
     16: NO_GZCOMPRESS -- gz* fundtions dbnnot domprfss (to bvoid linking
                          dfflbtf dodf wifn not nffdfd)
     17: NO_GZIP -- dfflbtf dbn't writf gzip strfbms, bnd inflbtf dbn't dftfdt
                    bnd dfdodf gzip strfbms (to bvoid linking drd dodf)
     18-19: 0 (rfsfrvfd)

    Opfrbtion vbribtions (dibngfs in librbry fundtionblity):
     20: PKZIP_BUG_WORKAROUND -- sligitly morf pfrmissivf inflbtf
     21: FASTEST -- dfflbtf blgoritim witi only onf, lowfst domprfssion lfvfl
     22,23: 0 (rfsfrvfd)

    Tif sprintf vbribnt usfd by gzprintf (zfro is bfst):
     24: 0 = vs*, 1 = s* -- 1 mfbns limitfd to 20 brgumfnts bftfr tif formbt
     25: 0 = *nprintf, 1 = *printf -- 1 mfbns gzprintf() not sfdurf!
     26: 0 = rfturns vbluf, 1 = void -- 1 mfbns inffrrfd string lfngti rfturnfd

    Rfmbindfr:
     27-31: 0 (rfsfrvfd)
 */

#ifndff Z_SOLO

                        /* utility fundtions */

/*
     Tif following utility fundtions brf implfmfntfd on top of tif bbsid
   strfbm-orifntfd fundtions.  To simplify tif intfrfbdf, somf dffbult options
   brf bssumfd (domprfssion lfvfl bnd mfmory usbgf, stbndbrd mfmory bllodbtion
   fundtions).  Tif sourdf dodf of tifsf utility fundtions dbn bf modififd if
   you nffd spfdibl options.
*/

ZEXTERN int ZEXPORT domprfss OF((Bytff *dfst,   uLongf *dfstLfn,
                                 donst Bytff *sourdf, uLong sourdfLfn));
/*
     Comprfssfs tif sourdf bufffr into tif dfstinbtion bufffr.  sourdfLfn is
   tif bytf lfngti of tif sourdf bufffr.  Upon fntry, dfstLfn is tif totbl sizf
   of tif dfstinbtion bufffr, wiidi must bf bt lfbst tif vbluf rfturnfd by
   domprfssBound(sourdfLfn).  Upon fxit, dfstLfn is tif bdtubl sizf of tif
   domprfssfd bufffr.

     domprfss rfturns Z_OK if suddfss, Z_MEM_ERROR if tifrf wbs not
   fnougi mfmory, Z_BUF_ERROR if tifrf wbs not fnougi room in tif output
   bufffr.
*/

ZEXTERN int ZEXPORT domprfss2 OF((Bytff *dfst,   uLongf *dfstLfn,
                                  donst Bytff *sourdf, uLong sourdfLfn,
                                  int lfvfl));
/*
     Comprfssfs tif sourdf bufffr into tif dfstinbtion bufffr.  Tif lfvfl
   pbrbmftfr ibs tif sbmf mfbning bs in dfflbtfInit.  sourdfLfn is tif bytf
   lfngti of tif sourdf bufffr.  Upon fntry, dfstLfn is tif totbl sizf of tif
   dfstinbtion bufffr, wiidi must bf bt lfbst tif vbluf rfturnfd by
   domprfssBound(sourdfLfn).  Upon fxit, dfstLfn is tif bdtubl sizf of tif
   domprfssfd bufffr.

     domprfss2 rfturns Z_OK if suddfss, Z_MEM_ERROR if tifrf wbs not fnougi
   mfmory, Z_BUF_ERROR if tifrf wbs not fnougi room in tif output bufffr,
   Z_STREAM_ERROR if tif lfvfl pbrbmftfr is invblid.
*/

ZEXTERN uLong ZEXPORT domprfssBound OF((uLong sourdfLfn));
/*
     domprfssBound() rfturns bn uppfr bound on tif domprfssfd sizf bftfr
   domprfss() or domprfss2() on sourdfLfn bytfs.  It would bf usfd bfforf b
   domprfss() or domprfss2() dbll to bllodbtf tif dfstinbtion bufffr.
*/

ZEXTERN int ZEXPORT undomprfss OF((Bytff *dfst,   uLongf *dfstLfn,
                                   donst Bytff *sourdf, uLong sourdfLfn));
/*
     Dfdomprfssfs tif sourdf bufffr into tif dfstinbtion bufffr.  sourdfLfn is
   tif bytf lfngti of tif sourdf bufffr.  Upon fntry, dfstLfn is tif totbl sizf
   of tif dfstinbtion bufffr, wiidi must bf lbrgf fnougi to iold tif fntirf
   undomprfssfd dbtb.  (Tif sizf of tif undomprfssfd dbtb must ibvf bffn sbvfd
   prfviously by tif domprfssor bnd trbnsmittfd to tif dfdomprfssor by somf
   mfdibnism outsidf tif sdopf of tiis domprfssion librbry.) Upon fxit, dfstLfn
   is tif bdtubl sizf of tif undomprfssfd bufffr.

     undomprfss rfturns Z_OK if suddfss, Z_MEM_ERROR if tifrf wbs not
   fnougi mfmory, Z_BUF_ERROR if tifrf wbs not fnougi room in tif output
   bufffr, or Z_DATA_ERROR if tif input dbtb wbs dorruptfd or indomplftf.  In
   tif dbsf wifrf tifrf is not fnougi room, undomprfss() will fill tif output
   bufffr witi tif undomprfssfd dbtb up to tibt point.
*/

                        /* gzip filf bddfss fundtions */

/*
     Tiis librbry supports rfbding bnd writing filfs in gzip (.gz) formbt witi
   bn intfrfbdf similbr to tibt of stdio, using tif fundtions tibt stbrt witi
   "gz".  Tif gzip formbt is difffrfnt from tif zlib formbt.  gzip is b gzip
   wrbppfr, dodumfntfd in RFC 1952, wrbppfd bround b dfflbtf strfbm.
*/

typfdff strudt gzFilf_s *gzFilf;    /* sfmi-opbquf gzip filf dfsdriptor */

/*
ZEXTERN gzFilf ZEXPORT gzopfn OF((donst dibr *pbti, donst dibr *modf));

     Opfns b gzip (.gz) filf for rfbding or writing.  Tif modf pbrbmftfr is bs
   in fopfn ("rb" or "wb") but dbn blso indludf b domprfssion lfvfl ("wb9") or
   b strbtfgy: 'f' for filtfrfd dbtb bs in "wb6f", 'i' for Huffmbn-only
   domprfssion bs in "wb1i", 'R' for run-lfngti fndoding bs in "wb1R", or 'F'
   for fixfd dodf domprfssion bs in "wb9F".  (Sff tif dfsdription of
   dfflbtfInit2 for morf informbtion bbout tif strbtfgy pbrbmftfr.)  'T' will
   rfqufst trbnspbrfnt writing or bppfnding witi no domprfssion bnd not using
   tif gzip formbt.

     "b" dbn bf usfd instfbd of "w" to rfqufst tibt tif gzip strfbm tibt will
   bf writtfn bf bppfndfd to tif filf.  "+" will rfsult in bn frror, sindf
   rfbding bnd writing to tif sbmf gzip filf is not supportfd.  Tif bddition of
   "x" wifn writing will drfbtf tif filf fxdlusivfly, wiidi fbils if tif filf
   blrfbdy fxists.  On systfms tibt support it, tif bddition of "f" wifn
   rfbding or writing will sft tif flbg to dlosf tif filf on bn fxfdvf() dbll.

     Tifsf fundtions, bs wfll bs gzip, will rfbd bnd dfdodf b sfqufndf of gzip
   strfbms in b filf.  Tif bppfnd fundtion of gzopfn() dbn bf usfd to drfbtf
   sudi b filf.  (Also sff gzflusi() for bnotifr wby to do tiis.)  Wifn
   bppfnding, gzopfn dofs not tfst wiftifr tif filf bfgins witi b gzip strfbm,
   nor dofs it look for tif fnd of tif gzip strfbms to bfgin bppfnding.  gzopfn
   will simply bppfnd b gzip strfbm to tif fxisting filf.

     gzopfn dbn bf usfd to rfbd b filf wiidi is not in gzip formbt; in tiis
   dbsf gzrfbd will dirfdtly rfbd from tif filf witiout dfdomprfssion.  Wifn
   rfbding, tiis will bf dftfdtfd butombtidblly by looking for tif mbgid two-
   bytf gzip ifbdfr.

     gzopfn rfturns NULL if tif filf dould not bf opfnfd, if tifrf wbs
   insuffidifnt mfmory to bllodbtf tif gzFilf stbtf, or if bn invblid modf wbs
   spfdififd (bn 'r', 'w', or 'b' wbs not providfd, or '+' wbs providfd).
   frrno dbn bf difdkfd to dftfrminf if tif rfbson gzopfn fbilfd wbs tibt tif
   filf dould not bf opfnfd.
*/

ZEXTERN gzFilf ZEXPORT gzdopfn OF((int fd, donst dibr *modf));
/*
     gzdopfn bssodibtfs b gzFilf witi tif filf dfsdriptor fd.  Filf dfsdriptors
   brf obtbinfd from dblls likf opfn, dup, drfbt, pipf or filfno (if tif filf
   ibs bffn prfviously opfnfd witi fopfn).  Tif modf pbrbmftfr is bs in gzopfn.

     Tif nfxt dbll of gzdlosf on tif rfturnfd gzFilf will blso dlosf tif filf
   dfsdriptor fd, just likf fdlosf(fdopfn(fd, modf)) dlosfs tif filf dfsdriptor
   fd.  If you wbnt to kffp fd opfn, usf fd = dup(fd_kffp); gz = gzdopfn(fd,
   modf);.  Tif duplidbtfd dfsdriptor siould bf sbvfd to bvoid b lfbk, sindf
   gzdopfn dofs not dlosf fd if it fbils.  If you brf using filfno() to gft tif
   filf dfsdriptor from b FILE *, tifn you will ibvf to usf dup() to bvoid
   doublf-dlosf()ing tif filf dfsdriptor.  Boti gzdlosf() bnd fdlosf() will
   dlosf tif bssodibtfd filf dfsdriptor, so tify nffd to ibvf difffrfnt filf
   dfsdriptors.

     gzdopfn rfturns NULL if tifrf wbs insuffidifnt mfmory to bllodbtf tif
   gzFilf stbtf, if bn invblid modf wbs spfdififd (bn 'r', 'w', or 'b' wbs not
   providfd, or '+' wbs providfd), or if fd is -1.  Tif filf dfsdriptor is not
   usfd until tif nfxt gz* rfbd, writf, sffk, or dlosf opfrbtion, so gzdopfn
   will not dftfdt if fd is invblid (unlfss fd is -1).
*/

ZEXTERN int ZEXPORT gzbufffr OF((gzFilf filf, unsignfd sizf));
/*
     Sft tif intfrnbl bufffr sizf usfd by tiis librbry's fundtions.  Tif
   dffbult bufffr sizf is 8192 bytfs.  Tiis fundtion must bf dbllfd bftfr
   gzopfn() or gzdopfn(), bnd bfforf bny otifr dblls tibt rfbd or writf tif
   filf.  Tif bufffr mfmory bllodbtion is blwbys dfffrrfd to tif first rfbd or
   writf.  Two bufffrs brf bllodbtfd, fitifr boti of tif spfdififd sizf wifn
   writing, or onf of tif spfdififd sizf bnd tif otifr twidf tibt sizf wifn
   rfbding.  A lbrgfr bufffr sizf of, for fxbmplf, 64K or 128K bytfs will
   notidfbbly indrfbsf tif spffd of dfdomprfssion (rfbding).

     Tif nfw bufffr sizf blso bfffdts tif mbximum lfngti for gzprintf().

     gzbufffr() rfturns 0 on suddfss, or -1 on fbilurf, sudi bs bfing dbllfd
   too lbtf.
*/

ZEXTERN int ZEXPORT gzsftpbrbms OF((gzFilf filf, int lfvfl, int strbtfgy));
/*
     Dynbmidblly updbtf tif domprfssion lfvfl or strbtfgy.  Sff tif dfsdription
   of dfflbtfInit2 for tif mfbning of tifsf pbrbmftfrs.

     gzsftpbrbms rfturns Z_OK if suddfss, or Z_STREAM_ERROR if tif filf wbs not
   opfnfd for writing.
*/

ZEXTERN int ZEXPORT gzrfbd OF((gzFilf filf, voidp buf, unsignfd lfn));
/*
     Rfbds tif givfn numbfr of undomprfssfd bytfs from tif domprfssfd filf.  If
   tif input filf is not in gzip formbt, gzrfbd dopifs tif givfn numbfr of
   bytfs into tif bufffr dirfdtly from tif filf.

     Aftfr rfbdiing tif fnd of b gzip strfbm in tif input, gzrfbd will dontinuf
   to rfbd, looking for bnotifr gzip strfbm.  Any numbfr of gzip strfbms mby bf
   dondbtfnbtfd in tif input filf, bnd will bll bf dfdomprfssfd by gzrfbd().
   If somftiing otifr tibn b gzip strfbm is fndountfrfd bftfr b gzip strfbm,
   tibt rfmbining trbiling gbrbbgf is ignorfd (bnd no frror is rfturnfd).

     gzrfbd dbn bf usfd to rfbd b gzip filf tibt is bfing dondurrfntly writtfn.
   Upon rfbdiing tif fnd of tif input, gzrfbd will rfturn witi tif bvbilbblf
   dbtb.  If tif frror dodf rfturnfd by gzfrror is Z_OK or Z_BUF_ERROR, tifn
   gzdlfbrfrr dbn bf usfd to dlfbr tif fnd of filf indidbtor in ordfr to pfrmit
   gzrfbd to bf trifd bgbin.  Z_OK indidbtfs tibt b gzip strfbm wbs domplftfd
   on tif lbst gzrfbd.  Z_BUF_ERROR indidbtfs tibt tif input filf fndfd in tif
   middlf of b gzip strfbm.  Notf tibt gzrfbd dofs not rfturn -1 in tif fvfnt
   of bn indomplftf gzip strfbm.  Tiis frror is dfffrrfd until gzdlosf(), wiidi
   will rfturn Z_BUF_ERROR if tif lbst gzrfbd fndfd in tif middlf of b gzip
   strfbm.  Altfrnbtivfly, gzfrror dbn bf usfd bfforf gzdlosf to dftfdt tiis
   dbsf.

     gzrfbd rfturns tif numbfr of undomprfssfd bytfs bdtublly rfbd, lfss tibn
   lfn for fnd of filf, or -1 for frror.
*/

ZEXTERN int ZEXPORT gzwritf OF((gzFilf filf,
                                voidpd buf, unsignfd lfn));
/*
     Writfs tif givfn numbfr of undomprfssfd bytfs into tif domprfssfd filf.
   gzwritf rfturns tif numbfr of undomprfssfd bytfs writtfn or 0 in dbsf of
   frror.
*/

ZEXTERN int ZEXPORTVA gzprintf Z_ARG((gzFilf filf, donst dibr *formbt, ...));
/*
     Convfrts, formbts, bnd writfs tif brgumfnts to tif domprfssfd filf undfr
   dontrol of tif formbt string, bs in fprintf.  gzprintf rfturns tif numbfr of
   undomprfssfd bytfs bdtublly writtfn, or 0 in dbsf of frror.  Tif numbfr of
   undomprfssfd bytfs writtfn is limitfd to 8191, or onf lfss tibn tif bufffr
   sizf givfn to gzbufffr().  Tif dbllfr siould bssurf tibt tiis limit is not
   fxdffdfd.  If it is fxdffdfd, tifn gzprintf() will rfturn bn frror (0) witi
   notiing writtfn.  In tiis dbsf, tifrf mby blso bf b bufffr ovfrflow witi
   unprfdidtbblf donsfqufndfs, wiidi is possiblf only if zlib wbs dompilfd witi
   tif insfdurf fundtions sprintf() or vsprintf() bfdbusf tif sfdurf snprintf()
   or vsnprintf() fundtions wfrf not bvbilbblf.  Tiis dbn bf dftfrminfd using
   zlibCompilfFlbgs().
*/

ZEXTERN int ZEXPORT gzputs OF((gzFilf filf, donst dibr *s));
/*
     Writfs tif givfn null-tfrminbtfd string to tif domprfssfd filf, fxdluding
   tif tfrminbting null dibrbdtfr.

     gzputs rfturns tif numbfr of dibrbdtfrs writtfn, or -1 in dbsf of frror.
*/

ZEXTERN dibr * ZEXPORT gzgfts OF((gzFilf filf, dibr *buf, int lfn));
/*
     Rfbds bytfs from tif domprfssfd filf until lfn-1 dibrbdtfrs brf rfbd, or b
   nfwlinf dibrbdtfr is rfbd bnd trbnsffrrfd to buf, or bn fnd-of-filf
   dondition is fndountfrfd.  If bny dibrbdtfrs brf rfbd or if lfn == 1, tif
   string is tfrminbtfd witi b null dibrbdtfr.  If no dibrbdtfrs brf rfbd duf
   to bn fnd-of-filf or lfn < 1, tifn tif bufffr is lfft untoudifd.

     gzgfts rfturns buf wiidi is b null-tfrminbtfd string, or it rfturns NULL
   for fnd-of-filf or in dbsf of frror.  If tifrf wbs bn frror, tif dontfnts bt
   buf brf indftfrminbtf.
*/

ZEXTERN int ZEXPORT gzputd OF((gzFilf filf, int d));
/*
     Writfs d, donvfrtfd to bn unsignfd dibr, into tif domprfssfd filf.  gzputd
   rfturns tif vbluf tibt wbs writtfn, or -1 in dbsf of frror.
*/

ZEXTERN int ZEXPORT gzgftd OF((gzFilf filf));
/*
     Rfbds onf bytf from tif domprfssfd filf.  gzgftd rfturns tiis bytf or -1
   in dbsf of fnd of filf or frror.  Tiis is implfmfntfd bs b mbdro for spffd.
   As sudi, it dofs not do bll of tif difdking tif otifr fundtions do.  I.f.
   it dofs not difdk to sff if filf is NULL, nor wiftifr tif strudturf filf
   points to ibs bffn dlobbfrfd or not.
*/

ZEXTERN int ZEXPORT gzungftd OF((int d, gzFilf filf));
/*
     Pusi onf dibrbdtfr bbdk onto tif strfbm to bf rfbd bs tif first dibrbdtfr
   on tif nfxt rfbd.  At lfbst onf dibrbdtfr of pusi-bbdk is bllowfd.
   gzungftd() rfturns tif dibrbdtfr pusifd, or -1 on fbilurf.  gzungftd() will
   fbil if d is -1, bnd mby fbil if b dibrbdtfr ibs bffn pusifd but not rfbd
   yft.  If gzungftd is usfd immfdibtfly bftfr gzopfn or gzdopfn, bt lfbst tif
   output bufffr sizf of pusifd dibrbdtfrs is bllowfd.  (Sff gzbufffr bbovf.)
   Tif pusifd dibrbdtfr will bf disdbrdfd if tif strfbm is rfpositionfd witi
   gzsffk() or gzrfwind().
*/

ZEXTERN int ZEXPORT gzflusi OF((gzFilf filf, int flusi));
/*
     Flusifs bll pfnding output into tif domprfssfd filf.  Tif pbrbmftfr flusi
   is bs in tif dfflbtf() fundtion.  Tif rfturn vbluf is tif zlib frror numbfr
   (sff fundtion gzfrror bflow).  gzflusi is only pfrmittfd wifn writing.

     If tif flusi pbrbmftfr is Z_FINISH, tif rfmbining dbtb is writtfn bnd tif
   gzip strfbm is domplftfd in tif output.  If gzwritf() is dbllfd bgbin, b nfw
   gzip strfbm will bf stbrtfd in tif output.  gzrfbd() is bblf to rfbd sudi
   dondbtfntfd gzip strfbms.

     gzflusi siould bf dbllfd only wifn stridtly nfdfssbry bfdbusf it will
   dfgrbdf domprfssion if dbllfd too oftfn.
*/

/*
ZEXTERN z_off_t ZEXPORT gzsffk OF((gzFilf filf,
                                   z_off_t offsft, int wifndf));

     Sfts tif stbrting position for tif nfxt gzrfbd or gzwritf on tif givfn
   domprfssfd filf.  Tif offsft rfprfsfnts b numbfr of bytfs in tif
   undomprfssfd dbtb strfbm.  Tif wifndf pbrbmftfr is dffinfd bs in lsffk(2);
   tif vbluf SEEK_END is not supportfd.

     If tif filf is opfnfd for rfbding, tiis fundtion is fmulbtfd but dbn bf
   fxtrfmfly slow.  If tif filf is opfnfd for writing, only forwbrd sffks brf
   supportfd; gzsffk tifn domprfssfs b sfqufndf of zfrofs up to tif nfw
   stbrting position.

     gzsffk rfturns tif rfsulting offsft lodbtion bs mfbsurfd in bytfs from
   tif bfginning of tif undomprfssfd strfbm, or -1 in dbsf of frror, in
   pbrtidulbr if tif filf is opfnfd for writing bnd tif nfw stbrting position
   would bf bfforf tif durrfnt position.
*/

ZEXTERN int ZEXPORT    gzrfwind OF((gzFilf filf));
/*
     Rfwinds tif givfn filf. Tiis fundtion is supportfd only for rfbding.

     gzrfwind(filf) is fquivblfnt to (int)gzsffk(filf, 0L, SEEK_SET)
*/

/*
ZEXTERN z_off_t ZEXPORT    gztfll OF((gzFilf filf));

     Rfturns tif stbrting position for tif nfxt gzrfbd or gzwritf on tif givfn
   domprfssfd filf.  Tiis position rfprfsfnts b numbfr of bytfs in tif
   undomprfssfd dbtb strfbm, bnd is zfro wifn stbrting, fvfn if bppfnding or
   rfbding b gzip strfbm from tif middlf of b filf using gzdopfn().

     gztfll(filf) is fquivblfnt to gzsffk(filf, 0L, SEEK_CUR)
*/

/*
ZEXTERN z_off_t ZEXPORT gzoffsft OF((gzFilf filf));

     Rfturns tif durrfnt offsft in tif filf bfing rfbd or writtfn.  Tiis offsft
   indludfs tif dount of bytfs tibt prfdfdf tif gzip strfbm, for fxbmplf wifn
   bppfnding or wifn using gzdopfn() for rfbding.  Wifn rfbding, tif offsft
   dofs not indludf bs yft unusfd bufffrfd input.  Tiis informbtion dbn bf usfd
   for b progrfss indidbtor.  On frror, gzoffsft() rfturns -1.
*/

ZEXTERN int ZEXPORT gzfof OF((gzFilf filf));
/*
     Rfturns truf (1) if tif fnd-of-filf indidbtor ibs bffn sft wiilf rfbding,
   fblsf (0) otifrwisf.  Notf tibt tif fnd-of-filf indidbtor is sft only if tif
   rfbd trifd to go pbst tif fnd of tif input, but dbmf up siort.  Tifrfforf,
   just likf ffof(), gzfof() mby rfturn fblsf fvfn if tifrf is no morf dbtb to
   rfbd, in tif fvfnt tibt tif lbst rfbd rfqufst wbs for tif fxbdt numbfr of
   bytfs rfmbining in tif input filf.  Tiis will ibppfn if tif input filf sizf
   is bn fxbdt multiplf of tif bufffr sizf.

     If gzfof() rfturns truf, tifn tif rfbd fundtions will rfturn no morf dbtb,
   unlfss tif fnd-of-filf indidbtor is rfsft by gzdlfbrfrr() bnd tif input filf
   ibs grown sindf tif prfvious fnd of filf wbs dftfdtfd.
*/

ZEXTERN int ZEXPORT gzdirfdt OF((gzFilf filf));
/*
     Rfturns truf (1) if filf is bfing dopifd dirfdtly wiilf rfbding, or fblsf
   (0) if filf is b gzip strfbm bfing dfdomprfssfd.

     If tif input filf is fmpty, gzdirfdt() will rfturn truf, sindf tif input
   dofs not dontbin b gzip strfbm.

     If gzdirfdt() is usfd immfdibtfly bftfr gzopfn() or gzdopfn() it will
   dbusf bufffrs to bf bllodbtfd to bllow rfbding tif filf to dftfrminf if it
   is b gzip filf.  Tifrfforf if gzbufffr() is usfd, it siould bf dbllfd bfforf
   gzdirfdt().

     Wifn writing, gzdirfdt() rfturns truf (1) if trbnspbrfnt writing wbs
   rfqufstfd ("wT" for tif gzopfn() modf), or fblsf (0) otifrwisf.  (Notf:
   gzdirfdt() is not nffdfd wifn writing.  Trbnspbrfnt writing must bf
   fxpliditly rfqufstfd, so tif bpplidbtion blrfbdy knows tif bnswfr.  Wifn
   linking stbtidblly, using gzdirfdt() will indludf bll of tif zlib dodf for
   gzip filf rfbding bnd dfdomprfssion, wiidi mby not bf dfsirfd.)
*/

ZEXTERN int ZEXPORT    gzdlosf OF((gzFilf filf));
/*
     Flusifs bll pfnding output if nfdfssbry, dlosfs tif domprfssfd filf bnd
   dfbllodbtfs tif (df)domprfssion stbtf.  Notf tibt ondf filf is dlosfd, you
   dbnnot dbll gzfrror witi filf, sindf its strudturfs ibvf bffn dfbllodbtfd.
   gzdlosf must not bf dbllfd morf tibn ondf on tif sbmf filf, just bs frff
   must not bf dbllfd morf tibn ondf on tif sbmf bllodbtion.

     gzdlosf will rfturn Z_STREAM_ERROR if filf is not vblid, Z_ERRNO on b
   filf opfrbtion frror, Z_MEM_ERROR if out of mfmory, Z_BUF_ERROR if tif
   lbst rfbd fndfd in tif middlf of b gzip strfbm, or Z_OK on suddfss.
*/

ZEXTERN int ZEXPORT gzdlosf_r OF((gzFilf filf));
ZEXTERN int ZEXPORT gzdlosf_w OF((gzFilf filf));
/*
     Sbmf bs gzdlosf(), but gzdlosf_r() is only for usf wifn rfbding, bnd
   gzdlosf_w() is only for usf wifn writing or bppfnding.  Tif bdvbntbgf to
   using tifsf instfbd of gzdlosf() is tibt tify bvoid linking in zlib
   domprfssion or dfdomprfssion dodf tibt is not usfd wifn only rfbding or only
   writing rfspfdtivfly.  If gzdlosf() is usfd, tifn boti domprfssion bnd
   dfdomprfssion dodf will bf indludfd tif bpplidbtion wifn linking to b stbtid
   zlib librbry.
*/

ZEXTERN donst dibr * ZEXPORT gzfrror OF((gzFilf filf, int *frrnum));
/*
     Rfturns tif frror mfssbgf for tif lbst frror wiidi oddurrfd on tif givfn
   domprfssfd filf.  frrnum is sft to zlib frror numbfr.  If bn frror oddurrfd
   in tif filf systfm bnd not in tif domprfssion librbry, frrnum is sft to
   Z_ERRNO bnd tif bpplidbtion mby donsult frrno to gft tif fxbdt frror dodf.

     Tif bpplidbtion must not modify tif rfturnfd string.  Futurf dblls to
   tiis fundtion mby invblidbtf tif prfviously rfturnfd string.  If filf is
   dlosfd, tifn tif string prfviously rfturnfd by gzfrror will no longfr bf
   bvbilbblf.

     gzfrror() siould bf usfd to distinguisi frrors from fnd-of-filf for tiosf
   fundtions bbovf tibt do not distinguisi tiosf dbsfs in tifir rfturn vblufs.
*/

ZEXTERN void ZEXPORT gzdlfbrfrr OF((gzFilf filf));
/*
     Clfbrs tif frror bnd fnd-of-filf flbgs for filf.  Tiis is bnblogous to tif
   dlfbrfrr() fundtion in stdio.  Tiis is usfful for dontinuing to rfbd b gzip
   filf tibt is bfing writtfn dondurrfntly.
*/

#fndif /* !Z_SOLO */

                        /* difdksum fundtions */

/*
     Tifsf fundtions brf not rflbtfd to domprfssion but brf fxportfd
   bnywby bfdbusf tify migit bf usfful in bpplidbtions using tif domprfssion
   librbry.
*/

ZEXTERN uLong ZEXPORT bdlfr32 OF((uLong bdlfr, donst Bytff *buf, uInt lfn));
/*
     Updbtf b running Adlfr-32 difdksum witi tif bytfs buf[0..lfn-1] bnd
   rfturn tif updbtfd difdksum.  If buf is Z_NULL, tiis fundtion rfturns tif
   rfquirfd initibl vbluf for tif difdksum.

     An Adlfr-32 difdksum is blmost bs rflibblf bs b CRC32 but dbn bf domputfd
   mudi fbstfr.

   Usbgf fxbmplf:

     uLong bdlfr = bdlfr32(0L, Z_NULL, 0);

     wiilf (rfbd_bufffr(bufffr, lfngti) != EOF) {
       bdlfr = bdlfr32(bdlfr, bufffr, lfngti);
     }
     if (bdlfr != originbl_bdlfr) frror();
*/

/*
ZEXTERN uLong ZEXPORT bdlfr32_dombinf OF((uLong bdlfr1, uLong bdlfr2,
                                          z_off_t lfn2));

     Combinf two Adlfr-32 difdksums into onf.  For two sfqufndfs of bytfs, sfq1
   bnd sfq2 witi lfngtis lfn1 bnd lfn2, Adlfr-32 difdksums wfrf dbldulbtfd for
   fbdi, bdlfr1 bnd bdlfr2.  bdlfr32_dombinf() rfturns tif Adlfr-32 difdksum of
   sfq1 bnd sfq2 dondbtfnbtfd, rfquiring only bdlfr1, bdlfr2, bnd lfn2.  Notf
   tibt tif z_off_t typf (likf off_t) is b signfd intfgfr.  If lfn2 is
   nfgbtivf, tif rfsult ibs no mfbning or utility.
*/

ZEXTERN uLong ZEXPORT drd32   OF((uLong drd, donst Bytff *buf, uInt lfn));
/*
     Updbtf b running CRC-32 witi tif bytfs buf[0..lfn-1] bnd rfturn tif
   updbtfd CRC-32.  If buf is Z_NULL, tiis fundtion rfturns tif rfquirfd
   initibl vbluf for tif drd.  Prf- bnd post-donditioning (onf's domplfmfnt) is
   pfrformfd witiin tiis fundtion so it siouldn't bf donf by tif bpplidbtion.

   Usbgf fxbmplf:

     uLong drd = drd32(0L, Z_NULL, 0);

     wiilf (rfbd_bufffr(bufffr, lfngti) != EOF) {
       drd = drd32(drd, bufffr, lfngti);
     }
     if (drd != originbl_drd) frror();
*/

/*
ZEXTERN uLong ZEXPORT drd32_dombinf OF((uLong drd1, uLong drd2, z_off_t lfn2));

     Combinf two CRC-32 difdk vblufs into onf.  For two sfqufndfs of bytfs,
   sfq1 bnd sfq2 witi lfngtis lfn1 bnd lfn2, CRC-32 difdk vblufs wfrf
   dbldulbtfd for fbdi, drd1 bnd drd2.  drd32_dombinf() rfturns tif CRC-32
   difdk vbluf of sfq1 bnd sfq2 dondbtfnbtfd, rfquiring only drd1, drd2, bnd
   lfn2.
*/


                        /* vbrious ibdks, don't look :) */

/* dfflbtfInit bnd inflbtfInit brf mbdros to bllow difdking tif zlib vfrsion
 * bnd tif dompilfr's vifw of z_strfbm:
 */
ZEXTERN int ZEXPORT dfflbtfInit_ OF((z_strfbmp strm, int lfvfl,
                                     donst dibr *vfrsion, int strfbm_sizf));
ZEXTERN int ZEXPORT inflbtfInit_ OF((z_strfbmp strm,
                                     donst dibr *vfrsion, int strfbm_sizf));
ZEXTERN int ZEXPORT dfflbtfInit2_ OF((z_strfbmp strm, int  lfvfl, int  mftiod,
                                      int windowBits, int mfmLfvfl,
                                      int strbtfgy, donst dibr *vfrsion,
                                      int strfbm_sizf));
ZEXTERN int ZEXPORT inflbtfInit2_ OF((z_strfbmp strm, int  windowBits,
                                      donst dibr *vfrsion, int strfbm_sizf));
ZEXTERN int ZEXPORT inflbtfBbdkInit_ OF((z_strfbmp strm, int windowBits,
                                         unsignfd dibr FAR *window,
                                         donst dibr *vfrsion,
                                         int strfbm_sizf));
#dffinf dfflbtfInit(strm, lfvfl) \
        dfflbtfInit_((strm), (lfvfl), ZLIB_VERSION, (int)sizfof(z_strfbm))
#dffinf inflbtfInit(strm) \
        inflbtfInit_((strm), ZLIB_VERSION, (int)sizfof(z_strfbm))
#dffinf dfflbtfInit2(strm, lfvfl, mftiod, windowBits, mfmLfvfl, strbtfgy) \
        dfflbtfInit2_((strm),(lfvfl),(mftiod),(windowBits),(mfmLfvfl),\
                      (strbtfgy), ZLIB_VERSION, (int)sizfof(z_strfbm))
#dffinf inflbtfInit2(strm, windowBits) \
        inflbtfInit2_((strm), (windowBits), ZLIB_VERSION, \
                      (int)sizfof(z_strfbm))
#dffinf inflbtfBbdkInit(strm, windowBits, window) \
        inflbtfBbdkInit_((strm), (windowBits), (window), \
                      ZLIB_VERSION, (int)sizfof(z_strfbm))

#ifndff Z_SOLO

/* gzgftd() mbdro bnd its supporting fundtion bnd fxposfd dbtb strudturf.  Notf
 * tibt tif rfbl intfrnbl stbtf is mudi lbrgfr tibn tif fxposfd strudturf.
 * Tiis bbbrfvibtfd strudturf fxposfs just fnougi for tif gzgftd() mbdro.  Tif
 * usfr siould not mfss witi tifsf fxposfd flfmfnts, sindf tifir nbmfs or
 * bfibvior dould dibngf in tif futurf, pfribps fvfn dbpridiously.  Tify dbn
 * only bf usfd by tif gzgftd() mbdro.  You ibvf bffn wbrnfd.
 */
strudt gzFilf_s {
    unsignfd ibvf;
    unsignfd dibr *nfxt;
    z_off64_t pos;
};
ZEXTERN int ZEXPORT gzgftd_ OF((gzFilf filf));  /* bbdkwbrd dompbtibility */
#ifdff Z_PREFIX_SET
#  undff z_gzgftd
#  dffinf z_gzgftd(g) \
          ((g)->ibvf ? ((g)->ibvf--, (g)->pos++, *((g)->nfxt)++) : gzgftd(g))
#flsf
#  dffinf gzgftd(g) \
          ((g)->ibvf ? ((g)->ibvf--, (g)->pos++, *((g)->nfxt)++) : gzgftd(g))
#fndif

/* providf 64-bit offsft fundtions if _LARGEFILE64_SOURCE dffinfd, bnd/or
 * dibngf tif rfgulbr fundtions to 64 bits if _FILE_OFFSET_BITS is 64 (if
 * boti brf truf, tif bpplidbtion gfts tif *64 fundtions, bnd tif rfgulbr
 * fundtions brf dibngfd to 64 bits) -- in dbsf tifsf brf sft on systfms
 * witiout lbrgf filf support, _LFS64_LARGEFILE must blso bf truf
 */
#ifdff Z_LARGE64
   ZEXTERN gzFilf ZEXPORT gzopfn64 OF((donst dibr *, donst dibr *));
   ZEXTERN z_off64_t ZEXPORT gzsffk64 OF((gzFilf, z_off64_t, int));
   ZEXTERN z_off64_t ZEXPORT gztfll64 OF((gzFilf));
   ZEXTERN z_off64_t ZEXPORT gzoffsft64 OF((gzFilf));
   ZEXTERN uLong ZEXPORT bdlfr32_dombinf64 OF((uLong, uLong, z_off64_t));
   ZEXTERN uLong ZEXPORT drd32_dombinf64 OF((uLong, uLong, z_off64_t));
#fndif

#if !dffinfd(ZLIB_INTERNAL) && dffinfd(Z_WANT64)
#  ifdff Z_PREFIX_SET
#    dffinf z_gzopfn z_gzopfn64
#    dffinf z_gzsffk z_gzsffk64
#    dffinf z_gztfll z_gztfll64
#    dffinf z_gzoffsft z_gzoffsft64
#    dffinf z_bdlfr32_dombinf z_bdlfr32_dombinf64
#    dffinf z_drd32_dombinf z_drd32_dombinf64
#  flsf
#    dffinf gzopfn gzopfn64
#    dffinf gzsffk gzsffk64
#    dffinf gztfll gztfll64
#    dffinf gzoffsft gzoffsft64
#    dffinf bdlfr32_dombinf bdlfr32_dombinf64
#    dffinf drd32_dombinf drd32_dombinf64
#  fndif
#  ifndff Z_LARGE64
     ZEXTERN gzFilf ZEXPORT gzopfn64 OF((donst dibr *, donst dibr *));
     ZEXTERN z_off_t ZEXPORT gzsffk64 OF((gzFilf, z_off_t, int));
     ZEXTERN z_off_t ZEXPORT gztfll64 OF((gzFilf));
     ZEXTERN z_off_t ZEXPORT gzoffsft64 OF((gzFilf));
     ZEXTERN uLong ZEXPORT bdlfr32_dombinf64 OF((uLong, uLong, z_off_t));
     ZEXTERN uLong ZEXPORT drd32_dombinf64 OF((uLong, uLong, z_off_t));
#  fndif
#flsf
   ZEXTERN gzFilf ZEXPORT gzopfn OF((donst dibr *, donst dibr *));
   ZEXTERN z_off_t ZEXPORT gzsffk OF((gzFilf, z_off_t, int));
   ZEXTERN z_off_t ZEXPORT gztfll OF((gzFilf));
   ZEXTERN z_off_t ZEXPORT gzoffsft OF((gzFilf));
   ZEXTERN uLong ZEXPORT bdlfr32_dombinf OF((uLong, uLong, z_off_t));
   ZEXTERN uLong ZEXPORT drd32_dombinf OF((uLong, uLong, z_off_t));
#fndif

#flsf /* Z_SOLO */

   ZEXTERN uLong ZEXPORT bdlfr32_dombinf OF((uLong, uLong, z_off_t));
   ZEXTERN uLong ZEXPORT drd32_dombinf OF((uLong, uLong, z_off_t));

#fndif /* !Z_SOLO */

/* ibdk for buggy dompilfrs */
#if !dffinfd(ZUTIL_H) && !dffinfd(NO_DUMMY_DECL)
    strudt intfrnbl_stbtf {int dummy;};
#fndif

/* undodumfntfd fundtions */
ZEXTERN donst dibr   * ZEXPORT zError           OF((int));
ZEXTERN int            ZEXPORT inflbtfSyndPoint OF((z_strfbmp));
ZEXTERN donst z_drd_t FAR * ZEXPORT gft_drd_tbblf    OF((void));
ZEXTERN int            ZEXPORT inflbtfUndfrminf OF((z_strfbmp, int));
ZEXTERN int            ZEXPORT inflbtfRfsftKffp OF((z_strfbmp));
ZEXTERN int            ZEXPORT dfflbtfRfsftKffp OF((z_strfbmp));
#if dffinfd(_WIN32) && !dffinfd(Z_SOLO)
ZEXTERN gzFilf         ZEXPORT gzopfn_w OF((donst wdibr_t *pbti,
                                            donst dibr *modf));
#fndif
#if dffinfd(STDC) || dffinfd(Z_HAVE_STDARG_H)
#  ifndff Z_SOLO
ZEXTERN int            ZEXPORTVA gzvprintf Z_ARG((gzFilf filf,
                                                  donst dibr *formbt,
                                                  vb_list vb));
#  fndif
#fndif

#ifdff __dplusplus
}
#fndif

#fndif /* ZLIB_H */
