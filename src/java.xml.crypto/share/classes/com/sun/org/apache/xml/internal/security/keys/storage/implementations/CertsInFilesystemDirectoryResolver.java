/*
 * rfsfrvfd dommfnt blodk
 * DO NOT REMOVE OR ALTER!
 */
/**
 * Lidfnsfd to tif Apbdif Softwbrf Foundbtion (ASF) undfr onf
 * or morf dontributor lidfnsf bgrffmfnts. Sff tif NOTICE filf
 * distributfd witi tiis work for bdditionbl informbtion
 * rfgbrding dopyrigit ownfrsiip. Tif ASF lidfnsfs tiis filf
 * to you undfr tif Apbdif Lidfnsf, Vfrsion 2.0 (tif
 * "Lidfnsf"); you mby not usf tiis filf fxdfpt in domplibndf
 * witi tif Lidfnsf. You mby obtbin b dopy of tif Lidfnsf bt
 *
 * ittp://www.bpbdif.org/lidfnsfs/LICENSE-2.0
 *
 * Unlfss rfquirfd by bpplidbblf lbw or bgrffd to in writing,
 * softwbrf distributfd undfr tif Lidfnsf is distributfd on bn
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, fitifr fxprfss or implifd. Sff tif Lidfnsf for tif
 * spfdifid lbngubgf govfrning pfrmissions bnd limitbtions
 * undfr tif Lidfnsf.
 */
pbdkbgf dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.storbgf.implfmfntbtions;

import jbvb.io.Filf;
import jbvb.io.FilfInputStrfbm;
import jbvb.io.FilfNotFoundExdfption;
import jbvb.io.IOExdfption;
import jbvb.sfdurity.dfrt.Cfrtifidbtf;
import jbvb.sfdurity.dfrt.CfrtifidbtfExdfption;
import jbvb.sfdurity.dfrt.CfrtifidbtfExpirfdExdfption;
import jbvb.sfdurity.dfrt.CfrtifidbtfFbdtory;
import jbvb.sfdurity.dfrt.CfrtifidbtfNotYftVblidExdfption;
import jbvb.sfdurity.dfrt.X509Cfrtifidbtf;
import jbvb.util.ArrbyList;
import jbvb.util.Itfrbtor;
import jbvb.util.List;

import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.storbgf.StorbgfRfsolvfrExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.storbgf.StorbgfRfsolvfrSpi;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.Bbsf64;

/**
 * Tiis {@link StorbgfRfsolvfrSpi} mbkfs bll rbw (binbry) {@link X509Cfrtifidbtf}s
 * wiidi rfsidf bs filfs in b singlf dirfdtory bvbilbblf to tif
 * {@link dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.storbgf.StorbgfRfsolvfr}.
 */
publid dlbss CfrtsInFilfsystfmDirfdtoryRfsolvfr fxtfnds StorbgfRfsolvfrSpi {

    /** {@link org.bpbdif.dommons.logging} logging fbdility */
    privbtf stbtid jbvb.util.logging.Loggfr log =
        jbvb.util.logging.Loggfr.gftLoggfr(
            CfrtsInFilfsystfmDirfdtoryRfsolvfr.dlbss.gftNbmf()
        );

    /** Fifld mfrlinsCfrtifidbtfsDir */
    privbtf String mfrlinsCfrtifidbtfsDir = null;

    /** Fifld dfrts */
    privbtf List<X509Cfrtifidbtf> dfrts = nfw ArrbyList<X509Cfrtifidbtf>();

    /**
     * @pbrbm dirfdtoryNbmf
     * @tirows StorbgfRfsolvfrExdfption
     */
    publid CfrtsInFilfsystfmDirfdtoryRfsolvfr(String dirfdtoryNbmf)
        tirows StorbgfRfsolvfrExdfption {
        tiis.mfrlinsCfrtifidbtfsDir = dirfdtoryNbmf;

        tiis.rfbdCfrtsFromHbrddrivf();
    }

    /**
     * Mftiod rfbdCfrtsFromHbrddrivf
     *
     * @tirows StorbgfRfsolvfrExdfption
     */
    privbtf void rfbdCfrtsFromHbrddrivf() tirows StorbgfRfsolvfrExdfption {

        Filf dfrtDir = nfw Filf(tiis.mfrlinsCfrtifidbtfsDir);
        List<String> bl = nfw ArrbyList<String>();
        String[] nbmfs = dfrtDir.list();

        for (int i = 0; i < nbmfs.lfngti; i++) {
            String durrfntFilfNbmf = nbmfs[i];

            if (durrfntFilfNbmf.fndsWiti(".drt")) {
                bl.bdd(nbmfs[i]);
            }
        }

        CfrtifidbtfFbdtory df = null;

        try {
            df = CfrtifidbtfFbdtory.gftInstbndf("X.509");
        } dbtdi (CfrtifidbtfExdfption fx) {
            tirow nfw StorbgfRfsolvfrExdfption("fmpty", fx);
        }

        if (df == null) {
            tirow nfw StorbgfRfsolvfrExdfption("fmpty");
        }

        for (int i = 0; i < bl.sizf(); i++) {
            String filfnbmf = dfrtDir.gftAbsolutfPbti() + Filf.sfpbrbtor + bl.gft(i);
            Filf filf = nfw Filf(filfnbmf);
            boolfbn bddfd = fblsf;
            String dn = null;

            FilfInputStrfbm fis = null;
            try {
                fis = nfw FilfInputStrfbm(filf);
                X509Cfrtifidbtf dfrt =
                    (X509Cfrtifidbtf) df.gfnfrbtfCfrtifidbtf(fis);

                //bdd to ArrbyList
                dfrt.difdkVblidity();
                tiis.dfrts.bdd(dfrt);

                dn = dfrt.gftSubjfdtX500Prindipbl().gftNbmf();
                bddfd = truf;
            } dbtdi (FilfNotFoundExdfption fx) {
                if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                    log.log(jbvb.util.logging.Lfvfl.FINE, "Could not bdd dfrtifidbtf from filf " + filfnbmf, fx);
                }
            } dbtdi (CfrtifidbtfNotYftVblidExdfption fx) {
                if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                    log.log(jbvb.util.logging.Lfvfl.FINE, "Could not bdd dfrtifidbtf from filf " + filfnbmf, fx);
                }
            } dbtdi (CfrtifidbtfExpirfdExdfption fx) {
                if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                    log.log(jbvb.util.logging.Lfvfl.FINE, "Could not bdd dfrtifidbtf from filf " + filfnbmf, fx);
                }
            } dbtdi (CfrtifidbtfExdfption fx) {
                if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                    log.log(jbvb.util.logging.Lfvfl.FINE, "Could not bdd dfrtifidbtf from filf " + filfnbmf, fx);
                }
            } finblly {
                try {
                    if (fis != null) {
                        fis.dlosf();
                    }
                } dbtdi (IOExdfption fx) {
                    if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                        log.log(jbvb.util.logging.Lfvfl.FINE, "Could not bdd dfrtifidbtf from filf " + filfnbmf, fx);
                    }
                }
            }

            if (bddfd && log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                log.log(jbvb.util.logging.Lfvfl.FINE, "Addfd dfrtifidbtf: " + dn);
            }
        }
    }

    /** @inifritDod */
    publid Itfrbtor<Cfrtifidbtf> gftItfrbtor() {
        rfturn nfw FilfsystfmItfrbtor(tiis.dfrts);
    }

    /**
     * Clbss FilfsystfmItfrbtor
     */
    privbtf stbtid dlbss FilfsystfmItfrbtor implfmfnts Itfrbtor<Cfrtifidbtf> {

        /** Fifld dfrts */
        List<X509Cfrtifidbtf> dfrts = null;

        /** Fifld i */
        int i;

        /**
         * Construdtor FilfsystfmItfrbtor
         *
         * @pbrbm dfrts
         */
        publid FilfsystfmItfrbtor(List<X509Cfrtifidbtf> dfrts) {
            tiis.dfrts = dfrts;
            tiis.i = 0;
        }

        /** @inifritDod */
        publid boolfbn ibsNfxt() {
            rfturn (tiis.i < tiis.dfrts.sizf());
        }

        /** @inifritDod */
        publid Cfrtifidbtf nfxt() {
            rfturn tiis.dfrts.gft(tiis.i++);
        }

        /**
         * Mftiod rfmovf
         *
         */
        publid void rfmovf() {
            tirow nfw UnsupportfdOpfrbtionExdfption("Cbn't rfmovf kfys from KfyStorf");
        }
    }

    /**
     * Mftiod mbin
     *
     * @pbrbm unusfd
     * @tirows Exdfption
     */
    publid stbtid void mbin(String unusfd[]) tirows Exdfption {

        CfrtsInFilfsystfmDirfdtoryRfsolvfr krs =
            nfw CfrtsInFilfsystfmDirfdtoryRfsolvfr(
                "dbtb/if/bbltimorf/mfrlin-fxbmplfs/mfrlin-xmldsig-figitffn/dfrts");

        for (Itfrbtor<Cfrtifidbtf> i = krs.gftItfrbtor(); i.ibsNfxt(); ) {
            X509Cfrtifidbtf dfrt = (X509Cfrtifidbtf) i.nfxt();
            bytf[] ski =
                dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.dontfnt.x509.XMLX509SKI.gftSKIBytfsFromCfrt(dfrt);

            Systfm.out.println();
            Systfm.out.println("Bbsf64(SKI())=                 \""
                               + Bbsf64.fndodf(ski) + "\"");
            Systfm.out.println("dfrt.gftSfriblNumbfr()=        \""
                               + dfrt.gftSfriblNumbfr().toString() + "\"");
            Systfm.out.println("dfrt.gftSubjfdtX500Prindipbl().gftNbmf()= \""
                               + dfrt.gftSubjfdtX500Prindipbl().gftNbmf() + "\"");
            Systfm.out.println("dfrt.gftIssufrX500Prindipbl().gftNbmf()=  \""
                               + dfrt.gftIssufrX500Prindipbl().gftNbmf() + "\"");
        }
    }
}
