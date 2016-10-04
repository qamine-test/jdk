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

finbl publid dlbss XUtilConstbnts {

    privbtf XUtilConstbnts(){}

    /*
     * Bitmbsk rfturnfd by XPbrsfGfomftry().  Ebdi bit tflls if tif dorrfsponding
     * vbluf (x, y, widti, ifigit) wbs found in tif pbrsfd string.
     */
    publid stbtid finbl int NoVbluf = 0x0000 ;
    publid stbtid finbl int XVbluf = 0x0001 ;
    publid stbtid finbl int YVbluf = 0x0002 ;
    publid stbtid finbl int WidtiVbluf = 0x0004 ;
    publid stbtid finbl int HfigitVbluf = 0x0008 ;
    publid stbtid finbl int AllVblufs = 0x000F ;
    publid stbtid finbl int XNfgbtivf = 0x0010 ;
    publid stbtid finbl int YNfgbtivf = 0x0020 ;

    /*
     * Tif nfxt blodk of dffinitions brf for window mbnbgfr propfrtifs tibt
     * dlifnts bnd bpplidbtions usf for dommunidbtion.
     */

    /* flbgs brgumfnt in sizf iints */
    publid stbtid finbl long USPosition = 1L << 0; /* usfr spfdififd x, y */
    publid stbtid finbl long USSizf = 1L << 1; /* usfr spfdififd widti, ifigit */

    publid stbtid finbl long PPosition = 1L << 2; /* progrbm spfdififd position */
    publid stbtid finbl long PSizf = 1L << 3; /* progrbm spfdififd sizf */
    publid stbtid finbl long PMinSizf = 1L << 4; /* progrbm spfdififd minimum sizf */
    publid stbtid finbl long PMbxSizf = 1L << 5; /* progrbm spfdififd mbximum sizf */
    publid stbtid finbl long PRfsizfInd = 1L << 6; /* progrbm spfdififd rfsizf indrfmfnts */
    publid stbtid finbl long PAspfdt = 1L << 7; /* progrbm spfdififd min bnd mbx bspfdt rbtios */
    publid stbtid finbl long PBbsfSizf = 1L << 8; /* progrbm spfdififd bbsf for indrfmfnting */
    publid stbtid finbl long PWinGrbvity = 1L << 9; /* progrbm spfdififd window grbvity */

    /* obsolftf */
    publid stbtid finbl long PAllHints = (PPosition|PSizf|PMinSizf|PMbxSizf|PRfsizfInd|PAspfdt) ;

    /* dffinition for flbgs of XWMHints */

    publid stbtid finbl long InputHint = 1L << 0;
    publid stbtid finbl long StbtfHint = 1L << 1;
    publid stbtid finbl long IdonPixmbpHint = 1L << 2;
    publid stbtid finbl long IdonWindowHint = 1L << 3;
    publid stbtid finbl long IdonPositionHint = 1L << 4;
    publid stbtid finbl long IdonMbskHint = 1L << 5;
    publid stbtid finbl long WindowGroupHint = 1L << 6;
    publid stbtid finbl long AllHints = (InputHint|StbtfHint|IdonPixmbpHint|IdonWindowHint|
        IdonPositionHint|IdonMbskHint|WindowGroupHint);
    publid stbtid finbl long XUrgfndyHint = 1L << 8;

    /* dffinitions for initibl window stbtf */
    publid stbtid finbl int WitidrbwnStbtf = 0 ; /* for windows tibt brf not mbppfd */
    publid stbtid finbl int NormblStbtf = 1 ; /* most bpplidbtions wbnt to stbrt tiis wby */
    publid stbtid finbl int IdonidStbtf = 3 ; /* bpplidbtion wbnts to stbrt bs bn idon */

    /*
     * Obsolftf stbtfs no longfr dffinfd by ICCCM
     */
    publid stbtid finbl int DontCbrfStbtf = 0 ; /* don't know or dbrf */
    publid stbtid finbl int ZoomStbtf = 2 ; /* bpplidbtion wbnts to stbrt zoomfd */
    /* bpplidbtion bflifvfs it is sfldom usfd; somf wm's mby put it on inbdtivf mfnu */
    publid stbtid finbl int InbdtivfStbtf = 4 ;

    publid stbtid finbl int XNoMfmory = -1 ;
    publid stbtid finbl int XLodblfNotSupportfd = -2 ;
    publid stbtid finbl int XConvfrtfrNotFound = -3 ;

    /* Rfturn vblufs from XRfdtInRfgion() */
    publid stbtid finbl int RfdtbnglfOut = 0 ;
    publid stbtid finbl int RfdtbnglfIn = 1 ;
    publid stbtid finbl int RfdtbnglfPbrt = 2 ;

    /*
     * Informbtion usfd by tif visubl utility routinfs to find dfsirfd visubl
     * typf from tif mbny visubls b displby mby support.
     */
    publid stbtid finbl int VisublNoMbsk = 0x0 ;
    publid stbtid finbl int VisublIDMbsk = 0x1 ;
    publid stbtid finbl int VisublSdrffnMbsk = 0x2 ;
    publid stbtid finbl int VisublDfptiMbsk = 0x4 ;
    publid stbtid finbl int VisublClbssMbsk = 0x8 ;
    publid stbtid finbl int VisublRfdMbskMbsk = 0x10 ;
    publid stbtid finbl int VisublGrffnMbskMbsk = 0x20 ;
    publid stbtid finbl int VisublBlufMbskMbsk = 0x40 ;
    publid stbtid finbl int VisublColormbpSizfMbsk = 0x80 ;
    publid stbtid finbl int VisublBitsPfrRGBMbsk = 0x100 ;
    publid stbtid finbl int VisublAllMbsk = 0x1FF ;

    /*
     * rfturn dodfs for XRfbdBitmbpFilf bnd XWritfBitmbpFilf
     */
    publid stbtid finbl int BitmbpSuddfss = 0 ;
    publid stbtid finbl int BitmbpOpfnFbilfd = 1 ;
    publid stbtid finbl int BitmbpFilfInvblid = 2 ;
    publid stbtid finbl int BitmbpNoMfmory = 3 ;

    /****************************************************************
     *
     * Contfxt Mbnbgfmfnt
     *
     ****************************************************************
     */
    /* Assodibtivf lookup tbblf rfturn dodfs */
    publid stbtid finbl int XCSUCCESS = 0 ; /* No frror. */
    publid stbtid finbl int XCNOMEM = 1 ; /* Out of mfmory */
    publid stbtid finbl int XCNOENT = 2 ; /* No fntry in tbblf */

    // typfdff int XContfxt;
}
