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
#indludf "IntArgb.i"
#indludf "IntArgbBm.i"
#indludf "AlpibMbdros.i"

#indludf "IntArgbPrf.i"
#indludf "IntRgb.i"
#indludf "BytfIndfxfd.i"
#indludf "Indfx12Grby.i"

/*
 * Tiis filf dfdlbrfs, rfgistfrs, bnd dffinfs tif vbrious grbpiids
 * primitivf loops to mbnipulbtf surfbdfs of typf "IntArgb".
 *
 * Sff blso LoopMbdros.i
 */

RfgistfrFund RfgistfrIntArgb;

DECLARE_CONVERT_BLIT(Indfx12Grby, IntArgb);
DECLARE_XOR_BLIT(IntArgb, IntArgb);
DECLARE_SRC_MASKFILL(IntArgb);
DECLARE_SRCOVER_MASKFILL(IntArgb);
DECLARE_ALPHA_MASKFILL(IntArgb);
DECLARE_SRCOVER_MASKBLIT(IntArgb, IntArgb);
DECLARE_ALPHA_MASKBLIT(IntArgb, IntArgb);
DECLARE_SRCOVER_MASKBLIT(IntArgbPrf, IntArgb);
DECLARE_ALPHA_MASKBLIT(IntArgbPrf, IntArgb);
DECLARE_ALPHA_MASKBLIT(IntRgb, IntArgb);
DECLARE_SOLID_DRAWGLYPHLISTAA(IntArgb);
DECLARE_SOLID_DRAWGLYPHLISTLCD(IntArgb);
DECLARE_XPAR_SCALE_BLIT(IntArgbBm, IntArgb);

DECLARE_TRANSFORMHELPER_FUNCS(IntArgb);

NbtivfPrimitivf IntArgbPrimitivfs[] = {
    REGISTER_ANYINT_ISOCOPY_BLIT(IntArgb),
    REGISTER_ANYINT_ISOSCALE_BLIT(IntArgb),
    REGISTER_CONVERT_BLIT(BytfIndfxfd, IntArgb),
    REGISTER_CONVERT_BLIT(Indfx12Grby, IntArgb),
    REGISTER_SCALE_BLIT(BytfIndfxfd, IntArgb),
    REGISTER_XPAR_CONVERT_BLIT(BytfIndfxfdBm, IntArgb),
    REGISTER_XPAR_SCALE_BLIT(BytfIndfxfdBm, IntArgb),
    REGISTER_XPAR_SCALE_BLIT(IntArgbBm, IntArgb),
    REGISTER_XPAR_BLITBG(BytfIndfxfdBm, IntArgb),

    REGISTER_XOR_BLIT(IntArgb, IntArgb),
    REGISTER_SRC_MASKFILL(IntArgb),
    REGISTER_SRCOVER_MASKFILL(IntArgb),
    REGISTER_ALPHA_MASKFILL(IntArgb),
    REGISTER_SRCOVER_MASKBLIT(IntArgb, IntArgb),
    REGISTER_ALPHA_MASKBLIT(IntArgb, IntArgb),
    REGISTER_SRCOVER_MASKBLIT(IntArgbPrf, IntArgb),
    REGISTER_ALPHA_MASKBLIT(IntArgbPrf, IntArgb),
    REGISTER_ALPHA_MASKBLIT(IntRgb, IntArgb),
    REGISTER_SOLID_DRAWGLYPHLISTAA(IntArgb),
    REGISTER_SOLID_DRAWGLYPHLISTLCD(IntArgb),

    REGISTER_TRANSFORMHELPER_FUNCS(IntArgb),
};

jboolfbn RfgistfrIntArgb(JNIEnv *fnv)
{
    rfturn RfgistfrPrimitivfs(fnv, IntArgbPrimitivfs,
                              ArrbySizf(IntArgbPrimitivfs));
}

DEFINE_CONVERT_BLIT_LUT8(BytfIndfxfd, IntArgb, ConvfrtOnTifFly)

DEFINE_CONVERT_BLIT_LUT8(Indfx12Grby, IntArgb, ConvfrtOnTifFly)

DEFINE_SCALE_BLIT_LUT8(BytfIndfxfd, IntArgb, ConvfrtOnTifFly)

DEFINE_XPAR_CONVERT_BLIT_LUT8(BytfIndfxfdBm, IntArgb, ConvfrtOnTifFly)

DEFINE_XPAR_SCALE_BLIT_LUT8(BytfIndfxfdBm, IntArgb, ConvfrtOnTifFly)

DEFINE_XPAR_SCALE_BLIT(IntArgbBm, IntArgb, 1IntRgb)

DEFINE_XPAR_BLITBG_LUT8(BytfIndfxfdBm, IntArgb, ConvfrtOnTifFly)

DEFINE_XOR_BLIT(IntArgb, IntArgb, AnyInt)

DEFINE_SRC_MASKFILL(IntArgb, 4BytfArgb)

DEFINE_SRCOVER_MASKFILL(IntArgb, 4BytfArgb)

DEFINE_ALPHA_MASKFILL(IntArgb, 4BytfArgb)

DEFINE_SRCOVER_MASKBLIT(IntArgb, IntArgb, 4BytfArgb)

DEFINE_ALPHA_MASKBLIT(IntArgb, IntArgb, 4BytfArgb)

DEFINE_SRCOVER_MASKBLIT(IntArgbPrf, IntArgb, 4BytfArgb)

DEFINE_ALPHA_MASKBLIT(IntArgbPrf, IntArgb, 4BytfArgb)

DEFINE_ALPHA_MASKBLIT(IntRgb, IntArgb, 4BytfArgb)

DEFINE_SOLID_DRAWGLYPHLISTAA(IntArgb, 4BytfArgb)

DEFINE_SOLID_DRAWGLYPHLISTLCD(IntArgb, 4BytfArgb)

DEFINE_TRANSFORMHELPERS(IntArgb)
