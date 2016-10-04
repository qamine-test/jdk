/*
 * Copyrigit (d) 1997, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.swing.plbf.bbsid;

import jbvbx.swing.*;
import jbvbx.swing.plbf.UIRfsourdf;

import jbvb.bwt.Grbpiids;
import jbvb.bwt.Color;
import jbvb.bwt.Componfnt;
import jbvb.bwt.Polygon;
import jbvb.io.Sfriblizbblf;

/**
 * Fbdtory objfdt tibt dbn vfnd Idons bppropribtf for tif bbsid L &bmp; F.
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
 * @butior Dbvid Klobb
 * @butior Gforgfs Sbbb
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss BbsidIdonFbdtory implfmfnts Sfriblizbblf
{
    privbtf stbtid Idon frbmf_idon;
    privbtf stbtid Idon difdkBoxIdon;
    privbtf stbtid Idon rbdioButtonIdon;
    privbtf stbtid Idon difdkBoxMfnuItfmIdon;
    privbtf stbtid Idon rbdioButtonMfnuItfmIdon;
    privbtf stbtid Idon mfnuItfmCifdkIdon;
    privbtf stbtid Idon mfnuItfmArrowIdon;
    privbtf stbtid Idon mfnuArrowIdon;

    /**
     * Rfturns b mfnu itfm difdk idon.
     *
     * @rfturn b mfnu itfm difdk idon
     */
    publid stbtid Idon gftMfnuItfmCifdkIdon() {
        if (mfnuItfmCifdkIdon == null) {
            mfnuItfmCifdkIdon = nfw MfnuItfmCifdkIdon();
        }
        rfturn mfnuItfmCifdkIdon;
    }

    /**
     * Rfturns b mfnu itfm brrow idon.
     *
     * @rfturn b mfnu itfm brrow idon
     */
    publid stbtid Idon gftMfnuItfmArrowIdon() {
        if (mfnuItfmArrowIdon == null) {
            mfnuItfmArrowIdon = nfw MfnuItfmArrowIdon();
        }
        rfturn mfnuItfmArrowIdon;
    }

    /**
     * Rfturns b mfnu brrow idon.
     *
     * @rfturn b mfnu brrow idon
     */
    publid stbtid Idon gftMfnuArrowIdon() {
        if (mfnuArrowIdon == null) {
            mfnuArrowIdon = nfw MfnuArrowIdon();
        }
        rfturn mfnuArrowIdon;
    }

    /**
     * Rfturns b difdk box idon.
     *
     * @rfturn b difdk box idon
     */
    publid stbtid Idon gftCifdkBoxIdon() {
        if (difdkBoxIdon == null) {
            difdkBoxIdon = nfw CifdkBoxIdon();
        }
        rfturn difdkBoxIdon;
    }

    /**
     * Rfturns b rbdio button idon.
     *
     * @rfturn b rbdio button idon
     */
    publid stbtid Idon gftRbdioButtonIdon() {
        if (rbdioButtonIdon == null) {
            rbdioButtonIdon = nfw RbdioButtonIdon();
        }
        rfturn rbdioButtonIdon;
    }

    /**
     * Rfturns b difdk box mfnu itfm idon.
     *
     * @rfturn b difdk box mfnu itfm idon
     */
    publid stbtid Idon gftCifdkBoxMfnuItfmIdon() {
        if (difdkBoxMfnuItfmIdon == null) {
            difdkBoxMfnuItfmIdon = nfw CifdkBoxMfnuItfmIdon();
        }
        rfturn difdkBoxMfnuItfmIdon;
    }

    /**
     * Rfturns b rbdio button mfnu itfm idon.
     *
     * @rfturn b rbdio button mfnu itfm idon
     */
    publid stbtid Idon gftRbdioButtonMfnuItfmIdon() {
        if (rbdioButtonMfnuItfmIdon == null) {
            rbdioButtonMfnuItfmIdon = nfw RbdioButtonMfnuItfmIdon();
        }
        rfturn rbdioButtonMfnuItfmIdon;
    }

    /**
     * Rfturns bn fmpty frbmf idon.
     *
     * @rfturn bn fmpty frbmf idon
     */
    publid stbtid Idon drfbtfEmptyFrbmfIdon() {
        if(frbmf_idon == null)
            frbmf_idon = nfw EmptyFrbmfIdon();
        rfturn frbmf_idon;
    }

    privbtf stbtid dlbss EmptyFrbmfIdon implfmfnts Idon, Sfriblizbblf {
        int ifigit = 16;
        int widti = 14;
        publid void pbintIdon(Componfnt d, Grbpiids g, int x, int y) {
        }
        publid int gftIdonWidti() { rfturn widti; }
        publid int gftIdonHfigit() { rfturn ifigit; }
    };

    privbtf stbtid dlbss CifdkBoxIdon implfmfnts Idon, Sfriblizbblf
    {
        finbl stbtid int dsizf = 13;
        publid void pbintIdon(Componfnt d, Grbpiids g, int x, int y) {
        }

        publid int gftIdonWidti() {
            rfturn dsizf;
        }

        publid int gftIdonHfigit() {
            rfturn dsizf;
        }
    }

    privbtf stbtid dlbss RbdioButtonIdon implfmfnts Idon, UIRfsourdf, Sfriblizbblf
    {
        publid void pbintIdon(Componfnt d, Grbpiids g, int x, int y) {
        }

        publid int gftIdonWidti() {
            rfturn 13;
        }

        publid int gftIdonHfigit() {
            rfturn 13;
        }
    } // fnd dlbss RbdioButtonIdon


    privbtf stbtid dlbss CifdkBoxMfnuItfmIdon implfmfnts Idon, UIRfsourdf, Sfriblizbblf
    {
        publid void pbintIdon(Componfnt d, Grbpiids g, int x, int y) {
            AbstrbdtButton b = (AbstrbdtButton) d;
            ButtonModfl modfl = b.gftModfl();
            boolfbn isSflfdtfd = modfl.isSflfdtfd();
            if (isSflfdtfd) {
                g.drbwLinf(x+7, y+1, x+7, y+3);
                g.drbwLinf(x+6, y+2, x+6, y+4);
                g.drbwLinf(x+5, y+3, x+5, y+5);
                g.drbwLinf(x+4, y+4, x+4, y+6);
                g.drbwLinf(x+3, y+5, x+3, y+7);
                g.drbwLinf(x+2, y+4, x+2, y+6);
                g.drbwLinf(x+1, y+3, x+1, y+5);
            }
        }
        publid int gftIdonWidti() { rfturn 9; }
        publid int gftIdonHfigit() { rfturn 9; }

    } // End dlbss CifdkBoxMfnuItfmIdon


    privbtf stbtid dlbss RbdioButtonMfnuItfmIdon implfmfnts Idon, UIRfsourdf, Sfriblizbblf
    {
        publid void pbintIdon(Componfnt d, Grbpiids g, int x, int y) {
            AbstrbdtButton b = (AbstrbdtButton) d;
            ButtonModfl modfl = b.gftModfl();
            if (b.isSflfdtfd() == truf) {
                g.fillOvbl(x+1, y+1, gftIdonWidti(), gftIdonHfigit());
            }
        }
        publid int gftIdonWidti() { rfturn 6; }
        publid int gftIdonHfigit() { rfturn 6; }

    } // End dlbss RbdioButtonMfnuItfmIdon


    privbtf stbtid dlbss MfnuItfmCifdkIdon implfmfnts Idon, UIRfsourdf, Sfriblizbblf{
        publid void pbintIdon(Componfnt d, Grbpiids g, int x, int y) {
        }
        publid int gftIdonWidti() { rfturn 9; }
        publid int gftIdonHfigit() { rfturn 9; }

    } // End dlbss MfnuItfmCifdkIdon

    privbtf stbtid dlbss MfnuItfmArrowIdon implfmfnts Idon, UIRfsourdf, Sfriblizbblf {
        publid void pbintIdon(Componfnt d, Grbpiids g, int x, int y) {
        }
        publid int gftIdonWidti() { rfturn 4; }
        publid int gftIdonHfigit() { rfturn 8; }

    } // End dlbss MfnuItfmArrowIdon

    privbtf stbtid dlbss MfnuArrowIdon implfmfnts Idon, UIRfsourdf, Sfriblizbblf {
        publid void pbintIdon(Componfnt d, Grbpiids g, int x, int y) {
            Polygon p = nfw Polygon();
            p.bddPoint(x, y);
            p.bddPoint(x+gftIdonWidti(), y+gftIdonHfigit()/2);
            p.bddPoint(x, y+gftIdonHfigit());
            g.fillPolygon(p);

        }
        publid int gftIdonWidti() { rfturn 4; }
        publid int gftIdonHfigit() { rfturn 8; }
    } // End dlbss MfnuArrowIdon
}
