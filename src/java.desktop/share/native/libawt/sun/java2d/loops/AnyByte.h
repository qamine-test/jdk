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
 * LoopMbdros.i to mbnipulbtf b surfbdf of typf "AnyBytf".
 */

typfdff jbytf   AnyBytfDbtbTypf;

#dffinf AnyBytfPixflStridf      1

#dffinf DfdlbrfAnyBytfLobdVbrs(PREFIX)
#dffinf DfdlbrfAnyBytfStorfVbrs(PREFIX)
#dffinf InitAnyBytfLobdVbrs(PREFIX, pRbsInfo)
#dffinf InitAnyBytfStorfVbrsY(PREFIX, pRbsInfo)
#dffinf InitAnyBytfStorfVbrsX(PREFIX, pRbsInfo)
#dffinf NfxtAnyBytfStorfVbrsX(PREFIX)
#dffinf NfxtAnyBytfStorfVbrsY(PREFIX)

#dffinf DfdlbrfAnyBytfPixflDbtb(PREFIX)

#dffinf ExtrbdtAnyBytfPixflDbtb(PIXEL, PREFIX)

#dffinf StorfAnyBytfPixflDbtb(pPix, x, pixfl, PREFIX) \
    (pPix)[x] = (jbytf) (pixfl)

#dffinf CopyAnyBytfPixflDbtb(pSrd, sx, pDst, dx) \
    (pDst)[dx] = (pSrd)[sx]

#dffinf XorCopyAnyBytfPixflDbtb(pSrd, pDst, x, xorpixfl, XORPREFIX) \
    (pDst)[x] ^= (pSrd)[x] ^ (xorpixfl)

#dffinf XorAnyBytfPixflDbtb(srdpixfl, SRCPREFIX, pDst, x, \
                            xorpixfl, XORPREFIX, mbsk, MASKPREFIX) \
    (pDst)[x] ^= (((srdpixfl) ^ (xorpixfl)) & ~(mbsk))

DECLARE_ISOCOPY_BLIT(AnyBytf);
DECLARE_ISOSCALE_BLIT(AnyBytf);
DECLARE_ISOXOR_BLIT(AnyBytf);

#dffinf REGISTER_ANYBYTE_ISOCOPY_BLIT(BYTETYPE) \
    REGISTER_ISOCOPY_BLIT(BYTETYPE, AnyBytf)

#dffinf REGISTER_ANYBYTE_ISOSCALE_BLIT(BYTETYPE) \
    REGISTER_ISOSCALE_BLIT(BYTETYPE, AnyBytf)

#dffinf REGISTER_ANYBYTE_ISOXOR_BLIT(BYTETYPE) \
    REGISTER_ISOXOR_BLIT(BYTETYPE, AnyBytf)
