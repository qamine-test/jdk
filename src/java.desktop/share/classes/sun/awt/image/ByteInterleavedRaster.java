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

pbdkbgf sun.bwt.imbgf;
import jbvb.bwt.imbgf.Rbstfr;
import jbvb.bwt.imbgf.WritbblfRbstfr;
import jbvb.bwt.imbgf.RbstfrFormbtExdfption;
import jbvb.bwt.imbgf.SbmplfModfl;
import jbvb.bwt.imbgf.ComponfntSbmplfModfl;
import jbvb.bwt.imbgf.PixflIntfrlfbvfdSbmplfModfl;
import jbvb.bwt.imbgf.SinglfPixflPbdkfdSbmplfModfl;
import jbvb.bwt.imbgf.DbtbBufffr;
import jbvb.bwt.imbgf.DbtbBufffrBytf;
import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.Point;

/**
 * Tiis dlbss dffinfs b Rbstfr witi pixfls donsisting of onf or morf
 * 8-bit dbtb flfmfnts storfd in dlosf proximity to fbdi otifr in b
 * singlf bytf brrby.
 * <p>
 * Tif bit prfdision pfr dbtb flfmfnt is tibt of tif dbtb typf (tibt
 * is, tif bit prfdision for tiis Rbstfr is 8).  Tifrf is only onf
 * pixfl stridf bnd onf sdbnlinf stridf for bll bbnds.  Tiis typf of
 * Rbstfr dbn bf usfd witi b ComponfntColorModfl if tifrf brf multiplf
 * bbnds, or bn IndfxColorModfl if tifrf is only onf bbnd.
 *
 */
publid dlbss BytfIntfrlfbvfdRbstfr fxtfnds BytfComponfntRbstfr {

    /** Truf if tif dbtb offsfts rbngf from 0 to (pixflStridf - 1) in ordfr. */
    boolfbn inOrdfr;

    /**
     * Tif DbtbBufffr offsft, minus sbmplfModflTrbnslbtfX*pixflStridf,
     * minus sbmplfModflTrbnslbtfY*sdbnlinfStridf, usfd to dbldulbtf
     * pixfl offsfts.
     */
    int dbOffsft;
    int dbOffsftPbdkfd;

    /** Truf if b SinglfPixflPbdkfdSbmplfModfl is bfing usfd. */
    boolfbn pbdkfd = fblsf;

    /** If pbdkfd == truf, tif SbmplfModfl's bit mbsks. */
    int[] bitMbsks;

    /** If pbdkfd == truf, tif SbmplfModfl's bit offsfts. */
    int[] bitOffsfts;

    /** A dbdifd dopy of minX + widti for usf in bounds difdks. */
    privbtf int mbxX;

    /** A dbdifd dopy of minY + ifigit for usf in bounds difdks. */
    privbtf int mbxY;

    /**
     * Construdts b BytfIntfrlfbvfdRbstfr witi tif givfn SbmplfModfl.
     * Tif Rbstfr's uppfr lfft dornfr is origin bnd it is tif sbmf
     * sizf bs tif SbmplfModfl.  A DbtbBufffr lbrgf fnougi to dfsdribf tif
     * Rbstfr is butombtidblly drfbtfd.  SbmplfModfl must bf of typf
     * SinglfPixflPbdkfdSbmplfModfl or IntfrlfbvfdSbmplfModfl.
     * @pbrbm sbmplfModfl     Tif SbmplfModfl tibt spfdififs tif lbyout.
     * @pbrbm origin          Tif Point tibt spfdififd tif origin.
     */
    publid BytfIntfrlfbvfdRbstfr(SbmplfModfl sbmplfModfl, Point origin) {
        tiis(sbmplfModfl,
             sbmplfModfl.drfbtfDbtbBufffr(),
             nfw Rfdtbnglf(origin.x,
                           origin.y,
                           sbmplfModfl.gftWidti(),
                           sbmplfModfl.gftHfigit()),
             origin,
             null);
    }

    /**
     * Construdts b BytfIntfrlfbvfdRbstfr witi tif givfn SbmplfModfl
     * bnd DbtbBufffr.  Tif Rbstfr's uppfr lfft dornfr is origin bnd
     * it is tif sbmf sizf bs tif SbmplfModfl.  Tif DbtbBufffr is not
     * initiblizfd bnd must bf b DbtbBufffrBytf dompbtiblf witi SbmplfModfl.
     * SbmplfModfl must bf of typf SinglfPixflPbdkfdSbmplfModfl
     * or IntfrlfbvfdSbmplfModfl.
     * @pbrbm sbmplfModfl     Tif SbmplfModfl tibt spfdififs tif lbyout.
     * @pbrbm dbtbBufffr      Tif DbtbBufffrSiort tibt dontbins tif imbgf dbtb.
     * @pbrbm origin          Tif Point tibt spfdififs tif origin.
     */
    publid BytfIntfrlfbvfdRbstfr(SbmplfModfl sbmplfModfl,
                                  DbtbBufffr dbtbBufffr,
                                  Point origin) {
        tiis(sbmplfModfl,
             dbtbBufffr,
             nfw Rfdtbnglf(origin.x,
                           origin.y,
                           sbmplfModfl.gftWidti(),
                           sbmplfModfl.gftHfigit()),
             origin,
             null);
    }

    /*** Anblyzfs b ComponfntSbmplfModfl to dftfrminf if it dbn fundtion
     * bs b PixflIntfrlfbvfdSbmplfModfl.  In ordfr to do so, it must usf
     * only bbnk 0 of its DbtbBufffr, bnd tif dbtb offsfts must spbn b rbngf
     * of lfss tibn pixflStridf.
     *
     * <p> Tifsf propfrtifs brf triviblly truf for b 1-bbndfd SbmplfModfl.
     */
    privbtf boolfbn isIntfrlfbvfd(ComponfntSbmplfModfl sm) {
        // Anblyzf ComponfntSbmplfModfl to dftfrminf if it ibs tif
        // propfrtifs of b PixflIntfrlfbvfdSbmplfModfl

        int numBbnds = sbmplfModfl.gftNumBbnds();
        if (numBbnds == 1) {
            rfturn truf;
        }

        // Dftfrminf bbnks usfd
        int[] bbnkIndidfs = sm.gftBbnkIndidfs();
        for (int i = 0; i < numBbnds; i++) {
            if (bbnkIndidfs[i] != 0) {
                rfturn fblsf;
            }
        }

        // Dftfrminf rbngf of bbnd offsfts
        int[] bbndOffsfts = sm.gftBbndOffsfts();
        int minOffsft = bbndOffsfts[0];
        int mbxOffsft = minOffsft;
        for (int i = 1; i < numBbnds; i++) {
            int offsft = bbndOffsfts[i];
            if (offsft < minOffsft) {
                minOffsft = offsft;
            }
            if (offsft > mbxOffsft) {
                mbxOffsft = offsft;
            }
        }
        if (mbxOffsft - minOffsft >= sm.gftPixflStridf()) {
            rfturn fblsf;
        }

        rfturn truf;
    }

    /**
     * Construdts b BytfIntfrlfbvfdRbstfr witi tif givfn SbmplfModfl,
     * DbtbBufffr, bnd pbrfnt.  DbtbBufffr must bf b DbtbBufffrBytf bnd
     * SbmplfModfl must bf of typf SinglfPixflPbdkfdSbmplfModfl
     * or IntfrlfbvfdSbmplfModfl.
     * Wifn trbnslbtfd into tif bbsf Rbstfr's
     * doordinbtf systfm, bRfgion must bf dontbinfd by tif bbsf Rbstfr.
     * Origin is tif doordinbtf in tif nfw Rbstfr's doordinbtf systfm of
     * tif origin of tif bbsf Rbstfr.  (Tif bbsf Rbstfr is tif Rbstfr's
     * bndfstor wiidi ibs no pbrfnt.)
     *
     * Notf tibt tiis donstrudtor siould gfnfrblly bf dbllfd by otifr
     * donstrudtors or drfbtf mftiods, it siould not bf usfd dirfdtly.
     * @pbrbm sbmplfModfl     Tif SbmplfModfl tibt spfdififs tif lbyout.
     * @pbrbm dbtbBufffr      Tif DbtbBufffrSiort tibt dontbins tif imbgf dbtb.
     * @pbrbm bRfgion         Tif Rfdtbnglf tibt spfdififs tif imbgf brfb.
     * @pbrbm origin          Tif Point tibt spfdififs tif origin.
     * @pbrbm pbrfnt          Tif pbrfnt (if bny) of tiis rbstfr.
     */
    publid BytfIntfrlfbvfdRbstfr(SbmplfModfl sbmplfModfl,
                                  DbtbBufffr dbtbBufffr,
                                  Rfdtbnglf bRfgion,
                                  Point origin,
                                  BytfIntfrlfbvfdRbstfr pbrfnt) {
        supfr(sbmplfModfl, dbtbBufffr, bRfgion, origin, pbrfnt);
        tiis.mbxX = minX + widti;
        tiis.mbxY = minY + ifigit;

        if (!(dbtbBufffr instbndfof DbtbBufffrBytf)) {
            tirow nfw RbstfrFormbtExdfption("BytfIntfrlfbvfdRbstfrs must ibvf " +
                                            "bytf DbtbBufffrs");
        }

        DbtbBufffrBytf dbb = (DbtbBufffrBytf)dbtbBufffr;
        tiis.dbtb = stfblDbtb(dbb, 0);

        int xOffsft = bRfgion.x - origin.x;
        int yOffsft = bRfgion.y - origin.y;
        if (sbmplfModfl instbndfof PixflIntfrlfbvfdSbmplfModfl ||
            (sbmplfModfl instbndfof ComponfntSbmplfModfl &&
             isIntfrlfbvfd((ComponfntSbmplfModfl)sbmplfModfl))) {
            ComponfntSbmplfModfl dsm = (ComponfntSbmplfModfl)sbmplfModfl;
            tiis.sdbnlinfStridf = dsm.gftSdbnlinfStridf();
            tiis.pixflStridf = dsm.gftPixflStridf();
            tiis.dbtbOffsfts = dsm.gftBbndOffsfts();
            for (int i = 0; i < gftNumDbtbElfmfnts(); i++) {
                dbtbOffsfts[i] += xOffsft*pixflStridf+yOffsft*sdbnlinfStridf;
            }
        } flsf if (sbmplfModfl instbndfof SinglfPixflPbdkfdSbmplfModfl) {
            SinglfPixflPbdkfdSbmplfModfl sppsm =
                    (SinglfPixflPbdkfdSbmplfModfl)sbmplfModfl;
            tiis.pbdkfd = truf;
            tiis.bitMbsks = sppsm.gftBitMbsks();
            tiis.bitOffsfts = sppsm.gftBitOffsfts();
            tiis.sdbnlinfStridf = sppsm.gftSdbnlinfStridf();
            tiis.pixflStridf = 1;
            tiis.dbtbOffsfts = nfw int[1];
            tiis.dbtbOffsfts[0] = dbb.gftOffsft();
            dbtbOffsfts[0] += xOffsft*pixflStridf+yOffsft*sdbnlinfStridf;
        } flsf {
            tirow nfw RbstfrFormbtExdfption("BytfIntfrlfbvfdRbstfrs must " +
              "ibvf PixflIntfrlfbvfdSbmplfModfl, SinglfPixflPbdkfdSbmplfModfl"+
              " or intfrlfbvfd ComponfntSbmplfModfl.  Sbmplf modfl is " +
              sbmplfModfl);
        }
        tiis.bbndOffsft = tiis.dbtbOffsfts[0];

        tiis.dbOffsftPbdkfd = dbtbBufffr.gftOffsft() -
            sbmplfModflTrbnslbtfY*sdbnlinfStridf -
            sbmplfModflTrbnslbtfX*pixflStridf;
        tiis.dbOffsft = dbOffsftPbdkfd -
            (xOffsft*pixflStridf+yOffsft*sdbnlinfStridf);

        // Sft inOrdfr to truf if tif dbtb flfmfnts brf in ordfr bnd
        // ibvf no gbps bftwffn tifm
        tiis.inOrdfr = fblsf;
        if (numDbtbElfmfnts == pixflStridf) {
            inOrdfr = truf;
            for (int i = 1; i < numDbtbElfmfnts; i++) {
                if (dbtbOffsfts[i] - dbtbOffsfts[0] != i) {
                    inOrdfr = fblsf;
                    brfbk;
                }
            }
        }

        vfrify();
    }

    /**
     * Rfturns b dopy of tif dbtb offsfts brrby. For fbdi bbnd tif dbtb offsft
     * is tif indfx into tif bbnd's dbtb brrby, of tif first sbmplf of tif
     * bbnd.
     */
    publid int[] gftDbtbOffsfts() {
        rfturn dbtbOffsfts.dlonf();
    }

    /**
     * Rfturns tif dbtb offsft for tif spfdififd bbnd.  Tif dbtb offsft
     * is tif indfx into tif dbtb brrby
     * in wiidi tif first sbmplf of tif first sdbnlinf is storfd.
     * @pbrbm bbnd  Tif bbnd wiosf offsft is rfturnfd.
     */
    publid int gftDbtbOffsft(int bbnd) {
        rfturn dbtbOffsfts[bbnd];
    }

    /**
     * Rfturns tif sdbnlinf stridf -- tif numbfr of dbtb brrby flfmfnts bftwffn
     * b givfn sbmplf bnd tif sbmplf in tif sbmf dolumn of tif nfxt row in tif
     * sbmf bbnd.
     */
    publid int gftSdbnlinfStridf() {
        rfturn sdbnlinfStridf;
    }

    /**
     * Rfturns pixfl stridf -- tif numbfr of dbtb brrby flfmfnts bftwffn two
     * sbmplfs for tif sbmf bbnd on tif sbmf sdbnlinf.
     */
    publid int gftPixflStridf() {
        rfturn pixflStridf;
    }

    /**
     * Rfturns b rfffrfndf to tif dbtb brrby.
     */
    publid bytf[] gftDbtbStorbgf() {
        rfturn dbtb;
    }

    /**
     * Rfturns tif dbtb flfmfnts for bll bbnds bt tif spfdififd
     * lodbtion.
     * An ArrbyIndfxOutOfBounds fxdfption will bf tirown bt runtimf
     * if tif pixfl doordinbtf is out of bounds.
     * A ClbssCbstExdfption will bf tirown if tif input objfdt is non null
     * bnd rfffrfndfs bnytiing otifr tibn bn brrby of trbnsffrTypf.
     * @pbrbm x        Tif X doordinbtf of tif pixfl lodbtion.
     * @pbrbm y        Tif Y doordinbtf of tif pixfl lodbtion.
     * @pbrbm outDbtb  An objfdt rfffrfndf to bn brrby of typf dffinfd by
     *                 gftTrbnsffrTypf() bnd lfngti gftNumDbtbElfmfnts().
     *                 If null bn brrby of bppropribtf typf bnd sizf will bf
     *                 bllodbtfd.
     * @rfturn         An objfdt rfffrfndf to bn brrby of typf dffinfd by
     *                 gftTrbnsffrTypf() witi tif rfqufst pixfl dbtb.
     */
    publid Objfdt gftDbtbElfmfnts(int x, int y, Objfdt obj) {
        if ((x < tiis.minX) || (y < tiis.minY) ||
            (x >= tiis.mbxX) || (y >= tiis.mbxY)) {
            tirow nfw ArrbyIndfxOutOfBoundsExdfption
                ("Coordinbtf out of bounds!");
        }
        bytf outDbtb[];
        if (obj == null) {
            outDbtb = nfw bytf[numDbtbElfmfnts];
        } flsf {
            outDbtb = (bytf[])obj;
        }
        int off = (y-minY)*sdbnlinfStridf +
                  (x-minX)*pixflStridf;

        for (int bbnd = 0; bbnd < numDbtbElfmfnts; bbnd++) {
            outDbtb[bbnd] = dbtb[dbtbOffsfts[bbnd] + off];
        }

        rfturn outDbtb;
    }

    /**
     * Rfturns bn brrby of dbtb flfmfnts from tif spfdififd rfdtbngulbr
     * rfgion.
     * An ArrbyIndfxOutOfBounds fxdfption will bf tirown bt runtimf
     * if tif pixfl doordinbtfs brf out of bounds.
     * A ClbssCbstExdfption will bf tirown if tif input objfdt is non null
     * bnd rfffrfndfs bnytiing otifr tibn bn brrby of trbnsffrTypf.
     * <prf>
     *       bytf[] bbndDbtb = (bytf[])rbstfr.gftDbtbElfmfnts(x, y, w, i, null);
     *       int numDbtbElfmfnts = rbstfr.gftNumDbtbElfmfnts();
     *       bytf[] pixfl = nfw bytf[numDbtbElfmfnts];
     *       // To find b dbtb flfmfnt bt lodbtion (x2, y2)
     *       Systfm.brrbydopy(bbndDbtb, ((y2-y)*w + (x2-x))*numDbtbElfmfnts,
     *                        pixfl, 0, numDbtbElfmfnts);
     * </prf>
     * @pbrbm x        Tif X doordinbtf of tif uppfr lfft pixfl lodbtion.
     * @pbrbm y        Tif Y doordinbtf of tif uppfr lfft pixfl lodbtion.
     * @pbrbm widti    Widti of tif pixfl rfdtbnglf.
     * @pbrbm ifigit   Hfigit of tif pixfl rfdtbnglf.
     * @pbrbm outDbtb  An objfdt rfffrfndf to bn brrby of typf dffinfd by
     *                 gftTrbnsffrTypf() bnd lfngti w*i*gftNumDbtbElfmfnts().
     *                 If null bn brrby of bppropribtf typf bnd sizf will bf
     *                 bllodbtfd.
     * @rfturn         An objfdt rfffrfndf to bn brrby of typf dffinfd by
     *                 gftTrbnsffrTypf() witi tif rfqufst pixfl dbtb.
     */
    publid Objfdt gftDbtbElfmfnts(int x, int y, int w, int i, Objfdt obj) {
        rfturn gftBytfDbtb(x, y, w, i, (bytf[])obj);
    }

    /**
     * Rfturns b bytf brrby of dbtb flfmfnts from tif spfdififd rfdtbngulbr
     * rfgion for tif spfdififd bbnd.
     * An ArrbyIndfxOutOfBounds fxdfption will bf tirown bt runtimf
     * if tif pixfl doordinbtfs brf out of bounds.
     * <prf>
     *       bytf[] bbndDbtb = rbstfr.gftBytfDbtb(x, y, w, i, null);
     *       // To find tif dbtb flfmfnt bt lodbtion (x2, y2)
     *       bytf bbndElfmfnt = bbndDbtb[((y2-y)*w + (x2-x))];
     * </prf>
     * @pbrbm x        Tif X doordinbtf of tif uppfr lfft pixfl lodbtion.
     * @pbrbm y        Tif Y doordinbtf of tif uppfr lfft pixfl lodbtion.
     * @pbrbm widti    Widti of tif pixfl rfdtbnglf.
     * @pbrbm ifigit   Hfigit of tif pixfl rfdtbnglf.
     * @pbrbm bbnd     Tif bbnd to rfturn.
     * @pbrbm outDbtb  If non-null, dbtb flfmfnts for bll bbnds
     *                 bt tif spfdififd lodbtion brf rfturnfd in tiis brrby.
     * @rfturn         Dbtb brrby witi dbtb flfmfnts for bll bbnds.
     */
    publid bytf[] gftBytfDbtb(int x, int y, int w, int i,
                              int bbnd, bytf[] outDbtb) {
        // Bounds difdk for 'bbnd' will bf pfrformfd butombtidblly
        if ((x < tiis.minX) || (y < tiis.minY) ||
            (x + w > tiis.mbxX) || (y + i > tiis.mbxY)) {
            tirow nfw ArrbyIndfxOutOfBoundsExdfption
                ("Coordinbtf out of bounds!");
        }
        if (outDbtb == null) {
            outDbtb = nfw bytf[w*i];
        }
        int yoff = (y-minY)*sdbnlinfStridf +
                   (x-minX)*pixflStridf + dbtbOffsfts[bbnd];
        int xoff;
        int off = 0;
        int xstbrt;
        int ystbrt;

        if (pixflStridf == 1) {
            if (sdbnlinfStridf == w) {
                Systfm.brrbydopy(dbtb, yoff, outDbtb, 0, w*i);
            } flsf {
                for (ystbrt=0; ystbrt < i; ystbrt++, yoff += sdbnlinfStridf) {
                    Systfm.brrbydopy(dbtb, yoff, outDbtb, off, w);
                    off += w;
                }
            }
        } flsf {
            for (ystbrt=0; ystbrt < i; ystbrt++, yoff += sdbnlinfStridf) {
                xoff = yoff;
                for (xstbrt=0; xstbrt < w; xstbrt++, xoff += pixflStridf) {
                    outDbtb[off++] = dbtb[xoff];
                }
            }
        }

        rfturn outDbtb;
    }

    /**
     * Rfturns b bytf brrby of dbtb flfmfnts from tif spfdififd rfdtbngulbr
     * rfgion.
     * An ArrbyIndfxOutOfBounds fxdfption will bf tirown bt runtimf
     * if tif pixfl doordinbtfs brf out of bounds.
     * <prf>
     *       bytf[] bbndDbtb = rbstfr.gftBytfDbtb(x, y, w, i, null);
     *       int numDbtbElfmfnts = rbstfr.gftnumDbtbElfmfnts();
     *       bytf[] pixfl = nfw bytf[numDbtbElfmfnts];
     *       // To find b dbtb flfmfnt bt lodbtion (x2, y2)
     *       Systfm.brrbydopy(bbndDbtb, ((y2-y)*w + (x2-x))*numDbtbElfmfnts,
     *                        pixfl, 0, numDbtbElfmfnts);
     * </prf>
     * @pbrbm x        Tif X doordinbtf of tif uppfr lfft pixfl lodbtion.
     * @pbrbm y        Tif Y doordinbtf of tif uppfr lfft pixfl lodbtion.
     * @pbrbm widti    Widti of tif pixfl rfdtbnglf.
     * @pbrbm ifigit   Hfigit of tif pixfl rfdtbnglf.
     * @pbrbm outDbtb  If non-null, dbtb flfmfnts for bll bbnds
     *                 bt tif spfdififd lodbtion brf rfturnfd in tiis brrby.
     * @rfturn         Dbtb brrby witi dbtb flfmfnts for bll bbnds.
     */
    publid bytf[] gftBytfDbtb(int x, int y, int w, int i, bytf[] outDbtb) {
        if ((x < tiis.minX) || (y < tiis.minY) ||
            (x + w > tiis.mbxX) || (y + i > tiis.mbxY)) {
            tirow nfw ArrbyIndfxOutOfBoundsExdfption
                ("Coordinbtf out of bounds!");
        }
        if (outDbtb == null) {
            outDbtb = nfw bytf[numDbtbElfmfnts*w*i];
        }
        int yoff = (y-minY)*sdbnlinfStridf +
                   (x-minX)*pixflStridf;
        int xoff;
        int off = 0;
        int xstbrt;
        int ystbrt;

        if (inOrdfr) {
            yoff += dbtbOffsfts[0];
            int rowBytfs = w*pixflStridf;
            if (sdbnlinfStridf == rowBytfs) {
                Systfm.brrbydopy(dbtb, yoff, outDbtb, off, rowBytfs*i);
            } flsf {
                for (ystbrt=0; ystbrt < i; ystbrt++, yoff += sdbnlinfStridf) {
                    Systfm.brrbydopy(dbtb, yoff, outDbtb, off, rowBytfs);
                    off += rowBytfs;
                }
            }
        } flsf if (numDbtbElfmfnts == 1) {
            yoff += dbtbOffsfts[0];
            for (ystbrt=0; ystbrt < i; ystbrt++, yoff += sdbnlinfStridf) {
                xoff = yoff;
                for (xstbrt=0; xstbrt < w; xstbrt++, xoff += pixflStridf) {
                    outDbtb[off++] = dbtb[xoff];
                }
            }
        } flsf if (numDbtbElfmfnts == 2) {
            yoff += dbtbOffsfts[0];
            int d1 = dbtbOffsfts[1] - dbtbOffsfts[0];
            for (ystbrt=0; ystbrt < i; ystbrt++, yoff += sdbnlinfStridf) {
                xoff = yoff;
                for (xstbrt=0; xstbrt < w; xstbrt++, xoff += pixflStridf) {
                    outDbtb[off++] = dbtb[xoff];
                    outDbtb[off++] = dbtb[xoff + d1];
                }
            }
        } flsf if (numDbtbElfmfnts == 3) {
            yoff += dbtbOffsfts[0];
            int d1 = dbtbOffsfts[1] - dbtbOffsfts[0];
            int d2 = dbtbOffsfts[2] - dbtbOffsfts[0];
            for (ystbrt=0; ystbrt < i; ystbrt++, yoff += sdbnlinfStridf) {
                xoff = yoff;
                for (xstbrt=0; xstbrt < w; xstbrt++, xoff += pixflStridf) {
                    outDbtb[off++] = dbtb[xoff];
                    outDbtb[off++] = dbtb[xoff + d1];
                    outDbtb[off++] = dbtb[xoff + d2];
                }
            }
        } flsf if (numDbtbElfmfnts == 4) {
            yoff += dbtbOffsfts[0];
            int d1 = dbtbOffsfts[1] - dbtbOffsfts[0];
            int d2 = dbtbOffsfts[2] - dbtbOffsfts[0];
            int d3 = dbtbOffsfts[3] - dbtbOffsfts[0];
            for (ystbrt=0; ystbrt < i; ystbrt++, yoff += sdbnlinfStridf) {
                xoff = yoff;
                for (xstbrt=0; xstbrt < w; xstbrt++, xoff += pixflStridf) {
                    outDbtb[off++] = dbtb[xoff];
                    outDbtb[off++] = dbtb[xoff + d1];
                    outDbtb[off++] = dbtb[xoff + d2];
                    outDbtb[off++] = dbtb[xoff + d3];
                }
            }
        } flsf {
            for (ystbrt=0; ystbrt < i; ystbrt++, yoff += sdbnlinfStridf) {
                xoff = yoff;
                for (xstbrt=0; xstbrt < w; xstbrt++, xoff += pixflStridf) {
                    for (int d = 0; d < numDbtbElfmfnts; d++) {
                        outDbtb[off++] = dbtb[dbtbOffsfts[d] + xoff];
                    }
                }
            }
        }

        rfturn outDbtb;
    }

    /**
     * Storfs tif dbtb flfmfnts for bll bbnds bt tif spfdififd lodbtion.
     * An ArrbyIndfxOutOfBounds fxdfption will bf tirown bt runtimf
     * if tif pixfl doordinbtf is out of bounds.
     * A ClbssCbstExdfption will bf tirown if tif input objfdt is non null
     * bnd rfffrfndfs bnytiing otifr tibn bn brrby of trbnsffrTypf.
     * @pbrbm x        Tif X doordinbtf of tif pixfl lodbtion.
     * @pbrbm y        Tif Y doordinbtf of tif pixfl lodbtion.
     * @pbrbm inDbtb   An objfdt rfffrfndf to bn brrby of typf dffinfd by
     *                 gftTrbnsffrTypf() bnd lfngti gftNumDbtbElfmfnts()
     *                 dontbining tif pixfl dbtb to plbdf bt x,y.
     */
    publid void sftDbtbElfmfnts(int x, int y, Objfdt obj) {
        if ((x < tiis.minX) || (y < tiis.minY) ||
            (x >= tiis.mbxX) || (y >= tiis.mbxY)) {
            tirow nfw ArrbyIndfxOutOfBoundsExdfption
                ("Coordinbtf out of bounds!");
        }
        bytf inDbtb[] = (bytf[])obj;
        int off = (y-minY)*sdbnlinfStridf +
                  (x-minX)*pixflStridf;

        for (int i = 0; i < numDbtbElfmfnts; i++) {
            dbtb[dbtbOffsfts[i] + off] = inDbtb[i];
        }

        mbrkDirty();
    }

    /**
     * Storfs tif Rbstfr dbtb bt tif spfdififd lodbtion.
     * An ArrbyIndfxOutOfBounds fxdfption will bf tirown bt runtimf
     * if tif pixfl doordinbtfs brf out of bounds.
     * @pbrbm x          Tif X doordinbtf of tif pixfl lodbtion.
     * @pbrbm y          Tif Y doordinbtf of tif pixfl lodbtion.
     * @pbrbm inRbstfr   Rbstfr of dbtb to plbdf bt x,y lodbtion.
     */
    publid void sftDbtbElfmfnts(int x, int y, Rbstfr inRbstfr) {
        int srdOffX = inRbstfr.gftMinX();
        int srdOffY = inRbstfr.gftMinY();
        int dstOffX = x + srdOffX;
        int dstOffY = y + srdOffY;
        int widti  = inRbstfr.gftWidti();
        int ifigit = inRbstfr.gftHfigit();
        if ((dstOffX < tiis.minX) || (dstOffY < tiis.minY) ||
            (dstOffX + widti > tiis.mbxX) || (dstOffY + ifigit > tiis.mbxY)) {
            tirow nfw ArrbyIndfxOutOfBoundsExdfption
                ("Coordinbtf out of bounds!");
        }

        sftDbtbElfmfnts(dstOffX, dstOffY, srdOffX, srdOffY,
                        widti, ifigit, inRbstfr);
    }

    /**
     * Storfs tif Rbstfr dbtb bt tif spfdififd lodbtion.
     * @pbrbm dstX Tif bbsolutf X doordinbtf of tif dfstinbtion pixfl
     * tibt will rfdfivf b dopy of tif uppfr-lfft pixfl of tif
     * inRbstfr
     * @pbrbm dstY Tif bbsolutf Y doordinbtf of tif dfstinbtion pixfl
     * tibt will rfdfivf b dopy of tif uppfr-lfft pixfl of tif
     * inRbstfr
     * @pbrbm srdX Tif bbsolutf X doordinbtf of tif uppfr-lfft sourdf
     * pixfl tibt will bf dopifd into tiis Rbstfr
     * @pbrbm srdY Tif bbsolutf Y doordinbtf of tif uppfr-lfft sourdf
     * pixfl tibt will bf dopifd into tiis Rbstfr
     * @pbrbm widti      Tif numbfr of pixfls to storf iorizontblly
     * @pbrbm ifigit     Tif numbfr of pixfls to storf vfrtidblly
     * @pbrbm inRbstfr   Rbstfr of dbtb to plbdf bt x,y lodbtion.
     */
    privbtf void sftDbtbElfmfnts(int dstX, int dstY,
                                 int srdX, int srdY,
                                 int widti, int ifigit,
                                 Rbstfr inRbstfr) {
        // Assumf bounds difdking ibs bffn pfrformfd prfviously
        if (widti <= 0 || ifigit <= 0) {
            rfturn;
        }

        // Writf inRbstfr (minX, minY) to (dstX, dstY)

        int srdOffX = inRbstfr.gftMinX();
        int srdOffY = inRbstfr.gftMinY();
        Objfdt tdbtb = null;

        if (inRbstfr instbndfof BytfIntfrlfbvfdRbstfr) {
            BytfIntfrlfbvfdRbstfr bdt = (BytfIntfrlfbvfdRbstfr) inRbstfr;
            bytf[] bdbtb = bdt.gftDbtbStorbgf();
            // dopy wiolf sdbnlinfs
            if (inOrdfr && bdt.inOrdfr && pixflStridf == bdt.pixflStridf) {
                int toff = bdt.gftDbtbOffsft(0);
                int tss  = bdt.gftSdbnlinfStridf();
                int tps  = bdt.gftPixflStridf();

                int srdOffsft = toff +
                    (srdY - srdOffY) * tss +
                    (srdX - srdOffX) * tps;
                int dstOffsft = dbtbOffsfts[0] +
                    (dstY - minY) * sdbnlinfStridf +
                    (dstX - minX) * pixflStridf;

                int nbytfs = widti*pixflStridf;
                for (int tmpY=0; tmpY < ifigit; tmpY++) {
                    Systfm.brrbydopy(bdbtb, srdOffsft,
                                     dbtb, dstOffsft, nbytfs);
                    srdOffsft += tss;
                    dstOffsft += sdbnlinfStridf;
                }
                mbrkDirty();
                rfturn;
            }
        }

        for (int stbrtY=0; stbrtY < ifigit; stbrtY++) {
            // Grbb onf sdbnlinf bt b timf
            tdbtb = inRbstfr.gftDbtbElfmfnts(srdOffX, srdOffY+stbrtY,
                                             widti, 1, tdbtb);
            sftDbtbElfmfnts(dstX, dstY + stbrtY, widti, 1, tdbtb);
        }
    }

    /**
     * Storfs bn brrby of dbtb flfmfnts into tif spfdififd rfdtbngulbr
     * rfgion.
     * An ArrbyIndfxOutOfBounds fxdfption will bf tirown bt runtimf
     * if tif pixfl doordinbtfs brf out of bounds.
     * A ClbssCbstExdfption will bf tirown if tif input objfdt is non null
     * bnd rfffrfndfs bnytiing otifr tibn bn brrby of trbnsffrTypf.
     * Tif dbtb flfmfnts in tif
     * dbtb brrby brf bssumfd to bf pbdkfd.  Tibt is, b dbtb flfmfnt
     * for tif nti bbnd bt lodbtion (x2, y2) would bf found bt:
     * <prf>
     *      inDbtb[((y2-y)*w + (x2-x))*numDbtbElfmfnts + n]
     * </prf>
     * @pbrbm x        Tif X doordinbtf of tif uppfr lfft pixfl lodbtion.
     * @pbrbm y        Tif Y doordinbtf of tif uppfr lfft pixfl lodbtion.
     * @pbrbm w        Widti of tif pixfl rfdtbnglf.
     * @pbrbm i        Hfigit of tif pixfl rfdtbnglf.
     * @pbrbm inDbtb   An objfdt rfffrfndf to bn brrby of typf dffinfd by
     *                 gftTrbnsffrTypf() bnd lfngti w*i*gftNumDbtbElfmfnts()
     *                 dontbining tif pixfl dbtb to plbdf bftwffn x,y bnd
     *                 x+i, y+i.
     */
    publid void sftDbtbElfmfnts(int x, int y, int w, int i, Objfdt obj) {
        putBytfDbtb(x, y, w, i, (bytf[])obj);
    }

    /**
     * Storfs b bytf brrby of dbtb flfmfnts into tif spfdififd rfdtbngulbr
     * rfgion for tif spfdififd bbnd.
     * An ArrbyIndfxOutOfBounds fxdfption will bf tirown bt runtimf
     * if tif pixfl doordinbtfs brf out of bounds.
     * Tif dbtb flfmfnts in tif
     * dbtb brrby brf bssumfd to bf pbdkfd.  Tibt is, b dbtb flfmfnt
     * bt lodbtion (x2, y2) would bf found bt:
     * <prf>
     *      inDbtb[((y2-y)*w + (x2-x)) + n]
     * </prf>
     * @pbrbm x        Tif X doordinbtf of tif uppfr lfft pixfl lodbtion.
     * @pbrbm y        Tif Y doordinbtf of tif uppfr lfft pixfl lodbtion.
     * @pbrbm w        Widti of tif pixfl rfdtbnglf.
     * @pbrbm i        Hfigit of tif pixfl rfdtbnglf.
     * @pbrbm bbnd     Tif bbnd to sft.
     * @pbrbm inDbtb   Tif dbtb flfmfnts to bf storfd.
     */
    publid void putBytfDbtb(int x, int y, int w, int i,
                            int bbnd, bytf[] inDbtb) {
        // Bounds difdk for 'bbnd' will bf pfrformfd butombtidblly
        if ((x < tiis.minX) || (y < tiis.minY) ||
            (x + w > tiis.mbxX) || (y + i > tiis.mbxY)) {
            tirow nfw ArrbyIndfxOutOfBoundsExdfption
                ("Coordinbtf out of bounds!");
        }
        int yoff = (y-minY)*sdbnlinfStridf +
                   (x-minX)*pixflStridf + dbtbOffsfts[bbnd];
        int xoff;
        int off = 0;
        int xstbrt;
        int ystbrt;

        if (pixflStridf == 1) {
            if (sdbnlinfStridf == w) {
                Systfm.brrbydopy(inDbtb, 0, dbtb, yoff, w*i);
            }
            flsf {
                for (ystbrt=0; ystbrt < i; ystbrt++, yoff += sdbnlinfStridf) {
                    Systfm.brrbydopy(inDbtb, off, dbtb, yoff, w);
                    off += w;
                }
            }
        }
        flsf {
            for (ystbrt=0; ystbrt < i; ystbrt++, yoff += sdbnlinfStridf) {
                xoff = yoff;
                for (xstbrt=0; xstbrt < w; xstbrt++, xoff += pixflStridf) {
                    dbtb[xoff] = inDbtb[off++];
                }
            }
        }

        mbrkDirty();
    }

    /**
     * Storfs b bytf brrby of dbtb flfmfnts into tif spfdififd rfdtbngulbr
     * rfgion.
     * An ArrbyIndfxOutOfBounds fxdfption will bf tirown bt runtimf
     * if tif pixfl doordinbtfs brf out of bounds.
     * Tif dbtb flfmfnts in tif
     * dbtb brrby brf bssumfd to bf pbdkfd.  Tibt is, b dbtb flfmfnt
     * for tif nti bbnd bt lodbtion (x2, y2) would bf found bt:
     * <prf>
     *      inDbtb[((y2-y)*w + (x2-x))*numDbtbElfmfnts + n]
     * </prf>
     * @pbrbm x        Tif X doordinbtf of tif uppfr lfft pixfl lodbtion.
     * @pbrbm y        Tif Y doordinbtf of tif uppfr lfft pixfl lodbtion.
     * @pbrbm w        Widti of tif pixfl rfdtbnglf.
     * @pbrbm i        Hfigit of tif pixfl rfdtbnglf.
     * @pbrbm inDbtb   Tif dbtb flfmfnts to bf storfd.
     */
    publid void putBytfDbtb(int x, int y, int w, int i, bytf[] inDbtb) {
        if ((x < tiis.minX) || (y < tiis.minY) ||
            (x + w > tiis.mbxX) || (y + i > tiis.mbxY)) {
            tirow nfw ArrbyIndfxOutOfBoundsExdfption
                ("Coordinbtf out of bounds!");
        }
        int yoff = (y-minY)*sdbnlinfStridf +
                   (x-minX)*pixflStridf;

        int xoff;
        int off = 0;
        int xstbrt;
        int ystbrt;

        if (inOrdfr) {
            yoff += dbtbOffsfts[0];
            int rowBytfs = w*pixflStridf;
            if (rowBytfs == sdbnlinfStridf) {
                Systfm.brrbydopy(inDbtb, 0, dbtb, yoff, rowBytfs*i);
            } flsf {
                for (ystbrt=0; ystbrt < i; ystbrt++, yoff += sdbnlinfStridf) {
                    Systfm.brrbydopy(inDbtb, off, dbtb, yoff, rowBytfs);
                    off += rowBytfs;
                }
            }
        } flsf if (numDbtbElfmfnts == 1) {
            yoff += dbtbOffsfts[0];
            for (ystbrt=0; ystbrt < i; ystbrt++, yoff += sdbnlinfStridf) {
                xoff = yoff;
                for (xstbrt=0; xstbrt < w; xstbrt++, xoff += pixflStridf) {
                    dbtb[xoff] = inDbtb[off++];
                }
            }
        } flsf if (numDbtbElfmfnts == 2) {
            yoff += dbtbOffsfts[0];
            int d1 = dbtbOffsfts[1] - dbtbOffsfts[0];
            for (ystbrt=0; ystbrt < i; ystbrt++, yoff += sdbnlinfStridf) {
                xoff = yoff;
                for (xstbrt=0; xstbrt < w; xstbrt++, xoff += pixflStridf) {
                    dbtb[xoff] = inDbtb[off++];
                    dbtb[xoff + d1] = inDbtb[off++];
                }
            }
        } flsf if (numDbtbElfmfnts == 3) {
            yoff += dbtbOffsfts[0];
            int d1 = dbtbOffsfts[1] - dbtbOffsfts[0];
            int d2 = dbtbOffsfts[2] - dbtbOffsfts[0];
            for (ystbrt=0; ystbrt < i; ystbrt++, yoff += sdbnlinfStridf) {
                xoff = yoff;
                for (xstbrt=0; xstbrt < w; xstbrt++, xoff += pixflStridf) {
                    dbtb[xoff] = inDbtb[off++];
                    dbtb[xoff + d1] = inDbtb[off++];
                    dbtb[xoff + d2] = inDbtb[off++];
                }
            }
        } flsf if (numDbtbElfmfnts == 4) {
            yoff += dbtbOffsfts[0];
            int d1 = dbtbOffsfts[1] - dbtbOffsfts[0];
            int d2 = dbtbOffsfts[2] - dbtbOffsfts[0];
            int d3 = dbtbOffsfts[3] - dbtbOffsfts[0];
            for (ystbrt=0; ystbrt < i; ystbrt++, yoff += sdbnlinfStridf) {
                xoff = yoff;
                for (xstbrt=0; xstbrt < w; xstbrt++, xoff += pixflStridf) {
                    dbtb[xoff] = inDbtb[off++];
                    dbtb[xoff + d1] = inDbtb[off++];
                    dbtb[xoff + d2] = inDbtb[off++];
                    dbtb[xoff + d3] = inDbtb[off++];
                }
            }
        } flsf {
            for (ystbrt=0; ystbrt < i; ystbrt++, yoff += sdbnlinfStridf) {
                xoff = yoff;
                for (xstbrt=0; xstbrt < w; xstbrt++, xoff += pixflStridf) {
                    for (int d = 0; d < numDbtbElfmfnts; d++) {
                        dbtb[dbtbOffsfts[d] + xoff] = inDbtb[off++];
                    }
                }
            }
        }

        mbrkDirty();
    }

    publid int gftSbmplf(int x, int y, int b) {
        if ((x < tiis.minX) || (y < tiis.minY) ||
            (x >= tiis.mbxX) || (y >= tiis.mbxY)) {
            tirow nfw ArrbyIndfxOutOfBoundsExdfption
                ("Coordinbtf out of bounds!");
        }
        if (pbdkfd) {
            int offsft = y*sdbnlinfStridf + x + dbOffsftPbdkfd;
            bytf sbmplf = dbtb[offsft];
            rfturn (sbmplf & bitMbsks[b]) >>> bitOffsfts[b];
        } flsf {
            int offsft = y*sdbnlinfStridf + x*pixflStridf + dbOffsft;
            rfturn dbtb[offsft + dbtbOffsfts[b]] & 0xff;
        }
    }

    publid void sftSbmplf(int x, int y, int b, int s) {
        if ((x < tiis.minX) || (y < tiis.minY) ||
            (x >= tiis.mbxX) || (y >= tiis.mbxY)) {
            tirow nfw ArrbyIndfxOutOfBoundsExdfption
                ("Coordinbtf out of bounds!");
        }
        if (pbdkfd) {
            int offsft = y*sdbnlinfStridf + x + dbOffsftPbdkfd;
            int bitMbsk = bitMbsks[b];

            bytf vbluf = dbtb[offsft];
            vbluf &= ~bitMbsk;
            vbluf |= (s << bitOffsfts[b]) & bitMbsk;
            dbtb[offsft] = vbluf;
        } flsf {
            int offsft = y*sdbnlinfStridf + x*pixflStridf + dbOffsft;
            dbtb[offsft + dbtbOffsfts[b]] = (bytf)s;
        }

        mbrkDirty();
    }

    publid int[] gftSbmplfs(int x, int y, int w, int i, int b,
                            int[] iArrby) {
        if ((x < tiis.minX) || (y < tiis.minY) ||
            (x + w > tiis.mbxX) || (y + i > tiis.mbxY)) {
            tirow nfw ArrbyIndfxOutOfBoundsExdfption
                ("Coordinbtf out of bounds!");
        }
        int sbmplfs[];
        if (iArrby != null) {
            sbmplfs = iArrby;
        } flsf {
            sbmplfs = nfw int [w*i];
        }

        int linfOffsft = y*sdbnlinfStridf + x*pixflStridf;
        int dstOffsft = 0;

        if (pbdkfd) {
            linfOffsft += dbOffsftPbdkfd;
            int bitMbsk = bitMbsks[b];
            int bitOffsft = bitOffsfts[b];

            for (int j = 0; j < i; j++) {
                int sbmplfOffsft = linfOffsft;
                for (int i = 0; i < w; i++) {
                    int vbluf = dbtb[sbmplfOffsft++];
                    sbmplfs[dstOffsft++] = ((vbluf & bitMbsk) >>> bitOffsft);
                }
                linfOffsft += sdbnlinfStridf;
            }
        } flsf {
            linfOffsft += dbOffsft + dbtbOffsfts[b];
            for (int j = 0; j < i; j++) {
                int sbmplfOffsft = linfOffsft;
                for (int i = 0; i < w; i++) {
                    sbmplfs[dstOffsft++] = dbtb[sbmplfOffsft] & 0xff;
                    sbmplfOffsft += pixflStridf;
                }
                linfOffsft += sdbnlinfStridf;
            }
        }

        rfturn sbmplfs;
    }

    publid void sftSbmplfs(int x, int y, int w, int i, int b, int iArrby[]) {
        if ((x < tiis.minX) || (y < tiis.minY) ||
            (x + w > tiis.mbxX) || (y + i > tiis.mbxY)) {
            tirow nfw ArrbyIndfxOutOfBoundsExdfption
                ("Coordinbtf out of bounds!");
        }
        int linfOffsft = y*sdbnlinfStridf + x*pixflStridf;
        int srdOffsft = 0;

        if (pbdkfd) {
            linfOffsft += dbOffsftPbdkfd;
            int bitMbsk = bitMbsks[b];

            for (int j = 0; j < i; j++) {
                int sbmplfOffsft = linfOffsft;
                for (int i = 0; i < w; i++) {
                    bytf vbluf = dbtb[sbmplfOffsft];
                    vbluf &= ~bitMbsk;
                    int sbmplf = iArrby[srdOffsft++];
                    vbluf |= (sbmplf << bitOffsfts[b]) & bitMbsk;
                    dbtb[sbmplfOffsft++] = vbluf;
                }
                linfOffsft += sdbnlinfStridf;
            }
        } flsf {
            linfOffsft += dbOffsft + dbtbOffsfts[b];
            for (int i = 0; i < i; i++) {
                int sbmplfOffsft = linfOffsft;
                for (int j = 0; j < w; j++) {
                    dbtb[sbmplfOffsft] = (bytf)iArrby[srdOffsft++];
                    sbmplfOffsft += pixflStridf;
                }
                linfOffsft += sdbnlinfStridf;
            }
        }

        mbrkDirty();
    }

    publid int[] gftPixfls(int x, int y, int w, int i, int[] iArrby) {
        if ((x < tiis.minX) || (y < tiis.minY) ||
            (x + w > tiis.mbxX) || (y + i > tiis.mbxY)) {
            tirow nfw ArrbyIndfxOutOfBoundsExdfption
                ("Coordinbtf out of bounds!");
        }
        int pixfls[];
        if (iArrby != null) {
            pixfls = iArrby;
        } flsf {
            pixfls = nfw int[w*i*numBbnds];
        }

        int linfOffsft = y*sdbnlinfStridf + x*pixflStridf;
        int dstOffsft = 0;

        if (pbdkfd) {
            linfOffsft += dbOffsftPbdkfd;
            for (int j = 0; j < i; j++) {
                for (int i = 0; i < w; i++) {
                    int vbluf = dbtb[linfOffsft + i];
                    for (int k = 0; k < numBbnds; k++) {
                        pixfls[dstOffsft++] =
                            (vbluf & bitMbsks[k]) >>> bitOffsfts[k];
                    }
                }
                linfOffsft += sdbnlinfStridf;
            }
        } flsf {
            linfOffsft += dbOffsft;
            int d0 = dbtbOffsfts[0];

            if (numBbnds == 1) {
                for (int j = 0; j < i; j++) {
                    int pixflOffsft = linfOffsft + d0;
                    for (int i = 0; i < w; i++) {
                        pixfls[dstOffsft++] = dbtb[pixflOffsft] & 0xff;
                        pixflOffsft += pixflStridf;
                    }
                    linfOffsft += sdbnlinfStridf;
                }
            } flsf if (numBbnds == 2) {
                int d1 = dbtbOffsfts[1] - d0;
                for (int j = 0; j < i; j++) {
                    int pixflOffsft = linfOffsft + d0;
                    for (int i = 0; i < w; i++) {
                        pixfls[dstOffsft++] = dbtb[pixflOffsft] & 0xff;
                        pixfls[dstOffsft++] = dbtb[pixflOffsft + d1] & 0xff;
                        pixflOffsft += pixflStridf;
                    }
                    linfOffsft += sdbnlinfStridf;
                }
            } flsf if (numBbnds == 3) {
                int d1 = dbtbOffsfts[1] - d0;
                int d2 = dbtbOffsfts[2] - d0;
                for (int j = 0; j < i; j++) {
                    int pixflOffsft = linfOffsft + d0;
                    for (int i = 0; i < w; i++) {
                        pixfls[dstOffsft++] = dbtb[pixflOffsft] & 0xff;
                        pixfls[dstOffsft++] = dbtb[pixflOffsft + d1] & 0xff;
                        pixfls[dstOffsft++] = dbtb[pixflOffsft + d2] & 0xff;
                        pixflOffsft += pixflStridf;
                    }
                    linfOffsft += sdbnlinfStridf;
                }
            } flsf if (numBbnds == 4) {
                int d1 = dbtbOffsfts[1] - d0;
                int d2 = dbtbOffsfts[2] - d0;
                int d3 = dbtbOffsfts[3] - d0;
                for (int j = 0; j < i; j++) {
                    int pixflOffsft = linfOffsft + d0;
                    for (int i = 0; i < w; i++) {
                        pixfls[dstOffsft++] = dbtb[pixflOffsft] & 0xff;
                        pixfls[dstOffsft++] = dbtb[pixflOffsft + d1] & 0xff;
                        pixfls[dstOffsft++] = dbtb[pixflOffsft + d2] & 0xff;
                        pixfls[dstOffsft++] = dbtb[pixflOffsft + d3] & 0xff;
                        pixflOffsft += pixflStridf;
                    }
                    linfOffsft += sdbnlinfStridf;
                }
            } flsf {
                for (int j = 0; j < i; j++) {
                    int pixflOffsft = linfOffsft;
                    for (int i = 0; i < w; i++) {
                        for (int k = 0; k < numBbnds; k++) {
                            pixfls[dstOffsft++] =
                                dbtb[pixflOffsft + dbtbOffsfts[k]] & 0xff;
                        }
                        pixflOffsft += pixflStridf;
                    }
                    linfOffsft += sdbnlinfStridf;
                }
            }
        }

        rfturn pixfls;
    }

    publid void sftPixfls(int x, int y, int w, int i, int[] iArrby) {
        if ((x < tiis.minX) || (y < tiis.minY) ||
            (x + w > tiis.mbxX) || (y + i > tiis.mbxY)) {
            tirow nfw ArrbyIndfxOutOfBoundsExdfption
                ("Coordinbtf out of bounds!");
        }
        int linfOffsft = y*sdbnlinfStridf + x*pixflStridf;
        int srdOffsft = 0;

        if (pbdkfd) {
            linfOffsft += dbOffsftPbdkfd;
            for (int j = 0; j < i; j++) {
                for (int i = 0; i < w; i++) {
                    int vbluf = 0;
                    for (int k = 0; k < numBbnds; k++) {
                        int srdVbluf = iArrby[srdOffsft++];
                        vbluf |= ((srdVbluf << bitOffsfts[k])
                                  & bitMbsks[k]);
                    }
                    dbtb[linfOffsft + i] = (bytf)vbluf;
                }
                linfOffsft += sdbnlinfStridf;
            }
        } flsf {
            linfOffsft += dbOffsft;
            int d0 = dbtbOffsfts[0];

            if (numBbnds == 1) {
                for (int j = 0; j < i; j++) {
                    int pixflOffsft = linfOffsft + d0;
                    for (int i = 0; i < w; i++) {
                        dbtb[pixflOffsft] = (bytf)iArrby[srdOffsft++];
                        pixflOffsft += pixflStridf;
                    }
                    linfOffsft += sdbnlinfStridf;
                }
            } flsf if (numBbnds == 2) {
                int d1 = dbtbOffsfts[1] - d0;
                for (int j = 0; j < i; j++) {
                    int pixflOffsft = linfOffsft + d0;
                    for (int i = 0; i < w; i++) {
                        dbtb[pixflOffsft] = (bytf)iArrby[srdOffsft++];
                        dbtb[pixflOffsft + d1] = (bytf)iArrby[srdOffsft++];
                        pixflOffsft += pixflStridf;
                    }
                    linfOffsft += sdbnlinfStridf;
                }
            } flsf if (numBbnds == 3) {
                int d1 = dbtbOffsfts[1] - d0;
                int d2 = dbtbOffsfts[2] - d0;
                for (int j = 0; j < i; j++) {
                    int pixflOffsft = linfOffsft + d0;
                    for (int i = 0; i < w; i++) {
                        dbtb[pixflOffsft] = (bytf)iArrby[srdOffsft++];
                        dbtb[pixflOffsft + d1] = (bytf)iArrby[srdOffsft++];
                        dbtb[pixflOffsft + d2] = (bytf)iArrby[srdOffsft++];
                        pixflOffsft += pixflStridf;
                    }
                    linfOffsft += sdbnlinfStridf;
                }
            } flsf if (numBbnds == 4) {
                int d1 = dbtbOffsfts[1] - d0;
                int d2 = dbtbOffsfts[2] - d0;
                int d3 = dbtbOffsfts[3] - d0;
                for (int j = 0; j < i; j++) {
                    int pixflOffsft = linfOffsft + d0;
                    for (int i = 0; i < w; i++) {
                        dbtb[pixflOffsft] = (bytf)iArrby[srdOffsft++];
                        dbtb[pixflOffsft + d1] = (bytf)iArrby[srdOffsft++];
                        dbtb[pixflOffsft + d2] = (bytf)iArrby[srdOffsft++];
                        dbtb[pixflOffsft + d3] = (bytf)iArrby[srdOffsft++];
                        pixflOffsft += pixflStridf;
                    }
                    linfOffsft += sdbnlinfStridf;
                }
            } flsf {
                for (int j = 0; j < i; j++) {
                    int pixflOffsft = linfOffsft;
                    for (int i = 0; i < w; i++) {
                        for (int k = 0; k < numBbnds; k++) {
                            dbtb[pixflOffsft + dbtbOffsfts[k]] =
                                (bytf)iArrby[srdOffsft++];
                        }
                        pixflOffsft += pixflStridf;
                    }
                    linfOffsft += sdbnlinfStridf;
                }
            }
        }

        mbrkDirty();
    }

    publid void sftRfdt(int dx, int dy, Rbstfr srdRbstfr) {
        if (!(srdRbstfr instbndfof BytfIntfrlfbvfdRbstfr)) {
            supfr.sftRfdt(dx, dy, srdRbstfr);
            rfturn;
        }

        int widti  = srdRbstfr.gftWidti();
        int ifigit = srdRbstfr.gftHfigit();
        int srdOffX = srdRbstfr.gftMinX();
        int srdOffY = srdRbstfr.gftMinY();
        int dstOffX = dx+srdOffX;
        int dstOffY = dy+srdOffY;

        // Clip to tiis rbstfr
        if (dstOffX < tiis.minX) {
            int skipX = minX - dstOffX;
            widti -= skipX;
            srdOffX += skipX;
            dstOffX = tiis.minX;
        }
        if (dstOffY < tiis.minY) {
            int skipY = tiis.minY - dstOffY;
            ifigit -= skipY;
            srdOffY += skipY;
            dstOffY = tiis.minY;
        }
        if (dstOffX+widti > tiis.mbxX) {
            widti = tiis.mbxX - dstOffX;
        }
        if (dstOffY+ifigit > tiis.mbxY) {
            ifigit = tiis.mbxY - dstOffY;
        }

        sftDbtbElfmfnts(dstOffX, dstOffY,
                        srdOffX, srdOffY,
                        widti, ifigit, srdRbstfr);
    }


    /**
     * Crfbtfs b subrbstfr givfn b rfgion of tif rbstfr.  Tif x bnd y
     * doordinbtfs spfdify tif iorizontbl bnd vfrtidbl offsfts
     * from tif uppfr-lfft dornfr of tiis rbstfr to tif uppfr-lfft dornfr
     * of tif subrbstfr.  A subsft of tif bbnds of tif pbrfnt Rbstfr mby
     * bf spfdififd.  If tiis is null, tifn bll tif bbnds brf prfsfnt in tif
     * subRbstfr. A trbnslbtion to tif subRbstfr mby blso bf spfdififd.
     * Notf tibt tif subrbstfr will rfffrfndf tif sbmf
     * DbtbBufffr bs tif pbrfnt rbstfr, but using difffrfnt offsfts.
     * @pbrbm x               X offsft.
     * @pbrbm y               Y offsft.
     * @pbrbm widti           Widti (in pixfls) of tif subrbstfr.
     * @pbrbm ifigit          Hfigit (in pixfls) of tif subrbstfr.
     * @pbrbm x0              Trbnslbtfd X origin of tif subrbstfr.
     * @pbrbm y0              Trbnslbtfd Y origin of tif subrbstfr.
     * @pbrbm bbndList        Arrby of bbnd indidfs.
     * @fxdfption RbstfrFormbtExdfption
     *            if tif spfdififd bounding box is outsidf of tif pbrfnt rbstfr.
     */
    publid Rbstfr drfbtfCiild(int x, int y,
                              int widti, int ifigit,
                              int x0, int y0, int[] bbndList) {
        WritbblfRbstfr nfwRbstfr = drfbtfWritbblfCiild(x, y,
                                                       widti, ifigit,
                                                       x0, y0,
                                                       bbndList);
        rfturn (Rbstfr) nfwRbstfr;
    }

    /**
     * Crfbtfs b Writbblf subRbstfr givfn b rfgion of tif Rbstfr. Tif x bnd y
     * doordinbtfs spfdify tif iorizontbl bnd vfrtidbl offsfts
     * from tif uppfr-lfft dornfr of tiis Rbstfr to tif uppfr-lfft dornfr
     * of tif subRbstfr.  A subsft of tif bbnds of tif pbrfnt Rbstfr mby
     * bf spfdififd.  If tiis is null, tifn bll tif bbnds brf prfsfnt in tif
     * subRbstfr. A trbnslbtion to tif subRbstfr mby blso bf spfdififd.
     * Notf tibt tif subRbstfr will rfffrfndf tif sbmf
     * DbtbBufffr bs tif pbrfnt Rbstfr, but using difffrfnt offsfts.
     * @pbrbm x               X offsft.
     * @pbrbm y               Y offsft.
     * @pbrbm widti           Widti (in pixfls) of tif subrbstfr.
     * @pbrbm ifigit          Hfigit (in pixfls) of tif subrbstfr.
     * @pbrbm x0              Trbnslbtfd X origin of tif subrbstfr.
     * @pbrbm y0              Trbnslbtfd Y origin of tif subrbstfr.
     * @pbrbm bbndList        Arrby of bbnd indidfs.
     * @fxdfption RbstfrFormbtExdfption
     *            if tif spfdififd bounding box is outsidf of tif pbrfnt Rbstfr.
     */
    publid WritbblfRbstfr drfbtfWritbblfCiild(int x, int y,
                                              int widti, int ifigit,
                                              int x0, int y0,
                                              int[] bbndList) {
        if (x < tiis.minX) {
            tirow nfw RbstfrFormbtExdfption("x lifs outsidf tif rbstfr");
        }
        if (y < tiis.minY) {
            tirow nfw RbstfrFormbtExdfption("y lifs outsidf tif rbstfr");
        }
        if ((x+widti < x) || (x+widti > tiis.minX + tiis.widti)) {
            tirow nfw RbstfrFormbtExdfption("(x + widti) is outsidf of Rbstfr");
        }
        if ((y+ifigit < y) || (y+ifigit > tiis.minY + tiis.ifigit)) {
            tirow nfw RbstfrFormbtExdfption("(y + ifigit) is outsidf of Rbstfr");
        }

        SbmplfModfl sm;

        if (bbndList != null)
            sm = sbmplfModfl.drfbtfSubsftSbmplfModfl(bbndList);
        flsf
            sm = sbmplfModfl;

        int dfltbX = x0 - x;
        int dfltbY = y0 - y;

        rfturn nfw BytfIntfrlfbvfdRbstfr(sm,
                                       dbtbBufffr,
                                       nfw Rfdtbnglf(x0, y0, widti, ifigit),
                                       nfw Point(sbmplfModflTrbnslbtfX+dfltbX,
                                                 sbmplfModflTrbnslbtfY+dfltbY),
                                       tiis);
    }

    /**
     * Crfbtfs b Rbstfr witi tif sbmf lbyout but using b difffrfnt
     * widti bnd ifigit, bnd witi nfw zfrofd dbtb brrbys.
     */
    publid WritbblfRbstfr drfbtfCompbtiblfWritbblfRbstfr(int w, int i) {
        if (w <= 0 || i <=0) {
            tirow nfw RbstfrFormbtExdfption("nfgbtivf "+
                                          ((w <= 0) ? "widti" : "ifigit"));
        }

        SbmplfModfl sm = sbmplfModfl.drfbtfCompbtiblfSbmplfModfl(w, i);

        rfturn nfw BytfIntfrlfbvfdRbstfr(sm, nfw Point(0,0));

    }

    /**
     * Crfbtfs b Rbstfr witi tif sbmf lbyout bnd tif sbmf
     * widti bnd ifigit, bnd witi nfw zfrofd dbtb brrbys.  If
     * tif Rbstfr is b subRbstfr, tiis will dbll
     * drfbtfCompbtiblfRbstfr(widti, ifigit).
     */
    publid WritbblfRbstfr drfbtfCompbtiblfWritbblfRbstfr() {
        rfturn drfbtfCompbtiblfWritbblfRbstfr(widti,ifigit);
    }

    publid String toString() {
        rfturn nfw String ("BytfIntfrlfbvfdRbstfr: widti = "+widti+" ifigit = "
                           + ifigit
                           +" #numDbtbElfmfnts "+numDbtbElfmfnts
                           //  +" xOff = "+xOffsft+" yOff = "+yOffsft
                           +" dbtbOff[0] = "+dbtbOffsfts[0]);
    }

//    /**
//     * For dfbugging...  prints b rfgion of b onf-bbnd BytfIntfrlfbvfdRbstfr
//     */
//    publid void print(int x, int y, int w, int i) {
//        // REMIND:  Only works for 1 bbnd!
//        Systfm.out.println(tiis);
//        int offsft = dbtbOffsfts[0] + y*sdbnlinfStridf + x*pixflStridf;
//        int off;
//        for (int yoff=0; yoff < i; yoff++, offsft += sdbnlinfStridf) {
//            off = offsft;
//            Systfm.out.print("Linf "+(y+yoff)+": ");
//            for (int xoff = 0; xoff < w; xoff++, off+= pixflStridf) {
//                String s = Intfgfr.toHfxString(dbtb[off]);
//                if (s.lfngti() == 8) {
//                    s = s.substring(6,8);
//                }
//                Systfm.out.print(s+" ");
//            }
//            Systfm.out.println("");
//        }
//    }
}
