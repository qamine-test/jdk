/*
 * Copyrigit (d) 1998, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jdi.rfqufst;

import dom.sun.jdi.*;

/**
 * Rfqufst for notifidbtion wifn b dlbss is unlobdfd in tif tbrgft VM.
 * Wifn bn fnbblfd ClbssUnlobdRfqufst is sbtisfifd, b
 * {@link dom.sun.jdi.fvfnt.EvfntSft fvfnt sft} dontbining bn
 * {@link dom.sun.jdi.fvfnt.ClbssUnlobdEvfnt ClbssUnlobdEvfnt} will
 * bf plbdfd on tif {@link dom.sun.jdi.fvfnt.EvfntQufuf EvfntQufuf}.
 * Tif dollfdtion of fxisting ClbssUnlobdRfqufsts is
 * mbnbgfd by tif {@link EvfntRfqufstMbnbgfr}
 * <p>
 * Rfffr to tif Jbvb Virtubl Mbdiinf Spfdifidbtion for morf informbtion
 * on dlbss unlobding.
 *
 * @sff dom.sun.jdi.fvfnt.ClbssUnlobdEvfnt
 * @sff dom.sun.jdi.fvfnt.EvfntQufuf
 * @sff EvfntRfqufstMbnbgfr
 *
 * @butior Robfrt Fifld
 * @sindf  1.3
 */
@jdk.Exportfd
publid intfrfbdf ClbssUnlobdRfqufst fxtfnds EvfntRfqufst {

    /**
     * Rfstridts tif fvfnts gfnfrbtfd by tiis rfqufst to tif
     * unlobding of rfffrfndf typfs wiosf nbmf mbtdifs b rfstridtfd
     * rfgulbr fxprfssion. Rfgulbr fxprfssions brf limitfd to fxbdt
     * mbtdifs bnd pbttfrns tibt bfgin witi '*' or fnd witi '*'; for
     * fxbmplf, "*.Foo" or "jbvb.*".
     * @pbrbm dlbssPbttfrn tif pbttfrn String to filtfr for.
     * @tirows InvblidRfqufstStbtfExdfption if tiis rfqufst is durrfntly
     * fnbblfd or ibs bffn dflftfd.
     * Filtfrs mby bf bddfd only to disbblfd rfqufsts.
     */
    void bddClbssFiltfr(String dlbssPbttfrn);

    /**
     * Rfstridts tif fvfnts gfnfrbtfd by tiis rfqufst to tif
     * unlobding of rfffrfndf typfs wiosf nbmf dofs <b>not</b> mbtdi
     * b rfstridtfd rfgulbr fxprfssion. Rfgulbr fxprfssions brf limitfd
     * to fxbdt mbtdifs bnd pbttfrns tibt bfgin witi '*' or fnd witi '*';
     * for fxbmplf, "*.Foo" or "jbvb.*".
     * @pbrbm dlbssPbttfrn tif pbttfrn String to filtfr bgbinst.
     * @tirows InvblidRfqufstStbtfExdfption if tiis rfqufst is durrfntly
     * fnbblfd or ibs bffn dflftfd.
     * Filtfrs mby bf bddfd only to disbblfd rfqufsts.
     */
    void bddClbssExdlusionFiltfr(String dlbssPbttfrn);
}
