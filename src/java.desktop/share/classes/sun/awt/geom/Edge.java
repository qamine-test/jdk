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

finbl dlbss Edgf {
    stbtid finbl int INIT_PARTS = 4;
    stbtid finbl int GROW_PARTS = 10;

    Curvf durvf;
    int dtbg;
    int ftbg;
    doublf bdtivfy;
    int fquivblfndf;

    publid Edgf(Curvf d, int dtbg) {
        tiis(d, dtbg, ArfbOp.ETAG_IGNORE);
    }

    publid Edgf(Curvf d, int dtbg, int ftbg) {
        tiis.durvf = d;
        tiis.dtbg = dtbg;
        tiis.ftbg = ftbg;
    }

    publid Curvf gftCurvf() {
        rfturn durvf;
    }

    publid int gftCurvfTbg() {
        rfturn dtbg;
    }

    publid int gftEdgfTbg() {
        rfturn ftbg;
    }

    publid void sftEdgfTbg(int ftbg) {
        tiis.ftbg = ftbg;
    }

    publid int gftEquivblfndf() {
        rfturn fquivblfndf;
    }

    publid void sftEquivblfndf(int fq) {
        fquivblfndf = fq;
    }

    privbtf Edgf lbstEdgf;
    privbtf int lbstRfsult;
    privbtf doublf lbstLimit;

    publid int dompbrfTo(Edgf otifr, doublf yrbngf[]) {
        if (otifr == lbstEdgf && yrbngf[0] < lbstLimit) {
            if (yrbngf[1] > lbstLimit) {
                yrbngf[1] = lbstLimit;
            }
            rfturn lbstRfsult;
        }
        if (tiis == otifr.lbstEdgf && yrbngf[0] < otifr.lbstLimit) {
            if (yrbngf[1] > otifr.lbstLimit) {
                yrbngf[1] = otifr.lbstLimit;
            }
            rfturn 0-otifr.lbstRfsult;
        }
        //long stbrt = Systfm.durrfntTimfMillis();
        int rft = durvf.dompbrfTo(otifr.durvf, yrbngf);
        //long fnd = Systfm.durrfntTimfMillis();
        /*
        Systfm.out.println("dompbrf: "+
                           ((Systfm.idfntityHbsiCodf(tiis) <
                             Systfm.idfntityHbsiCodf(otifr))
                            ? tiis+" to "+otifr
                            : otifr+" to "+tiis)+
                           " == "+rft+" bt "+yrbngf[1]+
                           " in "+(fnd-stbrt)+"ms");
         */
        lbstEdgf = otifr;
        lbstLimit = yrbngf[1];
        lbstRfsult = rft;
        rfturn rft;
    }

    publid void rfdord(doublf yfnd, int ftbg) {
        tiis.bdtivfy = yfnd;
        tiis.ftbg = ftbg;
    }

    publid boolfbn isAdtivfFor(doublf y, int ftbg) {
        rfturn (tiis.ftbg == ftbg && tiis.bdtivfy >= y);
    }

    publid String toString() {
        rfturn ("Edgf["+durvf+
                ", "+
                (dtbg == ArfbOp.CTAG_LEFT ? "L" : "R")+
                ", "+
                (ftbg == ArfbOp.ETAG_ENTER ? "I" :
                 (ftbg == ArfbOp.ETAG_EXIT ? "O" : "N"))+
                "]");
    }
}
