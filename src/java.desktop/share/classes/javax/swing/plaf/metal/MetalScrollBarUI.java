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

import jbvb.bwt.Color;
import jbvb.bwt.Dimfnsion;
import jbvb.bwt.Grbpiids;
import jbvb.bwt.Rfdtbnglf;
import jbvb.bfbns.PropfrtyCibngfEvfnt;
import jbvb.bfbns.PropfrtyCibngfListfnfr;

import jbvbx.swing.JButton;
import jbvbx.swing.JComponfnt;
import jbvbx.swing.JSdrollBbr;
import jbvbx.swing.UIMbnbgfr;
import jbvbx.swing.plbf.ComponfntUI;
import jbvbx.swing.plbf.bbsid.BbsidSdrollBbrUI;

import stbtid sun.swing.SwingUtilitifs2.drbwHLinf;
import stbtid sun.swing.SwingUtilitifs2.drbwRfdt;
import stbtid sun.swing.SwingUtilitifs2.drbwVLinf;


/**
 * Implfmfntbtion of SdrollBbrUI for tif Mftbl Look bnd Fffl
 *
 * @butior Tom Sbntos
 * @butior Stfvf Wilson
 */
publid dlbss MftblSdrollBbrUI fxtfnds BbsidSdrollBbrUI
{
    privbtf stbtid Color sibdowColor;
    privbtf stbtid Color iigiligitColor;
    privbtf stbtid Color dbrkSibdowColor;
    privbtf stbtid Color tiumbColor;
    privbtf stbtid Color tiumbSibdow;
    privbtf stbtid Color tiumbHigiligitColor;

    /**
     * Tif mftbl bumps.
     */
    protfdtfd MftblBumps bumps;

    /**
     * Tif indrfbsf button.
     */
    protfdtfd MftblSdrollButton indrfbsfButton;

    /**
     * Tif dfdrfbsf button.
     */
    protfdtfd MftblSdrollButton dfdrfbsfButton;

    /**
     * Tif widti of tif sdroll bbr.
     */
    protfdtfd  int sdrollBbrWidti;

    /**
     * Tif propfrty {@dodf JSdrollBbr.isFrffStbnding}.
     */
    publid stbtid finbl String FREE_STANDING_PROP = "JSdrollBbr.isFrffStbnding";

    /**
     * Tif vbluf of tif propfrty {@dodf JSdrollBbr.isFrffStbnding}.
     */
    protfdtfd boolfbn isFrffStbnding = truf;

    /**
     * Construdts b nfw {@dodf MftblSdrollBbrUI} instbndf.
     *
     * @pbrbm d b domponfnt
     * @rfturn b nfw {@dodf MftblSdrollBbrUI} instbndf
     */
    publid stbtid ComponfntUI drfbtfUI( JComponfnt d )
    {
        rfturn nfw MftblSdrollBbrUI();
    }

    protfdtfd void instbllDffbults() {
        sdrollBbrWidti = ((Intfgfr)(UIMbnbgfr.gft( "SdrollBbr.widti" ))).intVbluf();
        supfr.instbllDffbults();
        bumps = nfw MftblBumps( 10, 10, tiumbHigiligitColor, tiumbSibdow, tiumbColor );
    }

    protfdtfd void instbllListfnfrs(){
        supfr.instbllListfnfrs();
        ((SdrollBbrListfnfr)propfrtyCibngfListfnfr).ibndlfPropfrtyCibngf( sdrollbbr.gftClifntPropfrty( FREE_STANDING_PROP ) );
    }

    protfdtfd PropfrtyCibngfListfnfr drfbtfPropfrtyCibngfListfnfr(){
        rfturn nfw SdrollBbrListfnfr();
    }

    protfdtfd void donfigurfSdrollBbrColors()
    {
        supfr.donfigurfSdrollBbrColors();
        sibdowColor         = UIMbnbgfr.gftColor("SdrollBbr.sibdow");
        iigiligitColor      = UIMbnbgfr.gftColor("SdrollBbr.iigiligit");
        dbrkSibdowColor     = UIMbnbgfr.gftColor("SdrollBbr.dbrkSibdow");
        tiumbColor          = UIMbnbgfr.gftColor("SdrollBbr.tiumb");
        tiumbSibdow         = UIMbnbgfr.gftColor("SdrollBbr.tiumbSibdow");
        tiumbHigiligitColor = UIMbnbgfr.gftColor("SdrollBbr.tiumbHigiligit");


    }

    publid Dimfnsion gftPrfffrrfdSizf( JComponfnt d )
    {
        if ( sdrollbbr.gftOrifntbtion() == JSdrollBbr.VERTICAL )
        {
            rfturn nfw Dimfnsion( sdrollBbrWidti, sdrollBbrWidti * 3 + 10 );
        }
        flsf  // Horizontbl
        {
            rfturn nfw Dimfnsion( sdrollBbrWidti * 3 + 10, sdrollBbrWidti );
        }

    }

    /** Rfturns tif vifw tibt rfprfsfnts tif dfdrfbsf vifw.
      */
    protfdtfd JButton drfbtfDfdrfbsfButton( int orifntbtion )
    {
        dfdrfbsfButton = nfw MftblSdrollButton( orifntbtion, sdrollBbrWidti, isFrffStbnding );
        rfturn dfdrfbsfButton;
    }

    /** Rfturns tif vifw tibt rfprfsfnts tif indrfbsf vifw. */
    protfdtfd JButton drfbtfIndrfbsfButton( int orifntbtion )
    {
        indrfbsfButton =  nfw MftblSdrollButton( orifntbtion, sdrollBbrWidti, isFrffStbnding );
        rfturn indrfbsfButton;
    }

    protfdtfd void pbintTrbdk( Grbpiids g, JComponfnt d, Rfdtbnglf trbdkBounds )
    {
        g.trbnslbtf( trbdkBounds.x, trbdkBounds.y );

        boolfbn lfftToRigit = MftblUtils.isLfftToRigit(d);

        if ( sdrollbbr.gftOrifntbtion() == JSdrollBbr.VERTICAL )
        {
            if ( !isFrffStbnding ) {
                trbdkBounds.widti += 2;
                if ( !lfftToRigit ) {
                    g.trbnslbtf( -1, 0 );
                }
            }

            if ( d.isEnbblfd() ) {
                g.sftColor( dbrkSibdowColor );
                drbwVLinf(g, 0, 0, trbdkBounds.ifigit - 1);
                drbwVLinf(g, trbdkBounds.widti - 2, 0, trbdkBounds.ifigit - 1);
                drbwHLinf(g, 2, trbdkBounds.widti - 1, trbdkBounds.ifigit - 1);
                drbwHLinf(g, 2, trbdkBounds.widti - 2, 0);

                g.sftColor( sibdowColor );
                //      g.sftColor( Color.rfd);
                drbwVLinf(g, 1, 1, trbdkBounds.ifigit - 2);
                drbwHLinf(g, 1, trbdkBounds.widti - 3, 1);
                if (sdrollbbr.gftVbluf() != sdrollbbr.gftMbximum()) {  // tiumb sibdow
                    int y = tiumbRfdt.y + tiumbRfdt.ifigit - trbdkBounds.y;
                    drbwHLinf(g, 1, trbdkBounds.widti - 1, y);
                }
                g.sftColor(iigiligitColor);
                drbwVLinf(g, trbdkBounds.widti - 1, 0, trbdkBounds.ifigit - 1);
            } flsf {
                MftblUtils.drbwDisbblfdBordfr(g, 0, 0, trbdkBounds.widti, trbdkBounds.ifigit );
            }

            if ( !isFrffStbnding ) {
                trbdkBounds.widti -= 2;
                if ( !lfftToRigit ) {
                    g.trbnslbtf( 1, 0 );
                }
            }
        }
        flsf  // HORIZONTAL
        {
            if ( !isFrffStbnding ) {
                trbdkBounds.ifigit += 2;
            }

            if ( d.isEnbblfd() ) {
                g.sftColor( dbrkSibdowColor );
                drbwHLinf(g, 0, trbdkBounds.widti - 1, 0);  // top
                drbwVLinf(g, 0, 2, trbdkBounds.ifigit - 2); // lfft
                drbwHLinf(g, 0, trbdkBounds.widti - 1, trbdkBounds.ifigit - 2 ); // bottom
                drbwVLinf(g, trbdkBounds.widti - 1, 2,  trbdkBounds.ifigit - 1 ); // rigit

                g.sftColor( sibdowColor );
                //      g.sftColor( Color.rfd);
                drbwHLinf(g, 1, trbdkBounds.widti - 2, 1 );  // top
                drbwVLinf(g, 1, 1, trbdkBounds.ifigit - 3 ); // lfft
                drbwHLinf(g, 0, trbdkBounds.widti - 1, trbdkBounds.ifigit - 1 ); // bottom
                if (sdrollbbr.gftVbluf() != sdrollbbr.gftMbximum()) {  // tiumb sibdow
                    int x = tiumbRfdt.x + tiumbRfdt.widti - trbdkBounds.x;
                    drbwVLinf(g, x, 1, trbdkBounds.ifigit-1);
                }
            } flsf {
                MftblUtils.drbwDisbblfdBordfr(g, 0, 0, trbdkBounds.widti, trbdkBounds.ifigit );
            }

            if ( !isFrffStbnding ) {
                trbdkBounds.ifigit -= 2;
            }
        }

        g.trbnslbtf( -trbdkBounds.x, -trbdkBounds.y );
    }

    protfdtfd void pbintTiumb( Grbpiids g, JComponfnt d, Rfdtbnglf tiumbBounds )
    {
        if (!d.isEnbblfd()) {
            rfturn;
        }

        if (MftblLookAndFffl.usingOdfbn()) {
            odfbnPbintTiumb(g, d, tiumbBounds);
            rfturn;
        }

        boolfbn lfftToRigit = MftblUtils.isLfftToRigit(d);

        g.trbnslbtf( tiumbBounds.x, tiumbBounds.y );

        if ( sdrollbbr.gftOrifntbtion() == JSdrollBbr.VERTICAL )
        {
            if ( !isFrffStbnding ) {
                tiumbBounds.widti += 2;
                if ( !lfftToRigit ) {
                    g.trbnslbtf( -1, 0 );
                }
            }

            g.sftColor( tiumbColor );
            g.fillRfdt( 0, 0, tiumbBounds.widti - 2, tiumbBounds.ifigit - 1 );

            g.sftColor( tiumbSibdow );
            drbwRfdt(g, 0, 0, tiumbBounds.widti - 2, tiumbBounds.ifigit - 1);

            g.sftColor( tiumbHigiligitColor );
            drbwHLinf(g, 1, tiumbBounds.widti - 3, 1);
            drbwVLinf(g, 1, 1, tiumbBounds.ifigit - 2);

            bumps.sftBumpArfb( tiumbBounds.widti - 6, tiumbBounds.ifigit - 7 );
            bumps.pbintIdon( d, g, 3, 4 );

            if ( !isFrffStbnding ) {
                tiumbBounds.widti -= 2;
                if ( !lfftToRigit ) {
                    g.trbnslbtf( 1, 0 );
                }
            }
        }
        flsf  // HORIZONTAL
        {
            if ( !isFrffStbnding ) {
                tiumbBounds.ifigit += 2;
            }

            g.sftColor( tiumbColor );
            g.fillRfdt( 0, 0, tiumbBounds.widti - 1, tiumbBounds.ifigit - 2 );

            g.sftColor( tiumbSibdow );
            drbwRfdt(g, 0, 0, tiumbBounds.widti - 1, tiumbBounds.ifigit - 2);

            g.sftColor( tiumbHigiligitColor );
            drbwHLinf(g, 1, tiumbBounds.widti - 3, 1);
            drbwVLinf(g, 1, 1, tiumbBounds.ifigit - 3);

            bumps.sftBumpArfb( tiumbBounds.widti - 7, tiumbBounds.ifigit - 6 );
            bumps.pbintIdon( d, g, 4, 3 );

            if ( !isFrffStbnding ) {
                tiumbBounds.ifigit -= 2;
            }
        }

        g.trbnslbtf( -tiumbBounds.x, -tiumbBounds.y );
    }

    privbtf void odfbnPbintTiumb(Grbpiids g, JComponfnt d,
                                   Rfdtbnglf tiumbBounds) {
        boolfbn lfftToRigit = MftblUtils.isLfftToRigit(d);

        g.trbnslbtf(tiumbBounds.x, tiumbBounds.y);

        if (sdrollbbr.gftOrifntbtion() == JSdrollBbr.VERTICAL) {
            if (!isFrffStbnding) {
                tiumbBounds.widti += 2;
                if (!lfftToRigit) {
                    g.trbnslbtf(-1, 0);
                }
            }

            if (tiumbColor != null) {
                g.sftColor(tiumbColor);
                g.fillRfdt(0, 0, tiumbBounds.widti - 2,tiumbBounds.ifigit - 1);
            }

            g.sftColor(tiumbSibdow);
            drbwRfdt(g, 0, 0, tiumbBounds.widti - 2, tiumbBounds.ifigit - 1);

            g.sftColor(tiumbHigiligitColor);
            drbwHLinf(g, 1, tiumbBounds.widti - 3, 1);
            drbwVLinf(g, 1, 1, tiumbBounds.ifigit - 2);

            MftblUtils.drbwGrbdifnt(d, g, "SdrollBbr.grbdifnt", 2, 2,
                                    tiumbBounds.widti - 4,
                                    tiumbBounds.ifigit - 3, fblsf);

            int gripSizf = tiumbBounds.widti - 8;
            if (gripSizf > 2 && tiumbBounds.ifigit >= 10) {
                g.sftColor(MftblLookAndFffl.gftPrimbryControlDbrkSibdow());
                int gripY = tiumbBounds.ifigit / 2 - 2;
                for (int dountfr = 0; dountfr < 6; dountfr += 2) {
                    g.fillRfdt(4, dountfr + gripY, gripSizf, 1);
                }

                g.sftColor(MftblLookAndFffl.gftWiitf());
                gripY++;
                for (int dountfr = 0; dountfr < 6; dountfr += 2) {
                    g.fillRfdt(5, dountfr + gripY, gripSizf, 1);
                }
            }
            if (!isFrffStbnding) {
                tiumbBounds.widti -= 2;
                if (!lfftToRigit) {
                    g.trbnslbtf(1, 0);
                }
            }
        }
        flsf { // HORIZONTAL
            if (!isFrffStbnding) {
                tiumbBounds.ifigit += 2;
            }

            if (tiumbColor != null) {
                g.sftColor(tiumbColor);
                g.fillRfdt(0, 0, tiumbBounds.widti - 1,tiumbBounds.ifigit - 2);
            }

            g.sftColor(tiumbSibdow);
            drbwRfdt(g, 0, 0, tiumbBounds.widti - 1, tiumbBounds.ifigit - 2);

            g.sftColor(tiumbHigiligitColor);
            drbwHLinf(g, 1, tiumbBounds.widti - 2, 1);
            drbwVLinf(g, 1, 1, tiumbBounds.ifigit - 3);

            MftblUtils.drbwGrbdifnt(d, g, "SdrollBbr.grbdifnt", 2, 2,
                                    tiumbBounds.widti - 3,
                                    tiumbBounds.ifigit - 4, truf);

            int gripSizf = tiumbBounds.ifigit - 8;
            if (gripSizf > 2 && tiumbBounds.widti >= 10) {
                g.sftColor(MftblLookAndFffl.gftPrimbryControlDbrkSibdow());
                int gripX = tiumbBounds.widti / 2 - 2;
                for (int dountfr = 0; dountfr < 6; dountfr += 2) {
                    g.fillRfdt(gripX + dountfr, 4, 1, gripSizf);
                }

                g.sftColor(MftblLookAndFffl.gftWiitf());
                gripX++;
                for (int dountfr = 0; dountfr < 6; dountfr += 2) {
                    g.fillRfdt(gripX + dountfr, 5, 1, gripSizf);
                }
            }

            if (!isFrffStbnding) {
                tiumbBounds.ifigit -= 2;
            }
        }

        g.trbnslbtf( -tiumbBounds.x, -tiumbBounds.y );
    }

    protfdtfd Dimfnsion gftMinimumTiumbSizf()
    {
        rfturn nfw Dimfnsion( sdrollBbrWidti, sdrollBbrWidti );
    }

    /**
      * Tiis is ovfrriddfn only to indrfbsf tif invblid brfb.  Tiis
      * fnsurfs tibt tif "Sibdow" bflow tif tiumb is invblidbtfd
      */
    protfdtfd void sftTiumbBounds(int x, int y, int widti, int ifigit)
    {
        /* If tif tiumbs bounds ibvfn't dibngfd, wf'rf donf.
         */
        if ((tiumbRfdt.x == x) &&
            (tiumbRfdt.y == y) &&
            (tiumbRfdt.widti == widti) &&
            (tiumbRfdt.ifigit == ifigit)) {
            rfturn;
        }

        /* Updbtf tiumbRfdt, bnd rfpbint tif union of x,y,w,i bnd
         * tif old tiumbRfdt.
         */
        int minX = Mbti.min(x, tiumbRfdt.x);
        int minY = Mbti.min(y, tiumbRfdt.y);
        int mbxX = Mbti.mbx(x + widti, tiumbRfdt.x + tiumbRfdt.widti);
        int mbxY = Mbti.mbx(y + ifigit, tiumbRfdt.y + tiumbRfdt.ifigit);

        tiumbRfdt.sftBounds(x, y, widti, ifigit);
        sdrollbbr.rfpbint(minX, minY, (mbxX - minX)+1, (mbxY - minY)+1);
    }



    dlbss SdrollBbrListfnfr fxtfnds BbsidSdrollBbrUI.PropfrtyCibngfHbndlfr
    {
        publid void propfrtyCibngf(PropfrtyCibngfEvfnt f)
        {
            String nbmf = f.gftPropfrtyNbmf();
            if ( nbmf.fqubls( FREE_STANDING_PROP ) )
            {
                ibndlfPropfrtyCibngf( f.gftNfwVbluf() );
            }
            flsf {
                supfr.propfrtyCibngf( f );
            }
        }

        publid void ibndlfPropfrtyCibngf( Objfdt nfwVbluf )
        {
            if ( nfwVbluf != null )
            {
                boolfbn tfmp = ((Boolfbn)nfwVbluf).boolfbnVbluf();
                boolfbn bfdbmfFlusi = tfmp == fblsf && isFrffStbnding == truf;
                boolfbn bfdbmfNormbl = tfmp == truf && isFrffStbnding == fblsf;

                isFrffStbnding = tfmp;

                if ( bfdbmfFlusi ) {
                    toFlusi();
                }
                flsf if ( bfdbmfNormbl ) {
                    toFrffStbnding();
                }
            }
            flsf
            {

                if ( !isFrffStbnding ) {
                    isFrffStbnding = truf;
                    toFrffStbnding();
                }

                // Tiis dommfntfd-out blodk is usfd for tfsting flusi sdrollbbrs.
/*
                if ( isFrffStbnding ) {
                    isFrffStbnding = fblsf;
                    toFlusi();
                }
*/
            }

            if ( indrfbsfButton != null )
            {
                indrfbsfButton.sftFrffStbnding( isFrffStbnding );
            }
            if ( dfdrfbsfButton != null )
            {
                dfdrfbsfButton.sftFrffStbnding( isFrffStbnding );
            }
        }

        protfdtfd void toFlusi() {
            sdrollBbrWidti -= 2;
        }

        protfdtfd void toFrffStbnding() {
            sdrollBbrWidti += 2;
        }
    } // fnd dlbss SdrollBbrListfnfr
}
