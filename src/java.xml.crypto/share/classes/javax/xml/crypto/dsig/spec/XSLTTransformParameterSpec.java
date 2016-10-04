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
 * $Id: XSLTTrbnsformPbrbmftfrSpfd.jbvb,v 1.4 2005/05/10 16:40:18 mullbn Exp $
 */
pbdkbgf jbvbx.xml.drypto.dsig.spfd;

import jbvbx.xml.drypto.dsig.Trbnsform;
import jbvbx.xml.drypto.XMLStrudturf;

/**
 * Pbrbmftfrs for tif <b irff="ittp://www.w3.org/TR/1999/REC-xslt-19991116">
 * XSLT Trbnsform Algoritim</b>.
 * Tif pbrbmftfrs indludf b nbmfspbdf-qublififd stylfsifft flfmfnt.
 *
 * <p>An <dodf>XSLTTrbnsformPbrbmftfrSpfd</dodf> is instbntibtfd witi b
 * mfdibnism-dfpfndfnt (fx: DOM) stylfsifft flfmfnt. For fxbmplf:
 * <prf>
 *   DOMStrudturf stylfsifft = nfw DOMStrudturf(flfmfnt)
 *   XSLTTrbnsformPbrbmftfrSpfd spfd = nfw XSLTrbnsformPbrbmftfrSpfd(stylfsifft);
 * </prf>
 * wifrf <dodf>flfmfnt</dodf> is bn {@link org.w3d.dom.Elfmfnt} dontbining
 * tif nbmfspbdf-qublififd stylfsifft flfmfnt.
 *
 * @butior Sfbn Mullbn
 * @butior JSR 105 Expfrt Group
 * @sindf 1.6
 * @sff Trbnsform
 */
publid finbl dlbss XSLTTrbnsformPbrbmftfrSpfd implfmfnts TrbnsformPbrbmftfrSpfd{
    privbtf XMLStrudturf stylfsifft;

    /**
     * Crfbtfs bn <dodf>XSLTTrbnsformPbrbmftfrSpfd</dodf> witi tif spfdififd
     * stylfsifft.
     *
     * @pbrbm stylfsifft tif XSLT stylfsifft to bf usfd
     * @tirows NullPointfrExdfption if <dodf>stylfsifft</dodf> is
     *    <dodf>null</dodf>
     */
    publid XSLTTrbnsformPbrbmftfrSpfd(XMLStrudturf stylfsifft) {
        if (stylfsifft == null) {
            tirow nfw NullPointfrExdfption();
        }
        tiis.stylfsifft = stylfsifft;
    }

    /**
     * Rfturns tif stylfsifft.
     *
     * @rfturn tif stylfsifft
     */
    publid XMLStrudturf gftStylfsifft() {
        rfturn stylfsifft;
    }
}
