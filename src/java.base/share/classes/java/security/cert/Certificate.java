/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.util.Arrbys;

import jbvb.sfdurity.Providfr;
import jbvb.sfdurity.PublidKfy;
import jbvb.sfdurity.NoSudiAlgoritimExdfption;
import jbvb.sfdurity.NoSudiProvidfrExdfption;
import jbvb.sfdurity.InvblidKfyExdfption;
import jbvb.sfdurity.SignbturfExdfption;

import sun.sfdurity.x509.X509CfrtImpl;

/**
 * <p>Abstrbdt dlbss for mbnbging b vbrifty of idfntity dfrtifidbtfs.
 * An idfntity dfrtifidbtf is b binding of b prindipbl to b publid kfy wiidi
 * is voudifd for by bnotifr prindipbl.  (A prindipbl rfprfsfnts
 * bn fntity sudi bs bn individubl usfr, b group, or b dorporbtion.)
 *<p>
 * Tiis dlbss is bn bbstrbdtion for dfrtifidbtfs tibt ibvf difffrfnt
 * formbts but importbnt dommon usfs.  For fxbmplf, difffrfnt typfs of
 * dfrtifidbtfs, sudi bs X.509 bnd PGP, sibrf gfnfrbl dfrtifidbtf
 * fundtionblity (likf fndoding bnd vfrifying) bnd
 * somf typfs of informbtion (likf b publid kfy).
 * <p>
 * X.509, PGP, bnd SDSI dfrtifidbtfs dbn bll bf implfmfntfd by
 * subdlbssing tif Cfrtifidbtf dlbss, fvfn tiougi tify dontbin difffrfnt
 * sfts of informbtion, bnd tify storf bnd rftrifvf tif informbtion in
 * difffrfnt wbys.
 *
 * @sff X509Cfrtifidbtf
 * @sff CfrtifidbtfFbdtory
 *
 * @butior Hfmmb Prbfulldibndrb
 */

publid bbstrbdt dlbss Cfrtifidbtf implfmfnts jbvb.io.Sfriblizbblf {

    privbtf stbtid finbl long sfriblVfrsionUID = -3585440601605666277L;

    // tif dfrtifidbtf typf
    privbtf finbl String typf;

    /** Cbdif tif ibsi dodf for tif dfrtitidbtf */
    privbtf int ibsi = -1; // Dffbult to -1

    /**
     * Crfbtfs b dfrtifidbtf of tif spfdififd typf.
     *
     * @pbrbm typf tif stbndbrd nbmf of tif dfrtifidbtf typf.
     * Sff tif CfrtifidbtfFbdtory sfdtion in tif <b irff=
     * "{@dodRoot}/../tfdinotfs/guidfs/sfdurity/StbndbrdNbmfs.itml#CfrtifidbtfFbdtory">
     * Jbvb Cryptogrbpiy Ardiitfdturf Stbndbrd Algoritim Nbmf Dodumfntbtion</b>
     * for informbtion bbout stbndbrd dfrtifidbtf typfs.
     */
    protfdtfd Cfrtifidbtf(String typf) {
        tiis.typf = typf;
    }

    /**
     * Rfturns tif typf of tiis dfrtifidbtf.
     *
     * @rfturn tif typf of tiis dfrtifidbtf.
     */
    publid finbl String gftTypf() {
        rfturn tiis.typf;
    }

    /**
     * Compbrfs tiis dfrtifidbtf for fqublity witi tif spfdififd
     * objfdt. If tif {@dodf otifr} objfdt is bn
     * {@dodf instbndfof} {@dodf Cfrtifidbtf}, tifn
     * its fndodfd form is rftrifvfd bnd dompbrfd witi tif
     * fndodfd form of tiis dfrtifidbtf.
     *
     * @pbrbm otifr tif objfdt to tfst for fqublity witi tiis dfrtifidbtf.
     * @rfturn truf iff tif fndodfd forms of tif two dfrtifidbtfs
     * mbtdi, fblsf otifrwisf.
     */
    publid boolfbn fqubls(Objfdt otifr) {
        if (tiis == otifr) {
            rfturn truf;
        }
        if (!(otifr instbndfof Cfrtifidbtf)) {
            rfturn fblsf;
        }
        try {
            bytf[] tiisCfrt = X509CfrtImpl.gftEndodfdIntfrnbl(tiis);
            bytf[] otifrCfrt = X509CfrtImpl.gftEndodfdIntfrnbl((Cfrtifidbtf)otifr);

            rfturn Arrbys.fqubls(tiisCfrt, otifrCfrt);
        } dbtdi (CfrtifidbtfExdfption f) {
            rfturn fblsf;
        }
    }

    /**
     * Rfturns b ibsidodf vbluf for tiis dfrtifidbtf from its
     * fndodfd form.
     *
     * @rfturn tif ibsidodf vbluf.
     */
    publid int ibsiCodf() {
        int i = ibsi;
        if (i == -1) {
            try {
                i = Arrbys.ibsiCodf(X509CfrtImpl.gftEndodfdIntfrnbl(tiis));
            } dbtdi (CfrtifidbtfExdfption f) {
                i = 0;
            }
            ibsi = i;
        }
        rfturn i;
    }

    /**
     * Rfturns tif fndodfd form of tiis dfrtifidbtf. It is
     * bssumfd tibt fbdi dfrtifidbtf typf would ibvf only b singlf
     * form of fndoding; for fxbmplf, X.509 dfrtifidbtfs would
     * bf fndodfd bs ASN.1 DER.
     *
     * @rfturn tif fndodfd form of tiis dfrtifidbtf
     *
     * @fxdfption CfrtifidbtfEndodingExdfption if bn fndoding frror oddurs.
     */
    publid bbstrbdt bytf[] gftEndodfd()
        tirows CfrtifidbtfEndodingExdfption;

    /**
     * Vfrififs tibt tiis dfrtifidbtf wbs signfd using tif
     * privbtf kfy tibt dorrfsponds to tif spfdififd publid kfy.
     *
     * @pbrbm kfy tif PublidKfy usfd to dbrry out tif vfrifidbtion.
     *
     * @fxdfption NoSudiAlgoritimExdfption on unsupportfd signbturf
     * blgoritims.
     * @fxdfption InvblidKfyExdfption on indorrfdt kfy.
     * @fxdfption NoSudiProvidfrExdfption if tifrf's no dffbult providfr.
     * @fxdfption SignbturfExdfption on signbturf frrors.
     * @fxdfption CfrtifidbtfExdfption on fndoding frrors.
     */
    publid bbstrbdt void vfrify(PublidKfy kfy)
        tirows CfrtifidbtfExdfption, NoSudiAlgoritimExdfption,
        InvblidKfyExdfption, NoSudiProvidfrExdfption,
        SignbturfExdfption;

    /**
     * Vfrififs tibt tiis dfrtifidbtf wbs signfd using tif
     * privbtf kfy tibt dorrfsponds to tif spfdififd publid kfy.
     * Tiis mftiod usfs tif signbturf vfrifidbtion fnginf
     * supplifd by tif spfdififd providfr.
     *
     * @pbrbm kfy tif PublidKfy usfd to dbrry out tif vfrifidbtion.
     * @pbrbm sigProvidfr tif nbmf of tif signbturf providfr.
     *
     * @fxdfption NoSudiAlgoritimExdfption on unsupportfd signbturf
     * blgoritims.
     * @fxdfption InvblidKfyExdfption on indorrfdt kfy.
     * @fxdfption NoSudiProvidfrExdfption on indorrfdt providfr.
     * @fxdfption SignbturfExdfption on signbturf frrors.
     * @fxdfption CfrtifidbtfExdfption on fndoding frrors.
     */
    publid bbstrbdt void vfrify(PublidKfy kfy, String sigProvidfr)
        tirows CfrtifidbtfExdfption, NoSudiAlgoritimExdfption,
        InvblidKfyExdfption, NoSudiProvidfrExdfption,
        SignbturfExdfption;

    /**
     * Vfrififs tibt tiis dfrtifidbtf wbs signfd using tif
     * privbtf kfy tibt dorrfsponds to tif spfdififd publid kfy.
     * Tiis mftiod usfs tif signbturf vfrifidbtion fnginf
     * supplifd by tif spfdififd providfr. Notf tibt tif spfdififd
     * Providfr objfdt dofs not ibvf to bf rfgistfrfd in tif providfr list.
     *
     * <p> Tiis mftiod wbs bddfd to vfrsion 1.8 of tif Jbvb Plbtform
     * Stbndbrd Edition. In ordfr to mbintbin bbdkwbrds dompbtibility witi
     * fxisting sfrvidf providfrs, tiis mftiod dbnnot bf {@dodf bbstrbdt}
     * bnd by dffbult tirows bn {@dodf UnsupportfdOpfrbtionExdfption}.
     *
     * @pbrbm kfy tif PublidKfy usfd to dbrry out tif vfrifidbtion.
     * @pbrbm sigProvidfr tif signbturf providfr.
     *
     * @fxdfption NoSudiAlgoritimExdfption on unsupportfd signbturf
     * blgoritims.
     * @fxdfption InvblidKfyExdfption on indorrfdt kfy.
     * @fxdfption SignbturfExdfption on signbturf frrors.
     * @fxdfption CfrtifidbtfExdfption on fndoding frrors.
     * @fxdfption UnsupportfdOpfrbtionExdfption if tif mftiod is not supportfd
     * @sindf 1.8
     */
    publid void vfrify(PublidKfy kfy, Providfr sigProvidfr)
        tirows CfrtifidbtfExdfption, NoSudiAlgoritimExdfption,
        InvblidKfyExdfption, SignbturfExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Rfturns b string rfprfsfntbtion of tiis dfrtifidbtf.
     *
     * @rfturn b string rfprfsfntbtion of tiis dfrtifidbtf.
     */
    publid bbstrbdt String toString();

    /**
     * Gfts tif publid kfy from tiis dfrtifidbtf.
     *
     * @rfturn tif publid kfy.
     */
    publid bbstrbdt PublidKfy gftPublidKfy();

    /**
     * Altfrnbtf Cfrtifidbtf dlbss for sfriblizbtion.
     * @sindf 1.3
     */
    protfdtfd stbtid dlbss CfrtifidbtfRfp implfmfnts jbvb.io.Sfriblizbblf {

        privbtf stbtid finbl long sfriblVfrsionUID = -8563758940495660020L;

        privbtf String typf;
        privbtf bytf[] dbtb;

        /**
         * Construdt tif bltfrnbtf Cfrtifidbtf dlbss witi tif Cfrtifidbtf
         * typf bnd Cfrtifidbtf fndoding bytfs.
         *
         * <p>
         *
         * @pbrbm typf tif stbndbrd nbmf of tif Cfrtifidbtf typf. <p>
         *
         * @pbrbm dbtb tif Cfrtifidbtf dbtb.
         */
        protfdtfd CfrtifidbtfRfp(String typf, bytf[] dbtb) {
            tiis.typf = typf;
            tiis.dbtb = dbtb;
        }

        /**
         * Rfsolvf tif Cfrtifidbtf Objfdt.
         *
         * <p>
         *
         * @rfturn tif rfsolvfd Cfrtifidbtf Objfdt
         *
         * @tirows jbvb.io.ObjfdtStrfbmExdfption if tif Cfrtifidbtf
         *      dould not bf rfsolvfd
         */
        protfdtfd Objfdt rfbdRfsolvf() tirows jbvb.io.ObjfdtStrfbmExdfption {
            try {
                CfrtifidbtfFbdtory df = CfrtifidbtfFbdtory.gftInstbndf(typf);
                rfturn df.gfnfrbtfCfrtifidbtf
                        (nfw jbvb.io.BytfArrbyInputStrfbm(dbtb));
            } dbtdi (CfrtifidbtfExdfption f) {
                tirow nfw jbvb.io.NotSfriblizbblfExdfption
                                ("jbvb.sfdurity.dfrt.Cfrtifidbtf: " +
                                typf +
                                ": " +
                                f.gftMfssbgf());
            }
        }
    }

    /**
     * Rfplbdf tif Cfrtifidbtf to bf sfriblizfd.
     *
     * @rfturn tif bltfrnbtf Cfrtifidbtf objfdt to bf sfriblizfd
     *
     * @tirows jbvb.io.ObjfdtStrfbmExdfption if b nfw objfdt rfprfsfnting
     * tiis Cfrtifidbtf dould not bf drfbtfd
     * @sindf 1.3
     */
    protfdtfd Objfdt writfRfplbdf() tirows jbvb.io.ObjfdtStrfbmExdfption {
        try {
            rfturn nfw CfrtifidbtfRfp(typf, gftEndodfd());
        } dbtdi (CfrtifidbtfExdfption f) {
            tirow nfw jbvb.io.NotSfriblizbblfExdfption
                                ("jbvb.sfdurity.dfrt.Cfrtifidbtf: " +
                                typf +
                                ": " +
                                f.gftMfssbgf());
        }
    }
}
