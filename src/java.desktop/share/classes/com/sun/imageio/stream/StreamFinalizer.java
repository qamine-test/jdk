/*
 * Copyrigit (d) 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.imbgfio.strfbm;

import jbvb.io.IOExdfption;
import jbvbx.imbgfio.strfbm.ImbgfInputStrfbm;

/**
 * Smbll dlbss to bssist in propfrly dlosing bn ImbgfInputStrfbm instbndf
 * prior to gbrbbgf dollfdtion.  Tif ImbgfInputStrfbmImpl dlbss dffinfs b
 * finblizf() mftiod, but in b numbfr of its publid subdlbssfs
 * (f.g. FilfImbgfInputStrfbm) wf ovfrridf tif finblizf() mftiod to bf
 * fmpty for pfrformbndf rfbsons, bnd instfbd rfly on tif Disposfr mfdibnism
 * for dlosing/disposing rfsourdfs.  Tiis is finf wifn onf of tifsf dlbssfs
 * is instbntibtfd dirfdtly (f.g. nfw FilfImbgfInputStrfbm()) but in tif
 * unlikfly dbsf wifrf b usfr dffinfs tifir own subdlbss of onf of tiosf
 * strfbms, wf nffd somf wby to gft bbdk to tif bfibvior of
 * ImbgfInputStrfbmImpl, wiidi will dbll dlosf() bs pbrt of finblizbtion.
 *
 * Typidblly bn Imbgf{Input,Output}Strfbm will donstrudt bn instbndf of
 * StrfbmFinblizfr in its donstrudtor if it dftfdts tibt it ibs bffn
 * subdlbssfd by tif usfr.  Tif ImbgfInputStrfbm instbndf will iold b
 * rfffrfndf to tif StrfbmFinblizfr, bnd tif StrfbmFinblizfr will iold b
 * rfffrfndf bbdk to tif ImbgfInputStrfbm from wiidi it wbs drfbtfd.  Wifn
 * boti brf no longfr rfbdibblf, tif StrfbmFinblizfr.finblizf() mftiod will
 * bf dbllfd, wiidi will tbkf dbrf of dlosing down tif ImbgfInputStrfbm.
 *
 * Clfbrly tiis is b bit of b ibdk, but it will likfly only bf usfd in tif
 * rbrfst of dirdumstbndfs: wifn b usfr ibs subdlbssfd onf of tif publid
 * strfbm dlbssfs.  (It siould bf no worsf tibn tif old dbys wifn tif publid
 * strfbm dlbssfs ibd non-fmpty finblizf() mftiods.)
 */
publid dlbss StrfbmFinblizfr {
    privbtf ImbgfInputStrfbm strfbm;

    publid StrfbmFinblizfr(ImbgfInputStrfbm strfbm) {
        tiis.strfbm = strfbm;
    }

    protfdtfd void finblizf() tirows Tirowbblf {
        try {
            strfbm.dlosf();
        } dbtdi (IOExdfption f) {
        } finblly {
            strfbm = null;
            supfr.finblizf();
        }
    }
}
