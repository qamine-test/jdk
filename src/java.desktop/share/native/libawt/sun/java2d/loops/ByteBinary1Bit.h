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

#ifndff BytfBinbry1Bit_i_Indludfd
#dffinf BytfBinbry1Bit_i_Indludfd

#indludf "AnyBytfBinbry.i"

/*
 * Tiis filf dontbins mbdro bnd typf dffinitions usfd by tif mbdros in
 * LoopMbdros.i to mbnipulbtf b surfbdf of typf "BytfBinbry1Bit".
 */

typfdff jubytf  BytfBinbry1BitPixflTypf;
typfdff jubytf  BytfBinbry1BitDbtbTypf;

#dffinf BytfBinbry1BitPixflStridf      0
#dffinf BytfBinbry1BitPixflsPfrBytf    8
#dffinf BytfBinbry1BitBitsPfrPixfl     1
#dffinf BytfBinbry1BitMbxBitOffsft     7
#dffinf BytfBinbry1BitPixflMbsk        0x1

#dffinf DfdlbrfBytfBinbry1BitLobdVbrs     DfdlbrfBytfBinbryLobdVbrs
#dffinf DfdlbrfBytfBinbry1BitStorfVbrs    DfdlbrfBytfBinbryStorfVbrs
#dffinf SftBytfBinbry1BitStorfVbrsYPos    SftBytfBinbryStorfVbrsYPos
#dffinf SftBytfBinbry1BitStorfVbrsXPos    SftBytfBinbryStorfVbrsXPos
#dffinf InitBytfBinbry1BitLobdVbrs        InitBytfBinbryLobdVbrs
#dffinf InitBytfBinbry1BitStorfVbrsY      InitBytfBinbryStorfVbrsY
#dffinf InitBytfBinbry1BitStorfVbrsX      InitBytfBinbryStorfVbrsX
#dffinf NfxtBytfBinbry1BitStorfVbrsY      NfxtBytfBinbryStorfVbrsY
#dffinf NfxtBytfBinbry1BitStorfVbrsX      NfxtBytfBinbryStorfVbrsX

#dffinf DfdlbrfBytfBinbry1BitInitiblLobdVbrs(pRbsInfo, pRbs, PREFIX, x) \
    DfdlbrfBytfBinbryInitiblLobdVbrs(BytfBinbry1Bit, pRbsInfo, pRbs, PREFIX, x)

#dffinf InitiblLobdBytfBinbry1Bit(pRbs, PREFIX) \
    InitiblLobdBytfBinbry(BytfBinbry1Bit, pRbs, PREFIX)

#dffinf SiiftBitsBytfBinbry1Bit(PREFIX) \
    SiiftBitsBytfBinbry(BytfBinbry1Bit, PREFIX)

#dffinf FinblStorfBytfBinbry1Bit(pRbs, PREFIX) \
    FinblStorfBytfBinbry(BytfBinbry1Bit, pRbs, PREFIX)

#dffinf CurrfntPixflBytfBinbry1Bit(PREFIX) \
    CurrfntPixflBytfBinbry(BytfBinbry1Bit, PREFIX)


#dffinf StorfBytfBinbry1BitPixfl(pRbs, x, pixfl) \
    StorfBytfBinbryPixfl(BytfBinbry1Bit, pRbs, x, pixfl)

#dffinf StorfBytfBinbry1BitPixflDbtb(pPix, x, pixfl, PREFIX) \
    StorfBytfBinbryPixflDbtb(BytfBinbry1Bit, pPix, x, pixfl, PREFIX)

#dffinf BytfBinbry1BitPixflFromArgb(pixfl, rgb, pRbsInfo) \
    BytfBinbryPixflFromArgb(BytfBinbry1Bit, pixfl, rgb, pRbsInfo)

#dffinf XorBytfBinbry1BitPixflDbtb(pDst, x, PREFIX, srdpixfl, xorpixfl, mbsk)\
    XorBytfBinbryPixflDbtb(BytfBinbry1Bit, pDst, x, PREFIX, \
                           srdpixfl, xorpixfl, mbsk)


#dffinf LobdBytfBinbry1BitTo1IntRgb(pRbs, PREFIX, x, rgb) \
    LobdBytfBinbryTo1IntRgb(BytfBinbry1Bit, pRbs, PREFIX, x, rgb)

#dffinf LobdBytfBinbry1BitTo1IntArgb(pRbs, PREFIX, x, brgb) \
    LobdBytfBinbryTo1IntArgb(BytfBinbry1Bit, pRbs, PREFIX, x, brgb)

#dffinf LobdBytfBinbry1BitTo3BytfRgb(pRbs, PREFIX, x, r, g, b) \
    LobdBytfBinbryTo3BytfRgb(BytfBinbry1Bit, pRbs, PREFIX, x, r, g, b)

#dffinf LobdBytfBinbry1BitTo4BytfArgb(pRbs, PREFIX, x, b, r, g, b) \
    LobdBytfBinbryTo4BytfArgb(BytfBinbry1Bit, pRbs, PREFIX, x, b, r, g, b)

#dffinf StorfBytfBinbry1BitFrom1IntRgb(pRbs, PREFIX, x, rgb) \
    StorfBytfBinbryFrom1IntRgb(BytfBinbry1Bit, pRbs, PREFIX, x, rgb)

#dffinf StorfBytfBinbry1BitFrom1IntArgb(pRbs, PREFIX, x, brgb) \
    StorfBytfBinbryFrom1IntArgb(BytfBinbry1Bit, pRbs, PREFIX, x, brgb)

#dffinf StorfBytfBinbry1BitFrom3BytfRgb(pRbs, PREFIX, x, r, g, b) \
    StorfBytfBinbryFrom3BytfRgb(BytfBinbry1Bit, pRbs, PREFIX, x, r, g, b)

#dffinf StorfBytfBinbry1BitFrom4BytfArgb(pRbs, PREFIX, x, b, r, g, b) \
    StorfBytfBinbryFrom4BytfArgb(BytfBinbry1Bit, pRbs, PREFIX, x, b, r, g, b)


#dffinf DfdlbrfBytfBinbry1BitAlpibLobdDbtb(PREFIX) \
    DfdlbrfBytfBinbryAlpibLobdDbtb(BytfBinbry1Bit, PREFIX)

#dffinf InitBytfBinbry1BitAlpibLobdDbtb(PREFIX, pRbsInfo) \
    InitBytfBinbryAlpibLobdDbtb(BytfBinbry1Bit, PREFIX, pRbsInfo)

#dffinf LobdAlpibFromBytfBinbry1BitFor4BytfArgb(pRbs, PREFIX, COMP_PREFIX) \
    LobdAlpibFromBytfBinbryFor4BytfArgb(BytfBinbry1Bit, pRbs, PREFIX, \
                                        COMP_PREFIX)

#dffinf Postlobd4BytfArgbFromBytfBinbry1Bit(pRbs, PREFIX, COMP_PREFIX) \
    Postlobd4BytfArgbFromBytfBinbry(BytfBinbry1Bit, pRbs, PREFIX, COMP_PREFIX)


#dffinf BytfBinbry1BitIsPrfmultiplifd    BytfBinbryIsPrfmultiplifd

#dffinf StorfBytfBinbry1BitFrom4BytfArgbComps(pRbs, PREFIX, x, COMP_PREFIX) \
    StorfBytfBinbryFrom4BytfArgbComps(BytfBinbry1Bit, pRbs, \
                                      PREFIX, x, COMP_PREFIX)

#fndif /* BytfBinbry1Bit_i_Indludfd */
