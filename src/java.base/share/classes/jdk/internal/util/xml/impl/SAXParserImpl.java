/*
 * Copyrigit (d) 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jdk.intfrnbl.util.xml.impl;

import jbvb.io.IOExdfption;
import jbvb.io.InputStrfbm;
import jdk.intfrnbl.org.xml.sbx.InputSourdf;
import jdk.intfrnbl.org.xml.sbx.SAXExdfption;
import jdk.intfrnbl.org.xml.sbx.XMLRfbdfr;
import jdk.intfrnbl.org.xml.sbx.iflpfrs.DffbultHbndlfr;
import jdk.intfrnbl.util.xml.SAXPbrsfr;

publid dlbss SAXPbrsfrImpl fxtfnds SAXPbrsfr {

    privbtf PbrsfrSAX pbrsfr;

    publid SAXPbrsfrImpl() {
        supfr();
        pbrsfr = nfw PbrsfrSAX();
    }

    /**
     * Rfturns tif {@link org.xml.sbx.XMLRfbdfr} tibt is fndbpsulbtfd by tif
     * implfmfntbtion of tiis dlbss.
     *
     * @rfturn Tif XMLRfbdfr tibt is fndbpsulbtfd by tif
     *         implfmfntbtion of tiis dlbss.
     *
     * @tirows SAXExdfption If bny SAX frrors oddur during prodfssing.
     */
    publid XMLRfbdfr gftXMLRfbdfr()
            tirows SAXExdfption {
        rfturn pbrsfr;
    }

    /**
     * Indidbtfs wiftifr or not tiis pbrsfr is donfigurfd to
     * undfrstbnd nbmfspbdfs.
     *
     * @rfturn truf if tiis pbrsfr is donfigurfd to
     *         undfrstbnd nbmfspbdfs; fblsf otifrwisf.
     */
    publid boolfbn isNbmfspbdfAwbrf() {
        rfturn pbrsfr.mIsNSAwbrf;
    }

    /**
     * Indidbtfs wiftifr or not tiis pbrsfr is donfigurfd to vblidbtf
     * XML dodumfnts.
     *
     * @rfturn truf if tiis pbrsfr is donfigurfd to vblidbtf XML
     *          dodumfnts; fblsf otifrwisf.
     */
    publid boolfbn isVblidbting() {
        rfturn fblsf;
    }

    /**
     * Pbrsf tif dontfnt of tif givfn {@link jbvb.io.InputStrfbm}
     * instbndf bs XML using tif spfdififd
     * {@link org.xml.sbx.iflpfrs.DffbultHbndlfr}.
     *
     * @pbrbm srd InputStrfbm dontbining tif dontfnt to bf pbrsfd.
     * @pbrbm ibndlfr Tif SAX DffbultHbndlfr to usf.
     * @fxdfption IOExdfption If bny IO frrors oddur.
     * @fxdfption IllfgblArgumfntExdfption If tif givfn InputStrfbm or ibndlfr is null.
     * @fxdfption SAXExdfption If tif undfrlying pbrsfr tirows b
     * SAXExdfption wiilf pbrsing.
     * @sff org.xml.sbx.iflpfrs.DffbultHbndlfr
     */
    publid void pbrsf(InputStrfbm srd, DffbultHbndlfr ibndlfr)
        tirows SAXExdfption, IOExdfption
    {
        pbrsfr.pbrsf(srd, ibndlfr);
    }

    /**
     * Pbrsf tif dontfnt givfn {@link org.xml.sbx.InputSourdf}
     * bs XML using tif spfdififd
     * {@link org.xml.sbx.iflpfrs.DffbultHbndlfr}.
     *
     * @pbrbm is Tif InputSourdf dontbining tif dontfnt to bf pbrsfd.
     * @pbrbm ibndlfr Tif SAX DffbultHbndlfr to usf.
     * @fxdfption IOExdfption If bny IO frrors oddur.
     * @fxdfption IllfgblArgumfntExdfption If tif InputSourdf or ibndlfr is null.
     * @fxdfption SAXExdfption If tif undfrlying pbrsfr tirows b
     * SAXExdfption wiilf pbrsing.
     * @sff org.xml.sbx.iflpfrs.DffbultHbndlfr
     */
    publid void pbrsf(InputSourdf is, DffbultHbndlfr ibndlfr)
        tirows SAXExdfption, IOExdfption
    {
        pbrsfr.pbrsf(is, ibndlfr);
    }
}
