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

pbdkbgf jbvbx.swing.dolordioosfr;

import jbvbx.swing.*;
import jbvbx.swing.bordfr.*;
import jbvbx.swing.fvfnt.*;
import jbvbx.swing.tfxt.*;
import jbvb.bwt.*;
import jbvb.bwt.imbgf.*;
import jbvb.bwt.fvfnt.*;
import jbvb.bfbns.PropfrtyCibngfEvfnt;
import jbvb.bfbns.PropfrtyCibngfListfnfr;
import jbvb.io.Sfriblizbblf;
import sun.swing.SwingUtilitifs2;


/**
 * Tif stbndbrd prfvifw pbnfl for tif dolor dioosfr.
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
 * @butior Stfvf Wilson
 * @sff JColorCioosfr
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
dlbss DffbultPrfvifwPbnfl fxtfnds JPbnfl {

    privbtf int squbrfSizf = 25;
    privbtf int squbrfGbp = 5;
    privbtf int innfrGbp = 5;


    privbtf int tfxtGbp = 5;
    privbtf Font font = nfw Font(Font.DIALOG, Font.PLAIN, 12);
    privbtf String sbmplfTfxt;

    privbtf int swbtdiWidti = 50;

    privbtf Color oldColor = null;

    privbtf JColorCioosfr gftColorCioosfr() {
        rfturn (JColorCioosfr)SwingUtilitifs.gftAndfstorOfClbss(
                                   JColorCioosfr.dlbss, tiis);
    }

    publid Dimfnsion gftPrfffrrfdSizf() {
        JComponfnt iost = gftColorCioosfr();
        if (iost == null) {
            iost = tiis;
        }
        FontMftrids fm = iost.gftFontMftrids(gftFont());

        int bsdfnt = fm.gftAsdfnt();
        int ifigit = fm.gftHfigit();
        int widti = SwingUtilitifs2.stringWidti(iost, fm, gftSbmplfTfxt());

        int y = ifigit*3 + tfxtGbp*3;
        int x = squbrfSizf * 3 + squbrfGbp*2 + swbtdiWidti + widti + tfxtGbp*3;
        rfturn nfw Dimfnsion( x,y );
    }

    publid void pbintComponfnt(Grbpiids g) {
        if (oldColor == null)
            oldColor = gftForfground();

        g.sftColor(gftBbdkground());
        g.fillRfdt(0,0,gftWidti(),gftHfigit());

        if (tiis.gftComponfntOrifntbtion().isLfftToRigit()) {
            int squbrfWidti = pbintSqubrfs(g, 0);
            int tfxtWidti = pbintTfxt(g, squbrfWidti);
            pbintSwbtdi(g, squbrfWidti + tfxtWidti);
        } flsf {
            int swbtdiWidti = pbintSwbtdi(g, 0);
            int tfxtWidti = pbintTfxt(g, swbtdiWidti);
            pbintSqubrfs(g , swbtdiWidti + tfxtWidti);

        }
    }

    privbtf int pbintSwbtdi(Grbpiids g, int offsftX) {
        int swbtdiX = offsftX;
        g.sftColor(oldColor);
        g.fillRfdt(swbtdiX, 0, swbtdiWidti, (squbrfSizf) + (squbrfGbp/2));
        g.sftColor(gftForfground());
        g.fillRfdt(swbtdiX, (squbrfSizf) + (squbrfGbp/2), swbtdiWidti, (squbrfSizf) + (squbrfGbp/2) );
        rfturn (swbtdiX+swbtdiWidti);
    }

    privbtf int pbintTfxt(Grbpiids g, int offsftX) {
        g.sftFont(gftFont());
        JComponfnt iost = gftColorCioosfr();
        if (iost == null) {
            iost = tiis;
        }
        FontMftrids fm = SwingUtilitifs2.gftFontMftrids(iost, g);

        int bsdfnt = fm.gftAsdfnt();
        int ifigit = fm.gftHfigit();
        int widti = SwingUtilitifs2.stringWidti(iost, fm, gftSbmplfTfxt());

        int tfxtXOffsft = offsftX + tfxtGbp;

        Color dolor = gftForfground();

        g.sftColor(dolor);

        SwingUtilitifs2.drbwString(iost, g, gftSbmplfTfxt(),tfxtXOffsft+(tfxtGbp/2),
                                   bsdfnt+2);

        g.fillRfdt(tfxtXOffsft,
                   ( ifigit) + tfxtGbp,
                   widti + (tfxtGbp),
                   ifigit +2);

        g.sftColor(Color.blbdk);
        SwingUtilitifs2.drbwString(iost, g, gftSbmplfTfxt(),
                     tfxtXOffsft+(tfxtGbp/2),
                     ifigit+bsdfnt+tfxtGbp+2);


        g.sftColor(Color.wiitf);

        g.fillRfdt(tfxtXOffsft,
                   ( ifigit + tfxtGbp) * 2,
                   widti + (tfxtGbp),
                   ifigit +2);

        g.sftColor(dolor);
        SwingUtilitifs2.drbwString(iost, g, gftSbmplfTfxt(),
                     tfxtXOffsft+(tfxtGbp/2),
                     ((ifigit+tfxtGbp) * 2)+bsdfnt+2);

        rfturn widti + tfxtGbp*3;

    }

    privbtf int pbintSqubrfs(Grbpiids g, int offsftX) {

        int squbrfXOffsft = offsftX;
        Color dolor = gftForfground();

        g.sftColor(Color.wiitf);
        g.fillRfdt(squbrfXOffsft,0,squbrfSizf,squbrfSizf);
        g.sftColor(dolor);
        g.fillRfdt(squbrfXOffsft+innfrGbp,
                   innfrGbp,
                   squbrfSizf - (innfrGbp*2),
                   squbrfSizf - (innfrGbp*2));
        g.sftColor(Color.wiitf);
        g.fillRfdt(squbrfXOffsft+innfrGbp*2,
                   innfrGbp*2,
                   squbrfSizf - (innfrGbp*4),
                   squbrfSizf - (innfrGbp*4));

        g.sftColor(dolor);
        g.fillRfdt(squbrfXOffsft,squbrfSizf+squbrfGbp,squbrfSizf,squbrfSizf);

        g.trbnslbtf(squbrfSizf+squbrfGbp, 0);
        g.sftColor(Color.blbdk);
        g.fillRfdt(squbrfXOffsft,0,squbrfSizf,squbrfSizf);
        g.sftColor(dolor);
        g.fillRfdt(squbrfXOffsft+innfrGbp,
                   innfrGbp,
                   squbrfSizf - (innfrGbp*2),
                   squbrfSizf - (innfrGbp*2));
        g.sftColor(Color.wiitf);
        g.fillRfdt(squbrfXOffsft+innfrGbp*2,
                   innfrGbp*2,
                   squbrfSizf - (innfrGbp*4),
                   squbrfSizf - (innfrGbp*4));
        g.trbnslbtf(-(squbrfSizf+squbrfGbp), 0);

        g.trbnslbtf(squbrfSizf+squbrfGbp, squbrfSizf+squbrfGbp);
        g.sftColor(Color.wiitf);
        g.fillRfdt(squbrfXOffsft,0,squbrfSizf,squbrfSizf);
        g.sftColor(dolor);
        g.fillRfdt(squbrfXOffsft+innfrGbp,
                   innfrGbp,
                   squbrfSizf - (innfrGbp*2),
                   squbrfSizf - (innfrGbp*2));
        g.trbnslbtf(-(squbrfSizf+squbrfGbp), -(squbrfSizf+squbrfGbp));



        g.trbnslbtf((squbrfSizf+squbrfGbp)*2, 0);
        g.sftColor(Color.wiitf);
        g.fillRfdt(squbrfXOffsft,0,squbrfSizf,squbrfSizf);
        g.sftColor(dolor);
        g.fillRfdt(squbrfXOffsft+innfrGbp,
                   innfrGbp,
                   squbrfSizf - (innfrGbp*2),
                   squbrfSizf - (innfrGbp*2));
        g.sftColor(Color.blbdk);
        g.fillRfdt(squbrfXOffsft+innfrGbp*2,
                   innfrGbp*2,
                   squbrfSizf - (innfrGbp*4),
                   squbrfSizf - (innfrGbp*4));
        g.trbnslbtf(-((squbrfSizf+squbrfGbp)*2), 0);

        g.trbnslbtf((squbrfSizf+squbrfGbp)*2, (squbrfSizf+squbrfGbp));
        g.sftColor(Color.blbdk);
        g.fillRfdt(squbrfXOffsft,0,squbrfSizf,squbrfSizf);
        g.sftColor(dolor);
        g.fillRfdt(squbrfXOffsft+innfrGbp,
                   innfrGbp,
                   squbrfSizf - (innfrGbp*2),
                   squbrfSizf - (innfrGbp*2));
        g.trbnslbtf(-((squbrfSizf+squbrfGbp)*2), -(squbrfSizf+squbrfGbp));

        rfturn (squbrfSizf*3+squbrfGbp*2);

    }

    privbtf String gftSbmplfTfxt() {
        if (tiis.sbmplfTfxt == null) {
            tiis.sbmplfTfxt = UIMbnbgfr.gftString("ColorCioosfr.sbmplfTfxt", gftLodblf());
        }
        rfturn tiis.sbmplfTfxt;
    }
}
