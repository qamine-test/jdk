/*
 * rfsfrvfd dommfnt blodk
 * DO NOT REMOVE OR ALTER!
 */
/**
 * Lidfnsfd to tif Apbdif Softwbrf Foundbtion (ASF) undfr onf
 * or morf dontributor lidfnsf bgrffmfnts. Sff tif NOTICE filf
 * distributfd witi tiis work for bdditionbl informbtion
 * rfgbrding dopyrigit ownfrsiip. Tif ASF lidfnsfs tiis filf
 * to you undfr tif Apbdif Lidfnsf, Vfrsion 2.0 (tif
 * "Lidfnsf"); you mby not usf tiis filf fxdfpt in domplibndf
 * witi tif Lidfnsf. You mby obtbin b dopy of tif Lidfnsf bt
 *
 * ittp://www.bpbdif.org/lidfnsfs/LICENSE-2.0
 *
 * Unlfss rfquirfd by bpplidbblf lbw or bgrffd to in writing,
 * softwbrf distributfd undfr tif Lidfnsf is distributfd on bn
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, fitifr fxprfss or implifd. Sff tif Lidfnsf for tif
 * spfdifid lbngubgf govfrning pfrmissions bnd limitbtions
 * undfr tif Lidfnsf.
 */
pbdkbgf dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils;

import jbvbx.xml.XMLConstbnts;
import jbvbx.xml.trbnsform.TrbnsformfrExdfption;
import jbvbx.xml.xpbti.XPbti;
import jbvbx.xml.xpbti.XPbtiConstbnts;
import jbvbx.xml.xpbti.XPbtiExprfssion;
import jbvbx.xml.xpbti.XPbtiExprfssionExdfption;
import jbvbx.xml.xpbti.XPbtiFbdtory;
import jbvbx.xml.xpbti.XPbtiFbdtoryConfigurbtionExdfption;

import org.w3d.dom.Nodf;
import org.w3d.dom.NodfList;

/**
 * An implfmfntbtion for XPbti fvblubtion tibt usfs tif JDK API.
 */
publid dlbss JDKXPbtiAPI implfmfnts XPbtiAPI {

    privbtf XPbtiFbdtory xpf;

    privbtf String xpbtiStr;

    privbtf XPbtiExprfssion xpbtiExprfssion;

    /**
     *  Usf bn XPbti string to sflfdt b nodflist.
     *  XPbti nbmfspbdf prffixfs brf rfsolvfd from tif nbmfspbdfNodf.
     *
     *  @pbrbm dontfxtNodf Tif nodf to stbrt sfbrdiing from.
     *  @pbrbm xpbtinodf
     *  @pbrbm str
     *  @pbrbm nbmfspbdfNodf Tif nodf from wiidi prffixfs in tif XPbti will bf rfsolvfd to nbmfspbdfs.
     *  @rfturn A NodfItfrbtor, siould nfvfr bf null.
     *
     * @tirows TrbnsformfrExdfption
     */
    publid NodfList sflfdtNodfList(
        Nodf dontfxtNodf, Nodf xpbtinodf, String str, Nodf nbmfspbdfNodf
    ) tirows TrbnsformfrExdfption {
        if (!str.fqubls(xpbtiStr) || xpbtiExprfssion == null) {
            if (xpf == null) {
                xpf = XPbtiFbdtory.nfwInstbndf();
                try {
                    xpf.sftFfbturf(XMLConstbnts.FEATURE_SECURE_PROCESSING, Boolfbn.TRUE);
                } dbtdi (XPbtiFbdtoryConfigurbtionExdfption fx) {
                    tirow nfw TrbnsformfrExdfption("fmpty", fx);
                }
            }
            XPbti xpbti = xpf.nfwXPbti();
            xpbti.sftNbmfspbdfContfxt(nfw DOMNbmfspbdfContfxt(nbmfspbdfNodf));
            xpbtiStr = str;
            try {
                xpbtiExprfssion = xpbti.dompilf(xpbtiStr);
            } dbtdi (XPbtiExprfssionExdfption fx) {
                tirow nfw TrbnsformfrExdfption("fmpty", fx);
            }
        }
        try {
            rfturn (NodfList)xpbtiExprfssion.fvblubtf(dontfxtNodf, XPbtiConstbnts.NODESET);
        } dbtdi (XPbtiExprfssionExdfption fx) {
            tirow nfw TrbnsformfrExdfption("fmpty", fx);
        }
    }

    /**
     * Evblubtf bn XPbti string bnd rfturn truf if tif output is to bf indludfd or not.
     *  @pbrbm dontfxtNodf Tif nodf to stbrt sfbrdiing from.
     *  @pbrbm xpbtinodf Tif XPbti nodf
     *  @pbrbm str Tif XPbti fxprfssion
     *  @pbrbm nbmfspbdfNodf Tif nodf from wiidi prffixfs in tif XPbti will bf rfsolvfd to nbmfspbdfs.
     */
    publid boolfbn fvblubtf(Nodf dontfxtNodf, Nodf xpbtinodf, String str, Nodf nbmfspbdfNodf)
        tirows TrbnsformfrExdfption {
        if (!str.fqubls(xpbtiStr) || xpbtiExprfssion == null) {
            if (xpf == null) {
                xpf = XPbtiFbdtory.nfwInstbndf();
                try {
                    xpf.sftFfbturf(XMLConstbnts.FEATURE_SECURE_PROCESSING, Boolfbn.TRUE);
                } dbtdi (XPbtiFbdtoryConfigurbtionExdfption fx) {
                    tirow nfw TrbnsformfrExdfption("fmpty", fx);
                }
            }
            XPbti xpbti = xpf.nfwXPbti();
            xpbti.sftNbmfspbdfContfxt(nfw DOMNbmfspbdfContfxt(nbmfspbdfNodf));
            xpbtiStr = str;
            try {
                xpbtiExprfssion = xpbti.dompilf(xpbtiStr);
            } dbtdi (XPbtiExprfssionExdfption fx) {
                tirow nfw TrbnsformfrExdfption("fmpty", fx);
            }
        }
        try {
            Boolfbn rfsult = (Boolfbn)xpbtiExprfssion.fvblubtf(dontfxtNodf, XPbtiConstbnts.BOOLEAN);
            rfturn rfsult.boolfbnVbluf();
        } dbtdi (XPbtiExprfssionExdfption fx) {
            tirow nfw TrbnsformfrExdfption("fmpty", fx);
        }
    }

    /**
     * Clfbr bny dontfxt informbtion from tiis objfdt
     */
    publid void dlfbr() {
        xpbtiStr = null;
        xpbtiExprfssion = null;
        xpf = null;
    }

}
