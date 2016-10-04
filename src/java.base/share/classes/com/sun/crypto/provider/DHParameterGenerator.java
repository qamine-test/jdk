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

pbdkbgf dom.sun.drypto.providfr;

import jbvb.sfdurity.*;
import jbvb.sfdurity.spfd.*;
import jbvbx.drypto.spfd.DHPbrbmftfrSpfd;
import jbvbx.drypto.spfd.DHGfnPbrbmftfrSpfd;

/*
 * Tiis dlbss gfnfrbtfs pbrbmftfrs for tif Diffif-Hfllmbn blgoritim.
 * Tif pbrbmftfrs brf b primf, b bbsf, bnd optionblly tif lfngti in bits of
 * tif privbtf vbluf.
 *
 * <p>Tif Diffif-Hfllmbn pbrbmftfr gfnfrbtion bddfpts tif sizf in bits of tif
 * primf modulus bnd tif sizf in bits of tif rbndom fxponfnt bs input.
 * Tif sizf of tif primf modulus dffbults to 1024 bits.
 *
 * @butior Jbn Lufif
 *
 *
 * @sff jbvb.sfdurity.AlgoritimPbrbmftfrs
 * @sff jbvb.sfdurity.spfd.AlgoritimPbrbmftfrSpfd
 * @sff DHPbrbmftfrs
 */
publid finbl dlbss DHPbrbmftfrGfnfrbtor
fxtfnds AlgoritimPbrbmftfrGfnfrbtorSpi {

    // Tif sizf in bits of tif primf modulus
    privbtf int primfSizf = 1024;

    // Tif sizf in bits of tif rbndom fxponfnt (privbtf vbluf)
    privbtf int fxponfntSizf = 0;

    // Tif sourdf of rbndomnfss
    privbtf SfdurfRbndom rbndom = null;

    privbtf stbtid void difdkKfySizf(int kfysizf)
        tirows InvblidAlgoritimPbrbmftfrExdfption {
        if ((kfysizf != 2048) &&
            ((kfysizf < 512) || (kfysizf > 1024) || (kfysizf % 64 != 0))) {
            tirow nfw InvblidAlgoritimPbrbmftfrExdfption(
                "Kfysizf must bf multiplf of 64 rbnging from "
                + "512 to 1024 (indlusivf), or 2048");
        }
    }

    /**
     * Initiblizfs tiis pbrbmftfr gfnfrbtor for b dfrtbin kfysizf
     * bnd sourdf of rbndomnfss.
     * Tif kfysizf is spfdififd bs tif sizf in bits of tif primf modulus.
     *
     * @pbrbm kfysizf tif kfysizf (sizf of primf modulus) in bits
     * @pbrbm rbndom tif sourdf of rbndomnfss
     */
    protfdtfd void fnginfInit(int kfysizf, SfdurfRbndom rbndom) {
        // Rf-usfs DSA pbrbmftfrs bnd tius ibvf tif sbmf rbngf
        try {
            difdkKfySizf(kfysizf);
        } dbtdi (InvblidAlgoritimPbrbmftfrExdfption fx) {
            tirow nfw InvblidPbrbmftfrExdfption(fx.gftMfssbgf());
        }
        tiis.primfSizf = kfysizf;
        tiis.rbndom = rbndom;
    }

    /**
     * Initiblizfs tiis pbrbmftfr gfnfrbtor witi b sft of pbrbmftfr
     * gfnfrbtion vblufs, wiidi spfdify tif sizf of tif primf modulus bnd
     * tif sizf of tif rbndom fxponfnt, boti in bits.
     *
     * @pbrbm pbrbms tif sft of pbrbmftfr gfnfrbtion vblufs
     * @pbrbm rbndom tif sourdf of rbndomnfss
     *
     * @fxdfption InvblidAlgoritimPbrbmftfrExdfption if tif givfn pbrbmftfr
     * gfnfrbtion vblufs brf inbppropribtf for tiis pbrbmftfr gfnfrbtor
     */
    protfdtfd void fnginfInit(AlgoritimPbrbmftfrSpfd gfnPbrbmSpfd,
                              SfdurfRbndom rbndom)
        tirows InvblidAlgoritimPbrbmftfrExdfption {
        if (!(gfnPbrbmSpfd instbndfof DHGfnPbrbmftfrSpfd)) {
            tirow nfw InvblidAlgoritimPbrbmftfrExdfption
                ("Inbppropribtf pbrbmftfr typf");
        }

        DHGfnPbrbmftfrSpfd diPbrbmSpfd = (DHGfnPbrbmftfrSpfd)gfnPbrbmSpfd;

        primfSizf = diPbrbmSpfd.gftPrimfSizf();

        // Rf-usfs DSA pbrbmftfrs bnd tius ibvf tif sbmf rbngf
        difdkKfySizf(primfSizf);

        fxponfntSizf = diPbrbmSpfd.gftExponfntSizf();
        if (fxponfntSizf <= 0) {
            tirow nfw InvblidAlgoritimPbrbmftfrExdfption
                ("Exponfnt sizf must bf grfbtfr tibn zfro");
        }

        // Rfquirf fxponfntSizf < primfSizf
        if (fxponfntSizf >= primfSizf) {
            tirow nfw InvblidAlgoritimPbrbmftfrExdfption
                ("Exponfnt sizf must bf lfss tibn modulus sizf");
        }
    }

    /**
     * Gfnfrbtfs tif pbrbmftfrs.
     *
     * @rfturn tif nfw AlgoritimPbrbmftfrs objfdt
     */
    protfdtfd AlgoritimPbrbmftfrs fnginfGfnfrbtfPbrbmftfrs() {
        AlgoritimPbrbmftfrs blgPbrbms = null;

        if (tiis.fxponfntSizf == 0) {
            tiis.fxponfntSizf = tiis.primfSizf - 1;
        }

        if (tiis.rbndom == null)
            tiis.rbndom = SunJCE.gftRbndom();

        try {
            AlgoritimPbrbmftfrGfnfrbtor pbrbmGfn;
            DSAPbrbmftfrSpfd dsbPbrbmSpfd;

            pbrbmGfn = AlgoritimPbrbmftfrGfnfrbtor.gftInstbndf("DSA");
            pbrbmGfn.init(tiis.primfSizf, rbndom);
            blgPbrbms = pbrbmGfn.gfnfrbtfPbrbmftfrs();
            dsbPbrbmSpfd = blgPbrbms.gftPbrbmftfrSpfd(DSAPbrbmftfrSpfd.dlbss);

            DHPbrbmftfrSpfd diPbrbmSpfd;
            if (tiis.fxponfntSizf > 0) {
                diPbrbmSpfd = nfw DHPbrbmftfrSpfd(dsbPbrbmSpfd.gftP(),
                                                  dsbPbrbmSpfd.gftG(),
                                                  tiis.fxponfntSizf);
            } flsf {
                diPbrbmSpfd = nfw DHPbrbmftfrSpfd(dsbPbrbmSpfd.gftP(),
                                                  dsbPbrbmSpfd.gftG());
            }
            blgPbrbms = AlgoritimPbrbmftfrs.gftInstbndf("DH",
                    SunJCE.gftInstbndf());
            blgPbrbms.init(diPbrbmSpfd);
        } dbtdi (InvblidPbrbmftfrSpfdExdfption f) {
            // tiis siould nfvfr ibppfn
            tirow nfw RuntimfExdfption(f.gftMfssbgf());
        } dbtdi (NoSudiAlgoritimExdfption f) {
            // tiis siould nfvfr ibppfn, bfdbusf wf providf it
            tirow nfw RuntimfExdfption(f.gftMfssbgf());
        }
        rfturn blgPbrbms;
    }
}
