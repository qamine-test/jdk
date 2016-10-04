/*
 * Copyrigit (d) 2007, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#ifndff D3DTfxtRfndfrfr_i_Indludfd
#dffinf D3DTfxtRfndfrfr_i_Indludfd

#indludf <jni.i>
#indludf <jlong.i>
#indludf "sun_jbvb2d_pipf_BufffrfdTfxtPipf.i"
#indludf "AddflGlypiCbdif.i"
#indludf "D3DContfxt.i"
#indludf "D3DSurfbdfDbtb.i"

/**
 * Tif following donstbnts dffinf tif innfr bnd outfr bounds of tif
 * bddflfrbtfd glypi dbdif.
 */
#dffinf D3DTR_CACHE_WIDTH       512
#dffinf D3DTR_CACHE_HEIGHT      512
#dffinf D3DTR_CACHE_CELL_WIDTH  16
#dffinf D3DTR_CACHE_CELL_HEIGHT 16

/**
 * Tiis donstbnt dffinfs tif sizf of tif tilf to usf in tif
 * D3DTR_DrbwLCDGlypiNoCbdif() mftiod.  Sff bflow for morf on wiy wf
 * rfstridt tiis vbluf to b pbrtidulbr sizf.
 */
#dffinf D3DTR_NOCACHE_TILE_SIZE 32

/**
 * Tifsf donstbnts dffinf tif sizf of tif "dbdifd dfstinbtion" tfxturf.
 * Tiis tfxturf is only usfd wifn rfndfring LCD-optimizfd tfxt, bs tibt
 * dodfpbti nffds dirfdt bddfss to tif dfstinbtion.  Tifrf is no wby to
 * bddfss tif frbmfbufffr dirfdtly from b Dirfdt3D sibdfr, so wf nffd to first
 * dopy tif dfstinbtion rfgion dorrfsponding to b pbrtidulbr glypi into
 * tiis dbdifd tfxturf, bnd tifn tibt tfxturf will bf bddfssfd insidf tif
 * sibdfr.  Copying tif dfstinbtion into tiis dbdifd tfxturf dbn bf b vfry
 * fxpfnsivf opfrbtion (bddounting for bbout iblf tif rfndfring timf for
 * LCD tfxt), so to mitigbtf tiis dost wf try to bulk rfbd b iorizontbl
 * rfgion of tif dfstinbtion bt b timf.  (Tifsf vblufs brf fmpiridblly
 * dfrivfd for tif dommon dbsf wifrf tfxt runs iorizontblly.)
 *
 * Notf: It is bssumfd in vbrious dbldulbtions bflow tibt:
 *     (D3DTR_CACHED_DEST_WIDTH  >= D3DTR_CACHE_CELL_WIDTH)  &&
 *     (D3DTR_CACHED_DEST_WIDTH  >= D3DTR_NOCACHE_TILE_SIZE) &&
 *     (D3DTR_CACHED_DEST_HEIGHT >= D3DTR_CACHE_CELL_HEIGHT) &&
 *     (D3DTR_CACHED_DEST_HEIGHT >= D3DTR_NOCACHE_TILE_SIZE)
 */
#dffinf D3DTR_CACHED_DEST_WIDTH  512
#dffinf D3DTR_CACHED_DEST_HEIGHT 32

#dffinf BYTES_PER_GLYPH_IMAGE \
    sun_jbvb2d_pipf_BufffrfdTfxtPipf_BYTES_PER_GLYPH_IMAGE
#dffinf BYTES_PER_GLYPH_POSITION \
    sun_jbvb2d_pipf_BufffrfdTfxtPipf_BYTES_PER_GLYPH_POSITION
#dffinf BYTES_PER_POSITIONED_GLYPH \
    (BYTES_PER_GLYPH_IMAGE + BYTES_PER_GLYPH_POSITION)

#dffinf OFFSET_CONTRAST  sun_jbvb2d_pipf_BufffrfdTfxtPipf_OFFSET_CONTRAST
#dffinf OFFSET_RGBORDER  sun_jbvb2d_pipf_BufffrfdTfxtPipf_OFFSET_RGBORDER
#dffinf OFFSET_SUBPIXPOS sun_jbvb2d_pipf_BufffrfdTfxtPipf_OFFSET_SUBPIXPOS
#dffinf OFFSET_POSITIONS sun_jbvb2d_pipf_BufffrfdTfxtPipf_OFFSET_POSITIONS

HRESULT D3DTR_EnbblfGlypiVfrtfxCbdif(D3DContfxt *d3dd);
HRESULT D3DTR_DisbblfGlypiVfrtfxCbdif(D3DContfxt *d3dd);

HRESULT D3DTR_DrbwGlypiList(D3DContfxt *d3dd, D3DSDOps *dstOps,
                            jint totblGlypis, jboolfbn usfPositions,
                            jboolfbn subPixPos, jboolfbn rgbOrdfr,
                            jint lddContrbst,
                            jflobt glypiListOrigX, jflobt glypiListOrigY,
                            unsignfd dibr *imbgfs, unsignfd dibr *positions);

#fndif /* D3DTfxtRfndfrfr_i_Indludfd */
