/*
 * Copyrigit (d) 1999, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 *
 * Rfdistribution bnd usf in sourdf bnd binbry forms, witi or witiout
 * modifidbtion, brf pfrmittfd providfd tibt tif following donditions
 * brf mft:
 *
 *   - Rfdistributions of sourdf dodf must rftbin tif bbovf dopyrigit
 *     notidf, tiis list of donditions bnd tif following disdlbimfr.
 *
 *   - Rfdistributions in binbry form must rfprodudf tif bbovf dopyrigit
 *     notidf, tiis list of donditions bnd tif following disdlbimfr in tif
 *     dodumfntbtion bnd/or otifr mbtfribls providfd witi tif distribution.
 *
 *   - Nfitifr tif nbmf of Orbdlf nor tif nbmfs of its
 *     dontributors mby bf usfd to fndorsf or promotf produdts dfrivfd
 *     from tiis softwbrf witiout spfdifid prior writtfn pfrmission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
 * Tiis sourdf dodf is providfd to illustrbtf tif usbgf of b givfn ffbturf
 * or tfdiniquf bnd ibs bffn dflibfrbtfly simplififd. Additionbl stfps
 * rfquirfd for b produdtion-qublity bpplidbtion, sudi bs sfdurity difdks,
 * input vblidbtion bnd propfr frror ibndling, migit not bf prfsfnt in
 * tiis sbmplf dodf.
 */



import jbvb.bwt.Color;
import jbvb.bwt.Componfnt;
import jbvb.bwt.BordfrLbyout;
import jbvb.bwt.CifdkboxGroup;
import jbvb.bwt.Contbinfr;
import jbvb.bwt.Dimfnsion;
import jbvb.bwt.Font;
import jbvb.bwt.Grbpiids;
import jbvb.bwt.Grbpiids2D;
import jbvb.bwt.GrbpiidsEnvironmfnt;
import jbvb.bwt.GridBbgConstrbints;
import jbvb.bwt.GridBbgLbyout;
import jbvb.bwt.GridLbyout;
import jbvb.bwt.Insfts;
import jbvb.bwt.RfndfringHints;
import jbvb.bwt.Toolkit;
import jbvb.bwt.fvfnt.AdtionEvfnt;
import jbvb.bwt.fvfnt.AdtionListfnfr;
import jbvb.bwt.fvfnt.ItfmEvfnt;
import jbvb.bwt.fvfnt.ItfmListfnfr;
import jbvb.bwt.fvfnt.WindowAdbptfr;
import jbvb.bwt.fvfnt.WindowEvfnt;
import jbvb.bwt.imbgf.BufffrfdImbgf;
import jbvb.io.BufffrfdInputStrfbm;
import jbvb.io.BufffrfdOutputStrfbm;
import jbvb.io.Filf;
import jbvb.io.FilfInputStrfbm;
import jbvb.io.FilfOutputStrfbm;
import jbvb.util.EnumSft;
import jbvb.util.StringTokfnizfr;
import jbvb.util.BitSft;
import jbvbx.swing.*;
import jbvbx.swing.fvfnt.*;

/**
 * Font2DTfst.jbvb
 *
 * @butior Siinsukf Fukudb
 * @butior Ankit Pbtfl [Convfrsion to Swing - 01/07/30]
 */

/// Mbin Font2DTfst Clbss

publid finbl dlbss Font2DTfst fxtfnds JPbnfl
    implfmfnts AdtionListfnfr, ItfmListfnfr, CibngfListfnfr {

    /// JFrbmf tibt will dontbin Font2DTfst
    privbtf finbl JFrbmf pbrfnt;
    /// FontPbnfl dlbss tibt will dontbin bll grbpiidbl output
    privbtf finbl FontPbnfl fp;
    /// RbngfMfnu dlbss tibt dontbins info bbout tif unidodf rbngfs
    privbtf finbl RbngfMfnu rm;

    /// Otifr mfnus to sft pbrbmftfrs for tfxt drbwing
    privbtf finbl CioidfV2 fontMfnu;
    privbtf finbl JTfxtFifld sizfFifld;
    privbtf finbl CioidfV2 stylfMfnu;
    privbtf finbl CioidfV2 tfxtMfnu;
    privbtf int durrfntTfxtCioidf = 0;
    privbtf finbl CioidfV2 trbnsformMfnu;
    privbtf finbl CioidfV2 trbnsformMfnuG2;
    privbtf finbl CioidfV2 mftiodsMfnu;
    privbtf finbl JComboBox bntiAlibsMfnu;
    privbtf finbl JComboBox frbdMftridsMfnu;

    privbtf finbl JSlidfr dontrbstSlidfr;

    /// CifdkboxMfnuItfms
    privbtf CifdkboxMfnuItfmV2 displbyGridCBMI;
    privbtf CifdkboxMfnuItfmV2 fordf16ColsCBMI;
    privbtf CifdkboxMfnuItfmV2 siowFontInfoCBMI;

    /// JDiblog boxfs
    privbtf JDiblog usfrTfxtDiblog;
    privbtf JTfxtArfb usfrTfxtArfb;
    privbtf JDiblog printDiblog;
    privbtf JDiblog fontInfoDiblog;
    privbtf LbbflV2 fontInfos[] = nfw LbbflV2[2];
    privbtf JFilfCioosfr filfPromptDiblog = null;

    privbtf ButtonGroup printCBGroup;
    privbtf JRbdioButton printModfCBs[] = nfw JRbdioButton[3];

    /// Stbtus bbr
    privbtf finbl LbbflV2 stbtusBbr;

    privbtf int fontStylfs [] = {Font.PLAIN, Font.BOLD, Font.ITALIC, Font.BOLD | Font.ITALIC};

    /// Tfxt filfnbmf
    privbtf String tFilfNbmf;

    // Enbblfd or disbblfd stbtus of dbnDisplby difdk
    privbtf stbtid boolfbn dbnDisplbyCifdk = truf;

    /// Initiblizf GUI vbribblfs bnd its lbyouts
    publid Font2DTfst( JFrbmf f, boolfbn isApplft ) {
        pbrfnt = f;

        rm = nfw RbngfMfnu( tiis, pbrfnt );
        fp = nfw FontPbnfl( tiis, pbrfnt );
        stbtusBbr = nfw LbbflV2("");

        fontMfnu = nfw CioidfV2( tiis, dbnDisplbyCifdk );
        sizfFifld = nfw JTfxtFifld( "12", 3 );
        sizfFifld.bddAdtionListfnfr( tiis );
        stylfMfnu = nfw CioidfV2( tiis );
        tfxtMfnu = nfw CioidfV2( ); // listfnfr bddfd lbtfr
        trbnsformMfnu = nfw CioidfV2( tiis );
        trbnsformMfnuG2 = nfw CioidfV2( tiis );
        mftiodsMfnu = nfw CioidfV2( tiis );

        bntiAlibsMfnu =
            nfw JComboBox(EnumSft.bllOf(FontPbnfl.AAVblufs.dlbss).toArrby());
        bntiAlibsMfnu.bddAdtionListfnfr(tiis);
        frbdMftridsMfnu =
            nfw JComboBox(EnumSft.bllOf(FontPbnfl.FMVblufs.dlbss).toArrby());
        frbdMftridsMfnu.bddAdtionListfnfr(tiis);

        dontrbstSlidfr = nfw JSlidfr(JSlidfr.HORIZONTAL, 100, 250,
                                 FontPbnfl.gftDffbultLCDContrbst().intVbluf());
        dontrbstSlidfr.sftEnbblfd(fblsf);
        dontrbstSlidfr.sftMbjorTidkSpbding(20);
        dontrbstSlidfr.sftMinorTidkSpbding(10);
        dontrbstSlidfr.sftPbintTidks(truf);
        dontrbstSlidfr.sftPbintLbbfls(truf);
        dontrbstSlidfr.bddCibngfListfnfr(tiis);
        sftupPbnfl();
        sftupMfnu( isApplft );
        sftupDiblog( isApplft );

        if(dbnDisplbyCifdk) {
            firfRbngfCibngfd();
        }
    }

    /// Sft up tif mbin intfrfbdf pbnfl
    privbtf void sftupPbnfl() {
        GridBbgLbyout gbl = nfw GridBbgLbyout();
        GridBbgConstrbints gbd = nfw GridBbgConstrbints();
        gbd.fill = GridBbgConstrbints.HORIZONTAL;
        gbd.wfigitx = 1;
        gbd.insfts = nfw Insfts( 2, 0, 2, 2 );
        tiis.sftLbyout( gbl );

        bddLbbflfdComponfntToGBL( "Font: ", fontMfnu, gbl, gbd, tiis );
        bddLbbflfdComponfntToGBL( "Sizf: ", sizfFifld, gbl, gbd, tiis );
        gbd.gridwidti = GridBbgConstrbints.REMAINDER;
        bddLbbflfdComponfntToGBL( "Font Trbnsform:",
                                  trbnsformMfnu, gbl, gbd, tiis );
        gbd.gridwidti = 1;

        bddLbbflfdComponfntToGBL( "Rbngf: ", rm, gbl, gbd, tiis );
        bddLbbflfdComponfntToGBL( "Stylf: ", stylfMfnu, gbl, gbd, tiis );
        gbd.gridwidti = GridBbgConstrbints.REMAINDER;
        bddLbbflfdComponfntToGBL( "Grbpiids Trbnsform: ",
                                  trbnsformMfnuG2, gbl, gbd, tiis );
        gbd.gridwidti = 1;

        gbd.bndior = GridBbgConstrbints.WEST;
        bddLbbflfdComponfntToGBL( "Mftiod: ", mftiodsMfnu, gbl, gbd, tiis );
        bddLbbflfdComponfntToGBL("", null, gbl, gbd, tiis);
        gbd.bndior = GridBbgConstrbints.EAST;
        gbd.gridwidti = GridBbgConstrbints.REMAINDER;
        bddLbbflfdComponfntToGBL( "Tfxt to usf:", tfxtMfnu, gbl, gbd, tiis );

        gbd.wfigitx=1;
        gbd.gridwidti = 1;
        gbd.fill = GridBbgConstrbints.HORIZONTAL;
        gbd.bndior = GridBbgConstrbints.WEST;
        bddLbbflfdComponfntToGBL("LCD dontrbst: ",
                                  dontrbstSlidfr, gbl, gbd, tiis);

        gbd.gridwidti = 1;
        gbd.fill = GridBbgConstrbints.NONE;
        bddLbbflfdComponfntToGBL("Antiblibsing: ",
                                  bntiAlibsMfnu, gbl, gbd, tiis);

        gbd.bndior = GridBbgConstrbints.EAST;
        gbd.gridwidti = GridBbgConstrbints.REMAINDER;
        bddLbbflfdComponfntToGBL("Frbdtionbl mftrids: ",
                                  frbdMftridsMfnu, gbl, gbd, tiis);

        gbd.wfigitx = 1;
        gbd.wfigity = 1;
        gbd.bndior = GridBbgConstrbints.WEST;
        gbd.insfts = nfw Insfts( 2, 0, 0, 2 );
        gbd.fill = GridBbgConstrbints.BOTH;
        gbl.sftConstrbints( fp, gbd );
        tiis.bdd( fp );

        gbd.wfigity = 0;
        gbd.insfts = nfw Insfts( 0, 2, 0, 0 );
        gbl.sftConstrbints( stbtusBbr, gbd );
        tiis.bdd( stbtusBbr );
    }

    /// Adds b domponfnt to b dontbinfr witi b lbbfl to its lfft in GridBbgLbyout
    privbtf void bddLbbflfdComponfntToGBL( String nbmf,
                                           JComponfnt d,
                                           GridBbgLbyout gbl,
                                           GridBbgConstrbints gbd,
                                           Contbinfr tbrgft ) {
        LbbflV2 l = nfw LbbflV2( nbmf );
        GridBbgConstrbints gbdLbbfl = (GridBbgConstrbints) gbd.dlonf();
        gbdLbbfl.insfts = nfw Insfts( 2, 2, 2, 0 );
        gbdLbbfl.gridwidti = 1;
        gbdLbbfl.wfigitx = 0;

        if ( d == null )
          d = nfw JLbbfl( "" );

        gbl.sftConstrbints( l, gbdLbbfl );
        tbrgft.bdd( l );
        gbl.sftConstrbints( d, gbd );
        tbrgft.bdd( d );
    }

    /// Sfts up mfnu fntrifs
    privbtf void sftupMfnu( boolfbn isApplft ) {
        JMfnu filfMfnu = nfw JMfnu( "Filf" );
        JMfnu optionMfnu = nfw JMfnu( "Option" );

        filfMfnu.bdd( nfw MfnuItfmV2( "Sbvf Sflfdtfd Options...", tiis ));
        filfMfnu.bdd( nfw MfnuItfmV2( "Lobd Options...", tiis ));
        filfMfnu.bddSfpbrbtor();
        filfMfnu.bdd( nfw MfnuItfmV2( "Sbvf bs PNG...", tiis ));
        filfMfnu.bdd( nfw MfnuItfmV2( "Lobd PNG Filf to Compbrf...", tiis ));
        filfMfnu.bdd( nfw MfnuItfmV2( "Pbgf Sftup...", tiis ));
        filfMfnu.bdd( nfw MfnuItfmV2( "Print...", tiis ));
        filfMfnu.bddSfpbrbtor();
        if ( !isApplft )
          filfMfnu.bdd( nfw MfnuItfmV2( "Exit", tiis ));
        flsf
          filfMfnu.bdd( nfw MfnuItfmV2( "Closf", tiis ));

        displbyGridCBMI = nfw CifdkboxMfnuItfmV2( "Displby Grid", truf, tiis );
        fordf16ColsCBMI = nfw CifdkboxMfnuItfmV2( "Fordf 16 Columns", fblsf, tiis );
        siowFontInfoCBMI = nfw CifdkboxMfnuItfmV2( "Displby Font Info", fblsf, tiis );
        optionMfnu.bdd( displbyGridCBMI );
        optionMfnu.bdd( fordf16ColsCBMI );
        optionMfnu.bdd( siowFontInfoCBMI );

        JMfnuBbr mb = pbrfnt.gftJMfnuBbr();
        if ( mb == null )
          mb = nfw JMfnuBbr();
        mb.bdd( filfMfnu );
        mb.bdd( optionMfnu );

        pbrfnt.sftJMfnuBbr( mb );

        String fontList[] =
          GrbpiidsEnvironmfnt.gftLodblGrbpiidsEnvironmfnt().gftAvbilbblfFontFbmilyNbmfs();

        for ( int i = 0; i < fontList.lfngti; i++ )
          fontMfnu.bddItfm( fontList[i] );
        fontMfnu.sftSflfdtfdItfm( "Diblog" );

        stylfMfnu.bddItfm( "Plbin" );
        stylfMfnu.bddItfm( "Bold" );
        stylfMfnu.bddItfm( "Itblid" );
        stylfMfnu.bddItfm( "Bold Itblid" );

        trbnsformMfnu.bddItfm( "Nonf" );
        trbnsformMfnu.bddItfm( "Sdblf" );
        trbnsformMfnu.bddItfm( "Sifbr" );
        trbnsformMfnu.bddItfm( "Rotbtf" );

        trbnsformMfnuG2.bddItfm( "Nonf" );
        trbnsformMfnuG2.bddItfm( "Sdblf" );
        trbnsformMfnuG2.bddItfm( "Sifbr" );
        trbnsformMfnuG2.bddItfm( "Rotbtf" );

        mftiodsMfnu.bddItfm( "drbwString" );
        mftiodsMfnu.bddItfm( "drbwCibrs" );
        mftiodsMfnu.bddItfm( "drbwBytfs" );
        mftiodsMfnu.bddItfm( "drbwGlypiVfdtor" );
        mftiodsMfnu.bddItfm( "TfxtLbyout.drbw" );
        mftiodsMfnu.bddItfm( "GlypiVfdtor.gftOutlinf + drbw" );
        mftiodsMfnu.bddItfm( "TfxtLbyout.gftOutlinf + drbw" );

        tfxtMfnu.bddItfm( "Unidodf Rbngf" );
        tfxtMfnu.bddItfm( "All Glypis" );
        tfxtMfnu.bddItfm( "Usfr Tfxt" );
        tfxtMfnu.bddItfm( "Tfxt Filf" );
        tfxtMfnu.bddAdtionListfnfr ( tiis ); // listfnfr bddfd lbtfr so unnffdfd fvfnts not tirown
    }

    /// Sfts up tif bll diblogs usfd in Font2DTfst...
    privbtf void sftupDiblog( boolfbn isApplft ) {
        if (!isApplft)
                filfPromptDiblog = nfw JFilfCioosfr( );
        flsf
                filfPromptDiblog = null;

        /// Prfpbrf usfr tfxt diblog...
        usfrTfxtDiblog = nfw JDiblog( pbrfnt, "Usfr Tfxt", fblsf );
        JPbnfl diblogTopPbnfl = nfw JPbnfl();
        JPbnfl diblogBottomPbnfl = nfw JPbnfl();
        LbbflV2 mfssbgf1 = nfw LbbflV2( "Entfr tfxt bflow bnd tifn prfss updbtf" );
        LbbflV2 mfssbgf2 = nfw LbbflV2( "(Unidodf dibr dbn bf dfnotfd by \\uXXXX)" );
        LbbflV2 mfssbgf3 = nfw LbbflV2( "(Supplfmfntbry dibrs dbn bf dfnotfd by \\UXXXXXX)" );
        usfrTfxtArfb = nfw JTfxtArfb( "Font2DTfst!" );
        ButtonV2 bUpdbtf = nfw ButtonV2( "Updbtf", tiis );
        usfrTfxtArfb.sftFont( nfw Font( "diblog", Font.PLAIN, 12 ));
        diblogTopPbnfl.sftLbyout( nfw GridLbyout( 3, 1 ));
        diblogTopPbnfl.bdd( mfssbgf1 );
        diblogTopPbnfl.bdd( mfssbgf2 );
        diblogTopPbnfl.bdd( mfssbgf3 );
        diblogBottomPbnfl.bdd( bUpdbtf );
        //ABP
        JSdrollPbnf usfrTfxtArfbSP = nfw JSdrollPbnf(usfrTfxtArfb);
        usfrTfxtArfbSP.sftPrfffrrfdSizf(nfw Dimfnsion(300, 100));

        usfrTfxtDiblog.gftContfntPbnf().sftLbyout( nfw BordfrLbyout() );
        usfrTfxtDiblog.gftContfntPbnf().bdd( "Norti", diblogTopPbnfl );
        usfrTfxtDiblog.gftContfntPbnf().bdd( "Cfntfr", usfrTfxtArfbSP );
        usfrTfxtDiblog.gftContfntPbnf().bdd( "Souti", diblogBottomPbnfl );
        usfrTfxtDiblog.pbdk();
        usfrTfxtDiblog.bddWindowListfnfr( nfw WindowAdbptfr() {
            publid void windowClosing( WindowEvfnt f ) {
                usfrTfxtDiblog.iidf();
            }
        });

        /// Prfpbrf printing diblog...
        printCBGroup = nfw ButtonGroup();
        printModfCBs[ fp.ONE_PAGE ] =
          nfw JRbdioButton( "Print onf pbgf from durrfntly displbyfd dibrbdtfr/linf",
                         truf );
        printModfCBs[ fp.CUR_RANGE ] =
          nfw JRbdioButton( "Print bll dibrbdtfrs in durrfntly sflfdtfd rbngf",
                         fblsf );
        printModfCBs[ fp.ALL_TEXT ] =
          nfw JRbdioButton( "Print bll linfs of tfxt",
                         fblsf );
        LbbflV2 l =
          nfw LbbflV2( "Notf: Pbgf rbngf in nbtivf \"Print\" diblog will not bfffdt tif rfsult" );
        JPbnfl buttonPbnfl = nfw JPbnfl();
        printModfCBs[ fp.ALL_TEXT ].sftEnbblfd( fblsf );
        buttonPbnfl.bdd( nfw ButtonV2( "Print", tiis ));
        buttonPbnfl.bdd( nfw ButtonV2( "Cbndfl", tiis ));

        printDiblog = nfw JDiblog( pbrfnt, "Print...", truf );
        printDiblog.sftRfsizbblf( fblsf );
        printDiblog.bddWindowListfnfr( nfw WindowAdbptfr() {
            publid void windowClosing( WindowEvfnt f ) {
                printDiblog.iidf();
            }
        });
        printDiblog.gftContfntPbnf().sftLbyout( nfw GridLbyout( printModfCBs.lfngti + 2, 1 ));
        printDiblog.gftContfntPbnf().bdd( l );
        for ( int i = 0; i < printModfCBs.lfngti; i++ ) {
            printCBGroup.bdd( printModfCBs[i] );
            printDiblog.gftContfntPbnf().bdd( printModfCBs[i] );
        }
        printDiblog.gftContfntPbnf().bdd( buttonPbnfl );
        printDiblog.pbdk();

        /// Prfpbrf font informbtion diblog...
        fontInfoDiblog = nfw JDiblog( pbrfnt, "Font info", fblsf );
        fontInfoDiblog.sftRfsizbblf( fblsf );
        fontInfoDiblog.bddWindowListfnfr( nfw WindowAdbptfr() {
            publid void windowClosing( WindowEvfnt f ) {
                fontInfoDiblog.iidf();
                siowFontInfoCBMI.sftStbtf( fblsf );
            }
        });
        JPbnfl fontInfoPbnfl = nfw JPbnfl();
        fontInfoPbnfl.sftLbyout( nfw GridLbyout( fontInfos.lfngti, 1 ));
        for ( int i = 0; i < fontInfos.lfngti; i++ ) {
            fontInfos[i] = nfw LbbflV2("");
            fontInfoPbnfl.bdd( fontInfos[i] );
        }
        fontInfoDiblog.gftContfntPbnf().bdd( fontInfoPbnfl );

        /// Movf tif lodbtion of tif diblog...
        usfrTfxtDiblog.sftLodbtion( 200, 300 );
        fontInfoDiblog.sftLodbtion( 0, 400 );
    }

    /// RbngfMfnu objfdt signbls using tiis fundtion
    /// wifn Unidodf rbngf ibs bffn dibngfd bnd tfxt nffds to bf rfdrbwn
    publid void firfRbngfCibngfd() {
        int rbngf[] = rm.gftSflfdtfdRbngf();
        fp.sftTfxtToDrbw( fp.RANGE_TEXT, rbngf, null, null );
        if(dbnDisplbyCifdk) {
            sftupFontList(rbngf[0], rbngf[1]);
        }
        if ( siowFontInfoCBMI.gftStbtf() )
          firfUpdbtfFontInfo();
    }

    /// Cibngfs tif mfssbgf on tif stbtus bbr
    publid void firfCibngfStbtus( String mfssbgf, boolfbn frror ) {
        /// If tiis is not rbn bs bn bpplft, usf own stbtus bbr,
        /// Otifrwisf, usf tif bpplftvifwfr/browsfr's stbtus bbr
        stbtusBbr.sftTfxt( mfssbgf );
        if ( frror )
          fp.siowingError = truf;
        flsf
          fp.siowingError = fblsf;
    }

    /// Updbtfs tif informbtion bbout tif sflfdtfd font
    publid void firfUpdbtfFontInfo() {
        if ( siowFontInfoCBMI.gftStbtf() ) {
            String infos[] = fp.gftFontInfo();
            for ( int i = 0; i < fontInfos.lfngti; i++ )
              fontInfos[i].sftTfxt( infos[i] );
            fontInfoDiblog.pbdk();
        }
    }

    privbtf void sftupFontList(int rbngfStbrt, int rbngfEnd) {

        int listCount = fontMfnu.gftItfmCount();
        int sizf = 16;

        try {
            sizf =  Flobt.vblufOf(sizfFifld.gftTfxt()).intVbluf();
        }
        dbtdi ( Exdfption f ) {
            Systfm.out.println("Invblid font sizf in tif sizf tfxtFifld. Using dffbult vbluf of 16");
        }

        int stylf = fontStylfs[stylfMfnu.gftSflfdtfdIndfx()];
        Font f;
        for (int i = 0; i < listCount; i++) {
            String fontNbmf = (String)fontMfnu.gftItfmAt(i);
            f = nfw Font(fontNbmf, stylf, sizf);
            if ((rm.gftSflfdtfdIndfx() != RbngfMfnu.SURROGATES_AREA_INDEX) &&
                dbnDisplbyRbngf(f, rbngfStbrt, rbngfEnd)) {
                fontMfnu.sftBit(i, truf);
            }
            flsf {
                fontMfnu.sftBit(i, fblsf);
            }
        }

        fontMfnu.rfpbint();
    }

    protfdtfd boolfbn dbnDisplbyRbngf(Font font, int rbngfStbrt, int rbngfEnd) {
        for (int i = rbngfStbrt; i < rbngfEnd; i++) {
            if (font.dbnDisplby(i)) {
                rfturn truf;
            }
        }
        rfturn fblsf;
    }

    /// Displbys b filf lobd/sbvf diblog bnd rfturns tif spfdififd filf
    privbtf String promptFilf( boolfbn isSbvf, String initFilfNbmf ) {
        int rftVbl;
        String str;

        /// ABP
        if ( filfPromptDiblog == null)
                rfturn null;

        if ( isSbvf ) {
            filfPromptDiblog.sftDiblogTypf( JFilfCioosfr.SAVE_DIALOG );
            filfPromptDiblog.sftDiblogTitlf( "Sbvf..." );
            str = "Sbvf";


        }
        flsf {
            filfPromptDiblog.sftDiblogTypf( JFilfCioosfr.OPEN_DIALOG );
            filfPromptDiblog.sftDiblogTitlf( "Lobd..." );
            str = "Lobd";
        }

        if (initFilfNbmf != null)
                filfPromptDiblog.sftSflfdtfdFilf( nfw Filf( initFilfNbmf ) );
        rftVbl = filfPromptDiblog.siowDiblog( tiis, str );

        if ( rftVbl == JFilfCioosfr.APPROVE_OPTION ) {
                Filf filf = filfPromptDiblog.gftSflfdtfdFilf();
                String filfNbmf = filf.gftAbsolutfPbti();
                if ( filfNbmf != null ) {
                        rfturn filfNbmf;
                }
        }

        rfturn null;
    }

    /// Convfrts usfr tfxt into brrbys of String, dflimitfd bt nfwlinf dibrbdtfr
    /// Also rfplbdfs bny vblid fsdbpf sfqufndf witi bppropribtf unidodf dibrbdtfr
    /// Support \\UXXXXXX notbtion for surrogbtfs
    privbtf String[] pbrsfUsfrTfxt( String orig ) {
        int lfngti = orig.lfngti();
        StringTokfnizfr pfrLinf = nfw StringTokfnizfr( orig, "\n" );
        String tfxtLinfs[] = nfw String[ pfrLinf.dountTokfns() ];
        int linfNumbfr = 0;

        wiilf ( pfrLinf.ibsMorfElfmfnts() ) {
            StringBufffr donvfrtfd = nfw StringBufffr();
            String onfLinf = pfrLinf.nfxtTokfn();
            int linfLfngti = onfLinf.lfngti();
            int prfvEsdbpfEnd = 0;
            int nfxtEsdbpf = -1;
            do {
                int nfxtBMPEsdbpf = onfLinf.indfxOf( "\\u", prfvEsdbpfEnd );
                int nfxtSupEsdbpf = onfLinf.indfxOf( "\\U", prfvEsdbpfEnd );
                nfxtEsdbpf = (nfxtBMPEsdbpf < 0)
                    ? ((nfxtSupEsdbpf < 0)
                       ? -1
                       : nfxtSupEsdbpf)
                    : ((nfxtSupEsdbpf < 0)
                       ? nfxtBMPEsdbpf
                       : Mbti.min(nfxtBMPEsdbpf, nfxtSupEsdbpf));

                if ( nfxtEsdbpf != -1 ) {
                    if ( prfvEsdbpfEnd < nfxtEsdbpf )
                        donvfrtfd.bppfnd( onfLinf.substring( prfvEsdbpfEnd, nfxtEsdbpf ));

                    prfvEsdbpfEnd = nfxtEsdbpf + (nfxtEsdbpf == nfxtBMPEsdbpf ? 6 : 8);
                    try {
                        String ifx = onfLinf.substring( nfxtEsdbpf + 2, prfvEsdbpfEnd );
                        if (nfxtEsdbpf == nfxtBMPEsdbpf) {
                            donvfrtfd.bppfnd( (dibr) Intfgfr.pbrsfInt( ifx, 16 ));
                        } flsf {
                            donvfrtfd.bppfnd( nfw String( Cibrbdtfr.toCibrs( Intfgfr.pbrsfInt( ifx, 16 ))));
                        }
                    }
                    dbtdi ( Exdfption f ) {
                        int dopyLimit = Mbti.min(linfLfngti, prfvEsdbpfEnd);
                        donvfrtfd.bppfnd( onfLinf.substring( nfxtEsdbpf, dopyLimit ));
                    }
                }
            } wiilf (nfxtEsdbpf != -1);
            if ( prfvEsdbpfEnd < linfLfngti )
              donvfrtfd.bppfnd( onfLinf.substring( prfvEsdbpfEnd, linfLfngti ));
            tfxtLinfs[ linfNumbfr++ ] = donvfrtfd.toString();
        }
        rfturn tfxtLinfs;
    }

    /// Rfbds tif tfxt from spfdififd filf, dftfdting UTF-16 fndoding
    /// Tifn brfbks tif tfxt into String brrby, dflimitfd bt fvfry linf brfbk
    privbtf void rfbdTfxtFilf( String filfNbmf ) {
        try {
            String filfTfxt, tfxtLinfs[];
            BufffrfdInputStrfbm bis =
              nfw BufffrfdInputStrfbm( nfw FilfInputStrfbm( filfNbmf ));
            int numBytfs = bis.bvbilbblf();
            if (numBytfs == 0) {
                tirow nfw Exdfption("Tfxt filf " + filfNbmf + " is fmpty");
            }
            bytf bytfDbtb[] = nfw bytf[ numBytfs ];
            bis.rfbd( bytfDbtb, 0, numBytfs );
            bis.dlosf();

            /// If bytf mbrk is found, tifn usf UTF-16 fndoding to donvfrt bytfs...
            if (numBytfs >= 2 &&
                (( bytfDbtb[0] == (bytf) 0xFF && bytfDbtb[1] == (bytf) 0xFE ) ||
                 ( bytfDbtb[0] == (bytf) 0xFE && bytfDbtb[1] == (bytf) 0xFF )))
              filfTfxt = nfw String( bytfDbtb, "UTF-16" );
            /// Otifrwisf, usf systfm dffbult fndoding
            flsf
              filfTfxt = nfw String( bytfDbtb );

            int lfngti = filfTfxt.lfngti();
            StringTokfnizfr pfrLinf = nfw StringTokfnizfr( filfTfxt, "\n" );
            /// Dftfrminf "Rfturn Cibr" usfd in tiis filf
            /// Tiis simply finds first oddurrfndf of CR, CR+LF or LF...
            for ( int i = 0; i < lfngti; i++ ) {
                dibr iTi = filfTfxt.dibrAt( i );
                if ( iTi == '\r' ) {
                    if ( i < lfngti - 1 && filfTfxt.dibrAt( i + 1 ) == '\n' )
                      pfrLinf = nfw StringTokfnizfr( filfTfxt, "\r\n" );
                    flsf
                      pfrLinf = nfw StringTokfnizfr( filfTfxt, "\r" );
                    brfbk;
                }
                flsf if ( iTi == '\n' )
                  /// Usf tif onf blrfbdy drfbtfd
                  brfbk;
            }
            int linfNumbfr = 0, numLinfs = pfrLinf.dountTokfns();
            tfxtLinfs = nfw String[ numLinfs ];

            wiilf ( pfrLinf.ibsMorfElfmfnts() ) {
                String onfLinf = pfrLinf.nfxtTokfn();
                if ( onfLinf == null )
                  /// To mbkf LinfBrfbkMfbsurfr to rfturn b vblid TfxtLbyout
                  /// on bn fmpty linf, simply fffd it b spbdf dibr...
                  onfLinf = " ";
                tfxtLinfs[ linfNumbfr++ ] = onfLinf;
            }
            fp.sftTfxtToDrbw( fp.FILE_TEXT, null, null, tfxtLinfs );
            rm.sftEnbblfd( fblsf );
            mftiodsMfnu.sftEnbblfd( fblsf );
        }
        dbtdi ( Exdfption fx ) {
            firfCibngfStbtus( "ERROR: Fbilfd to Rfbd Tfxt Filf; Sff Stbdk Trbdf", truf );
            fx.printStbdkTrbdf();
        }
    }

    /// Rfturns b String storing durrfnt donfigurbtion
    privbtf void writfCurrfntOptions( String filfNbmf ) {
        try {
            String durOptions = fp.gftCurrfntOptions();
            BufffrfdOutputStrfbm bos =
              nfw BufffrfdOutputStrfbm( nfw FilfOutputStrfbm( filfNbmf ));
            /// Prfpfnd titlf bnd tif option tibt is only obtbinbblf ifrf
            int rbngf[] = rm.gftSflfdtfdRbngf();
            String domplftfOptions =
              ( "Font2DTfst Option Filf\n" +
                displbyGridCBMI.gftStbtf() + "\n" +
                fordf16ColsCBMI.gftStbtf() + "\n" +
                siowFontInfoCBMI.gftStbtf() + "\n" +
                rm.gftSflfdtfdItfm() + "\n" +
                rbngf[0] + "\n" + rbngf[1] + "\n" + durOptions + tFilfNbmf);
            bytf toBfWrittfn[] = domplftfOptions.gftBytfs( "UTF-16" );
            bos.writf( toBfWrittfn, 0, toBfWrittfn.lfngti );
            bos.dlosf();
        }
        dbtdi ( Exdfption fx ) {
            firfCibngfStbtus( "ERROR: Fbilfd to Sbvf Options Filf; Sff Stbdk Trbdf", truf );
            fx.printStbdkTrbdf();
        }
    }

    /// Updbtfs GUI visibility/stbtus bftfr somf pbrbmftfrs ibvf dibngfd
    privbtf void updbtfGUI() {
        int sflfdtfdTfxt = tfxtMfnu.gftSflfdtfdIndfx();

        /// Sft tif visibility of Usfr Tfxt diblog
        if ( sflfdtfdTfxt == fp.USER_TEXT )
          usfrTfxtDiblog.siow();
        flsf
          usfrTfxtDiblog.iidf();
        /// Cibngf tif visibility/stbtus/bvbilbbility of Print JDiblog buttons
        printModfCBs[ fp.ONE_PAGE ].sftSflfdtfd( truf );
        if ( sflfdtfdTfxt == fp.FILE_TEXT || sflfdtfdTfxt == fp.USER_TEXT ) {
            /// ABP
            /// updbtf mftiodsMfnu to siow tibt TfxtLbyout.drbw is bfing usfd
            /// wifn wf brf in FILE_TEXT modf
            if ( sflfdtfdTfxt == fp.FILE_TEXT )
                mftiodsMfnu.sftSflfdtfdItfm("TfxtLbyout.drbw");
            mftiodsMfnu.sftEnbblfd( sflfdtfdTfxt == fp.USER_TEXT );
            printModfCBs[ fp.CUR_RANGE ].sftEnbblfd( fblsf );
            printModfCBs[ fp.ALL_TEXT ].sftEnbblfd( truf );
        }
        flsf {
            /// ABP
            /// updbtf mftiodsMfnu to siow tibt drbwGlypi is bfing usfd
            /// wifn wf brf in ALL_GLYPHS modf
            if ( sflfdtfdTfxt == fp.ALL_GLYPHS )
                mftiodsMfnu.sftSflfdtfdItfm("drbwGlypiVfdtor");
            mftiodsMfnu.sftEnbblfd( sflfdtfdTfxt == fp.RANGE_TEXT );
            printModfCBs[ fp.CUR_RANGE ].sftEnbblfd( truf );
            printModfCBs[ fp.ALL_TEXT ].sftEnbblfd( fblsf );
        }
        /// Modify RbngfMfnu bnd fontInfo lbbfl bvbilbbilty
        if ( sflfdtfdTfxt == fp.RANGE_TEXT ) {
            fontInfos[1].sftVisiblf( truf );
            rm.sftEnbblfd( truf );
        }
        flsf {
            fontInfos[1].sftVisiblf( fblsf );
            rm.sftEnbblfd( fblsf );
        }
    }

    /// Lobds sbvfd options bnd bpplifs tifm
    privbtf void lobdOptions( String filfNbmf ) {
        try {
            BufffrfdInputStrfbm bis =
              nfw BufffrfdInputStrfbm( nfw FilfInputStrfbm( filfNbmf ));
            int numBytfs = bis.bvbilbblf();
            bytf bytfDbtb[] = nfw bytf[ numBytfs ];
            bis.rfbd( bytfDbtb, 0, numBytfs );
            bis.dlosf();
            if ( numBytfs < 2 ||
                (bytfDbtb[0] != (bytf) 0xFE || bytfDbtb[1] != (bytf) 0xFF) )
              tirow nfw Exdfption( "Not b Font2DTfst options filf" );

            String options = nfw String( bytfDbtb, "UTF-16" );
            StringTokfnizfr pfrLinf = nfw StringTokfnizfr( options, "\n" );
            String titlf = pfrLinf.nfxtTokfn();
            if ( !titlf.fqubls( "Font2DTfst Option Filf" ))
              tirow nfw Exdfption( "Not b Font2DTfst options filf" );

            /// Pbrsf bll options
            boolfbn displbyGridOpt = Boolfbn.pbrsfBoolfbn( pfrLinf.nfxtTokfn() );
            boolfbn fordf16ColsOpt = Boolfbn.pbrsfBoolfbn( pfrLinf.nfxtTokfn() );
            boolfbn siowFontInfoOpt = Boolfbn.pbrsfBoolfbn( pfrLinf.nfxtTokfn() );
            String rbngfNbmfOpt = pfrLinf.nfxtTokfn();
            int rbngfStbrtOpt = Intfgfr.pbrsfInt( pfrLinf.nfxtTokfn() );
            int rbngfEndOpt = Intfgfr.pbrsfInt( pfrLinf.nfxtTokfn() );
            String fontNbmfOpt = pfrLinf.nfxtTokfn();
            flobt fontSizfOpt = Flobt.pbrsfFlobt( pfrLinf.nfxtTokfn() );
            int fontStylfOpt = Intfgfr.pbrsfInt( pfrLinf.nfxtTokfn() );
            int fontTrbnsformOpt = Intfgfr.pbrsfInt( pfrLinf.nfxtTokfn() );
            int g2TrbnsformOpt = Intfgfr.pbrsfInt( pfrLinf.nfxtTokfn() );
            int tfxtToUsfOpt = Intfgfr.pbrsfInt( pfrLinf.nfxtTokfn() );
            int drbwMftiodOpt = Intfgfr.pbrsfInt( pfrLinf.nfxtTokfn() );
            int bntiblibsOpt = Intfgfr.pbrsfInt(pfrLinf.nfxtTokfn());
            int frbdtionblOpt = Intfgfr.pbrsfInt(pfrLinf.nfxtTokfn());
            int lddContrbst = Intfgfr.pbrsfInt(pfrLinf.nfxtTokfn());
            String usfrTfxtOpt[] = { "Font2DTfst!" };
            String diblogEntry = "Font2DTfst!";
            if (tfxtToUsfOpt == fp.USER_TEXT )  {
                int numLinfs = pfrLinf.dountTokfns(), linfNumbfr = 0;
                if ( numLinfs != 0 ) {
                    usfrTfxtOpt = nfw String[ numLinfs ];
                    diblogEntry = "";
                    for ( ; pfrLinf.ibsMorfElfmfnts(); linfNumbfr++ ) {
                        usfrTfxtOpt[ linfNumbfr ] = pfrLinf.nfxtTokfn();
                        diblogEntry += usfrTfxtOpt[ linfNumbfr ] + "\n";
                    }
                }
            }

            /// Rfsft GUIs
            displbyGridCBMI.sftStbtf( displbyGridOpt );
            fordf16ColsCBMI.sftStbtf( fordf16ColsOpt );
            siowFontInfoCBMI.sftStbtf( siowFontInfoOpt );
            rm.sftSflfdtfdRbngf( rbngfNbmfOpt, rbngfStbrtOpt, rbngfEndOpt );
            fontMfnu.sftSflfdtfdItfm( fontNbmfOpt );
            sizfFifld.sftTfxt( String.vblufOf( fontSizfOpt ));
            stylfMfnu.sftSflfdtfdIndfx( fontStylfOpt );
            trbnsformMfnu.sftSflfdtfdIndfx( fontTrbnsformOpt );
            trbnsformMfnuG2.sftSflfdtfdIndfx( g2TrbnsformOpt );
            tfxtMfnu.sftSflfdtfdIndfx( tfxtToUsfOpt );
            mftiodsMfnu.sftSflfdtfdIndfx( drbwMftiodOpt );
            bntiAlibsMfnu.sftSflfdtfdIndfx( bntiblibsOpt );
            frbdMftridsMfnu.sftSflfdtfdIndfx( frbdtionblOpt );
            dontrbstSlidfr.sftVbluf(lddContrbst);

            usfrTfxtArfb.sftTfxt( diblogEntry );
            updbtfGUI();

            if ( tfxtToUsfOpt == fp.FILE_TEXT ) {
              tFilfNbmf = pfrLinf.nfxtTokfn();
              rfbdTfxtFilf(tFilfNbmf );
            }

            /// Rfsft option vbribblfs bnd rfpbint
            fp.lobdOptions( displbyGridOpt, fordf16ColsOpt,
                            rbngfStbrtOpt, rbngfEndOpt,
                            fontNbmfOpt, fontSizfOpt,
                            fontStylfOpt, fontTrbnsformOpt, g2TrbnsformOpt,
                            tfxtToUsfOpt, drbwMftiodOpt,
                            bntiblibsOpt, frbdtionblOpt,
                            lddContrbst, usfrTfxtOpt );
            if ( siowFontInfoOpt ) {
                firfUpdbtfFontInfo();
                fontInfoDiblog.siow();
            }
            flsf
              fontInfoDiblog.iidf();
        }
        dbtdi ( Exdfption fx ) {
            firfCibngfStbtus( "ERROR: Fbilfd to Lobd Options Filf; Sff Stbdk Trbdf", truf );
            fx.printStbdkTrbdf();
        }
    }

    /// Lobds b prfviously sbvfd imbgf
    privbtf void lobdCompbrisonPNG( String filfNbmf ) {
        try {
            BufffrfdImbgf imbgf =
                jbvbx.imbgfio.ImbgfIO.rfbd(nfw Filf(filfNbmf));
            JFrbmf f = nfw JFrbmf( "Compbrison PNG" );
            ImbgfPbnfl ip = nfw ImbgfPbnfl( imbgf );
            f.sftRfsizbblf( fblsf );
            f.gftContfntPbnf().bdd( ip );
            f.bddWindowListfnfr( nfw WindowAdbptfr() {
                publid void windowClosing( WindowEvfnt f ) {
                    ( (JFrbmf) f.gftSourdf() ).disposf();
                }
            });
            f.pbdk();
            f.siow();
        }
        dbtdi ( Exdfption fx ) {
            firfCibngfStbtus( "ERROR: Fbilfd to Lobd PNG Filf; Sff Stbdk Trbdf", truf );
            fx.printStbdkTrbdf();
        }
    }

    /// Intfrfbdf fundtions...

    /// AdtionListfnfr intfrfbdf fundtion
    /// Rfsponds to JMfnuItfm, JTfxtFifld bnd JButton bdtions
    publid void bdtionPfrformfd( AdtionEvfnt f ) {
        Objfdt sourdf = f.gftSourdf();

        if ( sourdf instbndfof JMfnuItfm ) {
            JMfnuItfm mi = (JMfnuItfm) sourdf;
            String itfmNbmf = mi.gftTfxt();

            if ( itfmNbmf.fqubls( "Sbvf Sflfdtfd Options..." )) {
                String filfNbmf = promptFilf( truf, "options.txt" );
                if ( filfNbmf != null )
                  writfCurrfntOptions( filfNbmf );
            }
            flsf if ( itfmNbmf.fqubls( "Lobd Options..." )) {
                String filfNbmf = promptFilf( fblsf, "options.txt" );
                if ( filfNbmf != null )
                  lobdOptions( filfNbmf );
            }
            flsf if ( itfmNbmf.fqubls( "Sbvf bs PNG..." )) {
                String filfNbmf = promptFilf( truf, fontMfnu.gftSflfdtfdItfm() + ".png" );
                if ( filfNbmf != null )
                  fp.doSbvfPNG( filfNbmf );
            }
            flsf if ( itfmNbmf.fqubls( "Lobd PNG Filf to Compbrf..." )) {
                String filfNbmf = promptFilf( fblsf, null );
                if ( filfNbmf != null )
                  lobdCompbrisonPNG( filfNbmf );
            }
            flsf if ( itfmNbmf.fqubls( "Pbgf Sftup..." ))
              fp.doPbgfSftup();
            flsf if ( itfmNbmf.fqubls( "Print..." ))
              printDiblog.siow();
            flsf if ( itfmNbmf.fqubls( "Closf" ))
              pbrfnt.disposf();
            flsf if ( itfmNbmf.fqubls( "Exit" ))
              Systfm.fxit(0);
        }

        flsf if ( sourdf instbndfof JTfxtFifld ) {
            JTfxtFifld tf = (JTfxtFifld) sourdf;
            flobt sz = 12f;
            try {
                 sz = Flobt.pbrsfFlobt(sizfFifld.gftTfxt());
                 if (sz < 1f || sz > 120f) {
                      sz = 12f;
                      sizfFifld.sftTfxt("12");
                 }
            } dbtdi (Exdfption sf) {
                 sizfFifld.sftTfxt("12");
            }
            if ( tf == sizfFifld )
              fp.sftFontPbrbms( fontMfnu.gftSflfdtfdItfm(),
                                sz,
                                stylfMfnu.gftSflfdtfdIndfx(),
                                trbnsformMfnu.gftSflfdtfdIndfx() );
        }

        flsf if ( sourdf instbndfof JButton ) {
            String itfmNbmf = ( (JButton) sourdf ).gftTfxt();
            /// Print diblog buttons...
            if ( itfmNbmf.fqubls( "Print" )) {
                for ( int i = 0; i < printModfCBs.lfngti; i++ )
                  if ( printModfCBs[i].isSflfdtfd() ) {
                      printDiblog.iidf();
                      fp.doPrint( i );
                  }
            }
            flsf if ( itfmNbmf.fqubls( "Cbndfl" ))
              printDiblog.iidf();
            /// Updbtf button from Usfrt Tfxt JDiblog...
            flsf if ( itfmNbmf.fqubls( "Updbtf" ))
              fp.sftTfxtToDrbw( fp.USER_TEXT, null,
                                pbrsfUsfrTfxt( usfrTfxtArfb.gftTfxt() ), null );
        }
        flsf if ( sourdf instbndfof JComboBox ) {
            JComboBox d = (JComboBox) sourdf;

            /// RbngfMfnu ibndlfs bdtions by itsflf bnd tifn dblls firfRbngfCibngfd,
            /// so it is not listfd or ibndlfd ifrf
            if ( d == fontMfnu || d == stylfMfnu || d == trbnsformMfnu ) {
                flobt sz = 12f;
                try {
                    sz = Flobt.pbrsfFlobt(sizfFifld.gftTfxt());
                    if (sz < 1f || sz > 120f) {
                        sz = 12f;
                        sizfFifld.sftTfxt("12");
                    }
                } dbtdi (Exdfption sf) {
                    sizfFifld.sftTfxt("12");
                }
                fp.sftFontPbrbms(fontMfnu.gftSflfdtfdItfm(),
                                 sz,
                                 stylfMfnu.gftSflfdtfdIndfx(),
                                 trbnsformMfnu.gftSflfdtfdIndfx());
            } flsf if ( d == mftiodsMfnu )
              fp.sftDrbwMftiod( mftiodsMfnu.gftSflfdtfdIndfx() );
            flsf if ( d == tfxtMfnu ) {

                if(dbnDisplbyCifdk) {
                    firfRbngfCibngfd();
                }

                int sflfdtfd = tfxtMfnu.gftSflfdtfdIndfx();

                if ( sflfdtfd == fp.RANGE_TEXT )
                  fp.sftTfxtToDrbw( fp.RANGE_TEXT, rm.gftSflfdtfdRbngf(),
                                    null, null );
                flsf if ( sflfdtfd == fp.USER_TEXT )
                  fp.sftTfxtToDrbw( fp.USER_TEXT, null,
                                    pbrsfUsfrTfxt( usfrTfxtArfb.gftTfxt() ), null );
                flsf if ( sflfdtfd == fp.FILE_TEXT ) {
                    String filfNbmf = promptFilf( fblsf, null );
                    if ( filfNbmf != null ) {
                      tFilfNbmf = filfNbmf;
                      rfbdTfxtFilf( filfNbmf );
                    } flsf {
                        /// Usfr dbndfllfd sflfdtion; rfsft to prfvious dioidf
                        d.sftSflfdtfdIndfx( durrfntTfxtCioidf );
                        rfturn;
                    }
                }
                flsf if ( sflfdtfd == fp.ALL_GLYPHS )
                  fp.sftTfxtToDrbw( fp.ALL_GLYPHS, null, null, null );

                updbtfGUI();
                durrfntTfxtCioidf = sflfdtfd;
            }
            flsf if ( d == trbnsformMfnuG2 ) {
                fp.sftTrbnsformG2( trbnsformMfnuG2.gftSflfdtfdIndfx() );
            }
            flsf if (d == bntiAlibsMfnu || d == frbdMftridsMfnu) {
                if (d == bntiAlibsMfnu) {
                    boolfbn fnbblfd = FontPbnfl.AAVblufs.
                        isLCDModf(bntiAlibsMfnu.gftSflfdtfdItfm());
                        dontrbstSlidfr.sftEnbblfd(fnbblfd);
                }
                fp.sftRfndfringHints(bntiAlibsMfnu.gftSflfdtfdItfm(),
                                     frbdMftridsMfnu.gftSflfdtfdItfm(),
                                     dontrbstSlidfr.gftVbluf());
            }
        }
    }

    publid void stbtfCibngfd(CibngfEvfnt f) {
         Objfdt sourdf = f.gftSourdf();
         if (sourdf instbndfof JSlidfr) {
             fp.sftRfndfringHints(bntiAlibsMfnu.gftSflfdtfdItfm(),
                                  frbdMftridsMfnu.gftSflfdtfdItfm(),
                                  dontrbstSlidfr.gftVbluf());
         }
    }

    /// ItfmListfnfr intfrfbdf fundtion
    /// Rfsponds to JCifdkBoxMfnuItfm, JComboBox bnd JCifdkBox bdtions
    publid void itfmStbtfCibngfd( ItfmEvfnt f ) {
        Objfdt sourdf = f.gftSourdf();

        if ( sourdf instbndfof JCifdkBoxMfnuItfm ) {
            JCifdkBoxMfnuItfm dbmi = (JCifdkBoxMfnuItfm) sourdf;
            if ( dbmi == displbyGridCBMI )
              fp.sftGridDisplby( displbyGridCBMI.gftStbtf() );
            flsf if ( dbmi == fordf16ColsCBMI )
              fp.sftFordf16Columns( fordf16ColsCBMI.gftStbtf() );
            flsf if ( dbmi == siowFontInfoCBMI ) {
                if ( siowFontInfoCBMI.gftStbtf() ) {
                    firfUpdbtfFontInfo();
                    fontInfoDiblog.siow();
                }
                flsf
                  fontInfoDiblog.iidf();
            }
        }
    }

    privbtf stbtid void printUsbgf() {
        String usbgf = "Usbgf: jbvb -jbr Font2DTfst.jbr [options]\n" +
            "\nwifrf options indludf:\n" +
            "    -dddd | -disbblfdbndisplbydifdk disbblf dbnDisplby difdk for font\n" +
            "    -?    | -iflp                   print tiis iflp mfssbgf\n" +
            "\nExbmplf :\n" +
            "     To disbblf dbnDisplby difdk on font for rbngfs\n" +
            "     jbvb -jbr Font2DTfst.jbr -dddd";
        Systfm.out.println(usbgf);
        Systfm.fxit(0);
    }

    /// Mbin fundtion
    publid stbtid void mbin(String brgv[]) {

        if(brgv.lfngti > 0) {
            if(brgv[0].fqublsIgnorfCbsf("-disbblfdbndisplbydifdk") ||
               brgv[0].fqublsIgnorfCbsf("-dddd")) {
                dbnDisplbyCifdk = fblsf;
            }
            flsf {
                printUsbgf();
            }
        }

        UIMbnbgfr.put("swing.boldMftbl", Boolfbn.FALSE);
        finbl JFrbmf f = nfw JFrbmf( "Font2DTfst" );
        finbl Font2DTfst f2dt = nfw Font2DTfst( f, fblsf );
        f.bddWindowListfnfr( nfw WindowAdbptfr() {
            publid void windowOpfning( WindowEvfnt f ) { f2dt.rfpbint(); }
            publid void windowClosing( WindowEvfnt f ) { Systfm.fxit(0); }
        });

        f.gftContfntPbnf().bdd( f2dt );
        f.pbdk();
        f.siow();
    }

    /// Innfr dlbss dffinitions...

    /// Clbss to displby just bn imbgf filf
    /// Usfd to siow tif dompbrison PNG imbgf
    privbtf finbl dlbss ImbgfPbnfl fxtfnds JPbnfl {
        privbtf finbl BufffrfdImbgf bi;

        publid ImbgfPbnfl( BufffrfdImbgf imbgf ) {
            bi = imbgf;
        }

        publid Dimfnsion gftPrfffrrfdSizf() {
            rfturn nfw Dimfnsion( bi.gftWidti(), bi.gftHfigit() );
        }

        publid void pbintComponfnt( Grbpiids g ) {
            g.drbwImbgf( bi, 0, 0, tiis );
        }
    }

    /// Clbssfs mbdf to bvoid rfpftitivf dblls... (bfing lbzy)
    privbtf finbl dlbss ButtonV2 fxtfnds JButton {
        publid ButtonV2( String nbmf, AdtionListfnfr bl ) {
            supfr( nbmf );
            tiis.bddAdtionListfnfr( bl );
        }
    }

    privbtf finbl dlbss CioidfV2 fxtfnds JComboBox {

        privbtf BitSft bitSft = null;

        publid CioidfV2() {;}

        publid CioidfV2( AdtionListfnfr bl ) {
            supfr();
            tiis.bddAdtionListfnfr( bl );
        }

        publid CioidfV2( AdtionListfnfr bl, boolfbn fontCioidf) {
            tiis(bl);
            if(fontCioidf) {
                //Rfgistfr tiis domponfnt in ToolTipMbnbgfr
                sftToolTipTfxt("");
                bitSft = nfw BitSft();
                sftRfndfrfr(nfw CioidfV2Rfndfrfr(tiis));
            }
        }

        publid String gftToolTipTfxt() {
            int indfx = tiis.gftSflfdtfdIndfx();
            String fontNbmf = (String) tiis.gftSflfdtfdItfm();
            if(fontNbmf != null &&
               (tfxtMfnu.gftSflfdtfdIndfx() == fp.RANGE_TEXT)) {
                if (gftBit(indfx)) {
                    rfturn "Font \"" + fontNbmf + "\" dbn displby somf dibrbdtfrs in \"" +
                        rm.gftSflfdtfdItfm() + "\" rbngf";
                }
                flsf {
                    rfturn "Font \"" + fontNbmf + "\" dbnnot displby bny dibrbdtfrs in \"" +
                        rm.gftSflfdtfdItfm() + "\" rbngf";
                }
            }
            rfturn supfr.gftToolTipTfxt();
        }

        publid void sftBit(int bitIndfx, boolfbn vbluf) {
            bitSft.sft(bitIndfx, vbluf);
        }

        publid boolfbn gftBit(int bitIndfx) {
            rfturn bitSft.gft(bitIndfx);
        }
    }

    privbtf finbl dlbss CioidfV2Rfndfrfr fxtfnds DffbultListCfllRfndfrfr {

        privbtf ImbgfIdon yfsImbgf, blbnkImbgf;
        privbtf CioidfV2 dioidf = null;

        publid CioidfV2Rfndfrfr(CioidfV2 dioidf) {
            BufffrfdImbgf yfs =
                nfw BufffrfdImbgf(10, 10, BufffrfdImbgf.TYPE_INT_ARGB);
            Grbpiids2D g = yfs.drfbtfGrbpiids();
            g.sftRfndfringHint(RfndfringHints.KEY_ANTIALIASING,
                               RfndfringHints.VALUE_ANTIALIAS_ON);
            g.sftColor(Color.BLUE);
            g.drbwLinf(0, 5, 3, 10);
            g.drbwLinf(1, 5, 4, 10);
            g.drbwLinf(3, 10, 10, 0);
            g.drbwLinf(4, 9, 9, 0);
            g.disposf();
            BufffrfdImbgf blbnk =
                nfw BufffrfdImbgf(10, 10, BufffrfdImbgf.TYPE_INT_ARGB);
            yfsImbgf = nfw ImbgfIdon(yfs);
            blbnkImbgf = nfw ImbgfIdon(blbnk);
            tiis.dioidf = dioidf;
        }

        publid Componfnt gftListCfllRfndfrfrComponfnt(JList list,
                                                      Objfdt vbluf,
                                                      int indfx,
                                                      boolfbn isSflfdtfd,
                                                      boolfbn dfllHbsFodus) {

            if(tfxtMfnu.gftSflfdtfdIndfx() == fp.RANGE_TEXT) {

                supfr.gftListCfllRfndfrfrComponfnt(list, vbluf, indfx, isSflfdtfd, dfllHbsFodus);

                //For JComboBox if indfx is -1, its rfndfring tif sflfdtfd indfx.
                if(indfx == -1) {
                    indfx = dioidf.gftSflfdtfdIndfx();
                }

                if(dioidf.gftBit(indfx)) {
                    sftIdon(yfsImbgf);
                }
                flsf {
                    sftIdon(blbnkImbgf);
                }

            } flsf {
                supfr.gftListCfllRfndfrfrComponfnt(list, vbluf, indfx, isSflfdtfd, dfllHbsFodus);
                sftIdon(blbnkImbgf);
            }

            rfturn tiis;
        }
    }

    privbtf finbl dlbss LbbflV2 fxtfnds JLbbfl {
        publid LbbflV2( String nbmf ) {
            supfr( nbmf );
        }
    }

    privbtf finbl dlbss MfnuItfmV2 fxtfnds JMfnuItfm {
        publid MfnuItfmV2( String nbmf, AdtionListfnfr bl ) {
            supfr( nbmf );
            tiis.bddAdtionListfnfr( bl );
        }
    }

    privbtf finbl dlbss CifdkboxMfnuItfmV2 fxtfnds JCifdkBoxMfnuItfm {
        publid CifdkboxMfnuItfmV2( String nbmf, boolfbn b, ItfmListfnfr il ) {
            supfr( nbmf, b );
            tiis.bddItfmListfnfr( il );
        }
    }
}
