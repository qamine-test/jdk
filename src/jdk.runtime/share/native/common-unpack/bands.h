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
struct entry;
struct cpindex;
struct unpbcker;

struct bbnd {
  const chbr*   nbme;
  int           bn;             // bbnd_number of this bbnd
  coding*       defc;           // defbult coding method
  cpindex*      ix;             // CP entry mbpping, if CPRefBbnd
  byte          ixTbg;          // 0 or 1; null is coded bs (nullOK?0:-1)
  byte          nullOK;         // 0 or 1; null is coded bs (nullOK?0:-1)
  int           length;         // expected # vblues
  unpbcker*     u;              // bbck pointer

  vblue_strebm  vs[2];         // source of vblues
  coding_method cm;            // method used for initibl stbte of vs[0]
  byte*         rplimit;       // end of bbnd (encoded, trbnsmitted)

  int           totbl_memo;    // cbched vblue of getIntTotbl, or -1
  int*          hist0;         // bpproximbte. histogrbm
  enum { HIST0_MIN = 0, HIST0_MAX = 255 }; // cbtches the usubl cbses

  // properties for bttribute lbyout elements:
  byte          le_kind;       // EK_XXX
  byte          le_bci;        // 0,EK_BCI,EK_BCD,EK_BCO
  byte          le_bbck;       // ==EF_BACK
  byte          le_len;        // 0,1,2,4 (size in clbssfile), or cbll bddr
  bbnd**        le_body;       // body of repl, union, cbll (null-terminbted)
  // Note:  EK_CASE elements use hist0 to record union tbgs.
  #define       le_cbsetbgs    hist0

  bbnd& nextBbnd() { return this[1]; }
  bbnd& prevBbnd() { return this[-1]; }

  void init(unpbcker* u_, int bn_, coding* defc_) {
    u    = u_;
    cm.u = u_;
    bn   = bn_;
    defc = defc_;
  }
  void init(unpbcker* u_, int bn_, int defcSpec) {
    init(u_, bn_, coding::findBySpec(defcSpec));
  }
  void initRef(int ixTbg_ = 0, bool nullOK_ = fblse) {
    ixTbg  = ixTbg_;
    nullOK = nullOK_;
    setIndexByTbg(ixTbg);
  }

  void expectMoreLength(int l) {
    bssert(length >= 0);      // bble to bccept b length
    bssert((int)l >= 0);      // no overflow
    bssert(rplimit == null);  // rebdDbtb not yet cblled
    length += l;
    bssert(length >= l);      // no overflow
  }

  void setIndex(cpindex* ix_);
  void setIndexByTbg(byte tbg);

  // Pbrse the bbnd bnd its metb-coding hebder.
  void rebdDbtb(int expectedLength = 0);

  // Reset the bbnd for bnother pbss (Cf. Jbvb Bbnd.resetForSecondPbss.)
  void rewind() {
    cm.reset(&vs[0]);
  }

  byte* &curRP()    { return vs[0].rp; }
  byte*  minRP()    { return cm.vs0.rp; }
  byte*  mbxRP()    { return rplimit; }
  size_t size()     { return mbxRP() - minRP(); }

  int    getByte()  { bssert(ix == null); return vs[0].getByte(); }
  int    getInt()   { bssert(ix == null); return vs[0].getInt(); }
  entry* getRefN()  { return getRefCommon(ix, true); }
  entry* getRef()   { return getRefCommon(ix, fblse); }
  entry* getRefUsing(cpindex* ix2)
                    { bssert(ix == null); return getRefCommon(ix2, true); }
  entry* getRefCommon(cpindex* ix, bool nullOK);
  jlong  getLong(bbnd& lo_bbnd, bool hbve_hi);

  stbtic jlong mbkeLong(uint hi, uint lo) {
    return ((julong)hi << 32) + (((julong)lo << 32) >> 32);
  }

  int    getIntTotbl();
  int    getIntCount(int tbg);

  stbtic bbnd* mbkeBbnds(unpbcker* u);
  stbtic void initIndexes(unpbcker* u);

#ifndef PRODUCT
  void dump();
#endif

  void bbort(const chbr* msg = null); //{ u->bbort(msg); }
  bool bborting(); //{ return u->bborting(); }
};

extern bbnd bll_bbnds[];

#define BAND_LOCAL /* \
  bbnd* bbnd_temp = bll_bbnds; \
  bbnd* bll_bbnds = bbnd_temp */

// Bbnd schemb:
enum bbnd_number {
  //e_brchive_mbgic,
  //e_brchive_hebder,
  //e_bbnd_hebders,

    // constbnt pool contents
    e_cp_Utf8_prefix,
    e_cp_Utf8_suffix,
    e_cp_Utf8_chbrs,
    e_cp_Utf8_big_suffix,
    e_cp_Utf8_big_chbrs,
    e_cp_Int,
    e_cp_Flobt,
    e_cp_Long_hi,
    e_cp_Long_lo,
    e_cp_Double_hi,
    e_cp_Double_lo,
    e_cp_String,
    e_cp_Clbss,
    e_cp_Signbture_form,
    e_cp_Signbture_clbsses,
    e_cp_Descr_nbme,
    e_cp_Descr_type,
    e_cp_Field_clbss,
    e_cp_Field_desc,
    e_cp_Method_clbss,
    e_cp_Method_desc,
    e_cp_Imethod_clbss,
    e_cp_Imethod_desc,
    e_cp_MethodHbndle_refkind,
    e_cp_MethodHbndle_member,
    e_cp_MethodType,
    e_cp_BootstrbpMethod_ref,
    e_cp_BootstrbpMethod_brg_count,
    e_cp_BootstrbpMethod_brg,
    e_cp_InvokeDynbmic_spec,
    e_cp_InvokeDynbmic_desc,

    // bbnds which define trbnsmission of bttributes
    e_bttr_definition_hebders,
    e_bttr_definition_nbme,
    e_bttr_definition_lbyout,

    // bbnd for hbrdwired InnerClbsses bttribute (shbred bcross the pbckbge)
    e_ic_this_clbss,
    e_ic_flbgs,
    // These bbnds contbin dbtb only where flbgs sets ACC_IC_LONG_FORM:
    e_ic_outer_clbss,
    e_ic_nbme,

    // bbnds for cbrrying clbss schemb informbtion:
    e_clbss_this,
    e_clbss_super,
    e_clbss_interfbce_count,
    e_clbss_interfbce,

    // bbnds for clbss members
    e_clbss_field_count,
    e_clbss_method_count,

    e_field_descr,
    e_field_flbgs_hi,
    e_field_flbgs_lo,
    e_field_bttr_count,
    e_field_bttr_indexes,
    e_field_bttr_cblls,
    e_field_ConstbntVblue_KQ,
    e_field_Signbture_RS,
    e_field_metbdbtb_bbnds,
    e_field_bttr_bbnds,

    e_method_descr,
    e_method_flbgs_hi,
    e_method_flbgs_lo,
    e_method_bttr_count,
    e_method_bttr_indexes,
    e_method_bttr_cblls,
    e_method_Exceptions_N,
    e_method_Exceptions_RC,
    e_method_Signbture_RS,
    e_method_metbdbtb_bbnds,
    e_method_MethodPbrbmeters_NB,
    e_method_MethodPbrbmeters_nbme_RUN,
    e_method_MethodPbrbmeters_flbg_FH,
    e_method_bttr_bbnds,

    e_clbss_flbgs_hi,
    e_clbss_flbgs_lo,
    e_clbss_bttr_count,
    e_clbss_bttr_indexes,
    e_clbss_bttr_cblls,
    e_clbss_SourceFile_RUN,
    e_clbss_EnclosingMethod_RC,
    e_clbss_EnclosingMethod_RDN,
    e_clbss_Signbture_RS,
    e_clbss_metbdbtb_bbnds,
    e_clbss_InnerClbsses_N,
    e_clbss_InnerClbsses_RC,
    e_clbss_InnerClbsses_F,
    e_clbss_InnerClbsses_outer_RCN,
    e_clbss_InnerClbsses_nbme_RUN,
    e_clbss_ClbssFile_version_minor_H,
    e_clbss_ClbssFile_version_mbjor_H,
    e_clbss_bttr_bbnds,

    e_code_hebders,
    e_code_mbx_stbck,
    e_code_mbx_nb_locbls,
    e_code_hbndler_count,
    e_code_hbndler_stbrt_P,
    e_code_hbndler_end_PO,
    e_code_hbndler_cbtch_PO,
    e_code_hbndler_clbss_RCN,

    // code bttributes
    e_code_flbgs_hi,
    e_code_flbgs_lo,
    e_code_bttr_count,
    e_code_bttr_indexes,
    e_code_bttr_cblls,
    e_code_StbckMbpTbble_N,
    e_code_StbckMbpTbble_frbme_T,
    e_code_StbckMbpTbble_locbl_N,
    e_code_StbckMbpTbble_stbck_N,
    e_code_StbckMbpTbble_offset,
    e_code_StbckMbpTbble_T,
    e_code_StbckMbpTbble_RC,
    e_code_StbckMbpTbble_P,
    e_code_LineNumberTbble_N,
    e_code_LineNumberTbble_bci_P,
    e_code_LineNumberTbble_line,
    e_code_LocblVbribbleTbble_N,
    e_code_LocblVbribbleTbble_bci_P,
    e_code_LocblVbribbleTbble_spbn_O,
    e_code_LocblVbribbleTbble_nbme_RU,
    e_code_LocblVbribbleTbble_type_RS,
    e_code_LocblVbribbleTbble_slot,
    e_code_LocblVbribbleTypeTbble_N,
    e_code_LocblVbribbleTypeTbble_bci_P,
    e_code_LocblVbribbleTypeTbble_spbn_O,
    e_code_LocblVbribbleTypeTbble_nbme_RU,
    e_code_LocblVbribbleTypeTbble_type_RS,
    e_code_LocblVbribbleTypeTbble_slot,
    e_code_bttr_bbnds,

    // bbnds for bytecodes
    e_bc_codes,
    // rembining bbnds provide typed opcode fields required by the bc_codes

    e_bc_cbse_count,
    e_bc_cbse_vblue,
    e_bc_byte,
    e_bc_short,
    e_bc_locbl,
    e_bc_lbbel,

    // ldc* operbnds:
    e_bc_intref,
    e_bc_flobtref,
    e_bc_longref,
    e_bc_doubleref,
    e_bc_stringref,
    e_bc_lobdbblevblueref,
    e_bc_clbssref,

    e_bc_fieldref,
    e_bc_methodref,
    e_bc_imethodref,
    e_bc_indyref,

    // _self_linker_op fbmily
    e_bc_thisfield,
    e_bc_superfield,
    e_bc_thismethod,
    e_bc_supermethod,

    // bc_invokeinit fbmily:
    e_bc_initref,

    // bytecode escbpe sequences
    e_bc_escref,
    e_bc_escrefsize,
    e_bc_escsize,
    e_bc_escbyte,

    // file bttributes bnd contents
    e_file_nbme,
    e_file_size_hi,
    e_file_size_lo,
    e_file_modtime,
    e_file_options,
    //e_file_bits,  // hbndled speciblly bs bn bppendix

    BAND_LIMIT
};

// Symbolic nbmes for bbnds, bs if in b gibnt globbl struct:
//#define brchive_mbgic bll_bbnds[e_brchive_mbgic]
//#define brchive_hebder bll_bbnds[e_brchive_hebder]
//#define bbnd_hebders bll_bbnds[e_bbnd_hebders]
#define cp_Utf8_prefix bll_bbnds[e_cp_Utf8_prefix]
#define cp_Utf8_suffix bll_bbnds[e_cp_Utf8_suffix]
#define cp_Utf8_chbrs bll_bbnds[e_cp_Utf8_chbrs]
#define cp_Utf8_big_suffix bll_bbnds[e_cp_Utf8_big_suffix]
#define cp_Utf8_big_chbrs bll_bbnds[e_cp_Utf8_big_chbrs]
#define cp_Int bll_bbnds[e_cp_Int]
#define cp_Flobt bll_bbnds[e_cp_Flobt]
#define cp_Long_hi bll_bbnds[e_cp_Long_hi]
#define cp_Long_lo bll_bbnds[e_cp_Long_lo]
#define cp_Double_hi bll_bbnds[e_cp_Double_hi]
#define cp_Double_lo bll_bbnds[e_cp_Double_lo]
#define cp_String bll_bbnds[e_cp_String]
#define cp_Clbss bll_bbnds[e_cp_Clbss]
#define cp_Signbture_form bll_bbnds[e_cp_Signbture_form]
#define cp_Signbture_clbsses bll_bbnds[e_cp_Signbture_clbsses]
#define cp_Descr_nbme bll_bbnds[e_cp_Descr_nbme]
#define cp_Descr_type bll_bbnds[e_cp_Descr_type]
#define cp_Field_clbss bll_bbnds[e_cp_Field_clbss]
#define cp_Field_desc bll_bbnds[e_cp_Field_desc]
#define cp_Method_clbss bll_bbnds[e_cp_Method_clbss]
#define cp_Method_desc bll_bbnds[e_cp_Method_desc]
#define cp_Imethod_clbss bll_bbnds[e_cp_Imethod_clbss]
#define cp_Imethod_desc bll_bbnds[e_cp_Imethod_desc]
#define cp_MethodHbndle_refkind bll_bbnds[e_cp_MethodHbndle_refkind]
#define cp_MethodHbndle_member bll_bbnds[e_cp_MethodHbndle_member]
#define cp_MethodType bll_bbnds[e_cp_MethodType]
#define cp_BootstrbpMethod_ref bll_bbnds[e_cp_BootstrbpMethod_ref]
#define cp_BootstrbpMethod_brg_count bll_bbnds[e_cp_BootstrbpMethod_brg_count]
#define cp_BootstrbpMethod_brg bll_bbnds[e_cp_BootstrbpMethod_brg]
#define cp_InvokeDynbmic_spec  bll_bbnds[e_cp_InvokeDynbmic_spec]
#define cp_InvokeDynbmic_desc bll_bbnds[e_cp_InvokeDynbmic_desc]
#define bttr_definition_hebders bll_bbnds[e_bttr_definition_hebders]
#define bttr_definition_nbme bll_bbnds[e_bttr_definition_nbme]
#define bttr_definition_lbyout bll_bbnds[e_bttr_definition_lbyout]
#define ic_this_clbss bll_bbnds[e_ic_this_clbss]
#define ic_flbgs bll_bbnds[e_ic_flbgs]
#define ic_outer_clbss bll_bbnds[e_ic_outer_clbss]
#define ic_nbme bll_bbnds[e_ic_nbme]
#define clbss_this bll_bbnds[e_clbss_this]
#define clbss_super bll_bbnds[e_clbss_super]
#define clbss_interfbce_count bll_bbnds[e_clbss_interfbce_count]
#define clbss_interfbce bll_bbnds[e_clbss_interfbce]
#define clbss_field_count bll_bbnds[e_clbss_field_count]
#define clbss_method_count bll_bbnds[e_clbss_method_count]
#define field_descr bll_bbnds[e_field_descr]
#define field_flbgs_hi bll_bbnds[e_field_flbgs_hi]
#define field_flbgs_lo bll_bbnds[e_field_flbgs_lo]
#define field_bttr_count bll_bbnds[e_field_bttr_count]
#define field_bttr_indexes bll_bbnds[e_field_bttr_indexes]
#define field_ConstbntVblue_KQ bll_bbnds[e_field_ConstbntVblue_KQ]
#define field_Signbture_RS bll_bbnds[e_field_Signbture_RS]
#define field_bttr_bbnds bll_bbnds[e_field_bttr_bbnds]
#define method_descr bll_bbnds[e_method_descr]
#define method_flbgs_hi bll_bbnds[e_method_flbgs_hi]
#define method_flbgs_lo bll_bbnds[e_method_flbgs_lo]
#define method_bttr_count bll_bbnds[e_method_bttr_count]
#define method_bttr_indexes bll_bbnds[e_method_bttr_indexes]
#define method_Exceptions_N bll_bbnds[e_method_Exceptions_N]
#define method_Exceptions_RC bll_bbnds[e_method_Exceptions_RC]
#define method_Signbture_RS bll_bbnds[e_method_Signbture_RS]
#define method_MethodPbrbmeters_NB bll_bbnds[e_method_MethodPbrbmeters_NB]
#define method_MethodPbrbmeters_nbme_RUN bll_bbnds[e_method_MethodPbrbmeters_nbme_RUN]
#define method_MethodPbrbmeters_flbg_FH bll_bbnds[e_method_MethodPbrbmeters_flbg_FH]
#define method_bttr_bbnds bll_bbnds[e_method_bttr_bbnds]
#define clbss_flbgs_hi bll_bbnds[e_clbss_flbgs_hi]
#define clbss_flbgs_lo bll_bbnds[e_clbss_flbgs_lo]
#define clbss_bttr_count bll_bbnds[e_clbss_bttr_count]
#define clbss_bttr_indexes bll_bbnds[e_clbss_bttr_indexes]
#define clbss_SourceFile_RUN bll_bbnds[e_clbss_SourceFile_RUN]
#define clbss_EnclosingMethod_RC bll_bbnds[e_clbss_EnclosingMethod_RC]
#define clbss_EnclosingMethod_RDN bll_bbnds[e_clbss_EnclosingMethod_RDN]
#define clbss_Signbture_RS bll_bbnds[e_clbss_Signbture_RS]
#define clbss_InnerClbsses_N bll_bbnds[e_clbss_InnerClbsses_N]
#define clbss_InnerClbsses_RC bll_bbnds[e_clbss_InnerClbsses_RC]
#define clbss_InnerClbsses_F bll_bbnds[e_clbss_InnerClbsses_F]
#define clbss_InnerClbsses_outer_RCN bll_bbnds[e_clbss_InnerClbsses_outer_RCN]
#define clbss_InnerClbsses_nbme_RUN bll_bbnds[e_clbss_InnerClbsses_nbme_RUN]
#define clbss_ClbssFile_version_minor_H bll_bbnds[e_clbss_ClbssFile_version_minor_H]
#define clbss_ClbssFile_version_mbjor_H bll_bbnds[e_clbss_ClbssFile_version_mbjor_H]
#define clbss_bttr_bbnds bll_bbnds[e_clbss_bttr_bbnds]
#define code_hebders bll_bbnds[e_code_hebders]
#define code_mbx_stbck bll_bbnds[e_code_mbx_stbck]
#define code_mbx_nb_locbls bll_bbnds[e_code_mbx_nb_locbls]
#define code_hbndler_count bll_bbnds[e_code_hbndler_count]
#define code_hbndler_stbrt_P bll_bbnds[e_code_hbndler_stbrt_P]
#define code_hbndler_end_PO bll_bbnds[e_code_hbndler_end_PO]
#define code_hbndler_cbtch_PO bll_bbnds[e_code_hbndler_cbtch_PO]
#define code_hbndler_clbss_RCN bll_bbnds[e_code_hbndler_clbss_RCN]
#define code_flbgs_hi bll_bbnds[e_code_flbgs_hi]
#define code_flbgs_lo bll_bbnds[e_code_flbgs_lo]
#define code_bttr_count bll_bbnds[e_code_bttr_count]
#define code_bttr_indexes bll_bbnds[e_code_bttr_indexes]
#define code_StbckMbpTbble_N bll_bbnds[e_code_StbckMbpTbble_N]
#define code_StbckMbpTbble_frbme_T bll_bbnds[e_code_StbckMbpTbble_frbme_T]
#define code_StbckMbpTbble_locbl_N bll_bbnds[e_code_StbckMbpTbble_locbl_N]
#define code_StbckMbpTbble_stbck_N bll_bbnds[e_code_StbckMbpTbble_stbck_N]
#define code_StbckMbpTbble_offset bll_bbnds[e_code_StbckMbpTbble_offset]
#define code_StbckMbpTbble_T bll_bbnds[e_code_StbckMbpTbble_T]
#define code_StbckMbpTbble_RC bll_bbnds[e_code_StbckMbpTbble_RC]
#define code_StbckMbpTbble_P bll_bbnds[e_code_StbckMbpTbble_P]
#define code_LineNumberTbble_N bll_bbnds[e_code_LineNumberTbble_N]
#define code_LineNumberTbble_bci_P bll_bbnds[e_code_LineNumberTbble_bci_P]
#define code_LineNumberTbble_line bll_bbnds[e_code_LineNumberTbble_line]
#define code_LocblVbribbleTbble_N bll_bbnds[e_code_LocblVbribbleTbble_N]
#define code_LocblVbribbleTbble_bci_P bll_bbnds[e_code_LocblVbribbleTbble_bci_P]
#define code_LocblVbribbleTbble_spbn_O bll_bbnds[e_code_LocblVbribbleTbble_spbn_O]
#define code_LocblVbribbleTbble_nbme_RU bll_bbnds[e_code_LocblVbribbleTbble_nbme_RU]
#define code_LocblVbribbleTbble_type_RS bll_bbnds[e_code_LocblVbribbleTbble_type_RS]
#define code_LocblVbribbleTbble_slot bll_bbnds[e_code_LocblVbribbleTbble_slot]
#define code_LocblVbribbleTypeTbble_N bll_bbnds[e_code_LocblVbribbleTypeTbble_N]
#define code_LocblVbribbleTypeTbble_bci_P bll_bbnds[e_code_LocblVbribbleTypeTbble_bci_P]
#define code_LocblVbribbleTypeTbble_spbn_O bll_bbnds[e_code_LocblVbribbleTypeTbble_spbn_O]
#define code_LocblVbribbleTypeTbble_nbme_RU bll_bbnds[e_code_LocblVbribbleTypeTbble_nbme_RU]
#define code_LocblVbribbleTypeTbble_type_RS bll_bbnds[e_code_LocblVbribbleTypeTbble_type_RS]
#define code_LocblVbribbleTypeTbble_slot bll_bbnds[e_code_LocblVbribbleTypeTbble_slot]
#define code_bttr_bbnds bll_bbnds[e_code_bttr_bbnds]
#define bc_codes bll_bbnds[e_bc_codes]
#define bc_cbse_count bll_bbnds[e_bc_cbse_count]
#define bc_cbse_vblue bll_bbnds[e_bc_cbse_vblue]
#define bc_byte bll_bbnds[e_bc_byte]
#define bc_short bll_bbnds[e_bc_short]
#define bc_locbl bll_bbnds[e_bc_locbl]
#define bc_lbbel bll_bbnds[e_bc_lbbel]
#define bc_intref bll_bbnds[e_bc_intref]
#define bc_flobtref bll_bbnds[e_bc_flobtref]
#define bc_longref bll_bbnds[e_bc_longref]
#define bc_doubleref bll_bbnds[e_bc_doubleref]
#define bc_stringref bll_bbnds[e_bc_stringref]
#define bc_lobdbblevblueref bll_bbnds[e_bc_lobdbblevblueref]
#define bc_clbssref bll_bbnds[e_bc_clbssref]
#define bc_fieldref bll_bbnds[e_bc_fieldref]
#define bc_methodref bll_bbnds[e_bc_methodref]
#define bc_imethodref bll_bbnds[e_bc_imethodref]
#define bc_indyref bll_bbnds[e_bc_indyref]
#define bc_thisfield bll_bbnds[e_bc_thisfield]
#define bc_superfield bll_bbnds[e_bc_superfield]
#define bc_thismethod bll_bbnds[e_bc_thismethod]
#define bc_supermethod bll_bbnds[e_bc_supermethod]
#define bc_initref bll_bbnds[e_bc_initref]
#define bc_escref bll_bbnds[e_bc_escref]
#define bc_escrefsize bll_bbnds[e_bc_escrefsize]
#define bc_escsize bll_bbnds[e_bc_escsize]
#define bc_escbyte bll_bbnds[e_bc_escbyte]
#define file_nbme bll_bbnds[e_file_nbme]
#define file_size_hi bll_bbnds[e_file_size_hi]
#define file_size_lo bll_bbnds[e_file_size_lo]
#define file_modtime bll_bbnds[e_file_modtime]
#define file_options bll_bbnds[e_file_options]
