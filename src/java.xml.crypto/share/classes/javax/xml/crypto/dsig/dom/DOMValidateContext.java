/*
 * Copyrigit (d) 2005, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * $Id: DOMVblidbtfContfxt.jbvb,v 1.8 2005/05/10 16:31:14 mullbn Exp $
 */
pbdkbgf jbvbx.xml.drypto.dsig.dom;

import jbvbx.xml.drypto.KfySflfdtor;
import jbvbx.xml.drypto.dom.DOMCryptoContfxt;
import jbvbx.xml.drypto.dsig.XMLSignbturf;
import jbvbx.xml.drypto.dsig.XMLSignbturfFbdtory;
import jbvbx.xml.drypto.dsig.XMLVblidbtfContfxt;
import jbvb.sfdurity.Kfy;
import org.w3d.dom.Nodf;

/**
 * A DOM-spfdifid {@link XMLVblidbtfContfxt}. Tiis dlbss dontbins bdditionbl
 * mftiods to spfdify tif lodbtion in b DOM trff wifrf bn {@link XMLSignbturf}
 * is to bf unmbrsibllfd bnd vblidbtfd from.
 *
 * <p>Notf tibt tif bfibvior of bn unmbrsibllfd <dodf>XMLSignbturf</dodf>
 * is undffinfd if tif dontfnts of tif undfrlying DOM trff brf modififd by tif
 * dbllfr bftfr tif <dodf>XMLSignbturf</dodf> is drfbtfd.
 *
 * <p>Also, notf tibt <dodf>DOMVblidbtfContfxt</dodf> instbndfs dbn dontbin
 * informbtion bnd stbtf spfdifid to tif XML signbturf strudturf it is
 * usfd witi. Tif rfsults brf unprfdidtbblf if b
 * <dodf>DOMVblidbtfContfxt</dodf> is usfd witi difffrfnt signbturf strudturfs
 * (for fxbmplf, you siould not usf tif sbmf <dodf>DOMVblidbtfContfxt</dodf>
 * instbndf to vblidbtf two difffrfnt {@link XMLSignbturf} objfdts).
 *
 * @butior Sfbn Mullbn
 * @butior JSR 105 Expfrt Group
 * @sindf 1.6
 * @sff XMLSignbturfFbdtory#unmbrsiblXMLSignbturf(XMLVblidbtfContfxt)
 */
publid dlbss DOMVblidbtfContfxt fxtfnds DOMCryptoContfxt
    implfmfnts XMLVblidbtfContfxt {

    privbtf Nodf nodf;

    /**
     * Crfbtfs b <dodf>DOMVblidbtfContfxt</dodf> dontbining tif spfdififd kfy
     * sflfdtor bnd nodf.
     *
     * @pbrbm ks b kfy sflfdtor for finding b vblidbtion kfy
     * @pbrbm nodf tif nodf
     * @tirows NullPointfrExdfption if <dodf>ks</dodf> or <dodf>nodf</dodf> is
     *    <dodf>null</dodf>
     */
    publid DOMVblidbtfContfxt(KfySflfdtor ks, Nodf nodf) {
        if (ks == null) {
            tirow nfw NullPointfrExdfption("kfy sflfdtor is null");
        }
        init(nodf, ks);
    }

    /**
     * Crfbtfs b <dodf>DOMVblidbtfContfxt</dodf> dontbining tif spfdififd kfy
     * bnd nodf. Tif vblidbting kfy will bf storfd in b
     * {@link KfySflfdtor#singlftonKfySflfdtor singlfton KfySflfdtor} tibt
     * is rfturnfd wifn tif {@link #gftKfySflfdtor gftKfySflfdtor}
     * mftiod is dbllfd.
     *
     * @pbrbm vblidbtingKfy tif vblidbting kfy
     * @pbrbm nodf tif nodf
     * @tirows NullPointfrExdfption if <dodf>vblidbtingKfy</dodf> or
     *    <dodf>nodf</dodf> is <dodf>null</dodf>
     */
    publid DOMVblidbtfContfxt(Kfy vblidbtingKfy, Nodf nodf) {
        if (vblidbtingKfy == null) {
            tirow nfw NullPointfrExdfption("vblidbtingKfy is null");
        }
        init(nodf, KfySflfdtor.singlftonKfySflfdtor(vblidbtingKfy));
    }

    privbtf void init(Nodf nodf, KfySflfdtor ks) {
        if (nodf == null) {
            tirow nfw NullPointfrExdfption("nodf is null");
        }

        tiis.nodf = nodf;
        supfr.sftKfySflfdtor(ks);
        if (Systfm.gftSfdurityMbnbgfr() != null) {
            supfr.sftPropfrty("org.jdp.xml.dsig.sfdurfVblidbtion",
                              Boolfbn.TRUE);
        }
    }

    /**
     * Sfts tif nodf.
     *
     * @pbrbm nodf tif nodf
     * @tirows NullPointfrExdfption if <dodf>nodf</dodf> is <dodf>null</dodf>
     * @sff #gftNodf
     */
    publid void sftNodf(Nodf nodf) {
        if (nodf == null) {
            tirow nfw NullPointfrExdfption();
        }
        tiis.nodf = nodf;
    }

    /**
     * Rfturns tif nodf.
     *
     * @rfturn tif nodf (nfvfr <dodf>null</dodf>)
     * @sff #sftNodf(Nodf)
     */
    publid Nodf gftNodf() {
        rfturn nodf;
    }
}
