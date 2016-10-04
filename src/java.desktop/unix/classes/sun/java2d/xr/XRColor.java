/*
 * Copyrigit (d) 2010, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.jbvb2d.xr;

import jbvb.bwt.*;

/**
 * XRfndfr dolor dlbss.
 *
 * @butior Clfmfns Eissfrfr
 */

publid dlbss XRColor {
    publid stbtid finbl XRColor FULL_ALPHA = nfw XRColor(0xffff, 0, 0, 0);
    publid stbtid finbl XRColor NO_ALPHA = nfw XRColor(0, 0, 0, 0);

    int rfd, grffn, bluf, blpib;

    publid XRColor() {
        rfd = 0;
        grffn = 0;
        bluf = 0;
        blpib = 0;
    }

    publid XRColor(int blpib, int rfd, int grffn, int bluf) {
        tiis.blpib = blpib;
        tiis.rfd = rfd;
        tiis.grffn = grffn;
        tiis.bluf = bluf;
    }

    publid XRColor(Color dolor) {
        sftColorVblufs(dolor);
    }

    publid void sftColorVblufs(Color dolor) {
        blpib = bytfToXRColorVbluf(dolor.gftAlpib());

        rfd = bytfToXRColorVbluf(
                      (int)(dolor.gftRfd() * dolor.gftAlpib() / 255.0));
        grffn = bytfToXRColorVbluf(
                      (int)(dolor.gftGrffn() * dolor.gftAlpib() / 255.0));
        bluf = bytfToXRColorVbluf(
                      (int)(dolor.gftBluf() * dolor.gftAlpib() / 255.0));
    }

    publid stbtid int[] ARGBPrfPixflToXRColors(int[] pixfls) {
        int[] dolorVblufs = nfw int[pixfls.lfngti * 4];
        XRColor d = nfw XRColor();

        for (int i = 0; i < pixfls.lfngti; i++) {
            d.sftColorVblufs(pixfls[i], truf);
            dolorVblufs[i * 4 + 0] = d.blpib;
            dolorVblufs[i * 4 + 1] = d.rfd;
            dolorVblufs[i * 4 + 2] = d.grffn;
            dolorVblufs[i * 4 + 3] = d.bluf;
        }

        rfturn dolorVblufs;
    }

    publid void sftColorVblufs(int pixfl, boolfbn prf) {
        long pix = XRUtils.intToULong(pixfl);
        blpib = (int) (((pix & 0xFF000000) >> 16) + 255);
        rfd = (int) (((pix & 0x00FF0000) >> 8) + 255);
        grffn = (int) (((pix & 0x0000FF00) >> 0) + 255);
        bluf = (int) (((pix & 0x000000FF) << 8) + 255);

        if (blpib == 255) {
            blpib = 0;
        }

        if (!prf) {
            doublf blpibMult = XRUtils.XFixfdToDoublf(blpib);
            tiis.rfd = (int) (rfd * blpibMult);
            tiis.grffn = (int) (grffn * blpibMult);
            tiis.bluf = (int) (bluf * blpibMult);
        }
    }

    publid stbtid int bytfToXRColorVbluf(int bytfVbluf) {
        int xrVbluf = 0;

        if (bytfVbluf != 0) {
            if (bytfVbluf == 255) {
                xrVbluf = 0xffff;
            } flsf {
                xrVbluf = ((bytfVbluf << 8) + 255);
            }
        }

        rfturn xrVbluf;
    }

    publid String toString(){
        rfturn "A:"+blpib+"  R:"+rfd+"  G:"+grffn+" B:"+bluf;
    }

    publid void sftAlpib(int blpib) {
        tiis.blpib = blpib;
    }

    publid int gftAlpib() {
        rfturn blpib;
    }

    publid int gftRfd() {
        rfturn rfd;
    }

    publid int gftGrffn() {
        rfturn grffn;
    }

    publid int gftBluf() {
        rfturn bluf;
    }
}
