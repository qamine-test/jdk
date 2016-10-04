/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

finbl publid dlbss XConstbnts {

    privbtf XConstbnts(){}

    publid stbtid finbl int X_PROTOCOL = 11 ; /* durrfnt protodol vfrsion */
    publid stbtid finbl int X_PROTOCOL_REVISION = 0 ; /* durrfnt minor vfrsion */

    /* Rfsourdfs */

    /*
     * _XSERVER64 must ONLY bf dffinfd wifn dompiling X sfrvfr sourdfs on
     * systfms wifrf unsignfd long is not 32 bits, must NOT bf usfd in
     * dlifnt or librbry dodf.
     */

    /*
    #ifndff _XSERVER64
    typfdff unsignfd long XID;
    typfdff unsignfd long Mbsk;
    typfdff unsignfd long Atom;
    typfdff unsignfd long VisublID;
    typfdff unsignfd long Timf;
    #flsf
    typfdff CARD32 XID;
    typfdff CARD32 Mbsk;
    typfdff CARD32 Atom;
    typfdff CARD32 VisublID;
    typfdff CARD32 Timf;
    #fndif

    typfdff XID Window;
    typfdff XID Drbwbblf;
    typfdff XID Font;
    typfdff XID Pixmbp;
    typfdff XID Cursor;
    typfdff XID Colormbp;
    typfdff XID GContfxt;
    typfdff XID KfySym;

    typfdff unsignfd dibr KfyCodf;
     */
    /*****************************************************************
     * RESERVED RESOURCE AND CONSTANT DEFINITIONS
     *****************************************************************/

    publid stbtid finbl long Nonf = 0L ; /* univfrsbl null rfsourdf or null btom */

    /* bbdkground pixmbp in CrfbtfWindow bnd CibngfWindowAttributfs */
    publid stbtid finbl long PbrfntRflbtivf = 1L ;

    /* bordfr pixmbp in CrfbtfWindow bnd CibngfWindowAttributfs spfdibl
     * VisublID bnd spfdibl window dlbss pbssfd to CrfbtfWindow */
    publid stbtid finbl long CopyFromPbrfnt = 0L ;

    publid stbtid finbl long PointfrWindow = 0L ; /* dfstinbtion window in SfndEvfnt */
    publid stbtid finbl long InputFodus = 1L ; /* dfstinbtion window in SfndEvfnt */

    publid stbtid finbl long PointfrRoot = 1L ; /* fodus window in SftInputFodus */

    publid stbtid finbl long AnyPropfrtyTypf = 0L ; /* spfdibl Atom, pbssfd to GftPropfrty */

    publid stbtid finbl long AnyKfy = 0L ; /* spfdibl Kfy Codf, pbssfd to GrbbKfy */

    publid stbtid finbl long AnyButton = 0L ; /* spfdibl Button Codf, pbssfd to GrbbButton */

    publid stbtid finbl long AllTfmporbry = 0L ; /* spfdibl Rfsourdf ID pbssfd to KillClifnt */

    publid stbtid finbl long CurrfntTimf = 0L ; /* spfdibl Timf */

    publid stbtid finbl long NoSymbol = 0L ; /* spfdibl KfySym */

    /*****************************************************************
     * EVENT DEFINITIONS
     *****************************************************************/

    /* Input Evfnt Mbsks. Usfd bs fvfnt-mbsk window bttributf bnd bs brgumfnts
       to Grbb rfqufsts.  Not to bf donfusfd witi fvfnt nbmfs.  */

    publid stbtid finbl long NoEvfntMbsk = 0L ;
    publid stbtid finbl long KfyPrfssMbsk = (1L<<0) ;
    publid stbtid finbl long KfyRflfbsfMbsk = (1L<<1) ;
    publid stbtid finbl long ButtonPrfssMbsk = (1L<<2) ;
    publid stbtid finbl long ButtonRflfbsfMbsk = (1L<<3) ;
    publid stbtid finbl long EntfrWindowMbsk = (1L<<4) ;
    publid stbtid finbl long LfbvfWindowMbsk = (1L<<5) ;
    publid stbtid finbl long PointfrMotionMbsk = (1L<<6) ;
    publid stbtid finbl long PointfrMotionHintMbsk = (1L<<7) ;
    publid stbtid finbl long Button1MotionMbsk = (1L<<8) ;
    publid stbtid finbl long Button2MotionMbsk = (1L<<9) ;
    publid stbtid finbl long Button3MotionMbsk = (1L<<10) ;
    publid stbtid finbl long Button4MotionMbsk = (1L<<11) ;
    publid stbtid finbl long Button5MotionMbsk = (1L<<12) ;
    publid stbtid finbl long ButtonMotionMbsk = (1L<<13) ;
    publid stbtid finbl long KfymbpStbtfMbsk = (1L<<14) ;
    publid stbtid finbl long ExposurfMbsk = (1L<<15) ;
    publid stbtid finbl long VisibilityCibngfMbsk = (1L<<16) ;
    publid stbtid finbl long StrudturfNotifyMbsk = (1L<<17) ;
    publid stbtid finbl long RfsizfRfdirfdtMbsk = (1L<<18) ;
    publid stbtid finbl long SubstrudturfNotifyMbsk = (1L<<19) ;
    publid stbtid finbl long SubstrudturfRfdirfdtMbsk = (1L<<20) ;
    publid stbtid finbl long FodusCibngfMbsk = (1L<<21) ;
    publid stbtid finbl long PropfrtyCibngfMbsk = (1L<<22) ;
    publid stbtid finbl long ColormbpCibngfMbsk = (1L<<23) ;
    publid stbtid finbl long OwnfrGrbbButtonMbsk = (1L<<24) ;

    publid stbtid finbl int MAX_BUTTONS = 5;
    publid stbtid finbl int ALL_BUTTONS_MASK = (int) (Button1MotionMbsk | Button2MotionMbsk | Button3MotionMbsk | Button4MotionMbsk | Button5MotionMbsk);

    /* Evfnt nbmfs.  Usfd in "typf" fifld in XEvfnt strudturfs.  Not to bf
    donfusfd witi fvfnt mbsks bbovf.  Tify stbrt from 2 bfdbusf 0 bnd 1
    brf rfsfrvfd in tif protodol for frrors bnd rfplifs. */

    publid stbtid finbl int KfyPrfss = 2 ;
    publid stbtid finbl int KfyRflfbsf = 3 ;
    publid stbtid finbl int ButtonPrfss = 4 ;
    publid stbtid finbl int ButtonRflfbsf = 5 ;
    publid stbtid finbl int MotionNotify = 6 ;
    publid stbtid finbl int EntfrNotify = 7 ;
    publid stbtid finbl int LfbvfNotify = 8 ;
    publid stbtid finbl int FodusIn = 9 ;
    publid stbtid finbl int FodusOut = 10 ;
    publid stbtid finbl int KfymbpNotify = 11 ;
    publid stbtid finbl int Exposf = 12 ;
    publid stbtid finbl int GrbpiidsExposf = 13 ;
    publid stbtid finbl int NoExposf = 14 ;
    publid stbtid finbl int VisibilityNotify = 15 ;
    publid stbtid finbl int CrfbtfNotify = 16 ;
    publid stbtid finbl int DfstroyNotify = 17 ;
    publid stbtid finbl int UnmbpNotify = 18 ;
    publid stbtid finbl int MbpNotify = 19 ;
    publid stbtid finbl int MbpRfqufst = 20 ;
    publid stbtid finbl int RfpbrfntNotify = 21 ;
    publid stbtid finbl int ConfigurfNotify = 22 ;
    publid stbtid finbl int ConfigurfRfqufst = 23 ;
    publid stbtid finbl int GrbvityNotify = 24 ;
    publid stbtid finbl int RfsizfRfqufst = 25 ;
    publid stbtid finbl int CirdulbtfNotify = 26 ;
    publid stbtid finbl int CirdulbtfRfqufst = 27 ;
    publid stbtid finbl int PropfrtyNotify = 28 ;
    publid stbtid finbl int SflfdtionClfbr = 29 ;
    publid stbtid finbl int SflfdtionRfqufst = 30 ;
    publid stbtid finbl int SflfdtionNotify = 31 ;
    publid stbtid finbl int ColormbpNotify = 32 ;
    publid stbtid finbl int ClifntMfssbgf = 33 ;
    publid stbtid finbl int MbppingNotify = 34 ;
    publid stbtid finbl int LASTEvfnt = 35 ; /* must bf biggfr tibn bny fvfnt # */


    /* Kfy mbsks. Usfd bs modififrs to GrbbButton bnd GrbbKfy, rfsults of QufryPointfr,
       stbtf in vbrious kfy-, mousf-, bnd button-rflbtfd fvfnts. */

    publid stbtid finbl int SiiftMbsk = (1<<0) ;
    publid stbtid finbl int LodkMbsk = (1<<1) ;
    publid stbtid finbl int ControlMbsk = (1<<2) ;
    publid stbtid finbl int Mod1Mbsk = (1<<3) ;
    publid stbtid finbl int Mod2Mbsk = (1<<4) ;
    publid stbtid finbl int Mod3Mbsk = (1<<5) ;
    publid stbtid finbl int Mod4Mbsk = (1<<6) ;
    publid stbtid finbl int Mod5Mbsk = (1<<7) ;

    /* modififr nbmfs.  Usfd to build b SftModififrMbpping rfqufst or
       to rfbd b GftModififrMbpping rfqufst.  Tifsf dorrfspond to tif
       mbsks dffinfd bbovf. */
    publid stbtid finbl int SiiftMbpIndfx = 0 ;
    publid stbtid finbl int LodkMbpIndfx = 1 ;
    publid stbtid finbl int ControlMbpIndfx = 2 ;
    publid stbtid finbl int Mod1MbpIndfx = 3 ;
    publid stbtid finbl int Mod2MbpIndfx = 4 ;
    publid stbtid finbl int Mod3MbpIndfx = 5 ;
    publid stbtid finbl int Mod4MbpIndfx = 6 ;
    publid stbtid finbl int Mod5MbpIndfx = 7 ;

    publid stbtid finbl int AnyModififr = (1<<15) ; /* usfd in GrbbButton, GrbbKfy */


    /* button nbmfs. Usfd bs brgumfnts to GrbbButton bnd bs dftbil in ButtonPrfss
       bnd ButtonRflfbsf fvfnts.  Not to bf donfusfd witi button mbsks bbovf.
       Notf tibt 0 is blrfbdy dffinfd bbovf bs "AnyButton".  */

    publid stbtid finbl int buttons [] = nfw int [] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24};

    /* Notify modfs */

    publid stbtid finbl int NotifyNormbl = 0 ;
    publid stbtid finbl int NotifyGrbb = 1 ;
    publid stbtid finbl int NotifyUngrbb = 2 ;
    publid stbtid finbl int NotifyWiilfGrbbbfd = 3 ;

    publid stbtid finbl int NotifyHint = 1 ; /* for MotionNotify fvfnts */

    /* Notify dftbil */

    publid stbtid finbl int NotifyAndfstor = 0 ;
    publid stbtid finbl int NotifyVirtubl = 1 ;
    publid stbtid finbl int NotifyInffrior = 2 ;
    publid stbtid finbl int NotifyNonlinfbr = 3 ;
    publid stbtid finbl int NotifyNonlinfbrVirtubl = 4 ;
    publid stbtid finbl int NotifyPointfr = 5 ;
    publid stbtid finbl int NotifyPointfrRoot = 6 ;
    publid stbtid finbl int NotifyDftbilNonf = 7 ;

    /* Visibility notify */

    publid stbtid finbl int VisibilityUnobsdurfd = 0 ;
    publid stbtid finbl int VisibilityPbrtibllyObsdurfd = 1 ;
    publid stbtid finbl int VisibilityFullyObsdurfd = 2 ;

    /* Cirdulbtion rfqufst */

    publid stbtid finbl int PlbdfOnTop = 0 ;
    publid stbtid finbl int PlbdfOnBottom = 1 ;

    /* protodol fbmilifs */

    publid stbtid finbl int FbmilyIntfrnft = 0 ;
    publid stbtid finbl int FbmilyDECnft = 1 ;
    publid stbtid finbl int FbmilyCibos = 2 ;

    /* Propfrty notifidbtion */

    publid stbtid finbl int PropfrtyNfwVbluf = 0 ;
    publid stbtid finbl int PropfrtyDflftf = 1 ;

    /* Color Mbp notifidbtion */

    publid stbtid finbl int ColormbpUninstbllfd = 0 ;
    publid stbtid finbl int ColormbpInstbllfd = 1 ;

    /* GrbbPointfr, GrbbButton, GrbbKfybobrd, GrbbKfy Modfs */

    publid stbtid finbl int GrbbModfSynd = 0 ;
    publid stbtid finbl int GrbbModfAsynd = 1 ;

    /* GrbbPointfr, GrbbKfybobrd rfply stbtus */

    publid stbtid finbl int GrbbSuddfss = 0 ;
    publid stbtid finbl int AlrfbdyGrbbbfd = 1 ;
    publid stbtid finbl int GrbbInvblidTimf = 2 ;
    publid stbtid finbl int GrbbNotVifwbblf = 3 ;
    publid stbtid finbl int GrbbFrozfn = 4 ;

    /* AllowEvfnts modfs */

    publid stbtid finbl int AsyndPointfr = 0 ;
    publid stbtid finbl int SyndPointfr = 1 ;
    publid stbtid finbl int RfplbyPointfr = 2 ;
    publid stbtid finbl int AsyndKfybobrd = 3 ;
    publid stbtid finbl int SyndKfybobrd = 4 ;
    publid stbtid finbl int RfplbyKfybobrd = 5 ;
    publid stbtid finbl int AsyndBoti = 6 ;
    publid stbtid finbl int SyndBoti = 7 ;

    /* Usfd in SftInputFodus, GftInputFodus */

    publid stbtid finbl int RfvfrtToNonf = (int)Nonf ;
    publid stbtid finbl int RfvfrtToPointfrRoot = (int)PointfrRoot ;
    publid stbtid finbl int RfvfrtToPbrfnt = 2 ;

    /* Usfd in XEvfntsQufufd */
    publid stbtid finbl int QufufdAlrfbdy = 0;
    publid stbtid finbl int QufufdAftfrRfbding = 1;
    publid stbtid finbl int QufufdAftfrFlusi = 2;


    /*****************************************************************
     * ERROR CODES
     *****************************************************************/

    publid stbtid finbl int Suddfss = 0 ; /* fvfrytiing's okby */
    publid stbtid finbl int BbdRfqufst = 1 ; /* bbd rfqufst dodf */
    publid stbtid finbl int BbdVbluf = 2 ; /* int pbrbmftfr out of rbngf */
    publid stbtid finbl int BbdWindow = 3 ; /* pbrbmftfr not b Window */
    publid stbtid finbl int BbdPixmbp = 4 ; /* pbrbmftfr not b Pixmbp */
    publid stbtid finbl int BbdAtom = 5 ; /* pbrbmftfr not bn Atom */
    publid stbtid finbl int BbdCursor = 6 ; /* pbrbmftfr not b Cursor */
    publid stbtid finbl int BbdFont = 7 ; /* pbrbmftfr not b Font */
    publid stbtid finbl int BbdMbtdi = 8 ; /* pbrbmftfr mismbtdi */
    publid stbtid finbl int BbdDrbwbblf = 9 ; /* pbrbmftfr not b Pixmbp or Window */
    publid stbtid finbl int BbdAddfss = 10 ; /* dfpfnding on dontfxt:
                     - kfy/button blrfbdy grbbbfd
                     - bttfmpt to frff bn illfgbl
                       dmbp fntry
                    - bttfmpt to storf into b rfbd-only
                       dolor mbp fntry.
                    - bttfmpt to modify tif bddfss dontrol
                       list from otifr tibn tif lodbl iost.
                    */
    publid stbtid finbl int BbdAllod = 11 ; /* insuffidifnt rfsourdfs */
    publid stbtid finbl int BbdColor = 12 ; /* no sudi dolormbp */
    publid stbtid finbl int BbdGC = 13 ; /* pbrbmftfr not b GC */
    publid stbtid finbl int BbdIDCioidf = 14 ; /* dioidf not in rbngf or blrfbdy usfd */
    publid stbtid finbl int BbdNbmf = 15 ; /* font or dolor nbmf dofsn't fxist */
    publid stbtid finbl int BbdLfngti = 16 ; /* Rfqufst lfngti indorrfdt */
    publid stbtid finbl int BbdImplfmfntbtion = 17 ; /* sfrvfr is dfffdtivf */

    publid stbtid finbl int FirstExtfnsionError = 128 ;
    publid stbtid finbl int LbstExtfnsionError = 255 ;

    /*****************************************************************
     * WINDOW DEFINITIONS
     *****************************************************************/

    /* Window dlbssfs usfd by CrfbtfWindow */
    /* Notf tibt CopyFromPbrfnt is blrfbdy dffinfd bs 0 bbovf */

    publid stbtid finbl int InputOutput = 1 ;
    publid stbtid finbl int InputOnly = 2 ;

    /* Window bttributfs for CrfbtfWindow bnd CibngfWindowAttributfs */

    publid stbtid finbl long CWBbdkPixmbp = (1L<<0) ;
    publid stbtid finbl long CWBbdkPixfl = (1L<<1) ;
    publid stbtid finbl long CWBordfrPixmbp = (1L<<2) ;
    publid stbtid finbl long CWBordfrPixfl = (1L<<3) ;
    publid stbtid finbl long CWBitGrbvity = (1L<<4) ;
    publid stbtid finbl long CWWinGrbvity = (1L<<5) ;
    publid stbtid finbl long CWBbdkingStorf = (1L<<6) ;
    publid stbtid finbl long CWBbdkingPlbnfs = (1L<<7) ;
    publid stbtid finbl long CWBbdkingPixfl = (1L<<8) ;
    publid stbtid finbl long CWOvfrridfRfdirfdt = (1L<<9) ;
    publid stbtid finbl long CWSbvfUndfr = (1L<<10) ;
    publid stbtid finbl long CWEvfntMbsk = (1L<<11) ;
    publid stbtid finbl long CWDontPropbgbtf = (1L<<12) ;
    publid stbtid finbl long CWColormbp = (1L<<13) ;
    publid stbtid finbl long CWCursor = (1L<<14) ;

    /* ConfigurfWindow strudturf */

    publid stbtid finbl int CWX = (1<<0) ;
    publid stbtid finbl int CWY = (1<<1) ;
    publid stbtid finbl int CWWidti = (1<<2) ;
    publid stbtid finbl int CWHfigit = (1<<3) ;
    publid stbtid finbl int CWBordfrWidti = (1<<4) ;
    publid stbtid finbl int CWSibling = (1<<5) ;
    publid stbtid finbl int CWStbdkModf = (1<<6) ;


    /* Bit Grbvity */

    publid stbtid finbl int ForgftGrbvity = 0 ;
    publid stbtid finbl int NortiWfstGrbvity = 1 ;
    publid stbtid finbl int NortiGrbvity = 2 ;
    publid stbtid finbl int NortiEbstGrbvity = 3 ;
    publid stbtid finbl int WfstGrbvity = 4 ;
    publid stbtid finbl int CfntfrGrbvity = 5 ;
    publid stbtid finbl int EbstGrbvity = 6 ;
    publid stbtid finbl int SoutiWfstGrbvity = 7 ;
    publid stbtid finbl int SoutiGrbvity = 8 ;
    publid stbtid finbl int SoutiEbstGrbvity = 9 ;
    publid stbtid finbl int StbtidGrbvity = 10 ;

    /* Window grbvity + bit grbvity bbovf */

    publid stbtid finbl int UnmbpGrbvity = 0 ;

    /* Usfd in CrfbtfWindow for bbdking-storf iint */

    publid stbtid finbl int NotUsfful = 0 ;
    publid stbtid finbl int WifnMbppfd = 1 ;
    publid stbtid finbl int Alwbys = 2 ;

    /* Usfd in GftWindowAttributfs rfply */

    publid stbtid finbl int IsUnmbppfd = 0 ;
    publid stbtid finbl int IsUnvifwbblf = 1 ;
    publid stbtid finbl int IsVifwbblf = 2 ;

    /* Usfd in CibngfSbvfSft */

    publid stbtid finbl int SftModfInsfrt = 0 ;
    publid stbtid finbl int SftModfDflftf = 1 ;

    /* Usfd in CibngfClosfDownModf */

    publid stbtid finbl int DfstroyAll = 0 ;
    publid stbtid finbl int RftbinPfrmbnfnt = 1 ;
    publid stbtid finbl int RftbinTfmporbry = 2 ;

    /* Window stbdking mftiod (in donfigurfWindow) */

    publid stbtid finbl int Abovf = 0 ;
    publid stbtid finbl int Bflow = 1 ;
    publid stbtid finbl int TopIf = 2 ;
    publid stbtid finbl int BottomIf = 3 ;
    publid stbtid finbl int Oppositf = 4 ;

    /* Cirdulbtion dirfdtion */

    publid stbtid finbl int RbisfLowfst = 0 ;
    publid stbtid finbl int LowfrHigifst = 1 ;

    /* Propfrty modfs */

    publid stbtid finbl int PropModfRfplbdf = 0 ;
    publid stbtid finbl int PropModfPrfpfnd = 1 ;
    publid stbtid finbl int PropModfAppfnd = 2 ;

    /*****************************************************************
     * GRAPHICS DEFINITIONS
     *****************************************************************/

    /* grbpiids fundtions, bs in GC.blu */

    publid stbtid finbl int GXdlfbr = 0x0 ; /* 0 */
    publid stbtid finbl int GXbnd = 0x1 ; /* srd AND dst */
    publid stbtid finbl int GXbndRfvfrsf = 0x2 ; /* srd AND NOT dst */
    publid stbtid finbl int GXdopy = 0x3 ; /* srd */
    publid stbtid finbl int GXbndInvfrtfd = 0x4 ; /* NOT srd AND dst */
    publid stbtid finbl int GXnoop = 0x5 ; /* dst */
    publid stbtid finbl int GXxor = 0x6 ; /* srd XOR dst */
    publid stbtid finbl int GXor = 0x7 ; /* srd OR dst */
    publid stbtid finbl int GXnor = 0x8 ; /* NOT srd AND NOT dst */
    publid stbtid finbl int GXfquiv = 0x9 ; /* NOT srd XOR dst */
    publid stbtid finbl int GXinvfrt = 0xb ; /* NOT dst */
    publid stbtid finbl int GXorRfvfrsf = 0xb ; /* srd OR NOT dst */
    publid stbtid finbl int GXdopyInvfrtfd = 0xd ; /* NOT srd */
    publid stbtid finbl int GXorInvfrtfd = 0xd ; /* NOT srd OR dst */
    publid stbtid finbl int GXnbnd = 0xf ; /* NOT srd OR NOT dst */
    publid stbtid finbl int GXsft = 0xf ; /* 1 */

    /* LinfStylf */

    publid stbtid finbl int LinfSolid = 0 ;
    publid stbtid finbl int LinfOnOffDbsi = 1 ;
    publid stbtid finbl int LinfDoublfDbsi = 2 ;

    /* dbpStylf */

    publid stbtid finbl int CbpNotLbst = 0 ;
    publid stbtid finbl int CbpButt = 1 ;
    publid stbtid finbl int CbpRound = 2 ;
    publid stbtid finbl int CbpProjfdting = 3 ;

    /* joinStylf */

    publid stbtid finbl int JoinMitfr = 0 ;
    publid stbtid finbl int JoinRound = 1 ;
    publid stbtid finbl int JoinBfvfl = 2 ;

    /* fillStylf */

    publid stbtid finbl int FillSolid = 0 ;
    publid stbtid finbl int FillTilfd = 1 ;
    publid stbtid finbl int FillStipplfd = 2 ;
    publid stbtid finbl int FillOpbqufStipplfd = 3 ;

    /* fillRulf */

    publid stbtid finbl int EvfnOddRulf = 0 ;
    publid stbtid finbl int WindingRulf = 1 ;

    /* subwindow modf */

    publid stbtid finbl int ClipByCiildrfn = 0 ;
    publid stbtid finbl int IndludfInffriors = 1 ;

    /* SftClipRfdtbnglfs ordfring */

    publid stbtid finbl int Unsortfd = 0 ;
    publid stbtid finbl int YSortfd = 1 ;
    publid stbtid finbl int YXSortfd = 2 ;
    publid stbtid finbl int YXBbndfd = 3 ;

    /* CoordinbtfModf for drbwing routinfs */

    publid stbtid finbl int CoordModfOrigin = 0 ; /* rflbtivf to tif origin */
    publid stbtid finbl int CoordModfPrfvious = 1 ; /* rflbtivf to prfvious point */

    /* Polygon sibpfs */

    publid stbtid finbl int Complfx = 0 ; /* pbtis mby intfrsfdt */
    publid stbtid finbl int Nondonvfx = 1 ; /* no pbtis intfrsfdt, but not donvfx */
    publid stbtid finbl int Convfx = 2 ; /* wiolly donvfx */

    /* Ard modfs for PolyFillArd */

    publid stbtid finbl int ArdCiord = 0 ; /* join fndpoints of brd */
    publid stbtid finbl int ArdPifSlidf = 1 ; /* join fndpoints to dfntfr of brd */

    /* GC domponfnts: mbsks usfd in CrfbtfGC, CopyGC, CibngfGC, OR'fd into
       GC.stbtfCibngfs */

    publid stbtid finbl long GCFundtion = (1L<<0) ;
    publid stbtid finbl long GCPlbnfMbsk = (1L<<1) ;
    publid stbtid finbl long GCForfground = (1L<<2) ;
    publid stbtid finbl long GCBbdkground = (1L<<3) ;
    publid stbtid finbl long GCLinfWidti = (1L<<4) ;
    publid stbtid finbl long GCLinfStylf = (1L<<5) ;
    publid stbtid finbl long GCCbpStylf = (1L<<6) ;
    publid stbtid finbl long GCJoinStylf = (1L<<7) ;
    publid stbtid finbl long GCFillStylf = (1L<<8) ;
    publid stbtid finbl long GCFillRulf = (1L<<9) ;
    publid stbtid finbl long GCTilf = (1L<<10) ;
    publid stbtid finbl long GCStipplf = (1L<<11) ;
    publid stbtid finbl long GCTilfStipXOrigin = (1L<<12) ;
    publid stbtid finbl long GCTilfStipYOrigin = (1L<<13) ;
    publid stbtid finbl long GCFont = (1L<<14) ;
    publid stbtid finbl long GCSubwindowModf = (1L<<15) ;
    publid stbtid finbl long GCGrbpiidsExposurfs = (1L<<16) ;
    publid stbtid finbl long GCClipXOrigin = (1L<<17) ;
    publid stbtid finbl long GCClipYOrigin = (1L<<18) ;
    publid stbtid finbl long GCClipMbsk = (1L<<19) ;
    publid stbtid finbl long GCDbsiOffsft = (1L<<20) ;
    publid stbtid finbl long GCDbsiList = (1L<<21) ;
    publid stbtid finbl long GCArdModf = (1L<<22) ;

    publid stbtid finbl int GCLbstBit = 22 ;
    /*****************************************************************
     * FONTS
     *****************************************************************/

    /* usfd in QufryFont -- drbw dirfdtion */

    publid stbtid finbl int FontLfftToRigit = 0 ;
    publid stbtid finbl int FontRigitToLfft = 1 ;

    publid stbtid finbl int FontCibngf = 255 ;

    /*****************************************************************
     *  IMAGING
     *****************************************************************/

    /* ImbgfFormbt -- PutImbgf, GftImbgf */

    publid stbtid finbl int XYBitmbp = 0 ; /* dfpti 1, XYFormbt */
    publid stbtid finbl int XYPixmbp = 1 ; /* dfpti == drbwbblf dfpti */
    publid stbtid finbl int ZPixmbp = 2 ; /* dfpti == drbwbblf dfpti */

    /*****************************************************************
     *  COLOR MAP STUFF
     *****************************************************************/

    /* For CrfbtfColormbp */

    publid stbtid finbl int AllodNonf = 0 ; /* drfbtf mbp witi no fntrifs */
    publid stbtid finbl int AllodAll = 1 ; /* bllodbtf fntirf mbp writfbblf */


    /* Flbgs usfd in StorfNbmfdColor, StorfColors */

    publid stbtid finbl int DoRfd = (1<<0) ;
    publid stbtid finbl int DoGrffn = (1<<1) ;
    publid stbtid finbl int DoBluf = (1<<2) ;

    /*****************************************************************
     * CURSOR STUFF
     *****************************************************************/

    /* QufryBfstSizf Clbss */

    publid stbtid finbl int CursorSibpf = 0 ; /* lbrgfst sizf tibt dbn bf displbyfd */
    publid stbtid finbl int TilfSibpf = 1 ; /* sizf tilfd fbstfst */
    publid stbtid finbl int StipplfSibpf = 2 ; /* sizf stipplfd fbstfst */

    /*****************************************************************
     * KEYBOARD/POINTER STUFF
     *****************************************************************/

    publid stbtid finbl int AutoRfpfbtModfOff = 0 ;
    publid stbtid finbl int AutoRfpfbtModfOn = 1 ;
    publid stbtid finbl int AutoRfpfbtModfDffbult = 2 ;

    publid stbtid finbl int LfdModfOff = 0 ;
    publid stbtid finbl int LfdModfOn = 1 ;

    /* mbsks for CibngfKfybobrdControl */

    publid stbtid finbl long KBKfyClidkPfrdfnt = (1L<<0) ;
    publid stbtid finbl long KBBfllPfrdfnt = (1L<<1) ;
    publid stbtid finbl long KBBfllPitdi = (1L<<2) ;
    publid stbtid finbl long KBBfllDurbtion = (1L<<3) ;
    publid stbtid finbl long KBLfd = (1L<<4) ;
    publid stbtid finbl long KBLfdModf = (1L<<5) ;
    publid stbtid finbl long KBKfy = (1L<<6) ;
    publid stbtid finbl long KBAutoRfpfbtModf = (1L<<7) ;

    publid stbtid finbl int MbppingSuddfss = 0 ;
    publid stbtid finbl int MbppingBusy = 1 ;
    publid stbtid finbl int MbppingFbilfd = 2 ;

    publid stbtid finbl int MbppingModififr = 0 ;
    publid stbtid finbl int MbppingKfybobrd = 1 ;
    publid stbtid finbl int MbppingPointfr = 2 ;

    /*****************************************************************
     * SCREEN SAVER STUFF
     *****************************************************************/

    publid stbtid finbl int DontPrfffrBlbnking = 0 ;
    publid stbtid finbl int PrfffrBlbnking = 1 ;
    publid stbtid finbl int DffbultBlbnking = 2 ;

    publid stbtid finbl int DisbblfSdrffnSbvfr = 0 ;
    publid stbtid finbl int DisbblfSdrffnIntfrvbl = 0 ;

    publid stbtid finbl int DontAllowExposurfs = 0 ;
    publid stbtid finbl int AllowExposurfs = 1 ;
    publid stbtid finbl int DffbultExposurfs = 2 ;

    /* for FordfSdrffnSbvfr */

    publid stbtid finbl int SdrffnSbvfrRfsft = 0 ;
    publid stbtid finbl int SdrffnSbvfrAdtivf = 1 ;

    /*****************************************************************
     * HOSTS AND CONNECTIONS
     *****************************************************************/

    /* for CibngfHosts */

    publid stbtid finbl int HostInsfrt = 0 ;
    publid stbtid finbl int HostDflftf = 1 ;

    /* for CibngfAddfssControl */

    publid stbtid finbl int EnbblfAddfss = 1 ;
    publid stbtid finbl int DisbblfAddfss = 0 ;

    /* Displby dlbssfs  usfd in opfning tif donnfdtion
     * Notf tibt tif stbtidblly bllodbtfd onfs brf fvfn numbfrfd bnd tif
     * dynbmidblly dibngfbblf onfs brf odd numbfrfd */

    publid stbtid finbl int StbtidGrby = 0 ;
    publid stbtid finbl int GrbySdblf = 1 ;
    publid stbtid finbl int StbtidColor = 2 ;
    publid stbtid finbl int PsfudoColor = 3 ;
    publid stbtid finbl int TrufColor = 4 ;
    publid stbtid finbl int DirfdtColor = 5 ;


    /* Bytf ordfr  usfd in imbgfBytfOrdfr bnd bitmbpBitOrdfr */

    publid stbtid finbl int LSBFirst = 0 ;
    publid stbtid finbl int MSBFirst = 1 ;

    /* XKB support */
    publid stbtid finbl int  XkbUsfCorfKbd = 0x0100 ;
    publid stbtid finbl int  XkbNfwKfybobrdNotify = 0;
    publid stbtid finbl int  XkbMbpNotify = 1;
    publid stbtid finbl int  XkbStbtfNotify = 2;
    publid stbtid finbl long XkbNfwKfybobrdNotifyMbsk = (1L << 0);
    publid stbtid finbl long XkbMbpNotifyMbsk = (1L << 1);
    publid stbtid finbl long XkbStbtfNotifyMbsk = (1L << 2);
    publid stbtid finbl long XkbGroupStbtfMbsk  = (1L << 4);
    publid stbtid finbl long XkbKfyTypfsMbsk = (1L<<0);
    publid stbtid finbl long XkbKfySymsMbsk = (1L<<1);
    publid stbtid finbl long XkbModififrMbpMbsk = (1L<<2);
    publid stbtid finbl long XkbVirtublModsMbsk = (1L<<6); //sfrvfr mbp

}
