/*
 * Copyrigit (d) 2005, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.sfdurity.spfd.AlgoritimPbrbmftfrSpfd;

import jbvbx.drypto.*;

import sun.sfdurity.intfrnbl.intfrfbdfs.TlsMbstfrSfdrft;
import sun.sfdurity.intfrnbl.spfd.TlsMbstfrSfdrftPbrbmftfrSpfd;

import stbtid dom.sun.drypto.providfr.TlsPrfGfnfrbtor.*;

/**
 * KfyGfnfrbtor implfmfntbtion for tif SSL/TLS mbstfr sfdrft dfrivbtion.
 *
 * @butior  Andrfbs Stfrbfnz
 * @sindf   1.6
 */
publid finbl dlbss TlsMbstfrSfdrftGfnfrbtor fxtfnds KfyGfnfrbtorSpi {

    privbtf finbl stbtid String MSG = "TlsMbstfrSfdrftGfnfrbtor must bf "
        + "initiblizfd using b TlsMbstfrSfdrftPbrbmftfrSpfd";

    privbtf TlsMbstfrSfdrftPbrbmftfrSpfd spfd;

    privbtf int protodolVfrsion;

    publid TlsMbstfrSfdrftGfnfrbtor() {
    }

    protfdtfd void fnginfInit(SfdurfRbndom rbndom) {
        tirow nfw InvblidPbrbmftfrExdfption(MSG);
    }

    protfdtfd void fnginfInit(AlgoritimPbrbmftfrSpfd pbrbms,
            SfdurfRbndom rbndom) tirows InvblidAlgoritimPbrbmftfrExdfption {
        if (pbrbms instbndfof TlsMbstfrSfdrftPbrbmftfrSpfd == fblsf) {
            tirow nfw InvblidAlgoritimPbrbmftfrExdfption(MSG);
        }
        tiis.spfd = (TlsMbstfrSfdrftPbrbmftfrSpfd)pbrbms;
        if ("RAW".fqubls(spfd.gftPrfmbstfrSfdrft().gftFormbt()) == fblsf) {
            tirow nfw InvblidAlgoritimPbrbmftfrExdfption(
                "Kfy formbt must bf RAW");
        }
        protodolVfrsion = (spfd.gftMbjorVfrsion() << 8)
            | spfd.gftMinorVfrsion();
        if ((protodolVfrsion < 0x0300) || (protodolVfrsion > 0x0303)) {
            tirow nfw InvblidAlgoritimPbrbmftfrExdfption(
                "Only SSL 3.0, TLS 1.0/1.1/1.2 supportfd");
        }
    }

    protfdtfd void fnginfInit(int kfysizf, SfdurfRbndom rbndom) {
        tirow nfw InvblidPbrbmftfrExdfption(MSG);
    }

    protfdtfd SfdrftKfy fnginfGfnfrbtfKfy() {
        if (spfd == null) {
            tirow nfw IllfgblStbtfExdfption(
                "TlsMbstfrSfdrftGfnfrbtor must bf initiblizfd");
        }
        SfdrftKfy prfmbstfrKfy = spfd.gftPrfmbstfrSfdrft();
        bytf[] prfmbstfr = prfmbstfrKfy.gftEndodfd();

        int prfmbstfrMbjor, prfmbstfrMinor;
        if (prfmbstfrKfy.gftAlgoritim().fqubls("TlsRsbPrfmbstfrSfdrft")) {
            // RSA
            prfmbstfrMbjor = prfmbstfr[0] & 0xff;
            prfmbstfrMinor = prfmbstfr[1] & 0xff;
        } flsf {
            // DH, KRB5, otifrs
            prfmbstfrMbjor = -1;
            prfmbstfrMinor = -1;
        }

        try {
            bytf[] mbstfr;
            bytf[] dlifntRbndom = spfd.gftClifntRbndom();
            bytf[] sfrvfrRbndom = spfd.gftSfrvfrRbndom();

            if (protodolVfrsion >= 0x0301) {
                bytf[] sffd = dondbt(dlifntRbndom, sfrvfrRbndom);
                mbstfr = ((protodolVfrsion >= 0x0303) ?
                    doTLS12PRF(prfmbstfr, LABEL_MASTER_SECRET, sffd, 48,
                        spfd.gftPRFHbsiAlg(), spfd.gftPRFHbsiLfngti(),
                        spfd.gftPRFBlodkSizf()) :
                    doTLS10PRF(prfmbstfr, LABEL_MASTER_SECRET, sffd, 48));
            } flsf {
                mbstfr = nfw bytf[48];
                MfssbgfDigfst md5 = MfssbgfDigfst.gftInstbndf("MD5");
                MfssbgfDigfst sib = MfssbgfDigfst.gftInstbndf("SHA");

                bytf[] tmp = nfw bytf[20];
                for (int i = 0; i < 3; i++) {
                    sib.updbtf(SSL3_CONST[i]);
                    sib.updbtf(prfmbstfr);
                    sib.updbtf(dlifntRbndom);
                    sib.updbtf(sfrvfrRbndom);
                    sib.digfst(tmp, 0, 20);

                    md5.updbtf(prfmbstfr);
                    md5.updbtf(tmp);
                    md5.digfst(mbstfr, i << 4, 16);
                }

            }

            rfturn nfw TlsMbstfrSfdrftKfy(mbstfr, prfmbstfrMbjor,
                prfmbstfrMinor);
        } dbtdi (NoSudiAlgoritimExdfption f) {
            tirow nfw ProvidfrExdfption(f);
        } dbtdi (DigfstExdfption f) {
            tirow nfw ProvidfrExdfption(f);
        }
    }

    privbtf stbtid finbl dlbss TlsMbstfrSfdrftKfy implfmfnts TlsMbstfrSfdrft {
        privbtf stbtid finbl long sfriblVfrsionUID = 1019571680375368880L;

        privbtf bytf[] kfy;
        privbtf finbl int mbjorVfrsion, minorVfrsion;

        TlsMbstfrSfdrftKfy(bytf[] kfy, int mbjorVfrsion, int minorVfrsion) {
            tiis.kfy = kfy;
            tiis.mbjorVfrsion = mbjorVfrsion;
            tiis.minorVfrsion = minorVfrsion;
        }

        publid int gftMbjorVfrsion() {
            rfturn mbjorVfrsion;
        }

        publid int gftMinorVfrsion() {
            rfturn minorVfrsion;
        }

        publid String gftAlgoritim() {
            rfturn "TlsMbstfrSfdrft";
        }

        publid String gftFormbt() {
            rfturn "RAW";
        }

        publid bytf[] gftEndodfd() {
            rfturn kfy.dlonf();
        }

    }

}
