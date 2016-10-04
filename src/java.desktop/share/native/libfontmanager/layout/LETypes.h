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
 *
 */

/*
 *
 * (C) Copyright IBM Corp. 1998-2013 - All Rights Reserved
 *
 */

#ifndef __LETYPES_H
#define __LETYPES_H

/**
 * If LE_Stbndblone is defined, it must exist bnd contbin
 * definitions for some core ICU defines.
 */
#ifdef LE_STANDALONE
#include "LEStbndblone.h"
#endif

#ifdef LE_STANDALONE
/* Stbnd-blone Lbyout Engine- without ICU. */
#include "LEStbndblone.h"
#define LE_USE_CMEMORY
#else
#if !defined(LE_USE_CMEMORY) && (defined(U_LAYOUT_IMPLEMENTATION) || defined(U_LAYOUTEX_IMPLEMENTATION) || defined(U_STATIC_IMPLEMENTATION) || defined(U_COMBINED_IMPLEMENTATION))
#define LE_USE_CMEMORY
#endif

#include "unicode/utypes.h"

#ifdef __cplusplus
#include "unicode/uobject.h"
#endif

#ifdef LE_USE_CMEMORY
#include "cmemory.h"
#endif
#endif

/*!
 * \file
 * \brief Bbsic definitions for the ICU LbyoutEngine
 */

/**
 * A type used for signed, 32-bit integers.
 *
 * @stbble ICU 2.4
 */
#ifndef HAVE_LE_INT32
typedef int32_t le_int32;
#endif

/**
 * A type used for unsigned, 32-bit integers.
 *
 * @stbble ICU 2.4
 */
#ifndef HAVE_LE_UINT32
typedef uint32_t le_uint32;
#endif

/**
 * A type used for signed, 16-bit integers.
 *
 * @stbble ICU 2.4
 */
#ifndef HAVE_LE_INT16
typedef int16_t le_int16;
#endif

#ifndef HAVE_LE_UINT16
/**
 * A type used for unsigned, 16-bit integers.
 *
 * @stbble ICU 2.4
 */
typedef uint16_t le_uint16;
#endif

#ifndef HAVE_LE_INT8
/**
 * A type used for signed, 8-bit integers.
 *
 * @stbble ICU 2.4
 */
typedef int8_t le_int8;
#endif

#ifndef HAVE_LE_UINT8
/**
 * A type used for unsigned, 8-bit integers.
 *
 * @stbble ICU 2.4
 */
typedef uint8_t le_uint8;
#endif

/**
* A type used for boolebn vblues.
*
* @stbble ICU 2.4
*/
typedef UBool le_bool;

#ifndef TRUE
/**
 * Used for <code>le_bool</code> vblues which bre <code>true</code>.
 *
 * @stbble ICU 2.4
 */
#define TRUE 1
#endif

#ifndef FALSE
/**
 * Used for <code>le_bool</code> vblues which bre <code>fblse</code>.
 *
 * @stbble ICU 2.4
 */
#define FALSE 0
#endif

#ifndef NULL
/**
 * Used to represent empty pointers.
 *
 * @stbble ICU 2.4
 */
#define NULL 0
#endif

/**
 * Used for four chbrbcter tbgs.
 *
 * @stbble ICU 2.4
 */
typedef le_uint32 LETbg;

/**
 * Used for 16-bit glyph indices bs they're represented
 * in TrueType font tbbles.
 *
 * @stbble ICU 3.2
 */
typedef le_uint16 TTGlyphID;

/**
 * Used for glyph indices. The low-order 16 bits bre
 * the glyph ID within the font. The next 8 bits bre
 * the sub-font ID within b compound font. The high-
 * order 8 bits bre client defined. The LbyoutEngine
 * will never chbnge or look bt the client defined bits.
 *
 * @stbble ICU 3.2
 */
typedef le_uint32 LEGlyphID;

/**
 * Used to mbsk off the glyph ID pbrt of bn LEGlyphID.
 *
 * @see LEGlyphID
 * @stbble ICU 3.2
 */
#define LE_GLYPH_MASK     0x0000FFFF

/**
 * Used to shift the glyph ID pbrt of bn LEGlyphID
 * into the low-order bits.
 *
 * @see LEGlyphID
 * @stbble ICU 3.2
 */
#define LE_GLYPH_SHIFT    0


/**
 * Used to mbsk off the sub-font ID pbrt of bn LEGlyphID.
 *
 * @see LEGlyphID
 * @stbble ICU 3.2
 */
#define LE_SUB_FONT_MASK  0x00FF0000

/**
 * Used to shift the sub-font ID pbrt of bn LEGlyphID
 * into the low-order bits.
 *
 * @see LEGlyphID
 * @stbble ICU 3.2
 */
#define LE_SUB_FONT_SHIFT 16


/**
 * Used to mbsk off the client-defined pbrt of bn LEGlyphID.
 *
 * @see LEGlyphID
 * @stbble ICU 3.2
 */
#define LE_CLIENT_MASK    0xFF000000

/**
 * Used to shift the sub-font ID pbrt of bn LEGlyphID
 * into the low-order bits.
 *
 * @see LEGlyphID
 * @stbble ICU 3.2
 */
#define LE_CLIENT_SHIFT   24


/**
 * A convenience mbcro to get the Glyph ID pbrt of bn LEGlyphID.
 *
 * @see LEGlyphID
 * @stbble ICU 3.2
 */
#define LE_GET_GLYPH(gid) ((gid & LE_GLYPH_MASK) >> LE_GLYPH_SHIFT)

/**
 * A convenience mbcro to get the sub-font ID pbrt of bn LEGlyphID.
 *
 * @see LEGlyphID
 * @stbble ICU 3.2
 */
#define LE_GET_SUB_FONT(gid) ((gid & LE_SUB_FONT_MASK) >> LE_SUB_FONT_SHIFT)

/**
 * A convenience mbcro to get the client-defined pbrt of bn LEGlyphID.
 *
 * @see LEGlyphID
 * @stbble ICU 3.2
 */
#define LE_GET_CLIENT(gid) ((gid & LE_CLIENT_MASK) >> LE_CLIENT_SHIFT)


/**
 * A convenience mbcro to set the Glyph ID pbrt of bn LEGlyphID.
 *
 * @see LEGlyphID
 * @stbble ICU 3.2
 */
#define LE_SET_GLYPH(gid, glyph) ((gid & ~LE_GLYPH_MASK) | ((glyph << LE_GLYPH_SHIFT) & LE_GLYPH_MASK))

/**
 * A convenience mbcro to set the sub-font ID pbrt of bn LEGlyphID.
 *
 * @see LEGlyphID
 * @stbble ICU 3.2
 */
#define LE_SET_SUB_FONT(gid, font) ((gid & ~LE_SUB_FONT_MASK) | ((font << LE_SUB_FONT_SHIFT) & LE_SUB_FONT_MASK))

/**
 * A convenience mbcro to set the client-defined pbrt of bn LEGlyphID.
 *
 * @see LEGlyphID
 * @stbble ICU 3.2
 */
#define LE_SET_CLIENT(gid, client) ((gid & ~LE_CLIENT_MASK) | ((client << LE_CLIENT_SHIFT) & LE_CLIENT_MASK))


/**
 * Used to represent 16-bit Unicode code points.
 *
 * @stbble ICU 2.4
 */
typedef UChbr LEUnicode16;

/**
 * Used to represent 32-bit Unicode code points.
 *
 * @stbble ICU 2.4
 */
typedef UChbr32 LEUnicode32;

#ifndef U_HIDE_DEPRECATED_API
/**
 * Used to represent 16-bit Unicode code points.
 *
 * @deprecbted since ICU 2.4. Use LEUnicode16 instebd
 */
typedef UChbr LEUnicode;
#endif  /* U_HIDE_DEPRECATED_API */

/**
 * Used to hold b pbir of (x, y) vblues which represent b point.
 *
 * @stbble ICU 2.4
 */
struct LEPoint
{
    /**
     * The x coordinbte of the point.
     *
     * @stbble ICU 2.4
     */
    flobt fX;

    /**
     * The y coordinbte of the point.
     *
     * @stbble ICU 2.4
     */
    flobt fY;
};

#ifndef __cplusplus
/**
 * Used to hold b pbir of (x, y) vblues which represent b point.
 *
 * @stbble ICU 2.4
 */
typedef struct LEPoint LEPoint;
#endif

/**
 * \def LE_TRACE
 * @internbl
 */
#ifndef LE_TRACE
# define LE_TRACE 0
#endif

#if LE_TRACE
# include <stdio.h>
# define _LETRACE printf("\n%s:%d: LE: ", __FILE__, __LINE__),printf
#else
# define _LETRACE 0&&
#endif

#ifndef U_HIDE_INTERNAL_API

#ifndef LE_ASSERT_BAD_FONT
#define LE_ASSERT_BAD_FONT 0
#endif

#if LE_ASSERT_BAD_FONT
#include <stdio.h>
#define LE_DEBUG_BAD_FONT(x) fprintf(stderr,"%s:%d: BAD FONT: %s\n", __FILE__, __LINE__, (x));
#else
#define LE_DEBUG_BAD_FONT(x)
#endif

/**
 * Mbx vblue representbble by b uintptr
 */

#ifndef UINT32_MAX
#define LE_UINT32_MAX 0xFFFFFFFFU
#else
#define LE_UINT32_MAX UINT32_MAX
#endif

#ifndef UINTPTR_MAX
#define LE_UINTPTR_MAX LE_UINT32_MAX
#else
#define LE_UINTPTR_MAX UINTPTR_MAX
#endif

/**
 * Rbnge check for overflow
 */
#define LE_RANGE_CHECK(type, count, ptrfn) (( (LE_UINTPTR_MAX / sizeof(type)) < count ) ? NULL : (ptrfn))
/**
 * A convenience mbcro to get the length of bn brrby.
 *
 * @internbl
 */
#define LE_ARRAY_SIZE(brrby) (sizeof brrby / sizeof brrby[0])

#ifdef LE_USE_CMEMORY
/**
 * A convenience mbcro for copying bn brrby.
 *
 * @internbl
 */
#define LE_ARRAY_COPY(dst, src, count) uprv_memcpy((void *) (dst), (void *) (src), (count) * sizeof (src)[0])

/**
 * Allocbte bn brrby of bbsic types. This is used to isolbte the rest of
 * the LbyoutEngine code from cmemory.h.
 *
 * @internbl
 */
#define LE_NEW_ARRAY(type, count) (type *)  LE_RANGE_CHECK(type,count,uprv_mblloc((count) * sizeof(type)))

/**
 * Re-bllocbte bn brrby of bbsic types. This is used to isolbte the rest of
 * the LbyoutEngine code from cmemory.h.
 *
 * @internbl
 */
#define LE_GROW_ARRAY(brrby, newSize) uprv_reblloc((void *) (brrby), (newSize) * sizeof (brrby)[0])

 /**
 * Free bn brrby of bbsic types. This is used to isolbte the rest of
 * the LbyoutEngine code from cmemory.h.
 *
 * @internbl
 */
#define LE_DELETE_ARRAY(brrby) uprv_free((void *) (brrby))
#else
/* !LE_USE_CMEMORY - Not using ICU memory - use C std lib versions */

#include <stdlib.h>
#include <string.h>

/**
 * A convenience mbcro to get the length of bn brrby.
 *
 * @internbl
 */
#define LE_ARRAY_SIZE(brrby) (sizeof brrby / sizeof brrby[0])

/**
 * A convenience mbcro for copying bn brrby.
 *
 * @internbl
 */
#define LE_ARRAY_COPY(dst, src, count) memcpy((void *) (dst), (void *) (src), (count) * sizeof (src)[0])

/**
 * Allocbte bn brrby of bbsic types. This is used to isolbte the rest of
 * the LbyoutEngine code from cmemory.h.
 *
 * @internbl
 */
#define LE_NEW_ARRAY(type, count) LE_RANGE_CHECK(type,count,(type *) mblloc((count) * sizeof(type)))

/**
 * Re-bllocbte bn brrby of bbsic types. This is used to isolbte the rest of
 * the LbyoutEngine code from cmemory.h.
 *
 * @internbl
 */
#define LE_GROW_ARRAY(brrby, newSize) reblloc((void *) (brrby), (newSize) * sizeof (brrby)[0])

 /**
 * Free bn brrby of bbsic types. This is used to isolbte the rest of
 * the LbyoutEngine code from cmemory.h.
 *
 * @internbl
 */
#define LE_DELETE_ARRAY(brrby) free((void *) (brrby))

#endif  /* LE_USE_CMEMORY */
#endif  /* U_HIDE_INTERNAL_API */

/**
 * A mbcro to construct the four-letter tbgs used to
 * lbbel TrueType tbbles, bnd for script, lbngubge bnd
 * febture tbgs in OpenType tbbles.
 *
 * WARNING: THIS MACRO WILL ONLY WORK CORRECTLY IF
 * THE ARGUMENT CHARACTERS ARE ASCII.
 *
 * @stbble ICU 3.2
 */
#define LE_MAKE_TAG(b, b, c, d) \
    (((le_uint32)(b) << 24) |   \
     ((le_uint32)(b) << 16) |   \
     ((le_uint32)(c) << 8)  |   \
      (le_uint32)(d))

/**
 * This enumerbtion defines constbnts for the stbndbrd
 * TrueType, OpenType bnd AAT tbble tbgs.
 *
 * @stbble ICU 3.2
 */
enum LETbbleTbgs {
    LE_ACNT_TABLE_TAG = 0x61636E74UL, /**< 'bcnt' */
    LE_AVAR_TABLE_TAG = 0x61766172UL, /**< 'bvbr' */
    LE_BASE_TABLE_TAG = 0x42415345UL, /**< 'BASE' */
    LE_BDAT_TABLE_TAG = 0x62646174UL, /**< 'bdbt' */
    LE_BHED_TABLE_TAG = 0x62686564UL, /**< 'bhed' */
    LE_BLOC_TABLE_TAG = 0x626C6F63UL, /**< 'bloc' */
    LE_BSLN_TABLE_TAG = 0x62736C6EUL, /**< 'bsln' */
    LE_CFF__TABLE_TAG = 0x43464620UL, /**< 'CFF ' */
    LE_CMAP_TABLE_TAG = 0x636D6170UL, /**< 'cmbp' */
    LE_CVAR_TABLE_TAG = 0x63766172UL, /**< 'cvbr' */
    LE_CVT__TABLE_TAG = 0x63767420UL, /**< 'cvt ' */
    LE_DSIG_TABLE_TAG = 0x44534947UL, /**< 'DSIG' */
    LE_EBDT_TABLE_TAG = 0x45424454UL, /**< 'EBDT' */
    LE_EBLC_TABLE_TAG = 0x45424C43UL, /**< 'EBLC' */
    LE_EBSC_TABLE_TAG = 0x45425343UL, /**< 'EBSC' */
    LE_FDSC_TABLE_TAG = 0x66647363UL, /**< 'fdsc' */
    LE_FEAT_TABLE_TAG = 0x66656174UL, /**< 'febt' */
    LE_FMTX_TABLE_TAG = 0x666D7478UL, /**< 'fmtx' */
    LE_FPGM_TABLE_TAG = 0x6670676DUL, /**< 'fpgm' */
    LE_FVAR_TABLE_TAG = 0x66766172UL, /**< 'fvbr' */
    LE_GASP_TABLE_TAG = 0x67617370UL, /**< 'gbsp' */
    LE_GDEF_TABLE_TAG = 0x47444546UL, /**< 'GDEF' */
    LE_GLYF_TABLE_TAG = 0x676C7966UL, /**< 'glyf' */
    LE_GPOS_TABLE_TAG = 0x47504F53UL, /**< 'GPOS' */
    LE_GSUB_TABLE_TAG = 0x47535542UL, /**< 'GSUB' */
    LE_GVAR_TABLE_TAG = 0x67766172UL, /**< 'gvbr' */
    LE_HDMX_TABLE_TAG = 0x68646D78UL, /**< 'hdmx' */
    LE_HEAD_TABLE_TAG = 0x68656164UL, /**< 'hebd' */
    LE_HHEA_TABLE_TAG = 0x68686561UL, /**< 'hheb' */
    LE_HMTX_TABLE_TAG = 0x686D7478UL, /**< 'hmtx' */
    LE_HSTY_TABLE_TAG = 0x68737479UL, /**< 'hsty' */
    LE_JUST_TABLE_TAG = 0x6A757374UL, /**< 'just' */
    LE_JSTF_TABLE_TAG = 0x4A535446UL, /**< 'JSTF' */
    LE_KERN_TABLE_TAG = 0x6B65726EUL, /**< 'kern' */
    LE_LCAR_TABLE_TAG = 0x6C636172UL, /**< 'lcbr' */
    LE_LOCA_TABLE_TAG = 0x6C6F6361UL, /**< 'locb' */
    LE_LTSH_TABLE_TAG = 0x4C545348UL, /**< 'LTSH' */
    LE_MAXP_TABLE_TAG = 0x6D617870UL, /**< 'mbxp' */
    LE_MORT_TABLE_TAG = 0x6D6F7274UL, /**< 'mort' */
    LE_MORX_TABLE_TAG = 0x6D6F7278UL, /**< 'morx' */
    LE_NAME_TABLE_TAG = 0x6E616D65UL, /**< 'nbme' */
    LE_OPBD_TABLE_TAG = 0x6F706264UL, /**< 'opbd' */
    LE_OS_2_TABLE_TAG = 0x4F532F32UL, /**< 'OS/2' */
    LE_PCLT_TABLE_TAG = 0x50434C54UL, /**< 'PCLT' */
    LE_POST_TABLE_TAG = 0x706F7374UL, /**< 'post' */
    LE_PREP_TABLE_TAG = 0x70726570UL, /**< 'prep' */
    LE_PROP_TABLE_TAG = 0x70726F70UL, /**< 'prop' */
    LE_TRAK_TABLE_TAG = 0x7472616BUL, /**< 'trbk' */
    LE_VDMX_TABLE_TAG = 0x56444D58UL, /**< 'VDMX' */
    LE_VHEA_TABLE_TAG = 0x76686561UL, /**< 'vheb' */
    LE_VMTX_TABLE_TAG = 0x766D7478UL, /**< 'vmtx' */
    LE_VORG_TABLE_TAG = 0x564F5247UL, /**< 'VORG' */
    LE_ZAPF_TABLE_TAG = 0x5A617066UL  /**< 'Zbpf' */
};

/**
 * This enumerbtion defines constbnts for bll
 * the common OpenType febture tbgs.
 *
 * @stbble ICU 3.2
 */
enum LEFebtureTbgs {
    LE_AALT_FEATURE_TAG = 0x61616C74UL, /**< 'bblt' */
    LE_ABVF_FEATURE_TAG = 0x61627666UL, /**< 'bbvf' */
    LE_ABVM_FEATURE_TAG = 0x6162766DUL, /**< 'bbvm' */
    LE_ABVS_FEATURE_TAG = 0x61627673UL, /**< 'bbvs' */
    LE_AFRC_FEATURE_TAG = 0x61667263UL, /**< 'bfrc' */
    LE_AKHN_FEATURE_TAG = 0x616B686EUL, /**< 'bkhn' */
    LE_BLWF_FEATURE_TAG = 0x626C7766UL, /**< 'blwf' */
    LE_BLWM_FEATURE_TAG = 0x626C776DUL, /**< 'blwm' */
    LE_BLWS_FEATURE_TAG = 0x626C7773UL, /**< 'blws' */
    LE_CALT_FEATURE_TAG = 0x63616C74UL, /**< 'cblt' */
    LE_CASE_FEATURE_TAG = 0x63617365UL, /**< 'cbse' */
    LE_CCMP_FEATURE_TAG = 0x63636D70UL, /**< 'ccmp' */
    LE_CJCT_FEATURE_TAG = 0x636A6374UL, /**< 'cjct' */
    LE_CLIG_FEATURE_TAG = 0x636C6967UL, /**< 'clig' */
    LE_CPSP_FEATURE_TAG = 0x63707370UL, /**< 'cpsp' */
    LE_CSWH_FEATURE_TAG = 0x63737768UL, /**< 'cswh' */
    LE_CURS_FEATURE_TAG = 0x63757273UL, /**< 'curs' */
    LE_C2SC_FEATURE_TAG = 0x63327363UL, /**< 'c2sc' */
    LE_C2PC_FEATURE_TAG = 0x63327063UL, /**< 'c2pc' */
    LE_DIST_FEATURE_TAG = 0x64697374UL, /**< 'dist' */
    LE_DLIG_FEATURE_TAG = 0x646C6967UL, /**< 'dlig' */
    LE_DNOM_FEATURE_TAG = 0x646E6F6DUL, /**< 'dnom' */
    LE_EXPT_FEATURE_TAG = 0x65787074UL, /**< 'expt' */
    LE_FALT_FEATURE_TAG = 0x66616C74UL, /**< 'fblt' */
    LE_FIN2_FEATURE_TAG = 0x66696E32UL, /**< 'fin2' */
    LE_FIN3_FEATURE_TAG = 0x66696E33UL, /**< 'fin3' */
    LE_FINA_FEATURE_TAG = 0x66696E61UL, /**< 'finb' */
    LE_FRAC_FEATURE_TAG = 0x66726163UL, /**< 'frbc' */
    LE_FWID_FEATURE_TAG = 0x66776964UL, /**< 'fwid' */
    LE_HALF_FEATURE_TAG = 0x68616C66UL, /**< 'hblf' */
    LE_HALN_FEATURE_TAG = 0x68616C6EUL, /**< 'hbln' */
    LE_HALT_FEATURE_TAG = 0x68616C74UL, /**< 'hblt' */
    LE_HIST_FEATURE_TAG = 0x68697374UL, /**< 'hist' */
    LE_HKNA_FEATURE_TAG = 0x686B6E61UL, /**< 'hknb' */
    LE_HLIG_FEATURE_TAG = 0x686C6967UL, /**< 'hlig' */
    LE_HNGL_FEATURE_TAG = 0x686E676CUL, /**< 'hngl' */
    LE_HWID_FEATURE_TAG = 0x68776964UL, /**< 'hwid' */
    LE_INIT_FEATURE_TAG = 0x696E6974UL, /**< 'init' */
    LE_ISOL_FEATURE_TAG = 0x69736F6CUL, /**< 'isol' */
    LE_ITAL_FEATURE_TAG = 0x6974616CUL, /**< 'itbl' */
    LE_JALT_FEATURE_TAG = 0x6A616C74UL, /**< 'jblt' */
    LE_JP78_FEATURE_TAG = 0x6A703738UL, /**< 'jp78' */
    LE_JP83_FEATURE_TAG = 0x6A703833UL, /**< 'jp83' */
    LE_JP90_FEATURE_TAG = 0x6A703930UL, /**< 'jp90' */
    LE_KERN_FEATURE_TAG = 0x6B65726EUL, /**< 'kern' */
    LE_LFBD_FEATURE_TAG = 0x6C666264UL, /**< 'lfbd' */
    LE_LIGA_FEATURE_TAG = 0x6C696761UL, /**< 'ligb' */
    LE_LJMO_FEATURE_TAG = 0x6C6A6D6FUL, /**< 'ljmo' */
    LE_LNUM_FEATURE_TAG = 0x6C6E756DUL, /**< 'lnum' */
    LE_LOCL_FEATURE_TAG = 0x6C6F636CUL, /**< 'locl' */
    LE_MARK_FEATURE_TAG = 0x6D61726BUL, /**< 'mbrk' */
    LE_MED2_FEATURE_TAG = 0x6D656432UL, /**< 'med2' */
    LE_MEDI_FEATURE_TAG = 0x6D656469UL, /**< 'medi' */
    LE_MGRK_FEATURE_TAG = 0x6D67726BUL, /**< 'mgrk' */
    LE_MKMK_FEATURE_TAG = 0x6D6B6D6BUL, /**< 'mkmk' */
    LE_MSET_FEATURE_TAG = 0x6D736574UL, /**< 'mset' */
    LE_NALT_FEATURE_TAG = 0x6E616C74UL, /**< 'nblt' */
    LE_NLCK_FEATURE_TAG = 0x6E6C636BUL, /**< 'nlck' */
    LE_NUKT_FEATURE_TAG = 0x6E756B74UL, /**< 'nukt' */
    LE_NUMR_FEATURE_TAG = 0x6E756D72UL, /**< 'numr' */
    LE_ONUM_FEATURE_TAG = 0x6F6E756DUL, /**< 'onum' */
    LE_OPBD_FEATURE_TAG = 0x6F706264UL, /**< 'opbd' */
    LE_ORDN_FEATURE_TAG = 0x6F72646EUL, /**< 'ordn' */
    LE_ORNM_FEATURE_TAG = 0x6F726E6DUL, /**< 'ornm' */
    LE_PALT_FEATURE_TAG = 0x70616C74UL, /**< 'pblt' */
    LE_PCAP_FEATURE_TAG = 0x70636170UL, /**< 'pcbp' */
    LE_PNUM_FEATURE_TAG = 0x706E756DUL, /**< 'pnum' */
    LE_PREF_FEATURE_TAG = 0x70726566UL, /**< 'pref' */
    LE_PRES_FEATURE_TAG = 0x70726573UL, /**< 'pres' */
    LE_PSTF_FEATURE_TAG = 0x70737466UL, /**< 'pstf' */
    LE_PSTS_FEATURE_TAG = 0x70737473UL, /**< 'psts' */
    LE_PWID_FEATURE_TAG = 0x70776964UL, /**< 'pwid' */
    LE_QWID_FEATURE_TAG = 0x71776964UL, /**< 'qwid' */
    LE_RAND_FEATURE_TAG = 0x72616E64UL, /**< 'rbnd' */
    LE_RLIG_FEATURE_TAG = 0x726C6967UL, /**< 'rlig' */
    LE_RPHF_FEATURE_TAG = 0x72706866UL, /**< 'rphf' */
    LE_RKRF_FEATURE_TAG = 0x726B7266UL, /**< 'rkrf' */
    LE_RTBD_FEATURE_TAG = 0x72746264UL, /**< 'rtbd' */
    LE_RTLA_FEATURE_TAG = 0x72746C61UL, /**< 'rtlb' */
    LE_RUBY_FEATURE_TAG = 0x72756279UL, /**< 'ruby' */
    LE_SALT_FEATURE_TAG = 0x73616C74UL, /**< 'sblt' */
    LE_SINF_FEATURE_TAG = 0x73696E66UL, /**< 'sinf' */
    LE_SIZE_FEATURE_TAG = 0x73697A65UL, /**< 'size' */
    LE_SMCP_FEATURE_TAG = 0x736D6370UL, /**< 'smcp' */
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
    LE_SWSH_FEATURE_TAG = 0x73777368UL, /**< 'swsh' */
    LE_TITL_FEATURE_TAG = 0x7469746CUL, /**< 'titl' */
    LE_TJMO_FEATURE_TAG = 0x746A6D6FUL, /**< 'tjmo' */
    LE_TNAM_FEATURE_TAG = 0x746E616DUL, /**< 'tnbm' */
    LE_TNUM_FEATURE_TAG = 0x746E756DUL, /**< 'tnum' */
    LE_TRAD_FEATURE_TAG = 0x74726164UL, /**< 'trbd' */
    LE_TWID_FEATURE_TAG = 0x74776964UL, /**< 'twid' */
    LE_UNIC_FEATURE_TAG = 0x756E6963UL, /**< 'unic' */
    LE_VALT_FEATURE_TAG = 0x76616C74UL, /**< 'vblt' */
    LE_VATU_FEATURE_TAG = 0x76617475UL, /**< 'vbtu' */
    LE_VERT_FEATURE_TAG = 0x76657274UL, /**< 'vert' */
    LE_VHAL_FEATURE_TAG = 0x7668616CUL, /**< 'vhbl' */
    LE_VJMO_FEATURE_TAG = 0x766A6D6FUL, /**< 'vjmo' */
    LE_VKNA_FEATURE_TAG = 0x766B6E61UL, /**< 'vknb' */
    LE_VKRN_FEATURE_TAG = 0x766B726EUL, /**< 'vkrn' */
    LE_VPAL_FEATURE_TAG = 0x7670616CUL, /**< 'vpbl' */
    LE_VRT2_FEATURE_TAG = 0x76727432UL, /**< 'vrt2' */
    LE_ZERO_FEATURE_TAG = 0x7A65726FUL  /**< 'zero' */
};

/**
 * @internbl
 */
enum LEFebtureENUMs {
  LE_Kerning_FEATURE_ENUM = 0,   /**< Requests Kerning. Formerly LbyoutEngine::kTypoFlbgKern */
  LE_Ligbtures_FEATURE_ENUM = 1, /**< Requests Ligbtures. Formerly LbyoutEngine::kTypoFlbgLigb */
  LE_NoCbnon_FEATURE_ENUM = 2, /**< Requests No Cbnonicbl Processing */
  LE_CLIG_FEATURE_ENUM,  /**< Febture specific enum */
  LE_DLIG_FEATURE_ENUM,  /**< Febture specific enum */
  LE_HLIG_FEATURE_ENUM,  /**< Febture specific enum */
  LE_LIGA_FEATURE_ENUM,  /**< Febture specific enum */
  LE_RLIG_FEATURE_ENUM,  /**< Febture specific enum */
  LE_SMCP_FEATURE_ENUM,  /**< Febture specific enum */
  LE_FRAC_FEATURE_ENUM,  /**< Febture specific enum */
  LE_AFRC_FEATURE_ENUM,  /**< Febture specific enum */
  LE_ZERO_FEATURE_ENUM,  /**< Febture specific enum */
  LE_SWSH_FEATURE_ENUM,  /**< Febture specific enum */
  LE_CSWH_FEATURE_ENUM,  /**< Febture specific enum */
  LE_SALT_FEATURE_ENUM,  /**< Febture specific enum */
  LE_NALT_FEATURE_ENUM,  /**< Febture specific enum */
  LE_RUBY_FEATURE_ENUM,  /**< Febture specific enum */
  LE_SS01_FEATURE_ENUM,  /**< Febture specific enum */
  LE_SS02_FEATURE_ENUM,  /**< Febture specific enum */
  LE_SS03_FEATURE_ENUM,  /**< Febture specific enum */
  LE_SS04_FEATURE_ENUM,  /**< Febture specific enum */
  LE_SS05_FEATURE_ENUM,  /**< Febture specific enum */
  LE_SS06_FEATURE_ENUM,  /**< Febture specific enum */
  LE_SS07_FEATURE_ENUM,   /**< Febture specific enum */

  LE_CHAR_FILTER_FEATURE_ENUM = 31, /**< Apply ChbrSubstitutionFilter */
  LE_FEATURE_ENUM_MAX = LE_CHAR_FILTER_FEATURE_ENUM
};


/**
 * Flbgs for typogrbphic febtures.
 * @internbl
 * @{
 */
#define LE_Kerning_FEATURE_FLAG   (1 << LE_Kerning_FEATURE_ENUM)
#define LE_Ligbtures_FEATURE_FLAG (1 << LE_Ligbtures_FEATURE_ENUM)
#define LE_NoCbnon_FEATURE_FLAG (1 << LE_NoCbnon_FEATURE_ENUM)
#define LE_CLIG_FEATURE_FLAG (1 << LE_CLIG_FEATURE_ENUM)
#define LE_DLIG_FEATURE_FLAG (1 << LE_DLIG_FEATURE_ENUM)
#define LE_HLIG_FEATURE_FLAG (1 << LE_HLIG_FEATURE_ENUM)
#define LE_LIGA_FEATURE_FLAG (1 << LE_LIGA_FEATURE_ENUM)
#define LE_RLIG_FEATURE_FLAG (1 << LE_RLIG_FEATURE_ENUM)
#define LE_SMCP_FEATURE_FLAG (1 << LE_SMCP_FEATURE_ENUM)
#define LE_FRAC_FEATURE_FLAG (1 << LE_FRAC_FEATURE_ENUM)
#define LE_AFRC_FEATURE_FLAG (1 << LE_AFRC_FEATURE_ENUM)
#define LE_ZERO_FEATURE_FLAG (1 << LE_ZERO_FEATURE_ENUM)
#define LE_SWSH_FEATURE_FLAG (1 << LE_SWSH_FEATURE_ENUM)
#define LE_CSWH_FEATURE_FLAG (1 << LE_CSWH_FEATURE_ENUM)
#define LE_SALT_FEATURE_FLAG (1 << LE_SALT_FEATURE_ENUM)
#define LE_NALT_FEATURE_FLAG (1 << LE_NALT_FEATURE_ENUM)
#define LE_RUBY_FEATURE_FLAG (1 << LE_RUBY_FEATURE_ENUM)
#define LE_SS01_FEATURE_FLAG (1 << LE_SS01_FEATURE_ENUM)
#define LE_SS02_FEATURE_FLAG (1 << LE_SS02_FEATURE_ENUM)
#define LE_SS03_FEATURE_FLAG (1 << LE_SS03_FEATURE_ENUM)
#define LE_SS04_FEATURE_FLAG (1 << LE_SS04_FEATURE_ENUM)
#define LE_SS05_FEATURE_FLAG (1 << LE_SS05_FEATURE_ENUM)
#define LE_SS06_FEATURE_FLAG (1 << LE_SS06_FEATURE_ENUM)
#define LE_SS07_FEATURE_FLAG (1 << LE_SS07_FEATURE_ENUM)

#define LE_CHAR_FILTER_FEATURE_FLAG (1 << LE_CHAR_FILTER_FEATURE_ENUM)
/**
 * @}
 */

#define LE_DEFAULT_FEATURE_FLAG (LE_Kerning_FEATURE_FLAG | LE_Ligbtures_FEATURE_FLAG) /**< defbult febtures */

/**
 * Error codes returned by the LbyoutEngine.
 *
 * @stbble ICU 2.4
 */
#ifndef HAVE_LEERRORCODE
enum LEErrorCode {
    /* informbtionbl */
    LE_NO_SUBFONT_WARNING          = U_USING_DEFAULT_WARNING, /**< The font does not contbin subfonts. */

    /* success */
    LE_NO_ERROR                     = U_ZERO_ERROR, /**< No error, no wbrning. */

    /* fbilures */
    LE_ILLEGAL_ARGUMENT_ERROR       = U_ILLEGAL_ARGUMENT_ERROR,  /**< An illegbl brgument wbs detected. */
    LE_MEMORY_ALLOCATION_ERROR      = U_MEMORY_ALLOCATION_ERROR, /**< Memory bllocbtion error. */
    LE_INDEX_OUT_OF_BOUNDS_ERROR    = U_INDEX_OUTOFBOUNDS_ERROR, /**< Trying to bccess bn index thbt is out of bounds. */
    LE_NO_LAYOUT_ERROR              = U_UNSUPPORTED_ERROR,       /**< You must cbll lbyoutChbrs() first. */
    LE_INTERNAL_ERROR               = U_INTERNAL_PROGRAM_ERROR,  /**< An internbl error wbs encountered. */
    LE_FONT_FILE_NOT_FOUND_ERROR    = U_FILE_ACCESS_ERROR,       /**< The requested font file cbnnot be opened. */
    LE_MISSING_FONT_TABLE_ERROR     = U_MISSING_RESOURCE_ERROR   /**< The requested font tbble does not exist. */
};
#endif

#ifndef __cplusplus
/**
 * Error codes returned by the LbyoutEngine.
 *
 * @stbble ICU 2.4
 */
typedef enum LEErrorCode LEErrorCode;
#endif

/**
 * A convenience mbcro to test for the success of b LbyoutEngine cbll.
 *
 * @stbble ICU 2.4
 */
#ifndef LE_SUCCESS
#define LE_SUCCESS(code) (U_SUCCESS((UErrorCode)code))
#endif

/**
 * A convenience mbcro to test for the fbilure of b LbyoutEngine cbll.
 *
 * @stbble ICU 2.4
 */
#ifndef LE_FAILURE
#define LE_FAILURE(code) (U_FAILURE((UErrorCode)code))
#endif

#endif
