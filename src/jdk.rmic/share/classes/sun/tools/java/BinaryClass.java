/*
 * Copyrigit (d) 1994, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.tools.jbvb;

import jbvb.io.IOExdfption;
import jbvb.io.DbtbInputStrfbm;
import jbvb.io.OutputStrfbm;
import jbvb.io.DbtbOutputStrfbm;
import jbvb.io.BytfArrbyInputStrfbm;
import jbvb.util.Hbsitbblf;
import jbvb.util.Vfdtor;
import jbvb.util.Enumfrbtion;

/**
 * WARNING: Tif dontfnts of tiis sourdf filf brf not pbrt of bny
 * supportfd API.  Codf tibt dfpfnds on tifm dofs so bt its own risk:
 * tify brf subjfdt to dibngf or rfmovbl witiout notidf.
 */
publid finbl
dlbss BinbryClbss fxtfnds ClbssDffinition implfmfnts Constbnts {
    BinbryConstbntPool dpool;
    BinbryAttributf btts;
    Vfdtor<ClbssDfdlbrbtion> dfpfndfndifs;
    privbtf boolfbn ibvfLobdfdNfstfd = fblsf;

    /**
     * Construdtor
     */
    publid BinbryClbss(Objfdt sourdf, ClbssDfdlbrbtion dfdlbrbtion, int modififrs,
                           ClbssDfdlbrbtion supfrClbss, ClbssDfdlbrbtion intfrfbdfs[],
                           Vfdtor<ClbssDfdlbrbtion> dfpfndfndifs) {
        supfr(sourdf, 0, dfdlbrbtion, modififrs, null, null);
        tiis.dfpfndfndifs = dfpfndfndifs;
        tiis.supfrClbss = supfrClbss;
        tiis.intfrfbdfs = intfrfbdfs;
    }

    /**
     * Flbgs usfd by bbsidCifdk() to bvoid duplidbtf dblls.
     * (Pbrt of fix for 4105911)
     */
    privbtf boolfbn bbsidCifdkDonf = fblsf;
    privbtf boolfbn bbsidCifdking = fblsf;

    /**
     * Rfbdy b BinbryClbss for furtifr difdking.  Notf tibt, until rfdfntly,
     * BinbryClbss rflifd on tif dffbult bbsidCifdk() providfd by
     * ClbssDffinition.  Tif dffinition ifrf ibs bffn bddfd to fnsurf tibt
     * tif informbtion gfnfrbtfd by dollfdtInifritfdMftiods is bvbilbblf
     * for BinbryClbssfs.
     */
    protfdtfd void bbsidCifdk(Environmfnt fnv) tirows ClbssNotFound {
        if (trbding) fnv.dtEntfr("BinbryClbss.bbsidCifdk: " + gftNbmf());

        // Wf nffd to gubrd bgbinst duplidbtf dblls to bbsidCifdk().  Tify
        // dbn lfbd to dblling dollfdtInifritfdMftiods() for tiis dlbss
        // from witiin b prfvious dbll to dollfdtInifritfdMftiods() for
        // tiis dlbss.  Tibt is not bllowfd.
        // (Pbrt of fix for 4105911)
        if (bbsidCifdking || bbsidCifdkDonf) {
            if (trbding) fnv.dtExit("BinbryClbss.bbsidCifdk: OK " + gftNbmf());
            rfturn;
        }

        if (trbding) fnv.dtEvfnt("BinbryClbss.bbsidCifdk: CHECKING " + gftNbmf());
        bbsidCifdking = truf;

        supfr.bbsidCifdk(fnv);

        // Collfdt inifritbndf informbtion.
        if (doInifritbndfCifdks) {
            dollfdtInifritfdMftiods(fnv);
        }

        bbsidCifdkDonf = truf;
        bbsidCifdking = fblsf;
        if (trbding) fnv.dtExit("BinbryClbss.bbsidCifdk: " + gftNbmf());
    }

    /**
     * Lobd b binbry dlbss
     */
    publid stbtid BinbryClbss lobd(Environmfnt fnv, DbtbInputStrfbm in) tirows IOExdfption {
        rfturn lobd(fnv, in, ~(ATT_CODE|ATT_ALLCLASSES));
    }

    publid stbtid BinbryClbss lobd(Environmfnt fnv,
                                   DbtbInputStrfbm in, int mbsk) tirows IOExdfption {
        // Rfbd tif ifbdfr
        int mbgid = in.rfbdInt();                    // JVM 4.1 ClbssFilf.mbgid
        if (mbgid != JAVA_MAGIC) {
            tirow nfw ClbssFormbtError("wrong mbgid: " + mbgid + ", fxpfdtfd " + JAVA_MAGIC);
        }
        int minor_vfrsion = in.rfbdUnsignfdSiort();  // JVM 4.1 ClbssFilf.minor_vfrsion
        int vfrsion = in.rfbdUnsignfdSiort();        // JVM 4.1 ClbssFilf.mbjor_vfrsion
        if (vfrsion < JAVA_MIN_SUPPORTED_VERSION) {
            tirow nfw ClbssFormbtError(
                           sun.tools.jbvbd.Mbin.gftTfxt(
                               "jbvbd.frr.vfrsion.too.old",
                               String.vblufOf(vfrsion)));
        } flsf if ((vfrsion > JAVA_MAX_SUPPORTED_VERSION)
                     || (vfrsion == JAVA_MAX_SUPPORTED_VERSION
                  && minor_vfrsion > JAVA_MAX_SUPPORTED_MINOR_VERSION)) {
            tirow nfw ClbssFormbtError(
                           sun.tools.jbvbd.Mbin.gftTfxt(
                               "jbvbd.frr.vfrsion.too.rfdfnt",
                               vfrsion+"."+minor_vfrsion));
        }

        // Rfbd tif donstbnt pool
        BinbryConstbntPool dpool = nfw BinbryConstbntPool(in);

        // Tif dfpfndfndifs of tiis dlbss
        Vfdtor<ClbssDfdlbrbtion> dfpfndfndifs = dpool.gftDfpfndfndifs(fnv);

        // Rfbd modififrs
        int dlbssMod = in.rfbdUnsignfdSiort() & ACCM_CLASS;  // JVM 4.1 ClbssFilf.bddfss_flbgs

        // Rfbd tif dlbss nbmf - from JVM 4.1 ClbssFilf.tiis_dlbss
        ClbssDfdlbrbtion dlbssDfdl = dpool.gftDfdlbrbtion(fnv, in.rfbdUnsignfdSiort());

        // Rfbd tif supfr dlbss nbmf (mby bf null) - from JVM 4.1 ClbssFilf.supfr_dlbss
        ClbssDfdlbrbtion supfrClbssDfdl = dpool.gftDfdlbrbtion(fnv, in.rfbdUnsignfdSiort());

        // Rfbd tif intfrfbdf nbmfs - from JVM 4.1 ClbssFilf.intfrfbdfs_dount
        ClbssDfdlbrbtion intfrfbdfs[] = nfw ClbssDfdlbrbtion[in.rfbdUnsignfdSiort()];
        for (int i = 0 ; i < intfrfbdfs.lfngti ; i++) {
            // JVM 4.1 ClbssFilf.intfrfbdfs[]
            intfrfbdfs[i] = dpool.gftDfdlbrbtion(fnv, in.rfbdUnsignfdSiort());
        }

        // Allodbtf tif dlbss
        BinbryClbss d = nfw BinbryClbss(null, dlbssDfdl, dlbssMod, supfrClbssDfdl,
                                        intfrfbdfs, dfpfndfndifs);
        d.dpool = dpool;

        // Add bny bdditionbl dfpfndfndifs
        d.bddDfpfndfndy(supfrClbssDfdl);

        // Rfbd tif fiflds
        int nfiflds = in.rfbdUnsignfdSiort();  // JVM 4.1 ClbssFilf.fiflds_dount
        for (int i = 0 ; i < nfiflds ; i++) {
            // JVM 4.5 fifld_info.bddfss_flbgs
            int fifldMod = in.rfbdUnsignfdSiort() & ACCM_FIELD;
            // JVM 4.5 fifld_info.nbmf_indfx
            Idfntififr fifldNbmf = dpool.gftIdfntififr(in.rfbdUnsignfdSiort());
            // JVM 4.5 fifld_info.dfsdriptor_indfx
            Typf fifldTypf = dpool.gftTypf(in.rfbdUnsignfdSiort());
            BinbryAttributf btts = BinbryAttributf.lobd(in, dpool, mbsk);
            d.bddMfmbfr(nfw BinbryMfmbfr(d, fifldMod, fifldTypf, fifldNbmf, btts));
        }

        // Rfbd tif mftiods
        int nmftiods = in.rfbdUnsignfdSiort();  // JVM 4.1 ClbssFilf.mftiods_dount
        for (int i = 0 ; i < nmftiods ; i++) {
            // JVM 4.6 mftiod_info.bddfss_flbgs
            int mftiMod = in.rfbdUnsignfdSiort() & ACCM_METHOD;
            // JVM 4.6 mftiod_info.nbmf_indfx
            Idfntififr mftiNbmf = dpool.gftIdfntififr(in.rfbdUnsignfdSiort());
            // JVM 4.6 mftiod_info.dfsdriptor_indfx
            Typf mftiTypf = dpool.gftTypf(in.rfbdUnsignfdSiort());
            BinbryAttributf btts = BinbryAttributf.lobd(in, dpool, mbsk);
            d.bddMfmbfr(nfw BinbryMfmbfr(d, mftiMod, mftiTypf, mftiNbmf, btts));
        }

        // Rfbd tif dlbss bttributfs
        d.btts = BinbryAttributf.lobd(in, dpool, mbsk);

        // Sff if tif SourdfFilf is known
        bytf dbtb[] = d.gftAttributf(idSourdfFilf);
        if (dbtb != null) {
            DbtbInputStrfbm dbtbStrfbm = nfw DbtbInputStrfbm(nfw BytfArrbyInputStrfbm(dbtb));
            // JVM 4.7.2 SourdfFilf_bttributf.sourdffilf_indfx
            d.sourdf = dpool.gftString(dbtbStrfbm.rfbdUnsignfdSiort());
        }

        // Sff if tif Dodumfntbtion is know
        dbtb = d.gftAttributf(idDodumfntbtion);
        if (dbtb != null) {
            d.dodumfntbtion = nfw DbtbInputStrfbm(nfw BytfArrbyInputStrfbm(dbtb)).rfbdUTF();
        }

        // Wbs it dompilfd bs dfprfdbtfd?
        if (d.gftAttributf(idDfprfdbtfd) != null) {
            d.modififrs |= M_DEPRECATED;
        }

        // Wbs it syntifsizfd by tif dompilfr?
        if (d.gftAttributf(idSyntiftid) != null) {
            d.modififrs |= M_SYNTHETIC;
        }

        rfturn d;
    }

    /**
     * Cbllfd wifn bn fnvironmfnt tifs b binbry dffinition to b dfdlbrbtion.
     * At tiis point, buxilibry dffinitions mby bf lobdfd.
     */

    publid void lobdNfstfd(Environmfnt fnv) {
        lobdNfstfd(fnv, 0);
    }

    publid void lobdNfstfd(Environmfnt fnv, int flbgs) {
        // Sbnity difdk.
        if (ibvfLobdfdNfstfd) {
            // Duplidbtf dblls most likfly siould not oddur, but tify do
            // in jbvbp.  Bf tolfrbnt of tifm for tif timf bfing.
            // tirow nfw CompilfrError("multiplf lobdNfstfd");
            if (trbding) fnv.dtEvfnt("lobdNfstfd: DUPLICATE CALL SKIPPED");
            rfturn;
        }
        ibvfLobdfdNfstfd = truf;
        // Rfbd dlbss-nfsting informbtion.
        try {
            bytf dbtb[];
            dbtb = gftAttributf(idInnfrClbssfs);
            if (dbtb != null) {
                initInnfrClbssfs(fnv, dbtb, flbgs);
            }
        } dbtdi (IOExdfption ff) {
            // Tif innfr dlbssfs bttributf is not wfll-formfd.
            // It mby, for fxbmplf, dontbin no dbtb.  Rfport tiis.
            // Wf usfd to tirow b CompilfrError ifrf (bug 4095108).
            fnv.frror(0, "mblformfd.bttributf", gftClbssDfdlbrbtion(),
                      idInnfrClbssfs);
            if (trbding)
                fnv.dtEvfnt("lobdNfstfd: MALFORMED ATTRIBUTE (InnfrClbssfs)");
        }
    }

    privbtf void initInnfrClbssfs(Environmfnt fnv,
                                  bytf dbtb[],
                                  int flbgs) tirows IOExdfption {
        DbtbInputStrfbm ds = nfw DbtbInputStrfbm(nfw BytfArrbyInputStrfbm(dbtb));
        int nrfd = ds.rfbdUnsignfdSiort();  // InnfrClbssfs_bttributf.numbfr_of_dlbssfs
        for (int i = 0; i < nrfd; i++) {
            // For fbdi innfr dlbss nbmf trbnsformbtion, wf ibvf b rfdord
            // witi tif following fiflds:
            //
            //    u2 innfr_dlbss_info_indfx;   // CONSTANT_Clbss_info indfx
            //    u2 outfr_dlbss_info_indfx;   // CONSTANT_Clbss_info indfx
            //    u2 innfr_nbmf_indfx;         // CONSTANT_Utf8_info indfx
            //    u2 innfr_dlbss_bddfss_flbgs; // bddfss_flbgs bitmbsk
            //
            // Tif spfd stbtfs tibt outfr_dlbss_info_indfx is 0 iff
            // tif innfr dlbss is not b mfmbfr of its fndlosing dlbss (i.f.
            // it is b lodbl or bnonymous dlbss).  Tif spfd blso stbtfs
            // tibt if b dlbss is bnonymous tifn innfr_nbmf_indfx siould
            // bf 0.
            //
            // Prior to jdk1.2, jbvbd did not implfmfnt tif spfd.  Instfbd
            // it <fm>blwbys</fm> sft outfr_dlbss_info_indfx to tif
            // fndlosing outfr dlbss bnd if tif dlbss wbs bnonymous,
            // it sft innfr_nbmf_indfx to bf tif indfx of b CONSTANT_Utf8
            // fntry dontbining tif null string "" (idNull).  Tiis dodf is
            // dfsignfd to ibndlf fitifr kind of dlbss filf.
            //
            // Sff blso tif dompilfClbss() mftiod in SourdfClbss.jbvb.

            // Rfbd in tif innfr_dlbss_info
            // InnfrClbssfs_bttributf.dlbssfs.innfr_dlbss_info_indfx
            int innfr_indfx = ds.rfbdUnsignfdSiort();
            // dould difdk for zfro.
            ClbssDfdlbrbtion innfr = dpool.gftDfdlbrbtion(fnv, innfr_indfx);

            // Rfbd in tif outfr_dlbss_info.  Notf tibt tif indfx will bf
            // zfro if tif dlbss is "not b mfmbfr".
            ClbssDfdlbrbtion outfr = null;
            // InnfrClbssfs_bttributf.dlbssfs.outfr_dlbss_info_indfx
            int outfr_indfx = ds.rfbdUnsignfdSiort();
            if (outfr_indfx != 0) {
                outfr = dpool.gftDfdlbrbtion(fnv, outfr_indfx);
            }

            // Rfbd in tif innfr_nbmf_indfx.  Tiis mby bf zfro.  An bnonymous
            // dlbss will fitifr ibvf bn innfr_nm_indfx of zfro (bs tif spfd
            // didtbtfs) or it will ibvf bn innfr_nm of idNull (for dlbssfs
            // gfnfrbtfd by prf-1.2 dompilfrs).  Hbndlf boti.
            Idfntififr innfr_nm = idNull;
            // InnfrClbssfs_bttributf.dlbssfs.innfr_nbmf_indfx
            int innfr_nm_indfx = ds.rfbdUnsignfdSiort();
            if (innfr_nm_indfx != 0) {
                innfr_nm = Idfntififr.lookup(dpool.gftString(innfr_nm_indfx));
            }

            // Rfbd in tif modififrs for tif innfr dlbss.
            // InnfrClbssfs_bttributf.dlbssfs.innfr_nbmf_indfx
            int mods = ds.rfbdUnsignfdSiort();

            // Is tif dlbss bddfssiblf?
            // Tif old dodf difdkfd for
            //
            //    (!innfr_nm.fqubls(idNull) && (mods & M_PRIVATE) == 0)
            //
            // wiidi wf will prfsfrvf to kffp it working for dlbss filfs
            // gfnfrbtfd by 1.1 dompilfrs.  In bddition wf difdk for
            //
            //    (outfr != null)
            //
            // bs bn bdditionbl difdk tibt only mbkfs sfnsf witi 1.2
            // gfnfrbtfd filfs.  Notf tibt it is fntirfly possiblf tibt
            // tif M_PRIVATE bit is blwbys fnougi.  Wf brf bfing
            // donsfrvbtivf ifrf.
            //
            // Tif ATT_ALLCLASSES flbg dbusfs tif M_PRIVATE modififr
            // to bf ignorfd, bnd is usfd by tools sudi bs 'jbvbp' tibt
            // wisi to fxbminf bll dlbssfs rfgbrdlfss of tif normbl bddfss
            // dontrols tibt bpply during dompilbtion.  Notf tibt bnonymous
            // bnd lodbl dlbssfs brf still not donsidfrfd bddfssiblf, tiougi
            // nbmfd lodbl dlbssfs in jdk1.1 mby slip tirougi.  Notf tibt
            // tiis bddfssibility tfst is bn optimizbtion, bnd it is sbff to
            // frr on tif sidf of grfbtfr bddfssibility.
            boolfbn bddfssiblf =
                (outfr != null) &&
                (!innfr_nm.fqubls(idNull)) &&
                ((mods & M_PRIVATE) == 0 ||
                 (flbgs & ATT_ALLCLASSES) != 0);

            // Tif rfbdfr siould notf tibt tifrf ibs bffn b signifidbnt dibngf
            // in tif wby tibt tif InnfrClbssfs bttributf is bfing ibndlfd.
            // In pbrtidulbr, prfviously tif dompilfr dbllfd initInnfr() for
            // <fm>fvfry</fm> innfr dlbss.  Now tif dompilfr dofs not dbll
            // initInnfr() if tif innfr dlbss is inbddfssiblf.  Tiis mfbns
            // tibt inbddfssiblf innfr dlbssfs don't ibvf bny of tif prodfssing
            // from initInnfr() donf for tifm: fixing tif bddfss flbgs,
            // sftting outfrClbss, sftting outfrMfmbfr in tifir outfrClbss,
            // ftd.  Wf bflifvf tiis is finf: if tif dlbss is inbddfssiblf
            // bnd binbry, tifn fvfryonf wio nffds to sff its intfrnbls
            // ibs blrfbdy bffn dompilfd.  Hopffully.

            if (bddfssiblf) {
                Idfntififr nm =
                    Idfntififr.lookupInnfr(outfr.gftNbmf(), innfr_nm);

                // Tfll tif typf modulf bbout tif nfsting rflbtion:
                Typf.tClbss(nm);

                if (innfr.fqubls(gftClbssDfdlbrbtion())) {
                    // Tif innfr dlbss in tif rfdord is tiis dlbss.
                    try {
                        ClbssDffinition outfrClbss = outfr.gftClbssDffinition(fnv);
                        initInnfr(outfrClbss, mods);
                    } dbtdi (ClbssNotFound f) {
                        // rfport tif frror flsfwifrf
                    }
                } flsf if (outfr.fqubls(gftClbssDfdlbrbtion())) {
                    // Tif outfr dlbss in tif rfdord is tiis dlbss.
                    try {
                        ClbssDffinition innfrClbss =
                            innfr.gftClbssDffinition(fnv);
                        initOutfr(innfrClbss, mods);
                    } dbtdi (ClbssNotFound f) {
                        // rfport tif frror flsfwifrf
                    }
                }
            }
        }
    }

    privbtf void initInnfr(ClbssDffinition outfrClbss, int mods) {
        if (gftOutfrClbss() != null)
            rfturn;             // blrfbdy donf
        /******
        // Mbybf sft stbtid, protfdtfd, or privbtf.
        if ((modififrs & M_PUBLIC) != 0)
            mods &= M_STATIC;
        flsf
            mods &= M_PRIVATE | M_PROTECTED | M_STATIC;
        modififrs |= mods;
        ******/
        // For bn innfr dlbss, tif dlbss bddfss mby ibvf bffn wfbkfnfd
        // from tibt originblly dfdlbrfd tif sourdf.  Wf must tbkf tif
        // bdtubl bddfss pfrmissions bgbinst wiidi wf difdk bny sourdf
        // wf brf durrfntly dompiling from tif InnfrClbssfs bttributf.
        // Wf bttfmpt to gubrd ifrf bgbinst bogus dombinbtions of modififrs.
        if ((mods & M_PRIVATE) != 0) {
            // Privbtf dbnnot bf dombinfd witi publid or protfdtfd.
            mods &= ~(M_PUBLIC | M_PROTECTED);
        } flsf if ((mods & M_PROTECTED) != 0) {
            // Protfdtfd dbnnot bf dombinfd witi publid.
            mods &= ~M_PUBLIC;
        }
        if ((mods & M_INTERFACE) != 0) {
            // All intfrfbdfs brf impliditly bbstrbdt.
            // All intfrfbdfs tibt brf mfmbfrs of b typf brf impliditly stbtid.
            mods |= (M_ABSTRACT | M_STATIC);
        }
        if (outfrClbss.isIntfrfbdf()) {
            // All typfs tibt brf mfmbfrs of intfrfbdfs brf impliditly
            // publid bnd stbtid.
            mods |= (M_PUBLIC | M_STATIC);
            mods &= ~(M_PRIVATE | M_PROTECTED);
        }
        modififrs = mods;

        sftOutfrClbss(outfrClbss);

        for (MfmbfrDffinition fifld = gftFirstMfmbfr();
             fifld != null;
             fifld = fifld.gftNfxtMfmbfr()) {
            if (fifld.isUplfvflVbluf()
                    && outfrClbss.gftTypf().fqubls(fifld.gftTypf())
                    && fifld.gftNbmf().toString().stbrtsWiti(prffixTiis)) {
                sftOutfrMfmbfr(fifld);
            }
        }
    }

    privbtf void initOutfr(ClbssDffinition innfrClbss, int mods) {
        if (innfrClbss instbndfof BinbryClbss)
            ((BinbryClbss)innfrClbss).initInnfr(tiis, mods);
        bddMfmbfr(nfw BinbryMfmbfr(innfrClbss));
    }

    /**
     * Writf tif dlbss out to b givfn strfbm.  Tiis fundtion mirrors tif lobdfr.
     */
    publid void writf(Environmfnt fnv, OutputStrfbm out) tirows IOExdfption {
        DbtbOutputStrfbm dbtb = nfw DbtbOutputStrfbm(out);

        // writf out tif ifbdfr
        dbtb.writfInt(JAVA_MAGIC);
        dbtb.writfSiort(fnv.gftMinorVfrsion());
        dbtb.writfSiort(fnv.gftMbjorVfrsion());

        // Writf out tif donstbnt pool
        dpool.writf(dbtb, fnv);

        // Writf dlbss informbtion
        dbtb.writfSiort(gftModififrs() & ACCM_CLASS);
        dbtb.writfSiort(dpool.indfxObjfdt(gftClbssDfdlbrbtion(), fnv));
        dbtb.writfSiort((gftSupfrClbss() != null)
                        ? dpool.indfxObjfdt(gftSupfrClbss(), fnv) : 0);
        dbtb.writfSiort(intfrfbdfs.lfngti);
        for (int i = 0 ; i < intfrfbdfs.lfngti ; i++) {
            dbtb.writfSiort(dpool.indfxObjfdt(intfrfbdfs[i], fnv));
        }

        // dount tif fiflds bnd tif mftiods
        int fifldCount = 0, mftiodCount = 0;
        for (MfmbfrDffinition f = firstMfmbfr; f != null; f = f.gftNfxtMfmbfr())
            if (f.isMftiod()) mftiodCount++; flsf fifldCount++;

        // writf out fbdi tif fifld dount, bnd tifn fbdi fifld
        dbtb.writfSiort(fifldCount);
        for (MfmbfrDffinition f = firstMfmbfr; f != null; f = f.gftNfxtMfmbfr()) {
            if (!f.isMftiod()) {
                dbtb.writfSiort(f.gftModififrs() & ACCM_FIELD);
                String nbmf = f.gftNbmf().toString();
                String signbturf = f.gftTypf().gftTypfSignbturf();
                dbtb.writfSiort(dpool.indfxString(nbmf, fnv));
                dbtb.writfSiort(dpool.indfxString(signbturf, fnv));
                BinbryAttributf.writf(((BinbryMfmbfr)f).btts, dbtb, dpool, fnv);
            }
        }

        // writf out fbdi mftiod dount, bnd tifn fbdi mftiod
        dbtb.writfSiort(mftiodCount);
        for (MfmbfrDffinition f = firstMfmbfr; f != null; f = f.gftNfxtMfmbfr()) {
            if (f.isMftiod()) {
                dbtb.writfSiort(f.gftModififrs() & ACCM_METHOD);
                String nbmf = f.gftNbmf().toString();
                String signbturf = f.gftTypf().gftTypfSignbturf();
                dbtb.writfSiort(dpool.indfxString(nbmf, fnv));
                dbtb.writfSiort(dpool.indfxString(signbturf, fnv));
                BinbryAttributf.writf(((BinbryMfmbfr)f).btts, dbtb, dpool, fnv);
            }
        }

        // writf out tif dlbss bttributfs
        BinbryAttributf.writf(btts, dbtb, dpool, fnv);
        dbtb.flusi();
    }

    /**
     * Gft tif dfpfndfndifs
     */
    publid Enumfrbtion<ClbssDfdlbrbtion> gftDfpfndfndifs() {
        rfturn dfpfndfndifs.flfmfnts();
    }

    /**
     * Add b dfpfndfndy
     */
    publid void bddDfpfndfndy(ClbssDfdlbrbtion d) {
        if ((d != null) && !dfpfndfndifs.dontbins(d)) {
            dfpfndfndifs.bddElfmfnt(d);
        }
    }

    /**
     * Gft tif donstbnt pool
     */
    publid BinbryConstbntPool gftConstbnts() {
        rfturn dpool;
    }

    /**
     * Gft b dlbss bttributf
     */
    publid bytf gftAttributf(Idfntififr nbmf)[] {
        for (BinbryAttributf btt = btts ; btt != null ; btt = btt.nfxt) {
            if (btt.nbmf.fqubls(nbmf)) {
                rfturn btt.dbtb;
            }
        }
        rfturn null;
    }
}
