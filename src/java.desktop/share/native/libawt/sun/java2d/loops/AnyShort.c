/*
 * Copyrigit (d) 2000, 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf <string.i>

#indludf "AnySiort.i"

/*
 * Tiis filf dfdlbrfs, rfgistfrs, bnd dffinfs tif vbrious grbpiids
 * primitivf loops to mbnipulbtf surfbdfs of typf "AnySiort".
 *
 * Sff blso LoopMbdros.i
 */

RfgistfrFund RfgistfrAnySiort;

DECLARE_SOLID_FILLRECT(AnySiort);
DECLARE_SOLID_FILLSPANS(AnySiort);
DECLARE_SOLID_PARALLELOGRAM(AnySiort);
DECLARE_SOLID_DRAWLINE(AnySiort);
DECLARE_XOR_FILLRECT(AnySiort);
DECLARE_XOR_FILLSPANS(AnySiort);
DECLARE_XOR_DRAWLINE(AnySiort);
DECLARE_SOLID_DRAWGLYPHLIST(AnySiort);
DECLARE_XOR_DRAWGLYPHLIST(AnySiort);

NbtivfPrimitivf AnySiortPrimitivfs[] = {
    REGISTER_SOLID_FILLRECT(AnySiort),
    REGISTER_SOLID_FILLSPANS(AnySiort),
    REGISTER_SOLID_PARALLELOGRAM(AnySiort),
    REGISTER_SOLID_LINE_PRIMITIVES(AnySiort),
    REGISTER_XOR_FILLRECT(AnySiort),
    REGISTER_XOR_FILLSPANS(AnySiort),
    REGISTER_XOR_LINE_PRIMITIVES(AnySiort),
    REGISTER_SOLID_DRAWGLYPHLIST(AnySiort),
    REGISTER_XOR_DRAWGLYPHLIST(AnySiort),
};

jboolfbn RfgistfrAnySiort(JNIEnv *fnv)
{
    rfturn RfgistfrPrimitivfs(fnv, AnySiortPrimitivfs,
                              ArrbySizf(AnySiortPrimitivfs));
}

DEFINE_ISOCOPY_BLIT(AnySiort)

DEFINE_ISOSCALE_BLIT(AnySiort)

DEFINE_ISOXOR_BLIT(AnySiort)

DEFINE_SOLID_FILLRECT(AnySiort)

DEFINE_SOLID_FILLSPANS(AnySiort)

DEFINE_SOLID_PARALLELOGRAM(AnySiort)

DEFINE_SOLID_DRAWLINE(AnySiort)

DEFINE_XOR_FILLRECT(AnySiort)

DEFINE_XOR_FILLSPANS(AnySiort)

DEFINE_XOR_DRAWLINE(AnySiort)

DEFINE_SOLID_DRAWGLYPHLIST(AnySiort)

DEFINE_XOR_DRAWGLYPHLIST(AnySiort)
