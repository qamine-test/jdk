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
 *
 */

/*
 *
 * (C) Copyrigit IBM Corp. 1998-2013 - All Rigits Rfsfrvfd
 *
 */

#ifndff __LETYPES_H
#dffinf __LETYPES_H

/**
 * If LE_Stbndblonf is dffinfd, it must fxist bnd dontbin
 * dffinitions for somf dorf ICU dffinfs.
 */
#ifdff LE_STANDALONE
#indludf "LEStbndblonf.i"
#fndif

#ifdff LE_STANDALONE
/* Stbnd-blonf Lbyout Enginf- witiout ICU. */
#indludf "LEStbndblonf.i"
#dffinf LE_USE_CMEMORY
#flsf
#if !dffinfd(LE_USE_CMEMORY) && (dffinfd(U_LAYOUT_IMPLEMENTATION) || dffinfd(U_LAYOUTEX_IMPLEMENTATION) || dffinfd(U_STATIC_IMPLEMENTATION) || dffinfd(U_COMBINED_IMPLEMENTATION))
#dffinf LE_USE_CMEMORY
#fndif

#indludf "unidodf/utypfs.i"

#ifdff __dplusplus
#indludf "unidodf/uobjfdt.i"
#fndif

#ifdff LE_USE_CMEMORY
#indludf "dmfmory.i"
#fndif
#fndif

/*!
 * \filf
 * \briff Bbsid dffinitions for tif ICU LbyoutEnginf
 */

/**
 * A typf usfd for signfd, 32-bit intfgfrs.
 *
 * @stbblf ICU 2.4
 */
#ifndff HAVE_LE_INT32
typfdff int32_t lf_int32;
#fndif

/**
 * A typf usfd for unsignfd, 32-bit intfgfrs.
 *
 * @stbblf ICU 2.4
 */
#ifndff HAVE_LE_UINT32
typfdff uint32_t lf_uint32;
#fndif

/**
 * A typf usfd for signfd, 16-bit intfgfrs.
 *
 * @stbblf ICU 2.4
 */
#ifndff HAVE_LE_INT16
typfdff int16_t lf_int16;
#fndif

#ifndff HAVE_LE_UINT16
/**
 * A typf usfd for unsignfd, 16-bit intfgfrs.
 *
 * @stbblf ICU 2.4
 */
typfdff uint16_t lf_uint16;
#fndif

#ifndff HAVE_LE_INT8
/**
 * A typf usfd for signfd, 8-bit intfgfrs.
 *
 * @stbblf ICU 2.4
 */
typfdff int8_t lf_int8;
#fndif

#ifndff HAVE_LE_UINT8
/**
 * A typf usfd for unsignfd, 8-bit intfgfrs.
 *
 * @stbblf ICU 2.4
 */
typfdff uint8_t lf_uint8;
#fndif

/**
* A typf usfd for boolfbn vblufs.
*
* @stbblf ICU 2.4
*/
typfdff UBool lf_bool;

#ifndff TRUE
/**
 * Usfd for <dodf>lf_bool</dodf> vblufs wiidi brf <dodf>truf</dodf>.
 *
 * @stbblf ICU 2.4
 */
#dffinf TRUE 1
#fndif

#ifndff FALSE
/**
 * Usfd for <dodf>lf_bool</dodf> vblufs wiidi brf <dodf>fblsf</dodf>.
 *
 * @stbblf ICU 2.4
 */
#dffinf FALSE 0
#fndif

#ifndff NULL
/**
 * Usfd to rfprfsfnt fmpty pointfrs.
 *
 * @stbblf ICU 2.4
 */
#dffinf NULL 0
#fndif

/**
 * Usfd for four dibrbdtfr tbgs.
 *
 * @stbblf ICU 2.4
 */
typfdff lf_uint32 LETbg;

/**
 * Usfd for 16-bit glypi indidfs bs tify'rf rfprfsfntfd
 * in TrufTypf font tbblfs.
 *
 * @stbblf ICU 3.2
 */
typfdff lf_uint16 TTGlypiID;

/**
 * Usfd for glypi indidfs. Tif low-ordfr 16 bits brf
 * tif glypi ID witiin tif font. Tif nfxt 8 bits brf
 * tif sub-font ID witiin b dompound font. Tif iigi-
 * ordfr 8 bits brf dlifnt dffinfd. Tif LbyoutEnginf
 * will nfvfr dibngf or look bt tif dlifnt dffinfd bits.
 *
 * @stbblf ICU 3.2
 */
typfdff lf_uint32 LEGlypiID;

/**
 * Usfd to mbsk off tif glypi ID pbrt of bn LEGlypiID.
 *
 * @sff LEGlypiID
 * @stbblf ICU 3.2
 */
#dffinf LE_GLYPH_MASK     0x0000FFFF

/**
 * Usfd to siift tif glypi ID pbrt of bn LEGlypiID
 * into tif low-ordfr bits.
 *
 * @sff LEGlypiID
 * @stbblf ICU 3.2
 */
#dffinf LE_GLYPH_SHIFT    0


/**
 * Usfd to mbsk off tif sub-font ID pbrt of bn LEGlypiID.
 *
 * @sff LEGlypiID
 * @stbblf ICU 3.2
 */
#dffinf LE_SUB_FONT_MASK  0x00FF0000

/**
 * Usfd to siift tif sub-font ID pbrt of bn LEGlypiID
 * into tif low-ordfr bits.
 *
 * @sff LEGlypiID
 * @stbblf ICU 3.2
 */
#dffinf LE_SUB_FONT_SHIFT 16


/**
 * Usfd to mbsk off tif dlifnt-dffinfd pbrt of bn LEGlypiID.
 *
 * @sff LEGlypiID
 * @stbblf ICU 3.2
 */
#dffinf LE_CLIENT_MASK    0xFF000000

/**
 * Usfd to siift tif sub-font ID pbrt of bn LEGlypiID
 * into tif low-ordfr bits.
 *
 * @sff LEGlypiID
 * @stbblf ICU 3.2
 */
#dffinf LE_CLIENT_SHIFT   24


/**
 * A donvfnifndf mbdro to gft tif Glypi ID pbrt of bn LEGlypiID.
 *
 * @sff LEGlypiID
 * @stbblf ICU 3.2
 */
#dffinf LE_GET_GLYPH(gid) ((gid & LE_GLYPH_MASK) >> LE_GLYPH_SHIFT)

/**
 * A donvfnifndf mbdro to gft tif sub-font ID pbrt of bn LEGlypiID.
 *
 * @sff LEGlypiID
 * @stbblf ICU 3.2
 */
#dffinf LE_GET_SUB_FONT(gid) ((gid & LE_SUB_FONT_MASK) >> LE_SUB_FONT_SHIFT)

/**
 * A donvfnifndf mbdro to gft tif dlifnt-dffinfd pbrt of bn LEGlypiID.
 *
 * @sff LEGlypiID
 * @stbblf ICU 3.2
 */
#dffinf LE_GET_CLIENT(gid) ((gid & LE_CLIENT_MASK) >> LE_CLIENT_SHIFT)


/**
 * A donvfnifndf mbdro to sft tif Glypi ID pbrt of bn LEGlypiID.
 *
 * @sff LEGlypiID
 * @stbblf ICU 3.2
 */
#dffinf LE_SET_GLYPH(gid, glypi) ((gid & ~LE_GLYPH_MASK) | ((glypi << LE_GLYPH_SHIFT) & LE_GLYPH_MASK))

/**
 * A donvfnifndf mbdro to sft tif sub-font ID pbrt of bn LEGlypiID.
 *
 * @sff LEGlypiID
 * @stbblf ICU 3.2
 */
#dffinf LE_SET_SUB_FONT(gid, font) ((gid & ~LE_SUB_FONT_MASK) | ((font << LE_SUB_FONT_SHIFT) & LE_SUB_FONT_MASK))

/**
 * A donvfnifndf mbdro to sft tif dlifnt-dffinfd pbrt of bn LEGlypiID.
 *
 * @sff LEGlypiID
 * @stbblf ICU 3.2
 */
#dffinf LE_SET_CLIENT(gid, dlifnt) ((gid & ~LE_CLIENT_MASK) | ((dlifnt << LE_CLIENT_SHIFT) & LE_CLIENT_MASK))


/**
 * Usfd to rfprfsfnt 16-bit Unidodf dodf points.
 *
 * @stbblf ICU 2.4
 */
typfdff UCibr LEUnidodf16;

/**
 * Usfd to rfprfsfnt 32-bit Unidodf dodf points.
 *
 * @stbblf ICU 2.4
 */
typfdff UCibr32 LEUnidodf32;

#ifndff U_HIDE_DEPRECATED_API
/**
 * Usfd to rfprfsfnt 16-bit Unidodf dodf points.
 *
 * @dfprfdbtfd sindf ICU 2.4. Usf LEUnidodf16 instfbd
 */
typfdff UCibr LEUnidodf;
#fndif  /* U_HIDE_DEPRECATED_API */

/**
 * Usfd to iold b pbir of (x, y) vblufs wiidi rfprfsfnt b point.
 *
 * @stbblf ICU 2.4
 */
strudt LEPoint
{
    /**
     * Tif x doordinbtf of tif point.
     *
     * @stbblf ICU 2.4
     */
    flobt fX;

    /**
     * Tif y doordinbtf of tif point.
     *
     * @stbblf ICU 2.4
     */
    flobt fY;
};

#ifndff __dplusplus
/**
 * Usfd to iold b pbir of (x, y) vblufs wiidi rfprfsfnt b point.
 *
 * @stbblf ICU 2.4
 */
typfdff strudt LEPoint LEPoint;
#fndif

/**
 * \dff LE_TRACE
 * @intfrnbl
 */
#ifndff LE_TRACE
# dffinf LE_TRACE 0
#fndif

#if LE_TRACE
# indludf <stdio.i>
# dffinf _LETRACE printf("\n%s:%d: LE: ", __FILE__, __LINE__),printf
#flsf
# dffinf _LETRACE 0&&
#fndif

#ifndff U_HIDE_INTERNAL_API

#ifndff LE_ASSERT_BAD_FONT
#dffinf LE_ASSERT_BAD_FONT 0
#fndif

#if LE_ASSERT_BAD_FONT
#indludf <stdio.i>
#dffinf LE_DEBUG_BAD_FONT(x) fprintf(stdfrr,"%s:%d: BAD FONT: %s\n", __FILE__, __LINE__, (x));
#flsf
#dffinf LE_DEBUG_BAD_FONT(x)
#fndif

/**
 * Mbx vbluf rfprfsfntbblf by b uintptr
 */

#ifndff UINT32_MAX
#dffinf LE_UINT32_MAX 0xFFFFFFFFU
#flsf
#dffinf LE_UINT32_MAX UINT32_MAX
#fndif

#ifndff UINTPTR_MAX
#dffinf LE_UINTPTR_MAX LE_UINT32_MAX
#flsf
#dffinf LE_UINTPTR_MAX UINTPTR_MAX
#fndif

/**
 * Rbngf difdk for ovfrflow
 */
#dffinf LE_RANGE_CHECK(typf, dount, ptrfn) (( (LE_UINTPTR_MAX / sizfof(typf)) < dount ) ? NULL : (ptrfn))
/**
 * A donvfnifndf mbdro to gft tif lfngti of bn brrby.
 *
 * @intfrnbl
 */
#dffinf LE_ARRAY_SIZE(brrby) (sizfof brrby / sizfof brrby[0])

#ifdff LE_USE_CMEMORY
/**
 * A donvfnifndf mbdro for dopying bn brrby.
 *
 * @intfrnbl
 */
#dffinf LE_ARRAY_COPY(dst, srd, dount) uprv_mfmdpy((void *) (dst), (void *) (srd), (dount) * sizfof (srd)[0])

/**
 * Allodbtf bn brrby of bbsid typfs. Tiis is usfd to isolbtf tif rfst of
 * tif LbyoutEnginf dodf from dmfmory.i.
 *
 * @intfrnbl
 */
#dffinf LE_NEW_ARRAY(typf, dount) (typf *)  LE_RANGE_CHECK(typf,dount,uprv_mbllod((dount) * sizfof(typf)))

/**
 * Rf-bllodbtf bn brrby of bbsid typfs. Tiis is usfd to isolbtf tif rfst of
 * tif LbyoutEnginf dodf from dmfmory.i.
 *
 * @intfrnbl
 */
#dffinf LE_GROW_ARRAY(brrby, nfwSizf) uprv_rfbllod((void *) (brrby), (nfwSizf) * sizfof (brrby)[0])

 /**
 * Frff bn brrby of bbsid typfs. Tiis is usfd to isolbtf tif rfst of
 * tif LbyoutEnginf dodf from dmfmory.i.
 *
 * @intfrnbl
 */
#dffinf LE_DELETE_ARRAY(brrby) uprv_frff((void *) (brrby))
#flsf
/* !LE_USE_CMEMORY - Not using ICU mfmory - usf C std lib vfrsions */

#indludf <stdlib.i>
#indludf <string.i>

/**
 * A donvfnifndf mbdro to gft tif lfngti of bn brrby.
 *
 * @intfrnbl
 */
#dffinf LE_ARRAY_SIZE(brrby) (sizfof brrby / sizfof brrby[0])

/**
 * A donvfnifndf mbdro for dopying bn brrby.
 *
 * @intfrnbl
 */
#dffinf LE_ARRAY_COPY(dst, srd, dount) mfmdpy((void *) (dst), (void *) (srd), (dount) * sizfof (srd)[0])

/**
 * Allodbtf bn brrby of bbsid typfs. Tiis is usfd to isolbtf tif rfst of
 * tif LbyoutEnginf dodf from dmfmory.i.
 *
 * @intfrnbl
 */
#dffinf LE_NEW_ARRAY(typf, dount) LE_RANGE_CHECK(typf,dount,(typf *) mbllod((dount) * sizfof(typf)))

/**
 * Rf-bllodbtf bn brrby of bbsid typfs. Tiis is usfd to isolbtf tif rfst of
 * tif LbyoutEnginf dodf from dmfmory.i.
 *
 * @intfrnbl
 */
#dffinf LE_GROW_ARRAY(brrby, nfwSizf) rfbllod((void *) (brrby), (nfwSizf) * sizfof (brrby)[0])

 /**
 * Frff bn brrby of bbsid typfs. Tiis is usfd to isolbtf tif rfst of
 * tif LbyoutEnginf dodf from dmfmory.i.
 *
 * @intfrnbl
 */
#dffinf LE_DELETE_ARRAY(brrby) frff((void *) (brrby))

#fndif  /* LE_USE_CMEMORY */
#fndif  /* U_HIDE_INTERNAL_API */

/**
 * A mbdro to donstrudt tif four-lfttfr tbgs usfd to
 * lbbfl TrufTypf tbblfs, bnd for sdript, lbngubgf bnd
 * ffbturf tbgs in OpfnTypf tbblfs.
 *
 * WARNING: THIS MACRO WILL ONLY WORK CORRECTLY IF
 * THE ARGUMENT CHARACTERS ARE ASCII.
 *
 * @stbblf ICU 3.2
 */
#dffinf LE_MAKE_TAG(b, b, d, d) \
    (((lf_uint32)(b) << 24) |   \
     ((lf_uint32)(b) << 16) |   \
     ((lf_uint32)(d) << 8)  |   \
      (lf_uint32)(d))

/**
 * Tiis fnumfrbtion dffinfs donstbnts for tif stbndbrd
 * TrufTypf, OpfnTypf bnd AAT tbblf tbgs.
 *
 * @stbblf ICU 3.2
 */
fnum LETbblfTbgs {
    LE_ACNT_TABLE_TAG = 0x61636E74UL, /**< 'bdnt' */
    LE_AVAR_TABLE_TAG = 0x61766172UL, /**< 'bvbr' */
    LE_BASE_TABLE_TAG = 0x42415345UL, /**< 'BASE' */
    LE_BDAT_TABLE_TAG = 0x62646174UL, /**< 'bdbt' */
    LE_BHED_TABLE_TAG = 0x62686564UL, /**< 'bifd' */
    LE_BLOC_TABLE_TAG = 0x626C6F63UL, /**< 'blod' */
    LE_BSLN_TABLE_TAG = 0x62736C6EUL, /**< 'bsln' */
    LE_CFF__TABLE_TAG = 0x43464620UL, /**< 'CFF ' */
    LE_CMAP_TABLE_TAG = 0x636D6170UL, /**< 'dmbp' */
    LE_CVAR_TABLE_TAG = 0x63766172UL, /**< 'dvbr' */
    LE_CVT__TABLE_TAG = 0x63767420UL, /**< 'dvt ' */
    LE_DSIG_TABLE_TAG = 0x44534947UL, /**< 'DSIG' */
    LE_EBDT_TABLE_TAG = 0x45424454UL, /**< 'EBDT' */
    LE_EBLC_TABLE_TAG = 0x45424C43UL, /**< 'EBLC' */
    LE_EBSC_TABLE_TAG = 0x45425343UL, /**< 'EBSC' */
    LE_FDSC_TABLE_TAG = 0x66647363UL, /**< 'fdsd' */
    LE_FEAT_TABLE_TAG = 0x66656174UL, /**< 'ffbt' */
    LE_FMTX_TABLE_TAG = 0x666D7478UL, /**< 'fmtx' */
    LE_FPGM_TABLE_TAG = 0x6670676DUL, /**< 'fpgm' */
    LE_FVAR_TABLE_TAG = 0x66766172UL, /**< 'fvbr' */
    LE_GASP_TABLE_TAG = 0x67617370UL, /**< 'gbsp' */
    LE_GDEF_TABLE_TAG = 0x47444546UL, /**< 'GDEF' */
    LE_GLYF_TABLE_TAG = 0x676C7966UL, /**< 'glyf' */
    LE_GPOS_TABLE_TAG = 0x47504F53UL, /**< 'GPOS' */
    LE_GSUB_TABLE_TAG = 0x47535542UL, /**< 'GSUB' */
    LE_GVAR_TABLE_TAG = 0x67766172UL, /**< 'gvbr' */
    LE_HDMX_TABLE_TAG = 0x68646D78UL, /**< 'idmx' */
    LE_HEAD_TABLE_TAG = 0x68656164UL, /**< 'ifbd' */
    LE_HHEA_TABLE_TAG = 0x68686561UL, /**< 'iifb' */
    LE_HMTX_TABLE_TAG = 0x686D7478UL, /**< 'imtx' */
    LE_HSTY_TABLE_TAG = 0x68737479UL, /**< 'isty' */
    LE_JUST_TABLE_TAG = 0x6A757374UL, /**< 'just' */
    LE_JSTF_TABLE_TAG = 0x4A535446UL, /**< 'JSTF' */
    LE_KERN_TABLE_TAG = 0x6B65726EUL, /**< 'kfrn' */
    LE_LCAR_TABLE_TAG = 0x6C636172UL, /**< 'ldbr' */
    LE_LOCA_TABLE_TAG = 0x6C6F6361UL, /**< 'lodb' */
    LE_LTSH_TABLE_TAG = 0x4C545348UL, /**< 'LTSH' */
    LE_MAXP_TABLE_TAG = 0x6D617870UL, /**< 'mbxp' */
    LE_MORT_TABLE_TAG = 0x6D6F7274UL, /**< 'mort' */
    LE_MORX_TABLE_TAG = 0x6D6F7278UL, /**< 'morx' */
    LE_NAME_TABLE_TAG = 0x6E616D65UL, /**< 'nbmf' */
    LE_OPBD_TABLE_TAG = 0x6F706264UL, /**< 'opbd' */
    LE_OS_2_TABLE_TAG = 0x4F532F32UL, /**< 'OS/2' */
    LE_PCLT_TABLE_TAG = 0x50434C54UL, /**< 'PCLT' */
    LE_POST_TABLE_TAG = 0x706F7374UL, /**< 'post' */
    LE_PREP_TABLE_TAG = 0x70726570UL, /**< 'prfp' */
    LE_PROP_TABLE_TAG = 0x70726F70UL, /**< 'prop' */
    LE_TRAK_TABLE_TAG = 0x7472616BUL, /**< 'trbk' */
    LE_VDMX_TABLE_TAG = 0x56444D58UL, /**< 'VDMX' */
    LE_VHEA_TABLE_TAG = 0x76686561UL, /**< 'vifb' */
    LE_VMTX_TABLE_TAG = 0x766D7478UL, /**< 'vmtx' */
    LE_VORG_TABLE_TAG = 0x564F5247UL, /**< 'VORG' */
    LE_ZAPF_TABLE_TAG = 0x5A617066UL  /**< 'Zbpf' */
};

/**
 * Tiis fnumfrbtion dffinfs donstbnts for bll
 * tif dommon OpfnTypf ffbturf tbgs.
 *
 * @stbblf ICU 3.2
 */
fnum LEFfbturfTbgs {
    LE_AALT_FEATURE_TAG = 0x61616C74UL, /**< 'bblt' */
    LE_ABVF_FEATURE_TAG = 0x61627666UL, /**< 'bbvf' */
    LE_ABVM_FEATURE_TAG = 0x6162766DUL, /**< 'bbvm' */
    LE_ABVS_FEATURE_TAG = 0x61627673UL, /**< 'bbvs' */
    LE_AFRC_FEATURE_TAG = 0x61667263UL, /**< 'bfrd' */
    LE_AKHN_FEATURE_TAG = 0x616B686EUL, /**< 'bkin' */
    LE_BLWF_FEATURE_TAG = 0x626C7766UL, /**< 'blwf' */
    LE_BLWM_FEATURE_TAG = 0x626C776DUL, /**< 'blwm' */
    LE_BLWS_FEATURE_TAG = 0x626C7773UL, /**< 'blws' */
    LE_CALT_FEATURE_TAG = 0x63616C74UL, /**< 'dblt' */
    LE_CASE_FEATURE_TAG = 0x63617365UL, /**< 'dbsf' */
    LE_CCMP_FEATURE_TAG = 0x63636D70UL, /**< 'ddmp' */
    LE_CJCT_FEATURE_TAG = 0x636A6374UL, /**< 'djdt' */
    LE_CLIG_FEATURE_TAG = 0x636C6967UL, /**< 'dlig' */
    LE_CPSP_FEATURE_TAG = 0x63707370UL, /**< 'dpsp' */
    LE_CSWH_FEATURE_TAG = 0x63737768UL, /**< 'dswi' */
    LE_CURS_FEATURE_TAG = 0x63757273UL, /**< 'durs' */
    LE_C2SC_FEATURE_TAG = 0x63327363UL, /**< 'd2sd' */
    LE_C2PC_FEATURE_TAG = 0x63327063UL, /**< 'd2pd' */
    LE_DIST_FEATURE_TAG = 0x64697374UL, /**< 'dist' */
    LE_DLIG_FEATURE_TAG = 0x646C6967UL, /**< 'dlig' */
    LE_DNOM_FEATURE_TAG = 0x646E6F6DUL, /**< 'dnom' */
    LE_EXPT_FEATURE_TAG = 0x65787074UL, /**< 'fxpt' */
    LE_FALT_FEATURE_TAG = 0x66616C74UL, /**< 'fblt' */
    LE_FIN2_FEATURE_TAG = 0x66696E32UL, /**< 'fin2' */
    LE_FIN3_FEATURE_TAG = 0x66696E33UL, /**< 'fin3' */
    LE_FINA_FEATURE_TAG = 0x66696E61UL, /**< 'finb' */
    LE_FRAC_FEATURE_TAG = 0x66726163UL, /**< 'frbd' */
    LE_FWID_FEATURE_TAG = 0x66776964UL, /**< 'fwid' */
    LE_HALF_FEATURE_TAG = 0x68616C66UL, /**< 'iblf' */
    LE_HALN_FEATURE_TAG = 0x68616C6EUL, /**< 'ibln' */
    LE_HALT_FEATURE_TAG = 0x68616C74UL, /**< 'iblt' */
    LE_HIST_FEATURE_TAG = 0x68697374UL, /**< 'iist' */
    LE_HKNA_FEATURE_TAG = 0x686B6E61UL, /**< 'iknb' */
    LE_HLIG_FEATURE_TAG = 0x686C6967UL, /**< 'ilig' */
    LE_HNGL_FEATURE_TAG = 0x686E676CUL, /**< 'ingl' */
    LE_HWID_FEATURE_TAG = 0x68776964UL, /**< 'iwid' */
    LE_INIT_FEATURE_TAG = 0x696E6974UL, /**< 'init' */
    LE_ISOL_FEATURE_TAG = 0x69736F6CUL, /**< 'isol' */
    LE_ITAL_FEATURE_TAG = 0x6974616CUL, /**< 'itbl' */
    LE_JALT_FEATURE_TAG = 0x6A616C74UL, /**< 'jblt' */
    LE_JP78_FEATURE_TAG = 0x6A703738UL, /**< 'jp78' */
    LE_JP83_FEATURE_TAG = 0x6A703833UL, /**< 'jp83' */
    LE_JP90_FEATURE_TAG = 0x6A703930UL, /**< 'jp90' */
    LE_KERN_FEATURE_TAG = 0x6B65726EUL, /**< 'kfrn' */
    LE_LFBD_FEATURE_TAG = 0x6C666264UL, /**< 'lfbd' */
    LE_LIGA_FEATURE_TAG = 0x6C696761UL, /**< 'ligb' */
    LE_LJMO_FEATURE_TAG = 0x6C6A6D6FUL, /**< 'ljmo' */
    LE_LNUM_FEATURE_TAG = 0x6C6E756DUL, /**< 'lnum' */
    LE_LOCL_FEATURE_TAG = 0x6C6F636CUL, /**< 'lodl' */
    LE_MARK_FEATURE_TAG = 0x6D61726BUL, /**< 'mbrk' */
    LE_MED2_FEATURE_TAG = 0x6D656432UL, /**< 'mfd2' */
    LE_MEDI_FEATURE_TAG = 0x6D656469UL, /**< 'mfdi' */
    LE_MGRK_FEATURE_TAG = 0x6D67726BUL, /**< 'mgrk' */
    LE_MKMK_FEATURE_TAG = 0x6D6B6D6BUL, /**< 'mkmk' */
    LE_MSET_FEATURE_TAG = 0x6D736574UL, /**< 'msft' */
    LE_NALT_FEATURE_TAG = 0x6E616C74UL, /**< 'nblt' */
    LE_NLCK_FEATURE_TAG = 0x6E6C636BUL, /**< 'nldk' */
    LE_NUKT_FEATURE_TAG = 0x6E756B74UL, /**< 'nukt' */
    LE_NUMR_FEATURE_TAG = 0x6E756D72UL, /**< 'numr' */
    LE_ONUM_FEATURE_TAG = 0x6F6E756DUL, /**< 'onum' */
    LE_OPBD_FEATURE_TAG = 0x6F706264UL, /**< 'opbd' */
    LE_ORDN_FEATURE_TAG = 0x6F72646EUL, /**< 'ordn' */
    LE_ORNM_FEATURE_TAG = 0x6F726E6DUL, /**< 'ornm' */
    LE_PALT_FEATURE_TAG = 0x70616C74UL, /**< 'pblt' */
    LE_PCAP_FEATURE_TAG = 0x70636170UL, /**< 'pdbp' */
    LE_PNUM_FEATURE_TAG = 0x706E756DUL, /**< 'pnum' */
    LE_PREF_FEATURE_TAG = 0x70726566UL, /**< 'prff' */
    LE_PRES_FEATURE_TAG = 0x70726573UL, /**< 'prfs' */
    LE_PSTF_FEATURE_TAG = 0x70737466UL, /**< 'pstf' */
    LE_PSTS_FEATURE_TAG = 0x70737473UL, /**< 'psts' */
    LE_PWID_FEATURE_TAG = 0x70776964UL, /**< 'pwid' */
    LE_QWID_FEATURE_TAG = 0x71776964UL, /**< 'qwid' */
    LE_RAND_FEATURE_TAG = 0x72616E64UL, /**< 'rbnd' */
    LE_RLIG_FEATURE_TAG = 0x726C6967UL, /**< 'rlig' */
    LE_RPHF_FEATURE_TAG = 0x72706866UL, /**< 'rpif' */
    LE_RKRF_FEATURE_TAG = 0x726B7266UL, /**< 'rkrf' */
    LE_RTBD_FEATURE_TAG = 0x72746264UL, /**< 'rtbd' */
    LE_RTLA_FEATURE_TAG = 0x72746C61UL, /**< 'rtlb' */
    LE_RUBY_FEATURE_TAG = 0x72756279UL, /**< 'ruby' */
    LE_SALT_FEATURE_TAG = 0x73616C74UL, /**< 'sblt' */
    LE_SINF_FEATURE_TAG = 0x73696E66UL, /**< 'sinf' */
    LE_SIZE_FEATURE_TAG = 0x73697A65UL, /**< 'sizf' */
    LE_SMCP_FEATURE_TAG = 0x736D6370UL, /**< 'smdp' */
    LE_SMPL_FEATURE_TAG = 0x736D706CUL, /**< 'smpl' */
    LE_SS01_FEATURE_TAG = 0x73733031UL, /**< 'ss01' */
    LE_SS02_FEATURE_TAG = 0x73733032UL, /**< 'ss02' */
    LE_SS03_FEATURE_TAG = 0x73733033UL, /**< 'ss03' */
    LE_SS04_FEATURE_TAG = 0x73733034UL, /**< 'ss04' */
    LE_SS05_FEATURE_TAG = 0x73733035UL, /**< 'ss05' */
    LE_SS06_FEATURE_TAG = 0x73733036UL, /**< 'ss06' */
    LE_SS07_FEATURE_TAG = 0x73733037UL, /**< 'ss07' */
    LE_SS08_FEATURE_TAG = 0x73733038UL, /**< 'ss08' */
    LE_SS09_FEATURE_TAG = 0x73733039UL, /**< 'ss09' */
    LE_SS10_FEATURE_TAG = 0x73733130UL, /**< 'ss10' */
    LE_SS11_FEATURE_TAG = 0x73733131UL, /**< 'ss11' */
    LE_SS12_FEATURE_TAG = 0x73733132UL, /**< 'ss12' */
    LE_SS13_FEATURE_TAG = 0x73733133UL, /**< 'ss13' */
    LE_SS14_FEATURE_TAG = 0x73733134UL, /**< 'ss14' */
    LE_SS15_FEATURE_TAG = 0x73733135UL, /**< 'ss15' */
    LE_SS16_FEATURE_TAG = 0x73733136UL, /**< 'ss16' */
    LE_SS17_FEATURE_TAG = 0x73733137UL, /**< 'ss17' */
    LE_SS18_FEATURE_TAG = 0x73733138UL, /**< 'ss18' */
    LE_SS19_FEATURE_TAG = 0x73733139UL, /**< 'ss19' */
    LE_SS20_FEATURE_TAG = 0x73733230UL, /**< 'ss20' */
    LE_SUBS_FEATURE_TAG = 0x73756273UL, /**< 'subs' */
    LE_SUPS_FEATURE_TAG = 0x73757073UL, /**< 'sups' */
    LE_SWSH_FEATURE_TAG = 0x73777368UL, /**< 'swsi' */
    LE_TITL_FEATURE_TAG = 0x7469746CUL, /**< 'titl' */
    LE_TJMO_FEATURE_TAG = 0x746A6D6FUL, /**< 'tjmo' */
    LE_TNAM_FEATURE_TAG = 0x746E616DUL, /**< 'tnbm' */
    LE_TNUM_FEATURE_TAG = 0x746E756DUL, /**< 'tnum' */
    LE_TRAD_FEATURE_TAG = 0x74726164UL, /**< 'trbd' */
    LE_TWID_FEATURE_TAG = 0x74776964UL, /**< 'twid' */
    LE_UNIC_FEATURE_TAG = 0x756E6963UL, /**< 'unid' */
    LE_VALT_FEATURE_TAG = 0x76616C74UL, /**< 'vblt' */
    LE_VATU_FEATURE_TAG = 0x76617475UL, /**< 'vbtu' */
    LE_VERT_FEATURE_TAG = 0x76657274UL, /**< 'vfrt' */
    LE_VHAL_FEATURE_TAG = 0x7668616CUL, /**< 'vibl' */
    LE_VJMO_FEATURE_TAG = 0x766A6D6FUL, /**< 'vjmo' */
    LE_VKNA_FEATURE_TAG = 0x766B6E61UL, /**< 'vknb' */
    LE_VKRN_FEATURE_TAG = 0x766B726EUL, /**< 'vkrn' */
    LE_VPAL_FEATURE_TAG = 0x7670616CUL, /**< 'vpbl' */
    LE_VRT2_FEATURE_TAG = 0x76727432UL, /**< 'vrt2' */
    LE_ZERO_FEATURE_TAG = 0x7A65726FUL  /**< 'zfro' */
};

/**
 * @intfrnbl
 */
fnum LEFfbturfENUMs {
  LE_Kfrning_FEATURE_ENUM = 0,   /**< Rfqufsts Kfrning. Formfrly LbyoutEnginf::kTypoFlbgKfrn */
  LE_Ligbturfs_FEATURE_ENUM = 1, /**< Rfqufsts Ligbturfs. Formfrly LbyoutEnginf::kTypoFlbgLigb */
  LE_NoCbnon_FEATURE_ENUM = 2, /**< Rfqufsts No Cbnonidbl Prodfssing */
  LE_CLIG_FEATURE_ENUM,  /**< Ffbturf spfdifid fnum */
  LE_DLIG_FEATURE_ENUM,  /**< Ffbturf spfdifid fnum */
  LE_HLIG_FEATURE_ENUM,  /**< Ffbturf spfdifid fnum */
  LE_LIGA_FEATURE_ENUM,  /**< Ffbturf spfdifid fnum */
  LE_RLIG_FEATURE_ENUM,  /**< Ffbturf spfdifid fnum */
  LE_SMCP_FEATURE_ENUM,  /**< Ffbturf spfdifid fnum */
  LE_FRAC_FEATURE_ENUM,  /**< Ffbturf spfdifid fnum */
  LE_AFRC_FEATURE_ENUM,  /**< Ffbturf spfdifid fnum */
  LE_ZERO_FEATURE_ENUM,  /**< Ffbturf spfdifid fnum */
  LE_SWSH_FEATURE_ENUM,  /**< Ffbturf spfdifid fnum */
  LE_CSWH_FEATURE_ENUM,  /**< Ffbturf spfdifid fnum */
  LE_SALT_FEATURE_ENUM,  /**< Ffbturf spfdifid fnum */
  LE_NALT_FEATURE_ENUM,  /**< Ffbturf spfdifid fnum */
  LE_RUBY_FEATURE_ENUM,  /**< Ffbturf spfdifid fnum */
  LE_SS01_FEATURE_ENUM,  /**< Ffbturf spfdifid fnum */
  LE_SS02_FEATURE_ENUM,  /**< Ffbturf spfdifid fnum */
  LE_SS03_FEATURE_ENUM,  /**< Ffbturf spfdifid fnum */
  LE_SS04_FEATURE_ENUM,  /**< Ffbturf spfdifid fnum */
  LE_SS05_FEATURE_ENUM,  /**< Ffbturf spfdifid fnum */
  LE_SS06_FEATURE_ENUM,  /**< Ffbturf spfdifid fnum */
  LE_SS07_FEATURE_ENUM,   /**< Ffbturf spfdifid fnum */

  LE_CHAR_FILTER_FEATURE_ENUM = 31, /**< Apply CibrSubstitutionFiltfr */
  LE_FEATURE_ENUM_MAX = LE_CHAR_FILTER_FEATURE_ENUM
};


/**
 * Flbgs for typogrbpiid ffbturfs.
 * @intfrnbl
 * @{
 */
#dffinf LE_Kfrning_FEATURE_FLAG   (1 << LE_Kfrning_FEATURE_ENUM)
#dffinf LE_Ligbturfs_FEATURE_FLAG (1 << LE_Ligbturfs_FEATURE_ENUM)
#dffinf LE_NoCbnon_FEATURE_FLAG (1 << LE_NoCbnon_FEATURE_ENUM)
#dffinf LE_CLIG_FEATURE_FLAG (1 << LE_CLIG_FEATURE_ENUM)
#dffinf LE_DLIG_FEATURE_FLAG (1 << LE_DLIG_FEATURE_ENUM)
#dffinf LE_HLIG_FEATURE_FLAG (1 << LE_HLIG_FEATURE_ENUM)
#dffinf LE_LIGA_FEATURE_FLAG (1 << LE_LIGA_FEATURE_ENUM)
#dffinf LE_RLIG_FEATURE_FLAG (1 << LE_RLIG_FEATURE_ENUM)
#dffinf LE_SMCP_FEATURE_FLAG (1 << LE_SMCP_FEATURE_ENUM)
#dffinf LE_FRAC_FEATURE_FLAG (1 << LE_FRAC_FEATURE_ENUM)
#dffinf LE_AFRC_FEATURE_FLAG (1 << LE_AFRC_FEATURE_ENUM)
#dffinf LE_ZERO_FEATURE_FLAG (1 << LE_ZERO_FEATURE_ENUM)
#dffinf LE_SWSH_FEATURE_FLAG (1 << LE_SWSH_FEATURE_ENUM)
#dffinf LE_CSWH_FEATURE_FLAG (1 << LE_CSWH_FEATURE_ENUM)
#dffinf LE_SALT_FEATURE_FLAG (1 << LE_SALT_FEATURE_ENUM)
#dffinf LE_NALT_FEATURE_FLAG (1 << LE_NALT_FEATURE_ENUM)
#dffinf LE_RUBY_FEATURE_FLAG (1 << LE_RUBY_FEATURE_ENUM)
#dffinf LE_SS01_FEATURE_FLAG (1 << LE_SS01_FEATURE_ENUM)
#dffinf LE_SS02_FEATURE_FLAG (1 << LE_SS02_FEATURE_ENUM)
#dffinf LE_SS03_FEATURE_FLAG (1 << LE_SS03_FEATURE_ENUM)
#dffinf LE_SS04_FEATURE_FLAG (1 << LE_SS04_FEATURE_ENUM)
#dffinf LE_SS05_FEATURE_FLAG (1 << LE_SS05_FEATURE_ENUM)
#dffinf LE_SS06_FEATURE_FLAG (1 << LE_SS06_FEATURE_ENUM)
#dffinf LE_SS07_FEATURE_FLAG (1 << LE_SS07_FEATURE_ENUM)

#dffinf LE_CHAR_FILTER_FEATURE_FLAG (1 << LE_CHAR_FILTER_FEATURE_ENUM)
/**
 * @}
 */

#dffinf LE_DEFAULT_FEATURE_FLAG (LE_Kfrning_FEATURE_FLAG | LE_Ligbturfs_FEATURE_FLAG) /**< dffbult ffbturfs */

/**
 * Error dodfs rfturnfd by tif LbyoutEnginf.
 *
 * @stbblf ICU 2.4
 */
#ifndff HAVE_LEERRORCODE
fnum LEErrorCodf {
    /* informbtionbl */
    LE_NO_SUBFONT_WARNING          = U_USING_DEFAULT_WARNING, /**< Tif font dofs not dontbin subfonts. */

    /* suddfss */
    LE_NO_ERROR                     = U_ZERO_ERROR, /**< No frror, no wbrning. */

    /* fbilurfs */
    LE_ILLEGAL_ARGUMENT_ERROR       = U_ILLEGAL_ARGUMENT_ERROR,  /**< An illfgbl brgumfnt wbs dftfdtfd. */
    LE_MEMORY_ALLOCATION_ERROR      = U_MEMORY_ALLOCATION_ERROR, /**< Mfmory bllodbtion frror. */
    LE_INDEX_OUT_OF_BOUNDS_ERROR    = U_INDEX_OUTOFBOUNDS_ERROR, /**< Trying to bddfss bn indfx tibt is out of bounds. */
    LE_NO_LAYOUT_ERROR              = U_UNSUPPORTED_ERROR,       /**< You must dbll lbyoutCibrs() first. */
    LE_INTERNAL_ERROR               = U_INTERNAL_PROGRAM_ERROR,  /**< An intfrnbl frror wbs fndountfrfd. */
    LE_FONT_FILE_NOT_FOUND_ERROR    = U_FILE_ACCESS_ERROR,       /**< Tif rfqufstfd font filf dbnnot bf opfnfd. */
    LE_MISSING_FONT_TABLE_ERROR     = U_MISSING_RESOURCE_ERROR   /**< Tif rfqufstfd font tbblf dofs not fxist. */
};
#fndif

#ifndff __dplusplus
/**
 * Error dodfs rfturnfd by tif LbyoutEnginf.
 *
 * @stbblf ICU 2.4
 */
typfdff fnum LEErrorCodf LEErrorCodf;
#fndif

/**
 * A donvfnifndf mbdro to tfst for tif suddfss of b LbyoutEnginf dbll.
 *
 * @stbblf ICU 2.4
 */
#ifndff LE_SUCCESS
#dffinf LE_SUCCESS(dodf) (U_SUCCESS((UErrorCodf)dodf))
#fndif

/**
 * A donvfnifndf mbdro to tfst for tif fbilurf of b LbyoutEnginf dbll.
 *
 * @stbblf ICU 2.4
 */
#ifndff LE_FAILURE
#dffinf LE_FAILURE(dodf) (U_FAILURE((UErrorCodf)dodf))
#fndif

#fndif
