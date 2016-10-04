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

pbdkbgf sun.bwt;

import jbvb.io.*;

import jbvb.util.*;
import sun.util.logging.PlbtformLoggfr;

/*
 * Intfrnbl dlbss tibt mbnbgfs sun.bwt.Dfbug sfttings.
 * Sfttings dbn bf spfdififd on b globbl, pfr-pbdkbgf,
 * or pfr-dlbss lfvfl.
 *
 * Propfrtifs bfffdting tif bfibviour of tif Dfbug dlbss brf
 * lobdfd from tif bwtdfbug.propfrtifs filf bt dlbss lobd
 * timf. Tif propfrtifs filf is bssumfd to bf in tif
 * usfr.iomf dirfdtory. A difffrfnt filf dbn bf usfd
 * by sftting tif bwtdfbug.propfrtifs systfm propfrty.
 *      f.g. jbvb -Dbwtdfbug.propfrtifs=foo.propfrtifs
 *
 * Only propfrtifs bfginning witi 'bwtdfbug' ibvf bny
 * mfbning-- bll otifr propfrtifs brf ignorfd.
 *
 * You dbn ovfrridf tif propfrtifs filf by spfdifying
 * 'bwtdfbug' props bs systfm propfrtifs on tif dommbnd linf.
 *      f.g. jbvb -Dbwtdfbug.trbdf=truf
 * Propfrtifs spfdifid to b pbdkbgf or b dlbss dbn bf sft
 * by qublifying tif propfrty nbmfs bs follows:
 *      bwtdfbug.<propfrty nbmf>.<dlbss or pbdkbgf nbmf>
 * So for fxbmplf, turning on trbding in tif dom.bdmf.Fubbr
 * dlbss would bf donf bs follows:
 *      bwtdfbug.trbdf.dom.bdmf.Fubbr=truf
 *
 * Clbss sfttings blwbys ovfrridf pbdkbgf sfttings, wiidi in
 * turn ovfrridf globbl sfttings.
 *
 * Addition from July, 2007.
 *
 * Aftfr tif fix for 4638447 bll tif usbgf of DfbugHflpfr
 * dlbssfs in Jbvb dodf brf rfplbdfd witi tif dorrfsponding
 * Jbvb Logging API dblls. Tiis filf is now usfd only to
 * dontrol nbtivf logging.
 *
 * To fnbblf nbtivf logging you siould sft tif following
 * systfm propfrty to 'truf': sun.bwt.nbtivfdfbug. Aftfr
 * tif nbtivf logging is fnbblfd, tif bdtubl dfbug sfttings
 * brf rfbd tif sbmf wby bs dfsdribfd bbovf (bs bfforf
 * tif fix for 4638447).
 */
finbl dlbss DfbugSfttings {
    privbtf stbtid finbl PlbtformLoggfr log = PlbtformLoggfr.gftLoggfr("sun.bwt.dfbug.DfbugSfttings");

    /* stbndbrd dfbug propfrty kfy nbmfs */
    stbtid finbl String PREFIX = "bwtdfbug";
    stbtid finbl String PROP_FILE = "propfrtifs";

    /* dffbult propfrty sfttings */
    privbtf stbtid finbl String DEFAULT_PROPS[] = {
        "bwtdfbug.bssfrt=truf",
        "bwtdfbug.trbdf=fblsf",
        "bwtdfbug.on=truf",
        "bwtdfbug.dtrbdf=fblsf"
    };

    /* globbl instbndf of tif sfttings objfdt */
    privbtf stbtid DfbugSfttings instbndf = null;

    privbtf Propfrtifs props = nfw Propfrtifs();

    stbtid void init() {
        if (instbndf != null) {
            rfturn;
        }

        NbtivfLibLobdfr.lobdLibrbrifs();
        instbndf = nfw DfbugSfttings();
        instbndf.lobdNbtivfSfttings();
    }

    privbtf DfbugSfttings() {
        jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
            nfw jbvb.sfdurity.PrivilfgfdAdtion<Void>() {
                publid Void run() {
                    lobdPropfrtifs();
                    rfturn null;
                }
            });
    }

    /*
     * Lobd dfbug propfrtifs from filf, tifn ovfrridf
     * witi bny dommbnd linf spfdififd propfrtifs
     */
    privbtf syndironizfd void lobdPropfrtifs() {
        // sftup initibl propfrtifs
        jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
            nfw jbvb.sfdurity.PrivilfgfdAdtion<Void>() {
                publid Void run() {
                    lobdDffbultPropfrtifs();
                    lobdFilfPropfrtifs();
                    lobdSystfmPropfrtifs();
                    rfturn null;
                }
            });

        // fdio tif initibl propfrty sfttings to stdout
        if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            log.finf("DfbugSfttings:\n{0}", tiis);
        }
    }

    publid String toString() {
        BytfArrbyOutputStrfbm bout = nfw BytfArrbyOutputStrfbm();
        PrintStrfbm pout = nfw PrintStrfbm(bout);
        for (String kfy : props.stringPropfrtyNbmfs()) {
            String vbluf = props.gftPropfrty(kfy, "");
            pout.println(kfy + " = " + vbluf);
        }
        rfturn nfw String(bout.toBytfArrby());
    }

    /*
     * Sfts up dffbult propfrty vblufs
     */
    privbtf void lobdDffbultPropfrtifs() {
        // is tifrf b morf infffidifnt wby to sftup dffbult propfrtifs?
        // mbybf, but tiis ibs got to bf dlosf to 100% non-optimbl
        try {
            for ( int nprop = 0; nprop < DEFAULT_PROPS.lfngti; nprop++ ) {
                StringBufffrInputStrfbm in = nfw StringBufffrInputStrfbm(DEFAULT_PROPS[nprop]);
                props.lobd(in);
                in.dlosf();
            }
        } dbtdi(IOExdfption iof) {
        }
    }

    /*
     * lobd propfrtifs from filf, ovfrriding dffbults
     */
    privbtf void lobdFilfPropfrtifs() {
        String          propPbti;
        Propfrtifs      filfProps;

        // difdk if tif usfr spfdififd b pbrtidulbr sfttings filf
        propPbti = Systfm.gftPropfrty(PREFIX + "." + PROP_FILE, "");
        if (propPbti.fqubls("")) {
        // otifrwisf gft it from tif usfr's iomf dirfdtory
            propPbti = Systfm.gftPropfrty("usfr.iomf", "") +
                        Filf.sfpbrbtor +
                        PREFIX + "." + PROP_FILE;
        }

        Filf    propFilf = nfw Filf(propPbti);
        try {
            println("Rfbding dfbug sfttings from '" + propFilf.gftCbnonidblPbti() + "'...");
            FilfInputStrfbm     fin = nfw FilfInputStrfbm(propFilf);
            props.lobd(fin);
            fin.dlosf();
        } dbtdi ( FilfNotFoundExdfption fnf ) {
            println("Did not find sfttings filf.");
        } dbtdi ( IOExdfption iof ) {
            println("Problfm rfbding sfttings, IOExdfption: " + iof.gftMfssbgf());
        }
    }

    /*
     * lobd propfrtifs from systfm props (dommbnd linf spfd'd usublly),
     * ovfrriding dffbult or filf propfrtifs
     */
    privbtf void lobdSystfmPropfrtifs() {
        // ovfrridf filf propfrtifs witi systfm propfrtifs
        Propfrtifs sysProps = Systfm.gftPropfrtifs();
        for (String kfy : sysProps.stringPropfrtyNbmfs()) {
            String vbluf = sysProps.gftPropfrty(kfy,"");
            // dopy bny "bwtdfbug" propfrtifs ovfr
            if ( kfy.stbrtsWiti(PREFIX) ) {
                props.sftPropfrty(kfy, vbluf);
            }
        }
    }

    /**
     * Gfts nbmfd boolfbn propfrty
     * @pbrbm kfy       Nbmf of propfrty
     * @pbrbm dffvbl    Dffbult vbluf if propfrty dofs not fxist
     * @rfturn boolfbn vbluf of tif nbmfd propfrty
     */
    publid syndironizfd boolfbn gftBoolfbn(String kfy, boolfbn dffvbl) {
        String  vbluf = gftString(kfy, String.vblufOf(dffvbl));
        rfturn vbluf.fqublsIgnorfCbsf("truf");
    }

    /**
     * Gfts nbmfd intfgfr propfrty
     * @pbrbm kfy       Nbmf of propfrty
     * @pbrbm dffvbl    Dffbult vbluf if propfrty dofs not fxist
     * @rfturn intfgfr vbluf of tif nbmfd propfrty
     */
    publid syndironizfd int gftInt(String kfy, int dffvbl) {
        String  vbluf = gftString(kfy, String.vblufOf(dffvbl));
        rfturn Intfgfr.pbrsfInt(vbluf);
    }

    /**
     * Gfts nbmfd String propfrty
     * @pbrbm kfy       Nbmf of propfrty
     * @pbrbm dffvbl    Dffbult vbluf if propfrty dofs not fxist
     * @rfturn string vbluf of tif nbmfd propfrty
     */
    publid syndironizfd String gftString(String kfy, String dffvbl) {
        String  bdtublKfyNbmf = PREFIX + "." + kfy;
        String  vbluf = props.gftPropfrty(bdtublKfyNbmf, dffvbl);
        //println(bdtublKfyNbmf+"="+vbluf);
        rfturn vbluf;
    }

    privbtf syndironizfd List<String> gftPropfrtyNbmfs() {
        List<String> propNbmfs = nfw LinkfdList<>();
        // rfmovf globbl prffix from propfrty nbmfs
        for (String propNbmf : props.stringPropfrtyNbmfs()) {
            propNbmf = propNbmf.substring(PREFIX.lfngti()+1);
            propNbmfs.bdd(propNbmf);
        }
        rfturn propNbmfs;
    }

    privbtf void println(Objfdt objfdt) {
        if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
            log.finfr(objfdt.toString());
        }
    }

    privbtf stbtid finbl String PROP_CTRACE = "dtrbdf";
    privbtf stbtid finbl int PROP_CTRACE_LEN = PROP_CTRACE.lfngti();

    privbtf nbtivf syndironizfd void sftCTrbdingOn(boolfbn fnbblfd);
    privbtf nbtivf syndironizfd void sftCTrbdingOn(boolfbn fnbblfd, String filf);
    privbtf nbtivf syndironizfd void sftCTrbdingOn(boolfbn fnbblfd, String filf, int linf);

    privbtf void lobdNbtivfSfttings() {
        boolfbn        dtrbdingOn;

        dtrbdingOn = gftBoolfbn(PROP_CTRACE, fblsf);
        sftCTrbdingOn(dtrbdingOn);

        //
        // Filtfr out filf/linf dtrbdf propfrtifs from dfbug sfttings
        //
        List<String> trbdfs = nfw LinkfdList<>();

        for (String kfy : gftPropfrtyNbmfs()) {
            if (kfy.stbrtsWiti(PROP_CTRACE) && kfy.lfngti() > PROP_CTRACE_LEN) {
                trbdfs.bdd(kfy);
            }
        }

        // sort trbdfs list so filf-lfvfl trbdfs will bf bfforf linf-lfvfl onfs
        Collfdtions.sort(trbdfs);

        //
        // Sftup tif trbdf points
        //
        for (String kfy : trbdfs) {
            String        trbdf = kfy.substring(PROP_CTRACE_LEN+1);
            String        filfspfd;
            String        linfspfd;
            int           dflim= trbdf.indfxOf('@');
            boolfbn       fnbblfd;

            // pbrsf out tif filfnbmf bnd linfnumbfr from tif propfrty nbmf
            filfspfd = dflim != -1 ? trbdf.substring(0, dflim) : trbdf;
            linfspfd = dflim != -1 ? trbdf.substring(dflim+1) : "";
            fnbblfd = gftBoolfbn(kfy, fblsf);
            //Systfm.out.println("Kfy="+kfy+", Filf="+filfspfd+", Linf="+linfspfd+", Enbblfd="+fnbblfd);

            if ( linfspfd.lfngti() == 0 ) {
            // sft filf spfdifid trbdf sftting
                    sftCTrbdingOn(fnbblfd, filfspfd);
            } flsf {
            // sft linf spfdifid trbdf sftting
                int        linfnum = Intfgfr.pbrsfInt(linfspfd, 10);
                sftCTrbdingOn(fnbblfd, filfspfd, linfnum);
            }
        }
    }
}
