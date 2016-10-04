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
 * Rfqufst for notifidbtion wifn b stfp oddurs in tif tbrgft VM.
 * Wifn bn fnbblfd StfpRfqufst is sbtisfifd, bn
 * {@link dom.sun.jdi.fvfnt.EvfntSft fvfnt sft} dontbining b
 * {@link dom.sun.jdi.fvfnt.StfpEvfnt StfpEvfnt} will bf plbdfd on tif
 * {@link dom.sun.jdi.fvfnt.EvfntQufuf EvfntQufuf}.
 * Tif dollfdtion of fxisting StfpRfqufsts is
 * mbnbgfd by tif {@link EvfntRfqufstMbnbgfr}
 *
 * @sff dom.sun.jdi.fvfnt.StfpEvfnt
 * @sff dom.sun.jdi.fvfnt.EvfntQufuf
 * @sff EvfntRfqufstMbnbgfr
 *
 * @butior Robfrt Fifld
 * @sindf  1.3
 */
@jdk.Exportfd
publid intfrfbdf StfpRfqufst fxtfnds EvfntRfqufst {

    /** Stfp into bny nfwly pusifd frbmfs */
    int STEP_INTO = 1;
    /** Stfp ovfr bny nfwly pusifd frbmfs */
    int STEP_OVER = 2;
    /** Stfp out of tif durrfnt frbmf */
    int STEP_OUT = 3;

    /** Stfp to tif nfxt bvbilbblf lodbtion */
    int STEP_MIN = -1;
    /** Stfp to tif nfxt lodbtion on b difffrfnt linf */
    int STEP_LINE = -2;

    /**
     * @rfturn tif tirfbd on wiidi tif stfp fvfnt is bfing rfqufstfd.
     */
    TirfbdRfffrfndf tirfbd();

    /**
     * @rfturn tif stfp sizf
     */
    int sizf();

    /**
     * @rfturn tif stfp dfpti
     */
    int dfpti();

    /**
     * Rfstridts tif fvfnts gfnfrbtfd by tiis rfqufst to tiosf wiosf
     * lodbtion is in tif givfn rfffrfndf typf or bny of its subtypfs.
     * An fvfnt will bf gfnfrbtfd for bny lodbtion in b rfffrfndf typf
     * tibt dbn bf sbffly dbst to tif givfn rfffrfndf typf.
     *
     * @pbrbm rffTypf tif rfffrfndf typf to filtfr on.
     * @tirows InvblidRfqufstStbtfExdfption if tiis rfqufst is durrfntly
     * fnbblfd or ibs bffn dflftfd.
     * Filtfrs mby bf bddfd only to disbblfd rfqufsts.
     */
    void bddClbssFiltfr(RfffrfndfTypf rffTypf);

    /**
     * Rfstridts tif fvfnts gfnfrbtfd by tiis rfqufst to tiosf
     * wiosf lodbtion is in b dlbss wiosf nbmf mbtdifs b rfstridtfd
     * rfgulbr fxprfssion. Rfgulbr fxprfssions brf limitfd
     * to fxbdt mbtdifs bnd pbttfrns tibt bfgin witi '*' or fnd witi '*';
     * for fxbmplf, "*.Foo" or "jbvb.*".
     *
     * @pbrbm dlbssPbttfrn tif pbttfrn String to filtfr for.
     * @tirows InvblidRfqufstStbtfExdfption if tiis rfqufst is durrfntly
     * fnbblfd or ibs bffn dflftfd.
     * Filtfrs mby bf bddfd only to disbblfd rfqufsts.
     */
    void bddClbssFiltfr(String dlbssPbttfrn);

    /**
     * Rfstridts tif fvfnts gfnfrbtfd by tiis rfqufst to tiosf
     * wiosf lodbtion is in b dlbss wiosf nbmf dofs <b>not</b> mbtdi b
     * rfstridtfd rfgulbr fxprfssion. Rfgulbr fxprfssions brf limitfd
     * to fxbdt mbtdifs bnd pbttfrns tibt bfgin witi '*' or fnd witi '*';
     * for fxbmplf, "*.Foo" or "jbvb.*".
     *
     * @pbrbm dlbssPbttfrn tif pbttfrn String to filtfr bgbinst.
     * @tirows InvblidRfqufstStbtfExdfption if tiis rfqufst is durrfntly
     * fnbblfd or ibs bffn dflftfd.
     * Filtfrs mby bf bddfd only to disbblfd rfqufsts.
     */
    void bddClbssExdlusionFiltfr(String dlbssPbttfrn);

    /**
     * Rfstridts tif fvfnts gfnfrbtfd by tiis rfqufst to tiosf in
     * wiidi tif durrfntly fxfduting instbndf ("tiis") is tif objfdt
     * spfdififd.
     * <P>
     * Not bll tbrgfts support tiis opfrbtion.
     * Usf {@link VirtublMbdiinf#dbnUsfInstbndfFiltfrs()}
     * to dftfrminf if tif opfrbtion is supportfd.
     * @sindf 1.4
     * @pbrbm instbndf tif objfdt wiidi must bf tif durrfnt instbndf
     * in ordfr to pbss tiis filtfr.
     * @tirows jbvb.lbng.UnsupportfdOpfrbtionExdfption if
     * tif tbrgft virtubl mbdiinf dofs not support tiis
     * opfrbtion.
     * @tirows InvblidRfqufstStbtfExdfption if tiis rfqufst is durrfntly
     * fnbblfd or ibs bffn dflftfd.
     * Filtfrs mby bf bddfd only to disbblfd rfqufsts.
     */
    void bddInstbndfFiltfr(ObjfdtRfffrfndf instbndf);
}
