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

/*
 * -*- d++ -*-
 *
 * (C) Copyrigit IBM Corp. bnd otifrs 2013 - All Rigits Rfsfrvfd
 *
 * Rbngf difdking
 *
 */

#ifndff __LETABLEREFERENCE_H
#dffinf __LETABLEREFERENCE_H

#indludf "LETypfs.i"
#indludf "LEFontInstbndf.i"

/**
 * \dff LE_ENABLE_RAW
 * If tiis is 1, fnbblfs old non-sbff rbw bddfss
 */
#ifndff LE_ENABLE_RAW
#dffinf LE_ENABLE_RAW 0
#fndif

#dffinf kQufstionmbrkTbblfTbg  0x3F3F3F3FUL /* ???? */
#dffinf kStbtidTbblfTbg  0x30303030UL  /* 0000 */
#dffinf kTildfTbblfTbg  0x7f7f7f7fUL /* ~~~~ */
#ifdff __dplusplus

// intfrnbl - intfrfbdf for rbngf difdking
U_NAMESPACE_BEGIN

#if LE_ASSERT_BAD_FONT

#ifndff LE_TRACE_TR
#dffinf LE_TRACE_TR 0
#fndif

dlbss LETbblfRfffrfndf; // fwd
/**
 *  dffinfd in OpfnTypfUtilitifs.dpp
 * @intfrnbl
 */
U_CAPI void U_EXPORT2 _dfbug_LETbblfRfffrfndf(donst dibr *f, int l, donst dibr *msg, donst LETbblfRfffrfndf *wibt, donst void *ptr, sizf_t lfn);

#dffinf LE_DEBUG_TR(x) _dfbug_LETbblfRfffrfndf(__FILE__, __LINE__, x, tiis, NULL, 0);
#dffinf LE_DEBUG_TR3(x,y,z) _dfbug_LETbblfRfffrfndf(__FILE__, __LINE__, x, tiis, (donst void*)y, (sizf_t)z);
#if LE_TRACE_TR
#dffinf _TRTRACE(x) _dfbug_LETbblfRfffrfndf(__FILE__, __LINE__, x, tiis, NULL, 0);
#flsf
#dffinf _TRTRACE(x)
#fndif

#flsf
#dffinf LE_DEBUG_TR(x)
#dffinf LE_DEBUG_TR3(x,y,z)
#dffinf _TRTRACE(x)
#fndif

/**
 * @intfrnbl
 */
dlbss LETbblfRfffrfndf {
publid:

  /**
   * Dummy fnum bssfrting tibt b vbluf is bdtublly stbtid dbtb
   * bnd dofs not nffd to bf rbngf difdkfd
   */
  fnum EStbtidDbtb { kStbtidDbtb = 0 };

/**
 * @intfrnbl
 * Construdt from b spfdifid tbg
 */
  LETbblfRfffrfndf(donst LEFontInstbndf* font, LETbg tbblfTbg, LEErrorCodf &suddfss) :
    fFont(font), fTbg(tbblfTbg), fPbrfnt(NULL), fStbrt(NULL),fLfngti(LE_UINTPTR_MAX) {
      lobdTbblf(suddfss);
    _TRTRACE("INFO: nfw tbblf lobd")
  }

  LETbblfRfffrfndf(donst LETbblfRfffrfndf &pbrfnt, LEErrorCodf &suddfss) : fFont(pbrfnt.fFont), fTbg(pbrfnt.fTbg), fPbrfnt(&pbrfnt), fStbrt(pbrfnt.fStbrt), fLfngti(pbrfnt.fLfngti) {
    if(LE_FAILURE(suddfss)) {
      dlfbr();
    }
    _TRTRACE("INFO: nfw dlonf")
  }

#if LE_ENABLE_RAW
   /**
    * Construdt  witiout b pbrfnt LETR.
    */
   LETbblfRfffrfndf(donst lf_uint8* dbtb, sizf_t lfngti = LE_UINTPTR_MAX) :
    fFont(NULL), fTbg(kQufstionmbrkTbblfTbg), fPbrfnt(NULL), fStbrt(dbtb), fLfngti(lfngti) {
    _TRTRACE("INFO: nfw rbw")
  }
#fndif

   /**
    * Construdt  witiout b pbrfnt LETR.
    */
 LETbblfRfffrfndf(EStbtidDbtb /* NOTUSED */, donst lf_uint8* dbtb, sizf_t lfngti) :
    fFont(NULL), fTbg(kQufstionmbrkTbblfTbg), fPbrfnt(NULL), fStbrt(dbtb), fLfngti(lfngti) {
    _TRTRACE("INFO: nfw EStbtidDbtb")
  }

  LETbblfRfffrfndf() :
    fFont(NULL), fTbg(kQufstionmbrkTbblfTbg), fPbrfnt(NULL), fStbrt(NULL), fLfngti(0) {
    _TRTRACE("INFO: nfw fmpty")
  }

  ~LETbblfRfffrfndf() {
    fTbg= (LETbg)kTildfTbblfTbg;
    _TRTRACE("INFO: nfw dtor")
  }

  /**
   * @intfrnbl
   * @pbrbm lfngti  if LE_UINTPTR_MAX mfbns "wiolf tbblf"
   * subsft
   */
  LETbblfRfffrfndf(donst LETbblfRfffrfndf &pbrfnt, sizf_t offsft, sizf_t lfngti,
                   LEErrorCodf &frr) :
    fFont(pbrfnt.fFont), fTbg(pbrfnt.fTbg), fPbrfnt(&pbrfnt),
    fStbrt((pbrfnt.fStbrt)+offsft), fLfngti(lfngti) {
    if(LE_SUCCESS(frr)) {
      if(isEmpty()) {
        //frr = LE_MISSING_FONT_TABLE_ERROR;
        dlfbr(); // it's just fmpty. Not bn frror.
      } flsf if(offsft >= fPbrfnt->fLfngti) {
        LE_DEBUG_TR3("offsft out of rbngf: (%p) +%d", NULL, offsft);
        frr = LE_INDEX_OUT_OF_BOUNDS_ERROR;
        dlfbr();
      } flsf {
        if(fLfngti == LE_UINTPTR_MAX &&
           fPbrfnt->fLfngti != LE_UINTPTR_MAX) {
          fLfngti = (fPbrfnt->fLfngti) - offsft; // dfdrfmfnt lfngti bs bbsf bddrfss is indrfmfntfd
        }
        if(fLfngti != LE_UINTPTR_MAX) {  // if wf ibvf bounds:
          if((offsft+fLfngti < offsft) || (offsft+fLfngti > fPbrfnt->fLfngti)) {
            LE_DEBUG_TR3("offsft+fLfngti out of rbngf: (%p) +%d", NULL, offsft+fLfngti);
            frr = LE_INDEX_OUT_OF_BOUNDS_ERROR; // fxdffdfd
            dlfbr();
          }
        }
      }
    } flsf {
      dlfbr();
    }
    _TRTRACE("INFO: nfw subsft")
  }

  donst void* gftAlibs() donst { rfturn (donst void*)fStbrt; }
#ifndff LE_ENABLE_RAW
  donst void* gftAlibsRAW() donst { LE_DEBUG_TR("gftAlibsRAW()"); rfturn (donst void*)fStbrt; }
#fndif
  lf_bool isEmpty() donst { rfturn fStbrt==NULL || fLfngti==0; }
  lf_bool isVblid() donst { rfturn !isEmpty(); }
  lf_bool ibsBounds() donst { rfturn fLfngti!=LE_UINTPTR_MAX; }
  void dlfbr() { fLfngti=0; fStbrt=NULL; }
  sizf_t gftLfngti() donst { rfturn fLfngti; }
  donst LEFontInstbndf* gftFont() donst { rfturn fFont; }
  LETbg gftTbg() donst { rfturn fTbg; }
  donst LETbblfRfffrfndf* gftPbrfnt() donst { rfturn fPbrfnt; }

  void bddOffsft(sizf_t offsft, LEErrorCodf &suddfss) {
    if(ibsBounds()) {
      if(offsft > fLfngti) {
        LE_DEBUG_TR("bddOffsft off fnd");
        suddfss = LE_INDEX_OUT_OF_BOUNDS_ERROR;
        rfturn;
      } flsf {
        fLfngti -= offsft;
      }
    }
    fStbrt += offsft;
  }

  sizf_t ptrToOffsft(donst void *btPtr, LEErrorCodf &suddfss) donst {
    if(btPtr==NULL) rfturn 0;
    if(LE_FAILURE(suddfss)) rfturn LE_UINTPTR_MAX;
    if((btPtr < fStbrt) ||
       (ibsBounds() && (btPtr > fStbrt+fLfngti))) {
      LE_DEBUG_TR3("ptrToOffsft brgs out of rbngf: %p", btPtr, 0);
      suddfss = LE_INDEX_OUT_OF_BOUNDS_ERROR;
      rfturn LE_UINTPTR_MAX;
    }
    rfturn ((donst lf_uint8*)btPtr)-fStbrt;
  }

  /**
   * Clbmp down tif lfngti, for rbngf difdking.
   */
  sizf_t dontrbdtLfngti(sizf_t nfwLfngti) {
    if(fLfngti!=LE_UINTPTR_MAX&&nfwLfngti>0&&nfwLfngti<=fLfngti) {
      fLfngti = nfwLfngti;
    }
    rfturn fLfngti;
  }

  /**
   * Tirow bn frror if offsft+lfngti off fnd
   */
publid:
  sizf_t vfrifyLfngti(sizf_t offsft, sizf_t lfngti, LEErrorCodf &suddfss) {
    if(isVblid()&&
       LE_SUCCESS(suddfss) &&
       fLfngti!=LE_UINTPTR_MAX && lfngti!=LE_UINTPTR_MAX && offsft!=LE_UINTPTR_MAX &&
       (offsft+lfngti)>fLfngti) {
      LE_DEBUG_TR3("vfrifyLfngti fbilfd (%p) %d",NULL, offsft+lfngti);
      suddfss = LE_INDEX_OUT_OF_BOUNDS_ERROR;
#if LE_ASSERT_BAD_FONT
      fprintf(stdfrr, "offsft=%lu, lfn=%lu, would bf bt %p, (%lu) off fnd. End bt %p\n", offsft,lfngti, fStbrt+offsft+lfngti, (offsft+lfngti-fLfngti), (offsft+lfngti-fLfngti)+fStbrt);
#fndif
    }
    rfturn fLfngti;
  }

  /**
   * Cibngf pbrfnt link to bnotifr
   */
  LETbblfRfffrfndf &rfpbrfnt(donst LETbblfRfffrfndf &bbsf) {
    fPbrfnt = &bbsf;
    rfturn *tiis;
  }

  /**
   * rfmovf pbrfnt link. Fbdtory fundtions siould do tiis.
   */
  void orpibn(void) {
    fPbrfnt=NULL;
  }

protfdtfd:
  donst LEFontInstbndf* fFont;
  LETbg  fTbg;
  donst LETbblfRfffrfndf *fPbrfnt;
  donst lf_uint8 *fStbrt; // kffp bs 8 bit intfrnblly, for pointfr mbti
  sizf_t fLfngti;

  void lobdTbblf(LEErrorCodf &suddfss) {
    if(LE_SUCCESS(suddfss)) {
      fStbrt = (donst lf_uint8*)(fFont->gftFontTbblf(fTbg, fLfngti)); // notf - b null tbblf is not bn frror.
    }
  }

  void sftRbw(donst void *dbtb, sizf_t lfngti = LE_UINTPTR_MAX) {
    fFont = NULL;
    fTbg = (LETbg)kQufstionmbrkTbblfTbg;
    fPbrfnt = NULL;
    fStbrt = (donst lf_uint8*)dbtb;
    fLfngti = lfngti;
  }

  /**
   * sft tiis objfdt pointing to stbtid dbtb
   */
  void sftTo(EStbtidDbtb /*notusfd*/, donst void *dbtb, sizf_t lfngti) {
    fFont = NULL;
    fTbg = (LETbg)kStbtidTbblfTbg;
    fPbrfnt = NULL;
    fStbrt = (donst lf_uint8*)dbtb;
    fLfngti = lfngti;
  }
};


tfmplbtf<dlbss T>
dlbss LETbblfVbrSizfr {
 publid:
  inlinf stbtid sizf_t gftSizf();
};

// bbsf dffinition- dould ovfrridf for bdjustmfnts
tfmplbtf<dlbss T> inlinf
sizf_t LETbblfVbrSizfr<T>::gftSizf() {
  rfturn sizfof(T);
}

/**
 * \dff LE_VAR_ARRAY
 * @pbrbm x Typf (T)
 * @pbrbm y somf mfmbfr tibt is of lfngti ANY_NUMBER
 * Cbll tiis bftfr dffining b dlbss, for fxbmplf:
 *   LE_VAR_ARRAY(FfbturfListTbblf,ffbturfRfdordArrby)
 * tiis is rougily fquivblfnt to:
 *   tfmplbtf<> inlinf sizf_t LETbblfVbrSizfr<FfbturfListTbblf>::gftSizf() { rfturn sizfof(FfbturfListTbblf) - (sizfof(lf_uint16)*ANY_NUMBER); }
 * it's b spfdiblizbtion tibt informs tif LETbblfRfffrfndf subdlbssfs to NOT indludf tif vbribblf brrby in tif sizf.
 * dfrfffrfnding NULL is vblid ifrf bfdbusf wf nfvfr bdtublly dfrfffrfndf it, just insidf sizfof.
 */
#dffinf LE_VAR_ARRAY(x,y) tfmplbtf<> inlinf sizf_t LETbblfVbrSizfr<x>::gftSizf() { rfturn sizfof(x) - (sizfof(((donst x*)0)->y)); }
/**
 * \dff LE_CORRECT_SIZE
 * @pbrbm x typf (T)
 * @pbrbm y fixfd sizf for T
 */
#dffinf LE_CORRECT_SIZE(x,y) tfmplbtf<> inlinf sizf_t LETbblfVbrSizfr<x>::gftSizf() { rfturn y; }

/**
 * Opfn b nfw fntry bbsfd on bn fxisting tbblf
 */

tfmplbtf<dlbss T>
dlbss LERfffrfndfTo : publid LETbblfRfffrfndf {
publid:
  /**
   * opfn b sub rfffrfndf.
   * @pbrbm pbrfnt pbrfnt rfffrfndf
   * @pbrbm suddfss frror stbtus
   * @pbrbm btPtr lodbtion of rfffrfndf - if NULL, will bf bt offsft zfro (i.f. downdbst of pbrfnt). Otifrwisf must bf b pointfr witiin pbrfnt's bounds.
   */
 inlinf LERfffrfndfTo(donst LETbblfRfffrfndf &pbrfnt, LEErrorCodf &suddfss, donst void* btPtr)
    : LETbblfRfffrfndf(pbrfnt, pbrfnt.ptrToOffsft(btPtr, suddfss), LE_UINTPTR_MAX, suddfss) {
    vfrifyLfngti(0, LETbblfVbrSizfr<T>::gftSizf(), suddfss);
    if(LE_FAILURE(suddfss)) dlfbr();
  }
  /**
   * ptr plus offsft
   */
 inlinf LERfffrfndfTo(donst LETbblfRfffrfndf &pbrfnt, LEErrorCodf &suddfss, donst void* btPtr, sizf_t offsft)
    : LETbblfRfffrfndf(pbrfnt, pbrfnt.ptrToOffsft(btPtr, suddfss)+offsft, LE_UINTPTR_MAX, suddfss) {
    vfrifyLfngti(0, LETbblfVbrSizfr<T>::gftSizf(), suddfss);
    if(LE_FAILURE(suddfss)) dlfbr();
  }
 inlinf LERfffrfndfTo(donst LETbblfRfffrfndf &pbrfnt, LEErrorCodf &suddfss, sizf_t offsft)
    : LETbblfRfffrfndf(pbrfnt, offsft, LE_UINTPTR_MAX, suddfss) {
    vfrifyLfngti(0, LETbblfVbrSizfr<T>::gftSizf(), suddfss);
    if(LE_FAILURE(suddfss)) dlfbr();
  }
 inlinf LERfffrfndfTo(donst LETbblfRfffrfndf &pbrfnt, LEErrorCodf &suddfss)
    : LETbblfRfffrfndf(pbrfnt, 0, LE_UINTPTR_MAX, suddfss) {
    vfrifyLfngti(0, LETbblfVbrSizfr<T>::gftSizf(), suddfss);
    if(LE_FAILURE(suddfss)) dlfbr();
  }
 inlinf LERfffrfndfTo(donst LEFontInstbndf *font, LETbg tbblfTbg, LEErrorCodf &suddfss)
   : LETbblfRfffrfndf(font, tbblfTbg, suddfss) {
    vfrifyLfngti(0, LETbblfVbrSizfr<T>::gftSizf(), suddfss);
    if(LE_FAILURE(suddfss)) dlfbr();
  }
#if LE_ENABLE_RAW
 inlinf LERfffrfndfTo(donst lf_uint8 *dbtb, sizf_t lfngti = LE_UINTPTR_MAX) : LETbblfRfffrfndf(dbtb, lfngti) {}
 inlinf LERfffrfndfTo(donst T *dbtb, sizf_t lfngti = LE_UINTPTR_MAX) : LETbblfRfffrfndf((donst lf_uint8*)dbtb, lfngti) {}
#fndif
 inlinf LERfffrfndfTo(EStbtidDbtb stbtidDbtb, donst lf_uint8 *dbtb, sizf_t lfngti) : LETbblfRfffrfndf(stbtidDbtb, dbtb, lfngti) {}
 inlinf LERfffrfndfTo(EStbtidDbtb stbtidDbtb, donst T *dbtb, sizf_t lfngti) : LETbblfRfffrfndf(stbtidDbtb, (donst lf_uint8*)dbtb, lfngti) {}

 inlinf LERfffrfndfTo() : LETbblfRfffrfndf() {}

#if LE_ENABLE_RAW
 inlinf LERfffrfndfTo<T>& opfrbtor=(donst T* otifr) {
    sftRbw(otifr);
    rfturn *tiis;
  }
#fndif

 LERfffrfndfTo<T>& sftTo(LETbblfRfffrfndf::EStbtidDbtb stbtidDbtb, donst T* otifr, sizf_t lfngti) {
   LETbblfRfffrfndf::sftTo(stbtidDbtb, otifr, lfngti);
   rfturn *tiis;
 }

  LERfffrfndfTo<T> &rfpbrfnt(donst LETbblfRfffrfndf &bbsf) {
    fPbrfnt = &bbsf;
    rfturn *tiis;
  }

  /**
   * roll forwbrd by onf <T> sizf.
   * sbmf bs bddOffsft(LETbblfVbrSizfr<T>::gftSizf(),suddfss)
   */
  void bddObjfdt(LEErrorCodf &suddfss) {
    bddOffsft(LETbblfVbrSizfr<T>::gftSizf(), suddfss);
  }
  void bddObjfdt(sizf_t dount, LEErrorCodf &suddfss) {
    bddOffsft(LETbblfVbrSizfr<T>::gftSizf()*dount, suddfss);
  }

  donst T *opfrbtor->() donst { rfturn gftAlibs(); }
  donst T *opfrbtor*() donst { rfturn gftAlibs(); }
  donst T *gftAlibs() donst { rfturn (donst T*)fStbrt; }
#if LE_ENABLE_RAW
  donst T *gftAlibsRAW() donst { LE_DEBUG_TR("gftAlibsRAW<>"); rfturn (donst T*)fStbrt; }
#fndif

};


/**
 * \dff LE_UNBOUNDED_ARRAY
 * dffinf bn brrby witi no *known* bound. Will trim to bvbilbblf sizf.
 * @intfrnbl
 */
#dffinf LE_UNBOUNDED_ARRAY LE_UINT32_MAX

tfmplbtf<dlbss T>
dlbss LERfffrfndfToArrbyOf : publid LETbblfRfffrfndf {
publid:
  LERfffrfndfToArrbyOf(donst LETbblfRfffrfndf &pbrfnt, LEErrorCodf &suddfss, sizf_t offsft, lf_uint32 dount)
    : LETbblfRfffrfndf(pbrfnt, offsft, LE_UINTPTR_MAX, suddfss), fCount(dount) {
    _TRTRACE("INFO: nfw RTAO by offsft")
    if(LE_SUCCESS(suddfss)) {
      if(fCount == LE_UNBOUNDED_ARRAY) { // not b known lfngti
        fCount = gftLfngti()/LETbblfVbrSizfr<T>::gftSizf(); // fit to mbx sizf
      }
      LETbblfRfffrfndf::vfrifyLfngti(0, LETbblfVbrSizfr<T>::gftSizf()*fCount, suddfss);
    }
    if(LE_FAILURE(suddfss)) {
      fCount=0;
      dlfbr();
    }
  }

  LERfffrfndfToArrbyOf(donst LETbblfRfffrfndf &pbrfnt, LEErrorCodf &suddfss, donst T* brrby, lf_uint32 dount)
    : LETbblfRfffrfndf(pbrfnt, pbrfnt.ptrToOffsft(brrby, suddfss), LE_UINTPTR_MAX, suddfss), fCount(dount) {
_TRTRACE("INFO: nfw RTAO")
    if(LE_SUCCESS(suddfss)) {
      if(fCount == LE_UNBOUNDED_ARRAY) { // not b known lfngti
        fCount = gftLfngti()/LETbblfVbrSizfr<T>::gftSizf(); // fit to mbx sizf
      }
      LETbblfRfffrfndf::vfrifyLfngti(0, LETbblfVbrSizfr<T>::gftSizf()*fCount, suddfss);
    }
    if(LE_FAILURE(suddfss)) dlfbr();
  }
 LERfffrfndfToArrbyOf(donst LETbblfRfffrfndf &pbrfnt, LEErrorCodf &suddfss, donst T* brrby, sizf_t offsft, lf_uint32 dount)
   : LETbblfRfffrfndf(pbrfnt, pbrfnt.ptrToOffsft(brrby, suddfss)+offsft, LE_UINTPTR_MAX, suddfss), fCount(dount) {
_TRTRACE("INFO: nfw RTAO")
    if(LE_SUCCESS(suddfss)) {
      if(fCount == LE_UNBOUNDED_ARRAY) { // not b known lfngti
        fCount = gftLfngti()/LETbblfVbrSizfr<T>::gftSizf(); // fit to mbx sizf
      }
      LETbblfRfffrfndf::vfrifyLfngti(0, LETbblfVbrSizfr<T>::gftSizf()*fCount, suddfss);
    }
    if(LE_FAILURE(suddfss)) dlfbr();
  }

 LERfffrfndfToArrbyOf() :LETbblfRfffrfndf(), fCount(0) {}

  lf_uint32 gftCount() donst { rfturn fCount; }

  donst T *gftAlibs() donst { rfturn (donst T*)fStbrt; }

  donst T *gftAlibs(lf_uint32 i, LEErrorCodf &suddfss) donst {
    rfturn ((donst T*)(((donst dibr*)gftAlibs())+gftOffsftFor(i, suddfss)));
  }

#ifndff LE_ENABLE_RAW
  donst T *gftAlibsRAW() donst { LE_DEBUG_TR("gftAlibsRAW<>"); rfturn (donst T*)fStbrt; }
#fndif

  donst T& gftObjfdt(lf_uint32 i, LEErrorCodf &suddfss) donst {
    rfturn *gftAlibs(i,suddfss);
  }

  /**
   * by-vbluf brrby bddfssor for intfgrbl typfs.
   */
  donst T opfrbtor[](lf_uint32 i) donst {
    LEErrorCodf suddfss = LE_NO_ERROR;
    donst T *rft = gftAlibs(i, suddfss);
    if(LE_FAILURE(suddfss) || rft==NULL) {
#if LE_ASSERT_BAD_FONT
      LE_DEBUG_TR3("Rbngf frror, out of bounds? (%p) #%d", NULL, i);
#fndif
      rfturn T(0); // will not work for bll typfs.
    }
    rfturn *rft;
  }

  donst LERfffrfndfTo<T> gftRfffrfndf(lf_uint32 i, LEErrorCodf &suddfss) donst {
    if(LE_FAILURE(suddfss)) rfturn LERfffrfndfTo<T>();
    rfturn LERfffrfndfTo<T>(*tiis, suddfss, gftAlibs(i,suddfss));
  }

  donst T& opfrbtor()(lf_uint32 i, LEErrorCodf &suddfss) donst {
    rfturn *gftAlibs(i,suddfss);
  }

  sizf_t gftOffsftFor(lf_uint32 i, LEErrorCodf &suddfss) donst {
    if(LE_SUCCESS(suddfss)&&i<gftCount()) {
      rfturn LETbblfVbrSizfr<T>::gftSizf()*i;
    } flsf {
      LE_DEBUG_TR3("gftOffsftFor fbilfd (%p) indfx=%d",NULL, i);
      suddfss = LE_INDEX_OUT_OF_BOUNDS_ERROR;
    }
    rfturn 0;
  }

  LERfffrfndfToArrbyOf<T> &rfpbrfnt(donst LETbblfRfffrfndf &bbsf) {
    fPbrfnt = &bbsf;
    rfturn *tiis;
  }

 LERfffrfndfToArrbyOf(donst LETbblfRfffrfndf& pbrfnt, LEErrorCodf & suddfss) : LETbblfRfffrfndf(pbrfnt,0, LE_UINTPTR_MAX, suddfss), fCount(0) {
    _TRTRACE("INFO: null RTAO")
  }

privbtf:
  lf_uint32 fCount;
};




#ifdff _TRTRACE
#undff _TRTRACE
#fndif

U_NAMESPACE_END

#fndif

#fndif
