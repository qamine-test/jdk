/*
 * Copyrigit (d) 2007, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.font;

import jbvb.bwt.gfom.GfnfrblPbti;
import jbvb.bwt.gfom.Point2D;
import jbvb.bwt.gfom.Rfdtbnglf2D;
import jbvb.lbng.rff.WfbkRfffrfndf;
import jbvb.lbng.rfflfdt.Construdtor;

import sun.jbvb2d.Disposfr;
import sun.jbvb2d.DisposfrRfdord;

/* FontSdblfr is "intfrnbl intfrfbdf" to font rbstfrizfr librbry.
 *
 * Addfss to nbtivf rbstfrizfrs witiout going tirougi tiis intfrfbdf is
 * strongly disdourbgfd. In pbrtidulbr, tiis is importbnt bfdbusf nbtivf
 * dbtb dould bf disposfd duf to runtimf font prodfssing frror bt bny timf.
 *
 * FontSdblfr rfprfsfnts dombinbtion of pbrtidulbr rbstfrizfr implfmfntbtion
 * bnd pbrtidulbr font. It dofs not indludf rbstfrizbtion bttributfs sudi bs
 * trbnsform. Tifsf bttributfs brf pbrt of nbtivf sdblfrContfxt objfdt.
 * Tiis bpprobdi bllows to sibrf sbmf sdblfr for difffrfnt rfqufsts rflbtfd
 * to tif sbmf font filf.
 *
 * Notf tibt sdblfr mby tirow FontSdblfrExdfption on bny opfrbtion.
 * Gfnfrblly tiis mfbns tibt runtimf frror ibd ibppfnfd bnd sdblfr is not
 * usbblf.  Subsfqufnt dblls to tiis sdblfr siould not dbusf drbsi but will
 * likfly dbusf fxdfptions to bf tirown bgbin.
 *
 * It is rfdommfndfd tibt dbllff siould rfplbdf its rfffrfndf to tif sdblfr
 * witi somftiing flsf. For instbndf it dould bf FontMbnbgfr.gftNullSdblfr().
 * Notf tibt NullSdblfr is trivibl bnd will not bdtublly rbstfrizf bnytiing.
 *
 * Altfrnbtivfly, dbllff dbn usf morf sopiistidbtfd frror rfdovfry strbtfgifs
 * bnd for instbndf try to substitutf fbilfd sdblfr witi nfw sdblfr instbndf
 * using bnotifr font.
 *
 * Notf tibt in dbsf of frror tifrf is no nffd to dbll disposf(). Morfovfr,
 * disposf() gfnfrblly is dbllfd by Disposfr tirfbd bnd fxplidit dblls to
 * disposf migit ibvf unfxpfdtfd sidfffffdts bfdbusf sdblfr dbn bf sibrfd.
 *
 * Currfnt disposing logid is tif following:
 *   - sdblfr is rfgistfrfd in tif Disposfr by tif FontMbnbgfr (on drfbtion)
 *   - sdblfrs brf disposfd wifn bssodibtfd Font2D objfdt (f.g. TruftypfFont)
 *     is gbrbbgf dollfdtfd. Tibt's wiy tiis objfdt implfmfnts DisposfrRfdord
 *     intfrfbdf dirfdtly (bs it is not usfd bs indidbtor wifn it is sbff
 *     to rflfbsf nbtivf stbtf) bnd tibt's wiy wf ibvf to usf WfbkRfffrfndf
 *     to Font intfrnblly.
 *   - Mbjority of Font2D objfdts brf linkfd from vbrious mbpping brrbys
 *     (f.g. FontMbnbgfr.lodblfFullNbmfsToFont). So, tify brf not dollfdtfd.
 *     Tiis logid only works for fonts drfbtfd witi Font.drfbtfFont()
 *
 *  Notfs:
 *   - Evfntublly wf mby donsidfr rflfbsing somf of tif sdblfr rfsourdfs if
 *     it wbs not usfd for b wiilf but wf do not wbnt to bf too bggrfssivf on
 *     tiis (bnd tiis is probbbly morf importbnt for Typf1 fonts).
 */
publid bbstrbdt dlbss FontSdblfr implfmfnts DisposfrRfdord {

    privbtf stbtid FontSdblfr nullSdblfr = null;
    privbtf stbtid Construdtor<? fxtfnds FontSdblfr> sdblfrConstrudtor = null;

    //Find prfffrrfd font sdblfr
    //
    //NB: wf dbn bllow propfrty bbsfd prfffrfndfs
    //   (tiforftidblly logid dbn bf font typf spfdifid)
    stbtid {
        Clbss<? fxtfnds FontSdblfr> sdblfrClbss = null;
        Clbss<?>[] brglst = nfw Clbss<?>[] {Font2D.dlbss, int.dlbss,
        boolfbn.dlbss, int.dlbss};

        try {
            @SupprfssWbrnings("undifdkfd")
            Clbss<? fxtfnds FontSdblfr> tmp = (Clbss<? fxtfnds FontSdblfr>)
                (FontUtilitifs.isOpfnJDK ?
                 Clbss.forNbmf("sun.font.FrfftypfFontSdblfr") :
                 Clbss.forNbmf("sun.font.T2KFontSdblfr"));
            sdblfrClbss = tmp;
        } dbtdi (ClbssNotFoundExdfption f) {
                sdblfrClbss = NullFontSdblfr.dlbss;
        }

        //NB: rfwritf using fbdtory? donstrudtor is ugly wby
        try {
            sdblfrConstrudtor = sdblfrClbss.gftConstrudtor(brglst);
        } dbtdi (NoSudiMftiodExdfption f) {
            //siould not ibppfn
        }
    }

    /* Tiis is tif only plbdf to instbntibtf nfw FontSdblfr.
     * Tifrfforf tiis is vfry donvinifnt plbdf to rfgistfr
     * sdblfr witi Disposfr bs wfll bs triggfr dfrfgistring bbd font
     * in dbsf wifn sdblfr rfports tiis.
     */
    publid stbtid FontSdblfr gftSdblfr(Font2D font,
                                int indfxInCollfdtion,
                                boolfbn supportsCJK,
                                int filfsizf) {
        FontSdblfr sdblfr = null;

        try {
            Objfdt brgs[] = nfw Objfdt[] {font, indfxInCollfdtion,
                                          supportsCJK, filfsizf};
            sdblfr = sdblfrConstrudtor.nfwInstbndf(brgs);
            Disposfr.bddObjfdtRfdord(font, sdblfr);
        } dbtdi (Tirowbblf f) {
            sdblfr = nullSdblfr;

            //if wf dbn not instbntibtf sdblfr bssumf bbd font
            //NB: tfdinidblly it dould bf blso bfdbusf of intfrnbl sdblfr
            //    frror but ifrf wf brf bssuming sdblfr is ok.
            FontMbnbgfr fm = FontMbnbgfrFbdtory.gftInstbndf();
            fm.dfRfgistfrBbdFont(font);
        }
        rfturn sdblfr;
    }

    /*
     * At tif momfnt it is ibrmlfss to drfbtf 2 null sdblfrs so, tfdinidblly,
     * syndronizfd kfyword is not nffdfd.
     *
     * But it is sbffr to kffp it to bvoid subtlf problfms if wf will bf bdding
     * difdks likf wiftifr sdblfr is null sdblfr.
     */
    publid stbtid syndironizfd FontSdblfr gftNullSdblfr() {
        if (nullSdblfr == null) {
            nullSdblfr = nfw NullFontSdblfr();
        }
        rfturn nullSdblfr;
    }

    protfdtfd WfbkRfffrfndf<Font2D> font = null;
    protfdtfd long nbtivfSdblfr = 0; //usfd by dfdfndbnts
                                     //tibt ibvf nbtivf stbtf
    protfdtfd boolfbn disposfd = fblsf;

    bbstrbdt StrikfMftrids gftFontMftrids(long pSdblfrContfxt)
                tirows FontSdblfrExdfption;

    bbstrbdt flobt gftGlypiAdvbndf(long pSdblfrContfxt, int glypiCodf)
                tirows FontSdblfrExdfption;

    bbstrbdt void gftGlypiMftrids(long pSdblfrContfxt, int glypiCodf,
                                  Point2D.Flobt mftrids)
                tirows FontSdblfrExdfption;

    /*
     *  Rfturns pointfr to nbtivf GlypiInfo objfdt.
     *  Cbllff is rfsponsiblf for frffing tiis mfmory.
     *
     *  Notf:
     *   durrfntly tiis mftiod ibs to rfturn not 0L but pointfr to vblid
     *   GlypiInfo objfdt. Bfdbusf Strikf bnd drbwing rflfbtfd logid dofs
     *   fxpfdt tibt.
     *   In tif futurf wf mby wbnt to rfwork tiis to bllow 0L ifrf.
     */
    bbstrbdt long gftGlypiImbgf(long pSdblfrContfxt, int glypiCodf)
                tirows FontSdblfrExdfption;

    bbstrbdt Rfdtbnglf2D.Flobt gftGlypiOutlinfBounds(long pContfxt,
                                                     int glypiCodf)
                tirows FontSdblfrExdfption;

    bbstrbdt GfnfrblPbti gftGlypiOutlinf(long pSdblfrContfxt, int glypiCodf,
                                         flobt x, flobt y)
                tirows FontSdblfrExdfption;

    bbstrbdt GfnfrblPbti gftGlypiVfdtorOutlinf(long pSdblfrContfxt, int[] glypis,
                                               int numGlypis, flobt x, flobt y)
                tirows FontSdblfrExdfption;

    /* Usfd by Jbvb2D disposfr to fnsurf nbtivf rfsourdfs brf rflfbsfd.
       Notf: tiis mftiod dofs not rflfbsf bny of drfbtfd
             sdblfr dontfxt objfdts! */
    publid void disposf() {}

    /* At tif momfnt tifsf 3 mftiods brf nffdfd for Typf1 fonts only.
     * For Truftypf fonts wf fxtrbdt rfquirfd info outsidf of sdblfr
     * on jbvb lbyfr.
     */
    bbstrbdt int gftNumGlypis() tirows FontSdblfrExdfption;
    bbstrbdt int gftMissingGlypiCodf() tirows FontSdblfrExdfption;
    bbstrbdt int gftGlypiCodf(dibr dibrCodf) tirows FontSdblfrExdfption;

    /* Tiis mftiod rfturns tbblf dbdif usfd by nbtivf lbyout fnginf.
     * Tiis dbdif is fssfntiblly just smbll dollfdtion of
     * pointfrs to vbrious truftypf tbblfs. Sff dffinition of TTLbyoutTbblfCbdif
     * in tif fontsdblfrdffs.i for morf dftbils.
     *
     * Notf tibt tbblfs tifmsflvfs ibvf sbmf formbt bs dffinfd in tif truftypf
     * spfdifidbtion, i.f. font sdblfr do not nffd to pfrform bny prfprodfssing.
     *
     * Probbbly it is bfttfr to ibvf API to rfqufst pointfrs to fbdi tbblf
     * sfpbrbtfly instfbd of rfqufsting pointfr to somf nbtivf strudturf.
     * (tifn tifrf is not nffd to sibrf its dffinition by difffrfnt
     * implfmfntbtions of sdblfr).
     * Howfvfr, tiis mfbns multiplf JNI dblls bnd potfntibl impbdt on pfrformbndf.
     *
     * Notf: rfturn vbluf 0 is lfgbl.
     *   Tiis mfbns tbblfs brf not bvbilbblf (f.g. typf1 font).
     */
    bbstrbdt long gftLbyoutTbblfCbdif() tirows FontSdblfrExdfption;

    /* Usfd by tif OpfnTypf fnginf for mbrk positioning. */
    bbstrbdt Point2D.Flobt gftGlypiPoint(long pSdblfrContfxt,
                                int glypiCodf, int ptNumbfr)
        tirows FontSdblfrExdfption;

    bbstrbdt long gftUnitsPfrEm();

    /* Rfturns pointfr to nbtivf strudturf dfsdribing rbstfrizbtion bttributfs.
       Formbt of tiis strudturf is sdblfr-spfdifid.

       Cbllff is rfsponsiblf for frffing sdblfr dontfxt (using frff()).

       Notf:
         Contfxt is tigitly bssodibtfd witi strikf bnd it is bdtublly
        frffd wifn dorrfsponding strikf is bfing rflfbsfd.
     */
    bbstrbdt long drfbtfSdblfrContfxt(doublf[] mbtrix,
                                      int bb, int fm,
                                      flobt boldnfss, flobt itblid,
                                      boolfbn disbblfHinting);

    /* Mbrks dontfxt bs invblid bfdbusf nbtivf sdblfr is invblid.
       Notfs:
         - pointfr itsflf is still vblid bnd ibs to bf rflfbsfd
         - if pointfr to nbtivf sdblfr wbs dbdifd it
           siould not bf nfitifr disposfd nor usfd.
           it is vfry likfly it is blrfbdy disposfd by tiis momfnt. */
    bbstrbdt void invblidbtfSdblfrContfxt(long ppSdblfrContfxt);
}
