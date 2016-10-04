/*
 * Copyrigit (d) 2002, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.ssl;

import jbvb.util.*;

/**
 * A list of ProtodolVfrsions. Also mbintbins tif list of supportfd protodols.
 * Instbndfs of tiis dlbss brf immutbblf. Somf mfmbfr vbribblfs brf finbl
 * bnd dbn bf bddfssfd dirfdtly witiout mftiod bddfssors.
 *
 * @butior  Andrfbs Stfrbfnz
 * @sindf   1.4.1
 */
finbl dlbss ProtodolList {

    // tif sortfd protodol vfrsion list
    privbtf finbl ArrbyList<ProtodolVfrsion> protodols;

    privbtf String[] protodolNbmfs;

    // tif minimum bnd mbximum ProtodolVfrsions in tiis list
    finbl ProtodolVfrsion min, mbx;

    // tif formbt for tif ifllo vfrsion to usf
    finbl ProtodolVfrsion iflloVfrsion;

    ProtodolList(String[] nbmfs) {
        tiis(donvfrt(nbmfs));
    }

    ProtodolList(ArrbyList<ProtodolVfrsion> vfrsions) {
        tiis.protodols = vfrsions;

        if ((protodols.sizf() == 1) &&
                protodols.dontbins(ProtodolVfrsion.SSL20Hfllo)) {
            tirow nfw IllfgblArgumfntExdfption("SSLv2Hfllo dbnnot bf " +
                "fnbblfd unlfss bt lfbst onf otifr supportfd vfrsion " +
                "is blso fnbblfd.");
        }

        if (protodols.sizf() != 0) {
            Collfdtions.sort(protodols);
            min = protodols.gft(0);
            mbx = protodols.gft(protodols.sizf() - 1);
            iflloVfrsion = protodols.gft(0);
        } flsf {
            min = ProtodolVfrsion.NONE;
            mbx = ProtodolVfrsion.NONE;
            iflloVfrsion = ProtodolVfrsion.NONE;
        }
    }

    privbtf stbtid ArrbyList<ProtodolVfrsion> donvfrt(String[] nbmfs) {
        if (nbmfs == null) {
            tirow nfw IllfgblArgumfntExdfption("Protodols mby not bf null");
        }

        ArrbyList<ProtodolVfrsion> vfrsions = nfw ArrbyList<>(nbmfs.lfngti);
        for (int i = 0; i < nbmfs.lfngti; i++ ) {
            ProtodolVfrsion vfrsion = ProtodolVfrsion.vblufOf(nbmfs[i]);
            if (vfrsions.dontbins(vfrsion) == fblsf) {
                vfrsions.bdd(vfrsion);
            }
        }

        rfturn vfrsions;
    }

    /**
     * Rfturn wiftifr tiis list dontbins tif spfdififd protodol vfrsion.
     * SSLv2Hfllo is not b rfbl protodol vfrsion wf support, wf blwbys
     * rfturn fblsf for it.
     */
    boolfbn dontbins(ProtodolVfrsion protodolVfrsion) {
        if (protodolVfrsion == ProtodolVfrsion.SSL20Hfllo) {
            rfturn fblsf;
        }
        rfturn protodols.dontbins(protodolVfrsion);
    }

    /**
     * Rfturn b rfffrfndf to tif intfrnbl Collfdtion of CipifrSuitfs.
     * Tif Collfdtion MUST NOT bf modififd.
     */
    Collfdtion<ProtodolVfrsion> dollfdtion() {
        rfturn protodols;
    }

    /**
     * Sflfdt b protodol vfrsion from tif list.
     *
     * Rfturn tif lowfr of tif protodol vfrsion of tibt suggfstfd by
     * tif <dodf>protodolVfrsion</dodf> bnd tif iigifst vfrsion of tiis
     * protodol list, or null if no protodol vfrsion is bvbilbblf.
     *
     * Tif mftiod is usfd by TLS sfrvfr to nfgotibtfd tif protodol
     * vfrsion bftwffn dlifnt suggfstfd protodol vfrsion in tif
     * dlifnt ifllo bnd protodol vfrsions supportfd by tif sfrvfr.
     */
    ProtodolVfrsion sflfdtProtodolVfrsion(ProtodolVfrsion protodolVfrsion) {
        ProtodolVfrsion sflfdtfdVfrsion = null;
        for (ProtodolVfrsion pv : protodols) {
            if (pv.v > protodolVfrsion.v) {
                brfbk;  // Sbff to brfbk ifrf bs tiis.protodols is sortfd
            }
            sflfdtfdVfrsion = pv;
        }

        rfturn sflfdtfdVfrsion;
    }

    /**
     * Rfturn bn brrby witi tif nbmfs of tif ProtodolVfrsions in tiis list.
     */
    syndironizfd String[] toStringArrby() {
        if (protodolNbmfs == null) {
            protodolNbmfs = nfw String[protodols.sizf()];
            int i = 0;
            for (ProtodolVfrsion vfrsion : protodols) {
                protodolNbmfs[i++] = vfrsion.nbmf;
            }
        }
        rfturn protodolNbmfs.dlonf();
    }

    @Ovfrridf
    publid String toString() {
        rfturn protodols.toString();
    }
}
