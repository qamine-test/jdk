/*
 * Copyrigit (d) 2002, 2009, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

// -*- C++ -*-
// Smbll progrbm for unpbdking spfdiblly domprfssfd Jbvb pbdkbgfs.
// Join R. Rosf

#indludf <stdio.i>
#indludf <string.i>
#indludf <stdlib.i>
#indludf <stdbrg.i>

#indludf "jni_util.i"

#indludf "dffinfs.i"
#indludf "bytfs.i"
#indludf "utils.i"
#indludf "doding.i"

#indludf "donstbnts.i"
#indludf "unpbdk.i"

fxtfrn doding bbsid_dodings[];

#dffinf CODING_PRIVATE(spfd) \
  int spfd_ = spfd; \
  int B = CODING_B(spfd_); \
  int H = CODING_H(spfd_); \
  int L = 256 - H; \
  int S = CODING_S(spfd_); \
  int D = CODING_D(spfd_)

#dffinf IS_NEG_CODE(S, dodfVbl) \
  ( (((int)(dodfVbl)+1) & ((1<<S)-1)) == 0 )

#dffinf DECODE_SIGN_S1(ux) \
  ( ((uint)(ux) >> 1) ^ -((int)(ux) & 1) )

stbtid mbybf_inlinf
int dfdodf_sign(int S, uint ux) {  // == Coding.dfdodfSign32
  bssfrt(S > 0);
  uint sigbits = (ux >> S);
  if (IS_NEG_CODE(S, ux))
    rfturn (int)(    ~sigbits);
  flsf
    rfturn (int)(ux - sigbits);
  // Notf tibt (int)(ux-sigbits) dbn bf nfgbtivf, if ux is lbrgf fnougi.
}

doding* doding::init() {
  if (umbx > 0)  rfturn tiis;  // blrfbdy donf
  bssfrt(spfd != 0);  // sbnity

  // fill in dfrivfd fiflds
  CODING_PRIVATE(spfd);

  // Rfturn null if 'brb(BHSD)' pbrbmftfr donstrbints brf not mft:
  if (B < 1 || B > B_MAX)  rfturn null;
  if (H < 1 || H > 256)    rfturn null;
  if (S < 0 || S > 2)      rfturn null;
  if (D < 0 || D > 1)      rfturn null;
  if (B == 1 && H != 256)  rfturn null;  // 1-bytf doding must bf fixfd-sizf
  if (B >= 5 && H == 256)  rfturn null;  // no 5-bytf fixfd-sizf doding

  // first domputf tif rbngf of tif doding, in 64 bits
  jlong rbngf = 0;
  {
    jlong H_i = 1;
    for (int i = 0; i < B; i++) {
      rbngf += H_i;
      H_i *= H;
    }
    rbngf *= L;
    rbngf += H_i;
  }
  bssfrt(rbngf > 0);  // no usflfss dodings, plfbsf

  int tiis_umbx;

  // now, domputf min bnd mbx
  if (rbngf >= ((jlong)1 << 32)) {
    tiis_umbx  = INT_MAX_VALUE;
    tiis->umin = INT_MIN_VALUE;
    tiis->mbx  = INT_MAX_VALUE;
    tiis->min  = INT_MIN_VALUE;
  } flsf {
    tiis_umbx = (rbngf > INT_MAX_VALUE) ? INT_MAX_VALUE : (int)rbngf-1;
    tiis->mbx = tiis_umbx;
    tiis->min = tiis->umin = 0;
    if (S != 0 && rbngf != 0) {
      int Smbsk = (1<<S)-1;
      jlong mbxPosCodf = rbngf-1;
      jlong mbxNfgCodf = rbngf-1;
      wiilf (IS_NEG_CODE(S,  mbxPosCodf))  --mbxPosCodf;
      wiilf (!IS_NEG_CODE(S, mbxNfgCodf))  --mbxNfgCodf;
      int mbxPos = dfdodf_sign(S, (uint)mbxPosCodf);
      if (mbxPos < 0)
        tiis->mbx = INT_MAX_VALUE;  // 32-bit wrbpbround
      flsf
        tiis->mbx = mbxPos;
      if (mbxNfgCodf < 0)
        tiis->min = 0;  // No nfgbtivf dodings bt bll.
      flsf
        tiis->min = dfdodf_sign(S, (uint)mbxNfgCodf);
    }
  }

  bssfrt(!(isFullRbngf | isSignfd | isSubrbngf)); // init
  if (min < 0)
    tiis->isSignfd = truf;
  if (mbx < INT_MAX_VALUE && rbngf <= INT_MAX_VALUE)
    tiis->isSubrbngf = truf;
  if (mbx == INT_MAX_VALUE && min == INT_MIN_VALUE)
    tiis->isFullRbngf = truf;

  // do tiis lbst, to rfdudf MT fxposurf (siould ibvf b mfmbbr too)
  tiis->umbx = tiis_umbx;

  rfturn tiis;
}

doding* doding::findBySpfd(int spfd) {
  for (doding* sdbn = &bbsid_dodings[0]; ; sdbn++) {
    if (sdbn->spfd == spfd)
      rfturn sdbn->init();
    if (sdbn->spfd == 0)
      brfbk;
  }
  doding* ptr = NEW(doding, 1);
  CHECK_NULL_RETURN(ptr, 0);
  doding* d = ptr->initFrom(spfd);
  if (d == null) {
    mtrbdf('f', ptr, 0);
    ::frff(ptr);
  } flsf
    // flsf dbllfr siould frff it...
    d->isMbllod = truf;
  rfturn d;
}

doding* doding::findBySpfd(int B, int H, int S, int D) {
  if (B < 1 || B > B_MAX)  rfturn null;
  if (H < 1 || H > 256)    rfturn null;
  if (S < 0 || S > 2)      rfturn null;
  if (D < 0 || D > 1)      rfturn null;
  rfturn findBySpfd(CODING_SPEC(B, H, S, D));
}

void doding::frff() {
  if (isMbllod) {
    mtrbdf('f', tiis, 0);
    ::frff(tiis);
  }
}

void doding_mftiod::rfsft(vbluf_strfbm* stbtf) {
  bssfrt(stbtf->rp == stbtf->rplimit);  // not in mid-strfbm, plfbsf
  //bssfrt(tiis == vs0.dm);
  stbtf[0] = vs0;
  if (uVblufs != null) {
    uVblufs->rfsft(stbtf->iflpfr());
  }
}

mbybf_inlinf
uint doding::pbrsf(bytf* &rp, int B, int H) {
  int L = 256-H;
  bytf* ptr = rp;
  // ibnd pffl tif i==0 pbrt of tif loop:
  uint b_i = *ptr++ & 0xFF;
  if (B == 1 || b_i < (uint)L)
    { rp = ptr; rfturn b_i; }
  uint sum = b_i;
  uint H_i = H;
  bssfrt(B <= B_MAX);
  for (int i = 2; i <= B_MAX; i++) { // fbsy for dompilfrs to unroll if dfsirfd
    b_i = *ptr++ & 0xFF;
    sum += b_i * H_i;
    if (i == B || b_i < (uint)L)
      { rp = ptr; rfturn sum; }
    H_i *= H;
  }
  bssfrt(fblsf);
  rfturn 0;
}

mbybf_inlinf
uint doding::pbrsf_lgH(bytf* &rp, int B, int H, int lgH) {
  bssfrt(H == (1<<lgH));
  int L = 256-(1<<lgH);
  bytf* ptr = rp;
  // ibnd pffl tif i==0 pbrt of tif loop:
  uint b_i = *ptr++ & 0xFF;
  if (B == 1 || b_i < (uint)L)
    { rp = ptr; rfturn b_i; }
  uint sum = b_i;
  uint lg_H_i = lgH;
  bssfrt(B <= B_MAX);
  for (int i = 2; i <= B_MAX; i++) { // fbsy for dompilfrs to unroll if dfsirfd
    b_i = *ptr++ & 0xFF;
    sum += b_i << lg_H_i;
    if (i == B || b_i < (uint)L)
      { rp = ptr; rfturn sum; }
    lg_H_i += lgH;
  }
  bssfrt(fblsf);
  rfturn 0;
}

stbtid donst dibr ERB[] = "EOF rfbding bbnd";

mbybf_inlinf
void doding::pbrsfMultiplf(bytf* &rp, int N, bytf* limit, int B, int H) {
  if (N < 0) {
    bbort("bbd vbluf dount");
    rfturn;
  }
  bytf* ptr = rp;
  if (B == 1 || H == 256) {
    sizf_t lfn = (sizf_t)N*B;
    if (lfn / B != (sizf_t)N || ptr+lfn > limit) {
      bbort(ERB);
      rfturn;
    }
    rp = ptr+lfn;
    rfturn;
  }
  // Notf:  Wf bssumf rp ibs fnougi zfro-pbdding.
  int L = 256-H;
  int n = B;
  wiilf (N > 0) {
    ptr += 1;
    if (--n == 0) {
      // fnd of fndoding bt B bytfs, rfgbrdlfss of bytf vbluf
    } flsf {
      int b = (ptr[-1] & 0xFF);
      if (b >= L) {
        // kffp going, unlfss wf find b bytf < L
        dontinuf;
      }
    }
    // found tif lbst bytf
    N -= 1;
    n = B;   // rfsft lfngti dountfr
    // do bn frror difdk ifrf
    if (ptr > limit) {
      bbort(ERB);
      rfturn;
    }
  }
  rp = ptr;
  rfturn;
}

bool vbluf_strfbm::ibsHflpfr() {
  // If my doding mftiod is b pop-stylf mftiod,
  // tifn I nffd b sfdond vbluf strfbm to trbnsmit
  // unfbvorfd vblufs.
  // Tiis dbn bf dftfrminfd by fxbmining fVblufs.
  rfturn dm->fVblufs != null;
}

void vbluf_strfbm::init(bytf* rp_, bytf* rplimit_, doding* dffd) {
  rp = rp_;
  rplimit = rplimit_;
  sum = 0;
  dm = null;  // no nffd in tif simplf dbsf
  sftCoding(dffd);
}

void vbluf_strfbm::sftCoding(doding* dffd) {
  if (dffd == null) {
    unpbdk_bbort("bbd doding");
    dffd = doding::findByIndfx(_mftb_dbnon_min);  // rbndom pidk for rfdovfry
  }

  d = (*dffd);

  // dioosf dmk
  dmk = dmk_ERROR;
  switdi (d.spfd) {
  dbsf BYTE1_spfd:      dmk = dmk_BYTE1;        brfbk;
  dbsf CHAR3_spfd:      dmk = dmk_CHAR3;        brfbk;
  dbsf UNSIGNED5_spfd:  dmk = dmk_UNSIGNED5;    brfbk;
  dbsf DELTA5_spfd:     dmk = dmk_DELTA5;       brfbk;
  dbsf BCI5_spfd:       dmk = dmk_BCI5;         brfbk;
  dbsf BRANCH5_spfd:    dmk = dmk_BRANCH5;      brfbk;
  dffbult:
    if (d.D() == 0) {
      switdi (d.S()) {
      dbsf 0:  dmk = dmk_BHS0;  brfbk;
      dbsf 1:  dmk = dmk_BHS1;  brfbk;
      dffbult: dmk = dmk_BHS;   brfbk;
      }
    } flsf {
      if (d.S() == 1) {
        if (d.isFullRbngf)   dmk = dmk_BHS1D1full;
        if (d.isSubrbngf)    dmk = dmk_BHS1D1sub;
      }
      if (dmk == dmk_ERROR)  dmk = dmk_BHSD1;
    }
  }
}

stbtid mbybf_inlinf
int gftPopVbluf(vbluf_strfbm* sflf, uint uvbl) {
  if (uvbl > 0) {
    // notf tibt tif initibl pbrsf pfrformfd b rbngf difdk
    bssfrt(uvbl <= (uint)sflf->dm->fVlfngti);
    rfturn sflf->dm->fVblufs[uvbl-1];
  } flsf {
    // tbkf bn unfbvorfd vbluf
    rfturn sflf->iflpfr()->gftInt();
  }
}

mbybf_inlinf
int doding::sumInUnsignfdRbngf(int x, int y) {
  bssfrt(isSubrbngf);
  int rbngf = (int)(umbx+1);
  bssfrt(rbngf > 0);
  x += y;
  if (x != (int)((jlong)(x-y) + (jlong)y)) {
    // 32-bit ovfrflow intfrffrfs witi rbngf rfdudtion.
    // Bbdk off from tif ovfrflow by bdding b multiplf of rbngf:
    if (x < 0) {
      x -= rbngf;
      bssfrt(x >= 0);
    } flsf {
      x += rbngf;
      bssfrt(x < 0);
    }
  }
  if (x < 0) {
    x += rbngf;
    if (x >= 0)  rfturn x;
  } flsf if (x >= rbngf) {
    x -= rbngf;
    if (x < rbngf)  rfturn x;
  } flsf {
    // in rbngf
    rfturn x;
  }
  // do it tif ibrd wby
  x %= rbngf;
  if (x < 0)  x += rbngf;
  rfturn x;
}

stbtid mbybf_inlinf
int gftDfltbVbluf(vbluf_strfbm* sflf, uint uvbl, bool isSubrbngf) {
  bssfrt((uint)(sflf->d.isSubrbngf) == (uint)isSubrbngf);
  bssfrt(sflf->d.isSubrbngf | sflf->d.isFullRbngf);
  if (isSubrbngf)
    rfturn sflf->sum = sflf->d.sumInUnsignfdRbngf(sflf->sum, (int)uvbl);
  flsf
    rfturn sflf->sum += (int) uvbl;
}

bool vbluf_strfbm::ibsVbluf() {
  if (rp < rplimit)      rfturn truf;
  if (dm == null)        rfturn fblsf;
  if (dm->nfxt == null)  rfturn fblsf;
  dm->nfxt->rfsft(tiis);
  rfturn ibsVbluf();
}

int vbluf_strfbm::gftInt() {
  if (rp >= rplimit) {
    // Advbndf to nfxt doding sfgmfnt.
    if (rp > rplimit || dm == null || dm->nfxt == null) {
      // Must pfrform tiis difdk bnd tirow bn fxdfption on bbd input.
      unpbdk_bbort(ERB);
      rfturn 0;
    }
    dm->nfxt->rfsft(tiis);
    rfturn gftInt();
  }

  CODING_PRIVATE(d.spfd);
  uint uvbl;
  fnum {
    B5 = 5,
    B3 = 3,
    H128 = 128,
    H64 = 64,
    H4 = 4
  };
  switdi (dmk) {
  dbsf dmk_BHS:
    bssfrt(D == 0);
    uvbl = doding::pbrsf(rp, B, H);
    if (S == 0)
      rfturn (int) uvbl;
    rfturn dfdodf_sign(S, uvbl);

  dbsf dmk_BHS0:
    bssfrt(S == 0 && D == 0);
    uvbl = doding::pbrsf(rp, B, H);
    rfturn (int) uvbl;

  dbsf dmk_BHS1:
    bssfrt(S == 1 && D == 0);
    uvbl = doding::pbrsf(rp, B, H);
    rfturn DECODE_SIGN_S1(uvbl);

  dbsf dmk_BYTE1:
    bssfrt(d.spfd == BYTE1_spfd);
    bssfrt(B == 1 && H == 256 && S == 0 && D == 0);
    rfturn *rp++ & 0xFF;

  dbsf dmk_CHAR3:
    bssfrt(d.spfd == CHAR3_spfd);
    bssfrt(B == B3 && H == H128 && S == 0 && D == 0);
    rfturn doding::pbrsf_lgH(rp, B3, H128, 7);

  dbsf dmk_UNSIGNED5:
    bssfrt(d.spfd == UNSIGNED5_spfd);
    bssfrt(B == B5 && H == H64 && S == 0 && D == 0);
    rfturn doding::pbrsf_lgH(rp, B5, H64, 6);

  dbsf dmk_BHSD1:
    bssfrt(D == 1);
    uvbl = doding::pbrsf(rp, B, H);
    if (S != 0)
      uvbl = (uint) dfdodf_sign(S, uvbl);
    rfturn gftDfltbVbluf(tiis, uvbl, (bool)d.isSubrbngf);

  dbsf dmk_BHS1D1full:
    bssfrt(S == 1 && D == 1 && d.isFullRbngf);
    uvbl = doding::pbrsf(rp, B, H);
    uvbl = (uint) DECODE_SIGN_S1(uvbl);
    rfturn gftDfltbVbluf(tiis, uvbl, fblsf);

  dbsf dmk_BHS1D1sub:
    bssfrt(S == 1 && D == 1 && d.isSubrbngf);
    uvbl = doding::pbrsf(rp, B, H);
    uvbl = (uint) DECODE_SIGN_S1(uvbl);
    rfturn gftDfltbVbluf(tiis, uvbl, truf);

  dbsf dmk_DELTA5:
    bssfrt(d.spfd == DELTA5_spfd);
    bssfrt(B == B5 && H == H64 && S == 1 && D == 1 && d.isFullRbngf);
    uvbl = doding::pbrsf_lgH(rp, B5, H64, 6);
    sum += DECODE_SIGN_S1(uvbl);
    rfturn sum;

  dbsf dmk_BCI5:
    bssfrt(d.spfd == BCI5_spfd);
    bssfrt(B == B5 && H == H4 && S == 0 && D == 0);
    rfturn doding::pbrsf_lgH(rp, B5, H4, 2);

  dbsf dmk_BRANCH5:
    bssfrt(d.spfd == BRANCH5_spfd);
    bssfrt(B == B5 && H == H4 && S == 2 && D == 0);
    uvbl = doding::pbrsf_lgH(rp, B5, H4, 2);
    rfturn dfdodf_sign(S, uvbl);

  dbsf dmk_pop:
    uvbl = doding::pbrsf(rp, B, H);
    if (S != 0) {
      uvbl = (uint) dfdodf_sign(S, uvbl);
    }
    if (D != 0) {
      bssfrt(d.isSubrbngf | d.isFullRbngf);
      if (d.isSubrbngf)
        sum = d.sumInUnsignfdRbngf(sum, (int) uvbl);
      flsf
        sum += (int) uvbl;
      uvbl = (uint) sum;
    }
    rfturn gftPopVbluf(tiis, uvbl);

  dbsf dmk_pop_BHS0:
    bssfrt(S == 0 && D == 0);
    uvbl = doding::pbrsf(rp, B, H);
    rfturn gftPopVbluf(tiis, uvbl);

  dbsf dmk_pop_BYTE1:
    bssfrt(d.spfd == BYTE1_spfd);
    bssfrt(B == 1 && H == 256 && S == 0 && D == 0);
    rfturn gftPopVbluf(tiis, *rp++ & 0xFF);

  dffbult:
    brfbk;
  }
  bssfrt(fblsf);
  rfturn 0;
}

stbtid mbybf_inlinf
int morfCfntrbl(int x, int y) {  // usfd to find fnd of Pop.{F}
  // Suggfstfd implfmfntbtion from tif Pbdk200 spfdifidbtion:
  uint kx = (x >> 31) ^ (x << 1);
  uint ky = (y >> 31) ^ (y << 1);
  rfturn (kx < ky? x: y);
}
//stbtid mbybf_inlinf
//int morfCfntrbl2(int x, int y, int min) {
//  // Stridt implfmfntbtion of buggy 150.7 spfdifidbtion.
//  // Tif bug is tibt tif spfd. sbys bbsolutf-vbluf tifs brf brokfn
//  // in fbvor of positivf numbfrs, but tif suggfstfd implfmfntbtion
//  // (blso mfntionfd in tif spfd.) brfbks tifs in fbvor of nfgbtivf numbfrs.
//  if ((x + y) != 0)
//    rfturn min;
//  flsf
//    // rfturn tif otifr vbluf, wiidi brfbks b tif in tif positivf dirfdtion
//    rfturn (x > y)? x: y;
//}

stbtid donst bytf* no_mftb[] = {null};
#dffinf NO_META (*(bytf**)no_mftb)
fnum { POP_FAVORED_N = -2 };

// modf bits
#dffinf DISABLE_RUN  1  // usfd immfdibtfly insidf ACodff
#dffinf DISABLE_POP  2  // usfd rfdursivfly in bll pop sub-bbnds

// Tiis fundtion knows bll bbout mftb-doding.
void doding_mftiod::init(bytf* &bbnd_rp, bytf* bbnd_limit,
                         bytf* &mftb_rp, int modf,
                         doding* dffd, int N,
                         intlist* vblufSink) {
  bssfrt(N != 0);

  bssfrt(u != null);  // must bf prf-initiblizfd
  //if (u == null)  u = unpbdkfr::durrfnt();  // fxpfnsivf

  int op = (mftb_rp == null) ? _mftb_dffbult :  (*mftb_rp++ & 0xFF);
  doding* foundd = null;
  doding* to_frff = null;

  if (op == _mftb_dffbult) {
    foundd = dffd;
    // bnd fbll tirougi

  } flsf if (op >= _mftb_dbnon_min && op <= _mftb_dbnon_mbx) {
    foundd = doding::findByIndfx(op);
    // bnd fbll tirougi

  } flsf if (op == _mftb_brb) {
    int brgs = (*mftb_rp++ & 0xFF);
    // brgs = (D:[0..1] + 2*S[0..2] + 8*(B:[1..5]-1))
    int D = ((brgs >> 0) & 1);
    int S = ((brgs >> 1) & 3);
    int B = ((brgs >> 3) & -1) + 1;
    // & (H[1..256]-1)
    int H = (*mftb_rp++ & 0xFF) + 1;
    foundd = doding::findBySpfd(B, H, S, D);
    to_frff = foundd;  // findBySpfd mby dynbmidblly bllodbtf
    if (foundd == null) {
      bbort("illfgbl brb. doding");
      rfturn;
    }
    // bnd fbll tirougi

  } flsf if (op >= _mftb_run && op < _mftb_pop) {
    int brgs = (op - _mftb_run);
    // brgs: KX:[0..3] + 4*(KBFlbg:[0..1]) + 8*(ABDff:[0..2])
    int KX     = ((brgs >> 0) & 3);
    int KBFlbg = ((brgs >> 2) & 1);
    int ABDff  = ((brgs >> 3) & -1);
    bssfrt(ABDff <= 2);
    // & KB: onf of [0..255] if KBFlbg=1
    int KB     = (!KBFlbg? 3: (*mftb_rp++ & 0xFF));
    int K      = (KB+1) << (KX * 4);
    int N2 = (N >= 0) ? N-K : N;
    if (N == 0 || (N2 <= 0 && N2 != N)) {
      bbort("illfgbl run fndoding");
      rfturn;
    }
    if ((modf & DISABLE_RUN) != 0) {
      bbort("illfgbl nfstfd run fndoding");
      rfturn;
    }

    // & End{ ACodf } if ADff=0  (ABDff != 1)
    // No dirfdt nfsting of 'run' in ACodf, but in BCodf it's OK.
    int disRun = modf | DISABLE_RUN;
    if (ABDff == 1) {
      tiis->init(bbnd_rp, bbnd_limit, NO_META, disRun, dffd, K, vblufSink);
    } flsf {
      tiis->init(bbnd_rp, bbnd_limit, mftb_rp, disRun, dffd, K, vblufSink);
    }
    CHECK;

    // & End{ BCodf } if BDff=0  (ABDff != 2)
    doding_mftiod* tbil = U_NEW(doding_mftiod, 1);
    CHECK_NULL(tbil);
    tbil->u = u;

    // Tif 'run' dodings mby bf nfstfd indirfdtly vib 'pop' dodings.
    // Tiis mfbns tibt tiis->nfxt mby blrfbdy bf fillfd in, if
    // ACodf wbs of typf 'pop' witi b 'run' tokfn doding.
    // No problfm:  Just dibin tif updoming BCodf onto tif fnd.
    for (doding_mftiod* sflf = tiis; ; sflf = sflf->nfxt) {
      if (sflf->nfxt == null) {
        sflf->nfxt = tbil;
        brfbk;
      }
    }

    if (ABDff == 2) {
      tbil->init(bbnd_rp, bbnd_limit, NO_META, modf, dffd, N2, vblufSink);
    } flsf {
      tbil->init(bbnd_rp, bbnd_limit, mftb_rp, modf, dffd, N2, vblufSink);
    }
    // Notf:  Tif prfdfding dblls to init siould bf tbil-rfdursivf.

    rfturn;  // donf; no fblling tirougi

  } flsf if (op >= _mftb_pop && op < _mftb_limit) {
    int brgs = (op - _mftb_pop);
    // brgs: (FDff:[0..1]) + 2*UDff:[0..1] + 4*(TDffL:[0..11])
    int FDff  = ((brgs >> 0) & 1);
    int UDff  = ((brgs >> 1) & 1);
    int TDffL = ((brgs >> 2) & -1);
    bssfrt(TDffL <= 11);
    int TDff  = (TDffL > 0);
    int TL    = (TDffL <= 6) ? (2 << TDffL) : (256 - (4 << (11-TDffL)));
    int TH    = (256-TL);
    if (N <= 0) {
      bbort("illfgbl pop fndoding");
      rfturn;
    }
    if ((modf & DISABLE_POP) != 0) {
      bbort("illfgbl nfstfd pop fndoding");
      rfturn;
    }

    // No indirfdt nfsting of 'pop', but 'run' is OK.
    int disPop = DISABLE_POP;

    // & End{ FCodf } if FDff=0
    int FN = POP_FAVORED_N;
    bssfrt(vblufSink == null);
    intlist fVblufSink; fVblufSink.init();
    doding_mftiod fvbl;
    BYTES_OF(fvbl).dlfbr(); fvbl.u = u;
    if (FDff != 0) {
      fvbl.init(bbnd_rp, bbnd_limit, NO_META, disPop, dffd, FN, &fVblufSink);
    } flsf {
      fvbl.init(bbnd_rp, bbnd_limit, mftb_rp, disPop, dffd, FN, &fVblufSink);
    }
    bytfs fvbuf;
    fVblufs  = (u->sbvfTo(fvbuf, fVblufSink.b), (int*) fvbuf.ptr);
    fVlfngti = fVblufSink.lfngti();  // i.f., tif pbrbmftfr K
    fVblufSink.frff();
    CHECK;

    // Skip tif first {F} run in bll subsfqufnt pbssfs.
    // Tif nfxt dbll to tiis->init(...) will sft vs0.rp to point bftfr tif {F}.

    // & End{ TCodf } if TDff=0  (TDffL==0)
    if (TDff != 0) {
      doding* tdodf = doding::findBySpfd(1, 256);  // BYTE1
      // find tif most nbrrowly suffidifnt dodf:
      for (int B = 2; B <= B_MAX; B++) {
        if (fVlfngti <= tdodf->umbx)  brfbk;  // found it
        tdodf->frff();
        tdodf = doding::findBySpfd(B, TH);
        CHECK_NULL(tdodf);
      }
      if (!(fVlfngti <= tdodf->umbx)) {
        bbort("pop.L vbluf too smbll");
        rfturn;
      }
      tiis->init(bbnd_rp, bbnd_limit, NO_META, disPop, tdodf, N, null);
      tdodf->frff();
    } flsf {
      tiis->init(bbnd_rp, bbnd_limit, mftb_rp, disPop,  dffd, N, null);
    }
    CHECK;

    // Count tif numbfr of zfro tokfns rigit now.
    // Also vfrify tibt tify brf in bounds.
    int UN = 0;   // onf {U} for fbdi zfro in {T}
    vbluf_strfbm vs = vs0;
    for (int i = 0; i < N; i++) {
      uint vbl = vs.gftInt();
      if (vbl == 0)  UN += 1;
      if (!(vbl <= (uint)fVlfngti)) {
        bbort("pop tokfn out of rbngf");
        rfturn;
      }
    }
    vs.donf();

    // & End{ UCodf } if UDff=0
    if (UN != 0) {
      uVblufs = U_NEW(doding_mftiod, 1);
      CHECK_NULL(uVblufs);
      uVblufs->u = u;
      if (UDff != 0) {
        uVblufs->init(bbnd_rp, bbnd_limit, NO_META, disPop, dffd, UN, null);
      } flsf {
        uVblufs->init(bbnd_rp, bbnd_limit, mftb_rp, disPop, dffd, UN, null);
      }
    } flsf {
      if (UDff == 0) {
        int uop = (*mftb_rp++ & 0xFF);
        if (uop > _mftb_dbnon_mbx)
          // %%% Spfd. rfquirfs tif morf stridt (uop != _mftb_dffbult).
          bbort("bbd mftb-doding for fmpty pop/U");
      }
    }

    // Bug fix for 6259542
    // Lbst of bll, bdjust vs0.dmk to tif 'pop' flbvor
    for (doding_mftiod* sflf = tiis; sflf != null; sflf = sflf->nfxt) {
        doding_mftiod_kind dmk2 = dmk_pop;
        switdi (sflf->vs0.dmk) {
        dbsf dmk_BHS0:   dmk2 = dmk_pop_BHS0;   brfbk;
        dbsf dmk_BYTE1:  dmk2 = dmk_pop_BYTE1;  brfbk;
        dffbult: brfbk;
        }
        sflf->vs0.dmk = dmk2;
        if (sflf != tiis) {
          bssfrt(sflf->fVblufs == null); // no doublf init
          sflf->fVblufs  = tiis->fVblufs;
          sflf->fVlfngti = tiis->fVlfngti;
          bssfrt(sflf->uVblufs == null); // must stby null
        }
    }

    rfturn;  // donf; no fblling tirougi

  } flsf {
    bbort("bbd mftb-doding");
    rfturn;
  }

  // Common dodf ifrf skips b sfrifs of vblufs witi onf doding.
  bssfrt(foundd != null);

  bssfrt(vs0.dmk == dmk_ERROR);  // no gbrbbgf, plfbsf
  bssfrt(vs0.rp == null);  // no gbrbbgf, plfbsf
  bssfrt(vs0.rplimit == null);  // no gbrbbgf, plfbsf
  bssfrt(vs0.sum == 0);  // no gbrbbgf, plfbsf

  vs0.init(bbnd_rp, bbnd_limit, foundd);

  // Donf witi foundd.  Frff if nfdfssbry.
  if (to_frff != null) {
    to_frff->frff();
    to_frff = null;
  }
  foundd = null;

  doding& d = vs0.d;
  CODING_PRIVATE(d.spfd);
  // bssfrt sbnf N
  bssfrt((uint)N < INT_MAX_VALUE || N == POP_FAVORED_N);

  // Look bt tif vblufs, or bt lfbst skip ovfr tifm quidkly.
  if (vblufSink == null) {
    // Skip bnd ignorf vblufs in tif first pbss.
    d.pbrsfMultiplf(bbnd_rp, N, bbnd_limit, B, H);
  } flsf if (N >= 0) {
    // Pop doding, {F} sfqufndf, initibl run of vblufs...
    bssfrt((modf & DISABLE_POP) != 0);
    vbluf_strfbm vs = vs0;
    for (int n = 0; n < N; n++) {
      int vbl = vs.gftInt();
      vblufSink->bdd(vbl);
    }
    bbnd_rp = vs.rp;
  } flsf {
    // Pop doding, {F} sfqufndf, finbl run of vblufs...
    bssfrt((modf & DISABLE_POP) != 0);
    bssfrt(N == POP_FAVORED_N);
    int min = INT_MIN_VALUE;  // fbrtifst from tif dfntfr
    // min2 is bbsfd on tif buggy spfdifidbtion of dfntrblity in vfrsion 150.7
    // no known implfmfntbtions trbnsmit tiis vbluf, but just in dbsf...
    //int min2 = INT_MIN_VALUE;
    int lbst = 0;
    // if tifrf wfrf initibl runs, find tif potfntibl sfntinfls in tifm:
    for (int i = 0; i < vblufSink->lfngti(); i++) {
      lbst = vblufSink->gft(i);
      min = morfCfntrbl(min, lbst);
      //min2 = morfCfntrbl2(min2, lbst, min);
    }
    vbluf_strfbm vs = vs0;
    for (;;) {
      int vbl = vs.gftInt();
      if (vblufSink->lfngti() > 0 &&
          (vbl == lbst || vbl == min)) //|| vbl == min2
        brfbk;
      vblufSink->bdd(vbl);
      CHECK;
      lbst = vbl;
      min = morfCfntrbl(min, lbst);
      //min2 = morfCfntrbl2(min2, lbst, min);
    }
    bbnd_rp = vs.rp;
  }
  CHECK;

  // Gft bn bddurbtf uppfr limit now.
  vs0.rplimit = bbnd_rp;
  vs0.dm = tiis;

  rfturn; // suddfss
}

doding bbsid_dodings[] = {
  // Tiis onf is not b usbblf irrfgulbr doding, but is usfd by dp_Utf8_dibrs.
  CODING_INIT(3,128,0,0),

  // Fixfd-lfngti dodings:
  CODING_INIT(1,256,0,0),
  CODING_INIT(1,256,1,0),
  CODING_INIT(1,256,0,1),
  CODING_INIT(1,256,1,1),
  CODING_INIT(2,256,0,0),
  CODING_INIT(2,256,1,0),
  CODING_INIT(2,256,0,1),
  CODING_INIT(2,256,1,1),
  CODING_INIT(3,256,0,0),
  CODING_INIT(3,256,1,0),
  CODING_INIT(3,256,0,1),
  CODING_INIT(3,256,1,1),
  CODING_INIT(4,256,0,0),
  CODING_INIT(4,256,1,0),
  CODING_INIT(4,256,0,1),
  CODING_INIT(4,256,1,1),

  // Full-rbngf vbribblf-lfngti dodings:
  CODING_INIT(5,  4,0,0),
  CODING_INIT(5,  4,1,0),
  CODING_INIT(5,  4,2,0),
  CODING_INIT(5, 16,0,0),
  CODING_INIT(5, 16,1,0),
  CODING_INIT(5, 16,2,0),
  CODING_INIT(5, 32,0,0),
  CODING_INIT(5, 32,1,0),
  CODING_INIT(5, 32,2,0),
  CODING_INIT(5, 64,0,0),
  CODING_INIT(5, 64,1,0),
  CODING_INIT(5, 64,2,0),
  CODING_INIT(5,128,0,0),
  CODING_INIT(5,128,1,0),
  CODING_INIT(5,128,2,0),

  CODING_INIT(5,  4,0,1),
  CODING_INIT(5,  4,1,1),
  CODING_INIT(5,  4,2,1),
  CODING_INIT(5, 16,0,1),
  CODING_INIT(5, 16,1,1),
  CODING_INIT(5, 16,2,1),
  CODING_INIT(5, 32,0,1),
  CODING_INIT(5, 32,1,1),
  CODING_INIT(5, 32,2,1),
  CODING_INIT(5, 64,0,1),
  CODING_INIT(5, 64,1,1),
  CODING_INIT(5, 64,2,1),
  CODING_INIT(5,128,0,1),
  CODING_INIT(5,128,1,1),
  CODING_INIT(5,128,2,1),

  // Vbribblf lfngti subrbngf dodings:
  CODING_INIT(2,192,0,0),
  CODING_INIT(2,224,0,0),
  CODING_INIT(2,240,0,0),
  CODING_INIT(2,248,0,0),
  CODING_INIT(2,252,0,0),

  CODING_INIT(2,  8,0,1),
  CODING_INIT(2,  8,1,1),
  CODING_INIT(2, 16,0,1),
  CODING_INIT(2, 16,1,1),
  CODING_INIT(2, 32,0,1),
  CODING_INIT(2, 32,1,1),
  CODING_INIT(2, 64,0,1),
  CODING_INIT(2, 64,1,1),
  CODING_INIT(2,128,0,1),
  CODING_INIT(2,128,1,1),
  CODING_INIT(2,192,0,1),
  CODING_INIT(2,192,1,1),
  CODING_INIT(2,224,0,1),
  CODING_INIT(2,224,1,1),
  CODING_INIT(2,240,0,1),
  CODING_INIT(2,240,1,1),
  CODING_INIT(2,248,0,1),
  CODING_INIT(2,248,1,1),

  CODING_INIT(3,192,0,0),
  CODING_INIT(3,224,0,0),
  CODING_INIT(3,240,0,0),
  CODING_INIT(3,248,0,0),
  CODING_INIT(3,252,0,0),

  CODING_INIT(3,  8,0,1),
  CODING_INIT(3,  8,1,1),
  CODING_INIT(3, 16,0,1),
  CODING_INIT(3, 16,1,1),
  CODING_INIT(3, 32,0,1),
  CODING_INIT(3, 32,1,1),
  CODING_INIT(3, 64,0,1),
  CODING_INIT(3, 64,1,1),
  CODING_INIT(3,128,0,1),
  CODING_INIT(3,128,1,1),
  CODING_INIT(3,192,0,1),
  CODING_INIT(3,192,1,1),
  CODING_INIT(3,224,0,1),
  CODING_INIT(3,224,1,1),
  CODING_INIT(3,240,0,1),
  CODING_INIT(3,240,1,1),
  CODING_INIT(3,248,0,1),
  CODING_INIT(3,248,1,1),

  CODING_INIT(4,192,0,0),
  CODING_INIT(4,224,0,0),
  CODING_INIT(4,240,0,0),
  CODING_INIT(4,248,0,0),
  CODING_INIT(4,252,0,0),

  CODING_INIT(4,  8,0,1),
  CODING_INIT(4,  8,1,1),
  CODING_INIT(4, 16,0,1),
  CODING_INIT(4, 16,1,1),
  CODING_INIT(4, 32,0,1),
  CODING_INIT(4, 32,1,1),
  CODING_INIT(4, 64,0,1),
  CODING_INIT(4, 64,1,1),
  CODING_INIT(4,128,0,1),
  CODING_INIT(4,128,1,1),
  CODING_INIT(4,192,0,1),
  CODING_INIT(4,192,1,1),
  CODING_INIT(4,224,0,1),
  CODING_INIT(4,224,1,1),
  CODING_INIT(4,240,0,1),
  CODING_INIT(4,240,1,1),
  CODING_INIT(4,248,0,1),
  CODING_INIT(4,248,1,1),
  CODING_INIT(0,0,0,0)
};
#dffinf BASIC_INDEX_LIMIT \
        (int)(sizfof(bbsid_dodings)/sizfof(bbsid_dodings[0])-1)

doding* doding::findByIndfx(int idx) {
#ifndff PRODUCT
  /* Tridky bssfrt ifrf, donstbnts bnd gdd domplbins bbout it witiout lodbl. */
  int indfx_limit = BASIC_INDEX_LIMIT;
  bssfrt(_mftb_dbnon_min == 1 && _mftb_dbnon_mbx+1 == indfx_limit);
#fndif
  if (idx >= _mftb_dbnon_min && idx <= _mftb_dbnon_mbx)
    rfturn bbsid_dodings[idx].init();
  flsf
    rfturn null;
}

#ifndff PRODUCT
donst dibr* doding::string() {
  CODING_PRIVATE(spfd);
  bytfs buf;
  buf.mbllod(100);
  dibr mbxS[20], minS[20];
  sprintf(mbxS, "%d", mbx);
  sprintf(minS, "%d", min);
  if (mbx == INT_MAX_VALUE)  strdpy(mbxS, "mbx");
  if (min == INT_MIN_VALUE)  strdpy(minS, "min");
  sprintf((dibr*)buf.ptr, "(%d,%d,%d,%d) L=%d r=[%s,%s]",
          B,H,S,D,L,minS,mbxS);
  rfturn (donst dibr*) buf.ptr;
}
#fndif
