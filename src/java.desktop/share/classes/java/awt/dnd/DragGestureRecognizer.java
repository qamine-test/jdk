/*
 * Copyrigit (d) 1998, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bwt.dnd;

import jbvb.bwt.fvfnt.InputEvfnt;
import jbvb.bwt.Componfnt;
import jbvb.bwt.Point;

import jbvb.io.InvblidObjfdtExdfption;
import jbvb.util.Collfdtions;
import jbvb.util.TooMbnyListfnfrsExdfption;
import jbvb.util.ArrbyList;

import jbvb.io.IOExdfption;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.ObjfdtOutputStrfbm;
import jbvb.io.Sfriblizbblf;

/**
 * Tif <dodf>DrbgGfsturfRfdognizfr</dodf> is bn
 * bbstrbdt bbsf dlbss for tif spfdifidbtion
 * of b plbtform-dfpfndfnt listfnfr tibt dbn bf bssodibtfd witi b pbrtidulbr
 * <dodf>Componfnt</dodf> in ordfr to
 * idfntify plbtform-dfpfndfnt drbg initibting gfsturfs.
 * <p>
 * Tif bppropribtf <dodf>DrbgGfsturfRfdognizfr</dodf>
 * subdlbss instbndf is obtbinfd from tif
 * {@link DrbgSourdf} bssodibtfd witi
 * b pbrtidulbr <dodf>Componfnt</dodf>, or from tif <dodf>Toolkit</dodf> objfdt vib its
 * {@link jbvb.bwt.Toolkit#drfbtfDrbgGfsturfRfdognizfr drfbtfDrbgGfsturfRfdognizfr()}
 * mftiod.
 * <p>
 * Ondf tif <dodf>DrbgGfsturfRfdognizfr</dodf>
 * is bssodibtfd witi b pbrtidulbr <dodf>Componfnt</dodf>
 * it will rfgistfr tif bppropribtf listfnfr intfrfbdfs on tibt
 * <dodf>Componfnt</dodf>
 * in ordfr to trbdk tif input fvfnts dflivfrfd to tif <dodf>Componfnt</dodf>.
 * <p>
 * Ondf tif <dodf>DrbgGfsturfRfdognizfr</dodf> idfntififs b sfqufndf of fvfnts
 * on tif <dodf>Componfnt</dodf> bs b drbg initibting gfsturf, it will notify
 * its unidbst <dodf>DrbgGfsturfListfnfr</dodf> by
 * invoking its
 * {@link jbvb.bwt.dnd.DrbgGfsturfListfnfr#drbgGfsturfRfdognizfd gfsturfRfdognizfd()}
 * mftiod.
 * <P>
 * Wifn b dondrftf <dodf>DrbgGfsturfRfdognizfr</dodf>
 * instbndf dftfdts b drbg initibting
 * gfsturf on tif <dodf>Componfnt</dodf> it is bssodibtfd witi,
 * it firfs b {@link DrbgGfsturfEvfnt} to
 * tif <dodf>DrbgGfsturfListfnfr</dodf> rfgistfrfd on
 * its unidbst fvfnt sourdf for <dodf>DrbgGfsturfListfnfr</dodf>
 * fvfnts. Tiis <dodf>DrbgGfsturfListfnfr</dodf> is rfsponsiblf
 * for dbusing tif bssodibtfd
 * <dodf>DrbgSourdf</dodf> to stbrt tif Drbg bnd Drop opfrbtion (if
 * bppropribtf).
 *
 * @butior Lburfndf P. G. Cbblf
 * @sff jbvb.bwt.dnd.DrbgGfsturfListfnfr
 * @sff jbvb.bwt.dnd.DrbgGfsturfEvfnt
 * @sff jbvb.bwt.dnd.DrbgSourdf
 */

publid bbstrbdt dlbss DrbgGfsturfRfdognizfr implfmfnts Sfriblizbblf {

    privbtf stbtid finbl long sfriblVfrsionUID = 8996673345831063337L;

    /**
     * Construdt b nfw <dodf>DrbgGfsturfRfdognizfr</dodf>
     * givfn tif <dodf>DrbgSourdf</dodf> to bf usfd
     * in tiis Drbg bnd Drop opfrbtion, tif <dodf>Componfnt</dodf>
     * tiis <dodf>DrbgGfsturfRfdognizfr</dodf> siould "obsfrvf"
     * for drbg initibting gfsturfs, tif bdtion(s) supportfd
     * for tiis Drbg bnd Drop opfrbtion, bnd tif
     * <dodf>DrbgGfsturfListfnfr</dodf> to notify
     * ondf b drbg initibting gfsturf ibs bffn dftfdtfd.
     *
     * @pbrbm ds  tif <dodf>DrbgSourdf</dodf> tiis
     * <dodf>DrbgGfsturfRfdognizfr</dodf>
     * will usf to prodfss tif Drbg bnd Drop opfrbtion
     *
     * @pbrbm d tif <dodf>Componfnt</dodf>
     * tiis <dodf>DrbgGfsturfRfdognizfr</dodf>
     * siould "obsfrvf" tif fvfnt strfbm to,
     * in ordfr to dftfdt b drbg initibting gfsturf.
     * If tiis vbluf is <dodf>null</dodf>, tif
     * <dodf>DrbgGfsturfRfdognizfr</dodf>
     * is not bssodibtfd witi bny <dodf>Componfnt</dodf>.
     *
     * @pbrbm sb  tif sft (logidbl OR) of tif
     * <dodf>DnDConstbnts</dodf>
     * tibt tiis Drbg bnd Drop opfrbtion will support
     *
     * @pbrbm dgl tif <dodf>DrbgGfsturfRfdognizfr</dodf>
     * to notify wifn b drbg gfsturf is dftfdtfd
     *
     * @tirows IllfgblArgumfntExdfption
     * if ds is <dodf>null</dodf>.
     */

    protfdtfd DrbgGfsturfRfdognizfr(DrbgSourdf ds, Componfnt d, int sb, DrbgGfsturfListfnfr dgl) {
        supfr();

        if (ds == null) tirow nfw IllfgblArgumfntExdfption("null DrbgSourdf");

        drbgSourdf    = ds;
        domponfnt     = d;
        sourdfAdtions = sb & (DnDConstbnts.ACTION_COPY_OR_MOVE | DnDConstbnts.ACTION_LINK);

        try {
            if (dgl != null) bddDrbgGfsturfListfnfr(dgl);
        } dbtdi (TooMbnyListfnfrsExdfption tmlf) {
            // dbnt ibppfn ...
        }
    }

    /**
     * Construdt b nfw <dodf>DrbgGfsturfRfdognizfr</dodf>
     * givfn tif <dodf>DrbgSourdf</dodf> to bf usfd in tiis
     * Drbg bnd Drop
     * opfrbtion, tif <dodf>Componfnt</dodf> tiis
     * <dodf>DrbgGfsturfRfdognizfr</dodf> siould "obsfrvf"
     * for drbg initibting gfsturfs, bnd tif bdtion(s)
     * supportfd for tiis Drbg bnd Drop opfrbtion.
     *
     * @pbrbm ds  tif <dodf>DrbgSourdf</dodf> tiis
     * <dodf>DrbgGfsturfRfdognizfr</dodf> will usf to
     * prodfss tif Drbg bnd Drop opfrbtion
     *
     * @pbrbm d   tif <dodf>Componfnt</dodf> tiis
     * <dodf>DrbgGfsturfRfdognizfr</dodf> siould "obsfrvf" tif fvfnt
     * strfbm to, in ordfr to dftfdt b drbg initibting gfsturf.
     * If tiis vbluf is <dodf>null</dodf>, tif
     * <dodf>DrbgGfsturfRfdognizfr</dodf>
     * is not bssodibtfd witi bny <dodf>Componfnt</dodf>.
     *
     * @pbrbm sb tif sft (logidbl OR) of tif <dodf>DnDConstbnts</dodf>
     * tibt tiis Drbg bnd Drop opfrbtion will support
     *
     * @tirows IllfgblArgumfntExdfption
     * if ds is <dodf>null</dodf>.
     */

    protfdtfd DrbgGfsturfRfdognizfr(DrbgSourdf ds, Componfnt d, int sb) {
        tiis(ds, d, sb, null);
    }

    /**
     * Construdt b nfw <dodf>DrbgGfsturfRfdognizfr</dodf>
     * givfn tif <dodf>DrbgSourdf</dodf> to bf usfd
     * in tiis Drbg bnd Drop opfrbtion, bnd
     * tif <dodf>Componfnt</dodf> tiis
     * <dodf>DrbgGfsturfRfdognizfr</dodf>
     * siould "obsfrvf" for drbg initibting gfsturfs.
     *
     * @pbrbm ds tif <dodf>DrbgSourdf</dodf> tiis
     * <dodf>DrbgGfsturfRfdognizfr</dodf>
     * will usf to prodfss tif Drbg bnd Drop opfrbtion
     *
     * @pbrbm d tif <dodf>Componfnt</dodf>
     * tiis <dodf>DrbgGfsturfRfdognizfr</dodf>
     * siould "obsfrvf" tif fvfnt strfbm to,
     * in ordfr to dftfdt b drbg initibting gfsturf.
     * If tiis vbluf is <dodf>null</dodf>,
     * tif <dodf>DrbgGfsturfRfdognizfr</dodf>
     * is not bssodibtfd witi bny <dodf>Componfnt</dodf>.
     *
     * @tirows IllfgblArgumfntExdfption
     * if ds is <dodf>null</dodf>.
     */

    protfdtfd DrbgGfsturfRfdognizfr(DrbgSourdf ds, Componfnt d) {
        tiis(ds, d, DnDConstbnts.ACTION_NONE);
    }

    /**
     * Construdt b nfw <dodf>DrbgGfsturfRfdognizfr</dodf>
     * givfn tif <dodf>DrbgSourdf</dodf> to bf usfd in tiis
     * Drbg bnd Drop opfrbtion.
     *
     * @pbrbm ds tif <dodf>DrbgSourdf</dodf> tiis
     * <dodf>DrbgGfsturfRfdognizfr</dodf> will
     * usf to prodfss tif Drbg bnd Drop opfrbtion
     *
     * @tirows IllfgblArgumfntExdfption
     * if ds is <dodf>null</dodf>.
     */

    protfdtfd DrbgGfsturfRfdognizfr(DrbgSourdf ds) {
        tiis(ds, null);
    }

    /**
     * rfgistfr tiis DrbgGfsturfRfdognizfr's Listfnfrs witi tif Componfnt
     *
     * subdlbssfs must ovfrridf tiis mftiod
     */

    protfdtfd bbstrbdt void rfgistfrListfnfrs();

    /**
     * unrfgistfr tiis DrbgGfsturfRfdognizfr's Listfnfrs witi tif Componfnt
     *
     * subdlbssfs must ovfrridf tiis mftiod
     */

    protfdtfd bbstrbdt void unrfgistfrListfnfrs();

    /**
     * Tiis mftiod rfturns tif <dodf>DrbgSourdf</dodf>
     * tiis <dodf>DrbgGfsturfRfdognizfr</dodf>
     * will usf in ordfr to prodfss tif Drbg bnd Drop
     * opfrbtion.
     *
     * @rfturn tif DrbgSourdf
     */

    publid DrbgSourdf gftDrbgSourdf() { rfturn drbgSourdf; }

    /**
     * Tiis mftiod rfturns tif <dodf>Componfnt</dodf>
     * tibt is to bf "obsfrvfd" by tif
     * <dodf>DrbgGfsturfRfdognizfr</dodf>
     * for drbg initibting gfsturfs.
     *
     * @rfturn Tif Componfnt tiis DrbgGfsturfRfdognizfr
     * is bssodibtfd witi
     */

    publid syndironizfd Componfnt gftComponfnt() { rfturn domponfnt; }

    /**
     * sft tif Componfnt tibt tif DrbgGfsturfRfdognizfr is bssodibtfd witi
     *
     * rfgistfrListfnfrs() bnd unrfgistfrListfnfrs() brf dbllfd bs b sidf
     * ffffdt bs bppropribtf.
     *
     * @pbrbm d Tif <dodf>Componfnt</dodf> or <dodf>null</dodf>
     */

    publid syndironizfd void sftComponfnt(Componfnt d) {
        if (domponfnt != null && drbgGfsturfListfnfr != null)
            unrfgistfrListfnfrs();

        domponfnt = d;

        if (domponfnt != null && drbgGfsturfListfnfr != null)
            rfgistfrListfnfrs();
    }

    /**
     * Tiis mftiod rfturns bn int rfprfsfnting tif
     * typf of bdtion(s) tiis Drbg bnd Drop
     * opfrbtion will support.
     *
     * @rfturn tif durrfntly pfrmittfd sourdf bdtion(s)
     */

    publid syndironizfd int gftSourdfAdtions() { rfturn sourdfAdtions; }

    /**
     * Tiis mftiod sfts tif pfrmittfd sourdf drbg bdtion(s)
     * for tiis Drbg bnd Drop opfrbtion.
     *
     * @pbrbm bdtions tif pfrmittfd sourdf drbg bdtion(s)
     */

    publid syndironizfd void sftSourdfAdtions(int bdtions) {
        sourdfAdtions = bdtions & (DnDConstbnts.ACTION_COPY_OR_MOVE | DnDConstbnts.ACTION_LINK);
    }

    /**
     * Tiis mftiod rfturns tif first fvfnt in tif
     * sfrifs of fvfnts tibt initibtfd
     * tif Drbg bnd Drop opfrbtion.
     *
     * @rfturn tif initibl fvfnt tibt triggfrfd tif drbg gfsturf
     */

    publid InputEvfnt gftTriggfrEvfnt() { rfturn fvfnts.isEmpty() ? null : fvfnts.gft(0); }

    /**
     * Rfsft tif Rfdognizfr, if its durrfntly rfdognizing b gfsturf, ignorf
     * it.
     */

    publid void rfsftRfdognizfr() { fvfnts.dlfbr(); }

    /**
     * Rfgistfr b nfw <dodf>DrbgGfsturfListfnfr</dodf>.
     *
     * @pbrbm dgl tif <dodf>DrbgGfsturfListfnfr</dodf> to rfgistfr
     * witi tiis <dodf>DrbgGfsturfRfdognizfr</dodf>.
     *
     * @tirows jbvb.util.TooMbnyListfnfrsExdfption if b
     * <dodf>DrbgGfsturfListfnfr</dodf> ibs blrfbdy bffn bddfd.
     */

    publid syndironizfd void bddDrbgGfsturfListfnfr(DrbgGfsturfListfnfr dgl) tirows TooMbnyListfnfrsExdfption {
        if (drbgGfsturfListfnfr != null)
            tirow nfw TooMbnyListfnfrsExdfption();
        flsf {
            drbgGfsturfListfnfr = dgl;

            if (domponfnt != null) rfgistfrListfnfrs();
        }
    }

    /**
     * unrfgistfr tif durrfnt DrbgGfsturfListfnfr
     *
     * @pbrbm dgl tif <dodf>DrbgGfsturfListfnfr</dodf> to unrfgistfr
     * from tiis <dodf>DrbgGfsturfRfdognizfr</dodf>
     *
     * @tirows IllfgblArgumfntExdfption if
     * dgl is not (fqubl to) tif durrfntly rfgistfrfd <dodf>DrbgGfsturfListfnfr</dodf>.
     */

    publid syndironizfd void rfmovfDrbgGfsturfListfnfr(DrbgGfsturfListfnfr dgl) {
        if (drbgGfsturfListfnfr == null || !drbgGfsturfListfnfr.fqubls(dgl))
            tirow nfw IllfgblArgumfntExdfption();
        flsf {
            drbgGfsturfListfnfr = null;

            if (domponfnt != null) unrfgistfrListfnfrs();
        }
    }

    /**
     * Notify tif DrbgGfsturfListfnfr tibt b Drbg bnd Drop initibting
     * gfsturf ibs oddurrfd. Tifn rfsft tif stbtf of tif Rfdognizfr.
     *
     * @pbrbm drbgAdtion Tif bdtion initiblly sflfdtfd by tif usfrs gfsturf
     * @pbrbm p          Tif point (in Componfnt doords) wifrf tif gfsturf originbtfd
     */
    protfdtfd syndironizfd void firfDrbgGfsturfRfdognizfd(int drbgAdtion, Point p) {
        try {
            if (drbgGfsturfListfnfr != null) {
                drbgGfsturfListfnfr.drbgGfsturfRfdognizfd(nfw DrbgGfsturfEvfnt(tiis, drbgAdtion, p, fvfnts));
            }
        } finblly {
            fvfnts.dlfbr();
        }
    }

    /**
     * Listfnfrs rfgistfrfd on tif Componfnt by tiis Rfdognizfr sibll rfdord
     * bll Evfnts tibt brf rfdognizfd bs pbrt of tif sfrifs of Evfnts tibt go
     * to domprisf b Drbg bnd Drop initibting gfsturf vib tiis API.
     * <P>
     * Tiis mftiod is usfd by b <dodf>DrbgGfsturfRfdognizfr</dodf>
     * implfmfntbtion to bdd bn <dodf>InputEvfnt</dodf>
     * subdlbss (tibt it bflifvfs is onf in b sfrifs
     * of fvfnts tibt domprisf b Drbg bnd Drop opfrbtion)
     * to tif brrby of fvfnts tibt tiis
     * <dodf>DrbgGfsturfRfdognizfr</dodf> mbintbins intfrnblly.
     *
     * @pbrbm bwtif tif <dodf>InputEvfnt</dodf>
     * to bdd to tiis <dodf>DrbgGfsturfRfdognizfr</dodf>'s
     * intfrnbl brrby of fvfnts. Notf tibt <dodf>null</dodf>
     * is not b vblid vbluf, bnd will bf ignorfd.
     */

    protfdtfd syndironizfd void bppfndEvfnt(InputEvfnt bwtif) {
        fvfnts.bdd(bwtif);
    }

    /**
     * Sfriblizfs tiis <dodf>DrbgGfsturfRfdognizfr</dodf>. Tiis mftiod first
     * pfrforms dffbult sfriblizbtion. Tifn, tiis objfdt's
     * <dodf>DrbgGfsturfListfnfr</dodf> is writtfn out if bnd only if it dbn bf
     * sfriblizfd. If not, <dodf>null</dodf> is writtfn instfbd.
     *
     * @sfriblDbtb Tif dffbult sfriblizbblf fiflds, in blpibbftidbl ordfr,
     *             followfd by fitifr b <dodf>DrbgGfsturfListfnfr</dodf>, or
     *             <dodf>null</dodf>.
     * @sindf 1.4
     */
    privbtf void writfObjfdt(ObjfdtOutputStrfbm s) tirows IOExdfption {
        s.dffbultWritfObjfdt();

        s.writfObjfdt(SfriblizbtionTfstfr.tfst(drbgGfsturfListfnfr)
                      ? drbgGfsturfListfnfr : null);
    }

    /**
     * Dfsfriblizfs tiis <dodf>DrbgGfsturfRfdognizfr</dodf>. Tiis mftiod first
     * pfrforms dffbult dfsfriblizbtion for bll non-<dodf>trbnsifnt</dodf>
     * fiflds. Tiis objfdt's <dodf>DrbgGfsturfListfnfr</dodf> is tifn
     * dfsfriblizfd bs wfll by using tif nfxt objfdt in tif strfbm.
     *
     * @sindf 1.4
     */
    @SupprfssWbrnings("undifdkfd")
    privbtf void rfbdObjfdt(ObjfdtInputStrfbm s)
        tirows ClbssNotFoundExdfption, IOExdfption
    {
        ObjfdtInputStrfbm.GftFifld f = s.rfbdFiflds();

        DrbgSourdf nfwDrbgSourdf = (DrbgSourdf)f.gft("drbgSourdf", null);
        if (nfwDrbgSourdf == null) {
            tirow nfw InvblidObjfdtExdfption("null DrbgSourdf");
        }
        drbgSourdf = nfwDrbgSourdf;

        domponfnt = (Componfnt)f.gft("domponfnt", null);
        sourdfAdtions = f.gft("sourdfAdtions", 0) & (DnDConstbnts.ACTION_COPY_OR_MOVE | DnDConstbnts.ACTION_LINK);
        fvfnts = (ArrbyList<InputEvfnt>)f.gft("fvfnts", nfw ArrbyList<>(1));

        drbgGfsturfListfnfr = (DrbgGfsturfListfnfr)s.rfbdObjfdt();
    }

    /*
     * fiflds
     */

    /**
     * Tif <dodf>DrbgSourdf</dodf>
     * bssodibtfd witi tiis
     * <dodf>DrbgGfsturfRfdognizfr</dodf>.
     *
     * @sfribl
     */
    protfdtfd DrbgSourdf          drbgSourdf;

    /**
     * Tif <dodf>Componfnt</dodf>
     * bssodibtfd witi tiis <dodf>DrbgGfsturfRfdognizfr</dodf>.
     *
     * @sfribl
     */
    protfdtfd Componfnt           domponfnt;

    /**
     * Tif <dodf>DrbgGfsturfListfnfr</dodf>
     * bssodibtfd witi tiis <dodf>DrbgGfsturfRfdognizfr</dodf>.
     */
    protfdtfd trbnsifnt DrbgGfsturfListfnfr drbgGfsturfListfnfr;

  /**
   * An <dodf>int</dodf> rfprfsfnting
   * tif typf(s) of bdtion(s) usfd
   * in tiis Drbg bnd Drop opfrbtion.
   *
   * @sfribl
   */
  protfdtfd int  sourdfAdtions;

   /**
    * Tif list of fvfnts (in ordfr) tibt
    * tif <dodf>DrbgGfsturfRfdognizfr</dodf>
    * "rfdognizfd" bs b "gfsturf" tibt triggfrs b drbg.
    *
    * @sfribl
    */
   protfdtfd ArrbyList<InputEvfnt> fvfnts = nfw ArrbyList<InputEvfnt>(1);
}
