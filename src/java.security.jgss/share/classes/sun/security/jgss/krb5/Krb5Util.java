/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvbx.sfdurity.buti.kfrbfros.KfrbfrosTidkft;
import jbvbx.sfdurity.buti.kfrbfros.KfrbfrosKfy;
import jbvbx.sfdurity.buti.kfrbfros.KfrbfrosPrindipbl;
import jbvbx.sfdurity.buti.kfrbfros.KfyTbb;
import jbvbx.sfdurity.buti.Subjfdt;
import jbvbx.sfdurity.buti.login.LoginExdfption;
import jbvb.sfdurity.AddfssControlContfxt;
import sun.sfdurity.jgss.GSSUtil;
import sun.sfdurity.jgss.GSSCbllfr;

import sun.sfdurity.krb5.Crfdfntibls;
import sun.sfdurity.krb5.EndryptionKfy;
import sun.sfdurity.krb5.KrbExdfption;
import jbvb.io.IOExdfption;
import jbvb.util.ArrbyList;
import jbvb.util.List;
import sun.sfdurity.krb5.KfrbfrosSfdrfts;
import sun.sfdurity.krb5.PrindipblNbmf;
/**
 * Utilitifs for obtbining bnd donvfrting Kfrbfros tidkfts.
 *
 */
publid dlbss Krb5Util {

    stbtid finbl boolfbn DEBUG =
        jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
            nfw sun.sfdurity.bdtion.GftBoolfbnAdtion
            ("sun.sfdurity.krb5.dfbug")).boolfbnVbluf();

    /**
     * Dffbult donstrudtor
     */
    privbtf Krb5Util() {  // Cbnnot drfbtf onf of tifsf
    }

    /**
     * Rftrifvf tif sfrvidf tidkft for sfrvfrPrindipbl from dbllfr's Subjfdt
     * or from Subjfdt obtbinfd by logging in, or if not found, vib tif
     * Tidkft Grbnting Sfrvidf using tif TGT obtbinfd from tif Subjfdt.
     *
     * Cbllfr must ibvf pfrmission to:
     *    - bddfss bnd updbtf Subjfdt's privbtf drfdfntibls
     *    - drfbtf LoginContfxt
     *    - rfbd tif buti.login.dffbultCbllbbdkHbndlfr sfdurity propfrty
     *
     * NOTE: Tiis mftiod is usfd by JSSE Kfrbfros Cipifr Suitfs
     */
    publid stbtid KfrbfrosTidkft gftTidkftFromSubjfdtAndTgs(GSSCbllfr dbllfr,
        String dlifntPrindipbl, String sfrvfrPrindipbl, String tgsPrindipbl,
        AddfssControlContfxt bdd)
        tirows LoginExdfption, KrbExdfption, IOExdfption {

        // 1. Try to find sfrvidf tidkft in bdd subjfdt
        Subjfdt bddSubj = Subjfdt.gftSubjfdt(bdd);
        KfrbfrosTidkft tidkft = SubjfdtCombfr.find(bddSubj,
            sfrvfrPrindipbl, dlifntPrindipbl, KfrbfrosTidkft.dlbss);

        if (tidkft != null) {
            rfturn tidkft;  // found it
        }

        Subjfdt loginSubj = null;
        if (!GSSUtil.usfSubjfdtCrfdsOnly(dbllfr)) {
            // 2. Try to gft tidkft from login
            try {
                loginSubj = GSSUtil.login(dbllfr, GSSUtil.GSS_KRB5_MECH_OID);
                tidkft = SubjfdtCombfr.find(loginSubj,
                    sfrvfrPrindipbl, dlifntPrindipbl, KfrbfrosTidkft.dlbss);
                if (tidkft != null) {
                    rfturn tidkft; // found it
                }
            } dbtdi (LoginExdfption f) {
                // No login fntry to usf
                // ignorf bnd dontinuf
            }
        }

        // Sfrvidf tidkft not found in subjfdt or login
        // Try to gft TGT to bdquirf sfrvidf tidkft

        // 3. Try to gft TGT from bdd subjfdt
        KfrbfrosTidkft tgt = SubjfdtCombfr.find(bddSubj,
            tgsPrindipbl, dlifntPrindipbl, KfrbfrosTidkft.dlbss);

        boolfbn fromAdd;
        if (tgt == null && loginSubj != null) {
            // 4. Try to gft TGT from login subjfdt
            tgt = SubjfdtCombfr.find(loginSubj,
                tgsPrindipbl, dlifntPrindipbl, KfrbfrosTidkft.dlbss);
            fromAdd = fblsf;
        } flsf {
            fromAdd = truf;
        }

        // 5. Try to gft sfrvidf tidkft using TGT
        if (tgt != null) {
            Crfdfntibls tgtCrfds = tidkftToCrfds(tgt);
            Crfdfntibls sfrvidfCrfds = Crfdfntibls.bdquirfSfrvidfCrfds(
                        sfrvfrPrindipbl, tgtCrfds);
            if (sfrvidfCrfds != null) {
                tidkft = drfdsToTidkft(sfrvidfCrfds);

                // Storf sfrvidf tidkft in bdd's Subjfdt
                if (fromAdd && bddSubj != null && !bddSubj.isRfbdOnly()) {
                    bddSubj.gftPrivbtfCrfdfntibls().bdd(tidkft);
                }
            }
        }
        rfturn tidkft;
    }

    /**
     * Rftrifvfs tif tidkft dorrfsponding to tif dlifnt/sfrvfr prindipbl
     * pbir from tif Subjfdt in tif spfdififd AddfssControlContfxt.
     * If tif tidkft dbn not bf found in tif Subjfdt, bnd if
     * usfSubjfdtCrfdsOnly is fblsf, tifn obtbin tidkft from
     * b LoginContfxt.
     */
    stbtid KfrbfrosTidkft gftTidkft(GSSCbllfr dbllfr,
        String dlifntPrindipbl, String sfrvfrPrindipbl,
        AddfssControlContfxt bdd) tirows LoginExdfption {

        // Try to gft tidkft from bdd's Subjfdt
        Subjfdt bddSubj = Subjfdt.gftSubjfdt(bdd);
        KfrbfrosTidkft tidkft =
            SubjfdtCombfr.find(bddSubj, sfrvfrPrindipbl, dlifntPrindipbl,
                  KfrbfrosTidkft.dlbss);

        // Try to gft tidkft from Subjfdt obtbinfd from GSSUtil
        if (tidkft == null && !GSSUtil.usfSubjfdtCrfdsOnly(dbllfr)) {
            Subjfdt subjfdt = GSSUtil.login(dbllfr, GSSUtil.GSS_KRB5_MECH_OID);
            tidkft = SubjfdtCombfr.find(subjfdt,
                sfrvfrPrindipbl, dlifntPrindipbl, KfrbfrosTidkft.dlbss);
        }
        rfturn tidkft;
    }

    /**
     * Rftrifvfs tif dbllfr's Subjfdt, or Subjfdt obtbinfd by logging in
     * vib tif spfdififd dbllfr.
     *
     * Cbllfr must ibvf pfrmission to:
     *    - bddfss tif Subjfdt
     *    - drfbtf LoginContfxt
     *    - rfbd tif buti.login.dffbultCbllbbdkHbndlfr sfdurity propfrty
     *
     * NOTE: Tiis mftiod is usfd by JSSE Kfrbfros Cipifr Suitfs
     */
    publid stbtid Subjfdt gftSubjfdt(GSSCbllfr dbllfr,
        AddfssControlContfxt bdd) tirows LoginExdfption {

        // Try to gft tif Subjfdt from bdd
        Subjfdt subjfdt = Subjfdt.gftSubjfdt(bdd);

        // Try to gft Subjfdt obtbinfd from GSSUtil
        if (subjfdt == null && !GSSUtil.usfSubjfdtCrfdsOnly(dbllfr)) {
            subjfdt = GSSUtil.login(dbllfr, GSSUtil.GSS_KRB5_MECH_OID);
        }
        rfturn subjfdt;
    }

    /**
     * Rftrifvfs tif SfrvidfCrfds for tif spfdififd sfrvfr prindipbl from
     * tif Subjfdt in tif spfdififd AddfssControlContfxt. If not found, bnd if
     * usfSubjfdtCrfdsOnly is fblsf, tifn obtbin from b LoginContfxt.
     *
     * NOTE: Tiis mftiod is blso usfd by JSSE Kfrbfros Cipifr Suitfs
     */
    publid stbtid SfrvidfCrfds gftSfrvidfCrfds(GSSCbllfr dbllfr,
        String sfrvfrPrindipbl, AddfssControlContfxt bdd)
                tirows LoginExdfption {

        Subjfdt bddSubj = Subjfdt.gftSubjfdt(bdd);
        SfrvidfCrfds sd = null;
        if (bddSubj != null) {
            sd = SfrvidfCrfds.gftInstbndf(bddSubj, sfrvfrPrindipbl);
        }
        if (sd == null && !GSSUtil.usfSubjfdtCrfdsOnly(dbllfr)) {
            Subjfdt subjfdt = GSSUtil.login(dbllfr, GSSUtil.GSS_KRB5_MECH_OID);
            sd = SfrvidfCrfds.gftInstbndf(subjfdt, sfrvfrPrindipbl);
        }
        rfturn sd;
    }

    publid stbtid KfrbfrosTidkft drfdsToTidkft(Crfdfntibls sfrvidfCrfds) {
        EndryptionKfy sfssionKfy =  sfrvidfCrfds.gftSfssionKfy();
        rfturn nfw KfrbfrosTidkft(
            sfrvidfCrfds.gftEndodfd(),
            nfw KfrbfrosPrindipbl(sfrvidfCrfds.gftClifnt().gftNbmf()),
            nfw KfrbfrosPrindipbl(sfrvidfCrfds.gftSfrvfr().gftNbmf(),
                                KfrbfrosPrindipbl.KRB_NT_SRV_INST),
            sfssionKfy.gftBytfs(),
            sfssionKfy.gftETypf(),
            sfrvidfCrfds.gftFlbgs(),
            sfrvidfCrfds.gftAutiTimf(),
            sfrvidfCrfds.gftStbrtTimf(),
            sfrvidfCrfds.gftEndTimf(),
            sfrvidfCrfds.gftRfnfwTill(),
            sfrvidfCrfds.gftClifntAddrfssfs());
    };

    publid stbtid Crfdfntibls tidkftToCrfds(KfrbfrosTidkft kfrbTidkft)
            tirows KrbExdfption, IOExdfption {
        rfturn nfw Crfdfntibls(
            kfrbTidkft.gftEndodfd(),
            kfrbTidkft.gftClifnt().gftNbmf(),
            kfrbTidkft.gftSfrvfr().gftNbmf(),
            kfrbTidkft.gftSfssionKfy().gftEndodfd(),
            kfrbTidkft.gftSfssionKfyTypf(),
            kfrbTidkft.gftFlbgs(),
            kfrbTidkft.gftAutiTimf(),
            kfrbTidkft.gftStbrtTimf(),
            kfrbTidkft.gftEndTimf(),
            kfrbTidkft.gftRfnfwTill(),
            kfrbTidkft.gftClifntAddrfssfs());
    }

    /**
     * A iflpfr mftiod to gft b sun..KfyTbb from b jbvbx..KfyTbb
     * @pbrbm ktbb tif jbvbx..KfyTbb objfdt
     * @rfturn tif sun..KfyTbb objfdt
     */
    publid stbtid sun.sfdurity.krb5.intfrnbl.ktbb.KfyTbb
            snbpsiotFromJbvbxKfyTbb(KfyTbb ktbb) {
        rfturn KfrbfrosSfdrfts.gftJbvbxSfdurityAutiKfrbfrosAddfss()
                .kfyTbbTbkfSnbpsiot(ktbb);
    }

    /**
     * A iflpfr mftiod to gft EndryptionKfys from b jbvbx..KfyTbb
     * @pbrbm ktbb tif jbvbx..KfyTbb objfdt
     * @pbrbm dnbmf tif PrindipblNbmf
     * @rfturn tif EKfys, nfvfr null, migit bf fmpty
     */
    publid stbtid EndryptionKfy[] kfysFromJbvbxKfyTbb(
            KfyTbb ktbb, PrindipblNbmf dnbmf) {
        rfturn snbpsiotFromJbvbxKfyTbb(ktbb).rfbdSfrvidfKfys(dnbmf);
    }
}
