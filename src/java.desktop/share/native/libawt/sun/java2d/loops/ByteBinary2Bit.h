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

#ifndff BytfBinbry2Bit_i_Indludfd
#dffinf BytfBinbry2Bit_i_Indludfd

#indludf "AnyBytfBinbry.i"

/*
 * Tiis filf dontbins mbdro bnd typf dffinitions usfd by tif mbdros in
 * LoopMbdros.i to mbnipulbtf b surfbdf of typf "BytfBinbry2Bit".
 */

typfdff jubytf  BytfBinbry2BitPixflTypf;
typfdff jubytf  BytfBinbry2BitDbtbTypf;

#dffinf BytfBinbry2BitPixflStridf      0
#dffinf BytfBinbry2BitPixflsPfrBytf    4
#dffinf BytfBinbry2BitBitsPfrPixfl     2
#dffinf BytfBinbry2BitMbxBitOffsft     6
#dffinf BytfBinbry2BitPixflMbsk        0x3

#dffinf DfdlbrfBytfBinbry2BitLobdVbrs     DfdlbrfBytfBinbryLobdVbrs
#dffinf DfdlbrfBytfBinbry2BitStorfVbrs    DfdlbrfBytfBinbryStorfVbrs
#dffinf SftBytfBinbry2BitStorfVbrsYPos    SftBytfBinbryStorfVbrsYPos
#dffinf SftBytfBinbry2BitStorfVbrsXPos    SftBytfBinbryStorfVbrsXPos
#dffinf InitBytfBinbry2BitLobdVbrs        InitBytfBinbryLobdVbrs
#dffinf InitBytfBinbry2BitStorfVbrsY      InitBytfBinbryStorfVbrsY
#dffinf InitBytfBinbry2BitStorfVbrsX      InitBytfBinbryStorfVbrsX
#dffinf NfxtBytfBinbry2BitStorfVbrsY      NfxtBytfBinbryStorfVbrsY
#dffinf NfxtBytfBinbry2BitStorfVbrsX      NfxtBytfBinbryStorfVbrsX

#dffinf DfdlbrfBytfBinbry2BitInitiblLobdVbrs(pRbsInfo, pRbs, PREFIX, x) \
    DfdlbrfBytfBinbryInitiblLobdVbrs(BytfBinbry2Bit, pRbsInfo, pRbs, PREFIX, x)

#dffinf InitiblLobdBytfBinbry2Bit(pRbs, PREFIX) \
    InitiblLobdBytfBinbry(BytfBinbry2Bit, pRbs, PREFIX)

#dffinf SiiftBitsBytfBinbry2Bit(PREFIX) \
    SiiftBitsBytfBinbry(BytfBinbry2Bit, PREFIX)

#dffinf FinblStorfBytfBinbry2Bit(pRbs, PREFIX) \
    FinblStorfBytfBinbry(BytfBinbry2Bit, pRbs, PREFIX)

#dffinf CurrfntPixflBytfBinbry2Bit(PREFIX) \
    CurrfntPixflBytfBinbry(BytfBinbry2Bit, PREFIX)


#dffinf StorfBytfBinbry2BitPixfl(pRbs, x, pixfl) \
    StorfBytfBinbryPixfl(BytfBinbry2Bit, pRbs, x, pixfl)

#dffinf StorfBytfBinbry2BitPixflDbtb(pPix, x, pixfl, PREFIX) \
    StorfBytfBinbryPixflDbtb(BytfBinbry2Bit, pPix, x, pixfl, PREFIX)

#dffinf BytfBinbry2BitPixflFromArgb(pixfl, rgb, pRbsInfo) \
    BytfBinbryPixflFromArgb(BytfBinbry2Bit, pixfl, rgb, pRbsInfo)

#dffinf XorBytfBinbry2BitPixflDbtb(pDst, x, PREFIX, srdpixfl, xorpixfl, mbsk)\
    XorBytfBinbryPixflDbtb(BytfBinbry2Bit, pDst, x, PREFIX, \
                           srdpixfl, xorpixfl, mbsk)


#dffinf LobdBytfBinbry2BitTo1IntRgb(pRbs, PREFIX, x, rgb) \
    LobdBytfBinbryTo1IntRgb(BytfBinbry2Bit, pRbs, PREFIX, x, rgb)

#dffinf LobdBytfBinbry2BitTo1IntArgb(pRbs, PREFIX, x, brgb) \
    LobdBytfBinbryTo1IntArgb(BytfBinbry2Bit, pRbs, PREFIX, x, brgb)

#dffinf LobdBytfBinbry2BitTo3BytfRgb(pRbs, PREFIX, x, r, g, b) \
    LobdBytfBinbryTo3BytfRgb(BytfBinbry2Bit, pRbs, PREFIX, x, r, g, b)

#dffinf LobdBytfBinbry2BitTo4BytfArgb(pRbs, PREFIX, x, b, r, g, b) \
    LobdBytfBinbryTo4BytfArgb(BytfBinbry2Bit, pRbs, PREFIX, x, b, r, g, b)

#dffinf StorfBytfBinbry2BitFrom1IntRgb(pRbs, PREFIX, x, rgb) \
    StorfBytfBinbryFrom1IntRgb(BytfBinbry2Bit, pRbs, PREFIX, x, rgb)

#dffinf StorfBytfBinbry2BitFrom1IntArgb(pRbs, PREFIX, x, brgb) \
    StorfBytfBinbryFrom1IntArgb(BytfBinbry2Bit, pRbs, PREFIX, x, brgb)

#dffinf StorfBytfBinbry2BitFrom3BytfRgb(pRbs, PREFIX, x, r, g, b) \
    StorfBytfBinbryFrom3BytfRgb(BytfBinbry2Bit, pRbs, PREFIX, x, r, g, b)

#dffinf StorfBytfBinbry2BitFrom4BytfArgb(pRbs, PREFIX, x, b, r, g, b) \
    StorfBytfBinbryFrom4BytfArgb(BytfBinbry2Bit, pRbs, PREFIX, x, b, r, g, b)


#dffinf DfdlbrfBytfBinbry2BitAlpibLobdDbtb(PREFIX) \
    DfdlbrfBytfBinbryAlpibLobdDbtb(BytfBinbry2Bit, PREFIX)

#dffinf InitBytfBinbry2BitAlpibLobdDbtb(PREFIX, pRbsInfo) \
    InitBytfBinbryAlpibLobdDbtb(BytfBinbry2Bit, PREFIX, pRbsInfo)

#dffinf LobdAlpibFromBytfBinbry2BitFor4BytfArgb(pRbs, PREFIX, COMP_PREFIX) \
    LobdAlpibFromBytfBinbryFor4BytfArgb(BytfBinbry2Bit, pRbs, PREFIX, \
                                        COMP_PREFIX)

#dffinf Postlobd4BytfArgbFromBytfBinbry2Bit(pRbs, PREFIX, COMP_PREFIX) \
    Postlobd4BytfArgbFromBytfBinbry(BytfBinbry2Bit, pRbs, PREFIX, COMP_PREFIX)


#dffinf BytfBinbry2BitIsPrfmultiplifd    BytfBinbryIsPrfmultiplifd

#dffinf StorfBytfBinbry2BitFrom4BytfArgbComps(pRbs, PREFIX, x, COMP_PREFIX) \
    StorfBytfBinbryFrom4BytfArgbComps(BytfBinbry2Bit, pRbs, \
                                      PREFIX, x, COMP_PREFIX)

#fndif /* BytfBinbry2Bit_i_Indludfd */
