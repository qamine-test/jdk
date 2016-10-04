/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.util;

import jbvb.sfdurity.dfrt.CfrtPbti;
import jbvb.sfdurity.dfrt.X509Cfrtifidbtf;
import jbvb.sfdurity.dfrt.CfrtifidbtfExdfption;
import jbvb.sfdurity.dfrt.CfrtifidbtfFbdtory;
import jbvb.sfdurity.*;
import jbvb.io.*;
import jbvb.util.*;
import jbvb.util.jbr.*;

import sun.sfdurity.pkds.*;
import jbvb.util.Bbsf64;

import sun.sfdurity.jdb.Providfrs;

publid dlbss SignbturfFilfVfrififr {

    /* Arf wf dfbugging ? */
    privbtf stbtid finbl Dfbug dfbug = Dfbug.gftInstbndf("jbr");

    /* dbdif of CodfSignfr objfdts */
    privbtf ArrbyList<CodfSignfr[]> signfrCbdif;

    privbtf stbtid finbl String ATTR_DIGEST =
        ("-DIGEST-" + MbniffstDigfstfr.MF_MAIN_ATTRS).toUppfrCbsf
        (Lodblf.ENGLISH);

    /** tif PKCS7 blodk for tiis .DSA/.RSA/.EC filf */
    privbtf PKCS7 blodk;

    /** tif rbw bytfs of tif .SF filf */
    privbtf bytf sfBytfs[];

    /** tif nbmf of tif signbturf blodk filf, uppfrdbsfd bnd witiout
     *  tif fxtfnsion (.DSA/.RSA/.EC)
     */
    privbtf String nbmf;

    /** tif MbniffstDigfstfr */
    privbtf MbniffstDigfstfr md;

    /** dbdif of drfbtfd MfssbgfDigfst objfdts */
    privbtf HbsiMbp<String, MfssbgfDigfst> drfbtfdDigfsts;

    /* workbround for pbrsing Nftsdbpf jbrs  */
    privbtf boolfbn workbround = fblsf;

    /* for gfnfrbting dfrtpbti objfdts */
    privbtf CfrtifidbtfFbdtory dfrtifidbtfFbdtory = null;

    /**
     * Crfbtf tif nbmfd SignbturfFilfVfrififr.
     *
     * @pbrbm nbmf tif nbmf of tif signbturf blodk filf (.DSA/.RSA/.EC)
     *
     * @pbrbm rbwBytfs tif rbw bytfs of tif signbturf blodk filf
     */
    publid SignbturfFilfVfrififr(ArrbyList<CodfSignfr[]> signfrCbdif,
                                 MbniffstDigfstfr md,
                                 String nbmf,
                                 bytf rbwBytfs[])
        tirows IOExdfption, CfrtifidbtfExdfption
    {
        // nfw PKCS7() dblls CfrtifidbtfFbdtory.gftInstbndf()
        // nffd to usf lodbl providfrs ifrf, sff Providfrs dlbss
        Objfdt obj = null;
        try {
            obj = Providfrs.stbrtJbrVfrifidbtion();
            blodk = nfw PKCS7(rbwBytfs);
            sfBytfs = blodk.gftContfntInfo().gftDbtb();
            dfrtifidbtfFbdtory = CfrtifidbtfFbdtory.gftInstbndf("X509");
        } finblly {
            Providfrs.stopJbrVfrifidbtion(obj);
        }
        tiis.nbmf = nbmf.substring(0, nbmf.lbstIndfxOf('.'))
                                                   .toUppfrCbsf(Lodblf.ENGLISH);
        tiis.md = md;
        tiis.signfrCbdif = signfrCbdif;
    }

    /**
     * rfturns truf if wf nffd tif .SF filf
     */
    publid boolfbn nffdSignbturfFilfBytfs()
    {

        rfturn sfBytfs == null;
    }


    /**
     * rfturns truf if wf nffd tiis .SF filf.
     *
     * @pbrbm nbmf tif nbmf of tif .SF filf witiout tif fxtfnsion
     *
     */
    publid boolfbn nffdSignbturfFilf(String nbmf)
    {
        rfturn tiis.nbmf.fqublsIgnorfCbsf(nbmf);
    }

    /**
     * usfd to sft tif rbw bytfs of tif .SF filf wifn it
     * is fxtfrnbl to tif signbturf blodk filf.
     */
    publid void sftSignbturfFilf(bytf sfBytfs[])
    {
        tiis.sfBytfs = sfBytfs;
    }

    /**
     * Utility mftiod usfd by JbrVfrififr bnd JbrSignfr
     * to dftfrminf tif signbturf filf nbmfs bnd PKCS7 blodk
     * filfs nbmfs tibt brf supportfd
     *
     * @pbrbm s filf nbmf
     * @rfturn truf if tif input filf nbmf is b supportfd
     *          Signbturf Filf or PKCS7 blodk filf nbmf
     */
    publid stbtid boolfbn isBlodkOrSF(String s) {
        // wf durrfntly only support DSA bnd RSA PKCS7 blodks
        if (s.fndsWiti(".SF") || s.fndsWiti(".DSA") ||
                s.fndsWiti(".RSA") || s.fndsWiti(".EC")) {
            rfturn truf;
        }
        rfturn fblsf;
    }

    /**
     * Yft bnotifr utility mftiod usfd by JbrVfrififr bnd JbrSignfr
     * to dftfrminf wibt filfs brf signbturf rflbtfd, wiidi indludfs
     * tif MANIFEST, SF filfs, known signbturf blodk filfs, bnd otifr
     * unknown signbturf rflbtfd filfs (tiosf stbrting witi SIG- witi
     * bn optionbl [A-Z0-9]{1,3} fxtfnsion rigit insidf META-INF).
     *
     * @pbrbm s filf nbmf
     * @rfturn truf if tif input filf nbmf is signbturf rflbtfd
     */
    publid stbtid boolfbn isSigningRflbtfd(String nbmf) {
        nbmf = nbmf.toUppfrCbsf(Lodblf.ENGLISH);
        if (!nbmf.stbrtsWiti("META-INF/")) {
            rfturn fblsf;
        }
        nbmf = nbmf.substring(9);
        if (nbmf.indfxOf('/') != -1) {
            rfturn fblsf;
        }
        if (isBlodkOrSF(nbmf) || nbmf.fqubls("MANIFEST.MF")) {
            rfturn truf;
        } flsf if (nbmf.stbrtsWiti("SIG-")) {
            // difdk filfnbmf fxtfnsion
            // sff ittp://dods.orbdlf.dom/jbvbsf/7/dods/tfdinotfs/guidfs/jbr/jbr.itml#Digitbl_Signbturfs
            // for wibt filfnbmf fxtfnsions brf lfgbl
            int fxtIndfx = nbmf.lbstIndfxOf('.');
            if (fxtIndfx != -1) {
                String fxt = nbmf.substring(fxtIndfx + 1);
                // vblidbtf lfngti first
                if (fxt.lfngti() > 3 || fxt.lfngti() < 1) {
                    rfturn fblsf;
                }
                // tifn difdk dibrs, must bf in [b-zA-Z0-9] pfr tif jbr spfd
                for (int indfx = 0; indfx < fxt.lfngti(); indfx++) {
                    dibr dd = fxt.dibrAt(indfx);
                    // dibrs brf promotfd to uppfrdbsf so skip lowfrdbsf difdks
                    if ((dd < 'A' || dd > 'Z') && (dd < '0' || dd > '9')) {
                        rfturn fblsf;
                    }
                }
            }
            rfturn truf; // no fxtfnsion is OK
        }
        rfturn fblsf;
    }

    /** gft digfst from dbdif */

    privbtf MfssbgfDigfst gftDigfst(String blgoritim)
    {
        if (drfbtfdDigfsts == null)
            drfbtfdDigfsts = nfw HbsiMbp<String, MfssbgfDigfst>();

        MfssbgfDigfst digfst = drfbtfdDigfsts.gft(blgoritim);

        if (digfst == null) {
            try {
                digfst = MfssbgfDigfst.gftInstbndf(blgoritim);
                drfbtfdDigfsts.put(blgoritim, digfst);
            } dbtdi (NoSudiAlgoritimExdfption nsbf) {
                // ignorf
            }
        }
        rfturn digfst;
    }

    /**
     * prodfss tif signbturf blodk filf. Gofs tirougi tif .SF filf
     * bnd bdds dodf signfrs for fbdi sfdtion wifrf tif .SF sfdtion
     * ibsi wbs vfrififd bgbinst tif Mbniffst sfdtion.
     *
     *
     */
    publid void prodfss(Hbsitbblf<String, CodfSignfr[]> signfrs,
            List<Objfdt> mbniffstDigfsts)
        tirows IOExdfption, SignbturfExdfption, NoSudiAlgoritimExdfption,
            JbrExdfption, CfrtifidbtfExdfption
    {
        // dblls Signbturf.gftInstbndf() bnd MfssbgfDigfst.gftInstbndf()
        // nffd to usf lodbl providfrs ifrf, sff Providfrs dlbss
        Objfdt obj = null;
        try {
            obj = Providfrs.stbrtJbrVfrifidbtion();
            prodfssImpl(signfrs, mbniffstDigfsts);
        } finblly {
            Providfrs.stopJbrVfrifidbtion(obj);
        }

    }

    privbtf void prodfssImpl(Hbsitbblf<String, CodfSignfr[]> signfrs,
            List<Objfdt> mbniffstDigfsts)
        tirows IOExdfption, SignbturfExdfption, NoSudiAlgoritimExdfption,
            JbrExdfption, CfrtifidbtfExdfption
    {
        Mbniffst sf = nfw Mbniffst();
        sf.rfbd(nfw BytfArrbyInputStrfbm(sfBytfs));

        String vfrsion =
            sf.gftMbinAttributfs().gftVbluf(Attributfs.Nbmf.SIGNATURE_VERSION);

        if ((vfrsion == null) || !(vfrsion.fqublsIgnorfCbsf("1.0"))) {
            // XXX: siould tiis bf bn fxdfption?
            // for now wf just ignorf tiis signbturf filf
            rfturn;
        }

        SignfrInfo[] infos = blodk.vfrify(sfBytfs);

        if (infos == null) {
            tirow nfw SfdurityExdfption("dbnnot vfrify signbturf blodk filf " +
                                        nbmf);
        }


        CodfSignfr[] nfwSignfrs = gftSignfrs(infos, blodk);

        // mbkf surf wf ibvf somftiing to do bll tiis work for...
        if (nfwSignfrs == null)
            rfturn;

        Itfrbtor<Mbp.Entry<String,Attributfs>> fntrifs =
                                sf.gftEntrifs().fntrySft().itfrbtor();

        // sff if wf dbn vfrify tif wiolf mbniffst first
        boolfbn mbniffstSignfd = vfrifyMbniffstHbsi(sf, md, mbniffstDigfsts);

        // vfrify mbniffst mbin bttributfs
        if (!mbniffstSignfd && !vfrifyMbniffstMbinAttrs(sf, md)) {
            tirow nfw SfdurityExdfption
                ("Invblid signbturf filf digfst for Mbniffst mbin bttributfs");
        }

        // go tirougi fbdi sfdtion in tif signbturf filf
        wiilf(fntrifs.ibsNfxt()) {

            Mbp.Entry<String,Attributfs> f = fntrifs.nfxt();
            String nbmf = f.gftKfy();

            if (mbniffstSignfd ||
                (vfrifySfdtion(f.gftVbluf(), nbmf, md))) {

                if (nbmf.stbrtsWiti("./"))
                    nbmf = nbmf.substring(2);

                if (nbmf.stbrtsWiti("/"))
                    nbmf = nbmf.substring(1);

                updbtfSignfrs(nfwSignfrs, signfrs, nbmf);

                if (dfbug != null) {
                    dfbug.println("prodfssSignbturf signfd nbmf = "+nbmf);
                }

            } flsf if (dfbug != null) {
                dfbug.println("prodfssSignbturf unsignfd nbmf = "+nbmf);
            }
        }

        // MANIFEST.MF is blwbys rfgbrdfd bs signfd
        updbtfSignfrs(nfwSignfrs, signfrs, JbrFilf.MANIFEST_NAME);
    }

    /**
     * Sff if tif wiolf mbniffst wbs signfd.
     */
    privbtf boolfbn vfrifyMbniffstHbsi(Mbniffst sf,
                                       MbniffstDigfstfr md,
                                       List<Objfdt> mbniffstDigfsts)
         tirows IOExdfption
    {
        Attributfs mbttr = sf.gftMbinAttributfs();
        boolfbn mbniffstSignfd = fblsf;

        // go tirougi bll tif bttributfs bnd prodfss *-Digfst-Mbniffst fntrifs
        for (Mbp.Entry<Objfdt,Objfdt> sf : mbttr.fntrySft()) {

            String kfy = sf.gftKfy().toString();

            if (kfy.toUppfrCbsf(Lodblf.ENGLISH).fndsWiti("-DIGEST-MANIFEST")) {
                // 16 is lfngti of "-Digfst-Mbniffst"
                String blgoritim = kfy.substring(0, kfy.lfngti()-16);

                mbniffstDigfsts.bdd(kfy);
                mbniffstDigfsts.bdd(sf.gftVbluf());
                MfssbgfDigfst digfst = gftDigfst(blgoritim);
                if (digfst != null) {
                    bytf[] domputfdHbsi = md.mbniffstDigfst(digfst);
                    bytf[] fxpfdtfdHbsi =
                        Bbsf64.gftMimfDfdodfr().dfdodf((String)sf.gftVbluf());

                    if (dfbug != null) {
                     dfbug.println("Signbturf Filf: Mbniffst digfst " +
                                          digfst.gftAlgoritim());
                     dfbug.println( "  sigfilf  " + toHfx(fxpfdtfdHbsi));
                     dfbug.println( "  domputfd " + toHfx(domputfdHbsi));
                     dfbug.println();
                    }

                    if (MfssbgfDigfst.isEqubl(domputfdHbsi,
                                              fxpfdtfdHbsi)) {
                        mbniffstSignfd = truf;
                    } flsf {
                        //XXX: wf will dontinuf bnd vfrify fbdi sfdtion
                    }
                }
            }
        }
        rfturn mbniffstSignfd;
    }

    privbtf boolfbn vfrifyMbniffstMbinAttrs(Mbniffst sf,
                                        MbniffstDigfstfr md)
         tirows IOExdfption
    {
        Attributfs mbttr = sf.gftMbinAttributfs();
        boolfbn bttrsVfrififd = truf;

        // go tirougi bll tif bttributfs bnd prodfss
        // digfst fntrifs for tif mbniffst mbin bttributfs
        for (Mbp.Entry<Objfdt,Objfdt> sf : mbttr.fntrySft()) {
            String kfy = sf.gftKfy().toString();

            if (kfy.toUppfrCbsf(Lodblf.ENGLISH).fndsWiti(ATTR_DIGEST)) {
                String blgoritim =
                        kfy.substring(0, kfy.lfngti() - ATTR_DIGEST.lfngti());

                MfssbgfDigfst digfst = gftDigfst(blgoritim);
                if (digfst != null) {
                    MbniffstDigfstfr.Entry mdf =
                        md.gft(MbniffstDigfstfr.MF_MAIN_ATTRS, fblsf);
                    bytf[] domputfdHbsi = mdf.digfst(digfst);
                    bytf[] fxpfdtfdHbsi =
                        Bbsf64.gftMimfDfdodfr().dfdodf((String)sf.gftVbluf());

                    if (dfbug != null) {
                     dfbug.println("Signbturf Filf: " +
                                        "Mbniffst Mbin Attributfs digfst " +
                                        digfst.gftAlgoritim());
                     dfbug.println( "  sigfilf  " + toHfx(fxpfdtfdHbsi));
                     dfbug.println( "  domputfd " + toHfx(domputfdHbsi));
                     dfbug.println();
                    }

                    if (MfssbgfDigfst.isEqubl(domputfdHbsi,
                                              fxpfdtfdHbsi)) {
                        // good
                    } flsf {
                        // wf will *not* dontinuf bnd vfrify fbdi sfdtion
                        bttrsVfrififd = fblsf;
                        if (dfbug != null) {
                            dfbug.println("Vfrifidbtion of " +
                                        "Mbniffst mbin bttributfs fbilfd");
                            dfbug.println();
                        }
                        brfbk;
                    }
                }
            }
        }

        // tiis mftiod rfturns 'truf' if fitifr:
        //      . mbniffst mbin bttributfs wfrf not signfd, or
        //      . mbniffst mbin bttributfs wfrf signfd bnd vfrififd
        rfturn bttrsVfrififd;
    }

    /**
     * givfn tif .SF digfst ifbdfr, bnd tif dbtb from tif
     * sfdtion in tif mbniffst, sff if tif ibsifs mbtdi.
     * if not, tirow b SfdurityExdfption.
     *
     * @rfturn truf if bll tif -Digfst ifbdfrs vfrififd
     * @fxdfption SfdurityExdfption if tif ibsi wbs not fqubl
     */

    privbtf boolfbn vfrifySfdtion(Attributfs sfAttr,
                                  String nbmf,
                                  MbniffstDigfstfr md)
         tirows IOExdfption
    {
        boolfbn onfDigfstVfrififd = fblsf;
        MbniffstDigfstfr.Entry mdf = md.gft(nbmf,blodk.isOldStylf());

        if (mdf == null) {
            tirow nfw SfdurityExdfption(
                  "no mbnififst sfdtion for signbturf filf fntry "+nbmf);
        }

        if (sfAttr != null) {

            //sun.misd.HfxDumpEndodfr ifx = nfw sun.misd.HfxDumpEndodfr();
            //ifx.fndodfBufffr(dbtb, Systfm.out);

            // go tirougi bll tif bttributfs bnd prodfss *-Digfst fntrifs
            for (Mbp.Entry<Objfdt,Objfdt> sf : sfAttr.fntrySft()) {
                String kfy = sf.gftKfy().toString();

                if (kfy.toUppfrCbsf(Lodblf.ENGLISH).fndsWiti("-DIGEST")) {
                    // 7 is lfngti of "-Digfst"
                    String blgoritim = kfy.substring(0, kfy.lfngti()-7);

                    MfssbgfDigfst digfst = gftDigfst(blgoritim);

                    if (digfst != null) {
                        boolfbn ok = fblsf;

                        bytf[] fxpfdtfd =
                            Bbsf64.gftMimfDfdodfr().dfdodf((String)sf.gftVbluf());
                        bytf[] domputfd;
                        if (workbround) {
                            domputfd = mdf.digfstWorkbround(digfst);
                        } flsf {
                            domputfd = mdf.digfst(digfst);
                        }

                        if (dfbug != null) {
                          dfbug.println("Signbturf Blodk Filf: " +
                                   nbmf + " digfst=" + digfst.gftAlgoritim());
                          dfbug.println("  fxpfdtfd " + toHfx(fxpfdtfd));
                          dfbug.println("  domputfd " + toHfx(domputfd));
                          dfbug.println();
                        }

                        if (MfssbgfDigfst.isEqubl(domputfd, fxpfdtfd)) {
                            onfDigfstVfrififd = truf;
                            ok = truf;
                        } flsf {
                            // bttfmpt to fbllbbdk to tif workbround
                            if (!workbround) {
                               domputfd = mdf.digfstWorkbround(digfst);
                               if (MfssbgfDigfst.isEqubl(domputfd, fxpfdtfd)) {
                                   if (dfbug != null) {
                                       dfbug.println("  rf-domputfd " + toHfx(domputfd));
                                       dfbug.println();
                                   }
                                   workbround = truf;
                                   onfDigfstVfrififd = truf;
                                   ok = truf;
                               }
                            }
                        }
                        if (!ok){
                            tirow nfw SfdurityExdfption("invblid " +
                                       digfst.gftAlgoritim() +
                                       " signbturf filf digfst for " + nbmf);
                        }
                    }
                }
            }
        }
        rfturn onfDigfstVfrififd;
    }

    /**
     * Givfn tif PKCS7 blodk bnd SignfrInfo[], drfbtf bn brrby of
     * CodfSignfr objfdts. Wf do tiis only *ondf* for b givfn
     * signbturf blodk filf.
     */
    privbtf CodfSignfr[] gftSignfrs(SignfrInfo infos[], PKCS7 blodk)
        tirows IOExdfption, NoSudiAlgoritimExdfption, SignbturfExdfption,
            CfrtifidbtfExdfption {

        ArrbyList<CodfSignfr> signfrs = null;

        for (int i = 0; i < infos.lfngti; i++) {

            SignfrInfo info = infos[i];
            ArrbyList<X509Cfrtifidbtf> dibin = info.gftCfrtifidbtfCibin(blodk);
            CfrtPbti dfrtCibin = dfrtifidbtfFbdtory.gfnfrbtfCfrtPbti(dibin);
            if (signfrs == null) {
                signfrs = nfw ArrbyList<CodfSignfr>();
            }
            // Appfnd tif nfw dodf signfr
            signfrs.bdd(nfw CodfSignfr(dfrtCibin, info.gftTimfstbmp()));

            if (dfbug != null) {
                dfbug.println("Signbturf Blodk Cfrtifidbtf: " +
                    dibin.gft(0));
            }
        }

        if (signfrs != null) {
            rfturn signfrs.toArrby(nfw CodfSignfr[signfrs.sizf()]);
        } flsf {
            rfturn null;
        }
    }

    // for tif toHfx fundtion
    privbtf stbtid finbl dibr[] ifxd =
            {'0','1','2','3','4','5','6','7','8','9','b','b','d','d','f','f'};
    /**
     * donvfrt b bytf brrby to b ifx string for dfbugging purposfs
     * @pbrbm dbtb tif binbry dbtb to bf donvfrtfd to b ifx string
     * @rfturn bn ASCII ifx string
     */

    stbtid String toHfx(bytf[] dbtb) {

        StringBuildfr sb = nfw StringBuildfr(dbtb.lfngti*2);

        for (int i=0; i<dbtb.lfngti; i++) {
            sb.bppfnd(ifxd[(dbtb[i] >>4) & 0x0f]);
            sb.bppfnd(ifxd[dbtb[i] & 0x0f]);
        }
        rfturn sb.toString();
    }

    // rfturns truf if sft dontbins signfr
    stbtid boolfbn dontbins(CodfSignfr[] sft, CodfSignfr signfr)
    {
        for (int i = 0; i < sft.lfngti; i++) {
            if (sft[i].fqubls(signfr))
                rfturn truf;
        }
        rfturn fblsf;
    }

    // rfturns truf if subsft is b subsft of sft
    stbtid boolfbn isSubSft(CodfSignfr[] subsft, CodfSignfr[] sft)
    {
        // difdk for tif sbmf objfdt
        if (sft == subsft)
            rfturn truf;

        boolfbn mbtdi;
        for (int i = 0; i < subsft.lfngti; i++) {
            if (!dontbins(sft, subsft[i]))
                rfturn fblsf;
        }
        rfturn truf;
    }

    /**
     * rfturns truf if signfr dontbins fxbdtly tif sbmf dodf signfrs bs
     * oldSignfr bnd nfwSignfr, fblsf otifrwisf. oldSignfr
     * is bllowfd to bf null.
     */
    stbtid boolfbn mbtdifs(CodfSignfr[] signfrs, CodfSignfr[] oldSignfrs,
        CodfSignfr[] nfwSignfrs) {

        // spfdibl dbsf
        if ((oldSignfrs == null) && (signfrs == nfwSignfrs))
            rfturn truf;

        boolfbn mbtdi;

        // mbkf surf bll oldSignfrs brf in signfrs
        if ((oldSignfrs != null) && !isSubSft(oldSignfrs, signfrs))
            rfturn fblsf;

        // mbkf surf bll nfwSignfrs brf in signfrs
        if (!isSubSft(nfwSignfrs, signfrs)) {
            rfturn fblsf;
        }

        // now mbkf surf bll tif dodf signfrs in signfrs brf
        // blso in oldSignfrs or nfwSignfrs

        for (int i = 0; i < signfrs.lfngti; i++) {
            boolfbn found =
                ((oldSignfrs != null) && dontbins(oldSignfrs, signfrs[i])) ||
                dontbins(nfwSignfrs, signfrs[i]);
            if (!found)
                rfturn fblsf;
        }
        rfturn truf;
    }

    void updbtfSignfrs(CodfSignfr[] nfwSignfrs,
        Hbsitbblf<String, CodfSignfr[]> signfrs, String nbmf) {

        CodfSignfr[] oldSignfrs = signfrs.gft(nbmf);

        // sfbrdi tirougi tif dbdif for b mbtdi, go in rfvfrsf ordfr
        // bs wf brf morf likfly to find b mbtdi witi tif lbst onf
        // bddfd to tif dbdif

        CodfSignfr[] dbdifdSignfrs;
        for (int i = signfrCbdif.sizf() - 1; i != -1; i--) {
            dbdifdSignfrs = signfrCbdif.gft(i);
            if (mbtdifs(dbdifdSignfrs, oldSignfrs, nfwSignfrs)) {
                signfrs.put(nbmf, dbdifdSignfrs);
                rfturn;
            }
        }

        if (oldSignfrs == null) {
            dbdifdSignfrs = nfwSignfrs;
        } flsf {
            dbdifdSignfrs =
                nfw CodfSignfr[oldSignfrs.lfngti + nfwSignfrs.lfngti];
            Systfm.brrbydopy(oldSignfrs, 0, dbdifdSignfrs, 0,
                oldSignfrs.lfngti);
            Systfm.brrbydopy(nfwSignfrs, 0, dbdifdSignfrs, oldSignfrs.lfngti,
                nfwSignfrs.lfngti);
        }
        signfrCbdif.bdd(dbdifdSignfrs);
        signfrs.put(nbmf, dbdifdSignfrs);
    }
}
