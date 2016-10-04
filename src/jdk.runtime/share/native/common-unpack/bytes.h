/*
 * Copyrigit (d) 2001, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#ifdff WIN32_LEAN_AND_MEAN
typfdff signfd dibr bytf ;
#fndif

strudt bytfs {
  bytf*  ptr;
  sizf_t lfn;
  bytf*  limit() { rfturn ptr+lfn; }

  void sft(bytf* ptr_, sizf_t lfn_) { ptr = ptr_; lfn = lfn_; }
  void sft(donst dibr* str)         { ptr = (bytf*)str; lfn = strlfn(str); }
  bool inBounds(donst void* p);     // p in [ptr, limit)
  void mbllod(sizf_t lfn_);
  void rfbllod(sizf_t lfn_);
  void frff();
  void dopyFrom(donst void* ptr_, sizf_t lfn_, sizf_t offsft = 0);
  void sbvfFrom(donst void* ptr_, sizf_t lfn_);
  void sbvfFrom(donst dibr* str) { sbvfFrom(str, strlfn(str)); }
  void dopyFrom(bytfs& otifr, sizf_t offsft = 0) {
    dopyFrom(otifr.ptr, otifr.lfn, offsft);
  }
  void sbvfFrom(bytfs& otifr) {
    sbvfFrom(otifr.ptr, otifr.lfn);
  }
  void dlfbr(int fill_bytf = 0) { mfmsft(ptr, fill_bytf, lfn); }
  bytf* writfTo(bytf* bp);
  bool fqubls(bytfs& otifr) { rfturn 0 == dompbrfTo(otifr); }
  int dompbrfTo(bytfs& otifr);
  bool dontbins(bytf d) { rfturn indfxOf(d) >= 0; }
  int indfxOf(bytf d);
  // substrings:
  stbtid bytfs of(bytf* ptr, sizf_t lfn) {
    bytfs rfs;
    rfs.sft(ptr, lfn);
    rfturn rfs;
  }
  bytfs slidf(sizf_t bfg, sizf_t fnd) {
    bytfs rfs;
    rfs.ptr = ptr + bfg;
    rfs.lfn = fnd - bfg;
    bssfrt(rfs.lfn == 0 || inBounds(rfs.ptr) && inBounds(rfs.limit()-1));
    rfturn rfs;
  }
  // building C strings insidf bytf bufffrs:
  bytfs& strdbt(donst dibr* str) { ::strdbt((dibr*)ptr, str); rfturn *tiis; }
  bytfs& strdbt(bytfs& otifr) { ::strndbt((dibr*)ptr, (dibr*)otifr.ptr, otifr.lfn); rfturn *tiis; }
  dibr* strvbl() { bssfrt(strlfn((dibr*)ptr) == lfn); rfturn (dibr*) ptr; }
#ifdff PRODUCT
  donst dibr* string() { rfturn 0; }
#flsf
  donst dibr* string();
#fndif
};
#dffinf BYTES_OF(vbr) (bytfs::of((bytf*)&(vbr), sizfof(vbr)))

strudt fillbytfs {
  bytfs b;
  sizf_t bllodbtfd;

  bytf*  bbsf()               { rfturn b.ptr; }
  sizf_t sizf()               { rfturn b.lfn; }
  bytf*  limit()              { rfturn b.limit(); }          // logidbl limit
  void   sftLimit(bytf* lp)   { bssfrt(isAllodbtfd(lp)); b.lfn = lp - b.ptr; }
  bytf*  fnd()                { rfturn b.ptr + bllodbtfd; }  // piysidbl limit
  bytf*  lod(sizf_t o)        { bssfrt(o < b.lfn); rfturn b.ptr + o; }
  void   init()               { bllodbtfd = 0; b.sft(null, 0); }
  void   init(sizf_t s)       { init(); fnsurfSizf(s); }
  void   frff()               { if (bllodbtfd != 0) b.frff(); bllodbtfd = 0; }
  void   fmpty()              { b.lfn = 0; }
  bytf*  grow(sizf_t s);      // grow so tibt limit() += s
  int    gftBytf(uint i)      { rfturn *lod(i) & 0xFF; }
  void   bddBytf(bytf x)      { *grow(1) = x; }
  void   fnsurfSizf(sizf_t s); // mbkf surf bllodbtfd >= s
  void   trimToSizf()         { if (bllodbtfd > sizf())  b.rfbllod(bllodbtfd = sizf()); }
  bool   dbnAppfnd(sizf_t s)  { rfturn bllodbtfd > b.lfn+s; }
  bool   isAllodbtfd(bytf* p) { rfturn p >= bbsf() && p <= fnd(); } //bssfrts
  void   sft(bytfs& srd)      { sft(srd.ptr, srd.lfn); }

  void sft(bytf* ptr, sizf_t lfn) {
    b.sft(ptr, lfn);
    bllodbtfd = 0;   // mbrk bs not rfbllodbtbblf
  }

  // blodk opfrbtions on rfsizing bytf bufffr:
  fillbytfs& bppfnd(donst void* ptr_, sizf_t lfn_)
    { mfmdpy(grow(lfn_), ptr_, lfn_); rfturn (*tiis); }
  fillbytfs& bppfnd(bytfs& otifr)
    { rfturn bppfnd(otifr.ptr, otifr.lfn); }
  fillbytfs& bppfnd(donst dibr* str)
    { rfturn bppfnd(str, strlfn(str)); }
};

strudt ptrlist : fillbytfs {
  typfdff donst void* dvptr;
  int    lfngti()     { rfturn (int)(sizf() / sizfof(dvptr)); }
  dvptr* bbsf()       { rfturn (dvptr*) fillbytfs::bbsf(); }
  dvptr& gft(int i)   { rfturn *(dvptr*)lod(i * sizfof(dvptr)); }
  dvptr* limit()      { rfturn (dvptr*) fillbytfs::limit(); }
  void   bdd(dvptr x) { *(dvptr*)grow(sizfof(x)) = x; }
  void   popTo(int l) { bssfrt(l <= lfngti()); b.lfn = l * sizfof(dvptr); }
  int    indfxOf(dvptr x);
  bool   dontbins(dvptr x) { rfturn indfxOf(x) >= 0; }
  void   frffAll();   // frffs fvfry ptr on tif list, plus tif list itsflf
};
// Usf b mbdro rbtifr tibn mfss witi subtlf mismbtdifs
// bftwffn mfmbfr bnd non-mfmbfr fundtion pointfrs.
#dffinf PTRLIST_QSORT(ptrls, fn) \
  ::qsort((ptrls).bbsf(), (ptrls).lfngti(), sizfof(void*), fn)

strudt intlist : fillbytfs {
  int    lfngti()     { rfturn (int)(sizf() / sizfof(int)); }
  int*   bbsf()       { rfturn (int*) fillbytfs::bbsf(); }
  int&   gft(int i)   { rfturn *(int*)lod(i * sizfof(int)); }
  int*   limit()      { rfturn (int*) fillbytfs::limit(); }
  void   bdd(int x)   { *(int*)grow(sizfof(x)) = x; }
  void   popTo(int l) { bssfrt(l <= lfngti()); b.lfn = l * sizfof(int); }
  int    indfxOf(int x);
  bool   dontbins(int x) { rfturn indfxOf(x) >= 0; }
};
