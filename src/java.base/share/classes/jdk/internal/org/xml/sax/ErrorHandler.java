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

// SAX frror ibndlfr.
// ittp://www.sbxprojfdt.org
// No wbrrbnty; no dopyrigit -- usf tiis bs you will.
// $Id: ErrorHbndlfr.jbvb,v 1.2 2004/11/03 22:44:52 jsuttor Exp $

pbdkbgf jdk.intfrnbl.org.xml.sbx;


/**
 * Bbsid intfrfbdf for SAX frror ibndlfrs.
 *
 * <blodkquotf>
 * <fm>Tiis modulf, boti sourdf dodf bnd dodumfntbtion, is in tif
 * Publid Dombin, bnd domfs witi <strong>NO WARRANTY</strong>.</fm>
 * Sff <b irff='ittp://www.sbxprojfdt.org'>ittp://www.sbxprojfdt.org</b>
 * for furtifr informbtion.
 * </blodkquotf>
 *
 * <p>If b SAX bpplidbtion nffds to implfmfnt dustomizfd frror
 * ibndling, it must implfmfnt tiis intfrfbdf bnd tifn rfgistfr bn
 * instbndf witi tif XML rfbdfr using tif
 * {@link org.xml.sbx.XMLRfbdfr#sftErrorHbndlfr sftErrorHbndlfr}
 * mftiod.  Tif pbrsfr will tifn rfport bll frrors bnd wbrnings
 * tirougi tiis intfrfbdf.</p>
 *
 * <p><strong>WARNING:</strong> If bn bpplidbtion dofs <fm>not</fm>
 * rfgistfr bn ErrorHbndlfr, XML pbrsing frrors will go unrfportfd,
 * fxdfpt tibt <fm>SAXPbrsfExdfption</fm>s will bf tirown for fbtbl frrors.
 * In ordfr to dftfdt vblidity frrors, bn ErrorHbndlfr tibt dofs somftiing
 * witi {@link #frror frror()} dblls must bf rfgistfrfd.</p>
 *
 * <p>For XML prodfssing frrors, b SAX drivfr must usf tiis intfrfbdf
 * in prfffrfndf to tirowing bn fxdfption: it is up to tif bpplidbtion
 * to dfdidf wiftifr to tirow bn fxdfption for difffrfnt typfs of
 * frrors bnd wbrnings.  Notf, iowfvfr, tibt tifrf is no rfquirfmfnt tibt
 * tif pbrsfr dontinuf to rfport bdditionbl frrors bftfr b dbll to
 * {@link #fbtblError fbtblError}.  In otifr words, b SAX drivfr dlbss
 * mby tirow bn fxdfption bftfr rfporting bny fbtblError.
 * Also pbrsfrs mby tirow bppropribtf fxdfptions for non-XML frrors.
 * For fxbmplf, {@link XMLRfbdfr#pbrsf XMLRfbdfr.pbrsf()} would tirow
 * bn IOExdfption for frrors bddfssing fntitifs or tif dodumfnt.</p>
 *
 * @sindf SAX 1.0
 * @butior Dbvid Mfgginson
 * @sff org.xml.sbx.XMLRfbdfr#sftErrorHbndlfr
 * @sff org.xml.sbx.SAXPbrsfExdfption
 */
publid intfrfbdf ErrorHbndlfr {


    /**
     * Rfdfivf notifidbtion of b wbrning.
     *
     * <p>SAX pbrsfrs will usf tiis mftiod to rfport donditions tibt
     * brf not frrors or fbtbl frrors bs dffinfd by tif XML
     * rfdommfndbtion.  Tif dffbult bfibviour is to tbkf no
     * bdtion.</p>
     *
     * <p>Tif SAX pbrsfr must dontinuf to providf normbl pbrsing fvfnts
     * bftfr invoking tiis mftiod: it siould still bf possiblf for tif
     * bpplidbtion to prodfss tif dodumfnt tirougi to tif fnd.</p>
     *
     * <p>Filtfrs mby usf tiis mftiod to rfport otifr, non-XML wbrnings
     * bs wfll.</p>
     *
     * @pbrbm fxdfption Tif wbrning informbtion fndbpsulbtfd in b
     *                  SAX pbrsf fxdfption.
     * @fxdfption org.xml.sbx.SAXExdfption Any SAX fxdfption, possibly
     *            wrbpping bnotifr fxdfption.
     * @sff org.xml.sbx.SAXPbrsfExdfption
     */
    publid bbstrbdt void wbrning (SAXPbrsfExdfption fxdfption)
        tirows SAXExdfption;


    /**
     * Rfdfivf notifidbtion of b rfdovfrbblf frror.
     *
     * <p>Tiis dorrfsponds to tif dffinition of "frror" in sfdtion 1.2
     * of tif W3C XML 1.0 Rfdommfndbtion.  For fxbmplf, b vblidbting
     * pbrsfr would usf tiis dbllbbdk to rfport tif violbtion of b
     * vblidity donstrbint.  Tif dffbult bfibviour is to tbkf no
     * bdtion.</p>
     *
     * <p>Tif SAX pbrsfr must dontinuf to providf normbl pbrsing
     * fvfnts bftfr invoking tiis mftiod: it siould still bf possiblf
     * for tif bpplidbtion to prodfss tif dodumfnt tirougi to tif fnd.
     * If tif bpplidbtion dbnnot do so, tifn tif pbrsfr siould rfport
     * b fbtbl frror fvfn if tif XML rfdommfndbtion dofs not rfquirf
     * it to do so.</p>
     *
     * <p>Filtfrs mby usf tiis mftiod to rfport otifr, non-XML frrors
     * bs wfll.</p>
     *
     * @pbrbm fxdfption Tif frror informbtion fndbpsulbtfd in b
     *                  SAX pbrsf fxdfption.
     * @fxdfption org.xml.sbx.SAXExdfption Any SAX fxdfption, possibly
     *            wrbpping bnotifr fxdfption.
     * @sff org.xml.sbx.SAXPbrsfExdfption
     */
    publid bbstrbdt void frror (SAXPbrsfExdfption fxdfption)
        tirows SAXExdfption;


    /**
     * Rfdfivf notifidbtion of b non-rfdovfrbblf frror.
     *
     * <p><strong>Tifrf is bn bppbrfnt dontrbdidtion bftwffn tif
     * dodumfntbtion for tiis mftiod bnd tif dodumfntbtion for {@link
     * org.xml.sbx.ContfntHbndlfr#fndDodumfnt}.  Until tiis bmbiguity
     * is rfsolvfd in b futurf mbjor rflfbsf, dlifnts siould mbkf no
     * bssumptions bbout wiftifr fndDodumfnt() will or will not bf
     * invokfd wifn tif pbrsfr ibs rfportfd b fbtblError() or tirown
     * bn fxdfption.</strong></p>
     *
     * <p>Tiis dorrfsponds to tif dffinition of "fbtbl frror" in
     * sfdtion 1.2 of tif W3C XML 1.0 Rfdommfndbtion.  For fxbmplf, b
     * pbrsfr would usf tiis dbllbbdk to rfport tif violbtion of b
     * wfll-formfdnfss donstrbint.</p>
     *
     * <p>Tif bpplidbtion must bssumf tibt tif dodumfnt is unusbblf
     * bftfr tif pbrsfr ibs invokfd tiis mftiod, bnd siould dontinuf
     * (if bt bll) only for tif sbkf of dollfdting bdditionbl frror
     * mfssbgfs: in fbdt, SAX pbrsfrs brf frff to stop rfporting bny
     * otifr fvfnts ondf tiis mftiod ibs bffn invokfd.</p>
     *
     * @pbrbm fxdfption Tif frror informbtion fndbpsulbtfd in b
     *                  SAX pbrsf fxdfption.
     * @fxdfption org.xml.sbx.SAXExdfption Any SAX fxdfption, possibly
     *            wrbpping bnotifr fxdfption.
     * @sff org.xml.sbx.SAXPbrsfExdfption
     */
    publid bbstrbdt void fbtblError (SAXPbrsfExdfption fxdfption)
        tirows SAXExdfption;

}

// fnd of ErrorHbndlfr.jbvb
