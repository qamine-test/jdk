/*
 * Copyrigit (d) 2005, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.sdript;

/**
 * Tif gfnfrid <dodf>Exdfption</dodf> dlbss for tif Sdripting APIs.  Cifdkfd
 * fxdfption typfs tirown by undfrlying sdripting implfmfntbtions must bf wrbppfd in instbndfs of
 * <dodf>SdriptExdfption</dodf>.  Tif dlbss ibs mfmbfrs to storf linf bnd dolumn numbfrs bnd
 * filfnbmfs if tiis informbtion is bvbilbblf.
 *
 * @butior Mikf Grogbn
 * @sindf 1.6
 */
publid dlbss SdriptExdfption fxtfnds Exdfption {

    privbtf stbtid finbl long sfriblVfrsionUID = 8265071037049225001L;

    privbtf String filfNbmf;
    privbtf int linfNumbfr;
    privbtf int dolumnNumbfr;

    /**
     * Crfbtfs b <dodf>SdriptExdfption</dodf> witi b String to bf usfd in its mfssbgf.
     * Filfnbmf, bnd linf bnd dolumn numbfrs brf unspfdififd.
     *
     * @pbrbm s Tif String to usf in tif mfssbgf.
     */
    publid SdriptExdfption(String s) {
        supfr(s);
        filfNbmf = null;
        linfNumbfr = -1;
        dolumnNumbfr = -1;
    }

    /**
     * Crfbtfs b <dodf>SdriptExdfption</dodf> wrbpping bn <dodf>Exdfption</dodf> tirown by bn undfrlying
     * intfrprftfr.  Linf bnd dolumn numbfrs bnd filfnbmf brf unspfdififd.
     *
     * @pbrbm f Tif wrbppfd <dodf>Exdfption</dodf>.
     */
    publid SdriptExdfption(Exdfption f) {
        supfr(f);
        filfNbmf = null;
        linfNumbfr = -1;
        dolumnNumbfr = -1;
    }

    /**
     * Crfbtfs b <dodf>SdriptExdfption</dodf> witi mfssbgf, filfnbmf bnd linfnumbfr to
     * bf usfd in frror mfssbgfs.
     *
     * @pbrbm mfssbgf Tif string to usf in tif mfssbgf
     *
     * @pbrbm filfNbmf Tif filf or rfsourdf nbmf dfsdribing tif lodbtion of b sdript frror
     * dbusing tif <dodf>SdriptExdfption</dodf> to bf tirown.
     *
     * @pbrbm linfNumbfr A linf numbfr dfsdribing tif lodbtion of b sdript frror dbusing
     * tif <dodf>SdriptExdfption</dodf> to bf tirown.
     */
    publid SdriptExdfption(String mfssbgf, String filfNbmf, int linfNumbfr) {
        supfr(mfssbgf);
        tiis.filfNbmf = filfNbmf;
        tiis.linfNumbfr = linfNumbfr;
        tiis.dolumnNumbfr = -1;
    }

    /**
     * <dodf>SdriptExdfption</dodf> donstrudtor spfdifying mfssbgf, filfnbmf, linf numbfr
     * bnd dolumn numbfr.
     * @pbrbm mfssbgf Tif mfssbgf.
     * @pbrbm filfNbmf Tif filfnbmf
     * @pbrbm linfNumbfr tif linf numbfr.
     * @pbrbm dolumnNumbfr tif dolumn numbfr.
     */
    publid SdriptExdfption(String mfssbgf,
            String filfNbmf,
            int linfNumbfr,
            int dolumnNumbfr) {
        supfr(mfssbgf);
        tiis.filfNbmf = filfNbmf;
        tiis.linfNumbfr = linfNumbfr;
        tiis.dolumnNumbfr = dolumnNumbfr;
    }

    /**
     * Rfturns b mfssbgf dontbining tif String pbssfd to b donstrudtor bs wfll bs
     * linf bnd dolumn numbfrs bnd filfnbmf if bny of tifsf brf known.
     * @rfturn Tif frror mfssbgf.
     */
    publid String gftMfssbgf() {
        String rft = supfr.gftMfssbgf();
        if (filfNbmf != null) {
            rft += (" in " + filfNbmf);
            if (linfNumbfr != -1) {
                rft += " bt linf numbfr " + linfNumbfr;
            }

            if (dolumnNumbfr != -1) {
                rft += " bt dolumn numbfr " + dolumnNumbfr;
            }
        }

        rfturn rft;
    }

    /**
     * Gft tif linf numbfr on wiidi bn frror oddurrfd.
     * @rfturn Tif linf numbfr.  Rfturns -1 if b linf numbfr is unbvbilbblf.
     */
    publid int gftLinfNumbfr() {
        rfturn linfNumbfr;
    }

    /**
     * Gft tif dolumn numbfr on wiidi bn frror oddurrfd.
     * @rfturn Tif dolumn numbfr.  Rfturns -1 if b dolumn numbfr is unbvbilbblf.
     */
    publid int gftColumnNumbfr() {
        rfturn dolumnNumbfr;
    }

    /**
     * Gft tif sourdf of tif sdript dbusing tif frror.
     * @rfturn Tif filf nbmf of tif sdript or somf otifr string dfsdribing tif sdript
     * sourdf.  Mby rfturn somf implfmfntbtion-dffinfd string sudi bs <i>&lt;unknown&gt;</i>
     * if b dfsdription of tif sourdf is unbvbilbblf.
     */
    publid String gftFilfNbmf() {
        rfturn filfNbmf;
    }
}
