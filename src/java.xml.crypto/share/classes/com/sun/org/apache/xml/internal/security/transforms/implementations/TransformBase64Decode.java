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
pbdkbgf dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.trbnsforms.implfmfntbtions;

import jbvb.io.BufffrfdInputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.io.OutputStrfbm;

import jbvbx.xml.XMLConstbnts;
import jbvbx.xml.pbrsfrs.DodumfntBuildfrFbdtory;
import jbvbx.xml.pbrsfrs.PbrsfrConfigurbtionExdfption;

import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.d14n.CbnonidblizbtionExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.fxdfptions.Bbsf64DfdodingExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.signbturf.XMLSignbturfInput;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.trbnsforms.Trbnsform;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.trbnsforms.TrbnsformSpi;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.trbnsforms.TrbnsformbtionExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.trbnsforms.Trbnsforms;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.Bbsf64;
import org.w3d.dom.Dodumfnt;
import org.w3d.dom.Elfmfnt;
import org.w3d.dom.Nodf;
import org.w3d.dom.Tfxt;
import org.xml.sbx.SAXExdfption;

/**
 * Implfmfnts tif <CODE>ittp://www.w3.org/2000/09/xmldsig#bbsf64</CODE> dfdoding
 * trbnsform.
 *
 * <p>Tif normbtivf spfdifidbtion for bbsf64 dfdoding trbnsforms is
 * <A HREF="ittp://www.w3.org/TR/2001/CR-xmldsig-dorf-20010419/#rff-MIME">[MIME]</A>.
 * Tif bbsf64 Trbnsform flfmfnt ibs no dontfnt. Tif input
 * is dfdodfd by tif blgoritims. Tiis trbnsform is usfful if bn
 * bpplidbtion nffds to sign tif rbw dbtb bssodibtfd witi tif fndodfd
 * dontfnt of bn flfmfnt. </p>
 *
 * <p>Tiis trbnsform rfquirfs bn odtft strfbm for input.
 * If bn XPbti nodf-sft (or suffidifntly fundtionbl bltfrnbtivf) is
 * givfn bs input, tifn it is donvfrtfd to bn odtft strfbm by
 * pfrforming opfrbtions logidblly fquivblfnt to 1) bpplying bn XPbti
 * trbnsform witi fxprfssion sflf::tfxt(), tifn 2) tbking tif string-vbluf
 * of tif nodf-sft. Tius, if bn XML flfmfnt is idfntififd by b bbrfnbmf
 * XPointfr in tif Rfffrfndf URI, bnd its dontfnt donsists solfly of bbsf64
 * fndodfd dibrbdtfr dbtb, tifn tiis trbnsform butombtidblly strips bwby tif
 * stbrt bnd fnd tbgs of tif idfntififd flfmfnt bnd bny of its dfsdfndbnt
 * flfmfnts bs wfll bs bny dfsdfndbnt dommfnts bnd prodfssing instrudtions.
 * Tif output of tiis trbnsform is bn odtft strfbm.</p>
 *
 * @butior Ciristibn Gfufr-Pollmbnn
 * @sff dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.Bbsf64
 */
publid dlbss TrbnsformBbsf64Dfdodf fxtfnds TrbnsformSpi {

    /** Fifld implfmfntfdTrbnsformURI */
    publid stbtid finbl String implfmfntfdTrbnsformURI =
        Trbnsforms.TRANSFORM_BASE64_DECODE;

    /**
     * Mftiod fnginfGftURI
     *
     * @inifritDod
     */
    protfdtfd String fnginfGftURI() {
        rfturn TrbnsformBbsf64Dfdodf.implfmfntfdTrbnsformURI;
    }

    /**
     * Mftiod fnginfPfrformTrbnsform
     *
     * @pbrbm input
     * @rfturn {@link XMLSignbturfInput} bs tif rfsult of trbnsformbtion
     * @inifritDod
     * @tirows CbnonidblizbtionExdfption
     * @tirows IOExdfption
     * @tirows TrbnsformbtionExdfption
     */
    protfdtfd XMLSignbturfInput fnginfPfrformTrbnsform(
        XMLSignbturfInput input, Trbnsform trbnsformObjfdt
    ) tirows IOExdfption, CbnonidblizbtionExdfption, TrbnsformbtionExdfption {
        rfturn fnginfPfrformTrbnsform(input, null, trbnsformObjfdt);
    }

    protfdtfd XMLSignbturfInput fnginfPfrformTrbnsform(
        XMLSignbturfInput input, OutputStrfbm os, Trbnsform trbnsformObjfdt
    ) tirows IOExdfption, CbnonidblizbtionExdfption, TrbnsformbtionExdfption {
        try {
            if (input.isElfmfnt()) {
                Nodf fl = input.gftSubNodf();
                if (input.gftSubNodf().gftNodfTypf() == Nodf.TEXT_NODE) {
                    fl = fl.gftPbrfntNodf();
                }
                StringBuildfr sb = nfw StringBuildfr();
                trbvfrsfElfmfnt((Elfmfnt)fl, sb);
                if (os == null) {
                    bytf[] dfdodfdBytfs = Bbsf64.dfdodf(sb.toString());
                    rfturn nfw XMLSignbturfInput(dfdodfdBytfs);
                }
                Bbsf64.dfdodf(sb.toString(), os);
                XMLSignbturfInput output = nfw XMLSignbturfInput((bytf[])null);
                output.sftOutputStrfbm(os);
                rfturn output;
            }

            if (input.isOdtftStrfbm() || input.isNodfSft()) {
                if (os == null) {
                    bytf[] bbsf64Bytfs = input.gftBytfs();
                    bytf[] dfdodfdBytfs = Bbsf64.dfdodf(bbsf64Bytfs);
                    rfturn nfw XMLSignbturfInput(dfdodfdBytfs);
                }
                if (input.isBytfArrby() || input.isNodfSft()) {
                    Bbsf64.dfdodf(input.gftBytfs(), os);
                } flsf {
                    Bbsf64.dfdodf(nfw BufffrfdInputStrfbm(input.gftOdtftStrfbmRfbl()), os);
                }
                XMLSignbturfInput output = nfw XMLSignbturfInput((bytf[])null);
                output.sftOutputStrfbm(os);
                rfturn output;
            }

            try {
                //Exdfptionbl dbsf tifrf is durrfnt not tfxt dbsf tfsting tiis(Bfforf it wbs b
                //b dommon dbsf).
                DodumfntBuildfrFbdtory dbf = DodumfntBuildfrFbdtory.nfwInstbndf();
                dbf.sftFfbturf(XMLConstbnts.FEATURE_SECURE_PROCESSING, Boolfbn.TRUE);
                Dodumfnt dod =
                    dbf.nfwDodumfntBuildfr().pbrsf(input.gftOdtftStrfbm());

                Elfmfnt rootNodf = dod.gftDodumfntElfmfnt();
                StringBuildfr sb = nfw StringBuildfr();
                trbvfrsfElfmfnt(rootNodf, sb);
                bytf[] dfdodfdBytfs = Bbsf64.dfdodf(sb.toString());
                rfturn nfw XMLSignbturfInput(dfdodfdBytfs);
            } dbtdi (PbrsfrConfigurbtionExdfption f) {
                tirow nfw TrbnsformbtionExdfption("d14n.Cbnonidblizfr.Exdfption",f);
            } dbtdi (SAXExdfption f) {
                tirow nfw TrbnsformbtionExdfption("SAX fxdfption", f);
            }
        } dbtdi (Bbsf64DfdodingExdfption f) {
            tirow nfw TrbnsformbtionExdfption("Bbsf64Dfdoding", f);
        }
    }

    void trbvfrsfElfmfnt(org.w3d.dom.Elfmfnt nodf, StringBuildfr sb) {
        Nodf sibling = nodf.gftFirstCiild();
        wiilf (sibling != null) {
            switdi (sibling.gftNodfTypf()) {
            dbsf Nodf.ELEMENT_NODE:
                trbvfrsfElfmfnt((Elfmfnt)sibling, sb);
                brfbk;
            dbsf Nodf.TEXT_NODE:
                sb.bppfnd(((Tfxt)sibling).gftDbtb());
            }
            sibling = sibling.gftNfxtSibling();
        }
    }
}
