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

#ifndff _D3DSURFACEDATA_H_
#dffinf _D3DSURFACEDATA_H_

#indludf "jbvb_bwt_imbgf_AffinfTrbnsformOp.i"
#indludf "sun_jbvb2d_d3d_D3DSurfbdfDbtb.i"
#indludf "sun_jbvb2d_pipf_iw_AddflSurfbdf.i"
#indludf "SurfbdfDbtb.i"
#indludf <d3d9.i>

typfdff strudt _D3DSDOps D3DSDOps;

dlbss D3DRfsourdf;

strudt _D3DSDOps {
    SurfbdfDbtbOps sdOps;

    // tif ordinbl of tif d3d bdbptfr tiis surfbdf bflongs to
    // (mby bf difffrfnt from GDI displby numbfr)
    jint bdbptfr;
    jint widti, ifigit;

    // bbdkbufffr-rflbtfd dbtb
    jint xoff, yoff;
    D3DSWAPEFFECT swbpEfffdt;

    D3DRfsourdf  *pRfsourdf;
};

#dffinf UNDEFINED       sun_jbvb2d_pipf_iw_AddflSurfbdf_UNDEFINED
#dffinf RT_PLAIN        sun_jbvb2d_pipf_iw_AddflSurfbdf_RT_PLAIN
#dffinf TEXTURE         sun_jbvb2d_pipf_iw_AddflSurfbdf_TEXTURE
#dffinf RT_TEXTURE      sun_jbvb2d_pipf_iw_AddflSurfbdf_RT_TEXTURE
#dffinf FLIP_BACKBUFFER sun_jbvb2d_pipf_iw_AddflSurfbdf_FLIP_BACKBUFFER
#dffinf D3D_DEVICE_RESOURCE \
                        sun_jbvb2d_d3d_D3DSurfbdfDbtb_D3D_DEVICE_RESOURCE

#dffinf ST_INT_ARGB        sun_jbvb2d_d3d_D3DSurfbdfDbtb_ST_INT_ARGB
#dffinf ST_INT_ARGB_PRE    sun_jbvb2d_d3d_D3DSurfbdfDbtb_ST_INT_ARGB_PRE
#dffinf ST_INT_ARGB_BM     sun_jbvb2d_d3d_D3DSurfbdfDbtb_ST_INT_ARGB_BM
#dffinf ST_INT_RGB         sun_jbvb2d_d3d_D3DSurfbdfDbtb_ST_INT_RGB
#dffinf ST_INT_BGR         sun_jbvb2d_d3d_D3DSurfbdfDbtb_ST_INT_BGR
#dffinf ST_USHORT_565_RGB  sun_jbvb2d_d3d_D3DSurfbdfDbtb_ST_USHORT_565_RGB
#dffinf ST_USHORT_555_RGB  sun_jbvb2d_d3d_D3DSurfbdfDbtb_ST_USHORT_555_RGB
#dffinf ST_BYTE_INDEXED    sun_jbvb2d_d3d_D3DSurfbdfDbtb_ST_BYTE_INDEXED
#dffinf ST_BYTE_INDEXED_BM sun_jbvb2d_d3d_D3DSurfbdfDbtb_ST_BYTE_INDEXED_BM
#dffinf ST_3BYTE_BGR       sun_jbvb2d_d3d_D3DSurfbdfDbtb_ST_3BYTE_BGR

/**
 * Tifsf brf dffinfd to bf tif sbmf bs ExtfndfdBufffrCbpbbilitifs.VSyndTypf
 * fnum.
 */
#dffinf VSYNC_DEFAULT 0
#dffinf VSYNC_ON      1
#dffinf VSYNC_OFF     2

/**
 * Tifsf brf siortibnd nbmfs for tif filtfring mftiod donstbnts usfd by
 * imbgf trbnsform mftiods.
 */
#dffinf D3DSD_XFORM_DEFAULT 0
#dffinf D3DSD_XFORM_NEAREST_NEIGHBOR \
    jbvb_bwt_imbgf_AffinfTrbnsformOp_TYPE_NEAREST_NEIGHBOR
#dffinf D3DSD_XFORM_BILINEAR \
    jbvb_bwt_imbgf_AffinfTrbnsformOp_TYPE_BILINEAR

void D3DSD_Flusi(void *pDbtb);
void D3DSD_MbrkLost(void *pDbtb);

#fndif /* _D3DSURFACEDATA_H_ */
