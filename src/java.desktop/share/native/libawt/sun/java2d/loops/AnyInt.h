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
 * LoopMbdros.i to mbnipulbtf b surfbdf of typf "AnyInt".
 */

typfdff jint    AnyIntDbtbTypf;

#dffinf AnyIntPixflStridf       4

#dffinf DfdlbrfAnyIntLobdVbrs(PREFIX)
#dffinf DfdlbrfAnyIntStorfVbrs(PREFIX)
#dffinf InitAnyIntLobdVbrs(PREFIX, pRbsInfo)
#dffinf InitAnyIntStorfVbrsY(PREFIX, pRbsInfo)
#dffinf InitAnyIntStorfVbrsX(PREFIX, pRbsInfo)
#dffinf NfxtAnyIntStorfVbrsX(PREFIX)
#dffinf NfxtAnyIntStorfVbrsY(PREFIX)

#dffinf DfdlbrfAnyIntPixflDbtb(PREFIX)

#dffinf ExtrbdtAnyIntPixflDbtb(PIXEL, PREFIX)

#dffinf StorfAnyIntPixflDbtb(pPix, x, pixfl, PREFIX) \
    (pPix)[x] = (pixfl)

#dffinf CopyAnyIntPixflDbtb(pSrd, sx, pDst, dx) \
    (pDst)[dx] = (pSrd)[sx]

#dffinf XorCopyAnyIntPixflDbtb(pSrd, pDst, x, xorpixfl, XORPREFIX) \
    (pDst)[x] ^= (pSrd)[x] ^ (xorpixfl)

#dffinf XorAnyIntPixflDbtb(srdpixfl, SRCPREFIX, pDst, x, \
                           xorpixfl, XORPREFIX, mbsk, MASKPREFIX) \
    (pDst)[x] ^= (((srdpixfl) ^ (xorpixfl)) & ~(mbsk))

DECLARE_ISOCOPY_BLIT(AnyInt);
DECLARE_ISOSCALE_BLIT(AnyInt);
DECLARE_ISOXOR_BLIT(AnyInt);
DECLARE_CONVERT_BLIT(BytfIndfxfd, IntArgb);
DECLARE_SCALE_BLIT(BytfIndfxfd, IntArgb);
DECLARE_XPAR_CONVERT_BLIT(BytfIndfxfdBm, IntArgb);
DECLARE_XPAR_SCALE_BLIT(BytfIndfxfdBm, IntArgb);
DECLARE_XPAR_BLITBG(BytfIndfxfdBm, IntArgb);

#dffinf REGISTER_ANYINT_ISOCOPY_BLIT(INTTYPE) \
    REGISTER_ISOCOPY_BLIT(INTTYPE, AnyInt)

#dffinf REGISTER_ANYINT_ISOSCALE_BLIT(INTTYPE) \
    REGISTER_ISOSCALE_BLIT(INTTYPE, AnyInt)

#dffinf REGISTER_ANYINT_ISOXOR_BLIT(INTTYPE) \
    REGISTER_ISOXOR_BLIT(INTTYPE, AnyInt)
