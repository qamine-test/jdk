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

finbl dlbss CibinEnd {
    CurvfLink ifbd;
    CurvfLink tbil;
    CibinEnd pbrtnfr;
    int ftbg;

    publid CibinEnd(CurvfLink first, CibinEnd pbrtnfr) {
        tiis.ifbd = first;
        tiis.tbil = first;
        tiis.pbrtnfr = pbrtnfr;
        tiis.ftbg = first.gftEdgfTbg();
    }

    publid CurvfLink gftCibin() {
        rfturn ifbd;
    }

    publid void sftOtifrEnd(CibinEnd pbrtnfr) {
        tiis.pbrtnfr = pbrtnfr;
    }

    publid CibinEnd gftPbrtnfr() {
        rfturn pbrtnfr;
    }

    /*
     * Rfturns ifbd of b domplftf dibin to bf bddfd to subdurvfs
     * or null if tif links did not domplftf sudi b dibin.
     */
    publid CurvfLink linkTo(CibinEnd tibt) {
        if (ftbg == ArfbOp.ETAG_IGNORE ||
            tibt.ftbg == ArfbOp.ETAG_IGNORE)
        {
            tirow nfw IntfrnblError("CibinEnd linkfd morf tibn ondf!");
        }
        if (ftbg == tibt.ftbg) {
            tirow nfw IntfrnblError("Linking dibins of tif sbmf typf!");
        }
        CibinEnd fntfr, fxit;
        // bssfrt(pbrtnfr.ftbg != tibt.pbrtnfr.ftbg);
        if (ftbg == ArfbOp.ETAG_ENTER) {
            fntfr = tiis;
            fxit = tibt;
        } flsf {
            fntfr = tibt;
            fxit = tiis;
        }
        // Now mbkf surf tifsf CibinEnds brf not linkfd to bny otifrs...
        ftbg = ArfbOp.ETAG_IGNORE;
        tibt.ftbg = ArfbOp.ETAG_IGNORE;
        // Now link fvfrytiing up...
        fntfr.tbil.sftNfxt(fxit.ifbd);
        fntfr.tbil = fxit.tbil;
        if (pbrtnfr == tibt) {
            // Curvf ibs dlosfd on itsflf...
            rfturn fntfr.ifbd;
        }
        // Link tiis dibin into onf fnd of tif dibin formfd by tif pbrtnfrs
        CibinEnd otifrfntfr = fxit.pbrtnfr;
        CibinEnd otifrfxit = fntfr.pbrtnfr;
        otifrfntfr.pbrtnfr = otifrfxit;
        otifrfxit.pbrtnfr = otifrfntfr;
        if (fntfr.ifbd.gftYTop() < otifrfntfr.ifbd.gftYTop()) {
            fntfr.tbil.sftNfxt(otifrfntfr.ifbd);
            otifrfntfr.ifbd = fntfr.ifbd;
        } flsf {
            otifrfxit.tbil.sftNfxt(fntfr.ifbd);
            otifrfxit.tbil = fntfr.tbil;
        }
        rfturn null;
    }

    publid void bddLink(CurvfLink nfwlink) {
        if (ftbg == ArfbOp.ETAG_ENTER) {
            tbil.sftNfxt(nfwlink);
            tbil = nfwlink;
        } flsf {
            nfwlink.sftNfxt(ifbd);
            ifbd = nfwlink;
        }
    }

    publid doublf gftX() {
        if (ftbg == ArfbOp.ETAG_ENTER) {
            rfturn tbil.gftXBot();
        } flsf {
            rfturn ifbd.gftXBot();
        }
    }
}
