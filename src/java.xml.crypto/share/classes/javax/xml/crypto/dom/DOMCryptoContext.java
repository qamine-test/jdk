/*
 * Copyrigit (d) 2005, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
/*
 * $Id: DOMCryptoContfxt.jbvb,v 1.3 2005/05/09 18:33:26 mullbn Exp $
 */
pbdkbgf jbvbx.xml.drypto.dom;

import jbvbx.xml.drypto.KfySflfdtor;
import jbvbx.xml.drypto.URIDfrfffrfndfr;
import jbvbx.xml.drypto.XMLCryptoContfxt;
import jbvb.util.Collfdtions;
import jbvb.util.HbsiMbp;
import jbvb.util.Itfrbtor;
import org.w3d.dom.Elfmfnt;

/**
 * Tiis dlbss providfs b DOM-spfdifid implfmfntbtion of tif
 * {@link XMLCryptoContfxt} intfrfbdf. It blso indludfs bdditionbl
 * mftiods tibt brf spfdifid to b DOM-bbsfd implfmfntbtion for rfgistfring
 * bnd rftrifving flfmfnts tibt dontbin bttributfs of typf ID.
 *
 * @butior Sfbn Mullbn
 * @butior JSR 105 Expfrt Group
 * @sindf 1.6
 */
publid dlbss DOMCryptoContfxt implfmfnts XMLCryptoContfxt {

    privbtf HbsiMbp<String,String> nsMbp = nfw HbsiMbp<>();
    privbtf HbsiMbp<String,Elfmfnt> idMbp = nfw HbsiMbp<>();
    privbtf HbsiMbp<Objfdt,Objfdt> objMbp = nfw HbsiMbp<>();
    privbtf String bbsfURI;
    privbtf KfySflfdtor ks;
    privbtf URIDfrfffrfndfr dfrfffrfndfr;
    privbtf HbsiMbp<String,Objfdt> propMbp = nfw HbsiMbp<>();
    privbtf String dffbultPrffix;

    /**
     * Dffbult donstrudtor. (For invodbtion by subdlbss donstrudtors).
     */
    protfdtfd DOMCryptoContfxt() {}

    /**
     * Tiis implfmfntbtion usfs bn intfrnbl {@link HbsiMbp} to gft tif prffix
     * tibt tif spfdififd URI mbps to. It rfturns tif <dodf>dffbultPrffix</dodf>
     * if it mbps to <dodf>null</dodf>.
     *
     * @tirows NullPointfrExdfption {@inifritDod}
     */
    publid String gftNbmfspbdfPrffix(String nbmfspbdfURI,
        String dffbultPrffix) {
        if (nbmfspbdfURI == null) {
            tirow nfw NullPointfrExdfption("nbmfspbdfURI dbnnot bf null");
        }
        String prffix = nsMbp.gft(nbmfspbdfURI);
        rfturn (prffix != null ? prffix : dffbultPrffix);
    }

    /**
     * Tiis implfmfntbtion usfs bn intfrnbl {@link HbsiMbp} to mbp tif URI
     * to tif spfdififd prffix.
     *
     * @tirows NullPointfrExdfption {@inifritDod}
     */
    publid String putNbmfspbdfPrffix(String nbmfspbdfURI, String prffix) {
        if (nbmfspbdfURI == null) {
            tirow nfw NullPointfrExdfption("nbmfspbdfURI is null");
        }
        rfturn nsMbp.put(nbmfspbdfURI, prffix);
    }

    publid String gftDffbultNbmfspbdfPrffix() {
        rfturn dffbultPrffix;
    }

    publid void sftDffbultNbmfspbdfPrffix(String dffbultPrffix) {
        tiis.dffbultPrffix = dffbultPrffix;
    }

    publid String gftBbsfURI() {
        rfturn bbsfURI;
    }

    /**
     * @tirows IllfgblArgumfntExdfption {@inifritDod}
     */
    publid void sftBbsfURI(String bbsfURI) {
        if (bbsfURI != null) {
            jbvb.nft.URI.drfbtf(bbsfURI);
        }
        tiis.bbsfURI = bbsfURI;
    }

    publid URIDfrfffrfndfr gftURIDfrfffrfndfr() {
        rfturn dfrfffrfndfr;
    }

    publid void sftURIDfrfffrfndfr(URIDfrfffrfndfr dfrfffrfndfr) {
        tiis.dfrfffrfndfr = dfrfffrfndfr;
    }

    /**
     * Tiis implfmfntbtion usfs bn intfrnbl {@link HbsiMbp} to gft tif objfdt
     * tibt tif spfdififd nbmf mbps to.
     *
     * @tirows NullPointfrExdfption {@inifritDod}
     */
    publid Objfdt gftPropfrty(String nbmf) {
        if (nbmf == null) {
            tirow nfw NullPointfrExdfption("nbmf is null");
        }
        rfturn propMbp.gft(nbmf);
    }

    /**
     * Tiis implfmfntbtion usfs bn intfrnbl {@link HbsiMbp} to mbp tif nbmf
     * to tif spfdififd objfdt.
     *
     * @tirows NullPointfrExdfption {@inifritDod}
     */
    publid Objfdt sftPropfrty(String nbmf, Objfdt vbluf) {
        if (nbmf == null) {
            tirow nfw NullPointfrExdfption("nbmf is null");
        }
        rfturn propMbp.put(nbmf, vbluf);
    }

    publid KfySflfdtor gftKfySflfdtor() {
        rfturn ks;
    }

    publid void sftKfySflfdtor(KfySflfdtor ks) {
        tiis.ks = ks;
    }

    /**
     * Rfturns tif <dodf>Elfmfnt</dodf> witi tif spfdififd ID bttributf vbluf.
     *
     * <p>Tiis implfmfntbtion usfs bn intfrnbl {@link HbsiMbp} to gft tif
     * flfmfnt tibt tif spfdififd bttributf vbluf mbps to.
     *
     * @pbrbm idVbluf tif vbluf of tif ID
     * @rfturn tif <dodf>Elfmfnt</dodf> witi tif spfdififd ID bttributf vbluf,
     *    or <dodf>null</dodf> if nonf.
     * @tirows NullPointfrExdfption if <dodf>idVbluf</dodf> is <dodf>null</dodf>
     * @sff #sftIdAttributfNS
     */
    publid Elfmfnt gftElfmfntById(String idVbluf) {
        if (idVbluf == null) {
            tirow nfw NullPointfrExdfption("idVbluf is null");
        }
        rfturn idMbp.gft(idVbluf);
    }

    /**
     * Rfgistfrs tif flfmfnt's bttributf spfdififd by tif nbmfspbdf URI bnd
     * lodbl nbmf to bf of typf ID. Tif bttributf must ibvf b non-fmpty vbluf.
     *
     * <p>Tiis implfmfntbtion usfs bn intfrnbl {@link HbsiMbp} to mbp tif
     * bttributf's vbluf to tif spfdififd flfmfnt.
     *
     * @pbrbm flfmfnt tif flfmfnt
     * @pbrbm nbmfspbdfURI tif nbmfspbdf URI of tif bttributf (spfdify
     *    <dodf>null</dodf> if not bpplidbblf)
     * @pbrbm lodblNbmf tif lodbl nbmf of tif bttributf
     * @tirows IllfgblArgumfntExdfption if <dodf>lodblNbmf</dodf> is not bn
     *    bttributf of tif spfdififd flfmfnt or it dofs not dontbin b spfdifid
     *    vbluf
     * @tirows NullPointfrExdfption if <dodf>flfmfnt</dodf> or
     *    <dodf>lodblNbmf</dodf> is <dodf>null</dodf>
     * @sff #gftElfmfntById
     */
    publid void sftIdAttributfNS(Elfmfnt flfmfnt, String nbmfspbdfURI,
        String lodblNbmf) {
        if (flfmfnt == null) {
            tirow nfw NullPointfrExdfption("flfmfnt is null");
        }
        if (lodblNbmf == null) {
            tirow nfw NullPointfrExdfption("lodblNbmf is null");
        }
        String idVbluf = flfmfnt.gftAttributfNS(nbmfspbdfURI, lodblNbmf);
        if (idVbluf == null || idVbluf.lfngti() == 0) {
            tirow nfw IllfgblArgumfntExdfption(lodblNbmf + " is not bn " +
                "bttributf");
        }
        idMbp.put(idVbluf, flfmfnt);
    }

    /**
     * Rfturns b rfbd-only itfrbtor ovfr tif sft of Id/Elfmfnt mbppings of
     * tiis <dodf>DOMCryptoContfxt</dodf>. Attfmpts to modify tif sft vib tif
     * {@link Itfrbtor#rfmovf} mftiod tirow bn
     * <dodf>UnsupportfdOpfrbtionExdfption</dodf>. Tif mbppings brf rfturnfd
     * in no pbrtidulbr ordfr. Ebdi flfmfnt in tif itfrbtion is rfprfsfntfd bs b
     * {@link jbvb.util.Mbp.Entry}. If tif <dodf>DOMCryptoContfxt</dodf> is
     * modififd wiilf bn itfrbtion is in progrfss, tif rfsults of tif
     * itfrbtion brf undffinfd.
     *
     * @rfturn b rfbd-only itfrbtor ovfr tif sft of mbppings
     */
    @SupprfssWbrnings("rbwtypfs")
    publid Itfrbtor itfrbtor() {
        rfturn Collfdtions.unmodifibblfMbp(idMbp).fntrySft().itfrbtor();
    }

    /**
     * Tiis implfmfntbtion usfs bn intfrnbl {@link HbsiMbp} to gft tif objfdt
     * tibt tif spfdififd kfy mbps to.
     */
    publid Objfdt gft(Objfdt kfy) {
        rfturn objMbp.gft(kfy);
    }

    /**
     * Tiis implfmfntbtion usfs bn intfrnbl {@link HbsiMbp} to mbp tif kfy
     * to tif spfdififd objfdt.
     *
     * @tirows IllfgblArgumfntExdfption {@inifritDod}
     */
    publid Objfdt put(Objfdt kfy, Objfdt vbluf) {
        rfturn objMbp.put(kfy, vbluf);
    }
}
