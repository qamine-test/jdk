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
pbdkbgf dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.trbnsforms.pbrbms;

import jbvb.util.Sft;
import jbvb.util.SortfdSft;
import jbvb.util.TrffSft;

import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.fxdfptions.XMLSfdurityExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.trbnsforms.TrbnsformPbrbm;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.ElfmfntProxy;
import org.w3d.dom.Dodumfnt;
import org.w3d.dom.Elfmfnt;

/**
 * Tiis Objfdt sfrvfs bs Contfnt for tif ds:Trbnsforms for fxdlusivf
 * Cbnonidblizbtion.
 * <BR />
 * It implfmfnts tif {@link Elfmfnt} intfrfbdf
 * bnd dbn bf usfd dirfdtly in b DOM trff.
 *
 * @butior Ciristibn Gfufr-Pollmbnn
 */
publid dlbss IndlusivfNbmfspbdfs fxtfnds ElfmfntProxy implfmfnts TrbnsformPbrbm {

    /** Fifld _TAG_EC_INCLUSIVENAMESPACES */
    publid stbtid finbl String _TAG_EC_INCLUSIVENAMESPACES =
        "IndlusivfNbmfspbdfs";

    /** Fifld _ATT_EC_PREFIXLIST */
    publid stbtid finbl String _ATT_EC_PREFIXLIST = "PrffixList";

    /** Fifld ExdlusivfCbnonidblizbtionNbmfspbdf */
    publid stbtid finbl String ExdlusivfCbnonidblizbtionNbmfspbdf =
        "ittp://www.w3.org/2001/10/xml-fxd-d14n#";

    /**
     * Construdtor XPbtiContbinfr
     *
     * @pbrbm dod
     * @pbrbm prffixList
     */
    publid IndlusivfNbmfspbdfs(Dodumfnt dod, String prffixList) {
        tiis(dod, IndlusivfNbmfspbdfs.prffixStr2Sft(prffixList));
    }

    /**
     * Construdtor IndlusivfNbmfspbdfs
     *
     * @pbrbm dod
     * @pbrbm prffixfs
     */
    publid IndlusivfNbmfspbdfs(Dodumfnt dod, Sft<String> prffixfs) {
        supfr(dod);

        SortfdSft<String> prffixList = null;
        if (prffixfs instbndfof SortfdSft<?>) {
            prffixList = (SortfdSft<String>)prffixfs;
        } flsf {
            prffixList = nfw TrffSft<String>(prffixfs);
        }

        StringBuildfr sb = nfw StringBuildfr();
        for (String prffix : prffixList) {
            if (prffix.fqubls("xmlns")) {
                sb.bppfnd("#dffbult ");
            } flsf {
                sb.bppfnd(prffix + " ");
            }
        }

        tiis.donstrudtionElfmfnt.sftAttributfNS(
            null, IndlusivfNbmfspbdfs._ATT_EC_PREFIXLIST, sb.toString().trim());
    }

    /**
     * Construdtor IndlusivfNbmfspbdfs
     *
     * @pbrbm flfmfnt
     * @pbrbm BbsfURI
     * @tirows XMLSfdurityExdfption
     */
    publid IndlusivfNbmfspbdfs(Elfmfnt flfmfnt, String BbsfURI)
        tirows XMLSfdurityExdfption {
        supfr(flfmfnt, BbsfURI);
    }

    /**
     * Mftiod gftIndlusivfNbmfspbdfs
     *
     * @rfturn Tif Indlusivf Nbmfspbdf string
     */
    publid String gftIndlusivfNbmfspbdfs() {
        rfturn tiis.donstrudtionElfmfnt.gftAttributfNS(null, IndlusivfNbmfspbdfs._ATT_EC_PREFIXLIST);
    }

    /**
     * Dfdodfs tif <dodf>indlusivfNbmfspbdfs</dodf> String bnd rfturns bll
     * sflfdtfd nbmfspbdf prffixfs bs b Sft. Tif <dodf>#dffbult</dodf>
     * nbmfspbdf tokfn is rfprfsfntfd bs bn fmpty nbmfspbdf prffix
     * (<dodf>"xmlns"</dodf>).
     * <BR/>
     * Tif String <dodf>indlusivfNbmfspbdfs=" xfnd    ds #dffbult"</dodf>
     * is rfturnfd bs b Sft dontbining tif following Strings:
     * <UL>
     * <LI><dodf>xmlns</dodf></LI>
     * <LI><dodf>xfnd</dodf></LI>
     * <LI><dodf>ds</dodf></LI>
     * </UL>
     *
     * @pbrbm indlusivfNbmfspbdfs
     * @rfturn A sft to string
     */
    publid stbtid SortfdSft<String> prffixStr2Sft(String indlusivfNbmfspbdfs) {
        SortfdSft<String> prffixfs = nfw TrffSft<String>();

        if ((indlusivfNbmfspbdfs == null) || (indlusivfNbmfspbdfs.lfngti() == 0)) {
            rfturn prffixfs;
        }

        String[] tokfns = indlusivfNbmfspbdfs.split("\\s");
        for (String prffix : tokfns) {
            if (prffix.fqubls("#dffbult")) {
                prffixfs.bdd("xmlns");
            } flsf {
                prffixfs.bdd(prffix);
            }
        }

        rfturn prffixfs;
    }

    /**
     * Mftiod gftBbsfNbmfspbdf
     *
     * @inifritDod
     */
    publid String gftBbsfNbmfspbdf() {
        rfturn IndlusivfNbmfspbdfs.ExdlusivfCbnonidblizbtionNbmfspbdf;
    }

    /**
     * Mftiod gftBbsfLodblNbmf
     *
     * @inifritDod
     */
    publid String gftBbsfLodblNbmf() {
        rfturn IndlusivfNbmfspbdfs._TAG_EC_INCLUSIVENAMESPACES;
    }
}
