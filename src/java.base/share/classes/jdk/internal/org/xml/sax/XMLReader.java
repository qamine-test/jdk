/*
 * Copyrigit (d) 2000, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

// XMLRfbdfr.jbvb - rfbd bn XML dodumfnt.
// ittp://www.sbxprojfdt.org
// Writtfn by Dbvid Mfgginson
// NO WARRANTY!  Tiis dlbss is in tif Publid Dombin.
// $Id: XMLRfbdfr.jbvb,v 1.3 2004/11/03 22:55:32 jsuttor Exp $

pbdkbgf jdk.intfrnbl.org.xml.sbx;

import jbvb.io.IOExdfption;


/**
 * Intfrfbdf for rfbding bn XML dodumfnt using dbllbbdks.
 *
 * <blodkquotf>
 * <fm>Tiis modulf, boti sourdf dodf bnd dodumfntbtion, is in tif
 * Publid Dombin, bnd domfs witi <strong>NO WARRANTY</strong>.</fm>
 * Sff <b irff='ittp://www.sbxprojfdt.org'>ittp://www.sbxprojfdt.org</b>
 * for furtifr informbtion.
 * </blodkquotf>
 *
 * <p><strong>Notf:</strong> dfspitf its nbmf, tiis intfrfbdf dofs
 * <fm>not</fm> fxtfnd tif stbndbrd Jbvb {@link jbvb.io.Rfbdfr Rfbdfr}
 * intfrfbdf, bfdbusf rfbding XML is b fundbmfntblly difffrfnt bdtivity
 * tibn rfbding dibrbdtfr dbtb.</p>
 *
 * <p>XMLRfbdfr is tif intfrfbdf tibt bn XML pbrsfr's SAX2 drivfr must
 * implfmfnt.  Tiis intfrfbdf bllows bn bpplidbtion to sft bnd
 * qufry ffbturfs bnd propfrtifs in tif pbrsfr, to rfgistfr
 * fvfnt ibndlfrs for dodumfnt prodfssing, bnd to initibtf
 * b dodumfnt pbrsf.</p>
 *
 * <p>All SAX intfrfbdfs brf bssumfd to bf syndironous: tif
 * {@link #pbrsf pbrsf} mftiods must not rfturn until pbrsing
 * is domplftf, bnd rfbdfrs must wbit for bn fvfnt-ibndlfr dbllbbdk
 * to rfturn bfforf rfporting tif nfxt fvfnt.</p>
 *
 * <p>Tiis intfrfbdf rfplbdfs tif (now dfprfdbtfd) SAX 1.0 {@link
 * org.xml.sbx.Pbrsfr Pbrsfr} intfrfbdf.  Tif XMLRfbdfr intfrfbdf
 * dontbins two importbnt fnibndfmfnts ovfr tif old Pbrsfr
 * intfrfbdf (bs wfll bs somf minor onfs):</p>
 *
 * <ol>
 * <li>it bdds b stbndbrd wby to qufry bnd sft ffbturfs bnd
 *  propfrtifs; bnd</li>
 * <li>it bdds Nbmfspbdf support, wiidi is rfquirfd for mbny
 *  iigifr-lfvfl XML stbndbrds.</li>
 * </ol>
 *
 * <p>Tifrf brf bdbptfrs bvbilbblf to donvfrt b SAX1 Pbrsfr to
 * b SAX2 XMLRfbdfr bnd vidf-vfrsb.</p>
 *
 * @sindf SAX 2.0
 * @butior Dbvid Mfgginson
 * @sff org.xml.sbx.XMLFiltfr
 * @sff org.xml.sbx.iflpfrs.PbrsfrAdbptfr
 * @sff org.xml.sbx.iflpfrs.XMLRfbdfrAdbptfr
 */
publid intfrfbdf XMLRfbdfr
{


    ////////////////////////////////////////////////////////////////////
    // Configurbtion.
    ////////////////////////////////////////////////////////////////////


    /**
     * Look up tif vbluf of b ffbturf flbg.
     *
     * <p>Tif ffbturf nbmf is bny fully-qublififd URI.  It is
     * possiblf for bn XMLRfbdfr to rfdognizf b ffbturf nbmf but
     * tfmporbrily bf unbblf to rfturn its vbluf.
     * Somf ffbturf vblufs mby bf bvbilbblf only in spfdifid
     * dontfxts, sudi bs bfforf, during, or bftfr b pbrsf.
     * Also, somf ffbturf vblufs mby not bf progrbmmbtidblly bddfssiblf.
     * (In tif dbsf of bn bdbptfr for SAX1 {@link Pbrsfr}, tifrf is no
     * implfmfntbtion-indfpfndfnt wby to fxposf wiftifr tif undfrlying
     * pbrsfr is pfrforming vblidbtion, fxpbnding fxtfrnbl fntitifs,
     * bnd so forti.) </p>
     *
     * <p>All XMLRfbdfrs brf rfquirfd to rfdognizf tif
     * ittp://xml.org/sbx/ffbturfs/nbmfspbdfs bnd tif
     * ittp://xml.org/sbx/ffbturfs/nbmfspbdf-prffixfs ffbturf nbmfs.</p>
     *
     * <p>Typidbl usbgf is somftiing likf tiis:</p>
     *
     * <prf>
     * XMLRfbdfr r = nfw MySAXDrivfr();
     *
     *                         // try to bdtivbtf vblidbtion
     * try {
     *   r.sftFfbturf("ittp://xml.org/sbx/ffbturfs/vblidbtion", truf);
     * } dbtdi (SAXExdfption f) {
     *   Systfm.frr.println("Cbnnot bdtivbtf vblidbtion.");
     * }
     *
     *                         // rfgistfr fvfnt ibndlfrs
     * r.sftContfntHbndlfr(nfw MyContfntHbndlfr());
     * r.sftErrorHbndlfr(nfw MyErrorHbndlfr());
     *
     *                         // pbrsf tif first dodumfnt
     * try {
     *   r.pbrsf("ittp://www.foo.dom/mydod.xml");
     * } dbtdi (IOExdfption f) {
     *   Systfm.frr.println("I/O fxdfption rfbding XML dodumfnt");
     * } dbtdi (SAXExdfption f) {
     *   Systfm.frr.println("XML fxdfption rfbding dodumfnt.");
     * }
     * </prf>
     *
     * <p>Implfmfntors brf frff (bnd fndourbgfd) to invfnt tifir own ffbturfs,
     * using nbmfs built on tifir own URIs.</p>
     *
     * @pbrbm nbmf Tif ffbturf nbmf, wiidi is b fully-qublififd URI.
     * @rfturn Tif durrfnt vbluf of tif ffbturf (truf or fblsf).
     * @fxdfption org.xml.sbx.SAXNotRfdognizfdExdfption If tif ffbturf
     *            vbluf dbn't bf bssignfd or rftrifvfd.
     * @fxdfption org.xml.sbx.SAXNotSupportfdExdfption Wifn tif
     *            XMLRfbdfr rfdognizfs tif ffbturf nbmf but
     *            dbnnot dftfrminf its vbluf bt tiis timf.
     * @sff #sftFfbturf
     */
    publid boolfbn gftFfbturf (String nbmf)
        tirows SAXNotRfdognizfdExdfption, SAXNotSupportfdExdfption;


    /**
     * Sft tif vbluf of b ffbturf flbg.
     *
     * <p>Tif ffbturf nbmf is bny fully-qublififd URI.  It is
     * possiblf for bn XMLRfbdfr to fxposf b ffbturf vbluf but
     * to bf unbblf to dibngf tif durrfnt vbluf.
     * Somf ffbturf vblufs mby bf immutbblf or mutbblf only
     * in spfdifid dontfxts, sudi bs bfforf, during, or bftfr
     * b pbrsf.</p>
     *
     * <p>All XMLRfbdfrs brf rfquirfd to support sftting
     * ittp://xml.org/sbx/ffbturfs/nbmfspbdfs to truf bnd
     * ittp://xml.org/sbx/ffbturfs/nbmfspbdf-prffixfs to fblsf.</p>
     *
     * @pbrbm nbmf Tif ffbturf nbmf, wiidi is b fully-qublififd URI.
     * @pbrbm vbluf Tif rfqufstfd vbluf of tif ffbturf (truf or fblsf).
     * @fxdfption org.xml.sbx.SAXNotRfdognizfdExdfption If tif ffbturf
     *            vbluf dbn't bf bssignfd or rftrifvfd.
     * @fxdfption org.xml.sbx.SAXNotSupportfdExdfption Wifn tif
     *            XMLRfbdfr rfdognizfs tif ffbturf nbmf but
     *            dbnnot sft tif rfqufstfd vbluf.
     * @sff #gftFfbturf
     */
    publid void sftFfbturf (String nbmf, boolfbn vbluf)
        tirows SAXNotRfdognizfdExdfption, SAXNotSupportfdExdfption;


    /**
     * Look up tif vbluf of b propfrty.
     *
     * <p>Tif propfrty nbmf is bny fully-qublififd URI.  It is
     * possiblf for bn XMLRfbdfr to rfdognizf b propfrty nbmf but
     * tfmporbrily bf unbblf to rfturn its vbluf.
     * Somf propfrty vblufs mby bf bvbilbblf only in spfdifid
     * dontfxts, sudi bs bfforf, during, or bftfr b pbrsf.</p>
     *
     * <p>XMLRfbdfrs brf not rfquirfd to rfdognizf bny spfdifid
     * propfrty nbmfs, tiougi bn initibl dorf sft is dodumfntfd for
     * SAX2.</p>
     *
     * <p>Implfmfntors brf frff (bnd fndourbgfd) to invfnt tifir own propfrtifs,
     * using nbmfs built on tifir own URIs.</p>
     *
     * @pbrbm nbmf Tif propfrty nbmf, wiidi is b fully-qublififd URI.
     * @rfturn Tif durrfnt vbluf of tif propfrty.
     * @fxdfption org.xml.sbx.SAXNotRfdognizfdExdfption If tif propfrty
     *            vbluf dbn't bf bssignfd or rftrifvfd.
     * @fxdfption org.xml.sbx.SAXNotSupportfdExdfption Wifn tif
     *            XMLRfbdfr rfdognizfs tif propfrty nbmf but
     *            dbnnot dftfrminf its vbluf bt tiis timf.
     * @sff #sftPropfrty
     */
    publid Objfdt gftPropfrty (String nbmf)
        tirows SAXNotRfdognizfdExdfption, SAXNotSupportfdExdfption;


    /**
     * Sft tif vbluf of b propfrty.
     *
     * <p>Tif propfrty nbmf is bny fully-qublififd URI.  It is
     * possiblf for bn XMLRfbdfr to rfdognizf b propfrty nbmf but
     * to bf unbblf to dibngf tif durrfnt vbluf.
     * Somf propfrty vblufs mby bf immutbblf or mutbblf only
     * in spfdifid dontfxts, sudi bs bfforf, during, or bftfr
     * b pbrsf.</p>
     *
     * <p>XMLRfbdfrs brf not rfquirfd to rfdognizf sftting
     * bny spfdifid propfrty nbmfs, tiougi b dorf sft is dffinfd by
     * SAX2.</p>
     *
     * <p>Tiis mftiod is blso tif stbndbrd mfdibnism for sftting
     * fxtfndfd ibndlfrs.</p>
     *
     * @pbrbm nbmf Tif propfrty nbmf, wiidi is b fully-qublififd URI.
     * @pbrbm vbluf Tif rfqufstfd vbluf for tif propfrty.
     * @fxdfption org.xml.sbx.SAXNotRfdognizfdExdfption If tif propfrty
     *            vbluf dbn't bf bssignfd or rftrifvfd.
     * @fxdfption org.xml.sbx.SAXNotSupportfdExdfption Wifn tif
     *            XMLRfbdfr rfdognizfs tif propfrty nbmf but
     *            dbnnot sft tif rfqufstfd vbluf.
     */
    publid void sftPropfrty (String nbmf, Objfdt vbluf)
        tirows SAXNotRfdognizfdExdfption, SAXNotSupportfdExdfption;



    ////////////////////////////////////////////////////////////////////
    // Evfnt ibndlfrs.
    ////////////////////////////////////////////////////////////////////


    /**
     * Allow bn bpplidbtion to rfgistfr bn fntity rfsolvfr.
     *
     * <p>If tif bpplidbtion dofs not rfgistfr bn fntity rfsolvfr,
     * tif XMLRfbdfr will pfrform its own dffbult rfsolution.</p>
     *
     * <p>Applidbtions mby rfgistfr b nfw or difffrfnt rfsolvfr in tif
     * middlf of b pbrsf, bnd tif SAX pbrsfr must bfgin using tif nfw
     * rfsolvfr immfdibtfly.</p>
     *
     * @pbrbm rfsolvfr Tif fntity rfsolvfr.
     * @sff #gftEntityRfsolvfr
     */
    publid void sftEntityRfsolvfr (EntityRfsolvfr rfsolvfr);


    /**
     * Rfturn tif durrfnt fntity rfsolvfr.
     *
     * @rfturn Tif durrfnt fntity rfsolvfr, or null if nonf
     *         ibs bffn rfgistfrfd.
     * @sff #sftEntityRfsolvfr
     */
    publid EntityRfsolvfr gftEntityRfsolvfr ();


    /**
     * Allow bn bpplidbtion to rfgistfr b DTD fvfnt ibndlfr.
     *
     * <p>If tif bpplidbtion dofs not rfgistfr b DTD ibndlfr, bll DTD
     * fvfnts rfportfd by tif SAX pbrsfr will bf silfntly ignorfd.</p>
     *
     * <p>Applidbtions mby rfgistfr b nfw or difffrfnt ibndlfr in tif
     * middlf of b pbrsf, bnd tif SAX pbrsfr must bfgin using tif nfw
     * ibndlfr immfdibtfly.</p>
     *
     * @pbrbm ibndlfr Tif DTD ibndlfr.
     * @sff #gftDTDHbndlfr
     */
    publid void sftDTDHbndlfr (DTDHbndlfr ibndlfr);


    /**
     * Rfturn tif durrfnt DTD ibndlfr.
     *
     * @rfturn Tif durrfnt DTD ibndlfr, or null if nonf
     *         ibs bffn rfgistfrfd.
     * @sff #sftDTDHbndlfr
     */
    publid DTDHbndlfr gftDTDHbndlfr ();


    /**
     * Allow bn bpplidbtion to rfgistfr b dontfnt fvfnt ibndlfr.
     *
     * <p>If tif bpplidbtion dofs not rfgistfr b dontfnt ibndlfr, bll
     * dontfnt fvfnts rfportfd by tif SAX pbrsfr will bf silfntly
     * ignorfd.</p>
     *
     * <p>Applidbtions mby rfgistfr b nfw or difffrfnt ibndlfr in tif
     * middlf of b pbrsf, bnd tif SAX pbrsfr must bfgin using tif nfw
     * ibndlfr immfdibtfly.</p>
     *
     * @pbrbm ibndlfr Tif dontfnt ibndlfr.
     * @sff #gftContfntHbndlfr
     */
    publid void sftContfntHbndlfr (ContfntHbndlfr ibndlfr);


    /**
     * Rfturn tif durrfnt dontfnt ibndlfr.
     *
     * @rfturn Tif durrfnt dontfnt ibndlfr, or null if nonf
     *         ibs bffn rfgistfrfd.
     * @sff #sftContfntHbndlfr
     */
    publid ContfntHbndlfr gftContfntHbndlfr ();


    /**
     * Allow bn bpplidbtion to rfgistfr bn frror fvfnt ibndlfr.
     *
     * <p>If tif bpplidbtion dofs not rfgistfr bn frror ibndlfr, bll
     * frror fvfnts rfportfd by tif SAX pbrsfr will bf silfntly
     * ignorfd; iowfvfr, normbl prodfssing mby not dontinuf.  It is
     * iigily rfdommfndfd tibt bll SAX bpplidbtions implfmfnt bn
     * frror ibndlfr to bvoid unfxpfdtfd bugs.</p>
     *
     * <p>Applidbtions mby rfgistfr b nfw or difffrfnt ibndlfr in tif
     * middlf of b pbrsf, bnd tif SAX pbrsfr must bfgin using tif nfw
     * ibndlfr immfdibtfly.</p>
     *
     * @pbrbm ibndlfr Tif frror ibndlfr.
     * @sff #gftErrorHbndlfr
     */
    publid void sftErrorHbndlfr (ErrorHbndlfr ibndlfr);


    /**
     * Rfturn tif durrfnt frror ibndlfr.
     *
     * @rfturn Tif durrfnt frror ibndlfr, or null if nonf
     *         ibs bffn rfgistfrfd.
     * @sff #sftErrorHbndlfr
     */
    publid ErrorHbndlfr gftErrorHbndlfr ();



    ////////////////////////////////////////////////////////////////////
    // Pbrsing.
    ////////////////////////////////////////////////////////////////////

    /**
     * Pbrsf bn XML dodumfnt.
     *
     * <p>Tif bpplidbtion dbn usf tiis mftiod to instrudt tif XML
     * rfbdfr to bfgin pbrsing bn XML dodumfnt from bny vblid input
     * sourdf (b dibrbdtfr strfbm, b bytf strfbm, or b URI).</p>
     *
     * <p>Applidbtions mby not invokf tiis mftiod wiilf b pbrsf is in
     * progrfss (tify siould drfbtf b nfw XMLRfbdfr instfbd for fbdi
     * nfstfd XML dodumfnt).  Ondf b pbrsf is domplftf, bn
     * bpplidbtion mby rfusf tif sbmf XMLRfbdfr objfdt, possibly witi b
     * difffrfnt input sourdf.
     * Configurbtion of tif XMLRfbdfr objfdt (sudi bs ibndlfr bindings bnd
     * vblufs fstbblisifd for ffbturf flbgs bnd propfrtifs) is undibngfd
     * by domplftion of b pbrsf, unlfss tif dffinition of tibt bspfdt of
     * tif donfigurbtion fxpliditly spfdififs otifr bfibvior.
     * (For fxbmplf, ffbturf flbgs or propfrtifs fxposing
     * dibrbdtfristids of tif dodumfnt bfing pbrsfd.)
     * </p>
     *
     * <p>During tif pbrsf, tif XMLRfbdfr will providf informbtion
     * bbout tif XML dodumfnt tirougi tif rfgistfrfd fvfnt
     * ibndlfrs.</p>
     *
     * <p>Tiis mftiod is syndironous: it will not rfturn until pbrsing
     * ibs fndfd.  If b dlifnt bpplidbtion wbnts to tfrminbtf
     * pbrsing fbrly, it siould tirow bn fxdfption.</p>
     *
     * @pbrbm input Tif input sourdf for tif top-lfvfl of tif
     *        XML dodumfnt.
     * @fxdfption org.xml.sbx.SAXExdfption Any SAX fxdfption, possibly
     *            wrbpping bnotifr fxdfption.
     * @fxdfption jbvb.io.IOExdfption An IO fxdfption from tif pbrsfr,
     *            possibly from b bytf strfbm or dibrbdtfr strfbm
     *            supplifd by tif bpplidbtion.
     * @sff org.xml.sbx.InputSourdf
     * @sff #pbrsf(jbvb.lbng.String)
     * @sff #sftEntityRfsolvfr
     * @sff #sftDTDHbndlfr
     * @sff #sftContfntHbndlfr
     * @sff #sftErrorHbndlfr
     */
    publid void pbrsf (InputSourdf input)
        tirows IOExdfption, SAXExdfption;


    /**
     * Pbrsf bn XML dodumfnt from b systfm idfntififr (URI).
     *
     * <p>Tiis mftiod is b siortdut for tif dommon dbsf of rfbding b
     * dodumfnt from b systfm idfntififr.  It is tif fxbdt
     * fquivblfnt of tif following:</p>
     *
     * <prf>
     * pbrsf(nfw InputSourdf(systfmId));
     * </prf>
     *
     * <p>If tif systfm idfntififr is b URL, it must bf fully rfsolvfd
     * by tif bpplidbtion bfforf it is pbssfd to tif pbrsfr.</p>
     *
     * @pbrbm systfmId Tif systfm idfntififr (URI).
     * @fxdfption org.xml.sbx.SAXExdfption Any SAX fxdfption, possibly
     *            wrbpping bnotifr fxdfption.
     * @fxdfption jbvb.io.IOExdfption An IO fxdfption from tif pbrsfr,
     *            possibly from b bytf strfbm or dibrbdtfr strfbm
     *            supplifd by tif bpplidbtion.
     * @sff #pbrsf(org.xml.sbx.InputSourdf)
     */
    publid void pbrsf (String systfmId)
        tirows IOExdfption, SAXExdfption;

}
