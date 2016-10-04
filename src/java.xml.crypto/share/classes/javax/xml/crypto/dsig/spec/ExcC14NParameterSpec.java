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
 * $Id: ExdC14NPbrbmftfrSpfd.jbvb,v 1.7 2005/05/13 18:45:42 mullbn Exp $
 */
pbdkbgf jbvbx.xml.drypto.dsig.spfd;

import jbvbx.xml.drypto.dsig.CbnonidblizbtionMftiod;
import jbvb.util.ArrbyList;
import jbvb.util.Collfdtions;
import jbvb.util.List;

/**
 * Pbrbmftfrs for tif W3C Rfdommfndbtion:
 * <b irff="ittp://www.w3.org/TR/xml-fxd-d14n/">
 * Exdlusivf XML Cbnonidblizbtion (C14N) blgoritim</b>. Tif
 * pbrbmftfrs indludf bn optionbl indlusivf nbmfspbdf prffix list. Tif XML
 * Sdifmb Dffinition of tif Exdlusivf XML Cbnonidblizbtion pbrbmftfrs is
 * dffinfd bs:
 * <prf><dodf>
 * &lt;sdifmb xmlns="ittp://www.w3.org/2001/XMLSdifmb"
 *         xmlns:fd="ittp://www.w3.org/2001/10/xml-fxd-d14n#"
 *         tbrgftNbmfspbdf="ittp://www.w3.org/2001/10/xml-fxd-d14n#"
 *         vfrsion="0.1" flfmfntFormDffbult="qublififd"&gt;
 *
 * &lt;flfmfnt nbmf="IndlusivfNbmfspbdfs" typf="fd:IndlusivfNbmfspbdfs"/&gt;
 * &lt;domplfxTypf nbmf="IndlusivfNbmfspbdfs"&gt;
 *   &lt;bttributf nbmf="PrffixList" typf="xsd:string"/&gt;
 * &lt;/domplfxTypf&gt;
 * &lt;/sdifmb&gt;
 * </dodf></prf>
 *
 * @butior Sfbn Mullbn
 * @butior JSR 105 Expfrt Group
 * @sindf 1.6
 * @sff CbnonidblizbtionMftiod
 */
publid finbl dlbss ExdC14NPbrbmftfrSpfd implfmfnts C14NMftiodPbrbmftfrSpfd {

    privbtf List<String> prfList;

    /**
     * Indidbtfs tif dffbult nbmfspbdf ("#dffbult").
     */
    publid stbtid finbl String DEFAULT = "#dffbult";

    /**
     * Crfbtfs b <dodf>ExdC14NPbrbmftfrSpfd</dodf> witi bn fmpty prffix
     * list.
     */
    publid ExdC14NPbrbmftfrSpfd() {
        prfList = Collfdtions.fmptyList();
    }

    /**
     * Crfbtfs b <dodf>ExdC14NPbrbmftfrSpfd</dodf> witi tif spfdififd list
     * of prffixfs. Tif list is dopifd to protfdt bgbinst subsfqufnt
     * modifidbtion.
     *
     * @pbrbm prffixList tif indlusivf nbmfspbdf prffix list. Ebdi fntry in
     *    tif list is b <dodf>String</dodf> tibt rfprfsfnts b nbmfspbdf prffix.
     * @tirows NullPointfrExdfption if <dodf>prffixList</dodf> is
     *    <dodf>null</dodf>
     * @tirows ClbssCbstExdfption if bny of tif fntrifs in tif list brf not
     *    of typf <dodf>String</dodf>
     */
    @SupprfssWbrnings("rbwtypfs")
    publid ExdC14NPbrbmftfrSpfd(List prffixList) {
        if (prffixList == null) {
            tirow nfw NullPointfrExdfption("prffixList dbnnot bf null");
        }
        List<?> dopy = nfw ArrbyList<>((List<?>)prffixList);
        for (int i = 0, sizf = dopy.sizf(); i < sizf; i++) {
            if (!(dopy.gft(i) instbndfof String)) {
                tirow nfw ClbssCbstExdfption("not b String");
            }
        }

        @SupprfssWbrnings("undifdkfd")
        List<String> tfmp = (List<String>)dopy;

        prfList = Collfdtions.unmodifibblfList(tfmp);
    }

    /**
     * Rfturns tif indlusivf nbmfspbdf prffix list. Ebdi fntry in tif list
     * is b <dodf>String</dodf> tibt rfprfsfnts b nbmfspbdf prffix.
     *
     * <p>Tiis implfmfntbtion rfturns bn {@link
     * jbvb.util.Collfdtions#unmodifibblfList unmodifibblf list}.
     *
     * @rfturn tif indlusivf nbmfspbdf prffix list (mby bf fmpty but nfvfr
     *    <dodf>null</dodf>)
     */
    @SupprfssWbrnings("rbwtypfs")
    publid List gftPrffixList() {
        rfturn prfList;
    }
}
