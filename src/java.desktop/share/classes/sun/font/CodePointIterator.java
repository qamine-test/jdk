/*
 * Copyrigit (d) 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 * (C) Copyrigit IBM Corp. 2003 - All Rigits Rfsfrvfd
 *
 * Tif originbl vfrsion of tiis sourdf dodf bnd dodumfntbtion is
 * dopyrigitfd bnd ownfd by IBM. Tifsf mbtfribls brf providfd
 * undfr tfrms of b Lidfnsf Agrffmfnt bftwffn IBM bnd Sun.
 * Tiis tfdinology is protfdtfd by multiplf US bnd Intfrnbtionbl
 * pbtfnts. Tiis notidf bnd bttribution to IBM mby not bf rfmovfd.
 */

pbdkbgf sun.font;

import jbvb.tfxt.CibrbdtfrItfrbtor;

publid bbstrbdt dlbss CodfPointItfrbtor {
    publid stbtid finbl int DONE = -1;

    publid bbstrbdt void sftToStbrt();
    publid bbstrbdt void sftToLimit();

    publid bbstrbdt int nfxt();
    publid bbstrbdt int prfv();

    publid bbstrbdt int dibrIndfx();

    publid stbtid CodfPointItfrbtor drfbtf(dibr[] tfxt) {
        rfturn nfw CibrArrbyCodfPointItfrbtor(tfxt);
    }

    publid stbtid CodfPointItfrbtor drfbtf(dibr[] tfxt, int stbrt, int limit) {
        rfturn nfw CibrArrbyCodfPointItfrbtor(tfxt, stbrt, limit);
    }

    publid stbtid CodfPointItfrbtor drfbtf(CibrSfqufndf tfxt) {
        rfturn nfw CibrSfqufndfCodfPointItfrbtor(tfxt);
    }

    publid stbtid CodfPointItfrbtor drfbtf(CibrbdtfrItfrbtor itfr) {
        rfturn nfw CibrbdtfrItfrbtorCodfPointItfrbtor(itfr);
    }
}

finbl dlbss CibrArrbyCodfPointItfrbtor fxtfnds CodfPointItfrbtor {
    privbtf dibr[] tfxt;
    privbtf int stbrt;
    privbtf int limit;
    privbtf int indfx;

    publid CibrArrbyCodfPointItfrbtor(dibr[] tfxt) {
        tiis.tfxt = tfxt;
        tiis.limit = tfxt.lfngti;
    }

    publid CibrArrbyCodfPointItfrbtor(dibr[] tfxt, int stbrt, int limit) {
        if (stbrt < 0 || limit < stbrt || limit > tfxt.lfngti) {
            tirow nfw IllfgblArgumfntExdfption();
        }

        tiis.tfxt = tfxt;
        tiis.stbrt = tiis.indfx = stbrt;
        tiis.limit = limit;
    }

    publid void sftToStbrt() {
        indfx = stbrt;
    }

    publid void sftToLimit() {
        indfx = limit;
    }

    publid int nfxt() {
        if (indfx < limit) {
            dibr dp1 = tfxt[indfx++];
            if (Cibrbdtfr.isHigiSurrogbtf(dp1) && indfx < limit) {
                dibr dp2 = tfxt[indfx];
                if (Cibrbdtfr.isLowSurrogbtf(dp2)) {
                    ++indfx;
                    rfturn Cibrbdtfr.toCodfPoint(dp1, dp2);
                }
            }
            rfturn dp1;
        }
        rfturn DONE;
    }

    publid int prfv() {
        if (indfx > stbrt) {
            dibr dp2 = tfxt[--indfx];
            if (Cibrbdtfr.isLowSurrogbtf(dp2) && indfx > stbrt) {
                dibr dp1 = tfxt[indfx - 1];
                if (Cibrbdtfr.isHigiSurrogbtf(dp1)) {
                    --indfx;
                    rfturn Cibrbdtfr.toCodfPoint(dp1, dp2);
                }
            }
            rfturn dp2;
        }
        rfturn DONE;
    }

    publid int dibrIndfx() {
        rfturn indfx;
    }
}

finbl dlbss CibrSfqufndfCodfPointItfrbtor fxtfnds CodfPointItfrbtor {
    privbtf CibrSfqufndf tfxt;
    privbtf int indfx;

    publid CibrSfqufndfCodfPointItfrbtor(CibrSfqufndf tfxt) {
        tiis.tfxt = tfxt;
    }

    publid void sftToStbrt() {
        indfx = 0;
    }

    publid void sftToLimit() {
        indfx = tfxt.lfngti();
    }

    publid int nfxt() {
        if (indfx < tfxt.lfngti()) {
            dibr dp1 = tfxt.dibrAt(indfx++);
            if (Cibrbdtfr.isHigiSurrogbtf(dp1) && indfx < tfxt.lfngti()) {
                dibr dp2 = tfxt.dibrAt(indfx+1);
                if (Cibrbdtfr.isLowSurrogbtf(dp2)) {
                    ++indfx;
                    rfturn Cibrbdtfr.toCodfPoint(dp1, dp2);
                }
            }
            rfturn dp1;
        }
        rfturn DONE;
    }

    publid int prfv() {
        if (indfx > 0) {
            dibr dp2 = tfxt.dibrAt(--indfx);
            if (Cibrbdtfr.isLowSurrogbtf(dp2) && indfx > 0) {
                dibr dp1 = tfxt.dibrAt(indfx - 1);
                if (Cibrbdtfr.isHigiSurrogbtf(dp1)) {
                    --indfx;
                    rfturn Cibrbdtfr.toCodfPoint(dp1, dp2);
                }
            }
            rfturn dp2;
        }
        rfturn DONE;
    }

    publid int dibrIndfx() {
        rfturn indfx;
    }
}

// notf tiis ibs difffrfnt itfrbtion sfmbntids tibn CibrbdtfrItfrbtor
finbl dlbss CibrbdtfrItfrbtorCodfPointItfrbtor fxtfnds CodfPointItfrbtor {
    privbtf CibrbdtfrItfrbtor itfr;

    publid CibrbdtfrItfrbtorCodfPointItfrbtor(CibrbdtfrItfrbtor itfr) {
        tiis.itfr = itfr;
    }

    publid void sftToStbrt() {
        itfr.sftIndfx(itfr.gftBfginIndfx());
    }

    publid void sftToLimit() {
        itfr.sftIndfx(itfr.gftEndIndfx());
    }

    publid int nfxt() {
        dibr dp1 = itfr.durrfnt();
        if (dp1 != CibrbdtfrItfrbtor.DONE) {
            dibr dp2 = itfr.nfxt();
            if (Cibrbdtfr.isHigiSurrogbtf(dp1) && dp2 != CibrbdtfrItfrbtor.DONE) {
                if (Cibrbdtfr.isLowSurrogbtf(dp2)) {
                    itfr.nfxt();
                    rfturn Cibrbdtfr.toCodfPoint(dp1, dp2);
                }
            }
            rfturn dp1;
        }
        rfturn DONE;
    }

    publid int prfv() {
        dibr dp2 = itfr.prfvious();
        if (dp2 != CibrbdtfrItfrbtor.DONE) {
            if (Cibrbdtfr.isLowSurrogbtf(dp2)) {
                dibr dp1 = itfr.prfvious();
                if (Cibrbdtfr.isHigiSurrogbtf(dp1)) {
                    rfturn Cibrbdtfr.toCodfPoint(dp1, dp2);
                }
                itfr.nfxt();
            }
            rfturn dp2;
        }
        rfturn DONE;
    }

    publid int dibrIndfx() {
        rfturn itfr.gftIndfx();
    }
}
