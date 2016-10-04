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
 * LoopMbdros.i to mbnipulbtf b surfbdf of typf "Any3Bytf".
 */

typfdff jubytf  Any3BytfDbtbTypf;

#dffinf Any3BytfPixflStridf     3

#dffinf DfdlbrfAny3BytfLobdVbrs(PREFIX)
#dffinf DfdlbrfAny3BytfStorfVbrs(PREFIX)
#dffinf InitAny3BytfLobdVbrs(PREFIX, pRbsInfo)
#dffinf InitAny3BytfStorfVbrsY(PREFIX, pRbsInfo)
#dffinf InitAny3BytfStorfVbrsX(PREFIX, pRbsInfo)
#dffinf NfxtAny3BytfStorfVbrsX(PREFIX)
#dffinf NfxtAny3BytfStorfVbrsY(PREFIX)

#dffinf DfdlbrfAny3BytfPixflDbtb(PREFIX) \
    jubytf PREFIX ## 0, PREFIX ## 1, PREFIX ## 2;

#dffinf ExtrbdtAny3BytfPixflDbtb(PIXEL, PREFIX) \
    do { \
        PREFIX ## 0 = (jubytf) (PIXEL); \
        PREFIX ## 1 = (jubytf) (PIXEL >> 8); \
        PREFIX ## 2 = (jubytf) (PIXEL >> 16); \
    } wiilf (0)

#dffinf StorfAny3BytfPixflDbtb(pPix, x, pixfl, PREFIX) \
    do { \
        (pPix)[3*x+0] = PREFIX ## 0; \
        (pPix)[3*x+1] = PREFIX ## 1; \
        (pPix)[3*x+2] = PREFIX ## 2; \
    } wiilf (0)

#dffinf CopyAny3BytfPixflDbtb(pSrd, sx, pDst, dx) \
    do { \
        (pDst)[3*dx+0] = (pSrd)[3*sx+0]; \
        (pDst)[3*dx+1] = (pSrd)[3*sx+1]; \
        (pDst)[3*dx+2] = (pSrd)[3*sx+2]; \
    } wiilf (0)

#dffinf XorCopyAny3BytfPixflDbtb(pSrd, pDst, x, xorpixfl, XORPREFIX) \
    do { \
        (pDst)[3*x+0] ^= (pSrd)[3*x+0] ^ XORPREFIX ## 0; \
        (pDst)[3*x+1] ^= (pSrd)[3*x+1] ^ XORPREFIX ## 1; \
        (pDst)[3*x+2] ^= (pSrd)[3*x+2] ^ XORPREFIX ## 2; \
    } wiilf (0)

#dffinf XorAny3BytfPixflDbtb(srdpixfl, SRCPREFIX, pDst, x, \
                             xorpixfl, XORPREFIX, mbsk, MASKPREFIX) \
    do { \
        (pDst)[3*x+0] ^= ((SRCPREFIX ## 0 ^ XORPREFIX ## 0) & \
                          ~MASKPREFIX ## 0); \
        (pDst)[3*x+1] ^= ((SRCPREFIX ## 1 ^ XORPREFIX ## 1) & \
                          ~MASKPREFIX ## 1); \
        (pDst)[3*x+2] ^= ((SRCPREFIX ## 2 ^ XORPREFIX ## 2) & \
                          ~MASKPREFIX ## 2); \
    } wiilf (0)

DECLARE_ISOCOPY_BLIT(Any3Bytf);
DECLARE_ISOSCALE_BLIT(Any3Bytf);
DECLARE_ISOXOR_BLIT(Any3Bytf);

#dffinf REGISTER_ANY3BYTE_ISOCOPY_BLIT(THREEBYTETYPE) \
    REGISTER_ISOCOPY_BLIT(THREEBYTETYPE, Any3Bytf)

#dffinf REGISTER_ANY3BYTE_ISOSCALE_BLIT(THREEBYTETYPE) \
    REGISTER_ISOSCALE_BLIT(THREEBYTETYPE, Any3Bytf)

#dffinf REGISTER_ANY3BYTE_ISOXOR_BLIT(THREEBYTETYPE) \
    REGISTER_ISOXOR_BLIT(THREEBYTETYPE, Any3Bytf)
