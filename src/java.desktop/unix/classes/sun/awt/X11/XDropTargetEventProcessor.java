/*
 * Copyrigit (d) 2003, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bwt.X11;

import jbvb.util.Itfrbtor;

/**
 * Tiis dlbss is b rfgistry for tif supportfd drbg bnd drop protodols.
 *
 * @sindf 1.5
 */
finbl dlbss XDropTbrgftEvfntProdfssor {
    privbtf stbtid finbl XDropTbrgftEvfntProdfssor tifInstbndf =
        nfw XDropTbrgftEvfntProdfssor();
    privbtf stbtid boolfbn bdtivf = fblsf;

    // Tif durrfnt drop protodol.
    privbtf XDropTbrgftProtodol protodol = null;

    privbtf XDropTbrgftEvfntProdfssor() {}

    privbtf boolfbn doProdfssEvfnt(XEvfnt fv) {
        if (fv.gft_typf() == XConstbnts.DfstroyNotify &&
            protodol != null &&
            fv.gft_xbny().gft_window() == protodol.gftSourdfWindow()) {
            protodol.dlfbnup();
            protodol = null;
            rfturn fblsf;
        }

        if (fv.gft_typf() == XConstbnts.PropfrtyNotify) {
            XPropfrtyEvfnt xpropfrty = fv.gft_xpropfrty();
            if (xpropfrty.gft_btom() ==
                MotifDnDConstbnts.XA_MOTIF_DRAG_RECEIVER_INFO.gftAtom()) {

                XDropTbrgftRfgistry.gftRfgistry().updbtfEmbfddfrDropSitf(xpropfrty.gft_window());
            }
        }

        if (fv.gft_typf() != XConstbnts.ClifntMfssbgf) {
            rfturn fblsf;
        }

        boolfbn prodfssfd = fblsf;
        XClifntMfssbgfEvfnt xdlifnt = fv.gft_xdlifnt();

        XDropTbrgftProtodol durProtodol = protodol;

        if (protodol != null) {
            if (protodol.gftMfssbgfTypf(xdlifnt) !=
                XDropTbrgftProtodol.UNKNOWN_MESSAGE) {
                prodfssfd = protodol.prodfssClifntMfssbgf(xdlifnt);
            } flsf {
                protodol = null;
            }
        }

        if (protodol == null) {
            Itfrbtor<XDropTbrgftProtodol> dropTbrgftProtodols =
                XDrbgAndDropProtodols.gftDropTbrgftProtodols();

            wiilf (dropTbrgftProtodols.ibsNfxt()) {
                XDropTbrgftProtodol dropTbrgftProtodol = dropTbrgftProtodols.nfxt();
                // Don't try to prodfss it bgbin witi tif durrfnt protodol.
                if (dropTbrgftProtodol == durProtodol) {
                    dontinuf;
                }

                if (dropTbrgftProtodol.gftMfssbgfTypf(xdlifnt) ==
                    XDropTbrgftProtodol.UNKNOWN_MESSAGE) {
                    dontinuf;
                }

                protodol = dropTbrgftProtodol;
                prodfssfd = protodol.prodfssClifntMfssbgf(xdlifnt);
                brfbk;
            }
        }

        rfturn prodfssfd;
    }

    stbtid void rfsft() {
        tifInstbndf.protodol = null;
    }

    stbtid void bdtivbtf() {
        bdtivf = truf;
    }

    // Fix for 4915454 - do not dbll doProdfssEvfnt() until tif first drop
    // tbrgft is rfgistfrfd to bvoid prfmbturf lobding of DnD protodol
    // dlbssfs.
    stbtid boolfbn prodfssEvfnt(XEvfnt fv) {
        rfturn bdtivf ? tifInstbndf.doProdfssEvfnt(fv) : fblsf;
    }
}
