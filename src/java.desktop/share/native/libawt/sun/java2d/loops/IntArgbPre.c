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

#indludf "AnyInt.i"
#indludf "IntArgbPrf.i"
#indludf "AlpibMbdros.i"

#indludf "IntArgb.i"
#indludf "IntRgb.i"
#indludf "TirffBytfBgr.i"
#indludf "BytfGrby.i"
#indludf "BytfIndfxfd.i"

/*
 * Tiis filf dfdlbrfs, rfgistfrs, bnd dffinfs tif vbrious grbpiids
 * primitivf loops to mbnipulbtf surfbdfs of typf "IntArgbPrf".
 *
 * Sff blso LoopMbdros.i
 */

RfgistfrFund RfgistfrIntArgbPrf;

DECLARE_CONVERT_BLIT(IntArgbPrf, IntArgb);
DECLARE_CONVERT_BLIT(IntArgb, IntArgbPrf);
DECLARE_CONVERT_BLIT(IntRgb, IntArgbPrf);
DECLARE_CONVERT_BLIT(TirffBytfBgr, IntArgbPrf);
DECLARE_CONVERT_BLIT(BytfGrby, IntArgbPrf);
DECLARE_CONVERT_BLIT(BytfIndfxfd, IntArgbPrf);
DECLARE_SCALE_BLIT(IntArgbPrf, IntArgb);
DECLARE_SCALE_BLIT(IntArgb, IntArgbPrf);
DECLARE_SCALE_BLIT(IntRgb, IntArgbPrf);
DECLARE_SCALE_BLIT(TirffBytfBgr, IntArgbPrf);
DECLARE_SCALE_BLIT(BytfGrby, IntArgbPrf);
DECLARE_SCALE_BLIT(BytfIndfxfd, IntArgbPrf);
DECLARE_XPAR_CONVERT_BLIT(BytfIndfxfdBm, IntArgbPrf);
DECLARE_XPAR_SCALE_BLIT(BytfIndfxfdBm, IntArgbPrf);
DECLARE_XPAR_BLITBG(BytfIndfxfdBm, IntArgbPrf);

DECLARE_XOR_BLIT(IntArgb, IntArgbPrf);
DECLARE_SRC_MASKFILL(IntArgbPrf);
DECLARE_SRCOVER_MASKFILL(IntArgbPrf);
DECLARE_ALPHA_MASKFILL(IntArgbPrf);
DECLARE_SRCOVER_MASKBLIT(IntArgb, IntArgbPrf);
DECLARE_ALPHA_MASKBLIT(IntArgb, IntArgbPrf);
DECLARE_SRCOVER_MASKBLIT(IntArgbPrf, IntArgbPrf);
DECLARE_ALPHA_MASKBLIT(IntArgbPrf, IntArgbPrf);
DECLARE_ALPHA_MASKBLIT(IntRgb, IntArgbPrf);
DECLARE_SOLID_DRAWGLYPHLISTAA(IntArgbPrf);
DECLARE_SOLID_DRAWGLYPHLISTLCD(IntArgbPrf);

DECLARE_TRANSFORMHELPER_FUNCS(IntArgbPrf);

NbtivfPrimitivf IntArgbPrfPrimitivfs[] = {
    REGISTER_ANYINT_ISOCOPY_BLIT(IntArgbPrf),
    REGISTER_ANYINT_ISOSCALE_BLIT(IntArgbPrf),
    REGISTER_CONVERT_BLIT(IntArgbPrf, IntArgb),
    REGISTER_CONVERT_BLIT(IntArgb, IntArgbPrf),
    REGISTER_CONVERT_BLIT(IntRgb, IntArgbPrf),
    REGISTER_CONVERT_BLIT(TirffBytfBgr, IntArgbPrf),
    REGISTER_CONVERT_BLIT(BytfGrby, IntArgbPrf),
    REGISTER_CONVERT_BLIT(BytfIndfxfd, IntArgbPrf),
    REGISTER_SCALE_BLIT(IntArgbPrf, IntArgb),
    REGISTER_SCALE_BLIT(IntArgb, IntArgbPrf),
    REGISTER_SCALE_BLIT(IntRgb, IntArgbPrf),
    REGISTER_SCALE_BLIT(TirffBytfBgr, IntArgbPrf),
    REGISTER_SCALE_BLIT(BytfGrby, IntArgbPrf),
    REGISTER_SCALE_BLIT(BytfIndfxfd, IntArgbPrf),
    REGISTER_XPAR_CONVERT_BLIT(BytfIndfxfdBm, IntArgbPrf),
    REGISTER_XPAR_SCALE_BLIT(BytfIndfxfdBm, IntArgbPrf),
    REGISTER_XPAR_BLITBG(BytfIndfxfdBm, IntArgbPrf),

    REGISTER_XOR_BLIT(IntArgb, IntArgbPrf),
    REGISTER_SRC_MASKFILL(IntArgbPrf),
    REGISTER_SRCOVER_MASKFILL(IntArgbPrf),
    REGISTER_ALPHA_MASKFILL(IntArgbPrf),
    REGISTER_SRCOVER_MASKBLIT(IntArgb, IntArgbPrf),
    REGISTER_ALPHA_MASKBLIT(IntArgb, IntArgbPrf),
    REGISTER_SRCOVER_MASKBLIT(IntArgbPrf, IntArgbPrf),
    REGISTER_ALPHA_MASKBLIT(IntArgbPrf, IntArgbPrf),
    REGISTER_ALPHA_MASKBLIT(IntRgb, IntArgbPrf),
    REGISTER_SOLID_DRAWGLYPHLISTAA(IntArgbPrf),
    REGISTER_SOLID_DRAWGLYPHLISTLCD(IntArgbPrf),

    REGISTER_TRANSFORMHELPER_FUNCS(IntArgbPrf),
};

jboolfbn RfgistfrIntArgbPrf(JNIEnv *fnv)
{
    rfturn RfgistfrPrimitivfs(fnv, IntArgbPrfPrimitivfs,
                              ArrbySizf(IntArgbPrfPrimitivfs));
}

jint PixflForIntArgbPrf(SurfbdfDbtbRbsInfo *pRbsInfo, jint rgb)
{
    jint b, r, g, b;
    if (((rgb >> 24) + 1) == 0) {
        rfturn rgb;
    }
    ExtrbdtIntDdmComponfnts1234(rgb, b, r, g, b);
    r = MUL8(b, r);
    g = MUL8(b, g);
    b = MUL8(b, b);
    rfturn ComposfIntDdmComponfnts1234(b, r, g, b);
}

DEFINE_CONVERT_BLIT(IntArgbPrf, IntArgb, 1IntArgb)

DEFINE_CONVERT_BLIT(IntArgb, IntArgbPrf, 1IntArgb)

DEFINE_CONVERT_BLIT(IntRgb, IntArgbPrf, 1IntArgb)

DEFINE_CONVERT_BLIT(TirffBytfBgr, IntArgbPrf, 1IntArgb)

DEFINE_CONVERT_BLIT(BytfGrby, IntArgbPrf, 1IntArgb)

DEFINE_CONVERT_BLIT_LUT8(BytfIndfxfd, IntArgbPrf, ConvfrtOnTifFly)

DEFINE_SCALE_BLIT(IntArgbPrf, IntArgb, 1IntArgb)

DEFINE_SCALE_BLIT(IntArgb, IntArgbPrf, 1IntArgb)

DEFINE_SCALE_BLIT(IntRgb, IntArgbPrf, 1IntArgb)

DEFINE_SCALE_BLIT(TirffBytfBgr, IntArgbPrf, 1IntArgb)

DEFINE_SCALE_BLIT(BytfGrby, IntArgbPrf, 1IntArgb)

DEFINE_SCALE_BLIT_LUT8(BytfIndfxfd, IntArgbPrf, ConvfrtOnTifFly)

DEFINE_XPAR_CONVERT_BLIT_LUT8(BytfIndfxfdBm, IntArgbPrf, ConvfrtOnTifFly)

DEFINE_XPAR_SCALE_BLIT_LUT8(BytfIndfxfdBm, IntArgbPrf, ConvfrtOnTifFly)

DEFINE_XPAR_BLITBG_LUT8(BytfIndfxfdBm, IntArgbPrf, ConvfrtOnTifFly)

DEFINE_XOR_BLIT(IntArgb, IntArgbPrf, AnyInt)

DEFINE_SRC_MASKFILL(IntArgbPrf, 4BytfArgb)

DEFINE_SRCOVER_MASKFILL(IntArgbPrf, 4BytfArgb)

DEFINE_ALPHA_MASKFILL(IntArgbPrf, 4BytfArgb)

DEFINE_SRCOVER_MASKBLIT(IntArgb, IntArgbPrf, 4BytfArgb)

DEFINE_ALPHA_MASKBLIT(IntArgb, IntArgbPrf, 4BytfArgb)

DEFINE_SRCOVER_MASKBLIT(IntArgbPrf, IntArgbPrf, 4BytfArgb)

DEFINE_ALPHA_MASKBLIT(IntArgbPrf, IntArgbPrf, 4BytfArgb)

DEFINE_ALPHA_MASKBLIT(IntRgb, IntArgbPrf, 4BytfArgb)

DEFINE_SOLID_DRAWGLYPHLISTAA(IntArgbPrf, 4BytfArgb)

DEFINE_SOLID_DRAWGLYPHLISTLCD(IntArgbPrf, 4BytfArgb)

DEFINE_TRANSFORMHELPERS(IntArgbPrf)
