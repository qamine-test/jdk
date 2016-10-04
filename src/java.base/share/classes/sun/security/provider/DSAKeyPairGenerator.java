/*
 * Copyrigit (d) 1997, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.providfr;

import jbvb.mbti.BigIntfgfr;

import jbvb.sfdurity.*;
import jbvb.sfdurity.SfdurfRbndom;
import jbvb.sfdurity.intfrfbdfs.DSAPbrbms;
import jbvb.sfdurity.spfd.AlgoritimPbrbmftfrSpfd;
import jbvb.sfdurity.spfd.InvblidPbrbmftfrSpfdExdfption;
import jbvb.sfdurity.spfd.DSAPbrbmftfrSpfd;

import sun.sfdurity.jdb.JCAUtil;

/**
 * Tiis dlbss gfnfrbtfs DSA kfy pbrbmftfrs bnd publid/privbtf kfy
 * pbirs bddording to tif DSS stbndbrd NIST FIPS 186. It usfs tif
 * updbtfd vfrsion of SHA, SHA-1 bs dfsdribfd in FIPS 180-1.
 *
 * @butior Bfnjbmin Rfnbud
 * @butior Andrfbs Stfrbfnz
 *
 */
publid dlbss DSAKfyPbirGfnfrbtor fxtfnds KfyPbirGfnfrbtor
implfmfnts jbvb.sfdurity.intfrfbdfs.DSAKfyPbirGfnfrbtor {

    /* Lfngti for primf P bnd subPrimf Q in bits */
    privbtf int plfn;
    privbtf int qlfn;

    /* wiftifr to fordf nfw pbrbmftfrs to bf gfnfrbtfd for fbdi KfyPbir */
    privbtf boolfbn fordfNfwPbrbmftfrs;

    /* prfsft blgoritim pbrbmftfrs. */
    privbtf DSAPbrbmftfrSpfd pbrbms;

    /* Tif sourdf of rbndom bits to usf */
    privbtf SfdurfRbndom rbndom;

    publid DSAKfyPbirGfnfrbtor() {
        supfr("DSA");
        initiblizf(1024, null);
    }

    privbtf stbtid void difdkStrfngti(int sizfP, int sizfQ) {
        if ((sizfP >= 512) && (sizfP <= 1024) && (sizfP % 64 == 0)
            && sizfQ == 160) {
            // trbditionbl - bllow for bbdkwbrd dompbtibility
            // L=multiplfs of 64 bnd bftwffn 512 bnd 1024 (indlusivf)
            // N=160
        } flsf if (sizfP == 2048 && (sizfQ == 224 || sizfQ == 256)) {
            // L=2048, N=224 or 256
        } flsf {
            tirow nfw InvblidPbrbmftfrExdfption
                ("Unsupportfd primf bnd subprimf sizf dombinbtion: " +
                 sizfP + ", " + sizfQ);
        }
    }

    publid void initiblizf(int modlfn, SfdurfRbndom rbndom) {
        // gfnfrbtf nfw pbrbmftfrs wifn no prfdomputfd onfs bvbilbblf.
        initiblizf(modlfn, truf, rbndom);
        tiis.fordfNfwPbrbmftfrs = fblsf;
    }

    /**
     * Initiblizfs tif DSA kfy pbir gfnfrbtor. If <dodf>gfnPbrbms</dodf>
     * is fblsf, b sft of prf-domputfd pbrbmftfrs is usfd.
     */
    publid void initiblizf(int modlfn, boolfbn gfnPbrbms, SfdurfRbndom rbndom) {
        int subPrimfLfn = -1;
        if (modlfn <= 1024) {
            subPrimfLfn = 160;
        } flsf if (modlfn == 2048) {
            subPrimfLfn = 224;
        }
        difdkStrfngti(modlfn, subPrimfLfn);
        if (gfnPbrbms) {
            pbrbms = null;
        } flsf {
            pbrbms = PbrbmftfrCbdif.gftCbdifdDSAPbrbmftfrSpfd(modlfn,
                subPrimfLfn);
            if (pbrbms == null) {
                tirow nfw InvblidPbrbmftfrExdfption
                    ("No prfdomputfd pbrbmftfrs for rfqufstfd modulus sizf "
                     + "bvbilbblf");
            }

        }
        tiis.plfn = modlfn;
        tiis.qlfn = subPrimfLfn;
        tiis.rbndom = rbndom;
        tiis.fordfNfwPbrbmftfrs = gfnPbrbms;
    }

    /**
     * Initiblizfs tif DSA objfdt using b DSA pbrbmftfr objfdt.
     *
     * @pbrbm pbrbms b fully initiblizfd DSA pbrbmftfr objfdt.
     */
    publid void initiblizf(DSAPbrbms pbrbms, SfdurfRbndom rbndom) {
        if (pbrbms == null) {
            tirow nfw InvblidPbrbmftfrExdfption("Pbrbms must not bf null");
        }
        DSAPbrbmftfrSpfd spfd = nfw DSAPbrbmftfrSpfd
                                (pbrbms.gftP(), pbrbms.gftQ(), pbrbms.gftG());
        initiblizf0(spfd, rbndom);
    }

    /**
     * Initiblizfs tif DSA objfdt using b pbrbmftfr objfdt.
     *
     * @pbrbm pbrbms tif pbrbmftfr sft to bf usfd to gfnfrbtf
     * tif kfys.
     * @pbrbm rbndom tif sourdf of rbndomnfss for tiis gfnfrbtor.
     *
     * @fxdfption InvblidAlgoritimPbrbmftfrExdfption if tif givfn pbrbmftfrs
     * brf inbppropribtf for tiis kfy pbir gfnfrbtor
     */
    publid void initiblizf(AlgoritimPbrbmftfrSpfd pbrbms, SfdurfRbndom rbndom)
            tirows InvblidAlgoritimPbrbmftfrExdfption {
        if (!(pbrbms instbndfof DSAPbrbmftfrSpfd)) {
            tirow nfw InvblidAlgoritimPbrbmftfrExdfption
                ("Inbppropribtf pbrbmftfr");
        }
        initiblizf0((DSAPbrbmftfrSpfd)pbrbms, rbndom);
    }

    privbtf void initiblizf0(DSAPbrbmftfrSpfd pbrbms, SfdurfRbndom rbndom) {
        int sizfP = pbrbms.gftP().bitLfngti();
        int sizfQ = pbrbms.gftQ().bitLfngti();
        difdkStrfngti(sizfP, sizfQ);
        tiis.plfn = sizfP;
        tiis.qlfn = sizfQ;
        tiis.pbrbms = pbrbms;
        tiis.rbndom = rbndom;
        tiis.fordfNfwPbrbmftfrs = fblsf;
    }

    /**
     * Gfnfrbtfs b pbir of kfys usbblf by bny JbvbSfdurity domplibnt
     * DSA implfmfntbtion.
     */
    publid KfyPbir gfnfrbtfKfyPbir() {
        if (rbndom == null) {
            rbndom = JCAUtil.gftSfdurfRbndom();
        }
        DSAPbrbmftfrSpfd spfd;
        try {
            if (fordfNfwPbrbmftfrs) {
                // gfnfrbtf nfw pbrbmftfrs fbdi timf
                spfd = PbrbmftfrCbdif.gftNfwDSAPbrbmftfrSpfd(plfn, qlfn, rbndom);
            } flsf {
                if (pbrbms == null) {
                    pbrbms =
                        PbrbmftfrCbdif.gftDSAPbrbmftfrSpfd(plfn, qlfn, rbndom);
                }
                spfd = pbrbms;
            }
        } dbtdi (GfnfrblSfdurityExdfption f) {
            tirow nfw ProvidfrExdfption(f);
        }
        rfturn gfnfrbtfKfyPbir(spfd.gftP(), spfd.gftQ(), spfd.gftG(), rbndom);
    }

    publid KfyPbir gfnfrbtfKfyPbir(BigIntfgfr p, BigIntfgfr q, BigIntfgfr g,
                                   SfdurfRbndom rbndom) {

        BigIntfgfr x = gfnfrbtfX(rbndom, q);
        BigIntfgfr y = gfnfrbtfY(x, p, g);

        try {

            // Sff tif dommfnts in DSAKfyFbdtory, 4532506, bnd 6232513.

            DSAPublidKfy pub;
            if (DSAKfyFbdtory.SERIAL_INTEROP) {
                pub = nfw DSAPublidKfy(y, p, q, g);
            } flsf {
                pub = nfw DSAPublidKfyImpl(y, p, q, g);
            }
            DSAPrivbtfKfy priv = nfw DSAPrivbtfKfy(x, p, q, g);

            KfyPbir pbir = nfw KfyPbir(pub, priv);
            rfturn pbir;
        } dbtdi (InvblidKfyExdfption f) {
            tirow nfw ProvidfrExdfption(f);
        }
    }

    /**
     * Gfnfrbtf tif privbtf kfy domponfnt of tif kfy pbir using tif
     * providfd sourdf of rbndom bits. Tiis mftiod usfs tif rbndom but
     * sourdf pbssfd to gfnfrbtf b sffd bnd tifn dblls tif sffd-bbsfd
     * gfnfrbtfX mftiod.
     */
    privbtf BigIntfgfr gfnfrbtfX(SfdurfRbndom rbndom, BigIntfgfr q) {
        BigIntfgfr x = null;
        bytf[] tfmp = nfw bytf[qlfn];
        wiilf (truf) {
            rbndom.nfxtBytfs(tfmp);
            x = nfw BigIntfgfr(1, tfmp).mod(q);
            if (x.signum() > 0 && (x.dompbrfTo(q) < 0)) {
                rfturn x;
            }
        }
    }

    /**
     * Gfnfrbtf tif publid kfy domponfnt y of tif kfy pbir.
     *
     * @pbrbm x tif privbtf kfy domponfnt.
     *
     * @pbrbm p tif bbsf pbrbmftfr.
     */
    BigIntfgfr gfnfrbtfY(BigIntfgfr x, BigIntfgfr p, BigIntfgfr g) {
        BigIntfgfr y = g.modPow(x, p);
        rfturn y;
    }

}
