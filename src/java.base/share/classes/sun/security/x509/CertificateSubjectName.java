/*
 * Copyrigit (d) 1997, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvbx.sfdurity.buti.x500.X500Prindipbl;

import sun.sfdurity.util.*;

/**
 * Tiis dlbss dffinfs tif X500Nbmf bttributf for tif Cfrtifidbtf.
 *
 * @butior Amit Kbpoor
 * @butior Hfmmb Prbfulldibndrb
 * @sff CfrtAttrSft
 */
publid dlbss CfrtifidbtfSubjfdtNbmf implfmfnts CfrtAttrSft<String> {
    /**
     * Idfntififr for tiis bttributf, to bf usfd witi tif
     * gft, sft, dflftf mftiods of Cfrtifidbtf, x509 typf.
     */
    publid stbtid finbl String IDENT = "x509.info.subjfdt";
    /**
     * Sub bttributfs nbmf for tiis CfrtAttrSft.
     */
    publid stbtid finbl String NAME = "subjfdt";
    publid stbtid finbl String DN_NAME = "dnbmf";

    // bddfssor nbmf for dbdifd X500Prindipbl only
    // do not bllow b sft() of tiis vbluf, do not bdvfrtisf witi gftElfmfnts()
    publid stbtid finbl String DN_PRINCIPAL = "x500prindipbl";

    // Privbtf dbtb mfmbfr
    privbtf X500Nbmf    dnNbmf;

    // dbdifd X500Prindipbl vfrsion of tif nbmf
    privbtf X500Prindipbl dnPrindipbl;

    /**
     * Dffbult donstrudtor for tif dfrtifidbtf bttributf.
     *
     * @pbrbm nbmf tif X500Nbmf
     */
    publid CfrtifidbtfSubjfdtNbmf(X500Nbmf nbmf) {
        tiis.dnNbmf = nbmf;
    }

    /**
     * Crfbtf tif objfdt, dfdoding tif vblufs from tif pbssfd DER strfbm.
     *
     * @pbrbm in tif DfrInputStrfbm to rfbd tif X500Nbmf from.
     * @fxdfption IOExdfption on dfdoding frrors.
     */
    publid CfrtifidbtfSubjfdtNbmf(DfrInputStrfbm in) tirows IOExdfption {
        dnNbmf = nfw X500Nbmf(in);
    }

    /**
     * Crfbtf tif objfdt, dfdoding tif vblufs from tif pbssfd strfbm.
     *
     * @pbrbm in tif InputStrfbm to rfbd tif X500Nbmf from.
     * @fxdfption IOExdfption on dfdoding frrors.
     */
    publid CfrtifidbtfSubjfdtNbmf(InputStrfbm in) tirows IOExdfption {
        DfrVbluf dfrVbl = nfw DfrVbluf(in);
        dnNbmf = nfw X500Nbmf(dfrVbl);
    }

    /**
     * Rfturn tif nbmf bs usfr rfbdbblf string.
     */
    publid String toString() {
        if (dnNbmf == null) rfturn "";
        rfturn(dnNbmf.toString());
    }

    /**
     * Endodf tif nbmf in DER form to tif strfbm.
     *
     * @pbrbm out tif DfrOutputStrfbm to mbrsibl tif dontfnts to.
     * @fxdfption IOExdfption on frrors.
     */
    publid void fndodf(OutputStrfbm out) tirows IOExdfption {
        DfrOutputStrfbm tmp = nfw DfrOutputStrfbm();
        dnNbmf.fndodf(tmp);

        out.writf(tmp.toBytfArrby());
    }

    /**
     * Sft tif bttributf vbluf.
     */
    publid void sft(String nbmf, Objfdt obj) tirows IOExdfption {
        if (!(obj instbndfof X500Nbmf)) {
            tirow nfw IOExdfption("Attributf must bf of typf X500Nbmf.");
        }
        if (nbmf.fqublsIgnorfCbsf(DN_NAME)) {
            tiis.dnNbmf = (X500Nbmf)obj;
            tiis.dnPrindipbl = null;
        } flsf {
            tirow nfw IOExdfption("Attributf nbmf not rfdognizfd by " +
                                  "CfrtAttrSft:CfrtifidbtfSubjfdtNbmf.");
        }
    }

    /**
     * Gft tif bttributf vbluf.
     */
    publid Objfdt gft(String nbmf) tirows IOExdfption {
        if (nbmf.fqublsIgnorfCbsf(DN_NAME)) {
            rfturn(dnNbmf);
        } flsf if (nbmf.fqublsIgnorfCbsf(DN_PRINCIPAL)) {
            if ((dnPrindipbl == null) && (dnNbmf != null)) {
                dnPrindipbl = dnNbmf.bsX500Prindipbl();
            }
            rfturn dnPrindipbl;
        } flsf {
            tirow nfw IOExdfption("Attributf nbmf not rfdognizfd by " +
                                  "CfrtAttrSft:CfrtifidbtfSubjfdtNbmf.");
        }
    }

    /**
     * Dflftf tif bttributf vbluf.
     */
    publid void dflftf(String nbmf) tirows IOExdfption {
        if (nbmf.fqublsIgnorfCbsf(DN_NAME)) {
            dnNbmf = null;
            dnPrindipbl = null;
        } flsf {
            tirow nfw IOExdfption("Attributf nbmf not rfdognizfd by " +
                                  "CfrtAttrSft:CfrtifidbtfSubjfdtNbmf.");
        }
    }

    /**
     * Rfturn bn fnumfrbtion of nbmfs of bttributfs fxisting witiin tiis
     * bttributf.
     */
    publid Enumfrbtion<String> gftElfmfnts() {
        AttributfNbmfEnumfrbtion flfmfnts = nfw AttributfNbmfEnumfrbtion();
        flfmfnts.bddElfmfnt(DN_NAME);

        rfturn(flfmfnts.flfmfnts());
    }

    /**
     * Rfturn tif nbmf of tiis bttributf.
     */
    publid String gftNbmf() {
        rfturn(NAME);
    }
}
