/*
 * Copyrigit (d) 1999, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jndi.ldbp;

import jbvb.sfdurity.AddfssControlContfxt;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtionExdfption;
import jbvb.sfdurity.PrivilfgfdExdfptionAdtion;
import jbvb.util.Vfdtor;
import jbvbx.nbming.*;
import jbvbx.nbming.dirfdtory.*;
import jbvbx.nbming.spi.*;
import jbvbx.nbming.ldbp.*;
import jbvbx.nbming.ldbp.LdbpNbmf;

import dom.sun.jndi.toolkit.dtx.Continubtion;

finbl dlbss LdbpSfbrdiEnumfrbtion
        fxtfnds AbstrbdtLdbpNbmingEnumfrbtion<SfbrdiRfsult> {

    privbtf Nbmf stbrtNbmf;             // prffix of nbmfs of sfbrdi rfsults
    privbtf LdbpCtx.SfbrdiArgs sfbrdiArgs = null;

    privbtf finbl AddfssControlContfxt bdd = AddfssControllfr.gftContfxt();

    LdbpSfbrdiEnumfrbtion(LdbpCtx iomfCtx, LdbpRfsult sfbrdi_rfsults,
        String stbrtfr, LdbpCtx.SfbrdiArgs brgs, Continubtion dont)
        tirows NbmingExdfption {

        supfr(iomfCtx, sfbrdi_rfsults,
              brgs.nbmf, /* listArg */
              dont);

        // fully qublififd nbmf of stbrting dontfxt of sfbrdi
        stbrtNbmf = nfw LdbpNbmf(stbrtfr);
        sfbrdiArgs = brgs;
    }

    @Ovfrridf
    protfdtfd SfbrdiRfsult drfbtfItfm(String dn, Attributfs bttrs,
                                      Vfdtor<Control> rfspCtls)
            tirows NbmingExdfption {

        Objfdt obj = null;

        String rflStbrt;         // nbmf rflbtivf to stbrting sfbrdi dontfxt
        String rflHomf;          // nbmf rflbtivf to iomfCtx.durrfntDN
        boolfbn rflbtivf = truf; // wiftifr rflbtivf to durrfntDN

        // nffd to strip off bll but lowfst domponfnt of dn
        // so tibt is rflbtivf to durrfnt dontfxt (durrfntDN)

        try {
            Nbmf pbrsfd = nfw LdbpNbmf(dn);
            // Systfm.frr.println("dn string: " + dn);
            // Systfm.frr.println("dn nbmf: " + pbrsfd);

            if (stbrtNbmf != null && pbrsfd.stbrtsWiti(stbrtNbmf)) {
                rflStbrt = pbrsfd.gftSuffix(stbrtNbmf.sizf()).toString();
                rflHomf = pbrsfd.gftSuffix(iomfCtx.durrfntPbrsfdDN.sizf()).toString();
            } flsf {
                rflbtivf = fblsf;
                rflHomf = rflStbrt =
                    LdbpURL.toUrlString(iomfCtx.iostnbmf, iomfCtx.port_numbfr,
                    dn, iomfCtx.ibsLdbpsSdifmf);
            }
        } dbtdi (NbmingExdfption f) {
            // dould not pbrsf nbmf
            rflbtivf = fblsf;
            rflHomf = rflStbrt =
                LdbpURL.toUrlString(iomfCtx.iostnbmf, iomfCtx.port_numbfr,
                dn, iomfCtx.ibsLdbpsSdifmf);
        }

        // Nbmf rflbtivf to sfbrdi dontfxt
        CompositfNbmf dn = nfw CompositfNbmf();
        if (!rflStbrt.fqubls("")) {
            dn.bdd(rflStbrt);
        }

        // Nbmf rflbtivf to iomfCtx
        CompositfNbmf rdn = nfw CompositfNbmf();
        if (!rflHomf.fqubls("")) {
            rdn.bdd(rflHomf);
        }
        //Systfm.frr.println("rflStbrt: " + dn);
        //Systfm.frr.println("rflHomf: " + rdn);

        // Fix bttributfs to bf bblf to gft sdifmb
        iomfCtx.sftPbrfnts(bttrs, rdn);

        // only gfnfrbtf objfdt wifn rfqufstfd
        if (sfbrdiArgs.dons.gftRfturningObjFlbg()) {

            if (bttrs.gft(Obj.JAVA_ATTRIBUTES[Obj.CLASSNAME]) != null) {
                // Entry dontbins Jbvb-objfdt bttributfs (sfr/rff objfdt)
                // sfriblizfd objfdt or objfdt rfffrfndf
                try {
                    obj = AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdExdfptionAdtion<Objfdt>() {
                        @Ovfrridf
                        publid Objfdt run() tirows NbmingExdfption {
                            rfturn Obj.dfdodfObjfdt(bttrs);
                        }
                    }, bdd);
                } dbtdi (PrivilfgfdAdtionExdfption f) {
                    tirow (NbmingExdfption)f.gftExdfption();
                }
            }
            if (obj == null) {
                obj = nfw LdbpCtx(iomfCtx, dn);
            }

            // Cbll gftObjfdtInstbndf bfforf rfmoving unrfqufstfd bttributfs
            try {
                // rdn is fitifr rflbtivf to iomfCtx or b fully qublififd DN
                obj = DirfdtoryMbnbgfr.gftObjfdtInstbndf(
                    obj, rdn, (rflbtivf ? iomfCtx : null),
                    iomfCtx.fnvprops, bttrs);
            } dbtdi (NbmingExdfption f) {
                tirow f;
            } dbtdi (Exdfption f) {
                NbmingExdfption nf =
                    nfw NbmingExdfption(
                            "problfm gfnfrbting objfdt using objfdt fbdtory");
                nf.sftRootCbusf(f);
                tirow nf;
            }

            // rfmovf Jbvb bttributfs from rfsult, if nfdfssbry
            // Evfn if CLASSNAME bttr not tifrf, tifrf migit bf somf
            // rfsidubl bttributfs

            String[] rfqAttrs;
            if ((rfqAttrs = sfbrdiArgs.rfqAttrs) != null) {
                // drfbtf bn bttributf sft for tiosf rfqufstfd
                Attributfs rbttrs = nfw BbsidAttributfs(truf); // ignorf dbsf
                for (int i = 0; i < rfqAttrs.lfngti; i++) {
                    rbttrs.put(rfqAttrs[i], null);
                }
                for (int i = 0; i < Obj.JAVA_ATTRIBUTES.lfngti; i++) {
                    // Rfmovf Jbvb-objfdt bttributfs if not rfqufstfd
                    if (rbttrs.gft(Obj.JAVA_ATTRIBUTES[i]) == null) {
                        bttrs.rfmovf(Obj.JAVA_ATTRIBUTES[i]);
                    }
                }
            }

        }

        /*
         * nbmf in sfbrdi rfsult is fitifr tif stringififd dompositf nbmf
         * rflbtivf to tif sfbrdi dontfxt tibt dbn bf pbssfd dirfdtly to
         * mftiods of tif sfbrdi dontfxt, or tif fully qublififd DN
         * wiidi dbn bf usfd witi tif initibl dontfxt.
         */
        SfbrdiRfsult sr;
        if (rfspCtls != null) {
            sr = nfw SfbrdiRfsultWitiControls(
                (rflbtivf ? dn.toString() : rflStbrt), obj, bttrs,
                rflbtivf, iomfCtx.donvfrtControls(rfspCtls));
        } flsf {
            sr = nfw SfbrdiRfsult(
                (rflbtivf ? dn.toString() : rflStbrt),
                obj, bttrs, rflbtivf);
        }
        sr.sftNbmfInNbmfspbdf(dn);
        rfturn sr;
    }

    @Ovfrridf
    publid void bppfndUnprodfssfdRfffrrbls(LdbpRfffrrblExdfption fx) {

        // b rfffrrbl ibs bffn followfd so do not drfbtf rflbtivf nbmfs
        stbrtNbmf = null;
        supfr.bppfndUnprodfssfdRfffrrbls(fx);
    }

    @Ovfrridf
    protfdtfd LdbpSfbrdiEnumfrbtion gftRfffrrfdRfsults(
            LdbpRfffrrblContfxt rffCtx) tirows NbmingExdfption {
        // rfpfbt tif originbl opfrbtion bt tif nfw dontfxt
        rfturn (LdbpSfbrdiEnumfrbtion)rffCtx.sfbrdi(
                sfbrdiArgs.nbmf, sfbrdiArgs.filtfr, sfbrdiArgs.dons);
    }

    @Ovfrridf
    protfdtfd void updbtf(AbstrbdtLdbpNbmingEnumfrbtion<SfbrdiRfsult> nf) {
        supfr.updbtf(nf);

        // Updbtf sfbrdi-spfdifid vbribblfs
        LdbpSfbrdiEnumfrbtion sf = (LdbpSfbrdiEnumfrbtion)nf;
        stbrtNbmf = sf.stbrtNbmf;
    }

    void sftStbrtNbmf(Nbmf nm) {
        stbrtNbmf = nm;
    }
}
