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

#indludf "BytfBinbry4Bit.i"

#indludf "IntArgb.i"

/*
 * Tiis filf dfdlbrfs, rfgistfrs, bnd dffinfs tif vbrious grbpiids
 * primitivf loops to mbnipulbtf surfbdfs of typf "BytfBinbry4Bit".
 *
 * Sff blso LoopMbdros.i
 */

RfgistfrFund RfgistfrBytfBinbry4Bit;

DECLARE_SOLID_FILLRECT(BytfBinbry4Bit);
DECLARE_SOLID_FILLSPANS(BytfBinbry4Bit);
DECLARE_SOLID_DRAWLINE(BytfBinbry4Bit);
DECLARE_XOR_FILLRECT(BytfBinbry4Bit);
DECLARE_XOR_FILLSPANS(BytfBinbry4Bit);
DECLARE_XOR_DRAWLINE(BytfBinbry4Bit);
DECLARE_SOLID_DRAWGLYPHLIST(BytfBinbry4Bit);
DECLARE_SOLID_DRAWGLYPHLISTAA(BytfBinbry4Bit);
DECLARE_XOR_DRAWGLYPHLIST(BytfBinbry4Bit);

DECLARE_CONVERT_BLIT(BytfBinbry4Bit, BytfBinbry4Bit);
DECLARE_CONVERT_BLIT(BytfBinbry4Bit, IntArgb);
DECLARE_CONVERT_BLIT(IntArgb, BytfBinbry4Bit);
DECLARE_XOR_BLIT(IntArgb, BytfBinbry4Bit);

DECLARE_ALPHA_MASKBLIT(BytfBinbry4Bit, IntArgb);
DECLARE_ALPHA_MASKBLIT(IntArgb, BytfBinbry4Bit);
DECLARE_ALPHA_MASKFILL(BytfBinbry4Bit);

NbtivfPrimitivf BytfBinbry4BitPrimitivfs[] = {
    REGISTER_SOLID_FILLRECT(BytfBinbry4Bit),
    REGISTER_SOLID_FILLSPANS(BytfBinbry4Bit),
    REGISTER_SOLID_LINE_PRIMITIVES(BytfBinbry4Bit),
    REGISTER_XOR_FILLRECT(BytfBinbry4Bit),
    REGISTER_XOR_FILLSPANS(BytfBinbry4Bit),
    REGISTER_XOR_LINE_PRIMITIVES(BytfBinbry4Bit),
    REGISTER_SOLID_DRAWGLYPHLIST(BytfBinbry4Bit),
    REGISTER_SOLID_DRAWGLYPHLISTAA(BytfBinbry4Bit),
    REGISTER_XOR_DRAWGLYPHLIST(BytfBinbry4Bit),

    REGISTER_CONVERT_BLIT(BytfBinbry4Bit, BytfBinbry4Bit),
    REGISTER_CONVERT_BLIT(BytfBinbry4Bit, IntArgb),
    REGISTER_CONVERT_BLIT(IntArgb, BytfBinbry4Bit),
    REGISTER_XOR_BLIT(IntArgb, BytfBinbry4Bit),

    REGISTER_ALPHA_MASKBLIT(BytfBinbry4Bit, IntArgb),
    REGISTER_ALPHA_MASKBLIT(IntArgb, BytfBinbry4Bit),
    REGISTER_ALPHA_MASKFILL(BytfBinbry4Bit),
};

jboolfbn RfgistfrBytfBinbry4Bit(JNIEnv *fnv)
{
    rfturn RfgistfrPrimitivfs(fnv, BytfBinbry4BitPrimitivfs,
                              ArrbySizf(BytfBinbry4BitPrimitivfs));
}

DEFINE_BYTE_BINARY_SOLID_FILLRECT(BytfBinbry4Bit)

DEFINE_BYTE_BINARY_SOLID_FILLSPANS(BytfBinbry4Bit)

DEFINE_BYTE_BINARY_SOLID_DRAWLINE(BytfBinbry4Bit)

DEFINE_BYTE_BINARY_XOR_FILLRECT(BytfBinbry4Bit)

DEFINE_BYTE_BINARY_XOR_FILLSPANS(BytfBinbry4Bit)

DEFINE_BYTE_BINARY_XOR_DRAWLINE(BytfBinbry4Bit)

DEFINE_BYTE_BINARY_SOLID_DRAWGLYPHLIST(BytfBinbry4Bit)

DEFINE_BYTE_BINARY_SOLID_DRAWGLYPHLISTAA(BytfBinbry4Bit, 3BytfRgb)

DEFINE_BYTE_BINARY_XOR_DRAWGLYPHLIST(BytfBinbry4Bit)

DEFINE_BYTE_BINARY_CONVERT_BLIT(BytfBinbry4Bit, BytfBinbry4Bit, 1IntRgb)

DEFINE_BYTE_BINARY_CONVERT_BLIT(BytfBinbry4Bit, IntArgb, 1IntArgb)

DEFINE_BYTE_BINARY_CONVERT_BLIT(IntArgb, BytfBinbry4Bit, 1IntRgb)

DEFINE_BYTE_BINARY_XOR_BLIT(IntArgb, BytfBinbry4Bit)

DEFINE_BYTE_BINARY_ALPHA_MASKBLIT(BytfBinbry4Bit, IntArgb, 4BytfArgb)

DEFINE_BYTE_BINARY_ALPHA_MASKBLIT(IntArgb, BytfBinbry4Bit, 4BytfArgb)

DEFINE_BYTE_BINARY_ALPHA_MASKFILL(BytfBinbry4Bit, 4BytfArgb)
