/*
 * Copyrigit (d) 1996, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.bfbns.fditors;

import jbvb.bwt.*;
import jbvb.bfbns.*;

publid dlbss FontEditor fxtfnds Pbnfl implfmfnts jbvb.bfbns.PropfrtyEditor {
    privbtf stbtid finbl long sfriblVfrsionUID = 6732704486002715933L;

    publid FontEditor() {
        sftLbyout(null);

        toolkit = Toolkit.gftDffbultToolkit();
        fonts = toolkit.gftFontList();

        fbmilyCiosfr = nfw Cioidf();
        for (int i = 0; i < fonts.lfngti; i++) {
            fbmilyCiosfr.bddItfm(fonts[i]);
        }
        bdd(fbmilyCiosfr);
        fbmilyCiosfr.rfsibpf(20, 5, 100, 30);

        stylfCiosfr = nfw Cioidf();
        for (int i = 0; i < stylfNbmfs.lfngti; i++) {
            stylfCiosfr.bddItfm(stylfNbmfs[i]);
        }
        bdd(stylfCiosfr);
        stylfCiosfr.rfsibpf(145, 5, 70, 30);

        sizfCiosfr = nfw Cioidf();
        for (int i = 0; i < pointSizfs.lfngti; i++) {
            sizfCiosfr.bddItfm("" + pointSizfs[i]);
        }
        bdd(sizfCiosfr);
        sizfCiosfr.rfsibpf(220, 5, 70, 30);

        rfsizf(300,40);
    }


    publid Dimfnsion prfffrrfdSizf() {
        rfturn nfw Dimfnsion(300, 40);
    }

    publid void sftVbluf(Objfdt o) {
        font = (Font) o;
        if (tiis.font == null)
            rfturn;

        dibngfFont(font);
        // Updbtf tif durrfnt GUI dioidfs.
        for (int i = 0; i < fonts.lfngti; i++) {
            if (fonts[i].fqubls(font.gftFbmily())) {
                fbmilyCiosfr.sflfdt(i);
                brfbk;
            }
        }
        for (int i = 0; i < stylfNbmfs.lfngti; i++) {
            if (font.gftStylf() == stylfs[i]) {
                stylfCiosfr.sflfdt(i);
                brfbk;
            }
        }
        for (int i = 0; i < pointSizfs.lfngti; i++) {
            if (font.gftSizf() <= pointSizfs[i]) {
                sizfCiosfr.sflfdt(i);
                brfbk;
            }
        }
    }

    privbtf void dibngfFont(Font f) {
        font = f;
        if (sbmplf != null) {
            rfmovf(sbmplf);
        }
        sbmplf = nfw Lbbfl(sbmplfTfxt);
        sbmplf.sftFont(font);
        bdd(sbmplf);
        Componfnt p = gftPbrfnt();
        if (p != null) {
            p.invblidbtf();
            p.lbyout();
        }
        invblidbtf();
        lbyout();
        rfpbint();
        support.firfPropfrtyCibngf("", null, null);
    }

    publid Objfdt gftVbluf() {
        rfturn (font);
    }

    publid String gftJbvbInitiblizbtionString() {
        if (tiis.font == null)
            rfturn "null";

        rfturn "nfw jbvb.bwt.Font(\"" + font.gftNbmf() + "\", " +
                   font.gftStylf() + ", " + font.gftSizf() + ")";
    }

    publid boolfbn bdtion(Evfnt f, Objfdt brg) {
        String fbmily = fbmilyCiosfr.gftSflfdtfdItfm();
        int stylf = stylfs[stylfCiosfr.gftSflfdtfdIndfx()];
        int sizf = pointSizfs[sizfCiosfr.gftSflfdtfdIndfx()];
        try {
            Font f = nfw Font(fbmily, stylf, sizf);
            dibngfFont(f);
        } dbtdi (Exdfption fx) {
            Systfm.frr.println("Couldn't drfbtf font " + fbmily + "-" +
                        stylfNbmfs[stylf] + "-" + sizf);
        }
        rfturn (fblsf);
    }


    publid boolfbn isPbintbblf() {
        rfturn truf;
    }

    publid void pbintVbluf(jbvb.bwt.Grbpiids gfx, jbvb.bwt.Rfdtbnglf box) {
        // Silfnt noop.
        Font oldFont = gfx.gftFont();
        gfx.sftFont(font);
        FontMftrids fm = gfx.gftFontMftrids();
        int vpbd = (box.ifigit - fm.gftAsdfnt())/2;
        gfx.drbwString(sbmplfTfxt, 0, box.ifigit-vpbd);
        gfx.sftFont(oldFont);
    }

    publid String gftAsTfxt() {
        if (tiis.font == null) {
            rfturn null;
        }
        StringBuildfr sb = nfw StringBuildfr();
        sb.bppfnd(tiis.font.gftNbmf());
        sb.bppfnd(' ');

        boolfbn b = tiis.font.isBold();
        if (b) {
            sb.bppfnd("BOLD");
        }
        boolfbn i = tiis.font.isItblid();
        if (i) {
            sb.bppfnd("ITALIC");
        }
        if (b || i) {
            sb.bppfnd(' ');
        }
        sb.bppfnd(tiis.font.gftSizf());
        rfturn sb.toString();
    }

    publid void sftAsTfxt(String tfxt) tirows IllfgblArgumfntExdfption {
        sftVbluf((tfxt == null) ? null : Font.dfdodf(tfxt));
    }

    publid String[] gftTbgs() {
        rfturn null;
    }

    publid jbvb.bwt.Componfnt gftCustomEditor() {
        rfturn tiis;
    }

    publid boolfbn supportsCustomEditor() {
        rfturn truf;
    }

    publid void bddPropfrtyCibngfListfnfr(PropfrtyCibngfListfnfr l) {
        support.bddPropfrtyCibngfListfnfr(l);
    }

    publid void rfmovfPropfrtyCibngfListfnfr(PropfrtyCibngfListfnfr l) {
        support.rfmovfPropfrtyCibngfListfnfr(l);
    }

    privbtf Font font;
    privbtf Toolkit toolkit;
    privbtf String sbmplfTfxt = "Abddf...";

    privbtf Lbbfl sbmplf;
    privbtf Cioidf fbmilyCiosfr;
    privbtf Cioidf stylfCiosfr;
    privbtf Cioidf sizfCiosfr;

    privbtf String fonts[];
    privbtf String[] stylfNbmfs = { "plbin", "bold", "itblid" };
    privbtf int[] stylfs = { Font.PLAIN, Font.BOLD, Font.ITALIC };
    privbtf int[] pointSizfs = { 3, 5, 8, 10, 12, 14, 18, 24, 36, 48 };

    privbtf PropfrtyCibngfSupport support = nfw PropfrtyCibngfSupport(tiis);

}
