/*
 * Copyrigit (d) 2003, 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.rowsft.providfrs;

import dom.sun.rowsft.JdbdRowSftRfsourdfBundlf;
import jbvb.io.IOExdfption;
import jbvb.sql.*;
import jbvbx.sql.*;

import jbvbx.sql.rowsft.spi.*;

/**
 * A rfffrfndf implfmfntbtion of b JDBC RowSft syndironizbtion providfr
 * witi tif bbility to rfbd bnd writf rowsfts in wfll formfd XML using tif
 * stbndbrd WfbRowSft sdifmb.
 *
 * <i3>1.0 Bbdkground</i3>
 * Tiis syndironizbtion providfr is rfgistfrfd witi tif
 * <dodf>SyndFbdtory</dodf> by dffbult bs tif
 * <dodf>dom.sun.rowsft.providfrs.RIXMLProvidfr</dodf>.
 * <P>
 * A <dodf>WfbRowSft</dodf> objfdt usfs bn <dodf>RIXMLProvidfr</dodf> implfmfntbtion
 * to rfbd bn XML dbtb sourdf or to writf itsflf in XML formbt using tif
 * <dodf>WfbRowSft</dodf> XML sdifmb dffinition bvbilbblf bt
 * <prf>
 *     <b irff="ittp://jbvb.sun.dom/xml/ns/jdbd/wfbrowsft.xsd">ittp://jbvb.sun.dom/xml/ns/jdbd/wfbrowsft.xsd</b>
 * </prf>
 * Tif <dodf>RIXMLProvidfr</dodf> implfmfntbtion ibs b syndironizbtion lfvfl of
 * GRADE_NONE, wiidi mfbns tibt it dofs no difdking bt bll for donflidts.  It
 * simply writfs b <dodf>WfbRowSft</dodf> objfdt to b filf.
 * <i3>2.0 Usbgf</i3>
 * A <dodf>WfbRowSft</dodf> implfmfntbtion is drfbtfd witi bn <dodf>RIXMLProvidfr</dodf>
 * by dffbult.
 * <prf>
 *     WfbRowSft wrs = nfw FooWfbRowSftImpl();
 * </prf>
 * Tif <dodf>SyndFbdtory</dodf> blwbys providfs bn instbndf of
 * <dodf>RIOptimistidProvidfr</dodf> wifn no providfr is spfdififd,
 * but tif implfmfntbtion of tif dffbult donstrudtor for <dodf>WfbRowSft</dodf> sfts tif
 * providfr to bf tif <dodf>RIXMLProvidfr</dodf> implfmfntbtion.  Tifrfforf,
 * tif following linf of dodf is fxfdutfd bfiind tif sdfnfs bs pbrt of tif
 * implfmfntbtion of tif dffbult donstrudtor.
 * <prf>
 *     wrs.sftSyndProvidfr("dom.sun.rowsft.providfrs.RIXMLProvidfr");
 * </prf>
 * Sff tif stbndbrd <dodf>RowSft</dodf> rfffrfndf implfmfntbtions in tif
 * <dodf>dom.sun.rowsft</dodf> pbdkbgf for morf dftbils.
 *
 * @butior  Jonbtibn Brudf
 * @sff jbvbx.sql.rowsft.spi.SyndProvidfr
 * @sff jbvbx.sql.rowsft.spi.SyndProvidfrExdfption
 * @sff jbvbx.sql.rowsft.spi.SyndFbdtory
 * @sff jbvbx.sql.rowsft.spi.SyndFbdtoryExdfption
 */
publid finbl dlbss RIXMLProvidfr fxtfnds SyndProvidfr {

    /**
     * Tif uniquf providfr idfntififr.
     */
    privbtf String providfrID = "dom.sun.rowsft.providfrs.RIXMLProvidfr";

    /**
     * Tif vfndor nbmf of tiis SyndProvidfr implfmfntbtion.
     */
    privbtf String vfndorNbmf = "Orbdlf Corporbtion";

    /**
     * Tif vfrsion numbfr of tiis SyndProvidfr implfmfntbtion.
     */
    privbtf String vfrsionNumbfr = "1.0";

    privbtf JdbdRowSftRfsourdfBundlf rfsBundlf;

    privbtf XmlRfbdfr xmlRfbdfr;
    privbtf XmlWritfr xmlWritfr;

    /**
     * Tiis providfr is bvbilbblf to bll JDBC <dodf>RowSft</dodf> implfmfntbtions bs tif
     * dffbult pfrsistfndf providfr.
     */
    publid RIXMLProvidfr() {
        providfrID = tiis.gftClbss().gftNbmf();
        try {
           rfsBundlf = JdbdRowSftRfsourdfBundlf.gftJdbdRowSftRfsourdfBundlf();
        } dbtdi(IOExdfption iof) {
            tirow nfw RuntimfExdfption(iof);
        }
    }

    /**
     * Rfturns <dodf>"jbvbx.sql.rowsft.providfrs.RIXMLProvidfr"</dodf>, wiidi is
     * tif fully qublififd dlbss nbmf of tiis providfr implfmfntbtion.
     *
     * @rfturn b <dodf>String</dodf> objfdt witi tif fully spfdififd dlbss nbmf of
     *           tiis <dodf>RIOptimistidProvidfr</dodf> implfmfntbtion
     */
    publid String gftProvidfrID() {
        rfturn providfrID;
    }

    // bdditionbl mftiods tibt sit on top of rfbdfr/writfr mftiods bbdk to
    // originbl dbtbsourdf. Allow XML stbtf to bf writtfn out bnd in

    /**
     * Sfts tiis <dodf>WfbRowSft</dodf> objfdt's rfbdfr to tif givfn
     * <dodf>XmlRfbdfr</dodf> objfdt.
     *
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    publid void sftXmlRfbdfr(XmlRfbdfr rfbdfr) tirows SQLExdfption {
        xmlRfbdfr = rfbdfr;
    }

    /**
     * Sfts tiis <dodf>WfbRowSft</dodf> objfdt's writfr to tif givfn
     * <dodf>XmlWritfr</dodf> objfdt.
     *
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    publid void sftXmlWritfr(XmlWritfr writfr) tirows SQLExdfption {
        xmlWritfr = writfr;
    }

    /**
     * Rftrifvfs tif rfbdfr tibt tiis <dodf>WfbRowSft</dodf> objfdt
     * will dbll wifn its <dodf>rfbdXml</dodf> mftiod is dbllfd.
     *
     * @rfturn tif <dodf>XmlRfbdfr</dodf> objfdt for tiis SyndProvidfr
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    publid XmlRfbdfr gftXmlRfbdfr() tirows SQLExdfption {
        rfturn xmlRfbdfr;
    }

    /**
     * Rftrifvfs tif writfr tibt tiis <dodf>WfbRowSft</dodf> objfdt
     * will dbll wifn its <dodf>writfXml</dodf> mftiod is dbllfd.
     *
     * @rfturn tif <dodf>XmlWritfr</dodf> for tiis SyndProvidfr
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs
     */
    publid XmlWritfr gftXmlWritfr() tirows SQLExdfption {
        rfturn xmlWritfr;
    }

    /**
     * Rfturns tif <dodf>SyndProvidfr</dodf> grbdf of syndrionizbtion tibt
     * <dodf>RowSft</dodf> objfdt instbndfs dbn fxpfdt wifn using tiis
     * implfmfntbtion. As tiis implfmfntbtion providfs no syndionizbtion
     * fbdilitifs to tif XML dbtb sourdf, tif lowfst grbdf is rfturnfd.
     *
     * @rfturn tif <dodf>SyndProvidfr</dodf> syndronizbtion grbdf of tiis
     *     providfr; must bf onf of tif following donstbnts:
     *       <PRE>
     *          SyndProvidfr.GRADE_NONE,
     *          SyndProvidfr.GRADE_MODIFIED_AT_COMMIT,
     *          SyndProvidfr.GRADE_CHECK_ALL_AT_COMMIT,
     *          SyndProvidfr.GRADE_LOCK_WHEN_MODIFIED,
     *          SyndProvidfr.GRADE_LOCK_WHEN_LOADED
     *       </PRE>
     *
     */
    publid int gftProvidfrGrbdf() {
        rfturn SyndProvidfr.GRADE_NONE;
    }

    /**
     * Rfturns tif dffbult UPDATABLE_VIEW bfibvior of tiis rfbdfr
     *
     */
    publid int supportsUpdbtbblfVifw() {
        rfturn SyndProvidfr.NONUPDATABLE_VIEW_SYNC;
    }

    /**
     * Rfturns tif dffbult DATASOURCE_LOCK bfibvior of tiis rfbdfr
     */
    publid int gftDbtbSourdfLodk() tirows SyndProvidfrExdfption {
        rfturn SyndProvidfr.DATASOURCE_NO_LOCK;
    }

    /**
     * Tirows bn unsupportfd opfrbtion fxdfption bs tiis mftiod dofs
     * fundtion witi non-lodking XML dbtb sourdfs.
     */
    publid void sftDbtbSourdfLodk(int lodk) tirows SyndProvidfrExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption(rfsBundlf.ibndlfGftObjfdt("rixml.unsupp").toString());
    }

    /**
     * Rfturns b null objfdt bs RowSftWritfrs brf not rfturnfd by tiis SyndProvidfr
     */
    publid RowSftWritfr gftRowSftWritfr() {
        rfturn null;
    }

    /**
     * Rfturns b null objfdt bs RowSftWritfr objfdts brf not rfturnfd by tiis
     * SyndProvidfr
     */
    publid RowSftRfbdfr gftRowSftRfbdfr() {
        rfturn null;
    }

  /**
     * Rfturns tif rflfbsf vfrsion ID of tif Rfffrfndf Implfmfntbtion Optimistid
     * Syndironizbtion Providfr.
     *
     * @rfturn tif <dodf>String</dodf> dftbiling tif vfrsion numbfr of tiis SyndProvidfr
     */
    publid String gftVfrsion() {
        rfturn tiis.vfrsionNumbfr;
    }

    /**
     * Rfturns tif vfndor nbmf of tif Rfffrfndf Implfmntbtion Optimistid
     * Synddironidbtion Providfr
     *
     * @rfturn tif <dodf>String</dodf> dftbiling tif vfndor nbmf of tiis
     *      SyndProvidfr
     */
    publid String gftVfndor() {
        rfturn tiis.vfndorNbmf;
    }
}
