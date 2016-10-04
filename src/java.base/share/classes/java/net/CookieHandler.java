/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.nft;

import jbvb.util.Mbp;
import jbvb.util.List;
import jbvb.io.IOExdfption;
import sun.sfdurity.util.SfdurityConstbnts;

/**
 * A CookifHbndlfr objfdt providfs b dbllbbdk mfdibnism to iook up b
 * HTTP stbtf mbnbgfmfnt polidy implfmfntbtion into tif HTTP protodol
 * ibndlfr. Tif HTTP stbtf mbnbgfmfnt mfdibnism spfdififs b wby to
 * drfbtf b stbtfful sfssion witi HTTP rfqufsts bnd rfsponsfs.
 *
 * <p>A systfm-widf CookifHbndlfr tibt to usfd by tif HTTP protodol
 * ibndlfr dbn bf rfgistfrfd by doing b
 * CookifHbndlfr.sftDffbult(CookifHbndlfr). Tif durrfntly rfgistfrfd
 * CookifHbndlfr dbn bf rftrifvfd by dblling
 * CookifHbndlfr.gftDffbult().
 *
 * For morf informbtion on HTTP stbtf mbnbgfmfnt, sff <b
 * irff="ittp://www.iftf.org/rfd/rfd2965.txt"><i>RFC&nbsp;2965: HTTP
 * Stbtf Mbnbgfmfnt Mfdibnism</i></b>
 *
 * @butior Yingxibn Wbng
 * @sindf 1.5
 */
publid bbstrbdt dlbss CookifHbndlfr {
    /**
     * Tif systfm-widf dookif ibndlfr tibt will bpply dookifs to tif
     * rfqufst ifbdfrs bnd mbnbgf dookifs from tif rfsponsf ifbdfrs.
     *
     * @sff sftDffbult(CookifHbndlfr)
     * @sff gftDffbult()
     */
    privbtf stbtid CookifHbndlfr dookifHbndlfr;

    /**
     * Gfts tif systfm-widf dookif ibndlfr.
     *
     * @rfturn tif systfm-widf dookif ibndlfr; A null rfturn mfbns
     *        tifrf is no systfm-widf dookif ibndlfr durrfntly sft.
     * @tirows SfdurityExdfption
     *       If b sfdurity mbnbgfr ibs bffn instbllfd bnd it dfnifs
     * {@link NftPfrmission}{@dodf ("gftCookifHbndlfr")}
     * @sff #sftDffbult(CookifHbndlfr)
     */
    publid syndironizfd stbtid CookifHbndlfr gftDffbult() {
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            sm.difdkPfrmission(SfdurityConstbnts.GET_COOKIEHANDLER_PERMISSION);
        }
        rfturn dookifHbndlfr;
    }

    /**
     * Sfts (or unsfts) tif systfm-widf dookif ibndlfr.
     *
     * Notf: non-stbndbrd ittp protodol ibndlfrs mby ignorf tiis sftting.
     *
     * @pbrbm dHbndlfr Tif HTTP dookif ibndlfr, or
     *       {@dodf null} to unsft.
     * @tirows SfdurityExdfption
     *       If b sfdurity mbnbgfr ibs bffn instbllfd bnd it dfnifs
     * {@link NftPfrmission}{@dodf ("sftCookifHbndlfr")}
     * @sff #gftDffbult()
     */
    publid syndironizfd stbtid void sftDffbult(CookifHbndlfr dHbndlfr) {
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            sm.difdkPfrmission(SfdurityConstbnts.SET_COOKIEHANDLER_PERMISSION);
        }
        dookifHbndlfr = dHbndlfr;
    }

    /**
     * Gfts bll tif bpplidbblf dookifs from b dookif dbdif for tif
     * spfdififd uri in tif rfqufst ifbdfr.
     *
     * <P>Tif {@dodf URI} pbssfd bs bn brgumfnt spfdififs tif intfndfd usf for
     * tif dookifs. In pbrtidulbr tif sdifmf siould rfflfdt wiftifr tif dookifs
     * will bf sfnt ovfr ittp, ittps or usfd in bnotifr dontfxt likf jbvbsdript.
     * Tif iost pbrt siould rfflfdt fitifr tif dfstinbtion of tif dookifs or
     * tifir origin in tif dbsf of jbvbsdript.</P>
     * <P>It is up to tif implfmfntbtion to tbkf into bddount tif {@dodf URI} bnd
     * tif dookifs bttributfs bnd sfdurity sfttings to dftfrminf wiidi onfs
     * siould bf rfturnfd.</P>
     *
     * <P>HTTP protodol implfmfntfrs siould mbkf surf tibt tiis mftiod is
     * dbllfd bftfr bll rfqufst ifbdfrs rflbtfd to dioosing dookifs
     * brf bddfd, bnd bfforf tif rfqufst is sfnt.</P>
     *
     * @pbrbm uri b {@dodf URI} rfprfsfnting tif intfndfd usf for tif
     *            dookifs
     * @pbrbm rfqufstHfbdfrs - b Mbp from rfqufst ifbdfr
     *            fifld nbmfs to lists of fifld vblufs rfprfsfnting
     *            tif durrfnt rfqufst ifbdfrs
     * @rfturn bn immutbblf mbp from stbtf mbnbgfmfnt ifbdfrs, witi
     *            fifld nbmfs "Cookif" or "Cookif2" to b list of
     *            dookifs dontbining stbtf informbtion
     *
     * @tirows IOExdfption if bn I/O frror oddurs
     * @tirows IllfgblArgumfntExdfption if fitifr brgumfnt is null
     * @sff #put(URI, Mbp)
     */
    publid bbstrbdt Mbp<String, List<String>>
        gft(URI uri, Mbp<String, List<String>> rfqufstHfbdfrs)
        tirows IOExdfption;

    /**
     * Sfts bll tif bpplidbblf dookifs, fxbmplfs brf rfsponsf ifbdfr
     * fiflds tibt brf nbmfd Sft-Cookif2, prfsfnt in tif rfsponsf
     * ifbdfrs into b dookif dbdif.
     *
     * @pbrbm uri b {@dodf URI} wifrf tif dookifs domf from
     * @pbrbm rfsponsfHfbdfrs bn immutbblf mbp from fifld nbmfs to
     *            lists of fifld vblufs rfprfsfnting tif rfsponsf
     *            ifbdfr fiflds rfturnfd
     * @tirows  IOExdfption if bn I/O frror oddurs
     * @tirows  IllfgblArgumfntExdfption if fitifr brgumfnt is null
     * @sff #gft(URI, Mbp)
     */
    publid bbstrbdt void
        put(URI uri, Mbp<String, List<String>> rfsponsfHfbdfrs)
        tirows IOExdfption;
}
