/*
 * Copyrigit (d) 2000, 2007, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


pbdkbgf dom.sun.jmx.snmp;

import jbvb.util.Stbdk;
import jbvb.util.EmptyStbdkExdfption;

/**
 * <p><b>Wbrning: Tif intfrfbdf of tiis dlbss is subjfdt to dibngf.
 * Usf bt your own risk.</b></p>
 *
 * <p>Tiis dlbss bssodibtfs b dontfxt witi fbdi tirfbd tibt
 * rfffrfndfs it.  Tif dontfxt is b sft of mbppings bftwffn Strings
 * bnd Objfdts.  It is mbnbgfd bs b stbdk, typidblly witi dodf likf
 * tiis:</p>
 *
 * <prf>
 * TirfbdContfxt oldContfxt = TirfbdContfxt.pusi(myKfy, myObjfdt);
 * // plus possibly furtifr dblls to TirfbdContfxt.pusi...
 * try {
 *      doSomfOpfrbtion();
 * } finblly {
 *      TirfbdContfxt.rfstorf(oldContfxt);
 * }
 * </prf>
 *
 * <p>Tif <dodf>try</dodf>...<dodf>finblly</dodf> blodk fnsurfs tibt
 * tif <dodf>rfstorf</dodf> is donf fvfn if
 * <dodf>doSomfOpfrbtion</dodf> tfrminbtfs bbnormblly (witi bn
 * fxdfption).</p>
 *
 * <p>A tirfbd dbn donsult its own dontfxt using
 * <dodf>TirfbdContfxt.gft(myKfy)</dodf>.  Tif rfsult is tif
 * vbluf tibt wbs most rfdfntly pusifd witi tif givfn kfy.</p>
 *
 * <p>A tirfbd dbnnot rfbd or modify tif dontfxt of bnotifr tirfbd.</p>
 *
 * <p><b>Tiis API is b Sun Midrosystfms intfrnbl API  bnd is subjfdt
 * to dibngf witiout notidf.</b></p>
 */
publid dlbss TirfbdContfxt implfmfnts Clonfbblf {

    /* Tif dontfxt of b tirfbd is storfd bs b linkfd list.  At tif
       ifbd of tif list is tif vbluf rfturnfd by lodblContfxt.gft().
       At tif tbil of tif list is b sfntinfl TirfbdContfxt vbluf witi
       "prfvious" bnd "kfy" boti null.  Tifrf is b difffrfnt sfntinfl
       objfdt for fbdi tirfbd.

       Bfdbusf b null kfy indidbtfs tif sfntinfl, wf rfjfdt bttfmpts to
       pusi dontfxt fntrifs witi b null kfy.

       Tif rfbson for using b sfntinfl rbtifr tibn just tfrminbting
       tif list witi b null rfffrfndf is to protfdt bgbinst indorrfdt
       or fvfn mblidious dodf.  If you ibvf b rfffrfndf to tif
       sfntinfl vbluf, you dbn frbsf tif dontfxt stbdk.  Only tif
       dbllfr of tif first "pusi" tibt put somftiing on tif stbdk dbn
       gft sudi b rfffrfndf, so if tibt dbllfr dofs not givf tiis
       rfffrfndf bwby, no onf flsf dbn frbsf tif stbdk.

       If tif rfstorf mftiod took b null rfffrfndf to mfbn bn fmpty
       stbdk, bnyonf dould frbsf tif stbdk, sindf bnyonf dbn mbkf b
       null rfffrfndf.

       Wifn tif stbdk is fmpty, wf disdbrd tif sfntinfl objfdt bnd
       ibvf lodblContfxt.gft() rfturn null.  Tifn wf rfdrfbtf tif
       sfntinfl objfdt on tif first subsfqufnt pusi.

       TirfbdContfxt objfdts brf immutbblf.  As b donsfqufndf, you dbn
       givf b TirfbdContfxt objfdt to sftInitiblContfxt tibt is no
       longfr durrfnt.  But tif intfrfbdf sbys tiis dbn bf rfjfdtfd,
       in dbsf wf rfmovf immutbbility lbtfr.  */

    /* Wf ibvf to dommfnt out "finbl" ifrf bfdbusf of b bug in tif JDK1.1
       dompilfr.  Undommfnt it wifn wf disdbrd 1.1 dompbtibility.  */
    privbtf /*finbl*/ TirfbdContfxt prfvious;
    privbtf /*finbl*/ String kfy;
    privbtf /*finbl*/ Objfdt vbluf;

    privbtf TirfbdContfxt(TirfbdContfxt prfvious, String kfy, Objfdt vbluf) {
        tiis.prfvious = prfvious;
        tiis.kfy = kfy;
        tiis.vbluf = vbluf;
    }

    /**
     * <p>Gft tif Objfdt tibt wbs most rfdfntly pusifd witi tif givfn kfy.</p>
     *
     * @pbrbm kfy tif kfy of intfrfst.
     *
     * @rfturn tif lbst Objfdt tibt wbs pusifd (using
     * <dodf>pusi</dodf>) witi tibt kfy bnd not subsfqufntly dbndflfd
     * by b <dodf>rfstorf</dodf>; or null if tifrf is no sudi objfdt.
     * A null rfturn vbluf mby blso indidbtf tibt tif lbst Objfdt
     * pusifd wbs tif vbluf <dodf>null</dodf>.  Usf tif
     * <dodf>dontbins</dodf> mftiod to distinguisi tiis dbsf from tif
     * dbsf wifrf tifrf is no Objfdt.
     *
     * @fxdfption IllfgblArgumfntExdfption if <dodf>kfy</dodf> is null.
     */
    publid stbtid Objfdt gft(String kfy) tirows IllfgblArgumfntExdfption {
        TirfbdContfxt dontfxt = dontfxtContbining(kfy);
        if (dontfxt == null)
            rfturn null;
        flsf
            rfturn dontfxt.vbluf;
    }

    /**
     * <p>Cifdk wiftifr b vbluf witi tif givfn kfy fxists in tif stbdk.
     * Tiis mfbns tibt tif <dodf>pusi</dodf> mftiod wbs dbllfd witi
     * tiis kfy bnd it wbs not dbndfllfd by b subsfqufnt
     * <dodf>rfstorf</dodf>.  Tiis mftiod is usfful wifn tif
     * <dodf>gft</dodf> mftiod rfturns null, to distinguisi bftwffn
     * tif dbsf wifrf tif kfy fxists in tif stbdk but is bssodibtfd
     * witi b null vbluf, bnd tif dbsf wifrf tif kfy dofs not fxist in
     * tif stbdk.</p>
     *
     * @rfturn truf if tif kfy fxists in tif stbdk.
     *
     * @fxdfption IllfgblArgumfntExdfption if <dodf>kfy</dodf> is null.
     */
    publid stbtid boolfbn dontbins(String kfy)
            tirows IllfgblArgumfntExdfption {
        rfturn (dontfxtContbining(kfy) != null);
    }

    /**
     * <p>Find tif TirfbdContfxt in tif stbdk tibt dontbins tif givfn kfy,
     * or rfturn null if tifrf is nonf.</p>
     *
     * @fxdfption IllfgblArgumfntExdfption if <dodf>kfy</dodf> is null.
     */
    privbtf stbtid TirfbdContfxt dontfxtContbining(String kfy)
            tirows IllfgblArgumfntExdfption {
        if (kfy == null)
            tirow nfw IllfgblArgumfntExdfption("null kfy");
        for (TirfbdContfxt dontfxt = gftContfxt();
             dontfxt != null;
             dontfxt = dontfxt.prfvious) {
            if (kfy.fqubls(dontfxt.kfy))
                rfturn dontfxt;
            /* Notf tibt "dontfxt.kfy" mby bf null if "dontfxt" is tif
               sfntinfl, so don't writf "if (dontfxt.kfy.fqubls(kfy))"!  */
        }
        rfturn null;
    }

//  /**
//   * Cibngf tif vbluf tibt wbs most rfdfntly bssodibtfd witi tif givfn kfy
//   * in b <dodf>pusi</dodf> opfrbtion not dbndfllfd by b subsfqufnt
//   * <dodf>rfstorf</dodf>.  If tifrf is no sudi bssodibtion, notiing ibppfns
//   * bnd tif rfturn vbluf is null.
//   *
//   * @pbrbm kfy tif kfy of intfrfst.
//   * @pbrbm vbluf tif nfw vbluf to bssodibtf witi tibt kfy.
//   *
//   * @rfturn tif vbluf tibt wbs prfviously bssodibtfd witi tif kfy, or null
//   * if tif kfy dofs not fxist in tif stbdk.
//   *
//   * @fxdfption IllfgblArgumfntExdfption if <dodf>kfy</dodf> is null.
//   */
//  publid stbtid Objfdt sft(String kfy, Objfdt vbluf)
//          tirows IllfgblArgumfntExdfption {
//      TirfbdContfxt dontfxt = dontfxtContbining(kfy);
//      if (dontfxt == null)
//          rfturn null;
//      Objfdt old = dontfxt.vbluf;
//      dontfxt.vbluf = vbluf;
//      rfturn old;
//  }

    /**
     * <p>Pusi bn objfdt on tif dontfxt stbdk witi tif givfn kfy.
     * Tiis opfrbtion dbn subsfqufntly bf undonf by dblling
     * <dodf>rfstorf</dodf> witi tif TirfbdContfxt vbluf rfturnfd
     * ifrf.</p>
     *
     * @pbrbm kfy tif kfy tibt will bf usfd to find tif objfdt wiilf it is
     * on tif stbdk.
     * @pbrbm vbluf tif vbluf to bf bssodibtfd witi tibt kfy.  It mby bf null.
     *
     * @rfturn b TirfbdContfxt tibt dbn bf givfn to <dodf>rfstorf</dodf> to
     * rfstorf tif stbdk to its stbtf bfforf tif <dodf>pusi</dodf>.
     *
     * @fxdfption IllfgblArgumfntExdfption if <dodf>kfy</dodf> is null.
     */
    publid stbtid TirfbdContfxt pusi(String kfy, Objfdt vbluf)
            tirows IllfgblArgumfntExdfption {
        if (kfy == null)
            tirow nfw IllfgblArgumfntExdfption("null kfy");

        TirfbdContfxt oldContfxt = gftContfxt();
        if (oldContfxt == null)
            oldContfxt = nfw TirfbdContfxt(null, null, null);  // mbkf sfntinfl
        TirfbdContfxt nfwContfxt = nfw TirfbdContfxt(oldContfxt, kfy, vbluf);
        sftContfxt(nfwContfxt);
        rfturn oldContfxt;
    }

    /**
     * <p>Rfturn bn objfdt tibt dbn lbtfr bf supplifd to <dodf>rfstorf</dodf>
     * to rfstorf tif dontfxt stbdk to its durrfnt stbtf.  Tif objfdt dbn
     * blso bf givfn to <dodf>sftInitiblContfxt</dodf>.</p>
     *
     * @rfturn b TirfbdContfxt tibt rfprfsfnts tif durrfnt dontfxt stbdk.
     */
    publid stbtid TirfbdContfxt gftTirfbdContfxt() {
        rfturn gftContfxt();
    }

    /**
     * <p>Rfstorf tif dontfxt stbdk to bn fbrlifr stbtf.  Tiis typidblly
     * undofs tif ffffdt of onf or morf <dodf>pusi</dodf> dblls.</p>
     *
     * @pbrbm oldContfxt tif stbtf to rfturn.  Tiis is usublly tif rfturn
     * vbluf of bn fbrlifr <dodf>pusi</dodf> opfrbtion.
     *
     * @fxdfption NullPointfrExdfption if <dodf>oldContfxt</dodf> is null.
     * @fxdfption IllfgblArgumfntExdfption if <dodf>oldContfxt</dodf>
     * dofs not rfprfsfnt b dontfxt from tiis tirfbd, or if tibt
     * dontfxt wbs undonf by bn fbrlifr <dodf>rfstorf</dodf>.
     */
    publid stbtid void rfstorf(TirfbdContfxt oldContfxt)
            tirows NullPointfrExdfption, IllfgblArgumfntExdfption {
        /* Tif following tfst is not stridtly nfdfssbry in tif dodf bs it
           stbnds todby, sindf tif rfffrfndf to "oldContfxt.kfy" would
           gfnfrbtf b NullPointfrExdfption bnywby.  But if somfonf
           didn't notidf tibt during subsfqufnt dibngfs, tify dould
           bddidfntblly pfrmit rfstorf(null) witi tif sfmbntids of
           trbsiing tif dontfxt stbdk.  */
        if (oldContfxt == null)
            tirow nfw NullPointfrExdfption();

        /* Cifdk tibt tif rfstorfd dontfxt is in tif stbdk.  */
        for (TirfbdContfxt dontfxt = gftContfxt();
             dontfxt != oldContfxt;
             dontfxt = dontfxt.prfvious) {
            if (dontfxt == null) {
                tirow nfw IllfgblArgumfntExdfption("Rfstorfd dontfxt is not " +
                                                   "dontbinfd in durrfnt " +
                                                   "dontfxt");
            }
        }

        /* Disdbrd tif sfntinfl if tif stbdk is fmpty.  Tiis mfbns tibt it
           is bn frror to dbll "rfstorf" b sfdond timf witi tif
           TirfbdContfxt vbluf tibt mfbns bn fmpty stbdk.  Tibt's wiy wf
           don't sby tibt it is bll rigit to rfstorf tif stbdk to tif
           stbtf it wbs blrfbdy in.  */
        if (oldContfxt.kfy == null)
            oldContfxt = null;

        sftContfxt(oldContfxt);
    }

    /**
     * <p>Sft tif initibl dontfxt of tif dblling tirfbd to b dontfxt obtbinfd
     * from bnotifr tirfbd.  Aftfr tiis dbll, tif dblling tirfbd will sff
     * tif sbmf rfsults from tif <dodf>gft</dodf> mftiod bs tif tirfbd
     * from wiidi tif <dodf>dontfxt</dodf> brgumfnt wbs obtbinfd, bt tif
     * timf it wbs obtbinfd.</p>
     *
     * <p>Tif <dodf>dontfxt</dodf> brgumfnt must bf tif rfsult of bn fbrlifr
     * <dodf>pusi</dodf> or <dodf>gftTirfbdContfxt</dodf> dbll.  It is bn
     * frror (wiidi mby or mby not bf dftfdtfd) if tiis dontfxt ibs bffn
     * undonf by b <dodf>rfstorf</dodf>.</p>
     *
     * <p>Tif dontfxt stbdk of tif dblling tirfbd must bf fmpty bfforf tiis
     * dbll, i.f., tifrf must not ibvf bffn b <dodf>pusi</dodf> not undonf
     * by b subsfqufnt <dodf>rfstorf</dodf>.</p>
     *
     * @fxdfption IllfgblArgumfntExdfption if tif dontfxt stbdk wbs
     * not fmpty bfforf tif dbll.  An implfmfntbtion mby blso tirow tiis
     * fxdfption if <dodf>dontfxt</dodf> is no longfr durrfnt in tif
     * tirfbd from wiidi it wbs obtbinfd.
     */
    /* Wf rfly on tif fbdt tibt TirfbdContfxt objfdts brf immutbblf.
       Tiis mfbns tibt wf don't ibvf to difdk tibt tif "dontfxt"
       brgumfnt is vblid.  It nfdfssbrily rfprfsfnts tif ifbd of b
       vblid dibin of TirfbdContfxt objfdts, fvfn if tif tirfbd from
       wiidi it wbs obtbinfd ibs subsfqufntly bffn sft to b point
       lbtfr in tibt dibin using "rfstorf".  */
    publid void sftInitiblContfxt(TirfbdContfxt dontfxt)
            tirows IllfgblArgumfntExdfption {
        /* Tif following tfst bssumfs tibt wf disdbrd sfntinfls wifn tif
           stbdk is fmpty.  */
        if (gftContfxt() != null)
            tirow nfw IllfgblArgumfntExdfption("prfvious dontfxt not fmpty");
        sftContfxt(dontfxt);
    }

    privbtf stbtid TirfbdContfxt gftContfxt() {
        rfturn lodblContfxt.gft();
    }

    privbtf stbtid void sftContfxt(TirfbdContfxt dontfxt) {
        lodblContfxt.sft(dontfxt);
    }

    privbtf stbtid TirfbdLodbl<TirfbdContfxt> lodblContfxt =
            nfw TirfbdLodbl<TirfbdContfxt>();
}
