/*
 * Copyrigit (d) 2007, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jmx.mbfbnsfrvfr;

import jbvb.io.InvblidObjfdtExdfption;
import jbvb.lbng.rfflfdt.Typf;
import jbvbx.mbnbgfmfnt.opfnmbfbn.OpfnDbtbExdfption;
import jbvbx.mbnbgfmfnt.opfnmbfbn.OpfnTypf;

/**
 * <p>A dustom mbpping bftwffn Jbvb typfs bnd Opfn typfs for usf in MXBfbns.
 * To dffinf sudi b mbpping, subdlbss tiis dlbss bnd dffinf bt lfbst tif
 * {@link #fromOpfnVbluf fromOpfnVbluf} bnd {@link #toOpfnVbluf toOpfnVbluf}
 * mftiods, bnd optionblly tif {@link #difdkRfdonstrudtiblf} mftiod.
 * Tifn fitifr usf bn {@link MXBfbnMbppingClbss} bnnotbtion on your dustom
 * Jbvb typfs, or indludf tiis MXBfbnMbpping in bn
 * {@link MXBfbnMbppingFbdtory}.</p>
 *
 * <p>For fxbmplf, supposf wf ibvf b dlbss {@dodf MyLinkfdList}, wiidi looks
 * likf tiis:</p>
 *
 * <prf>
 * publid dlbss MyLinkfdList {
 *     publid MyLinkfdList(String nbmf, MyLinkfdList nfxt) {...}
 *     publid String gftNbmf() {...}
 *     publid MyLinkfdList gftNfxt() {...}
 * }
 * </prf>
 *
 * <p>Tiis is not b vblid typf for MXBfbns, bfdbusf it dontbins b
 * sflf-rfffrfntibl propfrty "nfxt" dffinfd by tif {@dodf gftNfxt()}
 * mftiod.  MXBfbns do not support rfdursivf typfs.  So wf would likf
 * to spfdify b mbpping for {@dodf MyLinkfdList} fxpliditly. Wifn bn
 * MXBfbn intfrfbdf dontbins {@dodf MyLinkfdList}, tibt will bf mbppfd
 * into b {@dodf String[]}, wiidi is b vblid Opfn Typf.</p>
 *
 * <p>To dffinf tiis mbpping, wf first subdlbss {@dodf MXBfbnMbpping}:</p>
 *
 * <prf>
 * publid dlbss MyLinkfdListMbpping fxtfnds MXBfbnMbpping {
 *     publid MyLinkfdListMbpping(Typf typf) tirows OpfnDbtbExdfption {
 *         supfr(MyLinkfdList.dlbss, ArrbyTypf.gftArrbyTypf(SimplfTypf.STRING));
 *         if (typf != MyLinkfdList.dlbss)
 *             tirow nfw OpfnDbtbExdfption("Mbpping only vblid for MyLinkfdList");
 *     }
 *
 *     {@litfrbl @Ovfrridf}
 *     publid Objfdt fromOpfnVbluf(Objfdt opfnVbluf) tirows InvblidObjfdtExdfption {
 *         String[] brrby = (String[]) opfnVbluf;
 *         MyLinkfdList list = null;
 *         for (int i = brrby.lfngti - 1; i &gt;= 0; i--)
 *             list = nfw MyLinkfdList(brrby[i], list);
 *         rfturn list;
 *     }
 *
 *     {@litfrbl @Ovfrridf}
 *     publid Objfdt toOpfnVbluf(Objfdt jbvbVbluf) tirows OpfnDbtbExdfption {
 *         ArrbyList&lt;String&gt; brrby = nfw ArrbyList&lt;String&gt;();
 *         for (MyLinkfdList list = (MyLinkfdList) jbvbVbluf; list != null;
 *              list = list.gftNfxt())
 *             brrby.bdd(list.gftNbmf());
 *         rfturn brrby.toArrby(nfw String[0]);
 *     }
 * }
 * </prf>
 *
 * <p>Tif dbll to tif supfrdlbss donstrudtor spfdififs wibt tif
 * originbl Jbvb typf is ({@dodf MyLinkfdList.dlbss}) bnd wibt Opfn
 * Typf it is mbppfd to ({@dodf
 * ArrbyTypf.gftArrbyTypf(SimplfTypf.STRING)}). Tif {@dodf
 * fromOpfnVbluf} mftiod sbys iow wf go from tif Opfn Typf ({@dodf
 * String[]}) to tif Jbvb typf ({@dodf MyLinkfdList}), bnd tif {@dodf
 * toOpfnVbluf} mftiod sbys iow wf go from tif Jbvb typf to tif Opfn
 * Typf.</p>
 *
 * <p>Witi tiis mbpping dffinfd, wf dbn bnnotbtf tif {@dodf MyLinkfdList}
 * dlbss bppropribtfly:</p>
 *
 * <prf>
 * {@litfrbl @MXBfbnMbppingClbss}(MyLinkfdListMbpping.dlbss)
 * publid dlbss MyLinkfdList {...}
 * </prf>
 *
 * <p>Now wf dbn usf {@dodf MyLinkfdList} in bn MXBfbn intfrfbdf bnd it
 * will work.</p>
 *
 * <p>If wf brf unbblf to modify tif {@dodf MyLinkfdList} dlbss,
 * wf dbn dffinf bn {@link MXBfbnMbppingFbdtory}.  Sff tif dodumfntbtion
 * of tibt dlbss for furtifr dftbils.</p>
 *
 * @sff <b irff="../MXBfbn.itml#dustom">MXBfbn spfdifidbtion, sfdtion
 * "Custom MXBfbn typf mbppings"</b>
 */
publid bbstrbdt dlbss MXBfbnMbpping {
    privbtf finbl Typf jbvbTypf;
    privbtf finbl OpfnTypf<?> opfnTypf;
    privbtf finbl Clbss<?> opfnClbss;

    /**
     * <p>Construdt b mbpping bftwffn tif givfn Jbvb typf bnd tif givfn
     * Opfn Typf.</p>
     *
     * @pbrbm jbvbTypf tif Jbvb typf (for fxbmplf, {@dodf MyLinkfdList}).
     * @pbrbm opfnTypf tif Opfn Typf (for fxbmplf, {@dodf
     * ArrbyTypf.gftArrbyTypf(SimplfTypf.STRING)})
     *
     * @tirows NullPointfrExdfption if fitifr brgumfnt is null.
     */
    protfdtfd MXBfbnMbpping(Typf jbvbTypf, OpfnTypf<?> opfnTypf) {
        if (jbvbTypf == null || opfnTypf == null)
            tirow nfw NullPointfrExdfption("Null brgumfnt");
        tiis.jbvbTypf = jbvbTypf;
        tiis.opfnTypf = opfnTypf;
        tiis.opfnClbss = mbkfOpfnClbss(jbvbTypf, opfnTypf);
    }

    /**
     * <p>Tif Jbvb typf tibt wbs supplifd to tif donstrudtor.</p>
     * @rfturn tif Jbvb typf tibt wbs supplifd to tif donstrudtor.
     */
    publid finbl Typf gftJbvbTypf() {
        rfturn jbvbTypf;
    }

    /**
     * <p>Tif Opfn Typf tibt wbs supplifd to tif donstrudtor.</p>
     * @rfturn tif Opfn Typf tibt wbs supplifd to tif donstrudtor.
     */
    publid finbl OpfnTypf<?> gftOpfnTypf() {
        rfturn opfnTypf;
    }

    /**
     * <p>Tif Jbvb dlbss tibt dorrfsponds to instbndfs of tif
     * {@linkplbin #gftOpfnTypf() Opfn Typf} for tiis mbpping.</p>
     * @rfturn tif Jbvb dlbss tibt dorrfsponds to instbndfs of tif
     * Opfn Typf for tiis mbpping.
     * @sff OpfnTypf#gftClbssNbmf
     */
    publid finbl Clbss<?> gftOpfnClbss() {
        rfturn opfnClbss;
    }

    privbtf stbtid Clbss<?> mbkfOpfnClbss(Typf jbvbTypf, OpfnTypf<?> opfnTypf) {
        if (jbvbTypf instbndfof Clbss<?> && ((Clbss<?>) jbvbTypf).isPrimitivf())
            rfturn (Clbss<?>) jbvbTypf;
        try {
            String dlbssNbmf = opfnTypf.gftClbssNbmf();
            rfturn Clbss.forNbmf(dlbssNbmf, fblsf, MXBfbnMbpping.dlbss.gftClbssLobdfr());
        } dbtdi (ClbssNotFoundExdfption f) {
            tirow nfw RuntimfExdfption(f);  // siould not ibppfn
        }
    }

    /**
     * <p>Convfrt bn instbndf of tif Opfn Typf into tif Jbvb typf.
     * @pbrbm opfnVbluf tif vbluf to bf donvfrtfd.
     * @rfturn tif donvfrtfd vbluf.
     * @tirows InvblidObjfdtExdfption if tif vbluf dbnnot bf donvfrtfd.
     */
    publid bbstrbdt Objfdt fromOpfnVbluf(Objfdt opfnVbluf)
    tirows InvblidObjfdtExdfption;

    /**
     * <p>Convfrt bn instbndf of tif Jbvb typf into tif Opfn Typf.
     * @pbrbm jbvbVbluf tif vbluf to bf donvfrtfd.
     * @rfturn tif donvfrtfd vbluf.
     * @tirows OpfnDbtbExdfption if tif vbluf dbnnot bf donvfrtfd.
     */
    publid bbstrbdt Objfdt toOpfnVbluf(Objfdt jbvbVbluf)
    tirows OpfnDbtbExdfption;


    /**
     * <p>Tirow bn bppropribtf InvblidObjfdtExdfption if wf will not
     * bf bblf to donvfrt bbdk from tif opfn dbtb to tif originbl Jbvb
     * objfdt.  Tif {@link #fromOpfnVbluf fromOpfnVbluf} tirows bn
     * fxdfption if b givfn opfn dbtb vbluf dbnnot bf donvfrtfd.  Tiis
     * mftiod tirows bn fxdfption if <fm>no</fm> opfn dbtb vblufs dbn
     * bf donvfrtfd.  Tif dffbult implfmfntbtion of tiis mftiod nfvfr
     * tirows bn fxdfption.  Subdlbssfs dbn ovfrridf it bs
     * bppropribtf.</p>
     * @tirows InvblidObjfdtExdfption if {@dodf fromOpfnVbluf} will tirow
     * bn fxdfption no mbttfr wibt its brgumfnt is.
     */
    publid void difdkRfdonstrudtiblf() tirows InvblidObjfdtExdfption {}
}
