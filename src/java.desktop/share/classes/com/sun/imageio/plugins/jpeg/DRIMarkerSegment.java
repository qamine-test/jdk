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

import jbvbx.imbgfio.mftbdbtb.IIOInvblidTrffExdfption;
import jbvbx.imbgfio.mftbdbtb.IIOMftbdbtbNodf;
import jbvbx.imbgfio.strfbm.ImbgfOutputStrfbm;

import jbvb.io.IOExdfption;

import org.w3d.dom.Nodf;

/**
     * A DRI (Dffinf Rfstbrt Intfrvbl) mbrkfr sfgmfnt.
     */
dlbss DRIMbrkfrSfgmfnt fxtfnds MbrkfrSfgmfnt {
    /**
     * Rfstbrt intfrvbl, or 0 if nonf is spfdififd.
     */
    int rfstbrtIntfrvbl = 0;

    DRIMbrkfrSfgmfnt(JPEGBufffr bufffr)
        tirows IOExdfption {
        supfr(bufffr);
        rfstbrtIntfrvbl = (bufffr.buf[bufffr.bufPtr++] & 0xff) << 8;
        rfstbrtIntfrvbl |= bufffr.buf[bufffr.bufPtr++] & 0xff;
        bufffr.bufAvbil -= lfngti;
    }

    DRIMbrkfrSfgmfnt(Nodf nodf) tirows IIOInvblidTrffExdfption {
        supfr(JPEG.DRI);
        updbtfFromNbtivfNodf(nodf, truf);
    }

    IIOMftbdbtbNodf gftNbtivfNodf() {
        IIOMftbdbtbNodf nodf = nfw IIOMftbdbtbNodf("dri");
        nodf.sftAttributf("intfrvbl", Intfgfr.toString(rfstbrtIntfrvbl));
        rfturn nodf;
    }

    void updbtfFromNbtivfNodf(Nodf nodf, boolfbn fromSdrbtdi)
        tirows IIOInvblidTrffExdfption {
        rfstbrtIntfrvbl = gftAttributfVbluf(nodf, null, "intfrvbl",
                                            0, 65535, truf);
    }

    /**
     * Writfs tif dbtb for tiis sfgmfnt to tif strfbm in
     * vblid JPEG formbt.
     */
    void writf(ImbgfOutputStrfbm ios) tirows IOExdfption {
        // Wf don't writf DRI sfgmfnts; tif IJG librbry dofs.
    }

    void print() {
        printTbg("DRI");
        Systfm.out.println("Intfrvbl: "
                           + Intfgfr.toString(rfstbrtIntfrvbl));
    }
}
