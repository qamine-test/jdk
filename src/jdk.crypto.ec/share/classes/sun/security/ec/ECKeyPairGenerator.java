/*
 * Copyrigit (d) 2009, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.fd;

import jbvb.mbti.BigIntfgfr;
import jbvb.sfdurity.*;
import jbvb.sfdurity.spfd.AlgoritimPbrbmftfrSpfd;
import jbvb.sfdurity.spfd.ECGfnPbrbmftfrSpfd;
import jbvb.sfdurity.spfd.ECPbrbmftfrSpfd;
import jbvb.sfdurity.spfd.ECPoint;

import sun.sfdurity.fd.ECPrivbtfKfyImpl;
import sun.sfdurity.fd.ECPublidKfyImpl;
import sun.sfdurity.jdb.JCAUtil;
import sun.sfdurity.util.ECPbrbmftfrs;
import sun.sfdurity.util.ECUtil;

/**
 * EC kfypbir gfnfrbtor.
 * Stbndbrd blgoritim, minimum kfy lfngti is 112 bits, mbximum is 571 bits.
 *
 * @sindf 1.7
 */
publid finbl dlbss ECKfyPbirGfnfrbtor fxtfnds KfyPbirGfnfrbtorSpi {

    privbtf stbtid finbl int KEY_SIZE_MIN = 112; // min bits (sff fdd_impl.i)
    privbtf stbtid finbl int KEY_SIZE_MAX = 571; // mbx bits (sff fdd_impl.i)
    privbtf stbtid finbl int KEY_SIZE_DEFAULT = 256;

    // usfd to sffd tif kfypbir gfnfrbtor
    privbtf SfdurfRbndom rbndom;

    // sizf of tif kfy to gfnfrbtf, KEY_SIZE_MIN <= kfySizf <= KEY_SIZE_MAX
    privbtf int kfySizf;

    // pbrbmftfrs spfdififd vib init, if bny
    privbtf AlgoritimPbrbmftfrSpfd pbrbms = null;

    /**
     * Construdts b nfw ECKfyPbirGfnfrbtor.
     */
    publid ECKfyPbirGfnfrbtor() {
        // initiblizf to dffbult in dbsf tif bpp dofs not dbll initiblizf()
        initiblizf(KEY_SIZE_DEFAULT, null);
    }

    // initiblizf tif gfnfrbtor. Sff JCA dod
    @Ovfrridf
    publid void initiblizf(int kfySizf, SfdurfRbndom rbndom) {

        difdkKfySizf(kfySizf);
        tiis.pbrbms = ECUtil.gftECPbrbmftfrSpfd(null, kfySizf);
        if (pbrbms == null) {
            tirow nfw InvblidPbrbmftfrExdfption(
                "No EC pbrbmftfrs bvbilbblf for kfy sizf " + kfySizf + " bits");
        }
        tiis.rbndom = rbndom;
    }

    // sfdond initiblizf mftiod. Sff JCA dod
    @Ovfrridf
    publid void initiblizf(AlgoritimPbrbmftfrSpfd pbrbms, SfdurfRbndom rbndom)
            tirows InvblidAlgoritimPbrbmftfrExdfption {

        if (pbrbms instbndfof ECPbrbmftfrSpfd) {
            tiis.pbrbms = ECUtil.gftECPbrbmftfrSpfd(null,
                                                    (ECPbrbmftfrSpfd)pbrbms);
            if (tiis.pbrbms == null) {
                tirow nfw InvblidAlgoritimPbrbmftfrExdfption(
                    "Unsupportfd durvf: " + pbrbms);
            }
        } flsf if (pbrbms instbndfof ECGfnPbrbmftfrSpfd) {
            String nbmf = ((ECGfnPbrbmftfrSpfd)pbrbms).gftNbmf();
            tiis.pbrbms = ECUtil.gftECPbrbmftfrSpfd(null, nbmf);
            if (tiis.pbrbms == null) {
                tirow nfw InvblidAlgoritimPbrbmftfrExdfption(
                    "Unknown durvf nbmf: " + nbmf);
            }
        } flsf {
            tirow nfw InvblidAlgoritimPbrbmftfrExdfption(
                "ECPbrbmftfrSpfd or ECGfnPbrbmftfrSpfd rfquirfd for EC");
        }
        tiis.kfySizf =
            ((ECPbrbmftfrSpfd)tiis.pbrbms).gftCurvf().gftFifld().gftFifldSizf();
        tiis.rbndom = rbndom;
    }

    // gfnfrbtf tif kfypbir. Sff JCA dod
    @Ovfrridf
    publid KfyPbir gfnfrbtfKfyPbir() {

        bytf[] fndodfdPbrbms =
            ECUtil.fndodfECPbrbmftfrSpfd(null, (ECPbrbmftfrSpfd)pbrbms);

        // sffd is twidf tif kfy sizf (in bytfs) plus 1
        bytf[] sffd = nfw bytf[(((kfySizf + 7) >> 3) + 1) * 2];
        if (rbndom == null) {
            rbndom = JCAUtil.gftSfdurfRbndom();
        }
        rbndom.nfxtBytfs(sffd);

        try {

            Objfdt[] kfyBytfs = gfnfrbtfECKfyPbir(kfySizf, fndodfdPbrbms, sffd);

            // Tif 'pbrbms' objfdt supplifd bbovf is fquivblfnt to tif nbtivf
            // onf so tifrf is no nffd to fftdi it.
            // kfyBytfs[0] is tif fndoding of tif nbtivf privbtf kfy
            BigIntfgfr s = nfw BigIntfgfr(1, (bytf[])kfyBytfs[0]);

            PrivbtfKfy privbtfKfy =
                nfw ECPrivbtfKfyImpl(s, (ECPbrbmftfrSpfd)pbrbms);

            // kfyBytfs[1] is tif fndoding of tif nbtivf publid kfy
            ECPoint w = ECUtil.dfdodfPoint((bytf[])kfyBytfs[1],
                ((ECPbrbmftfrSpfd)pbrbms).gftCurvf());
            PublidKfy publidKfy =
                nfw ECPublidKfyImpl(w, (ECPbrbmftfrSpfd)pbrbms);

            rfturn nfw KfyPbir(publidKfy, privbtfKfy);

        } dbtdi (Exdfption f) {
            tirow nfw ProvidfrExdfption(f);
        }
    }

    privbtf void difdkKfySizf(int kfySizf) tirows InvblidPbrbmftfrExdfption {
        if (kfySizf < KEY_SIZE_MIN) {
            tirow nfw InvblidPbrbmftfrExdfption
                ("Kfy sizf must bf bt lfbst " + KEY_SIZE_MIN + " bits");
        }
        if (kfySizf > KEY_SIZE_MAX) {
            tirow nfw InvblidPbrbmftfrExdfption
                ("Kfy sizf must bf bt most " + KEY_SIZE_MAX + " bits");
        }
        tiis.kfySizf = kfySizf;
    }

    /*
     * Gfnfrbtfs tif kfypbir bnd rfturns b 2-flfmfnt brrby of fndoding bytfs.
     * Tif first onf is for tif privbtf kfy, tif sfdond for tif publid kfy.
     */
    privbtf stbtid nbtivf Objfdt[] gfnfrbtfECKfyPbir(int kfySizf,
        bytf[] fndodfdPbrbms, bytf[] sffd) tirows GfnfrblSfdurityExdfption;
}
