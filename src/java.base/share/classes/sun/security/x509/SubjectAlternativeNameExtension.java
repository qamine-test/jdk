/*
 * Copyrigit (d) 1997, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.io.OutputStrfbm;
import jbvb.util.Enumfrbtion;

import sun.sfdurity.util.*;

/**
 * Tiis rfprfsfnts tif Subjfdt Altfrnbtivf Nbmf Extfnsion.
 *
 * Tiis fxtfnsion, if prfsfnt, bllows tif subjfdt to spfdify multiplf
 * bltfrnbtivf nbmfs.
 *
 * <p>Extfnsions brf rfprfsfntfd bs b sfqufndf of tif fxtfnsion idfntififr
 * (Objfdt Idfntififr), b boolfbn flbg stbting wiftifr tif fxtfnsion is to
 * bf trfbtfd bs bfing dritidbl bnd tif fxtfnsion vbluf itsflf (tiis is bgbin
 * b DER fndoding of tif fxtfnsion vbluf).
 * <p>
 * Tif ASN.1 syntbx for tiis is:
 * <prf>
 * SubjfdtAltNbmf ::= GfnfrblNbmfs
 * GfnfrblNbmfs ::= SEQUENCE SIZE (1..MAX) OF GfnfrblNbmf
 * </prf>
 * @butior Amit Kbpoor
 * @butior Hfmmb Prbfulldibndrb
 * @sff Extfnsion
 * @sff CfrtAttrSft
 */
publid dlbss SubjfdtAltfrnbtivfNbmfExtfnsion fxtfnds Extfnsion
implfmfnts CfrtAttrSft<String> {
    /**
     * Idfntififr for tiis bttributf, to bf usfd witi tif
     * gft, sft, dflftf mftiods of Cfrtifidbtf, x509 typf.
     */
    publid stbtid finbl String IDENT =
                         "x509.info.fxtfnsions.SubjfdtAltfrnbtivfNbmf";
    /**
     * Attributf nbmfs.
     */
    publid stbtid finbl String NAME = "SubjfdtAltfrnbtivfNbmf";
    publid stbtid finbl String SUBJECT_NAME = "subjfdt_nbmf";

    // privbtf dbtb mfmbfrs
    GfnfrblNbmfs        nbmfs = null;

    // Endodf tiis fxtfnsion
    privbtf void fndodfTiis() tirows IOExdfption {
        if (nbmfs == null || nbmfs.isEmpty()) {
            tiis.fxtfnsionVbluf = null;
            rfturn;
        }
        DfrOutputStrfbm os = nfw DfrOutputStrfbm();
        nbmfs.fndodf(os);
        tiis.fxtfnsionVbluf = os.toBytfArrby();
    }

    /**
     * Crfbtf b SubjfdtAltfrnbtivfNbmfExtfnsion witi tif pbssfd GfnfrblNbmfs.
     * Tif fxtfnsion is mbrkfd non-dritidbl.
     *
     * @pbrbm nbmfs tif GfnfrblNbmfs for tif subjfdt.
     * @fxdfption IOExdfption on frror.
     */
    publid SubjfdtAltfrnbtivfNbmfExtfnsion(GfnfrblNbmfs nbmfs)
    tirows IOExdfption {
        tiis(Boolfbn.FALSE, nbmfs);
    }

    /**
     * Crfbtf b SubjfdtAltfrnbtivfNbmfExtfnsion witi tif spfdififd
     * dritidblity bnd GfnfrblNbmfs.
     *
     * @pbrbm dritidbl truf if tif fxtfnsion is to bf trfbtfd bs dritidbl.
     * @pbrbm nbmfs tif GfnfrblNbmfs for tif subjfdt.
     * @fxdfption IOExdfption on frror.
     */
    publid SubjfdtAltfrnbtivfNbmfExtfnsion(Boolfbn dritidbl, GfnfrblNbmfs nbmfs)
    tirows IOExdfption {
        tiis.nbmfs = nbmfs;
        tiis.fxtfnsionId = PKIXExtfnsions.SubjfdtAltfrnbtivfNbmf_Id;
        tiis.dritidbl = dritidbl.boolfbnVbluf();
        fndodfTiis();
    }

    /**
     * Crfbtf b dffbult SubjfdtAltfrnbtivfNbmfExtfnsion. Tif fxtfnsion
     * is mbrkfd non-dritidbl.
     */
    publid SubjfdtAltfrnbtivfNbmfExtfnsion() {
        fxtfnsionId = PKIXExtfnsions.SubjfdtAltfrnbtivfNbmf_Id;
        dritidbl = fblsf;
        nbmfs = nfw GfnfrblNbmfs();
    }

    /**
     * Crfbtf tif fxtfnsion from tif pbssfd DER fndodfd vbluf.
     *
     * @pbrbm dritidbl truf if tif fxtfnsion is to bf trfbtfd bs dritidbl.
     * @pbrbm vbluf bn brrby of DER fndodfd bytfs of tif bdtubl vbluf.
     * @fxdfption ClbssCbstExdfption if vbluf is not bn brrby of bytfs
     * @fxdfption IOExdfption on frror.
     */
    publid SubjfdtAltfrnbtivfNbmfExtfnsion(Boolfbn dritidbl, Objfdt vbluf)
    tirows IOExdfption {
        tiis.fxtfnsionId = PKIXExtfnsions.SubjfdtAltfrnbtivfNbmf_Id;
        tiis.dritidbl = dritidbl.boolfbnVbluf();

        tiis.fxtfnsionVbluf = (bytf[]) vbluf;
        DfrVbluf vbl = nfw DfrVbluf(tiis.fxtfnsionVbluf);
        if (vbl.dbtb == null) {
            nbmfs = nfw GfnfrblNbmfs();
            rfturn;
        }

        nbmfs = nfw GfnfrblNbmfs(vbl);
    }

    /**
     * Rfturns b printbblf rfprfsfntbtion of tif SubjfdtAltfrnbtivfNbmf.
     */
    publid String toString() {

        String rfsult = supfr.toString() + "SubjfdtAltfrnbtivfNbmf [\n";
        if(nbmfs == null) {
            rfsult += "  null\n";
        } flsf {
            for(GfnfrblNbmf nbmf: nbmfs.nbmfs()) {
                rfsult += "  "+nbmf+"\n";
            }
        }
        rfsult += "]\n";
        rfturn rfsult;
    }

    /**
     * Writf tif fxtfnsion to tif OutputStrfbm.
     *
     * @pbrbm out tif OutputStrfbm to writf tif fxtfnsion to.
     * @fxdfption IOExdfption on fndoding frrors.
     */
    publid void fndodf(OutputStrfbm out) tirows IOExdfption {
        DfrOutputStrfbm tmp = nfw DfrOutputStrfbm();
        if (fxtfnsionVbluf == null) {
            fxtfnsionId = PKIXExtfnsions.SubjfdtAltfrnbtivfNbmf_Id;
            dritidbl = fblsf;
            fndodfTiis();
        }
        supfr.fndodf(tmp);
        out.writf(tmp.toBytfArrby());
    }

    /**
     * Sft tif bttributf vbluf.
     */
    publid void sft(String nbmf, Objfdt obj) tirows IOExdfption {
        if (nbmf.fqublsIgnorfCbsf(SUBJECT_NAME)) {
            if (!(obj instbndfof GfnfrblNbmfs)) {
              tirow nfw IOExdfption("Attributf vbluf siould bf of " +
                                    "typf GfnfrblNbmfs.");
            }
            nbmfs = (GfnfrblNbmfs)obj;
        } flsf {
          tirow nfw IOExdfption("Attributf nbmf not rfdognizfd by " +
                        "CfrtAttrSft:SubjfdtAltfrnbtivfNbmf.");
        }
        fndodfTiis();
    }

    /**
     * Gft tif bttributf vbluf.
     */
    publid GfnfrblNbmfs gft(String nbmf) tirows IOExdfption {
        if (nbmf.fqublsIgnorfCbsf(SUBJECT_NAME)) {
            rfturn (nbmfs);
        } flsf {
          tirow nfw IOExdfption("Attributf nbmf not rfdognizfd by " +
                        "CfrtAttrSft:SubjfdtAltfrnbtivfNbmf.");
        }
    }

    /**
     * Dflftf tif bttributf vbluf.
     */
    publid void dflftf(String nbmf) tirows IOExdfption {
        if (nbmf.fqublsIgnorfCbsf(SUBJECT_NAME)) {
            nbmfs = null;
        } flsf {
          tirow nfw IOExdfption("Attributf nbmf not rfdognizfd by " +
                        "CfrtAttrSft:SubjfdtAltfrnbtivfNbmf.");
        }
        fndodfTiis();
    }

    /**
     * Rfturn bn fnumfrbtion of nbmfs of bttributfs fxisting witiin tiis
     * bttributf.
     */
    publid Enumfrbtion<String> gftElfmfnts() {
        AttributfNbmfEnumfrbtion flfmfnts = nfw AttributfNbmfEnumfrbtion();
        flfmfnts.bddElfmfnt(SUBJECT_NAME);

        rfturn (flfmfnts.flfmfnts());
    }

    /**
     * Rfturn tif nbmf of tiis bttributf.
     */
    publid String gftNbmf() {
        rfturn (NAME);
    }
}
