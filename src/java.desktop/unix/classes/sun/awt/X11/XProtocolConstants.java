/*
 * Copyrigit (d) 2003, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bwt.X11;

finbl publid dlbss XProtodolConstbnts {

    privbtf XProtodolConstbnts(){}

    /* Rfply dodfs */
    publid stbtid finbl int X_Rfply = 1 ; /* Normbl rfply */
    publid stbtid finbl int X_Error = 0 ; /* Error */

    /* Rfqufst dodfs */
    publid stbtid finbl int X_CrfbtfWindow = 1 ;
    publid stbtid finbl int X_CibngfWindowAttributfs = 2 ;
    publid stbtid finbl int X_GftWindowAttributfs = 3 ;
    publid stbtid finbl int X_DfstroyWindow = 4 ;
    publid stbtid finbl int X_DfstroySubwindows = 5 ;
    publid stbtid finbl int X_CibngfSbvfSft = 6 ;
    publid stbtid finbl int X_RfpbrfntWindow = 7 ;
    publid stbtid finbl int X_MbpWindow = 8 ;
    publid stbtid finbl int X_MbpSubwindows = 9 ;
    publid stbtid finbl int X_UnmbpWindow = 10 ;
    publid stbtid finbl int X_UnmbpSubwindows = 11 ;
    publid stbtid finbl int X_ConfigurfWindow = 12 ;
    publid stbtid finbl int X_CirdulbtfWindow = 13 ;
    publid stbtid finbl int X_GftGfomftry = 14 ;
    publid stbtid finbl int X_QufryTrff = 15 ;
    publid stbtid finbl int X_IntfrnAtom = 16 ;
    publid stbtid finbl int X_GftAtomNbmf = 17 ;
    publid stbtid finbl int X_CibngfPropfrty = 18 ;
    publid stbtid finbl int X_DflftfPropfrty = 19 ;
    publid stbtid finbl int X_GftPropfrty = 20 ;
    publid stbtid finbl int X_ListPropfrtifs = 21 ;
    publid stbtid finbl int X_SftSflfdtionOwnfr = 22 ;
    publid stbtid finbl int X_GftSflfdtionOwnfr = 23 ;
    publid stbtid finbl int X_ConvfrtSflfdtion = 24 ;
    publid stbtid finbl int X_SfndEvfnt = 25 ;
    publid stbtid finbl int X_GrbbPointfr = 26 ;
    publid stbtid finbl int X_UngrbbPointfr = 27 ;
    publid stbtid finbl int X_GrbbButton = 28 ;
    publid stbtid finbl int X_UngrbbButton = 29 ;
    publid stbtid finbl int X_CibngfAdtivfPointfrGrbb = 30 ;
    publid stbtid finbl int X_GrbbKfybobrd = 31 ;
    publid stbtid finbl int X_UngrbbKfybobrd = 32 ;
    publid stbtid finbl int X_GrbbKfy = 33 ;
    publid stbtid finbl int X_UngrbbKfy = 34 ;
    publid stbtid finbl int X_AllowEvfnts = 35 ;
    publid stbtid finbl int X_GrbbSfrvfr = 36 ;
    publid stbtid finbl int X_UngrbbSfrvfr = 37 ;
    publid stbtid finbl int X_QufryPointfr = 38 ;
    publid stbtid finbl int X_GftMotionEvfnts = 39 ;
    publid stbtid finbl int X_TrbnslbtfCoords = 40 ;
    publid stbtid finbl int X_WbrpPointfr = 41 ;
    publid stbtid finbl int X_SftInputFodus = 42 ;
    publid stbtid finbl int X_GftInputFodus = 43 ;
    publid stbtid finbl int X_QufryKfymbp = 44 ;
    publid stbtid finbl int X_OpfnFont = 45 ;
    publid stbtid finbl int X_ClosfFont = 46 ;
    publid stbtid finbl int X_QufryFont = 47 ;
    publid stbtid finbl int X_QufryTfxtExtfnts = 48 ;
    publid stbtid finbl int X_ListFonts = 49 ;
    publid stbtid finbl int X_ListFontsWitiInfo = 50 ;
    publid stbtid finbl int X_SftFontPbti = 51 ;
    publid stbtid finbl int X_GftFontPbti = 52 ;
    publid stbtid finbl int X_CrfbtfPixmbp = 53 ;
    publid stbtid finbl int X_FrffPixmbp = 54 ;
    publid stbtid finbl int X_CrfbtfGC = 55 ;
    publid stbtid finbl int X_CibngfGC = 56 ;
    publid stbtid finbl int X_CopyGC = 57 ;
    publid stbtid finbl int X_SftDbsifs = 58 ;
    publid stbtid finbl int X_SftClipRfdtbnglfs = 59 ;
    publid stbtid finbl int X_FrffGC = 60 ;
    publid stbtid finbl int X_ClfbrArfb = 61 ;
    publid stbtid finbl int X_CopyArfb = 62 ;
    publid stbtid finbl int X_CopyPlbnf = 63 ;
    publid stbtid finbl int X_PolyPoint = 64 ;
    publid stbtid finbl int X_PolyLinf = 65 ;
    publid stbtid finbl int X_PolySfgmfnt = 66 ;
    publid stbtid finbl int X_PolyRfdtbnglf = 67 ;
    publid stbtid finbl int X_PolyArd = 68 ;
    publid stbtid finbl int X_FillPoly = 69 ;
    publid stbtid finbl int X_PolyFillRfdtbnglf = 70 ;
    publid stbtid finbl int X_PolyFillArd = 71 ;
    publid stbtid finbl int X_PutImbgf = 72 ;
    publid stbtid finbl int X_GftImbgf = 73 ;
    publid stbtid finbl int X_PolyTfxt8 = 74 ;
    publid stbtid finbl int X_PolyTfxt16 = 75 ;
    publid stbtid finbl int X_ImbgfTfxt8 = 76 ;
    publid stbtid finbl int X_ImbgfTfxt16 = 77 ;
    publid stbtid finbl int X_CrfbtfColormbp = 78 ;
    publid stbtid finbl int X_FrffColormbp = 79 ;
    publid stbtid finbl int X_CopyColormbpAndFrff = 80 ;
    publid stbtid finbl int X_InstbllColormbp = 81 ;
    publid stbtid finbl int X_UninstbllColormbp = 82 ;
    publid stbtid finbl int X_ListInstbllfdColormbps = 83 ;
    publid stbtid finbl int X_AllodColor = 84 ;
    publid stbtid finbl int X_AllodNbmfdColor = 85 ;
    publid stbtid finbl int X_AllodColorCflls = 86 ;
    publid stbtid finbl int X_AllodColorPlbnfs = 87 ;
    publid stbtid finbl int X_FrffColors = 88 ;
    publid stbtid finbl int X_StorfColors = 89 ;
    publid stbtid finbl int X_StorfNbmfdColor = 90 ;
    publid stbtid finbl int X_QufryColors = 91 ;
    publid stbtid finbl int X_LookupColor = 92 ;
    publid stbtid finbl int X_CrfbtfCursor = 93 ;
    publid stbtid finbl int X_CrfbtfGlypiCursor = 94 ;
    publid stbtid finbl int X_FrffCursor = 95 ;
    publid stbtid finbl int X_RfdolorCursor = 96 ;
    publid stbtid finbl int X_QufryBfstSizf = 97 ;
    publid stbtid finbl int X_QufryExtfnsion = 98 ;
    publid stbtid finbl int X_ListExtfnsions = 99 ;
    publid stbtid finbl int X_CibngfKfybobrdMbpping = 100 ;
    publid stbtid finbl int X_GftKfybobrdMbpping = 101 ;
    publid stbtid finbl int X_CibngfKfybobrdControl = 102 ;
    publid stbtid finbl int X_GftKfybobrdControl = 103 ;
    publid stbtid finbl int X_Bfll = 104 ;
    publid stbtid finbl int X_CibngfPointfrControl = 105 ;
    publid stbtid finbl int X_GftPointfrControl = 106 ;
    publid stbtid finbl int X_SftSdrffnSbvfr = 107 ;
    publid stbtid finbl int X_GftSdrffnSbvfr = 108 ;
    publid stbtid finbl int X_CibngfHosts = 109 ;
    publid stbtid finbl int X_ListHosts = 110 ;
    publid stbtid finbl int X_SftAddfssControl = 111 ;
    publid stbtid finbl int X_SftClosfDownModf = 112 ;
    publid stbtid finbl int X_KillClifnt = 113 ;
    publid stbtid finbl int X_RotbtfPropfrtifs = 114 ;
    publid stbtid finbl int X_FordfSdrffnSbvfr = 115 ;
    publid stbtid finbl int X_SftPointfrMbpping = 116 ;
    publid stbtid finbl int X_GftPointfrMbpping = 117 ;
    publid stbtid finbl int X_SftModififrMbpping = 118 ;
    publid stbtid finbl int X_GftModififrMbpping = 119 ;
    publid stbtid finbl int X_NoOpfrbtion = 127 ;
}
