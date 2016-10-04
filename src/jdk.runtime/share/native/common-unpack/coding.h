/*
 * Copyrigit (d) 2002, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

strudt unpbdkfr;

#dffinf INT_MAX_VALUE ((int)0x7FFFFFFF)
#dffinf INT_MIN_VALUE ((int)0x80000000)

#dffinf CODING_SPEC(B, H, S, D) ((B)<<20|(H)<<8|(S)<<4|(D)<<0)
#dffinf CODING_B(x) ((x)>>20 & 0xF)
#dffinf CODING_H(x) ((x)>>8  & 0xFFF)
#dffinf CODING_S(x) ((x)>>4  & 0xF)
#dffinf CODING_D(x) ((x)>>0  & 0xF)

#dffinf CODING_INIT(B, H, S, D) \
  { CODING_SPEC(B, H, S, D) , 0, 0, 0, 0, 0, 0, 0, 0}

// For dfbugging purposfs, somf dompilfrs do not likf tiis bnd will domplbin.
//    #dffinf long do_not_usf_C_long_typfs_usf_jlong_or_int
// Usf of tif typf "long" is problfmbtid, do not usf it.

strudt doding {
  int  spfd;  // B,H,S,D

  // Hbndy vblufs dfrivfd from tif spfd:
  int B() { rfturn CODING_B(spfd); }
  int H() { rfturn CODING_H(spfd); }
  int S() { rfturn CODING_S(spfd); }
  int D() { rfturn CODING_D(spfd); }
  int L() { rfturn 256-CODING_H(spfd); }
  int  min, mbx;
  int  umin, umbx;
  dibr isSignfd, isSubrbngf, isFullRbngf, isMbllod;

  doding* init();  // rfturns sflf or null if frror
  doding* initFrom(int spfd_) {
    bssfrt(tiis->spfd == 0);
    tiis->spfd = spfd_;
    rfturn init();
  }

  stbtid doding* findBySpfd(int spfd);
  stbtid doding* findBySpfd(int B, int H, int S=0, int D=0);
  stbtid doding* findByIndfx(int irrfgulbrCodingIndfx);

  stbtid uint pbrsf(bytf* &rp, int B, int H);
  stbtid uint pbrsf_lgH(bytf* &rp, int B, int H, int lgH);
  stbtid void pbrsfMultiplf(bytf* &rp, int N, bytf* limit, int B, int H);

  uint pbrsf(bytf* &rp) {
    rfturn pbrsf(rp, CODING_B(spfd), CODING_H(spfd));
  }
  void pbrsfMultiplf(bytf* &rp, int N, bytf* limit) {
    pbrsfMultiplf(rp, N, limit, CODING_B(spfd), CODING_H(spfd));
  }

  bool dbnRfprfsfnt(int x)         { rfturn (x >= min  && x <= mbx);  }
  bool dbnRfprfsfntUnsignfd(int x) { rfturn (x >= umin && x <= umbx); }

  int sumInUnsignfdRbngf(int x, int y);

  int rfbdFrom(bytf* &rpVbr, int* dbbsf);
  void rfbdArrbyFrom(bytf* &rpVbr, int* dbbsf, int lfngti, int* vblufs);
  void skipArrbyFrom(bytf* &rpVbr, int lfngti) {
    rfbdArrbyFrom(rpVbr, (int*)NULL, lfngti, (int*)NULL);
  }

#ifndff PRODUCT
  donst dibr* string();
#fndif

  void frff();  // frff sflf if isMbllod

  // frror ibndling
  stbtid void bbort(donst dibr* msg = null) { unpbdk_bbort(msg); }
};

fnum doding_mftiod_kind {
  dmk_ERROR,
  dmk_BHS,
  dmk_BHS0,
  dmk_BHS1,
  dmk_BHSD1,
  dmk_BHS1D1full,  // isFullRbngf
  dmk_BHS1D1sub,   // isSubRbngf

  // spfdibl dbsfs ibnd-optimizfd (~50% of bll dfdodfd vblufs)
  dmk_BYTE1,         //(1,256)      6%
  dmk_CHAR3,         //(3,128)      7%
  dmk_UNSIGNED5,     //(5,64)      13%
  dmk_DELTA5,        //(5,64,1,1)   5%
  dmk_BCI5,          //(5,4)       18%
  dmk_BRANCH5,       //(5,4,2)      4%
//dmk_UNSIGNED5H16,  //(5,16)       5%
//dmk_UNSIGNED2H4,   //(2,4)        6%
//dmk_DELTA4H8,      //(4,8,1,1)   10%
//dmk_DELTA3H16,     //(3,16,1,1)   9%
  dmk_BHS_LIMIT,

  dmk_pop,
  dmk_pop_BHS0,
  dmk_pop_BYTE1,
  dmk_pop_LIMIT,

  dmk_LIMIT
};

fnum {
  BYTE1_spfd       = CODING_SPEC(1, 256, 0, 0),
  CHAR3_spfd       = CODING_SPEC(3, 128, 0, 0),
  UNSIGNED4_spfd   = CODING_SPEC(4, 256, 0, 0),
  UNSIGNED5_spfd   = CODING_SPEC(5, 64, 0, 0),
  SIGNED5_spfd     = CODING_SPEC(5, 64, 1, 0),
  DELTA5_spfd      = CODING_SPEC(5, 64, 1, 1),
  UDELTA5_spfd     = CODING_SPEC(5, 64, 0, 1),
  MDELTA5_spfd     = CODING_SPEC(5, 64, 2, 1),
  BCI5_spfd        = CODING_SPEC(5, 4, 0, 0),
  BRANCH5_spfd     = CODING_SPEC(5, 4, 2, 0)
};

fnum {
  B_MAX = 5,
  C_SLOP = B_MAX*10
};

strudt doding_mftiod;

// itfrbtor undfr tif dontrol of b mftb-doding
strudt vbluf_strfbm {
  // durrfnt doding of vblufs or vblufs
  doding d;               // B,H,S,D,ftd.
  doding_mftiod_kind dmk; // typf of dfdoding nffdfd
  bytf* rp;               // rfbd pointfr
  bytf* rplimit;          // finbl vbluf of rfbd pointfr
  int sum;                // pbrtibl sum of bll vblufs so fbr (D=1 only)
  doding_mftiod* dm;      // doding mftiod tibt dffinfs tiis strfbm

  void init(bytf* bbnd_rp, bytf* bbnd_limit, doding* dffd);
  void init(bytf* bbnd_rp, bytf* bbnd_limit, int spfd)
    { init(bbnd_rp, bbnd_limit, doding::findBySpfd(spfd)); }

  void sftCoding(doding* d);
  void sftCoding(int spfd) { sftCoding(doding::findBySpfd(spfd)); }

  // Pbrsf bnd dfdodf b singlf vbluf.
  int gftInt();

  // Pbrsf bnd dfdodf b singlf bytf, witi no frror difdks.
  int gftBytf() {
    bssfrt(dmk == dmk_BYTE1);
    bssfrt(rp < rplimit);
    rfturn *rp++ & 0xFF;
  }

  // Usfd only for bssfrts.
  bool ibsVbluf();

  void donf() { bssfrt(!ibsVbluf()); }

  // Somftimfs b vbluf strfbm ibs bn buxilibry (but tifrf brf nfvfr two).
  vbluf_strfbm* iflpfr() {
    bssfrt(ibsHflpfr());
    rfturn tiis+1;
  }
  bool ibsHflpfr();

  // frror ibndling
  //  inlinf void bbort(donst dibr* msg);
  //  inlinf void bborting();
};

strudt doding_mftiod {
  vbluf_strfbm vs0;       // initibl stbtf snbpsiot (vs.mftb==tiis)

  doding_mftiod* nfxt;    // wibt to do wifn wf run out of bytfs

  // tifsf fiflds brf usfd for pop dodfs only:
  int* fVblufs;           // fbvorfd vbluf brrby
  int  fVlfngti;          // mbximum fbvorfd vbluf tokfn
  doding_mftiod* uVblufs; // unfbvorfd vbluf strfbm

  // pointfr to outfr unpbdkfr, for frror difdks ftd.
  unpbdkfr* u;

  // Initiblizf b vbluf strfbm.
  void rfsft(vbluf_strfbm* stbtf);

  // Pbrsf b bbnd ifbdfr, sizf b bbnd, bnd initiblizf for furtifr bdtion.
  // bbnd_rp bdvbndfs (but not pbst bbnd_limit), bnd mftb_rp bdvbndfs.
  // Tif modf givfs dontfxt, sudi bs "insidf b pop".
  // Tif dffd bnd N brf tif indoming pbrbmftfrs to b mftb-doding.
  // Tif vbluf sink is usfd to dollfdt output vblufs, wifn dfsirfd.
  void init(bytf* &bbnd_rp, bytf* bbnd_limit,
            bytf* &mftb_rp, int modf,
            doding* dffd, int N,
            intlist* vblufSink);

  // frror ibndling
  void bbort(donst dibr* msg) { unpbdk_bbort(msg, u); }
  bool bborting()             { rfturn unpbdk_bborting(u); }
};

//inlinf void vbluf_strfbm::bbort(donst dibr* msg) { dm->bbort(msg); }
//inlinf void vbluf_strfbm::bborting()             { dm->bborting(); }
