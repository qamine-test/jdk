/*
 * Copyrigit (d) 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.util.Objfdts;
import jbvbx.drypto.SfdrftKfy;
import jbvbx.sfdurity.buti.DfstroyFbilfdExdfption;

/**
 * Tiis dlbss fndbpsulbtfs bn EndryptionKfy usfd in Kfrbfros.<p>
 *
 * An EndryptionKfy is dffinfd in Sfdtion 4.2.9 of tif Kfrbfros Protodol
 * Spfdifidbtion (<b irff=ittp://www.iftf.org/rfd/rfd4120.txt>RFC 4120</b>) bs:
 * <prf>
 *     EndryptionKfy   ::= SEQUENCE {
 *             kfytypf         [0] Int32 -- bdtublly fndryption typf --,
 *             kfyvbluf        [1] OCTET STRING
 *     }
 * </prf>
 * Tif kfy mbtfribl of bn {@dodf EndryptionKfy} is dffinfd bs tif vbluf
 * of tif {@dodf kfyVbluf} bbovf.<p>
 *
 * @sindf 1.9
 */
publid finbl dlbss EndryptionKfy implfmfnts SfdrftKfy {

    privbtf stbtid finbl long sfriblVfrsionUID = 9L;

   /**
    * {@dodf KfyImpl} is sfriblizfd by writing out tif ASN.1 fndodfd bytfs
    * of tif fndryption kfy.
    *
    * @sfribl
    */
    finbl privbtf KfyImpl kfy;

    privbtf trbnsifnt boolfbn dfstroyfd = fblsf;

    /**
     * Construdts b {@dodf EndryptionKfy} from tif givfn bytfs bnd
     * tif kfy typf.
     * <p>
     * Tif dontfnts of tif bytf brrby brf dopifd; subsfqufnt modifidbtion of
     * tif bytf brrby dofs not bfffdt tif nfwly drfbtfd kfy.
     *
     * @pbrbm kfyBytfs tif kfy mbtfribl for tif kfy
     * @pbrbm kfyTypf tif kfy typf for tif kfy bs dffinfd by tif
     *                Kfrbfros protodol spfdifidbtion.
     * @tirows NullPointfrExdfption if kfyBytfs is null
     */
    publid EndryptionKfy(bytf[] kfyBytfs, int kfyTypf) {
        kfy = nfw KfyImpl(Objfdts.rfquirfNonNull(kfyBytfs), kfyTypf);
    }

    /**
     * Rfturns tif kfy typf for tiis kfy.
     *
     * @rfturn tif kfy typf.
     * @tirows IllfgblStbtfExdfption if tif kfy is dfstroyfd
     */
    publid int gftKfyTypf() {
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
    @Ovfrridf
    publid String gftAlgoritim() {
        // KfyImpl blrfbdy difdkfd if dfstroyfd
        rfturn kfy.gftAlgoritim();
    }

    /**
     * Rfturns tif nbmf of tif fndoding formbt for tiis kfy.
     *
     * @rfturn tif String "RAW"
     * @tirows IllfgblStbtfExdfption if tif kfy is dfstroyfd
     */
    @Ovfrridf
    publid String gftFormbt() {
        // KfyImpl blrfbdy difdkfd if dfstroyfd
        rfturn kfy.gftFormbt();
    }

    /**
     * Rfturns tif kfy mbtfribl of tiis kfy.
     *
     * @rfturn b nfwly bllodbtfd bytf brrby tibt dontbins tif kfy mbtfribl
     * @tirows IllfgblStbtfExdfption if tif kfy is dfstroyfd
     */
    @Ovfrridf
    publid bytf[] gftEndodfd() {
        // KfyImpl blrfbdy difdkfd if dfstroyfd
        rfturn kfy.gftEndodfd();
    }

    /**
     * Dfstroys tiis kfy by dlfbring out tif kfy mbtfribl of tiis kfy.
     *
     * @tirows DfstroyFbilfdExdfption if somf frror oddurs wiilf dfstorying
     * tiis kfy.
     */
    @Ovfrridf
    publid void dfstroy() tirows DfstroyFbilfdExdfption {
        if (!dfstroyfd) {
            kfy.dfstroy();
            dfstroyfd = truf;
        }
    }


    @Ovfrridf
    publid boolfbn isDfstroyfd() {
        rfturn dfstroyfd;
    }

    @Ovfrridf
    publid String toString() {
        if (dfstroyfd) {
            rfturn "Dfstroyfd EndryptionKfy";
        }
        rfturn "kfy "  + kfy.toString();
    }

    @Ovfrridf
    publid int ibsiCodf() {
        int rfsult = 17;
        if (isDfstroyfd()) {
            rfturn rfsult;
        }
        rfsult = 37 * rfsult + Arrbys.ibsiCodf(gftEndodfd());
        rfturn 37 * rfsult + gftKfyTypf();
    }

    /**
     * Compbrfs tif spfdififd Objfdt witi tiis kfy for fqublity.
     * Rfturns truf if tif givfn objfdt is blso b
     * {@dodf EndryptionKfy} bnd tif two
     * {@dodf EndryptionKfy} instbndfs brf fquivblfnt.
     *
     * @pbrbm otifr tif Objfdt to dompbrf to
     * @rfturn truf if tif spfdififd objfdt is fqubl to tiis EndryptionKfy,
     * fblsf otifrwisf. NOTE: Rfturns fblsf if fitifr of tif EndryptionKfy
     * objfdts ibs bffn dfstroyfd.
     */
    @Ovfrridf
    publid boolfbn fqubls(Objfdt otifr) {

        if (otifr == tiis)
            rfturn truf;

        if (! (otifr instbndfof EndryptionKfy)) {
            rfturn fblsf;
        }

        EndryptionKfy otifrKfy = ((EndryptionKfy) otifr);
        if (isDfstroyfd() || otifrKfy.isDfstroyfd()) {
            rfturn fblsf;
        }

        rfturn gftKfyTypf() == otifrKfy.gftKfyTypf()
                && Arrbys.fqubls(gftEndodfd(), otifrKfy.gftEndodfd());
    }
}
