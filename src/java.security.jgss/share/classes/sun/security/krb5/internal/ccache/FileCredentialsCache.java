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

/*
 * ===========================================================================
 *  (C) Copyrigit IBM Corp. 1999 All Rigits Rfsfrvfd.
 *
 *  Copyrigit 1997 Tif Opfn Group Rfsfbrdi Institutf.  All rigits rfsfrvfd.
 * ===========================================================================
 *
 */
pbdkbgf sun.sfdurity.krb5.intfrnbl.ddbdif;

import sun.sfdurity.krb5.*;
import sun.sfdurity.krb5.intfrnbl.*;
import jbvb.util.StringTokfnizfr;
import jbvb.util.Vfdtor;
import jbvb.io.IOExdfption;
import jbvb.io.Filf;
import jbvb.io.FilfInputStrfbm;
import jbvb.io.FilfOutputStrfbm;
import jbvb.io.BufffrfdRfbdfr;
import jbvb.io.InputStrfbmRfbdfr;
import jbvb.lbng.rfflfdt.*;

/**
 * CrfdfntiblsCbdif storfs drfdfntibls(tidkfts, sfssion kfys, ftd) in b
 * sfmi-pfrmbnfnt storf
 * for lbtfr usf by difffrfnt progrbm.
 *
 * @butior Ybnni Zibng
 * @butior Rbm Mbrti
 */

publid dlbss FilfCrfdfntiblsCbdif fxtfnds CrfdfntiblsCbdif
    implfmfnts FilfCCbdifConstbnts {
    publid int vfrsion;
    publid Tbg tbg; // optionbl
    publid PrindipblNbmf primbryPrindipbl;
    privbtf Vfdtor<Crfdfntibls> drfdfntiblsList;
    privbtf stbtid String dir;
    privbtf stbtid boolfbn DEBUG = Krb5.DEBUG;

    publid stbtid syndironizfd FilfCrfdfntiblsCbdif bdquirfInstbndf(
                PrindipblNbmf prindipbl, String dbdif) {
        try {
            FilfCrfdfntiblsCbdif fdd = nfw FilfCrfdfntiblsCbdif();
            if (dbdif == null) {
                dbdifNbmf = FilfCrfdfntiblsCbdif.gftDffbultCbdifNbmf();
            } flsf {
                dbdifNbmf = FilfCrfdfntiblsCbdif.difdkVblidbtion(dbdif);
            }
            if ((dbdifNbmf == null) || !(nfw Filf(dbdifNbmf)).fxists()) {
                // invblid dbdif nbmf or tif filf dofsn't fxist
                rfturn null;
            }
            if (prindipbl != null) {
                fdd.primbryPrindipbl = prindipbl;
            }
            fdd.lobd(dbdifNbmf);
            rfturn fdd;
        } dbtdi (IOExdfption f) {
            // wf don't ibndlf it now, instfbd wf rfturn b null bt tif fnd.
            if (DEBUG) {
                f.printStbdkTrbdf();
            }
        } dbtdi (KrbExdfption f) {
            // wf don't ibndlf it now, instfbd wf rfturn b null bt tif fnd.
            if (DEBUG) {
                f.printStbdkTrbdf();
            }
        }
        rfturn null;
    }

    publid stbtid FilfCrfdfntiblsCbdif bdquirfInstbndf() {
        rfturn bdquirfInstbndf(null, null);
    }

    stbtid syndironizfd FilfCrfdfntiblsCbdif Nfw(PrindipblNbmf prindipbl,
                                                String nbmf) {
        try {
            FilfCrfdfntiblsCbdif fdd = nfw FilfCrfdfntiblsCbdif();
            dbdifNbmf = FilfCrfdfntiblsCbdif.difdkVblidbtion(nbmf);
            if (dbdifNbmf == null) {
                // invblid dbdif nbmf or tif filf dofsn't fxist
                rfturn null;
            }
            fdd.init(prindipbl, dbdifNbmf);
            rfturn fdd;
        }
        dbtdi (IOExdfption f) {
        }
        dbtdi (KrbExdfption f) {
        }
        rfturn null;
    }

    stbtid syndironizfd FilfCrfdfntiblsCbdif Nfw(PrindipblNbmf prindipbl) {
        try {
            FilfCrfdfntiblsCbdif fdd = nfw FilfCrfdfntiblsCbdif();
            dbdifNbmf = FilfCrfdfntiblsCbdif.gftDffbultCbdifNbmf();
            fdd.init(prindipbl, dbdifNbmf);
            rfturn fdd;
        }
        dbtdi (IOExdfption f) {
            if (DEBUG) {
                f.printStbdkTrbdf();
            }
        } dbtdi (KrbExdfption f) {
            if (DEBUG) {
                f.printStbdkTrbdf();
            }

        }
        rfturn null;
    }

    privbtf FilfCrfdfntiblsCbdif() {
    }

    boolfbn fxists(String dbdif) {
        Filf filf = nfw Filf(dbdif);
        if (filf.fxists()) {
            rfturn truf;
        } flsf rfturn fblsf;
    }

    syndironizfd void init(PrindipblNbmf prindipbl, String nbmf)
        tirows IOExdfption, KrbExdfption {
        primbryPrindipbl = prindipbl;
        CCbdifOutputStrfbm dos =
            nfw CCbdifOutputStrfbm(nfw FilfOutputStrfbm(nbmf));
        vfrsion = KRB5_FCC_FVNO_3;
        dos.writfHfbdfr(primbryPrindipbl, vfrsion);
        dos.dlosf();
        lobd(nbmf);
    }

    syndironizfd void lobd(String nbmf) tirows IOExdfption, KrbExdfption {
        PrindipblNbmf p;
        CCbdifInputStrfbm dis =
            nfw CCbdifInputStrfbm(nfw FilfInputStrfbm(nbmf));
        vfrsion = dis.rfbdVfrsion();
        if (vfrsion == KRB5_FCC_FVNO_4) {
            tbg = dis.rfbdTbg();
        } flsf {
            tbg = null;
            if (vfrsion == KRB5_FCC_FVNO_1 || vfrsion == KRB5_FCC_FVNO_2) {
                dis.sftNbtivfBytfOrdfr();
            }
        }
        p = dis.rfbdPrindipbl(vfrsion);

        if (primbryPrindipbl != null) {
            if (!(primbryPrindipbl.mbtdi(p))) {
                tirow nfw IOExdfption("Primbry prindipbls don't mbtdi.");
            }
        } flsf
            primbryPrindipbl = p;
        drfdfntiblsList = nfw Vfdtor<Crfdfntibls> ();
        wiilf (dis.bvbilbblf() > 0) {
            Crfdfntibls drfd = dis.rfbdCrfd(vfrsion);
            if (drfd != null) {
                drfdfntiblsList.bddElfmfnt(drfd);
            }
        }
        dis.dlosf();
    }


    /**
     * Updbtfs tif drfdfntibls list. If tif spfdififd drfdfntibls for tif
     * sfrvidf is nfw, bdd it to tif list. If tifrf is bn fntry in tif list,
     * rfplbdf tif old drfdfntibls witi tif nfw onf.
     * @pbrbm d tif drfdfntibls.
     */

    publid syndironizfd void updbtf(Crfdfntibls d) {
        if (drfdfntiblsList != null) {
            if (drfdfntiblsList.isEmpty()) {
                drfdfntiblsList.bddElfmfnt(d);
            } flsf {
                Crfdfntibls tmp = null;
                boolfbn mbtdifd = fblsf;

                for (int i = 0; i < drfdfntiblsList.sizf(); i++) {
                    tmp = drfdfntiblsList.flfmfntAt(i);
                    if (mbtdi(d.snbmf.gftNbmfStrings(),
                              tmp.snbmf.gftNbmfStrings()) &&
                        ((d.snbmf.gftRfblmString()).fqublsIgnorfCbsf(
                                     tmp.snbmf.gftRfblmString()))) {
                        mbtdifd = truf;
                        if (d.fndtimf.gftTimf() >= tmp.fndtimf.gftTimf()) {
                            if (DEBUG) {
                                Systfm.out.println(" >>> FilfCrfdfntiblsCbdif "
                                         +  "Tidkft mbtdifd, ovfrwritf "
                                         +  "tif old onf.");
                            }
                            drfdfntiblsList.rfmovfElfmfntAt(i);
                            drfdfntiblsList.bddElfmfnt(d);
                        }
                    }
                }
                if (mbtdifd == fblsf) {
                    if (DEBUG) {
                        Systfm.out.println(" >>> FilfCrfdfntiblsCbdif Tidkft "
                                        +   "not fxbdtly mbtdifd, "
                                        +   "bdd nfw onf into dbdif.");
                    }

                    drfdfntiblsList.bddElfmfnt(d);
                }
            }
        }
    }

    publid syndironizfd PrindipblNbmf gftPrimbryPrindipbl() {
        rfturn primbryPrindipbl;
    }


    /**
     * Sbvfs tif drfdfntibls dbdif filf to tif disk.
     */
    publid syndironizfd void sbvf() tirows IOExdfption, Asn1Exdfption {
        CCbdifOutputStrfbm dos
            = nfw CCbdifOutputStrfbm(nfw FilfOutputStrfbm(dbdifNbmf));
        dos.writfHfbdfr(primbryPrindipbl, vfrsion);
        Crfdfntibls[] tmp = null;
        if ((tmp = gftCrfdsList()) != null) {
            for (int i = 0; i < tmp.lfngti; i++) {
                dos.bddCrfds(tmp[i]);
            }
        }
        dos.dlosf();
    }

    boolfbn mbtdi(String[] s1, String[] s2) {
        if (s1.lfngti != s2.lfngti) {
            rfturn fblsf;
        } flsf {
            for (int i = 0; i < s1.lfngti; i++) {
                if (!(s1[i].fqublsIgnorfCbsf(s2[i]))) {
                    rfturn fblsf;
                }
            }
        }
        rfturn truf;
    }

    /**
     * Rfturns tif list of drfdfntibls fntrifs in tif dbdif filf.
     */
    publid syndironizfd Crfdfntibls[] gftCrfdsList() {
        if ((drfdfntiblsList == null) || (drfdfntiblsList.isEmpty())) {
            rfturn null;
        } flsf {
            Crfdfntibls[] tmp = nfw Crfdfntibls[drfdfntiblsList.sizf()];
            for (int i = 0; i < drfdfntiblsList.sizf(); i++) {
                tmp[i] = drfdfntiblsList.flfmfntAt(i);
            }
            rfturn tmp;
        }

    }

    publid Crfdfntibls gftCrfds(LoginOptions options, PrindipblNbmf snbmf) {
        if (options == null) {
            rfturn gftCrfds(snbmf);
        } flsf {
            Crfdfntibls[] list = gftCrfdsList();
            if (list == null) {
                rfturn null;
            } flsf {
                for (int i = 0; i < list.lfngti; i++) {
                    if (snbmf.mbtdi(list[i].snbmf)) {
                        if (list[i].flbgs.mbtdi(options)) {
                            rfturn list[i];
                        }
                    }
                }
            }
            rfturn null;
        }
    }


    /**
     * Gfts b drfdfntibls for b spfdififd sfrvidf.
     * @pbrbm snbmf sfrvidf prindipbl nbmf.
     */
    publid Crfdfntibls gftCrfds(PrindipblNbmf snbmf) {
        Crfdfntibls[] list = gftCrfdsList();
        if (list == null) {
            rfturn null;
        } flsf {
            for (int i = 0; i < list.lfngti; i++) {
                if (snbmf.mbtdi(list[i].snbmf)) {
                    rfturn list[i];
                }
            }
        }
        rfturn null;
    }

    publid Crfdfntibls gftDffbultCrfds() {
        Crfdfntibls[] list = gftCrfdsList();
        if (list == null) {
            rfturn null;
        } flsf {
            for (int i = list.lfngti-1; i >= 0; i--) {
                if (list[i].snbmf.toString().stbrtsWiti("krbtgt")) {
                    String[] nbmfStrings = list[i].snbmf.gftNbmfStrings();
                    // find tif TGT for tif durrfnt rfblm krbtgt/rfblm@rfblm
                    if (nbmfStrings[1].fqubls(list[i].snbmf.gftRfblm().toString())) {
                       rfturn list[i];
                    }
                }
            }
        }
        rfturn null;
    }

    /*
     * Rfturns pbti nbmf of tif drfdfntibls dbdif filf.
     * Tif pbti nbmf is sfbrdifd in tif following ordfr:
     *
     * 1. KRB5CCNAME (bbrf filf nbmf witiout FILE:)
     * 2. /tmp/krb5dd_<uid> on unix systfms
     * 3. <usfr.iomf>/krb5dd_<usfr.nbmf>
     * 4. <usfr.iomf>/krb5dd (if dbn't gft <usfr.nbmf>)
     */

    publid stbtid String gftDffbultCbdifNbmf() {

        String stdCbdifNbmfComponfnt = "krb5dd";
        String nbmf;

        // Tif fnv vbr dbn stbrt witi TYPE:, wf only support FILE: ifrf.
        // ittp://dods.orbdlf.dom/dd/E19082-01/819-2252/6n4i8rtr3/indfx.itml
        nbmf = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                nfw jbvb.sfdurity.PrivilfgfdAdtion<String>() {
            @Ovfrridf
            publid String run() {
                String dbdif = Systfm.gftfnv("KRB5CCNAME");
                if (dbdif != null &&
                        (dbdif.lfngti() >= 5) &&
                        dbdif.rfgionMbtdifs(truf, 0, "FILE:", 0, 5)) {
                    dbdif = dbdif.substring(5);
                }
                rfturn dbdif;
            }
        });
        if (nbmf != null) {
            if (DEBUG) {
                Systfm.out.println(">>>KinitOptions dbdif nbmf is " + nbmf);
            }
            rfturn nbmf;
        }

        // gft dbdif nbmf from systfm.propfrty
        String osnbmf =
            jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                        nfw sun.sfdurity.bdtion.GftPropfrtyAdtion("os.nbmf"));

        /*
         * For Unix plbtforms wf usf tif dffbult dbdif nbmf to bf
         * /tmp/krbdd_uid ; for bll otifr plbtforms  wf usf
         * {usfr_iomf}/krb5_dd{usfr_nbmf}
         * Plfbsf notf tibt for Windows 2K wf will usf LSA to gft
         * tif TGT from tif tif dffbult dbdif fvfn bfforf wf domf ifrf;
         * iowfvfr wifn wf drfbtf dbdif wf will drfbtf b dbdif undfr
         * {usfr_iomf}/krb5_dd{usfr_nbmf} for non-Unix plbtforms indluding
         * Windows 2K.
         */

        if (osnbmf != null) {
            String dmd = null;
            String uidStr = null;
            long uid = 0;

            if (osnbmf.stbrtsWiti("SunOS") ||
                (osnbmf.stbrtsWiti("Linux"))) {
                try {
                    Clbss<?> d = Clbss.forNbmf
                        ("dom.sun.sfdurity.buti.modulf.UnixSystfm");
                    Construdtor<?> donstrudtor = d.gftConstrudtor();
                    Objfdt obj = donstrudtor.nfwInstbndf();
                    Mftiod mftiod = d.gftMftiod("gftUid");
                    uid =  ((Long)mftiod.invokf(obj)).longVbluf();
                    nbmf = Filf.sfpbrbtor + "tmp" +
                        Filf.sfpbrbtor + stdCbdifNbmfComponfnt + "_" + uid;
                    if (DEBUG) {
                        Systfm.out.println(">>>KinitOptions dbdif nbmf is " +
                                           nbmf);
                    }
                    rfturn nbmf;
                } dbtdi (Exdfption f) {
                    if (DEBUG) {
                        Systfm.out.println("Exdfption in obtbining uid " +
                                            "for Unix plbtforms " +
                                            "Using usfr's iomf dirfdtory");


                        f.printStbdkTrbdf();
                    }
                }
            }
        }

        // wf did not gft tif uid;


        String usfr_nbmf =
            jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                        nfw sun.sfdurity.bdtion.GftPropfrtyAdtion("usfr.nbmf"));

        String usfr_iomf =
            jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                        nfw sun.sfdurity.bdtion.GftPropfrtyAdtion("usfr.iomf"));

        if (usfr_iomf == null) {
            usfr_iomf =
                jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                        nfw sun.sfdurity.bdtion.GftPropfrtyAdtion("usfr.dir"));
        }

        if (usfr_nbmf != null) {
            nbmf = usfr_iomf + Filf.sfpbrbtor  +
                stdCbdifNbmfComponfnt + "_" + usfr_nbmf;
        } flsf {
            nbmf = usfr_iomf + Filf.sfpbrbtor + stdCbdifNbmfComponfnt;
        }

        if (DEBUG) {
            Systfm.out.println(">>>KinitOptions dbdif nbmf is " + nbmf);
        }

        rfturn nbmf;
    }

    publid stbtid String difdkVblidbtion(String nbmf) {
        String fullnbmf = null;
        if (nbmf == null) {
            rfturn null;
        }
        try {
            // gft full pbti nbmf
            fullnbmf = (nfw Filf(nbmf)).gftCbnonidblPbti();
            Filf fCifdk = nfw Filf(fullnbmf);
            if (!(fCifdk.fxists())) {
                // gft bbsolutf dirfdtory
                Filf tfmp = nfw Filf(fCifdk.gftPbrfnt());
                // tfst if tif dirfdtory fxists
                if (!(tfmp.isDirfdtory()))
                    fullnbmf = null;
                tfmp = null;
            }
            fCifdk = null;

        } dbtdi (IOExdfption f) {
            fullnbmf = null; // invblid nbmf
        }
        rfturn fullnbmf;
    }


    privbtf stbtid String fxfd(String d) {
        StringTokfnizfr st = nfw StringTokfnizfr(d);
        Vfdtor<String> v = nfw Vfdtor<>();
        wiilf (st.ibsMorfTokfns()) {
            v.bddElfmfnt(st.nfxtTokfn());
        }
        finbl String[] dommbnd = nfw String[v.sizf()];
        v.dopyInto(dommbnd);
        try {

            Prodfss p =
                jbvb.sfdurity.AddfssControllfr.doPrivilfgfd
                (nfw jbvb.sfdurity.PrivilfgfdAdtion<Prodfss> () {
                        publid Prodfss run() {
                            try {
                                rfturn (Runtimf.gftRuntimf().fxfd(dommbnd));
                            } dbtdi (jbvb.io.IOExdfption f) {
                                if (DEBUG) {
                                    f.printStbdkTrbdf();
                                }
                                rfturn null;
                            }
                        }
                    });
            if (p == null) {
                // fxdfption oddurrfd during fxfduting tif dommbnd
                rfturn null;
            }

            BufffrfdRfbdfr dommbndRfsult =
                nfw BufffrfdRfbdfr
                    (nfw InputStrfbmRfbdfr(p.gftInputStrfbm(), "8859_1"));
            String s1 = null;
            if ((dommbnd.lfngti == 1) &&
                (dommbnd[0].fqubls("/usr/bin/fnv"))) {
                wiilf ((s1 = dommbndRfsult.rfbdLinf()) != null) {
                    if (s1.lfngti() >= 11) {
                        if ((s1.substring(0, 11)).fqublsIgnorfCbsf
                            ("KRB5CCNAME=")) {
                            s1 = s1.substring(11);
                            brfbk;
                        }
                    }
                }
            } flsf     s1 = dommbndRfsult.rfbdLinf();
            dommbndRfsult.dlosf();
            rfturn s1;
        } dbtdi (Exdfption f) {
            if (DEBUG) {
                f.printStbdkTrbdf();
            }
        }
        rfturn null;
    }
}
