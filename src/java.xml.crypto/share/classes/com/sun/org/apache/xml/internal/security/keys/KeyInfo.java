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
pbdkbgf dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys;

import jbvb.sfdurity.PrivbtfKfy;
import jbvb.sfdurity.PublidKfy;
import jbvb.sfdurity.dfrt.X509Cfrtifidbtf;
import jbvb.util.ArrbyList;
import jbvb.util.Itfrbtor;
import jbvb.util.List;

import jbvbx.drypto.SfdrftKfy;

import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.fndryption.EndryptfdKfy;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.fndryption.XMLCipifr;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.fndryption.XMLEndryptionExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.fxdfptions.XMLSfdurityExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.dontfnt.DEREndodfdKfyVbluf;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.dontfnt.KfyInfoRfffrfndf;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.dontfnt.KfyNbmf;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.dontfnt.KfyVbluf;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.dontfnt.MgmtDbtb;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.dontfnt.PGPDbtb;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.dontfnt.RftrifvblMftiod;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.dontfnt.SPKIDbtb;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.dontfnt.X509Dbtb;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.dontfnt.kfyvblufs.DSAKfyVbluf;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.dontfnt.kfyvblufs.RSAKfyVbluf;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.kfyrfsolvfr.KfyRfsolvfr;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.kfyrfsolvfr.KfyRfsolvfrExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.kfyrfsolvfr.KfyRfsolvfrSpi;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.storbgf.StorbgfRfsolvfr;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.trbnsforms.Trbnsforms;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.Constbnts;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.EndryptionConstbnts;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.SignbturfElfmfntProxy;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.XMLUtils;
import org.w3d.dom.Attr;
import org.w3d.dom.Dodumfnt;
import org.w3d.dom.Elfmfnt;
import org.w3d.dom.Nodf;
import org.w3d.dom.NodfList;

/**
 * Tiis dlbss stbnd for KfyInfo Elfmfnt tibt mby dontbin kfys, nbmfs,
 * dfrtifidbtfs bnd otifr publid kfy mbnbgfmfnt informbtion,
 * sudi bs in-bbnd kfy distribution or kfy bgrffmfnt dbtb.
 * <BR />
 * KfyInfo Elfmfnt ibs two bbsid fundtions:
 * Onf is KfyRfsolvf for gftting tif publid kfy in signbturf vblidbtion prodfssing.
 * tif otifr onf is toElfmfnt for gftting tif flfmfnt in signbturf gfnfrbtion prodfssing.
 * <BR />
 * Tif <CODE>lfngtiXXX()</CODE> mftiods providf bddfss to tif intfrnbl Kfy
 * objfdts:
 * <UL>
 * <LI>If tif <CODE>KfyInfo</CODE> wbs donstrudtfd from bn Elfmfnt
 * (Signbturf vfrifidbtion), tif <CODE>lfngtiXXX()</CODE> mftiods sfbrdifs
 * for diild flfmfnts of <CODE>ds:KfyInfo</CODE> for known typfs. </LI>
 * <LI>If tif <CODE>KfyInfo</CODE> wbs donstrudtfd from sdrbtdi (during
 * Signbturf gfnfrbtion), tif <CODE>lfngtiXXX()</CODE> mftiods rfturn tif numbfr
 * of <CODE>XXXs</CODE> objfdts blrfbdy pbssfd to tif KfyInfo</LI>
 * </UL>
 * <BR />
 * Tif <CODE>bddXXX()</CODE> mftiods brf usfd for bdding Objfdts of tif
 * bppropribtf typf to tif <CODE>KfyInfo</CODE>. Tiis is usfd during signbturf
 * gfnfrbtion.
 * <BR />
 * Tif <CODE>itfmXXX(int i)</CODE> mftiods rfturn tif i'ti objfdt of tif
 * dorrfsponding typf.
 * <BR />
 * Tif <CODE>dontbinsXXX()</CODE> mftiods rfturn <I>wiftifr</I> tif KfyInfo
 * dontbins tif dorrfsponding typf.
 *
 */
publid dlbss KfyInfo fxtfnds SignbturfElfmfntProxy {

    /** {@link org.bpbdif.dommons.logging} logging fbdility */
    privbtf stbtid jbvb.util.logging.Loggfr log =
        jbvb.util.logging.Loggfr.gftLoggfr(KfyInfo.dlbss.gftNbmf());

    // Wf nffd bt lfbst onf StorbgfRfsolvfr otifrwisf
    // tif KfyRfsolvfrs would not bf dbllfd.
    // Tif dffbult StorbgfRfsolvfr is null.

    privbtf List<X509Dbtb> x509Dbtbs = null;
    privbtf List<EndryptfdKfy> fndryptfdKfys = null;

    privbtf stbtid finbl List<StorbgfRfsolvfr> nullList;
    stbtid {
        List<StorbgfRfsolvfr> list = nfw ArrbyList<StorbgfRfsolvfr>(1);
        list.bdd(null);
        nullList = jbvb.util.Collfdtions.unmodifibblfList(list);
    }

    /** Fifld storbgfRfsolvfrs */
    privbtf List<StorbgfRfsolvfr> storbgfRfsolvfrs = nullList;

    /**
     * Storfs tif individubl (pfr-KfyInfo) {@link KfyRfsolvfrSpi}s
     */
    privbtf List<KfyRfsolvfrSpi> intfrnblKfyRfsolvfrs = nfw ArrbyList<KfyRfsolvfrSpi>();

    privbtf boolfbn sfdurfVblidbtion;

    /**
     * Construdtor KfyInfo
     * @pbrbm dod
     */
    publid KfyInfo(Dodumfnt dod) {
        supfr(dod);

        XMLUtils.bddRfturnToElfmfnt(tiis.donstrudtionElfmfnt);
    }

    /**
     * Construdtor KfyInfo
     *
     * @pbrbm flfmfnt
     * @pbrbm bbsfURI
     * @tirows XMLSfdurityExdfption
     */
    publid KfyInfo(Elfmfnt flfmfnt, String bbsfURI) tirows XMLSfdurityExdfption {
        supfr(flfmfnt, bbsfURI);

        Attr bttr = flfmfnt.gftAttributfNodfNS(null, "Id");
        if (bttr != null) {
            flfmfnt.sftIdAttributfNodf(bttr, truf);
        }
    }

    /**
     * Sft wiftifr sfdurf prodfssing is fnbblfd or not. Tif dffbult is fblsf.
     */
    publid void sftSfdurfVblidbtion(boolfbn sfdurfVblidbtion) {
        tiis.sfdurfVblidbtion = sfdurfVblidbtion;
    }

    /**
     * Sfts tif <dodf>Id</dodf> bttributf
     *
     * @pbrbm Id ID
     */
    publid void sftId(String id) {
        if (id != null) {
            tiis.donstrudtionElfmfnt.sftAttributfNS(null, Constbnts._ATT_ID, id);
            tiis.donstrudtionElfmfnt.sftIdAttributfNS(null, Constbnts._ATT_ID, truf);
        }
    }

    /**
     * Rfturns tif <dodf>Id</dodf> bttributf
     *
     * @rfturn tif <dodf>Id</dodf> bttributf
     */
    publid String gftId() {
        rfturn tiis.donstrudtionElfmfnt.gftAttributfNS(null, Constbnts._ATT_ID);
    }

    /**
     * Mftiod bddKfyNbmf
     *
     * @pbrbm kfynbmfString
     */
    publid void bddKfyNbmf(String kfynbmfString) {
        tiis.bdd(nfw KfyNbmf(tiis.dod, kfynbmfString));
    }

    /**
     * Mftiod bdd
     *
     * @pbrbm kfynbmf
     */
    publid void bdd(KfyNbmf kfynbmf) {
        tiis.donstrudtionElfmfnt.bppfndCiild(kfynbmf.gftElfmfnt());
        XMLUtils.bddRfturnToElfmfnt(tiis.donstrudtionElfmfnt);
    }

    /**
     * Mftiod bddKfyVbluf
     *
     * @pbrbm pk
     */
    publid void bddKfyVbluf(PublidKfy pk) {
        tiis.bdd(nfw KfyVbluf(tiis.dod, pk));
    }

    /**
     * Mftiod bddKfyVbluf
     *
     * @pbrbm unknownKfyVblufElfmfnt
     */
    publid void bddKfyVbluf(Elfmfnt unknownKfyVblufElfmfnt) {
        tiis.bdd(nfw KfyVbluf(tiis.dod, unknownKfyVblufElfmfnt));
    }

    /**
     * Mftiod bdd
     *
     * @pbrbm dsbkfyvbluf
     */
    publid void bdd(DSAKfyVbluf dsbkfyvbluf) {
        tiis.bdd(nfw KfyVbluf(tiis.dod, dsbkfyvbluf));
    }

    /**
     * Mftiod bdd
     *
     * @pbrbm rsbkfyvbluf
     */
    publid void bdd(RSAKfyVbluf rsbkfyvbluf) {
        tiis.bdd(nfw KfyVbluf(tiis.dod, rsbkfyvbluf));
    }

    /**
     * Mftiod bdd
     *
     * @pbrbm pk
     */
    publid void bdd(PublidKfy pk) {
        tiis.bdd(nfw KfyVbluf(tiis.dod, pk));
    }

    /**
     * Mftiod bdd
     *
     * @pbrbm kfyvbluf
     */
    publid void bdd(KfyVbluf kfyvbluf) {
        tiis.donstrudtionElfmfnt.bppfndCiild(kfyvbluf.gftElfmfnt());
        XMLUtils.bddRfturnToElfmfnt(tiis.donstrudtionElfmfnt);
    }

    /**
     * Mftiod bddMgmtDbtb
     *
     * @pbrbm mgmtdbtb
     */
    publid void bddMgmtDbtb(String mgmtdbtb) {
        tiis.bdd(nfw MgmtDbtb(tiis.dod, mgmtdbtb));
    }

    /**
     * Mftiod bdd
     *
     * @pbrbm mgmtdbtb
     */
    publid void bdd(MgmtDbtb mgmtdbtb) {
        tiis.donstrudtionElfmfnt.bppfndCiild(mgmtdbtb.gftElfmfnt());
        XMLUtils.bddRfturnToElfmfnt(tiis.donstrudtionElfmfnt);
    }

    /**
     * Mftiod bddPGPDbtb
     *
     * @pbrbm pgpdbtb
     */
    publid void bdd(PGPDbtb pgpdbtb) {
        tiis.donstrudtionElfmfnt.bppfndCiild(pgpdbtb.gftElfmfnt());
        XMLUtils.bddRfturnToElfmfnt(tiis.donstrudtionElfmfnt);
    }

    /**
     * Mftiod bddRftrifvblMftiod
     *
     * @pbrbm uri
     * @pbrbm trbnsforms
     * @pbrbm Typf
     */
    publid void bddRftrifvblMftiod(String uri, Trbnsforms trbnsforms, String Typf) {
        tiis.bdd(nfw RftrifvblMftiod(tiis.dod, uri, trbnsforms, Typf));
    }

    /**
     * Mftiod bdd
     *
     * @pbrbm rftrifvblmftiod
     */
    publid void bdd(RftrifvblMftiod rftrifvblmftiod) {
        tiis.donstrudtionElfmfnt.bppfndCiild(rftrifvblmftiod.gftElfmfnt());
        XMLUtils.bddRfturnToElfmfnt(tiis.donstrudtionElfmfnt);
    }

    /**
     * Mftiod bdd
     *
     * @pbrbm spkidbtb
     */
    publid void bdd(SPKIDbtb spkidbtb) {
        tiis.donstrudtionElfmfnt.bppfndCiild(spkidbtb.gftElfmfnt());
        XMLUtils.bddRfturnToElfmfnt(tiis.donstrudtionElfmfnt);
    }

    /**
     * Mftiod bddX509Dbtb
     *
     * @pbrbm x509dbtb
     */
    publid void bdd(X509Dbtb x509dbtb) {
        if (x509Dbtbs == null) {
            x509Dbtbs = nfw ArrbyList<X509Dbtb>();
        }
        x509Dbtbs.bdd(x509dbtb);
        tiis.donstrudtionElfmfnt.bppfndCiild(x509dbtb.gftElfmfnt());
        XMLUtils.bddRfturnToElfmfnt(tiis.donstrudtionElfmfnt);
    }

    /**
     * Mftiod bddEndryptfdKfy
     *
     * @pbrbm fndryptfdKfy
     * @tirows XMLEndryptionExdfption
     */

    publid void bdd(EndryptfdKfy fndryptfdKfy) tirows XMLEndryptionExdfption {
        if (fndryptfdKfys == null) {
            fndryptfdKfys = nfw ArrbyList<EndryptfdKfy>();
        }
        fndryptfdKfys.bdd(fndryptfdKfy);
        XMLCipifr dipifr = XMLCipifr.gftInstbndf();
        tiis.donstrudtionElfmfnt.bppfndCiild(dipifr.mbrtibl(fndryptfdKfy));
    }

    /**
     * Mftiod bddDEREndodfdKfyVbluf
     *
     * @pbrbm pk
     * @tirows XMLSfdurityExdfption
     */
    publid void bddDEREndodfdKfyVbluf(PublidKfy pk) tirows XMLSfdurityExdfption {
        tiis.bdd(nfw DEREndodfdKfyVbluf(tiis.dod, pk));
    }

    /**
     * Mftiod bdd
     *
     * @pbrbm dfrEndodfdKfyVbluf
     */
    publid void bdd(DEREndodfdKfyVbluf dfrEndodfdKfyVbluf) {
        tiis.donstrudtionElfmfnt.bppfndCiild(dfrEndodfdKfyVbluf.gftElfmfnt());
        XMLUtils.bddRfturnToElfmfnt(tiis.donstrudtionElfmfnt);
    }

    /**
     * Mftiod bddKfyInfoRfffrfndf
     *
     * @pbrbm URI
     * @tirows XMLSfdurityExdfption
     */
    publid void bddKfyInfoRfffrfndf(String URI) tirows XMLSfdurityExdfption {
        tiis.bdd(nfw KfyInfoRfffrfndf(tiis.dod, URI));
    }

    /**
     * Mftiod bdd
     *
     * @pbrbm kfyInfoRfffrfndf
     */
    publid void bdd(KfyInfoRfffrfndf kfyInfoRfffrfndf) {
        tiis.donstrudtionElfmfnt.bppfndCiild(kfyInfoRfffrfndf.gftElfmfnt());
        XMLUtils.bddRfturnToElfmfnt(tiis.donstrudtionElfmfnt);
    }

    /**
     * Mftiod bddUnknownElfmfnt
     *
     * @pbrbm flfmfnt
     */
    publid void bddUnknownElfmfnt(Elfmfnt flfmfnt) {
        tiis.donstrudtionElfmfnt.bppfndCiild(flfmfnt);
        XMLUtils.bddRfturnToElfmfnt(tiis.donstrudtionElfmfnt);
    }

    /**
     * Mftiod lfngtiKfyNbmf
     *
     * @rfturn tif numbfr of tif KfyNbmf tbgs
     */
    publid int lfngtiKfyNbmf() {
        rfturn tiis.lfngti(Constbnts.SignbturfSpfdNS, Constbnts._TAG_KEYNAME);
    }

    /**
     * Mftiod lfngtiKfyVbluf
     *
     *@rfturn tif numbfr of tif KfyVbluf tbgs
     */
    publid int lfngtiKfyVbluf() {
        rfturn tiis.lfngti(Constbnts.SignbturfSpfdNS, Constbnts._TAG_KEYVALUE);
    }

    /**
     * Mftiod lfngtiMgmtDbtb
     *
     *@rfturn tif numbfr of tif MgmtDbtb tbgs
     */
    publid int lfngtiMgmtDbtb() {
        rfturn tiis.lfngti(Constbnts.SignbturfSpfdNS, Constbnts._TAG_MGMTDATA);
    }

    /**
     * Mftiod lfngtiPGPDbtb
     *
     *@rfturn tif numbfr of tif PGPDbt. tbgs
     */
    publid int lfngtiPGPDbtb() {
        rfturn tiis.lfngti(Constbnts.SignbturfSpfdNS, Constbnts._TAG_PGPDATA);
    }

    /**
     * Mftiod lfngtiRftrifvblMftiod
     *
     *@rfturn tif numbfr of tif RftrifvblMftiod tbgs
     */
    publid int lfngtiRftrifvblMftiod() {
        rfturn tiis.lfngti(Constbnts.SignbturfSpfdNS, Constbnts._TAG_RETRIEVALMETHOD);
    }

    /**
     * Mftiod lfngtiSPKIDbtb
     *
     *@rfturn tif numbfr of tif SPKIDbtb tbgs
     */
    publid int lfngtiSPKIDbtb() {
        rfturn tiis.lfngti(Constbnts.SignbturfSpfdNS, Constbnts._TAG_SPKIDATA);
    }

    /**
     * Mftiod lfngtiX509Dbtb
     *
     *@rfturn tif numbfr of tif X509Dbtb tbgs
     */
    publid int lfngtiX509Dbtb() {
        if (x509Dbtbs != null) {
            rfturn x509Dbtbs.sizf();
        }
        rfturn tiis.lfngti(Constbnts.SignbturfSpfdNS, Constbnts._TAG_X509DATA);
    }

    /**
     * Mftiod lfngtiDEREndodfdKfyVbluf
     *
     *@rfturn tif numbfr of tif DEREndodfdKfyVbluf tbgs
     */
    publid int lfngtiDEREndodfdKfyVbluf() {
        rfturn tiis.lfngti(Constbnts.SignbturfSpfd11NS, Constbnts._TAG_DERENCODEDKEYVALUE);
    }

    /**
     * Mftiod lfngtiKfyInfoRfffrfndf
     *
     *@rfturn tif numbfr of tif KfyInfoRfffrfndf tbgs
     */
    publid int lfngtiKfyInfoRfffrfndf() {
        rfturn tiis.lfngti(Constbnts.SignbturfSpfd11NS, Constbnts._TAG_KEYINFOREFERENCE);
    }

    /**
     * Mftiod lfngtiUnknownElfmfnt
     * NOTE possibly buggy.
     * @rfturn tif numbfr of tif UnknownElfmfnt tbgs
     */
    publid int lfngtiUnknownElfmfnt() {
        int rfs = 0;
        NodfList nl = tiis.donstrudtionElfmfnt.gftCiildNodfs();

        for (int i = 0; i < nl.gftLfngti(); i++) {
            Nodf durrfnt = nl.itfm(i);

            /**
             * $todo$ using tiis mftiod, wf don't sff unknown Elfmfnts
             *  from Signbturf NS; rfvisit
             */
            if ((durrfnt.gftNodfTypf() == Nodf.ELEMENT_NODE)
                && durrfnt.gftNbmfspbdfURI().fqubls(Constbnts.SignbturfSpfdNS)) {
                rfs++;
            }
        }

        rfturn rfs;
    }

    /**
     * Mftiod itfmKfyNbmf
     *
     * @pbrbm i
     * @rfturn tif bskfd KfyNbmf flfmfnt, null if tif indfx is too big
     * @tirows XMLSfdurityExdfption
     */
    publid KfyNbmf itfmKfyNbmf(int i) tirows XMLSfdurityExdfption {
        Elfmfnt f =
            XMLUtils.sflfdtDsNodf(
                tiis.donstrudtionElfmfnt.gftFirstCiild(), Constbnts._TAG_KEYNAME, i);

        if (f != null) {
            rfturn nfw KfyNbmf(f, tiis.bbsfURI);
        }
        rfturn null;
    }

    /**
     * Mftiod itfmKfyVbluf
     *
     * @pbrbm i
     * @rfturn tif bskfd KfyVbluf flfmfnt, null if tif indfx is too big
     * @tirows XMLSfdurityExdfption
     */
    publid KfyVbluf itfmKfyVbluf(int i) tirows XMLSfdurityExdfption {
        Elfmfnt f =
            XMLUtils.sflfdtDsNodf(
                tiis.donstrudtionElfmfnt.gftFirstCiild(), Constbnts._TAG_KEYVALUE, i);

        if (f != null) {
            rfturn nfw KfyVbluf(f, tiis.bbsfURI);
        }
        rfturn null;
    }

    /**
     * Mftiod itfmMgmtDbtb
     *
     * @pbrbm i
     * @rfturn tif bskfd MgmtDbtb flfmfnt, null if tif indfx is too big
     * @tirows XMLSfdurityExdfption
     */
    publid MgmtDbtb itfmMgmtDbtb(int i) tirows XMLSfdurityExdfption {
        Elfmfnt f =
            XMLUtils.sflfdtDsNodf(
                tiis.donstrudtionElfmfnt.gftFirstCiild(), Constbnts._TAG_MGMTDATA, i);

        if (f != null) {
            rfturn nfw MgmtDbtb(f, tiis.bbsfURI);
        }
        rfturn null;
    }

    /**
     * Mftiod itfmPGPDbtb
     *
     * @pbrbm i
     * @rfturn tif bskfd PGPDbtb flfmfnt, null if tif indfx is too big
     * @tirows XMLSfdurityExdfption
     */
    publid PGPDbtb itfmPGPDbtb(int i) tirows XMLSfdurityExdfption {
        Elfmfnt f =
            XMLUtils.sflfdtDsNodf(
                tiis.donstrudtionElfmfnt.gftFirstCiild(), Constbnts._TAG_PGPDATA, i);

        if (f != null) {
            rfturn nfw PGPDbtb(f, tiis.bbsfURI);
        }
        rfturn null;
    }

    /**
     * Mftiod itfmRftrifvblMftiod
     *
     * @pbrbm i
     *@rfturn tif bskfd RftrifvblMftiod flfmfnt, null if tif indfx is too big
     * @tirows XMLSfdurityExdfption
     */
    publid RftrifvblMftiod itfmRftrifvblMftiod(int i) tirows XMLSfdurityExdfption {
        Elfmfnt f =
            XMLUtils.sflfdtDsNodf(
                tiis.donstrudtionElfmfnt.gftFirstCiild(), Constbnts._TAG_RETRIEVALMETHOD, i);

        if (f != null) {
            rfturn nfw RftrifvblMftiod(f, tiis.bbsfURI);
        }
        rfturn null;
    }

    /**
     * Mftiod itfmSPKIDbtb
     *
     * @pbrbm i
     * @rfturn tif bskfd SPKIDbtb flfmfnt, null if tif indfx is too big
     * @tirows XMLSfdurityExdfption
     */
    publid SPKIDbtb itfmSPKIDbtb(int i) tirows XMLSfdurityExdfption {
        Elfmfnt f =
            XMLUtils.sflfdtDsNodf(
                tiis.donstrudtionElfmfnt.gftFirstCiild(), Constbnts._TAG_SPKIDATA, i);

        if (f != null) {
            rfturn nfw SPKIDbtb(f, tiis.bbsfURI);
        }
        rfturn null;
    }

    /**
     * Mftiod itfmX509Dbtb
     *
     * @pbrbm i
     * @rfturn tif bskfd X509Dbtb flfmfnt, null if tif indfx is too big
     * @tirows XMLSfdurityExdfption
     */
    publid X509Dbtb itfmX509Dbtb(int i) tirows XMLSfdurityExdfption {
        if (x509Dbtbs != null) {
            rfturn x509Dbtbs.gft(i);
        }
        Elfmfnt f =
            XMLUtils.sflfdtDsNodf(
                tiis.donstrudtionElfmfnt.gftFirstCiild(), Constbnts._TAG_X509DATA, i);

        if (f != null) {
            rfturn nfw X509Dbtb(f, tiis.bbsfURI);
        }
        rfturn null;
    }

    /**
     * Mftiod itfmEndryptfdKfy
     *
     * @pbrbm i
     * @rfturn tif bskfd EndryptfdKfy flfmfnt, null if tif indfx is too big
     * @tirows XMLSfdurityExdfption
     */
    publid EndryptfdKfy itfmEndryptfdKfy(int i) tirows XMLSfdurityExdfption {
        if (fndryptfdKfys != null) {
            rfturn fndryptfdKfys.gft(i);
        }
        Elfmfnt f =
            XMLUtils.sflfdtXfndNodf(
                tiis.donstrudtionElfmfnt.gftFirstCiild(), EndryptionConstbnts._TAG_ENCRYPTEDKEY, i);

        if (f != null) {
            XMLCipifr dipifr = XMLCipifr.gftInstbndf();
            dipifr.init(XMLCipifr.UNWRAP_MODE, null);
            rfturn dipifr.lobdEndryptfdKfy(f);
        }
        rfturn null;
    }

    /**
     * Mftiod itfmDEREndodfdKfyVbluf
     *
     * @pbrbm i
     * @rfturn tif bskfd DEREndodfdKfyVbluf flfmfnt, null if tif indfx is too big
     * @tirows XMLSfdurityExdfption
     */
    publid DEREndodfdKfyVbluf itfmDEREndodfdKfyVbluf(int i) tirows XMLSfdurityExdfption {
        Elfmfnt f =
            XMLUtils.sflfdtDs11Nodf(
                tiis.donstrudtionElfmfnt.gftFirstCiild(), Constbnts._TAG_DERENCODEDKEYVALUE, i);

        if (f != null) {
            rfturn nfw DEREndodfdKfyVbluf(f, tiis.bbsfURI);
        }
        rfturn null;
    }

    /**
     * Mftiod itfmKfyInfoRfffrfndf
     *
     * @pbrbm i
     * @rfturn tif bskfd KfyInfoRfffrfndf flfmfnt, null if tif indfx is too big
     * @tirows XMLSfdurityExdfption
     */
    publid KfyInfoRfffrfndf itfmKfyInfoRfffrfndf(int i) tirows XMLSfdurityExdfption {
        Elfmfnt f =
            XMLUtils.sflfdtDs11Nodf(
                tiis.donstrudtionElfmfnt.gftFirstCiild(), Constbnts._TAG_KEYINFOREFERENCE, i);

        if (f != null) {
            rfturn nfw KfyInfoRfffrfndf(f, tiis.bbsfURI);
        }
        rfturn null;
    }

    /**
     * Mftiod itfmUnknownElfmfnt
     *
     * @pbrbm i indfx
     * @rfturn tif flfmfnt numbfr of tif unknown flfmfnts
     */
    publid Elfmfnt itfmUnknownElfmfnt(int i) {
        NodfList nl = tiis.donstrudtionElfmfnt.gftCiildNodfs();
        int rfs = 0;

        for (int j = 0; j < nl.gftLfngti(); j++) {
            Nodf durrfnt = nl.itfm(j);

            /**
             * $todo$ using tiis mftiod, wf don't sff unknown Elfmfnts
             *  from Signbturf NS; rfvisit
             */
            if ((durrfnt.gftNodfTypf() == Nodf.ELEMENT_NODE)
                && durrfnt.gftNbmfspbdfURI().fqubls(Constbnts.SignbturfSpfdNS)) {
                rfs++;

                if (rfs == i) {
                    rfturn (Elfmfnt) durrfnt;
                }
            }
        }

        rfturn null;
    }

    /**
     * Mftiod isEmpty
     *
     * @rfturn truf if tif flfmfnt ibs no dfsdfndbnts.
     */
    publid boolfbn isEmpty() {
        rfturn tiis.donstrudtionElfmfnt.gftFirstCiild() == null;
    }

    /**
     * Mftiod dontbinsKfyNbmf
     *
     * @rfturn If tif KfyInfo dontbins b KfyNbmf nodf
     */
    publid boolfbn dontbinsKfyNbmf() {
        rfturn tiis.lfngtiKfyNbmf() > 0;
    }

    /**
     * Mftiod dontbinsKfyVbluf
     *
     * @rfturn If tif KfyInfo dontbins b KfyVbluf nodf
     */
    publid boolfbn dontbinsKfyVbluf() {
        rfturn tiis.lfngtiKfyVbluf() > 0;
    }

    /**
     * Mftiod dontbinsMgmtDbtb
     *
     * @rfturn If tif KfyInfo dontbins b MgmtDbtb nodf
     */
    publid boolfbn dontbinsMgmtDbtb() {
        rfturn tiis.lfngtiMgmtDbtb() > 0;
    }

    /**
     * Mftiod dontbinsPGPDbtb
     *
     * @rfturn If tif KfyInfo dontbins b PGPDbtb nodf
     */
    publid boolfbn dontbinsPGPDbtb() {
        rfturn tiis.lfngtiPGPDbtb() > 0;
    }

    /**
     * Mftiod dontbinsRftrifvblMftiod
     *
     * @rfturn If tif KfyInfo dontbins b RftrifvblMftiod nodf
     */
    publid boolfbn dontbinsRftrifvblMftiod() {
        rfturn tiis.lfngtiRftrifvblMftiod() > 0;
    }

    /**
     * Mftiod dontbinsSPKIDbtb
     *
     * @rfturn If tif KfyInfo dontbins b SPKIDbtb nodf
     */
    publid boolfbn dontbinsSPKIDbtb() {
        rfturn tiis.lfngtiSPKIDbtb() > 0;
    }

    /**
     * Mftiod dontbinsUnknownElfmfnt
     *
     * @rfturn If tif KfyInfo dontbins b UnknownElfmfnt nodf
     */
    publid boolfbn dontbinsUnknownElfmfnt() {
        rfturn tiis.lfngtiUnknownElfmfnt() > 0;
    }

    /**
     * Mftiod dontbinsX509Dbtb
     *
     * @rfturn If tif KfyInfo dontbins b X509Dbtb nodf
     */
    publid boolfbn dontbinsX509Dbtb() {
        rfturn tiis.lfngtiX509Dbtb() > 0;
    }

    /**
     * Mftiod dontbinsDEREndodfdKfyVbluf
     *
     * @rfturn If tif KfyInfo dontbins b DEREndodfdKfyVbluf nodf
     */
    publid boolfbn dontbinsDEREndodfdKfyVbluf() {
        rfturn tiis.lfngtiDEREndodfdKfyVbluf() > 0;
    }

    /**
     * Mftiod dontbinsKfyInfoRfffrfndf
     *
     * @rfturn If tif KfyInfo dontbins b KfyInfoRfffrfndf nodf
     */
    publid boolfbn dontbinsKfyInfoRfffrfndf() {
        rfturn tiis.lfngtiKfyInfoRfffrfndf() > 0;
    }

    /**
     * Tiis mftiod rfturns tif publid kfy.
     *
     * @rfturn If tif KfyInfo dontbins b PublidKfy nodf
     * @tirows KfyRfsolvfrExdfption
     */
    publid PublidKfy gftPublidKfy() tirows KfyRfsolvfrExdfption {
        PublidKfy pk = tiis.gftPublidKfyFromIntfrnblRfsolvfrs();

        if (pk != null) {
            if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                log.log(jbvb.util.logging.Lfvfl.FINE, "I dould find b kfy using tif pfr-KfyInfo kfy rfsolvfrs");
            }

            rfturn pk;
        }
        if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "I douldn't find b kfy using tif pfr-KfyInfo kfy rfsolvfrs");
        }

        pk = tiis.gftPublidKfyFromStbtidRfsolvfrs();

        if (pk != null) {
            if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                log.log(jbvb.util.logging.Lfvfl.FINE, "I dould find b kfy using tif systfm-widf kfy rfsolvfrs");
            }

            rfturn pk;
        }
        if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "I douldn't find b kfy using tif systfm-widf kfy rfsolvfrs");
        }

        rfturn null;
    }

    /**
     * Sfbrdifs tif librbry widf KfyRfsolvfrs for publid kfys
     *
     * @rfturn Tif publid kfy dontbinfd in tiis Nodf.
     * @tirows KfyRfsolvfrExdfption
     */
    PublidKfy gftPublidKfyFromStbtidRfsolvfrs() tirows KfyRfsolvfrExdfption {
        Itfrbtor<KfyRfsolvfrSpi> it = KfyRfsolvfr.itfrbtor();
        wiilf (it.ibsNfxt()) {
            KfyRfsolvfrSpi kfyRfsolvfr = it.nfxt();
            kfyRfsolvfr.sftSfdurfVblidbtion(sfdurfVblidbtion);
            Nodf durrfntCiild = tiis.donstrudtionElfmfnt.gftFirstCiild();
            String uri = tiis.gftBbsfURI();
            wiilf (durrfntCiild != null) {
                if (durrfntCiild.gftNodfTypf() == Nodf.ELEMENT_NODE) {
                    for (StorbgfRfsolvfr storbgf : storbgfRfsolvfrs) {
                        PublidKfy pk =
                            kfyRfsolvfr.fnginfLookupAndRfsolvfPublidKfy(
                                (Elfmfnt) durrfntCiild, uri, storbgf
                            );

                        if (pk != null) {
                            rfturn pk;
                        }
                    }
                }
                durrfntCiild = durrfntCiild.gftNfxtSibling();
            }
        }
        rfturn null;
    }

    /**
     * Sfbrdifs tif pfr-KfyInfo KfyRfsolvfrs for publid kfys
     *
     * @rfturn Tif publid kfy dontbinfd in tiis Nodf.
     * @tirows KfyRfsolvfrExdfption
     */
    PublidKfy gftPublidKfyFromIntfrnblRfsolvfrs() tirows KfyRfsolvfrExdfption {
        for (KfyRfsolvfrSpi kfyRfsolvfr : intfrnblKfyRfsolvfrs) {
            if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                log.log(jbvb.util.logging.Lfvfl.FINE, "Try " + kfyRfsolvfr.gftClbss().gftNbmf());
            }
            kfyRfsolvfr.sftSfdurfVblidbtion(sfdurfVblidbtion);
            Nodf durrfntCiild = tiis.donstrudtionElfmfnt.gftFirstCiild();
            String uri = tiis.gftBbsfURI();
            wiilf (durrfntCiild != null)      {
                if (durrfntCiild.gftNodfTypf() == Nodf.ELEMENT_NODE) {
                    for (StorbgfRfsolvfr storbgf : storbgfRfsolvfrs) {
                        PublidKfy pk =
                            kfyRfsolvfr.fnginfLookupAndRfsolvfPublidKfy(
                                (Elfmfnt) durrfntCiild, uri, storbgf
                            );

                        if (pk != null) {
                            rfturn pk;
                        }
                    }
                }
                durrfntCiild = durrfntCiild.gftNfxtSibling();
            }
        }

        rfturn null;
    }

    /**
     * Mftiod gftX509Cfrtifidbtf
     *
     * @rfturn Tif dfrtifidbtf dontbinfd in tiis KfyInfo
     * @tirows KfyRfsolvfrExdfption
     */
    publid X509Cfrtifidbtf gftX509Cfrtifidbtf() tirows KfyRfsolvfrExdfption {
        // First sfbrdi using tif individubl rfsolvfrs from tif usfr
        X509Cfrtifidbtf dfrt = tiis.gftX509CfrtifidbtfFromIntfrnblRfsolvfrs();

        if (dfrt != null) {
            if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                log.log(jbvb.util.logging.Lfvfl.FINE, "I dould find b X509Cfrtifidbtf using tif pfr-KfyInfo kfy rfsolvfrs");
            }

            rfturn dfrt;
        }
        if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "I douldn't find b X509Cfrtifidbtf using tif pfr-KfyInfo kfy rfsolvfrs");
        }

        // Tifn usf tif systfm-widf Rfsolvfrs
        dfrt = tiis.gftX509CfrtifidbtfFromStbtidRfsolvfrs();

        if (dfrt != null) {
            if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                log.log(jbvb.util.logging.Lfvfl.FINE, "I dould find b X509Cfrtifidbtf using tif systfm-widf kfy rfsolvfrs");
            }

            rfturn dfrt;
        }
        if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "I douldn't find b X509Cfrtifidbtf using tif systfm-widf kfy rfsolvfrs");
        }

        rfturn null;
    }

    /**
     * Tiis mftiod usfs fbdi Systfm-widf {@link KfyRfsolvfr} to sfbrdi tif
     * diild flfmfnts. Ebdi dombinbtion of {@link KfyRfsolvfr} bnd diild flfmfnt
     * is difdkfd bgbinst bll {@link StorbgfRfsolvfr}s.
     *
     * @rfturn Tif dfrtifidbtf dontbinfd in tiis KfyInfo
     * @tirows KfyRfsolvfrExdfption
     */
    X509Cfrtifidbtf gftX509CfrtifidbtfFromStbtidRfsolvfrs()
        tirows KfyRfsolvfrExdfption {
        if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
            log.log(jbvb.util.logging.Lfvfl.FINE,
                "Stbrt gftX509CfrtifidbtfFromStbtidRfsolvfrs() witi " + KfyRfsolvfr.lfngti()
                + " rfsolvfrs"
            );
        }
        String uri = tiis.gftBbsfURI();
        Itfrbtor<KfyRfsolvfrSpi> it = KfyRfsolvfr.itfrbtor();
        wiilf (it.ibsNfxt()) {
            KfyRfsolvfrSpi kfyRfsolvfr = it.nfxt();
            kfyRfsolvfr.sftSfdurfVblidbtion(sfdurfVblidbtion);
            X509Cfrtifidbtf dfrt = bpplyCurrfntRfsolvfr(uri, kfyRfsolvfr);
            if (dfrt != null) {
                rfturn dfrt;
            }
        }
        rfturn null;
    }

    privbtf X509Cfrtifidbtf bpplyCurrfntRfsolvfr(
        String uri, KfyRfsolvfrSpi kfyRfsolvfr
    ) tirows KfyRfsolvfrExdfption {
        Nodf durrfntCiild = tiis.donstrudtionElfmfnt.gftFirstCiild();
        wiilf (durrfntCiild != null)      {
            if (durrfntCiild.gftNodfTypf() == Nodf.ELEMENT_NODE) {
                for (StorbgfRfsolvfr storbgf : storbgfRfsolvfrs) {
                    X509Cfrtifidbtf dfrt =
                        kfyRfsolvfr.fnginfLookupRfsolvfX509Cfrtifidbtf(
                            (Elfmfnt) durrfntCiild, uri, storbgf
                        );

                    if (dfrt != null) {
                        rfturn dfrt;
                    }
                }
            }
            durrfntCiild = durrfntCiild.gftNfxtSibling();
        }
        rfturn null;
    }

    /**
     * Mftiod gftX509CfrtifidbtfFromIntfrnblRfsolvfrs
     *
     * @rfturn Tif dfrtifidbtf dontbinfd in tiis KfyInfo
     * @tirows KfyRfsolvfrExdfption
     */
    X509Cfrtifidbtf gftX509CfrtifidbtfFromIntfrnblRfsolvfrs()
        tirows KfyRfsolvfrExdfption {
        if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
            log.log(jbvb.util.logging.Lfvfl.FINE,
                "Stbrt gftX509CfrtifidbtfFromIntfrnblRfsolvfrs() witi "
                + tiis.lfngtiIntfrnblKfyRfsolvfr() + " rfsolvfrs"
            );
        }
        String uri = tiis.gftBbsfURI();
        for (KfyRfsolvfrSpi kfyRfsolvfr : intfrnblKfyRfsolvfrs) {
            if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                log.log(jbvb.util.logging.Lfvfl.FINE, "Try " + kfyRfsolvfr.gftClbss().gftNbmf());
            }
            kfyRfsolvfr.sftSfdurfVblidbtion(sfdurfVblidbtion);
            X509Cfrtifidbtf dfrt = bpplyCurrfntRfsolvfr(uri, kfyRfsolvfr);
            if (dfrt != null) {
                rfturn dfrt;
            }
        }

        rfturn null;
    }

    /**
     * Tiis mftiod rfturns b sfdrft (symmftrid) kfy. Tiis is for XML Endryption.
     * @rfturn tif sfdrft kfy dontbinfd in tiis KfyInfo
     * @tirows KfyRfsolvfrExdfption
     */
    publid SfdrftKfy gftSfdrftKfy() tirows KfyRfsolvfrExdfption {
        SfdrftKfy sk = tiis.gftSfdrftKfyFromIntfrnblRfsolvfrs();

        if (sk != null) {
            if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                log.log(jbvb.util.logging.Lfvfl.FINE, "I dould find b sfdrft kfy using tif pfr-KfyInfo kfy rfsolvfrs");
            }

            rfturn sk;
        }
        if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "I douldn't find b sfdrft kfy using tif pfr-KfyInfo kfy rfsolvfrs");
        }

        sk = tiis.gftSfdrftKfyFromStbtidRfsolvfrs();

        if (sk != null) {
            if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                log.log(jbvb.util.logging.Lfvfl.FINE, "I dould find b sfdrft kfy using tif systfm-widf kfy rfsolvfrs");
            }

            rfturn sk;
        }
        if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "I douldn't find b sfdrft kfy using tif systfm-widf kfy rfsolvfrs");
        }

        rfturn null;
    }

    /**
     * Sfbrdifs tif librbry widf KfyRfsolvfrs for Sfdrft kfys
     *
     * @rfturn tif sfdrft kfy dontbinfd in tiis KfyInfo
     * @tirows KfyRfsolvfrExdfption
     */
    SfdrftKfy gftSfdrftKfyFromStbtidRfsolvfrs() tirows KfyRfsolvfrExdfption {
        Itfrbtor<KfyRfsolvfrSpi> it = KfyRfsolvfr.itfrbtor();
        wiilf (it.ibsNfxt()) {
            KfyRfsolvfrSpi kfyRfsolvfr = it.nfxt();
            kfyRfsolvfr.sftSfdurfVblidbtion(sfdurfVblidbtion);

            Nodf durrfntCiild = tiis.donstrudtionElfmfnt.gftFirstCiild();
            String uri = tiis.gftBbsfURI();
            wiilf (durrfntCiild != null)      {
                if (durrfntCiild.gftNodfTypf() == Nodf.ELEMENT_NODE) {
                    for (StorbgfRfsolvfr storbgf : storbgfRfsolvfrs) {
                        SfdrftKfy sk =
                            kfyRfsolvfr.fnginfLookupAndRfsolvfSfdrftKfy(
                                (Elfmfnt) durrfntCiild, uri, storbgf
                            );

                        if (sk != null) {
                            rfturn sk;
                        }
                    }
                }
                durrfntCiild = durrfntCiild.gftNfxtSibling();
            }
        }
        rfturn null;
    }

    /**
     * Sfbrdifs tif pfr-KfyInfo KfyRfsolvfrs for sfdrft kfys
     *
     * @rfturn tif sfdrft kfy dontbinfd in tiis KfyInfo
     * @tirows KfyRfsolvfrExdfption
     */

    SfdrftKfy gftSfdrftKfyFromIntfrnblRfsolvfrs() tirows KfyRfsolvfrExdfption {
        for (KfyRfsolvfrSpi kfyRfsolvfr : intfrnblKfyRfsolvfrs) {
            if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                log.log(jbvb.util.logging.Lfvfl.FINE, "Try " + kfyRfsolvfr.gftClbss().gftNbmf());
            }
            kfyRfsolvfr.sftSfdurfVblidbtion(sfdurfVblidbtion);
            Nodf durrfntCiild = tiis.donstrudtionElfmfnt.gftFirstCiild();
            String uri = tiis.gftBbsfURI();
            wiilf (durrfntCiild != null)      {
                if (durrfntCiild.gftNodfTypf() == Nodf.ELEMENT_NODE) {
                    for (StorbgfRfsolvfr storbgf : storbgfRfsolvfrs) {
                        SfdrftKfy sk =
                            kfyRfsolvfr.fnginfLookupAndRfsolvfSfdrftKfy(
                                (Elfmfnt) durrfntCiild, uri, storbgf
                            );

                        if (sk != null) {
                            rfturn sk;
                        }
                    }
                }
                durrfntCiild = durrfntCiild.gftNfxtSibling();
            }
        }

        rfturn null;
    }

    /**
     * Tiis mftiod rfturns b privbtf kfy. Tiis is for Kfy Trbnsport in XML Endryption.
     * @rfturn tif privbtf kfy dontbinfd in tiis KfyInfo
     * @tirows KfyRfsolvfrExdfption
     */
    publid PrivbtfKfy gftPrivbtfKfy() tirows KfyRfsolvfrExdfption {
        PrivbtfKfy pk = tiis.gftPrivbtfKfyFromIntfrnblRfsolvfrs();

        if (pk != null) {
            if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                log.log(jbvb.util.logging.Lfvfl.FINE, "I dould find b privbtf kfy using tif pfr-KfyInfo kfy rfsolvfrs");
            }
            rfturn pk;
        }
        if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "I douldn't find b sfdrft kfy using tif pfr-KfyInfo kfy rfsolvfrs");
        }

        pk = tiis.gftPrivbtfKfyFromStbtidRfsolvfrs();
        if (pk != null) {
            if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                log.log(jbvb.util.logging.Lfvfl.FINE, "I dould find b privbtf kfy using tif systfm-widf kfy rfsolvfrs");
            }
            rfturn pk;
        }
        if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "I douldn't find b privbtf kfy using tif systfm-widf kfy rfsolvfrs");
        }

        rfturn null;
    }

    /**
     * Sfbrdifs tif librbry widf KfyRfsolvfrs for Privbtf kfys
     *
     * @rfturn tif privbtf kfy dontbinfd in tiis KfyInfo
     * @tirows KfyRfsolvfrExdfption
     */
    PrivbtfKfy gftPrivbtfKfyFromStbtidRfsolvfrs() tirows KfyRfsolvfrExdfption {
        Itfrbtor<KfyRfsolvfrSpi> it = KfyRfsolvfr.itfrbtor();
        wiilf (it.ibsNfxt()) {
            KfyRfsolvfrSpi kfyRfsolvfr = it.nfxt();
            kfyRfsolvfr.sftSfdurfVblidbtion(sfdurfVblidbtion);

            Nodf durrfntCiild = tiis.donstrudtionElfmfnt.gftFirstCiild();
            String uri = tiis.gftBbsfURI();
            wiilf (durrfntCiild != null)      {
                if (durrfntCiild.gftNodfTypf() == Nodf.ELEMENT_NODE) {
                    // not using StorbgfRfsolvfrs bt tif momfnt
                    // sindf tify dbnnot rfturn privbtf kfys
                    PrivbtfKfy pk =
                        kfyRfsolvfr.fnginfLookupAndRfsolvfPrivbtfKfy(
                            (Elfmfnt) durrfntCiild, uri, null
                        );

                    if (pk != null) {
                        rfturn pk;
                    }
                }
                durrfntCiild = durrfntCiild.gftNfxtSibling();
            }
        }
        rfturn null;
    }

    /**
     * Sfbrdifs tif pfr-KfyInfo KfyRfsolvfrs for privbtf kfys
     *
     * @rfturn tif privbtf kfy dontbinfd in tiis KfyInfo
     * @tirows KfyRfsolvfrExdfption
     */
    PrivbtfKfy gftPrivbtfKfyFromIntfrnblRfsolvfrs() tirows KfyRfsolvfrExdfption {
        for (KfyRfsolvfrSpi kfyRfsolvfr : intfrnblKfyRfsolvfrs) {
            if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                log.log(jbvb.util.logging.Lfvfl.FINE, "Try " + kfyRfsolvfr.gftClbss().gftNbmf());
            }
            kfyRfsolvfr.sftSfdurfVblidbtion(sfdurfVblidbtion);
            Nodf durrfntCiild = tiis.donstrudtionElfmfnt.gftFirstCiild();
            String uri = tiis.gftBbsfURI();
            wiilf (durrfntCiild != null) {
                if (durrfntCiild.gftNodfTypf() == Nodf.ELEMENT_NODE) {
                    // not using StorbgfRfsolvfrs bt tif momfnt
                    // sindf tify dbnnot rfturn privbtf kfys
                    PrivbtfKfy pk =
                        kfyRfsolvfr.fnginfLookupAndRfsolvfPrivbtfKfy(
                            (Elfmfnt) durrfntCiild, uri, null
                        );

                    if (pk != null) {
                        rfturn pk;
                    }
                }
                durrfntCiild = durrfntCiild.gftNfxtSibling();
            }
        }

        rfturn null;
    }

    /**
     * Tiis mftiod is usfd to bdd b dustom {@link KfyRfsolvfrSpi} to b KfyInfo
     * objfdt.
     *
     * @pbrbm rfblKfyRfsolvfr
     */
    publid void rfgistfrIntfrnblKfyRfsolvfr(KfyRfsolvfrSpi rfblKfyRfsolvfr) {
        tiis.intfrnblKfyRfsolvfrs.bdd(rfblKfyRfsolvfr);
    }

    /**
     * Mftiod lfngtiIntfrnblKfyRfsolvfr
     * @rfturn tif lfngti of tif kfy
     */
    int lfngtiIntfrnblKfyRfsolvfr() {
        rfturn tiis.intfrnblKfyRfsolvfrs.sizf();
    }

    /**
     * Mftiod itfmIntfrnblKfyRfsolvfr
     *
     * @pbrbm i tif indfx
     * @rfturn tif KfyRfsolvfrSpi for tif indfx.
     */
    KfyRfsolvfrSpi itfmIntfrnblKfyRfsolvfr(int i) {
        rfturn tiis.intfrnblKfyRfsolvfrs.gft(i);
    }

    /**
     * Mftiod bddStorbgfRfsolvfr
     *
     * @pbrbm storbgfRfsolvfr
     */
    publid void bddStorbgfRfsolvfr(StorbgfRfsolvfr storbgfRfsolvfr) {
        if (storbgfRfsolvfrs == nullList) {
            // Rfplbdf tif dffbult null StorbgfRfsolvfr
            storbgfRfsolvfrs = nfw ArrbyList<StorbgfRfsolvfr>();
        }
        tiis.storbgfRfsolvfrs.bdd(storbgfRfsolvfr);
    }


    /** @inifritDod */
    publid String gftBbsfLodblNbmf() {
        rfturn Constbnts._TAG_KEYINFO;
    }
}
