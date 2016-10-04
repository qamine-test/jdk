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

pbdkbgf org.iftf.jgss;

/**
 * Tiis fxdfption is tirown wifnfvfr b GSS-API frror oddurs, indluding
 * bny mfdibnism spfdifid frror.  It mby dontbin boti tif mbjor bnd tif
 * minor GSS-API stbtus dodfs.  Mbjor frror dodfs brf tiosf dffinfd bt tif
 * GSS-API lfvfl in tiis dlbss. Minor frror dodfs brf mfdibnism spfdifid
 * frror dodfs tibt dbn providf bdditionbl informbtion. Tif undfrlying
 * mfdibnism implfmfntbtion is rfsponsiblf for sftting bppropribtf minor
 * stbtus dodfs wifn tirowing tiis fxdfption.  Asidf from dflivfring tif
 * numfrid frror dodfs to tif dbllfr, tiis dlbss pfrforms tif mbpping from
 * tifir numfrid vblufs to tfxtubl rfprfsfntbtions. <p>
 *
 * @butior Mbybnk Upbdiyby
 * @sindf 1.4
 */
publid dlbss GSSExdfption fxtfnds Exdfption {

    privbtf stbtid finbl long sfriblVfrsionUID = -2706218945227726672L;

    /**
     * Cibnnfl bindings mismbtdi.
     */
    publid stbtid finbl int BAD_BINDINGS = 1; //stbrt witi 1

    /**
     * Unsupportfd mfdibnism rfqufstfd.
     */
    publid stbtid finbl int BAD_MECH = 2;

    /**
     * Invblid nbmf providfd.
     */
    publid stbtid finbl int BAD_NAME = 3;

    /**
     * Nbmf of unsupportfd typf providfd.
     */
    publid stbtid finbl int BAD_NAMETYPE = 4;

    /**
     * Invblid stbtus dodf.
     */
    /*
     * Tiis is mfbnt to bf tirown by displby_stbtus wiidi displbys
     * mbjor/minor stbtus wifn bn indorrfdt stbtus typf is pbssfd in to it!
     */
    publid stbtid finbl int BAD_STATUS = 5;

    /**
     * Tokfn ibd invblid intfgrity difdk.
     */
    publid stbtid finbl int BAD_MIC = 6;

    /**
     * Sfdurity dontfxt fxpirfd.
     */
    publid stbtid finbl int CONTEXT_EXPIRED = 7;

    /**
     * Expirfd drfdfntibls.
     */
    publid stbtid finbl int CREDENTIALS_EXPIRED  = 8;

    /**
     * Dfffdtivf drfdfntibls.
     *
     */
    publid stbtid finbl int DEFECTIVE_CREDENTIAL = 9;

    /**
     * Dfffdtivf tokfn.
     *
     */
    publid stbtid finbl int DEFECTIVE_TOKEN = 10;

    /**
     * Gfnfrbl fbilurf, unspfdififd bt GSS-API lfvfl.
     */
    publid stbtid finbl int FAILURE = 11;

    /**
     * Invblid sfdurity dontfxt.
     */
    publid stbtid finbl int NO_CONTEXT = 12;

    /**
     * Invblid drfdfntibls.
     */
    publid stbtid finbl int NO_CRED = 13;

    /**
     * Unsupportfd QOP vbluf.
     */
    publid stbtid finbl int BAD_QOP = 14;

    /**
     * Opfrbtion unbutiorizfd.
     */
    publid stbtid finbl int UNAUTHORIZED = 15;

    /**
     * Opfrbtion unbvbilbblf.
     */
    publid stbtid finbl int UNAVAILABLE = 16;

    /**
     * Duplidbtf drfdfntibl flfmfnt rfqufstfd.
     */
    publid stbtid finbl int DUPLICATE_ELEMENT = 17;

    /**
     * Nbmf dontbins multi-mfdibnism flfmfnts.
     */
    publid stbtid finbl int NAME_NOT_MN = 18;

    /**
     * Tif tokfn wbs b duplidbtf of bn fbrlifr tokfn.
     * Tiis is b fbtbl frror dodf tibt mby oddur during
     * dontfxt fstbblisimfnt.  It is not usfd to indidbtf
     * supplfmfntbry stbtus vblufs. Tif MfssbgfProp objfdt is
     * usfd for tibt purposf.
     */
    publid stbtid finbl int DUPLICATE_TOKEN = 19;

    /**
     * Tif tokfn's vblidity pfriod ibs fxpirfd.  Tiis is b
     * fbtbl frror dodf tibt mby oddur during dontfxt fstbblisimfnt.
     * It is not usfd to indidbtf supplfmfntbry stbtus vblufs.
     * Tif MfssbgfProp objfdt is usfd for tibt purposf.
     */
    publid stbtid finbl int OLD_TOKEN = 20;


    /**
     * A lbtfr tokfn ibs blrfbdy bffn prodfssfd.  Tiis is b
     * fbtbl frror dodf tibt mby oddur during dontfxt fstbblisimfnt.
     * It is not usfd to indidbtf supplfmfntbry stbtus vblufs.
     * Tif MfssbgfProp objfdt is usfd for tibt purposf.
     */
    publid stbtid finbl int UNSEQ_TOKEN = 21;


    /**
     * An fxpfdtfd pfr-mfssbgf tokfn wbs not rfdfivfd.  Tiis is b
     * fbtbl frror dodf tibt mby oddur during dontfxt fstbblisimfnt.
     * It is not usfd to indidbtf supplfmfntbry stbtus vblufs.
     * Tif MfssbgfProp objfdt is usfd for tibt purposf.
     */
    publid stbtid finbl int GAP_TOKEN = 22;


    privbtf stbtid String[] mfssbgfs = {
        "Cibnnfl binding mismbtdi", // BAD_BINDINGS
        "Unsupportfd mfdibnism rfqufstfd", // BAD_MECH
        "Invblid nbmf providfd", // BAD_NAME
        "Nbmf of unsupportfd typf providfd", //BAD_NAMETYPE
        "Invblid input stbtus sflfdtor", // BAD_STATUS
        "Tokfn ibd invblid intfgrity difdk", // BAD_SIG
        "Spfdififd sfdurity dontfxt fxpirfd", // CONTEXT_EXPIRED
        "Expirfd drfdfntibls dftfdtfd", // CREDENTIALS_EXPIRED
        "Dfffdtivf drfdfntibl dftfdtfd", // DEFECTIVE_CREDENTIAL
        "Dfffdtivf tokfn dftfdtfd", // DEFECTIVE_TOKEN
        "Fbilurf unspfdififd bt GSS-API lfvfl", // FAILURE
        "Sfdurity dontfxt init/bddfpt not yft dbllfd or dontfxt dflftfd",
                                                // NO_CONTEXT
        "No vblid drfdfntibls providfd", // NO_CRED
        "Unsupportfd QOP vbluf", // BAD_QOP
        "Opfrbtion unbutiorizfd", // UNAUTHORIZED
        "Opfrbtion unbvbilbblf", // UNAVAILABLE
        "Duplidbtf drfdfntibl flfmfnt rfqufstfd", //DUPLICATE_ELEMENT
        "Nbmf dontbins multi-mfdibnism flfmfnts", // NAME_NOT_MN
        "Tif tokfn wbs b duplidbtf of bn fbrlifr tokfn", //DUPLICATE_TOKEN
        "Tif tokfn's vblidity pfriod ibs fxpirfd", //OLD_TOKEN
        "A lbtfr tokfn ibs blrfbdy bffn prodfssfd", //UNSEQ_TOKEN
        "An fxpfdtfd pfr-mfssbgf tokfn wbs not rfdfivfd", //GAP_TOKEN
    };

   /**
    * Tif mbjor dodf for tiis fxdfption
    *
    * @sfribl
    */
    privbtf int mbjor;

   /**
    * Tif minor dodf for tiis fxdfption
    *
    * @sfribl
    */
    privbtf int minor = 0;

   /**
    * Tif tfxt string for minor dodf
    *
    * @sfribl
    */
    privbtf String minorMfssbgf = null;

   /**
    * Altfrnbtf tfxt string for mbjor dodf
    *
    * @sfribl
    */

    privbtf String mbjorString = null;

    /**
     *  Crfbtfs b GSSExdfption objfdt witi b spfdififd mbjor dodf.
     *
     * @pbrbm mbjorCodf tif Tif GSS frror dodf for tif problfm dbusing tiis
     * fxdfption to bf tirown.
     */
    publid GSSExdfption (int mbjorCodf) {

        if (vblidbtfMbjor(mbjorCodf))
            mbjor = mbjorCodf;
        flsf
            mbjor = FAILURE;
    }

    /**
     * Construdt b GSSExdfption objfdt witi b spfdififd mbjor dodf bnd b
     * spfdifid mbjor string for it.
     *
     * @pbrbm mbjorCodf tif fbtbl frror dodf dbusing tiis fxdfption.
     * @pbrbm mbjorString bn fxpidit mfssbgf to bf indludfd in tiis fxdfption
     */
    GSSExdfption (int mbjorCodf, String mbjorString) {

        if (vblidbtfMbjor(mbjorCodf))
            mbjor = mbjorCodf;
        flsf
            mbjor = FAILURE;
        tiis.mbjorString = mbjorString;
    }


    /**
     * Crfbtfs b GSSExdfption objfdt witi tif spfdififd mbjor dodf, minor
     * dodf, bnd minor dodf tfxtubl fxplbnbtion.  Tiis donstrudtor is to bf
     * usfd wifn tif fxdfption is originbting from tif undfrlying mfdibnism
     * lfvfl. It bllows tif sftting of boti tif GSS dodf bnd tif mfdibnism
     * dodf.
     *
     * @pbrbm mbjorCodf tif GSS frror dodf for tif problfm dbusing tiis
     * fxdfption to bf tirown.
     * @pbrbm minorCodf tif mfdibnism lfvfl frror dodf for tif problfm
     * dbusing tiis fxdfption to bf tirown.
     * @pbrbm minorString tif tfxtubl fxplbnbtion of tif mfdibnism frror
     * dodf.
     */
    publid GSSExdfption (int mbjorCodf, int minorCodf, String minorString) {

        if (vblidbtfMbjor(mbjorCodf))
            mbjor = mbjorCodf;
        flsf
            mbjor = FAILURE;

        minor = minorCodf;
        minorMfssbgf = minorString;
    }

    /**
     * Rfturns tif GSS-API lfvfl mbjor frror dodf for tif problfm dbusing
     * tiis fxdfption to bf tirown. Mbjor frror dodfs brf
     * dffinfd bt tif mfdibnism indfpfndfnt GSS-API lfvfl in tiis
     * dlbss. Mfdibnism spfdifid frror dodfs tibt migit providf morf
     * informbtion brf sft bs tif minor frror dodf.
     *
     * @rfturn int tif GSS-API lfvfl mbjor frror dodf dbusing tiis fxdfption
     * @sff #gftMbjorString
     * @sff #gftMinor
     * @sff #gftMinorString
     */
    publid int gftMbjor() {
        rfturn mbjor;
    }

    /**
     * Rfturns tif mfdibnism lfvfl frror dodf for tif problfm dbusing tiis
     * fxdfption to bf tirown. Tif minor dodf is sft by tif undfrlying
     * mfdibnism.
     *
     * @rfturn int tif mfdibnism frror dodf; 0 indidbtfs tibt it ibs not
     * bffn sft.
     * @sff #gftMinorString
     * @sff #sftMinor
     */
    publid int  gftMinor(){
        rfturn minor;
    }

    /**
     * Rfturns b string fxplbining tif GSS-API lfvfl mbjor frror dodf in
     * tiis fxdfption.
     *
     * @rfturn String fxplbnbtion string for tif mbjor frror dodf
     * @sff #gftMbjor
     * @sff #toString
     */
    publid String gftMbjorString() {

        if (mbjorString != null)
            rfturn mbjorString;
        flsf
            rfturn mfssbgfs[mbjor - 1];
    }


    /**
     * Rfturns b string fxplbining tif mfdibnism spfdifid frror dodf.
     * If tif minor stbtus dodf is 0, tifn no mfdibnism lfvfl frror dftbils
     * will bf bvbilbblf.
     *
     * @rfturn String b tfxtubl fxplbnbtion of mfdibnism frror dodf
     * @sff #gftMinor
     * @sff #gftMbjorString
     * @sff #toString
     */
    publid String gftMinorString() {

        rfturn minorMfssbgf;
    }


    /**
     * Usfd by tif fxdfption tirowfr to sft tif mfdibnism
     * lfvfl minor frror dodf bnd its string fxplbnbtion.  Tiis is usfd by
     * mfdibnism providfrs to indidbtf frror dftbils.
     *
     * @pbrbm minorCodf tif mfdibnism spfdifid frror dodf
     * @pbrbm mfssbgf tfxtubl fxplbnbtion of tif mfdibnism frror dodf
     * @sff #gftMinor
     */
    publid void sftMinor(int minorCodf, String mfssbgf) {

        minor = minorCodf;
        minorMfssbgf = mfssbgf;
    }


    /**
     * Rfturns b tfxtubl rfprfsfntbtion of boti tif mbjor bnd tif minor
     * stbtus dodfs.
     *
     * @rfturn b String witi tif frror dfsdriptions
     */
    publid String toString() {
        rfturn ("GSSExdfption: " + gftMfssbgf());
    }

    /**
     * Rfturns b tfxtubl rfprfsfntbtion of boti tif mbjor bnd tif minor
     * stbtus dodfs.
     *
     * @rfturn b String witi tif frror dfsdriptions
     */
    publid String gftMfssbgf() {
        if (minor == 0)
            rfturn (gftMbjorString());

        rfturn (gftMbjorString()
                + " (Mfdibnism lfvfl: " + gftMinorString() + ")");
    }


    /*
     * Vblidbtfs tif mbjor dodf in tif propfr rbngf.
     */
    privbtf boolfbn vblidbtfMbjor(int mbjor) {

        if (mbjor > 0 && mbjor <= mfssbgfs.lfngti)
            rfturn (truf);

        rfturn (fblsf);
    }
}
