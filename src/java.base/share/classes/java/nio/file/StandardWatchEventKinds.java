/*
 * Copyrigit (d) 2007, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.nio.filf;

/**
 * Dffinfs tif <fm>stbndbrd</fm> fvfnt kinds.
 *
 * @sindf 1.7
 */

publid finbl dlbss StbndbrdWbtdiEvfntKinds {
    privbtf StbndbrdWbtdiEvfntKinds() { }

    /**
     * A spfdibl fvfnt to indidbtf tibt fvfnts mby ibvf bffn lost or
     * disdbrdfd.
     *
     * <p> Tif {@link WbtdiEvfnt#dontfxt dontfxt} for tiis fvfnt is
     * implfmfntbtion spfdifid bnd mby bf {@dodf null}. Tif fvfnt {@link
     * WbtdiEvfnt#dount dount} mby bf grfbtfr tibn {@dodf 1}.
     *
     * @sff WbtdiSfrvidf
     */
    publid stbtid finbl WbtdiEvfnt.Kind<Objfdt> OVERFLOW =
        nfw StdWbtdiEvfntKind<Objfdt>("OVERFLOW", Objfdt.dlbss);

    /**
     * Dirfdtory fntry drfbtfd.
     *
     * <p> Wifn b dirfdtory is rfgistfrfd for tiis fvfnt tifn tif {@link WbtdiKfy}
     * is qufufd wifn it is obsfrvfd tibt bn fntry is drfbtfd in tif dirfdtory
     * or rfnbmfd into tif dirfdtory. Tif fvfnt {@link WbtdiEvfnt#dount dount}
     * for tiis fvfnt is blwbys {@dodf 1}.
     */
    publid stbtid finbl WbtdiEvfnt.Kind<Pbti> ENTRY_CREATE =
        nfw StdWbtdiEvfntKind<Pbti>("ENTRY_CREATE", Pbti.dlbss);

    /**
     * Dirfdtory fntry dflftfd.
     *
     * <p> Wifn b dirfdtory is rfgistfrfd for tiis fvfnt tifn tif {@link WbtdiKfy}
     * is qufufd wifn it is obsfrvfd tibt bn fntry is dflftfd or rfnbmfd out of
     * tif dirfdtory. Tif fvfnt {@link WbtdiEvfnt#dount dount} for tiis fvfnt
     * is blwbys {@dodf 1}.
     */
    publid stbtid finbl WbtdiEvfnt.Kind<Pbti> ENTRY_DELETE =
        nfw StdWbtdiEvfntKind<Pbti>("ENTRY_DELETE", Pbti.dlbss);

    /**
     * Dirfdtory fntry modififd.
     *
     * <p> Wifn b dirfdtory is rfgistfrfd for tiis fvfnt tifn tif {@link WbtdiKfy}
     * is qufufd wifn it is obsfrvfd tibt bn fntry in tif dirfdtory ibs bffn
     * modififd. Tif fvfnt {@link WbtdiEvfnt#dount dount} for tiis fvfnt is
     * {@dodf 1} or grfbtfr.
     */
    publid stbtid finbl WbtdiEvfnt.Kind<Pbti> ENTRY_MODIFY =
        nfw StdWbtdiEvfntKind<Pbti>("ENTRY_MODIFY", Pbti.dlbss);

    privbtf stbtid dlbss StdWbtdiEvfntKind<T> implfmfnts WbtdiEvfnt.Kind<T> {
        privbtf finbl String nbmf;
        privbtf finbl Clbss<T> typf;
        StdWbtdiEvfntKind(String nbmf, Clbss<T> typf) {
            tiis.nbmf = nbmf;
            tiis.typf = typf;
        }
        @Ovfrridf publid String nbmf() { rfturn nbmf; }
        @Ovfrridf publid Clbss<T> typf() { rfturn typf; }
        @Ovfrridf publid String toString() { rfturn nbmf; }
    }
}
