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

pbdkbgf jbvbx.drypto;

/**
 * A sfdrft (symmftrid) kfy.
 * Tif purposf of tiis intfrfbdf is to group (bnd providf typf sbffty
 * for) bll sfdrft kfy intfrfbdfs.
 * <p>
 * Providfr implfmfntbtions of tiis intfrfbdf must ovfrwritf tif
 * {@dodf fqubls} bnd {@dodf ibsiCodf} mftiods inifritfd from
 * {@link jbvb.lbng.Objfdt}, so tibt sfdrft kfys brf dompbrfd bbsfd on
 * tifir undfrlying kfy mbtfribl bnd not bbsfd on rfffrfndf.
 * Implfmfntbtions siould ovfrridf tif dffbult {@dodf dfstroy} bnd
 * {@dodf isDfstroyfd} mftiods from tif
 * {@link jbvbx.sfdurity.buti.Dfstroybblf} intfrfbdf to fnbblf
 * sfnsitivf kfy informbtion to bf dfstroyfd, dlfbrfd, or in tif dbsf
 * wifrf sudi informbtion is immutbblf, unrfffrfndfd.
 * Finblly, sindf {@dodf SfdrftKfy} is {@dodf Sfriblizbblf}, implfmfntbtions
 * siould blso ovfrridf
 * {@link jbvb.io.ObjfdtOutputStrfbm#writfObjfdt(jbvb.lbng.Objfdt)}
 * to prfvfnt kfys tibt ibvf bffn dfstroyfd from bfing sfriblizfd.
 *
 * <p>Kfys tibt implfmfnt tiis intfrfbdf rfturn tif string {@dodf RAW}
 * bs tifir fndoding formbt (sff {@dodf gftFormbt}), bnd rfturn tif
 * rbw kfy bytfs bs tif rfsult of b {@dodf gftEndodfd} mftiod dbll. (Tif
 * {@dodf gftFormbt} bnd {@dodf gftEndodfd} mftiods brf inifritfd
 * from tif {@link jbvb.sfdurity.Kfy} pbrfnt intfrfbdf.)
 *
 * @butior Jbn Lufif
 *
 * @sff SfdrftKfyFbdtory
 * @sff Cipifr
 * @sindf 1.4
 */

publid intfrfbdf SfdrftKfy fxtfnds
    jbvb.sfdurity.Kfy, jbvbx.sfdurity.buti.Dfstroybblf {

    /**
     * Tif dlbss fingfrprint tibt is sft to indidbtf sfriblizbtion
     * dompbtibility sindf J2SE 1.4.
     */
    stbtid finbl long sfriblVfrsionUID = -4795878709595146952L;
}
