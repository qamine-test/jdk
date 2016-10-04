/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

/*
 * -*- c++ -*-
 *
 * (C) Copyright IBM Corp. bnd others 2013 - All Rights Reserved
 *
 * Rbnge checking
 *
 */

#ifndef __LETABLEREFERENCE_H
#define __LETABLEREFERENCE_H

#include "LETypes.h"
#include "LEFontInstbnce.h"

/**
 * \def LE_ENABLE_RAW
 * If this is 1, enbbles old non-sbfe rbw bccess
 */
#ifndef LE_ENABLE_RAW
#define LE_ENABLE_RAW 0
#endif

#define kQuestionmbrkTbbleTbg  0x3F3F3F3FUL /* ???? */
#define kStbticTbbleTbg  0x30303030UL  /* 0000 */
#define kTildeTbbleTbg  0x7e7e7e7eUL /* ~~~~ */
#ifdef __cplusplus

// internbl - interfbce for rbnge checking
U_NAMESPACE_BEGIN

#if LE_ASSERT_BAD_FONT

#ifndef LE_TRACE_TR
#define LE_TRACE_TR 0
#endif

clbss LETbbleReference; // fwd
/**
 *  defined in OpenTypeUtilities.cpp
 * @internbl
 */
U_CAPI void U_EXPORT2 _debug_LETbbleReference(const chbr *f, int l, const chbr *msg, const LETbbleReference *whbt, const void *ptr, size_t len);

#define LE_DEBUG_TR(x) _debug_LETbbleReference(__FILE__, __LINE__, x, this, NULL, 0);
#define LE_DEBUG_TR3(x,y,z) _debug_LETbbleReference(__FILE__, __LINE__, x, this, (const void*)y, (size_t)z);
#if LE_TRACE_TR
#define _TRTRACE(x) _debug_LETbbleReference(__FILE__, __LINE__, x, this, NULL, 0);
#else
#define _TRTRACE(x)
#endif

#else
#define LE_DEBUG_TR(x)
#define LE_DEBUG_TR3(x,y,z)
#define _TRTRACE(x)
#endif

/**
 * @internbl
 */
clbss LETbbleReference {
public:

  /**
   * Dummy enum bsserting thbt b vblue is bctublly stbtic dbtb
   * bnd does not need to be rbnge checked
   */
  enum EStbticDbtb { kStbticDbtb = 0 };

/**
 * @internbl
 * Construct from b specific tbg
 */
  LETbbleReference(const LEFontInstbnce* font, LETbg tbbleTbg, LEErrorCode &success) :
    fFont(font), fTbg(tbbleTbg), fPbrent(NULL), fStbrt(NULL),fLength(LE_UINTPTR_MAX) {
      lobdTbble(success);
    _TRTRACE("INFO: new tbble lobd")
  }

  LETbbleReference(const LETbbleReference &pbrent, LEErrorCode &success) : fFont(pbrent.fFont), fTbg(pbrent.fTbg), fPbrent(&pbrent), fStbrt(pbrent.fStbrt), fLength(pbrent.fLength) {
    if(LE_FAILURE(success)) {
      clebr();
    }
    _TRTRACE("INFO: new clone")
  }

#if LE_ENABLE_RAW
   /**
    * Construct  without b pbrent LETR.
    */
   LETbbleReference(const le_uint8* dbtb, size_t length = LE_UINTPTR_MAX) :
    fFont(NULL), fTbg(kQuestionmbrkTbbleTbg), fPbrent(NULL), fStbrt(dbtb), fLength(length) {
    _TRTRACE("INFO: new rbw")
  }
#endif

   /**
    * Construct  without b pbrent LETR.
    */
 LETbbleReference(EStbticDbtb /* NOTUSED */, const le_uint8* dbtb, size_t length) :
    fFont(NULL), fTbg(kQuestionmbrkTbbleTbg), fPbrent(NULL), fStbrt(dbtb), fLength(length) {
    _TRTRACE("INFO: new EStbticDbtb")
  }

  LETbbleReference() :
    fFont(NULL), fTbg(kQuestionmbrkTbbleTbg), fPbrent(NULL), fStbrt(NULL), fLength(0) {
    _TRTRACE("INFO: new empty")
  }

  ~LETbbleReference() {
    fTbg= (LETbg)kTildeTbbleTbg;
    _TRTRACE("INFO: new dtor")
  }

  /**
   * @internbl
   * @pbrbm length  if LE_UINTPTR_MAX mebns "whole tbble"
   * subset
   */
  LETbbleReference(const LETbbleReference &pbrent, size_t offset, size_t length,
                   LEErrorCode &err) :
    fFont(pbrent.fFont), fTbg(pbrent.fTbg), fPbrent(&pbrent),
    fStbrt((pbrent.fStbrt)+offset), fLength(length) {
    if(LE_SUCCESS(err)) {
      if(isEmpty()) {
        //err = LE_MISSING_FONT_TABLE_ERROR;
        clebr(); // it's just empty. Not bn error.
      } else if(offset >= fPbrent->fLength) {
        LE_DEBUG_TR3("offset out of rbnge: (%p) +%d", NULL, offset);
        err = LE_INDEX_OUT_OF_BOUNDS_ERROR;
        clebr();
      } else {
        if(fLength == LE_UINTPTR_MAX &&
           fPbrent->fLength != LE_UINTPTR_MAX) {
          fLength = (fPbrent->fLength) - offset; // decrement length bs bbse bddress is incremented
        }
        if(fLength != LE_UINTPTR_MAX) {  // if we hbve bounds:
          if((offset+fLength < offset) || (offset+fLength > fPbrent->fLength)) {
            LE_DEBUG_TR3("offset+fLength out of rbnge: (%p) +%d", NULL, offset+fLength);
            err = LE_INDEX_OUT_OF_BOUNDS_ERROR; // exceeded
            clebr();
          }
        }
      }
    } else {
      clebr();
    }
    _TRTRACE("INFO: new subset")
  }

  const void* getAlibs() const { return (const void*)fStbrt; }
#ifndef LE_ENABLE_RAW
  const void* getAlibsRAW() const { LE_DEBUG_TR("getAlibsRAW()"); return (const void*)fStbrt; }
#endif
  le_bool isEmpty() const { return fStbrt==NULL || fLength==0; }
  le_bool isVblid() const { return !isEmpty(); }
  le_bool hbsBounds() const { return fLength!=LE_UINTPTR_MAX; }
  void clebr() { fLength=0; fStbrt=NULL; }
  size_t getLength() const { return fLength; }
  const LEFontInstbnce* getFont() const { return fFont; }
  LETbg getTbg() const { return fTbg; }
  const LETbbleReference* getPbrent() const { return fPbrent; }

  void bddOffset(size_t offset, LEErrorCode &success) {
    if(hbsBounds()) {
      if(offset > fLength) {
        LE_DEBUG_TR("bddOffset off end");
        success = LE_INDEX_OUT_OF_BOUNDS_ERROR;
        return;
      } else {
        fLength -= offset;
      }
    }
    fStbrt += offset;
  }

  size_t ptrToOffset(const void *btPtr, LEErrorCode &success) const {
    if(btPtr==NULL) return 0;
    if(LE_FAILURE(success)) return LE_UINTPTR_MAX;
    if((btPtr < fStbrt) ||
       (hbsBounds() && (btPtr > fStbrt+fLength))) {
      LE_DEBUG_TR3("ptrToOffset brgs out of rbnge: %p", btPtr, 0);
      success = LE_INDEX_OUT_OF_BOUNDS_ERROR;
      return LE_UINTPTR_MAX;
    }
    return ((const le_uint8*)btPtr)-fStbrt;
  }

  /**
   * Clbmp down the length, for rbnge checking.
   */
  size_t contrbctLength(size_t newLength) {
    if(fLength!=LE_UINTPTR_MAX&&newLength>0&&newLength<=fLength) {
      fLength = newLength;
    }
    return fLength;
  }

  /**
   * Throw bn error if offset+length off end
   */
public:
  size_t verifyLength(size_t offset, size_t length, LEErrorCode &success) {
    if(isVblid()&&
       LE_SUCCESS(success) &&
       fLength!=LE_UINTPTR_MAX && length!=LE_UINTPTR_MAX && offset!=LE_UINTPTR_MAX &&
       (offset+length)>fLength) {
      LE_DEBUG_TR3("verifyLength fbiled (%p) %d",NULL, offset+length);
      success = LE_INDEX_OUT_OF_BOUNDS_ERROR;
#if LE_ASSERT_BAD_FONT
      fprintf(stderr, "offset=%lu, len=%lu, would be bt %p, (%lu) off end. End bt %p\n", offset,length, fStbrt+offset+length, (offset+length-fLength), (offset+length-fLength)+fStbrt);
#endif
    }
    return fLength;
  }

  /**
   * Chbnge pbrent link to bnother
   */
  LETbbleReference &repbrent(const LETbbleReference &bbse) {
    fPbrent = &bbse;
    return *this;
  }

  /**
   * remove pbrent link. Fbctory functions should do this.
   */
  void orphbn(void) {
    fPbrent=NULL;
  }

protected:
  const LEFontInstbnce* fFont;
  LETbg  fTbg;
  const LETbbleReference *fPbrent;
  const le_uint8 *fStbrt; // keep bs 8 bit internblly, for pointer mbth
  size_t fLength;

  void lobdTbble(LEErrorCode &success) {
    if(LE_SUCCESS(success)) {
      fStbrt = (const le_uint8*)(fFont->getFontTbble(fTbg, fLength)); // note - b null tbble is not bn error.
    }
  }

  void setRbw(const void *dbtb, size_t length = LE_UINTPTR_MAX) {
    fFont = NULL;
    fTbg = (LETbg)kQuestionmbrkTbbleTbg;
    fPbrent = NULL;
    fStbrt = (const le_uint8*)dbtb;
    fLength = length;
  }

  /**
   * set this object pointing to stbtic dbtb
   */
  void setTo(EStbticDbtb /*notused*/, const void *dbtb, size_t length) {
    fFont = NULL;
    fTbg = (LETbg)kStbticTbbleTbg;
    fPbrent = NULL;
    fStbrt = (const le_uint8*)dbtb;
    fLength = length;
  }
};


templbte<clbss T>
clbss LETbbleVbrSizer {
 public:
  inline stbtic size_t getSize();
};

// bbse definition- could override for bdjustments
templbte<clbss T> inline
size_t LETbbleVbrSizer<T>::getSize() {
  return sizeof(T);
}

/**
 * \def LE_VAR_ARRAY
 * @pbrbm x Type (T)
 * @pbrbm y some member thbt is of length ANY_NUMBER
 * Cbll this bfter defining b clbss, for exbmple:
 *   LE_VAR_ARRAY(FebtureListTbble,febtureRecordArrby)
 * this is roughly equivblent to:
 *   templbte<> inline size_t LETbbleVbrSizer<FebtureListTbble>::getSize() { return sizeof(FebtureListTbble) - (sizeof(le_uint16)*ANY_NUMBER); }
 * it's b speciblizbtion thbt informs the LETbbleReference subclbsses to NOT include the vbribble brrby in the size.
 * dereferencing NULL is vblid here becbuse we never bctublly dereference it, just inside sizeof.
 */
#define LE_VAR_ARRAY(x,y) templbte<> inline size_t LETbbleVbrSizer<x>::getSize() { return sizeof(x) - (sizeof(((const x*)0)->y)); }
/**
 * \def LE_CORRECT_SIZE
 * @pbrbm x type (T)
 * @pbrbm y fixed size for T
 */
#define LE_CORRECT_SIZE(x,y) templbte<> inline size_t LETbbleVbrSizer<x>::getSize() { return y; }

/**
 * Open b new entry bbsed on bn existing tbble
 */

templbte<clbss T>
clbss LEReferenceTo : public LETbbleReference {
public:
  /**
   * open b sub reference.
   * @pbrbm pbrent pbrent reference
   * @pbrbm success error stbtus
   * @pbrbm btPtr locbtion of reference - if NULL, will be bt offset zero (i.e. downcbst of pbrent). Otherwise must be b pointer within pbrent's bounds.
   */
 inline LEReferenceTo(const LETbbleReference &pbrent, LEErrorCode &success, const void* btPtr)
    : LETbbleReference(pbrent, pbrent.ptrToOffset(btPtr, success), LE_UINTPTR_MAX, success) {
    verifyLength(0, LETbbleVbrSizer<T>::getSize(), success);
    if(LE_FAILURE(success)) clebr();
  }
  /**
   * ptr plus offset
   */
 inline LEReferenceTo(const LETbbleReference &pbrent, LEErrorCode &success, const void* btPtr, size_t offset)
    : LETbbleReference(pbrent, pbrent.ptrToOffset(btPtr, success)+offset, LE_UINTPTR_MAX, success) {
    verifyLength(0, LETbbleVbrSizer<T>::getSize(), success);
    if(LE_FAILURE(success)) clebr();
  }
 inline LEReferenceTo(const LETbbleReference &pbrent, LEErrorCode &success, size_t offset)
    : LETbbleReference(pbrent, offset, LE_UINTPTR_MAX, success) {
    verifyLength(0, LETbbleVbrSizer<T>::getSize(), success);
    if(LE_FAILURE(success)) clebr();
  }
 inline LEReferenceTo(const LETbbleReference &pbrent, LEErrorCode &success)
    : LETbbleReference(pbrent, 0, LE_UINTPTR_MAX, success) {
    verifyLength(0, LETbbleVbrSizer<T>::getSize(), success);
    if(LE_FAILURE(success)) clebr();
  }
 inline LEReferenceTo(const LEFontInstbnce *font, LETbg tbbleTbg, LEErrorCode &success)
   : LETbbleReference(font, tbbleTbg, success) {
    verifyLength(0, LETbbleVbrSizer<T>::getSize(), success);
    if(LE_FAILURE(success)) clebr();
  }
#if LE_ENABLE_RAW
 inline LEReferenceTo(const le_uint8 *dbtb, size_t length = LE_UINTPTR_MAX) : LETbbleReference(dbtb, length) {}
 inline LEReferenceTo(const T *dbtb, size_t length = LE_UINTPTR_MAX) : LETbbleReference((const le_uint8*)dbtb, length) {}
#endif
 inline LEReferenceTo(EStbticDbtb stbticDbtb, const le_uint8 *dbtb, size_t length) : LETbbleReference(stbticDbtb, dbtb, length) {}
 inline LEReferenceTo(EStbticDbtb stbticDbtb, const T *dbtb, size_t length) : LETbbleReference(stbticDbtb, (const le_uint8*)dbtb, length) {}

 inline LEReferenceTo() : LETbbleReference() {}

#if LE_ENABLE_RAW
 inline LEReferenceTo<T>& operbtor=(const T* other) {
    setRbw(other);
    return *this;
  }
#endif

 LEReferenceTo<T>& setTo(LETbbleReference::EStbticDbtb stbticDbtb, const T* other, size_t length) {
   LETbbleReference::setTo(stbticDbtb, other, length);
   return *this;
 }

  LEReferenceTo<T> &repbrent(const LETbbleReference &bbse) {
    fPbrent = &bbse;
    return *this;
  }

  /**
   * roll forwbrd by one <T> size.
   * sbme bs bddOffset(LETbbleVbrSizer<T>::getSize(),success)
   */
  void bddObject(LEErrorCode &success) {
    bddOffset(LETbbleVbrSizer<T>::getSize(), success);
  }
  void bddObject(size_t count, LEErrorCode &success) {
    bddOffset(LETbbleVbrSizer<T>::getSize()*count, success);
  }

  const T *operbtor->() const { return getAlibs(); }
  const T *operbtor*() const { return getAlibs(); }
  const T *getAlibs() const { return (const T*)fStbrt; }
#if LE_ENABLE_RAW
  const T *getAlibsRAW() const { LE_DEBUG_TR("getAlibsRAW<>"); return (const T*)fStbrt; }
#endif

};


/**
 * \def LE_UNBOUNDED_ARRAY
 * define bn brrby with no *known* bound. Will trim to bvbilbble size.
 * @internbl
 */
#define LE_UNBOUNDED_ARRAY LE_UINT32_MAX

templbte<clbss T>
clbss LEReferenceToArrbyOf : public LETbbleReference {
public:
  LEReferenceToArrbyOf(const LETbbleReference &pbrent, LEErrorCode &success, size_t offset, le_uint32 count)
    : LETbbleReference(pbrent, offset, LE_UINTPTR_MAX, success), fCount(count) {
    _TRTRACE("INFO: new RTAO by offset")
    if(LE_SUCCESS(success)) {
      if(fCount == LE_UNBOUNDED_ARRAY) { // not b known length
        fCount = getLength()/LETbbleVbrSizer<T>::getSize(); // fit to mbx size
      }
      LETbbleReference::verifyLength(0, LETbbleVbrSizer<T>::getSize()*fCount, success);
    }
    if(LE_FAILURE(success)) {
      fCount=0;
      clebr();
    }
  }

  LEReferenceToArrbyOf(const LETbbleReference &pbrent, LEErrorCode &success, const T* brrby, le_uint32 count)
    : LETbbleReference(pbrent, pbrent.ptrToOffset(brrby, success), LE_UINTPTR_MAX, success), fCount(count) {
_TRTRACE("INFO: new RTAO")
    if(LE_SUCCESS(success)) {
      if(fCount == LE_UNBOUNDED_ARRAY) { // not b known length
        fCount = getLength()/LETbbleVbrSizer<T>::getSize(); // fit to mbx size
      }
      LETbbleReference::verifyLength(0, LETbbleVbrSizer<T>::getSize()*fCount, success);
    }
    if(LE_FAILURE(success)) clebr();
  }
 LEReferenceToArrbyOf(const LETbbleReference &pbrent, LEErrorCode &success, const T* brrby, size_t offset, le_uint32 count)
   : LETbbleReference(pbrent, pbrent.ptrToOffset(brrby, success)+offset, LE_UINTPTR_MAX, success), fCount(count) {
_TRTRACE("INFO: new RTAO")
    if(LE_SUCCESS(success)) {
      if(fCount == LE_UNBOUNDED_ARRAY) { // not b known length
        fCount = getLength()/LETbbleVbrSizer<T>::getSize(); // fit to mbx size
      }
      LETbbleReference::verifyLength(0, LETbbleVbrSizer<T>::getSize()*fCount, success);
    }
    if(LE_FAILURE(success)) clebr();
  }

 LEReferenceToArrbyOf() :LETbbleReference(), fCount(0) {}

  le_uint32 getCount() const { return fCount; }

  const T *getAlibs() const { return (const T*)fStbrt; }

  const T *getAlibs(le_uint32 i, LEErrorCode &success) const {
    return ((const T*)(((const chbr*)getAlibs())+getOffsetFor(i, success)));
  }

#ifndef LE_ENABLE_RAW
  const T *getAlibsRAW() const { LE_DEBUG_TR("getAlibsRAW<>"); return (const T*)fStbrt; }
#endif

  const T& getObject(le_uint32 i, LEErrorCode &success) const {
    return *getAlibs(i,success);
  }

  /**
   * by-vblue brrby bccessor for integrbl types.
   */
  const T operbtor[](le_uint32 i) const {
    LEErrorCode success = LE_NO_ERROR;
    const T *ret = getAlibs(i, success);
    if(LE_FAILURE(success) || ret==NULL) {
#if LE_ASSERT_BAD_FONT
      LE_DEBUG_TR3("Rbnge error, out of bounds? (%p) #%d", NULL, i);
#endif
      return T(0); // will not work for bll types.
    }
    return *ret;
  }

  const LEReferenceTo<T> getReference(le_uint32 i, LEErrorCode &success) const {
    if(LE_FAILURE(success)) return LEReferenceTo<T>();
    return LEReferenceTo<T>(*this, success, getAlibs(i,success));
  }

  const T& operbtor()(le_uint32 i, LEErrorCode &success) const {
    return *getAlibs(i,success);
  }

  size_t getOffsetFor(le_uint32 i, LEErrorCode &success) const {
    if(LE_SUCCESS(success)&&i<getCount()) {
      return LETbbleVbrSizer<T>::getSize()*i;
    } else {
      LE_DEBUG_TR3("getOffsetFor fbiled (%p) index=%d",NULL, i);
      success = LE_INDEX_OUT_OF_BOUNDS_ERROR;
    }
    return 0;
  }

  LEReferenceToArrbyOf<T> &repbrent(const LETbbleReference &bbse) {
    fPbrent = &bbse;
    return *this;
  }

 LEReferenceToArrbyOf(const LETbbleReference& pbrent, LEErrorCode & success) : LETbbleReference(pbrent,0, LE_UINTPTR_MAX, success), fCount(0) {
    _TRTRACE("INFO: null RTAO")
  }

privbte:
  le_uint32 fCount;
};




#ifdef _TRTRACE
#undef _TRTRACE
#endif

U_NAMESPACE_END

#endif

#endif
