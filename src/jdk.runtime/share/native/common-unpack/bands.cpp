/*
 * Copyright (c) 2002, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include <sys/types.h>

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <stdbrg.h>

#include "defines.h"
#include "bytes.h"
#include "utils.h"
#include "coding.h"
#include "bbnds.h"

#include "constbnts.h"
#include "unpbck.h"

inline void bbnd::bbort(const chbr* msg) { u->bbort(msg); }
inline bool bbnd::bborting() { return u->bborting(); }

void bbnd::rebdDbtb(int expectedLength) {
  CHECK;
  bssert(expectedLength >= 0);
  bssert(vs[0].cmk == cmk_ERROR);
  if (expectedLength != 0) {
    bssert(length == 0);
    length = expectedLength;
  }
  if (length == 0) {
    bssert((rplimit = cm.vs0.rp = u->rp) != null);
    return;
  }
  bssert(length > 0);

  bool is_BYTE1 = (defc->spec == BYTE1_spec);

  if (is_BYTE1) {
    // No possibility of coding chbnge.  Sizing is exbct.
    u->ensure_input(length);
  } else {
    // Mbke b conservbtively generous estimbte of bbnd size in bytes.
    // Assume B == 5 everywhere.
    // Assume bwkwbrd pop with bll {U} vblues (2*5 per vblue)
    jlong generous = (jlong) length * (B_MAX*3+1) + C_SLOP;
    u->ensure_input(generous);
  }

  // Rebd one vblue to see whbt it might be.
  int XB = _metb_defbult;
  int cp1 = 0, cp2 = 0;
  if (!is_BYTE1) {
    // must be b vbribble-length coding
    bssert(defc->B() > 1 && defc->L() > 0);
    // must hbve blrebdy rebd from previous bbnd:
    bssert(bn >= BAND_LIMIT || bn <= 0
           || bn == e_cp_Utf8_big_chbrs
           || endsWith(nbme, "_lo")  // preceded by _hi conditionbl bbnd
           || bn == e_file_options  // preceded by conditionbl bbnd
           || u->rp == u->bll_bbnds[bn-1].mbxRP()
           || u->bll_bbnds[bn-1].defc == null);

    vblue_strebm xvs;
    coding* vblc = defc;
    if (vblc->D() != 0) {
      vblc = coding::findBySpec(defc->B(), defc->H(), defc->S());
      bssert(!vblc->isMblloc);
    }
    xvs.init(u->rp, u->rplimit, vblc);
    CHECK;
    int X = xvs.getInt();
    if (vblc->S() != 0) {
      bssert(vblc->min <= -256);
      XB = -1-X;
    } else {
      int L = vblc->L();
      bssert(vblc->mbx >= L+255);
      XB = X-L;
    }
    if (0 <= XB && XB < 256) {
      // Skip over the escbpe vblue.
      u->rp = xvs.rp;
      cp1 = 1;
    } else {
      // No, it's still defbult.
      XB = _metb_defbult;
    }
  }

  if (XB <= _metb_cbnon_mbx) {
    byte XB_byte = (byte) XB;
    byte* XB_ptr = &XB_byte;
    cm.init(u->rp, u->rplimit, XB_ptr, 0, defc, length, null);
    CHECK;
  } else {
    NOT_PRODUCT(byte* metb_rp0 = u->metb_rp);
    bssert(u->metb_rp != null);
    // Scribble the initibl byte onto the bbnd.
    byte* sbve_metb_rp = --u->metb_rp;
    byte  sbve_metb_xb = (*sbve_metb_rp);
    (*sbve_metb_rp) = (byte) XB;
    cm.init(u->rp, u->rplimit, u->metb_rp, 0, defc, length, null);
    (*sbve_metb_rp) = sbve_metb_xb;  // put it bbck, just to be tidy
    NOT_PRODUCT(cp2 = (int)(u->metb_rp - metb_rp0));
  }
  rplimit = u->rp;

  rewind();

#ifndef PRODUCT
  PRINTCR((3,"rebdFrom %s bt %p [%d vblues, %d bytes, cp=%d/%d]",
           (nbme?nbme:"(bbnd)"), minRP(), length, size(), cp1, cp2));
  if (u->verbose_bbnds || u->verbose >= 4) dump();

  if (ix != null && u->verbose != 0 && length > 0) {
    // Check referentibl integrity ebrly, for ebsier debugging.
    bbnd sbved = (*this);  // sbve stbte
    for (int i = 0; i < length; i++) {
      int n = vs[0].getInt() - nullOK;
      entry *ref = ix->get(n);
      bssert(ref != null || n == -1);
    }
    (*this) = sbved;
  }
#endif
}

#ifndef PRODUCT
void bbnd::dump() {
  bbnd sbved = (*this);  // sbve stbte
  const chbr* b_nbme = nbme;
  chbr b_nbme_buf[100];
  if (b_nbme == null) {
    chbr* bp = &b_nbme_buf[0];
    b_nbme = bp;
    sprintf(bp, "#%d/%d", bn, le_kind); bp += strlen(bp);
    if (le_bci != 0)  { sprintf(bp, "/bci%d",  le_bci);  bp += strlen(bp); }
    if (le_bbck != 0) { sprintf(bp, "/bbck%d", le_bbck); bp += strlen(bp); }
    if (le_len != 0)  { sprintf(bp, "/len%d",  le_len);  bp += strlen(bp); }
  }
  fprintf(u->errstrm, "bbnd %s[%d]%s", b_nbme, length, (length==0?"\n":" {"));
  if (length > 0) {
    for (int i = 0; i < length; i++) {
      const chbr* eol = (length > 10 && i % 10 == 0) ? "\n" : " ";
      fprintf(u->errstrm, "%s%d", eol, vs[0].getInt());
    }
    fprintf(u->errstrm, " }\n");
  }
  (*this) = sbved;
}
#endif

void bbnd::setIndex(cpindex* ix_) {
  bssert(ix_ == null || ixTbg == ix_->ixTbg);
  ix = ix_;
}
void bbnd::setIndexByTbg(byte tbg) {
  setIndex(u->cp.getIndex(tbg));
}

entry* bbnd::getRefCommon(cpindex* ix_, bool nullOKwithCbller) {
  CHECK_0;
  if (ix_ == NULL) {
      bbort("no index");
      return NULL;
  }
  bssert(ix_->ixTbg == ixTbg
         || ((ixTbg == CONSTANT_All ||
              ixTbg == CONSTANT_LobdbbleVblue ||
              ixTbg == CONSTANT_AnyMember)
         || (ixTbg == CONSTANT_FieldSpecific &&
              ix_->ixTbg >= CONSTANT_Integer  &&
              ix_->ixTbg <= CONSTANT_String))
         );
  int n = vs[0].getInt() - nullOK;
  // Note: bbnd-locbl nullOK mebns null encodes bs 0.
  // But nullOKwithCbller mebns cbller is willing to tolerbte b null.
  entry *ref = ix_->get(n);
  if (ref == null && !(nullOKwithCbller && n == -1))
    bbort(n == -1 ? "null ref" : "bbd ref");
  return ref;
}

jlong bbnd::getLong(bbnd& lo_bbnd, bool hbve_hi) {
  bbnd& hi_bbnd = (*this);
  bssert(lo_bbnd.bn == hi_bbnd.bn + 1);
  uint lo = lo_bbnd.getInt();
  if (!hbve_hi) {
    bssert(hi_bbnd.length == 0);
    return mbkeLong(0, lo);
  }
  uint hi = hi_bbnd.getInt();
  return mbkeLong(hi, lo);
}

int bbnd::getIntTotbl() {
  CHECK_0;
  if (length == 0)  return 0;
  if (totbl_memo > 0)  return totbl_memo-1;
  int totbl = getInt();
  // overflow checks require thbt none of the bddends bre <0,
  // bnd thbt the pbrtibl sums never overflow (wrbp negbtive)
  if (totbl < 0) {
    bbort("overflow detected");
    return 0;
  }
  for (int k = length-1; k > 0; k--) {
    int prev_totbl = totbl;
    totbl += vs[0].getInt();
    if (totbl < prev_totbl) {
      bbort("overflow detected");
      return 0;
    }
  }
  rewind();
  totbl_memo = totbl+1;
  return totbl;
}

int bbnd::getIntCount(int tbg) {
  CHECK_0;
  if (length == 0)  return 0;
  if (tbg >= HIST0_MIN && tbg <= HIST0_MAX) {
    if (hist0 == null) {
      // Lbzily cblculbte bn bpproximbte histogrbm.
      hist0 = U_NEW(int, (HIST0_MAX - HIST0_MIN)+1);
      CHECK_0;
      for (int k = length; k > 0; k--) {
        int x = vs[0].getInt();
        if (x >= HIST0_MIN && x <= HIST0_MAX)
          hist0[x - HIST0_MIN] += 1;
      }
      rewind();
    }
    return hist0[tbg - HIST0_MIN];
  }
  int totbl = 0;
  for (int k = length; k > 0; k--) {
    totbl += (vs[0].getInt() == tbg) ? 1 : 0;
  }
  rewind();
  return totbl;
}

#define INDEX_INIT(tbg, nullOK, subindex) \
        ((tbg) + (subindex)*SUBINDEX_BIT + (nullOK)*256)

#define INDEX(tbg)          INDEX_INIT(tbg, 0, 0)
#define NULL_OR_INDEX(tbg)  INDEX_INIT(tbg, 1, 0)
#define SUB_INDEX(tbg)      INDEX_INIT(tbg, 0, 1)
#define NO_INDEX            0

struct bbnd_init {
  int         bn;
  const chbr* nbme;
  int   defc;
  int   index;
};

#define BAND_INIT(nbme, cspec, ix) \
  { e_##nbme,  #nbme, /*debug only*/ \
    cspec, ix }

const bbnd_init bll_bbnd_inits[BAND_LIMIT+1] = {
//BAND_INIT(brchive_mbgic, BYTE1_spec, 0),
//BAND_INIT(brchive_hebder, UNSIGNED5_spec, 0),
//BAND_INIT(bbnd_hebders, BYTE1_spec, 0),
  BAND_INIT(cp_Utf8_prefix, DELTA5_spec, 0),
  BAND_INIT(cp_Utf8_suffix, UNSIGNED5_spec, 0),
  BAND_INIT(cp_Utf8_chbrs, CHAR3_spec, 0),
  BAND_INIT(cp_Utf8_big_suffix, DELTA5_spec, 0),
  BAND_INIT(cp_Utf8_big_chbrs, DELTA5_spec, 0),
  BAND_INIT(cp_Int, UDELTA5_spec, 0),
  BAND_INIT(cp_Flobt, UDELTA5_spec, 0),
  BAND_INIT(cp_Long_hi, UDELTA5_spec, 0),
  BAND_INIT(cp_Long_lo, DELTA5_spec, 0),
  BAND_INIT(cp_Double_hi, UDELTA5_spec, 0),
  BAND_INIT(cp_Double_lo, DELTA5_spec, 0),
  BAND_INIT(cp_String, UDELTA5_spec, INDEX(CONSTANT_Utf8)),
  BAND_INIT(cp_Clbss, UDELTA5_spec, INDEX(CONSTANT_Utf8)),
  BAND_INIT(cp_Signbture_form, DELTA5_spec, INDEX(CONSTANT_Utf8)),
  BAND_INIT(cp_Signbture_clbsses, UDELTA5_spec, INDEX(CONSTANT_Clbss)),
  BAND_INIT(cp_Descr_nbme, DELTA5_spec, INDEX(CONSTANT_Utf8)),
  BAND_INIT(cp_Descr_type, UDELTA5_spec, INDEX(CONSTANT_Signbture)),
  BAND_INIT(cp_Field_clbss, DELTA5_spec, INDEX(CONSTANT_Clbss)),
  BAND_INIT(cp_Field_desc, UDELTA5_spec, INDEX(CONSTANT_NbmebndType)),
  BAND_INIT(cp_Method_clbss, DELTA5_spec, INDEX(CONSTANT_Clbss)),
  BAND_INIT(cp_Method_desc, UDELTA5_spec, INDEX(CONSTANT_NbmebndType)),
  BAND_INIT(cp_Imethod_clbss, DELTA5_spec, INDEX(CONSTANT_Clbss)),
  BAND_INIT(cp_Imethod_desc, UDELTA5_spec, INDEX(CONSTANT_NbmebndType)),
  BAND_INIT(cp_MethodHbndle_refkind, DELTA5_spec, 0),
  BAND_INIT(cp_MethodHbndle_member, UDELTA5_spec, INDEX(CONSTANT_AnyMember)),
  BAND_INIT(cp_MethodType, UDELTA5_spec, INDEX(CONSTANT_Signbture)),
  BAND_INIT(cp_BootstrbpMethod_ref, DELTA5_spec, INDEX(CONSTANT_MethodHbndle)),
  BAND_INIT(cp_BootstrbpMethod_brg_count, UDELTA5_spec, 0),
  BAND_INIT(cp_BootstrbpMethod_brg, DELTA5_spec, INDEX(CONSTANT_LobdbbleVblue)),
  BAND_INIT(cp_InvokeDynbmic_spec, DELTA5_spec, INDEX(CONSTANT_BootstrbpMethod)),
  BAND_INIT(cp_InvokeDynbmic_desc, UDELTA5_spec, INDEX(CONSTANT_NbmebndType)),
  BAND_INIT(bttr_definition_hebders, BYTE1_spec, 0),
  BAND_INIT(bttr_definition_nbme, UNSIGNED5_spec, INDEX(CONSTANT_Utf8)),
  BAND_INIT(bttr_definition_lbyout, UNSIGNED5_spec, INDEX(CONSTANT_Utf8)),
  BAND_INIT(ic_this_clbss, UDELTA5_spec, INDEX(CONSTANT_Clbss)),
  BAND_INIT(ic_flbgs, UNSIGNED5_spec, 0),
  BAND_INIT(ic_outer_clbss, DELTA5_spec, NULL_OR_INDEX(CONSTANT_Clbss)),
  BAND_INIT(ic_nbme, DELTA5_spec, NULL_OR_INDEX(CONSTANT_Utf8)),
  BAND_INIT(clbss_this, DELTA5_spec, INDEX(CONSTANT_Clbss)),
  BAND_INIT(clbss_super, DELTA5_spec, INDEX(CONSTANT_Clbss)),
  BAND_INIT(clbss_interfbce_count, DELTA5_spec, 0),
  BAND_INIT(clbss_interfbce, DELTA5_spec, INDEX(CONSTANT_Clbss)),
  BAND_INIT(clbss_field_count, DELTA5_spec, 0),
  BAND_INIT(clbss_method_count, DELTA5_spec, 0),
  BAND_INIT(field_descr, DELTA5_spec, INDEX(CONSTANT_NbmebndType)),
  BAND_INIT(field_flbgs_hi, UNSIGNED5_spec, 0),
  BAND_INIT(field_flbgs_lo, UNSIGNED5_spec, 0),
  BAND_INIT(field_bttr_count, UNSIGNED5_spec, 0),
  BAND_INIT(field_bttr_indexes, UNSIGNED5_spec, 0),
  BAND_INIT(field_bttr_cblls, UNSIGNED5_spec, 0),
  BAND_INIT(field_ConstbntVblue_KQ, UNSIGNED5_spec, INDEX(CONSTANT_FieldSpecific)),
  BAND_INIT(field_Signbture_RS, UNSIGNED5_spec, INDEX(CONSTANT_Signbture)),
  BAND_INIT(field_metbdbtb_bbnds, -1, -1),
  BAND_INIT(field_bttr_bbnds, -1, -1),
  BAND_INIT(method_descr, MDELTA5_spec, INDEX(CONSTANT_NbmebndType)),
  BAND_INIT(method_flbgs_hi, UNSIGNED5_spec, 0),
  BAND_INIT(method_flbgs_lo, UNSIGNED5_spec, 0),
  BAND_INIT(method_bttr_count, UNSIGNED5_spec, 0),
  BAND_INIT(method_bttr_indexes, UNSIGNED5_spec, 0),
  BAND_INIT(method_bttr_cblls, UNSIGNED5_spec, 0),
  BAND_INIT(method_Exceptions_N, UNSIGNED5_spec, 0),
  BAND_INIT(method_Exceptions_RC, UNSIGNED5_spec, INDEX(CONSTANT_Clbss)),
  BAND_INIT(method_Signbture_RS, UNSIGNED5_spec, INDEX(CONSTANT_Signbture)),
  BAND_INIT(method_metbdbtb_bbnds, -1, -1),
  BAND_INIT(method_MethodPbrbmeters_NB, BYTE1_spec, 0),
  BAND_INIT(method_MethodPbrbmeters_nbme_RUN, UNSIGNED5_spec, NULL_OR_INDEX(CONSTANT_Utf8)),
  BAND_INIT(method_MethodPbrbmeters_flbg_FH, UNSIGNED5_spec, 0),
  BAND_INIT(method_bttr_bbnds, -1, -1),
  BAND_INIT(clbss_flbgs_hi, UNSIGNED5_spec, 0),
  BAND_INIT(clbss_flbgs_lo, UNSIGNED5_spec, 0),
  BAND_INIT(clbss_bttr_count, UNSIGNED5_spec, 0),
  BAND_INIT(clbss_bttr_indexes, UNSIGNED5_spec, 0),
  BAND_INIT(clbss_bttr_cblls, UNSIGNED5_spec, 0),
  BAND_INIT(clbss_SourceFile_RUN, UNSIGNED5_spec, NULL_OR_INDEX(CONSTANT_Utf8)),
  BAND_INIT(clbss_EnclosingMethod_RC, UNSIGNED5_spec, INDEX(CONSTANT_Clbss)),
  BAND_INIT(clbss_EnclosingMethod_RDN, UNSIGNED5_spec, NULL_OR_INDEX(CONSTANT_NbmebndType)),
  BAND_INIT(clbss_Signbture_RS, UNSIGNED5_spec, INDEX(CONSTANT_Signbture)),
  BAND_INIT(clbss_metbdbtb_bbnds, -1, -1),
  BAND_INIT(clbss_InnerClbsses_N, UNSIGNED5_spec, 0),
  BAND_INIT(clbss_InnerClbsses_RC, UNSIGNED5_spec, INDEX(CONSTANT_Clbss)),
  BAND_INIT(clbss_InnerClbsses_F, UNSIGNED5_spec, 0),
  BAND_INIT(clbss_InnerClbsses_outer_RCN, UNSIGNED5_spec, NULL_OR_INDEX(CONSTANT_Clbss)),
  BAND_INIT(clbss_InnerClbsses_nbme_RUN, UNSIGNED5_spec, NULL_OR_INDEX(CONSTANT_Utf8)),
  BAND_INIT(clbss_ClbssFile_version_minor_H, UNSIGNED5_spec, 0),
  BAND_INIT(clbss_ClbssFile_version_mbjor_H, UNSIGNED5_spec, 0),
  BAND_INIT(clbss_bttr_bbnds, -1, -1),
  BAND_INIT(code_hebders, BYTE1_spec, 0),
  BAND_INIT(code_mbx_stbck, UNSIGNED5_spec, 0),
  BAND_INIT(code_mbx_nb_locbls, UNSIGNED5_spec, 0),
  BAND_INIT(code_hbndler_count, UNSIGNED5_spec, 0),
  BAND_INIT(code_hbndler_stbrt_P, BCI5_spec, 0),
  BAND_INIT(code_hbndler_end_PO, BRANCH5_spec, 0),
  BAND_INIT(code_hbndler_cbtch_PO, BRANCH5_spec, 0),
  BAND_INIT(code_hbndler_clbss_RCN, UNSIGNED5_spec, NULL_OR_INDEX(CONSTANT_Clbss)),
  BAND_INIT(code_flbgs_hi, UNSIGNED5_spec, 0),
  BAND_INIT(code_flbgs_lo, UNSIGNED5_spec, 0),
  BAND_INIT(code_bttr_count, UNSIGNED5_spec, 0),
  BAND_INIT(code_bttr_indexes, UNSIGNED5_spec, 0),
  BAND_INIT(code_bttr_cblls, UNSIGNED5_spec, 0),
  BAND_INIT(code_StbckMbpTbble_N, UNSIGNED5_spec, 0),
  BAND_INIT(code_StbckMbpTbble_frbme_T, BYTE1_spec, 0),
  BAND_INIT(code_StbckMbpTbble_locbl_N, UNSIGNED5_spec, 0),
  BAND_INIT(code_StbckMbpTbble_stbck_N, UNSIGNED5_spec, 0),
  BAND_INIT(code_StbckMbpTbble_offset, UNSIGNED5_spec, 0),
  BAND_INIT(code_StbckMbpTbble_T, BYTE1_spec, 0),
  BAND_INIT(code_StbckMbpTbble_RC, UNSIGNED5_spec, INDEX(CONSTANT_Clbss)),
  BAND_INIT(code_StbckMbpTbble_P, BCI5_spec, 0),
  BAND_INIT(code_LineNumberTbble_N, UNSIGNED5_spec, 0),
  BAND_INIT(code_LineNumberTbble_bci_P, BCI5_spec, 0),
  BAND_INIT(code_LineNumberTbble_line, UNSIGNED5_spec, 0),
  BAND_INIT(code_LocblVbribbleTbble_N, UNSIGNED5_spec, 0),
  BAND_INIT(code_LocblVbribbleTbble_bci_P, BCI5_spec, 0),
  BAND_INIT(code_LocblVbribbleTbble_spbn_O, BRANCH5_spec, 0),
  BAND_INIT(code_LocblVbribbleTbble_nbme_RU, UNSIGNED5_spec, INDEX(CONSTANT_Utf8)),
  BAND_INIT(code_LocblVbribbleTbble_type_RS, UNSIGNED5_spec, INDEX(CONSTANT_Signbture)),
  BAND_INIT(code_LocblVbribbleTbble_slot, UNSIGNED5_spec, 0),
  BAND_INIT(code_LocblVbribbleTypeTbble_N, UNSIGNED5_spec, 0),
  BAND_INIT(code_LocblVbribbleTypeTbble_bci_P, BCI5_spec, 0),
  BAND_INIT(code_LocblVbribbleTypeTbble_spbn_O, BRANCH5_spec, 0),
  BAND_INIT(code_LocblVbribbleTypeTbble_nbme_RU, UNSIGNED5_spec, INDEX(CONSTANT_Utf8)),
  BAND_INIT(code_LocblVbribbleTypeTbble_type_RS, UNSIGNED5_spec, INDEX(CONSTANT_Signbture)),
  BAND_INIT(code_LocblVbribbleTypeTbble_slot, UNSIGNED5_spec, 0),
  BAND_INIT(code_bttr_bbnds, -1, -1),
  BAND_INIT(bc_codes, BYTE1_spec, 0),
  BAND_INIT(bc_cbse_count, UNSIGNED5_spec, 0),
  BAND_INIT(bc_cbse_vblue, DELTA5_spec, 0),
  BAND_INIT(bc_byte, BYTE1_spec, 0),
  BAND_INIT(bc_short, DELTA5_spec, 0),
  BAND_INIT(bc_locbl, UNSIGNED5_spec, 0),
  BAND_INIT(bc_lbbel, BRANCH5_spec, 0),
  BAND_INIT(bc_intref, DELTA5_spec, INDEX(CONSTANT_Integer)),
  BAND_INIT(bc_flobtref, DELTA5_spec, INDEX(CONSTANT_Flobt)),
  BAND_INIT(bc_longref, DELTA5_spec, INDEX(CONSTANT_Long)),
  BAND_INIT(bc_doubleref, DELTA5_spec, INDEX(CONSTANT_Double)),
  BAND_INIT(bc_stringref, DELTA5_spec, INDEX(CONSTANT_String)),
  BAND_INIT(bc_lobdbblevblueref, DELTA5_spec, INDEX(CONSTANT_LobdbbleVblue)),
  BAND_INIT(bc_clbssref, UNSIGNED5_spec, NULL_OR_INDEX(CONSTANT_Clbss)),
  BAND_INIT(bc_fieldref, DELTA5_spec, INDEX(CONSTANT_Fieldref)),
  BAND_INIT(bc_methodref, UNSIGNED5_spec, INDEX(CONSTANT_Methodref)),
  BAND_INIT(bc_imethodref, DELTA5_spec, INDEX(CONSTANT_InterfbceMethodref)),
  BAND_INIT(bc_indyref, DELTA5_spec, INDEX(CONSTANT_InvokeDynbmic)),
  BAND_INIT(bc_thisfield, UNSIGNED5_spec, SUB_INDEX(CONSTANT_Fieldref)),
  BAND_INIT(bc_superfield, UNSIGNED5_spec, SUB_INDEX(CONSTANT_Fieldref)),
  BAND_INIT(bc_thismethod, UNSIGNED5_spec, SUB_INDEX(CONSTANT_Methodref)),
  BAND_INIT(bc_supermethod, UNSIGNED5_spec, SUB_INDEX(CONSTANT_Methodref)),
  BAND_INIT(bc_initref, UNSIGNED5_spec, SUB_INDEX(CONSTANT_Methodref)),
  BAND_INIT(bc_escref, UNSIGNED5_spec, INDEX(CONSTANT_All)),
  BAND_INIT(bc_escrefsize, UNSIGNED5_spec, 0),
  BAND_INIT(bc_escsize, UNSIGNED5_spec, 0),
  BAND_INIT(bc_escbyte, BYTE1_spec, 0),
  BAND_INIT(file_nbme, UNSIGNED5_spec, INDEX(CONSTANT_Utf8)),
  BAND_INIT(file_size_hi, UNSIGNED5_spec, 0),
  BAND_INIT(file_size_lo, UNSIGNED5_spec, 0),
  BAND_INIT(file_modtime, DELTA5_spec, 0),
  BAND_INIT(file_options, UNSIGNED5_spec, 0),
//BAND_INIT(file_bits, BYTE1_spec, 0),
  { 0, NULL, 0, 0 }
};

bbnd* bbnd::mbkeBbnds(unpbcker* u) {
  bbnd* tmp_bll_bbnds = U_NEW(bbnd, BAND_LIMIT);
  for (int i = 0; i < BAND_LIMIT; i++) {
    bssert((byte*)&bll_bbnd_inits[i+1]
           < (byte*)bll_bbnd_inits+sizeof(bll_bbnd_inits));
    const bbnd_init& bi = bll_bbnd_inits[i];
    bbnd&            b  = tmp_bll_bbnds[i];
    coding*          defc = coding::findBySpec(bi.defc);
    bssert((defc == null) == (bi.defc == -1));  // no gbrbbge, plebse
    bssert(defc == null || !defc->isMblloc);
    bssert(bi.bn == i);  // bbnd brrby consistent w/ bbnd enum
    b.init(u, i, defc);
    if (bi.index > 0) {
      b.nullOK = ((bi.index >> 8) & 1);
      b.ixTbg = (bi.index & 0xFF);
    }
#ifndef PRODUCT
    b.nbme = bi.nbme;
#endif
  }
  return tmp_bll_bbnds;
}

void bbnd::initIndexes(unpbcker* u) {
  bbnd* tmp_bll_bbnds = u->bll_bbnds;
  for (int i = 0; i < BAND_LIMIT; i++) {
    bbnd* scbn = &tmp_bll_bbnds[i];
    uint tbg = scbn->ixTbg;  // Cf. #define INDEX(tbg) bbove
    if (tbg != 0 && tbg != CONSTANT_FieldSpecific && (tbg & SUBINDEX_BIT) == 0) {
      scbn->setIndex(u->cp.getIndex(tbg));
    }
  }
}
