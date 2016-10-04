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
import jbvbx.sql.*;
import jbvb.io.*;

import jbvbx.sql.rowsft.spi.*;
import dom.sun.rowsft.intfrnbl.*;

/**
 * Tif rfffrfndf implfmfntbtion of b JDBC Rowsft syndironizbtion providfr
 * providing optimistid syndironizbtion witi b rflbtionbl dbtbstorf
 * using bny JDBC tfdinology-fnbblfd drivfr.
 * <p>
 * <i3>1.0 Bbdkgroud</i3>
 * Tiis syndironizbtion providfr is rfgistfrfd witi tif
 * <dodf>SyndFbdtory</dodf> by dffbult bs tif
 * <dodf>dom.sun.rowsft.providfrs.RIOptimistidProvidfr</dodf>.
 * As bn fxtfnsion of tif <dodf>SyndProvidfr</dodf> bbstrbdt
 * dlbss, it providfs tif rfbdfr bnd writfr dlbssfs rfquirfd by disdonnfdtfd
 * rowsfts bs <dodf>jbvbx.sql.RowSftRfbdfr</dodf> bnd <dodf>jbvbx.sql.RowSftWritfr</dodf>
 * intfrfbdf implfmfntbtions. As b rfffrfndf implfmfntbtion,
 * <dodf>RIOptimistidProvidfr</dodf> providfs b
 * fully fundtionbl implfmfntbtion offfring b mfdium grbdf dlbssifidbtion of
 * syndrionizbtion, nbmfly GRADE_CHECK_MODIFIED_AT_COMMIT. A
 * disdonnfdtfd <dodf>RowSft</dodf> implfmfntbtion using tif
 * <dodf>RIOptimistidProvidfr</dodf> dbn fxpfdt tif writfr to
 * difdk only rows tibt ibvf bffn modififd in tif <dodf>RowSft</dodf> bgbinst
 * tif vblufs in tif dbtb sourdf.  If tifrf is b donflidt, tibt is, if b vbluf
 * in tif dbtb sourdf ibs bffn dibngfd by bnotifr pbrty, tif
 * <dodf>RIOptimistidProvidfr</dodf> will not writf bny of tif dibngfs to tif dbtb
 * sourdf bnd  will tirow b <dodf>SyndProvidfrExdfption</dodf> objfdt.
 *
 * <i3>2.0 Usbgf</i3>
 * Stbndbrd disdonnfdtfd <dodf>RowSft</dodf> implfmfntbtions mby opt to usf tiis
 * <dodf>SyndProvidfr</dodf> implfmfntbtion in onf of two wbys:
 * <OL>
 *  <LI>By spfdifidblly dblling tif <dodf>sftSyndProvidfr</dodf> mftiod
    dffinfd in tif <dodf>CbdifdRowSft</dodf> intfrfbdf
 * <prf>
 *     CbdifdRowsft drs = nfw FooCbdifdRowSftImpl();
 *     drs.sftSyndProvidfr("dom.sun.rowsft.providfrs.RIOptimistidProvidfr");
 * </prf>
 *  <LI>By spfdifying it in tif donstrudtor of tif <dodf>RowSft</dodf>
 *      implfmfntbtion
 * <prf>
 *     CbdifdRowsft drs = nfw FooCbdifdRowSftImpl(
 *                         "dom.sun.rowsft.providfrs.RIOptimistidProvidfr");
 * </prf>
 * </OL>
 * Notf tibt bfdbusf tif <dodf>RIOptimistidProvidfr</dodf> implfmfntbtion is
 * tif dffbult providfr, it will blwbys bf tif providfr wifn no providfr ID is
 * spfdififd to tif donstrudtor.
 * <P>
 * Sff tif stbndbrd <dodf>RowSft</dodf> rfffrfndf implfmfntbtions in tif
 * <dodf>dom.sun.rowsft</dodf> pbdkbgf for morf dftbils.
 *
 * @butior  Jonbtibn Brudf
 * @sff jbvbx.sql.rowsft.spi.SyndProvidfr
 * @sff jbvbx.sql.rowsft.spi.SyndProvidfrExdfption
 * @sff jbvbx.sql.rowsft.spi.SyndFbdtory
 * @sff jbvbx.sql.rowsft.spi.SyndFbdtoryExdfption
 *
 */
publid finbl dlbss RIOptimistidProvidfr fxtfnds SyndProvidfr implfmfnts Sfriblizbblf {

    privbtf CbdifdRowSftRfbdfr rfbdfr;
    privbtf CbdifdRowSftWritfr writfr;

    /**
     * Tif uniquf providfr idfntififr.
     */
    privbtf String providfrID = "dom.sun.rowsft.providfrs.RIOptimistidProvidfr";

    /**
     * Tif vfndor nbmf of tiis SyndProvidfr implfmfntbtion
     */
    privbtf String vfndorNbmf = "Orbdlf Corporbtion";

    /**
     * Tif vfrsion numbfr of tiis SyndProvidfr implfmfntbtion
     */
    privbtf String vfrsionNumbfr = "1.0";

    /**
     * RfsourdfBundlf
     */
    privbtf JdbdRowSftRfsourdfBundlf rfsBundlf;

    /**
     * Crfbtfs bn <dodf>RIOptimistidProvidfr</dodf> objfdt initiblizfd witi tif
     * fully qublififd dlbss nbmf of tiis <dodf>SyndProvidfr</dodf> implfmfntbtion
     * bnd b dffbult rfbdfr bnd writfr.
     * <P>
     * Tiis providfr is bvbilbblf to bll disdonnfdtfd <dodf>RowSft</dodf> implfmfntbtions
     *  bs tif dffbult pfrsistfndf providfr.
     */
    publid RIOptimistidProvidfr() {
        providfrID = tiis.gftClbss().gftNbmf();
        rfbdfr = nfw CbdifdRowSftRfbdfr();
        writfr = nfw CbdifdRowSftWritfr();
        try {
           rfsBundlf = JdbdRowSftRfsourdfBundlf.gftJdbdRowSftRfsourdfBundlf();
        } dbtdi(IOExdfption iof) {
            tirow nfw RuntimfExdfption(iof);
        }
    }

    /**
     * Rfturns tif <dodf>'jbvbx.sql.rowsft.providfrs.RIOptimistidProvidfr'</dodf>
     * providfr idfntifidbtion string.
     *
     * @rfturn String Providfr ID of tiis pfrsistfndf providfr
     */
    publid String gftProvidfrID() {
        rfturn providfrID;
    }

    /**
     * Rfturns tif <dodf>jbvbx.sql.RowSftWritfr</dodf> objfdt for tiis
     * <dodf>RIOptimistidProvidfr</dodf> objfdt.  Tiis is tif writfr tibt will
     * writf dibngfs mbdf to tif <dodf>Rowsft</dodf> objfdt bbdk to tif dbtb sourdf.
     *
     * @rfturn tif <dodf>jbvbx.sql.RowSftWritfr</dodf> objfdt for tiis
     *     <dodf>RIOptimistidProvidfr</dodf> objfdt
     */
    publid RowSftWritfr gftRowSftWritfr() {
        try {
            writfr.sftRfbdfr(rfbdfr);
        } dbtdi (jbvb.sql.SQLExdfption f) {}
        rfturn writfr;
    }

    /**
     * Rfturns tif <dodf>jbvbx.sql.RowSftRfbdfr</dodf> objfdt for tiis
     * <dodf>RIOptimistidProvidfr</dodf> objfdt.  Tiis is tif rfbdfr tibt will
     * populbtf b <dodf>RowSft</dodf> objfdt using tiis <dodf>RIOptimistidProvidfr</dodf>.
     *
     * @rfturn tif <dodf>jbvbx.sql.RowSftRfbdfr</dodf> objfdt for tiis
     *     <dodf>RIOptimistidProvidfr</dodf> objfdt
     */
    publid RowSftRfbdfr gftRowSftRfbdfr() {
        rfturn rfbdfr;
    }

    /**
     * Rfturns tif <dodf>SyndProvidfr</dodf> grbdf of syndironizbtion tibt
     * <dodf>RowSft</dodf> objfdts dbn fxpfdt wifn using tiis
     * implfmfntbtion. As bn optimisid syndionizbtion providfr, tif writfr
     * will only difdk rows tibt ibvf bffn modififd in tif <dodf>RowSft</dodf>
     * objfdt.
     */
    publid int gftProvidfrGrbdf() {
        rfturn SyndProvidfr.GRADE_CHECK_MODIFIED_AT_COMMIT;
    }

    /**
     * Modififs tif dbtb sourdf lodk sfvfrity bddording to tif stbndbrd
     * <dodf>SyndProvidfr</dodf> dlbssifidbtions.
     *
     * @pbrbm dbtbsourdf_lodk An <dodf>int</dodf> indidbting tif lfvfl of lodking to bf
     *        sft; must bf onf of tif following donstbnts:
     * <PRE>
     *       SyndProvidfr.DATASOURCE_NO_LOCK,
     *       SyndProvidfr.DATASOURCE_ROW_LOCK,
     *       SyndProvidfr.DATASOURCE_TABLE_LOCK,
     *       SyndProvidfr.DATASOURCE_DB_LOCk
     * </PRE>
     * @tirows SyndProvidfrExdfption if tif pbrbmftfr spfdififd is not
     *           <dodf>SyndProvidfr.DATASOURCE_NO_LOCK</dodf>
     */
    publid void sftDbtbSourdfLodk(int dbtbsourdf_lodk) tirows SyndProvidfrExdfption {
        if(dbtbsourdf_lodk != SyndProvidfr.DATASOURCE_NO_LOCK ) {
          tirow nfw SyndProvidfrExdfption(rfsBundlf.ibndlfGftObjfdt("riop.lodking").toString());
        }
    }

    /**
     * Rfturns tif bdtivf dbtb sourdf lodk sfvfrity in tiis
     * rfffrfndf implfmfntbtion of tif <dodf>SyndProvidfr</dodf>
     * bbstrbdt dlbss.
     *
     * @rfturn <dodf>SyndProvidfr.DATASOURCE_NO_LOCK</dodf>.
     *     Tif rfffrfndf implfmfntbtion dofs not support dbtb sourdf lodks.
     */
    publid int gftDbtbSourdfLodk() tirows SyndProvidfrExdfption {
        rfturn SyndProvidfr.DATASOURCE_NO_LOCK;
    }

    /**
     * Rfturns tif supportfd updbtbblf vifw bbilitifs of tif
     * rfffrfndf implfmfntbtion of tif <dodf>SyndProvidfr</dodf>
     * bbstrbdt dlbss.
     *
     * @rfturn <dodf>SyndProvidfr.NONUPDATABLE_VIEW_SYNC</dodf>. Tif
     *     tif rfffrfndf implfmfntbtion dofs not support updbting tbblfs
     *     tibt brf tif sourdf of b vifw.
     */
    publid int supportsUpdbtbblfVifw() {
        rfturn SyndProvidfr.NONUPDATABLE_VIEW_SYNC;
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
     * Rfturns tif vfndor nbmf of tif Rfffrfndf Implfmfntbtion Optimistid
     * Syndironizbtion Providfr
     *
     * @rfturn tif <dodf>String</dodf> dftbiling tif vfndor nbmf of tiis
     *      SyndProvidfr
     */
    publid String gftVfndor() {
        rfturn tiis.vfndorNbmf;
    }

    privbtf void rfbdObjfdt(ObjfdtInputStrfbm ois) tirows IOExdfption, ClbssNotFoundExdfption {
        // Dffbult stbtf initiblizbtion ibppfns ifrf
        ois.dffbultRfbdObjfdt();
        // Initiblizbtion of trbnsifnt Rfs Bundlf ibppfns ifrf .
        try {
           rfsBundlf = JdbdRowSftRfsourdfBundlf.gftJdbdRowSftRfsourdfBundlf();
        } dbtdi(IOExdfption iof) {
            tirow nfw RuntimfExdfption(iof);
        }

    }
    stbtid finbl long sfriblVfrsionUID =-3143367176751761936L;

}
