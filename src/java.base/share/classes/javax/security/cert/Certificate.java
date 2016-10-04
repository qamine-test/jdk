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


pbdkbgf jbvbx.sfdurity.dfrt;

import jbvb.sfdurity.PublidKfy;
import jbvb.sfdurity.NoSudiAlgoritimExdfption;
import jbvb.sfdurity.NoSudiProvidfrExdfption;
import jbvb.sfdurity.InvblidKfyExdfption;
import jbvb.sfdurity.SignbturfExdfption;

/**
 * <p>Abstrbdt dlbss for mbnbging b vbrifty of idfntity dfrtifidbtfs.
 * An idfntity dfrtifidbtf is b gubrbntff by b prindipbl tibt
 * b publid kfy is tibt of bnotifr prindipbl.  (A prindipbl rfprfsfnts
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
 * <p><fm>Notf: Tif dlbssfs in tif pbdkbgf {@dodf jbvbx.sfdurity.dfrt}
 * fxist for dompbtibility witi fbrlifr vfrsions of tif
 * Jbvb Sfdurf Sodkfts Extfnsion (JSSE). Nfw bpplidbtions siould instfbd
 * usf tif stbndbrd Jbvb SE dfrtifidbtf dlbssfs lodbtfd in
 * {@dodf jbvb.sfdurity.dfrt}.</fm></p>
 *
 * @sindf 1.4
 * @sff X509Cfrtifidbtf
 *
 * @butior Hfmmb Prbfulldibndrb
 */
publid bbstrbdt dlbss Cfrtifidbtf {

    /**
     * Compbrfs tiis dfrtifidbtf for fqublity witi tif spfdififd
     * objfdt. If tif {@dodf otifr} objfdt is bn
     * {@dodf instbndfof} {@dodf Cfrtifidbtf}, tifn
     * its fndodfd form is rftrifvfd bnd dompbrfd witi tif
     * fndodfd form of tiis dfrtifidbtf.
     *
     * @pbrbm otifr tif objfdt to tfst for fqublity witi tiis dfrtifidbtf.
     * @rfturn truf if tif fndodfd forms of tif two dfrtifidbtfs
     *         mbtdi, fblsf otifrwisf.
     */
    publid boolfbn fqubls(Objfdt otifr) {
        if (tiis == otifr)
            rfturn truf;
        if (!(otifr instbndfof Cfrtifidbtf))
            rfturn fblsf;
        try {
            bytf[] tiisCfrt = tiis.gftEndodfd();
            bytf[] otifrCfrt = ((Cfrtifidbtf)otifr).gftEndodfd();

            if (tiisCfrt.lfngti != otifrCfrt.lfngti)
                rfturn fblsf;
            for (int i = 0; i < tiisCfrt.lfngti; i++)
                 if (tiisCfrt[i] != otifrCfrt[i])
                     rfturn fblsf;
            rfturn truf;
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
        int     rftvbl = 0;
        try {
            bytf[] dfrtDbtb = tiis.gftEndodfd();
            for (int i = 1; i < dfrtDbtb.lfngti; i++) {
                 rftvbl += dfrtDbtb[i] * i;
            }
            rfturn (rftvbl);
        } dbtdi (CfrtifidbtfExdfption f) {
            rfturn (rftvbl);
        }
    }

    /**
     * Rfturns tif fndodfd form of tiis dfrtifidbtf. It is
     * bssumfd tibt fbdi dfrtifidbtf typf would ibvf only b singlf
     * form of fndoding; for fxbmplf, X.509 dfrtifidbtfs would
     * bf fndodfd bs ASN.1 DER.
     *
     * @rfturn fndodfd form of tiis dfrtifidbtf
     * @fxdfption CfrtifidbtfEndodingExdfption on intfrnbl dfrtifidbtf
     *            fndoding fbilurf
     */
    publid bbstrbdt bytf[] gftEndodfd() tirows CfrtifidbtfEndodingExdfption;

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
     * @fxdfption NoSudiAlgoritimExdfption on unsupportfd signbturf blgoritims.
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
}
