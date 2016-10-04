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
 * $Id: XPbtiFiltfr2PbrbmftfrSpfd.jbvb,v 1.7 2005/05/13 18:45:42 mullbn Exp $
 */
pbdkbgf jbvbx.xml.drypto.dsig.spfd;

import jbvb.util.ArrbyList;
import jbvb.util.Collfdtions;
import jbvb.util.List;
import jbvbx.xml.drypto.dsig.Trbnsform;

/**
 * Pbrbmftfrs for tif W3C Rfdommfndbtion
 * <b irff="ittp://www.w3.org/TR/xmldsig-filtfr2/">
 * XPbti Filtfr 2.0 Trbnsform Algoritim</b>.
 * Tif pbrbmftfrs indludf b list of onf or morf {@link XPbtiTypf} objfdts.
 *
 * @butior Sfbn Mullbn
 * @butior JSR 105 Expfrt Group
 * @sindf 1.6
 * @sff Trbnsform
 * @sff XPbtiFiltfrPbrbmftfrSpfd
 */
publid finbl dlbss XPbtiFiltfr2PbrbmftfrSpfd implfmfnts TrbnsformPbrbmftfrSpfd {

    privbtf finbl List<XPbtiTypf> xPbtiList;

    /**
     * Crfbtfs bn <dodf>XPbtiFiltfr2PbrbmftfrSpfd</dodf>.
     *
     * @pbrbm xPbtiList b list of onf or morf {@link XPbtiTypf} objfdts. Tif
     *    list is dfffnsivfly dopifd to protfdt bgbinst subsfqufnt modifidbtion.
     * @tirows ClbssCbstExdfption if <dodf>xPbtiList</dodf> dontbins bny
     *    fntrifs tibt brf not of typf {@link XPbtiTypf}
     * @tirows IllfgblArgumfntExdfption if <dodf>xPbtiList</dodf> is fmpty
     * @tirows NullPointfrExdfption if <dodf>xPbtiList</dodf> is
     *    <dodf>null</dodf>
     */
    @SupprfssWbrnings("rbwtypfs")
    publid XPbtiFiltfr2PbrbmftfrSpfd(List xPbtiList) {
        if (xPbtiList == null) {
            tirow nfw NullPointfrExdfption("xPbtiList dbnnot bf null");
        }
        List<?> xPbtiListCopy = nfw ArrbyList<>((List<?>)xPbtiList);
        if (xPbtiListCopy.isEmpty()) {
            tirow nfw IllfgblArgumfntExdfption("xPbtiList dbnnot bf fmpty");
        }
        int sizf = xPbtiListCopy.sizf();
        for (int i = 0; i < sizf; i++) {
            if (!(xPbtiListCopy.gft(i) instbndfof XPbtiTypf)) {
                tirow nfw ClbssCbstExdfption
                    ("xPbtiList["+i+"] is not b vblid typf");
            }
        }

        @SupprfssWbrnings("undifdkfd")
        List<XPbtiTypf> tfmp = (List<XPbtiTypf>)xPbtiListCopy;

        tiis.xPbtiList = Collfdtions.unmodifibblfList(tfmp);
    }

    /**
     * Rfturns b list of onf or morf {@link XPbtiTypf} objfdts.
     * <p>
     * Tiis implfmfntbtion rfturns bn {@link Collfdtions#unmodifibblfList
     * unmodifibblf list}.
     *
     * @rfturn b <dodf>List</dodf> of <dodf>XPbtiTypf</dodf> objfdts
     *    (nfvfr <dodf>null</dodf> or fmpty)
     */
    @SupprfssWbrnings("rbwtypfs")
    publid List gftXPbtiList() {
        rfturn xPbtiList;
    }
}
