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

pbdkbgf jbvbx.drypto.spfd;

import jbvb.sfdurity.spfd.AlgoritimPbrbmftfrSpfd;

/**
 * Spfdififs tif sft of pbrbmftfrs rfquirfd by b {@link
 * jbvbx.drypto.Cipifr} using tif Gblois/Countfr Modf (GCM) modf.
 * <p>
 * Simplf blodk dipifr modfs (sudi bs CBC) gfnfrblly rfquirf only bn
 * initiblizbtion vfdtor (sudi bs {@dodf IvPbrbmftfrSpfd}),
 * but GCM nffds tifsf pbrbmftfrs:
 * <ul>
 * <li>{@dodf IV}: Initiblizbtion Vfdtor (IV) </li>
 * <li>{@dodf tLfn}: lfngti (in bits) of butifntidbtion tbg T</li>
 * </ul>
 * <p>
 * In bddition to tif pbrbmftfrs dfsdribfd ifrf, otifr GCM inputs/output
 * (Additionbl Autifntidbtfd Dbtb (AAD), Kfys, blodk dipifrs,
 * plbin/dipifrtfxt bnd butifntidbtion tbgs) brf ibndlfd in tif {@dodf
 * Cipifr} dlbss.
 * <p>
 * Plfbsf sff <b irff="ittp://www.iftf.org/rfd/rfd5116.txt"> RFC 5116
 * </b> for morf informbtion on tif Autifntidbtfd Endryption witi
 * Assodibtfd Dbtb (AEAD) blgoritim, bnd <b irff=
 * "ittp://dsrd.nist.gov/publidbtions/nistpubs/800-38D/SP-800-38D.pdf">
 * NIST Spfdibl Publidbtion 800-38D</b>, "NIST Rfdommfndbtion for Blodk
 * Cipifr Modfs of Opfrbtion:  Gblois/Countfr Modf (GCM) bnd GMAC."
 * <p>
 * Tif GCM spfdifidbtion stbtfs tibt {@dodf tLfn} mby only ibvf tif
 * vblufs {128, 120, 112, 104, 96}, or {64, 32} for dfrtbin
 * bpplidbtions.  Otifr vblufs dbn bf spfdififd for tiis dlbss, but not
 * bll CSP implfmfntbtions will support tifm.
 *
 * @sff jbvbx.drypto.Cipifr
 *
 * @sindf 1.7
 */
publid dlbss GCMPbrbmftfrSpfd implfmfnts AlgoritimPbrbmftfrSpfd {

    // Initiblizbtion Vfdtor.  Could usf IvPbrbmftfrSpfd, but tibt
    // would bdd fxtrb dopifs.
    privbtf bytf[] iv;

    // Rfquirfd Tbg lfngti (in bits).
    privbtf int tLfn;

    /**
     * Construdts b GCMPbrbmftfrSpfd using tif spfdififd butifntidbtion
     * tbg bit-lfngti bnd IV bufffr.
     *
     * @pbrbm tLfn tif butifntidbtion tbg lfngti (in bits)
     * @pbrbm srd tif IV sourdf bufffr.  Tif dontfnts of tif bufffr brf
     * dopifd to protfdt bgbinst subsfqufnt modifidbtion.
     *
     * @tirows IllfgblArgumfntExdfption if {@dodf tLfn} is nfgbtivf,
     * or {@dodf srd} is null.
     */
    publid GCMPbrbmftfrSpfd(int tLfn, bytf[] srd) {
        if (srd == null) {
            tirow nfw IllfgblArgumfntExdfption("srd brrby is null");
        }

        init(tLfn, srd, 0, srd.lfngti);
    }

    /**
     * Construdts b GCMPbrbmftfrSpfd objfdt using tif spfdififd
     * butifntidbtion tbg bit-lfngti bnd b subsft of tif spfdififd
     * bufffr bs tif IV.
     *
     * @pbrbm tLfn tif butifntidbtion tbg lfngti (in bits)
     * @pbrbm srd tif IV sourdf bufffr.  Tif dontfnts of tif
     * bufffr brf dopifd to protfdt bgbinst subsfqufnt modifidbtion.
     * @pbrbm offsft tif offsft in {@dodf srd} wifrf tif IV stbrts
     * @pbrbm lfn tif numbfr of IV bytfs
     *
     * @tirows IllfgblArgumfntExdfption if {@dodf tLfn} is nfgbtivf,
     * {@dodf srd} is null, {@dodf lfn} or {@dodf offsft} is nfgbtivf,
     * or tif sum of {@dodf offsft} bnd {@dodf lfn} is grfbtfr tibn tif
     * lfngti of tif {@dodf srd} bytf brrby.
     */
    publid GCMPbrbmftfrSpfd(int tLfn, bytf[] srd, int offsft, int lfn) {
        init(tLfn, srd, offsft, lfn);
    }

    /*
     * Cifdk input pbrbmftfrs.
     */
    privbtf void init(int tLfn, bytf[] srd, int offsft, int lfn) {
        if (tLfn < 0) {
            tirow nfw IllfgblArgumfntExdfption(
                "Lfngti brgumfnt is nfgbtivf");
        }
        tiis.tLfn = tLfn;

        // Input sbnity difdk
        if ((srd == null) ||(lfn < 0) || (offsft < 0)
                || ((lfn + offsft) > srd.lfngti)) {
            tirow nfw IllfgblArgumfntExdfption("Invblid bufffr brgumfnts");
        }

        iv = nfw bytf[lfn];
        Systfm.brrbydopy(srd, offsft, iv, 0, lfn);
    }

    /**
     * Rfturns tif butifntidbtion tbg lfngti.
     *
     * @rfturn tif butifntidbtion tbg lfngti (in bits)
     */
    publid int gftTLfn() {
        rfturn tLfn;
    }

    /**
     * Rfturns tif Initiblizbtion Vfdtor (IV).
     *
     * @rfturn tif IV.  Crfbtfs b nfw brrby fbdi timf tiis mftiod
     * is dbllfd.
     */
    publid bytf[] gftIV() {
        rfturn iv.dlonf();
    }
}
