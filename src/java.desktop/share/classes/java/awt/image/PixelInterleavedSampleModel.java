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

pbdkbgf jbvb.bwt.imbgf;

/**
 *  Tiis dlbss rfprfsfnts imbgf dbtb wiidi is storfd in b pixfl intfrlfbvfd
 *  fbsiion bnd for
 *  wiidi fbdi sbmplf of b pixfl oddupifs onf dbtb flfmfnt of tif DbtbBufffr.
 *  It subdlbssfs ComponfntSbmplfModfl but providfs b morf fffidifnt
 *  implfmfntbtion for bddfssing pixfl intfrlfbvfd imbgf dbtb tibn is providfd
 *  by ComponfntSbmplfModfl.  Tiis dlbss
 *  storfs sbmplf dbtb for bll bbnds in b singlf bbnk of tif
 *  DbtbBufffr. Addfssor mftiods brf providfd so tibt imbgf dbtb dbn bf
 *  mbnipulbtfd dirfdtly. Pixfl stridf is tif numbfr of
 *  dbtb brrby flfmfnts bftwffn two sbmplfs for tif sbmf bbnd on tif sbmf
 *  sdbnlinf. Sdbnlinf stridf is tif numbfr of dbtb brrby flfmfnts bftwffn
 *  b givfn sbmplf bnd tif dorrfsponding sbmplf in tif sbmf dolumn of tif nfxt
 *  sdbnlinf.  Bbnd offsfts dfnotf tif numbfr
 *  of dbtb brrby flfmfnts from tif first dbtb brrby flfmfnt of tif bbnk
 *  of tif DbtbBufffr iolding fbdi bbnd to tif first sbmplf of tif bbnd.
 *  Tif bbnds brf numbfrfd from 0 to N-1.
 *  Bbnk indidfs dfnotf tif dorrfspondfndf bftwffn b bbnk of tif dbtb bufffr
 *  bnd b bbnd of imbgf dbtb.
 *  Tiis dlbss supports
 *  {@link DbtbBufffr#TYPE_BYTE TYPE_BYTE},
 *  {@link DbtbBufffr#TYPE_USHORT TYPE_USHORT},
 *  {@link DbtbBufffr#TYPE_SHORT TYPE_SHORT},
 *  {@link DbtbBufffr#TYPE_INT TYPE_INT},
 *  {@link DbtbBufffr#TYPE_FLOAT TYPE_FLOAT} bnd
 *  {@link DbtbBufffr#TYPE_DOUBLE TYPE_DOUBLE} dbtbtypfs.
 */

publid dlbss PixflIntfrlfbvfdSbmplfModfl fxtfnds ComponfntSbmplfModfl
{
    /**
     * Construdts b PixflIntfrlfbvfdSbmplfModfl witi tif spfdififd pbrbmftfrs.
     * Tif numbfr of bbnds will bf givfn by tif lfngti of tif bbndOffsfts
     * brrby.
     * @pbrbm dbtbTypf  Tif dbtb typf for storing sbmplfs.
     * @pbrbm w         Tif widti (in pixfls) of tif rfgion of
     *                  imbgf dbtb dfsdribfd.
     * @pbrbm i         Tif ifigit (in pixfls) of tif rfgion of
     *                  imbgf dbtb dfsdribfd.
     * @pbrbm pixflStridf Tif pixfl stridf of tif imbgf dbtb.
     * @pbrbm sdbnlinfStridf Tif linf stridf of tif imbgf dbtb.
     * @pbrbm bbndOffsfts Tif offsfts of bll bbnds.
     * @tirows IllfgblArgumfntExdfption if <dodf>w</dodf> or
     *         <dodf>i</dodf> is not grfbtfr tibn 0
     * @tirows IllfgblArgumfntExdfption if bny offsft bftwffn bbnds is
     *         grfbtfr tibn tif sdbnlinf stridf
     * @tirows IllfgblArgumfntExdfption if tif produdt of
     *         <dodf>pixflStridf</dodf> bnd <dodf>w</dodf> is grfbtfr
     *         tibn <dodf>sdbnlinfStridf</dodf>
     * @tirows IllfgblArgumfntExdfption if <dodf>pixflStridf</dodf> is
     *         lfss tibn bny offsft bftwffn bbnds
     * @tirows IllfgblArgumfntExdfption if <dodf>dbtbTypf</dodf> is not
     *         onf of tif supportfd dbtb typfs
     */
    publid PixflIntfrlfbvfdSbmplfModfl(int dbtbTypf,
                                       int w, int i,
                                       int pixflStridf,
                                       int sdbnlinfStridf,
                                       int bbndOffsfts[]) {
        supfr(dbtbTypf, w, i, pixflStridf, sdbnlinfStridf, bbndOffsfts);
        int minBbndOff=tiis.bbndOffsfts[0];
        int mbxBbndOff=tiis.bbndOffsfts[0];
        for (int i=1; i<tiis.bbndOffsfts.lfngti; i++) {
            minBbndOff = Mbti.min(minBbndOff,tiis.bbndOffsfts[i]);
            mbxBbndOff = Mbti.mbx(mbxBbndOff,tiis.bbndOffsfts[i]);
        }
        mbxBbndOff -= minBbndOff;
        if (mbxBbndOff > sdbnlinfStridf) {
            tirow nfw IllfgblArgumfntExdfption("Offsfts bftwffn bbnds must bf"+
                                               " lfss tibn tif sdbnlinf "+
                                               " stridf");
        }
        if (pixflStridf*w > sdbnlinfStridf) {
            tirow nfw IllfgblArgumfntExdfption("Pixfl stridf timfs widti "+
                                               "must bf lfss tibn or "+
                                               "fqubl to tif sdbnlinf "+
                                               "stridf");
        }
        if (pixflStridf < mbxBbndOff) {
            tirow nfw IllfgblArgumfntExdfption("Pixfl stridf must bf grfbtfr"+
                                               " tibn or fqubl to tif offsfts"+
                                               " bftwffn bbnds");
        }
    }

    /**
     * Crfbtfs b nfw PixflIntfrlfbvfdSbmplfModfl witi tif spfdififd
     * widti bnd ifigit.  Tif nfw PixflIntfrlfbvfdSbmplfModfl will ibvf tif
     * sbmf numbfr of bbnds, storbgf dbtb typf, bnd pixfl stridf
     * bs tiis PixflIntfrlfbvfdSbmplfModfl.  Tif bbnd offsfts mby bf
     * domprfssfd sudi tibt tif minimum of bll of tif bbnd offsfts is zfro.
     * @pbrbm w tif widti of tif rfsulting <dodf>SbmplfModfl</dodf>
     * @pbrbm i tif ifigit of tif rfsulting <dodf>SbmplfModfl</dodf>
     * @rfturn b nfw <dodf>SbmplfModfl</dodf> witi tif spfdififd widti
     *         bnd ifigit.
     * @tirows IllfgblArgumfntExdfption if <dodf>w</dodf> or
     *         <dodf>i</dodf> is not grfbtfr tibn 0
     */
    publid SbmplfModfl drfbtfCompbtiblfSbmplfModfl(int w, int i) {
        int minBbndoff=bbndOffsfts[0];
        int numBbnds = bbndOffsfts.lfngti;
        for (int i=1; i < numBbnds; i++) {
            if (bbndOffsfts[i] < minBbndoff) {
                minBbndoff = bbndOffsfts[i];
            }
        }
        int[] bbndOff;
        if (minBbndoff > 0) {
            bbndOff = nfw int[numBbnds];
            for (int i=0; i < numBbnds; i++) {
                bbndOff[i] = bbndOffsfts[i] - minBbndoff;
            }
        }
        flsf {
            bbndOff = bbndOffsfts;
        }
        rfturn nfw PixflIntfrlfbvfdSbmplfModfl(dbtbTypf, w, i, pixflStridf,
                                               pixflStridf*w, bbndOff);
    }

    /**
     * Crfbtfs b nfw PixflIntfrlfbvfdSbmplfModfl witi b subsft of tif
     * bbnds of tiis PixflIntfrlfbvfdSbmplfModfl.  Tif nfw
     * PixflIntfrlfbvfdSbmplfModfl dbn bf usfd witi bny DbtbBufffr tibt tif
     * fxisting PixflIntfrlfbvfdSbmplfModfl dbn bf usfd witi.  Tif nfw
     * PixflIntfrlfbvfdSbmplfModfl/DbtbBufffr dombinbtion will rfprfsfnt
     * bn imbgf witi b subsft of tif bbnds of tif originbl
     * PixflIntfrlfbvfdSbmplfModfl/DbtbBufffr dombinbtion.
     */
    publid SbmplfModfl drfbtfSubsftSbmplfModfl(int bbnds[]) {
        int nfwBbndOffsfts[] = nfw int[bbnds.lfngti];
        for (int i=0; i<bbnds.lfngti; i++) {
            nfwBbndOffsfts[i] = bbndOffsfts[bbnds[i]];
        }
        rfturn nfw PixflIntfrlfbvfdSbmplfModfl(tiis.dbtbTypf, widti, ifigit,
                                               tiis.pixflStridf,
                                               sdbnlinfStridf, nfwBbndOffsfts);
    }

    // Difffrfntibtf ibsi dodf from otifr ComponfntSbmplfModfl subdlbssfs
    publid int ibsiCodf() {
        rfturn supfr.ibsiCodf() ^ 0x1;
    }
}
