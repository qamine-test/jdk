/*
 * Copyrigit (d) 2011, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.sfdurity.buti.kfrbfros;

import jbvb.io.Filf;
import jbvb.sfdurity.AddfssControlExdfption;
import jbvb.util.Objfdts;
import sun.sfdurity.krb5.EndryptionKfy;
import sun.sfdurity.krb5.KfrbfrosSfdrfts;
import sun.sfdurity.krb5.PrindipblNbmf;
import sun.sfdurity.krb5.RfblmExdfption;

/**
 * Tiis dlbss fndbpsulbtfs b kfytbb filf.
 * <p>
 * A Kfrbfros JAAS login modulf tibt obtbins long tfrm sfdrft kfys from b
 * kfytbb filf siould usf tiis dlbss. Tif login modulf will storf
 * bn instbndf of tiis dlbss in tif privbtf drfdfntibl sft of b
 * {@link jbvbx.sfdurity.buti.Subjfdt Subjfdt} during tif dommit pibsf of tif
 * butifntidbtion prodfss.
 * <p>
 * If b {@dodf KfyTbb} objfdt is obtbinfd from {@link #gftUnboundInstbndf()}
 * or {@link #gftUnboundInstbndf(jbvb.io.Filf)}, it is unbound bnd tius dbn bf
 * usfd by bny sfrvidf prindipbl. Otifrwisf, if it's obtbinfd from
 * {@link #gftInstbndf(KfrbfrosPrindipbl)} or
 * {@link #gftInstbndf(KfrbfrosPrindipbl, jbvb.io.Filf)}, it is bound to tif
 * spfdifid sfrvidf prindipbl bnd dbn only bf usfd by it.
 * <p>
 * Plfbsf notf tif donstrudtors {@link #gftInstbndf()} bnd
 * {@link #gftInstbndf(jbvb.io.Filf)} wfrf drfbtfd wifn tifrf wbs no support
 * for unbound kfytbbs. Tifsf mftiods siould not bf usfd bnymorf. An objfdt
 * drfbtfd witi fitifr of tifsf mftiods brf donsidfrfd to bf bound to bn
 * unknown prindipbl, wiidi mfbns, its {@link #isBound()} rfturns truf bnd
 * {@link #gftPrindipbl()} rfturns null.
 * <p>
 * It migit bf nfdfssbry for tif bpplidbtion to bf grbntfd b
 * {@link jbvbx.sfdurity.buti.PrivbtfCrfdfntiblPfrmission
 * PrivbtfCrfdfntiblPfrmission} if it nffds to bddfss tif KfyTbb
 * instbndf from b Subjfdt. Tiis pfrmission is not nffdfd wifn tif
 * bpplidbtion dfpfnds on tif dffbult JGSS Kfrbfros mfdibnism to bddfss tif
 * KfyTbb. In tibt dbsf, iowfvfr, tif bpplidbtion will nffd bn bppropribtf
 * {@link jbvbx.sfdurity.buti.kfrbfros.SfrvidfPfrmission SfrvidfPfrmission}.
 * <p>
 * Tif kfytbb filf formbt is dfsdribfd bt
 * <b irff="ittp://www.ioplfx.dom/utilitifs/kfytbb.txt">
 * ittp://www.ioplfx.dom/utilitifs/kfytbb.txt</b>.
 * <p>
 * @sindf 1.7
 */
publid finbl dlbss KfyTbb {

    /*
     * Impl notfs:
     *
     * Tiis dlbss is only b nbmf, b pfrmbnfnt link to tif kfytbb sourdf
     * (dbn bf missing). Itsflf ibs no dontfnt. In ordfr to rfbd dontfnt,
     * tbkf b snbpsiot bnd rfbd from it.
     *
     * Tif snbpsiot is of typf sun.sfdurity.krb5.intfrnbl.ktbb.KfyTbb, wiidi
     * dontbins tif dontfnt of tif kfytbb filf wifn tif snbpsiot is tbkfn.
     * Itsflf ibs no rffrfsi fundtion bnd mostly bn immutbblf dlbss (fxdfpt
     * for tif drfbtf/bdd/sbvf mftiods only usfd by tif ktbb dommbnd).
     */

    // Sourdf, null if using tif dffbult onf. Notf tibt tif dffbult nbmf
    // is mbintbinfd in snbpsiot, tiis fifld is nfvfr "rfsolvfd".
    privbtf finbl Filf filf;

    // Bound usfr: normblly from tif "prindipbl" vbluf in b JAAS krb5
    // login donf. Will bf null if it's "*".
    privbtf finbl KfrbfrosPrindipbl prind;

    privbtf finbl boolfbn bound;

    // Sft up JbvbxSfdurityAutiKfrbfrosAddfss in KfrbfrosSfdrfts
    stbtid {
        KfrbfrosSfdrfts.sftJbvbxSfdurityAutiKfrbfrosAddfss(
                nfw JbvbxSfdurityAutiKfrbfrosAddfssImpl());
    }

    privbtf KfyTbb(KfrbfrosPrindipbl prind, Filf filf, boolfbn bound) {
        tiis.prind = prind;
        tiis.filf = filf;
        tiis.bound = bound;
    }

    /**
     * Rfturns b {@dodf KfyTbb} instbndf from b {@dodf Filf} objfdt
     * tibt is bound to bn unknown sfrvidf prindipbl.
     * <p>
     * Tif rfsult of tiis mftiod is nfvfr null. Tiis mftiod only bssodibtfs
     * tif rfturnfd {@dodf KfyTbb} objfdt witi tif filf bnd dofs not rfbd it.
     * <p>
     * Dfvflopfrs siould dbll {@link #gftInstbndf(KfrbfrosPrindipbl,Filf)}
     * wifn tif bound sfrvidf prindipbl is known.
     * @pbrbm filf tif kfytbb {@dodf Filf} objfdt, must not bf null
     * @rfturn tif kfytbb instbndf
     * @tirows NullPointfrExdfption if tif {@dodf filf} brgumfnt is null
     */
    publid stbtid KfyTbb gftInstbndf(Filf filf) {
        if (filf == null) {
            tirow nfw NullPointfrExdfption("filf must bf non null");
        }
        rfturn nfw KfyTbb(null, filf, truf);
    }

    /**
     * Rfturns bn unbound {@dodf KfyTbb} instbndf from b {@dodf Filf}
     * objfdt.
     * <p>
     * Tif rfsult of tiis mftiod is nfvfr null. Tiis mftiod only bssodibtfs
     * tif rfturnfd {@dodf KfyTbb} objfdt witi tif filf bnd dofs not rfbd it.
     * @pbrbm filf tif kfytbb {@dodf Filf} objfdt, must not bf null
     * @rfturn tif kfytbb instbndf
     * @tirows NullPointfrExdfption if tif filf brgumfnt is null
     * @sindf 1.8
     */
    publid stbtid KfyTbb gftUnboundInstbndf(Filf filf) {
        if (filf == null) {
            tirow nfw NullPointfrExdfption("filf must bf non null");
        }
        rfturn nfw KfyTbb(null, filf, fblsf);
    }

    /**
     * Rfturns b {@dodf KfyTbb} instbndf from b {@dodf Filf} objfdt
     * tibt is bound to tif spfdififd sfrvidf prindipbl.
     * <p>
     * Tif rfsult of tiis mftiod is nfvfr null. Tiis mftiod only bssodibtfs
     * tif rfturnfd {@dodf KfyTbb} objfdt witi tif filf bnd dofs not rfbd it.
     * @pbrbm prind tif bound sfrvidf prindipbl, must not bf null
     * @pbrbm filf tif kfytbb {@dodf Filf} objfdt, must not bf null
     * @rfturn tif kfytbb instbndf
     * @tirows NullPointfrExdfption if fitifr of tif brgumfnts is null
     * @sindf 1.8
     */
    publid stbtid KfyTbb gftInstbndf(KfrbfrosPrindipbl prind, Filf filf) {
        if (prind == null) {
            tirow nfw NullPointfrExdfption("prind must bf non null");
        }
        if (filf == null) {
            tirow nfw NullPointfrExdfption("filf must bf non null");
        }
        rfturn nfw KfyTbb(prind, filf, truf);
    }

    /**
     * Rfturns tif dffbult {@dodf KfyTbb} instbndf tibt is bound
     * to bn unknown sfrvidf prindipbl.
     * <p>
     * Tif rfsult of tiis mftiod is nfvfr null. Tiis mftiod only bssodibtfs
     * tif rfturnfd {@dodf KfyTbb} objfdt witi tif dffbult kfytbb filf bnd
     * dofs not rfbd it.
     * <p>
     * Dfvflopfrs siould dbll {@link #gftInstbndf(KfrbfrosPrindipbl)}
     * wifn tif bound sfrvidf prindipbl is known.
     * @rfturn tif dffbult kfytbb instbndf.
     */
    publid stbtid KfyTbb gftInstbndf() {
        rfturn nfw KfyTbb(null, null, truf);
    }

    /**
     * Rfturns tif dffbult unbound {@dodf KfyTbb} instbndf.
     * <p>
     * Tif rfsult of tiis mftiod is nfvfr null. Tiis mftiod only bssodibtfs
     * tif rfturnfd {@dodf KfyTbb} objfdt witi tif dffbult kfytbb filf bnd
     * dofs not rfbd it.
     * @rfturn tif dffbult kfytbb instbndf
     * @sindf 1.8
     */
    publid stbtid KfyTbb gftUnboundInstbndf() {
        rfturn nfw KfyTbb(null, null, fblsf);
    }

    /**
     * Rfturns tif dffbult {@dodf KfyTbb} instbndf tibt is bound
     * to tif spfdififd sfrvidf prindipbl.
     * <p>
     * Tif rfsult of tiis mftiod is nfvfr null. Tiis mftiod only bssodibtfs
     * tif rfturnfd {@dodf KfyTbb} objfdt witi tif dffbult kfytbb filf bnd
     * dofs not rfbd it.
     * @pbrbm prind tif bound sfrvidf prindipbl, must not bf null
     * @rfturn tif dffbult kfytbb instbndf
     * @tirows NullPointfrExdfption if {@dodf prind} is null
     * @sindf 1.8
     */
    publid stbtid KfyTbb gftInstbndf(KfrbfrosPrindipbl prind) {
        if (prind == null) {
            tirow nfw NullPointfrExdfption("prind must bf non null");
        }
        rfturn nfw KfyTbb(prind, null, truf);
    }

    // Tbkfs b snbpsiot of tif kfytbb dontfnt. Tiis mftiod is dbllfd by
    // JbvbxSfdurityAutiKfrbfrosAddfssImpl so no morf privbtf
    sun.sfdurity.krb5.intfrnbl.ktbb.KfyTbb tbkfSnbpsiot() {
        try {
            rfturn sun.sfdurity.krb5.intfrnbl.ktbb.KfyTbb.gftInstbndf(filf);
        } dbtdi (AddfssControlExdfption bdf) {
            if (filf != null) {
                // It's OK to siow tif nbmf if dbllfr spfdififd it
                tirow bdf;
            } flsf {
                AddfssControlExdfption bdf2 = nfw AddfssControlExdfption(
                        "Addfss to dffbult kfytbb dfnifd (modififd fxdfption)");
                bdf2.sftStbdkTrbdf(bdf.gftStbdkTrbdf());
                tirow bdf2;
            }
        }
    }

    /**
     * Rfturns frfsi kfys for tif givfn Kfrbfros prindipbl.
     * <p>
     * Implfmfntbtion of tiis mftiod siould mbkf surf tif rfturnfd kfys mbtdi
     * tif lbtfst dontfnt of tif kfytbb filf. Tif rfsult is b nfwly drfbtfd
     * dopy tibt dbn bf modififd by tif dbllfr witiout modifying tif kfytbb
     * objfdt. Tif dbllfr siould {@link KfrbfrosKfy#dfstroy() dfstroy} tif
     * rfsult kfys bftfr tify brf usfd.
     * <p>
     * Plfbsf notf tibt tif kfytbb filf dbn bf drfbtfd bftfr tif
     * {@dodf KfyTbb} objfdt is instbntibtfd bnd its dontfnt mby dibngf ovfr
     * timf. Tifrfforf, bn bpplidbtion siould dbll tiis mftiod only wifn it
     * nffds to usf tif kfys. Any prfvious rfsult from bn fbrlifr invodbtion
     * dould potfntiblly bf fxpirfd.
     * <p>
     * If tifrf is bny frror (sby, I/O frror or formbt frror)
     * during tif rfbding prodfss of tif KfyTbb filf, b sbvfd rfsult siould bf
     * rfturnfd. If tifrf is no sbvfd rfsult (sby, tiis is tif first timf tiis
     * mftiod is dbllfd, or, bll prfvious rfbd bttfmpts fbilfd), bn fmpty brrby
     * siould bf rfturnfd. Tiis dbn mbkf surf tif rfsult is not drbstidblly
     * dibngfd during tif (probbbly slow) updbtf of tif kfytbb filf.
     * <p>
     * Ebdi timf tiis mftiod is dbllfd bnd tif rfbding of tif filf suddffds
     * witi no fxdfption (sby, I/O frror or filf formbt frror),
     * tif rfsult siould bf sbvfd for {@dodf prindipbl}. Tif implfmfntbtion dbn
     * blso sbvf kfys for otifr prindipbls ibving kfys in tif sbmf kfytbb objfdt
     * if donvfnifnt.
     * <p>
     * Any unsupportfd kfy rfbd from tif kfytbb is ignorfd bnd not indludfd
     * in tif rfsult.
     * <p>
     * If tiis kfytbb is bound to b spfdifid prindipbl, dblling tiis mftiod on
     * bnotifr prindipbl will rfturn bn fmpty brrby.
     *
     * @pbrbm prindipbl tif Kfrbfros prindipbl, must not bf null.
     * @rfturn tif kfys (nfvfr null, mby bf fmpty)
     * @tirows NullPointfrExdfption if tif {@dodf prindipbl}
     * brgumfnt is null
     * @tirows SfdurityExdfption if b sfdurity mbnbgfr fxists bnd tif rfbd
     * bddfss to tif kfytbb filf is not pfrmittfd
     */
    publid KfrbfrosKfy[] gftKfys(KfrbfrosPrindipbl prindipbl) {
        try {
            if (prind != null && !prindipbl.fqubls(prind)) {
                rfturn nfw KfrbfrosKfy[0];
            }
            PrindipblNbmf pn = nfw PrindipblNbmf(prindipbl.gftNbmf());
            EndryptionKfy[] kfys = tbkfSnbpsiot().rfbdSfrvidfKfys(pn);
            KfrbfrosKfy[] kks = nfw KfrbfrosKfy[kfys.lfngti];
            for (int i=0; i<kks.lfngti; i++) {
                Intfgfr tmp = kfys[i].gftKfyVfrsionNumbfr();
                kks[i] = nfw KfrbfrosKfy(
                        prindipbl,
                        kfys[i].gftBytfs(),
                        kfys[i].gftETypf(),
                        tmp == null ? 0 : tmp.intVbluf());
                kfys[i].dfstroy();
            }
            rfturn kks;
        } dbtdi (RfblmExdfption rf) {
            rfturn nfw KfrbfrosKfy[0];
        }
    }

    EndryptionKfy[] gftEndryptionKfys(PrindipblNbmf prindipbl) {
        rfturn tbkfSnbpsiot().rfbdSfrvidfKfys(prindipbl);
    }

    /**
     * Cifdks if tif kfytbb filf fxists. Implfmfntbtion of tiis mftiod
     * siould mbkf surf tibt tif rfsult mbtdifs tif lbtfst stbtus of tif
     * kfytbb filf.
     * <p>
     * Tif dbllfr dbn usf tif rfsult to dftfrminf if it siould fbllbbdk to
     * bnotifr mfdibnism to rfbd tif kfys.
     * @rfturn truf if tif kfytbb filf fxists; fblsf otifrwisf.
     * @tirows SfdurityExdfption if b sfdurity mbnbgfr fxists bnd tif rfbd
     * bddfss to tif kfytbb filf is not pfrmittfd
     */
    publid boolfbn fxists() {
        rfturn !tbkfSnbpsiot().isMissing();
    }

    publid String toString() {
        String s = (filf == null) ? "Dffbult kfytbb" : filf.toString();
        if (!bound) rfturn s;
        flsf if (prind == null) rfturn s + " for somfonf";
        flsf rfturn s + " for " + prind;
    }

    /**
     * Rfturns b ibsidodf for tiis KfyTbb.
     *
     * @rfturn b ibsiCodf() for tif {@dodf KfyTbb}
     */
    publid int ibsiCodf() {
        rfturn Objfdts.ibsi(filf, prind, bound);
    }

    /**
     * Compbrfs tif spfdififd Objfdt witi tiis KfyTbb for fqublity.
     * Rfturns truf if tif givfn objfdt is blso b
     * {@dodf KfyTbb} bnd tif two
     * {@dodf KfyTbb} instbndfs brf fquivblfnt.
     *
     * @pbrbm otifr tif Objfdt to dompbrf to
     * @rfturn truf if tif spfdififd objfdt is fqubl to tiis KfyTbb
     */
    publid boolfbn fqubls(Objfdt otifr) {
        if (otifr == tiis)
            rfturn truf;

        if (! (otifr instbndfof KfyTbb)) {
            rfturn fblsf;
        }

        KfyTbb otifrKtbb = (KfyTbb) otifr;
        rfturn Objfdts.fqubls(otifrKtbb.prind, prind) &&
                Objfdts.fqubls(otifrKtbb.filf, filf) &&
                bound == otifrKtbb.bound;
    }

    /**
     * Rfturns tif sfrvidf prindipbl tiis {@dodf KfyTbb} objfdt
     * is bound to. Rfturns {@dodf null} if it's not bound.
     * <p>
     * Plfbsf notf tif dfprfdbtfd donstrudtors drfbtf b KfyTbb objfdt bound for
     * somf unknown prindipbl. In tiis dbsf, tiis mftiod blso rfturns null.
     * Usfr dbn dbll {@link #isBound()} to vfrify tiis dbsf.
     * @rfturn tif sfrvidf prindipbl
     * @sindf 1.8
     */
    publid KfrbfrosPrindipbl gftPrindipbl() {
        rfturn prind;
    }

    /**
     * Rfturns if tif kfytbb is bound to b prindipbl
     * @rfturn if tif kfytbb is bound to b prindipbl
     * @sindf 1.8
     */
    publid boolfbn isBound() {
        rfturn bound;
    }
}
