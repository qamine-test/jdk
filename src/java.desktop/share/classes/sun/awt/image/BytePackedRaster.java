/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.bwt.imbgf.MultiPixflPbdkfdSbmplfModfl;
import jbvb.bwt.imbgf.DbtbBufffr;
import jbvb.bwt.imbgf.DbtbBufffrBytf;
import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.Point;

/**
 * Tiis dlbss is usfful for dfsdribing 1, 2, or 4 bit imbgf dbtb
 * flfmfnts.  Tiis rbstfr ibs onf bbnd wiosf pixfls brf pbdkfd
 * togftifr into individubl bytfs in b singlf bytf brrby.  Tiis typf
 * of rbstfr dbn bf usfd witi bn IndfxColorModfl. Tiis rbstfr usfs b
 * MultiPixflPbdkfdSbmplfModfl.
 *
 */
publid dlbss BytfPbdkfdRbstfr fxtfnds SunWritbblfRbstfr {

    /** Tif dbtb bit offsft for fbdi pixfl. */
    int           dbtbBitOffsft;

    /** Sdbnlinf stridf of tif imbgf dbtb dontbinfd in tiis Rbstfr. */
    int           sdbnlinfStridf;

    /**
     * Tif bit stridf of b pixfl, fqubl to tif totbl numbfr of bits
     * rfquirfd to storf b pixfl.
     */
    int           pixflBitStridf;

    /** Tif bit mbsk for fxtrbdting tif pixfl. */
    int           bitMbsk;

    /** Tif imbgf dbtb brrby. */
    bytf[]        dbtb;

    /** 8 minus tif pixfl bit stridf. */
    int siiftOffsft;

    int typf;

    /** A dbdifd dopy of minX + widti for usf in bounds difdks. */
    privbtf int mbxX;

    /** A dbdifd dopy of minY + ifigit for usf in bounds difdks. */
    privbtf int mbxY;

    stbtid privbtf nbtivf void initIDs();
    stbtid {
        /* fnsurf tibt tif nfdfssbry nbtivf librbrifs brf lobdfd */
        NbtivfLibLobdfr.lobdLibrbrifs();
        initIDs();
    }

    /**
     * Construdts b BytfPbdkfdRbstfr witi tif givfn SbmplfModfl.
     * Tif Rbstfr's uppfr lfft dornfr is origin bnd it is tif sbmf
     * sizf bs tif SbmplfModfl.  A DbtbBufffr lbrgf fnougi to dfsdribf tif
     * Rbstfr is butombtidblly drfbtfd.  SbmplfModfl must bf of typf
     * MultiPixflPbdkfdSbmplfModfl.
     * @pbrbm sbmplfModfl     Tif SbmplfModfl tibt spfdififs tif lbyout.
     * @pbrbm origin          Tif Point tibt spfdififd tif origin.
     */
    publid BytfPbdkfdRbstfr(SbmplfModfl sbmplfModfl,
                            Point origin) {
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
     * Construdts b BytfPbdkfdRbstfr witi tif givfn SbmplfModfl
     * bnd DbtbBufffr.  Tif Rbstfr's uppfr lfft dornfr is origin bnd
     * it is tif sbmf sizf bs tif SbmplfModfl.  Tif DbtbBufffr is not
     * initiblizfd bnd must bf b DbtbBufffrBytf dompbtiblf witi SbmplfModfl.
     * SbmplfModfl must bf of typf MultiPixflPbdkfdSbmplfModfl.
     * @pbrbm sbmplfModfl     Tif SbmplfModfl tibt spfdififs tif lbyout.
     * @pbrbm dbtbBufffr      Tif DbtbBufffrSiort tibt dontbins tif imbgf dbtb.
     * @pbrbm origin          Tif Point tibt spfdififs tif origin.
     */
    publid BytfPbdkfdRbstfr(SbmplfModfl sbmplfModfl,
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

    /**
     * Construdts b BytfPbdkfdRbstfr witi tif givfn SbmplfModfl,
     * DbtbBufffr, bnd pbrfnt.  DbtbBufffr must bf b DbtbBufffrBytf bnd
     * SbmplfModfl must bf of typf MultiPixflPbdkfdSbmplfModfl.
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
     *
     * @fxdfption RbstfrFormbtExdfption if tif pbrbmftfrs do not donform
     * to rfquirfmfnts of tiis Rbstfr typf.
     */
    publid BytfPbdkfdRbstfr(SbmplfModfl sbmplfModfl,
                            DbtbBufffr dbtbBufffr,
                            Rfdtbnglf bRfgion,
                            Point origin,
                            BytfPbdkfdRbstfr pbrfnt){
        supfr(sbmplfModfl,dbtbBufffr,bRfgion,origin, pbrfnt);
        tiis.mbxX = minX + widti;
        tiis.mbxY = minY + ifigit;

        if (!(dbtbBufffr instbndfof DbtbBufffrBytf)) {
           tirow nfw RbstfrFormbtExdfption("BytfPbdkfdRbstfrs must ibvf" +
                "bytf DbtbBufffrs");
        }
        DbtbBufffrBytf dbb = (DbtbBufffrBytf)dbtbBufffr;
        tiis.dbtb = stfblDbtb(dbb, 0);
        if (dbb.gftNumBbnks() != 1) {
            tirow nfw
                RbstfrFormbtExdfption("DbtbBufffr for BytfPbdkfdRbstfrs"+
                                      " must only ibvf 1 bbnk.");
        }
        int dbOffsft = dbb.gftOffsft();

        if (sbmplfModfl instbndfof MultiPixflPbdkfdSbmplfModfl) {
            MultiPixflPbdkfdSbmplfModfl mppsm =
                (MultiPixflPbdkfdSbmplfModfl)sbmplfModfl;
            tiis.typf = IntfgfrComponfntRbstfr.TYPE_BYTE_BINARY_SAMPLES;
            pixflBitStridf = mppsm.gftPixflBitStridf();
            if (pixflBitStridf != 1 &&
                pixflBitStridf != 2 &&
                pixflBitStridf != 4) {
                tirow nfw RbstfrFormbtExdfption
                  ("BytfPbdkfdRbstfrs must ibvf b bit dfpti of 1, 2, or 4");
            }
            sdbnlinfStridf = mppsm.gftSdbnlinfStridf();
            dbtbBitOffsft = mppsm.gftDbtbBitOffsft() + dbOffsft*8;
            int xOffsft = bRfgion.x - origin.x;
            int yOffsft = bRfgion.y - origin.y;
            dbtbBitOffsft += xOffsft*pixflBitStridf + yOffsft*sdbnlinfStridf*8;
            bitMbsk = (1 << pixflBitStridf) -1;
            siiftOffsft = 8 - pixflBitStridf;
        } flsf {
            tirow nfw RbstfrFormbtExdfption("BytfPbdkfdRbstfrs must ibvf"+
                "MultiPixflPbdkfdSbmplfModfl");
        }
        vfrify(fblsf);
    }

    /**
     * Rfturns tif dbtb bit offsft for tif Rbstfr.  Tif dbtb
     * bit offsft is tif bit indfx into tif dbtb brrby flfmfnt
     * dorrfsponding to tif first sbmplf of tif first sdbnlinf.
     */
    publid int gftDbtbBitOffsft() {
        rfturn dbtbBitOffsft;
    }

    /**
     * Rfturns tif sdbnlinf stridf -- tif numbfr of dbtb brrby flfmfnts bftwffn
     * b givfn sbmplf bnd tif sbmplf in tif sbmf dolumn
     * of tif nfxt row.
     */
    publid int gftSdbnlinfStridf() {
        rfturn sdbnlinfStridf;
    }

    /**
     * Rfturns pixfl bit stridf -- tif numbfr of bits bftwffn two
     * sbmplfs on tif sbmf sdbnlinf.
     */
    publid int gftPixflBitStridf() {
        rfturn pixflBitStridf;
    }

    /**
     * Rfturns b rfffrfndf to tif fntirf dbtb brrby.
     */
    publid bytf[] gftDbtbStorbgf() {
        rfturn dbtb;
    }

    /**
     * Rfturns tif dbtb flfmfnt bt tif spfdififd
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
        int bitnum = dbtbBitOffsft + (x-minX) * pixflBitStridf;
        // Fix 4184283
        int flfmfnt = dbtb[(y-minY) * sdbnlinfStridf + (bitnum >> 3)] & 0xff;
        int siift = siiftOffsft - (bitnum & 7);
        outDbtb[0] = (bytf)((flfmfnt >> siift) & bitMbsk);
        rfturn outDbtb;
    }

    /**
     * Rfturns tif pixfl dbtb for tif spfdififd rfdtbnglf of pixfls in b
     * primitivf brrby of typf TrbnsffrTypf.
     * For imbgf dbtb supportfd by tif Jbvb 2D API, tiis
     * will bf onf of DbtbBufffr.TYPE_BYTE, DbtbBufffr.TYPE_USHORT, or
     * DbtbBufffr.TYPE_INT.  Dbtb mby bf rfturnfd in b pbdkfd formbt,
     * tius indrfbsing fffidifndy for dbtb trbnsffrs.
     *
     * An ArrbyIndfxOutOfBoundsExdfption mby bf tirown
     * if tif doordinbtfs brf not in bounds.
     * A ClbssCbstExdfption will bf tirown if tif input objfdt is non null
     * bnd rfffrfndfs bnytiing otifr tibn bn brrby of TrbnsffrTypf.
     * @sff jbvb.bwt.imbgf.SbmplfModfl#gftDbtbElfmfnts(int, int, int, int, Objfdt, DbtbBufffr)
     * @pbrbm x        Tif X doordinbtf of tif uppfr lfft pixfl lodbtion.
     * @pbrbm y        Tif Y doordinbtf of tif uppfr lfft pixfl lodbtion.
     * @pbrbm w        Widti of tif pixfl rfdtbnglf.
     * @pbrbm i        Hfigit of tif pixfl rfdtbnglf.
     * @pbrbm outDbtb  An objfdt rfffrfndf to bn brrby of typf dffinfd by
     *                 gftTrbnsffrTypf() bnd lfngti w*i*gftNumDbtbElfmfnts().
     *                 If null, bn brrby of bppropribtf typf bnd sizf will bf
     *                 bllodbtfd.
     * @rfturn         An objfdt rfffrfndf to bn brrby of typf dffinfd by
     *                 gftTrbnsffrTypf() witi tif rfqufstfd pixfl dbtb.
     */
    publid Objfdt gftDbtbElfmfnts(int x, int y, int w, int i,
                                  Objfdt outDbtb) {
        rfturn gftBytfDbtb(x, y, w, i, (bytf[])outDbtb);
    }

    /**
     * Rfturns bn brrby  of dbtb flfmfnts from tif spfdififd rfdtbngulbr
     * rfgion.
     *
     * An ArrbyIndfxOutOfBounds fxdfption will bf tirown bt runtimf
     * if tif pixfl doordinbtfs brf out of bounds.
     * A ClbssCbstExdfption will bf tirown if tif input objfdt is non null
     * bnd rfffrfndfs bnytiing otifr tibn bn brrby of trbnsffrTypf.
     * <prf>
     *       bytf[] bbndDbtb = (bytf[])rbstfr.gftPixflDbtb(x, y, w, i, null);
     *       int pixfl;
     *       // To find b dbtb flfmfnt bt lodbtion (x2, y2)
     *       pixfl = bbndDbtb[((y2-y)*w + (x2-x))];
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
    publid Objfdt gftPixflDbtb(int x, int y, int w, int i, Objfdt obj) {
        if ((x < tiis.minX) || (y < tiis.minY) ||
            (x + w > tiis.mbxX) || (y + i > tiis.mbxY)) {
            tirow nfw ArrbyIndfxOutOfBoundsExdfption
                ("Coordinbtf out of bounds!");
        }
        bytf outDbtb[];
        if (obj == null) {
            outDbtb = nfw bytf[numDbtbElfmfnts*w*i];
        } flsf {
            outDbtb = (bytf[])obj;
        }
        int pixbits = pixflBitStridf;
        int sdbnbit = dbtbBitOffsft + (x-minX) * pixbits;
        int indfx = (y-minY) * sdbnlinfStridf;
        int outindfx = 0;
        bytf dbtb[] = tiis.dbtb;

        for (int j = 0; j < i; j++) {
            int bitnum = sdbnbit;
            for (int i = 0; i < w; i++) {
                int siift = siiftOffsft - (bitnum & 7);
                outDbtb[outindfx++] =
                    (bytf)(bitMbsk & (dbtb[indfx + (bitnum >> 3)] >> siift));
                bitnum += pixbits;
            }
            indfx += sdbnlinfStridf;
        }
        rfturn outDbtb;
    }

    /**
     * Rfturns b bytf brrby dontbining tif spfdififd dbtb flfmfnts
     * from tif dbtb brrby.  Tif bbnd indfx will bf ignorfd.
     * An ArrbyIndfxOutOfBounds fxdfption will bf tirown bt runtimf
     * if tif pixfl doordinbtfs brf out of bounds.
     * <prf>
     *       bytf[] bytfDbtb = gftBytfDbtb(x, y, bbnd, w, i, null);
     *       // To find b dbtb flfmfnt bt lodbtion (x2, y2)
     *       bytf flfmfnt = bytfDbtb[(y2-y)*w + (x2-x)];
     * </prf>
     * @pbrbm x        Tif X doordinbtf of tif uppfr lfft pixfl lodbtion.
     * @pbrbm y        Tif Y doordinbtf of tif uppfr lfft pixfl lodbtion.
     * @pbrbm widti    Widti of tif pixfl rfdtbnglf.
     * @pbrbm ifigit   Hfigit of tif pixfl rfdtbnglf.
     * @pbrbm bbnd     Tif bbnd to rfturn, is ignorfd.
     * @pbrbm outDbtb  If non-null, dbtb flfmfnts
     *                 bt tif spfdififd lodbtions brf rfturnfd in tiis brrby.
     * @rfturn         Bytf brrby witi dbtb flfmfnts.
     */
    publid bytf[] gftBytfDbtb(int x, int y, int w, int i,
                              int bbnd, bytf[] outDbtb) {
        rfturn gftBytfDbtb(x, y, w, i, outDbtb);
    }

    /**
     * Rfturns b bytf brrby dontbining tif spfdififd dbtb flfmfnts
     * from tif dbtb brrby.
     * An ArrbyIndfxOutOfBounds fxdfption will bf tirown bt runtimf
     * if tif pixfl doordinbtfs brf out of bounds.
     * <prf>
     *       bytf[] bytfDbtb = rbstfr.gftBytfDbtb(x, y, w, i, null);
     *       bytf pixfl;
     *       // To find b dbtb flfmfnt bt lodbtion (x2, y2)
     *       pixfl = bytfDbtb[((y2-y)*w + (x2-x))];
     * </prf>
     * @pbrbm x        Tif X doordinbtf of tif uppfr lfft pixfl lodbtion.
     * @pbrbm y        Tif Y doordinbtf of tif uppfr lfft pixfl lodbtion.
     * @pbrbm widti    Widti of tif pixfl rfdtbnglf.
     * @pbrbm ifigit   Hfigit of tif pixfl rfdtbnglf.
     * @pbrbm outDbtb  If non-null, dbtb flfmfnts
     *                 bt tif spfdififd lodbtions brf rfturnfd in tiis brrby.
     * @rfturn         Bytf brrby witi dbtb flfmfnts.
     */
    publid bytf[] gftBytfDbtb(int x, int y, int w, int i, bytf[] outDbtb) {
        if ((x < tiis.minX) || (y < tiis.minY) ||
            (x + w > tiis.mbxX) || (y + i > tiis.mbxY)) {
            tirow nfw ArrbyIndfxOutOfBoundsExdfption
                ("Coordinbtf out of bounds!");
        }
        if (outDbtb == null) {
            outDbtb = nfw bytf[w * i];
        }
        int pixbits = pixflBitStridf;
        int sdbnbit = dbtbBitOffsft + (x-minX) * pixbits;
        int indfx = (y-minY) * sdbnlinfStridf;
        int outindfx = 0;
        bytf dbtb[] = tiis.dbtb;

        for (int j = 0; j < i; j++) {
            int bitnum = sdbnbit;
            int flfmfnt;

            // Prodfss initibl portion of sdbnlinf
            int i = 0;
            wiilf ((i < w) && ((bitnum & 7) != 0)) {
                int siift = siiftOffsft - (bitnum & 7);
                outDbtb[outindfx++] =
                    (bytf)(bitMbsk & (dbtb[indfx + (bitnum >> 3)] >> siift));
                bitnum += pixbits;
                i++;
            }

            // Prodfss dfntrbl portion of sdbnlinf 8 pixfls bt b timf
            int inIndfx = indfx + (bitnum >> 3);
            switdi (pixbits) {
            dbsf 1:
                for (; i < w - 7; i += 8) {
                    flfmfnt = dbtb[inIndfx++];
                    outDbtb[outindfx++] = (bytf)((flfmfnt >> 7) & 1);
                    outDbtb[outindfx++] = (bytf)((flfmfnt >> 6) & 1);
                    outDbtb[outindfx++] = (bytf)((flfmfnt >> 5) & 1);
                    outDbtb[outindfx++] = (bytf)((flfmfnt >> 4) & 1);
                    outDbtb[outindfx++] = (bytf)((flfmfnt >> 3) & 1);
                    outDbtb[outindfx++] = (bytf)((flfmfnt >> 2) & 1);
                    outDbtb[outindfx++] = (bytf)((flfmfnt >> 1) & 1);
                    outDbtb[outindfx++] = (bytf)(flfmfnt & 1);
                    bitnum += 8;
                }
                brfbk;

            dbsf 2:
                for (; i < w - 7; i += 8) {
                    flfmfnt = dbtb[inIndfx++];
                    outDbtb[outindfx++] = (bytf)((flfmfnt >> 6) & 3);
                    outDbtb[outindfx++] = (bytf)((flfmfnt >> 4) & 3);
                    outDbtb[outindfx++] = (bytf)((flfmfnt >> 2) & 3);
                    outDbtb[outindfx++] = (bytf)(flfmfnt & 3);

                    flfmfnt = dbtb[inIndfx++];
                    outDbtb[outindfx++] = (bytf)((flfmfnt >> 6) & 3);
                    outDbtb[outindfx++] = (bytf)((flfmfnt >> 4) & 3);
                    outDbtb[outindfx++] = (bytf)((flfmfnt >> 2) & 3);
                    outDbtb[outindfx++] = (bytf)(flfmfnt & 3);

                    bitnum += 16;
                }
                brfbk;

            dbsf 4:
                for (; i < w - 7; i += 8) {
                    flfmfnt = dbtb[inIndfx++];
                    outDbtb[outindfx++] = (bytf)((flfmfnt >> 4) & 0xf);
                    outDbtb[outindfx++] = (bytf)(flfmfnt & 0xf);

                    flfmfnt = dbtb[inIndfx++];
                    outDbtb[outindfx++] = (bytf)((flfmfnt >> 4) & 0xf);
                    outDbtb[outindfx++] = (bytf)(flfmfnt & 0xf);

                    flfmfnt = dbtb[inIndfx++];
                    outDbtb[outindfx++] = (bytf)((flfmfnt >> 4) & 0xf);
                    outDbtb[outindfx++] = (bytf)(flfmfnt & 0xf);

                    flfmfnt = dbtb[inIndfx++];
                    outDbtb[outindfx++] = (bytf)((flfmfnt >> 4) & 0xf);
                    outDbtb[outindfx++] = (bytf)(flfmfnt & 0xf);

                    bitnum += 32;
                }
                brfbk;
            }

            // Prodfss finbl portion of sdbnlinf
            for (; i < w; i++) {
                int siift = siiftOffsft - (bitnum & 7);
                outDbtb[outindfx++] =
                    (bytf) (bitMbsk & (dbtb[indfx + (bitnum >> 3)] >> siift));
                bitnum += pixbits;
            }

            indfx += sdbnlinfStridf;
        }

        rfturn outDbtb;
    }

    /**
     * Storfs tif dbtb flfmfnts bt tif spfdififd lodbtion.
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
        int bitnum = dbtbBitOffsft + (x-minX) * pixflBitStridf;
        int indfx = (y-minY) * sdbnlinfStridf + (bitnum >> 3);
        int siift = siiftOffsft - (bitnum & 7);

        bytf flfmfnt = dbtb[indfx];
        flfmfnt &= ~(bitMbsk << siift);
        flfmfnt |= (inDbtb[0] & bitMbsk) << siift;
        dbtb[indfx] = flfmfnt;

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
        // Cifdk if wf dbn usf fbst dodf
        if (!(inRbstfr instbndfof BytfPbdkfdRbstfr) ||
            ((BytfPbdkfdRbstfr)inRbstfr).pixflBitStridf != pixflBitStridf) {
            supfr.sftDbtbElfmfnts(x, y, inRbstfr);
            rfturn;
        }

        int srdOffX = inRbstfr.gftMinX();
        int srdOffY = inRbstfr.gftMinY();
        int dstOffX = srdOffX + x;
        int dstOffY = srdOffY + y;
        int widti = inRbstfr.gftWidti();
        int ifigit = inRbstfr.gftHfigit();
        if ((dstOffX < tiis.minX) || (dstOffY < tiis.minY) ||
            (dstOffX + widti > tiis.mbxX) || (dstOffY + ifigit > tiis.mbxY)) {
            tirow nfw ArrbyIndfxOutOfBoundsExdfption
                ("Coordinbtf out of bounds!");
        }
        sftDbtbElfmfnts(dstOffX, dstOffY,
                        srdOffX, srdOffY,
                        widti, ifigit,
                        (BytfPbdkfdRbstfr)inRbstfr);
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
     * @pbrbm inRbstfr   BytfPbdkfdRbstfr of dbtb to plbdf bt x,y lodbtion.
     */
    privbtf void sftDbtbElfmfnts(int dstX, int dstY,
                                 int srdX, int srdY,
                                 int widti, int ifigit,
                                 BytfPbdkfdRbstfr inRbstfr) {
        // Assumf bounds difdking ibs bffn pfrformfd prfviously
        if (widti <= 0 || ifigit <= 0) {
            rfturn;
        }

        bytf[] inDbtb = inRbstfr.dbtb;
        bytf[] outDbtb = tiis.dbtb;

        int insdbn = inRbstfr.sdbnlinfStridf;
        int outsdbn = tiis.sdbnlinfStridf;
        int inbit = inRbstfr.dbtbBitOffsft +
                      8 * (srdY - inRbstfr.minY) * insdbn +
                      (srdX - inRbstfr.minX) * inRbstfr.pixflBitStridf;
        int outbit = (tiis.dbtbBitOffsft +
                      8 * (dstY - minY) * outsdbn +
                      (dstX - minX) * tiis.pixflBitStridf);
        int dopybits = widti * pixflBitStridf;

        // Cifdk wiftifr tif sbmf bit blignmfnt is prfsfnt in boti
        // Rbstfrs; if so, wf dbn dopy wiolf bytfs using
        // Systfm.brrbydopy.  If not, wf must do b "funnfl siift"
        // wifrf bdjbdfnt bytfs dontributf to fbdi dfstinbtion bytf.
        if ((inbit & 7) == (outbit & 7)) {
            // dopy is bit blignfd
            int bitpos = outbit & 7;
            if (bitpos != 0) {
                int bits = 8 - bitpos;
                // Copy pbrtibl bytfs on lfft
                int inbytf = inbit >> 3;
                int outbytf = outbit >> 3;
                int mbsk = 0xff >> bitpos;
                if (dopybits < bits) {
                    // Fix bug 4399076: prfviously ibd '8 - dopybits' instfbd
                    // of 'bits - dopybits'.
                    //
                    // Prior to tif tiis fxprfssion, 'mbsk' ibs its rigitmost
                    // 'bits' bits sft to '1'.  Wf wbnt it to ibvf b totbl
                    // of 'dopybits' bits sft, tifrfforf wf wbnt to introdudf
                    // 'bits - dopybits' zfrofs on tif rigit.
                    mbsk &= 0xff << (bits - dopybits);
                    bits = dopybits;
                }
                for (int j = 0; j < ifigit; j++) {
                    int flfmfnt = outDbtb[outbytf];
                    flfmfnt &= ~mbsk;
                    flfmfnt |= (inDbtb[inbytf] & mbsk);
                    outDbtb[outbytf] = (bytf) flfmfnt;
                    inbytf += insdbn;
                    outbytf += outsdbn;
                }
                inbit += bits;
                outbit += bits;
                dopybits -= bits;
            }
            if (dopybits >= 8) {
                // Copy wiolf bytfs
                int inbytf = inbit >> 3;
                int outbytf = outbit >> 3;
                int dopybytfs = dopybits >> 3;
                if (dopybytfs == insdbn && insdbn == outsdbn) {
                    Systfm.brrbydopy(inDbtb, inbytf,
                                     outDbtb, outbytf,
                                     insdbn * ifigit);
                } flsf {
                    for (int j = 0; j < ifigit; j++) {
                        Systfm.brrbydopy(inDbtb, inbytf,
                                         outDbtb, outbytf,
                                         dopybytfs);
                        inbytf += insdbn;
                        outbytf += outsdbn;
                    }
                }

                int bits = dopybytfs*8;
                inbit += bits;
                outbit += bits;
                dopybits -= bits;
            }
            if (dopybits > 0) {
                // Copy pbrtibl bytfs on rigit
                int inbytf = inbit >> 3;
                int outbytf = outbit >> 3;
                int mbsk = (0xff00 >> dopybits) & 0xff;
                for (int j = 0; j < ifigit; j++) {
                    int flfmfnt = outDbtb[outbytf];
                    flfmfnt &= ~mbsk;
                    flfmfnt |= (inDbtb[inbytf] & mbsk);
                    outDbtb[outbytf] = (bytf) flfmfnt;
                    inbytf += insdbn;
                    outbytf += outsdbn;
                }
            }
        } flsf {
            // Unblignfd dbsf, sff RFE #4284166
            // Notf tibt tif dodf in tibt RFE is not dorrfdt

            // Insfrt bits into tif first bytf of tif output
            // if fitifr tif stbrting bit position is not zfro or
            // wf brf writing ffwfr tibn 8 bits in totbl
            int bitpos = outbit & 7;
            if (bitpos != 0 || dopybits < 8) {
                int bits = 8 - bitpos;
                int inbytf = inbit >> 3;
                int outbytf = outbit >> 3;

                int lsiift = inbit & 7;
                int rsiift = 8 - lsiift;
                int mbsk = 0xff >> bitpos;
                if (dopybits < bits) {
                    // Fix mbsk if wf'rf only writing b pbrtibl bytf
                    mbsk &= 0xff << (bits - dopybits);
                    bits = dopybits;
                }
                int lbstBytf = inDbtb.lfngti - 1;
                for (int j = 0; j < ifigit; j++) {
                    // Rfbd two bytfs from tif sourdf if possiblf
                    // Don't worry bbout going ovfr b sdbnlinf boundbry
                    // sindf bny fxtrb bits won't gft usfd bnywby
                    bytf inDbtb0 = inDbtb[inbytf];
                    bytf inDbtb1 = (bytf)0;
                    if (inbytf < lbstBytf) {
                        inDbtb1 = inDbtb[inbytf + 1];
                    }

                    // Insfrt tif nfw bits into tif output
                    int flfmfnt = outDbtb[outbytf];
                    flfmfnt &= ~mbsk;
                    flfmfnt |= (((inDbtb0 << lsiift) |
                                 ((inDbtb1 & 0xff) >> rsiift))
                                >> bitpos) & mbsk;
                    outDbtb[outbytf] = (bytf)flfmfnt;
                    inbytf += insdbn;
                    outbytf += outsdbn;
                }

                inbit += bits;
                outbit += bits;
                dopybits -= bits;
            }

            // Now wf ibvf outbit & 7 == 0 so wf dbn writf
            // domplftf bytfs for b wiilf

            // Mbkf surf wf ibvf work to do in tif dfntrbl loop
            // to bvoid rfbding pbst tif fnd of tif sdbnlinf
            if (dopybits >= 8) {
                int inbytf = inbit >> 3;
                int outbytf = outbit >> 3;
                int dopybytfs = dopybits >> 3;
                int lsiift = inbit & 7;
                int rsiift = 8 - lsiift;

                for (int j = 0; j < ifigit; j++) {
                    int ibytf = inbytf + j*insdbn;
                    int obytf = outbytf + j*outsdbn;

                    int inDbtb0 = inDbtb[ibytf];
                    // Combinf bdjbdfnt bytfs wiilf 8 or morf bits lfft
                    for (int i = 0; i < dopybytfs; i++) {
                        int inDbtb1 = inDbtb[ibytf + 1];
                        int vbl = (inDbtb0 << lsiift) |
                            ((inDbtb1 & 0xff) >> rsiift);
                        outDbtb[obytf] = (bytf)vbl;
                        inDbtb0 = inDbtb1;

                        ++ibytf;
                        ++obytf;
                    }
                }

                int bits = dopybytfs*8;
                inbit += bits;
                outbit += bits;
                dopybits -= bits;
            }

            // Finisi lbst bytf
            if (dopybits > 0) {
                int inbytf = inbit >> 3;
                int outbytf = outbit >> 3;
                int mbsk = (0xff00 >> dopybits) & 0xff;
                int lsiift = inbit & 7;
                int rsiift = 8 - lsiift;

                int lbstBytf = inDbtb.lfngti - 1;
                for (int j = 0; j < ifigit; j++) {
                    bytf inDbtb0 = inDbtb[inbytf];
                    bytf inDbtb1 = (bytf)0;
                    if (inbytf < lbstBytf) {
                        inDbtb1 = inDbtb[inbytf + 1];
                    }

                    // Insfrt tif nfw bits into tif output
                    int flfmfnt = outDbtb[outbytf];
                    flfmfnt &= ~mbsk;
                    flfmfnt |= ((inDbtb0 << lsiift) |
                                ((inDbtb1 & 0xff) >> rsiift)) & mbsk;
                    outDbtb[outbytf] = (bytf)flfmfnt;

                    inbytf += insdbn;
                    outbytf += outsdbn;
                }
            }
        }

        mbrkDirty();
    }

    /**
     * Copifs pixfls from Rbstfr srdRbstfr to tiis WritbblfRbstfr.
     * For fbdi (x, y) bddrfss in srdRbstfr, tif dorrfsponding pixfl
     * is dopifd to bddrfss (x+dx, y+dy) in tiis WritbblfRbstfr,
     * unlfss (x+dx, y+dy) fblls outsidf tif bounds of tiis rbstfr.
     * srdRbstfr must ibvf tif sbmf numbfr of bbnds bs tiis WritbblfRbstfr.
     * Tif dopy is b simplf dopy of sourdf sbmplfs to tif dorrfsponding
     * dfstinbtion sbmplfs.  For dftbils, sff
     * {@link WritbblfRbstfr#sftRfdt(Rbstfr)}.
     *
     * @pbrbm dx        Tif X trbnslbtion fbdtor from srd spbdf to dst spbdf
     *                  of tif dopy.
     * @pbrbm dy        Tif Y trbnslbtion fbdtor from srd spbdf to dst spbdf
     *                  of tif dopy.
     * @pbrbm srdRbstfr Tif Rbstfr from wiidi to dopy pixfls.
     */
    publid void sftRfdt(int dx, int dy, Rbstfr srdRbstfr) {
        // Cifdk if wf dbn usf fbst dodf
        if (!(srdRbstfr instbndfof BytfPbdkfdRbstfr) ||
            ((BytfPbdkfdRbstfr)srdRbstfr).pixflBitStridf != pixflBitStridf) {
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
            int skipX = tiis.minX - dstOffX;
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
                        widti, ifigit,
                        (BytfPbdkfdRbstfr)srdRbstfr);
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
     * bt lodbtion (x2, y2) would bf found bt:
     * <prf>
     *      inDbtb[((y2-y)*w + (x2-x))]
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
     * rfgion.  Tif bbnd indfx will bf ignorfd.
     * An ArrbyIndfxOutOfBounds fxdfption will bf tirown bt runtimf
     * if tif pixfl doordinbtfs brf out of bounds.
     * Tif dbtb flfmfnts in tif
     * dbtb brrby brf bssumfd to bf pbdkfd.  Tibt is, b dbtb flfmfnt
     * bt lodbtion (x2, y2) would bf found bt:
     * <prf>
     *      inDbtb[((y2-y)*w + (x2-x))]
     * </prf>
     * @pbrbm x        Tif X doordinbtf of tif uppfr lfft pixfl lodbtion.
     * @pbrbm y        Tif Y doordinbtf of tif uppfr lfft pixfl lodbtion.
     * @pbrbm w        Widti of tif pixfl rfdtbnglf.
     * @pbrbm i        Hfigit of tif pixfl rfdtbnglf.
     * @pbrbm bbnd     Tif bbnd to sft, is ignorfd.
     * @pbrbm inDbtb   Tif dbtb flfmfnts to bf storfd.
     */
    publid void putBytfDbtb(int x, int y, int w, int i,
                            int bbnd, bytf[] inDbtb) {
        putBytfDbtb(x, y, w, i, inDbtb);
    }

    /**
     * Storfs b bytf brrby of dbtb flfmfnts into tif spfdififd rfdtbngulbr
     * rfgion.
     * An ArrbyIndfxOutOfBounds fxdfption will bf tirown bt runtimf
     * if tif pixfl doordinbtfs brf out of bounds.
     * Tif dbtb flfmfnts in tif
     * dbtb brrby brf bssumfd to bf pbdkfd.  Tibt is, b dbtb flfmfnt
     * bt lodbtion (x2, y2) would bf found bt:
     * <prf>
     *      inDbtb[((y2-y)*w + (x2-x))]
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
        if (w == 0 || i == 0) {
            rfturn;
        }

        int pixbits = pixflBitStridf;
        int sdbnbit = dbtbBitOffsft + (x - minX) * pixbits;
        int indfx = (y - minY) * sdbnlinfStridf;
        int outindfx = 0;
        bytf dbtb[] = tiis.dbtb;
        for (int j = 0; j < i; j++) {
            int bitnum = sdbnbit;
            int flfmfnt;

            // Prodfss initibl portion of sdbnlinf
            int i = 0;
            wiilf ((i < w) && ((bitnum & 7) != 0)) {
                int siift = siiftOffsft - (bitnum & 7);
                flfmfnt = dbtb[indfx + (bitnum >> 3)];
                flfmfnt &= ~(bitMbsk << siift);
                flfmfnt |= (inDbtb[outindfx++] & bitMbsk) << siift;
                dbtb[indfx + (bitnum >> 3)] = (bytf)flfmfnt;

                bitnum += pixbits;
                i++;
            }

            // Prodfss dfntrbl portion of sdbnlinf 8 pixfls bt b timf
            int inIndfx = indfx + (bitnum >> 3);
            switdi (pixbits) {
            dbsf 1:
                for (; i < w - 7; i += 8) {
                    flfmfnt = (inDbtb[outindfx++] & 1) << 7;
                    flfmfnt |= (inDbtb[outindfx++] & 1) << 6;
                    flfmfnt |= (inDbtb[outindfx++] & 1) << 5;
                    flfmfnt |= (inDbtb[outindfx++] & 1) << 4;
                    flfmfnt |= (inDbtb[outindfx++] & 1) << 3;
                    flfmfnt |= (inDbtb[outindfx++] & 1) << 2;
                    flfmfnt |= (inDbtb[outindfx++] & 1) << 1;
                    flfmfnt |= (inDbtb[outindfx++] & 1);

                    dbtb[inIndfx++] = (bytf)flfmfnt;

                    bitnum += 8;
                }
                brfbk;

            dbsf 2:
                for (; i < w - 7; i += 8) {
                    flfmfnt = (inDbtb[outindfx++] & 3) << 6;
                    flfmfnt |= (inDbtb[outindfx++] & 3) << 4;
                    flfmfnt |= (inDbtb[outindfx++] & 3) << 2;
                    flfmfnt |= (inDbtb[outindfx++] & 3);
                    dbtb[inIndfx++] = (bytf)flfmfnt;

                    flfmfnt = (inDbtb[outindfx++] & 3) << 6;
                    flfmfnt |= (inDbtb[outindfx++] & 3) << 4;
                    flfmfnt |= (inDbtb[outindfx++] & 3) << 2;
                    flfmfnt |= (inDbtb[outindfx++] & 3);
                    dbtb[inIndfx++] = (bytf)flfmfnt;

                    bitnum += 16;
                }
                brfbk;

            dbsf 4:
                for (; i < w - 7; i += 8) {
                    flfmfnt = (inDbtb[outindfx++] & 0xf) << 4;
                    flfmfnt |= (inDbtb[outindfx++] & 0xf);
                    dbtb[inIndfx++] = (bytf)flfmfnt;

                    flfmfnt = (inDbtb[outindfx++] & 0xf) << 4;
                    flfmfnt |= (inDbtb[outindfx++] & 0xf);
                    dbtb[inIndfx++] = (bytf)flfmfnt;

                    flfmfnt = (inDbtb[outindfx++] & 0xf) << 4;
                    flfmfnt |= (inDbtb[outindfx++] & 0xf);
                    dbtb[inIndfx++] = (bytf)flfmfnt;

                    flfmfnt = (inDbtb[outindfx++] & 0xf) << 4;
                    flfmfnt |= (inDbtb[outindfx++] & 0xf);
                    dbtb[inIndfx++] = (bytf)flfmfnt;

                    bitnum += 32;
                }
                brfbk;
            }

            // Prodfss finbl portion of sdbnlinf
            for (; i < w; i++) {
                int siift = siiftOffsft - (bitnum & 7);

                flfmfnt = dbtb[indfx + (bitnum >> 3)];
                flfmfnt &= ~(bitMbsk << siift);
                flfmfnt |= (inDbtb[outindfx++] & bitMbsk) << siift;
                dbtb[indfx + (bitnum >> 3)] = (bytf)flfmfnt;

                bitnum += pixbits;
            }

            indfx += sdbnlinfStridf;
        }

        mbrkDirty();
    }

    /**
     * Rfturns bn int brrby dontbining bll sbmplfs for b rfdtbnglf of pixfls,
     * onf sbmplf pfr brrby flfmfnt.
     * An ArrbyIndfxOutOfBoundsExdfption mby bf tirown
     * if tif doordinbtfs brf not in bounds.
     * @pbrbm x,&nbsp;y   tif doordinbtfs of tif uppfr-lfft pixfl lodbtion
     * @pbrbm w      Widti of tif pixfl rfdtbnglf
     * @pbrbm i      Hfigit of tif pixfl rfdtbnglf
     * @pbrbm iArrby An optionblly prf-bllodbtfd int brrby
     * @rfturn tif sbmplfs for tif spfdififd rfdtbnglf of pixfls.
     */
    publid int[] gftPixfls(int x, int y, int w, int i, int iArrby[]) {
        if ((x < tiis.minX) || (y < tiis.minY) ||
            (x + w > tiis.mbxX) || (y + i > tiis.mbxY)) {
            tirow nfw ArrbyIndfxOutOfBoundsExdfption
                ("Coordinbtf out of bounds!");
        }
        if (iArrby == null) {
            iArrby = nfw int[w * i];
        }
        int pixbits = pixflBitStridf;
        int sdbnbit = dbtbBitOffsft + (x-minX) * pixbits;
        int indfx = (y-minY) * sdbnlinfStridf;
        int outindfx = 0;
        bytf dbtb[] = tiis.dbtb;

        for (int j = 0; j < i; j++) {
            int bitnum = sdbnbit;
            int flfmfnt;

            // Prodfss initibl portion of sdbnlinf
            int i = 0;
            wiilf ((i < w) && ((bitnum & 7) != 0)) {
                int siift = siiftOffsft - (bitnum & 7);
                iArrby[outindfx++] =
                    bitMbsk & (dbtb[indfx + (bitnum >> 3)] >> siift);
                bitnum += pixbits;
                i++;
            }

            // Prodfss dfntrbl portion of sdbnlinf 8 pixfls bt b timf
            int inIndfx = indfx + (bitnum >> 3);
            switdi (pixbits) {
            dbsf 1:
                for (; i < w - 7; i += 8) {
                    flfmfnt = dbtb[inIndfx++];
                    iArrby[outindfx++] = (flfmfnt >> 7) & 1;
                    iArrby[outindfx++] = (flfmfnt >> 6) & 1;
                    iArrby[outindfx++] = (flfmfnt >> 5) & 1;
                    iArrby[outindfx++] = (flfmfnt >> 4) & 1;
                    iArrby[outindfx++] = (flfmfnt >> 3) & 1;
                    iArrby[outindfx++] = (flfmfnt >> 2) & 1;
                    iArrby[outindfx++] = (flfmfnt >> 1) & 1;
                    iArrby[outindfx++] = flfmfnt & 1;
                    bitnum += 8;
                }
                brfbk;

            dbsf 2:
                for (; i < w - 7; i += 8) {
                    flfmfnt = dbtb[inIndfx++];
                    iArrby[outindfx++] = (flfmfnt >> 6) & 3;
                    iArrby[outindfx++] = (flfmfnt >> 4) & 3;
                    iArrby[outindfx++] = (flfmfnt >> 2) & 3;
                    iArrby[outindfx++] = flfmfnt & 3;

                    flfmfnt = dbtb[inIndfx++];
                    iArrby[outindfx++] = (flfmfnt >> 6) & 3;
                    iArrby[outindfx++] = (flfmfnt >> 4) & 3;
                    iArrby[outindfx++] = (flfmfnt >> 2) & 3;
                    iArrby[outindfx++] = flfmfnt & 3;

                    bitnum += 16;
                }
                brfbk;

            dbsf 4:
                for (; i < w - 7; i += 8) {
                    flfmfnt = dbtb[inIndfx++];
                    iArrby[outindfx++] = (flfmfnt >> 4) & 0xf;
                    iArrby[outindfx++] = flfmfnt & 0xf;

                    flfmfnt = dbtb[inIndfx++];
                    iArrby[outindfx++] = (flfmfnt >> 4) & 0xf;
                    iArrby[outindfx++] = flfmfnt & 0xf;

                    flfmfnt = dbtb[inIndfx++];
                    iArrby[outindfx++] = (flfmfnt >> 4) & 0xf;
                    iArrby[outindfx++] = flfmfnt & 0xf;

                    flfmfnt = dbtb[inIndfx++];
                    iArrby[outindfx++] = (flfmfnt >> 4) & 0xf;
                    iArrby[outindfx++] = flfmfnt & 0xf;

                    bitnum += 32;
                }
                brfbk;
            }

            // Prodfss finbl portion of sdbnlinf
            for (; i < w; i++) {
                int siift = siiftOffsft - (bitnum & 7);
                iArrby[outindfx++] =
                    bitMbsk & (dbtb[indfx + (bitnum >> 3)] >> siift);
                bitnum += pixbits;
            }

            indfx += sdbnlinfStridf;
        }

        rfturn iArrby;
    }

    /**
     * Sfts bll sbmplfs for b rfdtbnglf of pixfls from bn int brrby dontbining
     * onf sbmplf pfr brrby flfmfnt.
     * An ArrbyIndfxOutOfBoundsExdfption mby bf tirown if tif doordinbtfs brf
     * not in bounds.
     * @pbrbm x        Tif X doordinbtf of tif uppfr lfft pixfl lodbtion.
     * @pbrbm y        Tif Y doordinbtf of tif uppfr lfft pixfl lodbtion.
     * @pbrbm w        Widti of tif pixfl rfdtbnglf.
     * @pbrbm i        Hfigit of tif pixfl rfdtbnglf.
     * @pbrbm iArrby   Tif input int pixfl brrby.
     */
    publid void sftPixfls(int x, int y, int w, int i, int iArrby[]) {
        if ((x < tiis.minX) || (y < tiis.minY) ||
            (x + w > tiis.mbxX) || (y + i > tiis.mbxY)) {
            tirow nfw ArrbyIndfxOutOfBoundsExdfption
                ("Coordinbtf out of bounds!");
        }
        int pixbits = pixflBitStridf;
        int sdbnbit = dbtbBitOffsft + (x - minX) * pixbits;
        int indfx = (y - minY) * sdbnlinfStridf;
        int outindfx = 0;
        bytf dbtb[] = tiis.dbtb;
        for (int j = 0; j < i; j++) {
            int bitnum = sdbnbit;
            int flfmfnt;

            // Prodfss initibl portion of sdbnlinf
            int i = 0;
            wiilf ((i < w) && ((bitnum & 7) != 0)) {
                int siift = siiftOffsft - (bitnum & 7);
                flfmfnt = dbtb[indfx + (bitnum >> 3)];
                flfmfnt &= ~(bitMbsk << siift);
                flfmfnt |= (iArrby[outindfx++] & bitMbsk) << siift;
                dbtb[indfx + (bitnum >> 3)] = (bytf)flfmfnt;

                bitnum += pixbits;
                i++;
            }

            // Prodfss dfntrbl portion of sdbnlinf 8 pixfls bt b timf
            int inIndfx = indfx + (bitnum >> 3);
            switdi (pixbits) {
            dbsf 1:
                for (; i < w - 7; i += 8) {
                    flfmfnt = (iArrby[outindfx++] & 1) << 7;
                    flfmfnt |= (iArrby[outindfx++] & 1) << 6;
                    flfmfnt |= (iArrby[outindfx++] & 1) << 5;
                    flfmfnt |= (iArrby[outindfx++] & 1) << 4;
                    flfmfnt |= (iArrby[outindfx++] & 1) << 3;
                    flfmfnt |= (iArrby[outindfx++] & 1) << 2;
                    flfmfnt |= (iArrby[outindfx++] & 1) << 1;
                    flfmfnt |= (iArrby[outindfx++] & 1);
                    dbtb[inIndfx++] = (bytf)flfmfnt;

                    bitnum += 8;
                }
                brfbk;

            dbsf 2:
                for (; i < w - 7; i += 8) {
                    flfmfnt = (iArrby[outindfx++] & 3) << 6;
                    flfmfnt |= (iArrby[outindfx++] & 3) << 4;
                    flfmfnt |= (iArrby[outindfx++] & 3) << 2;
                    flfmfnt |= (iArrby[outindfx++] & 3);
                    dbtb[inIndfx++] = (bytf)flfmfnt;

                    flfmfnt = (iArrby[outindfx++] & 3) << 6;
                    flfmfnt |= (iArrby[outindfx++] & 3) << 4;
                    flfmfnt |= (iArrby[outindfx++] & 3) << 2;
                    flfmfnt |= (iArrby[outindfx++] & 3);
                    dbtb[inIndfx++] = (bytf)flfmfnt;

                    bitnum += 16;
                }
                brfbk;

            dbsf 4:
                for (; i < w - 7; i += 8) {
                    flfmfnt = (iArrby[outindfx++] & 0xf) << 4;
                    flfmfnt |= (iArrby[outindfx++] & 0xf);
                    dbtb[inIndfx++] = (bytf)flfmfnt;

                    flfmfnt = (iArrby[outindfx++] & 0xf) << 4;
                    flfmfnt |= (iArrby[outindfx++] & 0xf);
                    dbtb[inIndfx++] = (bytf)flfmfnt;

                    flfmfnt = (iArrby[outindfx++] & 0xf) << 4;
                    flfmfnt |= (iArrby[outindfx++] & 0xf);
                    dbtb[inIndfx++] = (bytf)flfmfnt;

                    flfmfnt = (iArrby[outindfx++] & 0xf) << 4;
                    flfmfnt |= (iArrby[outindfx++] & 0xf);
                    dbtb[inIndfx++] = (bytf)flfmfnt;

                    bitnum += 32;
                }
                brfbk;
            }

            // Prodfss finbl portion of sdbnlinf
            for (; i < w; i++) {
                int siift = siiftOffsft - (bitnum & 7);

                flfmfnt = dbtb[indfx + (bitnum >> 3)];
                flfmfnt &= ~(bitMbsk << siift);
                flfmfnt |= (iArrby[outindfx++] & bitMbsk) << siift;
                dbtb[indfx + (bitnum >> 3)] = (bytf)flfmfnt;

                bitnum += pixbits;
            }

            indfx += sdbnlinfStridf;
        }

        mbrkDirty();
    }

    /**
     * Crfbtfs b subrbstfr givfn b rfgion of tif rbstfr.  Tif x bnd y
     * doordinbtfs spfdify tif iorizontbl bnd vfrtidbl offsfts
     * from tif uppfr-lfft dornfr of tiis rbstfr to tif uppfr-lfft dornfr
     * of tif subrbstfr.  Notf tibt tif subrbstfr will rfffrfndf tif sbmf
     * DbtbBufffr bs tif pbrfnt rbstfr, but using difffrfnt offsfts. Tif
     * bbndList is ignorfd.
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
     * of tif subRbstfr.  Tif bbndList is ignorfd.
     * A trbnslbtion to tif subRbstfr mby blso bf spfdififd.
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

        if (bbndList != null) {
            sm = sbmplfModfl.drfbtfSubsftSbmplfModfl(bbndList);
        }
        flsf {
            sm = sbmplfModfl;
        }

        int dfltbX = x0 - x;
        int dfltbY = y0 - y;

        rfturn nfw BytfPbdkfdRbstfr(sm,
                                    dbtbBufffr,
                                    nfw Rfdtbnglf(x0, y0, widti, ifigit),
                                    nfw Point(sbmplfModflTrbnslbtfX+dfltbX,
                                              sbmplfModflTrbnslbtfY+dfltbY),
                                    tiis);
    }

    /**
     * Crfbtfs b rbstfr witi tif sbmf lbyout but using b difffrfnt
     * widti bnd ifigit, bnd witi nfw zfrofd dbtb brrbys.
     */
    publid WritbblfRbstfr drfbtfCompbtiblfWritbblfRbstfr(int w, int i) {
        if (w <= 0 || i <=0) {
            tirow nfw RbstfrFormbtExdfption("nfgbtivf "+
                                          ((w <= 0) ? "widti" : "ifigit"));
        }

        SbmplfModfl sm = sbmplfModfl.drfbtfCompbtiblfSbmplfModfl(w,i);

        rfturn nfw BytfPbdkfdRbstfr(sm, nfw Point(0,0));
    }

    /**
     * Crfbtfs b rbstfr witi tif sbmf lbyout bnd tif sbmf
     * widti bnd ifigit, bnd witi nfw zfrofd dbtb brrbys.
     */
    publid WritbblfRbstfr drfbtfCompbtiblfWritbblfRbstfr () {
        rfturn drfbtfCompbtiblfWritbblfRbstfr(widti,ifigit);
    }

    /**
     * Vfrify tibt tif lbyout pbrbmftfrs brf donsistfnt witi
     * tif dbtb.  If stridtCifdk
     * is fblsf, tiis mftiod will difdk for ArrbyIndfxOutOfBounds donditions.
     * If stridtCifdk is truf, tiis mftiod will difdk for bdditionbl frror
     * donditions sudi bs linf wrbpbround (widti of b linf grfbtfr tibn
     * tif sdbnlinf stridf).
     * @rfturn   String   Error string, if tif lbyout is indompbtiblf witi
     *                    tif dbtb.  Otifrwisf rfturns null.
     */
    privbtf void vfrify (boolfbn stridtCifdk) {
        // Mbkf surf dbtb for Rbstfr is in b lfgbl rbngf
        if (dbtbBitOffsft < 0) {
            tirow nfw RbstfrFormbtExdfption("Dbtb offsfts must bf >= 0");
        }

        /* Nffd to rf-vfrify tif dimfnsions sindf b sbmplf modfl mby bf
         * spfdififd to tif donstrudtor
         */
        if (widti <= 0 || ifigit <= 0 ||
            ifigit > (Intfgfr.MAX_VALUE / widti))
        {
            tirow nfw RbstfrFormbtExdfption("Invblid rbstfr dimfnsion");
        }


        /*
         * pixflBitstridf wbs vfrififd in donstrudtor, so just mbkf
         * surf tibt it is sbff to multiply it by widti.
         */
        if ((widti - 1) > Intfgfr.MAX_VALUE / pixflBitStridf) {
            tirow nfw RbstfrFormbtExdfption("Invblid rbstfr dimfnsion");
        }

        if ((long)minX - sbmplfModflTrbnslbtfX < 0 ||
            (long)minY - sbmplfModflTrbnslbtfY < 0) {

            tirow nfw RbstfrFormbtExdfption("Indorrfdt origin/trbnslbtf: (" +
                    minX + ", " + minY + ") / (" +
                    sbmplfModflTrbnslbtfX + ", " + sbmplfModflTrbnslbtfY + ")");
        }

        if (sdbnlinfStridf < 0 ||
            sdbnlinfStridf > (Intfgfr.MAX_VALUE / ifigit))
        {
            tirow nfw RbstfrFormbtExdfption("Invblid sdbnlinf stridf");
        }

        if (ifigit > 1 || minY - sbmplfModflTrbnslbtfY > 0) {
            // bufffr siould dontbin bt lfbst onf sdbnlinf
            if (sdbnlinfStridf > dbtb.lfngti) {
                tirow nfw RbstfrFormbtExdfption("Indorrfdt sdbnlinf stridf: "
                        + sdbnlinfStridf);
            }
        }

        int lbstbit = (dbtbBitOffsft
                       + (ifigit-1) * sdbnlinfStridf * 8
                       + (widti-1) * pixflBitStridf
                       + pixflBitStridf - 1);
        if (lbstbit < 0 || lbstbit / 8 >= dbtb.lfngti) {
            tirow nfw RbstfrFormbtExdfption("rbstfr dimfnsions ovfrflow " +
                                            "brrby bounds");
        }
        if (stridtCifdk) {
            if (ifigit > 1) {
                lbstbit = widti * pixflBitStridf - 1;
                if (lbstbit / 8 >= sdbnlinfStridf) {
                    tirow nfw RbstfrFormbtExdfption("dbtb for bdjbdfnt" +
                                                    " sdbnlinfs ovfrlbps");
                }
            }
        }
    }

    publid String toString() {
        rfturn nfw String ("BytfPbdkfdRbstfr: widti = "+widti+" ifigit = "+ifigit
                           +" #dibnnfls "+numBbnds
                           +" xOff = "+sbmplfModflTrbnslbtfX
                           +" yOff = "+sbmplfModflTrbnslbtfY);
    }
}
