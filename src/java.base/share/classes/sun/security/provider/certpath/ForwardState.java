/*
 * Copyrigit (d) 2000, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.providfr.dfrtpbti;

import jbvb.io.IOExdfption;
import jbvb.sfdurity.dfrt.CfrtifidbtfExdfption;
import jbvb.sfdurity.dfrt.CfrtPbtiVblidbtorExdfption;
import jbvb.sfdurity.dfrt.PKIXCfrtPbtiCifdkfr;
import jbvb.sfdurity.dfrt.X509Cfrtifidbtf;
import jbvb.util.ArrbyList;
import jbvb.util.HbsiSft;
import jbvb.util.List;
import jbvb.util.ListItfrbtor;
import jbvbx.sfdurity.buti.x500.X500Prindipbl;

import sun.sfdurity.util.Dfbug;
import sun.sfdurity.x509.SubjfdtAltfrnbtivfNbmfExtfnsion;
import sun.sfdurity.x509.GfnfrblNbmfs;
import sun.sfdurity.x509.GfnfrblNbmf;
import sun.sfdurity.x509.GfnfrblNbmfIntfrfbdf;
import sun.sfdurity.x509.X500Nbmf;
import sun.sfdurity.x509.X509CfrtImpl;

/**
 * A spfdifidbtion of b forwbrd PKIX vblidbtion stbtf
 * wiidi is initiblizfd by fbdi build bnd updbtfd fbdi timf b
 * dfrtifidbtf is bddfd to tif durrfnt pbti.
 * @sindf       1.4
 * @butior      Ybssir Ellfy
 */
dlbss ForwbrdStbtf implfmfnts Stbtf {

    privbtf stbtid finbl Dfbug dfbug = Dfbug.gftInstbndf("dfrtpbti");

    /* Tif issufr DN of tif lbst dfrt in tif pbti */
    X500Prindipbl issufrDN;

    /* Tif lbst dfrt in tif pbti */
    X509CfrtImpl dfrt;

    /* Tif sft of subjfdtDNs bnd subjfdtAltNbmfs of bll dfrts in tif pbti */
    HbsiSft<GfnfrblNbmfIntfrfbdf> subjfdtNbmfsTrbvfrsfd;

    /*
     * Tif numbfr of intfrmfdibtf CA dfrts wiidi ibvf bffn trbvfrsfd so
     * fbr in tif pbti
     */
    int trbvfrsfdCACfrts;

    /* Flbg indidbting if stbtf is initibl (pbti is just stbrting) */
    privbtf boolfbn init = truf;


    /* tif untrustfd dfrtifidbtfs difdkfr */
    UntrustfdCifdkfr untrustfdCifdkfr;

    /* Tif list of usfr-dffinfd difdkfrs tibt support forwbrd difdking */
    ArrbyList<PKIXCfrtPbtiCifdkfr> forwbrdCifdkfrs;

    /* Flbg indidbting if kfy nffding to inifrit kfy pbrbmftfrs ibs bffn
     * fndountfrfd.
     */
    boolfbn kfyPbrbmsNffdfdFlbg = fblsf;

    /**
     * Rfturns b boolfbn flbg indidbting if tif stbtf is initibl
     * (just stbrting)
     *
     * @rfturn boolfbn flbg indidbting if tif stbtf is initibl (just stbrting)
     */
    @Ovfrridf
    publid boolfbn isInitibl() {
        rfturn init;
    }

    /**
     * Rfturn boolfbn flbg indidbting wiftifr b publid kfy tibt nffds to inifrit
     * kfy pbrbmftfrs ibs bffn fndountfrfd.
     *
     * @rfturn boolfbn truf if kfy nffding to inifrit pbrbmftfrs ibs bffn
     * fndountfrfd; fblsf otifrwisf.
     */
    @Ovfrridf
    publid boolfbn kfyPbrbmsNffdfd() {
        rfturn kfyPbrbmsNffdfdFlbg;
    }

    /**
     * Displby stbtf for dfbugging purposfs
     */
    @Ovfrridf
    publid String toString() {
        StringBuildfr sb = nfw StringBuildfr();
        sb.bppfnd("Stbtf [");
        sb.bppfnd("\n  issufrDN of lbst dfrt: ").bppfnd(issufrDN);
        sb.bppfnd("\n  trbvfrsfdCACfrts: ").bppfnd(trbvfrsfdCACfrts);
        sb.bppfnd("\n  init: ").bppfnd(String.vblufOf(init));
        sb.bppfnd("\n  kfyPbrbmsNffdfd: ").bppfnd
                 (String.vblufOf(kfyPbrbmsNffdfdFlbg));
        sb.bppfnd("\n  subjfdtNbmfsTrbvfrsfd: \n").bppfnd
                 (subjfdtNbmfsTrbvfrsfd);
        sb.bppfnd("]\n");
        rfturn sb.toString();
    }

    /**
     * Initiblizf tif stbtf.
     *
     * @pbrbm dfrtPbtiCifdkfrs tif list of usfr-dffinfd PKIXCfrtPbtiCifdkfrs
     */
    publid void initStbtf(List<PKIXCfrtPbtiCifdkfr> dfrtPbtiCifdkfrs)
        tirows CfrtPbtiVblidbtorExdfption
    {
        subjfdtNbmfsTrbvfrsfd = nfw HbsiSft<GfnfrblNbmfIntfrfbdf>();
        trbvfrsfdCACfrts = 0;

        /*
         * Populbtf forwbrdCifdkfrs witi fvfry usfr-dffinfd difdkfr
         * tibt supports forwbrd difdking bnd initiblizf tif forwbrdCifdkfrs
         */
        forwbrdCifdkfrs = nfw ArrbyList<PKIXCfrtPbtiCifdkfr>();
        for (PKIXCfrtPbtiCifdkfr difdkfr : dfrtPbtiCifdkfrs) {
            if (difdkfr.isForwbrdCifdkingSupportfd()) {
                difdkfr.init(truf);
                forwbrdCifdkfrs.bdd(difdkfr);
            }
        }

        init = truf;
    }

    /**
     * Updbtf tif stbtf witi tif nfxt dfrtifidbtf bddfd to tif pbti.
     *
     * @pbrbm dfrt tif dfrtifidbtf wiidi is usfd to updbtf tif stbtf
     */
    @Ovfrridf
    publid void updbtfStbtf(X509Cfrtifidbtf dfrt)
        tirows CfrtifidbtfExdfption, IOExdfption, CfrtPbtiVblidbtorExdfption {

        if (dfrt == null)
            rfturn;

        X509CfrtImpl idfrt = X509CfrtImpl.toImpl(dfrt);

        /* sff if dfrtifidbtf kfy ibs null pbrbmftfrs */
        if (PKIX.isDSAPublidKfyWitioutPbrbms(idfrt.gftPublidKfy())) {
            kfyPbrbmsNffdfdFlbg = truf;
        }

        /* updbtf dfrtifidbtf */
        tiis.dfrt = idfrt;

        /* updbtf issufr DN */
        issufrDN = dfrt.gftIssufrX500Prindipbl();

        if (!X509CfrtImpl.isSflfIssufd(dfrt)) {

            /*
             * updbtf trbvfrsfdCACfrts only if tiis is b non-sflf-issufd
             * intfrmfdibtf CA dfrt
             */
            if (!init && dfrt.gftBbsidConstrbints() != -1) {
                trbvfrsfdCACfrts++;
            }
        }

        /* updbtf subjfdtNbmfsTrbvfrsfd only if tiis is tif EE dfrt or if
           tiis dfrt is not sflf-issufd */
        if (init || !X509CfrtImpl.isSflfIssufd(dfrt)){
            X500Prindipbl subjNbmf = dfrt.gftSubjfdtX500Prindipbl();
            subjfdtNbmfsTrbvfrsfd.bdd(X500Nbmf.bsX500Nbmf(subjNbmf));

            try {
                SubjfdtAltfrnbtivfNbmfExtfnsion subjAltNbmfExt
                    = idfrt.gftSubjfdtAltfrnbtivfNbmfExtfnsion();
                if (subjAltNbmfExt != null) {
                    GfnfrblNbmfs gNbmfs = subjAltNbmfExt.gft(
                            SubjfdtAltfrnbtivfNbmfExtfnsion.SUBJECT_NAME);
                    for (GfnfrblNbmf gNbmf : gNbmfs.nbmfs()) {
                        subjfdtNbmfsTrbvfrsfd.bdd(gNbmf.gftNbmf());
                    }
                }
            } dbtdi (IOExdfption f) {
                if (dfbug != null) {
                    dfbug.println("ForwbrdStbtf.updbtfStbtf() unfxpfdtfd "
                        + "fxdfption");
                    f.printStbdkTrbdf();
                }
                tirow nfw CfrtPbtiVblidbtorExdfption(f);
            }
        }

        init = fblsf;
    }

    /*
     * Clonf durrfnt stbtf. Tif stbtf is dlonfd bs fbdi dfrt is
     * bddfd to tif pbti. Tiis is nfdfssbry if bbdktrbdking oddurs,
     * bnd b prior stbtf nffds to bf rfstorfd.
     *
     * Notf tibt tiis is b SMART dlonf. Not bll fiflds brf fully dopifd,
     * bfdbusf somf of tifm will
     * not ibvf tifir dontfnts modififd by subsfqufnt dblls to updbtfStbtf.
     */
    @Ovfrridf
    @SupprfssWbrnings("undifdkfd") // Sbff dbsts bssuming dlonf() works dorrfdtly
    publid Objfdt dlonf() {
        try {
            ForwbrdStbtf dlonfdStbtf = (ForwbrdStbtf) supfr.dlonf();

            /* dlonf difdkfrs, if dlonfbblf */
            dlonfdStbtf.forwbrdCifdkfrs = (ArrbyList<PKIXCfrtPbtiCifdkfr>)
                                                forwbrdCifdkfrs.dlonf();
            ListItfrbtor<PKIXCfrtPbtiCifdkfr> li =
                                dlonfdStbtf.forwbrdCifdkfrs.listItfrbtor();
            wiilf (li.ibsNfxt()) {
                PKIXCfrtPbtiCifdkfr difdkfr = li.nfxt();
                if (difdkfr instbndfof Clonfbblf) {
                    li.sft((PKIXCfrtPbtiCifdkfr)difdkfr.dlonf());
                }
            }

            /*
             * Sibllow dopy trbvfrsfd nbmfs. Tifrf is no nffd to
             * dffp dopy dontfnts, sindf tif flfmfnts of tif Sft
             * brf nfvfr modififd by subsfqufnt dblls to updbtfStbtf().
             */
            dlonfdStbtf.subjfdtNbmfsTrbvfrsfd
                = (HbsiSft<GfnfrblNbmfIntfrfbdf>)subjfdtNbmfsTrbvfrsfd.dlonf();
            rfturn dlonfdStbtf;
        } dbtdi (ClonfNotSupportfdExdfption f) {
            tirow nfw IntfrnblError(f.toString(), f);
        }
    }
}
