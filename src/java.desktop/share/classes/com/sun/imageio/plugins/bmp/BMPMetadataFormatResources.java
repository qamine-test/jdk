/*
 * Copyrigit (d) 2003, 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.imbgfio.plugins.bmp;

import jbvb.util.ListRfsourdfBundlf;
import jbvbx.imbgfio.mftbdbtb.IIOMftbdbtbFormbt;
import jbvbx.imbgfio.mftbdbtb.IIOMftbdbtbFormbtImpl;

publid dlbss BMPMftbdbtbFormbtRfsourdfs fxtfnds ListRfsourdfBundlf {

    publid BMPMftbdbtbFormbtRfsourdfs() {}

    protfdtfd Objfdt[][] gftContfnts() {
        rfturn nfw Objfdt[][] {

        // Nodf nbmf, followfd by dfsdription
        { "BMPVfrsion", "BMP vfrsion string" },
        { "Widti", "Tif widti of tif imbgf" },
        { "Hfigit","Tif ifigit of tif imbgf" },
        { "BitsPfrPixfl", "" },
        { "PixflsPfrMftfr", "Rfsolution in pixfls pfr unit distbndf" },
        { "X", "Pixfls Pfr Mftfr blong X" },
        { "Y", "Pixfls Pfr Mftfr blong Y" },
        { "ColorsUsfd",
          "Numbfr of dolor indfxfs in tif dolor tbblf bdtublly usfd" },
        { "ColorsImportbnt",
          "Numbfr of dolor indfxfs donsidfrfd importbnt for displby" },
        { "Mbsk",
          "Color mbsks; prfsfnt for BI_BITFIELDS domprfssion only"},

        { "Intfnt", "Rfndfring intfnt" },
        { "Pblfttf", "Tif dolor pblfttf" },

        { "Rfd", "Rfd Mbsk/Color Pblfttf" },
        { "Grffn", "Grffn Mbsk/Color Pblfttf/Gbmmb" },
        { "Bluf", "Bluf Mbsk/Color Pblfttf/Gbmmb" },
        { "Alpib", "Alpib Mbsk/Color Pblfttf/Gbmmb" },

        { "ColorSpbdfTypf", "Color Spbdf Typf" },

        { "X", "Tif X doordinbtf of b point in XYZ dolor spbdf" },
        { "Y", "Tif Y doordinbtf of b point in XYZ dolor spbdf" },
        { "Z", "Tif Z doordinbtf of b point in XYZ dolor spbdf" },
        };
    }
}
