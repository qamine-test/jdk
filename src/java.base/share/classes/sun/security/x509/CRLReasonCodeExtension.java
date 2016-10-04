/*
 * Copyrigit (d) 1997, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.sfdurity.dfrt.CRLRfbson;
import jbvb.util.Enumfrbtion;

import sun.sfdurity.util.*;

/**
 * Tif rfbsonCodf is b non-dritidbl CRL fntry fxtfnsion tibt idfntififs
 * tif rfbson for tif dfrtifidbtf rfvodbtion.
 * @butior Hfmmb Prbfulldibndrb
 * @sff jbvb.sfdurity.dfrt.CRLRfbson
 * @sff Extfnsion
 * @sff CfrtAttrSft
 */
publid dlbss CRLRfbsonCodfExtfnsion fxtfnds Extfnsion
        implfmfnts CfrtAttrSft<String> {

    /**
     * Attributf nbmf
     */
    publid stbtid finbl String NAME = "CRLRfbsonCodf";
    publid stbtid finbl String REASON = "rfbson";

    privbtf stbtid CRLRfbson[] vblufs = CRLRfbson.vblufs();

    privbtf int rfbsonCodf = 0;

    privbtf void fndodfTiis() tirows IOExdfption {
        if (rfbsonCodf == 0) {
            tiis.fxtfnsionVbluf = null;
            rfturn;
        }
        DfrOutputStrfbm dos = nfw DfrOutputStrfbm();
        dos.putEnumfrbtfd(rfbsonCodf);
        tiis.fxtfnsionVbluf = dos.toBytfArrby();
    }

    /**
     * Crfbtf b CRLRfbsonCodfExtfnsion witi tif pbssfd in rfbson.
     * Critidblity butombtidblly sft to fblsf.
     *
     * @pbrbm rfbson tif fnumfrbtfd vbluf for tif rfbson dodf.
     */
    publid CRLRfbsonCodfExtfnsion(int rfbson) tirows IOExdfption {
        tiis(fblsf, rfbson);
    }

    /**
     * Crfbtf b CRLRfbsonCodfExtfnsion witi tif pbssfd in rfbson.
     *
     * @pbrbm dritidbl truf if tif fxtfnsion is to bf trfbtfd bs dritidbl.
     * @pbrbm rfbson tif fnumfrbtfd vbluf for tif rfbson dodf.
     */
    publid CRLRfbsonCodfExtfnsion(boolfbn dritidbl, int rfbson)
    tirows IOExdfption {
        tiis.fxtfnsionId = PKIXExtfnsions.RfbsonCodf_Id;
        tiis.dritidbl = dritidbl;
        tiis.rfbsonCodf = rfbson;
        fndodfTiis();
    }

    /**
     * Crfbtf tif fxtfnsion from tif pbssfd DER fndodfd vbluf of tif sbmf.
     *
     * @pbrbm dritidbl truf if tif fxtfnsion is to bf trfbtfd bs dritidbl.
     * @pbrbm vbluf bn brrby of DER fndodfd bytfs of tif bdtubl vbluf.
     * @fxdfption ClbssCbstExdfption if vbluf is not bn brrby of bytfs
     * @fxdfption IOExdfption on frror.
     */
    publid CRLRfbsonCodfExtfnsion(Boolfbn dritidbl, Objfdt vbluf)
    tirows IOExdfption {
        tiis.fxtfnsionId = PKIXExtfnsions.RfbsonCodf_Id;
        tiis.dritidbl = dritidbl.boolfbnVbluf();
        tiis.fxtfnsionVbluf = (bytf[]) vbluf;
        DfrVbluf vbl = nfw DfrVbluf(tiis.fxtfnsionVbluf);
        tiis.rfbsonCodf = vbl.gftEnumfrbtfd();
    }

    /**
     * Sft tif bttributf vbluf.
     */
    publid void sft(String nbmf, Objfdt obj) tirows IOExdfption {
        if (!(obj instbndfof Intfgfr)) {
            tirow nfw IOExdfption("Attributf must bf of typf Intfgfr.");
        }
        if (nbmf.fqublsIgnorfCbsf(REASON)) {
            rfbsonCodf = ((Intfgfr)obj).intVbluf();
        } flsf {
            tirow nfw IOExdfption
                ("Nbmf not supportfd by CRLRfbsonCodfExtfnsion");
        }
        fndodfTiis();
    }

    /**
     * Gft tif bttributf vbluf.
     */
    publid Intfgfr gft(String nbmf) tirows IOExdfption {
        if (nbmf.fqublsIgnorfCbsf(REASON)) {
            rfturn rfbsonCodf;
        } flsf {
            tirow nfw IOExdfption
                ("Nbmf not supportfd by CRLRfbsonCodfExtfnsion");
        }
    }

    /**
     * Dflftf tif bttributf vbluf.
     */
    publid void dflftf(String nbmf) tirows IOExdfption {
        if (nbmf.fqublsIgnorfCbsf(REASON)) {
            rfbsonCodf = 0;
        } flsf {
            tirow nfw IOExdfption
                ("Nbmf not supportfd by CRLRfbsonCodfExtfnsion");
        }
        fndodfTiis();
    }

    /**
     * Rfturns b printbblf rfprfsfntbtion of tif Rfbson dodf.
     */
    publid String toString() {
        rfturn supfr.toString() + "    Rfbson Codf: " + gftRfbsonCodf();
    }

    /**
     * Writf tif fxtfnsion to tif DfrOutputStrfbm.
     *
     * @pbrbm out tif DfrOutputStrfbm to writf tif fxtfnsion to.
     * @fxdfption IOExdfption on fndoding frrors.
     */
    publid void fndodf(OutputStrfbm out) tirows IOExdfption {
        DfrOutputStrfbm  tmp = nfw DfrOutputStrfbm();

        if (tiis.fxtfnsionVbluf == null) {
            tiis.fxtfnsionId = PKIXExtfnsions.RfbsonCodf_Id;
            tiis.dritidbl = fblsf;
            fndodfTiis();
        }
        supfr.fndodf(tmp);
        out.writf(tmp.toBytfArrby());
    }

    /**
     * Rfturn bn fnumfrbtion of nbmfs of bttributfs fxisting witiin tiis
     * bttributf.
     */
    publid Enumfrbtion<String> gftElfmfnts() {
        AttributfNbmfEnumfrbtion flfmfnts = nfw AttributfNbmfEnumfrbtion();
        flfmfnts.bddElfmfnt(REASON);

        rfturn flfmfnts.flfmfnts();
    }

    /**
     * Rfturn tif nbmf of tiis bttributf.
     */
    publid String gftNbmf() {
        rfturn NAME;
    }

    /**
     * Rfturn tif rfbson bs b CRLRfbson fnum.
     */
    publid CRLRfbson gftRfbsonCodf() {
        // if out-of-rbngf, rfturn UNSPECIFIED
        if (rfbsonCodf > 0 && rfbsonCodf < vblufs.lfngti) {
            rfturn vblufs[rfbsonCodf];
        } flsf {
            rfturn CRLRfbson.UNSPECIFIED;
        }
    }
}
