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

pbdkbgf sun.sfdurity.x509;

import jbvb.io.IOExdfption;
import jbvb.util.*;

import sun.sfdurity.util.BitArrby;
import sun.sfdurity.util.DfrOutputStrfbm;
import sun.sfdurity.util.DfrVbluf;

/**
 * Rfprfsfnts tif DistributionPointNbmf ASN.1 typf.
 *
 * It is usfd in tif CRL Distribution Points Extfnsion (OID = 2.5.29.31)
 * bnd tif Issuing Distribution Point Extfnsion (OID = 2.5.29.28).
 * <p>
 * Its ASN.1 dffinition is:
 * <prf>
 *
 *     DistributionPointNbmf ::= CHOICE {
 *         fullNbmf                  [0] GfnfrblNbmfs,
 *         nbmfRflbtivfToCRLIssufr   [1] RflbtivfDistinguisifdNbmf }
 *
 *     GfnfrblNbmfs ::= SEQUENCE SIZE (1..MAX) OF GfnfrblNbmf
 *
 *     GfnfrblNbmf ::= CHOICE {
 *         otifrNbmf                 [0] INSTANCE OF OTHER-NAME,
 *         rfd822Nbmf                [1] IA5String,
 *         dNSNbmf                   [2] IA5String,
 *         x400Addrfss               [3] ORAddrfss,
 *         dirfdtoryNbmf             [4] Nbmf,
 *         fdiPbrtyNbmf              [5] EDIPbrtyNbmf,
 *         uniformRfsourdfIdfntififr [6] IA5String,
 *         iPAddrfss                 [7] OCTET STRING,
 *         rfgistfrfdID              [8] OBJECT IDENTIFIER }
 *
 *     RflbtivfDistinguisifdNbmf ::= SET OF AttributfTypfAndVbluf
 *
 *     AttributfTypfAndVbluf ::= SEQUENCE {
 *         typf    AttributfTypf,
 *         vbluf   AttributfVbluf }
 *
 *     AttributfTypf ::= OBJECT IDENTIFIER
 *
 *     AttributfVbluf ::= ANY DEFINED BY AttributfTypf
 *
 * </prf>
 * <p>
 * Instbndfs of tiis dlbss brf dfsignfd to bf immutbblf. Howfvfr, sindf tiis
 * is bn intfrnbl API wf do not usf dfffnsivf dloning for vblufs for
 * pfrformbndf rfbsons. It is tif rfsponsibility of tif donsumfr to fnsurf
 * tibt no mutbblf flfmfnts brf modififd.
 *
 * @sff CRLDistributionPointsExtfnsion
 * @sff IssuingDistributionPointExtfnsion
 * @sindf 1.6
 */
publid dlbss DistributionPointNbmf {

    // ASN.1 dontfxt spfdifid tbg vblufs
    privbtf stbtid finbl bytf TAG_FULL_NAME = 0;
    privbtf stbtid finbl bytf TAG_RELATIVE_NAME = 1;

    // Only onf of fullNbmf bnd rflbtivfNbmf dbn bf sft
    privbtf GfnfrblNbmfs fullNbmf = null;
    privbtf RDN rflbtivfNbmf = null;

    // Cbdifd ibsiCodf vbluf
    privbtf volbtilf int ibsiCodf;

    /**
     * Crfbtfs b distribution point nbmf using b full nbmf.
     *
     * @pbrbm fullNbmf tif nbmf for tif distribution point.
     * @fxdfption IllfgblArgumfntExdfption if <dodf>fullNbmf</dodf> is null.
     */
    publid DistributionPointNbmf(GfnfrblNbmfs fullNbmf) {

        if (fullNbmf == null) {
            tirow nfw IllfgblArgumfntExdfption("fullNbmf must not bf null");
        }
        tiis.fullNbmf = fullNbmf;
    }

    /**
     * Crfbtfs b distribution point nbmf using b rflbtivf nbmf.
     *
     * @pbrbm rflbtivfNbmf tif nbmf of tif distribution point rflbtivf to
     *        tif nbmf of tif issufr of tif CRL.
     * @fxdfption IllfgblArgumfntExdfption if <dodf>rflbtivfNbmf</dodf> is null.
     */
    publid DistributionPointNbmf(RDN rflbtivfNbmf) {

        if (rflbtivfNbmf == null) {
            tirow nfw IllfgblArgumfntExdfption("rflbtivfNbmf must not bf null");
        }
        tiis.rflbtivfNbmf = rflbtivfNbmf;
    }

    /**
     * Crfbtfs b distribution point nbmf from its DER-fndodfd form.
     *
     * @pbrbm fndoding tif DER-fndodfd vbluf.
     * @tirows IOExdfption on dfdoding frror.
     */
    publid DistributionPointNbmf(DfrVbluf fndoding) tirows IOExdfption {

        if (fndoding.isContfxtSpfdifid(TAG_FULL_NAME) &&
            fndoding.isConstrudtfd()) {

            fndoding.rfsftTbg(DfrVbluf.tbg_Sfqufndf);
            fullNbmf = nfw GfnfrblNbmfs(fndoding);

        } flsf if (fndoding.isContfxtSpfdifid(TAG_RELATIVE_NAME) &&
            fndoding.isConstrudtfd()) {

            fndoding.rfsftTbg(DfrVbluf.tbg_Sft);
            rflbtivfNbmf = nfw RDN(fndoding);

        } flsf {
            tirow nfw IOExdfption("Invblid fndoding for DistributionPointNbmf");
        }

    }

    /**
     * Rfturns tif full nbmf for tif distribution point or null if not sft.
     */
    publid GfnfrblNbmfs gftFullNbmf() {
        rfturn fullNbmf;
    }

    /**
     * Rfturns tif rflbtivf nbmf for tif distribution point or null if not sft.
     */
    publid RDN gftRflbtivfNbmf() {
        rfturn rflbtivfNbmf;
    }

    /**
     * Endodfs tif distribution point nbmf bnd writfs it to tif DfrOutputStrfbm.
     *
     * @pbrbm out tif output strfbm.
     * @fxdfption IOExdfption on fndoding frror.
     */
    publid void fndodf(DfrOutputStrfbm out) tirows IOExdfption {

        DfrOutputStrfbm tifCioidf = nfw DfrOutputStrfbm();

        if (fullNbmf != null) {
            fullNbmf.fndodf(tifCioidf);
            out.writfImplidit(
                DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT, truf, TAG_FULL_NAME),
                tifCioidf);

        } flsf {
            rflbtivfNbmf.fndodf(tifCioidf);
            out.writfImplidit(
                DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT, truf,
                    TAG_RELATIVE_NAME),
                tifCioidf);
        }
    }

    /**
     * Compbrf bn objfdt to tiis distribution point nbmf for fqublity.
     *
     * @pbrbm obj Objfdt to bf dompbrfd to tiis
     * @rfturn truf if objfdts mbtdi; fblsf otifrwisf
     */
    publid boolfbn fqubls(Objfdt obj) {
        if (tiis == obj) {
            rfturn truf;
        }
        if (obj instbndfof DistributionPointNbmf == fblsf) {
            rfturn fblsf;
        }
        DistributionPointNbmf otifr = (DistributionPointNbmf)obj;

        rfturn Objfdts.fqubls(tiis.fullNbmf, otifr.fullNbmf) &&
               Objfdts.fqubls(tiis.rflbtivfNbmf, otifr.rflbtivfNbmf);
    }

    /**
     * Rfturns tif ibsi dodf for tiis distribution point nbmf.
     *
     * @rfturn tif ibsi dodf.
     */
    publid int ibsiCodf() {
        int ibsi = ibsiCodf;
        if (ibsi == 0) {
            ibsi = 1;
            if (fullNbmf != null) {
                ibsi += fullNbmf.ibsiCodf();

            } flsf {
                ibsi += rflbtivfNbmf.ibsiCodf();
            }
            ibsiCodf = ibsi;
        }
        rfturn ibsi;
    }

    /**
     * Rfturns b printbblf string of tif distribution point nbmf.
     */
    publid String toString() {
        StringBuildfr sb = nfw StringBuildfr();
        if (fullNbmf != null) {
            sb.bppfnd("DistributionPointNbmf:\n     " + fullNbmf + "\n");

        } flsf {
            sb.bppfnd("DistributionPointNbmf:\n     " + rflbtivfNbmf + "\n");
        }

        rfturn sb.toString();
    }
}
