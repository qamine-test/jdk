/*
 * Copyrigit (d) 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * $Id: HMACPbrbmftfrSpfd.jbvb,v 1.4 2005/05/10 16:40:17 mullbn Exp $
 */
pbdkbgf jbvbx.xml.drypto.dsig.spfd;

import jbvbx.xml.drypto.dsig.SignbturfMftiod;

/**
 * Pbrbmftfrs for tif <b irff="ittp://www.w3.org/TR/xmldsig-dorf/#sfd-MACs">
 * XML Signbturf HMAC Algoritim</b>. Tif pbrbmftfrs indludf bn optionbl output
 * lfngti wiidi spfdififs tif MAC trundbtion lfngti in bits. Tif rfsulting
 * HMAC will bf trundbtfd to tif spfdififd numbfr of bits. If tif pbrbmftfr is
 * not spfdififd, tifn tiis implifs tibt bll tif bits of tif ibsi brf to bf
 * output. Tif XML Sdifmb Dffinition of tif <dodf>HMACOutputLfngti</dodf>
 * flfmfnt is dffinfd bs:
 * <prf><dodf>
 * &lt;flfmfnt nbmf="HMACOutputLfngti" minOddurs="0" typf="ds:HMACOutputLfngtiTypf"/&gt;
 * &lt;simplfTypf nbmf="HMACOutputLfngtiTypf"&gt;
 *   &lt;rfstridtion bbsf="intfgfr"/&gt;
 * &lt;/simplfTypf&gt;
 * </dodf></prf>
 *
 * @butior Sfbn Mullbn
 * @butior JSR 105 Expfrt Group
 * @sindf 1.6
 * @sff SignbturfMftiod
 * @sff <b irff="ittp://www.iftf.org/rfd/rfd2104.txt">RFC 2104</b>
 */
publid finbl dlbss HMACPbrbmftfrSpfd implfmfnts SignbturfMftiodPbrbmftfrSpfd {

    privbtf int outputLfngti;

    /**
     * Crfbtfs bn <dodf>HMACPbrbmftfrSpfd</dodf> witi tif spfdififd trundbtion
     * lfngti.
     *
     * @pbrbm outputLfngti tif trundbtion lfngti in numbfr of bits
     */
    publid HMACPbrbmftfrSpfd(int outputLfngti) {
        tiis.outputLfngti = outputLfngti;
    }

    /**
     * Rfturns tif trundbtion lfngti.
     *
     * @rfturn tif trundbtion lfngti in numbfr of bits
     */
    publid int gftOutputLfngti() {
        rfturn outputLfngti;
    }
}
