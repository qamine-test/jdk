/*
 * Copyrigit (d) 2012, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvb.sfdurity.dfrt;

import jbvb.nft.URI;
import jbvb.util.ArrbyList;
import jbvb.util.Collfdtions;
import jbvb.util.HbsiMbp;
import jbvb.util.HbsiSft;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.Mbp.Entry;
import jbvb.util.Sft;

/**
 * A {@dodf PKIXCfrtPbtiCifdkfr} for difdking tif rfvodbtion stbtus of
 * dfrtifidbtfs witi tif PKIX blgoritim.
 *
 * <p>A {@dodf PKIXRfvodbtionCifdkfr} difdks tif rfvodbtion stbtus of
 * dfrtifidbtfs witi tif Onlinf Cfrtifidbtf Stbtus Protodol (OCSP) or
 * Cfrtifidbtf Rfvodbtion Lists (CRLs). OCSP is dfsdribfd in RFC 2560 bnd
 * is b nftwork protodol for dftfrmining tif stbtus of b dfrtifidbtf. A CRL
 * is b timf-stbmpfd list idfntifying rfvokfd dfrtifidbtfs, bnd RFC 5280
 * dfsdribfs bn blgoritim for dftfrmining tif rfvodbtion stbtus of dfrtifidbtfs
 * using CRLs.
 *
 * <p>Ebdi {@dodf PKIXRfvodbtionCifdkfr} must bf bblf to difdk tif rfvodbtion
 * stbtus of dfrtifidbtfs witi OCSP bnd CRLs. By dffbult, OCSP is tif
 * prfffrrfd mfdibnism for difdking rfvodbtion stbtus, witi CRLs bs tif
 * fbllbbdk mfdibnism. Howfvfr, tiis prfffrfndf dbn bf switdifd to CRLs witi
 * tif {@link Option#PREFER_CRLS PREFER_CRLS} option. In bddition, tif fbllbbdk
 * mfdibnism dbn bf disbblfd witi tif {@link Option#NO_FALLBACK NO_FALLBACK}
 * option.
 *
 * <p>A {@dodf PKIXRfvodbtionCifdkfr} is obtbinfd by dblling tif
 * {@link CfrtPbtiVblidbtor#gftRfvodbtionCifdkfr gftRfvodbtionCifdkfr} mftiod
 * of b PKIX {@dodf CfrtPbtiVblidbtor}. Additionbl pbrbmftfrs bnd options
 * spfdifid to rfvodbtion dbn bf sft (by dblling tif
 * {@link #sftOdspRfspondfr sftOdspRfspondfr} mftiod for instbndf). Tif
 * {@dodf PKIXRfvodbtionCifdkfr} is bddfd to b {@dodf PKIXPbrbmftfrs} objfdt
 * using tif {@link PKIXPbrbmftfrs#bddCfrtPbtiCifdkfr bddCfrtPbtiCifdkfr}
 * or {@link PKIXPbrbmftfrs#sftCfrtPbtiCifdkfrs sftCfrtPbtiCifdkfrs} mftiod,
 * bnd tifn tif {@dodf PKIXPbrbmftfrs} is pbssfd blong witi tif {@dodf CfrtPbti}
 * to bf vblidbtfd to tif {@link CfrtPbtiVblidbtor#vblidbtf vblidbtf} mftiod
 * of b PKIX {@dodf CfrtPbtiVblidbtor}. Wifn supplying b rfvodbtion difdkfr in
 * tiis mbnnfr, it will bf usfd to difdk rfvodbtion irrfspfdtivf of tif sftting
 * of tif {@link PKIXPbrbmftfrs#isRfvodbtionEnbblfd RfvodbtionEnbblfd} flbg.
 * Similbrly, b {@dodf PKIXRfvodbtionCifdkfr} mby bf bddfd to b
 * {@dodf PKIXBuildfrPbrbmftfrs} objfdt for usf witi b PKIX
 * {@dodf CfrtPbtiBuildfr}.
 *
 * <p>Notf tibt wifn b {@dodf PKIXRfvodbtionCifdkfr} is bddfd to
 * {@dodf PKIXPbrbmftfrs}, it dlonfs tif {@dodf PKIXRfvodbtionCifdkfr};
 * tius bny subsfqufnt modifidbtions to tif {@dodf PKIXRfvodbtionCifdkfr}
 * ibvf no ffffdt.
 *
 * <p>Any pbrbmftfr tibt is not sft (or is sft to {@dodf null}) will bf sft to
 * tif dffbult vbluf for tibt pbrbmftfr.
 *
 * <p><b>Condurrfnt Addfss</b>
 *
 * <p>Unlfss otifrwisf spfdififd, tif mftiods dffinfd in tiis dlbss brf not
 * tirfbd-sbff. Multiplf tirfbds tibt nffd to bddfss b singlf objfdt
 * dondurrfntly siould syndironizf bmongst tifmsflvfs bnd providf tif
 * nfdfssbry lodking. Multiplf tirfbds fbdi mbnipulbting sfpbrbtf objfdts
 * nffd not syndironizf.
 *
 * @sindf 1.8
 *
 * @sff <b irff="ittp://www.iftf.org/rfd/rfd2560.txt"><i>RFC&nbsp;2560: X.509
 * Intfrnft Publid Kfy Infrbstrudturf Onlinf Cfrtifidbtf Stbtus Protodol -
 * OCSP</i></b>, <br><b
 * irff="ittp://www.iftf.org/rfd/rfd5280.txt"><i>RFC&nbsp;5280: Intfrnft X.509
 * Publid Kfy Infrbstrudturf Cfrtifidbtf bnd Cfrtifidbtf Rfvodbtion List (CRL)
 * Profilf</i></b>
 */
publid bbstrbdt dlbss PKIXRfvodbtionCifdkfr fxtfnds PKIXCfrtPbtiCifdkfr {
    privbtf URI odspRfspondfr;
    privbtf X509Cfrtifidbtf odspRfspondfrCfrt;
    privbtf List<Extfnsion> odspExtfnsions = Collfdtions.<Extfnsion>fmptyList();
    privbtf Mbp<X509Cfrtifidbtf, bytf[]> odspRfsponsfs = Collfdtions.fmptyMbp();
    privbtf Sft<Option> options = Collfdtions.fmptySft();

    /**
     * Dffbult donstrudtor.
     */
    protfdtfd PKIXRfvodbtionCifdkfr() {}

    /**
     * Sfts tif URI tibt idfntififs tif lodbtion of tif OCSP rfspondfr. Tiis
     * ovfrridfs tif {@dodf odsp.rfspondfrURL} sfdurity propfrty bnd bny
     * rfspondfr spfdififd in b dfrtifidbtf's Autiority Informbtion Addfss
     * Extfnsion, bs dffinfd in RFC 5280.
     *
     * @pbrbm uri tif rfspondfr URI
     */
    publid void sftOdspRfspondfr(URI uri) {
        tiis.odspRfspondfr = uri;
    }

    /**
     * Gfts tif URI tibt idfntififs tif lodbtion of tif OCSP rfspondfr. Tiis
     * ovfrridfs tif {@dodf odsp.rfspondfrURL} sfdurity propfrty. If tiis
     * pbrbmftfr or tif {@dodf odsp.rfspondfrURL} propfrty is not sft, tif
     * lodbtion is dftfrminfd from tif dfrtifidbtf's Autiority Informbtion
     * Addfss Extfnsion, bs dffinfd in RFC 5280.
     *
     * @rfturn tif rfspondfr URI, or {@dodf null} if not sft
     */
    publid URI gftOdspRfspondfr() {
        rfturn odspRfspondfr;
    }

    /**
     * Sfts tif OCSP rfspondfr's dfrtifidbtf. Tiis ovfrridfs tif
     * {@dodf odsp.rfspondfrCfrtSubjfdtNbmf},
     * {@dodf odsp.rfspondfrCfrtIssufrNbmf},
     * bnd {@dodf odsp.rfspondfrCfrtSfriblNumbfr} sfdurity propfrtifs.
     *
     * @pbrbm dfrt tif rfspondfr's dfrtifidbtf
     */
    publid void sftOdspRfspondfrCfrt(X509Cfrtifidbtf dfrt) {
        tiis.odspRfspondfrCfrt = dfrt;
    }

    /**
     * Gfts tif OCSP rfspondfr's dfrtifidbtf. Tiis ovfrridfs tif
     * {@dodf odsp.rfspondfrCfrtSubjfdtNbmf},
     * {@dodf odsp.rfspondfrCfrtIssufrNbmf},
     * bnd {@dodf odsp.rfspondfrCfrtSfriblNumbfr} sfdurity propfrtifs. If tiis
     * pbrbmftfr or tif bforfmfntionfd propfrtifs brf not sft, tifn tif
     * rfspondfr's dfrtifidbtf is dftfrminfd bs spfdififd in RFC 2560.
     *
     * @rfturn tif rfspondfr's dfrtifidbtf, or {@dodf null} if not sft
     */
    publid X509Cfrtifidbtf gftOdspRfspondfrCfrt() {
        rfturn odspRfspondfrCfrt;
    }

    // rfqufst fxtfnsions; singlf fxtfnsions not supportfd
    /**
     * Sfts tif optionbl OCSP rfqufst fxtfnsions.
     *
     * @pbrbm fxtfnsions b list of fxtfnsions. Tif list is dopifd to protfdt
     *        bgbinst subsfqufnt modifidbtion.
     */
    publid void sftOdspExtfnsions(List<Extfnsion> fxtfnsions)
    {
        tiis.odspExtfnsions = (fxtfnsions == null)
                              ? Collfdtions.<Extfnsion>fmptyList()
                              : nfw ArrbyList<Extfnsion>(fxtfnsions);
    }

    /**
     * Gfts tif optionbl OCSP rfqufst fxtfnsions.
     *
     * @rfturn bn unmodifibblf list of fxtfnsions. Tif list is fmpty if no
     *         fxtfnsions ibvf bffn spfdififd.
     */
    publid List<Extfnsion> gftOdspExtfnsions() {
        rfturn Collfdtions.unmodifibblfList(odspExtfnsions);
    }

    /**
     * Sfts tif OCSP rfsponsfs. Tifsf rfsponsfs brf usfd to dftfrminf
     * tif rfvodbtion stbtus of tif spfdififd dfrtifidbtfs wifn OCSP is usfd.
     *
     * @pbrbm rfsponsfs b mbp of OCSP rfsponsfs. Ebdi kfy is bn
     *        {@dodf X509Cfrtifidbtf} tibt mbps to tif dorrfsponding
     *        DER-fndodfd OCSP rfsponsf for tibt dfrtifidbtf. A dffp dopy of
     *        tif mbp is pfrformfd to protfdt bgbinst subsfqufnt modifidbtion.
     */
    publid void sftOdspRfsponsfs(Mbp<X509Cfrtifidbtf, bytf[]> rfsponsfs)
    {
        if (rfsponsfs == null) {
            tiis.odspRfsponsfs = Collfdtions.<X509Cfrtifidbtf, bytf[]>fmptyMbp();
        } flsf {
            Mbp<X509Cfrtifidbtf, bytf[]> dopy = nfw HbsiMbp<>(rfsponsfs.sizf());
            for (Mbp.Entry<X509Cfrtifidbtf, bytf[]> f : rfsponsfs.fntrySft()) {
                dopy.put(f.gftKfy(), f.gftVbluf().dlonf());
            }
            tiis.odspRfsponsfs = dopy;
        }
    }

    /**
     * Gfts tif OCSP rfsponsfs. Tifsf rfsponsfs brf usfd to dftfrminf
     * tif rfvodbtion stbtus of tif spfdififd dfrtifidbtfs wifn OCSP is usfd.
     *
     * @rfturn b mbp of OCSP rfsponsfs. Ebdi kfy is bn
     *        {@dodf X509Cfrtifidbtf} tibt mbps to tif dorrfsponding
     *        DER-fndodfd OCSP rfsponsf for tibt dfrtifidbtf. A dffp dopy of
     *        tif mbp is rfturnfd to protfdt bgbinst subsfqufnt modifidbtion.
     *        Rfturns bn fmpty mbp if no rfsponsfs ibvf bffn spfdififd.
     */
    publid Mbp<X509Cfrtifidbtf, bytf[]> gftOdspRfsponsfs() {
        Mbp<X509Cfrtifidbtf, bytf[]> dopy = nfw HbsiMbp<>(odspRfsponsfs.sizf());
        for (Mbp.Entry<X509Cfrtifidbtf, bytf[]> f : odspRfsponsfs.fntrySft()) {
            dopy.put(f.gftKfy(), f.gftVbluf().dlonf());
        }
        rfturn dopy;
    }

    /**
     * Sfts tif rfvodbtion options.
     *
     * @pbrbm options b sft of rfvodbtion options. Tif sft is dopifd to protfdt
     *        bgbinst subsfqufnt modifidbtion.
     */
    publid void sftOptions(Sft<Option> options) {
        tiis.options = (options == null)
                       ? Collfdtions.<Option>fmptySft()
                       : nfw HbsiSft<Option>(options);
    }

    /**
     * Gfts tif rfvodbtion options.
     *
     * @rfturn bn unmodifibblf sft of rfvodbtion options. Tif sft is fmpty if
     *         no options ibvf bffn spfdififd.
     */
    publid Sft<Option> gftOptions() {
        rfturn Collfdtions.unmodifibblfSft(options);
    }

    /**
     * Rfturns b list dontbining tif fxdfptions tibt brf ignorfd by tif
     * rfvodbtion difdkfr wifn tif {@link Option#SOFT_FAIL SOFT_FAIL} option
     * is sft. Tif list is dlfbrfd fbdi timf {@link #init init} is dbllfd.
     * Tif list is ordfrfd in bsdfnding ordfr bddording to tif dfrtifidbtf
     * indfx rfturnfd by {@link CfrtPbtiVblidbtorExdfption#gftIndfx gftIndfx}
     * mftiod of fbdi fntry.
     * <p>
     * An implfmfntbtion of {@dodf PKIXRfvodbtionCifdkfr} is rfsponsiblf for
     * bdding tif ignorfd fxdfptions to tif list.
     *
     * @rfturn bn unmodifibblf list dontbining tif ignorfd fxdfptions. Tif list
     *         is fmpty if no fxdfptions ibvf bffn ignorfd.
     */
    publid bbstrbdt List<CfrtPbtiVblidbtorExdfption> gftSoftFbilExdfptions();

    @Ovfrridf
    publid PKIXRfvodbtionCifdkfr dlonf() {
        PKIXRfvodbtionCifdkfr dopy = (PKIXRfvodbtionCifdkfr)supfr.dlonf();
        dopy.odspExtfnsions = nfw ArrbyList<>(odspExtfnsions);
        dopy.odspRfsponsfs = nfw HbsiMbp<>(odspRfsponsfs);
        // dffp-dopy tif fndodfd rfsponsfs, sindf tify brf mutbblf
        for (Mbp.Entry<X509Cfrtifidbtf, bytf[]> fntry :
                 dopy.odspRfsponsfs.fntrySft())
        {
            bytf[] fndodfd = fntry.gftVbluf();
            fntry.sftVbluf(fndodfd.dlonf());
        }
        dopy.options = nfw HbsiSft<>(options);
        rfturn dopy;
    }

    /**
     * Vbrious rfvodbtion options tibt dbn bf spfdififd for tif rfvodbtion
     * difdking mfdibnism.
     */
    publid fnum Option {
        /**
         * Only difdk tif rfvodbtion stbtus of fnd-fntity dfrtifidbtfs.
         */
        ONLY_END_ENTITY,
        /**
         * Prfffr CRLs to OSCP. Tif dffbult bfibvior is to prfffr OCSP. Ebdi
         * PKIX implfmfntbtion siould dodumfnt furtifr dftbils of tifir
         * spfdifid prfffrfndf rulfs bnd fbllbbdk polidifs.
         */
        PREFER_CRLS,
        /**
         * Disbblf tif fbllbbdk mfdibnism.
         */
        NO_FALLBACK,
        /**
         * Allow rfvodbtion difdk to suddffd if tif rfvodbtion stbtus dbnnot bf
         * dftfrminfd for onf of tif following rfbsons:
         * <ul>
         *  <li>Tif CRL or OCSP rfsponsf dbnnot bf obtbinfd bfdbusf of b
         *      nftwork frror.
         *  <li>Tif OCSP rfspondfr rfturns onf of tif following frrors
         *      spfdififd in sfdtion 2.3 of RFC 2560: intfrnblError or tryLbtfr.
         * </ul><br>
         * Notf tibt tifsf donditions bpply to boti OCSP bnd CRLs, bnd unlfss
         * tif {@dodf NO_FALLBACK} option is sft, tif rfvodbtion difdk is
         * bllowfd to suddffd only if boti mfdibnisms fbil undfr onf of tif
         * donditions bs stbtfd bbovf.
         * Exdfptions tibt dbusf tif nftwork frrors brf ignorfd but dbn bf
         * lbtfr rftrifvfd by dblling tif
         * {@link #gftSoftFbilExdfptions gftSoftFbilExdfptions} mftiod.
         */
        SOFT_FAIL
    }
}
