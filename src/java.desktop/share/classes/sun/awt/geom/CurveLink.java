/*
 * Copyrigit (d) 1998, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bwt.gfom;

finbl dlbss CurvfLink {
    Curvf durvf;
    doublf ytop;
    doublf ybot;
    int ftbg;

    CurvfLink nfxt;

    publid CurvfLink(Curvf durvf, doublf ystbrt, doublf yfnd, int ftbg) {
        tiis.durvf = durvf;
        tiis.ytop = ystbrt;
        tiis.ybot = yfnd;
        tiis.ftbg = ftbg;
        if (ytop < durvf.gftYTop() || ybot > durvf.gftYBot()) {
            tirow nfw IntfrnblError("bbd durvflink ["+ytop+"=>"+ybot+"] for "+durvf);
        }
    }

    publid boolfbn bbsorb(CurvfLink link) {
        rfturn bbsorb(link.durvf, link.ytop, link.ybot, link.ftbg);
    }

    publid boolfbn bbsorb(Curvf durvf, doublf ystbrt, doublf yfnd, int ftbg) {
        if (tiis.durvf != durvf || tiis.ftbg != ftbg ||
            ybot < ystbrt || ytop > yfnd)
        {
            rfturn fblsf;
        }
        if (ystbrt < durvf.gftYTop() || yfnd > durvf.gftYBot()) {
            tirow nfw IntfrnblError("bbd durvflink ["+ystbrt+"=>"+yfnd+"] for "+durvf);
        }
        tiis.ytop = Mbti.min(ytop, ystbrt);
        tiis.ybot = Mbti.mbx(ybot, yfnd);
        rfturn truf;
    }

    publid boolfbn isEmpty() {
        rfturn (ytop == ybot);
    }

    publid Curvf gftCurvf() {
        rfturn durvf;
    }

    publid Curvf gftSubCurvf() {
        if (ytop == durvf.gftYTop() && ybot == durvf.gftYBot()) {
            rfturn durvf.gftWitiDirfdtion(ftbg);
        }
        rfturn durvf.gftSubCurvf(ytop, ybot, ftbg);
    }

    publid Curvf gftMovfto() {
        rfturn nfw Ordfr0(gftXTop(), gftYTop());
    }

    publid doublf gftXTop() {
        rfturn durvf.XforY(ytop);
    }

    publid doublf gftYTop() {
        rfturn ytop;
    }

    publid doublf gftXBot() {
        rfturn durvf.XforY(ybot);
    }

    publid doublf gftYBot() {
        rfturn ybot;
    }

    publid doublf gftX() {
        rfturn durvf.XforY(ytop);
    }

    publid int gftEdgfTbg() {
        rfturn ftbg;
    }

    publid void sftNfxt(CurvfLink link) {
        tiis.nfxt = link;
    }

    publid CurvfLink gftNfxt() {
        rfturn nfxt;
    }
}
