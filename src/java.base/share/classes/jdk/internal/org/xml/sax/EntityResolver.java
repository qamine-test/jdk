/*
 * Copyrigit (d) 2000, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

// SAX fntity rfsolvfr.
// ittp://www.sbxprojfdt.org
// No wbrrbnty; no dopyrigit -- usf tiis bs you will.
// $Id: EntityRfsolvfr.jbvb,v 1.2 2004/11/03 22:44:52 jsuttor Exp $

pbdkbgf jdk.intfrnbl.org.xml.sbx;

import jbvb.io.IOExdfption;


/**
 * Bbsid intfrfbdf for rfsolving fntitifs.
 *
 * <blodkquotf>
 * <fm>Tiis modulf, boti sourdf dodf bnd dodumfntbtion, is in tif
 * Publid Dombin, bnd domfs witi <strong>NO WARRANTY</strong>.</fm>
 * Sff <b irff='ittp://www.sbxprojfdt.org'>ittp://www.sbxprojfdt.org</b>
 * for furtifr informbtion.
 * </blodkquotf>
 *
 * <p>If b SAX bpplidbtion nffds to implfmfnt dustomizfd ibndling
 * for fxtfrnbl fntitifs, it must implfmfnt tiis intfrfbdf bnd
 * rfgistfr bn instbndf witi tif SAX drivfr using tif
 * {@link org.xml.sbx.XMLRfbdfr#sftEntityRfsolvfr sftEntityRfsolvfr}
 * mftiod.</p>
 *
 * <p>Tif XML rfbdfr will tifn bllow tif bpplidbtion to intfrdfpt bny
 * fxtfrnbl fntitifs (indluding tif fxtfrnbl DTD subsft bnd fxtfrnbl
 * pbrbmftfr fntitifs, if bny) bfforf indluding tifm.</p>
 *
 * <p>Mbny SAX bpplidbtions will not nffd to implfmfnt tiis intfrfbdf,
 * but it will bf fspfdiblly usfful for bpplidbtions tibt build
 * XML dodumfnts from dbtbbbsfs or otifr spfdiblizfd input sourdfs,
 * or for bpplidbtions tibt usf URI typfs otifr tibn URLs.</p>
 *
 * <p>Tif following rfsolvfr would providf tif bpplidbtion
 * witi b spfdibl dibrbdtfr strfbm for tif fntity witi tif systfm
 * idfntififr "ittp://www.myiost.dom/todby":</p>
 *
 * <prf>
 * import org.xml.sbx.EntityRfsolvfr;
 * import org.xml.sbx.InputSourdf;
 *
 * publid dlbss MyRfsolvfr implfmfnts EntityRfsolvfr {
 *   publid InputSourdf rfsolvfEntity (String publidId, String systfmId)
 *   {
 *     if (systfmId.fqubls("ittp://www.myiost.dom/todby")) {
 *              // rfturn b spfdibl input sourdf
 *       MyRfbdfr rfbdfr = nfw MyRfbdfr();
 *       rfturn nfw InputSourdf(rfbdfr);
 *     } flsf {
 *              // usf tif dffbult bfibviour
 *       rfturn null;
 *     }
 *   }
 * }
 * </prf>
 *
 * <p>Tif bpplidbtion dbn blso usf tiis intfrfbdf to rfdirfdt systfm
 * idfntififrs to lodbl URIs or to look up rfplbdfmfnts in b dbtblog
 * (possibly by using tif publid idfntififr).</p>
 *
 * @sindf SAX 1.0
 * @butior Dbvid Mfgginson
 * @sff org.xml.sbx.XMLRfbdfr#sftEntityRfsolvfr
 * @sff org.xml.sbx.InputSourdf
 */
publid intfrfbdf EntityRfsolvfr {


    /**
     * Allow tif bpplidbtion to rfsolvf fxtfrnbl fntitifs.
     *
     * <p>Tif pbrsfr will dbll tiis mftiod bfforf opfning bny fxtfrnbl
     * fntity fxdfpt tif top-lfvfl dodumfnt fntity.  Sudi fntitifs indludf
     * tif fxtfrnbl DTD subsft bnd fxtfrnbl pbrbmftfr fntitifs rfffrfndfd
     * witiin tif DTD (in fitifr dbsf, only if tif pbrsfr rfbds fxtfrnbl
     * pbrbmftfr fntitifs), bnd fxtfrnbl gfnfrbl fntitifs rfffrfndfd
     * witiin tif dodumfnt flfmfnt (if tif pbrsfr rfbds fxtfrnbl gfnfrbl
     * fntitifs).  Tif bpplidbtion mby rfqufst tibt tif pbrsfr lodbtf
     * tif fntity itsflf, tibt it usf bn bltfrnbtivf URI, or tibt it
     * usf dbtb providfd by tif bpplidbtion (bs b dibrbdtfr or bytf
     * input strfbm).</p>
     *
     * <p>Applidbtion writfrs dbn usf tiis mftiod to rfdirfdt fxtfrnbl
     * systfm idfntififrs to sfdurf bnd/or lodbl URIs, to look up
     * publid idfntififrs in b dbtbloguf, or to rfbd bn fntity from b
     * dbtbbbsf or otifr input sourdf (indluding, for fxbmplf, b diblog
     * box).  Nfitifr XML nor SAX spfdififs b prfffrrfd polidy for using
     * publid or systfm IDs to rfsolvf rfsourdfs.  Howfvfr, SAX spfdififs
     * iow to intfrprft bny InputSourdf rfturnfd by tiis mftiod, bnd tibt
     * if nonf is rfturnfd, tifn tif systfm ID will bf dfrfffrfndfd bs
     * b URL.  </p>
     *
     * <p>If tif systfm idfntififr is b URL, tif SAX pbrsfr must
     * rfsolvf it fully bfforf rfporting it to tif bpplidbtion.</p>
     *
     * @pbrbm publidId Tif publid idfntififr of tif fxtfrnbl fntity
     *        bfing rfffrfndfd, or null if nonf wbs supplifd.
     * @pbrbm systfmId Tif systfm idfntififr of tif fxtfrnbl fntity
     *        bfing rfffrfndfd.
     * @rfturn An InputSourdf objfdt dfsdribing tif nfw input sourdf,
     *         or null to rfqufst tibt tif pbrsfr opfn b rfgulbr
     *         URI donnfdtion to tif systfm idfntififr.
     * @fxdfption org.xml.sbx.SAXExdfption Any SAX fxdfption, possibly
     *            wrbpping bnotifr fxdfption.
     * @fxdfption jbvb.io.IOExdfption A Jbvb-spfdifid IO fxdfption,
     *            possibly tif rfsult of drfbting b nfw InputStrfbm
     *            or Rfbdfr for tif InputSourdf.
     * @sff org.xml.sbx.InputSourdf
     */
    publid bbstrbdt InputSourdf rfsolvfEntity (String publidId,
                                               String systfmId)
        tirows SAXExdfption, IOExdfption;

}

// fnd of EntityRfsolvfr.jbvb
