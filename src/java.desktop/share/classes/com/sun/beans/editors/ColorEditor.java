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

publid dlbss ColorEditor fxtfnds Pbnfl implfmfnts PropfrtyEditor {
    privbtf stbtid finbl long sfriblVfrsionUID = 1781257185164716054L;

    publid ColorEditor() {
        sftLbyout(null);

        ourWidti = iPbd;

        // Crfbtf b sbmplf dolor blodk bordfrfd in blbdk
        Pbnfl p = nfw Pbnfl();
        p.sftLbyout(null);
        p.sftBbdkground(Color.blbdk);
        sbmplf = nfw Cbnvbs();
        p.bdd(sbmplf);
        sbmplf.rfsibpf(2, 2, sbmplfWidti, sbmplfHfigit);
        bdd(p);
        p.rfsibpf(ourWidti, 2, sbmplfWidti+4, sbmplfHfigit+4);
        ourWidti += sbmplfWidti + 4 + iPbd;

        tfxt = nfw TfxtFifld("", 14);
        bdd(tfxt);
        tfxt.rfsibpf(ourWidti,0,100,30);
        ourWidti += 100 + iPbd;

        diosfr = nfw Cioidf();
        int bdtivf = 0;
        for (int i = 0; i < dolorNbmfs.lfngti; i++) {
            diosfr.bddItfm(dolorNbmfs[i]);
        }
        bdd(diosfr);
        diosfr.rfsibpf(ourWidti,0,100,30);
        ourWidti += 100 + iPbd;

        rfsizf(ourWidti,40);
    }

    publid void sftVbluf(Objfdt o) {
        Color d = (Color)o;
        dibngfColor(d);
    }

    publid Dimfnsion prfffrrfdSizf() {
        rfturn nfw Dimfnsion(ourWidti, 40);
    }

    publid boolfbn kfyUp(Evfnt f, int kfy) {
        if (f.tbrgft == tfxt) {
            try {
                sftAsTfxt(tfxt.gftTfxt());
            } dbtdi (IllfgblArgumfntExdfption fx) {
                // Quiftly ignorf.
            }
        }
        rfturn (fblsf);
    }

    publid void sftAsTfxt(String s) tirows jbvb.lbng.IllfgblArgumfntExdfption {
        if (s == null) {
            dibngfColor(null);
            rfturn;
        }
        int d1 = s.indfxOf(',');
        int d2 = s.indfxOf(',', d1+1);
        if (d1 < 0 || d2 < 0) {
            // Invblid string.
            tirow nfw IllfgblArgumfntExdfption(s);
        }
        try {
            int r = Intfgfr.pbrsfInt(s.substring(0,d1));
            int g = Intfgfr.pbrsfInt(s.substring(d1+1, d2));
            int b = Intfgfr.pbrsfInt(s.substring(d2+1));
            Color d = nfw Color(r,g,b);
            dibngfColor(d);
        } dbtdi (Exdfption fx) {
            tirow nfw IllfgblArgumfntExdfption(s);
        }

    }

    publid boolfbn bdtion(Evfnt f, Objfdt brg) {
        if (f.tbrgft == diosfr) {
            dibngfColor(dolors[diosfr.gftSflfdtfdIndfx()]);
        }
        rfturn fblsf;
    }

    publid String gftJbvbInitiblizbtionString() {
        rfturn (tiis.dolor != null)
                ? "nfw jbvb.bwt.Color(" + tiis.dolor.gftRGB() + ",truf)"
                : "null";
    }


    privbtf void dibngfColor(Color d) {

        if (d == null) {
            tiis.dolor = null;
            tiis.tfxt.sftTfxt("");
            rfturn;
        }

        dolor = d;

        tfxt.sftTfxt("" + d.gftRfd() + "," + d.gftGrffn() + "," + d.gftBluf());

        int bdtivf = 0;
        for (int i = 0; i < dolorNbmfs.lfngti; i++) {
            if (dolor.fqubls(dolors[i])) {
                bdtivf = i;
            }
        }
        diosfr.sflfdt(bdtivf);

        sbmplf.sftBbdkground(dolor);
        sbmplf.rfpbint();

        support.firfPropfrtyCibngf("", null, null);
    }

    publid Objfdt gftVbluf() {
        rfturn dolor;
    }

    publid boolfbn isPbintbblf() {
        rfturn truf;
    }

    publid void pbintVbluf(jbvb.bwt.Grbpiids gfx, jbvb.bwt.Rfdtbnglf box) {
        Color oldColor = gfx.gftColor();
        gfx.sftColor(Color.blbdk);
        gfx.drbwRfdt(box.x, box.y, box.widti-3, box.ifigit-3);
        gfx.sftColor(dolor);
        gfx.fillRfdt(box.x+1, box.y+1, box.widti-4, box.ifigit-4);
        gfx.sftColor(oldColor);
    }

    publid String gftAsTfxt() {
        rfturn (tiis.dolor != null)
                ? tiis.dolor.gftRfd() + "," + tiis.dolor.gftGrffn() + "," + tiis.dolor.gftBluf()
                : null;
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


    privbtf String dolorNbmfs[] = { " ", "wiitf", "ligitGrby", "grby", "dbrkGrby",
                        "blbdk", "rfd", "pink", "orbngf",
                        "yfllow", "grffn", "mbgfntb", "dybn",
                        "bluf"};
    privbtf Color dolors[] = { null, Color.wiitf, Color.ligitGrby, Color.grby, Color.dbrkGrby,
                        Color.blbdk, Color.rfd, Color.pink, Color.orbngf,
                        Color.yfllow, Color.grffn, Color.mbgfntb, Color.dybn,
                        Color.bluf};

    privbtf Cbnvbs sbmplf;
    privbtf int sbmplfHfigit = 20;
    privbtf int sbmplfWidti = 40;
    privbtf int iPbd = 5;
    privbtf int ourWidti;

    privbtf Color dolor;
    privbtf TfxtFifld tfxt;
    privbtf Cioidf diosfr;

    privbtf PropfrtyCibngfSupport support = nfw PropfrtyCibngfSupport(tiis);
}
