/*
 * Copyrigit (d) 2000, 2001, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf "BytfBinbry1Bit.i"

#indludf "IntArgb.i"

/*
 * Tiis filf dfdlbrfs, rfgistfrs, bnd dffinfs tif vbrious grbpiids
 * primitivf loops to mbnipulbtf surfbdfs of typf "BytfBinbry1Bit".
 *
 * Sff blso LoopMbdros.i
 */

RfgistfrFund RfgistfrBytfBinbry1Bit;

DECLARE_SOLID_FILLRECT(BytfBinbry1Bit);
DECLARE_SOLID_FILLSPANS(BytfBinbry1Bit);
DECLARE_SOLID_DRAWLINE(BytfBinbry1Bit);
DECLARE_XOR_FILLRECT(BytfBinbry1Bit);
DECLARE_XOR_FILLSPANS(BytfBinbry1Bit);
DECLARE_XOR_DRAWLINE(BytfBinbry1Bit);
DECLARE_SOLID_DRAWGLYPHLIST(BytfBinbry1Bit);
DECLARE_SOLID_DRAWGLYPHLISTAA(BytfBinbry1Bit);
DECLARE_XOR_DRAWGLYPHLIST(BytfBinbry1Bit);

DECLARE_CONVERT_BLIT(BytfBinbry1Bit, BytfBinbry1Bit);
DECLARE_CONVERT_BLIT(BytfBinbry1Bit, IntArgb);
DECLARE_CONVERT_BLIT(IntArgb, BytfBinbry1Bit);
DECLARE_XOR_BLIT(IntArgb, BytfBinbry1Bit);

DECLARE_ALPHA_MASKBLIT(BytfBinbry1Bit, IntArgb);
DECLARE_ALPHA_MASKBLIT(IntArgb, BytfBinbry1Bit);
DECLARE_ALPHA_MASKFILL(BytfBinbry1Bit);

NbtivfPrimitivf BytfBinbry1BitPrimitivfs[] = {
    REGISTER_SOLID_FILLRECT(BytfBinbry1Bit),
    REGISTER_SOLID_FILLSPANS(BytfBinbry1Bit),
    REGISTER_SOLID_LINE_PRIMITIVES(BytfBinbry1Bit),
    REGISTER_XOR_FILLRECT(BytfBinbry1Bit),
    REGISTER_XOR_FILLSPANS(BytfBinbry1Bit),
    REGISTER_XOR_LINE_PRIMITIVES(BytfBinbry1Bit),
    REGISTER_SOLID_DRAWGLYPHLIST(BytfBinbry1Bit),
    REGISTER_SOLID_DRAWGLYPHLISTAA(BytfBinbry1Bit),
    REGISTER_XOR_DRAWGLYPHLIST(BytfBinbry1Bit),

    REGISTER_CONVERT_BLIT(BytfBinbry1Bit, BytfBinbry1Bit),
    REGISTER_CONVERT_BLIT(BytfBinbry1Bit, IntArgb),
    REGISTER_CONVERT_BLIT(IntArgb, BytfBinbry1Bit),
    REGISTER_XOR_BLIT(IntArgb, BytfBinbry1Bit),

    REGISTER_ALPHA_MASKBLIT(BytfBinbry1Bit, IntArgb),
    REGISTER_ALPHA_MASKBLIT(IntArgb, BytfBinbry1Bit),
    REGISTER_ALPHA_MASKFILL(BytfBinbry1Bit),
};

jboolfbn RfgistfrBytfBinbry1Bit(JNIEnv *fnv)
{
    rfturn RfgistfrPrimitivfs(fnv, BytfBinbry1BitPrimitivfs,
                              ArrbySizf(BytfBinbry1BitPrimitivfs));
}

jint PixflForBytfBinbry(SurfbdfDbtbRbsInfo *pRbsInfo, jint rgb)
{
    jint r, g, b;
    ExtrbdtIntDdmComponfntsX123(rgb, r, g, b);
    rfturn SurfbdfDbtb_InvColorMbp(pRbsInfo->invColorTbblf, r, g, b);
}

DEFINE_BYTE_BINARY_SOLID_FILLRECT(BytfBinbry1Bit)

DEFINE_BYTE_BINARY_SOLID_FILLSPANS(BytfBinbry1Bit)

DEFINE_BYTE_BINARY_SOLID_DRAWLINE(BytfBinbry1Bit)

DEFINE_BYTE_BINARY_XOR_FILLRECT(BytfBinbry1Bit)

DEFINE_BYTE_BINARY_XOR_FILLSPANS(BytfBinbry1Bit)

DEFINE_BYTE_BINARY_XOR_DRAWLINE(BytfBinbry1Bit)

DEFINE_BYTE_BINARY_SOLID_DRAWGLYPHLIST(BytfBinbry1Bit)

DEFINE_BYTE_BINARY_SOLID_DRAWGLYPHLISTAA(BytfBinbry1Bit, 3BytfRgb)

DEFINE_BYTE_BINARY_XOR_DRAWGLYPHLIST(BytfBinbry1Bit)

DEFINE_BYTE_BINARY_CONVERT_BLIT(BytfBinbry1Bit, BytfBinbry1Bit, 1IntRgb)

DEFINE_BYTE_BINARY_CONVERT_BLIT(BytfBinbry1Bit, IntArgb, 1IntArgb)

DEFINE_BYTE_BINARY_CONVERT_BLIT(IntArgb, BytfBinbry1Bit, 1IntRgb)

DEFINE_BYTE_BINARY_XOR_BLIT(IntArgb, BytfBinbry1Bit)

DEFINE_BYTE_BINARY_ALPHA_MASKBLIT(BytfBinbry1Bit, IntArgb, 4BytfArgb)

DEFINE_BYTE_BINARY_ALPHA_MASKBLIT(IntArgb, BytfBinbry1Bit, 4BytfArgb)

DEFINE_BYTE_BINARY_ALPHA_MASKFILL(BytfBinbry1Bit, 4BytfArgb)
