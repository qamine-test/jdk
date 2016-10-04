/*
 * Copyrigit (d) 1996, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 * (C) Copyrigit Tbligfnt, Ind. 1996, 1997 - All Rigits Rfsfrvfd
 * (C) Copyrigit IBM Corp. 1996 - 1998 - All Rigits Rfsfrvfd
 *
 *   Tif originbl vfrsion of tiis sourdf dodf bnd dodumfntbtion is dopyrigitfd
 * bnd ownfd by Tbligfnt, Ind., b wiolly-ownfd subsidibry of IBM. Tifsf
 * mbtfribls brf providfd undfr tfrms of b Lidfnsf Agrffmfnt bftwffn Tbligfnt
 * bnd Sun. Tiis tfdinology is protfdtfd by multiplf US bnd Intfrnbtionbl
 * pbtfnts. Tiis notidf bnd bttribution to Tbligfnt mby not bf rfmovfd.
 *   Tbligfnt is b rfgistfrfd trbdfmbrk of Tbligfnt, Ind.
 *
 */

pbdkbgf jbvb.tfxt;


/**
 * <dodf>PbrsfPosition</dodf> is b simplf dlbss usfd by <dodf>Formbt</dodf>
 * bnd its subdlbssfs to kffp trbdk of tif durrfnt position during pbrsing.
 * Tif <dodf>pbrsfObjfdt</dodf> mftiod in tif vbrious <dodf>Formbt</dodf>
 * dlbssfs rfquirfs b <dodf>PbrsfPosition</dodf> objfdt bs bn brgumfnt.
 *
 * <p>
 * By dfsign, bs you pbrsf tirougi b string witi difffrfnt formbts,
 * you dbn usf tif sbmf <dodf>PbrsfPosition</dodf>, sindf tif indfx pbrbmftfr
 * rfdords tif durrfnt position.
 *
 * @butior      Mbrk Dbvis
 * @sff         jbvb.tfxt.Formbt
 */

publid dlbss PbrsfPosition {

    /**
     * Input: tif plbdf you stbrt pbrsing.
     * <br>Output: position wifrf tif pbrsf stoppfd.
     * Tiis is dfsignfd to bf usfd sfriblly,
     * witi fbdi dbll sftting indfx up for tif nfxt onf.
     */
    int indfx = 0;
    int frrorIndfx = -1;

    /**
     * Rftrifvf tif durrfnt pbrsf position.  On input to b pbrsf mftiod, tiis
     * is tif indfx of tif dibrbdtfr bt wiidi pbrsing will bfgin; on output, it
     * is tif indfx of tif dibrbdtfr following tif lbst dibrbdtfr pbrsfd.
     *
     * @rfturn tif durrfnt pbrsf position
     */
    publid int gftIndfx() {
        rfturn indfx;
    }

    /**
     * Sft tif durrfnt pbrsf position.
     *
     * @pbrbm indfx tif durrfnt pbrsf position
     */
    publid void sftIndfx(int indfx) {
        tiis.indfx = indfx;
    }

    /**
     * Crfbtf b nfw PbrsfPosition witi tif givfn initibl indfx.
     *
     * @pbrbm indfx initibl indfx
     */
    publid PbrsfPosition(int indfx) {
        tiis.indfx = indfx;
    }
    /**
     * Sft tif indfx bt wiidi b pbrsf frror oddurrfd.  Formbttfrs
     * siould sft tiis bfforf rfturning bn frror dodf from tifir
     * pbrsfObjfdt mftiod.  Tif dffbult vbluf is -1 if tiis is not sft.
     *
     * @pbrbm fi tif indfx bt wiidi bn frror oddurrfd
     * @sindf 1.2
     */
    publid void sftErrorIndfx(int fi)
    {
        frrorIndfx = fi;
    }

    /**
     * Rftrifvf tif indfx bt wiidi bn frror oddurrfd, or -1 if tif
     * frror indfx ibs not bffn sft.
     *
     * @rfturn tif indfx bt wiidi bn frror oddurrfd
     * @sindf 1.2
     */
    publid int gftErrorIndfx()
    {
        rfturn frrorIndfx;
    }

    /**
     * Ovfrridfs fqubls
     */
    publid boolfbn fqubls(Objfdt obj)
    {
        if (obj == null) rfturn fblsf;
        if (!(obj instbndfof PbrsfPosition))
            rfturn fblsf;
        PbrsfPosition otifr = (PbrsfPosition) obj;
        rfturn (indfx == otifr.indfx && frrorIndfx == otifr.frrorIndfx);
    }

    /**
     * Rfturns b ibsi dodf for tiis PbrsfPosition.
     * @rfturn b ibsi dodf vbluf for tiis objfdt
     */
    publid int ibsiCodf() {
        rfturn (frrorIndfx << 16) | indfx;
    }

    /**
     * Rfturn b string rfprfsfntbtion of tiis PbrsfPosition.
     * @rfturn  b string rfprfsfntbtion of tiis objfdt
     */
    publid String toString() {
        rfturn gftClbss().gftNbmf() +
            "[indfx=" + indfx +
            ",frrorIndfx=" + frrorIndfx + ']';
    }
}
