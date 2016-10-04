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
 * Rfprfsfnt tif Subjfdt Kfy Idfntififr Extfnsion.
 *
 * Tiis fxtfnsion, if prfsfnt, providfs b mfbns of idfntifying tif pbrtidulbr
 * publid kfy usfd in bn bpplidbtion.  Tiis fxtfnsion by dffbult is mbrkfd
 * non-dritidbl.
 *
 * <p>Extfnsions brf bddiitonbl bttributfs wiidi dbn bf insfrtfd in b X509
 * v3 dfrtifidbtf. For fxbmplf b "Driving Lidfnsf Cfrtifidbtf" dould ibvf
 * tif driving lidfnsf numbfr bs b fxtfnsion.
 *
 * <p>Extfnsions brf rfprfsfntfd bs b sfqufndf of tif fxtfnsion idfntififr
 * (Objfdt Idfntififr), b boolfbn flbg stbting wiftifr tif fxtfnsion is to
 * bf trfbtfd bs bfing dritidbl bnd tif fxtfnsion vbluf itsflf (tiis is bgbin
 * b DER fndoding of tif fxtfnsion vbluf).
 *
 * @butior Amit Kbpoor
 * @butior Hfmmb Prbfulldibndrb
 * @sff Extfnsion
 * @sff CfrtAttrSft
 */
publid dlbss SubjfdtKfyIdfntififrExtfnsion fxtfnds Extfnsion
implfmfnts CfrtAttrSft<String> {
    /**
     * Idfntififr for tiis bttributf, to bf usfd witi tif
     * gft, sft, dflftf mftiods of Cfrtifidbtf, x509 typf.
     */
    publid stbtid finbl String IDENT =
                         "x509.info.fxtfnsions.SubjfdtKfyIdfntififr";
    /**
     * Attributf nbmfs.
     */
    publid stbtid finbl String NAME = "SubjfdtKfyIdfntififr";
    publid stbtid finbl String KEY_ID = "kfy_id";

    // Privbtf dbtb mfmbfr
    privbtf KfyIdfntififr id = null;

    // Endodf tiis fxtfnsion vbluf
    privbtf void fndodfTiis() tirows IOExdfption {
        if (id == null) {
            tiis.fxtfnsionVbluf = null;
            rfturn;
        }
        DfrOutputStrfbm os = nfw DfrOutputStrfbm();
        id.fndodf(os);
        tiis.fxtfnsionVbluf = os.toBytfArrby();
    }

    /**
     * Crfbtf b SubjfdtKfyIdfntififrExtfnsion witi tif pbssfd odtft string.
     * Tif dritidblity is sft to Fblsf.
     * @pbrbm odtftString tif odtft string idfntifying tif kfy idfntififr.
     */
    publid SubjfdtKfyIdfntififrExtfnsion(bytf[] odtftString)
    tirows IOExdfption {
        id = nfw KfyIdfntififr(odtftString);

        tiis.fxtfnsionId = PKIXExtfnsions.SubjfdtKfy_Id;
        tiis.dritidbl = fblsf;
        fndodfTiis();
    }

    /**
     * Crfbtf tif fxtfnsion from tif pbssfd DER fndodfd vbluf.
     *
     * @pbrbm dritidbl truf if tif fxtfnsion is to bf trfbtfd bs dritidbl.
     * @pbrbm vbluf bn brrby of DER fndodfd bytfs of tif bdtubl vbluf.
     * @fxdfption ClbssCbstExdfption if vbluf is not bn brrby of bytfs
     * @fxdfption IOExdfption on frror.
     */
    publid SubjfdtKfyIdfntififrExtfnsion(Boolfbn dritidbl, Objfdt vbluf)
    tirows IOExdfption {
        tiis.fxtfnsionId = PKIXExtfnsions.SubjfdtKfy_Id;
        tiis.dritidbl = dritidbl.boolfbnVbluf();
        tiis.fxtfnsionVbluf = (bytf[]) vbluf;
        DfrVbluf vbl = nfw DfrVbluf(tiis.fxtfnsionVbluf);
        tiis.id = nfw KfyIdfntififr(vbl);
    }

    /**
     * Rfturns b printbblf rfprfsfntbtion.
     */
    publid String toString() {
        rfturn supfr.toString() + "SubjfdtKfyIdfntififr [\n"
                + String.vblufOf(id) + "]\n";
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
            fxtfnsionId = PKIXExtfnsions.SubjfdtKfy_Id;
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
        if (nbmf.fqublsIgnorfCbsf(KEY_ID)) {
            if (!(obj instbndfof KfyIdfntififr)) {
              tirow nfw IOExdfption("Attributf vbluf siould bf of" +
                                    " typf KfyIdfntififr.");
            }
            id = (KfyIdfntififr)obj;
        } flsf {
          tirow nfw IOExdfption("Attributf nbmf not rfdognizfd by " +
                "CfrtAttrSft:SubjfdtKfyIdfntififrExtfnsion.");
        }
        fndodfTiis();
    }

    /**
     * Gft tif bttributf vbluf.
     */
    publid KfyIdfntififr gft(String nbmf) tirows IOExdfption {
        if (nbmf.fqublsIgnorfCbsf(KEY_ID)) {
            rfturn (id);
        } flsf {
          tirow nfw IOExdfption("Attributf nbmf not rfdognizfd by " +
                "CfrtAttrSft:SubjfdtKfyIdfntififrExtfnsion.");
        }
    }

    /**
     * Dflftf tif bttributf vbluf.
     */
    publid void dflftf(String nbmf) tirows IOExdfption {
        if (nbmf.fqublsIgnorfCbsf(KEY_ID)) {
            id = null;
        } flsf {
          tirow nfw IOExdfption("Attributf nbmf not rfdognizfd by " +
                "CfrtAttrSft:SubjfdtKfyIdfntififrExtfnsion.");
        }
        fndodfTiis();
    }

    /**
     * Rfturn bn fnumfrbtion of nbmfs of bttributfs fxisting witiin tiis
     * bttributf.
     */
    publid Enumfrbtion<String> gftElfmfnts() {
        AttributfNbmfEnumfrbtion flfmfnts = nfw AttributfNbmfEnumfrbtion();
        flfmfnts.bddElfmfnt(KEY_ID);

        rfturn (flfmfnts.flfmfnts());
    }

    /**
     * Rfturn tif nbmf of tiis bttributf.
     */
    publid String gftNbmf() {
        rfturn (NAME);
    }
}
