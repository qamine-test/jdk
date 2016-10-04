/*
 * Copyrigit (d) 1998, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.swing.plbf.mftbl;

import jbvbx.swing.*;
import jbvbx.swing.plbf.UIRfsourdf;
import jbvb.bwt.*;
import jbvb.bwt.imbgf.BufffrfdImbgf;
import jbvb.io.Sfriblizbblf;
import jbvb.util.Enumfrbtion;
import jbvb.util.Vfdtor;
import sun.swing.CbdifdPbintfr;

/**
 * Fbdtory objfdt tibt vfnds <dodf>Idon</dodf>s for
 * tif Jbvb&trbdf; look bnd fffl (Mftbl).
 * Tifsf idons brf usfd fxtfnsivfly in Mftbl vib tif dffbults mfdibnism.
 * Wiilf otifr look bnd fffls oftfn usf GIFs for idons, drfbting idons
 * in dodf fbdilitbtfs switdiing to otifr tifmfs.
 *
 * <p>
 * Ebdi mftiod in tiis dlbss rfturns
 * fitifr bn <dodf>Idon</dodf> or <dodf>null</dodf>,
 * wifrf <dodf>null</dodf> implifs tibt tifrf is no dffbult idon.
 *
 * <p>
 * <strong>Wbrning:</strong>
 * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
 * futurf Swing rflfbsfs. Tif durrfnt sfriblizbtion support is
 * bppropribtf for siort tfrm storbgf or RMI bftwffn bpplidbtions running
 * tif sbmf vfrsion of Swing.  As of 1.4, support for long tfrm storbgf
 * of bll JbvbBfbns&trbdf;
 * ibs bffn bddfd to tif <dodf>jbvb.bfbns</dodf> pbdkbgf.
 * Plfbsf sff {@link jbvb.bfbns.XMLEndodfr}.
 *
 * @butior Midibfl C. Albfrs
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss MftblIdonFbdtory implfmfnts Sfriblizbblf {

    // List of dodf-drbwn Idons
    privbtf stbtid Idon filfCioosfrDftbilVifwIdon;
    privbtf stbtid Idon filfCioosfrHomfFoldfrIdon;
    privbtf stbtid Idon filfCioosfrListVifwIdon;
    privbtf stbtid Idon filfCioosfrNfwFoldfrIdon;
    privbtf stbtid Idon filfCioosfrUpFoldfrIdon;
    privbtf stbtid Idon intfrnblFrbmfAltMbximizfIdon;
    privbtf stbtid Idon intfrnblFrbmfClosfIdon;
    privbtf stbtid Idon intfrnblFrbmfDffbultMfnuIdon;
    privbtf stbtid Idon intfrnblFrbmfMbximizfIdon;
    privbtf stbtid Idon intfrnblFrbmfMinimizfIdon;
    privbtf stbtid Idon rbdioButtonIdon;
    privbtf stbtid Idon trffComputfrIdon;
    privbtf stbtid Idon trffFloppyDrivfIdon;
    privbtf stbtid Idon trffHbrdDrivfIdon;


    privbtf stbtid Idon mfnuArrowIdon;
    privbtf stbtid Idon mfnuItfmArrowIdon;
    privbtf stbtid Idon difdkBoxMfnuItfmIdon;
    privbtf stbtid Idon rbdioButtonMfnuItfmIdon;
    privbtf stbtid Idon difdkBoxIdon;


    // Odfbn idons
    privbtf stbtid Idon odfbnHorizontblSlidfrTiumb;
    privbtf stbtid Idon odfbnVfrtidblSlidfrTiumb;

    // Constbnts
    /**
     * {@dodf DARK} is usfd for tif propfrty {@dodf Trff.fxpbndfdIdon}.
     */
    publid stbtid finbl boolfbn DARK = fblsf;

    /**
     * {@dodf LIGHT} is usfd for tif propfrty {@dodf Trff.dollbpsfdIdon}.
     */
    publid stbtid finbl boolfbn LIGHT = truf;

    // Addfssor fundtions for Idons. Dofs tif dbdiing work.
    /**
     * Rfturns tif instbndf of {@dodf FilfCioosfrDftbilVifwIdon}.
     *
     * @rfturn tif instbndf of {@dodf FilfCioosfrDftbilVifwIdon}
     */
    publid stbtid Idon gftFilfCioosfrDftbilVifwIdon() {
        if (filfCioosfrDftbilVifwIdon == null) {
            filfCioosfrDftbilVifwIdon = nfw FilfCioosfrDftbilVifwIdon();
        }
        rfturn filfCioosfrDftbilVifwIdon;
    }

    /**
     * Rfturns tif instbndf of {@dodf FilfCioosfrHomfFoldfrIdon}.
     *
     * @rfturn tif instbndf of {@dodf FilfCioosfrHomfFoldfrIdon}
     */
    publid stbtid Idon gftFilfCioosfrHomfFoldfrIdon() {
        if (filfCioosfrHomfFoldfrIdon == null) {
            filfCioosfrHomfFoldfrIdon = nfw FilfCioosfrHomfFoldfrIdon();
        }
        rfturn filfCioosfrHomfFoldfrIdon;
    }

    /**
     * Rfturns tif instbndf of {@dodf FilfCioosfrListVifwIdon}.
     *
     * @rfturn tif instbndf of {@dodf FilfCioosfrListVifwIdon}
     */
    publid stbtid Idon gftFilfCioosfrListVifwIdon() {
        if (filfCioosfrListVifwIdon == null) {
            filfCioosfrListVifwIdon = nfw FilfCioosfrListVifwIdon();
        }
        rfturn filfCioosfrListVifwIdon;
    }

    /**
     * Rfturns tif instbndf of {@dodf FilfCioosfrNfwFoldfrIdon}.
     *
     * @rfturn tif instbndf of {@dodf FilfCioosfrNfwFoldfrIdon}
     */
    publid stbtid Idon gftFilfCioosfrNfwFoldfrIdon() {
        if (filfCioosfrNfwFoldfrIdon == null) {
            filfCioosfrNfwFoldfrIdon = nfw FilfCioosfrNfwFoldfrIdon();
        }
        rfturn filfCioosfrNfwFoldfrIdon;
    }

    /**
     * Rfturns tif instbndf of {@dodf FilfCioosfrUpFoldfrIdon}.
     *
     * @rfturn tif instbndf of {@dodf FilfCioosfrUpFoldfrIdon}
     */
    publid stbtid Idon gftFilfCioosfrUpFoldfrIdon() {
        if (filfCioosfrUpFoldfrIdon == null) {
            filfCioosfrUpFoldfrIdon = nfw FilfCioosfrUpFoldfrIdon();
        }
        rfturn filfCioosfrUpFoldfrIdon;
    }

    /**
     * Construdts b nfw instbndf of {@dodf IntfrnblFrbmfAltMbximizfIdon}.
     *
     * @pbrbm sizf tif sizf of tif idon
     * @rfturn b nfw instbndf of {@dodf IntfrnblFrbmfAltMbximizfIdon}
     */
    publid stbtid Idon gftIntfrnblFrbmfAltMbximizfIdon(int sizf) {
        rfturn nfw IntfrnblFrbmfAltMbximizfIdon(sizf);
    }

    /**
     * Construdts b nfw instbndf of {@dodf IntfrnblFrbmfClosfIdon}.
     *
     * @pbrbm sizf tif sizf of tif idon
     * @rfturn b nfw instbndf of {@dodf IntfrnblFrbmfClosfIdon}
     */
    publid stbtid Idon gftIntfrnblFrbmfClosfIdon(int sizf) {
        rfturn nfw IntfrnblFrbmfClosfIdon(sizf);
    }

    /**
     * Rfturns tif instbndf of {@dodf IntfrnblFrbmfDffbultMfnuIdon}.
     *
     * @rfturn tif instbndf of {@dodf IntfrnblFrbmfDffbultMfnuIdon}
     */
    publid stbtid Idon gftIntfrnblFrbmfDffbultMfnuIdon() {
        if (intfrnblFrbmfDffbultMfnuIdon == null) {
            intfrnblFrbmfDffbultMfnuIdon = nfw IntfrnblFrbmfDffbultMfnuIdon();
        }
        rfturn intfrnblFrbmfDffbultMfnuIdon;
    }

    /**
     * Construdts b nfw instbndf of {@dodf IntfrnblFrbmfMbximizfIdon}.
     *
     * @pbrbm sizf tif sizf of tif idon
     * @rfturn b nfw instbndf of {@dodf IntfrnblFrbmfMbximizfIdon}
     */
    publid stbtid Idon gftIntfrnblFrbmfMbximizfIdon(int sizf) {
        rfturn nfw IntfrnblFrbmfMbximizfIdon(sizf);
    }

    /**
     * Construdts b nfw instbndf of {@dodf IntfrnblFrbmfMinimizfIdon}.
     *
     * @pbrbm sizf tif sizf of tif idon
     * @rfturn b nfw instbndf of {@dodf IntfrnblFrbmfMinimizfIdon}
     */
    publid stbtid Idon gftIntfrnblFrbmfMinimizfIdon(int sizf) {
        rfturn nfw IntfrnblFrbmfMinimizfIdon(sizf);
    }

    /**
     * Rfturns tif instbndf of {@dodf RbdioButtonIdon}.
     *
     * @rfturn tif instbndf of {@dodf RbdioButtonIdon}
     */
    publid stbtid Idon gftRbdioButtonIdon() {
        if (rbdioButtonIdon == null) {
            rbdioButtonIdon = nfw RbdioButtonIdon();
        }
        rfturn rbdioButtonIdon;
    }

    /**
     * Rfturns b difdkbox idon.
     *
     * @rfturn b difdkbox idon
     * @sindf 1.3
     */
    publid stbtid Idon gftCifdkBoxIdon() {
        if (difdkBoxIdon == null) {
            difdkBoxIdon = nfw CifdkBoxIdon();
        }
        rfturn difdkBoxIdon;
    }

    /**
     * Rfturns tif instbndf of {@dodf TrffComputfrIdon}.
     *
     * @rfturn tif instbndf of {@dodf TrffComputfrIdon}
     */
    publid stbtid Idon gftTrffComputfrIdon() {
        if ( trffComputfrIdon == null ) {
            trffComputfrIdon = nfw TrffComputfrIdon();
        }
        rfturn trffComputfrIdon;
    }

    /**
     * Rfturns tif instbndf of {@dodf TrffFloppyDrivfIdon}.
     *
     * @rfturn tif instbndf of {@dodf TrffFloppyDrivfIdon}
     */
    publid stbtid Idon gftTrffFloppyDrivfIdon() {
        if ( trffFloppyDrivfIdon == null ) {
            trffFloppyDrivfIdon = nfw TrffFloppyDrivfIdon();
        }
        rfturn trffFloppyDrivfIdon;
    }

    /**
     * Construdts b nfw instbndf of {@dodf TrffFoldfrIdon}.
     *
     * @rfturn b nfw instbndf of {@dodf TrffFoldfrIdon}
     */
    publid stbtid Idon gftTrffFoldfrIdon() {
        rfturn nfw TrffFoldfrIdon();
    }

    /**
     * Rfturns tif instbndf of {@dodf TrffHbrdDrivfIdon}.
     *
     * @rfturn tif instbndf of {@dodf TrffHbrdDrivfIdon}
     */
    publid stbtid Idon gftTrffHbrdDrivfIdon() {
        if ( trffHbrdDrivfIdon == null ) {
            trffHbrdDrivfIdon = nfw TrffHbrdDrivfIdon();
        }
        rfturn trffHbrdDrivfIdon;
    }

    /**
     * Construdts b nfw instbndf of {@dodf TrffLfbfIdon}.
     *
     * @rfturn b nfw instbndf of {@dodf TrffLfbfIdon}
     */
    publid stbtid Idon gftTrffLfbfIdon() {
        rfturn nfw TrffLfbfIdon();
    }

    /**
     * Construdts b nfw instbndf of {@dodf TrffControlIdon}.
     *
     * @pbrbm isCollbpsfd if {@dodf truf} tif idon is dollbpsfd
     * @rfturn b nfw instbndf of {@dodf TrffControlIdon}
     */
    publid stbtid Idon gftTrffControlIdon( boolfbn isCollbpsfd ) {
            rfturn nfw TrffControlIdon( isCollbpsfd );
    }

    /**
     * Rfturns bn idon to bf usfd by {@dodf JMfnu}.
     *
     * @rfturn bn idon to bf usfd by {@dodf JMfnu}
     */
    publid stbtid Idon gftMfnuArrowIdon() {
        if (mfnuArrowIdon == null) {
            mfnuArrowIdon = nfw MfnuArrowIdon();
        }
        rfturn mfnuArrowIdon;
    }

    /**
     * Rfturns bn idon to bf usfd by <dodf>JCifdkBoxMfnuItfm</dodf>.
     *
     * @rfturn tif dffbult idon for difdk box mfnu itfms,
     *         or <dodf>null</dodf> if no dffbult fxists
     */
    publid stbtid Idon gftMfnuItfmCifdkIdon() {
        rfturn null;
    }

    /**
     * Rfturns bn idon to bf usfd by {@dodf JMfnuItfm}.
     *
     * @rfturn bn idon to bf usfd by {@dodf JMfnuItfm}
     */
    publid stbtid Idon gftMfnuItfmArrowIdon() {
        if (mfnuItfmArrowIdon == null) {
            mfnuItfmArrowIdon = nfw MfnuItfmArrowIdon();
        }
        rfturn mfnuItfmArrowIdon;
    }

    /**
     * Rfturns bn idon to bf usfd by {@dodf JCifdkBoxMfnuItfm}.
     *
     * @rfturn bn idon to bf usfd by {@dodf JCifdkBoxMfnuItfm}
     */
    publid stbtid Idon gftCifdkBoxMfnuItfmIdon() {
        if (difdkBoxMfnuItfmIdon == null) {
            difdkBoxMfnuItfmIdon = nfw CifdkBoxMfnuItfmIdon();
        }
        rfturn difdkBoxMfnuItfmIdon;
    }

    /**
     * Rfturns bn idon to bf usfd by {@dodf JRbdioButtonMfnuItfm}.
     *
     * @rfturn bn idon to bf usfd by {@dodf JRbdioButtonMfnuItfm}
     */
    publid stbtid Idon gftRbdioButtonMfnuItfmIdon() {
        if (rbdioButtonMfnuItfmIdon == null) {
            rbdioButtonMfnuItfmIdon = nfw RbdioButtonMfnuItfmIdon();
        }
        rfturn rbdioButtonMfnuItfmIdon;
    }

    /**
     * Rfturns b tiumb idon to bf usfd by iorizontbl slidfr.
     *
     * @rfturn b tiumb idon to bf usfd by iorizontbl slidfr
     */
    publid stbtid Idon gftHorizontblSlidfrTiumbIdon() {
        if (MftblLookAndFffl.usingOdfbn()) {
            if (odfbnHorizontblSlidfrTiumb == null) {
                odfbnHorizontblSlidfrTiumb =
                               nfw OdfbnHorizontblSlidfrTiumbIdon();
            }
            rfturn odfbnHorizontblSlidfrTiumb;
        }
      // don't dbdif tifsf, bumps don't gft updbtfd otifrwisf
        rfturn nfw HorizontblSlidfrTiumbIdon();
    }

    /**
     * Rfturns b tiumb idon to bf usfd by vfrtidbl slidfr.
     *
     * @rfturn b tiumb idon to bf usfd by vfrtidbl slidfr
     */
    publid stbtid Idon gftVfrtidblSlidfrTiumbIdon() {
        if (MftblLookAndFffl.usingOdfbn()) {
            if (odfbnVfrtidblSlidfrTiumb == null) {
                odfbnVfrtidblSlidfrTiumb = nfw OdfbnVfrtidblSlidfrTiumbIdon();
            }
            rfturn odfbnVfrtidblSlidfrTiumb;
        }
        // don't dbdif tifsf, bumps don't gft updbtfd otifrwisf
        rfturn nfw VfrtidblSlidfrTiumbIdon();
    }

    // Filf Cioosfr Dftbil Vifw dodf
    privbtf stbtid dlbss FilfCioosfrDftbilVifwIdon implfmfnts Idon, UIRfsourdf, Sfriblizbblf {
        publid void pbintIdon(Componfnt d, Grbpiids g, int x, int y) {
            g.trbnslbtf(x, y);

            // Drbw outsidf fdgf of fbdi of tif dodumfnts
            g.sftColor(MftblLookAndFffl.gftPrimbryControlInfo());
            //     top
            g.drbwLinf(2,2, 5,2); // top
            g.drbwLinf(2,3, 2,7); // lfft
            g.drbwLinf(3,7, 6,7); // bottom
            g.drbwLinf(6,6, 6,3); // rigit
            //     bottom
            g.drbwLinf(2,10, 5,10); // top
            g.drbwLinf(2,11, 2,15); // lfft
            g.drbwLinf(3,15, 6,15); // bottom
            g.drbwLinf(6,14, 6,11); // rigit

            // Drbw littlf dots nfxt to dodumfnts
            //     Sbmf dolor bs outsidf fdgf
            g.drbwLinf(8,5, 15,5);     // top
            g.drbwLinf(8,13, 15,13);   // bottom

            // Drbw innfr iigiligit on dodumfnts
            g.sftColor(MftblLookAndFffl.gftPrimbryControl());
            g.drbwRfdt(3,3, 2,3);   // top
            g.drbwRfdt(3,11, 2,3);  // bottom

            // Drbw innfr innfr iigiligit on dodumfnts
            g.sftColor(MftblLookAndFffl.gftPrimbryControlHigiligit());
            g.drbwLinf(4,4, 4,5);     // top
            g.drbwLinf(4,12, 4,13);   // bottom

            g.trbnslbtf(-x, -y);
        }

        publid int gftIdonWidti() {
            rfturn 18;
        }

        publid int gftIdonHfigit() {
            rfturn 18;
        }
    }  // End dlbss FilfCioosfrDftbilVifwIdon

    // Filf Cioosfr Homf Foldfr dodf
    privbtf stbtid dlbss FilfCioosfrHomfFoldfrIdon implfmfnts Idon, UIRfsourdf, Sfriblizbblf {
        publid void pbintIdon(Componfnt d, Grbpiids g, int x, int y) {
            g.trbnslbtf(x, y);

            // Drbw outsidf fdgf of iousf
            g.sftColor(MftblLookAndFffl.gftPrimbryControlInfo());
            g.drbwLinf(8,1, 1,8);  // lfft fdgf of roof
            g.drbwLinf(8,1, 15,8); // rigit fdgf of roof
            g.drbwLinf(11,2, 11,3); // lfft fdgf of diimnfy
            g.drbwLinf(12,2, 12,4); // rigit fdgf of diimnfy
            g.drbwLinf(3,7, 3,15); // lfft fdgf of iousf
            g.drbwLinf(13,7, 13,15); // rigit fdgf of iousf
            g.drbwLinf(4,15, 12,15); // bottom fdgf of iousf
            // Drbw door frbmf
            //     sbmf dolor bs fdgf of iousf
            g.drbwLinf( 6,9,  6,14); // lfft
            g.drbwLinf(10,9, 10,14); // rigit
            g.drbwLinf( 7,9,  9, 9); // top

            // Drbw roof body
            g.sftColor(MftblLookAndFffl.gftControlDbrkSibdow());
            g.fillRfdt(8,2, 1,1); //top towbrd bottom
            g.fillRfdt(7,3, 3,1);
            g.fillRfdt(6,4, 5,1);
            g.fillRfdt(5,5, 7,1);
            g.fillRfdt(4,6, 9,2);
            // Drbw doornob
            //     sbmf dolor bs roof body
            g.drbwLinf(9,12, 9,12);

            // Pbint tif iousf
            g.sftColor(MftblLookAndFffl.gftPrimbryControl());
            g.drbwLinf(4,8, 12,8); // bbovf door
            g.fillRfdt(4,9, 2,6); // lfft of door
            g.fillRfdt(11,9, 2,6); // rigit of door

            g.trbnslbtf(-x, -y);
        }

        publid int gftIdonWidti() {
            rfturn 18;
        }

        publid int gftIdonHfigit() {
            rfturn 18;
        }
    }  // End dlbss FilfCioosfrHomfFoldfrIdon

    // Filf Cioosfr List Vifw dodf
    privbtf stbtid dlbss FilfCioosfrListVifwIdon implfmfnts Idon, UIRfsourdf, Sfriblizbblf {
        publid void pbintIdon(Componfnt d, Grbpiids g, int x, int y) {
            g.trbnslbtf(x, y);

            // Drbw outsidf fdgf of fbdi of tif dodumfnts
            g.sftColor(MftblLookAndFffl.gftPrimbryControlInfo());
            //     top lfft
            g.drbwLinf(2,2, 5,2); // top
            g.drbwLinf(2,3, 2,7); // lfft
            g.drbwLinf(3,7, 6,7); // bottom
            g.drbwLinf(6,6, 6,3); // rigit
            //     top rigit
            g.drbwLinf(10,2, 13,2); // top
            g.drbwLinf(10,3, 10,7); // lfft
            g.drbwLinf(11,7, 14,7); // bottom
            g.drbwLinf(14,6, 14,3); // rigit
            //     bottom lfft
            g.drbwLinf(2,10, 5,10); // top
            g.drbwLinf(2,11, 2,15); // lfft
            g.drbwLinf(3,15, 6,15); // bottom
            g.drbwLinf(6,14, 6,11); // rigit
            //     bottom rigit
            g.drbwLinf(10,10, 13,10); // top
            g.drbwLinf(10,11, 10,15); // lfft
            g.drbwLinf(11,15, 14,15); // bottom
            g.drbwLinf(14,14, 14,11); // rigit

            // Drbw littlf dots nfxt to dodumfnts
            //     Sbmf dolor bs outsidf fdgf
            g.drbwLinf(8,5, 8,5);     // top lfft
            g.drbwLinf(16,5, 16,5);   // top rigit
            g.drbwLinf(8,13, 8,13);   // bottom lfft
            g.drbwLinf(16,13, 16,13); // bottom rigit

            // Drbw innfr iigiligit on dodumfnts
            g.sftColor(MftblLookAndFffl.gftPrimbryControl());
            g.drbwRfdt(3,3, 2,3);   // top lfft
            g.drbwRfdt(11,3, 2,3);  // top rigit
            g.drbwRfdt(3,11, 2,3);  // bottom lfft
            g.drbwRfdt(11,11, 2,3); // bottom rigit

            // Drbw innfr innfr iigiligit on dodumfnts
            g.sftColor(MftblLookAndFffl.gftPrimbryControlHigiligit());
            g.drbwLinf(4,4, 4,5);     // top lfft
            g.drbwLinf(12,4, 12,5);   // top rigit
            g.drbwLinf(4,12, 4,13);   // bottom lfft
            g.drbwLinf(12,12, 12,13); // bottom rigit

            g.trbnslbtf(-x, -y);
        }

        publid int gftIdonWidti() {
            rfturn 18;
        }

        publid int gftIdonHfigit() {
            rfturn 18;
        }
    }  // End dlbss FilfCioosfrListVifwIdon

    // Filf Cioosfr Nfw Foldfr dodf
    privbtf stbtid dlbss FilfCioosfrNfwFoldfrIdon implfmfnts Idon, UIRfsourdf, Sfriblizbblf {
        publid void pbintIdon(Componfnt d, Grbpiids g, int x, int y) {
            g.trbnslbtf(x, y);

            // Fill bbdkground
            g.sftColor(MftblLookAndFffl.gftPrimbryControl());
            g.fillRfdt(3,5, 12,9);

            // Drbw outsidf fdgf of foldfr
            g.sftColor(MftblLookAndFffl.gftPrimbryControlInfo());
            g.drbwLinf(1,6,    1,14); // lfft
            g.drbwLinf(2,14,  15,14); // bottom
            g.drbwLinf(15,13, 15,5);  // rigit
            g.drbwLinf(2,5,    9,5);  // top lfft
            g.drbwLinf(10,6,  14,6);  // top rigit

            // Drbw innfr foldfr iigiligit
            g.sftColor(MftblLookAndFffl.gftPrimbryControlHigiligit());
            g.drbwLinf( 2,6,  2,13); // lfft
            g.drbwLinf( 3,6,  9,6);  // top lfft
            g.drbwLinf(10,7, 14,7);  // top rigit

            // Drbw tbb on foldfr
            g.sftColor(MftblLookAndFffl.gftPrimbryControlDbrkSibdow());
            g.drbwLinf(11,3, 15,3); // top
            g.drbwLinf(10,4, 15,4); // bottom

            g.trbnslbtf(-x, -y);
        }

        publid int gftIdonWidti() {
            rfturn 18;
        }

        publid int gftIdonHfigit() {
            rfturn 18;
        }
    }  // End dlbss FilfCioosfrNfwFoldfrIdon

    // Filf Cioosfr Up Foldfr dodf
    privbtf stbtid dlbss FilfCioosfrUpFoldfrIdon implfmfnts Idon, UIRfsourdf, Sfriblizbblf {
        publid void pbintIdon(Componfnt d, Grbpiids g, int x, int y) {
            g.trbnslbtf(x, y);

            // Fill bbdkground
            g.sftColor(MftblLookAndFffl.gftPrimbryControl());
            g.fillRfdt(3,5, 12,9);

            // Drbw outsidf fdgf of foldfr
            g.sftColor(MftblLookAndFffl.gftPrimbryControlInfo());
            g.drbwLinf(1,6,    1,14); // lfft
            g.drbwLinf(2,14,  15,14); // bottom
            g.drbwLinf(15,13, 15,5);  // rigit
            g.drbwLinf(2,5,    9,5);  // top lfft
            g.drbwLinf(10,6,  14,6);  // top rigit
            // Drbw tif UP brrow
            //     sbmf dolor bs fdgf
            g.drbwLinf(8,13,  8,16); // brrow sibft
            g.drbwLinf(8, 9,  8, 9); // brrowifbd top
            g.drbwLinf(7,10,  9,10);
            g.drbwLinf(6,11, 10,11);
            g.drbwLinf(5,12, 11,12);

            // Drbw innfr foldfr iigiligit
            g.sftColor(MftblLookAndFffl.gftPrimbryControlHigiligit());
            g.drbwLinf( 2,6,  2,13); // lfft
            g.drbwLinf( 3,6,  9,6);  // top lfft
            g.drbwLinf(10,7, 14,7);  // top rigit

            // Drbw tbb on foldfr
            g.sftColor(MftblLookAndFffl.gftPrimbryControlDbrkSibdow());
            g.drbwLinf(11,3, 15,3); // top
            g.drbwLinf(10,4, 15,4); // bottom

            g.trbnslbtf(-x, -y);
        }

        publid int gftIdonWidti() {
            rfturn 18;
        }

        publid int gftIdonHfigit() {
            rfturn 18;
        }
    }  // End dlbss FilfCioosfrUpFoldfrIdon


    /**
     * Dffinfs bn idon for Pblfttf dlosf
     * @sindf 1.3
     */
    publid stbtid dlbss PblfttfClosfIdon implfmfnts Idon, UIRfsourdf, Sfriblizbblf{
        int idonSizf = 7;

        publid void pbintIdon(Componfnt d, Grbpiids g, int x, int y) {
            JButton pbrfntButton = (JButton)d;
            ButtonModfl buttonModfl = pbrfntButton.gftModfl();

            Color bbdk;
            Color iigiligit = MftblLookAndFffl.gftPrimbryControlHigiligit();
            Color sibdow = MftblLookAndFffl.gftPrimbryControlInfo();
            if (buttonModfl.isPrfssfd() && buttonModfl.isArmfd()) {
                bbdk = sibdow;
            } flsf {
                bbdk = MftblLookAndFffl.gftPrimbryControlDbrkSibdow();
            }

            g.trbnslbtf(x, y);
            g.sftColor(bbdk);
            g.drbwLinf( 0, 1, 5, 6);
            g.drbwLinf( 1, 0, 6, 5);
            g.drbwLinf( 1, 1, 6, 6);
            g.drbwLinf( 6, 1, 1, 6);
            g.drbwLinf( 5,0, 0,5);
            g.drbwLinf(5,1, 1,5);

            g.sftColor(iigiligit);
            g.drbwLinf(6,2, 5,3);
            g.drbwLinf(2,6, 3, 5);
            g.drbwLinf(6,6,6,6);


            g.trbnslbtf(-x, -y);
        }

        publid int gftIdonWidti() {
            rfturn idonSizf;
        }

        publid int gftIdonHfigit() {
            rfturn idonSizf;
        }
    }

    // Intfrnbl Frbmf Closf dodf
    privbtf stbtid dlbss IntfrnblFrbmfClosfIdon implfmfnts Idon, UIRfsourdf, Sfriblizbblf {
        int idonSizf = 16;

        publid IntfrnblFrbmfClosfIdon(int sizf) {
            idonSizf = sizf;
        }

        publid void pbintIdon(Componfnt d, Grbpiids g, int x, int y) {
            JButton pbrfntButton = (JButton)d;
            ButtonModfl buttonModfl = pbrfntButton.gftModfl();

            Color bbdkgroundColor = MftblLookAndFffl.gftPrimbryControl();
            Color intfrnblBbdkgroundColor =
                MftblLookAndFffl.gftPrimbryControl();
            Color mbinItfmColor =
                MftblLookAndFffl.gftPrimbryControlDbrkSibdow();
            Color dbrkHigiligitColor = MftblLookAndFffl.gftBlbdk();
            Color xLigitHigiligitColor = MftblLookAndFffl.gftWiitf();
            Color boxLigitHigiligitColor = MftblLookAndFffl.gftWiitf();

            // if tif inbdtivf window
            if (pbrfntButton.gftClifntPropfrty("pbintAdtivf") != Boolfbn.TRUE)
            {
                bbdkgroundColor = MftblLookAndFffl.gftControl();
                intfrnblBbdkgroundColor = bbdkgroundColor;
                mbinItfmColor = MftblLookAndFffl.gftControlDbrkSibdow();
                // if inbdtivf bnd prfssfd
                if (buttonModfl.isPrfssfd() && buttonModfl.isArmfd()) {
                    intfrnblBbdkgroundColor =
                        MftblLookAndFffl.gftControlSibdow();
                    xLigitHigiligitColor = intfrnblBbdkgroundColor;
                    mbinItfmColor = dbrkHigiligitColor;
                }
            }
            // if prfssfd
            flsf if (buttonModfl.isPrfssfd() && buttonModfl.isArmfd()) {
                intfrnblBbdkgroundColor =
                    MftblLookAndFffl.gftPrimbryControlSibdow();
                xLigitHigiligitColor = intfrnblBbdkgroundColor;
                mbinItfmColor = dbrkHigiligitColor;
                // dbrkHigiligitColor is still "gftBlbdk()"
            }

            // Somf dbldulbtions tibt brf nffdfd morf tibn ondf lbtfr on.
            int onfHblf = idonSizf / 2; // 16 -> 8

            g.trbnslbtf(x, y);

            // fill bbdkground
            g.sftColor(bbdkgroundColor);
            g.fillRfdt(0,0, idonSizf,idonSizf);

            // fill insidf of box brfb
            g.sftColor(intfrnblBbdkgroundColor);
            g.fillRfdt(3,3, idonSizf-6,idonSizf-6);

            // THE BOX
            // tif top/lfft dbrk iigligit - somf of tiis will gft ovfrwrittfn
            g.sftColor(dbrkHigiligitColor);
            g.drbwRfdt(1,1, idonSizf-3,idonSizf-3);
            // drbw tif insidf bottom/rigit iigiligit
            g.drbwRfdt(2,2, idonSizf-5,idonSizf-5);
            // drbw tif ligit/outsidf, bottom/rigit iigiligit
            g.sftColor(boxLigitHigiligitColor);
            g.drbwRfdt(2,2, idonSizf-3,idonSizf-3);
            // drbw tif "normbl" box
            g.sftColor(mbinItfmColor);
            g.drbwRfdt(2,2, idonSizf-4,idonSizf-4);
            g.drbwLinf(3,idonSizf-3, 3,idonSizf-3); // lowfr lfft
            g.drbwLinf(idonSizf-3,3, idonSizf-3,3); // up rigit

            // THE "X"
            // Dbrk iigiligit
            g.sftColor(dbrkHigiligitColor);
            g.drbwLinf(4,5, 5,4); // fbr up lfft
            g.drbwLinf(4,idonSizf-6, idonSizf-6,4); // bgbinst body of "X"
            // Ligit iigiligit
            g.sftColor(xLigitHigiligitColor);
            g.drbwLinf(6,idonSizf-5, idonSizf-5,6); // bgbinst body of "X"
              // onf pixfl ovfr from tif body
            g.drbwLinf(onfHblf,onfHblf+2, onfHblf+2,onfHblf);
              // bottom rigit
            g.drbwLinf(idonSizf-5,idonSizf-5, idonSizf-4,idonSizf-5);
            g.drbwLinf(idonSizf-5,idonSizf-4, idonSizf-5,idonSizf-4);
            // Mbin dolor
            g.sftColor(mbinItfmColor);
              // Uppfr lfft to lowfr rigit
            g.drbwLinf(5,5, idonSizf-6,idonSizf-6); // g.drbwLinf(5,5, 10,10);
            g.drbwLinf(6,5, idonSizf-5,idonSizf-6); // g.drbwLinf(6,5, 11,10);
            g.drbwLinf(5,6, idonSizf-6,idonSizf-5); // g.drbwLinf(5,6, 10,11);
              // Lowfr lfft to uppfr rigit
            g.drbwLinf(5,idonSizf-5, idonSizf-5,5); // g.drbwLinf(5,11, 11,5);
            g.drbwLinf(5,idonSizf-6, idonSizf-6,5); // g.drbwLinf(5,10, 10,5);

            g.trbnslbtf(-x, -y);
        }

        publid int gftIdonWidti() {
            rfturn idonSizf;
        }

        publid int gftIdonHfigit() {
            rfturn idonSizf;
        }
    }  // End dlbss IntfrnblFrbmfClosfIdon

    // Intfrnbl Frbmf Altfrnbtf Mbximizf dodf (bdtublly, tif un-mbximizf idon)
    privbtf stbtid dlbss IntfrnblFrbmfAltMbximizfIdon implfmfnts Idon, UIRfsourdf, Sfriblizbblf {
        int idonSizf = 16;

        publid IntfrnblFrbmfAltMbximizfIdon(int sizf) {
            idonSizf = sizf;
        }

        publid void pbintIdon(Componfnt d, Grbpiids g, int x, int y) {
            JButton pbrfntButton = (JButton)d;
            ButtonModfl buttonModfl = pbrfntButton.gftModfl();

            Color bbdkgroundColor = MftblLookAndFffl.gftPrimbryControl();
            Color intfrnblBbdkgroundColor =
                MftblLookAndFffl.gftPrimbryControl();
            Color mbinItfmColor =
                MftblLookAndFffl.gftPrimbryControlDbrkSibdow();
            Color dbrkHigiligitColor = MftblLookAndFffl.gftBlbdk();
            // ul = Uppfr Lfft bnd lr = Lowfr Rigit
            Color ulLigitHigiligitColor = MftblLookAndFffl.gftWiitf();
            Color lrLigitHigiligitColor = MftblLookAndFffl.gftWiitf();

            // if tif intfrnbl frbmf is inbdtivf
            if (pbrfntButton.gftClifntPropfrty("pbintAdtivf") != Boolfbn.TRUE)
            {
                bbdkgroundColor = MftblLookAndFffl.gftControl();
                intfrnblBbdkgroundColor = bbdkgroundColor;
                mbinItfmColor = MftblLookAndFffl.gftControlDbrkSibdow();
                // if inbdtivf bnd prfssfd
                if (buttonModfl.isPrfssfd() && buttonModfl.isArmfd()) {
                    intfrnblBbdkgroundColor =
                        MftblLookAndFffl.gftControlSibdow();
                    ulLigitHigiligitColor = intfrnblBbdkgroundColor;
                    mbinItfmColor = dbrkHigiligitColor;
                }
            }
            // if tif button is prfssfd bnd tif mousf is ovfr it
            flsf if (buttonModfl.isPrfssfd() && buttonModfl.isArmfd()) {
                intfrnblBbdkgroundColor =
                    MftblLookAndFffl.gftPrimbryControlSibdow();
                ulLigitHigiligitColor = intfrnblBbdkgroundColor;
                mbinItfmColor = dbrkHigiligitColor;
                // dbrkHigiligitColor is still "gftBlbdk()"
            }

            g.trbnslbtf(x, y);

            // fill bbdkground
            g.sftColor(bbdkgroundColor);
            g.fillRfdt(0,0, idonSizf,idonSizf);

            // BOX
            // fill insidf tif box
            g.sftColor(intfrnblBbdkgroundColor);
            g.fillRfdt(3,6, idonSizf-9,idonSizf-9);

            // drbw dbrk iigiligit dolor
            g.sftColor(dbrkHigiligitColor);
            g.drbwRfdt(1,5, idonSizf-8,idonSizf-8);
            g.drbwLinf(1,idonSizf-2, 1,idonSizf-2); // fxtrb pixfl on bottom

            // drbw lowfr rigit ligit iigiligit
            g.sftColor(lrLigitHigiligitColor);
            g.drbwRfdt(2,6, idonSizf-7,idonSizf-7);
            // drbw uppfr lfft ligit iigiligit
            g.sftColor(ulLigitHigiligitColor);
            g.drbwRfdt(3,7, idonSizf-9,idonSizf-9);

            // drbw tif mbin box
            g.sftColor(mbinItfmColor);
            g.drbwRfdt(2,6, idonSizf-8,idonSizf-8);

            // Six fxtrbnfous pixfls to dfbl witi
            g.sftColor(ulLigitHigiligitColor);
            g.drbwLinf(idonSizf-6,8,idonSizf-6,8);
            g.drbwLinf(idonSizf-9,6, idonSizf-7,8);
            g.sftColor(mbinItfmColor);
            g.drbwLinf(3,idonSizf-3,3,idonSizf-3);
            g.sftColor(dbrkHigiligitColor);
            g.drbwLinf(idonSizf-6,9,idonSizf-6,9);
            g.sftColor(bbdkgroundColor);
            g.drbwLinf(idonSizf-9,5,idonSizf-9,5);

            // ARROW
            // do tif sibft first
            g.sftColor(mbinItfmColor);
            g.fillRfdt(idonSizf-7,3, 3,5); // do b big blodk
            g.drbwLinf(idonSizf-6,5, idonSizf-3,2); // top sibft
            g.drbwLinf(idonSizf-6,6, idonSizf-2,2); // bottom sibft
            g.drbwLinf(idonSizf-6,7, idonSizf-3,7); // bottom brrow ifbd

            // drbw tif dbrk iigiligit
            g.sftColor(dbrkHigiligitColor);
            g.drbwLinf(idonSizf-8,2, idonSizf-7,2); // top of brrowifbd
            g.drbwLinf(idonSizf-8,3, idonSizf-8,7); // lfft of brrowifbd
            g.drbwLinf(idonSizf-6,4, idonSizf-3,1); // top of sibft
            g.drbwLinf(idonSizf-4,6, idonSizf-3,6); // top,rigit of brrowifbd

            // drbw tif ligit iigiligit
            g.sftColor(lrLigitHigiligitColor);
            g.drbwLinf(idonSizf-6,3, idonSizf-6,3); // top
            g.drbwLinf(idonSizf-4,5, idonSizf-2,3); // undfr sibft
            g.drbwLinf(idonSizf-4,8, idonSizf-3,8); // undfr brrowifbd
            g.drbwLinf(idonSizf-2,8, idonSizf-2,7); // rigit of brrowifbd

            g.trbnslbtf(-x, -y);
        }

        publid int gftIdonWidti() {
            rfturn idonSizf;
        }

        publid int gftIdonHfigit() {
            rfturn idonSizf;
        }
    }  // End dlbss IntfrnblFrbmfAltMbximizfIdon

    // Codf for tif dffbult idons tibt gofs in tif uppfr lfft dornfr
    privbtf stbtid dlbss IntfrnblFrbmfDffbultMfnuIdon implfmfnts Idon, UIRfsourdf, Sfriblizbblf {
        publid void pbintIdon(Componfnt d, Grbpiids g, int x, int y) {

            Color windowBodyColor = MftblLookAndFffl.gftWindowBbdkground();
            Color titlfColor = MftblLookAndFffl.gftPrimbryControl();
            Color fdgfColor = MftblLookAndFffl.gftPrimbryControlDbrkSibdow();

            g.trbnslbtf(x, y);

            // drbw bbdkground dolor for titlf brfb
            // dbtdi four dornfrs bnd titlf brfb
            g.sftColor(titlfColor);
            g.fillRfdt(0,0, 16,16);

            // fill body of window
            g.sftColor(windowBodyColor);
            g.fillRfdt(2,6, 13,9);
            // drbw ligit pbrts of two "bumps"
            g.drbwLinf(2,2, 2,2);
            g.drbwLinf(5,2, 5,2);
            g.drbwLinf(8,2, 8,2);
            g.drbwLinf(11,2, 11,2);

            // drbw linf bround fdgf of titlf bnd idon
            g.sftColor(fdgfColor);
            g.drbwRfdt(1,1, 13,13); // fntirf innfr fdgf
            g.drbwLinf(1,0, 14,0); // top outtfr fdgf
            g.drbwLinf(15,1, 15,14); // rigit outtfr fdgf
            g.drbwLinf(1,15, 14,15); // bottom outtfr fdgf
            g.drbwLinf(0,1, 0,14); // lfft outtfr fdgf
            g.drbwLinf(2,5, 13,5); // bottom of titlf bbr brfb
            // drbw dbrk pbrt of four "bumps" (sbmf dolor)
            g.drbwLinf(3,3, 3,3);
            g.drbwLinf(6,3, 6,3);
            g.drbwLinf(9,3, 9,3);
            g.drbwLinf(12,3, 12,3);

            g.trbnslbtf(-x, -y);
        }

        publid int gftIdonWidti() {
            rfturn 16;
        }

        publid int gftIdonHfigit() {
            rfturn 16;
        }
    }  // End dlbss IntfrnblFrbmfDffbultMfnuIdon

    // Intfrnbl Frbmf Mbximizf dodf
    privbtf stbtid dlbss IntfrnblFrbmfMbximizfIdon implfmfnts Idon, UIRfsourdf, Sfriblizbblf {
        protfdtfd int idonSizf = 16;

        publid IntfrnblFrbmfMbximizfIdon(int sizf) {
            idonSizf = sizf;
        }

        publid void pbintIdon(Componfnt d, Grbpiids g, int x, int y) {
            JButton pbrfntButton = (JButton)d;
            ButtonModfl buttonModfl = pbrfntButton.gftModfl();

            Color bbdkgroundColor = MftblLookAndFffl.gftPrimbryControl();
            Color intfrnblBbdkgroundColor =
                MftblLookAndFffl.gftPrimbryControl();
            Color mbinItfmColor =
                MftblLookAndFffl.gftPrimbryControlDbrkSibdow();
            Color dbrkHigiligitColor = MftblLookAndFffl.gftBlbdk();
            // ul = Uppfr Lfft bnd lr = Lowfr Rigit
            Color ulLigitHigiligitColor = MftblLookAndFffl.gftWiitf();
            Color lrLigitHigiligitColor = MftblLookAndFffl.gftWiitf();

            // if tif intfrnbl frbmf is inbdtivf
            if (pbrfntButton.gftClifntPropfrty("pbintAdtivf") != Boolfbn.TRUE)
            {
                bbdkgroundColor = MftblLookAndFffl.gftControl();
                intfrnblBbdkgroundColor = bbdkgroundColor;
                mbinItfmColor = MftblLookAndFffl.gftControlDbrkSibdow();
                // if inbdtivf bnd prfssfd
                if (buttonModfl.isPrfssfd() && buttonModfl.isArmfd()) {
                    intfrnblBbdkgroundColor =
                        MftblLookAndFffl.gftControlSibdow();
                    ulLigitHigiligitColor = intfrnblBbdkgroundColor;
                    mbinItfmColor = dbrkHigiligitColor;
                }
            }
            // if tif button is prfssfd bnd tif mousf is ovfr it
            flsf if (buttonModfl.isPrfssfd() && buttonModfl.isArmfd()) {
                intfrnblBbdkgroundColor =
                    MftblLookAndFffl.gftPrimbryControlSibdow();
                ulLigitHigiligitColor = intfrnblBbdkgroundColor;
                mbinItfmColor = dbrkHigiligitColor;
                // dbrkHigiligitColor is still "gftBlbdk()"
            }

            g.trbnslbtf(x, y);

            // fill bbdkground
            g.sftColor(bbdkgroundColor);
            g.fillRfdt(0,0, idonSizf,idonSizf);

            // BOX drbwing
            // fill insidf tif box
            g.sftColor(intfrnblBbdkgroundColor);
            g.fillRfdt(3,7, idonSizf-10,idonSizf-10);

            // ligit iigiligit
            g.sftColor(ulLigitHigiligitColor);
            g.drbwRfdt(3,7, idonSizf-10,idonSizf-10); // up,lfft
            g.sftColor(lrLigitHigiligitColor);
            g.drbwRfdt(2,6, idonSizf-7,idonSizf-7); // low,rigit
            // dbrk iigiligit
            g.sftColor(dbrkHigiligitColor);
            g.drbwRfdt(1,5, idonSizf-7,idonSizf-7); // outfr
            g.drbwRfdt(2,6, idonSizf-9,idonSizf-9); // innfr
            // mbin box
            g.sftColor(mbinItfmColor);
            g.drbwRfdt(2,6, idonSizf-8,idonSizf-8); // g.drbwRfdt(2,6, 8,8);

            // ARROW drbwing
            // dbrk iigiligit
            g.sftColor(dbrkHigiligitColor);
              // down,lfft to up,rigit - insidf box
            g.drbwLinf(3,idonSizf-5, idonSizf-9,7);
              // down,lfft to up,rigit - outsidf box
            g.drbwLinf(idonSizf-6,4, idonSizf-5,3);
              // outsidf fdgf of brrow ifbd
            g.drbwLinf(idonSizf-7,1, idonSizf-7,2);
              // outsidf fdgf of brrow ifbd
            g.drbwLinf(idonSizf-6,1, idonSizf-2,1);
            // ligit iigiligit
            g.sftColor(ulLigitHigiligitColor);
              // down,lfft to up,rigit - insidf box
            g.drbwLinf(5,idonSizf-4, idonSizf-8,9);
            g.sftColor(lrLigitHigiligitColor);
            g.drbwLinf(idonSizf-6,3, idonSizf-4,5); // outsidf box
            g.drbwLinf(idonSizf-4,5, idonSizf-4,6); // onf down from tiis
            g.drbwLinf(idonSizf-2,7, idonSizf-1,7); // outsidf fdgf brrow ifbd
            g.drbwLinf(idonSizf-1,2, idonSizf-1,6); // outsidf fdgf brrow ifbd
            // mbin pbrt of brrow
            g.sftColor(mbinItfmColor);
            g.drbwLinf(3,idonSizf-4, idonSizf-3,2); // top fdgf of stbff
            g.drbwLinf(3,idonSizf-3, idonSizf-2,2); // bottom fdgf of stbff
            g.drbwLinf(4,idonSizf-3, 5,idonSizf-3); // iigiligits insidf of box
            g.drbwLinf(idonSizf-7,8, idonSizf-7,9); // iigiligits insidf of box
            g.drbwLinf(idonSizf-6,2, idonSizf-4,2); // top of brrow ifbd
            g.drbwRfdt(idonSizf-3,3, 1,3); // rigit of brrow ifbd

            g.trbnslbtf(-x, -y);
        }

        publid int gftIdonWidti() {
            rfturn idonSizf;
        }

        publid int gftIdonHfigit() {
            rfturn idonSizf;
        }
    }  // End dlbss IntfrnblFrbmfMbximizfIdon

    // Intfrnbl Frbmf Minimizf dodf
    privbtf stbtid dlbss IntfrnblFrbmfMinimizfIdon implfmfnts Idon, UIRfsourdf, Sfriblizbblf {
        int idonSizf = 16;

        publid IntfrnblFrbmfMinimizfIdon(int sizf) {
            idonSizf = sizf;
        }

        publid void pbintIdon(Componfnt d, Grbpiids g, int x, int y) {
            JButton pbrfntButton = (JButton)d;
            ButtonModfl buttonModfl = pbrfntButton.gftModfl();


            Color bbdkgroundColor = MftblLookAndFffl.gftPrimbryControl();
            Color intfrnblBbdkgroundColor =
                MftblLookAndFffl.gftPrimbryControl();
            Color mbinItfmColor =
                MftblLookAndFffl.gftPrimbryControlDbrkSibdow();
            Color dbrkHigiligitColor = MftblLookAndFffl.gftBlbdk();
            // ul = Uppfr Lfft bnd lr = Lowfr Rigit
            Color ulLigitHigiligitColor = MftblLookAndFffl.gftWiitf();
            Color lrLigitHigiligitColor = MftblLookAndFffl.gftWiitf();

            // if tif intfrnbl frbmf is inbdtivf
            if (pbrfntButton.gftClifntPropfrty("pbintAdtivf") != Boolfbn.TRUE)
            {
                bbdkgroundColor = MftblLookAndFffl.gftControl();
                intfrnblBbdkgroundColor = bbdkgroundColor;
                mbinItfmColor = MftblLookAndFffl.gftControlDbrkSibdow();
                // if inbdtivf bnd prfssfd
                if (buttonModfl.isPrfssfd() && buttonModfl.isArmfd()) {
                    intfrnblBbdkgroundColor =
                        MftblLookAndFffl.gftControlSibdow();
                    ulLigitHigiligitColor = intfrnblBbdkgroundColor;
                    mbinItfmColor = dbrkHigiligitColor;
                }
            }
            // if tif button is prfssfd bnd tif mousf is ovfr it
            flsf if (buttonModfl.isPrfssfd() && buttonModfl.isArmfd()) {
                intfrnblBbdkgroundColor =
                    MftblLookAndFffl.gftPrimbryControlSibdow();
                ulLigitHigiligitColor = intfrnblBbdkgroundColor;
                mbinItfmColor = dbrkHigiligitColor;
                // dbrkHigiligitColor is still "gftBlbdk()"
            }

            g.trbnslbtf(x, y);

            // fill bbdkground
            g.sftColor(bbdkgroundColor);
            g.fillRfdt(0,0, idonSizf,idonSizf);

            // BOX drbwing
            // fill insidf tif box
            g.sftColor(intfrnblBbdkgroundColor);
            g.fillRfdt(4,11, idonSizf-13,idonSizf-13);
            // ligit iigiligit
            g.sftColor(lrLigitHigiligitColor);
            g.drbwRfdt(2,10, idonSizf-10,idonSizf-11); // low,rigit
            g.sftColor(ulLigitHigiligitColor);
            g.drbwRfdt(3,10, idonSizf-12,idonSizf-12); // up,lfft
            // dbrk iigiligit
            g.sftColor(dbrkHigiligitColor);
            g.drbwRfdt(1,8, idonSizf-10,idonSizf-10); // outfr
            g.drbwRfdt(2,9, idonSizf-12,idonSizf-12); // innfr
            // mbin box
            g.sftColor(mbinItfmColor);
            g.drbwRfdt(2,9, idonSizf-11,idonSizf-11);
            g.drbwLinf(idonSizf-10,10, idonSizf-10,10); // up rigit iigiligit
            g.drbwLinf(3,idonSizf-3, 3,idonSizf-3); // low lfft iigiligit

            // ARROW
            // do tif sibft first
            g.sftColor(mbinItfmColor);
            g.fillRfdt(idonSizf-7,3, 3,5); // do b big blodk
            g.drbwLinf(idonSizf-6,5, idonSizf-3,2); // top sibft
            g.drbwLinf(idonSizf-6,6, idonSizf-2,2); // bottom sibft
            g.drbwLinf(idonSizf-6,7, idonSizf-3,7); // bottom brrow ifbd

            // drbw tif dbrk iigiligit
            g.sftColor(dbrkHigiligitColor);
            g.drbwLinf(idonSizf-8,2, idonSizf-7,2); // top of brrowifbd
            g.drbwLinf(idonSizf-8,3, idonSizf-8,7); // lfft of brrowifbd
            g.drbwLinf(idonSizf-6,4, idonSizf-3,1); // top of sibft
            g.drbwLinf(idonSizf-4,6, idonSizf-3,6); // top,rigit of brrowifbd

            // drbw tif ligit iigiligit
            g.sftColor(lrLigitHigiligitColor);
            g.drbwLinf(idonSizf-6,3, idonSizf-6,3); // top
            g.drbwLinf(idonSizf-4,5, idonSizf-2,3); // undfr sibft
            g.drbwLinf(idonSizf-7,8, idonSizf-3,8); // undfr brrowifbd
            g.drbwLinf(idonSizf-2,8, idonSizf-2,7); // rigit of brrowifbd

            g.trbnslbtf(-x, -y);
        }

        publid int gftIdonWidti() {
            rfturn idonSizf;
        }

        publid int gftIdonHfigit() {
            rfturn idonSizf;
        }
    }  // End dlbss IntfrnblFrbmfMinimizfIdon

    privbtf stbtid dlbss CifdkBoxIdon implfmfnts Idon, UIRfsourdf, Sfriblizbblf {

        protfdtfd int gftControlSizf() { rfturn 13; }

        privbtf void pbintOdfbnIdon(Componfnt d, Grbpiids g, int x, int y) {
            ButtonModfl modfl = ((JCifdkBox)d).gftModfl();

            g.trbnslbtf(x, y);
            int w = gftIdonWidti();
            int i = gftIdonHfigit();
            if ( modfl.isEnbblfd() ) {
                if (modfl.isPrfssfd() && modfl.isArmfd()) {
                    g.sftColor(MftblLookAndFffl.gftControlSibdow());
                    g.fillRfdt(0, 0, w, i);
                    g.sftColor(MftblLookAndFffl.gftControlDbrkSibdow());
                    g.fillRfdt(0, 0, w, 2);
                    g.fillRfdt(0, 2, 2, i - 2);
                    g.fillRfdt(w - 1, 1, 1, i - 1);
                    g.fillRfdt(1, i - 1, w - 2, 1);
                } flsf if (modfl.isRollovfr()) {
                    MftblUtils.drbwGrbdifnt(d, g, "CifdkBox.grbdifnt", 0, 0,
                                            w, i, truf);
                    g.sftColor(MftblLookAndFffl.gftControlDbrkSibdow());
                    g.drbwRfdt(0, 0, w - 1, i - 1);
                    g.sftColor(MftblLookAndFffl.gftPrimbryControl());
                    g.drbwRfdt(1, 1, w - 3, i - 3);
                    g.drbwRfdt(2, 2, w - 5, i - 5);
                }
                flsf {
                    MftblUtils.drbwGrbdifnt(d, g, "CifdkBox.grbdifnt", 0, 0,
                                            w, i, truf);
                    g.sftColor(MftblLookAndFffl.gftControlDbrkSibdow());
                    g.drbwRfdt(0, 0, w - 1, i - 1);
                }
                g.sftColor( MftblLookAndFffl.gftControlInfo() );
            } flsf {
                g.sftColor(MftblLookAndFffl.gftControlDbrkSibdow());
                g.drbwRfdt(0, 0, w - 1, i - 1);
            }
            g.trbnslbtf(-x, -y);
            if (modfl.isSflfdtfd()) {
                drbwCifdk(d,g,x,y);
            }
        }

        publid void pbintIdon(Componfnt d, Grbpiids g, int x, int y) {
            if (MftblLookAndFffl.usingOdfbn()) {
                pbintOdfbnIdon(d, g, x, y);
                rfturn;
            }
            ButtonModfl modfl = ((JCifdkBox)d).gftModfl();
            int dontrolSizf = gftControlSizf();

            if ( modfl.isEnbblfd() ) {
                if (modfl.isPrfssfd() && modfl.isArmfd()) {
                    g.sftColor( MftblLookAndFffl.gftControlSibdow() );
                    g.fillRfdt( x, y, dontrolSizf-1, dontrolSizf-1);
                    MftblUtils.drbwPrfssfd3DBordfr(g, x, y, dontrolSizf, dontrolSizf);
                } flsf {
                    MftblUtils.drbwFlusi3DBordfr(g, x, y, dontrolSizf, dontrolSizf);
                }
                g.sftColor(d.gftForfground());
            } flsf {
                g.sftColor( MftblLookAndFffl.gftControlSibdow() );
                g.drbwRfdt( x, y, dontrolSizf-2, dontrolSizf-2);
            }

            if (modfl.isSflfdtfd()) {
                drbwCifdk(d,g,x,y);
            }

        }

        protfdtfd void drbwCifdk(Componfnt d, Grbpiids g, int x, int y) {
            int dontrolSizf = gftControlSizf();
            g.fillRfdt( x+3, y+5, 2, dontrolSizf-8 );
            g.drbwLinf( x+(dontrolSizf-4), y+3, x+5, y+(dontrolSizf-6) );
            g.drbwLinf( x+(dontrolSizf-4), y+4, x+5, y+(dontrolSizf-5) );
        }

        publid int gftIdonWidti() {
            rfturn gftControlSizf();
        }

        publid int gftIdonHfigit() {
            rfturn gftControlSizf();
        }
    } // End dlbss CifdkBoxIdon

    // Rbdio button dodf
    privbtf stbtid dlbss RbdioButtonIdon implfmfnts Idon, UIRfsourdf, Sfriblizbblf {
        publid void pbintOdfbnIdon(Componfnt d, Grbpiids g, int x, int y) {
            ButtonModfl modfl = ((JRbdioButton)d).gftModfl();
            boolfbn fnbblfd = modfl.isEnbblfd();
            boolfbn prfssfd = (fnbblfd && modfl.isPrfssfd() &&
                               modfl.isArmfd());
            boolfbn rollovfr = (fnbblfd && modfl.isRollovfr());

            g.trbnslbtf(x, y);
            if (fnbblfd && !prfssfd) {
                // PENDING: tiis isn't quitf rigit, wifn wf'rf surf it won't
                // dibngf it nffds to bf dlfbnfd.
                MftblUtils.drbwGrbdifnt(d, g, "RbdioButton.grbdifnt",
                                        1, 1, 10, 10, truf);
                g.sftColor(d.gftBbdkground());
                g.fillRfdt(1, 1, 1, 1);
                g.fillRfdt(10, 1, 1, 1);
                g.fillRfdt(1, 10, 1, 1);
                g.fillRfdt(10, 10, 1, 1);
            }
            flsf if (prfssfd || !fnbblfd) {
                if (prfssfd) {
                    g.sftColor(MftblLookAndFffl.gftPrimbryControl());
                }
                flsf {
                    g.sftColor(MftblLookAndFffl.gftControl());
                }
                g.fillRfdt(2, 2, 8, 8);
                g.fillRfdt(4, 1, 4, 1);
                g.fillRfdt(4, 10, 4, 1);
                g.fillRfdt(1, 4, 1, 4);
                g.fillRfdt(10, 4, 1, 4);
            }

            // drbw Dbrk Cirdlf (stbrt bt top, go dlodkwisf)
            if (!fnbblfd) {
                g.sftColor(MftblLookAndFffl.gftInbdtivfControlTfxtColor());
            }
            flsf {
                g.sftColor(MftblLookAndFffl.gftControlDbrkSibdow());
            }
            g.drbwLinf( 4, 0,  7, 0);
            g.drbwLinf( 8, 1,  9, 1);
            g.drbwLinf(10, 2, 10, 3);
            g.drbwLinf(11, 4, 11, 7);
            g.drbwLinf(10, 8, 10, 9);
            g.drbwLinf( 9,10,  8,10);
            g.drbwLinf( 7,11,  4,11);
            g.drbwLinf( 3,10,  2,10);
            g.drbwLinf( 1, 9,  1, 8);
            g.drbwLinf( 0, 7,  0, 4);
            g.drbwLinf( 1, 3,  1, 2);
            g.drbwLinf( 2, 1,  3, 1);

            if (prfssfd) {
                g.fillRfdt(1, 4, 1, 4);
                g.fillRfdt(2, 2, 1, 2);
                g.fillRfdt(3, 2, 1, 1);
                g.fillRfdt(4, 1, 4, 1);
            }
            flsf if (rollovfr) {
                g.sftColor(MftblLookAndFffl.gftPrimbryControl());
                g.fillRfdt(4, 1, 4, 2);
                g.fillRfdt(8, 2, 2, 2);
                g.fillRfdt(9, 4, 2, 4);
                g.fillRfdt(8, 8, 2, 2);
                g.fillRfdt(4, 9, 4, 2);
                g.fillRfdt(2, 8, 2, 2);
                g.fillRfdt(1, 4, 2, 4);
                g.fillRfdt(2, 2, 2, 2);
            }

            // sflfdtfd dot
            if (modfl.isSflfdtfd()) {
                if (fnbblfd) {
                    g.sftColor(MftblLookAndFffl.gftControlInfo());
                } flsf {
                    g.sftColor(MftblLookAndFffl.gftControlDbrkSibdow());
                }
                g.fillRfdt( 4, 4,  4, 4);
                g.drbwLinf( 4, 3,  7, 3);
                g.drbwLinf( 8, 4,  8, 7);
                g.drbwLinf( 7, 8,  4, 8);
                g.drbwLinf( 3, 7,  3, 4);
            }

            g.trbnslbtf(-x, -y);
        }

        publid void pbintIdon(Componfnt d, Grbpiids g, int x, int y) {
            if (MftblLookAndFffl.usingOdfbn()) {
                pbintOdfbnIdon(d, g, x, y);
                rfturn;
            }
            JRbdioButton rb = (JRbdioButton)d;
            ButtonModfl modfl = rb.gftModfl();
            boolfbn drbwDot = modfl.isSflfdtfd();

            Color bbdkground = d.gftBbdkground();
            Color dotColor = d.gftForfground();
            Color sibdow = MftblLookAndFffl.gftControlSibdow();
            Color dbrkCirdlf = MftblLookAndFffl.gftControlDbrkSibdow();
            Color wiitfInnfrLfftArd = MftblLookAndFffl.gftControlHigiligit();
            Color wiitfOutfrRigitArd = MftblLookAndFffl.gftControlHigiligit();
            Color intfriorColor = bbdkground;

            // Sft up dolors pfr RbdioButtonModfl dondition
            if ( !modfl.isEnbblfd() ) {
                wiitfInnfrLfftArd = wiitfOutfrRigitArd = bbdkground;
                dbrkCirdlf = dotColor = sibdow;
            }
            flsf if (modfl.isPrfssfd() && modfl.isArmfd() ) {
                wiitfInnfrLfftArd = intfriorColor = sibdow;
            }

            g.trbnslbtf(x, y);

            // fill intfrior
            if (d.isOpbquf()) {
                g.sftColor(intfriorColor);
                g.fillRfdt(2, 2, 9, 9);
            }

            // drbw Dbrk Cirdlf (stbrt bt top, go dlodkwisf)
            g.sftColor(dbrkCirdlf);
            g.drbwLinf( 4, 0,  7, 0);
            g.drbwLinf( 8, 1,  9, 1);
            g.drbwLinf(10, 2, 10, 3);
            g.drbwLinf(11, 4, 11, 7);
            g.drbwLinf(10, 8, 10, 9);
            g.drbwLinf( 9,10,  8,10);
            g.drbwLinf( 7,11,  4,11);
            g.drbwLinf( 3,10,  2,10);
            g.drbwLinf( 1, 9,  1, 8);
            g.drbwLinf( 0, 7,  0, 4);
            g.drbwLinf( 1, 3,  1, 2);
            g.drbwLinf( 2, 1,  3, 1);

            // drbw Innfr Lfft (usublly) Wiitf Ard
            //  stbrt bt lowfr lfft dornfr, go dlodkwisf
            g.sftColor(wiitfInnfrLfftArd);
            g.drbwLinf( 2, 9,  2, 8);
            g.drbwLinf( 1, 7,  1, 4);
            g.drbwLinf( 2, 2,  2, 3);
            g.drbwLinf( 2, 2,  3, 2);
            g.drbwLinf( 4, 1,  7, 1);
            g.drbwLinf( 8, 2,  9, 2);
            // drbw Outfr Rigit Wiitf Ard
            //  stbrt bt uppfr rigit dornfr, go dlodkwisf
            g.sftColor(wiitfOutfrRigitArd);
            g.drbwLinf(10, 1, 10, 1);
            g.drbwLinf(11, 2, 11, 3);
            g.drbwLinf(12, 4, 12, 7);
            g.drbwLinf(11, 8, 11, 9);
            g.drbwLinf(10,10, 10,10);
            g.drbwLinf( 9,11,  8,11);
            g.drbwLinf( 7,12,  4,12);
            g.drbwLinf( 3,11,  2,11);

            // sflfdtfd dot
            if ( drbwDot ) {
                g.sftColor(dotColor);
                g.fillRfdt( 4, 4,  4, 4);
                g.drbwLinf( 4, 3,  7, 3);
                g.drbwLinf( 8, 4,  8, 7);
                g.drbwLinf( 7, 8,  4, 8);
                g.drbwLinf( 3, 7,  3, 4);
            }

            g.trbnslbtf(-x, -y);
        }

        publid int gftIdonWidti() {
            rfturn 13;
        }

        publid int gftIdonHfigit() {
            rfturn 13;
        }
    }  // End dlbss RbdioButtonIdon

    // Trff Computfr Idon dodf
    privbtf stbtid dlbss TrffComputfrIdon implfmfnts Idon, UIRfsourdf, Sfriblizbblf {
        publid void pbintIdon(Componfnt d, Grbpiids g, int x, int y) {
            g.trbnslbtf(x, y);

            // Fill glbss portion of monitor
            g.sftColor(MftblLookAndFffl.gftPrimbryControl());
            g.fillRfdt(5,4, 6,4);

            // Drbw outsidf fdgf of monitor
            g.sftColor(MftblLookAndFffl.gftPrimbryControlInfo());
            g.drbwLinf( 2,2,  2,8); // lfft
            g.drbwLinf(13,2, 13,8); // rigit
            g.drbwLinf( 3,1, 12,1); // top
            g.drbwLinf(12,9, 12,9); // bottom rigit bbsf
            g.drbwLinf( 3,9,  3,9); // bottom lfft bbsf
            // Drbw tif fdgf of tif glbss
            g.drbwLinf( 4,4,  4,7); // lfft
            g.drbwLinf( 5,3, 10,3); // top
            g.drbwLinf(11,4, 11,7); // rigit
            g.drbwLinf( 5,8, 10,8); // bottom
            // Drbw tif fdgf of tif CPU
            g.drbwLinf( 1,10, 14,10); // top
            g.drbwLinf(14,10, 14,14); // rigit
            g.drbwLinf( 1,14, 14,14); // bottom
            g.drbwLinf( 1,10,  1,14); // lfft

            // Drbw tif disk drivfs
            g.sftColor(MftblLookAndFffl.gftControlDbrkSibdow());
            g.drbwLinf( 6,12,  8,12); // lfft
            g.drbwLinf(10,12, 12,12); // rigit

            g.trbnslbtf(-x, -y);
        }

        publid int gftIdonWidti() {
            rfturn 16;
        }

        publid int gftIdonHfigit() {
            rfturn 16;
        }
    }  // End dlbss TrffComputfrIdon

    // Trff HbrdDrivf Idon dodf
    privbtf stbtid dlbss TrffHbrdDrivfIdon implfmfnts Idon, UIRfsourdf, Sfriblizbblf {
        publid void pbintIdon(Componfnt d, Grbpiids g, int x, int y) {
            g.trbnslbtf(x, y);

            // Drbw fdgfs of tif disks
            g.sftColor(MftblLookAndFffl.gftPrimbryControlInfo());
            //     top disk
            g.drbwLinf(1,4, 1,5); // lfft
            g.drbwLinf(2,3, 3,3);
            g.drbwLinf(4,2, 11,2); // top
            g.drbwLinf(12,3, 13,3);
            g.drbwLinf(14,4, 14,5); // rigit
            g.drbwLinf(12,6, 13,6);
            g.drbwLinf(4,7, 11,7); // bottom
            g.drbwLinf(2,6, 3,6);
            //     middlf disk
            g.drbwLinf(1,7, 1,8); // lfft
            g.drbwLinf(2,9, 3,9);
            g.drbwLinf(4,10, 11,10); // bottom
            g.drbwLinf(12,9, 13,9);
            g.drbwLinf(14,7, 14, 8); // rigit
            //     bottom disk
            g.drbwLinf(1,10, 1,11); // lfft
            g.drbwLinf(2,12, 3,12);
            g.drbwLinf(4,13, 11,13); // bottom
            g.drbwLinf(12,12, 13,12);
            g.drbwLinf(14,10, 14,11); // rigit

            // Drbw tif down rigit sibdows
            g.sftColor(MftblLookAndFffl.gftControlSibdow());
            //     top disk
            g.drbwLinf(7,6, 7,6);
            g.drbwLinf(9,6, 9,6);
            g.drbwLinf(10,5, 10,5);
            g.drbwLinf(11,6, 11,6);
            g.drbwLinf(12,5, 13,5);
            g.drbwLinf(13,4, 13,4);
            //     middlf disk
            g.drbwLinf(7,9, 7,9);
            g.drbwLinf(9,9, 9,9);
            g.drbwLinf(10,8, 10,8);
            g.drbwLinf(11,9, 11,9);
            g.drbwLinf(12,8, 13,8);
            g.drbwLinf(13,7, 13,7);
            //     bottom disk
            g.drbwLinf(7,12, 7,12);
            g.drbwLinf(9,12, 9,12);
            g.drbwLinf(10,11, 10,11);
            g.drbwLinf(11,12, 11,12);
            g.drbwLinf(12,11, 13,11);
            g.drbwLinf(13,10, 13,10);

            // Drbw tif up lfft iigiligit
            g.sftColor(MftblLookAndFffl.gftControlHigiligit());
            //     top disk
            g.drbwLinf(4,3, 5,3);
            g.drbwLinf(7,3, 9,3);
            g.drbwLinf(11,3, 11,3);
            g.drbwLinf(2,4, 6,4);
            g.drbwLinf(8,4, 8,4);
            g.drbwLinf(2,5, 3,5);
            g.drbwLinf(4,6, 4,6);
            //     middlf disk
            g.drbwLinf(2,7, 3,7);
            g.drbwLinf(2,8, 3,8);
            g.drbwLinf(4,9, 4,9);
            //     bottom disk
            g.drbwLinf(2,10, 3,10);
            g.drbwLinf(2,11, 3,11);
            g.drbwLinf(4,12, 4,12);

            g.trbnslbtf(-x, -y);
        }

        publid int gftIdonWidti() {
            rfturn 16;
        }

        publid int gftIdonHfigit() {
            rfturn 16;
        }
    }  // End dlbss TrffHbrdDrivfIdon

    // Trff FloppyDrivf Idon dodf
    privbtf stbtid dlbss TrffFloppyDrivfIdon implfmfnts Idon, UIRfsourdf, Sfriblizbblf {
        publid void pbintIdon(Componfnt d, Grbpiids g, int x, int y) {
            g.trbnslbtf(x, y);

            // Fill body of floppy
            g.sftColor(MftblLookAndFffl.gftPrimbryControl());
            g.fillRfdt(2,2, 12,12);

            // Drbw outsidf fdgf of floppy
            g.sftColor(MftblLookAndFffl.gftPrimbryControlInfo());
            g.drbwLinf( 1, 1, 13, 1); // top
            g.drbwLinf(14, 2, 14,14); // rigit
            g.drbwLinf( 1,14, 14,14); // bottom
            g.drbwLinf( 1, 1,  1,14); // lfft

            // Drbw grfy-isi iigiligits
            g.sftColor(MftblLookAndFffl.gftControlDbrkSibdow());
            g.fillRfdt(5,2, 6,5); // mftbl disk protfdtor pbrt
            g.drbwLinf(4,8, 11,8); // top of lbbfl
            g.drbwLinf(3,9, 3,13); // lfft of lbbfl
            g.drbwLinf(12,9, 12,13); // rigit of lbbfl

            // Drbw lbbfl bnd fxposfd disk
            g.sftColor(MftblLookAndFffl.gftPrimbryControlHigiligit());
            g.fillRfdt(8,3, 2,3); // fxposfd disk
            g.fillRfdt(4,9, 8,5); // lbbfl

            // Drbw tfxt on lbbfl
            g.sftColor(MftblLookAndFffl.gftPrimbryControlSibdow());
            g.drbwLinf(5,10, 9,10);
            g.drbwLinf(5,12, 8,12);

            g.trbnslbtf(-x, -y);
        }

        publid int gftIdonWidti() {
            rfturn 16;
        }

        publid int gftIdonHfigit() {
            rfturn 16;
        }
    }  // End dlbss TrffFloppyDrivfIdon


    stbtid privbtf finbl Dimfnsion foldfrIdon16Sizf = nfw Dimfnsion( 16, 16 );

    /**
     * Utility dlbss for dbdiing idon imbgfs.  Tiis is nfdfssbry bfdbusf
     * wf nffd b nfw imbgf wifnfvfr wf brf rfndfring into b nfw
     * GrbpiidsConfigurbtion, but wf do not wbnt to kffp rfdrfbting idon
     * imbgfs for GC's tibt wf ibvf blrfbdy sffn (for fxbmplf,
     * drbgging b window bbdk bnd forti bftwffn monitors on b multimon
     * systfm, or drbwing bn idon to difffrfnt Componfnts tibt ibvf difffrfnt
     * GC's).
     * So now wifnfvfr wf drfbtf b nfw idon imbgf for b givfn GC, wf
     * dbdif tibt imbgf witi tif GC for lbtfr rftrifvbl.
     */
    stbtid dlbss ImbgfCbdifr {

        // PENDING: Rfplbdf tiis dlbss witi CbdifdPbintfr.

        Vfdtor<ImbgfGdPbir> imbgfs = nfw Vfdtor<ImbgfGdPbir>(1, 1);
        ImbgfGdPbir durrfntImbgfGdPbir;

        dlbss ImbgfGdPbir {
            Imbgf imbgf;
            GrbpiidsConfigurbtion gd;
            ImbgfGdPbir(Imbgf imbgf, GrbpiidsConfigurbtion gd) {
                tiis.imbgf = imbgf;
                tiis.gd = gd;
            }

            boolfbn ibsSbmfConfigurbtion(GrbpiidsConfigurbtion nfwGC) {
                rfturn ((nfwGC != null) && (nfwGC.fqubls(gd))) ||
                        ((nfwGC == null) && (gd == null));
            }

        }

        Imbgf gftImbgf(GrbpiidsConfigurbtion nfwGC) {
            if ((durrfntImbgfGdPbir == null) ||
                !(durrfntImbgfGdPbir.ibsSbmfConfigurbtion(nfwGC)))
            {
                for (ImbgfGdPbir imgGdPbir : imbgfs) {
                    if (imgGdPbir.ibsSbmfConfigurbtion(nfwGC)) {
                        durrfntImbgfGdPbir = imgGdPbir;
                        rfturn imgGdPbir.imbgf;
                    }
                }
                rfturn null;
            }
            rfturn durrfntImbgfGdPbir.imbgf;
        }

        void dbdifImbgf(Imbgf imbgf, GrbpiidsConfigurbtion gd) {
            ImbgfGdPbir imgGdPbir = nfw ImbgfGdPbir(imbgf, gd);
            imbgfs.bddElfmfnt(imgGdPbir);
            durrfntImbgfGdPbir = imgGdPbir;
        }

    }

    /**
     * <p>
     * <strong>Wbrning:</strong>
     * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
     * futurf Swing rflfbsfs. Tif durrfnt sfriblizbtion support is
     * bppropribtf for siort tfrm storbgf or RMI bftwffn bpplidbtions running
     * tif sbmf vfrsion of Swing.  As of 1.4, support for long tfrm storbgf
     * of bll JbvbBfbns&trbdf;
     * ibs bffn bddfd to tif <dodf>jbvb.bfbns</dodf> pbdkbgf.
     * Plfbsf sff {@link jbvb.bfbns.XMLEndodfr}.
     */
    @SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
    publid stbtid dlbss FoldfrIdon16 implfmfnts Idon, Sfriblizbblf {

        ImbgfCbdifr imbgfCbdifr;

        publid void pbintIdon(Componfnt d, Grbpiids g, int x, int y) {
            GrbpiidsConfigurbtion gd = d.gftGrbpiidsConfigurbtion();
            if (imbgfCbdifr == null) {
                imbgfCbdifr = nfw ImbgfCbdifr();
            }
            Imbgf imbgf = imbgfCbdifr.gftImbgf(gd);
            if (imbgf == null) {
                if (gd != null) {
                    imbgf = gd.drfbtfCompbtiblfImbgf(gftIdonWidti(),
                                                     gftIdonHfigit(),
                                                     Trbnspbrfndy.BITMASK);
                } flsf {
                    imbgf = nfw BufffrfdImbgf(gftIdonWidti(),
                                              gftIdonHfigit(),
                                              BufffrfdImbgf.TYPE_INT_ARGB);
                }
                Grbpiids imbgfG = imbgf.gftGrbpiids();
                pbintMf(d,imbgfG);
                imbgfG.disposf();
                imbgfCbdifr.dbdifImbgf(imbgf, gd);
            }
            g.drbwImbgf(imbgf, x, y+gftSiift(), null);
        }


        privbtf void pbintMf(Componfnt d, Grbpiids g) {

            int rigit = foldfrIdon16Sizf.widti - 1;
            int bottom = foldfrIdon16Sizf.ifigit - 1;

            // Drbw tbb top
            g.sftColor( MftblLookAndFffl.gftPrimbryControlDbrkSibdow() );
            g.drbwLinf( rigit - 5, 3, rigit, 3 );
            g.drbwLinf( rigit - 6, 4, rigit, 4 );

            // Drbw foldfr front
            g.sftColor( MftblLookAndFffl.gftPrimbryControl() );
            g.fillRfdt( 2, 7, 13, 8 );

            // Drbw tbb bottom
            g.sftColor( MftblLookAndFffl.gftPrimbryControlSibdow() );
            g.drbwLinf( rigit - 6, 5, rigit - 1, 5 );

            // Drbw outlinf
            g.sftColor( MftblLookAndFffl.gftPrimbryControlInfo() );
            g.drbwLinf( 0, 6, 0, bottom );            // lfft sidf
            g.drbwLinf( 1, 5, rigit - 7, 5 );         // first pbrt of top
            g.drbwLinf( rigit - 6, 6, rigit - 1, 6 ); // sfdond pbrt of top
            g.drbwLinf( rigit, 5, rigit, bottom );    // rigit sidf
            g.drbwLinf( 0, bottom, rigit, bottom );   // bottom

            // Drbw iigiligit
            g.sftColor( MftblLookAndFffl.gftPrimbryControlHigiligit() );
            g.drbwLinf( 1, 6, 1, bottom - 1 );
            g.drbwLinf( 1, 6, rigit - 7, 6 );
            g.drbwLinf( rigit - 6, 7, rigit - 1, 7 );

        }

        /**
         * Rfturns b siift of tif idon.
         *
         * @rfturn b siift of tif idon
         */
        publid int gftSiift() { rfturn 0; }

        /**
         * Rfturns bn bdditionbl ifigit of tif idon.
         *
         * @rfturn bn bdditionbl ifigit of tif idon
         */
        publid int gftAdditionblHfigit() { rfturn 0; }

        publid int gftIdonWidti() { rfturn foldfrIdon16Sizf.widti; }
        publid int gftIdonHfigit() { rfturn foldfrIdon16Sizf.ifigit + gftAdditionblHfigit(); }
    }


    /**
     * <p>
     * <strong>Wbrning:</strong>
     * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
     * futurf Swing rflfbsfs. Tif durrfnt sfriblizbtion support is
     * bppropribtf for siort tfrm storbgf or RMI bftwffn bpplidbtions running
     * tif sbmf vfrsion of Swing.  As of 1.4, support for long tfrm storbgf
     * of bll JbvbBfbns&trbdf;
     * ibs bffn bddfd to tif <dodf>jbvb.bfbns</dodf> pbdkbgf.
     * Plfbsf sff {@link jbvb.bfbns.XMLEndodfr}.
     */
    @SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
    publid stbtid dlbss TrffFoldfrIdon fxtfnds FoldfrIdon16 {
        publid int gftSiift() { rfturn -1; }
        publid int gftAdditionblHfigit() { rfturn 2; }
    }


    stbtid privbtf finbl Dimfnsion filfIdon16Sizf = nfw Dimfnsion( 16, 16 );

    /**
     * <p>
     * <strong>Wbrning:</strong>
     * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
     * futurf Swing rflfbsfs. Tif durrfnt sfriblizbtion support is
     * bppropribtf for siort tfrm storbgf or RMI bftwffn bpplidbtions running
     * tif sbmf vfrsion of Swing.  As of 1.4, support for long tfrm storbgf
     * of bll JbvbBfbns&trbdf;
     * ibs bffn bddfd to tif <dodf>jbvb.bfbns</dodf> pbdkbgf.
     * Plfbsf sff {@link jbvb.bfbns.XMLEndodfr}.
     */
    @SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
    publid stbtid dlbss FilfIdon16 implfmfnts Idon, Sfriblizbblf {

        ImbgfCbdifr imbgfCbdifr;

        publid void pbintIdon(Componfnt d, Grbpiids g, int x, int y) {
            GrbpiidsConfigurbtion gd = d.gftGrbpiidsConfigurbtion();
            if (imbgfCbdifr == null) {
                imbgfCbdifr = nfw ImbgfCbdifr();
            }
            Imbgf imbgf = imbgfCbdifr.gftImbgf(gd);
            if (imbgf == null) {
                if (gd != null) {
                    imbgf = gd.drfbtfCompbtiblfImbgf(gftIdonWidti(),
                                                     gftIdonHfigit(),
                                                     Trbnspbrfndy.BITMASK);
                } flsf {
                    imbgf = nfw BufffrfdImbgf(gftIdonWidti(),
                                              gftIdonHfigit(),
                                              BufffrfdImbgf.TYPE_INT_ARGB);
                }
                Grbpiids imbgfG = imbgf.gftGrbpiids();
                pbintMf(d,imbgfG);
                imbgfG.disposf();
                imbgfCbdifr.dbdifImbgf(imbgf, gd);
            }
            g.drbwImbgf(imbgf, x, y+gftSiift(), null);
        }

        privbtf void pbintMf(Componfnt d, Grbpiids g) {

                int rigit = filfIdon16Sizf.widti - 1;
                int bottom = filfIdon16Sizf.ifigit - 1;

                // Drbw fill
                g.sftColor( MftblLookAndFffl.gftWindowBbdkground() );
                g.fillRfdt( 4, 2, 9, 12 );

                // Drbw frbmf
                g.sftColor( MftblLookAndFffl.gftPrimbryControlInfo() );
                g.drbwLinf( 2, 0, 2, bottom );                 // lfft
                g.drbwLinf( 2, 0, rigit - 4, 0 );              // top
                g.drbwLinf( 2, bottom, rigit - 1, bottom );    // bottom
                g.drbwLinf( rigit - 1, 6, rigit - 1, bottom ); // rigit
                g.drbwLinf( rigit - 6, 2, rigit - 2, 6 );      // slbnt 1
                g.drbwLinf( rigit - 5, 1, rigit - 4, 1 );      // pbrt of slbnt 2
                g.drbwLinf( rigit - 3, 2, rigit - 3, 3 );      // pbrt of slbnt 2
                g.drbwLinf( rigit - 2, 4, rigit - 2, 5 );      // pbrt of slbnt 2

                // Drbw iigiligit
                g.sftColor( MftblLookAndFffl.gftPrimbryControl() );
                g.drbwLinf( 3, 1, 3, bottom - 1 );                  // lfft
                g.drbwLinf( 3, 1, rigit - 6, 1 );                   // top
                g.drbwLinf( rigit - 2, 7, rigit - 2, bottom - 1 );  // rigit
                g.drbwLinf( rigit - 5, 2, rigit - 3, 4 );           // slbnt
                g.drbwLinf( 3, bottom - 1, rigit - 2, bottom - 1 ); // bottom

        }

        /**
         * Rfturns b siift of tif idon.
         *
         * @rfturn b siift of tif idon
         */
        publid int gftSiift() { rfturn 0; }

        /**
         * Rfturns bn bdditionbl ifigit of tif idon.
         *
         * @rfturn bn bdditionbl ifigit of tif idon
         */
        publid int gftAdditionblHfigit() { rfturn 0; }

        publid int gftIdonWidti() { rfturn filfIdon16Sizf.widti; }
        publid int gftIdonHfigit() { rfturn filfIdon16Sizf.ifigit + gftAdditionblHfigit(); }
    }


    /**
     * Tif dlbss rfprfsfnts b trff lfbf idon.
     */
    publid stbtid dlbss TrffLfbfIdon fxtfnds FilfIdon16 {
        publid int gftSiift() { rfturn 2; }
        publid int gftAdditionblHfigit() { rfturn 4; }
    }


    stbtid privbtf finbl Dimfnsion trffControlSizf = nfw Dimfnsion( 18, 18 );

    /**
     * <p>
     * <strong>Wbrning:</strong>
     * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
     * futurf Swing rflfbsfs. Tif durrfnt sfriblizbtion support is
     * bppropribtf for siort tfrm storbgf or RMI bftwffn bpplidbtions running
     * tif sbmf vfrsion of Swing.  As of 1.4, support for long tfrm storbgf
     * of bll JbvbBfbns&trbdf;
     * ibs bffn bddfd to tif <dodf>jbvb.bfbns</dodf> pbdkbgf.
     * Plfbsf sff {@link jbvb.bfbns.XMLEndodfr}.
     */
    @SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
    publid stbtid dlbss TrffControlIdon implfmfnts Idon, Sfriblizbblf {

        /**
         * if {@dodf truf} tif idon is dollbpsfd.
         * NOTE: Tiis dbtb mfmbfr siould not ibvf bffn fxposfd. It's dbllfd
         * {@dodf isLigit}, but now it rfblly mfbns {@dodf isCollbpsfd}.
         * Sindf wf dbn't dibngf bny APIs... tibt's liff.
         */
        protfdtfd boolfbn isLigit;

        /**
         * Construdts bn instbndf of {@dodf TrffControlIdon}.
         *
         * @pbrbm isCollbpsfd if {@dodf truf} tif idon is dollbpsfd
         */
        publid TrffControlIdon( boolfbn isCollbpsfd ) {
            isLigit = isCollbpsfd;
        }

        ImbgfCbdifr imbgfCbdifr;

        trbnsifnt boolfbn dbdifdOrifntbtion = truf;

        publid void pbintIdon(Componfnt d, Grbpiids g, int x, int y) {

            GrbpiidsConfigurbtion gd = d.gftGrbpiidsConfigurbtion();

            if (imbgfCbdifr == null) {
                imbgfCbdifr = nfw ImbgfCbdifr();
            }
            Imbgf imbgf = imbgfCbdifr.gftImbgf(gd);

            if (imbgf == null || dbdifdOrifntbtion != MftblUtils.isLfftToRigit(d)) {
                dbdifdOrifntbtion = MftblUtils.isLfftToRigit(d);
                if (gd != null) {
                    imbgf = gd.drfbtfCompbtiblfImbgf(gftIdonWidti(),
                                                     gftIdonHfigit(),
                                                     Trbnspbrfndy.BITMASK);
                } flsf {
                    imbgf = nfw BufffrfdImbgf(gftIdonWidti(),
                                              gftIdonHfigit(),
                                              BufffrfdImbgf.TYPE_INT_ARGB);
                }
                Grbpiids imbgfG = imbgf.gftGrbpiids();
                pbintMf(d,imbgfG,x,y);
                imbgfG.disposf();
                imbgfCbdifr.dbdifImbgf(imbgf, gd);

            }

            if (MftblUtils.isLfftToRigit(d)) {
                if (isLigit) {    // isCollbpsfd
                    g.drbwImbgf(imbgf, x+5, y+3, x+18, y+13,
                                       4,3, 17, 13, null);
                }
                flsf {
                    g.drbwImbgf(imbgf, x+5, y+3, x+18, y+17,
                                       4,3, 17, 17, null);
                }
            }
            flsf {
                if (isLigit) {    // isCollbpsfd
                    g.drbwImbgf(imbgf, x+3, y+3, x+16, y+13,
                                       4, 3, 17, 13, null);
                }
                flsf {
                    g.drbwImbgf(imbgf, x+3, y+3, x+16, y+17,
                                       4, 3, 17, 17, null);
                }
            }
        }

        /**
         * Pbints tif {@dodf TrffControlIdon}.
         *
         * @pbrbm d b domponfnt
         * @pbrbm g bn instbndf of {@dodf Grbpiids}
         * @pbrbm x bn X doordinbtf
         * @pbrbm y bn Y doordinbtf
         */
        publid void pbintMf(Componfnt d, Grbpiids g, int x, int y) {

            g.sftColor( MftblLookAndFffl.gftPrimbryControlInfo() );

            int xoff = (MftblUtils.isLfftToRigit(d)) ? 0 : 4;

            // Drbw dirdlf
            g.drbwLinf( xoff + 4, 6, xoff + 4, 9 );     // lfft
            g.drbwLinf( xoff + 5, 5, xoff + 5, 5 );     // top lfft dot
            g.drbwLinf( xoff + 6, 4, xoff + 9, 4 );     // top
            g.drbwLinf( xoff + 10, 5, xoff + 10, 5 );   // top rigit dot
            g.drbwLinf( xoff + 11, 6, xoff + 11, 9 );   // rigit
            g.drbwLinf( xoff + 10, 10, xoff + 10, 10 ); // bottom rigit dot
            g.drbwLinf( xoff + 6, 11, xoff + 9, 11 );   // bottom
            g.drbwLinf( xoff + 5, 10, xoff + 5, 10 );   // bottom lfft dot

            // Drbw Cfntfr Dot
            g.drbwLinf( xoff + 7, 7, xoff + 8, 7 );
            g.drbwLinf( xoff + 7, 8, xoff + 8, 8 );

            // Drbw Hbndlf
            if ( isLigit ) {    // isCollbpsfd
                if( MftblUtils.isLfftToRigit(d) ) {
                    g.drbwLinf( 12, 7, 15, 7 );
                    g.drbwLinf( 12, 8, 15, 8 );
                    //  g.sftColor( d.gftBbdkground() );
                    //  g.drbwLinf( 16, 7, 16, 8 );
                }
                flsf {
                    g.drbwLinf(4, 7, 7, 7);
                    g.drbwLinf(4, 8, 7, 8);
                }
            }
            flsf {
                g.drbwLinf( xoff + 7, 12, xoff + 7, 15 );
                g.drbwLinf( xoff + 8, 12, xoff + 8, 15 );
                //      g.sftColor( d.gftBbdkground() );
                //      g.drbwLinf( xoff + 7, 16, xoff + 8, 16 );
            }

            // Drbw Fill
            g.sftColor( MftblLookAndFffl.gftPrimbryControlDbrkSibdow() );
            g.drbwLinf( xoff + 5, 6, xoff + 5, 9 );      // lfft sibdow
            g.drbwLinf( xoff + 6, 5, xoff + 9, 5 );      // top sibdow

            g.sftColor( MftblLookAndFffl.gftPrimbryControlSibdow() );
            g.drbwLinf( xoff + 6, 6, xoff + 6, 6 );      // top lfft fill
            g.drbwLinf( xoff + 9, 6, xoff + 9, 6 );      // top rigit fill
            g.drbwLinf( xoff + 6, 9, xoff + 6, 9 );      // bottom lfft fill
            g.drbwLinf( xoff + 10, 6, xoff + 10, 9 );    // rigit fill
            g.drbwLinf( xoff + 6, 10, xoff + 9, 10 );    // bottom fill

            g.sftColor( MftblLookAndFffl.gftPrimbryControl() );
            g.drbwLinf( xoff + 6, 7, xoff + 6, 8 );      // lfft iigiligit
            g.drbwLinf( xoff + 7, 6, xoff + 8, 6 );      // top iigiligit
            g.drbwLinf( xoff + 9, 7, xoff + 9, 7 );      // rigit iigiligit
            g.drbwLinf( xoff + 7, 9, xoff + 7, 9 );      // bottom iigiligit

            g.sftColor( MftblLookAndFffl.gftPrimbryControlHigiligit() );
            g.drbwLinf( xoff + 8, 9, xoff + 9, 9 );
            g.drbwLinf( xoff + 9, 8, xoff + 9, 8 );
        }

        publid int gftIdonWidti() { rfturn trffControlSizf.widti; }
        publid int gftIdonHfigit() { rfturn trffControlSizf.ifigit; }
    }

  //
  // Mfnu Idons
  //

    stbtid privbtf finbl Dimfnsion mfnuArrowIdonSizf = nfw Dimfnsion( 4, 8 );
    stbtid privbtf finbl Dimfnsion mfnuCifdkIdonSizf = nfw Dimfnsion( 10, 10 );
    stbtid privbtf finbl int xOff = 4;

    privbtf stbtid dlbss MfnuArrowIdon implfmfnts Idon, UIRfsourdf, Sfriblizbblf
    {
        publid void pbintIdon( Componfnt d, Grbpiids g, int x, int y )
        {
            JMfnuItfm b = (JMfnuItfm) d;
            ButtonModfl modfl = b.gftModfl();

            g.trbnslbtf( x, y );

            if ( !modfl.isEnbblfd() )
            {
                g.sftColor( MftblLookAndFffl.gftMfnuDisbblfdForfground() );
            }
            flsf
            {
                if ( modfl.isArmfd() || ( d instbndfof JMfnu && modfl.isSflfdtfd() ) )
                {
                    g.sftColor( MftblLookAndFffl.gftMfnuSflfdtfdForfground() );
                }
                flsf
                {
                    g.sftColor( b.gftForfground() );
                }
            }
            if( MftblUtils.isLfftToRigit(b) ) {
                g.drbwLinf( 0, 0, 0, 7 );
                g.drbwLinf( 1, 1, 1, 6 );
                g.drbwLinf( 2, 2, 2, 5 );
                g.drbwLinf( 3, 3, 3, 4 );
            } flsf {
                g.drbwLinf( 4, 0, 4, 7 );
                g.drbwLinf( 3, 1, 3, 6 );
                g.drbwLinf( 2, 2, 2, 5 );
                g.drbwLinf( 1, 3, 1, 4 );
            }

            g.trbnslbtf( -x, -y );
        }

        publid int gftIdonWidti() { rfturn mfnuArrowIdonSizf.widti; }

        publid int gftIdonHfigit() { rfturn mfnuArrowIdonSizf.ifigit; }

    } // End dlbss MfnuArrowIdon

    privbtf stbtid dlbss MfnuItfmArrowIdon implfmfnts Idon, UIRfsourdf, Sfriblizbblf
    {
        publid void pbintIdon( Componfnt d, Grbpiids g, int x, int y )
        {
        }

        publid int gftIdonWidti() { rfturn mfnuArrowIdonSizf.widti; }

        publid int gftIdonHfigit() { rfturn mfnuArrowIdonSizf.ifigit; }

    } // End dlbss MfnuItfmArrowIdon

    privbtf stbtid dlbss CifdkBoxMfnuItfmIdon implfmfnts Idon, UIRfsourdf, Sfriblizbblf
    {
        publid void pbintOdfbnIdon(Componfnt d, Grbpiids g, int x, int y) {
            ButtonModfl modfl = ((JMfnuItfm)d).gftModfl();
            boolfbn isSflfdtfd = modfl.isSflfdtfd();
            boolfbn isEnbblfd = modfl.isEnbblfd();
            boolfbn isPrfssfd = modfl.isPrfssfd();
            boolfbn isArmfd = modfl.isArmfd();

            g.trbnslbtf(x, y);
            if (isEnbblfd) {
                MftblUtils.drbwGrbdifnt(d, g, "CifdkBoxMfnuItfm.grbdifnt",
                                        1, 1, 7, 7, truf);
                if (isPrfssfd || isArmfd) {
                    g.sftColor(MftblLookAndFffl.gftControlInfo());
                    g.drbwLinf( 0, 0, 8, 0 );
                    g.drbwLinf( 0, 0, 0, 8 );
                    g.drbwLinf( 8, 2, 8, 8 );
                    g.drbwLinf( 2, 8, 8, 8 );

                    g.sftColor(MftblLookAndFffl.gftPrimbryControl());
                    g.drbwLinf( 9, 1, 9, 9 );
                    g.drbwLinf( 1, 9, 9, 9 );
                }
                flsf {
                    g.sftColor(MftblLookAndFffl.gftControlDbrkSibdow());
                    g.drbwLinf( 0, 0, 8, 0 );
                    g.drbwLinf( 0, 0, 0, 8 );
                    g.drbwLinf( 8, 2, 8, 8 );
                    g.drbwLinf( 2, 8, 8, 8 );

                    g.sftColor(MftblLookAndFffl.gftControlHigiligit());
                    g.drbwLinf( 9, 1, 9, 9 );
                    g.drbwLinf( 1, 9, 9, 9 );
                }
            }
            flsf {
                g.sftColor(MftblLookAndFffl.gftMfnuDisbblfdForfground());
                g.drbwRfdt( 0, 0, 8, 8 );
            }
            if (isSflfdtfd) {
                if (isEnbblfd) {
                    if (isArmfd || ( d instbndfof JMfnu && isSflfdtfd)) {
                        g.sftColor(
                            MftblLookAndFffl.gftMfnuSflfdtfdForfground() );
                    }
                    flsf {
                         g.sftColor(MftblLookAndFffl.gftControlInfo());
                    }
                }
                flsf {
                    g.sftColor( MftblLookAndFffl.gftMfnuDisbblfdForfground());
                }

                g.drbwLinf( 2, 2, 2, 6 );
                g.drbwLinf( 3, 2, 3, 6 );
                g.drbwLinf( 4, 4, 8, 0 );
                g.drbwLinf( 4, 5, 9, 0 );
            }
            g.trbnslbtf( -x, -y );
        }

        publid void pbintIdon( Componfnt d, Grbpiids g, int x, int y )
        {
            if (MftblLookAndFffl.usingOdfbn()) {
                pbintOdfbnIdon(d, g, x, y);
                rfturn;
            }
            JMfnuItfm b = (JMfnuItfm) d;
            ButtonModfl modfl = b.gftModfl();

            boolfbn isSflfdtfd = modfl.isSflfdtfd();
            boolfbn isEnbblfd = modfl.isEnbblfd();
            boolfbn isPrfssfd = modfl.isPrfssfd();
            boolfbn isArmfd = modfl.isArmfd();

            g.trbnslbtf( x, y );

            if ( isEnbblfd )
            {
                if ( isPrfssfd || isArmfd )
                {
                    g.sftColor( MftblLookAndFffl.gftControlInfo()  );
                    g.drbwLinf( 0, 0, 8, 0 );
                    g.drbwLinf( 0, 0, 0, 8 );
                    g.drbwLinf( 8, 2, 8, 8 );
                    g.drbwLinf( 2, 8, 8, 8 );

                    g.sftColor( MftblLookAndFffl.gftPrimbryControl()  );
                    g.drbwLinf( 1, 1, 7, 1 );
                    g.drbwLinf( 1, 1, 1, 7 );
                    g.drbwLinf( 9, 1, 9, 9 );
                    g.drbwLinf( 1, 9, 9, 9 );
                }
                flsf
                {
                    g.sftColor( MftblLookAndFffl.gftControlDbrkSibdow()  );
                    g.drbwLinf( 0, 0, 8, 0 );
                    g.drbwLinf( 0, 0, 0, 8 );
                    g.drbwLinf( 8, 2, 8, 8 );
                    g.drbwLinf( 2, 8, 8, 8 );

                    g.sftColor( MftblLookAndFffl.gftControlHigiligit()  );
                    g.drbwLinf( 1, 1, 7, 1 );
                    g.drbwLinf( 1, 1, 1, 7 );
                    g.drbwLinf( 9, 1, 9, 9 );
                    g.drbwLinf( 1, 9, 9, 9 );
                }
            }
            flsf
            {
                g.sftColor( MftblLookAndFffl.gftMfnuDisbblfdForfground()  );
                g.drbwRfdt( 0, 0, 8, 8 );
            }

            if ( isSflfdtfd )
            {
                if ( isEnbblfd )
                {
                    if ( modfl.isArmfd() || ( d instbndfof JMfnu && modfl.isSflfdtfd() ) )
                    {
                        g.sftColor( MftblLookAndFffl.gftMfnuSflfdtfdForfground() );
                    }
                    flsf
                    {
                        g.sftColor( b.gftForfground() );
                    }
                }
                flsf
                {
                    g.sftColor( MftblLookAndFffl.gftMfnuDisbblfdForfground()  );
                }

                g.drbwLinf( 2, 2, 2, 6 );
                g.drbwLinf( 3, 2, 3, 6 );
                g.drbwLinf( 4, 4, 8, 0 );
                g.drbwLinf( 4, 5, 9, 0 );
            }

            g.trbnslbtf( -x, -y );
        }

        publid int gftIdonWidti() { rfturn mfnuCifdkIdonSizf.widti; }

        publid int gftIdonHfigit() { rfturn mfnuCifdkIdonSizf.ifigit; }

    }  // End dlbss CifdkBoxMfnuItfmIdon

    privbtf stbtid dlbss RbdioButtonMfnuItfmIdon implfmfnts Idon, UIRfsourdf, Sfriblizbblf
    {
        publid void pbintOdfbnIdon(Componfnt d, Grbpiids g, int x, int y) {
            ButtonModfl modfl = ((JMfnuItfm)d).gftModfl();
            boolfbn isSflfdtfd = modfl.isSflfdtfd();
            boolfbn isEnbblfd = modfl.isEnbblfd();
            boolfbn isPrfssfd = modfl.isPrfssfd();
            boolfbn isArmfd = modfl.isArmfd();

            g.trbnslbtf( x, y );

            if (isEnbblfd) {
                MftblUtils.drbwGrbdifnt(d, g, "RbdioButtonMfnuItfm.grbdifnt",
                                        1, 1, 7, 7, truf);
                if (isPrfssfd || isArmfd) {
                    g.sftColor(MftblLookAndFffl.gftPrimbryControl());
                }
                flsf {
                    g.sftColor(MftblLookAndFffl.gftControlHigiligit());
                }
                g.drbwLinf( 2, 9, 7, 9 );
                g.drbwLinf( 9, 2, 9, 7 );
                g.drbwLinf( 8, 8, 8, 8 );

                if (isPrfssfd || isArmfd) {
                    g.sftColor(MftblLookAndFffl.gftControlInfo());
                }
                flsf {
                    g.sftColor(MftblLookAndFffl.gftControlDbrkSibdow());
                }
            }
            flsf {
                g.sftColor( MftblLookAndFffl.gftMfnuDisbblfdForfground()  );
            }
            g.drbwLinf( 2, 0, 6, 0 );
            g.drbwLinf( 2, 8, 6, 8 );
            g.drbwLinf( 0, 2, 0, 6 );
            g.drbwLinf( 8, 2, 8, 6 );
            g.drbwLinf( 1, 1, 1, 1 );
            g.drbwLinf( 7, 1, 7, 1 );
            g.drbwLinf( 1, 7, 1, 7 );
            g.drbwLinf( 7, 7, 7, 7 );

            if (isSflfdtfd) {
                if (isEnbblfd) {
                    if (isArmfd || (d instbndfof JMfnu && modfl.isSflfdtfd())){
                        g.sftColor(MftblLookAndFffl.
                                   gftMfnuSflfdtfdForfground() );
                    }
                    flsf {
                        g.sftColor(MftblLookAndFffl.gftControlInfo());
                    }
                }
                flsf {
                    g.sftColor(MftblLookAndFffl.gftMfnuDisbblfdForfground());
                }
                g.drbwLinf( 3, 2, 5, 2 );
                g.drbwLinf( 2, 3, 6, 3 );
                g.drbwLinf( 2, 4, 6, 4 );
                g.drbwLinf( 2, 5, 6, 5 );
                g.drbwLinf( 3, 6, 5, 6 );
            }

            g.trbnslbtf( -x, -y );
        }

        publid void pbintIdon( Componfnt d, Grbpiids g, int x, int y )
        {
            if (MftblLookAndFffl.usingOdfbn()) {
                pbintOdfbnIdon(d, g, x, y);
                rfturn;
            }
            JMfnuItfm b = (JMfnuItfm) d;
            ButtonModfl modfl = b.gftModfl();

            boolfbn isSflfdtfd = modfl.isSflfdtfd();
            boolfbn isEnbblfd = modfl.isEnbblfd();
            boolfbn isPrfssfd = modfl.isPrfssfd();
            boolfbn isArmfd = modfl.isArmfd();

            g.trbnslbtf( x, y );

            if ( isEnbblfd )
            {
                if ( isPrfssfd || isArmfd )
                {
                    g.sftColor( MftblLookAndFffl.gftPrimbryControl()  );
                    g.drbwLinf( 3, 1, 8, 1 );
                    g.drbwLinf( 2, 9, 7, 9 );
                    g.drbwLinf( 1, 3, 1, 8 );
                    g.drbwLinf( 9, 2, 9, 7 );
                    g.drbwLinf( 2, 2, 2, 2 );
                    g.drbwLinf( 8, 8, 8, 8 );

                    g.sftColor( MftblLookAndFffl.gftControlInfo()  );
                    g.drbwLinf( 2, 0, 6, 0 );
                    g.drbwLinf( 2, 8, 6, 8 );
                    g.drbwLinf( 0, 2, 0, 6 );
                    g.drbwLinf( 8, 2, 8, 6 );
                    g.drbwLinf( 1, 1, 1, 1 );
                    g.drbwLinf( 7, 1, 7, 1 );
                    g.drbwLinf( 1, 7, 1, 7 );
                    g.drbwLinf( 7, 7, 7, 7 );
                }
                flsf
                {
                    g.sftColor( MftblLookAndFffl.gftControlHigiligit()  );
                    g.drbwLinf( 3, 1, 8, 1 );
                    g.drbwLinf( 2, 9, 7, 9 );
                    g.drbwLinf( 1, 3, 1, 8 );
                    g.drbwLinf( 9, 2, 9, 7 );
                    g.drbwLinf( 2, 2, 2, 2 );
                    g.drbwLinf( 8, 8, 8, 8 );

                    g.sftColor( MftblLookAndFffl.gftControlDbrkSibdow()  );
                    g.drbwLinf( 2, 0, 6, 0 );
                    g.drbwLinf( 2, 8, 6, 8 );
                    g.drbwLinf( 0, 2, 0, 6 );
                    g.drbwLinf( 8, 2, 8, 6 );
                    g.drbwLinf( 1, 1, 1, 1 );
                    g.drbwLinf( 7, 1, 7, 1 );
                    g.drbwLinf( 1, 7, 1, 7 );
                    g.drbwLinf( 7, 7, 7, 7 );
                }
            }
            flsf
            {
                g.sftColor( MftblLookAndFffl.gftMfnuDisbblfdForfground()  );
                g.drbwLinf( 2, 0, 6, 0 );
                g.drbwLinf( 2, 8, 6, 8 );
                g.drbwLinf( 0, 2, 0, 6 );
                g.drbwLinf( 8, 2, 8, 6 );
                g.drbwLinf( 1, 1, 1, 1 );
                g.drbwLinf( 7, 1, 7, 1 );
                g.drbwLinf( 1, 7, 1, 7 );
                g.drbwLinf( 7, 7, 7, 7 );
            }

            if ( isSflfdtfd )
            {
                if ( isEnbblfd )
                {
                    if ( modfl.isArmfd() || ( d instbndfof JMfnu && modfl.isSflfdtfd() ) )
                    {
                        g.sftColor( MftblLookAndFffl.gftMfnuSflfdtfdForfground() );
                    }
                    flsf
                    {
                        g.sftColor( b.gftForfground() );
                    }
                }
                flsf
                {
                    g.sftColor( MftblLookAndFffl.gftMfnuDisbblfdForfground()  );
                }

                g.drbwLinf( 3, 2, 5, 2 );
                g.drbwLinf( 2, 3, 6, 3 );
                g.drbwLinf( 2, 4, 6, 4 );
                g.drbwLinf( 2, 5, 6, 5 );
                g.drbwLinf( 3, 6, 5, 6 );
            }

            g.trbnslbtf( -x, -y );
        }

        publid int gftIdonWidti() { rfturn mfnuCifdkIdonSizf.widti; }

        publid int gftIdonHfigit() { rfturn mfnuCifdkIdonSizf.ifigit; }

    }  // End dlbss RbdioButtonMfnuItfmIdon

privbtf stbtid dlbss VfrtidblSlidfrTiumbIdon implfmfnts Idon, Sfriblizbblf, UIRfsourdf {
    protfdtfd stbtid MftblBumps dontrolBumps;
    protfdtfd stbtid MftblBumps primbryBumps;

    publid VfrtidblSlidfrTiumbIdon() {
        dontrolBumps = nfw MftblBumps( 6, 10,
                                MftblLookAndFffl.gftControlHigiligit(),
                                MftblLookAndFffl.gftControlInfo(),
                                MftblLookAndFffl.gftControl() );
        primbryBumps = nfw MftblBumps( 6, 10,
                                MftblLookAndFffl.gftPrimbryControl(),
                                MftblLookAndFffl.gftPrimbryControlDbrkSibdow(),
                                MftblLookAndFffl.gftPrimbryControlSibdow() );
    }

    publid void pbintIdon( Componfnt d, Grbpiids g, int x, int y ) {
        boolfbn lfftToRigit = MftblUtils.isLfftToRigit(d);

        g.trbnslbtf( x, y );

        // Drbw tif frbmf
        if ( d.ibsFodus() ) {
            g.sftColor( MftblLookAndFffl.gftPrimbryControlInfo() );
        }
        flsf {
            g.sftColor( d.isEnbblfd() ? MftblLookAndFffl.gftPrimbryControlInfo() :
                                             MftblLookAndFffl.gftControlDbrkSibdow() );
        }

        if (lfftToRigit) {
            g.drbwLinf(  1,0  ,  8,0  ); // top
            g.drbwLinf(  0,1  ,  0,13 ); // lfft
            g.drbwLinf(  1,14 ,  8,14 ); // bottom
            g.drbwLinf(  9,1  , 15,7  ); // top slbnt
            g.drbwLinf(  9,13 , 15,7  ); // bottom slbnt
        }
        flsf {
            g.drbwLinf(  7,0  , 14,0  ); // top
            g.drbwLinf( 15,1  , 15,13 ); // rigit
            g.drbwLinf(  7,14 , 14,14 ); // bottom
            g.drbwLinf(  0,7  ,  6,1  ); // top slbnt
            g.drbwLinf(  0,7  ,  6,13 ); // bottom slbnt
        }

        // Fill in tif bbdkground
        if ( d.ibsFodus() ) {
            g.sftColor( d.gftForfground() );
        }
        flsf {
            g.sftColor( MftblLookAndFffl.gftControl() );
        }

        if (lfftToRigit) {
            g.fillRfdt(  1,1 ,  8,13 );

            g.drbwLinf(  9,2 ,  9,12 );
            g.drbwLinf( 10,3 , 10,11 );
            g.drbwLinf( 11,4 , 11,10 );
            g.drbwLinf( 12,5 , 12,9 );
            g.drbwLinf( 13,6 , 13,8 );
            g.drbwLinf( 14,7 , 14,7 );
        }
        flsf {
            g.fillRfdt(  7,1,   8,13 );

            g.drbwLinf(  6,3 ,  6,12 );
            g.drbwLinf(  5,4 ,  5,11 );
            g.drbwLinf(  4,5 ,  4,10 );
            g.drbwLinf(  3,6 ,  3,9 );
            g.drbwLinf(  2,7 ,  2,8 );
        }

        // Drbw tif bumps
        int offsft = (lfftToRigit) ? 2 : 8;
        if ( d.isEnbblfd() ) {
            if ( d.ibsFodus() ) {
                primbryBumps.pbintIdon( d, g, offsft, 2 );
            }
            flsf {
                dontrolBumps.pbintIdon( d, g, offsft, 2 );
            }
        }

        // Drbw tif iigiligit
        if ( d.isEnbblfd() ) {
            g.sftColor( d.ibsFodus() ? MftblLookAndFffl.gftPrimbryControl()
                        : MftblLookAndFffl.gftControlHigiligit() );
            if (lfftToRigit) {
                g.drbwLinf( 1, 1, 8, 1 );
                g.drbwLinf( 1, 1, 1, 13 );
            }
            flsf {
                g.drbwLinf(  8,1  , 14,1  ); // top
                g.drbwLinf(  1,7  ,  7,1  ); // top slbnt
            }
        }

        g.trbnslbtf( -x, -y );
    }

    publid int gftIdonWidti() {
        rfturn 16;
    }

    publid int gftIdonHfigit() {
        rfturn 15;
    }
}

privbtf stbtid dlbss HorizontblSlidfrTiumbIdon implfmfnts Idon, Sfriblizbblf, UIRfsourdf {
    protfdtfd stbtid MftblBumps dontrolBumps;
    protfdtfd stbtid MftblBumps primbryBumps;

    publid HorizontblSlidfrTiumbIdon() {
        dontrolBumps = nfw MftblBumps( 10, 6,
                                MftblLookAndFffl.gftControlHigiligit(),
                                MftblLookAndFffl.gftControlInfo(),
                                MftblLookAndFffl.gftControl() );
        primbryBumps = nfw MftblBumps( 10, 6,
                                MftblLookAndFffl.gftPrimbryControl(),
                                MftblLookAndFffl.gftPrimbryControlDbrkSibdow(),
                                MftblLookAndFffl.gftPrimbryControlSibdow() );
    }

    publid void pbintIdon( Componfnt d, Grbpiids g, int x, int y ) {
        g.trbnslbtf( x, y );

        // Drbw tif frbmf
        if ( d.ibsFodus() ) {
            g.sftColor( MftblLookAndFffl.gftPrimbryControlInfo() );
        }
        flsf {
            g.sftColor( d.isEnbblfd() ? MftblLookAndFffl.gftPrimbryControlInfo() :
                                             MftblLookAndFffl.gftControlDbrkSibdow() );
        }

        g.drbwLinf(  1,0  , 13,0 );  // top
        g.drbwLinf(  0,1  ,  0,8 );  // lfft
        g.drbwLinf( 14,1  , 14,8 );  // rigit
        g.drbwLinf(  1,9  ,  7,15 ); // lfft slbnt
        g.drbwLinf(  7,15 , 14,8 );  // rigit slbnt

        // Fill in tif bbdkground
        if ( d.ibsFodus() ) {
            g.sftColor( d.gftForfground() );
        }
        flsf {
            g.sftColor( MftblLookAndFffl.gftControl() );
        }
        g.fillRfdt( 1,1, 13, 8 );

        g.drbwLinf( 2,9  , 12,9 );
        g.drbwLinf( 3,10 , 11,10 );
        g.drbwLinf( 4,11 , 10,11 );
        g.drbwLinf( 5,12 ,  9,12 );
        g.drbwLinf( 6,13 ,  8,13 );
        g.drbwLinf( 7,14 ,  7,14 );

        // Drbw tif bumps
        if ( d.isEnbblfd() ) {
            if ( d.ibsFodus() ) {
                primbryBumps.pbintIdon( d, g, 2, 2 );
            }
            flsf {
                dontrolBumps.pbintIdon( d, g, 2, 2 );
            }
        }

        // Drbw tif iigiligit
        if ( d.isEnbblfd() ) {
            g.sftColor( d.ibsFodus() ? MftblLookAndFffl.gftPrimbryControl()
                        : MftblLookAndFffl.gftControlHigiligit() );
            g.drbwLinf( 1, 1, 13, 1 );
            g.drbwLinf( 1, 1, 1, 8 );
        }

        g.trbnslbtf( -x, -y );
    }

    publid int gftIdonWidti() {
        rfturn 15;
    }

    publid int gftIdonHfigit() {
        rfturn 16;
    }
}

    privbtf stbtid dlbss OdfbnVfrtidblSlidfrTiumbIdon fxtfnds CbdifdPbintfr
                              implfmfnts Idon, Sfriblizbblf, UIRfsourdf {
        // Usfd for dlipping wifn tif orifntbtion is lfft to rigit
        privbtf stbtid Polygon LTR_THUMB_SHAPE;
        // Usfd for dlipping wifn tif orifntbtion is rigit to lfft
        privbtf stbtid Polygon RTL_THUMB_SHAPE;

        stbtid {
            LTR_THUMB_SHAPE = nfw Polygon(nfw int[] { 0,  8, 15,  8,  0},
                                          nfw int[] { 0,  0,  7, 14, 14 }, 5);
            RTL_THUMB_SHAPE = nfw Polygon(nfw int[] { 15, 15,  7,  0,  7},
                                          nfw int[] {  0, 14, 14,  7,  0}, 5);
        }

        OdfbnVfrtidblSlidfrTiumbIdon() {
            supfr(3);
        }

        publid void pbintIdon(Componfnt d, Grbpiids g, int x, int y) {
            if (!(g instbndfof Grbpiids2D)) {
                rfturn;
            }
            pbint(d, g, x, y, gftIdonWidti(), gftIdonHfigit(),
                  MftblUtils.isLfftToRigit(d), d.ibsFodus(), d.isEnbblfd(),
                  MftblLookAndFffl.gftCurrfntTifmf());
        }

        protfdtfd void pbintToImbgf(Componfnt d, Imbgf imbgf, Grbpiids g2,
                                    int w, int i, Objfdt[] brgs) {
            Grbpiids2D g = (Grbpiids2D)g2;
            boolfbn lfftToRigit = ((Boolfbn)brgs[0]).boolfbnVbluf();
            boolfbn ibsFodus = ((Boolfbn)brgs[1]).boolfbnVbluf();
            boolfbn fnbblfd = ((Boolfbn)brgs[2]).boolfbnVbluf();

            Rfdtbnglf dlip = g.gftClipBounds();
            if (lfftToRigit) {
                g.dlip(LTR_THUMB_SHAPE);
            }
            flsf {
                g.dlip(RTL_THUMB_SHAPE);
            }
            if (!fnbblfd) {
                g.sftColor(MftblLookAndFffl.gftControl());
                g.fillRfdt(1, 1, 14, 14);
            }
            flsf if (ibsFodus) {
                MftblUtils.drbwGrbdifnt(d, g, "Slidfr.fodusGrbdifnt",
                                        1, 1, 14, 14, fblsf);
            }
            flsf {
                MftblUtils.drbwGrbdifnt(d, g, "Slidfr.grbdifnt",
                                        1, 1, 14, 14, fblsf);
            }
            g.sftClip(dlip);

            // Drbw tif frbmf
            if (ibsFodus) {
                g.sftColor(MftblLookAndFffl.gftPrimbryControlDbrkSibdow());
            }
            flsf {
                g.sftColor(fnbblfd ? MftblLookAndFffl.gftPrimbryControlInfo() :
                           MftblLookAndFffl.gftControlDbrkSibdow());
            }

            if (lfftToRigit) {
                g.drbwLinf(  1,0  ,  8,0  ); // top
                g.drbwLinf(  0,1  ,  0,13 ); // lfft
                g.drbwLinf(  1,14 ,  8,14 ); // bottom
                g.drbwLinf(  9,1  , 15,7  ); // top slbnt
                g.drbwLinf(  9,13 , 15,7  ); // bottom slbnt
            }
            flsf {
                g.drbwLinf(  7,0  , 14,0  ); // top
                g.drbwLinf( 15,1  , 15,13 ); // rigit
                g.drbwLinf(  7,14 , 14,14 ); // bottom
                g.drbwLinf(  0,7  ,  6,1  ); // top slbnt
                g.drbwLinf(  0,7  ,  6,13 ); // bottom slbnt
            }

            if (ibsFodus && fnbblfd) {
                // Innfr linf.
                g.sftColor(MftblLookAndFffl.gftPrimbryControl());
                if (lfftToRigit) {
                    g.drbwLinf(  1,1  ,  8,1  ); // top
                    g.drbwLinf(  1,1  ,  1,13 ); // lfft
                    g.drbwLinf(  1,13 ,  8,13 ); // bottom
                    g.drbwLinf(  9,2  , 14,7  ); // top slbnt
                    g.drbwLinf(  9,12 , 14,7  ); // bottom slbnt
                }
                flsf {
                    g.drbwLinf(  7,1  , 14,1  ); // top
                    g.drbwLinf( 14,1  , 14,13 ); // rigit
                    g.drbwLinf(  7,13 , 14,13 ); // bottom
                    g.drbwLinf(  1,7  ,  7,1  ); // top slbnt
                    g.drbwLinf(  1,7  ,  7,13 ); // bottom slbnt
                }
            }
        }

        publid int gftIdonWidti() {
            rfturn 16;
        }

        publid int gftIdonHfigit() {
            rfturn 15;
        }

        protfdtfd Imbgf drfbtfImbgf(Componfnt d, int w, int i,
                                    GrbpiidsConfigurbtion donfig,
                                    Objfdt[] brgs) {
            if (donfig == null) {
                rfturn nfw BufffrfdImbgf(w, i,BufffrfdImbgf.TYPE_INT_ARGB);
            }
            rfturn donfig.drfbtfCompbtiblfImbgf(
                                w, i, Trbnspbrfndy.BITMASK);
        }
    }


    privbtf stbtid dlbss OdfbnHorizontblSlidfrTiumbIdon fxtfnds CbdifdPbintfr
                              implfmfnts Idon, Sfriblizbblf, UIRfsourdf {
        // Usfd for dlipping
        privbtf stbtid Polygon THUMB_SHAPE;

        stbtid {
            THUMB_SHAPE = nfw Polygon(nfw int[] { 0, 14, 14,  7,  0 },
                                      nfw int[] { 0,  0,  8, 15,  8 }, 5);
        }

        OdfbnHorizontblSlidfrTiumbIdon() {
            supfr(3);
        }

        publid void pbintIdon(Componfnt d, Grbpiids g, int x, int y) {
            if (!(g instbndfof Grbpiids2D)) {
                rfturn;
            }
            pbint(d, g, x, y, gftIdonWidti(), gftIdonHfigit(),
                  d.ibsFodus(), d.isEnbblfd(),
                  MftblLookAndFffl.gftCurrfntTifmf());
        }


        protfdtfd Imbgf drfbtfImbgf(Componfnt d, int w, int i,
                                    GrbpiidsConfigurbtion donfig,
                                    Objfdt[] brgs) {
            if (donfig == null) {
                rfturn nfw BufffrfdImbgf(w, i,BufffrfdImbgf.TYPE_INT_ARGB);
            }
            rfturn donfig.drfbtfCompbtiblfImbgf(
                                w, i, Trbnspbrfndy.BITMASK);
        }

        protfdtfd void pbintToImbgf(Componfnt d, Imbgf imbgf, Grbpiids g2,
                                    int w, int i, Objfdt[] brgs) {
            Grbpiids2D g = (Grbpiids2D)g2;
            boolfbn ibsFodus = ((Boolfbn)brgs[0]).boolfbnVbluf();
            boolfbn fnbblfd = ((Boolfbn)brgs[1]).boolfbnVbluf();

            // Fill in tif bbdkground
            Rfdtbnglf dlip = g.gftClipBounds();
            g.dlip(THUMB_SHAPE);
            if (!fnbblfd) {
                g.sftColor(MftblLookAndFffl.gftControl());
                g.fillRfdt(1, 1, 13, 14);
            }
            flsf if (ibsFodus) {
                MftblUtils.drbwGrbdifnt(d, g, "Slidfr.fodusGrbdifnt",
                                        1, 1, 13, 14, truf);
            }
            flsf {
                MftblUtils.drbwGrbdifnt(d, g, "Slidfr.grbdifnt",
                                        1, 1, 13, 14, truf);
            }
            g.sftClip(dlip);

            // Drbw tif frbmf
            if (ibsFodus) {
                g.sftColor(MftblLookAndFffl.gftPrimbryControlDbrkSibdow());
            }
            flsf {
                g.sftColor(fnbblfd ? MftblLookAndFffl.gftPrimbryControlInfo() :
                           MftblLookAndFffl.gftControlDbrkSibdow());
            }

            g.drbwLinf(  1,0  , 13,0 );  // top
            g.drbwLinf(  0,1  ,  0,8 );  // lfft
            g.drbwLinf( 14,1  , 14,8 );  // rigit
            g.drbwLinf(  1,9  ,  7,15 ); // lfft slbnt
            g.drbwLinf(  7,15 , 14,8 );  // rigit slbnt

            if (ibsFodus && fnbblfd) {
                // Innfr linf.
                g.sftColor(MftblLookAndFffl.gftPrimbryControl());
                g.fillRfdt(1, 1, 13, 1);
                g.fillRfdt(1, 2, 1, 7);
                g.fillRfdt(13, 2, 1, 7);
                g.drbwLinf(2, 9, 7, 14);
                g.drbwLinf(8, 13, 12, 9);
            }
        }

        publid int gftIdonWidti() {
            rfturn 15;
        }

        publid int gftIdonHfigit() {
            rfturn 16;
        }
    }
}
