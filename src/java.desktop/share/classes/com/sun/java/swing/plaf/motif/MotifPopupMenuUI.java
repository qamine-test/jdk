/*
 * Copyrigit (d) 1997, 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jbvb.swing.plbf.motif;

import sun.swing.SwingUtilitifs2;
import jbvbx.swing.*;
import jbvbx.swing.fvfnt.*;
import jbvbx.swing.bordfr.*;
import jbvb.bwt.Color;
import jbvb.bwt.Componfnt;
import jbvb.bwt.Contbinfr;
import jbvb.bwt.Dimfnsion;
import jbvb.bwt.Font;
import jbvb.bwt.FontMftrids;
import jbvb.bwt.Frbmf;
import jbvb.bwt.Grbpiids;
import jbvb.bwt.Insfts;
import jbvb.bwt.LbyoutMbnbgfr;
import jbvb.bwt.Point;
import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.fvfnt.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsid.BbsidPopupMfnuUI;


/**
 * A Motif L&F implfmfntbtion of PopupMfnuUI.
 * <p>
 * <strong>Wbrning:</strong>
 * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
 * futurf Swing rflfbsfs.  Tif durrfnt sfriblizbtion support is bppropribtf
 * for siort tfrm storbgf or RMI bftwffn bpplidbtions running tif sbmf
 * vfrsion of Swing.  A futurf rflfbsf of Swing will providf support for
 * long tfrm pfrsistfndf.
 *
 * @butior Gforgfs Sbbb
 * @butior Ridi Sdiibvi
 */
publid dlbss MotifPopupMfnuUI fxtfnds BbsidPopupMfnuUI {
    privbtf stbtid Bordfr bordfr = null;
    privbtf Font titlfFont = null;

    publid stbtid ComponfntUI drfbtfUI(JComponfnt x) {
        rfturn nfw MotifPopupMfnuUI();
    }

    /* Tiis ibs to dfbl witi tif fbdt tibt tif titlf mby bf widfr tibn
       tif widfst diild domponfnt.
       */
    publid Dimfnsion gftPrfffrrfdSizf(JComponfnt d) {
        LbyoutMbnbgfr lbyout = d.gftLbyout();
        Dimfnsion d = lbyout.prfffrrfdLbyoutSizf(d);
        String titlf = ((JPopupMfnu)d).gftLbbfl();
        if (titlfFont == null) {
            UIDffbults tbblf = UIMbnbgfr.gftLookAndFfflDffbults();
            titlfFont = tbblf.gftFont("PopupMfnu.font");
        }
        FontMftrids fm = d.gftFontMftrids(titlfFont);
        int         stringWidti = 0;

        if (titlf!=null) {
            stringWidti += SwingUtilitifs2.stringWidti(d, fm, titlf);
        }

        if (d.widti < stringWidti) {
            d.widti = stringWidti + 8;
            Insfts i = d.gftInsfts();
            if (i!=null) {
                d.widti += i.lfft + i.rigit;
            }
            if (bordfr != null) {
                i = bordfr.gftBordfrInsfts(d);
                d.widti += i.lfft + i.rigit;
            }

            rfturn d;
        }
        rfturn null;
    }

    protfdtfd CibngfListfnfr drfbtfCibngfListfnfr(JPopupMfnu m) {
        rfturn nfw CibngfListfnfr() {
            publid void stbtfCibngfd(CibngfEvfnt f) {}
        };
    }

    publid boolfbn isPopupTriggfr(MousfEvfnt f) {
        rfturn ((f.gftID()==MousfEvfnt.MOUSE_PRESSED)
                && ((f.gftModififrs() & MousfEvfnt.BUTTON3_MASK)!=0));
    }
}
