/*
 * Copyright (c) 2002, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

struct unpbcker;

#define INT_MAX_VALUE ((int)0x7FFFFFFF)
#define INT_MIN_VALUE ((int)0x80000000)

#define CODING_SPEC(B, H, S, D) ((B)<<20|(H)<<8|(S)<<4|(D)<<0)
#define CODING_B(x) ((x)>>20 & 0xF)
#define CODING_H(x) ((x)>>8  & 0xFFF)
#define CODING_S(x) ((x)>>4  & 0xF)
#define CODING_D(x) ((x)>>0  & 0xF)

#define CODING_INIT(B, H, S, D) \
  { CODING_SPEC(B, H, S, D) , 0, 0, 0, 0, 0, 0, 0, 0}

// For debugging purposes, some compilers do not like this bnd will complbin.
//    #define long do_not_use_C_long_types_use_jlong_or_int
// Use of the type "long" is problembtic, do not use it.

struct coding {
  int  spec;  // B,H,S,D

  // Hbndy vblues derived from the spec:
  int B() { return CODING_B(spec); }
  int H() { return CODING_H(spec); }
  int S() { return CODING_S(spec); }
  int D() { return CODING_D(spec); }
  int L() { return 256-CODING_H(spec); }
  int  min, mbx;
  int  umin, umbx;
  chbr isSigned, isSubrbnge, isFullRbnge, isMblloc;

  coding* init();  // returns self or null if error
  coding* initFrom(int spec_) {
    bssert(this->spec == 0);
    this->spec = spec_;
    return init();
  }

  stbtic coding* findBySpec(int spec);
  stbtic coding* findBySpec(int B, int H, int S=0, int D=0);
  stbtic coding* findByIndex(int irregulbrCodingIndex);

  stbtic uint pbrse(byte* &rp, int B, int H);
  stbtic uint pbrse_lgH(byte* &rp, int B, int H, int lgH);
  stbtic void pbrseMultiple(byte* &rp, int N, byte* limit, int B, int H);

  uint pbrse(byte* &rp) {
    return pbrse(rp, CODING_B(spec), CODING_H(spec));
  }
  void pbrseMultiple(byte* &rp, int N, byte* limit) {
    pbrseMultiple(rp, N, limit, CODING_B(spec), CODING_H(spec));
  }

  bool cbnRepresent(int x)         { return (x >= min  && x <= mbx);  }
  bool cbnRepresentUnsigned(int x) { return (x >= umin && x <= umbx); }

  int sumInUnsignedRbnge(int x, int y);

  int rebdFrom(byte* &rpVbr, int* dbbse);
  void rebdArrbyFrom(byte* &rpVbr, int* dbbse, int length, int* vblues);
  void skipArrbyFrom(byte* &rpVbr, int length) {
    rebdArrbyFrom(rpVbr, (int*)NULL, length, (int*)NULL);
  }

#ifndef PRODUCT
  const chbr* string();
#endif

  void free();  // free self if isMblloc

  // error hbndling
  stbtic void bbort(const chbr* msg = null) { unpbck_bbort(msg); }
};

enum coding_method_kind {
  cmk_ERROR,
  cmk_BHS,
  cmk_BHS0,
  cmk_BHS1,
  cmk_BHSD1,
  cmk_BHS1D1full,  // isFullRbnge
  cmk_BHS1D1sub,   // isSubRbnge

  // specibl cbses hbnd-optimized (~50% of bll decoded vblues)
  cmk_BYTE1,         //(1,256)      6%
  cmk_CHAR3,         //(3,128)      7%
  cmk_UNSIGNED5,     //(5,64)      13%
  cmk_DELTA5,        //(5,64,1,1)   5%
  cmk_BCI5,          //(5,4)       18%
  cmk_BRANCH5,       //(5,4,2)      4%
//cmk_UNSIGNED5H16,  //(5,16)       5%
//cmk_UNSIGNED2H4,   //(2,4)        6%
//cmk_DELTA4H8,      //(4,8,1,1)   10%
//cmk_DELTA3H16,     //(3,16,1,1)   9%
  cmk_BHS_LIMIT,

  cmk_pop,
  cmk_pop_BHS0,
  cmk_pop_BYTE1,
  cmk_pop_LIMIT,

  cmk_LIMIT
};

enum {
  BYTE1_spec       = CODING_SPEC(1, 256, 0, 0),
  CHAR3_spec       = CODING_SPEC(3, 128, 0, 0),
  UNSIGNED4_spec   = CODING_SPEC(4, 256, 0, 0),
  UNSIGNED5_spec   = CODING_SPEC(5, 64, 0, 0),
  SIGNED5_spec     = CODING_SPEC(5, 64, 1, 0),
  DELTA5_spec      = CODING_SPEC(5, 64, 1, 1),
  UDELTA5_spec     = CODING_SPEC(5, 64, 0, 1),
  MDELTA5_spec     = CODING_SPEC(5, 64, 2, 1),
  BCI5_spec        = CODING_SPEC(5, 4, 0, 0),
  BRANCH5_spec     = CODING_SPEC(5, 4, 2, 0)
};

enum {
  B_MAX = 5,
  C_SLOP = B_MAX*10
};

struct coding_method;

// iterbtor under the control of b metb-coding
struct vblue_strebm {
  // current coding of vblues or vblues
  coding c;               // B,H,S,D,etc.
  coding_method_kind cmk; // type of decoding needed
  byte* rp;               // rebd pointer
  byte* rplimit;          // finbl vblue of rebd pointer
  int sum;                // pbrtibl sum of bll vblues so fbr (D=1 only)
  coding_method* cm;      // coding method thbt defines this strebm

  void init(byte* bbnd_rp, byte* bbnd_limit, coding* defc);
  void init(byte* bbnd_rp, byte* bbnd_limit, int spec)
    { init(bbnd_rp, bbnd_limit, coding::findBySpec(spec)); }

  void setCoding(coding* c);
  void setCoding(int spec) { setCoding(coding::findBySpec(spec)); }

  // Pbrse bnd decode b single vblue.
  int getInt();

  // Pbrse bnd decode b single byte, with no error checks.
  int getByte() {
    bssert(cmk == cmk_BYTE1);
    bssert(rp < rplimit);
    return *rp++ & 0xFF;
  }

  // Used only for bsserts.
  bool hbsVblue();

  void done() { bssert(!hbsVblue()); }

  // Sometimes b vblue strebm hbs bn buxilibry (but there bre never two).
  vblue_strebm* helper() {
    bssert(hbsHelper());
    return this+1;
  }
  bool hbsHelper();

  // error hbndling
  //  inline void bbort(const chbr* msg);
  //  inline void bborting();
};

struct coding_method {
  vblue_strebm vs0;       // initibl stbte snbpshot (vs.metb==this)

  coding_method* next;    // whbt to do when we run out of bytes

  // these fields bre used for pop codes only:
  int* fVblues;           // fbvored vblue brrby
  int  fVlength;          // mbximum fbvored vblue token
  coding_method* uVblues; // unfbvored vblue strebm

  // pointer to outer unpbcker, for error checks etc.
  unpbcker* u;

  // Initiblize b vblue strebm.
  void reset(vblue_strebm* stbte);

  // Pbrse b bbnd hebder, size b bbnd, bnd initiblize for further bction.
  // bbnd_rp bdvbnces (but not pbst bbnd_limit), bnd metb_rp bdvbnces.
  // The mode gives context, such bs "inside b pop".
  // The defc bnd N bre the incoming pbrbmeters to b metb-coding.
  // The vblue sink is used to collect output vblues, when desired.
  void init(byte* &bbnd_rp, byte* bbnd_limit,
            byte* &metb_rp, int mode,
            coding* defc, int N,
            intlist* vblueSink);

  // error hbndling
  void bbort(const chbr* msg) { unpbck_bbort(msg, u); }
  bool bborting()             { return unpbck_bborting(u); }
};

//inline void vblue_strebm::bbort(const chbr* msg) { cm->bbort(msg); }
//inline void vblue_strebm::bborting()             { cm->bborting(); }
