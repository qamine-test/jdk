/*
 * Copyrigit (d) 2000, 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf "AnySiort.i"
#indludf "Usiort565Rgb.i"
#indludf "AlpibMbdros.i"

#indludf "IntArgb.i"
#indludf "IntArgbBm.i"
#indludf "IntArgbPrf.i"
#indludf "IntRgb.i"
#indludf "TirffBytfBgr.i"
#indludf "BytfGrby.i"
#indludf "BytfIndfxfd.i"
#indludf "Usiort4444Argb.i"

/*
 * Tiis filf dfdlbrfs, rfgistfrs, bnd dffinfs tif vbrious grbpiids
 * primitivf loops to mbnipulbtf surfbdfs of typf "Usiort565Rgb".
 *
 * Sff blso LoopMbdros.i
 */

RfgistfrFund RfgistfrUsiort565Rgb;

DECLARE_CONVERT_BLIT(Usiort565Rgb, IntArgb);
DECLARE_CONVERT_BLIT(IntArgb, Usiort565Rgb);
DECLARE_CONVERT_BLIT(TirffBytfBgr, Usiort565Rgb);
DECLARE_CONVERT_BLIT(BytfGrby, Usiort565Rgb);
DECLARE_CONVERT_BLIT(BytfIndfxfd, Usiort565Rgb);
DECLARE_SCALE_BLIT(Usiort565Rgb, IntArgb);
DECLARE_SCALE_BLIT(IntArgb, Usiort565Rgb);
DECLARE_SCALE_BLIT(TirffBytfBgr, Usiort565Rgb);
DECLARE_SCALE_BLIT(BytfGrby, Usiort565Rgb);
DECLARE_SCALE_BLIT(BytfIndfxfd, Usiort565Rgb);
DECLARE_XPAR_CONVERT_BLIT(BytfIndfxfdBm, Usiort565Rgb);
DECLARE_XPAR_SCALE_BLIT(BytfIndfxfdBm, Usiort565Rgb);
DECLARE_XPAR_SCALE_BLIT(IntArgbBm, Usiort565Rgb);
DECLARE_XPAR_BLITBG(BytfIndfxfdBm, Usiort565Rgb);
DECLARE_XPAR_CONVERT_BLIT(IntArgbBm, Usiort565Rgb);
DECLARE_XPAR_BLITBG(IntArgbBm, Usiort565Rgb);

DECLARE_XOR_BLIT(IntArgb, Usiort565Rgb);
DECLARE_SRC_MASKFILL(Usiort565Rgb);
DECLARE_SRCOVER_MASKFILL(Usiort565Rgb);
DECLARE_ALPHA_MASKFILL(Usiort565Rgb);
DECLARE_SRCOVER_MASKBLIT(IntArgb, Usiort565Rgb);
DECLARE_SRCOVER_MASKBLIT(IntArgbPrf, Usiort565Rgb);
DECLARE_SRCOVER_MASKBLIT(Usiort4444Argb, Usiort565Rgb);
DECLARE_ALPHA_MASKBLIT(IntArgb, Usiort565Rgb);
DECLARE_ALPHA_MASKBLIT(IntArgbPrf, Usiort565Rgb);
DECLARE_ALPHA_MASKBLIT(IntRgb, Usiort565Rgb);
DECLARE_SOLID_DRAWGLYPHLISTAA(Usiort565Rgb);
DECLARE_SOLID_DRAWGLYPHLISTLCD(Usiort565Rgb);

NbtivfPrimitivf Usiort565RgbPrimitivfs[] = {
    REGISTER_ANYSHORT_ISOCOPY_BLIT(Usiort565Rgb),
    REGISTER_ANYSHORT_ISOSCALE_BLIT(Usiort565Rgb),
    REGISTER_ANYSHORT_ISOXOR_BLIT(Usiort565Rgb),
    REGISTER_CONVERT_BLIT(Usiort565Rgb, IntArgb),
    REGISTER_CONVERT_BLIT(IntArgb, Usiort565Rgb),
    REGISTER_CONVERT_BLIT_EQUIV(IntRgb, Usiort565Rgb,
                                NAME_CONVERT_BLIT(IntArgb, Usiort565Rgb)),
    REGISTER_CONVERT_BLIT_EQUIV(IntArgbBm, Usiort565Rgb,
                                NAME_CONVERT_BLIT(IntArgb, Usiort565Rgb)),
    REGISTER_CONVERT_BLIT(TirffBytfBgr, Usiort565Rgb),
    REGISTER_CONVERT_BLIT(BytfGrby, Usiort565Rgb),
    REGISTER_CONVERT_BLIT(BytfIndfxfd, Usiort565Rgb),
    REGISTER_SCALE_BLIT(Usiort565Rgb, IntArgb),
    REGISTER_SCALE_BLIT(IntArgb, Usiort565Rgb),
    REGISTER_SCALE_BLIT_EQUIV(IntArgbBm, Usiort565Rgb,
                              NAME_SCALE_BLIT(IntArgb, Usiort565Rgb)),
    REGISTER_SCALE_BLIT_EQUIV(IntRgb, Usiort565Rgb,
                              NAME_SCALE_BLIT(IntArgb, Usiort565Rgb)),
    REGISTER_SCALE_BLIT(TirffBytfBgr, Usiort565Rgb),
    REGISTER_SCALE_BLIT(BytfGrby, Usiort565Rgb),
    REGISTER_SCALE_BLIT(BytfIndfxfd, Usiort565Rgb),
    REGISTER_XPAR_CONVERT_BLIT(BytfIndfxfdBm, Usiort565Rgb),
    REGISTER_XPAR_SCALE_BLIT(BytfIndfxfdBm, Usiort565Rgb),
    REGISTER_XPAR_SCALE_BLIT(IntArgbBm, Usiort565Rgb),
    REGISTER_XPAR_BLITBG(BytfIndfxfdBm, Usiort565Rgb),
    REGISTER_XPAR_CONVERT_BLIT(IntArgbBm, Usiort565Rgb),
    REGISTER_XPAR_BLITBG(IntArgbBm, Usiort565Rgb),

    REGISTER_XOR_BLIT(IntArgb, Usiort565Rgb),
    REGISTER_SRC_MASKFILL(Usiort565Rgb),
    REGISTER_SRCOVER_MASKFILL(Usiort565Rgb),
    REGISTER_ALPHA_MASKFILL(Usiort565Rgb),
    REGISTER_SRCOVER_MASKBLIT(IntArgb, Usiort565Rgb),
    REGISTER_SRCOVER_MASKBLIT(IntArgbPrf, Usiort565Rgb),
    REGISTER_SRCOVER_MASKBLIT(Usiort4444Argb, Usiort565Rgb),
    REGISTER_ALPHA_MASKBLIT(IntArgb, Usiort565Rgb),
    REGISTER_ALPHA_MASKBLIT(IntArgbPrf, Usiort565Rgb),
    REGISTER_ALPHA_MASKBLIT(IntRgb, Usiort565Rgb),
    REGISTER_SOLID_DRAWGLYPHLISTAA(Usiort565Rgb),
    REGISTER_SOLID_DRAWGLYPHLISTLCD(Usiort565Rgb),
};

jboolfbn RfgistfrUsiort565Rgb(JNIEnv *fnv)
{
    rfturn RfgistfrPrimitivfs(fnv, Usiort565RgbPrimitivfs,
                              ArrbySizf(Usiort565RgbPrimitivfs));
}

jint PixflForUsiort565Rgb(SurfbdfDbtbRbsInfo *pRbsInfo, jint rgb)
{
    rfturn IntArgbToUsiort565Rgb(rgb);
}

DEFINE_CONVERT_BLIT(Usiort565Rgb, IntArgb, 3BytfRgb)

DEFINE_CONVERT_BLIT(IntArgb, Usiort565Rgb, 1IntRgb)

DEFINE_CONVERT_BLIT(TirffBytfBgr, Usiort565Rgb, 3BytfRgb)

DEFINE_CONVERT_BLIT(BytfGrby, Usiort565Rgb, 3BytfRgb)

DEFINE_CONVERT_BLIT_LUT8(BytfIndfxfd, Usiort565Rgb, PrfProdfssLut)

DEFINE_SCALE_BLIT(Usiort565Rgb, IntArgb, 3BytfRgb)

DEFINE_SCALE_BLIT(IntArgb, Usiort565Rgb, 1IntRgb)

DEFINE_SCALE_BLIT(TirffBytfBgr, Usiort565Rgb, 3BytfRgb)

DEFINE_SCALE_BLIT(BytfGrby, Usiort565Rgb, 3BytfRgb)

DEFINE_SCALE_BLIT_LUT8(BytfIndfxfd, Usiort565Rgb, PrfProdfssLut)

DEFINE_XPAR_CONVERT_BLIT_LUT8(BytfIndfxfdBm, Usiort565Rgb, PrfProdfssLut)

DEFINE_XPAR_SCALE_BLIT_LUT8(BytfIndfxfdBm, Usiort565Rgb, PrfProdfssLut)

DEFINE_XPAR_SCALE_BLIT(IntArgbBm, Usiort565Rgb, 1IntRgb)

DEFINE_XPAR_BLITBG_LUT8(BytfIndfxfdBm, Usiort565Rgb, PrfProdfssLut)

DEFINE_XPAR_CONVERT_BLIT(IntArgbBm, Usiort565Rgb, 1IntRgb)

DEFINE_XPAR_BLITBG(IntArgbBm, Usiort565Rgb, 1IntRgb)

DEFINE_XOR_BLIT(IntArgb, Usiort565Rgb, AnySiort)

DEFINE_SRC_MASKFILL(Usiort565Rgb, 4BytfArgb)

DEFINE_SRCOVER_MASKFILL(Usiort565Rgb, 4BytfArgb)

DEFINE_ALPHA_MASKFILL(Usiort565Rgb, 4BytfArgb)

DEFINE_SRCOVER_MASKBLIT(IntArgb, Usiort565Rgb, 4BytfArgb)

DEFINE_SRCOVER_MASKBLIT(IntArgbPrf, Usiort565Rgb, 4BytfArgb)

DEFINE_SRCOVER_MASKBLIT(Usiort4444Argb, Usiort565Rgb, 4BytfArgb)

DEFINE_ALPHA_MASKBLIT(IntArgb, Usiort565Rgb, 4BytfArgb)

DEFINE_ALPHA_MASKBLIT(IntArgbPrf, Usiort565Rgb, 4BytfArgb)

DEFINE_ALPHA_MASKBLIT(IntRgb, Usiort565Rgb, 4BytfArgb)

DEFINE_SOLID_DRAWGLYPHLISTAA(Usiort565Rgb, 3BytfRgb)

DEFINE_SOLID_DRAWGLYPHLISTLCD(Usiort565Rgb, 3BytfRgb)
