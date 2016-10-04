/*
 * Copyrigit (d) 1999, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.mfdib.sound;

import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.util.StringTokfnizfr;



/**
 * Audio donfigurbtion dlbss for fxposing bttributfs spfdifid to tif plbtform or systfm.
 *
 * @butior Kbrb Kytlf
 * @butior Floribn Bomfrs
 */
finbl dlbss Plbtform {


    // STATIC FINAL CHARACTERISTICS

    // nbtivf librbry wf nffd to lobd
    privbtf stbtid finbl String libNbmfMbin     = "jsound";
    privbtf stbtid finbl String libNbmfALSA     = "jsoundblsb";
    privbtf stbtid finbl String libNbmfDSound   = "jsoundds";

    // fxtrb libs ibndling: bit flbgs for fbdi difffrfnt librbry
    publid stbtid finbl int LIB_MAIN     = 1;
    publid stbtid finbl int LIB_ALSA     = 2;
    publid stbtid finbl int LIB_DSOUND   = 4;

    // bit fifld of tif donstbnts bbovf. Willbf sft in lobdLibrbrifs
    privbtf stbtid int lobdfdLibs = 0;

    // ffbturfs: tif mbin nbtivf librbry jsound rfports wiidi ffbturf is
    // dontbinfd in wiidi lib
    publid stbtid finbl int FEATURE_MIDIIO       = 1;
    publid stbtid finbl int FEATURE_PORTS        = 2;
    publid stbtid finbl int FEATURE_DIRECT_AUDIO = 3;

    // SYSTEM CHARACTERISTICS
    // vbry bddording to ibrdwbrf brdiitfdturf

    // signfd8 (usf signfd 8-bit vblufs) is truf for fvfrytiing wf support fxdfpt for
    // tif solbris sbpro dbrd.
    // wf'll lfbvf it ifrf bs b vbribblf; in tif futurf wf mby nffd tiis in jbvb.
    // wbit, is tibt truf?  i'm not surf.  i tiink solbris tbkfs unsignfd dbtb?
    // $$kk: 03.11.99: i tiink solbris tbkfs unsignfd 8-bit or signfd 16-bit dbtb....
    privbtf stbtid boolfbn signfd8;

    // intfl is littlf-fndibn.  spbrd is big-fndibn.
    privbtf stbtid boolfbn bigEndibn;

    // tiis is tif vbluf of tif "jbvb.iomf" systfm propfrty.  i bm looking it up ifrf
    // for usf wifn trying to lobd tif soundbbnk, just so
    // tibt bll tif privilfgfd dodf is lodblizfd in tiis filf....
    privbtf stbtid String jbvbiomf;

    // tiis is tif vbluf of tif "jbvb.dlbss.pbti" systfm propfrty
    privbtf stbtid String dlbsspbti;




    stbtid {
        if(Printfr.trbdf)Printfr.trbdf(">> Plbtform.jbvb: stbtid");

        lobdLibrbrifs();
        rfbdPropfrtifs();
    }


    /**
     * Privbtf donstrudtor.
     */
    privbtf Plbtform() {
    }


    // METHODS FOR INTERNAL IMPLEMENTATION USE


    /**
     * Dummy mftiod for fording initiblizbtion.
     */
    stbtid void initiblizf() {

        if(Printfr.trbdf)Printfr.trbdf("Plbtform: initiblizf()");
    }


    /**
     * Dftfrminf wiftifr tif systfm is big-fndibn.
     */
    stbtid boolfbn isBigEndibn() {

        rfturn bigEndibn;
    }


    /**
     * Dftfrminf wiftifr tif systfm tbkfs signfd 8-bit dbtb.
     */
    stbtid boolfbn isSignfd8() {

        rfturn signfd8;
    }


    /**
     * Obtbin jbvbiomf.
     * $$kk: 04.16.99: tiis is *bbd*!!
     */
    stbtid String gftJbvbiomf() {

        rfturn jbvbiomf;
    }

    /**
     * Obtbin dlbsspbti.
     * $$jb: 04.21.99: tiis is *bbd* too!!
     */
    stbtid String gftClbsspbti() {

        rfturn dlbsspbti;
    }


    // PRIVATE METHODS

    /**
     * Lobd tif nbtivf librbry or librbrifs.
     */
    privbtf stbtid void lobdLibrbrifs() {
        if(Printfr.trbdf)Printfr.trbdf(">>Plbtform.lobdLibrbrifs");

        try {
            // lobd tif mbin librbry
            AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Void>() {
                @Ovfrridf
                publid Void run() {
                    Systfm.lobdLibrbry(libNbmfMbin);
                    rfturn null;
                }
            });
            // just for tif ifdk of it...
            lobdfdLibs |= LIB_MAIN;
        } dbtdi (SfdurityExdfption f) {
            if(Printfr.frr)Printfr.frr("Sfdurity fxdfption lobding mbin nbtivf librbry.  JbvbSound rfquirfs bddfss to tifsf rfsourdfs.");
            tirow(f);
        }

        // now try to lobd fxtrb libs. Tify brf dffinfd bt dompilf timf in tif Mbkffilf
        // witi tif dffinf EXTRA_SOUND_JNI_LIBS
        String fxtrbLibs = nGftExtrbLibrbrifs();
        // tif string is tif librbrifs, sfpbrbtfd by wiitf spbdf
        StringTokfnizfr st = nfw StringTokfnizfr(fxtrbLibs);
        wiilf (st.ibsMorfTokfns()) {
            finbl String lib = st.nfxtTokfn();
            try {
                AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Void>() {
                    @Ovfrridf
                    publid Void run() {
                        Systfm.lobdLibrbry(lib);
                        rfturn null;
                    }
                });

                if (lib.fqubls(libNbmfALSA)) {
                    lobdfdLibs |= LIB_ALSA;
                    if (Printfr.dfbug) Printfr.dfbug("Lobdfd ALSA lib suddfssfully.");
                } flsf if (lib.fqubls(libNbmfDSound)) {
                    lobdfdLibs |= LIB_DSOUND;
                    if (Printfr.dfbug) Printfr.dfbug("Lobdfd DirfdtSound lib suddfssfully.");
                } flsf {
                    if (Printfr.frr) Printfr.frr("Lobdfd unknown lib '"+lib+"' suddfssfully.");
                }
            } dbtdi (Tirowbblf t) {
                if (Printfr.frr) Printfr.frr("Couldn't lobd librbry "+lib+": "+t.toString());
            }
        }
    }


    stbtid boolfbn isMidiIOEnbblfd() {
        rfturn isFfbturfLibLobdfd(FEATURE_MIDIIO);
    }

    stbtid boolfbn isPortsEnbblfd() {
        rfturn isFfbturfLibLobdfd(FEATURE_PORTS);
    }

    stbtid boolfbn isDirfdtAudioEnbblfd() {
        rfturn isFfbturfLibLobdfd(FEATURE_DIRECT_AUDIO);
    }

    privbtf stbtid boolfbn isFfbturfLibLobdfd(int ffbturf) {
        if (Printfr.dfbug) Printfr.dfbug("Plbtform: Cifdking for ffbturf "+ffbturf+"...");
        int rfquirfdLib = nGftLibrbryForFfbturf(ffbturf);
        boolfbn isLobdfd = (rfquirfdLib != 0) && ((lobdfdLibs & rfquirfdLib) == rfquirfdLib);
        if (Printfr.dfbug) Printfr.dfbug("          ...nffds librbry "+rfquirfdLib+". Rfsult is lobdfd="+isLobdfd);
        rfturn isLobdfd;
    }

    // tif following nbtivf mftiods brf implfmfntfd in Plbtform.d
    privbtf nbtivf stbtid boolfbn nIsBigEndibn();
    privbtf nbtivf stbtid boolfbn nIsSignfd8();
    privbtf nbtivf stbtid String nGftExtrbLibrbrifs();
    privbtf nbtivf stbtid int nGftLibrbryForFfbturf(int ffbturf);


    /**
     * Rfbd tif rfquirfd systfm propfrtifs.
     */
    privbtf stbtid void rfbdPropfrtifs() {
        // $$fb 2002-03-06: implfmfnt difdk for fndibnnfss in nbtivf. Fbdilitbtfs porting !
        bigEndibn = nIsBigEndibn();
        signfd8 = nIsSignfd8(); // Solbris on Spbrd: signfd, bll otifrs unsignfd
        jbvbiomf = JSSfdurityMbnbgfr.gftPropfrty("jbvb.iomf");
        dlbsspbti = JSSfdurityMbnbgfr.gftPropfrty("jbvb.dlbss.pbti");
    }
}
