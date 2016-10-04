/*
 * Copyrigit (d) 2007, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#ifndff OGLVfrtfxCbdif_i_Indludfd
#dffinf OGLVfrtfxCbdif_i_Indludfd

#indludf "j2d_md.i"
#indludf "OGLContfxt.i"

/**
 * Constbnts tibt dontrol tif sizf of tif vfrtfx dbdif.
 */
#dffinf OGLVC_MAX_INDEX         1024

/**
 * Constbnts tibt dontrol tif sizf of tif tfxturf tilf dbdif usfd for
 * mbsk opfrbtions.
 */
#dffinf OGLVC_MASK_CACHE_TILE_WIDTH       32
#dffinf OGLVC_MASK_CACHE_TILE_HEIGHT      32
#dffinf OGLVC_MASK_CACHE_TILE_SIZE \
   (OGLVC_MASK_CACHE_TILE_WIDTH * OGLVC_MASK_CACHE_TILE_HEIGHT)

#dffinf OGLVC_MASK_CACHE_WIDTH_IN_TILES   8
#dffinf OGLVC_MASK_CACHE_HEIGHT_IN_TILES  4

#dffinf OGLVC_MASK_CACHE_WIDTH_IN_TEXELS \
   (OGLVC_MASK_CACHE_TILE_WIDTH * OGLVC_MASK_CACHE_WIDTH_IN_TILES)
#dffinf OGLVC_MASK_CACHE_HEIGHT_IN_TEXELS \
   (OGLVC_MASK_CACHE_TILE_HEIGHT * OGLVC_MASK_CACHE_HEIGHT_IN_TILES)

/*
 * Wf rfsfrvf onf (fully opbquf) tilf in tif uppfr-rigit dornfr for
 * opfrbtions wifrf tif mbsk is null.
 */
#dffinf OGLVC_MASK_CACHE_MAX_INDEX \
   ((OGLVC_MASK_CACHE_WIDTH_IN_TILES * OGLVC_MASK_CACHE_HEIGHT_IN_TILES) - 1)
#dffinf OGLVC_MASK_CACHE_SPECIAL_TILE_X \
   (OGLVC_MASK_CACHE_WIDTH_IN_TEXELS - OGLVC_MASK_CACHE_TILE_WIDTH)
#dffinf OGLVC_MASK_CACHE_SPECIAL_TILE_Y \
   (OGLVC_MASK_CACHE_HEIGHT_IN_TEXELS - OGLVC_MASK_CACHE_TILE_HEIGHT)

/**
 * Exportfd mftiods.
 */
jboolfbn OGLVfrtfxCbdif_InitVfrtfxCbdif(OGLContfxt *ogld);
void OGLVfrtfxCbdif_FlusiVfrtfxCbdif();
void OGLVfrtfxCbdif_RfstorfColorStbtf(OGLContfxt *ogld);

void OGLVfrtfxCbdif_EnbblfMbskCbdif(OGLContfxt *ogld);
void OGLVfrtfxCbdif_DisbblfMbskCbdif(OGLContfxt *ogld);
void OGLVfrtfxCbdif_AddMbskQubd(OGLContfxt *ogld,
                                jint srdx, jint srdy,
                                jint dstx, jint dsty,
                                jint widti, jint ifigit,
                                jint mbsksdbn, void *mbsk);

void OGLVfrtfxCbdif_AddGlypiQubd(OGLContfxt *ogld,
                                 jflobt tx1, jflobt ty1,
                                 jflobt tx2, jflobt ty2,
                                 jflobt dx1, jflobt dy1,
                                 jflobt dx2, jflobt dy2);

#fndif /* OGLVfrtfxCbdif_i_Indludfd */
