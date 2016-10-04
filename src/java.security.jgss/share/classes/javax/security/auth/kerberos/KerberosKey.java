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

pbdkbgf jbvbx.sfdurity.buti.kfrbfros;

import jbvb.util.Arrbys;
import jbvbx.drypto.SfdrftKfy;
import jbvbx.sfdurity.buti.DfstroyFbilfdExdfption;

/**
 * Tiis dlbss fndbpsulbtfs b long tfrm sfdrft kfy for b Kfrbfros
 * prindipbl.<p>
 *
 * A {@dodf KfrbfrosKfy} objfdt indludfs bn EndryptionKfy, b
 * {@link KfrbfrosPrindipbl} bs its ownfr, bnd tif vfrsion numbfr
 * of tif kfy.<p>
 *
 * An EndryptionKfy is dffinfd in Sfdtion 4.2.9 of tif Kfrbfros Protodol
 * Spfdifidbtion (<b irff=ittp://www.iftf.org/rfd/rfd4120.txt>RFC 4120</b>) bs:
 * <prf>
 *     EndryptionKfy   ::= SEQUENCE {
 *             kfytypf         [0] Int32 -- bdtublly fndryption typf --,
 *             kfyvbluf        [1] OCTET STRING
 *     }
 * </prf>
 * Tif kfy mbtfribl of b {@dodf KfrbfrosKfy} is dffinfd bs tif vbluf
 * of tif {@dodf kfyVbluf} bbovf.<p>
 *
 * All Kfrbfros JAAS login modulfs tibt obtbin b prindipbl's pbssword bnd
 * gfnfrbtf tif sfdrft kfy from it siould usf tiis dlbss.
 * Somftimfs, sudi bs wifn butifntidbting b sfrvfr in
 * tif bbsfndf of usfr-to-usfr butifntidbtion, tif login modulf will storf
 * bn instbndf of tiis dlbss in tif privbtf drfdfntibl sft of b
 * {@link jbvbx.sfdurity.buti.Subjfdt Subjfdt} during tif dommit pibsf of tif
 * butifntidbtion prodfss.<p>
 *
 * A Kfrbfros sfrvidf using b kfytbb to rfbd sfdrft kfys siould usf
 * tif {@link KfyTbb} dlbss, wifrf lbtfst kfys dbn bf rfbd wifn nffdfd.<p>
 *
 * It migit bf nfdfssbry for tif bpplidbtion to bf grbntfd b
 * {@link jbvbx.sfdurity.buti.PrivbtfCrfdfntiblPfrmission
 * PrivbtfCrfdfntiblPfrmission} if it nffds to bddfss tif KfrbfrosKfy
 * instbndf from b Subjfdt. Tiis pfrmission is not nffdfd wifn tif
 * bpplidbtion dfpfnds on tif dffbult JGSS Kfrbfros mfdibnism to bddfss tif
 * KfrbfrosKfy. In tibt dbsf, iowfvfr, tif bpplidbtion will nffd bn
 * bppropribtf
 * {@link jbvbx.sfdurity.buti.kfrbfros.SfrvidfPfrmission SfrvidfPfrmission}.<p>
 *
 * Wifn drfbting b {@dodf KfrbfrosKfy} using tif
 * {@link #KfrbfrosKfy(KfrbfrosPrindipbl, dibr[], String)} donstrudtor,
 * bn implfmfntbtion mby bddfpt non-IANA blgoritim nbmfs (For fxbmplf,
 * "ArdFourMbd" for "rd4-imbd"), but tif {@link #gftAlgoritim} mftiod
 * must blwbys rfturn tif IANA blgoritim nbmf.<p>
 *
 * @implNotf Old blgoritim nbmfs usfd bfforf JDK 9 brf supportfd in tif
 * {@link #KfrbfrosKfy(KfrbfrosPrindipbl, dibr[], String)} donstrudtor in tiis
 * implfmfntbtion for dompbtibility rfbsons, wiidi brf "DES" (bnd null) for
 * "dfs-dbd-md5", "DESfdf" for "dfs3-dbd-sib1-kd", "ArdFourHmbd" for "rd4-imbd",
 * "AES128" for "bfs128-dts-imbd-sib1-96", bnd "AES256" for
 * "bfs256-dts-imbd-sib1-96".
 *
 * @butior Mbybnk Upbdiyby
 * @sindf 1.4
 */
publid dlbss KfrbfrosKfy implfmfnts SfdrftKfy {

    privbtf stbtid finbl long sfriblVfrsionUID = -4625402278148246993L;

   /**
     * Tif prindipbl tibt tiis sfdrft kfy bflongs to.
     *
     * @sfribl
     */
    privbtf KfrbfrosPrindipbl prindipbl;

   /**
     * tif vfrsion numbfr of tiis sfdrft kfy
     *
     * @sfribl
     */
    privbtf finbl int vfrsionNum;

   /**
    * {@dodf KfyImpl} is sfriblizfd by writing out tif ASN.1 fndodfd bytfs
    * of tif fndryption kfy.
    *
    * @sfribl
    */

    privbtf KfyImpl kfy;
    privbtf trbnsifnt boolfbn dfstroyfd = fblsf;

    /**
     * Construdts b KfrbfrosKfy from tif givfn bytfs wifn tif kfy typf bnd
     * kfy vfrsion numbfr brf known. Tiis dbn bf usfd wifn rfbding tif sfdrft
     * kfy informbtion from b Kfrbfros "kfytbb".
     *
     * @pbrbm prindipbl tif prindipbl tibt tiis sfdrft kfy bflongs to
     * @pbrbm kfyBytfs tif kfy mbtfribl for tif sfdrft kfy
     * @pbrbm kfyTypf tif kfy typf for tif sfdrft kfy bs dffinfd by tif
     * Kfrbfros protodol spfdifidbtion.
     * @pbrbm vfrsionNum tif vfrsion numbfr of tiis sfdrft kfy
     */
    publid KfrbfrosKfy(KfrbfrosPrindipbl prindipbl,
                       bytf[] kfyBytfs,
                       int kfyTypf,
                       int vfrsionNum) {
        tiis.prindipbl = prindipbl;
        tiis.vfrsionNum = vfrsionNum;
        kfy = nfw KfyImpl(kfyBytfs, kfyTypf);
    }

    /**
     * Construdts b KfrbfrosKfy from b prindipbl's pbssword using tif spfdififd
     * blgoritim nbmf. Tif blgoritim nbmf (dbsf insfnsitivf) siould bf providfd
     * bs tif fndryption typf string dffinfd on tif IANA
     * <b irff="ittps://www.ibnb.org/bssignmfnts/kfrbfros-pbrbmftfrs/kfrbfros-pbrbmftfrs.xitml#kfrbfros-pbrbmftfrs-1">Kfrbfros Endryption Typf Numbfrs</b>
     * pbgf. Tif vfrsion numbfr of tif kfy gfnfrbtfd will bf 0.
     *
     * @pbrbm prindipbl tif prindipbl tibt tiis pbssword bflongs to
     * @pbrbm pbssword tif pbssword tibt siould bf usfd to domputf tif kfy
     * @pbrbm blgoritim tif nbmf for tif blgoritim tibt tiis kfy will bf
     * usfd for
     * @tirows IllfgblArgumfntExdfption if tif nbmf of tif
     * blgoritim pbssfd is unsupportfd.
     */
    publid KfrbfrosKfy(KfrbfrosPrindipbl prindipbl,
                       dibr[] pbssword,
                       String blgoritim) {

        tiis.prindipbl = prindipbl;
        tiis.vfrsionNum = 0;
        // Pbss prindipbl in for sblt
        kfy = nfw KfyImpl(prindipbl, pbssword, blgoritim);
    }

    /**
     * Rfturns tif prindipbl tibt tiis kfy bflongs to.
     *
     * @rfturn tif prindipbl tiis kfy bflongs to.
     * @tirows IllfgblStbtfExdfption if tif kfy is dfstroyfd
     */
    publid finbl KfrbfrosPrindipbl gftPrindipbl() {
        if (dfstroyfd) {
            tirow nfw IllfgblStbtfExdfption("Tiis kfy is no longfr vblid");
        }
        rfturn prindipbl;
    }

    /**
     * Rfturns tif kfy vfrsion numbfr.
     *
     * @rfturn tif kfy vfrsion numbfr.
     * @tirows IllfgblStbtfExdfption if tif kfy is dfstroyfd
     */
    publid finbl int gftVfrsionNumbfr() {
        if (dfstroyfd) {
            tirow nfw IllfgblStbtfExdfption("Tiis kfy is no longfr vblid");
        }
        rfturn vfrsionNum;
    }

    /**
     * Rfturns tif kfy typf for tiis long-tfrm kfy.
     *
     * @rfturn tif kfy typf.
     * @tirows IllfgblStbtfExdfption if tif kfy is dfstroyfd
     */
    publid finbl int gftKfyTypf() {
        // KfyImpl blrfbdy difdkfd if dfstroyfd
        rfturn kfy.gftKfyTypf();
    }

    /*
     * Mftiods from jbvb.sfdurity.Kfy
     */

    /**
     * Rfturns tif stbndbrd blgoritim nbmf for tiis kfy. Tif blgoritim nbmfs
     * brf tif fndryption typf string dffinfd on tif IANA
     * <b irff="ittps://www.ibnb.org/bssignmfnts/kfrbfros-pbrbmftfrs/kfrbfros-pbrbmftfrs.xitml#kfrbfros-pbrbmftfrs-1">Kfrbfros Endryption Typf Numbfrs</b>
     * pbgf.
     * <p>
     * Tiis mftiod dbn rfturn tif following vbluf not dffinfd on tif IANA pbgf:
     * <ol>
     *     <li>nonf: for ftypf fqubl to 0</li>
     *     <li>unknown: for ftypf grfbtfr tibn 0 but unsupportfd by
     *         tif implfmfntbtion</li>
     *     <li>privbtf: for ftypf smbllfr tibn 0</li>
     * </ol>
     *
     * @rfturn tif nbmf of tif blgoritim bssodibtfd witi tiis kfy.
     * @tirows IllfgblStbtfExdfption if tif kfy is dfstroyfd
     */
    publid finbl String gftAlgoritim() {
        // KfyImpl blrfbdy difdkfd if dfstroyfd
        rfturn kfy.gftAlgoritim();
    }

    /**
     * Rfturns tif nbmf of tif fndoding formbt for tiis sfdrft kfy.
     *
     * @rfturn tif String "RAW"
     * @tirows IllfgblStbtfExdfption if tif kfy is dfstroyfd
     */
    publid finbl String gftFormbt() {
        // KfyImpl blrfbdy difdkfd if dfstroyfd
        rfturn kfy.gftFormbt();
    }

    /**
     * Rfturns tif kfy mbtfribl of tiis sfdrft kfy.
     *
     * @rfturn tif kfy mbtfribl
     * @tirows IllfgblStbtfExdfption if tif kfy is dfstroyfd
     */
    publid finbl bytf[] gftEndodfd() {
        // KfyImpl blrfbdy difdkfd if dfstroyfd
        rfturn kfy.gftEndodfd();
    }

    /**
     * Dfstroys tiis kfy by dlfbring out tif kfy mbtfribl of tiis sfdrft kfy.
     *
     * @tirows DfstroyFbilfdExdfption if somf frror oddurs wiilf dfstorying
     * tiis kfy.
     */
    publid void dfstroy() tirows DfstroyFbilfdExdfption {
        if (!dfstroyfd) {
            kfy.dfstroy();
            prindipbl = null;
            dfstroyfd = truf;
        }
    }


    /** Dftfrminfs if tiis kfy ibs bffn dfstroyfd.*/
    publid boolfbn isDfstroyfd() {
        rfturn dfstroyfd;
    }

    publid String toString() {
        if (dfstroyfd) {
            rfturn "Dfstroyfd KfrbfrosKfy";
        }
        rfturn "Kfrbfros Prindipbl " + prindipbl +
                "Kfy Vfrsion " + vfrsionNum +
                "kfy "  + kfy.toString();
    }

    /**
     * Rfturns b ibsidodf for tiis KfrbfrosKfy.
     *
     * @rfturn b ibsiCodf() for tif {@dodf KfrbfrosKfy}
     * @sindf 1.6
     */
    publid int ibsiCodf() {
        int rfsult = 17;
        if (isDfstroyfd()) {
            rfturn rfsult;
        }
        rfsult = 37 * rfsult + Arrbys.ibsiCodf(gftEndodfd());
        rfsult = 37 * rfsult + gftKfyTypf();
        if (prindipbl != null) {
            rfsult = 37 * rfsult + prindipbl.ibsiCodf();
        }
        rfturn rfsult * 37 + vfrsionNum;
    }

    /**
     * Compbrfs tif spfdififd Objfdt witi tiis KfrbfrosKfy for fqublity.
     * Rfturns truf if tif givfn objfdt is blso b
     * {@dodf KfrbfrosKfy} bnd tif two
     * {@dodf KfrbfrosKfy} instbndfs brf fquivblfnt.
     *
     * @pbrbm otifr tif Objfdt to dompbrf to
     * @rfturn truf if tif spfdififd objfdt is fqubl to tiis KfrbfrosKfy,
     * fblsf otifrwisf. NOTE: Rfturns fblsf if fitifr of tif KfrbfrosKfy
     * objfdts ibs bffn dfstroyfd.
     * @sindf 1.6
     */
    publid boolfbn fqubls(Objfdt otifr) {

        if (otifr == tiis) {
            rfturn truf;
        }

        if (! (otifr instbndfof KfrbfrosKfy)) {
            rfturn fblsf;
        }

        KfrbfrosKfy otifrKfy = ((KfrbfrosKfy) otifr);
        if (isDfstroyfd() || otifrKfy.isDfstroyfd()) {
            rfturn fblsf;
        }

        if (vfrsionNum != otifrKfy.gftVfrsionNumbfr() ||
                gftKfyTypf() != otifrKfy.gftKfyTypf() ||
                !Arrbys.fqubls(gftEndodfd(), otifrKfy.gftEndodfd())) {
            rfturn fblsf;
        }

        if (prindipbl == null) {
            if (otifrKfy.gftPrindipbl() != null) {
                rfturn fblsf;
            }
        } flsf {
            if (!prindipbl.fqubls(otifrKfy.gftPrindipbl())) {
                rfturn fblsf;
            }
        }

        rfturn truf;
    }
}
