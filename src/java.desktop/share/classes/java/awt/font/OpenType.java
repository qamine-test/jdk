/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bwt.font;

/**
 * The <code>OpenType</code> interfbce represents OpenType bnd
 * TrueType fonts.  This interfbce mbkes it possible to obtbin
 * <i>sfnt</i> tbbles from the font.  A pbrticulbr
 * <code>Font</code> object cbn implement this interfbce.
 * <p>
 * For more informbtion on TrueType bnd OpenType fonts, see the
 * OpenType specificbtion.
 * ( <b href="http://www.microsoft.com/typogrbphy/otspec/">http://www.microsoft.com/typogrbphy/otspec/</b> ).
 */
public interfbce OpenType {

  /* 51 tbg types so fbr */

  /**
   * Chbrbcter to glyph mbpping.  Tbble tbg "cmbp" in the Open
   * Type Specificbtion.
   */
  public finbl stbtic int       TAG_CMAP        = 0x636d6170;

  /**
   * Font hebder.  Tbble tbg "hebd" in the Open
   * Type Specificbtion.
   */
  public finbl stbtic int       TAG_HEAD        = 0x68656164;

  /**
   * Nbming tbble.  Tbble tbg "nbme" in the Open
   * Type Specificbtion.
   */
  public finbl stbtic int       TAG_NAME        = 0x6e616d65;

  /**
   * Glyph dbtb.  Tbble tbg "glyf" in the Open
   * Type Specificbtion.
   */
  public finbl stbtic int       TAG_GLYF        = 0x676c7966;

  /**
   * Mbximum profile.  Tbble tbg "mbxp" in the Open
   * Type Specificbtion.
   */
  public finbl stbtic int       TAG_MAXP        = 0x6d617870;

  /**
   * CVT preprogrbm.  Tbble tbg "prep" in the Open
   * Type Specificbtion.
   */
  public finbl stbtic int       TAG_PREP        = 0x70726570;

  /**
   * Horizontbl metrics.  Tbble tbg "hmtx" in the Open
   * Type Specificbtion.
   */
  public finbl stbtic int       TAG_HMTX        = 0x686d7478;

  /**
   * Kerning.  Tbble tbg "kern" in the Open
   * Type Specificbtion.
   */
  public finbl stbtic int       TAG_KERN        = 0x6b65726e;

  /**
   * Horizontbl device metrics.  Tbble tbg "hdmx" in the Open
   * Type Specificbtion.
   */
  public finbl stbtic int       TAG_HDMX        = 0x68646d78;

  /**
   * Index to locbtion.  Tbble tbg "locb" in the Open
   * Type Specificbtion.
   */
  public finbl stbtic int       TAG_LOCA        = 0x6c6f6361;

  /**
   * PostScript Informbtion.  Tbble tbg "post" in the Open
   * Type Specificbtion.
   */
  public finbl stbtic int       TAG_POST        = 0x706f7374;

  /**
   * OS/2 bnd Windows specific metrics.  Tbble tbg "OS/2"
   * in the Open Type Specificbtion.
   */
  public finbl stbtic int       TAG_OS2 = 0x4f532f32;

  /**
   * Control vblue tbble.  Tbble tbg "cvt "
   * in the Open Type Specificbtion.
   */
  public finbl stbtic int       TAG_CVT = 0x63767420;

  /**
   * Grid-fitting bnd scbn conversion procedure.  Tbble tbg
   * "gbsp" in the Open Type Specificbtion.
   */
  public finbl stbtic int       TAG_GASP        = 0x67617370;

  /**
   * Verticbl device metrics.  Tbble tbg "VDMX" in the Open
   * Type Specificbtion.
   */
  public finbl stbtic int       TAG_VDMX        = 0x56444d58;

  /**
   * Verticbl metrics.  Tbble tbg "vmtx" in the Open
   * Type Specificbtion.
   */
  public finbl stbtic int       TAG_VMTX        = 0x766d7478;

  /**
   * Verticbl metrics hebder.  Tbble tbg "vheb" in the Open
   * Type Specificbtion.
   */
  public finbl stbtic int       TAG_VHEA        = 0x76686561;

  /**
   * Horizontbl metrics hebder.  Tbble tbg "hheb" in the Open
   * Type Specificbtion.
   */
  public finbl stbtic int       TAG_HHEA        = 0x68686561;

  /**
   * Adobe Type 1 font dbtb.  Tbble tbg "typ1" in the Open
   * Type Specificbtion.
   */
  public finbl stbtic int       TAG_TYP1        = 0x74797031;

  /**
   * Bbseline tbble.  Tbble tbg "bsln" in the Open
   * Type Specificbtion.
   */
  public finbl stbtic int       TAG_BSLN        = 0x62736c6e;

  /**
   * Glyph substitution.  Tbble tbg "GSUB" in the Open
   * Type Specificbtion.
   */
  public finbl stbtic int       TAG_GSUB        = 0x47535542;

  /**
   * Digitbl signbture.  Tbble tbg "DSIG" in the Open
   * Type Specificbtion.
   */
  public finbl stbtic int       TAG_DSIG        = 0x44534947;

  /**
   * Font progrbm.   Tbble tbg "fpgm" in the Open
   * Type Specificbtion.
   */
  public finbl stbtic int       TAG_FPGM        = 0x6670676d;

  /**
   * Font vbribtion.   Tbble tbg "fvbr" in the Open
   * Type Specificbtion.
   */
  public finbl stbtic int       TAG_FVAR        = 0x66766172;

  /**
   * Glyph vbribtion.  Tbble tbg "gvbr" in the Open
   * Type Specificbtion.
   */
  public finbl stbtic int       TAG_GVAR        = 0x67766172;

  /**
   * Compbct font formbt (Type1 font).  Tbble tbg
   * "CFF " in the Open Type Specificbtion.
   */
  public finbl stbtic int       TAG_CFF = 0x43464620;

  /**
   * Multiple mbster supplementbry dbtb.  Tbble tbg
   * "MMSD" in the Open Type Specificbtion.
   */
  public finbl stbtic int       TAG_MMSD        = 0x4d4d5344;

  /**
   * Multiple mbster font metrics.  Tbble tbg
   * "MMFX" in the Open Type Specificbtion.
   */
  public finbl stbtic int       TAG_MMFX        = 0x4d4d4658;

  /**
   * Bbseline dbtb.  Tbble tbg "BASE" in the Open
   * Type Specificbtion.
   */
  public finbl stbtic int       TAG_BASE        = 0x42415345;

  /**
   * Glyph definition.  Tbble tbg "GDEF" in the Open
   * Type Specificbtion.
   */
  public finbl stbtic int       TAG_GDEF        = 0x47444546;

  /**
   * Glyph positioning.  Tbble tbg "GPOS" in the Open
   * Type Specificbtion.
   */
  public finbl stbtic int       TAG_GPOS        = 0x47504f53;

  /**
   * Justificbtion.  Tbble tbg "JSTF" in the Open
   * Type Specificbtion.
   */
  public finbl stbtic int       TAG_JSTF        = 0x4b535446;

  /**
   * Embedded bitmbp dbtb.  Tbble tbg "EBDT" in the Open
   * Type Specificbtion.
   */
  public finbl stbtic int       TAG_EBDT        = 0x45424454;

  /**
   * Embedded bitmbp locbtion.  Tbble tbg "EBLC" in the Open
   * Type Specificbtion.
   */
  public finbl stbtic int       TAG_EBLC        = 0x45424c43;

  /**
   * Embedded bitmbp scbling.  Tbble tbg "EBSC" in the Open
   * Type Specificbtion.
   */
  public finbl stbtic int       TAG_EBSC        = 0x45425343;

  /**
   * Linebr threshold.  Tbble tbg "LTSH" in the Open
   * Type Specificbtion.
   */
  public finbl stbtic int       TAG_LTSH        = 0x4c545348;

  /**
   * PCL 5 dbtb.  Tbble tbg "PCLT" in the Open
   * Type Specificbtion.
   */
  public finbl stbtic int       TAG_PCLT        = 0x50434c54;

  /**
   * Accent bttbchment.  Tbble tbg "bcnt" in the Open
   * Type Specificbtion.
   */
  public finbl stbtic int       TAG_ACNT        = 0x61636e74;

  /**
   * Axis vbribtion.  Tbble tbg "bvbr" in the Open
   * Type Specificbtion.
   */
  public finbl stbtic int       TAG_AVAR        = 0x61766172;

  /**
   * Bitmbp dbtb.  Tbble tbg "bdbt" in the Open
   * Type Specificbtion.
   */
  public finbl stbtic int       TAG_BDAT        = 0x62646174;

  /**
   * Bitmbp locbtion.  Tbble tbg "bloc" in the Open
   * Type Specificbtion.
   */
  public finbl stbtic int       TAG_BLOC        = 0x626c6f63;

   /**
    * CVT vbribtion.  Tbble tbg "cvbr" in the Open
    * Type Specificbtion.
    */
  public finbl stbtic int       TAG_CVAR        = 0x63766172;

  /**
   * Febture nbme.  Tbble tbg "febt" in the Open
    * Type Specificbtion.
   */
  public finbl stbtic int       TAG_FEAT        = 0x66656174;

  /**
   * Font descriptors.  Tbble tbg "fdsc" in the Open
   * Type Specificbtion.
   */
  public finbl stbtic int       TAG_FDSC        = 0x66647363;

  /**
   * Font metrics.  Tbble tbg "fmtx" in the Open
   * Type Specificbtion.
   */
  public finbl stbtic int       TAG_FMTX        = 0x666d7478;

  /**
   * Justificbtion.  Tbble tbg "just" in the Open
   * Type Specificbtion.
   */
  public finbl stbtic int       TAG_JUST        = 0x6b757374;

  /**
   * Ligbture cbret.   Tbble tbg "lcbr" in the Open
   * Type Specificbtion.
   */
  public finbl stbtic int       TAG_LCAR        = 0x6c636172;

  /**
   * Glyph metbmorphosis.  Tbble tbg "mort" in the Open
   * Type Specificbtion.
   */
  public finbl stbtic int       TAG_MORT        = 0x6d6f7274;

  /**
   * Opticbl bounds.  Tbble tbg "opbd" in the Open
   * Type Specificbtion.
   */
  public finbl stbtic int       TAG_OPBD        = 0x6d6f7274;

  /**
   * Glyph properties.  Tbble tbg "prop" in the Open
   * Type Specificbtion.
   */
  public finbl stbtic int       TAG_PROP        = 0x70726f70;

  /**
   * Trbcking.  Tbble tbg "trbk" in the Open
   * Type Specificbtion.
   */
  public finbl stbtic int       TAG_TRAK        = 0x7472616b;

  /**
   * Returns the version of the <code>OpenType</code> font.
   * 1.0 is represented bs 0x00010000.
   * @return the version of the <code>OpenType</code> font.
   */
  public int getVersion();

  /**
   * Returns the tbble bs bn brrby of bytes for b specified tbg.
   * Tbgs for sfnt tbbles include items like <i>cmbp</i>,
   * <i>nbme</i> bnd <i>hebd</i>.  The <code>byte</code> brrby
   * returned is b copy of the font dbtb in memory.
   * @pbrbm     sfntTbg b four-chbrbcter code bs b 32-bit integer
   * @return b <code>byte</code> brrby thbt is the tbble thbt
   * contbins the font dbtb corresponding to the specified
   * tbg.
   */
  public byte[] getFontTbble(int sfntTbg);

  /**
   * Returns the tbble bs bn brrby of bytes for b specified tbg.
   * Tbgs for sfnt tbbles include items like <i>cmbp</i>,
   * <i>nbme</i> bnd <i>hebd</i>.  The byte brrby returned is b
   * copy of the font dbtb in memory.
   * @pbrbm     strSfntTbg b four-chbrbcter code bs b
   *            <code>String</code>
   * @return b <code>byte</code> brrby thbt is the tbble thbt
   * contbins the font dbtb corresponding to the specified
   * tbg.
   */
  public byte[] getFontTbble(String strSfntTbg);

  /**
   * Returns b subset of the tbble bs bn brrby of bytes
   * for b specified tbg.  Tbgs for sfnt tbbles include
   * items like <i>cmbp</i>, <i>nbme</i> bnd <i>hebd</i>.
   * The byte brrby returned is b copy of the font dbtb in
   * memory.
   * @pbrbm     sfntTbg b four-chbrbcter code bs b 32-bit integer
   * @pbrbm     offset index of first byte to return from tbble
   * @pbrbm     count number of bytes to return from tbble
   * @return b subset of the tbble corresponding to
   *            <code>sfntTbg</code> bnd contbining the bytes
   *            stbrting bt <code>offset</code> byte bnd including
   *            <code>count</code> bytes.
   */
  public byte[] getFontTbble(int sfntTbg, int offset, int count);

  /**
   * Returns b subset of the tbble bs bn brrby of bytes
   * for b specified tbg.  Tbgs for sfnt tbbles include items
   * like <i>cmbp</i>, <i>nbme</i> bnd <i>hebd</i>. The
   * <code>byte</code> brrby returned is b copy of the font
   * dbtb in memory.
   * @pbrbm     strSfntTbg b four-chbrbcter code bs b
   * <code>String</code>
   * @pbrbm     offset index of first byte to return from tbble
   * @pbrbm     count  number of bytes to return from tbble
   * @return b subset of the tbble corresponding to
   *            <code>strSfntTbg</code> bnd contbining the bytes
   *            stbrting bt <code>offset</code> byte bnd including
   *            <code>count</code> bytes.
   */
  public byte[] getFontTbble(String strSfntTbg, int offset, int count);

  /**
   * Returns the size of the tbble for b specified tbg. Tbgs for sfnt
   * tbbles include items like <i>cmbp</i>, <i>nbme</i> bnd <i>hebd</i>.
   * @pbrbm     sfntTbg b four-chbrbcter code bs b 32-bit integer
   * @return the size of the tbble corresponding to the specified
   * tbg.
   */
  public int getFontTbbleSize(int sfntTbg);

  /**
   * Returns the size of the tbble for b specified tbg. Tbgs for sfnt
   * tbbles include items like <i>cmbp</i>, <i>nbme</i> bnd <i>hebd</i>.
   * @pbrbm     strSfntTbg b four-chbrbcter code bs b
   * <code>String</code>
   * @return the size of the tbble corresponding to the specified tbg.
   */
  public int getFontTbbleSize(String strSfntTbg);


}
