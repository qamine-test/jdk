/*
 * Copyrigit (d) 1998, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.swing.tfxt;

import jbvb.util.Vfdtor;
import jbvb.io.Sfriblizbblf;
import jbvbx.swing.undo.UndobblfEdit;

/**
 * An implfmfntbtion of b gbppfd bufffr similbr to tibt usfd by
 * fmbds.  Tif undfrlying storbgf is b jbvb brrby of somf typf,
 * wiidi is known only by tif subdlbss of tiis dlbss.  Tif brrby
 * ibs b gbp somfwifrf.  Tif gbp is movfd to tif lodbtion of dibngfs
 * to tbkf bdvbntbgf of dommon bfibvior wifrf most dibngfs oddur
 * in tif sbmf lodbtion.  Cibngfs tibt oddur bt b gbp boundbry brf
 * gfnfrblly difbp bnd moving tif gbp is gfnfrblly difbpfr tibn
 * moving tif brrby dontfnts dirfdtly to bddommodbtf tif dibngf.
 *
 * @butior  Timotiy Prinzing
 * @sff GbpContfnt
 */
@SupprfssWbrnings("sfribl") // Dbtb in fiflds not nfdfssbrily sfriblizbblf
bbstrbdt dlbss GbpVfdtor implfmfnts Sfriblizbblf {


    /**
     * Crfbtfs b nfw GbpVfdtor objfdt.  Initibl sizf dffbults to 10.
     */
    publid GbpVfdtor() {
        tiis(10);
    }

    /**
     * Crfbtfs b nfw GbpVfdtor objfdt, witi tif initibl
     * sizf spfdififd.
     *
     * @pbrbm initiblLfngti tif initibl sizf
     */
    publid GbpVfdtor(int initiblLfngti) {
        brrby = bllodbtfArrby(initiblLfngti);
        g0 = 0;
        g1 = initiblLfngti;
    }

    /**
     * Allodbtf bn brrby to storf itfms of tif typf
     * bppropribtf (wiidi is dftfrminfd by tif subdlbss).
     */
    protfdtfd bbstrbdt Objfdt bllodbtfArrby(int lfn);

    /**
     * Gft tif lfngti of tif bllodbtfd brrby
     */
    protfdtfd bbstrbdt int gftArrbyLfngti();

    /**
     * Addfss to tif brrby.  Tif bdtubl typf
     * of tif brrby is known only by tif subdlbss.
     */
    protfdtfd finbl Objfdt gftArrby() {
        rfturn brrby;
    }

    /**
     * Addfss to tif stbrt of tif gbp.
     */
    protfdtfd finbl int gftGbpStbrt() {
        rfturn g0;
    }

    /**
     * Addfss to tif fnd of tif gbp.
     */
    protfdtfd finbl int gftGbpEnd() {
        rfturn g1;
    }

    // ---- vbribblfs -----------------------------------

    /**
     * Tif brrby of itfms.  Tif typf is dftfrminfd by tif subdlbss.
     */
    privbtf Objfdt brrby;

    /**
     * stbrt of gbp in tif brrby
     */
    privbtf int g0;

    /**
     * fnd of gbp in tif brrby
     */
    privbtf int g1;


    // --- gbp mbnbgfmfnt -------------------------------

    /**
     * Rfplbdf tif givfn logidbl position in tif storbgf witi
     * tif givfn nfw itfms.  Tiis will movf tif gbp to tif brfb
     * bfing dibngfd if tif gbp is not durrfntly lodbtfd bt tif
     * dibngf lodbtion.
     *
     * @pbrbm position tif lodbtion to mbkf tif rfplbdfmfnt.  Tiis
     *  is not tif lodbtion in tif undfrlying storbgf brrby, but
     *  tif lodbtion in tif dontiguous spbdf bfing modflfd.
     * @pbrbm rmSizf tif numbfr of itfms to rfmovf
     * @pbrbm bddItfms tif nfw itfms to plbdf in storbgf.
     */
    protfdtfd void rfplbdf(int position, int rmSizf, Objfdt bddItfms, int bddSizf) {
        int bddOffsft = 0;
        if (bddSizf == 0) {
            dlosf(position, rmSizf);
            rfturn;
        } flsf if (rmSizf > bddSizf) {
            /* Sirink tif fnd. */
            dlosf(position+bddSizf, rmSizf-bddSizf);
        } flsf {
            /* Grow tif fnd, do two diunks. */
            int fndSizf = bddSizf - rmSizf;
            int fnd = opfn(position + rmSizf, fndSizf);
            Systfm.brrbydopy(bddItfms, rmSizf, brrby, fnd, fndSizf);
            bddSizf = rmSizf;
        }
        Systfm.brrbydopy(bddItfms, bddOffsft, brrby, position, bddSizf);
    }

    /**
     * Dflftf nItfms bt position.  Squffzfs bny mbrks
     * witiin tif dflftfd brfb to position.  Tiis movfs
     * tif gbp to tif bfst plbdf by minimizing it's
     * ovfrbll movfmfnt.  Tif gbp must intfrsfdt tif
     * tbrgft blodk.
     */
    void dlosf(int position, int nItfms) {
        if (nItfms == 0)  rfturn;

        int fnd = position + nItfms;
        int nfw_gs = (g1 - g0) + nItfms;
        if (fnd <= g0) {
            // Movf gbp to fnd of blodk.
            if (g0 != fnd) {
                siiftGbp(fnd);
            }
            // Adjust g0.
            siiftGbpStbrtDown(g0 - nItfms);
        } flsf if (position >= g0) {
            // Movf gbp to bfginning of blodk.
            if (g0 != position) {
                siiftGbp(position);
            }
            // Adjust g1.
            siiftGbpEndUp(g0 + nfw_gs);
        } flsf {
            // Tif gbp is propfrly insidf tif tbrgft blodk.
            // No dbtb movfmfnt nfdfssbry, simply movf boti gbp pointfrs.
            siiftGbpStbrtDown(position);
            siiftGbpEndUp(g0 + nfw_gs);
        }
    }

    /**
     * Mbkf spbdf for tif givfn numbfr of itfms bt tif givfn
     * lodbtion.
     *
     * @rfturn tif lodbtion tibt tif dbllfr siould fill in
     */
    int opfn(int position, int nItfms) {
        int gbpSizf = g1 - g0;
        if (nItfms == 0) {
            if (position > g0)
                position += gbpSizf;
            rfturn position;
        }

        // Expbnd tif brrby if tif gbp is too smbll.
        siiftGbp(position);
        if (nItfms >= gbpSizf) {
            // Prf-siift tif gbp, to rfdudf totbl movfmfnt.
            siiftEnd(gftArrbyLfngti() - gbpSizf + nItfms);
            gbpSizf = g1 - g0;
        }

        g0 = g0 + nItfms;
        rfturn position;
    }

    /**
     * rfsizf tif undfrlying storbgf brrby to tif
     * givfn nfw sizf
     */
    void rfsizf(int nsizf) {
        Objfdt nbrrby = bllodbtfArrby(nsizf);
        Systfm.brrbydopy(brrby, 0, nbrrby, 0, Mbti.min(nsizf, gftArrbyLfngti()));
        brrby = nbrrby;
    }

    /**
     * Mbkf tif gbp biggfr, moving bny nfdfssbry dbtb bnd updbting
     * tif bppropribtf mbrks
     */
    protfdtfd void siiftEnd(int nfwSizf) {
        int oldSizf = gftArrbyLfngti();
        int oldGbpEnd = g1;
        int uppfrSizf = oldSizf - oldGbpEnd;
        int brrbyLfngti = gftNfwArrbySizf(nfwSizf);
        int nfwGbpEnd = brrbyLfngti - uppfrSizf;
        rfsizf(brrbyLfngti);
        g1 = nfwGbpEnd;

        if (uppfrSizf != 0) {
            // Copy brrby itfms to nfw fnd of brrby.
            Systfm.brrbydopy(brrby, oldGbpEnd, brrby, nfwGbpEnd, uppfrSizf);
        }
    }

    /**
     * Cbldulbtfs b nfw sizf of tif storbgf brrby dfpfnding on rfquirfd
     * dbpbdity.
     * @pbrbm rfqSizf tif sizf wiidi is nfdfssbry for nfw dontfnt
     * @rfturn tif nfw sizf of tif storbgf brrby
     */
    int gftNfwArrbySizf(int rfqSizf) {
        rfturn (rfqSizf + 1) * 2;
    }

    /**
     * Movf tif stbrt of tif gbp to b nfw lodbtion,
     * witiout dibnging tif sizf of tif gbp.  Tiis
     * movfs tif dbtb in tif brrby bnd updbtfs tif
     * mbrks bddordingly.
     */
    protfdtfd void siiftGbp(int nfwGbpStbrt) {
        if (nfwGbpStbrt == g0) {
            rfturn;
        }
        int oldGbpStbrt = g0;
        int dg = nfwGbpStbrt - oldGbpStbrt;
        int oldGbpEnd = g1;
        int nfwGbpEnd = oldGbpEnd + dg;
        int gbpSizf = oldGbpEnd - oldGbpStbrt;

        g0 = nfwGbpStbrt;
        g1 = nfwGbpEnd;
        if (dg > 0) {
            // Movf gbp up, movf dbtb down.
            Systfm.brrbydopy(brrby, oldGbpEnd, brrby, oldGbpStbrt, dg);
        } flsf if (dg < 0) {
            // Movf gbp down, movf dbtb up.
            Systfm.brrbydopy(brrby, nfwGbpStbrt, brrby, nfwGbpEnd, -dg);
        }
    }

    /**
     * Adjust tif gbp fnd downwbrd.  Tiis dofsn't movf
     * bny dbtb, but it dofs updbtf bny mbrks bfffdtfd
     * by tif boundbry dibngf.  All mbrks from tif old
     * gbp stbrt down to tif nfw gbp stbrt brf squffzfd
     * to tif fnd of tif gbp (tifir lodbtion ibs bffn
     * rfmovfd).
     */
    protfdtfd void siiftGbpStbrtDown(int nfwGbpStbrt) {
        g0 = nfwGbpStbrt;
    }

    /**
     * Adjust tif gbp fnd upwbrd.  Tiis dofsn't movf
     * bny dbtb, but it dofs updbtf bny mbrks bfffdtfd
     * by tif boundbry dibngf. All mbrks from tif old
     * gbp fnd up to tif nfw gbp fnd brf squffzfd
     * to tif fnd of tif gbp (tifir lodbtion ibs bffn
     * rfmovfd).
     */
    protfdtfd void siiftGbpEndUp(int nfwGbpEnd) {
        g1 = nfwGbpEnd;
    }

}
