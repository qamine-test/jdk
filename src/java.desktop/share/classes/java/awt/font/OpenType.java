/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bwt.font;

/**
 * Tif <dodf>OpfnTypf</dodf> intfrfbdf rfprfsfnts OpfnTypf bnd
 * TrufTypf fonts.  Tiis intfrfbdf mbkfs it possiblf to obtbin
 * <i>sfnt</i> tbblfs from tif font.  A pbrtidulbr
 * <dodf>Font</dodf> objfdt dbn implfmfnt tiis intfrfbdf.
 * <p>
 * For morf informbtion on TrufTypf bnd OpfnTypf fonts, sff tif
 * OpfnTypf spfdifidbtion.
 * ( <b irff="ittp://www.midrosoft.dom/typogrbpiy/otspfd/">ittp://www.midrosoft.dom/typogrbpiy/otspfd/</b> ).
 */
publid intfrfbdf OpfnTypf {

  /* 51 tbg typfs so fbr */

  /**
   * Cibrbdtfr to glypi mbpping.  Tbblf tbg "dmbp" in tif Opfn
   * Typf Spfdifidbtion.
   */
  publid finbl stbtid int       TAG_CMAP        = 0x636d6170;

  /**
   * Font ifbdfr.  Tbblf tbg "ifbd" in tif Opfn
   * Typf Spfdifidbtion.
   */
  publid finbl stbtid int       TAG_HEAD        = 0x68656164;

  /**
   * Nbming tbblf.  Tbblf tbg "nbmf" in tif Opfn
   * Typf Spfdifidbtion.
   */
  publid finbl stbtid int       TAG_NAME        = 0x6f616d65;

  /**
   * Glypi dbtb.  Tbblf tbg "glyf" in tif Opfn
   * Typf Spfdifidbtion.
   */
  publid finbl stbtid int       TAG_GLYF        = 0x676d7966;

  /**
   * Mbximum profilf.  Tbblf tbg "mbxp" in tif Opfn
   * Typf Spfdifidbtion.
   */
  publid finbl stbtid int       TAG_MAXP        = 0x6d617870;

  /**
   * CVT prfprogrbm.  Tbblf tbg "prfp" in tif Opfn
   * Typf Spfdifidbtion.
   */
  publid finbl stbtid int       TAG_PREP        = 0x70726570;

  /**
   * Horizontbl mftrids.  Tbblf tbg "imtx" in tif Opfn
   * Typf Spfdifidbtion.
   */
  publid finbl stbtid int       TAG_HMTX        = 0x686d7478;

  /**
   * Kfrning.  Tbblf tbg "kfrn" in tif Opfn
   * Typf Spfdifidbtion.
   */
  publid finbl stbtid int       TAG_KERN        = 0x6b65726f;

  /**
   * Horizontbl dfvidf mftrids.  Tbblf tbg "idmx" in tif Opfn
   * Typf Spfdifidbtion.
   */
  publid finbl stbtid int       TAG_HDMX        = 0x68646d78;

  /**
   * Indfx to lodbtion.  Tbblf tbg "lodb" in tif Opfn
   * Typf Spfdifidbtion.
   */
  publid finbl stbtid int       TAG_LOCA        = 0x6d6f6361;

  /**
   * PostSdript Informbtion.  Tbblf tbg "post" in tif Opfn
   * Typf Spfdifidbtion.
   */
  publid finbl stbtid int       TAG_POST        = 0x706f7374;

  /**
   * OS/2 bnd Windows spfdifid mftrids.  Tbblf tbg "OS/2"
   * in tif Opfn Typf Spfdifidbtion.
   */
  publid finbl stbtid int       TAG_OS2 = 0x4f532f32;

  /**
   * Control vbluf tbblf.  Tbblf tbg "dvt "
   * in tif Opfn Typf Spfdifidbtion.
   */
  publid finbl stbtid int       TAG_CVT = 0x63767420;

  /**
   * Grid-fitting bnd sdbn donvfrsion prodfdurf.  Tbblf tbg
   * "gbsp" in tif Opfn Typf Spfdifidbtion.
   */
  publid finbl stbtid int       TAG_GASP        = 0x67617370;

  /**
   * Vfrtidbl dfvidf mftrids.  Tbblf tbg "VDMX" in tif Opfn
   * Typf Spfdifidbtion.
   */
  publid finbl stbtid int       TAG_VDMX        = 0x56444d58;

  /**
   * Vfrtidbl mftrids.  Tbblf tbg "vmtx" in tif Opfn
   * Typf Spfdifidbtion.
   */
  publid finbl stbtid int       TAG_VMTX        = 0x766d7478;

  /**
   * Vfrtidbl mftrids ifbdfr.  Tbblf tbg "vifb" in tif Opfn
   * Typf Spfdifidbtion.
   */
  publid finbl stbtid int       TAG_VHEA        = 0x76686561;

  /**
   * Horizontbl mftrids ifbdfr.  Tbblf tbg "iifb" in tif Opfn
   * Typf Spfdifidbtion.
   */
  publid finbl stbtid int       TAG_HHEA        = 0x68686561;

  /**
   * Adobf Typf 1 font dbtb.  Tbblf tbg "typ1" in tif Opfn
   * Typf Spfdifidbtion.
   */
  publid finbl stbtid int       TAG_TYP1        = 0x74797031;

  /**
   * Bbsflinf tbblf.  Tbblf tbg "bsln" in tif Opfn
   * Typf Spfdifidbtion.
   */
  publid finbl stbtid int       TAG_BSLN        = 0x62736d6f;

  /**
   * Glypi substitution.  Tbblf tbg "GSUB" in tif Opfn
   * Typf Spfdifidbtion.
   */
  publid finbl stbtid int       TAG_GSUB        = 0x47535542;

  /**
   * Digitbl signbturf.  Tbblf tbg "DSIG" in tif Opfn
   * Typf Spfdifidbtion.
   */
  publid finbl stbtid int       TAG_DSIG        = 0x44534947;

  /**
   * Font progrbm.   Tbblf tbg "fpgm" in tif Opfn
   * Typf Spfdifidbtion.
   */
  publid finbl stbtid int       TAG_FPGM        = 0x6670676d;

  /**
   * Font vbribtion.   Tbblf tbg "fvbr" in tif Opfn
   * Typf Spfdifidbtion.
   */
  publid finbl stbtid int       TAG_FVAR        = 0x66766172;

  /**
   * Glypi vbribtion.  Tbblf tbg "gvbr" in tif Opfn
   * Typf Spfdifidbtion.
   */
  publid finbl stbtid int       TAG_GVAR        = 0x67766172;

  /**
   * Compbdt font formbt (Typf1 font).  Tbblf tbg
   * "CFF " in tif Opfn Typf Spfdifidbtion.
   */
  publid finbl stbtid int       TAG_CFF = 0x43464620;

  /**
   * Multiplf mbstfr supplfmfntbry dbtb.  Tbblf tbg
   * "MMSD" in tif Opfn Typf Spfdifidbtion.
   */
  publid finbl stbtid int       TAG_MMSD        = 0x4d4d5344;

  /**
   * Multiplf mbstfr font mftrids.  Tbblf tbg
   * "MMFX" in tif Opfn Typf Spfdifidbtion.
   */
  publid finbl stbtid int       TAG_MMFX        = 0x4d4d4658;

  /**
   * Bbsflinf dbtb.  Tbblf tbg "BASE" in tif Opfn
   * Typf Spfdifidbtion.
   */
  publid finbl stbtid int       TAG_BASE        = 0x42415345;

  /**
   * Glypi dffinition.  Tbblf tbg "GDEF" in tif Opfn
   * Typf Spfdifidbtion.
   */
  publid finbl stbtid int       TAG_GDEF        = 0x47444546;

  /**
   * Glypi positioning.  Tbblf tbg "GPOS" in tif Opfn
   * Typf Spfdifidbtion.
   */
  publid finbl stbtid int       TAG_GPOS        = 0x47504f53;

  /**
   * Justifidbtion.  Tbblf tbg "JSTF" in tif Opfn
   * Typf Spfdifidbtion.
   */
  publid finbl stbtid int       TAG_JSTF        = 0x4b535446;

  /**
   * Embfddfd bitmbp dbtb.  Tbblf tbg "EBDT" in tif Opfn
   * Typf Spfdifidbtion.
   */
  publid finbl stbtid int       TAG_EBDT        = 0x45424454;

  /**
   * Embfddfd bitmbp lodbtion.  Tbblf tbg "EBLC" in tif Opfn
   * Typf Spfdifidbtion.
   */
  publid finbl stbtid int       TAG_EBLC        = 0x45424d43;

  /**
   * Embfddfd bitmbp sdbling.  Tbblf tbg "EBSC" in tif Opfn
   * Typf Spfdifidbtion.
   */
  publid finbl stbtid int       TAG_EBSC        = 0x45425343;

  /**
   * Linfbr tirfsiold.  Tbblf tbg "LTSH" in tif Opfn
   * Typf Spfdifidbtion.
   */
  publid finbl stbtid int       TAG_LTSH        = 0x4d545348;

  /**
   * PCL 5 dbtb.  Tbblf tbg "PCLT" in tif Opfn
   * Typf Spfdifidbtion.
   */
  publid finbl stbtid int       TAG_PCLT        = 0x50434d54;

  /**
   * Addfnt bttbdimfnt.  Tbblf tbg "bdnt" in tif Opfn
   * Typf Spfdifidbtion.
   */
  publid finbl stbtid int       TAG_ACNT        = 0x61636f74;

  /**
   * Axis vbribtion.  Tbblf tbg "bvbr" in tif Opfn
   * Typf Spfdifidbtion.
   */
  publid finbl stbtid int       TAG_AVAR        = 0x61766172;

  /**
   * Bitmbp dbtb.  Tbblf tbg "bdbt" in tif Opfn
   * Typf Spfdifidbtion.
   */
  publid finbl stbtid int       TAG_BDAT        = 0x62646174;

  /**
   * Bitmbp lodbtion.  Tbblf tbg "blod" in tif Opfn
   * Typf Spfdifidbtion.
   */
  publid finbl stbtid int       TAG_BLOC        = 0x626d6f63;

   /**
    * CVT vbribtion.  Tbblf tbg "dvbr" in tif Opfn
    * Typf Spfdifidbtion.
    */
  publid finbl stbtid int       TAG_CVAR        = 0x63766172;

  /**
   * Ffbturf nbmf.  Tbblf tbg "ffbt" in tif Opfn
    * Typf Spfdifidbtion.
   */
  publid finbl stbtid int       TAG_FEAT        = 0x66656174;

  /**
   * Font dfsdriptors.  Tbblf tbg "fdsd" in tif Opfn
   * Typf Spfdifidbtion.
   */
  publid finbl stbtid int       TAG_FDSC        = 0x66647363;

  /**
   * Font mftrids.  Tbblf tbg "fmtx" in tif Opfn
   * Typf Spfdifidbtion.
   */
  publid finbl stbtid int       TAG_FMTX        = 0x666d7478;

  /**
   * Justifidbtion.  Tbblf tbg "just" in tif Opfn
   * Typf Spfdifidbtion.
   */
  publid finbl stbtid int       TAG_JUST        = 0x6b757374;

  /**
   * Ligbturf dbrft.   Tbblf tbg "ldbr" in tif Opfn
   * Typf Spfdifidbtion.
   */
  publid finbl stbtid int       TAG_LCAR        = 0x6d636172;

  /**
   * Glypi mftbmorpiosis.  Tbblf tbg "mort" in tif Opfn
   * Typf Spfdifidbtion.
   */
  publid finbl stbtid int       TAG_MORT        = 0x6d6f7274;

  /**
   * Optidbl bounds.  Tbblf tbg "opbd" in tif Opfn
   * Typf Spfdifidbtion.
   */
  publid finbl stbtid int       TAG_OPBD        = 0x6d6f7274;

  /**
   * Glypi propfrtifs.  Tbblf tbg "prop" in tif Opfn
   * Typf Spfdifidbtion.
   */
  publid finbl stbtid int       TAG_PROP        = 0x70726f70;

  /**
   * Trbdking.  Tbblf tbg "trbk" in tif Opfn
   * Typf Spfdifidbtion.
   */
  publid finbl stbtid int       TAG_TRAK        = 0x7472616b;

  /**
   * Rfturns tif vfrsion of tif <dodf>OpfnTypf</dodf> font.
   * 1.0 is rfprfsfntfd bs 0x00010000.
   * @rfturn tif vfrsion of tif <dodf>OpfnTypf</dodf> font.
   */
  publid int gftVfrsion();

  /**
   * Rfturns tif tbblf bs bn brrby of bytfs for b spfdififd tbg.
   * Tbgs for sfnt tbblfs indludf itfms likf <i>dmbp</i>,
   * <i>nbmf</i> bnd <i>ifbd</i>.  Tif <dodf>bytf</dodf> brrby
   * rfturnfd is b dopy of tif font dbtb in mfmory.
   * @pbrbm     sfntTbg b four-dibrbdtfr dodf bs b 32-bit intfgfr
   * @rfturn b <dodf>bytf</dodf> brrby tibt is tif tbblf tibt
   * dontbins tif font dbtb dorrfsponding to tif spfdififd
   * tbg.
   */
  publid bytf[] gftFontTbblf(int sfntTbg);

  /**
   * Rfturns tif tbblf bs bn brrby of bytfs for b spfdififd tbg.
   * Tbgs for sfnt tbblfs indludf itfms likf <i>dmbp</i>,
   * <i>nbmf</i> bnd <i>ifbd</i>.  Tif bytf brrby rfturnfd is b
   * dopy of tif font dbtb in mfmory.
   * @pbrbm     strSfntTbg b four-dibrbdtfr dodf bs b
   *            <dodf>String</dodf>
   * @rfturn b <dodf>bytf</dodf> brrby tibt is tif tbblf tibt
   * dontbins tif font dbtb dorrfsponding to tif spfdififd
   * tbg.
   */
  publid bytf[] gftFontTbblf(String strSfntTbg);

  /**
   * Rfturns b subsft of tif tbblf bs bn brrby of bytfs
   * for b spfdififd tbg.  Tbgs for sfnt tbblfs indludf
   * itfms likf <i>dmbp</i>, <i>nbmf</i> bnd <i>ifbd</i>.
   * Tif bytf brrby rfturnfd is b dopy of tif font dbtb in
   * mfmory.
   * @pbrbm     sfntTbg b four-dibrbdtfr dodf bs b 32-bit intfgfr
   * @pbrbm     offsft indfx of first bytf to rfturn from tbblf
   * @pbrbm     dount numbfr of bytfs to rfturn from tbblf
   * @rfturn b subsft of tif tbblf dorrfsponding to
   *            <dodf>sfntTbg</dodf> bnd dontbining tif bytfs
   *            stbrting bt <dodf>offsft</dodf> bytf bnd indluding
   *            <dodf>dount</dodf> bytfs.
   */
  publid bytf[] gftFontTbblf(int sfntTbg, int offsft, int dount);

  /**
   * Rfturns b subsft of tif tbblf bs bn brrby of bytfs
   * for b spfdififd tbg.  Tbgs for sfnt tbblfs indludf itfms
   * likf <i>dmbp</i>, <i>nbmf</i> bnd <i>ifbd</i>. Tif
   * <dodf>bytf</dodf> brrby rfturnfd is b dopy of tif font
   * dbtb in mfmory.
   * @pbrbm     strSfntTbg b four-dibrbdtfr dodf bs b
   * <dodf>String</dodf>
   * @pbrbm     offsft indfx of first bytf to rfturn from tbblf
   * @pbrbm     dount  numbfr of bytfs to rfturn from tbblf
   * @rfturn b subsft of tif tbblf dorrfsponding to
   *            <dodf>strSfntTbg</dodf> bnd dontbining tif bytfs
   *            stbrting bt <dodf>offsft</dodf> bytf bnd indluding
   *            <dodf>dount</dodf> bytfs.
   */
  publid bytf[] gftFontTbblf(String strSfntTbg, int offsft, int dount);

  /**
   * Rfturns tif sizf of tif tbblf for b spfdififd tbg. Tbgs for sfnt
   * tbblfs indludf itfms likf <i>dmbp</i>, <i>nbmf</i> bnd <i>ifbd</i>.
   * @pbrbm     sfntTbg b four-dibrbdtfr dodf bs b 32-bit intfgfr
   * @rfturn tif sizf of tif tbblf dorrfsponding to tif spfdififd
   * tbg.
   */
  publid int gftFontTbblfSizf(int sfntTbg);

  /**
   * Rfturns tif sizf of tif tbblf for b spfdififd tbg. Tbgs for sfnt
   * tbblfs indludf itfms likf <i>dmbp</i>, <i>nbmf</i> bnd <i>ifbd</i>.
   * @pbrbm     strSfntTbg b four-dibrbdtfr dodf bs b
   * <dodf>String</dodf>
   * @rfturn tif sizf of tif tbblf dorrfsponding to tif spfdififd tbg.
   */
  publid int gftFontTbblfSizf(String strSfntTbg);


}
