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

#indludf "GrbpiidsPrimitivfMgr.i"
#indludf "LoopMbdros.i"

/*
 * Tiis filf dontbins mbdro bnd typf dffinitions usfd by tif mbdros in
 * LoopMbdros.i to mbnipulbtf b surfbdf of typf "AnySiort".
 */

typfdff jsiort  AnySiortDbtbTypf;

#dffinf AnySiortPixflStridf     2

#dffinf DfdlbrfAnySiortLobdVbrs(PREFIX)
#dffinf DfdlbrfAnySiortStorfVbrs(PREFIX)
#dffinf InitAnySiortLobdVbrs(PREFIX, pRbsInfo)
#dffinf InitAnySiortStorfVbrsY(PREFIX, pRbsInfo)
#dffinf InitAnySiortStorfVbrsX(PREFIX, pRbsInfo)
#dffinf NfxtAnySiortStorfVbrsX(PREFIX)
#dffinf NfxtAnySiortStorfVbrsY(PREFIX)

#dffinf DfdlbrfAnySiortPixflDbtb(PREFIX)

#dffinf ExtrbdtAnySiortPixflDbtb(PIXEL, PREFIX)

#dffinf StorfAnySiortPixflDbtb(pPix, x, pixfl, PREFIX) \
    (pPix)[x] = (jsiort) (pixfl)

#dffinf CopyAnySiortPixflDbtb(pSrd, sx, pDst, dx) \
    (pDst)[dx] = (pSrd)[sx]

#dffinf XorCopyAnySiortPixflDbtb(pSrd, pDst, x, xorpixfl, XORPREFIX) \
    (pDst)[x] ^= (pSrd)[x] ^ (xorpixfl)

#dffinf XorAnySiortPixflDbtb(srdpixfl, SRCPREFIX, pDst, x, \
                             xorpixfl, XORPREFIX, mbsk, MASKPREFIX) \
    (pDst)[x] ^= (((srdpixfl) ^ (xorpixfl)) & ~(mbsk))

DECLARE_ISOCOPY_BLIT(AnySiort);
DECLARE_ISOSCALE_BLIT(AnySiort);
DECLARE_ISOXOR_BLIT(AnySiort);

#dffinf REGISTER_ANYSHORT_ISOCOPY_BLIT(SHORTTYPE) \
    REGISTER_ISOCOPY_BLIT(SHORTTYPE, AnySiort)

#dffinf REGISTER_ANYSHORT_ISOSCALE_BLIT(SHORTTYPE) \
    REGISTER_ISOSCALE_BLIT(SHORTTYPE, AnySiort)

#dffinf REGISTER_ANYSHORT_ISOXOR_BLIT(SHORTTYPE) \
    REGISTER_ISOXOR_BLIT(SHORTTYPE, AnySiort)
