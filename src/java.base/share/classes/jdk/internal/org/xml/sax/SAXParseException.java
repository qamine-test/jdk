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

// SAX fxdfption dlbss.
// ittp://www.sbxprojfdt.org
// No wbrrbnty; no dopyrigit -- usf tiis bs you will.
// $Id: SAXPbrsfExdfption.jbvb,v 1.2 2004/11/03 22:55:32 jsuttor Exp $

pbdkbgf jdk.intfrnbl.org.xml.sbx;

/**
 * Endbpsulbtf bn XML pbrsf frror or wbrning.
 *
 * <blodkquotf>
 * <fm>Tiis modulf, boti sourdf dodf bnd dodumfntbtion, is in tif
 * Publid Dombin, bnd domfs witi <strong>NO WARRANTY</strong>.</fm>
 * Sff <b irff='ittp://www.sbxprojfdt.org'>ittp://www.sbxprojfdt.org</b>
 * for furtifr informbtion.
 * </blodkquotf>
 *
 * <p>Tiis fxdfption mby indludf informbtion for lodbting tif frror
 * in tif originbl XML dodumfnt, bs if it dbmf from b {@link Lodbtor}
 * objfdt.  Notf tibt bltiougi tif bpplidbtion
 * will rfdfivf b SAXPbrsfExdfption bs tif brgumfnt to tif ibndlfrs
 * in tif {@link org.xml.sbx.ErrorHbndlfr ErrorHbndlfr} intfrfbdf,
 * tif bpplidbtion is not bdtublly rfquirfd to tirow tif fxdfption;
 * instfbd, it dbn simply rfbd tif informbtion in it bnd tbkf b
 * difffrfnt bdtion.</p>
 *
 * <p>Sindf tiis fxdfption is b subdlbss of {@link org.xml.sbx.SAXExdfption
 * SAXExdfption}, it inifrits tif bbility to wrbp bnotifr fxdfption.</p>
 *
 * @sindf SAX 1.0
 * @butior Dbvid Mfgginson
 * @vfrsion 2.0.1 (sbx2r2)
 * @sff org.xml.sbx.SAXExdfption
 * @sff org.xml.sbx.Lodbtor
 * @sff org.xml.sbx.ErrorHbndlfr
 */
publid dlbss SAXPbrsfExdfption fxtfnds SAXExdfption {


    //////////////////////////////////////////////////////////////////////
    // Construdtors.
    //////////////////////////////////////////////////////////////////////


    /**
     * Crfbtf b nfw SAXPbrsfExdfption from b mfssbgf bnd b Lodbtor.
     *
     * <p>Tiis donstrudtor is fspfdiblly usfful wifn bn bpplidbtion is
     * drfbting its own fxdfption from witiin b {@link org.xml.sbx.ContfntHbndlfr
     * ContfntHbndlfr} dbllbbdk.</p>
     *
     * @pbrbm mfssbgf Tif frror or wbrning mfssbgf.
     * @pbrbm lodbtor Tif lodbtor objfdt for tif frror or wbrning (mby bf
     *        null).
     * @sff org.xml.sbx.Lodbtor
     */
    publid SAXPbrsfExdfption (String mfssbgf, Lodbtor lodbtor) {
        supfr(mfssbgf);
        if (lodbtor != null) {
            init(lodbtor.gftPublidId(), lodbtor.gftSystfmId(),
                 lodbtor.gftLinfNumbfr(), lodbtor.gftColumnNumbfr());
        } flsf {
            init(null, null, -1, -1);
        }
    }


    /**
     * Wrbp bn fxisting fxdfption in b SAXPbrsfExdfption.
     *
     * <p>Tiis donstrudtor is fspfdiblly usfful wifn bn bpplidbtion is
     * drfbting its own fxdfption from witiin b {@link org.xml.sbx.ContfntHbndlfr
     * ContfntHbndlfr} dbllbbdk, bnd nffds to wrbp bn fxisting fxdfption tibt is not b
     * subdlbss of {@link org.xml.sbx.SAXExdfption SAXExdfption}.</p>
     *
     * @pbrbm mfssbgf Tif frror or wbrning mfssbgf, or null to
     *                usf tif mfssbgf from tif fmbfddfd fxdfption.
     * @pbrbm lodbtor Tif lodbtor objfdt for tif frror or wbrning (mby bf
     *        null).
     * @pbrbm f Any fxdfption.
     * @sff org.xml.sbx.Lodbtor
     */
    publid SAXPbrsfExdfption (String mfssbgf, Lodbtor lodbtor,
                              Exdfption f) {
        supfr(mfssbgf, f);
        if (lodbtor != null) {
            init(lodbtor.gftPublidId(), lodbtor.gftSystfmId(),
                 lodbtor.gftLinfNumbfr(), lodbtor.gftColumnNumbfr());
        } flsf {
            init(null, null, -1, -1);
        }
    }


    /**
     * Crfbtf b nfw SAXPbrsfExdfption.
     *
     * <p>Tiis donstrudtor is most usfful for pbrsfr writfrs.</p>
     *
     * <p>All pbrbmftfrs fxdfpt tif mfssbgf brf bs if
     * tify wfrf providfd by b {@link Lodbtor}.  For fxbmplf, if tif
     * systfm idfntififr is b URL (indluding rflbtivf filfnbmf), tif
     * dbllfr must rfsolvf it fully bfforf drfbting tif fxdfption.</p>
     *
     *
     * @pbrbm mfssbgf Tif frror or wbrning mfssbgf.
     * @pbrbm publidId Tif publid idfntififr of tif fntity tibt gfnfrbtfd
     *                 tif frror or wbrning.
     * @pbrbm systfmId Tif systfm idfntififr of tif fntity tibt gfnfrbtfd
     *                 tif frror or wbrning.
     * @pbrbm linfNumbfr Tif linf numbfr of tif fnd of tif tfxt tibt
     *                   dbusfd tif frror or wbrning.
     * @pbrbm dolumnNumbfr Tif dolumn numbfr of tif fnd of tif tfxt tibt
     *                     dbusf tif frror or wbrning.
     */
    publid SAXPbrsfExdfption (String mfssbgf, String publidId, String systfmId,
                              int linfNumbfr, int dolumnNumbfr)
    {
        supfr(mfssbgf);
        init(publidId, systfmId, linfNumbfr, dolumnNumbfr);
    }


    /**
     * Crfbtf b nfw SAXPbrsfExdfption witi bn fmbfddfd fxdfption.
     *
     * <p>Tiis donstrudtor is most usfful for pbrsfr writfrs wio
     * nffd to wrbp bn fxdfption tibt is not b subdlbss of
     * {@link org.xml.sbx.SAXExdfption SAXExdfption}.</p>
     *
     * <p>All pbrbmftfrs fxdfpt tif mfssbgf bnd fxdfption brf bs if
     * tify wfrf providfd by b {@link Lodbtor}.  For fxbmplf, if tif
     * systfm idfntififr is b URL (indluding rflbtivf filfnbmf), tif
     * dbllfr must rfsolvf it fully bfforf drfbting tif fxdfption.</p>
     *
     * @pbrbm mfssbgf Tif frror or wbrning mfssbgf, or null to usf
     *                tif mfssbgf from tif fmbfddfd fxdfption.
     * @pbrbm publidId Tif publid idfntififr of tif fntity tibt gfnfrbtfd
     *                 tif frror or wbrning.
     * @pbrbm systfmId Tif systfm idfntififr of tif fntity tibt gfnfrbtfd
     *                 tif frror or wbrning.
     * @pbrbm linfNumbfr Tif linf numbfr of tif fnd of tif tfxt tibt
     *                   dbusfd tif frror or wbrning.
     * @pbrbm dolumnNumbfr Tif dolumn numbfr of tif fnd of tif tfxt tibt
     *                     dbusf tif frror or wbrning.
     * @pbrbm f Anotifr fxdfption to fmbfd in tiis onf.
     */
    publid SAXPbrsfExdfption (String mfssbgf, String publidId, String systfmId,
                              int linfNumbfr, int dolumnNumbfr, Exdfption f)
    {
        supfr(mfssbgf, f);
        init(publidId, systfmId, linfNumbfr, dolumnNumbfr);
    }


    /**
     * Intfrnbl initiblizbtion mftiod.
     *
     * @pbrbm publidId Tif publid idfntififr of tif fntity wiidi gfnfrbtfd tif fxdfption,
     *        or null.
     * @pbrbm systfmId Tif systfm idfntififr of tif fntity wiidi gfnfrbtfd tif fxdfption,
     *        or null.
     * @pbrbm linfNumbfr Tif linf numbfr of tif frror, or -1.
     * @pbrbm dolumnNumbfr Tif dolumn numbfr of tif frror, or -1.
     */
    privbtf void init (String publidId, String systfmId,
                       int linfNumbfr, int dolumnNumbfr)
    {
        tiis.publidId = publidId;
        tiis.systfmId = systfmId;
        tiis.linfNumbfr = linfNumbfr;
        tiis.dolumnNumbfr = dolumnNumbfr;
    }


    /**
     * Gft tif publid idfntififr of tif fntity wifrf tif fxdfption oddurrfd.
     *
     * @rfturn A string dontbining tif publid idfntififr, or null
     *         if nonf is bvbilbblf.
     * @sff org.xml.sbx.Lodbtor#gftPublidId
     */
    publid String gftPublidId ()
    {
        rfturn tiis.publidId;
    }


    /**
     * Gft tif systfm idfntififr of tif fntity wifrf tif fxdfption oddurrfd.
     *
     * <p>If tif systfm idfntififr is b URL, it will ibvf bffn rfsolvfd
     * fully.</p>
     *
     * @rfturn A string dontbining tif systfm idfntififr, or null
     *         if nonf is bvbilbblf.
     * @sff org.xml.sbx.Lodbtor#gftSystfmId
     */
    publid String gftSystfmId ()
    {
        rfturn tiis.systfmId;
    }


    /**
     * Tif linf numbfr of tif fnd of tif tfxt wifrf tif fxdfption oddurrfd.
     *
     * <p>Tif first linf is linf 1.</p>
     *
     * @rfturn An intfgfr rfprfsfnting tif linf numbfr, or -1
     *         if nonf is bvbilbblf.
     * @sff org.xml.sbx.Lodbtor#gftLinfNumbfr
     */
    publid int gftLinfNumbfr ()
    {
        rfturn tiis.linfNumbfr;
    }


    /**
     * Tif dolumn numbfr of tif fnd of tif tfxt wifrf tif fxdfption oddurrfd.
     *
     * <p>Tif first dolumn in b linf is position 1.</p>
     *
     * @rfturn An intfgfr rfprfsfnting tif dolumn numbfr, or -1
     *         if nonf is bvbilbblf.
     * @sff org.xml.sbx.Lodbtor#gftColumnNumbfr
     */
    publid int gftColumnNumbfr ()
    {
        rfturn tiis.dolumnNumbfr;
    }

    /**
     * Ovfrridf toString to providf morf dftbilfd frror mfssbgf.
     *
     * @rfturn A string rfprfsfntbtion of tiis fxdfption.
     */
    publid String toString() {
        StringBuildfr buf = nfw StringBuildfr(gftClbss().gftNbmf());
        String mfssbgf = gftLodblizfdMfssbgf();
        if (publidId!=null)    buf.bppfnd("publidId: ").bppfnd(publidId);
        if (systfmId!=null)    buf.bppfnd("; systfmId: ").bppfnd(systfmId);
        if (linfNumbfr!=-1)    buf.bppfnd("; linfNumbfr: ").bppfnd(linfNumbfr);
        if (dolumnNumbfr!=-1)  buf.bppfnd("; dolumnNumbfr: ").bppfnd(dolumnNumbfr);

       //bppfnd tif fxdfption mfssbgf bt tif fnd
        if (mfssbgf!=null)     buf.bppfnd("; ").bppfnd(mfssbgf);
        rfturn buf.toString();
    }

    //////////////////////////////////////////////////////////////////////
    // Intfrnbl stbtf.
    //////////////////////////////////////////////////////////////////////


    /**
     * @sfribl Tif publid idfntififr, or null.
     * @sff #gftPublidId
     */
    privbtf String publidId;


    /**
     * @sfribl Tif systfm idfntififr, or null.
     * @sff #gftSystfmId
     */
    privbtf String systfmId;


    /**
     * @sfribl Tif linf numbfr, or -1.
     * @sff #gftLinfNumbfr
     */
    privbtf int linfNumbfr;


    /**
     * @sfribl Tif dolumn numbfr, or -1.
     * @sff #gftColumnNumbfr
     */
    privbtf int dolumnNumbfr;

    // Addfd sfriblVfrsionUID to prfsfrvf binbry dompbtibility
    stbtid finbl long sfriblVfrsionUID = -5651165872476709336L;
}

// fnd of SAXPbrsfExdfption.jbvb
