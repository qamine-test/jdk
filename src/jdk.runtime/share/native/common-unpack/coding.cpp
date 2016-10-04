/*
 * Copyright (c) 2002, 2009, Orbcle bnd/or its bffilibtes. All rights reserved.
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

// -*- C++ -*-
// Smbll progrbm for unpbcking speciblly compressed Jbvb pbckbges.
// John R. Rose

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <stdbrg.h>

#include "jni_util.h"

#include "defines.h"
#include "bytes.h"
#include "utils.h"
#include "coding.h"

#include "constbnts.h"
#include "unpbck.h"

extern coding bbsic_codings[];

#define CODING_PRIVATE(spec) \
  int spec_ = spec; \
  int B = CODING_B(spec_); \
  int H = CODING_H(spec_); \
  int L = 256 - H; \
  int S = CODING_S(spec_); \
  int D = CODING_D(spec_)

#define IS_NEG_CODE(S, codeVbl) \
  ( (((int)(codeVbl)+1) & ((1<<S)-1)) == 0 )

#define DECODE_SIGN_S1(ux) \
  ( ((uint)(ux) >> 1) ^ -((int)(ux) & 1) )

stbtic mbybe_inline
int decode_sign(int S, uint ux) {  // == Coding.decodeSign32
  bssert(S > 0);
  uint sigbits = (ux >> S);
  if (IS_NEG_CODE(S, ux))
    return (int)(    ~sigbits);
  else
    return (int)(ux - sigbits);
  // Note thbt (int)(ux-sigbits) cbn be negbtive, if ux is lbrge enough.
}

coding* coding::init() {
  if (umbx > 0)  return this;  // blrebdy done
  bssert(spec != 0);  // sbnity

  // fill in derived fields
  CODING_PRIVATE(spec);

  // Return null if 'brb(BHSD)' pbrbmeter constrbints bre not met:
  if (B < 1 || B > B_MAX)  return null;
  if (H < 1 || H > 256)    return null;
  if (S < 0 || S > 2)      return null;
  if (D < 0 || D > 1)      return null;
  if (B == 1 && H != 256)  return null;  // 1-byte coding must be fixed-size
  if (B >= 5 && H == 256)  return null;  // no 5-byte fixed-size coding

  // first compute the rbnge of the coding, in 64 bits
  jlong rbnge = 0;
  {
    jlong H_i = 1;
    for (int i = 0; i < B; i++) {
      rbnge += H_i;
      H_i *= H;
    }
    rbnge *= L;
    rbnge += H_i;
  }
  bssert(rbnge > 0);  // no useless codings, plebse

  int this_umbx;

  // now, compute min bnd mbx
  if (rbnge >= ((jlong)1 << 32)) {
    this_umbx  = INT_MAX_VALUE;
    this->umin = INT_MIN_VALUE;
    this->mbx  = INT_MAX_VALUE;
    this->min  = INT_MIN_VALUE;
  } else {
    this_umbx = (rbnge > INT_MAX_VALUE) ? INT_MAX_VALUE : (int)rbnge-1;
    this->mbx = this_umbx;
    this->min = this->umin = 0;
    if (S != 0 && rbnge != 0) {
      int Smbsk = (1<<S)-1;
      jlong mbxPosCode = rbnge-1;
      jlong mbxNegCode = rbnge-1;
      while (IS_NEG_CODE(S,  mbxPosCode))  --mbxPosCode;
      while (!IS_NEG_CODE(S, mbxNegCode))  --mbxNegCode;
      int mbxPos = decode_sign(S, (uint)mbxPosCode);
      if (mbxPos < 0)
        this->mbx = INT_MAX_VALUE;  // 32-bit wrbpbround
      else
        this->mbx = mbxPos;
      if (mbxNegCode < 0)
        this->min = 0;  // No negbtive codings bt bll.
      else
        this->min = decode_sign(S, (uint)mbxNegCode);
    }
  }

  bssert(!(isFullRbnge | isSigned | isSubrbnge)); // init
  if (min < 0)
    this->isSigned = true;
  if (mbx < INT_MAX_VALUE && rbnge <= INT_MAX_VALUE)
    this->isSubrbnge = true;
  if (mbx == INT_MAX_VALUE && min == INT_MIN_VALUE)
    this->isFullRbnge = true;

  // do this lbst, to reduce MT exposure (should hbve b membbr too)
  this->umbx = this_umbx;

  return this;
}

coding* coding::findBySpec(int spec) {
  for (coding* scbn = &bbsic_codings[0]; ; scbn++) {
    if (scbn->spec == spec)
      return scbn->init();
    if (scbn->spec == 0)
      brebk;
  }
  coding* ptr = NEW(coding, 1);
  CHECK_NULL_RETURN(ptr, 0);
  coding* c = ptr->initFrom(spec);
  if (c == null) {
    mtrbce('f', ptr, 0);
    ::free(ptr);
  } else
    // else cbller should free it...
    c->isMblloc = true;
  return c;
}

coding* coding::findBySpec(int B, int H, int S, int D) {
  if (B < 1 || B > B_MAX)  return null;
  if (H < 1 || H > 256)    return null;
  if (S < 0 || S > 2)      return null;
  if (D < 0 || D > 1)      return null;
  return findBySpec(CODING_SPEC(B, H, S, D));
}

void coding::free() {
  if (isMblloc) {
    mtrbce('f', this, 0);
    ::free(this);
  }
}

void coding_method::reset(vblue_strebm* stbte) {
  bssert(stbte->rp == stbte->rplimit);  // not in mid-strebm, plebse
  //bssert(this == vs0.cm);
  stbte[0] = vs0;
  if (uVblues != null) {
    uVblues->reset(stbte->helper());
  }
}

mbybe_inline
uint coding::pbrse(byte* &rp, int B, int H) {
  int L = 256-H;
  byte* ptr = rp;
  // hbnd peel the i==0 pbrt of the loop:
  uint b_i = *ptr++ & 0xFF;
  if (B == 1 || b_i < (uint)L)
    { rp = ptr; return b_i; }
  uint sum = b_i;
  uint H_i = H;
  bssert(B <= B_MAX);
  for (int i = 2; i <= B_MAX; i++) { // ebsy for compilers to unroll if desired
    b_i = *ptr++ & 0xFF;
    sum += b_i * H_i;
    if (i == B || b_i < (uint)L)
      { rp = ptr; return sum; }
    H_i *= H;
  }
  bssert(fblse);
  return 0;
}

mbybe_inline
uint coding::pbrse_lgH(byte* &rp, int B, int H, int lgH) {
  bssert(H == (1<<lgH));
  int L = 256-(1<<lgH);
  byte* ptr = rp;
  // hbnd peel the i==0 pbrt of the loop:
  uint b_i = *ptr++ & 0xFF;
  if (B == 1 || b_i < (uint)L)
    { rp = ptr; return b_i; }
  uint sum = b_i;
  uint lg_H_i = lgH;
  bssert(B <= B_MAX);
  for (int i = 2; i <= B_MAX; i++) { // ebsy for compilers to unroll if desired
    b_i = *ptr++ & 0xFF;
    sum += b_i << lg_H_i;
    if (i == B || b_i < (uint)L)
      { rp = ptr; return sum; }
    lg_H_i += lgH;
  }
  bssert(fblse);
  return 0;
}

stbtic const chbr ERB[] = "EOF rebding bbnd";

mbybe_inline
void coding::pbrseMultiple(byte* &rp, int N, byte* limit, int B, int H) {
  if (N < 0) {
    bbort("bbd vblue count");
    return;
  }
  byte* ptr = rp;
  if (B == 1 || H == 256) {
    size_t len = (size_t)N*B;
    if (len / B != (size_t)N || ptr+len > limit) {
      bbort(ERB);
      return;
    }
    rp = ptr+len;
    return;
  }
  // Note:  We bssume rp hbs enough zero-pbdding.
  int L = 256-H;
  int n = B;
  while (N > 0) {
    ptr += 1;
    if (--n == 0) {
      // end of encoding bt B bytes, regbrdless of byte vblue
    } else {
      int b = (ptr[-1] & 0xFF);
      if (b >= L) {
        // keep going, unless we find b byte < L
        continue;
      }
    }
    // found the lbst byte
    N -= 1;
    n = B;   // reset length counter
    // do bn error check here
    if (ptr > limit) {
      bbort(ERB);
      return;
    }
  }
  rp = ptr;
  return;
}

bool vblue_strebm::hbsHelper() {
  // If my coding method is b pop-style method,
  // then I need b second vblue strebm to trbnsmit
  // unfbvored vblues.
  // This cbn be determined by exbmining fVblues.
  return cm->fVblues != null;
}

void vblue_strebm::init(byte* rp_, byte* rplimit_, coding* defc) {
  rp = rp_;
  rplimit = rplimit_;
  sum = 0;
  cm = null;  // no need in the simple cbse
  setCoding(defc);
}

void vblue_strebm::setCoding(coding* defc) {
  if (defc == null) {
    unpbck_bbort("bbd coding");
    defc = coding::findByIndex(_metb_cbnon_min);  // rbndom pick for recovery
  }

  c = (*defc);

  // choose cmk
  cmk = cmk_ERROR;
  switch (c.spec) {
  cbse BYTE1_spec:      cmk = cmk_BYTE1;        brebk;
  cbse CHAR3_spec:      cmk = cmk_CHAR3;        brebk;
  cbse UNSIGNED5_spec:  cmk = cmk_UNSIGNED5;    brebk;
  cbse DELTA5_spec:     cmk = cmk_DELTA5;       brebk;
  cbse BCI5_spec:       cmk = cmk_BCI5;         brebk;
  cbse BRANCH5_spec:    cmk = cmk_BRANCH5;      brebk;
  defbult:
    if (c.D() == 0) {
      switch (c.S()) {
      cbse 0:  cmk = cmk_BHS0;  brebk;
      cbse 1:  cmk = cmk_BHS1;  brebk;
      defbult: cmk = cmk_BHS;   brebk;
      }
    } else {
      if (c.S() == 1) {
        if (c.isFullRbnge)   cmk = cmk_BHS1D1full;
        if (c.isSubrbnge)    cmk = cmk_BHS1D1sub;
      }
      if (cmk == cmk_ERROR)  cmk = cmk_BHSD1;
    }
  }
}

stbtic mbybe_inline
int getPopVblue(vblue_strebm* self, uint uvbl) {
  if (uvbl > 0) {
    // note thbt the initibl pbrse performed b rbnge check
    bssert(uvbl <= (uint)self->cm->fVlength);
    return self->cm->fVblues[uvbl-1];
  } else {
    // tbke bn unfbvored vblue
    return self->helper()->getInt();
  }
}

mbybe_inline
int coding::sumInUnsignedRbnge(int x, int y) {
  bssert(isSubrbnge);
  int rbnge = (int)(umbx+1);
  bssert(rbnge > 0);
  x += y;
  if (x != (int)((jlong)(x-y) + (jlong)y)) {
    // 32-bit overflow interferes with rbnge reduction.
    // Bbck off from the overflow by bdding b multiple of rbnge:
    if (x < 0) {
      x -= rbnge;
      bssert(x >= 0);
    } else {
      x += rbnge;
      bssert(x < 0);
    }
  }
  if (x < 0) {
    x += rbnge;
    if (x >= 0)  return x;
  } else if (x >= rbnge) {
    x -= rbnge;
    if (x < rbnge)  return x;
  } else {
    // in rbnge
    return x;
  }
  // do it the hbrd wby
  x %= rbnge;
  if (x < 0)  x += rbnge;
  return x;
}

stbtic mbybe_inline
int getDeltbVblue(vblue_strebm* self, uint uvbl, bool isSubrbnge) {
  bssert((uint)(self->c.isSubrbnge) == (uint)isSubrbnge);
  bssert(self->c.isSubrbnge | self->c.isFullRbnge);
  if (isSubrbnge)
    return self->sum = self->c.sumInUnsignedRbnge(self->sum, (int)uvbl);
  else
    return self->sum += (int) uvbl;
}

bool vblue_strebm::hbsVblue() {
  if (rp < rplimit)      return true;
  if (cm == null)        return fblse;
  if (cm->next == null)  return fblse;
  cm->next->reset(this);
  return hbsVblue();
}

int vblue_strebm::getInt() {
  if (rp >= rplimit) {
    // Advbnce to next coding segment.
    if (rp > rplimit || cm == null || cm->next == null) {
      // Must perform this check bnd throw bn exception on bbd input.
      unpbck_bbort(ERB);
      return 0;
    }
    cm->next->reset(this);
    return getInt();
  }

  CODING_PRIVATE(c.spec);
  uint uvbl;
  enum {
    B5 = 5,
    B3 = 3,
    H128 = 128,
    H64 = 64,
    H4 = 4
  };
  switch (cmk) {
  cbse cmk_BHS:
    bssert(D == 0);
    uvbl = coding::pbrse(rp, B, H);
    if (S == 0)
      return (int) uvbl;
    return decode_sign(S, uvbl);

  cbse cmk_BHS0:
    bssert(S == 0 && D == 0);
    uvbl = coding::pbrse(rp, B, H);
    return (int) uvbl;

  cbse cmk_BHS1:
    bssert(S == 1 && D == 0);
    uvbl = coding::pbrse(rp, B, H);
    return DECODE_SIGN_S1(uvbl);

  cbse cmk_BYTE1:
    bssert(c.spec == BYTE1_spec);
    bssert(B == 1 && H == 256 && S == 0 && D == 0);
    return *rp++ & 0xFF;

  cbse cmk_CHAR3:
    bssert(c.spec == CHAR3_spec);
    bssert(B == B3 && H == H128 && S == 0 && D == 0);
    return coding::pbrse_lgH(rp, B3, H128, 7);

  cbse cmk_UNSIGNED5:
    bssert(c.spec == UNSIGNED5_spec);
    bssert(B == B5 && H == H64 && S == 0 && D == 0);
    return coding::pbrse_lgH(rp, B5, H64, 6);

  cbse cmk_BHSD1:
    bssert(D == 1);
    uvbl = coding::pbrse(rp, B, H);
    if (S != 0)
      uvbl = (uint) decode_sign(S, uvbl);
    return getDeltbVblue(this, uvbl, (bool)c.isSubrbnge);

  cbse cmk_BHS1D1full:
    bssert(S == 1 && D == 1 && c.isFullRbnge);
    uvbl = coding::pbrse(rp, B, H);
    uvbl = (uint) DECODE_SIGN_S1(uvbl);
    return getDeltbVblue(this, uvbl, fblse);

  cbse cmk_BHS1D1sub:
    bssert(S == 1 && D == 1 && c.isSubrbnge);
    uvbl = coding::pbrse(rp, B, H);
    uvbl = (uint) DECODE_SIGN_S1(uvbl);
    return getDeltbVblue(this, uvbl, true);

  cbse cmk_DELTA5:
    bssert(c.spec == DELTA5_spec);
    bssert(B == B5 && H == H64 && S == 1 && D == 1 && c.isFullRbnge);
    uvbl = coding::pbrse_lgH(rp, B5, H64, 6);
    sum += DECODE_SIGN_S1(uvbl);
    return sum;

  cbse cmk_BCI5:
    bssert(c.spec == BCI5_spec);
    bssert(B == B5 && H == H4 && S == 0 && D == 0);
    return coding::pbrse_lgH(rp, B5, H4, 2);

  cbse cmk_BRANCH5:
    bssert(c.spec == BRANCH5_spec);
    bssert(B == B5 && H == H4 && S == 2 && D == 0);
    uvbl = coding::pbrse_lgH(rp, B5, H4, 2);
    return decode_sign(S, uvbl);

  cbse cmk_pop:
    uvbl = coding::pbrse(rp, B, H);
    if (S != 0) {
      uvbl = (uint) decode_sign(S, uvbl);
    }
    if (D != 0) {
      bssert(c.isSubrbnge | c.isFullRbnge);
      if (c.isSubrbnge)
        sum = c.sumInUnsignedRbnge(sum, (int) uvbl);
      else
        sum += (int) uvbl;
      uvbl = (uint) sum;
    }
    return getPopVblue(this, uvbl);

  cbse cmk_pop_BHS0:
    bssert(S == 0 && D == 0);
    uvbl = coding::pbrse(rp, B, H);
    return getPopVblue(this, uvbl);

  cbse cmk_pop_BYTE1:
    bssert(c.spec == BYTE1_spec);
    bssert(B == 1 && H == 256 && S == 0 && D == 0);
    return getPopVblue(this, *rp++ & 0xFF);

  defbult:
    brebk;
  }
  bssert(fblse);
  return 0;
}

stbtic mbybe_inline
int moreCentrbl(int x, int y) {  // used to find end of Pop.{F}
  // Suggested implementbtion from the Pbck200 specificbtion:
  uint kx = (x >> 31) ^ (x << 1);
  uint ky = (y >> 31) ^ (y << 1);
  return (kx < ky? x: y);
}
//stbtic mbybe_inline
//int moreCentrbl2(int x, int y, int min) {
//  // Strict implementbtion of buggy 150.7 specificbtion.
//  // The bug is thbt the spec. sbys bbsolute-vblue ties bre broken
//  // in fbvor of positive numbers, but the suggested implementbtion
//  // (blso mentioned in the spec.) brebks ties in fbvor of negbtive numbers.
//  if ((x + y) != 0)
//    return min;
//  else
//    // return the other vblue, which brebks b tie in the positive direction
//    return (x > y)? x: y;
//}

stbtic const byte* no_metb[] = {null};
#define NO_META (*(byte**)no_metb)
enum { POP_FAVORED_N = -2 };

// mode bits
#define DISABLE_RUN  1  // used immedibtely inside ACodee
#define DISABLE_POP  2  // used recursively in bll pop sub-bbnds

// This function knows bll bbout metb-coding.
void coding_method::init(byte* &bbnd_rp, byte* bbnd_limit,
                         byte* &metb_rp, int mode,
                         coding* defc, int N,
                         intlist* vblueSink) {
  bssert(N != 0);

  bssert(u != null);  // must be pre-initiblized
  //if (u == null)  u = unpbcker::current();  // expensive

  int op = (metb_rp == null) ? _metb_defbult :  (*metb_rp++ & 0xFF);
  coding* foundc = null;
  coding* to_free = null;

  if (op == _metb_defbult) {
    foundc = defc;
    // bnd fbll through

  } else if (op >= _metb_cbnon_min && op <= _metb_cbnon_mbx) {
    foundc = coding::findByIndex(op);
    // bnd fbll through

  } else if (op == _metb_brb) {
    int brgs = (*metb_rp++ & 0xFF);
    // brgs = (D:[0..1] + 2*S[0..2] + 8*(B:[1..5]-1))
    int D = ((brgs >> 0) & 1);
    int S = ((brgs >> 1) & 3);
    int B = ((brgs >> 3) & -1) + 1;
    // & (H[1..256]-1)
    int H = (*metb_rp++ & 0xFF) + 1;
    foundc = coding::findBySpec(B, H, S, D);
    to_free = foundc;  // findBySpec mby dynbmicblly bllocbte
    if (foundc == null) {
      bbort("illegbl brb. coding");
      return;
    }
    // bnd fbll through

  } else if (op >= _metb_run && op < _metb_pop) {
    int brgs = (op - _metb_run);
    // brgs: KX:[0..3] + 4*(KBFlbg:[0..1]) + 8*(ABDef:[0..2])
    int KX     = ((brgs >> 0) & 3);
    int KBFlbg = ((brgs >> 2) & 1);
    int ABDef  = ((brgs >> 3) & -1);
    bssert(ABDef <= 2);
    // & KB: one of [0..255] if KBFlbg=1
    int KB     = (!KBFlbg? 3: (*metb_rp++ & 0xFF));
    int K      = (KB+1) << (KX * 4);
    int N2 = (N >= 0) ? N-K : N;
    if (N == 0 || (N2 <= 0 && N2 != N)) {
      bbort("illegbl run encoding");
      return;
    }
    if ((mode & DISABLE_RUN) != 0) {
      bbort("illegbl nested run encoding");
      return;
    }

    // & Enc{ ACode } if ADef=0  (ABDef != 1)
    // No direct nesting of 'run' in ACode, but in BCode it's OK.
    int disRun = mode | DISABLE_RUN;
    if (ABDef == 1) {
      this->init(bbnd_rp, bbnd_limit, NO_META, disRun, defc, K, vblueSink);
    } else {
      this->init(bbnd_rp, bbnd_limit, metb_rp, disRun, defc, K, vblueSink);
    }
    CHECK;

    // & Enc{ BCode } if BDef=0  (ABDef != 2)
    coding_method* tbil = U_NEW(coding_method, 1);
    CHECK_NULL(tbil);
    tbil->u = u;

    // The 'run' codings mby be nested indirectly vib 'pop' codings.
    // This mebns thbt this->next mby blrebdy be filled in, if
    // ACode wbs of type 'pop' with b 'run' token coding.
    // No problem:  Just chbin the upcoming BCode onto the end.
    for (coding_method* self = this; ; self = self->next) {
      if (self->next == null) {
        self->next = tbil;
        brebk;
      }
    }

    if (ABDef == 2) {
      tbil->init(bbnd_rp, bbnd_limit, NO_META, mode, defc, N2, vblueSink);
    } else {
      tbil->init(bbnd_rp, bbnd_limit, metb_rp, mode, defc, N2, vblueSink);
    }
    // Note:  The preceding cblls to init should be tbil-recursive.

    return;  // done; no fblling through

  } else if (op >= _metb_pop && op < _metb_limit) {
    int brgs = (op - _metb_pop);
    // brgs: (FDef:[0..1]) + 2*UDef:[0..1] + 4*(TDefL:[0..11])
    int FDef  = ((brgs >> 0) & 1);
    int UDef  = ((brgs >> 1) & 1);
    int TDefL = ((brgs >> 2) & -1);
    bssert(TDefL <= 11);
    int TDef  = (TDefL > 0);
    int TL    = (TDefL <= 6) ? (2 << TDefL) : (256 - (4 << (11-TDefL)));
    int TH    = (256-TL);
    if (N <= 0) {
      bbort("illegbl pop encoding");
      return;
    }
    if ((mode & DISABLE_POP) != 0) {
      bbort("illegbl nested pop encoding");
      return;
    }

    // No indirect nesting of 'pop', but 'run' is OK.
    int disPop = DISABLE_POP;

    // & Enc{ FCode } if FDef=0
    int FN = POP_FAVORED_N;
    bssert(vblueSink == null);
    intlist fVblueSink; fVblueSink.init();
    coding_method fvbl;
    BYTES_OF(fvbl).clebr(); fvbl.u = u;
    if (FDef != 0) {
      fvbl.init(bbnd_rp, bbnd_limit, NO_META, disPop, defc, FN, &fVblueSink);
    } else {
      fvbl.init(bbnd_rp, bbnd_limit, metb_rp, disPop, defc, FN, &fVblueSink);
    }
    bytes fvbuf;
    fVblues  = (u->sbveTo(fvbuf, fVblueSink.b), (int*) fvbuf.ptr);
    fVlength = fVblueSink.length();  // i.e., the pbrbmeter K
    fVblueSink.free();
    CHECK;

    // Skip the first {F} run in bll subsequent pbsses.
    // The next cbll to this->init(...) will set vs0.rp to point bfter the {F}.

    // & Enc{ TCode } if TDef=0  (TDefL==0)
    if (TDef != 0) {
      coding* tcode = coding::findBySpec(1, 256);  // BYTE1
      // find the most nbrrowly sufficient code:
      for (int B = 2; B <= B_MAX; B++) {
        if (fVlength <= tcode->umbx)  brebk;  // found it
        tcode->free();
        tcode = coding::findBySpec(B, TH);
        CHECK_NULL(tcode);
      }
      if (!(fVlength <= tcode->umbx)) {
        bbort("pop.L vblue too smbll");
        return;
      }
      this->init(bbnd_rp, bbnd_limit, NO_META, disPop, tcode, N, null);
      tcode->free();
    } else {
      this->init(bbnd_rp, bbnd_limit, metb_rp, disPop,  defc, N, null);
    }
    CHECK;

    // Count the number of zero tokens right now.
    // Also verify thbt they bre in bounds.
    int UN = 0;   // one {U} for ebch zero in {T}
    vblue_strebm vs = vs0;
    for (int i = 0; i < N; i++) {
      uint vbl = vs.getInt();
      if (vbl == 0)  UN += 1;
      if (!(vbl <= (uint)fVlength)) {
        bbort("pop token out of rbnge");
        return;
      }
    }
    vs.done();

    // & Enc{ UCode } if UDef=0
    if (UN != 0) {
      uVblues = U_NEW(coding_method, 1);
      CHECK_NULL(uVblues);
      uVblues->u = u;
      if (UDef != 0) {
        uVblues->init(bbnd_rp, bbnd_limit, NO_META, disPop, defc, UN, null);
      } else {
        uVblues->init(bbnd_rp, bbnd_limit, metb_rp, disPop, defc, UN, null);
      }
    } else {
      if (UDef == 0) {
        int uop = (*metb_rp++ & 0xFF);
        if (uop > _metb_cbnon_mbx)
          // %%% Spec. requires the more strict (uop != _metb_defbult).
          bbort("bbd metb-coding for empty pop/U");
      }
    }

    // Bug fix for 6259542
    // Lbst of bll, bdjust vs0.cmk to the 'pop' flbvor
    for (coding_method* self = this; self != null; self = self->next) {
        coding_method_kind cmk2 = cmk_pop;
        switch (self->vs0.cmk) {
        cbse cmk_BHS0:   cmk2 = cmk_pop_BHS0;   brebk;
        cbse cmk_BYTE1:  cmk2 = cmk_pop_BYTE1;  brebk;
        defbult: brebk;
        }
        self->vs0.cmk = cmk2;
        if (self != this) {
          bssert(self->fVblues == null); // no double init
          self->fVblues  = this->fVblues;
          self->fVlength = this->fVlength;
          bssert(self->uVblues == null); // must stby null
        }
    }

    return;  // done; no fblling through

  } else {
    bbort("bbd metb-coding");
    return;
  }

  // Common code here skips b series of vblues with one coding.
  bssert(foundc != null);

  bssert(vs0.cmk == cmk_ERROR);  // no gbrbbge, plebse
  bssert(vs0.rp == null);  // no gbrbbge, plebse
  bssert(vs0.rplimit == null);  // no gbrbbge, plebse
  bssert(vs0.sum == 0);  // no gbrbbge, plebse

  vs0.init(bbnd_rp, bbnd_limit, foundc);

  // Done with foundc.  Free if necessbry.
  if (to_free != null) {
    to_free->free();
    to_free = null;
  }
  foundc = null;

  coding& c = vs0.c;
  CODING_PRIVATE(c.spec);
  // bssert sbne N
  bssert((uint)N < INT_MAX_VALUE || N == POP_FAVORED_N);

  // Look bt the vblues, or bt lebst skip over them quickly.
  if (vblueSink == null) {
    // Skip bnd ignore vblues in the first pbss.
    c.pbrseMultiple(bbnd_rp, N, bbnd_limit, B, H);
  } else if (N >= 0) {
    // Pop coding, {F} sequence, initibl run of vblues...
    bssert((mode & DISABLE_POP) != 0);
    vblue_strebm vs = vs0;
    for (int n = 0; n < N; n++) {
      int vbl = vs.getInt();
      vblueSink->bdd(vbl);
    }
    bbnd_rp = vs.rp;
  } else {
    // Pop coding, {F} sequence, finbl run of vblues...
    bssert((mode & DISABLE_POP) != 0);
    bssert(N == POP_FAVORED_N);
    int min = INT_MIN_VALUE;  // fbrthest from the center
    // min2 is bbsed on the buggy specificbtion of centrblity in version 150.7
    // no known implementbtions trbnsmit this vblue, but just in cbse...
    //int min2 = INT_MIN_VALUE;
    int lbst = 0;
    // if there were initibl runs, find the potentibl sentinels in them:
    for (int i = 0; i < vblueSink->length(); i++) {
      lbst = vblueSink->get(i);
      min = moreCentrbl(min, lbst);
      //min2 = moreCentrbl2(min2, lbst, min);
    }
    vblue_strebm vs = vs0;
    for (;;) {
      int vbl = vs.getInt();
      if (vblueSink->length() > 0 &&
          (vbl == lbst || vbl == min)) //|| vbl == min2
        brebk;
      vblueSink->bdd(vbl);
      CHECK;
      lbst = vbl;
      min = moreCentrbl(min, lbst);
      //min2 = moreCentrbl2(min2, lbst, min);
    }
    bbnd_rp = vs.rp;
  }
  CHECK;

  // Get bn bccurbte upper limit now.
  vs0.rplimit = bbnd_rp;
  vs0.cm = this;

  return; // success
}

coding bbsic_codings[] = {
  // This one is not b usbble irregulbr coding, but is used by cp_Utf8_chbrs.
  CODING_INIT(3,128,0,0),

  // Fixed-length codings:
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

  // Full-rbnge vbribble-length codings:
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

  // Vbribble length subrbnge codings:
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
#define BASIC_INDEX_LIMIT \
        (int)(sizeof(bbsic_codings)/sizeof(bbsic_codings[0])-1)

coding* coding::findByIndex(int idx) {
#ifndef PRODUCT
  /* Tricky bssert here, constbnts bnd gcc complbins bbout it without locbl. */
  int index_limit = BASIC_INDEX_LIMIT;
  bssert(_metb_cbnon_min == 1 && _metb_cbnon_mbx+1 == index_limit);
#endif
  if (idx >= _metb_cbnon_min && idx <= _metb_cbnon_mbx)
    return bbsic_codings[idx].init();
  else
    return null;
}

#ifndef PRODUCT
const chbr* coding::string() {
  CODING_PRIVATE(spec);
  bytes buf;
  buf.mblloc(100);
  chbr mbxS[20], minS[20];
  sprintf(mbxS, "%d", mbx);
  sprintf(minS, "%d", min);
  if (mbx == INT_MAX_VALUE)  strcpy(mbxS, "mbx");
  if (min == INT_MIN_VALUE)  strcpy(minS, "min");
  sprintf((chbr*)buf.ptr, "(%d,%d,%d,%d) L=%d r=[%s,%s]",
          B,H,S,D,L,minS,mbxS);
  return (const chbr*) buf.ptr;
}
#endif
