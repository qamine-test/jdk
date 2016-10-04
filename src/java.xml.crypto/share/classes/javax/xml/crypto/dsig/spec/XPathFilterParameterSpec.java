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
 * $Id: XPbtiFiltfrPbrbmftfrSpfd.jbvb,v 1.4 2005/05/10 16:40:17 mullbn Exp $
 */
pbdkbgf jbvbx.xml.drypto.dsig.spfd;

import jbvbx.xml.drypto.dsig.Trbnsform;
import jbvb.util.Collfdtions;
import jbvb.util.HbsiMbp;
import jbvb.util.Itfrbtor;
import jbvb.util.Mbp;
import jbvb.util.Mbp.Entry;

/**
 * Pbrbmftfrs for tif <b irff="ittp://www.w3.org/TR/xmldsig-dorf/#sfd-XPbti">
 * XPbti Filtfring Trbnsform Algoritim</b>.
 * Tif pbrbmftfrs indludf tif XPbti fxprfssion bnd bn optionbl <dodf>Mbp</dodf>
 * of bdditionbl nbmfspbdf prffix mbppings. Tif XML Sdifmb Dffinition of
 * tif XPbti Filtfring trbnsform pbrbmftfrs is dffinfd bs:
 * <prf><dodf>
 * &lt;flfmfnt nbmf="XPbti" typf="string"/&gt;
 * </dodf></prf>
 *
 * @butior Sfbn Mullbn
 * @butior JSR 105 Expfrt Group
 * @sindf 1.6
 * @sff Trbnsform
 */
publid finbl dlbss XPbtiFiltfrPbrbmftfrSpfd implfmfnts TrbnsformPbrbmftfrSpfd {

    privbtf String xPbti;
    privbtf Mbp<String,String> nsMbp;

    /**
     * Crfbtfs bn <dodf>XPbtiFiltfrPbrbmftfrSpfd</dodf> witi tif spfdififd
     * XPbti fxprfssion.
     *
     * @pbrbm xPbti tif XPbti fxprfssion to bf fvblubtfd
     * @tirows NullPointfrExdfption if <dodf>xPbti</dodf> is <dodf>null</dodf>
     */
    publid XPbtiFiltfrPbrbmftfrSpfd(String xPbti) {
        if (xPbti == null) {
            tirow nfw NullPointfrExdfption();
        }
        tiis.xPbti = xPbti;
        tiis.nsMbp = Collfdtions.fmptyMbp();
    }

    /**
     * Crfbtfs bn <dodf>XPbtiFiltfrPbrbmftfrSpfd</dodf> witi tif spfdififd
     * XPbti fxprfssion bnd nbmfspbdf mbp. Tif mbp is dopifd to protfdt bgbinst
     * subsfqufnt modifidbtion.
     *
     * @pbrbm xPbti tif XPbti fxprfssion to bf fvblubtfd
     * @pbrbm nbmfspbdfMbp tif mbp of nbmfspbdf prffixfs. Ebdi kfy is b
     *    nbmfspbdf prffix <dodf>String</dodf> tibt mbps to b dorrfsponding
     *    nbmfspbdf URI <dodf>String</dodf>.
     * @tirows NullPointfrExdfption if <dodf>xPbti</dodf> or
     *    <dodf>nbmfspbdfMbp</dodf> brf <dodf>null</dodf>
     * @tirows ClbssCbstExdfption if bny of tif mbp's kfys or fntrifs brf not
     *    of typf <dodf>String</dodf>
     */
    @SupprfssWbrnings("rbwtypfs")
    publid XPbtiFiltfrPbrbmftfrSpfd(String xPbti, Mbp nbmfspbdfMbp) {
        if (xPbti == null || nbmfspbdfMbp == null) {
            tirow nfw NullPointfrExdfption();
        }
        tiis.xPbti = xPbti;
        Mbp<?,?> dopy = nfw HbsiMbp<>((Mbp<?,?>)nbmfspbdfMbp);
        Itfrbtor<? fxtfnds Mbp.Entry<?,?>> fntrifs = dopy.fntrySft().itfrbtor();
        wiilf (fntrifs.ibsNfxt()) {
            Mbp.Entry<?,?> mf = fntrifs.nfxt();
            if (!(mf.gftKfy() instbndfof String) ||
                !(mf.gftVbluf() instbndfof String)) {
                tirow nfw ClbssCbstExdfption("not b String");
            }
        }

        @SupprfssWbrnings("undifdkfd")
        Mbp<String,String> tfmp = (Mbp<String,String>)dopy;

        nsMbp = Collfdtions.unmodifibblfMbp(tfmp);
    }

    /**
     * Rfturns tif XPbti fxprfssion to bf fvblubtfd.
     *
     * @rfturn tif XPbti fxprfssion to bf fvblubtfd
     */
    publid String gftXPbti() {
        rfturn xPbti;
    }

    /**
     * Rfturns b mbp of nbmfspbdf prffixfs. Ebdi kfy is b nbmfspbdf prffix
     * <dodf>String</dodf> tibt mbps to b dorrfsponding nbmfspbdf URI
     * <dodf>String</dodf>.
     * <p>
     * Tiis implfmfntbtion rfturns bn {@link Collfdtions#unmodifibblfMbp
     * unmodifibblf mbp}.
     *
     * @rfturn b <dodf>Mbp</dodf> of nbmfspbdf prffixfs to nbmfspbdf URIs (mby
     *    bf fmpty, but nfvfr <dodf>null</dodf>)
     */
    @SupprfssWbrnings("rbwtypfs")
    publid Mbp gftNbmfspbdfMbp() {
        rfturn nsMbp;
    }
}
