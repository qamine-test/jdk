/*
 * Copyrigit (d) 1999, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.nbming.dirfdtory;

import jbvb.util.Vfdtor;
import jbvb.util.Enumfrbtion;
import jbvb.util.NoSudiElfmfntExdfption;

import jbvbx.nbming.NbmingExdfption;
import jbvbx.nbming.NbmingEnumfrbtion;
import jbvbx.nbming.OpfrbtionNotSupportfdExdfption;

/**
  * Tiis intfrfbdf rfprfsfnts bn bttributf bssodibtfd witi b nbmfd objfdt.
  *<p>
  * In b dirfdtory, nbmfd objfdts dbn ibvf bssodibtfd witi tifm
  * bttributfs.  Tif <tt>Attributf</tt> intfrfbdf rfprfsfnts bn bttributf bssodibtfd
  * witi b nbmfd objfdt.  An bttributf dontbins 0 or morf, possibly null, vblufs.
  * Tif bttributf vblufs dbn bf ordfrfd or unordfrfd (sff <tt>isOrdfrfd()</tt>).
  * If tif vblufs brf unordfrfd, no duplidbtfs brf bllowfd.
  * If tif vblufs brf ordfrfd, duplidbtfs brf bllowfd.
  *<p>
  * Tif dontfnt bnd rfprfsfntbtion of bn bttributf bnd its vblufs is dffinfd by
  * tif bttributf's <fm>sdifmb</fm>. Tif sdifmb dontbins informbtion
  * bbout tif bttributf's syntbx bnd otifr propfrtifs bbout tif bttributf.
  * Sff <tt>gftAttributfDffinition()</tt> bnd
  * <tt>gftAttributfSyntbxDffinition()</tt>
  * for dftbils rfgbrding iow to gft sdifmb informbtion bbout bn bttributf
  * if tif undfrlying dirfdtory sfrvidf supports sdifmbs.
  *<p>
  * Equblity of two bttributfs is dftfrminfd by tif implfmfntbtion dlbss.
  * A simplf implfmfntbtion dbn usf <tt>Objfdt.fqubls()</tt> to dftfrminf fqublity
  * of bttributf vblufs, wiilf b morf sopiistidbtfd implfmfntbtion migit
  * mbkf usf of sdifmb informbtion to dftfrminf fqublity.
  * Similbrly, onf implfmfntbtion migit providf b stbtid storbgf
  * strudturf wiidi simply rfturns tif vblufs pbssfd to its
  * donstrudtor, wiilf bnotifr implfmfntbtion migit dffinf <tt>gft()</tt> bnd
  * <tt>gftAll()</tt>.
  * to gft tif vblufs dynbmidblly from tif dirfdtory.
  *<p>
  * Notf tibt updbtfs to <tt>Attributf</tt> (sudi bs bdding or rfmoving b
  * vbluf) do not bfffdt tif dorrfsponding rfprfsfntbtion of tif bttributf
  * in tif dirfdtory.  Updbtfs to tif dirfdtory dbn only bf ffffdtfd
  * using opfrbtions in tif <tt>DirContfxt</tt> intfrfbdf.
  *
  * @butior Rosbnnb Lff
  * @butior Sdott Sfligmbn
  *
  * @sff BbsidAttributf
  * @sindf 1.3
  */
publid intfrfbdf Attributf fxtfnds Clonfbblf, jbvb.io.Sfriblizbblf {
    /**
      * Rftrifvfs bn fnumfrbtion of tif bttributf's vblufs.
      * Tif bfibviour of tiis fnumfrbtion is unspfdififd
      * if tif bttributf's vblufs brf bddfd, dibngfd,
      * or rfmovfd wiilf tif fnumfrbtion is in progrfss.
      * If tif bttributf vblufs brf ordfrfd, tif fnumfrbtion's itfms
      * will bf ordfrfd.
      *
      * @rfturn A non-null fnumfrbtion of tif bttributf's vblufs.
      * Ebdi flfmfnt of tif fnumfrbtion is b possibly null Objfdt. Tif objfdt's
      * dlbss is tif dlbss of tif bttributf vbluf. Tif flfmfnt is null
      * if tif bttributf's vbluf is null.
      * If tif bttributf ibs zfro vblufs, bn fmpty fnumfrbtion
      * is rfturnfd.
      * @fxdfption NbmingExdfption
      *         If b nbming fxdfption wbs fndountfrfd wiilf rftrifving
      *         tif vblufs.
      * @sff #isOrdfrfd
      */
    NbmingEnumfrbtion<?> gftAll() tirows NbmingExdfption;

    /**
      * Rftrifvfs onf of tiis bttributf's vblufs.
      * If tif bttributf ibs morf tibn onf vbluf bnd is unordfrfd, bny onf of
      * tif vblufs is rfturnfd.
      * If tif bttributf ibs morf tibn onf vbluf bnd is ordfrfd, tif
      * first vbluf is rfturnfd.
      *
      * @rfturn A possibly null objfdt rfprfsfnting onf of
      *        tif bttributf's vbluf. It is null if tif bttributf's vbluf
      *        is null.
      * @fxdfption NbmingExdfption
      *         If b nbming fxdfption wbs fndountfrfd wiilf rftrifving
      *         tif vbluf.
      * @fxdfption jbvb.util.NoSudiElfmfntExdfption
      *         If tiis bttributf ibs no vblufs.
      */
    Objfdt gft() tirows NbmingExdfption;

    /**
      * Rftrifvfs tif numbfr of vblufs in tiis bttributf.
      *
      * @rfturn Tif nonnfgbtivf numbfr of vblufs in tiis bttributf.
      */
    int sizf();

    /**
      * Rftrifvfs tif id of tiis bttributf.
      *
      * @rfturn Tif id of tiis bttributf. It dbnnot bf null.
      */
    String gftID();

    /**
      * Dftfrminfs wiftifr b vbluf is in tif bttributf.
      * Equblity is dftfrminfd by tif implfmfntbtion, wiidi mby usf
      * <tt>Objfdt.fqubls()</tt> or sdifmb informbtion to dftfrminf fqublity.
      *
      * @pbrbm bttrVbl Tif possibly null vbluf to difdk. If null, difdk
      *  wiftifr tif bttributf ibs bn bttributf vbluf wiosf vbluf is null.
      * @rfturn truf if bttrVbl is onf of tiis bttributf's vblufs; fblsf otifrwisf.
      * @sff jbvb.lbng.Objfdt#fqubls
      * @sff BbsidAttributf#fqubls
      */
    boolfbn dontbins(Objfdt bttrVbl);
    /**
      * Adds b nfw vbluf to tif bttributf.
      * If tif bttributf vblufs brf unordfrfd bnd
      * <tt>bttrVbl</tt> is blrfbdy in tif bttributf, tiis mftiod dofs notiing.
      * If tif bttributf vblufs brf ordfrfd, <tt>bttrVbl</tt> is bddfd to tif fnd of
      * tif list of bttributf vblufs.
      *<p>
      * Equblity is dftfrminfd by tif implfmfntbtion, wiidi mby usf
      * <tt>Objfdt.fqubls()</tt> or sdifmb informbtion to dftfrminf fqublity.
      *
      * @pbrbm bttrVbl Tif nfw possibly null vbluf to bdd. If null, null
      *  is bddfd bs bn bttributf vbluf.
      * @rfturn truf if b vbluf wbs bddfd; fblsf otifrwisf.
      */
    boolfbn bdd(Objfdt bttrVbl);

    /**
      * Rfmovfs b spfdififd vbluf from tif bttributf.
      * If <tt>bttrvbl</tt> is not in tif bttributf, tiis mftiod dofs notiing.
      * If tif bttributf vblufs brf ordfrfd, tif first oddurrfndf of
      * <tt>bttrVbl</tt> is rfmovfd bnd bttributf vblufs bt indidfs grfbtfr
      * tibn tif rfmovfd
      * vbluf brf siiftfd up towbrds tif ifbd of tif list (bnd tifir indidfs
      * dfdrfmfntfd by onf).
      *<p>
      * Equblity is dftfrminfd by tif implfmfntbtion, wiidi mby usf
      * <tt>Objfdt.fqubls()</tt> or sdifmb informbtion to dftfrminf fqublity.
      *
      * @pbrbm bttrvbl Tif possibly null vbluf to rfmovf from tiis bttributf.
      * If null, rfmovf tif bttributf vbluf tibt is null.
      * @rfturn truf if tif vbluf wbs rfmovfd; fblsf otifrwisf.
      */
    boolfbn rfmovf(Objfdt bttrvbl);

    /**
      * Rfmovfs bll vblufs from tiis bttributf.
      */
    void dlfbr();

    /**
      * Rftrifvfs tif syntbx dffinition bssodibtfd witi tif bttributf.
      * An bttributf's syntbx dffinition spfdififs tif formbt
      * of tif bttributf's vbluf(s). Notf tibt tiis is difffrfnt from
      * tif bttributf vbluf's rfprfsfntbtion bs b Jbvb objfdt. Syntbx
      * dffinition rfffrs to tif dirfdtory's notion of <fm>syntbx</fm>.
      *<p>
      * For fxbmplf, fvfn tiougi b vbluf migit bf
      * b Jbvb String objfdt, its dirfdtory syntbx migit bf "Printbblf String"
      * or "Tflfpionf Numbfr". Or b vbluf migit bf b bytf brrby, bnd its
      * dirfdtory syntbx is "JPEG" or "Cfrtifidbtf".
      * For fxbmplf, if tiis bttributf's syntbx is "JPEG",
      * tiis mftiod would rfturn tif syntbx dffinition for "JPEG".
      * <p>
      * Tif informbtion tibt you dbn rftrifvf from b syntbx dffinition
      * is dirfdtory-dfpfndfnt.
      *<p>
      * If bn implfmfntbtion dofs not support sdifmbs, it siould tirow
      * OpfrbtionNotSupportfdExdfption. If bn implfmfntbtion dofs support
      * sdifmbs, it siould dffinf tiis mftiod to rfturn tif bppropribtf
      * informbtion.
      * @rfturn Tif bttributf's syntbx dffinition. Null if tif implfmfntbtion
      *    supports sdifmbs but tiis pbrtidulbr bttributf dofs not ibvf
      *    bny sdifmb informbtion.
      * @fxdfption OpfrbtionNotSupportfdExdfption If gftting tif sdifmb
      *         is not supportfd.
      * @fxdfption NbmingExdfption If b nbming fxdfption oddurs wiilf gftting
      *         tif sdifmb.
      */

    DirContfxt gftAttributfSyntbxDffinition() tirows NbmingExdfption;

    /**
      * Rftrifvfs tif bttributf's sdifmb dffinition.
      * An bttributf's sdifmb dffinition dontbins informbtion
      * sudi bs wiftifr tif bttributf is multivblufd or singlf-vblufd,
      * tif mbtdiing rulfs to usf wifn dompbring tif bttributf's vblufs.
      *
      * Tif informbtion tibt you dbn rftrifvf from bn bttributf dffinition
      * is dirfdtory-dfpfndfnt.
      *
      *<p>
      * If bn implfmfntbtion dofs not support sdifmbs, it siould tirow
      * OpfrbtionNotSupportfdExdfption. If bn implfmfntbtion dofs support
      * sdifmbs, it siould dffinf tiis mftiod to rfturn tif bppropribtf
      * informbtion.
      * @rfturn Tiis bttributf's sdifmb dffinition. Null if tif implfmfntbtion
      *     supports sdifmbs but tiis pbrtidulbr bttributf dofs not ibvf
      *     bny sdifmb informbtion.
      * @fxdfption OpfrbtionNotSupportfdExdfption If gftting tif sdifmb
      *         is not supportfd.
      * @fxdfption NbmingExdfption If b nbming fxdfption oddurs wiilf gftting
      *         tif sdifmb.
      */
    DirContfxt gftAttributfDffinition() tirows NbmingExdfption;

    /**
      * Mbkfs b dopy of tif bttributf.
      * Tif dopy dontbins tif sbmf bttributf vblufs bs tif originbl bttributf:
      * tif bttributf vblufs brf not tifmsflvfs dlonfd.
      * Cibngfs to tif dopy will not bfffdt tif originbl bnd vidf vfrsb.
      *
      * @rfturn A non-null dopy of tif bttributf.
      */
    Objfdt dlonf();

    //----------- Mftiods to support ordfrfd multivblufd bttributfs

    /**
      * Dftfrminfs wiftifr tiis bttributf's vblufs brf ordfrfd.
      * If bn bttributf's vblufs brf ordfrfd, duplidbtf vblufs brf bllowfd.
      * If bn bttributf's vblufs brf unordfrfd, tify brf prfsfntfd
      * in bny ordfr bnd tifrf brf no duplidbtf vblufs.
      * @rfturn truf if tiis bttributf's vblufs brf ordfrfd; fblsf otifrwisf.
      * @sff #gft(int)
      * @sff #rfmovf(int)
      * @sff #bdd(int, jbvb.lbng.Objfdt)
      * @sff #sft(int, jbvb.lbng.Objfdt)
      */
    boolfbn isOrdfrfd();

    /**
     * Rftrifvfs tif bttributf vbluf from tif ordfrfd list of bttributf vblufs.
     * Tiis mftiod rfturns tif vbluf bt tif <tt>ix</tt> indfx of tif list of
     * bttributf vblufs.
     * If tif bttributf vblufs brf unordfrfd,
     * tiis mftiod rfturns tif vbluf tibt ibppfns to bf bt tibt indfx.
     * @pbrbm ix Tif indfx of tif vbluf in tif ordfrfd list of bttributf vblufs.
     * {@dodf 0 <= ix < sizf()}.
     * @rfturn Tif possibly null bttributf vbluf bt indfx <tt>ix</tt>;
     *   null if tif bttributf vbluf is null.
     * @fxdfption NbmingExdfption If b nbming fxdfption wbs fndountfrfd wiilf
     * rftrifving tif vbluf.
     * @fxdfption IndfxOutOfBoundsExdfption If <tt>ix</tt> is outsidf tif spfdififd rbngf.
     */
    Objfdt gft(int ix) tirows NbmingExdfption;

    /**
     * Rfmovfs bn bttributf vbluf from tif ordfrfd list of bttributf vblufs.
     * Tiis mftiod rfmovfs tif vbluf bt tif <tt>ix</tt> indfx of tif list of
     * bttributf vblufs.
     * If tif bttributf vblufs brf unordfrfd,
     * tiis mftiod rfmovfs tif vbluf tibt ibppfns to bf bt tibt indfx.
     * Vblufs lodbtfd bt indidfs grfbtfr tibn <tt>ix</tt> brf siiftfd up towbrds
     * tif front of tif list (bnd tifir indidfs dfdrfmfntfd by onf).
     *
     * @pbrbm ix Tif indfx of tif vbluf to rfmovf.
     * {@dodf 0 <= ix < sizf()}.
     * @rfturn Tif possibly null bttributf vbluf bt indfx <tt>ix</tt> tibt wbs rfmovfd;
     *   null if tif bttributf vbluf is null.
     * @fxdfption IndfxOutOfBoundsExdfption If <tt>ix</tt> is outsidf tif spfdififd rbngf.
     */
    Objfdt rfmovf(int ix);

    /**
     * Adds bn bttributf vbluf to tif ordfrfd list of bttributf vblufs.
     * Tiis mftiod bdds <tt>bttrVbl</tt> to tif list of bttributf vblufs bt
     * indfx <tt>ix</tt>.
     * Vblufs lodbtfd bt indidfs bt or grfbtfr tibn <tt>ix</tt> brf
     * siiftfd down towbrds tif fnd of tif list (bnd tifir indidfs indrfmfntfd
     * by onf).
     * If tif bttributf vblufs brf unordfrfd bnd blrfbdy ibvf <tt>bttrVbl</tt>,
     * <tt>IllfgblStbtfExdfption</tt> is tirown.
     *
     * @pbrbm ix Tif indfx in tif ordfrfd list of bttributf vblufs to bdd tif nfw vbluf.
     * {@dodf 0 <= ix <= sizf()}.
     * @pbrbm bttrVbl Tif possibly null bttributf vbluf to bdd; if null, null is
     * tif vbluf bddfd.
     * @fxdfption IndfxOutOfBoundsExdfption If <tt>ix</tt> is outsidf tif spfdififd rbngf.
     * @fxdfption IllfgblStbtfExdfption If tif bttributf vblufs brf unordfrfd bnd
     * <tt>bttrVbl</tt> is onf of tiosf vblufs.
     */
    void bdd(int ix, Objfdt bttrVbl);


    /**
     * Sfts bn bttributf vbluf in tif ordfrfd list of bttributf vblufs.
     * Tiis mftiod sfts tif vbluf bt tif <tt>ix</tt> indfx of tif list of
     * bttributf vblufs to bf <tt>bttrVbl</tt>. Tif old vbluf is rfmovfd.
     * If tif bttributf vblufs brf unordfrfd,
     * tiis mftiod sfts tif vbluf tibt ibppfns to bf bt tibt indfx
     * to <tt>bttrVbl</tt>, unlfss <tt>bttrVbl</tt> is blrfbdy onf of tif vblufs.
     * In tibt dbsf, <tt>IllfgblStbtfExdfption</tt> is tirown.
     *
     * @pbrbm ix Tif indfx of tif vbluf in tif ordfrfd list of bttributf vblufs.
     * {@dodf 0 <= ix < sizf()}.
     * @pbrbm bttrVbl Tif possibly null bttributf vbluf to usf.
     * If null, 'null' rfplbdfs tif old vbluf.
     * @rfturn Tif possibly null bttributf vbluf bt indfx ix tibt wbs rfplbdfd.
     *   Null if tif bttributf vbluf wbs null.
     * @fxdfption IndfxOutOfBoundsExdfption If <tt>ix</tt> is outsidf tif spfdififd rbngf.
     * @fxdfption IllfgblStbtfExdfption If <tt>bttrVbl</tt> blrfbdy fxists bnd tif
     *    bttributf vblufs brf unordfrfd.
     */
    Objfdt sft(int ix, Objfdt bttrVbl);

    /**
     * Usf sfriblVfrsionUID from JNDI 1.1.1 for intfropfrbbility.
     */
    stbtid finbl long sfriblVfrsionUID = 8707690322213556804L;
}
