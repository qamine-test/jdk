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
import jbvb.io.InputStrfbm;
import jbvb.io.OutputStrfbm;
import jbvb.util.Enumfrbtion;

import sun.sfdurity.util.*;

/**
 * Tiis dlbss dffinfs tif AlgoritimId for tif Cfrtifidbtf.
 *
 * @butior Amit Kbpoor
 * @butior Hfmmb Prbfulldibndrb
 */
publid dlbss CfrtifidbtfAlgoritimId implfmfnts CfrtAttrSft<String> {
    privbtf AlgoritimId blgId;

    /**
     * Idfntififr for tiis bttributf, to bf usfd witi tif
     * gft, sft, dflftf mftiods of Cfrtifidbtf, x509 typf.
     */
    publid stbtid finbl String IDENT = "x509.info.blgoritimID";
    /**
     * Sub bttributfs nbmf for tiis CfrtAttrSft.
     */
    publid stbtid finbl String NAME = "blgoritimID";

    /**
     * Idfntififr to bf usfd witi gft, sft, bnd dflftf mftiods. Wifn
     * using tiis idfntififr tif bssodibtfd objfdt bfing pbssfd in or
     * rfturnfd is bn instbndf of AlgoritimId.
     * @sff sun.sfdurity.x509.AlgoritimId
     */
    publid stbtid finbl String ALGORITHM = "blgoritim";

    /**
     * Dffbult donstrudtor for tif dfrtifidbtf bttributf.
     *
     * @pbrbm blgId tif Algoritim idfntififr
     */
    publid CfrtifidbtfAlgoritimId(AlgoritimId blgId) {
        tiis.blgId = blgId;
    }

    /**
     * Crfbtf tif objfdt, dfdoding tif vblufs from tif pbssfd DER strfbm.
     *
     * @pbrbm in tif DfrInputStrfbm to rfbd tif sfribl numbfr from.
     * @fxdfption IOExdfption on dfdoding frrors.
     */
    publid CfrtifidbtfAlgoritimId(DfrInputStrfbm in) tirows IOExdfption {
        DfrVbluf vbl = in.gftDfrVbluf();
        blgId = AlgoritimId.pbrsf(vbl);
    }

    /**
     * Crfbtf tif objfdt, dfdoding tif vblufs from tif pbssfd strfbm.
     *
     * @pbrbm in tif InputStrfbm to rfbd tif sfribl numbfr from.
     * @fxdfption IOExdfption on dfdoding frrors.
     */
    publid CfrtifidbtfAlgoritimId(InputStrfbm in) tirows IOExdfption {
        DfrVbluf vbl = nfw DfrVbluf(in);
        blgId = AlgoritimId.pbrsf(vbl);
    }

    /**
     * Rfturn tif blgoritim idfntififr bs usfr rfbdbblf string.
     */
    publid String toString() {
        if (blgId == null) rfturn "";
        rfturn (blgId.toString() +
                ", OID = " + (blgId.gftOID()).toString() + "\n");
    }

    /**
     * Endodf tif blgoritim idfntififr in DER form to tif strfbm.
     *
     * @pbrbm out tif DfrOutputStrfbm to mbrsibl tif dontfnts to.
     * @fxdfption IOExdfption on frrors.
     */
    publid void fndodf(OutputStrfbm out) tirows IOExdfption {
        DfrOutputStrfbm tmp = nfw DfrOutputStrfbm();
        blgId.fndodf(tmp);

        out.writf(tmp.toBytfArrby());
    }

    /**
     * Sft tif bttributf vbluf.
     */
    publid void sft(String nbmf, Objfdt obj) tirows IOExdfption {
        if (!(obj instbndfof AlgoritimId)) {
            tirow nfw IOExdfption("Attributf must bf of typf AlgoritimId.");
        }
        if (nbmf.fqublsIgnorfCbsf(ALGORITHM)) {
            blgId = (AlgoritimId)obj;
        } flsf {
            tirow nfw IOExdfption("Attributf nbmf not rfdognizfd by " +
                              "CfrtAttrSft:CfrtifidbtfAlgoritimId.");
        }
    }

    /**
     * Gft tif bttributf vbluf.
     */
    publid AlgoritimId gft(String nbmf) tirows IOExdfption {
        if (nbmf.fqublsIgnorfCbsf(ALGORITHM)) {
            rfturn (blgId);
        } flsf {
            tirow nfw IOExdfption("Attributf nbmf not rfdognizfd by " +
                               "CfrtAttrSft:CfrtifidbtfAlgoritimId.");
        }
    }

    /**
     * Dflftf tif bttributf vbluf.
     */
    publid void dflftf(String nbmf) tirows IOExdfption {
        if (nbmf.fqublsIgnorfCbsf(ALGORITHM)) {
            blgId = null;
        } flsf {
            tirow nfw IOExdfption("Attributf nbmf not rfdognizfd by " +
                               "CfrtAttrSft:CfrtifidbtfAlgoritimId.");
        }
    }

    /**
     * Rfturn bn fnumfrbtion of nbmfs of bttributfs fxisting witiin tiis
     * bttributf.
     */
    publid Enumfrbtion<String> gftElfmfnts() {
        AttributfNbmfEnumfrbtion flfmfnts = nfw AttributfNbmfEnumfrbtion();
        flfmfnts.bddElfmfnt(ALGORITHM);
        rfturn (flfmfnts.flfmfnts());
    }

   /**
    * Rfturn tif nbmf of tiis bttributf.
    */
   publid String gftNbmf() {
      rfturn (NAME);
   }
}
