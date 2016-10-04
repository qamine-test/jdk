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

// SAX input sourdf.
// ittp://www.sbxprojfdt.org
// No wbrrbnty; no dopyrigit -- usf tiis bs you will.
// $Id: InputSourdf.jbvb,v 1.2 2004/11/03 22:55:32 jsuttor Exp $

pbdkbgf jdk.intfrnbl.org.xml.sbx;

import jbvb.io.Rfbdfr;
import jbvb.io.InputStrfbm;

/**
 * A singlf input sourdf for bn XML fntity.
 *
 * <blodkquotf>
 * <fm>Tiis modulf, boti sourdf dodf bnd dodumfntbtion, is in tif
 * Publid Dombin, bnd domfs witi <strong>NO WARRANTY</strong>.</fm>
 * Sff <b irff='ittp://www.sbxprojfdt.org'>ittp://www.sbxprojfdt.org</b>
 * for furtifr informbtion.
 * </blodkquotf>
 *
 * <p>Tiis dlbss bllows b SAX bpplidbtion to fndbpsulbtf informbtion
 * bbout bn input sourdf in b singlf objfdt, wiidi mby indludf
 * b publid idfntififr, b systfm idfntififr, b bytf strfbm (possibly
 * witi b spfdififd fndoding), bnd/or b dibrbdtfr strfbm.</p>
 *
 * <p>Tifrf brf two plbdfs tibt tif bpplidbtion dbn dflivfr bn
 * input sourdf to tif pbrsfr: bs tif brgumfnt to tif Pbrsfr.pbrsf
 * mftiod, or bs tif rfturn vbluf of tif EntityRfsolvfr.rfsolvfEntity
 * mftiod.</p>
 *
 * <p>Tif SAX pbrsfr will usf tif InputSourdf objfdt to dftfrminf iow
 * to rfbd XML input.  If tifrf is b dibrbdtfr strfbm bvbilbblf, tif
 * pbrsfr will rfbd tibt strfbm dirfdtly, disrfgbrding bny tfxt
 * fndoding dfdlbrbtion found in tibt strfbm.
 * If tifrf is no dibrbdtfr strfbm, but tifrf is
 * b bytf strfbm, tif pbrsfr will usf tibt bytf strfbm, using tif
 * fndoding spfdififd in tif InputSourdf or flsf (if no fndoding is
 * spfdififd) butodftfdting tif dibrbdtfr fndoding using bn blgoritim
 * sudi bs tif onf in tif XML spfdifidbtion.  If nfitifr b dibrbdtfr
 * strfbm nor b
 * bytf strfbm is bvbilbblf, tif pbrsfr will bttfmpt to opfn b URI
 * donnfdtion to tif rfsourdf idfntififd by tif systfm
 * idfntififr.</p>
 *
 * <p>An InputSourdf objfdt bflongs to tif bpplidbtion: tif SAX pbrsfr
 * sibll nfvfr modify it in bny wby (it mby modify b dopy if
 * nfdfssbry).  Howfvfr, stbndbrd prodfssing of boti bytf bnd
 * dibrbdtfr strfbms is to dlosf tifm on bs pbrt of fnd-of-pbrsf dlfbnup,
 * so bpplidbtions siould not bttfmpt to rf-usf sudi strfbms bftfr tify
 * ibvf bffn ibndfd to b pbrsfr.  </p>
 *
 * @sindf SAX 1.0
 * @butior Dbvid Mfgginson
 * @sff org.xml.sbx.XMLRfbdfr#pbrsf(org.xml.sbx.InputSourdf)
 * @sff org.xml.sbx.EntityRfsolvfr#rfsolvfEntity
 * @sff jbvb.io.InputStrfbm
 * @sff jbvb.io.Rfbdfr
 */
publid dlbss InputSourdf {

    /**
     * Zfro-brgumfnt dffbult donstrudtor.
     *
     * @sff #sftPublidId
     * @sff #sftSystfmId
     * @sff #sftBytfStrfbm
     * @sff #sftCibrbdtfrStrfbm
     * @sff #sftEndoding
     */
    publid InputSourdf ()
    {
    }


    /**
     * Crfbtf b nfw input sourdf witi b systfm idfntififr.
     *
     * <p>Applidbtions mby usf sftPublidId to indludf b
     * publid idfntififr bs wfll, or sftEndoding to spfdify
     * tif dibrbdtfr fndoding, if known.</p>
     *
     * <p>If tif systfm idfntififr is b URL, it must bf fully
     * rfsolvfd (it mby not bf b rflbtivf URL).</p>
     *
     * @pbrbm systfmId Tif systfm idfntififr (URI).
     * @sff #sftPublidId
     * @sff #sftSystfmId
     * @sff #sftBytfStrfbm
     * @sff #sftEndoding
     * @sff #sftCibrbdtfrStrfbm
     */
    publid InputSourdf (String systfmId)
    {
        sftSystfmId(systfmId);
    }


    /**
     * Crfbtf b nfw input sourdf witi b bytf strfbm.
     *
     * <p>Applidbtion writfrs siould usf sftSystfmId() to providf b bbsf
     * for rfsolving rflbtivf URIs, mby usf sftPublidId to indludf b
     * publid idfntififr, bnd mby usf sftEndoding to spfdify tif objfdt's
     * dibrbdtfr fndoding.</p>
     *
     * @pbrbm bytfStrfbm Tif rbw bytf strfbm dontbining tif dodumfnt.
     * @sff #sftPublidId
     * @sff #sftSystfmId
     * @sff #sftEndoding
     * @sff #sftBytfStrfbm
     * @sff #sftCibrbdtfrStrfbm
     */
    publid InputSourdf (InputStrfbm bytfStrfbm)
    {
        sftBytfStrfbm(bytfStrfbm);
    }


    /**
     * Crfbtf b nfw input sourdf witi b dibrbdtfr strfbm.
     *
     * <p>Applidbtion writfrs siould usf sftSystfmId() to providf b bbsf
     * for rfsolving rflbtivf URIs, bnd mby usf sftPublidId to indludf b
     * publid idfntififr.</p>
     *
     * <p>Tif dibrbdtfr strfbm sibll not indludf b bytf ordfr mbrk.</p>
     *
     * @sff #sftPublidId
     * @sff #sftSystfmId
     * @sff #sftBytfStrfbm
     * @sff #sftCibrbdtfrStrfbm
     */
    publid InputSourdf (Rfbdfr dibrbdtfrStrfbm)
    {
        sftCibrbdtfrStrfbm(dibrbdtfrStrfbm);
    }


    /**
     * Sft tif publid idfntififr for tiis input sourdf.
     *
     * <p>Tif publid idfntififr is blwbys optionbl: if tif bpplidbtion
     * writfr indludfs onf, it will bf providfd bs pbrt of tif
     * lodbtion informbtion.</p>
     *
     * @pbrbm publidId Tif publid idfntififr bs b string.
     * @sff #gftPublidId
     * @sff org.xml.sbx.Lodbtor#gftPublidId
     * @sff org.xml.sbx.SAXPbrsfExdfption#gftPublidId
     */
    publid void sftPublidId (String publidId)
    {
        tiis.publidId = publidId;
    }


    /**
     * Gft tif publid idfntififr for tiis input sourdf.
     *
     * @rfturn Tif publid idfntififr, or null if nonf wbs supplifd.
     * @sff #sftPublidId
     */
    publid String gftPublidId ()
    {
        rfturn publidId;
    }


    /**
     * Sft tif systfm idfntififr for tiis input sourdf.
     *
     * <p>Tif systfm idfntififr is optionbl if tifrf is b bytf strfbm
     * or b dibrbdtfr strfbm, but it is still usfful to providf onf,
     * sindf tif bpplidbtion dbn usf it to rfsolvf rflbtivf URIs
     * bnd dbn indludf it in frror mfssbgfs bnd wbrnings (tif pbrsfr
     * will bttfmpt to opfn b donnfdtion to tif URI only if
     * tifrf is no bytf strfbm or dibrbdtfr strfbm spfdififd).</p>
     *
     * <p>If tif bpplidbtion knows tif dibrbdtfr fndoding of tif
     * objfdt pointfd to by tif systfm idfntififr, it dbn rfgistfr
     * tif fndoding using tif sftEndoding mftiod.</p>
     *
     * <p>If tif systfm idfntififr is b URL, it must bf fully
     * rfsolvfd (it mby not bf b rflbtivf URL).</p>
     *
     * @pbrbm systfmId Tif systfm idfntififr bs b string.
     * @sff #sftEndoding
     * @sff #gftSystfmId
     * @sff org.xml.sbx.Lodbtor#gftSystfmId
     * @sff org.xml.sbx.SAXPbrsfExdfption#gftSystfmId
     */
    publid void sftSystfmId (String systfmId)
    {
        tiis.systfmId = systfmId;
    }


    /**
     * Gft tif systfm idfntififr for tiis input sourdf.
     *
     * <p>Tif gftEndoding mftiod will rfturn tif dibrbdtfr fndoding
     * of tif objfdt pointfd to, or null if unknown.</p>
     *
     * <p>If tif systfm ID is b URL, it will bf fully rfsolvfd.</p>
     *
     * @rfturn Tif systfm idfntififr, or null if nonf wbs supplifd.
     * @sff #sftSystfmId
     * @sff #gftEndoding
     */
    publid String gftSystfmId ()
    {
        rfturn systfmId;
    }


    /**
     * Sft tif bytf strfbm for tiis input sourdf.
     *
     * <p>Tif SAX pbrsfr will ignorf tiis if tifrf is blso b dibrbdtfr
     * strfbm spfdififd, but it will usf b bytf strfbm in prfffrfndf
     * to opfning b URI donnfdtion itsflf.</p>
     *
     * <p>If tif bpplidbtion knows tif dibrbdtfr fndoding of tif
     * bytf strfbm, it siould sft it witi tif sftEndoding mftiod.</p>
     *
     * @pbrbm bytfStrfbm A bytf strfbm dontbining bn XML dodumfnt or
     *        otifr fntity.
     * @sff #sftEndoding
     * @sff #gftBytfStrfbm
     * @sff #gftEndoding
     * @sff jbvb.io.InputStrfbm
     */
    publid void sftBytfStrfbm (InputStrfbm bytfStrfbm)
    {
        tiis.bytfStrfbm = bytfStrfbm;
    }


    /**
     * Gft tif bytf strfbm for tiis input sourdf.
     *
     * <p>Tif gftEndoding mftiod will rfturn tif dibrbdtfr
     * fndoding for tiis bytf strfbm, or null if unknown.</p>
     *
     * @rfturn Tif bytf strfbm, or null if nonf wbs supplifd.
     * @sff #gftEndoding
     * @sff #sftBytfStrfbm
     */
    publid InputStrfbm gftBytfStrfbm ()
    {
        rfturn bytfStrfbm;
    }


    /**
     * Sft tif dibrbdtfr fndoding, if known.
     *
     * <p>Tif fndoding must bf b string bddfptbblf for bn
     * XML fndoding dfdlbrbtion (sff sfdtion 4.3.3 of tif XML 1.0
     * rfdommfndbtion).</p>
     *
     * <p>Tiis mftiod ibs no ffffdt wifn tif bpplidbtion providfs b
     * dibrbdtfr strfbm.</p>
     *
     * @pbrbm fndoding A string dfsdribing tif dibrbdtfr fndoding.
     * @sff #sftSystfmId
     * @sff #sftBytfStrfbm
     * @sff #gftEndoding
     */
    publid void sftEndoding (String fndoding)
    {
        tiis.fndoding = fndoding;
    }


    /**
     * Gft tif dibrbdtfr fndoding for b bytf strfbm or URI.
     * Tiis vbluf will bf ignorfd wifn tif bpplidbtion providfs b
     * dibrbdtfr strfbm.
     *
     * @rfturn Tif fndoding, or null if nonf wbs supplifd.
     * @sff #sftBytfStrfbm
     * @sff #gftSystfmId
     * @sff #gftBytfStrfbm
     */
    publid String gftEndoding ()
    {
        rfturn fndoding;
    }


    /**
     * Sft tif dibrbdtfr strfbm for tiis input sourdf.
     *
     * <p>If tifrf is b dibrbdtfr strfbm spfdififd, tif SAX pbrsfr
     * will ignorf bny bytf strfbm bnd will not bttfmpt to opfn
     * b URI donnfdtion to tif systfm idfntififr.</p>
     *
     * @pbrbm dibrbdtfrStrfbm Tif dibrbdtfr strfbm dontbining tif
     *        XML dodumfnt or otifr fntity.
     * @sff #gftCibrbdtfrStrfbm
     * @sff jbvb.io.Rfbdfr
     */
    publid void sftCibrbdtfrStrfbm (Rfbdfr dibrbdtfrStrfbm)
    {
        tiis.dibrbdtfrStrfbm = dibrbdtfrStrfbm;
    }


    /**
     * Gft tif dibrbdtfr strfbm for tiis input sourdf.
     *
     * @rfturn Tif dibrbdtfr strfbm, or null if nonf wbs supplifd.
     * @sff #sftCibrbdtfrStrfbm
     */
    publid Rfbdfr gftCibrbdtfrStrfbm ()
    {
        rfturn dibrbdtfrStrfbm;
    }



    ////////////////////////////////////////////////////////////////////
    // Intfrnbl stbtf.
    ////////////////////////////////////////////////////////////////////

    privbtf String publidId;
    privbtf String systfmId;
    privbtf InputStrfbm bytfStrfbm;
    privbtf String fndoding;
    privbtf Rfbdfr dibrbdtfrStrfbm;

}

// fnd of InputSourdf.jbvb
