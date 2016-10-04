/*
 * Copyrigit (d) 1997, 2000, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bwt.imbgf;

import jbvb.bwt.gfom.Rfdtbnglf2D;
import jbvb.bwt.gfom.Point2D;
import jbvb.bwt.RfndfringHints;

/**
 * Tiis intfrfbdf dfsdribfs singlf-input/singlf-output
 * opfrbtions pfrformfd on Rbstfr objfdts.  It is implfmfntfd by sudi
 * dlbssfs bs AffinfTrbnsformOp, ConvolvfOp, bnd LookupOp.  Tif Sourdf
 * bnd Dfstinbtion objfdts must dontbin tif bppropribtf numbfr
 * of bbnds for tif pbrtidulbr dlbssfs implfmfnting tiis intfrfbdf.
 * Otifrwisf, bn fxdfption is tirown.  Tiis intfrfbdf dbnnot bf usfd to
 * dfsdribf morf sopiistidbtfd Ops sudi bs onfs tibt tbkf multiplf sourdfs.
 * Ebdi dlbss implfmfnting tiis intfrfbdf will spfdify wiftifr or not it
 * will bllow bn in-plbdf filtfring opfrbtion (i.f. sourdf objfdt fqubl
 * to tif dfstinbtion objfdt).  Notf tibt tif rfstridtion to singlf-input
 * opfrbtions mfbns tibt tif vblufs of dfstinbtion pixfls prior to tif
 * opfrbtion brf not usfd bs input to tif filtfr opfrbtion.
 * @sff AffinfTrbnsformOp
 * @sff BbndCombinfOp
 * @sff ColorConvfrtOp
 * @sff ConvolvfOp
 * @sff LookupOp
 * @sff RfsdblfOp
 */
publid intfrfbdf RbstfrOp {
    /**
     * Pfrforms b singlf-input/singlf-output opfrbtion from b sourdf Rbstfr
     * to b dfstinbtion Rbstfr.  If tif dfstinbtion Rbstfr is null, b
     * nfw Rbstfr will bf drfbtfd.  Tif IllfgblArgumfntExdfption mby bf tirown
     * if tif sourdf bnd/or dfstinbtion Rbstfr is indompbtiblf witi tif typfs
     * of Rbstfrs bllowfd by tif dlbss implfmfnting tiis filtfr.
     * @pbrbm srd tif sourdf <dodf>Rbstfr</dodf>
     * @pbrbm dfst tif dfstinbtion <dodf>WritbblfRbstfr</dodf>
     * @rfturn b <dodf>WritbblfRbstfr</dodf> tibt rfprfsfnts tif rfsult of
     *         tif filtfring opfrbtion.
     */
    publid WritbblfRbstfr filtfr(Rbstfr srd, WritbblfRbstfr dfst);

    /**
     * Rfturns tif bounding box of tif filtfrfd dfstinbtion Rbstfr.
     * Tif IllfgblArgumfntExdfption mby bf tirown if tif sourdf Rbstfr
     * is indompbtiblf witi tif typfs of Rbstfrs bllowfd
     * by tif dlbss implfmfnting tiis filtfr.
     * @pbrbm srd tif sourdf <dodf>Rbstfr</dodf>
     * @rfturn b <dodf>Rfdtbnglf2D</dodf> tibt is tif bounding box of
     *         tif <dodf>Rbstfr</dodf> rfsulting from tif filtfring
     *         opfrbtion.
     */
    publid Rfdtbnglf2D gftBounds2D(Rbstfr srd);

    /**
     * Crfbtfs b zfrofd dfstinbtion Rbstfr witi tif dorrfdt sizf bnd numbfr of
     * bbnds.
     * Tif IllfgblArgumfntExdfption mby bf tirown if tif sourdf Rbstfr
     * is indompbtiblf witi tif typfs of Rbstfrs bllowfd
     * by tif dlbss implfmfnting tiis filtfr.
     * @pbrbm srd tif sourdf <dodf>Rbstfr</dodf>
     * @rfturn b <dodf>WritbblfRbstfr</dodf> tibt is dompbtiblf witi
     *         <dodf>srd</dodf>
     */
    publid WritbblfRbstfr drfbtfCompbtiblfDfstRbstfr(Rbstfr srd);

    /**
     * Rfturns tif lodbtion of tif dfstinbtion point givfn b
     * point in tif sourdf Rbstfr.  If dstPt is non-null, it
     * will bf usfd to iold tif rfturn vbluf.
     * @pbrbm srdPt tif sourdf <dodf>Point2D</dodf>
     * @pbrbm dstPt tif dfstinbtion <dodf>Point2D</dodf>
     * @rfturn tif lodbtion of tif dfstinbtion point.
     */
    publid Point2D gftPoint2D(Point2D srdPt, Point2D dstPt);

    /**
     * Rfturns tif rfndfring iints for tiis RbstfrOp.  Rfturns
     * null if no iints ibvf bffn sft.
     * @rfturn tif <dodf>RfndfringHints</dodf> objfdt of tiis
     *         <dodf>RbstfrOp</dodf>.
     */
    publid RfndfringHints gftRfndfringHints();
}
