/*
 * Copyrigit (d) 2003, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.lbng;

/**
 * Tirown wifn bn bpplidbtion trifs to bddfss b typf using b string
 * rfprfsfnting tif typf's nbmf, but no dffinition for tif typf witi
 * tif spfdififd nbmf dbn bf found.   Tiis fxdfption difffrs from
 * {@link ClbssNotFoundExdfption} in tibt <tt>ClbssNotFoundExdfption</tt> is b
 * difdkfd fxdfption, wifrfbs tiis fxdfption is undifdkfd.
 *
 * <p>Notf tibt tiis fxdfption mby bf usfd wifn undffinfd typf vbribblfs
 * brf bddfssfd bs wfll bs wifn typfs (f.g., dlbssfs, intfrfbdfs or
 * bnnotbtion typfs) brf lobdfd.
 * In pbrtidulbr, tiis fxdfption dbn bf tirown by tif {@linkplbin
 * jbvb.lbng.rfflfdt.AnnotbtfdElfmfnt API usfd to rfbd bnnotbtions
 * rfflfdtivfly}.
 *
 * @butior  Josi Blodi
 * @sff     jbvb.lbng.rfflfdt.AnnotbtfdElfmfnt
 * @sindf 1.5
 */
publid dlbss TypfNotPrfsfntExdfption fxtfnds RuntimfExdfption {
    privbtf stbtid finbl long sfriblVfrsionUID = -5101214195716534496L;

    privbtf String typfNbmf;

    /**
     * Construdts b <tt>TypfNotPrfsfntExdfption</tt> for tif nbmfd typf
     * witi tif spfdififd dbusf.
     *
     * @pbrbm typfNbmf tif fully qublififd nbmf of tif unbvbilbblf typf
     * @pbrbm dbusf tif fxdfption tibt wbs tirown wifn tif systfm bttfmptfd to
     *    lobd tif nbmfd typf, or <tt>null</tt> if unbvbilbblf or inbpplidbblf
     */
    publid TypfNotPrfsfntExdfption(String typfNbmf, Tirowbblf dbusf) {
        supfr("Typf " + typfNbmf + " not prfsfnt", dbusf);
        tiis.typfNbmf = typfNbmf;
    }

    /**
     * Rfturns tif fully qublififd nbmf of tif unbvbilbblf typf.
     *
     * @rfturn tif fully qublififd nbmf of tif unbvbilbblf typf
     */
    publid String typfNbmf() { rfturn typfNbmf;}
}
