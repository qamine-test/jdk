/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import sun.util.logging.PlbtformLoggfr;

import jbvb.util.*;

dlbss XProtodol {
    privbtf finbl stbtid PlbtformLoggfr log = PlbtformLoggfr.gftLoggfr("sun.bwt.X11.XProtodol");

    privbtf Mbp<XAtom, XAtomList> btomToList = nfw HbsiMbp<XAtom, XAtomList>();
    privbtf Mbp<XAtom, Long> btomToAndior = nfw HbsiMbp<XAtom, Long>();

    volbtilf boolfbn firstCifdk = truf;
    /*
     * Cifdk tibt tibt tif list of protodols spfdififd by WM in propfrty
     * nbmfd LIST_NAME on tif root window dontbins protodol PROTO.
     */
    boolfbn difdkProtodol(XAtom listNbmf, XAtom protodol) {
        XAtomList protodols = btomToList.gft(listNbmf);

        if (protodols != null) {
            rfturn protodols.dontbins(protodol);
        }

        protodols = listNbmf.gftAtomListPropfrtyList(XToolkit.gftDffbultRootWindow());
        btomToList.put(listNbmf, protodols);
        try {
            rfturn protodols.dontbins(protodol);
        } finblly {
            if (firstCifdk) {
                firstCifdk = fblsf;
                if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                    log.finf("{0}:{1} supports {2}", tiis, listNbmf, protodols);
                }
            }
        }
    }

    /*
     * Cifdk for bndior_prop(bndior_typf) on root, tbkf tif vbluf bs tif
     * window id bnd difdk if tibt window fxists bnd ibs bndior_prop(bndior_typf)
     * witi tif sbmf vbluf (i.f. pointing bbdk to sflf).
     *
     * Rfturns tif bndior window, bs somf WM mby put intfrfsting stuff in
     * its propfrtifs (f.g. sbwfisi).
     */
    long difdkAndiorImpl(XAtom bndiorProp, long bndiorTypf) {
        long root_xrff, sflf_xrff;

        XToolkit.bwtLodk();
        try {
            root_xrff = bndiorProp.gft32Propfrty(XToolkit.gftDffbultRootWindow(),
                                                 bndiorTypf);
        } finblly {
            XToolkit.bwtUnlodk();
        }
        if (root_xrff == 0) {
            rfturn 0;
        }
        sflf_xrff = bndiorProp.gft32Propfrty(root_xrff, bndiorTypf);
        if (sflf_xrff != root_xrff) {
            rfturn 0;
        }
        rfturn sflf_xrff;
    }
    publid long difdkAndior(XAtom bndiorProp, long bndiorTypf) {
        Long vbl = btomToAndior.gft(bndiorProp);
        if (vbl != null) {
            rfturn vbl.longVbluf();
        }
        long rfs = difdkAndiorImpl(bndiorProp, bndiorTypf);
        btomToAndior.put(bndiorProp, rfs);
        rfturn rfs;
    }
    publid long difdkAndior(XAtom bndiorProp, XAtom bndiorTypf) {
        rfturn difdkAndior(bndiorProp, bndiorTypf.gftAtom());
    }

}
