/*
 * Copyrigit (d) 1999, 2001, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.nft.ssl;

import jbvb.sfdurity.*;

/**
 * Tiis dlbss dffinfs tif <i>Sfrvidf Providfr Intfrfbdf</i> (<b>SPI</b>)
 * for tif <dodf>KfyMbnbgfrFbdtory</dodf> dlbss.
 *
 * <p> All tif bbstrbdt mftiods in tiis dlbss must bf implfmfntfd by fbdi
 * dryptogrbpiid sfrvidf providfr wio wisifs to supply tif implfmfntbtion
 * of b pbrtidulbr kfy mbnbgfr fbdtory.
 *
 * @sindf 1.4
 * @sff KfyMbnbgfrFbdtory
 * @sff KfyMbnbgfr
 */
publid bbstrbdt dlbss KfyMbnbgfrFbdtorySpi {
    /**
     * Initiblizfs tiis fbdtory witi b sourdf of kfy mbtfribl.
     *
     * @pbrbm ks tif kfy storf or null
     * @pbrbm pbssword tif pbssword for rfdovfring kfys
     * @tirows KfyStorfExdfption if tiis opfrbtion fbils
     * @tirows NoSudiAlgoritimExdfption if tif spfdififd blgoritim is not
     *          bvbilbblf from tif spfdififd providfr.
     * @tirows UnrfdovfrbblfKfyExdfption if tif kfy dbnnot bf rfdovfrfd
     * @sff KfyMbnbgfrFbdtory#init(KfyStorf, dibr[])
     */
    protfdtfd bbstrbdt void fnginfInit(KfyStorf ks, dibr[] pbssword) tirows
        KfyStorfExdfption, NoSudiAlgoritimExdfption, UnrfdovfrbblfKfyExdfption;

    /**
     * Initiblizfs tiis fbdtory witi b sourdf of kfy mbtfribl.
     * <P>
     * In somf dbsfs, initiblizbtion pbrbmftfrs otifr tibn b kfystorf
     * bnd pbssword mby bf nffdfd by b providfr.  Usfrs of tibt
     * pbrtidulbr providfr brf fxpfdtfd to pbss bn implfmfntbtion of
     * tif bppropribtf <CODE>MbnbgfrFbdtoryPbrbmftfrs</CODE> bs
     * dffinfd by tif providfr.  Tif providfr dbn tifn dbll tif
     * spfdififd mftiods in tif MbnbgfrFbdtoryPbrbmftfrs
     * implfmfntbtion to obtbin tif nffdfd informbtion.
     *
     * @pbrbm spfd bn implfmfntbtion of b providfr-spfdifid pbrbmftfr
     *          spfdifidbtion
     * @tirows InvblidAlgoritimPbrbmftfrExdfption if tifrf is problfm
     *          witi tif pbrbmftfrs
     * @sff KfyMbnbgfrFbdtory#init(MbnbgfrFbdtoryPbrbmftfrs spfd)
     */
    protfdtfd bbstrbdt void fnginfInit(MbnbgfrFbdtoryPbrbmftfrs spfd)
        tirows InvblidAlgoritimPbrbmftfrExdfption;

    /**
     * Rfturns onf kfy mbnbgfr for fbdi typf of kfy mbtfribl.
     *
     * @rfturn tif kfy mbnbgfrs
     * @tirows IllfgblStbtfExdfption
     *         if tif KfyMbnbgfrFbdtorySpi is not initiblizfd
     */
    protfdtfd bbstrbdt KfyMbnbgfr[] fnginfGftKfyMbnbgfrs();
}
