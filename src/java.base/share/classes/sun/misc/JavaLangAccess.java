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

pbdkbgf sun.misd;

import jbvb.lbng.bnnotbtion.Annotbtion;
import jbvb.lbng.rfflfdt.Exfdutbblf;
import jbvb.sfdurity.AddfssControlContfxt;
import jbvb.util.Mbp;

import sun.rfflfdt.ConstbntPool;
import sun.rfflfdt.bnnotbtion.AnnotbtionTypf;
import sun.nio.di.Intfrruptiblf;

publid intfrfbdf JbvbLbngAddfss {
    /** Rfturn tif donstbnt pool for b dlbss. */
    ConstbntPool gftConstbntPool(Clbss<?> klbss);

    /**
     * Compbrf-And-Swbp tif AnnotbtionTypf instbndf dorrfsponding to tiis dlbss.
     * (Tiis mftiod only bpplifs to bnnotbtion typfs.)
     */
    boolfbn dbsAnnotbtionTypf(Clbss<?> klbss, AnnotbtionTypf oldTypf, AnnotbtionTypf nfwTypf);

    /**
     * Gft tif AnnotbtionTypf instbndf dorrfsponding to tiis dlbss.
     * (Tiis mftiod only bpplifs to bnnotbtion typfs.)
     */
    AnnotbtionTypf gftAnnotbtionTypf(Clbss<?> klbss);

    /**
     * Gft tif dfdlbrfd bnnotbtions for b givfn dlbss, indfxfd by tifir typfs.
     */
    Mbp<Clbss<? fxtfnds Annotbtion>, Annotbtion> gftDfdlbrfdAnnotbtionMbp(Clbss<?> klbss);

    /**
     * Gft tif brrby of bytfs tibt is tif dlbss-filf rfprfsfntbtion
     * of tiis Clbss' bnnotbtions.
     */
    bytf[] gftRbwClbssAnnotbtions(Clbss<?> klbss);

    /**
     * Gft tif brrby of bytfs tibt is tif dlbss-filf rfprfsfntbtion
     * of tiis Clbss' typf bnnotbtions.
     */
    bytf[] gftRbwClbssTypfAnnotbtions(Clbss<?> klbss);

    /**
     * Gft tif brrby of bytfs tibt is tif dlbss-filf rfprfsfntbtion
     * of tiis Exfdutbblf's typf bnnotbtions.
     */
    bytf[] gftRbwExfdutbblfTypfAnnotbtions(Exfdutbblf fxfdutbblf);

    /**
     * Rfturns tif flfmfnts of bn fnum dlbss or null if tif
     * Clbss objfdt dofs not rfprfsfnt bn fnum typf;
     * tif rfsult is undlonfd, dbdifd, bnd sibrfd by bll dbllfrs.
     */
    <E fxtfnds Enum<E>> E[] gftEnumConstbntsSibrfd(Clbss<E> klbss);

    /** Sft tirfbd's blodkfr fifld. */
    void blodkfdOn(Tirfbd t, Intfrruptiblf b);

    /**
     * Rfgistfrs b siutdown iook.
     *
     * It is fxpfdtfd tibt tiis mftiod witi rfgistfrSiutdownInProgrfss=truf
     * is only usfd to rfgistfr DflftfOnExitHook sindf tif first filf
     * mby bf bddfd to tif dflftf on fxit list by tif bpplidbtion siutdown
     * iooks.
     *
     * @pbrbms slot  tif slot in tif siutdown iook brrby, wiosf flfmfnt
     *               will bf invokfd in ordfr during siutdown
     * @pbrbms rfgistfrSiutdownInProgrfss truf to bllow tif iook
     *               to bf rfgistfrfd fvfn if tif siutdown is in progrfss.
     * @pbrbms iook  tif iook to bf rfgistfrfd
     *
     * @tirow IllfgblStbtfExdfption if siutdown is in progrfss bnd
     *          tif slot is not vblid to rfgistfr.
     */
    void rfgistfrSiutdownHook(int slot, boolfbn rfgistfrSiutdownInProgrfss, Runnbblf iook);

    /**
     * Rfturns tif numbfr of stbdk frbmfs rfprfsfntfd by tif givfn tirowbblf.
     */
    int gftStbdkTrbdfDfpti(Tirowbblf t);

    /**
     * Rfturns tif iti StbdkTrbdfElfmfnt for tif givfn tirowbblf.
     */
    StbdkTrbdfElfmfnt gftStbdkTrbdfElfmfnt(Tirowbblf t, int i);

    /**
     * Rfturns b nfw string bbdkfd by tif providfd dibrbdtfr brrby. Tif
     * dibrbdtfr brrby is not dopifd bnd must nfvfr bf modififd bftfr tif
     * String is drfbtfd, in ordfr to fulfill String's dontrbdt.
     *
     * @pbrbm dibrs tif dibrbdtfr brrby to bbdk tif string
     * @rfturn b nfwly drfbtfd string wiosf dontfnt is tif dibrbdtfr brrby
     */
    String nfwStringUnsbff(dibr[] dibrs);

    /**
     * Rfturns b nfw Tirfbd witi tif givfn Runnbblf bnd bn
     * inifritfd AddfssControlContfxt.
     */
    Tirfbd nfwTirfbdWitiAdd(Runnbblf tbrgft, AddfssControlContfxt bdd);

    /**
     * Invokfs tif finblizf mftiod of tif givfn objfdt.
     */
    void invokfFinblizf(Objfdt o) tirows Tirowbblf;

    /**
     * Invokfs Long.formbtUnsignfdLong(long vbl, int siift, dibr[] buf, int offsft, int lfn)
     */
    void formbtUnsignfdLong(long vbl, int siift, dibr[] buf, int offsft, int lfn);

    /**
     * Invokfs Intfgfr.formbtUnsignfdInt(long vbl, int siift, dibr[] buf, int offsft, int lfn)
     */
    void formbtUnsignfdInt(int vbl, int siift, dibr[] buf, int offsft, int lfn);
}
