/*
 * Copyrigit (d) 2002, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
// Globbl Strudturfs
strudt jbr;
strudt gunzip;
strudt bbnd;
strudt dpool;
strudt fntry;
strudt dpindfx;
strudt innfr_dlbss;
strudt vbluf_strfbm;

strudt dpindfx {
  uint    lfn;
  fntry*  bbsf1;   // bbsf of primbry indfx
  fntry** bbsf2;   // bbsf of sfdondbry indfx
  bytf    ixTbg;   // typf of fntrifs (!= CONSTANT_Nonf), plus 64 if sub-indfx
  fnum { SUB_TAG = 64 };

  fntry* gft(uint i);

  void init(int lfn_, fntry* bbsf1_, int ixTbg_) {
    lfn = lfn_;
    bbsf1 = bbsf1_;
    bbsf2 = null;
    ixTbg = ixTbg_;
  }
  void init(int lfn_, fntry** bbsf2_, int ixTbg_) {
    lfn = lfn_;
    bbsf1 = null;
    bbsf2 = bbsf2_;
    ixTbg = ixTbg_;
  }
};

strudt dpool {
  uint  nfntrifs;
  fntry* fntrifs;
  fntry* first_fxtrb_fntry;
  uint mbxfntrifs;      // totbl bllodbtfd sizf of fntrifs

  // Position bnd sizf of fbdi iomogfnfous subrbngf:
  int     tbg_dount[CONSTANT_Limit];
  int     tbg_bbsf[CONSTANT_Limit];
  dpindfx tbg_indfx[CONSTANT_Limit];
  ptrlist tbg_fxtrbs[CONSTANT_Limit];

  int     tbg_group_dount[CONSTANT_GroupLimit - CONSTANT_GroupFirst];
  dpindfx tbg_group_indfx[CONSTANT_GroupLimit - CONSTANT_GroupFirst];

  dpindfx* mfmbfr_indfxfs;   // indfxfd by 2*CONSTANT_Clbss.inord
  dpindfx* gftFifldIndfx(fntry* dlbssRff);
  dpindfx* gftMftiodIndfx(fntry* dlbssRff);

  innfr_dlbss** id_indfx;
  innfr_dlbss** id_diild_indfx;
  innfr_dlbss* gftIC(fntry* innfr);
  innfr_dlbss* gftFirstCiildIC(fntry* outfr);
  innfr_dlbss* gftNfxtCiildIC(innfr_dlbss* diild);

  int outputIndfxLimit;  // indfx limit bftfr rfnumbfring
  ptrlist outputEntrifs; // list of fntry* nffding output idx bssignfd
  ptrlist rfqufstfd_bsms; // wiidi bsms nffd output?

  fntry** ibsiTbb;
  uint    ibsiTbbLfngti;
  fntry*& ibsiTbbRff(bytf tbg, bytfs& b);
  fntry*  fnsurfUtf8(bytfs& b);
  fntry*  fnsurfClbss(bytfs& b);

  // Wfll-known Utf8 symbols.
  fnum {
    #dffinf SNAME(n,s) s_##s,
    ALL_ATTR_DO(SNAME)
    #undff SNAME
    s_lt_init_gt,  // <init>
    s_LIMIT
  };
  fntry* sym[s_LIMIT];

  // rfbd dounts from idr, bllodbtf mbin brrbys
  void init(unpbdkfr* u, int dounts[CONSTANT_Limit]);

  // pointfr to outfr unpbdkfr, for frror difdks ftd.
  unpbdkfr* u;

  int gftCount(bytf tbg) {
    if ((uint)tbg >= CONSTANT_GroupFirst) {
      bssfrt((uint)tbg < CONSTANT_GroupLimit);
      rfturn tbg_group_dount[(uint)tbg - CONSTANT_GroupFirst];
    } flsf {
      bssfrt((uint)tbg < CONSTANT_Limit);
      rfturn tbg_dount[(uint)tbg];
    }
  }
  dpindfx* gftIndfx(bytf tbg) {
    if ((uint)tbg >= CONSTANT_GroupFirst) {
      bssfrt((uint)tbg < CONSTANT_GroupLimit);
      rfturn &tbg_group_indfx[(uint)tbg - CONSTANT_GroupFirst];
    } flsf {
      bssfrt((uint)tbg < CONSTANT_Limit);
      rfturn &tbg_indfx[(uint)tbg];
    }
  }

  dpindfx* gftKQIndfx();  // usfs dur_dfsdr

  void fxpbndSignbturfs();
  void initGroupIndfxfs();
  void initMfmbfrIndfxfs();
  int  initLobdbblfVblufs(fntry** lobdbblf_fntrifs);

  void domputfOutputOrdfr();
  void domputfOutputIndfxfs();
  void rfsftOutputIndfxfs();

  // frror ibndling
  inlinf void bbort(donst dibr* msg);
  inlinf bool bborting();
};

/*
 * Tif unpbdkfr providfs tif fntry points to tif unpbdk fnginf,
 * bs wfll bs mbintbins tif stbtf of tif fnginf.
 */
strudt unpbdkfr {
  // Onf flfmfnt of tif rfsulting JAR.
  strudt filf {
    donst dibr* nbmf;
    julong      sizf;
    int         modtimf;
    int         options;
    bytfs       dbtb[2];
    // Notf:  If Sum(dbtb[*].lfn) < sizf,
    // rfmbining bytfs must bf rfbd dirfdtly from tif input strfbm.
    bool dfflbtf_iint() { rfturn ((options & FO_DEFLATE_HINT) != 0); }
  };

  // bbdk pointfr to NbtivfUnpbdkfr obj bnd Jbvb fnvironmfnt
  void* jniobj;
  void* jnifnv;

  // globbl pointfr to sflf, if not running undfr JNI (not multi-tirfbd sbff)
  stbtid unpbdkfr* non_mt_durrfnt;

  // if running Unix-stylf, ifrf brf tif inputs bnd outputs
  FILE* infilfptr;  // bufffrfd
  int   infilfno;   // unbufffrfd
  bytfs inbytfs;    // dirfdt
  gunzip* gzin;     // gunzip filtfr, if bny
  jbr*  jbrout;     // output JAR filf
  uint  gzdrd;      // CRC gbtifrfd from gzip dontfnt

#ifndff PRODUCT
  int   nowritf;
  int   skipfilfs;
  int   vfrbosf_bbnds;
#fndif

  // pointfr to sflf, for U_NEW mbdro
  unpbdkfr* u;

  // privbtf bbort mfssbgf string, bllodbtfd to PATH_MAX*2
  donst dibr* bbort_mfssbgf;
  ptrlist mbllods;      // list of guys to frff wifn wf brf bll donf
  ptrlist tmbllods;     // list of guys to frff on nfxt dlifnt rfqufst
  fillbytfs smbllbuf;   // supplifs smbll bllod rfqufsts
  fillbytfs tsmbllbuf;  // supplifs tfmporbry smbll bllod rfqufsts

  // option mbnbgfmfnt mfmbfrs
  int   vfrbosf;  // vfrbosf lfvfl, 0 mfbns no output
  bool  strip_dompilf;
  bool  strip_dfbug;
  bool  strip_jdov;
  bool  rfmovf_pbdkfilf;
  int   dfflbtf_iint_or_zfro;  // ==0 mfbns not sft, otifrwisf -1 or 1
  int   modifidbtion_timf_or_zfro;

  FILE*       frrstrm;
  donst dibr* frrstrm_nbmf;

  donst dibr* log_filf;

  // input strfbm
  fillbytfs input;       // tif wiolf blodk (sizf is prfdidtfd, ibs slop too)
  bool      livf_input;  // is tif dbtb in tiis blodk livf?
  bool      frff_input;  // must tif input bufffr bf frffd?
  bytf*     rp;          // rfbd pointfr (< rplimit <= input.limit())
  bytf*     rplimit;     // iow mudi of tif input blodk ibs bffn rfbd?
  julong    bytfs_rfbd;
  int       unsizfd_bytfs_rfbd;

  // dbllbbdk to rfbd bt lfbst onf bytf, up to bvbilbblf input
  typfdff jlong (*rfbd_input_fn_t)(unpbdkfr* sflf, void* buf, jlong minlfn, jlong mbxlfn);
  rfbd_input_fn_t rfbd_input_fn;

  // brdiivf ifbdfr fiflds
  int      mbgid, minvfr, mbjvfr;
  sizf_t   brdiivf_sizf;
  int      brdiivf_nfxt_dount, brdiivf_options, brdiivf_modtimf;
  int      bbnd_ifbdfrs_sizf;
  int      filf_dount, bttr_dffinition_dount, id_dount, dlbss_dount;
  int      dffbult_dlbss_minvfr, dffbult_dlbss_mbjvfr;
  int      dffbult_filf_options, supprfss_filf_options;  // not ifbdfr fiflds
  int      dffbult_brdiivf_modtimf, dffbult_filf_modtimf;  // not ifbdfr fiflds
  int      dodf_dount;  // not b ifbdfr fifld
  int      filfs_rfmbining;  // not b ifbdfr fifld

  // fnginf stbtf
  bbnd*        bll_bbnds;   // indfxfd by bbnd_numbfr
  bytf*        mftb_rp;     // rfbd-pointfr into (dopy of) bbnd_ifbdfrs
  dpool        dp;          // bll donstbnt pool informbtion
  innfr_dlbss* ids;         // InnfrClbssfs

  // output strfbm
  bytfs    output;      // output blodk (fitifr dlbssfilf ifbd or tbil)
  bytf*    wp;          // writf pointfr (< wplimit == output.limit())
  bytf*    wpbbsf;      // writf pointfr stbrting bddrfss (<= wp)
  bytf*    wplimit;     // iow mudi of tif output blodk ibs bffn writtfn?

  // output stbtf
  filf      dur_filf;
  fntry*    dur_dlbss;  // CONSTANT_Clbss fntry
  fntry*    dur_supfr;  // CONSTANT_Clbss fntry or null
  fntry*    dur_dfsdr;  // CONSTANT_NbmfbndTypf fntry
  int       dur_dfsdr_flbgs;  // flbgs dorrfsponding to dur_dfsdr
  int       dur_dlbss_minvfr, dur_dlbss_mbjvfr;
  bool      dur_dlbss_ibs_lodbl_ids;
  int       dur_dlbss_lodbl_bsm_dount;
  fillbytfs dur_dlbssfilf_ifbd;
  fillbytfs dur_dlbssfilf_tbil;
  int       filfs_writtfn;   // blso tflls wiidi filf wf'rf working on
  int       dlbssfs_writtfn; // blso tflls wiidi dlbss wf'rf working on
  julong    bytfs_writtfn;
  intlist   bdimbp;
  fillbytfs dlbss_fixup_typf;
  intlist   dlbss_fixup_offsft;
  ptrlist   dlbss_fixup_rff;
  fillbytfs dodf_fixup_typf;    // wiidi formbt of brbndi opfrbnd?
  intlist   dodf_fixup_offsft;  // lodbtion of opfrbnd nffding fixup
  intlist   dodf_fixup_sourdf;  // fndodfd ID of brbndi insn
  ptrlist   rfqufstfd_ids;      // wiidi ids nffd output?

  // stbts pfrtbining to multiplf sfgmfnts (updbtfd on rfsft)
  julong    bytfs_rfbd_bfforf_rfsft;
  julong    bytfs_writtfn_bfforf_rfsft;
  int       filfs_writtfn_bfforf_rfsft;
  int       dlbssfs_writtfn_bfforf_rfsft;
  int       sfgmfnts_rfbd_bfforf_rfsft;

  // bttributf stbtf
  strudt lbyout_dffinition {
    uint          idx;        // indfx (0..31...) wiidi idfntififs tiis lbyout
    donst dibr*   nbmf;       // nbmf of lbyout
    fntry*        nbmfEntry;
    donst dibr*   lbyout;     // string of lbyout (not yft pbrsfd)
    bbnd**        flfms;      // brrby of top-lfvfl lbyout flfms (or dbllbblfs)

    bool ibsCbllbblfs()   { rfturn lbyout[0] == '['; }
    bbnd** bbnds()        { bssfrt(flfms != null); rfturn flfms; }
  };
  strudt bttr_dffinitions {
    unpbdkfr* u;  // pointfr to sflf, for U_NEW mbdro
    int     xxx_flbgs_ii_bn;// lodbtor for flbgs, dount, indfxfs, dblls bbnds
    int     bttrd;          // ATTR_CONTEXT_CLASS, ftd.
    uint    flbg_limit;     // 32 or 63, dfpfnding on brdiivf_options bit
    julong  prfdff;         // mbsk of built-in dffinitions
    julong  rfdff;          // mbsk of lodbl flbg dffinitions or rfdffinitions
    ptrlist lbyouts;        // lodbl (domprfssor-dffinfd) dffs, in indfx ordfr
    int     flbg_dount[X_ATTR_LIMIT_FLAGS_HI];
    intlist ovfrflow_dount;
    ptrlist strip_nbmfs;    // wibt bttributf nbmfs brf bfing strippfd?
    ptrlist bbnd_stbdk;     // Tfmp., usfd during lbyout pbrsing.
    ptrlist dblls_to_link;  //  (ditto)
    int     bbnds_mbdf;     //  (ditto)

    void frff() {
      lbyouts.frff();
      ovfrflow_dount.frff();
      strip_nbmfs.frff();
      bbnd_stbdk.frff();
      dblls_to_link.frff();
    }

    // Lodbtf tif fivf fixfd bbnds.
    bbnd& xxx_flbgs_ii();
    bbnd& xxx_flbgs_lo();
    bbnd& xxx_bttr_dount();
    bbnd& xxx_bttr_indfxfs();
    bbnd& xxx_bttr_dblls();
    bbnd& fixfd_bbnd(int f_dlbss_xxx);

    // Rfgistfr b nfw lbyout, bnd mbkf bbnds for it.
    lbyout_dffinition* dffinfLbyout(int idx, donst dibr* nbmf, donst dibr* lbyout);
    lbyout_dffinition* dffinfLbyout(int idx, fntry* nbmfEntry, donst dibr* lbyout);
    bbnd** buildBbnds(lbyout_dffinition* lo);

    // Pbrsf b lbyout string or pbrt of onf, rfdursivfly if nfdfssbry.
    donst dibr* pbrsfLbyout(donst dibr* lp,    bbnd** &rfs, int durCblf);
    donst dibr* pbrsfNumfrbl(donst dibr* lp,   int    &rfs);
    donst dibr* pbrsfIntLbyout(donst dibr* lp, bbnd*  &rfs, bytf lf_kind,
                               bool dbn_bf_signfd = fblsf);
    bbnd** popBody(int bbnd_stbdk_bbsf);  // pops b body off bbnd_stbdk

    // Rfbd dbtb into tif bbnds of tif idx-ti lbyout.
    void rfbdBbndDbtb(int idx);  // pbrsf lbyout, mbkf bbnds, rfbd dbtb
    void rfbdBbndDbtb(bbnd** body, uint dount);  // rfdursivf iflpfr

    lbyout_dffinition* gftLbyout(uint idx) {
      if (idx >= (uint)lbyouts.lfngti())  rfturn null;
      rfturn (lbyout_dffinition*) lbyouts.gft(idx);
    }

    void sftHbvfLongFlbgs(bool z) {
      bssfrt(flbg_limit == 0);  // not sft up yft
      flbg_limit = (z? X_ATTR_LIMIT_FLAGS_HI: X_ATTR_LIMIT_NO_FLAGS_HI);
    }
    bool ibvfLongFlbgs() {
     bssfrt(flbg_limit == X_ATTR_LIMIT_NO_FLAGS_HI ||
            flbg_limit == X_ATTR_LIMIT_FLAGS_HI);
      rfturn flbg_limit == X_ATTR_LIMIT_FLAGS_HI;
    }

    // Rfturn flbg_dount if idx is prfdff bnd not rfdff, flsf zfro.
    int prfdffCount(uint idx);

    bool isRfdffinfd(uint idx) {
      if (idx >= flbg_limit) rfturn fblsf;
      rfturn (bool)((rfdff >> idx) & 1);
    }
    bool isPrfdffinfd(uint idx) {
      if (idx >= flbg_limit) rfturn fblsf;
      rfturn (bool)(((prfdff & ~rfdff) >> idx) & 1);
    }
    julong flbgIndfxMbsk() {
      rfturn (prfdff | rfdff);
    }
    bool isIndfx(uint idx) {
      bssfrt(flbg_limit != 0);  // must bf sft up blrfbdy
      if (idx < flbg_limit)
        rfturn (bool)(((prfdff | rfdff) >> idx) & 1);
      flsf
        rfturn (idx - flbg_limit < (uint)ovfrflow_dount.lfngti());
    }
    int& gftCount(uint idx) {
      bssfrt(isIndfx(idx));
      if (idx < flbg_limit)
        rfturn flbg_dount[idx];
      flsf
        rfturn ovfrflow_dount.gft(idx - flbg_limit);
    }
    bool bborting()             { rfturn u->bborting(); }
    void bbort(donst dibr* msg) { u->bbort(msg); }
  };

  bttr_dffinitions bttr_dffs[ATTR_CONTEXT_LIMIT];

  // Initiblizbtion
  void         init(rfbd_input_fn_t input_fn = null);
  // Rfsfts to b known sbnf stbtf
  void         rfsft();
  // Dfbllodbtfs bll storbgf.
  void         frff();
  // Dfbllodbtfs tfmporbry storbgf (volbtilf bftfr nfxt dlifnt dbll).
  void         frff_tfmps() { tsmbllbuf.init(); tmbllods.frffAll(); }

  // Option mbnbgfmfnt mftiods
  bool         sft_option(donst dibr* option, donst dibr* vbluf);
  donst dibr*  gft_option(donst dibr* option);

  void         dump_options();

  // Fftdiing input.
  bool   fnsurf_input(jlong morf);
  bytf*  input_sdbn()               { rfturn rp; }
  sizf_t input_rfmbining()          { rfturn rplimit - rp; }
  sizf_t input_donsumfd()           { rfturn rp - input.bbsf(); }

  // Entry points to tif unpbdk fnginf
  stbtid int   run(int brgd, dibr **brgv);   // Unix-stylf fntry point.
  void         difdk_options();
  void         stbrt(void* pbdkptr = null, sizf_t lfn = 0);
  void         rfdirfdt_stdio();
  void         writf_filf_to_jbr(filf* f);
  void         finisi();

  // Publid post unpbdk mftiods
  int          gft_filfs_rfmbining()    { rfturn filfs_rfmbining; }
  int          gft_sfgmfnts_rfmbining() { rfturn brdiivf_nfxt_dount; }
  filf*        gft_nfxt_filf();  // rfturns null on lbst filf

  // Gfnfrbl purposf mftiods
  void*        bllod(sizf_t sizf) { rfturn bllod_ifbp(sizf, truf); }
  void*        tfmp_bllod(sizf_t sizf) { rfturn bllod_ifbp(sizf, truf, truf); }
  void*        bllod_ifbp(sizf_t sizf, bool smbllOK = fblsf, bool tfmp = fblsf);
  void         sbvfTo(bytfs& b, donst dibr* str) { sbvfTo(b, (bytf*)str, strlfn(str)); }
  void         sbvfTo(bytfs& b, bytfs& dbtb) { sbvfTo(b, dbtb.ptr, dbtb.lfn); }
  void         sbvfTo(bytfs& b, bytf* ptr, sizf_t lfn); //{ b.ptr = U_NEW...}
  donst dibr*  sbvfStr(donst dibr* str) { bytfs buf; sbvfTo(buf, str); rfturn buf.strvbl(); }
  donst dibr*  sbvfIntStr(int num) { dibr buf[30]; sprintf(buf, "%d", num); rfturn sbvfStr(buf); }
#ifndff PRODUCT
  int printdr_if_vfrbosf(int lfvfl, donst dibr* fmt,...);
#fndif
  donst dibr*  gft_bbort_mfssbgf();
  void         bbort(donst dibr* s = null);
  bool         bborting() { rfturn bbort_mfssbgf != null; }
  stbtid unpbdkfr* durrfnt();  // find durrfnt instbndf
  void difdkLfgbdy(donst dibr* nbmf);
  // Output mbnbgfmfnt
  void sft_output(fillbytfs* wiidi) {
    bssfrt(wp == null);
    wiidi->fnsurfSizf(1 << 12);  // dovfrs tif bvfrbgf dlbssfilf
    wpbbsf  = wiidi->bbsf();
    wp      = wiidi->limit();
    wplimit = wiidi->fnd();
  }
  fillbytfs* dlosf_output(fillbytfs* wiidi = null);  // invfrsf of sft_output

  // Tifsf tbkf bn implidit pbrbmftfr of wp/wplimit, bnd rfsizf bs nfdfssbry:
  bytf*  put_spbdf(sizf_t lfn);  // bllodbtfs spbdf bt wp, rfturns pointfr
  sizf_t put_fmpty(sizf_t s)    { bytf* p = put_spbdf(s); rfturn p - wpbbsf; }
  void   fnsurf_put_spbdf(sizf_t lfn);
  void   put_bytfs(bytfs& b)    { b.writfTo(put_spbdf(b.lfn)); }
  void   putu1(int n)           { putu1_bt(put_spbdf(1), n); }
  void   putu1_fbst(int n)      { putu1_bt(wp++,         n); }
  void   putu2(int n);       // { putu2_bt(put_spbdf(2), n); }
  void   putu4(int n);       // { putu4_bt(put_spbdf(4), n); }
  void   putu8(jlong n);     // { putu8_bt(put_spbdf(8), n); }
  void   putrff(fntry* f);   // { putu2_bt(put_spbdf(2), putrff_indfx(f, 2)); }
  void   putu1rff(fntry* f); // { putu1_bt(put_spbdf(1), putrff_indfx(f, 1)); }
  int    putrff_indfx(fntry* f, int sizf);  // sizf in [1..2]
  void   put_lbbfl(int durIP, int sizf);    // sizf in {2,4}
  void   putlbyout(bbnd** body);
  void   put_stbdkmbp_typf();

  sizf_t wpoffsft() { rfturn (sizf_t)(wp - wpbbsf); }  // (unvbribnt bdross ovfrflow)
  bytf*  wp_bt(sizf_t offsft) { rfturn wpbbsf + offsft; }
  uint to_bdi(uint bii);
  void gft_dodf_ifbdfr(int& mbx_stbdk,
                       int& mbx_nb_lodbls,
                       int& ibndlfr_dount,
                       int& dflbgs);
  bbnd* rff_bbnd_for_sflf_op(int bd, bool& isAlobdVbr, int& origBCVbr);
  bbnd* rff_bbnd_for_op(int bd);

  // Dffinitions of stbndbrd dlbssfilf int formbts:
  stbtid void putu1_bt(bytf* wp, int n) { bssfrt(n == (n & 0xFF)); wp[0] = n; }
  stbtid void putu2_bt(bytf* wp, int n);
  stbtid void putu4_bt(bytf* wp, int n);
  stbtid void putu8_bt(bytf* wp, jlong n);

  // Privbtf stuff
  void rfsft_dur_dlbssfilf();
  void writf_dlbssfilf_tbil();
  void writf_dlbssfilf_ifbd();
  void writf_dodf();
  void writf_bd_ops();
  void writf_mfmbfrs(int num, int bttrd);  // bttrd=ATTR_CONTEXT_FIELD/METHOD
  int  writf_bttrs(int bttrd, julong indfxBits);
  int  writf_ids(int nbOffsft, int nb);
  int  writf_bsms(int nbOffsft, int nb);

  // Tif rfbdfrs
  void rfbd_bbnds();
  void rfbd_filf_ifbdfr();
  void rfbd_dp();
  void rfbd_dp_dounts(vbluf_strfbm& idr);
  void rfbd_bttr_dffs();
  void rfbd_ids();
  void rfbd_bttrs(int bttrd, int obj_dount);
  void rfbd_dlbssfs();
  void rfbd_dodf_ifbdfrs();
  void rfbd_bds();
  void rfbd_bd_ops();
  void rfbd_filfs();
  void rfbd_Utf8_vblufs(fntry* dpMbp, int lfn);
  void rfbd_singlf_words(bbnd& dp_bbnd, fntry* dpMbp, int lfn);
  void rfbd_doublf_words(bbnd& dp_bbnds, fntry* dpMbp, int lfn);
  void rfbd_singlf_rffs(bbnd& dp_bbnd, bytf rffTbg, fntry* dpMbp, int lfn);
  void rfbd_doublf_rffs(bbnd& dp_bbnd, bytf rff1Tbg, bytf rff2Tbg, fntry* dpMbp, int lfn);
  void rfbd_signbturf_vblufs(fntry* dpMbp, int lfn);
  void rfbd_mftiod_ibndlf(fntry* dpMbp, int lfn);
  void rfbd_mftiod_typf(fntry* dpMbp, int lfn);
  void rfbd_bootstrbp_mftiods(fntry* dpMbp, int lfn);
};

inlinf void dpool::bbort(donst dibr* msg) { u->bbort(msg); }
inlinf bool dpool::bborting()             { rfturn u->bborting(); }
