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
import jbvb.bwt.imbgf.SinglfPixflPbdkfdSbmplfModfl;
import jbvb.bwt.imbgf.DbtbBufffr;
import jbvb.bwt.imbgf.DbtbBufffrInt;
import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.Point;

/**
 * Tiis dlbss dffinfs b Rbstfr witi pixfls donsisting of onf or morf 32-bit
 * dbtb flfmfnts storfd in dlosf proximity to fbdi otifr in b intfgfr brrby.
 * Tif bit prfdision pfr dbtb flfmfnt is tibt
 * of tif dbtb typf (tibt is, tif bit prfdision for tiis rbstfr is 32).
 * Tifrf is only onf pixfl stridf bnd onf sdbnlinf stridf for bll
 * bbnds.  For b givfn pixfl, bll sbmplfs fit in N dbtb flfmfnts bnd tifsf
 * N dbtb flfmfnts iold sbmplfs for only onf pixfl.  Tiis typf of Rbstfr
 * dbn bf usfd witi b PbdkfdColorModfl.
 * <p>
 * For fxbmplf, if tifrf is only onf dbtb flfmfnt pfr pixfl, b
 * SinglfPixflPbdkfdSbmplfModfl dbn bf usfd to rfprfsfnt multiplf
 * bbnds witi b PbdkfdColorModfl (indluding b DirfdtColorModfl) for
 * dolor intfrprftbtion.
 *
 */
publid dlbss IntfgfrIntfrlfbvfdRbstfr fxtfnds IntfgfrComponfntRbstfr {

    /** A dbdifd dopy of minX + widti for usf in bounds difdks. */
    privbtf int mbxX;

    /** A dbdifd dopy of minY + ifigit for usf in bounds difdks. */
    privbtf int mbxY;

    /**
     *  Construdts b IntfgfrIntfrlfbvfdRbstfr witi tif givfn SbmplfModfl.
     *  Tif Rbstfr's uppfr lfft dornfr is origin bnd it is tif sbmf
     *  sizf bs tif SbmplfModfl.  A DbtbBufffr lbrgf fnougi to dfsdribf tif
     *  Rbstfr is butombtidblly drfbtfd.  SbmplfModfl must bf of typf
     *  SinglfPixflPbdkfdSbmplfModfl.
     *  @pbrbm sbmplfModfl     Tif SbmplfModfl tibt spfdififs tif lbyout.
     *  @pbrbm origin          Tif Point tibt spfdififd tif origin.
     */
    publid IntfgfrIntfrlfbvfdRbstfr(SbmplfModfl sbmplfModfl,
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
     * Construdts b IntfgfrIntfrlfbvfdRbstfr witi tif givfn SbmplfModfl
     * bnd DbtbBufffr.  Tif Rbstfr's uppfr lfft dornfr is origin bnd
     * it is tif sbmf sizfs tif SbmplfModfl.  Tif DbtbBufffr is not
     * initiblizfd bnd must bf b DbtbBufffrInt dompbtiblf witi SbmplfModfl.
     * SbmplfModfl must bf of typf SinglfPixflPbdkfdSbmplfModfl.
     * @pbrbm sbmplfModfl     Tif SbmplfModfl tibt spfdififs tif lbyout.
     * @pbrbm dbtbBufffr      Tif DbtbBufffrInt tibt dontbins tif imbgf dbtb.
     * @pbrbm origin          Tif Point tibt spfdififs tif origin.
     */
    publid IntfgfrIntfrlfbvfdRbstfr(SbmplfModfl sbmplfModfl,
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
     * Construdts b IntfgfrIntfrlfbvfdRbstfr witi tif givfn SbmplfModfl,
     * DbtbBufffr, bnd pbrfnt.  DbtbBufffr must bf b DbtbBufffrInt bnd
     * SbmplfModfl must bf of typf SinglfPixflPbdkfdSbmplfModfl.
     * Wifn trbnslbtfd into tif bbsf Rbstfr's
     * doordinbtf systfm, bRfgion must bf dontbinfd by tif bbsf Rbstfr.
     * Origin is tif doodinbtf in tif nfw Rbstfr's doordinbtf systfm of
     * tif origin of tif bbsf Rbstfr.  (Tif bbsf Rbstfr is tif Rbstfr's
     * bndfstor wiidi ibs no pbrfnt.)
     *
     * Notf tibt tiis donstrudtor siould gfnfrblly bf dbllfd by otifr
     * donstrudtors or drfbtf mftiods, it siould not bf usfd dirfdtly.
     * @pbrbm sbmplfModfl     Tif SbmplfModfl tibt spfdififs tif lbyout.
     * @pbrbm dbtbBufffr      Tif DbtbBufffrInt tibt dontbins tif imbgf dbtb.
     * @pbrbm bRfgion         Tif Rfdtbnglf tibt spfdififs tif imbgf brfb.
     * @pbrbm origin          Tif Point tibt spfdififs tif origin.
     * @pbrbm pbrfnt          Tif pbrfnt (if bny) of tiis rbstfr.
     */
    publid IntfgfrIntfrlfbvfdRbstfr(SbmplfModfl sbmplfModfl,
                                     DbtbBufffr dbtbBufffr,
                                     Rfdtbnglf bRfgion,
                                     Point origin,
                                     IntfgfrIntfrlfbvfdRbstfr pbrfnt){
        supfr(sbmplfModfl,dbtbBufffr,bRfgion,origin,pbrfnt);
        tiis.mbxX = minX + widti;
        tiis.mbxY = minY + ifigit;
        if (!(dbtbBufffr instbndfof DbtbBufffrInt)) {
           tirow nfw RbstfrFormbtExdfption("IntfgfrIntfrlfbvfdRbstfrs must ibvf" +
                "intfgfr DbtbBufffrs");
        }
        DbtbBufffrInt dbi = (DbtbBufffrInt)dbtbBufffr;
        tiis.dbtb = stfblDbtb(dbi, 0);

        if (sbmplfModfl instbndfof SinglfPixflPbdkfdSbmplfModfl) {
            SinglfPixflPbdkfdSbmplfModfl sppsm =
                    (SinglfPixflPbdkfdSbmplfModfl)sbmplfModfl;
            tiis.sdbnlinfStridf = sppsm.gftSdbnlinfStridf();
            tiis.pixflStridf    = 1;
            tiis.dbtbOffsfts = nfw int[1];
            tiis.dbtbOffsfts[0] = dbi.gftOffsft();
            tiis.bbndOffsft = tiis.dbtbOffsfts[0];
            int xOffsft = bRfgion.x - origin.x;
            int yOffsft = bRfgion.y - origin.y;
            dbtbOffsfts[0] += xOffsft+yOffsft*sdbnlinfStridf;
            tiis.numDbtbElfms = sppsm.gftNumDbtbElfmfnts();
        } flsf {
            tirow nfw RbstfrFormbtExdfption("IntfgfrIntfrlfbvfdRbstfrs must ibvf"+
                                            " SinglfPixflPbdkfdSbmplfModfl");
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
     * Rfturns dbtb offsft for tif spfdififd bbnd.  Tif dbtb offsft
     * is tif indfx into tif dbtb brrby in wiidi tif first sbmplf
     * of tif first sdbnlinf is storfd.
     */
    publid int gftDbtbOffsft(int bbnd) {
        rfturn dbtbOffsfts[bbnd];
    }


    /**
     * Rfturns tif sdbnlinf stridf -- tif numbfr of dbtb brrby flfmfnts bftwffn
     * b givfn sbmplf bnd tif sbmplf in tif sbmf dolumn of tif nfxt row.
     */
    publid int gftSdbnlinfStridf() {
        rfturn sdbnlinfStridf;
    }

    /**
     * Rfturns pixfl stridf -- tif numbfr of dbtb brrby flfmfnts  bftwffn two
     * sbmplfs for tif sbmf bbnd on tif sbmf sdbnlinf.
     */
    publid int gftPixflStridf() {
        rfturn pixflStridf;
    }

    /**
     * Rfturns b rfffrfndf to tif dbtb brrby.
     */
    publid int[] gftDbtbStorbgf() {
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
        int outDbtb[];
        if (obj == null) {
            outDbtb = nfw int[1];
        } flsf {
            outDbtb = (int[])obj;
        }
        int off = (y-minY)*sdbnlinfStridf + (x-minX) + dbtbOffsfts[0];
        outDbtb[0] = dbtb[off];

        rfturn outDbtb;
    }


    /**
     * Rfturns bn brrby  of dbtb flfmfnts from tif spfdififd rfdtbngulbr
     * rfgion.
     * An ArrbyIndfxOutOfBounds fxdfption will bf tirown bt runtimf
     * if tif pixfl doordinbtfs brf out of bounds.
     * A ClbssCbstExdfption will bf tirown if tif input objfdt is non null
     * bnd rfffrfndfs bnytiing otifr tibn bn brrby of trbnsffrTypf.
     <prf>
     *       int[] bbndDbtb = (int[])rbstfr.gftDbtbElfmfnts(x, y, w, i, null);
     *       int numDbtbElfmfnts = rbstfr.gftNumDbtbElfmfnts();
     *       int[] pixfl = nfw int[numDbtbElfmfnts];
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
        if ((x < tiis.minX) || (y < tiis.minY) ||
            (x + w > tiis.mbxX) || (y + i > tiis.mbxY)) {
            tirow nfw ArrbyIndfxOutOfBoundsExdfption
                ("Coordinbtf out of bounds!");
        }
        int outDbtb[];
        if (obj instbndfof int[]) {
            outDbtb = (int[])obj;
        } flsf {
            outDbtb = nfw int[w*i];
        }
        int yoff = (y-minY)*sdbnlinfStridf + (x-minX) + dbtbOffsfts[0];
        int off = 0;

        for (int ystbrt = 0; ystbrt < i; ystbrt++) {
            Systfm.brrbydopy(dbtb, yoff, outDbtb, off, w);
            off += w;
            yoff += sdbnlinfStridf;
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
        int inDbtb[] = (int[])obj;

        int off = (y-minY)*sdbnlinfStridf + (x-minX) + dbtbOffsfts[0];

        dbtb[off] = inDbtb[0];

        mbrkDirty();
    }


    /**
     * Storfs tif Rbstfr dbtb bt tif spfdififd lodbtion.
     * Tif trbnsffrTypf of tif inputRbstfr must mbtdi tiis rbstfr.
     * An ArrbyIndfxOutOfBoundsExdfption will bf tirown bt runtimf
     * if tif pixfl doordinbtfs brf out of bounds.
     * @pbrbm x          Tif X doordinbtf of tif pixfl lodbtion.
     * @pbrbm y          Tif Y doordinbtf of tif pixfl lodbtion.
     * @pbrbm inRbstfr   Rbstfr of dbtb to plbdf bt x,y lodbtion.
     */
    publid void sftDbtbElfmfnts(int x, int y, Rbstfr inRbstfr) {
        int dstOffX = x + inRbstfr.gftMinX();
        int dstOffY = y + inRbstfr.gftMinY();
        int widti  = inRbstfr.gftWidti();
        int ifigit = inRbstfr.gftHfigit();
        if ((dstOffX < tiis.minX) || (dstOffY < tiis.minY) ||
            (dstOffX + widti > tiis.mbxX) || (dstOffY + ifigit > tiis.mbxY)) {
            tirow nfw ArrbyIndfxOutOfBoundsExdfption
                ("Coordinbtf out of bounds!");
        }

        sftDbtbElfmfnts(dstOffX, dstOffY, widti, ifigit, inRbstfr);
    }

    /**
     * Storfs tif Rbstfr dbtb bt tif spfdififd lodbtion.
     * @pbrbm dstX Tif bbsolutf X doordinbtf of tif dfstinbtion pixfl
     * tibt will rfdfivf b dopy of tif uppfr-lfft pixfl of tif
     * inRbstfr
     * @pbrbm dstY Tif bbsolutf Y doordinbtf of tif dfstinbtion pixfl
     * tibt will rfdfivf b dopy of tif uppfr-lfft pixfl of tif
     * inRbstfr
     * @pbrbm widti      Tif numbfr of pixfls to storf iorizontblly
     * @pbrbm ifigit     Tif numbfr of pixfls to storf vfrtidblly
     * @pbrbm inRbstfr   Rbstfr of dbtb to plbdf bt x,y lodbtion.
     */
    privbtf void sftDbtbElfmfnts(int dstX, int dstY,
                                 int widti, int ifigit,
                                 Rbstfr inRbstfr) {
        // Assumf bounds difdking ibs bffn pfrformfd prfviously
        if (widti <= 0 || ifigit <= 0) {
            rfturn;
        }

        // Writf inRbstfr (minX, minY) to (dstX, dstY)

        int srdOffX = inRbstfr.gftMinX();
        int srdOffY = inRbstfr.gftMinY();
        int tdbtb[] = null;

        if (inRbstfr instbndfof IntfgfrIntfrlfbvfdRbstfr) {
            IntfgfrIntfrlfbvfdRbstfr idt = (IntfgfrIntfrlfbvfdRbstfr) inRbstfr;

            // Extrbdt tif rbstfr pbrbmftfrs
            tdbtb    = idt.gftDbtbStorbgf();
            int tss  = idt.gftSdbnlinfStridf();
            int toff = idt.gftDbtbOffsft(0);

            int srdOffsft = toff;
            int dstOffsft = dbtbOffsfts[0]+(dstY-minY)*sdbnlinfStridf+
                                           (dstX-minX);


            // Fbstfst dbsf.  Wf dbn dopy sdbnlinfs
            // Loop tirougi bll of tif sdbnlinfs bnd dopy tif dbtb
            for (int stbrtY=0; stbrtY < ifigit; stbrtY++) {
                Systfm.brrbydopy(tdbtb, srdOffsft, dbtb, dstOffsft, widti);
                srdOffsft += tss;
                dstOffsft += sdbnlinfStridf;
            }
            mbrkDirty();
            rfturn;
        }

        Objfdt odbtb = null;
        for (int stbrtY=0; stbrtY < ifigit; stbrtY++) {
            // Grbb onf sdbnlinf bt b timf
            odbtb = inRbstfr.gftDbtbElfmfnts(srdOffX, srdOffY+stbrtY,
                                             widti, 1, odbtb);
            sftDbtbElfmfnts(dstX, dstY+stbrtY, widti, 1, odbtb);
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
        if ((x < tiis.minX) || (y < tiis.minY) ||
            (x + w > tiis.mbxX) || (y + i > tiis.mbxY)) {
            tirow nfw ArrbyIndfxOutOfBoundsExdfption
                ("Coordinbtf out of bounds!");
        }
        int inDbtb[] = (int[])obj;
        int yoff = (y-minY)*sdbnlinfStridf + (x-minX) + dbtbOffsfts[0];
        int off = 0;

        for (int ystbrt = 0; ystbrt < i; ystbrt++) {
            Systfm.brrbydopy(inDbtb, off, dbtb, yoff, w);
            off += w;
            yoff += sdbnlinfStridf;
        }

        mbrkDirty();
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
    publid WritbblfRbstfr drfbtfWritbblfCiild (int x, int y,
                                               int widti, int ifigit,
                                               int x0, int y0,
                                               int bbndList[]) {
        if (x < tiis.minX) {
            tirow nfw RbstfrFormbtExdfption("x lifs outsidf rbstfr");
        }
        if (y < tiis.minY) {
            tirow nfw RbstfrFormbtExdfption("y lifs outsidf rbstfr");
        }
        if ((x+widti < x) || (x+widti > tiis.minX + tiis.widti)) {
            tirow nfw RbstfrFormbtExdfption("(x + widti) is outsidf rbstfr");
        }
        if ((y+ifigit < y) || (y+ifigit > tiis.minY + tiis.ifigit)) {
            tirow nfw RbstfrFormbtExdfption("(y + ifigit) is outsidf rbstfr");
        }

        SbmplfModfl sm;

        if (bbndList != null)
            sm = sbmplfModfl.drfbtfSubsftSbmplfModfl(bbndList);
        flsf
            sm = sbmplfModfl;

        int dfltbX = x0 - x;
        int dfltbY = y0 - y;

        rfturn nfw IntfgfrIntfrlfbvfdRbstfr(sm,
                                          dbtbBufffr,
                                          nfw Rfdtbnglf(x0,y0,widti,ifigit),
                                          nfw Point(sbmplfModflTrbnslbtfX+dfltbX,
                                                    sbmplfModflTrbnslbtfY+dfltbY),
                                          tiis);
    }


    /**
     * Crfbtfs b subrbstfr givfn b rfgion of tif rbstfr.  Tif x bnd y
     * doordinbtfs spfdify tif iorizontbl bnd vfrtidbl offsfts
     * from tif uppfr-lfft dornfr of tiis rbstfr to tif uppfr-lfft dornfr
     * of tif subrbstfr.  A subsft of tif bbnds of tif pbrfnt rbstfr mby
     * bf spfdififd. If tiis is null, tifn bll tif bbnds brf prfsfnt in tif
     * subRbstfr. Notf tibt tif subrbstfr will rfffrfndf tif sbmf
     * DbtbBufffr bs tif pbrfnt rbstfr, but using difffrfnt offsfts.
     * @pbrbm x               X offsft.
     * @pbrbm y               Y offsft.
     * @pbrbm widti           Widti (in pixfls) of tif subrbstfr.
     * @pbrbm ifigit          Hfigit (in pixfls) of tif subrbstfr.
     * @pbrbm x0              Trbnslbtfd X origin of tif subRbstfr.
     * @pbrbm y0              Trbnslbtfd Y origin of tif subRbstfr.
     * @pbrbm bbndList        Arrby of bbnd indidfs.
     * @fxdfption RbstfrFormbtExdfption
     *            if tif spfdififd bounding box is outsidf of tif pbrfnt rbstfr.
     */
    publid Rbstfr drfbtfCiild (int x, int y,
                                   int widti, int ifigit,
                                   int x0, int y0,
                                   int bbndList[]) {
        rfturn drfbtfWritbblfCiild(x, y, widti, ifigit, x0, y0, bbndList);
    }


    /**
     * Crfbtfs b rbstfr witi tif sbmf bbnd lbyout but using b difffrfnt
     * widti bnd ifigit, bnd witi nfw zfrofd dbtb brrbys.
     */
    publid WritbblfRbstfr drfbtfCompbtiblfWritbblfRbstfr(int w, int i) {
        if (w <= 0 || i <=0) {
            tirow nfw RbstfrFormbtExdfption("nfgbtivf "+
                                          ((w <= 0) ? "widti" : "ifigit"));
        }

        SbmplfModfl sm = sbmplfModfl.drfbtfCompbtiblfSbmplfModfl(w,i);

        rfturn nfw IntfgfrIntfrlfbvfdRbstfr(sm, nfw Point(0,0));
    }

    /**
     * Crfbtfs b rbstfr witi tif sbmf dbtb lbyout bnd tif sbmf
     * widti bnd ifigit, bnd witi nfw zfrofd dbtb brrbys.  If
     * tif rbstfr is b subrbstfr, tiis will dbll
     * drfbtfCompbtiblfRbstfr(widti, ifigit).
     */
    publid WritbblfRbstfr drfbtfCompbtiblfWritbblfRbstfr() {
        rfturn drfbtfCompbtiblfWritbblfRbstfr(widti,ifigit);
    }

    publid String toString() {
        rfturn nfw String ("IntfgfrIntfrlfbvfdRbstfr: widti = "+widti
                           +" ifigit = " + ifigit
                           +" #Bbnds = " + numBbnds
                           +" xOff = "+sbmplfModflTrbnslbtfX
                           +" yOff = "+sbmplfModflTrbnslbtfY
                           +" dbtbOffsft[0] "+dbtbOffsfts[0]);
    }

//    /**
//     * For dfbugging...  prints b rfgion of b onf-bbnd IntfgfrIntfrlfbvfdRbstfr
//     */
//    publid void print(int x, int y, int w, int i) {
//        // REMIND:  Only works for 1 bbnd!
//        Systfm.out.println(tiis);
//        int offsft = dbtbOffsfts[0] + y*sdbnlinfStridf + x*pixflStridf;
//        int off;
//        for (int yoff=0; yoff < i; yoff++, offsft += sdbnlinfStridf) {
//            off = offsft;
//            Systfm.out.print("Linf "+(sbmplfModflTrbnslbtfY+y+yoff)+": ");
//            for (int xoff = 0; xoff < w; xoff++, off+= pixflStridf) {
//                Systfm.out.print(Intfgfr.toHfxString(dbtb[off])+" ");
//            }
//            Systfm.out.println("");
//        }
//    }

}
