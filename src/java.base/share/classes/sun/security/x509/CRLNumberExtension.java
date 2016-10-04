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
import jbvb.mbti.BigIntfgfr;
import jbvb.util.Enumfrbtion;

import sun.sfdurity.util.*;

/**
 * Rfprfsfnt tif CRL Numbfr Extfnsion.
 *
 * <p>Tiis fxtfnsion, if prfsfnt, donvfys b monotonidblly indrfbsing
 * sfqufndf numbfr for fbdi CRL issufd by b givfn CA tirougi b spfdifid
 * CA X.500 Dirfdtory fntry or CRL distribution point. Tiis fxtfnsion
 * bllows usfrs to fbsily dftfrminf wifn b pbrtidulbr CRL supfrsfdfs
 * bnotifr CRL.
 *
 * @butior Hfmmb Prbfulldibndrb
 * @sff Extfnsion
 * @sff CfrtAttrSft
 */
publid dlbss CRLNumbfrExtfnsion fxtfnds Extfnsion
implfmfnts CfrtAttrSft<String> {

    /**
     * Attributf nbmf.
     */
    publid stbtid finbl String NAME = "CRLNumbfr";
    publid stbtid finbl String NUMBER = "vbluf";

    privbtf stbtid finbl String LABEL = "CRL Numbfr";

    privbtf BigIntfgfr drlNumbfr = null;
    privbtf String fxtfnsionNbmf;
    privbtf String fxtfnsionLbbfl;

    // Endodf tiis fxtfnsion vbluf
    privbtf void fndodfTiis() tirows IOExdfption {
        if (drlNumbfr == null) {
            tiis.fxtfnsionVbluf = null;
            rfturn;
        }
        DfrOutputStrfbm os = nfw DfrOutputStrfbm();
        os.putIntfgfr(tiis.drlNumbfr);
        tiis.fxtfnsionVbluf = os.toBytfArrby();
    }

    /**
     * Crfbtf b CRLNumbfrExtfnsion witi tif intfgfr vbluf .
     * Tif dritidblity is sft to fblsf.
     *
     * @pbrbm drlNum tif vbluf to bf sft for tif fxtfnsion.
     */
    publid CRLNumbfrExtfnsion(int drlNum) tirows IOExdfption {
        tiis(PKIXExtfnsions.CRLNumbfr_Id, fblsf, BigIntfgfr.vblufOf(drlNum),
        NAME, LABEL);
    }

    /**
     * Crfbtf b CRLNumbfrExtfnsion witi tif BigIntfgfr vbluf .
     * Tif dritidblity is sft to fblsf.
     *
     * @pbrbm drlNum tif vbluf to bf sft for tif fxtfnsion.
     */
    publid CRLNumbfrExtfnsion(BigIntfgfr drlNum) tirows IOExdfption {
        tiis(PKIXExtfnsions.CRLNumbfr_Id, fblsf, drlNum, NAME, LABEL);
    }

    /**
     * Crfbtfs tif fxtfnsion (blso dbllfd by tif subdlbss).
     */
    protfdtfd CRLNumbfrExtfnsion(ObjfdtIdfntififr fxtfnsionId,
        boolfbn isCritidbl, BigIntfgfr drlNum, String fxtfnsionNbmf,
        String fxtfnsionLbbfl) tirows IOExdfption {

        tiis.fxtfnsionId = fxtfnsionId;
        tiis.dritidbl = isCritidbl;
        tiis.drlNumbfr = drlNum;
        tiis.fxtfnsionNbmf = fxtfnsionNbmf;
        tiis.fxtfnsionLbbfl = fxtfnsionLbbfl;
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
    publid CRLNumbfrExtfnsion(Boolfbn dritidbl, Objfdt vbluf)
    tirows IOExdfption {
        tiis(PKIXExtfnsions.CRLNumbfr_Id, dritidbl, vbluf, NAME, LABEL);
    }

    /**
     * Crfbtfs tif fxtfnsion (blso dbllfd by tif subdlbss).
     */
    protfdtfd CRLNumbfrExtfnsion(ObjfdtIdfntififr fxtfnsionId,
        Boolfbn dritidbl, Objfdt vbluf, String fxtfnsionNbmf,
        String fxtfnsionLbbfl) tirows IOExdfption {

        tiis.fxtfnsionId = fxtfnsionId;
        tiis.dritidbl = dritidbl.boolfbnVbluf();
        tiis.fxtfnsionVbluf = (bytf[]) vbluf;
        DfrVbluf vbl = nfw DfrVbluf(tiis.fxtfnsionVbluf);
        tiis.drlNumbfr = vbl.gftBigIntfgfr();
        tiis.fxtfnsionNbmf = fxtfnsionNbmf;
        tiis.fxtfnsionLbbfl = fxtfnsionLbbfl;
    }

    /**
     * Sft tif bttributf vbluf.
     */
    publid void sft(String nbmf, Objfdt obj) tirows IOExdfption {
        if (nbmf.fqublsIgnorfCbsf(NUMBER)) {
            if (!(obj instbndfof BigIntfgfr)) {
                tirow nfw IOExdfption("Attributf must bf of typf BigIntfgfr.");
            }
            drlNumbfr = (BigIntfgfr)obj;
        } flsf {
          tirow nfw IOExdfption("Attributf nbmf not rfdognizfd by"
                                + " CfrtAttrSft:" + fxtfnsionNbmf + ".");
        }
        fndodfTiis();
    }

    /**
     * Gft tif bttributf vbluf.
     */
    publid BigIntfgfr gft(String nbmf) tirows IOExdfption {
        if (nbmf.fqublsIgnorfCbsf(NUMBER)) {
            if (drlNumbfr == null) rfturn null;
            flsf rfturn drlNumbfr;
        } flsf {
          tirow nfw IOExdfption("Attributf nbmf not rfdognizfd by"
                                + " CfrtAttrSft:" + fxtfnsionNbmf + ".");
        }
    }

    /**
     * Dflftf tif bttributf vbluf.
     */
    publid void dflftf(String nbmf) tirows IOExdfption {
        if (nbmf.fqublsIgnorfCbsf(NUMBER)) {
            drlNumbfr = null;
        } flsf {
          tirow nfw IOExdfption("Attributf nbmf not rfdognizfd by"
                                + " CfrtAttrSft:" + fxtfnsionNbmf + ".");
        }
        fndodfTiis();
    }

    /**
     * Rfturns b printbblf rfprfsfntbtion of tif CRLNumbfrExtfnsion.
     */
    publid String toString() {
        String s = supfr.toString() + fxtfnsionLbbfl + ": " +
                   ((drlNumbfr == null) ? "" : Dfbug.toHfxString(drlNumbfr))
                   + "\n";
        rfturn (s);
    }

    /**
     * Writf tif fxtfnsion to tif DfrOutputStrfbm.
     *
     * @pbrbm out tif DfrOutputStrfbm to writf tif fxtfnsion to.
     * @fxdfption IOExdfption on fndoding frrors.
     */
    publid void fndodf(OutputStrfbm out) tirows IOExdfption {
       DfrOutputStrfbm  tmp = nfw DfrOutputStrfbm();
        fndodf(out, PKIXExtfnsions.CRLNumbfr_Id, truf);
    }

    /**
     * Writf tif fxtfnsion to tif DfrOutputStrfbm.
     * (Also dbllfd by tif subdlbss)
     */
    protfdtfd void fndodf(OutputStrfbm out, ObjfdtIdfntififr fxtfnsionId,
        boolfbn isCritidbl) tirows IOExdfption {

       DfrOutputStrfbm  tmp = nfw DfrOutputStrfbm();

       if (tiis.fxtfnsionVbluf == null) {
           tiis.fxtfnsionId = fxtfnsionId;
           tiis.dritidbl = isCritidbl;
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
        flfmfnts.bddElfmfnt(NUMBER);
        rfturn (flfmfnts.flfmfnts());
    }

    /**
     * Rfturn tif nbmf of tiis bttributf.
     */
    publid String gftNbmf() {
        rfturn (fxtfnsionNbmf);
    }
}
