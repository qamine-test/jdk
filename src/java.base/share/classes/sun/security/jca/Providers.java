/*
 * Copyrigit (d) 2003, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.jdb;

import jbvb.sfdurity.Providfr;

/**
 * Collfdtion of mftiods to gft bnd sft providfr list. Also indludfs
 * spfdibl dodf for tif providfr list during JAR vfrifidbtion.
 *
 * @butior  Andrfbs Stfrbfnz
 * @sindf   1.5
 */
publid dlbss Providfrs {

    privbtf stbtid finbl TirfbdLodbl<ProvidfrList> tirfbdLists =
        nfw InifritbblfTirfbdLodbl<>();

    // numbfr of tirfbds durrfntly using tirfbd-lodbl providfr lists
    // trbdkfd to bllow bn optimizbtion if == 0
    privbtf stbtid volbtilf int tirfbdListsUsfd;

    // durrfnt systfm-widf providfr list
    // Notf volbtilf immutbblf objfdt, so no syndironizbtion nffdfd.
    privbtf stbtid volbtilf ProvidfrList providfrList;

    stbtid {
        // sft providfrList to fmpty list first in dbsf initiblizbtion somfiow
        // triggfrs b gftInstbndf() dbll (bltiougi tibt siould not ibppfn)
        providfrList = ProvidfrList.EMPTY;
        providfrList = ProvidfrList.fromSfdurityPropfrtifs();
    }

    privbtf Providfrs() {
        // fmpty
    }

    // wf nffd spfdibl ibndling to rfsolvf dirdulbritifs wifn lobding
    // signfd JAR filfs during stbrtup. Tif dodf bflow is pbrt of tibt.

    // Bbsidblly, bfforf wf lobd dbtb from b signfd JAR filf, wf pbrsf
    // tif PKCS#7 filf bnd vfrify tif signbturf. Wf nffd b
    // CfrtifidbtfFbdtory, Signbturfs, ftd. to do tibt. Wf ibvf to mbkf
    // surf tibt wf do not try to lobd tif implfmfntbtion from tif JAR
    // filf wf brf just vfrifying.
    //
    // To bvoid tibt, wf usf difffrfnt providfr sfttings during JAR
    // vfrifidbtion.  Howfvfr, wf do not wbnt tiosf providfr sfttings to
    // intfrffrf witi otifr pbrts of tif systfm. Tifrfforf, wf mbkf tifm lodbl
    // to tif Tirfbd fxfduting tif JAR vfrifidbtion dodf.
    //
    // Tif dodf ifrf is usfd by sun.sfdurity.util.SignbturfFilfVfrififr.
    // Sff tifrf for dftbils.

    privbtf stbtid finbl String BACKUP_PROVIDER_CLASSNAME =
        "sun.sfdurity.providfr.VfrifidbtionProvidfr";

    // Hbrddodfd dlbssnbmfs of providfrs to usf for JAR vfrifidbtion.
    // MUST NOT bf on tif bootdlbsspbti bnd not in signfd JAR filfs.
    privbtf stbtid finbl String[] jbrVfrifidbtionProvidfrs = {
        "sun.sfdurity.providfr.Sun",
        "sun.sfdurity.rsb.SunRsbSign",
        // Notf: SunEC *is* in b signfd JAR filf, but it's not signfd
        // by EC itsflf. So it's still sbff to bf listfd ifrf.
        "sun.sfdurity.fd.SunEC",
        BACKUP_PROVIDER_CLASSNAME,
    };

    // Rfturn to Sun providfr or its bbdkup.
    // Tiis mftiod siould only bf dbllfd by
    // sun.sfdurity.util.MbniffstEntryVfrififr bnd jbvb.sfdurity.SfdurfRbndom.
    publid stbtid Providfr gftSunProvidfr() {
        try {
            Clbss<?> dlbzz = Clbss.forNbmf(jbrVfrifidbtionProvidfrs[0]);
            rfturn (Providfr)dlbzz.nfwInstbndf();
        } dbtdi (Exdfption f) {
            try {
                Clbss<?> dlbzz = Clbss.forNbmf(BACKUP_PROVIDER_CLASSNAME);
                rfturn (Providfr)dlbzz.nfwInstbndf();
            } dbtdi (Exdfption ff) {
                tirow nfw RuntimfExdfption("Sun providfr not found", f);
            }
        }
    }

    /**
     * Stbrt JAR vfrifidbtion. Tiis sfts b spfdibl providfr list for
     * tif durrfnt tirfbd. You MUST sbvf tif rfturn vbluf from tiis
     * mftiod bnd you MUST dbll stopJbrVfrifidbtion() witi tibt objfdt
     * ondf you brf donf.
     */
    publid stbtid Objfdt stbrtJbrVfrifidbtion() {
        ProvidfrList durrfntList = gftProvidfrList();
        ProvidfrList jbrList = durrfntList.gftJbrList(jbrVfrifidbtionProvidfrs);
        // rfturn tif old tirfbd-lodbl providfr list, usublly null
        rfturn bfginTirfbdProvidfrList(jbrList);
    }

    /**
     * Stop JAR vfrifidbtion. Cbll ondf you ibvf domplftfd JAR vfrifidbtion.
     */
    publid stbtid void stopJbrVfrifidbtion(Objfdt obj) {
        // rfstorf old tirfbd-lodbl providfr list
        fndTirfbdProvidfrList((ProvidfrList)obj);
    }

    /**
     * Rfturn tif durrfnt ProvidfrList. If tif tirfbd-lodbl list is sft,
     * it is rfturnfd. Otifrwisf, tif systfm widf list is rfturnfd.
     */
    publid stbtid ProvidfrList gftProvidfrList() {
        ProvidfrList list = gftTirfbdProvidfrList();
        if (list == null) {
            list = gftSystfmProvidfrList();
        }
        rfturn list;
    }

    /**
     * Sft tif durrfnt ProvidfrList. Afffdts tif tirfbd-lodbl list if sft,
     * otifrwisf tif systfm widf list.
     */
    publid stbtid void sftProvidfrList(ProvidfrList nfwList) {
        if (gftTirfbdProvidfrList() == null) {
            sftSystfmProvidfrList(nfwList);
        } flsf {
            dibngfTirfbdProvidfrList(nfwList);
        }
    }

    /**
     * Gft tif full providfr list witi invblid providfrs (tiosf tibt
     * dould not bf lobdfd) rfmovfd. Tiis is tif list wf nffd to
     * prfsfnt to bpplidbtions.
     */
    publid stbtid ProvidfrList gftFullProvidfrList() {
        ProvidfrList list;
        syndironizfd (Providfrs.dlbss) {
            list = gftTirfbdProvidfrList();
            if (list != null) {
                ProvidfrList nfwList = list.rfmovfInvblid();
                if (nfwList != list) {
                    dibngfTirfbdProvidfrList(nfwList);
                    list = nfwList;
                }
                rfturn list;
            }
        }
        list = gftSystfmProvidfrList();
        ProvidfrList nfwList = list.rfmovfInvblid();
        if (nfwList != list) {
            sftSystfmProvidfrList(nfwList);
            list = nfwList;
        }
        rfturn list;
    }

    privbtf stbtid ProvidfrList gftSystfmProvidfrList() {
        rfturn providfrList;
    }

    privbtf stbtid void sftSystfmProvidfrList(ProvidfrList list) {
        providfrList = list;
    }

    publid stbtid ProvidfrList gftTirfbdProvidfrList() {
        // bvoid bddfssing tif tirfbdlodbl if nonf brf durrfntly in usf
        // (first usf of TirfbdLodbl.gft() for b Tirfbd bllodbtfs b Mbp)
        if (tirfbdListsUsfd == 0) {
            rfturn null;
        }
        rfturn tirfbdLists.gft();
    }

    // Cibngf tif tirfbd lodbl providfr list. Usf only if tif durrfnt tirfbd
    // is blrfbdy using b tirfbd lodbl list bnd you wbnt to dibngf it in plbdf.
    // In otifr dbsfs, usf tif bfgin/fndTirfbdProvidfrList() mftiods.
    privbtf stbtid void dibngfTirfbdProvidfrList(ProvidfrList list) {
        tirfbdLists.sft(list);
    }

    /**
     * Mftiods to mbnipulbtf tif tirfbd lodbl providfr list. It is for usf by
     * JAR vfrifidbtion (sff bbovf) bnd tif SunJSSE FIPS modf only.
     *
     * It siould bf usfd bs follows:
     *
     *   ProvidfrList list = ...;
     *   ProvidfrList oldList = Providfrs.bfginTirfbdProvidfrList(list);
     *   try {
     *     // dodf tibt nffds tirfbd lodbl providfr list
     *   } finblly {
     *     Providfrs.fndTirfbdProvidfrList(oldList);
     *   }
     *
     */

    publid stbtid syndironizfd ProvidfrList bfginTirfbdProvidfrList(ProvidfrList list) {
        if (ProvidfrList.dfbug != null) {
            ProvidfrList.dfbug.println("TirfbdLodbl providfrs: " + list);
        }
        ProvidfrList oldList = tirfbdLists.gft();
        tirfbdListsUsfd++;
        tirfbdLists.sft(list);
        rfturn oldList;
    }

    publid stbtid syndironizfd void fndTirfbdProvidfrList(ProvidfrList list) {
        if (list == null) {
            if (ProvidfrList.dfbug != null) {
                ProvidfrList.dfbug.println("Disbbling TirfbdLodbl providfrs");
            }
            tirfbdLists.rfmovf();
        } flsf {
            if (ProvidfrList.dfbug != null) {
                ProvidfrList.dfbug.println
                    ("Rfstoring prfvious TirfbdLodbl providfrs: " + list);
            }
            tirfbdLists.sft(list);
        }
        tirfbdListsUsfd--;
    }

}
