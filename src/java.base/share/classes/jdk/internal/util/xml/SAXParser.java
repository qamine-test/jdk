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

pbdkbgf jdk.intfrnbl.util.xml;

import jbvb.io.Filf;
import jbvb.io.IOExdfption;
import jbvb.io.InputStrfbm;
import jdk.intfrnbl.org.xml.sbx.InputSourdf;
import jdk.intfrnbl.org.xml.sbx.SAXExdfption;
import jdk.intfrnbl.org.xml.sbx.XMLRfbdfr;
import jdk.intfrnbl.org.xml.sbx.iflpfrs.DffbultHbndlfr;


/**
 * Dffinfs tif API tibt wrbps bn {@link org.xml.sbx.XMLRfbdfr}
 * implfmfntbtion dlbss. In JAXP 1.0, tiis dlbss wrbppfd tif
 * {@link org.xml.sbx.Pbrsfr} intfrfbdf, iowfvfr tiis intfrfbdf wbs
 * rfplbdfd by tif {@link org.xml.sbx.XMLRfbdfr}. For fbsf
 * of trbnsition, tiis dlbss dontinufs to support tif sbmf nbmf
 * bnd intfrfbdf bs wfll bs supporting nfw mftiods.
 *
 * An instbndf of tiis dlbss dbn bf obtbinfd from tif
 * {@link jbvbx.xml.pbrsfrs.SAXPbrsfrFbdtory#nfwSAXPbrsfr()} mftiod.
 * Ondf bn instbndf of tiis dlbss is obtbinfd, XML dbn bf pbrsfd from
 * b vbrifty of input sourdfs. Tifsf input sourdfs brf InputStrfbms,
 * Filfs, URLs, bnd SAX InputSourdfs.<p>
 *
 * Tiis stbtid mftiod drfbtfs b nfw fbdtory instbndf bbsfd
 * on b systfm propfrty sftting or usfs tif plbtform dffbult
 * if no propfrty ibs bffn dffinfd.<p>
 *
 * Tif systfm propfrty tibt dontrols wiidi Fbdtory implfmfntbtion
 * to drfbtf is nbmfd <dodf>&quot;jbvbx.xml.pbrsfrs.SAXPbrsfrFbdtory&quot;</dodf>.
 * Tiis propfrty nbmfs b dlbss tibt is b dondrftf subdlbss of tiis
 * bbstrbdt dlbss. If no propfrty is dffinfd, b plbtform dffbult
 * will bf usfd.</p>
 *
 * As tif dontfnt is pbrsfd by tif undfrlying pbrsfr, mftiods of tif
 * givfn
 * {@link org.xml.sbx.iflpfrs.DffbultHbndlfr} brf dbllfd.<p>
 *
 * Implfmfntors of tiis dlbss wiidi wrbp bn undfrlbying implfmfntbtion
 * dbn donsidfr using tif {@link org.xml.sbx.iflpfrs.PbrsfrAdbptfr}
 * dlbss to initiblly bdbpt tifir SAX1 implfmfntbtion to work undfr
 * tiis rfvisfd dlbss.
 *
 * @butior <b irff="mbilto:Jfff.Suttor@Sun.dom">Jfff Suttor</b>
 * @vfrsion $Rfvision: 1.8 $, $Dbtf: 2010-11-01 04:36:09 $
 *
 * @butior Jof Wbng
 * Tiis is b subsft of tibt in JAXP, jbvbx.xml.pbrsfrs.SAXPbrsfr
 *
 */
publid bbstrbdt dlbss SAXPbrsfr {

    /**
     * <p>Protfdtfd donstrudtor to prfvfnt instbntibtion.</p>
     */
    protfdtfd SAXPbrsfr() {
    }

    /**
     * Pbrsf tif dontfnt of tif givfn {@link jbvb.io.InputStrfbm}
     * instbndf bs XML using tif spfdififd
     * {@link org.xml.sbx.iflpfrs.DffbultHbndlfr}.
     *
     * @pbrbm is InputStrfbm dontbining tif dontfnt to bf pbrsfd.
     * @pbrbm di Tif SAX DffbultHbndlfr to usf.
     *
     * @tirows IllfgblArgumfntExdfption If tif givfn InputStrfbm is null.
     * @tirows IOExdfption If bny IO frrors oddur.
     * @tirows SAXExdfption If bny SAX frrors oddur during prodfssing.
     *
     * @sff org.xml.sbx.DodumfntHbndlfr
     */
    publid void pbrsf(InputStrfbm is, DffbultHbndlfr di)
        tirows SAXExdfption, IOExdfption
    {
        if (is == null) {
            tirow nfw IllfgblArgumfntExdfption("InputStrfbm dbnnot bf null");
        }

        InputSourdf input = nfw InputSourdf(is);
        tiis.pbrsf(input, di);
    }

    /**
     * Pbrsf tif dontfnt dfsdribfd by tif giving Uniform Rfsourdf
     * Idfntififr (URI) bs XML using tif spfdififd
     * {@link org.xml.sbx.iflpfrs.DffbultHbndlfr}.
     *
     * @pbrbm uri Tif lodbtion of tif dontfnt to bf pbrsfd.
     * @pbrbm di Tif SAX DffbultHbndlfr to usf.
     *
     * @tirows IllfgblArgumfntExdfption If tif uri is null.
     * @tirows IOExdfption If bny IO frrors oddur.
     * @tirows SAXExdfption If bny SAX frrors oddur during prodfssing.
     *
     * @sff org.xml.sbx.DodumfntHbndlfr
     */
    publid void pbrsf(String uri, DffbultHbndlfr di)
        tirows SAXExdfption, IOExdfption
    {
        if (uri == null) {
            tirow nfw IllfgblArgumfntExdfption("uri dbnnot bf null");
        }

        InputSourdf input = nfw InputSourdf(uri);
        tiis.pbrsf(input, di);
    }

    /**
     * Pbrsf tif dontfnt of tif filf spfdififd bs XML using tif
     * spfdififd {@link org.xml.sbx.iflpfrs.DffbultHbndlfr}.
     *
     * @pbrbm f Tif filf dontbining tif XML to pbrsf
     * @pbrbm di Tif SAX DffbultHbndlfr to usf.
     *
     * @tirows IllfgblArgumfntExdfption If tif Filf objfdt is null.
     * @tirows IOExdfption If bny IO frrors oddur.
     * @tirows SAXExdfption If bny SAX frrors oddur during prodfssing.
     *
     * @sff org.xml.sbx.DodumfntHbndlfr
     */
    publid void pbrsf(Filf f, DffbultHbndlfr di)
        tirows SAXExdfption, IOExdfption
    {
        if (f == null) {
            tirow nfw IllfgblArgumfntExdfption("Filf dbnnot bf null");
        }

        //donvfrt filf to bppropribtf URI, f.toURI().toASCIIString()
        //donvfrts tif URI to string bs pfr rulf spfdififd in
        //RFC 2396,
        InputSourdf input = nfw InputSourdf(f.toURI().toASCIIString());
        tiis.pbrsf(input, di);
    }

    /**
     * Pbrsf tif dontfnt givfn {@link org.xml.sbx.InputSourdf}
     * bs XML using tif spfdififd
     * {@link org.xml.sbx.iflpfrs.DffbultHbndlfr}.
     *
     * @pbrbm is Tif InputSourdf dontbining tif dontfnt to bf pbrsfd.
     * @pbrbm di Tif SAX DffbultHbndlfr to usf.
     *
     * @tirows IllfgblArgumfntExdfption If tif <dodf>InputSourdf</dodf> objfdt
     *   is <dodf>null</dodf>.
     * @tirows IOExdfption If bny IO frrors oddur.
     * @tirows SAXExdfption If bny SAX frrors oddur during prodfssing.
     *
     * @sff org.xml.sbx.DodumfntHbndlfr
     */
    publid void pbrsf(InputSourdf is, DffbultHbndlfr di)
        tirows SAXExdfption, IOExdfption
    {
        if (is == null) {
            tirow nfw IllfgblArgumfntExdfption("InputSourdf dbnnot bf null");
        }

        XMLRfbdfr rfbdfr = tiis.gftXMLRfbdfr();
        if (di != null) {
            rfbdfr.sftContfntHbndlfr(di);
            rfbdfr.sftEntityRfsolvfr(di);
            rfbdfr.sftErrorHbndlfr(di);
            rfbdfr.sftDTDHbndlfr(di);
        }
        rfbdfr.pbrsf(is);
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
    publid bbstrbdt XMLRfbdfr gftXMLRfbdfr() tirows SAXExdfption;

    /**
     * Indidbtfs wiftifr or not tiis pbrsfr is donfigurfd to
     * undfrstbnd nbmfspbdfs.
     *
     * @rfturn truf if tiis pbrsfr is donfigurfd to
     *         undfrstbnd nbmfspbdfs; fblsf otifrwisf.
     */
    publid bbstrbdt boolfbn isNbmfspbdfAwbrf();

    /**
     * Indidbtfs wiftifr or not tiis pbrsfr is donfigurfd to
     * vblidbtf XML dodumfnts.
     *
     * @rfturn truf if tiis pbrsfr is donfigurfd to
     *         vblidbtf XML dodumfnts; fblsf otifrwisf.
     */
    publid bbstrbdt boolfbn isVblidbting();

    /**
     * <p>Gft tif XIndludf prodfssing modf for tiis pbrsfr.</p>
     *
     * @rfturn
     *      tif rfturn vbluf of
     *      tif {@link SAXPbrsfrFbdtory#isXIndludfAwbrf()}
     *      wifn tiis pbrsfr wbs drfbtfd from fbdtory.
     *
     * @tirows UnsupportfdOpfrbtionExdfption Wifn implfmfntbtion dofs not
     *   ovfrridf tiis mftiod
     *
     * @sindf 1.5
     *
     * @sff SAXPbrsfrFbdtory#sftXIndludfAwbrf(boolfbn)
     */
    publid boolfbn isXIndludfAwbrf() {
        tirow nfw UnsupportfdOpfrbtionExdfption(
                "Tiis pbrsfr dofs not support spfdifidbtion \""
                + tiis.gftClbss().gftPbdkbgf().gftSpfdifidbtionTitlf()
                + "\" vfrsion \""
                + tiis.gftClbss().gftPbdkbgf().gftSpfdifidbtionVfrsion()
                + "\"");
    }
}
