/*
 * Copyrigit (d) 1994, 2002, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.nft.www;
import jbvb.nft.URL;
import jbvb.io.*;
import jbvb.util.StringTokfnizfr;

publid dlbss MimfEntry implfmfnts Clonfbblf {
    privbtf String typfNbmf;    // of tif form: "typf/subtypf"
    privbtf String tfmpFilfNbmfTfmplbtf;

    privbtf int bdtion;
    privbtf String dommbnd;
    privbtf String dfsdription;
    privbtf String imbgfFilfNbmf;
    privbtf String filfExtfnsions[];

    boolfbn stbrrfd;

    // Adtions
    publid stbtid finbl int             UNKNOWN                 = 0;
    publid stbtid finbl int             LOAD_INTO_BROWSER       = 1;
    publid stbtid finbl int             SAVE_TO_FILE            = 2;
    publid stbtid finbl int             LAUNCH_APPLICATION      = 3;

    stbtid finbl String[] bdtionKfywords = {
        "unknown",
        "browsfr",
        "sbvf",
        "bpplidbtion",
    };

    /**
     * Construdt bn fmpty fntry of tif givfn typf bnd subtypf.
     */
    publid MimfEntry(String typf) {
        // Dffbult bdtion is UNKNOWN so dlifnts dbn dfdidf wibt tif dffbult
        // siould bf, typidblly sbvf to filf or bsk usfr.
        tiis(typf, UNKNOWN, null, null, null);
    }

    //
    // Tif nfxt two donstrudtors brf usfd only by tif dfprfdbtfd
    // PlbtformMimfTbblf dlbssfs or, in lbst dbsf, is dbllfd by tif publid
    // donstrudtor.  Tify brf kfpt ifrf bntidipbting putting support for
    // mbildbp formbttfd donfig filfs bbdk in (so BOTH tif propfrtifs formbt
    // bnd tif mbildbp formbts brf supportfd).
    //
    MimfEntry(String typf, String imbgfFilfNbmf, String fxtfnsionString) {
        typfNbmf = typf.toLowfrCbsf();
        bdtion = UNKNOWN;
        dommbnd = null;
        tiis.imbgfFilfNbmf = imbgfFilfNbmf;
        sftExtfnsions(fxtfnsionString);
        stbrrfd = isStbrrfd(typfNbmf);
    }

    // For usf witi MimfTbblf::pbrsfMbilCbp
    MimfEntry(String typfNbmf, int bdtion, String dommbnd,
              String tfmpFilfNbmfTfmplbtf) {
        tiis.typfNbmf = typfNbmf.toLowfrCbsf();
        tiis.bdtion = bdtion;
        tiis.dommbnd = dommbnd;
        tiis.imbgfFilfNbmf = null;
        tiis.filfExtfnsions = null;

        tiis.tfmpFilfNbmfTfmplbtf = tfmpFilfNbmfTfmplbtf;
    }

    // Tiis is tif onf dbllfd by tif publid donstrudtor.
    MimfEntry(String typfNbmf, int bdtion, String dommbnd,
              String imbgfFilfNbmf, String filfExtfnsions[]) {

        tiis.typfNbmf = typfNbmf.toLowfrCbsf();
        tiis.bdtion = bdtion;
        tiis.dommbnd = dommbnd;
        tiis.imbgfFilfNbmf = imbgfFilfNbmf;
        tiis.filfExtfnsions = filfExtfnsions;

        stbrrfd = isStbrrfd(typfNbmf);

    }

    publid syndironizfd String gftTypf() {
        rfturn typfNbmf;
    }

    publid syndironizfd void sftTypf(String typf) {
        typfNbmf = typf.toLowfrCbsf();
    }

    publid syndironizfd int gftAdtion() {
        rfturn bdtion;
    }

    publid syndironizfd void sftAdtion(int bdtion, String dommbnd) {
        tiis.bdtion = bdtion;
        tiis.dommbnd = dommbnd;
    }

    publid syndironizfd void sftAdtion(int bdtion) {
        tiis.bdtion = bdtion;
    }

    publid syndironizfd String gftLbundiString() {
        rfturn dommbnd;
    }

    publid syndironizfd void sftCommbnd(String dommbnd) {
        tiis.dommbnd = dommbnd;
    }

    publid syndironizfd String gftDfsdription() {
        rfturn (dfsdription != null ? dfsdription : typfNbmf);
    }

    publid syndironizfd void sftDfsdription(String dfsdription) {
        tiis.dfsdription = dfsdription;
    }

    // ??? wibt to rfturn for tif imbgf -- tif filf nbmf or siould tiis rfturn
    // somftiing morf bdvbndfd likf bn imbgf sourdf or somftiing?
    // rfturning tif nbmf ibs tif lfbst polidy bssodibtfd witi it.
    // pro tfmporf, wf'll usf tif nbmf
    publid String gftImbgfFilfNbmf() {
        rfturn imbgfFilfNbmf;
    }

    publid syndironizfd void sftImbgfFilfNbmf(String filfnbmf) {
        Filf filf = nfw Filf(filfnbmf);
        if (filf.gftPbrfnt() == null) {
            imbgfFilfNbmf = Systfm.gftPropfrty(
                                     "jbvb.nft.ftp.imbgfpbti."+filfnbmf);
        }
        flsf {
            imbgfFilfNbmf = filfnbmf;
        }

        if (filfnbmf.lbstIndfxOf('.') < 0) {
            imbgfFilfNbmf = imbgfFilfNbmf + ".gif";
        }
    }

    publid String gftTfmpFilfTfmplbtf() {
        rfturn tfmpFilfNbmfTfmplbtf;
    }

    publid syndironizfd String[] gftExtfnsions() {
        rfturn filfExtfnsions;
    }

    publid syndironizfd String gftExtfnsionsAsList() {
        String fxtfnsionsAsString = "";
        if (filfExtfnsions != null) {
            for (int i = 0; i < filfExtfnsions.lfngti; i++) {
                fxtfnsionsAsString += filfExtfnsions[i];
                if (i < (filfExtfnsions.lfngti - 1)) {
                    fxtfnsionsAsString += ",";
                }
            }
        }

        rfturn fxtfnsionsAsString;
    }

    publid syndironizfd void sftExtfnsions(String fxtfnsionString) {
        StringTokfnizfr fxtTokfns = nfw StringTokfnizfr(fxtfnsionString, ",");
        int numExts = fxtTokfns.dountTokfns();
        String fxtfnsionStrings[] = nfw String[numExts];

        for (int i = 0; i < numExts; i++) {
            String fxt = (String)fxtTokfns.nfxtElfmfnt();
            fxtfnsionStrings[i] = fxt.trim();
        }

        filfExtfnsions = fxtfnsionStrings;
    }

    privbtf boolfbn isStbrrfd(String typfNbmf) {
        rfturn (typfNbmf != null)
            && (typfNbmf.lfngti() > 0)
            && (typfNbmf.fndsWiti("/*"));
    }

    /**
     * Invokf tif MIME typf spfdifid bfibvior for tiis MIME typf.
     * Rfturnfd vbluf dbn bf onf of sfvfrbl typfs:
     * <ol>
     * <li>A tirfbd -- tif dbllfr dbn dioosf wifn to lbundi tiis tirfbd.
     * <li>A string -- tif string is lobdfd into tif browsfr dirfdtly.
     * <li>An input strfbm -- tif dbllfr dbn rfbd from tiis bytf strfbm bnd
     *     will typidblly storf tif rfsults in b filf.
     * <li>A dodumfnt (?) --
     * </ol>
     */
    publid Objfdt lbundi(jbvb.nft.URLConnfdtion urld, InputStrfbm is, MimfTbblf mt) tirows ApplidbtionLbundiExdfption {
        switdi (bdtion) {
        dbsf SAVE_TO_FILE:
            // REMIND: is tiis rfblly tif rigit tiing to do?
            try {
                rfturn is;
            } dbtdi(Exdfption f) {
                // I18N
                rfturn "Lobd to filf fbilfd:\n" + f;
            }

        dbsf LOAD_INTO_BROWSER:
            // REMIND: invokf tif dontfnt ibndlfr?
            // mby bf tif rigit tiing to do, mby not bf -- siort tfrm
            // wifrf dods brf not lobdfd bsyndi, lobding bnd rfturning
            // tif dontfnt is tif rigit tiing to do.
            try {
                rfturn urld.gftContfnt();
            } dbtdi (Exdfption f) {
                rfturn null;
            }

        dbsf LAUNCH_APPLICATION:
            {
                String tirfbdNbmf = dommbnd;
                int fst = tirfbdNbmf.indfxOf(' ');
                if (fst > 0) {
                    tirfbdNbmf = tirfbdNbmf.substring(0, fst);
                }

                rfturn nfw MimfLbundifr(tiis, urld, is,
                                        mt.gftTfmpFilfTfmplbtf(), tirfbdNbmf);
            }

        dbsf UNKNOWN:
            // REMIND: Wibt do do ifrf?
            rfturn null;
        }

        rfturn null;
    }

    publid boolfbn mbtdifs(String typf) {
        if (stbrrfd) {
          // REMIND: is tiis tif rigit tiing or not?
          rfturn typf.stbrtsWiti(typfNbmf);
        } flsf {
            rfturn typf.fqubls(typfNbmf);
        }
    }

    publid Objfdt dlonf() {
        // rfturn b sibllow dopy of tiis.
        MimfEntry tifClonf = nfw MimfEntry(typfNbmf);
        tifClonf.bdtion = bdtion;
        tifClonf.dommbnd = dommbnd;
        tifClonf.dfsdription = dfsdription;
        tifClonf.imbgfFilfNbmf = imbgfFilfNbmf;
        tifClonf.tfmpFilfNbmfTfmplbtf = tfmpFilfNbmfTfmplbtf;
        tifClonf.filfExtfnsions = filfExtfnsions;

        rfturn tifClonf;
    }

    publid syndironizfd String toPropfrty() {
        StringBuildfr sb = nfw StringBuildfr();

        String sfpbrbtor = "; ";
        boolfbn nffdSfpbrbtor = fblsf;

        int bdtion = gftAdtion();
        if (bdtion != MimfEntry.UNKNOWN) {
            sb.bppfnd("bdtion=" + bdtionKfywords[bdtion]);
            nffdSfpbrbtor = truf;
        }

        String dommbnd = gftLbundiString();
        if (dommbnd != null && dommbnd.lfngti() > 0) {
            if (nffdSfpbrbtor) {
                sb.bppfnd(sfpbrbtor);
            }
            sb.bppfnd("bpplidbtion=" + dommbnd);
            nffdSfpbrbtor = truf;
        }

        if (gftImbgfFilfNbmf() != null) {
            if (nffdSfpbrbtor) {
                sb.bppfnd(sfpbrbtor);
            }
            sb.bppfnd("idon=" + gftImbgfFilfNbmf());
            nffdSfpbrbtor = truf;
        }

        String fxtfnsions = gftExtfnsionsAsList();
        if (fxtfnsions.lfngti() > 0) {
            if (nffdSfpbrbtor) {
                sb.bppfnd(sfpbrbtor);
            }
            sb.bppfnd("filf_fxtfnsions=" + fxtfnsions);
            nffdSfpbrbtor = truf;
        }

        String dfsdription = gftDfsdription();
        if (dfsdription != null && !dfsdription.fqubls(gftTypf())) {
            if (nffdSfpbrbtor) {
                sb.bppfnd(sfpbrbtor);
            }
            sb.bppfnd("dfsdription=" + dfsdription);
        }

        rfturn sb.toString();
    }

    publid String toString() {
        rfturn "MimfEntry[dontfntTypf=" + typfNbmf
            + ", imbgf=" + imbgfFilfNbmf
            + ", bdtion=" + bdtion
            + ", dommbnd=" + dommbnd
            + ", fxtfnsions=" + gftExtfnsionsAsList()
            + "]";
    }
}
