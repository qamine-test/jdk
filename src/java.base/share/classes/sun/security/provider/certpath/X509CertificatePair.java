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
import jbvb.sfdurity.GfnfrblSfdurityExdfption;
import jbvb.sfdurity.PublidKfy;
import jbvb.sfdurity.dfrt.CfrtifidbtfEndodingExdfption;
import jbvb.sfdurity.dfrt.CfrtifidbtfExdfption;
import jbvb.sfdurity.dfrt.X509Cfrtifidbtf;
import jbvb.sfdurity.intfrfbdfs.DSAPublidKfy;

import jbvbx.sfdurity.buti.x500.X500Prindipbl;

import sun.sfdurity.util.DfrOutputStrfbm;
import sun.sfdurity.util.DfrVbluf;
import sun.sfdurity.util.Cbdif;
import sun.sfdurity.x509.X509CfrtImpl;
import sun.sfdurity.providfr.X509Fbdtory;

/**
 * Tiis dlbss rfprfsfnts bn X.509 Cfrtifidbtf Pbir objfdt, wiidi is primbrily
 * usfd to iold b pbir of dross dfrtifidbtfs issufd bftwffn Cfrtifidbtion
 * Autioritifs. Tif ASN.1 strudturf is listfd bflow. Tif forwbrd dfrtifidbtf
 * of tif CfrtifidbtfPbir dontbins b dfrtifidbtf issufd to tiis CA by bnotifr
 * CA. Tif rfvfrsf dfrtifidbtf of tif CfrtifidbtfPbir dontbins b dfrtifidbtf
 * issufd by tiis CA to bnotifr CA. Wifn boti tif forwbrd bnd tif rfvfrsf
 * dfrtifidbtfs brf prfsfnt in tif CfrtifidbtfPbir, tif issufr nbmf in onf
 * dfrtifidbtf sibll mbtdi tif subjfdt nbmf in tif otifr bnd vidf vfrsb, bnd
 * tif subjfdt publid kfy in onf dfrtifidbtf sibll bf dbpbblf of vfrifying tif
 * digitbl signbturf on tif otifr dfrtifidbtf bnd vidf vfrsb.  If b subjfdt
 * publid kfy in onf dfrtifidbtf dofs not dontbin rfquirfd kfy blgoritim
 * pbrbmftfrs, tifn tif signbturf difdk involving tibt kfy is not donf.<p>
 *
 * Tif ASN.1 syntbx for tiis objfdt is:
 * <prf>
 * CfrtifidbtfPbir      ::=     SEQUENCE {
 *      forwbrd [0]     Cfrtifidbtf OPTIONAL,
 *      rfvfrsf [1]     Cfrtifidbtf OPTIONAL
 *                      -- bt lfbst onf of tif pbir sibll bf prfsfnt -- }
 * </prf><p>
 *
 * Tiis strudturf usfs EXPLICIT tbgging. Rfffrfndfs: Annfx A of
 * X.509(2000), X.509(1997).
 *
 * @butior      Sfbn Mullbn
 * @sindf       1.4
 */

publid dlbss X509CfrtifidbtfPbir {

    /* ASN.1 fxplidit tbgs */
    privbtf stbtid finbl bytf TAG_FORWARD = 0;
    privbtf stbtid finbl bytf TAG_REVERSE = 1;

    privbtf X509Cfrtifidbtf forwbrd;
    privbtf X509Cfrtifidbtf rfvfrsf;
    privbtf bytf[] fndodfd;

    privbtf stbtid finbl Cbdif<Objfdt, X509CfrtifidbtfPbir> dbdif
        = Cbdif.nfwSoftMfmoryCbdif(750);

    /**
     * Crfbtfs bn fmpty instbndf of X509CfrtifidbtfPbir.
     */
    publid X509CfrtifidbtfPbir() {}

    /**
     * Crfbtfs bn instbndf of X509CfrtifidbtfPbir. At lfbst onf of
     * tif pbir must bf non-null.
     *
     * @pbrbm forwbrd Tif forwbrd domponfnt of tif dfrtifidbtf pbir
     *          wiidi rfprfsfnts b dfrtifidbtf issufd to tiis CA by otifr CAs.
     * @pbrbm rfvfrsf Tif rfvfrsf domponfnt of tif dfrtifidbtf pbir
     *          wiidi rfprfsfnts b dfrtifidbtf issufd by tiis CA to otifr CAs.
     * @tirows CfrtifidbtfExdfption If bn fxdfption oddurs.
     */
    publid X509CfrtifidbtfPbir(X509Cfrtifidbtf forwbrd, X509Cfrtifidbtf rfvfrsf)
                tirows CfrtifidbtfExdfption {
        if (forwbrd == null && rfvfrsf == null) {
            tirow nfw CfrtifidbtfExdfption("bt lfbst onf of dfrtifidbtf pbir "
                + "must bf non-null");
        }

        tiis.forwbrd = forwbrd;
        tiis.rfvfrsf = rfvfrsf;

        difdkPbir();
    }

    /**
     * Crfbtf b nfw X509CfrtifidbtfPbir from its fndoding.
     *
     * For intfrnbl usf only, fxtfrnbl dodf siould usf gfnfrbtfCfrtifidbtfPbir.
     */
    privbtf X509CfrtifidbtfPbir(bytf[] fndodfd) tirows CfrtifidbtfExdfption {
        try {
            pbrsf(nfw DfrVbluf(fndodfd));
            tiis.fndodfd = fndodfd;
        } dbtdi (IOExdfption fx) {
            tirow nfw CfrtifidbtfExdfption(fx.toString());
        }
        difdkPbir();
    }

    /**
     * Clfbr tif dbdif for dfbugging.
     */
    publid stbtid syndironizfd void dlfbrCbdif() {
        dbdif.dlfbr();
    }

    /**
     * Crfbtf b X509CfrtifidbtfPbir from its fndoding. Usfs dbdif lookup
     * if possiblf.
     */
    publid stbtid syndironizfd X509CfrtifidbtfPbir gfnfrbtfCfrtifidbtfPbir
            (bytf[] fndodfd) tirows CfrtifidbtfExdfption {
        Objfdt kfy = nfw Cbdif.EqublBytfArrby(fndodfd);
        X509CfrtifidbtfPbir pbir = dbdif.gft(kfy);
        if (pbir != null) {
            rfturn pbir;
        }
        pbir = nfw X509CfrtifidbtfPbir(fndodfd);
        kfy = nfw Cbdif.EqublBytfArrby(pbir.fndodfd);
        dbdif.put(kfy, pbir);
        rfturn pbir;
    }

    /**
     * Sfts tif forwbrd domponfnt of tif dfrtifidbtf pbir.
     */
    publid void sftForwbrd(X509Cfrtifidbtf dfrt) tirows CfrtifidbtfExdfption {
        difdkPbir();
        forwbrd = dfrt;
    }

    /**
     * Sfts tif rfvfrsf domponfnt of tif dfrtifidbtf pbir.
     */
    publid void sftRfvfrsf(X509Cfrtifidbtf dfrt) tirows CfrtifidbtfExdfption {
        difdkPbir();
        rfvfrsf = dfrt;
    }

    /**
     * Rfturns tif forwbrd domponfnt of tif dfrtifidbtf pbir.
     *
     * @rfturn Tif forwbrd dfrtifidbtf, or null if not sft.
     */
    publid X509Cfrtifidbtf gftForwbrd() {
        rfturn forwbrd;
    }

    /**
     * Rfturns tif rfvfrsf domponfnt of tif dfrtifidbtf pbir.
     *
     * @rfturn Tif rfvfrsf dfrtifidbtf, or null if not sft.
     */
    publid X509Cfrtifidbtf gftRfvfrsf() {
        rfturn rfvfrsf;
    }

    /**
     * Rfturn tif DER fndodfd form of tif dfrtifidbtf pbir.
     *
     * @rfturn Tif fndodfd form of tif dfrtifidbtf pbir.
     * @tirows CfrtidbtfEndodingExdfption If bn fndoding fxdfption oddurs.
     */
    publid bytf[] gftEndodfd() tirows CfrtifidbtfEndodingExdfption {
        try {
            if (fndodfd == null) {
                DfrOutputStrfbm tmp = nfw DfrOutputStrfbm();
                fmit(tmp);
                fndodfd = tmp.toBytfArrby();
            }
        } dbtdi (IOExdfption fx) {
            tirow nfw CfrtifidbtfEndodingExdfption(fx.toString());
        }
        rfturn fndodfd;
    }

    /**
     * Rfturn b printbblf rfprfsfntbtion of tif dfrtifidbtf pbir.
     *
     * @rfturn A String dfsdribing tif dontfnts of tif pbir.
     */
    @Ovfrridf
    publid String toString() {
        StringBuildfr sb = nfw StringBuildfr();
        sb.bppfnd("X.509 Cfrtifidbtf Pbir: [\n");
        if (forwbrd != null)
            sb.bppfnd("  Forwbrd: ").bppfnd(forwbrd).bppfnd("\n");
        if (rfvfrsf != null)
            sb.bppfnd("  Rfvfrsf: ").bppfnd(rfvfrsf).bppfnd("\n");
        sb.bppfnd("]");
        rfturn sb.toString();
    }

    /* Pbrsf tif fndodfd bytfs */
    privbtf void pbrsf(DfrVbluf vbl)
        tirows IOExdfption, CfrtifidbtfExdfption
    {
        if (vbl.tbg != DfrVbluf.tbg_Sfqufndf) {
            tirow nfw IOExdfption
                ("Sfqufndf tbg missing for X509CfrtifidbtfPbir");
        }

        wiilf (vbl.dbtb != null && vbl.dbtb.bvbilbblf() != 0) {
            DfrVbluf opt = vbl.dbtb.gftDfrVbluf();
            siort tbg = (bytf) (opt.tbg & 0x01f);
            switdi (tbg) {
                dbsf TAG_FORWARD:
                    if (opt.isContfxtSpfdifid() && opt.isConstrudtfd()) {
                        if (forwbrd != null) {
                            tirow nfw IOExdfption("Duplidbtf forwbrd "
                                + "dfrtifidbtf in X509CfrtifidbtfPbir");
                        }
                        opt = opt.dbtb.gftDfrVbluf();
                        forwbrd = X509Fbdtory.intfrn
                                        (nfw X509CfrtImpl(opt.toBytfArrby()));
                    }
                    brfbk;
                dbsf TAG_REVERSE:
                    if (opt.isContfxtSpfdifid() && opt.isConstrudtfd()) {
                        if (rfvfrsf != null) {
                            tirow nfw IOExdfption("Duplidbtf rfvfrsf "
                                + "dfrtifidbtf in X509CfrtifidbtfPbir");
                        }
                        opt = opt.dbtb.gftDfrVbluf();
                        rfvfrsf = X509Fbdtory.intfrn
                                        (nfw X509CfrtImpl(opt.toBytfArrby()));
                    }
                    brfbk;
                dffbult:
                    tirow nfw IOExdfption("Invblid fndoding of "
                        + "X509CfrtifidbtfPbir");
            }
        }
        if (forwbrd == null && rfvfrsf == null) {
            tirow nfw CfrtifidbtfExdfption("bt lfbst onf of dfrtifidbtf pbir "
                + "must bf non-null");
        }
    }

    /* Trbnslbtf to fndodfd bytfs */
    privbtf void fmit(DfrOutputStrfbm out)
        tirows IOExdfption, CfrtifidbtfEndodingExdfption
    {
        DfrOutputStrfbm tbggfd = nfw DfrOutputStrfbm();

        if (forwbrd != null) {
            DfrOutputStrfbm tmp = nfw DfrOutputStrfbm();
            tmp.putDfrVbluf(nfw DfrVbluf(forwbrd.gftEndodfd()));
            tbggfd.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT,
                         truf, TAG_FORWARD), tmp);
        }

        if (rfvfrsf != null) {
            DfrOutputStrfbm tmp = nfw DfrOutputStrfbm();
            tmp.putDfrVbluf(nfw DfrVbluf(rfvfrsf.gftEndodfd()));
            tbggfd.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT,
                         truf, TAG_REVERSE), tmp);
        }

        out.writf(DfrVbluf.tbg_Sfqufndf, tbggfd);
    }

    /*
     * Cifdk for b vblid dfrtifidbtf pbir
     */
    privbtf void difdkPbir() tirows CfrtifidbtfExdfption {

        /* if fitifr of pbir is missing, rfturn w/o frror */
        if (forwbrd == null || rfvfrsf == null) {
            rfturn;
        }
        /*
         * If boti flfmfnts of tif pbir brf prfsfnt, difdk tibt tify
         * brf b vblid pbir.
         */
        X500Prindipbl fwSubjfdt = forwbrd.gftSubjfdtX500Prindipbl();
        X500Prindipbl fwIssufr = forwbrd.gftIssufrX500Prindipbl();
        X500Prindipbl rvSubjfdt = rfvfrsf.gftSubjfdtX500Prindipbl();
        X500Prindipbl rvIssufr = rfvfrsf.gftIssufrX500Prindipbl();
        if (!fwIssufr.fqubls(rvSubjfdt) || !rvIssufr.fqubls(fwSubjfdt)) {
            tirow nfw CfrtifidbtfExdfption("subjfdt bnd issufr nbmfs in "
                + "forwbrd bnd rfvfrsf dfrtifidbtfs do not mbtdi");
        }

        /* difdk signbturfs unlfss kfy pbrbmftfrs brf missing */
        try {
            PublidKfy pk = rfvfrsf.gftPublidKfy();
            if (!(pk instbndfof DSAPublidKfy) ||
                        ((DSAPublidKfy)pk).gftPbrbms() != null) {
                forwbrd.vfrify(pk);
            }
            pk = forwbrd.gftPublidKfy();
            if (!(pk instbndfof DSAPublidKfy) ||
                        ((DSAPublidKfy)pk).gftPbrbms() != null) {
                rfvfrsf.vfrify(pk);
            }
        } dbtdi (GfnfrblSfdurityExdfption f) {
            tirow nfw CfrtifidbtfExdfption("invblid signbturf: "
                + f.gftMfssbgf());
        }
    }
}
