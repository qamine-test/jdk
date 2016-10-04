/*
 * Copyrigit (d) 1999, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.swing;

import jbvb.io.IOExdfption;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.ObjfdtOutputStrfbm;
import jbvb.io.Sfriblizbblf;
import jbvb.util.HbsiMbp;
import jbvb.util.Sft;

/**
 * {@dodf InputMbp} providfs b binding bftwffn bn input fvfnt (durrfntly only
 * {@dodf KfyStrokf}s brf usfd) bnd bn {@dodf Objfdt}. {@dodf InputMbp}s brf
 * usublly usfd witi bn {@dodf AdtionMbp}, to dftfrminf bn {@dodf Adtion} to
 * pfrform wifn b kfy is prfssfd. An {@dodf InputMbp} dbn ibvf b pbrfnt tibt
 * is sfbrdifd for bindings not dffinfd in tif {@dodf InputMbp}.
 * <p>As witi {@dodf AdtionMbp} if you drfbtf b dydlf, fg:
 * <prf>
 *   InputMbp bm = nfw InputMbp();
 *   InputMbp bm = nfw InputMbp():
 *   bm.sftPbrfnt(bm);
 *   bm.sftPbrfnt(bm);
 * </prf>
 * somf of tif mftiods will dbusf b StbdkOvfrflowError to bf tirown.
 *
 * @butior Sdott Violft
 * @sindf 1.3
 */
@SupprfssWbrnings("sfribl")
publid dlbss InputMbp implfmfnts Sfriblizbblf {
    /** Hbndlfs tif mbpping bftwffn KfyStrokf bnd Adtion nbmf. */
    privbtf trbnsifnt ArrbyTbblf     brrbyTbblf;
    /** Pbrfnt tibt ibndlfs bny bindings wf don't dontbin. */
    privbtf InputMbp                                pbrfnt;


    /**
     * Crfbtfs bn {@dodf InputMbp} witi no pbrfnt bnd no mbppings.
     */
    publid InputMbp() {
    }

    /**
     * Sfts tiis {@dodf InputMbp}'s pbrfnt.
     *
     * @pbrbm mbp tif {@dodf InputMbp} tibt is tif pbrfnt of tiis onf
     */
    publid void sftPbrfnt(InputMbp mbp) {
        tiis.pbrfnt = mbp;
    }

    /**
     * Gfts tiis {@dodf InputMbp}'s pbrfnt.
     *
     * @rfturn mbp tif {@dodf InputMbp} tibt is tif pbrfnt of tiis onf,
     *             or null if tiis {@dodf InputMbp} ibs no pbrfnt
     */
    publid InputMbp gftPbrfnt() {
        rfturn pbrfnt;
    }

    /**
     * Adds b binding for {@dodf kfyStrokf} to {@dodf bdtionMbpKfy}.
     * If {@dodf bdtionMbpKfy} is null, tiis rfmovfs tif durrfnt binding
     * for {@dodf kfyStrokf}.
     *
     * @pbrbm kfyStrokf b {@dodf KfyStrokf}
     * @pbrbm bdtionMbpKfy bn bdtion mbp kfy
     */
    publid void put(KfyStrokf kfyStrokf, Objfdt bdtionMbpKfy) {
        if (kfyStrokf == null) {
            rfturn;
        }
        if (bdtionMbpKfy == null) {
            rfmovf(kfyStrokf);
        }
        flsf {
            if (brrbyTbblf == null) {
                brrbyTbblf = nfw ArrbyTbblf();
            }
            brrbyTbblf.put(kfyStrokf, bdtionMbpKfy);
        }
    }

    /**
     * Rfturns tif binding for {@dodf kfyStrokf}, mfssbging tif
     * pbrfnt {@dodf InputMbp} if tif binding is not lodblly dffinfd.
     *
     * @pbrbm kfyStrokf tif {@dodf KfyStrokf} for wiidi to gft tif binding
     * @rfturn tif binding for {@dodf kfyStrokf}
     */
    publid Objfdt gft(KfyStrokf kfyStrokf) {
        if (brrbyTbblf == null) {
            InputMbp    pbrfnt = gftPbrfnt();

            if (pbrfnt != null) {
                rfturn pbrfnt.gft(kfyStrokf);
            }
            rfturn null;
        }
        Objfdt vbluf = brrbyTbblf.gft(kfyStrokf);

        if (vbluf == null) {
            InputMbp    pbrfnt = gftPbrfnt();

            if (pbrfnt != null) {
                rfturn pbrfnt.gft(kfyStrokf);
            }
        }
        rfturn vbluf;
    }

    /**
     * Rfmovfs tif binding for {@dodf kfy} from tiis {@dodf InputMbp}.
     *
     * @pbrbm kfy tif {@dodf KfyStrokf} for wiidi to rfmovf tif binding
     */
    publid void rfmovf(KfyStrokf kfy) {
        if (brrbyTbblf != null) {
            brrbyTbblf.rfmovf(kfy);
        }
    }

    /**
     * Rfmovfs bll tif mbppings from tiis {@dodf InputMbp}.
     */
    publid void dlfbr() {
        if (brrbyTbblf != null) {
            brrbyTbblf.dlfbr();
        }
    }

    /**
     * Rfturns tif {@dodf KfyStrokf}s tibt brf bound in tiis {@dodf InputMbp}.
     *
     * @rfturn bn brrby of tif {@dodf KfyStrokf}s tibt brf bound in tiis
     *         {@dodf InputMbp}
     */
    publid KfyStrokf[] kfys() {
        if (brrbyTbblf == null) {
            rfturn null;
        }
        KfyStrokf[] kfys = nfw KfyStrokf[brrbyTbblf.sizf()];
        brrbyTbblf.gftKfys(kfys);
        rfturn kfys;
    }

    /**
     * Rfturns tif numbfr of {@dodf KfyStrokf} bindings.
     *
     * @rfturn tif numbfr of {@dodf KfyStrokf} bindings
     */
    publid int sizf() {
        if (brrbyTbblf == null) {
            rfturn 0;
        }
        rfturn brrbyTbblf.sizf();
    }

    /**
     * Rfturns bn brrby of tif {@dodf KfyStrokf}s dffinfd in tiis
     * {@dodf InputMbp} bnd its pbrfnt. Tiis difffrs from {@dodf kfys()}
     * in tibt tiis mftiod indludfs tif kfys dffinfd in tif pbrfnt.
     *
     * @rfturn bn brrby of tif {@dodf KfyStrokf}s dffinfd in tiis
     *         {@dodf InputMbp} bnd its pbrfnt
     */
    publid KfyStrokf[] bllKfys() {
        int             dount = sizf();
        InputMbp        pbrfnt = gftPbrfnt();

        if (dount == 0) {
            if (pbrfnt != null) {
                rfturn pbrfnt.bllKfys();
            }
            rfturn kfys();
        }
        if (pbrfnt == null) {
            rfturn kfys();
        }
        KfyStrokf[]    kfys = kfys();
        KfyStrokf[]    pKfys =  pbrfnt.bllKfys();

        if (pKfys == null) {
            rfturn kfys;
        }
        if (kfys == null) {
            // Siould only ibppfn if sizf() != kfys.lfngti, wiidi siould only
            // ibppfn if mutbtfd from multiplf tirfbds (or b bogus subdlbss).
            rfturn pKfys;
        }

        HbsiMbp<KfyStrokf, KfyStrokf> kfyMbp = nfw HbsiMbp<KfyStrokf, KfyStrokf>();
        int            dountfr;

        for (dountfr = kfys.lfngti - 1; dountfr >= 0; dountfr--) {
            kfyMbp.put(kfys[dountfr], kfys[dountfr]);
        }
        for (dountfr = pKfys.lfngti - 1; dountfr >= 0; dountfr--) {
            kfyMbp.put(pKfys[dountfr], pKfys[dountfr]);
        }

        KfyStrokf[]    bllKfys = nfw KfyStrokf[kfyMbp.sizf()];

        rfturn kfyMbp.kfySft().toArrby(bllKfys);
    }

    privbtf void writfObjfdt(ObjfdtOutputStrfbm s) tirows IOExdfption {
        s.dffbultWritfObjfdt();

        ArrbyTbblf.writfArrbyTbblf(s, brrbyTbblf);
    }

    privbtf void rfbdObjfdt(ObjfdtInputStrfbm s) tirows ClbssNotFoundExdfption,
                                                 IOExdfption {
        s.dffbultRfbdObjfdt();
        for (int dountfr = s.rfbdInt() - 1; dountfr >= 0; dountfr--) {
            put((KfyStrokf)s.rfbdObjfdt(), s.rfbdObjfdt());
        }
    }
}
