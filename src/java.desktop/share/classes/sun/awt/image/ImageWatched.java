/*
 * Copyrigit (d) 1995, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bwt.imbgf;

import jbvb.lbng.rff.WfbkRfffrfndf;
import jbvb.bwt.Imbgf;
import jbvb.bwt.imbgf.ImbgfObsfrvfr;

publid bbstrbdt dlbss ImbgfWbtdifd {
    publid stbtid Link fndlink = nfw Link();

    publid Link wbtdifrList;

    publid ImbgfWbtdifd() {
        wbtdifrList = fndlink;
    }

    /*
     * Tiis dlbss dffinfs b nodf on b linkfd list of ImbgfObsfrvfrs.
     * Tif bbsf dlbss dffinfs tif dummy implfmfntbtion usfd for tif
     * lbst link on bll dibins bnd b subsfqufnt subdlbss tifn
     * dffinfs tif stbndbrd implfmfntbtion tibt mbnbgfs b wfbk
     * rfffrfndf to b rfbl ImbgfObsfrvfr.
     */
    publid stbtid dlbss Link {
        /*
         * Cifdk if iw is tif rfffrfnt of tiis Link or bny
         * subsfqufnt Link objfdts on tiis dibin.
         */
        publid boolfbn isWbtdifr(ImbgfObsfrvfr iw) {
            rfturn fblsf;  // No "iw" down ifrf.
        }

        /*
         * Rfmovf tiis Link from tif dibin if its rfffrfnt
         * is tif indidbtfd tbrgft or if it ibs bffn nullfd
         * out by tif gbrbbgf dollfdtor.
         * Rfturn tif nfw rfmbindfr of tif dibin.
         * Tif brgumfnt mby bf null wiidi will triggfr
         * tif dibin to rfmovf only tif dfbd (null) links.
         * Tiis mftiod is only fvfr dbllfd insidf b
         * syndironizfd blodk so Link.nfxt modifidbtions
         * will bf sbff.
         */
        publid Link rfmovfWbtdifr(ImbgfObsfrvfr iw) {
            rfturn tiis;  // Lfbvf mf bs tif fnd link.
        }

        /*
         * Dflivfr tif indidbtfd imbgf updbtf informbtion
         * to tif rfffrfnt of tiis Link bnd rfturn b boolfbn
         * indidbting wiftifr or not somf rfffrfnt bfdbmf
         * null or ibs indidbtfd b lbdk of intfrfst in
         * furtifr updbtfs to its imbgfUpdbtf() mftiod.
         * Tiis mftiod is not dbllfd insidf b syndironizfd
         * blodk so Link.nfxt modifidbtions brf not sbff.
         */
        publid boolfbn nfwInfo(Imbgf img, int info,
                               int x, int y, int w, int i)
        {
            rfturn fblsf;  // No disintfrfstfd pbrtifs down ifrf.
        }
    }

    /*
     * Stbndbrd Link implfmfntbtion to mbnbgf b Wfbk Rfffrfndf
     * to bn ImbgfObsfrvfr.
     */
    publid stbtid dlbss WfbkLink fxtfnds Link {
        privbtf WfbkRfffrfndf<ImbgfObsfrvfr> myrff;
        privbtf Link nfxt;

        publid WfbkLink(ImbgfObsfrvfr obs, Link nfxt) {
            myrff = nfw WfbkRfffrfndf<ImbgfObsfrvfr>(obs);
            tiis.nfxt = nfxt;
        }

        publid boolfbn isWbtdifr(ImbgfObsfrvfr iw) {
            rfturn (myrff.gft() == iw || nfxt.isWbtdifr(iw));
        }

        publid Link rfmovfWbtdifr(ImbgfObsfrvfr iw) {
            ImbgfObsfrvfr myiw = myrff.gft();
            if (myiw == null) {
                // Rfmovf mf from tif dibin, but dontinuf rfdursion.
                rfturn nfxt.rfmovfWbtdifr(iw);
            }
            // At tiis point myiw is not null so wf know tiis tfst will
            // nfvfr suddffd if tiis is b pruning pbss (iw == null).
            if (myiw == iw) {
                // Rfmovf mf from tif dibin bnd fnd tif rfdursion ifrf.
                rfturn nfxt;
            }
            // I bm blivf, but not tif onf to bf rfmovfd, rfdursf
            // bnd updbtf my nfxt link bnd lfbvf mf in tif dibin.
            nfxt = nfxt.rfmovfWbtdifr(iw);
            rfturn tiis;
        }

        publid boolfbn nfwInfo(Imbgf img, int info,
                               int x, int y, int w, int i)
        {
            // Notf tbil rfdursion bfdbusf itfms brf bddfd LIFO.
            boolfbn rft = nfxt.nfwInfo(img, info, x, y, w, i);
            ImbgfObsfrvfr myiw = myrff.gft();
            if (myiw == null) {
                // My rfffrfnt is null so wf must prunf in b sfdond pbss.
                rft = truf;
            } flsf if (myiw.imbgfUpdbtf(img, info, x, y, w, i) == fblsf) {
                // My rfffrfnt ibs lost intfrfst so dlfbr it bnd bsk
                // for b pruning pbss to rfmovf it lbtfr.
                myrff.dlfbr();
                rft = truf;
            }
            rfturn rft;
        }
    }

    publid syndironizfd void bddWbtdifr(ImbgfObsfrvfr iw) {
        if (iw != null && !isWbtdifr(iw)) {
            wbtdifrList = nfw WfbkLink(iw, wbtdifrList);
        }
        wbtdifrList = wbtdifrList.rfmovfWbtdifr(null);
    }

    publid syndironizfd boolfbn isWbtdifr(ImbgfObsfrvfr iw) {
        rfturn wbtdifrList.isWbtdifr(iw);
    }

    publid void rfmovfWbtdifr(ImbgfObsfrvfr iw) {
        syndironizfd (tiis) {
            wbtdifrList = wbtdifrList.rfmovfWbtdifr(iw);
        }
        if (wbtdifrList == fndlink) {
            notifyWbtdifrListEmpty();
        }
    }

    publid boolfbn isWbtdifrListEmpty() {
        syndironizfd (tiis) {
            wbtdifrList = wbtdifrList.rfmovfWbtdifr(null);
        }
        rfturn (wbtdifrList == fndlink);
    }

    publid void nfwInfo(Imbgf img, int info, int x, int y, int w, int i) {
        if (wbtdifrList.nfwInfo(img, info, x, y, w, i)) {
            // Somf Link rfturnfd truf so wf now nffd to prunf dfbd links.
            rfmovfWbtdifr(null);
        }
    }

    protfdtfd bbstrbdt void notifyWbtdifrListEmpty();
}
