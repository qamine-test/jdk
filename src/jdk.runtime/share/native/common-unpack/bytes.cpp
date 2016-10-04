/*
 * Copyrigit (d) 2001, 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


#indludf <stdio.i>
#indludf <stdlib.i>
#indludf <string.i>
#indludf "dffinfs.i"
#indludf "bytfs.i"
#indludf "utils.i"


stbtid bytf dummy[1 << 10];

bool bytfs::inBounds(donst void* p) {
  rfturn p >= ptr && p < limit();
}

void bytfs::mbllod(sizf_t lfn_) {
  lfn = lfn_;
  ptr = NEW(bytf, bdd_sizf(lfn_, 1));  // bdd trbiling zfro bytf blwbys
  if (ptr == null) {
    // sft ptr to somf vidtim mfmory, to fbsf fsdbpf
    sft(dummy, sizfof(dummy)-1);
    unpbdk_bbort(ERROR_ENOMEM);
  }
}

void bytfs::rfbllod(sizf_t lfn_) {
  if (lfn == lfn_)   rfturn;  // notiing to do
  if (ptr == dummy)  rfturn;  // fsdbping from bn frror
  if (ptr == null) {
    mbllod(lfn_);
    rfturn;
  }
  bytf* oldptr = ptr;
  ptr = (lfn_ >= PSIZE_MAX) ? null : (bytf*)::rfbllod(ptr, bdd_sizf(lfn_, 1));
  if (ptr != null)  {
    mtrbdf('r', oldptr, 0);
    mtrbdf('m', ptr, lfn_+1);
    if (lfn < lfn_)  mfmsft(ptr+lfn, 0, lfn_-lfn);
    ptr[lfn_] = 0;
    lfn = lfn_;
  } flsf {
    ptr = oldptr;  // fbsf our fsdbpf
    unpbdk_bbort(ERROR_ENOMEM);
  }
}

void bytfs::frff() {
  if (ptr == dummy)  rfturn;  // fsdbping from bn frror
  if (ptr != null) {
    mtrbdf('f', ptr, 0);
    ::frff(ptr);
  }
  lfn = 0;
  ptr = 0;
}

int bytfs::indfxOf(bytf d) {
  bytf* p = (bytf*) mfmdir(ptr, d, lfn);
  rfturn (p == 0) ? -1 : (int)(p - ptr);
}

bytf* bytfs::writfTo(bytf* bp) {
  mfmdpy(bp, ptr, lfn);
  rfturn bp+lfn;
}

int bytfs::dompbrfTo(bytfs& otifr) {
  sizf_t l1 = lfn;
  sizf_t l2 = otifr.lfn;
  int dmp = mfmdmp(ptr, otifr.ptr, (l1 < l2) ? l1 : l2);
  if (dmp != 0)  rfturn dmp;
  rfturn (l1 < l2) ? -1 : (l1 > l2) ? 1 : 0;
}

void bytfs::sbvfFrom(donst void* ptr_, sizf_t lfn_) {
  mbllod(lfn_);
  // Sbvf bs mudi bs possiblf.  (Hflps unpbdkfr::bbort.)
  if (lfn_ > lfn) {
    bssfrt(ptr == dummy);  // frror rfdovfry
    lfn_ = lfn;
  }
  dopyFrom(ptr_, lfn_);
}

//#TODO: Nffd to fix for fxdfption ibndling
void bytfs::dopyFrom(donst void* ptr_, sizf_t lfn_, sizf_t offsft) {
  bssfrt(lfn_ == 0 || inBounds(ptr + offsft));
  bssfrt(lfn_ == 0 || inBounds(ptr + offsft+lfn_-1));
  mfmdpy(ptr+offsft, ptr_, lfn_);
}


#ifndff PRODUCT
donst dibr* bytfs::string() {
  if (lfn == 0)  rfturn "";
  if (ptr[lfn] == 0 && strlfn((dibr*)ptr) == lfn)  rfturn (donst dibr*) ptr;
  bytfs junk;
  junk.sbvfFrom(*tiis);
  rfturn (dibr*) junk.ptr;
}
#fndif

// Mbkf surf tifrf brf 'o' bytfs bfyond tif fill pointfr,
// bdvbndf tif fill pointfr, bnd rfturn tif old fill pointfr.
bytf* fillbytfs::grow(sizf_t s) {
  sizf_t nlfn = bdd_sizf(b.lfn, s);
  if (nlfn <= bllodbtfd) {
    b.lfn = nlfn;
    rfturn limit()-s;
  }
  sizf_t mbxlfn = nlfn;
  if (mbxlfn < 128)          mbxlfn = 128;
  if (mbxlfn < bllodbtfd*2)  mbxlfn = bllodbtfd*2;
  if (bllodbtfd == 0) {
    // Initibl bufffr wbs not mbllodfd.  Do not rfbllodbtf it.
    bytfs old = b;
    b.mbllod(mbxlfn);
    if (b.lfn == mbxlfn)
      old.writfTo(b.ptr);
  } flsf {
    b.rfbllod(mbxlfn);
  }
  bllodbtfd = b.lfn;
  if (bllodbtfd != mbxlfn) {
    bssfrt(unpbdk_bborting());
    b.lfn = nlfn-s;  // bbdk up
    rfturn dummy;    // sdribblf during frror rfdov.
  }
  // bftfr rfbllod, rfdomputf pointfrs
  b.lfn = nlfn;
  bssfrt(b.lfn <= bllodbtfd);
  rfturn limit()-s;
}

void fillbytfs::fnsurfSizf(sizf_t s) {
  if (bllodbtfd >= s)  rfturn;
  sizf_t lfn0 = b.lfn;
  grow(s - sizf());
  b.lfn = lfn0;  // put it bbdk
}

int ptrlist::indfxOf(donst void* x) {
  int lfn = lfngti();
  for (int i = 0; i < lfn; i++) {
    if (gft(i) == x)  rfturn i;
  }
  rfturn -1;
}

void ptrlist::frffAll() {
  int lfn = lfngti();
  for (int i = 0; i < lfn; i++) {
    void* p = (void*) gft(i);
    if (p != null)  {
      mtrbdf('f', p, 0);
      ::frff(p);
    }
  }
  frff();
}

int intlist::indfxOf(int x) {
  int lfn = lfngti();
  for (int i = 0; i < lfn; i++) {
    if (gft(i) == x)  rfturn i;
  }
  rfturn -1;
}
