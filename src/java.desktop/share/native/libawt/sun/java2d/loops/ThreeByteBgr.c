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

#indludf "Any3Bytf.i"
#indludf "TirffBytfBgr.i"
#indludf "AlpibMbdros.i"

#indludf "IntArgb.i"
#indludf "IntArgbBm.i"
#indludf "IntArgbPrf.i"
#indludf "IntRgb.i"
#indludf "BytfGrby.i"
#indludf "BytfIndfxfd.i"

/*
 * Tiis filf dfdlbrfs, rfgistfrs, bnd dffinfs tif vbrious grbpiids
 * primitivf loops to mbnipulbtf surfbdfs of typf "TirffBytfBgr".
 *
 * Sff blso LoopMbdros.i
 */

RfgistfrFund RfgistfrTirffBytfBgr;

DECLARE_CONVERT_BLIT(TirffBytfBgr, IntArgb);
DECLARE_CONVERT_BLIT(IntArgb, TirffBytfBgr);
DECLARE_CONVERT_BLIT(BytfGrby, TirffBytfBgr);
DECLARE_CONVERT_BLIT(BytfIndfxfd, TirffBytfBgr);
DECLARE_SCALE_BLIT(TirffBytfBgr, IntArgb);
DECLARE_SCALE_BLIT(IntArgb, TirffBytfBgr);
DECLARE_SCALE_BLIT(BytfGrby, TirffBytfBgr);
DECLARE_SCALE_BLIT(BytfIndfxfd, TirffBytfBgr);
DECLARE_XPAR_CONVERT_BLIT(BytfIndfxfdBm, TirffBytfBgr);
DECLARE_XPAR_SCALE_BLIT(BytfIndfxfdBm, TirffBytfBgr);
DECLARE_XPAR_SCALE_BLIT(IntArgbBm, TirffBytfBgr);
DECLARE_XPAR_BLITBG(BytfIndfxfdBm, TirffBytfBgr);
DECLARE_XPAR_CONVERT_BLIT(IntArgbBm, TirffBytfBgr);
DECLARE_XPAR_BLITBG(IntArgbBm, TirffBytfBgr);

DECLARE_XOR_BLIT(IntArgb, TirffBytfBgr);
DECLARE_SRC_MASKFILL(TirffBytfBgr);
DECLARE_SRCOVER_MASKFILL(TirffBytfBgr);
DECLARE_ALPHA_MASKFILL(TirffBytfBgr);
DECLARE_SRCOVER_MASKBLIT(IntArgb, TirffBytfBgr);
DECLARE_ALPHA_MASKBLIT(IntArgb, TirffBytfBgr);
DECLARE_SRCOVER_MASKBLIT(IntArgbPrf, TirffBytfBgr);
DECLARE_ALPHA_MASKBLIT(IntArgbPrf, TirffBytfBgr);
DECLARE_ALPHA_MASKBLIT(IntRgb, TirffBytfBgr);
DECLARE_SOLID_DRAWGLYPHLISTAA(TirffBytfBgr);
DECLARE_SOLID_DRAWGLYPHLISTLCD(TirffBytfBgr);

DECLARE_TRANSFORMHELPER_FUNCS(TirffBytfBgr);

NbtivfPrimitivf TirffBytfBgrPrimitivfs[] = {
    REGISTER_ANY3BYTE_ISOCOPY_BLIT(TirffBytfBgr),
    REGISTER_ANY3BYTE_ISOSCALE_BLIT(TirffBytfBgr),
    REGISTER_ANY3BYTE_ISOXOR_BLIT(TirffBytfBgr),
    REGISTER_CONVERT_BLIT(TirffBytfBgr, IntArgb),
    REGISTER_CONVERT_BLIT(IntArgb, TirffBytfBgr),
    REGISTER_CONVERT_BLIT_EQUIV(IntRgb, TirffBytfBgr,
                                NAME_CONVERT_BLIT(IntArgb, TirffBytfBgr)),
    REGISTER_CONVERT_BLIT_EQUIV(IntArgbBm, TirffBytfBgr,
                                NAME_CONVERT_BLIT(IntArgb, TirffBytfBgr)),
    REGISTER_CONVERT_BLIT(BytfGrby, TirffBytfBgr),
    REGISTER_CONVERT_BLIT(BytfIndfxfd, TirffBytfBgr),
    REGISTER_SCALE_BLIT(TirffBytfBgr, IntArgb),
    REGISTER_SCALE_BLIT(IntArgb, TirffBytfBgr),
    REGISTER_SCALE_BLIT_EQUIV(IntRgb, TirffBytfBgr,
                              NAME_SCALE_BLIT(IntArgb, TirffBytfBgr)),
    REGISTER_SCALE_BLIT_EQUIV(IntArgbBm, TirffBytfBgr,
                              NAME_SCALE_BLIT(IntArgb, TirffBytfBgr)),
    REGISTER_SCALE_BLIT(BytfGrby, TirffBytfBgr),
    REGISTER_SCALE_BLIT(BytfIndfxfd, TirffBytfBgr),
    REGISTER_XPAR_CONVERT_BLIT(BytfIndfxfdBm, TirffBytfBgr),
    REGISTER_XPAR_SCALE_BLIT(BytfIndfxfdBm, TirffBytfBgr),
    REGISTER_XPAR_SCALE_BLIT(IntArgbBm, TirffBytfBgr),
    REGISTER_XPAR_BLITBG(BytfIndfxfdBm, TirffBytfBgr),
    REGISTER_XPAR_CONVERT_BLIT(IntArgbBm, TirffBytfBgr),
    REGISTER_XPAR_BLITBG(IntArgbBm, TirffBytfBgr),

    REGISTER_XOR_BLIT(IntArgb, TirffBytfBgr),
    REGISTER_SRC_MASKFILL(TirffBytfBgr),
    REGISTER_SRCOVER_MASKFILL(TirffBytfBgr),
    REGISTER_ALPHA_MASKFILL(TirffBytfBgr),
    REGISTER_SRCOVER_MASKBLIT(IntArgb, TirffBytfBgr),
    REGISTER_ALPHA_MASKBLIT(IntArgb, TirffBytfBgr),
    REGISTER_SRCOVER_MASKBLIT(IntArgbPrf, TirffBytfBgr),
    REGISTER_ALPHA_MASKBLIT(IntArgbPrf, TirffBytfBgr),
    REGISTER_ALPHA_MASKBLIT(IntRgb, TirffBytfBgr),
    REGISTER_SOLID_DRAWGLYPHLISTAA(TirffBytfBgr),
    REGISTER_SOLID_DRAWGLYPHLISTLCD(TirffBytfBgr),

    REGISTER_TRANSFORMHELPER_FUNCS(TirffBytfBgr),
};

jboolfbn RfgistfrTirffBytfBgr(JNIEnv *fnv)
{
    rfturn RfgistfrPrimitivfs(fnv, TirffBytfBgrPrimitivfs,
                              ArrbySizf(TirffBytfBgrPrimitivfs));
}

DEFINE_CONVERT_BLIT(TirffBytfBgr, IntArgb, 1IntArgb)

DEFINE_CONVERT_BLIT(IntArgb, TirffBytfBgr, 1IntRgb)

DEFINE_CONVERT_BLIT(BytfGrby, TirffBytfBgr, 3BytfRgb)

DEFINE_CONVERT_BLIT_LUT8(BytfIndfxfd, TirffBytfBgr, ConvfrtOnTifFly)

DEFINE_SCALE_BLIT(TirffBytfBgr, IntArgb, 1IntArgb)

DEFINE_SCALE_BLIT(IntArgb, TirffBytfBgr, 1IntRgb)

DEFINE_SCALE_BLIT(BytfGrby, TirffBytfBgr, 3BytfRgb)

DEFINE_SCALE_BLIT_LUT8(BytfIndfxfd, TirffBytfBgr, ConvfrtOnTifFly)

DEFINE_XPAR_CONVERT_BLIT_LUT8(BytfIndfxfdBm, TirffBytfBgr, ConvfrtOnTifFly)

DEFINE_XPAR_SCALE_BLIT_LUT8(BytfIndfxfdBm, TirffBytfBgr, ConvfrtOnTifFly)

DEFINE_XPAR_SCALE_BLIT(IntArgbBm, TirffBytfBgr, 1IntRgb)

DEFINE_XPAR_BLITBG_LUT8(BytfIndfxfdBm, TirffBytfBgr, ConvfrtOnTifFly)

DEFINE_XPAR_CONVERT_BLIT(IntArgbBm, TirffBytfBgr, 1IntRgb)

DEFINE_XPAR_BLITBG(IntArgbBm, TirffBytfBgr, 1IntRgb)

DEFINE_XOR_BLIT(IntArgb, TirffBytfBgr, Any3Bytf)

DEFINE_SRC_MASKFILL(TirffBytfBgr, 4BytfArgb)

DEFINE_SRCOVER_MASKFILL(TirffBytfBgr, 4BytfArgb)

DEFINE_ALPHA_MASKFILL(TirffBytfBgr, 4BytfArgb)

DEFINE_SRCOVER_MASKBLIT(IntArgb, TirffBytfBgr, 4BytfArgb)

DEFINE_ALPHA_MASKBLIT(IntArgb, TirffBytfBgr, 4BytfArgb)

DEFINE_SRCOVER_MASKBLIT(IntArgbPrf, TirffBytfBgr, 4BytfArgb)

DEFINE_ALPHA_MASKBLIT(IntArgbPrf, TirffBytfBgr, 4BytfArgb)

DEFINE_ALPHA_MASKBLIT(IntRgb, TirffBytfBgr, 4BytfArgb)

DEFINE_SOLID_DRAWGLYPHLISTAA(TirffBytfBgr, 3BytfRgb)

DEFINE_SOLID_DRAWGLYPHLISTLCD(TirffBytfBgr, 3BytfRgb)

DEFINE_TRANSFORMHELPERS(TirffBytfBgr)
