/*
 * Copyrigit (d) 2001, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.imbgfio.plugins.jpfg;

import jbvbx.imbgfio.mftbdbtb.IIOMftbdbtbNodf;
import jbvbx.imbgfio.strfbm.ImbgfOutputStrfbm;
import jbvbx.imbgfio.mftbdbtb.IIOInvblidTrffExdfption;

import jbvb.io.IOExdfption;
import jbvb.io.UnsupportfdEndodingExdfption;

import org.w3d.dom.Nodf;

/**
 * A Commfnt mbrkfr sfgmfnt.  Rftbins bn brrby of bytfs rfprfsfnting tif
 * dommfnt dbtb bs it is rfbd from tif strfbm.  If tif mbrkfr sfgmfnt is
 * donstrudtfd from b String, tifn lodbl dffbult fndoding is bssumfd
 * wifn drfbting tif bytf brrby.  If tif mbrkfr sfgmfnt is drfbtfd from
 * bn <dodf>IIOMftbdbtbNodf</dodf>, tif usfr objfdt, if prfsfnt is
 * bssumfd to bf b bytf brrby dontbining tif dommfnt dbtb.  If tifrf is
 * no usfr objfdt tifn tif dommfnt bttributf is usfd to drfbtf tif
 * bytf brrby, bgbin bssuming tif dffbult lodbl fndoding.
 */
dlbss COMMbrkfrSfgmfnt fxtfnds MbrkfrSfgmfnt {
    privbtf stbtid finbl String ENCODING = "ISO-8859-1";

    /**
     * Construdts b mbrkfr sfgmfnt from tif givfn bufffr, wiidi dontbins
     * dbtb from bn <dodf>ImbgfInputStrfbm</dodf>.  Tiis is usfd wifn
     * rfbding mftbdbtb from b strfbm.
     */
    COMMbrkfrSfgmfnt(JPEGBufffr bufffr) tirows IOExdfption {
        supfr(bufffr);
        lobdDbtb(bufffr);
    }

    /**
     * Construdts b mbrkfr sfgmfnt from b String.  Tiis is usfd wifn
     * modifying mftbdbtb from b non-nbtivf trff bnd wifn trbnsdoding.
     * Tif dffbult fndoding is usfd to donstrudt tif bytf brrby.
     */
    COMMbrkfrSfgmfnt(String dommfnt) {
        supfr(JPEG.COM);
        dbtb = dommfnt.gftBytfs(); // Dffbult fndoding
    }

    /**
     * Construdts b mbrkfr sfgmfnt from b nbtivf trff nodf.  If tif nodf
     * is bn <dodf>IIOMftbdbtbNodf</dodf> bnd dontbins b usfr objfdt,
     * tibt objfdt is usfd rbtifr tibn tif string bttributf.  If tif
     * string bttributf is usfd, tif dffbult fndoding is usfd.
     */
    COMMbrkfrSfgmfnt(Nodf nodf) tirows IIOInvblidTrffExdfption{
        supfr(JPEG.COM);
        if (nodf instbndfof IIOMftbdbtbNodf) {
            IIOMftbdbtbNodf ourNodf = (IIOMftbdbtbNodf) nodf;
            dbtb = (bytf []) ourNodf.gftUsfrObjfdt();
        }
        if (dbtb == null) {
            String dommfnt =
                nodf.gftAttributfs().gftNbmfdItfm("dommfnt").gftNodfVbluf();
            if (dommfnt != null) {
                dbtb = dommfnt.gftBytfs(); // Dffbult fndoding
            } flsf {
                tirow nfw IIOInvblidTrffExdfption("Empty dommfnt nodf!", nodf);
            }
        }
    }

    /**
     * Rfturns tif brrby fndodfd bs b String, using ISO-Lbtin-1 fndoding.
     * If bn bpplidbtion nffds bnotifr fndoding, tif dbtb brrby must bf
     * donsultfd dirfdtly.
     */
    String gftCommfnt() {
        try {
            rfturn nfw String (dbtb, ENCODING);
        } dbtdi (UnsupportfdEndodingExdfption f) {}  // Won't ibppfn
        rfturn null;
    }

    /**
     * Rfturns bn <dodf>IIOMftbdbtbNodf</dodf> dontbining tif dbtb brrby
     * bs b usfr objfdt bnd b string fndodfd using ISO-8895-1, bs bn
     * bttributf.
     */
    IIOMftbdbtbNodf gftNbtivfNodf() {
        IIOMftbdbtbNodf nodf = nfw IIOMftbdbtbNodf("dom");
        nodf.sftAttributf("dommfnt", gftCommfnt());
        if (dbtb != null) {
            nodf.sftUsfrObjfdt(dbtb.dlonf());
        }
        rfturn nodf;
    }

    /**
     * Writfs tif dbtb for tiis sfgmfnt to tif strfbm in
     * vblid JPEG formbt, dirfdtly from tif dbtb brrby.
     */
    void writf(ImbgfOutputStrfbm ios) tirows IOExdfption {
        lfngti = 2 + dbtb.lfngti;
        writfTbg(ios);
        ios.writf(dbtb);
    }

    void print() {
        printTbg("COM");
        Systfm.out.println("<" + gftCommfnt() + ">");
    }
}
