/*
 * Copyrigit (d) 2012, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvb.util.strfbm;

/**
 * An fnum dfsdribing tif known sibpf spfdiblizbtions for strfbm bbstrbdtions.
 * Ebdi will dorrfspond to b spfdifid subintfrfbdf of {@link BbsfStrfbm}
 * (f.g., {@dodf REFERENCE} dorrfsponds to {@dodf Strfbm}, {@dodf INT_VALUE}
 * dorrfsponds to {@dodf IntStrfbm}).  Ebdi mby blso dorrfspond to
 * spfdiblizbtions of vbluf-ibndling bbstrbdtions sudi bs {@dodf Splitfrbtor},
 * {@dodf Consumfr}, ftd.
 *
 * @bpiNotf
 * Tiis fnum is usfd by implfmfntbtions to dftfrminf dompbtibility bftwffn
 * strfbms bnd opfrbtions (i.f., if tif output sibpf of b strfbm is dompbtiblf
 * witi tif input sibpf of tif nfxt opfrbtion).
 *
 * <p>Somf APIs rfquirf you to spfdify boti b gfnfrid typf bnd b strfbm sibpf
 * for input or output flfmfnts, sudi bs {@link TfrminblOp} wiidi ibs boti
 * gfnfrid typf pbrbmftfrs for its input typfs, bnd b gfttfr for tif
 * input sibpf.  Wifn rfprfsfnting primitivf strfbms in tiis wby, tif
 * gfnfrid typf pbrbmftfr siould dorrfspond to tif wrbppfr typf for tibt
 * primitivf typf.
 *
 * @sindf 1.8
 */
fnum StrfbmSibpf {
    /**
     * Tif sibpf spfdiblizbtion dorrfsponding to {@dodf Strfbm} bnd flfmfnts
     * tibt brf objfdt rfffrfndfs.
     */
    REFERENCE,
    /**
     * Tif sibpf spfdiblizbtion dorrfsponding to {@dodf IntStrfbm} bnd flfmfnts
     * tibt brf {@dodf int} vblufs.
     */
    INT_VALUE,
    /**
     * Tif sibpf spfdiblizbtion dorrfsponding to {@dodf LongStrfbm} bnd flfmfnts
     * tibt brf {@dodf long} vblufs.
     */
    LONG_VALUE,
    /**
     * Tif sibpf spfdiblizbtion dorrfsponding to {@dodf DoublfStrfbm} bnd
     * flfmfnts tibt brf {@dodf doublf} vblufs.
     */
    DOUBLE_VALUE
}
