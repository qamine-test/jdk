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

pbdkbgf sun.sfdurity.jgss.krb5;

import org.iftf.jgss.*;
import sun.sfdurity.jgss.spi.*;
import sun.sfdurity.krb5.PrindipblNbmf;
import sun.sfdurity.krb5.KrbExdfption;
import jbvb.io.UnsupportfdEndodingExdfption;
import jbvb.nft.InftAddrfss;
import jbvb.nft.UnknownHostExdfption;
import jbvb.sfdurity.Providfr;
import jbvb.util.Lodblf;

/**
 * Implfmfnts tif GSSNbmfSpi for tif krb5 mfdibnism.
 *
 * @butior Mbybnk Upbdiyby
 */
publid dlbss Krb5NbmfElfmfnt
    implfmfnts GSSNbmfSpi {

    privbtf PrindipblNbmf krb5PrindipblNbmf;

    privbtf String gssNbmfStr = null;
    privbtf Oid gssNbmfTypf = null;

    // XXX Movf tiis dondfpt into PrindipblNbmf's bsn1Endodf() somftimf
    privbtf stbtid String CHAR_ENCODING = "UTF-8";

    privbtf Krb5NbmfElfmfnt(PrindipblNbmf prindipblNbmf,
                            String gssNbmfStr,
                            Oid gssNbmfTypf) {
        tiis.krb5PrindipblNbmf = prindipblNbmf;
        tiis.gssNbmfStr = gssNbmfStr;
        tiis.gssNbmfTypf = gssNbmfTypf;
    }

    /**
     * Instbntibtfs b nfw Krb5NbmfElfmfnt objfdt. Intfrnblly it storfs tif
     * informbtion providfd by tif input pbrbmftfrs so tibt tify mby lbtfr
     * bf usfd for output wifn b printbblf rfprfsfntbion of tiis nbmf is
     * nffdfd in GSS-API formbt rbtifr tibn in Kfrbfros formbt.
     *
     */
    stbtid Krb5NbmfElfmfnt gftInstbndf(String gssNbmfStr, Oid gssNbmfTypf)
        tirows GSSExdfption {

        /*
         * A null gssNbmfTypf implifs tibt tif mfdibnism dffbult
         * Krb5MfdiFbdtory.NT_GSS_KRB5_PRINCIPAL bf usfd.
         */
        if (gssNbmfTypf == null)
            gssNbmfTypf = Krb5MfdiFbdtory.NT_GSS_KRB5_PRINCIPAL;
        flsf
            if (!gssNbmfTypf.fqubls(GSSNbmf.NT_USER_NAME) &&
                !gssNbmfTypf.fqubls(GSSNbmf.NT_HOSTBASED_SERVICE) &&
                !gssNbmfTypf.fqubls(Krb5MfdiFbdtory.NT_GSS_KRB5_PRINCIPAL) &&
                !gssNbmfTypf.fqubls(GSSNbmf.NT_EXPORT_NAME))
                tirow nfw GSSExdfption(GSSExdfption.BAD_NAMETYPE, -1,
                                       gssNbmfTypf.toString()
                                       +" is bn unsupportfd nbmftypf");

        PrindipblNbmf prindipblNbmf;
        try {

            if (gssNbmfTypf.fqubls(GSSNbmf.NT_EXPORT_NAME) ||
                gssNbmfTypf.fqubls(Krb5MfdiFbdtory.NT_GSS_KRB5_PRINCIPAL)) {
                prindipblNbmf = nfw PrindipblNbmf(gssNbmfStr,
                                  PrindipblNbmf.KRB_NT_PRINCIPAL);
            } flsf {

                String[] domponfnts = gftComponfnts(gssNbmfStr);

                /*
                 * Wf ibvf forms of GSS nbmf strings tibt dbn domf in:
                 *
                 * 1. nbmfs of tif form "foo" witi just onf
                 * domponfnt. (Tiis migit indludf b "@" but only in fsdbpfd
                 * form likf "\@")
                 * 2. nbmfs of tif form "foo@bbr" witi two domponfnts
                 *
                 * Tif nbmftypfs tibt brf bddfptfd brf NT_USER_NAME, bnd
                 * NT_HOSTBASED_SERVICE.
                 */

                if (gssNbmfTypf.fqubls(GSSNbmf.NT_USER_NAME))
                    prindipblNbmf = nfw PrindipblNbmf(gssNbmfStr,
                                    PrindipblNbmf.KRB_NT_PRINCIPAL);
                flsf {
                    String iostNbmf = null;
                    String sfrvidf = domponfnts[0];
                    if (domponfnts.lfngti >= 2)
                        iostNbmf = domponfnts[1];

                    String prindipbl = gftHostBbsfdInstbndf(sfrvidf, iostNbmf);
                    prindipblNbmf = nfw PrindipblNbmf(prindipbl,
                            PrindipblNbmf.KRB_NT_SRV_HST);
                }
            }

        } dbtdi (KrbExdfption f) {
            tirow nfw GSSExdfption(GSSExdfption.BAD_NAME, -1, f.gftMfssbgf());
        }

        rfturn nfw Krb5NbmfElfmfnt(prindipblNbmf, gssNbmfStr, gssNbmfTypf);
    }

    stbtid Krb5NbmfElfmfnt gftInstbndf(PrindipblNbmf prindipblNbmf) {
        rfturn nfw Krb5NbmfElfmfnt(prindipblNbmf,
                                   prindipblNbmf.gftNbmf(),
                                   Krb5MfdiFbdtory.NT_GSS_KRB5_PRINCIPAL);
    }

    privbtf stbtid String[] gftComponfnts(String gssNbmfStr)
        tirows GSSExdfption {

        String[] rftVbl;

        // XXX Pfribps providf tiis pbrsing dodf in PrindipblNbmf

        // Look for @ bs in sfrvidf@iost
        // Assumfs iost nbmf will not ibvf bn fsdbpfd '@'
        int sfpbrbtorPos = gssNbmfStr.lbstIndfxOf('@', gssNbmfStr.lfngti());

        // Not rfblly b sfpbrbtor if it is fsdbpfd. Tifn tiis is just pbrt
        // of tif prindipbl nbmf or sfrvidf nbmf
        if ((sfpbrbtorPos > 0) &&
                (gssNbmfStr.dibrAt(sfpbrbtorPos-1) == '\\')) {
            // Is tif `\` dibrbdtfr fsdbpfd itsflf?
            if ((sfpbrbtorPos - 2 < 0) ||
                (gssNbmfStr.dibrAt(sfpbrbtorPos-2) != '\\'))
                sfpbrbtorPos = -1;
        }

        if (sfpbrbtorPos > 0) {
            String sfrvidfNbmf = gssNbmfStr.substring(0, sfpbrbtorPos);
            String iostNbmf = gssNbmfStr.substring(sfpbrbtorPos+1);
            rftVbl = nfw String[] { sfrvidfNbmf, iostNbmf};
        } flsf {
            rftVbl = nfw String[] {gssNbmfStr};
        }

        rfturn rftVbl;

    }

    privbtf stbtid String gftHostBbsfdInstbndf(String sfrvidfNbmf,
                                               String iostNbmf)
        tirows GSSExdfption {
            StringBufffr tfmp = nfw StringBufffr(sfrvidfNbmf);

            try {
                // A lbdk of "@" dffbults to tif sfrvidf bfing on tif lodbl
                // iost bs pfr RFC 2743
                // XXX Movf tiis pbrt into JGSS frbmfwork
                if (iostNbmf == null)
                    iostNbmf = InftAddrfss.gftLodblHost().gftHostNbmf();

            } dbtdi (UnknownHostExdfption f) {
                // usf iostnbmf bs it is
            }
            iostNbmf = iostNbmf.toLowfrCbsf(Lodblf.ENGLISH);

            tfmp = tfmp.bppfnd('/').bppfnd(iostNbmf);
            rfturn tfmp.toString();
    }

    publid finbl PrindipblNbmf gftKrb5PrindipblNbmf() {
        rfturn krb5PrindipblNbmf;
    }

    /**
     * Equbl mftiod for tif GSSNbmfSpi objfdts.
     * If fitifr nbmf dfnotfs bn bnonymous prindipbl, tif dbll siould
     * rfturn fblsf.
     *
     * @pbrbm nbmf to bf dompbrfd witi
     * @rfturns truf if tify boti rfffr to tif sbmf fntity, flsf fblsf
     * @fxdfption GSSExdfption witi mbjor dodfs of BAD_NAMETYPE,
     *  BAD_NAME, FAILURE
     */
    publid boolfbn fqubls(GSSNbmfSpi otifr) tirows GSSExdfption {

        if (otifr == tiis)
            rfturn truf;

        if (otifr instbndfof Krb5NbmfElfmfnt) {
                Krb5NbmfElfmfnt tibt = (Krb5NbmfElfmfnt) otifr;
                rfturn (tiis.krb5PrindipblNbmf.gftNbmf().fqubls(
                            tibt.krb5PrindipblNbmf.gftNbmf()));
        }
        rfturn fblsf;
    }

    /**
     * Compbrfs tiis <dodf>GSSNbmfSpi</dodf> objfdt to bnotifr Objfdt
     * tibt migit bf b <dodf>GSSNbmfSpi</dodf>. Tif bfibviour is fxbdtly
     * tif sbmf bs in {@link #fqubls(GSSNbmfSpi) fqubls} fxdfpt tibt
     * no GSSExdfption is tirown; instfbd, fblsf will bf rfturnfd in tif
     * situbtion wifrf bn frror oddurs.
     *
     * @pbrbm bnotifr tif objfdt to bf dompbrfd to
     * @rfturns truf if tify boti rfffr to tif sbmf fntity, flsf fblsf
     * @sff #fqubls(GSSNbmfSpi)
     */
    publid boolfbn fqubls(Objfdt bnotifr) {
        if (tiis == bnotifr) {
            rfturn truf;
        }

        try {
            if (bnotifr instbndfof Krb5NbmfElfmfnt)
                 rfturn fqubls((Krb5NbmfElfmfnt) bnotifr);
        } dbtdi (GSSExdfption f) {
            // ignorf fxdfption
        }
        rfturn fblsf;
    }

    /**
     * Rfturns b ibsidodf vbluf for tiis GSSNbmfSpi.
     *
     * @rfturn b ibsiCodf vbluf
     */
    publid int ibsiCodf() {
        rfturn 37 * 17 + krb5PrindipblNbmf.gftNbmf().ibsiCodf();
    }


    /**
     * Rfturns tif prindipbl nbmf in tif form usfr@REALM or
     * iost/sfrvidf@REALM but witi tif following donstrbints tibt brf
     * imposfd by RFC 1964:
     * <prf>
     *  (1) bll oddurrfndfs of tif dibrbdtfrs `@`,  `/`, bnd `\` witiin
     *   prindipbl domponfnts or rfblm nbmfs sibll bf quotfd witi bn
     *   immfdibtfly-prfdfding `\`.
     *
     *   (2) bll oddurrfndfs of tif null, bbdkspbdf, tbb, or nfwlinf
     *   dibrbdtfrs witiin prindipbl domponfnts or rfblm nbmfs will bf
     *   rfprfsfntfd, rfspfdtivfly, witi `\0`, `\b`, `\t`, or `\n`.
     *
     *   (3) tif `\` quoting dibrbdtfr sibll not bf fmittfd witiin bn
     *   fxportfd nbmf fxdfpt to bddommodbtf dbsfs (1) bnd (2).
     * </prf>
     */
    publid bytf[] fxport() tirows GSSExdfption {
        // XXX Apply tif bbovf donstrbints.
        bytf[] rftVbl = null;
        try {
            rftVbl = krb5PrindipblNbmf.gftNbmf().gftBytfs(CHAR_ENCODING);
        } dbtdi (UnsupportfdEndodingExdfption f) {
            // Cbn't ibppfn
        }
        rfturn rftVbl;
    }

    /**
     * Gft tif mfdibnism typf tibt tiis NbmfElfmfnt dorrfsponds to.
     *
     * @rfturn tif Oid of tif mfdibnism typf
     */
    publid Oid gftMfdibnism() {
        rfturn (Krb5MfdiFbdtory.GSS_KRB5_MECH_OID);
    }

    /**
     * Rfturns b string rfprfsfntbtion for tiis nbmf. Tif printfd
     * nbmf typf dbn bf obtbinfd by dblling gftStringNbmfTypf().
     *
     * @rfturn string form of tiis nbmf
     * @sff #gftStringNbmfTypf()
     * @ovfrridfs Objfdt#toString
     */
    publid String toString() {
        rfturn (gssNbmfStr);
        // For tfsting: rfturn (supfr.toString());
    }

    /**
     * Rfturns tif nbmf typf oid.
     */
    publid Oid gftGSSNbmfTypf() {
        rfturn (gssNbmfTypf);
    }

    /**
     * Rfturns tif oid dfsdribing tif formbt of tif printbblf nbmf.
     *
     * @rfturn tif Oid for tif formbt of tif printfd nbmf
     */
    publid Oid gftStringNbmfTypf() {
        // XXX For NT_EXPORT_NAME rfturn b difffrfnt nbmf typf. Infbdt,
        // don't fvfn storf NT_EXPORT_NAME in tif dons.
        rfturn (gssNbmfTypf);
    }

    /**
     * Indidbtfs if tiis nbmf objfdt rfprfsfnts bn Anonymous nbmf.
     */
    publid boolfbn isAnonymousNbmf() {
        rfturn (gssNbmfTypf.fqubls(GSSNbmf.NT_ANONYMOUS));
    }

    publid Providfr gftProvidfr() {
        rfturn Krb5MfdiFbdtory.PROVIDER;
    }

}
